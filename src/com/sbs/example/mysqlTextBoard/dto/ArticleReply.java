package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

public class ArticleReply {
	public int id;
	public int memberId;
	public int articleId;
	public String regDate;
	public String updateDate;
	public String reply;

	public ArticleReply(Map<String, Object> articleReplyMap) {
		this.id = (int) articleReplyMap.get("id");
		this.memberId = (int) articleReplyMap.get("memberId");
		this.articleId = (int) articleReplyMap.get("articleId");
		this.regDate = (String) articleReplyMap.get("regDate");
		this.updateDate = (String) articleReplyMap.get("updateDate");
		this.reply = (String) articleReplyMap.get("reply");
	}

}
