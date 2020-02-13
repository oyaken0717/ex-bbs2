package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

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

//	public List<Article> extractData(ResultSet rs) throws SQLException, DataAccessException {
//		public List<Article> List<Article>(ResultSet rs) throws SQLException, DataAccessException {
	private static final ResultSetExtractor<List<Article>> EXTRADATA = (rs) -> {

		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = null;
		int preId = 0;
		while (rs.next()) {
			//■記事
			if (preId != rs.getInt("id")) {
				Article article = new Article();
				commentList = new ArrayList<>();
				article.setId(rs.getInt("id"));
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				articleList.add(article);
				article.setCommentList(commentList);
			}
			//■コメント
			Comment comment = new Comment();
			comment.setId(rs.getInt("com_id"));
			comment.setName(rs.getString("com_name"));
			comment.setContent(rs.getString("com_content"));
			comment.setArticleId(rs.getInt("article_id"));
			
			
			if (comment.getId()!=0) {
				commentList.add(comment);				
			}

			preId = rs.getInt("id");
		}

		return articleList;
	};

//	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
//		Article article = new Article();
//		article.setId(rs.getInt("id"));
//		article.setName(rs.getString("name"));
//		article.setContent(rs.getString("content"));
//		return article;
//	};

	/**
	 * 記事とコメントの情報をIDの降順で全て取得する.
	 * 
	 * @return 全s記事の情報の詰まったリスト.
	 */
	public List<Article> findAll() {
//		String sql = "SELECT a.id, a.name, a.content, c.id AS com_id, c.name AS com_name, c.content AS com_content, c.article_id AS article_id FROM articles a INNER JOIN comments c ON a.id = c.article_id ORDER BY a.id DESC";
		String sql = "SELECT a.id, a.name, a.content, c.id AS com_id, c.name AS com_name, c.content AS com_content, c.article_id AS article_id FROM articles a LEFT OUTER JOIN comments c ON a.id = c.article_id ORDER BY a.id DESC, c.id DESC";
		List<Article> articleList = template.query(sql, EXTRADATA);
		return articleList;
	}
//	/**
//	 * 記事情報をIDの降順で全て取得する.
//	 * 
//	 * @return 全s記事の情報の詰まったリスト.
//	 */
//	public List<Article> findAll() {
//		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC";
//		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
//		return articleList;
//	}

//	/**
//	 * 記事情報をIDの降順で全て取得する.
//	 * 
//	 * @return 全記事の情報の詰まったリスト.
//	 */
//	public List<Article> findAll() {
//		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC";
//		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
//		return articleList;
//	}

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
