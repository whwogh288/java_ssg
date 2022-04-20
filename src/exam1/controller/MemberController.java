package exam1.controller;

import java.util.Scanner;

import exam1.container.Container;
import exam1.dto.Member;
import exam1.service.MemberService;
import exam1.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private MemberService memberService;

	public MemberController(Scanner sc) {
		this.sc = sc;
		memberService = Container.memberService;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}
	
	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

	private void doLogin() {
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비번 : ");
		String loginPw = sc.nextLine();
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if ( member == null ) {
			System.out.println("해당회원은 존재하지 않습니다.");
			return;
		}
		if ( member.loginPw.equals(loginPw) == false ) {
			System.out.println("비밀번호를 확인해주세요");
			return;
		}
		loginedMember = member;
		System.out.printf("로그인 성공!, %s님 환영합니다.\n", loginedMember.name);
		
	}
	
	private boolean isJoinableLoginId(String loginId) {
		int index = memberService.getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private void doJoin() {
		int id = Container.memberDao.getNewId();
		String regDate = Util.getNowDateStr();

		String loginId = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("로그인 비번 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비번확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		memberService.join(member);

		System.out.printf("%d번 회원이 생성되었습니다. 환영합니다.\n", id);

	}
	public void makeTestDate() {
		System.out.println("테스트를 위한 회원데이터를 생성합니다.");
		
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "admin", "admin", "관리자"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "user1", "user1", "홍길동"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "user2", "user2", "홍길순"));

	}

}
