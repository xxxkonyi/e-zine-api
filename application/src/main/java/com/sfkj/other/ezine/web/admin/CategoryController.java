package com.sfkj.other.ezine.web.admin;

import com.querydsl.core.types.Predicate;
import com.sfkj.other.ezine.query.Category;
import com.sfkj.other.ezine.query.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/a/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @RequestMapping
    public Iterable<Category> list(@QuerydslPredicate(root = Category.class) Predicate predicate, Sort sort) {
        return categoryRepository.findAll(predicate, sort);
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.GET)
    public Category get(@PathVariable String identifier) {
        return categoryRepository.findOne(identifier);
    }

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Category dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setSequence(dto.getSequence());

        category.setCreatedTime(DateTime.now());
        category.setUpdatedTime(DateTime.now());
        categoryRepository.save(category);
    }

    @RequestMapping(value = "/{identifier}", method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String identifier) {
        categoryRepository.delete(identifier);
    }

    @RequestMapping(value = "/{identifier}", method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void change(@PathVariable String identifier,
                       @RequestBody Category dto) {
        Category category = new Category();
        category.setId(identifier);
        category.setName(dto.getName());
        category.setSequence(dto.getSequence());

        category.setUpdatedTime(DateTime.now());
        categoryRepository.save(category);
    }

}
