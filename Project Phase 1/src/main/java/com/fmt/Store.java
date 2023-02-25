package com.fmt;

/**
 * This class contains the information of stores data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class Store {

	private String idStore;
	private Person managerCode;
	private Address addressStore;

	public Store(String idStore, Person managerCode, Address addressStore) {

		this.idStore = idStore;
		this.addressStore = addressStore;
		this.managerCode = managerCode;
	}

	public String getIdStore() {
		return this.idStore;
	}

	public Person getManagerCode() {
		return this.managerCode;
	}

	public Address getAddressStore() {
		return this.addressStore;
	}

	public String toString() {
		return this.idStore + " " + this.managerCode.getIdPerson() + " " + this.addressStore;
	}

}
