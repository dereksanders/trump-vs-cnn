package main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import crawlers.TrumpCrawler;

public class Main {

	public static final boolean DEBUG = true;
	private static final String TRUMP_TWITTER = "https://twitter.com/realdonaldtrump";

	public static void main(String[] args) {

		TrumpCrawler trumpTweets = null;

		try {
			trumpTweets = new TrumpCrawler(new URL(TRUMP_TWITTER));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		ArrayList<TrumpTweet> tweets = trumpTweets.crawl();
	}
}
