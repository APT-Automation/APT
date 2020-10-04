package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;


public class APT_wholeSaleHelper extends DriverHelper {

	public APT_wholeSaleHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	WebElement el;
	
	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_wholeSaleRepository.xml");
	
	
//	@Override
//	public void xmlRr(XMLReader xml) {
//		// TODO Auto-generated method stub
//		Log.info("xml value displaying as: "+xml);
//		super.xmlRr(xml);
//	}
	
	public String primarytrunkGroupname=null;

	public void webelementpresencelogger(WebElement ele, String msg) {

		boolean flag = ele.isDisplayed();
		Log.info("element presence state : " + flag);
		if (flag) {

			Log.info("webElement is present " + ele.getText());
			ExtentTestManager.getTest().log(LogStatus.PASS, msg);
		} else {

			Log.info("webElement is not  present" + ele.getText());
			ExtentTestManager.getTest().log(LogStatus.FAIL, msg);
		}

	}

	public void CreateCustomer(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws Exception {


		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying create New Customer Functionality");
		
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);
		Log.info("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "create customer link", "createcustomerlink", xml);
		Thread.sleep(2000);
		compareText(application, "create customer page header", "createcustomer_header", "Create Customer", xml);
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);
		//Warning msg check
		WarningMessage(application, "customernamewarngmsg", "Customer Name");
		WarningMessage(application, "countrywarngmsg", "Country");
		WarningMessage(application, "ocnwarngmsg", "OCN");
		WarningMessage(application, "typewarngmsg", "Type");
		WarningMessage(application, "emailwarngmsg", "Email");

		//Clear customer info
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		EnterTextValue(application, email, "Email", "emailtextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		scrolltoend();
		click_commonMethod(application, "Reset", "clearbutton", xml);
		ExtentTestManager.getTest().log(LogStatus.PASS, "All text field values are cleared");
		Log.info("All text fields are cleared");

		//Create customer by providing all info
		cleartext(application, "Customer Name", "nametextfield");
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		cleartext(application, "Main Domain", "maindomaintextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		addDropdownValues(application, "Country", "country", country);
		cleartext(application, "OCN", "ocntextfield");
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		cleartext(application, "Reference", "referencetextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		cleartext(application, "Technical Contact Name", "technicalcontactnametextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		addDropdownValues(application, "Type", "typedropdown", type);
		cleartext(application, "Email", "emailtextfield");
		EnterTextValue(application, email, "Email", "emailtextfield");
		cleartext(application, "Phone", "phonetextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		cleartext(application, "Fax", "faxtextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);
		compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
		sa.assertAll();	
	}


	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Select Existing Customer Functionality");

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		Log.info("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "Create Order/Service Link", "CreateOrderServiceLink", xml);	
		Log.info("=== Create Order/Service navigated ===");

		//click_commonMethod on Next button to check mandatory messages	
		click_commonMethod(application, "Next", "nextbutton", xml);

		//Customer Error message	
		warningMessage_commonMethod(application, "customer_createorderpage_warngmsg", "Choose a customer", xml);

		//Entering Customer name
		addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected, xml);
		
		waitforPagetobeenable();
		Thread.sleep(3000);
		EnterTextValue(application, "*", "Customer Name", "entercustomernamefield");	

		//Select Customer from dropdown
		
		Thread.sleep(3000);
		addDropdownValues_commonMethod(application, "Choose a customer", "choosecustomerdropdown", ChooseCustomerToBeSelected, xml);
		click_commonMethod(application, "Next", "nextbutton", xml);

	}

public static String newordernumber, newVoiceLineNumber, SelectOrderNumber;
	
	public void createorderservice(String application, String neworder, String neworderno, String newrfireqno, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		scrolltoend();
		
		click_commonMethod(application, "Next", "createOrder_NextButton", xml);
		Thread.sleep(1000);
		
		warningMessage_commonMethod(application, "createOrderService_warningMessage" , "Order/Contract Number(Parent SID)", xml);
		
		warningMessage_commonMethod(application, "serviceType_warningMessage", "Service Type", xml);
		Thread.sleep(1000);
		
		if (neworder.equalsIgnoreCase("YES")) {

			scrolltoend();
			Thread.sleep(1000);
			
			EnterTextValue(application, neworderno, "Order/Contract Number", "newordertextfield");
			EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");
			click_commonMethod(application, "create order", "createorderbutton", xml);	
			
			verifysuccessmessage(application, "Order created successfully");

		} 
		
		else if (existingorderservice.equalsIgnoreCase("YES")) {
			
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addDropdownValues(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

		} else {

			Log.info("Order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step :Order not selected");
		}
	}

	
	public void serviceSelection(String application, String serviceToBeSelected) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "select Service type");
		
		scrolltoend();
		Thread.sleep(1000);
		addDropdownValues_commonMethod(application, "Service Type", "servicetypeDropdown", serviceToBeSelected, xml);
		scrolltoend();
		Thread.sleep(2000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Nextbutton")));
		Thread.sleep(2000);
		
	}
	
	public void serviceCreation(String application, String serviceidentification, String ImproperEmailID, String properMailId, String Phone,
			String remark, String PerformanceReporting, String serviceType) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(1000);
		
		//verify mandatory warning messages
		click_commonMethod(application, "OK", "OKbutton", xml);
		Thread.sleep(2000);
		
		scrollToTop();
		Thread.sleep(1000);
	
	//Service Identification warning message	
		warningMessage_commonMethod(application, "serviceIdentificationWarningmsg", "Service Identification", xml);
		
		
	//Enter value in all fields
		//Service Identificaton
		addtextFields_commonMethod(application, "Service Identification", "secivceIdentification_textField", serviceidentification, xml);
		
		//Service Type
			compareText_InViewPage(application, "Service Type", serviceType, xml);
		
		//Remark
				addtextFields_commonMethod(application, "Remark", "remark_textField", remark, xml);
		
		WebElement serviceId=getwebelement(xml.getlocator("//locators/" + application + "/Email_addButton"));
		scrolltoview(serviceId);
		Thread.sleep(2000);
		
		//Email
		Thread.sleep(4000);
		addtextFields_commonMethod(application, "Email", "Email_textfield", ImproperEmailID, xml);
		
		
	//checking error message for email field	
		Email_improperFormat(application, "ImproperEmail_errValidationmsg", "Email", xml);
		
		//Email
		getwebelement(xml.getlocator("//locators/" + application + "/Email_textfield")).clear();
		Thread.sleep(1000);
		addtextFields_commonMethod(application, "Email", "Email_textfield", properMailId, xml);
		
		click_commonMethod(application, ">>", "Email_addButton", xml);
		Thread.sleep(1000);
		
		//phone contact
		addtextFields_commonMethod(application, "Phone Contact", "phoneContact_TextField", Phone, xml);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/phoneContact_addButton")));
		Thread.sleep(2000);
		
		
		
		scrolltoend();
		Thread.sleep(1000);
		//Performance Reporting
		addCheckbox_commonMethod(application, "performanceReporting_checkbox", "Performance Reporting", PerformanceReporting, "no", xml);
		
		
		scrolltoend();
		
		//Click on "OK" button
		click_commonMethod(application, "OK", "OKbutton", xml);
	}

	
	public void Email_improperFormat(String application, String xpath, String fieldlabelName, XMLReader xml) throws InterruptedException, DocumentException {
	 	boolean message=false;
	 	//Field Error Message
	 			try {
	 				message = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath  +"")).isDisplayed();
	 				Thread.sleep(3000);
	 			sa.assertTrue(message, fieldlabelName + " field error message is not displayed ");
	 			if(message) {
	 			String ErrMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath  +"")).getText();
	 			
