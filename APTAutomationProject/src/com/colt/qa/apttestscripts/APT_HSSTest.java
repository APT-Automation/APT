package com.colt.qa.apttestscripts;

	import java.io.IOException;
import java.util.Map;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

	public class APT_HSSTest extends DriverTestcase{
		
		public String CustomerName=null;
		
		APT_Login Login=new APT_Login();
		
			@Test(dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_HSS", priority=1)
			public void HSSService(Map<String, String> map) throws Exception {
				
				setup();	
				Login.APT_Login_1(map.get("url for the product"));
				
		        String newCustomerName=map.get("newCustomerCreation");
		        String existingCustomer=map.get("existingCustomerSelection");
		        
		        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
		              
		              logger= ExtentTestManager.startTest ("CreateCustomer - HSS");
		              APT_HSSHelper.get().createcustomer("hss", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
		                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
		                          map.get("Fax"));
		              CustomerName=map.get("newCustomer");
		              ExtentTestManager.endTest();
		              
		              logger= ExtentTestManager.startTest ("selectExistingCustomer - HSS"); 
		              APT_HSSHelper.get().selectCustomertocreateOrder("hss",map.get("newCustomer"));
		              ExtentTestManager.endTest();
		        }
		        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
		              
		              logger= ExtentTestManager.startTest ("selectExistingCustomer - HSS"); 
		              APT_HSSHelper.get().selectCustomertocreateOrder("hss",map.get("existingCustomer"));
		              CustomerName=map.get("existingCustomer");
		              ExtentTestManager.endTest();
		        }
		        
				logger= ExtentTestManager.startTest ("verifycreateorder - HSS");
				APT_HSSHelper.get().createorderservice("hss", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
				ExtentTestManager.endTest();
				
				logger= ExtentTestManager.startTest ("verifyservicetypeselection - HSS");
				APT_HSSHelper.get().verifyselectservicetype("hss", map.get("ServiceType"));
				ExtentTestManager.endTest();
				
				logger= ExtentTestManager.startTest ("verifyservicecreation - HSS");
				APT_HSSHelper.get().verifyservicecreation("hss", map.get("ServiceIdvalue"),map.get("RemarkValue"),map.get("EmailValue"),
				map.get("PhoneContactValue"),map.get("PerformanceReporting_Checkbox"),map.get("WaveService_checkbox")
				,map.get("InterfaceSpeedValue"),map.get("TypeOfServiceValue"));
				ExtentTestManager.endTest();
				
				logger= ExtentTestManager.startTest ("verifyCustomerDetailsInformation - HSS");
				APT_HSSHelper.get().verifyCustomerDetailsInformation("hss", map.get("newCustomerCreation"), map.get("existingCustomerSelection"),
						map.get("newCustomer"),	map.get("existingCustomer"),
						map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"), 
						map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
				ExtentTestManager.endTest();
				
				logger= ExtentTestManager.startTest ("verifyservicepanelinviewservicepage - HSS");
				APT_HSSHelper.get().verifyservicepanelInformationinviewservicepage("hss", map.get("ServiceIdvalue")
						,map.get("RemarkValue"),map.get("EmailValue"),map.get("PhoneContactValue"),map.get("ServiceType"));
				ExtentTestManager.endTest();
				
				logger= ExtentTestManager.startTest ("VerifyManagementOptionsPanel - HSS");
				APT_HSSHelper.get().VerifyManagementPanel("hss",map.get("PerformanceReporting_Checkbox"),map.get("WaveService_checkbox")
						,map.get("InterfaceSpeedValue"),map.get("TypeOfServiceValue"));
				ExtentTestManager.endTest();
				
				logger= ExtentTestManager.startTest ("VerifyOrderPanel - HSS");
				APT_HSSHelper.get().verifyorderpanel_editorder("hss", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
				APT_HSSHelper.get().verifyorderpanel_changeorder("hss", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
						map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
				ExtentTestManager.endTest();
				
			logger= ExtentTestManager.startTest ("verifyServicePanelLinks - HSS");
			APT_HSSHelper.get().VerifyEditService("hss", map.get("EditRemarks"), map.get("ServiceIdvalue"), map.get("Edit_Email"), map.get("Edit_PhoneContact"));				
			APT_HSSHelper.get().verifyDump("hss");
			APT_HSSHelper.get().verifyShowInfovistaReport("hss");
			ExtentTestManager.endTest();
			
				logger= ExtentTestManager.startTest ("AddNewPEDevice - HSS");
				APT_HSSHelper.get().navigateToAddNewDevicepage("hss");
				APT_HSSHelper.get().addNewPEDevice("hss", map.get("DeviceName"), map.get("DeviceType")
						, map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C")
						, map.get("SnmPro"), map.get("Snmprw"), map.get("SnmProNewValue"), map.get("SnmprwNewValue")
						, map.get("Snmp3"), map.get("Snmpv3Username"),map.get("Snmpv3Authpassword")
						, map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), map.get("Snmpv3AuthpasswordNewValue")
						, map.get("Snmpv3PrivpasswordNewValue"), map.get("SecurityProtocols_DropdownValue")
						,map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity")
						, map.get("ExistingCityValue"), map.get("ExistingSite"),map.get("ExistingSiteValue")
						, map.get("ExistingPremise"), map.get("ExistingPremiseValue"), map.get("NewCity")
						, map.get("NewCityName"), map.get("NewCityCode"), map.get("NewSiteName"), map.get("NewSiteCode")
						, map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
				
				APT_HSSHelper.get().verifyViewpage_Devicedetails("hss", map.get("ServiceIdvalue"), map.get("DeviceName")
						, map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), 
						map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3UsernameNewValue"), 
						map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue")
						, map.get("SecurityProtocols_DropdownValue"),map.get("Country")
						, map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue")
						, map.get("ExistingSite"),map.get("ExistingSiteValue"), map.get("ExistingPremise")
						, map.get("ExistingPremiseValue"), map.get("NewCity"), map.get("NewCityName")
						, map.get("NewCityCode"), map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName")
						, map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
				
				APT_HSSHelper.get().verifyEditDevice("hss", map.get("editdeviceName"), map.get("editVendorModel")
						, map.get("editTelnet"), map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3")
						, map.get("editSnmProNewValue"), map.get("editSnmprwNewValue")
						, map.get("editSnmpv3UsernameNewValue"),map.get("editSnmpv3AuthpasswordNewValue")
						, map.get("editSnmpv3PrivpasswordNewValue"), map.get("Edit_SecurityProtocols_Value")
						, map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity")
						,map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue")
						, map.get("editExistingPremise"), map.get("editExistingPremiseValue"), map.get("editNewCity")
						, map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName")
						, map.get("editNewCityCode"), map.get("editNewSiteName"),map.get("editNewSiteCode")
						, map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
				ExtentTestManager.endTest();
				
		logger= ExtentTestManager.startTest ("AddExistingDevice - HSS");
		APT_HSSHelper.get().AddExistingDevice("hss", map.get("ExistingDeviceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("SelectInterface - HSS");
		APT_HSSHelper.get().selectInterfacelinkforDevice("hss", map.get("ExistingDeviceName"));
		APT_HSSHelper.get().SelectInterface("hss");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("DeleteExistingDevice - HSS");
		APT_HSSHelper.get().deleteExistingDevice("hss", map.get("ExistingDeviceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("ManageService - HSS");
		APT_HSSHelper.get().verifyManageService("hss", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdvalue"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("DeleteNewDevice - HSS");
		APT_HSSHelper.get().deleteDevice("hss", map.get("ManagementAddress"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("DeleteService - HSS");
		APT_HSSHelper.get().deleteService("hss");
		ExtentTestManager.endTest();
		}
	}
	
	
	
	
