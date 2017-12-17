package crawlers;

import java.net.URL;

import main.CNNArticle;

public class CNNCrawler extends Crawler<CNNArticle> {

	public CNNCrawler(URL webpage) {
		super(webpage);
	}

	@Override
	public void crawl() {

		StringBuilder rawInput = this.loadWebpageSource();

	}
}