	 			Log.info( fieldlabelName + " field warning  message displayed as : " + ErrMsg + " when we enter value in wrong format");
	 			ExtentTestManager.getTest().log(LogStatus.PASS, "Step :  validation message for"+ fieldlabelName +"  field displayed as : " + ErrMsg + " when we enter value in wrong format");
	 			Log.info(fieldlabelName + " field warning  message displayed as : " + ErrMsg+ " when we enter value in wrong format");
	 			}else{
	 				ExtentTestManager.getTest().log(LogStatus.FAIL, "validation message for"+ fieldlabelName +"  field is not displaying");
	 				Log.info("validation message for"+ fieldlabelName +"  field is not displaying");
	 			}
	 			}catch(NoSuchElementException e) {
	 				e.printStackTrace();
	 				Log.info( "No warning message displayed for "+ fieldlabelName +" when we enter value in wrong format");
	 				ExtentTestManager.getTest().log(LogStatus.FAIL, "No warning message displayed for "+ fieldlabelName + " when we enter value in wrong format");
	 			}catch(Exception ed) {
	 				ed.printStackTrace();
	 				Log.info( "No warning message displayed for "+ fieldlabelName);
	 				ExtentTestManager.getTest().log(LogStatus.FAIL, "No warning message displayed for "+ fieldlabelName);
	 			}
	 }

	

		public void verifysuccessmessage(String application, String expected) throws InterruptedException {
			waitForpageload();
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(3000);
			try {	
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMsg")).isDisplayed();

				if(successMsg) {
					
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
					
					if(expected.contains(alrtmsg)) {
						
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
				
			}catch(Exception e) {
				Log.info("failure in fetching success message ");
				ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
				Log.info(expected+ " message is not getting dislpayed");
			}

		}



public void verifysuccessmessageforEditService(String application) throws InterruptedException {
			
			scrollToTop();
			Thread.sleep(3000);
			try {	
				
				String expected= "Service updated successfully";
				
				boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMsg")).isDisplayed();
				
				if(successMsg) {
					
					String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
					
					if(alrtmsg.equals(expected)) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS," 'service successfully created' message is verified. It is displaying as: "+alrtmsg);
						Log.info(" 'service successfully created' message is verified. It is displaying as: "+alrtmsg);
						
						successScreenshot(application);
						
					}else {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Service creation message is displaying but the success message display as: "+ alrtmsg);
						Log.info("Service creation message is displaying and the message gets mismatches. It is displaying as: "+ alrtmsg);
					}
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service updated successfully' message is not displaying after creating service");
				}
				
			}catch(Exception e) {
				Log.info("failure in fetching success message - 'Service updated successfully'  ");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service updated successfully' message is not displaying after editing the service");
				Log.info("Success message for edit Service is not getting dislpayed");
			}

		}

	
		public void verifyEnteredValuesForServiceCreation(String application, String serviceidentification, String ImproperEmailID, String properMailId, String Phone,
				String remark, String PerformanceReporting, String serviceType) throws InterruptedException, DocumentException, IOException { 
			
		
			WebElement servicePanel=getwebelement("//div[@class='heading-green-row row']//div[text()='Service']");
			scrolltoview(servicePanel);
			Thread.sleep(3000);
			
		//Service Identification
			compareText_InViewPage(application, "Service Identification", serviceidentification, xml);
		
		//Service Type	
			//Service Type
			compareText_InViewPage(application, "Service Type", serviceType, xml);
			
		//Email	
			compareText_InViewPageForEmail(application, "Email",  properMailId, xml);
			
		//Phone Contact	
			compareText_InViewPage(application, "Phone Contact", Phone,  xml);
			
		//Remark	
			compareText(application, "Remark", "viewPage_RemarkField", remark, xml);
			
	//under Management Options panel
		//performance reporting	
			compareText_InViewPage(application, "Performance Reporting", PerformanceReporting, xml);
		
		}
		
		
		public void verifyEnteredValuesForServiceUpdation(String application, String serviceidentification, String ImproperEmailID, String properMailId, String Phone,
				String remark, String PerformanceReporting,String editedServiceId, String editedRemark, String editedEmail, String editedphone, 
				String editedPerformanceReport, String serviceType) throws InterruptedException, DocumentException, IOException { 
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Updated values");
		
			WebElement servicePanel=getwebelement("//div[@class='heading-green-row row']//div[text()='Service']");
			scrolltoview(servicePanel);
			Thread.sleep(3000);
			
		//Service Identification
		if(editedServiceId.equalsIgnoreCase("null")) {
			
			compareText_InViewPage(application, "Service Identification", serviceidentification, xml);
		}else {
			compareText_InViewPage(application, "Service Identification", editedServiceId, xml);
		}
			
		
		//Service Type	
			compareText_InViewPage(application, "Service Type",  "Wholesale SIP Trunking", xml);
			
		//Email	
			compareText_InViewPageForEmail(application, "Email",  properMailId, xml);
			
		//Phone Contact	
			compareText_InViewPage(application, "Phone Contact", Phone,  xml);
			
		//Remark	
		if(editedRemark.equalsIgnoreCase("Null")) {
			compareText(application, "Remark", "viewPage_RemarkField", remark, xml);
		}else {
			compareText(application, "Remark", "viewPage_RemarkField", editedRemark, xml);
		}
			
			
	//under Management Options panel
		//performance reporting	
		if(editedPerformanceReport.equalsIgnoreCase("Null")) {
			compareText_InViewPage(application, "Performance Reporting", PerformanceReporting, xml);
		}else {
			compareText_InViewPage(application, "Performance Reporting", editedPerformanceReport, xml);
		}
			
		
		}
		
		@SuppressWarnings("unused")
		public void compareText_InViewPageForEmail(String application, String labelname,  String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

			String text = null;
			WebElement element = null;

			try {
				Thread.sleep(1000);
				element = getwebelement("(//div[div[label[contains(text(),'"+ labelname +"')]]]/div[2])[2]");
				String emptyele = element.getText().toString();

				if(element==null)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
					Log.info(labelname+" not found");
				}
				else if (emptyele!=null && emptyele.isEmpty()) {
//					ExtentTestManager.getTest().log(LogStatus.PASS,  labelname + "' value is empty");
					
					emptyele= "Null";
					
					sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
					
					if(emptyele.equalsIgnoreCase(ExpectedText)) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
						Log.info(" The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL,"The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
						Log.info(" The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
					}
				}else 
				{   
					text = element.getText();
					if(text.equals(ExpectedText)) {
						ExtentTestManager.getTest().log(LogStatus.PASS," The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					}
					else if(text.contains(ExpectedText)) {
						ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
					}
				}
			}catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
				e.printStackTrace();
			}
		}


	public void addDropdownValues(String application, String fieldname, String xpath, String expectedValueToAdd) throws InterruptedException, DocumentException {
		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, fieldname + " dropdown is displaying as expected");
				Log.info(fieldname + " dropdown is displaying as expected");

				if(expectedValueToAdd.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ fieldname + " dropdown");
					Log.info(" No values selected under "+ fieldname + " dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='"+ fieldname +"']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

					ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ fieldname + " dropdown is:  ");
					Log.info( " List of values inside "+ fieldname + "dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {

						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						Log.info(" " + valuetypes.getText());

					}

					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='"+ expectedValueToAdd +"']"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//div[label[text()='"+ fieldname +"']]//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, fieldname + " dropdown value selected as: "+ actualValue );
					Log.info( fieldname + " dropdown value selected as: "+ actualValue);

				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, fieldname + " is not displaying");
				Log.info(fieldname + " is not displaying");
			}
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, fieldname + " is not displaying");
			Log.info(fieldname + " is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NO value selected under "+ fieldname + " dropdown");
			Log.info(" NO value selected under "+ fieldname + " dropdown");
		}

	}

	public void EnterTextValue(String application, String value, String labelname, String xpath) {
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' text field not found");
				Log.info("Step: '"+labelname+"' text field not found");
			}
			else 
			{
				if(value.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered in "+labelname + " text field");
				}else {
					element.sendKeys(value);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Entered '"+value+"' into '"+labelname+"' text field");
				}
				
			}

		}catch(NoSuchElementException ep) {
			ep.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			Log.info(labelname + " field is not displaying");
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+value+"' into '"+labelname+"' text field");
			e.printStackTrace();
		}

	}

	

	
	public void WarningMessage(String application, String xpath, String labelname) throws InterruptedException, DocumentException {
		WebElement element= null;
		try {
			Thread.sleep(1000);
			element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			Thread.sleep(2000);
			if(element==null) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			}
			else
			{
				String WarningMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getText();

				Log.info("'"+labelname+"' field warning  message displayed as : " + WarningMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : validation message for '"+labelname+"' text field displayed as : " + WarningMsg);
				Log.info("'"+labelname+"' field warning message displayed as : " + WarningMsg);
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			Log.info("'"+labelname+"' field warning message is not dipslaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' field warning message is not displaying");
		}catch(Exception ed) {
			ed.printStackTrace();
		}
	}

	public void cleartext(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
		WebElement element= null;
		try {
			Thread.sleep(1000);
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

	public void isDisplayed(String application, String xpath, String labelname) {
		boolean availability = false;

		try {
			Thread.sleep(1000);
			availability= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
//			Log.info(availability);
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

	public String Checkboxvalue(String application, String xpath) throws InterruptedException, DocumentException {

		String value= null;
		value= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
		if(value!=null)
		{
			return "Yes";
		}
		else
			return "No";
	}


	public String GetText(String application, String labelname, String xpath) throws InterruptedException, DocumentException {

		String text = null;
		WebElement element = null;

		try {
			Thread.sleep(1000);
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

	
	public void navigateToEditPage(String application) throws InterruptedException, DocumentException {
		
		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/OrderPanel"));
		scrolltoview(orderPanel);
		Thread.sleep(2000);
		
	//click on Action dropdown	
		click_commonMethod(application, "Action", "ActionDropdown_InViewPage", xml);
		Thread.sleep(1000);
		
	//click on edit link
		click_commonMethod(application, "edit", "editService_Link", xml);
		Thread.sleep(1000);
		
	}
	
	public void editService(String application, String ServiceId, String Remark, String Email, String phone, 
			String PerformanceReport, String serviceType) throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit Service' Functionality");
		boolean editServicePage=false;
		
		scrollToTop();
		Thread.sleep(2000);
		
		editServicePage=getwebelement(xml.getlocator("//locators/" + application + "/editSerivcePage_pnaleHeader")).isDisplayed();
		if(editServicePage) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Edit Service' page is displaying");
			Log.info(" 'Edit Service' page is displaying");
			
		//service identification
			edittextFields_commonMethod(application,"Service Identification" , "secivceIdentification_textField", ServiceId, xml);
		
		//Service Type
			compareText_InViewPage(application, "Service Type", serviceType, xml);
			
		//Remark	
			edittextFields_commonMethod(application, "Remark", "remark_textField", Remark, xml);
			
		
	scrolltoend();
	Thread.sleep(2000);
	
		//Performance Reporting
			editcheckbox_commonMethod(application, PerformanceReport, "performanceReporting_checkbox", "Performance Reporting", xml);
			
		scrolltoend();
		//Click on "OK" button
			click_commonMethod(application, "OK", "OKbutton", xml);	
			
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Edit Service' page is not displaying");
			Log.info(" 'Edit Service' page is not displaying");
		}
		
	}
	
	
	public void addTrunkSiteOrder(String application, String trunkGroupOrder, String trunkgroupOrderNumber) throws InterruptedException, DocumentException, IOException { 
		
		boolean trunkPanel=false;
		boolean siteOrderpage=false;
		boolean trunkgrupOrderErrMsg= false;
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add Trunk Site Order' Functionality");
		
		scrolltoend();
		Thread.sleep(2000);
		
		trunkPanel= getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel")).isDisplayed();
		if(trunkPanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");
			Log.info("'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");
			
			
		//Perform Add Site order
			click_commonMethod(application, "Add TrunkGroup/SiteOrder link", "addTrunkSiteOrderlink", xml);
			
			siteOrderpage= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
			if(siteOrderpage) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is displaying as expected");
				Log.info("'Add Trunk Group/Site Order' page is displaying as expected");
				
			//verify mandatory Warning Message
				click_commonMethod(application, "OK", "trunk_okButton", xml);
				
				warningMessage_commonMethod(application, "trunkGrouporder_warningmsg", "Trunk Group Order", xml);
				
				warningMessage_commonMethod(application, "trunkGroupOrderName_warningmsg", "Trunk group Order Number", xml);
				
			//Add trunkgroup Order checkbox
				if(trunkGroupOrder.equalsIgnoreCase("yes")) {
					addCheckbox_commonMethod(application, "trunkGroupOrder_checkbox", "Trunk Group Order", trunkGroupOrder, "No", xml);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " ' Trunk Group order' checkbox is a mandatory field. No values passed");
					Log.info(" ' Trunk Group order' checkbox is a mandatory field. No values passed");

				}
				
				
			//Add Trunk Group order Number text field
				addtextFields_commonMethod(application, "Trunk Group Order Number", "trunkGroupOrderName_textField", trunkgroupOrderNumber, xml);
				
				
			//Click on OK button
				click_commonMethod(application, "OK", "trunk_okButton", xml);
				Thread.sleep(3000);
				
				try {
				trunkgrupOrderErrMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).isDisplayed();
				String actualMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).getText();
				if(trunkgrupOrderErrMsg) {
					if(actualMsg.contains("1.trunkgroup number already exists")) {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
						Log.info(" Error message we are getting as: "+ actualMsg);
					}
					else if(actualMsg.equalsIgnoreCase("Trunk Group created successfully")) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as 'Trunk Group created successfully'");
						Log.info(" Success Message displays as 'Trunk Group created successfully'");
					}
					
				}
				}catch(Exception e) {
					e.printStackTrace();
					
				}
				
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is not displaying");
				Log.info("'Add Trunk Group/Site Order' page is not displaying");
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
			Log.info("'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
		}
	}
	
	
	public void editSiteOrder(String application, String siteOrderName,  String trunkGroupOrder, String trunkGrouporderNumber) throws InterruptedException, DocumentException, IOException {
		
		boolean siteOrderpage=false;
		boolean trunkgrupOrderErrMsg=false;
		
		scrolltoend();
		Thread.sleep(3000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'edit trunk Site Order' Functionality");
		
		WebElement editlink=getwebelement(xml.getlocator("//locators/" + application + "/editSiteOrderLink").replace("value", siteOrderName));
				
		Clickon(editlink);
		
		siteOrderpage= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
		if(siteOrderpage) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is displaying as expected");
			Log.info("'Edit Trunk Group/Site Order' page is displaying as expected");
			
		//Trunk group Order
			if(trunkGroupOrder.equalsIgnoreCase("no")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Trunk Group Order' is a mandatory field. It cannot be unselected");
				Log.info("'Trunk Group Order' is a mandatory field. It cannot be unselected");
			}else {
				editcheckbox_commonMethod(application, trunkGroupOrder, "trunkGroupOrder_checkbox", "Trunk Group Order", xml);
			}
			
			
		//Trunk Group Order Number
			edittextFields_commonMethod(application, "Trunk Group Order Number", "trunkGroupOrderName_textField", trunkGrouporderNumber, xml);
			
			
		//Click on OK button
			click_commonMethod(application, "OK", "trunk_okButton", xml);
			Thread.sleep(3000);
			
			try {
			trunkgrupOrderErrMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).isDisplayed();
			String actualMsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSg_siteorderCreation")).getText();
			if(trunkgrupOrderErrMsg) {
				if(actualMsg.contains("1.trunkgroup number already exists")) {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
					Log.info(" Error message we are getting as: "+ actualMsg);
				}
				else if(actualMsg.contains("Trunk Group successfully updated")) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as 'Trunk Group successfully updated.'");
					Log.info(" Success Message displays as 'Trunk Group successfully updated.'");
				}
				
			}
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
			
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is not displaying");
			Log.info("'Edit Trunk Group/Site Order' page is displaying");
		}
	}
	
	public void verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel(String application, String siteOrderName) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(1000);
		
		boolean siteOrderUnderTrunkPanel=false;
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Added Site Order'");
		
		siteOrderUnderTrunkPanel=getwebelement("//span[text()='"+ siteOrderName +"']").isDisplayed();
		
		if(siteOrderUnderTrunkPanel) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, siteOrderName + " 'Site Order' is displaying under 'Trunk' panel");
			Log.info(siteOrderName + " 'Site Order' is displaying under 'Trunk' panel");
			
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add Trunk' Functionality");
			
		//Click on Add trunk link	
			String addTunklinkXpath="//div[div[span[text()='"+ siteOrderName +"']]]/following-sibling::div//span[text()='Add Trunk']";
			click_commonMethod_PassingWebelementDirectly(application, "Add Trunk", addTunklinkXpath, xml);
			Thread.sleep(1000);
			
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, siteOrderName + " 'Site Order' is not displaying under 'Trunk' panel");
			Log.info(siteOrderName + " 'Site Order' is not displaying under 'Trunk' panel");
		}
	}

	
	public void addTrunk(String application, String customerName, String servicename, String trunkType, String voipProtocol, String country, String CDRdelivery, String gateway, String quality,
			String trafficDirection, String ipAddresstype, String SIPsignallingPort, String internetBasedCustomer, String vlanTag, String subInterfaceSlot,
			String signallngZone, String signallingtransportProtocol, String coltSignalingIP, String mediaIP, String reuseNIFgroup, String reuseSigZonePart,
			String callAdmissionControl, String callrateLimit, String sourceAddressFiltering, String relSupport, String sipSessionkeepAliveTimer,
			String retryInvite, String routePriority, String addressReachability, String carrierIPoriginating, String carrierIPterminating, String TLSfield,
			String srtp, String prefix, String globalProfile_ExistingSelection, String globalProfile_newSelection, String globalProfile_ExistingValue, String globalProfile_newValue,
			String localProfile_existingFieldSelection, String localProfile_newFieldSelection, String localProfile_existingvalue, String localProfile_newvalue,
			String COSprofile_existingFieldSelection, String COSprofile_newFieldSelection, String COSprofile_existingValue, String COSprofile_newValue,
			String PSPGname_existingFieldSelection, String PSPGname_newFieldSelection,String pspgName_existingValue, String pspgName_newValue,
			String prefferedPSP_existingFieldSelection, String prefferedPSP_newFieldSelection,String preferredPSP_exitingvalue, String preferredPSP_newvalue,
			String carrier_existingFieldSelection, String carrier_newFieldSelection, String carrier_existingValue, String carrier_newValue,
			String IPsignallingProfile_existingFieldSelection, String IPsignallingProfile_newFieldSelection, String IPsignallingProfile_existingValue, String IPsignallingProfile_newValue,
			String EgressIpsignal_existingFieldSelection,String EgressIpsignal_newFieldSelection, String EgressIPsignal_existingValue, String EgressIPsignal_newValue,
			String InDMPMrule_existingFieldSelection, String InDMPMrule_newFieldSelection, String InDMPMrule_existingValue, String InDMPMrule_newValue,
			String OutDMPMrule_existingFieldSelection, String OutDMPMrule_newFieldSelection, String OutDMPMrule_existingValue, String OutDMPMrule_newValue,
			String featureControlprofile_existingFieldSelection,String featureControlprofile_newFieldSelection, String featureControlprofile_existingValue, String featureControlprofile_newValue,
			String localRingBackTone_existingFieldSelection, String localRingBackTone_newFieldSelection, String localRingBackTone_existingValue, String localRingBackTone_newValue,
			String createLowerCaseRoutervalue,String PSXmanualConfigvalue, String GSXmanualConfigvalue, String callLimit, String limitNumber, String callrateLimiteValue, String SBCmanualconfigValue) throws IOException, InterruptedException, DocumentException {      
		
		String gatewayCode=null;
		String subInterfacename_starting="SIF-1-";
		String subInterfacename_middle="-2-";
		String NIFgroupDEfaultValue_starting="SIF-1-";
		String NIFgroupDEfaultValue_middle="-2-";
		String NIGgroupdefaultValue_last="-OUTSIDE";
		String ipInterfaceDEfaultValue="EXTERNAL_IPIF_";
		String addressContextDefaultValue="EXTERNAL_AC_";
		String ipInterfaceGroupDefaultvalue="EXTERNAL_IPIG_";
		String signallingZoneDefaultValue="OUT-UNTRUSTED";
		
		
		String SubInterfaceName=null;
		String NIFgroup=null;
		String ipInterface=null;
		String addressContext=null;
		String ipInterfaceGroup=null;
		String prefix_code=null;
		
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(1000);
		
		scrolltoend();
		Thread.sleep(2000);
		
		click_commonMethod(application, "OK", "OKbutton", xml);
		
		scrollToTop();
		Thread.sleep(2000);
		
		
	//Trunk Type	
		selectValueInsideDropdown(application, "trunkType_Dropdown", "Trunk Type", trunkType, xml);
		String trunktype_code=trunkType_code(application, trunkType);
		
		
	//Trunk Group Description
		String expectedValue1=customerName+"_"+servicename+"_"+trunkType;
		compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

		
	//VOIP Protocol
		selectValueInsideDropdown(application, "voipProtocol_Dropdown", "VOIP Protocol", voipProtocol, xml);
		String voip_code=voipProtocol_code(application, voipProtocol);
		
	//Billing COuntry
		warningMessage_commonMethod(application, "country_warningMessage", "Billing Country", xml);   //validate warning message
		selectValueInsideDropdown(application, "billingCoutry_Dropdown", "Billing Country", country, xml);
		
		String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
		Log.info("country dropdon value is: "+country_code);
		
	//CDR Delivery
		selectValueInsideDropdown(application, "CDRdelivery_Dropdown", "CDR Delivery", CDRdelivery, xml);
		String CDR_code=CDRdelivery_code(application, CDRdelivery);
		
	//Prefix
		warningMessage_commonMethod(application, "prefix_warningMessage", "Prefix", xml);  //validate warning messsage
		
		//click on "Allocate Prefix" button
		 click_commonMethod(application, "Allocate Prefix", "allocatePrefix_button", xml);
		 
		 String prefix_actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/prefix_textField")).getAttribute("value");
		 if(prefix_actualvalue.isEmpty()) {
			 
			 addtextFields_commonMethod(application, "Prefix", "prefix_textField", prefix, xml);
			 
		 }else {
			 ExtentTestManager.getTest().log(LogStatus.PASS, "When we click on 'Allocate Prefix' button, Under 'Prefix' value is displaying as: "+prefix_actualvalue);
			 Log.info("When we click on 'Allocate Prefix' button, Under 'Prefix' value is displaying as: "+prefix_actualvalue);
		 }

	//valdate the prefix value for adding into Trunk group Name field	 
		 String preifxValueInsidetextField=getwebelement(xml.getlocator("//locators/" + application + "/prefix_textField")).getAttribute("value");
		 
		 int prefixSize=preifxValueInsidetextField.length(); 
		if(prefixSize==4) {
			prefix_code=preifxValueInsidetextField.substring(1);
		}else if(prefixSize<4) {
			Log.info("Prefix value cannot be less than 4");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Prefix value cannot be less than 4. Value is displaying as "+preifxValueInsidetextField);
		}else if(prefixSize>4) {
			prefix_code=preifxValueInsidetextField.substring(1);
			Log.info("Prefix value cannot be greater than 4");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Prefix value cannot be greater than 4. Value is displaying as "+preifxValueInsidetextField);
		}
		 
		 if((country.equals("NL (Netherlands)"))) {
			 prefix_code = "B" + prefix_code.substring(1);
		 }else {
			Log.info("No changes made in refix code"); 
		 }

		 
	//Gateway
		selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", gateway, xml);
		gatewayCode=gateway_code(application, gateway);
		
		WebElement trunktype=getwebelement(xml.getlocator("//locators/" + application + "/trunkType_Dropdown"));
		scrolltoview(trunktype);
		Thread.sleep(2000);
		
	//Quality
		selectValueInsideDropdown(application, "quality_Dropdown", "Quality", quality, xml);
		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");
		
	//Trunk Group Name
		String trunGroup=country_code+gatewayCode+voip_code+trunktype_code+CDR_code+prefix_code+"0"+quality_code;
		
		int totalLen=trunGroup.length();
		Log.info("Total lenth of 'Trunk Group' field is "+ totalLen);
		Log.info("----------------Trunk group name is "+ trunGroup);
		if(totalLen==13) {
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
			primarytrunkGroupname=trunGroup;
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Trunk Group NAme' length is: "+totalLen);
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
		}
		
	//traffic Direction
		selectValueInsideDropdown(application, "trafficDirection_Dropdown", "Traffic Directions", trafficDirection, xml);
		
	//IP Address Type
		selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", ipAddresstype, xml);
	
		WebElement traficDirection=getwebelement(xml.getlocator("//locators/" + application + "/trafficDirection_Dropdown"));
		scrolltoview(traficDirection);
		Thread.sleep(2000);
		
		
	//Carrier IP originating
		warningMessage_commonMethod(application, "carrierIPoriginating_warningMessage", "Carrier IP Originating (Address/Mask)", xml);
		
		addtextFields_commonMethod(application, "Carrier IP Originating (Address/Mask)", "carrierIPoriginating_textField", carrierIPoriginating, xml);
		click_commonMethod(application, ">>", "carrierIPoriginating_addButtton", xml);
		GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPOriginating_addedValue_selectDropdownField")), "Carrier IP Originating (Address/Mask)");
		
		
	//Carrier IP Terminating
		warningMessage_commonMethod(application, "carrerIPterminating_warningMessage", "Carrier IP Terminating((Address)", xml);
		
		addtextFields_commonMethod(application, "Carrier IP Terminating(Address)", "carrierIPterminating_textField", carrierIPterminating, xml);
		click_commonMethod(application, ">>", "carrierIPterminating_addButton", xml);
		GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPterminating_addedValue_selectDropdownField")), "Carrier IP Terminating (Address)");
		
		
	//SIP Signalling Port
	  if(voipProtocol.equalsIgnoreCase("SIP")) {
		  addtextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", SIPsignallingPort, xml);
		  
		  //message displaying under "SIP Signalling Port" text field	
			methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port:5060");
			
	  }else {
		  ExtentTestManager.getTest().log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
		  Log.info("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
	  }
		
	
	//Splitting the Gateway functionality into 2  
	  if(!gateway.contains("SBC")) {
		
		  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
			  
			  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
			  
			//Internet Based Customer
				addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
				
			  
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				 
				  ExtentTestManager.getTest().log(LogStatus.PASS, "When 'Internet Based Customer' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
				  if(vlanTag.equalsIgnoreCase("null")) {
						
						//Sub Interface slot
						selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
						
						if(subInterfaceSlot.equalsIgnoreCase("null")) {
							
							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanDefaultvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
							
							//NIF Group  
							NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanDefaultvalue+NIGgroupdefaultValue_last;
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
							
							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanDefaultvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
							
							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanDefaultvalue+NIGgroupdefaultValue_last;
							Log.info("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						
					}else {
						
						//VLAN Tag
//						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);
						
						String vlanDefaultvalue_afterVlanediting=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					//Sub Interface slot
						selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
						
						if(subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanDefaultvalue_afterVlanediting;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
							
							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanDefaultvalue_afterVlanediting+NIGgroupdefaultValue_last;
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
							
							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanDefaultvalue_afterVlanediting;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
							
							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanDefaultvalue_afterVlanediting+NIGgroupdefaultValue_last;
							Log.info("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
					}
			  }
		  }
		 //Internet Based Customer checkbox not selected 
		  else {
			if(vlanTag.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' field is  a mandatory field and no values are passed as an input");
				
				//Sub Interface slot
				selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
				
				if(subInterfaceSlot.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					SubInterfaceName=subInterfacename_starting+subInterfacename_middle;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+NIGgroupdefaultValue_last;
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
				else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+NIGgroupdefaultValue_last;
					Log.info("NIF Group value is: "+NIFgroup);
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
				
			}else {
				
			//VLAN Tag
				addtextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

			//Sub Interface slot
				selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);
				
				if(subInterfaceSlot.equalsIgnoreCase("null")) {

					//Sub Interface Name
					SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanTag;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
				else if(!subInterfaceSlot.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanTag;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanTag+NIGgroupdefaultValue_last;
					Log.info("NIF Group value is: "+NIFgroup);
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
			}
			
		//Signalling port
			signallingPort(application, gateway);
		  }
	  }
	 
	  else if(gateway.contains("SBC")) {
		  if(gateway.startsWith("FRA")) {
			  if(!internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  if(vlanTag.equalsIgnoreCase("null")) {
					 
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is a mandatory field. No values has been passed as an input");
				 
					//IP Interface
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
					
					  
				//Signalling port
						signallingPort(application, gateway);	  
			
				  }
				  else if(!vlanTag.equalsIgnoreCase("null")) {
					 
					//VLAN Tag
						addtextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);

						//IP Interface
						  ipInterface = ipInterfaceDEfaultValue +vlanTag;
						  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
						  
					//Address Context
						  addressContext=addressContextDefaultValue+vlanTag;
						  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
						
						  
					//IP Interface Group
						  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanTag;
						  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				 
				  }
				  
			  }
			  else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
					//Internet Based Customer
						addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
						
				  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				  if(vlanDefaultvalue.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
				  }else {
					//VLAN tag  
//					  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
//					  if(VlanEnability) {
//						  Log.info("VLAN Tag is enabled");
//						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
//					  }else {
//						  Log.info("VLAN Tag is disabled");
//						  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
//					  } 
				  }
				  
					//IP Interface
				  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
				  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
				  
			//Address Context
				  addressContext=addressContextDefaultValue+vlanDefaultvalue;
				  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
				
				  
			//IP Interface Group
				  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanDefaultvalue;
				  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
			  }
		  }
		 else {
			 if(!internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  
				//VLAN tag  
					  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
					  if(VlanEnability) {
						  Log.info("VLAN Tag is enabled");
						  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
					  }else {
						  Log.info("VLAN Tag is disabled");
						  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
					  }
					  
				//IP Interface
					  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values
					  
				//Address Context
					  compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values
					
					  
				//IP Interface Group
					  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values
					
					  
				//Signalling port
						signallingPort(application, gateway);	  
						
				}
			 
			 if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.INFO, " 'Signalling Port' text field will not display, if 'Internet Based Custoer' is selected");
				  
				//Internet Based Customer
					addCheckbox_commonMethod(application, "internetBasedCustomer_checkbox", "Internet Based Customer", internetBasedCustomer, "No", xml);
					
			  String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  if(vlanDefaultvalue.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Internet Based Customer' is selected");
			  }else {
				//VLAN tag  
				  boolean VlanEnability=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).isEnabled();
				  if(VlanEnability) {
					  Log.info("VLAN Tag is enabled");
					  ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Tag' text field is enabled");
				  }else {
					  Log.info("VLAN Tag is disabled");
					  ExtentTestManager.getTest().log(LogStatus.PASS, " 'VLAN Tag' text field is disabled");
				  } 
			  }
			  
				//IP Interface
			  ipInterface = ipInterfaceDEfaultValue +vlanDefaultvalue;
			  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
			  
		//Address Context
			  addressContext=addressContextDefaultValue+vlanDefaultvalue;
			  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
			
			  
		//IP Interface Group
			  ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanDefaultvalue;
			  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
			 }
		 }
	  }
	  
	//Signalling Zone
	  if(internetBasedCustomer.equalsIgnoreCase("yes")) {
		  
		  compareText_fromtextFields(application, "Signaling Zone", "signallingZone_textField", signallingZoneDefaultValue, xml);
		  
		  edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
		  
	  }else {
		  edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
	  }
		
	
	
	//Signalling Transport Protocol
		selectValueInsideDropdown(application, "signallingTransportProtocol_Dropdown", "Signalling Transport Protocol", signallingtransportProtocol, xml);
	
		WebElement internetBasedCusotmerView=getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox"));
		scrolltoview(internetBasedCusotmerView);
		Thread.sleep(2000);
		
		
		if(signallingtransportProtocol.equalsIgnoreCase("sip-tls-tcp")) {
			
			//TLS Profile
			addtextFields_commonMethod(application, "TLS Profile", "TLS_textField", TLSfield, xml);
			
			//SRTP
			addCheckbox_commonMethod(application, "srtp_checkbox", "SRTP", srtp, "no", xml);
		}
		
	//Colt Signalling IP
		addtextFields_commonMethod(application, "Colt Signaling IP", "coltSignalingIP_textField", coltSignalingIP, xml);
		
	//Media IP
		addtextFields_commonMethod(application, "Media IP", "mediaIP_textField", mediaIP, xml);
		
	//Reuse NIF Group
		addCheckbox_commonMethod(application, "reuseNIFgroup_checkbox", "Reuse NIF Group", reuseNIFgroup, "No", xml);
		
	//Reuse Sig Zone/Part
		addCheckbox_commonMethod(application, "reuseSigZonePart_checkbox", "Reuse Sig Zone/Part", reuseSigZonePart, "No", xml);
		
	//Call Admission Control
		addCheckbox_commonMethod(application, "callAdmissionControl_checkbox", "Call Admission Control", callAdmissionControl, "No", xml);
		
		if(callAdmissionControl.equalsIgnoreCase("yes")) {
			
			//Call Limit
			selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", callLimit, xml);
			
			if(callLimit.equalsIgnoreCase("Defined")) {
				addtextFields_commonMethod(application, "Limit Number", "limitNumber_textField", limitNumber, xml);
			}
		}
		
		WebElement signalingTransportprotocolLabelname=getwebelement(xml.getlocator("//locators/" + application + "/signalingTransportProtocol_labeName"));
		scrolltoview(signalingTransportprotocolLabelname);
		Thread.sleep(1000);
		
	//Call Rate Limit
		addCheckbox_commonMethod(application, "callrateLimit_checkbox", "Call Rate Limit", callrateLimit, "No", xml);
		if(callrateLimit.equalsIgnoreCase("yes")) {
			
			String callratelimitactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callRateLimitt_textField")).getAttribute("value");
			Log.info(" 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
		
			if(!callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(callrateLimiteValue);
					
				if(i>100) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less 100 for all Trunks");
				}
				else if(i<=100){
					waitForpageload();
					waitforPagetobeenable();
					Thread.sleep(1000);
					
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
				Log.info("'Call rate Limit' value is not edited");
			}
		}
		
		
	//Source Address Filtering
		selectValueInsideDropdown(application, "sourceAddressFiltering_Dropdown", "Source Address Filtering", sourceAddressFiltering, xml);
		
	//100rel Support
		selectValueInsideDropdown(application, "relsupport_Dropdown", "100rel Support", relSupport, xml);
		
	//SIP Session Keepalive Timer(Sec)
		edittextFields_commonMethod(application, "SIP Session Keepalive Timer(Sec)", "sipSessionKeepAliverTimer_textField", sipSessionkeepAliveTimer, xml);
	
		//Text message under "SIP Session Keepalive timer"
			methodToFindMessagesUnderTextField(application, "defaultTextMessageUnderSIPsessionTimer", "SIP Session Keepalive Timer(Sec)", "Default SIP Session Keepalive Timer (sec): 1800");
		
			
			WebElement reusenifgroup=getwebelement(xml.getlocator("//locators/" + application + "/reuseNIFgroup_checkbox"));
			scrolltoview(reusenifgroup);
			Thread.sleep(2000);
			
	//Retry Invite
		edittextFields_commonMethod(application, "Retry Invite", "retryinvite_textField", retryInvite, xml);
		
		//Text message under "Retry Invite" field
		methodToFindMessagesUnderTextField(application, "defaultTextMessageUnderretryInvite", "Retry Invite", "Default Retry Invite :2");
		
	//Route Priority
		selectValueInsideDropdown(application, "routepriority_Dropdown", "Route Priority", routePriority, xml);
		
	//Address Reachability
		selectValueInsideDropdown(application, "addressReachability_Dropdown", "Address Reachability", addressReachability, xml);
		
		WebElement retryinviteView=getwebelement(xml.getlocator("//locators/" + application + "/retryinvite_textField"));
		scrolltoview(retryinviteView);
		Thread.sleep(2000);
	
	//E164GlobalProfile
		addTrunk_existingNewDropdownField(application, globalProfile_ExistingSelection, globalProfile_newSelection, globalProfile_ExistingValue, 
				globalProfile_newValue, "globalProfile_Dropdown" , "globalProfile_Checkbox", "globalProfile_textField", "E164GlobalProfile");
		
		
	//E164LocalProfile	
		addTrunk_existingNewDropdownField(application, localProfile_existingFieldSelection, localProfile_newFieldSelection, localProfile_existingvalue,
				localProfile_newvalue, "localProfile_Dropdown", "localProfile_checkbox", "localProfile_TextField", "E164LocalProfile");
		
		
	//COS profile
		addTrunk_existingNewDropdownField(application, COSprofile_existingFieldSelection, COSprofile_newFieldSelection, COSprofile_existingValue,
				COSprofile_newValue, "COSprofile_Dropdown", "COSprofile_checkbox", "COSprofile_TextField", "COS Profile");
		
		
	//PSPG Name
		addTrunk_existingNewDropdownField(application, PSPGname_existingFieldSelection, PSPGname_newFieldSelection, pspgName_existingValue,
				pspgName_newValue, "PSPSGname_Dropdown", "PSPGname_Checkbox", "PSPGname_TextField", "PSPG Name");
		
		
	//Preferred  PSP
		addTrunk_existingNewDropdownField(application, prefferedPSP_existingFieldSelection, prefferedPSP_newFieldSelection, preferredPSP_exitingvalue,
				preferredPSP_newvalue, "preferredPSP_Dropdown", "preferredPSP_checkbox", "preferredPSP_TextField", "Preferred PSP");
		

		WebElement globalProfileView=getwebelement(xml.getlocator("//locators/" + application + "/globalProfile_textField"));
		scrolltoview(globalProfileView);
		Thread.sleep(2000);
		
		
	//Carrier
		addTrunk_existingNewDropdownField(application, carrier_existingFieldSelection, carrier_newFieldSelection, carrier_existingValue,
				carrier_newValue, "carriers_Dropdown", "carriers_checkbox", "carriers_TextField", "Carrier");
		
		
	//IP Signalling Profile
		addTrunk_existingNewDropdownField(application, IPsignallingProfile_existingFieldSelection, IPsignallingProfile_newFieldSelection, IPsignallingProfile_existingValue,
				IPsignallingProfile_newValue, "IPsignallingProfile_Dropdown", "IPsignallingProfile_Checkbox", "IPsignallingProfile_textField", "IP Signaling Profile");
		
		
	//Egress IP Signaling Profile
		addTrunk_existingNewDropdownField(application, EgressIpsignal_existingFieldSelection, EgressIpsignal_newFieldSelection, EgressIPsignal_existingValue,
				EgressIPsignal_newValue, "EgressIpsignal_Dropdown", "EgressIPsignal_checkbox", "EgressipSignal_TextField", "Egress IP Signaling Profile");
		
		
		scrolltoend();
		Thread.sleep(2000);
		
	
	//In DM/PM rule
		addTrunk_existingNewDropdownField(application, InDMPMrule_existingFieldSelection, InDMPMrule_newFieldSelection, InDMPMrule_existingValue,
				InDMPMrule_newValue,  "InDMPMrule_Dropdown", "InDMPMrule_checkbox", "InDMPMrule_TextField", "In DM/PM rule");
		
		
	//Out DM/PM rule
		addTrunk_existingNewDropdownField(application, OutDMPMrule_existingFieldSelection, OutDMPMrule_newFieldSelection, OutDMPMrule_existingValue,
				OutDMPMrule_newValue, "OutDMPMrule_Dropdown", "OutDMPMrule_checkbox", "OutDMPMrule_TextField", "Out DM/PM rule");
		
		
	//Feature Control Profile	
		addTrunk_existingNewDropdownField(application, featureControlprofile_existingFieldSelection, featureControlprofile_newFieldSelection, featureControlprofile_existingValue,
				featureControlprofile_newValue, "featureControlprofile_Dropdown", "featureControlprofile_Checkbox", "featureControlprofile_TextField", "Feature Control Profile");
		
		
	//Local Ring Back Tone
		addTrunk_existingNewDropdownField(application, localRingBackTone_existingFieldSelection, localRingBackTone_newFieldSelection, localRingBackTone_existingValue,
				localRingBackTone_newValue, "localRingBackTone_Dropdown", "localRingBackTone_checkbox", "localRingBackTone_TextField", "Local Ring Back Tone");
	
		
	//Create Lower Case Routes
		if((country.equals("DK (Denmark)")) || (country.equals("IT (Italy)")) || (country.equals("IE (Ireland)")) || (country.equals("PT (Portugal)")) ||
				(country.equals("ES (Spain)")) || (country.equals("SE (Sweden)")) || (country.equals("CH (Switzerland)")) || (country.equals("UK (UK)")))
		{
			addCheckbox_commonMethod(application, "createLowerCaseRoute_checkbox", "Create Lower Case Routes", createLowerCaseRoutervalue, "no", xml);
		}
		else
		{
			addCheckbox_commonMethod(application, "createLowerCaseRoute_checkbox", "Create Lower Case Routes", createLowerCaseRoutervalue, "Yes", xml);
		}
		
		
		
	//PSX Manual Configuration	
		addCheckbox_commonMethod(application, "PSXmanualConfig_checkbox", "PSX Manual Configuration", PSXmanualConfigvalue, "No" ,xml);
		
		
		if(gateway.contains("SBC")) {
			//Manual Configuration on SBC
			addCheckbox_commonMethod(application, "SBCmanualconfig_checkbox", "Manual Configuration On SBC", SBCmanualconfigValue, "No", xml);
		}else {
			//Manual Configuration On GSX
			addCheckbox_commonMethod(application, "GSXmanualConfig_checkbox", "Manual Configuration On GSX", GSXmanualConfigvalue, "No", xml);
		}
		
		
		scrolltoend();
		Thread.sleep(1000);
		
		click_commonMethod(application, "OK", "OKbutton", xml);
		
	}
	
	
	public void methodToFindMessagesUnderTextField(String application ,String xpath, String labelname, String expectedmsg) {
		
		boolean defaultPortValueUnderSIPSignalling=false;
		try {	
			defaultPortValueUnderSIPSignalling=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			if(defaultPortValueUnderSIPSignalling) {
				
				WebElement defaultValue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				if(defaultValue.getText().contains(expectedmsg)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
					Log.info("Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
					Log.info("Under '"+ labelname +"' text field', text message displays as "+ defaultValue);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
				Log.info("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			Log.info("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
		}
	}
	
	
	public String gateway_code(String application, String gateway) {
		
		String code=null;
		
		if(gateway.equals("DEVGSX1")) {
			code="DEV";
		}
		
		else if(gateway.equals("DEVSBC1")) {
			code="DEA";
		}
		
		else if(gateway.equals("DEVSBC2")) {
			code="DEB";		
		}
				
		else if(gateway.equals("OPSGSX1")) {
			code="OPS";
		}
				
		else if(gateway.equals("MILGSX1")) {
			code="MIA";
		}
				
		else if(gateway.equals("ZRHNBS1")) {
			code="ZHA";
		}
				
		else if(gateway.equals("PARNBS1")) {
			code="PSA";
		}
				
		else if(gateway.equals("LONNBS1")) {
			code="LNA";
		}
				
		else if(gateway.equals("FRANBS1")) {
			code="FTA";
		}
				
		else if(gateway.equals("MADGSX1")) {
			code="MDA";
		}
				
		else if(gateway.equals("BHXNBS1")) {
			code="BHA";
		}
				
		else if(gateway.equals("PARGSX2")) {
			code="PSB";
		}
				
		else if(gateway.equals("FRAGSX2")) {
			code="FTB";
		}
				
		else if(gateway.equals("PARGSX3")) {
			code="PSC";
		}
				
		else if(gateway.equals("FRASBC1")) {
			code="FTC";
		}
				
		else if(gateway.equals("PARSBC1")) {
			code="PSD";
		}
				
		else if(gateway.equals("ZRHGSX2")) {
			code="ZRB";
		}
				
		else if(gateway.equals("FRASBC2")) {
			code="FTD";
		}
				
		else if(gateway.equals("PARSBC2")) {
			code="PSE";
		}
				
		else if(gateway.equals("FRASBC3")) {
			code="FTF";
		}
				
		else if(gateway.equals("PARSBC3")) {
			code="PSF";
		}
		
		return code;
	}
	
	
	public void signallingPort(String application, String gateway) throws InterruptedException, DocumentException {
		
	
		String signallingPort_expectedVaue=signalingport(application, gateway);
		compareText_fromtextFields(application, "Signaling Port", "signallingPort_textField", signallingPort_expectedVaue, xml);
	}
	
	public void signallingPort_viewPage(String application, String gateway) throws InterruptedException, DocumentException {
		
		
		String signallingPort_expectedVaue=signalingport(application, gateway);
//		compareText_InViewPage(application, "Signalling Port", signallingPort_expectedVaue, xml);
		compareText(application, "Signalling Port", "viewTrunkPage_signallingPort", signallingPort_expectedVaue, xml);
	}
	
	public String signalingport(String application, String gateway) {
		
		String code=null;
		
		if(gateway.equals("DEVGSX1")) {
			code="543";
		}
		
		else if(gateway.equals("DEVSBC1")) {
			code="1017";
		}
		
		else if(gateway.equals("DEVSBC2")) {
			code="1013";		
		}
				
		else if(gateway.equals("OPSGSX1")) {
			code="509";
		}
				
		else if(gateway.equals("MILGSX1")) {
			code="690";
		}
				
		else if(gateway.equals("ZRHNBS1")) {
			code="1003";
		}
				
		else if(gateway.equals("PARNBS1")) {
			code="889";
		}
				
		else if(gateway.equals("LONNBS1")) {
			code="755";
		}
				
		else if(gateway.equals("FRANBS1")) {
			code="1120";
		}
				
		else if(gateway.equals("MADGSX1")) {
			code="725";
		}
				
		else if(gateway.equals("BHXNBS1")) {
			code="1013";
		}
				
		else if(gateway.equals("PARGSX2")) {
			code="773";
		}
				
		else if(gateway.equals("FRAGSX2")) {
			code="629";
		}
				
		else if(gateway.equals("PARGSX3")) {
			code="556";
		}
				
		else if(gateway.equals("FRASBC1")) {
			code="1023";
		}
				
		else if(gateway.equals("PARSBC1")) {
			code="1005";
		}
				
		else if(gateway.equals("ZRHGSX2")) {
			code="506";
		}
				
		else if(gateway.equals("FRASBC2")) {
			code="1013";
		}
				
		else if(gateway.equals("PARSBC2")) {
			code="1007";
		}
				
		else if(gateway.equals("FRASBC3")) {
			code="1001";
		}
				
		else if(gateway.equals("PARSBC3")) {
			code="1003";
		}
		
		return code;
	}
	
	
	
	public String trunkType_code(String application, String trunkType) {
			
			String code=null;
			
			if(trunkType.equalsIgnoreCase("Number Hosting")) {
				code="N";
			}
			
			else if(trunkType.equalsIgnoreCase("Carrier VoIP")) {
				code="I";
			}
			
			else if(trunkType.equalsIgnoreCase("IN over Carrier VoIP")) {
				code="D";
			}
			
			else if(trunkType.equalsIgnoreCase("National Carrier VoIP")) {
				code="National Carrier VoIP";
			}
			
			return code;
		}
	
	
		public String voipProtocol_code(String application, String voipProtocol) {
			
			String code1=null;
			
			if(voipProtocol.equalsIgnoreCase("SIP")) {
				code1="S";
			}
			
			else if(voipProtocol.equalsIgnoreCase("SIP-I")) {
				code1="S";
			}
			
			return code1;
		}
	
	
		public String CDRdelivery_code(String application, String cdrDElivery) {
			
			String code_cdr=null;
			
			if(cdrDElivery.equalsIgnoreCase("Delivery to COCOM")) {
				code_cdr="C";
			}
			
			else if(cdrDElivery.equalsIgnoreCase("No delivery to COCOM")) {
				code_cdr="0";
			}
			
			else if(cdrDElivery.equalsIgnoreCase("IN Tremination")) {
				code_cdr="I";
			}
			
			return code_cdr;
		}
		
		
	public void addTrunk_existingNewDropdownField(String application, String existingFieldSelection, String newFieldSelection, String existinFieldvalue,
			String newFieldvalue,String xpath_dropdown,String xpath_checkbox, String xpath_textfield, String labelname ) throws IOException, InterruptedException, DocumentException {
		
		if((existingFieldSelection.equalsIgnoreCase("yes")) && (newFieldSelection.equalsIgnoreCase("no"))) {
			
			selectValueInsideDropdown(application, xpath_dropdown, labelname, existinFieldvalue, xml);
			
		}
		else if((existingFieldSelection.equalsIgnoreCase("no")) && (newFieldSelection.equalsIgnoreCase("yes"))) {
			
			click_commonMethod(application, labelname, xpath_checkbox, xml);
			Thread.sleep(1000);
			
			addtextFields_commonMethod(application, labelname, xpath_textfield, newFieldvalue, xml);
		}
		
	}
	
	
	public void editTrunk_existingNewDropdownField(String application, String existingFieldSelection, String newFieldSelection, String existinFieldvalue,
			String newFieldvalue,String xpath_dropdown,String xpath_checkbox, String xpath_textfield, String labelname ) throws IOException, InterruptedException, DocumentException {
		
		if((existingFieldSelection.equalsIgnoreCase("yes")) && (newFieldSelection.equalsIgnoreCase("no"))) {
			
			editTrunk_selectExistingOrNewDropdown(application, xpath_dropdown, xpath_checkbox, existinFieldvalue, labelname);
			
		}
		else if((existingFieldSelection.equalsIgnoreCase("no")) && (newFieldSelection.equalsIgnoreCase("yes"))) {
			
			editTrunk_selectExistingOrNewTextField(application, xpath_textfield, xpath_checkbox, labelname, newFieldvalue);
			
		}
		
		else if((existingFieldSelection.equalsIgnoreCase("null")) && (newFieldSelection.equalsIgnoreCase("null"))) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " field is not edited");
		}
		
	}
	
	
	public void editTrunk_selectExistingOrNewTextField(String application, String xpath_textfield, String xpath_checkbox, String labelname, String textFieldValue) {
		
		boolean fieldEnabled=false;
		
		try {	
			fieldEnabled=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath_textfield +"")).isEnabled();
			
			if(fieldEnabled) {
				
				edittextFields_commonMethod(application, labelname, xpath_textfield, textFieldValue, xml);
				
			}else {
				
				click_commonMethod(application, labelname, xpath_checkbox, xml);
				Thread.sleep(1000);
				
				edittextFields_commonMethod(application, labelname, xpath_textfield, textFieldValue, xml);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			Log.info(labelname + " field is not displaying");
			
		}
		
	}
	
	
	public void editTrunk_selectExistingOrNewDropdown(String application, String xpath_dropdown, String xpath_checkbox, String existinFieldvalue, String labelname) throws InterruptedException, DocumentException, IOException {
		
		boolean fieldEnabled=false;
		
	try {	
		fieldEnabled=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath_dropdown +"")).isEnabled();
		
		if(fieldEnabled) {
			
			selectValueInsideDropdown(application, xpath_dropdown, labelname, existinFieldvalue, xml);
			
		}else {
			
			click_commonMethod(application, labelname, xpath_checkbox, xml);
			Thread.sleep(1000);
			
			selectValueInsideDropdown(application, xpath_dropdown, labelname, existinFieldvalue, xml);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
		Log.info(labelname + " field is not displaying");
	}
	}
	
	
	public void verifyaddTrunk_existingNewDropdownField(String application, String existingFieldSelection, String newFieldSelection, String existinFieldvalue,
			String newFieldvalue,String xpath_dropdown,String xpath_checkbox, String xpath_textfield, String labelname ) throws IOException, InterruptedException, DocumentException {

		
		if((existingFieldSelection.equalsIgnoreCase("yes")) && (newFieldSelection.equalsIgnoreCase("no"))) {
			
			viewTrunktable_ForExistingalues(application, labelname, existinFieldvalue);
			
		}
		else if((existingFieldSelection.equalsIgnoreCase("no")) && (newFieldSelection.equalsIgnoreCase("yes"))) {
			
			viewTrunktable_ForNewValues(application, labelname, newFieldvalue);
		}
		
	}
	
	
	public void viewTrunktable_ForNewValues(String application, String labelName, String expectedValue) throws InterruptedException, DocumentException, IOException {
		
	try {	
		WebElement value=getwebelement(xml.getlocator("//locators/" + application + "/viewTrunk_Table_ForNewValues").replace("value", labelName));
		String actualValue=Gettext(value);
		
		if(expectedValue.equalsIgnoreCase(actualValue)) {
			ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelName +"' field '"+expectedValue+"' is same as the Acutal value '"+actualValue+"'");
			Log.info("The Expected value for '"+ labelName +"' field '"+expectedValue+"' is same as the Acutal value '"+expectedValue+"'");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelName +"' field '"+expectedValue+"' is not same as the Acutal value '"+actualValue+"'");
			Log.info("The Expected value for '"+ labelName +"' field '"+expectedValue+"' is not same as the Acutal value '"+expectedValue+"'");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelName + " field is not displaying");
		Log.info(labelName + " field is not displaying");
	}
		
	}
	
	public void viewTrunktable_ForExistingalues(String application, String labelName, String expectedValue) throws InterruptedException, DocumentException, IOException {
		
	try {	
		WebElement value=getwebelement(xml.getlocator("//locators/" + application + "/viewTrunk_Table_ForExistingValue").replace("value", labelName));
		String actualValue=Gettext(value);
		
	if(expectedValue.equalsIgnoreCase("null")) {
		expectedValue="None";
		if(expectedValue.equalsIgnoreCase(actualValue)) {
			ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelName +"' field '"+expectedValue+"' is same as the Acutal value '"+actualValue+"'");
			Log.info("The Expected value for '"+ labelName +"' field '"+expectedValue+"' is same as the Acutal value '"+expectedValue+"'");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelName +"' field '"+expectedValue+"' is not same as the Acutal value '"+actualValue+"'");
			Log.info("The Expected value for '"+ labelName +"' field '"+expectedValue+"' is not same as the Acutal value '"+expectedValue+"'");
		}
	}
	else {
		if(expectedValue.equalsIgnoreCase(actualValue)) {
			ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelName +"' field '"+expectedValue+"' is same as the Acutal value '"+actualValue+"'");
			Log.info("The Expected value for '"+ labelName +"' field '"+expectedValue+"' is same as the Acutal value '"+expectedValue+"'");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelName +"' field '"+expectedValue+"' is not same as the Acutal value '"+actualValue+"'");
			Log.info("The Expected value for '"+ labelName +"' field '"+expectedValue+"' is not same as the Acutal value '"+expectedValue+"'");
		}
	}
		
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelName + " field is not displaying");
		Log.info(labelName + " field is not displaying");
	}
		
	}
	
	
	
	public void viewTrunk_Primary(String application, String customerName, String servicename, String trunkType, String voipProtocol, String country, String CDRdelivery, String gateway, String quality,
			String trafficDirection, String ipAddresstype, String SIPsignallingPort, String internetBasedCustomer, String vlanTag, String subInterfaceSlot,
			String signallngZone, String signallingtransportProtocol, String coltSignalingIP, String mediaIP, String reuseNIFgroup, String reuseSigZonePart,
			String callAdmissionControl, String callrateLimitSelection, String sourceAddressFiltering, String relSupport, String sipSessionkeepAliveTimer,
			String retryInvite, String routePriority, String addressReachability, String carrierIPoriginating, String carrierIPterminating, String TLSfield,
			String srtp, String prefix, String globalProfile_ExistingSelection, String globalProfile_newSelection, String globalProfile_ExistingValue, String globalProfile_newValue,
			String localProfile_existingFieldSelection, String localProfile_newFieldSelection, String localProfile_existingvalue, String localProfile_newvalue,
			String COSprofile_existingFieldSelection, String COSprofile_newFieldSelection, String COSprofile_existingValue, String COSprofile_newValue,
			String PSPGname_existingFieldSelection, String PSPGname_newFieldSelection,String pspgName_existingValue, String pspgName_newValue,
			String prefferedPSP_existingFieldSelection, String prefferedPSP_newFieldSelection,String preferredPSP_exitingvalue, String preferredPSP_newvalue,
			String carrier_existingFieldSelection, String carrier_newFieldSelection, String carrier_existingValue, String carrier_newValue,
			String IPsignallingProfile_existingFieldSelection, String IPsignallingProfile_newFieldSelection, String IPsignallingProfile_existingValue, String IPsignallingProfile_newValue,
			String EgressIpsignal_existingFieldSelection,String EgressIpsignal_newFieldSelection, String EgressIPsignal_existingValue, String EgressIPsignal_newValue,
			String InDMPMrule_existingFieldSelection, String InDMPMrule_newFieldSelection, String InDMPMrule_existingValue, String InDMPMrule_newValue,
			String OutDMPMrule_existingFieldSelection, String OutDMPMrule_newFieldSelection, String OutDMPMrule_existingValue, String OutDMPMrule_newValue,
			String featureControlprofile_existingFieldSelection,String featureControlprofile_newFieldSelection, String featureControlprofile_existingValue, String featureControlprofile_newValue,
			String localRingBackTone_existingFieldSelection, String localRingBackTone_newFieldSelection, String localRingBackTone_existingValue, String localRingBackTone_newValue,
			String createLowerCaseRoutervalue,String PSXmanualConfigvalue, String GSXmanualConfigvalue, String callLimit, String limitNumber, String callrateLimiteValue, String SBCmanualconfigValue) throws IOException, InterruptedException, DocumentException {   
		
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(1000);
		
		scrollToTop();
		Thread.sleep(2000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "verifying Added Trunk value");
		String AddressContext="EXTERNAL_AC_";
		String IPINTERFACEGROUP ="EXTERNAL_IPIG_";
		String IPINTERFACE=	"EXTERNAL_IPIF_";
		
		String subInterfacename_starting="SIF-1-";
		String subInterfacename_middle="-2-";
		String NIFgroupDEfaultValue_starting="SIF-1-";
		String NIFgroupDEfaultValue_middle="-2-";
		String NIGgroupdefaultValue_last="-OUTSIDE";
		
		
		
		//Trunk Group Description
		String expectedValue1=customerName+"_"+servicename+"_"+trunkType;
		compareText_InViewPage(application, "Trunk Group Description", expectedValue1, xml);
		
		//Trunk Type
			compareText_InViewPage(application, "Trunk Type", trunkType, xml);
			
		//VOIP Protocol
			compareText_InViewPage(application, "VOIP Protocol", voipProtocol, xml);
			
		//Billing Country
			compareText_InViewPage(application, "Billing Country", country, xml);
			
		//CDR Delivery
			compareText_InViewPage(application, "CDR Delivery", CDRdelivery, xml);
			
		//Prefix
//			compareText_InViewPage(application, "Prefix", prefix, xml);

		//Gateway
			compareText_InViewPage(application, "Gateway", gateway, xml);
			
		//Quality
			compareText_InViewPage(application, "Quality", quality, xml);
			
		//Trunk Group Name
			compareText_InViewPage(application, "Trunk Group Name", primarytrunkGroupname, xml);
			
		//Traffic Direction
			compareText_InViewPage(application, "Traffic Directions", trafficDirection, xml);
			
		//IP Address Type
			compareText_InViewPage(application, "IP Address Type", "IPv4", xml);
			
		//Carrier IP Originating
			compareText_InViewPage(application, "Carrier IP Originating", carrierIPoriginating, xml);
			
		//Carrier IP Terminating
			compareText_InViewPage(application, "Carrier IP Terminating", carrierIPterminating, xml);
			
		//SIP Signaling Port
			  if(voipProtocol.equalsIgnoreCase("SIP")) {
				  compareText_InViewPage(application, "SIP Signalling Port", SIPsignallingPort, xml);
			  }else {
				  Log.info(" 'SIP Signalling port' will not display, if voip protocol selected as 'SIP-1'");
			  }
			
		
			 
	//VLAN Tag 
			  if(!gateway.contains("SBC")) {
				  
				//Sub Interface Slot
				  String subinterfaceSlot_viewPage=getwebelement("//div[div[label[contains(text(),'Sub Interface Slot')]]]/div[2]").getText();
					
				  if(internetBasedCustomer.equalsIgnoreCase("no")) {
					  
					  //VLAN Tag
//					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  //Sub Interface Name
					  	String SubInterfaceName=subInterfacename_starting+subinterfaceSlot_viewPage+subInterfacename_middle+vlan_actualValue;
						compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);
						
						//NIF Group
						String NIFgroup=NIFgroupDEfaultValue_starting+subinterfaceSlot_viewPage+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
						Log.info("NIF Group value is: "+NIFgroup);
						compareText_InViewPage(application, "NIF Group", NIFgroup, xml);
					  
					  //Signalling Zone
						if(signallngZone.equalsIgnoreCase("null")) {
					  compareText_InViewPage(application, "Signalling Zone", "OUT-UNTRUSTED", xml);
						}
						else if(!signallngZone.equalsIgnoreCase("null")) {
							  compareText_InViewPage(application, "Signalling Zone", signallngZone, xml);
								}
					  
					  
				  }
				  else if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
					 
					  //VLAN Tag
//					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  //Sub Interface Name
					  	String SubInterfaceName=subInterfacename_starting+subinterfaceSlot_viewPage+subInterfacename_middle+vlan_actualValue;
						compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);
						
						//NIF Group
						String NIFgroup=NIFgroupDEfaultValue_starting+subinterfaceSlot_viewPage+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
						Log.info("NIF Group value is: "+NIFgroup);
						compareText_InViewPage(application, "NIF Group", NIFgroup, xml);
					  
						//Signalling Zone
						if(signallngZone.equalsIgnoreCase("null")) {
					  compareText_InViewPage(application, "Signalling Zone", "OUT-UNTRUSTED", xml);
						}
						else if(!signallngZone.equalsIgnoreCase("null")) {
							  compareText_InViewPage(application, "Signalling Zone", signallngZone, xml);
								}
					  
				  }
			  }
			  else if(gateway.contains("SBC")) {
				  if(internetBasedCustomer.equalsIgnoreCase("Yes")) {
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  //Address Content
					  compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);
					  
					  //IP Interface Group
					  compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);
					  
					  //IP Interface
					  compareText(application, "IP Interface", "viewTrunkPage_IPinterface", IPINTERFACE+vlan_actualValue, xml);
					  
					//Signalling Zone
						if(signallngZone.equalsIgnoreCase("null")) {
					  compareText_InViewPage(application, "Signalling Zone", "OUT-UNTRUSTED", xml);
						}
						else if(!signallngZone.equalsIgnoreCase("null")) {
							  compareText_InViewPage(application, "Signalling Zone", signallngZone, xml);
								}
					  
					  
				  }
				  else if(internetBasedCustomer.equalsIgnoreCase("No")) {
					  
					  //VLAN Tag
					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
					  
					  String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
					  
					  //Address Content
					  compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);
					  
					  //IP Interface Group
					  compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);
					  
					  //IP Interface
					  compareText(application, "IP Interface", "viewTrunkPage_IPinterface", IPINTERFACE+vlan_actualValue, xml);
				  }
			  }
				  
		//Signalling Transport Protocol
			compareText_InViewPage(application, "Signalling Transport Protocol", signallingtransportProtocol, xml);
			
		//Signalling Port
		if(internetBasedCustomer.equalsIgnoreCase("No")) {	
//			signallingPort_viewPage(application, gateway);
		}
			
		//Colt Signalling IP
			compareText_InViewPage(application, "Colt Signalling IP", coltSignalingIP, xml);

		//Media IP
			compareText_InViewPage(application, "Media IP", mediaIP, xml);
			
		//Reuse NIF Group	
			compareText_InViewPage(application, "Reuse NIF Group", reuseNIFgroup, xml);
			
		//Reuse Sig Zone/Port
			compareText_InViewPage(application, "Reuse Sig Zone/Port", reuseSigZonePart, xml);
		
		//Call Admission Control
			compareText_InViewPage(application, "Call Admission Control", callAdmissionControl, xml);
			
			if(callAdmissionControl.equalsIgnoreCase("yes")) {
			  //call limit	
				
				if(limitNumber.equalsIgnoreCase("null")) {
					Log.info("Limit Number value is not edited");
				}else {
					compareText_InViewPage(application, "Call Limit",limitNumber , xml);
				}
				
			}
		
		//Call Rate Limit
			if(callrateLimitSelection.equalsIgnoreCase("Yes")) {
				Thread.sleep(2000);
				//call rate limit value
				if(callrateLimiteValue.equalsIgnoreCase("null")) {
					Log.info("Call rate Limit value is not edited");
				}else {
					compareText_InViewPage(application,"Call Rate Limit", callrateLimiteValue, xml);
				}
				
			}
			
			
		//Source Address Filtering
			compareText_InViewPage(application, "Source Address Filtering", sourceAddressFiltering, xml);
			
		//100rel Support	
			compareText_InViewPage(application, "100rel Support", relSupport, xml);
			
		//SIP session Keepalive timer
			compareText_InViewPage(application, "SIP session Keepalive timer", sipSessionkeepAliveTimer, xml);
			
		//Retry Invite
			compareText_InViewPage(application, "Retry Invite", retryInvite, xml);
			
		//Route Priority
			compareText_InViewPage(application, "Route Priority", routePriority, xml);
		
		//Address Reachability	
			compareText_InViewPage(application, "Address Reachability", addressReachability, xml);
			
		//Create Lower Case Routes
			compareText_InViewPage(application, "Create Lower Case Routes", createLowerCaseRoutervalue, xml);
			
		//PSX Manual Configuration	
			compareText_InViewPage(application, "PSX Manual Configuration", PSXmanualConfigvalue, xml);
			
		//GSX or SBX configuration	
			if(gateway.contains("SBC")) {
				//Manual Configuration on SBC
				compareText_InViewPage(application, "Manual Configuration on SBC", SBCmanualconfigValue, xml);
			}else {
				//Manual Configuration On GSX
				compareText_InViewPage(application, "Manual Configuration on GSX", GSXmanualConfigvalue, xml);
			}

		try {	
			WebElement PSXconfigurationLabelName=getwebelement(xml.getlocator("//locators/" + application + "/psxConfiguration_LabelName_viewTrunkPage"));
			scrolltoview(PSXconfigurationLabelName);
			Thread.sleep(1000);
		}catch(Exception e) {
			Log.info("PSX configuration label is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "PSX configuration label is not displaying");
		}
			
		//E164Global Profile	
			verifyaddTrunk_existingNewDropdownField(application, globalProfile_ExistingSelection, globalProfile_newSelection, globalProfile_ExistingValue, 
					globalProfile_newValue, "globalProfile_Dropdown" , "globalProfile_Checkbox", "globalProfile_textField", "E164Global Profile");
			
			
		//E164LocalProfile	
			verifyaddTrunk_existingNewDropdownField(application, localProfile_existingFieldSelection, localProfile_newFieldSelection, localProfile_existingvalue,
					localProfile_newvalue, "localProfile_Dropdown", "localProfile_checkbox", "localProfile_TextField", "E164 Local Profile");
			
			
		//COS profile
			verifyaddTrunk_existingNewDropdownField(application, COSprofile_existingFieldSelection, COSprofile_newFieldSelection, COSprofile_existingValue,
					COSprofile_newValue, "COSprofile_Dropdown", "COSprofile_checkbox", "COSprofile_TextField", "COS Profile");
			
			
		//PSPG Name
			verifyaddTrunk_existingNewDropdownField(application, PSPGname_existingFieldSelection, PSPGname_newFieldSelection, pspgName_existingValue,
					pspgName_newValue, "PSPSGname_Dropdown", "PSPGname_Checkbox", "PSPGname_TextField", "PSPG Name");
			
			
		//Preferred  PSP
			verifyaddTrunk_existingNewDropdownField(application, prefferedPSP_existingFieldSelection, prefferedPSP_newFieldSelection, preferredPSP_exitingvalue,
					preferredPSP_newvalue, "preferredPSP_Dropdown", "preferredPSP_checkbox", "preferredPSP_TextField", "Preferred PSP");
			

		//Carrier
			verifyaddTrunk_existingNewDropdownField(application, carrier_existingFieldSelection, carrier_newFieldSelection, carrier_existingValue,
					carrier_newValue, "carriers_Dropdown", "carriers_checkbox", "carriers_TextField", "Carrier");
			
			
			
		//IP Signalling Profile
			verifyaddTrunk_existingNewDropdownField(application, IPsignallingProfile_existingFieldSelection, IPsignallingProfile_newFieldSelection, IPsignallingProfile_existingValue,
					IPsignallingProfile_newValue, "IPsignallingProfile_Dropdown", "IPsignallingProfile_Checkbox", "IPsignallingProfile_textField", "IP Signaling Profile");
			
			
		//Egress IP Signaling Profile
			verifyaddTrunk_existingNewDropdownField(application, EgressIpsignal_existingFieldSelection, EgressIpsignal_newFieldSelection, EgressIPsignal_existingValue,
					EgressIPsignal_newValue, "EgressIpsignal_Dropdown", "EgressIPsignal_checkbox", "EgressipSignal_TextField", "Egress IP Signalling Profile");
			
		
		//In DM/PM rule
			verifyaddTrunk_existingNewDropdownField(application, InDMPMrule_existingFieldSelection, InDMPMrule_newFieldSelection, InDMPMrule_existingValue,
					InDMPMrule_newValue,  "InDMPMrule_Dropdown", "InDMPMrule_checkbox", "InDMPMrule_TextField", "In DM/PM rule");
			
			
		//Out DM/PM rule
			verifyaddTrunk_existingNewDropdownField(application, OutDMPMrule_existingFieldSelection, OutDMPMrule_newFieldSelection, OutDMPMrule_existingValue,
					OutDMPMrule_newValue, "OutDMPMrule_Dropdown", "OutDMPMrule_checkbox", "OutDMPMrule_TextField", "Out DM/PM rule");
			
			
		//Feature Control Profile	
			verifyaddTrunk_existingNewDropdownField(application, featureControlprofile_existingFieldSelection, featureControlprofile_newFieldSelection, featureControlprofile_existingValue,
					featureControlprofile_newValue, "featureControlprofile_Dropdown", "featureControlprofile_Checkbox", "featureControlprofile_TextField", "Feature Control Profile");
			
			
		//Local Ring Back Tone
			verifyaddTrunk_existingNewDropdownField(application, localRingBackTone_existingFieldSelection, localRingBackTone_newFieldSelection, localRingBackTone_existingValue,
					localRingBackTone_newValue, "localRingBackTone_Dropdown", "localRingBackTone_checkbox", "localRingBackTone_TextField", "Local Ring Back Tone");

		
	}
	
	
	public void editTrunk(String application,String customerName, String servicename,String trunktype, String edit_TrunkType, String edit_VOIPprotocol, String edit_BillingCountry, String edit_CDRdelivery,
			String editPrefix, String editGateway, String editQuality, String editTrafficDirection, String edit_IpAddressType, String editCarrierIPoriginating,
			String editCarrierIPterminating, String editSIPsignallingPort, String editSignallingTransport, String edit_TLSproflile, String edit_SRTP, String edit_signallingZone,
			String edit_coltSignalIP, String edit_mediaIP, String edit_reuseNIFgroup, String edit_reuseSigZonePart, String edit_callAdmissionControl, String edit_callLimit,
			String edit_limitNumber, String edit_callrateLimit, String edit_callrateLimitvalue, String edit_sourceAddressFiltering, String edit_relSupport,
			String edit_sipSessionkeepAliveTimer, String edit_internetBasedCustomer, String edit_vlantag, String edit_subInterfaceSlot, String edit_retryInvite,
			String edit_addressReachability, String edit_routePriority,
			String editglobalProfile_ExistingSelection, String editglobalProfile_newSelection, String editGlobalProfile_ExistingValue,String editGlobalProfile_newValue,
			String editLocalProfile_existingFieldSelection, String editLocalProfile_newFieldSelection, String editLocalProfile_existingvalue, String editLocalProfile_newvalue,
			String editCOSprofile_existingFieldSelection, String editCOSprofile_newFieldSelection, String editCOSprofile_existingValue, String editCOSprofile_newValue,
			String editPSPGname_existingFieldSelection, String editPSPGname_newFieldSelection, String editpspgName_existingValue, String editpspgName_newValue,
			String editPrefferedPSP_existingFieldSelection, String editPrefferedPSP_newFieldSelection, String editPreferredPSP_exitingvalue, String editPreferredPSP_newvalue,
			String editCarrier_existingFieldSelection, String editCarrier_newFieldSelection, String editCarrier_existingValue, String editCarrier_newValue,
			String editIPsignalProfile_existingFieldSelection, String editIPsignalProfile_newFieldSelection, String editIPsignalProfile_existingValue, String editIPsignalProfile_newValue,
			String editEgressIpsignal_existingFieldSelection, String editEgressIpsignal_newFieldSelection, String editEgressIPsignal_existingValue, String editEgressIPsignal_newValue,
			String editInDMPMrule_existingFieldSelection, String editInDMPMrule_newFieldSelection, String editInDMPMrule_existingValue, String editInDMPMrule_newValue,
			String editOutDMPMrule_existingFieldSelection, String editOutDMPMrule_newFieldSelection, String editOutDMPMrule_existingValue, String editOutDMPMrule_newValue,
			String editFeatureControlprofile_existingFieldSelection, String editFeatureControlprofile_newFieldSelection, String editFeatureControlprofile_existingValue, String editFeatureControlprofile_newValue,
			String editLocalRingBackTone_existingFieldSelection, String editLocalRingBackTone_newFieldSelection, String editLocalRingBackTone_existingValue, String editLocalRingBackTone_newValue,
			String editCreateLowerCaseRoutervalue, String edit_PSXmanualConfigvalue, String edit_GSXmanualConfigvalue, String edit_SBCmanualconfigValue,
			String billingCoutry) throws InterruptedException, DocumentException, IOException {        
		

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit Trunk' Functionality");
		
		String subInterfacename_starting="SIF-1-";
		String subInterfacename_middle="-2-";
		String NIFgroupDEfaultValue_starting="SIF-1-";
		String NIFgroupDEfaultValue_middle="-2-";
		String NIGgroupdefaultValue_last="-OUTSIDE";
		String ipInterfaceDEfaultValue="EXTERNAL_IPIF_";
		String addressContextDefaultValue="EXTERNAL_AC_";
		String ipInterfaceGroupDefaultvalue="EXTERNAL_IPIG_";
		String signallingZoneDefaultValue="OUT-UNTRUSTED";
		
		String trunktype_code=null;
		String trunkNameToBePassed=null;
		String prefix_code=null;
		String gatewayCode=null;
		String primarytrunk="0";
		
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(1000);
		
		scrollToTop();
		Thread.sleep(2000);
		
	//Action button	
		click_commonMethod(application, "Action", "viewPage_ActionButton", xml);
		
	//click on Edit link
		click_commonMethod(application, "Edit", "editLink", xml);
		
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);
		
		scrollToTop();
		
		//Trunk Type	
				selectValueInsideDropdown(application, "trunkType_Dropdown", "Trunk Type", edit_TrunkType, xml);
				
				if(edit_TrunkType.equalsIgnoreCase("null")) {
					trunktype_code=trunkType_code(application, trunktype);
					trunkNameToBePassed=trunktype;
				}else {
					trunktype_code=trunkType_code(application, edit_TrunkType);
					trunkNameToBePassed=edit_TrunkType;
				}
			
				
		//Trunk Group Description
				String expectedValue1=customerName+"_"+servicename+"_"+trunkNameToBePassed;
				compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

				
			//Billing COuntry
				selectValueInsideDropdown(application, "billingCoutry_Dropdown", "Billing Country", edit_BillingCountry, xml);
				
				String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
				Log.info("country dropdon value is: "+country_code);
				
			//VOIP Protocol
				selectValueInsideDropdown(application, "voipProtocol_Dropdown", "VOIP Protocol", edit_VOIPprotocol, xml);
				Thread.sleep(1000);
				String voipProtocol_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "voipProtocol_Dropdown", "VOIP Protocol");
				Thread.sleep(1000);
				Log.info("The voip protocol displaying is "+ voipProtocol_actualvalue);
				String voip_code=voipProtocol_code(application, voipProtocol_actualvalue);
				
			//CDR Delivery
				selectValueInsideDropdown(application, "CDRdelivery_Dropdown", "CDR Delivery", edit_CDRdelivery, xml);
				Thread.sleep(1000);
				String cdrDelivery_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "CDRdelivery_Dropdown", "CDR Delivery");
				Thread.sleep(1000);
				String CDR_code=CDRdelivery_code(application, cdrDelivery_actualvalue);
			
			//Prefix
					 edittextFields_commonMethod(application, "Prefix", "prefix_textField", editPrefix, xml);
					 Thread.sleep(1000);

			//valdate the prefix value for adding into Trunk group Name field	 
				 String preifxValueInsidetextField=getwebelement(xml.getlocator("//locators/" + application + "/prefix_textField")).getAttribute("value");
				
				 int prefixSize=preifxValueInsidetextField.length(); 
				 
				 String country=null;
				 if(edit_BillingCountry.equalsIgnoreCase("null")) {
					 country=billingCoutry;
				 }
				 else {
					 country=edit_BillingCountry;
				 }
				 
				 if(prefixSize==4) {
						prefix_code=preifxValueInsidetextField.substring(1);
					}else if(prefixSize<4) {
						Log.info("Prefix value cannot be less than 4");
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Prefix value cannot be less than 4. Value is displaying as "+preifxValueInsidetextField);
					}else if(prefixSize>4) {
						prefix_code=preifxValueInsidetextField.substring(1);
						Log.info("Prefix value cannot be greater than 4");
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Prefix value cannot be greater than 4. Value is displaying as "+preifxValueInsidetextField);
					}
					 
					 if((country.equals("NL (Netherlands)"))) {
						 prefix_code = "B" + prefix_code.substring(1);
					 }else {
						Log.info("No changes made in prefix code"); 
					 }
				 
			//Gateway
				selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", editGateway, xml);
				String gateway_actualValue=GetTheSelectedValueInsideDropdown_trunk(application, "gateway_Dropdown", "Gateway");
				gatewayCode=gateway_code(application, gateway_actualValue);
				
				WebElement trunk_type=getwebelement(xml.getlocator("//locators/" + application + "/trunkType_Dropdown"));
				scrolltoview(trunk_type);
				Thread.sleep(2000);
				
			//Quality
				selectValueInsideDropdown(application, "quality_Dropdown", "Quality", editQuality, xml);
				String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");
				
			//Trunk Group Name
				String trunGroup=country_code+gatewayCode+voip_code+trunktype_code+CDR_code+prefix_code+primarytrunk+quality_code;
				
				int totalLen=trunGroup.length();
				Log.info("Total lenth of 'Trunk Group' field is "+ totalLen);
				Log.info("----------------Trunk group name is "+ trunGroup);
				if(totalLen==13) {
					compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
					primarytrunkGroupname=trunGroup;
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Trunk Group NAme' length is: "+totalLen);
					compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunGroup, xml);
				}
				
			//Traffic Directions 
				selectValueInsideDropdown(application, "trafficDirection_Dropdown", "Traffic Directions", editTrafficDirection, xml);
				
			//IP Address Type
				selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", edit_IpAddressType, xml);
			
				WebElement traficDirection=getwebelement(xml.getlocator("//locators/" + application + "/trafficDirection_Dropdown"));
				scrolltoview(traficDirection);
				Thread.sleep(2000);
				
			//Carrier IP originating
				edittextFields_commonMethod(application, "Carrier IP Originating (Address/Mask)", "carrierIPoriginating_textField", editCarrierIPoriginating, xml);
				click_commonMethod(application, ">>", "carrierIPoriginating_addButtton", xml);
				GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPOriginating_addedValue_selectDropdownField")), "Carrier IP Originating (Address/Mask)");
				
				
			//Carrier IP Terminating
				edittextFields_commonMethod(application, "Carrier IP Terminating(Address)", "carrierIPterminating_textField", editCarrierIPterminating, xml);
				click_commonMethod(application, ">>", "carrierIPterminating_addButton", xml);
				GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPterminating_addedValue_selectDropdownField")), "Carrier IP Terminating (Address)");
			
			//SIP Signalling Port
			  if(edit_VOIPprotocol.equalsIgnoreCase("SIP")) {
				  edittextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", editSIPsignallingPort, xml);
						
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.PASS, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
				  Log.info("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
			  }
			  
			
			//Internet Based Customer
			  editcheckbox_commonMethod(application, edit_internetBasedCustomer, "internetBasedCustomer_checkbox", "Internet Based Customer", xml);
			  
		    
			//VLAN Tag
			  edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", edit_vlantag, xml);
			  String vlan_actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
			  
			  
			//Sub Interface Slot
			  selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", edit_subInterfaceSlot, xml);
			  Thread.sleep(1000);
			  String subIntercaeSlot_actualValue=GetTheSelectedValueInsideDropdown_trunk(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot");
			  
				 
			if(!gateway_actualValue.contains("SBC")) {
				
				if(subIntercaeSlot_actualValue.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					String SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlan_actualvalue;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group  
					String NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlan_actualvalue+NIGgroupdefaultValue_last;
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
				else if(!subIntercaeSlot_actualValue.equalsIgnoreCase("null")) {
					
					//Sub Interface Name
					String SubInterfaceName=subInterfacename_starting+subIntercaeSlot_actualValue+subInterfacename_middle+vlan_actualvalue;
					compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);
					
					//NIF Group
					String NIFgroup=NIFgroupDEfaultValue_starting+subIntercaeSlot_actualValue+NIFgroupDEfaultValue_middle+vlan_actualvalue+NIGgroupdefaultValue_last;
					Log.info("NIF Group value is: "+NIFgroup);
					compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
				}
			}
			else if(gateway_actualValue.contains("SBC")) {
				

				//IP Interface
				  String ipInterface = ipInterfaceDEfaultValue +vlan_actualvalue;
				  compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  
				  
			//Address Context
				  String addressContext=addressContextDefaultValue+vlan_actualvalue;
				  compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values
				
				  
			//IP Interface Group
				  String ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlan_actualvalue;
				  compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
		 
			}

			
			//Signalling Zone
			  edittextFields_commonMethod(application, "Signalling Zone", "signallingZone_textField", edit_signallingZone, xml);
			  
			  
			//Signalling Transport Protocol
			  	selectValueInsideDropdown(application, "signallingTransportProtocol_Dropdown", "Signalling Transport Protocol", editSignallingTransport, xml);
				
				WebElement internetBasedCusotmerView=getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox"));
				scrolltoview(internetBasedCusotmerView);
				Thread.sleep(2000);
				
				String signaltransport_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "signallingTransportProtocol_Dropdown", "Signalling Transport Protocol");
				if(signaltransport_actualvalue.equalsIgnoreCase("sip-tls-tcp")) {
					
					//TLS Profile
					edittextFields_commonMethod(application, "TLS Profile", "TLS_textField", edit_TLSproflile, xml);
					
					//SRTP
					editcheckbox_commonMethod(application, edit_SRTP, "srtp_checkbox", "SRTP", xml);
				}
				
			//Colt Signalling IP
				edittextFields_commonMethod(application, "Colt Signaling IP", "coltSignalingIP_textField", edit_coltSignalIP, xml);
				
			//Media IP
				edittextFields_commonMethod(application, "Media IP", "mediaIP_textField", edit_mediaIP, xml);
				
			//Reuse NIF Group
				editcheckbox_commonMethod(application, edit_reuseNIFgroup, "reuseNIFgroup_checkbox", "Reuse NIF Group", xml);
				
			//Reuse Sig Zone/Part
				editcheckbox_commonMethod(application, edit_reuseSigZonePart, "reuseSigZonePart_checkbox", "Reuse Sig Zone/Part", xml);
				
				
				//Call Admission Control
				editcheckbox_commonMethod(application, edit_callAdmissionControl, "callAdmissionControl_checkbox", "Call Admission Control", xml);
				
				boolean calladmisssion_actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callAdmissionControl_checkbox")).isSelected();
				
				if(calladmisssion_actualvalue) {
					
					//Call Limit
					selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", edit_callLimit, xml);
					String callimit_actualvalue=GetTheSelectedValueInsideDropdown_trunk(application, "callLimit_Dropdown", "CAll Limit");
					
					if(callimit_actualvalue.equalsIgnoreCase("Defined")) {
						edittextFields_commonMethod(application, "Limit Number", "limitNumber_textField", edit_limitNumber, xml);
					}
				}
				
			//Call Rate Limit
				editcheckbox_commonMethod(application, edit_callrateLimit, "callrateLimit_checkbox", "Call Rate Limit", xml);
				boolean callratelimit_actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callrateLimit_checkbox")).isSelected();
				
				if(callratelimit_actualvalue) {
					
					String callratelimitactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callRateLimitt_textField")).getAttribute("value");
					Log.info(" 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
				
					if(!edit_callrateLimitvalue.equalsIgnoreCase("null")) {
						int i=Integer.parseInt(edit_callrateLimitvalue);
							
						if(i>100) {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less 100 for all Trunks");
						}
						else if(i<=100){
							edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", edit_callrateLimitvalue, xml);
						}
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
						Log.info("'Call rate Limit' value is not edited");
					}
				}
				
				
			//Source Address Filtering
				selectValueInsideDropdown(application, "sourceAddressFiltering_Dropdown", "Source Address Filtering", edit_sourceAddressFiltering, xml);
				
			//100rel Support
				selectValueInsideDropdown(application, "relsupport_Dropdown", "100rel Support", edit_relSupport, xml);
				
			//SIP Session Keepalive Timer(Sec)
				edittextFields_commonMethod(application, "SIP Session Keepalive Timer(Sec)", "sipSessionKeepAliverTimer_textField", edit_sipSessionkeepAliveTimer, xml);
			
			//Retry Invite
				edittextFields_commonMethod(application, "Retry Invite", "retryinvite_textField", edit_retryInvite, xml);
				
			//Text message under "Retry Invite" field
				methodToFindMessagesUnderTextField(application, "defaultTextMessageUnderretryInvite", "Retry Invite", "Default Retry Invite :2");
				
			//Route Priority
				selectValueInsideDropdown(application, "routepriority_Dropdown", "Route Priority", edit_routePriority, xml);
				
			//Address Reachability
				selectValueInsideDropdown(application, "addressReachability_Dropdown", "Address Reachability", edit_addressReachability, xml);
				
				WebElement retryinviteView=getwebelement(xml.getlocator("//locators/" + application + "/retryinvite_textField"));
				scrolltoview(retryinviteView);
				Thread.sleep(2000);
			
			//E164GlobalProfile
				editTrunk_existingNewDropdownField(application, editglobalProfile_ExistingSelection, editglobalProfile_newSelection, editGlobalProfile_ExistingValue, 
						editGlobalProfile_newValue, "globalProfile_Dropdown" , "globalProfile_Checkbox", "globalProfile_textField", "E164GlobalProfile");
				
			//E164LocalProfile	
				editTrunk_existingNewDropdownField(application, editLocalProfile_existingFieldSelection, editLocalProfile_newFieldSelection, editLocalProfile_existingvalue,
						editLocalProfile_newvalue, "localProfile_Dropdown", "localProfile_checkbox", "localProfile_TextField", "E164LocalProfile");
				
				 
			//COS profile
				editTrunk_existingNewDropdownField(application, editCOSprofile_existingFieldSelection, editCOSprofile_newFieldSelection, editCOSprofile_existingValue,
						editCOSprofile_newValue, "COSprofile_Dropdown", "COSprofile_checkbox", "COSprofile_TextField", "COS Profile");
				
							
			//PSPG Name
				editTrunk_existingNewDropdownField(application, editPSPGname_existingFieldSelection, editPSPGname_newFieldSelection, editpspgName_existingValue,
						editpspgName_newValue, "PSPSGname_Dropdown", "PSPGname_Checkbox", "PSPGname_TextField", "PSPG Name");
				
				
			//Preferred  PSP
				editTrunk_existingNewDropdownField(application, editPrefferedPSP_existingFieldSelection, editPrefferedPSP_newFieldSelection, editPreferredPSP_exitingvalue,
						editPreferredPSP_newvalue, "preferredPSP_Dropdown", "preferredPSP_checkbox", "preferredPSP_TextField", "Preferred PSP");
				
				
				
				WebElement globalProfileView=getwebelement(xml.getlocator("//locators/" + application + "/globalProfile_textField"));
				scrolltoview(globalProfileView);
				Thread.sleep(2000);
				
			//Carrier
				editTrunk_existingNewDropdownField(application, editCarrier_existingFieldSelection, editCarrier_newFieldSelection, editCarrier_existingValue,
						editCarrier_newValue, "carriers_Dropdown", "carriers_checkbox", "carriers_TextField", "Carrier");
				
			
			//IP Signalling Profile
				editTrunk_existingNewDropdownField(application, editIPsignalProfile_existingFieldSelection, editIPsignalProfile_newFieldSelection, editIPsignalProfile_existingValue,
						editIPsignalProfile_newValue, "IPsignallingProfile_Dropdown", "IPsignallingProfile_Checkbox", "IPsignallingProfile_textField", "IP Signaling Profile");
				
	
			//Egress IP Signaling Profile
				editTrunk_existingNewDropdownField(application, editEgressIpsignal_existingFieldSelection, editEgressIpsignal_newFieldSelection, editEgressIPsignal_existingValue,
						editEgressIPsignal_newValue, "EgressIpsignal_Dropdown", "EgressIPsignal_checkbox", "EgressipSignal_TextField", "Egress IP Signaling Profile");
				
				scrolltoend();
				Thread.sleep(2000);
				
			
			//In DM/PM rule
				editTrunk_existingNewDropdownField(application, editInDMPMrule_existingFieldSelection, editInDMPMrule_newFieldSelection, editInDMPMrule_existingValue,
						editInDMPMrule_newValue,  "InDMPMrule_Dropdown", "InDMPMrule_checkbox", "InDMPMrule_TextField", "In DM/PM rule");
				
				
			//Out DM/PM rule
				editTrunk_existingNewDropdownField(application, editOutDMPMrule_existingFieldSelection, editOutDMPMrule_newFieldSelection, editOutDMPMrule_existingValue,
						editOutDMPMrule_newValue, "OutDMPMrule_Dropdown", "OutDMPMrule_checkbox", "OutDMPMrule_TextField", "Out DM/PM rule");
				
		
			//Feature Control Profile	
				editTrunk_existingNewDropdownField(application, editFeatureControlprofile_existingFieldSelection, editFeatureControlprofile_newFieldSelection, editFeatureControlprofile_existingValue,
						editFeatureControlprofile_newValue, "featureControlprofile_Dropdown", "featureControlprofile_Checkbox", "featureControlprofile_TextField", "Feature Control Profile");
				
				
			//Local Ring Back Tone
				editTrunk_existingNewDropdownField(application, editLocalRingBackTone_existingFieldSelection, editLocalRingBackTone_newFieldSelection, editLocalRingBackTone_existingValue,
						editLocalRingBackTone_newValue, "localRingBackTone_Dropdown", "localRingBackTone_checkbox", "localRingBackTone_TextField", "Local Ring Back Tone");
			
				
			//Create Lower Case Routes
				editcheckbox_commonMethod(application, editCreateLowerCaseRoutervalue, "createLowerCaseRoute_checkbox", "Create Lower Case Routes", xml);
				
				
			//PSX Manual Configuration	
				editcheckbox_commonMethod(application, edit_PSXmanualConfigvalue, "PSXmanualConfig_checkbox", "PSX Manual Configuration", xml);
				
			//Manual Configuration On GSX
				
		
				if(gateway_actualValue.contains("SBC")) {
					//Manual Configuration on SBC
					editcheckbox_commonMethod(application, edit_SBCmanualconfigValue, "SBCmanualconfig_checkbox", "Manual Configuration On SBC", xml);
				}else {
					//Manual Configuration On GSX
					editcheckbox_commonMethod(application, edit_GSXmanualConfigvalue, "GSXmanualConfig_checkbox", "Manual Configuration On GSX", xml);
				}
				
				
				scrolltoend();
				Thread.sleep(1000);
				
				click_commonMethod(application, "OK", "OKbutton", xml);
				
	//			
					
				
	}
	
	
	public void fetchDisabledFieldValue(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
		
//		String actualvalue=null;
		WebElement ele=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		
		boolean elementEnabled=ele.isEnabled();
		
		if(elementEnabled) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is disabled");
			Log.info(labelname + " is disabled");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, labelname + "is disabled as expected");
			Log.info(labelname + "is disabled as expected");
			
//			actualvalue=ele.getAttribute("value");
//			ExtentTestManager.getTest().log(LogStatus.PASS, "Value for "+ labelname+ " field is displaying as:"+actualvalue);
//			Log.info("Value for "+ labelname+ " field is displaying as:"+actualvalue);
		}
	}
	
	
	
	public void addResilienttrunk(String application, String customerName, String servicename, String trunkType, String voipProtocol, String country, String CDRdelivery, String gateway, String quality,
			String trafficDirection, String ipAddresstype, String SIPsignallingPort, String internetBasedCustomer, String vlanTag, String subInterfaceSlot,
			String signallngZone, String signallingtransportProtocol, String coltSignalingIP, String mediaIP, String reuseNIFgroup, String reuseSigZonePart,
			String callAdmissionControl, String callrateLimit, String sourceAddressFiltering, String relSupport, String sipSessionkeepAliveTimer,
			String retryInvite, String routePriority, String addressReachability, String carrierIPoriginating, String carrierIPterminating, String TLSfield,
			String srtp, String prefix, String globalProfile_ExistingSelection, String globalProfile_newSelection, String globalProfile_ExistingValue, String globalProfile_newValue,
			String localProfile_existingvalue, String localProfile_newvalue,String COSprofile_existingValue, String COSprofile_newValue,String pspgName_existingValue, 
			String pspgName_newValue,String preferredPSP_exitingvalue, String preferredPSP_newvalue,String carrier_existingValue, String carrier_newValue, 
			String IPsignallingProfile_existingValue, String IPsignallingProfile_newValue,String EgressIPsignal_existingValue, String EgressIPsignal_newValue,
			String InDMPMrule_existingValue, String InDMPMrule_newValue,String OutDMPMrule_existingValue, String OutDMPMrule_newValue,String featureControlprofile_existingValue, 
			String featureControlprofile_newValue,String localRingBackTone_existingFieldSelection, String localRingBackTone_newFieldSelection, String localRingBackTone_existingValue, String localRingBackTone_newValue,
			String createLowerCaseRoutervalue,String PSXmanualConfigvalue, String GSXmanualConfigvalue, String callLimit, String limitNumber, String callrateLimiteValue,
			
			String gateway_resilientTrunk, String voip_resilientTrunk, String trafficDirection_resiltrunk, String ipAddressType_resilTrunk, String carierIPOrient_resiltrunk,
			String carierIPterminat_resiltrunk) throws IOException, InterruptedException, DocumentException {
		
		
		WebElement hyperLink=getwebelement("//a[text()='"+ servicename +"']");
		click_commonMethod_PassingWebelementDirectly(application, "Sevice name hyperlink","hyperLink" , xml);
		Thread.sleep(1000);
		
		scrolltoend();
		Thread.sleep(2000);
		
		click_commonMethod(application, "OK", "OKbutton", xml);
		
		scrollToTop();
		Thread.sleep(2000);
		
		//Primary Trunk Group
		Log.info("Primary trunk group value: "+primarytrunkGroupname);
		selectValueInsideDropdown(application, "primaryTrunkGroup_Dropdown", "Primary Trunk Group" , primarytrunkGroupname, xml);
		Thread.sleep(2000);
		
		//Trunk Type
		fetchDisabledFieldValue(application, "Trunk Type", "trunkType_Dropdown");
		String trunktypeSelected=GetTheSelectedValueInsideDropdown_trunk(application, "trunkType_Dropdown", "Trunk Type");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'Trunk Type' field is displaying as:"+trunktypeSelected);
		Log.info("Value for 'Trunk Type' field is displaying as:"+trunktypeSelected);
		
		//VOIP Protocol
		fetchDisabledFieldValue(application, "VOIP Protocol", "voipProtocol_Dropdown");
		String voipProtocolSelected=GetTheSelectedValueInsideDropdown_trunk(application, "voipProtocol_Dropdown", "VOIP Protocol");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'VOIP Protocol' field is displaying as:"+voipProtocolSelected);
		Log.info("Value for 'VOIP Protocol' field is displaying as:"+voipProtocolSelected);
		
		selectValueInsideDropdown(application, "voipProtocol_Dropdown", "VOIP Protocol", voip_resilientTrunk, xml);
		String voipProtocoledited=GetTheSelectedValueInsideDropdown_trunk(application, "voipProtocol_Dropdown", "VOIP Protocol");  //select value under dropdown
		
		
		//Billing Country
		fetchDisabledFieldValue(application, "Billing Country", "billingCoutry_Dropdown");
		String coutrySelected=GetTheSelectedValueInsideDropdown_trunk(application, "billingCoutry_Dropdown", "Billing Country");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'Billing Country' field is displaying as:"+coutrySelected);
		Log.info("Value for 'Billing Country' field is displaying as:"+coutrySelected);
		
		//CDR Delivery
		fetchDisabledFieldValue(application, "CDR Delivery", "CDRdelivery_Dropdown");
		String cdrSelected=GetTheSelectedValueInsideDropdown_trunk(application, "CDRdelivery_Dropdown", "CDR Delivery");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'CDR Delivery' field is displaying as:"+cdrSelected);
		Log.info("Value for 'CDR Delivery' field is displaying as:"+cdrSelected);
		
		//Prefix
		fetchDisabledFieldValue(application, "Prefix", "prefix_textField");
		String prefixSelected=GetTheSelectedValueInsideDropdown_trunk(application, "prefix_textField", "Prefix");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'Prefix' field is displaying as:"+prefixSelected);
		Log.info("Value for 'Prefix' field is displaying as:"+prefixSelected);
		
		
		//Gateway
		fetchDisabledFieldValue(application, "Gateway", "gateway_Dropdown");
		String gatewaySelected=GetTheSelectedValueInsideDropdown_trunk(application, "gateway_Dropdown", "Gateway");
		selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", gateway_resilientTrunk, xml);
		
		//Quality
		fetchDisabledFieldValue(application, "Quality", "quality_Dropdown");
		String qualitySelected=GetTheSelectedValueInsideDropdown_trunk(application, "quality_Dropdown", "Quality");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value for 'Prefix' field is displaying as:"+qualitySelected);
		Log.info("Value for 'Prefix' field is displaying as:"+qualitySelected);
		
		
		//Trunk Group Name
		
		
		//Traffic Direction
		fetchDisabledFieldValue(application, "Traffic Direction", "trafficDirection_Dropdown");
		GetTheSelectedValueInsideDropdown_trunk(application, "trafficDirection_Dropdown", "Traffic Direction");
		selectValueInsideDropdown(application, "trafficDirection_Dropdown", "Traffic Direction", trafficDirection_resiltrunk, xml);
		
		
	//IP Address Type
		if(voipProtocoledited.equalsIgnoreCase("SIP")) {
			fetchDisabledFieldValue(application, "IP Address Type", "IPaddresstype_Dropdown");
			GetTheSelectedValueInsideDropdown_trunk(application, "IPaddresstype_Dropdown", "IP Address Type");
			selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", ipAddressType_resilTrunk, xml);
		}
		else if(voipProtocoledited.equalsIgnoreCase("SIP-I")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "'IP Address type' dropdown should not display, if 'VOIP Protocol' selected as 'SIP-I'");
			try {
			boolean ipaddres=getwebelement(xml.getlocator("//locators/" + application + "/IPaddresstype_Dropdown")).isDisplayed();
			if(ipaddres) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'IP Address type' is displaying");
				Log.info("'IP Address type' is displaying");
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'IP Address type' is not displaying");
				Log.info("'IP Address type' is not displaying");
			}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'IP Address type' is not displaying");
				Log.info("'IP Address type' is not displaying");
			}
		}
		
		
		//Carrier IP originating
			addtextFields_commonMethod(application, "Carrier IP Originating (Address/Mask)", "carrierIPoriginating_textField", carierIPOrient_resiltrunk, xml);
			click_commonMethod(application, ">>", "carrierIPoriginating_addButtton", xml);
			GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPOriginating_addedValue_selectDropdownField")), "Carrier IP Originating (Address/Mask)");
				
				
		//Carrier IP Terminating
			addtextFields_commonMethod(application, "Carrier IP Terminating(Address)", "carrierIPterminating_textField", carierIPterminat_resiltrunk, xml);
			click_commonMethod(application, ">>", "carrierIPterminating_addButton", xml);
			GetTheValuesInsideDropdown(getwebelement(xml.getlocator("//locators/" + application + "/carrierIPterminating_addedValue_selectDropdownField")), "Carrier IP Terminating (Address)");
				
		//SIP Signalling Port
				if(voipProtocoledited.equalsIgnoreCase("SIP")) {
					  addtextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", SIPsignallingPort, xml);
					  
					  //message displaying under "SIP Signalling Port" text field	
						methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port:5060");
				  }
				else if(voipProtocoledited.equalsIgnoreCase("SIP-I")) {
					  ExtentTestManager.getTest().log(LogStatus.INFO, "'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-1'");
					  Log.info("'SIP Signalling Port' text field will not display, if 'VOIP Protocol' selected as 'SIP-I'");
					  
					  try {
							boolean signalPort=getwebelement(xml.getlocator("//locators/" + application + "/SIPsignallingport_textField")).isDisplayed();
							if(signalPort) {
								ExtentTestManager.getTest().log(LogStatus.FAIL, "'SIP Signalling Port' is displaying");
								Log.info("'SIP Signalling Port' is displaying");
							}else {
								ExtentTestManager.getTest().log(LogStatus.FAIL, "'SIP Signalling Port' is not displaying");
								Log.info("'SIP Signalling Port' is not displaying");
							}
							}catch(Exception e) {
								e.printStackTrace();
								ExtentTestManager.getTest().log(LogStatus.FAIL, "'SIP Signalling Port' is not displaying");
								Log.info("'SIP Signalling Port' is not displaying");
							}
				  }
			
			
			 
		
		
	}
	
	public String GetTheSelectedValueInsideDropdown_trunk(String application , String xpath, String labelname) throws IOException, InterruptedException, DocumentException
	{ //Thread.sleep(3000);
	
		String selectedValue=null;
		
	try {	
		WebElement el=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		
		Select s1=new Select(el);
		WebElement option = s1.getFirstSelectedOption();
//		Log.info(option);
		selectedValue = option.getText();	
		
		
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
		Log.info(labelname + " field is not displaying");
	}
		
		return selectedValue;
	
	}
	
	public void GetTheValuesInsideDropdown(WebElement el, String labelname) throws IOException, InterruptedException
	{ //Thread.sleep(3000);
		
		List<String> ls = new ArrayList<String>();
		Select sel=new Select(el);
		 List<WebElement> we = sel.getOptions();
		   
		    for(WebElement a : we)
		    {
		        if(!a.getText().equals("select")){
		            ls.add(a.getText());
		            
		        }
		    }
		
		Log.info("Value displaying under "+labelname+" is: "+ ls);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value displaying under "+labelname+" is: "+ ls);
	
	}	
	
	
	/**
	 *  For Comparing the values 
	 * @param application
	 * @param labelname
	 * @param xpath
	 * @param ExpectedText
	 * @param xml
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void compareText_fromtextFields(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

		WebElement element = null;

		try {
			Thread.sleep(1000);
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			String emptyele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			}
			else if (emptyele!=null && emptyele.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
			}else 
			{   
				if(emptyele.equals(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+emptyele+"'");
				}
				else if(emptyele.contains(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+emptyele+"'");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: The ExpectedText for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal Text '"+emptyele+"'");
				}
			}
		}catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			Log.info(labelname + " field is not displaying");
			e.printStackTrace();
		}

	}

	/**
	 * select IMS location for MAS switch	
	 * @param application
	 * @param MAS_IMSPOPLocation
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void verifyAddMASswitch(String application, String MAS_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add MAS Switch' Functionality");
		
		WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
		scrolltoview(managementOptions_header);
		
		
		click_commonMethod(application, "Add MAS switch link", "addMASswitch_link", xml);  //click on MAS switch link
		
		compareText(application, "Add MAS Switch header", "MAS_AddMASSwitch_header", "Add MAS Switch", xml);  //compare MAS switch Header
		

		click_commonMethod(application, "OK", "trunk_okButton", xml);
		warningMessage_commonMethod(application, "MASswitch_warningmessage", "IMS POP Location", xml);
		
		addDropdownValues_commonMethod(application, "IMS POP Location", "AddmasSWitch_IMSppswitch_dropdown", MAS_IMSPOPLocation, xml);
		
		click_commonMethod(application, "OK", "trunk_okButton", xml);
		Thread.sleep(4000);
		
		
		Log.info("------ MAS Switch added successfully ------");
	}
	
	
	/**
	 *  select location for create PE device
	 * @param application
	 * @param MAS_IMSPOPLocation
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void verifyPEdevice(String application, String PE_IMSPOPLocation) throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add PE Device' Functionality");
		
		WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
		scrolltoview(managementOptions_header);
		
		
		click_commonMethod(application, "Add PE device link", "addPEdevice_link", xml);  //click on PE device link
		
		compareText(application, "Add PE device", "PE_addPEdeviceHeaderName", "Add PE Device", xml);    //compare PE device Header name

		click_commonMethod(application, "OK", "trunk_okButton", xml);
		
		warningMessage_commonMethod(application, "PEdevice_IMSlocation_warningmessage", "IMS POP Location", xml);
		
		addDropdownValues_commonMethod(application, "IMS POP Location", "AddmasSWitch_IMSppswitch_dropdown", PE_IMSPOPLocation, xml);
		
		click_commonMethod(application, "OK", "trunk_okButton", xml);
		Thread.sleep(4000);
		

		Log.info("------ MAS Switch added successfully ------");
	}
	
	
	/**
	 *  Common Method, can be used for PE device and MAS switch. Verifying values under view device page
	 * @param application
	 * @param MAS_IMSPOPLocation
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void verifyAddedMASswitchInformation_View(String application) throws InterruptedException, DocumentException, IOException {
		
		
		waitForpageload();
		
		scrollToTop();
		Thread.sleep(1000);

		// Verify All Device Information under Device panel for MAS Switch
		GetText(application, "Device Name", "MAS_View_DeviceNameValue");
		GetText(application, "Vendor/Model", "MAS_View_VendorModelValue");
		GetText(application, "Management Address", "MAS_View_ManagementAddressValue");
		GetText(application, "Snmpro", "MAS_View_SnmproValue");
		GetText(application, "Country", "MAS_View_CountryValue");
		GetText(application, "City", "MAS_View_CityValue");
		GetText(application, "Site", "MAS_View_SiteValue");
		GetText(application, "Premise", "MAS_View_PremiseValue");
		
		
	//verify links under action dropodown	
		click_commonMethod(application, "Action", "MAS_View_ActionLink", xml);

		GetText(application, "Edit Link", "MAS_View_Action_EditLink");
		GetText(application, "Delete Link", "MAS_View_Action_DeleteLink");
		GetText(application, "Fetch Device Interfaces Link", "MAS_View_Action_FetchDeviceInterfacesLink");

		
		clickOnBankPage();
		Thread.sleep(1000);
		
	}
	
	
	public void editMASdevice(String application, String editDeviceName, String editVendorModel, String editmanageAddress,
			String editSnmpro, String editCountry,
			String editExistingCity, String editNewCity, String editExistingCityValue, String editNewCityName, String editNewCityCode,
			String editExistingSite, String editNewSite, String editExistingSiteValue, String editNewSiteName, String editNewSiteCode,
			String editExistingPremise, String editNewPremise, String editExistingPremiseValue, String editNewPremiseName, String editNewPremiseCode) 
					throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		Thread.sleep(1000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit MAS Switch' Functionality");
		
		click_commonMethod(application, "Action", "MAS_View_ActionLink", xml);

		click_commonMethod(application, "Edit link", "MAS_View_Action_EditLink", xml);
		
		
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(3000);
		
	//Device name	
		edittextFields_commonMethod(application, "Device name", "MAS_deviceName", editDeviceName, xml);
		
	//vendor/model	
		addDropdownValues_commonMethod_ForSpantag(application, "Vendor/Model", "MAS_vendorModel", editVendorModel, xml);
		
	//Management Address	
		edittextFields_commonMethod(application, "Management Address", "MAS_managementAddress", editmanageAddress, xml);
		
	//Snmrpo	
		edittextFields_commonMethod(application, "Snmpro", "MAS_snmpro", editSnmpro, xml);
	
		scrolltoend();
		Thread.sleep(2000);
		
		//Country
				if(!editCountry.equalsIgnoreCase("Null")) {
					
//					addDropdownValues_commonMethod(application, "Country", "countryinput", editCountry, xml);
					selectValueInsideDropdown(application, "countryinput", "Country", editCountry, xml);
					
					//New City		
					if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("yes")) {
						Clickon_addToggleButton(application, "addcityswitch");
					   //City name
						addtextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
					   //City Code	
						addtextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
					   //Site name
						addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
					   //Site Code
						addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", editNewSiteCode, xml);
					   //Premise name	
						addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
					   //Premise Code	
						addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);
							
					}	
				
				//Existing City	
					else if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("no")) {
						
//					   addDropdownValues_commonMethod(application, "City", "citydropdowninput", editExistingCityValue, xml);
					   selectValueInsideDropdown(application, "citydropdowninput", "City", editExistingCityValue, xml);
						
						
					 //Existing Site
						  if(editExistingSite.equalsIgnoreCase("yes") & editNewSite.equalsIgnoreCase("no")) {
//							  addDropdownValues_commonMethod(application, "Site", "sitedropdowninput", editExistingSiteValue, xml);
							  selectValueInsideDropdown(application, "sitedropdowninput", "Site", editExistingSiteValue, xml);
							  
						 //Existing Premise
							  if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("no")) {
//								  addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", editExistingPremiseValue, xml);
								  selectValueInsideDropdown(application, "premisedropdowninput", "Premise", editExistingPremiseValue, xml);
					          	 }
							  
							//New Premise  
							  else if(editExistingPremise.equalsIgnoreCase("no") & editNewPremise.equalsIgnoreCase("yes")) {
								  
								  Clickon_addToggleButton(application, "addpremiseswitch");
								  //Premise name	
									addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", editNewPremiseName, xml);
								   //Premise Code	
									addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", editNewPremiseCode, xml);
							  } 
				         	}
			  		
					  else if(editExistingSite.equalsIgnoreCase("no") & editNewSite.equalsIgnoreCase("yes")) {
						  	
						  Clickon_addToggleButton(application, "addsiteswitch");
						  //Site name
							addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", editNewSiteName, xml);
						   //Site Code
							addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", editNewSiteCode, xml);
						   //Premise name	
							addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", editNewPremiseName, xml);
						   //Premise Code	
							addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", editNewPremiseCode, xml);
						  }
					}
					
				}
				else if(editCountry.equalsIgnoreCase("Null")) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " No changes made for 'Country' dropdown");
				
				//City	
					editCity(application, editExistingCity, editNewCity, "citydropdowninput", "selectcityswitch", "addcityswitch",
							editExistingCityValue, editNewCityName, editNewCityCode, "City");
					
					
				//Site	
					editSite(application, editExistingSite, editNewSite, "sitedropdowninput", "selectsiteswitch",
							"addsiteswitch", editExistingSiteValue , editNewSiteName, editNewSiteCode, "Site");
					
				//Premise
					editPremise(application, editExistingPremise, editNewPremise, "premisedropdowninput", "selectpremiseswitch",
							"addpremiseswitch", editExistingPremiseValue, editNewPremiseName, editNewPremiseCode, "Premise");
					
				}

		scrolltoend();
		Thread.sleep(2000);
		click_commonMethod(application, "OK", "trunk_okButton" , xml);
		
	}
	

	/**
	 * In view device page, this method is used to find the test commands and its status
	 * @param application
	 * @throws InterruptedException
	 */
	public void testStatus(String application) throws InterruptedException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Test Status' table");
		
		String element=null;
		String status=null;
		
		List<WebElement> testlist=getwebelements("//tbody/tr");
		int listSize=testlist.size();
		
		
		for(int i=1; i<=listSize; i++) {
		  try {	
			element=getwebelement("(//tbody/tr["+ i +"]/td)[1]").getText();
			
			if(element.isEmpty()) {
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Test Name is displaying as: "+element);
				Log.info("Test Name is displaying as: "+element);
				
				
				status=getwebelement("(//tbody/tr["+ i +"]/td)[2]/div").getAttribute("class");
				Log.info("status displays as: "+status);
				
				if(status.contains("red")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: red");
					Log.info(element + " status colour dipslays as: red");
				}
				else if(status.contains("green")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: green");
					Log.info(element + " status colour dipslays as: green");
				}
			}
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		}
	}


