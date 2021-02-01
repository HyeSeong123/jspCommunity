package com.sbs.example.jspCommunity.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.App;
import com.sbs.example.jspCommunity.Container.Container;
import com.sbs.example.jspCommunity.Dao.MemberDao;
import com.sbs.example.jspCommunity.Dto.Attr;
import com.sbs.example.jspCommunity.Dto.Member;
import com.sbs.example.jspCommunity.Dto.ResultData;
import com.sbs.example.jspCommunity.Util.Util;

public class MemberService {
	private MemberDao memberDao;
	private EmailService emailService;
	private AttrService attrService;

	public MemberService() {
		attrService = Container.attrService;
		memberDao = Container.memberDao;
		emailService = Container.emailService;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}

	public int doJoin(Map<String, Object> joinArgs) {
		return memberDao.doJoin(joinArgs);
	}

	public Member getForPrintMember(String id) {
		return memberDao.getForPrintMember(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member getMemberByLoginNum(int loginMemberNum) {
		return memberDao.getMemberByLoginNum(loginMemberNum);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	public ResultData sendTempLoginPwToEmail(Member actor) {
		String siteName = App.getSite();
		String siteLoginUrl = App.getLoginUrl();
		String title = "[" + siteName + "] 임시 패스워드 발송";
		String tempPassword = Util.getTempPassword(6);
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
		body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\"> 로그인 하러가기</a>";

		Map<String, Object> rs = new HashMap<>();
		int sendRs = emailService.send(actor.getEmail(), title, body);

		setTempPassword(actor, tempPassword);
		if (sendRs != 1) {
			return new ResultData("F-1", "메일방송에 실패하였습니다.");
		}
		String resultMsg = String.format("고객님의 새 임시 패스워드가 %s (으)로 발송되었습니다.", actor.getEmail());
		return new ResultData("S-1", resultMsg, "email", actor.getEmail());
	}

	private void setTempPassword(Member actor, String tempPassword) {
		Map<String, Object> modifyParam = new HashMap<>();
		modifyParam.put("memberNum", actor.getMemberNum());
		modifyParam.put("loginPw", Util.sha256(tempPassword));
		modify(modifyParam);

		Attr attr = attrService.getAttr("member", actor.getMemberNum(), "extra", "tempPassword");

		if (attr == null) {
			attrService.setValue(actor.getMemberNum(), "member", "extra", "tempPassword", "1");
		}
		else if(attr!= null) {
			attrService.updatePwValue("member", actor.getMemberNum(), "extra", "tempPassword","1");
		}
	}

	private void modify(Map<String, Object> param) {
		memberDao.modify(param);
	}

	public void modifyAccount(Map<String, Object> modifyArgs) {
		modify(modifyArgs);
	}
}
