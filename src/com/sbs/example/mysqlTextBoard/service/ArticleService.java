package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dao.ArticleDao;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.ArticleReply;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getArticles(int boardId) {
		return articleDao.getArticles(boardId);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void modify(int inputedId, String title, String body) {
		articleDao.modify(inputedId, title, body);

	}

	public void delete(int inputedId) {
		articleDao.delete(inputedId);

	}

	public int write(int memberId, int boardId, String title, String body) {
		return articleDao.write(memberId, boardId, title, body);
	}

	public int makeBoard(String name) {
		return articleDao.makeBoard(name);
	}

	public Board getBoardById(int boardId) {
		return articleDao.getBoardById(boardId);
	}

	public int writeReply(int memberId, int articleId, String reply) {
		return articleDao.writeReply(memberId, articleId, reply);
	}

	public List<ArticleReply> getRepliesByArticleId(int inputedId) {
		return articleDao.getRepliesByArticleId(inputedId);
	}

	public ArticleReply getReplyById(int inputedId) {
		return articleDao.getReplyById(inputedId);
	}

	public void modifyReply(int inputedId, String reply) {
		articleDao.modifyReply(inputedId, reply);

	}

	public void deleteReply(int inputedId) {
		articleDao.deleteReply(inputedId);
	}

}
