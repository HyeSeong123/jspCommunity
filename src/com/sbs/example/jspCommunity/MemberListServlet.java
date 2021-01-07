package com.sbs.example.jspCommunity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.SecSql;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/usr/member/list")	
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspCommunity");
		
		List<Map<String,Object>> memberMapList = MysqlUtil.selectRows(new SecSql().append("SELECT * FROM member ORDER BY memberNum DESC") );
		
		System.out.println(memberMapList);
		
		request.setAttribute("memberMapList", memberMapList);
		
		request.getRequestDispatcher("/jsp/usr/member/list.jsp").forward(request, response);
		MysqlUtil.closeConnection();
	}
}
