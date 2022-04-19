package exam1.dao;

import java.util.ArrayList;
import java.util.List;

import exam1.dto.Member;

public class MemberDao {
	public List<Member> members;
	
	public MemberDao() {
		members = new ArrayList<>();
	}
}
