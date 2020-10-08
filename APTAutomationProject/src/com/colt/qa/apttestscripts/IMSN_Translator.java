package com.colt.qa.apttestscripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.colt.qa.excellibrary.DataReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.relevantcodes.extentreports.LogStatus;



public class IMSN_Translator extends DriverTestcase{
	

	   APT_Login Login = new APT_Login();
	   
	   
		@Test(dataProviderClass = DataReader.class, dataProvider = "DataReader_ManageTranslation", priority=1)
		public void manageColtNetwork(Map<String, String> map) throws Exception {

			setup();
			
			Login.APT_Login_1(map.get("url"));
		
			logger = ExtentTestManager.startTest("Navigate to 'Manage Number Translation'");
			ImsNmbrTranslator_Helper.get().selectImsTranslator("ManageColt");
			ExtentTestManager.endTest();
			
			logger= ExtentTestManager.startTest ("verifyListofCountries");
				ImsNmbrTranslator_Helper.get().verifyManageNumberTranslationcountrypage("ManageColt");
				System.out.println("Verified the list of countries");
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest ("verifyFieldsInManageTranslationPage");
				ImsNmbrTranslator_Helper.get().verifyManageNumberTranslation("ManageColt",map.get("countryCode"));
				ExtentTestManager.endTest(); 

			logger= ExtentTestManager.startTest ("wildcardsearch");
				ImsNmbrTranslator_Helper.get().verifyWildcardsearch("ManageColt");
				ExtentTestManager.endTest(); 
		
			logger= ExtentTestManager.startTest ("verifyAddTranslationfields");
				ImsNmbrTranslator_Helper.get().verifyAddnumberTranslationfields("ManageColt",map.get("countryCode"));
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest ("entervaluesinAddTranslation");
				ImsNmbrTranslator_Helper.get().filladdtranslation("ManageColt",map.get("countryCode"),map.get("TranslateNumber"),map.get("TranslatedNumber"),
						map.get("PrefixNumbertextField"), map.get("NatureOfAddress"),map.get("CarrierNo"), map.get("prefixCheckbox"), map.get("Range"),
						map.get("sequence"), map.get("countryName"));
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest ("verifyAddedNumberTranslationFunction");
				ImsNmbrTranslator_Helper.get().verifyAddnumbertranslationfunction("ManageColt",map.get("countryCode"),
						map.get("TranslateNumber"),map.get("TranslatedNumber"),map.get("PrefixNumbertextField"),map.get("CarrierNo"),
						 map.get("prefixCheckbox"), map.get("Range"), map.get("sequence"), map.get("NatureOfAddress"));
				ExtentTestManager.endTest();
				
			logger= ExtentTestManager.startTest ("editNumberTranslationFunction");	
				ImsNmbrTranslator_Helper.get().editIMSNT("ManageColt",map.get("countryCode"),map.get("TranslateNumber"),map.get("editTranslatedNumber"),
						map.get("editPrefixNumber"), map.get("editCarrier"), map.get("editPrefixCheckbox"));
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest ("Synchronize");	
				ImsNmbrTranslator_Helper.get().synchronise("ManageColt", map.get("TranslateNumber"));
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest ("verifyUploadupdatefile");
				ImsNmbrTranslator_Helper.get().createFileForUploadUpdateFile("ManageColt", map.get("Operation"), map.get("TranslateNumber"), map.get("countryCode"),
						map.get("TranslatedNumber"), map.get("editTranslatedNumber"), map.get("PrefixNumbertextField"), map.get("editPrefixNumber"),
						map.get("CarrierNo"), map.get("editCarrier"), map.get("NatureOfAddress"), map.get("filePath"));
				ImsNmbrTranslator_Helper.get().uploadUpdatefile("ManageColt", map.get("filePath"));
				ImsNmbrTranslator_Helper.get().deleteFile(map.get("filePath"));
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest ("verifydownlaodTranslationlink");
				ImsNmbrTranslator_Helper.get().downlaodTranslationlink("ManageColt");
				Thread.sleep(2000);
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest ("verifyviewUIHistory");
				ImsNmbrTranslator_Helper.get().viewUIHistory("ManageColt",map.get("Operation"),map.get("User"),map.get("TranslateNumber"));
				Thread.sleep(2000);
				ExtentTestManager.endTest(); 
			
			logger= ExtentTestManager.startTest ("verifyViewHistory");
				ImsNmbrTranslator_Helper.get().viewuploadHistory("ManageColt");
				ExtentTestManager.endTest(); 
				
			logger= ExtentTestManager.startTest ("deleteNumberTranslationFunction");	
				ImsNmbrTranslator_Helper.get().deleteIMST("ManageColt", map.get("TranslateNumber"));
				ExtentTestManager.endTest(); 
			
		}
				

}
