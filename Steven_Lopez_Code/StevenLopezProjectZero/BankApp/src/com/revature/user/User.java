package com.revature.user;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
//Super class for the users holds the majority of the properties for the subclasses
public abstract class User implements Serializable {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	static AtomicInteger nextId = new AtomicInteger();
	private int id;
	private String name;
	private String username;
	private char[] password = new char[30];
	private String email;
	private LocalDate dateCreated;

	public User() {
		this.id = nextId.incrementAndGet();
	}

	public User(String username, char[] password) {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password="
				+ Arrays.toString(password) + ", email=" + email + ", dateCreated=" + dateCreated + "]";
	}


	public boolean setUser(String filename) {

		return true;
	}

	public boolean getAllUser(String filename) {

		return true;
	}
}
