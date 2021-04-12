package com.test.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import model.User;

@Controller
public class MainController {
	
	final MainService mainService;
	
	MainController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@PostMapping("/saveUser")
	public String saveUser( @RequestBody User user) {
		System.out.println(user.toString());
		
		mainService.save( user);
		
		return "index";
	}
	
	@GetMapping("/findUser")
	public String findUser( @RequestParam String userId) {
		
		User newUser = mainService.findById( userId);
		
		return "index";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser( @RequestParam String userId) {
		
		mainService.deleteById( userId);
		
		return "index";
	}
}
