package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

public class Board {
	public int boardId;
	public String regDate;
	public String updateDate;
	public String code;
	public String name;

	public Board(Map<String, Object> boardMap) {
		this.boardId = (int) boardMap.get("boardId");
		this.regDate = String.valueOf(boardMap.get("regDate"));
		this.updateDate = String.valueOf(boardMap.get("updateDate"));
		this.code = (String) boardMap.get("code");
		this.name = (String) boardMap.get("name");
	}

}
