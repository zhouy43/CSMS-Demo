package com.macrosan.common.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.macrosan.common.anno.DoRedisCache;
import com.macrosan.common.utils.ObjectMapperUtil;
import com.macrosan.pojo.Project;

import redis.clients.jedis.Jedis;

@Aspect
@Component
public class DoRedisCacheAOP {
	@Autowired
	private Jedis jedis;

	/*
	 * 1.利用切入点表达式拦截@RedisCache注解,同时获取 RedisCache注解对象 
	 * 2.自定义key 
	 * 3.利用key查询缓存
	 * 缓存中有数据则直接返回; 缓存中没有数据,查询数据库后,存入redis,再返回
	 * 
	 * 通过反射获取注解中的相关内容
	 */
	@Around("@annotation(doRedisCache)")
	public Object around(ProceedingJoinPoint jp, DoRedisCache doRedisCache) {
		Object result = null;
		try {
			// 动态拼接key,key前缀::用户传入的参数
			Object[] args = jp.getArgs();
			String key = doRedisCache.preKey() + "::" + Arrays.toString(args);
			// 根据key查询redis
			if (!jedis.exists(key)) {
				// redis中不存在对应的key,则查询数据库
				System.out.println("查询数据库");
				result = jp.proceed();
				//将结果转换为JSON
				String jsonResult = ObjectMapperUtil.toJSON(result);
				//获取超时时间
				int expireTime = doRedisCache.expire();
				//将返回值存入redis中
				if(expireTime > 0) jedis.setex(key, expireTime, jsonResult);
				else jedis.set(key, jsonResult);
			} else {
				System.out.println("查询Redis");
				//通过反射获取目标方法的返回值类型
				MethodSignature signature = (MethodSignature)jp.getSignature();	//获取目标方法签名(方法名+参数类型)
				Class<?> returnType = signature.getReturnType(); 					//获取目标方法的返回值类型
				//如果redis中有缓存,那么将缓存转换为对象后返回
				String redis = jedis.get(key);		//假如进行精确查询,只返回一个项目,转换将出现问题
				//对查询获得的单个项目进行处理
				if(!redis.startsWith("[")) {
					Project singleObj = (Project) ObjectMapperUtil.toObject(redis, Project.class);
					List<Project> list2Convert = new ArrayList<>();
					list2Convert.add(singleObj);
					String singleObj2List2Json = ObjectMapperUtil.toJSON(list2Convert);
					result = ObjectMapperUtil.toObject(singleObj2List2Json, returnType);
				}else {
					result = ObjectMapperUtil.toObject(redis, returnType);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
}
