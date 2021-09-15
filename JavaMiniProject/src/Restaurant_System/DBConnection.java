package Restaurant_System;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

class DBConnection {
	
	String url;
	String userName;
	String pass;
	
	public DBConnection(String url, String userName, String pass) {
		super();
		this.url = url;
		this.userName = userName;
		this.pass = pass;
	}

	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		con = DriverManager.getConnection(url,userName,pass);
		System.out.println("Successfull connected to database.....");
		return con;
	}
}
