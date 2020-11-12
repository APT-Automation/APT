package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.LogStatus;

public class APT_IPAccessCMSDataCenterTest extends DriverTestcase{
	
public String CustomerName=null;
	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_IPA_CMSDataCenter")
	public void IPAccessVCPEConfigService(Map<String, String> map) throws Exception {
		
		setup();	
		Login.APT_Login_1(map.get("url for the product"));

		
		String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
        
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
              
              logger= ExtentTestManager.startTest("CreateCustomer_CMSDataCentre");
              APT_IPA_CMSHelper.get().createcustomer("ipaCMS", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              ExtentTestManager.endTest();
              
              logger= ExtentTestManager.startTest("selectExistingCustomer_CMSDataCentre"); 
              APT_IPA_CMSHelper.get().selectCustomertocreateOrder("ipaCMS",map.get("newCustomer"));
              ExtentTestManager.endTest();
              
        }
        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              logger= ExtentTestManager.startTest("selectExistingCustomer_CMSDataCentre"); 
              APT_IPA_CMSHelper.get().selectCustomertocreateOrder("ipaCMS",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
              ExtentTestManager.endTest();
        }
        
		logger= ExtentTestManager.startTest("verifyCreateorder_CMSDataCentre");
		APT_IPA_CMSHelper.get().createorderservice("ipaCMS", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyservicetypeselection_CMSDataCentre");
		APT_IPA_CMSHelper.get().verifyselectservicetype("ipaCMS", map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyservicecreation_CMSDataCentre");
		APT_IPA_CMSHelper.get().verifyservicecreation("ipaCMS", map.get("ServiceIdentification"), map.get("Remarks")
				, map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType")
				, map.get("NetworkConfiguration_DrodpwonValue"), map.get("TerminationDate"), map.get("BillingTypevalue")
				, map.get("Email"), map.get("PhoneContact"), map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox")
				, map.get("RouterConfigView_IPv4_Checkbox"), map.get("RouterConfigView_IPv6_Checkbox")
				, map.get("DeliveryChannel_DropdownValue"), map.get("Topology_DropdownValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyCustomerDetailsInformation_CMSDataCentre");
		APT_IPA_CMSHelper.get().verifyCustomerDetailsInformation("ipaCMS", map.get("newCustomerCreation"), map.get("existingCustomerSelection"),map.get("newCustomer"),map.get("existingCustomer"),
				map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));	
		
		APT_IPA_CMSHelper.get().verifyUserDetailsInformation("ipaCMS", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
		ExtentTestManager.endTest();
		
		
//		logger= ExtentTestManager.startTest("verifyUserDetailsInformation_CMSDataCentre");
//		APT_IPA_CMSHelper.get().VerifyUsersPanel("ipaCMS", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//				map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//				map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"), map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"), 
//				map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), 
//				map.get("SiteOrders_ToBeAvailable"), map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
//		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("verifyOrderDetailsInformation_CMSDataCentre");
		APT_IPA_CMSHelper.get().verifyorderpanel_editorder("ipaCMS", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
		APT_IPA_CMSHelper.get().verifyorderpanel_changeorder("ipaCMS", map.get("ChangeOrder_newOrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
				map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
		
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyServicepanelinviewservicepage_CMSDataCentre");
	  	APT_IPA_CMSHelper.get().verifyservicepanelInformationinviewservicepage("ipaCMS", map.get("ServiceIdentification")
	  			, map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"), map.get("Remarks"), map.get("TerminationDate")
	  			, map.get("BillingTypevalue"), map.get("Email"), map.get("PhoneContact"));
	  	ExtentTestManager.endTest();
	  	
		logger= ExtentTestManager.startTest("verifyManagementConfigOptionspanels_CMSDataCentre");
		APT_IPA_CMSHelper.get().verifyManagementConfigpanels("ipaCMS", map.get("PerformanceReporting_Checkbox")
				, map.get("IPGuardian_Checkbox"), map.get("RouterConfigView_IPv4_Checkbox"), map.get("RouterConfigView_IPv6_Checkbox")
				, map.get("DeliveryChannel_DropdownValue"), map.get("Topology_DropdownValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyServicepanelLinks_CMSDataCentre");
		APT_IPA_CMSHelper.get().verifyEditservice("ipaCMS", map.get("ServiceIdentification"), map.get("ServiceType")
				, map.get("NetworkConfiguration_DrodpwonValue"), map.get("EditRemarks"), map.get("Remarks")
				, map.get("Edit_TerminationDate"), map.get("Edit_BillingTypevalue"), map.get("Edit_Email"), map.get("Edit_PhoneContact")
				, map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_IPGuardian_Checkbox")
				, map.get("Edit_RouterConfigView_IPv4_Checkbox"), map.get("Edit_RouterConfigView_IPv6_Checkbox")
				, map.get("Edit_DeliveryChannel_DropdownValue"), map.get("Edit_Topology_DropdownValue"));
		APT_IPA_CMSHelper.get().verifyShowNewInfovistaReport("ipaCMS");
		APT_IPA_CMSHelper.get().verifyManageSubnets("ipaCMS");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("VerifyManageService_CMSDataCentre");
		APT_IPA_CMSHelper.get().verifyManageService("ipaCMS", map.get("ChangeOrder_newOrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		
       String topology = "Null"; 
        if(map.get("Edit_Topology_DropdownValue").equalsIgnoreCase("Null")) {
        	topology = map.get("Topology_DropdownValue");
        }
        else {
        	topology = map.get("Edit_Topology_DropdownValue");
        }
        
        
        
        /**    Distribution Switch     **/
        	
        if((topology.equalsIgnoreCase("2IAR - 2IAS")) || (topology.equalsIgnoreCase("2IAR - 1IAS")) || 
        		(topology.equalsIgnoreCase("2IAR only")) || (topology.equalsIgnoreCase("2SAR - 1AS")) || 
        			(topology.equalsIgnoreCase("2SAR - 2AS")) || (topology.equalsIgnoreCase("2AS")) || 
        				(topology.equalsIgnoreCase("1AS"))) {
        	
        	
    		logger= ExtentTestManager.startTest("verifyAddViewExistingDistributionSwitchDevice_CMSDataCentre");
    		APT_IPA_CMSHelper.get().searchorder("CMSDataCentre", map.get("ServiceIdentification"));
    		APT_IPA_CMSHelper.get().addExistingDistributionSwitchDevice_PE("CMSDataCentre",map.get("Topology_DropdownValue"), map.get("ExistingDestributionSwitchDeviceName"));
    		APT_IPA_CMSHelper.get().verifyExistingDistributionSwitchDeviceInfo_PE("CMSDataCentre",map.get("Topology_DropdownValue"), map.get("ExistingDestributionSwitchDeviceName"));
    		APT_IPA_CMSHelper.get().testStatus_PE("CMSDataCentre");
    		ExtentTestManager.endTest();
    
    		
    		logger= ExtentTestManager.startTest("verifyRouterToolFunction_PE_DistributionSwitch_CMSDataCentre");
    		APT_IPA_CMSHelper.get().routerPanel_PE("CMSDataCentre", map.get("PE_CommandIPV4"),
    				map.get("PE_CommandIPV6"),map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
    		ExtentTestManager.endTest();
    		
    					
    		logger= ExtentTestManager.startTest("verifyAddEditDeleteRoutesFunction_PE_DistributionSwitch_CMSDataCentre");
    		APT_IPA_CMSHelper.get().addRouteFunction_PE("CMSDataCentre", map.get("ServiceIdentification"),map.get("ExistingDestributionSwitchDeviceName"), 	map.get("PE_RouteCity"),
    		map.get("PE_RouteSubnetSize"), map.get("PE_Gateway"), map.get("PE_NetworkAddress"), map.get("PE_NetworkMAS"),	map.get("PE_Metrics"));
    		APT_IPA_CMSHelper.get().editRouteFunction_PE("CMSDataCentre", map.get("ServiceIdentification"),map.get("ExistingDestributionSwitchDeviceName"),map.get("PE_RouteCityEdit"),
    		map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
    		APT_IPA_CMSHelper.get().deleteRouteFunction_PE("CMSDataCentre", map.get("ServiceIdentification"),map.get("ExistingDestributionSwitchDeviceName"),map.get("PE_RouteCityEdit"),
    		map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
    		ExtentTestManager.endTest();
    		
    		
    		logger= ExtentTestManager.startTest("verifyEditCPEDeviceFunction_CMSDataCentre");
    		APT_IPA_CMSHelper.get().verifyEditDeviceFunction_DS("CMSDataCentre", map.get("ServiceIdentification"),
    				map.get("DS_DeviceNameEdit"),map.get("DS_VendorModelEdit"),map.get("DS_ManagementAddressEdit"),map.get("DS_CountryEdit"),
    				map.get("DS_CityEdit"),  map.get("DS_SiteEdit"), map.get("DS_PremiseEdit")); 
    		ExtentTestManager.endTest();
    		
    		
    		logger= ExtentTestManager.startTest("VerifyFetchDeviceInterfaceFunction_PE_CMSDataCentre");
    		String deviceName_cmsData = "Null";
    		if(map.get("DS_DeviceNameEdit").equalsIgnoreCase("Null")) {
    			deviceName_cmsData = map.get("ExistingDestributionSwitchDeviceName");
    		}else {
    			deviceName_cmsData = map.get("DS_DeviceNameEdit");
    		}
    		APT_IPA_CMSHelper.get().searchorder("CMSDataCentre", map.get("ServiceIdentification"));//ServiceIdentification
    		APT_IPA_CMSHelper.get().navigateToViewDevicePageDistributionSwitch("CMSDataCentre", map.get("ExistingDestributionSwitchDeviceName"));
    		APT_IPA_CMSHelper.get().verifyFetchDeviceInterface_DS("CMSDataCentre", deviceName_cmsData , map.get("InServiceStatus"), map.get("InMaintenanceStatus"), 
    				map.get("DS_VendorModelEdit"), map.get("DS_ManagementAddressEdit") , map.get("DS_SiteEdit"), map.get("DS_CountryEdit"), map.get("InterfaceName"));
    		ExtentTestManager.endTest();
    		
    		
    		logger= ExtentTestManager.startTest("DeleteDistributionSwitchDeviceFunction_PE_CMSDataCentre");
    		APT_IPA_CMSHelper.get().searchorder("CMSDataCentre", map.get("ServiceIdentification"));//ServiceIdentification
    		APT_IPA_CMSHelper.get().deleteDistributionSwitchDevice("CMSDataCentre", map.get("ExistingDestributionSwitchDeviceName"));
    		APT_IPA_CMSHelper.get().verifysuccessmessage("CMSDataCentre", "Device successfully removed from service.");
    		ExtentTestManager.endTest();
           
    			
    			logger= ExtentTestManager.startTest("AddNewPEDeviceFunction_CMSDataCentre");
    			APT_IPA_CMSHelper.get().searchorder("CMSDataCentre", map.get("ServiceIdentification"));
    			APT_IPA_CMSHelper.get().navigateToAddNewDestributionSwitchDevicePage("CMSDataCentre");
    			APT_IPA_CMSHelper.get().verifyAddNewDestributionSwitchDeviceFields("CMSDataCentre");
    			APT_IPA_CMSHelper.get().verifyAddNewDestributionSwitchDevice("CMSDataCentre", map.get("newDeviceName_distributionSWitch"), map.get("DeviceType"), map.get("DS_VendorModel"), map.get("Telnet"),
    					map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"),
    					map.get("Snmpv3Username"),map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), map.get("Snmpv3AuthpasswordNewValue"),
    					map.get("Snmpv3PrivpasswordNewValue"),map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"),
    					map.get("ExistingSite"),map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"),
    					map.get("NewCityName"), map.get("NewCityCode"), map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"),
    					map.get("NewSite"), map.get("NewPremise"));
    			ExtentTestManager.endTest();
    			
    			
    			logger= ExtentTestManager.startTest("VerifyNewDeviceInformationAdded_PE_CMSDataCentre");
    			APT_IPA_CMSHelper.get().verifyViewpage_AddedNewDestributionSwitchDeviceDetails("CMSDataCentre", map.get("newDeviceName_distributionSWitch"), map.get("DS_VendorModel"), map.get("Telnet"),
    					map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), 
    					map.get("Snmpv3Username"),map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"),map.get("Snmpv3AuthpasswordNewValue"),
    					map.get("Snmpv3PrivpasswordNewValue"),map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"),
    					map.get("ExistingSite"),map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), 
    					map.get("NewCityName"), map.get("NewCityCode"), map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"),
    					map.get("NewSite"), map.get("NewPremise"));
    			APT_IPA_CMSHelper.get().testStatus_PE("CMSDataCentre");
    			ExtentTestManager.endTest();
    			
    					logger= ExtentTestManager.startTest("AddInterface_Cisco_DistributionSwitch_CMSDataCentre");
    					APT_IPA_CMSHelper.get().clickOnAddInterfaceLink_CMSdata("IPACCESS");
    					APT_IPA_CMSHelper.get().addInterface_Cisco_CMSdata("IPACCESS", map.get("addInterface_CMS_interfaceValue"), map.get("adInterface_CMS_existingAddressIPv4Selection"), map.get("adInterface_CMS_existingAddressIPv4DropdownValue"),
    							map.get("adInterface_CMSnewAddressIpv4Selection"), map.get("adInterface_CMS_newAddressIpv4Range"),
    							map.get("adInterface_CMS_subnetSizeValue_IPv4"), map.get("adInterface_CMS_cityValue_IPv4"), map.get("adInterface_CMS_existingAddressRangeIPv6selection"),
    							map.get("adInterface_CMS_existingAddressIPv6DropdownValue"), map.get("adInterface_CMS_newAddressRangeIpv6selection"), map.get("adInterface_CMS_newAddressrangeIPv6"), 
    							map.get("adInterface_CMS_subnetSizeValue_IPv6"), map.get("adInterface_CMS_availableBlocksValue_IPv6"), map.get("adInterface_CMS_configureInterfaceOndevice"), 
    							map.get("adInterface_CMS_networKdropdown"), map.get("adInterface_CMS_link"), map.get("adInterface_CMS_bearerType"), map.get("adInterface_CMS_bandwidth"),
    							map.get("adInterface_CMS_Vlan"), map.get("adInterface_CMS_speed"), map.get("adInterface_CMS_duplex"), map.get("adInterface_CMS_ivmanagement"), map.get("adInterface_CMS_vrrpIP"),
    							map.get("adInterface_CMS_vrrpGroupNumber"), map.get("adInterface_vrrpTrackNumber"), map.get("adInterface_CMS_vrrpPassword"), map.get("adInterface_CMS_vrrpIPV6"),
    							map.get("adInterface_CMS_interfaceNode"));
    					APT_IPA_CMSHelper.get().verifysuccessmessage("IPACCESS", "Interface successfully created.");
    					ExtentTestManager.endTest();
    					
    					
    					logger= ExtentTestManager.startTest("AddMultilink_Cisco_distributionSwitch__CMSDataCentre");
    					APT_IPA_CMSHelper.get().clickOnBreadCrumb("IPACCESS", map.get("newDeviceName_distributionSWitch"));
    					APT_IPA_CMSHelper.get().clickOnAddMultilink_CMSdata("IPACCESS");
    					APT_IPA_CMSHelper.get().addMultilink_cisco_CMS("IPACCESS", map.get("addMultilink_CMS_MultilinkValue"), map.get("addMultilink_CMS_existingAddressIPv4Selection"), map.get("addMultilink_CMS_existingAddressIPv4DropdownValue"),
    							map.get("addMultilink_CMS_newAddressIpv4Selection"), map.get("addMultilink_CMS_newAddressIpv4Range"), map.get("addMultilink_CMS_subnetSizeValue_IPv4"), map.get("addMultilink_CMS_cityValue_IPv4"),
    							map.get("addMultilink_CMS_existingAddressRangeIPv6selection"), map.get("addMultilink_CMS_existingAddressIPv6DropdownValue"), map.get("addMultilink_CMS_newAddressRangeIpv6selection"),
    							map.get("addMultilink_CMS_newAddressrangeIPv6"), map.get("addMultilink_CMS_subnetSizeValue_IPv6"), map.get("addMultilink_CMS_availableBlocksValue_IPv6"), map.get("addMultilink_CMS_configureInterfaceOndevice"),
    							map.get("addMultilink_CMS_networKdropdown"), map.get("addMultilink_CMS_bandwidth"), map.get("addMultilink_CMS_Vlan"), map.get("addMultilink_CMS_speed"), map.get("addMultilink_CMS_duplex"), map.get("addMultilink_CMS_ivmanagement"));
    					APT_IPA_CMSHelper.get().verifysuccessmessage("IPACCESS", "Multilink interface successfully created.");
    					ExtentTestManager.endTest();
    					
    					
    					logger= ExtentTestManager.startTest("EditInterface_Cisco_distributionSwitch__CMSDataCentre");
    					APT_IPA_CMSHelper.get().clickOnBreadCrump("IPACCESS", map.get("ServiceIdentification"));
    					APT_IPA_CMSHelper.get().clickOnEditInterface("IPACCESS", map.get("addInterface_CMS_interfaceValue"));
    					APT_IPA_CMSHelper.get().editInterface_Cisco_CMSdata("IPACCESS", map.get("editInterface_CMS_interfaceValue"), map.get("editInterface_CMS_existingAddressIPv4Selection"), map.get("editInterface_CMS_existingAddressIPv4DropdownValue"),
    							map.get("editInterface_CMSnewAddressIpv4Selection"), map.get("editInterface_CMS_newAddressIpv4Range"),
    							map.get("editInterface_CMS_subnetSizeValue_IPv4"), map.get("editInterface_CMS_cityValue_IPv4"), map.get("editInterface_CMS_existingAddressRangeIPv6selection"),
    							map.get("editInterface_CMS_existingAddressIPv6DropdownValue"), map.get("editInterface_CMS_newAddressRangeIpv6selection"), map.get("editInterface_CMS_newAddressrangeIPv6"), 
    							map.get("editInterface_CMS_subnetSizeValue_IPv6"), map.get("editInterface_CMS_availableBlocksValue_IPv6"), map.get("editInterface_CMS_configureInterfaceOndevice"), 
    							map.get("editInterface_CMS_networKdropdown"), map.get("editInterface_CMS_link"), map.get("editInterface_CMS_bearerType"), map.get("editInterface_CMS_bandwidth"),
    							map.get("editInterface_CMS_Vlan"), map.get("editInterface_CMS_speed"), map.get("editInterface_CMS_duplex"), map.get("editInterface_CMS_ivmanagement"), map.get("editInterface_CMS_vrrpIP"),
    							map.get("editInterface_CMS_vrrpGroupNumber"), map.get("editInterface_vrrpTrackNumber"), map.get("editInterface_CMS_vrrpPassword"), map.get("editInterface_CMS_vrrpIPV6"),
    							map.get("editInterface_CMS_interfaceNode"));
    					ExtentTestManager.endTest();
    					
    					
    					logger= ExtentTestManager.startTest("EditMultilink_Cisco_distributionSwitch__CMSDataCentre");
    					String MultilinkValue = "Multilink" + map.get("addMultilink_CMS_MultilinkValue");
    					APT_IPA_CMSHelper.get().clickOnEditMultilink("IPACCESS", MultilinkValue);
    					APT_IPA_CMSHelper.get().editMultilink_cisco_CMS("IPACCESS", map.get("editMultilink_CMS_MultilinkValue"), map.get("editMultilink_CMS_existingAddressIPv4Selection"), map.get("editMultilink_CMS_existingAddressIPv4DropdownValue"),
    							map.get("editMultilink_CMS_newAddressIpv4Selection"), map.get("editMultilink_CMS_newAddressIpv4Range"), map.get("editMultilink_CMS_subnetSizeValue_IPv4"), map.get("editMultilink_CMS_cityValue_IPv4"),
    							map.get("editMultilink_CMS_existingAddressRangeIPv6selection"), map.get("editMultilink_CMS_existingAddressIPv6DropdownValue"), map.get("editMultilink_CMS_newAddressRangeIpv6selection"),
    							map.get("editMultilink_CMS_newAddressrangeIPv6"), map.get("editMultilink_CMS_subnetSizeValue_IPv6"), map.get("editMultilink_CMS_availableBlocksValue_IPv6"), map.get("editMultilink_CMS_configureInterfaceOndevice"),
    							map.get("editMultilink_CMS_networKdropdown"), map.get("editMultilink_CMS_bandwidth"), map.get("editMultilink_CMS_Vlan"), map.get("editMultilink_CMS_speed"), map.get("editMultilink_CMS_duplex"), map.get("editMultilink_CMS_ivmanagement"));
    					APT_IPA_CMSHelper.get().verifysuccessmessage("IPACCESS", "Multilink interface successfully updated.");
    					ExtentTestManager.endTest();
    					
    					logger= ExtentTestManager.startTest("DeleteInterface_distributionSwitch__CMSDataCentre");
    					APT_IPA_CMSHelper.get().clickOnBreadCrump("IPACCESS", map.get("ServiceIdentification"));
    					APT_IPA_CMSHelper.get().deleteInterface_Cisco_CMS("IPACCESS", map.get("addInterface_CMS_interfaceValue"), map.get("editInterface_CMS_interfaceValue"));
    					ExtentTestManager.endTest();
    					
    					logger= ExtentTestManager.startTest("DeleteDistributionSwitchForNewDevice_CMSDataCentre");
    					APT_IPA_CMSHelper.get().clickOnBreadCrump("IPACCESS", map.get("ServiceIdentification"));
    					APT_IPA_CMSHelper.get().deleteDistributionSwitchDevice("CMSDataCentre", map.get("newDeviceName_distributionSWitch"));
    					APT_IPA_CMSHelper.get().verifysuccessmessage("CMSDataCentre", "Device successfully removed from service.");
    					ExtentTestManager.endTest();
    	
        }else {
        	Log.info("Distribution Switch will not dipslay for the topology '" + topology + "'");
        }
        
        
        
        /**
         * Access Switch
         */
        
        if((topology.equalsIgnoreCase("2IAR - 2IAS")) || (topology.equalsIgnoreCase("2IAR - 1IAS")) || 
        		(topology.equalsIgnoreCase("2SAR - 1AS")) || (topology.equalsIgnoreCase("2SAR - 2AS")) ||
        			 (topology.equalsIgnoreCase("2AS")) || (topology.equalsIgnoreCase("1AS"))) {
        			
        	
    		if((map.get("ExistingAccessSwitchSelection").equalsIgnoreCase("Yes")) && (map.get("newAccessSwitchSElection").equalsIgnoreCase("No"))) {
    			logger= ExtentTestManager.startTest("VerifyAddViewExistingAccessSwitchDevice_CMSDataCentre");
        		APT_IPA_CMSHelper.get().searchorder("CMSDataCentre", map.get("ServiceIdentification"));
        		APT_IPA_CMSHelper.get().addExistingAccessSwitchDevice("CMSDataCentre",map.get("Topology_DropdownValue"), 
        	map.get("ExistingAccessSwitchDeviceName"));
        		APT_IPA_CMSHelper.get().verifyExistingAccessSwitchDeviceInfo_PE("CMSDataCentre",map.get("Topology_DropdownValue"), map.get("ExistingAccessSwitchDeviceName"));
        		APT_IPA_CMSHelper.get().testStatus_PE("CMSDataCentre");
        		ExtentTestManager.endTest();
        		
        		logger= ExtentTestManager.startTest("verifyAddEditDeleteRoutesFunction_AccessSwitch_CMSDataCentre");
        		APT_IPA_CMSHelper.get().addRouteFunction_AS("CMSDataCentre", map.get("ServiceIdentification"),map.get("ExistingAccessSwitchDeviceName"), 	map.get("PE_RouteCity"),
        				map.get("PE_RouteSubnetSize"), map.get("PE_Gateway"), map.get("PE_NetworkAddress"), map.get("PE_NetworkMAS"),	map.get("PE_Metrics"));
        		APT_IPA_CMSHelper.get().editRouteFunction_AS("CMSDataCentre", map.get("ServiceIdentification"),map.get("ExistingAccessSwitchDeviceName"),map.get("PE_RouteCityEdit"),
        				map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
        		APT_IPA_CMSHelper.get().deleteRouteFunction_AS("CMSDataCentre", map.get("ServiceIdentification"),map.get("ExistingAccessSwitchDeviceName"),map.get("PE_RouteCityEdit"),
        					map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
        		ExtentTestManager.endTest();
    		}
    		
    		else if((map.get("ExistingAccessSwitchSelection").equalsIgnoreCase("No")) && (map.get("newAccessSwitchSElection").equalsIgnoreCase("Yes"))) {
    			
    			logger= ExtentTestManager.startTest("AddNewAccessSwitchDevice_CMSDataCentre");
        		APT_IPA_CMSHelper.get().searchorder("CMSDataCentre", map.get("ServiceIdentification"));
    			APT_IPA_CMSHelper.get().navigateToAddNewAccessSwitchDevicePage("CMSDataCentre");
    			APT_IPA_CMSHelper.get().verifyAddNewAccessSwitchDeviceFields("CMSDataCentre");
    			APT_IPA_CMSHelper.get().verifyAddNewAccessSwitchDeviceFunction("CMSDataCentre", map.get("ServiceIdentification"),
    					map.get("NewAccessDeviceName"),map.get("AS_VendorModel"),map.get("AS_ManagementAddress"),map.get("AS_Snmpro"),
    					map.get("AS_Country"),  map.get("AS_City"), map.get("AS_Site"), map.get("AS_Premise")); 
    			ExtentTestManager.endTest();
    			
    			logger= ExtentTestManager.startTest("VerifyNewAddedDeviceInformation_CMSDataCentre");
    			APT_IPA_CMSHelper.get().verifyViewpage_AddedNewAccessSwitchDeviceDetails("CMSDataCentre", map.get("ServiceIdentification"),
    					map.get("NewAccessDeviceName"),map.get("AS_VendorModel"),map.get("AS_ManagementAddress"),map.get("AS_Snmpro"),
    					map.get("AS_Country"),  map.get("AS_City"), map.get("AS_Site"), map.get("AS_Premise")); 
    			APT_IPA_CMSHelper.get().testStatus_PE("CMSDataCentre");
    			ExtentTestManager.endTest();
    			
        		logger= ExtentTestManager.startTest("verifyAddEditDeleteRoutesFunction_AccessSwitch_CMSDataCentre");
        		APT_IPA_CMSHelper.get().addRouteFunction_AS("CMSDataCentre", map.get("ServiceIdentification"),map.get("NewAccessDeviceName"), 	map.get("PE_RouteCity"),
        				map.get("PE_RouteSubnetSize"), map.get("PE_Gateway"), map.get("PE_NetworkAddress"), map.get("PE_NetworkMAS"),	map.get("PE_Metrics"));
        		APT_IPA_CMSHelper.get().editRouteFunction_AS("CMSDataCentre", map.get("ServiceIdentification"),map.get("NewAccessDeviceName"),map.get("PE_RouteCityEdit"),
        				map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
        		APT_IPA_CMSHelper.get().deleteRouteFunction_AS("CMSDataCentre", map.get("ServiceIdentification"),map.get("NewAccessDeviceName"),map.get("PE_RouteCityEdit"),
        					map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
        		ExtentTestManager.endTest();
    		}
    		
    		
    		logger= ExtentTestManager.startTest("verifyRouterToolFunction_PE_AccessSwitch_CMSDataCentre");
    		APT_IPA_CMSHelper.get().routerPanel_PE("CMSDataCentre", map.get("PE_CommandIPV4"),
    					map.get("PE_CommandIPV6"),map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
    		ExtentTestManager.endTest();
    										
    		
    		logger= ExtentTestManager.startTest("AddInterface_AccessSwitch_Cisco_CMSDataCentre");
    		APT_IPA_CMSHelper.get().clickOnAddInterfaceLink_CMSdata("IPACCESS");
    		APT_IPA_CMSHelper.get().addInterface_Cisco_CMSdata_accessSwitch("IPACCESS", map.get("Accesswitch_addInterface_interfaceValue"), map.get("Accesswitch_addInterface_existingAddressIPv4Selection"), map.get("Accesswitch_addInterface_existingAddressIPv4DropdownValue"),
    				map.get("Accesswitch_addInterfacenewAddressIpv4Selection"), map.get("Accesswitch_addInterface_newAddressIpv4Range"),
    				map.get("Accesswitch_addInterface_subnetSizeValue_IPv4"), map.get("Accesswitch_addInterface_cityValue_IPv4"), map.get("Accesswitch_addInterface_existingAddressRangeIPv6selection"),
    				map.get("Accesswitch_addInterface_existingAddressIPv6DropdownValue"), map.get("Accesswitch_addInterface_newAddressRangeIpv6selection"), map.get("Accesswitch_addInterface_newAddressrangeIPv6"), 
    				map.get("Accesswitch_addInterface_subnetSizeValue_IPv6"), map.get("Accesswitch_addInterface_availableBlocksValue_IPv6"), map.get("Accesswitch_addInterface_configureInterfaceOndevice"), 
    				map.get("Accesswitch_addInterface_networKdropdown"), map.get("Accesswitch_addInterface_link"), map.get("Accesswitch_addInterface_bearerType"), map.get("Accesswitch_addInterface_bandwidth"),
    				map.get("Accesswitch_addInterface_Vlan"), map.get("Accesswitch_addInterface_speed"), map.get("Accesswitch_addInterface_duplex"), map.get("Accesswitch_addInterface_ivmanagement"), map.get("Accesswitch_addInterface_vrrpIP"),
    				map.get("Accesswitch_addInterface_vrrpGroupNumber"), map.get("adInterface_vrrpTrackNumber"), map.get("Accesswitch_addInterface_vrrpPassword"), map.get("Accesswitch_addInterface_vrrpIPV6"),
    				map.get("Accesswitch_addInterface_interfaceNode"));
    		APT_IPA_CMSHelper.get().verifysuccessmessage("IPACCESS", "Interface successfully created.");
    		ExtentTestManager.endTest();
    		
    		
    		logger= ExtentTestManager.startTest("AddMultilink_AccessSwitch_Cisco_CMSDataCentre");
    		APT_IPA_CMSHelper.get().clickOnAddMultilink_CMSdata("IPACCESS");
    		APT_IPA_CMSHelper.get().addMultilink_cisco_CMS_AccessSwitch("IPACCESS", map.get("Accesswitch_addMultilink_MultilinkValue"), map.get("Accesswitch_addMultilink_existingAddressIPv4Selection"), map.get("Accesswitch_addMultilink_existingAddressIPv4DropdownValue"),
    				map.get("Accesswitch_addMultilink_newAddressIpv4Selection"), map.get("Accesswitch_addMultilink_newAddressIpv4Range"), map.get("Accesswitch_addMultilink_subnetSizeValue_IPv4"), map.get("Accesswitch_addMultilink_cityValue_IPv4"),
    				map.get("Accesswitch_addMultilink_existingAddressRangeIPv6selection"), map.get("Accesswitch_addMultilink_existingAddressIPv6DropdownValue"), map.get("Accesswitch_addMultilink_newAddressRangeIpv6selection"),
    				map.get("Accesswitch_addMultilink_newAddressrangeIPv6"), map.get("Accesswitch_addMultilink_subnetSizeValue_IPv6"), map.get("Accesswitch_addMultilink_availableBlocksValue_IPv6"), map.get("Accesswitch_addMultilink_configureInterfaceOndevice"),
    				map.get("Accesswitch_addMultilink_networKdropdown"), map.get("Accesswitch_addMultilink_bandwidth"), map.get("Accesswitch_addMultilink_Vlan"), map.get("Accesswitch_addMultilink_speed"), map.get("Accesswitch_addMultilink_duplex"), map.get("Accesswitch_addMultilink_ivmanagement"));
    		APT_IPA_CMSHelper.get().verifysuccessmessage("IPACCESS", "Multilink interface successfully created.");
    		ExtentTestManager.endTest();
    		
    		
    		logger= ExtentTestManager.startTest("EditInterface_AccessSwitch_CMSDataCentre");
    		APT_IPA_CMSHelper.get().clickOnBreadCrump("IPACCESS", map.get("ServiceIdentification"));
    		APT_IPA_CMSHelper.get().clickOnEditInterface_accessSwitch("IPACCESS", map.get("Accesswitch_addInterface_interfaceValue"));
    		APT_IPA_CMSHelper.get().editInterface_Cisco_CMSdata("IPACCESS", map.get("Accesswitch_editInterface_interfaceValue"), map.get("Accesswitch_editInterface_existingAddressIPv4Selection"), map.get("Accesswitch_editInterface_existingAddressIPv4DropdownValue"),
    				map.get("Accesswitch_editInterfacenewAddressIpv4Selection"), map.get("Accesswitch_editInterface_newAddressIpv4Range"),
    				map.get("Accesswitch_editInterface_subnetSizeValue_IPv4"), map.get("Accesswitch_editInterface_cityValue_IPv4"), map.get("Accesswitch_editInterface_existingAddressRangeIPv6selection"),
    				map.get("Accesswitch_editInterface_existingAddressIPv6DropdownValue"), map.get("Accesswitch_editInterface_newAddressRangeIpv6selection"), map.get("Accesswitch_editInterface_newAddressrangeIPv6"), 
    				map.get("Accesswitch_editInterface_subnetSizeValue_IPv6"), map.get("Accesswitch_editInterface_availableBlocksValue_IPv6"), map.get("Accesswitch_editInterface_configureInterfaceOndevice"), 
    				map.get("Accesswitch_editInterface_networKdropdown"), map.get("Accesswitch_editInterface_link"), map.get("Accesswitch_editInterface_bearerType"), map.get("Accesswitch_editInterface_bandwidth"),
    				map.get("Accesswitch_editInterface_Vlan"), map.get("Accesswitch_editInterface_speed"), map.get("Accesswitch_editInterface_duplex"), map.get("Accesswitch_editInterface_ivmanagement"), map.get("Accesswitch_editInterface_vrrpIP"),
    				map.get("Accesswitch_editInterface_vrrpGroupNumber"), map.get("editInterface_vrrpTrackNumber"), map.get("Accesswitch_editInterface_vrrpPassword"), map.get("Accesswitch_editInterface_vrrpIPV6"),
    				map.get("Accesswitch_editInterface_interfaceNode"));
    		APT_IPA_CMSHelper.get().verifysuccessmessage("IPACCESS", "Interface successfully updated.");
    		ExtentTestManager.endTest();
    		
    		
    		logger= ExtentTestManager.startTest("EditMultilink_Cisco_AccessSwitch_CMSDataCentre");
    		String MultilinkValue = "Multilink" + map.get("Accesswitch_addMultilink_MultilinkValue");
    		APT_IPA_CMSHelper.get().clickOnEditMultilink_AccessSWitch("IPACCESS", MultilinkValue);
    		APT_IPA_CMSHelper.get().editMultilink_cisco_CMS("IPACCESS", map.get("Accesswitch_editMultilink_MultilinkValue"), map.get("Accesswitch_editMultilink_existingAddressIPv4Selection"), map.get("Accesswitch_editMultilink_existingAddressIPv4DropdownValue"),
    				map.get("Accesswitch_editMultilink_newAddressIpv4Selection"), map.get("Accesswitch_editMultilink_newAddressIpv4Range"), map.get("Accesswitch_editMultilink_subnetSizeValue_IPv4"), map.get("Accesswitch_editMultilink_cityValue_IPv4"),
    				map.get("Accesswitch_editMultilink_existingAddressRangeIPv6selection"), map.get("Accesswitch_editMultilink_existingAddressIPv6DropdownValue"), map.get("Accesswitch_editMultilink_newAddressRangeIpv6selection"),
    				map.get("Accesswitch_editMultilink_newAddressrangeIPv6"), map.get("Accesswitch_editMultilink_subnetSizeValue_IPv6"), map.get("Accesswitch_editMultilink_availableBlocksValue_IPv6"), map.get("Accesswitch_editMultilink_configureInterfaceOndevice"),
    				map.get("Accesswitch_editMultilink_networKdropdown"), map.get("Accesswitch_editMultilink_bandwidth"), map.get("Accesswitch_editMultilink_Vlan"), map.get("Accesswitch_editMultilink_speed"), map.get("Accesswitch_editMultilink_duplex"), map.get("Accesswitch_editMultilink_ivmanagement"));
    		APT_IPA_CMSHelper.get().verifysuccessmessage("IPACCESS", "Multilink interface successfully updated.");
    		ExtentTestManager.endTest();
    		
    		
    		logger= ExtentTestManager.startTest("DeleteInterface_accessSwitch_CMSDataCentre");
    		APT_IPA_CMSHelper.get().clickOnBreadCrump("IPACCESS", map.get("ServiceIdentification"));
    		APT_IPA_CMSHelper.get().deleteInterface_AccessSwitch_CMS("IPACCESS", map.get("Accesswitch_addInterface_interfaceValue"),
    				map.get("Accesswitch_editInterface_interfaceValue"));
    		ExtentTestManager.endTest();
    		
    		logger= ExtentTestManager.startTest("DeleteMultilink_accessSwitch_CMSDataCentre");
    		APT_IPA_CMSHelper.get().deleteInterface_AccessSwitch_CMS("IPACCESS", map.get("Accesswitch_addInterface_interfaceValue"),
    				map.get("Accesswitch_editInterface_interfaceValue"));
    		ExtentTestManager.endTest();
    		
        	
    		if((map.get("ExistingAccessSwitchSelection").equalsIgnoreCase("Yes")) && (map.get("newAccessSwitchSElection").equalsIgnoreCase("No"))) {
    			
    			logger= ExtentTestManager.startTest("deleteAccessSwitch_existingDevice_CMSDataCentre");
        		APT_IPA_CMSHelper.get().deleteAccessSwitchDevice("CMSDataCentre", map.get("ExistingAccessSwitchDeviceName"));
        		ExtentTestManager.endTest();
        		
    		}
    		else if((map.get("ExistingAccessSwitchSelection").equalsIgnoreCase("No")) && (map.get("newAccessSwitchSElection").equalsIgnoreCase("Yes"))) {
    			
    			logger= ExtentTestManager.startTest("deleteAccessSwitch_newDevice_CMSDataCentre");
        		APT_IPA_CMSHelper.get().deleteAccessSwitchDevice("CMSDataCentre", map.get("NewAccessDeviceName"));
        		ExtentTestManager.endTest();
    			
    		}
    		
        	
        }else {
        	ExtentTestManager.startTest("AccessSwitch_CMSDataCentre");
        	Log.info("Access SWitch will not dipslay for the topology '" + topology + "'");
        	ExtentTestManager.getTest().log(LogStatus.INFO, "Access SWitch will not dipslay for the topology '" + topology + "'");
        	ExtentTestManager.endTest();
        }
		
		
		logger= ExtentTestManager.startTest("Delete Service_CMSDataCentre");
		APT_IPA_CMSHelper.get().searchorder("CMSDataCentre", map.get("ServiceIdentification"));
		APT_IPA_CMSHelper.get().deleteService("ipaCMS");
		ExtentTestManager.endTest();
		
		
}
	
}
	