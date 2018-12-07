package com.example.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteStream {

	public static void main(String[] args) {

		String filename = "./ByteFile.txt";

		// C:/Users/Support you can just do ./src/com.example current directory

		//writeByTestStream(filename);
		readByteStream(filename);
	}

	static void readByteStream(String filename) {
		try (InputStream is = new FileInputStream(filename)) {
			int i;
			while ((i = is.read()) != -1) {
				System.out.println(i + " ");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} finally {

		}
	}

	static void writeByTestStream(String filename) {
		try (OutputStream os = new FileOutputStream(filename)) {
			os.write(65);
			os.write(66);
			os.write(67);
			os.write(200);
			os.write(300);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} finally {

		}
	}
}
