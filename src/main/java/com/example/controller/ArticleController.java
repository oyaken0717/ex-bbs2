package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
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
	public String receiveForm(ArticleForm form) {
		Article article = new Article(); 
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/article/toIndex";
	}
//■質問
//	receiveForm()でredirecしてますが、
//	toIndex()の方でModelを引数にしても大丈夫なのかどうか
	@RequestMapping("/toIndex")
	public String toIndex(Model model) {
		return index(model);
	}
}
