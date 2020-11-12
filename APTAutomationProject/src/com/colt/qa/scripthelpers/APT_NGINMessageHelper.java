package com.colt.qa.scripthelpers;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class APT_NGINMessageHelper extends DriverHelper{
	
	public APT_NGINMessageHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	
	WebElement el;
	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_NGINMessage.xml");
	
	public void verifyNGINMessage(String application, String nginmessage_sannumber, String startnetwork_checkbox) throws InterruptedException, DocumentException, IOException
	{
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/managecoltnetworklink")));
		Thread.sleep(1000);
		click_commonMethod(application, "NGIN Messages", "nginmessageslink", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		compareText(application, "Manage Messages Header", "nginmessageheader", "Manage Messages - Messages Search", xml);
		isDisplayed(application, "nginmsg_sannumber", "SAN Number text field");
		isDisplayed(application, "nginmsg_customername_textfield", "Customer Name text field");
		Thread.sleep(1000);
		addtextFields_commonMethod(application, "SAN Number", "nginmsg_sannumber", nginmessage_sannumber, xml);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();


		WebElement SelectExistingSAN= getwebelement("//div[text()='"+nginmessage_sannumber+"']/preceding-sibling::div//span[contains(@class,'unchecked')]");
		if(SelectExistingSAN.isDisplayed())
		{
			//Verify existing SAN column headers
			compareText(application, "FRC Number", "nginmsg_frcnumbercolumn", "FRC Number", xml);
			compareText(application, "Name", "nginmsg_namecolumn", "Name", xml);
			compareText(application, "Customer Name", "customernamecolumn", "Customer Name", xml);
			Thread.sleep(1000);
			Clickon(SelectExistingSAN);
			Thread.sleep(2000);
			click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
			//View link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/edit")).isDisplayed())
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Edit link is displaying in Manage Messages page");
				Log.info("Edit link is displaying in Manage Messages page");

				click_commonMethod(application, "Edit", "edit", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				compareText(application, "Edit Message Header", "editmessageheader", "Edit Message", xml);
				scrolltoend();
				click_commonMethod(application, "Cancel", "cancelbutton", xml);
				Thread.sleep(3000);
				waitforPagetobeenable();
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Edit link is not displaying in Manage Messages page");
				Log.info("Edit link is not displaying in Manage Messages page");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing NGIN Messages to display");
			Log.info("No existing NGIN Messages to display");
		}

		WebElement SelectExistingSAN1= getwebelement("//div[text()='"+nginmessage_sannumber+"']/preceding-sibling::div//span[contains(@class,'unchecked')]");
		if(SelectExistingSAN1.isDisplayed())
		{
			Clickon(SelectExistingSAN1);
			Thread.sleep(2000);
			click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/edit")).isDisplayed())
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Edit link is displaying in Manage Messages page");
				Log.info("Edit link is displaying in Manage Messages page");

				click_commonMethod(application, "Edit", "edit", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				compareText(application, "Edit Message Header", "editmessageheader", "Edit Message", xml);
				//verify edit message page fields
				//verify customer name field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_customername")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name field is disabled as expected");
					String Customernamevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_customername")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name field value is displayed as:"+ Customernamevalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Customer Name field is enabled");
				}

				//verify country code field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_countrycode")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Country code field is disabled as expected");
					String CountryCodevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_countrycode")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Country code field value is displayed as:"+ CountryCodevalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Country code field is enabled");
				}

				//verify san reference field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_sanreference")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Reference field is disabled as expected");
					String SANReferencevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_sanreference")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Reference field value is displayed as:"+ SANReferencevalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : SAN Reference field is enabled");
				}

				//verify name field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_name")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Name field is disabled as expected");
					String Namevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_name")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Name field value is displayed as:"+ Namevalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Name field is enabled");
				}

				//verify description field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_description")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Description field is disabled as expected");
					String Descriptionvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_description")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Description field value is displayed as:"+ Descriptionvalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Description field is enabled");
				}

				//verify body field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_bodyfield")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Body field is disabled as expected");
					String Bodyvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_bodyfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Body field value is displayed as:"+ Bodyvalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Body field is enabled");
				}

				//verify prefix field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_prefixfield")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Prefix field is disabled as expected");
					String Prefixvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_prefixfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Prefix field value is displayed as:"+ Prefixvalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Prefix field is enabled");
				}

				//verify repetitions field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_repetitionsfield")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Repetitions field is disabled as expected");
					String Repetitionsvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_repetitionsfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Repetitions field value is displayed as:"+ Repetitionsvalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Repetitions field is enabled");
				}

				//verify duration field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_durationfield")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Duration field is disabled as expected");
					String Durationvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_durationfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Duration field value is displayed as:"+ Durationvalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Duration field is enabled");
				}

				//verify suffix field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_suffixfield")).getAttribute("readonly")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Suffix field is disabled as expected");
					String Suffixvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_suffixfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Suffix field value is displayed as:"+ Suffixvalue);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Suffix field is enabled");
				}

				//verify start network charge checkbox field
				editcheckbox_commonMethod(application, startnetwork_checkbox, "editmessage_startnetworkcharge", "Start Network Charge", xml);
				scrolltoend();
				click_commonMethod(application, "OK", "editmessage_okbutton", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				if(getwebelement(xml.getlocator("//locators/" + application + "/nginmessageheader")).isDisplayed())
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to Manage Messages page");
					Log.info("Navigated to Manage Messages page");
					verifysuccessmessage(application, "Message successfully updated.");
					
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Not navigated to Manage Messages page");
					Log.info("Not navigated to Manage Messages page");
				}
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Edit link is not displaying in Manage Messages page");
				Log.info("Edit link is not displaying in Manage Messages page");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing NGIN Messages to display");
			Log.info("No existing NGIN Messages to display");
		}

	}
	
	public void verifysuccessmessage(String application, String expected) throws InterruptedException {
		
		scrollToTop();
		Thread.sleep(3000);
		try {	
			
			boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMsg")).isDisplayed();

			if(successMsg) {
				
				String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
				
				if(expected.contains(alrtmsg)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
					System.out.println("Message is verified. It is displaying as: "+alrtmsg);
					successScreenshot(application);
					
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
					System.out.println("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
					successScreenshot(application);
				}
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
				System.out.println(" Success Message is not displaying");
			}
			
		}catch(Exception e) {
			Log.info("failure in fetching success message");
			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
			System.out.println(expected+ " message is not getting dislpayed");
			successScreenshot(application);
		}

	}
	
	public void isDisplayed(String application, String xpath, String labelname) {
		boolean availability = false;

		try {
			Thread.sleep(1000);
			availability= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			System.out.println(availability);
			if (availability) {
				Thread.sleep(2000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: '"+labelname+"' is displayed as expected");
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' is not displaying as expected");
			}

		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: '"+labelname+"' is not available to display");
			e.printStackTrace();
		}
	}
	
//	public void successScreenshot(String application) {
//	String screenshotBase64 = "data:image/jpg;base64," +((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
//	String ScreenshottoReport= ExtentTestManager.getTest().addScreenCapture(screenshotBase64);
//	ExtentTestManager.getTest().log(LogStatus.PASS, ScreenshottoReport);
//	
//	}
	
}
