package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;

public class searchForDevice extends DriverTestcase {

APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_searchDevice", priority=0)
	public void searchForDevice(Map<String, String> map) throws Exception {
		
		setup();	
		
		Login.APT_Login_1(map.get("url for the Product"));
		
		logger = ExtentTestManager.startTest("search Device");
		APT_searchDevice.get().clickOnSearchForDevice("searchDevice");
		APT_searchDevice.get().searchDevice("searchDevice", map.get("deviceName"), map.get("ManagementAddress"), map.get("vendorModel"), map.get("deviceTypes"), 
				map.get("searchCity"), map.get("cityvaluestoBeSelected"), map.get("searchCO"), map.get("COvaluesToBeSelected"), map.get("routerId"));
		APT_searchDevice.get().selectValueUnderRecord("searchDevice");
		APT_searchDevice.get().fetchValueInViewDevicePage("searchDevice");
		ExtentTestManager.endTest();
	}
	
}
