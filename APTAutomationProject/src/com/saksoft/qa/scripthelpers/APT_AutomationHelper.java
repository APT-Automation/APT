package com.saksoft.qa.scripthelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.driverlibrary.Log;
import com.saksoft.qa.driverlibrary.XMLReader;


public class APT_AutomationHelper extends DriverHelper {

	public APT_AutomationHelper(WebDriver dr) 
	{
		super(dr);
		// TODO Auto-generated constructor stub
	}

	
	
	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/APT_Repository.xml");
	
	
//	@Override
//	public void xmlRr(XMLReader xml) {
//		// TODO Auto-generated method stub
//		System.out.println("xml value displaying as: "+xml);
//		super.xmlRr(xml);
//	}

	public void webelementpresencelogger(WebElement ele, String msg) {

		boolean flag = ele.isDisplayed();
		System.out.println("element presence state : " + flag);
		if (flag) {

			System.out.println("webElement is present " + ele.getText());
			DriverTestcase.logger.log(LogStatus.PASS, msg);
		} else {

			System.out.println("webElement is not  present" + ele.getText());
			DriverTestcase.logger.log(LogStatus.FAIL, msg);
		}

	}

	public void CreateCustomer(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws Exception {

		// webelementpresencelogger(getwebelement(xml.getlocator("//locators/" +
		// application + "/createcustomerlink")), "Create Customer link");

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);
		System.out.println("Mouser hovered on Manage Customer's Service");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "create customer link", "createcustomerlink", xml);
		Thread.sleep(2000);
		compareText(application, "create customer page header", "createcustomer_header", "Customer", xml);
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
		click_commonMethod(application, "Clear button", "clearbutton", xml);
		DriverTestcase.logger.log(LogStatus.PASS, "All text field values are cleared");

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


	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected, String customerName1, String customerName2)
			throws InterruptedException, DocumentException, IOException {


		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		System.out.println("Mouser hovered on Manage Customer's Service");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "Create Order/Service Link", "CreateOrderServiceLink", xml);	
		Log.info("=== Create Order/Service navigated ===");

		//click_commonMethod on Next button to check mandatory messages	
		click_commonMethod(application, "Next", "nextbutton", xml);

		//Customer Error message	
		WarningMessage(application, "customer_createorderpage_warngmsg", "Customer");

		//Entering Customer name
		addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", customerName1, xml);
//		EnterTextValue(application, customerName1, "Customer Name", "entercustomernamefield");
		
		EnterTextValue(application, customerName2, "Customer Name", "entercustomernamefield");	

		//Select Customer from dropdown
		addDropdownValues(application, "Customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected);
		click_commonMethod(application, "Next", "Next_Button", xml);

	}

public static String newordernumber, newVoiceLineNumber, SelectOrderNumber;
	
	public void createorderservice(String application, String neworder, String neworderno, String newrfireqno, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		CustomJavaScriptExecute("window.scrollTo(0, document.body.scrollHeight)");
		if (neworder.equalsIgnoreCase("YES")) {

			WebElement CreateOrder_Header= getwebelement("//div[text()='Create Order / Service']");
			scrolltoview(CreateOrder_Header);
			Thread.sleep(2000);
			
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);	
			EnterTextValue(application, neworderno, "Order/Contract Number", "newordertextfield");
			EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");
			click_commonMethod(application, "create order", "createorderbutton", xml);	
			compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully", xml);			
			scrolltoview(CreateOrder_Header);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;
		} 
		
		else if (existingorderservice.equalsIgnoreCase("YES")) {
			addDropdownValues(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

			SelectOrderNumber = existingordernumber;
		} else {

			System.out.println("Order not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step :Order not selected");
		}
	}

	

	//======================================  Common Methods  ===========================================

	/*public void scrolltoview(WebElement element) throws InterruptedException 
	{
		javascriptexecutor(element);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}*/
	
	public void addDropdownValues(String application, String fieldname, String xpath, String expectedValueToAdd) throws InterruptedException, DocumentException {
		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			if(availability) {
				DriverTestcase.logger.log(LogStatus.PASS, fieldname + " dropdown is displaying as expected");
				System.out.println(fieldname + " dropdown is displaying as expected");

				if(expectedValueToAdd.equalsIgnoreCase("null")) {

					DriverTestcase.logger.log(LogStatus.PASS, " No values selected under "+ fieldname + " dropdown");
					System.out.println(" No values selected under "+ fieldname + " dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='"+ fieldname +"']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = getwebelements("//div[@role='list']//span[@role='option']");

					DriverTestcase.logger.log(LogStatus.PASS, " List of values inside "+ fieldname + " dropdown is:  ");
					System.out.println( " List of values inside "+ fieldname + "dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {

						Log.info("service sub types : " + valuetypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());

					}

					Thread.sleep(3000);
					Clickon(getwebelement("//span[text()='"+ expectedValueToAdd +"']"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//div[label[text()='"+ fieldname +"']]//span").getText();
					DriverTestcase.logger.log(LogStatus.PASS, fieldname + " dropdown value selected as: "+ actualValue );
					System.out.println( fieldname + " dropdown value selected as: "+ actualValue);

				}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, fieldname + " is not displaying");
				System.out.println(fieldname + " is not displaying");
			}
		}catch(NoSuchElementException e) {
			DriverTestcase.logger.log(LogStatus.FAIL, fieldname + " is not displaying");
			System.out.println(fieldname + " is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " NO value selected under "+ fieldname + " dropdown");
			System.out.println(" NO value selected under "+ fieldname + " dropdown");
		}

	}

	public void EnterTextValue(String application, String value, String labelname, String xpath) {
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			if(element==null)
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: '"+labelname+"' text field not found");
				System.out.println(element);
			}
			else 
			{
				element.sendKeys(value);
				DriverTestcase.logger.log(LogStatus.PASS, "Step: Entered '"+value+"' into '"+labelname+"' text field");
			}

		} catch (Exception e) {
			DriverTestcase.logger.log(LogStatus.FAIL,"Not able to enter '"+value+"' into '"+labelname+"' text field");
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
				DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			}
			else
			{
				String WarningMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getText();

				System.out.println("'"+labelname+"' field warning  message displayed as : " + WarningMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : validation message for '"+labelname+"' text field displayed as : " + WarningMsg);
				Log.info("'"+labelname+"' field warning message displayed as : " + WarningMsg);
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("'"+labelname+"' field warning message is not dipslaying");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step: '"+labelname+"' field warning message is not displaying");
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
				DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
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
			System.out.println(availability);
			if (availability) {
				Thread.sleep(2000);
				DriverTestcase.logger.log(LogStatus.PASS, "Step: '"+labelname+"' is displayed as expected");
			}
			else {
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: '"+labelname+"' is not displaying as expected");
			}

		} catch (Exception e) {
			DriverTestcase.logger.log(LogStatus.FAIL,"Step: '"+labelname+"' is not available to display");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+ labelname +"' is not found");
			}
			else if (ele!=null && ele.isEmpty()) {
				DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
			}
			else {   

				text = element.getText();
				DriverTestcase.logger.log(LogStatus.PASS,"Step: '"+ labelname +"' value is displayed as : '"+text+"'");

			}
		}catch (Exception e) {
			DriverTestcase.logger.log(LogStatus.FAIL,"Step: '"+ labelname +"' value is not displaying");
			e.printStackTrace();
		}
		return text;

	}


	
}
