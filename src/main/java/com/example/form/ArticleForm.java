package com.example.form;

/**
 * 記事の投稿内容に関するリクエストパラメータを受け取るフォーム.
 * 
 * @author oyamadakenji
 *
 */
public class ArticleForm {

	/** 記事のID */
	private String id;
	/** 投稿者名 */
	private String name;
	/** 投稿内容 */
	private String content;

	public Integer getIntId() {
		return Integer.parseInt(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "ArticleForm [id=" + id + ", name=" + name + ", content=" + content + "]";
	}

}
