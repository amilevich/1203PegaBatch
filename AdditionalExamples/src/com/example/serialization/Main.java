package com.example.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	// serialization
	// is a mechanism of converting the state of an object
	// into byte stream

	// deserialization
	// reverse process, byte stream is used to recreate the actual
	// Java object in memory.

	// to make it happen, we implement io.Serializable interface
	// marker interface - zero methods

	// The ObjectOutputStream class contains the writeObject()
	// method for serializing an object.

	// The ObjectInputStream class contains the readObject()
	// method for deserializing an object.

	// SerialVersionUID
	// The serialization runtime associates a version number
	// with each Serializable class called a SerialVersionUID,
	// which is used during Deserialization to verify that
	// sender and receiver of serialized object have
	// loaded class for that object which
	// are compatible with respect to serialization.

	// If different UID for sender/receiver, then
	// InvalidClassException.

	// to generate it, we can do two things:
	// a default SerialVersionID = 1L;
	// generated SerialVersionID -> unique

	public static void main(String[] args) {

		String filename = "./sampleObjectFile.txt";
		
		Person person = new Person("Hippieh", 592, "111-11-1111");
		
		//writeObject(filename, person);
		readObject(filename);

	}

	static void readObject(String filename) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {

			Object obj = ois.readObject(); // deserialization
			System.out.println(obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static void writeObject(String filename, Object obj) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {

			oos.writeObject(obj); // serialization - turning an object to bytes
									// and into a text file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
