package com.colt.qa.apttestscripts;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;

import javafx.scene.control.ScrollToEvent;


public class Lanlink_DirectFiber extends DriverTestcase {
	
	APT_Login Login=new APT_Login();
	
	public String deviceName_Equip=null;
	public String devicename_IntEquipment=null;
	
	@Test(dataProviderClass = DataReader.class, dataProvider = "DataReader_LANLINK_DirectFiber", priority=0)
	public void Lanlink_DirectFiber(Map<String, String> map) throws Exception {
		
		setup();
		
		Login.APT_Login_1(map.get("url"));
		
		String CustomerName1=null;
		String newCustomerName=map.get("newCustomerSelection");
		String existingCustomer=map.get("existingCustomerSelection");
			
			if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
					
					logger= ExtentTestManager.startTest ("CreateNewCustomer_Lanlink_DirectFiber");
					DirectFiber.get().CreateCustomer("apt", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
							map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
							map.get("Fax"));
					CustomerName1=map.get("newCustomer");
					ExtentTestManager.endTest();
					
					logger= ExtentTestManager.startTest ("selectExistingCustomer_DirectFiber"); 
					DirectFiber.get().selectCustomertocreateOrder("apt",map.get("newCustomer"));
					ExtentTestManager.endTest();
					
			}
			else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
					
