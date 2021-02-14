package com.sbs.example.jspCommunity.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Member;
import com.sbs.example.jspCommunity.Dto.ResultData;
import com.sbs.example.jspCommunity.Service.AttrService;
import com.sbs.example.jspCommunity.Service.MemberService;
import com.sbs.example.jspCommunity.Util.Util;

public class usrMemberController extends Controller {

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
		String name = request.getParameter("name");
		if (Util.isEmpty(name)) {
			return msgAndBack(request, "이름을 입력 해주세요.");
		}
		String loginId = request.getParameter("loginId");
		if (Util.isEmpty(loginId)) {
			return msgAndBack(request, "로그인아이디를 입력해주세요");
		}
		String loginPw = request.getParameter("loginPwReal");
		if (Util.isEmpty(loginPw)) {
			return msgAndBack(request, "로그인패스워드를 입력해주세요");
		}
		String nickname = request.getParameter("nickname");
		if (Util.isEmpty(nickname)) {
			return msgAndBack(request, "닉네임을 입력해주세요");
		}
		String email = request.getParameter("email");
		if (Util.isEmpty(email)) {
			return msgAndBack(request, "이메일을 입력해주세요");
		}
		String phNum = request.getParameter("phNum");
		if (Util.isEmpty(phNum)) {
			return msgAndBack(request, "휴대전화 번호를 입력해주세요");
		}

		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return msgAndBack(request, oldMember.getLoginId() + "는 이미 사용중인 아이디 입니다.");
		}
		Map<String, Object> joinArgs = new HashMap<>();

		joinArgs.put("name", name);
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("nickname", nickname);
		joinArgs.put("email", email);
		joinArgs.put("phNum", phNum);

		memberService.doJoin(joinArgs);
		
		return msgAndReplace(request, loginId + "님의 회원가입을 환영합니다.","../home/main");
	}

	public String showLogin(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/login";
	}

	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPwReal");

		Member member = memberService.getMemberByLoginId(id);

		if (member == null) {
			return msgAndBack(request, id + "와 일치하는 아이디가 없습니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return msgAndBack(request, "패스워드가 일치하지 않습니다.");
		}

		HttpSession session = request.getSession();
		session.setAttribute("loginedMemberNum", member.getMemberNum());

		boolean isUsingTempPassword = memberService.getIsUsingTempPassword(member.getMemberNum());

		String alertMsg = String.format("%s님 환영합니다.", member.getNickname());
		String replaceUrl = "../home/main";

		if (Util.isEmpty(request.getParameter("afterLoginUrl")) == false) {
			replaceUrl = request.getParameter("afterLoginUrl");
		}

		if (isUsingTempPassword) {
			alertMsg = String.format("%s 님은 현재 임시비밀번	호를 사용중입니다. 변경 후 이용해주세요.", member.getNickname());
			replaceUrl = "../member/modifyAccount";
		}

		return msgAndReplace(request, alertMsg, replaceUrl);
	}

	public String doLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberNum");

		return msgAndReplace(request, "로그아웃 되었습니다.", "../home/main");
	}

	public String getLoginIdDup(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		System.out.println("loginId =" + loginId);
		Member member = memberService.getMemberByLoginId(loginId);

		String resultCode = null;
		String msg = null;

		if (member != null) {
			resultCode = "F-1";
			msg = "이미 사용중인 로그인 아이디 입니다.";
		}
		else if (loginId.length() == 0) {
			resultCode = "F-2";
			msg = "아이디를 입력해주세요.";
		}
		else {
			resultCode = "S-1";
			msg = "사용 가능한 로그인 아이디 입니다.";
		}

		return json(request, new ResultData(resultCode, msg, "loginId", loginId));
	}

	public String showFindLoginId(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			return msgAndBack(request, "로그아웃 후 진행해주세요.");
		}
		return "usr/member/findLoginId";
	}

	public String doFindLoginId(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			return msgAndBack(request, "로그아웃 후 진행해주세요.");
		}
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			return msgAndBack(request, "일치하는 회원이 없습니다.");
		}

		request.setAttribute("alertMsg", String.format("로그인아이디는 %s 입니다.", member.getLoginId()));
		request.setAttribute("replaceUrl", "../member/login");
		return "common/redirect";
	}

	public String showFindLoginPw(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			return msgAndBack(request, "로그아웃 후 진행해주세요.");
		}
		return "usr/member/findLoginPw";
	}

	public String doFindLoginPw(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			return msgAndBack(request, "로그아웃 후 진행해주세요.");
		}
		String loginId = request.getParameter("loginId");
		String email = request.getParameter("email");

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return msgAndBack(request, "일치하는 회원이 없습니다.");
		}

		if (member.getEmail().equals(email) == false) {
			return msgAndBack(request, "회원님의 이메일 주소와 아이디가 일치하지 않습니다.");
		}

		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

		if (sendTempLoginPwToEmailRs.isFail()) {
			return msgAndBack(request, sendTempLoginPwToEmailRs.getMsg());
		}
		return msgAndReplace(request, sendTempLoginPwToEmailRs.getMsg(), "../member/login");
	}

	public String showWhoAmI(HttpServletRequest request, HttpServletResponse response) {
		int memberNum = (int) request.getAttribute("loginedMemberNum");

		return "usr/member/whoami";
	}

	public String showModifyAccount(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/modifyAccount";
	}

	public String doModifyAccount(HttpServletRequest request, HttpServletResponse response) {
		int memberNum = (int) request.getAttribute("loginedMemberNum");
		String nickName = request.getParameter("nickName");
		if (Util.isEmpty(nickName)) {
			return msgAndBack(request, "닉네임을 입력 해주세요.");
		}
		String email = request.getParameter("email");
		if (Util.isEmpty(email)) {
			return msgAndBack(request, "이메일을 입력 해주세요.");
		}
		String phNum = request.getParameter("phNum");
		if (Util.isEmpty(phNum)) {
			return msgAndBack(request, "휴대전화 번호를 입력 해주세요.");
		}
		String loginPw = (String) request.getParameter("loginPwReal");
		if (Util.isEmpty(loginPw)) {
			return msgAndBack(request, "패스워드를 입력 해주세요.");
		}

		Member member = memberService.getMemberByLoginNum(memberNum);

		if (loginPw != null && loginPw.length() == 0) {
			loginPw = null;
		}

		Map<String, Object> modifyArgs = new HashMap<>();

		modifyArgs.put("loginPw", loginPw);
		modifyArgs.put("memberNum", memberNum);
		modifyArgs.put("nickName", nickName);
		modifyArgs.put("email", email);
		modifyArgs.put("phNum", phNum);

		memberService.modifyAccount(modifyArgs);

		if (loginPw != null) {
			memberService.setIsUsingTempPassword(memberNum, false);
		}

		return msgAndBack(request, "회원님의 정보가 변경 완료되었습니다.");
	}
}
