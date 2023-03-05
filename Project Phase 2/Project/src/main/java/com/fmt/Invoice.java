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
	private final Store idStore;
	private final Person idCustomer;
	private final Person idSale;
	private final LocalDate invDate;

	public Invoice(String idInvoice, Store idStore, Person idCustomer, Person idSale, LocalDate invDate) {
		this.idInvoice = idInvoice;
		this.idStore = idStore;
		this.idCustomer = idCustomer;
		this.idSale = idSale;
		this.invDate = invDate;
	}

	public String getIdInvoice() {
		return this.idInvoice;
	}

	public Store getIdStore() {
		return this.idStore;
	}

	public Person getIdCustomer() {
		return this.idCustomer;
	}

	public Person getIdSale() {
		return this.idSale;
	}

	public LocalDate getInvDate() {
		return this.invDate;
	}

	@Override
	public String toString() {
		return this.idInvoice + " " + this.idStore + " " + this.idCustomer + " " + this.idSale + " " + this.invDate;
	}

	@Override
	public int compareTo(Invoice that) {
		int result = this.idStore.getIdManager().getLastName().compareTo(that.idStore.getIdManager().getLastName());
		if (result == 0) {
			result = this.idStore.getIdManager().getFirstName().compareTo(that.idStore.getIdManager().getFirstName());
		}
		return result;
	}

}
