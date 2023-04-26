package com.fmt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class contain many methods to support for {@link InvoiceData}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class InvoiceDataUtils {

	/**
	 * Method to add a state record to the database with the provided data.
	 *
	 * @param state
	 * 
	 * @return stateId
	 */
	public static int addState(String state) {

		int stateId = 0;

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;
		ResultSet keys = null;

		String query = "INSERT INTO State(stateName) VALUES (?);";
		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, state);
			ps.executeUpdate();

			keys = ps.getGeneratedKeys();
			keys.next();
			stateId = keys.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(keys, ps, conn);
		return stateId;
	}

	/**
	 * Method to add a country record to the database with the provided data.
	 *
	 * @param country
	 * 
	 * @return countryId
	 */
	public static int addCountry(String country) {

		int countryId = 0;

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;
		ResultSet keys = null;

		String query = "INSERT INTO Country(countryName) VALUES (?);";
		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, country);
			ps.executeUpdate();

			keys = ps.getGeneratedKeys();
			keys.next();
			countryId = keys.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(keys, ps, conn);
		return countryId;
	}

	/**
	 * Method to add an address record to the database with the provided data.
	 *
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 * 
	 * @return addressId
	 */
	public static int addAddress(String street, String city, String state, String zip, String country) {

		int stateId = 0;
		int countryId = 0;
		int addressId = 0;

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;
		ResultSet keys = null;

		String query = "SELECT stateId from State WHERE stateName = ?;";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, state);
			keys = ps.executeQuery();
			while (keys.next()) {
				stateId = keys.getInt("stateId");
			}

			// Check whether there exists the state in the table State
			// If not, add the record in table State
			if (stateId == 0) {
				stateId = addState(state);
			}

			query = "SELECT countryId from Country WHERE countryName = ?;";
			ps = conn.prepareStatement(query);
			ps.setString(1, country);
			keys = ps.executeQuery();
			while (keys.next()) {
				countryId = keys.getInt("countryId");
			}

			// Check whether there exists the country in the table Country
			// If not, add the record in table Country
			if (countryId == 0) {
				countryId = addCountry(country);
			}

			query = "INSERT INTO Address(street, city, zipCode, stateId, countryId) VALUES(?,?,?,?,?);";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, zip);
			ps.setInt(4, stateId);
			ps.setInt(5, countryId);
			ps.executeUpdate();

			keys = ps.getGeneratedKeys();
			keys.next();
			addressId = keys.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(keys, ps, conn);
		return addressId;
	}

	/**
	 * Method to check, and get the addressId in table Address.
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 * @return addressId
	 */
	public static int getAddressId(String street, String city, String state, String zip, String country) {

		int addressId = 0;

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = """
				SELECT a.addressId from Address a
					   JOIN State s ON s.stateId = a.stateId
					   JOIN Country c ON c.countryId = a.countryId
					   WHERE a.street = ?
					   AND a.city = ?
					   AND s.stateName = ?
					   AND a.zipCode = ?
					   AND c.countryName = ?;
				""";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			rs = ps.executeQuery();
			while (rs.next()) {
				addressId = rs.getInt("a.addressId");
			}

			// Check whether there exists the address in the table Address
			// If not, add the record in table Address
			if (addressId == 0) {
				addressId = addAddress(street, city, state, zip, country);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(rs, ps, conn);
		return addressId;
	}

	/**
	 * Method to get the personId in table Person based on personCode.
	 * 
	 * @param personCode
	 * 
	 * @return personId
	 */
	public static int getPersonId(String personCode) {

		int personId = 0;

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = """
				SELECT personId, personCode FROM Person WHERE personCode = ?;
				""";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				personId = rs.getInt("personId");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(rs, ps, conn);
		return personId;
	}
	
	/**
	 * Method to get the storeId in table Store based on storeCode.
	 * 
	 * @param storeCode
	 * 
	 * @return storeId
	 */
	public static int getStoreId(String storeCode) {

		int storeId = 0;

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = """
				SELECT storeId, storeCode FROM Store WHERE storeCode = ?;
				""";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, storeCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				storeId = rs.getInt("storeId");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(rs, ps, conn);
		return storeId;
	}
	
	/**
	 * Method to get the ItemId in table Item based on itemCode.
	 * 
	 * @param itemCode
	 * 
	 * @return itemId
	 */
	public static int getItemId(String itemCode) {

		int itemId = 0;

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = """
				SELECT itemId, itemCode FROM Item WHERE itemCode = ?;
				""";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, itemCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				itemId = rs.getInt("itemId");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(rs, ps, conn);
		return itemId;
	}
	
	/**
	 * Method to get the ItemId in table Item based on itemCode.
	 * 
	 * @param invoiceCode
	 * 
	 * @return invoiceId
	 */
	public static int getInvoiceId(String invoiceCode) {

		int invoiceId = 0;

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = """
				SELECT invoiceId, invoiceCode FROM Invoice WHERE invoiceCode = ?;
				""";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				invoiceId = rs.getInt("invoiceId");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		DatabaseConnection.getClose(rs, ps, conn);
		return invoiceId;
	}
	
}
