package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.LogStatus;

/**
 *    Suite for Execution 
 * @author
 *
 */
public class APT_DomainManagementTest extends DriverTestcase{
	public String CustomerName=null;
	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_Domain", priority=0)
	public void DomainManagementService(Map<String, String> map) throws Exception {
		
		setup();	
		Login.APT_Login_1(map.get("url for the product"));
		
        String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
        
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
              
              logger= ExtentTestManager.startTest ("CreateCustomer - Domain Management");
              APT_DomainManageHelper.get().createcustomer("DomainManagementservice", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              ExtentTestManager.endTest();
              
              logger= ExtentTestManager.startTest ("selectNewCustomer - Domain Management");
              APT_DomainManageHelper.get().selectCustomertocreateOrder("DomainManagementservice",map.get("newCustomer"));
              ExtentTestManager.endTest();
              
        }
        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              logger= ExtentTestManager.startTest ("selectExistingCustomer - Domain Management"); 
              APT_DomainManageHelper.get().selectCustomertocreateOrder("DomainManagementservice",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
              ExtentTestManager.endTest();
        }
        
		logger= ExtentTestManager.startTest ("verifycreateorder");
		APT_DomainManageHelper.get().createorderservice("DomainManagementservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyservicetypeselection");
		APT_DomainManageHelper.get().verifyselectservicetype("DomainManagementservice", map.get("ServiceType"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyservicecreation");
		APT_DomainManageHelper.get().verifyservicecreation("DomainManagementservice", map.get("ServiceIdentification")
				, map.get("Remarks"), map.get("ServiceEmail"), map.get("ServicePhoneContact"), map.get("ServiceCountry")
				, map.get("Passwordvalue"), map.get("ServiceDefaultEmail"), map.get("ServiceUser")
				, map.get("ServiceFirstName"), map.get("ServiceLastName"), map.get("OrganizationName")
				, map.get("ServiceAddress"), map.get("ServiceComplement"), map.get("ServicePostalCode")
				, map.get("ServiceCity"), map.get("ServiceState"), map.get("ServicePhone"), map.get("ServiceFax")
				, map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyCustomerDetailsInformation");
		APT_DomainManageHelper.get().verifyCustomerDetailsInformation("DomainManagementservice", map.get("newCustomerCreation"), map.get("existingCustomerSelection"),
				map.get("newCustomer"),	map.get("existingCustomer"),
				map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"), 
				map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyorderpanelinviewservicepage");
		APT_DomainManageHelper.get().verifyorderpanel_editorder("DomainManagementservice", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
		APT_DomainManageHelper.get().verifyorderpanel_changeorder("DomainManagementservice", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
				map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest ("verifyservicepanelInformation");
		APT_DomainManageHelper.get().verifyservicepanelInformationinviewservicepage("DomainManagementservice", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("Remarks"), map.get("ServiceEmail"), map.get("ServicePhoneContact"), map.get("ServiceCountry"), map.get("ServiceDefaultEmail"), map.get("ServiceFirstName"), map.get("ServiceLastName"), map.get("OrganizationName"), map.get("ServiceAddress"), map.get("ServiceComplement"), map.get("ServicePostalCode"), map.get("ServiceCity"), map.get("ServiceState"), map.get("ServicePhone"), map.get("ServiceFax"), map.get("ServiceUser"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		APT_DomainManageHelper.get().verifyEditService("DomainManagementservice", map.get("Edit_Remarks"), map.get("Remarks"), map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("Edit_ServiceIdentification"), map.get("ServiceType"), map.get("Edit_ServiceEmail"), map.get("Edit_ServicePhoneContact"), map.get("Edit_ServiceUser"), map.get("Edit_ServiceDefaultEmail"), map.get("Edit_ServiceFirstName"), map.get("Edit_ServiceLastName"), map.get("Edit_OrganizationName"), map.get("Edit_ServiceAddress"), map.get("Edit_ServiceComplement"), map.get("Edit_ServicePostalCode"), map.get("Edit_ServiceCity"), map.get("Edit_ServiceState"), map.get("Edit_ServiceCountry"), map.get("Edit_ServicePhone"), map.get("Edit_ServiceFax"));
		APT_DomainManageHelper.get().verifySynchronize("DomainManagementservice");
		APT_DomainManageHelper.get().verifyDeleteService("DomainManagementservice");
		ExtentTestManager.endTest();
	}
}
