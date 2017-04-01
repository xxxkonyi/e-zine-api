package com.sfkj.other.gyzb;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Address {

    @Id
    private String userId;
    private String name;
    private String phone;
    private String address;

}