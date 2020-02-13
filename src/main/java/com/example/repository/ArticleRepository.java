package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事の情報を取得するレポジトリ.
 * 
 * @author oyamadakenji
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;

	};

	/**
	 * 記事情報をIDの降順で全て取得する.
	 * 
	 * @return 全記事の情報の詰まったリスト.
	 */
	public List<Article> findAll() {
		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}

	/**
	 * 作成した記事を挿入する.
	 * 
	 * @param article 投稿記事内容の入ったオブジェクト
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles (name, content) VALUES (:name, :content)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}

	/**
	 * 記事を削除する.
	 * 
	 * @param id 記事のID
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM articles WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
