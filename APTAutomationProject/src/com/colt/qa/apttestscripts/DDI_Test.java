package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sun.org.glassfish.gmbal.Description;
import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.scripthelpers.APT_LoginHelper;

public class DDI_Test extends DriverTestcase{

	
	APT_Login Login=new APT_Login();
	
	@Test(description = "TC-01",dataProviderClass = DataReader.class, dataProvider = "DataReader_DDIRange", priority=2)
	public void DDIRange(Map<String, String> map) throws Exception {
		
		
		
		setup();	
		Login.APT_Login_1(map.get("url for the Product"));
		
		
		
//		System.out.println("TC-01");
//		logger= ExtentTestManager.startTest("SearchTrunkName");
//		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
//		ExtentTestManager.endTest();
//		
//		
//		
//		System.out.println("TC-02");
//		logger= ExtentTestManager.startTest("selectTrunk");
//		DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
//		ExtentTestManager.endTest();
//		
//		
//		System.out.println("TC-03");
//		logger= ExtentTestManager.startTest("VerifyDDIRangefields");
//		DDI_Helper.get().VerifyDDIRangefields("DDIRange");
//		ExtentTestManager.endTest();
//		
//	
//		System.out.println("TC-04");
//		logger= ExtentTestManager.startTest("createDDIRange");
//		DDI_Helper.get().createDDIRange("DDIRange",map.get("CountrycodeValue"),map.get("LACValue"),map.get("MainNumbervalue"),map.get("RangestartValue"),map.get("RangeEndValue"),
//	    		  map.get("ExtensionDigits"),map.get("ActivateIncomingRouting"),map.get("InGeo"));
//		ExtentTestManager.endTest();
//		
//	
//		System.out.println("TC-05");
//		logger= ExtentTestManager.startTest("ViewDDIRange");
//		DDI_Helper.get().ViewDDIRange("DDIRange",map.get("CountrycodeValue"),map.get("LACValue"),map.get("MainNumbervalue"),map.get("RangestartValue"),map.get("RangeEndValue"),
//	    		  map.get("ExtensionDigits"),map.get("ActivateIncomingRouting"),map.get("InGeo"), map.get("PSXconfigurationValue"));
//		ExtentTestManager.endTest();
//		
//		
//		
//		System.out.println("TC-06");
//		logger= ExtentTestManager.startTest("editDDIRange");
//		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
//        DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
//	    DDI_Helper.get().editDDIRange("DDIRange",map.get("LACValue"),map.get("ExtensionDigits"));
//	    ExtentTestManager.endTest();
//		
		
		System.out.println("TC-07");
		logger= ExtentTestManager.startTest("showDDIRange");
		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
        DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
        DDI_Helper.get().showDDIRange("DDIRange");
        ExtentTestManager.endTest();
		
		
		System.out.println("TC-08");
		logger= ExtentTestManager.startTest("UploadDDIRange");
		DDI_Helper.get().UploadDDIRange("DDIRange",map.get("expected"), map.get("filePath_forUploading"));
		ExtentTestManager.endTest();
		
		
		System.out.println("TC-09");
		logger= ExtentTestManager.startTest("downloadDDIRange");
		DDI_Helper.get().downloadDDIRange("DDIRange");
		ExtentTestManager.endTest();
		
		
		System.out.println("TC-10");
		logger= ExtentTestManager.startTest("duplicateDDIRange");
		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
		DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
		DDI_Helper.get().duplicateDDIRange("DDIRange");
		ExtentTestManager.endTest();
		
		
	
		System.out.println("TC-11");
		logger= ExtentTestManager.startTest("searchDDIRange");
		DDI_Helper.get().searchDDIRange("DDIRange");
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-12");
		logger= ExtentTestManager.startTest("fillfieldDDIRange");
		DDI_Helper.get().searchDDIRange("DDIRange");
		DDI_Helper.get().fillfieldDDIRange("DDIRange", map.get("LACValue"),map.get("CountrycodeValue"));
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-13");
		logger= ExtentTestManager.startTest("viewSearchDDIRange");
		DDI_Helper.get().viewSearchDDIRange("DDIRange", map.get("LACValue"),map.get("CountrycodeValue"), map.get("TrunkValue"));
		ExtentTestManager.endTest();
		
		
		
		System.out.println("TC-14");
		logger= ExtentTestManager.startTest("deleteDDIRange");
		DDI_Helper.get().SearchTrunkName("DDIRange",map.get("TrunkValue"));
		DDI_Helper.get().selectTrunk("DDIRange",map.get("TrunkValue"));
		DDI_Helper.get().deleteDDIRange("DDIRange",map.get("LACValue"));
		ExtentTestManager.endTest();
		
	
              
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
