package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.reporter.ExtentTestManager;

public class voipAccess_newTab extends DriverTestcase{

	
APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "DataReader_voipAccessNewTab", priority = 0)
	public void Verify_wholesale_newTabFunctoinality(Map<String, String> map) throws Exception {
		
		setup();	
		
		Login.APT_Login_1(map.get("url for the Product"));	
		

		logger= ExtentTestManager.startTest("Search Existing Service");
		voipAccessnewtab.get().searchOrderORservice("newTab",  map.get("serviceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("Add MAS switch");
		voipAccessnewtab.get().verifyAddMASswitch("newTab", map.get("IMSpopLocation"), map.get("customerName"), map.get("serviceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("EditMASswitch and close child tabs");
		voipAccessnewtab.get().editMASdevice("newTab", map.get("customerName"), map.get("serviceName"), map.get("MAS_editName"), map.get("MAS_editVendor"), map.get("MAS_manageAddress"),
				map.get("MAS_Snmpro"));
		
		voipAccessnewtab.get().closeChildtab("newTab");
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("deleteMASdevice_wholesaleSIP");
		APT_Helper.get().clickOnBreadCrump("wholesaleService", map.get("serviceName"));
		APT_Helper.get().MASswitch__DeleteFromServiceFunctionality("wholesaleService", map.get("MAS_deviceName"));
		APT_Helper.get().MASswitch__DeleteFromServiceFunctionality("wholesaleService", map.get("MAS_devicename2"));
		ExtentTestManager.endTest(); 
	
	}	
	
	
}
