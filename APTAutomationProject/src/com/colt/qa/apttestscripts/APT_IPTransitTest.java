package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.scripthelpers.APT_LoginHelper;

public class APT_IPTransitTest extends DriverTestcase{
	
public String CustomerName=null;
public static String DeviceNameValue="Deviceedit474";
public static String VendorModelValue="Juniper";

	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_IPTransit", priority=0)
	public void IPTransitService(Map<String, String> map) throws Exception {
		
		setup();	
		Login.APT_Login_1(map.get("url"));
		
        String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
        
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
              
              logger= ExtentTestManager.startTest ("CreateCustomer");
              APT_IPTransitHelper.get().createcustomer("iptransit", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              
              logger= ExtentTestManager.startTest ("selectExistingCustomer"); 
              APT_IPTransitHelper.get().selectCustomertocreateOrder("iptransit",map.get("newCustomer"));
              
        }
        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              logger= ExtentTestManager.startTest ("selectExistingCustomer"); 
              APT_IPTransitHelper.get().selectCustomertocreateOrder("iptransit",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
        }
        
		logger= ExtentTestManager.startTest ("verifyCreateorder");
		APT_IPTransitHelper.get().createorderservice("iptransit", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
	
		logger= ExtentTestManager.startTest ("verifyservicetypeselection");
		APT_IPTransitHelper.get().verifyselectservicetype("iptransit", map.get("ServiceType"));
	
		logger= ExtentTestManager.startTest ("verifyservicecreation");
		APT_IPTransitHelper.get().verifyservicecreation("iptransit", map.get("ServiceIdentification"), map.get("Remarks"),  map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType"), map.get("TerminationDate"), map.get("BillingTypevalue"), map.get("Email"), map.get("PhoneContact"), map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox"));
	
		logger= ExtentTestManager.startTest ("verifyCustomerDetailsInformation");
		APT_IPTransitHelper.get().verifyCustomerDetailsInformation("iptransit", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		APT_IPTransitHelper.get().verifyUserDetailsInformation("iptransit", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
	
//		logger= ExtentTestManager.startTest ("verifyUserDetailsInformation");
//		APT_IPTransitHelper.get().VerifyUsersPanel("iptransit", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//				map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//				map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"), map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"), 
//				map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), 
//				map.get("SiteOrders_ToBeAvailable"), map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
		
		logger= ExtentTestManager.startTest ("verifyOrderDetailsInformation");
		APT_IPTransitHelper.get().verifyorderpanel_editorder("iptransit", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
		APT_IPTransitHelper.get().verifyorderpanel_changeorder("iptransit", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));
	
		logger= ExtentTestManager.startTest ("verifyServicepanelinviewservicepage");
	  	APT_IPTransitHelper.get().verifyservicepanelInformationinviewservicepage("iptransit", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("Remarks"), map.get("TerminationDate"), map.get("BillingTypevalue"), map.get("Email"), map.get("PhoneContact"));
		
	  	logger= ExtentTestManager.startTest ("verifyManagementOptionspanel");
		APT_IPTransitHelper.get().verifyManagementOptionspanel("iptransit", map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox"));
	
		logger= ExtentTestManager.startTest ("verifyServicepanelLinks");
	  	APT_IPTransitHelper.get().verifyEditservice("iptransit", map.get("EditRemarks"), map.get("Remarks"), map.get("ServiceIdentification"), map.get("Edit_TerminationDate"), map.get("Edit_BillingTypevalue"), map.get("Edit_Email"), map.get("Edit_PhoneContact"), map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_IPGuardian_Checkbox"));
		APT_IPTransitHelper.get().verifyManageSubnets("iptransit");
		APT_IPTransitHelper.get().verifyManageSubnetsIPv6("iptransit");
		APT_IPTransitHelper.get().verifyDump("iptransit");
		APT_IPTransitHelper.get().verifyShowNewInfovistaReport("iptransit");
		
		logger= ExtentTestManager.startTest ("addExistingPEDevice");
		APT_IPTransitHelper.get().addExistingPEDevice("iptransit", map.get("ExistingDeviceName"));
		APT_IPTransitHelper.get().verifyExistingDevice_ViewDevicedetails("iptransit", map.get("ExistingDeviceName"));
		APT_IPTransitHelper.get().deleteExistingDevice("iptransit", map.get("ExistingDeviceName"));
	
		logger= ExtentTestManager.startTest ("addNewPEDevice");
		APT_IPTransitHelper.get().navigateToAddNewDevicepage("iptransit");
		APT_IPTransitHelper.get().verifyadddevicefields("iptransit");
		APT_IPTransitHelper.get().addNewPEDevice("iptransit", map.get("DeviceName"), map.get("DeviceType"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
													map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
													map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
													map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"),
													map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
													map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
													map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		APT_IPTransitHelper.get().verifyViewpage_Devicedetails("iptransit", map.get("DeviceName"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), 
													map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3UsernameNewValue"), 
													map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"),
													map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
													map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
													map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		APT_IPTransitHelper.get().verifyViewDevicepage_Links("iptransit");
		APT_IPTransitHelper.get().verifyEditDevice("iptransit", map.get("editdeviceName"), map.get("editVendorModel"), map.get("editTelnet"), map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"), map.get("editSnmpv3UsernameNewValue"),
													map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
													map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
													map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
													map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
		
		APT_IPTransitHelper.get().verifyViewpage_UpdatedDevicedetails("iptransit", map.get("editdeviceName"), map.get("editVendorModel"), map.get("editTelnet"), map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"), map.get("editSnmpv3UsernameNewValue"),
													map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),map.get("editManagementAddress"), map.get("DeviceName"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
													map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"), map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
													map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"), map.get("ManagementAddress"));

		logger= ExtentTestManager.startTest ("verifyRouterTools");
		DeviceNameValue= APT_IPTransitHelper.get().DeviceName("iptransit");
		VendorModelValue= APT_IPTransitHelper.get().VendorModel("iptransit");
		String managementAddressEdit=map.get("editManagementAddress");
		String managementAddressCreated=map.get("ManagementAddress");
		if(VendorModelValue.contains("Cisco"))
		{
		if(managementAddressEdit.equalsIgnoreCase("null")) {
			
			APT_IPTransitHelper.get().verify_Cisco_RouterTools("iptransit", map.get("command_ipv4"), map.get("command_ipv6"), managementAddressCreated,
					map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
		}
		else {
			APT_IPTransitHelper.get().verify_Cisco_RouterTools("iptransit", map.get("command_ipv4"), map.get("command_ipv6"), managementAddressEdit,
					map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
			
		}
		}
		else
		{
			if(managementAddressEdit.equalsIgnoreCase("null")) {
				
				APT_IPTransitHelper.get().verify_Juniper_RouterTools("iptransit", map.get("command_ipv4"), managementAddressCreated,
						map.get("vrf_Ipv4"));
			}
			else {
				APT_IPTransitHelper.get().verify_Juniper_RouterTools("iptransit", map.get("command_ipv4"), managementAddressEdit,
						map.get("vrf_Ipv4"));
				
			}
		}
		
			logger= ExtentTestManager.startTest ("verifyRoutesPanel");
			
			DeviceNameValue= APT_IPTransitHelper.get().DeviceName("iptransit");
			VendorModelValue= APT_IPTransitHelper.get().VendorModel("iptransit");
			
			if(VendorModelValue.contains("Cisco"))
			{
			APT_IPTransitHelper.get().verify_CiscoVendor_AddInterface("iptransit", map.get("ConfigureInterface_Checkbox")
					, map.get("InterfaceAddressRange_Value")
					, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection")
					, map.get("ExistingAddressIPv4DropdownValue"), map.get("NewAddressRangeIpv4selection")
					, map.get("NewInterfaceAddressRangeIPv4"), map.get("EIPAllocation_SubnetSize")
					, map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
					, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
					, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue")
					, map.get("BearerType_Value"), map.get("CiscoVendor_Bandwidth_Value"), map.get("Encapsulation_Value")
					, map.get("BGP_Checkbox"), map.get("FramingType_Value"), map.get("VLANID_Value"), map.get("BGPTemplate_Value")
					, map.get("CPEWAN_Value"), map.get("CPEWANIPv6_Value"), map.get("Description_Value"), map.get("AsCustomer_Value")
					, map.get("BGPPassword_Value"));
			APT_IPTransitHelper.get().verify_CiscoVendor_EditInterface("iptransit", DeviceNameValue, map.get("Edit_ConfigureInterface_Checkbox")
					, map.get("Edit_Network")
					, map.get("InterfaceAddressRange_value"), map.get("EIPAllocation_City")
					, map.get("ExistingAddressRangeIPv4selection")
					, map.get("ExistingAddressIPv4DropdownValue"), map.get("NewAddressRangeIpv4selection")
					, map.get("NewInterfaceAddressRangeIPv4"), map.get("EIPAllocation_SubnetSize")
					, map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
					, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
					, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue")
					, map.get("Edit_LinkValue"), map.get("Edit_BearerType_value"), map.get("Edit_CiscoVendor_Bandwidth_value")
					, map.get("Edit_FramingTypeValue"), map.get("Edit_EncapsulationValue"), map.get("Edit_VLANIDvalue")
					, map.get("Edit_BGPCheckbox"), map.get("Edit_BGPTemplate_Dropdownvalue"), map.get("Edit_CPEWAN_Value")
					, map.get("Edit_CPEWANIPv6_Value"), map.get("Edit_DescriptionValue"), map.get("Edit_AsCustomerValue")
					, map.get("Edit_BGPPasswordValue"), map.get("Edit_IPSubnetIPv6Value"), map.get("Edit_IPSubnetIPv4Value")
					, map.get("Edit_EIPAllocation_Subnetsize"), map.get("Edit_EIPAllocation_IPv6_Subnetsize"));
			
			APT_IPTransitHelper.get().verify_CiscoVendor_AddMultilink("iptransit", DeviceNameValue
					, map.get("Multilink_InterfaceName"), VendorModelValue, map.get("InterfaceAddressRange_Value")
					, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection")
					, map.get("ExistingAddressIPv4DropdownValue"), map.get("NewAddressRangeIpv4selection")
					, map.get("NewInterfaceAddressRangeIPv4"), map.get("EIPAllocation_SubnetSize")
					, map.get("ExistingAddressRangeIPv6selection"), map.get("ExistingAddressIPv6Value")
					, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
					, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue")
					, map.get("LinkValue"), map.get("Multilink_Bandwidth_Value"), map.get("Encapsulation_Value")
					, map.get("BGP_Checkbox"), map.get("BGPTemplate_Value"), map.get("CPEWAN_Value"), map.get("CPEWANIPv6_Value")
					, map.get("Description_Value"), map.get("AsCustomer_Value"), map.get("BGPPassword_Value")
					, map.get("Multilink_ConfigInterface_checkbox"), map.get("CheckToAddInterface_Checkbox")
					, map.get("UnitIDValue"), map.get("SlotValue"), map.get("PicValue"), map.get("PortValue"));
			}
			else
			{
				APT_IPTransitHelper.get().verify_JuniperVendor_AddInterface("iptransit", map.get("Juniper_ConfigInterface_checkbox")
						, map.get("InterfaceAddressRange_Value")
						, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
						, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
						, map.get("EIPAllocation_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
						, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
						, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
						, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value")
						, map.get("Bandwidth_Value"), map.get("Encapsulation_Value"), map.get("BGP_Checkbox")
						, map.get("FramingType_Value"), map.get("VLANID_Value"), map.get("BGPTemplate_Value")
						, map.get("CPEWAN_Value"), map.get("CPEWANIPv6_Value"), map.get("Description_Value")
						, map.get("AsCustomer_Value"), map.get("BGPPassword_Value"), map.get("CardType_DropdownValue")
						, map.get("ClockSourceValue"), map.get("STM1NumberValue"), map.get("BearerNumber_Value")
						, map.get("UnitIDValue"), map.get("SlotValue"), map.get("PicValue"), map.get("PortValue")
						, map.get("IVManagement_checkbox"), map.get("AtricaConnected_checkbox"), map.get("CardType_Dropdown_Gigabit"));
				
				APT_IPTransitHelper.get().verify_JuniperVendor_EditInterface("iptransit", DeviceNameValue
						, map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_Network"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
						, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
						, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
						, map.get("EIPAllocation_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
						, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
						, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
						, map.get("EIPAllocation_AvailableBlocksValue"), map.get("Edit_LinkValue"), map.get("Edit_BearerType_value")
						, map.get("Edit_Bandwidth_value"), map.get("Edit_FramingTypeValue"), map.get("Edit_EncapsulationValue")
						, map.get("Edit_VLANIDvalue"), map.get("Edit_BGPCheckbox"), map.get("Edit_BGPTemplate_Dropdownvalue")
						, map.get("Edit_CPEWAN_Value"), map.get("Edit_CPEWANIPv6_Value"), map.get("Edit_DescriptionValue")
						, map.get("Edit_AsCustomerValue"), map.get("Edit_BGPPasswordValue")
						, map.get("Edit_CardType_DropdownValue"), map.get("Edit_ClockSourceValue"), map.get("Edit_STM1NumberValue")
						, map.get("Edit_BearerNumber_Value"), map.get("Edit_UnitIDValue"), map.get("Edit_SlotValue")
						, map.get("Edit_PicValue"), map.get("Edit_PortValue"), map.get("Edit_IVManagement_checkbox")
						, map.get("Edit_AtricaConnected_checkbox"), map.get("CardType_Dropdown_Gigabit")
						, map.get("Edit_IPSubnetIPv6_Value"), map.get("Edit_IPSubnetIPv4_value"));
				
				APT_IPTransitHelper.get().verify_JuniperVendor_AddMultilink("iptransit", DeviceNameValue
						, map.get("Multilink_InterfaceName"), VendorModelValue, map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
						, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
						, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
						, map.get("EIPAllocation_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
						, map.get("ExistingAddressIPv6Value"), map.get("NewAddressRangeIpv6selection")
						, map.get("NewInterfaceAddressRangeIPv6"), map.get("EIPAllocation_IPv6_SubnetSize")
						, map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("Bandwidth_Value")
						, map.get("Encapsulation_Value"), map.get("Multilink_BGPCheckbox"), map.get("BGPTemplate_Value")
						, map.get("CPEWAN_Value"), map.get("CPEWANIPv6_Value"), map.get("Description_Value")
						, map.get("AsCustomer_Value"), map.get("BGPPassword_Value"), map.get("Multilink_ConfigInterface_checkbox")
						, map.get("CheckToAddInterface_Checkbox"), map.get("UnitIDValue"), map.get("SlotValue")
						, map.get("PicValue"), map.get("PortValue"));
			}
			
		logger= ExtentTestManager.startTest ("verifyInterfaceConfigHistory");
		APT_IPTransitHelper.get().verifyInterfaceConfigHistory("iptransit", VendorModelValue);
		
		logger= ExtentTestManager.startTest ("verifySelectInterfaces");
		APT_IPTransitHelper.get().selectInterfacelinkforDevice("iptransit", DeviceNameValue);
		
		if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
			APT_IPTransitHelper.get().SelectInterfacetoremovefromservice("iptransit");
		}else {
			System.out.println("interfaces are not removed");
		}
		
		if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
			APT_IPTransitHelper.get().SelectInterfacetoaddwithservcie("iptransit", map.get("ServiceIdentification"));
		}else {
			System.out.println("Interfaces are not added");
		}
		
		logger= ExtentTestManager.startTest ("VerifyManageService");
		APT_IPTransitHelper.get().verifyManageService("iptransit", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		
		logger= ExtentTestManager.startTest ("Delete Device");
		APT_IPTransitHelper.get().deleteDevice("iptransit", DeviceNameValue);
		
		logger= ExtentTestManager.startTest ("Delete Service");
		APT_IPTransitHelper.get().deleteService("iptransit");
	
	}
	
	
}
	