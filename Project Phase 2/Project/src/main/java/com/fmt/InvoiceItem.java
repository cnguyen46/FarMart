package com.fmt;

/**
 * This class contains the information of invoice items data, which relates to
 * {@link Invoice}. 
 * 
 * It is an abstract class.  It has the Type T extends {@link Item}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */

public abstract class InvoiceItem<T extends Item> {

	private final Invoice idInvoice;
	private final T idItem;

	public InvoiceItem(Invoice idInvoice, T idItem) {
		this.idInvoice = idInvoice;
		this.idItem = idItem;
	}

	public Invoice getIdInvoice() {
		return this.idInvoice;
	}

	public T getIdItem() {
		return this.idItem;
	}

	public abstract double getSubTotalPayment();

	public abstract double getTaxes();
	
	public double getGrandTotal() {
		return getSubTotalPayment() + getTaxes();
	}

}
