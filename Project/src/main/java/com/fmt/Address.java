package com.fmt;

/**
 * This class contains the information of address.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Address {

	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;

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

	public String toString() {
		return this.street + " " + this.city + " " + this.state + " " + this.zipCode + " " + this.country;
	}

}