					logger= ExtentTestManager.startTest ("selectExistingCustomer_Lanlink_DirectFiber"); 
					DirectFiber.get().selectCustomertocreateOrder("apt",map.get("existingCustomer"));
					CustomerName1 = map.get("existingCustomer");
					ExtentTestManager.endTest();
			}
 
			
		logger= ExtentTestManager.startTest ("verifyListofFieldsForOrderandServicetype");
			DirectFiber.get().Verifyfields(("CreateOrderService"),map.get("ServiceType"), map.get("Modularmsp"), map.get("AutocreateService"));
			DirectFiber.get().selectCustomertocreateOrderfromleftpane("CreateOrderService", CustomerName1);
			DirectFiber.get().createorderservice("apt", map.get("NewOrderSelection"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"),
					map.get("ExistingOrderSelection"), map.get("ExistingOrderNumber"));
			DirectFiber.get().selectServiceType("CreateOrderService", map.get("ServiceType"));
			ExtentTestManager.endTest();
 
			
		logger= ExtentTestManager.startTest ("selectTheServiceType");
			DirectFiber.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
			ExtentTestManager.endTest();
			
			
		logger= ExtentTestManager.startTest ("verifyFields_createService_DirectFiber");
			DirectFiber.get().VerifyFieldsForServiceSubTypeSelected("LANLINK",map.get("ServiceType"),map.get("Servicesubtype"), map.get("Interfacespeed"),
					map.get("Notification management"), map.get("vpnTopology"), map.get("Modularmsp"));	
			ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest ("enterData_createSerice_DirectFiber");
			DirectFiber.get().selectOrder("apt", map.get("NewOrderSelection"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"),
				map.get("ExistingOrderSelection"), map.get("ExistingOrderNumber"));
			DirectFiber.get().selectServiceType("CreateOrderService", map.get("ServiceType"));
			DirectFiber.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
						map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
			DirectFiber.get().dataToBeEnteredOncesubservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
					map.get("performanceReportngForServices"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
					map.get("CircuitReference"), map.get("CircuitType"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"), map.get("Notification management"), map.get("performanceReportngForServices"),
					map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), map.get("premiumEIR_ServiceCreation"));
			ExtentTestManager.endTest();
			
		logger= ExtentTestManager.startTest ("successmessageforServicecreation");
			 DirectFiber.get().verifysuccessmessage("LANLINK", "Service successfully created");
			 ExtentTestManager.endTest(); 
			 
//		logger= ExtentTestManager.startTest ("verifyUserDetailsInformation");
//			 DirectFiber.get().VerifyUsersPanel("apt", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"),
//					map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//					map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),
//					map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//					map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"),
//					map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"),
//					map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), 
//					map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), map.get("SiteOrders_ToBeAvailable"),
//					map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
			
			 
		logger= ExtentTestManager.startTest ("verifyOrderDetailsInformation");
			DirectFiber.get().verifyorderpanel_editorder("apt", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
			DirectFiber.get().verifyorderpanel_changeorder("apt", map.get("ChangeOrder_newOrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
					map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
			ExtentTestManager.endTest();
			
			
		logger= ExtentTestManager.startTest ("verifydataenteredwhilecreatingServiceSubtypeunderLANLINKinviewservicepage");
			DirectFiber.get().VerifydatenteredForServiceSubTypeSelected("LANLINK",map.get("ServiceType"),map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
					map.get("PerformMonitor"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
					map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"),  map.get("performanceReportngForServices"),
					map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), map.get("premiumEIR_ServiceCreation"), map.get("Notification management"));
			ExtentTestManager.endTest();
			
		logger= ExtentTestManager.startTest ("editServiceSubtypesunderLANLINKservice");
			DirectFiber.get().EditTheservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Edit_serviceNumber"),map.get("Edit_endpointCPE"),map.get("EditService_email"), map.get("EditService_phone"), map.get("EditService_remark"), 
					map.get("EditService_PerformMonitor"),map.get("EditService_proactiveMonitor"), map.get("EditService_deliveryChannel"), map.get("EditService_ManagementOrder"), map.get("vpnTopology"), map.get("EditService_intermediateTechnology"),
					map.get("EditService_CircuitReference"), map.get("EditService_CircuitType"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
					map.get("AutocreateService"), map.get("EditService_ENNI"), map.get("EditService_manageConnectiondropdown"), map.get("Edit_A_Endtechnology"), map.get("Edit_B_Endtechnology"), map.get("EditService_NotificationManagement"),
					map.get("EditService_perCoSperformanceReport"), map.get("EditService_actelisBased"), map.get("EditService_standardCIR"), map.get("EditService_standardEIR"), map.get("EditService_premiumCIR"), map.get("EditService_premiumEIR"), map.get("CircuitType"));
			ExtentTestManager.endTest();
			
		logger= ExtentTestManager.startTest ("successmessageforServiceUpdation");
			 DirectFiber.get().verifysuccessmessage("LANLINK", "Service successfully updated.");
			 ExtentTestManager.endTest();	
			
			String proactiveMonitorvalue = DirectFiber.get().fetchProActiveMonitoringValue("LANLINK");
			 
		logger= ExtentTestManager.startTest ("synchronizeService_LANLINK");
			DirectFiber.get().syncservices("LANLINK");
			ExtentTestManager.endTest();
			
			
		logger= ExtentTestManager.startTest ("showNewInfovistaReport");
			DirectFiber.get().shownewInfovista("LANLINK");
			ExtentTestManager.endTest();
			
			
		logger= ExtentTestManager.startTest ("ManageSubnets");
			DirectFiber.get().manageSubnets("LANLINK");
			ExtentTestManager.endTest();
			
			
		logger= ExtentTestManager.startTest ("Dump");
			DirectFiber.get().dump_viewServicepage("LANLINK");
			ExtentTestManager.endTest();
			
			
		
		logger= ExtentTestManager.startTest ("AddSiteOrder_LANLINK_directFiber");
				String vpnTopology = map.get("vpnTopology");
				String circuitType = map.get("CircuitType");
			
		if((vpnTopology.equals("Point-to-Point")) &&  (circuitType.equals("Composite Circuit"))){
			
				ExtentTestManager.getTest().log(LogStatus.INFO, "Site order' Page will not display, if we select 'VPN TOpology' as 'Point-to-Point' "
						+ "and 'Circuit Type' as 'Composite Circuit' ");
			
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields for 'Add Site Order' page");
			DirectFiber.get().Enteraddsiteorder("LANLINK");
			DirectFiber.get().verifyAddsiteorderFields("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					 map.get("SiteOrder_Offnet"),map.get("SiteOrder_EPNoffnet"), map.get("SiteOrder_EPNEOSDH"));
			DirectFiber.get().Enteraddsiteorder("LANLINK");
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "create Site order");
			DirectFiber.get().addsiteorder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					map.get("country"),map.get("city"),map.get("CSR_Name"), 
					 map.get("sitevalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
					   map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("existing_SiteOrdervalue"),
					   map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
					   map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
					   map.get("Siteordernumber"), map.get("siteOrder_CircuitReference"), map.get("SiteOrder_Offnet"), map.get("SiteOrder_Ivrefrence"),map.get("SiteOrer_GCROloType"),
			  		   map.get("SiteOrder_Vlan"), map.get("SiteOrder_Vlanethertype"),map.get("siteOrder_PrimaryVlan"), map.get("SiteOrder_PrimaryVlanEtherType"), map.get("SiteOrder_EPNoffnet"), 
			  		   map.get("SiteOrder_EPNEOSDH"), map.get("SiteOrder_mappingMode"), map.get("SiteOrder_portBased"), map.get("SiteOrder_vlanBased"), map.get("siteOrder_sitePreferenceType"));
			ExtentTestManager.endTest();
			
		logger= ExtentTestManager.startTest ("verifySuccessMessageForAddSiteOrder");
			DirectFiber.get().verifysuccessmessage("LANLINK", "Site order created successfully");
			ExtentTestManager.endTest();
			
		logger= ExtentTestManager.startTest ("verifytheDataEnteredforSiteOrder");
			DirectFiber.get().VerifyDataEnteredForSiteOrder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
					map.get("country"),map.get("city"),map.get("CSR_Name"), 
					map.get("existing_SiteOrdervalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
					map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("sitevalue"),
					map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
					map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
					map.get("Siteordernumber"), map.get("siteOrder_CircuitReference"), map.get("SiteOrder_Offnet"), map.get("SiteOrder_Ivrefrence"),map.get("SiteOrer_GCROloType"),
			  		map.get("SiteOrder_Vlan"), map.get("SiteOrder_Vlanethertype"),map.get("siteOrder_PrimaryVlan"), map.get("SiteOrder_PrimaryVlanEtherType"), map.get("SiteOrder_EPNoffnet"), map.get("SiteOrder_EPNEOSDH"));
			ExtentTestManager.endTest();
			
		logger= ExtentTestManager.startTest ("EditSiteOrder_LANLINK");
			DirectFiber.get().returnbacktoviewsiteorderpage("LANLINK");
			DirectFiber.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"), map.get("siteOrderNumber_PointToPoint"), 
					map.get("vpnTopology"), map.get("Interfacespeed"), map.get("siteOrderNumber_10G_PointToPoint"));
			DirectFiber.get().editSiteOrder( "LANLINK", map.get("Interfacespeed") ,map.get("vpnTopology"), map.get("EditSiteOrder_performReport"), map.get("EditSiteOrder_ProactiveMonitor"),map.get("EditSiteOrder_smartmonitor"),
				     map.get("EditSiteOrder_siteallias"), map.get("EditSiteOrder_VLANid"), map.get("EditSiteOrder_DCAenabledsite"), map.get("EditSiteOrder_cloudserviceprovider"), map.get("technology"), 
				     map.get("editsiteorder_nonterminationpoint"), map.get("editsiteorder_protected"), map.get("editsiteorder_devicename"), map.get("editsiteorder_remark"), map.get("SiteOrder_Ivrefrence"),
				     map.get("Siteordernumber"), map.get("editsiteorder_circuitReference"), map.get("editsiteorder_GCRoloType"), map.get("editsiteorder_VLAN"), map.get("editsiteorder_VlanEtherType"),
				     map.get("editsiteorder_primaryVLAN"),map.get("editsiteorder_primaryVlanEtherType"), map.get("SiteOrder_Offnet"), map.get("editSiteOrder_Offnet"), map.get("editSiteOrder_EPNOffnet"),
				     map.get("editSiteOrder_mappingMode"), map.get("editSiteOrder_portbased"), map.get("editSiteorder_VlanBased"), map.get("SiteOrder_mappingMode"), map.get("editEpNEosDH"),  map.get("CircuitType"), map.get("editSiteOrder_sitePreferenceType"));
			DirectFiber.get().verifysuccessmessage("LANLINK", "Site Order successfully updated.");
			ExtentTestManager.endTest();	
		}
		
		
	//For device, Circuit creation
		String sitePreferenceType = "Null";
		
		if(map.get("editSiteOrder_sitePreferenceType").equalsIgnoreCase("Null")) {
			sitePreferenceType = map.get("siteOrder_sitePreferenceType");
		}
		else {
			sitePreferenceType = map.get("editSiteOrder_sitePreferenceType");
		}
		
		if(((vpnTopology.equals("Point-to-Point")) && (circuitType.equals("Composite Circuit")))  ||  ((vpnTopology.equals("Point-to-Point")) &&  (circuitType.equals("Extended Circuit"))  &&  (sitePreferenceType.equalsIgnoreCase("Circuit")))){
			
			String ServiceID = null;
			if(map.get("Edit_serviceNumber").equalsIgnoreCase("null")) {
				ServiceID=map.get("serviceNumber");
			}else {
				ServiceID=map.get("Edit_serviceNumber");
			}
			
			if(map.get("Interfacespeed").equals("1GigE")) {
				
				logger = ExtentTestManager.startTest("Circuit Creation For 1G");
				
				//Overture	
					DirectFiber.get().addOvertureCircuit("LANLINK", map.get("addOverture_serviceNameForCreatingCircuit"));
					DirectFiber.get().selectInterfaceForCircuits("LANLINK", map.get("addOverture_interfaceName1"), map.get("addOverture_interfaceName2"),
								map.get("addOverture_edgePointSelectForInterface1"), map.get("addOverture_edgePointSelectForInterface2"));
					DirectFiber.get().verifysuccessmessage("LANLINK", "Circuit successfully created");
					DirectFiber.get().addOveture_PAMtest_selectRow("LANLINK", map.get("addOverture_interfaceName1"));
					DirectFiber.get().PAMtest_ForCircuitCreation("LANLINK", ServiceID, map.get("addOverture_serviceNameForCreatingCircuit"));
					DirectFiber.get().deleteCircuit("LANLINK");
					
					
				//Accedian-1G
					DirectFiber.get().addAccedianCircuit("LANLINK", map.get("addAccedian1G_serviceNameForCreatingCircuit"));
					DirectFiber.get().selectInterfaceForCircuits("LANLINK", map.get("addAccedian1G_interfaceName1"), map.get("addAccedian1G_interfaceName2"),
								map.get("addAccedian1G_edgePointSelectForInterface1"), map.get("addAccedian1G_edgePointSelectForInterface2"));
					DirectFiber.get().verifysuccessmessage("LANLINK", "Circuit successfully created");
					DirectFiber.get().addOveture_PAMtest_selectRow("LANLINK",  map.get("addAccedian1G_interfaceName1"));
					DirectFiber.get().PAMtest_ForCircuitCreation("LANLINK", ServiceID, map.get("addAccedian1G_serviceNameForCreatingCircuit"));
					DirectFiber.get().deleteCircuit("LANLINK");
					
					
				//Atrica
					DirectFiber.get().addAtricaCircuit("LANLINK", map.get("addAtrica_serviceNameForCreatingCircuit"));
					DirectFiber.get().selectInterfaceForCircuits("LANLINK", map.get("addAtrica_interfaceName1"), map.get("addAtrica_interfaceName2"),
								map.get("addAtrica_edgePointSelectForInterface1"), map.get("addAtrica_edgePointSelectForInterface2"));
					DirectFiber.get().verifysuccessmessage("LANLINK", "Circuit successfully created");
					DirectFiber.get().addOveture_PAMtest_selectRow("LANLINK",  map.get("addAtrica_interfaceName1"));
					DirectFiber.get().PAMtest_ForCircuitCreation("LANLINK", ServiceID, map.get("addAtrica_serviceNameForCreatingCircuit"));
					DirectFiber.get().deleteCircuit("LANLINK");
					ExtentTestManager.endTest();
				}
				else if(map.get("Interfacespeed").equals("10GigE")) {
					
					ExtentTestManager.startTest("Circuit creation for 10G");
					
					//Overture	
					DirectFiber.get().addOvertureCircuit("LANLINK", map.get("addOverture_serviceNameForCreatingCircuit"));
					DirectFiber.get().selectInterfaceForCircuits("LANLINK", map.get("addOverture_interfaceName1"), map.get("addOverture_interfaceName2"),
							map.get("addOverture_edgePointSelectForInterface1"), map.get("addOverture_edgePointSelectForInterface2"));
					DirectFiber.get().verifysuccessmessage("LANLINK", "Circuit successfully created");
					DirectFiber.get().addOveture_PAMtest_selectRow("LANLINK", map.get("addOverture_interfaceName1"));
					DirectFiber.get().PAMtest_ForCircuitCreation("LANLINK", ServiceID, map.get("addOverture_serviceNameForCreatingCircuit"));
					DirectFiber.get().deleteCircuit("LANLINK");
					ExtentTestManager.endTest();
				}
		}
		else {	
		logger= ExtentTestManager.startTest ("Actelis_equipment configuration");
			String Technologyname=map.get("technology");
			if(Technologyname.equalsIgnoreCase("Actelis")) {
			boolean equipConfigurationPanel=DirectFiber.get().EquipmentCOnfigurationPanel("LANLINK");
				if(equipConfigurationPanel) {
					ExtentTestManager.getTest().log(LogStatus.INFO, "verify 'Add CPE Device'");
					DirectFiber.get().equipConfiguration_Actelis_AddDevice("LANLINK", map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
							map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"), map.get("ActelisTech_addCPE_ETH_Port"));
					DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
					
					logger= ExtentTestManager.startTest ("verifyDataEntered");
					DirectFiber.get().verifyDataEnteredFordeviceCreation_Actelis("LANLINK",  map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
							map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"),  map.get("ActelisTech_addCPE_ETH_Port"));
					DirectFiber.get().returnbacktoviewsiteorderpage("LANLINK");
					
					logger= ExtentTestManager.startTest ("deleteDevice");
					DirectFiber.get().deleteDeviceFromService_EquipmentConfig_Actelis("LANLINK",  map.get("ActelisTech_addCPE_name"));
					DirectFiber.get().verifysuccessmessage("LANLINK", "Actelis CPE Device successfully deleted and removed from service");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'Equipment Configuration' panel is not displaying");
				}
				ExtentTestManager.endTest();
			
		//Actelis Configuration panel
					logger= ExtentTestManager.startTest ("AddDSLAMandHSL");
					DirectFiber.get().verifyAddDSLAMandHSLlink("LANLINK", map.get("ActelisTech_DSLAMdevice"));
					DirectFiber.get().AddDSLAMandHSL("LANLINK", map.get("ActelisTech_DSLAMdevice"), map.get("ActelisTech_DSLAMInterfacename"));
					DirectFiber.get().showInterface_ActelisConfiguuration("LANLINK");
					DirectFiber.get().deletInterface_ActelisConfiguration("LANLINK", map.get("ActelisTech_DSLAMInterfacename"));
					DirectFiber.get().successMessage_deleteInterfaceFromDevice_ActelisConfiguration("LANLINK");
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "Actelis panel will display only for 'Actelis' Technology");
			}
			ExtentTestManager.endTest();
			
		logger= ExtentTestManager.startTest ("Adddevice_Equipment");
			String devicename=null;  String vendorModel=null;
			String manageAdres=null;  String country="";
			String siteOrderNumber=null;
			if(map.get("vpnTopology").equals("Point-to-Point")) {
				if(map.get("Interfacespeed").equalsIgnoreCase("1GigE")) {
					siteOrderNumber=map.get("siteOrderNumber_PointToPoint");
				}
				else if(map.get("Interfacespeed").equalsIgnoreCase("10GigE")) {
					siteOrderNumber=map.get("siteOrderNumber_10G_PointToPoint");
				}
				
			}else {
				siteOrderNumber=map.get("Siteordernumber");
			}
			
			
		//verify whether Equipment panel is available	
			boolean EquipmentPanel=DirectFiber.get().findPanelHeader("LANLINK", "Equipment");
			if(EquipmentPanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Equipment' panel is displaying under 'view site order' page as expected");
		//Verify whether Equipment device to be created
				if(map.get("deviceCreation_Equipment").equalsIgnoreCase("yes")){
					
					ExtentTestManager.getTest().log(LogStatus.INFO, " Device to be created for Eqiupment as per input provided");	

			ExtentTestManager.getTest().log(LogStatus.INFO, "Under Equipement, list of actions to be performed are: "
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
						ExtentTestManager.getTest().log(LogStatus.INFO, "AddExistingDevice_1G_Eqiupment");
						DirectFiber.get().verifyFieldsandSelectCPEdevicefortheserviceselected_existingDevice("LANLINK",map.get("Equip_existingDevicename"),
								map.get("technology"), map.get("vpnTopology"));
						DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
						ExtentTestManager.endTest();
						
						logger= ExtentTestManager.startTest ("verifyValueForExistingDevice_1G_Equipment");	
						DirectFiber.get().verifyValuesforCPEexistingdevice_1G_Equipment("LANLINK" );
						ExtentTestManager.endTest();
						
						logger= ExtentTestManager.startTest ("editExistingDevice_1G_Equipment");
						DirectFiber.get().eDITCPEdevicedetailsentered_1G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_1G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"), map.get("technology"));
						DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
						ExtentTestManager.endTest();
					}
					else if(existingDevice.equalsIgnoreCase("No") && newDevice.equalsIgnoreCase("Yes")) {
						ExtentTestManager.getTest().log(LogStatus.INFO, "addNewDevice_1G_Equipment");
						DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_1G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
								 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"),
								 map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"), map.get("vpnTopology"));
						DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
						ExtentTestManager.endTest();
						
						logger= ExtentTestManager.startTest ("verifyEnteredValueForNewDevice_1G_Eqiupment");
						DirectFiber.get().verifydetailsEnteredforCPEdevice_1G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
							 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
							 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"), map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"));
						ExtentTestManager.endTest();
						
						logger= ExtentTestManager.startTest ("editNewDevice_1G_Equipment");
						DirectFiber.get().eDITCPEdevicedetailsentered_1G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_1G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"), map.get("technology"));
						DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
						ExtentTestManager.endTest();
					}
					
				}else if(speed.equals("10GigE")) {
					
					if(existingDevice.equalsIgnoreCase("Yes") && newDevice.equalsIgnoreCase("No")) {
						ExtentTestManager.getTest().log(LogStatus.INFO, "addExistingDevice_10G_Eqiupment");
						DirectFiber.get().verifyFieldsandSelectCPEdevicefortheserviceselected_existingDevice("LANLINK",map.get("Equip_existingDevicename"),
								map.get("technology"), map.get("vpnTopology"));
						DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
						ExtentTestManager.endTest();
						
						logger= ExtentTestManager.startTest ("verifyValueForExistingDevice_10G_Equipment");
						DirectFiber.get().verifyValuesforCPEexistingdevice_10G_Equipment("LANLINK" );
						ExtentTestManager.endTest();
						
						logger= ExtentTestManager.startTest ("editExistingDevice_10G_Eqiupment");
						DirectFiber.get().eDITCPEdevicedetailsentered_10G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_10G"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_10G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"));
						DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
						ExtentTestManager.endTest();
					}
						
					else if(existingDevice.equalsIgnoreCase("No") && newDevice.equalsIgnoreCase("yes")) {
						ExtentTestManager.getTest().log(LogStatus.INFO, "addNewDevice_10G_Equipment");
						DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_10G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
								 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
						DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
						ExtentTestManager.endTest();
						
						logger= ExtentTestManager.startTest ("verifyEnteredValueForNewDevice_10G_Equipment");
						DirectFiber.get().verifydetailsEnteredforCPEdevice_10G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
								 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),  map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
						ExtentTestManager.endTest();
						
						logger= ExtentTestManager.startTest ("editNewDevice_10G_Eqiupment");
						DirectFiber.get().eDITCPEdevicedetailsentered_10G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_10G"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
								 map.get("EDIT_cpe_poweralarm_10G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
								 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"));
						DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
						ExtentTestManager.endTest();
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
					devicename=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");		//fetch Device Name
					manageAdres=DirectFiber.get().fetchManagementAddressValue_InviewPage("LANLINK");	//Fetch Management Address
					vendorModel=DirectFiber.get().fetchVendorModelValue("LANLINK");		//Fetch vendor/Model
		
			
		 logger= ExtentTestManager.startTest ("TestCommandTable");	
				DirectFiber.get().testStatus("LANLINK");
				ExtentTestManager.endTest();
					
					
		 logger= ExtentTestManager.startTest ("fetchDeviceInterface");
				boolean link=DirectFiber.get().fetchDeviceInterface_viewdevicepage("LANLINK", devicename);
				Thread.sleep(1000);
	//			
	//			if(link) {
	//				manageNetwork.get().verifyFetchInterface("LANLINK", devicename, map.get("InServiceStatus"),
	//						map.get("InMaintenanceStatus"), vendorModel, manageAdres, "JdhquA5", country, map.get("interfacename"));
	//			}
				
				
				 logger= ExtentTestManager.startTest ("routerTools_Equipment");
					DirectFiber.get().routerPanel("LANLINK", map.get("CommandIPv4_Routertool"), manageAdres);
					ExtentTestManager.endTest();
					
			 logger= ExtentTestManager.startTest ("configureInterface_Equipment");
					String devicename_EquipActual=null;
					devicename=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
					if(devicename.contains("...")) {
						devicename_EquipActual = devicename.substring(0, 10);
					}else {
						devicename_EquipActual=devicename;
					}
					DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
					DirectFiber.get().selectconfigurelinkAndverifyEditInterfacefield__Equipment("LANLINK", devicename_EquipActual);
					DirectFiber.get().EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
							map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
					DirectFiber.get().verifyeditedinterfacevalue("LANLINK", map.get("Interfacename_forEditInterface"));
					ExtentTestManager.endTest();
				
			 logger= ExtentTestManager.startTest ("selectInterface_Equipment");
					DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
					DirectFiber.get().selectInterfacelinkforEqipment("LANLINK", devicename_EquipActual);
						if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
							DirectFiber.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
						}else {
							System.out.println("interfaces are not removed");
							ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces are not removed");
						}
						
						if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
							DirectFiber.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
						}else {
							System.out.println("Interfaces are not added");
							ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces are not removed");
						}
						ExtentTestManager.endTest();
						
						
			 logger= ExtentTestManager.startTest ("showInterface_Equipment");
						deviceName_Equip=devicename_EquipActual;
						DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
						DirectFiber.get().SelectShowInterfacelinkAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"), devicename_EquipActual);
						DirectFiber.get().EnterdataForEditInterfaceforShowInterfacelinkunderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
									map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
						DirectFiber.get().hideInterfaceLink_Equipment("LANLINK");
						ExtentTestManager.endTest();
						
			logger= ExtentTestManager.startTest ("AMNvalidator_Equipment");
			 if(proactiveMonitorvalue.equalsIgnoreCase("Yes")) {
				 DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
					String csrName=DirectFiber.get().fetchCSRsiteName("LANLINK");
					String cityName=DirectFiber.get().fetchDeviceCityName("LANLINK");
					String countryName=DirectFiber.get().fetchSiteOrderCountryName("LANLINK");
					DirectFiber.get().clickOnAMNvalidatorLink("LANLINK");
					DirectFiber.get().AMNvalidator("LANLINK",siteOrderNumber , deviceName_Equip, csrName, cityName, countryName);
					ExtentTestManager.endTest();
			 }else {
				 ExtentTestManager.getTest().log(LogStatus.INFO, "'AMN Validator' link do not display, as 'proactive Monitoring' checkbox is not selected ");
				 ExtentTestManager.endTest();
			 }
			 			
						
			 logger= ExtentTestManager.startTest ("deleteDevice_Equipment");
						DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
						DirectFiber.get().deleteDeviceFromServiceForequipment("LANLINK", deviceName_Equip);
						DirectFiber.get().successMessage_deleteFromService("LANLINK");
						ExtentTestManager.endTest();
				
			   }else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Equipment device is not created as expected");
					System.out.println("Equipment device is not created as expected");
					ExtentTestManager.endTest();
						}
				}else {
					ExtentTestManager.getTest().log(LogStatus.INFO, " 'Equipment' panel is not displaying under 'view site order' page");
					System.out.println(" 'Equipment' panel is not displaying under 'view site order' page");
					ExtentTestManager.endTest();
				}
			
			
		 logger= ExtentTestManager.startTest ("addDevice_IntermediateEquipment");
					String devicename_intEquip=null;  String country_intEquip="";
					String manageAdres_intEqiup=null; String vendorModel_intEqiup=null;
					
					boolean IntermediateEquipmentPanel=DirectFiber.get().findPanelHeader("LANLINK", "Intermediate Equipment");
					if(IntermediateEquipmentPanel) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'Intermediate Equipment' panel is displaying under 'view site order' page as expected");
						
						if(map.get("deviceCreation_IntermediateEquipment").equalsIgnoreCase("yes")){
							ExtentTestManager.getTest().log(LogStatus.INFO, " Device to be created for Intermediate Eqiupment as per input provided");	
					ExtentTestManager.getTest().log(LogStatus.INFO, "Under Intermediate Equipement, list of actions to be performed are: "
							+ "Verify fields for Add device"
							+ "Add device"
							+ "Verify entered values for device"
							+ "Edit device"
							+ "Select Interface"
							+ "show/Hide Interface -- Edit Interface"
							+ "Select Interface -- Add Interface to service , Remove Interface from Service"
							+ "Delete device ");
				
					String speed=map.get("Interfacespeed");
					String existingDevice=map.get("IntEquip_existingdeviceSelection");
					String newDevice=map.get("IntEquip_newdeviceSelection");
					
					DirectFiber.get().clickOnBreadCrump("LANLINK", map.get("siteOrderNumber_PointToPoint"));
					
					if(speed.equals("1GigE")) {
						if(existingDevice.equalsIgnoreCase("Yes")  && newDevice.equalsIgnoreCase("No")) {
							ExtentTestManager.getTest().log(LogStatus.INFO, "selectExistingDevice_1G_IntermediateEquipment");
							DirectFiber.get().addDevice_IntEquipment("LANLINK");
							DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
							DirectFiber.get().verifyFieldsandSelectCPEdevice_existingDevice_IntEquipment("LANLINK", map.get("intEquip_existingDeviceValue"));
							ExtentTestManager.endTest();
							
							logger= ExtentTestManager.startTest ("verifyExistingDeviceValue_1G_IntermediateEquipment");
							DirectFiber.get().verifyValuesforCPEexistingdevice_1G_intEquipment("LANLINK");
							ExtentTestManager.endTest();
							
							logger= ExtentTestManager.startTest ("editExistingDevice_1G_IntermediateEquipment");
							DirectFiber.get().EDITCPEdevicedforIntermediateEquipment_1G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_1G_Overtue"),map.get("EDIT_Intequip_device_vender_1G_Accedian"),
									map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
									map.get("EDIT_Intequip_device_poweralarm_1G_Overture"),map.get("EDIT_Intequip_device_poweralarm_1G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
									map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), 
									map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
									,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
									map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"), 
									map.get("TechToBeselected_underTechpopup_device"));
							DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
							ExtentTestManager.endTest();
						}
						else if(existingDevice.equalsIgnoreCase("No")  && newDevice.equalsIgnoreCase("Yes")) {
							ExtentTestManager.getTest().log(LogStatus.INFO, "addNewDevice_1G_IntermediateEquipment");
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
						   DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
						   ExtentTestManager.endTest();
						   
						   logger= ExtentTestManager.startTest ("verifyEnteredValues_1G_IntermediateEquipment");
						   DirectFiber.get().verifyCPEdevicedataenteredForIntermediateEquipment_1G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_1G_Overture"),
									map.get("device_intequip_vender_1G_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
									map.get("device_intequip_poweralarm_1G_overture"), map.get("device_intequip_poweralarm_1G_Accedian"), map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
									map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
									map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
									map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
									map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
									map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));
						   ExtentTestManager.endTest();
						   
						   logger= ExtentTestManager.startTest ("editCPEdevice_1G_IntermediateEquipment");
						   DirectFiber.get().EDITCPEdevicedforIntermediateEquipment_1G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_1G_Overtue"),map.get("EDIT_Intequip_device_vender_1G_Accedian"),
									map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
									map.get("EDIT_Intequip_device_poweralarm_1G_Overture"),map.get("EDIT_Intequip_device_poweralarm_1G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
									map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), 
									map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
									,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
									map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
									map.get("TechToBeselected_underTechpopup_device"));
						   DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
						   ExtentTestManager.endTest();
						}
					}
			    else if(speed.equals("10GigE")) {
			    	if(existingDevice.equalsIgnoreCase("Yes")  && newDevice.equalsIgnoreCase("No")) {
			    		ExtentTestManager.getTest().log(LogStatus.INFO, "addExistingDevice_10G_IntermediateEquipment");
			    		DirectFiber.get().addDevice_IntEquipment("LANLINK");
			    		DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
			    		DirectFiber.get().verifyFieldsandSelectCPEdevice_existingDevice_IntEquipment("LANLINK", map.get("intEquip_existingDeviceValue"));
			    		DirectFiber.get().verifyValuesforCPEexistingdevice_10G_intEquipment("LANLINK");
			    		ExtentTestManager.endTest();
			    		
			    		logger= ExtentTestManager.startTest ("editExistingDevice_10G_IntermediateEquipment");
			    		DirectFiber.get().EDITCPEdevice_IntermediateEquipment_10G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), 
								   map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
									map.get("EDIT_Intequip_device_poweralarm_10G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
									map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), 
									map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
									,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
									map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
									map.get("TechToBeselected_underTechpopup_device"));
			    		DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
			    		ExtentTestManager.endTest();
			    	}
			    	else if(existingDevice.equalsIgnoreCase("No")  && newDevice.equalsIgnoreCase("Yes")) {
			    		ExtentTestManager.getTest().log(LogStatus.INFO, "addNewDevice_10G_IntermediateEquipment");
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
					   DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
					   ExtentTestManager.endTest();
					   
					   logger= ExtentTestManager.startTest ("verifyEnteredValues_10G_IntermediateEquipment");
					   DirectFiber.get().verifyCPEdevicedataenteredForIntermediateEquipment_10G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_10G_Accedian"),
								map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
								map.get("device_intequip_poweralarm_10G_Accedian"),map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
								map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
								map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
								map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
								map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
								map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));
					   ExtentTestManager.endTest();
					   
					   logger= ExtentTestManager.startTest ("editNewDevice_10G_IntermediateEquipment");
					   DirectFiber.get().EDITCPEdevice_IntermediateEquipment_10G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), 
							   map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
								map.get("EDIT_Intequip_device_poweralarm_10G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
								map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"),
								map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
								,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
								map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
								map.get("TechToBeselected_underTechpopup_device"));
					   DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
					   ExtentTestManager.endTest();
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
						devicename_intEquip=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");	//Device Name
						manageAdres_intEqiup=DirectFiber.get().fetchManagementAddressValue_InviewPage("LANLINK");	//Management Address
						vendorModel_intEqiup=DirectFiber.get().fetchVendorModelValue("LANLINK");		//Vendor/Model
						country_intEquip=DirectFiber.get().fetchCountryValue_InviewPage("LANLINK");		//Country
						
					//Check Test Status	
						DirectFiber.get().testStatus("LANLINK");
					//Perform fetch from Interface
						logger = ExtentTestManager.startTest("fetchDeviceInterface_IntermediateEquipment");
						boolean link=DirectFiber.get().fetchDeviceInterface_viewdevicepage("LANLINK", devicename_intEquip);
						Thread.sleep(1000);
				//		if(link) {
				//			manageNetwork.get().verifyFetchInterface("LANLINK", devicename_intEquip, map.get("InServiceStatus"),
				//					map.get("InMaintenanceStatus"), vendorModel, manageAdres, "JdhquA5", country, map.get("interfacename"));
				//		}
						ExtentTestManager.endTest();
						
						logger= ExtentTestManager.startTest ("routerTools_IntermediateEquipment");
						DirectFiber.get().routerPanel("LANLINK", map.get("CommandIPv4_Routertool"), manageAdres_intEqiup);
						ExtentTestManager.endTest();	

				logger= ExtentTestManager.startTest ("selectinterface_IntermediateEquipment");
						String devicename_intEquipActual=null;
						devicename_intEquip=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
					
						if(devicename_intEquip.contains("...")) {
							devicename_intEquipActual = devicename_intEquip.substring(0, 10);
						}else {
							devicename_intEquipActual=devicename_intEquip;
						}
						devicename_IntEquipment=devicename_intEquipActual;
						
						DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
						DirectFiber.get().selectInterfacelinkforIntermediateEqipment("LANLINK", devicename_intEquipActual);
						if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
							DirectFiber.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
						}else {
							System.out.println("interfaces are not removed");
						}
						if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
							DirectFiber.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
						}
						ExtentTestManager.endTest();	
						
				logger= ExtentTestManager.startTest ("showInterace_IntermediateEquipment");
						DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
						DirectFiber.get().SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"),  devicename_intEquipActual );
						DirectFiber.get().EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
								map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
						DirectFiber.get().hideInterface_IntEquipment("LANLINK");
						ExtentTestManager.endTest();

				logger= ExtentTestManager.startTest ("deletDevice_IntermediateEquipment");
						DirectFiber.get().deleteDeviceFromServiceForIntermediateequipment("LANLINK", devicename_IntEquipment);
						DirectFiber.get().successMessage_deleteFromService("LANLINK");
						ExtentTestManager.endTest();
						}
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'Intermediate Equipment' panel is not displaying under 'view site order' page");
						ExtentTestManager.endTest();
					}
					
					
					
			logger= ExtentTestManager.startTest ("PAMTest");
					String ServiceID = null;
					if(map.get("Edit_serviceNumber").equalsIgnoreCase("null")) {
						ServiceID=map.get("serviceNumber");
					}else {
						ServiceID=map.get("Edit_serviceNumber");
					}
					DirectFiber.get().returnbacktoviewsiteorderpage("LANLINK");
					DirectFiber.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"), map.get("siteOrderNumber_PointToPoint"), 
							map.get("vpnTopology"), map.get("Interfacespeed"), map.get("siteOrderNumber_10G_PointToPoint"));
					DirectFiber.get().pamTest("LANLINK", ServiceID);
					ExtentTestManager.endTest();
					
			logger= ExtentTestManager.startTest ("deleteSiteOrder");
					DirectFiber.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"), map.get("siteOrderNumber_PointToPoint"), 
							map.get("vpnTopology"), map.get("Interfacespeed"), map.get("siteOrderNumber_10G_PointToPoint"));
					DirectFiber.get().deleteSiteOrder("LANLINK");
					ExtentTestManager.endTest();
			}	
					
				String ServiceID = null;
				if(map.get("Edit_serviceNumber").equalsIgnoreCase("null")) {
					ServiceID=map.get("serviceNumber");
				}else {
					
					ServiceID=map.get("Edit_serviceNumber");
				}
			logger= ExtentTestManager.startTest ("deleteService_LANLINKdirectFiber");
					DirectFiber.get().clickOnBreadCrump("LANLINK", ServiceID);
					DirectFiber.get().deleteService("LANLINK");
					ExtentTestManager.endTest();	
		}
	
