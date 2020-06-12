package com.saksoft.qa.apttestscripts;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DataReader_PK;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.scripthelpers.APT_LoginHelper;

public class APT_MSPLatencyGridTest extends DriverTestcase{
	
	
	@Test(description = "MSP Latency Grip",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_MSPLatency", priority=0)
	public void VerifyMSPLatency(Map<String, String> map) throws Exception {
		
		DriverTestcase.logger = DriverTestcase.extent.startTest("VerifyMSPLatency"); 
		APT_MSPLatencyHelper.get().VerifyMSPLatency("msplatency", map.get("City"), map.get("DeviceIP"), map.get("VLANID"), map.get("CircuitName"), map.get("ThresholdFileName"));		
	} 
	
}
