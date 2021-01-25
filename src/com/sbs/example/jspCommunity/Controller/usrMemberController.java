package com.sbs.example.jspCommunity.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Member;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Service.MemberService;
import com.sbs.example.jspCommunity.Util.Util;

public class usrMemberController {

	private MemberService memberService;

	public usrMemberController() {
		memberService = Container.memberService;
	}

	public String showList(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = memberService.getForPrintMembers();

		request.setAttribute("members", members);

		return "usr/member/list";
	}

	public String showJoin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			request.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			request.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}
		String name = request.getParameter("name");
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPwReal");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String phNum = request.getParameter("phNum");

		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			request.setAttribute("alertMsg", "이미 사용중인 아이디 입니다.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}
		Map<String, Object> joinArgs = new HashMap<>();

		joinArgs.put("name", name);
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("nickname", nickname);
		joinArgs.put("email", email);
		joinArgs.put("phNum", phNum);

		int newMemberNum = memberService.doJoin(joinArgs);

		request.setAttribute("alertMsg", newMemberNum + "번 회원님의 가입을 환영합니다.");
		request.setAttribute("replaceUrl", "join");
		return "common/redirect";
	}

	public String showLogin(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/login";
	}

	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPwReal");

		Member member = memberService.getMemberByLoginId(id);

		if (member == null) {
			request.setAttribute("alertMsg", "일치하는 아이디가 없습니다.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			request.setAttribute("alertMsg", "패스워드가 일치하지 않습니다.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		HttpSession session = request.getSession();
		session.setAttribute("loginedMemberId", member.getMemberNum());

		request.setAttribute("alertMsg", String.format("%s님의 방문을 환영합니다.", member.getNickname()));
		request.setAttribute("replaceUrl", "../home/main");
		System.out.println(member.getMemberNum());
		return "common/redirect";
	}

	public String doLogout(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");

		request.setAttribute("alertMsg", "로그아웃 되었습니다.");
		request.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	public String getLoginIdDup(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");

		Member member = memberService.getMemberByLoginId(loginId);

		Map<String, Object> rs = new HashMap<>();

		String resultCode = null;
		String msg = null;

		if (member != null) {
			resultCode = "F-1";
			msg = "이미 사용중인 로그인 아이디 입니다.";
		} else {
			resultCode = "S-1";
			msg = "사용 가능한 로그인 아이디 입니다.";
		}
		rs.put("resultCode", resultCode);
		rs.put("msg", msg);
		rs.put("loginId", loginId);

		request.setAttribute("data", Util.getJsonText(rs));
		return "common/pure";
	}

	public String showFindLoginId(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			request.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}
		return "usr/member/findLoginId";
	}

	public String doFindLoginId(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			request.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			request.setAttribute("alertMsg", "일치하는 회원이 없습니다.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		request.setAttribute("alertMsg", String.format("로그인아이디는 %s 입니다.", member.getLoginId()));
		request.setAttribute("replaceUrl", "../member/login");
		return "common/redirect";
	}

	public String showFindLoginPw(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			request.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}
		return "usr/member/findLoginPw";
	}

	public String doFindLoginPw(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			request.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}
		String loginId = request.getParameter("loginId");
		String email = request.getParameter("email");

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			request.setAttribute("alertMsg", "일치하는 회원이 없습니다.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getEmail().equals(email) == false) {
			request.setAttribute("alertMsg", "회원님의 이메일 주소와 아이디가 일치하지 않습니다.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		memberService.sendTempLoginPwToEmail(member);

		request.setAttribute("alertMsg", String.format("고객님의 새 임시 ㅣ패스워드가 %s(으)로 발송 되었습니다..", member.getEmail()));
		request.setAttribute("replaceUrl", "../member/login");
		return "common/redirect";
	}
}
