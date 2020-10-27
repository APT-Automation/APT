package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.impl.inst2xsd.VenetianBlindStrategy;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class APT_IPAccessNoCPETest extends DriverTestcase{
	public String CustomerName=null;
	
	APT_Login Login=new APT_Login();
	private Map<String, String> map;
	
	@Test(description = "TC-01",dataProviderClass =DataReader.class, dataProvider = "DataReader_NoCPE")
	public void IPAccessNoCPETestCases(Map<String, String> map1) throws Exception {
		DriverTestcase dtc=new DriverTestcase();
		
		map=map1;
		setup();	
		Login.APT_Login_1(map.get("url for the Product"));
		
		
		
		System.out.println("TC-01");
		TestCase01();
		
		System.out.println("TC-02");
		TestCase02();
		
		System.out.println("TC-03");
		TestCase03();
		
		System.out.println("TC-04");
		TestCase04();
		
		System.out.println("TC-05");
		TestCase05();
		
		System.out.println("TC-06");
		TestCase06();
		
		System.out.println("TC-07");
		TestCase07();
		
		System.out.println("TC-08");
		TestCase08();
		
		System.out.println("TC-09");
		TestCase09();
		
		System.out.println("TC-10");
		TestCase10();
		
		System.out.println("TC-11");
		TestCase11();
		
		System.out.println("TC-12");
		TestCase12();
		
		System.out.println("TC-13");
		TestCase13();
		
		System.out.println("TC-14");
		TestCase14();
		
		System.out.println("TC-15");
		TestCase15();
		
		System.out.println("TC-16");
		TestCase16();
		
		System.out.println("TC-17");
		TestCase17();
		
		System.out.println("TC-18");
		TestCase18();
		 	
		System.out.println("TC-19");
		TestCase19();
		
		System.out.println("TC-20");
		TestCase20();
		
		System.out.println("TC-21");
		TestCase21();
		
		System.out.println("TC-22");
		TestCase22();
		
		
	}
	
	
	
	
	
	
	private void TestCase01() {
		try {
			String newCustomerName=map.get("newCustomerCreation");
	        String existingCustomer=map.get("existingCustomerSelection");
	        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
	        		//New Customer Creation
	        	logger= ExtentTestManager.startTest("TC-01 : selectNewCustomer_NoCPE");
	        	NoCPEHelper.get().createcustomer("NoCpeConfig", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
                        map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
                        map.get("Fax"));
             CustomerName=map.get("newCustomer");
            
            NoCPEHelper.get().selectCustomertocreateOrder("NoCpeConfig",map.get("newCustomer"));
	        }else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
	              logger= ExtentTestManager.startTest("TC-01 : selectExistingCustomer_NoCPE"); 
	              NoCPEHelper.get().selectCustomertocreateOrder("NoCpeConfig",map.get("existingCustomer"));
	              CustomerName=map.get("existingCustomer");
	        }
    }catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
		System.out.println(  e+" : Method failed");
	}
	ExtentTestManager.endTest();
    }
    
	
	
	private void TestCase02() {
		try {
			logger= ExtentTestManager.startTest("TC-02 : verifyNewOrderCreationOrExistingOrderSelection_NoCPE");
			 NoCPEHelper.get().createorderservice("NoCpeConfig", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
		
	
	
	
	private void TestCase03() {
		try {
			logger= ExtentTestManager.startTest("TC-03 : ServiceTypeAndNetworkConfigurationSelection_NoCPE");
			NoCPEHelper.get().verifyServicetypeAndNetworkConfigurationSelection("NoCpeConfig", map.get("ServiceType"), map.get("NetworkConfiguration"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	private void TestCase04() {
		try {
			logger= ExtentTestManager.startTest("TC-04 : verifyServiceCreation_NoCPE");
			NoCPEHelper.get().verifyServiceCreation("NoCpeConfig",map.get("ServiceType"), map.get("NetworkConfiguration"), map.get("ServiceIdentification"),
					map.get("BillingType"),map.get("TerminationDate"),map.get("EmailService"),map.get("PhoneService"), map.get("Remarks"), map.get("ManageService"),
					map.get("RouterConfigurationViewIPv4") ,map.get("RouterConfigurationViewIPv6"),	map.get("PerformanceReporting"), map.get("IPGuardian"),
					map.get("SNMPNotification"), map.get("DeliveryChannel"), map.get("TrapTargetAddress"),map.get("RouterBasedFirewall"), map.get("Qos"), 
					map.get("ExtendPErangetocustomerLAN"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	
	private void TestCase05() {
		try {
			logger= ExtentTestManager.startTest("TC-05 : verifyCustomerDetailsInformation_NoCPE");
			NoCPEHelper.get().searchorder("NoCpeConfig", map.get("ServiceIdentification"));
			NoCPEHelper.get().verifyCustomerDetailsInformation("NoCpeConfig", map.get("existingCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	
	private void TestCase06() {
		try {
			
			logger= ExtentTestManager.startTest("TC-06 : VerifyEditOrderChangeOrderFunction_NoCPE");
			NoCPEHelper.get().verifyorderpanel_editorder("NoCpeConfig", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
			NoCPEHelper.get().verifyorderpanel_changeorder("NoCpeConfig", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	private void TestCase07() {
		try {
			logger= ExtentTestManager.startTest("TC-07 : VerifyCreatedServiceInformationInViewServicePage_NoCPE");
			 NoCPEHelper.get().verifyservicepanelInformationinviewservicepage("NoCpeConfig", map.get("ServiceType"),  map.get("NetworkConfiguration"),
					  	map.get("ServiceIdentification"),map.get("BillingType"),map.get("TerminationDate"),map.get("EmailService"),map.get("PhoneService"), map.get("Remarks"), 
					  	map.get("ManageService"),map.get("RouterConfigurationViewIPv4") ,map.get("RouterConfigurationViewIPv6"),map.get("PerformanceReporting"), 
					  	map.get("IPGuardian"),map.get("SNMPNotification"), map.get("DeliveryChannel"), map.get("TrapTargetAddress"),map.get("RouterBasedFirewall"),
					  	map.get("Qos"), map.get("ExtendPErangetocustomerLAN"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	private void TestCase08() {
		try {
			
			logger= ExtentTestManager.startTest("TC-08 : VerifyServiceActionsListFunction_NoCPE");
			NoCPEHelper.get().verifyServicePanelActions_NoCPE("NoCpeConfig",map.get("ServiceType"),map.get("NetworkConfiguration"),map.get("ServiceIdentificationEdit"),
					map.get("BillingTypeEdit"),map.get("TerminationDateEdit"),map.get("EmailServiceEdit"),map.get("PhoneServiceEdit"), map.get("RemarksEdit"), 
				  	map.get("ManageServiceEdit"),map.get("RouterConfigurationViewIPv4Edit") ,map.get("RouterConfigurationViewIPv6Edit"),map.get("PerformanceReportingEdit"), 
				  	map.get("IPGuardianEdit"),map.get("SNMPNotificationEdit"), map.get("DeliveryChannelEdit"), map.get("TrapTargetAddressEdit"),map.get("RouterBasedFirewallEdit"),
				  	map.get("QosEdit"), map.get("ExtendPErangetocustomerLANEdit"));
			NoCPEHelper.get().verifyManageService("NoCpeConfig", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"),
					map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	

	private void TestCase09() {
		try {
			logger= ExtentTestManager.startTest("TC-09 : VerifyAddDeleteExistingPEDevice_NoCPE");
			NoCPEHelper.get().searchorder("NoCpeConfig", map.get("ServiceIdentification"));//PE_ServiceIdentification
			NoCPEHelper.get().addExistingPEDevice_PE("NoCpeConfig", map.get("ExistingDeviceName"));
			NoCPEHelper.get().verifyExistingDevice_ViewDevicedetails_PE("NoCpeConfig", map.get("ExistingDeviceName"));
			NoCPEHelper.get().testStatus_PE("NoCpeConfig");
			NoCPEHelper.get().deleteDevice("NoCpeConfig", map.get("ExistingDeviceName"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
			

	private void TestCase10() {
		try {
			logger= ExtentTestManager.startTest("TC-10 : VerifyAddNewPEDeviceFunction_NoCPE");
			NoCPEHelper.get().searchorder("NoCpeConfig", map.get("ServiceIdentification"));
			NoCPEHelper.get().navigateToAddNewDevicepage_PE("NoCpeConfig");
			NoCPEHelper.get().verifyadddevicefields_PE("NoCpeConfig");
			NoCPEHelper.get().addNewPEDevice_PE("NoCpeConfig", map.get("NewDeviceName"), map.get("DeviceType"), map.get("VendorModel"), map.get("Telnet"),
					map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"),
					map.get("Snmpv3Username"),map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), map.get("Snmpv3AuthpasswordNewValue"),
					map.get("Snmpv3PrivpasswordNewValue"),map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"),
					map.get("ExistingSite"),map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"),
					map.get("NewCityName"), map.get("NewCityCode"), map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"),
					map.get("NewSite"), map.get("NewPremise"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	private void TestCase11() {
		try {
			logger= ExtentTestManager.startTest("TC-11 : VerifyNewDeviceInformation_PE_NoCPE");
			NoCPEHelper.get().verifyViewpage_Devicedetails_PE("NoCpeConfig", map.get("NewDeviceName"), map.get("VendorModel"), map.get("Telnet"),
					map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), 
					map.get("Snmpv3Username"),map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"),map.get("Snmpv3AuthpasswordNewValue"),
					map.get("Snmpv3PrivpasswordNewValue"),map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"),
					map.get("ExistingSite"),map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), 
					map.get("NewCityName"), map.get("NewCityCode"), map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"),
					map.get("NewSite"), map.get("NewPremise"));
			NoCPEHelper.get().testStatus_PE("NoCpeConfig");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase12() {
		try {
			logger= ExtentTestManager.startTest("TC-12 : updateAddedNewPEDevice_PE_NoCPE");
			NoCPEHelper.get().searchorder("NoCpeConfig", map.get("ServiceIdentification"));
			NoCPEHelper.get().verifyEditDevice_PE("NoCpeConfig", map.get("editdeviceName"), map.get("editVendorModel"), map.get("editTelnet"),
					map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"),
					map.get("editSnmpv3UsernameNewValue"),map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),
					map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),map.get("editExistingCityValue"),
					map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
					map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), 
					map.get("editNewSiteName"),map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	private void TestCase13() {
		try {
			logger= ExtentTestManager.startTest("TC-13 : verifyViewpage_UpdatedDevicedetails_PE_NoCPE");
			NoCPEHelper.get().navigateToViewDevicePage("NoCpeConfig", "NewDeviceName");
			NoCPEHelper.get().verifyViewpage_UpdatedDevicedetails_PE("NoCpeConfig",map.get("editdeviceName"),map.get("editVendorModel"),map.get("editTelnet"), 
					map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"),
					map.get("editSnmpv3UsernameNewValue"),map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),
					map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),map.get("editExistingCityValue"),
					map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
					map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), 
					map.get("editNewSiteName"),map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"), 
					map.get("NewDeviceName"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"),
					map.get("Snmprw"), map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
					map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"),map.get("Snmpv3AuthpasswordNewValue"),
					map.get("Snmpv3PrivpasswordNewValue"),map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"),
					map.get("ExistingCityValue"), map.get("ExistingSite"), map.get("Existing SiteValue"), map.get("ExistingPremise"),
					map.get("Existing PremiseValue"), map.get("NewCityName"), map.get("NewCityCode"), map.get("NewSiteName"),map.get("NewSiteCode"), 
					map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewCity"), map.get("NewSite"), map.get("NewPremise"));
					}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	
	private void TestCase14() {
		try {
			logger= ExtentTestManager.startTest("TC-14 : verifyRouterToolFunction_PE_NoCPE");
			NoCPEHelper.get().routerPanel_PE("NoCpeConfig", map.get("PE_CommandIPV4"),
					map.get("PE_CommandIPV6"),map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	private void TestCase15() {
		try {
			logger= ExtentTestManager.startTest("TC-15 : verifyAddEditDeleteRoutesFunction_PE_NoCPE");
			NoCPEHelper.get().addRouteFunction_PE("NoCpeConfig", map.get("ServiceIdentification"),map.get("NewDeviceName"), 	map.get("ExistingCityValue"),
				 map.get("PE_RouteSubnetSize"), map.get("PE_Gateway"), map.get("PE_NetworkAddress"), map.get("PE_NetworkMAS"),	map.get("PE_Metrics"));
			NoCPEHelper.get().editRouteFunction_PE("NoCpeConfig", map.get("ServiceIdentification"),map.get("NewDeviceName"),map.get("ExistingCityValue"),
					map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
			NoCPEHelper.get().deleteRouteFunction_PE("NoCpeConfig", map.get("ServiceIdentification"),map.get("NewDeviceName"),map.get("ExistingCityValue"),
					map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase16() {
		try {
			logger= ExtentTestManager.startTest("TC-16 : verifyInterfaceConfigurationHistory_NoCPE");
			NoCPEHelper.get().verifyInterfaceConfigHistory("NoCpeConfig",map.get("ServiceIdentification"), map.get("NewDeviceName"), map.get("VendorModel"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	private void TestCase17() {
		try {
			logger= ExtentTestManager.startTest("TC-17 : verifyFetchDeviceInterfacePE_NoCPE");
			NoCPEHelper.get().verifyFetchDeviceInterface_PE("NoCpeConfig", map.get("NewDeviceName"), map.get("InServiceStatus"), map.get("InMaintenanceStatus"), 
					map.get("VendorModel"), map.get("ManagementAddress"), map.get("SnmProNewValue"), map.get("Country"), map.get("InterfaceName"));
		}catch(Exception e){
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	private void TestCase18() {
		try {
			NoCPEHelper.get().searchorder("NoCpeConfig", map.get("ServiceIdentification"));	
			if(map.get("VendorModel").contains("Cisco"))
			{
				logger= ExtentTestManager.startTest("TC-18 : verify_CiscoVendor_AddEditInterface_NoCPE");
				NoCPEHelper.get().verify_CiscoVendor_AddInterface("NoCpeConfig", map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), 
						map.get("GetAddress_Button"), map.get("IPV6_GetAddressButton"), map.get("InterfaceAddressRange_Value"), map.get("EIPAllocation_City"),
						map.get("EIPAllocation_SubnetSize"), map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"),
						map.get("BearerType_Value"), map.get("CiscoVendor_Bandwidth_Value"), map.get("Encapsulation_Value"), map.get("BGP_Checkbox"), map.get("FramingType_Value"),
						map.get("VLANID_Value"), map.get("BGPTemplate_Value"), map.get("CPEWAN_Value"), map.get("CPEWANIPv6_Value"), map.get("Description_Value"),
						map.get("AsCustomer_Value"), map.get("BGPPassword_Value"));
				
				NoCPEHelper.get().searchorder("NoCpeConfig", map.get("ServiceIdentification"));
				NoCPEHelper.get().verify_CiscoVendor_EditInterface("NoCpeConfig", map.get("InterfaceName"), map.get("GetAddress_Button"), map.get("IPV6_GetAddressButton"),
						map.get("NewDeviceName"), map.get("editdeviceName"), map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("Edit_Network"),
						map.get("Edit_EIPAllocation_City"), map.get("Edit_InterfaceAddressRange_value"), map.get("Edit_InterfaceAddressRangeIPv6_value"),
						map.get("Edit_AvailableBlocksvalue"), map.get("Edit_LinkValue"), map.get("Edit_BearerType_value"), map.get("Edit_CiscoVendor_Bandwidth_value"),
						map.get("Edit_FramingTypeValue"), map.get("Edit_EncapsulationValue"), map.get("Edit_VLANIDvalue"), map.get("Edit_BGPCheckbox"),
						map.get("Edit_BGPTemplate_Dropdownvalue"), map.get("Edit_CPEWAN_Value"), map.get("Edit_CPEWANIPv6_Value"), map.get("Edit_DescriptionValue"),
						map.get("Edit_AsCustomerValue"), map.get("Edit_BGPPasswordValue"), map.get("Edit_IPSubnetIPv6Value"), map.get("Edit_IPSubnetIPv4Value"),
						map.get("Edit_EIPAllocation_Subnetsize"), map.get("Edit_EIPAllocation_IPv6_Subnetsize"));
			}else{

				logger= ExtentTestManager.startTest("TC-18 : VerifyAdd_EditInterface_JuniperVendor_NoCPE");
				NoCPEHelper.get().VerifyAddInterface_JuniperVendor("NoCpeConfig", map.get("InterfaceName"), map.get("GetAddress_Button"),
						map.get("IPV6_GetAddressButton"), map.get("InterfaceAddressRange_Value"), map.get("ExistingCityValue"), map.get("EIPAllocation_SubnetSize"),
						map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value"),
						map.get("Bandwidth_Value"), map.get("Encapsulation_Value"), map.get("BGP_Checkbox"), map.get("FramingType_Value"), map.get("VLANID_Value"),
						map.get("BGPTemplate_Value"), map.get("CPEWAN_Value"), map.get("CPEWANIPv6_Value"), map.get("Description_Value"), map.get("AsCustomer_Value"), 
						map.get("BGPPassword_Value"), map.get("Juniper_ConfigInterface_checkbox"), map.get("CardType_DropdownValue"), map.get("ClockSourceValue"),
						map.get("STM1NumberValue"), map.get("BearerNumber_Value"), map.get("UnitIDValue"), map.get("SlotValue"), map.get("PicValue"), map.get("PortValue"),
						map.get("IVManagement_checkbox"), map.get("AtricaConnected_checkbox"), map.get("CardType_Dropdown_Gigabit"));
				
				NoCPEHelper.get().VerifyEditInterface_JuniperVendor("NoCpeConfig", map.get("NewDeviceName"), map.get("editdeviceName"),
						map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("Edit_Network"), map.get("ExistingCityValue"), 
						map.get("Edit_InterfaceAddressRange_value"), map.get("Edit_InterfaceAddressRangeIPv6_value"), map.get("Edit_AvailableBlocksvalue"),
						map.get("Edit_LinkValue"), map.get("Edit_BearerType_value"), map.get("Edit_Bandwidth_value"), map.get("Edit_FramingTypeValue"),
						map.get("Edit_EncapsulationValue"), map.get("Edit_VLANIDvalue"), map.get("Edit_BGPCheckbox"), map.get("Edit_BGPTemplate_Dropdownvalue"),
						map.get("Edit_CPEWAN_Value"), map.get("Edit_CPEWANIPv6_Value"), map.get("Edit_DescriptionValue"), map.get("Edit_AsCustomerValue"), 
						map.get("Edit_BGPPasswordValue"), map.get("Edit_Juniper_ConfigInterface_checkbox"), map.get("Edit_Juniper_InterfaceName"),
						map.get("Edit_CardType_DropdownValue"), map.get("Edit_ClockSourceValue"), map.get("Edit_STM1NumberValue"), map.get("Edit_BearerNumber_Value"),
						map.get("Edit_UnitIDValue"), map.get("Edit_SlotValue"), map.get("Edit_PicValue"), map.get("Edit_PortValue"), map.get("Edit_IVManagement_checkbox"), 
						map.get("Edit_AtricaConnected_checkbox"), map.get("CardType_Dropdown_Gigabit"), map.get("Edit_EIPAllocation_Subnetsize"),
						map.get("Edit_EIPAllocation_IPv6_Subnetsize"), map.get("Edit_EIPAllocation1"), map.get("Edit_GetAddress"), map.get("Edit_EIPAllocation2"),
						map.get("Edit_IPv6_GetAddress"), map.get("Edit_IPSubnetIPv6_Value"), map.get("Edit_IPSubnetIPv4_value"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	private void TestCase19() {
		try {
			NoCPEHelper.get().searchorder("NoCpeConfig", map.get("ServiceIdentification"));
	 		if(map.get("VendorModel").contains("Cisco"))
			{
	 			logger= ExtentTestManager.startTest("TC-19 : verify_CiscoVendor_AddMultilink_NoCPE");
	 			NoCPEHelper.get().verify_CiscoVendor_AddMultilink("NoCpeConfig", map.get("NewDeviceName"), map.get("Multilink_InterfaceName"), map.get("VendorModel"),
					map.get("GetAddress_Button"), map.get("IPV6_GetAddressButton"), map.get("InterfaceAddressRange_Value"), map.get("ExistingCityValue"), 
					map.get("EIPAllocation_SubnetSize"), map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"),
					map.get("Bandwidth_Value"), map.get("Multilink_Encapsulation_Value"), map.get("Multilink_BGPCheckbox"), map.get("BGPTemplate_Value"), map.get("CPEWAN_Value"),
					map.get("CPEWANIPv6_Value"), map.get("Description_Value"), map.get("AsCustomer_Value"), map.get("BGPPassword_Value"),
					map.get("Multilink_ConfigInterface_checkbox"), map.get("InterfaceName"), map.get("CheckToAddInterface_Checkbox"), map.get("UnitIDValue"),
					map.get("SlotValue"), map.get("PicValue"), map.get("PortValue"));
			
			}else{
					logger= ExtentTestManager.startTest("TC-19 : verify_JuniperVendor_AddMultilink_NoCPE");
					NoCPEHelper.get().verify_JuniperVendor_AddMultilink("NoCpeConfig", map.get("NewDeviceName"), map.get("Multilink_InterfaceName"), map.get("VendorModel"),
							map.get("GetAddress_Button"), map.get("IPV6_GetAddressButton"), map.get("InterfaceAddressRange_Value"), map.get("ExistingCityValue"), 
							map.get("EIPAllocation_SubnetSize"), map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"),
							map.get("Bandwidth_Value"), map.get("Multilink_Encapsulation_Value"), map.get("Multilink_BGPCheckbox"), map.get("BGPTemplate_Value"), map.get("CPEWAN_Value"),
							map.get("CPEWANIPv6_Value"), map.get("Description_Value"), map.get("AsCustomer_Value"), map.get("BGPPassword_Value"),
							map.get("Multilink_ConfigInterface_checkbox"), map.get("InterfaceName"), map.get("CheckToAddInterface_Checkbox"), map.get("UnitIDValue"),
							map.get("SlotValue"), map.get("PicValue"), map.get("PortValue"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	private void TestCase20() {
		try {
			logger= ExtentTestManager.startTest("TC-20 : verifySelectInterfaces_NoCPE");
			NoCPEHelper.get().searchorder("NoCpeConfig", map.get("ServiceIdentification"));	
			NoCPEHelper.get().selectInterfacelinkforDevice("NoCpeConfig", map.get("NewDeviceName"));
		
			if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
				NoCPEHelper.get().SelectInterfacetoremovefromservice("NoCpeConfig", map.get("InterfaceName"), map.get("Edit_InterfaceName"));
			}else {
				System.out.println("interfaces are not removed");
			}
			
			if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
				NoCPEHelper.get().SelectInterfacetoaddwithservcie("NoCpeConfig",map.get("InterfaceName"), map.get("Edit_InterfaceName"));
			}else {
				System.out.println("Interfaces are not added");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	
	
	private void TestCase21() {
		try {
			logger= ExtentTestManager.startTest("TC-21 : deleteDeviceFunction_PE_NewlyCreatedDevice_NoCPE");
			NoCPEHelper.get().searchorder("NoCpeConfig", map.get("ServiceIdentification"));
			NoCPEHelper.get().deleteDevice_PE("NoCpeConfig", "NewDeviceName");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	
	private void TestCase22() {
		try {
			logger= ExtentTestManager.startTest("TC-22 : deleteServiceFunction_PE_NewlyCreatedDevice_NoCPE");
			NoCPEHelper.get().deleteSevice1("NoCpeConfig", "ServiceIdentification");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
					
		 
	
	
	
	
	
	
					
					
		}

