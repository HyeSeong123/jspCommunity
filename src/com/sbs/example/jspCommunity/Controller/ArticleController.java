package com.sbs.example.jspCommunity.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Service.ArticleService;

public class ArticleController {

	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest request, HttpServletResponse response) {

		int boardNum = Integer.parseInt(request.getParameter("boardNum"));

		List<Article> articles = articleService.getForPrintArticlesByBoard(boardNum);

		request.setAttribute("articles", articles);
		return "usr/article/list";
	}

	public String showDetail(HttpServletRequest request, HttpServletResponse response) {

		int num = Integer.parseInt(request.getParameter("num"));

		Article article = articleService.getForPrintArticle(num);

		if (article == null) {
			request.setAttribute("alertMsg", num + "번 게시물은 존재하지 않습니다.");
			request.setAttribute("historyBack", true);
			return "common/redirect";

		}
		request.setAttribute("article", article);
		return "usr/article/detail";
	}

	public String doAdd(HttpServletRequest request, HttpServletResponse response) {

		int memberNum = Integer.parseInt(request.getParameter("memberNum"));
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		int articleNum = articleService.doAdd(memberNum, boardNum, title, body);

		request.setAttribute("articleNum", articleNum);
		return "usr/article/add";
	}
}
