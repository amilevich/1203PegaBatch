package com.example.serializeable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class IoWithCollections {

	private static final String cerealFile = "cereal.txt";

	public static ArrayList<Cereal> cerealList = new ArrayList<>();

	public static void writeCerealFile() {

		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(cerealFile));
			objectOutputStream.writeObject(cerealList);
			objectOutputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readCerealFile() {

		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(cerealFile));
			cerealList = (ArrayList<Cereal>) objectInputStream.readObject();
			objectInputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
