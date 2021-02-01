package com.sbs.example.jspCommunity.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Attr;
import com.sbs.example.jspCommunity.Dto.Member;
import com.sbs.example.jspCommunity.Dto.ResultData;
import com.sbs.example.jspCommunity.Service.AttrService;
import com.sbs.example.jspCommunity.Service.MemberService;

public class usrMemberController {

	private MemberService memberService;
	private AttrService attrService;

	public usrMemberController() {
		memberService = Container.memberService;
		attrService = Container.attrService;
	}

	public String showList(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = memberService.getForPrintMembers();

		request.setAttribute("members", members);

		return "usr/member/list";
	}

	public String showJoin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			request.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
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
		session.setAttribute("loginedMemberNum", member.getMemberNum());

		Attr attr = attrService.getAttr("member", member.getMemberNum(), "extra", "tempPassword");

		request.setAttribute("attr", attr);
		if (attr != null) {
			if (attr.getType2Code().equals("tempPassword")) {
				if (attr.getRelId() == member.getMemberNum()) {
					if (attr.getValue().equals("1")) {
						request.setAttribute("tempPassword", attr.getValue());
						System.out.printf("tempPassword=%s\n", attr.getValue());
						request.setAttribute("alertMsg", "비밀번호 변경이 필요합니다.");
						request.setAttribute("replaceUrl", "../member/whoami");
						return "common/redirect";
					}
				}
			}
		}
		request.setAttribute("alertMsg", String.format("%s님의 방문을 환영합니다.", member.getNickname()));
		request.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	public String doLogout(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberNum");

		request.setAttribute("alertMsg", "로그아웃 되었습니다.");
		request.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	public String getLoginIdDup(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");

		Member member = memberService.getMemberByLoginId(loginId);

		String resultCode = null;
		String msg = null;

		if (member != null) {
			resultCode = "F-1";
			msg = "이미 사용중인 로그인 아이디 입니다.";
		} else {
			resultCode = "S-1";
			msg = "사용 가능한 로그인 아이디 입니다.";
		}

		request.setAttribute("data", new ResultData(resultCode, msg, "loginId", loginId));
		return "common/json";
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

		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

		if (sendTempLoginPwToEmailRs.isFail()) {
			request.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}
		request.setAttribute("alertMsg", String.format(sendTempLoginPwToEmailRs.getMsg()));
		request.setAttribute("replaceUrl", "../member/login");
		return "common/redirect";
	}

	public String showWhoAmI(HttpServletRequest request, HttpServletResponse response) {
		int memberNum = (int) request.getAttribute("loginedMemberNum");

		if (memberNum != 0) {
			Attr attr = attrService.getAttr("member", memberNum, "extra", "tempPassword");

			request.setAttribute("tempPassword", attr.getValue());
		} 
		return "usr/member/whoami";
	}

	public String showModifyAccount(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/modifyAccount";
	}

	public String doModifyAccount(HttpServletRequest request, HttpServletResponse response) {
		int memberNum = (int) request.getAttribute("loginedMemberNum");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		String phNum = request.getParameter("phNum");
		String loginPw = (String) request.getParameter("loginPwReal");

		Member member = memberService.getMemberByLoginNum(memberNum);

		if (loginPw != null && loginPw.length() == 0) {
			loginPw = null;
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			attrService.updatePwValue("member", memberNum, "extra", "tempPassword", "2");
		}

		Map<String, Object> modifyArgs = new HashMap<>();

		modifyArgs.put("loginPw", loginPw);
		modifyArgs.put("memberNum", memberNum);
		modifyArgs.put("nickName", nickName);
		modifyArgs.put("email", email);
		modifyArgs.put("phNum", phNum);

		memberService.modifyAccount(modifyArgs);

		request.setAttribute("alertMsg", "회원님의 정보가 변경 완료되었습니다.");
		request.setAttribute("replaceUrl", "whoami");

		return "common/redirect";
	}
}
