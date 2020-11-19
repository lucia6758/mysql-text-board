package com.sbs.example.mysqlTextBoard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.ArticleRecommand;
import com.sbs.example.mysqlTextBoard.dto.ArticleReply;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlTextBoard.mysqlutil.SecSql;

public class ArticleDao {

	public List<Article> getForPrintArticles(int selectedBoardId) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS `extra_writer`");
		sql.append("FROM article AS A");
		sql.append("inner join `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.boardId = ?", selectedBoardId);
		sql.append("ORDER BY A.id DESC");

		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}

		return articles;
	}

	public Article getArticleById(int inputedId) {

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", inputedId);

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		if (articleMap.isEmpty()) {
			return null;
		}

		return new Article(articleMap);
	}

	public void modify(int inputedId, String title, String body) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET title = ?", title);
		sql.append(", body = ?", body);
		sql.append(", updateDate = NOW()");
		sql.append("WHERE id = ?", inputedId);

		MysqlUtil.update(sql);

	}

	public void delete(int inputedId) {
		SecSql sql = new SecSql();
		sql.append("DELETE");
		sql.append("FROM article");
		sql.append("WHERE id = ?", inputedId);

		MysqlUtil.delete(sql);
	}

	public int write(int memberId, int boardId, String title, String body) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", boardId = ?", boardId);
		sql.append(", memberId = ?", memberId);
		sql.append(", title = ?", title);
		sql.append(", body = ?", body);

		return MysqlUtil.insert(sql);
	}

	public int makeBoard(String name) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO board");
		sql.append("SET name = ?", name);

		return MysqlUtil.insert(sql);
	}

	public Board getBoardById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM board");
		sql.append("WHERE boardId = ?", id);

		Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

		if (boardMap.isEmpty()) {
			return null;
		}

		return new Board(boardMap);
	}

	public int writeReply(int memberId, int articleId, String reply) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO articleReply");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", memberId = ?", memberId);
		sql.append(", articleId = ?", articleId);
		sql.append(", reply = ?", reply);

		return MysqlUtil.insert(sql);
	}

	public List<ArticleReply> getRepliesByArticleId(int inputedId) {
		List<ArticleReply> articleReplies = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM articleReply");
		sql.append("WHERE articleId = ?", inputedId);

		List<Map<String, Object>> articleReplyMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleReplyMap : articleReplyMapList) {
			articleReplies.add(new ArticleReply(articleReplyMap));
		}

		return articleReplies;

	}

	public ArticleReply getReplyById(int inputedId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM articleReply");
		sql.append("WHERE id = ?", inputedId);

		Map<String, Object> articleReplyMap = MysqlUtil.selectRow(sql);

		if (articleReplyMap.isEmpty()) {
			return null;
		}

		return new ArticleReply(articleReplyMap);
	}

	public void modifyReply(int inputedId, String reply) {
		SecSql sql = new SecSql();
		sql.append("UPDATE articleReply");
		sql.append("SET reply = ?", reply);
		sql.append(", updateDate = NOW()");
		sql.append("WHERE id = ?", inputedId);

		MysqlUtil.update(sql);

	}

	public void deleteReply(int inputedId) {
		SecSql sql = new SecSql();
		sql.append("DELETE");
		sql.append("FROM articleReply");
		sql.append("WHERE id = ?", inputedId);

		MysqlUtil.delete(sql);

	}

	public List<ArticleRecommand> getRecommandsById(int inputedId) {
		List<ArticleRecommand> articleRecommands = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM articleRecommand");
		sql.append("WHERE articleId = ?", inputedId);

		List<Map<String, Object>> articleRecommandMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleRecommandMap : articleRecommandMapList) {
			articleRecommands.add(new ArticleRecommand(articleRecommandMap));
		}

		return articleRecommands;
	}

	public ArticleRecommand getRecommand(int inputedId) {
		List<ArticleRecommand> articleRecommands = getRecommandsById(inputedId);

		for (ArticleRecommand articleRecommand : articleRecommands) {
			if (articleRecommand.memberId == Container.session.loginedMemberId) {
				return articleRecommand;
			}
		}
		return null;
	}

	public int recommand(int inputedId) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO articleRecommand");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", memberId = ?", Container.session.loginedMemberId);
		sql.append(", articleId = ?", inputedId);

		return MysqlUtil.insert(sql);
	}

	public void cancelRecommand(int inputedId) {

		SecSql sql = new SecSql();
		sql.append("DELETE");
		sql.append("FROM articleRecommand");
		sql.append("WHERE articleId = ?", inputedId);

		MysqlUtil.delete(sql);

	}

	public void countHit(int inputedId) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET hit = hit+1");
		sql.append("WHERE id = ?", inputedId);

		MysqlUtil.update(sql);

	}
}
