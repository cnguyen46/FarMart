package com.fmt;

/**
 * This class contains the information of purchased equipment, which is the
 * subclass of {@link Equipment}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class EquipmentPurchase extends Equipment {

	private final Double price;
	
	public EquipmentPurchase(Equipment e, Double price) {
		super(e.getItemCode(), e.getNameItem(), e.getModel());
		this.price = price;
	}

	public Double getPrice() {
		return this.price;
	}
	
	@Override
	public double getSubTotal() {
		if (this.getPrice() <= 0) {
			throw new IllegalArgumentException("You cannot have negative or zero price of equipment");
		} else {
			return Math.round(this.getPrice() * 100) / 100.00;
		}
	}

	@Override
	public double getTaxes() {
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-8s (Purchase) %s - %s\n", this.getItemCode(), this.getNameItem(), this.getModel()));
		sb.append(String.format("%70s%10.2f", "$", this.getSubTotal()));
		return sb.toString();

	}
}
