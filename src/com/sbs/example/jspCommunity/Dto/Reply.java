package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class Reply {

	private int likeNum;
	private String regDate;
	private String updateDate;
	private String relTypeCode;
	private int relId;
	private int memberNum;
	private String body;

	public Reply(Map<String, Object> map) {
		this.setLikeNum((int) map.get("likeNum"));
		this.setRegDate((String) map.get("regDate"));
		this.setUpdateDate((String) map.get("updateDate"));
		this.setRelTypeCode((String) map.get("relTypeCode"));
		this.setRelId((int) map.get("relId"));
		this.setMemberNum((int) map.get("memberNum"));
		this.setBody((String) map.get("body"));
	}
}
