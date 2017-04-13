package com.sfkj.other.ezine.query;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private String content;
    private String publisher;
    private DateTime publishedDate;
    private DateTime createdTime, updatedTime;

}