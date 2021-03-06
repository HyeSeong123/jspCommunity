package com.sbs.example.jspCommunity;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Controller.Controller;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Service.ReplyService;
import com.sbs.example.jspCommunity.Util.Util;

public class UsrReplyController extends Controller {

	private ReplyService replyService;
	private ArticleService articleService;

	public UsrReplyController() {
		replyService = Container.replyService;
		articleService = Container.articleService;
	}

	public String doWriteReply(HttpServletRequest request, HttpServletResponse response) {
		String redirectUrl = request.getParameter("redirectUrl");

		int loginedMemberId = (int) request.getAttribute("loginedMemberNum");

		String relTypeCode = request.getParameter("relTypeCode");

		if (relTypeCode == null) {
			return msgAndBack(request, "관련데이터코드를 입력해주세요");
		}

		int relId = Util.getAsInt(request.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(request, "관련데이터번호를 입력해주세요");
		}

		if (relTypeCode.equals("article")) {
			Article article = articleService.getArticleById(relId);
			System.out.println(article);
			if (article == null) {
				return msgAndBack(request, relId + "번 게시물은 존재하지 않습니다.");
			}
		}

		String body = request.getParameter("body");
		System.out.println("body= " +body);
		
		if (Util.isEmpty(body)) {
			return msgAndBack(request, "내용을 입력해주세요");
		}

		Map<String, Object> writeArgs = new HashMap<>();
		writeArgs.put("memberNum", loginedMemberId);
		writeArgs.put("relId", relId);
		writeArgs.put("relTypeCode", relTypeCode);
		writeArgs.put("body", body);
		
		int newArticleNum = replyService.write(writeArgs);

		redirectUrl = redirectUrl.replace("[NEW_REPLY_ID]", newArticleNum + "");
		
		return msgAndReplace(request, newArticleNum + "번 댓글이 작성되었습니다..", redirectUrl);
	}

	public String doModifyReply(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	public String doDeleteReply(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

}
