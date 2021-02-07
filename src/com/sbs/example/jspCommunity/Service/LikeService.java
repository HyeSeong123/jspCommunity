package com.sbs.example.jspCommunity.Service;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.LikeDao;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Member;

public class LikeService {

	LikeDao likeDao;

	public LikeService() {
		likeDao = Container.likeDao;
	}

	public void setLikePoint(String relTypeCode, int relId, int actorId, int point) {
		if (point == 0) {
			likeDao.removePoint(relTypeCode, relId, actorId);
		} else {
			likeDao.setLikePoint(relTypeCode, relId, actorId, point);
		}

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
}
