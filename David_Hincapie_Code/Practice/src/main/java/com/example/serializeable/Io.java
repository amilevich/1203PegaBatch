package com.example.serializeable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.jni.OS;

public class Io {

	private static final String OUTFILE = "output.txt";
	private static final String INFILE = "input.txt";

	public void writeOutputStreamContents(String contents) {
		OutputStream os = null;
		File file = new File("output.txt");

		try {
			os = new FileOutputStream(file, false);
			os.write(contents.getBytes());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (os != null) {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}// end of writeOutputStreamContents

	public String readInputStreamContents() {
		InputStream is = null;
		File file = new File(INFILE);
		StringBuilder stringBuilder = new StringBuilder();
		int b = 0;
		try {
			is = new FileInputStream(file);

			while ((b = is.read()) != -1) {
				char c = (char) b;
				stringBuilder.append(c);
			}

			if (is != null) {
				is.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stringBuilder.toString();

	}// end of readInputStreamContents

}
