package com.sbs.example.jspCommunity.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Attr;
import com.sbs.example.jspCommunity.Dto.Board;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Service.AttrService;
import com.sbs.example.jspCommunity.Util.Util;

public class ArticleController extends Controller {

	private ArticleService articleService;
	private AttrService attrService;

	public ArticleController() {
		attrService = Container.attrService;
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest request, HttpServletResponse response) {
		String searchKeyword = request.getParameter("searchKeyword");
		String searchKeywordType = request.getParameter("searchKeywordType");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));

		int memberNum = (int) request.getAttribute("loginedMemberNum");

		if (memberNum != 0) {
			Attr attr = attrService.getAttr("member", memberNum, "extra", "tempPassword");

			request.setAttribute("tempPassword", attr.getValue());
		}

		Board board = articleService.getBoardNum(boardNum);

		request.setAttribute("board", board);
		int totalCount = articleService.getArticlesCountByBoardNum(boardNum, searchKeyword, searchKeywordType);

		int page = Util.getAsInt(request.getParameter("page"), 1);
		int itemsInAPage = 30;
		int limitStart = (page - 1) * itemsInAPage;

		List<Article> articles = articleService.getForPrintArticlesByBoard(boardNum, limitStart, itemsInAPage,
				searchKeyword, searchKeywordType);

		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);

		int pageBoxSize = 10;

		int previousPageBoxesCount = (page - 1) / pageBoxSize;
		int pageBoxStartPage = pageBoxSize * previousPageBoxesCount + 1;
		int pageBoxEndPage = pageBoxStartPage + pageBoxSize - 1;

		if (pageBoxEndPage > totalPage) {
			pageBoxEndPage = totalPage;
		}

		int pageBoxStartBeforePage = pageBoxStartPage - 1;
		if (pageBoxStartBeforePage < 1) {
			pageBoxStartBeforePage = 1;
		}

		int pageBoxEndAfterPage = pageBoxEndPage + 1;
		if (pageBoxEndAfterPage > totalPage) {
			pageBoxEndAfterPage = totalPage;
		}

		boolean pageBoxStartBeforeBtnNeedToShow = pageBoxStartBeforePage != pageBoxStartPage;
		boolean pageBoxEndAfterBtnNeedToShow = pageBoxEndAfterPage != pageBoxEndPage;

		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("articles", articles);
		request.setAttribute("pageBoxStartBeforeBtnNeedToShow", pageBoxStartBeforeBtnNeedToShow);
		request.setAttribute("pageBoxEndAfterBtnNeedToShow", pageBoxEndAfterBtnNeedToShow);
		request.setAttribute("pageBoxStartBeforePage", pageBoxStartBeforePage);
		request.setAttribute("pageBoxEndAfterPage", pageBoxEndAfterPage);
		request.setAttribute("pageBoxEndPage", pageBoxEndPage);
		request.setAttribute("pageBoxStartPage", pageBoxStartPage);

		return "usr/article/list";
	}

	public String showDetail(HttpServletRequest request, HttpServletResponse response) {

		int num = Integer.parseInt(request.getParameter("num"));

		Article article = articleService.getForPrintArticle(num);

		int memberNum = (int) request.getAttribute("loginedMemberNum");

		if (memberNum != 0) {
			Attr attr = attrService.getAttr("member", memberNum, "extra", "tempPassword");

			request.setAttribute("tempPassword", attr.getValue());
		}

		if (article == null) {
			return msgAndBack(request, num + "번 게시물은 존재하지 않습니다.");

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
		
		int boardNum = Util.getAsInt(request.getParameter("boardNum"),0);
		
		if(boardNum ==0) {
			return msgAndBack(request, "게시판 번호를 입력해주세요.");
		}
		Board board = articleService.getBoardNum(boardNum);
		String title = request.getParameter("title");
		if(Util.isEmpty(title)) {
			return msgAndBack(request, "제목을 입력해주세요.");
		}
		String body = request.getParameter("body");
		if(Util.isEmpty(body)) {
			return msgAndBack(request, "내용을 입력해주세요.");
		}
		Map<String, Object> writeArgs = new HashMap<>();

		writeArgs.put("memberNum", memberNum);
		writeArgs.put("boardNum", boardNum);
		writeArgs.put("title", title);
		writeArgs.put("body", body);

		int newArticleNum = articleService.doWrite(writeArgs);
		request.setAttribute("board", board);

		return msgAndBack(request, newArticleNum + "번 게시물이 생성되었습니다.");
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
			return msgAndBack(request, articleNum + "번 게시물은 존재하지 않습니다.");
		}

		int memberNum = (int) request.getAttribute("loginedMemberNum");

		if (article.getMemberNum() != memberNum) {
			return msgAndBack(request, articleNum + "번 글의 수정 권한이 없습니다.");
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

		int memberNum = (int) session.getAttribute("loginedMemberNum");

		if (article.getMemberNum() != memberNum) {
			return msgAndBack(request, articleNum + "번 글의 삭제 권한이 없습니다.");
		}

		articleService.doDelete(articleNum);

		request.setAttribute("alertMsg", articleNum + "번 게시물이 삭제되었습니다..");
		request.setAttribute("replaceUrl", String.format("list?boardNum=%d", article.getBoardNum()));
		return "common/redirect";
	}
}
