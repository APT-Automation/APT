package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader_SS;

public class Lanlink_Outbandmanagement extends DriverTestcase {
	

	   APT_Login login = new APT_Login();
	   
	   
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=1)
		public void chooseCustomer(Map<String, String> map) throws Exception {

			
			DriverTestcase.logger = DriverTestcase.extent.startTest("chooseCustomer");
			
			System.out.println("-------Login functionality------------1");
			login.APT_Login_1();
			
		System.out.println("LOgin is done");
			DriverTestcase.logger.log(LogStatus.INFO,"The service type to be created is: "+map.get("Servicesubtype"));

			Outband.get().selectCustomertocreateOrder("CreateOrderService",map.get("ChooseCustomerToBeSelected"),map.get("Customername"));
			
	}
		
		
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=2)
		public void verifyListofFieldsForOrderandServicetype(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyListofFieldsForOrderandServicetype");
			
			System.out.println("-------Verify the fields for Service type and select the service sub type------------1");	
		
			Outband.get().Verifyfields(("CreateOrderService"),map.get("ServiceType"), map.get("Modularmsp"), map.get("AutocreateService"));
			Outband.get().selectCustomertocreateOrderfromleftpane("CreateOrderService",map.get("ChooseCustomerToBeSelected"),map.get("Customername"));
			Outband.get().SelectServiceType("CreateOrderService", map.get("Order/ContractNumber"),map.get("ServiceType"), map.get("Ordertype"), map.get("valuetobeselectedinorderdropdown"));

		}
		
		
		
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=3)
		public void selectTheServiceType(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("selectTheServiceType");
			
			
			System.out.println("------After selecting service type------2");
			Outband.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
			
		}
		
		
		
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=4)
		public void verifyFieldsFortheSubServicetypeselelctedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFieldsFortheSubServicetypeselelctedunderLANLINK");
			
			System.out.println("----Verify the fields for Service sub type selected----------3");
			Outband.get().VerifyFieldsForServiceSubTypeSelected("LANLINK",map.get("ServiceType"),map.get("Servicesubtype"), map.get("Interfacespeed"),
					map.get("Notification management"), map.get("vpnTopology"), map.get("AggregateTraffic"), map.get("Modularmsp"));	
		
		}
		
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=5)
		public void enterdatafortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("enterdatafortheServiceSubtypeSelectedunderLANLINK");
			
			System.out.println("------Enter data for the service sub type selected-------4");	
			Outband.get().SelectServiceType2("CreateOrderService", map.get("Order/ContractNumber"),map.get("ServiceType"), map.get("Ordertype"), map.get("valuetobeselectedinorderdropdown"));
			Outband.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
						map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
			Outband.get().dataToBeEnteredOncesubservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
					map.get("performanceReportngForServices"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
					map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"), map.get("Notification management"), map.get("performanceReportngForServices"),
					map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), map.get("premiumEIR_ServiceCreation"));
		   
		}
		
//		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=6)
		public void successmessageforServicecreation(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("successmessageforServicecreation");
			Thread.sleep(2000);
			
			 Outband.get().verifysuccessmessageforCreateService("LANLINK");		
			
		}
		
//		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=7)
		public void verifydataenteredwhilecreatingServiceSubtypeunderLANLINKinviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifydataenteredwhilecreatingServiceSubtypeunderLANLINKinviewservicepage");
			
			System.out.println("-------- Verify data entered under service sub types---------5");		
				Outband.get().VerifydatenteredForServiceSubTypeSelected("LANLINK",map.get("servicetypeforverification"),map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
						map.get("PerformMonitor"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
						map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
						map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"),  map.get("performanceReportngForServices"),
						map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), map.get("premiumEIR_ServiceCreation"), map.get("Notification management"));
			
		}
		
		
		
//		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=8)
		public void editServiceSubtypesunderLANLINKservice(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("editServiceSubtypesunderLANLINKservice");
			
			System.out.println("----------- Edit the service type -----------------");	
			Outband.get().EditTheservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Edit_serviceNumber"),map.get("Edit_endpointCPE"),map.get("EditService_email"), map.get("EditService_phone"), map.get("EditService_remark"), 
					map.get("EditService_PerformMonitor"),map.get("EditService_proactiveMonitor"), map.get("EditService_deliveryChannel"), map.get("EditService_ManagementOrder"), map.get("vpnTopology"), map.get("EditService_intermediateTechnology"),
					map.get("EditService_CircuitReference"), map.get("EditService_CircuitType"), map.get("EditService_AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("EditService_ENNI"), map.get("EditService_manageConnectiondropdown"), map.get("Edit_A_Endtechnology"), map.get("Edit_B_Endtechnology"), map.get("EditService_NotificationManagement"),
					map.get("EditService_perCoSperformanceReport"), map.get("EditService_actelisBased"), map.get("EditService_standardCIR"), map.get("EditService_standardEIR"), map.get("EditService_premiumCIR"), map.get("EditService_premiumEIR"), map.get("CircuitType"));
			
		}
		
		
