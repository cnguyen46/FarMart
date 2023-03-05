package com.fmt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * This class contains the information of lease equipment, which is the subclass
 * of {@link InvoiceEquipment}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class InvoiceLease extends InvoiceEquipment {

	private final LocalDate beginLease;
	private final LocalDate endLease;

	public InvoiceLease(Invoice idInvoice, Equipment idItem, String typeContract, double price,
			LocalDate beginLease, LocalDate endLease) {
		super(idInvoice, idItem, typeContract, price);
		this.beginLease = beginLease;
		this.endLease = endLease;

	}

	public LocalDate getBeginLease() {
		return beginLease;
	}

	public LocalDate getEndLease() {
		return endLease;
	}

	@Override
	public double getSubTotalPayment() {
		return this.getPrice()
				* (double)((ChronoUnit.DAYS.between(this.beginLease, this.endLease) + 1.0) / 30.0);
	}

	@Override
	public double getTaxes() {
		
		if (this.getPrice() <= 0) {
			throw new IllegalArgumentException("You cannot have negative or zero price of equipment");
		} else if(this.getPrice() > 0 && this.getPrice() < 10000.0) {
			return 0;
		} else if(this.getPrice() >= 10000.0 && this.getPrice() < 100000.0 ) {
			return 500;
		} else {
			return 1500;
		}
	}

	@Override
	public String toString() {
		return this.getIdInvoice() + " " + this.getIdItem() + " " + 
				this.getTypeContract() + " " + this.getPrice() + " " + this.beginLease + " " + this.endLease;
	}

}
