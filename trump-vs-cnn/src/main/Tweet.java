package main;

import java.time.LocalDateTime;

public class Tweet {

	private String user;
	private LocalDateTime time;
	private String text;

	public Tweet(String user, LocalDateTime time, String text) {

		this.user = user;
		this.time = time;
		this.text = text;
	}

	@Override
	public String toString() {

		String desc = "{";

		desc += "\"user\":\"" + this.user + "\"," + "\"time\":\"" + this.time + "\"," + "\"text\":\"" + this.text
				+ "\"";

		desc += "}";

		return desc;
	}
}
