package com.sbs.example.jspCommunity.Container;

import com.sbs.example.jspCommunity.Controller.AdmMemberController;
import com.sbs.example.jspCommunity.Controller.ArticleController;
import com.sbs.example.jspCommunity.Controller.UsrHomeController;
import com.sbs.example.jspCommunity.Controller.UsrLikeController;
import com.sbs.example.jspCommunity.Controller.usrMemberController;
import com.sbs.example.jspCommunity.Dao.ArticleDao;
import com.sbs.example.jspCommunity.Dao.AttrDao;
import com.sbs.example.jspCommunity.Dao.LikeDao;
import com.sbs.example.jspCommunity.Dao.MemberDao;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Service.AttrService;
import com.sbs.example.jspCommunity.Service.EmailService;
import com.sbs.example.jspCommunity.Service.LikeService;
import com.sbs.example.jspCommunity.Service.MemberService;

public class Container {
	
	public static ArticleDao articleDao;
	public static ArticleService articleService;
	public static ArticleController articleController;
	
	public static EmailService emailService;
	public static AttrDao attrDao;
	public static MemberDao memberDao;
	
	public static MemberService memberService;
	public static AttrService attrService;
	public static usrMemberController usrMemberController;
	public static AdmMemberController admMemberController;
	public static UsrHomeController homeController;
	
	public static LikeDao likeDao;
	public static LikeService likeService;
	public static UsrLikeController usrLikeController;
	
	
	
	
	static {
		attrDao = new AttrDao();
		likeDao = new LikeDao();
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		attrService = new AttrService();
		emailService = new EmailService();
		likeService = new LikeService();
		memberService = new MemberService();
		articleService = new ArticleService();
		
		usrLikeController = new UsrLikeController(); 
		admMemberController = new AdmMemberController();
		usrMemberController = new usrMemberController();
		articleController = new ArticleController();
		homeController = new UsrHomeController();
	}
}
