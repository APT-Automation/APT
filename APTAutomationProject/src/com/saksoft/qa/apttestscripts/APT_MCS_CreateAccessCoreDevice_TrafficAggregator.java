package com.saksoft.qa.apttestscripts;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;


public class APT_MCS_CreateAccessCoreDevice_TrafficAggregator extends DriverTestcase {
	

	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=0)
	public void verifynavigationtocreatedevicepage(Map<String, String> map) throws InterruptedException, DocumentException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifynavigationtocreatedevicepage");
		
		APT_CreateTrafficAggregatorDeviceHelper.get().navigatetomanagecoltnetwork("CreateAccessCoreDevice");
		APT_CreateTrafficAggregatorDeviceHelper.get().navigatetocreateaccesscoredevicepage("CreateAccessCoreDevice");
	}
	
	
//	@Test(description = "TC-02",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=1)
	public void verifycreatedevicefields(Map<String, String> map) throws InterruptedException, DocumentException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifycreatedevicefields");
		
		APT_CreateTrafficAggregatorDeviceHelper.get().verifycreatedevicefields("CreateAccessCoreDevice");
		
	}
	
	@Test(description = "TC-03",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=2)
	public void createTrafficAggregatordevice(Map<String, String> map) throws InterruptedException, IOException, DocumentException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("createTrafficAggregatordevice");
		
		APT_CreateTrafficAggregatorDeviceHelper.get().verifydevicecreation_AccessRouter("CreateAccessCoreDevice", map.get("Name"), map.get("DeviceType"), map.get("VendorModel"),map.get("Modular MSP"), 
		map.get("Full IQNET"), map.get("IOSXR"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
		map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
		map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
		map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"), map.get("RouterID"),
		map.get("Country"), map.get("Management Address"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
		map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
		map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
	
	}

	@Test(description = "TC-03", priority=3)
	public void verifyDeviceCreationMessagefor_TrafficAggregatorDevice() throws IOException, InterruptedException, DocumentException{
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeviceCreationMessagefor_TrafficAggregatorDevice");
		
	APT_CreateTrafficAggregatorDeviceHelper.get().verifyDeviceCreationMessage("CreateAccessCoreDevice");
	
	}
	
	@Test(description = "TC-04",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=4)
	public void verifyEnteredValueForDevice(Map<String, String> map) throws InterruptedException, IOException, DocumentException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEnteredValueForDevice");
		
		
		APT_CreateTrafficAggregatorDeviceHelper.get().verifyenteredValue_forDeviceCreation("CreateAccessCoreDevice", map.get("Name"), map.get("DeviceType"), map.get("VendorModel"),map.get("Modular MSP"), 
				map.get("Full IQNET"), map.get("IOSXR"), map.get("Telnet"), map.get("SSH"), map.get("Snmp2C"), map.get("SnmPro"), map.get("Snmprw"), 
				map.get("SnmProNewValue"), map.get("SnmprwNewValue"), map.get("Snmp3"), map.get("Snmpv3Username"),
				map.get("Snmpv3Authpassword"), map.get("Snmpv3Privpassword"), map.get("Snmpv3UsernameNewValue"), 
				map.get("Snmpv3AuthpasswordNewValue"), map.get("Snmpv3PrivpasswordNewValue"), map.get("RouterID"),
				map.get("Country"), map.get("Management Address"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
				map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
				map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		
	}
	
	
	@Test(description = "TC-05",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=5)
	public void editTrafficAggregatorDevice(Map<String, String> map) throws InterruptedException, IOException, DocumentException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("editTrafficAggregatorDevice");
		
		APT_CreateTrafficAggregatorDeviceHelper.get().verifydeviceEdit_AccessRouter("CreateAccessCoreDevice", map.get("editdeviceName"), map.get("DeviceType"),
				map.get("editVendorModel"), map.get("editRouterID"), map.get("editModularMSP"), map.get("editFullIQNET"), map.get("editIOSXR"), map.get("editTelnet"),
				map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"), map.get("editSnmpv3UsernameNewValue"),
				map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
				map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
				map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
				map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));

	
	}
	
	@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=6)
	public void verifyDeviceUpdationSuccessMessagefor_TrafficAggregatorDevice(Map<String, String> map) throws IOException, InterruptedException, DocumentException{
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDeviceUpdationSuccessMessagefor_TrafficAggregatorDevice");
		
	APT_CreateTrafficAggregatorDeviceHelper.get().verifyDeviceUpdationSuccessMessage("verifyDeviceUpdationSuccessMessagefor_FirewallDevice");
	
	}
	
	
	@Test(description = "TC-07",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=7)
	public void verifyUpdatedValueForDevice(Map<String, String> map) throws InterruptedException, IOException, DocumentException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyEnteredValueForDevice");
		
		APT_CreateTrafficAggregatorDeviceHelper.get().verifEditedValue_Firewall("CreateAccessCoreDevice", map.get("editdeviceName"), map.get("DeviceType"),
				map.get("editVendorModel"), map.get("editRouterID"), map.get("editModularMSP"), map.get("editFullIQNET"), map.get("editIOSXR"), map.get("editTelnet"),
				map.get("editSSH"), map.get("editSnmp2C"), map.get("editSnmp3"), map.get("editSnmProNewValue"), map.get("editSnmprwNewValue"), map.get("editSnmpv3UsernameNewValue"),
				map.get("editSnmpv3AuthpasswordNewValue"), map.get("editSnmpv3PrivpasswordNewValue"),map.get("editManagementAddress"), map.get("editCountry"), map.get("editExistingCity"),
				map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
				map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
				map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
		
	}
	
	@Test(description = "TC-08",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=8)
	public void verifyTestCommandAndStatus(Map<String, String> map) throws InterruptedException, IOException, DocumentException {
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyTestCommandAndStatus");
		
		APT_CreateTrafficAggregatorDeviceHelper.get().testStatus("CreateAccessCoreDevice");
	
	}
	
	@Test(description = "TC-09",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=9)
	public void routerPanel(Map<String, String> map) throws InterruptedException, IOException, DocumentException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("routerPanel");
		
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
			
			APT_CreateTrafficAggregatorDeviceHelper.get().routerPanel("CreateAccessCoreDevice", map.get("command_ipv4"), map.get("command_ipv6"), managementAddressCreated ,
					map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
		}
		else {
			APT_CreateTrafficAggregatorDeviceHelper.get().routerPanel("CreateAccessCoreDevice", map.get("command_ipv4"), map.get("command_ipv6"), managementAddressEdit,
					map.get("vrf_Ipv4"), map.get("vrf_Ipv6"));
			
		}
	  }
	else {
		DriverTestcase.logger.log(LogStatus.INFO, "Router Panel will not display for the selected vendorModel: "+vendorModel);
		System.out.println("Router Panel will not display for the selected vendorModel: "+vendorModel);
	}
	}


	
	@Test(description = "TC-10",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=10)
	public void fetchDeviceInterface(Map<String, String> map) throws InterruptedException, IOException, DocumentException, ParseException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("fetchDeviceInterface");
		
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
		
		APT_CreateTrafficAggregatorDeviceHelper.get().fetchDeviceInterface_viewdevicepage("CreateAccessCoreDevice");
		
		APT_ManageNetworkHelpr.get().verifyFetchInterface("ManageNetwork", DeviceName, map.get("InServiceStatus"), map.get("InMaintenanceStatus"), 
				VendorModel, managementAddress, map.get("Snmpro"), country, map.get("InterfaceName"));
	}
	
	@Test(description = "TC-11",dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_TrafficAggregator_Device", priority=11)
	public void verifyTrafficAggregatordeviceDeleteFunctionality(Map<String, String> map) throws InterruptedException, IOException, DocumentException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyTrafficAggregatordeviceDeleteFunctionality");
		
		
		APT_CreateTrafficAggregatorDeviceHelper.get().verifydeviceDelete_AccessRouter("CreateAccessCoreDevice");
	
	}
	
	
}



