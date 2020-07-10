package com.saksoft.qa.apttestscripts;


import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader_SS;

import javafx.scene.control.ScrollToEvent;

public class Lanlink_DirectFiber extends DriverTestcase {
	
	
		@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=0)
		public void searchCreatedService(Map<String, String> map) throws Exception {
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("searchCreatedService"); 
			
			APT_wholesaleHelper.get().searchorder("apt", map.get("serviceNumber"));
		}
	
	
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=1)
		public void chooseCustomer(Map<String, String> map) throws Exception {

			
			DriverTestcase.logger = DriverTestcase.extent.startTest("chooseCustomer");
			
			System.out.println("-------Login functionality------------1");
			
		System.out.println("LOgin is done");
			DriverTestcase.logger.log(LogStatus.INFO,"The service type to be created is: "+map.get("Servicesubtype"));

			DirectFiber.get().selectCustomertocreateOrder("CreateOrderService",map.get("ChooseCustomerToBeSelected"),map.get("Customername"));
			
	}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=2)
		public void verifyListofFieldsForOrderandServicetype(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyListofFieldsForOrderandServicetype");
			
			System.out.println("-------Verify the fields for Service type and select the service sub type------------1");	
		
			DirectFiber.get().Verifyfields(("CreateOrderService"),map.get("ServiceType"), map.get("Modularmsp"), map.get("AutocreateService"));
			DirectFiber.get().selectCustomertocreateOrderfromleftpane("CreateOrderService",map.get("ChooseCustomerToBeSelected"),map.get("Customername"));
			DirectFiber.get().SelectServiceType("CreateOrderService", map.get("Order/ContractNumber"),map.get("ServiceType"), map.get("Ordertype"), map.get("valuetobeselectedinorderdropdown"));

		}
		
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=3)
		public void selectTheServiceType(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("selectTheServiceType");
			
			
			System.out.println("------After selecting service type------2");
			DirectFiber.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
			
		}
		
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=4)
		public void verifyFieldsFortheSubServicetypeselelctedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyFieldsFortheSubServicetypeselelctedunderLANLINK");
			
			System.out.println("----Verify the fields for Service sub type selected----------3");
			DirectFiber.get().VerifyFieldsForServiceSubTypeSelected("LANLINK",map.get("ServiceType"),map.get("Servicesubtype"), map.get("Interfacespeed"),
					map.get("Notification management"), map.get("vpnTopology"), map.get("AggregateTraffic"), map.get("Modularmsp"));	
		
		}
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=5)
		public void enterdatafortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("enterdatafortheServiceSubtypeSelectedunderLANLINK");
			
			System.out.println("------Enter data for the service sub type selected-------4");	
			DirectFiber.get().SelectServiceType2("CreateOrderService", map.get("Order/ContractNumber"),map.get("ServiceType"), map.get("Ordertype"), map.get("valuetobeselectedinorderdropdown"));
			DirectFiber.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
						map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
			DirectFiber.get().dataToBeEnteredOncesubservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
					map.get("performanceReportngForServices"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
					map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"), map.get("Notification management"), map.get("performanceReportngForServices"),
					map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), map.get("premiumEIR_ServiceCreation"));
		   
		}
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=6)
		public void successmessageforServicecreation(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("successmessageforServicecreation");
			Thread.sleep(2000);
			
			 DirectFiber.get().verifysuccessmessage("LANLINK", "Service successfully created");
			
		}
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=7)
		public void verifydataenteredwhilecreatingServiceSubtypeunderLANLINKinviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifydataenteredwhilecreatingServiceSubtypeunderLANLINKinviewservicepage");
			
			System.out.println("-------- Verify data entered under service sub types---------5");		
				DirectFiber.get().VerifydatenteredForServiceSubTypeSelected("LANLINK",map.get("servicetypeforverification"),map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
						map.get("PerformMonitor"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
						map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
						map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"),  map.get("performanceReportngForServices"),
						map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), map.get("premiumEIR_ServiceCreation"), map.get("Notification management"));
			
		}
		
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=8)
		public void editServiceSubtypesunderLANLINKservice(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("editServiceSubtypesunderLANLINKservice");
			
			System.out.println("----------- Edit the service type -----------------");	
			DirectFiber.get().EditTheservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Edit_serviceNumber"),map.get("Edit_endpointCPE"),map.get("EditService_email"), map.get("EditService_phone"), map.get("EditService_remark"), 
					map.get("EditService_PerformMonitor"),map.get("EditService_proactiveMonitor"), map.get("EditService_deliveryChannel"), map.get("EditService_ManagementOrder"), map.get("vpnTopology"), map.get("EditService_intermediateTechnology"),
					map.get("EditService_CircuitReference"), map.get("EditService_CircuitType"), map.get("EditService_AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("EditService_ENNI"), map.get("EditService_manageConnectiondropdown"), map.get("Edit_A_Endtechnology"), map.get("Edit_B_Endtechnology"), map.get("EditService_NotificationManagement"),
					map.get("EditService_perCoSperformanceReport"), map.get("EditService_actelisBased"), map.get("EditService_standardCIR"), map.get("EditService_standardEIR"), map.get("EditService_premiumCIR"), map.get("EditService_premiumEIR"), map.get("CircuitType"));
			
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=9)
		public void successmessageforServiceUpdation(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("successmessageforServiceUpdation");
			Thread.sleep(2000);
			
			 DirectFiber.get().verifysuccessmessage("LANLINK", "Service successfully updated.");
			
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=10)
		public void synchronizeServiceSubtypeforLANLINKserviceunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("synchronizeServiceSubtypeforLANLINKserviceunderviewservicepage");
			Thread.sleep(3000);
	
			System.out.println("Entered sync service");
			DirectFiber.get().syncservices("LANLINK");
			
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=11)
		public void showNewInfovistaReportunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("showNewInfovistaReportunderviewservicepage");
			Thread.sleep(3000);
			
			System.out.println("Entered show info vista");
			DirectFiber.get().shownewInfovista("LANLINK");
			
		}
	
		
