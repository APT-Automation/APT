package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.reporter.ExtentManager;
import com.colt.qa.reporter.ExtentTestManager;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;


public class APT_IPAccessNoCPEHelper extends DriverHelper {

	public APT_IPAccessNoCPEHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	
	
	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_IPAccessNoCPE.xml");

	public void webelementpresencelogger(WebElement ele, String msg) {

		boolean flag = ele.isDisplayed();
		System.out.println("element presence state : " + flag);
		if (flag) {

			System.out.println("webElement is present " + ele.getText());
			ExtentTestManager.getTest().log(LogStatus.PASS, msg);
		} else {

			System.out.println("webElement is not  present" + ele.getText());
			ExtentTestManager.getTest().log(LogStatus.FAIL, msg);
		}

	}
	
	



	
	public void createcustomer(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Customer Creation Functionality");
		try {
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		
		System.out.println("Mouse hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
		Log.info("Mouse hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "create customer link", "createcustomerlink", xml);
		
		compareText(application, "create customer page header", "createcustomer_header", "Create Customer", xml);
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);

		//Warning msg check
		warningMessage_commonMethod(application, "customernamewarngmsg", "Legal Customer Name", xml);
		warningMessage_commonMethod(application, "countrywarngmsg", "Country", xml);
		warningMessage_commonMethod(application, "ocnwarngmsg", "OCN", xml);
		warningMessage_commonMethod(application, "typewarngmsg", "Type", xml);
		warningMessage_commonMethod(application, "emailwarngmsg", "Email", xml);

		//Create customer by providing all info
		addtextFields_commonMethod(application, "Customer Name", "nametextfield", name, xml);
		addtextFields_commonMethod(application, "Main Domain", "maindomaintextfield", maindomain, xml);
		addDropdownValues_commonMethod(application, "Country", "country", country, xml);
		addtextFields_commonMethod(application, "OCN", "ocntextfield", ocn, xml);
		addtextFields_commonMethod(application, "Reference", "referencetextfield", reference, xml);
		addtextFields_commonMethod(application, "Technical Contact Name", "technicalcontactnametextfield", tcn, xml);
		addDropdownValues_commonMethod(application, "Type", "typedropdown", type, xml);
		addtextFields_commonMethod(application, "Email", "emailtextfield", email, xml);
		addtextFields_commonMethod(application, "Phone", "phonetextfield", phone, xml);
		addtextFields_commonMethod(application, "Fax", "faxtextfield", fax, xml);
		scrolltoend();
		
		click_commonMethod(application, "Ok", "okbutton", xml);
//		compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
		verifysuccessmessage(application, "Customer successfully created.");
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
			System.out.println(  e+ " : Field is not displayed in  Service Creation page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
			System.out.println(  e+" : Field is not displayed in Service Creation page");
		}
		sa.assertAll();
	}


	
	
	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected)
			throws InterruptedException, DocumentException, IOException {

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		
		System.out.println("Mouse hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
		Log.info("Mouse hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "Create Order/Service", "CreateOrderServiceLink", xml);
		Log.info("=== Create Order/Service navigated ===");

		//click on Next button to check mandatory messages
		click_commonMethod(application, "Next", "nextbutton", xml);

		//Customer Error message	
		warningMessage_commonMethod(application, "customer_createorderpage_warngmsg", "Choose a customer", xml);

		//Entering Customer name
        addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected, xml);
        
        Thread.sleep(7000);
        addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", "*", xml);
        
        //Select Customer from dropdown
        addDropdownValues_commonMethod(application, "Choose a customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);

		click_commonMethod(application, "Next", "nextbutton", xml);

	}
	

	
	public void selectCustomertocreateOrderOld(String application, String ChooseCustomerToBeSelected)
			throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Customer Selection Functionality");
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		
		System.out.println("Mouse hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
		Log.info("Mouse hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "Create Order/Service", "CreateOrderServiceLink", xml);
		Log.info("=== Create Order/Service navigated ===");

		//click on Next button to check mandatory messages
		click_commonMethod(application, "Next", "nextbutton", xml);

		//Customer Error message	
		warningMessage_commonMethod(application, "customer_createorderpage_warngmsg", "Choose a customer", xml);

		//Entering Customer name
	    addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected, xml);
	    
	    Thread.sleep(7000);
	    addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", "*", xml);
	    
	    //Select Customer from dropdown
	    addDropdownValues_commonMethod(application, "Choose a customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);
//	    addtextFields_commonMethod(application, "Customer Name", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);
//	    WebElement ChooseCustomerToBeSelectedWb=getwebelement("//div[text()='"+ChooseCustomerToBeSelected+"']");
//		Clickon(ChooseCustomerToBeSelectedWb);
	    click_commonMethod(application, "Next", "nextbutton", xml);

	}

	
	
	
	
	
	
	public void click(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
		WebElement element= null;

		try {
			
			element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			}
			else {
				element.click();	
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
			}

		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
			e.printStackTrace();
		}
	}
	
	
	public void compareText(String application, String labelname, String xpath, String ExpectedText) throws InterruptedException, DocumentException {

		String text = null;
		WebElement element = null;

		try {
			
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
				text = element.getText();
				if(text.equals(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step: The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
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
	
	
	public void ClearAndEnterTextValue(String application, String labelname,  String xpath, String newValue) {
		WebElement element = null;
		try {
			
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			String value= element.getAttribute("value");
			
			if(value.isEmpty())
			{
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step: '"+labelname+"' text field is empty");
				System.out.println(value);
			
			}else {
				element.clear();
				
				element.sendKeys(newValue);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Entered '"+newValue+"' into '"+labelname+"' text field");
			}

		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+newValue+"' into '"+labelname+"' text field");
			e.printStackTrace();
		}

	}
	
	
	
	public void EnterTextValue(String application, String value, String labelname, String xpath) {
		WebElement element = null;

		try {
			
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' text field not found");
				System.out.println(element);
			}
			else 
			{
				element.sendKeys(value);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Entered '"+value+"' into '"+labelname+"' text field");
			}

		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+value+"' into '"+labelname+"' text field");
			e.printStackTrace();
		}

	}

	
	
	public void SelectDropdownValueUnderDivTag(String application ,String lebelname, String dropdownToBeSelectedInTheEnd, String dropdownXpath, String commonDropdownValueTag) throws InterruptedException, DocumentException, IOException {
		
		try {
			// Select  Country dropdown
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+dropdownXpath+"")));
				List<WebElement> dropdownValueList = driver.findElements(By.xpath(commonDropdownValueTag));
				
				for (WebElement dropdownvaluelist : dropdownValueList) {
					System.out.println("Available " +lebelname+ " are : " + dropdownvaluelist.getText().toString()+ "  ,  ");
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step : Available  '" +lebelname+ "'  is : " + dropdownvaluelist.getText().toString());
					Log.info("Available " +lebelname+ " is : " + dropdownvaluelist.getText().toString());
				}
				
				
				
				// click on Country dropdown
				WebElement selectDropdownValue = driver.findElement(By.xpath("//div[contains(text(),'" + dropdownToBeSelectedInTheEnd + "')]"));
				System.out.println("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected  '"+lebelname+ "'  is : " + selectDropdownValue.getText().toString());
				Log.info("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
				selectDropdownValue.click();
				
				
			
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
		System.out.println( lebelname + " dropdown is not displaying");
		
	}catch(Exception ee) {
		ee.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
		System.out.println( lebelname + " dropdown is not displaying");
	}

}
		
	
	
	
	public static String newordernumber, newVoiceLineNumber;
	public void createneworderservice(String application, String neworder, String neworderno, String newrfireqno)
			throws InterruptedException, IOException, DocumentException {

		if (neworder.equalsIgnoreCase("YES")) {

			WebElement CreateOrder_Header= driver.findElement(By.xpath("//div[text()='Create Order / Service']"));
			scrolltoview(CreateOrder_Header);
			

			click(application, "select order switch", "selectorderswitch");	
			EnterTextValue
			(application, neworderno, "Order/Contract Number", "newordertextfield");
			EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");
			click(application, "create order", "createorderbutton");	
//			compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully");			
			verifysuccessmessage(application, "Order created successfully");
			scrolltoview(CreateOrder_Header);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;

		} else {

			System.out.println("new order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : clicked on button to create new order");
		}

	}
	

	
	public static String SelectOrderNumber;

	public void createexistingorderservice(String application, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		if (existingorderservice.equalsIgnoreCase("YES")) {

			SelectDropdownValueUnderDivTag(application, "Existing order", existingordernumber, "existingorderdropdown", "commonDropdownValueTag");
			Log.info("=== Order Contract Number selected===");

			

			SelectOrderNumber = existingordernumber;


		} else {

			System.out.println("existing order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step :existing order not selected");
		}

	}
	


	public void createorderservice(String application, String neworder, String neworderno, String newrfireqno, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		scrolltoend();
		
		click_commonMethod(application, "Next", "nextbutton", xml);
		

		//Warning messages verify
		warningMessage_commonMethod(application, "order_contractnumber_warngmsg", "Order/Contract Number(Parent SID)", xml);
		warningMessage_commonMethod(application, "servicetype_warngmsg", "Service Type", xml);

		if (neworder.equalsIgnoreCase("YES")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying New Order Creation Functionality");
			addtextFields_commonMethod(application, "Order/Contract Number", "newordertextfield", neworderno, xml);
			addtextFields_commonMethod(application, "RFI Voice line Number", "newrfireqtextfield", newrfireqno, xml);
			click_commonMethod(application, "create order", "createorderbutton", xml);
//			compareText(application, "Order Creation Success message", "OrderCreatedSuccessMsg", "Order created successfully", xml);
			verifysuccessmessage(application, "Order created successfully");
			ScrolltoElement(application, "CreateOrderHeader", xml);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;
		} 

		else if (existingorderservice.equalsIgnoreCase("YES")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Existing Order Selection Functionality");
			
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber, xml);
			Log.info("=== Order Contract Number selected===");

			

			SelectOrderNumber = existingordernumber;
		} else {
			System.out.println("Order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step :Order not selected");
			Log.info("Order not selected");
		}
	}
	
	public void verifyServicetypeAndNetworkConfigurationSelection(String application, String servicetype, String NetworkConfiguration)
			throws InterruptedException, IOException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Service Type Network Selection Functionality");
		// select service type
		scrolltoend();
		click_commonMethod(application, "Next", "nextbutton", xml);
		warningMessage_commonMethod(application, "ServiceTypeWarningMessage", "Service Type", xml);
		addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		
		// select service type 
		click_commonMethod(application, "Next", "nextbutton", xml);
		
		warningMessage_commonMethod(application, "NetworkConfigurationWarningMessage", "Network Configuration", xml);
		
		click_commonMethod(application, "Network Configuration", "networkconfigurationinputfield", xml);
		WebElement servicesubtype = driver.findElement(By.xpath("//div[text()='"+NetworkConfiguration+"']"));
		
		servicesubtype.click();
		
		
		// click on next button
		
		click_commonMethod(application, "Next", "nextbutton", xml);
		
		scrollToTop();
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

	}
	

