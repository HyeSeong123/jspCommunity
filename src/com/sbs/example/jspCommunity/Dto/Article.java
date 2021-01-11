package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

public class Article {
	public int num;
	public String regDate;
	public String updateDate;
	public int boardNum;
	public int memberNum;
	public String title;
	public String body;
	public int hitsCount;

	public String extra__writer;
	public String extra__boardName;
	public String extra__boardCode;

	public Article() {
		
	}
	public Article(Map<String, Object> map) {
		this.num = (int) map.get("num");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.boardNum = (int) map.get("boardNum");
		this.memberNum = (int) map.get("memberNum");
		this.title = (String) map.get("title");
		this.body = (String) map.get("body");
		this.hitsCount = (int) map.get("hitsCount");

		if (map.containsKey("extra__writer")) {
			this.extra__writer = (String) map.get("extra__writer");
		}
		if (map.containsKey("extra__boardName")) {
			this.extra__boardName = (String) map.get("extra__boardName");
		}
		if (map.containsKey("extra__boardCode")) {
			this.extra__boardCode = (String) map.get("extra__boardCode");
		}
	}

	@Override
	public String toString() {
		return "Article [num=" + num + ", regDate=" + regDate + ", updateDate=" + updateDate + ", boardNum=" + boardNum
				+ ", memberNum=" + memberNum + ", title=" + title + ", body=" + body + ", hitsCount=" + hitsCount
				+ ", extra__writer=" + extra__writer + ", extra__boardName=" + extra__boardName + ", extra__boardCode="
				+ extra__boardCode + "]";
	}
	
}
