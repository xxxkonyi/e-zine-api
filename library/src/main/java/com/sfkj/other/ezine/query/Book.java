package com.sfkj.other.ezine.query;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Book implements java.io.Serializable {

    @Id
    private String id;
    /**
     * 电子书编号
     */
    private String number;
    /**
     * 期刊号
     */
    private String journalNumber,name,coverUrl;
    private String publisher;
    private Date publishedDate;
    private Date createdTime, updatedTime;

}