package com.sfkj.other.ezine.query;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleContentRepository extends MongoRepository<ArticleContent, String>, QueryDslPredicateExecutor<ArticleContent> {

    ArticleContent findByArticleId(String articleId);

}