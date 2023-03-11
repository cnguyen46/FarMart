package com.fmt;

/**
 * This class contains the information of items data. It is an abstract class.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public abstract class Item {

	private final String idItem;
	private final String typeItem;
	private final String nameItem;

	public Item(String idItem, String typeItem, String nameItem) {
		this.idItem = idItem;
		this.typeItem = typeItem;
		this.nameItem = nameItem;
	}

	public String getIdItem() {
		return this.idItem;
	}

	public String getNameItem() {
		return this.nameItem;
	}

	public String getTypeItem() {
		return this.typeItem;
	}

	public abstract double getSubTotal();

	public abstract double getTaxes();

	public double getGrandTotal() {
		return getSubTotal() + getTaxes();
	}

}
