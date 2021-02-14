package com.sbs.example.jspCommunity.Controller;

import javax.servlet.http.HttpServletRequest;

import com.sbs.example.jspCommunity.Dto.ResultData;

public class Controller {
	protected String msgAndBack(HttpServletRequest requset, String msg) {
		requset.setAttribute("alertMsg", msg);
		requset.setAttribute("historyBack", true);
		return "common/redirect";
	}
	protected String msgAndReplace(HttpServletRequest request, String msg, String replaceUrl) {
		request.setAttribute("alertMsg", msg);
		request.setAttribute("replaceUrl", replaceUrl);
		return "common/redirect";
	}
	protected String json(HttpServletRequest request , ResultData resultData) {
		request.setAttribute("data", resultData);
		System.out.println("resultData= " + resultData);
		return "common/json";
	}
}
