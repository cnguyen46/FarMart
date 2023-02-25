package com.fmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * This is the JUnit testing program for various classes, that helps for main
 * class {@link #DataConverter}. Various tests include the created methods, and
 * see whether the expected results match with the output
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class DataTest {

	/**
	 * This is the testing program for {@link #Person}, to check that it has the
	 * appropriate constructor, getters, and toString method
	 */
	@Test
	public void personTest() {
		assertFalse(Modifier.isAbstract(Person.class.getModifiers()));
		assertEquals(6, Person.class.getDeclaredMethods().length, "Did you have enough getters and toString method?");

		List<String> email = Arrays.asList("adam123@husker.unl.edu", "willyadam999@gmail.com");
		Person actual = new Person("BAFN13175", "William", "Smith", null, email);
		Assertions.assertEquals("BAFN13175", actual.getIdPerson());
		Assertions.assertEquals("William", actual.getLastName());
		Assertions.assertEquals("Smith", actual.getFirstName());
		Assertions.assertEquals(email, actual.getEmail());

		String expected = "BAFN13175 William Smith" + " " + null + " "
				+ "[adam123@husker.unl.edu, willyadam999@gmail.com]";
		Assert.assertEquals(expected, actual.toString());
	}

	/**
	 * This is the testing program for {@link #Address}, to check that it has the
	 * appropriate constructor, getters, and toString method
	 */
	@Test
	public void addressTest() {
		assertFalse(Modifier.isAbstract(Address.class.getModifiers()));
		assertEquals(6, Address.class.getDeclaredMethods().length, "Did you have enough getters and toString method?");

		Address actual = new Address("123 HomeStay", "Lincoln", "NE", "68521-251", "US");
		Assertions.assertEquals("123 HomeStay", actual.getStreet());
		Assertions.assertEquals("Lincoln", actual.getCity());
		Assertions.assertEquals("NE", actual.getState());
		Assertions.assertEquals("68521-251", actual.getZipCode());
		Assertions.assertEquals("US", actual.getCountry());

		String expected = "123 HomeStay Lincoln NE 68521-251 US";
		Assert.assertEquals(expected, actual.toString());
	}

	/**
	 * This is the testing program for {@link #Store}, to check that it has the
	 * appropriate constructor, getters, and toString method
	 */
	@Test
	public void storeTest() {
		assertFalse(Modifier.isAbstract(Store.class.getModifiers()));
		assertEquals(4, Store.class.getDeclaredMethods().length, "Did you have enough getters and toString method?");

		List<String> email = Arrays.asList("adam123@husker.unl.edu", "willyadam999@gmail.com");
		Person managerID = new Person("BAFN13175", "William", "Smith", null, email);
		Store actual = new Store("FN2314", managerID, null);
		Assertions.assertEquals("FN2314", actual.getIdStore());
		Assertions.assertEquals(managerID, actual.getManagerCode());

		String expected = "FN2314 BAFN13175" + " " + null;
		Assert.assertEquals(expected, actual.toString());
	}

	/**
	 * This is the testing program for {@link #Item}, to check that it has the
	 * appropriate constructor, getters, and toString method
	 */
	@Test
	public void itemTest() {
		assertTrue(Modifier.isAbstract(Item.class.getModifiers()), "Item must be abstract");
		assertEquals(3, Item.class.getDeclaredMethods().length, "Did you have enough getters and toString method?");
	}

	/**
	 * This is the testing program for {@link #Equipment} to check that it has the
	 * appropriate constructor, getters, and toString method
	 */
	@Test
	public void equipmentTest() {
		assertFalse(Modifier.isAbstract(Equipment.class.getModifiers()));
		assertEquals(2, Equipment.class.getDeclaredMethods().length,
				"Did you have enough getters and toString method?");
		assertTrue(Item.class.isAssignableFrom(Equipment.class), "Equipment must extend Item");

		Equipment actual = new Equipment("AX174T", "Tractor", "174T");
		Assertions.assertEquals("AX174T", actual.getIdItem());
		Assertions.assertEquals("Tractor", actual.getNameItem());
		Assertions.assertEquals("174T", actual.getModel());

		String expected = "AX174T Tractor 174T";
		Assert.assertEquals(expected, actual.toString());
	}

	/**
	 * This is the testing program for {@link #Product} to check that it has the
	 * appropriate constructor, getters, and toString method
	 */
	@Test
	public void productTest() {
		assertFalse(Modifier.isAbstract(Product.class.getModifiers()));
		assertEquals(3, Product.class.getDeclaredMethods().length, "Did you have enough getters and toString method?");
		assertTrue(Item.class.isAssignableFrom(Product.class), "Product must extend Item");

		Product actual = new Product("PA1075", "Potatoes", "kg", 5);
		Assertions.assertEquals("PA1075", actual.getIdItem());
		Assertions.assertEquals("Potatoes", actual.getNameItem());
		Assertions.assertEquals("kg", actual.getUnit());
		Assertions.assertEquals(5, actual.getUnitPrice());

		String expected = "PA1075 Potatoes kg 5.0";
		Assert.assertEquals(expected, actual.toString());
	}

	/**
	 * This is the testing program for {@link #Service} to check that it has the
	 * appropriate constructor, getters, and toString method
	 */
	@Test
	public void serviceTest() {
		assertFalse(Modifier.isAbstract(Service.class.getModifiers()));
		assertEquals(2, Service.class.getDeclaredMethods().length, "Did you have enough getters and toString method?");
		assertTrue(Item.class.isAssignableFrom(Service.class), "Service must extend Item");

		Service actual = new Service("AR156", "Audit report", 20);
		Assertions.assertEquals("AR156", actual.getIdItem());
		Assertions.assertEquals("Audit report", actual.getNameItem());
		Assertions.assertEquals(20, actual.getHourlyRate());

		String expected = "AR156 Audit report 20.0";
		Assert.assertEquals(expected, actual.toString());
	}

}
