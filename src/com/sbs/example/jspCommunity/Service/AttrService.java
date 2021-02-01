package com.sbs.example.jspCommunity.Service;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.AttrDao;
import com.sbs.example.jspCommunity.Dto.Attr;


public class AttrService {
	AttrDao attrDao;
	public AttrService() {
		attrDao = Container.attrDao;
	}
	public int setValue(int id, String relTypeCode, String typeCode, String type2Code, String value) {
		return attrDao.setValue(id, relTypeCode, typeCode, type2Code, value);
	}
	public Attr getAttr(String relTypeCode, int relId, String typeCode, String type2Code) {
		return attrDao.getAttr(relTypeCode,relId,typeCode,type2Code);
	}
	
	public int remove(String relTypeCode, int relId, String typeCode, String type2Code) {
		return attrDao.remove(relTypeCode, relId, typeCode, type2Code);
	}
	
	public int updatePwValue(String relTypeCode, int relId, String typeCode, String type2Code, String value) {
		return attrDao.updatePwValue(relTypeCode, relId, typeCode, type2Code, value);
	}

}
