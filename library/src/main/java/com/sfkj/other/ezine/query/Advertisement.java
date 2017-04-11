package com.sfkj.other.ezine.query;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Advertisement {

    @Id
    private String id;
    private String location;
    private String imageUrl;
    private String articleId;

}