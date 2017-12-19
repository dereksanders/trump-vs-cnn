package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import crawlers.CNNCrawler;
import crawlers.TweetCrawler;

public class Main {

	public static final boolean DEBUG = true;

	private static final String TRUMP_USER = "@realDonaldTrump";
	private static final String TRUMP_TWITTER = "https://twitter.com/realdonaldtrump";
	private static final String CNN_LINK = "http://www.cnn.com/";
	private static final String CNN_KEYWORD = "Trump";
	private static final int MAX_TWEET_LENGTH = 280;

	public static void main(String[] args) {

		TweetCrawler trumpTweets = null;
		CNNCrawler trumpArticles = null;

		try {
			trumpTweets = new TweetCrawler(new URL(TRUMP_TWITTER), TRUMP_USER);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		try {
			trumpArticles = new CNNCrawler(new URL(CNN_LINK), CNN_KEYWORD);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		ArrayList<Tweet> tweets = trumpTweets.crawl();
		ArrayList<CNNArticle> articles = trumpArticles.crawl();

		// Roughly large enough to contain many lengthy tweets. Doesn't take into
		// consideration the space taken up by a tweet's user or date but it's unlikely
		// that every tweet will be max length.
		StringBuilder allTweets = new StringBuilder(tweets.size() * MAX_TWEET_LENGTH);

		allTweets.append("{\n");
		allTweets.append("\t\"tweets\":[\n");

		for (int i = 0; i < tweets.size(); i++) {

			allTweets.append("\t\t");
			allTweets.append(tweets.get(i).toString());

			if (i < tweets.size() - 1) {
				allTweets.append(",\n");
			} else {
				allTweets.append("\n");
			}
		}
		allTweets.append("]}");

		writeFile(allTweets.toString(), "trumpTweets.json");

		StringBuilder allArticles = new StringBuilder(articles.size() * 100);

		allArticles.append("{\n");
		allArticles.append("\t\"articles\":[\n");

		for (int i = 0; i < articles.size(); i++) {

			allArticles.append("\t\t");
			allArticles.append(articles.get(i).toString());

			if (i < articles.size() - 1) {
				allArticles.append(",\n");
			} else {
				allArticles.append("\n");
			}
		}
		allArticles.append("]}");

		writeFile(allArticles.toString(), "trumpArticles.json");
	}

	private static void writeFile(String text, String filename) {

		try {

			Files.write(Paths.get("./" + filename), text.getBytes());

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
