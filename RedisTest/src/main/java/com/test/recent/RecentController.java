package com.test.recent;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecentController {
	
	final RecentService recentService;
	
	RecentController( RecentService recentService) {
		this.recentService = recentService;
	}
	
	@GetMapping("/search")
	public String search( @RequestParam String userId, @RequestParam String keyword) {
		recentService.save(userId, keyword);
		return "index";
	}
	
	@GetMapping("/recent")
	public String recent( @RequestParam String userId) {
		String recent[] = recentService.findRecent(userId);
		System.out.println(Arrays.toString(recent));
		return "index";
	}
}