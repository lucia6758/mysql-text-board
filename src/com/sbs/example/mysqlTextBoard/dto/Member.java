package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Member {
	private int id;
	private String userId;
	private String regDate;
	private String updateDate;
	private String userPw;
	private String name;

	public Member(Map<String, Object> memberMap) {
		this.id = (int) memberMap.get("id");
		this.regDate = String.valueOf(memberMap.get("regDate"));
		this.updateDate = String.valueOf(memberMap.get("updateDate"));
		this.userId = (String) memberMap.get("userId");
		this.userPw = (String) memberMap.get("userPw");
		this.name = (String) memberMap.get("name");
	}

}
