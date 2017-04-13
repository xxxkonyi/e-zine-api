package com.sfkj.other.ezine.query;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Advertisement implements java.io.Serializable {

    @Id
    private String id;
    private String location, imageUrl, articleNumber;
    private DateTime createdTime, updatedTime;

}