//		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=9)
		public void successmessageforServiceUpdation(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("successmessageforServiceUpdation");
			Thread.sleep(2000);
			
			 Outband.get().verifysuccessmessageforEditService("LANLINK");		
			
		}
		
		
//		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=9)
		public void synchronizeServiceSubtypeforLANLINKserviceunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("synchronizeServiceSubtypeforLANLINKserviceunderviewservicepage");
			Thread.sleep(3000);
	
			System.out.println("Entered sync service");
			Outband.get().syncservices("LANLINK");
			
		}
		
		
	
//		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=10)
		public void showNewInfovistaReportunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("showNewInfovistaReportunderviewservicepage");
			Thread.sleep(3000);
			
			System.out.println("Entered show info vista");
			Outband.get().shownewInfovista("LANLINK");
			
		}
	//
	//	
////		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=11)
//		public void manageServiceunderviewservicepage(Map<String, String> map) throws Exception {
//			
//			DriverTestcase.logger = DriverTestcase.extent.startTest("manageServiceunderviewservicepage");
//			Thread.sleep(3000);
//			
//			System.out.println("Enetred manage service");
//			Outband.get().manageService("LANLINK");
//			
//		}
	//	
	//	
	//
////		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=12)
////		public void ManageSubnetsForlanlinkServiceunderviewservicepage(Map<String, String> map) throws Exception {
////			
////			DriverTestcase.logger = DriverTestcase.extent.startTest("ManageSubnetsForlanlinkServiceunderviewservicepage");
////			Thread.sleep(3000);
////			
////			System.out.println("Entered manage sub nets");
////			Outband.get().manageSubnets("LANLINK");
////			
////		}
////			
//	//	
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=13)
		public void verifyAddSiteOrderFieldsfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddSiteOrderFieldsfortheServiceSubtypeSelectedunderLANLINK");
			Thread.sleep(3000);
			
			System.out.println("------Verify fields for add site order-------- 6");
			
			
			Outband.get().Enteraddsiteorder("LANLINK");
			Outband.get().verifyAddsiteorderFields("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					 map.get("SiteOrder_Offnet"),map.get("SiteOrder_EPNoffnet"), map.get("SiteOrder_EPNEOSDH"));
		
			Outband.get().Enteraddsiteorder("LANLINK");
			Outband.get().addsiteorder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					map.get("country"),map.get("city"),map.get("CSR_Name"), 
					 map.get("sitevalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
					   map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("existing_SiteOrdervalue"),
					   map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
					   map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
					   map.get("Siteordernumber"), map.get("siteOrder_CircuitReference"), map.get("SiteOrder_Offnet"), map.get("SiteOrder_Ivrefrence"),map.get("SiteOrer_GCROloType"),
			  		   map.get("SiteOrder_Vlan"), map.get("SiteOrder_Vlanethertype"),map.get("siteOrder_PrimaryVlan"), map.get("SiteOrder_PrimaryVlanEtherType"), map.get("SiteOrder_EPNoffnet"), 
			  		   map.get("SiteOrder_EPNEOSDH"), map.get("SiteOrder_mappingMode"), map.get("SiteOrder_portBased"), map.get("SiteOrder_vlanBased"));
			
		}
	
		
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=15)
		public void SuccessmessageforCreationofSiteOrder(Map<String, String> map) throws Exception {
			
	   System.out.println("---- verify success messgae for adding site order-------7");
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("SuccessmessageforCreationofSiteOrder");
			Thread.sleep(3000);
		
			Outband.get().verifysuccessmessageforSiteOrder();
		}
		
		
	
//		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=16)
		public void verifytheDataEnteredforAddSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifytheDataEnteredforAddSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
			Thread.sleep(3000);
			
			
			Outband.get().VerifyDataEnteredForSiteOrder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					map.get("country"),map.get("city"),map.get("CSR_Name"), 
					 map.get("existing_SiteOrdervalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
					   map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("sitevalue"),
					   map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
					   map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
					   map.get("Siteordernumber"), map.get("siteOrder_CircuitReference"), map.get("SiteOrder_Offnet"), map.get("SiteOrder_Ivrefrence"),map.get("SiteOrer_GCROloType"),
			  		   map.get("SiteOrder_Vlan"), map.get("SiteOrder_Vlanethertype"),map.get("siteOrder_PrimaryVlan"), map.get("SiteOrder_PrimaryVlanEtherType"), map.get("SiteOrder_EPNoffnet"), map.get("SiteOrder_EPNEOSDH"));
			
			
			
//			System.out.println("----verify details inside the siteorder table under view service page-----------");
//				Outband.get().VerifySiteOrderdetailsInTable("LANLINK", map.get("Siteordernumber"));
		
		}
