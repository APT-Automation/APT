package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.scripthelpers.APT_LoginHelper;

public class APT_VoiceLineTest extends DriverTestcase{
	
public String CustomerName=null;
public String newCustomerName=null;
public String existingCustomer=null;
public String Gateway;

	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_VoiceLine", priority=0)
	public void VoiceLineVService(Map<String, String> map) throws Exception {
		
		setup();
		Login.APT_Login_1(map.get("url"));
		
		newCustomerName=map.get("newCustomerCreation");
        existingCustomer=map.get("existingCustomerSelection");
        
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
              
              logger= ExtentTestManager.startTest ("CreateCustomer");
              APT_VoiceLineHelper.get().createcustomer("voiceline", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              ExtentTestManager.endTest();
              
              logger= ExtentTestManager.startTest ("selectNewCustomer"); 
              APT_VoiceLineHelper.get().selectCustomertocreateOrder("voiceline",map.get("newCustomer"));
              ExtentTestManager.endTest();
        }
        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              logger= ExtentTestManager.startTest ("selectExistingCustomer"); 
              APT_NGIN.get().selectCustomertocreateOrder("nginservice",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
              ExtentTestManager.endTest();
        }
        
		logger= ExtentTestManager.startTest ("verifyneworder");
		APT_VoiceLineHelper.get().createorderservice("voiceline", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyservicetypeselection");
		APT_VoiceLineHelper.get().verifyselectservicetype("voiceline", map.get("ServiceType"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyservicecreation");
		APT_VoiceLineHelper.get().verifyservicecreation("voiceline", map.get("ServiceIdentification"), map.get("Remarks"),  map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"), map.get("ResellerCode_Value"), map.get("ThirdPartyInternet_Checkbox"), map.get("Email"), map.get("PhoneContact"), map.get("PerformanceReporting_Checkbox"), map.get("ProactiveNotification_Checkbox"), map.get("NotificationManagementTeam_value"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyCustomerDetailsInformation");
		APT_VoiceLineHelper.get().verifyCustomerDetailsInformation("voiceline", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		APT_VoiceLineHelper.get().verifyUserDetailsInformation("voiceline", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
		ExtentTestManager.endTest();
		
//		logger= ExtentTestManager.startTest ("verifyUserDetailsInformation");
//		APT_VoiceLineHelper.get().VerifyUsersPanel("voiceline", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//				map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//				map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"), map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"), map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), 
//				map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), map.get("SiteOrders_ToBeAvailable"), map.get("SiteOrders_ToBeHidden"));
//		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyServicepanelinviewservicepage");
		APT_VoiceLineHelper.get().verifyservicepanelInformationinviewservicepage("voiceline", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("Remarks"), map.get("ResellerCode_Value"), map.get("ThirdPartyInternet_Checkbox"), map.get("PhoneContact"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyServicepanelLinks");
		APT_VoiceLineHelper.get().verifyEditService("voiceline", map.get("EditRemarks"), map.get("Remarks"), map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"), map.get("ResellerCode_Value"), map.get("ThirdPartyInternet_Checkbox"), map.get("PhoneContact"), map.get("Edit_ResellerCode"), map.get("Edit_ThirdPartyInternet_Checkbox"), map.get("Edit_ServiceEmail"), map.get("Edit_PhoneContact"), map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_ProactiveNotification_Checkbox"), map.get("Edit_NotificationManagementTeam_Drodpwon"));
		APT_VoiceLineHelper.get().verifyManageSubnetsIPv6("voiceline");
		APT_VoiceLineHelper.get().verifyShowNewInfovistaReport("voiceline");
		APT_VoiceLineHelper.get().verifyDump("voiceline");
		APT_VoiceLineHelper.get().verifyManageService("voiceline", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyManagementOptionspanel");
		APT_VoiceLineHelper.get().verifyManagementOptionspanel("voiceline", map.get("PerformanceReporting_Checkbox"), map.get("ProactiveNotification_Checkbox"), map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_ProactiveNotification_Checkbox"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyASRDevice");
		APT_VoiceLineHelper.get().verifyAddASRDevice("voiceline", map.get("IMSPopLocation_DropdownValue"));
		APT_VoiceLineHelper.get().verifyEditASRDevice("voiceline", map.get("IMSPopLocation_DropdownValue"), map.get("editASRDeviceName"), map.get("editASRManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
				map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
				map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
				map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
		APT_VoiceLineHelper.get().verifyViewASRDevice("voiceline", map.get("IMSPopLocation_DropdownValue"));
		APT_VoiceLineHelper.get().verifyViewDevicepage_Links("voiceline", map.get("ServiceIdentification"), map.get("IMSPopLocation_DropdownValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyRouterTools");
		APT_VoiceLineHelper.get().verify_Cisco_RouterTools("voiceline", map.get("IMSPopLocation_DropdownValue"), map.get("command_ipv4"), map.get("command_ipv6"), map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyRoutesPanel");
		APT_VoiceLineHelper.get().verify_CiscoVendor_AddInterface("voiceline", map.get("InterfaceName"), map.get("AddInterface_Allocate"), map.get("Configuration_Dropdownvalue"), map.get("InterfaceAddress"), map.get("VirtualTemplate"), map.get("CPEAddressRange"), map.get("LocalPreShareKey"), map.get("RemotePreShareKey"), map.get("IdentityEmail"), map.get("ServiceIdentification"));
		APT_VoiceLineHelper.get().verify_CiscoVendor_EditInterface("voiceline", map.get("InterfaceName"), map.get("Edit_InterfaceName"), map.get("Configuration_Dropdownvalue"), map.get("editASRDeviceName"), map.get("EditInterface_Allocate"), map.get("Edit_Configuration_Dropdownvalue"), map.get("Edit_InterfaceAddress"), map.get("Edit_VirtualTemplate"), map.get("Edit_CPEAddressRange"), map.get("Edit_LocalPreshareKey"), map.get("Edit_RemotePreshareKey"), map.get("Edit_IdentityEmail"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifySelectInterface");
		APT_VoiceLineHelper.get().selectInterfacelinkforDevice("voiceline", map.get("IMSPopLocation_DropdownValue"));
		
		if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
			APT_VoiceLineHelper.get().SelectInterfacetoremovefromservice("voiceline", map.get("InterfaceName"), map.get("VendorModel"));
		}else {
			System.out.println("interfaces are not removed");
		}
		
		if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
			APT_VoiceLineHelper.get().SelectInterfacetoaddwithservcie("voiceline", map.get("InterfaceName"), map.get("ServiceIdentification"));
		}else {
			System.out.println("Interfaces are not added");
		}
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("FetchInterfaces");
		APT_VoiceLineHelper.get().verifyFetchInterface("voiceline", map.get("IMSPopLocation_DropdownValue"), map.get("ServiceIdentification"), map.get("editASRDeviceName"), map.get("InServiceStatus"), map.get("InMaintenanceStatus"), map.get("InterfaceName"), map.get("Edit_InterfaceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifySiteOrder");
		APT_VoiceLineHelper.get().addTrunkSiteOrder("voiceline", map.get("TrunkGroupOrder"), map.get("TrunkGroupOrderNumber"));
		APT_VoiceLineHelper.get().editSiteOrder("voiceline", map.get("TrunkGroupOrderNumber"), map.get("edit_TrunkGroupOrder"), map.get("edit_TrunkGroupOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyAddTrunk");
		newCustomerName=map.get("newCustomerCreation");
        existingCustomer=map.get("existingCustomerSelection");
        APT_VoiceLineHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("voiceline", map.get("TrunkGroupOrderNumber"));
		APT_VoiceLineHelper.get().addTrunk("voiceline", map.get("newCustomerCreation"), map.get("newCustomer"), map.get("existingCustomer"), map.get("ServiceIdentification"), map.get("BillingCountry"), map.get("CDRdelivery"),
				map.get("gateway"), map.get("quality"), map.get("SIPURI"), map.get("ResellerID_Value"), map.get("ipAddresstype"), map.get("SIPsignallingPort"),
				map.get("ThirdPartyInternet"), map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("callAdmissionControl"),
				map.get("callrateLimitselection"), map.get("PSXmanualConfigvalue"), map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), 
				map.get("callrateLimiteValue"), map.get("SBCmanualconfigValue"), map.get("COSProfile_Value"), map.get("LANRange_Value"), map.get("NumberPorting_Checkbox"), map.get("CLIPScreeningandCLIRperCall_radio"), 
				map.get("CLIRPermanent_radio"), map.get("ClipNoScreening_radio"), map.get("ClipMainNumber_radio"), map.get("PresentationNumbers_radio"), map.get("PresentationNumber_Value"), map.get("CustomerDefaultNumber_Value"), 
				map.get("EgressNumberFormat_DropdownValue"), map.get("IncomingRoutingOnDDILevel_Checkbox"), map.get("PSXmanualConfigTG_value"), map.get("PSXmanualConfigDDI_value"), map.get("DRusingTDM_checkbox"), 
				map.get("Codec_Value"), map.get("FaxDiversionNumber_Value"), map.get("PartialNumberReplacement_Checkbox"), map.get("cpemanualconfig_checkbox"), map.get("vlanTag_FRASBC_value"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("viewTrunk");
		APT_VoiceLineHelper.get().viewTrunk_Primary("voiceline", map.get("newCustomerCreation"), map.get("newCustomer"), map.get("existingCustomer"), map.get("ServiceIdentification"), map.get("BillingCountry"), map.get("CDRdelivery"),
				map.get("gateway"), map.get("quality"), map.get("SIPURI"), map.get("ResellerID_value"), map.get("ipAddresstype"), map.get("SIPsignallingPort"), map.get("ThirdPartyInternet"), 
				map.get("vlanTag"),map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("callAdmissionControl"), map.get("callrateLimitselection"), map.get("PSXmanualConfigvalue"), 
				map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), map.get("callrateLimiteValue"), map.get("SBCmanualconfigValue"), map.get("COSProfile_Value"), 
				map.get("LANRange_Value"), map.get("NumberPorting_Checkbox"), map.get("CLIPScreeningandCLIRperCall_radio"), map.get("CLIRPermanent_radio"), map.get("ClipNoScreening_radio"), 
				map.get("ClipMainNumber_radio"), map.get("PresentationNumbers_radio"), map.get("PresentationNumber_Value"), map.get("CustomerDefaultNumber_Value"), map.get("EgressNumberFormat_DropdownValue"), 
				map.get("IncomingRoutingOnDDILevel_Checkbox"), map.get("PSXmanualConfigTG_value"), map.get("PSXmanualConfigDDI_value"), map.get("DRusingTDM_checkbox"), map.get("Codec_Value"), 
				map.get("FaxDiversionNumber_Value"), map.get("PartialNumberReplacement_Checkbox"), map.get("cpemanualconfig_checkbox"));
		ExtentTestManager.endTest();
		
	    logger= ExtentTestManager.startTest ("verifyEditTrunk");
		APT_VoiceLineHelper.get().editTrunk("voiceline", map.get("newCustomerCreation"), map.get("newCustomer"), map.get("existingCustomer"), map.get("ServiceIdentification"), map.get("edit_SIPURI"), map.get("edit_SIPsignallingPort"), map.get("edit_ipAddresstype"), map.get("edit_BillingCountry"), map.get("edit_CDRdelivery"),
				map.get("edit_resellerID_value"), map.get("gateway"), map.get("edit_gateway"), map.get("edit_quality"),  map.get("edit_cosprofile_value"), map.get("edit_lanrange_value"),
				map.get("edit_CLIPScreeningandCLIRperCall"), map.get("edit_clirPermanent"), map.get("edit_clipNoScreening"), map.get("edit_clipMainNumber"), map.get("edit_presentationNumbers"),
				map.get("edit_presentationnumber_value"), map.get("edit_customerdefaultnumber_value"), map.get("edit_egressnumberformat_dropdownvalue"), map.get("edit_callAdmissionControl"), map.get("edit_callLimit"), 
				map.get("edit_limitNumber"), map.get("edit_callrateLimit"), map.get("edit_callrateLimiteValue"), map.get("edit_incomingroutingonDDIlevel_checkbox"), map.get("edit_PSXmanualConfigTG_value"), map.get("edit_PSXmanualConfigDDI_value"), 
				map.get("edit_SBCmanualconfigValue"), map.get("edit_GSXmanualConfigvalue"), map.get("edit_cpemanualconfig_checkbox"), map.get("edit_DRusingTDM_checkbox"), map.get("edit_codec_value"), map.get("edit_faxdiversionnumber_value"), 
				map.get("edit_partialnumberreplacement_checkbox"), map.get("edit_thirdpartyinternet"), map.get("edit_subInterfaceSlot"), map.get("edit_vlanTag"), map.get("edit_vlanTag_FRASBC_value"), 
				map.get("edit_signallingZone"), map.get("edit_numberporting_checkbox"));
		APT_VoiceLineHelper.get().clickOnBreadCrumb("voiceline", map.get("ServiceIdentification"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("AddTrunk_Resilient");
		APT_VoiceLineHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("voiceline", map.get("TrunkGroupOrderNumber"));
		APT_VoiceLineHelper.get().addResilienttrunk("voiceline", map.get("newCustomerCreation"), map.get("newCustomer"), map.get("existingCustomer"), map.get("ServiceIdentification"), map.get("Resilient_Country"), map.get("Resilient_CDRdelivery"),
				map.get("Resilient_Gateway"), map.get("Resilient_Quality"), map.get("SIPURI"), map.get("Resilient_ResellerID_value"), map.get("ipAddresstype"), map.get("SIPsignallingPort"),
				map.get("ThirdPartyInternet"), map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("callAdmissionControl"),
				map.get("callrateLimitselection"), map.get("PSXmanualConfigvalue"), map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), 
				map.get("callrateLimiteValue"), map.get("SBCmanualconfigValue"), map.get("COSProfile_Value"), map.get("LANRange_Value"), map.get("NumberPorting_Checkbox"), map.get("CLIPScreeningandCLIRperCall_radio"), 
				map.get("CLIRPermanent_radio"), map.get("ClipNoScreening_radio"), map.get("ClipMainNumber_radio"), map.get("PresentationNumbers_radio"), map.get("PresentationNumber_Value"), map.get("CustomerDefaultNumber_Value"), 
				map.get("EgressNumberFormat_DropdownValue"), map.get("IncomingRoutingOnDDILevel_Checkbox"), map.get("PSXmanualConfigTG_value"), map.get("PSXmanualConfigDDI_value"), map.get("DRusingTDM_checkbox"), 
				map.get("Codec_Value"), map.get("FaxDiversionNumber_Value"), map.get("PartialNumberReplacement_Checkbox"), map.get("cpemanualconfig_checkbox"), map.get("vlanTag_FRASBC_value"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("ServiceLevel Synchronize");
		APT_VoiceLineHelper.get().verifySynchronize("voiceline");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("verifyAddVoiceCPEDevice");
		APT_VoiceLineHelper.get().verifyAddVoiceCPEDevice("voiceline", map.get("VoiceCPEDeviceName"), map.get("VoiceCPE_VendorModel"), map.get("VoiceCPE_ManagementAddress"), map.get("VoiceCPE_Country"), map.get("voiceCPE_ExistingCity"), map.get("voiceCPE_ExistingCityValue"), 
				map.get("voiceCPE_ExistingSite"), map.get("voiceCPE_Existing SiteValue"), map.get("voiceCPE_ExistingPremise"), map.get("voiceCPE_Existing PremiseValue"), map.get("voiceCPE_NewCity"), map.get("voiceCPE_NewCityName"), map.get("voiceCPE_NewCityCode"), 
				map.get("voiceCPE_NewSiteName"), map.get("voiceCPE_NewSiteCode"), map.get("voiceCPE_NewPremiseName"), map.get("voiceCPE_NewPremiseCode"), map.get("voiceCPE_NewSite"), map.get("voiceCPE_NewPremise"), map.get("CPEToProvideDialTone_Checkbox"), 
				map.get("CPELinePowerRequired_Checkbox"), map.get("NumberPorting_Checkbox"), map.get("BRIPortMapping_Checkbox"), map.get("CRCSettings_DropdownValue"), map.get("NumberOfPRIPorts_DropdownValue"), map.get("NumberOfBRIPorts_DropdownValue"), 
				map.get("BRIPort1Number_value"), map.get("BRIPort2Number_value"), map.get("BRIPort3Number_value"), map.get("BRIPort4Number_value"), map.get("BRIPort5Number_value"), map.get("BRIPort6Number_value"), map.get("BRIPort7Number_value"), map.get("BRIPort8Number_value"), 
				map.get("NumberOfFXSPorts_DropdownValue"), map.get("FXSNumber1_value"), map.get("FXSNumber2_value"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("VerifyViewVoiceCPEDevice");
		APT_VoiceLineHelper.get().ViewVoiceCPEDevice("voiceline", map.get("VoiceCPEDeviceName"), map.get("VoiceCPE_VendorModel"), map.get("VoiceCPE_ManagementAddress"), map.get("VoiceCPE_Country"), 
															map.get("CPEToProvideDialTone_Checkbox"), map.get("CPELinePowerRequired_Checkbox"), map.get("NumberPorting_Checkbox"), map.get("BRIPortMapping_Checkbox"), map.get("CRCSettings_DropdownValue"), 
															map.get("NumberOfPRIPorts_DropdownValue"), map.get("NumberOfBRIPorts_DropdownValue"), map.get("NumberOfFXSPorts_DropdownValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("VerifyEditVoiceCPEDevice");
		APT_VoiceLineHelper.get().EditVoiceCPEDevice("voiceline", map.get("Edit_VoiceCPEDeviceName"), map.get("Edit_VoiceCPE_VendorModel"), map.get("Edit_VoiceCPE_ManagementAddress"), map.get("Edit_VoiceCPE_Country"), map.get("Edit_voiceCPE_ExistingCity"), map.get("Edit_voiceCPE_ExistingCityValue"), 
		map.get("Edit_voiceCPE_ExistingSite"), map.get("Edit_voiceCPE_ExistingSiteValue"), map.get("Edit_voiceCPE_ExistingPremise"), map.get("Edit_voiceCPE_ExistingPremiseValue"), map.get("Edit_voiceCPE_NewCity"), map.get("Edit_voiceCPE_NewCityName"), map.get("Edit_voiceCPE_NewCityCode"), 
		map.get("Edit_voiceCPE_NewSiteName"), map.get("Edit_voiceCPE_NewSiteCode"), map.get("Edit_voiceCPE_NewPremiseName"), map.get("Edit_voiceCPE_NewPremiseCode"), map.get("Edit_voiceCPE_NewSite"), map.get("Edit_voiceCPE_NewPremise"), map.get("Edit_CPEToProvideDialTone_Checkbox"), 
		map.get("Edit_CPELinePowerRequired_Checkbox"), map.get("Edit_NumberPorting_Checkbox"), map.get("Edit_BRIPortMapping_Checkbox"), map.get("Edit_CRCSettings_DropdownValue"), map.get("Edit_NumberOfPRIPorts_DropdownValue"), map.get("Edit_NumberOfBRIPorts_DropdownValue"), 
		map.get("Edit_BRIPort1Number_value"), map.get("Edit_BRIPort2Number_value"), map.get("Edit_BRIPort3Number_value"), map.get("Edit_BRIPort4Number_value"), map.get("Edit_BRIPort5Number_value"), map.get("Edit_BRIPort6Number_value"), map.get("Edit_BRIPort7Number_value"), map.get("Edit_BRIPort8Number_value"), 
		map.get("Edit_NumberOfFXSPorts_DropdownValue"), map.get("Edit_FXSNumber1_value"), map.get("Edit_FXSNumber2_value"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("addCPEdevice");
		String siteOrderNumber=null;
		if(map.get("edit_TrunkGroupOrderNumber").equalsIgnoreCase("null")) {
			
			siteOrderNumber= map.get("TrunkGroupOrderNumber");
					
		}else{
			siteOrderNumber=map.get("edit_TrunkGroupOrderNumber");
		}
		//String trunkName=APT_VoiceLineHelper.get().Edit_primarytrunkGroupname;
		APT_VoiceLineHelper.get().clickOnCPEdeviceLink("voiceline", siteOrderNumber);
		APT_VoiceLineHelper.get().addCPEdevice("voiceline", map.get("CPEdevice_routerID"), map.get("CPEdevice_vendorModel"),
				map.get("CPEdevice_managementAddress"), map.get("CPEdevice_Snmpro"), map.get("CPEdevice_Snmprw"), map.get("CPEdevice_SNMPv3Contextname"),
				map.get("CPEdevice_SNMPv3ContextEngineId") , map.get("CPEdevice_SNMPv3securityUsername"), map.get("CPEdevice_SNMPv3AutoProto"), map.get("CPEdevice_SNMPv3AuthPasswrd"),
				map.get("Country"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
				map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
				map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		APT_VoiceLineHelper.get().verifysuccessmessage("voiceline", "CPE Device added successfully");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("viewCPEdevice");
		APT_VoiceLineHelper.get().CPEdevice_clickOnViewLink("voiceline");
		APT_VoiceLineHelper.get().viewCPEdevice("voiceline", map.get("ServiceIdentification"), map.get("CPEdevice_routerID"), map.get("CPEdevice_vendorModel"),
				map.get("CPEdevice_managementAddress"), map.get("CPEdevice_Snmpro"), map.get("CPEdevice_Snmprw"), map.get("CPEdevice_SNMPv3Contextname"),
				map.get("CPEdevice_SNMPv3ContextEngineId") , map.get("CPEdevice_SNMPv3securityUsername"), map.get("CPEdevice_SNMPv3AutoProto"), map.get("CPEdevice_SNMPv3AuthPasswrd"),
				map.get("Country"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
				map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
				map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("editCPEdevice");
		APT_VoiceLineHelper.get().CPEdevice_clickOnEditLink("voiceline");
		APT_VoiceLineHelper.get().editCPEdevice("voiceline", map.get("ServiceIdentification"), map.get("editCPEdevice_routerID"), map.get("editCPEdevice_vendorModel"), map.get("editCPEdevice_managementAddress"),
				map.get("editCPEdevice_Snmpro"), map.get("editCPEdevice_Snmprw"), map.get("editCPEdevice_SNMPv3Contextname"), map.get("editCPEdevice_SNMPv3ContextEngineId"),
				map.get("editCPEdevice_SNMPv3securityUsername"), map.get("editCPEdevice_SNMPv3AuthProto"), map.get("editCPEdevice_SNMPv3AuthPasswrd"),
				map.get("editCountry"), map.get("editExistingCity"),
				map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
				map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
				map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
		ExtentTestManager.endTest();
	
		logger= ExtentTestManager.startTest ("DR using TDM Links");
		
			String siteOrderNumber1=null;
			if(map.get("edit_TrunkGroupOrderNumber").equalsIgnoreCase("null")) {
				
				siteOrderNumber1= map.get("TrunkGroupOrderNumber");
						
			}else{
				siteOrderNumber1=map.get("edit_TrunkGroupOrderNumber");
			}
		APT_VoiceLineHelper.get().clickOnViewTrunkLink("voiceline", siteOrderNumber1);
		String DRusingTDMValue= APT_VoiceLineHelper.get().DRusingTDMvalue("voiceline");
		
		if(DRusingTDMValue.equalsIgnoreCase("Yes")) {
		APT_VoiceLineHelper.get().verifyAddDRPlans("voiceline", map.get("Add_DRplanA"), map.get("Add_DRplanB"), map.get("Add_DRplanC"), map.get("Add_DRplanD"), map.get("Add_DRplanE"), map.get("rangestart_cc"), map.get("rangestart_lac"), map.get("rangestart_num"), map.get("rangefinish_cc"), map.get("rangefinish_lac"), map.get("rangefinish_num"), map.get("destinationnumber_cc"), map.get("destinationnumber_lac"), map.get("destinationnumber_num"), map.get("activate_deactivateDRplan_dropdownvalue"), map.get("edit_rangestart_cc"), map.get("edit_rangestart_lac"), map.get("edit_rangestart_num"), map.get("edit_rangefinish_cc"), map.get("edit_rangefinish_lac"), map.get("edit_rangefinish_num"), map.get("edit_destinationnumber_cc"), map.get("edit_destinationnumber_lac"), map.get("edit_destinationnumber_num"), map.get("edit_activate_deactivateDRplan_dropdownvalue"));
		APT_VoiceLineHelper.get().verifyDRPlansBulkInterface("voiceline", map.get("BulkJob_FilePath"));
		APT_VoiceLineHelper.get().verifydownloadDRplans("voiceline", map.get("DRPlans_FileName"), map.get("Browserfiles_Downloadspath"));
		}
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("VerifyDisasterRecoveryStatus");
		APT_VoiceLineHelper.get().VerifyDisasterRecoveryStatus("voiceline");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("VerifyPortGroup");
		Thread.sleep(2000);
		APT_VoiceLineHelper.get().AddPortGroup("voiceline", map.get("Prefix_DropdownValue"), map.get("RoutePriority_DropdownValue"), map.get("VoiceCPEDeviceName"), map.get("Edit_VoiceCPEDeviceName"));
		APT_VoiceLineHelper.get().ViewPortGroup("voiceline", map.get("Prefix_DropdownValue"), map.get("Edit_Prefix_DropdownValue"), map.get("RoutePriority_DropdownValue"), map.get("VoiceCPEDeviceName"), map.get("Edit_VoiceCPEDeviceName"));
		APT_VoiceLineHelper.get().EditPortGroup("voiceline", map.get("Prefix_DropdownValue"), map.get("RoutePriority_DropdownValue"), map.get("Edit_Prefix_DropdownValue"), map.get("Edit_RoutePriority_DropdownValue"), map.get("VoiceCPEDeviceName"), map.get("Edit_VoiceCPEDeviceName"));
		APT_VoiceLineHelper.get().OverflowPortGroup("voiceline", map.get("Prefix_DropdownValue"), map.get("Edit_Prefix_DropdownValue"), map.get("RoutePriority_DropdownValue"), map.get("VoiceCPEDeviceName"), map.get("Edit_VoiceCPEDeviceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("VerifyDDIRange");
		APT_VoiceLineHelper.get().AddDDIRange("voiceline",map.get("LACValue"),map.get("MainNumberValue"),map.get("RangeStartValue"),map.get("RangeEndValue"),map.get("ExtensionDigitsValue"),map.get("IncomingRouting_Checkbox"),map.get("Prefix_DropdownValue"), map.get("Edit_Prefix_DropdownValue"));
		APT_VoiceLineHelper.get().viewDDIRange("voiceline",map.get("LACValue"),map.get("MainNumberValue"),map.get("RangeStartValue"),map.get("RangeEndValue"),map.get("ExtensionDigitsValue"),map.get("ViewDDI_PSXConfig_DropdownValue"));
		APT_VoiceLineHelper.get().editDDIRange("voiceline",map.get("Edit_LACValue"),map.get("MainNumberValue"),map.get("Edit_MainNumberValue"),map.get("Edit_RangeStartValue"),map.get("Edit_RangeEndValue"),map.get("Edit_ExtensionDigitsValue"),map.get("Edit_IncomingRouting_Checkbox"),map.get("Prefix_DropdownValue"), map.get("Edit_Prefix_DropdownValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("VerifyVoiceResiliency");
		APT_VoiceLineHelper.get().VerifyVoiceResiliency("voiceline", map.get("BackupNumber_Checkbox"), map.get("OBackupNumber_Value"), map.get("BillingNumber_Value"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("CommandsExecution");
		APT_VoiceLineHelper.get().VerifyPSXcommandExecution("voiceline", map.get("PSXcommand_DropdownValue"));
		APT_VoiceLineHelper.get().VerifyGSXcommandExecution("voiceline", map.get("GSXcommand_DropdownValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("Configuration");
		APT_VoiceLineHelper.get().verifyConfiguration("voiceline", map.get("ServiceIdentification"), map.get("VoiceCPEDeviceName"), map.get("Edit_VoiceCPEDeviceName"), map.get("Configuration_dropdownValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest ("All Delete Operations");
		APT_VoiceLineHelper.get().deleteDDIRange("voiceline");
		APT_VoiceLineHelper.get().deletePortGroup("voiceline", map.get("ServiceIdentification"), map.get("Prefix_DropdownValue"), map.get("Edit_Prefix_DropdownValue"), map.get("RoutePriority_DropdownValue"), map.get("VoiceCPEDeviceName"), map.get("Edit_VoiceCPEDeviceName"));
		APT_VoiceLineHelper.get().deleteVoiceCPEDevice("voiceline");
		APT_VoiceLineHelper.get().deleteCPEDevice("voiceline");
		String siteOrderNumber2=null;
		if(map.get("edit_TrunkGroupOrderNumber").equalsIgnoreCase("null")) {
			
			siteOrderNumber2= map.get("TrunkGroupOrderNumber");
					
		}else{
			siteOrderNumber2=map.get("edit_TrunkGroupOrderNumber");
		}
		APT_VoiceLineHelper.get().deleteResilientTrunk("voiceline", siteOrderNumber2);
		APT_VoiceLineHelper.get().deleteTrunkSiteOrder("voiceline", map.get("TrunkGroupOrderNumber"));
		APT_VoiceLineHelper.get().deleteASRDevice("voiceline", map.get("IMSPopLocation_DropdownValue"));
		APT_VoiceLineHelper.get().deleteService("voiceline");
		ExtentTestManager.endTest();
	}
		
}
