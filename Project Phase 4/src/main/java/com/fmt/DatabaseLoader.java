package com.fmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to load the data from database.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class DatabaseLoader {

	private static final Logger LOGGER = LogManager.getLogger(DatabaseLoader.class);

	/**
	 * This method load the database as a HashMap for store.
	 * 
	 * @return <code>HashMap<<code>String,Store><code>
	 */
	public static HashMap<Integer, Store> getstoreData() {
		HashMap<Integer, Store> stores = new HashMap<>();

		LOGGER.info("Loading data of every stores...");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

		} catch (SQLException e) {
			LOGGER.error("Wrong password, or user name, or URL");
			throw new RuntimeException(e);
		}

		String query = """
				SELECT s.storeId ,s.storeCode, s.managerId, s.addressId FROM Store s;
				""";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Integer storeId = rs.getInt("s.storeId");
				String storeCode = rs.getString("s.storeCode");
				Person manager = DatabaseLoaderById.getPersonById(rs.getInt("s.managerId"));
				Address addressStore = DatabaseLoaderById.getAddressById(rs.getInt("s.addressId"));
				Store s = new Store(storeCode, manager, addressStore);
				stores.put(storeId, s);
			}

		} catch (SQLException e) {
			LOGGER.error("Something wrong, check the database");
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		LOGGER.info("Done loading data of every stores...");

		return stores;
	}

	/**
	 * This method load the database as a HashMap for invoice
	 * 
	 * @param <code>HashMap<<code>Integer, Store><code>
	 * @return <code>HashMap<<code>String,Invoice><code>
	 */
	public static HashMap<Integer, Invoice> getInvoiceData(HashMap<Integer, Store> hashMapStore) {
		HashMap<Integer, Invoice> hashMapInvoice = new HashMap<>();
		Invoice invoice = null;
		LOGGER.info("Loading data of every invoices...");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			LOGGER.error("Wrong password, or user name, ot URL");
			throw new RuntimeException(e);
		}
		String query = """
				SELECT invoiceId, invoiceCode, storeId, customerId, salesPersonId, invoiceDate FROM Invoice i;
				""";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Integer invoiceId = rs.getInt("invoiceId");
				String invoiceCode = rs.getString("invoiceCode");
				Integer storeId = rs.getInt("storeId");
				Store store = hashMapStore.get(storeId);
				Person customer = DatabaseLoaderById.getPersonById(rs.getInt("customerId"));
				Person salesPerson = DatabaseLoaderById.getPersonById(rs.getInt("salesPersonId"));
				LocalDate invoiceDate = LocalDate.parse(rs.getString("invoiceDate"));
				invoice = new Invoice(invoiceCode, store, customer, salesPerson, invoiceDate);
				store.addInvoice(invoice);
				hashMapInvoice.put(invoiceId, invoice);
			}

		} catch (SQLException e) {
			LOGGER.error("Something wrong, check the database");
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		LOGGER.info("Done loading data of every invoices...");

		return hashMapInvoice;

	}

	/**
	 * This method load the database as a list of item matching with invoice.
	 * 
	 * @param <code>HashMap<<code>Integer, Invoice><code>
	 * @return <code>List<<code>Item><code>
	 */
	public static List<Item> getItemData(HashMap<Integer, Invoice> hashMapInvoice) {
		List<Item> listofItem = new ArrayList<>();
		Item item = null;
		LOGGER.info("Loading data of every used items in every invoices...");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			LOGGER.error("Wrong password, or user name, or URL");
			throw new RuntimeException(e);
		}
		String query = """
				SELECT a.itemId, b.invoiceId, b.typeEquipment,
					   b.price, b.beginLease, b.endLease, b.quantity, b.amountOfHour FROM Item a
					   JOIN InvoiceItem b ON b.itemId = a.itemId;
				""";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				item = DatabaseLoaderById.getItemById(rs.getInt("a.itemId"));
				Integer invoiceId = rs.getInt("b.invoiceId");
				Invoice invoice = hashMapInvoice.get(invoiceId);
				if (item instanceof Equipment) {
					String typeEquipment = rs.getString("b.typeEquipment");
					Double price = rs.getDouble("b.price");
					if (typeEquipment.equals("P")) {
						item = new EquipmentPurchase((Equipment) item, price);
					} else if (typeEquipment.equals("L")) {
						LocalDate beginLease = LocalDate.parse(rs.getString("b.beginLease"));
						LocalDate endLease = LocalDate.parse(rs.getString("b.endLease"));
						item = new EquipmentLease((Equipment) item, price, beginLease, endLease);
					}
				} else if (item instanceof Product) {
					Double quantity = rs.getDouble("b.quantity");
					item = new Product(((Product) item), quantity);
				} else if (item instanceof Service) {
					Double amountOfHour = rs.getDouble("b.amountOfHour");
					item = new Service(((Service) item), amountOfHour);
				}
				listofItem.add(item);
				invoice.addItem(item);
			}

		} catch (SQLException e) {
			LOGGER.error("Something wrong, please check the database");
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		LOGGER.info("Done loading data of every used items in every invoices...");
		return listofItem;
	}

}
