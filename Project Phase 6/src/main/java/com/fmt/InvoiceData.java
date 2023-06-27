package com.fmt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class InvoiceData {

	/**
	 * Removes all records from all tables in the database. The removing order must
	 * be followed as below:
	 * <p>
	 * InvoiceItem -> Item -> Invoice -> Store -> Email -> Person -> Address ->
	 * State -> Country
	 */
	public static void clearDatabase() {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		// Delete every InvoiceItem's records
		String query = "DELETE FROM InvoiceItem;";
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();

			// Delete every Item's records
			query = "DELETE FROM Item;";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			
			// Delete every Invoice's records
			query = "DELETE FROM Invoice;";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();

			// Delete every Store's records
			query = "DELETE FROM Store;";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();

			// Delete every Email's records
			query = "DELETE FROM Email;";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();

			// Delete every Person's records
			query = "DELETE FROM Person;";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();

			// Delete every Address's records
			query = "DELETE FROM Address;";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();

			// Delete every State's records
			query = "DELETE FROM State;";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();

			// Delete every Country's records
			query = "DELETE FROM Country;";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Method to add a person record to the database with the provided data.
	 *
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		int addressId = InvoiceDataUtils.getAddressId(street, city, state, zip, country);

		String query = "INSERT INTO Person(personCode, firstName, lastName, addressId) VALUES (?,?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt(4, addressId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 *
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		int personId = InvoiceDataUtils.getPersonId(personCode);

		String query = "INSERT INTO Email(personId,userName) VALUES (?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			ps.setString(2, email);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds a store record to the database managed by the person identified by the
	 * given code.
	 *
	 * @param storeCode
	 * @param managerCode
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addStore(String storeCode, String managerCode, String street, String city, String state,
			String zip, String country) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		int managerId = InvoiceDataUtils.getPersonId(managerCode);
		int addressId = InvoiceDataUtils.getAddressId(street, city, state, zip, country);

		String query = "INSERT INTO Store (storeCode, managerId, addressId) VALUES (?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, storeCode);
			ps.setInt(2, managerId);
			ps.setInt(3, addressId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds a product record to the database with the given <code>code</code>,
	 * <code>name</code> and <code>unit</code> and <code>pricePerUnit</code>.
	 *
	 * @param itemCode
	 * @param name
	 * @param unit
	 * @param pricePerUnit
	 */
	public static void addProduct(String code, String name, String unit, double pricePerUnit) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		String query = "INSERT INTO Item (itemCode, nameItem, typeItem, unit, unitPrice) VALUES (?,?,?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setString(3, "P");
			ps.setString(4, unit);
			ps.setDouble(5, pricePerUnit);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds an equipment record to the database with the given <code>code</code>,
	 * <code>name</code> and <code>modelNumber</code>.
	 *
	 * @param itemCode
	 * @param name
	 * @param modelNumber
	 */
	public static void addEquipment(String code, String name, String modelNumber) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		String query = "INSERT INTO Item (itemCode, nameItem, typeItem, model) VALUES (?,?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setString(3, "E");
			ps.setString(4, modelNumber);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds a service record to the database with the given <code>code</code>,
	 * <code>name</code> and <code>costPerHour</code>.
	 *
	 * @param itemCode
	 * @param name
	 * @param modelNumber
	 */
	public static void addService(String code, String name, double costPerHour) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		String query = "INSERT INTO Item (itemCode, nameItem, typeItem, hourlyRate) VALUES (?,?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setString(3, "S");
			ps.setDouble(4, costPerHour);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds an invoice record to the database with the given data.
	 *
	 * @param invoiceCode
	 * @param storeCode
	 * @param customerCode
	 * @param salesPersonCode
	 * @param invoiceDate
	 */
	public static void addInvoice(String invoiceCode, String storeCode, String customerCode, String salesPersonCode,
			String invoiceDate) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		int storeId = InvoiceDataUtils.getStoreId(storeCode);
		int customerId = InvoiceDataUtils.getPersonId(customerCode);
		int salesPersonId = InvoiceDataUtils.getPersonId(salesPersonCode);

		String query = "INSERT INTO Invoice (invoiceCode, storeId, customerId, salesPersonId, invoiceDate) VALUES (?,?,?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ps.setInt(2, storeId);
			ps.setInt(3, customerId);
			ps.setInt(4, salesPersonId);
			ps.setString(5, invoiceDate);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds a particular product (identified by <code>itemCode</code>) to a
	 * particular invoice (identified by <code>invoiceCode</code>) with the
	 * specified quantity.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param quantity
	 */
	public static void addProductToInvoice(String invoiceCode, String itemCode, int quantity) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		int invoiceId = InvoiceDataUtils.getInvoiceId(invoiceCode);
		int itemId = InvoiceDataUtils.getItemId(itemCode);

		String query = "INSERT INTO InvoiceItem (invoiceId, itemId, quantity) VALUES (?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			ps.setInt(2, itemId);
			ps.setInt(3, quantity);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds a particular equipment <i>purchase</i> (identified by
	 * <code>itemCode</code>) to a particular invoice (identified by
	 * <code>invoiceCode</code>) at the given <code>purchasePrice</code>.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param purchasePrice
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String itemCode, double purchasePrice) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		int invoiceId = InvoiceDataUtils.getInvoiceId(invoiceCode);
		int itemId = InvoiceDataUtils.getItemId(itemCode);

		String query = "INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price) VALUES (?,?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			ps.setInt(2, itemId);
			ps.setString(3, "P");
			ps.setDouble(4, purchasePrice);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds a particular equipment <i>lease</i> (identified by
	 * <code>itemCode</code>) to a particular invoice (identified by
	 * <code>invoiceCode</code>) with the given 30-day <code>periodFee</code> and
	 * <code>beginDate/endDate</code>.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param amount
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String itemCode, double periodFee, String beginDate,
			String endDate) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		int invoiceId = InvoiceDataUtils.getInvoiceId(invoiceCode);
		int itemId = InvoiceDataUtils.getItemId(itemCode);

		String query = "INSERT INTO InvoiceItem (invoiceId, itemId, typeEquipment, price, beginLease, endLease) VALUES (?,?,?,?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			ps.setInt(2, itemId);
			ps.setString(3, "L");
			ps.setDouble(4, periodFee);
			ps.setString(5, beginDate);
			ps.setString(6, endDate);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

	/**
	 * Adds a particular service (identified by <code>itemCode</code>) to a
	 * particular invoice (identified by <code>invoiceCode</code>) with the
	 * specified number of hours.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param billedHours
	 */
	public static void addServiceToInvoice(String invoiceCode, String itemCode, double billedHours) {

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;

		int invoiceId = InvoiceDataUtils.getInvoiceId(invoiceCode);
		int itemId = InvoiceDataUtils.getItemId(itemCode);

		String query = "INSERT INTO InvoiceItem (invoiceId, itemId, amountOfHour) VALUES (?,?,?);";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			ps.setInt(2, itemId);
			ps.setDouble(3, billedHours);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(null, ps, conn);
	}

}