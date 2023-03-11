package com.fmt;

import java.util.List;

/**
 * 
 * This is program used to report the data of invoice, and invoice items, by
 * creating three reports from sales activities for FMT.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 * @date March 04, 2023
 *
 */

public class InvoiceReport {

	/**
	 * This is the main program used to call for all the class, and method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Data and output.txt files:");
		System.out.println("-==-==-==-==-==-==-==-==-");

		// Call out the method to print out the csv files
		DataLoading.parseData("Person.csv", "data/Persons.csv");
		DataLoading.parseData("Stores.csv", "data/Stores.csv");
		DataLoading.parseData("Items.csv", "data/Items.csv");
		DataLoading.parseData("Invoices.csv", "data/Invoices.csv");
		DataLoading.parseData("InvoiceItems.csv", "data/InvoiceItems.csv");
		
		// Create a list of Invoice, and Invoice Items.
		List<Invoice> invoice = DataLoading.listInvoiceData();
		List<InvoiceItem<?>> invoiceItem = DataLoading.listInvItemData();
		
		// Call out the method to create a report 
		StringBuilder totalReport = InvoiceReportUtils.totalReport(invoice,invoiceItem);
		StringBuilder storeReport = InvoiceReportUtils.storeReport(invoice,invoiceItem);
		StringBuilder detailReport = InvoiceReportUtils.detailReport(invoice,invoiceItem);
		
		// Print out the report
		System.out.println(totalReport);
		System.out.println(storeReport);
		System.out.println(detailReport);

		// Call out the method to output the report into output.txt 
		InvoiceReportUtils.outputReport();
		
	}
}
