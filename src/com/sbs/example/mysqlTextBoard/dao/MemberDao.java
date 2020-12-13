package com.sbs.example.mysqlTextBoard.dao;

import java.util.Map;

import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlTextBoard.mysqlutil.SecSql;

public class MemberDao {

	public int join(String userId, String userPw, String name) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append("SET userId = ?", userId);
		sql.append(", userPw = ?", userPw);
		sql.append(", `name` = ?", name);

		return MysqlUtil.insert(sql);

	}

	public Member getMemberByUserId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE userId = ?", loginId);

		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);

		if (memberMap.isEmpty()) {
			return null;
		}

		return new Member(memberMap);
	}

	public Member getMemerById(int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE id = ?", memberId);

		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);

		if (memberMap.isEmpty()) {
			return null;
		}

		return new Member(memberMap);

	}

	public int getNumberOfMember() {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*)");
		sql.append("FROM `member`");
		
		return MysqlUtil.count(sql);
	}

}
