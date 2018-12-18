package com.ex.sorting;

import java.time.LocalDate;

/*
 * 		Comparable - is the default/natural/primary ordering for sorting 
 *			gives the compareTo()
 *			must return a negative, 0, or positive number to determine which is greater 		
 * 
 */

//just another pojo
public class Student implements Comparable<Student>{
	
	private int studentId;
	private String name;
	private double gpa;
	private LocalDate dob;
	
	public Student(){}

	public Student(int studentId, String name, double gpa, LocalDate dob) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.gpa = gpa;
		this.dob = dob;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", gpa=" + gpa + ", dob=" + dob + "]";
	}

	/*
	 *	return:
	 *		-1
	 *		0
	 *		1  
	 */
	@Override
	public int compareTo(Student o) {
		return this.studentId - o.studentId;
	}

}
