package com.ha.reactiveredis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@EnableCaching
@SpringBootApplication
public class ReactiveRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRedisApplication.class, args);
    }

    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        redisTemplate.opsForSet().add("hahaha", "sisisi")
                .subscribe(id -> {
                    log.info("id: " + id);
                });
    }
}
