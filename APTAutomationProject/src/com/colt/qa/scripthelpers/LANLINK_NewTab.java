package com.colt.qa.scripthelpers;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class LANLINK_NewTab extends DriverHelper{

	public LANLINK_NewTab(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	XMLReader xml = new XMLReader("src\\com\\colt\\qa\\pagerepository\\LANLINKnewTab.xml");
	
	
	public void searchOrderORservice(String application, String existingService) throws InterruptedException, DocumentException, IOException {
		
		Moveon(getwebelement(xml.getlocator("//locators/"+application+"/ManageCustomerServiceLink")));
		Thread.sleep(1000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Mouse hovered on 'Manage Customer Service' link");
		
		
		click_commonMethod(application, "SearchorderORservice", "searchorderORservicelink", xml);
		
		
		addtextFields_commonMethod(application, "Service", "searchService_serviceTextField", existingService, xml);
		scrolltoend();
		
		click_commonMethod(application, "Search", "searchButton" , xml);
		waitForpageload();   waitforPagetobeenable();
		
		WebElement selectTheSearchedService = getwebelement(xml.getlocator("//locators/"+application+"/selectSearchedService").replace("value", existingService));
		Clickon(selectTheSearchedService);
		
		click_commonMethod(application, "Action", "searchSerice_ActionDropdown", xml);
		click_commonMethod(application, "View", "searchService_viewLink", xml);
		Thread.sleep(2000);
		
		waitForpageload();   waitforPagetobeenable();
	}
	
	
	public void editService(String application, String customerName, String serviceIdentification, String singleEndPointCPE, String email,
			String phone, String remark, String performancereporting, String proactiveMonitoring, String deliveryChannel, String managementOrder,
			String notificationmanagement, String vpnTopology, String intermediateTechnology, String circuitReference) throws Exception {
		
		String expectedPageTitle = "Edit Order / Service";
		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/OrderPanel"));
		scrolltoview(orderPanel);
		Thread.sleep(2000);
		
	//click on Action dropdown	
		click_commonMethod(application, "Action", "ActionDropdown_InViewPage", xml);
		Thread.sleep(1000);
		
		
		//New tab Implementation for create Customer Page
		openLinkInNewTab(application, "editLink_InViewPage", "Edit",  xml);		//opens link in new tab
		Switchtotab();	
		
		waitForpageload();  waitforPagetobeenable();
		scrollToTop();
		
		String actualPageTitle=driver.getTitle();
		
		if(expectedPageTitle.equals(actualPageTitle)) {
			
		ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title is displaying as "+ actualPageTitle + " as expected");
			Log.info("Page Title is displaying as "+ actualPageTitle + " as expected");
			
		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate breadcrumbs");
			WebElement el1 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", "Home"));
			isDisplayed(application, el1 , "Home", xml);
			
			WebElement el2 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", customerName));
			isDisplayed(application, el2 , "Customer Name", xml);
			
			WebElement el3 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumb").replace("value", serviceIdentification));
			isDisplayed(application, el3, "Service Name", xml);
			
//			WebElement el4 = getwebelement(xml.getlocator("//locators/" + application + "/breadCrumbForcurrentPage").replace("value", "Edit Service"));
//			isDisplayed(application, el4, "Edit Service", xml);
			
			
			
		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate Panel Header");
			compareText(application, "Edit service", "editServicePage_pnaleHeader", "Edit Service", xml);	//compare Panel Header
			
			
		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate Functionalities in 'Edit Service' page");
		
			edittextFields_commonMethod(application, "Email", "emailField", email, xml);
			
			edittextFields_commonMethod(application, "Phone Contat", "phoneField", phone, xml);
			
			edittextFields_commonMethod(application, "Remark", "remarkField", remark, xml);
			
			scrolltoend();
			
			addDropdownValues_commonMethod(application, "Delivery Channel", "deliveryChannelField", deliveryChannel, xml);
			
			addDropdownValues_commonMethod(application, "Management Order", "managementOrder", managementOrder, xml);
			
			editcheckbox_commonMethod(application, proactiveMonitoring, "proactiveMonitoring" , "Proactive Monitoring", xml);
			if(proactiveMonitoring.equalsIgnoreCase("Yes")) {
				
				addDropdownValues_commonMethod(application, "Notification Management Team", "notificationManagement", notificationmanagement, xml);
			}
			
			editcheckbox_commonMethod(application, performancereporting, "performancereporting", "Performance Reporting", xml);
			
			
			//VPN topology	
			boolean vpnDisplayedValues=getwebelement("//div[contains(text(),'"+ vpnTopology +"')]").isDisplayed();
			if(vpnDisplayedValues) {
				ExtentTestManager.getTest().log(LogStatus.PASS, vpnTopology + " is displaying under 'VPN Topology' as expected");
			
			if(vpnTopology.equals("Point-to-Point")) {
				
			//Intermediate Technologies	
				edittextFields_commonMethod(application, "Intermediate Technologies", "intermediateTechnology", intermediateTechnology, xml);
			
			//Circuit reference
				edittextFields_commonMethod(application, "Circuit Reference", "circuitReferenceTextField", circuitReference, xml);
				
			}
			
			else if(vpnTopology.equals("Hub&Spoke") || vpnTopology.equals("E-PN (Any-to-Any)")) {

			//Circuit Reference
				edittextFields_commonMethod(application, "Circuit Reference", "circuitReferenceTextField", circuitReference, xml);
				
			}
			
			scrolltoend();
			
			click_commonMethod(application, "OK", "okButton", xml);
			
			waitForpageload();  waitforPagetobeenable();
			verifysuccessmessage(application, "Service successfully updated");
			
			CloseProposalwindow();
			
		}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Page Title mismatches. It is displaying as: " + actualPageTitle + " ."
					+ "The Expected title is: " + expectedPageTitle);
			Log.info("Page Title mismatches. It is displaying as: " + actualPageTitle + " ."
					+ "The Expected title is: " + expectedPageTitle);
		}
	}
	
	
public void verifysuccessmessage(String application, String expected) throws InterruptedException {
		
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(3000);
		try {	
			
			boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert")).isDisplayed();
			if(successMsg) {
				String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
				if(expected.contains(alrtmsg)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
					Log.info("Message is verified. It is displaying as: "+alrtmsg);
					successScreenshot(application);
				}else if(expected.equals(alrtmsg)){
					ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
					Log.info("Message is verified. It is displaying as: "+alrtmsg);
					successScreenshot(application);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
					Log.info("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
				Log.info(" Success Message is not displaying");
			}
			Thread.sleep(2000);
			
		}catch(Exception e) {
			Log.info("failure in fetching success message");
			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
			Log.info(expected+ " message is not getting dislpayed");
			Thread.sleep(2000);
		}

	}

	

}
