package com.wulaobo.bean;

import java.io.Serializable;
import java.util.List;

public class Topic implements Serializable {
	private Integer id;
	private Integer state;

	private String title;
	private String pubtime;
	private String detail;
	private String edituser;
	private List<Answer> answer;

	public List<Answer> getAnswer() {
		return answer;
	}
	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

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
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getEdituser() {
		return edituser;
	}
	public void setEdituser(String edituser) {
		this.edituser = edituser;
	}


	@Override
	public String toString() {
		return "Topic{" +
				"id=" + id +
				", state=" + state +
				", title='" + title + '\'' +
				", pubtime='" + pubtime + '\'' +
				", detail='" + detail + '\'' +
				", edituser='" + edituser + '\'' +
				", answer=" + answer +
				'}';
	}
}
