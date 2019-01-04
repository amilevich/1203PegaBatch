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
			return false;
		}
		
		if(street_number == null) {
			return false;
		}
		
		if(route == null) {
			return false;
		}
		
		if(city == null) {
			return false;
		}
		
		if(state == null) {
			return false;
		}
		
		if(zipcode == null) {
			return false;
		}
		
		if(country == null) {
			return false;
		}
		
		return true;
	}
}
