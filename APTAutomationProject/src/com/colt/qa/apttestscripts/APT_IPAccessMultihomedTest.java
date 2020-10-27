package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.LogStatus;

public class APT_IPAccessMultihomedTest extends DriverTestcase{
	
	
	public String CustomerName=null;
	APT_Login Login=new APT_Login();
	private Map<String, String> map;
	
	@Test(description = "TC-01",dataProviderClass =DataReader.class, dataProvider = "DataReader_Multihomed")
	public void MultihomedTestCases(Map<String, String> map1) throws Exception {
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
		
		System.out.println("TC-23");
		TestCase23();
		
		System.out.println("TC-24");
		TestCase24();
		
		System.out.println("TC-25");
		TestCase25();
		
		System.out.println("TC-26");
		TestCase26();
		
		System.out.println("TC-27");
		TestCase27();
		
		System.out.println("TC-28");
		TestCase28();
		
		System.out.println("TC-29");
		TestCase29();
		
		System.out.println("TC-30");
		TestCase30();
		
		System.out.println("TC-31");
		TestCase31();
		
		System.out.println("TC-32");
		TestCase32();
		
		System.out.println("TC-33");
		TestCase33();
		
		System.out.println("TC-34");
		TestCase34();
		
		System.out.println("TC-35");
		TestCase35();
		
		System.out.println("TC-36");
		TestCase36();
		
		System.out.println("TC-37");
		TestCase37();
		
		System.out.println("TC-38");
		TestCase38();
		
		System.out.println("TC-39");
		TestCase39();
		
	}
	
	
	
