package com.project.files;

import java.util.Scanner;

public class ApplicationInput extends ScreenInput {
	// Constructor
	public ApplicationInput() {
		String name="Application Information";
		String screenInput[]= {"Name","Date of Birth"};
		
		setName(name);
		setScreenInput(screenInput);
	}
	
	public void inputDisplay() {
		displayHeader();
	}
	
	public void getInput(Scanner userIn) {
		String name[];
		for(int i=0;i<getScreenInput().length;i++) {
			switch(i) {
			case 1:
				name=InputValidation.nameValidate(userIn);
				// Will Set to Customer Object Field
				for(String nm:name) {
					System.out.print(nm+" ");
				}
				break;
			case 2:
				System.out.println("Will Enter Date Next");
				break;
			}
		}
	}
}
