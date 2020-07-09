package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader;

public class APT_MCS_DeleteCustomer extends DriverTestcase{
  

		
		@Test(description = "TC-04",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_DeleteCustomer", priority = 5)
		public void Verify_DeleteCustomerFunctionality(Map<String, String> map) throws Exception {
			DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_DeleteCustomerFunctionality");
			Thread.sleep(2000);
			createCustomerSeparateHelper.get().navigateToCreateCustomerPage("CreateCustomer");
			createCustomerSeparateHelper.get().createCustomerFunctionality("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"), map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
			createCustomerSeparateHelper.get().verifyCreatedCustomer("Customer successfully created.");
			Thread.sleep(1000);
			createCustomerSeparateHelper.get().deleteCustomer("CreateCustomer");
			createCustomerSeparateHelper.get().verifyDeletedCustomer("Customer successfully deleted");
			
			//TODO:verify the deleted customer in search function, go to search module, search this customer, verify no result found or not match found text
			
			searchCustomerHelper.get().clickOnSearchCustomerLink("SearchCustomer");
			searchCustomerHelper.get().verifySearchCustomerFunctionality2("SearchCustomer", map.get("Name").toString(), map.get("OCN").toString(), map.get("MainDomain").toString(), map.get("Reference").toString(), map.get("CountryToBeSelected").toString(), map.get("TypeToBeSelected").toString());	

		}
		
		
		
	
	
	
	
	
	
	
}
