package com.ex.fileio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteStream {
	public static void main(String[] args) {
		
		String pathToFile = "src/main/resources/static/sampleByteFile.txt";
		
//		writeByteStream(pathToFile);
		readByteStream(pathToFile);
		
		readThisClassFile();
	}
	
	static void readByteStream(String filename){
		try(InputStream is = new FileInputStream(filename)){
			
			int i;
			while((i = is.read()) != -1){
				System.out.print(i + " ");;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void writeByteStream(String filename){
		System.out.println("writing to file");
		/*
		 *  try with resources - Java 1.7
		 *  	will automatically close any open resource after
		 *  	the try block finishes or leaves in the result
		 *  	of an exception being thrown
		 *  
		 *  Below is an example of what we did before try w/ resources
		 */
		OutputStream os = null;
		try{
			os = new FileOutputStream(filename);
			os.write(65);
			os.write(66);
			os.write(67);
			os.write(68);
			os.write(69);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static void readThisClassFile(){
		String filename = "C:/Users/Steve_Revature/my_git_repos/Batches/1712_dec11th_java_steve/Java/CoreJavaExamples/target/classes/com/ex/autoboxing/MainClass.class";
		try(InputStream is = new FileInputStream(filename)){
			
			byte[] first4Bytes = new byte[4];
			is.read(first4Bytes);
			
			for(byte b:first4Bytes){				
				System.out.print(Integer.toHexString(b).substring(6));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
}
