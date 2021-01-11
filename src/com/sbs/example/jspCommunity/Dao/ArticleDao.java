package com.sbs.example.jspCommunity.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.SecSql;

public class ArticleDao {

	public List<Article> doAdd;

	public List<Article> getForPringArticleByBoard(int boardNum) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append("FROM article as A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberNum = M.memberNum");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardNum = B.boardNum");
		if (boardNum != 0) {
			sql.append("WHERE A.boardNum =?", boardNum);
		}
		sql.append("ORDER BY A.num DESC");

		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}

	public Article getForPrintArticle(int num) {

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.name AS extra__boardCode");
		sql.append("FROM article as A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberNum = M.memberNum");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardNum = b.boardNum");
		sql.append("WHERE num = ?", num);

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		if (articleMap.isEmpty()) {
			return null;
		}
		return new Article(articleMap);
	}

	public int doAdd(int memberNum, int boardNum, String title, String body) {

		SecSql sql = new SecSql();
		memberNum = 1;
		boardNum = 1;

		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("memberNum = ?,", memberNum);
		sql.append("boardNum = ?,", boardNum);
		sql.append("title = ?,", title);
		sql.append("body = ?,", body);
		sql.append("views = 0,");
		sql.append("hitsCount = 0");

		return MysqlUtil.insert(sql);
	}
}