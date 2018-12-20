package com.revature.jdbcbank;

import java.util.List;

import com.revature.daoimpls.ApplicationDaoImpl;
import com.revature.daoimpls.UserDaoImpl;
import com.revature.exceptions.UsernameExists;
import com.revature.pojos.Application;
import com.revature.pojos.User;

public class UserAuthorizer {

	// Singleton instance private data member
	private static UserAuthorizer userAuth = new UserAuthorizer();

	private static UserDaoImpl userDaoImpl = new UserDaoImpl();
	
	private static ApplicationDaoImpl appDaoImpl = new ApplicationDaoImpl();

	/**
	 * Private default constructor (necessary for singleton
	 */
	private UserAuthorizer() {
		super();
	}

	/**
	 * getUserAuthSingleton()
	 * 
	 * @return singleton instance of this class
	 */
	public static UserAuthorizer getUserAuthSingleton() {
		return userAuth;
	}

	/**
	 * login(String,String)
	 * 
	 * @param username - username to login
	 * @param password - password to check
	 * @return - true if a user with the given username exists, false otherwise
	 */
	public User login(String username, String password) {
		User user = userDaoImpl.getUserByUsername(username);
		// Short circuit null and password check combined
		if (user == null || !user.getPassword().equals(password)) {
			return null;
		}

		// If the password matches the one provided, return the user
		return user;
	}

	/**
	 * usernameExists(String) check to see if a given username already exists
	 */
	public boolean usernameExists(String username) {
		User user = userDaoImpl.getUserByUsername(username);
		// Return false if no user exists with given username, true otherwise
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * returns the user_id of the given user
	 * @param username
	 * @return
	 */
	public Integer getUserId(String username) {
		User user = userDaoImpl.getUserByUsername(username);

		if (user == null) {
			return null;
		}
		return user.getId();
	}

	/**
	 * registers a user into the database if a user with the given username doesn't
	 * already exist
	 * 
	 * @param user
	 * @return
	 */
	public boolean register(User user) {
		
		if (usernameExists(user.getUsername())) {
			throw new UsernameExists();
		}
		if (userDaoImpl.createUser(user)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Submits the application (logs it in the application table in the db through the Application dao
	 * @param app
	 * @return
	 */
	public boolean submitApplication(Application app) {
		return appDaoImpl.createApplication(app);	
	}

	/**
	 * Gets a list of 'pending' account applications
	 * @return a list of account applications from the Application table
	 */
	public List<Application> getPendingApplications() {
		return appDaoImpl.getApplications();
	}
	
	/**
	 * Gets an account application with the given id, if one exists
	 * @param appid
	 * @return an application if one exists with the given id, null otherwise
	 */
	public Application getApplicationById(int appid) {
		return appDaoImpl.getApplicationById(appid);
	}
	
	/**
	 * Removes an account application from the table. 
	 * If an application is going to be approved or denied, 
	 * this method can be used to remove the 'reviewed' account application
	 * @param appid
	 * @return true if application successfully removed, false if unable to
	 */
	public boolean removeApplication(int appid) {
		
		if(getApplicationById(appid) == null) {
			return false;
		}
		return appDaoImpl.removeApplication(appid);
	}
	
	/**
	 * Removes a user from the user table and all referencing tables
	 * @param username
	 * @return
	 */
	public boolean removeUser(String username) {
		if(!usernameExists(username)) {
			return false;
		}
		
		int id = getUserId(username);
		return userDaoImpl.deleteUser(id);
		
	}
	
	/**
	 * 
	 * @param username
	 * @return user with given username, if one exists. null otherwise.
	 */
	public User getUserByUsername(String username) {
		
		return userDaoImpl.getUserByUsername(username);
	}
	
	
	/**
	 * updates a user record with the provided username and password
	 * @param oldUsername
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean updateUser(String oldUsername, String newUsername, String newPassword) {
		if( getUserId(oldUsername) != null && getUserId(newUsername) == null ) {
			return userDaoImpl.updateUser(getUserId(oldUsername), newUsername, newPassword);
		}else {
			return false;
		}
		 	
	}
	

}
