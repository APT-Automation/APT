package com.colt.qa.scripthelpers;

import java.io.File;
import java.io.IOException;

import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class APT_SANManagementHelper extends DriverHelper{

	public APT_SANManagementHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_SANManagement.xml");
	
	public void verifySearchSAN(String application, String search_sannumber, String searchSANfilename, String browserfiles_downloadspath) throws InterruptedException, DocumentException, IOException
	{
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/managecoltnetworklink")));
		click_commonMethod(application, "Search for SANs", "searchsanlink", xml);
		compareText(application, "SAN Search", "sansearchheader", "SAN Search", xml);
		addtextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		waitforPagetobeenable();
		click_commonMethod(application, "Download To Excel", "downloadtoexcellink", xml);
		isFileDownloaded(searchSANfilename, browserfiles_downloadspath);
		waitforPagetobeenable();
		//Rework done
		edittextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		waitforPagetobeenable();
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		waitforPagetobeenable();

		WebElement SelectSAN= getwebelement("//div[contains(text(),'"+search_sannumber+"')]/parent::div//span[contains(@class,'unchecked')]");
		try {
		if(SelectSAN.isDisplayed())
		{
			//Verify SearchSAN columns headers
			compareText(application, "FRC Number", "frcnumber_column", "FRC Number", xml);
			compareText(application, "Service Profile", "serviceprofilecolumn", "Service Profile", xml);
			compareText(application, "Customer Name", "customernamecolumn", "Customer Name", xml);
			compareText(application, "Begin Date", "begindatecolumn", "Begin Date", xml);
			compareText(application, "Order Name", "ordernamecolumn", "Order Name", xml);

			Clickon(SelectSAN);
			click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
			//View link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : View link is displaying in Search SAN page");
				Log.info("View link is displaying in Search SAN page");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : View link is not displaying in Search SAN page");
				Log.info("View link is not displaying in Search SAN page");
			}

			//Delete link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete")).isDisplayed())
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete link is displaying in Search SAN page");
				Log.info("Delete link is displaying in Search SAN page");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete link is not displaying in Search SAN page");
				Log.info("Delete link is not displaying in Search SAN page");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing SAN to display");
			Log.info("No existing SAN to display");
		}
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Step : No existing SAN to display");
				e.printStackTrace();
		}
			click_commonMethod(application, "View", "view", xml);
			compareText(application, "View San Header", "searchsan_viewpageheader", "View San", xml);

			//Verify view San details

			GetText(application, "Customer Name", "viewsan_customername");
			GetText(application, "SAN/FRC Number", "viewsan_sanmgmt");
			GetText(application, "Service Profile", "serviceprofilevalue");
			GetText(application, "International outgoing calls", "internationaloutgoingcallsvalue");
			GetText(application, "International incoming calls", "searchsan_internationalincomingcallsvalue");
			GetText(application, "Mobile calls allowed", "mobilecallsallowedvalue");
			GetText(application, "No reply timer", "view_noreplytimervalue");
			GetText(application, "Max call duration", "maxcalldurationvalue");
			GetText(application, "Charge band name", "chargebandnamevalue");
			GetText(application, "Payphone blocking enabled", "payphoneblockingenabledvalue");
			GetText(application, "Web access blocked", "webaccessblockedvalue");
			GetText(application, "Predestination number", "searchsan_predestinationnumbervalue");
			GetText(application, "CPS Free Format", "CPSvalue");
			GetText(application, "Ring To Number", "ringtonumbervalue");
			GetText(application, "Announcement to play", "searchsan_announcementtoplayvalue");
			ScrolltoElement(application, "viewpage_backbutton", xml);
			waitforPagetobeenable();
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/sansearchheader")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to SAN Search page");
			Log.info("Navigated to SAN Search page");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to SAN Search page");
			Log.info("Didn't navigate to SAN Search page");
		}

		//Verify Search order/service page
		edittextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		waitforPagetobeenable();
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		waitforPagetobeenable();
		WebElement SelectSAN1= getwebelement("//div[contains(text(),'"+search_sannumber+"')]/parent::div//span[contains(@class,'unchecked')]");
		try {
		if(SelectSAN1.isDisplayed())
		{	
			String OrderNumber= getwebelement(xml.getlocator("//locators/" + application + "/ordername_link")).getText();
			click_commonMethod(application, "Order Name", "ordername_link", xml);
			compareText(application, "Search For Order / Service Header", "searchfororder_header", "Search For Order / Service", xml);
			waitforPagetobeenable();
			WebElement SelectExistingOrder= getwebelement("//div[contains(text(),'"+OrderNumber+"')]/parent::div//input[@type='radio']");

			if(SelectExistingOrder.isDisplayed())
			{
				Clickon(SelectExistingOrder);
				click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
				waitforPagetobeenable();
				click_commonMethod(application, "View", "searchfororder_viewlink", xml);
				waitforPagetobeenable();
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to view service page");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
					Log.info("Didn't navigate to view service page");
				}

			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Existing order is not available");
				Log.info("Existing order is not available");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing SAN to display");
			Log.info(" No existing SAN to display");
		}
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Step : No existing SAN to display");
				e.printStackTrace();
		}
		driver.navigate().back();
		waitforPagetobeenable();

		//Verify manage link in search order page
		compareText(application, "SAN Search", "sansearchheader", "SAN Search", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/sansearchheader")).isDisplayed())
		{
			Log.info("Navigated to 'SAN Search' page");
			System.out.println("Navigated to 'SAN Search' page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to 'SAN Search' page");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to 'SAN Search' page");
			Log.info("Didn't navigate to 'SAN Search' page");
		}

		edittextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		waitforPagetobeenable();
		WebElement SelectSAN2= getwebelement("//div[contains(text(),'"+search_sannumber+"')]/parent::div//span[contains(@class,'unchecked')]");
		try {
		if(SelectSAN2.isDisplayed())
		{
			String OrderNumber= getwebelement(xml.getlocator("//locators/" + application + "/ordername_link")).getText();
			click_commonMethod(application, "Order Name", "ordername_link", xml);
			compareText(application, "Search For Order / Service Header", "searchfororder_header", "Search For Order / Service", xml);
			WebElement SelectExistingOrder= getwebelement("//div[contains(text(),'"+OrderNumber+"')]/parent::div//input[@type='radio']");
			if(SelectExistingOrder.isDisplayed())
			{
				Clickon(SelectExistingOrder);
				click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
				click_commonMethod(application, "Manage", "searchfororder_managelink", xml);
				waitforPagetobeenable();
				
				if(getwebelement(xml.getlocator("//locators/" + application + "/manageservice_header")).isDisplayed())
				{
					Log.info("Navigated to Manage service page");
					System.out.println("Navigated to Manage service page");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to Manage service page");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigated to Manage service page");
					Log.info("Didn't navigated to Manage service page");
				}

			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Existing order is not available");
				Log.info("Existing order is not available");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing SAN to display");
			Log.info("No existing SAN to display");
		}
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Step : No existing SAN to display");
				e.printStackTrace();
		}
		
		driver.navigate().back();
		waitforPagetobeenable();

		//Verify Delete SAN details
		edittextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		waitforPagetobeenable();
		WebElement SelectSAN3= getwebelement("//div[contains(text(),'"+search_sannumber+"')]/parent::div//span[contains(@class,'unchecked')]");
		try {
		if(SelectSAN3.isDisplayed())
		{
			Clickon(SelectSAN3);
			waitforPagetobeenable();
			click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Delete SAN", "delete", xml);
			waitforPagetobeenable();
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			if(DeleteAlertPopup.isDisplayed())
			{
				click_commonMethod(application, "Delete", "searchsan_deletebutton", xml);
				verifysuccessmessage(application, "SAN successfully deleted.");
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing SAN to display");
			Log.info("No existing SAN to display");
		}
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Step : No existing SAN to display");
				e.printStackTrace();
		}
		
	}

	
	public static Boolean isFileDownloaded(String fileName, String downloadspath) {
		boolean flag = false;
		//paste your directory path below
		//eg: C:\\Users\\username\\Downloads
		String dirPath = downloadspath; 
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		try {
		if (files.length == 0 || files == null) {
			System.out.println("The directory is empty");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Downloads folder is empty");
			flag = false;
		} else {
			for (File listFile : files) {
				if (listFile.getName().contains(fileName)) {
					System.out.println(fileName + " is present");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+fileName+"' excel file is downloaded successfully");
					break;
				}
				flag = true;
			}
		}
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Downloads folder path '"+dirPath+"' is not found");
		}
		return flag;
	}
	
	public String GetText(String application, String labelname, String xpath) throws InterruptedException, DocumentException {

		String text = null;
		WebElement element = null;

		try {
			waitforPagetobeenable();
			element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")); 
			String ele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' is not found");
			}
			else if (ele!=null && ele.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
			}
			else {   

				text = element.getText();
				ExtentTestManager.getTest().log(LogStatus.PASS,"Step: '"+ labelname +"' value is displayed as : '"+text+"'");

			}
		}catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: '"+ labelname +"' value is not displaying");
			e.printStackTrace();
		}
		return text;

	}
	
	public void verifysuccessmessage(String application, String expected) throws InterruptedException {
		
		scrollToTop();
		waitforPagetobeenable();
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
		}

	}
	
	public void cleartext(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
		WebElement element= null;
		try {
			waitforPagetobeenable();
			element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			String value= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			}
			else if(value!=null) {
				Thread.sleep(1000);
				element.clear();	
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
