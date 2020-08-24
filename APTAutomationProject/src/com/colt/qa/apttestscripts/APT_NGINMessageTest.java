package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DataReader_PK;
import com.colt.qa.driverlibrary.DriverTestcase;

public class APT_NGINMessageTest extends DriverTestcase{

	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader_PK.class, dataProvider = "Finaldatareader_NGINMessage", priority=2)
	 public void verifyNGINMessage(Map<String, String> map) throws Exception {
		
		setup();
		Login.APT_Login_1(map.get("url"));
		DriverTestcase.logger = DriverTestcase.extent.startTest("verifyNGINMessage");
		APT_NGINMessageHelper.get().verifyNGINMessage("nginmessage", map.get("NGINMessage_SANNumber"), map.get("StartNetwork_Checkbox"));
	}
}