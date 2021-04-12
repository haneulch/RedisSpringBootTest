package com.test.main;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import model.User;

@Service
public class MainService {

	final RedisTemplate<String, Object> redisTemplate;
	
	final HashOperations<String, String, User> hash;
	
	MainService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hash = this.redisTemplate.opsForHash();
	}

	public void save( User user) {
		hash.put("USER", user.getUserId(), user);
	}

	public User findById( String userId) {
		return (User) hash.get("USER", userId);
	}

	public void deleteById(String userId) {
		hash.delete("USER", userId);
	}
}