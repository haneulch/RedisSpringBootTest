package com.test.config;

import org.springframework.data.redis.core.HashOperations;
import java.lang.reflect.Field;

public class RedisHashModel {

	public static void setModel(HashOperations<String, String, Object> hash, String key, Object obj) {
		try {
			for (Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(obj);
				
				hash.put(key, field.getName(), value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