	public void verifyServiceCreation(String application, String ServiceType, String NetworkConfiguration, String ServiceIdentification, String BillingType, 
			String TerminationDate, String EmailService, String PhoneService, String Remarks, String ManageService, String RouterConfigurationViewIPv4,
			String RouterConfigurationViewIPv6, String PerformanceReporting, String IPGuardian, String SNMPNotification, String DeliveryChannel, 
			String TrapTargetAddress, String RouterBasedFirewall, String Qos , String ExtendPErangetocustomerLAN , String NewOrderNumber , String NewRFIREQNumber
			) throws InterruptedException, IOException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Service Creation Functionality");
		if(getwebelement(xml.getlocator("//locators/" + application + "/createorderservice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Servce Creation' page navigated as expected");
			System.out.println("'Servce Creation' page navigated as expected");
		// verify warning messages
		scrolltoend();
		click_commonMethod(application, "Next", "nextbutton", xml);
		
		try {
		scrollToTop();
		warningMessage_commonMethod(application, "sidwarngmsg", "Service Identification", xml);
		warningMessage_commonMethod(application, "billingtype_warngmsg", "Billing Type", xml);

		//Create service
		//*addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", ServiceIdentification, xml);
		EnterTextValue(application, ServiceIdentification, "Service Identification", "serviceidentificationtextfield");
		//**addDropdownValues_commonMethod(application, "Billing Type", "billingtype_dropdown", BillingType, xml);
		SelectDropdownValueUnderSelectTag(application, "Billing Type", BillingType, "billingtype_dropdown", xml);
		addtextFields_commonMethod(application, "Termination Date", "terminationdate_field", TerminationDate, xml);
		addtextFields_commonMethod(application, "Email", "emailtextfield_Service", EmailService, xml);
		addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfield", PhoneService, xml);
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", Remarks, xml);
		
		scrolltoend();

		// management options panel
		if(ManageService.equalsIgnoreCase("Yes")){
			addCheckbox_commonMethod(application, "ManageServicecheckbox", "Manage Service", ManageService, "no", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Manage Service checkbox is not checked");
		}
		

		if(RouterConfigurationViewIPv4.equalsIgnoreCase("Yes"))	{
			addCheckbox_commonMethod(application, "RouterConfigurationViewIPv4checkbox", "Router Configuration View IPv4", RouterConfigurationViewIPv4, "no", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Router Configuration View IPv4 checkbox is not checked");
		}
		
		if(RouterConfigurationViewIPv6.equalsIgnoreCase("Yes"))	{
			addCheckbox_commonMethod(application, "RouterConfigurationViewIPv6checkbox", "Router Configuration View IPv6", RouterConfigurationViewIPv6, "no", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Router Configuration View IPv6 checkbox is not checked");
		}
		
		if(PerformanceReporting.equalsIgnoreCase("Yes"))
		{
			addCheckbox_commonMethod(application, "performancereportingcheckbox", "Performance Reporting", PerformanceReporting, "yes", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Performance Reporting checkbox is not checked");
		}
		
		if(IPGuardian.equalsIgnoreCase("Yes")){
			addCheckbox_commonMethod(application, "IPGuardiancheckbox", "IP Guardian", IPGuardian, "no", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : IP Guardian checkbox is not checked");
		}

		if(SNMPNotification.equalsIgnoreCase("Yes")){
			addCheckbox_commonMethod(application, "SNMPNotificationcheckbox", "SNMP Notification", SNMPNotification, "no", xml);
			addtextFields_commonMethod(application, "Trap Target Address", "TrapTargetAddresstextfield", TrapTargetAddress, xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SNMP Notification checkbox is not checked");
		}
		
		
		//**addDropdownValues_commonMethod(application, "Delivery Channel", "DeliveryChannelDropdown", DeliveryChannel, xml);
		SelectDropdownValueUnderSelectTag(application, "Delivery Channel", DeliveryChannel, "DeliveryChannelDropdown", xml);
		
		//		<!-- Configuration Options -->
		if(RouterBasedFirewall.equalsIgnoreCase("Yes")){
			addCheckbox_commonMethod(application, "RouterBasedFirewallcheckbox", "Router Based Firewall", RouterBasedFirewall, "no", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Router Based Firewall checkbox is not checked");
		}
		
		if(Qos.equalsIgnoreCase("Yes")){
			addCheckbox_commonMethod(application, "Qoscheckbox", "Qos", Qos, "no", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Qos checkbox is not checked");
		}
		
		if(ExtendPErangetocustomerLAN.equalsIgnoreCase("Yes")){
			addCheckbox_commonMethod(application, "ExtendPErangetocustomerLANcheckbox", "Extend PE range to customer LAN", ExtendPErangetocustomerLAN, "no", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Extend PE range to customer LAN checkbox is not checked");
		}
		
		click_commonMethod(application, "Next", "Nextbutton_ServiceCreation", xml);
		compareText(application, "Service creation success msg", "servicecreationmessage", "Service successfully created", xml);
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
			System.out.println(  e+ " : Field is not displayed in Service Creation page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
			System.out.println(  e+" : Field is not displayed in Service Creation page");
		}
		
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Servce Creation' page not navigated");
		System.out.println("'Servce Creation' page navigated");
	}
		sa.assertAll();
	}
	
	
	
	

	
	public void verifyCustomerDetailsInformation(String application, String newCustomerCreation, String existingCustomerSelection,String newCustomer,
			String existingCustomer, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "'Verifying Customer informations");
		ScrolltoElement(application, "customerdetailsheader", xml);
		//Customer Name
			if(newCustomerCreation.equalsIgnoreCase("Yes") || existingCustomerSelection.equalsIgnoreCase("No")) {
				compareText(application, "Customer Name", "Name_Value", newCustomer, xml);
				compareText(application, "Country", "Country_Value", country, xml);
				compareText(application, "OCN", "OCN_Value", ocn, xml);
				compareText(application, "Reference", "Reference_Value", reference, xml);
				compareText(application, "Technical Contact Name", "TechnicalContactName_Value", tcn, xml);
				compareText(application, "Type", "Type_Value", type, xml);
				compareText(application, "Email", "Email_Value", email, xml);
				compareText(application, "Phone", "Phone_Value", phone, xml);
				compareText(application, "Fax", "Fax_Value", fax, xml);
			}
			else if(newCustomerCreation.equalsIgnoreCase("No") || existingCustomerSelection.equalsIgnoreCase("Yes")) {
				compareText(application, "Customer Name", "Name_Value", existingCustomer, xml);
			}
			
		//Main Domain
			if(maindomain.equalsIgnoreCase("Null")) {
				Log.info("A default displays for main domain field, if no provided while creating customer");
			}else {
				compareText(application, "Main Domain", "MainDomain_Value", maindomain, xml);
			}
		
		Log.info("=== Customer Details panel fields Verified ===");
	}
	
	
	
	public void verifyUserDetailsInformation(String application, String Login, String Name, String Email, String Roles, String Address, String Resource)
			throws InterruptedException, DocumentException, IOException {

		// verify table column names
		ScrolltoElement(application, "customerdetailsheader", xml);
		try {
		compareText(application, "Login column", "LoginColumn", Login, xml);
		compareText(application, "Name column", "NameColumn", Name, xml);
		compareText(application, "Email column", "EmailColumn", Email, xml);
		compareText(application, "Roles column", "RolesColumn", Roles, xml);
		compareText(application, "Address column", "AddressColumn", Address, xml);
		compareText(application, "Resource column", "ResourcesColumn", Resource, xml);
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
		}
		
		Log.info("=== Customer Details panel fields Verified ===");
		sa.assertAll();
		
		

	}


	public void createnewuser(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email,String Phone, String EditUsername, String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone, String ipguardianaccountgroup, String coltonlineuser, String selectrole)
			throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "customerdetailsheader", xml);
		WebElement UserGridCheck= driver.findElement(By.xpath("(//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String UserGrid= UserGridCheck.getAttribute("style");

		if(UserGrid.contains("height: 1px"))
		{
			//Cancel User
			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Add", "AddLink", xml);
			compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);
			ScrolltoElement(application, "cancelbutton", xml);
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			

			//Create User
			ScrolltoElement(application, "customerdetailsheader", xml);
			compareText(application, "User panel Header", "userspanel_header", "Users", xml);
			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Add", "AddLink", xml);
			compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);
			addtextFields_commonMethod(application, "User Name", "UserName", Username, xml);
			addtextFields_commonMethod(application, "First Name", "FirstName", Firstname, xml);
			addtextFields_commonMethod(application, "SurName", "SurName", Surname, xml);
			addtextFields_commonMethod(application, "Postal Address", "PostalAddress", Postaladdress, xml);
			addtextFields_commonMethod(application, "Email", "adduser_Email", Email, xml);
			addtextFields_commonMethod(application, "Phone", "adduser_Phone", Phone, xml);
			addtextFields_commonMethod(application, "IPGuardian Account Group", "ipguardianaccountgroup", ipguardianaccountgroup, xml);
			addtextFields_commonMethod(application, "Colt Online User", "coltonlineuser", coltonlineuser, xml);
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);

			//Verify Roles field
			try{

				List<WebElement> Rolesvalue= driver.findElements(By.xpath("//select[@id='availableRole']//option"));
				int Rolesvalue_count= Rolesvalue.size();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Available Roles are: ");
				for(int i=0; i<Rolesvalue_count; i++)
				{
					String Rolesvaluetext= Rolesvalue.get(i).getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : " +Rolesvaluetext);
					if(selectrole.contains(Rolesvaluetext))
					{
						Rolesvalue.get(i).click();
						click_commonMethod(application, "Roles field add arrow", "rolesaddarrow", xml);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No available roles to display");
			}
			try{
				List<WebElement> SelectedRoles= driver.findElements(By.xpath("//select[@id='selectedRole']//option"));
				int SelectedRoles_count= SelectedRoles.size();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected Roles are: ");
				for(int j=0;j<SelectedRoles_count;j++) 
				{
					if(SelectedRoles_count>=1)
					{
						String Selectedroletext= SelectedRoles.get(j).getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : " +Selectedroletext);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No selected to display");
			}

			scrolltoend();
			click_commonMethod(application, "OK", "user_okbutton", xml);
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User added successfully");

			//Edit User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Edit", "edit", xml);
			
			compareText(application, "Edit User Header", "edituser_header", "Edit User", xml);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
			cleartext(application, "User Name", "UserName");
			addtextFields_commonMethod(application, "User Name", "UserName", EditUsername, xml);
			cleartext(application, "First Name", "FirstName");
			addtextFields_commonMethod(application, "First Name", "FirstName", EditFirstname, xml);
			cleartext(application, "SurName", "SurName");
			addtextFields_commonMethod(application, "SurName", "SurName", EditSurname, xml);
			cleartext(application, "Postal Address", "PostalAddress");
			addtextFields_commonMethod(application, "Postal Address", "PostalAddress", EditPostaladdress, xml);
			cleartext(application, "Email", "adduser_Email");
			addtextFields_commonMethod(application, "Email", "adduser_Email", EditEmail, xml);
			cleartext(application, "Phone", "adduser_Phone");
			addtextFields_commonMethod(application, "Phone", "adduser_Phone", EditPhone, xml);
			cleartext(application, "Password", "Password");
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			scrolltoend();
			click_commonMethod(application, "OK", "user_okbutton", xml);

			//View User
			
			ScrolltoElement(application, "customerdetailsheader", xml);
			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "view", "view", xml);
			ScrolltoElement(application, "usernamevalue", xml);
			compareText(application, "User Name", "usernamevalue", EditUsername, xml);
			compareText(application, "First Name", "firstnamevalue", EditFirstname, xml);
			compareText(application, "SurName", "surnamevalue", EditSurname, xml);
			compareText(application, "Postal Address", "postaladdressvalue", EditPostaladdress, xml);
			compareText(application, "Email", "emailvalue", EditEmail, xml);
			compareText(application, "Phone", "phonevalue", EditPhone, xml);
			ScrolltoElement(application, "viewpage_backbutton", xml);
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			Log.info("------ View User successful ------");

			//Delete User
			
			ScrolltoElement(application, "customerdetailsheader", xml);
			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Delete", "delete", xml);
			
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
			compareText(application, "Customer Delete success msg", "deletesuccessmsg", "User successfully deleted", xml);
		}

		else if(!UserGrid.contains("1px"))
		{
			//Edit User
			
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Edit", "edit", xml);
			ScrolltoElement(application, "edituser_header", xml);
			compareText(application, "Edit User Header", "edituser_header", "Edit User", xml);
			cleartext(application, "User Name", "UserName");
			addtextFields_commonMethod(application, "User Name", "UserName", EditUsername, xml);
			cleartext(application, "First Name", "FirstName");
			addtextFields_commonMethod(application, "First Name", "FirstName", EditFirstname, xml);
			cleartext(application, "SurName", "SurName");
			addtextFields_commonMethod(application, "SurName", "SurName", EditSurname, xml);
			cleartext(application, "Postal Address", "PostalAddress");
			addtextFields_commonMethod(application, "Postal Address", "PostalAddress", EditPostaladdress, xml);
			cleartext(application, "Email", "adduser_Email");
			addtextFields_commonMethod(application, "Email", "adduser_Email", EditEmail, xml);
			cleartext(application, "Phone", "adduser_Phone");
			addtextFields_commonMethod(application, "Phone", "adduser_Phone", EditPhone, xml);
			cleartext(application, "Password", "Password");
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			scrolltoend();
			click_commonMethod(application, "OK", "user_okbutton", xml);

			//View User
			
			ScrolltoElement(application, "customerdetailsheader", xml);
			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "view", "view", xml);
			scrollToTop();
			compareText(application, "User Name", "usernamevalue", EditUsername, xml);
			compareText(application, "First Name", "firstnamevalue", EditFirstname, xml);
			compareText(application, "SurName", "surnamevalue", EditSurname, xml);
			compareText(application, "Postal Address", "postaladdressvalue", EditPostaladdress, xml);
			compareText(application, "Email", "emailvalue", EditEmail, xml);
			compareText(application, "Phone", "phonevalue", EditPhone, xml);
			scrolltoend();
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			Log.info("------ View User successful ------");

			//Delete User
			
			ScrolltoElement(application, "customerdetailsheader", xml);
			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Delete", "delete", xml);
			
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
			compareText(application, "Customer Delete success msg", "deletesuccessmsg", "User successfully deleted", xml);

		}
	}

	
	
	
	public void verifyorderpanelinformation_Existingorder(String application, String existingorder,
			String expectedorderno, String expectedvoicelineno) throws InterruptedException, DocumentException {
		
//		implicitlyWait("Service screen");
//		webdriverWait(application, "userspanel_header", xml);
		
		
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));//orderpanelheader//userspanel_header

		if (getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Order Panel' page navigated as expected");
			System.out.println("'Order Panel' page navigated as expected");
			
			
		if (existingorder.equalsIgnoreCase("Yes")) {
			try {
			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue")).getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue")).getText();
			System.out.println("actual voice line number displayed in order panel is : " + actualvoicelineno);

			if (expectedorderno.equalsIgnoreCase(actualorderno)
					&& expectedvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

				System.out.println("order information is matched");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : order information is matched");
			} else {
				sa.fail("order information is not matched");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Service Creation page");
				System.out.println(  e+ " : Field is not displayed in Service Creation page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Service Creation page ");
				System.out.println(  e+" : Field is not displayed in Service Creation page");
			}
			
		} else {

			System.out.println("existing order is not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : existing order is not selected");
		}

		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Order panel header is not displayed");
			System.out.println("Order panel header is not displayed");
		}
		
		//sa.assertAll();//Temp
	}

	

	
	
	
	
	public void verifyorderpanelinformation_Neworder(String application, String neworder, String expectedneworderno,
			String expectednewvoicelineno) throws InterruptedException, DocumentException {
		implicitlyWait("Service screen");
		webdriverWait(application, "userspanel_header", xml);
		waitforPagetobeenable();
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")));
		try {
		if (neworder.equalsIgnoreCase("YES")) {

			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue")).getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue"))
					.getText();
			System.out.println("actual voice line number displayed in order panel is : " + actualvoicelineno);

			if (expectedneworderno.equalsIgnoreCase(actualorderno)
					&& expectednewvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

				System.out.println("order information is matched");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : order information is matched");				
			} else {
				sa.fail("order information is not matched");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

		} else {

			System.out.println("new order is not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : new order is not selected");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Order page");
			System.out.println(  e+ " : Field is not displayed in  View Order page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Order page ");
			System.out.println(  e+" : Field is not displayed in View Order page");
		}

		sa.assertAll();
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
		

		WebElement EditOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/editorderno"));
		click_commonMethod(application, "Order Number", "editorderno", xml);
		
		Clear(EditOrderNo);
		
		addtextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);

		WebElement EditVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/editvoicelineno"));
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		
		Clear(EditVoiceLineNo);
		
		addtextFields_commonMethod(application, "RFI Voiceline Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);

		//Edit Order
		
		ScrolltoElement(application, "userspanel_header", xml);
		
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		compareText(application, "Edit Order Header", "editorderheader", "Edit Order", xml);
		
		click_commonMethod(application, "Order Number", "editorderno", xml);
		
		cleartext(application, "Order Number", "editorderno");
		
		addtextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		
		cleartext(application, "RFI Voice Line Number", "editvoicelineno");
		
		addtextFields_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "OK", "editorder_okbutton", xml);
		
		ScrolltoElement(application, "userspanel_header", xml);
		

		if(editorderno.equalsIgnoreCase("Null")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Order/Contract Number (Parent SID)' field is not edited");
			Log.info("'Order/Contract Number (Parent SID)' field is not edited");
		}else {
			compareText(application, "Order Number", "ordernumbervalue", editorderno, xml);
		}
		
		if(editvoicelineno.equalsIgnoreCase("Null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,"'RFI/RFQ/IP Voice Line Number' field is not edited");
			Log.info("'RFI/RFQ/IP Voice Line Number' field is not edited");
		}else {
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno, xml);
		}
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
			
			click_commonMethod(application, "Select order switch", "changeorder_selectorderswitch", xml);
			click_commonMethod(application, "Order Number", "changeordernumber", xml);
			
			addtextFields_commonMethod(application, "Order Number", "changeordernumber", ChangeOrder_newOrderNumber, xml);
			click_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", xml);
			
			addtextFields_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", changevoicelineno, xml);
			click_commonMethod(application, "Create Order", "createorder_button", xml);
			
			ScrolltoElement(application, "userspanel_header", xml);
			
			compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_newOrderNumber, xml);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
			Log.info("------ Change Order is successful ------");
		}
		else if(changeOrderSelection_existingOrder.equalsIgnoreCase("yes")) 
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Performing Change Order functionality");
			
			ScrolltoElement(application, "userspanel_header", xml);
			
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			
			
				addDropdownValues_commonMethod(application, "Order/Contract Number (Parent SID)", "changeorder_chooseorderdropdown", ChangeOrder_existingOrderNumber, xml);
				
				click_commonMethod(application, "OK", "changeorder_okbutton", xml);
				
				ScrolltoElement(application, "userspanel_header", xml);
				
				compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_existingOrderNumber, xml);
				compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
				Log.info("------ Change Order is successful ------");
	
		}
		
	}
	

	
	
	
	
	public void implicitlyWait(String pageNavigation) {
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);			
		}
		
		public void implicitlyWait10S(String pageNavigation) {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);			
			}
		public void webdriverWait(String application, String xpath, XMLReader xml) throws DocumentException, InterruptedException {
			WebDriverWait wait=new WebDriverWait(driver, 300);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xml.getlocator("//locators/" + application + "/"+ xpath +""))));
					//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
		}
	
	





	public void compareText_InViewPageOptional1(String application, String labelname,  String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {
		String text = null;
		WebElement element = null;

		try {
			
			element = getwebelement("//div[label[contains(text(),'"+labelname+"')]]/div");
			String emptyele = element.getText().toString();

			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
				System.out.println(labelname+" not found");
			}
			else if (emptyele!=null && emptyele.isEmpty()) {
//				ExtentTestManager.getTest().log(LogStatus.PASS,  labelname + "' value is empty");
				
				emptyele= "Null";
				
				sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
				
				if(emptyele.equalsIgnoreCase(ExpectedText)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println(" The Expected Text for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
			
					
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					System.out.println(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					
						}
				

			}else 
			{   
				text = element.getText();
				if(text.equals(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				}
				else if(text.contains(ExpectedText)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			System.out.println(labelname + " field is not displaying");
		}

	}
	
	
	public void verifyservicepanelInformationinviewservicepage(
			
			String application, String ServiceType, String NetworkConfiguration, String ServiceIdentification, String BillingType, 
			String TerminationDate, String EmailService, String PhoneService, String Remarks, String ManageService, String RouterConfigurationViewIPv4,
			String RouterConfigurationViewIPv6, String PerformanceReporting, String IPGuardian, String SNMPNotification, String DeliveryChannel, 
			String TrapTargetAddress, String RouterBasedFirewall, String Qos , String ExtendPErangetocustomerLAN
			) throws InterruptedException, DocumentException, IOException {

		
	  	
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		scrolltoview(application, "order panel header", "orderpanelheader", xml);
		
		try {
		compareText(application, "Service panel Header", "servicepanel_header", "Service", xml);
		compareText_InViewPageOptional1(application, "Service Identification", ServiceIdentification, xml);
		compareText_InViewPageOptional1(application, "Service Type", ServiceType, xml);
		compareText_InViewPageOptional1(application, "Network Configuration", NetworkConfiguration, xml);
		compareText_InViewPageOptional1(application, "Billing Type", BillingType, xml);
		//*compareText_InViewPageOptional1(application, "Termination Date", TerminationDate, xml);
		compareText_InViewPageOptional1(application, "Phone Contact", PhoneService, xml);
		compareText_InViewPageOptional1(application, "Email", EmailService, xml);
		compareText_InViewPageOptional1(application, "Remark", Remarks, xml);
		compareText_InViewPageOptional1(application, "Managed Service", ManageService, xml);
		compareText_InViewPageOptional1(application, "Router Configuration View IPv4", RouterConfigurationViewIPv4, xml);
		compareText_InViewPageOptional1(application, "Router Configuration View IPv6", RouterConfigurationViewIPv6, xml);
		compareText_InViewPageOptional1(application, "Performance Reporting", PerformanceReporting, xml);
		compareText_InViewPageOptional1(application, "IP Guardian", IPGuardian, xml);
		compareText_InViewPageOptional1(application, "Delivery Channel", DeliveryChannel, xml);
		ScrolltoElement(application, "servicepanel_header", xml);
		compareText_InViewPageOptional1(application, "Router Based Firewall", RouterBasedFirewall, xml);
		compareText_InViewPageOptional1(application, "QoS", Qos, xml);
		compareText_InViewPageOptional1(application, "Extend PE range to customer LAN", ExtendPErangetocustomerLAN, xml);
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Service page");
			System.out.println(  e+ " : Field is not displayed in View Service page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Service page ");
			System.out.println(  e+" : Field is not displayed in View Service page");
		}
		
	}

	
public void SelectDropdownValueUnderDivTag(String application ,String lebelname, String dropdownToBeSelectedInTheEnd, String dropdownXpath, String commonDropdownValueTag, XMLReader xml) throws InterruptedException, DocumentException, IOException {
		
		try {
			// Select  Country dropdown
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+dropdownXpath+"")));
				List<WebElement> dropdownValueList = driver.findElements(By.xpath(commonDropdownValueTag));
				
				for (WebElement dropdownvaluelist : dropdownValueList) {
					System.out.println("Available " +lebelname+ " are : " + dropdownvaluelist.getText().toString()+ "  ,  ");
					ExtentTestManager.getTest().log(LogStatus.PASS,"Step : Available  '" +lebelname+ "'  is : " + dropdownvaluelist.getText().toString());
					Log.info("Available " +lebelname+ " is : " + dropdownvaluelist.getText().toString());
				}
				
				
				
				// click on Country dropdown
				WebElement selectDropdownValue = driver.findElement(By.xpath("//div[contains(text(),'"+dropdownToBeSelectedInTheEnd+"')]"));
				System.out.println("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected  '"+lebelname+ "'  is : " + selectDropdownValue.getText().toString());
				Log.info("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
				selectDropdownValue.click();
				
			
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
		System.out.println( lebelname + " dropdown is not displaying");
		
	}catch(Exception ee) {
		ee.printStackTrace();
		
		ExtentTestManager.getTest().log(LogStatus.FAIL, lebelname + " dropdown is not displaying" );
		System.out.println( lebelname + " dropdown is not displaying");
	}

}
				
	public void verifyServicePanelActions_NoCPE(
			String application, String ServiceType, String NetworkConfiguration, String ServiceIdentification, String BillingTypeEdit, 
			String TerminationDateEdit, String EmailServiceEdit, String PhoneServiceEdit, String RemarksEdit, String ManageServiceEdit, String RouterConfigurationViewIPv4Edit,
			String RouterConfigurationViewIPv6Edit, String PerformanceReportingEdit, String IPGuardianEdit, String SNMPNotificationEdit, String DeliveryChannelEdit, 
			String TrapTargetAddressEdit, String RouterBasedFirewallEdit, String QosEdit , String ExtendPErangetocustomerLANEdit
			) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		
		ScrolltoElement(application, "orderpanelheader", xml);
		
		try { 
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		
		
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		ScrolltoElement(application, "orderpanelheader", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to view service page");
		}
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to view service page");

		//Edit service
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/EditService_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Servce' page navigated as expected");
			System.out.println("'Edit Servce' page navigated as expected");
			
		//**scrollToTop();
		try {
			
		//*ClearAndEnterTextValue(application, "Service Identification", "serviceidentificationtextfield", ServiceIdentification, xml);
		//**addDropdownValues_commonMethod(application, "Billing Type", "billingtype_dropdown", BillingTypeEdit, xml);
		//SelectDropdownValueUnderDivTag(application, "Billing Type", BillingTypeEdit, "billingtype_dropdown", "commonDropdownValueTag", xml);
		SelectDropdownValueUnderSelectTag(application, "Billing Type", BillingTypeEdit, "billingtype_dropdown", xml);

		//*ClearAndEnterTextValue(application, "termination date", "terminationdate_field", TerminationDateEdit, xml);
		ClearAndEnterTextValue(application, "Email", "emailtextfield_Service", EmailServiceEdit, xml);
		ClearAndEnterTextValue(application, "Phone Contact", "phonecontacttextfield", PhoneServiceEdit, xml);
		ClearAndEnterTextValue(application, "Remark", "remarktextarea", RemarksEdit, xml);
		
		
		scrolltoview(application, "Configuration Options header", "ConfigurationOptionEditServicePage_Header", xml);
		// management options panel
				if(ManageServiceEdit.equalsIgnoreCase("Yes")){
					addCheckbox_commonMethod(application, "ManageServicecheckbox", "Manage Service", ManageServiceEdit, "yes", xml);
				}else{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Manage Service checkbox is not checked");
				}
				

				if(RouterConfigurationViewIPv4Edit.equalsIgnoreCase("Yes"))	{
					addCheckbox_commonMethod(application, "RouterConfigurationViewIPv4checkbox", "Router Configuration View IPv4", RouterConfigurationViewIPv4Edit, "yes", xml);
				}else{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Router Configuration View IPv4 checkbox is not checked");
				}
				
				if(RouterConfigurationViewIPv6Edit.equalsIgnoreCase("Yes"))	{
					addCheckbox_commonMethod(application, "RouterConfigurationViewIPv6checkbox", "Router Configuration View IPv6", RouterConfigurationViewIPv6Edit, "yes", xml);
				}else{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Router Configuration View IPv6 checkbox is not checked");
				}
				
				if(PerformanceReportingEdit.equalsIgnoreCase("Yes"))
				{
					addCheckbox_commonMethod(application, "performancereportingcheckbox", "Performance Reporting", PerformanceReportingEdit, "yes", xml);
				}else{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Performance Reporting checkbox is not checked");
				}
				
				
				if(IPGuardianEdit.equalsIgnoreCase("Yes")){
					addCheckbox_commonMethod(application, "IPGuardiancheckbox", "IP Guardian", IPGuardianEdit, "yes", xml);
				}else{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : IP Guardian checkbox is not checked");
				}

				if(SNMPNotificationEdit.equalsIgnoreCase("Yes")){
					addCheckbox_commonMethod(application, "SNMPNotificationcheckbox", "SNMP Notification", SNMPNotificationEdit, "no", xml);
					ClearAndEnterTextValue(application, "Trap Target Address", "TrapTargetAddresstextfield", TrapTargetAddressEdit, xml);

				}else{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SNMP Notification checkbox is not checked");
				}
				
				//**addDropdownValues_commonMethod(application, "Delivery Channel", "DeliveryChannelDropdown", DeliveryChannelEdit, xml);
				//SelectDropdownValueUnderDivTag(application, "Delivery channel", DeliveryChannelEdit, "DeliveryChannelDropdown", "commonDropdownValueTag", xml);
				SelectDropdownValueUnderSelectTag(application, "Delivery Channel", DeliveryChannelEdit, "DeliveryChannelDropdown", xml);

				
				//		Configuration Options
				if(RouterBasedFirewallEdit.equalsIgnoreCase("Yes")){
					addCheckbox_commonMethod(application, "RouterBasedFirewallcheckbox", "Router Based Firewall", RouterBasedFirewallEdit, "yes", xml);
				}else{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Router Based Firewall checkbox is not checked");
				}
				
				if(QosEdit.equalsIgnoreCase("Yes")){
					addCheckbox_commonMethod(application, "Qoscheckbox", "Qos", QosEdit, "yes", xml);
				}else{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Qos checkbox is not checked");
				}
				
				if(ExtendPErangetocustomerLANEdit.equalsIgnoreCase("Yes")){
					addCheckbox_commonMethod(application, "ExtendPErangetocustomerLANcheckbox", "Extend PE range to customer LAN", ExtendPErangetocustomerLANEdit, "no", xml);
				}else{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Extend PE range to customer LAN checkbox is not checked");
				}
				
		
		
		

		
		scrolltoend();
		click_commonMethod(application, "OK", "editservice_okbutton", xml);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			verifysuccessmessage(application, "Service successfully updated");
		}
		else
		{
			Log.info("Service not updated");
			System.out.println("Service not updated");
		}

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Service page");
			System.out.println(  e+ " : Field is not displayed in Edit Service page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Edit Service page ");
			System.out.println(  e+" : Field is not displayed in Edit Service page");
		}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit Service' page not navigated");
			System.out.println("'Edit Service' page not navigated");
		}
		
		
		
		
		
		
		try {	
		//Manage subnets
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage Subnets", "managesubnets_link", xml);
		waitforPagetobeenable();
		GetText(application, "Manage Subnet header", "managesubnet_header");
		compareText(application, "Space Name", "spacename_column", "Space Name", xml);
		compareText(application, "Block Name", "blockname_column", "Block Name", xml);
		compareText(application, "Subnet Name", "subnetname_column", "Subnet Name", xml);
		compareText(application, "Start Address", "startaddress_column", "Start Address", xml);
		compareText(application, "Size", "size_column", "Size", xml);
		click_commonMethod(application, "Close", "closesymbol", xml);
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Manage subnets page");
			System.out.println(  e+ " : Field is not displayed in Manage subnets page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Manage subnets page ");
			System.out.println(  e+" : Field is not displayed in Manage subnets page");
		}
		
		
		try {
		//manage subnets IPv6
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage Subnets Ipv6", "managesubnetsipv6_link", xml);
		waitforPagetobeenable();
		GetText(application, "Manage Subnet header", "managesubnet_header");
		compareText(application, "Space Name", "spacename_column", "Space Name", xml);
		compareText(application, "Block Name", "blockname_column", "Block Name", xml);
		compareText(application, "Subnet Name", "subnetname_column", "Subnet Name", xml);
		compareText(application, "Start Address", "startaddress_column", "Start Address", xml);
		compareText(application, "Size", "size_column", "Size", xml);
		
		click_commonMethod(application, "Close", "closesymbol", xml);
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in manage subnets IPv6 page");
			System.out.println(  e+ " : Field is not displayed in manage subnets IPv6 page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in manage subnets IPv6 page ");
			System.out.println(  e+" : Field is not displayed in manage subnets IPv6 page");
		}
		
		

		//dump
		try {
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Dump", "dump_link", xml);
		
		GetText(application, "Dump header", "dumppage_header");
		GetText(application, "Service retrieved time", "serviceretrieved_text");
		compareText(application, "Service header", "service_header", "Service", xml);
		GetText(application, "Dump page service details", "dumppage_text");
		
		click_commonMethod(application, "Close", "closesymbol", xml);
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Dump page");
			System.out.println(  e+ " : Field is not displayed in Dump page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Dump page ");
			System.out.println(  e+" : Field is not displayed in Dump page");
		}
		
		//Service delete is performed in the last test case
	}

	
	public void verifyManageServiceNew(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		//Manage service
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage", "manageLink", xml);
		
		waitforPagetobeenable();
		scrollToTop();
		compareText(application, "Manage service header", "manageservice_header", "Manage Service", xml);
		compareText(application, "Status header", "statuspanel_header", "Status", xml);
		
		//Status panel headers
		compareText(application, "Service Header", "status_serviceheader", "Service", xml);
		compareText(application, "Service Type Header", "status_servicetypeheader", "Service Type", xml);
		compareText(application, "Details Header", "status_detailsheader", "Details", xml);
		compareText(application, "Status Header", "status_statusheader", "Status", xml);
		compareText(application, "Last Modification Header", "status_modificationheader", "Last Modification", xml);
		
		GetText(application, "Order Name", "status_ordername");
		compareText(application, "Service Identification", "status_servicename", sid, xml);
		compareText(application, "Service type", "status_servicetype", servicetype, xml);
		GetText(application, "Service Details", "status_servicedetails");
		GetText(application, "Service Status", "status_currentstatus");

		String LastModificationTime_value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/status_modificationtime")));
		if(LastModificationTime_value.contains("UTC"))
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
				compareText(application, "Service status header", "servicestatus_header", "Service Status", xml);
				compareText(application, "Service Identification", "statuspage_serviceidentification", sid, xml);
				compareText(application, "Service Type", "statuspage_servicetype", servicetype, xml);
				compareText(application, "Service Status History Header", "servicestatushistory_header", "Service Status History", xml);
				GetText(application, "Current Status", "statuspage_currentstatus");
								
				click_commonMethod(application, "New Status", "statuspage_newstatusdropdown", xml);
				click_commonMethod(application, "New Status value", "statuspage_newstatusdropdownvalue", xml);
				click_commonMethod(application, "OK", "okbutton", xml);
				
				scrolltoend();
				WebElement ServiceStatusHistory= getwebelement(xml.getlocator("//locators/" + application + "/servicestatushistory"));
				try
				{
					if(ServiceStatusHistory.isDisplayed())
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request logged");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request is not logged");
					}
				}
				catch(StaleElementReferenceException e)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No service history to display");
				}
				click_commonMethod(application, "Close", "servicestatus_popupclose", xml);
			}
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Status link is not working");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change not required");
		}

		//synchronize panel in manage service page
		ScrolltoElement(application, "statuspanel_header", xml);
		
		//Synchronisation panel headers
		compareText(application, "Service Header", "sync_serviceheader", "Service", xml);
		compareText(application, "Service Type Header", "status_servicetypeheader", "Service Type", xml);
		compareText(application, "Details Header", "sync_detailsheader", "Details", xml);
		compareText(application, "Sync Status Header", "sync_syncstatus", "Sync Status", xml);
		
		GetText(application, "Order Name", "sync_ordername");
		compareText(application, "Service Identification", "sync_servicename", sid, xml);
		compareText(application, "Service type", "sync_servicetype", servicetype, xml);
		GetText(application, "Service Details", "sync_servicedetails");
		GetText(application, "Sync Status", "sync_status");
		GetText(application, "Vistamart Status", "vistamart_status");
		GetText(application, "Vistamart DateTime", "vistamart_datetime");
		try {
			if(getwebelement(xml.getlocator("//locators/" + application + "/synchronizelink")).isDisplayed())
			{
				click_commonMethod(application, "Synchronize", "synchronizelink", xml);
				
				verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
			}
		}
		catch (Exception e) {
			String Synchronization_serviceError= getwebelement(xml.getlocator("//locators/" + application + "/synchronization_serviceerror")).getText();
			ExtentTestManager.getTest().log(LogStatus.INFO, "Synchronize link is not displaying. It is displaying as: " +Synchronization_serviceError);
		}
		
		scrolltoend();
		
		//Devices for service panel
		
		//Devices for service panel headers
		compareText(application, "Device header", "devicesforservice_deviceheader", "Device", xml);
		compareText(application, "Sync Status header", "devicesforservice_syncstatus", "Sync Status", xml);
		compareText(application, "Smarts header", "devicesforservice_smartsheader", "Smarts", xml);
		compareText(application, "Fetch Interfaces header", "devicesforservice_fetchinterfacesheader", "Fetch Interfaces", xml);
		compareText(application, "VistaMart Device header", "devicesforservice_vistamartdeviceheader", "VistaMart Device", xml);
		
		GetText(application, "Device Name", "deviceforservicepanel_devicename");
		GetText(application, "Sync Status", "deviceforservicepanel_syncstatus");
//		verify_SmartsValue(application);
//		verify_FetchInterfacesValue(application);
//		verify_VistamartDeviceValue(application);
		click_commonMethod(application, "Manage", "deviceforservicepanel_managelink", xml);
		
		scrollToTop();
		compareText(application, "Manage Network header", "managenetwork_header", "Manage COLT's Network - Manage Network", xml);
		driver.navigate().back();
		
		scrolltoend();
		
		click_commonMethod(application, "Back", "managepage_backbutton", xml);
		
		waitforPagetobeenable();
	}
	
	public void verifyManageService(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		
		try {
		//Manage service
		implicitlyWait("Service screen");
		webdriverWait(application, "orderpanelheader", xml);
		
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage", "manageLink", xml);
		
		scrollToTop();
		compareText(application, "Manage service header", "manageservice_header", "Manage Service", xml);
		GetText(application, "Order Name", "status_ordername");
		GetText(application, "Service Identification", "status_servicename");
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

		if(servicestatuschangerequired=="Yes")
		{
			WebElement ServiceStatusPage= getwebelement(xml.getlocator("//locators/" + application + "/Servicestatus_popup"));
			if(ServiceStatusPage.isDisplayed())
			{
				click_commonMethod(application, "Change Status", "changestatus_dropdown", xml);
				click_commonMethod(application, "Change Status value", "changestatus_dropdownvalue", xml);
				click_commonMethod(application, "OK", "okbutton", xml);
				WebElement ServiceStatusHistory= getwebelement(xml.getlocator("//locators/" + application + "/servicestatushistory"));
				try
				{
					if(ServiceStatusHistory.isDisplayed())
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request logged");
					}
					else
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request is not logged");
				}
				catch(StaleElementReferenceException e)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No service history to display");
				}
			}
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Status link is not working");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change not reqired");
			click_commonMethod(application, "Close", "servicestatus_popupclose", xml);
		}

		GetText(application, "Order Name", "sync_ordername");
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
		GetText(application, "Sync Status", "sync_status");
		click_commonMethod(application, "Back", "managepage_backbutton", xml);
		
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
			System.out.println(  e+ " : Field is not displayed");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
			System.out.println(  e+" : Field is not displayed");
		}
		sa.assertAll();

	}

	
	
	public void verifyManageService2(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		//Manage service
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage", "manageLink", xml);
		
		scrollToTop();
		compareText(application, "Manage service header", "manageservice_header", "Manage Service", xml);
		compareText(application, "Status header", "statuspanel_header", "Status", xml);
		
		//Status panel headers
		compareText(application, "Service Header", "status_serviceheader", "Service", xml);
		compareText(application, "Service Type Header", "status_servicetypeheader", "Service Type", xml);
		compareText(application, "Details Header", "status_detailsheader", "Details", xml);
		compareText(application, "Status Header", "status_statusheader", "Status", xml);
		compareText(application, "Last Modification Header", "status_modificationheader", "Last Modification", xml);
		
		GetText(application, "Order Name", "status_ordername");
		compareText(application, "Service Identification", "status_servicename", sid, xml);
		compareText(application, "Service type", "status_servicetype", servicetype, xml);
		GetText(application, "Service Details", "status_servicedetails");
		GetText(application, "Service Status", "status_currentstatus");

		String LastModificationTime_value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/status_modificationtime")));
		if(LastModificationTime_value.contains("UTC"))
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
				compareText(application, "Service status header", "servicestatus_header", "Service Status", xml);
				compareText(application, "Service Identification", "statuspage_serviceidentification", sid, xml);
				compareText(application, "Service Type", "statuspage_servicetype", servicetype, xml);
				compareText(application, "Service Status History Header", "servicestatushistory_header", "Service Status History", xml);
				GetText(application, "Current Status", "statuspage_currentstatus");
								
				click_commonMethod(application, "New Status", "statuspage_newstatusdropdown", xml);
				click_commonMethod(application, "New Status value", "statuspage_newstatusdropdownvalue", xml);
				click_commonMethod(application, "OK", "okbutton", xml);
				
				scrolltoend();
				WebElement ServiceStatusHistory= getwebelement(xml.getlocator("//locators/" + application + "/servicestatushistory"));
				try
				{
					if(ServiceStatusHistory.isDisplayed())
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request logged");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request is not logged");
					}
				}
				catch(StaleElementReferenceException e)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No service history to display");
				}
				click_commonMethod(application, "Close", "servicestatus_popupclose", xml);
			}
			else
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Status link is not working");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change not required");
		}

		//synchronize panel in manage service page
		ScrolltoElement(application, "statuspanel_header", xml);
		
		//Synchronisation panel headers
		compareText(application, "Service Header", "sync_serviceheader", "Service", xml);
		compareText(application, "Service Type Header", "status_servicetypeheader", "Service Type", xml);
		compareText(application, "Details Header", "sync_detailsheader", "Details", xml);
		compareText(application, "Sync Status Header", "sync_syncstatus", "Sync Status", xml);
		
		GetText(application, "Order Name", "sync_ordername");
		compareText(application, "Service Identification", "sync_servicename", sid, xml);
		compareText(application, "Service type", "sync_servicetype", servicetype, xml);
		GetText(application, "Service Details", "sync_servicedetails");
		GetText(application, "Sync Status", "sync_status");
		GetText(application, "Vistamart Status", "vistamart_status");
		GetText(application, "Vistamart DateTime", "vistamart_datetime");
		try {
			if(getwebelement(xml.getlocator("//locators/" + application + "/synchronizelink")).isDisplayed())
			{
				click_commonMethod(application, "Synchronize", "synchronizelink", xml);
				verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
			}
		}
		catch (Exception e) {
			String Synchronization_serviceError= getwebelement(xml.getlocator("//locators/" + application + "/synchronization_serviceerror")).getText();
			ExtentTestManager.getTest().log(LogStatus.INFO, "Synchronize link is not displaying. It is displaying as: " +Synchronization_serviceError);
		}
		
		scrolltoend();
		
		//Devices for service panel
		
		//Devices for service panel headers
		compareText(application, "Device header", "devicesforservice_deviceheader", "Device", xml);
		compareText(application, "Sync Status header", "devicesforservice_syncstatus", "Sync Status", xml);
		compareText(application, "Smarts header", "devicesforservice_smartsheader", "Smarts", xml);
		compareText(application, "Fetch Interfaces header", "devicesforservice_fetchinterfacesheader", "Fetch Interfaces", xml);
		compareText(application, "VistaMart Device header", "devicesforservice_vistamartdeviceheader", "VistaMart Device", xml);
		
		GetText(application, "Device Name", "deviceforservicepanel_devicename");
		GetText(application, "Sync Status", "deviceforservicepanel_syncstatus");
