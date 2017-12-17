package crawlers;

import java.net.URL;
import java.util.ArrayList;

public abstract class Crawler<G> {

	private ArrayList<G> items;
	private URL webpage;

	public Crawler(URL webpage) {

		setItems(new ArrayList<>());
		setWebpage(webpage);
	}

	public ArrayList<G> getItems() {
		return new ArrayList<>(items);
	}

	private void setItems(ArrayList<G> items) {
		this.items = items;
	}

	public URL getWebpage() {
		return this.webpage;
	}

	private void setWebpage(URL webpage) {
		this.webpage = webpage;
	}

	public abstract void crawl();
}
