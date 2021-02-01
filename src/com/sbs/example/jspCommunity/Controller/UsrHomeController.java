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
		int memberNum = (int) request.getAttribute("loginedMemberNum");

		System.out.printf("loginedId=%d\n", memberNum);

		if (memberNum != 0) {
			Attr attr = attrService.getAttr("member", memberNum, "extra", "tempPassword");

			request.setAttribute("tempPassword", attr.getValue());
		}
		return "usr/home/main";
	}
}
