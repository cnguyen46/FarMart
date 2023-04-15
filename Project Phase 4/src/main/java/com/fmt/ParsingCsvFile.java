package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to input the data from csv file as a List, or HashMap.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class ParsingCsvFile {

	/**
	 * 
	 * This method is used to print out line by line for CSV files
	 * 
	 * @param fileName
	 * @param fileLocation
	 */

	public static void parseData(String fileName, String fileLocation) {

		System.out.println(fileName);
		File f = new File(fileLocation);
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String line = sc.nextLine();
				System.out.println(line);
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		System.out.println("");
	}

	/**
	 * This method is used to input the data from {@link Person}.csv as a HashMap
	 *
	 * @return <code> HashMap<<code>String,Person> </code>
	 */
	public static HashMap<String, Person> personData() {

		HashMap<String, Person> hashMapPerson = new HashMap<>();

		File f = new File("data/Persons.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				String idPerson = tokens[0];
				String lastName = tokens[1];
				String firstName = tokens[2];
				String street = tokens[3];
				String city = tokens[4];
				String state = tokens[5];
				String zipCode = tokens[6];
				String country = tokens[7];
				List<String> email = new ArrayList<>();
				for (int i = 8; i < tokens.length; i++) {
					email.add(tokens[i]);
				}
				Address addressPerson = new Address(street, city, state, zipCode, country);
				Person person = new Person(idPerson, lastName, firstName, addressPerson, email);

				hashMapPerson.put(idPerson, person);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return hashMapPerson;

	}

	/**
	 * This method is used to input the data from {@link Store}.csv as a HashMap
	 * 
	 * @return <code> HashMap<<code>String,Store> </code>
	 */
	public static HashMap<String, Store> storeData() {

		// Call out the method to create a HashMap for Person
		HashMap<String, Person> person = personData();

		// Using Scan method to input the csv data as the Hashmap
		HashMap<String, Store> hashMapStore = new HashMap<>();
		File f = new File("data/Stores.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				String idStore = tokens[0];
				Person managerInfo = person.get(tokens[1]);
				String street = tokens[2];
				String city = tokens[3];
				String state = tokens[4];
				String zipCode = tokens[5];
				String country = tokens[6];
				Address addressStore = new Address(street, city, state, zipCode, country);
				Store store = new Store(idStore, managerInfo, addressStore);

				hashMapStore.put(idStore, store);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return hashMapStore;
	}

	/**
	 * This method is used to input the data from {@link Item}.csv as a HashMap
	 * 
	 * @return <code>HashMap<<code>String,Item> </code>
	 */
	public static HashMap<String, Item> itemData() {

		HashMap<String, Item> hashMapItem = new HashMap<>();

		File f = new File("data/Items.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				Item item = null;
				String idItem = tokens[0];
				String typeItem = tokens[1];
				String nameItem = tokens[2];
				
				if (typeItem.equals("E")) {
					String model = tokens[3];
					item = new Equipment(idItem, nameItem, model);

				}
				if (typeItem.equals("P")) {
					String unit = tokens[3];
					double unitPrice = Double.parseDouble(tokens[4]);
					item = new Product(idItem, nameItem, unit, unitPrice);

				}
				if (typeItem.equals("S")) {
					double hourlyRate = Double.parseDouble(tokens[3]);
					item = new Service(idItem, nameItem, hourlyRate);
				}

				hashMapItem.put(idItem, item);
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return hashMapItem;
	}

	/**
	 * This method is used to input the data from {@link Invoice}.csv as a HashMap
	 * 
	 * @param storeData
	 * @return <code>HashMap<<code>String, Invoice></code>
	 */
	public static HashMap<String, Invoice> invoiceData(HashMap<String, Store> storeData) {

		// Call out the method to create a HashMap for Customer, and Salesperson
		HashMap<String, Person> customer = personData();
		HashMap<String, Person> salesPerson = personData();

		// Using Scan method to input the csv data as the Hashmap
		HashMap<String, Invoice> hashMapInvoice = new HashMap<>();

		File f = new File("data/Invoices.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				String idInvoice = tokens[0];
				Store storeInfo = storeData.get(tokens[1]);
				Person customerInfo = customer.get(tokens[2]);
				Person salesPersonInfo = salesPerson.get(tokens[3]);
				LocalDate invoiceDate = LocalDate.parse(tokens[4]);

				Invoice invoice = new Invoice(idInvoice, storeInfo, customerInfo, salesPersonInfo, invoiceDate);
				storeInfo.addInvoice(invoice);
				hashMapInvoice.put(idInvoice, invoice);
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return hashMapInvoice;

	}

	/**
	 * This method is used to input the data from {@link InvoiceItem}.csv as a List
	 * 
	 * @param hashMapInvoice
	 * @return <code>List<<code>Item></code>
	 */
	public static List<Item> invoiceItemData(HashMap<String, Invoice> hashMapInvoice) {

		// Call out the method to create a HashMap for Invoice, and Item
		HashMap<String, Item> item = itemData();

		// Using Scan method to input the InvoiceItem.csv data as the List
		List<Item> listInvItem = new ArrayList<>();

		File f = new File("data/InvoiceItems.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				Item invItem = null;
				Invoice invoiceInfo = hashMapInvoice.get(tokens[0]);
				Item itemInfo = item.get(tokens[1]);

				if (itemInfo instanceof Equipment) {
					String agreement = tokens[2];
					Double price = Double.parseDouble(tokens[3]);
					if (agreement.equals("P")) {
						invItem = new EquipmentPurchase((Equipment)itemInfo, price);

					} else if (agreement.equals("L")) {
						LocalDate beginLease = LocalDate.parse(tokens[4]);
						LocalDate endLease = LocalDate.parse(tokens[5]);
						invItem = new EquipmentLease((Equipment)itemInfo, price, beginLease,
								endLease);

					}
				} else if (itemInfo instanceof Product) {
					Double quantity = Double.parseDouble(tokens[2]);
					invItem = new Product(((Product)itemInfo), quantity);

				} else if (itemInfo instanceof Service) {
					Double amountOfService = Double.parseDouble(tokens[2]);
					invItem = new Service(((Service) itemInfo), amountOfService);

				}
				invoiceInfo.addItem(invItem);
				listInvItem.add(invItem);
			}

			sc.close();
		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		}
		return listInvItem;
	}

}
