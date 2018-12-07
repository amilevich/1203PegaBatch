package MostQuestions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Question20 {

	public static void main(String[] args) {
		File file = new File("Data.txt"); // get file
		String name = "Name: "; // will hold each name statement
		String age = "Age: "; // will hold each age statement
		String state = "State: "; // will hold each state statement
		String string = ""; // will hold contents of file
		// put file contents into string
		try (InputStream is = new FileInputStream(file)) {
			byte[] buffer = new byte[4];
			int bytesRead;
			while ((bytesRead = is.read(buffer)) != -1) {
				string += new String(buffer).substring(0, bytesRead);
			}
		} catch (IOException e) {

		}
		// System.out.println(string);
		// System.out.println(Arrays.toString(string.split(":")));

		String[] strings = string.split(":"); // get rid of ':' characters
		ArrayList<String> strings2 = new ArrayList<>();
		String[] tempStrings = new String[2];
		// put all elements of strings into an arraylist while also splitting elements
		// that contain '\n' characters
		for (String s : strings) {
			if (s.contains("\n")) {
				tempStrings = s.split("\n");
				strings2.add(tempStrings[0].replace("\n", ""));
				strings2.add(tempStrings[1].replace("\n", ""));
			} else {
				strings2.add(s.replace("\n", "")); // was unable to completely remove all newline characters but i left
													// in my attempts
			}
		}
		// System.out.println(strings2);
		int part = 1; // part keeps track of whether we are looking at a first name, last name, age,
						// or state
		for (int i = 0; i < strings2.size(); i++) {
			if ((part == 1) || (part == 2)) {
				name += strings2.get(i) + " ";
				part++;
			} else if (part == 3) {
				age += strings2.get(i) + " years old";
				part++;

			} else {
				state += strings2.get(i);
				part = 1; // reset after state
				// print all lines
				System.out.println(name);
				System.out.println(age);
				System.out.println(state);
				// reset lines
				name = "Name: ";
				age = "Age: ";
				state = "State: ";
			}
		}

	}

}