public void Clickon_addToggleButton(String application, String xpath) throws InterruptedException, DocumentException {
	
	//Add Toggle button
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
	Thread.sleep(5000);
}



public void editPremise(String application, String editExistingPremise, String editNewPremise, String dropdown_xpath, String selectPremiseToggleButton,
		String addPremisetoggleButton, String dropdownValue, String editNewPremiseName, String editNewPremiseCode, String labelname) throws InterruptedException, DocumentException, IOException {
	
	if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("no")) {
		
		existingPremise(application, dropdown_xpath, dropdownValue, selectPremiseToggleButton, labelname);
		
	}
	
	else if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("null")) {
		
		existingPremise(application, dropdown_xpath, dropdownValue, selectPremiseToggleButton, labelname);
		
	}
	
	else if(editExistingPremise.equalsIgnoreCase("no") & editNewPremise.equalsIgnoreCase("Yes")) {
		
		newPremise(application, dropdown_xpath, addPremisetoggleButton, editNewPremiseName, editNewPremiseCode, labelname);
		
	}
	
	else if(editExistingPremise.equalsIgnoreCase("null") & editNewPremise.equalsIgnoreCase("Yes")) {
		
		newPremise(application, dropdown_xpath, addPremisetoggleButton, editNewPremiseName, editNewPremiseCode, labelname);
		
	}
	
	else if(editExistingPremise.equalsIgnoreCase("null") & editNewPremise.equalsIgnoreCase("null")) {
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made under 'Premise' field");
		Log.info("No changes made under 'Premise' field");
		
	}
	
}

