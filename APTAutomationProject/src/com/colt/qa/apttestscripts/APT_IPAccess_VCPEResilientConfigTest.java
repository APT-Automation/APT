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

public class APT_IPAccess_VCPEResilientConfigTest extends DriverTestcase{
	
public String CustomerName=null;
public static String DeviceNameValue=null;
public static String VendorModelValue=null;
public static String ManagementAddress=null;


	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_IPAccess_VCPEResilientConfig", priority=0)
	public void IPAccessVCPEConfigService(Map<String, String> map) throws Exception {
		
		setup();	
		Login.APT_Login_1(map.get("url for the product"));
		
        String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
        
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
              
              logger= ExtentTestManager.startTest("CreateCustomer - IPAccess VCPE Resilient Configuration");
              APT_IPA_VCPEResilientConfigHelper.get().createcustomer("ipaVcpeResilient", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              ExtentTestManager.endTest();
        
              logger= ExtentTestManager.startTest("selectExistingCustomer - IPAccess VCPE Resilient Configuration"); 
              APT_IPA_VCPEResilientConfigHelper.get().selectCustomertocreateOrder("ipaVcpeResilient",map.get("newCustomer"));
              ExtentTestManager.endTest();
        
        }
        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              logger= ExtentTestManager.startTest("selectExistingCustomer - IPAccess VCPE Resilient Configuration"); 
              APT_IPA_VCPEResilientConfigHelper.get().selectCustomertocreateOrder("ipaVcpeResilient",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
       		ExtentTestManager.endTest();
        }
        
		logger= ExtentTestManager.startTest("verifyCreateorder - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().createorderservice("ipaVcpeResilient", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		ExtentTestManager.endTest();
        
		logger= ExtentTestManager.startTest("verifyservicetypeselection - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().verifyselectservicetype("ipaVcpeResilient", map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"));
		ExtentTestManager.endTest();
        
		logger= ExtentTestManager.startTest("verifyservicecreation - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().verifyservicecreation("ipaVcpeResilient", map.get("ServiceIdentification")
				, map.get("Remarks"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType")
				, map.get("NetworkConfiguration_DrodpwonValue"), map.get("TerminationDate"), map.get("BillingTypevalue")
				, map.get("Email"), map.get("PhoneContact"), map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox")
				, map.get("ServiceBW_Value"), map.get("Package_DropdownValue"), map.get("SnmpNotification_Checkbox")
				, map.get("RouterConfigView_IPv4_Checkbox"), map.get("RouterConfigView_IPv6_Checkbox")
				, map.get("SyslogEventView_Checkbox"), map.get("DeliveryChannel_DropdownValue"), map.get("RouterbasedFirewall_Checkbox")
				, map.get("WithLogicalTunnel_Checkbox"), map.get("QOS_Checkbox"), map.get("ModularMSP_Checkbox")
				, map.get("L2Technology_DropdownValue"), map.get("ManagedService_Checkbox"), map.get("BGPAsNumber_Value")
				, map.get("CustomerDescription_Value"), map.get("SecondaryCircuitOrder_Value")
				, map.get("SecondaryCircuitID_Value"), map.get("SecondaryL2Technology_DropdownValue"), map.get("LoadShared_DropdownValue"));
		ExtentTestManager.endTest();
        
		logger= ExtentTestManager.startTest("verifyCustomerDetailsInformation - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().verifyCustomerDetailsInformation("ipaVcpeResilient", map.get("newCustomerCreation"), map.get("existingCustomerSelection"),
				map.get("newCustomer"),	map.get("existingCustomer"),
				map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"), 
				map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		APT_IPA_VCPEResilientConfigHelper.get().verifyUserDetailsInformation("ipaVcpeResilient", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
		ExtentTestManager.endTest();
        
//		logger= ExtentTestManager.startTest("verifyUserDetailsInformation - IPAccess VCPE Resilient Configuration");
//		APT_IPA_VCPEResilientConfigHelper.get().VerifyUsersPanel("ipaVcpeResilient", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//				map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//				map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"), map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"), 
//				map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), 
//				map.get("SiteOrders_ToBeAvailable"), map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
//		ExtentTestManager.endTest();
        
		logger= ExtentTestManager.startTest("verifyOrderDetailsInformation - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().verifyorderpanel_editorder("ipaVcpeResilient", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
		APT_IPA_VCPEResilientConfigHelper.get().verifyorderpanel_changeorder("ipaVcpeResilient", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
				map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
		ExtentTestManager.endTest();
        
		logger= ExtentTestManager.startTest("verifyServicepanelinviewservicepage - IPAccess VCPE Resilient Configuration");
	  	APT_IPA_VCPEResilientConfigHelper.get().verifyservicepanelInformationinviewservicepage("ipaVcpeResilient"
	  			, map.get("ServiceIdentification"), map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue")
	  			, map.get("Remarks"), map.get("TerminationDate"), map.get("BillingTypevalue"), map.get("Email")
	  			, map.get("PhoneContact"), map.get("ServiceBW_Value"), map.get("SecondaryCircuitOrder_Value")
				, map.get("SecondaryCircuitID_Value"));
		ExtentTestManager.endTest();
        
		logger= ExtentTestManager.startTest("verifyManagementOptionspanel - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().verifyManagementConfigpanels("ipaVcpeResilient"
				, map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox"), map.get("Package_DropdownValue")
				, map.get("SnmpNotification_Checkbox"), map.get("RouterConfigView_IPv4_Checkbox")
				, map.get("RouterConfigView_IPv6_Checkbox"), map.get("SyslogEventView_Checkbox")
				, map.get("DeliveryChannel_DropdownValue"), map.get("RouterbasedFirewall_Checkbox")
				, map.get("WithLogicalTunnel_Checkbox"), map.get("QOS_Checkbox"), map.get("ModularMSP_Checkbox")
				, map.get("L2Technology_DropdownValue"), map.get("ManagedService_Checkbox"), map.get("BGPAsNumber_Value")
				, map.get("CustomerDescription_Value"), map.get("SecondaryL2Technology_DropdownValue"), map.get("LoadShared_DropdownValue"));
		ExtentTestManager.endTest();
        
		logger= ExtentTestManager.startTest("verifyServicepanelLinks - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().verifyEditservice("ipaVcpeResilient", map.get("ServiceIdentification")
				, map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"), map.get("EditRemarks"), map.get("Remarks")
				, map.get("Edit_TerminationDate"), map.get("Edit_BillingTypevalue"), map.get("Edit_Email")
				, map.get("Edit_PhoneContact"), map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_IPGuardian_Checkbox")
				, map.get("Edit_ServiceBW_Value"), map.get("Edit_Package_DropdownValue"), map.get("Edit_SnmpNotification_Checkbox")
				, map.get("Edit_RouterConfigView_IPv4_Checkbox"), map.get("Edit_RouterConfigView_IPv6_Checkbox")
				, map.get("Edit_SyslogEventView_Checkbox"), map.get("Edit_DeliveryChannel_DropdownValue")
				, map.get("Edit_RouterbasedFirewall_Checkbox"), map.get("Edit_WithLogicalTunnel_Checkbox")
				, map.get("Edit_QOS_Checkbox"), map.get("Edit_ModularMSP_Checkbox"), map.get("Edit_L2Technology_DropdownValue")
				, map.get("Edit_ManagedService_Checkbox"), map.get("Edit_CustomerDescription_Value"), map.get("Edit_SecondaryCircuitOrder_Value")
				, map.get("Edit_SecondaryCircuitID_Value"), map.get("Edit_SecondaryL2Technology_DropdownValue"), map.get("Edit_LoadShared_DropdownValue"));
		APT_IPA_VCPEResilientConfigHelper.get().verifyShowNewInfovistaReport("ipaVcpeResilient");
		APT_IPA_VCPEResilientConfigHelper.get().verifyManageSubnets("ipaVcpeResilient");
		APT_IPA_VCPEResilientConfigHelper.get().verifyDump("ipaVcpeResilient");
		ExtentTestManager.endTest();
        
		logger= ExtentTestManager.startTest("addExistingPEDevice - IPAccess VCPE Resilient Configuration");
		
		APT_IPA_VCPEResilientConfigHelper.get().addExistingPEDevice("ipaVcpeResilient", map.get("ExistingDeviceName"));
		APT_IPA_VCPEResilientConfigHelper.get().verifyExistingDevice_ViewDevicedetails("ipaVcpeResilient", map.get("ExistingDeviceName"));
		APT_IPA_VCPEResilientConfigHelper.get().deleteExistingDevice("ipaVcpeResilient", map.get("ExistingDeviceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("addNewPEDevice - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().navigateToAddNewDevicepage("ipaVcpeResilient");
		APT_IPA_VCPEResilientConfigHelper.get().addNewPEDevice("ipaVcpeResilient", map.get("DeviceName"), map.get("DeviceType"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
													map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
													map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
													map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"),
													map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
													map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
													map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		APT_IPA_VCPEResilientConfigHelper.get().verifyViewpage_Devicedetails("ipaVcpeResilient", map.get("DeviceName"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
													map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
													map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
													map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"),
													map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
													map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
													map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		APT_IPA_VCPEResilientConfigHelper.get().verifyViewDevicepage_Links("ipaVcpeResilient");
		APT_IPA_VCPEResilientConfigHelper.get().verifyEditDevice("ipaVcpeResilient", map.get("editdeviceName"), map.get("editVendorModel"), map.get("editTelnet"), map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"), map.get("editSnmpv3UsernameNewValue"),
													map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
													map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
													map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
													map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("addSecondaryPEDevice - IPAccess VCPE Resilient Configuration");
		
		APT_IPA_VCPEResilientConfigHelper.get().navigateToAddNewDevicepage("ipaVcpeResilient");
		APT_IPA_VCPEResilientConfigHelper.get().addNewPEDevice("ipaVcpeResilient", map.get("SecondaryPE_DeviceName"), map.get("SecondaryPE_DeviceType")
				, map.get("SecondaryPE_VendorModel"), map.get("SecondaryPE_Telnet"), map.get("SecondaryPE_SSH"), map.get("SecondaryPE_Snmp2C")
				, map.get("SecondaryPE_SnmPro"), map.get("SecondaryPE_Snmprw"), map.get("SecondaryPE_SnmProNewValue")
				, map.get("SecondaryPE_SnmprwNewValue"), map.get("SecondaryPE_Snmp3"), map.get("SecondaryPE_Snmpv3Username")
				,map.get("SecondaryPE_Snmpv3Authpassword"), map.get("SecondaryPE_Snmpv3Privpassword"), map.get("SecondaryPE_Snmpv3UsernameNewValue")
				, map.get("SecondaryPE_Snmpv3AuthpasswordNewValue"), map.get("SecondaryPE_Snmpv3PrivpasswordNewValue"),map.get("SecondaryPE_Country")
				, map.get("SecondaryPE_ManagementAddress"), map.get("SecondaryPE_ExistingCity"), map.get("SecondaryPE_ExistingCityValue")
				, map.get("SecondaryPE_ExistingSite"),map.get("SecondaryPE_Existing SiteValue"), map.get("SecondaryPE_ExistingPremise")
				, map.get("SecondaryPE_Existing PremiseValue"), map.get("SecondaryPE_NewCity"), map.get("SecondaryPE_NewCityName")
				, map.get("SecondaryPE_NewCityCode"), map.get("SecondaryPE_NewSiteName"),map.get("SecondaryPE_NewSiteCode")
				, map.get("SecondaryPE_NewPremiseName"), map.get("SecondaryPE_NewPremiseCode"), map.get("SecondaryPE_NewSite")
				, map.get("SecondaryPE_NewPremise"));
		
		APT_IPA_VCPEResilientConfigHelper.get().verifyViewpage_Devicedetails("ipaVcpeResilient", map.get("SecondaryPE_DeviceName")
				, map.get("SecondaryPE_VendorModel"), map.get("SecondaryPE_Telnet"), map.get("SecondaryPE_SSH"), map.get("SecondaryPE_Snmp2C")
				, map.get("SecondaryPE_SnmPro"), map.get("SecondaryPE_Snmprw"), map.get("SecondaryPE_SnmProNewValue")
				, map.get("SecondaryPE_SnmprwNewValue"), map.get("SecondaryPE_Snmp3"), map.get("SecondaryPE_Snmpv3Username")
				,map.get("SecondaryPE_Snmpv3Authpassword"), map.get("SecondaryPE_Snmpv3Privpassword"), map.get("SecondaryPE_Snmpv3UsernameNewValue")
				, map.get("SecondaryPE_Snmpv3AuthpasswordNewValue"), map.get("SecondaryPE_Snmpv3PrivpasswordNewValue"),map.get("SecondaryPE_Country")
				, map.get("SecondaryPE_ManagementAddress"), map.get("SecondaryPE_ExistingCity"), map.get("SecondaryPE_ExistingCityValue")
				, map.get("SecondaryPE_ExistingSite"),map.get("SecondaryPE_Existing SiteValue"), map.get("SecondaryPE_ExistingPremise")
				, map.get("SecondaryPE_Existing PremiseValue"), map.get("SecondaryPE_NewCity"), map.get("SecondaryPE_NewCityName")
				, map.get("SecondaryPE_NewCityCode"), map.get("SecondaryPE_NewSiteName"),map.get("SecondaryPE_NewSiteCode")
				, map.get("SecondaryPE_NewPremiseName"), map.get("SecondaryPE_NewPremiseCode"), map.get("SecondaryPE_NewSite")
				, map.get("SecondaryPE_NewPremise"));
		
		APT_IPA_VCPEResilientConfigHelper.get().verifyViewDevicepage_Links("ipaVcpeResilient");
		APT_IPA_VCPEResilientConfigHelper.get().verifyEditDevice("ipaVcpeResilient", map.get("EditSecondaryPE_deviceName")
				, map.get("EditSecondaryPE_VendorModel"), map.get("EditSecondaryPE_Telnet"), map.get("EditSecondaryPE_SSH")
				, map.get("EditSecondaryPE_Snmp2C"), map.get("EditSecondaryPE_Snmp3"), map.get("EditSecondaryPE_SnmProNewValue")
				, map.get("EditSecondaryPE_SnmprwNewValue"), map.get("EditSecondaryPE_Snmpv3UsernameNewValue")
				, map.get("EditSecondaryPE_Snmpv3AuthpasswordNewValue"), map.get("EditSecondaryPE_Snmpv3PrivpasswordNewValue")
				,map.get("EditSecondaryPE_ManagementAddress"), map.get("EditSecondaryPE_Country"), map.get("EditSecondaryPE_ExistingCity")
				,map.get("EditSecondaryPE_ExistingCityValue"), map.get("EditSecondaryPE_ExistingSite"), map.get("EditSecondaryPE_ExistingSiteValue")
				, map.get("EditSecondaryPE_ExistingPremise"), map.get("EditSecondaryPE_ExistingPremiseValue"),map.get("EditSecondaryPE_NewCity")
				, map.get("EditSecondaryPE_NewSite"), map.get("EditSecondaryPE_NewPremise"), map.get("EditSecondaryPE_NewCityName")
				, map.get("EditSecondaryPE_NewCityCode"), map.get("EditSecondaryPE_NewSiteName"),map.get("EditSecondaryPE_NewSiteCode")
				, map.get("EditSecondaryPE_NewPremiseName"), map.get("EditSecondaryPE_NewPremiseCode"));
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("verifyRouterTools - IPAccess VCPE Resilient Configuration");
        
		APT_IPA_VCPEResilientConfigHelper.get().navigatetoViewDevicepage("ipaVcpeResilient", map.get("DeviceName"), map.get("editdeviceName"));
		DeviceNameValue= APT_IPA_VCPEResilientConfigHelper.get().DeviceName("ipaVcpeResilient");
		VendorModelValue= APT_IPA_VCPEResilientConfigHelper.get().VendorModel("ipaVcpeResilient");
		ManagementAddress= APT_IPA_VCPEResilientConfigHelper.get().ManagementAddress("ipaVcpeResilient");
		if(VendorModelValue.contains("Cisco"))
		{
		APT_IPA_VCPEResilientConfigHelper.get().verify_Cisco_RouterTools("ipaVcpeResilient", map.get("command_ipv4"), map.get("command_ipv6"), ManagementAddress,
					map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
		
		}
		else
		{
		APT_IPA_VCPEResilientConfigHelper.get().verify_Juniper_RouterTools("ipaVcpeResilient", map.get("command_ipv4"), ManagementAddress,
						map.get("vrf_Ipv4"));
		}
		ExtentTestManager.endTest();
		
			logger= ExtentTestManager.startTest("verifyRoutesPanel - IPAccess VCPE Resilient Configuration");
			
			APT_IPA_VCPEResilientConfigHelper.get().verify_JuniperVendor_AddInterface("ipaVcpeResilient"
					, map.get("ConfigureInterface_Checkbox"), map.get("NetworkIPv4_FirstDropdownValue")
					, map.get("NetworkIPv4_SecondDropdownValue")
					, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection"), map.get("NewAddressRangeIpv4selection")
					, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("NewInterfaceAddressRange_FirstfieldValue")
					, map.get("NewInterfaceAddressRange_SecondfieldValue"), map.get("VRRPIP_FirstValue")
					, map.get("VRRPPassword_FirstValue"), map.get("VRRPIP_SecondValue"), map.get("VRRPPassword_SecondValue")
					, map.get("ExistingAddressRangeIPv6selection"), map.get("NewAddressRangeIpv6selection"), map.get("EIPAllocation_IPv6_SubnetSize")
					, map.get("EIPAllocation_AvailableBlocksValue"), map.get("NewInterfaceAddressRangeIPv6_FirstfieldValue")
					, map.get("NewInterfaceAddressRangeIPv6_SecondfieldValue"), map.get("VRRPIPV6_FirstValue")
					, map.get("VRRPIPV6Password_FirstValue"), map.get("VRRPIPV6_SecondValue"), map.get("VRRPIPV6Password_SecondValue")
					, map.get("existingWANAddressRangeIPv4selection"), map.get("NewWANAddressRangeIPv4selection")
					, map.get("NewWANInterfaceAddressRange_Value"), map.get("existingWANAddressRangeIPv6selection")
					, map.get("NewWANAddressRangeIPv6selection"), map.get("NewWANInterfaceAddressRangeIPv6_Value")
					, map.get("CardType_DropdownValue"), map.get("STM1NumberValue"), map.get("UnitIDValue"), map.get("SlotValue")
					, map.get("PicValue"), map.get("PortValue"), map.get("IVManagement_checkbox"), map.get("AtricaConnected_checkbox")
					, map.get("LinkValue"), map.get("BearerType_Value"), map.get("Bandwidth_Value"), map.get("Encapsulation_Value")
					, map.get("VLANID_Value"), map.get("CircuitRole_DropdownValue"), map.get("MDNameFormat_Value")
					, map.get("DomainLevel_DropdownValue"));
			
			APT_IPA_VCPEResilientConfigHelper.get().navigatetoEditInterfacepage("ipaVcpeResilient", DeviceNameValue);
			
			APT_IPA_VCPEResilientConfigHelper.get().verify_JuniperVendor_EditInterface("ipaVcpeResilient"
					, map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_NetworkIPv4_FirstDropdownValue")
					, map.get("Edit_NetworkIPv4_SecondDropdownValue")
					, map.get("EIPAllocation_City"), map.get("Edit_ExistingAddressRangeIPv4selection"), map.get("Edit_NewAddressRangeIpv4selection")
					, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("Edit_NewInterfaceAddressRange_FirstfieldValue")
					, map.get("Edit_NewInterfaceAddressRange_SecondfieldValue"), map.get("Edit_VRRPIP_FirstValue")
					, map.get("Edit_VRRPPassword_FirstValue"), map.get("Edit_VRRPIP_SecondValue"), map.get("Edit_VRRPPassword_SecondValue")
					, map.get("Edit_ExistingAddressRangeIPv6selection"), map.get("Edit_NewAddressRangeIpv6selection"), map.get("EIPAllocation_IPv6_SubnetSize")
					, map.get("EIPAllocation_AvailableBlocksValue"), map.get("Edit_NewInterfaceAddressRangeIPv6_FirstfieldValue")
					, map.get("Edit_NewInterfaceAddressRangeIPv6_SecondfieldValue"), map.get("Edit_VRRPIPV6_FirstValue")
					, map.get("Edit_VRRPIPV6Password_FirstValue"), map.get("Edit_VRRPIPV6_SecondValue"), map.get("Edit_VRRPIPV6Password_SecondValue")
					, map.get("existingWANAddressRangeIPv4selection"), map.get("NewWANAddressRangeIPv4selection")
					, map.get("Edit_NewWANInterfaceAddressRange_Value"), map.get("existingWANAddressRangeIPv6selection")
					, map.get("NewWANAddressRangeIPv6selection"), map.get("Edit_NewWANInterfaceAddressRangeIPv6_Value")
					, map.get("Edit_CardType_DropdownValue"), map.get("Edit_STM1NumberValue"), map.get("Edit_UnitIDValue"), map.get("Edit_SlotValue")
					, map.get("Edit_PicValue"), map.get("Edit_PortValue"), map.get("Edit_IVManagement_checkbox"), map.get("Edit_AtricaConnected_checkbox")
					, map.get("Edit_LinkValue"), map.get("Edit_BearerType_Value"), map.get("Edit_Bandwidth_Value"), map.get("Edit_Encapsulation_Value")
					, map.get("Edit_VLANID_Value"), map.get("Edit_CircuitRole_DropdownValue"), map.get("Edit_MDNameFormat_Value")
					, map.get("Edit_DomainLevel_DropdownValue"));
			ExtentTestManager.endTest();
		
		if(VendorModelValue.contains("Juniper")) {
		logger= ExtentTestManager.startTest("verifyInterfaceConfigHistory - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().navigatetoViewDevicepage("ipaVcpeResilient", map.get("DeviceName"), map.get("editdeviceName"));
		APT_IPA_VCPEResilientConfigHelper.get().verifyInterfaceConfigHistory("ipaVcpeResilient", VendorModelValue);
		ExtentTestManager.endTest();
		}
			
		logger= ExtentTestManager.startTest("verifySelectInterfaces - IPAccess VCPE Resilient Configuration");
		
		APT_IPA_VCPEResilientConfigHelper.get().selectInterfacelinkforDevice("ipaVcpeResilient", DeviceNameValue);
		
		if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
			APT_IPA_VCPEResilientConfigHelper.get().SelectInterfacetoremovefromservice("ipaVcpeResilient");
		}else {
			System.out.println("interfaces are not removed");
		}
		
		if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
			APT_IPA_VCPEResilientConfigHelper.get().SelectInterfacetoaddwithservcie("ipaVcpeResilient");
		}else {
			System.out.println("Interfaces are not added");
		}
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("verifyL2Technology - IPAccess VCPE Resilient Configuration");
		
		String L2Technology= map.get("L2Technology_DropdownValue");
		String SecondaryL2Technology= map.get("SecondaryL2Technology_DropdownValue");
		String CircuitValue= map.get("AddL2Circuit_DropdownValue");
		if(L2Technology.equalsIgnoreCase("Actelis")) {
			if(SecondaryL2Technology.equalsIgnoreCase("Actelis")) {
				
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary"))
				{
					
					APT_IPA_VCPEResilientConfigHelper.get().verifySecondaryL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_NewCity"), map.get("L2Circuit_CityName"), map.get("L2Circuit_CityCode")
							, map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue"), map.get("L2Circuit_NewSite")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_SiteCode"), map.get("L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_SecondaryL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_CityName"), map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_VLANIDValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_SecondaryL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"), map.get("Edit_L2Circuit_DeviceCountry")
							, map.get("Edit_L2Circuit_ExistingCity"), map.get("Edit_L2Circuit_ExistingCityValue"), map.get("Edit_L2Circuit_NewCity")
							, map.get("Edit_L2Circuit_CityName"), map.get("Edit_L2Circuit_CityCode")
							, map.get("Edit_L2Circuit_ExistingSite"), map.get("Edit_L2Circuit_ExistingSiteValue"), map.get("Edit_L2Circuit_NewSite")
							, map.get("Edit_L2Circuit_SiteName"), map.get("Edit_L2Circuit_SiteCode"), map.get("Edit_L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				else {
					APT_IPA_VCPEResilientConfigHelper.get().verifySecondaryL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_NewCity"), map.get("L2Circuit_CityName"), map.get("L2Circuit_CityCode")
							, map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue"), map.get("L2Circuit_NewSite")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_SiteCode"), map.get("L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_SecondaryL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_CityName"), map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_VLANIDValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_SecondaryL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"), map.get("Edit_L2Circuit_DeviceCountry")
							, map.get("Edit_L2Circuit_ExistingCity"), map.get("Edit_L2Circuit_ExistingCityValue"), map.get("Edit_L2Circuit_NewCity")
							, map.get("Edit_L2Circuit_CityName"), map.get("Edit_L2Circuit_CityCode")
							, map.get("Edit_L2Circuit_ExistingSite"), map.get("Edit_L2Circuit_ExistingSiteValue"), map.get("Edit_L2Circuit_NewSite")
							, map.get("Edit_L2Circuit_SiteName"), map.get("Edit_L2Circuit_SiteCode"), map.get("Edit_L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				
			}
			else if(SecondaryL2Technology.equalsIgnoreCase("Atrica")) {
				
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary"))
				{
					
					APT_IPA_VCPEResilientConfigHelper.get().verifySecondaryL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_NewCity"), map.get("L2Circuit_CityName"), map.get("L2Circuit_CityCode")
							, map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue"), map.get("L2Circuit_NewSite")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_SiteCode"), map.get("L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_SecondaryL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_CityName"), map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_VLANIDValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_SecondaryL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"), map.get("Edit_L2Circuit_DeviceCountry")
							, map.get("Edit_L2Circuit_ExistingCity"), map.get("Edit_L2Circuit_ExistingCityValue"), map.get("Edit_L2Circuit_NewCity")
							, map.get("Edit_L2Circuit_CityName"), map.get("Edit_L2Circuit_CityCode")
							, map.get("Edit_L2Circuit_ExistingSite"), map.get("Edit_L2Circuit_ExistingSiteValue"), map.get("Edit_L2Circuit_NewSite")
							, map.get("Edit_L2Circuit_SiteName"), map.get("Edit_L2Circuit_SiteCode"), map.get("Edit_L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				else
				{
					APT_IPA_VCPEResilientConfigHelper.get().verifyL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyViewL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteA("ipaVcpeResilient", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
							, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
							, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
							, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteB("ipaVcpeResilient", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
							, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
							, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
							, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEditL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"));
				}
				
			}
			else if((SecondaryL2Technology.equalsIgnoreCase("Overture"))) {
				
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary"))
				{
					
					APT_IPA_VCPEResilientConfigHelper.get().verifySecondaryL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_NewCity"), map.get("L2Circuit_CityName"), map.get("L2Circuit_CityCode")
							, map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue"), map.get("L2Circuit_NewSite")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_SiteCode"), map.get("L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_SecondaryL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_CityName"), map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_VLANIDValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_SecondaryL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"), map.get("Edit_L2Circuit_DeviceCountry")
							, map.get("Edit_L2Circuit_ExistingCity"), map.get("Edit_L2Circuit_ExistingCityValue"), map.get("Edit_L2Circuit_NewCity")
							, map.get("Edit_L2Circuit_CityName"), map.get("Edit_L2Circuit_CityCode")
							, map.get("Edit_L2Circuit_ExistingSite"), map.get("Edit_L2Circuit_ExistingSiteValue"), map.get("Edit_L2Circuit_NewSite")
							, map.get("Edit_L2Circuit_SiteName"), map.get("Edit_L2Circuit_SiteCode"), map.get("Edit_L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				else
				{
				
					APT_IPA_VCPEResilientConfigHelper.get().verifyOvertureL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("InterfaceSpeed_DropdownValue"), map.get("ENNICheckbox")
							, map.get("CircuitRole_DropdownValue"));
				APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
				APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_OvertureL2Technology("ipaVcpeResilient"
						, map.get("Add_L2Circuit_RemarkValue"), map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
						, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
						, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
						, map.get("Edit_InterfaceSpeed_DropdownValue"), map.get("Edit_ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
			}
			}
			else if(SecondaryL2Technology.equalsIgnoreCase("Accedian")) {
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary"))
				{
					
					APT_IPA_VCPEResilientConfigHelper.get().verifySecondaryL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_NewCity"), map.get("L2Circuit_CityName"), map.get("L2Circuit_CityCode")
							, map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue"), map.get("L2Circuit_NewSite")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_SiteCode"), map.get("L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_SecondaryL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_CityName"), map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_VLANIDValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_SecondaryL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"), map.get("Edit_L2Circuit_DeviceCountry")
							, map.get("Edit_L2Circuit_ExistingCity"), map.get("Edit_L2Circuit_ExistingCityValue"), map.get("Edit_L2Circuit_NewCity")
							, map.get("Edit_L2Circuit_CityName"), map.get("Edit_L2Circuit_CityCode")
							, map.get("Edit_L2Circuit_ExistingSite"), map.get("Edit_L2Circuit_ExistingSiteValue"), map.get("Edit_L2Circuit_NewSite")
							, map.get("Edit_L2Circuit_SiteName"), map.get("Edit_L2Circuit_SiteCode"), map.get("Edit_L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				else
				{
					APT_IPA_VCPEResilientConfigHelper.get().verifyAccedianL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_AccedianL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue")
							, map.get("CircuitType_DropdownValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("ENNICheckbox"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_AccedianL2Technology("ipaVcpeResilient"
							, map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("Edit_ENNICheckbox"));
				}
				
			}
		}
		else if(L2Technology.equalsIgnoreCase("Atrica")) {
			if(SecondaryL2Technology.equalsIgnoreCase("Actelis")) {
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary"))
				{
					
					APT_IPA_VCPEResilientConfigHelper.get().verifyL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyViewL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteA("ipaVcpeResilient", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
							, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
							, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
							, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteB("ipaVcpeResilient", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
							, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
							, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
							, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEditL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"));
				}
				else {
				APT_IPA_VCPEResilientConfigHelper.get().verifySecondaryL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
						, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
						, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
						, map.get("L2Circuit_NewCity"), map.get("L2Circuit_CityName"), map.get("L2Circuit_CityCode")
						, map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue"), map.get("L2Circuit_NewSite")
						, map.get("L2Circuit_SiteName"), map.get("L2Circuit_SiteCode"), map.get("L2Circuit_VLANIDValue")
						, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
				APT_IPA_VCPEResilientConfigHelper.get().verifyView_SecondaryL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
						, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
						, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
						, map.get("L2Circuit_CityName"), map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue")
						, map.get("L2Circuit_SiteName"), map.get("L2Circuit_VLANIDValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
				APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_SecondaryL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
						, map.get("Edit_CircuitReference_Value"), map.get("Edit_L2Circuit_DeviceCountry")
						, map.get("Edit_L2Circuit_ExistingCity"), map.get("Edit_L2Circuit_ExistingCityValue"), map.get("Edit_L2Circuit_NewCity")
						, map.get("Edit_L2Circuit_CityName"), map.get("Edit_L2Circuit_CityCode")
						, map.get("Edit_L2Circuit_ExistingSite"), map.get("Edit_L2Circuit_ExistingSiteValue"), map.get("Edit_L2Circuit_NewSite")
						, map.get("Edit_L2Circuit_SiteName"), map.get("Edit_L2Circuit_SiteCode"), map.get("Edit_L2Circuit_VLANIDValue")
						, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				
				}
			else if(SecondaryL2Technology.equalsIgnoreCase("Atrica")) {
			
				//Verify Primary L2Technology
				
				if(CircuitValue.equalsIgnoreCase("Primary"))
				{
					
					APT_IPA_VCPEResilientConfigHelper.get().verifyL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyViewL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteA("ipaVcpeResilient", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
							, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
							, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
							, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteB("ipaVcpeResilient", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
							, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
							, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
							, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEditL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"));
				}
				else {
					APT_IPA_VCPEResilientConfigHelper.get().verifyL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyViewL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteA("ipaVcpeResilient", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
							, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
							, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
							, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteB("ipaVcpeResilient", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
							, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
							, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
							, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEditL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"));
				}
				
			}
			else if(SecondaryL2Technology.equalsIgnoreCase("Overture")) {
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary"))
				{
					
					APT_IPA_VCPEResilientConfigHelper.get().verifyL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyViewL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteA("ipaVcpeResilient", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
							, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
							, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
							, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteB("ipaVcpeResilient", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
							, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
							, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
							, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEditL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"));
				}
				else {
					APT_IPA_VCPEResilientConfigHelper.get().verifyOvertureL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("InterfaceSpeed_DropdownValue"), map.get("ENNICheckbox")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_secondaryL2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_OvertureL2Technology("ipaVcpeResilient"
							, map.get("Add_L2Circuit_RemarkValue"), map.get("Edit_Add_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("Edit_InterfaceSpeed_DropdownValue"), map.get("Edit_ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				
			}
			else if(SecondaryL2Technology.equalsIgnoreCase("Accedian")) {
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary"))
				{
					
					APT_IPA_VCPEResilientConfigHelper.get().verifyL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyViewL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteA("ipaVcpeResilient", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
							, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
							, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
							, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteB("ipaVcpeResilient", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
							, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
							, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
							, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEditL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"));
				}
				else 
				{
					APT_IPA_VCPEResilientConfigHelper.get().verifyAccedianL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_AccedianL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue")
							, map.get("CircuitType_DropdownValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("ENNICheckbox"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_AccedianL2Technology("ipaVcpeResilient"
							, map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("Edit_ENNICheckbox"));
				}
				
				}
		}
		else if(L2Technology.equalsIgnoreCase("Overture")) {
			if((SecondaryL2Technology.equalsIgnoreCase("Actelis")) || (SecondaryL2Technology.equalsIgnoreCase("Atrica"))) {
				
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary")) {
				
					APT_IPA_VCPEResilientConfigHelper.get().verifyOvertureL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("InterfaceSpeed_DropdownValue"), map.get("ENNICheckbox")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_OvertureL2Technology("ipaVcpeResilient"
							, map.get("Add_L2Circuit_RemarkValue"), map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("Edit_InterfaceSpeed_DropdownValue"), map.get("Edit_ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				else {
					APT_IPA_VCPEResilientConfigHelper.get().verifyL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyViewL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteA("ipaVcpeResilient", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
							, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
							, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
							, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteB("ipaVcpeResilient", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
							, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
							, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
							, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEditL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"));
				}
				
			}
			
			else if(SecondaryL2Technology.equalsIgnoreCase("Overture")) {
				
				//Verify Primary L2Technology
				if((CircuitValue.equalsIgnoreCase("Primary")) || CircuitValue.equalsIgnoreCase("Secondary")) {
					
					APT_IPA_VCPEResilientConfigHelper.get().verifyOvertureL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("InterfaceSpeed_DropdownValue"), map.get("ENNICheckbox")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_OvertureL2Technology("ipaVcpeResilient"
							, map.get("Add_L2Circuit_RemarkValue"), map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("Edit_InterfaceSpeed_DropdownValue"), map.get("Edit_ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				
				}
			else if(SecondaryL2Technology.equalsIgnoreCase("Accedian")) {
				
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary")) {
					
					APT_IPA_VCPEResilientConfigHelper.get().verifyOvertureL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("InterfaceSpeed_DropdownValue"), map.get("ENNICheckbox")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_OvertureL2Technology("ipaVcpeResilient"
							, map.get("Add_L2Circuit_RemarkValue"), map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("Edit_InterfaceSpeed_DropdownValue"), map.get("Edit_ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				else 
				{
					APT_IPA_VCPEResilientConfigHelper.get().verifyAccedianL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_AccedianL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue")
							, map.get("CircuitType_DropdownValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("ENNICheckbox"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_AccedianL2Technology("ipaVcpeResilient"
							, map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("Edit_ENNICheckbox"));
				}
				
			}
			}
		else if(L2Technology.equalsIgnoreCase("Accedian")) {
			if(SecondaryL2Technology.equalsIgnoreCase("Actelis")) {
				
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary")){
					APT_IPA_VCPEResilientConfigHelper.get().verifyAccedianL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_AccedianL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue")
							, map.get("CircuitType_DropdownValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("ENNICheckbox"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_AccedianL2Technology("ipaVcpeResilient"
							, map.get("Edit_Add_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("Edit_ENNICheckbox"));
				}
				else {
					APT_IPA_VCPEResilientConfigHelper.get().verifySecondaryL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_NewCity"), map.get("L2Circuit_CityName"), map.get("L2Circuit_CityCode")
							, map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue"), map.get("L2Circuit_NewSite")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_SiteCode"), map.get("L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_SecondaryL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value")
							, map.get("L2Circuit_DeviceCountry"), map.get("L2Circuit_ExistingCity"), map.get("L2Circuit_ExistingCityValue")
							, map.get("L2Circuit_CityName"), map.get("L2Circuit_ExistingSite"), map.get("L2Circuit_ExistingSiteValue")
							, map.get("L2Circuit_SiteName"), map.get("L2Circuit_VLANIDValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_SecondaryL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"), map.get("Edit_L2Circuit_DeviceCountry")
							, map.get("Edit_L2Circuit_ExistingCity"), map.get("Edit_L2Circuit_ExistingCityValue"), map.get("Edit_L2Circuit_NewCity")
							, map.get("Edit_L2Circuit_CityName"), map.get("Edit_L2Circuit_CityCode")
							, map.get("Edit_L2Circuit_ExistingSite"), map.get("Edit_L2Circuit_ExistingSiteValue"), map.get("Edit_L2Circuit_NewSite")
							, map.get("Edit_L2Circuit_SiteName"), map.get("Edit_L2Circuit_SiteCode"), map.get("Edit_L2Circuit_VLANIDValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					}
				
			}
			else if(SecondaryL2Technology.equalsIgnoreCase("Atrica")) {
				
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary")){
					APT_IPA_VCPEResilientConfigHelper.get().verifyAccedianL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_AccedianL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue")
							, map.get("CircuitType_DropdownValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("ENNICheckbox"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_AccedianL2Technology("ipaVcpeResilient"
							, map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("Edit_ENNICheckbox"));
				}
				else {
					APT_IPA_VCPEResilientConfigHelper.get().verifyL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyViewL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("LinkValue"), map.get("Edit_LinkValue"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteA("ipaVcpeResilient", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
							, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
							, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
							, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().verifySiteB("ipaVcpeResilient", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
							, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
							, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
							, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
							, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEditL2Circuit("ipaVcpeResilient", map.get("Edit_L2Circuit_RemarkValue")
							, map.get("Edit_CircuitReference_Value"));
				}
			}
			else if(SecondaryL2Technology.equalsIgnoreCase("Overture")) {
				
				//Verify Primary L2Technology
				if(CircuitValue.equalsIgnoreCase("Primary")){
					APT_IPA_VCPEResilientConfigHelper.get().verifyAccedianL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_AccedianL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue")
							, map.get("CircuitType_DropdownValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("ENNICheckbox"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_AccedianL2Technology("ipaVcpeResilient"
							, map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("Edit_ENNICheckbox"));
				}
				else
				{
					APT_IPA_VCPEResilientConfigHelper.get().verifyOvertureL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("InterfaceSpeed_DropdownValue"), map.get("ENNICheckbox")
							, map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_OvertureL2Technology("ipaVcpeResilient"
							, map.get("Add_L2Circuit_RemarkValue"), map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("Edit_InterfaceSpeed_DropdownValue"), map.get("Edit_ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
				}
				
			}
			else if(SecondaryL2Technology.equalsIgnoreCase("Accedian")) {
				//Verify Primary L2Technology
				if((CircuitValue.equalsIgnoreCase("Primary")) || (CircuitValue.equalsIgnoreCase("Secondary"))){
					APT_IPA_VCPEResilientConfigHelper.get().verifyAccedianL2Technology("ipaVcpeResilient", map.get("AddL2Circuit_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
							, map.get("ENNICheckbox"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("CircuitRole_DropdownValue"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyView_AccedianL2Circuit("ipaVcpeResilient", map.get("L2Technology_DropdownValue")
							, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
							, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue")
							, map.get("CircuitType_DropdownValue"), map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("ENNICheckbox"));
					APT_IPA_VCPEResilientConfigHelper.get().clickOn_L2Checkbox("ipaVcpeResilient");
					APT_IPA_VCPEResilientConfigHelper.get().verifyEdit_AccedianL2Technology("ipaVcpeResilient"
							, map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value")
							, map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
							, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue")
							, map.get("LinkValue"), map.get("Edit_LinkValue"), map.get("Edit_ENNICheckbox"));
				}
			}
		}
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("VerifyManageService - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().verifyManageService("ipaVcpeResilient", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("Delete Device - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().deleteDevice("ipaVcpeResilient");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("Delete Service - IPAccess VCPE Resilient Configuration");
		APT_IPA_VCPEResilientConfigHelper.get().deleteService("ipaVcpeResilient");
		ExtentTestManager.endTest();
		
	}
	
	
}
	