package Question18;

public class StringCheckerMinion extends StringCheckerOverlord {

	@Override
	public boolean upperCheck(String string) {
		int upperCount = 0;
		for (int i = 0; i < string.length(); i++) {
			if (Character.isUpperCase(string.charAt(i))) {
				upperCount++;
			}
		}
		if (upperCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String makeUpper(String string) {
		return string.toUpperCase();
	}

	@Override
	public int getInt(String string) {
		return Integer.parseInt(string) + 10;
	}

}
