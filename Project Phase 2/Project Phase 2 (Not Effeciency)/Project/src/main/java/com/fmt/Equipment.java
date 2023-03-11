package com.fmt;

/**
 * This class contains the information of equipment, which is the subclass of
 * {@link Item}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Equipment extends Item {

	private final String model;

	public Equipment(String idItem, String typeItem, String nameItem, String model) {
		super(idItem, typeItem, nameItem);
		this.model = model;
	}

	public String getModel() {
		return this.model;
	}

	@Override
	public String toString() {
		return getIdItem() + " " + getTypeItem() + " " + getNameItem() + " " + model;
	}

}
