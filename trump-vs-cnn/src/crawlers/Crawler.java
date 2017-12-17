package crawlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	protected void setItems(ArrayList<G> items) {
		this.items = items;
	}

	public URL getWebpage() {
		return this.webpage;
	}

	protected void setWebpage(URL webpage) {
		this.webpage = webpage;
	}

	protected StringBuilder loadWebpageSource() {

		// Initial capacity of the StringBuilder that will contain the webpage's source.
		// The larger this value, the fewer times the StringBuilder will have to expand
		// its capacity but if it's larger than the number of characters in the source,
		// unnecessary memory will be allocated.
		final int INITIAL_CAPACITY = 10000;

		StringBuilder rawInput = new StringBuilder(INITIAL_CAPACITY);
		BufferedReader webpageReader = null;

		try {
			webpageReader = new BufferedReader(new InputStreamReader(this.getWebpage().openStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Could not create reader for webpage: " + this.getWebpage());
			System.exit(1);
		}

		String currentLine = "";
		int line = 1;

		try {
			while ((currentLine = webpageReader.readLine()) != null) {
				rawInput.append(currentLine);
				line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not read line " + line + " of source for webpage: " + this.getWebpage());
			System.exit(1);
		}

		try {
			webpageReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not close reader for webpage: " + this.getWebpage());
			System.exit(1);
		}

		return rawInput;
	}

	public abstract ArrayList<G> crawl();
}
