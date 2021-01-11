package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

public class Member {
	public int memberNum;
	public String regDate;
	public String updateDate;
	public String loginId;
	public String loginPw;
	public String name;
	public String nickname;
	public String email;
	public int authLevel;
	
	public Member(Map<String, Object> memberMap) {
		this.memberNum = (int)memberMap.get("memberNum");
		this.regDate = (String)memberMap.get("regDate");
		this.updateDate = (String)memberMap.get("updateDate");
		this.loginId = (String)memberMap.get("loginId");
		this.loginPw = (String)memberMap.get("loginPw");
		this.name = (String)memberMap.get("name");
		this.nickname = (String)memberMap.get("nickname");
		this.email = (String)memberMap.get("email");
		this.authLevel = (int)memberMap.get("authLevel");
	}
}
