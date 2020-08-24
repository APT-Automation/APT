package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DataReader_PK;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.scripthelpers.APT_LoginHelper;

public class APT_SANManagementTest extends DriverTestcase{

	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_SANManagement", priority=1)
	 public void verifySearchforSAN(Map<String, String> map) throws Exception {
		
		setup();	
		Login.APT_Login_1(map.get("url"));
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifySearchforSAN");
		APT_SANMgmtHelper.get().verifySearchSAN("sanmgmt", map.get("Search_SANNumber"), map.get("SearchSANfilename"), map.get("Browserfiles_Downloadspath"));
		
	
	}
}