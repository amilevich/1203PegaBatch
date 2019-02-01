package com.revature.bean;

import java.io.Serializable;

public class Cereal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -250359463361332199L;
	private String name;
	private int deliciousnessRating;
	public Cereal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cereal(String name, int deliciousnessRating) {
		super();
		this.name = name;
		this.deliciousnessRating = deliciousnessRating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDeliciousnessRating() {
		return deliciousnessRating;
	}
	public void setDeliciousnessRating(int deliciousnessRating) {
		this.deliciousnessRating = deliciousnessRating;
	}
	@Override
	public String toString() {
		return "Cereal [name=" + name + ", deliciousnessRating=" + deliciousnessRating + "]";
	}
	
	
}
