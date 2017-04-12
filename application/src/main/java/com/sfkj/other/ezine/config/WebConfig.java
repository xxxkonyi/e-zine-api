package com.sfkj.other.ezine.config;

import com.sfkj.other.ezine.web.filter.SimpleCORSFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class WebConfig {

    @Bean
    public SimpleCORSFilter corsFilter() {

        SimpleCORSFilter corsFilter = new SimpleCORSFilter();
        corsFilter.setCorsAllowCredentials("true");
        corsFilter.setCorsAllowMethods("GET, POST, PUT, PATCH, DELETE, OPTIONS");
        corsFilter.setCorsAllowHeaders("content-type, x-requested-with, origin, accept, authorization, username, password, x-app-type, x-app-version, x-auth-token, x-csrf-token, soapaction");
        corsFilter.setCorsExposeHeaders("content-type, cookie, x-requested-with, origin, accept, username, password, x-app-type, x-app-version, x-auth-token, x-csrf-token, soapaction");
        corsFilter.setCorsMaxAge("3600");

        return corsFilter;
    }

}