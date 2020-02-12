package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@RequestMapping("")
	public String index() {
		return "index";
	}
	
	@RequestMapping("receive-form")
	public String receiveForm(Model model) {
		return "result";
	}
}
