package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DataReader_PK;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.excellibrary.APT_DataReader_SS;
import com.relevantcodes.extentreports.LogStatus;

	public class APT_HSSTest extends DriverTestcase{
		
		public String CustomerName=null;
		
		APT_Login Login=new APT_Login();
		
			@Test(dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_HSS", priority=1)
			public void HSSService(Map<String, String> map) throws Exception {
				
				setup();	
				Login.APT_Login_1(map.get("url"));
				
		        String newCustomerName=map.get("newCustomerCreation");
		        String existingCustomer=map.get("existingCustomerSelection");
		        
		        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
		              
		              DriverTestcase.logger = DriverTestcase.extent.startTest("CreateCustomer");
		              APT_HSSHelper.get().createcustomer("hss", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
		                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
		                          map.get("Fax"));
		              CustomerName=map.get("newCustomer");
		              
		              DriverTestcase.logger = DriverTestcase.extent.startTest("selectExistingCustomer"); 
		              APT_HSSHelper.get().selectCustomertocreateOrder("hss",map.get("newCustomer"));
		              
		        }
		        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
		              
		              DriverTestcase.logger = DriverTestcase.extent.startTest("selectExistingCustomer"); 
		              APT_HSSHelper.get().selectCustomertocreateOrder("hss",map.get("existingCustomer"));
		              CustomerName=map.get("existingCustomer");
		        }
		        
				DriverTestcase.logger = DriverTestcase.extent.startTest("verifycreateorder");
				APT_HSSHelper.get().createorderservice("hss", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
			
				DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicetypeselection");
				APT_HSSHelper.get().verifyselectservicetype("hss", map.get("ServiceType"));

				DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicecreation");
				APT_HSSHelper.get().verifyservicecreation("hss", map.get("ServiceIdvalue"),map.get("RemarkValue"),map.get("EmailValue"),
				map.get("PhoneContactValue"),map.get("Performanceexpected"),map.get("PerformanceDefault"),map.get("Waveexpected"),map.get("WaveDefault")
				,map.get("InterfaceSpeedValue"),map.get("TypeOfServiceValue"));
			
				DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicepanelInformationinviewservicepage");
				APT_HSSHelper.get().verifyservicepanelInformationinviewservicepage("hss", map.get("ServiceIdvalue"),map.get("RemarkValue"),map.get("EmailValue"),map.get("PhoneContactValue"),map.get("ServiceType"),
						map.get("Performanceexpected"),map.get("PerformanceDefault"),map.get("Waveexpected"),map.get("WaveDefault"));
		
				DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyManagementPanel");
				APT_HSSHelper.get().VerifyManagementPanel("hss",map.get("Performanceexpected"),map.get("Waveexpected"),map.get("InterfaceSpeed"),map.get("TypeOfService"));
	
				DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyOrderPanel");
				APT_HSSHelper.get().verifyorderpanel_editorder("hss", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
				APT_HSSHelper.get().verifyorderpanel_changeorder("hss", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyServicePanelLinks");
			APT_HSSHelper.get().VerifyEditService("hss", map.get("EditRemarks"), map.get("ServiceIdvalue"), map.get("Edit_Email"), map.get("Edit_PhoneContact"));				
			APT_HSSHelper.get().verifyDump("hss");
			APT_HSSHelper.get().verifyShowInfovistaReport("hss");
	
				DriverTestcase.logger = DriverTestcase.extent.startTest("addNewPEDevice");
				APT_HSSHelper.get().navigateToAddNewDevicepage("hss");
				APT_HSSHelper.get().addNewPEDevice("hss", map.get("DeviceName"), map.get("DeviceType"), map.get("VendorModel"),
															map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
															map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
															map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
				APT_HSSHelper.get().verifyViewpage_Devicedetails("hss", map.get("ServiceIdvalue"), map.get("DeviceName"), map.get("VendorModel"),
															map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
															map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
															map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
				APT_HSSHelper.get().verifyEditDevice("hss", map.get("editdeviceName"), map.get("editVendorModel"), map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
															map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
															map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
															map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));

		DriverTestcase.logger = DriverTestcase.extent.startTest("Add Existing Device");
		APT_HSSHelper.get().AddExistingDevice("hss", map.get("ExistingDeviceName"));
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("Select Interface");
		APT_HSSHelper.get().selectInterfacelinkforDevice("hss", map.get("ExistingDeviceName"));
		APT_HSSHelper.get().SelectInterface("hss");
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("Delete existing device");
		APT_HSSHelper.get().deleteExistingDevice("hss", map.get("ExistingDeviceName"));
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("Manage Service");
		APT_HSSHelper.get().verifyManageService("hss", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdvalue"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("Delete new device");
		APT_HSSHelper.get().deleteDevice("hss", map.get("ManagementAddress"));
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("Delete Service");
		APT_HSSHelper.get().deleteService("hss");
		}
	}
	
	
	
	
