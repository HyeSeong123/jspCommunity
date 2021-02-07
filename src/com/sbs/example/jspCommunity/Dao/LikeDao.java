package com.sbs.example.jspCommunity.Dao;

import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.SecSql;

public class LikeDao {

	public int getPoint(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT IFNULL(SUM(L.point), 0) AS `point`");
		sql.append("FROM `like` AS L");
		sql.append("WHERE 1");
		sql.append("AND L.relTypeCode = ?", relTypeCode);
		sql.append("AND L.relId = ?", relId);
		sql.append("AND L.memberNum = ?", memberId);

		System.out.println("dislike= " + sql);
		return MysqlUtil.selectRowIntValue(sql);
	}

	public int setLikePoint(String relTypeCode, int relId, int actorId, int point) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `like`");
		sql.append("SET regDate = DATE(NOW())");
		sql.append(", updateDate = DATE(NOW())");
		sql.append(", relTypeCode = ?", relTypeCode);
		sql.append(", relId = ?", relId);
		sql.append(", memberNum = ?", actorId);
		sql.append(", point = ?", point);

		return MysqlUtil.insert(sql);
	}

	public int removePoint(String relTypeCode, int relId, int actorId) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM `like`");
		sql.append("WHERE 1");
		sql.append("AND relTypeCode = ?", relTypeCode);
		sql.append("AND relId = ?", relId);
		sql.append("AND memberNum = ?", actorId);

		return MysqlUtil.delete(sql);
	}

}
