package com.fmt;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * This class is used to format the reports for {@link InvoiceReport}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class ReportFormating {

	/**
	 * This method creates summary report by total.
	 */
	public static StringBuilder totalReport(List<Invoice> invoice) {

		StringBuilder sb = new StringBuilder();
		sb.append(String
				.format("+-------------------------------------------------------------------------------------+\n"));
		sb.append(String
				.format("| Summary Report - By Total                                                           |\n"));
		sb.append(String
				.format("+-------------------------------------------------------------------------------------+\n"));
		sb.append(String.format("%-8s %-8s %-30s %-4s %12s %14s\n", "Invoice", "Store", "Customer", "Num Items", "Tax",
				"Total"));

		int numOfItem = 0;
		int count = 0;
		double finalTax = 0.0;
		double finalTotal = 0.0;

		for (Invoice inv : invoice) {

			count = inv.numOfItem();
			numOfItem = count + numOfItem;
			finalTax = finalTax + inv.getTaxes();
			finalTotal = finalTotal + inv.getGrandTotal();

			sb.append(String.format("%-8s %-8s %-30s %d %10s %9.2f %2s %11.2f\n", inv.getInvoiceCode(),
					inv.getStoreInfo().getStoreCode(), inv.getCustomerInfo().getFullName(), count, "$", inv.getTaxes(),
					"$", inv.getGrandTotal()));

		}
		sb.append(String
				.format("+-------------------------------------------------------------------------------------+\n"));
		sb.append(String.format("%51d%10s%10.2f%3s%12.2f", numOfItem, "$", finalTax, "$", finalTotal));
		return sb;
	}

	/**
	 * This method creates summary report by store sales.
	 */
	public static StringBuilder storeReport(List<Store> store) {

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("+---------------------------------------------------------------+\n"));
		sb.append(String.format("| Store Sales Summary Report                                    |\n"));
		sb.append(String.format("+---------------------------------------------------------------+\n"));
		sb.append(String.format("%-10s %-28s %-12s %s\n", "Store", "Manager", "Sales", "Grand Total"));

		int numOfInvoice = 0;
		int count = 0;
		double finalTotal = 0.0;

		for (Store s : store) {

			count = s.numOfInvoice();
			numOfInvoice = count + numOfInvoice;
			finalTotal = finalTotal + s.getGrandTotal();

			String storeInfo = String.format("%-10s %-28s", s.getStoreCode(), s.getManagerInfo().getFullName());
			sb.append(String.format("%s %d %8s %13.2f\n", storeInfo, s.numOfInvoice(), "$", s.getGrandTotal()));

		}
		sb.append(String.format("+---------------------------------------------------------------+\n"));
		sb.append(String.format("%41d%9s%14.2f", numOfInvoice, "$", finalTotal));
		return sb;
	}

	/**
	 * This method reports invoice's information in detail.
	 */
	public static <T> StringBuilder detailReport(HashMap<T, Invoice> invoice) {

		StringBuilder sb = new StringBuilder();
		for (T inv : invoice.keySet()) {
			invoice.get(inv).detailReport(sb);
		}
		return sb;
	}

	/**
	 * This method creates a sales reports:
	 * <p>
	 * Sales by Customer Sales by Total Sales by Store
	 * 
	 * @param invoice
	 * @param title
	 * @return
	 */
	public static StringBuilder salesReport(SortedLinkedList<Invoice> invoice, String title) {

		StringBuilder sb = new StringBuilder();
		sb.append(String
				.format("+----------------------------------------------------------------------------------+\n"));
		sb.append(String.format("| %-80s |\n", title));
		sb.append(String
				.format("+----------------------------------------------------------------------------------+\n"));
		sb.append(String.format("%s %10s %15s %25s %16s\n", "Sales", "Store", "Customer", "Salesperson", "Total"));

		for (Invoice i : invoice) {
			sb.append(String.format("%-10s %-12s %-22s %-22s %-2s %10.2f \n", i.getInvoiceCode(),
					i.getStoreInfo().getStoreCode(), i.getCustomerInfo().getFullName(),
					i.getSalePersonInfo().getFullName(), "$", i.getGrandTotal()));
		}
		sb.append(String
				.format("+----------------------------------------------------------------------------------+\n"));
		return sb;
	}
}