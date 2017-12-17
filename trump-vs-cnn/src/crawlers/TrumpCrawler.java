package crawlers;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import main.Main;
import main.TrumpTweet;

public class TrumpCrawler extends Crawler<TrumpTweet> {

	public TrumpCrawler(URL webpage) {
		super(webpage);
	}

	@Override
	public ArrayList<TrumpTweet> crawl() {

		ArrayList<TrumpTweet> tweets = new ArrayList<>();

		StringBuilder rawInput = this.loadWebpageSource();

		// To find the time of a tweet, first search for "small class="time"" and
		// then "data-time=" to find the Unix timestamp (seconds from the epoch of
		// 1970-01-01T00:00:00Z) enclosed in quotation marks.
		int currentIndex = 0;
		String timeClass = "";

		while (timeClass != null) {

			String tweetTime = "";
			String tweetText = "";

			int tweetNum = 1;

			String timeContainerBeginTag = "<small class=\"time\">";
			String timeContainerEndTag = "</small>";

			// Get the indices of the content within the next timeClass tags.
			int timeClassBegin = rawInput.indexOf(timeContainerBeginTag, currentIndex) + timeContainerBeginTag.length();
			int timeClassEnd = rawInput.indexOf(timeContainerEndTag, timeClassBegin);

			currentIndex = timeClassBegin;

			try {

				timeClass = rawInput.toString().substring(timeClassBegin, timeClassEnd);

			} catch (IndexOutOfBoundsException e) {

				// There are no more tweets.
				timeClass = null;
				break;
			}

			String timestampBeginTag = "data-time=\"";

			int timestampBegin = rawInput.indexOf(timestampBeginTag, currentIndex) + timestampBeginTag.length();
			currentIndex = timestampBegin;

			try {

				tweetTime = rawInput.toString().substring(timestampBegin, rawInput.indexOf("\"", currentIndex));

				if (Main.DEBUG) {
					System.out.println(tweetTime);
				}

			} catch (IndexOutOfBoundsException e) {

				// No timestamp found for this tweet
				tweetTime = null;
				e.printStackTrace();
				System.out.println("Could not find timestamp for tweet " + tweetNum);
				System.exit(1);
			}

			LocalDateTime tweetTimeEpoch = LocalDateTime.ofEpochSecond(Long.parseLong(tweetTime), 0, ZoneOffset.UTC);

			// To find the content of the tweet, first search for <div
			// class="js-tweet-text-container"> and then between <p class=*> and </p> will
			// be the text.
			String textContainerBeginTag = "<div class=\"js-tweet-text-container\">";
			String textContainerEndTag = "</div>";

			int textContainerBegin = rawInput.indexOf(textContainerBeginTag, currentIndex)
					+ textContainerBeginTag.length();
			int textContainerEnd = rawInput.indexOf(textContainerEndTag, timeClassBegin);

			currentIndex = textContainerBegin;

			int tweetTextBegin = rawInput.indexOf(">", textContainerEnd) + 1;

			String tweetTextEndTag = "</p>";

			try {

				tweetText = rawInput.toString().substring(tweetTextBegin,
						rawInput.indexOf(tweetTextEndTag, tweetTextBegin));

				if (Main.DEBUG) {
					System.out.println(tweetText);
				}

			} catch (IndexOutOfBoundsException e) {

				// No text found for this tweet
				tweetText = null;
				e.printStackTrace();
				System.out.println("Could not find text for tweet " + tweetNum + " at time " + tweetTimeEpoch);
				System.exit(1);
			}

			tweets.add(new TrumpTweet(tweetTimeEpoch, tweetText));

			tweetNum++;
		}

		return tweets;
	}
}
