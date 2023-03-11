package com.fmt;

/**
 * This class contains the information of invoice items data, which relates to
 * {@link Invoice}.
 * 
 * It is an abstract class. It has the Type T extends {@link Item}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */

public abstract class InvoiceItem<T extends Item> {

	private final Invoice invoiceInfo;
	private final T itemInfo;

	public InvoiceItem(Invoice invoiceInfo, T itemInfo) {
		this.invoiceInfo = invoiceInfo;
		this.itemInfo = itemInfo;
	}

	public Invoice getInvoiceInfo() {
		return this.invoiceInfo;
	}

	public T getItemInfo() {
		return this.itemInfo;
	}
	
	public abstract double getSubTotal();

	public abstract double getTaxes();

	public double getGrandTotal() {
		return getSubTotal() + getTaxes();
	}

}
