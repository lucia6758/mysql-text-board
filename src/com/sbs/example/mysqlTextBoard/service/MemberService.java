package com.sbs.example.mysqlTextBoard.service;

import com.sbs.example.mysqlTextBoard.container.Container;
import com.sbs.example.mysqlTextBoard.dao.MemberDao;
import com.sbs.example.mysqlTextBoard.dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public int join(String userId, String userPw, String name) {
		return memberDao.join(userId, userPw, name);
	}

	public Member getMemberByUserId(String userId) {
		return memberDao.getMemberByUserId(userId);
	}

	public boolean isJoinableUserId(String userId) {
		Member member = memberDao.getMemberByUserId(userId);
		if (member != null) {
			return false;
		}
		return true;
	}

	public Member getMemberById(int memberId) {
		return memberDao.getMemerById(memberId);
	}

	public int getNumberOfMember() {
		return memberDao.getNumberOfMember();
	}

}
