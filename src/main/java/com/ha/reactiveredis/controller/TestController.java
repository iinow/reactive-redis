package com.ha.reactiveredis.controller;

import com.ha.reactiveredis.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private CacheManager cacheManager;

//    @Cacheable(value = "get", key = "#id")
    @GetMapping(value = "/{id}")
    public Mono<Person> get(@PathVariable(name = "id") long id){
        Person p = new Person(1L, "title...", "content");
        cacheManager.getCache(String.valueOf(id)).put("person", p);
        return Mono.just(cacheManager.getCache(String.valueOf(id)).get("person", Person.class));
    }

//    @CacheEvict(value = "get", key = "#id")
    @DeleteMapping(value = "/{id}")
    public Mono<Void> delete(@PathVariable(name = "id") long id){
        cacheManager.getCache(String.valueOf(id)).evict("person");
        return Mono.empty();
    }
}
