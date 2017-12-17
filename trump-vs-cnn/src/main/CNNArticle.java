package main;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CNNArticle {

	// CNN articles are either video or text.
	public enum Type {
		VIDEO, TEXT;
	}

	private String link;
	private LocalDate date;
	private String headline;
	private Type type;

	// The 'last updated' time is only available from the page of the article and
	// not the homepage.
	private LocalDateTime lastUpdated;

	public CNNArticle(String link, LocalDate date, String headline, Type type, LocalDateTime lastUpdated) {

	}
}
