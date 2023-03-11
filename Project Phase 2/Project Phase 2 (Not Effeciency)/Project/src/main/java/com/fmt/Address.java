package com.fmt;

/**
 * This class contains the information of address.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Address {

	private final String street;
	private final String city;
	private final String state;
	private final String zipCode;
	private final String country;

	public Address(String street, String city, String state, String zipCode, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	public String getStreet() {
		return this.street;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public String getCountry() {
		return this.country;
	}

	@Override
	public String toString() {
		return "     " + street + "\n     " + city + " " + state + " " + zipCode + " " + country;
	}

}
