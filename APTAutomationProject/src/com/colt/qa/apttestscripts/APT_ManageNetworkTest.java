package com.colt.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DataReader_PK;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.scripthelpers.APT_LoginHelper;

public class APT_ManageNetworkTest extends DriverTestcase{
	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_ManageNetwork", priority=0)
	public void managenetwork(Map<String, String> map) throws Exception {
		
		setup();	
		Login.APT_Login_1(map.get("url"));
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("ManageNetwork");
		APT_ManageNetworkHelper.get().searchdevice("ManageNetwork", map.get("DeviceName"));
		APT_ManageNetworkHelper.get().manageNetwork("ManageNetwork", map.get("DeviceName"), map.get("InServiceStatus"), map.get("InMaintenanceStatus"), map.get("VendorModel"), map.get("ManagementAddress"), map.get("Snmpro"), map.get("Country"), map.get("City"), map.get("Site"), map.get("Premise"), map.get("InterfaceName"), map.get("FMC_column"), map.get("SMARTS_column"), map.get("DCS_column"), map.get("FetchInterfaces_column"), map.get("Vistamart_column"), map.get("PGW_column"));
		
	} 
	

}
