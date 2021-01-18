package com.sbs.example.jspCommunity.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.Dto.Member;
import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.SecSql;

public class MemberDao {

	public List<Member> getForPrintMembers() {
		List<Member> members = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM member");
		sql.append("ORDER BY memberNum DESC");

		List<Map<String, Object>> memberMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> memberMap : memberMapList) {
			members.add(new Member(memberMap));
		}

		return members;
	}

	public int doJoin(Map<String, Object> writeArgs) {

		String id = (String) writeArgs.get("loginId");

		int num = idCheck(id);

		if (num != 1) {
			return num;
		}
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append("SET regDate=DATE_FORMAT(NOW(), '%Y-%m-%d %h:%i'),");
		sql.append("updateDate=DATE_FORMAT(NOW(), '%Y-%m-%d %h:%i'),");
		sql.append("name=?,", writeArgs.get("name"));
		sql.append("nickname=?,", writeArgs.get("nickname"));
		sql.append("email=?,", writeArgs.get("email"));
		sql.append("loginId=?,", writeArgs.get("loginId"));
		sql.append("loginPw=?", writeArgs.get("loginPw"));

		return MysqlUtil.insert(sql);
	}

	private int idCheck(String id) {
		List<Member> members = getForPrintMembers();

		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getLoginId().equals(id)) {
				return -1;
			}
		}
		String[] idBytes = id.split("");

		if (idBytes.length <= 5) {
			return -2;
		}

		for (int i = 0; i < idBytes.length; i++) {
			if (idBytes[i].equals(" ")) {
				return -3;
			}
		}

		return 1;
	}

}
