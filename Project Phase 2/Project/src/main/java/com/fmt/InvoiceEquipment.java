package com.fmt;

/**
 * This class contains the information of invoice equipment, which is the
 * subclass of {@link InvoiceItem}.
 * 
 * It is an abstract class.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */

public abstract class InvoiceEquipment extends InvoiceItem<Equipment> {

	private final String typeContract;
	private final double price;

	public InvoiceEquipment(Invoice idInvoice, Equipment idItem, String typeContract, double price) {
		super(idInvoice, idItem);
		this.typeContract = typeContract;
		this.price = price;
	}

	public String getTypeContract() {
		return this.typeContract;
	}

	public double getPrice() {
		return this.price;
	}

}
