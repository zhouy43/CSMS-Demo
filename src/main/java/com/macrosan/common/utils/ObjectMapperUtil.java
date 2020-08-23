package com.macrosan.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * ObjectMapper是com.fasterxml.**jackson**.databind包下的程序, 该对象是当下数据转化的主流API. 
 * 1.将对象转换为JSON
 * 2.将JSON转换为对象
 */
public class ObjectMapperUtil {
	public static final ObjectMapper MAPPER = new ObjectMapper();
	
	//将对象转换为JSON
	public static String toJSON(Object obj) {
		try {
			return MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			//将检查异常转换为运行时异常
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//将JSON串转换为对象
	public static <T> Object toObject(String json,Class<T> target) {
		try {
			return MAPPER.readValue(json, target);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
