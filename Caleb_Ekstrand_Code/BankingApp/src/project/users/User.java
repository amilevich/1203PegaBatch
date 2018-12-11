package project.users;

import java.util.ArrayList;

import project.account.Account;

public abstract class User {
	protected String username;
	protected String password;
	protected String name;
	private static ArrayList<String> usernames = new ArrayList<String>();
	private static ArrayList<User> users = new ArrayList<User>();

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
