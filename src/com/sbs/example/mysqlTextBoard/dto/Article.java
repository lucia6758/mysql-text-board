package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

public class Article {
	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;
	public int memberId;
	public int boardId;
	public int hit;
	public String extra_writer;

	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = String.valueOf(articleMap.get("regDate"));
		this.updateDate = String.valueOf(articleMap.get("updateDate"));
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.memberId = (int) articleMap.get("memberId");
		this.boardId = (int) articleMap.get("boardId");
		this.hit = (int) articleMap.get("hit");
		if (articleMap.containsKey("extra_writer")) {
			this.extra_writer = (String) articleMap.get("extra_writer");
		}
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title
				+ ", body=" + body + ", memberId=" + memberId + ", boardId=" + boardId + "]";
	}

}
