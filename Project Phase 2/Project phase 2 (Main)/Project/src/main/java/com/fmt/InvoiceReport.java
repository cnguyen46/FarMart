package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * This is program used to report the data of invoice, and invoice items, by
 * creating three reports from sales activities for FMT.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
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

		// Create a HashMap of Store, HashMap of Invoice, and a List of Invoice Items.
		HashMap<String, Store> hashMapStore = DataLoading.hashMapStoreData();
		HashMap<String, Invoice> hashMapInvoice = DataLoading.hashMapInvoiceData(hashMapStore);

		// This List<Item> is used to input the data of Item from the InvoiceItem.csv to
		// the List<Item>itemInfo inside the class Invoice.
		@SuppressWarnings("unused")
		List<Item> listInvItem = DataLoading.listInvItemData(hashMapInvoice);

		// Call out the method to convert the HashMap to the List
		List<Store> listStore = DataConverter.convertMapToList(hashMapStore);
		List<Invoice> listInvoice = DataConverter.convertMapToList(hashMapInvoice);

		// Sorting the data following the order

		Collections.sort(listStore);
		Collections.sort(listInvoice);

		// Call out the method to create a report
		StringBuilder totalReport = InvoiceReportFormating.totalReport(listInvoice);
		StringBuilder storeReport = InvoiceReportFormating.storeReport(listStore);
		StringBuilder detailReport = InvoiceReportFormating.detailReport(hashMapInvoice);

		// Print out the report
		System.out.println(totalReport);
		System.out.println(storeReport);
		System.out.println(detailReport);

		// Output the reports into a file output.txt
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
