package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dao.ArticleDao;
import com.sbs.example.mysqlTextBoard.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void modify(int inputedId, String title, String body) {
		articleDao.modify(inputedId, title, body);
		
	}

	public void remove(int inputedId) {
		articleDao.remove(inputedId);
		
	}

	public int write(int memberId, int boardId, String title, String body) {
		return articleDao.write(memberId, boardId, title, body);
	}

}
