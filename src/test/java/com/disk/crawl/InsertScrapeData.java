package com.disk.crawl;

import java.sql.SQLException;
import java.util.Date;

public class InsertScrapeData {
	/*
	 * private static final String DB_DRIVER = Config.dbdriver; private static
	 * final String DB_CONNECTION = Config.database; private static final String
	 * DB_USER = Config.dbuser; private static final String DB_PASSWORD =
	 * Config.dbpassword;
	 */

	public static void save(ParseDataModel data) {

		try {

			insertRecordIntoTable(data);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

	}

	private static void insertRecordIntoTable(ParseDataModel data) throws SQLException {
		java.sql.Connection dbConnection = null;
		java.sql.PreparedStatement preparedStatement = null;
		StringBuilder sbd = new StringBuilder();
		sbd.append(
				"INSERT INTO scrape_data3(id,product_name, product_url, sku, mrp, sp, description, detail, sizes, material, care_instruction, img_urls, retailer, brand, color_text, color_code, bread_crumb, category, sub_category, fabric, pattern_or_detailing, delivery, return_policy, page_hash,lastUpdated) VALUES");
		sbd.append("(md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,md5(?),?)");
		sbd.append("ON DUPLICATE KEY UPDATE ");
		sbd.append("product_name=VALUES(product_name), product_url=VALUES(product_url), sku=VALUES(sku), mrp=VALUES(mrp), sp=VALUES(sp), description=VALUES(description), detail=VALUES(detail), sizes=VALUES(sizes), material=VALUES(material), care_instruction=VALUES(care_instruction), img_urls=VALUES(img_urls), retailer=VALUES(retailer), brand=VALUES(brand), color_text=VALUES(color_text), color_code=VALUES(color_code), bread_crumb=VALUES(bread_crumb), category=VALUES(category), sub_category=VALUES(sub_category), fabric=VALUES(fabric), pattern_or_detailing=VALUES(pattern_or_detailing), delivery=VALUES(delivery), return_policy=VALUES(return_policy),lastUpdated=VALUES(lastUpdated)");
		String insertTableSQL = sbd.toString();
		int i = 1;
		try {
			dbConnection = DbConnection.getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			preparedStatement.setString(i++,data.getRelativeUrl());
			preparedStatement.setString(i++, data.getProductName());
			preparedStatement.setString(i++, data.getProductUrl());
			preparedStatement.setString(i++, data.getSku());
			preparedStatement.setString(i++, data.getMrp());
			preparedStatement.setString(i++, data.getSp());
			preparedStatement.setString(i++, data.getDescription());
			preparedStatement.setString(i++, data.getDetail());
			preparedStatement.setString(i++, data.getSizes());
			preparedStatement.setString(i++, data.getMaterial());
			preparedStatement.setString(i++, data.getCareInstruction());
			preparedStatement.setString(i++, data.getImgUrls());
			preparedStatement.setString(i++, data.getRetailer());
			preparedStatement.setString(i++, data.getBrand());
			preparedStatement.setString(i++, data.getColorText());
			preparedStatement.setString(i++, data.getColorCode());
			preparedStatement.setString(i++, data.getBreadCrumb());
			preparedStatement.setString(i++, data.getCategory());
			preparedStatement.setString(i++, data.getSubCategory());
			preparedStatement.setString(i++, data.getFabric());
			preparedStatement.setString(i++, data.getPatternOrDetailing());
			preparedStatement.setString(i++, data.getDelivery());
			preparedStatement.setString(i++, data.getReturnPolicy());
			preparedStatement.setString(i++, data.getProductName());
			preparedStatement.setLong(i++,(new Date()).getTime());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			// System.out.println("Record is inserted into DBUSER table!");
			// System.out.println(data.toString());
			System.out.println();
			System.out.println("Inserted");
				} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
}
