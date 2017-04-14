package com.sfkj.other.ezine.web;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Predicate;
import com.sfkj.other.ezine.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/f")
public class HomeController {

    private final AdvertisementRepository advertisementRepository;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final ArticleRepository articleRepository;
    private final ArticleContentRepository articleContentRepository;

    @MessageMapping("/health")
    @RequestMapping("/health")
    public Map<String, String> health() {
        return ImmutableMap.of("status", "UP");
    }

    @MessageMapping("/advertisements")
    @RequestMapping("/advertisements")
    public Iterable<Advertisement> advertisements(@QuerydslPredicate(root = Advertisement.class) Predicate predicate) {
        return advertisementRepository.findAll(predicate);
    }

    @MessageMapping("/categories")
    @RequestMapping("/categories")
    public Iterable<Category> categories(@QuerydslPredicate(root = Category.class) Predicate predicate) {
        return categoryRepository.findAll(predicate);
    }

    @MessageMapping("/books")
    @RequestMapping("/books")
    public Iterable<Book> books(@QuerydslPredicate(root = Book.class) Predicate predicate, Pageable pageable) {
        return bookRepository.findAll(predicate, pageable);
    }

    @MessageMapping("/articles")
    @RequestMapping("/articles")
    public Iterable<Article> articles(@QuerydslPredicate(root = Article.class) Predicate predicate, Pageable pageable) {
        return articleRepository.findAll(predicate, pageable);
    }

    @MessageMapping("/articles/{number}")
    @RequestMapping("/articles/{number}")
    public Article article(@PathVariable String number) {
        Article article = articleRepository.findByNumber(number);
        if (Objects.isNull(article)) {
            article = articleRepository.findOne(number);
        }
        if (Objects.isNull(article)) {
            return article;
        }
        ArticleContent content = articleContentRepository.findByArticleId(article.getId());
        if (Objects.nonNull(content)) {
            article.setContent(content.getContent());
        }
        return article;
    }

    @RequestMapping(value = "/articles/{identifier}/viewCount", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void view(@PathVariable String identifier) {
        Article article = articleRepository.findOne(identifier);
        if (Objects.isNull(article)) {
            return;
        }
        article.setViewCount(article.getViewCount() + 1);
    }

}
