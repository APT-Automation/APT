package com.saksoft.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.scripthelpers.APT_LoginHelper;

public class APT_VoiceLineTest extends DriverTestcase{
	
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=0)
	public void CreateCustomer(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("CreateCustomer"); 
		APT_VoiceLineHelper.get().createcustomer("voiceline", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));		
	}
	
	@Test(description = "TC-02",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=1)
    public void choosecustomer(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("choosecustomer"); 
		APT_VoiceLineHelper.get().selectCustomertocreateOrder("voiceline",map.get("ChooseCustomerToBeSelected"),map.get("Name1"),map.get("Name2"));
		
	}


	@Test(description = "TC-03",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=2)
	 public void verifycreateorder(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyneworder");
		APT_VoiceLineHelper.get().createorderservice("voiceline", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
	}
	
		
	@Test(description = "TC-04",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=3)
	 public void verifyservicetypeselection(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicetypeselection");
		APT_VoiceLineHelper.get().verifyselectservicetype("voiceline", map.get("ServiceType"));
	}
	
	
	@Test(description = "TC-05",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=4)
	 public void verifyservicecreation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicecreation");
		APT_VoiceLineHelper.get().verifyservicecreation("voiceline", map.get("ServiceIdentification"), map.get("Remarks"),  map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"), map.get("ResellerCode_Value"), map.get("ThirdPartyInternet_Checkbox"), map.get("Email"), map.get("PhoneContact"), map.get("PerformanceReporting_Checkbox"), map.get("ProactiveNotification_Checkbox"), map.get("NotificationManagementTeam_value"));
	}
	
	
	@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=5)
	 public void verifyCustomerDetailsInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyCustomerDetailsInformation");
		APT_VoiceLineHelper.get().searchservice("voiceline", map.get("ServiceIdentification"));
		APT_VoiceLineHelper.get().verifyCustomerDetailsInformation("voiceline", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		APT_VoiceLineHelper.get().verifyUserDetailsInformation("voiceline", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
	}
		
	@Test(description = "TC-07",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=6)
	public void verifyUserDetailsInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyUserDetailsInformation");
		APT_VoiceLineHelper.get().searchservice("voiceline", map.get("ServiceIdentification"));
		APT_VoiceLineHelper.get().createnewuser("voiceline", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), map.get("EditEmail"), map.get("EditPhone"));
		
	}
	
	@Test(description = "TC-08",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=7)
	public void verifyOrderDetailsInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyOrderDetailsInformation");
		APT_VoiceLineHelper.get().searchservice("voiceline", map.get("ServiceIdentification"));
		APT_VoiceLineHelper.get().verifyorderpanelinformation_Neworder("voiceline", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		APT_VoiceLineHelper.get().verifyorderpanel_editorder("voiceline", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
		APT_VoiceLineHelper.get().verifyorderpanel_changeorder("voiceline", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));
		
}
	
	@Test(description = "TC-09",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=8)
	 public void verifyServicepanelinviewservicepage(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyServicepanelinviewservicepage");
		APT_VoiceLineHelper.get().searchservice("voiceline", map.get("ServiceIdentification"));
		//APT_VoiceLineHelper.get().verifyservicepanelInformationinviewservicepage("voiceline", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("Remarks"), map.get("ResellerCode_Value"), map.get("ThirdPartyInternet_Checkbox"), map.get("PhoneContact"));
		APT_VoiceLineHelper.get().verifyservicepanel_links("voiceline", map.get("EditRemarks"), map.get("Remarks"), map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"), map.get("ResellerCode_Value"), map.get("ThirdPartyInternet_Checkbox"), map.get("PhoneContact"), map.get("Edit_ResellerCode"), map.get("Edit_ThirdPartyInternet_Checkbox"), map.get("Edit_ServiceEmail"), map.get("Edit_PhoneContact"), map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_ProactiveNotification_Checkbox"), map.get("Edit_NotificationManagementTeam_Drodpwon"));
		//APT_VoiceLineHelper.get().verifyManageService("voiceline", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
	}
	
	@Test(description = "TC-10",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=9)
	 public void verifyManagementOptionspanel(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyManagementOptionspanel");
		APT_VoiceLineHelper.get().searchservice("voiceline", map.get("ServiceIdentification"));
		APT_VoiceLineHelper.get().verifyManagementOptionspanel("voiceline", map.get("PerformanceReporting_Checkbox"), map.get("ProactiveNotification_Checkbox"), map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_ProactiveNotification_Checkbox"));
	
	}
	
	@Test(description = "TC-11",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=10)
	 public void verifyASRDevice(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyASRDevice");
		APT_VoiceLineHelper.get().searchservice("voiceline", map.get("ServiceIdentification"));
		APT_VoiceLineHelper.get().verifyAddASRDevice("voiceline", map.get("IMSPopLocation_DropdownValue"));
		APT_VoiceLineHelper.get().verifyEditASRDevice("voiceline", map.get("IMSPopLocation_DropdownValue"), map.get("editASRDeviceName"), map.get("editASRManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
				map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
				map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
				map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
		APT_VoiceLineHelper.get().verifyViewASRDevice("voiceline", map.get("IMSPopLocation_DropdownValue"));
		APT_VoiceLineHelper.get().verifyViewDevicepage_Links("voiceline", map.get("ServiceIdentification"), map.get("IMSPopLocation_DropdownValue"));
		APT_VoiceLineHelper.get().verifyFetchInterface("voiceline", map.get("IMSPopLocation_DropdownValue"), map.get("ServiceIdentification"), map.get("editASRDeviceName"), map.get("InServiceStatus"), map.get("InMaintenanceStatus"), map.get("InterfaceName"));
		
	}
	
	@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=11)
	 public void verifyAddTrunk(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddTrunk");
		//APT_VoiceLineHelper.get().searchservice("voiceline", map.get("ServiceIdentification"));
		APT_VoiceLineHelper.get().addTrunkSiteOrder("voiceline", map.get("TrunkGroupOrder"), map.get("TrunkGroupOrderNumber"));
		APT_VoiceLineHelper.get().editSiteOrder("voiceline", map.get("TrunkGroupOrderNumber"), map.get("edit_TrunkGroupOrder"), map.get("edit_TrunkGroupOrderNumber"));
		APT_VoiceLineHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("voiceline", map.get("TrunkGroupOrderNumber"));
		
		APT_VoiceLineHelper.get().addTrunk("voiceline", map.get("Name"), map.get("ServiceIdentification"), map.get("BillingCountry"), map.get("CDRdelivery"),
				map.get("gateway"), map.get("quality"), map.get("SIPURI"), map.get("ResellerID_Value"), map.get("ipAddresstype"), map.get("SIPsignallingPort"),
				map.get("ThirdPartyInternet"), map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("callAdmissionControl"),
				map.get("callrateLimitselection"), map.get("PSXmanualConfigvalue"), map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), 
				map.get("callrateLimiteValue"), map.get("SBCmanualconfigValue"), map.get("COSProfile_Value"), map.get("LANRange_Value"), map.get("NumberPorting_Checkbox"), map.get("CLIPScreeningandCLIRperCall_radio"), 
				map.get("CLIRPermanent_radio"), map.get("ClipNoScreening_radio"), map.get("ClipMainNumber_radio"), map.get("PresentationNumbers_radio"), map.get("PresentationNumber_Value"), map.get("CustomerDefaultNumber_Value"), 
				map.get("EgressNumberFormat_DropdownValue"), map.get("IncomingRoutingOnDDILevel_Checkbox"), map.get("PSXmanualConfigTG_value"), map.get("PSXmanualConfigDDI_value"), map.get("DRusingTDM_checkbox"), 
				map.get("Codec_Value"), map.get("FaxDiversionNumber_Value"), map.get("PartialNumberReplacement_Checkbox"), map.get("cpemanualconfig_checkbox"), map.get("vlanTag_FRASBC_value"));
		
	}
	
	@Test(description = "TC-13",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VoiceLine", priority=12)
	public void viewTrunk(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("viewTrunk");
		
		APT_VoiceLineHelper.get().viewTrunk_Primary("voiceline", map.get("Name"), map.get("ServiceIdentification"), map.get("BillingCountry"), map.get("CDRdelivery"),
				map.get("gateway"), map.get("quality"), map.get("SIPURI"), map.get("ResellerID_value"), map.get("ipAddresstype"), map.get("SIPsignallingPort"), map.get("ThirdPartyInternet"), 
				map.get("vlanTag"),map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("callAdmissionControl"), map.get("callrateLimitselection"), map.get("PSXmanualConfigvalue"), 
				map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), map.get("callrateLimiteValue"), map.get("SBCmanualconfigValue"), map.get("COSProfile_Value"), 
				map.get("LANRange_Value"), map.get("NumberPorting_Checkbox"), map.get("CLIPScreeningandCLIRperCall_radio"), map.get("CLIRPermanent_radio"), map.get("ClipNoScreening_radio"), 
				map.get("ClipMainNumber_radio"), map.get("PresentationNumbers_radio"), map.get("PresentationNumber_Value"), map.get("CustomerDefaultNumber_Value"), map.get("EgressNumberFormat_DropdownValue"), 
				map.get("IncomingRoutingOnDDILevel_Checkbox"), map.get("PSXmanualConfigTG_value"), map.get("PSXmanualConfigDDI_value"), map.get("DRusingTDM_checkbox"), map.get("Codec_Value"), 
				map.get("FaxDiversionNumber_Value"), map.get("PartialNumberReplacement_Checkbox"), map.get("cpemanualconfig_checkbox"));
		
	}
	
	
}