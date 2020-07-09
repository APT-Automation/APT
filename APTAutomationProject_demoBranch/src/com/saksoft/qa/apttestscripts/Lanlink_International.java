package com.saksoft.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;

public class Lanlink_International extends DriverTestcase{
	

	   APT_Login login = new APT_Login();
	   
	  // DataReader_PK
	   
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=1)
		public void chooseCustomer(Map<String, String> map) throws Exception {

			
			DriverTestcase.logger = DriverTestcase.extent.startTest("chooseCustomer");
			
			System.out.println("-------Login functionality------------1");
			login.APT_Login_1();
			
		System.out.println("Login is done");
			DriverTestcase.logger.log(LogStatus.INFO,"The service type to be created is: "+map.get("Servicesubtype"));

			International.get().selectCustomertocreateOrder("CreateOrderService",map.get("ChooseCustomerToBeSelected"),map.get("Customername"));
			
	}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=2)
		public void verifyListofFieldsForOrderandServicetype(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyListofFieldsForOrderandServicetype");
			
			System.out.println("-------Verify the fields for Service type and select the service sub type------------1");	
		
			International.get().Verifyfields(("CreateOrderService"),map.get("ServiceType"), map.get("Modularmsp"), map.get("AutocreateService"));
			International.get().selectCustomertocreateOrderfromleftpane("CreateOrderService",map.get("ChooseCustomerToBeSelected"),map.get("Customername"));
			International.get().SelectServiceType("CreateOrderService", map.get("Order/ContractNumber"),map.get("ServiceType"), map.get("Ordertype"), map.get("valuetobeselectedinorderdropdown"));

		}
		
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=3)
		public void selectTheServiceType(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("selectTheServiceType");
			
			System.out.println("------After selecting service type------2");
			International.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
			
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=4)
		public void verifyFieldsFortheSubServicetypeselelctedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFieldsFortheSubServicetypeselelctedunderLANLINK");
			
			System.out.println("----Verify the fields for Service sub type selected----------3");
			International.get().VerifyFieldsForServiceSubTypeSelected("LANLINK",map.get("ServiceType"),map.get("Servicesubtype"), map.get("Interfacespeed"),
					map.get("Notification management"), map.get("vpnTopology"), map.get("AggregateTraffic"), map.get("Modularmsp"));	
		
		}
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=5)
		public void enterdatafortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("enterdatafortheServiceSubtypeSelectedunderLANLINK");
			
			System.out.println("------Enter data for the service sub type selected-------4");	
			International.get().SelectServiceType2("CreateOrderService", map.get("Order/ContractNumber"),map.get("ServiceType"), map.get("Ordertype"), map.get("valuetobeselectedinorderdropdown"));
			International.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
						map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
			International.get().dataToBeEnteredOncesubservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
					map.get("performanceReportngForServices"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
					map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"), map.get("Notification management"), map.get("performanceReportngForServices"),
					map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), 
					map.get("premiumEIR_ServiceCreation"), map.get("E_VPNtechnology"), map.get("HCoSPerformanceReporting"));
		   
		}
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=6)
		public void successmessageforServicecreation(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("successmessageforServicecreation");
			Thread.sleep(2000);
			
			 International.get().verifysuccessmessage("LANLINK", "Service successfully created");		
			
		}
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=7)
		public void verifydataenteredwhilecreatingServiceSubtypeunderLANLINKinviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifydataenteredwhilecreatingServiceSubtypeunderLANLINKinviewservicepage");
			
			System.out.println("-------- Verify data entered under service sub types---------5");		
				International.get().VerifydatenteredForServiceSubTypeSelected("LANLINK",map.get("servicetypeforverification"),map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
						map.get("PerformMonitor"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
						map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
						map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"),  map.get("performanceReportngForServices"),
						map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), 
						map.get("premiumEIR_ServiceCreation"), map.get("Notification management"),  map.get("E_VPNtechnology"), map.get("HCoSPerformanceReporting"));
			
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=8)
		public void editServiceSubtypesunderLANLINKservice(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("editServiceSubtypesunderLANLINKservice");
			
			System.out.println("----------- Edit the service type -----------------");	
			International.get().EditTheservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Edit_serviceNumber"),map.get("Edit_endpointCPE"),map.get("EditService_email"), map.get("EditService_phone"), map.get("EditService_remark"), 
					map.get("EditService_PerformMonitor"),map.get("EditService_proactiveMonitor"), map.get("EditService_deliveryChannel"), map.get("EditService_ManagementOrder"), map.get("vpnTopology"), map.get("EditService_intermediateTechnology"),
					map.get("EditService_CircuitReference"), map.get("EditService_CircuitType"), map.get("EditService_AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("EditService_ENNI"), map.get("EditService_manageConnectiondropdown"), map.get("Edit_A_Endtechnology"), map.get("Edit_B_Endtechnology"), map.get("EditService_NotificationManagement"),
					map.get("EditService_perCoSperformanceReport"), map.get("EditService_actelisBased"), map.get("EditService_standardCIR"), map.get("EditService_standardEIR"), map.get("EditService_premiumCIR"), map.get("EditService_premiumEIR"), 
					map.get("CircuitType"), map.get("EditService_EVPNtechnology"), map.get("EditService_HCoSPerformanceReporting"));
			
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=9)
		public void successmessageforServiceUpdation(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("successmessageforServiceUpdation");
			Thread.sleep(2000);
			
			 International.get().verifysuccessmessage("LANLINK", "Service successfully updated.");		
			
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=9)
		public void synchronizeServiceSubtypeforLANLINKserviceunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("synchronizeServiceSubtypeforLANLINKserviceunderviewservicepage");
			Thread.sleep(3000);
	
			System.out.println("Entered sync service");
			International.get().syncservices("LANLINK");
			
		}
		
		
	
