package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class Article {
	private int num;
	private String regDate;
	private String updateDate;
	private int boardNum;
	private int memberNum;
	private String title;
	private String body;
	private int hitsCount;
	private int like;
	private int unLike;
	
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
		this.setLike((int) map.get("like"));
		this.setUnLike((int) map.get("unLike"));
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
}
