package com.saksoft.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.scripthelpers.APT_LoginHelper;

public class APT_ManageNetworkTest extends DriverTestcase{
	
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_ManageNetwork", priority=0)
	public void managenetwork(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("ManageNetwork");
		APT_MainManageNetworkHelper.get().searchdevice("ManageNetwork", map.get("DeviceName"));
		APT_MainManageNetworkHelper.get().managenetwork("ManageNetwork", map.get("DeviceName"), map.get("InServiceStatus"), map.get("InMaintenanceStatus"), map.get("VendorModel"), map.get("ManagementAddress"), map.get("Snmpro"), map.get("Country"), map.get("City"), map.get("Site"), map.get("Premise"), map.get("InterfaceName"));
		
	} 
	

}
