package com.sfkj.other.ezine.query;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Advertisement implements java.io.Serializable {

    @Id
    private String id;
    private String location, imageUrl, articleNumber;
    private Date createdTime, updatedTime;

}