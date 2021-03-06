package com.sfkj.other.ezine.web.admin;

import com.querydsl.core.types.Predicate;
import com.sfkj.other.ezine.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/a/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleContentRepository articleContentRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @RequestMapping
    public Iterable<Article> list(@QuerydslPredicate(root = Article.class) Predicate predicate, Pageable pageable) {
        Page<Article> page = articleRepository.findAll(predicate, pageable);
        List<Article> dtos = page.getContent().stream().map(t->get(t.getId())).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.GET)
    public Article get(@PathVariable String identifier) {
        Article article = articleRepository.findOne(identifier);
        if (Objects.isNull(article)) {
            return article;
        }

        Book book = bookRepository.findOne(article.getBookId());
        if (Objects.nonNull(book)) {
            article.setBook(book);
        }
        Category category = categoryRepository.findOne(article.getCategoryId());
        if (Objects.nonNull(category)) {
            article.setCategory(category);
        }

        return article;
    }

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Article create(@RequestBody Article dto) {
        Article article = new Article();
        article.setCategoryId(dto.getCategoryId());

        Book book = bookRepository.findByNumber(dto.getBookId());
        if (Objects.nonNull(book)) {
            article.setBookId(book.getId());
        }
        if (Objects.nonNull(book)) {
            article.setBookId(book.getId());
        }
        article.setNumber(String.valueOf(articleRepository.count() + 1));
        article.setTitle(dto.getTitle());
        article.setCoverUrl(dto.getCoverUrl());
        article.setViewCount(dto.getViewCount());
        article.setPublisher(dto.getPublisher());
        article.setPublishedDate(dto.getPublishedDate());

        article.setCreatedTime(new Date());
        article.setUpdatedTime(new Date());
        articleRepository.save(article);

        ArticleContent content = new ArticleContent();
        content.setArticleId(article.getId());
        content.setContent(dto.getContent());
        article.setCreatedTime(new Date());
        article.setUpdatedTime(new Date());
        articleContentRepository.save(content);

        return article;
    }

    @RequestMapping(value = "/{identifier}", method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String identifier) {
        articleRepository.delete(identifier);
    }

    @RequestMapping(value = "/{identifier}", method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void change(@PathVariable String identifier,
                       @RequestBody Article dto) {
        Article article = new Article();
        article.setCategoryId(identifier);
        Book book = bookRepository.findByNumber(dto.getBookId());
        if (Objects.nonNull(book)) {
            article.setBookId(book.getId());
        }
        if (Objects.nonNull(book)) {
            article.setBookId(book.getId());
        }
        article.setTitle(dto.getTitle());
        article.setCoverUrl(dto.getCoverUrl());
        article.setViewCount(dto.getViewCount());
        article.setPublisher(dto.getPublisher());
        article.setPublishedDate(dto.getPublishedDate());

        article.setUpdatedTime(new Date());
        articleRepository.save(article);

        ArticleContent content = articleContentRepository.findByArticleId(article.getId());
        if (Objects.isNull(content)) {
            content = new ArticleContent();
        }
        content.setContent(dto.getContent());
        article.setUpdatedTime(new Date());
        articleContentRepository.save(content);
    }

}
