
public class Eighteen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SuperClass obj = new SubClass();
		//Couldn't think of some clever way of integrating the three methods chosen into a class
		//so I just make an abstract SuperClass class and a concrete SubClass class
		System.out.println(obj.checkForCap("Hello")); //checks for caps
		System.out.println(obj.checkForCap("hello"));
		System.out.println(obj.makeAllCap("Helo")); //makes it all caps
		System.out.println(obj.addTen("5")); //adds 10
	}

}
