package com.sfkj.other.ezine.query;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Article implements java.io.Serializable {

    @Id
    private String id;
    private String bookId;
    private List<String> categoryIds;
    private String title;
    private String coverUrl;
    private String publisher;
    private String publishedDate;
    private String content;

}