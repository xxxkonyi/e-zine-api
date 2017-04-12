package com.sfkj.other.ezine.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyController {

    @RequestMapping("/.well-known/pki-validation/fileauth.txt")
    public String health() {
        return "2017041100000036lztk37nx9i1a4iw1f7z8ieek2942pqjuy8rke2q4qo85250d";
    }

}
