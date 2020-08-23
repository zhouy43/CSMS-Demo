package com.macrosan.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.Jedis;

@Configuration
@PropertySource("classpath:/redis.properties")
public class RedisConfig {
	//SPEL表达式
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private int port;
	
	@Bean
	public Jedis getJedis() {
		return new Jedis(host, port);
	}
}
