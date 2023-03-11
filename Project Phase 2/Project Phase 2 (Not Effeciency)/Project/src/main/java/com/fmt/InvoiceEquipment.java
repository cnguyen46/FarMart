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

	private final String agreement;
	private final double price;

	public InvoiceEquipment(Invoice invoiceInfo, Equipment itemInfo, String agreement, double price) {
		super(invoiceInfo, itemInfo);
		this.agreement = agreement;
		this.price = price;
	}

	public String getAgreement() {
		return this.agreement;
	}

	public double getPrice() {
		return this.price;
	}

}
