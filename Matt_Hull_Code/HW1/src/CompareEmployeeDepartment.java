import java.util.Comparator;

public class CompareEmployeeDepartment implements Comparator<Employee> {

	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return o1.getDepartment().compareToIgnoreCase(o2.getDepartment());
	}

}
