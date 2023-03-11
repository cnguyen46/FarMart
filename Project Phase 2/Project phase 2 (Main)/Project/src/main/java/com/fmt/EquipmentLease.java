package com.fmt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * This class contains the information of leased equipment, which is the
 * subclass of {@link Equipment}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class EquipmentLease extends Equipment {

	private final LocalDate beginLease;
	private final LocalDate endLease;

	public EquipmentLease(String idItem, String typeItem, String nameItem, String model, String agreement, Double price,
			LocalDate beginLease, LocalDate endLease) {
		super(idItem, typeItem, nameItem, model, agreement, price);
		this.beginLease = beginLease;
		this.endLease = endLease;
	}

	public LocalDate getBeginLease() {
		return this.beginLease;
	}

	public LocalDate getEndLease() {
		return this.endLease;
	}

	public int getNumOfLeaseDay() {
		return (int) (ChronoUnit.DAYS.between(this.beginLease, this.endLease) + 1);
	}

	@Override
	public double getSubTotal() {
		return Math.round(this.getPrice() * (double) (this.getNumOfLeaseDay() / 30.0) * 100) / 100.00;
	}

	@Override
	public double getTaxes() {

		if (this.getPrice() <= 0) {
			throw new IllegalArgumentException("You cannot have negative or zero price of equipment");
		} else if (this.getSubTotal() > 0 && this.getSubTotal() < 10000.0) {
			return 0;
		} else if (this.getSubTotal() >= 10000.0 && this.getSubTotal() < 100000.0) {
			return 500;
		} else {
			return 1500;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-8s (Lease) %s-%s\n", this.getIdItem(), this.getNameItem(), this.getModel()));
		sb.append(String.format("%8d days (%s -> %s) @ $%.2f/ 30 days\n", this.getNumOfLeaseDay(), this.beginLease,
				this.endLease, this.getPrice()));
		sb.append(String.format("%70s%10.2f", "$", this.getSubTotal()));
		return sb.toString();
	}
}
