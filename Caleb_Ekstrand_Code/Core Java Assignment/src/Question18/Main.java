package Question18;

public class Main {

	public static void main(String[] args) {
		StringCheckerMinion scm = new StringCheckerMinion();
		System.out.println(scm.upperCheck("Apple"));
		System.out.println(scm.upperCheck("apPle"));
		System.out.println(scm.upperCheck("apple"));
		System.out.println(scm.makeUpper("apple"));
		System.out.println(scm.makeUpper("aPpLE"));
		System.out.println(scm.makeUpper("APPLE"));
		System.out.println(scm.getInt("10"));
		System.out.println(scm.getInt("92"));
	}

}
