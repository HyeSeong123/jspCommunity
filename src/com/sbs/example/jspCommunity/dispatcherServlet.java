package com.sbs.example.jspCommunity;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.ha.ReplicationMySQLConnection;
import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Controller.ArticleController;
import com.sbs.example.jspCommunity.Controller.MemberController;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Util.MysqlUtil;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/usr/*")
public class dispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String requestUri = request.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		if (requestUriBits.length < 5) {
			response.getWriter().append("올바른 요청이 아닙니다.");
			return;
		}
		System.out.println(requestUri);

		String ControllerName = requestUriBits[3];
		String actionMethodName = requestUriBits[4];

		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspCommunity");

		String jspPath = null;

		if (ControllerName.equals("member")) {
			MemberController memberController = Container.memberController;
			if (actionMethodName.equals("list")) {
				jspPath = memberController.showList(request, response);
			}
		} else if (ControllerName.equals("article")) {
			ArticleController articleController = Container.articleController;
			if (actionMethodName.equals("list")) {
				jspPath = articleController.showList(request, response);
			} else if (actionMethodName.equals("detail")) {
				jspPath = articleController.showDetail(request, response);
			} else if (actionMethodName.equals("add")) {
				jspPath = articleController.doAdd(request, response);
			}
		}

		MysqlUtil.closeConnection();

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(request, response);
	}

}