//		
////		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=17)
////		public void verifyFieldsforEditSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
////			
////			System.out.println("-------verify Edit site order fields--------8");
////			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFieldsforEditSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
////			
////			
////			
////			Thread.sleep(5000);
////			
////			Outband.get().clickonEditwithoutselectingrow("LANLINK");
////			Outband.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"));
////			Outband.get().verifyEditSiteOrder("LANLINK" ,map.get("country"),map.get("city"),map.get("CSR_Name"), 
////				 map.get("site"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
////				 map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("sitevalue"));
////			
////		}
//		
//		
//		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=17)
		public void EditSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("EditSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
			Thread.sleep(3000);
			
			System.out.println("----- edit site order page-----------9");
			
			Outband.get().returnbacktoviewsiteorderpage("LANLINK");
			
			Outband.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"), map.get("siteOrderNumber_PointToPoint"), map.get("vpnTopology"));
			
			Outband.get().editSiteOrder( "LANLINK", map.get("Interfacespeed") ,map.get("vpnTopology"), map.get("EditSiteOrder_performReport"), map.get("EditSiteOrder_ProactiveMonitor"),map.get("EditSiteOrder_smartmonitor"),
			     map.get("EditSiteOrder_siteallias"), map.get("EditSiteOrder_VLANid"), map.get("EditSiteOrder_DCAenabledsite"), map.get("EditSiteOrder_cloudserviceprovider"), map.get("technology"), 
			     map.get("editsiteorder_nonterminationpoint"), map.get("editsiteorder_protected"), map.get("editsiteorder_devicename"), map.get("editsiteorder_remark"), map.get("SiteOrder_Ivrefrence"),
			     map.get("Siteordernumber"), map.get("editsiteorder_circuitReference"), map.get("editsiteorder_GCRoloType"), map.get("editsiteorder_VLAN"), map.get("editsiteorder_VlanEtherType"),
			     map.get("editsiteorder_primaryVLAN"),map.get("editsiteorder_primaryVlanEtherType"), map.get("SiteOrder_Offnet"), map.get("editSiteOrder_Offnet"), map.get("editSiteOrder_EPNOffnet"),
			     map.get("editSiteOrder_mappingMode"), map.get("editSiteOrder_portbased"), map.get("editSiteorder_VlanBased"), map.get("SiteOrder_mappingMode"));
		}
		
