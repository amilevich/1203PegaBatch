package seven.hwk;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionSeven {
	/*
	 * The problem is Question 7:
	 * 
	 * Sort two employees based on their name, department, and age using the
	 * Comparator interface.
	 * 
	 */

	/*
	 * (1) So, in the main method I created an ArrayList called List that takes in
	 * new objects of Employee. Then I place the list and new object(nameComparator
	 * | departmentComparator | ageComparator) into Collections.sort and sort them.
	 * I then use the compare method that I override in each class to compare
	 * between the values that are being compared from the object. Once the values
	 * have been sorted I run a print method that I created to output the sorted list by its title header.
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) { // Look at (1)

		ArrayList<Employee> list = new ArrayList<Employee>();
		list.add(new Employee("William", "Playwright", 52));
		list.add(new Employee("Einstein", "Philosophy", 76));
		list.add(new Employee("Stephen", "Physics", 76));
		list.add(new Employee("Telsa", "Engineering", 86));

		Collections.sort(list, new nameComparator());
		print(list, "Sorted by name:");
		Collections.sort(list, new departmentComparator());
		print(list, "Sorted by Department:");
		Collections.sort(list, new ageComparator());
		print(list, "Sorted by Age:");
	}

	public static void print(ArrayList<Employee> list, String s) {
		System.out.println(s);
		for (Employee x : list)
			System.out.println(x);

		System.out.println();
	}

}
