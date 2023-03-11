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

	public InvoiceService(Invoice invoiceInfo, Service itemInfo, double hourService) {
		super(invoiceInfo, itemInfo);
		this.hourService = hourService;
	}

	public double getHourService() {
		return this.hourService;
	}

	@Override
	public double getSubTotal() {
		return Math.round(this.hourService * this.getItemInfo().getHourlyRate()*100)/100.00;
	}

	@Override
	public double getTaxes() {
		return Math.round(getSubTotal() * taxRateService *100)/100.00;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-8s (Service) %s\n", getItemInfo().getIdItem(),
				this.getItemInfo().getNameItem()));
		sb.append(String.format("%8.1f hours @ %s\n",hourService,getItemInfo().toString()));
		sb.append(String.format("%70s%10.2f","$", getSubTotal()));
		return sb.toString();}

}
