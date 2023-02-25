package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class is used to convert the data from csv file into xml file for
 * {@link DataConverter}
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class DataConverterXml {

	/**
	 * This method is used to create a Person.xml.
	 */
	public static void personXml() {

		// Call out the method to create a List for Person
		List<Person> listPerson = DataLoading.listPersonData();

		// Using XStream to convert the List into xml file
		XStream xstream = new XStream(new DomDriver());
		xstream.aliasType("person", Person.class);
		xstream.aliasField("address", Person.class, "addressPerson");
		String dataXml = xstream.toXML(listPerson).replaceAll("list", "persons");

		// Output the xml file
		File output = new File("data/Persons.xml");
		try {
			PrintWriter pw = new PrintWriter(output);
			pw.println("<?xml version=\"1.0\"?>");
			pw.println(dataXml);

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to create a Store.xml.
	 */
	public static void storeXml() {

		// Call out the method to create a List for Store
		List<Store> listStore = DataLoading.listStoreData();

		// Using XStream to convert the List into xml file
		XStream xstream = new XStream(new DomDriver());
		xstream.aliasType("store", Store.class);
		xstream.aliasField("address", Person.class, "addressPerson");
		xstream.aliasField("address", Store.class, "addressStore");
		xstream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
		String dataXml = xstream.toXML(listStore).replaceAll("list", "stores");;

		// Output the xml file
		File output = new File("data/Stores.xml");
		try {
			PrintWriter pw = new PrintWriter(output);
			pw.println("<?xml version=\"1.0\"?>");
			pw.println(dataXml);

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to create a Item.xml.
	 */
	public static void itemXml() {

		// Call out the method to create a List for Item
		List<Item> listItem = DataLoading.listItemData();

		// Using XStream to convert the List into xml file
		XStream xstream = new XStream(new DomDriver());
		xstream.aliasType("equpment", Equipment.class);
		xstream.aliasType("products", Product.class);
		xstream.aliasType("services", Service.class);
		String dataXml = xstream.toXML(listItem).replaceAll("list", "items");

		// Output the xml file
		File output = new File("data/Items.xml");
		try {
			PrintWriter pw = new PrintWriter(output);
			pw.println("<?xml version=\"1.0\"?>");
			pw.println(dataXml);

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
