package exam1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exam1.dto.Article;
import exam1.util.Util;

public class App {
	    private List<Article> articles;
		
		public App() {
			articles = new ArrayList<>();
		}
		
		public void start() {
			System.out.println("== 프로그램 시작 ==");
			
			makeTestDate();

			Scanner sc = new Scanner(System.in);

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

				if (command.equals("article write")) {
					int id = articles.size() + 1;
					String regDate = Util.getNowDateStr();
					System.out.printf("제목 : ");
					String title = sc.nextLine();
					System.out.printf("내용 : ");
					String body = sc.nextLine();

					Article article = new Article(id, regDate, title, body);
					articles.add(article);

					System.out.printf("%d번글이 생성되었습니다.\n", id);
				} else if (command.equals("article list")) {
					if (articles.size() == 0) {
						System.out.println("게시물이 없습니다.");
						continue;
					}
					System.out.println("번호 : 조회 : 제목");

					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);

						System.out.printf("%4d : %4d : %s\n", article.id, article.hit, article.title);
					}

				} else if (command.startsWith("article detail ")) {
					String[] commandBits = command.split(" ");
					int id = Integer.parseInt(commandBits[2]);

					Article foundArticle = null;

					for (int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);

						if (article.id == id) {
							foundArticle = article;
							break;
						}
					}

					if (foundArticle == null) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
						continue;
					}
					
					foundArticle.increaseHit();

					System.out.printf("번호 : %d\n", foundArticle.id);
					System.out.printf("날짜 : %s\n", foundArticle.regDate);
					System.out.printf("제목 : %s\n", foundArticle.title);
					System.out.printf("내용 : %s\n", foundArticle.body);
					System.out.printf("조회 : %s\n", foundArticle.hit);

				} else if (command.startsWith("article modify ")) {
					String[] commandBits = command.split(" ");
					int id = Integer.parseInt(commandBits[2]);

					Article foundArticle = null;

					for (int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);

						if (article.id == id) {
							foundArticle = article;
							break;
						}
					}

					if (foundArticle == null) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
						continue;
					}

					System.out.printf("제목 : ");
					String title = sc.nextLine();
					System.out.printf("내용 : ");
					String body = sc.nextLine();
					
					foundArticle.title = title;
					foundArticle.body = body;
					
					System.out.printf("%d번 게시물이 수정되었습니다\n", id);

				} else if (command.startsWith("article delete ")) {
					String[] commandBits = command.split(" ");
					int id = Integer.parseInt(commandBits[2]);

					int foundIndex = -1;

					for (int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);

						if (article.id == id) {
							foundIndex = i;
							break;
						}
					}

					if (foundIndex == -1) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
						continue;
					}

					articles.remove(foundIndex);
					System.out.printf("%d번 게실물이 삭제되었습니다.\n", id);
				} else {
					System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.");
				}
			}
			sc.close();

			System.out.println("== 프로그램 끝 ==");
		}

		private void makeTestDate() {
			System.out.println("테스트를 위한 데이터를 생성합니다.");
			
			articles.add(new Article(1, Util.getNowDateStr(), "제목1","내용1" ));
			articles.add(new Article(2, Util.getNowDateStr(), "제목2","내용2" ));
			articles.add(new Article(3, Util.getNowDateStr(), "제목3","내용3" ));
	}
}

