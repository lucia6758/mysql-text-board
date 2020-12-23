package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.Util.Util;
import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.dto.Member;

public class BuildService {
	private ArticleService articleService;
	private MemberService memberService;

	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void buildSite() {

		System.out.println("site 폴더생성");
		Util.mkdirs("site");

		Util.copy("site_template/app.css", "site/app.css");

		buildIndexPage();
		buildArticleDetailPages();
		buildArticleList();
		buildAboutPage();
		buildStatisticsPage();

	}

	private void buildAboutPage() {
		System.out.println("site/my 폴더생성");
		Util.mkdirs("site/my");

		Util.copy("site_template/app.css", "site/my/app.css");

		String head = getHeadHtml("about");
		String foot = Util.getFileContents("site_template/foot.html");
		String about = Util.getFileContents("site_template/about.html");

		StringBuilder sb = new StringBuilder();

		sb.append(head);
		sb.append(about);
		sb.append(foot);

		String fileName = "about.html";
		String filePath = "site/my/" + fileName;

		Util.writeFile(filePath, sb.toString());

		System.out.println(filePath + "생성");
	}

	private void buildStatisticsPage() {
		System.out.println("site/stat 폴더생성");
		Util.mkdirs("site/stat");

		Util.copy("site_template/app.css", "site/stat/app.css");

		String head = getHeadHtml("stat");
		String foot = Util.getFileContents("site_template/foot.html");
		String stat = getStatHtml();

		List<Board> boards = articleService.getBoards();

		StringBuilder sb = new StringBuilder();

		sb.append(head);
		sb.append(stat);
		sb.append(foot);

		String fileName = "index.html";
		String filePath = "site/stat/" + fileName;

		Util.writeFile(filePath, sb.toString());

		System.out.println(filePath + "생성");
	}

	private String getStatHtml() {
		String stat = Util.getFileContents("site_template/stat.html");

		List<Board> boards = articleService.getBoards();
		
		StringBuilder statHtml = new StringBuilder();

		statHtml.append("<span>전체 게시물 수: " + articleService.getNumberOfArticles() + "</span>");
		statHtml.append("<ul>");
		for (Board board : boards) {
			statHtml.append("&nbsp<li><span>-" + board.name + " 게시판 게시물 수: " + articleService.getNumberOfArticles(board.boardId)
					+ "</span></li>");
		}
		statHtml.append("</ul>");
		
		stat = stat.replace("${article_statistics}", statHtml);

		return stat;
	}

	private void buildArticleList() {

		List<Board> boards = articleService.getBoards();

		for (Board board : boards) {
			String head = getHeadHtml("article_list_" + board.code);
			String foot = Util.getFileContents("site_template/foot.html");

			List<Article> articles = articleService.getForPrintArticles(board.boardId);

			StringBuilder sb = new StringBuilder();

			if (articles.size() == 0) {
				String emptyList = getEmptyListHtml(board.boardId);
				sb.append(head);
				sb.append(emptyList);
				sb.append(foot);

				String fileName = "list_" + board.code + "_1.html";
				String filePath = "site/article/" + fileName;

				Util.writeFile(filePath, sb.toString());

				System.out.println(filePath + "생성");
			}

			int itemsInAPage = 10;
			int pages;
			int page = 1;
			if (articles.size() % itemsInAPage == 0) {
				pages = articles.size() / itemsInAPage;
			} else {
				pages = articles.size() / itemsInAPage + 1;
			}

			for (page = 1; page <= pages; page++) {
				String list = getListHtml(page, board.boardId);

				sb.append(head);
				sb.append(list);
				sb.append(foot);

				String fileName = "list_" + board.code + "_" + page + ".html";
				String filePath = "site/article/" + fileName;

				Util.writeFile(filePath, sb.toString());

				System.out.println(filePath + "생성");
			}
		}

	}

	private String getEmptyListHtml(int boardId) {
		String list = Util.getFileContents("site_template/list.html");

		StringBuilder emptyListHtml = new StringBuilder();
		emptyListHtml.append("<tr class=\"list\"><td class=\"empty\" colspan=\"6\">게시물이 존재하지 않습니다</td><tr>");

		list = list.replace("${articleList tr_list}", emptyListHtml);

		list = list.replace("${articleList page}", "");
		return list;
	}