//	
//		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=18)
//		public void ViewSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
//			
//		System.out.println("--------- view site order page--------11");
//	
//		DriverTestcase.logger = DriverTestcase.extent.startTest("ViewSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
//		Thread.sleep(3000);
//		
//		Outband.get().returnbacktoviewsiteorderpage("LANLINK");	
//		
//		Outband.get().clickonviewewithoutselectingrow("LANLINK");		
//			Outband.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"));
//			Outband.get().viewsiteorderlink("LANLINK");	
//			
//			
//		
//		}
		
		
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=18)
		public void Actelis(Map<String, String> map) throws Exception {
			
			String Technologyname=map.get("technology");
			if(Technologyname.equalsIgnoreCase("Actelis")) {
			
			boolean equipConfigurationPanel=Outband.get().EquipmentCOnfigurationPanel("LANLINK");
			
			if(equipConfigurationPanel) {
				
				Outband.get().equipConfiguration_Actelis_AddDevice("LANLINK", map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
						map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"), map.get("ActelisTech_addCPE_ETH_Port"));
			
				Outband.get().verifysuccessmessageforDeviceCreation_Actellis();
				
				Outband.get().verifyDataEnteredFordeviceCreation_Actelis("LANLINK",  map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
						map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"),  map.get("ActelisTech_addCPE_ETH_Port"));
				
				Outband.get().returnbacktoviewsiteorderpage("LANLINK");
				
				Outband.get().deleteDeviceFromService_EquipmentConfig_Actelis("LANLINK", map.get("deleteDeviceSelection_EquipmentConfiguration"), map.get("ActelisTech_addCPE_name"));
			
				Outband.get().successMessage_deleteFromService("LANLINK");
			}
			
		//Actelis Configuration panel	
			Outband.get().verifyAddDSLAMandHSLlink("LANLINK", map.get("ActelisTech_DSLAMdevice"));
			
			Outband.get().AddDSLAMandHSL("LANLINK", map.get("ActelisTech_DSLAMdevice"), map.get("ActelisTech_DSLAMInterfacename"));
			
			Outband.get().showInterface_ActelisConfiguuration("LANLINK");
			
			Outband.get().deletInterface_ActelisConfiguration("LANLINK", map.get("ActelisTech_DSLAMInterfacename"), map.get("deleteInterfcae_ActelisConfiguration"));
			
			Outband.get().successMessage_deleteInterfaceFromDevice_ActelisConfiguration("LANLINK");
			}
		}
		
		
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=19)
		public void AdddeviceforEquipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("AdddeviceforEquipment");
			Thread.sleep(3000);
		
		//verify whether Equipment panel is available	
			boolean EquipmentPanel=Outband.get().findPanelHeader("LANLINK", "Equipment");
			
			if(EquipmentPanel) {
			
				DriverTestcase.logger.log(LogStatus.PASS, " 'Equipment' panel is displaying under 'view site order' page as expected");
				
		//Verify whether Equipment device to be created
				if(map.get("deviceCreation_Equipment").equalsIgnoreCase("yes")){
					
					DriverTestcase.logger.log(LogStatus.INFO, " Device to be created for Eqiupment as per input provided");	

			DriverTestcase.logger.log(LogStatus.INFO, "Under Equipement, list of actions to be performed are: "
					+ "Verify fields for Add device"
					+ "Add device"
					+ "Verify entered values for device"
					+ "Edit device"
					+ "Select Interface"
					+ "Configure Interface -- Edit Inteface"
					+ "show/Hide Interface -- Edit Interface"
					+ "Select Interface -- Add Interface to service , Remove Interface from Service"
					+ "Delete device ");
			
			System.out.println("------For Equipment ..........Entering add cpe device, Verify CPE device, Edit CPE device----------");
				String speed=map.get("Interfacespeed");
			
				if(speed.equals("1GigE")) {
					
					Outband.get().verifyFieldsandAddCPEdevicefortheserviceselected_1G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
							 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
							 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"));
			
					Outband.get().verifysuccessmessageforDeviceCreation();
					
					Outband.get().verifydetailsEnteredforCPEdevice_1G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
						 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
						 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"), map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"));
					
//					Outband.get().returnbacktoviewsiteorderpage("LANLINK");
					
					Outband.get().eDITCPEdevicedetailsentered_1G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
							 map.get("EDIT_cpe_poweralarm_1G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
							 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"), map.get("technology"));
					
					Outband.get().verifysuccessmessageforDeviceUpdation();
				}
				
				
				if(speed.equals("10GigE")) {
					
					Outband.get().verifyFieldsandAddCPEdevicefortheserviceselected_10G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
							 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
							 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
					
					Outband.get().verifysuccessmessageforDeviceCreation();
					
					Outband.get().verifydetailsEnteredforCPEdevice_10G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
							 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
							 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),  map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
					
//					Outband.get().returnbacktoviewsiteorderpage("LANLINK");
					
					Outband.get().eDITCPEdevicedetailsentered_10G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_10G"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
							 map.get("EDIT_cpe_poweralarm_10G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
							 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"));
			
					Outband.get().verifysuccessmessageforDeviceUpdation();
				}
			
				
		//Perform fetch from Interface
			Outband.get().fetchDeviceInterface_viewdevicepage("LANLINK");
			Thread.sleep(3000);
			
			Outband.get().returnbacktoviewsiteorderpage("LANLINK");
			
		if(map.get("EDIT_cpename").equalsIgnoreCase("null")) {
			
			//Find list of Interfaces	
			Outband.get().findlistofInterfaces_Equipment_viewdevicePage("LANLINK", map.get("devicename_equip"));
			Thread.sleep(3000);
			
		   Outband.get().returnbacktoviewsiteorderpage("LANLINK");
		   
		 //Configure  
		   System.out.println("--------------Select Configure link under Equipment------------------");	
			Outband.get().selectconfigurelinkAndverifyEditInterfacefield__Equipment("LANLINK", map.get("devicename_equip"));
			Outband.get().EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
					map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
			Outband.get().verifyeditedinterfacevalue("LANLINK", map.get("Interfacename_forEditInterface"));
			
			Outband.get().returnbacktoviewsiteorderpage("LANLINK");
			
			System.out.println("-----------Show Interface link for Equipment----------14");
					Outband.get().SelectShowInterfacelinkAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"), map.get("devicename_equip"));
					Outband.get().EnterdataForEditInterfaceforShowInterfacelinkunderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
							map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
			
			
			System.out.println("--------SELECTINTERFACE link for Equipment--------13");
			
				Outband.get().selectInterfacelinkforEqipment("LANLINK", map.get("devicename_equip"));
				
				if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
					Outband.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//					Outband.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
				}else {
					System.out.println("interfaces are not removed");
				}
				
				if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
					Outband.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//					Outband.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
				}else {
					
				}
				 
				Outband.get().returnbacktoviewsiteorderpage("LANLINK");
			
			System.out.println("----------------Delete device from service------------------ ");
				Outband.get().deleteDeviceFromServiceForequipment("LANLINK", map.get("deleteDeviceSelection_equipment"), map.get("devicename_equip"));
				Outband.get().successMessage_deleteFromService("LANLINK");
			
		}else{
			
			//Find list of Interfaces	
			Outband.get().findlistofInterfaces_Equipment_viewdevicePage("LANLINK", map.get("EDIT_cpename"));
			Thread.sleep(3000);
			
		   Outband.get().returnbacktoviewsiteorderpage("LANLINK");
		   
		 //Configure  
		   System.out.println("--------------Select Configure link under Equipment------------------");	
			Outband.get().selectconfigurelinkAndverifyEditInterfacefield__Equipment("LANLINK", map.get("EDIT_cpename"));
			Outband.get().EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
					map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
			Outband.get().verifyeditedinterfacevalue("LANLINK", map.get("Interfacename_forEditInterface"));
			
			Outband.get().returnbacktoviewsiteorderpage("LANLINK");
			
			System.out.println("-----------Show Interface link for Equipment----------14");
					Outband.get().SelectShowInterfacelinkAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"), map.get("EDIT_cpename"));
					Outband.get().EnterdataForEditInterfaceforShowInterfacelinkunderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
							map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
			
			
			System.out.println("--------SELECTINTERFACE link for Equipment--------13");
			
				Outband.get().selectInterfacelinkforEqipment("LANLINK", map.get("EDIT_cpename"));
				
				if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
					Outband.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//					Outband.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
				}else {
					System.out.println("interfaces are not removed");
				}
				
				if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
					Outband.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//					Outband.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
				}else {
					
				}
				 
				Outband.get().returnbacktoviewsiteorderpage("LANLINK");
			
			System.out.println("----------------Delete device from service------------------ ");
				Outband.get().deleteDeviceFromServiceForequipment("LANLINK", map.get("deleteDeviceSelection_equipment"), map.get("EDIT_cpename"));
				Outband.get().successMessage_deleteFromService("LANLINK");
		
		}
					}
			}	else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Equipment' panel is not displaying under 'view site order' page");
			}
		}	

		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=20)
		public void IntermediateEquipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("IntermediateEquipment");
			Thread.sleep(3000);
			  
			
			
			boolean IntermediateEquipmentPanel=Outband.get().findPanelHeader("LANLINK", "Intermediate Equipment");
			
			if(IntermediateEquipmentPanel) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Intermediate Equipment' panel is displaying under 'view site order' page as expected");
				
				if(map.get("deviceCreation_IntermediateEquipment").equalsIgnoreCase("yes")){
					
					DriverTestcase.logger.log(LogStatus.INFO, " Device to be created for Intermediate Eqiupment as per input provided");	

			DriverTestcase.logger.log(LogStatus.INFO, "Under Intermediate Equipement, list of actions to be performed are: "
					+ "Verify fields for Add device"
					+ "Add device"
					+ "Verify entered values for device"
					+ "Edit device"
					+ "Select Interface"
					+ "show/Hide Interface -- Edit Interface"
					+ "Select Interface -- Add Interface to service , Remove Interface from Service"
					+ "Delete device ");
		
			
		System.out.println("--------For intermediate Equipment-------------");	
		
		String speed=map.get("Interfacespeed");
		
		
		if(speed.equals("1GigE")) {
//		 
//		Outband.get().verifyAddcpedevicepageforIntermediatEquipmetn("LANLINK");
//		
		
		Outband.get().addDevice_IntEquipment("LANLINK");
		Outband.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
		Outband.get().verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_1G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_1G_Overture"),
				map.get("device_intequip_vender_1G_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
				map.get("device_intequip_poweralarm_1G_overture"), map.get("device_intequip_poweralarm_1G_Accedian"), map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
				map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
				map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
				map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
				map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
				map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));	
		Outband.get().verifysuccessmessageforDeviceCreation();
		
	   Outband.get().verifyCPEdevicedataenteredForIntermediateEquipment_1G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_1G_Overture"),
				map.get("device_intequip_vender_1G_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
				map.get("device_intequip_poweralarm_1G_overture"), map.get("device_intequip_poweralarm_1G_Accedian"), map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
				map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
				map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
				map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
				map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
				map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));
