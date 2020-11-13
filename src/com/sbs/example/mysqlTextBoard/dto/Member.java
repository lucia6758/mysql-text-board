package com.sbs.example.mysqlTextBoard.dto;

public class Member {
	public int id;
	public String userId;
	public String userPw;
	public String name;
	
	public Member() {
		
	}

	public Member(int id, String userId, String userPw, String name) {
		this.id = id;
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
	}

}
