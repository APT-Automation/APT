package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.LogStatus;

public class APT_IPAccessConfigTest extends DriverTestcase{
	
public String CustomerName=null;
public static String DeviceName=null;	
public static String VendorModel=null;
public String ServicenameTag="- IPAccess Configuration";

	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_IPAccessConfig")
	public void ipa_ConfigurationService(Map<String, String> map) throws Exception {
		
		setup();
		
		Login.APT_Login_1(map.get("url for the Product"));
		
        String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
      
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
              
              logger= ExtentTestManager.startTest("CreateCustomer");
              APT_IPAccessConfigHelper.get().createcustomer("ipa_config", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              ExtentTestManager.endTest();
              
              logger= ExtentTestManager.startTest("selectExistingCustomer"); 
              APT_IPAccessConfigHelper.get().selectCustomertocreateOrder("ipa_config",map.get("newCustomer"));
              ExtentTestManager.endTest();
        }
        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              logger= ExtentTestManager.startTest("selectExistingCustomer"); 
              APT_IPAccessConfigHelper.get().selectCustomertocreateOrder("ipa_config",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
              ExtentTestManager.endTest();
        }
        
		logger= ExtentTestManager.startTest("verifyCreateorder");
		APT_IPAccessConfigHelper.get().createorderservice("ipa_config", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyservicetypeselection");
		APT_IPAccessConfigHelper.get().verifyselectservicetype("ipa_config", map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyservicecreation");
		APT_IPAccessConfigHelper.get().verifyservicecreation("ipa_config", map.get("ServiceIdentification"), map.get("Remarks")
				, map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue")
				, map.get("TerminationDate"), map.get("BillingTypevalue"), map.get("Email"), map.get("PhoneContact")
				, map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox"), map.get("ServiceBW_Value")
				, map.get("Package_DropdownValue"), map.get("SnmpNotification_Checkbox"), map.get("RouterConfigView_IPv4_Checkbox")
				, map.get("RouterConfigView_IPv6_Checkbox"), map.get("DeliveryChannel_DropdownValue")
				, map.get("RouterbasedFirewall_Checkbox"), map.get("QOS_Checkbox"), map.get("ManagedService_Checkbox")
				, map.get("NoOfCircuits_DropdownValue"), map.get("OrderType_DropdownValue"), map.get("SpeedySurf_CheckboxValue")
				, map.get("CPEWANTechnology_DropdownValue"), map.get("PEWANTechnology_DropdownValue")
				, map.get("DSLProvider_DropdownValue"), map.get("PPPBased_CheckboxValue"), map.get("VOIP_CheckboxValue")
				, map.get("Downstream_DropdownValue"), map.get("BGPCheck_Checkbox"), map.get("ManInMiddle_Checkbox")
				, map.get("PA_Checkbox"), map.get("PI_Checkbox"), map.get("TrapTargetAddress_Value"), map.get("ActelisBased_Checkbox"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyCustomerDetailsInformation");
		APT_IPAccessConfigHelper.get().verifyCustomerDetailsInformation("ipa_config", map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		APT_IPAccessConfigHelper.get().verifyUserDetailsInformation("ipa_config", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
		ExtentTestManager.endTest();
		
//		logger= ExtentTestManager.startTest("verifyUserDetailsInformation");
//		APT_IPAccessConfigHelper.get().VerifyUsersPanel("ipa_config", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//				map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//				map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"), map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"), 
//				map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), 
//				map.get("SiteOrders_ToBeAvailable"), map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
//		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyOrderDetailsInformation");
		APT_IPAccessConfigHelper.get().verifyorderpanel_editorder("ipa_config", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
		APT_IPAccessConfigHelper.get().verifyorderpanel_changeorder("ipa_config", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyServicepanelinviewservicepage");
	  	APT_IPAccessConfigHelper.get().verifyservicepanelInformationinviewservicepage("ipa_config", map.get("ServiceIdentification")
	  			, map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"), map.get("Remarks")
	  			, map.get("TerminationDate"), map.get("BillingTypevalue"), map.get("Email"), map.get("PhoneContact")
	  			, map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox"), map.get("ServiceBW_Value")
				, map.get("Package_DropdownValue"), map.get("NoOfCircuits_DropdownValue"), map.get("OrderType_DropdownValue")
				, map.get("SpeedySurf_CheckboxValue"), map.get("CPEWANTechnology_DropdownValue"), map.get("PEWANTechnology_DropdownValue")
				, map.get("DSLProvider_DropdownValue"), map.get("PPPBased_CheckboxValue"), map.get("VOIP_CheckboxValue")
				, map.get("Downstream_DropdownValue"));
		
	  	ExtentTestManager.endTest();
	  	
		logger= ExtentTestManager.startTest("verifyManagementOptionspanel");
		APT_IPAccessConfigHelper.get().verifyManagementConfigpanels("ipa_config", map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox")
				, map.get("Package_DropdownValue"), map.get("SnmpNotification_Checkbox"), map.get("RouterConfigView_IPv4_Checkbox")
				, map.get("RouterConfigView_IPv6_Checkbox"), map.get("DeliveryChannel_DropdownValue"), map.get("RouterbasedFirewall_Checkbox")
				, map.get("QOS_Checkbox"), map.get("ManagedService_Checkbox"), map.get("BGPCheck_Checkbox"), map.get("ManInMiddle_Checkbox")
				, map.get("PA_Checkbox"), map.get("PI_Checkbox"), map.get("TrapTargetAddress_Value"), map.get("ActelisBased_Checkbox"));
		
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("verifyServicepanelLinks");
		APT_IPAccessConfigHelper.get().verifyEditservice("ipa_config", map.get("ServiceIdentification"), map.get("ServiceType")
				, map.get("NetworkConfiguration_DrodpwonValue"), map.get("EditRemarks"), map.get("Remarks")
				, map.get("Edit_TerminationDate"), map.get("Edit_BillingTypevalue"), map.get("Edit_Email"), map.get("Edit_PhoneContact")
				, map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_IPGuardian_Checkbox"), map.get("Edit_ServiceBW_Value")
				, map.get("Edit_Package_DropdownValue"), map.get("Edit_SnmpNotification_Checkbox"), map.get("Edit_RouterConfigView_IPv4_Checkbox")
				, map.get("Edit_RouterConfigView_IPv6_Checkbox"), map.get("Edit_DeliveryChannel_DropdownValue")
				, map.get("Edit_RouterbasedFirewall_Checkbox"), map.get("Edit_QOS_Checkbox"), map.get("Edit_ManagedService_Checkbox")
				, map.get("Edit_NoOfCircuits_DropdownValue"), map.get("Edit_OrderType_DropdownValue"), map.get("Edit_SpeedySurf_CheckboxValue")
				, map.get("Edit_CPEWANTechnology_DropdownValue"), map.get("Edit_PEWANTechnology_DropdownValue")
				, map.get("Edit_DSLProvider_DropdownValue"), map.get("Edit_PPPBased_CheckboxValue"), map.get("Edit_VOIP_CheckboxValue")
				, map.get("Edit_Downstream_DropdownValue"), map.get("Edit_BGPCheck_Checkbox"), map.get("Edit_ManInMiddle_Checkbox")
				, map.get("Edit_PA_Checkbox"), map.get("Edit_PI_Checkbox"), map.get("Edit_TrapTargetAddress_Value"), map.get("Edit_ActelisBased_Checkbox"));
		APT_IPAccessConfigHelper.get().verifyShowNewInfovistaReport("ipa_config");
		APT_IPAccessConfigHelper.get().verifyManageSubnets("ipa_config");
		APT_IPAccessConfigHelper.get().verifyDump("ipa_config");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("addExistingPEDevice");
		APT_IPAccessConfigHelper.get().searchorder("ipa_config", map.get("ServiceIdentification"));
		APT_IPAccessConfigHelper.get().addExistingPEDevice("ipa_config", map.get("ExistingDeviceName"));
		APT_IPAccessConfigHelper.get().deleteExistingDevice("ipa_config", map.get("ExistingDeviceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("addNewPEDevice");
		APT_IPAccessConfigHelper.get().navigateToAddNewDevicepage("ipa_config");
		APT_IPAccessConfigHelper.get().addNewPEDevice("ipa_config", map.get("DeviceName"), map.get("DeviceType"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
													map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
													map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
													map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"),
													map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
													map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
													map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		APT_IPAccessConfigHelper.get().verifyViewpage_Devicedetails("ipa_config", map.get("DeviceName"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
													map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
													map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
													map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"),
													map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
													map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
													map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		APT_IPAccessConfigHelper.get().verifyViewDevicepage_Links("ipa_config");
		APT_IPAccessConfigHelper.get().verifyEditDevice("ipa_config", map.get("editdeviceName"), map.get("editVendorModel"),map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
													map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
													map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
													map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("verifyRouterTools");
		APT_IPAccessConfigHelper.get().navigatetoViewDevicePage("ipa_config", map.get("DeviceName"), map.get("editdeviceName"));
		DeviceName= APT_IPAccessConfigHelper.get().verifyDeviceName("ipa_config");
		VendorModel= APT_IPAccessConfigHelper.get().verifyVendorModel("ipa_config");
		String managementAddressEdit=map.get("editManagementAddress");
		String managementAddressCreated=map.get("ManagementAddress");
		if(map.get("VendorModel").contains("Cisco"))
		{
		if(managementAddressEdit.equalsIgnoreCase("null")) {
			
			APT_IPAccessConfigHelper.get().verify_Cisco_RouterTools("ipa_config", map.get("command_ipv4"), map.get("command_ipv6"), managementAddressCreated,
					map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
		}
		else {
			APT_IPAccessConfigHelper.get().verify_Cisco_RouterTools("ipa_config", map.get("command_ipv4"), map.get("command_ipv6"), managementAddressEdit,
					map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
			
		}
		}
		else
		{
			if(managementAddressEdit.equalsIgnoreCase("null")) {
				
				APT_IPAccessConfigHelper.get().verify_Juniper_RouterTools("ipa_config", map.get("command_ipv4"), managementAddressCreated,
						map.get("vrf_Ipv4"));
			}
			else {
				APT_IPAccessConfigHelper.get().verify_Juniper_RouterTools("ipa_config", map.get("command_ipv4"), managementAddressEdit,
						map.get("vrf_Ipv4"));
				
			}
		}
		ExtentTestManager.endTest();
		
		
			logger= ExtentTestManager.startTest("verifyInterfacePanel");
			String CPEWANTechnology= map.get("CPEWANTechnology_DropdownValue");
			String Edit_CPEWANTechnology=map.get("Edit_CPEWANTechnology_DropdownValue");
			String PEWANTechnology= map.get("PEWANTechnology_DropdownValue");
			String Edit_PEWANTechnology=map.get("Edit_PEWANTechnology_DropdownValue");
			
			if(CPEWANTechnology.equalsIgnoreCase("Ethernet") || Edit_CPEWANTechnology.equalsIgnoreCase("Ethernet")) {
				if(PEWANTechnology.equalsIgnoreCase("Ethernet-Standard") || Edit_PEWANTechnology.equalsIgnoreCase("Ethernet-Standard")) {
					if(VendorModel.contains("Cisco") || VendorModel.contains("OneAccess"))
					{
					APT_IPAccessConfigHelper.get().verify_Cisco_Ethernet_EthernetStandard_AddInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4"), map.get("AddressIPv4Value")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
							, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
							, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue")
							, map.get("LinkValue"), map.get("BearerType_Value"), map.get("Encapsulation_Value")
							, map.get("Bandwidth_Value"), map.get("BGP_Checkbox"), map.get("VLANID_Value")
							, map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox")
							, map.get("VLV_Converged_Radiobutton"), map.get("VLV_Standalone_Radiobutton"));
					
					APT_IPAccessConfigHelper.get().verify_Cisco_Ethernet_EthernetStandard_EditInterface("ipa_config"
							, map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
							, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
							, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue")
							, map.get("Edit_LinkValue"), map.get("Edit_BearerType_Value"), map.get("Edit_Encapsulation_Value")
							, map.get("Bandwidth_Value"), map.get("BGP_Checkbox"), map.get("Edit_VLANID_Value")
							, map.get("Edit_IVManagement_Checkbox"), map.get("Edit_VLV_Checkbox")
							, map.get("Edit_VLV_Converged_Radiobutton"), map.get("Edit_VLV_Standalone_Radiobutton"));
					
					}
					else if(VendorModel.contains("Juniper"))
					{
						APT_IPAccessConfigHelper.get().verify_Juniper_Ethernet_EthernetStandard_AddInterface("ipa_config"
								, PEWANTechnology, Edit_PEWANTechnology, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
								, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize")
								, map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
								, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
								, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue")
								, map.get("LinkValue"), map.get("BearerType_Value"), map.get("Encapsulation_Value")
								, map.get("Bandwidth_Value"), map.get("UnitID_Value"), map.get("Slot_Value"), map.get("Pic_Value")
								, map.get("Port_Value"), map.get("STM1number_Value"), map.get("VLANID_Value")
								, map.get("AtricaConnected_checkbox"), map.get("BGP_Checkbox"), map.get("ColtAs_Checkbox")
								, map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox"), map.get("VLV_Converged_Radiobutton")
								, map.get("VLV_Standalone_Radiobutton"), map.get("CardType_DropdownValue"), map.get("FramingType_Value")
								, map.get("ClockSource_DropdownValue"), map.get("BearerNumber_Value"), map.get("DLCINumber_Value"));
						
						APT_IPAccessConfigHelper.get().verify_Juniper_Ethernet_EthernetStandard_EditInterface("ipa_config"
								, PEWANTechnology, Edit_PEWANTechnology, map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
								, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize")
								, map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
								, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
								, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue")
								, map.get("Edit_LinkValue"), map.get("Edit_BearerType_Value"), map.get("Edit_Encapsulation_Value")
								, map.get("Edit_Bandwidth_Value"), map.get("Edit_UnitID_Value"), map.get("Edit_Slot_Value"), map.get("Edit_Pic_Value")
								, map.get("Edit_Port_Value"), map.get("Edit_STM1number_Value"), map.get("Edit_VLANID_Value")
								, map.get("Edit_AtricaConnected_checkbox"), map.get("BGP_Checkbox"), map.get("Edit_ColtAs_Checkbox")
								, map.get("Edit_IVManagement_Checkbox"), map.get("Edit_VLV_Checkbox"), map.get("Edit_VLV_Converged_Radiobutton")
								, map.get("Edit_VLV_Standalone_Radiobutton"));
					}
				}
				else if(PEWANTechnology.equalsIgnoreCase("ATM") || Edit_PEWANTechnology.equalsIgnoreCase("ATM")) {
					APT_IPAccessConfigHelper.get().verify_Ethernet_ATM_AddInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
							, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
							, map.get("Encapsulation_Value"), map.get("PPPEncapsulation_Value"), map.get("DSLDownstreamSpeed_Value"), map.get("DSLUpstreamSpeed_Value")
							, map.get("MBS_Value"), map.get("VPI_Value"), map.get("VCI_Value"), map.get("Slot_DropdownValue"), map.get("Port_DropdownValue")
							, map.get("IVManagement_Checkbox"));
					
					APT_IPAccessConfigHelper.get().verify_Ethernet_ATM_EditInterface("ipa_config"
							, map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
							, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("Edit_LinkValue"), map.get("Edit_BearerType_Value")
							, map.get("Edit_Encapsulation_Value"), map.get("Edit_PPPEncapsulation_Value"), map.get("Edit_DSLDownstreamSpeed_Value")
							, map.get("Edit_DSLUpstreamSpeed_Value"), map.get("Edit_MBS_Value"), map.get("Edit_VPI_Value"), map.get("Edit_VCI_Value")
							, map.get("Edit_Slot_DropdownValue"), map.get("Edit_Port_DropdownValue"), map.get("Edit_IVManagement_Checkbox"));
				
				}
				else if(PEWANTechnology.equalsIgnoreCase("Ethernet-L2TP") || Edit_PEWANTechnology.equalsIgnoreCase("Ethernet-L2TP")) {
					APT_IPAccessConfigHelper.get().verify_Ethernet_EthernetL2TP_AddInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
							, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("IVManagement_Checkbox"));
					
					APT_IPAccessConfigHelper.get().verify_Ethernet_EthernetL2TP_EditInterface("ipa_config"
							, map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
							, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("Edit_IVManagement_Checkbox"));
					
				}
			}
			else if(CPEWANTechnology.equalsIgnoreCase("SDH") || Edit_CPEWANTechnology.equalsIgnoreCase("SDH")) {
					if(VendorModel.contains("Juniper"))
					{
						APT_IPAccessConfigHelper.get().verify_Juniper_SDH_AddInterface("ipa_config"
								, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
								, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
								, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
								, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
								, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
								, map.get("Encapsulation_Value"), map.get("Bandwidth_Value"), map.get("UnitID_Value")
								, map.get("Slot_Value"), map.get("Pic_Value"), map.get("Port_Value"), map.get("VLANID_Value")
								, map.get("AtricaConnected_checkbox"), map.get("BGP_Checkbox"), map.get("ColtAs_Checkbox")
								, map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox"), map.get("VLV_Converged_Radiobutton")
								, map.get("VLV_Standalone_Radiobutton"));
						
						APT_IPAccessConfigHelper.get().verify_Juniper_SDH_EditInterface("ipa_config"
								, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
								, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
								, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
								, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
								, map.get("EIPAllocation_AvailableBlocksValue"), map.get("Edit_LinkValue"), map.get("Edit_BearerType_Value")
								, map.get("Edit_Encapsulation_Value"), map.get("Bandwidth_Value"), map.get("Edit_UnitID_Value"), map.get("Edit_Slot_Value"), map.get("Edit_Pic_Value")
								, map.get("Edit_Port_Value"), map.get("Edit_VLANID_Value")
								, map.get("Edit_AtricaConnected_checkbox"), map.get("BGP_Checkbox"), map.get("Edit_ColtAs_Checkbox")
								, map.get("Edit_IVManagement_Checkbox"), map.get("Edit_VLV_Checkbox"), map.get("Edit_VLV_Converged_Radiobutton")
								, map.get("Edit_VLV_Standalone_Radiobutton"));
						
						APT_IPAccessConfigHelper.get().verify_SDH_AddMultilink("ipa_config", map.get("DeviceName")
								, map.get("Multilink_InterfaceName"), map.get("VendorModel"), map.get("ConfigureInterface_Checkbox")
								, map.get("InterfaceAddressRange_Value")
								, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
								, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
								, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
								, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("Encapsulation_Value")
								, map.get("Bandwidth_Value"), map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox")
								, map.get("VLV_Converged_Radiobutton"), map.get("VLV_Standalone_Radiobutton"), map.get("CheckToAddInterface_Checkbox"));
					}
					else
					{
						APT_IPAccessConfigHelper.get().verify_OneAccess_SDH_AddInterface("ipa_config"
								, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
								, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
								, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
								, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
								, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
								, map.get("Encapsulation_Value"), map.get("Bandwidth_Value")
								, map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox"), map.get("VLV_Converged_Radiobutton")
								, map.get("VLV_Standalone_Radiobutton"));
						
						APT_IPAccessConfigHelper.get().verify_OneAccess_SDH_EditInterface("ipa_config"
								, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
								, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
								, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
								, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
								, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
								, map.get("Encapsulation_Value"), map.get("Bandwidth_Value")
								, map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox"), map.get("VLV_Converged_Radiobutton")
								, map.get("VLV_Standalone_Radiobutton"));
						
						APT_IPAccessConfigHelper.get().verify_SDH_AddMultilink("ipa_config", map.get("DeviceName")
								, map.get("Multilink_InterfaceName"), map.get("VendorModel"), map.get("ConfigureInterface_Checkbox")
								, map.get("InterfaceAddressRange_Value")
								, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
								, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
								, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
								, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("Encapsulation_Value")
								, map.get("Bandwidth_Value"), map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox")
								, map.get("VLV_Converged_Radiobutton"), map.get("VLV_Standalone_Radiobutton"), map.get("CheckToAddInterface_Checkbox"));
					}
					
			}
			else if(CPEWANTechnology.equalsIgnoreCase("ADSL") || Edit_CPEWANTechnology.equalsIgnoreCase("ADSL")) {
				if(PEWANTechnology.equalsIgnoreCase("ATM") || Edit_PEWANTechnology.equalsIgnoreCase("ATM")) {
					APT_IPAccessConfigHelper.get().verify_Ethernet_ATM_AddInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
							, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
							, map.get("Encapsulation_Value"), map.get("PPPEncapsulation_Value"), map.get("DSLDownstreamSpeed_Value"), map.get("DSLUpstreamSpeed_Value")
							, map.get("MBS_Value"), map.get("VPI_Value"), map.get("VCI_Value"), map.get("Slot_DropdownValue"), map.get("Port_DropdownValue")
							, map.get("IVManagement_Checkbox"));
					
						APT_IPAccessConfigHelper.get().verify_Ethernet_ATM_EditInterface("ipa_config"
								, map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
								, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
								, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
								, map.get("EIPAllocation_AvailableBlocksValue"), map.get("Edit_LinkValue"), map.get("Edit_BearerType_Value")
								, map.get("Edit_Encapsulation_Value"), map.get("Edit_PPPEncapsulation_Value"), map.get("Edit_DSLDownstreamSpeed_Value")
								, map.get("Edit_DSLUpstreamSpeed_Value"), map.get("Edit_MBS_Value"), map.get("Edit_VPI_Value"), map.get("Edit_VCI_Value")
								, map.get("Edit_Slot_DropdownValue"), map.get("Edit_Port_DropdownValue"), map.get("Edit_IVManagement_Checkbox"));
						
						APT_IPAccessConfigHelper.get().verify_ADSL_ATM_AddMultilink("ipa_config", map.get("DeviceName")
								, map.get("Multilink_InterfaceName"), map.get("VendorModel"), map.get("ConfigureInterface_Checkbox")
								, map.get("InterfaceAddressRange_Value")
								, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("LinkValue"), map.get("Encapsulation_Value")
								, map.get("IVManagement_Checkbox"), map.get("DSLDownstreamSpeed_Value"), map.get("DSLUpstreamSpeed_Value")
								, map.get("CheckToAddInterface_Checkbox"));
						
				}
				else if(PEWANTechnology.equalsIgnoreCase("L2TP") || Edit_PEWANTechnology.equalsIgnoreCase("L2TP")) {
					APT_IPAccessConfigHelper.get().verify_ADSL_L2TP_AddInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName")
							, map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("IVManagement_Checkbox"));
					
					APT_IPAccessConfigHelper.get().verify_ADSL_L2TP_EditInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName")
							, map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("IVManagement_Checkbox"));
					
					APT_IPAccessConfigHelper.get().verify_ADSL_L2TP_AddMultilink("ipa_config", map.get("DeviceName")
							, map.get("Multilink_InterfaceName"), map.get("VendorModel"), map.get("ConfigureInterface_Checkbox")
							, map.get("InterfaceAddressRange_Value")
							, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("IVManagement_Checkbox"), map.get("CheckToAddInterface_Checkbox"));
					
				}

				else if(PEWANTechnology.equalsIgnoreCase("Ethernet") || Edit_PEWANTechnology.equalsIgnoreCase("Ethernet")) {
					APT_IPAccessConfigHelper.get().verify_ADSL_Ethernet_AddInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
							, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
							, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
							, map.get("Encapsulation_Value"), map.get("Bandwidth_Value"), map.get("VLANID_Value")
							, map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox"), map.get("VLV_Converged_Radiobutton")
							, map.get("VLV_Standalone_Radiobutton"));
					
					APT_IPAccessConfigHelper.get().verify_ADSL_Ethernet_EditInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
							, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
							, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("Edit_LinkValue"), map.get("Edit_BearerType_Value")
							, map.get("Edit_Encapsulation_Value"), map.get("Bandwidth_Value"), map.get("Edit_VLANID_Value")
							, map.get("Edit_IVManagement_Checkbox"), map.get("Edit_VLV_Checkbox"), map.get("Edit_VLV_Converged_Radiobutton")
							, map.get("Edit_VLV_Standalone_Radiobutton"));
					
					APT_IPAccessConfigHelper.get().verify_ADSL_Ethernet_AddMultilink("ipa_config", map.get("DeviceName")
							, map.get("Multilink_InterfaceName"), map.get("VendorModel"), map.get("ConfigureInterface_Checkbox")
							, map.get("InterfaceAddressRange_Value")
							, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
							, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
							, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("Encapsulation_Value")
							, map.get("Bandwidth_Value"), map.get("VLANID_Value"), map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox")
							, map.get("VLV_Converged_Radiobutton"), map.get("VLV_Standalone_Radiobutton"), map.get("CheckToAddInterface_Checkbox"));
					
				}
			}
			else if(CPEWANTechnology.equalsIgnoreCase("SDSL") || Edit_CPEWANTechnology.equalsIgnoreCase("SDSL")) {
				if(PEWANTechnology.equalsIgnoreCase("ATM") || Edit_PEWANTechnology.equalsIgnoreCase("ATM")) {
						APT_IPAccessConfigHelper.get().verify_Ethernet_ATM_AddInterface("ipa_config"
								, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
								, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
								, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
								, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
								, map.get("Encapsulation_Value"), map.get("PPPEncapsulation_Value"), map.get("DSLDownstreamSpeed_Value"), map.get("DSLUpstreamSpeed_Value")
								, map.get("MBS_Value"), map.get("VPI_Value"), map.get("VCI_Value"), map.get("Slot_DropdownValue"), map.get("Port_DropdownValue")
								, map.get("IVManagement_Checkbox"));
						
						APT_IPAccessConfigHelper.get().verify_Ethernet_ATM_EditInterface("ipa_config"
								, map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
								, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
								, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
								, map.get("EIPAllocation_AvailableBlocksValue"), map.get("Edit_LinkValue"), map.get("Edit_BearerType_Value")
								, map.get("Edit_Encapsulation_Value"), map.get("Edit_PPPEncapsulation_Value"), map.get("Edit_DSLDownstreamSpeed_Value")
								, map.get("Edit_DSLUpstreamSpeed_Value"), map.get("Edit_MBS_Value"), map.get("Edit_VPI_Value"), map.get("Edit_VCI_Value")
								, map.get("Edit_Slot_DropdownValue"), map.get("Edit_Port_DropdownValue"), map.get("Edit_IVManagement_Checkbox"));
						
						APT_IPAccessConfigHelper.get().verify_ADSL_ATM_AddMultilink("ipa_config", map.get("DeviceName")
								, map.get("Multilink_InterfaceName"), map.get("VendorModel"), map.get("ConfigureInterface_Checkbox")
								, map.get("InterfaceAddressRange_Value")
								, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
								, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
								, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("LinkValue"), map.get("Encapsulation_Value")
								, map.get("IVManagement_Checkbox"), map.get("DSLDownstreamSpeed_Value"), map.get("DSLUpstreamSpeed_Value")
								, map.get("CheckToAddInterface_Checkbox"));
						
				}
				else if(PEWANTechnology.equalsIgnoreCase("L2TP") || Edit_PEWANTechnology.equalsIgnoreCase("L2TP")) {
					APT_IPAccessConfigHelper.get().verify_ADSL_L2TP_AddInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName")
							, map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("existingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("IVManagement_Checkbox"));
					
					APT_IPAccessConfigHelper.get().verify_ADSL_L2TP_EditInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName")
							, map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("IVManagement_Checkbox"));
					
					APT_IPAccessConfigHelper.get().verify_ADSL_L2TP_AddMultilink("ipa_config", map.get("DeviceName")
							, map.get("Multilink_InterfaceName"), map.get("VendorModel"), map.get("ConfigureInterface_Checkbox")
							, map.get("InterfaceAddressRange_Value")
							, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("IVManagement_Checkbox"), map.get("CheckToAddInterface_Checkbox"));
					
				}
				else if(PEWANTechnology.equalsIgnoreCase("Ethernet") || Edit_PEWANTechnology.equalsIgnoreCase("Ethernet")) {
					APT_IPAccessConfigHelper.get().verify_ADSL_Ethernet_AddInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
							, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
							, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
							, map.get("Encapsulation_Value"), map.get("Bandwidth_Value"), map.get("VLANID_Value")
							, map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox"), map.get("VLV_Converged_Radiobutton")
							, map.get("VLV_Standalone_Radiobutton"));
					
					APT_IPAccessConfigHelper.get().verify_ADSL_Ethernet_EditInterface("ipa_config"
							, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
							, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
							, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
							, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
							, map.get("Encapsulation_Value"), map.get("Bandwidth_Value"), map.get("VLANID_Value")
							, map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox"), map.get("VLV_Converged_Radiobutton")
							, map.get("VLV_Standalone_Radiobutton"));
					
					APT_IPAccessConfigHelper.get().verify_ADSL_Ethernet_AddMultilink("ipa_config", map.get("DeviceName")
							, map.get("Multilink_InterfaceName"), map.get("VendorModel"), map.get("ConfigureInterface_Checkbox")
							, map.get("InterfaceAddressRange_Value")
							, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
							, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
							, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
							, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
							, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
							, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("Encapsulation_Value")
							, map.get("Bandwidth_Value"), map.get("VLANID_Value"), map.get("IVManagement_Checkbox"), map.get("VLV_Checkbox")
							, map.get("VLV_Converged_Radiobutton"), map.get("VLV_Standalone_Radiobutton"), map.get("CheckToAddInterface_Checkbox"));
					
				}
			}
			ExtentTestManager.endTest();
		
		
		
		if(VendorModel.contains("Juniper")) 
		{
		logger= ExtentTestManager.startTest("verifyInterfaceConfigHistory");
		APT_IPAccessConfigHelper.get().verifyInterfaceConfigHistory("ipa_config");
		ExtentTestManager.endTest();
		}
		
		
		logger= ExtentTestManager.startTest("verifySelectInterfaces");
		APT_IPAccessConfigHelper.get().selectInterfacelinkforDevice("ipa_config", DeviceName);
		
		if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
			APT_IPAccessConfigHelper.get().SelectInterfacetoremovefromservice("ipa_config");
		}else {
			System.out.println("interfaces are not removed");
		}
		
		if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
			APT_IPAccessConfigHelper.get().SelectInterfacetoaddwithservcie("ipa_config");
		}else {
			System.out.println("Interfaces are not added");
		}
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("VerifyManageService");
		APT_IPAccessConfigHelper.get().verifyManageService("ipa_config", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("Delete Device");
		APT_IPAccessConfigHelper.get().deleteDevice("ipa_config", DeviceName);
		ExtentTestManager.endTest();
		
	
		
		//================================== Other Code =============================================
		
		
		System.out.println("TC-08");
		logger= ExtentTestManager.startTest("VerifyActelisConfiguration_AddDSLAMandHSL"+ServicenameTag);
		APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
		APT_IPAccessConfigHelper.get().verifyAddDSLAMandHSLlink("IPAConfig2", map.get("ActelisTech_DSLAMdevice"));
		APT_IPAccessConfigHelper.get().AddDSLAMandHSL("IPAConfig2", map.get("ActelisTech_DSLAMdevice"), map.get("ActelisTech_DSLAMInterfacename"));
		ExtentTestManager.endTest();
		

		
		
		System.out.println("TC-09");
		logger= ExtentTestManager.startTest("verifyAddCPEDeviceFunction_IPAccessConfiguration");
		Thread.sleep(2000);
		APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
		APT_IPAccessConfigHelper.get().verifyAddCPEDeviceFunction("IPAConfig2", map.get("ServiceIdentification"),
				map.get("CPE_DeviceName"), map.get("CPE_RouterID"), map.get("CPE_VendorModel"),map.get("CPE_ManagementAddress"),map.get("CPE_Snmpro"),map.get("CPE_Country"),
				map.get("CPE_City"),  map.get("CPE_Site"), map.get("CPE_Premise"), map.get("CPE_Role1"),  map.get("CPE_Role2")); 
		ExtentTestManager.endTest();
		
		
		
		
		
		
		
	System.out.println("TC-10");
	logger= ExtentTestManager.startTest("verifyAddedCPEDeviceInformation_IPAccessConfiguration");
	APT_IPAccessConfigHelper.get().verifyAddedCPEDeviceInformation_View("IPAConfig2", map.get("ServiceIdentification"),
			map.get("CPE_DeviceName"), map.get("CPE_RouterID"),map.get("CPE_VendorModel"),map.get("CPE_ManagementAddress"),map.get("CPE_Snmpro"),map.get("CPE_Country"),
			map.get("CPE_City"),  map.get("CPE_Site"), map.get("CPE_Premise"), map.get("CPE_Role1"),  map.get("CPE_Role2")); 
	APT_IPAccessConfigHelper.get().testStatus_CPE("IPAConfig2");
	ExtentTestManager.endTest();
	
	
		
		
		
		
	
	
	System.out.println("TC-11");
	logger= ExtentTestManager.startTest("verifyEditCPEDeviceFunction_IPAccessConfiguration");
	APT_IPAccessConfigHelper.get().verifyEditCPEDeviceFunction("IPAConfig2", map.get("ServiceIdentification"),
			map.get("CPE_DeviceNameEdit"), map.get("CPE_RouterIDEdit"),map.get("CPE_VendorModelEdit"),map.get("CPE_ManagementAddressEdit"),map.get("CPE_SnmproEdit"),map.get("CPE_CountryEdit"),
			map.get("CPE_CityEdit"),  map.get("CPE_SiteEdit"), map.get("CPE_PremiseEdit"), map.get("CPE_Role1Edit"),  map.get("CPE_Role2Edit")); 
	ExtentTestManager.endTest();
	
	
	
	
		
	
	
		System.out.println("TC-12");
		logger= ExtentTestManager.startTest("verifyRouterToolFunction_CPE_IPAccessConfiguration");
		APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
		APT_IPAccessConfigHelper.get().navigateToViewDevicePage_CPE("IPAConfig2", map.get("CPE_DeviceName"));
		APT_IPAccessConfigHelper.get().routerPanel_CPE("IPAConfig2", map.get("CPE_CommandIPV4"),
				map.get("CPE_CommandIPV6"),map.get("CPE_vrf_Ipv4"), map.get("CsPE_vrf_Ipv6"));
		ExtentTestManager.endTest();
		

		
		
	
		
			System.out.println("TC-13");
			logger= ExtentTestManager.startTest("verifyAddEditDeleteRouteFunction_CPE"+ServicenameTag);
			
			APT_IPAccessConfigHelper.get().addRouteFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"), 	map.get("CPE_RouteCity"),
				 map.get("CPE_RouteSubnetSize"), map.get("CPE_Gateway"), map.get("CPE_NetworkAddress"), map.get("CPE_NetworkMAS"),	map.get("CPE_Metrics"));
			APT_IPAccessConfigHelper.get().editRouteFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),map.get("CPE_RouteCityEdit"),
					map.get("CPE_RouteSubnetSizeEdit"), map.get("CPE_GatewayEdit"), map.get("CPE_NetworkAddressEdit"), map.get("CPE_NetworkMASEdit"),map.get("CPE_MetricsEdit"));
			APT_IPAccessConfigHelper.get().deleteRouteFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),map.get("CPE_RouteCityEdit"),
					map.get("CPE_RouteSubnetSizeEdit"), map.get("CPE_GatewayEdit"), map.get("CPE_NetworkAddressEdit"), map.get("CPE_NetworkMASEdit"),map.get("CPE_MetricsEdit"));
			ExtentTestManager.endTest();
				
	
			
			
			
	
	
			System.out.println("TC-14");
			logger= ExtentTestManager.startTest("verifyCustomerReadonlySNMPFunction_CPE"+ServicenameTag);
			APT_IPAccessConfigHelper.get().addCustomerReadonlySNMPFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("CustomerIPAddress"), map.get("CustomerCommunityString"));
			APT_IPAccessConfigHelper.get().editCustomerReadonlySNMPFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("CustomerIPAddressEdit"),map.get("CustomerCommunityStringEdit"));
			APT_IPAccessConfigHelper.get().deleteCustomerReadonlySNMPFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("CustomerIPAddressEdit"),map.get("CustomerCommunityStringEdit"));
			ExtentTestManager.endTest();
	
		 

			
			
