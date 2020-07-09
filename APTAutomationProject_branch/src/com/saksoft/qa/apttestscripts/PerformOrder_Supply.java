package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader;

public class PerformOrder_Supply extends DriverTestcase{

	
	
	@Test(description = "TC-01",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_CreateCustomer_AddUser_SupplyService", priority = 1)
	public void Verify_serivceOrderCreationforCustomer(Map<String, String> map) throws Exception {
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_SupplyServiceFromOneCustomerToOtherCustomer");
		Thread.sleep(2000);
		
		supply.get().navigateToCreateCustomerPage("CreateCustomer");
		supply.get().createCustomerFunctionality("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"),
				map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("DedicatedPortalStatus"), map.get("DedicatedPortal"));
		supply.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully created");
		
		supply.get().VerifyAddUserFunctionality("CreateCustomer",map.get("UserName"),map.get("FirstName"), map.get("SurName"),
				map.get("PostalAddress"), map.get("Email_AddUser"),map.get("Phone_AddUser"),map.get("IPGuardianAccountGroup"),
				map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),
				map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),
				map.get("HideSiteOrderToBeSelected"));
		supply.get().verifyAddedUser("User successfully created");
			
		supply.get().createexistingorderservice("CreateCustomer",map.get("Existing OrderService"), map.get("ExistingOrderNumber"), map.get("Existing RFIREQNumber"));

		supply.get().createneworderservice("CreateCustomer", map.get("New OrderService"),
				map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		Thread.sleep(3000);
		supply.get().verifysuccessmessage( "CreateCustomer"  , "Order created successfully");
//		supply.get().verifyCreated_NewOrder("Order created successfully");
		
		Thread.sleep(3000);
		supply.get().verifyselectservicetype("CreateCustomer", map.get("ServiceType"),
				map.get("Service SubType"));
		
		supply.get().Verify_createorderservice("CreateCustomer",
				map.get("Service Identification"), map.get("BelongstoBundle"), map.get("BundleSelectionType"),
				map.get("BillingType"), map.get("BillingTypeSelection"), map.get("TerminationDate"), map.get("Email"),
				map.get("Phone"), map.get("Remarks"));
		
		supply.get().Verify_Selectmanagementoptions("CreateCustomer",
				map.get("Router Configuration View IPv4"), map.get("Router Configuration View IPv6"),
				map.get("Performance Reporting"), map.get("IP Guardian"), map.get("Delivery Channel"), map.get("Managedservice"), 
				map.get("SNMPnotify"), map.get("Traptargetaddress"));
		
		supply.get().Verify_ConfigurationOptions("CreateCustomer", map.get("routerbasedfirewall"), map.get("Qos"));
		Thread.sleep(3000);
		
		supply.get().verifysuccessmessage( "CreateCustomer"  , "Service successfully created");
		
	}
	

	@Test(description = "TC-02",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_CreateCustomer_AddUser_SupplyService", priority = 2)
	public void Verify_SupplyServiceFromOneCustomerToOtherCustomer1(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_SupplyServiceFromOneCustomerToOtherCustomer1");
		Thread.sleep(2000);
		
		String supplyService=map.get("SupplySevice");
		
		if(supplyService.equalsIgnoreCase("Yes")) {
			
			DriverTestcase.logger.log(LogStatus.INFO, "GOing to perform Supply action for "+map.get("Name"));
			
			supply.get().clickOnSearchCustomerLink("CreateCustomer");
			supply.get().verifySearchCustomerFunctionality4("CreateCustomer", map.get("Name"), map.get("OCN"), 
					map.get("SupplyServiceToCustomerName"), map.get("SupplyServiceToCustomerChooseACustomer"), map.get("NewOrderNumber"));	

			
			supply.get().verifyServiceSupplySuccessMessage("Service successfully supplied");
			supply.get().verifySuppliesPanelInformation("CreateCustomer", map.get("SupplyServiceToCustomerName"),
					map.get("NewOrderNumber"), map.get("Service Identification"),
					map.get("ServiceType"), map.get("Status"), map.get("SyncStatus"));
			
			System.out.println("supply done");
		}else {
			
			DriverTestcase.logger.log(LogStatus.INFO, " Not going to perform supply action for "+map.get("Name"));
		}
	}
	
	
	@Test(description = "TC-03",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_CreateCustomer_AddUser_SupplyService", priority = 3)
	public void Verify_Subscribers(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("Verify_Subscribers");
		Thread.sleep(2000);
		
		String subscribedCustomer=map.get("SubscribedForService");
		
		if(subscribedCustomer.equalsIgnoreCase("Yes")) {
			
			DriverTestcase.logger.log(LogStatus.INFO, " Subscribe panel verification is going to be performed for "+ map.get("Name"));
			
			supply.get().clickOnSearchCustomerLink("CreateCustomer");
			supply.get().verifySearchCustomerFunctionality("CreateCustomer", map.get("SupplyServiceToCustomerName"), map.get("OCN")); 

			supply.get().verifySubscribedCustomers("CreateCustomer", map.get("Name"),
					map.get("NewOrderNumber"), map.get("Service Identification"),
					map.get("ServiceType"), map.get("Status"), map.get("SyncStatus"));

			
		}else {
			DriverTestcase.logger.log(LogStatus.INFO, " Subscribe panel verification is not performed for "+ map.get("Name"));
		}
	}
	
	
	@Test(description = "TC-04",dataProviderClass = APT_DataReader.class, dataProvider = "DataReader_CreateCustomer_AddUser_SupplyService", priority = 4)
	public void deleteFunctionality(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("deleteFunctionality");
		Thread.sleep(2000);
		
		supply.get().clickOnSearchCustomerLink("CreateCustomer");
		supply.get().deletOrderFunctionality_serviceDeletion("CreateCustomer", map.get("Name"), map.get("OCN"), 
				map.get("SupplyServiceToCustomerName"), map.get("SupplyServiceToCustomerChooseACustomer"), map.get("NewOrderNumber"));	
		
	//delete service	
		supply.get().deleteService("CreateCustomer");
		supply.get().verifySuccessMessageFor_deleteService("CreateCustomer");
		
	//delete Order
		supply.get().clickOnSearchCustomerLink("CreateCustomer");
		supply.get().deletOrderFunctionality_orderDeletion("CreateCustomer", map.get("Name"), map.get("OCN"), 
				map.get("SupplyServiceToCustomerName"), map.get("SupplyServiceToCustomerChooseACustomer"), map.get("NewOrderNumber"));
		supply.get().deleteOrder("CreateCustomer");
		
		
	//delete User
		supply.get().deleteUser("CreateCustomer", map.get("UserName"));
		supply.get().verifyDeletedUser("User successfully deleted");
		
	//delete Customer
		supply.get().deleteCustomer("CreateCustomer");
		supply.get().verifyDeletedCustomer("Customer successfully deleted");
		
	}
	
	
	


	
}