//		verify_SmartsValue(application);
//		verify_FetchInterfacesValue(application);
//		verify_VistamartDeviceValue(application);
		click_commonMethod(application, "Manage", "deviceforservicepanel_managelink", xml);
		
		scrollToTop();
		compareText(application, "Manage Network header", "managenetwork_header", "Manage COLT's Network - Manage Network", xml);
		driver.navigate().back();
		
		scrolltoend();
		
		click_commonMethod(application, "Back", "managepage_backbutton", xml);
		

	}
	

	public void navigateToAddNewDevicepage_PE(String application) throws InterruptedException, DocumentException {
		ScrolltoElement(application, "portalaccess_header", xml);
		
		compareText(application, "Provider Equipment (PE)", "providerequipment_header", "Provider Equipment (PE)", xml);
		click_commonMethod(application, "Add PE Device", "addpedevice_link", xml);
		
		compareText(application, "Add PE Device Header", "addpedevice_header", "Add PE Device", xml);
		click_commonMethod(application, "Add New Device toggle", "addnewdevice_togglebutton", xml);
		
	}

	public void verifyadddevicefields_PE(String application) throws InterruptedException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add New PE Device page Fields");
		try {

			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), "Name" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), "Vendor Model" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), "Management Address" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3username")), "Snmp v3 Username" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3authpassword")), "Snmp v3 Auth password" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/snmpv3privpassword")), "Snmp v3 Priv password" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), "Country" );
			scrolltoend();
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/citydropdowninput")), "City" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/sitedropdowninput")), "Site" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/premisedropdowninput")), "Premise" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/addcityswitch")), "Add City" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/addsiteswitch")), "Add Site" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/addpremiseswitch")), "Add Premise");
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton")), "Cancel");
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")), "Next");
			scrollToTop();

		} catch (NoSuchElementException e) {
			System.out.println("webElement is not  present");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: expected field is not displayed in page ");
			e.printStackTrace();
		}catch (TimeoutException e) {
			System.out.println("webElement is not  present" );
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: expected field is not displayed in page ");
			e.printStackTrace();
		}
	}

	public void addDropdownValues_commonMethod2(String application, String labelname, String xpath,
			String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
		boolean availability = false;
		try {
			availability = getwebelement(xml.getlocator("//locators/" + application + "/" + xpath + "")).isDisplayed();
			if (availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				System.out.println(labelname + " dropdown is displaying");

				if (expectedValueToAdd.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under " + labelname + " dropdown");
					System.out.println(" No values selected under " + labelname + " dropdown");
				} else {

					Clickon(getwebelement("//div[label[text()='" + labelname + "']]//div[text()='×']"));
					

					// verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

					ExtentTestManager.getTest().log(LogStatus.PASS,
							" List of values inside " + labelname + " dropdown is:  ");
					System.out.println(" List of values inside " + labelname + "dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS, " " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					//
					SendKeys(getwebelement("//div[label[text()='" + labelname + "']]//input"), expectedValueToAdd);
					

					Clickon(getwebelement("(//div[contains(text(),'" + expectedValueToAdd + "')])[1]"));
					

					String actualValue = getwebelement(
							"//label[text()='" + labelname + "']/following-sibling::div//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS,
							labelname + " dropdown value selected as: " + actualValue);
					System.out.println(labelname + " dropdown value selected as: " + actualValue);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				System.out.println(labelname + " is not displaying");
			}
		} catch (NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
			System.out.println(labelname + " is not displaying");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" NOt able to perform selection under " + labelname + " dropdown");
			System.out.println(" NO value selected under " + labelname + " dropdown");
		}

	} 
	
	public void addDropdownValues_commonMethod3(String application, String labelname, String xpath,
			String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
		boolean availability = false;
		try {
			availability = getwebelement(xml.getlocator("//locators/" + application + "/" + xpath + "")).isDisplayed();
			if (availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				System.out.println(labelname + " dropdown is displaying");

				if (expectedValueToAdd.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under " + labelname + " dropdown");
					System.out.println(" No values selected under " + labelname + " dropdown");
				} else {

					Clickon(getwebelement("//div[label[text()='" + labelname + "']]//div[text()='×']"));
					

					// verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

					ExtentTestManager.getTest().log(LogStatus.PASS,
							" List of values inside " + labelname + " dropdown is:  ");
					System.out.println(" List of values inside " + labelname + "dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS, " " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					//
					SendKeys(getwebelement("//div[label[text()='" + labelname + "']]//input"), expectedValueToAdd);
					

					Clickon(getwebelement("(//span[contains(text(),'" + expectedValueToAdd + "')])[1]"));
					

					String actualValue = getwebelement("//label[text()='" + labelname + "']/following-sibling::div//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS,
							labelname + " dropdown value selected as: " + actualValue);
					System.out.println(labelname + " dropdown value selected as: " + actualValue);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
				System.out.println(labelname + " is not displaying");
			}
		} catch (NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
			System.out.println(labelname + " is not displaying");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL," NOt able to perform selection under " + labelname + " dropdown");
			System.out.println(" NO value selected under " + labelname + " dropdown");
		}

	} 

	public void SelectDropdownValueUnderSelectTag(String application, String labelname , String dropdownToBeSelectedInTheEnd ,  String dropdownXpath,   XMLReader xml) throws InterruptedException, DocumentException, IOException {
		

		{ 
		//SelectDropdownValueUnderSelectTag
		boolean availability=false;
		List<String> ls = new ArrayList<String>();

		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdownXpath +"")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
			  System.out.println(labelname + " dropdown is displaying");
			  
			  WebElement el =getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdownXpath +""));
			  
			  Select sel = new Select(el);
			  
			 String firstSelectedOption=sel.getFirstSelectedOption().getText();
			 ExtentTestManager.getTest().log(LogStatus.PASS, "By default "+ labelname+" dropdown is displaying as: "+firstSelectedOption);
			 System.out.println("By default "+ labelname+" dropdown is displaying as: "+firstSelectedOption);
			 
			    List<WebElement> we = sel.getOptions();
			   
			    for(WebElement a : we)
			    {
			        if(!a.getText().equals("select")){
			            ls.add(a.getText());
			            
			        }
			    }

			    
			    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
		        System.out.println("list of values inside "+labelname+" dropdown is: "+ls);
		        
			  if(dropdownToBeSelectedInTheEnd.equalsIgnoreCase("null")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, "No values selected under "+ labelname + " dropdown");
			  }else {
				  Select s1=new Select(el);
				  s1.selectByVisibleText(dropdownToBeSelectedInTheEnd);
				  
				  String SelectedValueInsideDropdown=sel.getFirstSelectedOption().getText();
					 ExtentTestManager.getTest().log(LogStatus.PASS,  labelname+" dropdown value selected as: "+SelectedValueInsideDropdown);
					 System.out.println(labelname+" dropdown value selected as: "+SelectedValueInsideDropdown);
			  }
			 }
			
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " Value is not displaying");
			  System.out.println(labelname + " value is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
			System.out.println(" NO value selected under "+ labelname + " dropdown");
			}
				}
		}
	
	
	
	
	
	public void addNewPEDevice_PE(String application, String name, String devicetype, 
			String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
			String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
			Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
			String Snmpv3Privpasswordvalue, String Country, String managementaddress, String existingcity, 
			String existingcityvalue, String existingsite,
			String existingsitevalue, String existingpremise, 
			String existingpremisevalue, String newcity,String cityname,String Citycode,
			String sitename, String sitecode,  String premisename,  String premisecode, 
			String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add New PE Device Functionality");
		try {

			scrolltoend();
			//Verify Mandatory fields
			click_commonMethod(application, "Next", "Next_Button", xml);
			
			scrollToTop();
			
			warningMessage_commonMethod(application, "warningMessage_name", "Device Name", xml);
			warningMessage_commonMethod(application, "warningMessage_vendor", "Vendor/Model", xml);
			//**warningMessage_commonMethod(application, "warningMessage_Country", "Country", xml);

			//name
			addtextFields_commonMethod(application, "Name", "nametextfield", name, xml);

			//vendormodel
			//addDropdownValues_commonMethod3(application, "Vendor/Model", "vendormodelinput", vendormodel, xml);
			SelectDropdownValueUnderSelectTag(application, "Vendor/Model", vendormodel, "vendormodelinput", xml);

			//Management address
			//javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
			addtextFields_commonMethod(application, "Management Address", "managementaddresstextbox", managementaddress, xml);

			//Connectivity Protocol		
			if((telnet.equalsIgnoreCase("No")) && (ssh.equalsIgnoreCase("No"))) {

				verifyDefaultSelection_connectivityprotocol_ssh(application);

				verifyDefaultSelection_connectivityprotocol_telnet(application);

			}else{
				verifyDefaultSelection_connectivityprotocol_ssh(application);

				verifyDefaultSelection_connectivityprotocol_telnet(application);

				if((telnet.equalsIgnoreCase("Yes")) && (ssh.equalsIgnoreCase("No"))) {

					//telnet
					addCheckbox_commonMethod(application, "telnetradiobutton", "Telnet", telnet, "No", xml);

				}
				else if((telnet.equalsIgnoreCase("No")) && (ssh.equalsIgnoreCase("Yes"))) {

					//ssh
					addCheckbox_commonMethod(application, "sshradiobutton", "SSH", ssh, "Yes", xml);

				}

			}
			
			
			

			//SNMP version		
			if((snmp2c.equalsIgnoreCase("Yes"))  && (snmp3.equalsIgnoreCase("NO"))) {

				addCheckbox_commonMethod(application, "c2cradiobutton", "SNMP Version-2c", snmp2c, "No", xml);

				//snmpro	
				edittextFields_SNMPversion(application, "Snmpro", "snmprotextfield", snmpro2cvalue);

				//snmprw 
				edittextFields_SNMPversion(application, "Snmprw", "snmprwtextfield", snmprw2cvalue);

			}else if((snmp2c.equalsIgnoreCase("no"))  && (snmp3.equalsIgnoreCase("yes"))) {

				addCheckbox_commonMethod(application, "c3radiobutton", "SNMP Version-3", snmp3, "Yes", xml);

				//Snmp v3 Username	
				edittextFields_SNMPversion(application, "Snmp v3 Username", "snmpv3username", Snmpv3Usernamevalue);

				//Snmp v3 Auth password
				edittextFields_SNMPversion(application, "Snmp v3 Auth password", "snmpv3authpassword", Snmpv3Authpasswordvalue);

				//Snmp v3 Priv password
				edittextFields_SNMPversion(application, "Snmp v3 Priv password", "snmpv3privpassword", Snmpv3Privpasswordvalue);

			}

			//select country
			scrolltoend();
			  scrolltoview(application, "Snprw Label", "SnmprwLabelAddPEDevicePage", xml);
			  
			//SelectDropdownValueUnderDivTag(application, "Country", Country, "countryinput", "commonDropdownValueTag", xml);
			SelectDropdownValueUnderSelectTag(application, "Country", Country, "countryinput", xml);
			scrolltoend();
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
				
			   //addDropdownValues_commonMethod(application, "City", "citydropdowninput", existingcityvalue, xml);
				SelectDropdownValueUnderSelectTag(application, "City", existingcityvalue, "citydropdowninput", xml);

				
			 //Existing Site
				  if(existingsite.equalsIgnoreCase("yes") & newsite.equalsIgnoreCase("no")) {
					  //addDropdownValues_commonMethod(application, "Site", "sitedropdowninput", existingsitevalue, xml);
						SelectDropdownValueUnderSelectTag(application, "Site", existingsitevalue, "sitedropdowninput", xml);

				 //Existing Premise
					  if(existingpremise.equalsIgnoreCase("yes") & NewPremise.equalsIgnoreCase("no")) {
						  //addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", existingpremisevalue, xml);
							SelectDropdownValueUnderSelectTag(application, "Premise", existingpremisevalue, "premisedropdowninput", xml);

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
					
	
	} catch (StaleElementReferenceException e) {
		
		e.printStackTrace();
	}
	
		scrolltoend();
		
		click_commonMethod(application, "Next", "Next_Button", xml);
		
		scrollToTop();
		compareText(application, "Add Device success msg", "PE_AddPEDeviceSuccessfulMessage", "Device successfully created.", xml);
		
	}

	public void verifyViewpage_Devicedetails_PE(String application, String name, String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro,
			String Snmprw, 	String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String Snmpv3Authpassword,
			String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue, String Snmpv3Privpasswordvalue, String Country, 
			String managementaddress, String existingcity, 	String existingcityvalue, String existingsite, String existingsitevalue, String existingpremise, 
			String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename, String sitecode,  String premisename, 
			String premisecode, String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Added New PE Device Information");
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Newly created device information'");
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
			try {
		//Device Name
		compareText(application, "Name", "viewpage_devicename", name, xml);


		//Vendor/model
		compareText(application, "Vendor/Model", "viewpage_vendormodel", vendormodel, xml);

		//Connectivity protocol
		if((telnet.equalsIgnoreCase("Yes")) && (ssh.equalsIgnoreCase("No"))){
			compareText(application, "Telnet", "viewpage_connectivityprotocol", "telnet", xml);
		}
		else if((telnet.equalsIgnoreCase("no")) && (ssh.equalsIgnoreCase("Yes"))){
			compareText(application, "SSH", "viewpage_connectivityprotocol", "ssh", xml);
		}

		//SNMP Version
		if((snmp2c.equalsIgnoreCase("Yes")) && (snmp3.equalsIgnoreCase("No"))) {

			//snmp version
			compareText(application, "SNMP Version", "viewpage_snmpversion", "2c", xml);

			//Snmpro
			compareText(application, "Snmpro", "viewpage_snmpro", "incc", xml);

			//Snmprw
			compareText(application, "Snmprw", "viewpage_snmprw", "ip4corp3", xml);

		}
		else if((snmp2c.equalsIgnoreCase("No")) && (snmp3.equalsIgnoreCase("Yes"))) {

			//Snmp v3 Username
			compareText(application, "Snmp v3 Username", "viewpage_snmpv3username", "colt-nms", xml);


			//Snmp v3 Auth Password
			compareText(application, "Snmp V3 Auth Password", "viewpage_snmpv3authpassword", "OrHzjWmRvr4piJZb", xml);

			//Snmp v3 Priv Password
			compareText(application, "Snmp V3 Priv Password", "viewpage_snmpv3privpassword", "3k0hw8thNxHucQkE", xml);
		}

		
		
		//Management Address
		compareText(application, "Management Address", "viewpage_managementaddress", managementaddress, xml);

		//Country
		compareText(application, "Country", "viewpage_country", Country, xml);

		//City
		if((existingcity.equalsIgnoreCase("yes")) && (newcity.equalsIgnoreCase("NO"))) {

			//Existing City
			//**compareText(application, "City", "viewpage_city", existingcityvalue, xml);
			GetText(application, "City", "viewpage_city");
		}else if((existingcity.equalsIgnoreCase("NO")) && (newcity.equalsIgnoreCase("Yes"))) {

			//new City
			compareText(application, "City", "viewpage_city", cityname, xml);
		}

		
		
		
		//Site
		if((existingsite.equalsIgnoreCase("yes")) && (newsite.equalsIgnoreCase("NO"))) {
			//Existing Site
			compareText(application, "Site", "viewpage_site", existingsitevalue, xml);
		}

		else if((existingsite.equalsIgnoreCase("No")) && (newsite.equalsIgnoreCase("Yes"))) {

			//New Site
			compareText(application, "Site", "viewpage_site", sitename, xml);
		}

		
		
		//Premise
		if((existingpremise.equalsIgnoreCase("yes")) && (NewPremise.equalsIgnoreCase("NO"))) {

			//Existing premise
			compareText(application, "Premise", "viewpage_premise", existingpremisevalue, xml);
		}

		else if((existingpremise.equalsIgnoreCase("No")) && (NewPremise.equalsIgnoreCase("Yes"))) {

			//new premise
			compareText(application, "Premise", "viewpage_premise", premisename, xml);
		}
		
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View PE Device page");
				System.out.println(  e+ " : Field is not displayed in View PE Device page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View PE Device page");
				System.out.println(  e+" : Field is not displayed in View PE Device page");
			}

			
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
				System.out.println("'View PE Device' page not navigated");
			}
		
	}

	
	
	
	
public void testStatus_PE(String application) throws InterruptedException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Test Status' table");
		scrollToTop();
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
				System.out.println("Test Name is displaying as: "+element);
				
				
				status=getwebelement("(//tbody/tr["+ i +"]/td)[2]/div").getAttribute("class");
				System.out.println("status displays as: "+status);
				
				if(status.contains("red")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: red");
					System.out.println(element + " status colour dipslays as: red");
				}
				else if(status.contains("green")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, element + " status colour dipslays as: green");
					System.out.println(element + " status colour dipslays as: green");
				}
			}
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		}
		
		
		
	}




	public void verifyViewDevicepage_Links_PE(String application) throws InterruptedException, DocumentException {
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
			try {
				scrollToTop();
		waitforPagetobeenable();
		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		compareText(application, "Edit", "viewdevice_Edit", "Edit", xml);
		compareText(application, "Delete", "viewdevice_delete", "Delete", xml);
		compareText(application, "Fetch Interface", "viewdevice_fetchinterfacelink", "Fetch Device Interfaces", xml);

		//Edit in view device page
		click_commonMethod(application, "Edit", "viewdevice_Edit", xml);
		
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/editdeviceheader")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated to 'Edit PE Device' page successfully");
			scrolltoend();
			
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			
			//ScrolltoElement(application, "portalaccess_header", xml);
			scrollToTop();
		}
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View PE Device page");
			System.out.println(  e+ " : Field is not displayed in View PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View PE Device page");
			System.out.println(  e+" : Field is not displayed in View PE Device page");
		}

		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page not navigated");
		}
			}
	
	
	 public boolean fetchDeviceInterface_viewdevicepage(String application) throws InterruptedException, DocumentException {
			
		 boolean clickLink=false;
		 String actualMessage=null;
		 
		 waitforPagetobeenable();
		 scrollToTop();
		 
		 
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewdevice_Actiondropdown")));
			
			
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewdevice_fetchinterfacelink")));
			
			
			
		//verify success Message
			String expectedValue="Fetch interfaces started successfully. Please check the sync status of this device";
			boolean successMessage=false;
		try {	
			successMessage=getwebelement(xml.getlocator("//locators/" + application + "/fetchDeviceInterace_SuccessMessage")).isDisplayed();
			actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/fetchdeviceInterface_successtextMessage")).getText();
			if(successMessage) {
				
				if(actualMessage.contains(expectedValue)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaying");
				System.out.println(" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
				
				ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
				System.out.println(" Success Message displays as: "+actualMessage);
				
				//click on the 'click here' link
//				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ClickhereLink_fetchInterface")));
//				
				
				clickLink=true;
				
				}
				else if (actualMessage.equalsIgnoreCase(expectedValue)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
					System.out.println(" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
					System.out.println(" Success Message displays as: "+actualMessage);
					
					//click on the 'click here' link
//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ClickhereLink_fetchInterface")));
//					
					
					clickLink=true;
				}
				else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Can't start the fetch operation because another process is already fetching the interfaces for this device.");
					System.out.println("Can't start the fetch operation because another process is already fetching the interfaces for this device.");
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Can't start the fetch operation because another process is already fetching the interfaces for this device.");
			System.out.println("Can't start the fetch operation because another process is already fetching the interfaces for this device.");
			
		}
			return clickLink;
	 }

	
	public static String InterfaceAddress;
	
public void verifyFetchDeviceInterface_PE(String application, String devicename, String Inservice_status, String Inmaintenance_status, String vendormodel, 
		String managementaddress, String snmpro, String country, String interfacename) throws Exception {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Fetch Device Functionality");
	scrollToTop();
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
		System.out.println("'View PE Device' page navigated as expected");
	
		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		click_commonMethod(application, "Fetch Interface", "viewdevice_fetchinterfacelink", xml);
		
		
		try {
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_FetchDeviceInterfacesSuccessMessage")).isDisplayed()){

			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
			System.out.println("Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
		//click_commonMethod(application, "here", "herelink_fetchinterface", xml);/Not working
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" +application+ "/herelink_fetchinterface")));
				

	//Manage Network
	compareText(application, "Manage Network header", "managenetwork_header", "Manage COLT's Network - Manage Network", xml);
	compareText(application, "Status header", "status_header", "Status", xml);
	compareText(application, "Synchronization header", "synchronization_header", "Synchronization", xml);

	//verify column headers
	compareText(application, "Device column header", "status_devicecolumn", "Device", xml);
	compareText(application, "Status column header", "status_statuscloumn", "Status", xml);
	compareText(application, "Last Modification column header", "status_lastmodificationcolumn", "Last Modification", xml);
	compareText(application, "Action column header", "status_Action", "Action", xml);
	compareText(application, "Device column header", "synchronization_devicecolumn", "Device", xml);
	compareText(application, "Sync Status column header", "synchronization_syncstatuscolumn", "Sync Status", xml);
	compareText(application, "Smarts column header", "synchronization_smartscolumn", "Smarts", xml);
	compareText(application, "Fetch Interfaces column header", "synchronization_FetchInterfacescolumn", "Fetch Interfaces", xml);
	compareText(application, "VistaMart Device column header", "synchronization_vistamartdevice", "VistaMart Device", xml);
	compareText(application, "Action column header", "synchronization_actioncolumn", "Action", xml);

		
	//verify Status panel column values
	compareText(application, "Device", "status_devicevalue", devicename, xml);
	String ServiceStatus= GetText(application, "Status", "status_statusvalue");
	if(ServiceStatus.equalsIgnoreCase(Inservice_status))
	{
		compareText(application, "Status", "status_statusvalue", Inservice_status, xml);
	}
	else
	{
		compareText(application, "Status", "status_statusvalue", Inmaintenance_status, xml);
	}

	//verify last modification
	try {
		String GMTValue;
		String LastModificationvalue= GetText(application, "Last Modification", "status_lastmodificationvalue");
		System.out.println("Last modified date: " +LastModificationvalue);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");

		if (LastModificationvalue.length() > 3) 
		{
			GMTValue = LastModificationvalue.substring(LastModificationvalue.length() - 3);
		} 
		else
		{
			GMTValue = LastModificationvalue;
		}
		sa.assertEquals(GMTValue, "GMT");

	}catch(Exception e)
	{
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Last Modification column value field is not displaying");
	}

	click_commonMethod(application, "Status", "status_statuslink", xml);
	
	compareText(application, "Status page header", "Statuspage_header", "Device", xml);

	//verify field headers in status page
	compareText(application, "Name field header", "statuspage_nameheader", "Name", xml);
	compareText(application, "Vendor/Model field header", "statuspage_vendormodelheader", "Vendor/Model", xml);
	compareText(application, "Management Address field header", "statuspage_managementaddressheader", "Management Address", xml);
	compareText(application, "Country field header", "statuspage_countryheader", "Country", xml);
	compareText(application, "City field header", "statuspage_cityheader", "City", xml);
	compareText(application, "Site field header", "statuspage_siteheader", "Site", xml);
	compareText(application, "Premise field header", "statuspage_premiseheader", "Premise", xml);

	//verify field values in status page
	compareText(application, "Name", "statuspage_namevalue", devicename, xml);
	compareText(application, "Vendor/Model", "statuspage_vendormodelvalue", vendormodel, xml);
	compareText(application, "Management Address", "statuspage_managementaddressvalue", managementaddress, xml);
	compareText(application, "Snmpro", "statuspage_snmprovalue", snmpro, xml);
	compareText(application, "Country", "statuspage_countryvalue", country, xml);
	GetText(application, "City", "statuspage_cityvalue");
	GetText(application, "Site", "statuspage_sitevalue");
	GetText(application, "Premise", "statuspage_premisevalue");

	
	
	compareText(application, "Status header", "Statuspage_statusheader", "Status", xml);
	compareText(application, "Current Status field header", "statuspage_currentstatusfieldheader", "Current Status", xml);
	compareText(application, "New Status field header", "statuspage_newstatusfieldheader", "New Status", xml);
	compareText(application, "Current Status", "statuspage_currentstatusvalue", Inservice_status, xml);
	click_commonMethod(application, "New Status Dropdown", "statuspage_newstatusdropdown", xml);
	WebElement selectNewStatusvalue= getwebelement(xml.getlocator("//locators/" + application + "/statuspage_newstatusdropdownvalue"));
	Clickon(selectNewStatusvalue);
	String NewStatusvalue= getwebelement(xml.getlocator("//locators/" + application + "/statuspage_newstatusdropdownvalue")).getText();
	ExtentTestManager.getTest().log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue);
	click_commonMethod(application, "OK", "statuspage_okbutton", xml);
	
	scrollToTop();
//	compareText(application, "Device status update success message", "Sync_successmsg", "Device Status history successfully changed", xml);
	verifysuccessmessage(application, "Device Status history successfully changed");
	scrolltoend();
	//verify 'new status' table column headers
	compareText(application, "Status column header", "statuspage_statuscolumnheader", "Status", xml);
	compareText(application, "Changed On column header", "statuspage_changedon_columnheader", "Changed On", xml);
	compareText(application, "Changed By column header", "statuspage_changedby_columnheader", "Changed By", xml);

	//verify 'new status' table column values
	//Device status history table
	int TotalPages;
	String TotalPagesText = getwebelement("//div[@class='ag-div-margin row']//div//span[@ref='lbTotal']").getText();
	TotalPages = Integer.parseInt(TotalPagesText);
	System.out.println("Total number of pages in table is: " + TotalPages);

	if (TotalPages != 0) {

		outerloop:
			for (int k = 1; k <= TotalPages; k++) {

				String AddedInterface= getwebelement("//div[@role='grid']//div[@ref='eBodyViewport']/div").getAttribute("style");
				if(!AddedInterface.contains("height: 1px"))
				{
					List<WebElement> results = getwebelements("//div[@class='modal-content']//div[@class='ag-div-margin row']//div[@ref='eBodyContainer']//div[@role='row']");
					int numofrows = results.size();
					System.out.println("no of results: " + numofrows);

					if ((numofrows == 0)) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "Device Status History is empty");
					}
					else {
						// Current page
						String CurrentPage = getwebelement("//div[@class='ag-div-margin row']//div//span[@ref='lbCurrent']").getText();
						int Current_page = Integer.parseInt(CurrentPage);
						System.out.println("The current page is: " + Current_page);

						sa.assertEquals(k, Current_page);

						System.out.println("Currently we are in page number: " + Current_page);
						for (int i = 0; i < numofrows; i++) {
							try {

								String Devicehistorydata = results.get(i).getText();
								System.out.println(Devicehistorydata);
								if (Devicehistorydata.contains(NewStatusvalue)) 
								{
									ExtentTestManager.getTest().log(LogStatus.PASS, "Device status history table has data");
									
									compareText(application, "New Status", "statuspage_newstatusvalue", NewStatusvalue, xml);
									try {
										String GMTValue;
										String ChangedOnvalue= GetText(application, "Changed On", "statuspage_changedonvalue");
										System.out.println("Changed On value: " +ChangedOnvalue);
										SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");

										if (ChangedOnvalue.length() > 3) 
										{
											GMTValue = ChangedOnvalue.substring(ChangedOnvalue.length() - 3);
										} 
										else
										{
											GMTValue = ChangedOnvalue;
										}
										assertEquals(GMTValue, "GMT");

									}catch(Exception e)
									{
										e.printStackTrace();
										ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Changed on column value field is not displaying");
									}

									compareText(application, "Changed By", "statuspage_changedbyvalue", Getkeyvalue("APT_NonVoiceService_Username"), xml);
									
									click_commonMethod(application, "Close", "statuspage_closebutton", xml);
									break outerloop;
								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}
						}
					}
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No interfaces added");

				}
			}

	}else {

		System.out.println("No data available in table");
		Log.info("No data available in table");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No data available in table");
	}

	//verify view interfaces page
	
	click_commonMethod(application, "View Interfaces", "status_viewinterfaceslink", xml);
	
	compareText(application, "Interface page header", "viewinterfacepage_header", "Interfaces", xml);
	compareText(application, "Interface page subheader", "viewinterfacepage_interfacesubheader", "Interfaces", xml);
	String AddedInterface= getwebelement("//div[@role='grid']//div[@ref='eBodyViewport']/div").getAttribute("style");
	if(!AddedInterface.contains("height: 1px"))
	{
		compareText(application, "Device Name column header", "viewinterface_devicenamecolumnheader", "Device Name", xml);
		compareText(application, "Interface Name column header", "interfacename_columnheader", "Interface Name", xml);
		compareText(application, "Interface Address column header", "interfaceaddress_columnheader", "Interface Address", xml);
		WebElement InterfaceAddressRowValue= driver.findElement(By.xpath("(//div[@role='gridcell'][@col-id='address'])[1]"));
		Clickon(InterfaceAddressRowValue);
		InterfaceAddressRowValue.sendKeys(Keys.TAB);
		compareText(application, "Interface Type column header", "interfacetype_columnheader", "Interface Type", xml);
		WebElement InterfaceTypeRowValue= getwebelement(xml.getlocator("//locators/" + application + "/interfacetype_rowvalue"));
		Clickon(InterfaceTypeRowValue);
		InterfaceTypeRowValue.sendKeys(Keys.TAB);
		compareText(application, "Status column header", "viewinterface_status_columnheader", "Status", xml);
		WebElement StatusRowValue= getwebelement(xml.getlocator("//locators/" + application + "/viewinterface_status_rowvalue"));
		Clickon(StatusRowValue);
		StatusRowValue.sendKeys(Keys.TAB);
		compareText(application, "Last Modification column header", "viewinterface_lastmod_columnheader", "Last Modification", xml);

		
		click_commonMethod(application, "Close", "viewinterface_closebutton", xml);
		
		click_commonMethod(application, "View Interfaces", "status_viewinterfaceslink", xml);
		

		int TotalPages1;
		String TotalPagesText1 = getwebelement("//div[@class='ag-div-margin row']//div//span[@ref='lbTotal']").getText();
		TotalPages1 = Integer.parseInt(TotalPagesText1);
		System.out.println("Total number of pages in table is: " + TotalPages1);

		if (TotalPages1 != 0) {

			outerloop:
				for (int k = 1; k <= TotalPages1; k++) {

					List<WebElement> results = getwebelements("//div[@role='row']//div[@role='gridcell'][@col-id='name']");

					int numofrows = results.size();
					System.out.println("no of results: " + numofrows);

					if ((numofrows == 0)) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "Interface table is empty");
					}
					else {
						// Current page
						String CurrentPage = getwebelement("//div[@class='ag-div-margin row']//div//span[@ref='lbCurrent']").getText();
						int Current_page = Integer.parseInt(CurrentPage);
						System.out.println("The current page is: " + Current_page);

						sa.assertEquals(k, Current_page);

						System.out.println("Currently we are in page number: " + Current_page);
						for (int i = 0; i < numofrows; i++) {
							try {

								String AddedInterfacedata = results.get(i).getText();
								System.out.println(AddedInterfacedata);
								if (AddedInterfacedata.equalsIgnoreCase(interfacename)) {

									String InterfaceNameRowID= getwebelement("//div[@role='gridcell'][text()='"+interfacename+"']/parent::div[@role='row']").getAttribute("row-id");
									System.out.println("Interface row id: "+InterfaceNameRowID);

									//verify interface values in table
									String DeviceNamevalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='deviceName']").getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Interface Device Name value is displayed as : "+DeviceNamevalue);
									String InterfaceNamevalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='name']").getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Interface Name value is displayed as : "+InterfaceNamevalue);
									String InterfaceAddressvalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='address']").getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Interface Address value is displayed as : "+InterfaceAddressvalue);
									WebElement InterfaceAddressRowValue1= driver.findElement(By.xpath("(//div[@role='gridcell'][@col-id='address'])[1]"));
									Clickon(InterfaceAddressRowValue1);
									InterfaceAddressRowValue1.sendKeys(Keys.TAB);
									String InterfaceTypevalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='type.desc']").getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Interface Type value is displayed as : "+InterfaceTypevalue);
									WebElement InterfaceTypeRowValue1= getwebelement(xml.getlocator("//locators/" + application + "/interfacetype_rowvalue"));
									Clickon(InterfaceTypeRowValue1);
									InterfaceTypeRowValue1.sendKeys(Keys.TAB);
									String Statusvalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='currentStatus.desc']").getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Status value is displayed as : "+Statusvalue);
									WebElement StatusRowValue1= getwebelement(xml.getlocator("//locators/" + application + "/viewinterface_status_rowvalue"));
									Clickon(StatusRowValue1);
									StatusRowValue1.sendKeys(Keys.TAB);
									String LastModificationvalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='m_time']").getText();
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Last Modification value is displayed as : "+LastModificationvalue);
									WebElement LastModificationRowValue= getwebelement(xml.getlocator("//locators/" + application + "/viewinterface_lastmod_rowvalue"));
									Clickon(LastModificationRowValue);
									LastModificationRowValue.sendKeys(Keys.TAB);
									WebElement StatusLink= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='Status']/div/a");
									Clickon(StatusLink);
									ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Clicked on Status link in interface table");

									InterfaceAddress= InterfaceAddressvalue;
									break outerloop;
								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}
						}
						Clickon(getwebelement("//div[@class='ag-div-margin row']//div//button[text()='Next']"));
						
					}
				}

		//verify status page headers & field names
		compareText(application, "Interface header", "statuspage_interfaceheader", "Interface", xml);
		compareText(application, "Name field header", "interface_statuspage_namefield", "Name", xml);
		compareText(application, "Interface Address field header", "interface_statuspage_interfaceaddressfield", "Interface Address", xml);
		compareText(application, "Status header", "interface_statuspage_statusheader", "Status", xml);
		compareText(application, "Current Status field header", "interface_statuspage_currentstatusfield", "Current Status", xml);
		compareText(application, "New Status field header", "interface_statuspage_newstatusfield", "New Status", xml);

		//verify status page field values
		compareText(application, "Name", "interface_statuspage_namevalue", interfacename, xml);
		compareText(application, "Interface Address", "interface_statuspage_interfaceaddressvalue", InterfaceAddress, xml);
		compareText(application, "Current Status", "interface_statuspage_currentstatusvalue", Inservice_status, xml);
		click_commonMethod(application, "New Status Dropdown", "interface_statuspage_newstatusdropdown", xml);
		WebElement selectNewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue"));
		Clickon(selectNewStatusvalue1);
		String NewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue1);
		click_commonMethod(application, "OK", "interface_statuspage_okbutton", xml);
		
		scrollToTop();
