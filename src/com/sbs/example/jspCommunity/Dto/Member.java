package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

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

	public int getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(int authLevel) {
		this.authLevel = authLevel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
}
