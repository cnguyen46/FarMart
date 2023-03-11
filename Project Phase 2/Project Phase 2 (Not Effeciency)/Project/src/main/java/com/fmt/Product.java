package com.fmt;

/**
 * This class contains the information of products, which is the subclass of
 * {@link Item}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Product extends Item {

	private final String unit;
	private final double unitPrice;

	public Product(String idItem, String typeItem, String nameItem, String unit, double unitPrice) {
		super(idItem, typeItem, nameItem);
		this.unit = unit;
		this.unitPrice = unitPrice;
	}

	public String getUnit() {
		return this.unit;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	@Override
	public String toString() {
		return String.format("$%.2f/%s",unitPrice,unit);
	}

}
