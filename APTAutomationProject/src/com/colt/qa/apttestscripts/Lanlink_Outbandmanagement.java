package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;


public class Lanlink_Outbandmanagement extends DriverTestcase {
	
	APT_Login Login=new APT_Login();
	
	public String deviceName_Equip=null;
	public String devicename_IntEquipment=null;
	public String CPEdeviceName_CustomerPremiseEquipment = null;

		@Test(dataProviderClass = DataReader.class, dataProvider = "outBandManagement", priority=0)
		public void chooseCustomer(Map<String, String> map) throws Exception {

			setup();
			
			Login.APT_Login_1(map.get("url"));
			
			String CustomerName1=null;
			String newCustomerName=map.get("newCustomerSelection");
			String existingCustomer=map.get("existingCustomerSelection");
				
				if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
						
						logger= ExtentTestManager.startTest ("CreateNewCustomer_Lanlink_OLO");
						Outband.get().CreateCustomer("apt", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
								map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
								map.get("Fax"));
						CustomerName1=map.get("newCustomer");
						
						logger= ExtentTestManager.startTest ("selectExistingCustomer_wholesaleSIPTrunking"); 
						Outband.get().selectCustomertocreateOrder("apt",map.get("newCustomer"));
						
				}
				else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
						
						logger= ExtentTestManager.startTest ("selectExistingCustomer_Lanlink_OLO"); 
						Outband.get().selectCustomertocreateOrder("apt",map.get("existingCustomer"));
						CustomerName1 = map.get("existingCustomer");
				}

				
			logger= ExtentTestManager.startTest ("verifyListofFieldsForOrderandServicetype");
					Outband.get().Verifyfields(("CreateOrderService"),map.get("ServiceType"), map.get("Modularmsp"), map.get("AutocreateService"));
					Outband.get().selectCustomertocreateOrderfromleftpane("CreateOrderService", CustomerName1);
					Outband.get().createorderservice("apt", map.get("NewOrderSelection"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"),
								map.get("ExistingOrderSelection"), map.get("ExistingOrderNumber"));
					Outband.get().selectServiceType("CreateOrderService", map.get("ServiceType"));
	 
				
			logger= ExtentTestManager.startTest ("selectTheServiceType");
					Outband.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
								map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
					
			
			logger= ExtentTestManager.startTest ("verifyFields_lanlink_outBand");
					Outband.get().VerifyFieldsForServiceSubTypeSelected("LANLINK",map.get("ServiceType"),map.get("Servicesubtype"), map.get("Interfacespeed"),
							map.get("Notification management"), map.get("Modularmsp"));
					
		
					
			logger= ExtentTestManager.startTest ("enterdataToCreateService_LANLINK_OutBand");
					Outband.get().selectOrder("apt", map.get("NewOrderSelection"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"),
							map.get("ExistingOrderSelection"), map.get("ExistingOrderNumber"));
					Outband.get().selectServiceType("CreateOrderService", map.get("ServiceType"));
					Outband.get().selectsubtypeunderServiceTypeSelected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Modularmsp"),
							map.get("AutocreateService"), map.get("A_Endtechnology"), map.get("B_Endtechnology"));
					Outband.get().dataToBeEnteredOncesubservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
							map.get("performanceReportngForServices"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
							map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
							map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"), map.get("Notification management"), map.get("performanceReportngForServices"),
							map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), map.get("premiumEIR_ServiceCreation"));
			
					
			logger= ExtentTestManager.startTest ("successmessageforServicecreation");
					Outband.get().verifysuccessmessage("LANLINK", "Service successfully created");
					
					
