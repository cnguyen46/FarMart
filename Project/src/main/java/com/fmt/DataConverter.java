package com.fmt;

/**
 * 
 * This is the program used to report the information of people, stores, and
 * invoice items. Also, it is used to converted the csv file into json file.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 * @date February 17, 2023
 *
 */

public class DataConverter {

	/**
	 * This is the main program, that use to call for all the class, and method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Data and output files handed in:");
		System.out.println("-==-==-==-==-==-==-==-==-==-==-==-");

		// Call out the method to print out the csv file
		DataLoading.loadDataReport("Person.csv:", "data/Persons.csv");
		DataLoading.loadDataReport("Store.csv:", "data/Stores.csv");
		DataLoading.loadDataReport("Item.csv:", "data/Items.csv");

		// Call out the method to convert the csv file into json file
		DataConverterJson.convertPersonData();
		DataConverterJson.convertStoreData();
		DataConverterJson.convertItemData();
	}
}
