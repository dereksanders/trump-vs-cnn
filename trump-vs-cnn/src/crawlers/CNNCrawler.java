package crawlers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import main.CNNArticle;
import main.CNNArticle.Type;
import main.Main;

public class CNNCrawler extends Crawler<CNNArticle> {

	private String keyword;

	public CNNCrawler(URL webpage, String keyword) {
		super(webpage);
		this.keyword = keyword;
	}

	@Override
	public ArrayList<CNNArticle> crawl() {

		ArrayList<CNNArticle> articles = new ArrayList<>();

		StringBuilder rawInput = this.loadWebpageSource();

		// First look for "articleList". Then parse each item in the list.

		String articleListBeginTag = "{\"articleList\":[";
		String articleListEndTag = "]}";

		int articleListBegin = rawInput.indexOf(articleListBeginTag);
		int articleListEnd = rawInput.indexOf(articleListEndTag, articleListBegin);
		int currentIndex = articleListBegin + articleListBeginTag.length();

		if (Main.DEBUG) {

			System.out.println("Article list begin: " + articleListBegin);
			System.out.println("Article list end: " + articleListEnd);
			System.out.println("Current index: " + currentIndex);
		}

		String articleList = rawInput.toString().substring(currentIndex, articleListEnd);

		int articleNum = 1;

		String nextArticleStartTag = "{";
		String nextArticleEndTag = "}";

		// currentIndex now needs to refer to articleList and not rawInput.
		currentIndex = 0;

		int nextArticleStart = articleList.indexOf(nextArticleStartTag, currentIndex);

		if (Main.DEBUG) {

			System.out.println("Next article start: " + nextArticleStart);
		}

		while (nextArticleStart != -1) {

			currentIndex = nextArticleStart + 1;

			String currentArticle = articleList.substring(nextArticleStart,
					articleList.indexOf(nextArticleEndTag, currentIndex));

			String linkBeginTag = "\"uri\":\"";
			int linkBegin = currentArticle.indexOf(linkBeginTag, currentIndex) + linkBeginTag.length();

			currentIndex = linkBegin;

			String linkEndTag = "\"";
			int linkEnd = currentArticle.indexOf(linkEndTag, currentIndex);

			if (Main.DEBUG) {
				System.out.println("linkBegin: " + linkBegin);
				System.out.println("linkEnd: " + linkEnd);
			}

			String link = "";
			try {
				link = currentArticle.substring(linkBegin, linkEnd);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.out.println("Could not find link to article.");
				System.exit(1);
			}

			if (Main.DEBUG) {
				System.out.println(link);
			}

			String headlineBeginTag = "\"headline\":\"";
			int headlineBegin = currentArticle.indexOf(headlineBeginTag, currentIndex) + headlineBeginTag.length();

			currentIndex = headlineBegin;

			String headlineEndTag = "\"";
			int headlineEnd = currentArticle.indexOf(headlineEndTag, currentIndex);

			String headline = "";
			try {
				headline = currentArticle.substring(headlineBegin, headlineEnd);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.out.println("Could not find headline of article.");
				System.exit(1);
			}

			if (Main.DEBUG) {
				System.out.println(headline);
			}

			// The current article may not have an iconType specified, in which case it is a
			// text article.
			Type type = Type.TEXT;
			String typeBeginTag = "\"iconType\":\"";
			int typeBegin = currentArticle.indexOf(typeBeginTag) + typeBeginTag.length();
			if (typeBegin != -1) {
				type = Type.VIDEO;
			}

			String date = "";
			int currentDateIndex = 0;

			if (type == Type.TEXT) {

				// currentDateIndex = link.substring(currentDateIndex, link.indexOf(""));

			} else {

			}

			if (headline.contains(this.keyword)) {

				CNNArticle current = new CNNArticle(link, LocalDate.now(), headline, type);
				articles.add(current);
				articleNum++;
			}

			nextArticleStart = articleList.indexOf(nextArticleStartTag, nextArticleStart + 1);
		}
		System.out.println("Found " + articleNum + " articles about " + this.keyword);

		return articles;
	}
}