package com.sfkj.other.ezine.query;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Book implements java.io.Serializable {

    @Id
    private String id;
    private String name;
    private String publisher;
    private String publishedDate;

}