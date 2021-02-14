
package com.sbs.example.jspCommunity.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.Dto.Article;
import com.sbs.example.jspCommunity.Dto.Board;
import com.sbs.example.jspCommunity.Dto.Like;
import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.SecSql;

public class ArticleDao {

	public List<Article> getForPrintArticlesByBoard(int boardNum, int limitStart, int limitCount, String searchKeyword,
			String searchKeywordType) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra__likePoint");
		sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra__likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra__disLikeOnlyPoint");
		sql.append("FROM article as A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberNum = M.memberNum");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardNum = B.boardNum");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'article'");
		sql.append("AND A.num = L.relId");
		if (boardNum != 0) {
			sql.append("WHERE A.boardNum =?", boardNum);
		}

		if (searchKeywordType != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType == null || searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType == null || searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
						searchKeyword);
			}
		}

		sql.append("GROUP BY A.num");

		sql.append("ORDER BY A.num DESC");

		if (limitCount != -1) {
			sql.append("LIMIT ?, ?", limitStart, limitCount);
		}

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
		sql.append(", REPLACE(A.regDate,'00:00:00','')");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra__likePoint");
		sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra__likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra__disLikeOnlyPoint");
		sql.append("FROM article as A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberNum = M.memberNum");
		sql.append("INNER JOIN `board` AS B\r");
		sql.append("ON A.boardNum = B.boardNum");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'article'");
		sql.append("AND A.num = L.relId");
		sql.append("WHERE A.num = ?", num);
		sql.append("GROUP BY A.num");

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
		sql.append("SET regDate = DATE(NOW()),");
		sql.append("updateDate = DATE(NOW()),");
		sql.append("boardNum = ?,", writeArgs.get("boardNum"));
		sql.append("memberNum = ?,", writeArgs.get("memberNum"));
		sql.append("title = ?,", writeArgs.get("title"));
		sql.append("body = ?", writeArgs.get("body"));
		
		return MysqlUtil.insert(sql);
	}

	public int doModify(Map<String, Object> modifyArgs) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate =NOW()");

		boolean needToFalse = false;

		if (modifyArgs.get("title") != null) {
			needToFalse = true;
			sql.append(", title =?", modifyArgs.get("title"));
		}

		if (modifyArgs.get("body") != null) {
			needToFalse = true;
			sql.append(", `body` =?", modifyArgs.get("body"));
		}
		if (needToFalse == false) {
			return 0;
		}
		sql.append("WHERE num = ?", modifyArgs.get("num"));

		return MysqlUtil.update(sql);

	}

	public int doDelete(int articleNum) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article");
		sql.append("WHERE num = ?", articleNum);

		return MysqlUtil.delete(sql);
	}

	public int getArticlesCountByBoardNum(int boardNum, String searchKeyword, String searchKeywordType) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM article AS A");
		sql.append("WHERE 1");
		if (boardNum != 0) {
			sql.append("AND A.boardNum = ?", boardNum);
		}
		if (searchKeywordType != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType == null || searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType == null || searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
						searchKeyword);

			}
		}
		return MysqlUtil.selectRowIntValue(sql);
	}

	public int doArticleLike(int loginId, int articleNum) {
		SecSql sql = new SecSql();

		Like like = dupLike(loginId, "article_like", articleNum);

		if (like != null) {
			sql.append("DELETE FROM `like`");
			sql.append("WHERE 1");
			sql.append("AND memberNum = ?", loginId);
			sql.append("AND relTypeCode = 'article_like'");
			sql.append("AND relId = ?", articleNum);
			articleAddObject(articleNum, "`like`", -1);
			MysqlUtil.delete(sql);
			return -1;
		}

		sql.append("INSERT INTO `like`");
		sql.append("SET regDate = DATE(NOW())");
		sql.append(", updateDate = DATE(NOW())");
		sql.append(", relTypeCode = 'article_like'");
		sql.append(", relId = ?", articleNum);
		sql.append(", memberNum = ?", loginId);
		sql.append(", `point`='1'");
		articleAddObject(articleNum, "`like`", +1);

		return MysqlUtil.insert(sql);
	}

	public Like dupLike(int loginId, String relTypeCode, int relId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM `like`");
		sql.append("WHERE 1");
		sql.append("AND like.memberNum = ?", loginId);
		sql.append("AND like.relTypeCode = ?", relTypeCode);
		sql.append("AND like.relId = ?", relId);

		Map<String, Object> likeMap = MysqlUtil.selectRow(sql);

		if (likeMap.isEmpty()) {
			return null;
		}
		return new Like(likeMap);
	}

	public void articleAddObject(int loginId, String relTypeCode, int status) {
		SecSql sql = new SecSql();

		sql.append("UPDATE `article`");

		if (status > 0) {
			sql.append("SET " + relTypeCode + "=" + relTypeCode + "+1");
		} else {
			sql.append("SET " + relTypeCode + "=" + relTypeCode + "-1");
		}
		sql.append("WHERE num = ?", loginId);
		System.out.println(sql);
		MysqlUtil.update(sql);
	}

	public int doArticleUnLike(int loginId, int articleNum) {
		SecSql sql = new SecSql();

		Like like = dupLike(loginId, "article_unlike", articleNum);

		if (like != null) {
			sql.append("DELETE FROM `like`");
			sql.append("WHERE 1");
			sql.append("AND memberNum = ?", loginId);
			sql.append("AND relTypeCode = 'article_unLike'");
			sql.append("AND relId = ?", articleNum);
			articleAddObject(articleNum, "unLike", -1);
			MysqlUtil.delete(sql);
			return -1;
		}

		sql.append("INSERT INTO `like`");
		sql.append("SET regDate = DATE(NOW())");
		sql.append(", updateDate = DATE(NOW())");
		sql.append(", relTypeCode = 'article_unLike'");
		sql.append(", relId = ?", articleNum);
		sql.append(", memberNum = ?", loginId);
		sql.append(", `point`='1'");
		articleAddObject(articleNum, "unLike", +1);

		return MysqlUtil.insert(sql);
	}

	public Article getArticleById(int relId) {
		SecSql sql = new SecSql();
		
		sql.append("SELECT A.*");
		sql.append("FROM article as A");
		sql.append("WHERE A.num = ? ",relId);
		
		Map<String,Object> map = MysqlUtil.selectRow(sql);
		
		if(map.isEmpty()) {
			return null;
		}
		
		return new Article(map);
	}

	public Map<String, Object> getArticleLikeAvailable(int num, int loginedMemberNum) {
		Article article = getForPrintArticle(num);
		
		Map<String, Object> rs = new HashMap<>();
		
		/*
		if(article.getMemberNum() == loginedMemberNum) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "본인은 추천 할 수 없습니다.");
			
			return rs;
		}
		*/
		

		
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공");
		
		return rs;
	}
	
	
}
