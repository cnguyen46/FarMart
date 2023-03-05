package com.fmt;

/**
 * This class contains the information of purchased equipment, which is the
 * subclass of {@link InvoiceEquipment}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */

public class InvoicePurchase extends InvoiceEquipment {

	public InvoicePurchase(Invoice idInvoice, Equipment idItem, String typeContract, double price) {
		super(idInvoice, idItem, typeContract, price);

	}

	@Override
	public double getSubTotalPayment() {
		if (this.getPrice() <= 0) {
			throw new IllegalArgumentException("You cannot have negative or zero price of equipment");
		} else {
			return this.getPrice();
		}
	}

	@Override
	public double getTaxes() {
		return 0;
	}

	@Override
	public String toString() {
		return this.getIdInvoice() + " " + this.getIdItem() + " " + this.getTypeContract() + " " + this.getPrice();
	}

}
