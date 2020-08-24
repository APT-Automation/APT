package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DataReader_PK;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.LogStatus;

public class APT_VOIPAccessTest extends DriverTestcase{
	public String CustomerName=null;
	
APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass =DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=1)
	public void VOIPAccessTestCases(Map<String, String> map) throws Exception {
		
		setup();	
		Login.APT_Login_1(map.get("url"));

		
		System.out.println("TC-01");
		String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
        		//New Customer Creation
              APT_VOIPHelper.get().createcustomer("voipservice", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              
              DriverTestcase.logger = DriverTestcase.extent.startTest("selectNewCustomer");
              APT_VOIPHelper.get().selectCustomertocreateOrder("voipservice",map.get("newCustomer"));
              extent.endTest(logger);
              //extent.flush();
              
        }else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              DriverTestcase.logger = DriverTestcase.extent.startTest("selectExistingCustomer"); 
              APT_VOIPHelper.get().selectCustomertocreateOrder("voipservice",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
              extent.endTest(logger);
              //extent.flush();
        }
        
        
        
        
        System.out.println("TC-02");
        DriverTestcase.logger = DriverTestcase.extent.startTest("verifyNewOrderCreationOrExistingOrderSelection");
        APT_VOIPHelper.get().createorderservice("voipservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
        extent.endTest(logger);
        //extent.flush();
	
      
        
        
        System.out.println("TC-03");
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyServiceTypeSelection");
		APT_VOIPHelper.get().verifyselectservicetype("voipservice", map.get("ServiceType"));
		extent.endTest(logger);
		//extent.flush();
		
		

		
		
		System.out.println("TC-04");
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyServiceCreation");
		APT_VOIPHelper.get().verifyingservicecreation("voipservice", map.get("ServiceIdentification"), map.get("ResellerCode"),map.get("Remarks"),map.get("EmailService"),map.get("PhoneService"), map.get("ManageService"), map.get("SyslogEventView") ,map.get("ServiceStatusView"), map.get("RouterConfigurationView"),	map.get("PerformanceReporting"),map.get("ProactiveNotification"), map.get("NotificationManagementTeam"), map.get("DialUserAdministration"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"));
		extent.endTest(logger);
		//extent.flush();
		
		
		
		
		System.out.println("TC-05");
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyCustomerDetailsInformation");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyCustomerDetailsInformation("voipservice", map.get("existingCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		//extent.endTest(logger);
		//extent.flush();
		
		
		
		
		System.out.println("TC-06");
		DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyEditOrderChangeOrderFunction");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		//APT_VOIPHelper.get().verifyorderpanelinformation_Existingorder("voipservice", map.get("ExistingOrderService"), map.get("ExistingOrderNumber"), map.get("ExistingRFIREQNumber"));
		APT_VOIPHelper.get().verifyorderpanelinformation_Neworder("voipservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		APT_VOIPHelper.get().verifyorderpanel_editorder("voipservice", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
		APT_VOIPHelper.get().verifyorderpanel_changeorder("voipservice", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));	
		//extent.endTest(logger);
		//extent.flush();
	
	
		
		
		System.out.println("TC-07");
		DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyServiceInfoEditSeviceFunction");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyservicepanelInformationinviewservicepage("voipservice", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ResellerCode") , map.get("Remarks") , map.get("EmailService") , map.get("PhoneService"));
		APT_VOIPHelper.get().editService("voipservice", map.get("EditRemarks"), map.get("Remarks"), map.get("ChangeOrder_OrderNumber"),
				map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		//extent.endTest(logger);
		//extent.flush();
		
		
		
		
		System.out.println("TC-08");
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyManageService_ManageSubnet_IPv6");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().manageSubnet_viewServicepage("voipservice");
		APT_VOIPHelper.get().manageServiceFunctionTest("voipservice", map.get("ChangeOrder_OrderNumber"),map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		//		extent.endTest(logger);
		//		extent.flush();

		
		
		
		System.out.println("TC-09");
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDumpFunction");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyDumpFunctionInviewServicepage("voipservice");
			//		extent.endTest(logger);
			//		extent.flush();
		
		
		
		System.out.println("TC-10");
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyManagementOptionspanel");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyManagementOptionspanel("voipservice",map.get("ManageService"), map.get("SyslogEventView"), map.get("ServiceStatusView"),
			map.get("RouterConfigurationView"), map.get("PerformanceReporting"), map.get("ProactiveNotification"),map.get("NotificationManagementTeam"), map.get("DialUserAdministration"));
			//		extent.endTest(logger);
			//		extent.flush();
	
	
	
	
		
		
		
		
//		/*	*********************************************************************************
//		 	*																				*
//		 	*								MAS Switch										*
//		 	*																				*
//		 	*********************************************************************************
//		 */

		 
		
//		System.out.println("TC-11");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddMASswitchFunction");
//		APT_VOIPHelper.get().searchorder("voipservice", map.get("MAS_ServiceIdentification"));
//		APT_VOIPHelper.get().verifyAddMASswitch("voipservice", map.get("MAS_IMSPOPLocation"));
//
//		
//		
//		System.out.println("TC-12");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddedMASswitchInformation");
//		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("MAS_ServiceIdentification"));
//		APT_VOIPHelper.get().navigateToViewDevicePage_MAS("voipservice", map.get("MAS_DeviceName"));
//		APT_VOIPHelper.get().verifyAddedMASswitchInformation_View("voipservice", map.get("MAS_IMSPOPLocation"));
//
//		
//		
//		
//		
//		System.out.println("TC-13");	
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEditMasSwitchFunction");
//		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("MAS_ServiceIdentification"));
//		//**APT_VOIPHelper.get().navigateToViewDevicePage_MAS("voipservice", map.get("MAS_DeviceName"));
//		APT_VOIPHelper.get().verifyEditMASswitchFunction("voipservice", map.get("MAS_ServiceIdentification"),map.get("MAS_DeviceName_Edit"), map.get("MAS_VendorModelEdit"), map.get("MAS_ManagementAddressEdit"),map.get("MAS_SnmproEdit"),map.get("MAS_CountryEdit"),map.get("MAS_CityEdit"), map.get("MAS_SiteEdit"), map.get("MAS_PremiseEdit"));
//
//		
//		
//		System.out.println("TC-14");	
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFetchDeviceInterfaceFunction_MAS");
//		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("MAS_ServiceIdentification"));
//		//**APT_VOIPHelper.get().navigateToViewDevicePage_MAS("voipservice", map.get("MAS_DeviceName"));
//		APT_VOIPHelper.get().veriyFetchDeviceInterfacesFunction_MAS("voipservice",map.get("MAS_ServiceIdentification"), map.get("MAS_ServiceStatusChangeRequired"));
//
//		
//		System.out.println("TC-15");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyRouterToolFunction_MAS");
//		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("MAS_ServiceIdentification"));
//		//APT_VOIPHelper.get().navigateToViewServicePageByServiceBreadcumLink_MAS("voipservice", "MAS_ServiceIdentification");
//		//APT_VOIPHelper.get().navigateToViewDevicePage_MAS("voipservice", map.get("MAS_DeviceName"));
//		APT_VOIPHelper.get().navigateToDevicePageFromManageColtNetwork_MAS("voipservice", "MAS_DeviceName");
//		APT_VOIPHelper.get().routerPanel_MAS("voipservice", map.get("MAS_CommandIPV4"), map.get("MAS_CommandIPV6"),
//				map.get("MAS_vrf_Ipv4"), map.get("MAS_vrf_Ipv6"));
//		
//		
//		 
//		
//			////ADD INTERFACE for MAS Switch  ///
//			System.out.println("TC-16");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddInterfaceFunction_MAS");
//			//**APT_VOIPHelper.get().searchorder("voipservice", map.get("MAS_ServiceIdentification"));
//			//APT_VOIPHelper.get().navigateToViewDevicePage_MAS("voipservice", map.get("MAS_DeviceName"));
//			//**APT_VOIPHelper.get().navigateToDevicePageFromManageColtNetwork_MAS("voipservice", "MAS_DeviceName");
//			APT_VOIPHelper.get().verifyAddInterfaceFunction_MAS("voipservice",map.get("MAS_AccessMedia"),map.get("MAS_Network"),map.get("MAS_HSRPBGP"),
//					map.get("MAS_GenerateConfiguration"), map.get("MAS_Interface"), map.get("MAS_InterfaceAddressRange"), map.get("MAS_InterfaceAddressMask"),
//					map.get("MAS_HSRPIP"), map.get("MAS_InterfaceAddressRangeIPV6"), map.get("MAS_HSRPIPv6Address"), map.get("MAS_PrimaryIPv6onMas1"),
//					map.get("MAS_SecondaryIPv6onMas2"), map.get("MAS_GroupNumber"), map.get("MAS_Link"), map.get("MAS_VLANID"),
//					map.get("MAS_IVManagement"), map.get("MAS_Configuration"), map.get("MAS_HSRPTrackInterface"),map.get("MAS_HSRPAuthentication"));
//
//			
//			
//			System.out.println("TC-17");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEditInterfaceFunction_MAS");
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("MAS_ServiceIdentification"));
//			//**APT_VOIPHelper.get().verifyAddMASswitch("voipservice", map.get("MAS_IMSPOPLocation"));
//			APT_VOIPHelper.get().verifyEditInterfaceFunction_MAS("voipservice",map.get("MAS_ServiceIdentification"), map.get("MAS_AccessMediaEdit"),map.get("MAS_NetworkEdit"),map.get("MAS_HSRPBGPEdit"),
//					map.get("MAS_GenerateConfigurationEdit"), map.get("MAS_InterfaceEdit"), map.get("MAS_InterfaceAddressRangeEdit"), map.get("MAS_InterfaceAddressMaskEdit"),
//					map.get("MAS_HSRPIPEdit"), map.get("MAS_InterfaceAddressRangeIPV6Edit"), map.get("MAS_HSRPIPv6AddressEdit"), map.get("MAS_PrimaryIPv6onMas1Edit"),
//					map.get("MAS_SecondaryIPv6onMas2Edit"), map.get("MAS_GroupNumberEdit"), map.get("MAS_LinkEdit"), map.get("MAS_VLANIDEdit"),
//					map.get("MAS_IVManagementEdit"), map.get("MAS_ConfigurationEdit"), map.get("MAS_HSRPTrackInterfaceEdit"),map.get("MAS_HSRPAuthenticationEdit"));
//
//			
//			
//			
//			System.out.println("TC-18");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyConfigureInterfaceFunction_MAS");
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("MAS_ServiceIdentification"));
//			APT_VOIPHelper.get().verifyConfigureInterfaceFunction_MAS("voipservice",map.get("MAS_ServiceIdentification"), map.get("MAS_GenerateConfiguration"), map.get("MAS_Configuration"));
//
//			
//
//			System.out.println("TC-20");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeleteInterfaceFunction_MAS");
//				APT_VOIPHelper.get().searchorder("voipservice", map.get("MAS_ServiceIdentification"));
//				//**APT_VOIPHelper.get().verifyAddMASswitch("voipservice", map.get("MAS_IMSPOPLocation"));
//				APT_VOIPHelper.get().verifyDeleteInterfaceFunction_MAS("voipservice",map.get("MAS_ServiceIdentification"));
//
//				
//				
//			System.out.println("TC-21");	
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeleteDeviceFunction_MAS");
//			APT_VOIPHelper.get().verifyDeleteDeviceFunction_MAS("voipservice",map.get("MAS_ServiceIdentification") );
//			
//		
//
//		
//		
//		
//		
//		
//		
//		
//
//		/*	*********************************************************************************
//			*																				*
//			*				Provider Equipment (PE)											*
//			*																				*
//			*********************************************************************************
//		*/
//
//		//Working
//		System.out.println("TC-22");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddPEDeviceFunction");
//		APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//		APT_VOIPHelper.get().verifyAddPEDevice("voipservice", map.get("PE_IMSPOPLocation"));
//
//				
//		
//		System.out.println("TC-23");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddedPEDeviceInformation");
//		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//		APT_VOIPHelper.get().navigateToViewDevicePage_PE("voipservice", "PE_DeviceName");
//		APT_VOIPHelper.get().verifyAddedPEDeviceInformation_View("voipservice", map.get("PE_IMSPOPLocation"));
//		
//		
//		
//		System.out.println("TC-24");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEditPEDeviceFunction");
//		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//		//*APT_VOIPHelper.get().navigateToViewDevicePage_PE("voipservice", "PE_DeviceName");
//		APT_VOIPHelper.get().verifyEditPEDeviceFunction("voipservice", map.get("PE_ServiceIdentification"),map.get("PE_DeviceName_Edit"),map.get("PE_VendorModelEdit"), map.get("PE_ManagementAddressEdit"),map.get("PE_SnmproEdit"),map.get("PE_CountryEdit"),map.get("PE_CityEdit"), map.get("PE_SiteEdit"), map.get("PE_PremiseEdit"));
//
//		
//		
//		
//		System.out.println("TC-25");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFetchDeviceInterfaceFunction_PE");
//		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//		//**APT_VOIPHelper.get().navigateToViewDevicePage_PE("voipservice", "PE_DeviceName");
//		APT_VOIPHelper.get().verifFetchDeviceInterfacesFunction_PE("voipservice",map.get("PE_ServiceIdentification"), map.get("PE_ServiceStatusChangeRequired"));
//
//		
//		
//		
//		System.out.println("TC-26");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyRouterToolFunction_PE");
//		APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//		APT_VOIPHelper.get().navigateToViewDevicePage_PE("voipservice", "PE_DeviceName");
//		APT_VOIPHelper.get().routerPanel_PE("voipservice", map.get("PE_CommandIPV4"), map.get("PE_CommandIPV6"),
//						map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
//		
//		
//		
//		
//		
//		////////////////////////////ADD INTERFACE for MAS Switch  ///////////////////////////
//		System.out.println("TC-27");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddInterfaceFunction_PE");
//		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//		//**APT_VOIPHelper.get().navigateToViewDevicePage_PE("voipservice", "PE_DeviceName");
//		APT_VOIPHelper.get().verifyAddInterfaceFunction_PE("voipservice",map.get("PE_AccessMedia"),map.get("PE_Network"),map.get("PE_VRRPBGP"),
//			map.get("PE_GenerateConfiguration"), map.get("PE_Interface"), map.get("PE_InterfaceAddressRange"), map.get("PE_InterfaceAddressMask"),
//			map.get("PE_VRRPIP"), map.get("PE_InterfaceAddressRangeIPV6"), map.get("PE_VRRPIPv6Address"), map.get("PE_PrimaryIPv6onMas1"),
//			map.get("PE_SecondaryIPv6onMas2"), map.get("PE_GroupNumber"), map.get("PE_Link"), map.get("PE_VLANID"), map.get("PE_VRRPGroupName"), map.get("PE_VRF"),
//			map.get("PE_IVManagement"), map.get("PE_Configuration"), map.get("PE_VRRPTrackInterface"),map.get("PE_VRRPAuthentication"));
//
//		
//		
//
//			
//			System.out.println("TC-28");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEditInterfaceFunction_PE");
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//			APT_VOIPHelper.get().verifyEditInterfaceFunction_PE("voipservice",map.get("PE_ServiceIdentification"), map.get("PE_AccessMediaEdit"),map.get("PE_NetworkEdit"),map.get("PE_VRRPBGPEdit"),
//					map.get("PE_GenerateConfigurationEdit"), map.get("PE_InterfaceEdit"), map.get("PE_InterfaceAddressRangeEdit"), map.get("PE_InterfaceAddressMaskEdit"),
//					map.get("PE_VRRPIPEdit"), map.get("PE_InterfaceAddressRangeIPV6Edit"), map.get("PE_VRRPIPv6AddressEdit"), map.get("PE_PrimaryIPv6onMas1Edit"),
//					map.get("PE_SecondaryIPv6onMas2Edit"), map.get("PE_GroupNumberEdit"), map.get("PE_LinkEdit"), map.get("PE_VLANIDEdit"), map.get("PE_VRRPGroupNameEdit"), map.get("PE_VRFEdit"),
//					map.get("PE_IVManagementEdit"), map.get("PE_ConfigurationEdit"), map.get("PE_VRRPTrackInterfaceEdit"),map.get("PE_VRRPAuthenticationEdit"));
//
//
//			
//			System.out.println("TC-29");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyConfigureInterfaceFunction_PE");
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//			//**APT_VOIPHelper.get().verifyAddPEDevice("voipservice", map.get("PE_IMSPOPLocation"));
//			APT_VOIPHelper.get().verifyConfigureInterfaceFunction_PE("voipservice",map.get("PE_ServiceIdentification"), map.get("PE_GenerateConfiguration"), map.get("PE_Configuration"));
//
//			
//
//
//			System.out.println("TC-30");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeleteInterfaceFunction_PE");
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//			//**APT_VOIPHelper.get().verifyAddPEDevice("voipservice", map.get("PE_IMSPOPLocation"));
//			APT_VOIPHelper.get().verifyDeleteInterfaceFunction_PE("voipservice",map.get("PE_ServiceIdentification"));
//
//			
//			
//			
//			System.out.println("TC-31");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeleteDeviceFunction_PE");
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("PE_ServiceIdentification"));
//			APT_VOIPHelper.get().verifyDeleteDeviceFunction_PE("voipservice",map.get("PE_ServiceIdentification") );
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
//			System.out.println("TC-32");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyTrunkGroupSiteOrderFunction");
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().addTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupOrderCheckboxStatus"), map.get("TrunkGroupSiteOrderNumber"));
//			//**APT_VOIPHelper.get().editTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("TrunkGroupOrderCheckboxStatusEdit"), map.get("TrunkGroupSiteOrderNumberEdit"));
//			//**APT_VOIPHelper.get().deleteTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"));
//			//**APT_VOIPHelper.get().deleteUpdatedTrunkGroupSiteOrderNumber("voipservice",  map.get("TrunkGroupSiteOrderNumberEdit"));
//			//**APT_VOIPHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("voipservice", map.get("TrunkGroupSiteOrderNumber"));
//			
//		
//		
//			
//			System.out.println("TC-33");
//			 DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddTrunkFunction");
//			//*APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
//			//*APT_VOIPHelper.get().addTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupOrderCheckboxStatus"), map.get("TrunkGroupSiteOrderNumber"));
//			APT_VOIPHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("voipservice", map.get("TrunkGroupSiteOrderNumber"));
//			APT_VOIPHelper.get().verifyAddTrunkFunction("voipservice", map.get("Trunk_CustomerName"), map.get("Trunk_ServiceIdentification") , map.get("ServiceType"), map.get("PrimaryTrunk"),
//			    map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"), map.get("ResellerID"),	map.get("gateway"), map.get("quality"), 
//			    map.get("ipAddresstype"),map.get("IPPBXRange"), map.get("IPPBXAddress"), map.get("SIPsignallingPort"), map.get("internetBasedCustomer"),
//			    map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),	map.get("TLSfield"),
//			    map.get("srtp"),  map.get("SignalingPort"),map.get("CustomerDefaultNumber"),  map.get("ReuseNetworkSelectorTable"), map.get("reuseNIFgroup"),
//			    map.get("reuseSigZonePart"), map.get("coltSignalingIP"), map.get("mediaIP"),  map.get("callAdmissionControl"), map.get("CallLimitDropdwon"),
//			    map.get("CallRateLimitCheckboxSelection"), map.get("limitNumber"), map.get("callrateLimiteValue"),map.get("sourceAddressFiltering"),
//			    map.get("relSupport"), map.get("sipSessionkeepAliveTimer"),map.get("PBXTYPE"), map.get("PBXProfile"),
//			    map.get("PSXManualConfigurationTrunkGroup"), map.get("PSXManualConfigurationDDIRange"),
//			    map.get("ManualConfigurationonGSX"),  map.get("Carrier"), map.get("IPSignalingProfile"), map.get("EgressIPSignalingProfile"));
//
//		
//			
//			
//			System.out.println("TC-34");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddedTrunkInformationInviewTrunk");
//			//**APT_VOIPHelper.get().clickOnBreadCrump("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
//			APT_VOIPHelper.get().verifyAddedTrunkInformationInviewTrunk("voipservice", map.get("Trunk_CustomerName"), map.get("Trunk_ServiceIdentification") , map.get("ServiceType"), map.get("PrimaryTrunk"),
//			 map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"), map.get("ResellerID"),	map.get("gateway"), map.get("quality"), 
//			 map.get("ipAddresstype"),map.get("IPPBXRange"), map.get("IPPBXAddress"), map.get("SIPsignallingPort"), map.get("internetBasedCustomer"),
//			 map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),	map.get("TLSfield"),
//			 map.get("srtp"),  map.get("SignalingPort"),map.get("CustomerDefaultNumber"),  map.get("ReuseNetworkSelectorTable"), map.get("reuseNIFgroup"),
//			 map.get("reuseSigZonePart"), map.get("coltSignalingIP"), map.get("mediaIP"),  map.get("callAdmissionControl"), map.get("CallLimitDropdwon"),
//			 map.get("CallRateLimitCheckboxSelection"), map.get("limitNumber"), map.get("callrateLimiteValue"),map.get("sourceAddressFiltering"),
//			 map.get("relSupport"), map.get("sipSessionkeepAliveTimer"),map.get("PBXTYPE"), map.get("PBXProfile"),  map.get("PSXManualConfigurationTrunkGroup"),
//			 map.get("PSXManualConfigurationDDIRange"), map.get("ManualConfigurationonGSX"),  map.get("Carrier"), map.get("IPSignalingProfile"), 
//			 map.get("EgressIPSignalingProfile"));
//									
//
//		
//			
//		
//			System.out.println("TC-35");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("editTrunk");
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
//			APT_VOIPHelper.get().editTrunk("voipservice",map.get("Trunk_CustomerName"), map.get("Trunk_ServiceIdentification"),  map.get("trunkType"), map.get("edit_TrunkType"), map.get("edit_VOIPprotocol"), map.get("edit_BillingCountry"), map.get("edit_CDRdelivery"),
//					map.get("editPrefix"), map.get("editGateway"), map.get("editQuality"), map.get("editTrafficDirection"), map.get("edit_IpAddressType"),
//					map.get("editCarrierIPoriginating"), map.get("editCarrierIPterminating"), map.get("editSIPsignallingPort"), map.get("editSignallingTransport"),
//					map.get("edit_TLSproflile"), map.get("edit_SRTP"), map.get("edit_signallingZone"), map.get("edit_coltSignalIP"), map.get("edit_mediaIP"), map.get("edit_reuseNIFgroup"),
//					map.get("edit_reuseSigZonePart"), map.get("edit_callAdmissionControl"), map.get("edit_callLimit"), map.get("edit_limitNumber"), map.get("edit_callrateLimit"),
//					map.get("edit_callrateLimitvalue"), map.get("edit_sourceAddressFiltering"), map.get("edit_relSupport"), map.get("edit_sipSessionkeepAliveTimer"),
//					map.get("edit_internetBasedCustomer"), map.get("edit_vlantag"), map.get("edit_subInterfaceSlot"), map.get("edit_retryInvite"), map.get("edit_addressReachability"),
//					map.get("edit_routePriority"),
//					map.get("editglobalProfile_ExistingSelection"), map.get("editglobalProfile_newSelection"), map.get("editGlobalProfile_ExistingValue"),map.get("editGlobalProfile_newValue"), 
//					map.get("editLocalProfile_existingFieldSelection"), map.get("editLocalProfile_newFieldSelection"), map.get("editLocalProfile_existingvalue"), map.get("editLocalProfile_newvalue"),
//					map.get("editCOSprofile_existingFieldSelection"), map.get("editCOSprofile_newFieldSelection"), map.get("editCOSprofile_existingValue"), map.get("editCOSprofile_newValue"),
//					map.get("editPSPGname_existingFieldSelection"), map.get("editPSPGname_newFieldSelection"), map.get("editpspgName_existingValue"), map.get("editpspgName_newValue"),
//					map.get("editPrefferedPSP_existingFieldSelection"), map.get("editPrefferedPSP_newFieldSelection"), map.get("editPreferredPSP_exitingvalue"), map.get("editPreferredPSP_newvalue"),
//					map.get("editCarrier_existingFieldSelection"), map.get("editCarrier_newFieldSelection"), map.get("editCarrier_existingValue"), map.get("editCarrier_newValue"),
//					map.get("editIPsignalProfile_existingFieldSelection"), map.get("editIPsignalProfile_newFieldSelection"), map.get("editIPsignalProfile_existingValue"), map.get("editIPsignalProfile_newValue"),
//					map.get("editEgressIpsignal_existingFieldSelection"), map.get("editEgressIpsignal_newFieldSelection"), map.get("editEgressIPsignal_existingValue"), map.get("editEgressIPsignal_newValue"),
//					map.get("editInDMPMrule_existingFieldSelection"), map.get("editInDMPMrule_newFieldSelection"), map.get("editInDMPMrule_existingValue"), map.get("editInDMPMrule_newValue"),
//					map.get("editOutDMPMrule_existingFieldSelection"), map.get("editOutDMPMrule_newFieldSelection"), map.get("editOutDMPMrule_existingValue"), map.get("editOutDMPMrule_newValue"),
//					map.get("editFeatureControlprofile_existingFieldSelection"), map.get("editFeatureControlprofile_newFieldSelection"), map.get("editFeatureControlprofile_existingValue"), map.get("editFeatureControlprofile_newValue"),
//					map.get("editLocalRingBackTone_existingFieldSelection"), map.get("editLocalRingBackTone_newFieldSelection"), map.get("editLocalRingBackTone_existingValue"), map.get("editLocalRingBackTone_newValue"),
//					map.get("editCreateLowerCaseRoutervalue"), map.get("edit_PSXmanualConfigvalue"), map.get("edit_GSXmanualConfigvalue"));
//			
//		
//		
//		
//		
//			System.out.println("TC-36");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("viewTrunk_History");
//			//**APT_VOIPHelper.get().clickOnViewLink("voipservice", "trunkGroupName", "siteOrderName");
//			//**APT_VOIPHelper.get().clickOnBreadCrump("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
//			APT_VOIPHelper.get().trunkHistory("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
//		
//		
//	
//			
//			System.out.println("TC-37");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("viewTrunk_PSXQueue");
//			//**APT_VOIPHelper.get().clickOnViewLink("voipservice", "trunkGroupName", "siteOrderName");
//			//**APT_VOIPHelper.get().clickOnBreadCrump("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
//			APT_VOIPHelper.get().trunkPSXQueue("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
//		
//		
//			
//			
//			
//			System.out.println("TC-38");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("viewTrunk_GSX_PSX_SBC_configuration");
//			//**APT_VOIPHelper.get().clickOnViewLink("voipservice", "ITLNASD0E3708", "SiVA_A45");
//			//**APT_VOIPHelper.get().clickOnBreadCrump("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
//			APT_VOIPHelper.get().viewTrunk_PSX_executeConfiguration("voipservice", map.get("viewtrunk_PSXconfiguration"));
//			String Gateway=null;
//			if(map.get("editGateway").equalsIgnoreCase("null")) {
//				Gateway=map.get("gateway");
//			}else{
//				Gateway=map.get("editGateway");
//			}
//			if(Gateway.contains("SBC")) {
//				APT_VOIPHelper.get().viewTrunk_SBC_executeConfiguration("voipservice", map.get("viewtrunk_SBCconfiguration"));
//			}else{
//				APT_VOIPHelper.get().viewTrunk_GSX_executeConfiguration("voipservice", map.get("viewtrunk_GSXconfiguration"));
//			}
//		
//			
//			
//			
//			
//			
//			System.out.println("TC-39");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("SBCManuallyExecutedConfigurations");
//			String Gateway2=null;
//			if(map.get("editGateway").equalsIgnoreCase("null")) {
//				Gateway2=map.get("gateway");
//			}else{
//				Gateway2=map.get("editGateway");
//			}
//			if(Gateway2.contains("SBC")) {
//				APT_VOIPHelper.get().addSBC_manualExecutionConfig("voipservice", map.get("SBCmanualConfigvalue"));
//				APT_VOIPHelper.get().verifySBCfileAdded("voipservice");
//				APT_VOIPHelper.get().editSBC_manualExecutionConfig("voipservice", map.get("editSBCmanualConfigvalue"));
//				APT_VOIPHelper.get().deleteSBC_manualExecutionConfig("voipservice");
//			}else {
//				DriverTestcase.logger.log(LogStatus.INFO, "'SBC Manual Execution Configuration' panel will not display, if 'SBC' gateway not selected");
//			}
//		
//		
//		
//		
//			System.out.println("TC-40");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("PSXManuallyExecutedConfigurations");
//			APT_VOIPHelper.get().addPSX_manualExecutionConfig("voipservice", map.get("PSXmanualConfigValue"));
//			APT_VOIPHelper.get().verifyPSXfileAdded("voipservice");
//			APT_VOIPHelper.get().editPSX_manualExecutionConfig("voipservice", map.get("editPSXmanualConfigValue"));
//			APT_VOIPHelper.get().deletePSX_manualExecutionConfig("voipservice");
//		
//
//		
//			System.out.println("TC-41");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("GSXManuallyExecutedConfigurations");
//			String Gateway3=null;
//			if(map.get("editGateway").equalsIgnoreCase("null")) {
//				Gateway3=map.get("gateway");
//			}else{
//				Gateway3=map.get("editGateway");
//			}
//			if(Gateway3.contains("SBC")) {
//				DriverTestcase.logger.log(LogStatus.INFO, "'GSX Manual Execution Configuration' panel will not display, if 'SBC' gateway is selected ");
//			}else{
//				APT_VOIPHelper.get().addGSX_manualExecutionConfig("voipservice", map.get("GSXmanualConfigValue"));
//				APT_VOIPHelper.get().verifyGSXfileAdded("voipservice");
//				APT_VOIPHelper.get().editGSX_manualExecutionConfig("voipservice", map.get("editGSXmanualConfigValue"));
//				APT_VOIPHelper.get().deleteGSX_manualExecutionConfig("voipservice");
//			}
//			
//	
//
//				
//			
//			
//			
//		/*System.out.println("TC-42");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyTrunkAndTrunkSiteOrderDeletion");
//		APT_VOIPHelper.get().deleteTrunk("voipservice",map.get("Trunk_ServiceIdentification") );
//		APT_VOIPHelper.get().deleteTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"));
//		*/
//		
//			
//			
//
//		
//		
//		
//		
//		/*	*********************************************************************************
//		 *																					*
//		 *						Customer Premise Equipment (CPE)							*
//		 *																					*
//		 ************************************************************************************
//		 */
//	
//			System.out.println("TC-43");
//			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddCPEDeviceFunction");
//			Thread.sleep(2000);
//			APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//			APT_VOIPHelper.get().verifyAddCPEDeviceFunction("voipservice", map.get("Trunk_ServiceIdentification"), map.get("TrunkGroupSiteOrderNumber"),  map.get("ExistingTrunkName"),  map.get("AddNewCPEDevice"),  map.get("AddExistingCPEDevice"),  map.get("CPE_RouterID"), map.get("CPE_DeviceName"),  map.get("CPE_VendorModel"),  map.get("CPE_ManagementAddress"),  map.get("CPE_Snmpro"),map.get("CPE_Snmprw"),  map.get("CPE_SnmpV3ContextName"),  map.get("CPE_SnmpV3ContextEngineID"), map.get("CPE_SnmpV3SecurityUsername"),  map.get("CPE_SnmpV3AuthProto"),  map.get("CPE_SnmpV3AuthPassword"),  map.get("CPE_Country"),  map.get("CPE_City"),  map.get("CPE_Site"), map.get("CPE_Premise")); 
//		
//		
//			
//			
//		System.out.println("TC-44");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddedCPEDeviceInformation");
//		APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//		APT_VOIPHelper.get().verifyAddedCPEDeviceInformation_View("voipservice", map.get("Trunk_ServiceIdentification"), map.get("TrunkGroupSiteOrderNumber"),  map.get("ExistingTrunkName"),  map.get("AddNewCPEDevice"),  map.get("AddExistingCPEDevice"),  map.get("CPE_RouterID"), map.get("CPE_DeviceName"),  map.get("CPE_VendorModel"),  map.get("CPE_ManagementAddress"),  map.get("CPE_Snmpro"),map.get("CPE_Snmprw"),  map.get("CPE_SnmpV3ContextName"),  map.get("CPE_SnmpV3ContextEngineID"), map.get("CPE_SnmpV3SecurityUsername"),  map.get("CPE_SnmpV3AuthProto"),  map.get("CPE_SnmpV3AuthPassword"),  map.get("CPE_Country"),  map.get("CPE_City"),  map.get("CPE_Site"), map.get("CPE_Premise")); 
//		
//		
//		
//		System.out.println("TC-45");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEditCPEDeviceFunction");
//		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//		APT_VOIPHelper.get().verifyEditCPEDeviceFunction("voipservice", map.get("Trunk_ServiceIdentification"), map.get("CPE_RouterIDEdit"), map.get("CPE_DeviceNameEdit"), map.get("CPE_VendorModelEdit"), map.get("CPE_ManagementAddressEdit"),
//				map.get("CPE_SnmproEdit"), map.get("CPE_SnmprwEdit"), map.get("CPE_SnmpV3ContextNameEdit"), map.get("CPE_SnmpV3ContextEngineIDEdit"), map.get("CPE_SnmpV3SecurityUsernameEdit"),
//				map.get("CPE_SnmpV3AuthProtoEdit"), map.get("CPE_SnmpV3AuthPasswordEdit"),	map.get("CPE_CountryEdit"), map.get("CPE_CityEdit"), map.get("CPE_SiteEdit"), map.get("CPE_PremiseEdit"));
//	
//		
//		
//		
//		System.out.println("TC-46");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFetchDeviceInterfaceFunction_CPE");
//		//APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//		APT_VOIPHelper.get().veriyFetchDeviceInterfacesFunction_CPE("voipservice",map.get("Trunk_ServiceIdentification"), map.get("CPE_ServiceStatusChangeRequired"));
//		
//		
//		
//		
//		System.out.println("TC-47");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyRouterToolFunction_CPE");
//		APT_VOIPHelper.get().verifyRouterToolFunction_CPE("voipservice",map.get("Trunk_ServiceIdentification"), map.get("CPE_CommandIPV4"), map.get("CPE_CommandIPV6"), map.get("CPE_ManagementAddress"));
//				
//		
//		
//		
//		System.out.println("TC-48");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeleteDeviceFunction_CPE");
//		APT_VOIPHelper.get().verifyDeleteDeviceFunction_CPE("voipservice",map.get("Trunk_ServiceIdentification") );
//				
//		
//		
//		System.out.println("TC-49");
//		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyTrunkAndTrunkSiteOrderDeletion");
//		APT_VOIPHelper.get().searchorder("voipservice", map.get("Trunk_ServiceIdentification"));
//		APT_VOIPHelper.get().navigateToViewTrunkPage("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("ExistingTrunkName"));
//		APT_VOIPHelper.get().deleteTrunk("voipservice",map.get("Trunk_ServiceIdentification") );
//		APT_VOIPHelper.get().deleteTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"));

	}
				
				
		
		
		
		
		
		
		
	

	
}
