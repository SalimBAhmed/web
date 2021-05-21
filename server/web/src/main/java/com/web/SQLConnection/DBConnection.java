package com.web.SQLConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnection {
	private static String dbhost = "jdbc:mysql://localhost:3306/web";
	private static String username = "root";
	private static String password = "";
	private static Connection conn;
	
	
	@SuppressWarnings("finally")
	public static Connection createNewDBConnection() {
		try  {	
			conn = DriverManager.getConnection(
					dbhost, username, password);
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}

}