public void existingPremise(String application, String dropdown_xpath, String dropdownValue, String selectPremiseToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
	
	boolean premiseDisplayed=false;
try {	
	premiseDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	
	if(premiseDisplayed) {
		
//		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
		
	}else {
		
		click_commonMethod(application, "Select Premise toggle button", selectPremiseToggleButton, xml);
		Thread.sleep(1000);
		
//		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
		
	}
}catch(Exception e) {
	e.printStackTrace();
	
	click_commonMethod(application, "Select Premise toggle button", selectPremiseToggleButton, xml);
	
//	addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
}
	
}


public void newPremise(String application, String dropdown_xpath, String addPremisetoggleButton, String editNewPremiseName,
		String editNewPremiseCode, String labelname) throws InterruptedException, DocumentException, IOException {
	
	boolean premiseDisplayed=false;
try {	
	premiseDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	
	if(premiseDisplayed) {
		
		click_commonMethod(application, "Select Premise toggle button", addPremisetoggleButton, xml);
		Thread.sleep(1000);
		
		//Premise name
		edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", editNewPremiseName, xml);
		
	   //Premise Code	
		edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", editNewPremiseCode, xml);
		
	}else {
		
		//Premise name
		edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
		
	   //Premise Code	
		edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);
		
	}
}catch(Exception e) {
	e.printStackTrace();
	
	//Premise name
	edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
	
   //Premise Code	
	edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);
	
}
	
}


public void editSite(String application, String editExistingCity, String editNewCity, String dropdown_xpath, String selectSiteToggleButton,
		String addSitetoggleButton, String dropdownValue, String editNewSiteName, String editNewSiteCode, String labelname) throws InterruptedException, DocumentException, IOException {
	
	if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("no")) {
		
		existingSite(application, dropdown_xpath, dropdownValue, selectSiteToggleButton, labelname);
		
	}
	
	else if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("null")) {
		
		existingSite(application, dropdown_xpath, dropdownValue, selectSiteToggleButton, labelname);
		
	}
	
	else if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("Yes")) {
		
		newSite(application, dropdown_xpath, addSitetoggleButton, editNewSiteName, editNewSiteCode, labelname);
		
	}
	
	else if(editExistingCity.equalsIgnoreCase("null") & editNewCity.equalsIgnoreCase("Yes")) {
		
		newSite(application, dropdown_xpath, addSitetoggleButton, editNewSiteName, editNewSiteCode, labelname);
		
	}
	
	else if(editExistingCity.equalsIgnoreCase("null") & editNewCity.equalsIgnoreCase("null")) {
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made under 'Site' field");
		Log.info("No changes made under 'Site' field");
		
	}
	
}

public void existingSite(String application, String dropdown_xpath, String dropdownValue, String selectSiteToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
	
	boolean siteDisplayed=false;
try {	
	siteDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	
	if(siteDisplayed) {
		
//		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
		
	}else {
		
		click_commonMethod(application, "Select Site toggle button", selectSiteToggleButton, xml);
		Thread.sleep(1000);
		
//		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
		
	}
}catch(Exception e) {
	e.printStackTrace();
	
	click_commonMethod(application, "Select Site toggle button", selectSiteToggleButton, xml);
	
//	addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
}
	
}


public void newSite(String application, String dropdown_xpath, String addSitetoggleButton, String editNewSiteName,
		String editNewSiteCode, String labelname) throws InterruptedException, DocumentException, IOException {
	
	boolean siteDisplayed=false;
try {	
	siteDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	
	if(siteDisplayed) {
		
		click_commonMethod(application, "Select City toggle button", addSitetoggleButton, xml);
		Thread.sleep(1000);
		
		//Site name
		edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", editNewSiteName, xml);
		
	   //Site Code	
		edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", editNewSiteCode, xml);
		
	}else {
		
		//Site name
		edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
		
	   //Site Code	
		edittextFields_commonMethod(application, "Site Code", "sitenameinputfield_addCityToggleSelected", editNewSiteCode, xml);
		
	}
}catch(Exception e) {
	e.printStackTrace();
	
	//Site name
	edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
	
   //Site Code	
	edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", editNewSiteCode, xml);
	
}
	
}


public void editCity(String application, String editExistingCity, String editNewCity, String dropdown_xpath, String selectCityToggleButton,
		String addCityToggleButton, String dropdownValue, String editNewCityName, String editNewCityCode, String labelname) throws InterruptedException, DocumentException, IOException {
	
	if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("no")) {
		
		existingCity(application, dropdown_xpath, dropdownValue, selectCityToggleButton, labelname);
		
	}
	
	else if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("null")) {
		
		existingCity(application, dropdown_xpath, dropdownValue, selectCityToggleButton, labelname);
		
	}
	
	else if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("Yes")) {
		
		newCity(application, dropdown_xpath, addCityToggleButton, editNewCityName, editNewCityCode, labelname);
		
	}
	
	else if(editExistingCity.equalsIgnoreCase("null") & editNewCity.equalsIgnoreCase("Yes")) {
		
		newCity(application, dropdown_xpath, addCityToggleButton, editNewCityName, editNewCityCode, labelname);
		
	}
	
	else if(editExistingCity.equalsIgnoreCase("null") & editNewCity.equalsIgnoreCase("null")) {
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "No chnges made under 'City' field");
		Log.info("No chnges made under 'City' field");
	}
	
}


public void existingCity(String application, String dropdown_xpath, String dropdownValue, String selectCityToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
	
	boolean cityDisplayed=false;
try {	
	cityDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	
	if(cityDisplayed) {
		
//		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
		
	}else {
		
		click_commonMethod(application, "Select City toggle button", selectCityToggleButton, xml);
		Thread.sleep(1000);
		
//		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
		selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
		
	}
}catch(Exception e) {
	e.printStackTrace();
	
	click_commonMethod(application, "Select City toggle button", selectCityToggleButton, xml);
	
//	addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	selectValueInsideDropdown(application, dropdown_xpath, labelname, dropdownValue, xml);
}
	
}


public void newCity(String application, String dropdown_xpath, String addCitytoggleButton, String editNewCityName,
		String editNewCityCode, String labelname) throws InterruptedException, DocumentException, IOException {
	
	boolean cityDisplayed=false;
try {	
	cityDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	
	if(cityDisplayed) {
		
		click_commonMethod(application, "Select City toggle button", addCitytoggleButton, xml);
		Thread.sleep(1000);
		
		//City name
		edittextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
		
	   //City Code	
		edittextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
		
	}else {
		
		//City name
		edittextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
		
	   //City Code	
		edittextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
	}
}catch(Exception e) {
	e.printStackTrace();
	
	//City name
	edittextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
	
   //City Code	
	edittextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
	
}
}

/**
 * Router Tool Panel
 * @throws InterruptedException 
 * @throws DocumentException 
 * @throws IOException 
 */
public void routerPanel(String application, String commandIPv4, String commandIPv6, 
		String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException, IOException {
	
	waitForpageload();
	waitforPagetobeenable();
	
	scrollToTop();
	Thread.sleep(1000);
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Router Tool' panel");
	
	WebElement vendorModel=getwebelement(xml.getlocator("//locators/" + application + "/MAS_View_VendorModelValue"));
	String vendor=Gettext(vendorModel);
	WebElement manageAddress=getwebelement(xml.getlocator("//locators/" + application + "/MAS_View_ManagementAddressValue"));
	String ipAddress=Gettext(manageAddress);
	
	ScrolltoElement(application, "MAS_View_VendorModelValue" , xml);
	Thread.sleep(1000);
	
	if(vendor.startsWith("Cisco")) {
		
	//Command IPV4	
		addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);
		
		hostnametextField_IPV4(application, commandIPv4, ipAddress);
		
		vrfNametextField_IPV4(application, commandIPv4, vrfname_ipv4);
		
		executeCommandAndFetchTheValue(application, "executebutton_Ipv4");
		
		
	//Commmand IPV6
		addDropdownValues_commonMethod(application, "Command IPV6", "commandIPV6_dropdown" , commandIPv6, xml);
		
		hostnametextField_IPV6(application, commandIPv6, ipAddress);
		
		vrfNametextField_IPV6(application, commandIPv6, vrfname_ipv6);
		
		executeCommandAndFetchTheValue(application, "executebutton_IPv6");
		
	}
	else if((vendor.startsWith("Juniper "))  && (!vendor.equals("Juniper MX204"))){
		
		//Command IPV4	
				addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);
				
				hostnametextField_IPV4(application, commandIPv4, ipAddress);
				
				vrfNametextField_IPV4(application, commandIPv4, vrfname_ipv4);
				
				executeCommandAndFetchTheValue(application, "executebutton_Ipv4");
				
		
	}
	else if(vendor.startsWith("Huawei ")){
		
		//Command IPV4	
				addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);
				
				hostnametextField_IPV4(application, commandIPv4, ipAddress);
				
				vrfNametextField_IPV4(application, commandIPv4, vrfname_ipv4);
				
				executeCommandAndFetchTheValue(application, "executebutton_Ipv4");
				
		
	}
	else {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Router Panel will not display for the selected vendorModel: "+vendorModel);
		Log.info("Router Panel will not display for the selected vendorModel: "+vendorModel);
	}
	

	
}

public void executeCommandAndFetchTheValue(String application, String executeButton) throws InterruptedException, DocumentException {
	
	click_commonMethod(application, "Execute", executeButton, xml);
	
	waitForpageload();
	Thread.sleep(1000);
	
	waitforPagetobeenable();
	Thread.sleep(2000);
boolean resultField=false;	
try {	
resultField=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).isDisplayed();
if(resultField) {
	ExtentTestManager.getTest().log(LogStatus.PASS, "'Result' text field is displaying");
	Log.info( "'Result' text field is displaying");
	
	String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).getText();
	ExtentTestManager.getTest().log(LogStatus.PASS, "value under 'Result' field displaying as "+ remarkvalue);
	Log.info("value under 'Result' field displaying as "+ remarkvalue);

}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
	Log.info( "'Result' text field is not displaying");
}
}catch(Exception e) {
e.printStackTrace();
ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
Log.info("'Result' text field is not displaying");
}
	
}


