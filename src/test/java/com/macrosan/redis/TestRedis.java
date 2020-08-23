package com.macrosan.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import redis.clients.jedis.Jedis;

@SpringBootTest
public class TestRedis {
	@Autowired
	private Jedis jedis;
	
	@Test
	public void testRedis() {
		//操作redis
		jedis.set("test", "测试redis1");
		String value = jedis.get("test");
		System.out.println(value);
	}
}
