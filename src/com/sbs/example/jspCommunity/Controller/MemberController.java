package com.sbs.example.jspCommunity.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Member;
import com.sbs.example.jspCommunity.Service.MemberService;

public class MemberController {
	
	private MemberService memberService;
	
	public MemberController(){
		memberService = Container.memberService;
	}
	
	public String showList(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = memberService.getForPrintMembers();
		
		request.setAttribute("members", members);
		
		return "usr/member/list";
	}

}
