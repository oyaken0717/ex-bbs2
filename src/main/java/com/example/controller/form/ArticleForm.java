package com.example.controller.form;

public class ArticleForm {
	
	private String id;
	private String name;
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
