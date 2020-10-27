package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.reporter.ExtentTestManager;

public class LANLINK_newTab extends DriverTestcase{

	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "DataReader_LANLINKNewTab", priority = 0)
	public void Verify_wholesale_newTabFunctoinality(Map<String, String> map) throws Exception {
		
		setup();	
		
		
		Login.APT_Login_1(map.get("url for the Product"));	
		
		logger= ExtentTestManager.startTest("Search Existing Service");
		lanlinkNewTab.get().searchOrderORservice("newTab", map.get("serviceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("Edit Service");
		lanlinkNewTab.get().editService("newTab", map.get("customerName"), map.get("serviceName"),
				map.get("edtSingleendPointCPE"), map.get("edit_Email"), map.get("edit_PhoneContact"), map.get("edit_Remark"), map.get("edit_performancereporting"), 
				map.get("edit_ProactiveMonitoring"), map.get("edit_deliveryChannel"), map.get("edit_managmentOrder"), map.get("edit_notficationManagementTeam"), 
				map.get("VPNtopology"), map.get("edit_intermediateTechnology"), map.get("edit_circuitReference"));
		ExtentTestManager.endTest();
		
		
	}	
}
