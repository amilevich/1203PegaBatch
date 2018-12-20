package com.bank.main;

import java.util.Random;

public class RegistrationForm {

	public void regNames() {
		System.out.println("Please enter your first name, middle initial, and last name: ");
		String firstName = Bank.input.next();
		UserInputValidation.isLetters(firstName);
		Bank.reg.setFirstName(firstName);
		String middleInitial = Bank.input.next();
		UserInputValidation.isALetter(middleInitial);
		Bank.reg.setMiddleInitial(middleInitial);
		String lastName = Bank.input.next();
		UserInputValidation.isLetters(lastName);
		Bank.reg.setLastName(lastName);
	}

	public void regAddress() {
		System.out.println("Please enter your address: ");
		Bank.input.nextLine();
		String address = Bank.input.nextLine();
		UserInputValidation.isLetterNumLine(address);
		Bank.reg.setAddress(address);

		System.out.println("Please enter your city, state, zip: ");
		String city = Bank.input.next();
		UserInputValidation.isLetters(city);
		Bank.reg.setCity(city);
		String state = Bank.input.next();
		UserInputValidation.isLetters(state);
		Bank.reg.setState(state);
		Bank.input = UserInputValidation.isInt(Bank.input);
		int zip = Bank.input.nextInt();
		Bank.reg.setZip(zip);
	}

	public void regPhoneNumber() {
		System.out.println("Please enter your phone number: ");
		Bank.input = UserInputValidation.isLong(Bank.input);
		Long phoneNum = Bank.input.nextLong();
		Bank.reg.setPhoneNum(phoneNum);
	}

	public void regPassword() {

		System.out.println("Please enter your passWord(Letters, numbers, special characters(%^!@): ");
		String passWord = Bank.input.next();
		UserInputValidation.isLetterNumSpecial(passWord); // make this more functional later
		Bank.reg.setPassWord(passWord);

	}

	public void regEmail() {
		System.out.println("Please enter your email address: ");
		String email = Bank.input.next();
		UserInputValidation.isLetterNumSpecial(email); // make this more functional later
		Bank.reg.setEmail(email);
	}

	public void regUserName() {

		String id = generateID(Bank.reg.getFirstName(), Bank.reg.getLastName());
		System.out.println("Your UserName: " + id);
		Bank.reg.setUserName(id);
	}

	public static String generateID(String first, String last) { // Look at (3)
		Random rm = new Random();
		String id = first.charAt(0) + last, temp;
		int number;
		do {
			temp = "";
			number = rm.nextInt(9000) + 1000;
			temp = id + number;

		} while (!Bank.Bankuser.userIdCheck(temp, temp));

		return temp;

	}

}
