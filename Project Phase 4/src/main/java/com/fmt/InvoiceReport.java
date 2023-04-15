package com.fmt;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

/**
 * This class is used to create reports having data loading from database.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class InvoiceReport {

	/**
	 * This is the main program used to call all methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);

		// Call out the method to print out three reports, but using data from Database.
		MainMethods.InvoiceReportFromDBD();
		
	}
}
