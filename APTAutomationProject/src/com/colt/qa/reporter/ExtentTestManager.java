package com.colt.qa.reporter;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {
	    static Map extentTestMap = new HashMap();
	    static ExtentReports extent = ExtentManager.getReporter();

	    public static synchronized ExtentTest getTest() {
	        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	    }

	    public static synchronized void endTest(ExtentTest logger) {
	    	extent.flush();
	    	extent.endTest(logger);
	    }
	    
	    public static synchronized void endTest() {
	    	extent.flush();
	    	extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	    }

	    
	    	public static synchronized void endAllTest() {
	    		extent.flush();

	    		for(int i=0;i<extentTestMap.size();i++)
	    		{
	    		extent.endTest((ExtentTest) extentTestMap.get(i) );
	    		}
	    		}
	    
	    public static synchronized ExtentTest startTest(String testName, String desc) {
	        ExtentTest test = extent.startTest(testName, desc);
	        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
	        return test;
	    }
	    
	    public static synchronized ExtentTest startTest(String testName) {
	        ExtentTest test = extent.startTest(testName);
	        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
	        return test;
	    }
	}
	
