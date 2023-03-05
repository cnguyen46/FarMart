package com.fmt;

/**
 * This class contains the information of invoice service, which is the subclass
 * of {@link InvoiceItem}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */

public class InvoiceService extends InvoiceItem<Service> {

	private final double hourService;
	private static final double taxRateService = 0.0345;

	public InvoiceService(Invoice idInvoice, Service idItem, double hourService) {
		super(idInvoice, idItem);
		this.hourService = hourService;
	}

	public double getHourService() {
		return this.hourService * this.getIdItem().getHourlyRate();
	}

	@Override
	public double getSubTotalPayment() {
		return this.hourService;
	}

	@Override
	public double getTaxes() {
		return this.getSubTotalPayment() * taxRateService;
	}

	@Override
	public String toString() {
		return this.getIdInvoice() + " " + this.getIdItem() + " " + this.hourService;
	}

}
