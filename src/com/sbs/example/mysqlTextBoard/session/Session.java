package com.sbs.example.mysqlTextBoard.session;

public class Session {

	public int loginedMemberId;
	public int selectedBoardId;

	public Session() {
		loginedMemberId = 0;
		selectedBoardId = 1;
	}

	public boolean islogined() {
		return loginedMemberId != 0;
	}

	public void logout() {
		loginedMemberId = 0;
	}

}
