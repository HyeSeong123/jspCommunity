package com.sbs.example.jspCommunity.Service;

import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.MemberDao;
import com.sbs.example.jspCommunity.Dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}

	public int doJoin(Map<String, Object> writeArgs) {
		return memberDao.doJoin(writeArgs);
	}

	
}
