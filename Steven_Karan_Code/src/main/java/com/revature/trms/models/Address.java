package com.revature.trms.models;

public class Address {

	private int address_id;
	private String address_text;
	private String street_number;
	private String route;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	public Address() {
		
	}

	public Address(int address_id, String address_text, String street_number, String route, String city, String state,
			String zipcode, String country) {
		super();
		this.address_id = address_id;
		this.address_text = address_text;
		this.street_number = street_number;
		this.route = route;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getAddress_text() {
		return address_text;
	}

	public void setAddress_text(String address_text) {
		this.address_text = address_text;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + address_id;
		result = prime * result + ((address_text == null) ? 0 : address_text.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street_number == null) ? 0 : street_number.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (address_id != other.address_id)
			return false;
		if (address_text == null) {
			if (other.address_text != null)
				return false;
		} else if (!address_text.equals(other.address_text))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street_number == null) {
			if (other.street_number != null)
				return false;
		} else if (!street_number.equals(other.street_number))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", address_text=" + address_text + ", street_number="
				+ street_number + ", route=" + route + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
				+ ", country=" + country + "]";
	}

}