//		compareText(application, "Interface status update success message", "Sync_successmsg", "Interface Status History successfully changed.", xml);
		verifysuccessmessage(application, "Interface Status History successfully changed.");
		scrolltoend();
		
		//verify 'new status' table column headers
		compareText(application, "Status column header", "interface_statuspage_statuscolumnheader", "Status", xml);
		compareText(application, "Changed On column header", "interface_statuspage_changedon_columnheader", "Changed On", xml);
		compareText(application, "Changed By column header", "interface_statuspage_changedby_columnheader", "Changed By", xml);

		//verify 'new status' table column values
		//verify interface status history table
		int TotalPages2=0;
		String TotalPagesText2 = getwebelement("(//div[@class='ag-div-margin row']//div//span[@ref='lbTotal'])[1]").getText();
		TotalPages2 = Integer.parseInt(TotalPagesText2);
		System.out.println("Total number of pages in table is: " + TotalPages2);

		if (TotalPages2 != 0) {

			outerloop:
				for (int k = 1; k <= TotalPages2; k++) {

					List<WebElement> results = getwebelements("(//div[@ref='eBodyContainer'])[2]//div[@role='row']");

					int numofrows = results.size();
					System.out.println("no of results: " + numofrows);

					if ((numofrows == 0)) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Status History is empty");
					}
					else {
						// Current page
						String CurrentPage = getwebelement("(//div[@class='ag-div-margin row']//div//span[@ref='lbCurrent'])[1]").getText();
						int Current_page = Integer.parseInt(CurrentPage);
						System.out.println("The current page is: " + Current_page);

						sa.assertEquals(k, Current_page);

						System.out.println("Currently we are in page number: " + Current_page);
						for (int i = 0; i < numofrows; i++) {
							try {
								String Interfacehistorydata = results.get(i).getText();
								System.out.println(Interfacehistorydata);
								if (Interfacehistorydata.contains(NewStatusvalue1)) 
								{
									ExtentTestManager.getTest().log(LogStatus.PASS, "Interface status history table has data");
									
									compareText(application, "New Status", "interface_statuspage_newstatusvalue", NewStatusvalue1, xml);
									try {
										String GMTValue;
										String ChangedOnvalue= GetText(application, "Changed On", "interface_statuspage_changedonvalue");
										System.out.println("Changed On value: " +ChangedOnvalue);
										SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");

										if (ChangedOnvalue.length() > 3)
										{
											GMTValue = ChangedOnvalue.substring(ChangedOnvalue.length() - 3);
										} 
										else
										{
											GMTValue = ChangedOnvalue;
										}
										sa.assertEquals(GMTValue, "GMT");

									}catch(Exception e)
									{
										e.printStackTrace();
										ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Changed On column value field is not displaying");
									}

									compareText(application, "Changed By", "interface_statuspage_changedbyvalue", Getkeyvalue("APT_login_1_Username"), xml);
									
									click_commonMethod(application, "Close", "interface_statuspage_closebutton", xml);
									break outerloop;
								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}
						}
					}
				}

		}else {

			System.out.println("No data available in status history table");
			Log.info("No data available in status history table");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No data available in status history table");
		}
		}else {

			System.out.println("No data available in Interface table");
			Log.info("No data available in Interface table");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No data available in Interface table");
		}
	}else
	{
		ExtentTestManager.getTest().log(LogStatus.INFO, "No Interface added in table");
	}

	click_commonMethod(application, "Close", "viewinterface_closebutton", xml);
	

	//verify synchronization panel column values
	
	scrolltoend();
	compareText(application, "Device", "synchronization_devicevalue", devicename, xml);
	GetText(application, "Sync Status", "synchronization_syncstatusvalue");


	//verify smarts value
	GetText(application, "Smarts", "synchronization_smartsvalue");
	//verify smarts date time 
	try {
		String GMTValue;
		String Smartsvalue= getwebelement(xml.getlocator("//locators/" + application + "/smarts_datetimevalue")).getText();
		String SmartsDateTimevalue= "";
		if (Smartsvalue.length() > 20) 
		{
			SmartsDateTimevalue = Smartsvalue.substring(Smartsvalue.length() - 20);
			System.out.println("last 20 characters:"+SmartsDateTimevalue);
		} 
		else 
		{
			SmartsDateTimevalue = Smartsvalue;
		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Smarts date value is displayed as: "+SmartsDateTimevalue);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");
		if (SmartsDateTimevalue.length() > 3) 
		{
			GMTValue = SmartsDateTimevalue.substring(SmartsDateTimevalue.length() - 3);
		} 
		else
		{
			GMTValue = SmartsDateTimevalue;
		}
		assertEquals(GMTValue, "GMT");

	}catch(Exception e)
	{
		e.printStackTrace();
	}

	//verify fetch interfaces value
	GetText(application, "Fetch Interfaces", "synchronization_fetchinterfacesvalue");
	//verify fetch interfaces date time
	try {
		String GMTValue;
		String FetchInterfacesvalue= getwebelement(xml.getlocator("//locators/" + application + "/fetchinterfaces_datetime")).getText();
		String FetchInterfaces_DateTimevalue= "";
		if (FetchInterfacesvalue.length() > 20) 
		{
			FetchInterfaces_DateTimevalue = FetchInterfacesvalue.substring(FetchInterfacesvalue.length() - 20);
			System.out.println("last 20 characters:"+FetchInterfaces_DateTimevalue);
		} 
		else 
		{
			FetchInterfaces_DateTimevalue = FetchInterfacesvalue;
		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Fetch Interfaces date value is displayed as: "+FetchInterfaces_DateTimevalue);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");
		if (FetchInterfaces_DateTimevalue.length() > 3) 
		{
			GMTValue = FetchInterfaces_DateTimevalue.substring(FetchInterfaces_DateTimevalue.length() - 3);
		} 
		else
		{
			GMTValue = FetchInterfaces_DateTimevalue;
		}
		assertEquals(GMTValue, "GMT");

	}catch(Exception e)
	{
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Fetch Interfaces date value field is not displaying");
	}

	//verify vistamart device value
	GetText(application, "VistaMart Device", "synchronization_vistamartdevicevalue");
	//verify vistamart device date time
	try {
		String GMTValue;
		String VistaMartDevicevalue= getwebelement(xml.getlocator("//locators/" + application + "/vistamartdevice_datetime")).getText();
		String VistaMartDevice_DateTimevalue= "";
		if (VistaMartDevicevalue.length() > 20) 
		{
			VistaMartDevice_DateTimevalue = VistaMartDevicevalue.substring(VistaMartDevicevalue.length() - 20);
			System.out.println("last 20 characters:"+VistaMartDevice_DateTimevalue);
		} 
		else 
		{
			VistaMartDevice_DateTimevalue = VistaMartDevicevalue;
		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Vistamart Device date value is displayed as: "+VistaMartDevice_DateTimevalue);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");
		if (VistaMartDevice_DateTimevalue.length() > 3) 
		{
			GMTValue = VistaMartDevice_DateTimevalue.substring(VistaMartDevice_DateTimevalue.length() - 3);
		} 
		else
		{
			GMTValue = VistaMartDevice_DateTimevalue;
		}
		assertEquals(GMTValue, "GMT");

	}catch(Exception e)
	{
		e.printStackTrace();
	}

	//verify synchronize link
	
	click_commonMethod(application, "Synchronize", "synchronization_synchronizelink", xml);
	
	scrollToTop();
	
	compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this device.", xml);
	

		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			System.out.println("Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.INFO, "Message displays as: 'Message not found for Message ID : Can't start the fetch operation because another process is already fetching the interfaces for this device.'");
			System.out.println( "Message displays as: 'Message not found for Message ID : Can't start the fetch operation because another process is already fetching the interfaces for this device.'");
		}
		
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
		System.out.println("'View PE Device' page navigated");
	}
		
}



	
	
	
	public void verifyEditDevice_PE(String application, String editDevicename, String editVendorModel, String editTelnet, String editSSH, String editSnmp2C, String editSnmp3,
			String editSnmProNewValue, String editSnmprwNewValue, String editSnmpv3UsernameNewValue, String editSnmpv3AuthpasswordNewValue, String editSnmpv3PrivpasswordNewValue,
			String editManagementAddress, String editCountry, String editExistingCity, String editExistingCityValue, String editExistingSite, String editExistingSiteValue,
			String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, String editNewPremise, String editNewCityName,
			String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edit PE Device Functionality");
		try {
			waitforPagetobeenable();
			ScrolltoElement(application, "portalaccess_header", xml);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
			{
				click_commonMethod(application, "Edit", "viewservicepage_editdevicelink", xml);
				waitforPagetobeenable();
				scrollToTop();
				compareText(application, "Edit PE Device header", "editdeviceheader", "Edit PE Device", xml);

				//edit device fields

				//name
				addtextFields_commonMethod(application, "Name", "nametextfield", editDevicename, xml);

				//vendormodel
				addDropdownValues_commonMethod(application, "Vendor/Model", "vendormodelinput", editVendorModel, xml);

				//Management address
				javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
				addtextFields_commonMethod(application, "Management Address", "managementaddresstextbox", editManagementAddress, xml);



				//select country
				scrolltoend();
				
				addDropdownValues_commonMethod(application, "Country", "countryinput", editCountry, xml);

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
					
				   addDropdownValues_commonMethod(application, "City", "citydropdowninput", editExistingCityValue, xml);
					
					
				 //Existing Site
					  if(editExistingSite.equalsIgnoreCase("yes") & editNewSite.equalsIgnoreCase("no")) {
						  addDropdownValues_commonMethod(application, "Site", "sitedropdowninput", editExistingSiteValue, xml);
						  
					 //Existing Premise
						  if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("no")) {
							  addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", editExistingPremiseValue, xml);
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

			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
		} catch (StaleElementReferenceException e) {

			e.printStackTrace();
		}

		scrolltoend();
		
		click_commonMethod(application, "OK", "okbutton", xml);
		
		compareText(application, "Edit Device success msg", "successmsg", "Device successfully updated.", xml);
		

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
				System.out.println("No changes made under 'Premise' field");
				
			}
	    	
	    }

	 public void existingPremise(String application, String dropdown_xpath, String dropdownValue, String selectPremiseToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean premiseDisplayed=false;
	    try {	
	    	premiseDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(premiseDisplayed) {
	    		
	    		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	    		
	    	}else {
	    		
	    		click_commonMethod(application, "Select Premise toggle button", selectPremiseToggleButton, xml);
	    		
				
	    		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	    		
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    	click_commonMethod(application, "Select Premise toggle button", selectPremiseToggleButton, xml);
			
	    	addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	    }
	    	
	    }
	    
	    
	 public void newPremise(String application, String dropdown_xpath, String addPremisetoggleButton, String editNewPremiseName,
	    		String editNewPremiseCode, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean premiseDisplayed=false;
	    try {	
	    	premiseDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(premiseDisplayed) {
	    		
	    		click_commonMethod(application, "Select Premise toggle button", addPremisetoggleButton, xml);
	    		
				
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
				System.out.println("No changes made under 'Site' field");
				
			}
	    	
	    }
	    
	 public void existingSite(String application, String dropdown_xpath, String dropdownValue, String selectSiteToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean siteDisplayed=false;
	    try {	
	    	siteDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(siteDisplayed) {
	    		
	    		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	    		
	    	}else {
	    		
	    		click_commonMethod(application, "Select Site toggle button", selectSiteToggleButton, xml);
	    		
				
	    		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	    		
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    	click_commonMethod(application, "Select Site toggle button", selectSiteToggleButton, xml);
			
	    	addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	    }
	    	
	    }
	    
	    
	 public void newSite(String application, String dropdown_xpath, String addSitetoggleButton, String editNewSiteName,
	    		String editNewSiteCode, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean cityDisplayed=false;
	    try {	
	    	cityDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(cityDisplayed) {
	    		
	    		click_commonMethod(application, "Select City toggle button", addSitetoggleButton, xml);
	    		
				
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
				System.out.println("No chnges made under 'City' field");
			}
	    	
	    }
	    
	    
	    public void existingCity(String application, String dropdown_xpath, String dropdownValue, String selectCityToggleButton, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean cityDisplayed=false;
	    try {	
	    	cityDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(cityDisplayed) {
	    		
	    		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	    		
	    	}else {
	    		
	    		click_commonMethod(application, "Select City toggle button", selectCityToggleButton, xml);
	    		
				
	    		addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	    		
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    	click_commonMethod(application, "Select City toggle button", selectCityToggleButton, xml);
			
	    	addDropdownValues_commonMethod(application, labelname, dropdown_xpath, dropdownValue, xml);
	    }
	    	
	    }
	    
	    
	    public void newCity(String application, String dropdown_xpath, String addCitytoggleButton, String editNewCityName,
	    		String editNewCityCode, String labelname) throws InterruptedException, DocumentException, IOException {
	    	
	    	boolean cityDisplayed=false;
	    try {	
	    	cityDisplayed=getwebelement(xml.getlocator("//locators/" + application + "/"+ dropdown_xpath +"")).isDisplayed();
	    	
	    	if(cityDisplayed) {
	    		
	    		click_commonMethod(application, "Select City toggle button", addCitytoggleButton, xml);
	    		
				
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

	    
	public void verifyViewpage_UpdatedDevicedetails_PE(String application, String editDevicename, String editVendorModel, String editTelnet, String editSSH,
			String editSnmp2C, String editSnmp3,String editSnmProNewValue, String editSnmprwNewValue, String editSnmpv3UsernameNewValue,
			String editSnmpv3AuthpasswordNewValue, String editSnmpv3PrivpasswordNewValue,String editManagementAddress, String editCountry, String editExistingCity,
			String editExistingCityValue, String editExistingSite, String editExistingSiteValue,String editExistingPremise, String editExistingPremiseValue,
			String editNewCity, String editNewSite, String editNewPremise, String editNewCityName, String editNewCityCode, String editNewSiteName, String editNewSiteCode,
			String editNewPremiseName, String editNewPremiseCode, String name,String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
			String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String Snmpv3Authpassword, String Snmpv3Privpassword, 
			String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,	String Snmpv3Privpasswordvalue, String Country, String managementaddress, String existingcity, 
			String existingcityvalue, String existingsite,String existingsitevalue, String existingpremise, String existingpremisevalue, String cityname, String Citycode,
			String sitename, String sitecode,  String premisename,  String premisecode, String newcity, String newsite, String NewPremise
			) throws InterruptedException, IOException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edited PE Device Information");
		waitforPagetobeenable();
		scrollToTop();
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
		try {
		//Device name
		if(editDevicename.equalsIgnoreCase("Null")) {
			compareText(application, "Name", "viewpage_devicename", name, xml);
		}else {
			compareText(application, "Name", "viewpage_devicename", editDevicename, xml);
		}

		//Vendor/Model
		if(editVendorModel.equalsIgnoreCase("Null")) {
			compareText(application, "Vendor/Model", "viewpage_vendormodel", vendormodel, xml);
		}else {
			compareText(application, "Vendor/Model", "viewpage_vendormodel", editVendorModel, xml);
		}

		//Connectivity protocol
		if((editSSH.equalsIgnoreCase("null")) || (editSSH.equalsIgnoreCase("no"))) {
			GetText(application, "Connectivity Protocol", "viewpage_connectivityprotocol");
		}
		else if((editTelnet.equalsIgnoreCase("null")) || (editTelnet.equalsIgnoreCase("no"))){
			GetText(application, "Connectivity Protocol", "viewpage_connectivityprotocol");
		}
		else if(editSSH.equalsIgnoreCase("yes") && editTelnet.equalsIgnoreCase("no")) {

			GetText(application, "Connectivity Protocol", "viewpage_connectivityprotocol");
		}
		else if(editSSH.equalsIgnoreCase("no") && editTelnet.equalsIgnoreCase("yes")) {

			GetText(application, "Connectivity Protocol", "viewpage_connectivityprotocol");
		}

		//Management Address
		if(editManagementAddress.equalsIgnoreCase("Null")) {
			compareText(application, "Management Address", "viewpage_managementaddress", managementaddress, xml);
		}else {
			compareText(application, "Management Address", "viewpage_managementaddress", editManagementAddress, xml);
		}

		//SNMP Version
		if(editSnmp2C.equalsIgnoreCase("Yes") && editSnmp3.equalsIgnoreCase("No")) {
			GetText(application, "SNMP Version", "viewpage_snmpversion");
			compareText(application, "Snmpro", "viewpage_snmpro", "incc", xml);
			compareText(application, "Snmprw", "viewpage_snmprw", "ip4corp3", xml);

		}
		else if(editSnmp2C.equalsIgnoreCase("Yes") && editSnmp3.equalsIgnoreCase("null")) {

			GetText(application, "SNMP Version", "viewpage_snmpversion");
			compareText(application, "Snmpro", "viewpage_snmpro", "incc", xml);
			compareText(application, "Snmprw", "viewpage_snmprw", "ip4corp3", xml);
			
		}else if(editSnmp2C.equalsIgnoreCase("no") && editSnmp3.equalsIgnoreCase("yes")) {
			GetText(application, "SNMP Version", "viewpage_snmpversion");
		}else if(editSnmp2C.equalsIgnoreCase("null") && editSnmp3.equalsIgnoreCase("yes")) {
			GetText(application, "SNMP Version", "viewpage_snmpversion");
			compareText(application, "Snmp v3 Username", "viewpage_snmpv3username", "colt-nms", xml);
			compareText(application, "Snmp v3 Auth password", "viewpage_snmpv3authpassword", "OrHzjWmRvr4piJZb", xml);
			compareText(application, "Snmp v3 Priv password", "viewpage_snmpv3privpassword", "3k0hw8thNxHucQkE", xml);
		
		}else if((editSnmp2C.equalsIgnoreCase("null")) || (editSnmp2C.equalsIgnoreCase("no"))) {
			GetText(application, "SNMP Version", "viewpage_snmpversion");
		}
		else if((editSnmp3.equalsIgnoreCase("null")) || (editSnmp3.equalsIgnoreCase("no"))) {
			GetText(application, "SNMP Version", "viewpage_snmpversion");
		}
		//Country
		if(editCountry.equalsIgnoreCase("Null")) {
			compareText(application, "Country", "viewpage_country", Country, xml);
		}else {
			compareText(application, "Country", "viewpage_country", editCountry, xml);
		}
		

		//City
		if((editExistingCity.equalsIgnoreCase("Null") && editNewCity.equalsIgnoreCase("Null")) || (editExistingCity.equalsIgnoreCase("no") && editNewCity.equalsIgnoreCase("no"))) {
			compareText(application, "City", "viewpage_city", newcity, xml);
			
		}else if(editExistingCity.equalsIgnoreCase("yes") && editNewCity.equalsIgnoreCase("no")) {
			compareText(application, "City", "viewpage_city", editExistingCityValue, xml);
		}else if(editExistingCity.equalsIgnoreCase("no") && editNewCity.equalsIgnoreCase("yes")) {
			compareText(application, "City", "viewpage_city", editNewCityName, xml);
		}

		
		//Site
		if((editExistingSite.equalsIgnoreCase("Null") && editNewSite.equalsIgnoreCase("Null")) || (editExistingSite.equalsIgnoreCase("no") && editNewSite.equalsIgnoreCase("no"))) {

			compareText(application, "Site", "viewpage_site", newsite, xml);
		}
		else if(editExistingSite.equalsIgnoreCase("yes") && editNewSite.equalsIgnoreCase("no")) {
			compareText(application, "Site", "viewpage_site", editExistingSiteValue, xml);

		}
		else if(editExistingSite.equalsIgnoreCase("no") && editNewSite.equalsIgnoreCase("yes")) {
			compareText(application, "Site", "viewpage_site", editNewSiteName, xml);
		}


		//Premise
		if((editExistingPremise.equalsIgnoreCase("Null") && editNewPremise.equalsIgnoreCase("Null")) || (editExistingPremise.equalsIgnoreCase("no") && editNewPremise.equalsIgnoreCase("no"))) {

			compareText(application, "Premise", "viewpage_premise", NewPremise, xml);
		}
		else if(editExistingPremise.equalsIgnoreCase("yes") && editNewPremise.equalsIgnoreCase("no")) {
			compareText(application, "Premise", "viewpage_premise", editExistingPremiseValue, xml);

		}
		else if(editExistingPremise.equalsIgnoreCase("no") && editNewPremise.equalsIgnoreCase("yes")) {
			compareText(application, "Premise", "viewpage_premise", editNewPremiseName, xml);
		}

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Device page");
			System.out.println(  e+ " : Field is not displayed in Edit Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Edit Device page ");
			System.out.println(  e+" : Field is not displayed in Edit Device page");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page not navigated");
		}
	}


	public void addExistingPEDevice_PE(String application, String existingdevicename) throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Existing PE Device addition");
		ScrolltoElement(application, "portalaccess_header", xml);
		
		compareText(application, "Provider Equipment (PE)", "providerequipment_header", "Provider Equipment (PE)", xml);
		click_commonMethod(application, "Add PE Device", "addpedevice_link", xml);
		waitforPagetobeenable();
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/addpedevice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add PE Device' page navigated as expected");
			System.out.println("'Add PE Device' page navigated as expected");
			
			try {
//		addDropdownValues_commonMethod(application, "Type PE name to filter", "typepename_dropdown", existingdevicename, xml);
//		SelectDropdownValueUnderSpanTag(application, "Type PE name to filter", existingdevicename, "typepename_dropdown", "commonDropdownValueSpanTag", xml);
		addDropdownValues_commonMethod3(application, "Type PE name to filter", "typepename_dropdown", existingdevicename, xml);
		
		
		//Verify existing device values
		GetText(application, "Vendor/Model", "existingdevice_vendormodelvalue");
		GetText(application, "Management Address", "existingdevice_managementaddressvalue");
		GetText(application, "Connectivity Protocal", "existingdevice_connectivityprotocol");
		String SNMPVersionValue= getwebelement(xml.getlocator("//locators/" + application + "/existingdevice_snmpversion")).getText();
		if(SNMPVersionValue.equalsIgnoreCase("2c"))
		{
			GetText(application, "Snmpro", "existingdevice_snmpro");
			GetText(application, "Snmprw", "existingdevice_snmprw");
		}
		else if(SNMPVersionValue.equalsIgnoreCase("3"))
		{
			GetText(application, "Snmp v3 Username", "existingdevice_snmpv3username");
			GetText(application, "Snmp v3 Auth password", "existingdevice_snmpv3authpassword");
			GetText(application, "Snmp v3 Priv password", "existingdevice_snmpv3privpassword");
		}
		else
		{
			GetText(application, "SNMP Version", "existingdevice_snmpversion");
		}

		GetText(application, "Country", "existingdevice_country");
		GetText(application, "City", "existingdevice_city");
		GetText(application, "Site", "existingdevice_site");
		GetText(application, "Premise", "existingdevice_premise");
		
		scrolltoend();
		
		click_commonMethod(application, "Next", "Next_Button", xml);
		
		compareText(application, "Add Device success msg", "successmsg", "Device is added to Service.", xml);
		
		
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add PE Device page");
				System.out.println(  e+ " : Field is not displayed in Add PE Device page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add PE Device page ");
				System.out.println(  e+" : Field is not displayed in Add PE Device page");
			}

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add PE Deviceh' page not navigated");
				System.out.println("'Add PE Device' page not navigated");
			}
	}

	public void verifyExistingDevice_ViewDevicedetails_PE(String application, String existingdevicename) throws InterruptedException, IOException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Existing PE Device Information");
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
			try {
					//Device Name
					GetText(application, "Name", "viewpage_devicename");

					//Vendor/model
					GetText(application, "Vendor/Model", "viewpage_vendormodel");

					//Connectivity protocol
					GetText(application, "Connectiviy Protocol", "viewpage_connectivityprotocol");

					//SNMP Version
					String SNMPVersionValue= getwebelement(xml.getlocator("//locators/" + application + "/viewpage_snmpversion")).getText();
					if(SNMPVersionValue.equalsIgnoreCase("2c"))
					{
						GetText(application, "Snmpro", "viewpage_snmpro");
						GetText(application, "Snmprw", "viewpage_snmprw");
					}
					else if(SNMPVersionValue.equalsIgnoreCase("3"))
					{
						GetText(application, "Snmp v3 Username", "viewpage_snmpv3username");
						GetText(application, "Snmp v3 Auth password", "viewpage_snmpv3authpassword");
						GetText(application, "Snmp v3 Priv password", "viewpage_snmpv3privpassword");
					}
					else
					{
						GetText(application, "SNMP Version", "viewpage_snmpversion");
					}


					//Management Address
					GetText(application, "Management Address", "viewpage_managementaddress");

					//Country
					GetText(application, "Country", "viewpage_country");

					//City
					GetText(application, "City", "viewpage_city");

					//Site
					GetText(application, "Site", "viewpage_site");

					//Premise
					GetText(application, "Premise", "viewpage_premise");

					scrolltoend();
					click_commonMethod(application, "Back", "viewpage_backbutton", xml);
					
					
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View PE Device page");
			System.out.println(  e+ " : Field is not displayed in View PE Device page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View PE Device page");
			System.out.println(  e+" : Field is not displayed in View PE Device page");
		}

		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page not navigated");
		}

	}

	public void deleteDevice(String application, String DeviceName) throws InterruptedException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Delete Service Functionality");
		ScrolltoElement(application, "portalaccess_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= driver.findElements(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				if(AddedDeviceNameText.contains(DeviceName))
				{
					WebElement AddedDevice_DeletefromserviceLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='Delete from Service']"));
					Clickon(AddedDevice_DeletefromserviceLink);								   
					
					
//					WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
//					if(DeleteAlertPopup.isDisplayed())
//					{
//						click_commonMethod(application, "Delete", "deletebutton", xml);
//					}else{
//						Log.info("Delete alert popup is not displayed");
//						ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
//					}
					
					//**CancelJavaScriptMethod();
					AcceptJavaScriptMethod();
//					compareText(application, "Device delete success message", "successmsg", "Device successfully removed from service.", xml);
					verifysuccessmessage(application, "Device successfully removed from service.");
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
	
	
	
	
	public void deleteDevice_PE(String application, String DeviceName) throws InterruptedException, DocumentException {

		//ScrolltoElement(application, "portalaccess_header", xml);
		scrolltoend();
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= driver.findElements(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
					WebElement AddedDevice_DeletefromserviceLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='Delete from Service']"));
					Clickon(AddedDevice_DeletefromserviceLink);								   
					
					
					Alert alert = driver.switchTo().alert();       
					  // Capturing alert message.    
					    String alertMessage= driver.switchTo().alert().getText();
					    if(alertMessage.isEmpty()) {
					       ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
					          System.out.println("No Message displays"); 
					    }else {
					       ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
					          System.out.println("text message for alert displays as: "+alertMessage);
					    }
					  
					  try {  
					    alert.accept();
					    
					  }catch(Exception e) {
					     e.printStackTrace();
					  } 
					  
//					  compareText(application, "Device delete success message", "PEDevicedeletionsuccessmessage", "Device successfully removed from service.", xml);
					  verifysuccessmessage(application, "Device successfully removed from service.");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}
	}
			
	
	
	
	///////////////////////////
	
	public void routerPanel_PE(String application, String commandIPv4, String commandIPv6, 
			String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Router Tool Functionality");
		waitforPagetobeenable();
		scrollToTop();
		
		
		WebElement vendorModel=getwebelement(xml.getlocator("//locators/" + application + "/PE_View_VendorModelValue"));
		String vendor=Gettext(vendorModel);
		WebElement manageAddress=getwebelement(xml.getlocator("//locators/" + application + "/PE_View_ManagementAddressValue"));
		String ipAddress=Gettext(manageAddress);
		
		ScrolltoElement(application, "PE_View_VendorModelValue" , xml);
		
		
		if(vendor.startsWith("Cisco")) {
			
		//Command IPV4	
			addDropdownValues_commonMethod(application, "Command IPV4", "PE_CPE_Router_IPV4CommandsDropdown" , commandIPv4, xml);
			
			hostnametextField_IPV4_PE(application, commandIPv4, ipAddress);
			
			vrfNametextField_IPV4_PE(application, commandIPv4, vrfname_ipv4);
			
			executeCommandAndFetchTheValue_PE(application, "PE_CPE_Router_IPV4Command_Executebutton");
			
			
		//Commmand IPV6
			addDropdownValues_commonMethod(application, "Command IPV6", "PE_CPE_Router_IPV6CommandsDropdown" , commandIPv6, xml);
			
			hostnametextField_IPV6_PE(application, commandIPv6, ipAddress);
			
			vrfNametextField_IPV6_PE(application, commandIPv6, vrfname_ipv6);
			
			executeCommandAndFetchTheValue_PE(application, "PE_CPE_Router_IPV6Command_Executebutton");
			
		}
		else if(vendor.startsWith("Juniper ")){
			
			//Command IPV4	
					addDropdownValues_commonMethod(application, "Command IPV4", "PE_CPE_Router_IPV4Command_Executebutton" , commandIPv4, xml);
					
					hostnametextField_IPV4_PE(application, commandIPv4, ipAddress);
					
					vrfNametextField_IPV4_PE(application, commandIPv4, vrfname_ipv4);
					
					executeCommandAndFetchTheValue_PE(application, "PE_CPE_Router_IPV4Command_Executebutton");
					
			
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Router Panel will not display for the selected vendorModel: "+vendorModel);
			System.out.println("Router Panel will not display for the selected vendorModel: "+vendorModel);
		}
		

		
	}

	public void executeCommandAndFetchTheValue_PE(String application, String executeButton) throws InterruptedException, DocumentException {
		
		click_commonMethod(application, "Execute", executeButton, xml);
		Thread.sleep(100000);
	boolean resultField=false;	
	try {	
	resultField=getwebelement(xml.getlocator("//locators/" + application + "/PE_CPE_result_textArea")).isDisplayed();
	if(resultField) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Result' text field is displaying");
		System.out.println( "'Result' text field is displaying");
		
		String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/PE_CPE_result_textArea")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "value under 'Result' field displaying as "+ remarkvalue);
		System.out.println("value under 'Result' field displaying as "+ remarkvalue);

	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
		System.out.println( "'Result' text field is not displaying");
	}
	}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
	System.out.println("'Result' text field is not displaying");
	}
		
	}


	public void hostnametextField_IPV6_PE(String application, String commandIPv6, String ipv6Address) {
		boolean IPV4availability=false;
		try {  
			IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/PE_CPE_commandIPv6_hostnameTextfield")).isDisplayed();
		  
		  if(IPV4availability) {
			  
			  addtextFields_commonMethod(application, "IP Address or Hostname", "PE_CPE_commandIPv6_hostnameTextfield", ipv6Address, xml);
			  
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
				System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
		  }
		}catch(Exception e) {
			e.printStackTrace();
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
			System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
		}
	}


	public void vrfNametextField_IPV6_PE(String application, String commandIPV6, String vrfname_IPV6) {
		boolean IPV6availability=false;
		
		if(commandIPV6.equalsIgnoreCase("vrf")) {
			try {  
				IPV6availability=getwebelement(xml.getlocator("//locators/" + application + "/PE_CPE_commandIPv6_vrfnameTextField")).isDisplayed();
				
				if(IPV6availability) {
					addtextFields_commonMethod(application, "Router Vrf Name", "PE_CPE_commandIPv6_vrfnameTextField", vrfname_IPV6, xml);
				}else {
					ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
					System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			}
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv6' text field is not displaying for "+ commandIPV6);
			System.out.println("'VRF Name IPv6' text field is not displaying for "+ commandIPV6 +" command");
		}
		
	}	


	public void hostnametextField_IPV4_PE(String application, String command_ipv4, String ipAddress) {
		boolean IPV4availability=false;
		try {  
			IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/PE_CPE_commandIPv4_hostnameTextfield")).isDisplayed();
		  
		  if(IPV4availability) {
			  
			  addtextFields_commonMethod(application, "IP Address or Hostname", "PE_CPE_commandIPv4_hostnameTextfield", ipAddress, xml);
			  
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
				System.out.println("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
		  }
		}catch(Exception e) {
			e.printStackTrace();
			
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
			System.out.println("'Hostname or IpAddress' for 'Ipv4' text field is not displaying for "+ command_ipv4);
		}
	}


	public void vrfNametextField_IPV4_PE(String application, String command_ipv4, String vrfname_ipv4) {
		boolean IPV4availability=false;
		  
			
		if(command_ipv4.contains("vrf")) {
			try {
				IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/PE_CPE_commandIPv4_vrfnameTextField")).isDisplayed();
				
				if(IPV4availability) {
					addtextFields_commonMethod(application, "Router Vrf Name", "PE_CPE_commandIPv4_vrfnameTextField", vrfname_ipv4, xml);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
					System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				}
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			}
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv4' text field is not displaying for "+ command_ipv4);
			System.out.println("'VRF Name IPv4' text field is not displaying for "+ command_ipv4 +" command");
		}
	}	



	
	
	public void verify_CiscoVendor_AddInterface(String application, String configureinterface_checkbox, String interfacename, String getaddress, String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String bearertype_value, String ciscovendor_bandwidth_value, String encapsulation_value, String bgp_checkbox, String framingtype_value, String vlanID_value, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value) throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Interface Functionality - Cisco");
	
		ScrolltoElement(application, "portalaccess_header", xml);
		try {
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			click_commonMethod(application, "View", "viewservicepage_viewdevicelink1", xml);
			waitforPagetobeenable();
			scrollToTop();
			if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
				System.out.println("'View PE Device' page navigated as expected");

				ScrolltoElement(application, "Routes_Header", xml);//Routes_Header//RouterTool_header
		
		compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
		click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
		click_commonMethod(application, "Add Interface/Link", "addinterface_link", xml);
		scrollToTop();

		
		if(getwebelement(xml.getlocator("//locators/" + application + "/addinterface_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Interface' page navigated as expected");
			System.out.println("'Add Interface' page navigated as expected");

			scrolltoend();
		
		click_commonMethod(application, "OK", "okbutton", xml);
		
		scrollToTop();
		//verify warning messages in add interface page
		warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);
		warningMessage_commonMethod(application, "bearertype_warngmsg", "Bearer Type", xml);
		warningMessage_commonMethod(application, "encapsulation_warngmsg", "Encapsulation", xml);

		//Add Interface
		addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", configureinterface_checkbox, "yes", xml);
		addtextFields_commonMethod(application, "Interface", "interfacename_textfield", interfacename, xml);
		GetText(application, "Network", "network_fieldvalue");

		//verify EIP Allocation
		
		scrollToTop();
		click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
		{
			compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
			GetText(application, "Subnet Type", "subnettype_value");
			SelectDropdownValueUnderSelectTag(application, "City", eipallocation_city, "eipallocation_citydropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_subnetsize, "eipallocation_subnetsize", xml);
			GetText(application, "Available Pools", "eipallocation_availablepools_value");
			
			click_commonMethod(application, "Close", "closesymbol", xml);
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
		}
		

		//verify getaddress
		if(getaddress.equalsIgnoreCase("yes"))
		{
			click_commonMethod(application, "Get Address", "getaddress1_button", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/interfacerange_Address_dropdown")).isDisplayed()) 
			{
			SelectDropdownValueUnderSelectTag(application, "Interface Address Range", interfaceaddressrange_value, "interfacerange_Address_dropdown", xml);
			click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
			}
			else
			{
			addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", interfaceaddressrange_value, xml);
			
			click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
			}
		}

		isDisplayed(application, "networkipv6_fieldvalue", "Network");
		
		//verify EIP Allocation for IPV6
				
				ScrolltoElement(application, "network_fieldvalue", xml);
				click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
				{
					compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
					GetText(application, "Subnet Type", "subnettype_value");
					GetText(application, "Space Name", "eipallocation_spacename");
					SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_ipv6_subnetsize, "eipallocation_ipv6_subnetsize", xml);
					SelectDropdownValueUnderSelectTag(application, "Available Blocks", eipallocation_availableblocksvalue, "availableblocks_dropdown", xml);
					
					click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
					
					scrollToTop();
					//**compareText(application, "Subnet aalocation success message", "successmsg", "Subnet Allocation IPTransitService_05-IPTransitService_05-XFER-0017527 (null)Successfully", xml);
					GetText(application, "Subnet allocation success message", "successmsg");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
				}
			
			
		ScrolltoElement(application, "network_fieldvalue", xml);
		//verify getaddress ipv6
		if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
		{
			click_commonMethod(application, "Get Address", "getaddress2_button", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/interfacerange_AddressIpv6_dropdown")).isDisplayed()) 
			{
			//**SelectDropdownValueUnderSelectTag(application, "AvInterface Address Range IPv6", interfaceaddressrange_value, "interfacerange_AddressIpv6_dropdown", xml);

			click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
		}
		else
		{
			addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
			
			click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
		}
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Available Blocks dropdown values are not displaying");
				click_commonMethod(application, "Close", "eipallocation_popupclose", xml);
				
				addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
				
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
			}
			
		ScrolltoElement(application, "link_textfield", xml);
		addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
		SelectDropdownValueUnderSelectTag(application, "Bearer Type", bearertype_value, "bearertype_dropdown", xml);

		
		SelectDropdownValueUnderSelectTag(application, "Bandwidth", ciscovendor_bandwidth_value, "bandwidth_dropdown", xml);

		if(bearertype_value.equalsIgnoreCase("E1")) {
			SelectDropdownValueUnderSelectTag(application, "Framing Type", framingtype_value, "framingtype_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Encapsulation", encapsulation_value, "encapsulation_dropdown", xml);
		}
		else if(bearertype_value.equalsIgnoreCase("Ethernet"))
		{
			addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanID_value, xml);
		}
		else
		{
			SelectDropdownValueUnderSelectTag(application, "Encapsulation", encapsulation_value, "encapsulation_dropdown", xml);
		}
		
		
		ScrolltoElement(application, "link_textfield", xml);
		addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", bgp_checkbox, "no", xml);
		if(bgp_checkbox.equalsIgnoreCase("yes"))
		{
			SelectDropdownValueUnderSelectTag(application, "BGP Templates Generate For", bgptemplate_dropdownvalue, "multilink_bgptemplate_dropdown", xml);
			addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
			addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
			addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
			addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
			addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
		}

		scrolltoend();
		//configuration panel in add interface page
		if(configureinterface_checkbox.equalsIgnoreCase("yes"))
		{
			compareText(application, "Configuration", "configuration_header", "Configuration", xml);
			click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
			
			GetText(application, "Configuration", "configuration_textarea");
			
			click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
			
			click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
		}
		else
		{
			click_commonMethod(application, "OK", "okbutton", xml);
		}
//		compareText(application, "Interface Added success message", "AddInterfaceSucessMessage", "Interface successfully created.", xml);
		verifysuccessmessage(application, "Interface successfully created.");
		scrolltoend();
		//***click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		

		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Interface' page not navigated");
			System.out.println("'Add Interface' page not navigated");
			}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
				System.out.println("'View PE Device' page not navigated");
			}
	
			}else{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Interface page");
			System.out.println(  e+ " : Field is not displayed in Add Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add Interface page");
			System.out.println(  e+" : Field is not displayed in Add Interface page");
		}
	}
	
	
	
	

	public void verify_CiscoVendor_EditInterface(String application, String interfacename, String getaddress, String ipv6_getaddress_button, String name, String editDevicename,
			String edit_configureinterface_checkbox, String edit_interfacename, String edit_network, String edit_eipallocation_city, String edit_interfaceaddressrange_value,
			String edit_interfaceaddressrangeIPv6_value, String edit_ipallocation_availableblocksvalue, String edit_linkvalue, String edit_bearertype_value, 
			String edit_ciscovendor_bandwidth_value, String edit_framingtype_value, String edit_encapsulation_value, String edit_vlanID_value, String edit_bgp_checkbox,
			String edit_bgptemplate_dropdownvalue, String edit_cpewan_value, String edit_cpewanipv6_value, String edit_descriptionfield_value, String edit_ascustomerfield_value,
			String edit_bgppassword_value, String ipsubnetipv6_value, String ipsubnetipv4_value, String edit_eipallocation_subnetsize, String edit_eipallocation_ipv6_subnetsize
			) throws InterruptedException, DocumentException, IOException {
		
		//edit Interface
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edit Interface Functionality - Cisco");
		ScrolltoElement(application, "portalaccess_header", xml);//portalaccess_header//ConfigurationOptions_Header
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			
			scrolltoend();
			WebElement SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface"));

//			WebElement SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", JuniperInterfaceNameValue));
			//WebElement SelectInterface= getwebelement("//div[div[@role='gridcell'][@col-id='interfaceName']/div[text()='e3-4468/2799/3456:4884:38962.033256']]//div//span[contains(@class,'unchecked')]");
			if(SelectInterface.isDisplayed())
			{
				Clickon(SelectInterface);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
				
				if(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", name)).isDisplayed())
				{
					WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", name));
					Clickon(AddedDevice_Interface_Actiondropdown);
				}
				else if(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", editDevicename)).isDisplayed())
				{
					WebElement EditDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", editDevicename));
					Clickon(EditDevice_Interface_Actiondropdown);	
				}

				click_commonMethod(application, "Edit", "edit", xml);
				
				
				if(getwebelement(xml.getlocator("//locators/" + application + "/editinterface_header")).isDisplayed()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Interface' page navigated as expected");
					System.out.println("'Edit Interface' page navigated as expected");
					
			editcheckbox_commonMethod(application, edit_configureinterface_checkbox, "configureinterface_checkbox", "Configure Interface on Device", xml);
			if(!edit_interfacename.equalsIgnoreCase("null"))
			{
				cleartext(application, "Interface", "interfacename_textfield");
				edittextFields_commonMethod(application, "Interface", "interfacename_textfield", edit_interfacename, xml);
			}
			else
			{
				GetText(application, "Interface", "interfacename_textfield");
			}
			addDropdownValues_commonMethod(application, "Network", "network_fieldvalue", edit_network, xml);

			//verify EIP Allocation
			
			click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
			{
				compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
				GetText(application, "Subnet Type", "subnettype_value");
				SelectDropdownValueUnderSelectTag(application, "City", edit_eipallocation_city, "eipallocation_citydropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Sub Net Size", edit_eipallocation_subnetsize, "eipallocation_subnetsize", xml);
			
				GetText(application, "Available Pools", "eipallocation_availablepools_value");
				//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
				
				click_commonMethod(application, "Close", "closesymbol", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
			}
			

			//verify getaddress
			if(getaddress.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress1_button", xml);
				SelectDropdownValueUnderSelectTag(application, "Interface Address Range", edit_interfaceaddressrange_value, "interfacerange_Address_dropdown", xml);

				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
			}
			else
			{
				edittextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrangeIPv6_textfield", edit_interfaceaddressrange_value, xml);
				
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

			}
			GetText(application, "Network", "networkipv6_fieldvalue");

			//verify EIP Allocation
			
			ScrolltoElement(application, "network_fieldvalue", xml);
			click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);

			//compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
				GetText(application, "EIP Address Allocation header", "eipallocationIPv6_header");
				GetText(application, "Subnet Type", "subnettype_valueMultilink");
				GetText(application, "Space Name", "eipallocation_spacename");
				SelectDropdownValueUnderSelectTag(application, "Sub Net Size", edit_eipallocation_ipv6_subnetsize, "eipallocation_ipv6_subnetsize", xml);
				
				boolean availableblocks_dropdowncheck= availableBlocks_dropdownCheck(application, "Available Blocks", "availableblocks_dropdown");
				if(availableblocks_dropdowncheck==true) 
				{
				addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", edit_ipallocation_availableblocksvalue, xml);
				
				click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
				
				scrollToTop();
				GetText(application, "Subnet aalocation success message", "successmsg");
			

			//verify getaddress ipv6
			ScrolltoElement(application, "network_fieldvalue", xml);
			if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress2_button", xml);
				addDropdownValues_commonMethod(application, "Interface Address Range IPv6", "interfacerange_AddressIpv6_dropdown", edit_interfaceaddressrangeIPv6_value, xml);
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
			}
			else
			{
				edittextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", edit_interfaceaddressrangeIPv6_value, xml);
				
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

			}
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Available Blocks dropdown values are not displaying");
					click_commonMethod(application, "Close", "eipallocation_popupclose", xml);
					
					edittextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", edit_interfaceaddressrangeIPv6_value, xml);
					
					click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

				}

			ScrolltoElement(application, "link_textfield", xml);
			edittextFields_commonMethod(application, "Link", "link_textfield", edit_linkvalue, xml);
			SelectDropdownValueUnderSelectTag(application, "Bearer Type", edit_bearertype_value, "bearertype_dropdownEdit", xml);
			SelectDropdownValueUnderSelectTag(application, "Bandwidth", edit_ciscovendor_bandwidth_value, "multilink_bandwidth_dropdown", xml);
				
			if(edit_bearertype_value.equalsIgnoreCase("E1")) {
				SelectDropdownValueUnderSelectTag(application, "Framing Type", edit_framingtype_value, "framingtype_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Encapsulation", edit_encapsulation_value, "encapsulation_dropdown", xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("Ethernet"))
			{
				edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", edit_vlanID_value, xml);
			}
			else
			{
				SelectDropdownValueUnderSelectTag(application, "Encapsulation", edit_encapsulation_value, "encapsulation_dropdownEdit", xml);
			}
			
			
			ScrolltoElement(application, "link_textfield", xml);
			
				editcheckbox_commonMethod(application, edit_bgp_checkbox, "bgp_checkbox", "BGP", xml);
				if(edit_bgp_checkbox.equalsIgnoreCase("yes"))
				{
					addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", edit_bgptemplate_dropdownvalue, xml);
				
				if(!edit_cpewan_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "CPE WAN", "cpewan_textfield");
					edittextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", edit_cpewan_value, xml);
				}
				else
				{
					GetText(application, "CPE WAN", "cpewan_textfield");
				}
				if(!edit_cpewanipv6_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield");
					edittextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", edit_cpewanipv6_value, xml);
				}
				else
				{
					GetText(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield");
				}
				if(!edit_descriptionfield_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "Description", "bgp-descriptionfield");
					edittextFields_commonMethod(application, "Description", "bgp-descriptionfield", edit_descriptionfield_value, xml);
				}
				else
				{
					GetText(application, "Description", "bgp-descriptionfield");
				}
				if(!edit_ascustomerfield_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "AS CUSTOMER", "bgp_ascustomerfield");
					edittextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", edit_ascustomerfield_value, xml);
				}
				else
				{
					GetText(application, "AS CUSTOMER", "bgp_ascustomerfield");
				}
				if(!edit_bgppassword_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "BGP PASSWORD", "bgppassword_field");
					edittextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", edit_bgppassword_value, xml);
				}
				else
				{
					GetText(application, "BGP PASSWORD", "bgppassword_field");
				}
				//Add IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
				edittextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", ipsubnetipv6_value, xml);
				//Remove IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Remove", "ipsubnetipv6_removebutton", xml);
				//Add IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
				addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", ipsubnetipv6_value, xml);
				//Add IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
				edittextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", ipsubnetipv4_value, xml);
				//Remove IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Remove", "ipsubnetipv4_removebutton", xml);
				//Add IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
				edittextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", ipsubnetipv4_value, xml);
			}
			
			scrolltoend();
			//configuration panel in edit interface page
			if(edit_configureinterface_checkbox.equalsIgnoreCase("Yes"))
			{
				ScrolltoElement(application, "configuration_header", xml);
				compareText(application, "Configuration", "configuration_header", "Configuration", xml);
				click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
				
				
				//click_commonMethod(application, "OK", "configAlert_okbutton", xml);
				GetText(application, "Configuration", "configuration_textarea");
				
				click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
				
				scrolltoend();
				
				compareText(application, "Interface Configuration History header", "interfaceconfighistory_header", "Interface Configuration History", xml);
				compareText(application, "Date column", "date_column", "Date", xml);
				compareText(application, "File Name column", "filename_column", "File Name", xml);
				
				click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
				
				waitforPagetobeenable();
//				compareText(application, "Interface update success message", "UpdateInterfaceSucessMessage", "Interface successfully updated.", xml);
				verifysuccessmessage(application, "Interface successfully updated.");
			}
			else
			{
				ScrolltoElement(application, "okbutton", xml);
				click_commonMethod(application, "OK", "okbutton", xml);
			
			verifysuccessmessage(application, "Interface successfully updated.");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface is not added");
		}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit Interface' page not navigated");
				System.out.println("'Edit Interface' page not navigated");
				}
		}	}
	
	
	
	
	
	
	

	public static String Cisco_Multilink_InterfaceName;
public void verify_CiscoVendor_AddMultilink(String application, String name, String multilink_interfacename, String vendormodel, String getaddress,
		String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize,
		String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String multilink_bandwidth_value, 
		String Multilink_Encapsulation_Value, String multilink_bgpcheckbox, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value,
		String descriptionfield_value, String ascustomerfield_value, String bgppassword_value, String multilink_configureinterface_checkbox, String interfacename,
		String checktoaddinterface_checkbox, String unitid_value, String slot_value, String pic_value, String port_value
		) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Multilink Functionality - Cisco");
		
	ScrolltoElement(application, "portalaccess_header", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		click_commonMethod(application, "View", "viewservicepage_viewdevicelink1", xml);
		waitforPagetobeenable();
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
			ScrolltoElement(application, "Routes_Header", xml);//Routes_Header//RouterTool_header
				
				compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
				click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
				click_commonMethod(application, "Add Multilink", "addmultilink_link", xml);
				
				
				if(getwebelement(xml.getlocator("//locators/" + application + "/addmultilink_header")).isDisplayed()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Multilink' page navigated as expected");
					System.out.println("'Add Multilink' page navigated as expected");
					
				scrollToTop();
				addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", multilink_configureinterface_checkbox, "yes", xml);
				click_commonMethod(application, "Configure Interface on Device", "configureinterface_checkbox", xml);
				scrolltoend();
				
				if(multilink_configureinterface_checkbox.equalsIgnoreCase("no"))
				{
				click_commonMethod(application, "OK", "okbutton", xml);
				
				}
				else
				{
					click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
					
				}
				scrollToTop();
				//verify warning messages in add interface page
				warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);
				warningMessage_commonMethod(application, "encapsulation_warngmsg", "Encapsulation", xml);
				warningMessage_commonMethod(application, "slot_warngmsg", "Slot", xml);
				warningMessage_commonMethod(application, "pic_warngmsg", "Pic", xml);
				warningMessage_commonMethod(application, "port_warngmsg", "Port", xml);

				//Add Multilink
				//isDisplayed(application, "Multilink", "multilink_text");
				addtextFields_commonMethod(application, "Interface", "interfacename_textfield", multilink_interfacename, xml);
				
				//verify EIP Allocation
				
				click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
				{
					compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
					GetText(application, "Subnet Type", "subnettype_valueMultilink");
					SelectDropdownValueUnderSelectTag(application, "City", eipallocation_city, "eipallocation_citydropdown", xml);
					SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_subnetsize, "eipallocation_subnetsize", xml);
					
					GetText(application, "Available Pools", "eipallocation_availablepools_value");
					//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
					
					click_commonMethod(application, "Close", "closesymbol", xml);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
				}
				

				//verify getaddress
				if(getaddress.equalsIgnoreCase("yes"))
				{
					click_commonMethod(application, "Get Address", "getaddress1_button", xml);
					if(getwebelement(xml.getlocator("//locators/" + application + "/interfacerange_Address_dropdown")).isDisplayed()) 
					{
					SelectDropdownValueUnderSelectTag(application, "Interface Address Range", interfaceaddressrange_value, "interfacerange_Address_dropdown", xml);

					click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
					}
					else
					{
					addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", interfaceaddressrange_value, xml);
					
					click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
					}
				}
				GetText(application, "Network", "networkipv6_fieldvalueMutilink");

				//verify EIP Allocation
				
				ScrolltoElement(application, "interfacerange_AddressIpv6_dropdown", xml);
				ScrolltoElement(application, "interfaceaddressrange_textfield", xml);

				
				click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
				{
					GetText(application, "EIP Address Allocation header", "eipallocationIPv6_header");
					GetText(application, "Subnet Type", "subnettype_valueMultilink");
					GetText(application, "Space Name", "eipallocation_spacename");
					SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_ipv6_subnetsize, "eipallocation_ipv6_subnetsize", xml);
					SelectDropdownValueUnderSelectTag(application, "Available Blocks", eipallocation_availableblocksvalue, "availableblocks_dropdown", xml);

					
					click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
					
					scrollToTop();
					GetText(application, "Subnet aalocation success message", "successmsg");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
				}
				

				//verify getaddress ipv6
				ScrolltoElement(application, "getaddress1_button", xml);
				if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
				{
					click_commonMethod(application, "Get Address", "getaddress2_button", xml);
					if(getwebelement(xml.getlocator("//locators/" + application + "/interfacerange_AddressIpv6_dropdownMultilink")).isDisplayed()) 
					{
					SelectDropdownValueUnderSelectTag(application, "Interface Address Range IPv6", interfaceaddressrange_value, "interfacerange_AddressIpv6_dropdownMultilink", xml);

					click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
					}
					else
					{
					addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
					
					click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

					}
				}
				
				addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
				
				SelectDropdownValueUnderSelectTag(application, "Encapsulation", Multilink_Encapsulation_Value, "encapsulation_dropdown", xml);
				ScrolltoElement(application, "link_textfield", xml);
				addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", unitid_value, xml);
				addtextFields_commonMethod(application, "Slot", "slot_textfield", slot_value, xml);
				addtextFields_commonMethod(application, "Pic", "pic_textfield", pic_value, xml);
				addtextFields_commonMethod(application, "Port", "port_textfield", port_value, xml);
				
				addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", multilink_bgpcheckbox, "no", xml);
				if(multilink_bgpcheckbox.equalsIgnoreCase("yes"))
				{
					SelectDropdownValueUnderSelectTag(application, "BGP Templates Generate For", bgptemplate_dropdownvalue, "multilink_bgptemplate_dropdown", xml);
					addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
					addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
					addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
					addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
					addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
				}

				//Multilinked Bearers table
				scrolltoend();
				compareText(application, "Multilinked Bearers", "multilinkedbearers_header", "Multilinked Bearers", xml);

				//table columns
				compareText(application, "Check to add Interface", "checktoaddinterface_column", "Check to add Interface", xml);
				compareText(application, "Interface", "multilink_interface_column", "Interface", xml);
				compareText(application, "Link/Circuit", "multilink_link_column", "Link/Circuit", xml);
				compareText(application, "Bearer Type", "multilink_BearerType_column", "Bearer Type", xml);
				compareText(application, "VLAN Id", "multilink_vlanid_column", "VLAN Id", xml);
				
				String MultilinkBearer_ExistingInterface= getwebelement(xml.getlocator("//locators/" + application + "/multilinkbearer_tabledata")).getAttribute("style");
				
				if(!MultilinkBearer_ExistingInterface.contains("height: 1px"))
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Existing interface details are displaying in multilink bearer table");
					if(checktoaddinterface_checkbox.equalsIgnoreCase("yes"))
					{
						if(name.contains("Cisco"))
						{
							WebElement CheckToAddInterface= getwebelement(xml.getlocator("//locators/" + application + "/checktoaddinterface").replace("value", interfacename));
							Clickon(CheckToAddInterface);
						}
						else
						{
							WebElement CheckToAddInterface= getwebelement(xml.getlocator("//locators/" + application + "/checktoaddinterface").replace("value", Edit_JuniperInterfaceNameValue));
							Clickon(CheckToAddInterface);
						}
					}
					
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "No existing interfaces to display");
				}
				scrolltoend();
				//configuration panel in add interface page
				if(multilink_configureinterface_checkbox.equalsIgnoreCase("yes") || multilink_configureinterface_checkbox.equalsIgnoreCase("null"))
				{
					compareText(application, "Configuration", "configuration_header", "Configuration", xml);
					click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
					
					GetText(application, "Configuration", "configuration_textarea");
					
					click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
					
					click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
				}
				else
				{
					click_commonMethod(application, "OK", "okbutton", xml);
				}
				
				waitforPagetobeenable();
