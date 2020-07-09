package com.saksoft.qa.scripthelpers;

import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.XMLReader;
import com.saksoft.qa.reporter.ExtentTestManager;

public class APT_LoginHelper extends DriverHelper {

	XMLReader xml = new XMLReader("src\\com\\saksoft\\qa\\pagerepository\\APT_LoginPage.xml");

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
	public void LoginHeader(String Application) throws Exception {

		if (Application.equals("APT_headerlogin")) {

			openurl(Application);

			Thread.sleep(3000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Username")),
					Getkeyvalue(Application + "_Username"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter User Name");

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Password")),
					Getkeyvalue(Application + "_Password"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Password");

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Loginbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Login Button");
			
			
		}

		else if (Application.equals("APT_invalidHeaderLogin")) {

			openurl(Application);

			Thread.sleep(3000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Username")),
					Getkeyvalue(Application + "_Username"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter User Name");

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Password")),
					Getkeyvalue(Application + "_Password"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Password");

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Loginbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Login Button");

		}

	}

	public void Login(String Application) throws Exception {
		
		
		
		
		if (Application.equals("APT_login_1")) {
			
			openurl(Application);

			Thread.sleep(2000);
//			click_commonMethod(Application, "Login", "Loginbutton" , xml);
//			
//			compareText(Application, "Error Message in login page", "fetchErrorMessage", "You have entered incorrect Login Id or Password" , xml);
			
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Username")),
					Getkeyvalue(Application + "_Username"));
//			addtextFields_commonMethod(Application, "Username", "Username", Getkeyvalue(Application + "_Username"), xml);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Password")),
					Getkeyvalue(Application + "_Password"));
//			addtextFields_commonMethod(Application, "Password", "Password", Getkeyvalue(Application + "_Password"), xml);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Loginbutton")));

			
			
			com.saksoft.qa.driverlibrary.Log.info("=== APT logged in successfully ===");
			System.out.println("APT logged in successfully");
			//DriverTestcase.logger.log(LogStatus.PASS, "Step :  APT logged in successfully ");

		}

		else if (Application.equals("APT_login")) {

			openurl(Application);

			Thread.sleep(3000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Username")),
					Getkeyvalue(Application + "_Username"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter User Name");

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Password")),
					Getkeyvalue(Application + "_Password"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Password");

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Loginbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Login Button");
			
			com.saksoft.qa.driverlibrary.Log.info("=== APT logged in successfully ===");

		}

		else if (Application.equals("APT_invalidLogin")) {

			openurl(Application);

			Thread.sleep(3000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Username")),
					Getkeyvalue(Application + "_Username"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter User Name");

			SendKeys(getwebelement(xml.getlocator("//locators/" + Application + "/Password")),
					Getkeyvalue(Application + "_Password"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Password");

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Loginbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Login Button");
			
			com.saksoft.qa.driverlibrary.Log.info("=== APT failed to logged In ===");
		}
		

	}
	
	
	
	public  void logout_APT(String Application) throws InterruptedException, DocumentException {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Logout_Button")));
		Thread.sleep(2000);
		
	}

}
