package test;

import java.util.HashMap;

public class test {
	static HashMap<String, Double> testing = new HashMap<String, Double>();
	
	public static void main(String[] args) {
		testing.put("1",3.0);
		testing.put("1", 2.0);
System.out.println(" " + testing.get("1"));
	}

}
