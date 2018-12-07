import java.util.Comparator;

public class CompareEmployeeAge implements Comparator<Employee> {

	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return o1.getAge()-o2.getAge();

	}

}
