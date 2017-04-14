package com.sfkj.other.ezine.web.admin;

import com.querydsl.core.types.Predicate;
import com.sfkj.other.ezine.query.Article;
import com.sfkj.other.ezine.query.ArticleContent;
import com.sfkj.other.ezine.query.ArticleContentRepository;
import com.sfkj.other.ezine.query.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/a/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleContentRepository articleContentRepository;

    @RequestMapping
    public Iterable<Article> list(@QuerydslPredicate(root = Article.class) Predicate predicate, Pageable pageable) {
        return articleRepository.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.GET)
    public Article get(@PathVariable String identifier) {
        return articleRepository.findOne(identifier);
    }

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Article create(@RequestBody Article dto) {
        Article article = new Article();
        article.setCategoryId(dto.getCategoryId());
        article.setBookId(dto.getBookId());
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
        article.setBookId(dto.getBookId());
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
