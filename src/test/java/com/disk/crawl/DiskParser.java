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
		String subUrl=url.substring(url.lastIndexOf("/")+1);
		System.out.println(url);
		System.out.println(path);
		System.out.println(subUrl);
		Document doc = Jsoup.parse(html);
		ParseDataModel data = null;
		switch (Config.SITE.val()) {
		case "koovs":
			data = FilterUtils.urlFilterForKoovs(doc, url) ? ParseUtils.getParseDataModelForKoovs(doc,url) : null;
			break;
		case "jabong":
			data = FilterUtils.urlFilterForJabong(doc, url) ? ParseUtils.getParsedDataModelForJabong(doc,url) : null;
			break;
		case "faballey":
			data = FilterUtils.urlFilterForFaballey(doc, url) ? ParseUtils.getParsedDataModelForFaballey(doc,url) : null;
			break;
		case "6ycollective":
			data = FilterUtils.urlFilterFor6ycollective(doc, url) ? ParseUtils.getParseDataModelFor6ycollective(doc,url)
					: null;
			break;
		case "fleaffair":
			data = FilterUtils.urlFilterForFleaffair(doc, url) ? ParseUtils.getParseDataModelForFleaffair(doc,url) : null;
			break;
		case "thelabellife":
			data = FilterUtils.urlFilterForTheLabelLife(doc, url) ? ParseUtils.getParseDataModelForThelabellife(doc,url)
					: null;
			break;
		case "vajor":
			data = FilterUtils.urlFilterForVajor(doc, url) ? ParseUtils.getParseDataModelForVajor(doc,url) : null;
			break;
		case "20dresses":
			data = FilterUtils.urlFilterFor20dresses(doc, url) ? ParseUtils.getParseDataModelFor20dresses(doc, url)
					: null;
			break;
		case "craftisan":
			data = FilterUtils.urlFilterForCraftisan(doc, url) ? ParseUtils.getParseDataModelForCraftisan(doc,url) : null;
			break;
		case "sesamethestylestudio":
			data = FilterUtils.urlFilterForSesamethestylestudio(doc, url)
					? ParseUtils.getParseDataModelForSesamethestylestudio(doc,url) : null;
			break;
		case "jaypore":
			data = FilterUtils.urlFilterForJaypore(doc, url) ? ParseUtils.getParseDataModelForJaypore(doc,url) : null;
			break;
		default:
			break;
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
