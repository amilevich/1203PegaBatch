package com.assignment.persons;

import java.util.HashMap;


public class Person {
		protected static HashMap<String, Person> personMap = new HashMap<>();
		String username, password;
		int userType; //C-> Customer, E-> Employee, B->Bank Admin
		
		static Person e1 = new Person("e1", "e1", 2);//these will serve as tests, in reality we'll want to pull these from database/text file
		static Person e2 = new Person("e2", "e2", 2);
		static Person b1 = new Person("b1", "b1", 3);//for testing purposes
		static Person b2 = new Person("b2", "b2", 3);
		static boolean loaded = false;
		
		public Person() {}
		
		public Person (String username, String password, int userType) {//initial constructor
			this.username=username;
			this.password=password;
			this.userType=userType;
		}
		public static void loadEmployees() {//loads employee users
			personMap.put("e1" , e1);
			personMap.put("e2" , e2);
			personMap.put("b1" , b1);
			personMap.put("b2" , b2);
			loaded = true;
		}
		
		public static boolean getloaded() {//get loaded!LOL
			return loaded;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getUserType() {
			return userType;
		}
		public void setUserType(char userType) {
			this.userType = userType;
		}
		public static boolean checkHash (String input) {//returns true if the username already exists in the hashmap
				if (personMap.get(input).getUsername().equals(input))
					return true;
				else
					return false;
		}
		public static boolean checkUsername (String input) {
			if (Person.pullFromHash(input).getUsername().equals(input))
				return true;
			else
				return false;			
		}
		public static boolean checkPassword (String input) {
			if (Person.pullFromHash(input).getPassword().equals(input))
				return true;
			else
				return false;
		}
		public static void addtoHash(Person toBeAdded, String username) {
			personMap.put(username , toBeAdded);
		}
		public static Person pullFromHash(String username) {
			return personMap.get(username);
		}
		public static int hashsize() {
			return personMap.size();
		}
}
