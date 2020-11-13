package com.sbs.example.mysqlTextBoard.controller;

import java.util.List;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.service.ArticleService;
import com.sbs.example.mysqlTextBoard.service.MemberService;

public class ArticleController {
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.equals("article list")) {
			showList();
		} else if (cmd.startsWith("article detail ")) {
			showDetail(cmd);
		} else if (cmd.startsWith("article modify ")) {
			modifyArticle(cmd);
		} else if (cmd.startsWith("article delete")) {
			deleteArticle(cmd);
		} else if (cmd.equals("article write")) {
			write(cmd);
		}

	}

	private void showList() {
		System.out.println("== 게시물 리스트 ==");

		List<Article> articles = articleService.getArticles();

		System.out.println("번호 / 작성 / 수정 / 작성자 / 제목");

		for (Article article : articles) {
			Member member = memberService.getMemberById(article.memberId);
			System.out.printf("%d / %s / %s / %s / %s\n", article.id, article.regDate, article.updateDate,
					member.name, article.title);
		}
	}

	private void showDetail(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleById(inputedId);

		if (article == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}

		System.out.println("== 게시물 상세 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성 : %s\n", article.regDate);
		System.out.printf("수정 : %s\n", article.updateDate);
		System.out.printf("작성자 : %s\n", article.memberId);
		System.out.printf("게시판 : %s\n", article.boardId);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);

	}

	private void modifyArticle(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다");
			return;
		}

		Article article = articleService.getArticleById(inputedId);

		if (article == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		if (Container.session.loginedMemberId != article.memberId) {
			System.out.println("다른 사람의 글을 수정할 수 없습니다.");
			return;
		}

		System.out.println("== 게시물 수정 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("게시판 : %d\n", article.boardId);
		System.out.printf("작성 : %s\n", article.regDate);
		System.out.printf("작성자 : %s\n", article.memberId);
		System.out.printf("제목 : ");
		String title = Container.scanner.nextLine();
		System.out.printf("내용 : ");
		String body = Container.scanner.nextLine();

		articleService.modify(inputedId, title, body);

		System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);

	}

	private void deleteArticle(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다");
			return;
		}

		Article article = articleService.getArticleById(inputedId);
		if (article == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}

		if (Container.session.loginedMemberId != article.memberId) {
			System.out.println("다른 사람의 글을 지울 수 없습니다.");
			return;
		}

		System.out.println("== 게시물 삭제 ==");

		articleService.remove(inputedId);

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

	}

	private void write(String cmd) {

		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다");
			return;
		}
		
		System.out.println("== 게시물 작성 ==");
		System.out.printf("제목 : ");
		String title = Container.scanner.nextLine();
		System.out.printf("내용 : ");
		String body = Container.scanner.nextLine();
		
		//임시
		int boardId = 1;

		int id = articleService.write(Container.session.loginedMemberId, boardId, title, body);

		System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
	}

}
