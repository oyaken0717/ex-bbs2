package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList",articleList);
		return "index";
	}
	
	@RequestMapping("receive-form")
//	public String receiveForm(String name,String content,Model model) {
	public String receiveForm(ArticleForm form,Model model) {
		//■Formを作る。		
		//		INSERTなのでドメインを使う。
		Article article = new Article(); 
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "result";
	}
}
