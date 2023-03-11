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

	public InvoiceProduct(Invoice invoiceInfo, Product itemInfo, double quantity) {
		super(invoiceInfo, itemInfo);
		this.quantity = quantity;
	}

	@Override
	public double getSubTotal() {
		return Math.round(this.quantity * this.getItemInfo().getUnitPrice() * 100)/100.00;
	}

	@Override
	public double getTaxes() {
		return Math.round(this.getSubTotal() * taxRateProduct * 100)/100.00;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-8s (Product) %s\n", getItemInfo().getIdItem(),
				this.getItemInfo().getNameItem()));
		sb.append(String.format("%10.2f @ %s\n",quantity,getItemInfo().toString()));
		sb.append(String.format("%70s%10.2f","$", getSubTotal()));
		return sb.toString();
	}

}
