package com.pojos;

/*
 * Useful class
 * 	
 * 	Comparable vs Comparator
 * 		Comparable - natural, default sorting, interface that gives compareTo method
 * 		Comparator - unnatural, alternate sorting, interface that gives compare method
 * 
 */
public class Person implements Comparable<Person>{
	private int id;
	



	private String name;
	private int age;
	
	public Person(){}
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Person o) {
		if(this.id > o.id) {
			return 1;
		}else if ( this.id < o.id ) {
			return -1;
		}
			return 0;
	}
	
	
}
