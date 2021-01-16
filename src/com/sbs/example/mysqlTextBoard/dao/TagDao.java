package com.sbs.example.mysqlTextBoard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlTextBoard.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlTextBoard.mysqlutil.SecSql;

public class TagDao {

	public List<String> getTagBodiesByRelTypeCode(String relTypeCode) {
		List<String> tagBodies = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT body");
		sql.append("FROM tag");
		sql.append("WHERE 1");

		if (relTypeCode != null && relTypeCode.length() > 0) {
			sql.append("AND relTypeCode = ?", relTypeCode);
		}
		sql.append("GROUP BY body");
		sql.append("ORDER BY body");

		List<Map<String, Object>> list = MysqlUtil.selectRows(sql);

		for (Map<String, Object> map : list) {
			tagBodies.add((String) map.get("body"));
		}
		return tagBodies;
	}

}
