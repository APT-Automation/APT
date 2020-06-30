package com.saksoft.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.scripthelpers.APT_LoginHelper;

public class APT_MCS_CreateOrder_IPVPN extends DriverTestcase{
	
	
	//@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=0)
	public void CreateCustomer(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("CreateCustomer"); 
		APT_IPVPNHelper.get().createnewcustomer("ipvpnservice", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));		
	} 
	
	//@Test(description = "TC-02",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=1)
    public void choosecustomer(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("choosecustomer"); 
		APT_IPVPNHelper.get().selectCustomertocreateOrder("ipvpnservice",map.get("ChooseCustomerToBeSelected"),map.get("Name1"),map.get("Name2"));
			//APT_VOIPHelper.get().choosecustomertocreateorder("voipservice", map.get("CreateOrderName"), map.get("CreateOrderCustomer"));
		
	}


	//@Test(description = "TC-03",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=2)
	 public void verifycreateorder(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyneworder");
		//APT_VOIPHelper.get().createexistingorderservice("voipservice", map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		APT_IPVPNHelper.get().createneworderservice("ipvpnservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
	}
	
		
	//@Test(description = "TC-04",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=3)
	 public void verifyservicetypeselection(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyservicetypeselection");
		APT_IPVPNHelper.get().verifyservicetypeandSubtype("ipvpnservice", map.get("ServiceType"),map.get("ServiceSubType"));
	}
	
	
	//@Test(description = "TC-05",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=4)
	 public void createservicecreation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("createservicecreation");
		//APT_IPVPNHelper.get().verifyingservicecreation("ipvpnservice", map.get("ServiceIdentification"), map.get("ResellerCode"),map.get("Remarks"),map.get("EmailService"),map.get("PhoneService"), map.get("ManageService"), map.get("SyslogEventView") ,map.get("ServiceStatusView"), map.get("RouterConfigurationView"),	map.get("PerformanceReporting"),map.get("ProactiveNotification"), map.get("NotificationManagementTeam"), map.get("DialUserAdministration"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"));
		APT_IPVPNHelper.get().createservice("ipvpnservice", map.get("ServiceIdentification"),map.get("Remarks"),map.get("Email"),map.get("Phone"),map.get("DeliveryChannel"),map.get("Package"));
	}

	
	//@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=5)
	 public void verifyCustomerDetailsInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyCustomerDetailsInformation");
		APT_IPVPNHelper.get().verifyCustomerDetailsInformation("ipvpnservice", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		//APT_IPVPNHelper.get().verifyUserDetailsInformation("ipvpnservice", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
	}
		
	//@Test(description = "TC-07",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=6)
	public void verifyUserDetailsInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyUserDetailsInformation");
		APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
		APT_IPVPNHelper.get().createnewuser("ipvpnservice", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), map.get("EditEmail"), map.get("EditPhone"));
		
	}
	
	//@Test(description = "TC-08",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=7)
	public void verifyOrderDetailsInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyOrderDetailsInformation");
		APT_IPVPNHelper.get().verifyorderpanelinformation_Neworder("ipvpnservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		APT_IPVPNHelper.get().verifyorderpanel_editorder("ipvpnservice", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
		APT_IPVPNHelper.get().verifyorderpanel_changeorder("ipvpnservice", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"),map.get("CreateNewOrder"));	
}
	
	
	
	
	//@Test(description = "TC-09",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=8)
	 public void verifyServicepanelinviewservicepage(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyServicepanelinviewservicepage");
		APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
		APT_IPVPNHelper.get().verifyservicepanelInformationinviewservicepage("ipvpnservice", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceSubType") , map.get("Remarks") , map.get("EditEmail") , map.get("EditPhone"));
		

		
		APT_IPVPNHelper.get().verifyservicepanel_links("ipvpnservice", map.get("EditRemarks"), map.get("Remarks"), map.get("ChangeOrder_OrderNumber"),
				map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
	}
	
	//@Test(description = "TC-10",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=9)
	 public void verifyManagementOptionspanel(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyManagementOptionspanel");
		APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
		APT_IPVPNHelper.get().verifyManagementOptionspanel("ipvpnservice");
	
	}
	
	 
	@Test(description = "TC-11",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=10)
			public void AddVPNSITEOrder(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("AddVPNSITEOrder");
			APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
			APT_IPVPNHelper.get().AddVPNSiteOrder("ipvpnservice", map.get("VPN Device Country"),map.get("VPN Device City"),map.get("Physical Site"),map.get("VPN Vendor/Model"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),
					map.get("Router Id"),map.get("Management Address"),map.get("Voip Service")	);
	}
	
	
	@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_IPVPN", priority=11)
	public void verifyAddedMASswitchInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
	DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddedMASswitchInformation");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().verifyAddedVPNSiteInformation_View("ipvpnservice");
}
	
	/*
	
	//@Test(description = "TC-13",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=12)
	public void verifyEditMasSwitchFunction(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEditMasSwitchFunction");
	
	APT_VOIPHelper.get().verifyEditMASswitchFunction("voipservice");

}
	
	//@Test(description = "TC-14",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=13)
	public void verifyFetchDeviceInterfaceFunction_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFetchDeviceInterfaceFunction_MAS");
	
	APT_VOIPHelper.get().verifFetchDeviceInterfacesFunction_MAS("voipservice",map.get("ServiceIdentification"), map.get("MAS_ServiceStatusChangeRequired"));

}
	
	//@Test(description = "TC-15",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=14)
	public void verifyRouterToolFunction_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	DriverTestcase.logger = DriverTestcase.extent.startTest("verifyRouterToolFunction_MAS");
	
	APT_VOIPHelper.get().verifyRouterToolFunction_MAS("voipservice",map.get("ServiceIdentification"), map.get("MAS_CommandIPV4"), map.get("MAS_CommandIPV6"), map.get("MAS_ManagementAddress"));

}
	
	
	
	
	 
	
////////////////////////////ADD INTERFACE for MAS Switch  ///////////////////////////
	 
	//@Test(description = "TC-17",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=17)
	public void verifyAddInterfaceFunction_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {

		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddInterfaceFunction_MAS");
		//APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		//APT_VOIPHelper.get().verifyAddMASswitch("voipservice", map.get("MAS_IMSPOPLocation"));	
		
		APT_VOIPHelper.get().verifyAddInterfaceFunction_MAS("voipservice",map.get("MAS_AccessMedia"),map.get("MAS_Network"),map.get("MAS_HSRPBGP"),
				map.get("MAS_GenerateConfiguration"), map.get("MAS_Interface"), map.get("MAS_InterfaceAddressRange"), map.get("MAS_InterfaceAddressMask"),
				map.get("MAS_HSRPIP"), map.get("MAS_InterfaceAddressRangeIPV6"), map.get("MAS_HSRPIPv6Address"), map.get("MAS_PrimaryIPv6onMas1"),
				map.get("MAS_SecondaryIPv6onMas2"), map.get("MAS_GroupNumber"), map.get("MAS_Link"), map.get("MAS_VLANID"),
				map.get("MAS_IVManagement"), map.get("MAS_Configuration"), map.get("MAS_HSRPTrackInterface"),map.get("MAS_HSRPAuthentication"));
}

	
	//@Test(description = "TC-18",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=18)
	public void verifyEditInterfaceFunction_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {

		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEditInterfaceFunction_MAS");
		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		//**APT_VOIPHelper.get().verifyAddMASswitch("voipservice", map.get("MAS_IMSPOPLocation"));
		
		APT_VOIPHelper.get().verifyEditInterfaceFunction_MAS("voipservice",map.get("ServiceIdentification"), map.get("MAS_AccessMediaEdit"),map.get("MAS_NetworkEdit"),map.get("MAS_HSRPBGPEdit"),
				map.get("MAS_GenerateConfigurationEdit"), map.get("MAS_InterfaceEdit"), map.get("MAS_InterfaceAddressRangeEdit"), map.get("MAS_InterfaceAddressMaskEdit"),
				map.get("MAS_HSRPIPEdit"), map.get("MAS_InterfaceAddressRangeIPV6Edit"), map.get("MAS_HSRPIPv6AddressEdit"), map.get("MAS_PrimaryIPv6onMas1Edit"),
				map.get("MAS_SecondaryIPv6onMas2Edit"), map.get("MAS_GroupNumberEdit"), map.get("MAS_LinkEdit"), map.get("MAS_VLANIDEdit"),
				map.get("MAS_IVManagementEdit"), map.get("MAS_ConfigurationEdit"), map.get("MAS_HSRPTrackInterfaceEdit"),map.get("MAS_HSRPAuthenticationEdit"));
}
	
	
	
	//@Test(description = "TC-19",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=19)
	public void verifyConfigureInterfaceFunction_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
				
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyConfigureInterfaceFunction_MAS");
		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		//**APT_VOIPHelper.get().verifyAddMASswitch("voipservice", map.get("MAS_IMSPOPLocation"));
				
		APT_VOIPHelper.get().verifyConfigureInterfaceFunction_MAS("voipservice",map.get("ServiceIdentification"), map.get("MAS_GenerateConfiguration"), map.get("MAS_Configuration"));
	}
		
			
			
			
	
	//@Test(description = "TC-20",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=20)
		public void verifyDeleteInterfaceFunction_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {

			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeleteInterfaceFunction_MAS");
			//**APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			//**APT_VOIPHelper.get().verifyAddMASswitch("voipservice", map.get("MAS_IMSPOPLocation"));
			
			APT_VOIPHelper.get().verifyDeleteInterfaceFunction_MAS("voipservice",map.get("ServiceIdentification"));
	}



	//@Test(description = "TC-21",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=22)
		public void verifyDeleteFunction_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeleteFunction_MAS");
		
		APT_VOIPHelper.get().verifyDeleteDeviceFunction_MAS("voipservice",map.get("ServiceIdentification") );

	}

		
		
		
		
   
		
		
		
		
		
		
		
		
		
		
		@SuppressWarnings("PE DEVICE")
		//@Test(description = "TC-23",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=23)
		public void verifyAddPEDeviceFunction(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddPEDeviceFunction");
		APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyAddPEDevice("voipservice", map.get("PE_IMSPOPLocation"));
	
	}

		//@Test(description = "TC-24",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=24)
		public void verifyAddedPEDeviceInformation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddedPEDeviceInformation");
		//**APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
		APT_VOIPHelper.get().verifyAddedPEDeviceInformation_View("voipservice", map.get("PE_IMSPOPLocation"));
	}	
		
		//@Test(description = "TC-25",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=25)
		public void verifyEditPEDeviceFunction(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEditPEDeviceFunction");
		
		APT_VOIPHelper.get().verifyEditPEDeviceFunction("voipservice");

	}
	
		//@Test(description = "TC-26",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=26)
		public void verifyFetchDeviceInterfaceFunction_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFetchDeviceInterfaceFunction_PE");
		
		APT_VOIPHelper.get().verifFetchDeviceInterfacesFunction_PE("voipservice",map.get("ServiceIdentification"), map.get("PE_ServiceStatusChangeRequired"));

	}
		
		//@Test(description = "TC-27",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=27)
		public void verifyRouterToolFunction_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyRouterToolFunction_PE");
		
		APT_VOIPHelper.get().verifyRouterToolFunction_PE("voipservice",map.get("ServiceIdentification"), map.get("PE_CommandIPV4"), map.get("PE_ManagementAddress"));

	}
		
		
		
		
		
		
		
		
		
		
		////////////////////////////ADD INTERFACE for MAS Switch  ///////////////////////////
		 
		//@Test(description = "TC-28",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=28)
		public void verifyAddInterfaceFunction_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {

			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddInterfaceFunction_PE");
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			//APT_VOIPHelper.get().verifyAddPEDevice("voipservice", map.get("PE_IMSPOPLocation"));	

			APT_VOIPHelper.get().verifyAddInterfaceFunction_PE("voipservice",map.get("PE_AccessMedia"),map.get("PE_Network"),map.get("PE_VRRPBGP"),
					map.get("PE_GenerateConfiguration"), map.get("PE_Interface"), map.get("PE_InterfaceAddressRange"), map.get("PE_InterfaceAddressMask"),
					map.get("PE_VRRPIP"), map.get("PE_InterfaceAddressRangeIPV6"), map.get("PE_VRRPIPv6Address"), map.get("PE_PrimaryIPv6onMas1"),
					map.get("PE_SecondaryIPv6onMas2"), map.get("PE_GroupNumber"), map.get("PE_Link"), map.get("PE_VLANID"), map.get("PE_VRRPGroupName"), map.get("PE_VRF"),
					map.get("PE_IVManagement"), map.get("PE_Configuration"), map.get("PE_VRRPTrackInterface"),map.get("PE_VRRPAuthentication"));
}

		
		

		//@Test(description = "TC-29",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=29)
		public void verifyEditInterfaceFunction_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {

			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEditInterfaceFunction_PE");
			//**APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			//**APT_VOIPHelper.get().verifyAddPEDevice("voipservice", map.get("PE_IMSPOPLocation"));

			APT_VOIPHelper.get().verifyEditInterfaceFunction_PE("voipservice",map.get("ServiceIdentification"), map.get("PE_AccessMediaEdit"),map.get("PE_NetworkEdit"),map.get("PE_VRRPBGPEdit"),
					map.get("PE_GenerateConfigurationEdit"), map.get("PE_InterfaceEdit"), map.get("PE_InterfaceAddressRangeEdit"), map.get("PE_InterfaceAddressMaskEdit"),
					map.get("PE_VRRPIPEdit"), map.get("PE_InterfaceAddressRangeIPV6Edit"), map.get("PE_VRRPIPv6AddressEdit"), map.get("PE_PrimaryIPv6onMas1Edit"),
					map.get("PE_SecondaryIPv6onMas2Edit"), map.get("PE_GroupNumberEdit"), map.get("PE_LinkEdit"), map.get("PE_VLANIDEdit"), map.get("PE_VRRPGroupNameEdit"), map.get("PE_VRFEdit"),
					map.get("PE_IVManagementEdit"), map.get("PE_ConfigurationEdit"), map.get("PE_VRRPTrackInterfaceEdit"),map.get("PE_VRRPAuthenticationEdit"));
}



		//@Test(description = "TC-30",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=30)
		public void verifyConfigureInterfaceFunction_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {

			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyConfigureInterfaceFunction_PE");
			//**APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			//**APT_VOIPHelper.get().verifyAddPEDevice("voipservice", map.get("PE_IMSPOPLocation"));

			APT_VOIPHelper.get().verifyConfigureInterfaceFunction_PE("voipservice",map.get("ServiceIdentification"), map.get("PE_GenerateConfiguration"), map.get("PE_Configuration"));
}



		//@Test(description = "TC-31",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=31)
		public void verifyDeleteInterfaceFunction_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {

			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeleteInterfaceFunction_PE");
			//**APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			//**APT_VOIPHelper.get().verifyAddPEDevice("voipservice", map.get("PE_IMSPOPLocation"));

			APT_VOIPHelper.get().verifyDeleteInterfaceFunction_PE("voipservice",map.get("ServiceIdentification"));
}


		//@Test(description = "TC-32",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=32)
		public void verifyDeleteDeviceFunction_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeleteDeviceFunction_PE");
		
		APT_VOIPHelper.get().verifyDeleteDeviceFunction_PE("voipservice",map.get("ServiceIdentification") );

	}
	
		
		
		
		
		
		
		
		@SuppressWarnings("TRUNK PANEL")
		//@Test(description = "TC-34", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=34)
		public void verifyTrunkGroupSiteOrderFunction(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyTrunkGroupSiteOrderFunction");
			Thread.sleep(2000);
			
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			
			//APT_VOIPHelper.get().addTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupOrderCheckboxStatus"), map.get("TrunkGroupSiteOrderNumber"));
			
			//**APT_VOIPHelper.get().editTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("TrunkGroupOrderCheckboxStatusEdit"), map.get("TrunkGroupSiteOrderNumberEdit"));
			//**APT_VOIPHelper.get().deleteTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"));
			//**APT_VOIPHelper.get().deleteUpdatedTrunkGroupSiteOrderNumber("voipservice",  map.get("TrunkGroupSiteOrderNumberEdit"));
			
			//**APT_VOIPHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("wholesaleService", map.get("ExistingTrunkGroupSiteOrderNumber"));
}
		
			
		
		
		
		
		
		
		
		
		@SuppressWarnings("CPE DEVICE")
		//@Test(description = "TC-40", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=41)
		public void verifyCPEDeviceFunction(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyTrunkGroupSiteOrderFunction");
			Thread.sleep(2000);
			
			APT_VOIPHelper.get().searchorder("voipservice", map.get("ServiceIdentification"));
			
			APT_VOIPHelper.get().addTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupOrderCheckboxStatus"), map.get("TrunkGroupSiteOrderNumber"));
			
			APT_VOIPHelper.get().editTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"), map.get("TrunkGroupOrderCheckboxStatusEdit"), map.get("TrunkGroupSiteOrderNumberEdit"));
			APT_VOIPHelper.get().deleteTrunkGroupSiteOrderNumber("voipservice", map.get("TrunkGroupSiteOrderNumber"));
			//APT_VOIPHelper.get().deleteUpdatedTrunkGroupSiteOrderNumber("voipservice",  map.get("TrunkGroupSiteOrderNumberEdit"));
			
			//**APT_VOIPHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("wholesaleService", map.get("ExistingTrunkGroupSiteOrderNumber"));
			
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	///////////////////////////////////////  TTRRUUNNKK ////////////////////////////////////
	
		//@Test(description = "TC-34", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=34)
		public void addTrunk(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("addTrunk");
			Thread.sleep(2000);
			
			APT_VOIPHelper.get().addTrunkGroupSiteOrderNumber("wholesaleService", map.get("TrunkGroupOrderCheckboxStatus"), map.get("TrunkGroupOrderNumber"));
			
			APT_VOIPHelper.get().editTrunkGroupSiteOrderNumber("wholesaleService", map.get("TrunkGroupOrderNumber"), map.get("edit_TrunkGroupOrder"), map.get("edit_TrunkGroupOrderNumber"));
			
			APT_VOIPHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("wholesaleService", map.get("TrunkGroupOrderNumber"));
			
			APT_VOIPHelper.get().addTrunk("wholesaleService", map.get("Name"), map.get("ServiceIdentification") ,map.get("trunkType"), map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"),
					map.get("gateway"), map.get("quality"), map.get("trafficDirection"), map.get("ipAddresstype"), map.get("SIPsignallingPort"),
					map.get("internetBasedCustomer"), map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),
					map.get("coltSignalingIP"), map.get("mediaIP"), map.get("reuseNIFgroup"), map.get("reuseSigZonePart"), map.get("callAdmissionControl"),
					map.get("callrateLimitselection"), map.get("sourceAddressFiltering"), map.get("relSupport"), map.get("sipSessionkeepAliveTimer"), map.get("retryInvite"),
					map.get("routePriority"), map.get("addressReachability"), map.get("carrierIPoriginating"), map.get("carrierIPterminating"), map.get("TLSfield"),
					map.get("srtp"), map.get("prefix"), map.get("globalProfile_ExistingSelection"), map.get("globalProfile_newSelection"),map.get("globalProfile_ExistingValue"),map.get("globalProfile_newValue"), 
					map.get("localProfile_existingFieldSelection"), map.get("localProfile_newFieldSelection"), map.get("localProfile_existingvalue"), map.get("localProfile_newvalue"),
					map.get("COSprofile_existingFieldSelection"), map.get("COSprofile_newFieldSelection"),map.get("COSprofile_existingValue"), map.get("COSprofile_newValue"),
					map.get("PSPGname_existingFieldSelection"), map.get("PSPGname_newFieldSelection"),map.get("pspgName_existingValue"), map.get("pspgName_newValue"),
					map.get("prefferedPSP_existingFieldSelection"), map.get("prefferedPSP_newFieldSelection"),map.get("preferredPSP_exitingvalue"), map.get("preferredPSP_newvalue"),
					map.get("carrier_existingFieldSelection"), map.get("carrier_newFieldSelection"), map.get("carrier_existingValue"), map.get("carrier_newValue"),
					map.get("IPsignallingProfile_existingFieldSelection"), map.get("IPsignallingProfile_newFieldSelection"), map.get("IPsignallingProfile_existingValue"), map.get("IPsignallingProfile_newValue"),
					map.get("EgressIpsignal_existingFieldSelection"), map.get("EgressIpsignal_newFieldSelection"),map.get("EgressIPsignal_existingValue"), map.get("EgressIPsignal_newValue"),
					map.get("InDMPMrule_existingFieldSelection"), map.get("InDMPMrule_newFieldSelection"), map.get("InDMPMrule_existingValue"), map.get("InDMPMrule_newValue"),
					map.get("OutDMPMrule_existingFieldSelection"),map.get("OutDMPMrule_newFieldSelection"), map.get("OutDMPMrule_existingValue"), map.get("OutDMPMrule_newValue"),
					map.get("featureControlprofile_existingFieldSelection"), map.get("featureControlprofile_newFieldSelection"), map.get("featureControlprofile_existingValue"), map.get("featureControlprofile_newValue"),
					map.get("localRingBackTone_existingFieldSelection"), map.get("localRingBackTone_newFieldSelection"), map.get("localRingBackTone_existingValue"), map.get("localRingBackTone_newValue"),
					map.get("createLowerCaseRoutervalue"), map.get("PSXmanualConfigvalue"), map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), map.get("callrateLimiteValue"));
			
			APT_VOIPHelper.get().verifysuccessmessage("wholesaleService", "Trunk created successfully");
		}
		
		
		
		
		
		
		
		
		//@Test(description = "TC-35", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=35)
		public void viewTrunk(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("viewTrunk");
			Thread.sleep(2000);
			
			APT_VOIPHelper.get().viewTrunk_Primary("wholesaleService", map.get("Name"), map.get("ServiceIdentification") ,map.get("trunkType"), map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"),
					map.get("gateway"), map.get("quality"), map.get("trafficDirection"), map.get("ipAddresstype"), map.get("SIPsignallingPort"),
					map.get("internetBasedCustomer"), map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),
					map.get("coltSignalingIP"), map.get("mediaIP"), map.get("reuseNIFgroup"), map.get("reuseSigZonePart"), map.get("callAdmissionControl"),
					map.get("callrateLimitselection"), map.get("sourceAddressFiltering"), map.get("relSupport"), map.get("sipSessionkeepAliveTimer"), map.get("retryInvite"),
					map.get("routePriority"), map.get("addressReachability"), map.get("carrierIPoriginating"), map.get("carrierIPterminating"), map.get("TLSfield"),
					map.get("srtp"), map.get("prefix"), map.get("globalProfile_ExistingSelection"), map.get("globalProfile_newSelection"),map.get("globalProfile_ExistingValue"),map.get("globalProfile_newValue"), 
					map.get("localProfile_existingFieldSelection"), map.get("localProfile_newFieldSelection"), map.get("localProfile_existingvalue"), map.get("localProfile_newvalue"),
					map.get("COSprofile_existingFieldSelection"), map.get("COSprofile_newFieldSelection"),map.get("COSprofile_existingValue"), map.get("COSprofile_newValue"),
					map.get("PSPGname_existingFieldSelection"), map.get("PSPGname_newFieldSelection"),map.get("pspgName_existingValue"), map.get("pspgName_newValue"),
					map.get("prefferedPSP_existingFieldSelection"), map.get("prefferedPSP_newFieldSelection"),map.get("preferredPSP_exitingvalue"), map.get("preferredPSP_newvalue"),
					map.get("carrier_existingFieldSelection"), map.get("carrier_newFieldSelection"), map.get("carrier_existingValue"), map.get("carrier_newValue"),
					map.get("IPsignallingProfile_existingFieldSelection"), map.get("IPsignallingProfile_newFieldSelection"), map.get("IPsignallingProfile_existingValue"), map.get("IPsignallingProfile_newValue"),
					map.get("EgressIpsignal_existingFieldSelection"), map.get("EgressIpsignal_newFieldSelection"),map.get("EgressIPsignal_existingValue"), map.get("EgressIPsignal_newValue"),
					map.get("InDMPMrule_existingFieldSelection"), map.get("InDMPMrule_newFieldSelection"), map.get("InDMPMrule_existingValue"), map.get("InDMPMrule_newValue"),
					map.get("OutDMPMrule_existingFieldSelection"),map.get("OutDMPMrule_newFieldSelection"), map.get("OutDMPMrule_existingValue"), map.get("OutDMPMrule_newValue"),
					map.get("featureControlprofile_existingFieldSelection"), map.get("featureControlprofile_newFieldSelection"), map.get("featureControlprofile_existingValue"), map.get("featureControlprofile_newValue"),
					map.get("localRingBackTone_existingFieldSelection"), map.get("localRingBackTone_newFieldSelection"), map.get("localRingBackTone_existingValue"), map.get("localRingBackTone_newValue"),
					map.get("createLowerCaseRoutervalue"), map.get("PSXmanualConfigvalue"), map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), map.get("callrateLimiteValue"));
			
		}
			
			
		
		
		
		
		
		
		//@Test(description = "TC-36", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=36)
		public void editTrunk(Map<String, String> map) throws Exception {
			
			APT_VOIPHelper.get().editTrunk("wholesaleService",map.get("Name"), map.get("ServiceIdentification"),  map.get("trunkType"), map.get("edit_TrunkType"), map.get("edit_VOIPprotocol"), map.get("edit_BillingCountry"), map.get("edit_CDRdelivery"),
					map.get("editPrefix"), map.get("editGateway"), map.get("editQuality"), map.get("editTrafficDirection"), map.get("edit_IpAddressType"),
					map.get("editCarrierIPoriginating"), map.get("editCarrierIPterminating"), map.get("editSIPsignallingPort"), map.get("editSignallingTransport"),
					map.get("edit_TLSproflile"), map.get("edit_SRTP"), map.get("edit_signallingZone"), map.get("edit_coltSignalIP"), map.get("edit_mediaIP"), map.get("edit_reuseNIFgroup"),
					map.get("edit_reuseSigZonePart"), map.get("edit_callAdmissionControl"), map.get("edit_callLimit"), map.get("edit_limitNumber"), map.get("edit_callrateLimit"),
					map.get("edit_callrateLimitvalue"), map.get("edit_sourceAddressFiltering"), map.get("edit_relSupport"), map.get("edit_sipSessionkeepAliveTimer"),
					map.get("edit_internetBasedCustomer"), map.get("edit_vlantag"), map.get("edit_subInterfaceSlot"), map.get("edit_retryInvite"), map.get("edit_addressReachability"),
					map.get("edit_routePriority"),
					map.get("editglobalProfile_ExistingSelection"), map.get("editglobalProfile_newSelection"), map.get("editGlobalProfile_ExistingValue"),map.get("editGlobalProfile_newValue"), 
					map.get("editLocalProfile_existingFieldSelection"), map.get("editLocalProfile_newFieldSelection"), map.get("editLocalProfile_existingvalue"), map.get("editLocalProfile_newvalue"),
					map.get("editCOSprofile_existingFieldSelection"), map.get("editCOSprofile_newFieldSelection"), map.get("editCOSprofile_existingValue"), map.get("editCOSprofile_newValue"),
					map.get("editPSPGname_existingFieldSelection"), map.get("editPSPGname_newFieldSelection"), map.get("editpspgName_existingValue"), map.get("editpspgName_newValue"),
					map.get("editPrefferedPSP_existingFieldSelection"), map.get("editPrefferedPSP_newFieldSelection"), map.get("editPreferredPSP_exitingvalue"), map.get("editPreferredPSP_newvalue"),
					map.get("editCarrier_existingFieldSelection"), map.get("editCarrier_newFieldSelection"), map.get("editCarrier_existingValue"), map.get("editCarrier_newValue"),
					map.get("editIPsignalProfile_existingFieldSelection"), map.get("editIPsignalProfile_newFieldSelection"), map.get("editIPsignalProfile_existingValue"), map.get("editIPsignalProfile_newValue"),
					map.get("editEgressIpsignal_existingFieldSelection"), map.get("editEgressIpsignal_newFieldSelection"), map.get("editEgressIPsignal_existingValue"), map.get("editEgressIPsignal_newValue"),
					map.get("editInDMPMrule_existingFieldSelection"), map.get("editInDMPMrule_newFieldSelection"), map.get("editInDMPMrule_existingValue"), map.get("editInDMPMrule_newValue"),
					map.get("editOutDMPMrule_existingFieldSelection"), map.get("editOutDMPMrule_newFieldSelection"), map.get("editOutDMPMrule_existingValue"), map.get("editOutDMPMrule_newValue"),
					map.get("editFeatureControlprofile_existingFieldSelection"), map.get("editFeatureControlprofile_newFieldSelection"), map.get("editFeatureControlprofile_existingValue"), map.get("editFeatureControlprofile_newValue"),
					map.get("editLocalRingBackTone_existingFieldSelection"), map.get("editLocalRingBackTone_newFieldSelection"), map.get("editLocalRingBackTone_existingValue"), map.get("editLocalRingBackTone_newValue"),
					map.get("editCreateLowerCaseRoutervalue"), map.get("edit_PSXmanualConfigvalue"), map.get("edit_GSXmanualConfigvalue"));
			
		}
		
		
		
		
		
		
		//@Test(description = "TC-37", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_VOIP", priority=37)
		public void AddTrunk_Resilient(Map<String, String> map) throws Exception {
			
			APT_VOIPHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("wholesaleService", map.get("TrunkGroupOrderNumber"));
			
			APT_VOIPHelper.get().addResilienttrunk("wholesaleService", map.get("Name"), map.get("ServiceIdentification") ,map.get("trunkType"), map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"),
					map.get("gateway"), map.get("quality"), map.get("trafficDirection"), map.get("ipAddresstype"), map.get("SIPsignallingPort"),
					map.get("internetBasedCustomer"), map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),
					map.get("coltSignalingIP"), map.get("mediaIP"), map.get("reuseNIFgroup"), map.get("reuseSigZonePart"), map.get("callAdmissionControl"),
					map.get("callrateLimitselection"), map.get("sourceAddressFiltering"), map.get("relSupport"), map.get("sipSessionkeepAliveTimer"), map.get("retryInvite"),
					map.get("routePriority"), map.get("addressReachability"), map.get("carrierIPoriginating"), map.get("carrierIPterminating"), map.get("TLSfield"),
					map.get("srtp"), map.get("prefix"), map.get("globalProfile_ExistingSelection"), map.get("globalProfile_newSelection"),map.get("globalProfile_ExistingValue"),map.get("globalProfile_newValue"), 
					map.get("localProfile_existingvalue"), map.get("localProfile_newvalue"),map.get("COSprofile_existingValue"), map.get("COSprofile_newValue"),
					map.get("pspgName_existingValue"), map.get("pspgName_newValue"),map.get("preferredPSP_exitingvalue"), map.get("preferredPSP_newvalue"),
					map.get("carrier_existingValue"), map.get("carrier_newValue"),map.get("IPsignallingProfile_existingValue"), map.get("IPsignallingProfile_newValue"),
					map.get("EgressIPsignal_existingValue"), map.get("EgressIPsignal_newValue"),map.get("InDMPMrule_existingValue"), map.get("InDMPMrule_newValue"),
					map.get("OutDMPMrule_existingValue"), map.get("OutDMPMrule_newValue"),map.get("featureControlprofile_existingValue"), map.get("featureControlprofile_newValue"),
					map.get("localRingBackTone_existingFieldSelection"), map.get("localRingBackTone_newFieldSelection"), map.get("localRingBackTone_existingValue"), map.get("localRingBackTone_newValue"),
					map.get("createLowerCaseRoutervalue"), map.get("PSXmanualConfigvalue"), map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), map.get("callrateLimiteValue"),
					
					map.get("gateway_resilientTrunk"), map.get("voip_resilientTrunk"), map.get("trafficDirection_resiltrunk"), map.get("ipAddressType_resilTrunk"), map.get("carierIPOrient_resiltrunk"),
					map.get("carierIPterminat_resiltrunk"));
		}
		
	
	
	
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
	
*/
	
}
