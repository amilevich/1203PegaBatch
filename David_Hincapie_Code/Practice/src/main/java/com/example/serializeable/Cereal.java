package com.example.serializeable;

import java.io.Serializable;

public class Cereal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 229463664801961043L;
	private String name;
	private int rating;
	public Cereal() {
		super();
	}
	public Cereal(String name, int rating) {
		super();
		this.name = name;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Cereal [name=" + name + ", rating=" + rating + "]";
	}
	
	
}
