package com.sbs.example.jspCommunity;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/99dan")	
public class jspCommunity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (request.getParameter("dan") == null) {
			response.getWriter().append("dan을 입력해주세요.");
			return;
		}
		int dan = Integer.parseInt(request.getParameter("dan"));
		int limit = 9;
		
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		response.getWriter().append("<h1>" + String.format("구구단 %d단", dan) + "</h1>");
		
		for(int i=1; i<=limit; i++) {
			response.getWriter().append("<div>" + String.format("%d * %d = %d",dan, i , dan*i) + "</div>");
		}
	}
}
