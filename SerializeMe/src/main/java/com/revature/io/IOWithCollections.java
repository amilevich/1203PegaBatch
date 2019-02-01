package com.revature.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.revature.bean.Cereal;

public class IOWithCollections {

	private static final String cerealFile= "cereal.txt";
	public static ArrayList<Cereal> cerealList=
			new ArrayList<Cereal>();
	
	public static void writeCerealFile() {
		ObjectOutputStream objectOut;
		try {
			objectOut = new ObjectOutputStream(new FileOutputStream(cerealFile));
			objectOut.writeObject(cerealList);
			objectOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void readCerealFile() {
		ObjectInputStream objectIn;
		try {
			objectIn=
					new ObjectInputStream(new FileInputStream(cerealFile));
			cerealList= (ArrayList<Cereal>)objectIn.readObject();
			objectIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