	private String getListHtml(int page, int boardId) {
		String list = Util.getFileContents("site_template/list.html");

		List<Article> articles = articleService.getForPrintArticles(boardId);

		int itemsInAPage = 10;
		int totalPage;
		if (articles.size() % itemsInAPage == 0) {
			totalPage = articles.size() / itemsInAPage;
		} else {
			totalPage = articles.size() / itemsInAPage + 1;
		}

		int startPos = itemsInAPage * (page - 1);
		int endPos = itemsInAPage * page - 1;
		if (endPos >= articles.size() - 1) {
			endPos = articles.size() - 1;
		}

		StringBuilder listHtml = new StringBuilder();

		for (int i = startPos; i <= endPos; i++) {
			Article article = articles.get(i);

			listHtml.append("<tr class=\"list\">");
			listHtml.append("<td class=\"td_id\">" + article.id + "</td>");
			listHtml.append("<td class=\"td_title\"><a href=\"article_" + article.id
					+ ".html\" class=\"hover_bottomLine\">" + article.title + "</a></td>");
			listHtml.append("<td class=\"td_writer\">" + article.extra_writer + "</td>");
			listHtml.append("<td class=\"td_regDate\">" + article.regDate + "</td>");
			listHtml.append("<td class=\"td_hit\">" + article.hit + "</td>");
			listHtml.append("<td class=\"td_rec\">0</td>");
			listHtml.append("</tr>");

		}

		list = list.replace("${articleList tr_list}", listHtml);

		StringBuilder pageHtml = new StringBuilder();

		Board board = articleService.getBoardById(boardId);

		int pagesInAList = 10;
		int pageBoundary = page / pagesInAList;
		if (page % pagesInAList == 0) {
			pageBoundary = page / pagesInAList - 1;
		}
		int startPage = 1 + pagesInAList * pageBoundary;
		int endPage = startPage + (pagesInAList - 1);
		if (endPage >= totalPage) {
			endPage = totalPage;
		}

		if (pageBoundary > 0) {
			pageHtml.append("<li class=\"back\"><a href=\"list_" + board.code + "_" + (startPage - 1)
					+ ".html\" class=\"flex flex-ai-c\">&lt 이전</a></li>");
		}

		for (int i = startPage; i <= endPage; i++) {
			if (i == page) {
				pageHtml.append("<li class=\"page_now\"><a class=\"flex flex-ai-c\">" + i + "</a></li>");
			} else {
				pageHtml.append("<li><a href=\"list_" + board.code + "_" + i + ".html\" class=\"flex flex-ai-c\"> " + i
						+ "</a></li>");
			}
		}

		if (endPage == startPage + (pagesInAList - 1)) {
			pageHtml.append("<li class=\"next\"><a href=\"list_" + board.code + "_" + (endPage + 1)
					+ ".html\" class=\"flex flex-ai-c\">다음 &gt</a></li>");
		}

		list = list.replace("${articleList page}", pageHtml);

		return list;
	}

	private void buildIndexPage() {

		Util.copy("site_template/app.css", "site/app.css");

		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("index");
		String foot = Util.getFileContents("site_template/foot.html");
		String mainHtml = getMainListHtml();

		sb.append(head);
		sb.append(mainHtml);
		sb.append(foot);

		String fileName = "index.html";
		String filePath = "site/" + fileName;

		Util.writeFile(filePath, sb.toString());

		System.out.println(filePath + "생성");

	}

	private String getMainListHtml() {
		String mainHtml = Util.getFileContents("site_template/index.html");

		List<Article> articles = articleService.getArticles();

		int numberOfArticles = 5;
		if (articles.size() - 1 < numberOfArticles) {
			numberOfArticles = articles.size() - 1;
		}

		StringBuilder lastestList = new StringBuilder();

		for (int i = 0; i <= numberOfArticles; i++) {
			Article article = articles.get(i);
			Board board = articleService.getBoardById(article.boardId);

			lastestList.append("<li class=\"flex\"><a href=\"../article/article_" + articles.get(i).id
					+ ".html\" class=\"block\">");
			lastestList.append("[ " + board.name + " ] " + article.title);
			lastestList.append("<span>" + article.regDate + "</span></a></li>");
		}

		mainHtml = mainHtml.replace("${main__latestArticle_list}", lastestList);

		return mainHtml;
	}

	private void buildArticleDetailPages() {
		System.out.println("site/article 폴더생성");
		Util.mkdirs("site/article");

		Util.copy("site_template/app.css", "site/article/app.css");
		Util.copy("site_template/app.js", "site/article/app.js");

		List<Article> articles = articleService.getArticles();

		for (Article article : articles) {
			Board board = articleService.getBoardById(article.boardId);

			String head = getHeadHtml("article_list_" + board.code);
			String foot = Util.getFileContents("site_template/foot.html");
			String detail = getDetailHtml(article.id);

			StringBuilder sb = new StringBuilder();

			sb.append(head);
			sb.append(detail);
			sb.append(foot);

			String fileName = "article_" + article.id + ".html";
			String filePath = "site/article/" + fileName;

			Util.writeFile(filePath, sb.toString());

			System.out.println(filePath + "생성");

		}

	}

