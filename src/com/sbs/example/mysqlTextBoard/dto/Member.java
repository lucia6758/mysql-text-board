package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

public class Member {
	public int id;
	public String userId;
	public String regDate;
	public String updateDate;
	public String userPw;
	public String name;

	public Member(Map<String, Object> memberMap) {
		this.id = (int) memberMap.get("id");
		this.regDate = String.valueOf(memberMap.get("regDate"));
		this.updateDate = String.valueOf(memberMap.get("updateDate"));
		this.userId = (String) memberMap.get("userId");
		this.userPw = (String) memberMap.get("userPw");
		this.name = (String) memberMap.get("name");
	}

}
