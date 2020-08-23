package com.macrosan.common.aop;

import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.macrosan.common.anno.DeleteRedisCache;

import redis.clients.jedis.Jedis;

@Aspect
@Component
public class DeleteRedisCacheAOP {
	@Autowired
			private Jedis jedis;

			@Around("@annotation(deleteRedisCache)")
			public void around(ProceedingJoinPoint jp,DeleteRedisCache deleteRedisCache) {
				try {
					jp.proceed();
					String pattern = deleteRedisCache.preKey()+"::*";
					Set<String> keys = jedis.keys(pattern);
					for (String key : keys) {
						jedis.del(key);
					}
					System.out.println("Redis缓存Key已删除");
				} catch (Throwable e) {
					e.printStackTrace();
		}

	}
	
}
