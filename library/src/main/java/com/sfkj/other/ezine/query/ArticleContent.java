package com.sfkj.other.ezine.query;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class ArticleContent implements java.io.Serializable {

    @Id
    private String id;
    private String articleId;
    private String content;
    private Date createdTime, updatedTime;

}