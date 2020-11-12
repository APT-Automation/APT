package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author ASingh4-adm
 *
 */
public class APT_MCS_CreateOrder_IPVPNCPESolutionL3 extends DriverTestcase{
	APT_Login Login=new APT_Login();
	
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_IPVPNCPESolutionL3", priority=0)
	public void CPESolutionL3(Map<String, String> map) throws Exception {
		setup();
		Login.APT_Login_1(map.get("url for the Product"));
		
		if(map.get("New Customer").equalsIgnoreCase("Yes")) {
		logger= ExtentTestManager.startTest("CreateCustomer-CPEL3"); 
		APT_IPVPNHelper.get().createnewcustomer("ipvpnservice", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));		
		ExtentTestManager.endTest();
		}else {
		
		logger= ExtentTestManager.startTest("selectCustomertocreateOrder-CPEL3");
			APT_IPVPNHelper.get().selectCustomertocreateOrder("ipvpnservice",map.get("ChooseCustomerToBeSelected"),map.get("Name1"),map.get("Name2"));
			ExtentTestManager.endTest();
		}
		logger= ExtentTestManager.startTest("selectCustomertocreateOrder-IPAccess");
		APT_IPVPNHelper.get().selectCustomertocreateOrder("ipvpnservice",map.get("ChooseCustomerToBeSelected"),map.get("Name1"),map.get("Name2"));
		ExtentTestManager.endTest();
	
