package com.fmt;

/**
 * This class contains the information of purchased equipment, which is the
 * subclass of {@link InvoiceEquipment}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */

public class InvoicePurchase extends InvoiceEquipment {

	public InvoicePurchase(Invoice invoiceInfo, Equipment itemInfo, String agreement, double price) {
		super(invoiceInfo, itemInfo, agreement, price);

	}

	@Override
	public double getSubTotal() {
		if (this.getPrice() <= 0) {
			throw new IllegalArgumentException("You cannot have negative or zero price of equipment");
		} else {
			return Math.round(this.getPrice()*100)/100.00;
		}
	}

	@Override
	public double getTaxes() {
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-8s (Purchase) %s-%s\n", getItemInfo().getIdItem(),
				getItemInfo().getNameItem(), getItemInfo().getModel()));
		sb.append(String.format("%70s%10.2f","$", getSubTotal()));
		return sb.toString();
	}

}
