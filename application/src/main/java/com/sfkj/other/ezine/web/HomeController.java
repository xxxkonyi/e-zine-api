package com.sfkj.other.ezine.web;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Predicate;
import com.sfkj.other.ezine.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class HomeController {

    private AdvertisementRepository advertisementRepository;
    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;
    private ArticleRepository articleRepository;

    @MessageMapping("/health")
    public Map<String, String> health() {
        return ImmutableMap.of("status", "UP");
    }

    @MessageMapping("/advertisements")
    public Iterable<Category> advertisementRepository(String location) {
        return categoryRepository.findAll();
    }

    @MessageMapping("/categories")
    public Iterable<Category> categories(@QuerydslPredicate(root = Category.class) Predicate predicate) {
        return categoryRepository.findAll(predicate);
    }

    @MessageMapping("/books")
    public Iterable<Book> books(@QuerydslPredicate(root = Book.class) Predicate predicate, Pageable pageable) {
        return bookRepository.findAll();
    }

    @MessageMapping("/articles")
    public Iterable<Article> articles(@QuerydslPredicate(root = Article.class) Predicate predicate) {
        return articleRepository.findAll();
    }

}
