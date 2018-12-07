package com.example.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterString {

	// i/o - input/output
	// we can either use
	// character stream - human readable, FileReader and FileWriter
	// byte stream - machine readable, byte code
	// FileInputStream and FileOutputStream

	// one character at a time is costly, so we do large chunks
	// of data instead by using these:
	// BufferedReader - characters
	// BufferedWriter - characters
	// BufferedOutputStream - byte code
	// BufferedInputStream - byte code

	public static void main(String[] args) {

		String filename = "./CharExample.txt";
		
		//writeCharacterStream(filename);
		readCharacterStream(filename);

	}

	static void readCharacterStream(String filename) {
		try (FileReader reader = new FileReader(filename)) {
			int i;
			while ((i = reader.read()) != -1) {
				System.out.println((char) i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}

	static void writeCharacterStream(String filename) {
		try (FileWriter writer = new FileWriter(filename, true)) {
			// true appends to the existing file
			// false would create a new file each time
			for (int i = 65; i < 72; i++) {
				writer.write(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		} finally {
//			System.out.println("in finally");
		}
	}

}
