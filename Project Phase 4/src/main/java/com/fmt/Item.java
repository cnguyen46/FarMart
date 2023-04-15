package com.fmt;

/**
 * This class contains the information of items data. It is an abstract class.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public abstract class Item {

	private Integer itemId;
	private final String itemCode;
	private final String nameItem;

	public Item(String itemCode, String nameItem) {
		this.itemCode = itemCode;
		this.nameItem = nameItem;
	}
	
	public Item(Integer itemId, Item item) {
		this(item.itemCode, item.nameItem);
		this.itemId = itemId;
	}
	
	public Integer getItemId() {
		return itemId;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public String getNameItem() {
		return this.nameItem;
	}
	
	//Those below methods are used to calculate:
	// - SubTotal of every items of each invoice
	// - Taxes of every items of each invoice
	// - The total of SubTotal and Taxes
	public abstract double getSubTotal();
	public abstract double getTaxes();
	public double getGrandTotal() {
		return getSubTotal() + getTaxes();
	}

}