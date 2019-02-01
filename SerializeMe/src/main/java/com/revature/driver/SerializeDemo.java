package com.revature.driver;

import com.revature.bean.Cereal;
import com.revature.io.IO;
import com.revature.io.IOWithCollections;

public class SerializeDemo {

	public static void main(String[] args) {
		/*if output.txt does not exist, it will create it!
		 * If it doesnt exist, run it, and then refresh the project
		 * explorer to see it
		 * 
		 */
		IO io= new IO();
		//io.writeOutputStreamContents("uhcdsh. ");
		//System.out.println(io.readInputStreamContents());
		/*Cereal a= new Cereal("GG",11);
		Cereal b= new Cereal("RB",3);
		Cereal c= new Cereal("LC",8);
		Cereal d= new Cereal("CTC",110);
		IOWithCollections.cerealList.add(a);
		IOWithCollections.cerealList.add(b);
		IOWithCollections.cerealList.add(c);
		IOWithCollections.cerealList.add(d);*/
		
		//IOWithCollections.writeCerealFile();
		IOWithCollections.readCerealFile();
		System.out.println(IOWithCollections.cerealList.get(0).getName());
		System.out.println(IOWithCollections.cerealList.get(3).getName());
	}

}
