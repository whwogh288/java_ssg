package exam1.service;

import exam1.container.Container;
import exam1.dao.MemberDao;
import exam1.dto.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao; 
	}

	public int  getMemberIndexByLoginId(String loginId) {
		return memberDao.getMemberIndexByLoginId(loginId);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public void join(Member member) {
		memberDao.add(member);
	}

	public String getMemberNameById(int memberId) {
		return memberDao.getMemberNameById(memberId);
	}

}
