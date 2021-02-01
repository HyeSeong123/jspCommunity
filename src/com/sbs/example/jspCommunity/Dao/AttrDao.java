package com.sbs.example.jspCommunity.Dao;

import com.sbs.example.jspCommunity.Dto.Attr;
import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.SecSql;

public class AttrDao {

	public int setValue(int id, String relTypeCode, String typeCode, String type2Code, String value) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO attr");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("relId = ?,", id);
		sql.append("relTypeCode = ?,", relTypeCode);
		sql.append("typeCode = ?,", typeCode);
		sql.append("type2Code = ?,", type2Code);
		sql.append("value = ?", value);

		return MysqlUtil.insert(sql);
	}

	public int updatePwValue(String relTypeCode, int relId, String typeCode, String type2Code, String value) {
		SecSql sql = new SecSql();

		sql.append("update attr");
		sql.append("SET updateDate = NOW(),");
		sql.append("value = ?", value);
		sql.append("WHERE 1");
		sql.append("AND `relTypeCode` = ?", relTypeCode);
		sql.append("AND `relId` = ?", relId);
		sql.append("AND `typeCode` = ?", typeCode);
		sql.append("AND `type2Code` = ?", type2Code);
		
		return MysqlUtil.update(sql);
	}

	public Attr getAttr(String relTypeCode, int relId, String typeCode, String type2Code) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM attr");
		sql.append("WHERE 1");
		sql.append("AND `relTypeCode` = ?", relTypeCode);
		sql.append("AND `relId` = ?", relId);
		sql.append("AND `typeCode` = ?", typeCode);
		sql.append("AND `type2Code` = ?", type2Code);

		return new Attr(MysqlUtil.selectRow(sql));
	}

	public int remove(String relTypeCode, int relId, String typeCode, String type2Code) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM attr");
		sql.append("WHERE 1");
		sql.append("AND `relTypeCode` = ?", relTypeCode);
		sql.append("AND `relId` = ?", relId);
		sql.append("AND `typeCode` = ?", typeCode);
		sql.append("AND `type2Code` = ?", type2Code);

		return MysqlUtil.delete(sql);
	}
}