package com.test.board;

import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import model.Board;

@Service
public class BoardService {

	final RedisTemplate<String, Object> redisTemplate;
	
	final HashOperations<String, String, Board> hash;
	final SetOperations<String, Object> set;
	
	BoardService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hash = this.redisTemplate.opsForHash();
		this.set = redisTemplate.opsForSet();
	}

	public void save(Board board) {
		hash.put("BOARD", board.getBoardId(), board);
	}
	
	public void like( String boardId, String userId) {
		
	    String key = String.format("BOARD:LIKE:%s", boardId);
	    set.add(key, userId);
	}
	
	public String[] likeList( String boardId) {
		Set<Object> likeSet = set.members( String.format("BOARD:LIKE:%s", boardId));
		
		String arr[] = likeSet.toArray(new String[likeSet.size()]);
		
		return arr;
	}

}
