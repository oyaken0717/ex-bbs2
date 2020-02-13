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

/**
 * 記事とコメントの処理を制御するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * 掲示板画面に遷移する.
	 * 
	 * @param model リクエストスコープ
	 * @return 一覧画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		for (Article article : articleList) {
			List<Comment> commentList = commentRepository.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}
		model.addAttribute("articleList", articleList);
		return "index";
	}

	/**
	 * 記事を作成する.
	 * 
	 * @param form 記事のリクエストスコープを受け取るフォーム
	 * @return 掲示板画面
	 */
	@RequestMapping("/insert-article")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/article/toIndex";
	}

//	@RequestMapping("/toIndex")
//	public String toIndex(Model model) {
//		return index(model);
//	}
	
	/**
	 * コメントを作成する.
	 * 
	 * @param form 記事のリクエストスコープを受け取るフォーム
	 * @return 掲示板画面
	 */
	@RequestMapping("/insert-comment")
	public String insertComment(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(form.getIntArticleId());
		commentRepository.insert(comment);
		return "redirect:/article/toIndex";
	}
	
	/**
	 * 記事とコメントを削除する.
	 * 
	 * @param articleId 記事のID
	 * @return 掲示板画面
	 */
	@RequestMapping("/delete-article")
	public String deleteArticle(Integer articleId) {
		System.out.println(articleId);
		commentRepository.deleteByArticleId(articleId);
		articleRepository.deleteById(articleId);
		return "redirect:/article/toIndex";
	}
}
