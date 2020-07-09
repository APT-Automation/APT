package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader;

public class APT_MCS_AddUser extends DriverTestcase{
  
	
	@Test(description = "TC-01",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_CreateCustomer_AddUserTest", priority = 1)
	public void Verify_AddUserFunctionality(Map<String, String> map) throws Exception {
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_AddUserFunctionality");
		Thread.sleep(2000);
		
		createCustomerSeparateHelper.get().navigateToCreateCustomerPage("CreateCustomer");
		createCustomerSeparateHelper.get().createCustomerFunctionality("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"),
				map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
		createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully created.");
		
		createCustomerSeparateHelper.get().VerifyAddUserFunctionality("CreateCustomer",map.get("UserName"),map.get("FirstName"), map.get("SurName"),
				map.get("PostalAddress"), map.get("Email_AddUser"),map.get("Phone_AddUser"),map.get("IPGuardianAccountGroup"),
				map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),
				map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),
				map.get("HideSiteOrderToBeSelected"));
//		createCustomerSeparateHelper.get().verifyAddedUser("User successfully created");
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "User successfully created");
		Thread.sleep(2000);
		
		createCustomerSeparateHelper.get().deleteUser("CreateCustomer", map.get("UserName"));
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "User successfully deleted");
//		createCustomerSeparateHelper.get().verifyDeletedUser("User successfully deleted");
	
	}
	
	
	@Test(description = "TC-05",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_CreateCustomer", priority = 3)
	public void deleteCustomer(Map<String, String> map) throws Exception {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("deleteCustomer");
		
		createCustomerSeparateHelper.get().deleteCustomer("CreateCustomer");
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "Customer successfully deleted");
//		createCustomerSeparateHelper.get().verifyDeletedCustomer("Customer successfully deleted");
		
	}	
			
	
	
	
}
