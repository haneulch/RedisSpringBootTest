package com.test.recent;

import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class RecentService {
	
	final RedisTemplate<String, Object> redisTemplate;
	
	final ZSetOperations<String, Object> zSet;
	
	RecentService( RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.zSet = this.redisTemplate.opsForZSet();
	}

	public void save(String userId, String keyword) {
		String key = String.format("SEARCH:%s", userId);
		zSet.add(key, keyword, 0);
	}

	public String[] findRecent(String userId) {
		String key = String.format("SEARCH:%s", userId);
		Set<Object> range = zSet.range(key, 0, 5);
		
		String arr[] = range.toArray(new String[range.size()]);
		return arr;
	}
}