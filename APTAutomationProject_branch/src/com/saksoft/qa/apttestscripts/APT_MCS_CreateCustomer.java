package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader;

public class APT_MCS_CreateCustomer extends DriverTestcase{
  
		@Test(description = "TC-01",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_CreateCustomer", priority = 1)
		public void Verify_CreateCustomerFunctionality(Map<String, String> map) throws Exception {
			DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_CreateCustomerFunctionality");
			Thread.sleep(2000);
			createCustomerSeparateHelper.get().navigateToCreateCustomerPage("CreateCustomer");
			createCustomerSeparateHelper.get().createCustomerFunctionality("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"),
					map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
			createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully created.");
			
//			createCustomerSeparateHelper.get().verifyCreatedCustomerValuesInViewCustomePage(application, Name, MainDomain, CountryToBeSelected, TypeToBeSelected, OCN, Reference, TechnicalContactName, Email, Phone, Fax);
			
			createCustomerSeparateHelper.get().verifyCustomerdetails_InviewCustomerPage("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"),
					map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
			Thread.sleep(1000);
			createCustomerSeparateHelper.get().deleteCustomer("CreateCustomer");
			createCustomerSeparateHelper.get().verifyDeletedCustomer("Customer successfully deleted.");
		}
		
	
}
