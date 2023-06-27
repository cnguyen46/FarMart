package com.fmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contain many methods to load data from database based on primary
 * key, that uses to support for {@link DatabaseLoader}.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class DatabaseLoaderUtils {

	/**
	 * This method is used to load a list of email based on the personId from
	 * database.
	 * 
	 * @param personId
	 * @return <code>List<<code>String>
	 */
	public static List<String> getEmailById(int personId) {

		List<String> emailList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = """
					SELECT userName FROM Email WHERE personID = ?;
					""";
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String email = rs.getString("userName");
				emailList.add(email);
			}

		} catch (SQLException e) {
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
		return emailList;
	}

	/**
	 * This method is used to the data of each address by their ID from database.
	 * 
	 * @param addressId
	 * @return Address
	 */
	public static Address getAddressById(int addressId) {

		Address address = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = """
					SELECT a.street, a.city, a.zipCode,
					   	   c.countryName, s.stateName, a.addressId FROM Address a
					   	   JOIN Country c ON c.countryId = a.countryId
					   	   JOIN State s ON s.stateId = a.stateId
					   	   WHERE a.addressId = ?;
					""";
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String street = rs.getString("a.street");
				String city = rs.getString("a.city");
				String state = rs.getString("s.stateName");
				String zipCode = rs.getString("a.zipCode");
				String country = rs.getString("c.countryName");
				address = new Address(street, city, state, zipCode, country);
			}

		} catch (SQLException e) {
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
		return address;
	}

	/**
	 * This method is used to load the data of each person by their ID from
	 * database.
	 * 
	 * @param personId
	 * @return Person
	 */
	public static Person getPersonById(int personId) {

		Person persons = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = """
					SELECT p.personCode, p.firstName, p.lastName, p.addressId, p.personId FROM Person p
						   JOIN Address a ON a.addressId = p.addressId
					   	   WHERE p.personId = ?;
					""";
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String personCode = rs.getString("p.personCode");
				String firstName = rs.getString("p.firstName");
				String lastName = rs.getString("p.lastName");
				Address addressPerson = getAddressById(rs.getInt("p.addressId"));
				List<String> email = getEmailById(rs.getInt("p.personId"));

				persons = new Person(personCode, firstName, lastName, addressPerson, email);
			}

		} catch (SQLException e) {
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
		return persons;
	}

	/**
	 * This method is used to load the data of each item by itemId from database.
	 * 
	 * @param itemId
	 * @return Item
	 */
	public static Item getItemById(int itemId) {

		Item items = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = """
					SELECT itemCode, nameItem,typeItem, model, unit, unitPrice, hourlyRate
					   	   FROM Item WHERE itemId = ?;
					""";
			ps = conn.prepareStatement(query);
			ps.setInt(1, itemId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String itemCode = rs.getString("itemCode");
				String nameItem = rs.getString("nameItem");
				String typeItem = rs.getString("typeItem");
				if (typeItem.equals("E")) {
					String model = rs.getString("model");
					items = new Equipment(itemCode, nameItem, model);
				} else if (typeItem.equals("P")) {
					String unit = rs.getString("unit");
					Double unitPrice = rs.getDouble("unitPrice");
					items = new Product(itemCode, nameItem, unit, unitPrice);
				} else if (typeItem.equals("S")) {
					Double hourlyRate = rs.getDouble("hourlyRate");
					items = new Service(itemCode, nameItem, hourlyRate);
				}
			}

		} catch (SQLException e) {
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
		return items;
	}

}
