package main;

import java.time.LocalDateTime;

public class TrumpTweet {

	private LocalDateTime time;
	private String text;

	public TrumpTweet(LocalDateTime time, String text) {

		this.time = time;
		this.text = text;
	}

	@Override
	public String toString() {

		return "@realDonaldTrump at " + time + ":\n" + text;
	}
}
