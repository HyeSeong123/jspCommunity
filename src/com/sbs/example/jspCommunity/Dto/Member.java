package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class Member {
	private int memberNum;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String email;
	private int authLevel;
	
	public Member(Map<String, Object> memberMap) {
		this.setMemberNum((int)memberMap.get("memberNum"));
		this.setRegDate((String)memberMap.get("regDate"));
		this.setUpdateDate((String)memberMap.get("updateDate"));
		this.setLoginId((String)memberMap.get("loginId"));
		this.setLoginPw((String)memberMap.get("loginPw"));
		this.setName((String)memberMap.get("name"));
		this.setNickname((String)memberMap.get("nickname"));
		this.setEmail((String)memberMap.get("email"));
		this.setAuthLevel((int)memberMap.get("authLevel"));
	}
}