	private String getDetailHtml(int id) {
		String detail = Util.getFileContents("site_template/detail.html");

		Article article = articleService.getArticleById(id);
		Member member = memberService.getMemberById(article.memberId);
		Board board = articleService.getBoardById(article.boardId);

		StringBuilder detailTopHtml = new StringBuilder();

		detailTopHtml.append("<h2 class=\"detail_title\">" + article.title + "</h2>");
		detailTopHtml.append("<div class=\"detail_info flex\">");
		detailTopHtml.append("<div class=\"detail_id\">번호: " + article.id + "</div>");
		detailTopHtml.append("<div class=\"detail_writer\">작성자: " + member.name + "</div>");
		detailTopHtml.append("<div class=\"detail_regDate\">등록일: " + article.regDate + "</div>");
		detailTopHtml.append("<div class=\"detail_updateDate\">수정일: " + article.updateDate + "</div>");
		detailTopHtml.append("<div class=\"detail_hit\">조회수: " + article.hit + "</div>");
		detailTopHtml.append("<div class=\"detail_rec\">추천수: 0</div>");
		detailTopHtml.append("</div>");

		detail = detail.replace("${article_detail__top}", detailTopHtml);

		StringBuilder detailBodyHtml = new StringBuilder();

		detailBodyHtml.append(article.body);

		detail = detail.replace("${article_detail__body}", detailBodyHtml);

		StringBuilder detailPageHtml = new StringBuilder();

		List<Article> articles = articleService.getForPrintArticles(article.boardId);
		int preArticleId = articleService.getPreArticlePage(article.boardId, article.id);
		int nextArticleId = articleService.getNextArticlePage(article.boardId, article.id);

		if (article.id != articles.get(articles.size() - 1).id) {
			detailPageHtml.append("<div class=\"page-back\"><a href=\"article_" + preArticleId
					+ ".html\" class=\"hover_bottomLine\">&lt 이전글</a></div>");
		} else {
			detailPageHtml.append("<div class=\"page-back\"></div>");
		}
		detailPageHtml.append("<div class=\"page-list\"><a href=\"list_" + board.code
				+ "_1.html\" class=\"hover_bottomLine\">목록</a></div>");
		if (articles.get(0).id == article.id) {
			detailPageHtml.append("<div class=\"page-next\"></div>");
		} else {
			detailPageHtml.append("<div class=\"page-next\"><a href=\"article_" + nextArticleId
					+ ".html\" class=\"hover_bottomLine\">다음글 &gt</a></div>");
		}

		detail = detail.replace("${article_detail__page}", detailPageHtml);

		return detail;
	}

	private String getHeadHtml(String pageName) {
		String head = Util.getFileContents("site_template/head.html");

		StringBuilder boardMenuContentHtml = new StringBuilder();
		List<Board> Boards = articleService.getBoards();

		for (Board board : Boards) {
			boardMenuContentHtml.append("<li>");

			String link = "../article/list_" + board.code + "_1.html";

			boardMenuContentHtml.append("<a href=\"" + link + "\" class=\"block\">");

			String iClass = "fas fa-pencil-alt";

			if (board.code.contains("java")) {
				iClass = "fab fa-java";
			} else if (board.code.contains("sql")) {
				iClass = "fas fa-database";
			} else if (board.code.contains("etc")) {
				iClass = "fas fa-ellipsis-h";
			} else if (board.code.contains("js")) {
				iClass = "fab fa-js-square";
			} else if (board.code.contains("html")) {
				iClass = "far fa-file-code";
			}

			boardMenuContentHtml.append("<i class=\"" + iClass + "\"></i>");

			boardMenuContentHtml.append(" ");

			boardMenuContentHtml.append("<span>");
			boardMenuContentHtml.append(board.name);
			boardMenuContentHtml.append("</span>");

			boardMenuContentHtml.append("</a>");

			boardMenuContentHtml.append("</li>");
		}

		head = head.replace("${menu-bar__menu-1__board-menu-study}", boardMenuContentHtml.toString());

		String titleBarContentHtml = getTitleBarContentByFileName(pageName);

		head = head.replace("${title-bar__content}", titleBarContentHtml);

		return head;
	}

	private String getTitleBarContentByFileName(String pageName) {
		if (pageName.equals("index")) {
			return "<i class=\"fas fa-home\"></i> <span>HOME</span>";
		} else if (pageName.equals("article_list_free")) {
			return "<i class=\"far fa-comment-dots\"></i> <span>TALK</span>";
		} else if (pageName.equals("article_list_notice")) {
			return "<i class=\"far fa-hand-point-right\"></i> <span>ABOUT</span>";
		} else if (pageName.equals("stat")) {
			return "<i class=\"fas fa-chart-pie\"></i> <span>STATISTICS</span>";
		} else if (pageName.equals("article_detail")) {
			return "<i class=\"fas fa-list\"></i> <span>ARTICLES</span>";
		} else if (pageName.equals("article_list_study")) {
			return "<i class=\"fas fa-pencil-alt\"></i> <span>STUDY</span>";
		} else if (pageName.equals("article_list_java")) {
			return "<i class=\"fab fa-java\"></i> <span>JAVA</span>";
		} else if (pageName.equals("article_list_sql")) {
			return "<i class=\"fas fa-database\"></i> <span>SQL</span>";
		} else if (pageName.equals("about")) {
			return "<i class=\"far fa-hand-point-right\"></i> <span>ABOUT</span>";
		} else if (pageName.equals("article_list_js")) {
			return "<i class=\"fab fa-js-square\"></i> <span>JAVA SCRIPT</span>";
		} else if (pageName.equals("article_list_etc")) {
			return "<i class=\"fas fa-ellipsis-h\"></i> <span>ETC</span>";
		} else if (pageName.equals("article_list_htmlCss")) {
			return "<i class=\"far fa-file-code\"></i> <span>HTML/CSS</span>";
		}
		return "";
	}
}
