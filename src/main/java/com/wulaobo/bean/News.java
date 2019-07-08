package com.wulaobo.bean;

import java.io.Serializable;

public class News implements Serializable {
	private Integer id;
    private String title;
    private String article;
    private String time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "News{" +
				"id=" + id +
				", title='" + title + '\'' +
				", article='" + article + '\'' +
				", time='" + time + '\'' +
				'}';
	}
}
