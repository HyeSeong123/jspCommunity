package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

public class Board {
	public int boardNum;
	public String regDate;
	public String updateDate;
	public String code;
	public String name;

	public Board(Map<String, Object> map) {
		this.boardNum = (int) map.get("boardNum");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.code = (String) map.get("code");
		this.name = (String) map.get("name");

	}

	@Override
	public String toString() {
		return "Board [boardNum=" + boardNum + ", regDate=" + regDate + ", updateDate=" + updateDate + ", code=" + code
				+ ", name=" + name + "]";
	}
}
