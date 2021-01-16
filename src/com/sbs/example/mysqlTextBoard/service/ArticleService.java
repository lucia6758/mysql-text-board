package com.sbs.example.mysqlTextBoard.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dao.ArticleDao;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.ArticleRecommand;
import com.sbs.example.mysqlTextBoard.dto.ArticleReply;

public class ArticleService {
	private ArticleDao articleDao;
	private TagService tagService;

	public ArticleService() {
		articleDao = Container.articleDao;
		tagService = Container.tagService;
	}

	public List<Article> getForPrintArticles(int boardId) {
		return articleDao.getForPrintArticles(boardId);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public int modify(int inputedId, String title, String body) {
		Map<String, Object> modifyArgs = new HashMap<>();
		modifyArgs.put("inputedId", inputedId);
		modifyArgs.put("title", title);
		modifyArgs.put("body", body);

		return modify(modifyArgs);
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

	public boolean isRecommandable(int inputedId) {
		ArticleRecommand articleRecommand = articleDao.getRecommand(inputedId);

		if (articleRecommand != null) {
			return false;
		}
		return true;

	}

	public List<ArticleRecommand> getRecommandsById(int inputedId) {
		return articleDao.getRecommandsById(inputedId);
	}

	public int recommand(int inputedId) {
		return articleDao.recommand(inputedId);
	}

	public void cancelRecommand(int inputedId) {
		articleDao.cancelRecommand(inputedId);

	}

	public void countHit(int inputedId) {
		articleDao.countHit(inputedId);

	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public List<Board> getBoards() {
		return articleDao.getBoards();
	}

	public int getNumberOfArticles() {
		return articleDao.getNumerOfArticles();
	}

	public int getNumberOfArticles(int boardId) {
		return articleDao.getNumerOfArticles(boardId);
	}

	public int getPreArticlePage(int boardId, int articleId) {
		return articleDao.getPreArticlePage(boardId, articleId);
	}

	public int getNextArticlePage(int boardId, int id) {
		return articleDao.getNextArticlePage(boardId, id);
	}

	public int modify(Map<String, Object> args) {
		return articleDao.modify(args);

	}

	public List<Article> getForPrintArticles() {
		return articleDao.getForPrintArticles();
	}

	public void updatePageHits() {
		articleDao.updatePageHits();

	}

	public Map<String, List<Article>> getArticleByTagMap() {
		Map<String, List<Article>> map = new LinkedHashMap<>();

		List<String> tagBodies = tagService.getTagBodiesByRelTypeCode("article");

		for (String tagBody : tagBodies) {
			List<Article> articles = getForPrintArticlesByTag(tagBody);

			map.put(tagBody, articles);

		}
		return map;
	}

	private List<Article> getForPrintArticlesByTag(String tagBody) {
		return articleDao.getForPrintArticlesByTag(tagBody);
	}

}
