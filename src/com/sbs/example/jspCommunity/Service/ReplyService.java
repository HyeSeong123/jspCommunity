package com.sbs.example.jspCommunity.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.ReplyDao;
import com.sbs.example.jspCommunity.Dto.Reply;
import com.sbs.example.jspCommunity.Util.MysqlUtil;
import com.sbs.example.jspCommunity.Util.SecSql;

public class ReplyService {

	ReplyDao replyDao;

	public ReplyService() {
		replyDao = Container.replyDao;
	}

	public int write(Map<String, Object> writeArgs) {
		return replyDao.write(writeArgs);
	}

	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
		return replyDao.getForPrintReplies(relTypeCode,relId);
	}

}
