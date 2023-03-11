package com.fmt;

import java.time.LocalDate;

/**
 * This class contains the information of invoices data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class Invoice implements Comparable<Invoice> {

	private final String idInvoice;
	private final Store storeInfo;
	private final Person customerInfo;
	private final Person salePersonInfo;
	private final LocalDate invoiceDate;

	public Invoice(String idInvoice, Store storeInfo, Person customerInfo, Person salePersonInfo, LocalDate invoiceDate) {
		this.idInvoice = idInvoice;
		this.storeInfo = storeInfo;
		this.customerInfo = customerInfo;
		this.salePersonInfo = salePersonInfo;
		this.invoiceDate = invoiceDate;
	}

	public String getIdInvoice() {
		return this.idInvoice;
	}

	public Store getStoreInfo() {
		return this.storeInfo;
	}

	public Person getCustomerInfo() {
		return this.customerInfo;
	}

	public Person getSalePersonInfo() {
		return this.salePersonInfo;
	}

	public LocalDate getInvoiceDate() {
		return this.invoiceDate;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Invoice #" + getIdInvoice() + "\n"));
		sb.append(String.format("Store   #" + getStoreInfo().getIdStore() + "\n"));
		sb.append(String.format("Date     " + getInvoiceDate()) + "\n");
		sb.append(String.format("Customer:\n"));
		sb.append(getCustomerInfo().toString() + "\n");
		sb.append(getCustomerInfo().getAddressPerson().toString() + "\n");
		sb.append(String.format("\nSales Person:\n"));
		sb.append(getSalePersonInfo().toString() + "\n");
		sb.append(getSalePersonInfo().getAddressPerson().toString() + "\n");
		sb.append(String.format("\n%s%76s\n", "Item", "Total"));
		sb.append(String.format("%-69s%s\n", "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", "-=-=-=-=-=-"));
		return sb.toString();
	}

	@Override
	public int compareTo(Invoice that) {
		int result = this.storeInfo.getManagerInfo().getLastName().
				compareTo(that.storeInfo.getManagerInfo().getLastName());
		if (result == 0) {
			result = this.storeInfo.getManagerInfo().getFirstName().
				compareTo(that.storeInfo.getManagerInfo().getFirstName());
		}
		return result;
	}

}
