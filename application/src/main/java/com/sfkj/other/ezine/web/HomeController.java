package com.sfkj.other.ezine.web;

import com.google.common.collect.ImmutableMap;
import com.sfkj.other.ezine.query.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.sfkj.other.ezine.query.CategoryRepository;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private CategoryRepository categoryRepository;

    @SubscribeMapping("/health")
    public Map<String, String> health() {
        return ImmutableMap.of("status", "UP");
    }

    @SubscribeMapping("/categories")
    public Iterable<Category> categories() {
        return categoryRepository.findAll();
    }

}
