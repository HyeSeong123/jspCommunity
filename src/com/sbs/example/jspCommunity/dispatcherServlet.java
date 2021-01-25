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
		String jspPath = doAction(request, response, (String) doBeforeActionRs.get("ControllerName"),
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

		String requestUri = request.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		if (requestUriBits.length < 5) {
			response.getWriter().append("올바른 요청이 아닙니다.");
			return null;
		}
		System.out.println(requestUri);
		
		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspCommunity");
		
		String ControllerTypeName = requestUriBits[2];
		String ControllerName = requestUriBits[3];
		String actionMethodName = requestUriBits[4];
		
		String actionUrl = "/" + ControllerTypeName + "/" + ControllerName + "/" + actionMethodName;
		
 		
		
		// 데이터 추가 인터셉터 끝
		
		boolean isLogined = false;
		int loginedMemberNum = 0;
		Member loginedMember = null;

		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberNum") != null) {
			isLogined = true;
			loginedMemberNum= (int)session.getAttribute("loginedMemberNum");
			loginedMember = Container.memberService.getMemberByLoginNum(loginedMemberNum);
		}

		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberNum", loginedMemberNum);
		request.setAttribute("loginedMember", loginedMember);

		// 데이터 추가 인터셉터 끝
		
		// 로그인 필요 필터링 인터셉터 시작
		
		List<String> needToLoginactionUrls = new ArrayList<>();
		
		needToLoginactionUrls.add("/usr/article/doLogout");
		needToLoginactionUrls.add("/usr/article/write");
		needToLoginactionUrls.add("/usr/article/doWrite");
		needToLoginactionUrls.add("/usr/article/modify");
		needToLoginactionUrls.add("/usr/article/doModify");
		needToLoginactionUrls.add("/usr/article/doDelete");
		
		if(needToLoginactionUrls.contains(actionUrl) ) {
			if ((boolean) request.getAttribute("isLogined")) {
				request.setAttribute("alertMsg", "로그인 후 이용해주세요.");
				request.setAttribute("replaceUrl", "../member/login");
				
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/common/redirect.jsp");
				rd.forward(request, response);
			}
		}
		
		
		// 로그인 필요 필터링 인터셉터 끝
		
		Map<String, Object> rs = new HashMap<>();
		rs.put("ControllerName", ControllerName);
		rs.put("actionMethodName", actionMethodName);

		return rs;
	}

	protected abstract String doAction(HttpServletRequest request, HttpServletResponse response, String ControllerName,
			String actionMethodName);

	private void doAfterAction(HttpServletRequest request, HttpServletResponse response, String jspPath)
			throws ServletException, IOException {
		MysqlUtil.closeConnection();

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(request, response);
	}
}
