package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.reporter.ExtentTestManager;

public class IPVPN_newTab extends DriverTestcase {

	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "DataReader_IPVPNNewTab", priority = 0)
	public void Verify_wholesale_newTabFunctoinality(Map<String, String> map) throws Exception {
		
		setup();	
		
		Login.APT_Login_1(map.get("url"));
		
		String interfaceName = "Null";
		String interfaceCreation = "Null";
		String interfaceNameUpdated = "Null";

		logger= ExtentTestManager.startTest("Search Existing Service");
		ipvpnnewTab.get().searchOrderORservice("newTab",  map.get("siteOrderNumber"), map.get("serviceName"));
		
		
		logger= ExtentTestManager.startTest("View device page");
		ipvpnnewTab.get().PEdevice_clickOnViewPEdevice("IPVPN", map.get("customerName"), map.get("serviceName"), map.get("siteOrderNumber"), map.get("ExistingPEdevice"));
		
		
		logger= ExtentTestManager.startTest("Add Interface page");
		ipvpnnewTab.get().clickOnAddInterfaceLink("IPVPN", map.get("customerName"), map.get("serviceName"), map.get("siteOrderNumber"), map.get("ExistingPEdevice"));
		
		
		interfaceName  = ipvpnnewTab.get().addInterface_Juniper("IPVPN", map.get("serviceSubType"), map.get("existingAddressIPv4Selection"), map.get("existingAddressIPv4DropdownValue"), map.get("newAddressIpv4Selection"), map.get("newAddressIpv4Range"), 
				map.get("subnetSizeValue_IPv4"), map.get("availableBlocksValue_IPv4"), map.get("addInterface_link"), map.get("addInterface_Juniper_bearerType"), map.get("addInterface_juniper_encapsulation"),
				map.get("addInterface_slot"), map.get("addInterface_port"), map.get("addInterface_vlanId"), map.get("addInterface_unitId"), map.get("addInterface_pic"),
				map.get("addInterace_STM1number"), map.get("addInterface_bandwidth"), map.get("addInterface_cardType"),
				map.get("addInterface_frameType"), map.get("addInterface_clockSource"), map.get("addInterface_timeSlot"), map.get("addInterface_configureInterfaceOnDevice"),
				map.get("addInterface_Ivmanagement"),
				map.get("existingAddressRangeIPv6selection"), map.get("existingAddressIPv6DropdownValue"), map.get("newAddressRangeIpv6selection"),
				map.get("newAddressrangeIPv6"), map.get("subnetSizeValue_IPv6"), map.get("availableBlocksValue_IPv6"), map.get("addInterface_bearerNumber"));
		ipvpnnewTab.get().verifysuccessmessage("IPVPN", "Interface successfully created.");
		interfaceCreation = ipvpnnewTab.get().successmessageForInterfaceOrMultilinkCreation("IPVPN", "successfully created");
		
		
		
		//Select interface
		ipvpnnewTab.get().clickOnBreadCrump("IPVPN", map.get("siteOrderNumber"));
		if(interfaceCreation.equalsIgnoreCase("Yes")) {
			ipvpnnewTab.get().PEdevice_clickOnselectInterface("IPVPN", map.get("customerName"), map.get("serviceName"), map.get("siteOrderNumber"), map.get("ExistingPEdevice"));
			ipvpnnewTab.get().SelectInterfacetoremovefromservice("IPVPN", interfaceName);
			ipvpnnewTab.get().SelectInterfacetoaddwithservcie("IPVPN", interfaceName);
		}
		
	}
}
