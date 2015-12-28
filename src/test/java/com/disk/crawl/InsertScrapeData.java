package com.disk.crawl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import com.mysql.jdbc.Connection;

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

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1,data.getRelativeUrl());
			preparedStatement.setString(2, data.getProductName());
			preparedStatement.setString(3, data.getProductUrl());
			preparedStatement.setString(4, data.getSku());
			preparedStatement.setString(5, data.getMrp());
			preparedStatement.setString(6, data.getSp());
			preparedStatement.setString(7, data.getDescription());
			preparedStatement.setString(8, data.getDetail());
			preparedStatement.setString(9, data.getSizes());
			preparedStatement.setString(10, data.getMaterial());
			preparedStatement.setString(11, data.getCareInstruction());
			preparedStatement.setString(12, data.getImgUrls());
			preparedStatement.setString(13, data.getRetailer());
			preparedStatement.setString(14, data.getBrand());
			preparedStatement.setString(15, data.getColorText());
			preparedStatement.setString(16, data.getColorCode());
			preparedStatement.setString(17, data.getBreadCrumb());
			preparedStatement.setString(18, data.getCategory());
			preparedStatement.setString(19, data.getSubCategory());
			preparedStatement.setString(20, data.getFabric());
			preparedStatement.setString(21, data.getPatternOrDetailing());
			preparedStatement.setString(22, data.getDelivery());
			preparedStatement.setString(23, data.getReturnPolicy());
			preparedStatement.setString(24, data.getProductName());
			preparedStatement.setLong(25,(new Date()).getTime());

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

	private static java.sql.Connection getDBConnection() {

		java.sql.Connection dbConnection = null;

		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			prop.load(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			Class.forName(prop.getProperty("dbdriver"));

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(prop.getProperty("database"), prop.getProperty("dbuser"),
					prop.getProperty("dbpassword"));
			return (Connection) dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
}
