package com.colt.qa.scripthelpers;

import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class APT_LoginHelper extends DriverHelper {

	XMLReader xml = new XMLReader("src\\com\\colt\\qa\\pagerepository\\APT_LoginPage.xml");

	public APT_LoginHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * APT Page Login
	 * 
	 * @param Application
	 * @throws Exception
	 */
	
	public void Login(String Application) throws Exception {
		
		if (Application.equals("APT_NonVoiceService")) {
			
			openurl(Application);

			Thread.sleep(2000);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Username")),
					Getkeyvalue(Application + "_Username"));
			//ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter User Name");

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Password")),
					Getkeyvalue(Application + "_Password"));
			//ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Password");

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Loginbutton")));
			
			com.colt.qa.driverlibrary.Log.info("=== APT logged in successfully ===");
			System.out.println("APT logged in successfully");
			//DriverTestcase.logger.log(LogStatus.PASS, "Step :  APT logged in successfully ");

		}

		else if (Application.equals("APT_VoiceService")) {

			openurl(Application);

			Thread.sleep(3000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Username")),
					Getkeyvalue(Application + "_Username"));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter User Name");

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Password")),
					Getkeyvalue(Application + "_Password"));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Password");

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Loginbutton")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Login Button");
			
			com.colt.qa.driverlibrary.Log.info("=== APT logged in successfully ===");

		}

	}
	
	
	
	public  void logout_APT(String Application) throws InterruptedException, DocumentException {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Logout_Button")));
		Thread.sleep(2000);
		
	}

}
