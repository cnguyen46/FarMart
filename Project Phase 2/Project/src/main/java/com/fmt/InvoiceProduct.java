package com.fmt;

/**
 * This class contains the information of invoice product, which is the subclass
 * of {@link InvoiceItem}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */

public class InvoiceProduct extends InvoiceItem<Product> {

	private final double quantity;
	private static final double taxRateProduct = 0.0715;

	public InvoiceProduct(Invoice idInvoice, Product idItem, double quantity) {
		super(idInvoice, idItem);
		this.quantity = quantity;
	}

	@Override
	public double getSubTotalPayment() {
		return (this.quantity * this.getIdItem().getUnitPrice());
	}

	@Override
	public double getTaxes() {
		return getSubTotalPayment() * taxRateProduct;
	}

	@Override
	public String toString() {
		return this.getIdInvoice() + " " + this.getIdItem() + " " + this.quantity;
	}

}
