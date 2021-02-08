package com.sbs.example.jspCommunity.Service;

import java.util.Map;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.ReplyDao;

public class UsrReplyService {

	ReplyDao replyDao;

	public UsrReplyService() {
		replyDao = Container.replyDao;
	}

	public int write(Map<String, Object> writeArgs) {
		return replyDao.write(writeArgs);
	}

}