			logger= ExtentTestManager.startTest("createneworderservice-CPEL3");
			APT_IPVPNHelper.get().createneworderservice("ipvpnservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));	
			ExtentTestManager.endTest();
			logger= ExtentTestManager.startTest("verifyservicetypeandSubtype-CPEL3");
		 APT_IPVPNHelper.get().verifyservicetypeandSubtype("ipvpnservice", map.get("ServiceType"),map.get("ServiceSubType"));
		 ExtentTestManager.endTest();
		 logger= ExtentTestManager.startTest("createservice-CPEL3");
		 APT_IPVPNHelper.get().createservice("ipvpnservice", map.get("ServiceIdentification"),map.get("Remarks"),map.get("Email"),map.get("Phone"),map.get("DeliveryChannel"),map.get("Package"),
				map.get("Management Vpn"),map.get("VPN Topology"),map.get("Wholesale VPN/Global MPLS"),map.get("Multicast Group Address"),map.get("Multicast Group Pool"),map.get("Multicast Threshold (in kbps)"),map.get("MulticastCheckbox"),
				map.get("AllowSMCEmail"),map.get("ProactiveNotification"),map.get("DialUserAdmin"),map.get("ApplicationAwareNetwork"),map.get("Performance Reporting Type"));
		 ExtentTestManager.endTest();
		 
		 logger= ExtentTestManager.startTest("verifyCustomerDetailsInformation-CPEL3");	
		 	APT_IPVPNHelper.get().selectCustomertocreateOrder("ipvpnservice",map.get("ChooseCustomerToBeSelected"),map.get("Name1"),map.get("Name2"));	
		APT_IPVPNHelper.get().verifyCustomerDetailsInformation("ipvpnservice", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));
		ExtentTestManager.endTest();
	
		
		//logger= ExtentTestManager.startTest("verifyUserDetailsInformation-CPEL3");
	 	//APT_IPVPNHelper.get().selectCustomertocreateOrder("ipvpnservice",map.get("ChooseCustomerToBeSelected"),map.get("Name1"),map.get("Name2"));
	//APT_IPVPNHelper.get().createnewuser("ipvpnservice", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), map.get("EditEmail"), map.get("EditPhone"),
		//	 map.get("User-Roles"), map.get("User_Hide-Services"), map.get("User_IPV4CommandCisco"), map.get("User_IPV4CommandHuawai"), map.get("User_IPV6CommandCisco"));
	//APT_IPVPNHelper.get().Viewnewuser("ipvpnservice", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"),
		//	 map.get("User-Roles"), map.get("User_Hide-Services"), map.get("User_IPV4CommandCisco"), map.get("User_IPV4CommandHuawai"), map.get("User_IPV6CommandCisco"));
	//APT_IPVPNHelper.get().Editnewuser("ipvpnservice", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"), map.get("UserEmail"), map.get("Phone"),
		//	 map.get("User-Roles"), map.get("User_Hide-Services"), map.get("User_IPV4CommandCisco"), map.get("User_IPV4CommandHuawai"), map.get("User_IPV6CommandCisco"),
			// map.get("EditUser-Roles"), map.get("EditUser_Hide-Services"), map.get("EditUser_IPV4CommandCisco"), map.get("EditUser_IPV4CommandHuawai"), map.get("EditUser_IPV6CommandCisco"));
	//APT_IPVPNHelper.get().Deletenewuser("ipvpnservice", map.get("UserName"));

	
	 logger= ExtentTestManager.startTest("verifyOrderDetailsInformation-CPEL3");
	 APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().verifyorderpanelinformation_Neworder("ipvpnservice", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("verifyorderpanel_editorder-CPEL3");
	APT_IPVPNHelper.get().verifyorderpanel_editorder("ipvpnservice", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("verifyorderpanel_changeorder-CPEL3");
	APT_IPVPNHelper.get().verifyorderpanel_changeorder("ipvpnservice", map.get("ChangeOrder_OrderNumber1"), map.get("ChangeOrder_VoicelineNumber"),map.get("CreateNewOrder"));	
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("verifyServicepanelinviewservicepage-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().verifyservicepanelInformationinviewservicepage("ipvpnservice", map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceSubType") , map.get("Remarks") , map.get("EditEmail") , map.get("EditPhone"));
	APT_IPVPNHelper.get().verifyservicepanel_links("ipvpnservice",map.get("ServiceSubType"), map.get("EditRemarks"), map.get("Remarks"), map.get("ChangeOrder_OrderNumber"),
			map.get("ServiceIdentification"), map.get("ServiceType"), map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired"));
	
	ExtentTestManager.endTest();
	
	logger= ExtentTestManager.startTest("Editservice-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().Editservice("ipvpnservice", map.get("ServiceIdentification"),map.get("Remarks"),map.get("Email"),map.get("Phone"),map.get("DeliveryChannel"),map.get("Package"),
			map.get("Management Vpn"),map.get("VPN Topology"),map.get("Wholesale VPN/Global MPLS"),map.get("Multicast Group Address"),map.get("Multicast Group Pool"),map.get("Multicast Threshold (in kbps)"));
	
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("syncservices-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().syncservices("ipvpnservice");
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("Dumpservice-CPEL3");
	APT_IPVPNHelper.get().Dumpservice("ipvpnservice", map.get("ServiceIdentification"),map.get("Remarks"),map.get("Email"),map.get("Phone"),map.get("DeliveryChannel"),map.get("Package"),
			map.get("Management Vpn"),map.get("VPN Topology"),map.get("Wholesale VPN/Global MPLS"),map.get("Multicast Group Address"),map.get("Multicast Group Pool"),map.get("Multicast Threshold (in kbps)"));
	ExtentTestManager.endTest();
    logger= ExtentTestManager.startTest("verifyManagementOptionspanel-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().verifyManagementOptionspanel("ipvpnservice",map.get("ServiceSubType"));
	ExtentTestManager.endTest();
	
	logger= ExtentTestManager.startTest("AddManageUser-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddManageUser("ipvpnservice",map.get("ServiceSubType"),map.get("ManageUserName"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("ViewManageUser-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().ViewManageUser("ipvpnservice",map.get("ServiceSubType"),map.get("EditUserName"),map.get("ManageUserName"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("EditManageUser-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().EditManageUser("ipvpnservice",map.get("ServiceSubType"),map.get("EditUserName"),map.get("ManageUserName"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("DeleteManageUser-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().DeleteManageUser("ipvpnservice",map.get("ServiceSubType"),map.get("EditUserName"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("AddVPNAlis-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddVPNAlis("ipvpnservice",map.get("ServiceSubType"),map.get("VPN Name"),map.get("VPN Alis"));
	ExtentTestManager.endTest();
	
	logger= ExtentTestManager.startTest("EditVPNAlis-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().EditVPNAlis("ipvpnservice",map.get("ServiceSubType"),map.get("VPN Name"),map.get("VPN Alis"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("DeleteVPNAlis-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().DeleteVPNAlis("ipvpnservice",map.get("ServiceSubType"),map.get("VPN Name"),map.get("VPN Alis"));
	ExtentTestManager.endTest();
	
	logger= ExtentTestManager.startTest("AddVPNSITEOrder-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddVPNSiteOrder("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Device Country"),map.get("VPN Device City"),map.get("Physical Site"),map.get("VPN Vendor/Model"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),
			map.get("Router Id"),map.get("Management Address"),map.get("Voip Service"),map.get("HUB/SPOKE"),map.get("SelectSiteToggle"),map.get("SelectCityToggle"),map.get("Device_Xng_City_Name"),map.get("Device_Xng_City_Code"),map.get("VoIPCheckBox"),map.get("DeviceToggle"),map.get("Physical_Site"));
	APT_IPVPNHelper.get().AddVPNSiteOrderPlus("ipvpnservice", map.get("ServiceSubType"), map.get("VPN Device Country"),map.get("VPN Device City"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),map.get("CSRName"),map.get("Site Order Type"),
			map.get("IAReference"),map.get("IV Reference"),map.get("PerfReport"),map.get("PerCoS"),map.get("RouterIPv4"),map.get("RouterIPv6"),map.get("CPEName"),map.get("VoIPCheckBox"),map.get("Voip Service"),map.get("VirtualCPEcheckbox"),
			map.get("SelectSiteToggle"),map.get("SelectCityToggle"),map.get("Device_Xng_City_Name"),map.get("Device_Xng_City_Code"),map.get("DSL_Site"),map.get("Speedboat_Site"),map.get("ActelisBased"),map.get("Physical_Site"));
	APT_IPVPNHelper.get().AddVPNSiteOrder3("ipvpnservice", map.get("ServiceSubType"), map.get("VPN Device Country"),map.get("VPN Device City"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),map.get("CSRName"),map.get("Site Order Type"),
			map.get("IAReference"),map.get("IV Reference"),map.get("PerfReport"),map.get("PerCoS"),map.get("RouterIPv4"),map.get("RouterIPv6"),map.get("CPEName"),map.get("VoipService"),
		map.get("SelectSiteToggle"),map.get("SelectCityToggle"),map.get("Device_Xng_City_Name"),map.get("Device_Xng_City_Code"),map.get("VoIPCheckBox"),map.get("DSL_Site"),map.get("Speedboat_Site"),map.get("ActelisBased"),map.get("CNG-Option"),map.get("CNG"),map.get("Physical_Site"));
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().SwifNetSpoke("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Device Country"),map.get("VPN Device City"),map.get("Spoke_Physical Site"),map.get("VPN Vendor/Model"),map.get("Spoke_VPN Site Order Num"),map.get("Spoke_VPN Site Alis"),
			map.get("Spoke_Router Id"),map.get("Spoke_Management Address"),map.get("Voip Service"),map.get("Spoke_HUB/SPOKE"),map.get("SelectSiteToggle"),map.get("SelectCityToggle"),map.get("Device_Xng_City_Name"),map.get("Spoke_Device_Xng_City_Code"),map.get("VoIPCheckBox"),map.get("DeviceToggle"),map.get("Physical_Site"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("verifyAddedVPNSiteInformation-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().verifyAddedVPNSitePlusInformation_View("ipvpnservice", map.get("ServiceSubType"), map.get("VPN Device Country"),map.get("VPN Device City"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),map.get("CSRName"),map.get("Site Order Type"),
			map.get("IAReference"),map.get("IV Reference"),map.get("PerfReport"),map.get("PerCoS"),map.get("RouterIPv4"),map.get("RouterIPv6"),map.get("CPEName"));
	APT_IPVPNHelper.get().VerifyVPNSiteOrder3("ipvpnservice", map.get("ServiceSubType"), map.get("VPN Device Country"),map.get("VPN Device City"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),map.get("CSRName"),map.get("Site Order Type"),
			map.get("IAReference"),map.get("IV Reference"),map.get("PerfReport"),map.get("PerCoS"),map.get("RouterIPv4"),map.get("RouterIPv6"),map.get("CPEName"),map.get("VoipService"));
	APT_IPVPNHelper.get().VerifyVPNSiteOrder("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Device Country"),map.get("VPN Device City"),map.get("Physical Site"),map.get("VPN Vendor/Model"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),
			map.get("Router Id"),map.get("Management Address"),map.get("Voip Service"),map.get("HUB/SPOKE"));
	APT_IPVPNHelper.get().VerifyVPNSiteOrderSpoke("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Device Country"),map.get("VPN Device City"),map.get("Physical Site"),map.get("VPN Vendor/Model"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),
			map.get("Router Id"),map.get("Management Address"),map.get("Voip Service"),map.get("Spoke_HUB/SPOKE"));
	ExtentTestManager.endTest();
	/*
	logger= ExtentTestManager.startTest("verifyEditDeviceFunction-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().EditVPNSiteOrderPlus("ipvpnservice", map.get("ServiceSubType"), map.get("VoIPCheckBox"),map.get("Voip Service"),map.get("VirtualCPEcheckbox"),map.get("VPN Site Alis"),map.get("CSRName"),map.get("Site Order Type"),
			map.get("IAReference"),map.get("IV Reference"),map.get("PerfReport"),map.get("PerCoS"),map.get("RouterIPv4"),map.get("RouterIPv6"),map.get("CPEName"),map.get("VPN Site Order Num"),map.get("Physical_Site"));
	APT_IPVPNHelper.get().EditVPNSiteOrder3("ipvpnservice", map.get("ServiceSubType"), map.get("VPN Device Country"),map.get("VPN Device City"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),map.get("CSRName"),map.get("Site Order Type"),
			map.get("IAReference"),map.get("IV Reference"),map.get("PerfReport"),map.get("PerCoS"),map.get("RouterIPv4"),map.get("RouterIPv6"),map.get("CPEName"),map.get("VoipService"),map.get("Physical_Site"));
	APT_IPVPNHelper.get().EditVPNSiteOrder("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Device Country"),map.get("VPN Device City"),map.get("Physical Site"),map.get("VPN Vendor/Model"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),
			map.get("Router Id"),map.get("Management Address"),map.get("Voip Service"),map.get("HUB/SPOKE"),map.get("Physical_Site"));
	APT_IPVPNHelper.get().EditVPNSiteOrderSpoke("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Device Country"),map.get("VPN Device City"),map.get("Physical Site"),map.get("VPN Vendor/Model"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),
			map.get("Router Id"),map.get("Management Address"),map.get("Voip Service"),map.get("Spoke_HUB/SPOKE"),map.get("Physical_Site"));
	APT_IPVPNHelper.get().verifyEditDeviceFunction("ipvpnservice", map.get("ServiceSubType"),map.get("Router Id"),map.get("ManagementAdd_Re"),map.get("VPN Vendor/Model"));
	APT_IPVPNHelper.get().verifyEditDeviceFunctionSpoke("ipvpnservice", map.get("ServiceSubType"),map.get("Router Id"),map.get("ManagementAdd_Re"),map.get("VPN Vendor/Model"));
	ExtentTestManager.endTest();
	*/
	logger= ExtentTestManager.startTest("showNewInfovistaReport-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().shownewInfovista("ipvpnservice");
	ExtentTestManager.endTest();
	
	logger= ExtentTestManager.startTest("AddNewDevice-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddNewDevice("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Vendor/Model"),map.get("Router Id"),map.get("Management Address"),map.get("Snmp V3 User Name"));
	ExtentTestManager.endTest();
	 logger= ExtentTestManager.startTest("AddWholesaleInterconnect-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddWholesaleInterconnect("ipvpnservice",map.get("ServiceSubType"), map.get("WholesaleDeviceName"), map.get("WholesaleInterface"));
	APT_IPVPNHelper.get().EditWholesaleInterconnect("ipvpnservice",map.get("ServiceSubType"), map.get("IV_Management"));
	APT_IPVPNHelper.get().DeleteWholesaleInterconnect("ipvpnservice",map.get("ServiceSubType"),map.get("DeleteWholesale"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("CreateCPEDevice-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddCPEDevice("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_VPN Vendor/Model"),map.get("CPE_Router Id"),map.get("CPE_Management Address"),map.get("VPN Site Order Num"),map.get("CPEGetAddress"),map.get("CPEAvailableBlock"),map.get("CPEPremiseName"),map.get("CPEPremiseCode")
			,map.get("CPEPremiseToggle"),map.get("CPEJitterRadio"),map.get("CPEConnectPortalTal"),map.get("CPEConnectPortalSsh"),map.get("CPEPremiseddn"),map.get("CPESNMP3"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("ViewCPEDevice-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().ViewCPEDevice("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_VPN Vendor/Model"),map.get("CPE_Router Id"),map.get("CPE_Management Address"),map.get("VPN Site Order Num"),map.get("CPEGetAddress"),map.get("CPEAvailableBlock"),map.get("CPEPremiseName"),map.get("CPEPremiseCode")
			,map.get("CPEPremiseToggle"),map.get("CPEJitterRadio"), map.get("VPN Device Country"),map.get("VPN Device City"),map.get("CSRName"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("EditCPEDevice-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().EditCPEDevice("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_VPN Vendor/Model"),map.get("CPE_Router Id"),map.get("CPE_Management Address"),map.get("VPN Site Order Num"),map.get("CPEGetAddress"),map.get("CPEAvailableBlock"),map.get("CPEPremiseName"),map.get("CPEPremiseCode")
			,map.get("CPEPremiseToggle"),map.get("CPEJitterRadio"),map.get("CPEConnectPortalTal"),map.get("CPEConnectPortalSsh"),map.get("CPEPremiseddn"),map.get("CPESNMP3"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("AddInterfaceCPE-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddInterfaceCPE("ipvpnservice", map.get("ServiceSubType"),map.get("CPEBearerType"),map.get("CPEBandwidthE1"),map.get("CPEBandwidthE3"),map.get("CPEBandwidthSTM"),map.get("CPELink"),map.get("CpeEncapsulation"),map.get("CpeClockSource"),map.get("CpePrimary/Backup")
			,map.get("CpenterfaceDirection"),map.get("CpeVoiceline"), map.get("CpeIVManagement"),map.get("CpeIVBitCounter"),map.get("CpeAddRange"),map.get("CpeSecondaryIp"),map.get("CpeNetmask"),map.get("CpeSpeed"),map.get("CpeDuplex"),map.get("CpeInterface"),map.get("CpeInterfaceAddRangeDdn"),map.get("CpeInterfaceAddRangeText"),map.get("CpeAddress"),
			map.get("InterfaceGetAddress"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"),map.get("CpeAddInterfaceButton"),map.get("InterfaceCNGReferance"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("EditInterfaceCPE-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().EditInterfaceCPE("ipvpnservice", map.get("ServiceSubType"),map.get("CPEBearerType"),map.get("CPEBandwidthE1"),map.get("CPEBandwidthE3"),map.get("CPEBandwidthSTM"),map.get("CPELink"),map.get("CpeEncapsulation"),map.get("CpeClockSource"),map.get("CpePrimary/Backup")
			,map.get("CpenterfaceDirection"),map.get("CpeVoiceline"), map.get("CpeIVManagement"),map.get("CpeIVBitCounter"),map.get("CpeAddRange"),map.get("CpeSecondaryIp"),map.get("CpeNetmask"),map.get("CpeSpeed"),map.get("CpeDuplex"),map.get("CpeInterface"),map.get("CpeInterfaceAddRangeDdn"),map.get("CpeInterfaceAddRangeText"),map.get("CpeAddress"),
			map.get("InterfaceGetAddress"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"),map.get("CpeAddInterfaceButton"),map.get("InterfaceCNGReferance"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("CPEDeviceConfiguration-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().CPEDeviceConfiguration("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_VPN Vendor/Model"),map.get("CPE_Router Id"),map.get("CPE_Management Address"),map.get("VPN Site Order Num"),map.get("CPEGetAddress"),map.get("CPEAvailableBlock"),map.get("CPEPremiseName"),map.get("CPEPremiseCode")
			,map.get("CPEPremiseToggle"),map.get("CPEJitterRadio"),map.get("CPEConnectPortalTal"),map.get("CPEConnectPortalSsh"),map.get("CPEPremiseddn"),map.get("CPESNMP3"));
	ExtentTestManager.endTest();
	
	logger= ExtentTestManager.startTest("SelectInterface-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().SelectInterface("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"),map.get("CpeInterface"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("DeleteInterfaceCPE-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().DeleteInterfaceCPE("ipvpnservice", map.get("ServiceSubType"),map.get("CPEBearerType"),map.get("CPEBandwidthE1"),map.get("CPEBandwidthE3"),map.get("CPEBandwidthSTM"),map.get("CPELink"),map.get("CpeEncapsulation"),map.get("CpeClockSource"),map.get("CpePrimary/Backup")
			,map.get("CpenterfaceDirection"),map.get("CpeVoiceline"), map.get("CpeIVManagement"),map.get("CpeIVBitCounter"),map.get("CpeAddRange"),map.get("CpeSecondaryIp"),map.get("CpeNetmask"),map.get("CpeSpeed"),map.get("CpeDuplex"),map.get("CpeInterface"),map.get("CpeInterfaceAddRangeDdn"),map.get("CpeInterfaceAddRangeText"),map.get("CpeAddress"),
			map.get("InterfaceGetAddress"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"),map.get("CpeAddInterfaceButton"),map.get("InterfaceCNGReferance"));
	ExtentTestManager.endTest();
	
	if ((map.get("ServiceSubType").contains("IPVPN"))&& (!map.get("ServiceSubType").equalsIgnoreCase("IPVPN Access"))) {
		logger= ExtentTestManager.startTest("addPPPconfiguration-CPEL3");	
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().CPEdevice_clickOnPPPconfiguration("ipvpnservice", map.get("CPE_Router Id"),map.get("VPN Site Order Num"));
	APT_IPVPNHelper.get().pppConfiguration("ipvpnservice");
	APT_IPVPNHelper.get().addPPPconfiguration("ipvpnservice", map.get("CPE_Router Id") , map.get("CPE_pppconfiguration_framedWANipAddress"),
			map.get("CPE_pppConfiguration_framedRoute0"), map.get("CPE_pppConfiguration_framedRoute1"), map.get("CPE_pppConfiguration_framedRoute2"), 
			map.get("CPE_pppConfiguration_framedRoute3"), map.get("CPE_pppConfiguration_framedRoute4"), map.get("CPE_pppConfiguration_framedRoute5"),
			map.get("CPE_pppConfiguration_framedRoute6"), map.get("CPE_pppConfiguration_framedRoute7"), 
			map.get("CPE_pppConfig_uniQOSprofileName"), map.get("CPE_pppConfig_selectQosParameters"), map.get("CPE_pppConfig_QosParametertextValue"),
			map.get("CPE_pppconfig_Savalidation"), map.get("CPE_pppConfig_uniSphereEnable"), map.get("CPE_ppp_uniSphereVersion"), map.get("CPE_ppp_IPv6Parameters"),
			map.get("CPE_ppp_framedIPv6Prefix"), map.get("CPE_ppp_framedIPv6Address"), map.get("CPE_ppp_delegateIPv6Prefix"), map.get("CPE_ppp_framedIPv6route0"),
			map.get("CPE_ppp_framedIPv6route1"), map.get("CPE_ppp_framedIPv6Route2"), map.get("CPE_ppp_framedIPv6route3"), map.get("CPE_ppp_framedIPv6Route4"),
			map.get("CPE_ppp_framedIPv6Route5"), map.get("CPE_ppp_framedIPv6Route6"), map.get("CPE_ppp_framedIPv6route7"), map.get("CPE_pppConfig_uniIPv6LocalInterface"),
			map.get("CPE_pppConfig_uniIPv6VirtualRouter"), map.get("CPE_pppConfig_uniIngressStatistics"));
	APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Device successfully created.");
	ExtentTestManager.endTest();		
	logger= ExtentTestManager.startTest("viewPPPconfiguration-CPEL3");
	APT_IPVPNHelper.get().viewPPPconfiguration("ipvpnservice", map.get("CPE_Router Id") , map.get("CPE_pppconfiguration_framedWANipAddress"),
			map.get("CPE_pppConfiguration_framedRoute0"), map.get("CPE_pppConfiguration_framedRoute1"), map.get("CPE_pppConfiguration_framedRoute2"), 
			map.get("CPE_pppConfiguration_framedRoute3"), map.get("CPE_pppConfiguration_framedRoute4"), map.get("CPE_pppConfiguration_framedRoute5"),
			map.get("CPE_pppConfiguration_framedRoute6"), map.get("CPE_pppConfiguration_framedRoute7"), map.get("CPE_pppConfig_uniVirtualRouterName"), 
			map.get("CPE_pppConfig_uniQOSprofileName"), map.get("CPE_pppConfig_selectQosParameters"), map.get("CPE_pppConfig_QosParametertextValue"),
			map.get("CPE_pppconfig_Savalidation"), map.get("CPE_pppConfig_uniSphereEnable"), map.get("CPE_ppp_uniSphereVersion"), map.get("CPE_ppp_IPv6Parameters"),
			map.get("CPE_ppp_framedIPv6Prefix"), map.get("CPE_ppp_framedIPv6Address"), map.get("CPE_ppp_delegateIPv6Prefix"), map.get("CPE_ppp_framedIPv6route0"),
			map.get("CPE_ppp_framedIPv6route1"), map.get("CPE_ppp_framedIPv6Route2"), map.get("CPE_ppp_framedIPv6route3"), map.get("CPE_ppp_framedIPv6Route4"),
			map.get("CPE_ppp_framedIPv6Route5"), map.get("CPE_ppp_framedIPv6Route6"), map.get("CPE_ppp_framedIPv6route7"));
	
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("editPPPconfiguration-CPEL3");
	APT_IPVPNHelper.get().editPPPconfiguration("ipvpnservice", map.get("CPE_editPPPconfig_framedWANipAddress"),
			map.get("CPE_editPPPconfig_framedRoute0"), map.get("CPE_editPPPconfig_framedRoute1"), map.get("CPE_editPPPconfig_framedRoute2"), 
			map.get("CPE_editPPPconfig_framedRoute3"), map.get("CPE_editPPPconfig_framedRoute4"), map.get("CPE_editPPPconfig_framedRoute5"),
			map.get("CPE_editPPPconfig_framedRoute6"), map.get("CPE_editPPPconfig_framedRoute7"), 
			map.get("CPE_editPPPConfig_uniQOSprofileName"), map.get("CPE_editPPPConfig_selectQosParameters"), map.get("CPE_editPPPConfig_QosParametertextValue"),
			map.get("CPE_editPPPconfig_SAvalidation"), map.get("CPE_editPPPConfig_uniSphereEnable"), map.get("CPE_editPPPconfig_uniSphereVersion"), map.get("CPE_editPPPconfig_IPv6Parameters"),
			map.get("CPE_editPPPconfig_framedIPv6Prefix"), map.get("CPE_editPPPconfig_framedIPv6Address"), map.get("CPE_editPPPconfig_delegateIPv6Prefix"), map.get("CPE_editPPPconfig_framedIPv6route0"),
			map.get("CPE_editPPPconfig_framedIPv6route1"), map.get("CPE_editPPPconfig_framedIPv6Route2"), map.get("CPE_editPPPconfig_framedIPv6route3"), map.get("CPE_editPPPconfig_framedIPv6Route4"),
			map.get("CPE_editPPPconfig_framedIPv6Route5"), map.get("CPE_editPPPconfig_framedIPv6Route6"), map.get("CPE_editPPPconfig_framedIPv6route7"), map.get("CPE_editPPPConfig_uniIPv6LocalInterface"),
			map.get("CPE_editPPPConfig_uniIPv6VirtualRouter"), map.get("CPE_editPPPConfig_uniIngressStatistics"));
	APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Device successfully updated.");
	
	
	//logger= ExtentTestManager.startTest("deletePPPconfiguration-CPEL3");
	//APT_IPVPNHelper.get().deletePPPconfiguration("ipvpnservice");
	
	ExtentTestManager.endTest();
	}
	logger= ExtentTestManager.startTest("AddMultilinkCPE-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddMultilinkCPE("ipvpnservice", map.get("ServiceSubType"),map.get("MultilinkEthernetCheckbox"),map.get("CPEBandwidthE1"),map.get("CPEBandwidthE3"),map.get("CPEBandwidthSTM"),map.get("CPELink"),map.get("CpeEncapsulation"),map.get("CpeClockSource"),map.get("CpePrimary/Backup")
			,map.get("CpenterfaceDirection"),map.get("CpeVoiceline"), map.get("CpeIVManagement"),map.get("CpeIVBitCounter"),map.get("CpeAddRange"),map.get("CpeSecondaryIp"),map.get("CpeNetmask"),map.get("CpeSpeed"),map.get("CpeDuplex"),map.get("CpeInterface"),map.get("CpeMultilinkAddRangeDdn"),map.get("CpeMultilinkAddRangeText"),map.get("CpeAddress"),
			map.get("InterfaceGetAddress"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"),map.get("CpeAddInterfaceButton"),map.get("InterfaceCNGReferance"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("EditMultilinkCPE-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().EditMultilinkCPE("ipvpnservice", map.get("ServiceSubType"),map.get("MultilinkEthernetCheckbox"),map.get("CPEBandwidthE1"),map.get("CPEBandwidthE3"),map.get("CPEBandwidthSTM"),map.get("CPELink"),map.get("CpeEncapsulation"),map.get("CpeClockSource"),map.get("CpePrimary/Backup")
			,map.get("CpenterfaceDirection"),map.get("CpeVoiceline"), map.get("CpeIVManagement"),map.get("CpeIVBitCounter"),map.get("CpeAddRange"),map.get("CpeSecondaryIp"),map.get("CpeNetmask"),map.get("CpeSpeed"),map.get("CpeDuplex"),map.get("CpeInterface"),map.get("CpeMultilinkAddRangeDdn"),map.get("CpeMultilinkAddRangeText"),map.get("CpeAddress"),
			map.get("InterfaceGetAddress"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"),map.get("CpeAddInterfaceButton"),map.get("InterfaceCNGReferance"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("DeleteMultilinkCPE-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().DeleteMultilinkCPE("ipvpnservice", map.get("ServiceSubType"),map.get("MultilinkEthernetCheckbox"),map.get("CPEBandwidthE1"),map.get("CPEBandwidthE3"),map.get("CPEBandwidthSTM"),map.get("CPELink"),map.get("CpeEncapsulation"),map.get("CpeClockSource"),map.get("CpePrimary/Backup")
			,map.get("CpenterfaceDirection"),map.get("CpeVoiceline"), map.get("CpeIVManagement"),map.get("CpeIVBitCounter"),map.get("CpeAddRange"),map.get("CpeSecondaryIp"),map.get("CpeNetmask"),map.get("CpeSpeed"),map.get("CpeDuplex"),map.get("CpeInterface"),map.get("CpeMultilinkAddRangeDdn"),map.get("CpeMultilinkAddRangeText"),map.get("CpeAddress"),
			map.get("InterfaceGetAddress"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"),map.get("CpeAddInterfaceButton"),map.get("InterfaceCNGReferance"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("AddRoutesCPE-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddRoutesCPE("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"), map.get("RoutesDestination"), map.get("RoutesNetMask"), map.get("RoutesMetrics"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("EditRoutesCPE-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().EditRoutesCPE("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"), map.get("RoutesDestination"), map.get("RoutesNetMask"), map.get("RoutesMetrics"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("DeleteRoutesCPE-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().DeleteRoutesCPE("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"), map.get("RoutesDestination"), map.get("RoutesNetMask"), map.get("RoutesMetrics"));
	ExtentTestManager.endTest();
	logger= ExtentTestManager.startTest("AddRouterTool-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddRouterTool("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_Router Id"),map.get("VPN Site Order Num"), map.get("RouterToolIPV4"), map.get("RouterToolIPV6"));
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().AddRouterTool4("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Device Country"),map.get("VPN Device City"),map.get("Physical Site"),map.get("VPN Vendor/Model"),map.get("VPN Site Order Num"),map.get("VPN Site Alis"),
			map.get("Router Id"), map.get("RouterToolIPV4"), map.get("RouterToolIPV6"),map.get("HUB/SPOKE"));
	ExtentTestManager.endTest();
	
	logger= ExtentTestManager.startTest("DeleteCPEDevice-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().DeleteCPEDevice("ipvpnservice", map.get("ServiceSubType"),map.get("CPE_VPN Vendor/Model"),map.get("CPE_Router Id"),map.get("CPE_Management Address"),map.get("VPN Site Order Num"),map.get("CPEGetAddress"),map.get("CPEAvailableBlock"),map.get("CPEPremiseName"),map.get("CPEPremiseCode")
			,map.get("CPEPremiseToggle"),map.get("CPEJitterRadio"),map.get("CPEConnectPortalTal"),map.get("CPEConnectPortalSsh"),map.get("CPEPremiseddn"),map.get("CPESNMP3"));
	ExtentTestManager.endTest();
	
	if (map.get("ServiceSubType").contains("IPVPN")) {
		
	logger= ExtentTestManager.startTest("PEdevice-CPEL3"); 
	
	//verify whether Equipment panel is available	
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	boolean EquipmentPanel=APT_IPVPNHelper.get().findPanelHeader("ipvpnservice", "Provider Equipment (PE)",map.get("VPN Site Order Num"));

		if(EquipmentPanel) {
			
			APT_IPVPNHelper.get().SelectPEdevice_existingDevice("ipvpnservice", map.get("ExistingPEdevice"));
			
			APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Site device created successfully");
			
			APT_IPVPNHelper.get().verifyValuesforProviderEqiupment("ipvpnservice");
			
			boolean link = APT_IPVPNHelper.get().fetchdeviceInterface("ipvpnservice");
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Provider Equipment (PE) panel is not displaying");
			System.out.println("Provider Equipment (PE) panel is not displaying");
		}

		ExtentTestManager.endTest();
		logger= ExtentTestManager.startTest("PEdevice_addInterface-CPEL3");
		String interfaceCreation = "No";
		String multilinkCreation = "No";
		String interfaceName = "Null";
		String interfaceNameUpdated = "Null";
		String multiLink = "Null";
		
			String vendorModel = APT_IPVPNHelper.get().fetchVendorModel_PE("ipvpnservice");
			
			String managementAddress = APT_IPVPNHelper.get().fetchManagementAddress_PE("ipvpnservice");
			
			if(map.get("DSLsiteOrder").equalsIgnoreCase("Yes")) {
				
				if(vendorModel.contains("Juniper")) {
					APT_IPVPNHelper.get().routerPanel_juniper("ipvpnservice", map.get("command_ipv4"), map.get("command_ipv6"), managementAddress,
							map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
				}
				else if(vendorModel.contains("Cisco")) {
					
					APT_IPVPNHelper.get().routerPanel_Cisco("ipvpnservice", map.get("command_ipv4"), map.get("command_ipv6"), managementAddress,
							map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
					
				}
				
				APT_IPVPNHelper.get().clickOnAddInterfaceLink("ipvpnservice");
				APT_IPVPNHelper.get().addInterface_DSlsiteorderSelected("ipvpnservice",map.get("ServiceSubType"), map.get("existingAddressIPv4Selection"), map.get("existingAddressIPv4DropdownValue"), map.get("newAddressIpv4Selection"), map.get("newAddressIpv4Range"), 
						map.get("subnetSizeValue_IPv4"), map.get("availableBlocksValue_IPv4"), map.get("addInterface_link"), map.get("addInterace_DSLsiteOrderSelected_BearerType"), map.get("addInterface_encapsulation"),
						map.get("addInterface_slot"), map.get("addInterface_port"), map.get("addInterface_vlanId"),map.get("addInterface_configureInterfaceOnDevice"),map.get("addInterface_Ivmanagement"),
						map.get("existingAddressRangeIPv6selection"), map.get("existingAddressIPv6DropdownValue"), map.get("newAddressRangeIpv6selection"),
						map.get("newAddressrangeIPv6"), map.get("subnetSizeValue_IPv6"), map.get("availableBlocksValue_IPv6"), map.get("addInterface_cisco_pppEncapsulation"), map.get("addInterface_cisco_vpitextField"), map.get("addInterface_cisco_vciTextField"), 
						map.get("addInterace_cisco_dslDownStreamSpeed"), map.get("addInterace_cisco_dslUpStreamSpeed"), map.get("addInterface_MBSdropdown"));
				APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Interface successfully created.");
				interfaceCreation = APT_IPVPNHelper.get().successmessageForInterfaceOrMultilinkCreation("ipvpnservice", "successfully created");
				
				
				APT_IPVPNHelper.get().clickOnAddMultilinkLink("ipvpnservice");
				APT_IPVPNHelper.get().addMultilink_DSLsiteOrderselected("ipvpnservice", map.get("ServiceSubType"), map.get("multilink_configureInterfaceOnDevice"), map.get("multilink_Link"), map.get("multilink_Encapsulation"),
					map.get("multilink_DSLsiteOrderSelected_PPPencapsulation"),map.get("multilink_DSLsiteOrderSelected_DSLdownstreamPCR"),
					map.get("multilink_DSLsiteOrderSelected_DSLupstreamSCR"), map.get("multilink_Ivmanagement"), map.get("multilink_UnitID"),
					map.get("addMultilink_existingAddressRangeIPv4selection"), map.get("addMultilink_newAddressRangeIpv4selection"), map.get("addMultilink_subnetSizeValue_IPv4"),
					map.get("addMultilik_availableBlocksValue_IPv4"), map.get("addMultilink_existingAddressIPv4DropdownValue"), map.get("addMultilink_newinterfaceAddressrangeIPv4"),
					map.get("addMultilink_existingAddressRangeIPv6selection"), map.get("addMultilink_newAddressRangeIpv6selection"), map.get("addMultilink_subnetSizeValue_IPv6"),
					map.get("addMultilink_availableBlocksValue_IPv6"), map.get("addMultilink_existingAddressIPv6DropdownValue"), map.get("addMultilink_newinterfaceAddressrangeIPv6"));
				APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Interface successfully created.");
				multilinkCreation = APT_IPVPNHelper.get().successmessageForInterfaceOrMultilinkCreation("ipvpnservice", "successfully created");
				
				
				APT_IPVPNHelper.get().clickOnAddLoopback("ipvpnservice");
				String interfaceName_loopback = APT_IPVPNHelper.get().addLoopback("ipvpnservice", map.get("addLoopback_interfaceAddress"), map.get("addLoopBack_Ivmanagement"), map.get("addLoopBack_configureOnBackupBRX")); 
				
			}
			else {
				if(vendorModel.contains("Juniper")) {
					
					APT_IPVPNHelper.get().routerPanel_juniper("ipvpnservice", map.get("command_ipv4"), map.get("command_ipv6"), managementAddress,
							map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
					
					APT_IPVPNHelper.get().clickOnAddInterfaceLink("ipvpnservice");
					interfaceName  = APT_IPVPNHelper.get().addInterface_Juniper("ipvpnservice", map.get("ServiceSubType"), map.get("existingAddressIPv4Selection"), map.get("existingAddressIPv4DropdownValue"), map.get("newAddressIpv4Selection"), map.get("newAddressIpv4Range"), 
							map.get("subnetSizeValue_IPv4"), map.get("availableBlocksValue_IPv4"), map.get("addInterface_link"), map.get("addInterface_Juniper_bearerType"), map.get("addInterface_juniper_encapsulation"),
							map.get("addInterface_slot"), map.get("addInterface_port"), map.get("addInterface_vlanId"), map.get("addInterface_unitId"), map.get("addInterface_pic"),
							map.get("addInterace_STM1number"), map.get("addInterface_bandwidth"), map.get("addInterface_cardType"),
							map.get("addInterface_frameType"), map.get("addInterface_clockSource"), map.get("addInterface_timeSlot"), map.get("addInterface_configureInterfaceOnDevice"),
							map.get("addInterface_Ivmanagement"),
							map.get("existingAddressRangeIPv6selection"), map.get("existingAddressIPv6DropdownValue"), map.get("newAddressRangeIpv6selection"),
							map.get("newAddressrangeIPv6"), map.get("subnetSizeValue_IPv6"), map.get("availableBlocksValue_IPv6"), map.get("addInterface_bearerNumber"));
					APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Interface successfully created.");
					interfaceCreation = APT_IPVPNHelper.get().successmessageForInterfaceOrMultilinkCreation("ipvpnservice", "successfully created");
					
					
					APT_IPVPNHelper.get().clickOnBreadCrump("ipvpnservice", map.get("ExistingPEdevice"));
					APT_IPVPNHelper.get().clickOnAddMultilinkLink("ipvpnservice");
					multiLink = APT_IPVPNHelper.get().addMultilink("ipvpnservice", map.get("ServiceSubType"), map.get("multilink_configureInterfaceOnDevice"), map.get("multilink_Link"), map.get("multilink_Encapsulation"),
									map.get("multilink_Slot"), map.get("multilink_Port"), map.get("multilink_Pic"), map.get("multilink_Ivmanagement"), map.get("multilink_UnitID"),
								map.get("addMultilink_existingAddressRangeIPv4selection"), map.get("addMultilink_newAddressRangeIpv4selection"), map.get("addMultilink_subnetSizeValue_IPv4"),
								map.get("addMultilik_availableBlocksValue_IPv4"), map.get("addMultilink_existingAddressIPv4DropdownValue"), map.get("addMultilink_newinterfaceAddressrangeIPv4"),
								map.get("addMultilink_existingAddressRangeIPv6selection"), map.get("addMultilink_newAddressRangeIpv6selection"), map.get("addMultilink_subnetSizeValue_IPv6"),
								map.get("addMultilink_availableBlocksValue_IPv6"), map.get("addMultilink_existingAddressIPv6DropdownValue"), map.get("addMultilink_newinterfaceAddressrangeIPv6"));
					APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Interface successfully created.");
					multilinkCreation = APT_IPVPNHelper.get().successmessageForInterfaceOrMultilinkCreation("ipvpnservice", "successfully created");
					
					
					//Edit Interface
					APT_IPVPNHelper.get().clickOnBreadCrump("ipvpnservice", map.get("VPN Site Order Num"));
				if(interfaceCreation.equalsIgnoreCase("Yes")) {
					APT_IPVPNHelper.get().PEinterface_clickOEditlink("ipvpnservice", interfaceName);
					interfaceNameUpdated  = APT_IPVPNHelper.get().editInterface_Juniper("ipvpnservice", map.get("ServiceSubType"), map.get("EditInterface_existingAddressIPv4Selection"), map.get("EditInterface_existingAddressIPv4DropdownValue"),
							map.get("EditInterface_newAddressIpv4Selection"), map.get("EditInterface_newAddressIpv4Range"), 
							map.get("EditInterface_subnetSizeValue_IPv4"), map.get("EditInterface_availableBlocksValue_IPv4"), map.get("EditInterface_link"), map.get("EditInterface_Juniper_bearerType"), map.get("EditInterface_encapsulation"),
							map.get("EditInterface_slot"), map.get("EditInterface_port"), map.get("EditInterface_vlanId"), map.get("EditInterface_unitId"), map.get("EditInterface_pic"),
							map.get("EditaddInterace_STM1number"), map.get("EditInterface_bandwidth"), map.get("EditInterface_cardType"),
							map.get("EditInterface_frameType"), map.get("EditInterface_clockSource"), map.get("EditInterface_timeSlot"), map.get("EditInterface_configureInterfaceOnDevice"),
							map.get("EditInterface_Ivmanagement"),map.get("EditInterface_existingAddressRangeIPv6selection"), map.get("EditInterface_existingAddressIPv6DropdownValue"),
							map.get("EditInterface_newAddressRangeIpv6selection"),map.get("EditInterface_newAddressrangeIPv6"), map.get("EditInterface_subnetSizeValue_IPv6"), map.get("EditInterface_availableBlocksValue_IPv6"),
							map.get("EditInterface_bearerNumber"), map.get("EditInterface_v4Configugration_selection"), map.get("EditInterface_v6Configugration_selection"));
					APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Interface successfully updated.");
					APT_IPVPNHelper.get().successmessageForInterfaceOrMultilinkCreation("ipvpnservice", "successfully updated");
					interfaceName = interfaceNameUpdated;
				}
					
					
				APT_IPVPNHelper.get().clickOnBreadCrump("ipvpnservice", map.get("VPN Site Order Num"));
				if(multilinkCreation.equalsIgnoreCase("Yes")) {
					APT_IPVPNHelper.get().PEinterface_clickOEditlink("ipvpnservice", multiLink);
					APT_IPVPNHelper.get().editMultilink("ipvpnservice", map.get("ServiceSubType"), map.get("EditMultilink_configureInterfaceOnDevice"), map.get("EditMultilink_link"), map.get("EditMultilink_encapsulation"),
							map.get("EditMultilink_slot"), map.get("EditMultilink_port"), map.get("EditMultilink_pic"), map.get("EditMultilink_Ivmanagement"), map.get("EditMultilink_unitId"),
							map.get("EditMultilink_existingAddressIPv4Selection"), map.get("EditMultilink_newAddressIpv4Selection"), map.get("EditMultilink_subnetSizeValue_IPv4"), map.get("EditMultilink_availableBlocksValue_IPv4"),
							map.get("EditMutilik_existingAddressIPv4DropdownValue"), map.get("EditMultilink_newAddressIpv4Range"), map.get("EditMultilink_existingAddressRangeIPv6selection"), 
							map.get("EditMultilink_newAddressRangeIpv6selection"), map.get("EditMultilink_subnetSizeValue_IPv6"), map.get("EditMultilink_availableBlocksValue_IPv6"), 
							map.get("EditMultilink_existingAddressIPv6DropdownValue"), map.get("EditMultilink_newAddressrangeIPv6"), map.get("EditMultilink_v4Configugration_selection"),
							map.get("EditMultilink_v6Configugration_selection"));
					APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Interface successfully updated.");
					
				}
					
				
				}
				else if(vendorModel.contains("Cisco")) {
					
					APT_IPVPNHelper.get().routerPanel_Cisco("ipvpnservice", map.get("command_ipv4"), map.get("command_ipv6"), managementAddress,
							map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
					
					APT_IPVPNHelper.get().clickOnAddInterfaceLink("ipvpnservice");
				    interfaceName =	APT_IPVPNHelper.get().addInterface_Cisco("ipvpnservice",map.get("ServiceSubType"), map.get("existingAddressIPv4Selection"), map.get("existingAddressIPv4DropdownValue"), map.get("newAddressIpv4Selection"),
							map.get("newAddressIpv4Range"), map.get("subnetSizeValue_IPv4"), map.get("availableBlocksValue_IPv4"), map.get("addInterface_link"), map.get("addInterface_Cisco_bearerType"),
							map.get("addInterface_Cisco_encapsulation"), map.get("addInterface_slot"), map.get("addInterface_port"), map.get("addInterface_vlanId"), map.get("addInterface_unitId"),
							map.get("addInterface_pic"), map.get("addInterace_STM1number"), map.get("addInterface_bandwidth"), map.get("addInterface_cardType"), map.get("addInterface_frameType"),
							map.get("addInterface_clockSource"), map.get("addInterface_timeSlot"), map.get("addInterface_configureInterfaceOnDevice"), map.get("addInterface_Ivmanagement"),
							map.get("addInterface_cisco_pppEncapsulation"), map.get("addInterface_cisco_vpitextField"), map.get("addInterface_cisco_vciTextField"), 
							map.get("addInterace_cisco_dslDownStreamSpeed"), map.get("addInterace_cisco_dslUpStreamSpeed"), map.get("addInterface_cisco_SIPbay"), map.get("existingAddressRangeIPv6selection"),
							map.get("newAddressRangeIpv6selection"), map.get("subnetSizeValue_IPv6"), map.get("availableBlocksValue_IPv6"),
							map.get("existingAddressIPv6DropdownValue"), map.get("newAddressrangeIPv6"));
				    APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice" , "Interface successfully created.");
				    interfaceCreation = APT_IPVPNHelper.get().successmessageForInterfaceOrMultilinkCreation("ipvpnservice", "successfully created");
				    
				   
				    APT_IPVPNHelper.get().clickOnBreadCrump("ipvpnservice", map.get("ExistingPEdevice"));
				    APT_IPVPNHelper.get().clickOnAddMultilinkLink("ipvpnservice");
				    String multilinkName = APT_IPVPNHelper.get().addMultilink_Cisco("ipvpnservice", map.get("ServiceSubType"),  map.get("multilink_Link"), map.get("multilink_Encapsulation"),
				    		map.get("multilink_cisco_bandwidth"), map.get("multilink_Ivmanagement"), map.get("addMultilink_existingAddressRangeIPv4selection"), map.get("addMultilink_newAddressRangeIpv4selection"), 
				    		map.get("addMultilink_subnetSizeValue_IPv4"), map.get("addMultilik_availableBlocksValue_IPv4"), map.get("addMultilink_existingAddressIPv4DropdownValue"), map.get("addMultilink_newinterfaceAddressrangeIPv4"), 
				    		map.get("addMultilink_existingAddressRangeIPv6selection"), map.get("addMultilink_newAddressRangeIpv6selection"), map.get("addMultilink_subnetSizeValue_IPv6"), 
				    		map.get("addMultilink_availableBlocksValue_IPv6"), map.get("addMultilink_existingAddressIPv6DropdownValue"), map.get("addMultilink_newinterfaceAddressrangeIPv6"), map.get("addMultilink_Cisco_InterfaceTextField"));
				    APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Interface successfully created.");
				    multilinkCreation = APT_IPVPNHelper.get().successmessageForInterfaceOrMultilinkCreation("ipvpnservice", "successfully created");
				}
			}
			
			
		//Select interface
			APT_IPVPNHelper.get().clickOnBreadCrump("ipvpnservice", map.get("VPN Site Order Num"));
			if(interfaceCreation.equalsIgnoreCase("Yes")) {
				APT_IPVPNHelper.get().PEdevice_clickOnselectInterface("ipvpnservice", map.get("ExistingPEdevice"));
				APT_IPVPNHelper.get().SelectInterfacetoremovefromservice("ipvpnservice", interfaceName);
				APT_IPVPNHelper.get().SelectInterfacetoaddwithservcie("ipvpnservice", interfaceName);
			}
			
			
			//Autodiscover VPn
			APT_IPVPNHelper.get().clickOnBreadCrump("ipvpnservice", map.get("VPN Site Order Num"));
			APT_IPVPNHelper.get().PEdevice_clickOnAutodiscoverVPN("ipvpnservice", map.get("ExistingPEdevice"));
			
		//Delete Interface
			if(interfaceCreation.equalsIgnoreCase("Yes")) {
				
				APT_IPVPNHelper.get().PEInterface_clickOnDeleteLink("ipvpnservice", interfaceName);
			}
			
			//Delete Provier Equipment
			APT_IPVPNHelper.get().deletePEdevice("ipvpnservice", map.get("ExistingPEdevice"));
			APT_IPVPNHelper.get().verifysuccessmessage("ipvpnservice", "Device successfully removed from service.");
			
			
			logger= ExtentTestManager.startTest("CPE to CPE Links-CPEL3"); 
			APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
			//	APT_IPVPNHelper.get().CPEtoCPElink("ipvpnservice", map.get("sourceDevice"), map.get("sourceInterface"), map.get("targetDevice"), map.get("targetInterface"));
			ExtentTestManager.endTest();
			
			logger= ExtentTestManager.startTest("Actelis-CPEL3");	
			APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
			APT_IPVPNHelper.get().verifyAddDSLAMandHSLlink("ipvpnservice", map.get("ActelisTech_DSLAMdevice"),map.get("ServiceSubType"),map.get("VPN Site Order Num"));
			APT_IPVPNHelper.get().AddDSLAMandHSL("ipvpnservice", map.get("ActelisTech_DSLAMdevice"), map.get("ActelisTech_DSLAMInterfacename"),map.get("ServiceSubType"));
			APT_IPVPNHelper.get().showInterface_ActelisConfiguuration("ipvpnservice",map.get("ServiceSubType"));
			APT_IPVPNHelper.get().deletInterface_ActelisConfiguration("ipvpnservice", map.get("ActelisTech_DSLAMInterfacename"),map.get("ServiceSubType"));
			APT_IPVPNHelper.get().successMessage_deleteInterfaceFromDevice_ActelisConfiguration("ipvpnservice",map.get("ServiceSubType"));
			ExtentTestManager.endTest();
	}
			
    logger= ExtentTestManager.startTest("DeleteVPNSiterder-CPEL3");
	APT_IPVPNHelper.get().searchorder("ipvpnservice", map.get("ServiceIdentification"));
	APT_IPVPNHelper.get().DeleteVPNSiteOrder("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Site Order Num"));
	APT_IPVPNHelper.get().DeleteVPNSiteOrder4("ipvpnservice", map.get("ServiceSubType"),map.get("VPN Site Order Num"));
	
	ExtentTestManager.endTest();

	} 
	
		
	
	
	
	 

			
 
	 
	 
		
}
