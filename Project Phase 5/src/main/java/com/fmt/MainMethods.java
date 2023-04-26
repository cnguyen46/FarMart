package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * This class contains all the main methods for each phase of the project.
 * <p>
 * For showing the result of each phase, the users can call their desired method
 * in the main method <code> public static void
 * main(String[] args){} </code> inside this class.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class MainMethods {

	/**
	 * This is the result of the Phase 1, which requires to convert the data into
	 * JSON, or XML file.
	 * <li>For seeing the detail of converting JSON, searching in
	 * {@link DataConverterUtils#convertToJson(List,String)}.
	 * <li>For seeing the detail of converting XML, searching in
	 * {@link DataConverterUtils#convertToXml(List,String,String)}.
	 */
	public static void DataConverter() {
		System.out.println("Data and output files handed in:");
		System.out.println("-==-==-==-==-==-==-==-==-==-==-==-");

		// Call out the method to print out the CSV file
		ParsingCsvFile.parseData("Person.csv:", "data/Persons.csv");
		ParsingCsvFile.parseData("Store.csv:", "data/Stores.csv");
		ParsingCsvFile.parseData("Item.csv:", "data/Items.csv");

		// Create the List for Person, Store, and Item
		List<Person> persons = new ArrayList<>();
		List<Store> stores = new ArrayList<>();
		List<Item> items = new ArrayList<>();

		// Call out the method to convert HashMap to List for Person, Store, and Item
		persons = DataConverterUtils.convertListToHashMap(ParsingCsvFile.personData());
		stores = DataConverterUtils.convertListToHashMap(ParsingCsvFile.storeData());
		items = DataConverterUtils.convertListToHashMap(ParsingCsvFile.itemData());

		// Call out the method to convert the CSV file into JSON file
		DataConverterUtils.convertToJson(persons, "data/Persons.json");
		DataConverterUtils.convertToJson(stores, "data/Stores.json");
		DataConverterUtils.convertToJson(items, "data/Items.json");

		// Call out the method to convert the CSV file into XML file
		DataConverterUtils.convertToXml(persons, "data/Persons.xml", "person");
		DataConverterUtils.convertToXml(stores, "data/Stores.xml", "store");
		DataConverterUtils.convertToXml(items, "data/Items.xml", "item");

	}

	/**
	 * This is the result of the Phase 2, which requires to create three reports,
	 * but loading data from CSV file {@link ParsingCsvFile}:
	 * <li>Summary Report;
	 * <li>Store Sales Summary Report;
	 * <li>Detail Report.
	 * <p>
	 * For seeing the detail of formating those report, searching in
	 * {@link InvoiceReportFormating}.
	 */
	public static void InvoiceReportFromCSV() {

		System.out.println("Data and output.txt files:");
		System.out.println("-==-==-==-==-==-==-==-==-");

		// Call out the method to print out the CSV files
		ParsingCsvFile.parseData("Person.csv", "data/Persons.csv");
		ParsingCsvFile.parseData("Stores.csv", "data/Stores.csv");
		ParsingCsvFile.parseData("Items.csv", "data/Items.csv");
		ParsingCsvFile.parseData("Invoices.csv", "data/Invoices.csv");
		ParsingCsvFile.parseData("InvoiceItems.csv", "data/InvoiceItems.csv");

		// Create a HashMap of Store, HashMap of Invoice, and a List of Invoice Items.
		HashMap<String, Store> hashMapStore = ParsingCsvFile.storeData();
		HashMap<String, Invoice> hashMapInvoice = ParsingCsvFile.invoiceData(hashMapStore);

		// This List<Item> is used to input the data of Item from the InvoiceItem.csv to
		// the List<Item>itemInfo inside the class Invoice.
		ParsingCsvFile.invoiceItemData(hashMapInvoice);

		// Call out the method to convert the HashMap to the List
		List<Store> listStore = DataConverterUtils.convertListToHashMap(hashMapStore);
		List<Invoice> listInvoice = DataConverterUtils.convertListToHashMap(hashMapInvoice);

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
		File m = new File("data/outputFromCSV.txt");
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

	/**
	 * This is the result of the Phase 4, which requires to create three reports,
	 * but loading data from Database {@link DatabaseLoader}:
	 * <li>Summary Report;
	 * <li>Store Sales Summary Report;
	 * <li>Detail Report.
	 * <p>
	 * For seeing the detail of formating these reports, searching in
	 * {@link InvoiceReportFormating}.
	 */
	public static void InvoiceReportFromDBD() {

		// Create a HashMap of Store, HashMap of Invoice, and a List of Invoice Items.
		HashMap<Integer, Store> hashMapStore = DatabaseLoader.getstoreData();
		HashMap<Integer, Invoice> hashMapInvoice = DatabaseLoader.getInvoiceData(hashMapStore);

		// Create a List of item containing information using for invoice.
		DatabaseLoader.getItemData(hashMapInvoice);

		// Call out the method to convert the HashMap to the List
		List<Store> listStore = DataConverterUtils.convertListToHashMap(hashMapStore);
		List<Invoice> listInvoice = DataConverterUtils.convertListToHashMap(hashMapInvoice);

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
	}
}