	private void TestCase01() {
		try {
			String newCustomerName=map.get("newCustomerCreation");
	        String existingCustomer=map.get("existingCustomerSelection");
	        if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
	        		//New Customer Creation
	            logger= ExtentTestManager.startTest("TC-01 : selectNewCustomer_Multihomed");
	        	MultihomedHelper.get().createcustomer("MultihomedConfig", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
	                    map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
	                    map.get("Fax"));
	              CustomerName=map.get("newCustomer");
	              MultihomedHelper.get().selectCustomertocreateOrder("MultihomedConfig",map.get("newCustomer"));
	        }else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
	              logger= ExtentTestManager.startTest("TC-01 : selectExistingCustomer_Multihomed"); 
	              MultihomedHelper.get().selectCustomertocreateOrder("MultihomedConfig",map.get("existingCustomer"));
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
			logger= ExtentTestManager.startTest("TC-02 : verifyNewOrderCreationOrExistingOrderSelection_Multihomed");
	        MultihomedHelper.get().createorderservice("MultihomedConfig", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
	    	}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
		
	
	
	
	private void TestCase03() {
		try {
			logger= ExtentTestManager.startTest("TC-03 : ServiceTypeAndNetworkConfigurationSelection_Multihomed");
			MultihomedHelper.get().verifyServicetypeAndNetworkConfigurationSelection("MultihomedConfig", map.get("ServiceType"), map.get("NetworkConfiguration"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	private void TestCase04() {
		try {
			logger= ExtentTestManager.startTest("TC-04 : verifyServiceCreation_Multihomed");
			MultihomedHelper.get().verifyServiceCreation("MultihomedConfig",map.get("ServiceType"), map.get("NetworkConfiguration"),
			map.get("ServiceIdentification"),map.get("BillingType"),map.get("TerminationDate"),map.get("SecondaryCircuitOrder"), 
			map.get("EmailService"), map.get("PhoneService"), map.get("Remarks"),map.get("ManageService"),map.get("RouterConfigurationViewIPv4"),
			map.get("RouterConfigurationViewIPv6"),map.get("PerformanceReporting"), map.get("IPGuardian"),map.get("SmartMonitoring"),
			map.get("BGPCheck"), map.get("ProactiveNotification"),map.get("NotificationManagementTeam"), map.get("SNMPNotification"), 
			map.get("TrapTargetAddress"),map.get("DeliveryChannel"),map.get("RouterBasedFirewall"), map.get("Qos"), 
			map.get("ActelisBased"), map.get("BGPASNumber"), map.get("BGPPassword"),map.get("GeneratePassword"),
			map.get("LoadShared"), map.get("BGPTableDistribution"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	
	private void TestCase05() {
		try {
			logger= ExtentTestManager.startTest("TC-05 : verifyCustomerDetailsInformation_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
//			MultihomedHelper.get().verifyCustomerDetailsInformation("MultihomedConfig", map.get("existingCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
			MultihomedHelper.get().verifyCustomerDetailsInformation("MultihomedConfig", map.get("newCustomerCreation"), map.get("existingCustomerSelection"),map.get("newCustomer"),map.get("existingCustomer"),
					map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"), map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));	
		
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	private void TestCase06() {
		try {
			
			logger= ExtentTestManager.startTest("TC-06 : VerifyEditOrderChangeOrderFunction_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
//			MultihomedHelper.get().verifyorderpanel_editorder("MultihomedConfig", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
//			MultihomedHelper.get().verifyorderpanel_changeorder("MultihomedConfig", map.get("ChangeOrder_OrderNumber"), map.get("ChangeOrder_VoicelineNumber"));	
			
			MultihomedHelper.get().verifyorderpanel_editorder("MultihomedConfig", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
			MultihomedHelper.get().verifyorderpanel_changeorder("MultihomedConfig", map.get("ChangeOrder_newOrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
					map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
			
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	private void TestCase07() {
		try {
			logger= ExtentTestManager.startTest("TC-07 : VerifyCreatedServiceInformationInViewServicePage_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().VerifyCreatedServiceInformationInViewServicePage("MultihomedConfig",map.get("ServiceType"), map.get("NetworkConfiguration"),
					map.get("ServiceIdentification"),map.get("BillingType"),map.get("TerminationDate"),map.get("SecondaryCircuitOrder"), 
					map.get("EmailService"), map.get("PhoneService"), map.get("Remarks"),map.get("ManageService"),map.get("RouterConfigurationViewIPv4"),
					map.get("RouterConfigurationViewIPv6"),map.get("PerformanceReporting"), map.get("IPGuardian"),map.get("SmartMonitoring"),
					map.get("BGPCheck"), map.get("ProactiveNotification"),map.get("NotificationManagementTeam"), map.get("SNMPNotification"), 
					map.get("TrapTargetAddress"),map.get("DeliveryChannel"),map.get("RouterBasedFirewall"), map.get("Qos"), 
					map.get("ActelisBased"), map.get("BGPASNumber"), map.get("BGPPassword"),map.get("GeneratePassword"),
					map.get("LoadShared"), map.get("BGPTableDistribution"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	private void TestCase08() {
		try {
			
			logger= ExtentTestManager.startTest("TC-08 : VerifyServiceActionsListFunction_Multihomed");
			MultihomedHelper.get().EditServiceFunction("MultihomedConfig",map.get("ServiceType"),map.get("NetworkConfiguration"),
					map.get("ServiceIdentification"),map.get("BillingTypeEdit"),map.get("TerminationDateEdit"),map.get("SecondaryCircuitOrderEdit"),
					map.get("EmailServiceEdit"),map.get("PhoneServiceEdit"), map.get("RemarksEdit"), map.get("ManageServiceEdit"),map.get("RouterConfigurationViewIPv4Edit"),
					map.get("RouterConfigurationViewIPv6Edit"),map.get("PerformanceReportingEdit"), map.get("IPGuardianEdit"), map.get("SmartMonitoringEdit"),
					map.get("BGPCheckEdit"),map.get("ProactiveNotificationEdit"),map.get("NotificationManagementTeamEdit"),map.get("SNMPNotificationEdit"),
					map.get("TrapTargetAddressEdit"), map.get("DeliveryChannelEdit"),map.get("RouterBasedFirewallEdit"),map.get("QosEdit"),map.get("ActelisBasedEdit"), 
					map.get("BGPASNumberEdit"), map.get("BGPPasswordEdit"), map.get("LoadSharedEdit"),  map.get("GeneratePasswordEdit"), map.get("BGPTableDistributionEdit"));
			
			MultihomedHelper.get().verifyManageSubnets("MultihomedConfig");
			MultihomedHelper.get().verifyManageSubnetsIPv6("MultihomedConfig");
			MultihomedHelper.get().verifyDump("MultihomedConfig");
			MultihomedHelper.get().ShowNewInfovistaReport("MultihomedConfig");
			MultihomedHelper.get().verifyManageService1("MultihomedConfig", map.get("ChangeOrder_OrderNumber"), map.get("ServiceIdentification"),
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
	logger= ExtentTestManager.startTest("TC-09 : VerifyActelisConfiguration_AddDSLAMandHSL_Multihomed");
	MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
	MultihomedHelper.get().verifyAddDSLAMandHSLlink("MultihomedConfig", map.get("ActelisTech_DSLAMdevice"));
	MultihomedHelper.get().AddDSLAMandHSL("MultihomedConfig", map.get("ActelisTech_DSLAMdevice"), map.get("ActelisTech_DSLAMInterfacename"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
		
	

	private void TestCase10() {
		try {
			logger= ExtentTestManager.startTest("TC-10 : VerifyAddDeleteExistingPEDevice_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));//ServiceIdentification
			MultihomedHelper.get().addExistingPEDevice_PE("MultihomedConfig", map.get("ExistingDeviceName"));
			MultihomedHelper.get().verifyExistingDevice_ViewDevicedetails_PE("MultihomedConfig", map.get("ExistingDeviceName"));
			MultihomedHelper.get().testStatus_PE("MultihomedConfig");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));//ServiceIdentification
			MultihomedHelper.get().deleteDevice("MultihomedConfig", map.get("ExistingDeviceName"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
			

	private void TestCase11() {
		try {
			logger= ExtentTestManager.startTest("TC-11 : VerifyAddNewPEDeviceFunction_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().navigateToAddNewDevicepage_PE("MultihomedConfig");
			MultihomedHelper.get().verifyadddevicefields_PE("MultihomedConfig");
			MultihomedHelper.get().addNewPEDevice_PE("MultihomedConfig", map.get("NewPE_DeviceName"), map.get("DeviceType"), map.get("VendorModel"), map.get("Telnet"),
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
	
	private void TestCase12() {
		try {
			logger= ExtentTestManager.startTest("TC-12 : VerifyNewDeviceInformation_PE_Multihomed");
			MultihomedHelper.get().verifyViewpage_Devicedetails_PE("MultihomedConfig", map.get("NewPE_DeviceName"), map.get("VendorModel"), map.get("Telnet"),
					map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), 
					map.get("Snmpv3Username"),map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"),map.get("Snmpv3AuthpasswordNewValue"),
					map.get("Snmpv3PrivpasswordNewValue"),map.get("Country"), map.get("ManagementAddress"), map.get("ExistingCity"), map.get("ExistingCityValue"),
					map.get("ExistingSite"),map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), 
					map.get("NewCityName"), map.get("NewCityCode"), map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"),
					map.get("NewSite"), map.get("NewPremise"));
			MultihomedHelper.get().testStatus_PE("MultihomedConfig");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase13() {
		try {
			logger= ExtentTestManager.startTest("TC-13 : updateAddedNewPEDevice_PE_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().verifyEditDevice_PE("MultihomedConfig", map.get("editdeviceName"), map.get("editVendorModel"), map.get("editTelnet"),
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
	
	
	private void TestCase14() {
		try {
			logger= ExtentTestManager.startTest("TC-14 : verifyViewpage_UpdatedDevicedetails_PE_Multihomed");
			MultihomedHelper.get().navigateToViewDevicePage("MultihomedConfig", map.get("NewPE_DeviceName"));
			MultihomedHelper.get().verifyViewpage_UpdatedDevicedetails_PE("MultihomedConfig",map.get("editdeviceName"),map.get("editVendorModel"),map.get("editTelnet"), 
					map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"),
					map.get("editSnmpv3UsernameNewValue"),map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),
					map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),map.get("editExistingCityValue"),
					map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
					map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), 
					map.get("editNewSiteName"),map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"), 
					map.get("NewPE_DeviceName"), map.get("VendorModel"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"),
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
	
	private void TestCase15() {
		try {
			logger= ExtentTestManager.startTest("TC-15 : verifyRouterToolFunction_PE_Multihomed");
			MultihomedHelper.get().routerPanel_PE("MultihomedConfig", map.get("PE_CommandIPV4"),
					map.get("PE_CommandIPV6"),map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	private void TestCase16() {
		try {
			logger= ExtentTestManager.startTest("TC-16 : verifyAddEditDeleteRoutesFunction_PE_Multihomed");
			MultihomedHelper.get().addRouteFunction_PE("MultihomedConfig", map.get("ServiceIdentification"),map.get("NewPE_DeviceName"), 	map.get("editExistingCityValue"),
				 map.get("PE_RouteSubnetSize"), map.get("PE_Gateway"), map.get("PE_NetworkAddress"), map.get("PE_NetworkMAS"),	map.get("PE_Metrics"));
			MultihomedHelper.get().editRouteFunction_PE("MultihomedConfig", map.get("ServiceIdentification"),map.get("NewPE_DeviceName"),map.get("editExistingCityValue"),
					map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
			MultihomedHelper.get().deleteRouteFunction_PE("MultihomedConfig", map.get("ServiceIdentification"),map.get("NewPE_DeviceName"),map.get("editExistingCityValue"),
					map.get("PE_RouteSubnetSizeEdit"), map.get("PE_GatewayEdit"), map.get("PE_NetworkAddressEdit"), map.get("PE_NetworkMASEdit"),map.get("PE_MetricsEdit"));
		
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase17() {
		try {
			logger= ExtentTestManager.startTest("TC-17 : verifyInterfaceConfigurationHistory_ColtTotal");
			MultihomedHelper.get().verifyInterfaceConfigHistory("MultihomedConfig",map.get("ServiceIdentification"), map.get("NewPE_DeviceName"), map.get("VendorModel"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	
	private void TestCase18() {
		try {
			logger= ExtentTestManager.startTest("TC-18 : VerifyFetchDeviceInterfaceFunction_PE_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().navigateToViewDevicePage("MultihomedConfig", "NewPE_DeviceName");
			MultihomedHelper.get().verifyFetchDeviceInterface_PE("MultihomedConfig", map.get("NewPE_DeviceName"), map.get("InServiceStatus"), map.get("InMaintenanceStatus"), 
					map.get("VendorModel"), map.get("ManagementAddress"), map.get("SnmProNewValue"), map.get("Country"), map.get("InterfaceName"));
			}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
		System.out.println(  e+" : Method failed");
	}
	ExtentTestManager.endTest();
	}
	
	
	private void TestCase19() {
		try {
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			if(map.get("VendorModel").contains("Cisco"))
			{
				logger= ExtentTestManager.startTest("TC-19 : verify_CiscoVendor_AddEditInterface_Multihomed");
				MultihomedHelper.get().verify_CiscoVendor_AddInterface("MultihomedConfig", map.get("ConfigureInterface_Checkbox"), map.get("InterfaceName"), 
					map.get("GetAddress_Button"), map.get("IPV6_GetAddressButton"), map.get("InterfaceAddressRange_Value"), map.get("editExistingCityValue"),
					map.get("EIPAllocation_SubnetSize"), map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"),
					map.get("BearerType_Value"), map.get("CiscoVendor_Bandwidth_Value"), map.get("Encapsulation_Value"), map.get("BGP_Checkbox"), map.get("FramingType_Value"),
					map.get("VLANID_Value"), map.get("BGPTemplate_Value"), map.get("CPEWAN_Value"), map.get("CPEWANIPv6_Value"), map.get("Description_Value"),
					map.get("AsCustomer_Value"), map.get("BGPPassword_Value"));
			
				MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));	
				MultihomedHelper.get().verify_CiscoVendor_EditInterface("MultihomedConfig", map.get("InterfaceName"), map.get("GetAddress_Button"), map.get("IPV6_GetAddressButton"),
					map.get("NewPE_DeviceName"), map.get("editdeviceName"), map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("Edit_Network"),
					map.get("editExistingCityValue"), map.get("Edit_InterfaceAddressRange_value"), map.get("Edit_InterfaceAddressRangeIPv6_value"),
					map.get("Edit_AvailableBlocksvalue"), map.get("Edit_LinkValue"), map.get("Edit_BearerType_value"), map.get("Edit_CiscoVendor_Bandwidth_value"),
					map.get("Edit_FramingTypeValue"), map.get("Edit_EncapsulationValue"), map.get("Edit_VLANIDvalue"), map.get("Edit_BGPCheckbox"),
					map.get("Edit_BGPTemplate_Dropdownvalue"), map.get("Edit_CPEWAN_Value"), map.get("Edit_CPEWANIPv6_Value"), map.get("Edit_DescriptionValue"),
					map.get("Edit_AsCustomerValue"), map.get("Edit_BGPPasswordValue"), map.get("Edit_IPSubnetIPv6Value"), map.get("Edit_IPSubnetIPv4Value"),
					map.get("Edit_EIPAllocation_Subnetsize"), map.get("Edit_EIPAllocation_IPv6_Subnetsize"));
			}else{

				logger= ExtentTestManager.startTest("TC-19 : VerifyAdd_EditInterface_JuniperVendor_Multihomed");
				MultihomedHelper.get().VerifyAddInterface_JuniperVendor("MultihomedConfig", map.get("InterfaceName"), map.get("GetAddress_Button"),
				map.get("IPV6_GetAddressButton"), map.get("InterfaceAddressRange_Value"), map.get("editExistingCityValue"), map.get("EIPAllocation_SubnetSize"),
				map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"), map.get("BearerType_Value"),
				map.get("Bandwidth_Value"), map.get("Encapsulation_Value"), map.get("BGP_Checkbox"), map.get("FramingType_Value"), map.get("VLANID_Value"),
				map.get("BGPTemplate_Value"), map.get("CPEWAN_Value"), map.get("CPEWANIPv6_Value"), map.get("Description_Value"), map.get("AsCustomer_Value"), 
				map.get("BGPPassword_Value"), map.get("Juniper_ConfigInterface_checkbox"), map.get("CardType_DropdownValue"), map.get("ClockSourceValue"),
				map.get("STM1NumberValue"), map.get("BearerNumber_Value"), map.get("UnitIDValue"), map.get("SlotValue"), map.get("PicValue"), map.get("PortValue"),
				map.get("IVManagement_checkbox"), map.get("AtricaConnected_checkbox"), map.get("CardType_Dropdown_Gigabit"));
				
				
				MultihomedHelper.get().VerifyEditInterface_JuniperVendor("MultihomedConfig", map.get("NewPE_DeviceName"), map.get("editdeviceName"),
					map.get("Edit_ConfigureInterface_Checkbox"), map.get("Edit_InterfaceName"), map.get("Edit_Network"), map.get("editExistingCityValue"), 
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
	
	
	private void TestCase20() {
		try {
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			if(map.get("VendorModel").contains("Cisco"))
			{
			logger= ExtentTestManager.startTest("TC-20 : verify_CiscoVendor_AddMultilink_Multihomed");
			MultihomedHelper.get().verify_CiscoVendor_AddMultilink("MultihomedConfig", map.get("NewPE_DeviceName"), map.get("Multilink_InterfaceName"), map.get("VendorModel"),
					map.get("GetAddress_Button"), map.get("IPV6_GetAddressButton"), map.get("InterfaceAddressRange_Value"), map.get("ExistingCityValue"), 
					map.get("EIPAllocation_SubnetSize"), map.get("EIPAllocation_IPv6_SubnetSize"), map.get("EIPAllocation_AvailableBlocksValue"), map.get("LinkValue"),
					map.get("Bandwidth_Value"), map.get("Multilink_Encapsulation_Value"), map.get("Multilink_BGPCheckbox"), map.get("BGPTemplate_Value"), map.get("CPEWAN_Value"),
					map.get("CPEWANIPv6_Value"), map.get("Description_Value"), map.get("AsCustomer_Value"), map.get("BGPPassword_Value"),
					map.get("Multilink_ConfigInterface_checkbox"), map.get("InterfaceName"), map.get("CheckToAddInterface_Checkbox"), map.get("UnitIDValue"),
					map.get("SlotValue"), map.get("PicValue"), map.get("PortValue"));
			
			}else{
				
					logger= ExtentTestManager.startTest("TC-20 : verify_JuniperVendor_AddMultilink_Multihomed");
					MultihomedHelper.get().verify_JuniperVendor_AddMultilink("MultihomedConfig", map.get("NewPE_DeviceName"), map.get("Multilink_InterfaceName"), map.get("VendorModel"),
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
	
	
	private void TestCase21() {
		try {
			logger= ExtentTestManager.startTest("TC-21 : verifyInterfaceConfigurationHistory_Multihomed");
			MultihomedHelper.get().verifyInterfaceConfigHistory("MultihomedConfig",map.get("ServiceIdentification"), map.get("NewPE_DeviceName"), map.get("VendorModel"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}	
	
	
	
	
	
	private void TestCase22() {
		try {
			logger= ExtentTestManager.startTest("TC-22 : verifySelectInterfaces_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().selectInterfacelinkforDevice("MultihomedConfig", map.get("NewPE_DeviceName"));
		
			if(map.get("RemoveInterface_Selection").equalsIgnoreCase("yes")) {
				MultihomedHelper.get().SelectInterfacetoremovefromservice("MultihomedConfig", map.get("InterfaceName"), map.get("Edit_InterfaceName"));
			}else {
				System.out.println("interfaces are not removed");
			}
			
			if(map.get("AddInterface_Selection").equalsIgnoreCase("yes")) {
				MultihomedHelper.get().SelectInterfacetoaddwithservcie("MultihomedConfig",map.get("InterfaceName"), map.get("Edit_InterfaceName"));
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
	
	
	private void TestCase23() {
		try {
			logger= ExtentTestManager.startTest("TC-23 : verifyAddCPEDeviceFunction_Multihomed");
			Thread.sleep(2000);
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().verifyAddCPEDeviceFunction("MultihomedConfig", map.get("ServiceIdentification"),
					map.get("CPE_DeviceName"),map.get("CPE_VendorModel"),map.get("CPE_ManagementAddress"),map.get("CPE_Snmpro"),map.get("CPE_Country"),
					map.get("CPE_City"),  map.get("CPE_Site"), map.get("CPE_Premise"), map.get("CPE_Role1"),  map.get("CPE_Role2")); 
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	private void TestCase24() {
		try {
			
			logger= ExtentTestManager.startTest("TC-24 : verifyAddedCPEDeviceInformation_Multihomed");
		MultihomedHelper.get().verifyAddedCPEDeviceInformation_View("MultihomedConfig", map.get("ServiceIdentification"),
				map.get("CPE_DeviceName"),map.get("CPE_VendorModel"),map.get("CPE_ManagementAddress"),map.get("CPE_Snmpro"),map.get("CPE_Country"),
				map.get("CPE_City"),  map.get("CPE_Site"), map.get("CPE_Premise"), map.get("CPE_Role1"),  map.get("CPE_Role2")); 
		MultihomedHelper.get().testStatus_CPE("MultihomedConfig");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase25() {
		try {
			logger= ExtentTestManager.startTest("TC-25 : verifyEditCPEDeviceFunction_Multihomed");
			MultihomedHelper.get().verifyEditCPEDeviceFunction("MultihomedConfig", map.get("ServiceIdentification"),
					map.get("CPE_DeviceNameEdit"),map.get("CPE_VendorModelEdit"),map.get("CPE_ManagementAddressEdit"),map.get("CPE_SnmproEdit"),map.get("CPE_CountryEdit"),
					map.get("CPE_CityEdit"),  map.get("CPE_SiteEdit"), map.get("CPE_PremiseEdit"), map.get("CPE_Role1Edit"),  map.get("CPE_Role2Edit")); 
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	
	private void TestCase26() {
		try {
			
			logger= ExtentTestManager.startTest("TC-26 : verifyRouterToolFunction_CPE_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().navigateToViewDevicePage_CPE("MultihomedConfig", map.get("CPE_DeviceName"));
			MultihomedHelper.get().routerPanel_PE("MultihomedConfig", map.get("CPE_CommandIPV4"),
					map.get("CPE_CommandIPV6"),map.get("CPE_vrf_Ipv4"), map.get("CsPE_vrf_Ipv6"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase27() {
		try {
			logger= ExtentTestManager.startTest("TC-27 : verifyAddEditDeleteRouteFunction_CPE_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().navigateToViewDevicePage_CPE("MultihomedConfig", map.get("CPE_DeviceName"));
			MultihomedHelper.get().addRouteFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"), 	map.get("ExistingCityValue"),
				 map.get("CPE_RouteSubnetSize"), map.get("CPE_Gateway"), map.get("CPE_NetworkAddress"), map.get("CPE_NetworkMAS"),	map.get("CPE_Metrics"));
			MultihomedHelper.get().editRouteFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),map.get("ExistingCityValue"),
					map.get("CPE_RouteSubnetSizeEdit"), map.get("CPE_GatewayEdit"), map.get("CPE_NetworkAddressEdit"), map.get("CPE_NetworkMASEdit"),map.get("CPE_MetricsEdit"));
			MultihomedHelper.get().deleteRouteFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),map.get("ExistingCityValue"),
					map.get("CPE_RouteSubnetSizeEdit"), map.get("CPE_GatewayEdit"), map.get("CPE_NetworkAddressEdit"), map.get("CPE_NetworkMASEdit"),map.get("CPE_MetricsEdit"));
	}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase28() {
		try {
			logger= ExtentTestManager.startTest("TC-28 : verifyCustomerReadonlySNMPFunction_CPE_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().navigateToViewDevicePage_CPE("MultihomedConfig", map.get("CPE_DeviceName"));
			MultihomedHelper.get().addCustomerReadonlySNMPFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("CustomerIPAddress"), map.get("CustomerCommunityString"));
			MultihomedHelper.get().editCustomerReadonlySNMPFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("CustomerIPAddressEdit"),map.get("CustomerCommunityStringEdit"));
			MultihomedHelper.get().deleteCustomerReadonlySNMPFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("CustomerIPAddressEdit"),map.get("CustomerCommunityStringEdit"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase29() {
		try {
			logger= ExtentTestManager.startTest("TC-29 : verifyPIRangesFunction_CPE_Multihomed");
			MultihomedHelper.get().addPIRangesFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"), map.get("PIAddressRange"));
			MultihomedHelper.get().deletePIRangesFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),map.get("PIAddressRange"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	private void TestCase30() {
		try {
			logger= ExtentTestManager.startTest("TC-30 : verifyeditNATConfigurationFunction_CPE_Multihomed");
			MultihomedHelper.get().editNATConfigurationFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("StaticNATEdit"),map.get("DynamicNATEdit"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	private void TestCase31() {
		try {
			String StaticNATMapping=map.get("StaticNATEdit");
	        String DynamicNATMapping=map.get("DynamicNATEdit");
	        if(StaticNATMapping.equalsIgnoreCase("yes") && DynamicNATMapping.equalsIgnoreCase("no")) {
				logger= ExtentTestManager.startTest("TC-31 : verifyStaticNATMappingFunction_CPE_Multihomed");
				ExtentTestManager.getTest().log(LogStatus.INFO, "'Static NAT Selection status is :  "+StaticNATMapping);
				MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
				MultihomedHelper.get().navigateToViewDevicePage_CPE("MultihomedConfig", "CPE_DeviceName");
				MultihomedHelper.get().addStaticNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
				map.get("Static_Protocol"),map.get("Static_LocalPort"), map.get("Static_GlobalPort"), map.get("Static_LocalIP"), map.get("Static_GlobalIP"));
				
				MultihomedHelper.get().editStaticNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
				map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));

				APT_IPAccessConfigHelper.get().deleteStaticNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIP"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));

	        }else if(StaticNATMapping.equalsIgnoreCase("no") && DynamicNATMapping.equalsIgnoreCase("Yes")) {
				logger= ExtentTestManager.startTest("TC-31 : verifyDynamicNATMappingFunction_CPE_Multihomed");
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Dynamic NAT Selection status is :  "+DynamicNATMapping);
				MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
				MultihomedHelper.get().navigateToViewDevicePage_CPE("MultihomedConfig", "CPE_DeviceName");
				MultihomedHelper.get().addDynamicNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
				map.get("Dynamic_PoolMode"),map.get("Dynamic_InterfaceMode"), map.get("Dynamic_LocalNetwork"), map.get("Dynamic_PoolStartAddress"), map.get("Dynamic_PoolEndAddress"),
				map.get("Dynamic_PoolPrefix"), map.get("Dynamic_MapsToInterface"), map.get("StaticNATEdit"), map.get("StaticNATEdit"));
				MultihomedHelper.get().editDynamicNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("EditDynamic_PoolMode"),map.get("EditDynamic_InterfaceMode"), map.get("EditDynamic_LocalNetwork"), map.get("EditDynamic_PoolStartAddress"), map.get("EditDynamic_PoolEndAddress"),
						map.get("EditDynamic_PoolPrefix"), map.get("EditDynamic_MapsToInterface"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"));
				APT_IPAccessConfigHelper.get().deleteDynamicNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification")
				,map.get("CPE_DeviceName"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"), map.get("Dynamic_LocalNetwork")
				, map.get("EditDynamic_LocalNetwork"));
				}else if(StaticNATMapping.equalsIgnoreCase("Yes") && DynamicNATMapping.equalsIgnoreCase("Yes")) {	
	        	logger= ExtentTestManager.startTest("TC-31 : verifyStaticNATMappingFunction_CPE_Multihomed");
				ExtentTestManager.getTest().log(LogStatus.INFO, "'Static NAT Selection status is :  "+StaticNATMapping);
				MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
				MultihomedHelper.get().navigateToViewDevicePage_CPE("MultihomedConfig", "CPE_DeviceName");
				MultihomedHelper.get().addStaticNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Static_Protocol"),map.get("Static_LocalPort"), map.get("Static_GlobalPort"), map.get("Static_LocalIP"), map.get("Static_GlobalIP"));
				MultihomedHelper.get().editStaticNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));
				APT_IPAccessConfigHelper.get().deleteStaticNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Static_ProtocolEdit"),map.get("Static_LocalPortEdit"), map.get("Static_GlobalPortEdit"), map.get("Static_LocalIP"), map.get("Static_LocalIPEdit"), map.get("Static_GlobalIPEdit"));
			
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Dynamic NAT Selection status is :  "+DynamicNATMapping);
	        	MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
				MultihomedHelper.get().navigateToViewDevicePage_CPE("MultihomedConfig", "CPE_DeviceName");
				MultihomedHelper.get().addDynamicNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("Dynamic_PoolMode"),map.get("Dynamic_InterfaceMode"), map.get("Dynamic_LocalNetwork"), map.get("Dynamic_PoolStartAddress"), map.get("Dynamic_PoolEndAddress"),
						map.get("Dynamic_PoolPrefix"), map.get("Dynamic_MapsToInterface"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"));
				MultihomedHelper.get().editDynamicNATMappingFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
						map.get("EditDynamic_PoolMode"),map.get("EditDynamic_InterfaceMode"), map.get("EditDynamic_LocalNetwork"), map.get("EditDynamic_PoolStartAddress"), map.get("EditDynamic_PoolEndAddress"),
						map.get("EditDynamic_PoolPrefix"), map.get("EditDynamic_MapsToInterface"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"));
				APT_IPAccessConfigHelper.get().deleteDynamicNATMappingFunction_CPE("IPAConfig2", map.get("ServiceIdentification")
						,map.get("CPE_DeviceName"), map.get("StaticNATEdit"), map.get("DynamicNATEdit"), map.get("Dynamic_LocalNetwork")
						, map.get("EditDynamic_LocalNetwork"));
				
	        }else if(StaticNATMapping.equalsIgnoreCase("no") && DynamicNATMapping.equalsIgnoreCase("no")) {
	        	logger= ExtentTestManager.startTest("TC-31 : StaticAndDynamicNonSelection_CPE_Multihomed");
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Static NAT & Dynamic NAT is not selected as expected");
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Static NAT Selection status is :  "+StaticNATMapping);
	        	ExtentTestManager.getTest().log(LogStatus.INFO, "'Dynamic NAT Selection status is :  "+DynamicNATMapping);
	        	ExtentTestManager.endTest();
	        }
	        
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	
	private void TestCase32() {
		try {
			logger= ExtentTestManager.startTest("TC-32 : verifyDHCPServersonCPEFunction_CPE_Multihomed");
			MultihomedHelper.get().addDHCPServersonCPEFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("DHCP_CustomerLANSubnet"),map.get("DHCP_SubnetMask"), map.get("DHCP_PrimaryDNSServer"), map.get("DHCP_SecondaryDNSServer"));
			MultihomedHelper.get().editDHCPServersonCPEFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("DHCP_CustomerLANSubnetEdit"),map.get("DHCP_SubnetMaskEdit"), map.get("DHCP_PrimaryDNSServerEdit"), map.get("DHCP_SecondaryDNSServerEdit"));
			MultihomedHelper.get().deleteDHCPServersonCPEFunction_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("DHCP_CustomerLANSubnetEdit"),map.get("DHCP_SubnetMaskEdit"), map.get("DHCP_PrimaryDNSServerEdit"), map.get("DHCP_SecondaryDNSServerEdit"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	private void TestCase33() {
		try {
			logger= ExtentTestManager.startTest("TC-33 : VerifyConfigurationPanel_CPE_Multihomed");
			MultihomedHelper.get().VerifyConfigurationPanel_CPE("MultihomedConfig", map.get("ServiceIdentification"),map.get("CPE_DeviceName"),
					map.get("Configuration"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
			}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	
	private void TestCase34() {
		try {
			logger= ExtentTestManager.startTest("TC-34 : verifyFetchDeviceInterfaceFunction_CPE_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().navigateToViewDevicePage_CPE("MultihomedConfig", map.get("CPE_DeviceName"));
			MultihomedHelper.get().veriyFetchDeviceInterfacesFunction_CPE("MultihomedConfig",map.get("ServiceIdentification"), 
					map.get("CPE_ServiceStatusChangeRequired"));
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	private void TestCase35() {
		try {
			logger= ExtentTestManager.startTest("TC-35 : verifyAddInterface_CPEDevice_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().verifyAddInterface_CPEDevice("MultihomedConfig",map.get("CPE_InterfaceName"), 
					map.get("CPE_GetAddress_Button"), map.get("CPE_IPV6_GetAddressButton"), map.get("CPE_InterfaceAddressRange_Value"), map.get("CPE_EIPAllocation_City"),
					map.get("CPE_EIPAllocation_SubnetSize"), map.get("CPE_EIPAllocation_IPv6_SubnetSize"), map.get("CPE_EIPAllocation_AvailableBlocksValue"),
					map.get("CPE_EthernetCheckbox"), map.get("CPE_Speed"), map.get("CPE_Duplex"), map.get("CPE_InterfaceIsWANCheckbox"),
					map.get("CPE_InterfaceIsLANCheckbox"), map.get("CPE_VRRPGroup"), map.get("CPE_VRRPIP"), map.get("CPE_VRRPIPV6"), map.get("CPE_VRRPPriority"),
					map.get("CPE_VRRPPassword"), map.get("CPE_FailoverCableChekbox"),map.get("CPE_LinkValue"), map.get("IVManagementCheckbox"), map.get("CPE_ClockSource"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	
	
	private void TestCase36() {
		try {
			logger= ExtentTestManager.startTest("TC-36 : verifyAddMultilink_CPEDevice_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().verifyAddMultilink_CPEDevice("MultihomedConfig",map.get("CPEMultilink_Name"), 
					map.get("CPEMultilink_GetAddress_Button"), map.get("CPEMultilink_IPV6_GetAddressButton"), map.get("CPEMultilink_InterfaceAddressRange_Value"), 
					map.get("CPEMultilink_EIPAllocation_City"),map.get("CPEMultilink_EIPAllocation_SubnetSize"), map.get("CPEMultilink_EIPAllocation_IPv6_SubnetSize"), 
					map.get("CPEMultilink_EIPAllocation_AvailableBlocksValue"),	map.get("CPEMultilink_EthernetCheckbox"), map.get("CPEMultilink_Speed"),
					map.get("CPEMultilink_Duplex"), map.get("CPEMultilink_InterfaceIsWANCheckbox"),map.get("CPEMultilink_InterfaceIsLANCheckbox"),
					map.get("CPEMultilink_VRRPGroup"), map.get("CPEMultilink_VRRPIP"),map.get("CPEMultilink_VRRPIPV6"), map.get("CPEMultilink_VRRPPriority"),
					map.get("CPEMultilink_VRRPPassword"), map.get("CPEMultilink_FailoverCableChekbox"),map.get("CPEMultilink_LinkValue"), map.get("Multilink_IVManagementCheckbox"), 
					map.get("CPEMultilink_ClockSource"), map.get("CPEMultilink_AddthisasaPIRangeCheckbox"), map.get("CPEMultilink_AddthisasaPIRangeIPV6Checkbox"));
			}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase37() {
		try {
			logger= ExtentTestManager.startTest("TC-37 : deletePEdeviceFunction_NewlyCreatedDevice_Multihomed");
			MultihomedHelper.get().searchorder("MultihomedConfig", map.get("ServiceIdentification"));
			MultihomedHelper.get().deleteDevice_PE("MultihomedConfig", "DeviceName");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	
	
	private void TestCase38() {
		try {
			logger= ExtentTestManager.startTest("TC-38 : deleteCPEdeviceFunction_NewlyCreatedDevice_Multihomed");
			MultihomedHelper.get().deleteDevice_CPE("MultihomedConfig", "CPE_DeviceName");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}

	
	
	
	private void TestCase39() {
		try {
			logger= ExtentTestManager.startTest("TC-39 : deleteServiceFunction_Multihomed");
			MultihomedHelper.get().deleteSevice1("MultihomedConfig", "ServiceIdentification");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Method failed");
			System.out.println(  e+" : Method failed");
		}
		ExtentTestManager.endTest();
	}
	
	
	

					
		 
					
					
		}
	
