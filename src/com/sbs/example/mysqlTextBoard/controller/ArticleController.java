package com.sbs.example.mysqlTextBoard.controller;

import java.util.List;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.ArticleReply;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.service.ArticleService;
import com.sbs.example.mysqlTextBoard.service.MemberService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("article list ")) {
			showList(cmd);
		} else if (cmd.startsWith("article detail ")) {
			showDetail(cmd);
		} else if (cmd.startsWith("article deleteReply ")) {
			deleteReply(cmd);
		} else if (cmd.startsWith("article modify ")) {
			modifyArticle(cmd);
		} else if (cmd.startsWith("article delete")) {
			deleteArticle(cmd);
		} else if (cmd.equals("article write")) {
			write(cmd);
		} else if (cmd.equals("article makeBoard")) {
			makeBoard(cmd);
		} else if (cmd.startsWith("article selectBoard ")) {
			selectBoard(cmd);
		} else if (cmd.startsWith("article writeReply ")) {
			writeReply(cmd);
		} else if (cmd.startsWith("article modifyReply ")) {
			modifyReply(cmd);
		} else if (cmd.startsWith("article recommand ")) {
			doRecommand(cmd);
		} else if (cmd.startsWith("article cancelRecommand ")) {
			cancelRecommand(cmd);
		}

	}

	private void cancelRecommand(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다");
			return;
		}

		System.out.println("== 추천 취소 ==");

		Article article = articleService.getArticleById(inputedId);
		if (article == null) {
			System.out.printf("%d번 글이 존재하지 않습니다.\n", inputedId);
			return;
		}

		boolean isRecommandable = articleService.isRecommandable(inputedId);
		if (isRecommandable == true) {
			System.out.println("추천하지 않은 글입니다. 취소불가");
			return;
		}

		articleService.cancelRecommand(inputedId);
		System.out.printf("%d번 글의 추천을 취소하였습니다.\n", inputedId);
	}

	private void doRecommand(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다");
			return;
		}
		System.out.println("== 게시물 추천 ==");

		Article article = articleService.getArticleById(inputedId);
		if (article == null) {
			System.out.printf("%d번 글이 존재하지 않습니다.\n", inputedId);
			return;
		}

		boolean isRecommandable = articleService.isRecommandable(inputedId);
		if (isRecommandable == false) {
			System.out.println("이미 추천한 게시글입니다.");
			return;
		}

		int id = articleService.recommand(inputedId);

		System.out.printf("%d번 글이 추천되었습니다.\n", inputedId);
	}

	private void deleteReply(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다");
			return;
		}

		ArticleReply articleReply = articleService.getReplyById(inputedId);

		if (articleReply == null) {
			System.out.printf("%d번 댓글이 존재하지 않습니다.\n", inputedId);
			return;
		}
		if (Container.session.loginedMemberId != articleReply.memberId) {
			System.out.println("다른 사람의 댓글을 삭제할 수 없습니다.");
			return;
		}

		System.out.println("== 댓글 삭제 ==");

		articleService.deleteReply(inputedId);

		System.out.printf("%d번 댓글이 삭제되었습니다.\n", inputedId);

	}

	private void modifyReply(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다");
			return;
		}

		ArticleReply articleReply = articleService.getReplyById(inputedId);

		if (articleReply == null) {
			System.out.printf("%d번 댓글이 존재하지 않습니다.\n", inputedId);
			return;
		}
		if (Container.session.loginedMemberId != articleReply.memberId) {
			System.out.println("다른 사람의 댓글을 수정할 수 없습니다.");
			return;
		}

		Member member = memberService.getMemberById(articleReply.memberId);

		System.out.println("== 댓글 수정 ==");
		System.out.printf("글 번호 : %d\n", articleReply.articleId);
		System.out.printf("댓글 번호 : %d\n", articleReply.id);
		System.out.printf("작성 : %s\n", articleReply.regDate);
		System.out.printf("작성자 : %s\n", member.name);
		System.out.printf("내용 : ");
		String reply = Container.scanner.nextLine();

		articleService.modifyReply(inputedId, reply);

		System.out.printf("%d번 댓글이 수정되었습니다.\n", inputedId);

	}

	private void writeReply(String cmd) {
		int articleId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다");
			return;
		}

		Article article = articleService.getArticleById(articleId);
		if (article == null) {
			System.out.printf("%d번 글이 존재하지 않습니다.\n", articleId);
			return;
		}
		System.out.println("== 댓글 작성 ==");

		System.out.printf("댓글 내용: ");
		String reply = Container.scanner.nextLine();

		int memberId = Container.session.loginedMemberId;
		int id = articleService.writeReply(memberId, articleId, reply);

		System.out.printf("%d번 글에 %d번 댓글이 등록되었습니다.\n", memberId, id);

	}

	private void selectBoard(String cmd) {
		int boardId = Integer.parseInt(cmd.split(" ")[2]);

		Board board = articleService.getBoardById(boardId);
		if (board == null) {
			System.out.println("게시판이 존재하지 않습니다.");
			return;
		}
		Container.session.selectedBoardId = boardId;
		System.out.printf("%s(%d번) 게시판이 선택되었습니다.\n", board.name, boardId);

	}

	private void makeBoard(String cmd) {
		System.out.println("== 게시판 생성 ==");

		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다");
			return;
		}
		if (Container.session.loginedMemberId != 1) {
			System.out.println("게시판 생성 권한이 없습니다.");
			return;
		}

		System.out.printf("게시판 이름 : ");
		String name = Container.scanner.nextLine();

		int boardId = articleService.makeBoard(name);

		System.out.printf("%d번 게시판이 생성되었습니다.\n", boardId);
	}

	private void showList(String cmd) {
		int boardId = Integer.parseInt(cmd.split(" ")[2]);
		Board board = articleService.getBoardById(boardId);
		if (board == null) {
			System.out.printf("%d번 게시판이 존재하지 않습니다.\n", boardId);
			return;
		}
		System.out.printf("== %s 게시판 리스트 ==\n", board.name);

		List<Article> articles = articleService.getForPrintArticles(boardId);

		System.out.println("번호 / 작성 / 수정 / 작성자 / 제목 / 조회수");

		for (Article article : articles) {
			System.out.printf("%d / %s / %s / %s / %s / %d\n", article.id, article.regDate, article.updateDate,
					article.extra_writer, article.title, article.hit);
		}
	}

	private void showDetail(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleById(inputedId);

		if (article == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}

		articleService.countHit(inputedId);

		Member member = memberService.getMemberById(article.memberId);
		List<ArticleReply> articleReplies = articleService.getRepliesByArticleId(inputedId);

		System.out.println("== 게시물 상세 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성 : %s\n", article.regDate);
		System.out.printf("수정 : %s\n", article.updateDate);
		System.out.printf("작성자 : %s\n", member.name);
		System.out.printf("게시판 : %s\n", article.boardId);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("조회수 : %d\n", article.hit + 1);
		System.out.println("=댓글=  번호/작성/수정/이름/내용 ");
		for (ArticleReply articleReply : articleReplies) {
			Member member1 = memberService.getMemberById(articleReply.memberId);
			System.out.printf("%d / %s / %s / %s / %s\n", articleReply.id, articleReply.regDate,
					articleReply.updateDate, member1.name, articleReply.reply);
		}

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

		Member member = memberService.getMemberById(article.memberId);

		System.out.println("== 게시물 수정 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("게시판 : %d\n", article.boardId);
		System.out.printf("작성 : %s\n", article.regDate);
		System.out.printf("작성자 : %s\n", member.name);
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

		articleService.delete(inputedId);

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
		int memberId = Container.session.loginedMemberId;
		int boardId = Container.session.selectedBoardId;

		int id = articleService.write(memberId, boardId, title, body);

		System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
	}

}
