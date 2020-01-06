package com.ha.reactiveredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RedisContextConfiguration {

    private AppConfig app;

    public RedisContextConfiguration(
            AppConfig app){
        this.app = app;
    }

    @Bean
	public LettuceConnectionFactory reactiveRedisConnectionFactory(){
		RedisStandaloneConfiguration standConfig = new RedisStandaloneConfiguration();
		standConfig.setHostName(app.getRedis().getHost());
		standConfig.setPort(app.getRedis().getPort());
		standConfig.setDatabase(app.getRedis().getDatabase());
		return new LettuceConnectionFactory();
	}

	@Bean
	public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(){
		return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory(), RedisSerializationContext.string());
	}
}
