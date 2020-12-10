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
		// 인덱스페이지
		System.out.println("site/home 폴더생성");
		Util.mkdirs("site/home");

		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html lang=\"ko\">");

		sb.append("<head>");
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		sb.append("<title>메인_index </title>");
		sb.append("</head>");

		sb.append("<body>");
		sb.append("<h1>메인</h1>");

		sb.append("<div>");
		sb.append("<a href=\"articlelist_notice_1.html\">공지게시판</a><br>");
		sb.append("<a href=\"articlelist_free_1.html\">자유게시판</a><br>");

		sb.append("</div>");

		sb.append("</body>");

		sb.append("</html>");

		String fileName = "index.html";
		String filePath = "site/home/" + fileName;

		Util.writeFile(filePath, sb.toString());

		System.out.println(filePath + "생성");

		// 게시물디테일
		System.out.println("site/article 폴더생성");
		Util.mkdirs("site/article");

		Util.copy("site_template/app.css", "site/article/app.css");

		List<Article> articles = articleService.getArticles();

		String head = getHeadHtml();
		String foot = Util.getFileContents("site_template/foot.html");

		for (Article article : articles) {
			StringBuilder sb_article = new StringBuilder();

			sb_article.append(head);

			sb_article.append("<div>");
			sb_article.append("번호 : " + article.id + "<br>");
			sb_article.append("작성날짜 : " + article.regDate + "<br>");
			sb_article.append("갱신날짜 : " + article.updateDate + "<br>");
			sb_article.append("제목 : " + article.title + "<br>");
			sb_article.append("내용 : " + article.body + "<br>");

			if (article.id - 1 > 0) {
				sb_article.append("<a href=\"article" + (article.id - 1) + ".html\">이전글</a><br>");
			}
			if (articles.size() > article.id) {
				sb_article.append("<a href=\"article" + (article.id + 1) + ".html\">다음글</a><br>");
			}

			sb_article.append("</div>");

			sb_article.append(foot);

			String fileName_article = "article" + article.id + ".html";
			String filePath_article = "site/article/" + fileName_article;

			Util.writeFile(filePath_article, sb_article.toString());

			System.out.println(filePath_article + "생성");

		}

		// 게시물 전체 리스트
		StringBuilder sb_list = new StringBuilder();

		sb_list.append("<!DOCTYPE html>");
		sb_list.append("<html lang=\"ko\">");

		sb_list.append("<head>");
		sb_list.append("<meta charset=\"UTF-8\">");
		sb_list.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		sb_list.append("<title>게시물 리스트</title>");
		sb_list.append("</head>");

		sb_list.append("<body>");
		sb_list.append("<h1>전체 게시물 리스트</h1>");

		for (Article article : articles) {

			sb_list.append("<div>");
			sb_list.append("번호 : " + article.id + " / ");
			sb_list.append("작성날짜 : " + article.regDate + " / ");
			sb_list.append("갱신날짜 : " + article.updateDate + " / ");
			sb_list.append("작성자 : " + article.memberId + " / ");
			sb_list.append("제목 : " + article.title);

			sb_list.append("</div>");

			sb_list.append("</body>");

			sb_list.append("</html>");

		}
		String fileName_list = "articlelist" + ".html";
		String filePath_list = "site/article/" + fileName_list;

		Util.writeFile(filePath_list, sb_list.toString());

		System.out.println(filePath_list + "생성");
		// 게시물리스트 끝

		// 게시판별 리스트
		// 공지게시판
		List<Article> articles1 = articleService.getForPrintArticles(1);
		StringBuilder sb1 = new StringBuilder();

		sb1.append("<!DOCTYPE html>");
		sb1.append("<html lang=\"ko\">");

		sb1.append("<head>");
		sb1.append("<meta charset=\"UTF-8\">");
		sb1.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		sb1.append("<title>게시물 리스트</title>");
		sb1.append("</head>");

		sb1.append("<body>");
		sb1.append("<h1>공지게시판 게시물 리스트</h1>");

		int itemsInAPage = 10;
		int pages;
		int page = 1;
		if (articles1.size() % itemsInAPage == 0) {
			pages = articles1.size() / itemsInAPage;
		} else {
			pages = articles1.size() / itemsInAPage + 1;
		}

		for (page = 1; page <= pages; page++) {

			int startPos = itemsInAPage * (page - 1);
			int endPos = itemsInAPage * page - 1;
			if (endPos >= articles1.size() - 1) {
				endPos = articles1.size() - 1;
			}
			for (int i = startPos; i <= endPos; i++) {
				Article article = articles1.get(i);

				sb1.append("<div>");
				sb1.append("번호 : " + article.id + " / ");
				sb1.append("작성날짜 : " + article.regDate + " / ");
				sb1.append("갱신날짜 : " + article.updateDate + " / ");
				sb1.append("작성자 : " + article.memberId + " / ");
				sb1.append("제목 : " + article.title);

			}
			sb1.append("</div>");

			sb1.append("</body>");

			sb1.append("</html>");

			String fileName1 = "articlelist_notice_" + page + ".html";
			String filePath1 = "site/article/" + fileName1;

			Util.writeFile(filePath1, sb1.toString());

			System.out.println(filePath1 + "생성");
		}

		// 자유게시판
		List<Article> articles2 = articleService.getForPrintArticles(2);
		StringBuilder sb2 = new StringBuilder();

		sb2.append("<!DOCTYPE html>");
		sb2.append("<html lang=\"ko\">");

		sb2.append("<head>");
		sb2.append("<meta charset=\"UTF-8\">");
		sb2.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		sb2.append("<title>게시물 리스트</title>");
		sb1.append("</head>");

		sb2.append("<body>");
		sb2.append("<h1>자유게시판 게시물 리스트</h1>");

		for (Article article : articles2) {

			sb2.append("<div>");
			sb2.append("번호 : " + article.id + " / ");
			sb2.append("작성날짜 : " + article.regDate + " / ");
			sb2.append("갱신날짜 : " + article.updateDate + " / ");
			sb2.append("작성자 : " + article.memberId + " / ");
			sb2.append("제목 : " + article.title);

			sb2.append("</div>");

			sb2.append("</body>");

			sb2.append("</html>");

		}
		String fileName2 = "articlelist_free" + ".html";
		String filePath2 = "site/article/" + fileName2;

		Util.writeFile(filePath2, sb2.toString());

		System.out.println(filePath2 + "생성");
	}

	private String getHeadHtml() {
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
			boardMenuContentHtml.append(board.name);
			boardMenuContentHtml.append("</span>");

			boardMenuContentHtml.append("</a>");

			boardMenuContentHtml.append("</li>");
		}

		head = head.replace("${menu-bar__menu-1__board-menu-content}", boardMenuContentHtml.toString());

		return head;
	}

}
