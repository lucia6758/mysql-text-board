package com.sbs.example.mysqlTextBoard.controller;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.service.MemberService;

public class MemberController extends Controller {

	private MemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	public void doCommand(String cmd) {
		if (cmd.equals("member join")) {
			join(cmd);
		} else if (cmd.equals("member login")) {
			login(cmd);
		} else if (cmd.equals("member whoami")) {
			whoami(cmd);
		} else if (cmd.equals("member logout")) {
			logout();
		}

	}

	private void logout() {
		if (Container.session.islogined() == false) {
			System.out.println("이미 로그아웃 상태입니다.");
			return;
		}

		Container.session.logout();
		System.out.println("로그아웃 되었습니다.");
	}

	private void whoami(String cmd) {
		if (Container.session.islogined() == false) {
			System.out.println("로그인 후에 이용할 수 있습니다.");
			return;
		}
		System.out.println("== 회원 정보 ==");

		Member loginedMember = memberService.getMemberById(Container.session.loginedMemberId);

		System.out.printf("번호 : %d\n", loginedMember.id);
		System.out.printf("가입날짜 : %s\n", loginedMember.regDate);
		System.out.printf("아이디 : %s\n", loginedMember.userId);
		System.out.printf("이름 : %s\n", loginedMember.name);

	}

	private void join(String cmd) {
		if (Container.session.islogined()) {
			System.out.println("로그아웃 후에 이용할 수 있습니다.");
			return;
		}

		System.out.println("== 회원가입 ==");

		System.out.printf("아이디 : ");
		String userId = Container.scanner.nextLine().trim();

		if (userId.length() == 0) {
			System.out.println("아이디가 입력되지않았습니다.");
			return;
		}

		boolean isJoinableUserId = memberService.isJoinableUserId(userId);
		if (isJoinableUserId == false) {
			System.out.println("이미 존재하는 아이디입니다.");
			return;
		}

		System.out.printf("비밀번호 : ");
		String userPw = Container.scanner.nextLine().trim();

		if (userPw.length() == 0) {
			System.out.println("비밀번호가 입력되지않았습니다.");
			return;
		}
		System.out.printf("비밀번호 확인 : ");
		String checkUserPw = Container.scanner.nextLine().trim();

		if (userPw.equals(checkUserPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}

		System.out.printf("이름 : ");
		String name = Container.scanner.nextLine().trim();

		if (name.length() == 0) {
			System.out.println("이름이 입력되지않았습니다.");
			return;
		}

		int id = memberService.join(userId, userPw, name);

		System.out.printf("%d번 회원으로 등록되었습니다.\n", id);

	}

	private void login(String cmd) {
		if (Container.session.islogined()) {
			System.out.println("이미 로그인되어있습니다.");
			return;
		}
		System.out.println("== 로그인 ==");

		System.out.printf("아이디 : ");
		String userId = Container.scanner.nextLine();

		Member member = memberService.getMemberByUserId(userId);

		if (member == null) {
			System.out.println("존재하지않는 아이디입니다.");
			return;
		}

		System.out.printf("비밀번호 : ");
		String userPw = Container.scanner.nextLine();

		if (member.userPw.equals(userPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}

		System.out.printf("%s님 환영합니다.\n", member.name);
		Container.session.login(member.id);
	}

}