public void hostnametextField_IPV6(String application, String commandIPv6, String ipv6Address) {
	boolean IPV4availability=false;
	try {  
		IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv6_hostnameTextfield")).isDisplayed();
	  
	  if(IPV4availability) {
		  
		  addtextFields_commonMethod(application, "IP Address or Hostname", "commandIPv6_hostnameTextfield", ipv6Address, xml);
		  
	  }else {
		  	ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
			Log.info("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
	  }
	}catch(Exception e) {
		e.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
		Log.info("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
	}
}


public void vrfNametextField_IPV6(String application, String commandIPV6, String vrfname_IPV6) {
	boolean IPV6availability=false;
	
	if(commandIPV6.equalsIgnoreCase("vrf")) {
		try {  
			IPV6availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv6_vrfnameTextField")).isDisplayed();
			
			if(IPV6availability) {
				addtextFields_commonMethod(application, "Router Vrf Name", "commandIPv6_vrfnameTextField", vrfname_IPV6, xml);
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				Log.info("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			Log.info("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
		}
	}
	else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv6' text field is not displaying for "+ commandIPV6);
		Log.info("'VRF Name IPv6' text field is not displaying for "+ commandIPV6 +" command");
	}
	
}	


public void hostnametextField_IPV4(String application, String command_ipv4, String ipAddress) {
	boolean IPV4availability=false;
	try {  
		IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv4_hostnameTextfield")).isDisplayed();
	  
	  if(IPV4availability) {
		  
		  addtextFields_commonMethod(application, "IP Address or Hostname", "commandIPv4_hostnameTextfield", ipAddress, xml);
		  
	  }else {
		  	ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
			Log.info("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
	  }
	}catch(Exception e) {
		e.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
		Log.info("'Hostname or IpAddress' for 'Ipv4' text field is not displaying for "+ command_ipv4);
	}
}


public void vrfNametextField_IPV4(String application, String command_ipv4, String vrfname_ipv4) {
	boolean IPV4availability=false;
	  
		
	if(command_ipv4.contains("vrf")) {
		try {
			IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv4_vrfnameTextField")).isDisplayed();
			
			if(IPV4availability) {
				addtextFields_commonMethod(application, "Router Vrf Name", "commandIPv4_vrfnameTextField", vrfname_ipv4, xml);
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				Log.info("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			Log.info("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
		}
		
	}else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv4' text field is not displaying for "+ command_ipv4);
		Log.info("'VRF Name IPv4' text field is not displaying for "+ command_ipv4 +" command");
	}
}	


public void verifyAddInterfaceFunction_MAS(String application, String MAS_AccessMedia, String MAS_Network,
		String MAS_HSRPBGP, String MAS_Interface, String MAS_InterfaceAddressRange,
		String MAS_InterfaceAddressMask, String	MAS_HSRPIP, String	MAS_InterfaceAddressRangeIPV6, String MAS_HSRPIPv6Address, 
		String	MAS_PrimaryIPv6onMas1, String MAS_SecondaryIPv6onMas2, String MAS_GroupNumber, String MAS_Link, String MAS_VLANID,
		String	MAS_IVManagement, String MAS_generateConfiguration, String MAS_HSRPTrackInterface, String MAS_HSRPAuthentication) 
				throws InterruptedException, DocumentException, IOException {  

	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add Interface' Functionality");

	WebElement routerpanel= getwebelement(xml.getlocator("//locators/" + application + "/routerTool_HeaderPanel"));
	scrolltoview(routerpanel);
	Thread.sleep(2000);
	
	click_commonMethod(application, "Action", "MAS_PE_interfacepanel_ActionDropdown", xml);    //clicking on action dropdown under Interface panel
	click_commonMethod(application, "Add Interface/Link", "MAS_PE_addInterfaceLink", xml);
	
	scrolltoend();
	click_commonMethod(application, "OK", "trunk_okButton", xml);
	
	warningMessage_commonMethod(application, "MAS_PE_configurationWarningMessage", "Configuration", xml);
	
	scrollToTop();
	Thread.sleep(2000);
	
	warningMessage_commonMethod(application, "MAS_PE-interfaceWarningMessage", "Interface", xml);
	
	if(MAS_AccessMedia.equalsIgnoreCase("VPN")) {
		addDropdownValues(application, "Access Media", "MAS_PE_AccessMediaDropdown", MAS_AccessMedia);  //Access Media Dropdown
		addtextFields_commonMethod(application, "Interface", "MAS_PE_InterfaceTextfield", MAS_Interface, xml);   //interface Text Field
		addDropdownValues_commonMethod(application, "Network", "MAS_PE_networkDropdown", MAS_Network, xml);   //network Dropdown
		addtextFields_commonMethod(application, "Interface Address Range", "MAS_PE_InterfaceAddressRangeTextfield", MAS_InterfaceAddressRange, xml);   //interface address range text field
		addtextFields_commonMethod(application, "Interface Address/Mask" , "MAS_PE_InterfaceAddressMaskTextfield", MAS_InterfaceAddressMask, xml);   //Interface Address/Mask textfield
		addtextFields_commonMethod(application, "HSRP IP", "MAS_PE_HSRPIPTextfield", MAS_HSRPIP, xml);   //HSRP IP text field
		addtextFields_commonMethod(application, "Interface Address Range IPV6", "MAS_PE_InterfaceAddressRangeIPV6Textfield", MAS_InterfaceAddressRangeIPV6, xml);   //Interface Address Range IPV6 text field
		addtextFields_commonMethod(application, "HSRP IPv6 Address", "MAS_PE_HSRPIPv6AddressTextfield", MAS_HSRPIPv6Address, xml);   //HSRP IPv6 Address text field
		addtextFields_commonMethod(application, "Primary IPv6 on Mas-1", "MAS_PE_PrimaryIPv6onMas1Textfield", MAS_PrimaryIPv6onMas1, xml);   //Primary IPv6 on Mas-1 textfield
		addtextFields_commonMethod(application, "Secondary IPv6 on Mas-2", "MAS_PE_SecondaryIPv6onMas2Textfield", MAS_SecondaryIPv6onMas2, xml);   //Secondary IPv6 on Mas-2 text field
		
	ScrolltoElement(application, "MAS_PE_InterfaceTextfield", xml);	
	Thread.sleep(1000);
		
		addtextFields_commonMethod(application, "Group Number", "MAS_PE_GroupNumberTextfield", MAS_GroupNumber, xml);   //Group Number text field
		addtextFields_commonMethod(application, "Link", "MAS_PE_LinkTextfield", MAS_Link, xml);   //Link text field
		addtextFields_commonMethod(application, "VLAN Id", "MAS_PE_VLANIDTextfield", MAS_VLANID, xml);   //VLAN Id text field
		addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", MAS_IVManagement, "no", xml);   //IV Management checkbox
		
	}else if(MAS_AccessMedia.equalsIgnoreCase("EPN")) {
		
		
		addDropdownValues(application, "Access Media", "MAS_PE_AccessMediaDropdown", MAS_AccessMedia);  //Access Media Dropdown
		addtextFields_commonMethod(application, "Interface", "MAS_PE_InterfaceTextfield", MAS_Interface, xml);   //interface Text Field
		addDropdownValues(application, "HSRP/BGP", "MAS_PE_HSRDorBPdropdown", MAS_HSRPBGP);   //HSRP/BGP dropdown
		addDropdownValues_commonMethod(application, "Network", "MAS_PE_networkDropdown", MAS_Network, xml);   //network Dropdown
		addtextFields_commonMethod(application, "Interface Address Range", "MAS_PE_InterfaceAddressRangeTextfield", MAS_InterfaceAddressRange, xml);   //interface address range text field
		addtextFields_commonMethod(application, "Interface Address/Mask" , "MAS_PE_InterfaceAddressMaskTextfield", MAS_InterfaceAddressMask, xml);   //Interface Address/Mask textfield
		addtextFields_commonMethod(application, "HSRP IP", "MAS_PE_HSRPIPTextfield", MAS_HSRPIP, xml);   //HSRP IP text field
		addtextFields_commonMethod(application, "Interface Address Range IPV6", "MAS_PE_InterfaceAddressRangeIPV6Textfield", MAS_InterfaceAddressRangeIPV6, xml);   //Interface Address Range IPV6 text field
		addtextFields_commonMethod(application, "HSRP IPv6 Address", "MAS_PE_HSRPIPv6AddressTextfield", MAS_HSRPIPv6Address, xml);   //HSRP IPv6 Address text field
		addtextFields_commonMethod(application, "Primary IPv6 on Mas-1", "MAS_PE_PrimaryIPv6onMas1Textfield", MAS_PrimaryIPv6onMas1, xml);   //Primary IPv6 on Mas-1 textfield
		addtextFields_commonMethod(application, "Secondary IPv6 on Mas-2", "MAS_PE_SecondaryIPv6onMas2Textfield", MAS_SecondaryIPv6onMas2, xml);   //Secondary IPv6 on Mas-2 text field
		
		ScrolltoElement(application, "MAS_PE_InterfaceTextfield", xml);	
		Thread.sleep(1000);
		
		addtextFields_commonMethod(application, "Group Number", "MAS_PE_GroupNumberTextfield", MAS_GroupNumber, xml);   //Group Number text field
		addtextFields_commonMethod(application, "Link", "MAS_PE_LinkTextfield", MAS_Link, xml);   //Link text field
		addtextFields_commonMethod(application, "VLAN Id", "MAS_PE_VLANIDTextfield", MAS_VLANID, xml);   //VLAN Id text field
		addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", MAS_IVManagement, "no", xml);   //IV Management checkbox
		addtextFields_commonMethod(application, "HSRP Track Interface", "MAS_PE_HSRPtrackInterfacetextField", MAS_HSRPTrackInterface, xml);   //HSRP Track Interface text Field
		addtextFields_commonMethod(application, "HSRP Authentication", "MAS_PE_HSRPauthenticationtextField", MAS_HSRPAuthentication, xml);   //HSRP Authentication text Field
		
	}
		
	scrolltoend();
	Thread.sleep(1000);
	
//perform Generate configuration
	boolean configurationpanel=false;
try {	
	configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/MAS-PE_confiugrationPanelheader")).isDisplayed();
	if(configurationpanel) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
		Log.info("'Configuration' panel is displaying");
		
		addDropdownValues_commonMethod(application, "Generate Configuration ", "MAS_PE_generateConfigurationDropdown", MAS_generateConfiguration, xml);
		Thread.sleep(2000);
		
		click_commonMethod(application, "Generate Configuration", "MAS_PE_generateConfigurationLink", xml);
		Thread.sleep(2000);
		
		scrolltoend();
		Thread.sleep(1000);
		
		String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/configuration_textArea")));
		if(configurationvalues.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate Configuration' link, no values displaying under 'Configuration' text box");
			Log.info("After clicking on 'Generate Configuration' link, no values displaying under 'Configuration' text box");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate Configuration' link, "
					+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
			Log.info("After clicking on 'Generate Configuration' link, "
					+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
		}
	
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
		Log.info("'Configuration' panel is not displaying");
	}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			Log.info("'Configuration' panel is not displaying");
		}
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "trunk_okButton", xml);
	}



public void verifyAddInterfaceFunction_PE(String application, String PE_AccessMedia, String PE_Network,
		String PE_VRRPBGP, String PE_Interface, String PE_InterfaceAddressRange,
		String PE_InterfaceAddressMask, String	PE_VRRPIP, String	PE_InterfaceAddressRangeIPV6, String PE_VRRPIPv6Address, 
		String	PE_PrimaryIPv6onMas1, String PE_SecondaryIPv6onMas2, String PE_GroupNumber, String PE_Link, String PE_VLANID,
		String	PE_IVManagement, String PE_generateConfiguration, String PE_VRRPTrackInterface, String PE_VRRPAuthentication, 
		String PE_VRRfgroupname, String PE_VRF, String PE_InterfaceDefaultValue) 
				throws InterruptedException, DocumentException, IOException { 
	
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add Interface_PE Device' Functionality");
	
	WebElement routerpanel= getwebelement(xml.getlocator("//locators/" + application + "/routerTool_HeaderPanel"));
	scrolltoview(routerpanel);
	Thread.sleep(2000);
	
	String interfaceFieldFinalvalue=PE_InterfaceDefaultValue+PE_VLANID;

	click_commonMethod(application, "Action", "MAS_PE_interfacepanel_ActionDropdown", xml);    //clicking on action dropdown under Interface panel
	click_commonMethod(application, "Add Interface/Link", "MAS_PE_addInterfaceLink", xml);
	
	scrolltoend();
	click_commonMethod(application, "OK", "trunk_okButton", xml);
	
	warningMessage_commonMethod(application, "MAS_PE_configurationWarningMessage", "Configuration", xml);
	
	scrollToTop();
	Thread.sleep(2000);
	
	//Interface text field
			WebElement interfaceField=null;
		try {	
			interfaceField=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_InterfaceTextfield"));
			if(interfaceField.isEnabled()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Interface' text field is enabled");
				Log.info("'Interface' text field is enabled");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface' text field is disabled");
				Log.info("'Interface' text field is disabled");
				
				String interfacevalue=interfaceField.getAttribute("value");
				if(PE_InterfaceDefaultValue.equals(interfacevalue)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "By default 'Interface' text field value is displaying as '"+interfacevalue+"' as expected");
					Log.info("By default 'Interface' text field value is displaying as '"+interfacevalue+"' as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "By default 'Interface' text field value is displaying as "+interfacevalue);
					Log.info("By default 'Interface' text field value is displaying as "+interfacevalue);
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Interface' text field is not displaying");
			Log.info("'Interface' text field is not displaying");
		}
	
	if(PE_AccessMedia.equalsIgnoreCase("VPN")) {
		addDropdownValues(application, "Access Media", "MAS_PE_AccessMediaDropdown", PE_AccessMedia);  //Access Media Dropdown
		addDropdownValues_commonMethod(application, "Network", "MAS_PE_networkDropdown", PE_Network, xml);   //network Dropdown
		addtextFields_commonMethod(application, "Interface Address Range", "MAS_PE_InterfaceAddressRangeTextfield", PE_InterfaceAddressRange, xml);   //interface address range text field
		addtextFields_commonMethod(application, "Interface Address/Mask" , "MAS_PE_InterfaceAddressMaskTextfield", PE_InterfaceAddressMask, xml);   //Interface Address/Mask textfield
		addtextFields_commonMethod(application, "VRRP IP", "PE_VRRPiptextField", PE_VRRPIP, xml);   //VRRP IP text field
		addtextFields_commonMethod(application, "Interface Address Range IPV6", "MAS_PE_InterfaceAddressRangeIPV6Textfield", PE_InterfaceAddressRangeIPV6, xml);   //Interface Address Range IPV6 text field
		addtextFields_commonMethod(application, "Primary IPv6 on Mas-1", "MAS_PE_PrimaryIPv6onMas1Textfield", PE_PrimaryIPv6onMas1, xml);   //Primary IPv6 on Mas-1 textfield
		addtextFields_commonMethod(application, "Secondary IPv6 on Mas-2", "MAS_PE_SecondaryIPv6onMas2Textfield", PE_SecondaryIPv6onMas2, xml);   //Secondary IPv6 on Mas-2 text field
		
	ScrolltoElement(application, "MAS_PE_InterfaceTextfield", xml);	
	Thread.sleep(1000);
	
		addtextFields_commonMethod(application, "Group Number", "MAS_PE_GroupNumberTextfield", PE_GroupNumber, xml);   //Group Number text field
		addtextFields_commonMethod(application, "Link", "MAS_PE_LinkTextfield", PE_Link, xml);   //Link text field
		addtextFields_commonMethod(application, "VLAN Id", "MAS_PE_VLANIDTextfield", PE_VLANID, xml);   //VLAN Id text field
		addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", PE_IVManagement, "no", xml);   //IV Management checkbox
		edittextFields_commonMethod(application, "VRRP-Group Name", "PE_VRRpgroupnametextField", PE_VRRfgroupname, xml);   //VRRP-Group Name text field
		addtextFields_commonMethod(application, "VRF", "PE_VRFtextField", PE_VRF, xml);   //VRF textfield
		
	}else if(PE_AccessMedia.equalsIgnoreCase("EPN")) {
		
		
		addDropdownValues(application, "Access Media", "MAS_PE_AccessMediaDropdown", PE_AccessMedia);  //Access Media Dropdown
		addDropdownValues_commonMethod(application, "VRRP/BGP", "PE_VRRPBGPdropdown", PE_VRRPBGP, xml);    //VRRP/BGP dropdown
		addDropdownValues_commonMethod(application, "Network", "MAS_PE_networkDropdown", PE_Network, xml);   //network Dropdown
		addtextFields_commonMethod(application, "Interface Address Range", "MAS_PE_InterfaceAddressRangeTextfield", PE_InterfaceAddressRange, xml);   //interface address range text field
		addtextFields_commonMethod(application, "Interface Address/Mask" , "MAS_PE_InterfaceAddressMaskTextfield", PE_InterfaceAddressMask, xml);   //Interface Address/Mask textfield
		addtextFields_commonMethod(application, "VRRP IP", "PE_VRRPiptextField", PE_VRRPIP, xml);   //VRRP IP text field
		addtextFields_commonMethod(application, "Interface Address Range IPV6", "MAS_PE_InterfaceAddressRangeIPV6Textfield", PE_InterfaceAddressRangeIPV6, xml);   //Interface Address Range IPV6 text field
		addtextFields_commonMethod(application, "VRRP IPv6 Address", "PE_VRRPipv6AddressTextField", PE_VRRPIPv6Address , xml);   //VRRP IPv6 Address text field
		addtextFields_commonMethod(application, "Primary IPv6 on Mas-1", "MAS_PE_PrimaryIPv6onMas1Textfield", PE_PrimaryIPv6onMas1, xml);   //Primary IPv6 on Mas-1 textfield
		addtextFields_commonMethod(application, "Secondary IPv6 on Mas-2", "MAS_PE_SecondaryIPv6onMas2Textfield", PE_SecondaryIPv6onMas2, xml);   //Secondary IPv6 on Mas-2 text field
		
	ScrolltoElement(application, "MAS_PE_InterfaceTextfield", xml);	
	Thread.sleep(1000);
	
		addtextFields_commonMethod(application, "Group Number", "MAS_PE_GroupNumberTextfield", PE_GroupNumber, xml);   //Group Number text field
		addtextFields_commonMethod(application, "Link", "MAS_PE_LinkTextfield", PE_Link, xml);   //Link text field
		addtextFields_commonMethod(application, "VLAN Id", "MAS_PE_VLANIDTextfield", PE_VLANID, xml);   //VLAN Id text field
		addCheckbox_commonMethod(application, "MAS_PE_IVManagementCheckbox", "IV Management", PE_IVManagement, "no", xml);   //IV Management checkbox
		addtextFields_commonMethod(application, "VRRP Track Interface", "PE_VRRPtrackInterfacetextField", PE_VRRPTrackInterface, xml);   //VRRP Track Interface text Field
		addtextFields_commonMethod(application, "VRRP Authentication", "PE_VRRPauthenticationtextField", PE_VRRPAuthentication, xml);   //VRRP Authentication text Field
		edittextFields_commonMethod(application, "VRRP-Group Name", "PE_VRRpgroupnametextField", PE_VRRfgroupname, xml);   //VRRP-Group Name text field
		addtextFields_commonMethod(application, "VRF", "PE_VRFtextField", PE_VRF, xml);   //VRF textfield
		
	}
		
	
	scrollToTop();
	Thread.sleep(2000);
	
	try {	
		interfaceField=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_InterfaceTextfield"));
		if(interfaceField.isDisplayed()) {
			if(!PE_VLANID.equalsIgnoreCase("null")) {
				
				String actualvalue=interfaceField.getAttribute("value");
				if(interfaceFieldFinalvalue.equals(actualvalue)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interfcae' field value is displaying as: "+actualvalue);
					Log.info("'Interfcae' field value is displaying as: "+actualvalue);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'Interface' value is mismatching. It is displaying as '"+actualvalue+"'. Expected value is '"+ interfaceFieldFinalvalue +"'");
					Log.info("'Interface' value is mismatching. It is displaying as '"+actualvalue+"'. Expected value is '"+ interfaceFieldFinalvalue +"'");
				
				}
				
			}else if(PE_VLANID.equalsIgnoreCase("null")) {
				Log.info("interface field value remais same");
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
		Log.info("Interface field is not displaying");
	}
	
	scrolltoend();
	Thread.sleep(1000);
	
//perform Generate configuration
	boolean configurationpanel=false;
try {	
	configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/MAS-PE_confiugrationPanelheader")).isDisplayed();
	if(configurationpanel) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
		Log.info("'Configuration' panel is displaying");
		
		addDropdownValues_commonMethod(application, "Generate Configuration ", "MAS_PE_generateConfigurationDropdown", PE_generateConfiguration, xml);
		Thread.sleep(2000);
		
		click_commonMethod(application, "Generate Configuration", "MAS_PE_generateConfigurationLink", xml);
		Thread.sleep(2000);
		
		String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/configuration_textArea")));
		if(configurationvalues.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate Configuration' link, no values displaying under 'Configuration' text box");
			Log.info("After clicking on 'Generate Configuration' link, no values displaying under 'Configuration' text box");
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate Configuration' link, "
					+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
			Log.info("After clicking on 'Generate Configuration' link, "
					+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
		}
	
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
		Log.info("'Configuration' panel is not displaying");
	}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			Log.info("'Configuration' panel is not displaying");
		}
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "trunk_okButton", xml);
	}



	public void editInterface_PE(String application, String PE_AccessMedia, String PE_Network,
			String PE_VRRPBGP, String PE_InterfaceAddressRange,
			String PE_InterfaceAddressMask, String	PE_VRRPIP, String	PE_InterfaceAddressRangeIPV6, String PE_VRRPIPv6Address, 
			String	PE_PrimaryIPv6onMas1, String PE_SecondaryIPv6onMas2, String PE_GroupNumber, String PE_Link, String PE_VLANID,
			String	PE_IVManagement, String PE_generateConfiguration, String PE_VRRPTrackInterface, String PE_VRRPAuthentication, 
			String PE_VRRfgroupname, String PE_VRF, String PE_interfaceName) 
					throws InterruptedException, DocumentException, IOException { 
		
		waitForpageload();
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(2000);
		
		//Interface text field
				WebElement interfaceField=null;
			try {	
				interfaceField=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_InterfaceTextfield"));
				if(interfaceField.isEnabled()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'Interface' text field is enabled");
					Log.info("'Interface' text field is enabled");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface' text field is disabled");
					Log.info("'Interface' text field is disabled");
					
					String interfacevalue=interfaceField.getAttribute("value");
					if(PE_interfaceName.equals(interfacevalue)) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface' text field value is displaying as '"+interfacevalue+"' as expected");
						Log.info("'Interface' text field value is displaying as '"+interfacevalue+"' as expected");
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface' text field value is displaying as "+interfacevalue);
						Log.info("'Interface' text field value is displaying as "+interfacevalue);
					}
					
				}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Interface' text field is not displaying");
				Log.info("'Interface' text field is not displaying");
			}
		
		if(PE_AccessMedia.equalsIgnoreCase("VPN")) {
			addDropdownValues(application, "Access Media", "MAS_PE_AccessMediaDropdown", PE_AccessMedia);  //Access Media Dropdown
			addDropdownValues_commonMethod(application, "Network", "MAS_PE_networkDropdown", PE_Network, xml);   //network Dropdown
			edittextFields_commonMethod(application, "Interface Address Range", "MAS_PE_InterfaceAddressRangeTextfield", PE_InterfaceAddressRange, xml);   //interface address range text field
			edittextFields_commonMethod(application, "Interface Address/Mask" , "MAS_PE_InterfaceAddressMaskTextfield", PE_InterfaceAddressMask, xml);   //Interface Address/Mask textfield
			edittextFields_commonMethod(application, "VRRP IP", "PE_VRRPiptextField", PE_VRRPIP, xml);   //VRRP IP text field
			edittextFields_commonMethod(application, "Interface Address Range IPV6", "MAS_PE_InterfaceAddressRangeIPV6Textfield", PE_InterfaceAddressRangeIPV6, xml);   //Interface Address Range IPV6 text field
			edittextFields_commonMethod(application, "Primary IPv6 on Mas-1", "MAS_PE_PrimaryIPv6onMas1Textfield", PE_PrimaryIPv6onMas1, xml);   //Primary IPv6 on Mas-1 textfield
			edittextFields_commonMethod(application, "Secondary IPv6 on Mas-2", "MAS_PE_SecondaryIPv6onMas2Textfield", PE_SecondaryIPv6onMas2, xml);   //Secondary IPv6 on Mas-2 text field
			
		ScrolltoElement(application, "MAS_PE_InterfaceTextfield", xml);	
		Thread.sleep(1000);
		
			edittextFields_commonMethod(application, "Group Number", "MAS_PE_GroupNumberTextfield", PE_GroupNumber, xml);   //Group Number text field
			edittextFields_commonMethod(application, "Link", "MAS_PE_LinkTextfield", PE_Link, xml);   //Link text field
			edittextFields_commonMethod(application, "VLAN Id", "MAS_PE_VLANIDTextfield", PE_VLANID, xml);   //VLAN Id text field
			editcheckbox_commonMethod(application, PE_IVManagement, "MAS_PE_IVManagementCheckbox", "IV Management", xml);
			edittextFields_commonMethod(application, "VRRP-Group Name", "PE_VRRpgroupnametextField", PE_VRRfgroupname, xml);   //VRRP-Group Name text field
			edittextFields_commonMethod(application, "VRF", "PE_VRFtextField", PE_VRF, xml);   //VRF textfield
			
		}else if(PE_AccessMedia.equalsIgnoreCase("EPN")) {
			
			
			addDropdownValues(application, "Access Media", "MAS_PE_AccessMediaDropdown", PE_AccessMedia);  //Access Media Dropdown
			addDropdownValues_commonMethod(application, "VRRP/BGP", "PE_VRRPBGPdropdown", PE_VRRPBGP, xml);    //VRRP/BGP dropdown
			addDropdownValues_commonMethod(application, "Network", "MAS_PE_networkDropdown", PE_Network, xml);   //network Dropdown
			edittextFields_commonMethod(application, "Interface Address Range", "MAS_PE_InterfaceAddressRangeTextfield", PE_InterfaceAddressRange, xml);   //interface address range text field
			edittextFields_commonMethod(application, "Interface Address/Mask" , "MAS_PE_InterfaceAddressMaskTextfield", PE_InterfaceAddressMask, xml);   //Interface Address/Mask textfield
			edittextFields_commonMethod(application, "VRRP IP", "PE_VRRPiptextField", PE_VRRPIP, xml);   //VRRP IP text field
			edittextFields_commonMethod(application, "Interface Address Range IPV6", "MAS_PE_InterfaceAddressRangeIPV6Textfield", PE_InterfaceAddressRangeIPV6, xml);   //Interface Address Range IPV6 text field
			edittextFields_commonMethod(application, "VRRP IPv6 Address", "PE_VRRPipv6AddressTextField", PE_VRRPIPv6Address , xml);   //VRRP IPv6 Address text field
			edittextFields_commonMethod(application, "Primary IPv6 on Mas-1", "MAS_PE_PrimaryIPv6onMas1Textfield", PE_PrimaryIPv6onMas1, xml);   //Primary IPv6 on Mas-1 textfield
			edittextFields_commonMethod(application, "Secondary IPv6 on Mas-2", "MAS_PE_SecondaryIPv6onMas2Textfield", PE_SecondaryIPv6onMas2, xml);   //Secondary IPv6 on Mas-2 text field
			
		ScrolltoElement(application, "MAS_PE_InterfaceTextfield", xml);	
		Thread.sleep(1000);
		
			edittextFields_commonMethod(application, "Group Number", "MAS_PE_GroupNumberTextfield", PE_GroupNumber, xml);   //Group Number text field
			edittextFields_commonMethod(application, "Link", "MAS_PE_LinkTextfield", PE_Link, xml);   //Link text field
			edittextFields_commonMethod(application, "VLAN Id", "MAS_PE_VLANIDTextfield", PE_VLANID, xml);   //VLAN Id text field
			editcheckbox_commonMethod(application, PE_IVManagement, "MAS_PE_IVManagementCheckbox", "IV Management", xml);
			edittextFields_commonMethod(application, "VRRP Track Interface", "PE_VRRPtrackInterfacetextField", PE_VRRPTrackInterface, xml);   //VRRP Track Interface text Field
			edittextFields_commonMethod(application, "VRRP Authentication", "PE_VRRPauthenticationtextField", PE_VRRPAuthentication, xml);   //VRRP Authentication text Field
			edittextFields_commonMethod(application, "VRRP-Group Name", "PE_VRRpgroupnametextField", PE_VRRfgroupname, xml);   //VRRP-Group Name text field
			edittextFields_commonMethod(application, "VRF", "PE_VRFtextField", PE_VRF, xml);   //VRF textfield
			
		}
			
		scrolltoend();
		Thread.sleep(1000);
		
	//perform Generate configuration
		boolean configurationpanel=false;
	try {	
		configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/MAS-PE_confiugrationPanelheader")).isDisplayed();
		if(configurationpanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
			Log.info("'Configuration' panel is displaying");
			
			addDropdownValues_commonMethod(application, "Generate Configuration ", "MAS_PE_generateConfigurationDropdown", PE_generateConfiguration, xml);
			Thread.sleep(2000);
			
			click_commonMethod(application, "Generate Configuration", "MAS_PE_generateConfigurationLink", xml);
			Thread.sleep(2000);
			
			String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/configuration_textArea")));
			if(configurationvalues.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate Configuration' link, no values displaying under 'Configuration' text box");
				Log.info("After clicking on 'Generate Configuration' link, no values displaying under 'Configuration' text box");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate Configuration' link, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				Log.info("After clicking on 'Generate Configuration' link, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
			}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			Log.info("'Configuration' panel is not displaying");
		}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				Log.info("'Configuration' panel is not displaying");
			}
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "trunk_okButton", xml);
		}



  /**
   *  List of column names under Interface column
   * @param application
   * @param interfaceName
   * @throws InterruptedException
   * @throws DocumentException
   * @throws IOException
   */
		public void verifyinterfaceTableColumnNames(String application, String interfaceName) throws InterruptedException, DocumentException, IOException {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Added Interface' value in table");
			
			WebElement routerToolPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/routerTool_HeaderPanel"));
			scrolltoview(routerToolPanelHeader);
			Thread.sleep(1000);
			
			String[] InterfaceFieldNames= {"Interface", "Link/Circuit Id", "Interface Address Range", "Interface Address", "Bearer Type","Bandwidth", "VLAN Id" , "IfInOctets", "IV Management"};
			List<String> ls = new ArrayList<String>();
			
			String expectedvalues="[Interface, Link/Circuit Id, Interface Address Range, Interface Address, Bearer Type, Bandwidth, VLAN Id, IfInOctets, IV Management]";
			ExtentTestManager.getTest().log(LogStatus.PASS, "Expected column names are: "+ expectedvalues);
			
			addtextFields_commonMethod(application, "Search box", "MAS_PE_searchbox", interfaceName, xml);
		
		//Fetches list of common label names	
			List<WebElement> interfaceLabelnames = getwebelements(xml.getlocator("//locators/" + application + "/MAS_PE_InterfacePanel_labelnames"));
			for (WebElement interfaceLabelname : interfaceLabelnames) {

				boolean match = false;
				for (int i = 0; i < InterfaceFieldNames.length; i++) {
					if (interfaceLabelname.getText().equals(InterfaceFieldNames[i])) {
						match = true;
						ls.add(interfaceLabelname.getText());
					}
				}
			}
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Actual column names displaying are: "+ ls);  //printing list of column names displaying
			Log.info("Actual column names displaying are: "+ ls);
			
		}
		
		
		public void verifyInterfaceValues_MAS(String application, String MAS_AccessMedia, String MAS_Network,
				String MAS_HSRPBGP, String MAS_Interface, String MAS_InterfaceAddressRange,
				String MAS_InterfaceAddressMask, String	MAS_HSRPIP, String	MAS_InterfaceAddressRangeIPV6, String MAS_HSRPIPv6Address, 
				String	MAS_PrimaryIPv6onMas1, String MAS_SecondaryIPv6onMas2, String MAS_GroupNumber, String MAS_Link, String MAS_VLANID,
				String	MAS_IVManagement, String MAS_generateConfiguration, String MAS_HSRPTrackInterface, String MAS_HSRPAuthentication) throws InterruptedException, DocumentException {
			
			
			String interfaceAddressrangeValue=null;
					
			
			if(MAS_InterfaceAddressRangeIPV6.equalsIgnoreCase("null")) {
				interfaceAddressrangeValue=MAS_InterfaceAddressRange;
			}else {
				interfaceAddressrangeValue=MAS_InterfaceAddressRange+","+MAS_InterfaceAddressRangeIPV6;
			}
			
			compareText(application, "Interface" , "MAS_PE_InterfacePanel_nameValue" , MAS_Interface, xml);
			compareText(application, "Link/Circuit id", "MAS_PE_InterfacePanel_linkValue", MAS_Link, xml);
			compareText(application,"Interface Address Range" , "MAS_PE_InterfacePanel_InteraceAddressRangeValue", interfaceAddressrangeValue , xml);
			compareText(application, "Interface Address" , "MAS_PE_InterfacePanel_InteraceAddressValue", "", xml);
			compareText(application, "Bearer Type" , "MAS_PE_InterfacePanel_bearertypeValue" , "", xml);
			compareText(application, "Bandwidth", "MAS_PE_InterfacePanel_bandwidthvalue" , "", xml);
			compareText(application, "Vlan Id", "MAS_PE_InterfacePanel_vlanidvalue" , MAS_VLANID, xml);
			compareText(application, "IfInOctets", "MAS_PE_interfacePanel_IfInOctetsValue" , "", xml);
			compareText(application, "IV Management", "MAS_PE_interfacePanel_IVmanagementvalue", MAS_IVManagement, xml);
			
		}
		
		
		public void verifyInterfaceValues_PE(String application, String PE_AccessMedia, String PE_Network,
				String PE_VRRPBGP, String PE_Interface, String PE_InterfaceAddressRange,
				String PE_InterfaceAddressMask, String	PE_VRRPIP, String	PE_InterfaceAddressRangeIPV6, String PE_VRRPIPv6Address, 
				String	PE_PrimaryIPv6onMas1, String PE_SecondaryIPv6onMas2, String PE_GroupNumber, String PE_Link, String PE_VLANID,
				String	PE_IVManagement, String PE_generateConfiguration, String PE_VRRPTrackInterface, String PE_VRRPAuthentication, 
				String PE_VRRfgroupname, String PE_VRF, String PE_InterfaceDefaultValue) throws InterruptedException, DocumentException {
			
			
			String interfaceAddressrangeValue=null;
					
			
			if(PE_InterfaceAddressRangeIPV6.equalsIgnoreCase("null")) {
				interfaceAddressrangeValue=PE_InterfaceAddressRange;
			}else {
				interfaceAddressrangeValue=PE_InterfaceAddressRange+","+PE_InterfaceAddressRangeIPV6;
			}
			
			compareText(application, "Interface" , "MAS_PE_InterfacePanel_nameValue" , PE_Interface, xml);
			compareText(application, "Link/Circuit id", "MAS_PE_InterfacePanel_linkValue", PE_Link, xml);
			compareText(application,"Interface Address Range" , "MAS_PE_InterfacePanel_InteraceAddressRangeValue", interfaceAddressrangeValue , xml);
			compareText(application, "Interface Address" , "MAS_PE_InterfacePanel_InteraceAddressValue", "", xml);
			compareText(application, "Bearer Type" , "MAS_PE_InterfacePanel_bearertypeValue" , "", xml);
			compareText(application, "Bandwidth", "MAS_PE_InterfacePanel_bandwidthvalue" , "", xml);
			compareText(application, "Vlan Id", "MAS_PE_InterfacePanel_vlanidvalue" , PE_VLANID, xml);
			compareText(application, "IfInOctets", "MAS_PE_interfacePanel_IfInOctetsValue" , "", xml);
			compareText(application, "IV Management", "MAS_PE_interfacePanel_IVmanagementvalue", PE_IVManagement, xml);
			
		}
		
	/**
	 * 	clicking on breadcrump for navigation
	 * @param application
	 * @throws DocumentException 
	 * @throws InterruptedException 
	 */
		public void clickOnBreadCrump(String application, String breadCrumpLink) throws InterruptedException, DocumentException {
			
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(2000);
			
			scrollToTop();
			WebElement breadcrumb=null;
			
			try {
			breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
			if(breadcrumb.isDisplayed()) {
				click_commonMethod_PassingWebelementDirectly(application, "Breadcrump", "breadcrump", xml);
				Thread.sleep(2000);
				
				waitForpageload();
				waitforPagetobeenable();
			}else {
				Log.info("Breadcrumb is not displaying for the element "+ breadcrumb);
			}
		}catch(Exception e) {
			e.printStackTrace();
			Log.info("Breadcrumb is not displaying for the element "+ breadcrumb);
		}
			
			
		}
		
		
		public void editPEdevice(String application, String editDeviceName, String editVendorModel, String editmanageAddress,
				String editSnmpro, String editCountry,
				String editExistingCity, String editNewCity, String editExistingCityValue, String editNewCityName, String editNewCityCode,
				String editExistingSite, String editNewSite, String editExistingSiteValue, String editNewSiteName, String editNewSiteCode,
				String editExistingPremise, String editNewPremise, String editExistingPremiseValue, String editNewPremiseName, String editNewPremiseCode) 
						throws InterruptedException, DocumentException, IOException {
			
			scrollToTop();
			Thread.sleep(1000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit PE Device' Functoinality");
			
			click_commonMethod(application, "Action", "MAS_View_ActionLink", xml);

			click_commonMethod(application, "Edit link", "MAS_View_Action_EditLink", xml);
			
			waitForpageload();
			waitforPagetobeenable();
			
			Thread.sleep(2000);
			
		//Device name	
			edittextFields_commonMethod(application, "Device name", "MAS_deviceName", editDeviceName, xml);
			
		//vendor/model	
			addDropdownValues_commonMethod_ForSpantag(application, "Vendor/Model", "MAS_vendorModel", editVendorModel, xml);
			
		//Management Address	
			edittextFields_commonMethod(application, "Management Address", "MAS_managementAddress", editmanageAddress, xml);
			
		//Snmrpo	
			edittextFields_commonMethod(application, "Snmpro", "MAS_snmpro", editSnmpro, xml);
		
			scrolltoend();
			Thread.sleep(2000);
			//Country
					if(!editCountry.equalsIgnoreCase("Null")) {
						
//						addDropdownValues_commonMethod(application, "Country", "countryinput", editCountry, xml);
						selectValueInsideDropdown(application, "countryinput", "Country", editCountry, xml);
						
						//New City		
						if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("yes")) {
							Clickon_addToggleButton(application, "addcityswitch");
						   //City name
							addtextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
						   //City Code	
							addtextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
						   //Site name
							addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
						   //Site Code
							addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", editNewSiteCode, xml);
						   //Premise name	
							addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
						   //Premise Code	
							addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);
								
						}	
					
					//Existing City	
						else if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("no")) {
							
//						   addDropdownValues_commonMethod(application, "City", "citydropdowninput", editExistingCityValue, xml);
						   selectValueInsideDropdown(application, "citydropdowninput", "City", editExistingCityValue, xml);
							
							
						 //Existing Site
							  if(editExistingSite.equalsIgnoreCase("yes") & editNewSite.equalsIgnoreCase("no")) {
//								  addDropdownValues_commonMethod(application, "Site", "sitedropdowninput", editExistingSiteValue, xml);
								  selectValueInsideDropdown(application, "sitedropdowninput", "Site", editExistingSiteValue, xml);
								  
							 //Existing Premise
								  if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("no")) {
//									  addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", editExistingPremiseValue, xml);
									  selectValueInsideDropdown(application, "premisedropdowninput", "Premise", editExistingPremiseValue, xml);
						          	 }
								  
								//New Premise  
								  else if(editExistingPremise.equalsIgnoreCase("no") & editNewPremise.equalsIgnoreCase("yes")) {
									  
									  Clickon_addToggleButton(application, "addpremiseswitch");
									  //Premise name	
										addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", editNewPremiseName, xml);
									   //Premise Code	
										addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", editNewPremiseCode, xml);
								  } 
					         	}
				  		
						  else if(editExistingSite.equalsIgnoreCase("no") & editNewSite.equalsIgnoreCase("yes")) {
							  	
							  Clickon_addToggleButton(application, "addsiteswitch");
							  //Site name
								addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", editNewSiteName, xml);
							   //Site Code
								addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", editNewSiteCode, xml);
							   //Premise name	
								addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", editNewPremiseName, xml);
							   //Premise Code	
								addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", editNewPremiseCode, xml);
							  }
						}
						
					}
					else if(editCountry.equalsIgnoreCase("Null")) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, " No changes made for 'Country' dropdown");
					
					//City	
						editCity(application, editExistingCity, editNewCity, "citydropdowninput", "selectcityswitch", "addcityswitch",
								editExistingCityValue, editNewCityName, editNewCityCode, "City");
						
						
					//Site	
						editSite(application, editExistingSite, editNewSite, "sitedropdowninput", "selectsiteswitch",
								"addsiteswitch", editExistingSiteValue , editNewSiteName, editNewSiteCode, "Site");
						
					//Premise
						editPremise(application, editExistingPremise, editNewPremise, "premisedropdowninput", "selectpremiseswitch",
								"addpremiseswitch", editExistingPremiseValue, editNewPremiseName, editNewPremiseCode, "Premise");
						
					}

			scrolltoend();
			Thread.sleep(2000);
			click_commonMethod(application, "OK", "trunk_okButton" , xml);
			
		}
		
		
		
		/**
		 * For deleting the PE devices
		 * @param application
		 * @param existingdevicename
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void PEdevice__DeleteFromServiceFunctionality(String application, String existingdevicename) throws InterruptedException, DocumentException { 
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete PE device' Functionality");
			
			WebElement MASswitchPanel_header= getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_panelHeader"));
			scrolltoview(MASswitchPanel_header);
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
            {
                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
//                  Log.info(addeddevicesList);
                  int AddedDevicesCount= addeddevicesList.size();
                  for(int i=0;i<AddedDevicesCount;i++) {
                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                        if(AddedDeviceNameText.contains(existingdevicename))
                        {
                              WebElement AddedDevice_DeletefromserviceLink=getwebelement(xml.getlocator("//locators/" + application + "/PE_deleteFromService_InViewPage").replace("value", AddedDevice_SNo)); 
                              Clickon(AddedDevice_DeletefromserviceLink);
                              Thread.sleep(2000);
                              WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
                              if(DeleteAlertPopup.isDisplayed())
                              {
                                    click_commonMethod(application, "Delete", "deletebutton", xml);
                                    compareText(application, "Device delete success message", "MAS_deleteSuccessMessage", "PE Device deleted successfully", xml);
                                    break;
                              }
                              else
                              {
                                    Log.info("Delete alert popup is not displayed");
                                    ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
                              }
                              
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                        }
                        
                  }
            }
            else
            {
                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
            }		
			}

	
		/**
		 * For deleting the MAS SWitches
		 * @param application
		 * @param existingdevicename
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void MASswitch__DeleteFromServiceFunctionality(String application, String existingdevicename) throws InterruptedException, DocumentException { 
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete MAS Switch' Functionality");
			
			WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
			scrolltoview(managementOptions_header);
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid_MAS")).isDisplayed())
            {
                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/MAS_fetchAlldevice_InviewPage"));
//                  Log.info(addeddevicesList);
                  int AddedDevicesCount= addeddevicesList.size();
                  for(int i=0;i<AddedDevicesCount;i++) {
                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                        if(AddedDeviceNameText.contains(existingdevicename))
                        {
                              WebElement AddedDevice_DeletefromserviceLink=getwebelement(xml.getlocator("//locators/" + application + "/MAS_deleteFromService_InViewPage").replace("value", AddedDevice_SNo)); 
                              Clickon(AddedDevice_DeletefromserviceLink);
                              Thread.sleep(2000);
                              WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
                              if(DeleteAlertPopup.isDisplayed())
                              {
                                    click_commonMethod(application, "Delete", "deletebutton", xml);
                                    compareText(application, "Device delete success message", "MAS_deleteSuccessMessage", "MAS switch deleted successfully", xml);
                                    break;
                              
                              }
                              else
                              {
                                    Log.info("Delete alert popup is not displayed");
                                    ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
                              }
                              
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                        }
                       
                  }
            }
            else
            {
                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
            }		
			}

		
		
		
	/**
	 * Method is to click on view link for the respective device name under PE device
	 * @param application
	 * @param existingdevicename
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
		public void PEdevice_clickOnViewPage(String application, String existingdevicename) throws InterruptedException, DocumentException {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'View PE Device'");
			
			WebElement MASswitchPanel_header= getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_panelHeader"));
			scrolltoview(MASswitchPanel_header);
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
            {
                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
                  int AddedDevicesCount= addeddevicesList.size();
                  for(int i=0;i<AddedDevicesCount;i++) {
                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                        Log.info("number "+ AddedDevice_SNo);
                        if(AddedDeviceNameText.contains(existingdevicename))
                        {
                              WebElement viewLink=getwebelement(xml.getlocator("//locators/" + application + "/PE_viewLink_InViewPage").replace("value", AddedDevice_SNo)); 
                              Clickon(viewLink);
                              Thread.sleep(2000);
                              break;
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                        }
                       
                  }
            }
            else
            {
                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
            }		
		}

		
		/**
		 * Method is to click on view link for the respective device name under PE device
		 * @param application
		 * @param existingdevicename
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
			public void MASswitch_clickOnViewPage(String application, String existingdevicename) throws InterruptedException, DocumentException {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'view MAS Switch'");
				
				waitForpageload();
				waitforPagetobeenable();
				
				WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
				scrolltoview(managementOptions_header);
				Thread.sleep(2000);
				
				if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid_MAS")).isDisplayed())
	            {
	                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/MAS_fetchAlldevice_InviewPage"));
//	                  Log.info(addeddevicesList);
	                  int AddedDevicesCount= addeddevicesList.size();
	                  for(int i=0;i<AddedDevicesCount;i++) {
	                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
	                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
	                        Log.info("number "+ AddedDevice_SNo);
	                        if(AddedDeviceNameText.contains(existingdevicename))
	                        {
	                              WebElement viewLink=getwebelement(xml.getlocator("//locators/" + application + "/MAS_viewLink_InViewPage").replace("value", AddedDevice_SNo)); 
	                              Clickon(viewLink);
	                              Thread.sleep(2000);
	                              break;
	                        }
	                        else
	                        {
	                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
	                        }
	                       
	                  }
	            }
	            else
	            {
	                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	            }		
			}
		
//		/**
//		 * Method is to click on view link for the respective device name under PE device
//		 * @param application
//		 * @param existingdevicename
//		 * @throws InterruptedException
//		 * @throws DocumentException
//		 */
//			public void MASdevice_clickOnViewPage(String application, String existingdevicename) throws InterruptedException, DocumentException {
//				
//				WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
//				scrolltoview(managementOptions_header);
//				Thread.sleep(2000);
//				
//				if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
//	            {
//	                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
//	                  Log.info(addeddevicesList);
//	                  int AddedDevicesCount= addeddevicesList.size();
//	                  for(int i=0;i<AddedDevicesCount;i++) {
//	                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
//	                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
//	                        Log.info("number "+ AddedDevice_SNo);
//	                        if(AddedDeviceNameText.contains(existingdevicename))
//	                        {
//	                              WebElement viewLink=getwebelement(xml.getlocator("//locators/" + application + "/PE_viewLink_InViewPage").replace("value", AddedDevice_SNo)); 
//	                              Clickon(viewLink);
//	                              Thread.sleep(2000);
//									break;
//	                        }
//	                        else
//	                        {
//	                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
//	                        }
//	                        
//	                  }
//	            }
//	            else
//	            {
//	                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
//	            }		
//			}
//			
		
		/**
		 *  Method is for clicking on "Select interface" link for a PE device	
		 * @param application
		 * @param existingdevicename
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void PEdevice_clickOnselectInterface(String application, String existingdevicename) throws InterruptedException, DocumentException {
			
			WebElement MASswitchPanel_header= getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_panelHeader"));
			scrolltoview(MASswitchPanel_header);
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
            {
                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
//                  Log.info(addeddevicesList);
                  int AddedDevicesCount= addeddevicesList.size();
                  for(int i=0;i<AddedDevicesCount;i++) {
                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                        if(AddedDeviceNameText.contains(existingdevicename))
                        {
                              WebElement AddedDevice_selectInterfaceLink=getwebelement(xml.getlocator("//locators/" + application + "/PE_selectInterface_InViewPage").replace("value", AddedDevice_SNo)); 
                              Clickon(AddedDevice_selectInterfaceLink);
                              Thread.sleep(2000);
                              break;
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                        }
                       
                  }
            }
            else
            {
                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
            }		
		}


		
		/**
		 *  Method is for clicking on "Select interface" link under MAS swicth	
		 * @param application
		 * @param existingdevicename
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void MSAdevice_clickOnselectInterface(String application, String existingdevicename) throws InterruptedException, DocumentException {
			
			WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
			scrolltoview(managementOptions_header);
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid_MAS")).isDisplayed())
            {
                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/MAS_fetchAlldevice_InviewPage"));
//                  Log.info(addeddevicesList);
                  int AddedDevicesCount= addeddevicesList.size();
                  for(int i=0;i<AddedDevicesCount;i++) {
                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                        if(AddedDeviceNameText.contains(existingdevicename))
                        {
                              WebElement AddedDevice_selectInterfaceLink=getwebelement(xml.getlocator("//locators/" + application + "/MAS_selectInterface_InViewPage").replace("value", AddedDevice_SNo)); 
                              Clickon(AddedDevice_selectInterfaceLink);
                              Thread.sleep(2000);
                              break;
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                        }
                       
                  }
            }
            else
            {
                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
            }		
		}

		
		public void SelectInterfacetoremovefromservice(String Application, String interfacename)
				throws IOException, InterruptedException, DocumentException {

			waitForpageload();
			
			waitforPagetobeenable();
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Select Interface_Remove Interace from Service");
			
			WebElement manageAddress=getwebelement(xml.getlocator("//locators/" + Application + "/MAS_View_ManagementAddressValue"));
			scrolltoview(manageAddress);
			Thread.sleep(2000);
			
			Thread.sleep(5000);
			selectRowforInterfaceInService(Application, interfacename);

		}
		
		
		public void selectRowforInterfaceInService(String Application, String interfacenumber)
				throws IOException, InterruptedException, DocumentException {

			int TotalPages;

			String TextKeyword = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_totalpage")));

			TotalPages = Integer.parseInt(TextKeyword);

			Log.info("Total number of pages in table is: " + TotalPages);

			ab:

			if (TotalPages != 0) {
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_currentpage")));
					int Current_page = Integer.parseInt(CurrentPage);

					assertEquals(k, Current_page);

					Log.info("Currently we are in page number: " + Current_page);

					List<WebElement> results = getwebelements("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='" + interfacenumber +"']]//span[@class='ag-icon ag-icon-checkbox-unchecked']");
					
					int numofrows = results.size();
					Log.info("no of results: " + numofrows);
					boolean resultflag;

					if (numofrows == 0) {

						PageNavigation_NextPageForInterfaceInService(Application);

					}
					else {
						for (int i = 0; i < numofrows; i++) {
							try {
								
								waitForpageload();
								waitforPagetobeenable();
								
								resultflag = results.get(i).isDisplayed();
								Log.info("status of result: " + resultflag);
								if (resultflag) {
									Log.info(results.get(i).getText());
									results.get(i).click();
									ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Interface in Service' table");
									click_commonMethod(Application, "Action", "InterfaceInselect_Actiondropdown", xml);
									Thread.sleep(1000);
									
									click_commonMethod(Application, "Remove", "InterfaceInselect_removebuton", xml);

								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"+ interfacenumber+"']]//input"));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								ExtentTestManager.getTest().log(LogStatus.FAIL, "failure while selecting interface to remove from service");

							}
						}
						break ab;
					}
				}
			} else {
				Log.info("No values available in table");
				Log.info("No values available inside the InterfaceInService table");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'interfaces in Services' table");
			}
		}
		
		
		public void PageNavigation_NextPageForInterfaceToselect(String Application)
				throws InterruptedException, DocumentException {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_nextpage")));
			Thread.sleep(3000);

		}
		
		public void PageNavigation_NextPageForInterfaceInService(String Application)
				throws InterruptedException, DocumentException {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_nextpage")));
			Thread.sleep(3000);

		}

		
		
		public void SelectInterfacetoaddwithservcie(String Application, String interfacenumber)
				throws InterruptedException, DocumentException, IOException {

			scrolltoend();
			Thread.sleep(10000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Select interface_Add Interface to Service'");
			
			waitForpageload();
			
			waitforPagetobeenable();
			
			WebElement interfaceInSericePanelHeader=getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInService_panelHeader"));
			scrolltoview(interfaceInSericePanelHeader);
			Thread.sleep(2000);
			
			click_commonMethod(Application, "Interface Column filter", "InteraceColumn_Filter", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(Application, "Filter Text", "filterTxt", interfacenumber, xml); 
			Thread.sleep(1000);
			
			click_commonMethod(Application, "Interface column Header", "InterfaceToSelect_interfaceColumnHeader", xml);
			
			selectrowforInterfaceToselecttable(Application, interfacenumber);

		}
		
		public void selectrowforInterfaceToselecttable(String Application, String interfacenumber)
				throws IOException, InterruptedException, DocumentException {

			int TotalPages;

			String TextKeyword = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_totalpage")));

			TotalPages = Integer.parseInt(TextKeyword);

			Log.info("Total number of pages in Interface to select table is: " + TotalPages);

			ab:

			if (TotalPages != 0) {
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_currentpage")));
					int Current_page = Integer.parseInt(CurrentPage);

					assertEquals(k, Current_page);

					Log.info("Currently we are in page number: " + Current_page);

					List<WebElement> results = getwebelements("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[div[text()='"+interfacenumber+"']]//span[@class='ag-icon ag-icon-checkbox-unchecked']");
//					Log.info(results);	
					int numofrows = results.size();
					Log.info("no of results: " + numofrows);
					boolean resultflag;

					if (numofrows == 0) {
						PageNavigation_NextPageForInterfaceToselect(Application);
					}

					else {
						for (int i = 0; i < numofrows; i++) {
							try {
								resultflag = results.get(i).isDisplayed();
								Log.info("status of result: " + resultflag);
								if (resultflag) {
									Log.info(results.get(i).getText());
									results.get(i).click();
									ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Interface to select' table");
									Thread.sleep(8000);
									click_commonMethod(Application, "Action Dropdown", "InterfaceToselect_Actiondropdown", xml);
									Thread.sleep(1000);
									
									click_commonMethod(Application, "Add", "InterfaceToselect_addbuton", xml);

								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = driver.findElements(By.xpath("(//div[@class='row'])[8]//div[div[contains(text(),'"
										+ interfacenumber + "')]]//input"));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								ExtentTestManager.getTest().log(LogStatus.FAIL, " Failure on selecting an Interface to ad with service ");


							}
						}
						break ab;
					}
				}
			} else {
				Log.info("No values found inside the table");
				Log.info("No values available inside the Interfacetoselect table");
			}

		}


		public void viewTrunk_GSX_generateConfiguration(String application) throws InterruptedException, DocumentException {
			
			WebElement lowerCaseRouteelement=getwebelement(xml.getlocator("//locators/" + application + "/createLowerCaseRoute_viewTrunkPage"));
			scrolltoview(lowerCaseRouteelement);
			Thread.sleep(2000);
			
//			addDropdownValues(application, fieldname, xpath, expectedValueToAdd);
			
		}

		
		public String fetchgatewayValue(String application) throws InterruptedException, DocumentException, IOException {
			
			WebElement homePage = getwebelement(xml.getlocator("//locators/" + application + "/HomeBreadcrump"));
			scrolltoview(homePage);
			Thread.sleep(1000);
			
			
			String gateway = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/HomeBreadcrump")));
			
			return gateway;
		}

		public void viewTrunk_PSX_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException {
			
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(7000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'PSX_Execute Configuration' Functionality");
			
			WebElement localRingBack=getwebelement(xml.getlocator("//locators/" + application + "/labelName_localRingBackTone"));
			scrolltoview(localRingBack);
			Thread.sleep(2000);
			
			addDropdownValues_commonMethod(application, "PSX Configuration", "PSXconfigurationDropdown_viewtrunk", expectedConfiguration , xml);
			click_commonMethod(application, "Execute", "viewTrunk_PSX_executeButton" , xml);
			
			
			 Alert alert = driver.switchTo().alert();		
	     		
		     // Capturing alert message.    
		       String alertMessage= driver.switchTo().alert().getText();
		       if(alertMessage.isEmpty()) {
		    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No mEssage displays");
			       Log.info("No Message displays"); 
		       }else {
		    	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
			       Log.info("text message for alert displays as: "+alertMessage);
		       }
		     
		     try {  
		       alert.accept();
		       Thread.sleep(2000);
		       
		       Log.info("accept 2");
		       alert.accept();
		     }catch(Exception e) {
		    	 e.printStackTrace();
		     }
		     
		     
		     
		     if(expectedConfiguration.equalsIgnoreCase("Delete Trunk Group")) {
		    	 verifysuccessmessage(application, "PSX sync started successfully.");
		     }
		     else if(expectedConfiguration.equalsIgnoreCase("Add Destination IP Address")) {
		    	 String alertMessage1=alert.getText();
		    	 if(alertMessage1.isEmpty()) {
			    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on OK, No mEssage displays");
				       Log.info("No Message displays"); 
			       }else {
			    	   ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on OK button, Alert message displays as: "+alertMessage);
				       Log.info("text message for alert displays as: "+alertMessage);
				       
				       alert.dismiss();
//				       alert.accept();
				       
				       
				       
			       }
		    	 
		     }
		}
		
		
		public void viewTrunk_SBC_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'SBC_Execute Configuration' Functionality");
			
			WebElement localRingBack=getwebelement(xml.getlocator("//locators/" + application + "/labelName_localRingBackTone"));
			scrolltoview(localRingBack);
			Thread.sleep(2000);
			
			addDropdownValues_commonMethod(application, "SBC Configuration", "SBCconfigurationDropdown_viewtrunk", expectedConfiguration, xml);
			
			click_commonMethod(application, "Execute", "viewTrunk_SBC_executeButton" , xml);
			
			 Alert alert = driver.switchTo().alert();		
     		
		     // Capturing alert message.    
		       String alertMessage= driver.switchTo().alert().getText();
		       if(alertMessage.isEmpty()) {
		    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No mEssage displays");
			       Log.info("No Message displays"); 
		       }else {
		    	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
			       Log.info("text message for alert displays as: "+alertMessage);
		       }
		       
		       Thread.sleep(3000);
		     
		       //TODO 
		     try {  
		       alert.accept();
		       Thread.sleep(2000);
		       
		       Log.info("accept 2");
		       alert.accept();
		     }catch(Exception e) {
		    	 e.printStackTrace();
		     }

		     
		     //fetch success Message
		     if(expectedConfiguration.equalsIgnoreCase("Cease Trunk Group")) {
		    	 verifysuccessmessage(application, "SBC sync started successfully. Please check the sync status of this Trunk Group.");
		     }
		     else if(expectedConfiguration.equalsIgnoreCase("Synchronize All")) {
		    	 verifysuccessmessage("wholesaleService", "SBC sync has not started because of the manual change in the SBC. Uncheck the 'Manual Configuration' flag "
							+ "to overwrite the changes.");
		     }
			
		}
		

		public void viewTrunk_GSX_executeConfiguration(String application, String expectedConfiguration, String gsxTextvalue) throws InterruptedException, DocumentException, IOException {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'GSX_Execute Configuration' Functionality");
			
			WebElement localRingBack=getwebelement(xml.getlocator("//locators/" + application + "/labelName_localRingBackTone"));
			scrolltoview(localRingBack);
			Thread.sleep(2000);
			
			addDropdownValues_commonMethod(application, "GSX Configuration", "GSXconfigurationDropdown_viewtrunk", expectedConfiguration, xml);
			
			click_commonMethod(application, "Generate Configuration", "viewTrunk_GSX_generateConfigurationButton" , xml);
			
			if((expectedConfiguration.equalsIgnoreCase("Create Trunk Group")) ||  (expectedConfiguration.equalsIgnoreCase("Update trunk group"))) {
				
				String mainWindow=driver.getWindowHandle();
				Set<String> allwindows = driver.getWindowHandles();
	            Iterator<String> itr = allwindows.iterator();
	            while(itr.hasNext())
	            {
	                  String childWindow = itr.next();
	                  if(!mainWindow.equals(childWindow)){
	                        driver.switchTo().window(childWindow);
	                        Log.info(driver.switchTo().window(childWindow).getTitle());
	                          
	                        Thread.sleep(1000);
	                      String gsxConfiguredValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/GSXcongig_textArea")));
	                      if(gsxConfiguredValue.isEmpty()) {
	                    	  ExtentTestManager.getTest().log(LogStatus.PASS, "No values displaying under GSX Configuration");
	                      }else {
	                    	  ExtentTestManager.getTest().log(LogStatus.PASS, "'Under GSX Configuration' value is displaying as: "+ gsxConfiguredValue);
	                      }
	                      
	                      addtextFields_commonMethod(application, "GSX Configuration", "GSXconfig_textArea", gsxTextvalue , xml);
	                      Log.info("came inside child window");
	                        
	                        scrolltoend();
	                        Thread.sleep(2000);
	                        click_commonMethod(application, "Execute", "GSX_config_executeButton", xml);
	                        Thread.sleep(2000);
	                        
	                        waitForpageload();    waitforPagetobeenable();
	                        
	                        click_commonMethod(application, "Close", "GSXconfig_closeButton", xml);
	                  }
	            }
	            
	           Thread.sleep(3000);
	            driver.switchTo().window(mainWindow);
	            Thread.sleep(1000);
			}
			else if(expectedConfiguration.equalsIgnoreCase("Remove Trunk Group")) {
				
				 Alert alert = driver.switchTo().alert();	
				 alert.accept();
			     Thread.sleep(2000);
				
			}
			
            scrollToTop();
           String gsxSuccessMesage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/GSXconfig_sucessMessage")));
           if(gsxSuccessMesage.isEmpty()) {
        	   ExtentTestManager.getTest().log(LogStatus.FAIL, "NO message displays after clicking on 'Execute' button");
           }
           else {
        	   ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Execute' button, success Message displays as: "+gsxSuccessMesage);
           }
         
		}

		
		
		/**
		 *  perform Manual SBC execution
		 * @param application
		 * @throws InterruptedException
		 * @throws DocumentException
		 * @throws IOException 
		 */
		public void addSBC_manualExecutionConfig(String application, String manualConfigurationValue) throws InterruptedException, DocumentException, IOException {
			
			scrolltoend();
			Thread.sleep(2000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Add SBC Manual Configuration' Functionality");
			
		try {	
			boolean SBCHeder=getwebelement(xml.getlocator("//locators/" + application + "/SBCmanualConfig_PanelHeader")).isDisplayed();
			if(SBCHeder) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'SBC Manully Executed Configuration' panel is displaying");
				Log.info("'SBC Manully Executed Configuration' panel is displaying");
				
				
				click_commonMethod(application, "Action", "SBCManualConfig_actionDropdown", xml);
				click_commonMethod(application, "Add link", "SBC_addLink", xml);
				
				//manual configuration on SBC page
				addtextFields_commonMethod(application, "SBC Manual Configuration", "manualConfiguration_textArea", manualConfigurationValue, xml);
				click_commonMethod(application, "Save", "saveButton_manualConfiguration", xml);
				
				scrollToTop();
				Thread.sleep(1000);
				
				verifysuccessmessage(application, "Manual Configuration added Successfully");   //verify success message
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'SBC Manully Executed Configuration' panel is not displaying");
				Log.info("'SBC Manully Executed Configuration' panel is not displaying");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'SBC Manully Executed Configuration' panel is not displaying");
			Log.info("'SBC Manully Executed Configuration' panel is not displaying");
		}
		}
		
		
		public void verifySBCfileAdded(String application) throws InterruptedException, DocumentException, IOException {
			
			
			scrolltoend();
			Thread.sleep(1000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying Added Values under 'Manual SBC Configuration' panel");
			
			WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/SBC_selectCreatedValue"));
			String SBCfilenameCreated=Gettext(filename);
			
			if(SBCfilenameCreated.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "SBC Manually Executed configuration file name is not displaying");
				Log.info("SBC Manually Executed configuration file name is not displaying");
			}else {
				
				int i=0; int k=0; int j=0;
				
			//Fetching Column Names	
				List<WebElement> columns=getwebelements(xml.getlocator("//locators/" + application + "/SBC_columnNames"));
				int listOfColumns=columns.size();
				
				String columnName[]=new String[listOfColumns];
				for (WebElement column : columns) {
					
					columnName[k]=column.getText();
					k++;
				}
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "column names display as: "+ columnName[1] + "  " + columnName[2]);  //printing column Names
				
				
			//Fetching value under File Column	
				List<WebElement> files=getwebelements(xml.getlocator("//locators/" + application + "/SBC_filenames"));
				int listOfFiles=files.size();
				
				String[] fileNameValues=new String[listOfFiles];
				
				for(WebElement filenametransfer : files) {
					
					fileNameValues[i] = filenametransfer.getText();
					i++;
				}
				
			
			//Fetching value under Date Column
				List<WebElement> dates=getwebelements(xml.getlocator("//locators/" + application + "/SBC_dates"));
				int listOfDates=dates.size();
				
				String[] dateValues=new String[listOfDates];
				
				for(WebElement dateList : dates) {
					
					dateValues[j] = dateList.getText();
					j++;
				}
			
			
				ExtentTestManager.getTest().log(LogStatus.INFO, "Values under 'SBC Manually Executed Configuration' value displays as: ");
				
			for(int y=0; y<fileNameValues.length; y++) {
			ExtentTestManager.getTest().log(LogStatus.PASS,  fileNameValues[y] +"      "+ dateValues[y]);
			}
			
			}
		}


		public void editGSX_manualExecutionConfig(String application, String editGSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Edit GSX Manual Configuration' Functionality");
			
			click_commonMethod(application, "GSXcreated", "GSX_selectCreatedValue", xml);
			
			click_commonMethod(application, "Action", "GSXManualConfig_actionDropdown", xml);  //click acton dropdown
			click_commonMethod(application, "Edit link", "GSX_editLink", xml);   //click on edit link
			
//			Log.info("date displaying as : "+getCurrentDate());
//			Log.info("username displays as: "+Getkeyvalue("APT_login_1_Username"));
			
			Thread.sleep(6000);
			
			String mainWindow=driver.getWindowHandle();
			Set<String> allwindows = driver.getWindowHandles();
            Iterator<String> itr = allwindows.iterator();
            while(itr.hasNext())
            {
                  String childWindow = itr.next();
                  if(!mainWindow.equals(childWindow)){
                        driver.switchTo().window(childWindow);
                        Log.info(driver.switchTo().window(childWindow).getTitle());
                          
//                        driver.manage().window().maximize();
                        Thread.sleep(1000);
                        
//                       Write here  whatever you want to do and perform
                        Log.info("came inside child window");
                        
                        edittextFields_commonMethod(application, "GSX manual Configuration", "GSX_editPage_teaxtArea", editGSXmanualConfigvalur, xml);
                       
                        scrolltoend();
                        Thread.sleep(2000);
                        
                        click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
                        
                  }
            }
            
            driver.switchTo().window(mainWindow);
            Thread.sleep(1000);
            verifysuccessmessage(application, "Manual Configuration updated Successfully");

            
		}

		
		public void editPSX_manualExecutionConfig(String application, String editPSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Edit PSX Manual Configuration' Functionality");
			
			click_commonMethod(application, "PSXcreated", "PSX_selectCreatedValue", xml);
			
			click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);  //click acton dropdown
			click_commonMethod(application, "Edit link", "PSX_editLink", xml);   //click on edit link
			
