package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

public class Article {
	private int num;
	private String regDate;
	private String updateDate;
	private int boardNum;
	private int memberNum;
	private String title;
	private String body;
	private int hitsCount;

	private String extra__writer;
	private String extra__boardName;
	private String extra__boardCode;

	public Article(Map<String, Object> map) {
		this.setNum((int) map.get("num"));
		this.setRegDate((String) map.get("regDate"));
		this.setUpdateDate((String) map.get("updateDate"));
		this.setBoardNum((int) map.get("boardNum"));
		this.setMemberNum((int) map.get("memberNum"));
		this.setTitle((String) map.get("title"));
		this.setBody((String) map.get("body"));
		this.setHitsCount((int) map.get("hitsCount"));

		if (map.containsKey("extra__writer")) {
			this.setExtra__writer((String) map.get("extra__writer"));
		}
		if (map.containsKey("extra__boardName")) {
			this.setExtra__boardName((String) map.get("extra__boardName"));
		}
		if (map.containsKey("extra__boardCode")) {
			this.setExtra__boardCode((String) map.get("extra__boardCode"));
		}
	}

	@Override
	public String toString() {
		return "Article [num=" + getNum() + ", regDate=" + getRegDate() + ", updateDate=" + getUpdateDate() + ", boardNum=" + getBoardNum()
				+ ", memberNum=" + getMemberNum() + ", title=" + getTitle() + ", body=" + getBody() + ", hitsCount=" + getHitsCount()
				+ ", extra__writer=" + getExtra__writer() + ", extra__boardName=" + getExtra__boardName() + ", extra__boardCode="
				+ getExtra__boardCode() + "]";
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getHitsCount() {
		return hitsCount;
	}

	public void setHitsCount(int hitsCount) {
		this.hitsCount = hitsCount;
	}

	public String getExtra__writer() {
		return extra__writer;
	}

	public void setExtra__writer(String extra__writer) {
		this.extra__writer = extra__writer;
	}

	public String getExtra__boardName() {
		return extra__boardName;
	}

	public void setExtra__boardName(String extra__boardName) {
		this.extra__boardName = extra__boardName;
	}

	public String getExtra__boardCode() {
		return extra__boardCode;
	}

	public void setExtra__boardCode(String extra__boardCode) {
		this.extra__boardCode = extra__boardCode;
	}

}
