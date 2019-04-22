package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static DBManager instance;
	private Connection connection;
	
	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "users";
	private static final String DB_USERNAME = "lays";
	private static final String DB_PASSWORD = "vasetow0w";
	private static final String endUrl = "?useSSL=false";
	private static final String URL = "jdbc:mysql://"+DB_IP+":"+DB_PORT+"/"+DB_NAME+endUrl;
	 
	private DBManager(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("loaded success");
		} catch (ClassNotFoundException e) {
			System.out.println("cant connect");
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "lays", "vasetow0w");
			System.out.println("success connection");
		} catch (SQLException e) {
			System.out.println(" cant connect again");
		}
	}
	
	synchronized static DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	Connection getConnection() {
		return connection;
	}
}
