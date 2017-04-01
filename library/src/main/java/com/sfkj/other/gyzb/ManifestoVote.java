package com.sfkj.other.gyzb;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ManifestoVote {

    @Id
    private String id;
    private String openId;
    private String name;
    private String avatar;

    private String userInfo;

}