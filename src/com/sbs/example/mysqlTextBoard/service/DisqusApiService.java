package com.sbs.example.mysqlTextBoard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlTextBoard.Util.Util;
import com.sbs.example.mysqlTextBoard.apidto.DisqusApiDataListThread;
import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;

public class DisqusApiService {

	public Map<String, Object> getArticleDate(Article article) {
		String fileName = Container.buildService.getArticleDetailFileName(article.getId());

		String url = "https://disqus.com/api/3.0/forums/listThreads.json";

		DisqusApiDataListThread disqusApiDataListThread = (DisqusApiDataListThread) Util.callApiResponseTo(
				DisqusApiDataListThread.class, url, "api_key=" + Container.config.getDisqusApiKey(),
				"forum=" + Container.config.getDisqusForumName(), "thread:ident=" + fileName);

		if (disqusApiDataListThread == null) {
			return null;
		}

		Map<String, Object> rs = new HashMap<>();
		rs.put("likesCount", disqusApiDataListThread.response.get(0).likes);
		rs.put("replyCount", disqusApiDataListThread.response.get(0).posts);

		return rs;
	}

	public void updateArticleCounts() {
		List<Article> articles = Container.articleService.getForPrintArticles();

		for (Article article : articles) {
			Map<String, Object> disqusArticleData = Container.disqusApiService.getArticleDate(article);

			if (disqusArticleData != null) {
				int likesCount = (int) disqusArticleData.get("likesCount");
				int replyCount = (int) disqusArticleData.get("replyCount");

				Map<String, Object> modifyArgs = new HashMap<>();

				modifyArgs.put("id", article.getId());
				modifyArgs.put("likesCount", likesCount);
				modifyArgs.put("replyCount", replyCount);

				Container.articleService.modify(modifyArgs);

			}
		}

	}

}
