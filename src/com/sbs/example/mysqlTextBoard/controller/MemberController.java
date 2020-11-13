package com.sbs.example.mysqlTextBoard.controller;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.service.MemberService;

public class MemberController {

	private MemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	public void doCommand(String cmd) {
		if (cmd.equals("member join")) {
			join(cmd);
		} else if (cmd.equals("member login")) {
			login();
		}

	}

	private void join(String cmd) {
		System.out.println("== 회원가입 ==");

		System.out.printf("아이디 : ");
		String userId = Container.scanner.nextLine();
		
		boolean isJoinableUserId = memberService.isJoinableUserId(userId);
		if(isJoinableUserId==false) {
			System.out.println("이미 존재하는 아이디입니다.");
			return;
		}
		
		System.out.printf("비밀번호 : ");
		String userPw = Container.scanner.nextLine();
		System.out.printf("이름 : ");
		String name = Container.scanner.nextLine();

		int id = memberService.join(userId, userPw, name);

		System.out.printf("%d번 회원으로 등록되었습니다.\n", id);

	}

	private void login() {
		if(Container.session.islogined()) {
			System.out.println("이미 로그인되어있습니다.");
			return;
		}
		System.out.println("== 로그인 ==");

		System.out.printf("아이디 : ");
		String userId = Container.scanner.nextLine();

		Member member = memberService.getMemberByUserId(userId);
		
		if(member==null) {
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
		Container.session.loginedMemberId = member.id;
	}

}
