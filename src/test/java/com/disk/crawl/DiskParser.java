package com.disk.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;

public class DiskParser {
	private static int cnt = 0;

	public static void parse(Page page) {
		HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		String html = htmlParseData.getHtml();
		String url = page.getWebURL().getURL();
		String path = page.getWebURL().getPath();
		String subUrl = path.substring(1);

		System.out.println(url);
		System.out.println(path);
		System.out.println(subUrl);
		Document doc = Jsoup.parse(html);
		if (url.contains("www.koovs.com") && !FilterUtils.urlFilterForKoovs(doc, url)) {
			return;
		}
		if (url.contains("www.jabong.com") && !FilterUtils.urlFilterForJabong(doc, url)) {
			return;
		}
		if (url.contains("www.faballey.com") && !FilterUtils.urlFilterForFaballey(doc, url)) {
			return;
		}
		if (url.contains("www.6ycollective.com") && !FilterUtils.urlFilterFor6ycollective(doc, url)) {
			return;
		}
		if (url.contains("www.fleaffair.com") && !FilterUtils.urlFilterForFleaffair(doc, url)) {
			return;
		}
		if (url.contains("www.thelabellife.com") && !FilterUtils.urlFilterForTheLabelLife(doc, url)) {
			return;
		}
		if (url.contains("www.vajor.com") && !FilterUtils.urlFilterForVajor(doc, url)) {
			return;
		}
		if (url.contains("www.jaypore.com") && !FilterUtils.urlFilterForJaypore(doc, url)) {
			return;
		}
		if (url.contains("www.20dresses.com") && !FilterUtils.urlFilterFor20dresses(doc, url)) {
			return;
		}
		if (url.contains("craftisan.in") && !FilterUtils.urlFilterForCraftisan(doc, url)) {
			return;
		}
		if (url.contains("sesamethestylestudio.com") && !FilterUtils.urlFilterForSesamethestylestudio(doc, url)) {
			return;
		}

		ParseDataModel data = null;
		
		if (url.contains("www.koovs.com")) {
			data = ParseUtils.getParseDataModelForKoovs(doc);
		}
		if (url.contains("www.jabong.com")) {
			data = ParseUtils.getParsedDataModelForJabong(doc);
		}
		if (url.contains("www.faballey.com")) {
			data = ParseUtils.getParsedDataModelForFaballey(doc);
		}
		if (url.contains("www.6ycollective.com")) {
			data = ParseUtils.getParseDataModelFor6ycollective(doc);
		}
		if (url.contains("www.fleaffair.com")) {
			data = ParseUtils.getParseDataModelForFleaffair(doc);
		}
		if (url.contains("www.thelabellife.com")) {
			data = ParseUtils.getParseDataModelForThelabellife(doc);
		}
		if (url.contains("www.vajor.com")) {
			data = ParseUtils.getParseDataModelForVajor(doc);
		}
		if (url.contains("www.jaypore.com")) {
			data = ParseUtils.getParseDataModelForJaypore(doc);
		}
		if (url.contains("www.20dresses.com")) {
			data = ParseUtils.getParseDataModelFor20dresses(doc, url);
		}
		if (url.contains("craftisan.in")) {
			data = ParseUtils.getParseDataModelForCraftisan(doc);
		}
		if (url.contains("sesamethestylestudio.com")) {
			data = ParseUtils.getParseDataModelForSesamethestylestudio(doc);
			subUrl=url.substring(url.lastIndexOf("/")+1);
		}

		if (data != null) {
			data.setProductUrl(url);
			InsertScrapeData.save(data, subUrl);
			cnt++;
			System.out.println(
					"===================================================================================================");
			System.out.println(cnt + " pages inserted");
			System.out.println("For URL " + url);
			System.out.println(
					"===================================================================================================");
		}
	}
}
