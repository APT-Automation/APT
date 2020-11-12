package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class APT_VOIPAccessTest extends DriverTestcase{
	public String CustomerName=null;
	
	APT_Login Login=new APT_Login();
	@Test(description = "TC-01",dataProviderClass =DataReader.class, dataProvider = "Finaldatareader_VOIP", priority=1)
	public void VOIPAccessTestCases(Map<String, String> map) throws Exception {
		
		setup();	
		Login.APT_Login_1(map.get("url for the Product"));
		

		System.out.println("TC-01");
		String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
        		//New Customer Creation
        	logger= ExtentTestManager.startTest("selectNewCustomer_VOIP"); 
              APT_VOIPHelper.get().createcustomer("voipservice", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                      map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                      map.get("Fax"));
              CustomerName=map.get("newCustomer");
              
              APT_VOIPHelper.get().selectCustomertocreateOrder("voipservice",map.get("newCustomer"));
              ExtentTestManager.endTest();
              
        }else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
        	logger= ExtentTestManager.startTest("selectExistingCustomer_VOIP");
              APT_VOIPHelper.get().selectCustomertocreateOrder("voipservice",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
              ExtentTestManager.endTest();
        }
        
        
        
        
        System.out.println("TC-02");
       logger= ExtentTestManager.startTest("verifyNewOrderCreationOrExistingOrderSelection_VOIP");
        APT_VOIPHelper.get().createorderservice("voipservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
        ExtentTestManager.endTest();
	
      
        
        
        System.out.println("TC-03");
		logger= ExtentTestManager.startTest("verifyServiceTypeSelection_VOIP");
		APT_VOIPHelper.get().verifyselectservicetype("voipservice", map.get("ServiceType"));
		ExtentTestManager.endTest();
		
		
		
		
		
		
		System.out.println("TC-04");
		logger= ExtentTestManager.startTest("verifyServiceCreation_VOIP");
		APT_VOIPHelper.get().verifyingservicecreation("voipservice", map.get("ServiceIdentification"), map.get("ResellerCode"),map.get("Remarks"),map.get("EmailService"),
				map.get("PhoneService"), map.get("ManageService"), map.get("SyslogEventView") ,map.get("ServiceStatusView"), map.get("RouterConfigurationView"),
				map.get("PerformanceReporting"),map.get("ProactiveNotification"), map.get("NotificationManagementTeam"), map.get("DialUserAdministration"), 
				map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"));
		ExtentTestManager.endTest();
		
		
		
		
		System.out.println("TC-05");
		logger= ExtentTestManager.startTest("verifyCustomerDetailsInformation_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyCustomerDetailsInformation("voipservice", map.get("newCustomerCreation"), map.get("existingCustomerSelection"),map.get("newCustomer"),map.get("existingCustomer"),
				map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		ExtentTestManager.endTest();
		
		
		
		
		
		
		
		System.out.println("TC-06");
		logger= ExtentTestManager.startTest("VerifyEditOrderChangeOrderFunction_VOIP");
		APT_VOIPHelper.get().verifyorderpanel_editorder("voipservice", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
		APT_VOIPHelper.get().verifyorderpanel_changeorder("voipservice", map.get("ChangeOrder_newOrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
				map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));	
		ExtentTestManager.endTest();
		

		
		
		
		System.out.println("TC-07");
		logger= ExtentTestManager.startTest("VerifyServiceInfoEditSeviceFunction_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyservicepanelInformationinviewservicepage("voipservice", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ResellerCode"),map.get("Remarks") , map.get("EmailService") , map.get("PhoneService"));
		APT_VOIPHelper.get().editService("voipservice", map.get("ServiceIdentification"), map.get("ResellerCode"),map.get("Remarks"),map.get("EmailService"),
				map.get("PhoneService"), map.get("ManageService"), map.get("SyslogEventView") ,map.get("ServiceStatusView"), map.get("RouterConfigurationView"),
				map.get("PerformanceReporting"),map.get("ProactiveNotification"),map.get("NotificationManagementTeam"),map.get("DialUserAdministration"),map.get("ServiceType"),
				map.get("ServiceIdentification"), map.get("EditResellerCode"),map.get("EditRemarks"),map.get("EditEmailService"),map.get("EditPhoneService"),
				map.get("EditManageService"), map.get("EditSyslogEventView") ,map.get("EditServiceStatusView"),
				map.get("EditRouterConfigurationView"),map.get("EditPerformanceReporting"),map.get("EditProactiveNotification"), map.get("EditNotificationManagementTeam"),
				map.get("EditDialUserAdministration"));
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-08");
		logger= ExtentTestManager.startTest("verifyManageService_ManageSubnet_IPv6_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().manageSubnet_viewServicepage("voipservice");
		APT_VOIPHelper.get().manageServiceFunctionTest("voipservice", map.get("ChangeOrder_newOrderNumber"),map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();

		
		
		
		System.out.println("TC-09");
		logger= ExtentTestManager.startTest("verifyDumpFunction_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyDumpFunctionInviewServicepage("voipservice");
		ExtentTestManager.endTest();
		
		
		
		
		System.out.println("TC-10");
		logger= ExtentTestManager.startTest("verifyManagementOptionspanel_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyManagementOptionspanel("voipservice",map.get("ManageService"), map.get("SyslogEventView"), map.get("ServiceStatusView"),
			map.get("RouterConfigurationView"), map.get("PerformanceReporting"), map.get("ProactiveNotification"),map.get("NotificationManagementTeam"), map.get("DialUserAdministration"));
		ExtentTestManager.endTest();
	
	
	
		
		
		
		
		/*	*********************************************************************************
		 	*																				*
		 	*								MAS Switch										*
		 	*																				*
		 	*********************************************************************************
		 */

		 
		
		System.out.println("TC-11");
		logger= ExtentTestManager.startTest("verifyAddMASswitchFunction_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyAddMASswitch("voipservice", map.get("MAS_IMSPOPLocation"));
		ExtentTestManager.endTest();
		
		
		System.out.println("TC-12");
		logger= ExtentTestManager.startTest("verifyAddedMASswitchInformation_VOIP");
		APT_VOIPHelper.get().navigateToViewDevicePage_MAS("voipservice", map.get("MAS_DeviceName"));
		APT_VOIPHelper.get().verifyAddedMASswitchInformation_View("voipservice", map.get("MAS_IMSPOPLocation"));
		APT_VOIPHelper.get().testStatus_MAS("voipservice");
		ExtentTestManager.endTest();
		
		
		
		
		System.out.println("TC-13");	
		logger= ExtentTestManager.startTest("verifyEditMasSwitchFunction_VOIP");
		APT_VOIPHelper.get().verifyEditMASswitchFunction("voipservice", map.get("ServiceIdentification"),map.get("MAS_DeviceName_Edit"), map.get("MAS_VendorModelEdit"), map.get("MAS_ManagementAddressEdit"),map.get("MAS_SnmproEdit"),map.get("MAS_CountryEdit"),map.get("MAS_CityEdit"), map.get("MAS_SiteEdit"), map.get("MAS_PremiseEdit"));
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-14");	
		logger= ExtentTestManager.startTest("verifyFetchDeviceInterfaceFunction_MAS_VOIP");
		APT_VOIPHelper.get().veriyFetchDeviceInterfacesFunction_MAS("voipservice",map.get("ServiceIdentification"), map.get("MAS_ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		
		System.out.println("TC-15");
		logger= ExtentTestManager.startTest("verifyRouterToolFunction_MAS_VOIP");
		APT_VOIPHelper.get().navigateToDevicePageFromManageColtNetwork_MAS("voipservice", "MAS_DeviceName");
		APT_VOIPHelper.get().routerPanel_MAS("voipservice", map.get("MAS_CommandIPV4"), map.get("MAS_CommandIPV6"),
				map.get("MAS_vrf_Ipv4"), map.get("MAS_vrf_Ipv6"));
		ExtentTestManager.endTest();
		
		 
		
			////ADD INTERFACE for MAS Switch  ///
			System.out.println("TC-16");
			logger= ExtentTestManager.startTest("verifyAddInterfaceFunction_MAS_VOIP");
			APT_VOIPHelper.get().verifyAddInterfaceFunction_MAS("voipservice",map.get("MAS_AccessMedia"),map.get("MAS_Network"),map.get("MAS_HSRPBGP"),
					map.get("MAS_GenerateConfiguration"), map.get("MAS_Interface"), map.get("MAS_InterfaceAddressRange"), map.get("MAS_InterfaceAddressMask"),
					map.get("MAS_HSRPIP"), map.get("MAS_InterfaceAddressRangeIPV6"), map.get("MAS_HSRPIPv6Address"), map.get("MAS_PrimaryIPv6onMas1"),
					map.get("MAS_SecondaryIPv6onMas2"), map.get("MAS_GroupNumber"), map.get("MAS_Link"), map.get("MAS_VLANID"),
					map.get("MAS_IVManagement"), map.get("MAS_Configuration"), map.get("MAS_HSRPTrackInterface"),map.get("MAS_HSRPAuthentication"));
			ExtentTestManager.endTest();
			
			
			
			System.out.println("TC-17");
			logger= ExtentTestManager.startTest("verifyEditInterfaceFunction_MAS_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().verifyEditInterfaceFunction_MAS("voipservice",map.get("ServiceIdentification"), map.get("MAS_AccessMediaEdit"),map.get("MAS_NetworkEdit"),map.get("MAS_HSRPBGPEdit"),
					map.get("MAS_GenerateConfigurationEdit"), map.get("MAS_InterfaceEdit"), map.get("MAS_InterfaceAddressRangeEdit"), map.get("MAS_InterfaceAddressMaskEdit"),
					map.get("MAS_HSRPIPEdit"), map.get("MAS_InterfaceAddressRangeIPV6Edit"), map.get("MAS_HSRPIPv6AddressEdit"), map.get("MAS_PrimaryIPv6onMas1Edit"),
					map.get("MAS_SecondaryIPv6onMas2Edit"), map.get("MAS_GroupNumberEdit"), map.get("MAS_LinkEdit"), map.get("MAS_VLANIDEdit"),
					map.get("MAS_IVManagementEdit"), map.get("MAS_ConfigurationEdit"), map.get("MAS_HSRPTrackInterfaceEdit"),map.get("MAS_HSRPAuthenticationEdit"));
			ExtentTestManager.endTest();
			
			
			
			System.out.println("TC-18");
			logger= ExtentTestManager.startTest("verifyConfigureInterfaceFunction_MAS_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().verifyConfigureInterfaceFunction_MAS("voipservice",map.get("ServiceIdentification"), map.get("MAS_GenerateConfiguration"), map.get("MAS_Configuration"));
			ExtentTestManager.endTest();
			
			
			

			System.out.println("TC-20");
			logger= ExtentTestManager.startTest("verifyDeleteInterfaceFunction_MAS_VOIP");
				APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
				APT_VOIPHelper.get().verifyDeleteInterfaceFunction_MAS("voipservice",map.get("ServiceIdentification"));
				ExtentTestManager.endTest();
				
				
			System.out.println("TC-21");	
			logger= ExtentTestManager.startTest("verifyDeleteDeviceFunction_MAS_VOIP");
			APT_VOIPHelper.get().verifyDeleteDeviceFunction_MAS("voipservice",map.get("ServiceIdentification") );
			ExtentTestManager.endTest();
		

		
		
		
		
		
		
		

		/*	*********************************************************************************
			*																				*
			*				Provider Equipment (PE)											*
			*																				*
			*********************************************************************************
		*/

		System.out.println("TC-22");
		logger= ExtentTestManager.startTest("verifyAddPEDeviceFunction_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyAddPEDevice("voipservice", map.get("PE_IMSPOPLocation"));
		ExtentTestManager.endTest();
				
		
		System.out.println("TC-23");
		logger= ExtentTestManager.startTest("verifyAddedPEDeviceInformation_VOIP");
		APT_VOIPHelper.get().navigateToViewDevicePage_PE("voipservice", "PE_DeviceName");
		APT_VOIPHelper.get().verifyAddedPEDeviceInformation_View("voipservice", map.get("PE_IMSPOPLocation"));
		APT_VOIPHelper.get().testStatus_PE("voipservice");
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-24");
		logger= ExtentTestManager.startTest("verifyEditPEDeviceFunction_VOIP");
		APT_VOIPHelper.get().verifyEditPEDeviceFunction("voipservice", map.get("ServiceIdentification"),map.get("PE_DeviceName_Edit"),map.get("PE_VendorModelEdit"),
		map.get("PE_ManagementAddressEdit"),map.get("PE_SnmproEdit"),map.get("PE_CountryEdit"),
		map.get("PE_ExistingCitySelection"), map.get("PE_newCitySelection"), map.get("PE_editExistingCity"), map.get("PE_editNewCityname"), map.get("PE_editNewCityCode"),
		map.get("PE_ExistingSiteSelection"), map.get("PE_newSiteSelection"), map.get("PE_editExistingSite"), map.get("PE_editNewSitename"), map.get("PE_editNewSiteCode"),
		map.get("PE_ExistingPremiseSelection"), map.get("PE_newPremiseSelection"), map.get("PE_editExistingPremise"), map.get("PE_editNewPremiseName"), map.get("PE_editNewPremiseCode"));
		ExtentTestManager.endTest();
		
		
		System.out.println("TC-25");
		logger= ExtentTestManager.startTest("verifyFetchDeviceInterfaceFunction_PE_VOIP");
		APT_VOIPHelper.get().verifFetchDeviceInterfacesFunction_PE("voipservice",map.get("ServiceIdentification"), map.get("PE_ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-26");
		logger= ExtentTestManager.startTest("verifyRouterToolFunction_PE_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().navigateToViewDevicePage_PE("voipservice", "PE_DeviceName");
		APT_VOIPHelper.get().routerPanel_PE("voipservice", map.get("PE_CommandIPV4"), map.get("PE_CommandIPV6"),
						map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
		ExtentTestManager.endTest();
		
		
		
		
		////////////////////////////ADD INTERFACE for MAS Switch  ///////////////////////////
		System.out.println("TC-27");
		logger= ExtentTestManager.startTest("verifyAddInterfaceFunction_PE_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().navigateToViewDevicePage_PE("voipservice", "PE_DeviceName");
		APT_VOIPHelper.get().verifyAddInterfaceFunction_PE("voipservice",map.get("PE_AccessMedia"),map.get("PE_Network"),map.get("PE_VRRPBGP"),
			map.get("PE_GenerateConfiguration"), map.get("PE_Interface"), map.get("PE_InterfaceAddressRange"), map.get("PE_InterfaceAddressMask"),
			map.get("PE_VRRPIP"), map.get("PE_InterfaceAddressRangeIPV6"), map.get("PE_VRRPIPv6Address"), map.get("PE_PrimaryIPv6onMas1"),
			map.get("PE_SecondaryIPv6onMas2"), map.get("PE_GroupNumber"), map.get("PE_Link"), map.get("PE_VLANID"), map.get("PE_VRRPGroupName"), map.get("PE_VRF"),
			map.get("PE_IVManagement"), map.get("PE_Configuration"), map.get("PE_VRRPTrackInterface"),map.get("PE_VRRPAuthentication"));
		ExtentTestManager.endTest();
		
		

			
			System.out.println("TC-28");
			logger= ExtentTestManager.startTest("verifyEditInterfaceFunction_PE_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().verifyEditInterfaceFunction_PE("voipservice",map.get("ServiceIdentification"), map.get("PE_AccessMediaEdit"),map.get("PE_NetworkEdit"),map.get("PE_VRRPBGPEdit"),
					map.get("PE_GenerateConfigurationEdit"), map.get("PE_InterfaceEdit"), map.get("PE_InterfaceAddressRangeEdit"), map.get("PE_InterfaceAddressMaskEdit"),
					map.get("PE_VRRPIPEdit"), map.get("PE_InterfaceAddressRangeIPV6Edit"), map.get("PE_VRRPIPv6AddressEdit"), map.get("PE_PrimaryIPv6onMas1Edit"),
					map.get("PE_SecondaryIPv6onMas2Edit"), map.get("PE_GroupNumberEdit"), map.get("PE_LinkEdit"), map.get("PE_VLANIDEdit"), map.get("PE_VRRPGroupNameEdit"), map.get("PE_VRFEdit"),
					map.get("PE_IVManagementEdit"), map.get("PE_ConfigurationEdit"), map.get("PE_VRRPTrackInterfaceEdit"),map.get("PE_VRRPAuthenticationEdit"));
			ExtentTestManager.endTest();

			
			
			
			
			System.out.println("TC-29");
			logger= ExtentTestManager.startTest("verifyConfigureInterfaceFunction_PE_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().verifyConfigureInterfaceFunction_PE("voipservice",map.get("ServiceIdentification"), map.get("PE_GenerateConfiguration"), map.get("PE_Configuration"));
			ExtentTestManager.endTest();
			

			
			

			System.out.println("TC-30");
			logger= ExtentTestManager.startTest("verifyDeleteInterfaceFunction_PE_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().verifyDeleteInterfaceFunction_PE("voipservice",map.get("ServiceIdentification"));
			ExtentTestManager.endTest();
			
			
			
			System.out.println("TC-31");
			logger= ExtentTestManager.startTest("verifyDeleteDeviceFunction_PE_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().verifyDeleteDeviceFunction_PE("voipservice",map.get("ServiceIdentification") );
			ExtentTestManager.endTest();
//		
//		
//		
//		
//		
//		
//
//		/*	*********************************************************************************
//			*																				*
//			*						Trunk Group/Site Order Function		 					*
//			*																				*
//			*********************************************************************************
//		*/
//			
//			
//		
			System.out.println("TC-32");
			logger= ExtentTestManager.startTest("verifyTrunkGroupSiteOrderFunction_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().addTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupOrderCheckboxStatus"), map.get("TrunkGroupSiteOrderNumber"));
			//**APT_VOIPHelper.get().editTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("TrunkGroupOrderCheckboxStatusEdit"), map.get("TrunkGroupSiteOrderNumberEdit"));
			//**APT_VOIPHelper.get().deleteTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"));
			//**APT_VOIPHelper.get().deleteUpdatedTrunkGroupSiteOrderNumber("voipservice",  map.get("TrunkGroupSiteOrderNumberEdit"));
			ExtentTestManager.endTest();
		
		
			
			System.out.println("TC-33");
			 logger= ExtentTestManager.startTest("verifyAddTrunkFunction_VOIP");
			APT_VOIPHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("voipservice", map.get("TrunkGroupSiteOrderNumber"));
			APT_VOIPHelper.get().verifyAddTrunkFunction("voipservice", map.get("Trunk_CustomerName"), map.get("ServiceIdentification") , map.get("ServiceType"), map.get("PrimaryTrunk"),
			    map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"), map.get("ResellerID"),	map.get("gateway"), map.get("quality"), 
			    map.get("ipAddresstype"),map.get("IPPBXRange"), map.get("IPPBXAddress"), map.get("SIPsignallingPort"), map.get("internetBasedCustomer"),
			    map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),	map.get("TLSfield"),
			    map.get("srtp"),  map.get("SignalingPort"),map.get("CustomerDefaultNumber"),  map.get("ReuseNetworkSelectorTable"), map.get("reuseNIFgroup"),
			    map.get("reuseSigZonePart"), map.get("coltSignalingIP"), map.get("mediaIP"),  map.get("callAdmissionControl"), map.get("CallLimitDropdwon"),
			    map.get("CallRateLimitCheckboxSelection"), map.get("limitNumber"), map.get("callrateLimiteValue"),map.get("sourceAddressFiltering"),
			    map.get("relSupport"), map.get("sipSessionkeepAliveTimer"),map.get("PBXTYPE"), map.get("PBXProfile"),
			    map.get("PSXManualConfigurationTrunkGroup"), map.get("PSXManualConfigurationDDIRange"),
			    map.get("ManualConfigurationonGSX"),  map.get("Carrier"), map.get("IPSignalingProfile"), map.get("EgressIPSignalingProfile"));
			ExtentTestManager.endTest();
		
		
		
		
		
		System.out.println("TC-34");
		logger= ExtentTestManager.startTest("verifyAddedTrunkInformationInviewTrunk_VOIP");
		APT_VOIPHelper.get().verifyAddedTrunkInformationInviewTrunk("voipservice", map.get("Trunk_CustomerName"), map.get("ServiceIdentification") , map.get("ServiceType"), map.get("PrimaryTrunk"),
		 map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"), map.get("ResellerID"),	map.get("gateway"), map.get("quality"), 
		 map.get("ipAddresstype"),map.get("IPPBXRange"), map.get("IPPBXAddress"), map.get("SIPsignallingPort"), map.get("internetBasedCustomer"),
		 map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),	map.get("TLSfield"),
		 map.get("srtp"),  map.get("SignalingPort"),map.get("CustomerDefaultNumber"),  map.get("ReuseNetworkSelectorTable"), map.get("reuseNIFgroup"),
		 map.get("reuseSigZonePart"), map.get("coltSignalingIP"), map.get("mediaIP"),  map.get("callAdmissionControl"), map.get("CallLimitDropdwon"),
		 map.get("CallRateLimitCheckboxSelection"), map.get("limitNumber"), map.get("callrateLimiteValue"),map.get("sourceAddressFiltering"),
		 map.get("relSupport"), map.get("sipSessionkeepAliveTimer"),map.get("PBXTYPE"), map.get("PBXProfile"),  map.get("PSXManualConfigurationTrunkGroup"),
		 map.get("PSXManualConfigurationDDIRange"), map.get("ManualConfigurationonGSX"),  map.get("Carrier"), map.get("IPSignalingProfile"), 
		 map.get("EgressIPSignalingProfile"));
		ExtentTestManager.endTest();
		
		
		
		
		
			System.out.println("TC-35");
			logger= ExtentTestManager.startTest("verifyEditTrunkFunction_VOIP");
			APT_VOIPHelper.get().verifyEditTrunkFunction("voipservice", map.get("Trunk_CustomerName"), map.get("ServiceIdentification") , map.get("ServiceType"), map.get("PrimaryTrunkEdit"),
				    map.get("voipProtocolEdit"), map.get("BillingCountryEdit"), map.get("CDRdeliveryEdit"), map.get("ResellerIDEdit"),	map.get("gatewayEdit"), map.get("qualityEdit"), 
				    map.get("ipAddresstypeEdit"),map.get("IPPBXRangeEdit"), map.get("IPPBXAddressEdit"), map.get("SIPsignallingPortEdit"), map.get("internetBasedCustomerEdit"),
				    map.get("vlanTagEdit"), map.get("subInterfaceSlotEdit"), map.get("signallngZoneEdit"), map.get("signallingtransportProtocolEdit"),	map.get("TLSfieldEdit"),
				    map.get("srtpEdit"),  map.get("SignalingPortEdit"),map.get("CustomerDefaultNumberEdit"),  map.get("ReuseNetworkSelectorTableEdit"), map.get("reuseNIFgroupEdit"),
				    map.get("reuseSigZonePartEdit"), map.get("coltSignalingIPEdit"), map.get("mediaIPEdit"),  map.get("callAdmissionControlEdit"), map.get("CallLimitDropdwonEdit"),
				    map.get("CallRateLimitCheckboxSelectionEdit"), map.get("limitNumberEdit"), map.get("callrateLimiteValueEdit"),map.get("sourceAddressFilteringEdit"),
				    map.get("relSupportEdit"), map.get("sipSessionkeepAliveTimerEdit"),map.get("PBXTYPEEdit"), map.get("PBXProfileEdit"),
				    map.get("PSXManualConfigurationTrunkGroupEdit"), map.get("PSXManualConfigurationDDIRangeEdit"),
				    map.get("ManualConfigurationonGSXEdit"),  map.get("CarrierEdit"), map.get("IPSignalingProfileEdit"), map.get("EgressIPSignalingProfileEdit"));
			ExtentTestManager.endTest();
			
		
			
			
		
			System.out.println("TC-36");
			logger= ExtentTestManager.startTest("viewTrunk_History_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
			APT_VOIPHelper.get().trunkHistory("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
			ExtentTestManager.endTest();
		
			
			
			
			System.out.println("TC-37");
			logger= ExtentTestManager.startTest("viewTrunk_PSXQueue_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
			APT_VOIPHelper.get().trunkPSXQueue("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
			ExtentTestManager.endTest();
		
			
			
			
			System.out.println("TC-38");
			logger= ExtentTestManager.startTest("viewTrunk_GSX_PSX_SBC_configuration_VOIP");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
			APT_VOIPHelper.get().viewTrunk_PSX_executeConfiguration("voipservice", map.get("viewtrunk_PSXconfiguration"));
			
			String Gateway=null;
			if(map.get("editGateway").equalsIgnoreCase("null")) {
				Gateway=map.get("gateway");
			}else{
				Gateway=map.get("editGateway");
			}
			if(Gateway.contains("SBC")) {
				APT_VOIPHelper.get().viewTrunk_SBC_executeConfiguration("voipservice", map.get("viewtrunk_SBCconfiguration"));
			}else{
				APT_VOIPHelper.get().viewTrunk_GSX_executeConfiguration("voipservice", map.get("viewtrunk_GSXconfiguration"));
			}
			ExtentTestManager.endTest();
			
			
			
			
			
			System.out.println("TC-39");
			logger= ExtentTestManager.startTest("SBCManuallyExecutedConfigurations_VOIP");
			String Gateway2=null;
			if(map.get("editGateway").equalsIgnoreCase("null")) {
				Gateway2=map.get("gateway");
			}else{
				Gateway2=map.get("editGateway");
			}
			if(Gateway2.contains("SBC")) {
				APT_VOIPHelper.get().addSBC_manualExecutionConfig("voipservice", map.get("SBCmanualConfigvalue"));
				APT_VOIPHelper.get().verifySBCfileAdded("voipservice");
				APT_VOIPHelper.get().editSBC_manualExecutionConfig("voipservice", map.get("editSBCmanualConfigvalue"));
				APT_VOIPHelper.get().deleteSBC_manualExecutionConfig("voipservice");
			}else {
				   ExtentTestManager.getTest().log(LogStatus.INFO, "'SBC Manual Execution Configuration' panel will not display, if 'SBC' gateway not selected");
			}
			ExtentTestManager.endTest();
		
		
			
		
//			System.out.println("TC-40");
//			logger= ExtentTestManager.startTest("PSXManuallyExecutedConfigurations_VOIP");
//			APT_VOIPHelper.get().addPSX_manualExecutionConfig("voipservice", map.get("PSXmanualConfigValue"));
//			APT_VOIPHelper.get().verifyPSXfileAdded("voipservice");
//			APT_VOIPHelper.get().editPSX_manualExecutionConfig("voipservice", map.get("editPSXmanualConfigValue"));
//			APT_VOIPHelper.get().deletePSX_manualExecutionConfig("voipservice");
//			ExtentTestManager.endTest();
//
//		
//			System.out.println("TC-41");
//			logger= ExtentTestManager.startTest("GSXManuallyExecutedConfigurations_VOIP");
//			String Gateway3=null;
//			if(map.get("editGateway").equalsIgnoreCase("null")) {
//				Gateway3=map.get("gateway");
//			}else{
//				Gateway3=map.get("editGateway");
//			}
//			if(Gateway3.contains("SBC")) {
//				   ExtentTestManager.getTest().log(LogStatus.INFO, "'GSX Manual Execution Configuration' panel will not display, if 'SBC' gateway is selected ");
//
//			}else{
//				APT_VOIPHelper.get().addGSX_manualExecutionConfig("voipservice", map.get("GSXmanualConfigValue"));
//				APT_VOIPHelper.get().verifyGSXfileAdded("voipservice");
//				APT_VOIPHelper.get().editGSX_manualExecutionConfig("voipservice", map.get("editGSXmanualConfigValue"));
//				APT_VOIPHelper.get().deleteGSX_manualExecutionConfig("voipservice");
//			}
//			ExtentTestManager.endTest();
//		
			
		
        System.out.println("TC-42");
		logger= ExtentTestManager.startTest("DR using TDM Links");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
		if((map.get("DRusingTDM_checkbox").equalsIgnoreCase("Yes") || map.get("edit_DRusingTDM_checkbox").equalsIgnoreCase("Yes")) || (map.get("DRusingTDM_checkbox").equalsIgnoreCase("Yes") && map.get("edit_DRusingTDM_checkbox").equalsIgnoreCase("null")) || (map.get("DRusingTDM_checkbox").equalsIgnoreCase("No") && map.get("edit_DRusingTDM_checkbox").equalsIgnoreCase("Yes")))
		{
			APT_VOIPHelper.get().verifyAddDRPlans("voipservice", map.get("Add_DRplanA"), map.get("Add_DRplanB"), map.get("Add_DRplanC"), map.get("Add_DRplanD"), map.get("Add_DRplanE"), map.get("rangestart_cc"), map.get("rangestart_lac"), map.get("rangestart_num"), map.get("rangefinish_cc"), map.get("rangefinish_lac"), map.get("rangefinish_num"), map.get("destinationnumber_cc"), map.get("destinationnumber_lac"), map.get("destinationnumber_num"), map.get("activate_deactivateDRplan_dropdownvalue"), map.get("edit_rangestart_cc"), map.get("edit_rangestart_lac"), map.get("edit_rangestart_num"), map.get("edit_rangefinish_cc"), map.get("edit_rangefinish_lac"), map.get("edit_rangefinish_num"), map.get("edit_destinationnumber_cc"), map.get("edit_destinationnumber_lac"), map.get("edit_destinationnumber_num"), map.get("edit_activate_deactivateDRplan_dropdownvalue"));
			APT_VOIPHelper.get().verifyDRPlansBulkInterface("voipservice", map.get("BulkJob_FilePath"), map.get("ExistingTrunkName"));
			APT_VOIPHelper.get().verifydownloadDRplans("voipservice", map.get("DRPlans_FileName"), map.get("Browserfiles_Downloadspath"));
		}
		ExtentTestManager.endTest();

			
			
			
		
		/*	*********************************************************************************
		 *																					*
		 *						Customer Premise Equipment (CPE)							*
		 *																					*
		 ************************************************************************************
		 */
	
			
			
			System.out.println("TC-42");
			logger= ExtentTestManager.startTest("verifyAddCPEDeviceFunction_VOIP");
			Thread.sleep(2000);
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			APT_VOIPHelper.get().verifyAddCPEDeviceFunction("voipservice", map.get("ServiceIdentification"), map.get("TrunkGroupSiteOrderNumber"),  map.get("ExistingTrunkName"),  map.get("AddNewCPEDevice"),  map.get("AddExistingCPEDevice"),  map.get("CPE_RouterID"), map.get("CPE_DeviceName"),  map.get("CPE_VendorModel"),  map.get("CPE_ManagementAddress"),  map.get("CPE_Snmpro"),map.get("CPE_Snmprw"),  map.get("CPE_SnmpV3ContextName"),  map.get("CPE_SnmpV3ContextEngineID"), map.get("CPE_SnmpV3SecurityUsername"),  map.get("CPE_SnmpV3AuthProto"),  map.get("CPE_SnmpV3AuthPassword"),  map.get("CPE_Country"),  map.get("CPE_City"),  map.get("CPE_Site"), map.get("CPE_Premise")); 
			ExtentTestManager.endTest();
		
			
			
		System.out.println("TC-43");
		logger= ExtentTestManager.startTest("verifyAddedCPEDeviceInformation_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyAddedCPEDeviceInformation_View("voipservice", map.get("ServiceIdentification"), map.get("TrunkGroupSiteOrderNumber"),  map.get("ExistingTrunkName"),  map.get("AddNewCPEDevice"),  map.get("AddExistingCPEDevice"),  map.get("CPE_RouterID"), map.get("CPE_DeviceName"),  map.get("CPE_VendorModel"),  map.get("CPE_ManagementAddress"),  map.get("CPE_Snmpro"),map.get("CPE_Snmprw"),  map.get("CPE_SnmpV3ContextName"),  map.get("CPE_SnmpV3ContextEngineID"), map.get("CPE_SnmpV3SecurityUsername"),  map.get("CPE_SnmpV3AuthProto"),  map.get("CPE_SnmpV3AuthPassword"),  map.get("CPE_Country"),  map.get("CPE_City"),  map.get("CPE_Site"), map.get("CPE_Premise")); 
		ExtentTestManager.endTest();
		
		
		System.out.println("TC-44");
		logger= ExtentTestManager.startTest("verifyEditCPEDeviceFunction_VOIP");
		APT_VOIPHelper.get().verifyEditCPEDeviceFunction("voipservice", map.get("ServiceIdentification"), map.get("CPE_RouterIDEdit"), map.get("CPE_DeviceNameEdit"), map.get("CPE_VendorModelEdit"), map.get("CPE_ManagementAddressEdit"),
				map.get("CPE_SnmproEdit"), map.get("CPE_SnmprwEdit"), map.get("CPE_SnmpV3ContextNameEdit"), map.get("CPE_SnmpV3ContextEngineIDEdit"), map.get("CPE_SnmpV3SecurityUsernameEdit"),
				map.get("CPE_SnmpV3AuthProtoEdit"), map.get("CPE_SnmpV3AuthPasswordEdit"),	map.get("CPE_CountryEdit"), map.get("CPE_CityEdit"), map.get("CPE_SiteEdit"), map.get("CPE_PremiseEdit"));
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-45");
		logger= ExtentTestManager.startTest("verifyFetchDeviceInterfaceFunction_CPE_VOIP");
		APT_VOIPHelper.get().veriyFetchDeviceInterfacesFunction_CPE("voipservice",map.get("ServiceIdentification"), map.get("CPE_ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-46");
		logger= ExtentTestManager.startTest("verifyRouterToolFunction_CPE_VOIP");
		APT_VOIPHelper.get().verifyRouterToolFunction_CPE("voipservice",map.get("ServiceIdentification"), map.get("CPE_CommandIPV4"), map.get("CPE_CommandIPV6"), map.get("CPE_ManagementAddress"));
		ExtentTestManager.endTest();		
		
		
		
		System.out.println("TC-47");
		logger= ExtentTestManager.startTest("verifyDeleteDeviceFunction_CPE_VOIP");
		APT_VOIPHelper.get().verifyDeleteDeviceFunction_CPE("voipservice",map.get("ServiceIdentification") );
		ExtentTestManager.endTest();	
		
		
		
		System.out.println("TC-48");
		logger= ExtentTestManager.startTest("verifyTrunkAndTrunkSiteOrderDeletion_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
		APT_VOIPHelper.get().deleteTrunk("voipservice",map.get("ServiceIdentification") );
		APT_VOIPHelper.get().deleteTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"));
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-49");
		logger= ExtentTestManager.startTest("deleteServiceFunction_VOIP");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().deleteServiceFunction("voipservice", map.get("ServiceIdentification"));
		ExtentTestManager.endTest();
		
		
	}
				
	

	
}