//	   Outband.get().returnbacktoviewsiteorderpage("LANLINK");
	   Outband.get().EDITCPEdevicedforIntermediateEquipment_1G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_1G_Overtue"),map.get("EDIT_Intequip_device_vender_1G_Accedian"),
				map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
				map.get("EDIT_Intequip_device_poweralarm_1G_Overture"),map.get("EDIT_Intequip_device_poweralarm_1G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
				map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), map.get("EDIT_Intequip_device_city"),map.get("EDIT_Intequip_device_site"), map.get("EDIT_Intequip_device_premise"), 
				map.get("TechToBeselected_underTechpopup_device"));
		
		Outband.get().verifysuccessmessageforDeviceUpdation();
		}
		
		else if(speed.equals("10GigE")) {
			
			Outband.get().addDevice_IntEquipment("LANLINK");
			Outband.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
			Outband.get().verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_10G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_10G_Accedian"),
					map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
					map.get("device_intequip_poweralarm_10G_Accedian"),map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
					map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
					map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
					map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
					map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
					map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));	
			Outband.get().verifysuccessmessageforDeviceCreation();
			
		   Outband.get().verifyCPEdevicedataenteredForIntermediateEquipment_10G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_10G_Accedian"),
					map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
					map.get("device_intequip_poweralarm_10G_Accedian"),map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
					map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
					map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
					map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
					map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
					map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));
		   
