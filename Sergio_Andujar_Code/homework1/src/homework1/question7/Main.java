package homework1.question7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Sort Employees
 * @author Sergio Andujar
 * @version 1.8
 */

class Employee {
	
	private String name;
	private String department;
	private int age; 
	
	Employee(){}

	public Employee(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	@Override
	public String toString() {
		return this.getName() + " " + this.getDepartment() + " " + this.getAge();
	}

}

class SortByName implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		
		return o1.getName().compareTo(o2.getName());
	}
	
}

class SortByDepartment implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		
		return o1.getDepartment().compareTo(o2.getDepartment());
	}
	
}

class SortByAge implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		// why does this work?
		// return o1.getAge() - o2.getAge();
		// is it better than what I wrote?
		return o1.getAge() < o2.getAge() ? -1 : o1.getAge() == o2.getAge() ? 1 : 0;
	}
	
}

class Main{
	
	public static void main(String[] args) {
		
		ArrayList<Employee> Employees = new ArrayList<Employee>();
		Employee susan = new Employee("Susan", "Art", 31);
		Employee bob = new Employee("Bob", "Math", 89);
		Employee jamie = new Employee("Jamie", "CS", 25);
		Employees.add(susan);
		Employees.add(bob);
		Employees.add(jamie);
		
		// call collections sort method to sort by name
		Collections.sort(Employees, new SortByName());
		System.out.println("Employees sorted by name");
		for(Employee employee : Employees) {
			System.out.println(employee);
		}
		
		// call collections sort method to sort by department
		Collections.sort(Employees, new SortByDepartment());
		System.out.println("Employees sorted by department");
		for(Employee employee : Employees) {
			System.out.println(employee);
			
		}
		
		// call collections sort method to sort by age
		Collections.sort(Employees, new SortByAge());
		System.out.println("Employees sorted by age");
		for(Employee employee : Employees) {
			System.out.println(employee);
			
		}
	}
}

