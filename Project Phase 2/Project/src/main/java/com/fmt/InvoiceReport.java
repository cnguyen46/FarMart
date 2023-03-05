package com.fmt;

import java.util.HashMap;
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

		System.out.println("Data and output files:");
		System.out.println("-==-==-==-==-==-==-==-==-");

		// Call out the method to print out the csv files
		DataLoading.parseData("Invoices", "data/invoices.csv");
		DataLoading.parseData("Invoice items", "data/invoiceitems.csv");

//		//TODO: Testing parsing Invoice.csv
//		HashMap<String,Invoice> hashMapInvoice = DataLoading.hashMapInvoiceData();
//		for(String str : hashMapInvoice.keySet()) {
//			System.out.println(hashMapInvoice.get(str));
//		}

////	TODO: Testing parsing InvoiceItem.csv
//		List<InvoiceItem<?>> listInvItem = DataLoading.listInvItemData();
//		for (int i = 0; i < listInvItem.size(); i++) {
//			System.out.println(listInvItem.get(i));
//		}
//		TODO: Test the report
//		StringBuilder sb = InvoiceReportUtils.detailReport();
		StringBuilder sb =InvoiceReportUtils.storeReport();
		
//		System.out.println(sb);
	}
}
