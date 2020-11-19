package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

public class ArticleRecommand {
	public int id;
	public String regDate;
	public String updateDate;
	public int articleId;
	public int memberId;

	public ArticleRecommand(Map<String, Object> articleRecommandMap) {
		this.id = (int) articleRecommandMap.get("id");
		this.regDate = (String) articleRecommandMap.get("regDate");
		this.updateDate = (String) articleRecommandMap.get("updateDate");
		this.articleId = (int) articleRecommandMap.get("articleId");
		this.memberId = (int) articleRecommandMap.get("memberId");
	}

}
