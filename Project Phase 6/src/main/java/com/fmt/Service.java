package com.fmt;

/**
 * This class contains the information of services, which is the subclass of
 * {@link Item}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Service extends Item {

	private final Double hourlyRate;
	private final Double amountOfHour;
	private static final double taxRateService = 0.0345;

	public Service(String itemCode, String nameItem, Double hourlyRate) {
		super(itemCode, nameItem);
		this.hourlyRate = hourlyRate;
		this.amountOfHour = null;
	}

	public Service(Service s, Double amountOfHour) {
		super(s.getItemCode(), s.getNameItem());
		this.hourlyRate = s.hourlyRate;
		this.amountOfHour = amountOfHour;
	}

	public Double getHourlyRate() {
		return this.hourlyRate;
	}

	public Double getAmountOfHour() {
		return this.amountOfHour;
	}

	@Override
	public double getSubTotal() {
		return Math.round(this.amountOfHour * this.hourlyRate * 100) / 100.00;
	}

	@Override
	public double getTaxes() {
		return Math.round(getSubTotal() * taxRateService * 100) / 100.00;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-8s (Service) %s\n", this.getItemCode(), this.getNameItem()));
		sb.append(String.format("%8.1f hours @ $%.2f/hr\n", this.amountOfHour, this.hourlyRate));
		sb.append(String.format("%70s%10.2f", "$", this.getSubTotal()));
		return sb.toString();
	}

}
