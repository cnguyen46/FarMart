package com.fmt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the information of invoices data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class Invoice implements Comparable<Invoice> {

	private final String invoiceCode;
	private final Store storeInfo;
	private final Person customerInfo;
	private final Person salePersonInfo;
	private final LocalDate invoiceDate;
	private final List<Item> itemInfo = new ArrayList<>();

	public Invoice(String invoiceCode, Store storeInfo, Person customerInfo, Person salePersonInfo,
			LocalDate invoiceDate) {
		this.invoiceCode = invoiceCode;
		this.storeInfo = storeInfo;
		this.customerInfo = customerInfo;
		this.salePersonInfo = salePersonInfo;
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceCode() {
		return this.invoiceCode;
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

	public List<Item> getItemInfo() {
		return this.itemInfo;
	}

	public void addItem(Item item) {
		this.itemInfo.add(item);
	}

	public double getSubTotal() {
		double total = 0.0;
		for (Item item : itemInfo) {
			total = total + item.getSubTotal();
		}
		return total;
	}

	public double getTaxes() {
		double total = 0.0;
		for (Item item : itemInfo) {
			total = total + item.getTaxes();
		}
		return total;
	}

	public double getGrandTotal() {
		double total = 0.0;
		for (Item item : itemInfo) {
			total = total + item.getGrandTotal();
		}
		return total;
	}

	public int numOfItem() {
		return this.itemInfo.size();
	}
	
	public void detailReport(StringBuilder sb) {
		
		double subTotal = 0.0;
		double taxTotal = 0.0;
		sb.append(this.toString());
		for (Item i : this.itemInfo) {
				sb.append(i.toString() + "\n");
				subTotal = subTotal + i.getSubTotal();
				taxTotal = taxTotal + i.getTaxes();
		}
		sb.append(String.format("%81s", "-=-=-=-=-=-\n"));
		sb.append(String.format("%70s%10.2f\n", "Subtotal $", subTotal));
		sb.append(String.format("%70s%10.2f\n", "Tax $", taxTotal));
		sb.append(String.format("%70s%10.2f\n", "Grand Total $", subTotal + taxTotal));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Invoice #" + this.getInvoiceCode() + "\n"));
		sb.append(String.format("Store   #" + this.getStoreInfo().getStoreCode() + "\n"));
		sb.append(String.format("Date     " + this.getInvoiceDate()) + "\n");
		sb.append(String.format("Customer:\n"));
		sb.append(this.getCustomerInfo().toString() + "\n");
		sb.append(this.getCustomerInfo().getAddressPerson().toString() + "\n");
		sb.append(String.format("\nSales Person:\n"));
		sb.append(this.getSalePersonInfo().toString() + "\n");
		sb.append(this.getSalePersonInfo().getAddressPerson().toString() + "\n");
		sb.append(String.format("\n%s%76s\n", "Item", "Total"));
		sb.append(String.format("%-69s%s\n", "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", "-=-=-=-=-=-"));
		return sb.toString();
	}

	@Override
	public int compareTo(Invoice that) {

		int result = (int) (that.getSubTotal() - this.getSubTotal());
		return result;

	}
	
}
