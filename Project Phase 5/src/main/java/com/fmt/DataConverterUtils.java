package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class contain many methods that uses to convert data.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class DataConverterUtils {

	/**
	 * This method is used to convert the <code>HashMap<<code>?, T><code> into the
	 * <code>List<<code>T><code>
	 * 
	 * @param <T>
	 * @param hashMapData<?, T>
	 * @return List<T>
	 */
	public static <T> List<T> convertListToHashMap(HashMap<?, T> hashMapData) {

		List<T> listData = new ArrayList<>();
		for (Object key : hashMapData.keySet()) {
			listData.add(hashMapData.get(key));
		}
		return listData;
	}

	/**
	 * This method is used to convert the data into the JSON file for
	 * {@link MainMethods#DataConverter()}
	 * 
	 * @param <T>
	 * @param listData
	 * @param fileName
	 */
	public static <T> void convertToJson(List<T> listData, String fileName) {

		GsonBuilder builer = new GsonBuilder().setExclusionStrategies(new GsonExcludeField());
		builer.setPrettyPrinting();
		Gson gson = builer.create();
		String dataJson = gson.toJson(listData);
		File output = new File(fileName);

		if (listData.get(0) instanceof Person && fileName.equals("data/Persons.json")) {
			try {
				PrintWriter pw = new PrintWriter(output);
				pw.println("{");
				pw.println("\"persons\": [");
				pw.println(dataJson);
				pw.println("]}");

				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else if (listData.get(0) instanceof Store && fileName.equals("data/Stores.json")) {
			try {
				PrintWriter pw = new PrintWriter(output);
				pw.println("{");
				pw.println("\"stores\": [");
				pw.println(dataJson);
				pw.println("]}");

				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else if (listData.get(0) instanceof Item && fileName.equals("data/Items.json")) {
			try {
				PrintWriter pw = new PrintWriter(output);
				pw.println("{");
				pw.println("\"items\": [");
				pw.println(dataJson);
				pw.println("]}");

				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			throw new IllegalArgumentException(
					"You output the file to the wrong folder, or use wrong name for data file");
		}
	}

	/**
	 * This method is used to convert the data into the XML file
	 * {@link MainMethods#DataConverter()}
	 * 
	 * @param <T>
	 * @param listData
	 * @param fileName
	 * @param className
	 */
	public static <T> void convertToXml(List<T> listData, String fileName, String className) {

		XStream xstream = new XStream(new DomDriver());
		xstream.omitField(Store.class, "invoiceInfo");
		String dataXml = xstream.toXML(listData).replaceAll("list", className);

		if (listData.get(0) instanceof Person && fileName.equals("data/Persons.xml")) {
			xstream.aliasType("person", Person.class);
			xstream.aliasField("address", Person.class, "addressPerson");
			dataXml.replaceAll("list", "persons");

			File output = new File(fileName);
			try {
				PrintWriter pw = new PrintWriter(output);
				pw.println("<?xml version=\"1.0\"?>");
				pw.println(dataXml);

				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else if (listData.get(0) instanceof Store && fileName.equals("data/Stores.xml")) {
			xstream.aliasType("store", Store.class);
			xstream.aliasField("address", Person.class, "addressPerson");
			xstream.aliasField("address", Store.class, "addressStore");
			xstream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);

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

		else if (listData.get(0) instanceof Item && fileName.equals("data/Items.xml")) {
			xstream.aliasType("equipment", Equipment.class);
			xstream.aliasType("products", Product.class);
			xstream.aliasType("services", Service.class);

			File output = new File("data/Items.xml");
			try {
				PrintWriter pw = new PrintWriter(output);
				pw.println("<?xml version=\"1.0\"?>");
				pw.println(dataXml);

				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(
					"You output the file to the wrong folder, or use wrong name for data file");
		}
	}

}
