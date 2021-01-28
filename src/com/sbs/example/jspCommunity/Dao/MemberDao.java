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

	public Member getForPrintMember(String id) {
		List<Member> members = getForPrintMembers();

		for (int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
			if (member.getLoginId().equals(id)) {
				return member;
			}
		}
		return null;
	}

	public int doJoin(Map<String, Object> joinArgs) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append("SET regDate=DATE_FORMAT(NOW(), '%Y-%m-%d'),");
		sql.append("updateDate=DATE_FORMAT(NOW(), '%Y-%m-%d'),");
		sql.append("name=?,", joinArgs.get("name"));
		sql.append("nickname=?,", joinArgs.get("nickname"));
		sql.append("email=?,", joinArgs.get("email"));
		sql.append("loginId=?,", joinArgs.get("loginId"));
		sql.append("loginPw=?,", joinArgs.get("loginPw"));
		sql.append("phNum=?", joinArgs.get("phNum"));

		return MysqlUtil.insert(sql);
	}

	public Member getMemberByLoginId(String loginId) {

		SecSql sql = new SecSql();

		sql.append("SELECT * FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}
		return new Member(map);
	}

	public Member getMemberByLoginNum(int loginMemberNum) {

		SecSql sql = new SecSql();

		sql.append("SELECT * FROM `member`");
		sql.append("WHERE memberNum = ?", loginMemberNum);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}
		return new Member(map);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("WHERE name = ?", name);
		sql.append("AND email = ?", email);
		sql.append("ORDER BY memberNum DESC");
		sql.append("LIMIT 1");

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Member(map);
	}

	public int modify(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("UPDATE member");
		sql.append("SET updateDate = DATE_FORMAT(NOW(), '%Y-%m-%d %h')");

		boolean needToUpdate = false;

		if (args.get("loginPw") != null) {
			needToUpdate = true;
			sql.append(", loginPw =?", args.get("loginPw"));
		}

		if (args.get("name") != null) {
			needToUpdate = true;
			sql.append(", name =?", args.get("name"));
		}

		if (args.get("nickName") != null) {
			needToUpdate = true;
			sql.append(", nickname =?", args.get("nickName"));
		}

		if (args.get("email") != null) {
			needToUpdate = true;
			sql.append(", email =?", args.get("email"));
		}

		if (args.get("phNum") != null) {
			needToUpdate = true;
			sql.append(", phNum =?", args.get("phNum"));
		}

		if (needToUpdate == false) {
			return 0;
		}

		sql.append("WHERE memberNum = ?", args.get("memberNum"));

		return MysqlUtil.update(sql);
	}
}
