package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.relevantcodes.extentreports.LogStatus;

public class PerformOrder_Supply extends DriverTestcase{

	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "DataReader_CreateCustomer_AddUser_SupplyService", priority = 1)
	public void Verify_serivceOrderCreationforCustomer(Map<String, String> map) throws Exception {
		
		setup();	
		
		Login.APT_Login_1(map.get("url"));	
		
		
		logger= ExtentTestManager.startTest ("Verify_SupplyServiceFromOneCustomerToOtherCustomer");
		Thread.sleep(2000);
		
		createCustomerSeparateHelper.get().navigateToCreateCustomerPage("CreateCustomer");
		createCustomerSeparateHelper.get().createCustomerFunctionality("CreateCustomer", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("TypeToBeSelected"),  map.get("OCN"),map.get("Reference"),
				map.get("TechnicalContactName"), map.get("Email"),map.get("Phone"),map.get("Fax"), map.get("enableDedicatedPortal"), map.get("DedicatedPortal"));
		createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Customer successfully created.");
		
		createCustomerSeparateHelper.get().VerifyAddUserFunctionality("CreateCustomer",map.get("UserName"),map.get("FirstName"), map.get("SurName"),
				map.get("PostalAddress"), map.get("Email_AddUser"),map.get("Phone_AddUser"),map.get("IPGuardianAccountGroup"),
				map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),
				map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),
				map.get("HideSiteOrderToBeSelected"));
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "User successfully created");
			
		createCustomerSeparateHelper.get().createexistingorderservice("CreateCustomer",map.get("Existing OrderService"), map.get("ExistingOrderNumber"), map.get("Existing RFIREQNumber"));

		createCustomerSeparateHelper.get().createneworderservice("CreateCustomer", map.get("New OrderService"),
				map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		Thread.sleep(3000);
		createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Order created successfully");
		
		Thread.sleep(3000);
		createCustomerSeparateHelper.get().verifyselectservicetype("CreateCustomer", map.get("ServiceType"),
				map.get("Service SubType"));
		
		createCustomerSeparateHelper.get().Verify_createorderservice("CreateCustomer",
				map.get("Service Identification"), map.get("BelongstoBundle"), map.get("BundleSelectionType"),
				map.get("BillingType"), map.get("BillingTypeSelection"), map.get("TerminationDate"), map.get("Email"),
				map.get("Phone"), map.get("Remarks"));
		
		createCustomerSeparateHelper.get().Verify_Selectmanagementoptions("CreateCustomer",
				map.get("Router Configuration View IPv4"), map.get("Router Configuration View IPv6"),
				map.get("Performance Reporting"), map.get("IP Guardian"), map.get("Delivery Channel"), map.get("Managedservice"), 
				map.get("SNMPnotify"), map.get("Traptargetaddress"));
		
		createCustomerSeparateHelper.get().Verify_ConfigurationOptions("CreateCustomer", map.get("routerbasedfirewall"), map.get("Qos"));
		Thread.sleep(3000);
		
		createCustomerSeparateHelper.get().verifysuccessmessage( "CreateCustomer"  , "Service successfully created");
		ExtentTestManager.endTest(); 
	}
	

	@Test(description = "TC-02",dataProviderClass = DataReader.class, dataProvider = "DataReader_CreateCustomer_AddUser_SupplyService", priority = 2)
	public void Verify_SupplyServiceFromOneCustomerToOtherCustomer1(Map<String, String> map) throws Exception {
		
		logger= ExtentTestManager.startTest ("Verify_SupplyServiceFromOneCustomerToOtherCustomer1");
		Thread.sleep(2000);
		
		String supplyService=map.get("SupplySevice");
		
		if(supplyService.equalsIgnoreCase("Yes")) {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Going to perform Supply action for "+map.get("Name"));
			
			createCustomerSeparateHelper.get().clickOnSearchCustomerLink("CreateCustomer");
			createCustomerSeparateHelper.get().searchCustomerAndperformSupply("CreateCustomer", map.get("Name"), map.get("OCN"), 
					map.get("SupplyServiceToCustomerName"), map.get("NewOrderNumber"));	
			createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "Service successfully supplied");
			createCustomerSeparateHelper.get().verifySuppliesPanelInformation("CreateCustomer", map.get("SupplyServiceToCustomerName"),
					map.get("NewOrderNumber"), map.get("Service Identification"),
					map.get("ServiceType"), map.get("Status"), map.get("SyncStatus"));
			
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, " Not going to perform supply action for "+map.get("Name"));
		}
		ExtentTestManager.endTest(); 
	}
	
	
	@Test(description = "TC-03",dataProviderClass = DataReader.class, dataProvider = "DataReader_CreateCustomer_AddUser_SupplyService", priority = 3)
	public void Verify_Subscribers(Map<String, String> map) throws Exception {
		
		logger= ExtentTestManager.startTest ("Verify_Subscribers");
		Thread.sleep(2000);
		
		String subscribedCustomer=map.get("SubscribedForService");
		
		if(subscribedCustomer.equalsIgnoreCase("Yes")) {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, " Subscribe panel verification is going to be performed for "+ map.get("Name"));
			
			createCustomerSeparateHelper.get().clickOnSearchCustomerLink("CreateCustomer");
			createCustomerSeparateHelper.get().verifySearchCustomerFunctionality("CreateCustomer", map.get("SupplyServiceToCustomerName"), map.get("OCN")); 

			createCustomerSeparateHelper.get().verifySubscribedCustomers("CreateCustomer", map.get("Name"),
					map.get("NewOrderNumber"), map.get("Service Identification"),
					map.get("ServiceType"), map.get("Status"), map.get("SyncStatus"));

			
		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, " Subscribe panel verification is not performed for "+ map.get("Name"));
		}
	}
	
	
	@Test(description = "TC-04",dataProviderClass = DataReader.class, dataProvider = "DataReader_CreateCustomer_AddUser_SupplyService", priority = 4)
	public void deleteFunctionality(Map<String, String> map) throws Exception {
		
		logger= ExtentTestManager.startTest ("deleteFunctionality");
		Thread.sleep(2000);
		
		createCustomerSeparateHelper.get().clickOnSearchCustomerLink("CreateCustomer");
		createCustomerSeparateHelper.get().deletSupplyFunctionalityAndSelectOrderInsideTable("CreateCustomer", map.get("Name"), map.get("OCN"), 
				map.get("SupplyServiceToCustomerName"),  map.get("NewOrderNumber"));	
		
	//delete service	
		createCustomerSeparateHelper.get().deleteService("CreateCustomer");
		createCustomerSeparateHelper.get().verifySuccessMessageFor_deleteService("CreateCustomer");
		
	//delete Order
//		createCustomerSeparateHelper.get().clickOnSearchCustomerLink("CreateCustomer");
		createCustomerSeparateHelper.get().deletOrderFunctionality_selectTheOrderInsideTable("CreateCustomer", map.get("Name"), map.get("OCN"), 
				map.get("SupplyServiceToCustomerName"),  map.get("NewOrderNumber"));
		createCustomerSeparateHelper.get().deleteOrder("CreateCustomer");
		
		
	//delete User
		createCustomerSeparateHelper.get().deleteUser("CreateCustomer", map.get("UserName"));
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "User successfully deleted");
		
	//delete Customer
		createCustomerSeparateHelper.get().deleteCustomer("CreateCustomer");
		createCustomerSeparateHelper.get().verifysuccessmessage("CreateCustomer", "Customer successfully deleted");
		ExtentTestManager.endTest(); 
	}
	
	
	


	
}
