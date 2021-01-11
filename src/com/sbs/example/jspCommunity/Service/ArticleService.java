package com.sbs.example.jspCommunity.Service;

import java.util.List;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.ArticleDao;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Member;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticlesByBoard(int boardNum) {
		return articleDao.getForPringArticleByBoard(boardNum);
	}

	public Article getForPrintArticle(int num) {
		return articleDao.getForPrintArticle(num);
	}

	public int doAdd(int memberNum, int boardNum, String title, String body) {
		return articleDao.doAdd(memberNum,boardNum,title,body);
	}

}
