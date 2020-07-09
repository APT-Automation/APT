package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader;

public class APT_MCS_EditCustomer extends DriverTestcase{
  

		
		@Test(description = "TC-03",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_CreateCustomer", priority = 3)
		public void Verify_CreateCustomerFunctionality(Map<String, String> map) throws Exception {
			DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_CreateCustomerFunctionality");
			Thread.sleep(2000);
			createCustomerSeparateHelper.get().navigateToCreateCustomerPage("CreateCustomer");
			createCustomerSeparateHelper.get().createCustomerFunctionality("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"), map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
			createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully created.");
			createCustomerSeparateHelper.get().verifyCustomerdetails_InviewCustomerPage("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"),
					map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
			Thread.sleep(1000);
		}
		
		
		@Test(description = "TC-03",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_EditCustomer", priority = 4)
		public void Verify_EditCustomerFunctionality(Map<String, String> map) throws Exception {
			DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_EditCustomerFunctionality");
			
			createCustomerSeparateHelper.get().verifyEditCustomerFunction("CreateCustomer", map.get("Name"), map.get("MainDomain"), 
					map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"), map.get("TechnicalContactName"),
					map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
			createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully created.");
			createCustomerSeparateHelper.get().verifyCustomerdetails_InviewCustomerPage_edited("CreateCustomer", map.get("Name"), map.get("MainDomain"), 
					map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"), map.get("TechnicalContactName"),
					map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
			createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully updated.");
			
			createCustomerSeparateHelper.get().deleteCustomer("CreateCustomer");
			createCustomerSeparateHelper.get().verifyDeletedCustomer("Customer successfully deleted");

		}
		
		
		
	
	
	
	
	
	
}
