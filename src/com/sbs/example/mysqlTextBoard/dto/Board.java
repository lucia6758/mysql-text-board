package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

public class Board {
	public int boardId;
	public String name;

	public Board(Map<String, Object> boardMap) {
		this.boardId = (int) boardMap.get("boardId");
		this.name = (String) boardMap.get("name");
	}

}
