package com.sbs.example.jspCommunity.Container;

import com.sbs.example.jspCommunity.Controller.ArticleController;
import com.sbs.example.jspCommunity.Controller.MemberController;
import com.sbs.example.jspCommunity.Dao.ArticleDao;
import com.sbs.example.jspCommunity.Dao.MemberDao;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Service.MemberService;

public class Container {
	
	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	public static MemberController memberController;
	public static ArticleController articleController;
	
	static {
		memberDao =  new MemberDao();
		articleDao = new ArticleDao();
		articleService = new ArticleService();
		memberService = new MemberService();
		memberController = new MemberController();
		articleController = new ArticleController();
	}
}
