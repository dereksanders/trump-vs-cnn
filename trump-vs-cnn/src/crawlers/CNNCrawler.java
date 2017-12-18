package crawlers;

import java.net.URL;
import java.util.ArrayList;

import main.CNNArticle;

public class CNNCrawler extends Crawler<CNNArticle> {

	public CNNCrawler(URL webpage) {
		super(webpage);
	}

	@Override
	public ArrayList<CNNArticle> crawl() {

		ArrayList<CNNArticle> articles = new ArrayList<>();

		StringBuilder rawInput = this.loadWebpageSource();

		// First look for "articleList". Then parse each item in the list.

		return articles;
	}
}
