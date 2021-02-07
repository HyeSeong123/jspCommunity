package com.sbs.example.jspCommunity.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Service.LikeService;
import com.sbs.example.jspCommunity.Util.Util;

public class UsrLikeController extends Controller {
	private LikeService likeService;

	public UsrLikeController() {
		likeService = Container.likeService;
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
		
		System.out.println("actorId= " + actorId);
		
		return msgAndReplace(request, "좋아요 처리되었습니다.", request.getParameter("redirectUrl"));
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
