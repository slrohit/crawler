package com.disk.crawl;

import org.jsoup.nodes.Document;

public class FilterUtils {
	public static boolean urlFilterForFaballey(Document doc, String url) {
		if (url.contains("/accessories") || url.contains("/night")) {
			return false;
		}
		if (doc.select(".prodRightSec").isEmpty()) {
			return false;
		}
		String breadCrumb = doc.select(".homeTop").text();
		breadCrumb = breadCrumb.replaceAll("\\s+", "");
		if (breadCrumb.contains("HOME>NIGHT") || breadCrumb.contains("HOME>ACCESSORIES")
				|| breadCrumb.contains("HOME>BAGS") || breadCrumb.contains("HOME>TOTES")
				|| breadCrumb.contains("HOME>SETCHELS") || breadCrumb.contains("HOME>SLIPS&TEDDIE")
				|| breadCrumb.contains("HOME>PAJAMAS&SHORTS") || breadCrumb.contains("HOME>BELTS")
				|| breadCrumb.contains("HOME>DENIM") || breadCrumb.contains("HOME>SUNGLASSES")
				|| breadCrumb.contains("HOME>HAIRACCESSORIES") || breadCrumb.contains("HOME>SCARVES")
				|| breadCrumb.contains("HOME>PHONECOVER") || breadCrumb.contains("HOME>SHOULDERBAG")) {
			return false;
		}

		return true;
	}

	public static boolean urlFilterForJabong(Document doc, String url) {
		if (doc.select("#add-to-cart").isEmpty()) {
			return false;
		}
		String prevLink = doc.select(".product-details .back-page").attr("href");
		if (prevLink.isEmpty() || !prevLink.contains("/women/clothing/")) {
			return false;
		}
		if (prevLink.isEmpty()) {
			return false;
		}
		int cnt = 0;
		for (int i = 0; i < url.length(); i++) {
			if (url.charAt(i) == '/') {
				cnt++;
			}
		}
		if (cnt > 3)
			return false;
		System.out.println(url);
		return true;
	}

	public static boolean urlFilterForKoovs(Document doc, String url) {

		if (doc.select("#breadcrumb").isEmpty()) {
			return false;
		}
		String breadCrumb = doc.select("#breadcrumb").text();
		breadCrumb = breadCrumb.replaceAll("\\s+", "").toLowerCase();
		if (!breadCrumb.contains("women")) {
			return false;
		}
		if (breadCrumb.contains("women›beauty")) {
			return false;
		}
		if (breadCrumb.contains("women›watches")) {
			return false;
		}
		if (breadCrumb.contains("women›sunglasses")) {
			return false;
		}
		if (breadCrumb.contains("women›jeans")) {
			return false;
		}
		if (breadCrumb.contains("women›lingerie&sleepwear")) {
			return false;
		}
		return true;
	}

	public static boolean urlFilterFor6ycollective(Document doc, String url) {
		if (doc.select(".product-code").isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean urlFilterForFleaffair(Document doc, String url) {
		if (doc.select(".product-info").isEmpty()) {
			return false;
		}
		String breadcrumb = doc.select(".breadcrumbs a").text().toLowerCase();
		if (!breadcrumb.contains("apparels")) {
			return false;
		}
		if (breadcrumb.contains("sarees")) {
			return true;
		}
		if (breadcrumb.contains("lehengas")) {
			return true;
		}
		if (breadcrumb.contains("indo western")) {
			return true;
		}
		return false;
	}

	public static boolean urlFilterForTheLabelLife(Document doc, String url) {
		if (doc.select(".product-shop").isEmpty())
			return false;
		return true;
	}

	public static boolean urlFilterForVajor(Document doc, String url) {
		if (doc.select(".product-page").isEmpty())
			return false;
		return true;
	}

	public static boolean urlFilterForJaypore(Document doc, String url) {
		if (doc.select(".prodDetail").isEmpty())
			return false;
		return true;
	}

	public static boolean urlFilterFor20dresses(Document doc, String url) {
		if (doc.select(".product_info_main").isEmpty())
			return false;
		return true;
	}
	public static boolean urlFilterForCraftisan(Document doc, String url) {
		if (doc.select("#product").isEmpty())
			return false;
		return true;
	}
	public static boolean urlFilterForSesamethestylestudio(Document doc, String url) {
		if (doc.select("#product").isEmpty() || doc.select("#content").isEmpty())
			return false;
		return true;
	}
	
}
