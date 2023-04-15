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
public class InvoiceReportFormating {

	/**
	 * This class creates summary report by total.
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

			sb.append(String.format("%-8s %-8s %-30s %d %10s %9.2f %2s %11.2f\n", inv.getIdInvoice(),
					inv.getStoreInfo().getStoreCode(), inv.getCustomerInfo().getFullName(), count, "$", inv.getTaxes(),
					"$", inv.getGrandTotal()));

		}
		sb.append(String
				.format("+-------------------------------------------------------------------------------------+\n"));
		sb.append(String.format("%51d%10s%10.2f%3s%12.2f", numOfItem, "$", finalTax, "$", finalTotal));
		return sb;
	}

	/**
	 * This class creates summary report by store sales.
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
	 * This class reports invoice's information in detail.
	 */
	public static <T> StringBuilder detailReport(HashMap<T, Invoice> invoice) {

		StringBuilder sb = new StringBuilder();
		for (T inv : invoice.keySet()) {

			invoice.get(inv).detailReport(sb);
		}

		return sb;
	}
}