//		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=12)
		public void manageServiceunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("manageServiceunderviewservicepage");
			Thread.sleep(3000);
			
			System.out.println("Enetred manage service");
			DirectFiber.get().manageService("LANLINK");
			
		}
		
		
//		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=13)
		public void ManageSubnetsForlanlinkServiceunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("ManageSubnetsForlanlinkServiceunderviewservicepage");
			Thread.sleep(1000);
			
			System.out.println("Entered manage subnets");
			DirectFiber.get().manageSubnets("LANLINK");
			
		}
		
		
//		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=14)
		public void AMNValidatorForlanlinkServiceunderviewservicepage(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("AMNValidatorForlanlinkServiceunderviewservicepage");
			Thread.sleep(1000);
			
			if(map.get("EditService_proactiveMonitor").equalsIgnoreCase("no")) {
				if(map.get("proactiveMonitor").equalsIgnoreCase("Yes")) {
					DirectFiber.get().amnvalidator_viewServicepage("LANLINK");
				}else {
					DriverTestcase.logger.log(LogStatus.INFO, "'AMN Validator' panel is not displaying, if 'proactive Monitor' checkbox is not selected");
				}
			}else {
				if(map.get("EditService_proactiveMonitor").equalsIgnoreCase("yes")) {
					DirectFiber.get().amnvalidator_viewServicepage("LANLINK");
				}else {
					DriverTestcase.logger.log(LogStatus.INFO, "'AMN Validator' panel is not displaying, if 'proactive Monitor' checkbox is not selected");
				}
			}
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=15)
		public void DumpForlanlinkServiceunderviewservicepage(Map<String, String> map) throws Exception {
			
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("DumpForlanlinkServiceunderviewservicepage");
			Thread.sleep(4000);
			
			DirectFiber.get().dump_viewServicepage("LANLINK");
			
		}
		
		
				
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=16)
		public void verifyAddSiteOrderFieldsfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddSiteOrderFieldsfortheServiceSubtypeSelectedunderLANLINK");
			Thread.sleep(3000);
			
			System.out.println("------Verify fields for add site order-------- 6");
			
			
			DirectFiber.get().Enteraddsiteorder("LANLINK");
			DirectFiber.get().verifyAddsiteorderFields("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					 map.get("SiteOrder_Offnet"),map.get("SiteOrder_EPNoffnet"), map.get("SiteOrder_EPNEOSDH"));
		
			DirectFiber.get().Enteraddsiteorder("LANLINK");
			DirectFiber.get().addsiteorder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					map.get("country"),map.get("city"),map.get("CSR_Name"), 
					 map.get("sitevalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
					   map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("existing_SiteOrdervalue"),
					   map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
					   map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
					   map.get("Siteordernumber"), map.get("siteOrder_CircuitReference"), map.get("SiteOrder_Offnet"), map.get("SiteOrder_Ivrefrence"),map.get("SiteOrer_GCROloType"),
			  		   map.get("SiteOrder_Vlan"), map.get("SiteOrder_Vlanethertype"),map.get("siteOrder_PrimaryVlan"), map.get("SiteOrder_PrimaryVlanEtherType"), map.get("SiteOrder_EPNoffnet"), 
			  		   map.get("SiteOrder_EPNEOSDH"), map.get("SiteOrder_mappingMode"), map.get("SiteOrder_portBased"), map.get("SiteOrder_vlanBased"));
			
		}
	
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=17)
		public void SuccessmessageforCreationofSiteOrder(Map<String, String> map) throws Exception {
			
	   System.out.println("---- verify success messgae for adding site order-------7");
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("SuccessmessageforCreationofSiteOrder");
			Thread.sleep(2000);
		
			DirectFiber.get().verifysuccessmessage("LANLINK", "Site order created successfully");
		}
		
		
	
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=18)
		public void verifytheDataEnteredforAddSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifytheDataEnteredforAddSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
			Thread.sleep(3000);
			
			
			DirectFiber.get().VerifyDataEnteredForSiteOrder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					map.get("country"),map.get("city"),map.get("CSR_Name"), 
					 map.get("existing_SiteOrdervalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
					   map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("sitevalue"),
					   map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
					   map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
					   map.get("Siteordernumber"), map.get("siteOrder_CircuitReference"), map.get("SiteOrder_Offnet"), map.get("SiteOrder_Ivrefrence"),map.get("SiteOrer_GCROloType"),
			  		   map.get("SiteOrder_Vlan"), map.get("SiteOrder_Vlanethertype"),map.get("siteOrder_PrimaryVlan"), map.get("SiteOrder_PrimaryVlanEtherType"), map.get("SiteOrder_EPNoffnet"), map.get("SiteOrder_EPNEOSDH"));
			
		}

		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=19)
		public void EditSiteOrderfortheServiceSubtypeSelectedunderLANLINK(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("EditSiteOrderfortheServiceSubtypeSelectedunderLANLINK");
			Thread.sleep(3000);
			
			System.out.println("----- edit site order page-----------9");
			
			DirectFiber.get().returnbacktoviewsiteorderpage("LANLINK");
			
			DirectFiber.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"), map.get("siteOrderNumber_PointToPoint"), 
					map.get("vpnTopology"), map.get("Interfacespeed"), map.get("siteOrderNumber_10G_PointToPoint"));
			
			DirectFiber.get().editSiteOrder( "LANLINK", map.get("Interfacespeed") ,map.get("vpnTopology"), map.get("EditSiteOrder_performReport"), map.get("EditSiteOrder_ProactiveMonitor"),map.get("EditSiteOrder_smartmonitor"),
			     map.get("EditSiteOrder_siteallias"), map.get("EditSiteOrder_VLANid"), map.get("EditSiteOrder_DCAenabledsite"), map.get("EditSiteOrder_cloudserviceprovider"), map.get("technology"), 
			     map.get("editsiteorder_nonterminationpoint"), map.get("editsiteorder_protected"), map.get("editsiteorder_devicename"), map.get("editsiteorder_remark"), map.get("SiteOrder_Ivrefrence"),
			     map.get("Siteordernumber"), map.get("editsiteorder_circuitReference"), map.get("editsiteorder_GCRoloType"), map.get("editsiteorder_VLAN"), map.get("editsiteorder_VlanEtherType"),
			     map.get("editsiteorder_primaryVLAN"),map.get("editsiteorder_primaryVlanEtherType"), map.get("SiteOrder_Offnet"), map.get("editSiteOrder_Offnet"), map.get("editSiteOrder_EPNOffnet"),
			     map.get("editSiteOrder_mappingMode"), map.get("editSiteOrder_portbased"), map.get("editSiteorder_VlanBased"), map.get("SiteOrder_mappingMode"), map.get("editEpNEosDH"));
		
		
			DirectFiber.get().verifysuccessmessage("LANLINK", "Site Order successfully updated.");
			
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=20)
		public void Actelis(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("Actelis");
			Thread.sleep(1000);
			
			String Technologyname=map.get("technology");
			if(Technologyname.equalsIgnoreCase("Actelis")) {
			
			boolean equipConfigurationPanel=DirectFiber.get().EquipmentCOnfigurationPanel("LANLINK");
			
			if(equipConfigurationPanel) {
				
				DirectFiber.get().equipConfiguration_Actelis_AddDevice("LANLINK", map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
						map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"), map.get("ActelisTech_addCPE_ETH_Port"));
			
				DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
				
				DirectFiber.get().verifyDataEnteredFordeviceCreation_Actelis("LANLINK",  map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
						map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"),  map.get("ActelisTech_addCPE_ETH_Port"));
				
				DirectFiber.get().returnbacktoviewsiteorderpage("LANLINK");
				
				DirectFiber.get().deleteDeviceFromService_EquipmentConfig_Actelis("LANLINK", map.get("deleteDeviceSelection_EquipmentConfiguration"), map.get("ActelisTech_addCPE_name"));
			
				DirectFiber.get().successMessage_deleteFromService("LANLINK");
			}
			
		//Actelis Configuration panel	
			DirectFiber.get().verifyAddDSLAMandHSLlink("LANLINK", map.get("ActelisTech_DSLAMdevice"));
			
			DirectFiber.get().AddDSLAMandHSL("LANLINK", map.get("ActelisTech_DSLAMdevice"), map.get("ActelisTech_DSLAMInterfacename"));
			
			DirectFiber.get().showInterface_ActelisConfiguuration("LANLINK");
			
			DirectFiber.get().deletInterface_ActelisConfiguration("LANLINK", map.get("ActelisTech_DSLAMInterfacename"), map.get("deleteInterfcae_ActelisConfiguration"));
			
			DirectFiber.get().successMessage_deleteInterfaceFromDevice_ActelisConfiguration("LANLINK");
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "Actelis panel will display only for 'Actelis' Technology");
			}
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=21)
		public void AdddeviceforEquipment(Map<String, String> map) throws Exception {
			
  			DriverTestcase.logger = DriverTestcase.extent.startTest("AdddeviceforEquipment");
			Thread.sleep(3000);
			
			System.out.println("Add device for Equipment");
			
			String devicename=null;  String vendorModel=null;
			String manageAdres=null;  String country="";
			
			String siteOrderNumber=null;
			if(map.get("vpnTopology").equals("Point-to-Point")) {
				siteOrderNumber=map.get("siteOrderNumber_PointToPoint");
			}else {
				siteOrderNumber=map.get("Siteordernumber");
			}
			
			
		//verify whether Equipment panel is available	
			boolean EquipmentPanel=DirectFiber.get().findPanelHeader("LANLINK", "Equipment");
			
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
				String existingDevice=map.get("Equip_ExistingDeviceSelection");
				String newDevice=map.get("Equip_NewDeviceSelection");
				
			
				if(speed.equals("1GigE")) {
					
					if(existingDevice.equalsIgnoreCase("Yes") && newDevice.equalsIgnoreCase("No")) {
						
						DirectFiber.get().verifyFieldsandSelectCPEdevicefortheserviceselected_existingDevice("LANLINK",map.get("Equip_existingDevicename"));
						
						DirectFiber.get().verifysuccessmessage("LANLINK", "Site device created successfully");
						
						DirectFiber.get().verifyValuesforCPEexistingdevice_1G_Equipment("LANLINK" );
						
						DirectFiber.get().eDITCPEdevicedetailsentered_1G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_1G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"), map.get("technology"));
						
						DirectFiber.get().verifysuccessmessage("LANLINK", "Site device updated successfully");
						
					}
					else if(existingDevice.equalsIgnoreCase("No") && newDevice.equalsIgnoreCase("Yes")) {
						
						DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_1G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
								 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"));
				
						DirectFiber.get().verifysuccessmessage("LANLINK", "Site device created successfully");
						
						DirectFiber.get().verifydetailsEnteredforCPEdevice_1G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
							 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
							 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"), map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"));
						
//						DirectFiber.get().returnbacktoviewsiteorderpage("LANLINK");
						
						DirectFiber.get().eDITCPEdevicedetailsentered_1G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_1G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"), map.get("technology"));
						
						DirectFiber.get().verifysuccessmessage("LANLINK", "Site device updated successfully");
						
					}
					
				}else if(speed.equals("10GigE")) {
					
					if(existingDevice.equalsIgnoreCase("Yes") && newDevice.equalsIgnoreCase("No")) {
						
						DirectFiber.get().verifyFieldsandSelectCPEdevicefortheserviceselected_existingDevice("LANLINK",map.get("Equip_existingDevicename"));
						
						DirectFiber.get().verifysuccessmessage("LANLINK", "Site device created successfully");
						
						DirectFiber.get().verifyValuesforCPEexistingdevice_10G_Equipment("LANLINK" );
						
						DirectFiber.get().eDITCPEdevicedetailsentered_10G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_10G"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_10G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"));
				
						DirectFiber.get().verifysuccessmessage("LANLINK", "Site device updated successfully");
					}
						
					else if(existingDevice.equalsIgnoreCase("No") && newDevice.equalsIgnoreCase("yes")) {
						
						DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_10G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
								 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
						
						DirectFiber.get().verifysuccessmessage("LANLINK", "Site device created successfully");
						
						DirectFiber.get().verifydetailsEnteredforCPEdevice_10G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
								 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),  map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
						
						DirectFiber.get().eDITCPEdevicedetailsentered_10G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_10G"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_10G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"));
				
						DirectFiber.get().verifysuccessmessage("LANLINK", "Site device updated successfully");
						
						DirectFiber.get().returnbacktoviewsiteorderpage("LANLINK");
					}
				}	
			
				
			//get Device name 	
				if(existingDevice.equalsIgnoreCase("Yes") && newDevice.equalsIgnoreCase("No")) {
					
					devicename=map.get("Equip_existingDevicename");
				}
				else if(existingDevice.equalsIgnoreCase("No") && newDevice.equalsIgnoreCase("Yes")) {
					
					if(map.get("EDIT_cpename").equalsIgnoreCase("null")) {
						devicename=map.get("devicename_equip");
					}
					else if(!map.get("EDIT_cpename").equalsIgnoreCase("null")) {
						devicename=map.get("EDIT_cpename");
					}	
				}
				
				
				
				DirectFiber.get().Equip_clickonviewButton("LANLINK", devicename);   //navigate to view device page
					
				//devicename	
					devicename=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
					
				//management Address	
					manageAdres=DirectFiber.get().fetchManagementAddressValue_InviewPage("LANLINK");
					
				//Vendor Model
					vendorModel=DirectFiber.get().fetchVendorModelValue("LANLINK");
				
				//Country
					country=DirectFiber.get().fetchCountryValue_InviewPage("LANLINK");
		
			
			
			DirectFiber.get().testStatus("LANLINK");
			
		//Perform fetch from Interface
