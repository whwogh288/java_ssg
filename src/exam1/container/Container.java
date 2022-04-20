package exam1.container;

import exam1.dao.ArticleDao;
import exam1.dao.MemberDao;
import exam1.service.ArticleService;
import exam1.service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}
}