//		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=10)
		public void showNewInfovistaReportunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("showNewInfovistaReportunderviewservicepage");
			Thread.sleep(3000);
			
			System.out.println("Entered show info vista");
			International.get().shownewInfovista("LANLINK");
			
		}
	
		
//		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=11)
		public void manageServiceunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("manageServiceunderviewservicepage");
			Thread.sleep(3000);
			
			System.out.println("Enetred manage service");
			International.get().manageService("LANLINK");
			
		}
		
		
	
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=12)
		public void ManageSubnetsForlanlinkServiceunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("ManageSubnetsForlanlinkServiceunderviewservicepage");
			Thread.sleep(3000);
			
			System.out.println("Entered manage sub nets");
			International.get().manageSubnets("LANLINK");
			
		}
			

		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=13)
		public void verifyAddSiteOrderFieldsfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddSiteOrderFieldsfortheServiceSubtypeSelectedunderLANLINK");
			Thread.sleep(3000);
			
			System.out.println("------Verify fields for add site order-------- 6");
			
			International.get().Enteraddsiteorder("LANLINK");
			International.get().verifyAddsiteorderFields("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					 map.get("SiteOrder_Offnet"),map.get("SiteOrder_EPNoffnet"), map.get("SiteOrder_EPNEOSDH"), map.get("Modularmsp"), map.get("AggregateTraffic"));
		
			International.get().Enteraddsiteorder("LANLINK");
			International.get().addsiteorder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					map.get("country"),map.get("city"),map.get("CSR_Name"), 
					 map.get("sitevalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
					   map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("existing_SiteOrdervalue"),
					   map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
					   map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
					   map.get("Siteordernumber"), map.get("siteOrder_CircuitReference"), map.get("SiteOrder_Offnet"), map.get("SiteOrder_Ivrefrence"),map.get("SiteOrer_GCROloType"),
			  		   map.get("SiteOrder_Vlan"), map.get("SiteOrder_Vlanethertype"),map.get("siteOrder_PrimaryVlan"), map.get("SiteOrder_PrimaryVlanEtherType"), map.get("SiteOrder_EPNoffnet"), 
			  		   map.get("SiteOrder_EPNEOSDH"), map.get("SiteOrder_mappingMode"), map.get("SiteOrder_portBased"), map.get("SiteOrder_vlanBased"), map.get("Modularmsp"), map.get("AggregateTraffic"));
			
		}
	
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=15)
		public void SuccessmessageforCreationofSiteOrder(Map<String, String> map) throws Exception {
			
	   System.out.println("---- verify success messgae for adding site order-------7");
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("SuccessmessageforCreationofSiteOrder");
			Thread.sleep(3000);
		
			International.get().verifysuccessmessage("LANLINK", "Site order created successfully");
		}
		
		
	
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=16)
		public void verifytheDataEnteredforAddSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifytheDataEnteredforAddSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
			Thread.sleep(3000);
			
			
			International.get().VerifyDataEnteredForSiteOrder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					map.get("country"),map.get("city"),map.get("CSR_Name"), 
					 map.get("existing_SiteOrdervalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
					   map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("sitevalue"),
					   map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
					   map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
					   map.get("Siteordernumber"), map.get("siteOrder_CircuitReference"), map.get("SiteOrder_Offnet"), map.get("SiteOrder_Ivrefrence"),map.get("SiteOrer_GCROloType"),
			  		   map.get("SiteOrder_Vlan"), map.get("SiteOrder_Vlanethertype"),map.get("siteOrder_PrimaryVlan"), map.get("SiteOrder_PrimaryVlanEtherType"), map.get("SiteOrder_EPNoffnet"),
			  		   map.get("SiteOrder_EPNEOSDH"), map.get("Modularmsp"), map.get("AggregateTraffic"));
			
			
			
//			System.out.println("----verify details inside the siteorder table under view service page-----------");
//				International.get().VerifySiteOrderdetailsInTable("LANLINK", map.get("Siteordernumber"));
		
		}
