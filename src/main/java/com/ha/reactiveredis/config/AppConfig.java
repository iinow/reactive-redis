package com.ha.reactiveredis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private AppRedisConfig redis;

    @Data
    public static class AppRedisConfig {
        private String host;
        private int port;
        private int database;
    }
}

