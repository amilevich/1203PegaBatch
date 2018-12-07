import java.util.Comparator;

public class Employee{
	private String name;
	private String department;
	private int age;
	
	public Employee() {
		
	}
	public Employee(String name, String department,int age) {
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
	public void setDepartment(String deparment) {
		this.department = deparment;
	}
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}
	public void setAge(int age) {
		this.age = age;
	}

	

}
