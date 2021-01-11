package com.sbs.example.jspCommunity.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.Dto.Article;
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
		
		List<Map<String,Object>> memberMapList = MysqlUtil.selectRows(sql);
		
		for(Map<String,Object> memberMap : memberMapList) {
			members.add(new Member(memberMap));
		}
		
		return members;
	}

}
