package crawlers;

import java.net.URL;

import main.TrumpTweet;

public class TrumpCrawler extends Crawler<TrumpTweet> {

	public TrumpCrawler(URL webpage) {
		super(webpage);
	}

	@Override
	public void crawl() {

		StringBuilder rawInput = this.loadWebpageSource();
	}
}
