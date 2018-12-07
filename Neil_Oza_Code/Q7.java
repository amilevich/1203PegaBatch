package hw.weekone;

import java.util.Comparator;

public class Q7 implements Comparable<Q7>{
		private String name;
		private String department;
		private Integer age;
		
		public void setDepartment(String department){
			this.department = department;
		}
		public String getDepartment(){
			return this.department;
		}


		public void setName(String name){
			this.name = name;
		}
		public String getName(){
			return name;
		}

		public void setAge(int age){
			this.age = age;
		}
		public int getAge(){
			return age;
		}

		@Override
		public String toString() {
			return "Employee " + name + " is in " + department + " department and is " + age + " years old.";
		}
		
		@Override/*
		public int compareTo(Q7 e){
			return this.age-e.age; 
		}
		*/
		/*
		public int compareTo(Q7 e) {
			return this.department.compareTo(Q7 e);
		}
		*/
		public int compareTo(Q7 e) {
			return this.name.compareTo(e.name);
		}

	}



