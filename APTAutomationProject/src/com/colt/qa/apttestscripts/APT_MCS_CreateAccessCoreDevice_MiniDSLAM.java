package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DataReader_PK;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.relevantcodes.extentreports.LogStatus;


public class APT_MCS_CreateAccessCoreDevice_MiniDSLAM extends DriverTestcase {
	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_MiniDSLAM_Device", priority=0)
	public void createAccessCoreDevice_MiniDSLAM(Map<String, String> map) throws Exception {
		
		String devicename=map.get("Name");
		
		setup();	
		
		Login.APT_Login_1(map.get("url"));	
		
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifynavigationtoCreateAccessCoreDevicepage_MiniDSLAM");
			APT_CreateMiniDSLAMDeviceHelper.get().navigatetomanagecoltnetwork("CreateAccessCoreDevice");
			APT_CreateMiniDSLAMDeviceHelper.get().navigatetocreateaccesscoredevicepage("CreateAccessCoreDevice");

		
		DriverTestcase.logger = DriverTestcase.extent.startTest("createMiniDSLAMdevice_"+devicename);
			APT_CreateMiniDSLAMDeviceHelper.get().verifydevicecreation_AccessRouter("CreateAccessCoreDevice", map.get("Name"), map.get("DeviceType"), map.get("VendorModel"),map.get("Modular MSP"), 
			map.get("Full IQNET"), map.get("IOSXR"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
			map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
			map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
			map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"), map.get("RouterID"),
			map.get("Country"), map.get("Management Address"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
			map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
			map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
	
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeviceCreationMessagefor_miniDSLAMDevice_"+devicename);
			APT_CreateMiniDSLAMDeviceHelper.get().verifyDeviceCreationMessage("CreateAccessCoreDevice");
		
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEnteredValueForDevice_"+devicename);
			APT_CreateMiniDSLAMDeviceHelper.get().verifyenteredValue_forDeviceCreation("CreateAccessCoreDevice", map.get("Name"), map.get("DeviceType"), map.get("VendorModel"),map.get("Modular MSP"), 
					map.get("Full IQNET"), map.get("IOSXR"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
					map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
					map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
					map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"), map.get("RouterID"),
					map.get("Country"), map.get("Management Address"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
					map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
					map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("editMiniDSLAMdevice_"+devicename);
			APT_CreateMiniDSLAMDeviceHelper.get().verifydeviceEdit_AccessRouter("CreateAccessCoreDevice", map.get("editdeviceName"), map.get("DeviceType"),
					map.get("editVendorModel"), map.get("editRouterID"), map.get("editModularMSP"), map.get("editFullIQNET"), map.get("editIOSXR"), map.get("editTelnet"),
					map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"), map.get("editSnmpv3UsernameNewValue"),
					map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
					map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
					map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
					map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
	
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeviceCreationMessageforMiniDSLAMDevice_"+devicename);
			APT_CreateMiniDSLAMDeviceHelper.get().verifyDeviceUpdationSuccessMessage("CreateAccessCoreDevice");
	
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEnteredValueForDeviceUpdated_"+devicename);
			APT_CreateMiniDSLAMDeviceHelper.get().verifEditedValue_Firewall("CreateAccessCoreDevice", map.get("editdeviceName"), map.get("DeviceType"),
					map.get("editVendorModel"), map.get("editRouterID"), map.get("editModularMSP"), map.get("editFullIQNET"), map.get("editIOSXR"), map.get("editTelnet"),
					map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"), map.get("editSnmpv3UsernameNewValue"),
					map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
					map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
					map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
					map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
	
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyTestCommandAndStatus_"+devicename);
			APT_CreateMiniDSLAMDeviceHelper.get().testStatus("CreateAccessCoreDevice");
	
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("routerPanel_"+devicename);
			String vendorModel=null;
			 String editedVendor=map.get("editVendorModel");
			 String addedVendor=map.get("VendorModel");
			if(editedVendor.equalsIgnoreCase("null")) {
				vendorModel=addedVendor;
			}else {
				vendorModel=editedVendor;
			}
			
			String managementAddressEdit=map.get("editManagementAddress");
			String managementAddressCreated=map.get("Management Address");
			
			if(vendorModel.startsWith("Cisco")) {
				if(managementAddressEdit.equalsIgnoreCase("null")) {
					
					APT_CreateMiniDSLAMDeviceHelper.get().routerPanel("CreateAccessCoreDevice", map.get("command_ipv4"), map.get("command_ipv6"), managementAddressCreated ,
							map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
				}
				else {
					APT_CreateMiniDSLAMDeviceHelper.get().routerPanel("CreateAccessCoreDevice", map.get("command_ipv4"), map.get("command_ipv6"), managementAddressEdit,
							map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
					
				}
			  }
			else {
				DriverTestcase.logger.log(LogStatus.INFO, "Router Panel will not display for the selected vendorModel: "+vendorModel);
				System.out.println("Router Panel will not display for the selected vendorModel: "+vendorModel);
			}
		
			
		DriverTestcase.logger = DriverTestcase.extent.startTest("fetchDeviceInterface_"+devicename);
			String DeviceName= null;
			String VendorModel=null;
			String managementAddress=null;
			String country=null;
		
			//Device name	
			if(map.get("editdeviceName").equalsIgnoreCase("null")) {
				DeviceName=map.get("Name");
			}else {
				DeviceName = map.get("editdeviceName");
			}
			
		
			//Vendor/Model
			if(map.get("editVendorModel").equalsIgnoreCase("null")) {
				VendorModel=map.get("VendorModel");
			}else {
				VendorModel=map.get("editVendorModel");
			}
			
			//Management Address
			if(map.get("editManagementAddress").equalsIgnoreCase("null")) {
				managementAddress=map.get("Management Address");
			}else {
				managementAddress=map.get("editManagementAddress");
			}
		
			//Country
			if(map.get("editCountry").equalsIgnoreCase("null")) {
				country=map.get("Country");
			}else {
				country=map.get("editCountry");
			}
			
			//Snmpro
			String Snmpro=null;
			String snmproCreated=map.get("SnmProNewValue");
			String snmproEdited=map.get("editSnmProNewValue");
			String snmproDefaultValue="JdhquA5";
			
			if(snmproEdited.equalsIgnoreCase("null")) {
				if(snmproCreated.equalsIgnoreCase("null")) {
					Snmpro=snmproDefaultValue;
				}else {
					Snmpro=snmproCreated;
				}
			}
			else {
				Snmpro=snmproEdited;
			}
		
			APT_CreateMiniDSLAMDeviceHelper.get().fetchDeviceInterface_viewdevicepage("CreateAccessCoreDevice");
			APT_ManageNetworkHelpr.get().verifyFetchInterface("ManageNetwork", DeviceName, map.get("InServiceStatus"), map.get("InMaintenanceStatus"), 
					VendorModel, managementAddress, map.get("Snmpro"), country, map.get("InterfaceName"));

			
		DriverTestcase.logger = DriverTestcase.extent.startTest("deleteMiniDSLAMdevice_"+devicename);
			APT_CreateMiniDSLAMDeviceHelper.get().verifydeviceDelete_AccessRouter("CreateAccessCoreDevice");
	
	}

}



