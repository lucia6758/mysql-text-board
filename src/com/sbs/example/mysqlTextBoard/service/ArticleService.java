package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.dao.ArticleDao;
import com.sbs.example.mysqlTextBoard.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = new ArticleDao();
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

}
