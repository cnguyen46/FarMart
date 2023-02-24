
package com.fmt;

import java.util.List;

/**
 * This class contains the information of persons data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class Person {

	private String idPerson;
	private String lastName;
	private String firstName;
	private Address addressPerson;
	private List<String> email;

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

	public String toString() {
		return this.idPerson + " " + this.lastName + " " + this.firstName + " " + this.addressPerson + " " + this.email;
	}

}
