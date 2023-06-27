package com.fmt;

/**
 * This class is used to create and print out reports.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class InvoiceReport {

	/**
	 * This is the main program used to call all methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Call out the method to print out three sales reports, using data from Database.
		MainMethods.salesReportFromDBD();
	}
}
