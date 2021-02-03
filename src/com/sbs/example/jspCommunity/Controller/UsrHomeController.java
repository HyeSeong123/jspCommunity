package com.sbs.example.jspCommunity.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Attr;
import com.sbs.example.jspCommunity.Service.AttrService;

public class UsrHomeController {

	private AttrService attrService;

	public UsrHomeController() {
		attrService = Container.attrService;
	}

	public String showMain(HttpServletRequest request, HttpServletResponse response) {
		return "usr/home/main";
	}
}
