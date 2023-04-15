
package com.fmt;

import java.util.List;

/**
 * This class contains the information of persons data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Person {

	private Integer personId;
	private final String personCode;
	private final String lastName;
	private final String firstName;
	private final Address addressPerson;
	private final List<String> email;

	public Person(String personCode, String lastName, String firstName, Address addressPerson, List<String> email) {
		this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.addressPerson = addressPerson;
		this.email = email;
	}
	
	public Person(Integer personId, Person person) {
		this(person.personCode, person.lastName, person.firstName, person.addressPerson, person.email);
		this.personId = personId;
	}

	public Integer getPersonId() {
		return this.personId;
	}

	public String getPersonCode() {
		return this.personCode;
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
		return this.getFullName() + " (" + this.personCode + " : " + this.email + ")";
	}

}
