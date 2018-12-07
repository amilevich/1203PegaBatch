package com.homework.questions.question7;
/** Employee class
 * @author Blake Biskner
 * @version 1.7
 */

public class EmployeeQ7 {
	// Class State
	private String name;
	private String dept;
	private int age;
	// Class Behavior
	public EmployeeQ7(String name, String dept, int age) {
		this.name=name;
		this.dept=dept;
		this.age=age;
	}
	
	public String getName() {
		return name;
	}
	public String getDept() {
		return dept;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setDept(String dept) {
		this.dept=dept;
	}
	public void setAge(int age) {
		this.age=age;
	}
	
	@Override
	public String toString() {
		return("Name="+this.getName()+" Department="+this.getDept()+" Age="+this.getAge());
	}
}