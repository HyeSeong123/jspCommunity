package com.sbs.example.jspCommunity.Service;

import java.util.Map;

import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.UsrReplyDao;

public class UsrReplyService {

	UsrReplyDao usrReplyDao;

	public UsrReplyService() {
		usrReplyDao = Container.usrReplyDao;
	}

	public int write(Map<String, Object> writeArgs) {
		return usrReplyDao.write(writeArgs);
	}

}
