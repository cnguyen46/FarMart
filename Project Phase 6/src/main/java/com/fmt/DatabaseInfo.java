package com.fmt;

/**
 * This class contains the logging information to connect to the database.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 */
public class DatabaseInfo {

	/**
	 * Connection parameters that are necessary for CSE's configuration
	 */
	public static final String PARAMETERS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String USERNAME = "cnguyen";
	public static final String PASSWORD = "mFPyUomjuv4";
	public static final String URL = "jdbc:mysql://cse.unl.edu/" + USERNAME + PARAMETERS;

}