//			boolean link=DirectFiber.get().fetchDeviceInterface_viewdevicepage("LANLINK", devicename);
//			Thread.sleep(1000);
//			
//			if(link) {
//				manageNetwork.get().verifyFetchInterface("LANLINK", devicename, map.get("InServiceStatus"),
//						map.get("InMaintenanceStatus"), vendorModel, manageAdres, "JdhquA5", country, map.get("interfacename"));
//			}
			
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "Equipment device is not created as expected");
						System.out.println("Equipment device is not created as expected");
						}
				}else {
				DriverTestcase.logger.log(LogStatus.INFO, " 'Equipment' panel is not displaying under 'view site order' page");
				System.out.println(" 'Equipment' panel is not displaying under 'view site order' page");
			}
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=22)
		public void routerTools_Equipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("routerTools_Equipment");
			Thread.sleep(1000);
			
		String manageAdres=null;	
			//management Address	
				manageAdres=DirectFiber.get().fetchManagementAddressValue_InviewPage("LANLINK");
			
			//Perform Router Tools for 1GigE
			DirectFiber.get().routerPanel("LANLINK", map.get("CommandIPv4_Routertool"), manageAdres);
		
		}
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=23)
		public void fetcListofInterface_Equipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("fetcListofInterface_Equipment");
			Thread.sleep(1000);
			
			//Find list of Interfaces
			DirectFiber.get().findlistofInterfaces_Equipment_viewdevicePage("LANLINK");
			Thread.sleep(3000);
			
		}
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=24)
		public void configureEquipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("configureEquipment");
			Thread.sleep(1000);
			
			System.out.println("--------------Select Configure link under Equipment------------------");
			
			String devicename=null;	
			String devicename_EquipActual=null;
			devicename=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
			
			if(devicename.contains("...")) {
				devicename_EquipActual = devicename.substring(0, 10);
			}else {
				devicename_EquipActual=devicename;
			}
			

			String siteOrderNumber=null;
			if(map.get("vpnTopology").equals("Point-to-Point")) {
				siteOrderNumber=map.get("siteOrderNumber_PointToPoint");
			}else {
				siteOrderNumber=map.get("Siteordernumber");
			}
			
			DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
			Thread.sleep(2000);
			
				
			DirectFiber.get().selectconfigurelinkAndverifyEditInterfacefield__Equipment("LANLINK", devicename_EquipActual);
			DirectFiber.get().EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
					map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
			DirectFiber.get().verifyeditedinterfacevalue("LANLINK", map.get("Interfacename_forEditInterface"));
			
		}
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=25)
		public void selectInterface_Equipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("selectInterface_Equipment");
			Thread.sleep(1000);
			
			//Select Interface			
			System.out.println("--------SELECTINTERFACE link for Equipment--------13");
			
			
			String siteOrderNumber=null;
			if(map.get("vpnTopology").equals("Point-to-Point")) {
				siteOrderNumber=map.get("siteOrderNumber_PointToPoint");
			}else {
				siteOrderNumber=map.get("Siteordernumber");
			}
			
			//get device name	
			String devicename=null;	
			String devicename_EquipActual=null;
			devicename=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
			
			if(devicename.contains("...")) {
				devicename_EquipActual = devicename.substring(0, 10);
			}else {
				devicename_EquipActual=devicename;
			}
			
			DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
			Thread.sleep(2000);
			
			
				DirectFiber.get().selectInterfacelinkforEqipment("LANLINK", devicename_EquipActual);
				
				if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
					DirectFiber.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//					DirectFiber.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
				}else {
					System.out.println("interfaces are not removed");
					DriverTestcase.logger.log(LogStatus.PASS, "Interfaces are not removed");
					
				}
				
				if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
					DirectFiber.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//					DirectFiber.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
				}else {
					System.out.println("Interfaces are not added");
					DriverTestcase.logger.log(LogStatus.PASS, "Interfaces are not removed");
				}
				 
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=26)
		public void showInterface_Equipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("showInterface_Equipment");
			Thread.sleep(1000);
			
			//Show Interface	
			System.out.println("-----------Show Interface link for Equipment----------14");
			
			String siteOrderNumber=null;
			if(map.get("vpnTopology").equals("Point-to-Point")) {
				siteOrderNumber=map.get("siteOrderNumber_PointToPoint");
			}else {
				siteOrderNumber=map.get("Siteordernumber");
			}
			
		//get device name	
			String devicename=null;	
			String existingDevice=map.get("Equip_ExistingDeviceSelection");
			String newDevice=map.get("Equip_NewDeviceSelection");
			
			String devicename_EquipActual=null;
			devicename=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
			
			if(devicename.contains("...")) {
				devicename_EquipActual = devicename.substring(0, 10);
			}else {
				devicename_EquipActual=devicename;
			}
			
			DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
			Thread.sleep(2000);
			
					DirectFiber.get().SelectShowInterfacelinkAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"), devicename_EquipActual);
					DirectFiber.get().EnterdataForEditInterfaceforShowInterfacelinkunderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
							map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
					DirectFiber.get().hideInterfaceLink_Equipment("LANLINK");
		}
		
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=27)
		public void deleteDevice_Equipment(Map<String, String> map) throws Exception {
			
			System.out.println("---delete device");
			DriverTestcase.logger = DriverTestcase.extent.startTest("deleteDevice_Equipment");
			Thread.sleep(1000);
		
			String siteOrderNumber=null;
			if(map.get("vpnTopology").equals("Point-to-Point")) {
				siteOrderNumber=map.get("siteOrderNumber_PointToPoint");
			}else {
				siteOrderNumber=map.get("Siteordernumber");
			}
			
		//get device name	
			String devicename=null;	
			String existingDevice=map.get("Equip_ExistingDeviceSelection");
			String newDevice=map.get("Equip_NewDeviceSelection");
			if(existingDevice.equalsIgnoreCase("Yes") && newDevice.equalsIgnoreCase("No")) {
				
				devicename=map.get("Equip_existingDevicename");
			}
			else if(existingDevice.equalsIgnoreCase("No") && newDevice.equalsIgnoreCase("Yes")) {
				
				if(map.get("EDIT_cpename").equalsIgnoreCase("null")) {
					devicename=map.get("devicename_equip");
				}
				else if(!map.get("EDIT_cpename").equalsIgnoreCase("null")) {
					devicename=map.get("EDIT_cpename");
				}	
			}
			
			DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
			Thread.sleep(2000);
			
			//Delete device from Equipment			
			System.out.println("----------------Delete device from service------------------ ");
				DirectFiber.get().deleteDeviceFromServiceForequipment("LANLINK", map.get("deleteDeviceSelection_equipment"), devicename);
				DirectFiber.get().successMessage_deleteFromService("LANLINK");
			
		}
		


