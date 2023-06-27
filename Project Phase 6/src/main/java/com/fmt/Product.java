package com.fmt;

/**
 * This class contains the information of products, which is the subclass of
 * {@link Item}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Product extends Item {

	private final String unit;
	private final Double unitPrice;
	private final Integer quantity;
	private final static double taxRateProduct = 0.0715;

	public Product(String itemCode, String nameItem, String unit, Double unitPrice) {
		super(itemCode, nameItem);
		this.unit = unit;
		this.unitPrice = unitPrice;
		this.quantity = null;
	}

	public Product(Product p, Integer quantity) {
		super(p.getItemCode(), p.getNameItem());
		this.unit = p.unit;
		this.unitPrice = p.unitPrice;
		this.quantity = quantity;
	}

	public String getUnit() {
		return this.unit;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	@Override
	public double getSubTotal() {
		return Math.round(this.quantity * this.unitPrice * 100) / 100.00;
	}

	@Override
	public double getTaxes() {
		return Math.round(this.getSubTotal() * taxRateProduct * 100) / 100.00;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-8s (Product) %s\n", this.getItemCode(), this.getNameItem()));
		sb.append(String.format("%10d @ $%.2f/%s\n", this.quantity, this.unitPrice, this.unit));
		sb.append(String.format("%70s%10.2f", "$", this.getSubTotal()));
		return sb.toString();
	}
}