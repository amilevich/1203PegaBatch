
public class SubClass extends SuperClass {

	@Override
	public boolean checkForCap(String str) {
		// TODO Auto-generated method stub
		for(int i = 0; i < str.length();i++) {
			if(Character.isUpperCase(str.charAt(i)))
				
				return true;
		}
		return false;
	}

	@Override
	public String makeAllCap(String str) {
		// TODO Auto-generated method stub
		return str.toUpperCase();
	}

	@Override
	public int addTen(String str) {
		return Integer.parseInt(str) + 10; 
		// TODO Auto-generated method stub
	}

}
