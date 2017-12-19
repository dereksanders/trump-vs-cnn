package crawlers;

import java.net.URL;
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

		String articleListBeginTag = "{articleList:[";
		String articleListEndTag = "]}";

		int articleListBegin = rawInput.indexOf(articleListBeginTag);
		int articleListEnd = rawInput.indexOf(articleListEndTag);

		int currentIndex = articleListBegin + articleListBeginTag.length();

		String articleList = rawInput.toString().substring(currentIndex, articleListEnd);

		int articleNum = 1;

		String nextArticleStartTag = "{";
		String nextArticleEndTag = "}";

		int nextArticleStart = articleList.indexOf(nextArticleStartTag, currentIndex);

		while (nextArticleStart != -1) {

			currentIndex = nextArticleStart + 1;

			String currentArticle = articleList.substring(nextArticleStart,
					articleList.indexOf(articleListEndTag, currentIndex));

			String linkBeginTag = "\"uri\":\"";
			int linkBegin = articleList.indexOf(linkBeginTag, currentIndex) + linkBeginTag.length();

			String link = "";
			try {
				link = articleList.substring(linkBegin, articleList.indexOf("\""));
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.out.println("Could not find link to article.");
				System.exit(1);
			}

			if (Main.DEBUG) {
				System.out.println(link);
			}

			String headlineBeginTag = "\"headline\":\"";
			int headlineBegin = articleList.indexOf(headlineBeginTag, currentIndex) + headlineBeginTag.length();

			String headline = "";
			try {
				headline = articleList.substring(headlineBegin, articleList.indexOf("\""));
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

				CNNArticle current = new CNNArticle(link, null, typeBeginTag, null, null);
				articleNum++;
			}

			nextArticleStart = articleList.indexOf(nextArticleStartTag, currentIndex);
		}

		return articles;
	}
}