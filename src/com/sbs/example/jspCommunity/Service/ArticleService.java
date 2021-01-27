package com.sbs.example.jspCommunity.Service;

import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.ArticleDao;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public Article getForPrintArticle(int num) {
		return articleDao.getForPrintArticle(num);
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

	public List<Article> getForPrintArticlesByBoard(int boardNum, int limitStart, int limitCount, String searchKeyword, String searchKeywordType) {
		return articleDao.getForPrintArticlesByBoard(boardNum, limitStart,limitCount, searchKeyword, searchKeywordType);
	}

}
