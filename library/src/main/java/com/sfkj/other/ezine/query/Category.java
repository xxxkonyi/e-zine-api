package com.sfkj.other.ezine.query;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Category {

    @Id
    private String id;
    private String name;
    private Double sequence;

}