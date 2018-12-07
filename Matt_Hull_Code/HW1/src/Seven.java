import java.util.ArrayList;
import java.util.Collections;

public class Seven {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee one = new Employee("Bob","Hardware",35);
		Employee two = new Employee("Sauron","Jewelry",8000);
		Employee three = new Employee("Carmen","Hiking Supplies",33);
		Employee four = new Employee("Ash","Housewares",57);
		ArrayList<Employee> workers = new ArrayList<>();
		workers.add(one);
		workers.add(two);
		workers.add(three);	//I added some more people because it's irritating checking the sorts with only two
		workers.add(four);

		System.out.println(workers);
		Collections.sort(workers, new CompareEmployeeAge());  //Using Collections method to sort the workers using a subclass of Comparator
		System.out.println(workers);
		Collections.sort(workers, new CompareEmployeeDepartment());
		System.out.println(workers);
		Collections.sort(workers, new CompareEmployeeName());
		System.out.println(workers);
	}

}
