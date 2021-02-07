package com.sbs.example.jspCommunity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Controller.usrMemberController;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/adm/*")
public class admDispatcherServlet extends dispatcherServlet {
	protected String doAction(HttpServletRequest request, HttpServletResponse response, String ControllerName,
			String actionMethodName) {
		String jspPath = null;

		if (ControllerName.equals("member")) {
			usrMemberController memberController = Container.usrMemberController;

			if (actionMethodName.equals("list")) {
				jspPath = memberController.showList(request, response);
			}
		}
		return jspPath;
	}
}
