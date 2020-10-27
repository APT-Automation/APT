package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.scripthelpers.APT_LoginHelper;

public class APT_IPAccessSpeedboatTest extends DriverTestcase{
	
public String CustomerName=null;
	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_IPAccessSpeedboat")
	public void IPAccessVCPEConfigService(Map<String, String> map) throws Exception {
		
		setup();	
		
		Login.APT_Login_1(map.get("url for the product"));
		
        String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
        
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
              
              logger= ExtentTestManager.startTest("CreateCustomer");
              APT_IPASpeedboatHelper.get().createcustomer("ipaSpeedboat", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              ExtentTestManager.endTest();
              
              logger= ExtentTestManager.startTest("selectExistingCustomer"); 
              APT_IPASpeedboatHelper.get().selectCustomertocreateOrder("ipaSpeedboat",map.get("newCustomer"));
              ExtentTestManager.endTest();
        }
        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              logger= ExtentTestManager.startTest("selectExistingCustomer"); 
              APT_IPASpeedboatHelper.get().selectCustomertocreateOrder("ipaSpeedboat",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
              ExtentTestManager.endTest();
        }
        
		logger= ExtentTestManager.startTest("verifyCreateorder");
		APT_IPASpeedboatHelper.get().createorderservice("ipaSpeedboat", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyservicetypeselection");
		APT_IPASpeedboatHelper.get().verifyselectservicetype("ipaSpeedboat", map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyservicecreation");
		APT_IPASpeedboatHelper.get().verifyservicecreation("ipaSpeedboat", map.get("ServiceIdentification"), map.get("Remarks")
				, map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType")
				, map.get("NetworkConfiguration_DrodpwonValue"), map.get("TerminationDate"), map.get("BillingTypevalue")
				, map.get("Email"), map.get("PhoneContact"), map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox")
				, map.get("RouterConfigView_IPv4_Checkbox"), map.get("RouterConfigView_IPv6_Checkbox")
				, map.get("DeliveryChannel_DropdownValue"), map.get("Topology_DropdownValue"), map.get("SmartsMonitoringDestination_DropdownValue")
				, map.get("DataCenterGeneration_DropdownValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyCustomerDetailsInformation");
		APT_IPASpeedboatHelper.get().verifyCustomerDetailsInformation("ipaSpeedboat", map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		APT_IPASpeedboatHelper.get().verifyUserDetailsInformation("ipaSpeedboat", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
		ExtentTestManager.endTest();
		
//		logger= ExtentTestManager.startTest("verifyUserDetailsInformation");
//		APT_IPASpeedboatHelper.get().VerifyUsersPanel("ipaSpeedboat", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//				map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//				map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"), map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"), 
//				map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), 
//				map.get("SiteOrders_ToBeAvailable"), map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
//		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyOrderDetailsInformation");
		APT_IPASpeedboatHelper.get().verifyorderpanel_editorder("ipaSpeedboat", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
		APT_IPASpeedboatHelper.get().verifyorderpanel_changeorder("ipaSpeedboat", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyServicepanelinviewservicepage");
	  	APT_IPASpeedboatHelper.get().verifyservicepanelInformationinviewservicepage("ipaSpeedboat", map.get("ServiceIdentification")
	  			, map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"), map.get("Remarks"), map.get("TerminationDate")
	  			, map.get("BillingTypevalue"), map.get("Email"), map.get("PhoneContact"), map.get("SmartsMonitoringDestination_DropdownValue"));
	  	ExtentTestManager.endTest();
	  	
		logger= ExtentTestManager.startTest("verifyManagementOptionspanel");
		APT_IPASpeedboatHelper.get().verifyManagementConfigpanels("ipaSpeedboat", map.get("PerformanceReporting_Checkbox")
				, map.get("IPGuardian_Checkbox"), map.get("RouterConfigView_IPv4_Checkbox"), map.get("RouterConfigView_IPv6_Checkbox")
				, map.get("DeliveryChannel_DropdownValue"), map.get("Topology_DropdownValue"), map.get("DataCenterGeneration_DropdownValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyServicepanelLinks");
		APT_IPASpeedboatHelper.get().verifyEditservice("ipaSpeedboat", map.get("ServiceIdentification"), map.get("ServiceType")
				, map.get("NetworkConfiguration_DrodpwonValue"), map.get("EditRemarks"), map.get("Remarks")
				, map.get("Edit_TerminationDate"), map.get("Edit_BillingTypevalue"), map.get("Edit_Email"), map.get("Edit_PhoneContact")
				, map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_IPGuardian_Checkbox")
				, map.get("Edit_RouterConfigView_IPv4_Checkbox"), map.get("Edit_RouterConfigView_IPv6_Checkbox")
				, map.get("Edit_DeliveryChannel_DropdownValue"), map.get("Topology_DropdownValue")
				, map.get("Edit_SmartsMonitoringDestination_DropdownValue"), map.get("DataCenterGeneration_DropdownValue"));
		APT_IPASpeedboatHelper.get().verifyShowNewInfovistaReport("ipaSpeedboat");
		APT_IPASpeedboatHelper.get().verifyManageSubnets("ipaSpeedboat");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("VerifyManageService");
		APT_IPASpeedboatHelper.get().verifyManageService("ipaSpeedboat", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		//=====================================  Part2 ===============================================
		
		System.out.println("TC-01");
		logger= ExtentTestManager.startTest("verifyAddViewDeleteExistingDistributionSwitchDevice");
		APT_IPASpeedboatHelper.get().searchorder("IPAccessSpeedboat2", map.get("ServiceIdentification"));
		APT_IPASpeedboatHelper.get().addExistingDistributionSwitchDevice_PE("IPAccessSpeedboat2",map.get("Topology_DropdownValue"), map.get("ExistingDestributionSwitchDeviceName"));
		APT_IPASpeedboatHelper.get().verifyExistingDistributionSwitchDeviceInfo_PE("IPAccessSpeedboat2",map.get("Topology_DropdownValue"), map.get("ExistingDestributionSwitchDeviceName"));
		APT_IPASpeedboatHelper.get().testStatus_PE("IPAccessSpeedboat2");
		ExtentTestManager.endTest();

		
		
		
		System.out.println("TC-02");
		logger= ExtentTestManager.startTest("verifyRouterToolFunction_PE_DistributionSwitch_Speedboat");
		APT_IPASpeedboatHelper.get().routerPanel_PE("IPAccessSpeedboat2", map.get("PE_CommandIPV4"),
				map.get("PE_CommandIPV6"),map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
		ExtentTestManager.endTest();
					
				
		
		
					
		System.out.println("TC-03");		
		logger= ExtentTestManager.startTest("verifyAddEditDeleteRoutesFunction_PE_DistributionSwitch_Speedboat");
		APT_IPASpeedboatHelper.get().addRouteFunction_PE("IPAccessSpeedboat2", map.get("ServiceIdentification"),map.get("ExistingDestributionSwitchDeviceName"), 	map.get("PE_DS_RouteCity"),
		map.get("PE_RouteSubnetSize"), map.get("PE_Gateway"), map.get("PE_NetworkAddress"), map.get("PE_NetworkMAS"),	map.get("PE_Metrics"));
		APT_IPASpeedboatHelper.get().editRouteFunction_PE("IPAccessSpeedboat2", map.get("ServiceIdentification"),map.get("ExistingDestributionSwitchDeviceName"),map.get("PE_DS_RouteCityEdit"),
		map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
		APT_IPASpeedboatHelper.get().deleteRouteFunction_PE("IPAccessSpeedboat2", map.get("ServiceIdentification"),map.get("ExistingDestributionSwitchDeviceName"),map.get("PE_DS_RouteCityEdit"),
		map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
		ExtentTestManager.endTest();
		
		
		
		
		System.out.println("TC-04");
		logger= ExtentTestManager.startTest("verifyEditCPEDeviceFunction_Speedboat");
		APT_IPASpeedboatHelper.get().verifyEditDeviceFunction_DS("IPAccessSpeedboat2", map.get("ServiceIdentification"),
				map.get("DS_DeviceNameEdit"),map.get("DS_VendorModelEdit"),map.get("DS_ManagementAddressEdit"),map.get("DS_CountryEdit"),
				map.get("DS_CityEdit"),  map.get("DS_SiteEdit"), map.get("DS_PremiseEdit")); 
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-05");
		logger= ExtentTestManager.startTest("VerifyFetchDeviceInterfaceFunction_PE_Speedboat");
		APT_IPASpeedboatHelper.get().verifyFetchDeviceInterface_DS("IPAccessSpeedboat2", map.get("ExistingDestributionSwitchDeviceName"), map.get("InServiceStatus"), map.get("InMaintenanceStatus"), 
				map.get("DS_VendorModelEdit"), map.get("DS_ManagementAddressEdit"), map.get("SnmProNewValue"), map.get("DS_CountryEdit"), map.get("InterfaceName"));
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-06");
		logger= ExtentTestManager.startTest("deleteDistributionSwitchDevice_Speedboat");
		APT_IPASpeedboatHelper.get().searchorder("IPAccessSpeedboat2", map.get("ServiceIdentification"));//ServiceIdentification
		APT_IPASpeedboatHelper.get().deleteDistributionSwitchDevice("IPAccessSpeedboat2", map.get("ExistingDestributionSwitchDeviceName"));
		ExtentTestManager.endTest();
					
		
		
		
		//ACCESS SWITCH
		System.out.println("TC-07");
		logger= ExtentTestManager.startTest("VerifyAddViewDeleteExistingAccessSwitchDevice_Speedboat");
		APT_IPASpeedboatHelper.get().searchorder("IPAccessSpeedboat2", map.get("ServiceIdentification"));
		APT_IPASpeedboatHelper.get().addExistingAccessSwitchDevice("IPAccessSpeedboat2",map.get("Topology_DropdownValue"), map.get("ExistingAccessSwitchDeviceName"));
		APT_IPASpeedboatHelper.get().verifyExistingAccessSwitchDeviceInfo_PE("IPAccessSpeedboat2",map.get("Topology_DropdownValue"), map.get("ExistingAccessSwitchDeviceName"));
		APT_IPASpeedboatHelper.get().testStatus_PE("IPAccessSpeedboat2");
		ExtentTestManager.endTest();

					
					
		
		
		System.out.println("TC-08");
		logger= ExtentTestManager.startTest("verifyRouterToolFunction_PE_AccessSwitch_Speedboat");
		APT_IPASpeedboatHelper.get().routerPanel_PE("IPAccessSpeedboat2", map.get("PE_CommandIPV4"),
					map.get("PE_CommandIPV6"),map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
		ExtentTestManager.endTest();
								
				
		
								
		System.out.println("TC-09");						
		logger= ExtentTestManager.startTest("verifyAddEditDeleteRoutesFunction_AccessSwitch_Speedboat");
		APT_IPASpeedboatHelper.get().addRouteFunction_AS("IPAccessSpeedboat2", map.get("ServiceIdentification"),map.get("DeviceName"), 	map.get("PE_RouteCity"),
				map.get("PE_RouteSubnetSize"), map.get("PE_Gateway"), map.get("PE_NetworkAddress"), map.get("PE_NetworkMAS"),	map.get("PE_Metrics"));
		APT_IPASpeedboatHelper.get().editRouteFunction_AS("IPAccessSpeedboat2", map.get("ServiceIdentification"),map.get("DeviceName"),map.get("PE_RouteCityEdit"),
				map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
		APT_IPASpeedboatHelper.get().deleteRouteFunction_AS("IPAccessSpeedboat2", map.get("ServiceIdentification"),map.get("DeviceName"),map.get("PE_RouteCityEdit"),
					map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
		ExtentTestManager.endTest();
										
		
		
		System.out.println("TC-10");
		logger= ExtentTestManager.startTest("deleteAccessSwitchDevice");
		APT_IPASpeedboatHelper.get().searchorder("IPAccessSpeedboat2", map.get("ServiceIdentification"));//ServiceIdentification
		APT_IPASpeedboatHelper.get().deleteAccessSwitchDevice("IPAccessSpeedboat2", map.get("ExistingAccessSwitchDeviceName"));
		ExtentTestManager.endTest();

		
		

		
		
		
		System.out.println("TC-11");
		logger= ExtentTestManager.startTest("VerifyAddNewPEDeviceFunction_Speedboat");
		APT_IPASpeedboatHelper.get().searchorder("IPAccessSpeedboat2", map.get("ServiceIdentification"));
		APT_IPASpeedboatHelper.get().navigateToAddNewDestributionSwitchDevicePage("IPAccessSpeedboat2");
		APT_IPASpeedboatHelper.get().verifyAddNewDestributionSwitchDeviceFields("IPAccessSpeedboat2");
		APT_IPASpeedboatHelper.get().verifyAddNewDestributionSwitchDevice("IPAccessSpeedboat2", map.get("DeviceName"), map.get("DeviceType"), map.get("VendorModel"), map.get("Telnet"),
				map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"),
				map.get("Snmpv3Username"),map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), map.get("Snmpv3AuthpasswordNewValue"),
				map.get("Snmpv3PrivpasswordNewValue"),map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"),
				map.get("ExistingSite"),map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"),
				map.get("NewCityName"), map.get("NewCityCode"), map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"),
				map.get("NewSite"), map.get("NewPremise"));
		ExtentTestManager.endTest();
		
		
		
		
		System.out.println("TC-12");
		logger= ExtentTestManager.startTest("VerifyNewDeviceInformation_PE_Speedboat");
		APT_IPASpeedboatHelper.get().verifyViewpage_AddedNewDestributionSwitchDeviceDetails("IPAccessSpeedboat2", map.get("DeviceName"), map.get("VendorModel"), map.get("Telnet"),
				map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), 
				map.get("Snmpv3Username"),map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"),map.get("Snmpv3AuthpasswordNewValue"),
				map.get("Snmpv3PrivpasswordNewValue"),map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"),
				map.get("ExistingSite"),map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), 
				map.get("NewCityName"), map.get("NewCityCode"), map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"),
				map.get("NewSite"), map.get("NewPremise"));
		APT_IPASpeedboatHelper.get().testStatus_PE("IPAccessSpeedboat");
		ExtentTestManager.endTest();
		
		
				System.out.println("TC-13");
				logger= ExtentTestManager.startTest("VerifyAddInterface_DistributionSwitch_JuniperVendor_Speedboat");
				APT_IPASpeedboatHelper.get().VerifyAddInterface_DistributionSwitch_JuniperVendor("IPAccessSpeedboat2", map.get("InterfaceName"), map.get("InterfaceAddressRange_Value")
						, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection")
						, map.get("ExistingAddressIPv4DropdownValue"), map.get("NewAddressRangeIpv4selection")
						, map.get("NewInterfaceAddressRangeIPv4")
						, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
						, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
						, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value"),
				map.get("PhysicalAddress"), map.get("VirtualAddress"),map.get("IPAddressingType"), map.get("Bandwidth_Value"), map.get("Encapsulation_Value"), 
				map.get("BGP_Checkbox"), map.get("FramingType_Value"), map.get("VLANID_Value"), map.get("Juniper_ConfigInterface_checkbox"),
				map.get("STM1NumberValue"), map.get("BearerNumber_Value"), map.get("UnitIDValue"),
				map.get("IVManagement_checkbox"), map.get("AtricaConnected_checkbox"));
				ExtentTestManager.endTest();
				
				
				
				
				System.out.println("TC-14");
				logger= ExtentTestManager.startTest("VerifyAddMultilink_DistributionSwitch_JuniperVendor_Speedboat");
				APT_IPASpeedboatHelper.get().VerifyAddMultilink_DistributionSwitch_JuniperVendor("IPAccessSpeedboat2", map.get("InterfaceName"), map.get("InterfaceAddressRange_Value")
						, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection")
						, map.get("ExistingAddressIPv4DropdownValue"), map.get("NewAddressRangeIpv4selection")
						, map.get("NewInterfaceAddressRangeIPv4")
						, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
						, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
						, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value"),
				map.get("PhysicalAddress"), map.get("VirtualAddress"),
				map.get("BGP_Checkbox"), map.get("Juniper_ConfigInterface_checkbox"),
				map.get("UnitIDValue"),
				map.get("IVManagement_checkbox"), map.get("AtricaConnected_checkbox"));
			ExtentTestManager.endTest();
				
				
				
				
				System.out.println("TC-15");
				logger= ExtentTestManager.startTest("VerifyAddNewPEDeviceFunction_Speedboat");
				APT_IPASpeedboatHelper.get().searchorder("IPAccessSpeedboat2", map.get("ServiceIdentification"));
				APT_IPASpeedboatHelper.get().navigateToAddNewAccessSwitchDevicePage("IPAccessSpeedboat2");
				APT_IPASpeedboatHelper.get().verifyAddNewAccessSwitchDeviceFields("IPAccessSpeedboat2");
				APT_IPASpeedboatHelper.get().verifyAddNewAccessSwitchDeviceFunction("IPAccessSpeedboat2", map.get("ServiceIdentification"),
						map.get("NewAccessDeviceName"),map.get("AS_VendorModel"),map.get("AS_ManagementAddress"),map.get("AS_Snmpro"),
						map.get("AS_Country"),  map.get("AS_City"), map.get("AS_Site"), map.get("AS_Premise")); 
				ExtentTestManager.endTest();

				
				
				System.out.println("TC-16");
				logger= ExtentTestManager.startTest("VerifyNewDeviceInformation_PE_Speedboat");
				APT_IPASpeedboatHelper.get().verifyViewpage_AddedNewAccessSwitchDeviceDetails("IPAccessSpeedboat2", map.get("ServiceIdentification"),
						map.get("NewAccessDeviceName"),map.get("AS_VendorModel"),map.get("AS_ManagementAddress"),map.get("AS_Snmpro"),
						map.get("AS_Country"),  map.get("AS_City"), map.get("AS_Site"), map.get("AS_Premise")); 
				APT_IPASpeedboatHelper.get().testStatus_PE("IPAccessSpeedboat2");
				ExtentTestManager.endTest();
				
				
				
		
			
				
				System.out.println("TC-17");
				logger= ExtentTestManager.startTest("VerifyAddInterface_DistributionSwitch_JuniperVendor_Speedboat");
				APT_IPASpeedboatHelper.get().VerifyAddInterface_AccessSwitch_AristaVendor("IPAccessSpeedboat2", map.get("InterfaceName"), map.get("NewAccessDeviceName"), map.get("InterfaceAddressRange_Value")
						, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection")
						, map.get("ExistingAddressIPv4DropdownValue"), map.get("NewAddressRangeIpv4selection")
						, map.get("NewInterfaceAddressRangeIPv4")
						, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("LinkValue"), map.get("AristaVendor_BearerTypeValue"),
				map.get("PhysicalAddress"), map.get("VirtualAddress"),map.get("IPAddressingType"), map.get("AristaVendor_Bandwidth_Value"), 
				map.get("VLANID_Value"), map.get("Arista_ConfigInterface_checkbox"),
				map.get("Interface_GroupNumber"), map.get("Interface_Speed"), map.get("Interface_Duplex"),map.get("Interface_AccessLeaf"),
				map.get("Interface_InterfacePortType"),map.get("Interface_VRRPIP"), map.get("Interface_VRRPPassword"),map.get("Interface_InterfaceNode"),
				map.get("IVManagement_checkbox"), map.get("AtricaConnected_checkbox"));
				ExtentTestManager.endTest();

				
				
				
		System.out.println("TC-18");
		logger= ExtentTestManager.startTest("VerifyAddMultilink_AccessSwitch_AristaVendor_Speedboat");
		APT_IPASpeedboatHelper.get().VerifyAddMultilink_AccessSwitch_AristaVendor("IPAccessSpeedboat2",  map.get("MultilinkName"),
				 map.get("VendorModel"), map.get("InterfaceAddressRange_Value")
					, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection")
					, map.get("ExistingAddressIPv4DropdownValue"), map.get("NewAddressRangeIpv4selection")
					, map.get("NewInterfaceAddressRangeIPv4")
					, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("LinkValue"),
				map.get("Multilink_Bandwidth"), map.get("Multilink_IPAddressingType"),map.get("Multilink_BGPCheckbox"), 
				 map.get("Multilink_ConfigInterface_checkbox"),map.get("IVManagement_checkbox"), map.get("AtricaConnected_checkbox")
				 ,map.get("Multilink_GroupNumber"),map.get("Multilink_Speed"),map.get("Multilink_Duplex"), map.get("Multilink_VLAN"), map.get("Multilink_AccessLeaf"), map.get("Multilink_InterfacePortType"));
		ExtentTestManager.endTest();
				


		
		
		
		System.out.println("TC-19");
		logger= ExtentTestManager.startTest("deleteFunction_Interfaces_NewlyCreatedDevice_Service_Customer_Speedboat");
		APT_IPASpeedboatHelper.get().searchorder("IPAccessSpeedboat2", map.get("ServiceIdentification"));
		//APT_IPASpeedboatHelper.get().deleteAccessSwitchInterface("IPAccessSpeedboat2", map.get("InterfaceName"));
		APT_IPASpeedboatHelper.get().deleteAccessSwitchDevice("IPAccessSpeedboat2", map.get("NewAccessDeviceName"));
		ExtentTestManager.endTest();
		
		
		
		
		System.out.println("TC-20");
		logger= ExtentTestManager.startTest("Delete Service");
		APT_IPASpeedboatHelper.get().deleteService("IPAccessSpeedboat2");
		ExtentTestManager.endTest();
		
		
		
				
		}
		
		
		
	
}
	