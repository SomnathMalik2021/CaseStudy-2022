package com.training.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class ConnectionFactory {
	
	
	public static Connection getMySqlConnection() {
		Connection con = null;
		try{
			ResourceBundle bundle = ResourceBundle.getBundle("application") ;  
			String url = bundle.getString("url") ; 
			String password = bundle.getString("password") ;
			String username = bundle.getString("username") ; 
			con = DriverManager.getConnection(url, username, password) ; 
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con ; 
	}
}
