package com.example.serializeable;

public class SerializeDemo {

	public static void main(String[] args) {

		Io io = new Io();
		// io.writeOutputStreamContents("Roll Tide");
		// System.out.println(io.readInputStreamContents());

		// Cereal a = new Cereal("Golden Grahams", 11);
		// Cereal b = new Cereal("RB", 3);
		// Cereal c = new Cereal("LC", 8);
		// Cereal d = new Cereal("CTC", 110);
		//
		// IoWithCollections.cerealList.add(a);
		// IoWithCollections.cerealList.add(b);
		// IoWithCollections.cerealList.add(c);
		// IoWithCollections.cerealList.add(d);
		//
		// IoWithCollections.writeCerealFile();
		IoWithCollections.readCerealFile();
		for (Cereal cereal : IoWithCollections.cerealList) {
			System.out.println(cereal);
		}

	}

}