//				compareText(application, "Multilink Added success message", "AddMultilinkSucessMessage", "Interface successfully created.", xml);
				verifysuccessmessage(application, "Interface successfully created.");
				scrolltoend();
				//**click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				
			
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Multilink' page not navigated");
					System.out.println("'Add Multilink' page not navigated");
					}

	}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
	System.out.println("'View PE Device' page not navigated");
	}

	}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}

}




	
	
	
	
	
	
	public static String JuniperInterfaceNameValue, Edit_JuniperInterfaceNameValue;
	public void VerifyAddInterface_JuniperVendor(String application, String interfacename, String getaddress, String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String bearertype_value, String bandwidth_value, String encapsulation_value, String bgp_checkbox, String framingtype_value, String vlanID_value, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value, String juniper_configureinterface_checkbox, String cardtype_dropdownvalue, String clocksource_value, String STM1Number_value, String bearerNo_value, String unitid_value, String slot_value, String pic_value, String port_value, String ivmanagement_checkbox, String atricaconnected_checkbox, String cardtype_dropdownvalue_gigabit) throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Interface Functionality - Juniper");
		ScrolltoElement(application, "portalaccess_header", xml);
		try {
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			click_commonMethod(application, "View", "viewservicepage_viewdevicelink1", xml);
			waitforPagetobeenable();
			scrollToTop();
			if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
				System.out.println("'View PE Device' page navigated as expected");

				ScrolltoElement(application, "Routes_Header", xml);//Routes_Header//RouterTool_header
		
		compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
		click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
		click_commonMethod(application, "Add Interface/Link", "addinterface_link", xml);
		
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/addinterface_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Interface' page navigated as expected");
			System.out.println("'Add Interface' page navigated as expected");
			
		scrolltoendOptional();
		click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
		
		
		//verify warning messages in add interface page
		ScrolltoElement(application, "encapsulation_dropdown", xml);
		warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);
		warningMessage_commonMethod(application, "bearertype_warngmsg", "Bearer Type", xml);
		warningMessage_commonMethod(application, "encapsulation_warngmsg", "Encapsulation", xml);
		warningMessage_commonMethod(application, "slot_warngmsg", "Slot", xml);
		warningMessage_commonMethod(application, "pic_warngmsg", "Pic", xml);
		warningMessage_commonMethod(application, "port_warngmsg", "Port", xml);
		warningMessage_commonMethod(application, "stm1number_warngmsg", "STM1 Number", xml);
		warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);

		//Add Interface
		scrollToTop();
		addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", juniper_configureinterface_checkbox, "yes", xml);
		GetText(application, "Network", "network_fieldvalue");

		//verify EIP Allocation
		
		click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
		{
			compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
			GetText(application, "Subnet Type", "subnettype_value");
			SelectDropdownValueUnderSelectTag(application, "City", eipallocation_city, "eipallocation_citydropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_subnetsize, "eipallocation_subnetsize", xml);
			GetText(application, "Available Pools", "eipallocation_availablepools_value");
			
			click_commonMethod(application, "Close", "closesymbol", xml);
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
		}
		

		//verify getaddress
		if(getaddress.equalsIgnoreCase("yes"))
		{
			click_commonMethod(application, "Get Address", "getaddress1_button", xml);
			SelectDropdownValueUnderSelectTag(application, "Interface Address Range", interfaceaddressrange_value, "interfacerange_Address_dropdown", xml);
			click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
		}
		else
		{
			addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", interfaceaddressrange_value, xml);
			
			click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

		}

		isDisplayed(application, "networkipv6_fieldvalue", "Network");

		//verify EIP Allocation for IPV6
		
		click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
		{
			//compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
			GetText(application, "EIP Address Allocation header", "eipallocationIPv6_header");
			GetText(application, "Subnet Type", "subnettype_value");
			GetText(application, "Space Name", "eipallocation_spacename");
			SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_ipv6_subnetsize, "eipallocation_ipv6_subnetsize", xml);
			isDisplayed(application, "availableblocks_dropdown", "Available Blocks");
			
			click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
			
			scrollToTop();
			click_commonMethod(application, "Close", "closesymbol", xml);
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
		}
		

		//verify getaddress ipv6
		if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
		{
			ScrolltoElement(application, "getaddress1_button", xml);
			GetText(application, "Get Address", "getaddress2_button");
		}
		else
		{
			addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
			
			click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
		}

		ScrolltoElement(application, "link_textfield", xml);
		addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
		SelectDropdownValueUnderSelectTag(application, "Bearer Type", bearertype_value, "bearertype_dropdown", xml);

		if(bearertype_value.equalsIgnoreCase("10Gigabit Ethernet") || bearertype_value.equalsIgnoreCase("Gigabit Ethernet") || bearertype_value.equalsIgnoreCase("Gigabit Ethernet")) {
			SelectDropdownValueUnderSelectTag(application, "Bandwidth", bandwidth_value, "bandwidth_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Card Type", cardtype_dropdownvalue, "cardtype_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Encapsulation", encapsulation_value, "encapsulation_dropdown", xml);
		}
		else if(bearertype_value.equalsIgnoreCase("E1"))
		{
			SelectDropdownValueUnderSelectTag(application, "Bandwidth", bandwidth_value, "bandwidth_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Card Type", cardtype_dropdownvalue, "cardtype_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Encapsulation", encapsulation_value, "encapsulation_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Framing Type", framingtype_value, "framingtype_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Clock Source", clocksource_value, "clocksource_dropdown", xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
			addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", bearerNo_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("E3") || bearertype_value.equalsIgnoreCase("T3"))
		{
			SelectDropdownValueUnderSelectTag(application, "Bandwidth", bandwidth_value, "bandwidth_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Encapsulation", encapsulation_value, "encapsulation_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Card Type", cardtype_dropdownvalue, "cardtype_dropdown", xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
			addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", bearerNo_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("STM-1"))
		{
			SelectDropdownValueUnderSelectTag(application, "Bandwidth", bandwidth_value, "bandwidth_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Card Type", cardtype_dropdownvalue, "cardtype_dropdown", xml);
			SelectDropdownValueUnderSelectTag(application, "Encapsulation", encapsulation_value, "encapsulation_dropdown", xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
		}
		else
		{
			SelectDropdownValueUnderSelectTag(application, "Encapsulation", encapsulation_value, "encapsulation_dropdown", xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
		}
		
		addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", unitid_value, xml);
		addtextFields_commonMethod(application, "Slot", "slot_textfield", slot_value, xml);
		addtextFields_commonMethod(application, "Pic", "pic_textfield", pic_value, xml);
		addtextFields_commonMethod(application, "Port", "port_textfield", port_value, xml);
		//addtextFields_commonMethod(application, "Interface", "interfacename_textfield", interfacename, xml);
		String Juniper_InterfaceName= GetText(application, "Interface", "interfacename_textfield");
		if(encapsulation_value.equalsIgnoreCase("802.1q"))
		{
			addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanID_value, xml);
		}
		addCheckbox_commonMethod(application, "ivmanagement_checkbox", "IV Management", ivmanagement_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "atricaconnected_checkbox", "Atrica Connected", atricaconnected_checkbox, "no", xml);
		
		
		//configuration panel in add interface page
		if(juniper_configureinterface_checkbox.equalsIgnoreCase("yes") || juniper_configureinterface_checkbox.equalsIgnoreCase("null"))
		{
			compareText(application, "Configuration", "configuration_header", "Configuration", xml);
			scrolltoend();
			click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
			click_commonMethod(application, "Generate Configuration", "GenerateConfigurationButton2", xml);
			
			GetText(application, "Configuration", "configuration_textarea");
			
			click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
			
			click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
			
			 Alert alert = driver.switchTo().alert();		
		     // Capturing alert message.    
		       String alertMessage= driver.switchTo().alert().getText();
		       if(alertMessage.isEmpty()) {
		    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
			       System.out.println("No Message displays"); 
		       }else {
		    	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
			       System.out.println("text message for alert displays as: "+alertMessage);
		       }
		     
		     try {  
		       alert.accept();
		       
		     }catch(Exception e) {
		    	 e.printStackTrace();
		     }
		     
		}
		else
		{
			scrolltoendOptional();
			click_commonMethod(application, "OK", "okbutton", xml);
		}
		waitforPagetobeenable();
//		compareText(application, "Interface Added success message", "AddInterfaceSucessMessage", "Interface successfully created.", xml);
		verifysuccessmessage(application, "Interface successfully created.");
		scrolltoendOptional();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		
		
				JuniperInterfaceNameValue= Juniper_InterfaceName;
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Interface' page not navigated");
			System.out.println("'Add Interface' page not navigated");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page not navigated");
		}
		
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Interface page");
			System.out.println(  e+ " : Field is not displayed in Add Interface page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add Interface page");
			System.out.println(  e+" : Field is not displayed in Add Interface page");
		}
}
	

	

	
	public void scrolltoendOptional() {//Or Scroll Down
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
public boolean availableBlocks_dropdownCheck(String application, String labelname, String xpath) {
		
		boolean availability=false;
		boolean Value=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
			  
				  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
				  
				  
				  //verify list of values inside dropdown
				  List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
				  if(listofvalues.isEmpty()) {
					  Value= false;
				  }
				  
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
			  System.out.println(labelname + " is not displaying");
		  }
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
			  System.out.println(labelname + " is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
			System.out.println(" NO value selected under "+ labelname + " dropdown");
		}
		
		return Value;
	}




	
public void VerifyEditInterface_JuniperVendor(String application, String name, String editDevicename, String edit_configureinterface_checkbox, String edit_interfacename,
		String edit_network, String edit_eipallocation_city, String edit_interfaceaddressrange_value, String edit_interfaceaddressrangeIPv6_value, 
		String edit_availableblocksvalue, String edit_linkvalue, String edit_bearertype_value, String edit_bandwidth_value, String edit_framingtype_value,
		String edit_encapsulation_value, String edit_vlanID_value, String edit_bgp_checkbox, String edit_bgptemplate_dropdownvalue, String edit_cpewan_value,
		String edit_cpewanipv6_value, String edit_descriptionfield_value, String edit_ascustomerfield_value, String edit_bgppassword_value,
		String edit_juniper_configureinterface_checkbox, String edit_juniper_interfacename, String edit_cardtype_dropdownvalue, String edit_clocksource_value, 
		String edit_STM1Number_value, String edit_bearerNo_value, String edit_unitid_value, String edit_slot_value, String edit_pic_value, String edit_port_value,
		String edit_ivmanagement_checkbox, String edit_atricaconnected_checkbox, String cardtype_dropdownvalue_gigabit, String edit_eipallocation_subnetsize,
		String edit_eipallocation_ipv6_subnetsize, String edit_eipallocation1, String edit_getaddress, String edit_eipallocation2, String edit_ipv6_getaddress,
		String edit_ipsubnetipv6_value, String edit_ipsubnetipv4_value) throws InterruptedException, DocumentException, IOException {
		
		//edit Interface
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edit Interface Functionality - Juniper");
	try {
	
	ScrolltoElement(application, "portalaccess_header", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		scrolltoendOptional();
		WebElement SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface"));

		//	WebElement SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", JuniperInterfaceNameValue));
		//WebElement SelectInterface= getwebelement("//div[div[@role='gridcell'][@col-id='interfaceName']/div[text()='e3-4468/2799/3456:4884:38962.033256']]//div//span[contains(@class,'unchecked')]");
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", name)).isDisplayed())
			{
				WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", name));
				Clickon(AddedDevice_Interface_Actiondropdown);
			}
			else if(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", editDevicename)).isDisplayed())
			{
				WebElement EditDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", editDevicename));
				Clickon(EditDevice_Interface_Actiondropdown);	
			}

			click_commonMethod(application, "Edit", "edit", xml);
			
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/editinterface_header")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Interface' page navigated as expected");
				System.out.println("'Edit Interface' page navigated as expected");
				
			editcheckbox_commonMethod(application, edit_juniper_configureinterface_checkbox, "configureinterface_checkbox", "Configure Interface on Device", xml);
			addDropdownValues_commonMethod(application, "Network", "network_fieldvalue", edit_network, xml);

			//verify EIP Allocation
			
			if(edit_eipallocation1.equalsIgnoreCase("yes"))
			{
			click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
			{
				compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
				GetText(application, "Subnet Type", "subnettype_value");
				addDropdownValues_commonMethod(application, "City", "eipallocation_citydropdown", edit_eipallocation_city, xml);
				addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", edit_eipallocation_subnetsize, xml);
				GetText(application, "Available Pools", "eipallocation_availablepools_value");
				isDisplayed(application, "allocatesubnet_button", "Allocate subnet button");
				
				click_commonMethod(application, "Close", "closesymbol", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
			}
			}
			

			//verify getaddress
			if(edit_getaddress.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress1_button", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/interfacerange_Address_dropdown")).isDisplayed()) 
				{
				addDropdownValues_commonMethod(application, "Interface Address Range", "interfacerange_Address_dropdown", edit_interfaceaddressrange_value, xml);
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
				}
				else
				{
				edittextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", edit_interfaceaddressrange_value, xml);
				
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
				}
			}
			
				
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

			isDisplayed(application, "networkipv6_fieldvalue", "Network");


			//verify EIP Allocation
			if(edit_eipallocation2.equalsIgnoreCase("yes"))
			{
			
			click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
			
				compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
				GetText(application, "Subnet Type", "subnettype_value");
				GetText(application, "Space Name", "eipallocation_spacename");
				addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_ipv6_subnetsize", edit_eipallocation_ipv6_subnetsize, xml);
				
				boolean availableblocks_dropdowncheck= availableBlocks_dropdownCheck(application, "Available Blocks", "availableblocks_dropdown");
				if(availableblocks_dropdowncheck==true) 
				{
					GetText(application, "Available Blocks", "availableblocks_dropdown");
				
				isDisplayed(application, "allocatesubnet_button", "Allocate subnet");
				click_commonMethod(application, "Close", "closesymbol", xml);
				
			

			//verify getaddress ipv6
			if(edit_ipv6_getaddress.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress2_button", xml);
				addDropdownValues_commonMethod(application, "Interface Address Range IPv6", "interfacerange_AddressIpv6_dropdown", edit_interfaceaddressrangeIPv6_value, xml);
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
			}
			else
			{
				edittextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", edit_interfaceaddressrangeIPv6_value, xml);
				
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
			}
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Available Blocks dropdown values are not displaying");
					click_commonMethod(application, "Close", "eipallocation_popupclose", xml);
					
					edittextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", edit_interfaceaddressrangeIPv6_value, xml);
					
					click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
				}
			}

			ScrolltoElement(application, "link_textfield", xml);
			edittextFields_commonMethod(application, "Link", "link_textfield", edit_linkvalue, xml);
			
			SelectDropdownValueUnderSelectTag(application, "Bearer Type", edit_bearertype_value, "bearertype_dropdownEdit", xml);
			if(edit_bearertype_value.equalsIgnoreCase("10Gigabit Ethernet") || edit_bearertype_value.equalsIgnoreCase("Gigabit Ethernet") || edit_bearertype_value.equalsIgnoreCase("Gigabit Ethernet")) {
				SelectDropdownValueUnderSelectTag(application, "Bandwidth", edit_bandwidth_value, "bandwidth_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Card Type", edit_cardtype_dropdownvalue, "cardtype_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Encapsulation", edit_encapsulation_value, "encapsulation_dropdownEdit", xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("E1"))
			{
				SelectDropdownValueUnderSelectTag(application, "Bandwidth", edit_bandwidth_value, "bandwidth_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Card Type", edit_cardtype_dropdownvalue, "cardtype_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Encapsulation", edit_encapsulation_value, "encapsulation_dropdownEdit", xml);
				SelectDropdownValueUnderSelectTag(application, "Framing Type", edit_framingtype_value, "framingtype_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Clock Source", edit_clocksource_value, "clocksource_dropdown", xml);
				ClearAndEnterTextValue(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				ClearAndEnterTextValue(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("E3") || edit_bearertype_value.equalsIgnoreCase("T3"))
			{
				SelectDropdownValueUnderSelectTag(application, "Bandwidth", edit_bandwidth_value, "bandwidth_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Card Type", edit_cardtype_dropdownvalue, "cardtype_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Encapsulation", edit_encapsulation_value, "encapsulation_dropdownEdit", xml);
				edittextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				edittextFields_commonMethod(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("STM-1"))
			{
				SelectDropdownValueUnderSelectTag(application, "Bandwidth", edit_bandwidth_value, "bandwidth_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Card Type", edit_cardtype_dropdownvalue, "cardtype_dropdown", xml);
				SelectDropdownValueUnderSelectTag(application, "Encapsulation", edit_encapsulation_value, "encapsulation_dropdownEdit", xml);
				ClearAndEnterTextValue(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
			}
			else
			{
				SelectDropdownValueUnderSelectTag(application, "Encapsulation", edit_encapsulation_value, "encapsulation_dropdownEdit", xml);
				ClearAndEnterTextValue(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
			}
			
			edittextFields_commonMethod(application, "Unit ID", "unitid_textfield", edit_unitid_value, xml);
			edittextFields_commonMethod(application, "Slot", "slot_textfield", edit_slot_value, xml);
			edittextFields_commonMethod(application, "Pic", "pic_textfield", edit_pic_value, xml);
			edittextFields_commonMethod(application, "Port", "port_textfield", edit_port_value, xml);
			
			String edit_Juniper_InterfaceName= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_textfield")).getAttribute("value");
			if(edit_encapsulation_value.equalsIgnoreCase("802.1q"))
			{
				ClearAndEnterTextValue(application, "VLAN Id", "vlanid_textfield", edit_vlanID_value, xml);
			}
			editcheckbox_commonMethod(application, edit_ivmanagement_checkbox, "ivmanagement_checkbox", "IV Management", xml);
			editcheckbox_commonMethod(application, edit_atricaconnected_checkbox, "atricaconnected_checkbox", "Atrica Connected", xml);
			editcheckbox_commonMethod(application, edit_bgp_checkbox, "bgp_checkbox", "BGP", xml);

			//configuration panel in edit interface page
			if(edit_juniper_configureinterface_checkbox.equalsIgnoreCase("Yes"))
			{
				ScrolltoElement(application, "configuration_header", xml);
				compareText(application, "Configuration", "configuration_header", "Configuration", xml);
				click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
				click_commonMethod(application, "Generate Configuration", "GenerateConfigurationButton2", xml);
				
				
				GetText(application, "Configuration", "configuration_textarea");
				
				//click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
				
				click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
				
				
				Alert alert = driver.switchTo().alert();       
              
              // Capturing alert message.    
                String alertMessage= driver.switchTo().alert().getText();
                if(alertMessage.isEmpty()) {
                   ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
                      System.out.println("No Message displays"); 
                }else {
                   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
                      System.out.println("text message for alert displays as: "+alertMessage);
                }
              
              try {  
                alert.accept();
                
              }catch(Exception e) {
                 e.printStackTrace();
              } 
				
			}else{
				ScrolltoElement(application, "okbutton", xml);
				click_commonMethod(application, "OK", "okbutton", xml);
			}
			waitforPagetobeenable();
			verifysuccessmessage(application, "Interface successfully updated.");
			
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit Interface' page not navigated");
				System.out.println("'Edit Interface' page not navigated");
				}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Interface is not added");
		}
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
	sa.assertAll();
	}




public static String Juniper_Multilink_InterfaceName;
public void verify_JuniperVendor_AddMultilink(String application, String name, String multilink_interfacename, String vendormodel, String getaddress, 
		String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize, 
		String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String bandwidth_value, String Multilink_Encapsulation_Value,
		String multilink_bgpcheckbox, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value,
		String ascustomerfield_value, String bgppassword_value, String multilink_configureinterface_checkbox, String interfacename, String checktoaddinterface_checkbox,
		String unitid_value, String slot_value, String pic_value, String port_value) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Multilink Functionality - Juniper");
	try {
	ScrolltoElement(application, "portalaccess_header", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		click_commonMethod(application, "View", "viewservicepage_viewdevicelink1", xml);
		waitforPagetobeenable();
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
			ScrolltoElement(application, "Routes_Header", xml);//Routes_Header//RouterTool_header
				
				compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
				click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
				click_commonMethod(application, "Add Multilink", "addmultilink_link", xml);
				
				scrollToTop();
				if(getwebelement(xml.getlocator("//locators/" + application + "/addmultilink_header")).isDisplayed()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Multilink' page navigated as expected");
					System.out.println("'Add Multilink' page navigated as expected");
					
				click_commonMethod(application, "Configure Interface on Device", "configureinterface_checkbox", xml);
				scrolltoend();
				
				if(multilink_configureinterface_checkbox.equalsIgnoreCase("no"))
				{
				click_commonMethod(application, "OK", "okbutton", xml);
				
				}
				else
				{
					click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
					
				}
				//verify warning messages in add interface page
				warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);
				warningMessage_commonMethod(application, "encapsulation_warngmsg", "Encapsulation", xml);
				warningMessage_commonMethod(application, "slot_warngmsg", "Slot", xml);
				warningMessage_commonMethod(application, "pic_warngmsg", "Pic", xml);
				warningMessage_commonMethod(application, "port_warngmsg", "Port", xml);

				//Add Multilink
				scrollToTop();
				addtextFields_commonMethod(application, "Interface", "interfacename_textfield", multilink_interfacename, xml);
				
				//verify EIP Allocation
				
				click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
				{
					compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
					GetText(application, "Subnet Type", "subnettype_valueMultilink");
					SelectDropdownValueUnderSelectTag(application, "City", eipallocation_city, "eipallocation_citydropdown", xml);
					SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_subnetsize, "eipallocation_subnetsize", xml);
					
					GetText(application, "Available Pools", "eipallocation_availablepools_value");
					GetText(application, "Allocate subnet button", "allocatesubnet_button");
					
					click_commonMethod(application, "Close", "closesymbol", xml);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
				}
				

				//verify getaddress
				if(getaddress.equalsIgnoreCase("yes"))
				{
					click_commonMethod(application, "Get Address", "getaddress1_button", xml);
					if(getwebelement(xml.getlocator("//locators/" + application + "/interfacerange_Address_dropdown")).isDisplayed()) 
					{
					SelectDropdownValueUnderSelectTag(application, "Interface Address Range", interfaceaddressrange_value, "interfacerange_Address_dropdown", xml);

					click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
					}
					else
					{
					addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", interfaceaddressrange_value, xml);
					
					click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
					}
				}
				GetText(application, "Network", "networkipv6_fieldvalueMutilink");

				//verify EIP Allocation
				
				ScrolltoElement(application, "interfacerange_AddressIpv6_dropdown", xml);
				ScrolltoElement(application, "interfaceaddressrange_textfield", xml);

				
				click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
				{
					GetText(application, "EIP Address Allocation header", "eipallocationIPv6_header");
					GetText(application, "Subnet Type", "subnettype_valueMultilink");
					GetText(application, "Space Name", "eipallocation_spacename");
					SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_ipv6_subnetsize, "eipallocation_ipv6_subnetsize", xml);
					GetText(application, "Available Blocks", "availableblocks_dropdown");
					
					click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
					
					click_commonMethod(application, "Close", "closesymbol", xml);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
				}
				

				//verify getaddress ipv6
				if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
				{
					click_commonMethod(application, "Get Address", "getaddress2_button", xml);
					if(getwebelement(xml.getlocator("//locators/" + application + "/interfacerange_AddressIpv6_dropdownMultilink")).isDisplayed()) 
					{
					SelectDropdownValueUnderSelectTag(application, "Interface Address Range IPv6", interfaceaddressrange_value, "interfacerange_AddressIpv6_dropdown", xml);

					}
					else
					{
					addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
					
					click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

					}
				}
				
				addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
				
				SelectDropdownValueUnderSelectTag(application, "Encapsulation", Multilink_Encapsulation_Value, "encapsulation_dropdown", xml);
				ScrolltoElement(application, "link_textfield", xml);
				addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", unitid_value, xml);
				addtextFields_commonMethod(application, "Slot", "slot_textfield", slot_value, xml);
				addtextFields_commonMethod(application, "Pic", "pic_textfield", pic_value, xml);
				addtextFields_commonMethod(application, "Port", "port_textfield", port_value, xml);
				
				addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", multilink_bgpcheckbox, "no", xml);
				if(multilink_bgpcheckbox.equalsIgnoreCase("yes"))
				{
					SelectDropdownValueUnderSelectTag(application, "BGP Templates Generate For", bgptemplate_dropdownvalue, "multilink_bgptemplate_dropdown", xml);
					addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
					addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
					addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
					addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
					addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
				}

				//Multilinked Bearers table
				scrolltoend();
				compareText(application, "Multilinked Bearers", "multilinkedbearers_header", "Multilink Bearers", xml);

				//table columns
				compareText(application, "Check to add Interface", "checktoaddinterface_column", "Check to add to Multilink", xml);
				compareText(application, "Interface", "multilink_interface_column", "Interface", xml);
				compareText(application, "Link/Circuit", "multilink_link_column", "Link / Circuit Id", xml);
				compareText(application, "Bearer Type", "multilink_BearerType_column", "Bearer Type", xml);
				compareText(application, "VLAN Id", "multilink_vlanid_column", "VLAN Id", xml);
				
				String MultilinkBearer_ExistingInterface= getwebelement(xml.getlocator("//locators/" + application + "/multilinkbearer_tabledata")).getAttribute("style");
				
				if(!MultilinkBearer_ExistingInterface.contains("height: 1px"))
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Existing interface details are displaying in multilink bearer table");
					if(checktoaddinterface_checkbox.equalsIgnoreCase("yes"))
					{
						if(name.contains("Cisco"))
						{
							WebElement CheckToAddInterface= getwebelement(xml.getlocator("//locators/" + application + "/checktoaddinterface").replace("value", interfacename));
							Clickon(CheckToAddInterface);
						}
						else
						{
							WebElement CheckToAddInterface= getwebelement(xml.getlocator("//locators/" + application + "/checktoaddinterface").replace("value", Edit_JuniperInterfaceNameValue));
							Clickon(CheckToAddInterface);
						}
					}
					
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "No existing interfaces to display");
				}
				scrolltoend();
				//configuration panel in add interface page
				if(multilink_configureinterface_checkbox.equalsIgnoreCase("yes") || multilink_configureinterface_checkbox.equalsIgnoreCase("null"))
				{
					compareText(application, "Configuration", "configuration_header", "Configuration", xml);
					click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
					
					GetText(application, "Configuration", "configuration_textarea");
					
					click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
					
					click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
				}
				else
				{
					scrolltoendOptional();
					click_commonMethod(application, "OK", "okbutton", xml);
				}
				
				waitforPagetobeenable();
//				compareText(application, "Multilink Added success message", "AddMultilinkSucessMessage", "Interface successfully created.", xml);
				verifysuccessmessage(application, "Interface successfully created.");
				click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				
			
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Multilink' page not navigated");
					System.out.println("'Add Multilink' page not navigated");
					}

	}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
	System.out.println("'View PE Device' page not navigated");
	}

	}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
	sa.assertAll();
}







