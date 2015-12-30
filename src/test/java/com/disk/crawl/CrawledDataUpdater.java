package com.disk.crawl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrawledDataUpdater {

	public static void main(String[] args) throws IOException {
		int size = args.length;

		if (size > 0) {
			ConfigNew conf;
			List<String> urls = new ArrayList<String>();
			for (int i = 0; i < args.length; i++) {
				ConfigNew.setConfType(args[i]);
				conf = Conf.valueOf(ConfigNew.getConfType()).getValues();
				String[] what = new String[1];
				what[0] = ScrapeDataDao.column_product_url;
				String where_url = conf.getUrlPrefix();
				ResultSet rs = ScrapeDataDao.getSelectQuery(what, where_url);
				try {
					while (rs.next()) {
						urls.add(rs.getString(ScrapeDataDao.column_product_url));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!urls.isEmpty()) {
					for (String url : urls) {
						Test.parseTestUrl(url);
					}
				}
			}
		}

	}
}
