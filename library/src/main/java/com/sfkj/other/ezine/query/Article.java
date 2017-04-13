package com.sfkj.other.ezine.query;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Article implements java.io.Serializable {

    @Id
    private String id;
    private String categoryId, bookId;
    /**
     * 文章编号
     */
    private String number, title, coverUrl;
    private Long viewCount;
    private String publisher;
    private Date publishedDate;
    private Date createdTime, updatedTime;

    // By Query
    private String content;
}