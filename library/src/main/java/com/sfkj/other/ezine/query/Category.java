package com.sfkj.other.ezine.query;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Category implements java.io.Serializable {

    @Id
    private String id;
    private String name;
    private Double sequence;
    private Date createdTime, updatedTime;

}