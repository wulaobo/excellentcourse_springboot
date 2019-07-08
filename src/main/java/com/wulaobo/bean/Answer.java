package com.wulaobo.bean;

import java.io.Serializable;

public class Answer implements Serializable {
	private Integer id;
	private Integer state2;
	private String content;
	private String answeruser;
	private String pubtime2;
	private Integer topicId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public void setAnsweruser(String answeruser) {
		this.answeruser = answeruser;
	}

	public Integer getState2() {
		return state2;
	}

	public void setState2(Integer state2) {
		this.state2 = state2;
	}

	public String getPubtime2() {
		return pubtime2;
	}
	public void setPubtime2(String pubtime2) {
		this.pubtime2 = pubtime2;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnsweruser() {
		return answeruser;
	}


	@Override
	public String toString() {
		return "Answer{" +
				"id=" + id +
				", state2=" + state2 +
				", content='" + content + '\'' +
				", answeruser='" + answeruser + '\'' +
				", pubtime2='" + pubtime2 + '\'' +
				", topicId=" + topicId +
				'}';
	}
}
