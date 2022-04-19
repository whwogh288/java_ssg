package exam1.controller;

import exam1.dto.Member;

abstract public class Controller {
	public static Member loginedMember;
	
	public abstract void doAction(String command, String actionMethodName);
	public abstract void makeTestDate();
	
	public static boolean isLogined() {
		return loginedMember != null;
	}

}
