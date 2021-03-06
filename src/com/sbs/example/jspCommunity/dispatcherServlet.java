package com.sbs.example.jspCommunity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Member;
import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.Util;

public abstract class dispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		run(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> doBeforeActionRs = doBeforeAction(request, response);

		if (doBeforeActionRs == null) {
			return;
		}

		String jspPath = doAction(request, response, (String) doBeforeActionRs.get("controllerName"),
				(String) doBeforeActionRs.get("actionMethodName"));

		if (jspPath == null) {
			response.getWriter().append("jsp 정보가 없습니다.");
			return;
		}

		doAfterAction(request, response, jspPath);
	}

	private Map<String, Object> doBeforeAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String profilesActive = System.getProperty("spring.profiles.active");

		String requestUri = request.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		int minBitsCount = 5;

		if (App.isProductMode()) {
			minBitsCount = 4;
		}

		if (requestUriBits.length < minBitsCount) {
			response.getWriter().append("올바른 요청이 아닙니다. <br/>");
			response.getWriter().append("minBitsCount= " + minBitsCount);
			response.getWriter().append("profilesActive= " + profilesActive);
			return null;
		}
		if (App.isProductMode()) {
			MysqlUtil.setDBInfo("127.0.0.1", "sbsstLocal", "sbs123414", "jspCommunityReal");
		} else {
			MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
			MysqlUtil.setDevMode(true);
		}

		int controllerTypeNameIndex = 2;
		int controllerNameIndex = 3;
		int actionMethodNameIndex = 4;

		if (App.isProductMode()) {
			controllerTypeNameIndex = 1;
			controllerNameIndex = 2;
			actionMethodNameIndex = 3;
		}

		String controllerTypeName = requestUriBits[controllerTypeNameIndex];
		String controllerName = requestUriBits[controllerNameIndex];
		String actionMethodName = requestUriBits[actionMethodNameIndex];
		String actionUrl = "/" + controllerTypeName + "/" + controllerName + "/" + actionMethodName;

		// 데이터 추가 인터셉터 끝

		boolean isLogined = false;
		int loginedMemberNum = 0;
		Member loginedMember = null;

		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			isLogined = true;
			loginedMemberNum = (int) session.getAttribute("loginedMemberNum");
			loginedMember = Container.memberService.getMemberByLoginNum(loginedMemberNum);
		}

		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberNum", loginedMemberNum);
		request.setAttribute("loginedMember", loginedMember);

		String currentUrl = request.getRequestURI();

		if (request.getQueryString() != null) {
			currentUrl += "?" + request.getQueryString();
		}

		String encodedCurrentUrl = Util.getUrlEncoded(currentUrl);

		request.setAttribute("currentUrl", currentUrl);
		request.setAttribute("encodedCurrentUrl", encodedCurrentUrl);
		
		String siteName = "휴게소";
		String subject = "휴게소 모음 사이트";
		String siteDescription = "휴게소 정보를 모아두는 사이트 입니다..";
		String siteDomain = "comu.baobab612.com";
		String siteMainUrl = "https://comu.baobab612.com";
		String currentDate = Util.getNowDateStr().replace("", "T");
		
		
		
		request.setAttribute("site-name", siteName);
		request.setAttribute("site-subject", subject);
		request.setAttribute("current-date", currentDate);
		request.setAttribute("site-description", siteDescription);
		request.setAttribute("site-main-url", siteMainUrl);
		
		Map<String, Object> param = Util.getParamMap(request);
		String paramJson = Util.getJsonText(param);

		request.setAttribute("paramMap", param);
		request.setAttribute("paramJson", paramJson);
		// 데이터 추가 인터셉터 끝

		// 로그인 필요 필터링 인터셉터 시작

		List<String> needToLoginactionUrls = new ArrayList<>();

		needToLoginactionUrls.add("/usr/article/doLogout");
		needToLoginactionUrls.add("/usr/article/write");
		needToLoginactionUrls.add("/usr/article/doWrite");
		needToLoginactionUrls.add("/usr/article/modify");
		needToLoginactionUrls.add("/usr/article/doModify");
		needToLoginactionUrls.add("/usr/article/doDelete");
		needToLoginactionUrls.add("/usr/member/whoami");
		needToLoginactionUrls.add("/usr/member/modifyAccount");
		if (needToLoginactionUrls.contains(actionUrl)) {
			if ((boolean) request.getAttribute("isLogined") == false) {
				request.setAttribute("alertMsg", "로그인 후 이용해주세요.");
				request.setAttribute("replaceUrl", "../member/login?afterLoginUrl=" + encodedCurrentUrl);

				RequestDispatcher rd = request.getRequestDispatcher(getJspDirPath() + "/common/redirect.jsp");
				rd.forward(request, response);
			}
		}

		List<String> needToLogoutactionUrls = new ArrayList<>();

		needToLogoutactionUrls.add("/usr/member/join");
		needToLogoutactionUrls.add("/usr/member/doJoin");
		needToLogoutactionUrls.add("/usr/member/login");
		needToLogoutactionUrls.add("/usr/member/doLogin");
		needToLogoutactionUrls.add("/usr/member/findLoginId");
		needToLogoutactionUrls.add("/usr/member/doFindLoginId");
		needToLogoutactionUrls.add("/usr/member/findLoginPw");
		needToLogoutactionUrls.add("/usr/member/doFindLoginPw");
		if (needToLogoutactionUrls.contains(actionUrl)) {
			if ((boolean) request.getAttribute("isLogined")) {
				request.setAttribute("alertMsg", "로그아웃 후 이용해주세요.");
				request.setAttribute("historyBack", true);

				RequestDispatcher rd = request.getRequestDispatcher(getJspDirPath() + "/common/redirect.jsp");
				rd.forward(request, response);
			}
		}

		// 로그인 필요 필터링 인터셉터 끝

		Map<String, Object> rs = new HashMap<>();
		rs.put("controllerName", controllerName);
		rs.put("actionMethodName", actionMethodName);
		return rs;
	}

	protected abstract String doAction(HttpServletRequest request, HttpServletResponse response, String ControllerName,
			String actionMethodName);

	private void doAfterAction(HttpServletRequest request, HttpServletResponse response, String jspPath)
			throws ServletException, IOException {
		MysqlUtil.closeConnection();

		RequestDispatcher rd = request.getRequestDispatcher(getJspDirPath() + "/" + jspPath + ".jsp");
		rd.forward(request, response);
	}

	private String getJspDirPath() {
		return "/WEB-INF/jsp";
	}
}
