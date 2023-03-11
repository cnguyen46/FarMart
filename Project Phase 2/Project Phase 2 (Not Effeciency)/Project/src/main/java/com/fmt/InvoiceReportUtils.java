package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 
 * This class is used to format the reports for {@link InvoiceReport}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class InvoiceReportUtils {

	/**
	 * This class creates summary report by total.
	 */
	public static StringBuilder totalReport(List<Invoice> invoice, List<InvoiceItem<?>> invoiceItem) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("+---------------------------------------------------------------------------+\n"));
		sb.append(String.format("| Summary Report - By Total                                                 |\n"));
		sb.append(String.format("+---------------------------------------------------------------------------+\n"));
		sb.append(String.format("%-8s %-8s %-20s %-4s %12s %14s\n", "Invoice", "Store", "Customer", "Num Items", "Tax",
				"Total"));

		int numOfItem = 0;
		double finalTax = 0.0;
		double finalTotal = 0.0;

		for (Invoice inv : invoice) {
			int count = 0;
			double tax = 0.0;
			double totalCost = 0.0;

			for (InvoiceItem<?> invItem : invoiceItem) {
				if (inv.getIdInvoice().equals((invItem.getInvoiceInfo().getIdInvoice()))) {
					count++;
					tax = tax + invItem.getTaxes();
					totalCost = totalCost + invItem.getGrandTotal();
				}
			}
			numOfItem = numOfItem + count;
			finalTax = tax + finalTax;
			finalTotal = totalCost + finalTotal;
			sb.append(String.format("%-8s %-8s %-20s %d %10s %9.2f %2s %11.2f\n", inv.getIdInvoice(),
					inv.getStoreInfo().getIdStore(), inv.getCustomerInfo().getFullName(), count, "$", tax, "$",
					totalCost));

		}
		sb.append(String.format("+---------------------------------------------------------------------------+\n"));
		sb.append(String.format("%41d %9s %9.2f %2s %11.2f", numOfItem, "$", finalTax, "$", finalTotal));
		return sb;
	}

	/**
	 * This class creates summary report by store sales.
	 */
	public static StringBuilder storeReport(List<Invoice> invoice, List<InvoiceItem<?>> invoiceItem) {

		List<Store> store = DataLoading.listStoreData();

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("+--------------------------------------------------------+\n"));
		sb.append(String.format("| Store Sales Summary Report                             |\n"));
		sb.append(String.format("+--------------------------------------------------------+\n"));
		sb.append(String.format("%-10s %-22s %-12s %s\n", "Store", "Manager", "Sales", "GrandTotal"));

		int numOfInvoice = 0;
		double finalTotal = 0.0;

		for (Store s : store) {
			int count = 0;
			double totalCost = 0.0;
			for (Invoice inv : invoice) {
				if (s.getIdStore().equals(inv.getStoreInfo().getIdStore())) {
					count++;
					for (InvoiceItem<?> invItem : invoiceItem) {
						if (inv.getIdInvoice().equals((invItem.getInvoiceInfo().getIdInvoice()))) {
							totalCost = totalCost + invItem.getGrandTotal();
						}
					}
				}
			}
			numOfInvoice = count + numOfInvoice;
			finalTotal = finalTotal + totalCost;
			sb.append(String.format("%-10s %-22s %-8d %s %12.2f\n", s.getIdStore(), s.getManagerInfo().getFullName(),
					count, "$", totalCost));
		}
		sb.append(String.format("+--------------------------------------------------------+\n"));
		sb.append(String.format("%35d %8s %12.2f", numOfInvoice, "$", finalTotal));
		return sb;
	}

	/**
	 * This class reports invoice's information in detail.
	 */
	public static StringBuilder detailReport(List<Invoice> invoice, List<InvoiceItem<?>> invoiceItem) {

		StringBuilder sb = new StringBuilder();
		for (Invoice inv : invoice) {
			sb.append(inv.toString());
			double subTotal = 0.0;
			double taxTotal = 0.0;

			for (InvoiceItem<?> invItem : invoiceItem) {
				if (inv.getIdInvoice().equals((invItem.getInvoiceInfo().getIdInvoice()))) {
					if (invItem.getItemInfo().getTypeItem().equals("E")) {
						if (((InvoiceEquipment) invItem).getAgreement().equals("P")) {
							sb.append(((InvoicePurchase) invItem).toString() + "\n");
							subTotal = subTotal + invItem.getSubTotal();
							taxTotal = taxTotal + invItem.getTaxes();
						} else if (((InvoiceEquipment) invItem).getAgreement().equals("L")) {
							sb.append(((InvoiceLease) invItem).toString() + "\n");
							subTotal = subTotal + invItem.getSubTotal();
							taxTotal = taxTotal + invItem.getTaxes();
						}
					} else if (invItem.getItemInfo().getTypeItem().equals("P")) {
						sb.append(((InvoiceProduct) invItem).toString() + "\n");
						subTotal = subTotal + invItem.getSubTotal();
						taxTotal = taxTotal + invItem.getTaxes();
					} else if (invItem.getItemInfo().getTypeItem().equals("S")) {
						sb.append(((InvoiceService) invItem).toString() + "\n");
						subTotal = subTotal + invItem.getSubTotal();
						taxTotal = taxTotal + invItem.getTaxes();
					}
				}
			}
			sb.append(String.format("%81s", "-=-=-=-=-=-\n"));
			sb.append(String.format("%70s%10.2f\n", "Subtotal $", subTotal));
			sb.append(String.format("%70s%10.2f\n", "Tax $", taxTotal));
			sb.append(String.format("%70s%10.2f\n", "Grand Total $", subTotal + taxTotal));
		}
		return sb;

	}

	/**
	 * This method is used to output the report into data/output.txt
	 */
	public static void outputReport() {
		// Create a list of Invoice, and Invoice Items.
		List<Invoice> invoice = DataLoading.listInvoiceData();
		List<InvoiceItem<?>> invoiceItem = DataLoading.listInvItemData();

		// Create three reports to prepare for the output file
		StringBuilder totalReport = totalReport(invoice, invoiceItem);
		StringBuilder storeReport = storeReport(invoice, invoiceItem);
		StringBuilder detailReport = detailReport(invoice, invoiceItem);

		File m = new File("data/output.txt");
		try {
			PrintWriter pw = new PrintWriter(m);
			pw.println(totalReport + "\n");
			pw.println(storeReport + "\n");
			pw.println(detailReport + "\n");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
