package com.sbs.example.jspCommunity.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Like;
import com.sbs.example.jspCommunity.Dto.ResultData;
import com.sbs.example.jspCommunity.Service.ArticleService;
import com.sbs.example.jspCommunity.Service.LikeService;
import com.sbs.example.jspCommunity.Service.MemberService;
import com.sbs.example.jspCommunity.Util.Util;

public class UsrLikeController extends Controller {
	private LikeService likeService;
	private ArticleService articleService;
	private MemberService memberService;

	public UsrLikeController() {
		likeService = Container.likeService;
		memberService = Container.memberService;
		articleService = Container.articleService;

	}

	public String doLike(HttpServletRequest request, HttpServletResponse response) {
		String relTypeCode = request.getParameter("relTypeCode");

		if (relTypeCode == null) {
			return msgAndBack(request, "관련데이터코드를 입력해주세요");
		}

		int relId = Util.getAsInt(request.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(request, "관련데이터번호를 입력해주세요");
		}

		int actorId = (int) request.getAttribute("loginedMemberNum");

		likeService.setLikePoint(relTypeCode, relId, actorId, 1);

		return msgAndReplace(request, "좋아요 처리되었습니다.", request.getParameter("redirectUrl"));
	}

	public String getLikeCount(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> rs = new HashMap<>();

		int num = Util.getAsInt(request.getParameter("num"), 0);
		int actorId = (int) request.getAttribute("loginedMemberNum");

		Map<String, Object> articleLikeAvailableRs = articleService.getArticleLikeAvailable(num, actorId);
		String resultCode = "";
		String msg = "";

		if (((String) articleLikeAvailableRs.get("resultCode")).startsWith("F-")) {
			resultCode = (String) articleLikeAvailableRs.get("resultCode");
			msg = (String) articleLikeAvailableRs.get("msg");

			return json(request, new ResultData(resultCode, msg));

		}
		String relTypeCode = request.getParameter("relTypeCode");

		int relId = Util.getAsInt(request.getParameter("relId"), 0);

		System.out.println("relTypeCode= " + relTypeCode);
		System.out.println("relId= " + relId);

		Like like = likeService.likeforPrint(actorId);
		
		int canLike = 0;
		
		if (like == null) {
			likeService.setLikePoint(relTypeCode, num, actorId, 1);
			canLike = 1;
		}
		
		request.setAttribute("canLike", canLike);
		
		Article article = articleService.getForPrintArticle(num);

		int likePoint = article.getExtra__likeOnlyPoint();

		resultCode = "S-1";
		msg = "전송";

		return json(request, new ResultData(resultCode, msg, likePoint));
	}

	public String doCancelLike(HttpServletRequest request, HttpServletResponse response) {
		String relTypeCode = request.getParameter("relTypeCode");

		if (relTypeCode == null) {
			return msgAndBack(request, "관련데이터코드를 입력해주세요");
		}

		int relId = Util.getAsInt(request.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(request, "관련데이터번호를 입력해주세요");
		}

		int actorId = (int) request.getAttribute("loginedMemberNum");

		likeService.setLikePoint(relTypeCode, relId, actorId, 0);

		return msgAndReplace(request, "좋아요가 취소 처리됐습니다.", request.getParameter("redirectUrl"));
	}

	public String doDislike(HttpServletRequest request, HttpServletResponse response) {
		String relTypeCode = request.getParameter("relTypeCode");

		if (relTypeCode == null) {
			return msgAndBack(request, "관련데이터코드를 입력해주세요");
		}

		int relId = Util.getAsInt(request.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(request, "관련데이터번호를 입력해주세요");
		}

		int actorId = (int) request.getAttribute("loginedMemberNum");

		likeService.setLikePoint(relTypeCode, relId, actorId, -1);

		return msgAndReplace(request, "싫어요 처리되었습니다.", request.getParameter("redirectUrl"));
	}

	public String doCancelDislike(HttpServletRequest request, HttpServletResponse response) {
		String relTypeCode = request.getParameter("relTypeCode");

		if (relTypeCode == null) {
			return msgAndBack(request, "관련데이터코드를 입력해주세요");
		}

		int relId = Util.getAsInt(request.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(request, "관련데이터번호를 입력해주세요");
		}

		int actorId = (int) request.getAttribute("loginedMemberNum");

		likeService.setLikePoint(relTypeCode, relId, actorId, 0);

		return msgAndReplace(request, "싫어요가 취소 처리됐습니다.", request.getParameter("redirectUrl"));
	}

}
