package com.sbs.example.jspCommunity.Service;

import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.ArticleDao;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Board;
import com.sbs.example.jspCommunity.Dto.Member;

public class ArticleService {
	private ArticleDao articleDao;
	private LikeService likeService;

	public ArticleService() {
		articleDao = Container.articleDao;
		likeService = Container.likeService;
	}

	public Article getForPrintArticle(int num) {
		return getForPrintArticle(num, null);
	}

	public Article getForPrintArticle(int num, Member actor) {
		Article article = articleDao.getForPrintArticle(num);

		if (article == null) {
			return null;
		}
		if (actor != null) {
			updateInfoForPrint(article, actor);
		}

		return article;
	}

	private void updateInfoForPrint(Article article, Member actor) {
		boolean actorCanLike = likeService.actorCanLike(article, actor);
		boolean actorCanCancelLike = likeService.actorCanCancelLike(article, actor);
		boolean actorCanDislike = likeService.actorCanDislike(article, actor);
		boolean actorCanCancelDislike = likeService.actorCanCancelDislike(article, actor);

		article.getExtra().put("actorCanLike", actorCanLike);
		article.getExtra().put("actorCanCancelLike", actorCanCancelLike);
		article.getExtra().put("actorCanDislike", actorCanDislike);
		article.getExtra().put("actorCanCancelDislike", actorCanCancelDislike);
	}

	public Board getBoardNum(int boardNum) {
		return articleDao.getBoardNum(boardNum);
	}

	public int doWrite(Map<String, Object> writeArgs) {
		return articleDao.doWrite(writeArgs);
	}

	public int doModify(Map<String, Object> modifyArgs) {
		return articleDao.doModify(modifyArgs);
	}

	public int doDelete(int articleNum) {
		return articleDao.doDelete(articleNum);
	}

	public int getArticlesCountByBoardNum(int boardNum, String searchKeyword, String searchKeywordType) {
		return articleDao.getArticlesCountByBoardNum(boardNum, searchKeyword, searchKeywordType);
	}

	public List<Article> getForPrintArticlesByBoard(int boardNum, int limitStart, int limitCount, String searchKeyword,
			String searchKeywordType) {
		return articleDao.getForPrintArticlesByBoard(boardNum, limitStart, limitCount, searchKeyword,
				searchKeywordType);
	}

	public int doArticleLike(int loginId, int articleNum) {
		return articleDao.doArticleLike(loginId, articleNum);
	}

	public int doArticleUnLike(int loginId, int articleNum) {
		return articleDao.doArticleUnLike(loginId, articleNum);
	}
}
