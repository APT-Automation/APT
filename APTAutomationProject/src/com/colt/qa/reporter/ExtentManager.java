package com.colt.qa.reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
//	 private static ExtentReports extent;
	private static ExtentReports extent;

//	  public synchronized static ExtentReports getReporter(){
	
	 public synchronized static ExtentReports getReporter() {
		 String dateName1 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	      if(extent == null){
	          //Set HTML reporting file location
//	          String workingDir = System.getProperty("user.dir");
//	          extent = new ExtentReports(workingDir+"\\ExtentReports\\ExtentReportResults2.html", true);
//	          System.out.println(workingDir+"\\ExtentReports\\ExtentReportResults2.html");
	    	  
	    	  extent = new ExtentReports(
	  				System.getProperty("user.dir") + "/ExtentReports/" + "PK_ExtentReport-" + dateName1 + ".html", true);
	  		extent.addSystemInfo("Host Name", "APT_QA_Colt").addSystemInfo("Environment", "QA").addSystemInfo("User Name",
	  				"COLT");
	      }
	      return extent;
	  }
	

		

}
