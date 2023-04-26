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

	public Equipment(String itemCode, String nameItem, String model) {
		super(itemCode, nameItem);
		this.model = model;
	}

	public String getModel() {
		return this.model;
	}

	@Override
	public double getSubTotal() {
		return 0;
	}

	@Override
	public double getTaxes() {
		return 0;
	}

	@Override
	public String toString() {
		return this.getItemCode() + " " + this.getNameItem() + " " + this.model;
	}
}
