package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader;

public class APT_MCS_ViewCustomer extends DriverTestcase{
  

		
		@Test(description = "TC-02",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_ViewCustomer", priority = 2)
		public void Verify_ViewCustomerFunctionality(Map<String, String> map) throws Exception {
			DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_ViewCustomerFunctionality");
			Thread.sleep(2000);
			createCustomerSeparateHelper.get().navigateToCreateCustomerPage("CreateCustomer");
			createCustomerSeparateHelper.get().createCustomerFunctionality("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"), map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
			
			createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully created.");
			
			createCustomerSeparateHelper.get().verifyCustomerdetails_InviewCustomerPage("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"),
					map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
			Thread.sleep(1000);
			
			createCustomerSeparateHelper.get().deleteCustomer("CreateCustomer");
			createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer", "Customer successfully deleted" );
//			createCustomerSeparateHelper.get().verifyDeletedCustomer("Customer successfully deleted");
	
			Thread.sleep(1000);
		}
		
	
	
	
	
	
	
	
	
	
	
	
}
