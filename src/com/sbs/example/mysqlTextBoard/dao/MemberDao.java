package com.sbs.example.mysqlTextBoard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbs.example.mysqlTextBoard.dto.Member;

public class MemberDao {

	public int join(String userId, String userPw, String name) {
		Connection con = null;
		Member member = new Member();

		try {
			String dbmsJdbcUrl = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";
			String dbmsLoginId = "sbsst";
			String dbmsLoginPw = "sbs123414";

			// MySQL 드라이버 등록
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			// 연결 생성
			try {
				con = DriverManager.getConnection(dbmsJdbcUrl, dbmsLoginId, dbmsLoginPw);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "insert into member(userId, userPw, name) values(?, ?, ?)";

			try {
				PreparedStatement pstmt = con.prepareStatement(sql);

				pstmt.setString(1, userId);
				pstmt.setString(2, userPw);
				pstmt.setString(3, name);

				pstmt.executeUpdate();

				String confSql = "SELECT * FROM member ORDER BY id DESC LIMIT 1";
				ResultSet rs = con.prepareStatement(confSql).executeQuery();
				while (rs.next()) {
					member.id = rs.getInt("id");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return member.id;

	}

	public Member getMemberByUserId(String loginId) {
		Connection con = null;
		Member member = null;

		try {
			String dbmsJdbcUrl = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";
			String dbmsLoginId = "sbsst";
			String dbmsLoginPw = "sbs123414";

			// MySQL 드라이버 등록
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			// 연결 생성
			try {
				con = DriverManager.getConnection(dbmsJdbcUrl, dbmsLoginId, dbmsLoginPw);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "SELECT * FROM `member` where userId = ?";

			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, loginId);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {

					int id = rs.getInt("id");
					String userId = rs.getString("userId");
					String userPw = rs.getString("userPw");
					String name = rs.getString("name");
					
					member = new Member(id, userId, userPw, name);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	public Member getMemerById(int memberId) {
		Connection con = null;
		Member member = null;

		try {
			String dbmsJdbcUrl = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";
			String dbmsLoginId = "sbsst";
			String dbmsLoginPw = "sbs123414";

			// MySQL 드라이버 등록
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			// 연결 생성
			try {
				con = DriverManager.getConnection(dbmsJdbcUrl, dbmsLoginId, dbmsLoginPw);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String sql = "SELECT * FROM `member` where id = ?";

			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, memberId);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {

					int id = rs.getInt("id");
					String userId = rs.getString("userId");
					String userPw = rs.getString("userPw");
					String name = rs.getString("name");
					
					member = new Member(id, userId, userPw, name);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

}
