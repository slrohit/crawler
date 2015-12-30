package com.disk.crawl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrapeDataDao {
	public final static String column_product_url = "product_url";

	public static ResultSet getSelectQuery(String[] what, String where_url) {
		java.sql.Connection dbConnection = null;
		java.sql.PreparedStatement preparedStatement = null;
		StringBuilder sbd = new StringBuilder();
		sbd.append("select ");
		if (what.length != 0) {
			sbd.append(implode(", ", what));
		} else {
			sbd.append("*");
		}
		sbd.append(" from scrape_data3");
		if (where_url != "") {
			sbd.append(" where " + column_product_url + " REGEXP ?");
		}
		String query = sbd.toString();
		int i = 1;
		try {
			dbConnection = DbConnection.getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			preparedStatement.setString(i++, where_url);
			ResultSet rs = preparedStatement.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String implode(String separator, String... data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length - 1; i++) {
			// data.length - 1 => to not add separator at the end
			if (!data[i].matches(" *")) {// empty string are ""; " "; " "; and
											// so on
				sb.append(data[i]);
				sb.append(separator);
			}
		}
		sb.append(data[data.length - 1].trim());
		return sb.toString();
	}
}
