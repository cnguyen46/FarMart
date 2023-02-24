package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
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
public class DataLoading {

	/**
	 * 
	 * This method is used to print out line by line for csv files
	 * 
	 * @param fileName
	 * @param fileLocation
	 */

	public static void loadDataReport(String fileName, String fileLocation) {

		System.out.println(fileName);
		File f = new File(fileLocation);
		Scanner s = null;
		try {
			s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				System.out.println(line);
			}

			s.close(); // Closing a scanner
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		System.out.println("");
	}

	/**
	 * This method is used to input the data from {@link Person}.csv as a HashMap
	 *
	 * @return HashMap<String,Person>
	 */
	public static HashMap<String, Person> hashMapPerson() {

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
	 * This method is used to convert a HashMap into a List for {@link Person} data
	 * 
	 * @return List<Person>
	 *
	 */
	public static List<Person> listPersonData() {

		// Call out the method to create a HashMap, then convert it to a List
		HashMap<String, Person> hashMapPerson = hashMapPerson();
		List<Person> listPerson = new ArrayList<>();
		for (String s : hashMapPerson.keySet()) {
			listPerson.add(hashMapPerson.get(s));
		}
		return listPerson;
	}

	/**
	 * This method is used to input the data from {@link Store}.csv as a List
	 * 
	 * @return List<Store>
	 */
	public static List<Store> listStoreData() {

		// Call out the method to create a HashMap for Person
		HashMap<String, Person> hashMapPerson = hashMapPerson();

		// Using Scan method to input the csv data as the List
		List<Store> listStore = new ArrayList<>();
		File f = new File("data/Stores.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				String idStore = tokens[0];
				Person personManager = hashMapPerson.get(tokens[1]);
				String street = tokens[2];
				String city = tokens[3];
				String state = tokens[4];
				String zipCode = tokens[5];
				String country = tokens[6];
				Address addressStore = new Address(street, city, state, zipCode, country);
				Store store = new Store(idStore, personManager, addressStore);

				listStore.add(store);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listStore;
	}

	/**
	 * This method is used to input the data from {@link Item}.csv as a List
	 * 
	 * @return List<Item>
	 */
	public static List<Item> listItemData() {

		// Using Scan method to input the csv data as the List
		List<Item> listItem = new ArrayList<>();

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

				listItem.add(item);
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listItem;
	}

}