//		
////		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=17)
////		public void verifyFieldsforEditSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
////			
////			System.out.println("-------verify Edit site order fields--------8");
////			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFieldsforEditSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
////			
////			
////			
////			Thread.sleep(5000);
////			
////			International.get().clickonEditwithoutselectingrow("LANLINK");
////			International.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"));
////			International.get().verifyEditSiteOrder("LANLINK" ,map.get("country"),map.get("city"),map.get("CSR_Name"), 
////				 map.get("site"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
////				 map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("sitevalue"));
////			
////		}
//		
//		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=17)
		public void EditSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("EditSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
			Thread.sleep(3000);
			
			System.out.println("----- edit site order page-----------9");
			
			International.get().returnbacktoviewsiteorderpage("LANLINK");
			
			International.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"), map.get("siteOrderNumber_PointToPoint"), 
					map.get("vpnTopology"), map.get("Modularmsp"), map.get("siteOrderNumber_p2p_mspSelected"),
					 map.get("Interfacespeed"), map.get("siteOrderNumber_10G_PointToPoint"));
			
			International.get().editSiteOrder( "LANLINK", map.get("Interfacespeed") ,map.get("vpnTopology"), map.get("EditSiteOrder_performReport"), map.get("EditSiteOrder_ProactiveMonitor"),map.get("EditSiteOrder_smartmonitor"),
			     map.get("EditSiteOrder_siteallias"), map.get("EditSiteOrder_VLANid"), map.get("EditSiteOrder_DCAenabledsite"), map.get("EditSiteOrder_cloudserviceprovider"), map.get("technology"), 
			     map.get("editsiteorder_nonterminationpoint"), map.get("editsiteorder_protected"), map.get("editsiteorder_devicename"), map.get("editsiteorder_remark"), map.get("SiteOrder_Ivrefrence"),
			     map.get("Siteordernumber"), map.get("editsiteorder_circuitReference"), map.get("editsiteorder_GCRoloType"), map.get("editsiteorder_VLAN"), map.get("editsiteorder_VlanEtherType"),
			     map.get("editsiteorder_primaryVLAN"),map.get("editsiteorder_primaryVlanEtherType"), map.get("SiteOrder_Offnet"), map.get("editSiteOrder_Offnet"), map.get("editSiteOrder_EPNOffnet"),
			     map.get("editSiteOrder_mappingMode"), map.get("editSiteOrder_portbased"), map.get("editSiteorder_VlanBased"), map.get("SiteOrder_mappingMode"),  map.get("Modularmsp"), map.get("AggregateTraffic"), map.get("editEpNEosDH"));
		
			International.get().verifysuccessmessage("LANLINK", "Site Order successfully updated.");
		
		
		}
		
