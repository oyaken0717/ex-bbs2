package com.example.form;

/**
 * コメントの投稿内容に関するリクエストパラメータを受け取るフォーム.
 * 
 * @author oyamadakenji
 *
 */
public class CommentForm {

	private String articleId;
	private String name;
	private String content;

	public Integer getIntArticleId() {
		return Integer.parseInt(articleId);
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}
}
