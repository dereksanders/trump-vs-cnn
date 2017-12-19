package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import crawlers.TweetCrawler;

public class Main {

	public static final boolean DEBUG = true;

	private static final String TRUMP_USER = "@realDonaldTrump";
	private static final String TRUMP_TWITTER = "https://twitter.com/realdonaldtrump";
	private static final int MAX_TWEET_LENGTH = 280;

	public static void main(String[] args) {

		TweetCrawler trumpTweets = null;

		try {
			trumpTweets = new TweetCrawler(new URL(TRUMP_TWITTER), TRUMP_USER);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		ArrayList<Tweet> tweets = trumpTweets.crawl();

		StringBuilder allTweets = new StringBuilder(tweets.size() * MAX_TWEET_LENGTH);

		for (Tweet t : tweets) {

			allTweets.append(t.toString());
			allTweets.append("\n\n");
		}

		// Get rid of trailing endlines.
		allTweets.substring(0, allTweets.lastIndexOf("\n\n"));

		writeFile(allTweets.toString(), "trumpTweets.txt");
	}

	private static void writeFile(String text, String filename) {

		try {

			Files.write(Paths.get("./" + filename), text.getBytes());

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
