package com.disk.crawl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup
				.connect("http://sesamethestylestudio.com/index.php?route=product/product&path=60_90&product_id=1775")
				.timeout(5000).get();
		//System.out.println(doc.toString());
		ParseDataModel data = new ParseDataModel();
		Elements elements;
		StringBuilder sbd;
		data.setBreadCrumb(doc.select(".breadcrumb").text());
		sbd = new StringBuilder("[");
		elements = doc.select(".MagicToolboxSelectorsContainer a");
		String comma = "";
		for (Element ele : elements) {
			sbd.append(comma);
			sbd.append(ele.attr("href"));
			if (comma.equals("")) {
				comma = ",";
			}
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		data.setProductName(doc.select("h1").text());
		elements = doc.select(".list-unstyled li");
		for (Element ele : elements) {
			String key = ele.text();
			if (!key.isEmpty() && key.contains("INR") && !key.toLowerCase().contains("tax")) {
				data.setSp(key.replaceAll("[^\\d.]", ""));
			}
			if (!key.isEmpty() && key.toLowerCase().contains("product code")) {
				data.setSku(key.substring(key.indexOf(":")+1).replaceAll("\\s+", ""));
			}
		}
		elements = doc.select("#tab-description p");
		for(Element ele:elements){
			String key=ele.text();
			if(!key.isEmpty() && key.toLowerCase().contains("material")){
				data.setMaterial(key.substring(key.indexOf(":")+1));
			}
		}
		elements=doc.select("#product label");
		for(Element ele:elements){
			String key=ele.text();
			if(!key.isEmpty() && key.toLowerCase().contains("size")){
				Element ele1=ele.nextElementSibling();
				Elements eles=ele1.select("option");
				if(!eles.isEmpty()){
					sbd= new StringBuilder("[");
					comma="";
					for(Element e:eles){
						sbd.append(comma);
						sbd.append(e.text());
						if(comma.equals("")){
							comma=",";
						}
					}
					sbd.append("]");
					data.setSizes(sbd.toString());
				}
			}
			if(!key.isEmpty() && key.toLowerCase().contains("color")){
				Element ele1=ele.nextElementSibling();
				Elements eles=ele1.select("option");
				if(!eles.isEmpty()){
					sbd= new StringBuilder("[");
					comma="";
					for(Element e:eles){
						sbd.append(comma);
						sbd.append(e.text());
						if(comma.equals("")){
							comma=",";
						}
					}
					sbd.append("]");
					data.setColorText(sbd.toString());
				}
			}
		}
		System.out.println(data.toString());

	}
}
