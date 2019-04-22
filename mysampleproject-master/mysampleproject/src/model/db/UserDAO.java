package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.User;

public class UserDAO {
private static UserDAO instance;
	
	private UserDAO(){}
	
	public synchronized static UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public Set<User> getAllUsers(){
		Set<User> users = new HashSet<User>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("select username, password, email,first_name,last_name from user;");
			while(resultSet.next()){
				users.add(new User(	resultSet.getString("username"),
									resultSet.getString("password"),
									resultSet.getString("email"),
									resultSet.getString("first_name"),
									resultSet.getString("last_name")
									));
			}
		} catch (SQLException e) {
			e.getMessage();
//			System.out.println("Oops, cannot make statement.");
//			return users;
		}
		System.out.println("Users loaded successfully");
		return users;
	}
	
	public void saveUser(User user){
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO users (username, first_name,last_name, password, email) VALUES (?, ?, ?, ?, ?);");
			st.setString(1, user.getUsername());
			st.setString(2, user.getFirstName());
			st.setString(3, user.getPassword());
			st.setString(4, user.getEmail());
			st.setString(5, user.getLastName());
			st.executeUpdate();
			System.out.println("User added successfully");
		} catch (SQLException e) {
			System.out.println("Oops .. did not save the user");
			e.printStackTrace();
		}
		
	}
}
