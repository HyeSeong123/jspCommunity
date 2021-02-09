package com.sbs.example.jspCommunity.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.Dto.Reply;
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

	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {

		List<Reply> replies = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT R.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra__likePoint");
		sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra__likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra__dislikeOnlyPoint");
		sql.append("FROM reply AS R");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON R.memberNum = M.memberNum");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'article'");
		sql.append("AND R.replyNum = L.relId");
		sql.append("WHERE 1");
		sql.append("AND R.relTypeCode = ?", relTypeCode);
		sql.append("AND R.relId = ?", relId);
		sql.append("GROUP BY R.replyNum");
		sql.append("ORDER BY R.replyNum DESC");

		List<Map<String, Object>> mapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> map : mapList) {
			replies.add(new Reply(map));
		}

		return replies;

	}

}
