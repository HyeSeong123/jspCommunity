package com.sbs.example.jspCommunity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Controller.ArticleController;
import com.sbs.example.jspCommunity.Controller.UsrHomeController;
import com.sbs.example.jspCommunity.Controller.UsrLikeController;
import com.sbs.example.jspCommunity.Controller.usrMemberController;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/usr/*")
public class UsrdipatcherServlet extends dispatcherServlet {
	protected String doAction(HttpServletRequest request, HttpServletResponse response, String ControllerName,
			String actionMethodName) {

		String jspPath = null;

		if (ControllerName.equals("member")) {
			usrMemberController memberController = Container.usrMemberController;
			if (actionMethodName.equals("list")) {
				jspPath = memberController.showList(request, response);
			} else if (actionMethodName.equals("join")) {
				jspPath = memberController.showJoin(request, response);
			} else if (actionMethodName.equals("doJoin")) {
				jspPath = memberController.doJoin(request, response);
			} else if (actionMethodName.equals("login")) {
				jspPath = memberController.showLogin(request, response);
			} else if (actionMethodName.equals("doLogin")) {
				jspPath = memberController.doLogin(request, response);
			} else if (actionMethodName.equals("doLogout")) {
				jspPath = memberController.doLogout(request, response);
			} else if (actionMethodName.equals("getLoginIdDup")) {
				jspPath = memberController.getLoginIdDup(request, response);
			} else if (actionMethodName.equals("findLoginId")) {
				jspPath = memberController.showFindLoginId(request, response);
			} else if (actionMethodName.equals("doFindLoginId")) {
				jspPath = memberController.doFindLoginId(request, response);
			} else if (actionMethodName.equals("findLoginPw")) {
				jspPath = memberController.showFindLoginPw(request, response);
			} else if (actionMethodName.equals("doFindLoginPw")) {
				jspPath = memberController.doFindLoginPw(request, response);
			} else if (actionMethodName.equals("whoami")) {
				jspPath = memberController.showWhoAmI(request, response);
			} else if (actionMethodName.equals("modifyAccount")) {
				jspPath = memberController.showModifyAccount(request, response);
			} else if (actionMethodName.equals("doModifyAccount")) {
				jspPath = memberController.doModifyAccount(request, response);
			}
		} else if (ControllerName.equals("article")) {
			ArticleController articleController = Container.articleController;
			if (actionMethodName.equals("list")) {
				jspPath = articleController.showList(request, response);
			} else if (actionMethodName.equals("detail")) {
				jspPath = articleController.showDetail(request, response);
			} else if (actionMethodName.equals("write")) {
				jspPath = articleController.showWrite(request, response);
			} else if (actionMethodName.equals("doWrite")) {
				jspPath = articleController.doWrite(request, response);
			} else if (actionMethodName.equals("modify")) {
				jspPath = articleController.showModify(request, response);
			} else if (actionMethodName.equals("doModify")) {
				jspPath = articleController.doModify(request, response);
			} else if (actionMethodName.equals("doDelete")) {
				jspPath = articleController.doDelete(request, response);
			}

		} else if (ControllerName.equals("like")) {
			UsrLikeController usrlikeController = Container.usrLikeController;
			if (actionMethodName.equals("doLike")) {
				jspPath = usrlikeController.doLike(request, response);
			} else if (actionMethodName.equals("doCancelLike")) {
				jspPath = usrlikeController.doCancelLike(request, response);
			} else if (actionMethodName.equals("doDislike")) {
				jspPath = usrlikeController.doDislike(request, response);
			} else if (actionMethodName.equals("doCancelDislike")) {
				jspPath = usrlikeController.doCancelDislike(request, response);
			}
		} else if (ControllerName.equals("reply")) {
			UsrReplyController usrReplyController = Container.usrReplyController;
			if (actionMethodName.equals("doWriteReply")) {
				jspPath = usrReplyController.doWriteReply(request, response);
			} else if (actionMethodName.equals("doModifyReply")) {
				jspPath = usrReplyController.doModifyReply(request, response);
			} else if (actionMethodName.equals("doDeleteReply")) {
				jspPath = usrReplyController.doDeleteReply(request, response);
			}
		} else if (ControllerName.equals("home")) {
			UsrHomeController homeController = Container.homeController;
			if (actionMethodName.equals("main")) {
				jspPath = homeController.showMain(request, response);
			}
		}
		return jspPath;
	}
}