public void deleteInterface_PE1(String application, String interfacename, String name, String editDevicename) throws InterruptedException, DocumentException {
	//Delete Interface
	ScrolltoElement(application, "portalaccess_header", xml);
	scrolltoend();
	//click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
	
	WebElement SelectInterface1= driver.findElement(By.xpath("//div[@role='gridcell' and @col-id='interfaceName']/div[text()='"+interfacename+"']"));
	if(SelectInterface1.isDisplayed())
	{
		Clickon(SelectInterface1);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
		
		
		if(driver.findElement(By.xpath("//div[div[text()='"+interfacename+"']]/parent::div//span[text()='Delete']")).isDisplayed())
		{
			WebElement InterfaceDeleteButtonLink= driver.findElement(By.xpath("//div[div[text()='"+interfacename+"']]/parent::div//span[text()='Delete']"));
			Clickon(InterfaceDeleteButtonLink);
			
			click(application, "Delete Button", "deletebutton", xml);
			compareText(application, "Delete Interface Sucess Message ", "DeleteInterfaceSucessMessage", "Interface successfully removed from this service.", xml);
			
		}else{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Delete button is not displaying for interface");	
			System.out.println("Delete button is not displaying for interface");
			}
	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.PASS, "Expected Interface is not available in grid");
		System.out.println("Expected Interface is not available in grid");
	}
}




