package com.sfkj.other.ezine.query;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Article {

    @Id
    private String id;
    private String bookId;
    private List<String> categoryId;
    private String title;
    private String editor;
    private String publicationTime;
    private String content;

}