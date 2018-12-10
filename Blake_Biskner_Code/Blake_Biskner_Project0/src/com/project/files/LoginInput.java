package com.project.files;

public class LoginInput extends ScreenInput{
	
	// Constructor
	public LoginInput() {
		String name="Login Information Screen";
		String screenInput[]= {"Username","Password"};
		
		setName(name);
		setScreenInput(screenInput);
	}
	public void inputDisplay() {
		displayHeader();
	}

}
