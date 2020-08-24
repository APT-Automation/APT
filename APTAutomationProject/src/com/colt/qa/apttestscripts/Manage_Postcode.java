package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DataReader_PK;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.excellibrary.APT_DataReader_SS;
import com.colt.qa.scripthelpers.APT_LoginHelper;
import com.relevantcodes.extentreports.LogStatus;



public class Manage_Postcode extends DriverTestcase{

	   APT_Login Login = new APT_Login();
	   
		@Test(dataProviderClass = DataReader_PK.class, dataProvider = "DataReader_ManagePostcode", priority=1)
		public void manageColtNetwork(Map<String, String> map) throws Exception {

			String countryName=map.get("Countrylist");
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("manageColtNetwork");
			
				setup();
				
				Login.APT_Login_1(map.get("url"));
			
				ManagePostcode_Helper.get().selectColtnetwork("ManageColt");
			
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyListofCountries");
				ManagePostcode_Helper.get().verifyManagePostcodepage("ManageColt");
			
				
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddPostCodeFields"+ countryName);
				ManagePostcode_Helper.get().verifyManagePostcodeinternal("ManageColt",map.get("Countrylist"));
	
				
			DriverTestcase.logger = DriverTestcase.extent.startTest("addpostcode");
				ManagePostcode_Helper.get().addPostcode("ManageColt",map.get("Countrylist"),map.get("EmergencyAreaIDSub"),map.get("NtServiceAreaValue"),
						map.get("ImplementSwitchValue"),map.get("CityTranValue"),map.get("SubProvinValue"),map.get("SubAreaValue"),map.get("SubAreaComValue"),
						map.get("SubAreaIDValue"),map.get("SubAreaBValue"),map.get("SubAreaZIpValue"),map.get("EmergencySUbValue"),map.get("EmergencyKeyValue"),
						map.get("ActualValue"),map.get("DummyCodeValue"));
				
				ManagePostcode_Helper.get().fillAddPostCode("ManageColt",map.get("Countrylist"),map.get("EmergencyAreaIDSub"),map.get("NtServiceAreaValue"),
						map.get("ImplementSwitchValue"),map.get("CityTranValue"),map.get("SubProvinValue"),map.get("SubAreaValue"),map.get("SubAreaComValue"),
						map.get("SubAreaIDValue"),map.get("SubAreaBValue"),map.get("SubAreaZIpValue"),map.get("EmergencySUbValue"),map.get("EmergencyKeyValue"),
						map.get("ActualValue"),map.get("DummyCodeValue"));
			
				
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyAddPostcode");
				ManagePostcode_Helper.get().verifyPostcodevalues("ManageColt",map.get("Countrylist"),map.get("EmergencyAreaIDSub"),map.get("ImplementSwitchValue"),map.get("CityTranValue"),
						map.get("SubProvinValue"),map.get("SubAreaValue"),map.get("SubAreaComValue"),map.get("SubAreaIDValue"),map.get("SubAreaBValue"),map.get("SubAreaZIpValue")
						,map.get("EmergencySUbValue"));
				
				ManagePostcode_Helper.get().editPostcode("ManageColt",map.get("Countrylist"),map.get("EmergencyAreaIDSub"), map.get("edit_ImplementSwitchValue"), map.get("edit_CityTranValue"),
						map.get("edit_SubArea1"), map.get("edit_SubArea1_ID"), map.get("edit_SubArea2"), map.get("edit_SubArea2_ID"), map.get("edit_SubArea3"), map.get("edit_SubArea3_ID"),
						map.get("edit_EmergencyArea"), map.get("replaceEmergencyArea"), map.get("updateEmergencyArea"), map.get("edit_EmergencyKeyValue"),
						map.get("edit_ActualValue"), map.get("edit_DummyCodeValue"));
			
				
			DriverTestcase.logger = DriverTestcase.extent.startTest("Uploadupdatefile");
				ManagePostcode_Helper.get().createFileForUploadupdateFile(map.get("filePathForUploadUpdateFletestDataToUpload"), 
						map.get("addUploadUpdateFiletestData_column1"), map.get("addUploadUpdateFiletestData_column2"));
				ManagePostcode_Helper.get().verifyUploadupdatefile("ManageColt", map.get("filePathForUploadUpdateFletestDataToUpload"));
				ManagePostcode_Helper.get().deleteFile(map.get("filePathForUploadUpdateFletestDataToUpload"), "Upload Update File");
				
			
				
			DriverTestcase.logger = DriverTestcase.extent.startTest("addEmergencyNumber");
				ManagePostcode_Helper.get().createFileForAddEmergencyNumber(map.get("filePathForAddEmergencyNumberTestDataToUpload"), map.get("EmergencyAreaIDSub"),
						map.get("addEmergencyNumberTestdata_DummyCodeValue"), map.get("addEmergencyNumberTestata_ActualProviderValue"), map.get("addEmergencyNumberTestData_EmergencyKeyValue"));
				ManagePostcode_Helper.get().verifyAddEmergencyNumber("ManageColt",map.get("filePathForAddEmergencyNumberTestDataToUpload"));
				ManagePostcode_Helper.get().deleteFile( map.get("filePathForAddEmergencyNumberTestDataToUpload"), "Add Emergency Number");
				
			DriverTestcase.logger = DriverTestcase.extent.startTest("viewHistory");
				ManagePostcode_Helper.get().verifyViewHistory("ManageColt", map.get("Countrylist"));

				
			DriverTestcase.logger = DriverTestcase.extent.startTest("NtserviceArea");
				ManagePostcode_Helper.get().createFileForuploadNTServiceArea("ManageColt", map.get("filePathForUploadNTServiceAreaTestDataToUpload"),
						map.get("EmergencyAreaIDSub"), map.get("Countrylist"));
				ManagePostcode_Helper.get().verifyUploadNtserviceArea("ManageColt",map.get("filePathForUploadNTServiceAreaTestDataToUpload"));
				ManagePostcode_Helper.get().deleteFile(map.get("filePathForUploadNTServiceAreaTestDataToUpload"), "Upload NT Service Area");

				
			DriverTestcase.logger = DriverTestcase.extent.startTest("verifyDownloadNtArea");
				ManagePostcode_Helper.get().verifyDownloadNt("ManageColt");
			
			
			DriverTestcase.logger = DriverTestcase.extent.startTest("delete postcode");
				ManagePostcode_Helper.get().deletePostcode("ManageColt",map.get("Countrylist"),map.get("EmergencyAreaIDSub"));
			
		}

				

}
