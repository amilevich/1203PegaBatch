package com.revature.trms.validators;

import com.revature.trms.models.Address;

public class AddressValidator {

	public static boolean validate_Address(Address address) {

		String address_text = address.getAddress_text();
		String street_number = address.getStreet_number();
		String route = address.getRoute();
		String city = address.getCity();
		String state = address.getState();
		String zipcode = address.getZipcode();
		String country = address.getCountry();

		
		if(address_text == null) {
			System.out.println("Invalid address text");
			return false;
		}
		
		if(street_number == null) {
			System.out.println("Invalid street number");
			return false;
		}
		
		if(route == null) {
			System.out.println("Invalid route");
			return false;
		}
		
		if(city == null) {
			System.out.println("invalid city");
			return false;
		}
		
		if(state == null) {
			System.out.println("invalid state");
			return false;
		}
		
		if(zipcode == null) {
			System.out.println("invalid zipcode");
			return false;
		}
		
		if(country == null) {
			System.out.println("invalid country");
			return false;
		}
		
		return true;
	}
}