//	
//		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=18)
//		public void ViewSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
//			
//		System.out.println("--------- view site order page--------11");
//	
//		DriverTestcase.logger = DriverTestcase.extent.startTest("ViewSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
//		Thread.sleep(3000);
//		
//		International.get().returnbacktoviewsiteorderpage("LANLINK");	
//		
//		International.get().clickonviewewithoutselectingrow("LANLINK");		
//			International.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"));
//			International.get().viewsiteorderlink("LANLINK");	
//			
//			
//		
//		}
//		
//		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=18)
		public void Actelis(Map<String, String> map) throws Exception {
			
			String Technologyname=map.get("technology");
			if(Technologyname.equalsIgnoreCase("Actelis")) {
			
			boolean equipConfigurationPanel=OLO.get().EquipmentCOnfigurationPanel("LANLINK");
			
			if(equipConfigurationPanel) {
				
				International.get().equipConfiguration_Actelis_AddDevice("LANLINK", map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
						map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"), map.get("ActelisTech_addCPE_ETH_Port"));
			
				International.get().verifysuccessmessage("LANLINK", "Device successfully created");
				
				International.get().verifyDataEnteredFordeviceCreation_Actelis("LANLINK",  map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
						map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"),  map.get("ActelisTech_addCPE_ETH_Port"));
				
				International.get().returnbacktoviewsiteorderpage("LANLINK");
				
				International.get().deleteDeviceFromService_EquipmentConfig_Actelis("LANLINK", map.get("deleteDeviceSelection_EquipmentConfiguration"), map.get("ActelisTech_addCPE_name"));
				
				International.get().successMessage_deleteFromService("LANLINK");
			}
			
		//Actelis Configuration panel	
			International.get().verifyAddDSLAMandHSLlink("LANLINK", map.get("ActelisTech_DSLAMdevice"));
			
			International.get().AddDSLAMandHSL("LANLINK", map.get("ActelisTech_DSLAMdevice"), map.get("ActelisTech_DSLAMInterfacename"));
			
			International.get().showInterface_ActelisConfiguuration("LANLINK");
			
			International.get().deletInterface_ActelisConfiguration("LANLINK", map.get("ActelisTech_DSLAMInterfacename"), map.get("deleteInterfcae_ActelisConfiguration"));
			
			International.get().successMessage_deleteInterfaceFromDevice_ActelisConfiguration("LANLINK");
			
			}
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=19)
		public void AdddeviceforEquipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("AdddeviceforEquipment");
			Thread.sleep(3000);
		
		//verify whether Equipment panel is available	
			boolean EquipmentPanel=International.get().findPanelHeader("LANLINK", "Equipment");
			
			if(EquipmentPanel) {
			
				
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
				String ModularMSp= map.get("Modularmsp");
				
				if(ModularMSp.equalsIgnoreCase("Yes")) 
				{
					International.get().verifyFieldsandAddCPEdevicefortheserviceselected_MSPselected("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_modularMSpselected"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
							 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
							 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"),
							 map.get("cpe_Mspselected_VLANid"));
			
					International.get().verifysuccessmessage("LANLINK", "Device successfully created");
					
					International.get().verifydetailsEnteredforCPEdevice_MSPselected( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_modularMSpselected"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
						 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
						 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"), map.get("cpe_newmanagementAddressSelection"), 
						 map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"), map.get("cpe_Mspselected_VLANid"));
					
					International.get().eDITCPEdevicedetailsentered_MSPselected( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_MSPselected"),  map.get("cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
							 map.get("EDIT_cpe_vender_MSPselected_VLANid") );
					
					International.get().verifysuccessmessage("LANLINK", "Device successfully updated");
				}
				else
				{
					if(speed.equals("1GigE")) {
						
						International.get().verifyFieldsandAddCPEdevicefortheserviceselected_1G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
								 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"));
				
						International.get().verifysuccessmessage("LANLINK", "Device successfully created");
						
						International.get().verifydetailsEnteredforCPEdevice_1G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
							 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
							 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"), map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"));
						
						International.get().eDITCPEdevicedetailsentered_1G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_1G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"), map.get("technology"));
						
						International.get().verifysuccessmessage("LANLINK", "Device successfully updated");
					}
					
					
					if(speed.equals("10GigE")) {
						
						International.get().verifyFieldsandAddCPEdevicefortheserviceselected_10G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
								 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
						
						International.get().verifysuccessmessage("LANLINK", "Device successfully created");
						
						International.get().verifydetailsEnteredforCPEdevice_10G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
								 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),  map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
						
						International.get().eDITCPEdevicedetailsentered_10G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_10G"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_10G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"));
				
						International.get().verifysuccessmessage("LANLINK", "Device successfully updated");
						
						Metro.get().returnbacktoviewsiteorderpage("LANLINK");
					}
				}	
				
				//Perform fetch from Interface
					International.get().fetchDeviceInterface_viewdevicepage("LANLINK");
					Thread.sleep(3000);
					
					International.get().returnbacktoviewsiteorderpage("LANLINK");
					
				if(map.get("EDIT_cpename").equalsIgnoreCase("null")) {
					
					//Find list of Interfaces	
					International.get().findlistofInterfaces_Equipment_viewdevicePage("LANLINK", map.get("devicename_equip"));
					Thread.sleep(3000);
					
				   International.get().returnbacktoviewsiteorderpage("LANLINK");
				   
				 //Configure  
				   System.out.println("--------------Select Configure link under Equipment------------------");	
					International.get().selectconfigurelinkAndverifyEditInterfacefield__Equipment("LANLINK", map.get("devicename_equip"));
					International.get().EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
							map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
					International.get().verifyeditedinterfacevalue("LANLINK", map.get("Interfacename_forEditInterface"));
					
					International.get().returnbacktoviewsiteorderpage("LANLINK");
					
					System.out.println("-----------Show Interface link for Equipment----------14");
							International.get().SelectShowInterfacelinkAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"), map.get("devicename_equip"));
							International.get().EnterdataForEditInterfaceforShowInterfacelinkunderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
									map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
					
					
					System.out.println("--------SELECTINTERFACE link for Equipment--------13");
					
						International.get().selectInterfacelinkforEqipment("LANLINK", map.get("devicename_equip"));
						
						if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
							International.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//							International.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
						}else {
							System.out.println("interfaces are not removed");
						}
						
						if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
							International.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//							International.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
						}else {
							
						}
						 
						International.get().returnbacktoviewsiteorderpage("LANLINK");
					
					System.out.println("----------------Delete device from service------------------ ");
						International.get().deleteDeviceFromServiceForequipment("LANLINK", map.get("deleteDeviceSelection_equipment"), map.get("devicename_equip"));
						International.get().successMessage_deleteFromService("LANLINK");
					
				}else{
					
					//Find list of Interfaces	
					International.get().findlistofInterfaces_Equipment_viewdevicePage("LANLINK", map.get("EDIT_cpename"));
					Thread.sleep(3000);
					
				   International.get().returnbacktoviewsiteorderpage("LANLINK");
				   
				 //Configure  
				   System.out.println("--------------Select Configure link under Equipment------------------");	
					International.get().selectconfigurelinkAndverifyEditInterfacefield__Equipment("LANLINK", map.get("EDIT_cpename"));
					International.get().EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
							map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
					International.get().verifyeditedinterfacevalue("LANLINK", map.get("Interfacename_forEditInterface"));
					
					International.get().returnbacktoviewsiteorderpage("LANLINK");
					
					System.out.println("-----------Show Interface link for Equipment----------14");
							International.get().SelectShowInterfacelinkAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"), map.get("EDIT_cpename"));
							International.get().EnterdataForEditInterfaceforShowInterfacelinkunderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
									map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
					
					
					System.out.println("--------SELECTINTERFACE link for Equipment--------13");
					
						International.get().selectInterfacelinkforEqipment("LANLINK", map.get("EDIT_cpename"));
						
						if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
							International.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//							International.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
						}else {
							System.out.println("interfaces are not removed");
						}
						
						if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
							International.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//							International.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
						}else {
							
						}
						 
						International.get().returnbacktoviewsiteorderpage("LANLINK");
					
					System.out.println("----------------Delete device from service------------------ ");
						International.get().deleteDeviceFromServiceForequipment("LANLINK", map.get("deleteDeviceSelection_equipment"), map.get("EDIT_cpename"));
						International.get().successMessage_deleteFromService("LANLINK");
				
				}
				}
				}		
		}	
		

		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=20)
		public void IntermediateEquipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("IntermediateEquipment");
			Thread.sleep(3000);
			  
			
			
			boolean IntermediateEquipmentPanel=International.get().findPanelHeader("LANLINK", "Intermediate Equipment");
			
			if(IntermediateEquipmentPanel) {
				
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
		String ModularMSp= map.get("Modularmsp");
		
		if(ModularMSp.equalsIgnoreCase("yes")) 
		{

			International.get().addDevice_IntEquipment("LANLINK");
			International.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
			International.get().verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_MSPselected( "LANLINK",  map.get("device_intEquip_name"),
					map.get("device_intEquip_vender_MSPSelected_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"),
					map.get("device_intequip_Mepid"), map.get("device_intEquip_VLANid"),map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
					map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
					map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
					map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
					map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));	
			International.get().verifysuccessmessage("LANLINK", "Device successfully created");
			
			International.get().verifyCPEdevicedataenteredForIntermediateEquipment_MSPselected( "LANLINK",  map.get("device_intEquip_name"), 
					map.get("device_intEquip_vender_MSPSelected_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
					 map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
					map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
					map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
					map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
					map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"),
					map.get("TechToBeselected_underTechpopup_device"), map.get("device_intEquip_VLANid"));

			International.get().EDITCPEdevicedforIntermediateEquipment_MSPselected("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"),map.get("EDIT_Intequip_device_vender_MSPselected"),
					 map.get("EDIT_Intequip_devic_snmpro"), map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),map.get("EDIT_Intequip_device_country"),
				    map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
					,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
					map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"), 
					map.get("TechToBeselected_underTechpopup_device"), map.get("EDIT_Intequip_VLANid_MSPselected"));
			
			International.get().verifysuccessmessage("LANLINK", "Device successfully updated");
			}
		else
		{
			if(speed.equals("1GigE")) {

				International.get().addDevice_IntEquipment("LANLINK");
				International.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
				International.get().verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_1G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_1G_Overture"),
						map.get("device_intequip_vender_1G_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
						map.get("device_intequip_poweralarm_1G_overture"), map.get("device_intequip_poweralarm_1G_Accedian"), map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
						map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
						map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
						map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
						map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
						map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));	
				International.get().verifysuccessmessage("LANLINK", "Device successfully created");
				
				International.get().verifyCPEdevicedataenteredForIntermediateEquipment_1G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_1G_Overture"),
						map.get("device_intequip_vender_1G_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
						map.get("device_intequip_poweralarm_1G_overture"), map.get("device_intequip_poweralarm_1G_Accedian"), map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
						map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
						map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
						map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
						map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
						map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));

				International.get().EDITCPEdevicedforIntermediateEquipment_1G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_1G_Overtue"),map.get("EDIT_Intequip_device_vender_1G_Accedian"),
						map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
						map.get("EDIT_Intequip_device_poweralarm_1G_Overture"),map.get("EDIT_Intequip_device_poweralarm_1G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
						map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"),
						map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
						,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
						map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"), 
						map.get("TechToBeselected_underTechpopup_device"));
				
				International.get().verifysuccessmessage("LANLINK", "Device successfully updated");
				}
				
				else if(speed.equals("10GigE")) {
					
					International.get().addDevice_IntEquipment("LANLINK");
					International.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
					International.get().verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_10G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_10G_Accedian"),
							map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
							map.get("device_intequip_poweralarm_10G_Accedian"),map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
							map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
							map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
							map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
							map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
							map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));	
					International.get().verifysuccessmessage("LANLINK", "Device successfully created");
					
					International.get().verifyCPEdevicedataenteredForIntermediateEquipment_10G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_10G_Accedian"),
							map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
							map.get("device_intequip_poweralarm_10G_Accedian"),map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
							map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
							map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
							map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
							map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
							map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));
				   
