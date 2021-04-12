package com.test.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VisitorController {
	
	final VisitorService visitorService;
	
	VisitorController( VisitorService visitorService) {
		this.visitorService = visitorService;
	}
	
	@GetMapping("/visit")
	public String visit( @RequestParam long userSeq) {
		visitorService.save(userSeq);
		return "index";
	}
	
	@GetMapping("/visitCount")
	public String visitCount( @RequestParam String date) {
		System.out.println(visitorService.countVisitor(date));
		return "index";
	}
}