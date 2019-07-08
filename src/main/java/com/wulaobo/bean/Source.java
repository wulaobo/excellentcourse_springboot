package com.wulaobo.bean;

import java.io.Serializable;

public class Source implements Serializable {
	private Integer id;
	private String filename;
	private String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	private String pubtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}


	@Override
	public String toString() {
		return "Source{" +
				"id=" + id +
				", filename='" + filename + '\'' +
				", filepath='" + filepath + '\'' +
				", pubtime='" + pubtime + '\'' +
				'}';
	}
}