//		
////		@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=12)
//		public void manageServiceunderviewservicepage(Map<String, String> map) throws Exception {
//			
//			logger= ExtentTestManager.startTest ("manageServiceunderviewservicepage");
//			Thread.sleep(3000);
//			
//			String orderNumber=DirectFiber.get().fetchOrderNuber("LANLINK");
//			String serviceIdentification=DirectFiber.get().fetchServiceId("LANLINK");
//			
//			System.out.println("Enetred manage service");
//			DirectFiber.get().manageService("LANLINK", orderNumber, serviceIdentification);
//			
//		}
//		
	
	
//	
//	
//	@Test(dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService", priority=1)
//	public void manageServiceunderviewservicepage(Map<String, String> map) throws Exception {
//		
//		setup();
//		
//		 logger= ExtentTestManager.startTest ("AMNvalidator_Equipment");
//			String csrName=DirectFiber.get().fetchCSRsiteName("LANLINK");
//			String cityName=DirectFiber.get().fetchDeviceCityName("LANLINK");
//			String countryName=DirectFiber.get().fetchSiteOrderCountryName("LANLINK");
//			DirectFiber.get().clickOnAMNvalidatorLink("LANLINK");
//			DirectFiber.get().AMNvalidator("LANLINK","A(Outband MGMT)" , deviceName_Equip, csrName, cityName, countryName);
//					
//	}
	

			
		
}
