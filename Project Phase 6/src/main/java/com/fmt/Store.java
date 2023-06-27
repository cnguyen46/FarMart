package com.fmt;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * This class contains the information of stores data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Store implements Comparable<Store> {

	private Integer storeId;
	private final String storeCode;
	private final Person managerInfo;
	private final Address addressStore;
	@XStreamOmitField
	@GsonOmitField
	private final List<Invoice> invoiceInfo = new ArrayList<>();

	public Store(String storeCode, Person managerInfo, Address addressStore) {
		this.storeCode = storeCode;
		this.managerInfo = managerInfo;
		this.addressStore = addressStore;
	}
	
	public Store(Integer storeId, Store store) {
		this(store.storeCode, store.managerInfo, store.addressStore);
		this.storeId = storeId;
	}
	
	public Integer getStoreId() {
		return this.storeId;
	}
	
	public String getStoreCode() {
		return this.storeCode;
	}

	public Person getManagerInfo() {
		return this.managerInfo;
	}

	public Address getAddressStore() {
		return this.addressStore;
	}

	public void addInvoice(Invoice i) {
		this.invoiceInfo.add(i);
	}

	public List<Invoice> getInvoiceInfo() {
		return this.invoiceInfo;
	}

	public double getSubTotal() {
		double total = 0.0;
		for (Invoice invoice : invoiceInfo) {
			total = total + invoice.getSubTotal();
		}
		return total;
	}

	public double getTaxes() {
		double total = 0.0;
		for (Invoice invoice : invoiceInfo) {
			total = total + invoice.getTaxes();
		}
		return total;
	}

	public double getGrandTotal() {
		double total = 0.0;
		for (Invoice invoice : invoiceInfo) {
			total = total + invoice.getGrandTotal();
		}
		return total;
	}

	public int numOfInvoice() {
		return this.invoiceInfo.size();
	}

	@Override
	public String toString() {
		return this.storeCode + " " + this.managerInfo.getPersonCode() + " " + this.addressStore;
	}

	@Override
	public int compareTo(Store that) {
		int result = this.getManagerInfo().getFirstName().compareToIgnoreCase(that.getManagerInfo().getFirstName());
		if (result == 0) {
			result = this.getManagerInfo().getLastName().compareToIgnoreCase(that.getManagerInfo().getLastName());
		} if (result == 0) {
			result = (int) (that.getSubTotal() - this.getSubTotal());
		}
		return result;
	}
}
