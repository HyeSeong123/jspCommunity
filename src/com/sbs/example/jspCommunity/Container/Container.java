package com.sbs.example.jspCommunity.Container;

import com.sbs.example.jspCommunity.Controller.AdmMemberController;
import com.sbs.example.jspCommunity.Controller.ArticleController;
import com.sbs.example.jspCommunity.Controller.UsrHomeController;
import com.sbs.example.jspCommunity.Controller.usrMemberController;
import com.sbs.example.jspCommunity.Dao.ArticleDao;
import com.sbs.example.jspCommunity.Dao.MemberDao;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Service.MemberService;

public class Container {

	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	public static usrMemberController usrMemberController;
	public static AdmMemberController admMemberController;
	public static ArticleController articleController;
	public static UsrHomeController homeController;

	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		articleService = new ArticleService();
		memberService = new MemberService();
		homeController = new UsrHomeController();
		usrMemberController = new usrMemberController();
		admMemberController = new AdmMemberController();
		articleController = new ArticleController();
	}
}
