package com.sbs.example.mysqlTextBoard.session;

public class Session {

	public int loginedMemberId;
	
	public Session() {
		loginedMemberId = 0;
	}

	public boolean islogined() {
		return loginedMemberId != 0;
	}
	
	

}
