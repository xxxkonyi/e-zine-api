package com.sfkj.other.gyzb;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Manifesto {

    @Id
    private String id;
    private String manifestoId;
    private String userId;

}