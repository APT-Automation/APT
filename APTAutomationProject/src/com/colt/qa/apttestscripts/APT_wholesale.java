package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.LogStatus;

public class APT_wholesale extends DriverTestcase{

	public String TrunkName=null;
	public String CustomerName=null;    //Customer name made public so that it can be used throughout the application
	
	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "Finaldatareader_wholeSale", priority=1)
	public void wholeSale(Map<String, String> map) throws Exception {
		
		String serviceIdentification=null;
		
		setup();	
		
		Login.APT_Login_1(map.get("url for the Product"));
		
		String newCustomerName=map.get("newCustomerSelection");
		String existingCustomer=map.get("existingCustomerSelection");
		
			if(newCustomerName.equalsIgnoreCase("yes") && existingCustomer.equalsIgnoreCase("no")) {
				
				logger= ExtentTestManager.startTest("CreateNewCustomer_wholesaleSIPTrunking");
				APT_Helper.get().CreateCustomer("apt", map.get("newCustomer"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), 
						map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), 
						map.get("Fax"));
				CustomerName=map.get("newCustomer");
				
				
				APT_Helper.get().selectCustomertocreateOrder("apt",map.get("newCustomer"));
				ExtentTestManager.endTest();
			}
			else if(newCustomerName.equalsIgnoreCase("no") && existingCustomer.equalsIgnoreCase("Yes")) {
				
				logger= ExtentTestManager.startTest("selectExistingCustomer_wholesaleSIPTrunking"); 
				APT_Helper.get().selectCustomertocreateOrder("apt",map.get("existingCustomer"));
				CustomerName=map.get("existingCustomer");
				ExtentTestManager.endTest();
			}
			 
		
		logger= ExtentTestManager.startTest("verifyCreateOrderFunctionality_wholesaleSIPtrunking");
			APT_Helper.get().createorderservice("apt", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"),
					map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
			ExtentTestManager.endTest();
			
		logger= ExtentTestManager.startTest("verifyServiceCreation_wholesaleSIPTrunking");
			APT_Helper.get().serviceSelection("wholesaleService", map.get("serviceToBeSelected"));
			APT_Helper.get().serviceCreation("wholesaleService", map.get("ServiceIdentification"), map.get("ImproperEmailID"), map.get("properMailId"),
					map.get("Phone"), map.get("remark"), map.get("PerformanceReporting"), map.get("serviceToBeSelected"));
			APT_Helper.get().verifysuccessmessage("wholesaleService", "Service successfully created.");
			ExtentTestManager.endTest(); 	
			
//		logger= ExtentTestManager.startTest("verifyUserDetailsInformation");
//			APT_Helper.get().VerifyUsersPanel("apt", map.get("UserName"), map.get("FirstName"), map.get("SurName"), map.get("PostalAddress"),
//					map.get("UserEmail"), map.get("Phone"), map.get("EditUserName"), map.get("EditFirstName"), map.get("EditSurName"), map.get("EditPostalAddress"), 
//					map.get("EditEmail"), map.get("EditPhone"),map.get("IPGuardianAccountGroup"),map.get("ColtOnlineUser"),map.get("GeneratePassword"),map.get("RolesToBeSelected")
//					,map.get("HideRouterToolsIPv6CommandsCisco_ToBeSelected"),map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeSelected"), 
//					map.get("HideRouterToolsIPv4CommandsCisco_ToBeSelected"), map.get("HideServicesToBeSelected"),map.get("HideSiteOrderToBeSelected"), map.get("editRolesToBeSelected"),
//					map.get("edit_RoleToBeHidden"), map.get("RouterToolsIPv6CommandsCisco_ToBeAvailable"), map.get("RouterToolsIPv6CommandsCisco_ToBeHidden"),
//					map.get("RouterToolsIPv4CommandsHuiwai_ToBeAvailable"), map.get("HideRouterToolsIPv4CommandsHuiwai_ToBeHidden"), map.get("HideRouterToolsIPv4CommandsCisco_ToBeAvailable"), 
//					map.get("HideRouterToolsIPv4CommandsCisco_ToBeHidden"), map.get("Services_ToBeAvailable"), map.get("Services_ToBeHidden"), map.get("SiteOrders_ToBeAvailable"),
//					map.get("SiteOrders_ToBeHidden"), map.get("editIPGuardianAccountGroup"), map.get("editColtOnlineUser"));
		
		
		logger= ExtentTestManager.startTest("verifyEditOrder_wholesaleSIpTrunking");
			APT_Helper.get().verifyorderpanel_editorder("apt", map.get("EditOrder_OrderNumber"), map.get("EditOrder_VoicelineNumber"), map.get("editOrderSelection"));
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("verifyChangeOrder_wholesaleSIPTruning");	
			APT_Helper.get().verifyorderpanel_changeorder("apt", map.get("ChangeOrder_newOrderNumber"), map.get("ChangeOrder_VoicelineNumber"), map.get("changeOrderSelection_newOrder"),
					map.get("changeOrderSelection_existingOrder"), map.get("ChangeOrder_existingOrderNumber"));
			ExtentTestManager.endTest(); 
			
		
		logger= ExtentTestManager.startTest("verifyValuesUsedForCreatingService_wholesaleSIPTrunking");
			APT_Helper.get().verifyEnteredValuesForServiceCreation("wholesaleService", map.get("ServiceIdentification"), map.get("ImproperEmailID"), map.get("properMailId"),
					map.get("Phone"), map.get("remark"), map.get("PerformanceReporting"), map.get("serviceToBeSelected"));		
			ExtentTestManager.endTest(); 
	
		logger= ExtentTestManager.startTest("editService_wholesaleSIP");
			APT_Helper.get().navigateToEditPage("wholesaleService");
			APT_Helper.get().editService("wholesaleService", map.get("edit_serviceId"), map.get("edit_remark"), map.get("edit_email"),
					map.get("edit_phone"), map.get("edit_performanceReport"), map.get("serviceToBeSelected"));
			APT_Helper.get().verifysuccessmessage("wholesaleService", "Service successfully updated");
			APT_Helper.get().verifyEnteredValuesForServiceUpdation("wholesaleService", map.get("ServiceIdentification"), map.get("ImproperEmailID"), map.get("properMailId"),
					map.get("Phone"), map.get("remark"), map.get("PerformanceReporting"), map.get("edit_serviceId"), map.get("edit_remark"), map.get("edit_email"),
					map.get("edit_phone"), map.get("edit_performanceReport"), map.get("serviceToBeSelected"));
			
			if(map.get("edit_serviceId").equalsIgnoreCase("null")) {
				serviceIdentification = map.get("ServiceIdentification");
			}else {
				serviceIdentification = map.get("edit_serviceId");
			}
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("ManageService_wholesaleSIP");
			String orderNumber = APT_Helper.get().fetchOrderNumber("apt");
			APT_Helper.get().verifyManageService("manageService" , serviceIdentification , 	map.get("serviceToBeSelected"), 
					map.get("ServiceStatus"), map.get("syncstatus"), map.get("ServiceStatusChangeRequired") , orderNumber, map.get("changeStatus"));
			APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("verifyManageSubnet_wholesaleSIP");
			APT_Helper.get().manageSubnet_viewServicepage("wholesaleService");
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("verifyDump_wholesaleSIP");
			APT_Helper.get().dump_viewServicepage("wholesaleService");
			ExtentTestManager.endTest(); 
	
			
		/**
		 * MAS switch	
		 */
			
		logger= ExtentTestManager.startTest("addMASswitch_wholesaleSIP");
			APT_Helper.get().verifyAddMASswitch("wholesaleService", map.get("MAS_IMSPOPLocation"));
			APT_Helper.get().verifysuccessmessage("wholesaleService", "MAS switch added successfully");
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("viewMASswitch_wholesaleSIP");
			APT_Helper.get().MASswitch_clickOnViewPage("wholesaleService", map.get("MAS_deviceName"));
			APT_Helper.get().verifyAddedMASswitchInformation_View("wholesaleService");
			APT_Helper.get().testStatus("wholesaleService");
			ExtentTestManager.endTest(); 
		
		logger= ExtentTestManager.startTest("editMASswitch_wholesaleSIP");
			APT_Helper.get().editMASdevice("wholesaleService", map.get("MAS_editName"), map.get("MAS_editVendor"), map.get("MAS_manageAddress"),
					map.get("MAS_Snmpro"), map.get("MAS_editCountry"), 
					map.get("MAS_ExistingCitySelection"), map.get("MAS_newCitySelection"), map.get("MAS_editExistingCity"), map.get("MAS_editNewCityname"), map.get("MAS_editNewCityCode"),
					map.get("MAS_ExistingSiteSelection"), map.get("MAS_newSiteSelection"), map.get("MAS_editExistingSite"), map.get("MAS_editNewSitename"), map.get("MAS_editNewSiteCode"),
					map.get("MAS_ExistingPremiseSelection"), map.get("MAS_newPremiseSelection"), map.get("MAS_editExistingPremise"), map.get("MAS_editNewPremiseName"), map.get("MAS_editNewPremiseCode"));
			APT_Helper.get().verifysuccessmessage("wholesaleService", "MAS switch updated successfully");
			ExtentTestManager.endTest(); 

		logger= ExtentTestManager.startTest("verifyAddInterfaceFunction_MAS_wholesaleSIP");
			APT_Helper.get().verifyAddInterfaceFunction_MAS("wholesaleService",map.get("MAS_AccessMedia"),map.get("MAS_Network"),map.get("MAS_HSRPBGP"),
					map.get("MAS_Interface"), map.get("MAS_InterfaceAddressRange"), map.get("MAS_InterfaceAddressMask"),
					map.get("MAS_HSRPIP"), map.get("MAS_InterfaceAddressRangeIPV6"), map.get("MAS_HSRPIPv6Address"), map.get("MAS_PrimaryIPv6onMas1"),
					map.get("MAS_SecondaryIPv6onMas2"), map.get("MAS_GroupNumber"), map.get("MAS_Link"), map.get("MAS_VLANID"),
					map.get("MAS_IVManagement"), map.get("MAS_GenerateConfiguration"), map.get("MAS_HSRPTrackInterface"),map.get("MAS_HSRPAuthentication"));
			APT_Helper.get().verifysuccessmessage("wholesaleService", "Interface added successfully");
			ExtentTestManager.endTest(); 
		
		logger= ExtentTestManager.startTest("verifyAddedInterface_MAS_wholesaleSIP");
			APT_Helper.get().verifyinterfaceTableColumnNames("wholesaleService", map.get("MAS_Interface"));
			APT_Helper.get().verifyInterfaceValues_MAS("wholesaleService",map.get("MAS_AccessMedia"),map.get("MAS_Network"),map.get("MAS_HSRPBGP"),
					map.get("MAS_Interface"), map.get("MAS_InterfaceAddressRange"), map.get("MAS_InterfaceAddressMask"),
					map.get("MAS_HSRPIP"), map.get("MAS_InterfaceAddressRangeIPV6"), map.get("MAS_HSRPIPv6Address"), map.get("MAS_PrimaryIPv6onMas1"),
					map.get("MAS_SecondaryIPv6onMas2"), map.get("MAS_GroupNumber"), map.get("MAS_Link"), map.get("MAS_VLANID"),
					map.get("MAS_IVManagement"), map.get("MAS_GenerateConfiguration"), map.get("MAS_HSRPTrackInterface"),map.get("MAS_HSRPAuthentication"));
			ExtentTestManager.endTest(); 
		
		logger= ExtentTestManager.startTest("MAS_routerToolsPanel_wholesaleSIP");
			APT_Helper.get().routerPanel("wholesaleService", map.get("MAS_command_ipv4"), map.get("MAS_command_ipv6"),
				map.get("MAS_vrf_Ipv4"), map.get("MAS_vrf_Ipv6"));
			ExtentTestManager.endTest(); 
	
		logger= ExtentTestManager.startTest("configureInterface_MASSwicth_wholesaleSIP");
			APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
			
			String deviceName_masswitch=null;
			
			if(map.get("MAS_editName").equalsIgnoreCase("null")) {
				deviceName_masswitch=map.get("MAS_deviceName");
			}else {
				deviceName_masswitch=map.get("MAS_editName");
			}
			
			String deviceSerialNumberunderMASPanel=APT_Helper.get().MASswitch_getDeviceSerialNumber("wholesaleService", deviceName_masswitch);
			
			APT_Helper.get().selectInterfaceAndClickonConfigureLInk_MASswitch("wholesaleService", deviceSerialNumberunderMASPanel, map.get("MAS_Interface"));
			APT_Helper.get().viewInterface_MASswitch("wholesaleService", deviceName_masswitch , map.get("MAS_Interface"), map.get("MAS_Link"),
					map.get("MAS_InterfaceAddressRange"), map.get("MAS_VLANID"));
			ExtentTestManager.endTest(); 
	
		logger= ExtentTestManager.startTest("editInterface_MASSwitch_wholesaleSIP");
			APT_Helper.get().clickOnEditInterfaceLink("wholesaleService");
			APT_Helper.get().editInterface("wholesaleService", map.get("MAS_editAccessMedia"), map.get("MAS_editNetwork") , map.get("MAS_editHSRPBGP"), 
					map.get("MAS_editInterface"), map.get("MAS_editInterfaceAddressRange"), map.get("MAS_editInterfaceAddressMask"), map.get("MAS_editHSRPIP"), 
					map.get("MAS_editInterfaceAddressRangeIPV6"), map.get("MAS_editHSRPIPv6Address"), map.get("MAS_editPrimaryIPv6onMas1"), map.get("MAS_editSecondaryIPv6onMas2"),
					map.get("MAS_editGroupNumber"), map.get("MAS_editLink"), map.get("MAS_editVLANID"), map.get("MAS_editIVManagement"), 
					map.get("MAS_editGenerateConfiguration"), map.get("MAS_editHSRPTrackInterface"), map.get("MAS_editHSRPAuthentication"));
			ExtentTestManager.endTest(); 
	
		logger= ExtentTestManager.startTest("selectInterface_MASSwitch_wholesaleSIP");
			String interfaceName=null;
			if(map.get("MAS_editInterface").equalsIgnoreCase("null")) {
				interfaceName=map.get("MAS_Interface");
			}else {
				interfaceName=map.get("MAS_editInterface");
			}
	
			
			APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
			APT_Helper.get().MSAdevice_clickOnselectInterface("wholesaleService", map.get("MAS_deviceName"));
			
			if(map.get("MAS_RemoveInterface_Selection").equalsIgnoreCase("yes")) {
				APT_Helper.get().SelectInterfacetoremovefromservice("wholesaleService", interfaceName);
			}else {
				Log.info("interfaces are not removed");
			}
			
			if(map.get("MAS_AddInterface_Selection").equalsIgnoreCase("yes")) {
				APT_Helper.get().SelectInterfacetoaddwithservcie("wholesaleService", interfaceName);
			}
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("deleteInterface_MASSwitch_wholesaleSIP");
			APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
			APT_Helper.get().selectInterface_AndDelete_MASswitch("wholesaleService", map.get("MAS_deviceName"), interfaceName);
			ExtentTestManager.endTest(); 
	
		logger= ExtentTestManager.startTest("deleteMASdevice_wholesaleSIP");
			APT_Helper.get().MASswitch__DeleteFromServiceFunctionality("wholesaleService", map.get("MAS_deviceName"));
			APT_Helper.get().MASswitch__DeleteFromServiceFunctionality("wholesaleService", map.get("MAS-devicename2"));
			ExtentTestManager.endTest(); 
			
			
			/**
			 * Provider Equipment (PE)
			 */
		logger= ExtentTestManager.startTest("addPEdevice_wholesaleSIP");
			APT_Helper.get().verifyPEdevice("wholesaleService", map.get("PE_IMSPOPLocation"));
			APT_Helper.get().verifysuccessmessage("wholesaleService", "PE Device added successfully");
			ExtentTestManager.endTest(); 
			
			String peDeviceAvailability = APT_Helper.get().verifyDevicesUnderPEpanel("wholesaleService");
			
			if(peDeviceAvailability.equalsIgnoreCase("Yes")) {
				
				logger= ExtentTestManager.startTest("viewPEdevice_wholesaleSIP");
				APT_Helper.get().PEdevice_clickOnViewPage("wholesaleService", map.get("PE_deviceName"));
				APT_Helper.get().verifyAddedMASswitchInformation_View("wholesaleService");
				APT_Helper.get().testStatus("wholesaleService");
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest("editPEdevice_wholesaleSIP");
				APT_Helper.get().editPEdevice("wholesaleService", map.get("PE_editName"), map.get("PE_editVendor"), map.get("PE_manageAddress"),
						map.get("PE_Snmpro"), map.get("PE_editCountry"), 
						map.get("PE_ExistingCitySelection"), map.get("PE_newCitySelection"), map.get("PE_editExistingCity"), map.get("PE_editNewCityname"), map.get("PE_editNewCityCode"),
						map.get("PE_ExistingSiteSelection"), map.get("PE_newSiteSelection"), map.get("PE_editExistingSite"), map.get("PE_editNewSitename"), map.get("PE_editNewSiteCode"),
						map.get("PE_ExistingPremiseSelection"), map.get("PE_newPremiseSelection"), map.get("PE_editExistingPremise"), map.get("PE_editNewPremiseName"), map.get("PE_editNewPremiseCode"));
				APT_Helper.get().verifysuccessmessage("wholesaleService", "PE Device updated successfully");
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest("verifyAddInterfaceFunction_PE_wholesaleSIP");
					APT_Helper.get().verifyAddInterfaceFunction_PE("wholesaleService",map.get("PE_AccessMedia"),map.get("PE_Network"),map.get("PE_VRRPBGP"),
							map.get("PE_Interface"), map.get("PE_InterfaceAddressRange"), map.get("PE_InterfaceAddressMask"),
							map.get("PE_VRRPIP"), map.get("PE_InterfaceAddressRangeIPV6"), map.get("PE_VRRPIPv6Address"), map.get("PE_PrimaryIPv6onMAS1"),
							map.get("PE_SecondaryIPv6onMAS2"), map.get("PE_GroupNumber"), map.get("PE_Link"), map.get("PE_VLANID"),
							map.get("PE_IVManagement"), map.get("PE_GenerateConfiguration"), map.get("PE_VRRPTrackInterface"),map.get("PE_VRRPAuthentication"),
							map.get("PE_VRRP_GroupName"), map.get("PE_VRF"), map.get("PE_InterfaceDefaultValue"));
					APT_Helper.get().verifysuccessmessage("wholesaleService", "Interface added successfully");
					ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest("verifyAddedInterface_PE_wholesaleSIP");
				String interfaceDefaultValue=map.get("PE_InterfaceDefaultValue");
				String vlanIdValue=map.get("PE_VLANID");
				String interfacename1=interfaceDefaultValue+vlanIdValue;
				APT_Helper.get().verifyinterfaceTableColumnNames("wholesaleService", interfacename1);
				APT_Helper.get().verifyInterfaceValues_PE("wholesaleService",map.get("PE_AccessMedia"),map.get("PE_Network"),map.get("PE_VRRPBGP"),
						interfacename1, map.get("PE_InterfaceAddressRange"), map.get("PE_InterfaceAddressMask"),
						map.get("PE_VRRPIP"), map.get("PE_InterfaceAddressRangeIPV6"), map.get("PE_VRRPIPv6Address"), map.get("PE_PrimaryIPv6onMAS1"),
						map.get("PE_SecondaryIPv6onMAS2"), map.get("PE_GroupNumber"), map.get("PE_Link"), map.get("PE_VLANID"),
						map.get("PE_IVManagement"), map.get("PE_GenerateConfiguration"), map.get("PE_VRRPTrackInterface"),map.get("PE_VRRPAuthentication"),
						map.get("PE_VRRP_GroupName"), map.get("PE_VRF"), map.get("PE_InterfaceDefaultValue"));
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest("PE_routerToolsPanel_wholesaleSIP");
					APT_Helper.get().routerPanel("wholesaleService", map.get("PE_command_ipv4"), map.get("PE_command_ipv6"),
						map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
					ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest("configureInterface_PE_wholesaleSIP");
				APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
				
					String deviceName_PEdevice=null;
					
					if(map.get("PE_editName").equalsIgnoreCase("null")) {
						deviceName_PEdevice=map.get("PE_deviceName");
					}else {
						deviceName_PEdevice=map.get("PE_editName");
					}
				
				String deviceSerialNumberunderPEdevicePanel=APT_Helper.get().PEdevice_getDeviceSerialNumber("wholesaleService", deviceName_PEdevice);
				APT_Helper.get().selectInterfaceAndClickonConfigureLInk_PEdevice("wholesaleService", deviceSerialNumberunderPEdevicePanel, interfacename1);
				APT_Helper.get().viewInterface_PEdevice("wholesaleService", deviceName_PEdevice, interfacename1 , map.get("PE_Link"),
						map.get("PE_InterfaceAddressRange"), map.get("PE_VLANID"));
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest("editInterface_wholesaleSIP");
				String interfaceDefaultValue1=map.get("PE_InterfaceDefaultValue");
				String vlanIdValue1=map.get("PE_VLANID");
				String interfacename2=interfaceDefaultValue1+vlanIdValue1;
				APT_Helper.get().clickOnEditInterfaceLink("wholesaleService");
				APT_Helper.get().editInterface_PE("wholesaleService",map.get("PE_editAccessMedia"),map.get("PE_editNetwork"),map.get("PE_editVRRPBGP"),
						map.get("PE_editInterfaceAddressRange"), map.get("PE_editInterfaceAddressMask"),
						map.get("PE_editVRRPIP"), map.get("PE_editInterfaceAddressRangeIPV6"), map.get("PE_editVRRPIPv6Address"), map.get("PE_editPrimaryIPv6onMAS1"),
						map.get("PE_editSecondaryIPv6onMAS2"), map.get("PE_editGroupNumber"), map.get("PE_editLink"), map.get("PE_editVLANID"),
						map.get("PE_editIVManagement"), map.get("PE_GenerateConfiguration"), map.get("PE_editVRRPTrackInterface"),map.get("PE_editVRRPAuthentication"),
						map.get("PE_editVRRP_GroupName"), map.get("PE_editVRF"),interfacename2 );
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest("selectInterface_PE_wholesaleIP");
				APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
				APT_Helper.get().PEdevice_clickOnselectInterface("wholesaleService", map.get("PE_deviceName"));
				if(map.get("PE_RemoveInterface_Selection").equalsIgnoreCase("yes")) {
					APT_Helper.get().SelectInterfacetoremovefromservice("wholesaleService", interfacename2);
				}else {
					Log.info("interfaces are not removed");
				}
				
				if(map.get("PE_AddInterface_Selection").equalsIgnoreCase("yes")) {
					APT_Helper.get().SelectInterfacetoaddwithservcie("wholesaleService", interfacename2);
				}
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest("deletInterface_wholesaleSIP");
				APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
				APT_Helper.get().selectInterface_AndDelete_PEdevice("wholesaleService", map.get("PE_deviceName"), interfacename2);
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest("delete_PEdevice_wholesaleSIP");
				APT_Helper.get().PEdevice__DeleteFromServiceFunctionality("wholesaleService", map.get("PE_deviceName"));
//				APT_Helper.get().PEdevice__DeleteFromServiceFunctionality("wholesaleService", map.get("PE_deviceName2"));
				ExtentTestManager.endTest(); 
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No devices displaying under 'Provider Equipment' Panel");
				Log.info("No devices displaying under 'Provider Equipment' Panel");
			}
		
			
	/**
	 *  Trunk		
	 */
			
		logger= ExtentTestManager.startTest("addTrunkSiteOrder_wholesaleSIP");
			String siteOrderNumber=null;
			APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
			APT_Helper.get().addTrunkSiteOrder("wholesaleService", map.get("TrunkGroupOrder"), map.get("TrunkGroupOrderNumber"));
			APT_Helper.get().editSiteOrder("wholesaleService", map.get("TrunkGroupOrderNumber"), map.get("edit_TrunkGroupOrder"), map.get("edit_TrunkGroupOrderNumber"));
			
			if(map.get("edit_TrunkGroupOrderNumber").equalsIgnoreCase("null")) {
				
				siteOrderNumber= map.get("TrunkGroupOrderNumber");
						
			}else{
				siteOrderNumber=map.get("edit_TrunkGroupOrderNumber");
			}
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("addTrunk_wholesaleSIP");
			APT_Helper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("wholesaleService", siteOrderNumber );
			APT_Helper.get().addTrunk("wholesaleService", CustomerName, serviceIdentification ,map.get("trunkType"), map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"),
					map.get("gateway"), map.get("quality"), map.get("trafficDirection"), map.get("ipAddresstype"), map.get("SIPsignallingPort"),
					map.get("internetBasedCustomer"), map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),
					map.get("coltSignalingIP"), map.get("mediaIP"), map.get("reuseNIFgroup"), map.get("reuseSigZonePart"), map.get("callAdmissionControl"),
					map.get("callrateLimitselection"), map.get("sourceAddressFiltering"), map.get("relSupport"), map.get("sipSessionkeepAliveTimer"), map.get("retryInvite"),
					map.get("routePriority"), map.get("addressReachability"), map.get("carrierIPoriginating"), map.get("carrierIPterminating"), map.get("TLSfield"),
					map.get("srtp"), map.get("prefix"), map.get("globalProfile_ExistingSelection"), map.get("globalProfile_newSelection"),map.get("globalProfile_ExistingValue"),map.get("globalProfile_newValue"), 
					map.get("localProfile_existingFieldSelection"), map.get("localProfile_newFieldSelection"), map.get("localProfile_existingvalue"), map.get("localProfile_newvalue"),
					map.get("COSprofile_existingFieldSelection"), map.get("COSprofile_newFieldSelection"),map.get("COSprofile_existingValue"), map.get("COSprofile_newValue"),
					map.get("PSPGname_existingFieldSelection"), map.get("PSPGname_newFieldSelection"),map.get("pspgName_existingValue"), map.get("pspgName_newValue"),
					map.get("prefferedPSP_existingFieldSelection"), map.get("prefferedPSP_newFieldSelection"),map.get("preferredPSP_exitingvalue"), map.get("preferredPSP_newvalue"),
					map.get("carrier_existingFieldSelection"), map.get("carrier_newFieldSelection"), map.get("carrier_existingValue"), map.get("carrier_newValue"),
					map.get("IPsignallingProfile_existingFieldSelection"), map.get("IPsignallingProfile_newFieldSelection"), map.get("IPsignallingProfile_existingValue"), map.get("IPsignallingProfile_newValue"),
					map.get("EgressIpsignal_existingFieldSelection"), map.get("EgressIpsignal_newFieldSelection"),map.get("EgressIPsignal_existingValue"), map.get("EgressIPsignal_newValue"),
					map.get("InDMPMrule_existingFieldSelection"), map.get("InDMPMrule_newFieldSelection"), map.get("InDMPMrule_existingValue"), map.get("InDMPMrule_newValue"),
					map.get("OutDMPMrule_existingFieldSelection"),map.get("OutDMPMrule_newFieldSelection"), map.get("OutDMPMrule_existingValue"), map.get("OutDMPMrule_newValue"),
					map.get("featureControlprofile_existingFieldSelection"), map.get("featureControlprofile_newFieldSelection"), map.get("featureControlprofile_existingValue"), map.get("featureControlprofile_newValue"),
					map.get("localRingBackTone_existingFieldSelection"), map.get("localRingBackTone_newFieldSelection"), map.get("localRingBackTone_existingValue"), map.get("localRingBackTone_newValue"),
					map.get("createLowerCaseRoutervalue"), map.get("PSXmanualConfigvalue"), map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), map.get("callrateLimiteValue"), map.get("SBCmanualconfigValue"));
			APT_Helper.get().verifysuccessmessage("wholesaleService", "Trunk created successfully");
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("viewTrunk_wholesaleSIP");
			APT_Helper.get().viewTrunk_Primary("wholesaleService", CustomerName, serviceIdentification ,map.get("trunkType"), map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"),
					map.get("gateway"), map.get("quality"), map.get("trafficDirection"), map.get("ipAddresstype"), map.get("SIPsignallingPort"),
					map.get("internetBasedCustomer"), map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),
					map.get("coltSignalingIP"), map.get("mediaIP"), map.get("reuseNIFgroup"), map.get("reuseSigZonePart"), map.get("callAdmissionControl"),
					map.get("callrateLimitselection"), map.get("sourceAddressFiltering"), map.get("relSupport"), map.get("sipSessionkeepAliveTimer"), map.get("retryInvite"),
					map.get("routePriority"), map.get("addressReachability"), map.get("carrierIPoriginating"), map.get("carrierIPterminating"), map.get("TLSfield"),
					map.get("srtp"), map.get("prefix"), map.get("globalProfile_ExistingSelection"), map.get("globalProfile_newSelection"),map.get("globalProfile_ExistingValue"),map.get("globalProfile_newValue"), 
					map.get("localProfile_existingFieldSelection"), map.get("localProfile_newFieldSelection"), map.get("localProfile_existingvalue"), map.get("localProfile_newvalue"),
					map.get("COSprofile_existingFieldSelection"), map.get("COSprofile_newFieldSelection"),map.get("COSprofile_existingValue"), map.get("COSprofile_newValue"),
					map.get("PSPGname_existingFieldSelection"), map.get("PSPGname_newFieldSelection"),map.get("pspgName_existingValue"), map.get("pspgName_newValue"),
					map.get("prefferedPSP_existingFieldSelection"), map.get("prefferedPSP_newFieldSelection"),map.get("preferredPSP_exitingvalue"), map.get("preferredPSP_newvalue"),
					map.get("carrier_existingFieldSelection"), map.get("carrier_newFieldSelection"), map.get("carrier_existingValue"), map.get("carrier_newValue"),
					map.get("IPsignallingProfile_existingFieldSelection"), map.get("IPsignallingProfile_newFieldSelection"), map.get("IPsignallingProfile_existingValue"), map.get("IPsignallingProfile_newValue"),
					map.get("EgressIpsignal_existingFieldSelection"), map.get("EgressIpsignal_newFieldSelection"),map.get("EgressIPsignal_existingValue"), map.get("EgressIPsignal_newValue"),
					map.get("InDMPMrule_existingFieldSelection"), map.get("InDMPMrule_newFieldSelection"), map.get("InDMPMrule_existingValue"), map.get("InDMPMrule_newValue"),
					map.get("OutDMPMrule_existingFieldSelection"),map.get("OutDMPMrule_newFieldSelection"), map.get("OutDMPMrule_existingValue"), map.get("OutDMPMrule_newValue"),
					map.get("featureControlprofile_existingFieldSelection"), map.get("featureControlprofile_newFieldSelection"), map.get("featureControlprofile_existingValue"), map.get("featureControlprofile_newValue"),
					map.get("localRingBackTone_existingFieldSelection"), map.get("localRingBackTone_newFieldSelection"), map.get("localRingBackTone_existingValue"), map.get("localRingBackTone_newValue"),
					map.get("createLowerCaseRoutervalue"), map.get("PSXmanualConfigvalue"), map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), map.get("callrateLimiteValue"),  map.get("SBCmanualconfigValue"));
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("History_wholesaleSIP");
			String trunkGroupName = APT_Helper.get().fetchTrunkName("wholesaleService");
			
			APT_Helper.get().history("wholesaleService", "insert_trunk", trunkGroupName, map.get("gateway"), map.get("voipProtocol"), 
					map.get("signallingtransportProtocol"));
			ExtentTestManager.endTest(); 
			
		logger= ExtentTestManager.startTest("editTrunk_wholesaleSIP");
			APT_Helper.get().editTrunk("wholesaleService",CustomerName, serviceIdentification ,  map.get("trunkType"), map.get("edit_TrunkType"), map.get("edit_VOIPprotocol"), map.get("edit_BillingCountry"), map.get("edit_CDRdelivery"),
					map.get("editPrefix"), map.get("editGateway"), map.get("editQuality"), map.get("editTrafficDirection"), map.get("edit_IpAddressType"),
					map.get("editCarrierIPoriginating"), map.get("editCarrierIPterminating"), map.get("editSIPsignallingPort"), map.get("editSignallingTransport"),
					map.get("edit_TLSproflile"), map.get("edit_SRTP"), map.get("edit_signallingZone"), map.get("edit_coltSignalIP"), map.get("edit_mediaIP"), map.get("edit_reuseNIFgroup"),
					map.get("edit_reuseSigZonePart"), map.get("edit_callAdmissionControl"), map.get("edit_callLimit"), map.get("edit_limitNumber"), map.get("edit_callrateLimit"),
					map.get("edit_callrateLimitvalue"), map.get("edit_sourceAddressFiltering"), map.get("edit_relSupport"), map.get("edit_sipSessionkeepAliveTimer"),
					map.get("edit_internetBasedCustomer"), map.get("edit_vlantag"), map.get("edit_subInterfaceSlot"), map.get("edit_retryInvite"), map.get("edit_addressReachability"),
					map.get("edit_routePriority"),
					map.get("editglobalProfile_ExistingSelection"), map.get("editglobalProfile_newSelection"), map.get("editGlobalProfile_ExistingValue"),map.get("editGlobalProfile_newValue"), 
					map.get("editLocalProfile_existingFieldSelection"), map.get("editLocalProfile_newFieldSelection"), map.get("editLocalProfile_existingvalue"), map.get("editLocalProfile_newvalue"),
					map.get("editCOSprofile_existingFieldSelection"), map.get("editCOSprofile_newFieldSelection"), map.get("editCOSprofile_existingValue"), map.get("editCOSprofile_newValue"),
					map.get("editPSPGname_existingFieldSelection"), map.get("editPSPGname_newFieldSelection"), map.get("editpspgName_existingValue"), map.get("editpspgName_newValue"),
					map.get("editPrefferedPSP_existingFieldSelection"), map.get("editPrefferedPSP_newFieldSelection"), map.get("editPreferredPSP_exitingvalue"), map.get("editPreferredPSP_newvalue"),
					map.get("editCarrier_existingFieldSelection"), map.get("editCarrier_newFieldSelection"), map.get("editCarrier_existingValue"), map.get("editCarrier_newValue"),
					map.get("editIPsignalProfile_existingFieldSelection"), map.get("editIPsignalProfile_newFieldSelection"), map.get("editIPsignalProfile_existingValue"), map.get("editIPsignalProfile_newValue"),
					map.get("editEgressIpsignal_existingFieldSelection"), map.get("editEgressIpsignal_newFieldSelection"), map.get("editEgressIPsignal_existingValue"), map.get("editEgressIPsignal_newValue"),
					map.get("editInDMPMrule_existingFieldSelection"), map.get("editInDMPMrule_newFieldSelection"), map.get("editInDMPMrule_existingValue"), map.get("editInDMPMrule_newValue"),
					map.get("editOutDMPMrule_existingFieldSelection"), map.get("editOutDMPMrule_newFieldSelection"), map.get("editOutDMPMrule_existingValue"), map.get("editOutDMPMrule_newValue"),
					map.get("editFeatureControlprofile_existingFieldSelection"), map.get("editFeatureControlprofile_newFieldSelection"), map.get("editFeatureControlprofile_existingValue"), map.get("editFeatureControlprofile_newValue"),
					map.get("editLocalRingBackTone_existingFieldSelection"), map.get("editLocalRingBackTone_newFieldSelection"), map.get("editLocalRingBackTone_existingValue"), map.get("editLocalRingBackTone_newValue"),
					map.get("editCreateLowerCaseRoutervalue"), map.get("edit_PSXmanualConfigvalue"), map.get("edit_GSXmanualConfigvalue"),map.get("edit_SBCmanualconfigValue"), map.get("BillingCountry"));
			ExtentTestManager.endTest(); 

			
			logger= ExtentTestManager.startTest("viewTrunk_GSX_PSX_configuration_wholesaleSIP");
			String trunkName=APT_Helper.get().fetchTrunkName("wholesaleService");
			TrunkName=trunkName;
			
				String Gateway = APT_Helper.get().fetchgatewayValue("wholesaleService");
			
				APT_Helper.get().viewTrunk_PSX_executeConfiguration("wholesaleService", map.get("viewtrunk_PSXconfiguration"), TrunkName,
						map.get("carierIPOrient_resiltrunk"), map.get("carierIPterminat_resiltrunk"));
				
				if(map.get("editGateway").equalsIgnoreCase("null")) {
					Gateway=map.get("gateway");
				}
				else {
					Gateway=map.get("editGateway");
				}
				
				if(Gateway.contains("SBC")) {
					APT_Helper.get().viewTrunk_SBC_executeConfiguration("wholesaleService", map.get("viewtrunk_SBCconfiguration"));
				}
				else
				{
					APT_Helper.get().viewTrunk_GSX_executeConfiguration("wholesaleService", map.get("viewtrunk_GSXconfiguration"), map.get("gsxConfigurationValue"));
				}
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest("SBCmanualConfig_wholesaleSIP");
				if(Gateway.contains("SBC")) {
					APT_Helper.get().addSBC_manualExecutionConfig("wholesaleService", map.get("SBCmanualConfigvalue"));
					APT_Helper.get().verifySBCfileAdded("wholesaleService");
					APT_Helper.get().editSBC_manualExecutionConfig("wholesaleService", map.get("editSBCmanualConfigvalue"));
					APT_Helper.get().deleteSBC_manualExecutionConfig("wholesaleService");
					ExtentTestManager.endTest();
				}else {
					ExtentTestManager.getTest().log(LogStatus.INFO, "'SBC Manual Execution Configuration' panel will not display, if 'SBC' gateway not selected ");
					ExtentTestManager.endTest();
				}
				
				
			logger= ExtentTestManager.startTest("PSXmanualConfig_wholesaleSIP");
				APT_Helper.get().addPSX_manualExecutionConfig("wholesaleService", map.get("PSXmanualConfigValue"));
				APT_Helper.get().verifyPSXfileAdded("wholesaleService");
				APT_Helper.get().editPSX_manualExecutionConfig("wholesaleService", map.get("editPSXmanualConfigValue"));
				APT_Helper.get().deletePSX_manualExecutionConfig("wholesaleService");
				ExtentTestManager.endTest();
				
			logger= ExtentTestManager.startTest("GSXmanualConfig_wholesaleSIP");
				if(Gateway.contains("SBC")) {
					ExtentTestManager.getTest().log(LogStatus.INFO, "'GSX Manual Execution Configuration' panel will not display, if 'SBC' gateway is selected ");
					ExtentTestManager.endTest();
				}
				else {
					APT_Helper.get().addGSX_manualExecutionConfig("wholesaleService", map.get("GSXmanualConfigValue"));
					APT_Helper.get().verifyGSXfileAdded("wholesaleService");
					APT_Helper.get().editGSX_manualExecutionConfig("wholesaleService", map.get("editGSXmanualConfigValue"));
					APT_Helper.get().deleteGSX_manualExecutionConfig("wholesaleService");
					ExtentTestManager.endTest();
				}
			
				
			/**
			 * CPE device	
			 */
			logger= ExtentTestManager.startTest("addCPEdevice_wholesaleSIP");
			String resilTrunkName=APT_Helper.get().fetchTrunkName("wholesaleService");
				APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
				APT_Helper.get().clickOnCPEdeviceLink("wholesaleService", trunkName, siteOrderNumber);
				APT_Helper.get().addCPEdevice("wholesaleService", map.get("CPEdevice_routerID"), map.get("CPEdevice_vendorModel"),
						map.get("CPEdevice_managementAddress"), map.get("CPEdevice_Snmpro"), map.get("CPEdevice_Snmprw"), map.get("CPEdevice_SNMPv3Contextname"),
						map.get("CPEdevice_SNMPv3ContextEngineId") , map.get("CPEdevice_SNMPv3securityUsername"), map.get("CPEdevice_SNMPv3AutoProto"), map.get("CPEdevice_SNMPv3AuthPasswrd"),
						map.get("Country"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
						map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
						map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
				APT_Helper.get().verifysuccessmessage("wholesaleService", "CPE Device added successfully");
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest("viewCPEdevice_wholesaleSIP");
				APT_Helper.get().CPEdevice_clickOnViewLink("wholesaleService", map.get("CPEdevice_routerID"));
				APT_Helper.get().viewCPEdevice("wholesaleService", map.get("CPEdevice_routerID"), map.get("CPEdevice_vendorModel"),
						map.get("CPEdevice_managementAddress"), map.get("CPEdevice_Snmpro"), map.get("CPEdevice_Snmprw"), map.get("CPEdevice_SNMPv3Contextname"),
						map.get("CPEdevice_SNMPv3ContextEngineId") , map.get("CPEdevice_SNMPv3securityUsername"), map.get("CPEdevice_SNMPv3AutoProto"), map.get("CPEdevice_SNMPv3AuthPasswrd"),
						map.get("Country"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
						map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
						map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest("editCPEdevice_wholesaleSIP");
				APT_Helper.get().editCPEdeviceLink("wholesaleService");
				APT_Helper.get().editCPEdevice("wholesaleService", map.get("editCPEdevice_routerID"), map.get("editCPEdevice_vendorModel"), map.get("editCPEdevice_managementAddress"),
						map.get("editCPEdevice_Snmpro"), map.get("editCPEdevice_Snmprw"), map.get("editCPEdevice_SNMPv3Contextname"), map.get("editCPEdevice_SNMPv3ContextEngineId"),
						map.get("editCPEdevice_SNMPv3securityUsername"), map.get("editCPEdevice_SNMPv3AuthProto"), map.get("editCPEdevice_SNMPv3AuthPasswrd"),
						map.get("editCountry"), map.get("editExistingCity"),
						map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
						map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
						map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
				APT_Helper.get().verifysuccessmessage("wholesaleService", "CPE Device updated successfully");
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest("routerPanel_CPEdevice_wholesaleSIP");
				APT_Helper.get().routerPanel("wholesaleService", map.get("CPE_command_ipv4"), map.get("CPE_command_ipv6"), map.get("CPE_vrf_Ipv4"), map.get("CPE_vrf_Ipv6"));
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest("deleteCPEdevice_wholesaleSIP");
				String routerId=null;
				if(map.get("editCPEdevice_routerID").equalsIgnoreCase("null")) {
					routerId=map.get("CPEdevice_routerID");
				}
				else {
					routerId=map.get("editCPEdevice_routerID");
				}
				
				APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
				APT_Helper.get().CPEdevice_clickOnDeleteLink("wholesaleService", routerId );
				APT_Helper.get().verifysuccessmessage("wholesaleService", "CPE Device deleted successfully");
				ExtentTestManager.endTest(); 	
			
				logger= ExtentTestManager.startTest("deleteTrunkSiteOrder_wholesaleSIP");
				APT_Helper.get().clickOnBreadCrump("wholesaleService", serviceIdentification);
				
				if(map.get("viewtrunk_PSXconfiguration").equals("Add Destination IP Address")) {
					APT_Helper.get().deleteTrunk("wholesaleService",  resilTrunkName, siteOrderNumber);
				}
//				APT_Helper.get().deleteTrunk("wholesaleService", trunkGroupName, siteOrderNumber);
				
				APT_Helper.get().deleteSiteOrder("wholesaleService", siteOrderNumber);
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest("deleteService_wholesaleSIP");
				APT_Helper.get().deleteService("wholesaleService");
				ExtentTestManager.endTest(); 
			}
		
		
}
