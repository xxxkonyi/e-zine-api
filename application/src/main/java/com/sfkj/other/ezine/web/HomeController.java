package com.sfkj.other.ezine.web;

import com.google.common.collect.ImmutableMap;
import com.sfkj.other.ezine.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @SubscribeMapping("/health")
    public Map<String, String> health() {
        return ImmutableMap.of("status", "UP");
    }

    @SubscribeMapping("/advertisements")
    public Iterable<Category> advertisementRepository(String location) {
        return categoryRepository.findAll();
    }

    @SubscribeMapping("/categories")
    public Iterable<Category> categories() {
        return categoryRepository.findAll();
    }

}
