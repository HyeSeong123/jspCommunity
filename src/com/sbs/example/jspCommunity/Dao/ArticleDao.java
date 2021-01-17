package com.sbs.example.jspCommunity.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Board;
import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.SecSql;

public class ArticleDao {

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

	public Board getBoardNum(int boardNum) {

		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM board");
		sql.append("WHERE boardNum = ?", boardNum);

		Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

		if (boardMap.isEmpty()) {
			return null;
		}
		return new Board(boardMap);
	}

	public int doWrite(Map<String, Object> writeArgs) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("boardNum = ?,", writeArgs.get("boardNum"));
		sql.append("memberNum = ?,", writeArgs.get("memberNum"));
		sql.append("title = ?,", writeArgs.get("title"));
		sql.append("body = ?", writeArgs.get("body"));
		return MysqlUtil.insert(sql);
	}

	public int doModify(Map<String, Object> modifyArgs) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate =NOW(),");

		boolean needToFalse = false;

		if (modifyArgs.get("title") != null) {
			needToFalse = true;
			sql.append("title =?,", modifyArgs.get("title"));
		}

		if (modifyArgs.get("body") != null) {
			needToFalse = true;
			sql.append("`body` =?", modifyArgs.get("body"));
		}

		sql.append("WHERE num = ?", modifyArgs.get("num"));
		if (needToFalse == false) {
			return 0;
		}

		return MysqlUtil.update(sql);

	}

	public int doDelete(int articleNum) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article");
		sql.append("WHERE num = ?", articleNum);

		return MysqlUtil.delete(sql);
	}
}