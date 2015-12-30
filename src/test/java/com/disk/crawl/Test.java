package com.disk.crawl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test {
	public static void main(String[] args) throws IOException {
		ConfigNew.setConfType(args[0]);
		String url = "http://www.cbazaar.com/neck-piece/necklace-set-with-maang-tika/golden-n-off-white-motis-n-stones-necklace-set-p-jymhrsrdmyns32g.html";
		parseTestUrl(url);
	}

	public static void parseTestUrl(String url) throws IOException {
		Document doc = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
				.referrer("http://www.google.com").timeout(5000).get();
		DiskParser.parse(doc, url);
	}
}
