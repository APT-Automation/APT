package com.saksoft.qa.driverlibrary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

public class Log {
	
	private static Logger Log = Logger.getLogger(Log.class.getName());

	 // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

	 public static void startTestCase(String sTestCaseName){

		Log.info("****************************************************************************************");

		Log.info("****************************************************************************************");

		Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

		Log.info("****************************************************************************************");

		Log.info("****************************************************************************************");

		}

		//This is to print log for the ending of the test case

	 public static void endTestCase(String sTestCaseName){

		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");

		Log.info("X");

		Log.info("X");

		Log.info("X");

		Log.info("X");

		}
		// Need to create these methods, so that they can be called  

	public static void info(String message) {

			Log.info(message);

			}

	public static void warn(String message) {

	 Log.warn(message);

		}

	public static void error(String message) {

	 Log.error(message);

		}

	public static void fatal(String message) {

	 Log.fatal(message);

		}

	public static void debug(String message) {

	 Log.debug(message);

		}
	
	public static void clearFile(String fileLocation){
	    try{
	        BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation));
	        bw.write("");
	        bw.flush();
	        bw.close();
	    }catch(IOException ioe){
	        // You should really do something more appropriate here
	        ioe.printStackTrace();
	    }
	}

	 }

	

