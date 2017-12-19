package main;

import java.time.LocalDate;

public class CNNArticle {

	// CNN articles are either video or text.
	public enum Type {
		VIDEO, TEXT;
	}

	private String link;
	private LocalDate date;
	private String headline;
	private Type type;

	public CNNArticle(String link, LocalDate date, String headline, Type type) {

		this.link = link;
		this.date = date;
		this.headline = headline;
		this.type = type;
	}

	@Override
	public String toString() {

		String desc = "{";

		desc += "\"link\":\"" + this.link + "\"," + "\"headline\":\"" + this.headline + "\"," + "\"type\":\""
				+ this.type + "\"," + "\"date\":\"" + this.date + "\"";

		desc += "}";

		return desc;
	}

}
