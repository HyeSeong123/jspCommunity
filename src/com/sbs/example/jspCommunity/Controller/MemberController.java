package com.sbs.example.jspCommunity.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Member;
import com.sbs.example.jspCommunity.Service.MemberService;

public class MemberController {

	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public String showList(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = memberService.getForPrintMembers();

		request.setAttribute("members", members);

		return "usr/member/list";
	}

	public String showJoin(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");

		Map<String, Object> writeArgs = new HashMap<>();

		writeArgs.put("name", name);
		writeArgs.put("loginId", loginId);
		writeArgs.put("loginPw", loginPw);
		writeArgs.put("nickname", nickname);
		writeArgs.put("email", email);

		int newMemberNum = memberService.doJoin(writeArgs);

		if (newMemberNum < 0) {
			if (newMemberNum == -1) {
				request.setAttribute("alertMsg", "아이디 중복!! 다른 아이디를 이용해주세요.");

			} else if (newMemberNum == -2) {
				request.setAttribute("alertMsg", "아이디는 다섯 글자 이상이어야 합니다.");

			} else if (newMemberNum == -3) {
				request.setAttribute("alertMsg", "아이디 중간에 공백이 있어서는 안됩니다.");

			}
		}

		request.setAttribute("alertMsg", newMemberNum + "번 회원님의 가입을 환영합니다.");

		return "common/redirect";
	}

}
