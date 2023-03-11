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
	private final Double quantity;
	private final static double taxRateProduct = 0.0715;

	public Product(String idItem, String typeItem, String nameItem, String unit, Double unitPrice) {
		super(idItem, typeItem, nameItem);
		this.unit = unit;
		this.unitPrice = unitPrice;
		this.quantity = null;
	}

	public Product(String idItem, String nameItem, String typeItem, String unit, Double unitPrice,
			Double quantity) {
		super(idItem, nameItem, typeItem);
		this.unit = unit;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public String getUnit() {
		return this.unit;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public Double getQuantity() {
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
			sb.append(String.format("%-8s (Product) %s\n", this.getIdItem(),
					this.getNameItem()));
			sb.append(String.format("%10.2f @ $%.2f/%s\n",this.quantity,this.unitPrice,this.unit));
			sb.append(String.format("%70s%10.2f","$", this.getSubTotal()));
			return sb.toString();
		}
}