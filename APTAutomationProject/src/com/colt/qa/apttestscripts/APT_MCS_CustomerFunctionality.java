package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DataReader_PK;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.excellibrary.APT_DataReader;

public class APT_MCS_CustomerFunctionality extends DriverTestcase{
		
	APT_Login Login=new APT_Login();
	
	
		@Test(description = "TC-03",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_CustomerFunctionality", priority = 0)
		public void Verify_CreateCustomerFunctionality(Map<String, String> map) throws Exception {
			
			String devicename=map.get("Name");
			
			setup();	
			
			Login.APT_Login_1(map.get("url"));	
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_CreateCustomerFunctionality");
			createCustomerSeparateHelper.get().navigateToCreateCustomerPage("CreateCustomer");
			createCustomerSeparateHelper.get().createCustomerFunctionality("CreateCustomer", map.get("Name"), map.get("MainDomain"),
					map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"), 
					map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("enableDedicatedPortal"), map.get("DedicatedPortal"));
			createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully created.");
			
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyEnteredValuesForCreatingCustomer");	
			createCustomerSeparateHelper.get().verifyCustomerdetails_InviewCustomerPage("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"),
					map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("enableDedicatedPortal"), map.get("DedicatedPortal"));
			
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_EditCustomerFunctionality");
			createCustomerSeparateHelper.get().verifyEditCustomerFunction("CreateCustomer", map.get("editCustomerName"), map.get("editMainDomain"), 
					map.get("editCountryToBeSelected"), map.get("editTypeToBeSelected"),  map.get("editOCN"),map.get("editReference"), map.get("editTechnicalContactName"),
					map.get("editEmail"),map.get("editPhone"),map.get("editFax"), map.get("editDedicatedPortalStatus"), map.get("editDedicatedPortal"));
			createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully updated.");
			
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyUpdatedValuesForCreatingCustomer");		
			createCustomerSeparateHelper.get().verifyCustomerdetails_InviewCustomerPage_edited("CreateCustomer", map.get("editCustomerName"), map.get("editMainDomain"), 
					map.get("editCountryToBeSelected"), map.get("editTypeToBeSelected"),  map.get("editOCN"),map.get("editReference"), map.get("editTechnicalContactName"),
					map.get("editEmail"),map.get("editPhone"),map.get("editFax"), map.get("editDedicatedPortalStatus"), map.get("editDedicatedPortal"));
			
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_AddUserFunctionality");	
			createCustomerSeparateHelper.get().VerifyAddUserFunctionality("CreateCustomer",map.get("UserName"),map.get("FirstName"), map.get("SurName"),
					map.get("PostalAddress"), map.get("Email_AddUser"),map.get("Phone_AddUser"),map.get("IPGuardianAccountGroup"),
					map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),
					map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), 
					map.get("HideServicesToBeSelected"), map.get("HideSiteOrderToBeSelected"));
			createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "User successfully created");
			
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_AddedUserValues");	
			createCustomerSeparateHelper.get().verifyAddedUserValuesInViewUserPage("CreateCustomer", map.get("UserName"), map.get("FirstName"), map.get("SurName"),
					map.get("PostalAddress"),  map.get("Email_AddUser"),map.get("Phone_AddUser"), map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),
					map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"),
					map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"), map.get("HideSiteOrderToBeSelected"));
			
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_EditUserFunctionality");
			createCustomerSeparateHelper.get().selectUserTOEdit("CreateCustomer", map.get("UserName"));
			createCustomerSeparateHelper.get().verifyEditUserFunction("CreateCustomer",map.get("editUserName"), map.get("editFirstName"), map.get("editSurName"),
					map.get("editPostalAddress"), map.get("Email_editUser"), map.get("Phone_editUser"),map.get("editIPGuardianAccountGroup"),
					map.get("editColtOnlineUser"), map.get("editGeneratePassword"),
					map.get("Role_ToBeAvailable"),map.get("Role_ToBeHidden"),
					map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"),
					map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"),
					map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"),
					map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"),
					map.get("SiteOrders_ToBeAvailable") , map.get("SiteOrders_ToBeHidden"));
			createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "User successfully updated");

			
		DriverTestcase.logger = DriverTestcase.extent.startTest("deleteUser");	
			createCustomerSeparateHelper.get().deleteUser("CreateCustomer", map.get("UserName"));
			createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "User successfully deleted");

		DriverTestcase.logger = DriverTestcase.extent.startTest("deleteCustomer");
			createCustomerSeparateHelper.get().deleteCustomer("CreateCustomer");
			createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "Customer successfully deleted");

		}
	
	
}
