package com.sbs.example.jspCommunity.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Member;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Service.MemberService;

public class UsrHomeController {

	private MemberService memberService;
	private ArticleService articleService;

	public UsrHomeController() {
		memberService = Container.memberService;
	}

	public String showMain(HttpServletRequest request, HttpServletResponse response) {
		return "usr/home/main";
	}
}
