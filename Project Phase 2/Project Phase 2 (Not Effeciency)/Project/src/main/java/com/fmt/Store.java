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
	private final Person managerInfo;
	private final Address addressStore;

	public Store(String idStore, Person managerInfo, Address addressStore) {

		this.idStore = idStore;
		this.addressStore = addressStore;
		this.managerInfo = managerInfo;
	}

	public String getIdStore() {
		return this.idStore;
	}

	public Person getManagerInfo() {
		return this.managerInfo;
	}

	public Address getAddressStore() {
		return this.addressStore;
	}

	@Override
	public String toString() {
		return idStore + " " + managerInfo.getIdPerson() + " " + addressStore;
	}
	
	
	
}
