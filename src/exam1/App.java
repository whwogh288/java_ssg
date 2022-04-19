package exam1;

import java.util.Scanner;

import exam1.controller.ArticleController;
import exam1.controller.Controller;
import exam1.controller.MemberController;

public class App {

	public void start() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		memberController.makeTestDate();
		articleController.makeTestDate();

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine();
			command = command.trim();

			if (command.length() == 0) {
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}
			String[] commandBits = command.split(" ");
			
			if ( commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];

			Controller controller = null;
			
			if ( controllerName.equals("article")) {
				controller = articleController;
			}
			else if ( controllerName.equals("member")) {
				controller = memberController;
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			controller.doAction(command, actionMethodName);

		}
		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}
}