//		   Outband.get().returnbacktoviewsiteorderpage("LANLINK");
		   Outband.get().EDITCPEdevice_IntermediateEquipment_10G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), 
				   map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
					map.get("EDIT_Intequip_device_poweralarm_10G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
					map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), map.get("EDIT_Intequip_device_city"),map.get("EDIT_Intequip_device_site"), map.get("EDIT_Intequip_device_premise"),map.get("TechToBeselected_underTechpopup_device"));
		   Outband.get().verifysuccessmessageforDeviceUpdation();
		  
		}
	
		//Perform fetch from Interface
				Outband.get().fetchDeviceInterface_viewdevicepage("LANLINK");
				Thread.sleep(3000);
				
				Outband.get().returnbacktoviewsiteorderpage("LANLINK");
				
				
	if(map.get("EDIT_Intequip_cpe_deviecname").equalsIgnoreCase("null")) {
		
		//Find list of Interfaces	
		Outband.get().findlistofInterfaces_IntEquipment_viewdevicePage("LANLINK", map.get("device_intEquip_name"));
		Thread.sleep(3000);
		
	   Outband.get().returnbacktoviewsiteorderpage("LANLINK");
	
	
	System.out.println("-----------Show Interface link for Equipment----------14");
	Outband.get().SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"),  map.get("device_intEquip_name"));
	Outband.get().EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
			map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
	
	
	System.out.println("--------SELECTINTERFACE link for Equipment--------13");
	
		Outband.get().selectInterfacelinkforIntermediateEqipment("LANLINK", map.get("device_intEquip_name"));
		
		if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
			Outband.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//			Outband.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
		}else {
			System.out.println("interfaces are not removed");
		}
		
		if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
			Outband.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//			Outband.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
		}

Outband.get().returnbacktoviewsiteorderpage("LANLINK");

System.out.println("-------------Delete device from service for intermediate equipment---------");
Outband.get().deleteDeviceFromServiceForIntermediateequipment("LANLINK", map.get("deleteDeviceSelection_Intermediateequipment"), map.get("device_intEquip_name"));
Outband.get().successMessage_deleteFromService("LANLINK");
		
	}else {
		
		//Find list of Interfaces	
		Outband.get().findlistofInterfaces_IntEquipment_viewdevicePage("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"));
		Thread.sleep(3000);
		
	   Outband.get().returnbacktoviewsiteorderpage("LANLINK");
	
	
	System.out.println("-----------Show Interface link for Equipment----------14");
	Outband.get().SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"),  map.get("EDIT_Intequip_cpe_deviecname"));
	Outband.get().EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
			map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
	
	
	System.out.println("--------SELECTINTERFACE link for Equipment--------13");
	
		Outband.get().selectInterfacelinkforIntermediateEqipment("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"));
		
		if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
			Outband.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//			Outband.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
		}else {
			System.out.println("interfaces are not removed");
		}
		
		if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
			Outband.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//			Outband.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
		}

Outband.get().returnbacktoviewsiteorderpage("LANLINK");


System.out.println("-------------Delete device from service for intermediate equipment---------");
Outband.get().deleteDeviceFromServiceForIntermediateequipment("LANLINK", map.get("deleteDeviceSelection_Intermediateequipment"), map.get("EDIT_Intequip_cpe_deviecname"));
Outband.get().successMessage_deleteFromService("LANLINK");
	}
				
						}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Intermediate Equipment' panel is not displaying under 'view site order' page");
			}
		}	
		
		
