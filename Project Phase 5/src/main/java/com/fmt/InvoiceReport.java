package com.fmt;

/**
 * This class is used to create reports included:
 * <li> Summary Report - By Total
 * <li> Store Sales Summary Report
 * <li> Detail Report
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
		
		// Call out the method to print out three reports, but using data from Database.
		MainMethods.InvoiceReportFromDBD();
	}
}
