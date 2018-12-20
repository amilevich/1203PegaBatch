package project.users;

import java.sql.SQLException;
import java.util.ArrayList;

import project.account.Account;
import project.daoimpl.UserDAOImpl;

public abstract class User {
	/**
	 * username: username
	 * password: pass
	 * name: name
	 * usernames: select username from user_db
	 * users select * from user_db
	 */
	protected String username;
	protected String password;
	protected String name;
	private static ArrayList<String> usernames = new ArrayList<String>();
	private static ArrayList<User> users = new ArrayList<User>();
	private static UserDAOImpl udi = new UserDAOImpl();
	
	public static void loadUsers() {
		try {
			usernames = udi.getUsernames();
			users = udi.getUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for(String i : usernames) {
//			System.out.println(i);
//		}
	}
	public static boolean usernameExists(String username) {
		if (usernames.contains(username)) {
			return true;
		} else {
			return false;
		}

	}

	public static void addUser(User user) {
		usernames.add(user.username);
		users.add(user);
	}
	public static User getUser(String username) {
		for(User u: users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
	public String getPassword() {
		return this.password;
	}
	public String getName() {
		return this.name;
	}
	public String getUsername() {
		return this.username;
		
	}

}
