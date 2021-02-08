package com.sbs.example.jspCommunity.Dao;

import java.util.Map;

import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.SecSql;

public class ReplyDao {

	public int write(Map<String, Object> writeArgs) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `reply`");
		sql.append("SET regDate = DATE(NOW())");
		sql.append(", updateDate = DATE(NOW())");
		sql.append(", relTypeCode = ?", writeArgs.get("relTypeCode"));
		sql.append(", relId = ?", writeArgs.get("relId"));
		sql.append(", memberNum = ?", writeArgs.get("memberNum"));
		sql.append(", body = ?", writeArgs.get("body"));

		return MysqlUtil.insert(sql);
	}

}
