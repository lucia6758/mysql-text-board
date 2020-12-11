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
		Util.rmdir("site");
		Util.mkdirs("site");

		Util.copy("site_template/app.css", "site/app.css");

		buildIndexPage();
		buildArticleDetailPages();
		buildArticleList();
		buildArticleList1();
		buildArticleList2();
		buildStatisticsPage();

	}

	private void buildStatisticsPage() {

		Util.copy("site_template/app.css", "site/stat/app.css");

		System.out.println("site/stat 폴더생성");
		Util.mkdirs("site/stat");

		String head = getHeadHtml("stat");
		String foot = Util.getFileContents("site_template/foot.html");

		StringBuilder sb = new StringBuilder();

		sb.append(head);
		
		sb.append("<section class=\"stat con-min-width\">");
		sb.append("<div class=\"con\">");

		sb.append("회원 수: ");
		sb.append("전체 게시물 수: ");
		sb.append("각 게시판별 게시물 수: ");
		sb.append("전체 게시물 조회 수: ");
		sb.append("각 게시판별 게시물 조회 수: ");
		
		sb.append("</div>");
		sb.append("</section>");

		sb.append(foot);

		String fileName = "index.html";
		String filePath = "site/stat/" + fileName;

		Util.writeFile(filePath, sb.toString());

		System.out.println(filePath + "생성");
	}

	private void buildArticleList2() {
		String head = getHeadHtml("article_list_free");
		String foot = Util.getFileContents("site_template/foot.html");

		List<Article> articles = articleService.getForPrintArticles(2);

		int itemsInAPage = 10;
		int pages;
		int page = 1;
		if (articles.size() % itemsInAPage == 0) {
			pages = articles.size() / itemsInAPage;
		} else {
			pages = articles.size() / itemsInAPage + 1;
		}

		for (page = 1; page <= pages; page++) {

			StringBuilder sb = new StringBuilder();

			sb.append(head);

			int startPos = itemsInAPage * (page - 1);
			int endPos = itemsInAPage * page - 1;
			if (endPos >= articles.size() - 1) {
				endPos = articles.size() - 1;
			}
			sb.append("<section class=\"article_list con-min-width\">");
			sb.append("<div class=\"con\">");

			sb.append("번호 / 날짜 / 갱신날짜 / 작성자 / 제목");

			for (int i = startPos; i <= endPos; i++) {
				Article article = articles.get(i);

				sb.append("<div>");
				sb.append(article.id + " / ");
				sb.append(article.regDate + " / ");
				sb.append(article.updateDate + " / ");
				sb.append(article.memberId + " / ");
				sb.append(article.title);
				sb.append("</div>");

			}
			sb.append("</div>");
			sb.append("</section>");

			sb.append(foot);

			String fileName = "articlelist_free_" + page + ".html";
			String filePath = "site/article/" + fileName;

			Util.writeFile(filePath, sb.toString());

			System.out.println(filePath + "생성");
		}

	}

	private void buildArticleList1() {

		String head = getHeadHtml("article_list_notice");
		String foot = Util.getFileContents("site_template/foot.html");

		List<Article> articles = articleService.getForPrintArticles(1);

		int itemsInAPage = 10;
		int pages;
		int page = 1;
		if (articles.size() % itemsInAPage == 0) {
			pages = articles.size() / itemsInAPage;
		} else {
			pages = articles.size() / itemsInAPage + 1;
		}

		for (page = 1; page <= pages; page++) {
			StringBuilder sb = new StringBuilder();

			sb.append(head);

			int startPos = itemsInAPage * (page - 1);
			int endPos = itemsInAPage * page - 1;
			if (endPos >= articles.size() - 1) {
				endPos = articles.size() - 1;
			}

			sb.append("<section class=\"article_list con-min-width\">");
			sb.append("<div class=\"con\">");

			sb.append("번호 / 날짜 / 갱신날짜 / 작성자 / 제목");
			for (int i = startPos; i <= endPos; i++) {
				Article article = articles.get(i);

				sb.append("<div>");
				sb.append(article.id + " / ");
				sb.append(article.regDate + " / ");
				sb.append(article.updateDate + " / ");
				sb.append(article.memberId + " / ");
				sb.append(article.title);
				sb.append("</div>");

			}
			sb.append("</div>");
			sb.append("</section>");

			sb.append(foot);

			String fileName = "articlelist_notice_" + page + ".html";
			String filePath = "site/article/" + fileName;

			Util.writeFile(filePath, sb.toString());

			System.out.println(filePath + "생성");
		}

	}

	private void buildArticleList() {
		List<Article> articles = articleService.getArticles();

		String head = getHeadHtml("article_list");
		String foot = Util.getFileContents("site_template/foot.html");

		int itemsInAPage = 10;
		int pages;
		int page = 1;
		if (articles.size() % itemsInAPage == 0) {
			pages = articles.size() / itemsInAPage;
		} else {
			pages = articles.size() / itemsInAPage + 1;
		}

		for (page = 1; page <= pages; page++) {
			StringBuilder sb = new StringBuilder();

			sb.append(head);

			int startPos = itemsInAPage * (page - 1);
			int endPos = itemsInAPage * page - 1;
			if (endPos >= articles.size() - 1) {
				endPos = articles.size() - 1;
			}

			sb.append("<section class=\"article_list con-min-width\">");
			sb.append("<div class=\"con\">");
			sb.append("번호 / 날짜 / 갱신날짜 / 작성자 / 제목");

			for (int i = startPos; i <= endPos; i++) {
				Article article = articles.get(i);

				sb.append("<div>");
				sb.append(article.id + " / ");
				sb.append(article.regDate + " / ");
				sb.append(article.updateDate + " / ");
				sb.append(article.memberId + " / ");
				sb.append(article.title);
				sb.append("</div>");
			}
			sb.append("</div>");
			sb.append("</section>");

			sb.append(foot);

			String fileName = "articlelist_" + page + ".html";
			String filePath = "site/article/" + fileName;

			Util.writeFile(filePath, sb.toString());

			System.out.println(filePath + "생성");
		}

	}

	private void buildIndexPage() {
		System.out.println("site/home 폴더생성");
		Util.mkdirs("site/home");

		Util.copy("site_template/app.css", "site/home/app.css");

		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("index");
		String foot = Util.getFileContents("site_template/foot.html");

		String mainHtml = Util.getFileContents("site_template/index.html");

		sb.append(head);
		sb.append(mainHtml);
		sb.append(foot);

		String fileName = "index.html";
		String filePath = "site/home/" + fileName;

		Util.writeFile(filePath, sb.toString());

		System.out.println(filePath + "생성");

	}

	private void buildArticleDetailPages() {
		System.out.println("site/article 폴더생성");
		Util.mkdirs("site/article");

		Util.copy("site_template/app.css", "site/article/app.css");

		List<Article> articles = articleService.getArticles();

		String head = getHeadHtml("article_detail");
		String foot = Util.getFileContents("site_template/foot.html");

		for (Article article : articles) {
			StringBuilder sb = new StringBuilder();

			sb.append(head);

			sb.append("<section class=\"article_detail con-min-width\">");
			sb.append("<div class=\"con\">");
			sb.append("번호 : " + article.id + "<br>");
			sb.append("작성날짜 : " + article.regDate + "<br>");
			sb.append("갱신날짜 : " + article.updateDate + "<br>");
			sb.append("제목 : " + article.title + "<br>");
			sb.append("내용 : " + article.body + "<br>");

			if (article.id - 1 > 0) {
				sb.append("<a href=\"article" + (article.id - 1) + ".html\">이전글</a><br>");
			}
			if (articles.size() > article.id) {
				sb.append("<a href=\"article" + (article.id + 1) + ".html\">다음글</a><br>");
			}

			sb.append("</div>");
			sb.append("</section>");

			sb.append(foot);

			String fileName = "article" + article.id + ".html";
			String filePath = "site/article/" + fileName;

			Util.writeFile(filePath, sb.toString());

			System.out.println(filePath + "생성");

		}

	}

	private String getHeadHtml(String pageName) {
		String head = Util.getFileContents("site_template/head.html");

		StringBuilder boardMenuContentHtml = new StringBuilder();
		List<Board> Boards = articleService.getBoards();

		for (Board board : Boards) {
			boardMenuContentHtml.append("<li>");

			String link = board.code + "-list-1.html";

			boardMenuContentHtml.append("<a href=\"" + link + "\" class=\"block\">");

			String iClass = "fas fa-clipboard-list";

			if (board.code.contains("notice")) {
				iClass = "fas fa-exclamation-circle";
			} else if (board.code.contains("free")) {
				iClass = "far fa-comment-dots";
			}

			boardMenuContentHtml.append("<i class=\"" + iClass + "\"></i>");

			boardMenuContentHtml.append(" ");

			boardMenuContentHtml.append("<span>");
			boardMenuContentHtml.append(board.code);
			boardMenuContentHtml.append("</span>");

			boardMenuContentHtml.append("</a>");

			boardMenuContentHtml.append("</li>");
		}

		head = head.replace("${menu-bar__menu-1__board-menu-content}", boardMenuContentHtml.toString());

		String titleBarContentHtml = getTitleBarContentByFileName(pageName);

		head = head.replace("${title-bar__content}", titleBarContentHtml);

		return head;
	}

	private String getTitleBarContentByFileName(String pageName) {
		if (pageName.equals("index")) {
			return "<i class=\"fas fa-home\"></i> <span>HOME</span>";
		} else if (pageName.equals("article_list_free")) {
			return "<i class=\"far fa-comment-dots\"></i> <span>FREE</span>";
		} else if (pageName.equals("article_list_notice")) {
			return "<i class=\"fas fa-exclamation-circle\"></i> <span>Notice</span>";
		} else if (pageName.equals("stat")) {
			return "<i class=\"fas fa-chart-pie\"></i> <span>STATISTICS</span>";
		} else if (pageName.equals("article_detail")) {
			return "<i class=\"fas fa-list\"></i> <span>ARTICLES</span>";
		}
		return "";
	}

}
