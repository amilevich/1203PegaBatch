package Question7;
import java.util.Comparator;
public class AgeComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		Integer wrapper1 = o1.getAge();
		Integer wrapper2 = o2.getAge();
		return wrapper1.compareTo(wrapper2);
	}

	

}
