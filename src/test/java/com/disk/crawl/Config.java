package com.disk.crawl;

public enum Config {
	SEED("http://www.mirraw.com/"),
	SITE("mirraw"),
	URL_PREFIX("http://www.mirraw.com/");

	private String val;

	private Config(String s) {
		val = s;
	}

	public String val() {
		return val;
	}
}