			System.out.println("TC-15");
			logger= ExtentTestManager.startTest("verifyExtraSubnetsFunction_CPE"+ServicenameTag);
			APT_IPAccessConfigHelper.get().addExtraSubnetFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"), map.get("ExtraSubnets_City"), map.get("ExtraSubnets_SubnetSize"));
			ExtentTestManager.endTest();
		 
		 
			
		 
			System.out.println("TC-16");
			logger= ExtentTestManager.startTest("verifyNATConfigurationFunction_CPE"+ServicenameTag);
			APT_IPAccessConfigHelper.get().editNATConfigurationFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("StaticNATEdit"),map.get("DynamicNATEdit"));
			ExtentTestManager.endTest();
		 
		 
		
		
		
			
			
			String StaticNATMapping=map.get("StaticNATEdit");
	        String DynamicNATMapping=map.get("DynamicNATEdit");
	        if(StaticNATMapping.equalsIgnoreCase("yes") && DynamicNATMapping.equalsIgnoreCase("no")) {
	        		//New Customer Creation
	        	System.out.println("TC-17");
				logger= ExtentTestManager.startTest("verifyStaticNATMappingFunction_CPE"+ServicenameTag);
				ExtentTestManager.getTest().log(LogStatus.INFO, "'Static NAT Selection status is :  "+StaticNATMapping);
				APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
				APT_IPAccessConfigHelper.get().navigateToViewDevicePage_CPE("IPAConfig2", "CPE_DeviceName");
				APT_IPAccessConfigHelper.get().addStaticNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
				map.get("Static_Protocol"),map.get("Static_LocalPort"), map.get("Static_GlobalPort"), map.get("Static_LocalIP"), map.get("Static_GlobalIP"));
				APT_IPAccessConfigHelper.get().editStaticNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
				map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));
				APT_IPAccessConfigHelper.get().deleteStaticNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIP"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));
				ExtentTestManager.endTest();
	              
	        }else if(StaticNATMapping.equalsIgnoreCase("no") && DynamicNATMapping.equalsIgnoreCase("Yes")) {
	              
	        	System.out.println("TC-18");
				logger= ExtentTestManager.startTest("verifyDynamicNATMappingFunction_CPE"+ServicenameTag);
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Dynamic NAT Selection status is :  "+DynamicNATMapping);
				APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
				APT_IPAccessConfigHelper.get().navigateToViewDevicePage_CPE("IPAConfig2", "CPE_DeviceName");
				APT_IPAccessConfigHelper.get().addDynamicNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
				map.get("Dynamic_PoolMode"),map.get("Dynamic_InterfaceMode"), map.get("Dynamic_LocalNetwork"), map.get("Dynamic_PoolStartAddress"), map.get("Dynamic_PoolEndAddress"),
				map.get("Dynamic_PoolPrefix"), map.get("Dynamic_MapsToInterface"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"));
				APT_IPAccessConfigHelper.get().editDynamicNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("EditDynamic_PoolMode"),map.get("EditDynamic_InterfaceMode"), map.get("EditDynamic_LocalNetwork"), map.get("EditDynamic_PoolStartAddress"), map.get("EditDynamic_PoolEndAddress"),
						map.get("EditDynamic_PoolPrefix"), map.get("EditDynamic_MapsToInterface"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"));
				APT_IPAccessConfigHelper.get().deleteDynamicNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification")
						,map.get("CPE_DeviceName"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"), map.get("Dynamic_LocalNetwork")
						, map.get("EditDynamic_LocalNetwork"));
				ExtentTestManager.endTest();
				
	        }else if(StaticNATMapping.equalsIgnoreCase("Yes") && DynamicNATMapping.equalsIgnoreCase("Yes")) {
	        	System.out.println("TC-19");
				logger= ExtentTestManager.startTest("verifyStaticNATMappingFunction_CPE"+ServicenameTag);
				ExtentTestManager.getTest().log(LogStatus.INFO, "'Static NAT Selection status is :  "+StaticNATMapping);
				APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
				APT_IPAccessConfigHelper.get().navigateToViewDevicePage_CPE("IPAConfig2", "CPE_DeviceName");
				APT_IPAccessConfigHelper.get().addStaticNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Static_Protocol"),map.get("Static_LocalPort"), map.get("Static_GlobalPort"), map.get("Static_LocalIP"), map.get("Static_GlobalIP"));
				APT_IPAccessConfigHelper.get().editStaticNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));
				APT_IPAccessConfigHelper.get().deleteStaticNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIP"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));
				ExtentTestManager.endTest();
				
				logger= ExtentTestManager.startTest("verifyDynamicNATMappingFunction_CPE"+ServicenameTag);
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Dynamic NAT Selection status is :  "+DynamicNATMapping);
				APT_IPAccessConfigHelper.get().addDynamicNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Dynamic_PoolMode"),map.get("Dynamic_InterfaceMode"), map.get("Dynamic_LocalNetwork"), map.get("Dynamic_PoolStartAddress"), map.get("Dynamic_PoolEndAddress"),
						map.get("Dynamic_PoolPrefix"), map.get("Dynamic_MapsToInterface"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"));
				APT_IPAccessConfigHelper.get().editDynamicNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("EditDynamic_PoolMode"),map.get("EditDynamic_InterfaceMode"), map.get("EditDynamic_LocalNetwork"), map.get("EditDynamic_PoolStartAddress"), map.get("EditDynamic_PoolEndAddress"),
						map.get("EditDynamic_PoolPrefix"), map.get("EditDynamic_MapsToInterface"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"));
				APT_IPAccessConfigHelper.get().deleteDynamicNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName")
						, map.get("StaticNATEdit"), map.get("DynamicNATEdit"), map.get("Dynamic_LocalNetwork"), map.get("EditDynamic_LocalNetwork"));
				ExtentTestManager.endTest();
	        	
	        }else if(StaticNATMapping.equalsIgnoreCase("no") && DynamicNATMapping.equalsIgnoreCase("no")) {
	        	System.out.println("TC-20");
	        	logger= ExtentTestManager.startTest("StaticAndDynamicNonSelection_CPE"+ServicenameTag);
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Static NAT & Dynamic NAT is not selected as expected");
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Static NAT Selection status is :  "+StaticNATMapping);
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Dynamic NAT Selection status is :  "+DynamicNATMapping);
	        	ExtentTestManager.endTest();
	        }
	        
	        
	        
	        
		 
		 
		 
			System.out.println("TC-21");
			logger= ExtentTestManager.startTest("verifyDHCPServersonCPEFunction_CPE_Multohomed");
			APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
			APT_IPAccessConfigHelper.get().navigateToViewDevicePage_CPE("IPAConfig2", "CPE_DeviceName");
			APT_IPAccessConfigHelper.get().addDHCPServersonCPEFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("DHCP_CustomerLANSubnet"),map.get("DHCP_SubnetMask"), map.get("DHCP_PrimaryDNSServer"), map.get("DHCP_SecondaryDNSServer"));
			APT_IPAccessConfigHelper.get().editDHCPServersonCPEFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("DHCP_CustomerLANSubnetEdit"),map.get("DHCP_SubnetMaskEdit"), map.get("DHCP_PrimaryDNSServerEdit"), map.get("DHCP_SecondaryDNSServerEdit"));
			APT_IPAccessConfigHelper.get().deleteDHCPServersonCPEFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("DHCP_CustomerLANSubnetEdit"),map.get("DHCP_SubnetMaskEdit"), map.get("DHCP_PrimaryDNSServerEdit"), map.get("DHCP_SecondaryDNSServerEdit"));
			ExtentTestManager.endTest();
		 
			
			
			
		 
			System.out.println("TC-22");
			logger= ExtentTestManager.startTest("verifyDHCPIPV6ServersonCPEFunction_CPE"+ServicenameTag);
			APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
			APT_IPAccessConfigHelper.get().navigateToViewDevicePage_CPE("IPAConfig2", "CPE_DeviceName");
			APT_IPAccessConfigHelper.get().addDHCPIPV6ServersonCPEFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("DHCPIPV6_DHCPType"),map.get("DHCPIPV6_SubnetMask"), map.get("DHCPIPV6_LANIPV6Subnet"), map.get("DHCPIPV6_DomainName"), map.get("DHCPIPV6_PrimaryDNSServer"), map.get("DHCPIPV6_SecondaryDNSServer"));
			APT_IPAccessConfigHelper.get().editDHCPIPV6ServersonCPEFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("EditDHCPIPV6_DHCPType"),map.get("EditDHCPIPV6_SubnetMask"), map.get("EditDHCPIPV6_LANIPV6Subnet"), map.get("EditDHCPIPV6_DomainName"), map.get("EditDHCPIPV6_PrimaryDNSServer"), map.get("EditDHCPIPV6_SecondaryDNSServer"));
			APT_IPAccessConfigHelper.get().deleteDHCPIPV6ServersonCPEFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("EditDHCPIPV6_DHCPType"),map.get("EditDHCPIPV6_SubnetMask"), map.get("EditDHCPIPV6_LANIPV6Subnet"), map.get("EditDHCPIPV6_DomainName"), map.get("EditDHCPIPV6_PrimaryDNSServer"), map.get("EditDHCPIPV6_SecondaryDNSServer"));
				ExtentTestManager.endTest();
		 
		 
				
				
				
				System.out.println("TC-23");
				logger= ExtentTestManager.startTest("VerifyConfigurationPanel_CPE"+ServicenameTag);
				APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
				APT_IPAccessConfigHelper.get().navigateToViewDevicePage_CPE("IPAConfig2", "CPE_DeviceName");
				APT_IPAccessConfigHelper.get().VerifyConfigurationPanel_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Configuration"));
					ExtentTestManager.endTest();
		
					
					
					
					System.out.println("TC-24");
					logger= ExtentTestManager.startTest("verifyFetchDeviceInterfaceFunction_CPE_IPAccessConfiguration");
					APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
					APT_IPAccessConfigHelper.get().navigateToViewDevicePage_CPE("IPAConfig2", map.get("CPE_DeviceName"));
					APT_IPAccessConfigHelper.get().veriyFetchDeviceInterfacesFunction_CPE("IPAConfig2",map.get("ServiceIdentification"), 
							map.get("CPE_ServiceStatusChangeRequired"));
					ExtentTestManager.endTest();
					
					
					////////////////////////////////////
					
					
					
					if(CPEWANTechnology.equalsIgnoreCase("ADSL") || Edit_CPEWANTechnology.equalsIgnoreCase("ADSL")) {
						System.out.println("TC-25");
						if(PEWANTechnology.equalsIgnoreCase("L2TP") || Edit_PEWANTechnology.equalsIgnoreCase("L2TP")) {
							logger= ExtentTestManager.startTest("verifyAddWANMultilink_CPEDevice_IPAccessConfiguration");
							APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
							APT_IPAccessConfigHelper.get().verifyADSL_L2TP_AddWANMultilink_CPEDevice("IPAConfig2",map.get("CPEMultilink_Name"), 
									map.get("CPEMultilink_GetAddress_Button"), map.get("CPEMultilink_IPV6_GetAddressButton"), map.get("CPEMultilink_InterfaceAddressRange_Value"), 
									map.get("CPEMultilink_EIPAllocation_City"),map.get("CPEMultilink_EIPAllocation_SubnetSize"), map.get("CPEMultilink_EIPAllocation_IPv6_SubnetSize"), 
									map.get("CPEMultilink_EIPAllocation_AvailableBlocksValue"),	map.get("CPEMultilink_EthernetCheckbox"), map.get("CPEMultilink_Speed"),
									map.get("CPEMultilink_Duplex"), map.get("CPEMultilink_InterfaceIsWANCheckbox"),map.get("CPEMultilink_InterfaceIsLANCheckbox"),
									map.get("CPEMultilink_VRRPGroup"), map.get("CPEMultilink_VRRPIP"),map.get("CPEMultilink_VRRPIPV6"), map.get("CPEMultilink_VRRPPriority"),
									map.get("CPEMultilink_VRRPPassword"), map.get("CPEMultilink_FailoverCableChekbox"),map.get("CPEMultilink_LinkValue"), map.get("Multilink_IVManagementCheckbox"), 
									map.get("CPEMultilink_ClockSource"), map.get("CPEMultilink_AddthisasaPIRangeCheckbox"), map.get("CPEMultilink_AddthisasaPIRangeIPV6Checkbox"));
							ExtentTestManager.endTest();
							
						
							

							System.out.println("TC-26");
							logger= ExtentTestManager.startTest("verifyAddLANInterface_CPEDevice_IPAccessConfiguration");
							APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
							APT_IPAccessConfigHelper.get().verifyAddLANInterface_CPEDevice("IPAConfig2",map.get("CPE_InterfaceName"), 
									map.get("CPE_GetAddress_Button"), map.get("CPE_IPV6_GetAddressButton"), map.get("CPE_InterfaceAddressRange_Value"), map.get("CPE_EIPAllocation_City"),
									map.get("CPE_EIPAllocation_SubnetSize"), map.get("CPE_EIPAllocation_IPv6_SubnetSize"), map.get("CPE_EIPAllocation_AvailableBlocksValue"),
									map.get("CPE_EthernetCheckbox"), map.get("CPE_Speed"), map.get("CPE_Duplex"), map.get("CPE_InterfaceIsWANCheckbox"),
									map.get("CPE_InterfaceIsLANCheckbox"), map.get("CPE_VRRPGroup"), map.get("CPE_VRRPIP"), map.get("CPE_VRRPIPV6"), map.get("CPE_VRRPPriority"),
									map.get("CPE_VRRPPassword"), map.get("CPE_FailoverCableChekbox"),map.get("CPE_LinkValue"),
									map.get("IVManagementCheckbox"), map.get("CPE_ClockSource"), map.get("CPE_Encapsulation"), map.get("CPE_VLAN"));
							ExtentTestManager.endTest();
							
						
							
						
							System.out.println("TC-27");
							logger= ExtentTestManager.startTest("verifyAddWANInterface_CPEDevice_IPAccessConfiguration");
							APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
							APT_IPAccessConfigHelper.get().verifyAddWANInterface_CPEDevice("IPAConfig2",map.get("CPE_InterfaceName"), 
									map.get("CPE_GetAddress_Button"), map.get("CPE_IPV6_GetAddressButton"), map.get("CPE_InterfaceAddressRange_Value"), map.get("CPE_EIPAllocation_City"),
									map.get("CPE_EIPAllocation_SubnetSize"), map.get("CPE_EIPAllocation_IPv6_SubnetSize"), map.get("CPE_EIPAllocation_AvailableBlocksValue"),
									map.get("CPE_EthernetCheckbox"), map.get("CPE_Speed"), map.get("CPE_Duplex"), map.get("CPE_InterfaceIsWANCheckbox"),
									map.get("CPE_InterfaceIsLANCheckbox"), map.get("CPE_VRRPGroup"), map.get("CPE_VRRPIP"), map.get("CPE_VRRPIPV6"), map.get("CPE_VRRPPriority"),
									map.get("CPE_VRRPPassword"), map.get("CPE_FailoverCableChekbox"),map.get("CPE_LinkValue"),
									map.get("IVManagementCheckbox"), map.get("CPE_ClockSource"), map.get("CPE_EncapsulationForLANInterface"), map.get("CPE_VLAN")
									,map.get("CPE_BearerType"), map.get("CPE_DownStream"), map.get("CPE_UpStream"), map.get("CPE_LineMode"), map.get("CPE_VPI"), map.get("CPE_VCI")
									,map.get("Layer3Checkbox"));
							ExtentTestManager.endTest();


								
						}
						
					}
					
					
					
					
					
					
					///////////////////////////////////////
				
				
					

		
				logger= ExtentTestManager.startTest("TC-28 : deletePEdeviceFunction_NewlyCreatedDevice_IPAccessConfiguration");
				APT_IPAccessConfigHelper.get().searchorder("IPAConfig2", map.get("ServiceIdentification"));
				APT_IPAccessConfigHelper.get().deleteDevice_PE("IPAConfig2", "DeviceName");
				ExtentTestManager.endTest();
		
		
		
		
				logger= ExtentTestManager.startTest("TC-29 : deleteCPEdeviceFunction_IPAccessConfiguration");
				APT_IPAccessConfigHelper.get().deleteDevice_CPE("IPAConfig2", "CPE_DeviceName");
				ExtentTestManager.endTest();

		
		
		
		
		
				logger= ExtentTestManager.startTest("TC-30 : deleteServiceFunction_IPAccessConfiguration");
				APT_IPAccessConfigHelper.get().deleteSevice1("IPAConfig2", "ServiceIdentification");
				ExtentTestManager.endTest();
		
					
					
					
					
					
	
	}
	
	
}
	