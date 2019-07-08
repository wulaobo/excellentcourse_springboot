package com.wulaobo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Video implements Serializable {
	private Integer id;
	private String type;
	private String size;
	private String path;
	private String title;
	private Timestamp uploadTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "Video{" +
				"id=" + id +
				", type='" + type + '\'' +
				", size='" + size + '\'' +
				", path='" + path + '\'' +
				", title='" + title + '\'' +
				", uploadTime=" + uploadTime +
				'}';
	}
}
