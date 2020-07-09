package com.saksoft.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.excellibrary.APT_DataReader_SS;
import com.saksoft.qa.scripthelpers.APT_LoginHelper;

public class APT_wholesale extends DriverTestcase{
	
	
//	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=0)
	public void CreateCustomer(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("CreateCustomer"); 
		APT_wholesaleHelper.get().CreateCustomer("apt", map.get("Name"), map.get("MainDomain"), map.get("CountryToBeSelected"), map.get("OCN"), map.get("Reference"),  map.get("TechnicalContactName"), map.get("TypeToBeSelected"), map.get("Email"), map.get("Phone"), map.get("Fax"));		
	} 
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=0)
	public void searchCreatedService(Map<String, String> map) throws Exception {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("searchCreatedService"); 
		
		APT_wholesaleHelper.get().searchorder("apt", map.get("ServiceIdentification"));
	}
	
	@Test(description = "TC-02",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=1)
    public void choosecustomer(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("choosecustomer"); 
		APT_wholesaleHelper.get().selectCustomertocreateOrder("apt",map.get("ChooseCustomerToBeSelected"),map.get("Name1"),map.get("Name2"));
		
	}


	@Test(description = "TC-03",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=2)
	 public void verifycreateorder(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifycreateorder");
		APT_wholesaleHelper.get().createorderservice("apt", map.get("NewOrderService"), map.get("NewOrderNumber"), map.get("NewRFIREQNumber"), map.get("ExistingOrderService"), map.get("ExistingOrderNumber"));
	}
	
	
	
	@Test(description = "TC-04",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=3)
	 public void verifyServiceCreation(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyServiceCreation");
		
		APT_wholesaleHelper.get().serviceSelection("wholesaleService", map.get("serviceToBeSelected"));
		
		APT_wholesaleHelper.get().serviceCreation("wholesaleService", map.get("ServiceIdentification"), map.get("ImproperEmailID"), map.get("properMailId"),
				map.get("Phone"), map.get("remark"), map.get("PerformanceReporting"), map.get("serviceToBeSelected"));
		
		APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "Service successfully created.");
		
	}
	
	
	@Test(description = "TC-05", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=4)
	public void verifyValuesUsedForCreatingService(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyValuesUsedForCreatingService");
		Thread.sleep(2000);
		
		APT_wholesaleHelper.get().verifyEnteredValuesForServiceCreation("wholesaleService", map.get("ServiceIdentification"), map.get("ImproperEmailID"), map.get("properMailId"),
				map.get("Phone"), map.get("remark"), map.get("PerformanceReporting"), map.get("serviceToBeSelected"));		
		
	}
	
	
	
	@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=5)
	 public void editSevice(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("editSevice");
		
		APT_wholesaleHelper.get().navigateToEditPage("wholesaleService");
		
		APT_wholesaleHelper.get().editService("wholesaleService", map.get("edit_serviceId"), map.get("edit_remark"), map.get("edit_email"),
				map.get("edit_phone"), map.get("edit_performanceReport"), map.get("serviceToBeSelected"));
		
		APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "Service successfully updated");
		
		APT_wholesaleHelper.get().verifyEnteredValuesForServiceUpdation("wholesaleService", map.get("ServiceIdentification"), map.get("ImproperEmailID"), map.get("properMailId"),
				map.get("Phone"), map.get("remark"), map.get("PerformanceReporting"), map.get("edit_serviceId"), map.get("edit_remark"), map.get("edit_email"),
				map.get("edit_phone"), map.get("edit_performanceReport"), map.get("serviceToBeSelected"));
		
	}
	
	
	@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=6)
	 public void verifyManageSubnet_IPv6(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyManageSubnet_IPv6");
	
		APT_wholesaleHelper.get().manageSubnet_viewServicepage("wholesaleService");
	}

	
	@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=7)
	public void verifyDump(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDump");
		Thread.sleep(4000);
		
		APT_wholesaleHelper.get().dump_viewServicepage("wholesaleService");
		
	}
	
	
	@Test(description = "TC-06",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=8)
	 public void addMASswitch(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("addMASswitch");
		
		APT_wholesaleHelper.get().verifyAddMASswitch("wholesaleService", map.get("MAS_IMSPOPLocation"));
		
		APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "MAS switch added successfully");
	}
	

	@Test(description = "TC-07",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=9)
	 public void viewMASswitch(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("viewMASswitch");
		
		APT_wholesaleHelper.get().MASswitch_clickOnViewPage("wholesaleService", map.get("MAS_deviceName"));
		
		APT_wholesaleHelper.get().verifyAddedMASswitchInformation_View("wholesaleService");
		
		APT_wholesaleHelper.get().testStatus("wholesaleService");
	
	}
	
	
	@Test(description = "TC-08",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=10)
	 public void editMASswitch(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("editMASswitch");
		
		APT_wholesaleHelper.get().editMASdevice("wholesaleService", map.get("MAS_editName"), map.get("MAS_editVendor"), map.get("MAS_manageAddress"),
				map.get("MAS_Snmpro"), map.get("MAS_editCountry"), 
				map.get("MAS_ExistingCitySelection"), map.get("MAS_newCitySelection"), map.get("MAS_editExistingCity"), map.get("MAS_editNewCityname"), map.get("MAS_editNewCityCode"),
				map.get("MAS_ExistingSiteSelection"), map.get("MAS_newSiteSelection"), map.get("MAS_editExistingSite"), map.get("MAS_editNewSitename"), map.get("MAS_editNewSiteCode"),
				map.get("MAS_ExistingPremiseSelection"), map.get("MAS_newPremiseSelection"), map.get("MAS_editExistingPremise"), map.get("MAS_editNewPremiseName"), map.get("MAS_editNewPremiseCode"));
	
		APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "MAS switch updated successfully");
	
	}
	
	@Test(description = "TC-10",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=11)
		public void verifyAddInterfaceFunction_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {

			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddInterfaceFunction_MAS");
			
			APT_wholesaleHelper.get().verifyAddInterfaceFunction_MAS("wholesaleService",map.get("MAS_AccessMedia"),map.get("MAS_Network"),map.get("MAS_HSRPBGP"),
					map.get("MAS_Interface"), map.get("MAS_InterfaceAddressRange"), map.get("MAS_InterfaceAddressMask"),
					map.get("MAS_HSRPIP"), map.get("MAS_InterfaceAddressRangeIPV6"), map.get("MAS_HSRPIPv6Address"), map.get("MAS_PrimaryIPv6onMas1"),
					map.get("MAS_SecondaryIPv6onMas2"), map.get("MAS_GroupNumber"), map.get("MAS_Link"), map.get("MAS_VLANID"),
					map.get("MAS_IVManagement"), map.get("MAS_GenerateConfiguration"), map.get("MAS_HSRPTrackInterface"),map.get("MAS_HSRPAuthentication"));
	
	}
	
	@Test(description = "TC-11",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=12)
	public void verifyAddedInterface_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddedInterface_MAS");
		
		APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "Interface added successfully");
		
		APT_wholesaleHelper.get().verifyinterfaceTableColumnNames("wholesaleService", map.get("MAS_Interface"));
		
		APT_wholesaleHelper.get().verifyInterfaceValues_MAS("wholesaleService",map.get("MAS_AccessMedia"),map.get("MAS_Network"),map.get("MAS_HSRPBGP"),
				map.get("MAS_Interface"), map.get("MAS_InterfaceAddressRange"), map.get("MAS_InterfaceAddressMask"),
				map.get("MAS_HSRPIP"), map.get("MAS_InterfaceAddressRangeIPV6"), map.get("MAS_HSRPIPv6Address"), map.get("MAS_PrimaryIPv6onMas1"),
				map.get("MAS_SecondaryIPv6onMas2"), map.get("MAS_GroupNumber"), map.get("MAS_Link"), map.get("MAS_VLANID"),
				map.get("MAS_IVManagement"), map.get("MAS_GenerateConfiguration"), map.get("MAS_HSRPTrackInterface"),map.get("MAS_HSRPAuthentication"));
		
	}
	@Test(description = "TC-09",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=13)
	 public void MAS_routerToolsPanel(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("MAS_routerToolsPanel");
		
			APT_wholesaleHelper.get().routerPanel("wholesaleService", map.get("MAS_command_ipv4"), map.get("MAS_command_ipv6"),
				map.get("MAS_vrf_Ipv4"), map.get("MAS_vrf_Ipv6"));
		
	}
	

	@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=14)
	public void configureInterface(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("configureInterface");
		
		APT_wholesaleHelper.get().clickOnBreadCrump("wholesaleService", map.get("ServiceIdentification"));
		
		APT_wholesaleHelper.get().selectInterfaceAndClickonConfigureLInk_MASswitch("wholesaleService", map.get("MAS_deviceName"), map.get("MAS_Interface"));
		
		APT_wholesaleHelper.get().viewInterface_MASswitch("wholesaleService", map.get("MAS_deviceName"), map.get("MAS_Interface"), map.get("MAS_Link"),
				map.get("MAS_InterfaceAddressRange"), map.get("MAS_VLANID"));
		
	}
	
	

	@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=15)
	public void editInterface(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("editInterface");
	
		APT_wholesaleHelper.get().clickOnEditInterfaceLink("wholesaleService");
		
		APT_wholesaleHelper.get().editInterface("wholesaleService", map.get("MAS_editAccessMedia"), map.get("MAS_editNetwork") , map.get("MAS_editHSRPBGP"), 
				map.get("MAS_editInterface"), map.get("MAS_editInterfaceAddressRange"), map.get("MAS_editInterfaceAddressMask"), map.get("MAS_editHSRPIP"), 
				map.get("MAS_editInterfaceAddressRangeIPV6"), map.get("MAS_editHSRPIPv6Address"), map.get("MAS_editPrimaryIPv6onMas1"), map.get("MAS_editSecondaryIPv6onMas2"),
				map.get("MAS_editGroupNumber"), map.get("MAS_editLink"), map.get("MAS_editVLANID"), map.get("MAS_editIVManagement"), 
				map.get("MAS_editGenerateConfiguration"), map.get("MAS_editHSRPTrackInterface"), map.get("MAS_editHSRPAuthentication"));
		
	}	
		
	
	
	@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=16)
	public void selectInterface_MAS(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("selectInterface");
		
		String interfaceName=null;
		
		if(map.get("MAS_editInterface").equalsIgnoreCase("null")) {
			interfaceName=map.get("MAS_Interface");
		}else {
			interfaceName=map.get("MAS_editInterface");
		}

		
		APT_wholesaleHelper.get().clickOnBreadCrump("wholesaleService", map.get("ServiceIdentification"));
		
		APT_wholesaleHelper.get().MSAdevice_clickOnselectInterface("wholesaleService", map.get("MAS_deviceName"));
		
		if(map.get("MAS_RemoveInterface_Selection").equalsIgnoreCase("yes")) {
			APT_wholesaleHelper.get().SelectInterfacetoremovefromservice("wholesaleService", interfaceName);
		}else {
			System.out.println("interfaces are not removed");
		}
		
		if(map.get("MAS_AddInterface_Selection").equalsIgnoreCase("yes")) {
			APT_wholesaleHelper.get().SelectInterfacetoaddwithservcie("wholesaleService", interfaceName);
		}
	}
	

	@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=17)
	public void deletInterfce(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("deletInterfce");
		
		APT_wholesaleHelper.get().clickOnBreadCrump("wholesaleService", map.get("ServiceIdentification"));

		String interfaceName=null;
		
		if(map.get("MAS_editInterface").equalsIgnoreCase("null")) {
			interfaceName=map.get("MAS_Interface");
		}else {
			interfaceName=map.get("MAS_editInterface");
		}
		
		APT_wholesaleHelper.get().selectInterface_AndDelete_MASswitch("wholesaleService", map.get("MAS_deviceName"), interfaceName);
		
	}
	
	@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=18)
	public void delete_MASdevice(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("delete_MASdevice");
		
		APT_wholesaleHelper.get().MASswitch__DeleteFromServiceFunctionality("wholesaleService", map.get("MAS_deviceName"));
		
		APT_wholesaleHelper.get().MASswitch__DeleteFromServiceFunctionality("wholesaleService", map.get("MAS-devicename2"));
	}
	
		
	@Test(description = "TC-13",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=19)
	 public void addPEdevice(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("addPEdevice");
		
		APT_wholesaleHelper.get().verifyPEdevice("wholesaleService", map.get("PE_IMSPOPLocation"));
		
		APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "PE Device added successfully");
	}
	

	
	@Test(description = "TC-14",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=20)
	 public void viewPEdevice(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
	
		DriverTestcase.logger = DriverTestcase.extent.startTest("viewPEdevice");
		
		APT_wholesaleHelper.get().PEdevice_clickOnViewPage("wholesaleService", map.get("PE_deviceName"));
		
		APT_wholesaleHelper.get().verifyAddedMASswitchInformation_View("wholesaleService");
		
		APT_wholesaleHelper.get().testStatus("wholesaleService");
	
	}	
	
	@Test(description = "TC-15",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=21)
	 public void editPEdevice(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("editPEdevice");
		
		APT_wholesaleHelper.get().editPEdevice("wholesaleService", map.get("PE_editName"), map.get("PE_editVendor"), map.get("PE_manageAddress"),
				map.get("PE_Snmpro"), map.get("PE_editCountry"), 
				map.get("PE_ExistingCitySelection"), map.get("PE_newCitySelection"), map.get("PE_editExistingCity"), map.get("PE_editNewCityname"), map.get("PE_editNewCityCode"),
				map.get("PE_ExistingSiteSelection"), map.get("PE_newSiteSelection"), map.get("PE_editExistingSite"), map.get("PE_editNewSitename"), map.get("PE_editNewSiteCode"),
				map.get("PE_ExistingPremiseSelection"), map.get("PE_newPremiseSelection"), map.get("PE_editExistingPremise"), map.get("PE_editNewPremiseName"), map.get("PE_editNewPremiseCode"));
	
		APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "PE Device updated successfully");
	}
		
		@Test(description = "TC-10",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=22)
			public void verifyAddInterfaceFunction_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {

				DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddInterfaceFunction_PE");
				
				APT_wholesaleHelper.get().verifyAddInterfaceFunction_PE("wholesaleService",map.get("PE_AccessMedia"),map.get("PE_Network"),map.get("PE_VRRPBGP"),
						map.get("PE_Interface"), map.get("PE_InterfaceAddressRange"), map.get("PE_InterfaceAddressMask"),
						map.get("PE_VRRPIP"), map.get("PE_InterfaceAddressRangeIPV6"), map.get("PE_VRRPIPv6Address"), map.get("PE_PrimaryIPv6onMAS1"),
						map.get("PE_SecondaryIPv6onMAS2"), map.get("PE_GroupNumber"), map.get("PE_Link"), map.get("PE_VLANID"),
						map.get("PE_IVManagement"), map.get("PE_GenerateConfiguration"), map.get("PE_VRRPTrackInterface"),map.get("PE_VRRPAuthentication"),
						map.get("PE_VRRP_GroupName"), map.get("PE_VRF"), map.get("PE_InterfaceDefaultValue"));
		
		}
		
		@Test(description = "TC-11",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=23)
		public void verifyAddedInterface_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddedInterface_PE");
			
			String interfaceDefaultValue=map.get("PE_InterfaceDefaultValue");
			String vlanIdValue=map.get("PE_VLANID");
			
			String interfacename=interfaceDefaultValue+vlanIdValue;
			
			APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "Interface added successfully");
			
			APT_wholesaleHelper.get().verifyinterfaceTableColumnNames("wholesaleService", interfacename );
			
			APT_wholesaleHelper.get().verifyInterfaceValues_PE("wholesaleService",map.get("PE_AccessMedia"),map.get("PE_Network"),map.get("PE_VRRPBGP"),
					interfacename, map.get("PE_InterfaceAddressRange"), map.get("PE_InterfaceAddressMask"),
					map.get("PE_VRRPIP"), map.get("PE_InterfaceAddressRangeIPV6"), map.get("PE_VRRPIPv6Address"), map.get("PE_PrimaryIPv6onMAS1"),
					map.get("PE_SecondaryIPv6onMAS2"), map.get("PE_GroupNumber"), map.get("PE_Link"), map.get("PE_VLANID"),
					map.get("PE_IVManagement"), map.get("PE_GenerateConfiguration"), map.get("PE_VRRPTrackInterface"),map.get("PE_VRRPAuthentication"),
					map.get("PE_VRRP_GroupName"), map.get("PE_VRF"), map.get("PE_InterfaceDefaultValue"));
			
		}
		

		@Test(description = "TC-09",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=24)
		 public void PE_routerToolsPanel(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("PE_routerToolsPanel");
			
				APT_wholesaleHelper.get().routerPanel("wholesaleService", map.get("PE_command_ipv4"), map.get("PE_command_ipv6"),
					map.get("PE_vrf_Ipv4"), map.get("PE_vrf_Ipv6"));
			
		}
		
		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=25)
		public void selectInterface_PE(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("selectInterface_PE");
			
			APT_wholesaleHelper.get().clickOnBreadCrump("wholesaleService", map.get("ServiceIdentification"));
			
			String interfaceDefaultValue=map.get("PE_InterfaceDefaultValue");
			String vlanIdValue=map.get("PE_VLANID");
			
			String interfacename=interfaceDefaultValue+vlanIdValue;
					
			APT_wholesaleHelper.get().PEdevice_clickOnselectInterface("wholesaleService", map.get("PE_deviceName"));
			
			if(map.get("PE_RemoveInterface_Selection").equalsIgnoreCase("yes")) {
				APT_wholesaleHelper.get().SelectInterfacetoremovefromservice("wholesaleService", interfacename);
			}else {
				System.out.println("interfaces are not removed");
			}
			
			if(map.get("PE_AddInterface_Selection").equalsIgnoreCase("yes")) {
				APT_wholesaleHelper.get().SelectInterfacetoaddwithservcie("wholesaleService", interfacename);
			}
		}
		
		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=26)
		public void PE_deletInterfce(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("deletInterfce");
			
			APT_wholesaleHelper.get().clickOnBreadCrump("wholesaleService", map.get("ServiceIdentification"));
			
			String interfaceDefaultValue=map.get("PE_InterfaceDefaultValue");
			String vlanIdValue=map.get("PE_VLANID");
			
			String interfacename=interfaceDefaultValue+vlanIdValue;
			
			APT_wholesaleHelper.get().selectInterface_AndDelete_PEdevice("wholesaleService", map.get("PE_deviceName"), interfacename);
			
		}
		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=27)
		public void delete_PEdevice(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("delete_PEdevice");
			
			APT_wholesaleHelper.get().PEdevice__DeleteFromServiceFunctionality("wholesaleService", map.get("PE_deviceName"));
			
//			APT_wholesaleHelper.get().PEdevice__DeleteFromServiceFunctionality("wholesaleService", map.get("PE_deviceName2"));
		}

	
		
		@Test(description = "TC-07", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=28)
		public void addTrunk(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("addTrunk");
			Thread.sleep(2000);
			
			String siteOrderNumber=null;
			
			APT_wholesaleHelper.get().clickOnBreadCrump("wholesaleService", map.get("ServiceIdentification"));
			
			APT_wholesaleHelper.get().addTrunkSiteOrder("wholesaleService", map.get("TrunkGroupOrder"), map.get("TrunkGroupOrderNumber"));
			
			APT_wholesaleHelper.get().editSiteOrder("wholesaleService", map.get("TrunkGroupOrderNumber"), map.get("edit_TrunkGroupOrder"), map.get("edit_TrunkGroupOrderNumber"));
			
			if(map.get("edit_TrunkGroupOrderNumber").equalsIgnoreCase("null")) {
				
				siteOrderNumber= map.get("TrunkGroupOrderNumber");
						
			}else{
				siteOrderNumber=map.get("edit_TrunkGroupOrderNumber");
			}
			
			APT_wholesaleHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("wholesaleService", siteOrderNumber );
			
			APT_wholesaleHelper.get().addTrunk("wholesaleService", map.get("Name"), map.get("ServiceIdentification") ,map.get("trunkType"), map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"),
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
			
			APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "Trunk created successfully");
		}
		
		@Test(description = "TC-08", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=29)
		public void viewTrunk(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("viewTrunk");
			Thread.sleep(2000);
			
			APT_wholesaleHelper.get().viewTrunk_Primary("wholesaleService", map.get("Name"), map.get("ServiceIdentification") ,map.get("trunkType"), map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"),
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
			
		}
			
			
		@Test(description = "TC-09", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=30)
		public void editTrunk(Map<String, String> map) throws Exception {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("editTrunk");
			Thread.sleep(2000);
			
			APT_wholesaleHelper.get().editTrunk("wholesaleService",map.get("Name"), map.get("ServiceIdentification"),  map.get("trunkType"), map.get("edit_TrunkType"), map.get("edit_VOIPprotocol"), map.get("edit_BillingCountry"), map.get("edit_CDRdelivery"),
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
					map.get("editCreateLowerCaseRoutervalue"), map.get("edit_PSXmanualConfigvalue"), map.get("edit_GSXmanualConfigvalue"),map.get("edit_SBCmanualconfigValue"));
			
		}
		
		
//		@Test(description = "TC-10", dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=31)
		public void AddTrunk_Resilient(Map<String, String> map) throws Exception {
			
			APT_wholesaleHelper.get().verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel("wholesaleService", map.get("TrunkGroupOrderNumber"));
			
			APT_wholesaleHelper.get().addResilienttrunk("wholesaleService", map.get("Name"), map.get("ServiceIdentification") ,map.get("trunkType"), map.get("voipProtocol"), map.get("BillingCountry"), map.get("CDRdelivery"),
					map.get("gateway"), map.get("quality"), map.get("trafficDirection"), map.get("ipAddresstype"), map.get("SIPsignallingPort"),
					map.get("internetBasedCustomer"), map.get("vlanTag"), map.get("subInterfaceSlot"), map.get("signallngZone"), map.get("signallingtransportProtocol"),
					map.get("coltSignalingIP"), map.get("mediaIP"), map.get("reuseNIFgroup"), map.get("reuseSigZonePart"), map.get("callAdmissionControl"),
					map.get("callrateLimitselection"), map.get("sourceAddressFiltering"), map.get("relSupport"), map.get("sipSessionkeepAliveTimer"), map.get("retryInvite"),
					map.get("routePriority"), map.get("addressReachability"), map.get("carrierIPoriginating"), map.get("carrierIPterminating"), map.get("TLSfield"),
					map.get("srtp"), map.get("prefix"), map.get("globalProfile_ExistingSelection"), map.get("globalProfile_newSelection"),map.get("globalProfile_ExistingValue"),map.get("globalProfile_newValue"), 
					map.get("localProfile_existingvalue"), map.get("localProfile_newvalue"),map.get("COSprofile_existingValue"), map.get("COSprofile_newValue"),
					map.get("pspgName_existingValue"), map.get("pspgName_newValue"),map.get("preferredPSP_exitingvalue"), map.get("preferredPSP_newvalue"),
					map.get("carrier_existingValue"), map.get("carrier_newValue"),map.get("IPsignallingProfile_existingValue"), map.get("IPsignallingProfile_newValue"),
					map.get("EgressIPsignal_existingValue"), map.get("EgressIPsignal_newValue"),map.get("InDMPMrule_existingValue"), map.get("InDMPMrule_newValue"),
					map.get("OutDMPMrule_existingValue"), map.get("OutDMPMrule_newValue"),map.get("featureControlprofile_existingValue"), map.get("featureControlprofile_newValue"),
					map.get("localRingBackTone_existingFieldSelection"), map.get("localRingBackTone_newFieldSelection"), map.get("localRingBackTone_existingValue"), map.get("localRingBackTone_newValue"),
					map.get("createLowerCaseRoutervalue"), map.get("PSXmanualConfigvalue"), map.get("GSXmanualConfigvalue"), map.get("callLimit"), map.get("limitNumber"), map.get("callrateLimiteValue"),
					
					map.get("gateway_resilientTrunk"), map.get("voip_resilientTrunk"), map.get("trafficDirection_resiltrunk"), map.get("ipAddressType_resilTrunk"), map.get("carierIPOrient_resiltrunk"),
					map.get("carierIPterminat_resiltrunk"));
		}
//		

		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=32)
		public void viewTrunk_GSX_PSX_SBC_configuration(Map<String, String> map) throws Exception {
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("viewTrunk_GSX_PSX_configuration");
			
			APT_wholesaleHelper.get().clickOnViewLink("wholesaleService", "ITLNASD0E3708", "SiVA_A45");
			
			APT_wholesaleHelper.get().viewTrunk_PSX_executeConfiguration("wholesaleService", map.get("viewtrunk_PSXconfiguration"));
			
			String Gateway=null;
			
			if(map.get("editGateway").equalsIgnoreCase("null")) {
				Gateway=map.get("gateway");
			}
			else {
				Gateway=map.get("editGateway");
			}
			
			
			if(Gateway.contains("SBC")) {
			
				APT_wholesaleHelper.get().viewTrunk_SBC_executeConfiguration("wholesaleService", map.get("viewtrunk_SBCconfiguration"));
			
			}
			else
			{
				
				APT_wholesaleHelper.get().viewTrunk_GSX_executeConfiguration("wholesaleService", map.get("viewtrunk_GSXconfiguration"));
			}
			
		}
		
		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=33)
		public void SBCmanualConfig(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("SBCmanualConfig");
			
			String Gateway=null;
					
			if(map.get("editGateway").equalsIgnoreCase("null")) {
				Gateway=map.get("gateway");
			}
			else {
				Gateway=map.get("editGateway");
			}
			
			
			if(Gateway.contains("SBC")) {
				
				APT_wholesaleHelper.get().addSBC_manualExecutionConfig("wholesaleService", map.get("SBCmanualConfigvalue"));
				
				APT_wholesaleHelper.get().verifySBCfileAdded("wholesaleService");
				
				APT_wholesaleHelper.get().editSBC_manualExecutionConfig("wholesaleService", map.get("editSBCmanualConfigvalue"));
				
				APT_wholesaleHelper.get().deleteSBC_manualExecutionConfig("wholesaleService");
				
			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "'SBC Manual Execution Configuration' panel will not display, if 'SBC' gateway not selected ");
			}
		}
		
		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=34)
		public void PSXmanualConfig(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("PSXmanualConfig");
			
			APT_wholesaleHelper.get().addPSX_manualExecutionConfig("wholesaleService", map.get("PSXmanualConfigValue"));
			
			APT_wholesaleHelper.get().verifyPSXfileAdded("wholesaleService");
			
			APT_wholesaleHelper.get().editPSX_manualExecutionConfig("wholesaleService", map.get("editPSXmanualConfigValue"));
			
			APT_wholesaleHelper.get().deletePSX_manualExecutionConfig("wholesaleService");
			
		}
		
		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=35)
		public void GSXmanualConfig(Map<String, String> map) throws InterruptedException, DocumentException, IOException {
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("GSXmanualConfig");
			
			String Gateway=null;
			
			if(map.get("editGateway").equalsIgnoreCase("null")) {
				Gateway=map.get("gateway");
			}
			else {
				Gateway=map.get("editGateway");
			}
			
			if(Gateway.contains("SBC")) {
				DriverTestcase.logger.log(LogStatus.INFO, "'GSX Manual Execution Configuration' panel will not display, if 'SBC' gateway is selected ");
			}
			else {
				
				APT_wholesaleHelper.get().addGSX_manualExecutionConfig("wholesaleService", map.get("GSXmanualConfigValue"));
				
				APT_wholesaleHelper.get().verifyGSXfileAdded("wholesaleService");
				
				APT_wholesaleHelper.get().editGSX_manualExecutionConfig("wholesaleService", map.get("editGSXmanualConfigValue"));
				
				APT_wholesaleHelper.get().deleteGSX_manualExecutionConfig("wholesaleService");
			
			}
		}
	
		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=36)
		public void addCPEdevice(Map<String, String> map) throws Exception {
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("addCPEdevice");
			
			String siteOrderNumber=null;
			
			if(map.get("edit_TrunkGroupOrderNumber").equalsIgnoreCase("null")) {
				
				siteOrderNumber= map.get("TrunkGroupOrderNumber");
						
			}else{
				siteOrderNumber=map.get("edit_TrunkGroupOrderNumber");
			}
			
			String trunkName=APT_wholesaleHelper.get().fetchTrunkName("wholesaleService");
			
			APT_wholesaleHelper.get().clickOnBreadCrump("wholesaleService", map.get("ServiceIdentification"));
			Thread.sleep(2000);
			
			APT_wholesaleHelper.get().clickOnCPEdeviceLink("wholesaleService", trunkName, siteOrderNumber);
			
			APT_wholesaleHelper.get().addCPEdevice("wholesaleService", map.get("CPEdevice_routerID"), map.get("CPEdevice_vendorModel"),
					map.get("CPEdevice_managementAddress"), map.get("CPEdevice_Snmpro"), map.get("CPEdevice_Snmprw"), map.get("CPEdevice_SNMPv3Contextname"),
					map.get("CPEdevice_SNMPv3ContextEngineId") , map.get("CPEdevice_SNMPv3securityUsername"), map.get("CPEdevice_SNMPv3AutoProto"), map.get("CPEdevice_SNMPv3AuthPasswrd"),
					map.get("Country"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
					map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
					map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
			
			APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "CPE Device added successfully");
			
		}
		
		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=37)
		public void viewCPEdevice(Map<String, String> map) throws Exception {
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("viewCPEdevice");
		
			APT_wholesaleHelper.get().CPEdevice_clickOnViewLink("wholesaleService", map.get("CPEdevice_routerID"));
			
			APT_wholesaleHelper.get().viewCPEdevice("wholesaleService", map.get("CPEdevice_routerID"), map.get("CPEdevice_vendorModel"),
					map.get("CPEdevice_managementAddress"), map.get("CPEdevice_Snmpro"), map.get("CPEdevice_Snmprw"), map.get("CPEdevice_SNMPv3Contextname"),
					map.get("CPEdevice_SNMPv3ContextEngineId") , map.get("CPEdevice_SNMPv3securityUsername"), map.get("CPEdevice_SNMPv3AutoProto"), map.get("CPEdevice_SNMPv3AuthPasswrd"),
					map.get("Country"), map.get("ExistingCity"), map.get("ExistingCityValue"), map.get("ExistingSite"),
					map.get("Existing SiteValue"), map.get("ExistingPremise"), map.get("Existing PremiseValue"), map.get("NewCity"), map.get("NewCityName"), map.get("NewCityCode"), 
					map.get("NewSiteName"),map.get("NewSiteCode"), map.get("NewPremiseName"), map.get("NewPremiseCode"), map.get("NewSite"), map.get("NewPremise"));
		}
	

		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=38)
		public void editCPEdevice(Map<String, String> map) throws Exception {
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("editCPEdevice");
			
			APT_wholesaleHelper.get().editCPEdeviceLink("wholesaleService");
			
			APT_wholesaleHelper.get().editCPEdevice("wholesaleService", map.get("editCPEdevice_routerID"), map.get("editCPEdevice_vendorModel"), map.get("editCPEdevice_managementAddress"),
					map.get("editCPEdevice_Snmpro"), map.get("editCPEdevice_Snmprw"), map.get("editCPEdevice_SNMPv3Contextname"), map.get("editCPEdevice_SNMPv3ContextEngineId"),
					map.get("editCPEdevice_SNMPv3securityUsername"), map.get("editCPEdevice_SNMPv3AuthProto"), map.get("editCPEdevice_SNMPv3AuthPasswrd"),
					map.get("editCountry"), map.get("editExistingCity"),
					map.get("editExistingCityValue"), map.get("editExistingSite"), map.get("editExistingSiteValue"), map.get("editExistingPremise"), map.get("editExistingPremiseValue"),
					map.get("editNewCity"), map.get("editNewSite"), map.get("editNewPremise"), map.get("editNewCityName"), map.get("editNewCityCode"), map.get("editNewSiteName"),
					map.get("editNewSiteCode"), map.get("editNewPremiseName"), map.get("editNewPremiseCode"));
			
			APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "CPE Device updated successfully");
			
		}
		
		

		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=39)
		public void routerPanel_CPEdevice(Map<String, String> map) throws Exception {
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("routerPanel_CPEdevice");
		
			APT_wholesaleHelper.get().routerPanel("wholesaleService", map.get("CPE_command_ipv4"), map.get("CPE_command_ipv6"), map.get("CPE_vrf_Ipv4"), map.get("CPE_vrf_Ipv6"));
		}
		
		
		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=40)
		public void deleteCPEdevice(Map<String, String> map) throws Exception {
		
			DriverTestcase.logger = DriverTestcase.extent.startTest("deleteCPEdevice");
		
			String routerId=null;
			
			if(map.get("editCPEdevice_routerID").equalsIgnoreCase("null")) {
				routerId=map.get("CPEdevice_routerID");
			}
			else {
				routerId=map.get("editCPEdevice_routerID");
			}
			
			APT_wholesaleHelper.get().clickOnBreadCrump("wholesaleService", map.get("ServiceIdentification"));
			Thread.sleep(2000);
			
			APT_wholesaleHelper.get().CPEdevice_clickOnDeleteLink("wholesaleService", routerId );
			
			APT_wholesaleHelper.get().verifysuccessmessage("wholesaleService", "CPE Device deleted successfully");
			
		}
			
//		
//		@Test(description = "TC-12",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_wholeSale", priority=41)
//		public void deleteTrunk(Map<String, String> map) throws Exception {
//		
//			DriverTestcase.logger = DriverTestcase.extent.startTest("deleteTrunk");
//			
//			String siteOrderNumber=null;
//			
//			if(map.get("edit_TrunkGroupOrderNumber").equalsIgnoreCase("null")) {
//				
//				siteOrderNumber= map.get("TrunkGroupOrderNumber");
//						
//			}else{
//				siteOrderNumber=map.get("edit_TrunkGroupOrderNumber");
//			}
//			
//			String trunkName=APT_wholesaleHelper.get().fetchTrunkName("wholesaleService");
//			
//			APT_wholesaleHelper.get().deleteTrunk("wholesaleService",  trunkName, siteOrderNumber );
//		}	
		
}