public void deleteInterface_PE2(String application, String interfacename, String name, String editDevicename, String vendormodel) throws InterruptedException, DocumentException {
	//Delete Interface
	
	ScrolltoElement(application, "portalaccess_header", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
	{
	click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
	}
	
	if(vendormodel.contains("Cisco"))
	{
		WebElement CiscoInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", interfacename));
		if(CiscoInterface.isDisplayed())
		{
			Clickon(CiscoInterface);
		}
	}
		else
		{
			WebElement JuniperInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", JuniperInterfaceNameValue));
			if(JuniperInterface.isDisplayed())
			{
			Clickon(JuniperInterface);
			}
		}
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", name)).isDisplayed())
		{
			WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", name));
			Clickon(AddedDevice_Interface_Actiondropdown);
		}
		else if(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", editDevicename)).isDisplayed())
		{
			WebElement EditDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", editDevicename));
			Clickon(EditDevice_Interface_Actiondropdown);	
		}

		click_commonMethod(application, "Delete", "delete", xml);
		
		
		Alert alert = driver.switchTo().alert();       
		  
		  // Capturing alert message.    
		    String alertMessage= driver.switchTo().alert().getText();
		    if(alertMessage.isEmpty()) {
		       ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
		          System.out.println("No Message displays"); 
		    }else {
		       ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
		          System.out.println("text message for alert displays as: "+alertMessage);
		    }
		  
		  try {  
		    alert.accept();
		    
		  }catch(Exception e) {
		     e.printStackTrace();
		  } 
		verifysuccessmessage(application, "Interface successfully deleted.");

}

public void delete_MultilinkInterface(String application, String multilink_interfacename, String name, String editDevicename, String vendormodel) throws InterruptedException, DocumentException {
	//Delete Interface
	
	ScrolltoElement(application, "portalaccess_header", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
	{
	click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
	}
	
	if(vendormodel.contains("Cisco"))
	{
		WebElement CiscoMultilinkInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", Cisco_Multilink_InterfaceName));
		if(CiscoMultilinkInterface.isDisplayed())
		{
			Clickon(CiscoMultilinkInterface);
		}
	}
	else
	{
		WebElement JuniperMultilinkInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", Juniper_Multilink_InterfaceName));
		if(JuniperMultilinkInterface.isDisplayed())
		{
			Clickon(JuniperMultilinkInterface);
		}
	}
	
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", name)).isDisplayed())
		{
			WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", name));
			Clickon(AddedDevice_Interface_Actiondropdown);
		}
		else if(getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", editDevicename)).isDisplayed())
		{
			WebElement EditDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", editDevicename));
			Clickon(EditDevice_Interface_Actiondropdown);	
		}

		click_commonMethod(application, "Delete", "delete", xml);
		
		
		Alert alert = driver.switchTo().alert();       
		  
		  // Capturing alert message.    
		    String alertMessage= driver.switchTo().alert().getText();
		    if(alertMessage.isEmpty()) {
		       ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
		          System.out.println("No Message displays"); 
		    }else {
		       ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
		          System.out.println("text message for alert displays as: "+alertMessage);
		    }
		  
		  try {  
		    alert.accept();
		    
		  }catch(Exception e) {
		     e.printStackTrace();
		  } 
		verifysuccessmessage(application, "Interface successfully deleted.");

}

public void verifyInterfaceConfigHistory(String application, String ServiceIdentification , String DeviceName, String vendormodel) throws InterruptedException, DocumentException, IOException {
	if(vendormodel.contains("Juniper"))
	{
		searchorder(application, ServiceIdentification);
		navigateToViewDevicePage(application, DeviceName);
	ScrolltoElement(application, "interfaces_header", xml);
	
	compareText(application, "Interface Configuration History", "interfaceconfighistory_header", "Interface Configuration History", xml);
	compareText(application, "Date column", "date_column", "Date", xml);
	compareText(application, "File Name column", "filename_column", "File Name", xml);
	
	//**click_commonMethod(application, "Back", "viewpage_backbutton", xml);
	}
	else
	{
		scrolltoend();
		//**click_commonMethod(application, "Back", "viewpage_backbutton", xml);	
	}

}
public void selectInterfacelinkforEquipment(String application, String devicename) throws InterruptedException, DocumentException {
	waitforPagetobeenable();

	ScrolltoElement(application, "portalaccess_header", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		List<WebElement> addeddevicesList= driver.findElements(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b"));
		System.out.println(addeddevicesList);
		int AddedDevicesCount= addeddevicesList.size();
		for(int i=0;i<AddedDevicesCount;i++) {
			String AddedDeviceNameText= addeddevicesList.get(i).getText();
			String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
		
			if(AddedDeviceNameText.contains(devicename)){
		WebElement AddedDevice_SelectInterfacesLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='Select Interfaces']"));
		Clickon(AddedDevice_SelectInterfacesLink);								   //div[div[div[text()='Provider Equipment (PE)']]]//div[div[b[contains(text(),'"+AddedDevice_SNo+"')]]]//span[text()='Select Interfaces']
		System.out.println("Select Interfaces link for PE is selected");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Select Interface link has been clicked for PE device under Provider Equipment Panel");
		
		scrollToTop();
		waitforPagetobeenable();
		compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);
		}
	}
	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
	
	
}




public void SelectInterfacetoremovefromservice(String application, String interfacename)
		throws IOException, InterruptedException, DocumentException {

	ScrolltoElement(application, "labelname_managementAddresss", xml);
	

	selectRowforInterfaceInService(application, interfacename);
	compareText(application, "Interface in Select Remove message", "InterfaceInSelect_RemoveSuccessMessage", "Interface removed Successfully", xml);
}



public void selectRowforInterfaceInService(String application, String interfacenumber)
		throws IOException, InterruptedException, DocumentException {

	int TotalPages;

	String TextKeyword = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_totalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in table is: " + TotalPages);

	ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div//div[div[text()='" +interfacenumber+"']]//span[span[@class='ag-icon ag-icon-checkbox-unchecked']]"));
				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceToselect(application);

				}else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Interface in Service' table");
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_Actiondropdown")));
								

								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_removebuton")));
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " has been selected to get removed from service");
							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div//div[div[text()='" +interfacenumber+"']]//span[span[@class='ag-icon ag-icon-checkbox-unchecked']]"));
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

			System.out.println("No values available in table");
			Log.info("No values available inside the InterfaceInService table");
		}

}





public void PageNavigation_NextPageForInterfaceToselect_old(String Application)
		throws InterruptedException, DocumentException {

	Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_nextpage")));
	

}


public void selectrowforInterfaceToselecttable(String Application, String interfacenumber)
		throws IOException, InterruptedException, DocumentException {

	scrolltoend();
	int TotalPages;

	String TextKeyword = Gettext(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_totalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in Interface to select table is: " + TotalPages);

	ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div//div[div[text()='"+interfacenumber+"']]//span[span[@class='ag-icon ag-icon-checkbox-unchecked']]");
				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceToselect(Application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Interface to select' table");
								waitforPagetobeenable();
								Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_Actiondropdown")));

								waitforPagetobeenable();

								Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_addbuton")));
								
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is added to service");


							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div//div[div[text()='"+interfacenumber+"']]//span[span[@class='ag-icon ag-icon-checkbox-unchecked']]"));
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

			System.out.println("No values found inside the table");
			Log.info("No values available inside the Interfacetoselect table");
		}

}

public void verifyDefaultSelection_connectivityprotocol_ssh(String application) throws InterruptedException, DocumentException {
	boolean conectivityProtocolssh_availability=false;
	boolean conectivityProtocolssh_Selection=false;
	try {	
		conectivityProtocolssh_availability=getwebelement(xml.getlocator("//locators/" + application + "/sshradiobutton")).isDisplayed();
		if(conectivityProtocolssh_availability) {

			ExtentTestManager.getTest().log(LogStatus.PASS, " 'SSH' is displaying under 'Connectivity protocol' as expected");
			System.out.println(" 'SSH' is displaying under 'Connectivity protocol' as expected");

			conectivityProtocolssh_Selection=getwebelement(xml.getlocator("//locators/" + application + "/sshradiobutton")).isSelected();
			if(conectivityProtocolssh_Selection) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'SSH' is selected under 'Connectivity protocol' by default as expected");
				System.out.println(" 'SSH' is selected under 'Connectivity protocol' as expected");

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'SSH' is not selected by default under 'Connectivity protocol'");
				System.out.println(" 'SSH' is not selected under 'Connectivity protocol'");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'SSH' is not displaying under 'Connectivity protocol'");
			System.out.println(" 'SSH' is not displaying under 'Connectivity protocol'");
		}

	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'SSH' is not displaying under 'Connectivity protocol'");
		System.out.println(" 'SSH' is not displaying under 'Connectivity protocol'");

	}catch(Exception ee) {
		ee.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'SSH' is not selected under 'Connectivity protocol'");
		System.out.println(" 'SSH' is not selected under 'Connectivity protocol'");

	}
}


public void verifyDefaultSelection_connectivityprotocol_telnet(String application) throws InterruptedException, DocumentException {
	boolean conectivityProtocoltelnet_availability=false;
	boolean conectivityProtocoltelnet_Selection=false;
	try {	
		conectivityProtocoltelnet_availability=getwebelement(xml.getlocator("//locators/" + application + "/telnetradiobutton")).isDisplayed();
		if(conectivityProtocoltelnet_availability) {

			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Telnet' is displaying under 'Connectivity protocol' as expected");
			System.out.println(" 'Telnet' is displaying under 'Connectivity protocol' as expected");

			conectivityProtocoltelnet_Selection=getwebelement(xml.getlocator("//locators/" + application + "/telnetradiobutton")).isSelected();
			if(conectivityProtocoltelnet_Selection) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Telnet' is selected under 'Connectivity protocol' by default");
				System.out.println(" 'Telnet' is selected under 'Connectivity protocol'");

			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Telnet' is not selected under 'Connectivity protocol' by default as expected");
				System.out.println(" 'Telnet' is not selected under 'Connectivity protocol'");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Telnet' is not displaying under 'Connectivity protocol'");
			System.out.println(" 'Telnet' is not displaying under 'Connectivity protocol'");
		}


	}catch(Exception ee) {
		ee.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Telnet' is not displaying under 'Connectivity protocol'");
		System.out.println(" 'Telnet' is not displaying under 'Connectivity protocol'");

	}
}

public void edittextFields_SNMPversion(String application, String labelname, String xpathname, String expectedValueToEdit) throws InterruptedException, DocumentException, IOException {
	boolean availability=false;
	try {	
		availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).isDisplayed();
		if(availability) {
			ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is displaying as expected");
			System.out.println(labelname + " text field is displaying as expected");

			String actualvalueInsidetextfield=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");

			if(actualvalueInsidetextfield.isEmpty()) {

				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname+" text field");
				System.out.println("No values displaying under "+labelname+" text field");

				if(expectedValueToEdit.equalsIgnoreCase("null")) {
					//ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is not edited as expected");
					System.out.println(labelname + " text field is not edited as expected");
				}else {

					getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).clear();
					

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
					

					String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field value is edited as: "+ actualvalue);
				}

			}else {

				ExtentTestManager.getTest().log(LogStatus.PASS, "Value displaying under "+labelname + " field is: "+actualvalueInsidetextfield);
				System.out.println("Value displaying under "+labelname + " field is: "+actualvalueInsidetextfield);

				if(expectedValueToEdit.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is not edited as expected");
					System.out.println(labelname + " text field is not edited as expected");
				}else {

					getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).clear();
					

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
					

					String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field value is edited as: "+ actualvalue);
				}
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " text field is not displaying");
			System.out.println(labelname + " text field is not displaying");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " text field is not displaying");
		System.out.println(labelname + " text field is not displaying");
	}catch(Exception ee) {
		ee.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under "+ labelname + " text field");
		System.out.println(" Not able to enter value under "+ labelname + " text field");
	}
}


