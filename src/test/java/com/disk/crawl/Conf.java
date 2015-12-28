package com.disk.crawl;

public enum Conf {
	MIRRAW {
		@Override
		public ConfigNew getValues() {
			return new ConfigNew("http://www.mirraw.com/", "mirraw", "http://www.mirraw.com/",
					"/Users/salonasinha/mirraw");
		}
	},
	CBAZAAR {
		@Override
		public ConfigNew getValues() {
			return new ConfigNew("http://www.cbazaar.com/", "cbazaar", "http://www.cbazaar.com/",
					"/Users/salonasinha/cbazaar");
		}
	};

	public abstract ConfigNew getValues();
}

class ConfigNew {
	private static String confType;
	private String seed;
	private String site;
	private String urlPrefix;
	private String folder;

	public ConfigNew(String seed, String site, String urlPrefix, String folder) {
		this.seed = seed;
		this.site = site;
		this.urlPrefix = urlPrefix;
		this.folder = folder;
	}

	public String getSeed() {
		return seed;
	}

	public String getFolder() {
		return folder;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	public String getSite() {
		return site;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public static String getConfType() {
		return confType;
	}

	public static void setConfType(String confType) {
		if (!confType.isEmpty()) {
			ConfigNew.confType = confType.toUpperCase();
		}
	}
}
