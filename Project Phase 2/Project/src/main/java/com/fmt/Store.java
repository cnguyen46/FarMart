package com.fmt;

/**
 * This class contains the information of stores data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class Store {

	private final String idStore;
	private final Person idManager;
	private final Address addressStore;

	public Store(String idStore, Person managerCode, Address addressStore) {

		this.idStore = idStore;
		this.addressStore = addressStore;
		this.idManager = managerCode;
	}

	public String getIdStore() {
		return this.idStore;
	}

	public Person getIdManager() {
		return this.idManager;
	}

	public Address getAddressStore() {
		return this.addressStore;
	}

	@Override
	public String toString() {
		return this.idStore + " " + this.idManager.getIdPerson() + " " + this.addressStore;
	}
	
	
	
}