//			Log.info("date displaying as : "+getCurrentDate());
//			Log.info("username displays as: "+Getkeyvalue("APT_login_1_Username"));
			
			String mainWindow=null;
			
			Thread.sleep(7000);
			
			mainWindow=driver.getWindowHandle();
			Set<String> allwindows = driver.getWindowHandles();
            Iterator<String> itr = allwindows.iterator();
            while(itr.hasNext())
            {
                  String childWindow = itr.next();
                  if(!mainWindow.equals(childWindow)){
                        driver.switchTo().window(childWindow);
                        Log.info(driver.switchTo().window(childWindow).getTitle());
                          
//                        driver.manage().window().maximize();
                        Thread.sleep(1000);
                        
//                       Write here  whatever you want to do and perform
                        Log.info("came inside child window");
                        
                        edittextFields_commonMethod(application, "PSX manual Configuration", "GSX_editPage_teaxtArea", editPSXmanualConfigvalur, xml);
                        
                        scrolltoend();
                        Thread.sleep(1000);
                        
                        click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
                        
                  }
            }
            
            driver.switchTo().window(mainWindow);
            Thread.sleep(1000);
            verifysuccessmessage(application, "Manual Configuration updated Successfully");

            
		}


		public void editSBC_manualExecutionConfig(String application, String editSBCmanualConfigvalur) throws InterruptedException, DocumentException, IOException {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Edit SBC Manual Configuration' Functionality");
			
			click_commonMethod(application, "PSXcreated", "SBC_selectCreatedValue", xml);
			
			click_commonMethod(application, "Action", "SBCManualConfig_actionDropdown", xml);  //click acton dropdown
			click_commonMethod(application, "Edit link", "SBC_editLink", xml);   //click on edit link
			
			Log.info("date displaying as : "+getCurrentDate());
			Log.info("username displays as: "+Getkeyvalue("APT_login_1_Username"));
			
			Thread.sleep(6000);
			
			String mainWindow=driver.getWindowHandle();
			Set<String> allwindows = driver.getWindowHandles();
            Iterator<String> itr = allwindows.iterator();
            while(itr.hasNext())
            {
                  String childWindow = itr.next();
                  if(!mainWindow.equals(childWindow)){
                        driver.switchTo().window(childWindow);
                        Log.info(driver.switchTo().window(childWindow).getTitle());
                          
//                        driver.manage().window().maximize();
                        Thread.sleep(1000);
                        
                        edittextFields_commonMethod(application, "GSX manual Configuration", "GSX_editPage_teaxtArea", editSBCmanualConfigvalur, xml);
                       
                        scrolltoend();
                        Thread.sleep(2000);
                        click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
                        
                  }
            }
            
            driver.switchTo().window(mainWindow);
            Thread.sleep(1000);
            verifysuccessmessage(application, "Manual Configuration updated Successfully");

            
		}

		
		
		public void deleteSBC_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {
			
			scrolltoend();
			Thread.sleep(2000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Delete SBC Manual Configuration' Functionality");
			
			click_commonMethod(application, "SBCcreated", "SBC_selectCreatedValue", xml);
			
			click_commonMethod(application, "Action", "SBCManualConfig_actionDropdown", xml);  //click acton dropdown
			click_commonMethod(application, "Delete link", "SBC_deleteLink", xml);   //click on edit link
			
			 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
             if(DeleteAlertPopup.isDisplayed())
             {
           	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage")));
           	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
           	 
                click_commonMethod(application, "Delete", "deletebutton", xml);
                
                scrollToTop();
                Thread.sleep(1000);
                
                verifysuccessmessage(application, "Deleted SBC manual config successfully.");
             }
             else
             {
                   Log.info("Delete alert popup is not displayed");
                   ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
             }
			
		}
		
	/**
	 * perform delete operation under PSX manually executed configuration panel	
	 * @param application
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
		public void deletePSX_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {
			
			scrolltoend();
			Thread.sleep(2000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Delete PSX Manual Configuration' Functionality");
			
			click_commonMethod(application, "PSXcreated", "PSX_selectCreatedValue", xml);
			
			click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);  //click action dropdown
			click_commonMethod(application, "Delete link", "PSX_deleteLink", xml);   //click on edit link
			
			 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
             if(DeleteAlertPopup.isDisplayed())
             {
           	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage")));
           	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
           	 
                click_commonMethod(application, "Delete", "deletebutton", xml);
                
                scrollToTop();
                Thread.sleep(1000);
                
                verifysuccessmessage(application, "Deleted PSX manual config successfully.");
             }
             else
             {
                   Log.info("Delete alert popup is not displayed");
                   ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
             }
		}
		
		
		/**
		 * perform delete operation under GSX manually executed configuration panel	
		 * @param application
		 * @throws InterruptedException
		 * @throws DocumentException
		 * @throws IOException
		 */
			public void deleteGSX_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {
				
				scrolltoend();
				Thread.sleep(2000);
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete GSX Manual Configuration' Functionality");
				
				click_commonMethod(application, "PSXcreated", "GSX_selectCreatedValue", xml);
				
				click_commonMethod(application, "Action", "GSXManualConfig_actionDropdown", xml);  //click action dropdown
				click_commonMethod(application, "Delete link", "GSX_deleteLink", xml);   //click on edit link
				
				 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
	             if(DeleteAlertPopup.isDisplayed())
	             {
	           	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage")));
	           	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
	           	 
	                click_commonMethod(application, "Delete", "deletebutton", xml);
	                
	                scrollToTop();
	                Thread.sleep(1000);
	                
	                verifysuccessmessage(application, "Deleted GSX manual config successfully.");
	             }
	             else
	             {
	                   Log.info("Delete alert popup is not displayed");
	                   ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
	             }
			}
		
		
		/**
		 *  perform Manual PSX execution
		 * @param application
		 * @throws InterruptedException
		 * @throws DocumentException
		 * @throws IOException 
		 */
		public void addPSX_manualExecutionConfig(String application, String PSXmanualConfiguratio) throws InterruptedException, DocumentException, IOException {
			
			scrolltoend();
			Thread.sleep(2000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Add Verifying 'PSX Manual Configuration' Functionality");
			
			boolean PSXHeader=getwebelement(xml.getlocator("//locators/" + application + "/PSXmanualConfig_PanelHeader")).isDisplayed();
			if(PSXHeader) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'PSX Manully Executed Configuration' panel is displaying");
				Log.info("'PSX Manully Executed Configuration' panel is displaying");
				
				
				click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);
				Thread.sleep(5000);
				click_commonMethod(application, "Add link", "PSX_addLink", xml);
				
				//TODO compare Header name
				
			//Text Area	
				addtextFields_commonMethod(application, "Manul Configuration", "manualConfiguration_textArea", PSXmanualConfiguratio, xml);
				
				scrolltoend();
				Thread.sleep(1000);
				
				click_commonMethod(application, "Save", "saveButton_manualConfiguration", xml);   //click on Save buttton
				Thread.sleep(1000);
				
				verifysuccessmessage(application, "Manual Configuration added Successfully");  //verify success Message
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'PSX Manully Executed Configuration' panel is not displaying");
				Log.info("'PSX Manully Executed Configuration' panel is not displaying");
			}
		}
		
		
		/**
		 *  perform Manual GSX execution
		 * @param application
		 * @throws InterruptedException
		 * @throws DocumentException
		 * @throws IOException 
		 */
		public void addGSX_manualExecutionConfig(String application, String GSXmanualConfiguratio) throws InterruptedException, DocumentException, IOException {
			
			scrolltoend();
			Thread.sleep(2000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "Add Verifying 'GSX Manual Configuration' Functionality");
			
			boolean PSXHeader=getwebelement(xml.getlocator("//locators/" + application + "/GSXmanualConfig_PanelHeader")).isDisplayed();
			if(PSXHeader) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'GSX Manully Executed Configuration' panel is displaying");
				Log.info("'GSX Manully Executed Configuration' panel is displaying");
				
				
				click_commonMethod(application, "Action", "GSXManualConfig_actionDropdown", xml);
				click_commonMethod(application, "Add link", "GSX_addLink", xml);
				
				//TODO compare Header name
				
			//Text Area	
				addtextFields_commonMethod(application, "Manul Configuration", "manualConfiguration_textArea", GSXmanualConfiguratio, xml);
				
				scrolltoend();
				Thread.sleep(1000);
				
				click_commonMethod(application, "Save", "saveButton_manualConfiguration", xml);   //click on Save buttton
				Thread.sleep(1000);
				
				verifysuccessmessage(application, "Manual Configuration added Successfully");  //verify success Message
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'GSX Manully Executed Configuration' panel is not displaying");
				Log.info("'GSX Manully Executed Configuration' panel is not displaying");
			}
		}
		
		
	/**
	 * Verify the files added under PSX Manually Executed Configuration panel	
	 * @param application
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void verifyPSXfileAdded(String application) throws InterruptedException, DocumentException, IOException {
			
			scrolltoend();
			Thread.sleep(1000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Added value under PSX Manual Configuration' Functionality");
			
			WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/PSX_selectCreatedValue"));
			String PSXfilenameCreated=Gettext(filename);
			
			if(PSXfilenameCreated.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "PSX Manually Executed configuration file name is not displaying");
				Log.info("PSX Manually Executed configuration file name is not displaying");
			}else {
				
				int i=0; int k=0; int j=0;
				
			//Fetching Column Names	
				List<WebElement> columns=getwebelements(xml.getlocator("//locators/" + application + "/PSX_columnNames"));
				int listOfColumns=columns.size();
				
				String columnName[]=new String[listOfColumns];
				for (WebElement column : columns) {
					
					columnName[k]=column.getText();
					k++;
				}
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "column names display as: "+ columnName[1] + "  " + columnName[2]);  //printing column Names
				
				
			//Fetching value under File Column	
				List<WebElement> files=getwebelements(xml.getlocator("//locators/" + application + "/PSX_fileName"));
				int listOfFiles=files.size();
				
				String[] fileNameValues=new String[listOfFiles];
				
				for(WebElement filenametransfer : files) {
					
					fileNameValues[i] = filenametransfer.getText();
					i++;
				}
				
			
			//Fetching value under Date Column
				List<WebElement> dates=getwebelements(xml.getlocator("//locators/" + application + "/PSX_dates"));
				int listOfDates=dates.size();
				
				String[] dateValues=new String[listOfDates];
				
				for(WebElement dateList : dates) {
					
					dateValues[j] = dateList.getText();
					j++;
				}
			
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "Values under 'PSX Manually Executed Configuration' value displays as: ");
			
			for(int y=0; y<fileNameValues.length; y++) {
			ExtentTestManager.getTest().log(LogStatus.PASS, fileNameValues[y] +"      "+ dateValues[y]);
			}
				
			}
		}


	/**
	 * Verify the files added under GSX Manually Executed Configuration panel	
	 * @param application
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void verifyGSXfileAdded(String application) throws InterruptedException, DocumentException, IOException {
			
			scrolltoend();
			Thread.sleep(1000);
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Added value under GSX Manual Configuration' Functionality");
			
			WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/GSX_selectCreatedValue"));
			String GSXfilenameCreated=Gettext(filename);
			
			if(GSXfilenameCreated.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "GSX Manually Executed configuration file name is not displaying");
				Log.info("GSX Manually Executed configuration file name is not displaying");
			}else {
				
				int i=0; int k=0; int j=0;
				
			//Fetching Column Names	
				List<WebElement> columns=getwebelements(xml.getlocator("//locators/" + application + "/GSX_columnNames"));
				int listOfColumns=columns.size();
				
				String columnName[]=new String[listOfColumns];
				for (WebElement column : columns) {
					
					columnName[k]=column.getText();
					k++;
				}
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "column names display as: ");  //printing column Names
				ExtentTestManager.getTest().log(LogStatus.PASS,  columnName[1] + "     	     " + columnName[2]);
				
			//Fetching value under File Column	
				List<WebElement> files=getwebelements(xml.getlocator("//locators/" + application + "/GSX_fileName"));
				int listOfFiles=files.size();
				
				String[] fileNameValues=new String[listOfFiles];
				
				for(WebElement filenametransfer : files) {
					
					fileNameValues[i] = filenametransfer.getText();
					i++;
				}
				
			
			//Fetching value under Date Column
				List<WebElement> dates=getwebelements(xml.getlocator("//locators/" + application + "/GSX_dates"));
				int listOfDates=dates.size();
				
				String[] dateValues=new String[listOfDates];
				
				for(WebElement dateList : dates) {
					
					dateValues[j] = dateList.getText();
					j++;
				}
			
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "Values under 'GSX Manually Executed Configuration' value displays as: ");
			
			for(int y=0; y<fileNameValues.length; y++) {
			ExtentTestManager.getTest().log(LogStatus.PASS, fileNameValues[y] +"      "+ dateValues[y]);
			}
			
			}
		}

	
	
	
	public void selectInterfaceAndClickonConfigureLInk_MASswitch(String application, String deviceName, String interfaceName) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Configure Interface_MAS Swicth' Functionality");
		
		WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
		scrolltoview(managementOptions_header);
		Thread.sleep(2000);
		
		
		WebElement SelectInterface= getwebelement("//div[div[b[text()='"+ deviceName+"']]]//following-sibling::div//div[@role='gridcell'][text()='"+ interfaceName +"']");
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on "+ interfaceName + "Interface");
			Thread.sleep(1000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", deviceName)));
			click_commonMethod(application, "Configure", "configureLink", xml);

		  }else {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, interfaceName + " is not displaying under 'MAS Switch' for the device "+ deviceName);
		  }
		
		}
	
	
	public void selectInterfaceAndClickonConfigureLInk_PEdevice(String application, String deviceName, String interfaceName) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Configure Interface_PE device' ");
		
		WebElement MASswitchPanel_header= getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_panelHeader"));
		scrolltoview(MASswitchPanel_header);
		Thread.sleep(2000);
		
		WebElement SelectInterface= getwebelement("//div[div[b[text()='"+ deviceName+"']]]//following-sibling::div//div[@role='gridcell'][text()='"+ interfaceName +"']");
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on "+ interfaceName + "Interface");
			Thread.sleep(1000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", deviceName)));
			click_commonMethod(application, "Configure", "configureLink", xml);

		  }else {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, interfaceName + " is not displaying under 'MAS Switch' for the device "+ deviceName);
		  }
		
		}
	

public void selectInterface_AndDelete_MASswitch(String application, String deviceName, String interfaceName) throws InterruptedException, DocumentException {
		
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete Interface' Functionality");
		
		WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
		scrolltoview(managementOptions_header);
		Thread.sleep(2000);
		
		
//		VerifyText(text);
		if(getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_showInterfaceLink")).isDisplayed())
		{
		click_commonMethod(application, "Show Interfaces", "MASswitch_showInterfaceLink", xml);
		
		Thread.sleep(1000);
		WebElement SelectInterface= getwebelement("//div[div[b[text()='"+ deviceName+"']]]//following-sibling::div//div[@role='gridcell'][text()='"+ interfaceName +"']");
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on "+ interfaceName + "Interface");
			Thread.sleep(1000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", deviceName)));
			
			 click_commonMethod(application, "delete", "MAS_View_Action_DeleteLink", xml);
             Thread.sleep(2000);
             WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
             if(DeleteAlertPopup.isDisplayed())
             {
                   click_commonMethod(application, "Delete", "deletebutton", xml);
             }
             else
             {
                   Log.info("Delete alert popup is not displayed");
                   ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
             }
             compareText(application, "Device delete success message", "MAS_deleteSuccessMessage", "Interface deleted successfully", xml);
             
             
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, interfaceName + " is not displaying under 'MAS Switch' for the device "+ deviceName);
		  }
		}else {
//			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Show Interface' link is not available under 'MAS switch'");
			
			Thread.sleep(1000);
			WebElement SelectInterface= getwebelement("//div[div[b[text()='"+ deviceName+"']]]//following-sibling::div//div[@role='gridcell'][text()='"+ interfaceName +"']");
			if(SelectInterface.isDisplayed())
			{
				Clickon(SelectInterface);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on "+ interfaceName + "Interface");
				Thread.sleep(1000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", deviceName)));

				 click_commonMethod(application, "delete", "MAS_View_Action_DeleteLink", xml);
	             Thread.sleep(2000);
	             WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
	             if(DeleteAlertPopup.isDisplayed())
	             {
	                   click_commonMethod(application, "Delete", "deletebutton", xml);
	             }
	             else
	             {
	                   Log.info("Delete alert popup is not displayed");
	                   ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
	             }
	             compareText(application, "Device delete success message", "MAS_deleteSuccessMessage", "Interface deleted successfully", xml);
	             

			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, interfaceName + " is not displaying under 'MAS Switch' for the device "+ deviceName);
			  }
		}
		
		}


public void selectInterface_AndDelete_PEdevice(String application, String deviceName, String interfaceName) throws InterruptedException, DocumentException {
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete Interface_PE device");
	
	WebElement MASswitchPanel_header= getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_panelHeader"));
	scrolltoview(MASswitchPanel_header);
	Thread.sleep(2000);
	
	
	Thread.sleep(1000);
	WebElement SelectInterface= getwebelement("//div[div[b[text()='"+ deviceName+"']]]//following-sibling::div//div[@role='gridcell'][text()='"+ interfaceName +"']");
	if(SelectInterface.isDisplayed())
	{
		Clickon(SelectInterface);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on "+ interfaceName + "Interface");
		Thread.sleep(1000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", deviceName)));
		
		 click_commonMethod(application, "delete", "MAS_View_Action_DeleteLink", xml);
         Thread.sleep(2000);
         WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
         if(DeleteAlertPopup.isDisplayed())
         {
               click_commonMethod(application, "Delete", "deletebutton", xml);
         }
         else
         {
               Log.info("Delete alert popup is not displayed");
               ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
         }
         
         verifysuccessmessage(application, "Interface deleted successfully");
         
         
	  }else {
		  ExtentTestManager.getTest().log(LogStatus.FAIL, interfaceName + " is not displaying under 'PE Device' for the device "+ deviceName);
	  }
	
	}

	
	public void viewInterface_MASswitch(String application, String deviceName, String Interface, String link, String interfaceAddressrange,
			String vlanID) throws InterruptedException, DocumentException {
		
		waitForpageload();
		Thread.sleep(2000);
		
		
		compareText_InViewPage(application, "Device Name", deviceName, xml);
		
		compareText_InViewPage(application, "Interface", Interface, xml);
		
		compareText_InViewPage(application, "Link/Circuit Id", link, xml);
		
		compareText_InViewPage(application, "Interface Address Range", interfaceAddressrange, xml);
		
		compareText_InViewPage(application, "VLAN Id", vlanID, xml);
		
	}
	
	
	public void viewInterface_PEdevice(String application, String deviceName, String Interface, String link, String interfaceAddressrange,
			String vlanID) throws InterruptedException, DocumentException {
		
		waitForpageload();
		Thread.sleep(2000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Added Interface Value");
		
		compareText_InViewPage(application, "Device Name", deviceName, xml);
		
		compareText_InViewPage(application, "Interface", Interface, xml);
		
		compareText_InViewPage(application, "Link/Circuit Id", link, xml);
		
		compareText_InViewPage(application, "Interface Address Range", interfaceAddressrange, xml);
		
		compareText_InViewPage(application, "VLAN Id", vlanID, xml);
		
	}
	
	
	public void deleteTrunk(String application, String trunkGroupName, String siteOrderName) throws Exception {
		
		scrolltoend();
		Thread.sleep(3000);
		
		scrolltoend();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete Trunk' Funtionality");
		
		WebElement getTrunkRow=getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedTrunk_InViewServicePage").replace("value", trunkGroupName));
		safeJavaScriptClick(getTrunkRow);
		
		
		//click on Action dropdown under Trunk Panel	
			WebElement trukPanel_actionDropdown=getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel_ActionDropdown").replace("value", siteOrderName));
			safeJavaScriptClick(trukPanel_actionDropdown);
		
		//click on delete link
			click_commonMethod(application, "Delete", "MAS_View_Action_DeleteLink", xml);
			
			
			 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
	         if(DeleteAlertPopup.isDisplayed())
	         {
	       	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
	       	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
	       	 
	            click_commonMethod(application, "Delete", "deletebutton", xml);
	            
	            scrollToTop();
	            Thread.sleep(1000);
	            
	            verifysuccessmessage(application, "Trunk deleted successfully");
	         }
	         else
	         {
	               Log.info("Delete alert popup is not displayed");
	               ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
	         }
				
	}
	
	

	public void dump_viewServicepage(String application) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying dump");
		
		 WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/OrderPanel"));
			scrolltoview(orderPanel);
			Thread.sleep(3000);
		
			click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
		   Thread.sleep(3000);
		   click_commonMethod(application, "Dump", "Editservice_Dumplink", xml);
		   Thread.sleep(2000);
		   
		   boolean DumpPage=false;
		   DumpPage=getwebelement(xml.getlocator("//locators/" + application + "/dumpPage_header")).isDisplayed();
		   if(DumpPage) {
			   ExtentTestManager.getTest().log(LogStatus.PASS, "Service Dump Detail page is displaying");
			   Log.info("Service Dump Detail page is displaying");
			  
			  //Header 
			   String headerName=getwebelement(xml.getlocator("//locators/" + application + "/dumpheaderName")).getText();
			   if(headerName.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "Header name is not displaying");
				   Log.info("Header name is not displaying");
				 
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "Dump header name is displaying as "+ headerName);  
				   Log.info("Dump header name is displaying as "+ headerName);
			   }
			   
			   //body
			   String bodyContent=getwebelement(xml.getlocator("//locators/" + application + "/dumpMessage_body")).getText();
			   if(bodyContent.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "Dump value is not displaying");
				   Log.info("Dump value is not displaying");
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "Dump value is displaying as "+bodyContent);
				   Log.info("Dump value is displaying as "+bodyContent);
			   }
			   
			   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/dump_xButton")));
			   Thread.sleep(2000);
			   
			   
		   }else {
			   ExtentTestManager.getTest().log(LogStatus.FAIL, "Service Dump Detail page is not displaying");
			   Log.info("Service Dump Detail page is not displaying");

		   }
	}

	
	public void manageSubnet_viewServicepage(String application) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Manage Subnet'");
		
		 WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/OrderPanel"));
			scrolltoview(orderPanel);
			Thread.sleep(3000);
		
			click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
		   Thread.sleep(3000);
		   click_commonMethod(application, "Manage Subnet IPv6", "manageSubnet_IPv6", xml);
		   Thread.sleep(2000);
		   
		   boolean DumpPage=false;
		   DumpPage=getwebelement(xml.getlocator("//locators/" + application + "/dumpPage_header")).isDisplayed();
		   if(DumpPage) {
			   ExtentTestManager.getTest().log(LogStatus.PASS, "Manage Subnet IPv6 page is displaying");
			   Log.info("manage subnet ipv6 page is displaying");
			  
			  //Header 
			   String headerName=getwebelement(xml.getlocator("//locators/" + application + "/dumpheaderName")).getText();
			   if(headerName.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "Header name is not displaying");
				   Log.info("Header name is not displaying");
				 
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "manage Subnet IPv6 header name is displaying as "+ headerName);  
				   Log.info("manage subnet ipv6 header name is displaying as "+ headerName);
			   }
			   
			   //body
			   String bodyContent=getwebelement(xml.getlocator("//locators/" + application + "/manageSubnet_successMSG")).getText();
			   if(bodyContent.isEmpty()) {
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "manage subnet value is not displaying");
				   Log.info("manage subnet value is not displaying");
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "manage subnet message is displaying as "+bodyContent);
				   Log.info("manage subnet message is displaying as "+bodyContent);
			   }
			   
			   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/dump_xButton")));
			   Thread.sleep(2000);
			   
			   
		   }else {
			   ExtentTestManager.getTest().log(LogStatus.FAIL, "manage subnet ipv6 page is not displaying");
			   Log.info("manage subnet ipv6 page is not displaying");

		   }
	}

	
	public String fetchTrunkName(String application) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		Thread.sleep(1000);
		
		WebElement trunkGroupNameelement = getwebelement(xml.getlocator("//locators/" + application + "/fetchTrunkGroupName"));
		String trunkGroupName=Gettext(trunkGroupNameelement);
			
		String[] trunkNames=trunkGroupName.split("-");
		Log.info("trunk name: "+ trunkNames[1]);
		
		return trunkNames[1];
		
	}
	
	
	public void clickOnCPEdeviceLink(String application, String trunkGroupName, String siteOrderName) throws Exception {
		
		scrolltoend();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add CPE Device' Functionality");
		
		WebElement getTrunkRow=getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedTrunk_InViewServicePage").replace("value", trunkGroupName));
		safeJavaScriptClick(getTrunkRow);
		
		
		//click on Action dropdown under Trunk Panel	
			WebElement trukPanel_actionDropdown=getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel_ActionDropdown").replace("value", siteOrderName));
			safeJavaScriptClick(trukPanel_actionDropdown);
			
		
		//Add CPE link
			click_commonMethod(application, "Add CPE device_Link", "addCPEdeviceLink" , xml);
		
	}
	
	
	
	public void addCPEdevice(String application, String RouterId, String VendorModel, String ManagamentAddress, String Snmpro,
			String Snmprw, String SNMPv3ContaxtName, String SNMPv3ContextEngineId, String SNMPv3securityUsername, String SNMPv3AutoProto, String SNMPv3AuthPasswrd,
			String Country, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode,
    		String sitename, String sitecode,  String premisename,  String premisecode, 
    		String newsite, String NewPremise) throws InterruptedException, DocumentException, IOException {
		
		addtextFields_commonMethod(application, "Router ID" , "routerId"  , RouterId, xml);   //Router ID
		
		addDropdownValues_commonMethod_ForSpantag(application, "Vendor/Model" , "CPE_vendorModel" , VendorModel, xml);   //Vendor Model
		
		addtextFields_commonMethod(application, "Management Address", "CPE_manageAddress", ManagamentAddress, xml);   //Management Address
		
		addtextFields_commonMethod(application, "Snmpro", "CPE_snmpro" , Snmpro , xml);   //Snmpro
		
		addtextFields_commonMethod(application, "Snmprw" , "CPE_snmprw" , Snmprw, xml);   //Snmprw
		
		addtextFields_commonMethod(application, "Snmp V3 Context Name", "CPE_snmpV3ContextName" , SNMPv3ContaxtName, xml);   //Snmp V3 Context Name
		
		addtextFields_commonMethod(application, "Snmp V3 Context Engine ID" , "CPE_snmpV3ContextEngineID", SNMPv3ContextEngineId, xml);   //Snmp V3 Context Engine ID
		
		addtextFields_commonMethod(application, "Snmp V3 Security Username" , "CPE_snmpv3SecurityUserName", SNMPv3securityUsername, xml);   //Snmp V3 Security Username
		
		addDropdownValues_commonMethod(application, "Snmp V3 Auth Proto" , "CPE_snmpv3AuthProto" , SNMPv3AutoProto, xml);   ////Snmp V3 Auth Proto
		
		addtextFields_commonMethod(application, "Snmp V3 Auth Password", "CPE_snmpv3AuthPasswrd" , SNMPv3AuthPasswrd, xml);   //Snmp V3 Auth Password
		
		
	//select country
		scrolltoend();
		Thread.sleep(2000);
		
//		addDropdownValues_commonMethod(application, "Country", "countryinput", Country, xml);
		selectValueInsideDropdown(application, "countryinput", "Country", Country, xml);
		
	//New City		
		if(existingcity.equalsIgnoreCase("no") & newcity.equalsIgnoreCase("yes")) {
			Clickon_addToggleButton(application, "addcityswitch");
		   //City name
			addtextFields_commonMethod(application, "City Name", "citynameinputfield", cityname, xml);
		   //City Code	
			addtextFields_commonMethod(application, "City Code", "citycodeinputfield", Citycode, xml);
		   //Site name
			addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", sitename, xml);
		   //Site Code
			addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", sitecode, xml);
		   //Premise name	
			addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", premisename, xml);
		   //Premise Code	
			addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", premisecode, xml);
				
		}	
	
	//Existing City	
		else if(existingcity.equalsIgnoreCase("yes") & newcity.equalsIgnoreCase("no")) {
			
//		   addDropdownValues_commonMethod(application, "City", "citydropdowninput", existingcityvalue, xml);
		   selectValueInsideDropdown(application, "citydropdowninput", "City", existingcityvalue, xml);
			
			
		 //Existing Site
			  if(existingsite.equalsIgnoreCase("yes") & newsite.equalsIgnoreCase("no")) {
//				  addDropdownValues_commonMethod(application, "Site", "sitedropdowninput", existingsitevalue, xml);
				  selectValueInsideDropdown(application, "sitedropdowninput", "Site", existingsitevalue, xml);
				  
			 //Existing Premise
				  if(existingpremise.equalsIgnoreCase("yes") & NewPremise.equalsIgnoreCase("no")) {
//					  addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", existingpremisevalue, xml);
					  selectValueInsideDropdown(application, "premisedropdowninput", "Premise", existingpremisevalue, xml);
		          	 }
				  
				//New Premise  
				  else if(existingpremise.equalsIgnoreCase("no") & NewPremise.equalsIgnoreCase("yes")) {
					  
					  Clickon_addToggleButton(application, "addpremiseswitch");
					  //Premise name	
						addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", premisename, xml);
					   //Premise Code	
						addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", premisecode, xml);
				  } 
	         	}
			  
		  else if(existingsite.equalsIgnoreCase("no") & newsite.equalsIgnoreCase("yes")) {
			  	
			  Clickon_addToggleButton(application, "addsiteswitch");
			  //Site name
				addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", sitename, xml);
			   //Site Code
				addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", sitecode, xml);
			   //Premise name	
				addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", premisename, xml);
			   //Premise Code	
				addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", premisecode, xml);
			  }
		}

		scrolltoend();
		Thread.sleep(1000);
		
		clickOnBankPage();
		Thread.sleep(200);
		
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "trunk_okButton" , xml);
		Thread.sleep(2000);
		
	}
	
	
	public void clickOnEditInterfaceLink(String application) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit Interface' Functionality");
		
		click_commonMethod(application, "Action", "configure_EditInterface_ActioButton" , xml);
		Thread.sleep(1000);
		
		click_commonMethod(application, "Edit_Link", "editLink_InViewPage" , xml);
	}
	
	
	public void editInterface(String application, String MAS_AccessMedia, String MAS_Network,
			String MAS_HSRPBGP, String MAS_Interface, String MAS_InterfaceAddressRange,
			String MAS_InterfaceAddressMask, String	MAS_HSRPIP, String	MAS_InterfaceAddressRangeIPV6, String MAS_HSRPIPv6Address, 
			String	MAS_PrimaryIPv6onMas1, String MAS_SecondaryIPv6onMas2, String MAS_GroupNumber, String MAS_Link, String MAS_VLANID,
			String	MAS_IVManagement, String MAS_generateConfiguration, String MAS_HSRPTrackInterface, String MAS_HSRPAuthentication) throws InterruptedException, DocumentException, IOException {
		
		waitForpageload();
		waitforPagetobeenable();
		
		addDropdownValues(application, "Access Media", "MAS_PE_AccessMediaDropdown", MAS_AccessMedia);  //Access Media Dropdown
		WebElement accessMediaElement = getwebelement(xml.getlocator("//locators/" + application + "/fetchMAS_AccessMediaDropdown")); 
		String accessMediaActualValue=Gettext(accessMediaElement);
		
		if(accessMediaActualValue.equalsIgnoreCase("VPN")) {
			
			edittextFields_commonMethod(application, "Interface", "MAS_PE_InterfaceTextfield", MAS_Interface, xml);   //interface Text Field
			addDropdownValues_commonMethod(application, "Network", "MAS_PE_networkDropdown", MAS_Network, xml);   //network Dropdown
			edittextFields_commonMethod(application, "Interface Address Range", "MAS_PE_InterfaceAddressRangeTextfield", MAS_InterfaceAddressRange, xml);   //interface address range text field
			edittextFields_commonMethod(application, "Interface Address/Mask" , "MAS_PE_InterfaceAddressMaskTextfield", MAS_InterfaceAddressMask, xml);   //Interface Address/Mask textfield
			edittextFields_commonMethod(application, "HSRP IP", "MAS_PE_HSRPIPTextfield", MAS_HSRPIP, xml);   //HSRP IP text field
			edittextFields_commonMethod(application, "Interface Address Range IPV6", "MAS_PE_InterfaceAddressRangeIPV6Textfield", MAS_InterfaceAddressRangeIPV6, xml);   //Interface Address Range IPV6 text field
			edittextFields_commonMethod(application, "HSRP IPv6 Address", "MAS_PE_HSRPIPv6AddressTextfield", MAS_HSRPIPv6Address, xml);   //HSRP IPv6 Address text field
			edittextFields_commonMethod(application, "Primary IPv6 on Mas-1", "MAS_PE_PrimaryIPv6onMas1Textfield", MAS_PrimaryIPv6onMas1, xml);   //Primary IPv6 on Mas-1 textfield
			edittextFields_commonMethod(application, "Secondary IPv6 on Mas-2", "MAS_PE_SecondaryIPv6onMas2Textfield", MAS_SecondaryIPv6onMas2, xml);   //Secondary IPv6 on Mas-2 text field
			
		ScrolltoElement(application, "MAS_PE_InterfaceTextfield", xml);	
		Thread.sleep(1000);
			
		edittextFields_commonMethod(application, "Group Number", "MAS_PE_GroupNumberTextfield", MAS_GroupNumber, xml);   //Group Number text field
		edittextFields_commonMethod(application, "Link", "MAS_PE_LinkTextfield", MAS_Link, xml);   //Link text field
		edittextFields_commonMethod(application, "VLAN Id", "MAS_PE_VLANIDTextfield", MAS_VLANID, xml);   //VLAN Id text field
		editcheckbox_commonMethod(application, MAS_IVManagement, "MAS_PE_IVManagementCheckbox" , "IV Management" , xml);   //IV Management checkbox	
		
		}else if(accessMediaActualValue.equalsIgnoreCase("EPN")) {
			
			edittextFields_commonMethod(application, "Interface", "MAS_PE_InterfaceTextfield", MAS_Interface, xml);   //interface Text Field
			addDropdownValues(application, "HSRP/BGP", "MAS_PE_HSRDorBPdropdown", MAS_HSRPBGP);   //HSRP/BGP dropdown
			addDropdownValues_commonMethod(application, "Network", "MAS_PE_networkDropdown", MAS_Network, xml);   //network Dropdown
			edittextFields_commonMethod(application, "Interface Address Range", "MAS_PE_InterfaceAddressRangeTextfield", MAS_InterfaceAddressRange, xml);   //interface address range text field
			edittextFields_commonMethod(application, "Interface Address/Mask" , "MAS_PE_InterfaceAddressMaskTextfield", MAS_InterfaceAddressMask, xml);   //Interface Address/Mask textfield
			edittextFields_commonMethod(application, "HSRP IP", "MAS_PE_HSRPIPTextfield", MAS_HSRPIP, xml);   //HSRP IP text field
			edittextFields_commonMethod(application, "Interface Address Range IPV6", "MAS_PE_InterfaceAddressRangeIPV6Textfield", MAS_InterfaceAddressRangeIPV6, xml);   //Interface Address Range IPV6 text field
			edittextFields_commonMethod(application, "HSRP IPv6 Address", "MAS_PE_HSRPIPv6AddressTextfield", MAS_HSRPIPv6Address, xml);   //HSRP IPv6 Address text field
			edittextFields_commonMethod(application, "Primary IPv6 on Mas-1", "MAS_PE_PrimaryIPv6onMas1Textfield", MAS_PrimaryIPv6onMas1, xml);   //Primary IPv6 on Mas-1 textfield
			edittextFields_commonMethod(application, "Secondary IPv6 on Mas-2", "MAS_PE_SecondaryIPv6onMas2Textfield", MAS_SecondaryIPv6onMas2, xml);   //Secondary IPv6 on Mas-2 text field
			
			ScrolltoElement(application, "MAS_PE_InterfaceTextfield", xml);	
			Thread.sleep(1000);
			
			edittextFields_commonMethod(application, "Group Number", "MAS_PE_GroupNumberTextfield", MAS_GroupNumber, xml);   //Group Number text field
			edittextFields_commonMethod(application, "Link", "MAS_PE_LinkTextfield", MAS_Link, xml);   //Link text field
			edittextFields_commonMethod(application, "VLAN Id", "MAS_PE_VLANIDTextfield", MAS_VLANID, xml);   //VLAN Id text field
			editcheckbox_commonMethod(application, MAS_IVManagement, "MAS_PE_IVManagementCheckbox" , "IV Management", xml);    //IV Management checkbox
			edittextFields_commonMethod(application, "HSRP Track Interface", "MAS_PE_HSRPtrackInterfacetextField", MAS_HSRPTrackInterface, xml);   //HSRP Track Interface text Field
			edittextFields_commonMethod(application, "HSRP Authentication", "MAS_PE_HSRPauthenticationtextField", MAS_HSRPAuthentication, xml);   //HSRP Authentication text Field
			
		}
			
		scrolltoend();
		Thread.sleep(1000);
		
	//perform Generate configuration
		boolean configurationpanel=false;
	try {	
		configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/MAS_PE_IVManagementCheckbox")).isDisplayed();
		if(configurationpanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
			Log.info("'Configuration' panel is displaying");
			
			addDropdownValues_commonMethod(application, "Generate Configuration ", "MAS_PE_generateConfigurationDropdown", MAS_generateConfiguration, xml);
			Thread.sleep(2000);
			
			click_commonMethod(application, "Generate Configuration", "MAS_PE_generateConfigurationLink", xml);
			Thread.sleep(2000);
			
			scrolltoend();
			Thread.sleep(1000);
			
			String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/configuration_textArea")));
			if(configurationvalues.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate Configuration' link, no values displaying under 'Configuration' text box");
				Log.info("After clicking on 'Generate Configuration' link, no values displaying under 'Configuration' text box");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate Configuration' link, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				Log.info("After clicking on 'Generate Configuration' link, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
			}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			Log.info("'Configuration' panel is not displaying");
		}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				Log.info("'Configuration' panel is not displaying");
			}
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "trunk_okButton", xml);
	}
	
	
	public void viewCPEdevice(String application, String RouterId, String VendorModel, String ManagamentAddress, String Snmpro,
			String Snmprw, String SNMPv3ContextName, String SNMPv3ContextEngineId, String SNMPv3securityUsername, String SNMPv3AutoProto, String SNMPv3AuthPasswrd,
			String Country, String existingcity, 
    		String existingcityvalue, String existingsite, 
    		String existingsitevalue, String existingpremise, 
    		String existingpremisevalue, String newcity,String cityname,String Citycode,
    		String sitename, String sitecode,  String premisename,  String premisecode, 
    		String newsite, String NewPremise) throws InterruptedException, DocumentException {
		
		waitForpageload();
		
		waitforPagetobeenable();
		
		Thread.sleep(4000);
		
		compareText_InViewPage(application, "Router Id" , RouterId, xml);   //Router Id
		
		compareText_InViewPage(application, "Vendor/Model" , VendorModel , xml);   //vendor/model
		
		compareText_InViewPage(application, "Management Address", ManagamentAddress, xml);   //Management Address
		
		compareText_InViewPage(application, "Snmpro", Snmpro, xml);   //Snmpro
		
		compareText_InViewPage(application, "Snmprw" , Snmprw, xml);   //Snmprw
		
		compareText_InViewPage(application, "Snmp V3 Context Name" , SNMPv3ContextName, xml);   //SNMP V3 Context Name
		
		compareText_InViewPage(application, "Snmp V3 Context Engine ID" , SNMPv3ContextEngineId, xml);   //Snmp V3 Context Engine ID
		
		compareText_InViewPage(application, "Snmp V3 Security Username" , SNMPv3securityUsername, xml);   //Snmp V3 Security Username
		
		compareText_InViewPage(application, "Snmp V3 Auth Proto" , SNMPv3AutoProto, xml);   //SNMP v3 Auto Proto
		
		compareText_InViewPage(application, "Snmp V3 Auth Password", SNMPv3AuthPasswrd, xml);
		
	//Country
    	compareText_InViewPage(application, "Country", Country, xml);
    	
    	
    //City
    	if((existingcity.equalsIgnoreCase("yes")) && (newcity.equalsIgnoreCase("NO"))) {
    		
    		//Existing City
    		  	compareText_InViewPage(application, "City", existingcityvalue, xml);
    		
    	}
    	else if((existingcity.equalsIgnoreCase("NO")) && (newcity.equalsIgnoreCase("Yes"))) {
    		
    		//new City
    			compareText_InViewPage(application, "City", cityname, xml);
    		
    	}
    	
    //Site
    	if((existingsite.equalsIgnoreCase("yes")) && (newsite.equalsIgnoreCase("NO"))) {
    		
    		//Existing Site
    			compareText_InViewPage(application, "Site" , existingsitevalue, xml);
    	}
    	else if((existingsite.equalsIgnoreCase("No")) && (newsite.equalsIgnoreCase("Yes"))) {
    		
    		//New Site
    			compareText_InViewPage(application, "Site" , sitename, xml);
    		
    	}
    	
    //Premise
    	if((existingpremise.equalsIgnoreCase("yes")) && (NewPremise.equalsIgnoreCase("NO"))) {
    		
    		//Existing premise
    			 compareText_InViewPage(application, "Premise", existingpremisevalue, xml);
    		
    	}
    	else if((existingpremise.equalsIgnoreCase("No")) && (NewPremise.equalsIgnoreCase("Yes"))) {
    		
    		//new premise
    			compareText_InViewPage(application, "Premise", premisename, xml);
    	}
    
	}
	
	
	public void editCPEdeviceLink(String application) throws InterruptedException, DocumentException {
		
		scrollToTop();
		Thread.sleep(2000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit CPE Device' Functionality");
		
		click_commonMethod(application, "Action" , "viewdevicePage_actionDropdown" , xml);
		Thread.sleep(1000);
		
		click_commonMethod(application, "Edit_device Link", "editLink_InViewPage" , xml);
		
	}
	
	
	public void editCPEdevice(String application, String editRouterId, String editVendorModel, String editManagamentAddress, String editSnmpro,
			String editSnmprw, String editSNMPv3ContaxtName, String editSNMPv3ContextEngineId, String editSNMPv3securityUsername, 
			String editSNMPv3AutoProto, String editSNMPv3AuthPasswrd,
			String editCountry, String editExistingCity, String editExistingCityValue, String editExistingSite, String editExistingSiteValue,
	    	String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, String editNewPremise, String editNewCityName,
	    	String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, DocumentException, IOException {
		
		edittextFields_commonMethod(application, "Router ID" , "routerId"  , editRouterId, xml);   //Router ID
		
		addDropdownValues_commonMethod_ForSpantag(application, "Vendor/Model" , "CPE_vendorModel" , editVendorModel, xml);   //Vendor Model
		
		edittextFields_commonMethod(application, "Management Address", "CPE_manageAddress", editManagamentAddress, xml);   //Management Address
		
		edittextFields_commonMethod(application, "Snmpro", "CPE_snmpro" , editSnmpro , xml);   //Snmpro
		
		edittextFields_commonMethod(application, "Snmprw" , "CPE_snmprw" , editSnmprw, xml);   //Snmprw
		
		edittextFields_commonMethod(application, "Snmp V3 Context Name", "CPE_snmpV3ContextName" , editSNMPv3ContaxtName, xml);   //Snmp V3 Context Name
		
		edittextFields_commonMethod(application, "Snmp V3 Context Engine ID" , "CPE_snmpV3ContextEngineID", editSNMPv3ContextEngineId, xml);   //Snmp V3 Context Engine ID
		
		edittextFields_commonMethod(application, "Snmp V3 Security Username" , "CPE_snmpv3SecurityUserName", editSNMPv3securityUsername, xml);   //Snmp V3 Security Username
		
		addDropdownValues_commonMethod(application, "Snmp V3 Auth Proto" , "CPE_snmpv3AuthProto" , editSNMPv3AutoProto, xml);   ////Snmp V3 Auth Proto
		
		edittextFields_commonMethod(application, "Snmp V3 Auth Password", "CPE_snmpv3AuthPasswrd" , editSNMPv3AuthPasswrd, xml);   //Snmp V3 Auth Password
		
		scrolltoend();
		Thread.sleep(1000);
		
		//Country
		if(!editCountry.equalsIgnoreCase("Null")) {
			
//			addDropdownValues_commonMethod(application, "Country", "countryinput", editCountry, xml);
			selectValueInsideDropdown(application, "countryinput", "Country", editCountry, xml);
			
			//New City		
			if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("yes")) {
				Clickon_addToggleButton(application, "addcityswitch");
			   //City name
				addtextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
			   //City Code	
				addtextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
			   //Site name
				addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
			   //Site Code
				addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", editNewSiteCode, xml);
			   //Premise name	
				addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
			   //Premise Code	
				addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);
					
			}	
		
		//Existing City	
			else if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("no")) {
				
//			   addDropdownValues_commonMethod(application, "City", "citydropdowninput", editExistingCityValue, xml);
			   selectValueInsideDropdown(application, "citydropdowninput", "City", editExistingCityValue, xml);
				
				
			 //Existing Site
				  if(editExistingSite.equalsIgnoreCase("yes") & editNewSite.equalsIgnoreCase("no")) {
//					  addDropdownValues_commonMethod(application, "Site", "sitedropdowninput", editExistingSiteValue, xml);
					  selectValueInsideDropdown(application, "sitedropdowninput", "Site", editExistingSiteValue, xml);
					  
				 //Existing Premise
					  if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("no")) {
//						  addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", editExistingPremiseValue, xml);
						  selectValueInsideDropdown(application, "premisedropdowninput", "Premise", editExistingPremiseValue, xml);
			          	 }
					  
					//New Premise  
					  else if(editExistingPremise.equalsIgnoreCase("no") & editNewPremise.equalsIgnoreCase("yes")) {
						  
						  Clickon_addToggleButton(application, "addpremiseswitch");
						  //Premise name	
							addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", editNewPremiseName, xml);
						   //Premise Code	
							addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", editNewPremiseCode, xml);
					  } 
		         	}
	  		
			  else if(editExistingSite.equalsIgnoreCase("no") & editNewSite.equalsIgnoreCase("yes")) {
				  	
				  Clickon_addToggleButton(application, "addsiteswitch");
				  //Site name
					addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", editNewSiteName, xml);
				   //Site Code
					addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", editNewSiteCode, xml);
				   //Premise name	
					addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", editNewPremiseName, xml);
				   //Premise Code	
					addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", editNewPremiseCode, xml);
				  }
			}
			
		}
		else if(editCountry.equalsIgnoreCase("Null")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " No changes made for 'Country' dropdown");
		
		//City	
			editCity(application, editExistingCity, editNewCity, "citydropdowninput", "selectcityswitch", "addcityswitch",
					editExistingCityValue, editNewCityName, editNewCityCode, "City");
			
			
		//Site	
			editSite(application, editExistingSite, editNewSite, "sitedropdowninput", "selectsiteswitch",
					"addsiteswitch", editExistingSiteValue , editNewSiteName, editNewSiteCode, "Site");
			
		//Premise
			editPremise(application, editExistingPremise, editNewPremise, "premisedropdowninput", "selectpremiseswitch",
					"addpremiseswitch", editExistingPremiseValue, editNewPremiseName, editNewPremiseCode, "Premise");
			
		}
		
		
		scrolltoend();
		Thread.sleep(1000);
		
		clickOnBankPage();
		
		scrolltoend();
		Thread.sleep(1000);
		
		click_commonMethod(application, "OK", "trunk_okButton" , xml);
		Thread.sleep(2000);
		
	}
	
	
	
	
	/**
	 *  Method is for clicking on "view" link under CPE device	
	 * @param application
	 * @param existingdevicename
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void CPEdevice_clickOnViewLink(String application, String existingdevicename) throws InterruptedException, DocumentException {
		
		scrolltoend();
		Thread.sleep(2000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'View CPE Device' Functionality");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid_CPEdevice")).isDisplayed())
        {
              List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/CPEdevice_fetchAlldevice_inViewPage"));
//              Log.info(addeddevicesList);
              int AddedDevicesCount= addeddevicesList.size();
              for(int i=0;i<AddedDevicesCount;i++) {
                    String AddedDeviceNameText= addeddevicesList.get(i).getText();
                    String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                    if(AddedDeviceNameText.contains(existingdevicename))
                    {
                          WebElement AddedDevice_selectInterfaceLink=getwebelement(xml.getlocator("//locators/" + application + "/CPEdveice_viewLink").replace("value", AddedDevice_SNo)); 
                          Clickon(AddedDevice_selectInterfaceLink);
                          Thread.sleep(2000);
                          break;
                    }
                    else
                    {
                          ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                    }
                    
              }
        }
        else
        {
              ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
        }		
	}

	
	/**
	 *  Method is for clicking on "view" link under CPE device	
	 * @param application
	 * @param existingdevicename
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void CPEdevice_clickOnDeleteLink(String application, String existingdevicename) throws InterruptedException, DocumentException {
		
		scrolltoend();
		Thread.sleep(2000);
		
	waitForpageload();
	waitforPagetobeenable();
	
	scrolltoend();
	Thread.sleep(1000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete CPE Device' Functionality");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid_CPEdevice")).isDisplayed())
        {
              List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/CPEdevice_fetchAlldevice_inViewPage"));
//              Log.info(addeddevicesList);
              int AddedDevicesCount= addeddevicesList.size();
              for(int i=0;i<AddedDevicesCount;i++) {
                    String AddedDeviceNameText= addeddevicesList.get(i).getText();
                    String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                    if(AddedDeviceNameText.contains(existingdevicename))
                    {
                          WebElement AddedDevice_deleteLink=getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_deleteLink").replace("value", AddedDevice_SNo)); 
                          Clickon(AddedDevice_deleteLink);
                          Thread.sleep(2000);
                          
                          WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
                          if(DeleteAlertPopup.isDisplayed())
                          {
                                click_commonMethod(application, "Delete", "deletebutton", xml);
                                break;
                          }
                          else
                          {
                                Log.info("Delete alert popup is not displayed");
                                ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
                          }
                    }
                    else
                    {
                          ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                    }
                   
              }
        }
        else
        {
              ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
        }		
	}
	
	
	public void clickOnViewLink(String application, String trunkGroupName, String siteOrderName) throws Exception {
		
		scrolltoend();
		
		WebElement getTrunkRow=getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedTrunk_InViewServicePage").replace("value", trunkGroupName));
		safeJavaScriptClick(getTrunkRow);
		
		
		//click on Action dropdown under Trunk Panel	
			WebElement trukPanel_actionDropdown=getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel_ActionDropdown").replace("value", siteOrderName));
			safeJavaScriptClick(trukPanel_actionDropdown);
			
		
		//Add CPE link
			Clickon(getwebelement("//a[contains(text(),'View')]"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on view link");
		
	}
	
	
	public void searchorder(String application, String sid) throws InterruptedException, DocumentException, IOException {

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
		Thread.sleep(1000);

		WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
		ScrolltoElement(application, "searchbutton", xml);
		Clickon(searchbutton);
		Thread.sleep(2000);
		scrolltoend();
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
		Thread.sleep(3000);
	}
	
	
	public void deleteService(String application) throws InterruptedException, DocumentException, IOException {
		
		Thread.sleep(2000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete Serice' Functionality");
		
		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/OrderPanel"));
		scrolltoview(orderPanel);
		Thread.sleep(2000);
		
	//click on Action dropdown	
		click_commonMethod(application, "Action", "ActionDropdown_InViewPage", xml);
		Thread.sleep(1000);
		
	//click on delete link
		click_commonMethod(application, "Delete", "deleteServiceLink", xml);
		
		
		 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
         if(DeleteAlertPopup.isDisplayed())
         {
       	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
       	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
       	 
            click_commonMethod(application, "Delete", "deletebutton", xml);
            
            scrollToTop();
            Thread.sleep(1000);
            
            verifysuccessmessage(application, "Service successfully deleted..");
         }
         else
         {
               Log.info("Delete alert popup is not displayed");
               ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
         }

	}
	
	
	public void deleteSiteOrder(String application, String siteOrder) throws InterruptedException, DocumentException, IOException {
		
		Thread.sleep(3000);
		scrolltoend();
		Thread.sleep(1000);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete Site Order' Functionality");
		
		WebElement siteOrderLink = getwebelement(xml.getlocator("//locators/" + application + "/deleteSiteOrderlink").replace("value", siteOrder));
		Clickon(siteOrderLink);
		
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
        if(DeleteAlertPopup.isDisplayed())
        {
      	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
      	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
      	 
           click_commonMethod(application, "Delete", "deletebutton", xml);
           
           scrollToTop();
           Thread.sleep(1000);
           
//           verifysuccessmessage(application, "Deleted SBC manual config successfully.");
        }
        else
        {
              Log.info("Delete alert popup is not displayed");
              ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
        }
		
        Thread.sleep(2000);
	}
	
	
	public void VerifyUsersPanel(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email, String Phone, String EditUsername, 
			String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone, String IPGuardianAccountGroup,String ColtOnlineUser, 
			String GeneratePassword, String RolesToBeSelected,String HideRouterToolsIPv6CommandsCisco_ToBeSelected, String HideRouterToolsIPv4CommandsHuiwai_ToBeSelected, 
			String HideRouterToolsIPv4CommandsCisco_ToBeSelected,String HideServicesToBeSelected,String HideSiteOrderToBeSelected, String editRolesToBeSelected, 
			String edit_RoleToBeHidden, String RouterToolsIPv6CommandsCisco_ToBeAvailable, String RouterToolsIPv6CommandsCisco_ToBeHidden, String RouterToolsIPv4CommandsHuiwai_ToBeAvailable, 
			String HideRouterToolsIPv4CommandsHuiwai_ToBeHidden, String HideRouterToolsIPv4CommandsCisco_ToBeAvailable, String HideRouterToolsIPv4CommandsCisco_ToBeHidden,
			String Services_ToBeAvailable, String Services_ToBeHidden, String SiteOrders_ToBeAvailable, String SiteOrders_ToBeHidden, String editIPGuardianAccountGroup, String editColtOnlineUser) 
					throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying User Panel in 'View Service' page");
		
		String[] rolestobeSelectedList=RolesToBeSelected.split(",");
		String[] routerToolIPv4CiscoTobeSelectedList = HideRouterToolsIPv4CommandsCisco_ToBeSelected.split(",");
		String[] routerToolIPv4HuaweiTobeSelectedList =  HideRouterToolsIPv4CommandsHuiwai_ToBeSelected.split(",");
		String[] ServicesTobeSelectedlist= HideServicesToBeSelected.split(",");
		String[] siteOrdersToBeselectedList = HideSiteOrderToBeSelected.split(",");

		String[] rolestobeAvailableList=editRolesToBeSelected.split(",");
		String[] rolestobeHiddenList=edit_RoleToBeHidden.split(",");

		String[] routerToolIPv4CiscoTobeAvailableList = HideRouterToolsIPv4CommandsCisco_ToBeAvailable.split(",");
		String[] routerToolIPv4CiscoTobeHiddenList = HideRouterToolsIPv4CommandsCisco_ToBeHidden.split(",");

		String[] routerToolIPv4HuaweiTobeAvailableList =  RouterToolsIPv4CommandsHuiwai_ToBeAvailable.split(",");
		String[] routerToolIPv4HuaweiTobeHiddenList =  HideRouterToolsIPv4CommandsHuiwai_ToBeHidden.split(",");


		String[] ServicesTobeAvailablelist= Services_ToBeAvailable.split(",");
		String[] ServicesTobeHiddenlist= Services_ToBeHidden.split(",");

		String[] siteOrdersToBeAvailableList = SiteOrders_ToBeAvailable.split(",");
		String[] siteOrdersToBeHiddenList = SiteOrders_ToBeHidden.split(",");

		ScrolltoElement(application, "customerdetailsheader", xml);
		WebElement UserGridCheck= getwebelement("(//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String UserGrid= UserGridCheck.getAttribute("style");

			//Cancel User
			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Add", "AddLink", xml);
			compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);
			scrolltoend();
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			compareText(application, "User panel Header", "userspanel_header", "Users", xml);

			//Create User
			ScrolltoElement(application, "customerdetailsheader", xml);
			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Add", "AddLink", xml);
			compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);

			//Warning messages verify
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "OK_button", xml);
			scrollToTop();
			warningMessage_commonMethod(application, "warningmsg_username", "User Name", xml);
			warningMessage_commonMethod(application, "warningmsg_firstname", "First Name", xml);
			warningMessage_commonMethod(application, "warningmsg_surname", "Surname", xml);
			warningMessage_commonMethod(application, "warningmsg_postaladdress", "Postal Address", xml);
			warningMessage_commonMethod(application, "warningmsg_useremail", "Email", xml);
			warningMessage_commonMethod(application, "warningmsg_userphone", "Phone", xml);
			warningMessage_commonMethod(application, "warningmsg_userpassword", "Password", xml);

			addtextFields_commonMethod(application, "User Name", "UserName", Username, xml);
			addtextFields_commonMethod(application, "First Name", "FirstName", Firstname, xml);
			addtextFields_commonMethod(application, "SurName", "SurName", Surname, xml);
			addtextFields_commonMethod(application, "Postal Address", "PostalAddress", Postaladdress, xml);
			addtextFields_commonMethod(application, "Email", "Email", Email, xml);
			addtextFields_commonMethod(application, "Phone", "Phone", Phone, xml);
			addtextFields_commonMethod(application, "IPGuardian Account Group" , "IPGuardianAccountGroup" , IPGuardianAccountGroup, xml);
			addtextFields_commonMethod(application, "Colt Online User", "ColtOnlineUser", ColtOnlineUser, xml);
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			String  password=getwebelement(xml.getlocator("//locators/"+application+"/Password_Textfield")).getAttribute("value");
			Log.info("Generated Password is : "+password);

			if(password.isEmpty()) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Password Field is empty. No values displaying after clicked on 'Generate password link");

				SendKeys(getwebelement(xml.getlocator("//locators/"+application+"/Password_Textfield")), GeneratePassword);	
				Thread.sleep(1000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Password entered manually not automatically generated :  "+GeneratePassword);
				Log.info("===Password entered manually not automatically generated ===");

			}else {
				Log.info("Automatically generated Password value is : "+ password);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password generated and the value is displaying as :  "+password);
			}

			ScrolltoElement(application, "Email", xml);
			Thread.sleep(2000);

			//Role	
			selectAndAddValueFromLeftDropdown(application, "Role", "roleDropdown_available" , rolestobeSelectedList, "roleDropdown_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Role", "roleDropdown_selectedValues");


			//Hide Service
			selectAndAddValueFromLeftDropdown(application, "Hide Service", "HideService_Available", ServicesTobeSelectedlist, "HideService_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Services", "HideServicesDropdown_selectedValues");


			//Hide Site Order
			selectAndAddValueFromLeftDropdown(application, "Hide Site Order", "HideSiteOrder_Available" , siteOrdersToBeselectedList , "hideSiteOrder_addbutton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Site Order" , "HideSiteOrderDropdown_selectedValues");

			scrolltoend();
			Thread.sleep(1000);

			//Hide Router Tool IPv4 Commands(Cisco)
			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv4 Commands(Cisco)", "hideRouterToolIPv4_Cisco_Available", routerToolIPv4CiscoTobeSelectedList, "hideRouterToolIPv4_Cisco_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv4 Commands(Cisco)", "hideRouterToolIpv4_Cisco_selectedvalues");


			//Hide Router Tool IPv4 Commands(Huawei)
			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIPv4_Huawei_available" , routerToolIPv4HuaweiTobeSelectedList, "hideRouterToolIPv4__Huawei_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIpv4_Huawei_selectedvalues");


			//		//Hide Router Tool IPv6 Commands(Cisco)	
			//			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , "HideRouterToolIPv6_Cisco_Available" , selectValue, xpathForAddButton);
			//			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , xpath);

			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "OK_button", xml);
			Thread.sleep(2000);
			compareText(application, "Create User success message", "successmsg", "User successfully created", xml);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User added successfully");
			Log.info("User added successfully");

			//Edit User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers= getwebelements("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']");
			int NoOfUsers = ExistingUsers.size();
			Log.info("Total users:"+ NoOfUsers);

			if(NoOfUsers==1 || NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");

				click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
				click_commonMethod(application, "Edit", "edit", xml);
				Thread.sleep(2000);
				compareText(application, "Edit User Header", "edituser_header", "Edit User", xml);
				scrollToTop();
				edittextFields_commonMethod(application, "User Name", "UserName" , EditUsername, xml);
				edittextFields_commonMethod(application, "First Name", "FirstName" , EditFirstname, xml);
				edittextFields_commonMethod(application, "Sur Name", "SurName" , EditSurname, xml);
				edittextFields_commonMethod(application, "Postal Address", "PostalAddress" , EditPostaladdress, xml);
				edittextFields_commonMethod(application, "Email", "Email" , EditEmail, xml);
				edittextFields_commonMethod(application, "Phone", "Phone" , EditPhone, xml);
				edittextFields_commonMethod(application, "IPGuardian Account Group" , "IPGuardianAccountGroup" , editIPGuardianAccountGroup, xml);
				edittextFields_commonMethod(application, "Colt Online User", "ColtOnlineUser", editColtOnlineUser, xml);

				String editpassword=getwebelement(xml.getlocator("//locators/"+application+"/Password")).getAttribute("value");
				Log.info("Generated Password is : "+editpassword);

				if(editpassword.isEmpty()) {

					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Password Field is empty. No values displaying under'Generate password link");

					click_commonMethod(application, "Generate Password", "GeneratePassword", xml);

				}else {
					Log.info("Automatically generated Password value is : "+ editpassword);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Password value is displaying as :  "+editpassword);
				}

				ScrolltoElement(application, "Email", xml);

				//Role	
				selectAndRemoveValueFromRightDropdown(application, "Roles_Hidden", "roleDropdown_selectedValues", rolestobeAvailableList, "roleDropdown_removeButton");
				selectAndAddValueFromLeftDropdown(application, "Role_Available", "roleDropdown_available" , rolestobeHiddenList, "roleDropdown_addButton");
				verifySelectedValuesInsideRightDropdown(application, "Role_Hidden", "roleDropdown_selectedValues");

				//Hide Service
				selectAndRemoveValueFromRightDropdown(application, "Service_Hidden" , "HideServicesDropdown_selectedValues" , ServicesTobeAvailablelist, "HideService_removeButton");
				selectAndAddValueFromLeftDropdown(application, "Service_Available", "HideService_Available", ServicesTobeHiddenlist, "HideService_addButton");
				verifySelectedValuesInsideRightDropdown(application, "Hidden Services", "HideServicesDropdown_selectedValues");

				//Hide Site Order
				selectAndRemoveValueFromRightDropdown(application, "SiteOrder_Hidden" , "HideSiteOrderDropdown_selectedValues", siteOrdersToBeAvailableList, "hideSiteOrder_removeButton");
				selectAndAddValueFromLeftDropdown(application, "SiteOrder_Available", "HideSiteOrder_Available" , siteOrdersToBeHiddenList , "hideSiteOrder_addbutton");
				verifySelectedValuesInsideRightDropdown(application, "Hiden Site Orders" , "HideSiteOrderDropdown_selectedValues");

				scrolltoend();
				Thread.sleep(1000);

				//Hide Router Tool IPv4 Commands(Cisco)
				selectAndRemoveValueFromRightDropdown(application, "Router Tool IPv4 Commands(Cisco)_Available", "hideRouterToolIpv4_Cisco_selectedvalues", routerToolIPv4CiscoTobeAvailableList, "hideRouterToolIPv4_Cisco_removeButton");
				selectAndAddValueFromLeftDropdown(application, "Router Tool IPv4 Commands(Cisco)_Hidden", "hideRouterToolIPv4_Cisco_Available", routerToolIPv4CiscoTobeHiddenList, "hideRouterToolIPv4_Cisco_addButton");
				verifySelectedValuesInsideRightDropdown(application, "Hiden Router Tool IPv4 Commands(Cisco)", "hideRouterToolIpv4_Cisco_selectedvalues");

				//Hide Router Tool IPv4 Commands(Huawei)
				selectAndRemoveValueFromRightDropdown(application, "Router Tool IPv4 Commands(Huawei)_Hidden", "hideRouterToolIpv4_Huawei_selectedvalues", routerToolIPv4HuaweiTobeAvailableList, "hideRouterToolIPv4_Huawei_removeButton");
				selectAndAddValueFromLeftDropdown(application, "Router Tool IPv4 Commands(Huawei)_Available" , "hideRouterToolIPv4_Huawei_available" , routerToolIPv4HuaweiTobeHiddenList, "hideRouterToolIPv4__Huawei_addButton");
				verifySelectedValuesInsideRightDropdown(application, "Hideen Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIpv4_Huawei_selectedvalues");

				scrolltoend();
				Thread.sleep(1000);
				click_commonMethod(application, "OK", "OK_button", xml);
				Thread.sleep(2000);
				compareText(application, "User update success message", "successmsg", "User successfully updated", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No users displayed");
				Log.info("No users displayed");
			}


			//View User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers1= getwebelements("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']");
			int NoOfUsers1 = ExistingUsers1.size();
			Log.info("Total users:"+ NoOfUsers1);
			if(NoOfUsers1==1 || NoOfUsers1>1)
			{
				if(!EditUsername.equalsIgnoreCase("null"))
				{
					WebElement EditedUserName = getwebelement("//div[contains(text(),'" + EditUsername + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
					EditedUserName.click();
				}
				else
				{
					WebElement AddedUser = getwebelement("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
					AddedUser.click();
				}
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");

				click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
				click_commonMethod(application, "view", "view", xml);
				scrollToTop();
				
			//Username	
				if(EditUsername.equalsIgnoreCase("null")) {
					compareText(application, "User Name", "usernamevalue", Username, xml);
				}else {
					compareText(application, "User Name", "usernamevalue", EditUsername, xml);
				}
				
			//Firstname	
				if(EditFirstname.equalsIgnoreCase("null")) {
					compareText(application, "First Name", "firstnamevalue", Firstname, xml);
				}else {
					compareText(application, "First Name", "firstnamevalue", EditFirstname, xml);
				}
				
			//Surname	
				if(EditSurname.equalsIgnoreCase("null")) {
					compareText(application, "SurName", "surnamevalue", Surname, xml);
				}else {
					compareText(application, "SurName", "surnamevalue", EditSurname, xml);
				}
				
			//postal Address
				if(EditPostaladdress.equalsIgnoreCase("null")) {
					compareText(application, "Postal Address", "postaladdressvalue", Postaladdress, xml);
				}else {
					compareText(application, "Postal Address", "postaladdressvalue", EditPostaladdress, xml);
				}
				
			//Email
				if(EditPostaladdress.equalsIgnoreCase("null")) {
					compareText(application, "Email", "emailvalue", Email, xml);
				}else {
					compareText(application, "Email", "emailvalue", EditEmail, xml);
				}
				
			//Phone
				if(EditPostaladdress.equalsIgnoreCase("null")) {
					compareText(application, "Phone", "phonevalue", Phone, xml);
				}else {
					compareText(application, "Phone", "phonevalue", EditPhone, xml);
				}	
				

				//IP Guardian Accouunt Group
				GetText(application, "IPGuardian Account Group", "IPGuardianAccountGroup_viewpage");

				//Colt Online User
				GetText(application, "Colt Online User", "coltonlineuser_viewpage");

				ScrolltoElement(application, "usernamevalue", xml);
				Thread.sleep(1000);

				//Roles
				//compareTextForViewUserPage(application, labelname, ExpectedText, xml);


				//Hidden Router Tools IPv4 (Cisco)
				List<WebElement> HRcisco = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolIPv4Cisco"));	

				for(WebElement listofHiddenCiscoValues : HRcisco) {
					Log.info("list of values in Hide router Tool Command IPv4(Cisco) are: "+listofHiddenCiscoValues.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Cisco) are: " + listofHiddenCiscoValues.getText());
				}

				scrolltoend();
				Thread.sleep(2000);

				//Hidden Router Tool IPv4 (Huawei)
				List<WebElement> Ipv4CommandHuawei = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv4Huawei"));	

				for(WebElement listofHuaweiValues : Ipv4CommandHuawei) {
					Log.info("list of values in Hide router Tool Command (Cisco) are: "+listofHuaweiValues.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Huawei) are: "+ listofHuaweiValues.getText());
				}	


				//Hidden Router Tools IPv6 (Cisco)
				List<WebElement> HiddenIPv6cisco = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv6Cisco"));	

				for(WebElement listofHiddenIPv6CiscoValues : HiddenIPv6cisco) {
					Log.info("list of values in Hide router Tool Command IPv6 (Cisco) are: "+listofHiddenIPv6CiscoValues.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv6 Commands(Cisco) are: " + listofHiddenIPv6CiscoValues.getText());
				}			

				scrolltoend();
				Thread.sleep(2000);
				click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				Log.info("------ View User successful ------");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No users displayed");
				Log.info("No users displayed");
			}


			//Delete User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers2= getwebelements("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']");
			int NoOfUsers2 = ExistingUsers2.size();
			Log.info("Total users:"+ NoOfUsers2);
			if(NoOfUsers2==1 || NoOfUsers2>1)
			{
				if(!EditUsername.equalsIgnoreCase("null"))
				{
					WebElement EditedUserName = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderUserPanel_ViewSericePage").replace("value", EditUsername));
							//getwebelement("//div[contains(text(),'" +  + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
					EditedUserName.click();
				}
				else
				{
					WebElement AddedUser =  getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderUserPanel_ViewSericePage").replace("value", Username));
							//getwebelement("//div[contains(text(),'" +  + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
					AddedUser.click();
				}
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");

				click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
				click_commonMethod(application, "Delete", "delete", xml);
				Thread.sleep(2000);
				
				Alert alert= driver.switchTo().alert ();
				alert.accept();
				
//				WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
//				if(DeleteAlertPopup.isDisplayed())
//				{
//					
//					click_commonMethod(application, "Delete", "userdelete", xml);
//					compareText(application, "User delete success msg", "deletesuccessmsg", "User successfully deleted", xml);
//				}
//				else
//				{
//					Log.info("Delete alert popup is not displayed");
//					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
//				}
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");
				Log.info("No users displayed");
			}
		}

	public void selectAndAddValueFromLeftDropdown(String application, String labelname, String xpath, String[] selectValue, String xpathForAddButton) {

		WebElement availability=null;
		List<String> ls = new ArrayList<String>();
		try{

			List<WebElement> elements= getwebelements(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			int element_count= elements.size();

			if(element_count>=1) {

				//Print list of values inside Dropdown 
				for(WebElement a : elements) {
					ls.add(a.getText());
				}

				ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
				Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				for(int i=0; i<selectValue.length; i++)
				{
					Thread.sleep(5000);
					for(int j=0; j<ls.size() ; j++) {
						Log.info("ls value "+ ls.get(j));
						if(selectValue[i].equals(ls.get(j)))
						{
							elements.get(j).click();
							ExtentTestManager.getTest().log(LogStatus.PASS, elements.get(j) + " got selected" );
							Thread.sleep(1000);
							click_commonMethod(application, "Add", xpathForAddButton , xml);
							Thread.sleep(5000);
						}
					}
				}

			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");

				Log.info("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			Log.info( "No values displaying under "+labelname + " available dropdown");
		}
	}


	public void verifySelectedValuesInsideRightDropdown(String application, String labelname, String xpath) {

		//getAllValuesInsideDropDown
		boolean availability=false;
		List<String> ls = new ArrayList<String>();

		try{

			List<WebElement> elements= getwebelements(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			int element_count= elements.size();

			if(element_count>=1) {

				//Print list of values inside Dropdown 
				for(WebElement a : elements) {
					ls.add(a.getText());
				}

				ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
				Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");

				Log.info("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			Log.info( "No values displaying under "+labelname + " available dropdown");
		}
	}

	public void selectAndRemoveValueFromRightDropdown(String application, String labelname, String xpath, String[] selectValue, String xpathForRemoveButton) {

		WebElement availability=null;
		List<String> ls = new ArrayList<String>();

		try{
			List<WebElement> elements= getwebelements(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			int element_count= elements.size();

			if(element_count>=1) {

				//Print list of values inside Dropdown 
				for(WebElement a : elements) {
					ls.add(a.getText());
				}

				ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
				Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				for(int i=0; i<selectValue.length; i++)
				{
					Thread.sleep(2000);
					for(int j=0; j<ls.size() ; j++) {
						Log.info("ls value "+ ls.get(j));
						if(selectValue[i].equals(ls.get(j)))
						{
							elements.get(j).click();
							ExtentTestManager.getTest().log(LogStatus.PASS, elements.get(j) + " got selected" );
							Thread.sleep(1000);
							WebElement removeButton=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathForRemoveButton +"").replace("value", "<<"));
							Clickon(removeButton);
							ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on remove '<<' button");
							Thread.sleep(3000);
						}
					}
				}

			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");

				Log.info("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			Log.info( "No values displaying under "+labelname + " available dropdown");
		}
	}
	
	public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno, String editOrderSelection) 
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit Order' Functionality");
		
		ScrolltoElement(application, "userspanel_header", xml);

		if(editOrderSelection.equalsIgnoreCase("no")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edit Order is not performed");
			Log.info("Edit Order is not performed");
		}
		else if(editOrderSelection.equalsIgnoreCase("Yes")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Performing Edit Order Functionality");
		
		//Cancel Edit order in Order panel
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		compareText(application, "Edit Order", "editorderheader", "Edit Order", xml);
		Thread.sleep(1000);

		WebElement EditOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/editorderno"));
		click_commonMethod(application, "Order Number", "editorderno", xml);
		Thread.sleep(2000);
		Clear(EditOrderNo);
		Thread.sleep(2000);
		addtextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);

		WebElement EditVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/editvoicelineno"));
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		Thread.sleep(2000);
		Clear(EditVoiceLineNo);
		Thread.sleep(2000);
		addtextFields_commonMethod(application, "RFI Voiceline Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
//		compareText(application, "Order Header", "orderpanelheader", "Order", xml);
//		Log.info("Navigated to order panel in view service page");
//		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		//Edit Order
		Thread.sleep(1000);
		ScrolltoElement(application, "userspanel_header", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		compareText(application, "Edit Order Header", "editorderheader", "Edit Order", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Order Number", "editorderno", xml);
		Thread.sleep(2000);
		cleartext(application, "Order Number", "editorderno");
		Thread.sleep(2000);
		addtextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		Thread.sleep(2000);
		cleartext(application, "RFI Voice Line Number", "editvoicelineno");
		Thread.sleep(2000);
		addtextFields_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "OK", "editorder_okbutton", xml);
		Thread.sleep(1000);
		ScrolltoElement(application, "userspanel_header", xml);
		Thread.sleep(1000);
//		compareText(application, "Order Header", "orderpanelheader", "Order", xml);
//		Log.info("Navigated to order panel in view service page");
//		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		compareText(application, "Order Number", "ordernumbervalue", editorderno, xml);
		compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno, xml);
		Log.info("------ Edit Order is successful ------");
		}

	}

	public void verifyorderpanel_changeorder(String application, String ChangeOrder_newOrderNumber, String changevoicelineno, String changeOrderSelection_newOrder,
			String changeOrderSelection_existingOrder, String ChangeOrder_existingOrderNumber) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "userspanel_header", xml);
				
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Change Order' Functionality");
		
		if((changeOrderSelection_newOrder.equalsIgnoreCase("No")) && (changeOrderSelection_existingOrder.equalsIgnoreCase("No"))) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Change Order is not performed");
			Log.info("Change Order is not performed");
		}
		else if(changeOrderSelection_newOrder.equalsIgnoreCase("Yes")) {
			
			//Change Order
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Select order switch", "changeorder_selectorderswitch", xml);
			click_commonMethod(application, "Order Number", "changeordernumber", xml);
			Thread.sleep(2000);
			addtextFields_commonMethod(application, "Order Number", "changeordernumber", ChangeOrder_newOrderNumber, xml);
			click_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", xml);
			Thread.sleep(3000);
			addtextFields_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", changevoicelineno, xml);
			click_commonMethod(application, "Create Order", "createorder_button", xml);
			Thread.sleep(1000);
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_newOrderNumber, xml);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
			Log.info("------ Change Order is successful ------");
		}
		else if(changeOrderSelection_existingOrder.equalsIgnoreCase("yes")) 
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Performing Change Order functionality");
			
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			
				addDropdownValues_commonMethod(application, "Order/Contract Number (Parent SID)", "changeOrder_orderDropdown", ChangeOrder_existingOrderNumber, xml);
				
				click_commonMethod(application, "OK", "changeorder_okbutton", xml);
				Thread.sleep(1000);
				ScrolltoElement(application, "userspanel_header", xml);
				Thread.sleep(1000);
				compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_existingOrderNumber, xml);
				compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
				Log.info("------ Change Order is successful ------");
	
		}
		
	}

	
	/**
	 *   For Dropdown common method _ For Span tag  Add
	 * @param application
	 * @param labelname
	 * @param xpath
	 * @param expectedValueToAdd
	 * @param xml
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void addDropdownValues_commonMethod_ForSpantag(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml) throws InterruptedException, DocumentException {
		  boolean availability=false;
		  List<String> ls = new ArrayList<String>();
		  
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
			  Log.info(labelname + " dropdown is displaying");
			  
			  if(expectedValueToAdd.equalsIgnoreCase("null")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
				  Log.info(" No values selected under "+ labelname + " dropdown");
			  }else {
				  
				  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
				  Thread.sleep(3000);
				  
				  //verify list of values inside dropdown
				  List<WebElement> listofvalues = driver
							.findElements(By.xpath("//span[@role='option']"));
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
				  Log.info( " List of values inside "+ labelname + "dropdown is:  ");
				  
					for (WebElement valuetypes : listofvalues) {
								Log.info("service sub types : " + valuetypes.getText());
								 ls.add(valuetypes.getText());
					}
					
					    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
			            Log.info("list of values inside "+labelname+" dropdown is: "+ls);
					
					Thread.sleep(2000);
				SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
				Thread.sleep(2000);
					
				  Clickon(getwebelement("(//span[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
				  Thread.sleep(3000);
				  
				  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
				  Log.info( labelname + " dropdown value selected as: "+ actualValue);
				  
			  }
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
			  Log.info(labelname + " is not displaying");
		  }
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
			  Log.info(labelname + " is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
			Log.info(" NO value selected under "+ labelname + " dropdown");
		}
		
	}
	
	
	/**
	 * Method is to get the device name serial number under the MAS panel
	 * @param application
	 * @param existingdevicename
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
		public String MASswitch_getDeviceSerialNumber(String application, String existingdevicename) throws InterruptedException, DocumentException {
			
			String expectedDeviceSerialNumber=null;
			
			WebElement managementOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/managementOptionsPanelheader"));
			scrolltoview(managementOptions_header);
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid_MAS")).isDisplayed())
            {
                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/MAS_fetchAlldevice_InviewPage"));
//                  Log.info(addeddevicesList);
                  int AddedDevicesCount= addeddevicesList.size();
                  for(int i=0;i<AddedDevicesCount;i++) {
                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                        Log.info("number "+ AddedDevice_SNo);
                        if(AddedDeviceNameText.contains(existingdevicename))
                        {
                        	expectedDeviceSerialNumber=AddedDevice_SNo;
                        	 break;
                        	
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                        }
                       
                  }
            }
            else
            {
                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
            }	
			
			return expectedDeviceSerialNumber; 
			
		}

	
		/**
		 * Method is to fetch serial number for PE device under Provider Equipment panel
		 * @param application
		 * @param existingdevicename
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
			public String PEdevice_getDeviceSerialNumber(String application, String existingdevicename) throws InterruptedException, DocumentException {
				
				String expectedDeviceSerialNumber=null;
				
				WebElement MASswitchPanel_header= getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_panelHeader"));
				scrolltoview(MASswitchPanel_header);
				Thread.sleep(2000);
				
				if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	            {
	                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
//	                  Log.info(addeddevicesList);
	                  int AddedDevicesCount= addeddevicesList.size();
	                  for(int i=0;i<AddedDevicesCount;i++) {
	                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
	                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
//	                        Log.info("number "+ AddedDevice_SNo);
	                        if(AddedDeviceNameText.contains(existingdevicename))
	                        {
	                        	expectedDeviceSerialNumber=AddedDevice_SNo;
	                        	 break;
	                        }
	                        else
	                        {
	                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
	                        }
	                       
	                  }
	            }
	            else
	            {
	                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	            }
				
				return expectedDeviceSerialNumber;
			}
			
			
			
			
		public String fetchOrderNumber(String application) throws InterruptedException, DocumentException, IOException {
				
				ScrolltoElement(application, "userspanel_header", xml);
				
				String orderNumber=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/fetchorderNumber")));
				
				return orderNumber;
				
		}
		
		
		public void shownewInfovista(String application) throws Exception {
			
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(2000);
			
			WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/OrderPanel"));
			scrolltoview(orderPanel);
			Thread.sleep(2000);
	   		
		   click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
		   Thread.sleep(2000);
		   click_commonMethod(application, "Show infovista link", "viewservice_infovistareport", xml);
		   Thread.sleep(6000);
		   
		   String expectedPageName= "SSO login Page";
		   
//		   Switchtotab();		//navigate to new tab
		   List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		   driver.switchTo().window(browserTabs .get(1));
			
			
			String actualPageTitle=driver.getTitle();
			if(expectedPageName.equals(actualPageTitle)) {
				
				successScreenshot(application);
				ExtentTestManager.getTest().log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ actualPageTitle + " as expected");
				Log.info("on clicking 'Show Infovista link', it got naviagted to "+ actualPageTitle + " as expected");
				
			}else {
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Page Title displays as: "+actualPageTitle + " . The Exected Page title is: "+ expectedPageName);
				Log.info("Page Title displays as: "+actualPageTitle + " . The Exected Page title is: "+ expectedPageName);
			}	
			
		   CloseProposalwindow();
	   		
	   }
		
		
		public void verifyManageService(String application, String sid, String servicetype, String servicestatus, 
				String syncstatus, String servicestatuschangerequired, String orderNumber , String changeStatus) throws InterruptedException, DocumentException, IOException {
			
			ScrolltoElement(application, "orderpanelheader", xml);
			click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
			click_commonMethod(application, "Manage", "manageLink", xml);
			compareText(application, "Manage service header", "manageservice_header", "Manage Service", xml);
			compareText(application, "Order", "order_syncPanel", orderNumber, xml);
			compareText(application, "Service Identification", "status_servicename", sid, xml);
			compareText(application, "Service type", "status_servicetype", servicetype, xml);
			String ServiceDetails_value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/status_servicedetails")));
			if(ServiceDetails_value.isEmpty())
			{
				Log.info("Service Details column value is empty as expected");
				System.out.println("Service Details column value is empty as expected");
			}
			else
			{
				Log.info("Service Details column value should be empty");
				System.out.println("Service Details column value should be empty");
			}
			compareText(application, "Service Status", "status_currentstatus", servicestatus, xml);

			String LastModificationTime_value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/status_modificationtime")));
			if(LastModificationTime_value.contains("BST"))
			{
				Log.info("Service status is displayed as : " + LastModificationTime_value);
				System.out.println("Service status is :"+ LastModificationTime_value);
			}
			else
			{
				Log.info("Incorrect modification time format");
				System.out.println("Incorrect modification time format");
			}
			click_commonMethod(application, "Status", "statuslink", xml);

			if(servicestatuschangerequired.equalsIgnoreCase("Yes"))
			{
				WebElement ServiceStatusPage= getwebelement(xml.getlocator("//locators/" + application + "/Servicestatus_popup"));
				if(ServiceStatusPage.isDisplayed())
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Service Status' popup displays");
					Log.info("'Service Status' popup displays");
					
					compareText(application, "Service Identification", "serviceStatusPopup_sericeIdentificationValue", sid, xml);
					compareText(application, "Service type", "serviceStatusPopup_serviceTypeValue", servicetype, xml);
					
					addDropdownValues_commonMethod(application, "Change Status", "serviceStatusPopup_changeStatusDropdown", changeStatus, xml);
					click_commonMethod(application, "OK", "okbutton", xml);
					
					verifysuccessmessage(application, "Service Status Changed successfully..");
					
					waitForpageload();
					waitforPagetobeenable();
					Thread.sleep(2000);
					
					WebElement ServiceStatusHistory= getwebelement(xml.getlocator("//locators/" + application + "/servicestatushistory"));
					try
					{
						if(ServiceStatusHistory.isDisplayed())
						{
							ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request logged");
							Log.info("Service status change request logged");
							
							compareText(application, "status column value", "serviceStatusPopup_statusTable_statusColumn", changeStatus, xml);
							
							click_commonMethod(application, "Close", "servicestatus_popupclose", xml);
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Service status change request is not logged");
							Log.info("Service status change request is not logged");
							
							click_commonMethod(application, "Close", "servicestatus_popupclose", xml);
						}
					}
					catch(StaleElementReferenceException e)
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No service history displays");
						Log.info("No service history displays");
						
						click_commonMethod(application, "Close", "servicestatus_popupclose", xml);
					}
					
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Status link is not working");
					Log.info("No service history to display");
				}
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change not reqired");
				Log.info("Service status change not reqired");
				click_commonMethod(application, "Close", "servicestatus_popupclose", xml);
			}

		//synchronize panel in manage service page
			compareText(application, "Order", "order_syncPanel", orderNumber, xml);
			compareText(application, "Service Identification", "sync_servicename", sid, xml);
			compareText(application, "Service type", "sync_servicetype", servicetype, xml);

			String ServiceDetails_value1 = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/sync_servicedetails")));
			if(ServiceDetails_value1.isEmpty())
			{
				Log.info("Service Details column value is empty as expected");
				System.out.println("Service Details column value is empty as expected");
			}
			else
			{
				Log.info("Service Details column value should be empty");
				System.out.println("Service Details column value should be empty");
			}

			scrolltoend();
			compareText(application, "Sync Status", "sync_status", syncstatus, xml);
			click_commonMethod(application, "Synchronize", "synchronizelink", xml);
			verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
			
		}
		
		
		public void history(String application, String revisionType, String trunkGroupName, String gateway, 
				String voipProtocol, String signallingTransportProtocol) throws InterruptedException, DocumentException {
			
			String voipValue = null;
			
			//Action button	
					click_commonMethod(application, "Action", "viewPage_ActionButton", xml);
					
			//click on History link
					click_commonMethod(application, "History", "viewTrunkPage_historyLink", xml);
					
					waitForpageload();
					waitforPagetobeenable();
					Thread.sleep(2000);
			
			//Revision Type
				compareText(application, "Revision Type" , "historyPanel_revisionTypeColumn" , revisionType , xml);
				
			//Last Modified By
				compareText(application, "Last Modified By", "historyPanel_lastModifiedColumn", "colttest@colt.net", xml);
				
			//Trunk Group Name
				compareText(application, "Trunk Grup Name", "historyPanel_trunkGroupNameColumn", trunkGroupName, xml);
				
			//Gateway
				compareText(application, "Gateway", "historyPanel_gatewayColumn", gateway, xml);
				
			//VOIP Protocol
				if(voipProtocol.equals("SIP-I")) {
					voipValue = "SI";
				}
				else if(voipProtocol.equals("SIP")) {
					voipValue = "S";
				}
				compareText(application, "VOIP Protocol", "historyPanel_voipProtocolColumn", voipValue, xml);
				
			//Signalling Transport Protocol
				compareText(application, "Signalling Transport Protocol", "historyPanel_signalingTransportprotocol", signallingTransportProtocol, xml);
				
			//Action Column
				click_commonMethod(application, "View", "historyPanel_viewLink", xml);
				waitForpageload();
				waitforPagetobeenable();
				Thread.sleep(1000);
				
			//click on back button
				scrolltoend();
				Thread.sleep(1000);
				scrolltoend();
				
				click_commonMethod(application, "Back", "trunk_backbutton", xml);
				waitForpageload();
				waitforPagetobeenable();
				Thread.sleep(2000);
				
				click_commonMethod(application, "Back", "trunk_backbutton", xml);
		}
		
		
		public String verifyDevicesUnderPEpanel(String application) throws InterruptedException, DocumentException {
			
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(1000);
			
			String deviceAvailability = null;
			
			WebElement MASswitchPanel_header= getwebelement(xml.getlocator("//locators/" + application + "/MASswitch_panelHeader"));
			scrolltoview(MASswitchPanel_header);
			Thread.sleep(2000);
		
		try {	
            List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
            int AddedDevicesCount= addeddevicesList.size();
            
            if(AddedDevicesCount>=1) {
            	deviceAvailability = "Yes";
            	
            	ExtentTestManager.getTest().log(LogStatus.INFO, "List of device names displaying under 'Provider Equipment' panel are: ");
            	Log.info("List of device names displaying under 'Provider Equipment' panel are: ");
            	
            	for(int i=0;i<AddedDevicesCount;i++) {
                    String AddedDeviceNameText= addeddevicesList.get(i).getText();
                    
                    ExtentTestManager.getTest().log(LogStatus.PASS, AddedDeviceNameText);
                    Log.info(AddedDeviceNameText);
            	} 
            }
            else
            {
            	deviceAvailability = "No";
            }
            
		}catch(Exception e) {
			e.printStackTrace();
			deviceAvailability = "No";
		}
            
			return deviceAvailability;
			
		}
		
		
		public void listOfDeviceNAmesUnderPEpanel(String application, String existingdevicename) throws InterruptedException, DocumentException {
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
            {
                  List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/PE_fetchAlldevice_InviewPage"));
                  int AddedDevicesCount= addeddevicesList.size();
                  for(int i=0;i<AddedDevicesCount;i++) {
                        String AddedDeviceNameText= addeddevicesList.get(i).getText();
                        String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                        Log.info("number "+ AddedDevice_SNo);
                        if(AddedDeviceNameText.contains(existingdevicename))
                        {
                              WebElement viewLink=getwebelement(xml.getlocator("//locators/" + application + "/PE_viewLink_InViewPage").replace("value", AddedDevice_SNo)); 
                              Clickon(viewLink);
                              Thread.sleep(2000);
                              break;
                        }
                        else
                        {
                              ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
                        }
                  }
            }
            else
            {
                  ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
            }		
		}
		

	
}	
		
