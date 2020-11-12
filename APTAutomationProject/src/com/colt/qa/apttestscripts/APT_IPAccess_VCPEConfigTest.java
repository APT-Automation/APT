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

public class APT_IPAccess_VCPEConfigTest extends DriverTestcase{
	
public String CustomerName=null;
public static String DeviceNameValue=null;
public static String VendorModelValue=null;
public static String ManagementAddress=null;

	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_IPAccess_VCPEConfig", priority=0)
	public void IPAccessVCPEConfigService(Map<String, String> map) throws Exception {
		
		setup();
		
		Login.APT_Login_1(map.get("url for the product"));
		
        String newCustomerName=map.get("newCustomerCreation");
        String existingCustomer=map.get("existingCustomerSelection");
        
        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
              
              logger= ExtentTestManager.startTest("CreateCustomer - IPAccess VCPE Configuration");
              APT_IPAccess_VCPEConfigHelper.get().createcustomer("ipaVCPE", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                          map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                          map.get("Fax"));
              CustomerName=map.get("newCustomer");
              ExtentTestManager.endTest();
              
              logger= ExtentTestManager.startTest("selectExistingCustomer - IPAccess VCPE Configuration"); 
              APT_IPAccess_VCPEConfigHelper.get().selectCustomertocreateOrder("ipaVCPE",map.get("newCustomer"));
              ExtentTestManager.endTest();
              
        }
        else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
              
