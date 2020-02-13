package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメントの情報を取得するレポジトリ.
 * 
 * @author oyamadakenji
 *
 */
@Repository
public class CommentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};

//	/**
//	 * すべてのコメントの情報を取得する.
//	 * 
//	 * @param articleId 記事のID
//	 * @return 記事に紐づいたコメントの一覧
//	 */
//	public List<Comment> findByArticleId(Integer articleId) {
//		String sql = "SELECT id, name, content, article_id FROM comments WHERE article_id = :articleId ORDER BY id DESC";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
//		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
//		return commentList;
//	}

	/**
	 * 作成したコメントを挿入する.
	 * 
	 * @param comment 作成したコメントの情報が入ったオブジェクト
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments (name, content, article_id) VALUES (:name,:content,:articleId)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		template.update(sql, param);
	}

	/**
	 * コメントを削除する.
	 * 
	 * @param articleId 記事のID
	 */
	public void deleteByArticleId(Integer articleId) {
		String sql = "DELETE FROM comments WHERE article_id = :articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}
}