public void Clickon_addToggleButton(String application, String xpath) throws InterruptedException, DocumentException {

	//Add Toggle button
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
	waitforPagetobeenable();
}

public void executeCommandAndFetchTheValue(String application, String executeButton) throws InterruptedException, DocumentException {

	click_commonMethod(application, "Execute", executeButton, xml);

	boolean remarkField=false;
	try {	
		remarkField=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).isDisplayed();
		if(remarkField) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Result' text field is displaying");
			System.out.println( "'Result' text field is displaying");

			String resultvalue=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "value under 'Result' field displaying as "+ resultvalue);
			System.out.println("value under 'Result' field displaying as "+ resultvalue);

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
			System.out.println( "'Result' text field is not displaying");
		}
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Result' text field is not displaying");
		System.out.println("'Result' text field is not displaying");
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
			System.out.println("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
		}
	}catch(Exception e) {
		e.printStackTrace();

		ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
		System.out.println("'Hostname or IpAddress' for 'Ipv4' text field is not displaying for "+ command_ipv4);
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
				System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
		}

	}else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv4' text field is not displaying for "+ command_ipv4);
		System.out.println("'VRF Name IPv4' text field is not displaying for "+ command_ipv4 +" command");
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
			System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
		}
	}catch(Exception e) {
		e.printStackTrace();

		ExtentTestManager.getTest().log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
		System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
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
				System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
		}
	}
	else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'VRF Name IPv6' text field is not displaying for "+ commandIPV6);
		System.out.println("'VRF Name IPv6' text field is not displaying for "+ commandIPV6 +" command");
	}

}	


public void deleteSevice1(String application, String ServiceIdentification) throws InterruptedException, DocumentException	{

	try {
	//Delete Service
	scrolltoview(application, "order panel header", "orderpanelheader", xml);
	
	click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
	click_commonMethod(application, "Delete", "delete", xml);
	
	
	Alert alert = driver.switchTo().alert();       
	  
	  // Capturing alert message.    
	    String alertMessage= driver.switchTo().alert().getText();
	    if(alertMessage.isEmpty()) {
	       ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
	          System.out.println("No Message displays"); 
	    }else {
	       ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
	          System.out.println("text message for alert displays as: "+alertMessage);
	    }
	  
	  try {  
	    alert.accept();
	    
	  }catch(Exception e) {
	     e.printStackTrace();
	  } 
	compareText(application, "Service Delete success msg", "deletesuccessmsg", "Service successfully deleted.", xml);
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
sa.assertAll();
}





//======================================  Common Methods  ===========================================

public static Boolean isFileDownloaded(String fileName, String downloadspath) {
	boolean flag = false;
	//paste your directory path below
	//eg: C:\\Users\\username\\Downloads
	String dirPath = downloadspath; 
	File dir = new File(dirPath);
	File[] files = dir.listFiles();
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
	return flag;
}

public void searchorder(String application, String sid) throws InterruptedException, DocumentException, IOException {
	Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));
	SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
	
	WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
	ScrolltoElement(application, "searchbutton", xml);
	Clickon(searchbutton);
	
	scrolltoend();
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
	
}


public void cleartext(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
	WebElement element= null;
	try {
		
		element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		String value= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
		if(element==null)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
		}
		else if(value!=null) {
			
			element.clear();	
		}			
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void isDisplayed(String application, String xpath, String labelname) {
	boolean availability = false;

	try {
		
		availability= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		System.out.println(availability);
		if (availability) {
			
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
		
		element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")); 
		String ele = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getAttribute("value");
		if(element==null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' is not found");
			System.out.println("Step : '"+ labelname +"' is not found");
		}
		else if (ele!=null && ele.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
			System.out.println( "Step : '"+ labelname +"' value is empty");
		}
		else {   

			text = element.getText();
			ExtentTestManager.getTest().log(LogStatus.PASS,"Step: '"+ labelname +"' value is displayed as : '"+text+"'");
			System.out.println("Step: '"+ labelname +"' value is displayed as : '"+text+"'");

		}
	}catch (Exception e) {
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Step: '"+ labelname +"' value is not displaying");
		System.out.println("Step: '"+ labelname +"' value is not displaying");
		e.printStackTrace();
	}
	return text;

}


public void clickOnBankPage() {
	driver.findElement(By.xpath("//body")).click();
}

public void writetoexcel(String filepath, String sheetname, int columnnumber, String inputvalue) throws IOException{

	File file= new File(filepath);
	FileInputStream fis= new FileInputStream(file);
	XSSFWorkbook work= new XSSFWorkbook(fis);
	XSSFSheet sh= work.getSheet(sheetname);
	sh.getRow(1).createCell(columnnumber).setCellValue(inputvalue);
	fis.close();
	FileOutputStream fos= new FileOutputStream(file);
	work.write(fos);
	work.close();
	fis.close();

}


public void verifysuccessmessage(String application, String expected) throws InterruptedException {
	
	scrollToTop();
	
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
		Log.info("failure in fetching success message - 'Service created Successfully'  ");
		ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
		System.out.println(expected+ " message is not getting dislpayed");
		successScreenshot(application);
	}

}




/**
 * 	clicking on breadcrump for navigation
 * @param application
 * @throws DocumentException 
 * @throws InterruptedException 
 */
	public void clickOnBreadCrump(String application, String breadCrumpLink) throws InterruptedException, DocumentException {
		
		scrollToTop();
		
		WebElement breadcrumb=null;

		try {
		breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
		if(breadcrumb.isDisplayed()) {
			click_commonMethod_PassingWebelementDirectly(application, "Breadcrump", "breadcrump", xml);
		}else {
			System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
		}
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
	}
		
		
	}

	

public void scrollToBottomAndClickOnBackButton(String application, String BackButtonxpath) throws InterruptedException, DocumentException {
	scrolltoend();
	click_commonMethod(application, "Back Button", "BackButtonxpath", xml);
}




public void navigateToViewDevicePage(String application, String PE_DeviceName) throws InterruptedException, DocumentException {
	try {
	ScrolltoElement(application, "ConfigurationOptions_Header", xml);//ProviderEquipment_header //PE_AddPEDeviceLink
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		click_commonMethod(application, "View Link", "viewservicepage_viewdevicelink1", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
			System.out.println("'View PE Device' page not navigated");
		}
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
	
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}
}


public void navigateToViewDevicePage_PE(String application, String PE_DeviceName) throws InterruptedException, DocumentException {
	try {
	ScrolltoElement(application, "ProviderEquipment_header", xml);//ProviderEquipment_header //managementOptionsPanelheader//CustomerPremiseEquipment_CPE_Panelheader
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingPEdevicegrid")).isDisplayed())
	{
		//**WebElement AddedDevice_ViewLink=getwebelement("//div[div[b[text()='"+PE_DeviceName+"']]]//span[text()='View']");
		//**Clickon(AddedDevice_ViewLink);
		click_commonMethod(application, "View Link", "PE_viewdevice1", xml);
		compareText(application, "View device header", "PE_ViewDevice_Header", "Device", xml);
			
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
	
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed");
		System.out.println(  e+ " : Field is not displayed");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed");
		System.out.println(  e+" : Field is not displayed");
	}

	
}




//Add/Edit/Delete Routes
public void addRouteFunction_PE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCity, 
String PE_RouteSubnetSize, String	PE_Gateway,String	PE_NetworkAddress, String	PE_NetworkMAS, String	PE_Metrics
) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Route Functionality");
ScrolltoElement(application, "PE_CPE_Router_IPV4Command_Executebutton", xml);//interfaces_header//RouterTool_header//PE_CPE_Router_IPV4Command_Executebutton

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
click_commonMethod(application, "Add Link", "Routes_AddLink", xml);
compareText(application, "Add Route header", "Routes_AddRoutes_Header", "Add Route", xml);
click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
	
	GetText(application, "Subnet Type", "CPE_Routes_EIPAllocation_SubnetTypeValue");
	click_commonMethod(application, "Allocate Subnet Button", "Routes_AllocateSubnetButon", xml);
	
	warningMessage_commonMethod(application, "Routes_EIPAllocation_CityWarningMessage", "City  warning message", xml);
	warningMessage_commonMethod(application, "Routes_EIPAllocation_SubnetSizeWarningMessage", "Subnet Size  warning message", xml);
	warningMessage_commonMethod(application, "Routes_EIPAllocation_AvilablePoolWarningMessage", "Avilable Pool  warning message", xml);
	
	SelectDropdownValueUnderSpanTag(application, "City dropdown", PE_RouteCity, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueSpanTag", xml);
	SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", PE_RouteSubnetSize, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);
	
	GetText(application, "Available Pool Message", "Routes_EIPAllocation_AvailablePoolsMessage");
	click_commonMethod(application, "Close EIP Allocation window", "Routes_EIPAllocation_CloseButton", xml);
}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Subnet Allocation for Service popup is not displaying");
}

click_commonMethod(application, "OK Button", "Routes_OKButton", xml);
warningMessage_commonMethod(application, "Routes_Gatway_WarningMessage", "Getway", xml);
warningMessage_commonMethod(application, "Routes_NetworkAddress_WarningMessage", "Network Address", xml);
warningMessage_commonMethod(application, "Routes_NetworkMASK_WarningMessage", "Network MAS", xml);

addtextFields_commonMethod(application, "Getway", "Routes_Gatewaytextfield", PE_Gateway, xml);
addtextFields_commonMethod(application, "Network Address", "Routes_NetworkAddresstextfield", PE_NetworkAddress, xml);
addtextFields_commonMethod(application, "Network MAS", "Routes_NetworkMAStextfield", PE_NetworkMAS, xml);
addtextFields_commonMethod(application, "Metrics", "Routes_Metricstextfield", PE_Metrics, xml);

click_commonMethod(application, "OK Button", "Routes_OKButton", xml);
//compareText(application, "Add Route Success Message", "AddRouteSuccessMessage", "Static Route successfully created.", xml);
verifysuccessmessage(application, "Static Route successfully created.");

}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
}

sa.assertAll();
}





public void editRouteFunction_PE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
String PE_RouteSubnetSizeEdit, String	PE_GatewayEdit,String	PE_NetworkAddressEdit, String	PE_NetworkMASEdit, String	PE_MetricsEdit
) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edit Route Functionality");
	ScrolltoElement(application, "PE_CPE_Router_IPV4Command_Executebutton", xml);//interfaces_header//RouterTool_header//Routes_Header//PE_CPE_Router_IPV4Command_Executebutton

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
click_commonMethod(application, "Edit Link", "Routes_EditLink", xml);
compareText(application, "Edit Route header", "Routes_EditRoutes_Header", "Edit Route", xml);
click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
	
	GetText(application, "Subnet Type", "CPE_Routes_EIPAllocation_SubnetTypeValue");
	//SelectDropdownValueUnderSpanTag(application, "City dropdown", PE_RouteCityEdit, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueSpanTag", xml);
	SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", PE_RouteSubnetSizeEdit, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);
	
	
	GetText(application, "Available Pool Message", "Routes_EIPAllocation_AvailablePoolsMessage");
	compareText(application, "Allocate Subnet Button", "Routes_AllocateSubnetButon", "Allocate Subnet", xml);
	click_commonMethod(application, "Close EIP Allocation window", "EditRoutes_EIPAllocation_CloseButton", xml);

}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Subnet Allocation for Service popup is not displaying");
}

ClearAndEnterTextValue(application, "Getway", "Routes_Gatewaytextfield", PE_GatewayEdit, xml);
ClearAndEnterTextValue(application, "Network Address", "Routes_NetworkAddresstextfield", PE_NetworkAddressEdit, xml);
ClearAndEnterTextValue(application, "Network MAS", "Routes_NetworkMAStextfield", PE_NetworkMASEdit, xml);
ClearAndEnterTextValue(application, "Metrics", "Routes_Metricstextfield", PE_MetricsEdit, xml);

click_commonMethod(application, "OK Button", "Routes_OKButton", xml);
scrollToTop();
//compareText(application, "Add Route Success Message", "UpdateRouteSuccessMessage", "Static Route successfully updated.", xml);
verifysuccessmessage(application, "Static Route successfully updated.");


}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
}

}








public void deleteRouteFunction_PE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
String PE_RouteSubnetSizeEdit, String	PE_GatewayEdit,String	PE_NetworkAddressEdit, String	PE_NetworkMASEdit, String	PE_MetricsEdit
) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Delete Route Functionality");
	ScrolltoElement(application, "PE_CPE_Router_IPV4Command_Executebutton", xml);//interfaces_header//RouterTool_header//PE_CPE_Router_IPV4Command_Executebutton

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
	
click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
click_commonMethod(application, "Delete Link", "Routes_DeleteLink", xml);


Alert alert = driver.switchTo().alert();


// Capturing alert message.    
//   String alertMessage= driver.switchTo().alert().getText();
//   if(alertMessage.isEmpty()) {
//	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No Message displays under Alert popup");
//       System.out.println("No Message displays under Alert popup"); 
//   }else {
//	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
//       System.out.println("text message for alert displays as: "+alertMessage);
//   }
 
GetMessageFromAlertJavaScriptPopup();

 try {  
   AcceptJavaScriptMethod();//CancelJavaScriptMethod();
//	compareText(application, "Delete Route Success Message", "DeleteRouteSuccessMessage", "Static Route successfully deleted.", xml);
	verifysuccessmessage(application, "Static Route successfully deleted.");
 }catch(Exception e) {
	 e.printStackTrace();
 }

}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
}

}





			/// Select Interface
public void selectInterfacelinkforDevice_Old(String application, String name) throws InterruptedException, DocumentException {
	
	//ScrolltoElement(application, "portalaccess_header", xml);
	scrolltoend();
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "check 'Select Interface' link");
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		click_commonMethod(application, "Select Interface", "PE_SelectInterfacesdevice1", xml);
	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
				
}





public void selectInterfacelinkforDevice2(String application, String name) throws InterruptedException, DocumentException {
	
	//ScrolltoElement(application, "portalaccess_header", xml);
	scrolltoend();
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "check 'Select Interface' link");
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevices_list"));
		System.out.println(addeddevicesList);
		int AddedDevicesCount= addeddevicesList.size();
		for(int i=0;i<AddedDevicesCount;i++) {
			String AddedDeviceNameText= addeddevicesList.get(i).getText();
			String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
			if(AddedDeviceNameText.contains(name))
			{
				WebElement AddedDevice_SelectInterfacesLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_selectinterfaceslink").replace("value", AddedDevice_SNo));
				Clickon(AddedDevice_SelectInterfacesLink);
				waitforPagetobeenable();
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

public void SelectInterfacetoremovefromservice_Old(String application,String InterfaceName,	String Edit_InterfaceName, String vendormodel)
		throws IOException, InterruptedException, DocumentException {

	ScrolltoElement(application, "viewpage_vendormodel", xml);
	

	if(vendormodel.contains("Cisco"))
	{
	Cisco_selectRowforInterfaceInService(application, InterfaceName);
	}
	else
	{
		Juniper_selectRowforInterfaceInService(application,InterfaceName, Edit_InterfaceName);
	}

}




public void SelectInterfacetoaddwithservcie_Old(String application, String interfacename, String vendormodel)
		throws InterruptedException, DocumentException, IOException {

	scrolltoend();
	

	if(vendormodel.contains("Cisco"))
	{
		Cisco_selectrowforInterfaceToselecttable(application, interfacename);
	}
	else
	{
		Juniper_selectrowforInterfaceToselecttable(application);
	}
	click_commonMethod(application, "Back", "viewpage_backbutton", xml);
}






public void deleteService2(String application) throws InterruptedException, DocumentException	{

	ScrolltoElement(application, "orderpanelheader", xml);
	//Delete Service
	click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
	click_commonMethod(application, "Delete", "delete", xml);
	
	
	Alert alert = driver.switchTo().alert();       
	  
	  // Capturing alert message.    
	    String alertMessage= driver.switchTo().alert().getText();
	    if(alertMessage.isEmpty()) {
	       ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
	          System.out.println("No Message displays"); 
	    }else {
	       ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
	          System.out.println("text message for alert displays as: "+alertMessage);
	    }
	  
	  try {  
	    alert.accept();
	    
	  }catch(Exception e) {
	     e.printStackTrace();
	  } 
	  
	  verifysuccessmessage(application, "Service successfully deleted");
	
}


public void addDropdownValues_Clickondropdown(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml)
			throws InterruptedException, DocumentException {
		  boolean availability=false;
		  List<String> ls = new ArrayList<String>();
		  
		try {  

		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
			  System.out.println(labelname + " dropdown is displaying");
			  
			  if(expectedValueToAdd.equalsIgnoreCase("null")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
				  System.out.println(" No values selected under "+ labelname + " dropdown");
			  }else {
				  
				  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[contains(@class,'react-dropdown-select-content')]"));
				  
				  
				  //verify list of values inside dropdown
				  List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
				  System.out.println( " List of values inside "+ labelname + " dropdown is:  ");
				  
					for (WebElement valuetypes : listofvalues) {
								Log.info("service sub types : " + valuetypes.getText());
								 ls.add(valuetypes.getText());
					}
					
					
					    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
			            System.out.println("list of values inside "+labelname+" dropdown is: "+ls);
					
					
				SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
				
				  Clickon(getwebelement("(//div[label[text()='"+ labelname +"']]//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
				  
				  
				  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
				  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
				  
			  }
		  }else {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
			  System.out.println(labelname + " is not displaying");
		  }
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " is not displaying");
			  System.out.println(labelname + " is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under "+ labelname + " dropdown");
			System.out.println(" NO value selected under "+ labelname + " dropdown");
		}
		
	}
	













					// Select Interface

public void selectInterfacelinkforDevice(String application, String name) throws InterruptedException, DocumentException {
	scrolltoend();
	//ScrolltoElement(application, "providerequipment_header", xml);
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "check 'Select Interface' link");
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevices_list"));
		System.out.println(addeddevicesList);
		int AddedDevicesCount= addeddevicesList.size();
		for(int i=0;i<AddedDevicesCount;i++) {
			String AddedDeviceNameText= addeddevicesList.get(i).getText();
			String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
			if(AddedDeviceNameText.contains(name))
			{
				WebElement AddedDevice_SelectInterfacesLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_selectinterfaceslink").replace("value", AddedDevice_SNo));
				Clickon(AddedDevice_SelectInterfacesLink);
				
				waitforPagetobeenable();
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

public void SelectInterfacetoremovefromservice(String application, String InterfaceName,	String Edit_InterfaceName)
		throws IOException, InterruptedException, DocumentException {

	ScrolltoElement(application, "viewpage_premise", xml);
	
	click_commonMethod(application, "Interfaces in Service Filter", "interfacesinservice_filter", xml);
	if(Edit_InterfaceName.equalsIgnoreCase("null")) {
	addtextFields_commonMethod(application, "Interface in Service search", "interfaceinservice_fitertext", InterfaceName, xml);
	WebElement InterfaceName_GridSelect= getwebelement(xml.getlocator("//locators/" + application + "/interfaceinservice_gridselect").replace("value", InterfaceName));
	Clickon(InterfaceName_GridSelect);
	ExtentTestManager.getTest().log(LogStatus.PASS, InterfaceName + " is selected under 'Interface in service' table");
	}
	else
	{
		addtextFields_commonMethod(application, "Interface in Service search", "interfaceinservice_fitertext", Edit_InterfaceName, xml);
		WebElement InterfaceName_GridSelect= getwebelement(xml.getlocator("//locators/" + application + "/interfaceinservice_gridselect").replace("value", Edit_InterfaceName));
		Clickon(InterfaceName_GridSelect);
		ExtentTestManager.getTest().log(LogStatus.PASS, Edit_InterfaceName + " is selected under 'Interface in service' table");
	}
	

	ScrolltoElement(application, "viewpage_premise", xml);
	
	click_commonMethod(application, "Action Dropdown", "InterfaceInselect_Actiondropdown", xml);
	
	click_commonMethod(application, "Remove", "InterfaceInselect_removebuton", xml);
	
	waitforPagetobeenable();
//	compareText(application, "Remove Interface Success Message", "RemoveSelectInterfaceSuccessMessage", "Interface removed Successfully", xml);
	verifysuccessmessage(application, "Interface removed Successfully");
}

public void Juniper_selectRowforInterfaceInService(String application, String InterfaceName,	String Edit_InterfaceName)
		throws IOException, InterruptedException, DocumentException {

	//String Edit_JuniperInterfaceNameValue= "e3-4468/2799/3456:4:38962.33256";
	int TotalPages;

	String TextKeyword = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_totalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in table is: " + TotalPages);

	ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", InterfaceName));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceToselect(application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS, InterfaceName + " is selected under 'Interface in Service' table");
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + application + "/InterfaceInselect_Actiondropdown")));

								

								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_removebuton")));
								ExtentTestManager.getTest().log(LogStatus.PASS, InterfaceName + " has been selected to get removed from service");

							}

						} catch (StaleElementReferenceException e) {
							results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", InterfaceName));
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

			System.out.println("No values available in table");
			Log.info("No values available inside the InterfaceInService table");
		}

}

public void Cisco_selectRowforInterfaceInService(String application, String interfacename)
		throws IOException, InterruptedException, DocumentException {

	int TotalPages;

	String TextKeyword = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_totalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in table is: " + TotalPages);

	ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", interfacename));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceToselect(application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected under 'Interface in Service' table");
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + application + "/InterfaceInselect_Actiondropdown")));

								

								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_removebuton")));
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " has been selected to get removed from service");

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", interfacename));
								//driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"+ interfacenumber+"']]//input"));
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

			System.out.println("No values available in table");
			Log.info("No values available inside the InterfaceInService table");
		}

}


public void PageNavigation_NextPageForInterfaceToselect(String Application)
		throws InterruptedException, DocumentException {

	Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_nextpage")));
	

}

public void SelectInterfacetoaddwithservcie(String application, String InterfaceName,	String Edit_InterfaceName)
		throws InterruptedException, DocumentException, IOException {

	ScrolltoElement(application, "InterfaceInService_panelHeader", xml);
	
	
	click_commonMethod(application, "Interfaces To Select Filter", "InteraceColumn_Filter", xml);
	if(Edit_InterfaceName.equalsIgnoreCase("null")) {
	addtextFields_commonMethod(application, "Interface search", "InterfacefilterTxt", InterfaceName, xml);
	WebElement InterfaceName_GridSelect= getwebelement(xml.getlocator("//locators/" + application + "/interface_gridselect").replace("value", InterfaceName));
	Clickon(InterfaceName_GridSelect);
	ExtentTestManager.getTest().log(LogStatus.PASS, InterfaceName + " is selected under 'Interface to select' table");
	}
	else
	{
		addtextFields_commonMethod(application, "Interface search", "InterfacefilterTxt", Edit_InterfaceName, xml);
		WebElement InterfaceName_GridSelect= getwebelement(xml.getlocator("//locators/" + application + "/interface_gridselect").replace("value", Edit_InterfaceName));
		Clickon(InterfaceName_GridSelect);
		ExtentTestManager.getTest().log(LogStatus.PASS, Edit_InterfaceName + " is selected under 'Interface to select' table");
	}
	
	waitforPagetobeenable();
	ScrolltoElement(application, "InterfaceInService_panelHeader", xml);
	
	click_commonMethod(application, "Action Dropdown", "InterfaceToselect_Actiondropdown", xml);
	
	click_commonMethod(application, "Add", "InterfaceToselect_addbuton", xml);
	
//	compareText(application, "Add Interface Success Message", "AddSelectInterfaceSuccessMessage", "Interface Added Successfully.", xml);
	verifysuccessmessage(application, "Interface Added Successfully.");
	ScrolltoElement(application, "viewpage_backbutton", xml);
	
	click_commonMethod(application, "Back", "viewpage_backbutton", xml);
	waitforPagetobeenable();
}

public void Juniper_selectrowforInterfaceToselecttable(String application)
		throws IOException, InterruptedException, DocumentException {

	String Edit_JuniperInterfaceNameValue="e3-4468/2799/3456:4:38962.33256";
	int TotalPages;

	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_totalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in Interface to select table is: " + TotalPages);

	ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements("(//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[@ref='eBodyContainer']//div[@role='row']//span[@class='ag-icon ag-icon-checkbox-unchecked'])[1]");
					//	getwebelements("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[div[text()='Client']]//span[@class='ag-icon ag-icon-checkbox-unchecked']");

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceToselect(application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS, Edit_JuniperInterfaceNameValue + " is selected under 'Interface to select' table");
								waitforPagetobeenable();
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + application + "/InterfaceToselect_Actiondropdown")));

								waitforPagetobeenable();

								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_addbuton")));
								
								ExtentTestManager.getTest().log(LogStatus.PASS, Edit_JuniperInterfaceNameValue + " is added to service");


							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", Edit_JuniperInterfaceNameValue));
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

			System.out.println("No values found inside the table");
			Log.info("No values available inside the Interfacetoselect table");
		}

}


public void Cisco_selectrowforInterfaceToselecttable(String application, String interfacename)
		throws IOException, InterruptedException, DocumentException {

	int TotalPages;

	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_totalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in Interface to select table is: " + TotalPages);

	ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[div[text()='Client']]//span[@class='ag-icon ag-icon-checkbox-unchecked']");

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceToselect(application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected under 'Interface to select' table");
								waitforPagetobeenable();
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + application + "/InterfaceToselect_Actiondropdown")));

								waitforPagetobeenable();

								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_addbuton")));
								
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is added to service");


							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", interfacename));
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

			System.out.println("No values found inside the table");
			Log.info("No values available inside the Interfacetoselect table");
		}

}


public void GetMessageFromAlertJavaScriptPopup(){
	String alertMessage= driver.switchTo().alert().getText();
    if(alertMessage.isEmpty()) {
 	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No Message displays under Alert popup");
	       System.out.println("No Message displays under Alert popup"); 
    }else {
 	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
	       System.out.println("text message for alert displays as: "+alertMessage);
    }
	}


}
