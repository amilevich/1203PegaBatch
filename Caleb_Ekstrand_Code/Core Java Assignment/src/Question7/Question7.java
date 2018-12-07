package Question7;

public class Question7 {
	public static void main(String[] args) {
		AgeComparator ac = new AgeComparator();
		DepartmentComparator dc = new DepartmentComparator();
		NameComparator nc = new NameComparator();
		Employee e1 = new Employee("Bilboh", "HR", 43);
		Employee e2 = new Employee("Chewy", "Space Force", 12);
		int ageCompare = ac.compare(e1,e2);
		System.out.print("Sorted by age: ");
		if (ageCompare <= 0) {
			System.out.println(e1.toString() + ", " + e2.toString());
		} else {
			System.out.println(e2.toString() + ", " + e1.toString());
		}
		int departmentCompare = dc.compare(e1,e2);
		System.out.print("Sorted by department: ");
		if (departmentCompare <= 0) {
			System.out.println(e1.toString() + ", " + e2.toString());
		} else {
			System.out.println(e2.toString() + ", " + e1.toString());
		}
		int nameCompare = nc.compare(e1, e2);
		System.out.print("Sorted by name: ");
		if (nameCompare <= 0) {
			System.out.println(e1.toString() + ", " + e2.toString());
		} else {
			System.out.println(e2.toString() + ", " + e1.toString());
		}
		
	}
}
