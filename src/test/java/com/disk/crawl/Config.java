package com.disk.crawl;

public enum Config {
	SEED("http://www.vajor.com/"),
	SITE("vajor"),
	URL_PREFIX("http://www.vajor.com/");
	

	private String val;

	private Config(String s) {
		val = s;
	}

	public String val() {
		return val;
	}
}
