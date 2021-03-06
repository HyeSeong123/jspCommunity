package com.sbs.example.jspCommunity.Service;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.LikeDao;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Like;
import com.sbs.example.jspCommunity.Dto.Member;

public class LikeService {

	LikeDao likeDao;

	public LikeService() {
		likeDao = Container.likeDao;
	}

	public int setLikePoint(String relTypeCode, int relId, int actorId, int point) {
		if (point == 0) {
			return likeDao.removePoint(relTypeCode, relId, actorId);
		} else {
			return likeDao.setLikePoint(relTypeCode, relId, actorId, point);
		}
	}
	public int getPoint(String relTypeCode, int relId, int actorNum) {
		return likeDao.getPoint(relTypeCode, relId, actorNum);
	}
	public boolean actorCanLike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getNum(), actor.getMemberNum()) == 0;
	}
	
	public boolean actorCanCancelLike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getNum(), actor.getMemberNum()) > 0;
	}

	public boolean actorCanDislike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getNum(), actor.getMemberNum()) == 0;
	}

	public boolean actorCanCancelDislike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getNum(), actor.getMemberNum()) < 0;
	}
	public Like likeforPrint(int actorNum) {
		return likeDao.likeforPrint(actorNum);
	}

	public int CanLikeArticle(String relTypeCode, int loginedMemberNum, int num) {
		return likeDao.CanLikeArticle(relTypeCode,loginedMemberNum,num);
	}
}
