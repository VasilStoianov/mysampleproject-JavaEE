package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import model.db.UserDAO;



public class UsersManager {

	private  ConcurrentHashMap<String, User> registerredUsers;//username -> user
	private static UsersManager instance;
	private UsersManager(){
		registerredUsers = new ConcurrentHashMap<>();
		for(User u : UserDAO.getInstance().getAllUsers()){
			registerredUsers.put(u.getUsername(), u);
		}
	}
	
	public synchronized static UsersManager getInstance(){
		if(instance == null){
			instance = new UsersManager();
		}
		return instance;
	}
	
	
	
	public boolean validLogin(String username, String password){
		if(!registerredUsers.containsKey(username)){
			return false;
		}
		return registerredUsers.get(username).getPassword().equals(password);
	}
	
	public  void regUser(String username, String password, String email, String firstname, String lastname){
		User user = new User(username, password, email, firstname, lastname);
		registerredUsers.put(username, user);
		UserDAO.getInstance().saveUser(user);
	}

	
		
}
