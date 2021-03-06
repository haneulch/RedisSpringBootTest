package com.test.visitor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {
	
	final RedisTemplate<String, Object> redisTemplate;
	
	final ValueOperations<String, Object> value;
	
	final RedisConnectionFactory factory;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	VisitorService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.value = this.redisTemplate.opsForValue();
		this.factory = this.redisTemplate.getConnectionFactory();
	}

	public void save(long userSeq) {
		Date date = new Date();
		
		String today = sdf.format(date);
		
		String key = String.format("VISIT:%s", today);
		
		value.setBit(key, userSeq, true);
	}
	
	public long countVisitor( String date) {
		
		String key = String.format("VISIT:%s", date);
		return factory.getConnection().bitCount(key.getBytes());
	}

}
