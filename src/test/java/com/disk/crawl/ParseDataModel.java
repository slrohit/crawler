package com.disk.crawl;

public class ParseDataModel {
	private String relativeUrl;
	private String productName;
	private String productUrl;
	private String sku;
	private String mrp;
	private String sp;
	private String description;
	private String detail;
	private String sizes;
	private String material;
	private String careInstruction;
	private String imgUrls;
	private String retailer;
	private String brand;
	private String colorText;
	private String colorCode;
	private String breadCrumb;
	private String category;
	private String subCategory;
	private String fabric;
	private String patternOrDetailing;
	private String delivery;
	private String returnPolicy;
	private String sizeChart;

	public ParseDataModel() {
		relativeUrl = "";
		productName = "";
		productUrl = "";
		sku = "";
		mrp = "";
		sp = "";
		description = "";
		detail = "";
		sizes = "";
		material = "";
		careInstruction = "";
		imgUrls = "";
		retailer = "";
		brand = "";
		colorText = "";
		colorCode = "";
		breadCrumb = "";
		category = "";
		subCategory = "";
		fabric = "";
		patternOrDetailing = "";
		delivery = "";
		returnPolicy = "";
	}

	public String getRelativeUrl() {
		return relativeUrl;
	}

	public void setRelativeUrl(String relativeUrl) {
		this.relativeUrl = relativeUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getCareInstruction() {
		return careInstruction;
	}

	public void setCareInstruction(String careInstruction) {
		this.careInstruction = careInstruction;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColorText() {
		return colorText;
	}

	public void setColorText(String colorText) {
		this.colorText = colorText;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getBreadCrumb() {
		return breadCrumb;
	}

	public void setBreadCrumb(String breadCrumb) {
		this.breadCrumb = breadCrumb;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getFabric() {
		return fabric;
	}

	public void setFabric(String fabric) {
		this.fabric = fabric;
	}

	public String getPatternOrDetailing() {
		return patternOrDetailing;
	}

	public void setPatternOrDetailing(String patternOrDetailing) {
		this.patternOrDetailing = patternOrDetailing;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getReturnPolicy() {
		return returnPolicy;
	}

	public void setReturnPolicy(String returnPolicy) {
		this.returnPolicy = returnPolicy;
	}

	public String getSizeChart() {
		return sizeChart;
	}

	public void setSizeChart(String sizeChart) {
		this.sizeChart = sizeChart;
	}

	@Override
	public String toString() {
		return "ParseDataModel [productName=" + productName + ", productUrl=" + productUrl + ", sku=" + sku + ", mrp="
				+ mrp + ", sp=" + sp + ", description=" + description + ", detail=" + detail + ", sizes=" + sizes
				+ ", material=" + material + ", careInstruction=" + careInstruction + ", imgUrls=" + imgUrls
				+ ", retailer=" + retailer + ", brand=" + brand + ", colorText=" + colorText + ", colorCode="
				+ colorCode + ", breadCrumb=" + breadCrumb + ", category=" + category + ", subCategory=" + subCategory
				+ ", fabric=" + fabric + ", patternOrDetailing=" + patternOrDetailing + ", delivery=" + delivery
				+ ", returnPolicy=" + returnPolicy + ", sizeChart=" + sizeChart + "]";
	}
}