////		
////		
////		
//////		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=20)
//////		public void LanlinkNational(Map<String, String> map) throws Exception {
//////			
//////			
//////			String servicesubtype=map.get("Servicesubtype");	
////			
////			
//////		if (servicesubtype.equalsIgnoreCase("LANLink National")) {
//////				
//////							Outband.get().AddDSLAMandHSL("LANLINK", map.get("DSLMdevice"));
//////							Outband.get().AddCPEdevicefortheserviceselected( "LANLINK",  map.get("cpename"), map.get("cpe_vender"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
//////									 map.get("cpe_poweralarm"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
//////									   map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"));
//////							Outband.get().verifydetailsenteredforCPEdevice( "LANLINK",  map.get("cpename"), map.get("cpe_vender"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
//////									map.get("cpe_poweralarm"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
//////									   map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"));
////////							Outband.get().eDITCPEdevicedetailsentered( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
////////									 map.get("EDIT_cpe_poweralarm"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
////////									   map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"));
//////						
//////							
//////							
//////							
//////							
//////							Outband.get().addCPEdeviceforIntermediateequipment( "LANLINK",  map.get("cpeintEquip_name"), map.get("cpeintequip_vender"),  map.get("cpeintequip_snmpro"), 
//////									map.get("cpeintequip_managementAddress"), map.get("cpeintequip_Mepid"),
//////									map.get("cpeintequip_poweralarm"), map.get("cpeintequip_Mediaselection"),  map.get("cpeintequip_Macaddress"),  map.get("cpeintequip_serialNumber"),
//////									map.get("cpeintequip_hexaSerialnumber"),  map.get("cpeintequip_linkLostForwarding"), map.get("cpeintequip_country"), map.get("cpeintequip_city"),
//////									map.get("cpeintequip_site"), map.get("cpeintequip_premise"));	
//////						   Outband.get().verifyCPEdevicedataenteredForIntermediateEquipment( "LANLINK",  map.get("cpeintEquip_name"), map.get("cpeintequip_vender"),  map.get("cpeintequip_snmpro"), 
//////									map.get("cpeintequip_managementAddress"), map.get("cpeintequip_Mepid"),
//////									map.get("cpeintequip_poweralarm"), map.get("cpeintequip_Mediaselection"),  map.get("cpeintequip_Macaddress"),  map.get("cpeintequip_serialNumber"),
//////									map.get("cpeintequip_hexaSerialnumber"),  map.get("cpeintequip_linkLostForwarding"), map.get("cpeintequip_country"), map.get("cpeintequip_city"),
//////									map.get("cpeintequip_site"), map.get("cpeintequip_premise"));	
//////						   Outband.get().EDITCPEdevicedforIntermediateEquipment( "LANLINK",  map.get("cpeintEquip_name"), map.get("cpeintequip_vender"),  map.get("cpeintequip_snmpro"), 
//////									map.get("cpeintequip_managementAddress"), map.get("cpeintequip_Mepid"),
//////									map.get("cpeintequip_poweralarm"), map.get("cpeintequip_Mediaselection"),  map.get("cpeintequip_Macaddress"),  map.get("cpeintequip_serialNumber"),
//////									map.get("cpeintequip_hexaSerialnumber"),  map.get("cpeintequip_linkLostForwarding"), map.get("cpeintequip_country"), map.get("cpeintequip_city"),
//////									map.get("cpeintequip_site"), map.get("cpeintequip_premise"));
//////							
//////						   Outband.get().returnbacktoviewsiteorderpage("LANLINK");
//////						   
//////					     System.out.println("------"+ "SELECTINTERFACE"+ "link for Intermediate Equipment--------16");
//////							Outband.get().selectInterfacelinkforIntermediateEqipment("LANLINK");	
//////							Outband.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));  
//////							Outband.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//////							Outband.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//////							Outband.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//////							
//////							Outband.get().returnbacktoviewsiteorderpage("LANLINK");
//////						
//////					     System.out.println("--------------Select Configure link under Intermediate Equipment------------------");	
//////							Outband.get().selectconfigurelinkunderIntermediateEquipmentAndverifyEditInterfacefield("LANLINK", map.get("Interfacename_forEditInterface"));
//////							Outband.get().EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
//////									map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
//////						    
//////						
//////							Outband.get().returnbacktoviewsiteorderpage("LANLINK");
//////							
//////						 System.out.println("-----------Show Interface link for Equipment----------14");
//////							Outband.get().SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"));
//////							Outband.get().EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
//////									map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
//////						
//////				
//////					     System.out.println("-------------Delete device from service for intermediate equipment---------");
//////							Outband.get().deleteDeviceFromServiceForIntermediateequipment("LANLINK", map.get("deleteDeviceforIntermediateequipment"));
//////				
//////							
//////							
//////						}
//////				
//////		}
//////			
////	//	
////	//	
////////		 Outband.get().verifychoosecustomer("CreateOrderService", map.get("Name"),
////////		map.get("Customer"));
////	//
////	////
////	////System.out.println("--------delete site order -----------10");
////	////Outband.get().clickondeletewithoutselectingrow("LANLINK");		
////	////Outband.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"));
////	////Outband.get().deletesiteorderdetails("LANLINK");
////	////
////	////
////	////
////	////else if (servicesubtype.equalsIgnoreCase("LANLink outbandmanagement")) {
////	//
////	////Outband.get().VerifythefieldsforProviderEquipment("LANLINK");
////	////Outband.get().providerEquipment("LANLINK", map.get("IMS pop locations_MAS_PE"),
////////			map.get("Select/Create device_PE"), map.get("Name_Mas_PE"), map.get("VendorModel_PE"),
////////			map.get("Address_PE"), map.get("Snmpro_PE"), map.get("country_PE"), map.get("city_PE"),
////////			map.get("site_PE"), map.get("premise_PE"));
////	////Outband.get().verifyPEdeviceEnteredvalue("LANLINK", map.get("Name_Mas_PE"),
////////			map.get("VendorModel_PE"), map.get("Address_PE"), map.get("Snmpro_PE"), map.get("country_PE"),
////////			map.get("city_PE"), map.get("site_PE"), map.get("premise_PE"));
////	////Outband.get().EditProviderEquipment("LANLINK", map.get("IMS pop locations_MAS_PE"),
////////			map.get("Select/Create device_PE"), map.get("Name_Mas_PE"), map.get("VendorModel_PE"),
////////			map.get("Address_PE"), map.get("Snmpro_PE"), map.get("country_PE"), map.get("city_PE"),
////////			map.get("site_PE"), map.get("premise_PE"));
////	////
////////				
////	////Outband.get().returnbacktoviewsiteorderpage("LANLINK");
////	////
////	////System.out.println("--------SELECTINTERFACE link for Provider Equipment--------");
////	////Outband.get().selectInterfacelinkforProviderEqipment("LANLINK");
////	////Outband.get().AddInterfaceToserviceforProviderEquipment("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
////	////Outband.get().verifyInterfaceaddedToServiceForProviderEquipment("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
////	////Outband.get().ProviderEquipmentInterfaceInService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
////	////Outband.get().verifyInterfaceremovedFromServiceforProviderEquipment("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
////	////
////	////Outband.get().returnbacktoviewsiteorderpage("LANLINK");
////	////	
////	////System.out.println("-----------Show Interface link for Provider Equipment----------14");
////////			Outband.get().SelectShowInterfacelinkAndVerifyEditInterfacePageforProviderEquipment("LANLINK", map.get("Interfacename_forEditInterface"));
////////			
////////			Outband.get().EnterdataForEditInterfaceforShowInterfacelinkunderProviderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
////////					map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
////	////	
////	////System.out.println("------------CONFIGURE link for Provider Equipment--------------15");
////	////Outband.get().selectconfigurelinkAndverifyForProviderEquipment("LANLINK", map.get("Interfacename_forEditInterface"));
////	////   
////	////Outband.get().returnbacktoviewsiteorderpage("LANLINK");
////	////    
////	////System.out.println("----------------Delete device from service------------------ ");
////	////Outband.get().deleteDeviceFromServiceForProviderequipment("LANLINK", map.get("deleteDeviceforequipment"));
////	////	
////	////	
////	////System.out.println("-----------PE to CPE link-----------");		
////	////Outband.get().verifyAddnewlinkunderPE2CPEtable("LANLINK");
////	////Outband.get().enterdataforAddNewlinkunderPEtoCPEtable("LANLINK", map.get("Addnewlink_Circuitid"), map.get("Addnewlink_sourcedevice"), 
////////		                       map.get("Addnewlink_sourceInterface"), map.get("Addnewlink_targetdevice"), map.get("enterdataforAddNewlinkunderPEtoCPEtable"), map.get("Addnewlink_interfacename"));
////	////
////	////
////	////
////	////
////	////
////	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////	////
////	////
////	////Outband.get().CustomerPremiseEquipment("serviceSelected",
////////			map.get("IMS pop locations_MAS_PE"), map.get("Select/Create device_PE"), map.get("Name_Mas_PE"),
////////			map.get("VendorModel_PE"), map.get("Address_PE"), map.get("Snmpro_PE"), map.get("country_PE"),
////////			map.get("city_PE"), map.get("site_PE"), map.get("premise_PE"));
////	////
////	////
////	////Outband.get().selectInterfacelinkforCustomerpremiseequipment("LANLINK");
////	////Outband.get().AddInterfaceToserviceforProviderEquipment("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
////	////Outband.get().verifyInterfaceaddedToServiceForProviderEquipment("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
////	////Outband.get().ProviderEquipmentInterfaceInService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
////	////Outband.get().verifyInterfaceremovedFromServiceforProviderEquipment("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
////	////
////	////
////	////System.out.println("-----------Show Interface link for Provider Equipment----------14");
////	////Outband.get().SelectShowInterfacelinkAndVerifyEditInterfacePageforCustomerPremiseEquipment("LANLINK", map.get("Interfacename_forEditInterface"));
////	////
////	////Outband.get().EnterdataForEditInterfaceforShowInterfacelinkunderProviderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
////////		map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
////	////
////	////System.out.println("------------CONFIGURE link for Provider Equipment--------------15");
////	////Outband.get().selectconfigurelinkAndverifyForProviderEquipment("LANLINK", map.get("Interfacename_forEditInterface"));
////	////
////	////Outband.get().returnbacktoviewsiteorderpage("LANLINK");
////	////
////	////System.out.println("----------------Delete device from service------------------ ");
////	////Outband.get().deleteDeviceFromServiceForProviderequipment("LANLINK", map.get("deleteDeviceforequipment"));
////	////
////	////}
////	////
////	////
////	////
////	////
////	////}
////	//
////	//
////	//
////	////@Test(dataProviderClass = APT_DataReader_sanjeev.class, dataProvider = "DataReader_createService")
////	////public void sanjeev(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
////	////
////	////
////	////
////	////
////	////}
////	//
////		
////		
////		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=20)
////	    public void Lanlink_Circuit(Map<String, String> map) throws Exception {
////			
////			if(map.get("CircuitType").equalsIgnoreCase("Composite Circuit")) {
////				
////				if(map.get("Interfacespeed").equals("10GigE")) {
////					
////					Outband.get().addOverture("LANLINK", map.get("servicename"));
////					
////				}
////				
////				else if(map.get("Interfacespeed").equals("1GigE")) {
////					
////					
////					
////				}
////				
////				
////			}
////			
////			
////		}
////
////		
////		
////
////
}
