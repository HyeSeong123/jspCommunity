package com.sbs.example.jspCommunity.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Board;
import com.sbs.example.jspCommunity.Service.ArticleService;

public class ArticleController {

	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest request, HttpServletResponse response) {

		int boardNum = Integer.parseInt(request.getParameter("boardNum"));

		Board board = articleService.getBoardNum(boardNum);

		request.setAttribute("board", board);

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

	public String showWrite(HttpServletRequest request, HttpServletResponse response) {
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));

		Board board = articleService.getBoardNum(boardNum);

		request.setAttribute("board", board);

		return "usr/article/write";
	}

	public String doWrite(HttpServletRequest request, HttpServletResponse response) {
		
		int memberNum = (int) request.getAttribute("loginedMemberNum");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		Board board = articleService.getBoardNum(boardNum);
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		Map<String, Object> writeArgs = new HashMap<>();

		writeArgs.put("memberNum", memberNum);
		writeArgs.put("boardNum", boardNum);
		writeArgs.put("title", title);
		writeArgs.put("body", body);

		int newArticleNum = articleService.doWrite(writeArgs);
		request.setAttribute("board", board);
		request.setAttribute("alertMsg", newArticleNum + "번 게시물이 생성되었습니다..");
		request.setAttribute("replaceUrl", String.format("detail?num=%d", newArticleNum));
		return "common/redirect";
	}

	public String showModify(HttpServletRequest request, HttpServletResponse response) {

		int articleNum = Integer.parseInt(request.getParameter("num"));

		Article article = articleService.getForPrintArticle(articleNum);

		request.setAttribute("article", article);

		return "usr/article/modify";
	}

	public String doModify(HttpServletRequest request, HttpServletResponse response) {

		int articleNum = Integer.parseInt(request.getParameter("num"));
		Article article = articleService.getForPrintArticle(articleNum);
		if (article == null) {
			request.setAttribute("alertMsg", articleNum + "번 게시물은 존재하지 않습니다.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int memberNum = (int)request.getAttribute("loginedMemberNum");

		if (article.getMemberNum() != memberNum) {
			request.setAttribute("alertMsg", articleNum + "번 글의 수정 권한이 없습니다.");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		Map<String, Object> modifyArgs = new HashMap<>();

		modifyArgs.put("num", articleNum);
		modifyArgs.put("title", title);
		modifyArgs.put("body", body);

		articleService.doModify(modifyArgs);

		request.setAttribute("alertMsg", articleNum + "번 게시물이 변경되었습니다..");
		request.setAttribute("replaceUrl", String.format("detail?num=%d", articleNum));
		return "common/redirect";
	}

	public String doDelete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int articleNum = Integer.parseInt(request.getParameter("num"));

		Article article = articleService.getForPrintArticle(articleNum);

		int memberNum = (int)session.getAttribute("loginedMemberNum");

		if (article.getMemberNum() != memberNum) {

			request.setAttribute("alertMsg", articleNum + "번 글의 삭제 권한이 없습니다..");
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		articleService.doDelete(articleNum);

		request.setAttribute("alertMsg", articleNum + "번 게시물이 삭제되었습니다..");
		request.setAttribute("replaceUrl", String.format("list?boardNum=%d", article.getBoardNum()));
		return "common/redirect";
	}
}
