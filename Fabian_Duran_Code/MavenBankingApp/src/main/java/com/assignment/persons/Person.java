package com.assignment.persons;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment.daoimpl.PersonsDAOImpl;


public class Person {
		int personID;
		String username, password;
		int userType; //1-> Customer, 2-> Employee, 3->Bank Admin, 4->SuperUser
		static List <Person> personList = new ArrayList<Person>();
		static boolean loaded = false;
		public Person() {}

		//THE TRUE BEAN!!!!!!!!!!!!!->The other will be phased out
		public Person (int personID, String username, String password, int userType) {//initial constructor
			super();
			this.personID = personID;
			this.username = username;
			this.password = password;
			this.userType = userType;
		}
		public static boolean getLoaded() {//get loaded!LOL
			return loaded;
		}
		public int getPersonID() {
			return personID;
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
		public void setUserType(int userType) {
			this.userType = userType;
		}
		public static List<Person> getList(){
			return personList;
		}
		public static void loadPersonList() {
			PersonsDAOImpl pimpl = new PersonsDAOImpl();//PersonsImpl->p impl--> pimpl
			try {
				personList = pimpl.getPersonList();
			} catch (SQLException e) {
				System.out.println("loadPersonList! Database Connection Error!");
				e.printStackTrace();
			}
			if (loaded==false)
				loaded = true;
		}
		public static boolean checkUsername (String input) {
			String tempS ="";
			for(int i = 0;i<personList.size(); i++) {
				tempS = personList.get(i).getUsername();
				if (input.equals(tempS))
					return true;
			}		
			return false;
		}
		public static boolean checkPassword (String input) {
			String tempS ="";
			for(int i = 0;i<personList.size(); i++) {
				tempS = personList.get(i).getPassword();
				if (input.equals(tempS))
					return true;
			}		
			return false;
		}
		//this will confirm that username matches security level that user is trying to enter
		public static boolean checkUserMatch (String username, int input) {
			int tempI =0;
			String tempS ="";
			for(int i = 0;i<personList.size(); i++) {
				tempI = personList.get(i).getUserType();
				tempS = personList.get(i).getUsername();
				if (input == tempI&&username.equals(tempS))
					return true;
			}		
			return false;
		}
		//to replace to addtoHash
		//creates new person in DB, updates personList 
		public static void addToUserDB(String username, String password, int userType ) {
			PersonsDAOImpl pimpl = new PersonsDAOImpl();
			try {
				pimpl.createPerson(username, password, userType);
				Person.loadPersonList();
			} catch (SQLException e) {
				System.out.println("addToUserDB! Could not connect to Database!");
				e.printStackTrace();
			}
		}
		//to get person from specific list, 
		//will replace pullFromHash
		public static Person pullFromList(String username) {
			String tempS = "";
			Person p = new Person();
			for (int i = 0; i < personList.size();i++){
				tempS = personList.get(i).getUsername();
				if (username.equals(tempS))
					p = personList.get(i);
			}
			return p;
		}
		public static void deleteUserDB(String username) {
			PersonsDAOImpl pimpl = new PersonsDAOImpl();
			int id = Person.pullFromList(username).getPersonID();
			try {
				pimpl.deletePerson(id);
				Person.loadPersonList();
				Customer.loadCustomerList();
			} catch (SQLException e) {
				System.out.println("deleteUserDB! Could not connect to Database!");
				e.printStackTrace();
			}
		}
		public static void updateUserDB(String username, String newName) {
			PersonsDAOImpl pimpl = new PersonsDAOImpl();
			int id = Person.pullFromList(username).getPersonID();
			try {
				pimpl.updatePerson(id, newName);
				Person.loadPersonList();
			} catch (SQLException e) {
				System.out.println("updateUserDB! Could not connect to Database!");
				e.printStackTrace();
			}
		}
		/*
		 * Want to view the stuff that was removed? Use this link!
		 * https://drive.google.com/open?id=1V-7-wZmgnlB4hGBkGKBBAs9LMR1Lo9BH
		 */
}
