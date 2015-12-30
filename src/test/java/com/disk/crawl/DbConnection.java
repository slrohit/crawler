package com.disk.crawl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class DbConnection {

	public static java.sql.Connection getDBConnection() {

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