@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=28)
public void IntermediateEquipment(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("IntermediateEquipment");
			Thread.sleep(1000);
			  
			String devicename_intEquip=null;  String country="";
			String manageAdres=null; String vendorModel=null;
			
			boolean IntermediateEquipmentPanel=DirectFiber.get().findPanelHeader("LANLINK", "Intermediate Equipment");
			
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
		String existingDevice=map.get("IntEquip_existingdeviceSelection");
		String newDevice=map.get("IntEquip_newdeviceSelection");
		
		DirectFiber.get().clickOnBreadCrump("LANLINK", map.get("siteOrderNumber_PointToPoint"));
		
		
		if(speed.equals("1GigE")) {
		
			if(existingDevice.equalsIgnoreCase("Yes")  && newDevice.equalsIgnoreCase("No")) {
				
				DirectFiber.get().addDevice_IntEquipment("LANLINK");
				DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
				DirectFiber.get().verifyFieldsandSelectCPEdevicefortheserviceselected_existingDevice("LANLINK", map.get("intEquip_existingDeviceValue"));
				DirectFiber.get().verifyValuesforCPEexistingdevice_1G_intEquipment("LANLINK");
				DirectFiber.get().EDITCPEdevicedforIntermediateEquipment_1G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_1G_Overtue"),map.get("EDIT_Intequip_device_vender_1G_Accedian"),
						map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
						map.get("EDIT_Intequip_device_poweralarm_1G_Overture"),map.get("EDIT_Intequip_device_poweralarm_1G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
						map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), 
						map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
						,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
						map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"), 
						map.get("TechToBeselected_underTechpopup_device"));
				
				DirectFiber.get().verifysuccessmessage("LANLINK", "Site device updated successfully");
			}
			else if(existingDevice.equalsIgnoreCase("No")  && newDevice.equalsIgnoreCase("Yes")) {
				
				DirectFiber.get().addDevice_IntEquipment("LANLINK");
				DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
				DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_1G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_1G_Overture"),
						map.get("device_intequip_vender_1G_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
						map.get("device_intequip_poweralarm_1G_overture"), map.get("device_intequip_poweralarm_1G_Accedian"), map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
						map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
						map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
						map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
						map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
						map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));	
			   DirectFiber.get().verifysuccessmessage("LANLINK", "Site device created successfully");
				
			   DirectFiber.get().verifyCPEdevicedataenteredForIntermediateEquipment_1G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_1G_Overture"),
						map.get("device_intequip_vender_1G_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
						map.get("device_intequip_poweralarm_1G_overture"), map.get("device_intequip_poweralarm_1G_Accedian"), map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
						map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
						map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
						map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
						map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
						map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));
			  
			   DirectFiber.get().EDITCPEdevicedforIntermediateEquipment_1G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_1G_Overtue"),map.get("EDIT_Intequip_device_vender_1G_Accedian"),
						map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
						map.get("EDIT_Intequip_device_poweralarm_1G_Overture"),map.get("EDIT_Intequip_device_poweralarm_1G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
						map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), 
						map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
						,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
						map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
						map.get("TechToBeselected_underTechpopup_device"));
				
			   DirectFiber.get().verifysuccessmessage("LANLINK", "Site device updated successfully");
			  
			}
		
		}
    else if(speed.equals("10GigE")) {
    	
    	if(existingDevice.equalsIgnoreCase("Yes")  && newDevice.equalsIgnoreCase("No")) {
    		
    		DirectFiber.get().addDevice_IntEquipment("LANLINK");
    		DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
    		DirectFiber.get().verifyFieldsandSelectCPEdevicefortheserviceselected_existingDevice("LANLINK", map.get("intEquip_existingDeviceValue"));
    		DirectFiber.get().verifyValuesforCPEexistingdevice_10G_intEquipment("LANLINK");
    		DirectFiber.get().EDITCPEdevice_IntermediateEquipment_10G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), 
					   map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
						map.get("EDIT_Intequip_device_poweralarm_10G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
						map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), 
						map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
						,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
						map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
						map.get("TechToBeselected_underTechpopup_device"));
    		DirectFiber.get().verifysuccessmessage("LANLINK", "Site device updated successfully");
			
    		
    	}
    	else if(existingDevice.equalsIgnoreCase("No")  && newDevice.equalsIgnoreCase("Yes")) {

			DirectFiber.get().addDevice_IntEquipment("LANLINK");
			DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
			DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_10G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_10G_Accedian"),
					map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
					map.get("device_intequip_poweralarm_10G_Accedian"),map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
					map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
					map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
					map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
					map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
					map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));	
		   DirectFiber.get().verifysuccessmessage("LANLINK", "Site device created successfully");
			
		   DirectFiber.get().verifyCPEdevicedataenteredForIntermediateEquipment_10G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_10G_Accedian"),
					map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
					map.get("device_intequip_poweralarm_10G_Accedian"),map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
					map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
					map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
					map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
					map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
					map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));
		   
		   DirectFiber.get().EDITCPEdevice_IntermediateEquipment_10G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), 
				   map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
					map.get("EDIT_Intequip_device_poweralarm_10G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
					map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"),
					map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
					,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
					map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
					map.get("TechToBeselected_underTechpopup_device"));
		   DirectFiber.get().verifysuccessmessage("LANLINK", "Site device updated successfully");
		   
		   DirectFiber.get().returnbacktoviewsiteorderpage("LANLINK");
		   
    		}
			
		}
	
		
	//Get device name 	
		if(existingDevice.equalsIgnoreCase("Yes")  && newDevice.equalsIgnoreCase("No")) {
			devicename_intEquip=map.get("intEquip_existingDeviceValue");
		}
		else if(existingDevice.equalsIgnoreCase("no")  && newDevice.equalsIgnoreCase("Yes")) {
			
			if(map.get("EDIT_Intequip_cpe_deviecname").equalsIgnoreCase("null")) {
				devicename_intEquip=map.get("device_intEquip_name");
			}
			else if(!map.get("EDIT_Intequip_cpe_deviecname").equalsIgnoreCase("null")) {
				devicename_intEquip=map.get("EDIT_Intequip_cpe_deviecname");
			}
		}
		
		
	//navigate to view device page	
		DirectFiber.get().IntEquip_clickonviewButton("LANLINK", devicename_intEquip);
		
			//devicename	
				devicename_intEquip=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
				
			//management Address	
				manageAdres=DirectFiber.get().fetchManagementAddressValue_InviewPage("LANLINK");
				
			//Vendor Model
				vendorModel=DirectFiber.get().fetchVendorModelValue("LANLINK");	
				
			//Country
				country=DirectFiber.get().fetchCountryValue_InviewPage("LANLINK");
				
			//Check Test Status	
				DirectFiber.get().testStatus("LANLINK");
		
	//Perform fetch from Interface
		boolean link=DirectFiber.get().fetchDeviceInterface_viewdevicepage("LANLINK", devicename_intEquip);
		Thread.sleep(1000);
		
