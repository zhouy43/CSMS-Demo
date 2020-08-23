package com.macrosan.common.aop;

import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.macrosan.common.anno.UpdateRedisCache;
import com.macrosan.common.utils.ObjectMapperUtil;
import com.macrosan.pojo.Project;

import redis.clients.jedis.Jedis;

@Aspect
@Component
public class UpdateRedisCacheAOP {
	@Autowired
	private Jedis jedis;
	
	@Around("@annotation(updateRedisCache)")
	public Object around(ProceedingJoinPoint jp,UpdateRedisCache updateRedisCache) {
		try {
			//动态拼接key
			Object[] args = jp.getArgs();		//获取到的是Project对象
			Project project = (Project)args[0];
			String proName = project.getName();
			String pattern = updateRedisCache.preKey() + "::*";
			//更新数据库,并取得更新后的项目信息
			Project result = (Project)jp.proceed();
			//删除redis中的缓存,查询新数据后存入redis
			int expireTime = updateRedisCache.expire();
			if(expireTime > 0 ) {
				System.out.println("更新Redis缓存");
				Set<String> keys = jedis.keys(pattern);
				for (String key : keys) {
					jedis.del(key);
				}
				String key = updateRedisCache.preKey() + "::["+proName+"]";
				String jsonResult = ObjectMapperUtil.toJSON(result);
				jedis.setex(key, expireTime, jsonResult);		//更新redis,设置超时时间
				return result;
			}else {
				System.out.println("更新Redis缓存");
				Set<String> keys = jedis.keys(pattern);
				for (String key : keys) {
					jedis.del(key);
				}
				String key = updateRedisCache.preKey() + "::["+proName+"]";
				String jsonResult = ObjectMapperUtil.toJSON(result);
				jedis.set(key, jsonResult);					//更新redis,不设置超时时间
				return result;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
}
