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
		
		Login.APT_Login_1(map.get("url"));	
		

		logger= ExtentTestManager.startTest("Search Existing Service");
		voipAccessnewtab.get().searchOrderORservice("newTab",  map.get("serviceName"));
		
		logger= ExtentTestManager.startTest("Add MAS switch");
		voipAccessnewtab.get().verifyAddMASswitch("newTab", map.get("IMSpopLocation"), map.get("customerName"), map.get("serviceName"));
		
		logger= ExtentTestManager.startTest("Edit MAS switch");
		voipAccessnewtab.get().editMASdevice("newTab", map.get("customerName"), map.get("serviceName"), map.get("MAS_editName"), map.get("MAS_editVendor"), map.get("MAS_manageAddress"),
				map.get("MAS_Snmpro"));
		
		voipAccessnewtab.get().closeChildtab("newTab");
	
	}	
	
	
}
