package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//  @RequestMapping : @GetMapping + @PostMapping
	@RequestMapping("/hi")
	public String  hi() {
		return "greetings";    // greetings.mustache 
	}
	
	@GetMapping("/hi2")
	public  String  hi2(Model model) {
		model.addAttribute("name", "로제");		
		return "greetings2";
	}
	
	@GetMapping("/hi3")
	public  String  hi3(Model model) {
		model.addAttribute("name", "로제");		
		return "greetings3";
	}
	
	@GetMapping("/hi4")
	public  String  hi4(Model model) {
		model.addAttribute("name", "짱원영");		
		return "greetings4";
	}
	
	
	
	
}



