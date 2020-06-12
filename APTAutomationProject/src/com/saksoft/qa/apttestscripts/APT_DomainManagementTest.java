package com.saksoft.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.scripthelpers.APT_LoginHelper;

public class APT_DomainManagementTest extends DriverTestcase{
	
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_Domain", priority=0)
	public void CreateCustomer(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("CreateCustomer"); 
		APT_DomainManageHelper.get().createnewcustomer("DomainManagementservice", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		
	} 
	
	@Test(description = "TC-02",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_Domain", priority=1)
    public void choosecustomer(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("choosecustomer"); 
		APT_DomainManageHelper.get().selectCustomertocreateOrder("DomainManagementservice",map.get("ChooseCustomerToBeSelected"),map.get("Name1"),map.get("Name2"));
		
	}

	@Test(description = "TC-03",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_Domain", priority=2)
	 public void verifycreateorder(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifycreateorder");
		//APT_DomainManageHelper.get().createexistingorderservice("nginservice", map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		APT_DomainManageHelper.get().createorderservice("DomainManagementservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
	}
	
		
	@Test(description = "TC-04",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_Domain", priority=3)
	 public void verifyservicetypeselection(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicetypeselection");
		APT_DomainManageHelper.get().verifyselectservicetype("DomainManagementservice", map.get("ServiceType"));
	}
	
	
	@Test(description = "TC-05",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_Domain", priority=4)
	 public void verifyservicecreation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicecreation");
		APT_DomainManageHelper.get().verifyingservicecreation("DomainManagementservice", map.get("ServiceIdentification"), map.get("Remarks"), map.get("ServiceEmail"), map.get("ServicePhoneContact"), map.get("ServiceCountry"), map.get("Passwordvalue"), map.get("ServiceDefaultEmail"), map.get("ServiceUser"), map.get("ServiceFirstName"), map.get("ServiceLastName"), map.get("OrganizationName"), map.get("ServiceAddress"), map.get("ServiceComplement"), map.get("ServicePostalCode"), map.get("ServiceCity"), map.get("ServiceState"), map.get("ServicePhone"), map.get("ServiceFax"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"));
	}
	

	@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_Domain", priority=5)
	 public void verifyorderpanelinviewservicepage(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyorderpanelinviewservicepage");
		//APT_DomainManageHelper.get().searchorder("DomainManagementservice", map.get("ServiceIdentification"));
		APT_DomainManageHelper.get().verifyorderpanelinformation_Neworder("DomainManagementservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		APT_DomainManageHelper.get().verifyorderpanel_editorder("DomainManagementservice", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
		APT_DomainManageHelper.get().verifyorderpanel_changeorder("DomainManagementservice", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));
	}
		
	@Test(description = "TC-07",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_Domain", priority=6)
	public void verifyservicepanelInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicepanelInformation");
		//APT_DomainManageHelper.get().searchorder("DomainManagementservice", map.get("ServiceIdentification"));
		APT_DomainManageHelper.get().verifyservicepanelInformationinviewservicepage("DomainManagementservice", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("Remarks"), map.get("ServiceEmail"), map.get("ServicePhoneContact"), map.get("ServiceCountry"), map.get("ServiceDefaultEmail"), map.get("ServiceFirstName"), map.get("ServiceLastName"), map.get("OrganizationName"), map.get("ServiceAddress"), map.get("ServiceComplement"), map.get("ServicePostalCode"), map.get("ServiceCity"), map.get("ServiceState"), map.get("ServicePhone"), map.get("ServiceFax"), map.get("ServiceUser"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		APT_DomainManageHelper.get().verifyservicepanel_links("DomainManagementservice", map.get("Edit_Remarks"), map.get("Remarks"), map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("Edit_ServiceIdentification"), map.get("ServiceType"), map.get("Edit_ServiceEmail"), map.get("Edit_ServicePhoneContact"), map.get("Edit_ServiceUser"), map.get("Edit_ServiceDefaultEmail"), map.get("Edit_ServiceFirstName"), map.get("Edit_ServiceLastName"), map.get("Edit_OrganizationName"), map.get("Edit_ServiceAddress"), map.get("Edit_ServiceComplement"), map.get("Edit_ServicePostalCode"), map.get("Edit_ServiceCity"), map.get("Edit_ServiceState"), map.get("Edit_ServiceCountry"), map.get("Edit_ServicePhone"), map.get("Edit_ServiceFax"));
		
	}
	
	

}
