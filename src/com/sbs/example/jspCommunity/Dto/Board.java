package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

public class Board {
	private int boardNum;
	private String regDate;
	private String updateDate;
	private String code;
	private String name;

	public Board(Map<String, Object> map) {
		this.setBoardNum((int) map.get("boardNum"));
		this.setRegDate((String) map.get("regDate"));
		this.setUpdateDate((String) map.get("updateDate"));
		this.setCode((String) map.get("code"));
		this.setName((String) map.get("name"));

	}

	@Override
	public String toString() {
		return "Board [boardNum=" + getBoardNum() + ", regDate=" + getRegDate() + ", updateDate=" + getUpdateDate() + ", code=" + getCode()
				+ ", name=" + getName() + "]";
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
