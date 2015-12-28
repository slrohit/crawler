package com.disk.crawl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseUtils {
	public static ParseDataModel getParsedDataModelForJabong(Document doc, String url) {
		Elements elements;
		StringBuilder sbd = new StringBuilder();
		ParseDataModel data = new ParseDataModel();
		data.setRetailer("Jabong");
		data.setBrand(doc.select(".content h1 .brand").text());
		data.setProductName(doc.select(".content h1 .product-title").text());
		data.setSp(doc.select(".actual-price").text());
		data.setMrp(doc.select(".standard-price ").text());
		data.setSizeChart(doc.select(".one-dropdown  a.dialogify").attr("data-chart"));
		data.setDelivery(doc.select("#pdp-tat-tool-tip.delivery-info").text());
		data.setReturnPolicy("");
		String prevLink = doc.select("#product-details-wrapper .product-details a.back-page").attr("href");
		if (prevLink != null) {
			data.setBreadCrumb(prevLink.substring(21));
		}
		elements = doc.select(".size  ul.options li");
		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).text());
			if (i == elements.size() - 1)
				continue;
			sbd.append(",");
		}
		sbd.append("]");
		data.setSizes(sbd.toString());
		elements = doc.select(".product-image img");
		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).attr("data-src-500").toString());
			if (i == elements.size() - 1)
				continue;
			sbd.append(",");
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		data.setDetail(doc.select(".prod-info .detail").text());
		elements = doc.select(".prod-info .prod-main-wrapper li");
		sbd = new StringBuilder();
		sbd.append("{");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).select("label").text());
			sbd.append(":");
			sbd.append(elements.get(i).select("span").text());
			if (i == elements.size() - 1)
				continue;
			sbd.append(",");

			String label = elements.get(i).select("label").text();
			String val = elements.get(i).select("span").text();
			if (!label.isEmpty()) {
				label = label.replaceAll("\\s+", "");
				if (label.equalsIgnoreCase("fabric")) {
					data.setMaterial(val);
				}
				if (label.equalsIgnoreCase("WashCare")) {
					data.setCareInstruction(val);
				}
				if (label.equalsIgnoreCase("Color")) {
					data.setColorText(val);
				}
				if (label.equalsIgnoreCase("Style")) {
					data.setPatternOrDetailing(val);
				}
				if (label.equalsIgnoreCase("SKU")) {
					data.setSku(val);
				}
			}

		}
		sbd.append("}");
		data.setDescription(sbd.toString());
		elements = doc.select(".color .color-drop-down option");
		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 1; i < elements.size(); i++) {
			if (!elements.get(i).attr("value").isEmpty()) {
				String color = elements.get(i).attr("value").toString();
				sbd.append(color);
				sbd.append(",");
			}
			if (i == elements.size() - 1)
				continue;
		}
		sbd.append("]");
		data.setColorText(sbd.toString());

		elements = doc.select(".color .options li .option a");

		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 1; i < elements.size(); i++) {
			if (!elements.get(i).attr("style").isEmpty()) {
				String style = elements.get(i).attr("style").toString();
				if (!style.isEmpty()) {
					String[] temp = style.split(":");
					sbd.append(temp[1]);
				}
			}
			if (i == elements.size() - 1)
				continue;
			sbd.append(",");
		}
		sbd.append("]");
		data.setColorCode(sbd.toString());
		return data;
	}

	public static ParseDataModel getParseDataModelForKoovs(Document doc, String url) {
		Elements elements;
		StringBuilder sbd = new StringBuilder();
		ParseDataModel data = new ParseDataModel();

		data.setProductName(doc.select("div.productTitle .product-name").text());

		data.setSku(doc.select("span.prdDescInfoBlk strong").text());

		data.setMrp(doc.select("div.product-price strong").text());

		elements = doc.select("#product-desc .short_detail a");
		data.setCategory(elements.get(0).text());
		data.setBrand(elements.get(1).text());
		data.setRetailer("koovs");
		data.setDescription(doc.select("#product-desc ul").text());
		data.setCareInstruction(doc.select(".tabcontent #tab_info_care").text());
		data.setDetail(doc.select(".tabcontent #style_tip_tab").text());
		data.setFabric(doc.select(".tabcontent #style_tip_tab .fabric").text());
		data.setDelivery(doc.select(".tabcontent #delivery_tab").text());
		data.setReturnPolicy(doc.select(".tabcontent #returns_tab").text());
		data.setBreadCrumb(doc.select("#breadcrumb").text());
		elements = doc.select("meta[property='og:image']");
		data.setColorText(doc.select(".colorselection .selected").attr("original-color").toString());
		elements = doc.select(".colorselection li");
		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 0; i < elements.size(); i++) {
			String style = elements.get(i).select("span").get(0).attr("style").toString();
			String[] temp = style.split(":");
			if (temp.length >= 2) {
				sbd.append(temp[1]);
			}
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setColorCode(sbd.toString());

		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 0; i < elements.size(); i++) {
			String style = elements.get(i).attr("original-color").toString();
			sbd.append(style);
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setColorText(sbd.toString());

		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).attr("content").toString());
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		elements = doc.select(".select-size option");
		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 1; i < elements.size(); i++) {
			sbd.append(elements.get(i).text());
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setSizes(sbd.toString());
		return data;
	}

	public static ParseDataModel getParsedDataModelForFaballey(Document doc, String url) {
		Elements elements;
		StringBuilder sbd;
		ParseDataModel data = new ParseDataModel();
		data.setProductName(doc.select("div.prodRightSec .title7 h1").text());
		data.setSku(doc.select(".proSkuid").text());
		elements = doc.select(".sliderDesktop li a img");
		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 0; i < elements.size(); i++) {
			String imgUrl = elements.get(i).attr("src").toString();
			if (imgUrl.contains("http://") || imgUrl.contains("http://")) {
				sbd.append(imgUrl);
			} else {
				sbd.append("http://www.faballey.com/" + imgUrl);
			}
			if (i == elements.size() - 1)
				continue;
			sbd.append(",");
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		data.setBreadCrumb(doc.select(".homeTop").text());
		data.setMrp(doc.select(".prodRightSec .title7 h3 span").eq(1).text());
		data.setSp(doc.select(".prodRightSec .title7 h3").text());
		data.setDescription(doc.select(".proText").text());

		elements = doc.select(".sizes .SizeChart");
		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).text());
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setSizes(sbd.toString());
		elements = doc.select(".proContent");
		data.setDetail(elements.get(0).text());
		data.setCareInstruction(elements.get(1).text());
		data.setDelivery(elements.get(2).text());
		data.setBrand("Faballey");
		data.setRetailer("Faballey");
		return data;
	}

	public static ParseDataModel getParseDataModelFor6ycollective(Document doc, String url) {
		Elements elements;
		StringBuilder sbd;
		ParseDataModel data = new ParseDataModel();
		data.setProductName(doc.select(".product-info .product-name").text());
		data.setRetailer("6ycollective");
		data.setSku(doc.select(".product-code").text());
		elements = doc.select("select.js-product-size option");
		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 1; i < elements.size(); i++) {
			sbd.append(elements.get(i).text());
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setSizes(sbd.toString());

		data.setDescription(doc.select(".description").text());

		data.setSp(doc.select(".display-price").text());

		elements = doc.select(".photo-thumbs img");
		sbd = new StringBuilder();
		sbd.append("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).attr("src").toString());
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		return data;
	}

	public static ParseDataModel getParseDataModelForFleaffair(Document doc, String url) {
		Elements elements;
		StringBuilder sbd;
		ParseDataModel data = new ParseDataModel();
		data.setRetailer("fleaffair");
		elements = doc.select(".breadcrumbs-grid a");
		sbd = new StringBuilder();
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).text());
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(">");
		}
		data.setBreadCrumb(sbd.toString());

		data.setProductName(doc.select("h1.mainbox-title").text());

		data.setSp(doc.select(".product-prices .actual-price .price span").eq(1).text());

		data.setMrp(doc.select(".product-prices .strike span").eq(1).text());

		data.setReturnPolicy(doc.select("#content_block_popup_11 p").eq(0).text());

		elements = doc.select(".product-info table tr");
		for (int i = 0; i < elements.size(); i++) {
			Elements eles = elements.get(i).select("td");
			String key = eles.eq(0).text().toLowerCase();
			String val = eles.eq(1).text();
			if (key.contains("vendor")) {
				data.setBrand(val);
			}
			if (key.contains("sku")) {
				data.setSku(val);
			}
		}

		data.setDescription(doc.select("#content_description").text());

		elements = doc.select("#content_features label");
		for (int i = 0; i < elements.size(); i++) {
			String key = elements.get(i).text().toLowerCase();
			Element el = elements.get(i).nextElementSibling();
			Elements eles = el.select("li");
			sbd = new StringBuilder();
			sbd.append("[");
			for (int j = 0; j < eles.size(); j++) {
				sbd.append(eles.get(j).text());
				if (j == eles.size() - 1) {
					continue;
				}
				sbd.append(",");
			}
			sbd.append("]");
			if (key.contains("colors")) {
				data.setColorText(sbd.toString());
			}
			if (key.contains("fabric")) {
				data.setFabric(sbd.toString());
			}
			if (key.contains("kaarigari")) {
				data.setPatternOrDetailing(sbd.toString());
			}
		}
		sbd = new StringBuilder();
		sbd.append("[");
		sbd.append(doc.select(".image-wrap img").attr("src"));
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		return data;
	}

	public static ParseDataModel getParseDataModelForThelabellife(Document doc, String url) {
		Elements elements;
		StringBuilder sbd;
		ParseDataModel data = new ParseDataModel();
		data.setRetailer("thelabellife");
		data.setBrand("thelabellife");
		data.setBreadCrumb(doc.select(".breadcrumbs").text());

		data.setProductName(doc.select(".product-shop .product-name").text());

		String sp = doc.select(".product-shop .price-box .price-new").text();
		String mrp = doc.select(".product-shop .price-box .price").text();
		if (sp.isEmpty()) {
			sp = mrp;
		}
		if (!sp.isEmpty()) {
			data.setSp(sp.replaceAll("[^\\d.]", ""));

		}
		if (!mrp.isEmpty()) {
			data.setMrp(mrp.replaceAll("[^\\d.]", ""));
		}

		data.setDescription(doc.select(".notAccContent").text());

		elements = doc.select(".accordion-toggle");
		for (int i = 0; i < elements.size(); i++) {
			Element ele = elements.get(i);
			if (ele.text().contains("PRODUCT INFORMATION")) {
				data.setDetail(ele.nextElementSibling().text());
				data.setMaterial(ele.nextElementSibling().text());
				data.setCareInstruction(ele.nextElementSibling().text());
			}
			if (ele.text().contains("Shipping & Return Policy")) {
				data.setReturnPolicy(ele.nextElementSibling().text());
				data.setDelivery(ele.nextElementSibling().text());
			}
		}

		elements = doc.select("#galleria img");
		sbd = new StringBuilder("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).attr("src"));
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		return data;
	}

	public static ParseDataModel getParseDataModelForVajor(Document doc, String url) {
		Elements elements;
		StringBuilder sbd;
		ParseDataModel data = new ParseDataModel();
		data.setRetailer("vajor");
		data.setBrand("vajor");
		data.setProductName(doc.select(".product-page .prod-sidebar-inner form h1").eq(0).text());

		elements = doc.select(".product-page #amasty_gallery img");
		sbd = new StringBuilder("[");
		for (int i = 1; i < elements.size() - 1; i++) {
			sbd.append(elements.get(i).attr("src"));
			if (i == elements.size() - 2) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());

		elements = doc.select(".input-box select option");
		sbd = new StringBuilder("[");
		for (int i = 1; i < elements.size(); i++) {
			sbd.append(elements.get(i).text());
			if (i == elements.size() - 2) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setSizes(sbd.toString());

		data.setDetail(doc.select(".material-list").text());

		data.setDescription(doc.select(".guid-desc p").text());
		data.setSizes(doc.select(".guid-desc.withbelowline").text());
		String sp = "";
		String mrp = "";
		elements = doc.select(".prod-price .price");
		if (elements.size() > 1) {
			mrp = elements.eq(0).text().replaceAll("[^\\d.]", "");
			sp = elements.eq(1).text().replaceAll("[^\\d.]", "");
		} else {
			mrp = sp = elements.eq(0).text().replaceAll("[^\\d.]", "");
		}
		data.setSp(sp);
		data.setMrp(mrp);
		return data;
	}

	public static ParseDataModel getParseDataModelForJaypore(Document doc, String url) {
		Elements elements;
		StringBuilder sbd;
		ParseDataModel data = new ParseDataModel();
		data.setRetailer("jaypore");
		data.setBrand(doc.select(".prodDetail .rgtPanel .productName span").text());
		data.setProductName(doc.select(".prodDetail .rgtPanel .productName").text());

		elements = doc.select(".prodDetail .lftPanel img");
		sbd = new StringBuilder("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).attr("src"));
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		elements = doc.select(".moreDetails dl dt");
		for (int i = 0; i < elements.size(); i++) {
			Element ele = elements.get(i);
			String key = ele.text();
			String val = ele.nextElementSibling().text();
			if (key.contains("SHIPPING")) {
				data.setDelivery(val);
			}
			if (key.contains("COLOR")) {
				data.setColorText(val);
			}
			if (key.contains("MATERIAL")) {
				data.setMaterial(val);
			}
			if (key.contains("CARE")) {
				data.setCareInstruction(val);
			}
			if (key.contains("SKU")) {
				data.setSku(val);
			}
		}
		data.setDescription(doc.select(".prdDisc li").eq(0).text());
		data.setSp(doc.select(".price #dPrice").text());
		data.setMrp(doc.select(".old-price .line-thru").text());
		return data;
	}

	public static ParseDataModel getParseDataModelFor20dresses(Document doc, String url) {
		Elements elements;
		StringBuilder sbd;
		ParseDataModel data = new ParseDataModel();
		data.setBreadCrumb(url.substring("http://www.20dresses.com/".length()));
		data.setRetailer("20dresses");
		data.setBrand("20dresses");
		data.setProductName(doc.select(".product_info_main .productname_container").text());
		elements = doc.select("#sizemain li .my-size");
		sbd = new StringBuilder("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).text());
			if (i == elements.size() - 1)
				continue;
			sbd.append(",");
		}
		sbd.append("]");
		data.setSizes(sbd.toString());
		elements = doc.select(".product_image li").eq(0).select("img");
		sbd = new StringBuilder("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).attr("src"));
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		elements = doc.select(".attribute_main .attribute_left");
		for (int i = 0; i < elements.size(); i++) {
			Element ele = elements.get(i);
			String key = ele.text();

			if (key.contains("COLOUR")) {
				String val = ele.nextElementSibling().text();
				data.setColorText(val);
			}
			if (key.contains("MATERIAL")) {
				String val = ele.nextElementSibling().text();
				data.setMaterial(val);
			}
		}
		data.setCareInstruction(doc.select(".TabbedPanelsContent").eq(2).text());
		data.setDescription(doc.select(".prdDisc li").eq(0).text());
		data.setSp(doc.select(".price_info .main_price").text().replaceAll("[^\\d.]", ""));
		data.setMrp(doc.select(".price_info .price_cut").text().replaceAll("[^\\d.]", ""));
		data.setDetail(doc.select(".TabbedPanelsContent").eq(1).text());
		return data;
	}

	public static ParseDataModel getParseDataModelForCraftisan(Document doc, String url) {
		Elements elements;
		StringBuilder sbd;
		ParseDataModel data = new ParseDataModel();
		data.setRetailer("craftisan");
		data.setBrand("craftisan");
		data.setBreadCrumb(doc.select("#breadcrumb").text());
		data.setProductName(doc.select("#product h1").text());
		elements = doc.select(".attribute_div .attribute_box");
		sbd = new StringBuilder("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).text());
			if (i == elements.size() - 1)
				continue;
			sbd.append(",");
		}
		sbd.append("]");
		data.setSizes(sbd.toString());
		elements = doc.select("#gallery li a");
		sbd = new StringBuilder("[");
		for (int i = 0; i < elements.size(); i++) {
			sbd.append(elements.get(i).attr("href"));
			if (i == elements.size() - 1) {
				continue;
			}
			sbd.append(",");
		}
		sbd.append("]");
		data.setImgUrls(sbd.toString());
		data.setSku(doc.select(".product_pricecon .product_priceright span").text());
		String spString = doc.select(".product_priceleft .price").text();
		if (!spString.isEmpty()) {
			System.out.println(spString);
			data.setSp(spString.replaceAll("[^\\d.]", ""));
		}

		int idx = -1;
		int idx2 = -1;
		elements = doc.select("#prodDetM li");
		for (int i = 0; i < elements.size(); i++) {
			String tab = elements.get(i).text();
			if (!tab.isEmpty() && tab.toLowerCase().contains("details")) {
				idx = i + 1;
			}
			if (!tab.isEmpty() && tab.toLowerCase().contains("delivery")) {
				idx2 = i + 1;
			}
		}
		if (idx != -1) {
			elements = doc.select("#ptab-" + Integer.toString(idx) + " p");
			for (int i = 0; i < elements.size(); i++) {
				Element ele = elements.get(i);
				String key = ele.select("strong").text();
				if (!key.isEmpty()) {
					if (key.toLowerCase().contains("care")) {
						data.setCareInstruction(ele.text());
					}
					if (key.toLowerCase().contains("color")) {
						data.setColorText(ele.text());
					}
					if (key.toLowerCase().contains("material")) {
						data.setMaterial(ele.text());
					}
					if (key.toLowerCase().contains("craft process")) {
						data.setPatternOrDetailing(ele.text());
					}
				}
			}
		}
		if (idx2 != -1) {
			data.setDelivery(doc.select("#ptab-" + Integer.toString(idx2) + " p").eq(0).text());
		}
		return data;
	}

	public static ParseDataModel getParseDataModelForSesamethestylestudio(Document doc, String url) {
		ParseDataModel data = new ParseDataModel();
		Elements elements;
		StringBuilder sbd;
		data.setRetailer("sesamethestylestudio");
		data.setBrand("sesamethestylestudio");
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
				data.setSku(key.substring(key.indexOf(":") + 1).replaceAll("\\s+", ""));
			}
		}
		elements = doc.select("#tab-description p");
		for (Element ele : elements) {
			String key = ele.text();
			if (!key.isEmpty() && key.toLowerCase().contains("material")) {
				data.setMaterial(key.substring(key.indexOf(":") + 1));
			}
		}
		elements = doc.select("#product label");
		for (Element ele : elements) {
			String key = ele.text();
			if (!key.isEmpty() && key.toLowerCase().contains("size")) {
				Element ele1 = ele.nextElementSibling();
				Elements eles = ele1.select("option");
				if (!eles.isEmpty()) {
					sbd = new StringBuilder("[");
					comma = "";
					for (Element e : eles) {
						if (e.text().toLowerCase().contains("select"))
							continue;
						sbd.append(comma);
						sbd.append(e.text());
						if (comma.equals("")) {
							comma = ",";
						}
					}
					sbd.append("]");
					data.setSizes(sbd.toString());
				}
			}
			if (!key.isEmpty() && key.toLowerCase().contains("color")) {
				Element ele1 = ele.nextElementSibling();
				Elements eles = ele1.select("option");
				if (!eles.isEmpty()) {
					sbd = new StringBuilder("[");
					comma = "";
					for (Element e : eles) {
						if (e.text().toLowerCase().contains("select"))
							continue;
						sbd.append(comma);
						sbd.append(e.text());
						if (comma.equals("")) {
							comma = ",";
						}
					}
					sbd.append("]");
					data.setColorText(sbd.toString());
				}
			}
		}
		return data;
	}

	public static ParseDataModel getParseDataModelForMirraw(Document doc, String url) {
		ParseDataModel data = new ParseDataModel();
		data.setProductName(doc.select(".heading .design_title").text());
		data.setMrp(doc.select(".discount_old_price .old_price_label").text().replaceAll("[^\\d.]", ""));
		data.setSp(doc.select(".discount_old_price h3.floatl").text().replaceAll("[^\\d.]", ""));
		Elements elements = doc.select("#spec-1 ul.product_specif_detail li");
		for (Element element : elements) {
			String key = element.select("label").text();
			String val = element.select("span").text();
			switch (key) {
			case "Product ID":
				data.setSku(val);
				break;
			case "Type":
				data.setSubCategory(val);
				break;
			case "Fabric of saree :":
			case "Blouse fabric :":
			case "Lehenga fabric :":
			case "Fabric :":
			case "Kameez fabric :":
			case "Base material :":
			case "Material bag :":
				data.setMaterial(val);
				break;
			case "Returns":
				data.setReturnPolicy(val);
				break;
			case "Shipping":
				data.setDelivery(val);
				break;
			case "Saree color :":
			case "color :":
			case "Kameez color :":
			case "Lehenga color :":
			case "Dupatta color :":
			case "Blouse color :":
			case "Bottom color :":
				data.setColorText(val);
				break;
			case "Look :":
			case "Work :":
			case "Stitching :":
			case "Lehenga style :":
			case "Pattern :":
				val = !data.getPatternOrDetailing().isEmpty() ? val + ", " + data.getPatternOrDetailing() : val;
				data.setPatternOrDetailing(val);
				break;
			case "care":
				data.setCareInstruction(val);
			default:
				break;
			}
		}

		elements = doc.select("#fancybox-thumbs li img");
		if (!elements.isEmpty()) {
			StringBuilder sbd = new StringBuilder("[");
			String pref = "";
			for (Element element : elements) {
				sbd.append(pref);
				pref = ",";
				sbd.append(element.attr("src"));
			}
			sbd.append("]");
			data.setImgUrls(sbd.toString());
		} else {
			data.setImgUrls("[" + doc.select(".large_img.floatl img").attr("src") + "]");
		}
		elements = doc.select(".right_description_wrapper li");
		if (!elements.isEmpty()) {
			StringBuilder sbd = new StringBuilder("[");
			String pref = "";
			for (Element element : elements) {
				if (element.select("del").isEmpty()) {
					sbd.append(pref);
					pref = ",";
					sbd.append(element.text());
				}
			}
			sbd.append("]");
			data.setSizes(sbd.toString());
		}
		return data;
	}

	public static ParseDataModel getParseDataModelForStalkBuyLove(Document doc, String url) {
		ParseDataModel data = new ParseDataModel();
		data.setProductName(doc.select(".name-write-box h1").text());
		data.setBreadCrumb(doc.select(".breadcrumbs ul").text());
		if (!doc.select(".name-price-box .old-price").isEmpty()) {
			data.setMrp(doc.select(".name-price-box .striken-price").text().replaceAll("[^\\d.]", ""));
			data.setSp(doc.select(".name-price-box .special_new_price").text().replaceAll("[^\\d.]", ""));
		} else {
			data.setMrp(doc.select(".name-price-box .regular-price .price").text().replaceAll("[^\\d.]", ""));
			data.setSp(doc.select(".name-price-box .regular-price .price").text().replaceAll("[^\\d.]", ""));
		}
		Elements elements = doc.select("#prodSizeSelect option");
		if (!elements.isEmpty()) {
			elements.remove(0);
			if (!elements.isEmpty()) {
				StringBuilder sbd = new StringBuilder("[");
				String pref = "";
				for (Element element : elements) {
					sbd.append(pref);
					pref = ",";
					sbd.append(element.text());
				}
				sbd.append("]");
				data.setSizes(sbd.toString());
			}
		}

		elements = doc.select("#prodColorSelect option");
		if (!elements.isEmpty()) {
			StringBuilder sbd = new StringBuilder("[");
			String pref = "";
			for (Element element : elements) {
				sbd.append(pref);
				pref = ",";
				sbd.append(element.text());
			}
			sbd.append("]");
			data.setColorText(sbd.toString());
		}
		data.setReturnPolicy(doc.select(".cro_extra_info").text().trim());
		String skuid = doc.select("#cro_prod_test_det_content p").eq(0).text();
		String sku = doc.select("#cro_prod_test_det_content p span").eq(0).text();
		if (!skuid.isEmpty() && !sku.isEmpty()) {
			skuid = skuid.replaceAll(sku, "").trim();
			data.setSku(skuid);
		}
		data.setDetail(doc.select(".info_block_content .bulleted_desc").text());
		return data;
	}

	public static ParseDataModel getParseDataModelForCbazaar(Document doc, String url) {
		ParseDataModel data = new ParseDataModel();
		data.setProductName(doc.select(".prodDetails h1 span").text());
		data.setBreadCrumb(doc.select(".breadCrum ul").text());
		data.setPatternOrDetailing(doc.select(".prodDetails .showMorewrpper .visibleText p").text());
		String price = doc.select("#h5Amount").text().replaceAll("[^\\d.]", "");
		price = moneyFormat(price);
		data.setMrp(price);
		data.setSp(price);
		data.setDelivery(doc.select(".wishlistBox .dispatchdate").text());
		Elements elements = doc.select(".wishlistBox ul.prodInfo li.details li");
		for (Element element : elements) {
			String key = element.select("span").text().trim();
			String val = element.select("strong").text().trim();
			switch (key) {
			case "Fabric":
				data.setMaterial(val);
				break;
			case "Color":
				data.setColorText(val);
				break;
			case "Designer":
				data.setBrand(val);
				break;
			default:
				break;
			}
		}
		data.setCareInstruction(doc.select("#MoreonPrds #accordion13 p").eq(1).text());
		elements = doc.select(".smallThumbnailWrapper .samllThumbnail img");
		if (!elements.isEmpty()) {
			StringBuilder sbd = new StringBuilder("[");
			String pref = "";
			for (Element element : elements) {
				sbd.append(pref);
				pref = ",";
				sbd.append(element.attr("largeimage"));
			}
			sbd.append("]");
			data.setImgUrls(sbd.toString());
		}else{
			data.setImgUrls(doc.select("#imgPrdTopLeft").attr("largeimage"));
		}
		elements = doc.select("#MoreonPrds .panel-group .panel-body");
		for(Element element : elements){
			if(element.select("p").eq(0).text().equalsIgnoreCase("Wash Care 3")){
				data.setCareInstruction(element.select("p").eq(1).text());
			}
		}
		return data;
	}
	
	public static String moneyFormat(String amount){
		boolean flag=true;
		if(amount.charAt(0) == '.'){
			flag = false;
			amount = amount.substring(1);
		}
		if(amount.charAt(amount.length() - 1) == '.'){
			flag = false;
			amount = amount.substring(0,amount.length()-2);
		}
		if(flag){
			return amount;
		}
		return moneyFormat(amount);
	}
}