//				   International.get().returnbacktoviewsiteorderpage("LANLINK");
					International.get().EDITCPEdevice_IntermediateEquipment_10G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), 
						   map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
							map.get("EDIT_Intequip_device_poweralarm_10G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
							map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"),
							map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
							,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
							map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
							map.get("TechToBeselected_underTechpopup_device"));
					International.get().verifysuccessmessage("LANLINK", "Device successfully updated");
					
					International.get().returnbacktoviewsiteorderpage("LANLINK");
				}
		}
		
		//Perform fetch from Interface
		International.get().fetchDeviceInterface_viewdevicepage("LANLINK");
		Thread.sleep(3000);
		
		International.get().returnbacktoviewsiteorderpage("LANLINK");
		
		
		if(map.get("EDIT_Intequip_cpe_deviecname").equalsIgnoreCase("null")) {
			
			//Find list of Interfaces	
			International.get().findlistofInterfaces_IntEquipment_viewdevicePage("LANLINK", map.get("device_intEquip_name"));
			Thread.sleep(3000);
			
		   International.get().returnbacktoviewsiteorderpage("LANLINK");
		
		
		System.out.println("-----------Show Interface link for Equipment----------14");
		International.get().SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"),  map.get("device_intEquip_name"));
		International.get().EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
				map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
		
		
		System.out.println("--------SELECTINTERFACE link for Equipment--------13");
		
			International.get().selectInterfacelinkforIntermediateEqipment("LANLINK", map.get("device_intEquip_name"));
			
			if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
				International.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//				International.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
			}else {
				System.out.println("interfaces are not removed");
			}
			
			if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
				International.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//				International.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
			}

	International.get().returnbacktoviewsiteorderpage("LANLINK");

	System.out.println("-------------Delete device from service for intermediate equipment---------");
	International.get().deleteDeviceFromServiceForIntermediateequipment("LANLINK", map.get("deleteDeviceSelection_Intermediateequipment"), map.get("device_intEquip_name"));
	International.get().successMessage_deleteFromService("LANLINK");
			
		}else {
			
			//Find list of Interfaces	
			International.get().findlistofInterfaces_IntEquipment_viewdevicePage("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"));
			Thread.sleep(3000);
			
		   International.get().returnbacktoviewsiteorderpage("LANLINK");
		
		
		System.out.println("-----------Show Interface link for Equipment----------14");
		International.get().SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"),  map.get("EDIT_Intequip_cpe_deviecname"));
		International.get().EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
				map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
		
		
		System.out.println("--------SELECTINTERFACE link for Equipment--------13");
		
			International.get().selectInterfacelinkforIntermediateEqipment("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"));
			
			if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
				International.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//				International.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
			}else {
				System.out.println("interfaces are not removed");
			}
			
			if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
				International.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//				International.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
			}

	International.get().returnbacktoviewsiteorderpage("LANLINK");


	System.out.println("-------------Delete device from service for intermediate equipment---------");
	International.get().deleteDeviceFromServiceForIntermediateequipment("LANLINK", map.get("deleteDeviceSelection_Intermediateequipment"), map.get("EDIT_Intequip_cpe_deviecname"));
	International.get().successMessage_deleteFromService("LANLINK");
		}

				}		
				}
		}	
		
		
