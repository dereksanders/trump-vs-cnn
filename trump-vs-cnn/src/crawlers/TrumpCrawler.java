package crawlers;

import java.net.URL;
import java.util.HashMap;

import main.TrumpTweet;

public class TrumpCrawler extends Crawler<TrumpTweet> {

	public TrumpCrawler(URL webpage) {
		super(webpage);
	}

	@Override
	public void crawl() {

		StringBuilder rawInput = this.loadWebpageSource();
		HashMap<String, String> features = new HashMap<>();

		// To find the time of a tweet, first search for "small class="time"" and
		// then "data-time=" to find the Unix timestamp (seconds from the epoch of
		// 1970-01-01T00:00:00Z) enclosed in quotation marks.

		String tweetTime = "";
		features.put("time", tweetTime);

		// To find the content of the tweet, first search for <div
		// class="js-tweet-text-container"> and then between <p class=*> and </p> will
		// be the text.

		String tweetText = "";
		features.put("text", tweetText);
	}
}
