package exam1.service;

import java.util.List;

import exam1.container.Container;
import exam1.dao.ArticleDao;
import exam1.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}	
	public List<Article> getForPrintArticles(String searchKeyword) {
		return articleDao.getArticles(searchKeyword);
	}

	public int getArticleIndexById(int id) {
		return articleDao.getArticleIndexById(id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
		
	}
}