////		
////		
////		
//////		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=20)
//////		public void LanlinkNational(Map<String, String> map) throws Exception {
//////			
//////			
//////			String servicesubtype=map.get("Servicesubtype");	
////			
////			
//////		if (servicesubtype.equalsIgnoreCase("LANLink National")) {
//////				
//////							International.get().AddDSLAMandHSL("LANLINK", map.get("DSLMdevice"));
//////							International.get().AddCPEdevicefortheserviceselected( "LANLINK",  map.get("cpename"), map.get("cpe_vender"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
//////									 map.get("cpe_poweralarm"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
//////									   map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"));
//////							International.get().verifydetailsenteredforCPEdevice( "LANLINK",  map.get("cpename"), map.get("cpe_vender"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
//////									map.get("cpe_poweralarm"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
//////									   map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"));
////////							International.get().eDITCPEdevicedetailsentered( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
////////									 map.get("EDIT_cpe_poweralarm"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
////////									   map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"));
//////						
//////							
//////							
//////							
//////							
//////							International.get().addCPEdeviceforIntermediateequipment( "LANLINK",  map.get("cpeintEquip_name"), map.get("cpeintequip_vender"),  map.get("cpeintequip_snmpro"), 
//////									map.get("cpeintequip_managementAddress"), map.get("cpeintequip_Mepid"),
//////									map.get("cpeintequip_poweralarm"), map.get("cpeintequip_Mediaselection"),  map.get("cpeintequip_Macaddress"),  map.get("cpeintequip_serialNumber"),
//////									map.get("cpeintequip_hexaSerialnumber"),  map.get("cpeintequip_linkLostForwarding"), map.get("cpeintequip_country"), map.get("cpeintequip_city"),
//////									map.get("cpeintequip_site"), map.get("cpeintequip_premise"));	
//////						   International.get().verifyCPEdevicedataenteredForIntermediateEquipment( "LANLINK",  map.get("cpeintEquip_name"), map.get("cpeintequip_vender"),  map.get("cpeintequip_snmpro"), 
//////									map.get("cpeintequip_managementAddress"), map.get("cpeintequip_Mepid"),
//////									map.get("cpeintequip_poweralarm"), map.get("cpeintequip_Mediaselection"),  map.get("cpeintequip_Macaddress"),  map.get("cpeintequip_serialNumber"),
//////									map.get("cpeintequip_hexaSerialnumber"),  map.get("cpeintequip_linkLostForwarding"), map.get("cpeintequip_country"), map.get("cpeintequip_city"),
//////									map.get("cpeintequip_site"), map.get("cpeintequip_premise"));	
//////						   International.get().EDITCPEdevicedforIntermediateEquipment( "LANLINK",  map.get("cpeintEquip_name"), map.get("cpeintequip_vender"),  map.get("cpeintequip_snmpro"), 
//////									map.get("cpeintequip_managementAddress"), map.get("cpeintequip_Mepid"),
//////									map.get("cpeintequip_poweralarm"), map.get("cpeintequip_Mediaselection"),  map.get("cpeintequip_Macaddress"),  map.get("cpeintequip_serialNumber"),
//////									map.get("cpeintequip_hexaSerialnumber"),  map.get("cpeintequip_linkLostForwarding"), map.get("cpeintequip_country"), map.get("cpeintequip_city"),
//////									map.get("cpeintequip_site"), map.get("cpeintequip_premise"));
//////							
//////						   International.get().returnbacktoviewsiteorderpage("LANLINK");
//////						   
//////					     System.out.println("------"+ "SELECTINTERFACE"+ "link for Intermediate Equipment--------16");
//////							International.get().selectInterfacelinkforIntermediateEqipment("LANLINK");	
//////							International.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));  
//////							International.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//////							International.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//////							International.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//////							
//////							International.get().returnbacktoviewsiteorderpage("LANLINK");
//////						
//////					     System.out.println("--------------Select Configure link under Intermediate Equipment------------------");	
//////							International.get().selectconfigurelinkunderIntermediateEquipmentAndverifyEditInterfacefield("LANLINK", map.get("Interfacename_forEditInterface"));
//////							International.get().EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
//////									map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
//////						    
//////						
//////							International.get().returnbacktoviewsiteorderpage("LANLINK");
//////							
//////						 System.out.println("-----------Show Interface link for Equipment----------14");
//////							International.get().SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"));
//////							International.get().EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
//////									map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
//////						
//////				
//////					     System.out.println("-------------Delete device from service for intermediate equipment---------");
//////							International.get().deleteDeviceFromServiceForIntermediateequipment("LANLINK", map.get("deleteDeviceforIntermediateequipment"));
//////				
//////							
//////							
//////						}
//////				
//////		}
//////			
////	//	
////	//	
////////		 International.get().verifychoosecustomer("CreateOrderService", map.get("Name"),
////////		map.get("Customer"));
////	//
////	////
////	////System.out.println("--------delete site order -----------10");
////	////International.get().clickondeletewithoutselectingrow("LANLINK");		
////	////International.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"));
////	////International.get().deletesiteorderdetails("LANLINK");
////	////
////	////
////	////
////	////else if (servicesubtype.equalsIgnoreCase("LANLink outbandmanagement")) {
////	//
////	////International.get().VerifythefieldsforProviderEquipment("LANLINK");
////	////International.get().providerEquipment("LANLINK", map.get("IMS pop locations_MAS_PE"),
////////			map.get("Select/Create device_PE"), map.get("Name_Mas_PE"), map.get("VendorModel_PE"),
////////			map.get("Address_PE"), map.get("Snmpro_PE"), map.get("country_PE"), map.get("city_PE"),
////////			map.get("site_PE"), map.get("premise_PE"));
////	////International.get().verifyPEdeviceEnteredvalue("LANLINK", map.get("Name_Mas_PE"),
////////			map.get("VendorModel_PE"), map.get("Address_PE"), map.get("Snmpro_PE"), map.get("country_PE"),
////////			map.get("city_PE"), map.get("site_PE"), map.get("premise_PE"));
////	////International.get().EditProviderEquipment("LANLINK", map.get("IMS pop locations_MAS_PE"),
////////			map.get("Select/Create device_PE"), map.get("Name_Mas_PE"), map.get("VendorModel_PE"),
////////			map.get("Address_PE"), map.get("Snmpro_PE"), map.get("country_PE"), map.get("city_PE"),
////////			map.get("site_PE"), map.get("premise_PE"));
////	////
////////				
////	////International.get().returnbacktoviewsiteorderpage("LANLINK");
////	////
////	////System.out.println("--------SELECTINTERFACE link for Provider Equipment--------");
////	////International.get().selectInterfacelinkforProviderEqipment("LANLINK");
////	////International.get().AddInterfaceToserviceforProviderEquipment("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
////	////International.get().verifyInterfaceaddedToServiceForProviderEquipment("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
////	////International.get().ProviderEquipmentInterfaceInService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
////	////International.get().verifyInterfaceremovedFromServiceforProviderEquipment("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
////	////
////	////International.get().returnbacktoviewsiteorderpage("LANLINK");
////	////	
////	////System.out.println("-----------Show Interface link for Provider Equipment----------14");
////////			International.get().SelectShowInterfacelinkAndVerifyEditInterfacePageforProviderEquipment("LANLINK", map.get("Interfacename_forEditInterface"));
////////			
////////			International.get().EnterdataForEditInterfaceforShowInterfacelinkunderProviderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
////////					map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
////	////	
////	////System.out.println("------------CONFIGURE link for Provider Equipment--------------15");
////	////International.get().selectconfigurelinkAndverifyForProviderEquipment("LANLINK", map.get("Interfacename_forEditInterface"));
////	////   
////	////International.get().returnbacktoviewsiteorderpage("LANLINK");
////	////    
////	////System.out.println("----------------Delete device from service------------------ ");
////	////International.get().deleteDeviceFromServiceForProviderequipment("LANLINK", map.get("deleteDeviceforequipment"));
////	////	
////	////	
////	////System.out.println("-----------PE to CPE link-----------");		
////	////International.get().verifyAddnewlinkunderPE2CPEtable("LANLINK");
////	////International.get().enterdataforAddNewlinkunderPEtoCPEtable("LANLINK", map.get("Addnewlink_Circuitid"), map.get("Addnewlink_sourcedevice"), 
////////		                       map.get("Addnewlink_sourceInterface"), map.get("Addnewlink_targetdevice"), map.get("enterdataforAddNewlinkunderPEtoCPEtable"), map.get("Addnewlink_interfacename"));
////	////
////	////
////	////
////	////
////	////
////	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////	////
////	////
////	////International.get().CustomerPremiseEquipment("serviceSelected",
////////			map.get("IMS pop locations_MAS_PE"), map.get("Select/Create device_PE"), map.get("Name_Mas_PE"),
////////			map.get("VendorModel_PE"), map.get("Address_PE"), map.get("Snmpro_PE"), map.get("country_PE"),
////////			map.get("city_PE"), map.get("site_PE"), map.get("premise_PE"));
////	////
////	////
////	////International.get().selectInterfacelinkforCustomerpremiseequipment("LANLINK");
////	////International.get().AddInterfaceToserviceforProviderEquipment("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
////	////International.get().verifyInterfaceaddedToServiceForProviderEquipment("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
////	////International.get().ProviderEquipmentInterfaceInService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
////	////International.get().verifyInterfaceremovedFromServiceforProviderEquipment("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
////	////
////	////
////	////System.out.println("-----------Show Interface link for Provider Equipment----------14");
////	////International.get().SelectShowInterfacelinkAndVerifyEditInterfacePageforCustomerPremiseEquipment("LANLINK", map.get("Interfacename_forEditInterface"));
////	////
////	////International.get().EnterdataForEditInterfaceforShowInterfacelinkunderProviderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
////////		map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
////	////
////	////System.out.println("------------CONFIGURE link for Provider Equipment--------------15");
////	////International.get().selectconfigurelinkAndverifyForProviderEquipment("LANLINK", map.get("Interfacename_forEditInterface"));
////	////
////	////International.get().returnbacktoviewsiteorderpage("LANLINK");
////	////
////	////System.out.println("----------------Delete device from service------------------ ");
////	////International.get().deleteDeviceFromServiceForProviderequipment("LANLINK", map.get("deleteDeviceforequipment"));
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
////	////@Test(dataProviderClass = APT_DataReader_sanjeev.class, dataProvider = "DataReader_createService_International")
////	////public void sanjeev(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
////	////
////	////
////	////
////	////
////	////}
////	//
////		
////		
////		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService_International", priority=20)
////	    public void Lanlink_Circuit(Map<String, String> map) throws Exception {
////			
////			if(map.get("CircuitType").equalsIgnoreCase("Composite Circuit")) {
////				
////				if(map.get("Interfacespeed").equals("10GigE")) {
////					
////					International.get().addOverture("LANLINK", map.get("servicename"));
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