              logger= ExtentTestManager.startTest("selectExistingCustomer - IPAccess VCPE Configuration"); 
              APT_IPAccess_VCPEConfigHelper.get().selectCustomertocreateOrder("ipaVCPE",map.get("existingCustomer"));
              CustomerName=map.get("existingCustomer");
              ExtentTestManager.endTest();
        }
        
		logger= ExtentTestManager.startTest("verifyCreateorder - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().createorderservice("ipaVCPE", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyservicetypeselection - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().verifyselectservicetype("ipaVCPE", map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyservicecreation - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().verifyservicecreation("ipaVCPE", map.get("ServiceIdentification"), map.get("Remarks")
				, map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ServiceType")
				, map.get("NetworkConfiguration_DrodpwonValue"), map.get("TerminationDate"), map.get("BillingTypevalue")
				, map.get("Email"), map.get("PhoneContact"), map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox")
				, map.get("ServiceBW_Value"), map.get("Package_DropdownValue"), map.get("SnmpNotification_Checkbox")
				, map.get("RouterConfigView_IPv4_Checkbox"), map.get("RouterConfigView_IPv6_Checkbox")
				, map.get("SyslogEventView_Checkbox"), map.get("DeliveryChannel_DropdownValue")
				, map.get("RouterbasedFirewall_Checkbox"), map.get("WithLogicalTunnel_Checkbox"), map.get("QOS_Checkbox")
				, map.get("ModularMSP_Checkbox"), map.get("L2Technology_DropdownValue"), map.get("RoutingProtocol_DropdownValue")
				, map.get("ManagedService_Checkbox"), map.get("BGPAsNumber_Value"), map.get("CustomerDescription_Value"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyCustomerDetailsInformation - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().verifyCustomerDetailsInformation("ipaVCPE", map.get("newCustomerCreation"), map.get("existingCustomerSelection"),
				map.get("newCustomer"),	map.get("existingCustomer"),
				map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"), 
				map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		APT_IPAccess_VCPEConfigHelper.get().verifyUserDetailsInformation("ipaVCPE", map.get("LoginColumn"), map.get("NameColumn"), map.get("EmailColumn"), map.get("RolesColumn"), map.get("AddressColumn"), map.get("ResourceColumn"));
		ExtentTestManager.endTest();
		
		//logger= ExtentTestManager.startTest("verifyUserDetailsInformation - IPAccess VCPE Configuration");
		//APT_IPAccess_VCPEConfigHelper.get().VerifyUsersPanel("ipaVCPE", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
		//		map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected"),map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
		//		map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"), map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"), 
		//		map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), 
		//		map.get("SiteOrders_ToBeAvailable"), map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
		//ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyOrderDetailsInformation - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().verifyorderpanel_editorder("ipaVCPE", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
		APT_IPAccess_VCPEConfigHelper.get().verifyorderpanel_changeorder("ipaVCPE", map.get("ChangeOrder_newOrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
				map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyServicepanelinviewservicepage - IPAccess VCPE Configuration");
	  	APT_IPAccess_VCPEConfigHelper.get().verifyservicepanelInformationinviewservicepage("ipaVCPE", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("NetworkConfiguration_DrodpwonValue"), map.get("Remarks"), map.get("TerminationDate"), map.get("BillingTypevalue"), map.get("Email"), map.get("PhoneContact"), map.get("ServiceBW_Value"));
	  	ExtentTestManager.endTest();
	  	
		logger= ExtentTestManager.startTest("verifyManagementOptionspanel - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().verifyManagementConfigpanels("ipaVCPE", map.get("PerformanceReporting_Checkbox"), map.get("IPGuardian_Checkbox"), map.get("Package_DropdownValue"), map.get("SnmpNotification_Checkbox"), map.get("RouterConfigView_IPv4_Checkbox"), map.get("RouterConfigView_IPv6_Checkbox"), map.get("SyslogEventView_Checkbox"), map.get("DeliveryChannel_DropdownValue"), map.get("RouterbasedFirewall_Checkbox"), map.get("WithLogicalTunnel_Checkbox"), map.get("QOS_Checkbox"), map.get("ModularMSP_Checkbox"), map.get("L2Technology_DropdownValue"), map.get("RoutingProtocol_DropdownValue"), map.get("ManagedService_Checkbox"), map.get("BGPAsNumber_Value"), map.get("CustomerDescription_Value"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("verifyServicepanelLinks - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().verifyEditservice("ipaVCPE", map.get("ServiceIdentification"), map.get("ServiceType")
		, map.get("NetworkConfiguration_DrodpwonValue"), map.get("EditRemarks"), map.get("Remarks"), map.get("Edit_TerminationDate")
		, map.get("Edit_BillingTypevalue"), map.get("Edit_Email"), map.get("Edit_PhoneContact")
		, map.get("Edit_PerformanceReporting_Checkbox"), map.get("Edit_IPGuardian_Checkbox"), map.get("Edit_ServiceBW_Value")
		, map.get("Edit_Package_DropdownValue"), map.get("Edit_SnmpNotification_Checkbox")
		, map.get("Edit_RouterConfigView_IPv4_Checkbox"), map.get("Edit_RouterConfigView_IPv6_Checkbox")
		, map.get("Edit_SyslogEventView_Checkbox"), map.get("Edit_DeliveryChannel_DropdownValue")
		, map.get("Edit_RouterbasedFirewall_Checkbox"), map.get("Edit_WithLogicalTunnel_Checkbox"), map.get("Edit_QOS_Checkbox")
		, map.get("Edit_ModularMSP_Checkbox"), map.get("Edit_L2Technology_DropdownValue"), map.get("RoutingProtocol_DropdownValue")
		, map.get("Edit_ManagedService_Checkbox"), map.get("Edit_CustomerDescription_Value"));
		APT_IPAccess_VCPEConfigHelper.get().verifyShowNewInfovistaReport("ipaVCPE");
		APT_IPAccess_VCPEConfigHelper.get().verifyManageSubnets("ipaVCPE");
		APT_IPAccess_VCPEConfigHelper.get().verifyDump("ipaVCPE");
		ExtentTestManager.endTest();
				
		logger= ExtentTestManager.startTest("verifyL2Technology - IPAccess VCPE Configuration");
		String L2TechnologyValue=map.get("L2Technology_DropdownValue");
        
		if(L2TechnologyValue.equalsIgnoreCase("Atrica"))
		{
		APT_IPAccess_VCPEConfigHelper.get().verifyL2Technology("ipaVCPE", map.get("Add_L2Circuit_RemarkValue")
				, map.get("CircuitReference_Value"));
		APT_IPAccess_VCPEConfigHelper.get().verifyViewL2Circuit("ipaVCPE", map.get("L2Technology_DropdownValue")
				, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"));
		APT_IPAccess_VCPEConfigHelper.get().verifySiteA("ipaVCPE", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
				, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
				, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
				, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_DeviceName"), map.get("L2Technology_DropdownValue")
				, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_DeviceName"));
		
		APT_IPAccess_VCPEConfigHelper.get().verifySiteB("ipaVCPE", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
				, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
				, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
				, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
				, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
		
		APT_IPAccess_VCPEConfigHelper.get().verifyEditL2Circuit("ipaVCPE", map.get("Edit_L2Circuit_RemarkValue"), map.get("Edit_CircuitReference_Value"));
		
		}
		else if(L2TechnologyValue.equalsIgnoreCase("Actelis"))
		{
			APT_IPAccess_VCPEConfigHelper.get().verifyActelisL2Technology("ipaVCPE", map.get("Add_L2Circuit_RemarkValue")
					, map.get("CircuitReference_Value"), map.get("Actelis_DeviceCountry"), map.get("Actelis_ExistingCity")
					, map.get("Actelis_ExistingCityValue"), map.get("Actelis_NewCity"), map.get("Actelis_CityName")
					, map.get("Actelis_CityCode"), map.get("Actelis_ExistingSite"), map.get("Actelis_ExistingSiteValue")
					, map.get("Actelis_NewSite"), map.get("Actelis_SiteName"), map.get("Actelis_SiteCode"), map.get("Actelis_VLANIDValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyView_ActelisL2Circuit("ipaVCPE", map.get("L2Technology_DropdownValue")
					, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("Actelis_DeviceCountry")
					, map.get("Actelis_ExistingCity"), map.get("Actelis_ExistingCityValue"), map.get("Actelis_CityName")
					, map.get("Actelis_ExistingSite"), map.get("Actelis_ExistingSiteValue"), map.get("Actelis_SiteName"), map.get("Actelis_VLANIDValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyAddDSLAMandHSLlink("ipaVCPE", map.get("ActelisTech_DSLAMdevice"));
			APT_IPAccess_VCPEConfigHelper.get().AddDSLAMandHSL("ipaVCPE", map.get("ActelisTech_DSLAMdevice")
					, map.get("ActelisTech_DSLAMInterfacename"), map.get("ServiceIdentification"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyEdit_ActelisL2Circuit("ipaVCPE", map.get("Edit_L2Circuit_RemarkValue")
					, map.get("Edit_CircuitReference_Value"), map.get("Edit_Actelis_DeviceCountry"), map.get("Edit_Actelis_ExistingCity")
					, map.get("Edit_Actelis_ExistingCityValue"), map.get("Edit_Actelis_NewCity"), map.get("Edit_Actelis_CityName")
					, map.get("Edit_Actelis_CityCode"), map.get("Edit_Actelis_ExistingSite"), map.get("Edit_Actelis_ExistingSiteValue")
					, map.get("Edit_Actelis_NewSite"), map.get("Edit_Actelis_SiteName"), map.get("Edit_Actelis_SiteCode"), map.get("Edit_Actelis_VLANIDValue"));
		
			
		}
		else if(L2TechnologyValue.equalsIgnoreCase("Overture"))
		{
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureL2Technology("ipaVCPE", map.get("Add_L2Circuit_RemarkValue")
					, map.get("CircuitReference_Value"), map.get("ExistingManagementOrder"), map.get("ManagementOrder_DropdownValue")
					, map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue")
					, map.get("InterfaceSpeed_DropdownValue"), map.get("ENNICheckbox"));
		
			APT_IPAccess_VCPEConfigHelper.get().verifyViewOvertureL2Circuit("ipaVCPE", map.get("L2Technology_DropdownValue")
					, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
					, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue")
					, map.get("CircuitType_DropdownValue"), map.get("InterfaceSpeed_DropdownValue"), map.get("ENNICheckbox"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteA("ipaVCPE", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
					, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
					, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
					, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_Protected_Checkbox"), map.get("L2Technology_DropdownValue")
					, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_Protected_Checkbox"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteA_AddEquipment("ipaVCPE", map.get("SiteA_Existing_EquipmentCPE")
					, map.get("SiteA_Equipment_CPEDeviceName"), map.get("SiteA_Equipment_VendorModel"), map.get("SiteA_Equipment_Snmpro")
					, map.get("SiteA_ExistingEquipment_ManagementAddress"), map.get("SiteAEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteAEquipment_ManagementAddress_FieldValue"), map.get("SiteA_MACAddress_Value"), map.get("SiteA_MEPID_Value")
					, map.get("SiteA_PowerAlarm_DropdownValue"), map.get("SiteA_MediaSelection_DropdownValue")
					, map.get("SiteA_LinkLostForwarding_Checkbox"), map.get("SiteAEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteA_ViewEquipment("ipaVCPE", map.get("SiteA_Existing_EquipmentCPE")
					, map.get("SiteA_Equipment_CPEDeviceName"), map.get("SiteA_Equipment_VendorModel"), map.get("SiteA_Equipment_Snmpro")
					, map.get("SiteA_ExistingEquipment_ManagementAddress"), map.get("SiteAEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteAEquipment_ManagementAddress_FieldValue"), map.get("SiteA_MACAddress_Value"), map.get("SiteA_MEPID_Value")
					, map.get("SiteA_PowerAlarm_DropdownValue"), map.get("SiteA_MediaSelection_DropdownValue")
					, map.get("SiteA_LinkLostForwarding_Checkbox"), map.get("SiteAEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteA_EditEquipment("ipaVCPE", map.get("Edit_SiteAExisting_EquipmentCPE")
					, map.get("Edit_SiteAEquipment_CPEDeviceName"), map.get("Edit_SiteAEquipment_VendorModel"), map.get("Edit_SiteAEquipment_Snmpro")
					, map.get("Edit_SiteAExistingEquipment_ManagementAddress"), map.get("Edit_SiteAEquipment_ManagementAddress_DropdownValue")
					, map.get("Edit_SiteAEquipment_ManagementAddress_FieldValue"), map.get("Edit_SiteAMACAddress_Value"), map.get("Edit_SiteAMEPID_Value")
					, map.get("Edit_SiteAPowerAlarm_DropdownValue"), map.get("Edit_SiteAMediaSelection_DropdownValue")
					, map.get("Edit_SiteALinkLostForwarding_Checkbox"), map.get("Edit_SiteAEquipment_ExistingCPEdeviceValue"));
			
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB("ipaVCPE", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
					, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
					, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
					, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
					, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB_AddEquipment("ipaVCPE", map.get("SiteB_Existing_EquipmentCPE")
					, map.get("SiteB_Equipment_CPEDeviceName"), map.get("SiteB_Equipment_VendorModel"), map.get("SiteB_Equipment_Snmpro")
					, map.get("SiteB_ExistingEquipment_ManagementAddress"), map.get("SiteBEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteBEquipment_ManagementAddress_FieldValue"), map.get("SiteB_MACAddress_Value"), map.get("SiteB_MEPID_Value")
					, map.get("SiteB_PowerAlarm_DropdownValue"), map.get("SiteB_MediaSelection_DropdownValue")
					, map.get("SiteB_LinkLostForwarding_Checkbox"), map.get("SiteBEquipment_ExistingCPEdeviceValue"));
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB_ViewEquipment("ipaVCPE", map.get("SiteB_Existing_EquipmentCPE")
					, map.get("SiteB_Equipment_CPEDeviceName"), map.get("SiteB_Equipment_VendorModel"), map.get("SiteB_Equipment_Snmpro")
					, map.get("SiteB_ExistingEquipment_ManagementAddress"), map.get("SiteBEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteBEquipment_ManagementAddress_FieldValue"), map.get("SiteB_MACAddress_Value"), map.get("SiteB_MEPID_Value")
					, map.get("SiteB_PowerAlarm_DropdownValue"), map.get("SiteB_MediaSelection_DropdownValue")
					, map.get("SiteB_LinkLostForwarding_Checkbox"), map.get("SiteBEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB_EditEquipment("ipaVCPE", map.get("Edit_SiteBExisting_EquipmentCPE")
					, map.get("Edit_SiteBEquipment_CPEDeviceName"), map.get("Edit_SiteBEquipment_VendorModel"), map.get("Edit_SiteBEquipment_Snmpro")
					, map.get("Edit_SiteBExistingEquipment_ManagementAddress"), map.get("Edit_SiteBEquipment_ManagementAddress_DropdownValue")
					, map.get("Edit_SiteBEquipment_ManagementAddress_FieldValue"), map.get("Edit_SiteBMACAddress_Value"), map.get("Edit_SiteBMEPID_Value")
					, map.get("Edit_SiteBPowerAlarm_DropdownValue"), map.get("Edit_SiteBMediaSelection_DropdownValue")
					, map.get("Edit_SiteBLinkLostForwarding_Checkbox"), map.get("Edit_SiteBEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verify_EditAccedianL2Technology("ipaVCPE", map.get("Edit_L2Circuit_RemarkValue")
					, map.get("Edit_CircuitReference_Value"), map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
					, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue"), map.get("Edit_ENNICheckbox"));
			
		}
		else if(L2TechnologyValue.equalsIgnoreCase("Accedian"))
		{
			APT_IPAccess_VCPEConfigHelper.get().verifyAccedianL2Technology("ipaVCPE", map.get("Add_L2Circuit_RemarkValue")
					, map.get("CircuitReference_Value"), map.get("ExistingManagementOrder"), map.get("ManagementOrder_DropdownValue")
					, map.get("ManagementOrder_FieldValue"), map.get("CircuitType_DropdownValue"), map.get("ENNICheckbox"));

			APT_IPAccess_VCPEConfigHelper.get().verifyViewAccedianL2Circuit("ipaVCPE", map.get("L2Technology_DropdownValue")
					, map.get("Add_L2Circuit_RemarkValue"), map.get("CircuitReference_Value"), map.get("ExistingManagementOrder")
					, map.get("ManagementOrder_DropdownValue"), map.get("ManagementOrder_FieldValue")
					, map.get("CircuitType_DropdownValue"), map.get("ENNICheckbox"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteA("ipaVCPE", map.get("SiteA_DeviceCountry"), map.get("SiteA_ExistingCity")
					, map.get("SiteA_NewCity"), map.get("SiteA_CityName"), map.get("SiteA_CityCode"), map.get("SiteA_ExistingCityValue")
					, map.get("SiteA_PhysicalSiteDropdown"), map.get("SiteA_PhysicalSiteDropdownValue"), map.get("SiteA_CSRNameValue")
					, map.get("SiteA_VLANIDValue"), map.get("SiteA_Remarks"), map.get("SiteA_Protected_Checkbox"), map.get("L2Technology_DropdownValue")
					, map.get("Edit_SiteA_VLANIDValue"), map.get("Edit_SiteA_Remarks"), map.get("Edit_SiteA_Protected_Checkbox"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteA_AddEquipment("ipaVCPE", map.get("SiteA_Existing_EquipmentCPE")
					, map.get("SiteA_Equipment_CPEDeviceName"), map.get("SiteA_Equipment_VendorModel"), map.get("SiteA_Equipment_Snmpro")
					, map.get("SiteA_ExistingEquipment_ManagementAddress"), map.get("SiteAEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteAEquipment_ManagementAddress_FieldValue"), map.get("SiteA_MACAddress_Value"), map.get("SiteA_MEPID_Value")
					, map.get("SiteA_PowerAlarm_DropdownValue"), map.get("SiteA_MediaSelection_DropdownValue")
					, map.get("SiteA_LinkLostForwarding_Checkbox"), map.get("SiteAEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteA_ViewEquipment("ipaVCPE", map.get("SiteA_Existing_EquipmentCPE")
					, map.get("SiteA_Equipment_CPEDeviceName"), map.get("SiteA_Equipment_VendorModel"), map.get("SiteA_Equipment_Snmpro")
					, map.get("SiteA_ExistingEquipment_ManagementAddress"), map.get("SiteAEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteAEquipment_ManagementAddress_FieldValue"), map.get("SiteA_MACAddress_Value"), map.get("SiteA_MEPID_Value")
					, map.get("SiteA_PowerAlarm_DropdownValue"), map.get("SiteA_MediaSelection_DropdownValue")
					, map.get("SiteA_LinkLostForwarding_Checkbox"), map.get("SiteAEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteA_EditEquipment("ipaVCPE", map.get("Edit_SiteAExisting_EquipmentCPE")
					, map.get("Edit_SiteAEquipment_CPEDeviceName"), map.get("Edit_SiteAEquipment_VendorModel"), map.get("Edit_SiteAEquipment_Snmpro")
					, map.get("Edit_SiteAExistingEquipment_ManagementAddress"), map.get("Edit_SiteAEquipment_ManagementAddress_DropdownValue")
					, map.get("Edit_SiteAEquipment_ManagementAddress_FieldValue"), map.get("Edit_SiteAMACAddress_Value"), map.get("Edit_SiteAMEPID_Value")
					, map.get("Edit_SiteAPowerAlarm_DropdownValue"), map.get("Edit_SiteAMediaSelection_DropdownValue")
					, map.get("Edit_SiteALinkLostForwarding_Checkbox"), map.get("Edit_SiteAEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB_AddEquipment("ipaVCPE", map.get("SiteB_Existing_EquipmentCPE")
					, map.get("SiteB_Equipment_CPEDeviceName"), map.get("SiteB_Equipment_VendorModel"), map.get("SiteB_Equipment_Snmpro")
					, map.get("SiteB_ExistingEquipment_ManagementAddress"), map.get("SiteBEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteBEquipment_ManagementAddress_FieldValue"), map.get("SiteB_MACAddress_Value"), map.get("SiteB_MEPID_Value")
					, map.get("SiteB_PowerAlarm_DropdownValue"), map.get("SiteB_MediaSelection_DropdownValue")
					, map.get("SiteB_LinkLostForwarding_Checkbox"), map.get("SiteBEquipment_ExistingCPEdeviceValue"));
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB_ViewEquipment("ipaVCPE", map.get("SiteB_Existing_EquipmentCPE")
					, map.get("SiteB_Equipment_CPEDeviceName"), map.get("SiteB_Equipment_VendorModel"), map.get("SiteB_Equipment_Snmpro")
					, map.get("SiteB_ExistingEquipment_ManagementAddress"), map.get("SiteBEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteBEquipment_ManagementAddress_FieldValue"), map.get("SiteB_MACAddress_Value"), map.get("SiteB_MEPID_Value")
					, map.get("SiteB_PowerAlarm_DropdownValue"), map.get("SiteB_MediaSelection_DropdownValue")
					, map.get("SiteB_LinkLostForwarding_Checkbox"), map.get("SiteBEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB_EditEquipment("ipaVCPE", map.get("Edit_SiteBExisting_EquipmentCPE")
					, map.get("Edit_SiteBEquipment_CPEDeviceName"), map.get("Edit_SiteBEquipment_VendorModel"), map.get("Edit_SiteBEquipment_Snmpro")
					, map.get("Edit_SiteBExistingEquipment_ManagementAddress"), map.get("Edit_SiteBEquipment_ManagementAddress_DropdownValue")
					, map.get("Edit_SiteBEquipment_ManagementAddress_FieldValue"), map.get("Edit_SiteBMACAddress_Value"), map.get("Edit_SiteBMEPID_Value")
					, map.get("Edit_SiteBPowerAlarm_DropdownValue"), map.get("Edit_SiteBMediaSelection_DropdownValue")
					, map.get("Edit_SiteBLinkLostForwarding_Checkbox"), map.get("Edit_SiteBEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB("ipaVCPE", map.get("SiteB_DeviceCountry"), map.get("SiteB_ExistingCity")
					, map.get("SiteB_NewCity"), map.get("SiteB_CityName"), map.get("SiteB_CityCode"), map.get("SiteB_ExistingCityValue")
					, map.get("SiteB_PhysicalSiteDropdown"), map.get("SiteB_PhysicalSiteDropdownValue"), map.get("SiteB_CSRNameValue")
					, map.get("SiteB_VLANIDValue"), map.get("SiteB_Remarks"), map.get("SiteB_DeviceName"), map.get("L2Technology_DropdownValue")
					, map.get("Edit_SiteB_VLANIDValue"), map.get("Edit_SiteB_Remarks"), map.get("Edit_SiteB_DeviceName"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB_AddEquipment("ipaVCPE", map.get("SiteB_Existing_EquipmentCPE")
					, map.get("SiteB_Equipment_CPEDeviceName"), map.get("SiteB_Equipment_VendorModel"), map.get("SiteB_Equipment_Snmpro")
					, map.get("SiteB_ExistingEquipment_ManagementAddress"), map.get("SiteBEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteBEquipment_ManagementAddress_FieldValue"), map.get("SiteB_MACAddress_Value"), map.get("SiteB_MEPID_Value")
					, map.get("SiteB_PowerAlarm_DropdownValue"), map.get("SiteB_MediaSelection_DropdownValue")
					, map.get("SiteB_LinkLostForwarding_Checkbox"), map.get("SiteBEquipment_ExistingCPEdeviceValue"));
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB_ViewEquipment("ipaVCPE", map.get("SiteB_Existing_EquipmentCPE")
					, map.get("SiteB_Equipment_CPEDeviceName"), map.get("SiteB_Equipment_VendorModel"), map.get("SiteB_Equipment_Snmpro")
					, map.get("SiteB_ExistingEquipment_ManagementAddress"), map.get("SiteBEquipment_ManagementAddress_DropdownValue")
					, map.get("SiteBEquipment_ManagementAddress_FieldValue"), map.get("SiteB_MACAddress_Value"), map.get("SiteB_MEPID_Value")
					, map.get("SiteB_PowerAlarm_DropdownValue"), map.get("SiteB_MediaSelection_DropdownValue")
					, map.get("SiteB_LinkLostForwarding_Checkbox"), map.get("SiteBEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verifyOvertureSiteB_EditEquipment("ipaVCPE", map.get("Edit_SiteBExisting_EquipmentCPE")
					, map.get("Edit_SiteBEquipment_CPEDeviceName"), map.get("Edit_SiteBEquipment_VendorModel"), map.get("Edit_SiteBEquipment_Snmpro")
					, map.get("Edit_SiteBExistingEquipment_ManagementAddress"), map.get("Edit_SiteBEquipment_ManagementAddress_DropdownValue")
					, map.get("Edit_SiteBEquipment_ManagementAddress_FieldValue"), map.get("Edit_SiteBMACAddress_Value"), map.get("Edit_SiteBMEPID_Value")
					, map.get("Edit_SiteBPowerAlarm_DropdownValue"), map.get("Edit_SiteBMediaSelection_DropdownValue")
					, map.get("Edit_SiteBLinkLostForwarding_Checkbox"), map.get("Edit_SiteBEquipment_ExistingCPEdeviceValue"));
			
			APT_IPAccess_VCPEConfigHelper.get().verify_EditAccedianL2Technology("ipaVCPE", map.get("Edit_L2Circuit_RemarkValue")
					, map.get("Edit_CircuitReference_Value"), map.get("Edit_ExistingManagementOrder"), map.get("Edit_ManagementOrder_DropdownValue")
					, map.get("Edit_ManagementOrder_FieldValue"), map.get("Edit_CircuitType_DropdownValue"), map.get("Edit_ENNICheckbox"));
		}
		ExtentTestManager.endTest();
				
		logger= ExtentTestManager.startTest("addExistingPEDevice - IPAccess VCPE Configuration");
        
		APT_IPAccess_VCPEConfigHelper.get().addExistingPEDevice("ipaVCPE", map.get("ExistingDeviceName"));
		APT_IPAccess_VCPEConfigHelper.get().verifyExistingDevice_ViewDevicedetails("ipaVCPE", map.get("ExistingDeviceName"));
		APT_IPAccess_VCPEConfigHelper.get().deleteExistingDevice("ipaVCPE", map.get("ExistingDeviceName"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("addNewPEDevice - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().navigateToAddNewDevicepage("ipaVCPE");
		APT_IPAccess_VCPEConfigHelper.get().addNewPEDevice("ipaVCPE", map.get("DeviceName"), map.get("DeviceType"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
													map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
													map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
													map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"),
													map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
													map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
													map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		APT_IPAccess_VCPEConfigHelper.get().verifyViewpage_Devicedetails("ipaVCPE", map.get("DeviceName"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
													map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
													map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
													map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"),
													map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
													map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
													map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		APT_IPAccess_VCPEConfigHelper.get().verifyViewDevicepage_Links("ipaVCPE");
		APT_IPAccess_VCPEConfigHelper.get().verifyEditDevice("ipaVCPE", map.get("editdeviceName"), map.get("editVendorModel"), map.get("editTelnet"), map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"), map.get("editSnmpv3UsernameNewValue"),
													map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
													map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
													map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
													map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
		ExtentTestManager.endTest();
		
		
		logger= ExtentTestManager.startTest("verifyRouterTools - IPAccess VCPE Configuration");
		
		APT_IPAccess_VCPEConfigHelper.get().navigatetoViewDevicepage("ipaVCPE");
		DeviceNameValue= APT_IPAccess_VCPEConfigHelper.get().DeviceName("ipaVCPE");
		VendorModelValue= APT_IPAccess_VCPEConfigHelper.get().VendorModel("ipaVCPE");
		ManagementAddress= APT_IPAccess_VCPEConfigHelper.get().ManagementAddress("ipaVCPE");
		
		APT_IPAccess_VCPEConfigHelper.get().verify_Juniper_RouterTools("ipaVCPE", map.get("command_ipv4"), ManagementAddress,
						map.get("vrf_Ipv4"));
		ExtentTestManager.endTest();
		
		
			logger= ExtentTestManager.startTest("verifyRoutesPanel - IPAccess VCPE Configuration");
			if(VendorModelValue.contains("Juniper"))
			{
				APT_IPAccess_VCPEConfigHelper.get().verify_JuniperVendor_AddInterface("ipaVCPE"
						, map.get("ConfigureInterface_Checkbox"), map.get("InterfaceAddressRange_Value")
						, map.get("EIPAllocation_City"), map.get("ExistingAddressRangeIPv4selection")
						, map.get("ExistingAddressIPv4DropdownValue"), map.get("NewAddressRangeIpv4selection")
						, map.get("NewInterfaceAddressRangeIPv4")
						, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
						, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
						, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue")
						, map.get("LinkValue"), map.get("BearerType_Value"), map.get("CardType_DropdownValue")
						, map.get("Encapsulation_Value"), map.get("ServiceBW_Value"), map.get("Edit_ServiceBW_Value")
						, map.get("UnitID_Value"), map.get("Slot_Value"), map.get("Pic_Value"), map.get("Port_Value")
						, map.get("VLANID_Value"), map.get("IVManagement_Checkbox"), map.get("ExistingWANInterface_AddressRangeIPv4selection")
						, map.get("newWANInterface_AddressRangeIpv4selection"), map.get("existingWANInterface_AddressRangeIPv6selection")
						, map.get("newWANInterface_AddressRangeIpv6selection"), map.get("NewWANInterfaceAddressRange_Value")
						, map.get("NewWANInterfaceAddressRangeIPv6_Value"), map.get("MDNameFormat_Value"), map.get("DomainLevel_DropdownValue"));
				
				APT_IPAccess_VCPEConfigHelper.get().verify_JuniperVendor_EditInterface("ipaVCPE", map.get("Edit_ConfigureInterface_Checkbox")
						, map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City")
						, map.get("ExistingAddressRangeIPv4selection"), map.get("ExistingAddressIPv4DropdownValue")
						, map.get("NewAddressRangeIpv4selection"), map.get("NewInterfaceAddressRangeIPv4")
						, map.get("EIPAllocation_IPv4_SubnetSize"), map.get("ExistingAddressRangeIPv6selection")
						, map.get("NewAddressRangeIpv6selection"), map.get("NewInterfaceAddressRangeIPv6")
						, map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue")
						, map.get("Edit_LinkValue"), map.get("Edit_BearerType_Value"), map.get("Edit_CardType_DropdownValue")
						, map.get("Edit_Encapsulation_Value"), map.get("ServiceBW_Value"), map.get("Edit_ServiceBW_Value")
						, map.get("Edit_UnitID_Value"), map.get("Edit_Slot_Value"), map.get("Edit_Pic_Value")
						, map.get("Edit_Port_Value"), map.get("Edit_VLANID_Value"), map.get("Edit_IVManagement_Checkbox")
						, map.get("ExistingWANInterface_AddressRangeIPv4selection")
						, map.get("newWANInterface_AddressRangeIpv4selection"), map.get("existingWANInterface_AddressRangeIPv6selection")
						, map.get("newWANInterface_AddressRangeIpv6selection"), map.get("NewWANInterfaceAddressRange_Value")
						, map.get("NewWANInterfaceAddressRangeIPv6_Value"), map.get("Edit_MDNameFormat_Value")
						, map.get("Edit_DomainLevel_DropdownValue"));
			}
			ExtentTestManager.endTest();
			
		
		logger= ExtentTestManager.startTest("verifyFetchInterfaceDevice - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().navigatetoViewDevicepage("ipaVCPE");
		boolean FetchInterfaceSuccessMsg= APT_IPAccess_VCPEConfigHelper.get().fetchDeviceInterface_viewdevicepage("ipaVCPE");
		
		if(FetchInterfaceSuccessMsg) {
		APT_IPAccess_VCPEConfigHelper.get().verifyFetchInterface("ipaVCPE", DeviceNameValue, map.get("InServiceStatus")
				, map.get("InMaintenanceStatus"), VendorModelValue, map.get("ManagementAddress"), map.get("SnmProNewValue")
				, map.get("Country"));
		}
		ExtentTestManager.endTest();
		
		if(VendorModelValue.contains("Juniper")) {
		logger= ExtentTestManager.startTest("verifyInterfaceConfigHistory - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().verifyInterfaceConfigHistory("ipaVCPE");
		ExtentTestManager.endTest();
		}
		
		logger= ExtentTestManager.startTest("verifySelectInterfaces - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().selectInterfacelinkforDevice("ipaVCPE", DeviceNameValue);
		
		if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
			APT_IPAccess_VCPEConfigHelper.get().SelectInterfacetoremovefromservice("ipaVCPE");
		}else {
			System.out.println("interfaces are not removed");
		}
		
		if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
			APT_IPAccess_VCPEConfigHelper.get().SelectInterfacetoaddwithservcie("ipaVCPE");
		}else {
			System.out.println("Interfaces are not added");
		}
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("VerifyManageService - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().verifyManageService("ipaVCPE", map.get("ChangeOrder_newOrderNumber"), map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("Delete Device - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().deleteDevice("ipaVCPE", DeviceNameValue);
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("Delete L2Technology - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().deleteL2Technology("ipaVCPE");
		ExtentTestManager.endTest();
		
		logger= ExtentTestManager.startTest("Delete Service - IPAccess VCPE Configuration");
		APT_IPAccess_VCPEConfigHelper.get().deleteService("ipaVCPE");
		ExtentTestManager.endTest();
	}
	
	
}
	