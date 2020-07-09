package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader;

public class APT_MCS_ViewUser extends DriverTestcase{
  
	
	@Test(description = "TC-06",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_CreateCustomer_ViewUserTest", priority = 7)
	public void Verify_ViewUserFunctionality(Map<String, String> map) throws Exception {
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_ViewUserFunctionality");
		Thread.sleep(2000);
		createCustomerSeparateHelper.get().navigateToCreateCustomerPage("CreateCustomer");
		createCustomerSeparateHelper.get().createCustomerFunctionality("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"), map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "Customer successfully created.");
//		createCustomerSeparateHelper.get().verifyCreatedCustomer("Customer successfully created.");
		Thread.sleep(1000);

		createCustomerSeparateHelper.get().VerifyAddUserFunctionality("CreateCustomer",map.get("UserName"),map.get("FirstName"), map.get("SurName"),map.get("PostalAddress"), map.get("Email_AddUser"),map.get("Phone_AddUser"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"));
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "User successfully created");
		
		createCustomerSeparateHelper.get().verifyAddedUserValuesInViewUserPage("CreateCustomer", map.get("UserName"), map.get("FirstName"), map.get("SurName"),map.get("PostalAddress"),  map.get("Email_AddUser"),map.get("Phone_AddUser"), map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"), map.get("HideSiteOrderToBeSelected"));
		
		createCustomerSeparateHelper.get().deleteUser("CreateCustomer", map.get("UserName"));
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "User successfully deleted");
		
		createCustomerSeparateHelper.get().deleteCustomer("CreateCustomer");
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "Customer successfully deleted");
		
	}
	
	
		
}
