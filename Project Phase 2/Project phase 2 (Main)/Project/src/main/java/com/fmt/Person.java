
package com.fmt;

import java.util.List;

/**
 * This class contains the information of persons data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Person {

	private final String idPerson;
	private final String lastName;
	private final String firstName;
	private final Address addressPerson;
	private final List<String> email;

	public Person(String idPerson, String lastName, String firstName, Address addressPerson, List<String> emails) {
		this.idPerson = idPerson;
		this.lastName = lastName;
		this.firstName = firstName;
		this.addressPerson = addressPerson;
		this.email = emails;
	}

	public String getIdPerson() {
		return this.idPerson;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Address getAddressPerson() {
		return this.addressPerson;
	}

	public List<String> getEmail() {
		return this.email;
	}
	
	public String getFullName() {
		return this.lastName + ", " + this.firstName;
	}

	@Override
	public String toString() {
		return this.getFullName() + " (" + this.idPerson + " : " + this.email + ")";
	}

}
