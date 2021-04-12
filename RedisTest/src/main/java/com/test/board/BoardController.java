package com.test.board;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import model.Board;

@Controller
public class BoardController {
	
	final BoardService boardService;
	
	BoardController( BoardService boardService) {
		this.boardService = boardService;
	}
	
	@PostMapping("/saveBoard")
	public void saveBoard( @RequestBody Board board) {
		boardService.save(board);
	}
	
	@GetMapping("/like")
	public String likeBoard( 
			@RequestParam String boardId
			, @RequestParam String userId) {
		
		boardService.like(boardId, userId);
		return "index";
	}
	
	@GetMapping("/likeList")
	public String likeList( @RequestParam String boardId) {
		
		String like[] = boardService.likeList(boardId);
		
		System.out.println(Arrays.toString(like));
		return "index";
	}
}