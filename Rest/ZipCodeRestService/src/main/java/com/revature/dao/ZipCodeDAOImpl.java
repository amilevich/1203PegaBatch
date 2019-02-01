package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.ZipCodes;

public class ZipCodeDAOImpl {
	public static List<ZipCodes> zipList = new ArrayList<>();
	//simulate DB
	static {
		zipList.add(new ZipCodes(33579, "Riverview","Florida"));
		zipList.add(new ZipCodes(33559, "Lutz","Florida"));
		zipList.add(new ZipCodes(35670, "Somerville","Alabama"));
	}
	//get all zipcodes
	public static List<ZipCodes> getAllZipCodes(){
		return zipList;
	}
	//get info by zipcode
	public static ZipCodes getInfoByZip(int zipcode) {
		for (ZipCodes z:zipList) {
			if(z.getZipCode()==(zipcode)) {
				return z;
			}
		}
		return null;
	}
	
	//add Zipcode
	public static void addZipCode(ZipCodes z) {
		zipList.add(z);
	}
	//delete zipcode
	public static void removeZipCode(int zipcode) {
		for(int i=0; i<zipList.size(); i++) {
			if(zipList.get(i).getZipCode()==zipcode) {
				zipList.remove(i);
			}
		}
	}
	
}
