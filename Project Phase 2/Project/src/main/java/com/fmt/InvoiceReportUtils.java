package com.fmt;

import java.util.Collections;
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
	 * This class reports invoice's information in detail.
	 */
	public static StringBuilder detailReport() {
		List<Invoice> invoice = DataLoading.listInvoiceData();
		List<InvoiceItem<? extends Item>> invoiceItem = DataLoading.listInvItemData();

		StringBuilder sb = new StringBuilder();
		for (Invoice s : invoice) {
			sb.append(String.format("Invoice #" + s.getIdInvoice() + "\n"));
			sb.append(String.format("Store   #" + s.getIdStore().getIdStore() + "\n"));
			sb.append(String.format("Date     " + s.getInvDate()) + "\n");
			sb.append(String.format("Customer:\n"));
			sb.append(String.format(s.getIdCustomer().getLastName() + ", " + s.getIdCustomer().getFirstName() + "  ("
					+ s.getIdCustomer().getIdPerson() + " : " + s.getIdCustomer().getEmail() + ")\n"));
			sb.append(String.format("        " + s.getIdCustomer().getAddressPerson().getStreet() + "\n"));
			sb.append(String.format("        " + s.getIdCustomer().getAddressPerson().getCity() + " "
					+ s.getIdCustomer().getAddressPerson().getState() + " "
					+ s.getIdCustomer().getAddressPerson().getZipCode() + " "
					+ s.getIdCustomer().getAddressPerson().getCountry() + "\n\n"));
			sb.append(String.format("Sales Person:\n"));
			sb.append(String.format(s.getIdSale().getLastName() + ", " + s.getIdSale().getFirstName() + "  ("
					+ s.getIdSale().getIdPerson() + " : " + s.getIdSale().getEmail() + ")\n"));
			sb.append(String.format("        " + s.getIdSale().getAddressPerson().getStreet() + "\n"));
			sb.append(String.format("        " + s.getIdSale().getAddressPerson().getCity() + " "
					+ s.getIdSale().getAddressPerson().getState() + " " + s.getIdSale().getAddressPerson().getZipCode()
					+ " " + s.getIdSale().getAddressPerson().getCountry() + "\n\n"));
			sb.append(String.format("Item\n"));
			sb.append(String.format("-==-==-==-==-==-==-==-==-==-\n"));
			for (InvoiceItem<? extends Item> x : invoiceItem) {
				if (s.getIdInvoice().equals((x.getIdInvoice()))) {

					System.out.println((Equipment) x.getIdItem());
//					System.out.format("%-8s (Purchase) %s-%s ", x.getIdItem());
				}

			}
		}
		return sb;

	}

	/**
	 * This class reports invoice's information in detail.
	 */
	public static StringBuilder storeReport() {
		List<Invoice> invoice = DataLoading.listInvoiceData();
		List<InvoiceItem<? extends Item>> invoiceItem = DataLoading.listInvItemData();

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("+----------------------------------------------------+\n"));
		sb.append(String.format("| Store Sales Summary Report                         |\n"));
		sb.append(String.format("+----------------------------------------------------+\n"));
		sb.append(String.format("%-10s %-20s %-10s %s\n", "Store", "Manager", "Sales", "GrandTotal"));

		return sb;
	}

}
