package com.sbs.example.jspCommunity.Dto;

import java.util.Map;

import lombok.Data;

@Data
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
}
