package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int memberId;
	private int boardId;
	private int hit;
	private int likesCount;
	private int replyCount;
	private String extra_writer;

	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = String.valueOf(articleMap.get("regDate"));
		this.updateDate = String.valueOf(articleMap.get("updateDate"));
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.memberId = (int) articleMap.get("memberId");
		this.boardId = (int) articleMap.get("boardId");
		this.hit = (int) articleMap.get("hit");
		this.likesCount = (int) articleMap.get("likesCount");
		this.replyCount = (int) articleMap.get("replyCount");
		if (articleMap.containsKey("extra_writer")) {
			this.extra_writer = (String) articleMap.get("extra_writer");
		}
	}

}
