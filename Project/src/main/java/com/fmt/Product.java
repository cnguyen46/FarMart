package com.fmt;
/**
 * This class contains the information of products, which is the subclass of {@link Item}.
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Product extends Item {

	private String unit;
	private double unitPrice;
	
	public Product(String idItem, String nameItem, String unit, double unitPrice) {
		super(idItem, nameItem);
		this.unit = unit;
		this.unitPrice = unitPrice;
	}

	
	public String getUnit() {
		return this.unit;
	}


	public double getUnitPrice() {
		return this.unitPrice;
	}

	public String toString() {
		return this.getIdItem() + " " + this.getNameItem() 
				+ " " + this.unit + " " + this.unitPrice;
	}

	
}
