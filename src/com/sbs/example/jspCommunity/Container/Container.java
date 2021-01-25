package com.sbs.example.jspCommunity.Container;

import com.sbs.example.jspCommunity.Controller.AdmMemberController;
import com.sbs.example.jspCommunity.Controller.ArticleController;
import com.sbs.example.jspCommunity.Controller.UsrHomeController;
import com.sbs.example.jspCommunity.Controller.usrMemberController;
import com.sbs.example.jspCommunity.Dao.ArticleDao;
import com.sbs.example.jspCommunity.Dao.MemberDao;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Service.EmailService;
import com.sbs.example.jspCommunity.Service.MemberService;

public class Container {
	
	public static ArticleDao articleDao;
	public static ArticleService articleService;
	public static ArticleController articleController;
	
	public static EmailService emailService;
	public static MemberDao memberDao;
	public static MemberService memberService;
	
	public static usrMemberController usrMemberController;
	public static AdmMemberController admMemberController;
	public static UsrHomeController homeController;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		emailService = new EmailService();
		memberService = new MemberService();
		articleService = new ArticleService();
		
		admMemberController = new AdmMemberController();
		usrMemberController = new usrMemberController();
		articleController = new ArticleController();
		homeController = new UsrHomeController();
	}
}