//		if(link) {
//			manageNetwork.get().verifyFetchInterface("LANLINK", devicename_intEquip, map.get("InServiceStatus"),
//					map.get("InMaintenanceStatus"), vendorModel, manageAdres, "JdhquA5", country, map.get("interfacename"));
//		}
		
		}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Intermediate Equipment' panel is not displaying under 'view site order' page");
			}
	}	
		


		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=29)
		public void routerTools_intermediateEquipment(Map<String, String> map) throws Exception {
					
			DriverTestcase.logger = DriverTestcase.extent.startTest("routerTools_intermediateEquipment");
			
		//Management Address			
			String manageAdres=null;
			manageAdres=DirectFiber.get().fetchManagementAddressValue_InviewPage("LANLINK");
					
				//Perform Router Tools for 1GigE
					DirectFiber.get().routerPanel("LANLINK", map.get("CommandIPv4_Routertool"), manageAdres);
				
		}
		
		
		
		
//		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=30)
		public void fetchListOfDevice_IntermediateEquipment(Map<String, String> map) throws Exception {
					
					DriverTestcase.logger = DriverTestcase.extent.startTest("fetchListOfDevice_IntermediateEquipment");
				
				//Find list of Interfaces	
					DirectFiber.get().findlistofInterfaces_IntEquipment_viewdevicePage("LANLINK");
					Thread.sleep(3000);
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=31)
		public void selectinterface_IntermediateEquipment(Map<String, String> map) throws Exception {
					
					DriverTestcase.logger = DriverTestcase.extent.startTest("selectinterface_IntermediateEquipment");
					
					System.out.println("--------SELECTINTERFACE link for Equipment--------13");
					
					String devicename_intEquip=null;
					String devicename_intEquipActual=null;
					devicename_intEquip=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
				
					if(devicename_intEquip.contains("...")) {
						devicename_intEquipActual = devicename_intEquip.substring(0, 10);
					}else {
						devicename_intEquipActual=devicename_intEquip;
					}
					
				String siteOrderNumber=null;
					if(map.get("vpnTopology").equals("Point-to-Point")) {
						siteOrderNumber=map.get("siteOrderNumber_PointToPoint");
					}else {
						siteOrderNumber=map.get("Siteordernumber");
					}
							
					DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
					
					DirectFiber.get().selectInterfacelinkforIntermediateEqipment("LANLINK", devicename_intEquipActual);
					
					if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
						DirectFiber.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//						DirectFiber.get().verifyInterfaceremovedFromService("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
					}else {
						System.out.println("interfaces are not removed");
					}
					
					if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
						DirectFiber.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//						DirectFiber.get().verifyInterfaceaddedToService("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
					}
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=32)
		public void showInterace_IntermediateEquipment(Map<String, String> map) throws Exception {
					
					DriverTestcase.logger = DriverTestcase.extent.startTest("showInterace_IntermediateEquipment");
					System.out.println("-----------Show Interface link for Equipment----------14");
					
					String devicename_intEquip=null;
					String devicename_intEquipActual=null;
					devicename_intEquip=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
					
					if(devicename_intEquip.contains("...")) {
						devicename_intEquipActual = devicename_intEquip.substring(0, 10);
					}else {
						devicename_intEquipActual=devicename_intEquip;
					}
					
					String siteOrderNumber=null;
					if(map.get("vpnTopology").equals("Point-to-Point")) {
						siteOrderNumber=map.get("siteOrderNumber_PointToPoint");
					}else {
						siteOrderNumber=map.get("Siteordernumber");
					}
					
					DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
										
					DirectFiber.get().SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"),  devicename_intEquipActual );
					DirectFiber.get().EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
							map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
					DirectFiber.get().hideInterface_IntEquipment("LANLINK");
		}
		
		
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_createService", priority=33)
		public void deletDeviceFromService_IntermediateEquipment(Map<String, String> map) throws Exception {
					
					DriverTestcase.logger = DriverTestcase.extent.startTest("deletDeviceFromService_IntermediateEquipment");
					
					System.out.println("-------------Delete device from service for intermediate equipment---------");
					
				//Get device name
					String devicename_intEquip=null;
					String existingDevice=map.get("IntEquip_existingdeviceSelection");
					String newDevice=map.get("IntEquip_newdeviceSelection");
					if(existingDevice.equalsIgnoreCase("Yes")  && newDevice.equalsIgnoreCase("No")) {
						devicename_intEquip=map.get("intEquip_existingDeviceValue");
					}
					else if(existingDevice.equalsIgnoreCase("no")  && newDevice.equalsIgnoreCase("Yes")) {
						
						if(map.get("EDIT_Intequip_cpe_deviecname").equalsIgnoreCase("null")) {
							devicename_intEquip=map.get("device_intEquip_name");
						}
						else if(!map.get("EDIT_Intequip_cpe_deviecname").equalsIgnoreCase("null")) {
							devicename_intEquip=map.get("EDIT_Intequip_cpe_deviecname");
						}
					}
					
					
					DirectFiber.get().deleteDeviceFromServiceForIntermediateequipment("LANLINK", map.get("deleteDeviceSelection_Intermediateequipment"), devicename_intEquip );
					DirectFiber.get().successMessage_deleteFromService("LANLINK");

		}
		
		
		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=34)
		public void PAMTest(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("PAMTest");
			Thread.sleep(3000);
			
			String ServiceID = null;
			
			if(map.get("Edit_serviceNumber").equalsIgnoreCase("null")) {
				ServiceID=map.get("serviceNumber");
			}else {
				ServiceID=map.get("Edit_serviceNumber");
			}
			System.out.println("----- PAM Test-----------9");
			
			DirectFiber.get().returnbacktoviewsiteorderpage("LANLINK");
			
			DirectFiber.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"), map.get("siteOrderNumber_PointToPoint"), 
					map.get("vpnTopology"), map.get("Interfacespeed"), map.get("siteOrderNumber_10G_PointToPoint"));
			
			DirectFiber.get().pamTest("LANLINK", ServiceID);
		
		}	




		
}
