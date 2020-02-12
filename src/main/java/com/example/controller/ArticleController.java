package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList",articleList);
		return "index";
	}
	
	@RequestMapping("insert-article")
	public String insertArticle(ArticleForm form) {
		Article article = new Article(); 
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/article/toIndex";
	}
//■質問
//	receiveForm()でredirectしてますが、
//	toIndex()の方でModelを引数にしても大丈夫なのかどうか
	@RequestMapping("/toIndex")
	public String toIndex(Model model) {
		return index(model);
	}
	
	@RequestMapping("insert-comment")
	public String insertComment(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(form.getIntArticleId());
		commentRepository.insert(comment);
		return "redirect:/article/toIndex";
	}
}
