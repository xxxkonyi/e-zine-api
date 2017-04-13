package com.sfkj.other.ezine.web.admin;

import com.querydsl.core.types.Predicate;
import com.sfkj.other.ezine.query.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/a/advertisements")
public class AdvertisementController {

    private final AdvertisementRepository advertisementRepository;

    @RequestMapping
    public Iterable<Advertisement> list(@QuerydslPredicate(root = Advertisement.class) Predicate predicate, Sort sort) {
        return advertisementRepository.findAll(predicate, sort);
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.GET)
    public Advertisement get(@PathVariable String identifier) {
        return advertisementRepository.findOne(identifier);
    }

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Advertisement dto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setArticleNumber(dto.getArticleNumber());
        advertisement.setLocation(StringUtils.isEmpty(dto.getLocation()) ? "首页Banner" : dto.getLocation());
        advertisement.setImageUrl(dto.getImageUrl());

        advertisement.setCreatedTime(new Date());
        advertisement.setUpdatedTime(new Date());
        advertisementRepository.save(advertisement);
    }

    @RequestMapping(value = "/{identifier}", method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String identifier) {
        advertisementRepository.delete(identifier);
    }

    @RequestMapping(value = "/{identifier}", method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void change(@PathVariable String identifier,
                       @RequestBody Advertisement dto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(identifier);
        advertisement.setArticleNumber(dto.getArticleNumber());
        advertisement.setLocation(StringUtils.isEmpty(dto.getLocation()) ? "首页Banner" : dto.getLocation());
        advertisement.setImageUrl(dto.getImageUrl());

        advertisement.setUpdatedTime(new Date());
        advertisementRepository.save(advertisement);
    }

}
