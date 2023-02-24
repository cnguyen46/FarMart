package com.fmt;
/**
 * This class contains the information of equipment, which is the subclass of {@link Item}.
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Equipment extends Item {

	private String model;
	
	public Equipment(String idItem, String nameItem, String model) {
		super(idItem, nameItem);
		this.model = model;
	}

	public String getModel() {
		return this.model;
	}

	public String toString() {
		return this.getIdItem() + " " + this.getNameItem() 
				+ " " + this.model;
	}
	
	
	
}
