package com.sbs.example.jspCommunity.Dto;

import java.util.LinkedHashMap;
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

	private Map<String, Object> extra;
	
	private String extra__writer;
	private String extra__boardName;
	private String extra__boardCode;
	private int extra__likePoint;
	private int extra__likeOnlyPoint;
	private int extra__disLikeOnlyPoint;

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
			this.extra__writer = (String) map.get("extra__writer");
		}

		if (map.containsKey("extra__boardName")) {
			this.extra__boardName = (String) map.get("extra__boardName");
		}

		if (map.containsKey("extra__boardCode")) {
			this.extra__boardCode = (String) map.get("extra__boardCode");
		}

		if (map.containsKey("extra__likePoint")) {
			this.extra__likePoint = (int) map.get("extra__likePoint");
		}

		if (map.containsKey("extra__likeOnlyPoint")) {
			this.extra__likeOnlyPoint = (int) map.get("extra__likeOnlyPoint");
		}

		if (map.containsKey("extra__disLikeOnlyPoint")) {
			this.extra__disLikeOnlyPoint = (int) map.get("extra__disLikeOnlyPoint");
		}

		this.extra = new LinkedHashMap<>();
	}
}
