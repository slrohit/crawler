package com.disk.crawl;

public enum Config {
	SEED("http://www.cbazaar.com/"),
	SITE("cbazaar"),
	URL_PREFIX("http://www.cbazaar.com/"),
	FOLDER("/home/salonasinha/cbazaar");
	private String val;

	private Config(String s) {
		val = s;
	}

	public String val() {
		return val;
	}
}