//			logger= ExtentTestManager.startTest ("verifyUserDetailsInformation");
//					Outband.get().VerifyUsersPanel("apt", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"),
//							map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//							map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),
//							map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//							map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"),
//							map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"),
//							map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), 
//							map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), map.get("SiteOrders_ToBeAvailable"),
//							map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
						
						 
			logger= ExtentTestManager.startTest ("verifyOrderDetailsInformation");
					Outband.get().verifyorderpanel_editorder("apt", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
					Outband.get().verifyorderpanel_changeorder("apt", map.get("ChangeOrder_newOrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
							map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
		
					
			logger= ExtentTestManager.startTest ("verifyEnteredValues_LalninkService");
					Outband.get().VerifydatenteredForServiceSubTypeSelected("LANLINK",map.get("ServiceType"),map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("serviceNumber"),map.get("endpointCPE"),map.get("email"), map.get("phone"), map.get("remark"), 
							map.get("PerformMonitor"),map.get("proactiveMonitor"), map.get("deliveryChannel"), map.get("ManagementOrder"), map.get("vpnTopology"), map.get("intermediateTechnology"),
							map.get("CircuitReference"), map.get("CircuitType"), map.get("AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
							map.get("AutocreateService"), map.get("ENNI checkbox"), map.get("manageConnectiondropdown"), map.get("A_Endtechnology"), map.get("B_Endtechnology"),  map.get("performanceReportngForServices"),
							map.get("PerCoS preformance reporting_serviceCreation"), map.get("Actelis Based_service creation"), map.get("StandardCIR_ServiceCreation"), map.get("StandardEIR_ServiceCreation"), map.get("premiumCIR_ServiceCreation"), map.get("premiumEIR_ServiceCreation"), map.get("Notification management"));
			
					
			logger= ExtentTestManager.startTest ("editService");
					Outband.get().EditTheservicesselected("LANLINK",map.get("Servicesubtype"),map.get("Interfacespeed"),map.get("Edit_serviceNumber"),map.get("Edit_endpointCPE"),map.get("EditService_email"), map.get("EditService_phone"), map.get("EditService_remark"), 
							map.get("EditService_PerformMonitor"),map.get("EditService_proactiveMonitor"), map.get("EditService_deliveryChannel"), map.get("EditService_ManagementOrder"),  map.get("EditService_intermediateTechnology"),
							map.get("EditService_CircuitReference"), map.get("EditService_CircuitType"), map.get("EditService_AggregateTraffic"), map.get("Delivery channel for select tag"),map.get("Modularmsp"),
							map.get("AutocreateService"), map.get("EditService_ENNI"), map.get("EditService_manageConnectiondropdown"), map.get("Edit_A_Endtechnology"), map.get("Edit_B_Endtechnology"), map.get("EditService_NotificationManagement"),
							map.get("EditService_perCoSperformanceReport"), map.get("EditService_actelisBased"), map.get("EditService_standardCIR"), map.get("EditService_standardEIR"), map.get("EditService_premiumCIR"), map.get("EditService_premiumEIR"));
			
					
			logger= ExtentTestManager.startTest ("successmessageforServiceUpdation");
					Outband.get().verifysuccessmessage("LANLINK", "Service successfully updated.");
					
					
			logger= ExtentTestManager.startTest ("synchronizeService");
					Outband.get().syncservices("LANLINK");	
					
					
			logger= ExtentTestManager.startTest ("showNewInfovistaReport");
					Outband.get().shownewInfovista("LANLINK");
					
					
			logger= ExtentTestManager.startTest ("ManageSubnets");
					Outband.get().manageSubnets("LANLINK");
					
					
			logger= ExtentTestManager.startTest ("DumpForlanlinkServiceunderviewservicepage");
					Outband.get().dump_viewServicepage("LANLINK");
			
					
					String managementConnectionValue=null;
					String editManageConnection=map.get("EditService_manageConnectiondropdown");
					String createManageConnection=map.get("manageConnectiondropdown");
					
					if(editManageConnection.equalsIgnoreCase("null")) {
						managementConnectionValue=createManageConnection;
					}else {
						managementConnectionValue=editManageConnection;
					}
					
			if(managementConnectionValue.equalsIgnoreCase("Onnet-Offnet")) {
				
				logger= ExtentTestManager.startTest ("addSiteOrder_Lanlink");
				Outband.get().Enteraddsiteorder("LANLINK");
				Outband.get().verifyAddsiteorderFields("LANLINK", map.get("Interfacespeed"));
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
				

		logger= ExtentTestManager.startTest ("SuccessmessageforCreationofSiteOrder");
				Outband.get().verifysuccessmessage("LANLINK", "Site order created successfully.");
				
				 
		logger= ExtentTestManager.startTest ("verifyEnteredValues_LanlinkSiteOrder");
				Outband.get().VerifyDataEnteredForSiteOrder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
						map.get("country"),map.get("city"),map.get("CSR_Name"), 
						map.get("existing_SiteOrdervalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
						map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("sitevalue"),
						map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
						map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
						map.get("Siteordernumber"), map.get("siteOrder_CircuitReference"), map.get("SiteOrder_Offnet"), map.get("SiteOrder_Ivrefrence"),map.get("SiteOrer_GCROloType"),
				  		map.get("SiteOrder_Vlan"), map.get("SiteOrder_Vlanethertype"),map.get("siteOrder_PrimaryVlan"), map.get("SiteOrder_PrimaryVlanEtherType"), map.get("SiteOrder_EPNoffnet"), map.get("SiteOrder_EPNEOSDH"));
				
				
		logger= ExtentTestManager.startTest ("editSiteOrder_Lanlink");
				Outband.get().returnbacktoviewsiteorderpage("LANLINK");
				Outband.get().selectRowForsiteorder("LANLINK", map.get("siteOrderNumber_OnnetOffnet"));
				Outband.get().editSiteOrder( "LANLINK", map.get("Interfacespeed"), map.get("EditSiteOrder_performReport"), map.get("EditSiteOrder_ProactiveMonitor"),map.get("EditSiteOrder_smartmonitor"),
				     map.get("EditSiteOrder_siteallias"), map.get("EditSiteOrder_VLANid"), map.get("EditSiteOrder_DCAenabledsite"), map.get("EditSiteOrder_cloudserviceprovider"), map.get("technology"), 
				     map.get("editsiteorder_nonterminationpoint"), map.get("editsiteorder_protected"), map.get("editsiteorder_devicename"), map.get("editsiteorder_remark"), map.get("SiteOrder_Ivrefrence"),
				     map.get("Siteordernumber"));
				Outband.get().verifysuccessmessage("LANLINK", "Site Order successfully updated.");
			
				
		logger= ExtentTestManager.startTest ("Actelis");
				String Technologyname=map.get("technology");
				if(Technologyname.equalsIgnoreCase("Actelis")) {
				boolean equipConfigurationPanel=DirectFiber.get().EquipmentCOnfigurationPanel("LANLINK");
					if(equipConfigurationPanel) {
						ExtentTestManager.getTest().log(LogStatus.INFO, "verify 'Add CPE Device'");
						Outband.get().equipConfiguration_Actelis_AddDevice("LANLINK", map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
								map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"), map.get("ActelisTech_addCPE_ETH_Port"));
						Outband.get().verifysuccessmessage("LANLINK", "Device successfully created");
						
						logger= ExtentTestManager.startTest ("verifyDataEntered");
						Outband.get().verifyDataEnteredFordeviceCreation_Actelis("LANLINK",  map.get("ActelisTech_addCPE_name"), map.get("ActelisTech_addCPE_vendor"), 
								map.get("ActelisTech_addCPE_routerID"), map.get("ActelisTech_addCPE_manageAddress"), map.get("ActelisTech_addCPE_MepID"),  map.get("ActelisTech_addCPE_ETH_Port"));
						Outband.get().returnbacktoviewsiteorderpage("LANLINK");
						
						logger= ExtentTestManager.startTest ("deleteDevice");
						Outband.get().deleteDeviceFromService_EquipmentConfig_Actelis("LANLINK",  map.get("ActelisTech_addCPE_name"));
						Outband.get().verifysuccessmessage("LANLINK", "Actelis CPE Device successfully deleted and removed from service");
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "'Equipment Configuration' panel is not displaying");
					}
				
			//Actelis Configuration panel
						logger= ExtentTestManager.startTest ("AddDSLAMandHSL");
						Outband.get().verifyAddDSLAMandHSLlink("LANLINK", map.get("ActelisTech_DSLAMdevice"));
						Outband.get().AddDSLAMandHSL("LANLINK", map.get("ActelisTech_DSLAMdevice"), map.get("ActelisTech_DSLAMInterfacename"));
						Outband.get().showInterface_ActelisConfiguuration("LANLINK");
						Outband.get().deletInterface_ActelisConfiguration("LANLINK", map.get("ActelisTech_DSLAMInterfacename"));
						Outband.get().successMessage_deleteInterfaceFromDevice_ActelisConfiguration("LANLINK");
				}else {
					ExtentTestManager.getTest().log(LogStatus.INFO, "Actelis panel will display only for 'Actelis' Technology");
				}
				
				
//		logger= ExtentTestManager.startTest ("Adddevice_Equipment");
//				String devicename=null;  String vendorModel=null;
//				String manageAdres=null;  String country="";
//				String siteOrderNumber=map.get("siteOrderNumber_OnnetOffnet");
//				
//				
//			//verify whether Equipment panel is available	
//				boolean EquipmentPanel=DirectFiber.get().findPanelHeader("LANLINK", "Equipment");
//				if(EquipmentPanel) {
//					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Equipment' panel is displaying under 'view site order' page as expected");
//			//Verify whether Equipment device to be created
//					if(map.get("deviceCreation_Equipment").equalsIgnoreCase("yes")){
//						
//						ExtentTestManager.getTest().log(LogStatus.INFO, " Device to be created for Eqiupment as per input provided");	
//
//				ExtentTestManager.getTest().log(LogStatus.INFO, "Under Equipement, list of actions to be performed are: "
//						+ "Verify fields for Add device"
//						+ "Add device"
//						+ "Verify entered values for device"
//						+ "Edit device"
//						+ "Select Interface"
//						+ "Configure Interface -- Edit Inteface"
//						+ "show/Hide Interface -- Edit Interface"
//						+ "Select Interface -- Add Interface to service , Remove Interface from Service"
//						+ "Delete device ");
//				
//				System.out.println("------For Equipment ..........Entering add cpe device, Verify CPE device, Edit CPE device----------");
//					String speed=map.get("Interfacespeed");
//					String existingDevice=map.get("Equip_ExistingDeviceSelection");
//					String newDevice=map.get("Equip_NewDeviceSelection");
//					
//				
//					if(speed.equals("1GigE")) {
//						if(existingDevice.equalsIgnoreCase("Yes") && newDevice.equalsIgnoreCase("No")) {
//							ExtentTestManager.getTest().log(LogStatus.INFO, "AddExistingDevice_1G_Eqiupment");
//							DirectFiber.get().verifyFieldsandSelectCPEdevicefortheserviceselected_existingDevice("LANLINK",map.get("Equip_existingDevicename"));
//							DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
//							
//							
//							logger= ExtentTestManager.startTest ("verifyValueForExistingDevice_1G_Equipment");	
//							DirectFiber.get().verifyValuesforCPEexistingdevice_1G_Equipment("LANLINK" );
//							
//							
//							logger= ExtentTestManager.startTest ("editExistingDevice_1G_Equipment");
//							DirectFiber.get().eDITCPEdevicedetailsentered_1G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
//									 map.get("EDIT_cpe_poweralarm_1G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
//									 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"), map.get("technology"));
//							DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
//							
//						}
//						else if(existingDevice.equalsIgnoreCase("No") && newDevice.equalsIgnoreCase("Yes")) {
//							ExtentTestManager.getTest().log(LogStatus.INFO, "addNewDevice_1G_Equipment");
//							DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_1G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
//									 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
//									 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"));
//							DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
//							
//							
//							logger= ExtentTestManager.startTest ("verifyEnteredValueForNewDevice_1G_Eqiupment");
//							DirectFiber.get().verifydetailsEnteredforCPEdevice_1G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
//								 map.get("cpe_poweralarm_1G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
//								 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"), map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"), map.get("technology"));
//							
//							
//							logger= ExtentTestManager.startTest ("editNewDevice_1G_Equipment");
//							DirectFiber.get().eDITCPEdevicedetailsentered_1G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_1G"),  map.get("cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
//									 map.get("EDIT_cpe_poweralarm_1G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
//									 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"), map.get("technology"));
//							DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
//						}
//						
//					}else if(speed.equals("10GigE")) {
//						
//						if(existingDevice.equalsIgnoreCase("Yes") && newDevice.equalsIgnoreCase("No")) {
//							ExtentTestManager.getTest().log(LogStatus.INFO, "addExistingDevice_10G_Eqiupment");
//							DirectFiber.get().verifyFieldsandSelectCPEdevicefortheserviceselected_existingDevice("LANLINK",map.get("Equip_existingDevicename"));
//							DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
//							
//							
//							logger= ExtentTestManager.startTest ("verifyValueForExistingDevice_10G_Equipment");
//							DirectFiber.get().verifyValuesforCPEexistingdevice_10G_Equipment("LANLINK" );
//							
//							
//							logger= ExtentTestManager.startTest ("editExistingDevice_10G_Eqiupment");
//							DirectFiber.get().eDITCPEdevicedetailsentered_10G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_10G"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
//									 map.get("EDIT_cpe_poweralarm_10G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
//									 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"));
//							DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
//						}
//							
//						else if(existingDevice.equalsIgnoreCase("No") && newDevice.equalsIgnoreCase("yes")) {
//							ExtentTestManager.getTest().log(LogStatus.INFO, "addNewDevice_10G_Equipment");
//							DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_10G("LANLINK",map.get("Interfacespeed"), map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
//									 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
//									 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
//							DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
//							
//							
//							logger= ExtentTestManager.startTest ("verifyEnteredValueForNewDevice_10G_Equipment");
//							DirectFiber.get().verifydetailsEnteredforCPEdevice_10G( "LANLINK",  map.get("devicename_equip"), map.get("cpe_vender_10G"),  map.get("cpe_snmpro"),  map.get("cpe_managementAddress"), map.get("cpe_Mepid"),
//									 map.get("cpe_poweralarm_10G"), map.get("cpe_Mediaselection"),  map.get("cpe_Macaddress"),  map.get("cpe_serialNumber"),
//									 map.get("cpe_hexaSerialnumber"),  map.get("cpe_linkLostForwarding"),  map.get("cpe_newmanagementAddressSelection"), map.get("cpe_existingmanagementAddressSelection"), map.get("cpe_manageaddressdropdownvalue"));
//							
//							
//							logger= ExtentTestManager.startTest ("editNewDevice_10G_Eqiupment");
//							DirectFiber.get().eDITCPEdevicedetailsentered_10G( "LANLINK",  map.get("EDIT_cpename"), map.get("EDIT_cpe_vender_10G"),  map.get("EDIT_cpe_snmpro"),  map.get("EDIT_cpe_managementAddress"), map.get("EDIT_cpe_Mepid"),
//									 map.get("EDIT_cpe_poweralarm_10G"), map.get("EDIT_cpe_Mediaselection"),  map.get("EDIT_cpe_Macaddress"),  map.get("EDIT_cpe_serialNumber"),
//									 map.get("EDIT_cpe_hexaSerialnumber"),  map.get("EDIT_cpe_linkLostForwarding"), map.get("devicenameforEquipment"));
//							DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
//						}
//					}	
//				
//				//get Device name 	
//					if(existingDevice.equalsIgnoreCase("Yes") && newDevice.equalsIgnoreCase("No")) {
//						devicename=map.get("Equip_existingDevicename");
//					}
//					else if(existingDevice.equalsIgnoreCase("No") && newDevice.equalsIgnoreCase("Yes")) {
//						if(map.get("EDIT_cpename").equalsIgnoreCase("null")) {
//							devicename=map.get("devicename_equip");
//						}
//						else if(!map.get("EDIT_cpename").equalsIgnoreCase("null")) {
//							devicename=map.get("EDIT_cpename");
//						}	
//					}
//					
//					DirectFiber.get().Equip_clickonviewButton("LANLINK", devicename);   //navigate to view device page
//						devicename=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");		//fetch Device Name
//						manageAdres=DirectFiber.get().fetchManagementAddressValue_InviewPage("LANLINK");	//Fetch Management Address
//						vendorModel=DirectFiber.get().fetchVendorModelValue("LANLINK");		//Fetch vendor/Model
//			
//				
//			 logger= ExtentTestManager.startTest ("TestCommandTable");	
//					DirectFiber.get().testStatus("LANLINK");
//						
//						
//			 logger= ExtentTestManager.startTest ("fetchDeviceInterface");
//					boolean link=DirectFiber.get().fetchDeviceInterface_viewdevicepage("LANLINK", devicename);
//					Thread.sleep(1000);
//		//			
//		//			if(link) {
//		//				manageNetwork.get().verifyFetchInterface("LANLINK", devicename, map.get("InServiceStatus"),
//		//						map.get("InMaintenanceStatus"), vendorModel, manageAdres, "JdhquA5", country, map.get("interfacename"));
//		//			}
//					
//					
//					 logger= ExtentTestManager.startTest ("routerTools_Equipment");
//						DirectFiber.get().routerPanel("LANLINK", map.get("CommandIPv4_Routertool"), manageAdres);
//						
//						
//				 logger= ExtentTestManager.startTest ("configureInterface_Equipment");
//						String devicename_EquipActual=null;
//						devicename=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
//						if(devicename.contains("...")) {
//							devicename_EquipActual = devicename.substring(0, 10);
//						}else {
//							devicename_EquipActual=devicename;
//						}
//						DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
//						DirectFiber.get().selectconfigurelinkAndverifyEditInterfacefield__Equipment("LANLINK", devicename_EquipActual);
//						DirectFiber.get().EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
//								map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
//						DirectFiber.get().verifyeditedinterfacevalue("LANLINK", map.get("Interfacename_forEditInterface"));
//					
//					
//				 logger= ExtentTestManager.startTest ("selectInterface_Equipment");
//						DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
//						DirectFiber.get().selectInterfacelinkforEqipment("LANLINK", devicename_EquipActual);
//							if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
//								DirectFiber.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//							}else {
//								System.out.println("interfaces are not removed");
//								ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces are not removed");
//							}
//							
//							if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
//								DirectFiber.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//							}else {
//								System.out.println("Interfaces are not added");
//								ExtentTestManager.getTest().log(LogStatus.PASS, "Interfaces are not removed");
//							}
//							
//							
//				 logger= ExtentTestManager.startTest ("showInterface_Equipment");
//							deviceName_Equip=devicename_EquipActual;
//							DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
//							DirectFiber.get().SelectShowInterfacelinkAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"), devicename_EquipActual);
//							DirectFiber.get().EnterdataForEditInterfaceforShowInterfacelinkunderEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
//										map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
//							DirectFiber.get().hideInterfaceLink_Equipment("LANLINK");
//							
//							
//				 logger= ExtentTestManager.startTest ("AMNvalidator_Equipment");
//							String csrName=DirectFiber.get().fetchCSRsiteName("LANLINK");
//							String cityName=DirectFiber.get().fetchDeviceCityName("LANLINK");
//							String countryName=DirectFiber.get().fetchSiteOrderCountryName("LANLINK");
//							DirectFiber.get().clickOnAMNvalidatorLink("LANLINK");
//							DirectFiber.get().AMNvalidator("LANLINK",siteOrderNumber , deviceName_Equip, csrName, cityName, countryName);
//									
//							
//				 logger= ExtentTestManager.startTest ("deleteDevice_Equipment");
//							DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
//							DirectFiber.get().deleteDeviceFromServiceForequipment("LANLINK", deviceName_Equip);
//							DirectFiber.get().successMessage_deleteFromService("LANLINK");
//			
//					
//				   }else {
//						ExtentTestManager.getTest().log(LogStatus.PASS, "Equipment device is not created as expected");
//						System.out.println("Equipment device is not created as expected");
//							}
//					}else {
//						ExtentTestManager.getTest().log(LogStatus.INFO, " 'Equipment' panel is not displaying under 'view site order' page");
//						System.out.println(" 'Equipment' panel is not displaying under 'view site order' page");
//					}
//				
//				
//			 logger= ExtentTestManager.startTest ("IntermediateEquipment");
//						String devicename_intEquip=null;  String country_intEquip="";
//						String manageAdres_intEqiup=null; String vendorModel_intEqiup=null;
//						
//						boolean IntermediateEquipmentPanel=DirectFiber.get().findPanelHeader("LANLINK", "Intermediate Equipment");
//						if(IntermediateEquipmentPanel) {
//							ExtentTestManager.getTest().log(LogStatus.PASS, " 'Intermediate Equipment' panel is displaying under 'view site order' page as expected");
//							
//							if(map.get("deviceCreation_IntermediateEquipment").equalsIgnoreCase("yes")){
//								ExtentTestManager.getTest().log(LogStatus.INFO, " Device to be created for Intermediate Eqiupment as per input provided");	
//						ExtentTestManager.getTest().log(LogStatus.INFO, "Under Intermediate Equipement, list of actions to be performed are: "
//								+ "Verify fields for Add device"
//								+ "Add device"
//								+ "Verify entered values for device"
//								+ "Edit device"
//								+ "Select Interface"
//								+ "show/Hide Interface -- Edit Interface"
//								+ "Select Interface -- Add Interface to service , Remove Interface from Service"
//								+ "Delete device ");
//					
//						String speed=map.get("Interfacespeed");
//						String existingDevice=map.get("IntEquip_existingdeviceSelection");
//						String newDevice=map.get("IntEquip_newdeviceSelection");
//						
//						DirectFiber.get().clickOnBreadCrump("LANLINK", map.get("siteOrderNumber_PointToPoint"));
//						
//						if(speed.equals("1GigE")) {
//							if(existingDevice.equalsIgnoreCase("Yes")  && newDevice.equalsIgnoreCase("No")) {
//								ExtentTestManager.getTest().log(LogStatus.INFO, "selectExistingDevice_1G_IntermediateEquipment");
//								DirectFiber.get().addDevice_IntEquipment("LANLINK");
//								DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
//								DirectFiber.get().verifyFieldsandSelectCPEdevice_existingDevice_IntEquipment("LANLINK", map.get("intEquip_existingDeviceValue"));
//								
//								logger= ExtentTestManager.startTest ("verifyExistingDeviceValue_1G_IntermediateEquipment");
//								DirectFiber.get().verifyValuesforCPEexistingdevice_1G_intEquipment("LANLINK");
//								
//								logger= ExtentTestManager.startTest ("editExistingDevice_1G_IntermediateEquipment");
//								DirectFiber.get().EDITCPEdevicedforIntermediateEquipment_1G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_1G_Overtue"),map.get("EDIT_Intequip_device_vender_1G_Accedian"),
//										map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
//										map.get("EDIT_Intequip_device_poweralarm_1G_Overture"),map.get("EDIT_Intequip_device_poweralarm_1G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
//										map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), 
//										map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
//										,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
//										map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"), 
//										map.get("TechToBeselected_underTechpopup_device"));
//								DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
//							}
//							else if(existingDevice.equalsIgnoreCase("No")  && newDevice.equalsIgnoreCase("Yes")) {
//								ExtentTestManager.getTest().log(LogStatus.INFO, "addNewDevice_1G_IntermediateEquipment");
//								DirectFiber.get().addDevice_IntEquipment("LANLINK");
//								DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
//								DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_1G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_1G_Overture"),
//										map.get("device_intequip_vender_1G_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
//										map.get("device_intequip_poweralarm_1G_overture"), map.get("device_intequip_poweralarm_1G_Accedian"), map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
//										map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
//										map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
//										map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
//										map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
//										map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));	
//							   DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
//								
//							   logger= ExtentTestManager.startTest ("verifyEnteredValues_1G_IntermediateEquipment");
//							   DirectFiber.get().verifyCPEdevicedataenteredForIntermediateEquipment_1G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_1G_Overture"),
//										map.get("device_intequip_vender_1G_Accedian"), map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
//										map.get("device_intequip_poweralarm_1G_overture"), map.get("device_intequip_poweralarm_1G_Accedian"), map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
//										map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
//										map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
//										map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
//										map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
//										map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));
//							  
//							   logger= ExtentTestManager.startTest ("editCPEdevice_1G_IntermediateEquipment");
//							   DirectFiber.get().EDITCPEdevicedforIntermediateEquipment_1G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_1G_Overtue"),map.get("EDIT_Intequip_device_vender_1G_Accedian"),
//										map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
//										map.get("EDIT_Intequip_device_poweralarm_1G_Overture"),map.get("EDIT_Intequip_device_poweralarm_1G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
//										map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), 
//										map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
//										,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
//										map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
//										map.get("TechToBeselected_underTechpopup_device"));
//							   DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
//							}
//						}
//				    else if(speed.equals("10GigE")) {
//				    	if(existingDevice.equalsIgnoreCase("Yes")  && newDevice.equalsIgnoreCase("No")) {
//				    		ExtentTestManager.getTest().log(LogStatus.INFO, "addExistingDevice_10G_IntermediateEquipment");
//				    		DirectFiber.get().addDevice_IntEquipment("LANLINK");
//				    		DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
//				    		DirectFiber.get().verifyFieldsandSelectCPEdevice_existingDevice_IntEquipment("LANLINK", map.get("intEquip_existingDeviceValue"));
//				    		DirectFiber.get().verifyValuesforCPEexistingdevice_10G_intEquipment("LANLINK");
//				    		
//				    		logger= ExtentTestManager.startTest ("editExistingDevice_10G_IntermediateEquipment");
//				    		DirectFiber.get().EDITCPEdevice_IntermediateEquipment_10G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), 
//									   map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
//										map.get("EDIT_Intequip_device_poweralarm_10G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
//										map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"), 
//										map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
//										,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
//										map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
//										map.get("TechToBeselected_underTechpopup_device"));
//				    		DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
//				    	}
//				    	else if(existingDevice.equalsIgnoreCase("No")  && newDevice.equalsIgnoreCase("Yes")) {
//				    		ExtentTestManager.getTest().log(LogStatus.INFO, "addNewDevice_10G_IntermediateEquipment");
//							DirectFiber.get().addDevice_IntEquipment("LANLINK");
//							DirectFiber.get().selectTechnology("LANLINK", map.get("TechToBeselected_underTechpopup_device"));
//							DirectFiber.get().verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_10G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_10G_Accedian"),
//									map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
//									map.get("device_intequip_poweralarm_10G_Accedian"),map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
//									map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
//									map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
//									map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
//									map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
//									map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));	
//						   DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully created");
//							
//						   logger= ExtentTestManager.startTest ("verifyEnteredValues_10G_IntermediateEquipment");
//						   DirectFiber.get().verifyCPEdevicedataenteredForIntermediateEquipment_10G( "LANLINK",  map.get("device_intEquip_name"), map.get("device_intequip_vender_10G_Accedian"),
//									map.get("device_intequip_snmpro"), map.get("device_intequip_managementAddress_textfield"), map.get("device_intequip_Mepid"),
//									map.get("device_intequip_poweralarm_10G_Accedian"),map.get("device_intequip_Mediaselection_Overture"),  map.get("device_intequip_Macaddress_Overture"),  map.get("device_intequip_serialNumber_Accedian"),
//									map.get("device_intequip_hexaSerialnumber"),  map.get("device_intequip_linkLostForwarding"), map.get("device_intequip_country"), map.get("device_intequip_existingcity_dropodwnvalue"),
//									map.get("device_intequip_site_dropdownvalue"), map.get("device_intequip_premisedropdownvalue"), map.get("device_intequip_newmanagementAddress_selection"), map.get("device_intequip_existingmanagementAddress_selection"), map.get("device_intequip_manageaddress_dropdownvalue"),
//									map.get("device_intequip_existingcityselectionmode"), map.get("device_intequip_newcityselectionmode"), map.get("device_intequip_cityname"), map.get("device_intequip_citycode"),
//									map.get("device_intequip_existingsiteselectionmode"), map.get("device_intequip_newsiteselectionmode"), map.get("device_intequip_sitename"), map.get("device_intequip_sitecode"),
//									map.get("device_intequip_existingpremiseselectionmode"), map.get("device_intequip_newpremiseselectionmode"), map.get("deivce_intequip_premisename"), map.get("device_intequip_premisecode"), map.get("TechToBeselected_underTechpopup_device"));
//						   
//						   logger= ExtentTestManager.startTest ("editNewDevice_10G_IntermediateEquipment");
//						   DirectFiber.get().EDITCPEdevice_IntermediateEquipment_10G("LANLINK", map.get("EDIT_Intequip_cpe_deviecname"), map.get("EDIT_Intequip_device_vender_10g_Accedian"), map.get("EDIT_Intequip_devic_snmpro"), 
//								   map.get("EDIT_Intequip_device_managementAddress"), map.get("EDIT_Intequip_device_Mepid"),
//									map.get("EDIT_Intequip_device_poweralarm_10G_Accedian"), map.get("EDIT_Intequip_device_Mediaselection"), map.get("EDIT_Intequip_device_Macaddress"),	map.get("EDIT_Intequip_device_serialNumber"), map.get("EDIT_Intequip_device_hexaSerialnumber"),
//									map.get("EDIT_Intequip_device_linkLostForwarding"), map.get("EDIT_Intequip_device_country"),
//									map.get("EDIT_Intequip_device_ExistingcitySelection"),map.get("EDIT_Intequip_device_newcitySelection"), map.get("EDIT_Intequip_device_Existingcity"), map.get("EDIT_Intequip_device_newcityName"), map.get("EDIT_Intequip_device_newcityCode")
//									,map.get("EDIT_Intequip_device_ExistingSiteSelection"), map.get("EDIT_Intequip_device_newSiteSelection"), map.get("EDIT_Intequip_device_ExistingSite"), map.get("EDIT_Intequip_device_newSiteName"), map.get("EDIT_Intequip_device_newSiteCode"),
//									map.get("EDIT_Intequip_device_existingPremiseSelection"), map.get("EDIT_Intequip_device_newPremiseSelection"), map.get("EDIT_Intequip_device_existingPremise"), map.get("EDIT_Intequip_device_newPremiseName"), map.get("EDIT_Intequip_device_newPremiseCode"),
//									map.get("TechToBeselected_underTechpopup_device"));
//						   DirectFiber.get().verifysuccessmessage("LANLINK", "Device successfully updated");
//				    		}
//						}
//						
//					//Get device name 	
//						if(existingDevice.equalsIgnoreCase("Yes")  && newDevice.equalsIgnoreCase("No")) {
//							devicename_intEquip=map.get("intEquip_existingDeviceValue");
//						}
//						else if(existingDevice.equalsIgnoreCase("no")  && newDevice.equalsIgnoreCase("Yes")) {
//							
//							if(map.get("EDIT_Intequip_cpe_deviecname").equalsIgnoreCase("null")) {
//								devicename_intEquip=map.get("device_intEquip_name");
//							}
//							else if(!map.get("EDIT_Intequip_cpe_deviecname").equalsIgnoreCase("null")) {
//								devicename_intEquip=map.get("EDIT_Intequip_cpe_deviecname");
//							}
//						}
//					
//				//navigate to view device page	
//					DirectFiber.get().IntEquip_clickonviewButton("LANLINK", devicename_intEquip);
//							devicename_intEquip=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");	//Device Name
//							manageAdres_intEqiup=DirectFiber.get().fetchManagementAddressValue_InviewPage("LANLINK");	//Management Address
//							vendorModel_intEqiup=DirectFiber.get().fetchVendorModelValue("LANLINK");		//Vendor/Model
//							country_intEquip=DirectFiber.get().fetchCountryValue_InviewPage("LANLINK");		//Country
//							
//						//Check Test Status	
//							DirectFiber.get().testStatus("LANLINK");
//						//Perform fetch from Interface
//							boolean link=DirectFiber.get().fetchDeviceInterface_viewdevicepage("LANLINK", devicename_intEquip);
//							Thread.sleep(1000);
//					//		if(link) {
//					//			manageNetwork.get().verifyFetchInterface("LANLINK", devicename_intEquip, map.get("InServiceStatus"),
//					//					map.get("InMaintenanceStatus"), vendorModel, manageAdres, "JdhquA5", country, map.get("interfacename"));
//					//		}
//							
//							logger= ExtentTestManager.startTest ("routerTools_IntermediateEquipment");
//							DirectFiber.get().routerPanel("LANLINK", map.get("CommandIPv4_Routertool"), manageAdres_intEqiup);
//								
//
//					logger= ExtentTestManager.startTest ("selectinterface_IntermediateEquipment");
//							String devicename_intEquipActual=null;
//							devicename_intEquip=DirectFiber.get().fetchdevicename_InviewPage("LANLINK");
//						
//							if(devicename_intEquip.contains("...")) {
//								devicename_intEquipActual = devicename_intEquip.substring(0, 10);
//							}else {
//								devicename_intEquipActual=devicename_intEquip;
//							}
//							devicename_IntEquipment=devicename_intEquipActual;
//							
//							DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
//							DirectFiber.get().selectInterfacelinkforIntermediateEqipment("LANLINK", devicename_intEquipActual);
//							if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
//								DirectFiber.get().SelectInterfacetoremovefromservice("LANLINK", map.get("Interfaceinservice_Interfacenumber"));
//							}else {
//								System.out.println("interfaces are not removed");
//							}
//							if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
//								DirectFiber.get().SelectInterfacetoaddwithservcie("LANLINK", map.get("Interfacetoselect_Interfacenumber"));
//							}
//							
//							
//					logger= ExtentTestManager.startTest ("showInterace_IntermediateEquipment");
//							DirectFiber.get().clickOnBreadCrump("LANLINK", siteOrderNumber);
//							DirectFiber.get().SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage("LANLINK", map.get("Interfacename_forEditInterface"),  devicename_intEquipActual );
//							DirectFiber.get().EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment("LANLINK",  map.get("Interfacename_forEditInterface"), map.get("editInterfacepage_circuitId"), map.get("editInterfacepage_BearerType"),
//									map.get("editInterfacepage_BearerSpeed"), map.get("editInterfacepage_bandwidth"), map.get("editInterfacepage_Vlanid"), map.get("editInterfacepage_vlantype"));
//							DirectFiber.get().hideInterface_IntEquipment("LANLINK");
//
//
//					logger= ExtentTestManager.startTest ("deletDevice_IntermediateEquipment");
//							DirectFiber.get().deleteDeviceFromServiceForIntermediateequipment("LANLINK", devicename_IntEquipment);
//							DirectFiber.get().successMessage_deleteFromService("LANLINK");
//							}
//						}else {
//							ExtentTestManager.getTest().log(LogStatus.PASS, " 'Intermediate Equipment' panel is not displaying under 'view site order' page");
//						}
						
						
				logger= ExtentTestManager.startTest ("PAMTest");
						String ServiceID = null;
						if(map.get("Edit_serviceNumber").equalsIgnoreCase("null")) {
							ServiceID=map.get("serviceNumber");
						}else {
							ServiceID=map.get("Edit_serviceNumber");
						}
						
						Outband.get().clickOnBreadCrump("LANLINK", ServiceID);
						Outband.get().selectRowForsiteorder_ForPAMTest("LANLINK", map.get("siteOrderNumber_OnnetOffnet"), map.get("Interfacespeed"));
						DirectFiber.get().pamTest("LANLINK", ServiceID);
						
						
				logger= ExtentTestManager.startTest ("deleteSiteOrder");
						DirectFiber.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"), map.get("siteOrderNumber_PointToPoint"), 
								map.get("vpnTopology"), map.get("Interfacespeed"), map.get("siteOrderNumber_10G_PointToPoint"));
						DirectFiber.get().deleteSiteOrder("LANLINK");
						
						
				logger= ExtentTestManager.startTest ("deleteService");		
						DirectFiber.get().deleteService("LANLINK");

				
			}
			else if(managementConnectionValue.equalsIgnoreCase("IPC Based")) {
				
				Outband.get().Enteraddsiteorder("LANLINK");
				Outband.get().addIPVPNsiteorder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
						map.get("country"),map.get("city"),map.get("CSR_Name"), 
						 map.get("sitevalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
						   map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("existing_SiteOrdervalue"),
						   map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
						   map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
						   map.get("Siteordernumber"), map.get("SiteOrder_Ivrefrence"), map.get("SiteOrder_perCoSPerformancereport"), map.get("primarySiteOrder"), map.get("SiteOrder_routerConfigurationViewIpv4"),
						   map.get("SiteOrder_wholesaleProvider"), map.get("SiteOrder_managedSite"), map.get("SiteORder_priority"), map.get("SiteOrder_actelisBased"), map.get("SiteOrder_voip"),
						   map.get("siteOrder_voipClassOfService"));
				Outband.get().verifysuccessmessage("LANLINK", "Site order created successfully .");
				
				
		logger= ExtentTestManager.startTest ("verifyEnteredValues_IPVPNSiteOrder");
				Outband.get().VerifyDataEnteredForIPVPNSiteOrder("LANLINK", map.get("Interfacespeed"), map.get("vpnTopology"), map.get("CircuitType"),
						map.get("country"),map.get("city"),map.get("CSR_Name"), 
						 map.get("sitevalue"), map.get("performReport"), map.get("Proactivemonitor"),map.get("smartmonitor"),
						   map.get("technology"),map.get("siteallias"), map.get("VLANid"), map.get("DCAenabledsite"), map.get("cloudserviceprovider"), map.get("existing_SiteOrdervalue"),
						   map.get("siteorder_Remark"), map.get("xng city name"), map.get("xng ciy code"), map.get("devicenameForaddsiteorder"),
						   map.get("nonterminationpoint"), map.get("protectforaddsiteorder"), map.get("newcity"), map.get("existingcity"), map.get("existingsite"), map.get("newsite"),
						   map.get("Siteordernumber"), map.get("SiteOrder_Ivrefrence"), map.get("SiteOrder_perCoSPerformancereport"), map.get("primarySiteOrder"), map.get("SiteOrder_routerConfigurationViewIpv4"),
						   map.get("SiteOrder_wholesaleProvider"), map.get("SiteOrder_managedSite"), map.get("SiteORder_priority"), map.get("SiteOrder_actelisBased"), map.get("SiteOrder_voip"),
						   map.get("siteOrder_voipClassOfService"));
				Outband.get().returnbacktoviewsiteorderpage("LANLINK");
				
				
		logger= ExtentTestManager.startTest ("editIPVPNsiteOrder");
				Outband.get().selectRowForIPVPNsiteorder("LANLINK", map.get("Siteordernumber"));
				Outband.get().editIPVPNsiteOrder("LANLINK", map.get("EditSiteOrder_performReport"), map.get("EditSiteOrder_ProactiveMonitor"),map.get("EditSiteOrder_smartmonitor"),
						   map.get("technology"),map.get("EditSiteOrder_siteallias"),  map.get("editsiteorder_remark"),map.get("editSiteOrder_ivReference"),
						   map.get("editSiteOrder_perCoSPerformancereport"), map.get("editprimarySiteOrder"), map.get("editSiteOrder_routerConfigurationViewIpv4"),
						   map.get("editSiteOrder_wholesaleProvider"), map.get("editSiteOrder_managedSite"), map.get("editSiteORder_priority"), map.get("editSiteOrder_actelisBased"), map.get("editSiteOrder_voip"),
						   map.get("editsiteOrder_voipClassOfService"));
				Outband.get().verifysuccessmessage("LANLINK", "Site Order successfully updated.");
				
				
		logger= ExtentTestManager.startTest ("selectDevice_Provider Equipment");
				Outband.get().providerEquipment("LANLINK", map.get("Pe_chooseAdevice"));
				Outband.get().verifyPEdeviceEnteredvalue("LANLINK",map.get("Pe_chooseAdevice"));
				
				
		logger= ExtentTestManager.startTest ("addInterface_PEdevice");
				Outband.get().clickOnBreadCrump("LANLINK", map.get("Siteordernumber"));
				Outband.get().cickOnViewButton_PEdevice("LANLINK", map.get("Pe_chooseAdevice"));   //navigate to view device page
				Outband.get().clickOnAddInterfaceLink_PE("LANLINK");  //click on "add Interface" link
				Outband.get().addInterface("LANLINK", map.get("PE_addInterface_bearerType_out"), map.get("PE_addInterface_encapsulation_out"),
						map.get("PE_addInterface_pppEncapsulation_out"), map.get("PE_addInterface_dslDownStreamSpeed_out"), map.get("PE_addInterface_dslUpstreamSpeed"), 
						map.get("PE_addInterface_mbsDropdown_out"), map.get("PE_addInterface_vpi_out"), map.get("PE_addInterface_vci_out"), map.get("PE_addInterface_slot_out"),
						map.get("PE_addInterface_port_out"), map.get("PE_addInterface_ivManagement"), map.get("PE_addInterface_existigAddressRangeSelection_out"),
						map.get("PE_addInterface_newAddressRangeSelection_out"), map.get("PE_addInterface_country_out"), map.get("PE_addInterface_subnetSize_out"),
						map.get("PE_addInterface_availableBlock_out"), map.get("PE_addInterface_addressDropdownValue"), map.get("PE_addInterface_newInterfaceAddressRangeValue_out"));
				Outband.get().verifysuccessmessage("LANLINK", "Interface added successfully");
				
				
		logger= ExtentTestManager.startTest ("addMultilink_PEdevice");
				Outband.get().clickOnBreadCrump("LANLINK",map.get("Pe_chooseAdevice"));
				Outband.get().clickOnAddMultiLink_PE("LANLINK");  //click on "add Multilink" link
				Outband.get().addMultilink("LANLINK", map.get("PE_addMultilink_existingAddressRangeSelection_out"), map.get("PE_addMultilink_newAddressRangeSelection_out"),
						map.get("PE_addMultilink_country_out"), map.get("PE_addMultilink_subnetSize"), map.get("PE_addMultilink_availableBlock_out"), map.get("PE_addMultilink_addressDropdown_out"),
						map.get("PE_addMultilink_newInterfaceAddressRangeValue_out"),
						map.get("PE_addMultilink_encapsulation_out"), map.get("PE_addMultilink_dslDownstreamSpeed_out"), map.get("PE_addMultilink_dslUpstreamSpeed_out"));
				Outband.get().verifysuccessmessage("LANLINK", "Multilink added successfully");
				
				
				
		logger= ExtentTestManager.startTest ("addLoopback_PEdevice");
				Outband.get().clickOnBreadCrump("LANLINK", map.get("Pe_chooseAdevice"));
				Outband.get().clickOnAddLoopback_PE("LANLINK");
				Outband.get().addLoopback("LANLINK", map.get("PE_addLoopback_interfaceAddress_out"), map.get("PE_addLoopback_ivmanagement_out"));
				Outband.get().verifysuccessmessage("LANLINK", "Loopback added successfully");
		
				
		logger= ExtentTestManager.startTest ("Autodiscover VPNs_PEdevice");
				Outband.get().clickOnBreadCrump("LANLINK", map.get("Siteordernumber"));
				Outband.get().clickOnautoDiscoverVPNPEdevice("LANLINK", map.get("Pe_chooseAdevice"));
				
		logger= ExtentTestManager.startTest ("Customer Premise Equipment");
				Outband.get().fetchCityName("LANLINK");
				Outband.get().clickOnCustomerPremiseEquipmentLink_addDevice("LANLINK");
				Outband.get().addCPEdevice_CustomerPremiseEquipment("LANLINK", map.get("CPE_routerId_out"), map.get("CPE_vendorModel_out"), 
						map.get("CPE_snmpV3ContextName_out"), map.get("CPE_managementAddress_out"), map.get("CPE_snmpro_out"), map.get("CPE_snmprw_out"), map.get("CPE_snmpv3SecurityUsername"),
						map.get("CPE_snmpV3AuthPassword"), map.get("CPE_snmpV3COntextEngineId_out"), map.get("CPE_snmpV3AuthProto_out"), map.get("CPE_existingPremiseSelection_out"),
						map.get("CPE_newPremiseSelection_out"), map.get("CPE_existingPremiseValue_out"), map.get("CPE_premisename"), map.get("CPE_premiseCode"));
				Outband.get().verifysuccessmessage("LANLINK", "Site device created successfully ");
				Outband.get().verifyCPEdevice("LANLINK", map.get("CPE_vendorModel_out"), map.get("CPE_managementAddress_out"), map.get("CPE_snmpro_out"));
				
				
		logger= ExtentTestManager.startTest ("editCPEdevice");
				CPEdeviceName_CustomerPremiseEquipment=Outband.get().fetchdeviceNameFromviewDevicePage("LANLINK");
				Outband.get().clickOnBreadCrump("LANLINK", map.get("Siteordernumber"));
				Outband.get().cickOnEditButton_CPEdevice("LANLINK", CPEdeviceName_CustomerPremiseEquipment);
				Outband.get().editCPEdevice_CustomerPremiseEquipment("LANLINK", map.get("editCPE_routerId_out"), map.get("editCPE_vendorModel_out"), 
						map.get("editCPE_snmpV3ContextName_out"), map.get("editCPE_managementAddress_out"), map.get("editCPE_snmpro_out"), map.get("editCPE_snmprw_out"),
						map.get("editCPE_snmpv3SecurityUsername"), map.get("editCPE_snmpV3AuthPassword"), map.get("editCPE_snmpV3COntextEngineId_out"),
						map.get("editCPE_snmpV3AuthProto_out"), map.get("editCPE_existingPremiseSelection_out"), map.get("editCPE_newPremiseSelection_out"), 
						map.get("editCPE_existingPremiseValue_out"), map.get("editCPE_premisename"), map.get("editCPE_premiseCode"));
				Outband.get().verifysuccessmessage("LANLINK", "Site device updated successfully");
				
				
		logger= ExtentTestManager.startTest ("addInterface_CustomerPremiseEquipment");
				Outband.get().clickOnAddInterfaceLink_CPE("LANLINK");  //click on "add Interface" link
				Outband.get().addInterface_CPEdevice("LANLINK", map.get("CPE_addInterface_interfaceTextValue_out"), map.get("CPE_addInterface_network_out"),
						map.get("CPE_addInterface_existingAddressRageSelection"), map.get("CPE_addInterface_newAddressRangeSelection"), map.get("CPE_addInterface_country_out"),
						map.get("CPE_addInterface_subnetSize_out"), map.get("CPE_addInterface_availableBLock_out"), map.get("CPE_addInterface_newInterfaceAddressRange_out"),
						map.get("CPE_addInterface_addressValue_out"), map.get("CPE_addInterface_IV64bitCounter"), map.get("CPE_addInterface_ethernet"), map.get("CPE_addInterface_speed_out"),
						map.get("CPE_addInterface_duplex"), map.get("CPE_addInterface_clockSource") );
				Outband.get().verifysuccessmessage("LANLINK", "Interface successfully created");
				
				
		logger= ExtentTestManager.startTest ("addMultilink_CustomerPremiseEquipment");
				Outband.get().clickOnAddMultiLink_CPE("LANLINK");  //click on "add Multilink" link
				Outband.get().addMultilink_CPEdevice("LANLINK", map.get("CPE_addMultilink_interfaceTextValue_out"), map.get("CPE_addMultilink_network_out"), map.get("CPE_addMultilink_existingAddressRageSelection"),
						map.get("CPE_addMultilink_newAddressRangeSelection"), map.get("CPE_addMultilink_country_out"), map.get("CPE_addMultilink_subnetSize_out"), map.get("CPE_addMultilink_availableBLock_out"),
						map.get("CPE_addMultilink_newInterfaceAddressRange_out"), map.get("CPE_addMultilink_addressValue_out"), map.get("CPE_addMultilink_IV64bitCounter"),
						map.get("CPE_addMultilink_ethernet"), map.get("CPE_addMultilink_speed_out"), map.get("CPE_addMultilink_duplex"), map.get("CPE_addMultilink_clockSource"));
				Outband.get().verifysuccessmessage("LANLINK", "Multilink interface successfully created");
				
				
		logger= ExtentTestManager.startTest ("addPPPConfiguration_CPEdevice");
				String CPEdeviceName=Outband.get().fetchdeviceNameFromviewDevicePage("LANLINK");
				Outband.get().clickOnBreadCrump("LANLINK", map.get("Siteordernumber"));
				Outband.get().clickOnpppConfigurationButton_CPEdevice("LANLINK", CPEdeviceName);
				Outband.get().pppConfiguration("LANLINK");
				Outband.get().addPPPconfiguration("LANLINK", CPEdeviceName, map.get("CPE_pppConfiguration_manualPassword_out"),
						map.get("CPE_pppconfiguration_framedWANipAddress_out"), map.get("CPE_pppConfiguration_framedRoute0"), map.get("CPE_pppConfiguration_framedRoute1"), map.get("CPE_pppConfiguration_framedRoute2"),
						map.get("CPE_pppConfiguration_framedRoute3"), map.get("CPE_pppConfiguration_framedRoute4"), map.get("CPE_pppConfiguration_framedRoute5"), map.get("CPE_pppConfiguration_framedRoute6"),
						map.get("CPE_pppConfiguration_framedRoute7"), map.get("CPE_pppConfiguration_uniVirtualRouterName"), map.get("CPE_pppConfiguration_uniLoopbackInteface"));
				Outband.get().verifysuccessmessage("LANLINK", "Multilink interface successfully created");
				
				
		logger= ExtentTestManager.startTest ("verifyEnteredValues_pppConfiguration_CPEdevice");	
				Outband.get().viewPPPconfiguration("LANLINK", map.get("CPE_pppconfiguration_framedWANipAddress_out"), map.get("CPE_pppConfiguration_framedRoute0"), 
						map.get("CPE_pppConfiguration_framedRoute1"), map.get("CPE_pppConfiguration_framedRoute2"),	map.get("CPE_pppConfiguration_framedRoute3"), 
						map.get("CPE_pppConfiguration_framedRoute4"), map.get("CPE_pppConfiguration_framedRoute5"), map.get("CPE_pppConfiguration_framedRoute6"),
						map.get("CPE_pppConfiguration_framedRoute7"), CPEdeviceName );
				
				
		logger= ExtentTestManager.startTest ("editPPPConfiguration_CPEdevice");
				Outband.get().pppConfigurationClickOnEditLink("LANLINK");
				Outband.get().editPPPconfiguration("LANLINK", map.get("CPE_editpppConfiguration_framedRoute0"), map.get("CPE_editpppConfiguration_framedRoute1"),
						map.get("CPE_editpppConfiguration_framedRoute2"), map.get("CPE_pppConfiguration_framedRoute3"), map.get("CPE_pppConfiguration_framedRoute4"),
						map.get("CPE_pppConfiguration_framedRoute5"), map.get("CPE_pppConfiguration_framedRoute6"), map.get("CPE_pppConfiguration_framedRoute7"),
						map.get("CPE_pppConfiguration_uniVirtualRouterName"), map.get("CPE_pppConfiguration_uniLoopbackInteface"));
				Outband.get().verifysuccessmessage("LANLINK", "Device successfully updated");
				
				
		logger= ExtentTestManager.startTest ("deletePPPConfiguration_CPEdevice");
				Outband.get().deletePPPconfiguration("LANLINK", map.get("Siteordernumber"));
				
				
		logger= ExtentTestManager.startTest ("customerReadonlySNMPfunction");
				Outband.get().addCustomerReadonlySNMPFunction_CPE("LANLINK", map.get("CustomerIPAddress"), map.get("CustomerCommunityString"));
				Outband.get().editCustomerReadonlySNMPFunction_CPE("LANLINK", map.get("CustomerIPAddressEdit"),map.get("CustomerCommunityStringEdit"), map.get("CustomerIPAddress"));
					String CustomerIPvalue = null;
					if(map.get("CustomerIPAddressEdit").equalsIgnoreCase("null")) {
						CustomerIPvalue = map.get("CustomerIPAddress");
					}else {
						CustomerIPvalue = map.get("CustomerIPAddressEdit");
					}
				Outband.get().deleteCustomerReadonlySNMPFunction_CPE("LANLINK", map.get("CustomerIPAddressEdit"),map.get("CustomerCommunityStringEdit"), CustomerIPvalue);
		
			
		logger= ExtentTestManager.startTest ("Extra Subnet");
				Outband.get().addExtraSubnetFunction_CPE("LANLINK", map.get("ExtraSubnets_City"), map.get("ExtraSubnets_SubnetSize"));

			
		logger= ExtentTestManager.startTest ("NAT Configuration");
				Outband.get().editNATConfigurationFunction_CPE("LANLINK", map.get("StaticNATEdit"),map.get("DynamicNATEdit"));
					
			
		logger= ExtentTestManager.startTest ("Static NAT Configuration");
					if(map.get("StaticNATEdit").equalsIgnoreCase("Yes")) {
						Outband.get().addStaticNATMappingFunction_CPE("LANLINK", map.get("Static_Protocol"),
								map.get("Static_LocalPort"), map.get("Static_GlobalPort"), map.get("Static_LocalIP"), map.get("Static_GlobalIP"));
						Outband.get().editStaticNATMappingFunction_CPE("LANLINK", map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"),
								 map.get("Static_GlobalPortEdit"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"), map.get("Static_LocalIP"));
								String localIP = null;
								if(map.get("Static_LocalIPEdit").equalsIgnoreCase("null")) {
									localIP = map.get("Static_LocalIP");
								}else{
									localIP = map.get("Static_LocalIPEdit");
								}
						Outband.get().deleteStaticNATMappingFunction_CPE("LANLINK", localIP);
					}
					else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Static NAT Configuration panel not displaying, as 'Static NAT' checkbox is unselected under 'NAT Configuration'");
					}
					
					
		logger= ExtentTestManager.startTest ("Dynamic NAT Configuration");
					if(map.get("DynamicNATEdit").equalsIgnoreCase("Yes")) {
						Outband.get().addDynamicNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
								map.get("Static_Protocol"),map.get("Static_LocalPort"), map.get("Static_GlobalPort"), map.get("Static_LocalIP"), map.get("Static_GlobalIP"));
								
						Outband.get().editDynamicNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
								map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));
		
						Outband.get().deleteDynamicNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
								map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));
					}
					else {
						ExtentTestManager.getTest().log(LogStatus.INFO, "Dynamic NAT Configuration panel not displaying,  as 'Dynamic NAT' checkbox is unselected under 'NAT Configuration'");
					}
			
			
		logger= ExtentTestManager.startTest ("DHCP Servers on CPE");
					Outband.get().addDHCPServersonCPEFunction_CPE("LANLINK", map.get("DHCP_CustomerLANSubnet"),map.get("DHCP_SubnetMask"), 
							map.get("DHCP_PrimaryDNSServer"), map.get("DHCP_SecondaryDNSServer"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"));
					Outband.get().editDHCPServersonCPEFunction_CPE("LANLINK", map.get("DHCP_CustomerLANSubnetEdit"),map.get("DHCP_SubnetMaskEdit"),
							 map.get("DHCP_PrimaryDNSServerEdit"), map.get("DHCP_SecondaryDNSServerEdit"), map.get("DHCP_CustomerLANSubnet"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"));

							String customerLANsubnet = "Null";
							if(map.get("DHCP_CustomerLANSubnetEdit").equalsIgnoreCase("Null")) {
								customerLANsubnet = map.get("DHCP_CustomerLANSubnet");
							}else {
								customerLANsubnet = map.get("DHCP_CustomerLANSubnetEdit");
							}
					Outband.get().deleteDHCPServersonCPEFunction_CPE("LANLINK", customerLANsubnet, map.get("StaticNATEdit"), map.get("DynamicNATEdit"));
					
					
			logger= ExtentTestManager.startTest ("Generate Configuration");
					Outband.get().generateConfiguration("LANLINK", map.get("generateConfigurationValue"));
					
					
			logger= ExtentTestManager.startTest ("PE to CPE Link");
					Outband.get().clickOnBreadCrump("LANLINK", map.get("Siteordernumber"));
					Outband.get().createPEtoCPElink("LANLINK", map.get("peToCPElink_circuitID"), map.get("Pe_chooseAdevice"), "null",
							CPEdeviceName , "null");
					
					
			logger= ExtentTestManager.startTest ("deletePEdevice");
					Outband.get().deletePEdevice("LANLINK", map.get("Pe_chooseAdevice"));
					
					
			logger= ExtentTestManager.startTest ("deleteCPEdevice");		
					Outband.get().deleteCPEdevice("LANLINK" , CPEdeviceName);
					
					
			logger= ExtentTestManager.startTest ("deleteSiteOrder");
					String ServiceID = null;
					if(map.get("Edit_serviceNumber").equalsIgnoreCase("null")) {
						ServiceID=map.get("serviceNumber");
					}else {
						ServiceID=map.get("Edit_serviceNumber");
					}
					
					Outband.get().clickOnBreadCrump("LANLINK", ServiceID);
					DirectFiber.get().selectRowForsiteorder("LANLINK", map.get("Siteordernumber"), map.get("siteOrderNumber_PointToPoint"), 
							map.get("vpnTopology"), map.get("Interfacespeed"), map.get("siteOrderNumber_10G_PointToPoint"));
					DirectFiber.get().deleteSiteOrder("LANLINK");
					
					
			logger= ExtentTestManager.startTest ("deleteService");		
					DirectFiber.get().deleteService("LANLINK");
					
			}
	}
		
		
//		@Test(description = "TC-19",dataProviderClass = DataReader.class, dataProvider = "DataReader_createService_outbandManagement", priority=19)
//		 public void fetchDeivceInterface1(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
//			
//			logger= ExtentTestManager.startTest ("PEtoCPElink");
//		
//			Outband.get().clickOnFetchDeviceInterfaec_CPEdevice("LANLINK", "7394");
//			
//			String CPEdveiceName = "21.212342343";
//			
//			
//			
//		}
		
	
	
	
//	
//		@Test(description = "TC-19",dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_outbandManagement", priority=19)
//		 public void verifyAddEditDeleteRouterFunction_CPE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
//			logger= ExtentTestManager.startTest ("RouterPanel");
//			
//			Outband.get().addRouterFunction_CPE("LANLINK", map.get("CPE_RouteCity"),
//				 map.get("CPE_RouteSubnetSize"), map.get("CPE_Gateway"), map.get("CPE_NetworkAddress"), map.get("CPE_NetworkMAS"),	map.get("CPE_Metrics"));
//			
//			Outband.get().editRouterFunction_CPE("LANLINK", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),map.get("CPE_RouteCityEdit"),
//					map.get("CPE_RouteSubnetSizeEdit"), map.get("CPE_GatewayEdit"), map.get("CPE_NetworkAddressEdit"), map.get("CPE_NetworkMASEdit"),map.get("CPE_MetricsEdit"));
//			
//			Outband.get().deleteRouterFunction_CPE("LANLINK", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),map.get("CPE_RouteCityEdit"),
//					map.get("CPE_RouteSubnetSizeEdit"), map.get("CPE_GatewayEdit"), map.get("CPE_NetworkAddressEdit"), map.get("CPE_NetworkMASEdit"),map.get("CPE_MetricsEdit"));
//		 }	
//				
//			
	
		 
//		 @Test(description = "TC-19",dataProviderClass = APT_DataReader_SS.class, dataProvider = "DataReader_createService_outbandManagement", priority=19)
//		 public void verifyAddEditDeleteRouterFunction_CPE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
//			 
//			logger= ExtentTestManager.startTest ("Generate Configuration");
//		 
//			
//		 
//		 }
		
		
		
//////		
		
		@Test(dataProviderClass = DataReader.class, dataProvider = "outBandManagement", priority=20)
	    public void Lanlink_Circuit(Map<String, String> map) throws Exception {
			
			String ServiceID = null;
			if(map.get("Edit_serviceNumber").equalsIgnoreCase("null")) {
				ServiceID=map.get("serviceNumber");
			}else {
				ServiceID=map.get("Edit_serviceNumber");
			}
			
//			if(map.get("CircuitType").equalsIgnoreCase("Composite Circuit")) {
				
				if(map.get("Interfacespeed").equals("1GigE")) {
					
				//Overture	
					Outband.get().addOvertureCircuit("LANLINK", map.get("addOverture_serviceNameForCreatingCircuit"));
					Outband.get().selectInterfaceForCircuits("LANLINK", map.get("addOverture_interfaceName1"), map.get("addOverture_interfaceName2"),
							map.get("addOverture_edgePointSelectForInterface1"), map.get("addOverture_edgePointSelectForInterface2"));
					Outband.get().verifysuccessmessage("LANLINK", "Circuit successfully created");
					Outband.get().addOveture_PAMtest_selectRow("LANLINK", map.get("addOverture_interfaceName1"));
					Outband.get().PAMtest_ForCircuitCreation("LANLINK", ServiceID, map.get("addOverture_serviceNameForCreatingCircuit"));
					Outband.get().deleteCircuit("LANLINK");
					
					
				//Accedian-1G
					Outband.get().addAccedianCircuit("LANLINK", map.get("addAccedian1G_serviceNameForCreatingCircuit"));
					Outband.get().selectInterfaceForCircuits("LANLINK", map.get("addAccedian1G_interfaceName1"), map.get("addAccedian1G_interfaceName2"),
							map.get("addAccedian1G_edgePointSelectForInterface1"), map.get("addAccedian1G_edgePointSelectForInterface2"));
					Outband.get().verifysuccessmessage("LANLINK", "Circuit successfully created");
					Outband.get().addOveture_PAMtest_selectRow("LANLINK",  map.get("addAccedian1G_interfaceName1"));
					Outband.get().PAMtest_ForCircuitCreation("LANLINK", ServiceID, map.get("addAccedian1G_serviceNameForCreatingCircuit"));
					Outband.get().deleteCircuit("LANLINK");
					
					
				//Atrica
					Outband.get().addAtricaCircuit("LANLINK", map.get("addAtrica_serviceNameForCreatingCircuit"));
					Outband.get().selectInterfaceForCircuits("LANLINK", map.get("addAtrica_interfaceName1"), map.get("addAtrica_interfaceName2"),
							map.get("addAtrica_edgePointSelectForInterface1"), map.get("addAtrica_edgePointSelectForInterface2"));
					Outband.get().verifysuccessmessage("LANLINK", "Circuit successfully created");
					Outband.get().addOveture_PAMtest_selectRow("LANLINK",  map.get("addAtrica_interfaceName1"));
					Outband.get().PAMtest_ForCircuitCreation("LANLINK", ServiceID, map.get("addAtrica_serviceNameForCreatingCircuit"));
					Outband.get().deleteCircuit("LANLINK");
				
				}
				
				
				else if(map.get("Interfacespeed").equals("10GigE")) {
					//Overture	
					Outband.get().addOvertureCircuit("LANLINK", map.get("addOverture_serviceNameForCreatingCircuit"));
					Outband.get().selectInterfaceForCircuits("LANLINK", map.get("addOverture_interfaceName1"), map.get("addOverture_interfaceName2"),
							map.get("addOverture_edgePointSelectForInterface1"), map.get("addOverture_edgePointSelectForInterface2"));
					Outband.get().verifysuccessmessage("LANLINK", "Circuit successfully created");
					Outband.get().addOveture_PAMtest_selectRow("LANLINK", map.get("addOverture_interfaceName1"));
					Outband.get().PAMtest_ForCircuitCreation("LANLINK", ServiceID, map.get("addOverture_serviceNameForCreatingCircuit"));
					Outband.get().deleteCircuit("LANLINK");
					
					
				}
				
				
//			}
			
			
		}

}
