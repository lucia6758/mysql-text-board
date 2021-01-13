package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Board {
	private int boardId;
	private String regDate;
	private String updateDate;
	private String code;
	private String name;

	public Board(Map<String, Object> boardMap) {
		this.boardId = (int) boardMap.get("boardId");
		this.regDate = String.valueOf(boardMap.get("regDate"));
		this.updateDate = String.valueOf(boardMap.get("updateDate"));
		this.code = (String) boardMap.get("code");
		this.name = (String) boardMap.get("name");
	}

}
