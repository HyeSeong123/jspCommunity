package com.sbs.example.jspCommunity.Service;

import java.util.List;

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

	
}
