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
	private final Double price;
	private final String agreement;

	public Equipment(String idItem, String typeItem, String nameItem, String model) {
		super(idItem, typeItem, nameItem);
		this.model = model;
		this.price = null;
		this.agreement = null;
	}

	public Equipment(String idItem, String typeItem, String nameItem, String model, String agreement, Double price) {
		super(idItem, typeItem, nameItem);
		this.model = model;
		this.price = price;
		this.agreement = agreement;
	}

	public String getModel() {
		return this.model;
	}

	public String getAgreement() {
		return this.agreement;
	}

	public Double getPrice() {
		return this.price;
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
		return this.getIdItem() + " " + this.getTypeItem() + " " + this.getNameItem() + " " + this.model;
	}
}
