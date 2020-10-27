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
import java.util.HashSet;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;


public class APT_VoiceLineHelper extends DriverHelper {

	public APT_VoiceLineHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_VoiceLine.xml");

	public String primarytrunkGroupname=null;
	public String Edit_primarytrunkGroupname=null;
	public String primarytrunkGroupname_Resilient=null;
	public String ASRDevice_ManagementAddress=null;
	//public String DRusingTDM_Value= "Yes";

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


		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);
		System.out.println("Mouse hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
		Log.info("Mouse hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "create customer link", "createcustomerlink", xml);
		Thread.sleep(2000);
		compareText(application, "create customer page header", "createcustomer_header", "Create Customer", xml);
		ScrolltoElement(application, "okbutton", xml);
		click_commonMethod(application, "Ok", "okbutton", xml);

		//Warning msg check
		warningMessage_commonMethod(application, "customernamewarngmsg", "Legal Customer Name", xml);
		warningMessage_commonMethod(application, "countrywarngmsg", "Country", xml);
		warningMessage_commonMethod(application, "ocnwarngmsg", "OCN", xml);
		warningMessage_commonMethod(application, "typewarngmsg", "Type", xml);
		warningMessage_commonMethod(application, "emailwarngmsg", "Email", xml);

		//Clear customer info
		addtextFields_commonMethod(application, "Customer Name", "nametextfield", name, xml);
		addtextFields_commonMethod(application, "Main Domain", "maindomaintextfield", maindomain, xml);
		Thread.sleep(1000);
		ScrolltoElement(application, "resetbutton", xml);
		click_commonMethod(application, "Reset", "resetbutton", xml);
		ExtentTestManager.getTest().log(LogStatus.PASS, "All text field values are cleared");
		Log.info("All text field values are cleared");

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
		ScrolltoElement(application, "okbutton", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Ok", "okbutton", xml);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Customer successfully created.");
		sa.assertAll();
	}


	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected)
			throws InterruptedException, DocumentException, IOException {

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
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
		waitforPagetobeenable();
	}


	public static String newordernumber, newVoiceLineNumber, SelectOrderNumber;
	public void createorderservice(String application, String neworder, String neworderno, String newrfireqno, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		ScrolltoElement(application, "nextbutton", xml);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(1000);

		//Warning messages verify
		//warningMessage_commonMethod(application, "order_contractnumber_warngmsg", "Order/Contract Number(Parent SID)", xml);
		warningMessage_commonMethod(application, "servicetype_warngmsg", "Service Type", xml);

		if (neworder.equalsIgnoreCase("YES")) {

			Thread.sleep(2000);

			addtextFields_commonMethod(application, "Order/Contract Number", "newordertextfield", neworderno, xml);
			addtextFields_commonMethod(application, "RFI Voice line Number", "newrfireqtextfield", newrfireqno, xml);
			click_commonMethod(application, "create order", "createorderbutton", xml);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Order created successfully");
			ScrolltoElement(application, "CreateOrderHeader", xml);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;
		} 

		else if (existingorderservice.equalsIgnoreCase("YES")) {
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber, xml);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

			SelectOrderNumber = existingordernumber;
		} else {

			System.out.println("Order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step :Order not selected");
		}
	}

	public void verifyselectservicetype(String application, String servicetype)
			throws InterruptedException, IOException, DocumentException {

		// select service type
		ScrolltoElement(application, "nextbutton", xml);
		//addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/servicetypetextfield")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service Type dropdown is displaying");
				System.out.println("Service Type dropdown is displaying");

				if(servicetype.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Service Type dropdown");
					System.out.println(" No values selected under Service Type dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='Service Type']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

					ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside Service Type dropdown is:  ");
					System.out.println( " List of values inside Service Type dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='Service Type']]//input"), servicetype);	
					Thread.sleep(2000);

					ScrolltoElement(application, "nextbutton", xml);
					Thread.sleep(2000);
					Clickon(getwebelement("(//div[text()='"+ servicetype +"'])[1]"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//label[text()='Service Type']/following-sibling::div//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Service Type dropdown value selected as: "+ actualValue );
					System.out.println("Service Type dropdown value selected as: "+ actualValue);

				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Service Type is not displaying");
				System.out.println("Service Type is not displaying");
			}
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Service Type is not displaying");
			System.out.println("Service Type is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under Service Type dropdown");
			System.out.println(" NO value selected under Service Type dropdown");
		}
		// click on next button
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

	}


	public void verifyservicecreation(String application, String sid, String Remarks, String orderno, String rfireqno, String servicetype, String resellercodevalue, String thirdpartyinternet_checkbox, String email, String phonecontact, String performancereporting_checkbox, String proactivenotification_checkbox, String notificationmanagementteam_value) throws InterruptedException, IOException, DocumentException {


		//Create service
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);
		//addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno, xml);

		// service identification
		cleartext(application, "Service Identification", "serviceidentificationtextfield");
		addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
		Thread.sleep(1000);
		GetText(application, "Service Type", "servicetypevalue");
		addtextFields_commonMethod(application, "Reseller Code", "resellercode_textfield", resellercodevalue, xml);
		addCheckbox_commonMethod(application, "servicepage_thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet_checkbox, "no", xml);
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", Remarks, xml);
		addtextFields_commonMethod(application, "Email", "emailtextfieldvalue", email, xml);
		click_commonMethod(application, "Email adding Arrow", "emailaddarrow", xml);
		ScrolltoElement(application, "emailtextfieldvalue", xml);
		Thread.sleep(1000);
		addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", phonecontact, xml);
		click_commonMethod(application, "phone contact adding Arrow", "phoneaddarrow", xml);
		compareText(application, "Management Options", "managementoptions_header", "Management Options", xml);
		String Package= getwebelement(xml.getlocator("//locators/" + application + "/packagefield")).getAttribute("disabled");
		if(Package!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Package dropdown field is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Package dropdown field is not disabled");
		}
		String ManagedService_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/managedservice_checkbox")).getAttribute("disabled");
		String ManagedService_Checked= getwebelement(xml.getlocator("//locators/" + application + "/managedservice_checkbox")).getAttribute("checked");
		if(ManagedService_Disabled!=null && ManagedService_Checked!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Managed Service Checkbox is disabled & checked by default as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Managed Service Checkbox is not disabled & checked by default");
		}
		ScrolltoElement(application, "managementoptions_header", xml);
		//syslog event view checkbox
		String SyslogEvent_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/syslogevent_checkbox")).getAttribute("disabled");
		if(SyslogEvent_Disabled!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Syslog Event View Checkbox is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Syslog Event View Checkbox is not disabled");
		}
		//service status view checkbox
		String ServiceStatusView_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/servicestatusview_checkbox")).getAttribute("disabled");
		if(ServiceStatusView_Disabled!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service Status View Checkbox is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Service Status View Checkbox is not disabled");
		}
		//Router configuration view checkbox
		String RouterConfigurationView_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/routerconfigview_checkbox")).getAttribute("disabled");
		if(RouterConfigurationView_Disabled!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Router Configuration View Checkbox is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Router Configuration View Checkbox is not disabled");
		}
		addCheckbox_commonMethod(application, "performancereporting_checkbox", "Performance Reporting", performancereporting_checkbox, "no", xml);
		if(proactivenotification_checkbox.equalsIgnoreCase("Yes"))
		{
			addCheckbox_commonMethod(application, "proactivenotification_checkbox", "Pro-active Notification", proactivenotification_checkbox, "no", xml);
			addDropdownValues_commonMethod(application, "Notification Management Team", "notificationmanagementteam_dropdown", notificationmanagementteam_value, xml);
		}
		else
		{
			addCheckbox_commonMethod(application, "proactivenotification_checkbox", "Pro-active Notification", proactivenotification_checkbox, "no", xml);
		}
		//Dial user administration checkbox
		String DialUserAdministration_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/dialuseradministration_checkbox")).getAttribute("disabled");
		if(DialUserAdministration_Disabled!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Dial User Administration Checkbox is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Dial User Administration Checkbox is not disabled");
		}
		
		ScrolltoElement(application, "okbutton", xml);
		click_commonMethod(application, "OK", "okbutton", xml);

		Thread.sleep(1000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Service successfully created");
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
		compareText(application, "Login column", "LoginColumn", Login, xml);
		compareText(application, "Name column", "NameColumn", Name, xml);
		compareText(application, "Email column", "EmailColumn", Email, xml);
		compareText(application, "Roles column", "RolesColumn", Roles, xml);
		compareText(application, "Address column", "AddressColumn", Address, xml);
		compareText(application, "Resource column", "ResourcesColumn", Resource, xml);

	}


	public void VerifyUsersPanel(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email, String Phone, String EditUsername, 
			String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone, String IPGuardianAccountGroup,String ColtOnlineUser, 
			String GeneratePassword, String RolesToBeSelected,String HideRouterToolsIPv6CommandsCisco_ToBeSelected, String HideRouterToolsIPv4CommandsHuiwai_ToBeSelected, 
			String HideRouterToolsIPv4CommandsCisco_ToBeSelected,String HideServicesToBeSelected,String HideSiteOrderToBeSelected, String editRolesToBeSelected, 
			String edit_RoleToBeHidden, String RouterToolsIPv6CommandsCisco_ToBeAvailable, String RouterToolsIPv6CommandsCisco_ToBeHidden, String RouterToolsIPv4CommandsHuiwai_ToBeAvailable, 
			String HideRouterToolsIPv4CommandsHuiwai_ToBeHidden, String HideRouterToolsIPv4CommandsCisco_ToBeAvailable, String HideRouterToolsIPv4CommandsCisco_ToBeHidden,
			String Services_ToBeAvailable, String Services_ToBeHidden, String SiteOrders_ToBeAvailable, String SiteOrders_ToBeHidden) throws InterruptedException, DocumentException, IOException {

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

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "customerdetailsheader", xml);
		Thread.sleep(2000);

		//Cancel User
		click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		Thread.sleep(1000);
		waitforPagetobeenable();
		compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);
		ScrolltoElement(application, "cancelbutton", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		waitforPagetobeenable();
		compareText(application, "User panel Header", "userspanel_header", "Users", xml);

		//Create User
		ScrolltoElement(application, "customerdetailsheader", xml);
		click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		waitforPagetobeenable();
		compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);

		//Warning messages verify
		ScrolltoElement(application, "OK_button", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "OK_button", xml);
		waitforPagetobeenable();
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
		String password=getwebelement(xml.getlocator("//locators/"+application+"/Password_Textfield")).getAttribute("value");
		System.out.println("Generated Password is : "+password);

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

		ScrolltoElement(application, "OK_button", xml);
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

		ScrolltoElement(application, "OK_button", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "OK_button", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "User successfully created");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User added successfully");
		Log.info("User added successfully");

		//Edit User
		ScrolltoElement(application, "customerdetailsheader", xml);
		List<WebElement> ExistingUsers= getwebelements("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']");
		int NoOfUsers = ExistingUsers.size();
		System.out.println("Total users:"+ NoOfUsers);

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
			edittextFields_commonMethod(application, "IPGuardian Account Group" , "IPGuardianAccountGroup" , IPGuardianAccountGroup, xml);
			edittextFields_commonMethod(application, "Colt Online User", "ColtOnlineUser", ColtOnlineUser, xml);

			String editpassword=getwebelement(xml.getlocator("//locators/"+application+"/Password")).getAttribute("value");
			System.out.println("Generated Password is : "+editpassword);

			if(editpassword.isEmpty()) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Password Field is empty. No values displaying under'Generate password link");

				click_commonMethod(application, "Generate Password", "GeneratePassword", xml);

			}else {
				Log.info("Automatically generated Password value is : "+ editpassword);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password generated and the value is displaying as :  "+editpassword);
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

			ScrolltoElement(application, "OK_button", xml);
			Thread.sleep(1000);

			//Hide Router Tool IPv4 Commands(Cisco)
			selectAndRemoveValueFromRightDropdown(application, "Router Tool IPv4 Commands(Cisco)_Available", "hideRouterToolIpv4_Cisco_selectedvalues", routerToolIPv4CiscoTobeAvailableList, "hideRouterToolIPv4_Cisco_removeButton");
			selectAndAddValueFromLeftDropdown(application, "Router Tool IPv4 Commands(Cisco)_Hidden", "hideRouterToolIPv4_Cisco_Available", routerToolIPv4CiscoTobeHiddenList, "hideRouterToolIPv4_Cisco_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hiden Router Tool IPv4 Commands(Cisco)", "hideRouterToolIpv4_Cisco_selectedvalues");

			//Hide Router Tool IPv4 Commands(Huawei)
			selectAndRemoveValueFromRightDropdown(application, "Router Tool IPv4 Commands(Huawei)_Hidden", "hideRouterToolIpv4_Huawei_selectedvalues", routerToolIPv4HuaweiTobeAvailableList, "hideRouterToolIPv4_Huawei_removeButton");
			selectAndAddValueFromLeftDropdown(application, "Router Tool IPv4 Commands(Huawei)_Available" , "hideRouterToolIPv4_Huawei_available" , routerToolIPv4HuaweiTobeHiddenList, "hideRouterToolIPv4__Huawei_addButton");
			verifySelectedValuesInsideRightDropdown(application, "Hideen Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIpv4_Huawei_selectedvalues");

			ScrolltoElement(application, "OK_button", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "OK_button", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			verifysuccessmessage(application, "User successfully updated");
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
		System.out.println("Total users:"+ NoOfUsers1);
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
			compareText(application, "User Name", "usernamevalue", EditUsername, xml);
			compareText(application, "First Name", "firstnamevalue", EditFirstname, xml);
			compareText(application, "SurName", "surnamevalue", EditSurname, xml);
			compareText(application, "Postal Address", "postaladdressvalue", EditPostaladdress, xml);
			compareText(application, "Email", "emailvalue", EditEmail, xml);
			compareText(application, "Phone", "phonevalue", EditPhone, xml);

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
				System.out.println("list of values in Hide router Tool Command IPv4(Cisco) are: "+listofHiddenCiscoValues.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Cisco) are: " + listofHiddenCiscoValues.getText());
			}

			ScrolltoElement(application, "viewpage_backbutton", xml);
			Thread.sleep(2000);

			//Hidden Router Tool IPv4 (Huawei)
			List<WebElement> Ipv4CommandHuawei = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv4Huawei"));	

			for(WebElement listofHuaweiValues : Ipv4CommandHuawei) {
				System.out.println("list of values in Hide router Tool Command (Cisco) are: "+listofHuaweiValues.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Huawei) are: "+ listofHuaweiValues.getText());
			}	


			//Hidden Router Tools IPv6 (Cisco)
			List<WebElement> HiddenIPv6cisco = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv6Cisco"));	

			for(WebElement listofHiddenIPv6CiscoValues : HiddenIPv6cisco) {
				System.out.println("list of values in Hide router Tool Command IPv6 (Cisco) are: "+listofHiddenIPv6CiscoValues.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv6 Commands(Cisco) are: " + listofHiddenIPv6CiscoValues.getText());
			}			

			ScrolltoElement(application, "viewpage_backbutton", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			waitforPagetobeenable();
			Log.info("------ View User successful ------");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No users displayed");
			Log.info("No users displayed");
		}
	}

	public void verifyorderpanelinformation_Existingorder(String application, String existingorder,
			String expectedorderno, String expectedvoicelineno) throws InterruptedException, DocumentException {

		if (existingorder.equalsIgnoreCase("Yes")) {

			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue")).getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue")).getText();
			System.out.println("actual voice line number displayed in order panel is : " + actualvoicelineno);

			if (expectedorderno.equalsIgnoreCase(actualorderno)&& expectedvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

				System.out.println("order information is matched");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : order information is matched");
			} else {
				sa.fail("order information is not matched");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

		} else {

			System.out.println("existing order is not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : existing order is not selected");
		}

		sa.assertAll();
	}

	public void verifyorderpanelinformation_Neworder(String application, String neworder, String expectedneworderno,
			String expectednewvoicelineno) throws InterruptedException, DocumentException {

		Thread.sleep(2000);
		ScrolltoElement(application, "userspanel_header", xml);

		if (neworder.equalsIgnoreCase("YES")) {

			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue")).getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue")).getText();
			System.out.println("actual voice line number displayed in order panel is : " + actualvoicelineno);

			if (expectedneworderno.equalsIgnoreCase(actualorderno)&& expectednewvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

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

		sa.assertAll();
	}

	public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno, String editOrderSelection) 
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit Order' Functionality");
		
		ScrolltoElement(application, "userspanel_header", xml);

		if(editOrderSelection.equalsIgnoreCase("No")) {
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
			waitforPagetobeenable();
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
			waitforPagetobeenable();
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
			waitforPagetobeenable();
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			
				addDropdownValues_commonMethod(application, "Order/Contract Number (Parent SID)", "changeorder_chooseorderdropdown", ChangeOrder_existingOrderNumber, xml);
				
				click_commonMethod(application, "OK", "changeorder_okbutton", xml);
				waitforPagetobeenable();
				ScrolltoElement(application, "userspanel_header", xml);
				Thread.sleep(1000);
				compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_existingOrderNumber, xml);
				compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
				Log.info("------ Change Order is successful ------");
	
		}
		
	}


	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String Remarks, String resellercodevalue, String thirdpartyinternet_checkbox, String phonecontact) throws InterruptedException, DocumentException, IOException {

		Thread.sleep(2000);
		ScrolltoElement(application, "orderpanelheader", xml);
		compareText(application, "Service panel Header", "servicepanel_header", "Service", xml);
		compareText(application, "Service Identification", "servicepanel_serviceidentificationvalue", sid, xml);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype, xml);
		compareText(application, "Reseller Code", "servicepanel_resellercode", resellercodevalue, xml);
		GetText(application, "Email", "servicepanel_email");
		compareText(application, "Phone Contact", "servicepanel_phonecontact", phonecontact, xml);
		compareText(application, "3rd Party Internet", "servicepanel_thirdpartyinternet", thirdpartyinternet_checkbox, xml);
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks, xml);
	}

	public void verifyEditService(String application, String EditRemarks, String Remarks, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired, String resellercodevalue, String thirdpartyinternet_checkbox, String phonecontact, String edit_resellercodevalue, String edit_thirdpartyinternet_checkbox, String edit_email, String edit_phonecontact, String edit_performancereporting_checkbox, String edit_proactivenotification_checkbox, String edit_notificationmanagementteam_value) throws Exception {

		//Cancel edit service
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		waitforPagetobeenable();
		ScrolltoElement(application, "cancelbutton", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		ScrolltoElement(application, "orderpanelheader", xml);

		//Edit service
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		waitforPagetobeenable();
		String ServiceIdentification= getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service Identification number is displayed as: "+sid);
		GetText(application, "Service Type", "servicetypevalue");
		if(!edit_resellercodevalue.equalsIgnoreCase("null"))
		{
			cleartext(application, "Reseller Code", "resellercode_textfield");
			edittextFields_commonMethod(application, "Reseller Code", "resellercode_textfield", edit_resellercodevalue, xml);
		}
		else
		{
			edittextFields_commonMethod(application, "Reseller Code", "resellercode_textfield", edit_resellercodevalue, xml);
		}
		editcheckbox_commonMethod(application, edit_thirdpartyinternet_checkbox, "servicepage_thirdpartyinternet_checkbox", "3rd Party Internet", xml);
		if(!EditRemarks.equalsIgnoreCase("null"))
		{
			cleartext(application, "Remarks", "remarktextarea");
			edittextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
		}
		else
		{
			edittextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
		}
		//Edit email
		ScrolltoElement(application, "servicetypevalue", xml);
		click_commonMethod(application, "Selected Email", "selectedemail", xml);
		click_commonMethod(application, "Email remove arrow", "emailremovearrow", xml);
		Thread.sleep(1000);
		edittextFields_commonMethod(application, "Email", "emailtextfieldvalue", edit_email, xml);
		click_commonMethod(application, "Email adding arrow", "emailaddarrow", xml);
		ScrolltoElement(application, "okbutton", xml);
		//Edit phone contact
		click_commonMethod(application, "Selected phone contact", "selectedphone", xml);
		click_commonMethod(application, "Phonecontact remove arrow", "phoneremovearrow", xml);
		edittextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", edit_phonecontact, xml);
		click_commonMethod(application, "phonecontact adding Arrow", "phoneaddarrow", xml);
		Thread.sleep(1000);

		compareText(application, "Management Options", "managementoptions_header", "Management Options", xml);
		String Package= getwebelement(xml.getlocator("//locators/" + application + "/packagefield")).getAttribute("disabled");
		if(Package!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Package dropdown field is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Package dropdown field is not disabled");
		}
		String ManagedService_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/managedservice_checkbox")).getAttribute("disabled");
		String ManagedService_Checked= getwebelement(xml.getlocator("//locators/" + application + "/managedservice_checkbox")).getAttribute("checked");
		if(ManagedService_Disabled!=null && ManagedService_Checked!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Managed Service Checkbox is disabled & checked by default as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Managed Service Checkbox is not disabled & checked by default");
		}
		//syslog event view checkbox
		String SyslogEvent_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/syslogevent_checkbox")).getAttribute("disabled");
		if(SyslogEvent_Disabled!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Syslog Event View Checkbox is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Syslog Event View Checkbox is not disabled");
		}
		//service status view checkbox
		String ServiceStatusView_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/servicestatusview_checkbox")).getAttribute("disabled");
		if(ServiceStatusView_Disabled!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service Status View Checkbox is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Service Status View Checkbox is not disabled");
		}
		//Router configuration view checkbox
		String RouterConfigurationView_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/routerconfigview_checkbox")).getAttribute("disabled");
		if(RouterConfigurationView_Disabled!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Router Configuration View Checkbox is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Router Configuration View Checkbox is not disabled");
		}
		editcheckbox_commonMethod(application, edit_performancereporting_checkbox, "performancereporting_checkbox", "Performance Reporting", xml);
		editcheckbox_commonMethod(application, edit_proactivenotification_checkbox, "proactivenotification_checkbox", "Pro-active Notification", xml);
		if(edit_proactivenotification_checkbox.equalsIgnoreCase("Yes"))
		{
			addDropdownValues_commonMethod(application, "Notification Management Team", "notificationmanagementteam_dropdown", edit_notificationmanagementteam_value, xml);
		}
		//Dial user administration checkbox
		String DialUserAdministration_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/dialuseradministration_checkbox")).getAttribute("disabled");
		if(DialUserAdministration_Disabled!=null)
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Dial User Administration Checkbox is disabled as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Dial User Administration Checkbox is not disabled");
		}
		ScrolltoElement(application, "okbutton", xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);
		waitForpageload();
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			verifysuccessmessage(application, "Service successfully updated");
		}
		else
		{
			Log.info("Service not updated");
			System.out.println("Service not updated");
		}
	}

	public void verifyManageSubnetsIPv6(String application) throws InterruptedException, DocumentException {

		//manage subnets IPv6
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage Subnets Ipv6", "managesubnetsipv6_link", xml);
		Thread.sleep(3000);
		waitforPagetobeenable();
		GetText(application, "Manage Subnet header", "managesubnet_header");
		compareText(application, "Manage subnet message", "managesubnet_successmsg", "There are no subnets to be managed for this service.", xml);
		compareText(application, "Space Name", "spacename_column", "Space Name", xml);
		compareText(application, "Block Name", "blockname_column", "Block Name", xml);
		compareText(application, "Subnet Name", "subnetname_column", "Subnet Name", xml);
		compareText(application, "Start Address", "startaddress_column", "Start Address", xml);
		compareText(application, "Size", "size_column", "Size", xml);
		Thread.sleep(2000);
		click_commonMethod(application, "Close", "closesymbol", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
	}

	public void verifyShowNewInfovistaReport(String application) throws Exception {
		//Show new infovista report
		shownewInfovista(application);
		Thread.sleep(2000);
	}

	public void verifyDump(String application) throws InterruptedException, DocumentException {
		//dump
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Dump", "dump_link", xml);
		Thread.sleep(3000);
		waitforPagetobeenable();
		GetText(application, "Dump header", "dumppage_header");
		GetText(application, "Service retrieved time", "serviceretrieved_text");
		compareText(application, "Service header", "service_header", "Service", xml);
		GetText(application, "Dump page service details", "dumppage_text");
		Thread.sleep(1000);
		click_commonMethod(application, "Close", "closesymbol", xml);
		Thread.sleep(2000);
	}

	public void verifySynchronize(String application) throws InterruptedException, DocumentException {

		Thread.sleep(3000);
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Synchronize", "synchronizelink_servicepanel", xml);
		Thread.sleep(2000);
		ScrolltoElement(application, "customerdetailsheader", xml);
		verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
		Thread.sleep(2000);
	}


	public void verifyManageService(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {

		//Manage service
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage", "manageLink", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		compareText(application, "Manage service header", "manageservice_header", "Manage Service", xml);
		compareText(application, "Order Name", "status_ordername", changeorderno, xml);
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
		Thread.sleep(2000);
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
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Status link is not working");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change not reqired");
			click_commonMethod(application, "Close", "servicestatus_popupclose", xml);
		}

		//synchronize panel in manage service page

		compareText(application, "Order Name", "sync_ordername", changeorderno, xml);
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

		ScrolltoElement(application, "synchronizelink", xml);
		GetText(application, "Sync Status", "sync_status");
		click_commonMethod(application, "Synchronize", "synchronizelink", xml);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
		clickOnBreadCrumb(application, sid);
		Thread.sleep(2000);
		waitforPagetobeenable();

	}

	
	public void verifyManagementOptionspanel(String application, String performancereporting_checkbox, String proactivenotification_checkbox, String edit_performancereporting_checkbox, String edit_proactivenotification_checkbox) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "managementoptions_header", xml);
		Thread.sleep(1000);
		compareText(application, "Management options header", "managementoptions_header", "Management Options", xml);
		compareText(application, "Managed Service", "viewservice_managedservicevalue", "Yes", xml);
		compareText(application, "Syslog Event View", "viewservice_syslogeventview", "No", xml);
		compareText(application, "Service Status View", "viewservice_servicestatusview", "No", xml);
		compareText(application, "Router Configuration View", "viewservice_routerconfigview", "No", xml);
		if(edit_performancereporting_checkbox.equalsIgnoreCase("null"))
		{
			compareText(application, "Performance Reporting", "viewservice_performancereporting", performancereporting_checkbox, xml);
		}
		else
		{
			compareText(application, "Performance Reporting", "viewservice_performancereporting", edit_performancereporting_checkbox, xml);
		}
		if(edit_proactivenotification_checkbox.equalsIgnoreCase("null"))
		{
			compareText(application, "Pro-active Notification", "viewservice_proactivenotification", proactivenotification_checkbox, xml);
		}
		else
		{
			compareText(application, "Pro-active Notification", "viewservice_proactivenotification", edit_proactivenotification_checkbox, xml);
		}
		compareText(application, "Dial User Administration", "viewservice_dialuseradministration", "No", xml);

	}

	public void verifyAddASRDevice(String application, String imspoplocation_dropdownvalue) throws InterruptedException, DocumentException
	{

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "asrdevice_header", xml);
		Thread.sleep(1000);
		compareText(application, "ASR Device header", "asrdevice_header", "ASR Device", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Add ASR Device link", "adddevice_link", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		compareText(application, "Add ASR Device header", "adddevice_header", "Add ASR Device", xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(1000);
		addDropdownValues_commonMethod(application, "IMS POP Location", "imspoplocation_dropdown", imspoplocation_dropdownvalue, xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "ASR Device added successfully");

		//Added device
		Thread.sleep(2000);
		ScrolltoElement(application, "asrdevice_header", xml);
		Thread.sleep(1000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevicename"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();

			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				System.out.println(AddedDeviceNameText);
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				String AddedDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_viewlink").replace("value", AddedDevice_SNo)).getText();
				String AddedDevice_EditLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_editlink").replace("value", AddedDevice_SNo)).getText();
				String AddedDevice_DeletefromServiceLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_Deletefromservicelink").replace("value", AddedDevice_SNo)).getText();
				String AddedDevice_SelectInterfacesLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_SelectInterfaceslink").replace("value", AddedDevice_SNo)).getText();
				if(imspoplocation_dropdownvalue.equalsIgnoreCase("Bangalore-1"))
				{
					//verify view link
					if(AddedDevice_ViewLink.contains("View"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "View link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "View link is not displaying for added device");
					}
					//verify Edit link
					if(AddedDevice_EditLink.contains("Edit"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Edit link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit link is not displaying for added device");
					}
					//verify Delete from Service link
					if(AddedDevice_DeletefromServiceLink.contains("Delete from Service"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Delete from Service link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete from Service link is not displaying for added device");
					}
					//verify Select Interfaces link
					if(AddedDevice_SelectInterfacesLink.contains("Select Interfaces"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Select Interfaces link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Select Interfaces link is not displaying for added device");
					}

				}
				else if(imspoplocation_dropdownvalue.equalsIgnoreCase("Paris-1"))
				{
					//verify view link
					if(AddedDevice_ViewLink.contains("View"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "View link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "View link is not displaying for added device");
					}
					//verify Edit link
					if(AddedDevice_EditLink.contains("Edit"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Edit link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit link is not displaying for added device");
					}
					//verify Delete from Service link
					if(AddedDevice_DeletefromServiceLink.contains("Delete from Service"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Delete from Service link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete from Service link is not displaying for added device");
					}
					//verify Select Interfaces link
					if(AddedDevice_SelectInterfacesLink.contains("Select Interfaces"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Select Interfaces link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Select Interfaces link is not displaying for added device");
					}
				}
				else if(imspoplocation_dropdownvalue.equalsIgnoreCase("Frankfurt-1"))
				{
					//verify view link
					if(AddedDevice_ViewLink.contains("View"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "View link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "View link is not displaying for added device");
					}
					//verify Edit link
					if(AddedDevice_EditLink.contains("Edit"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Edit link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit link is not displaying for added device");
					}
					//verify Delete from Service link
					if(AddedDevice_DeletefromServiceLink.contains("Delete from Service"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Delete from Service link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete from Service link is not displaying for added device");
					}
					//verify Select Interfaces link
					if(AddedDevice_SelectInterfacesLink.contains("Select Interfaces"))
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Select Interfaces link is displaying as expected for added device");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Select Interfaces link is not displaying for added device");
					}
				}

			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}

	}

	public static String ASRDeviceNameValue, ASRManagementAddressValue, ASRDeviceCountryValue;
	public void verifyEditASRDevice(String application, String imspoplocation_dropdownvalue, String edit_asrdevicename, String edit_asrmanagementaddress, String editCountry, String editExistingCity, 
			String editExistingCityValue, String editExistingSite, String editExistingSiteValue, String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, 
			String editNewPremise, String editNewCityName, String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, DocumentException, IOException {

		String ASRDeviceName=null;
		String ASRManagementAddress=null;
		String ASRDevice_Country=null;

		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(1000);
		ScrolltoElement(application, "asrdevice_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevicename"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();

			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				System.out.println(AddedDeviceNameText);
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				String IMSPopLocation_Code= imsPopLocationValue(application, imspoplocation_dropdownvalue);
				if(AddedDeviceNameText.contains(IMSPopLocation_Code))
				{
					WebElement AddedDevice_EditLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_editlink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_EditLink);
					Thread.sleep(2000);
					compareText(application, "Edit ASR Device header", "editasrdevice_header", "Edit ASR Device", xml);
					if(!edit_asrdevicename.equalsIgnoreCase("null"))
					{
						edittextFields_commonMethod(application, "Name", "edit_asrdevicename", edit_asrdevicename, xml);
					}
					else
					{
						ASRDeviceName= getwebelement(xml.getlocator("//locators/" + application + "/edit_asrdevicename")).getAttribute("value");
						compareText_fromtextFields(application, "Name", "edit_asrdevicename", ASRDeviceName, xml);
					}

					compareText_fromActualvalue(application, "Vendor/Model", "edit_asrvendormodel", "Cisco 12016", xml);

					if(!edit_asrmanagementaddress.equalsIgnoreCase("null"))
					{
						edittextFields_commonMethod(application, "Management Address", "edit_managementaddressvalue", edit_asrmanagementaddress, xml);
					}
					else
					{
						ASRManagementAddress= getwebelement(xml.getlocator("//locators/" + application + "/edit_managementaddressvalue")).getAttribute("value");
						compareText_fromtextFields(application, "Management Address", "edit_managementaddressvalue", ASRManagementAddress, xml);
					}

					compareText_fromtextFields(application, "Snmpro", "snmpro_textfield", "inccc", xml);

					//select country
					Thread.sleep(2000);
					if(!editCountry.equalsIgnoreCase("null"))
					{
						addDropdownValues_commonMethod(application, "Country", "countryinput", editCountry, xml);
					}
					else
					{
						ASRDevice_Country= getwebelement(xml.getlocator("//locators/" + application + "/countryinput")).getText();
						compareText(application, "Country", "countryinput", ASRDevice_Country, xml);
					}
					ScrolltoElement(application, "okbutton", xml);
					//New City		
					if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("yes")) {
						Clickon_addToggleButton(application, "addcityswitch");
						//City name
						edittextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
						//City Code	
						edittextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
						//Site name
						edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
						//Site Code
						edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", editNewSiteCode, xml);
						//Premise name	
						edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
						//Premise Code	
						edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);

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
								edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", editNewPremiseName, xml);
								//Premise Code	
								edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", editNewPremiseCode, xml);
							} 
						}

						else if(editExistingSite.equalsIgnoreCase("no") & editNewSite.equalsIgnoreCase("yes")) {

							Clickon_addToggleButton(application, "addsiteswitch");
							//Site name
							edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", editNewSiteName, xml);
							//Site Code
							edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", editNewSiteCode, xml);
							//Premise name	
							edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", editNewPremiseName, xml);
							//Premise Code	
							edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", editNewPremiseCode, xml);
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
					ScrolltoElement(application, "okbutton", xml);
					click_commonMethod(application, "OK", "okbutton", xml);
					Thread.sleep(2000);
					waitforPagetobeenable();
					verifysuccessmessage(application, "ASR Device updated successfully");

					ASRDeviceNameValue= ASRDeviceName;
					ASRManagementAddressValue= ASRManagementAddress;
					ASRDeviceCountryValue= ASRDevice_Country;
					break;
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Device not added for "+IMSPopLocation_Code+ " location");
				}
				
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
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
				Thread.sleep(1000);

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
				Thread.sleep(1000);

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
				Thread.sleep(1000);

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


	public void verifyViewASRDevice(String application, String imspoplocation_dropdownvalue) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "asrdevice_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevicename"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();

			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				System.out.println(AddedDeviceNameText);
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				String IMSPopLocation_Code= imsPopLocationValue(application, imspoplocation_dropdownvalue);
				if(AddedDeviceNameText.contains(IMSPopLocation_Code))
				{
					WebElement AddedDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_viewlink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_ViewLink);
					Thread.sleep(2000);

					//compareText(application, "View Device header", "viewasrdevice_header", "Device", xml);
					GetText(application, "Name", "asrdevicename_viewpage");
					GetText(application, "Vendor/Model", "asrvendormodel_viewpage");
					GetText(application, "Management Address", "asrmanagementaddess_viewpage");
					GetText(application, "Snmpro", "asrsnmpro_viewpage");
					GetText(application, "Country", "asrcountry_viewpage");
					GetText(application, "City", "asrcity_viewpage");
					GetText(application, "Site", "asrsite_viewpage");
					GetText(application, "Premise", "asrpremise_viewpage");

					//String AddedDevice_DeletefromServiceLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_Deletefromservicelink").replace("value", AddedDevice_SNo)).getText();
					break;
				}
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}

	}

	public void verifyViewDevicepage_Links(String application, String sid, String imspoplocation_dropdownvalue) throws InterruptedException, DocumentException {


		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		compareText(application, "Edit", "viewdevice_Edit", "Edit", xml);
		compareText(application, "Delete", "viewdevice_delete", "Delete", xml);
		compareText(application, "Fetch Device Interfaces", "viewdevice_fetchinterfacelink", "Fetch Device Interfaces", xml);

		//Edit in view device page
		click_commonMethod(application, "Edit", "viewdevice_Edit", xml);
		Thread.sleep(2000);
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/editasrdevice_header")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated to 'Edit ASR Device' page successfully");
			Thread.sleep(1000);
			scrollToTop();
			clickOnBreadCrumb(application, sid);
			Thread.sleep(3000);
			ScrolltoElement(application, "asrdevice_header", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
			{
				List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevicename"));
				System.out.println(addeddevicesList);
				int AddedDevicesCount= addeddevicesList.size();

				for(int i=0;i<AddedDevicesCount;i++) {
					String AddedDeviceNameText= addeddevicesList.get(i).getText();
					System.out.println(AddedDeviceNameText);
					String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
					String IMSPopLocation_Code= imsPopLocationValue(application, imspoplocation_dropdownvalue);
					if(AddedDeviceNameText.contains(IMSPopLocation_Code))
					{
						WebElement AddedDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_viewlink").replace("value", AddedDevice_SNo));
						Clickon(AddedDevice_ViewLink);
						Thread.sleep(5000);
						//compareText(application, "View device header", "viewasrdevice_header", "Device", xml);

						break;
					}
				}
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not navigated to 'Edit ASR Device' page");
		}

		//verify delete device in view device page
		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		click_commonMethod(application, "Delete", "viewdevice_delete", xml);
		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete alert is displayed as expected");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletealertclose")));
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert is not displayed");
		}
		scrollToTop();
		clickOnBreadCrumb(application, sid);
		Thread.sleep(3000);
	}


	public String imsPopLocationValue(String application, String imspoplocation_dropdownvalue) {
		String Location_code=null;
		if(imspoplocation_dropdownvalue.equalsIgnoreCase("Bangalore-1"))
		{
			Location_code= "BLR";
		}
		else if(imspoplocation_dropdownvalue.equalsIgnoreCase("Paris-1"))
		{
			Location_code= "PAR";
		}
		else if(imspoplocation_dropdownvalue.equalsIgnoreCase("Frankfurt-1"))
		{
			Location_code= "FRA";
		}
		return Location_code;
	}

	 public boolean fetchDeviceInterface_viewdevicepage(String application) throws InterruptedException, DocumentException {
			
		 boolean clickLink=false;
		 String actualMessage=null;
		 
		 scrollToTop();
		 Thread.sleep(3000);
		 
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewdevice_Actiondropdown")));
			
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewdevice_fetchinterfacelink")));
			Thread.sleep(2000);
			
			
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
//				Thread.sleep(3000);
				
				clickLink=true;
				
				}
				else if (actualMessage.equalsIgnoreCase(expectedValue)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
					System.out.println(" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
					
					ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
					System.out.println(" Success Message displays as: "+actualMessage);
					
					//click on the 'click here' link
//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ClickhereLink_fetchInterface")));
//					Thread.sleep(3000);
					
					clickLink=true;
				}
				else {
					ExtentTestManager.getTest().log(LogStatus.INFO, "Message displays as: 'Message not found for Message ID : Can't start the fetch operation because another process is already fetching the interfaces for this device.'");
					System.out.println("Message displays as: 'Message not found for Message ID : Can't start the fetch operation because another process is already fetching the interfaces for this device.'");
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.INFO, "Message displays as: 'Message not found for Message ID : Can't start the fetch operation because another process is already fetching the interfaces for this device.'");
			System.out.println("Message displays as: 'Message not found for Message ID : Can't start the fetch operation because another process is already fetching the interfaces for this device.'");
			
		}
			return clickLink;
	 }
	 
	public static String InterfaceAddress;
	public void verifyFetchInterface(String application, String imspoplocation_dropdownvalue, String sid, String edit_asrdevicename, String Inservice_status, String Inmaintenance_status, String interfacename, String edit_interfacename) throws InterruptedException, DocumentException, IOException {

		String interfacenameValue;

		ScrolltoElement(application, "asrdevice_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/existingdevicegrid"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				String IMSPopLocation_Code= imsPopLocationValue(application, imspoplocation_dropdownvalue);
				if(AddedDeviceNameText.contains(IMSPopLocation_Code))
				{
					WebElement AddedDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_viewlink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_ViewLink);
					Thread.sleep(5000);
					Thread.sleep(1000);
					click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
					click_commonMethod(application, "Fetch Device Interfaces", "viewdevice_fetchinterfacelink", xml);
					Thread.sleep(3000);
					compareText(application, "Fetch Interface success msg", "fetchsuccessmsg", "Fetch interfaces started successfully.Please check the sync status of this device here", xml);
					click_commonMethod(application, "here", "herelink_fetchinterface", xml);
					Thread.sleep(2000);

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
					compareText(application, "VistaMart Device column header", "synchronization_vistamartdevicecolumn", "VistaMart Device", xml);
					compareText(application, "Action column header", "synchronization_actioncolumn", "Action", xml);
					Thread.sleep(1000);
					//verify Status panel column values
					if(!edit_asrdevicename.equalsIgnoreCase("null"))
					{
						compareText(application, "Device", "status_devicevalue", edit_asrdevicename, xml);
					}
					else
					{
						compareText(application, "Device", "status_devicevalue", ASRDeviceNameValue, xml);
					}

					String ServiceStatus= GetText(application, "Status", "status_statusvalue");
					GetText(application, "Status", "status_statusvalue");
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
					Thread.sleep(2000);
					compareText(application, "Status page header", "Statuspage_header", "Device", xml);

					//verify field headers in status page
					compareText(application, "Name field header", "statuspage_nameheader", "Name", xml);
					compareText(application, "Vendor/Model field header", "statuspage_vendormodelheader", "Vendor/Model", xml);
					compareText(application, "Management Address field header", "statuspage_managementaddressheader", "Management Address", xml);
					compareText(application, "Snmpro field header", "statuspage_snmproheader", "Snmpro", xml);
					compareText(application, "Country field header", "statuspage_countryheader", "Country", xml);
					compareText(application, "City field header", "statuspage_cityheader", "City", xml);
					compareText(application, "Site field header", "statuspage_siteheader", "Site", xml);
					compareText(application, "Premise field header", "statuspage_premiseheader", "Premise", xml);
					Thread.sleep(1000);
					//verify field values in status page
					if(!edit_asrdevicename.equalsIgnoreCase("null"))
					{
						compareText(application, "Name", "statuspage_namevalue", edit_asrdevicename, xml);
					}
					else
					{
						compareText(application, "Name", "statuspage_namevalue", ASRDeviceNameValue, xml);
					}
					GetText(application, "Vendor/Model", "statuspage_vendormodelvalue");
					GetText(application, "Management Address", "statuspage_managementaddressvalue");
					GetText(application, "Snmpro", "statuspage_snmprovalue");
					GetText(application, "Country", "statuspage_countryvalue");
					GetText(application, "City", "statuspage_cityvalue");
					GetText(application, "Site", "statuspage_sitevalue");
					GetText(application, "Premise", "statuspage_premisevalue");

					compareText(application, "Status header", "Statuspage_statusheader", "Status", xml);
					compareText(application, "Current Status field header", "statuspage_currentstatusfieldheader", "Current Status", xml);
					compareText(application, "New Status field header", "statuspage_newstatusfieldheader", "New Status", xml);
					GetText(application, "Status", "status_statusvalue");
					click_commonMethod(application, "New Status Dropdown", "statuspage_newstatusdropdown", xml);
					WebElement selectNewStatusvalue= getwebelement(xml.getlocator("//locators/" + application + "/statuspage_newstatusdropdownvalue"));
					Clickon(selectNewStatusvalue);
					String NewStatusvalue= getwebelement(xml.getlocator("//locators/" + application + "/statuspage_newstatusdropdownvalue")).getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue);
					click_commonMethod(application, "OK", "statuspage_okbutton", xml);
					Thread.sleep(2000);
					scrollToTop();
					compareText(application, "Device status update success message", "Sync_successmsg", "Device Status changed successfully", xml);
					Thread.sleep(1000);
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
										for (int j = 0; j < numofrows; j++) {
											try {

												String Devicehistorydata = results.get(j).getText();
												System.out.println(Devicehistorydata);
												if (Devicehistorydata.contains(NewStatusvalue)) 
												{
													ExtentTestManager.getTest().log(LogStatus.PASS, "Device status history table has data");
													Thread.sleep(2000);
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

													GetText(application, "Changed By", "statuspage_changedbyvalue");
													Thread.sleep(2000);
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
									ExtentTestManager.getTest().log(LogStatus.INFO, "No interfaces added");

								}
							}

					}else {

						System.out.println("No data available in table");
						Log.info("No data available in table");
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No data available in table");
					}

					//verify view interfaces page
					Thread.sleep(2000);
					click_commonMethod(application, "View Interfaces", "status_viewinterfaceslink", xml);
					Thread.sleep(2000);
					compareText(application, "Interface page header", "viewinterfacepage_header", "Interfaces", xml);
					compareText(application, "Interface page subheader", "viewinterfacepage_interfacesubheader", "Interfaces", xml);
					String AddedInterface= getwebelement("//div[@role='grid']//div[@ref='eBodyViewport']/div").getAttribute("style");
					if(!AddedInterface.contains("height: 1px"))
					{
						compareText(application, "Device Name column header", "viewinterface_devicenamecolumnheader", "Device Name", xml);
						compareText(application, "Interface Name column header", "interfacename_columnheader", "Interface Name", xml);
						compareText(application, "Interface Address column header", "interfaceaddress_columnheader", "Interface Address", xml);
						WebElement InterfaceAddressRowValue= getwebelement(xml.getlocator("//locators/" + application + "/interfacetype_rowvalue"));
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

						Thread.sleep(1000);
						click_commonMethod(application, "Close", "viewinterface_closebutton", xml);
						Thread.sleep(2000);
						click_commonMethod(application, "View Interfaces", "status_viewinterfaceslink", xml);
						Thread.sleep(2000);

						if(edit_interfacename.equalsIgnoreCase("null"))
						{
							interfacenameValue= interfacename;
						}
						else
						{
							interfacenameValue= edit_interfacename;
						}
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
										for (int p = 0; p < numofrows; p++) {
											try {

												String AddedInterfacedata = results.get(p).getText();
												System.out.println(AddedInterfacedata);
												if (AddedInterfacedata.equalsIgnoreCase(interfacenameValue)) {

													String InterfaceNameRowID= getwebelement("//div[@role='gridcell'][text()='"+interfacenameValue+"']/parent::div[@role='row']").getAttribute("row-id");
													System.out.println("Interface row id: "+InterfaceNameRowID);

													//verify interface values in table
													String DeviceNamevalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='deviceName']").getText();
													ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Interface Device Name value is displayed as : "+DeviceNamevalue);
													String InterfaceNamevalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='name']").getText();
													ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Interface Name value is displayed as : "+InterfaceNamevalue);
													String InterfaceAddressvalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='address']").getText();
													ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Interface Address value is displayed as : "+InterfaceAddressvalue);
													WebElement InterfaceAddressRowValue1= getwebelement(xml.getlocator("//locators/" + application + "/interfacetype_rowvalue"));
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
										Thread.sleep(3000);
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
						compareText(application, "Name", "interface_statuspage_namevalue", interfacenameValue, xml);
						compareText(application, "Interface Address", "interface_statuspage_interfaceaddressvalue", InterfaceAddress, xml);
						GetText(application, "Status", "status_statusvalue");
						click_commonMethod(application, "New Status Dropdown", "interface_statuspage_newstatusdropdown", xml);
						WebElement selectNewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue"));
						Clickon(selectNewStatusvalue1);
						String NewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue")).getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue1);
						click_commonMethod(application, "OK", "interface_statuspage_okbutton", xml);
						Thread.sleep(2000);
						scrollToTop();
						compareText(application, "Interface status update success message", "Sync_successmsg", "Interface Status changed successfully", xml);
						Thread.sleep(1000);
						scrolltoend();
						Thread.sleep(1000);
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
										for (int q = 0; q < numofrows; q++) {
											try {
												String Interfacehistorydata = results.get(q).getText();
												System.out.println(Interfacehistorydata);
												if (Interfacehistorydata.contains(NewStatusvalue1)) 
												{
													ExtentTestManager.getTest().log(LogStatus.PASS, "Interface status history table has data");
													Thread.sleep(2000);
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

													GetText(application, "Changed By", "interface_statuspage_changedbyvalue");
													Thread.sleep(2000);
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
					Thread.sleep(2000);

					//verify synchronization panel column values
					Thread.sleep(2000);
					scrolltoend();
					if(!edit_asrdevicename.equalsIgnoreCase("null"))
					{
						compareText(application, "Device", "synchronization_devicevalue", edit_asrdevicename, xml);
					}
					else
					{
						compareText(application, "Device", "synchronization_devicevalue", ASRDeviceNameValue, xml);
					}

					GetText(application, "Sync Status", "synchronization_syncstatusvalue");

					//verify smarts value
					GetText(application, "Smarts", "smartsvalue_asrdevicepanel");
					//verify smarts date time 
					try {
						String GMTValue;
						String Smartsvalue= getwebelement(xml.getlocator("//locators/" + application + "/smarts_datetime_asrdevicepanel")).getText();
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
					GetText(application, "Fetch Interfaces", "fetchinterfacevalue_asrdevicepanel");
					//verify fetch interfaces date time
					try {
						String GMTValue;
						String FetchInterfacesvalue= getwebelement(xml.getlocator("//locators/" + application + "/fetchinterface_datetime_asrdevicepanel")).getText();
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

					//	//verify vistamart device value
					//	GetText(application, "VistaMart Device", "vistamartvalue_asrdevicepanel");
					//	//verify vistamart device date time
					//	try {
					//		String GMTValue;
					//		String VistaMartDevicevalue= getwebelement(xml.getlocator("//locators/" + application + "/vistamart_datetime_asrdevicepanel")).getText();
					//		String VistaMartDevice_DateTimevalue= "";
					//		if (VistaMartDevicevalue.length() > 20) 
					//		{
					//			VistaMartDevice_DateTimevalue = VistaMartDevicevalue.substring(VistaMartDevicevalue.length() - 20);
					//			System.out.println("last 20 characters:"+VistaMartDevice_DateTimevalue);
					//		} 
					//		else 
					//		{
					//			VistaMartDevice_DateTimevalue = VistaMartDevicevalue;
					//		}
					//
					//		ExtentTestManager.getTest().log(LogStatus.PASS, "Vistamart Device date value is displayed as: "+VistaMartDevice_DateTimevalue);
					//		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");
					//		if (VistaMartDevice_DateTimevalue.length() > 3) 
					//		{
					//			GMTValue = VistaMartDevice_DateTimevalue.substring(VistaMartDevice_DateTimevalue.length() - 3);
					//		} 
					//		else
					//		{
					//			GMTValue = VistaMartDevice_DateTimevalue;
					//		}
					//		assertEquals(GMTValue, "GMT");
					//
					//	}catch(Exception e)
					//	{
					//		e.printStackTrace();
					//	}

					//verify synchronize link
					Thread.sleep(2000);
					click_commonMethod(application, "Synchronize", "synchronization_synchronizelink", xml);
					Thread.sleep(1000);
					scrollToTop();
					Thread.sleep(2000);
					compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this device.", xml);
					Thread.sleep(3000);
					scrollToTop();
					Thread.sleep(2000);

					//verify device name link in status panel
					click(application, "Device", "status_devicevalue", xml);
					Thread.sleep(2000);
					//compareText(application, "View Device header", "viewasrdevice_header", "Device", xml);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to 'View Device' page");
					driver.navigate().back();
					Thread.sleep(1000);

					//verify device name link in synchronization panel
					click(application, "Device", "synchronization_devicevalue", xml);
					Thread.sleep(2000);
					//compareText(application, "View Device header", "viewasrdevice_header", "Device", xml);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to 'View Device' page");
					Thread.sleep(2000);
					clickOnBreadCrumb(application, sid);
					Thread.sleep(2000);

				}
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}

	}

	public void verify_Cisco_RouterTools(String application, String imspoplocation_dropdownvalue, String commandIPv4, String commandIPv6, String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException {

		Thread.sleep(3000);
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "asrdevice_header", xml);
		Thread.sleep(1000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevicename"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();

			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				System.out.println(AddedDeviceNameText);
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				String IMSPopLocation_Code= imsPopLocationValue(application, imspoplocation_dropdownvalue);
				if(AddedDeviceNameText.contains(IMSPopLocation_Code))
				{
					WebElement AddedDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_viewlink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_ViewLink);
					Thread.sleep(2000);
					scrollToTop();
					ASRDevice_ManagementAddress=getwebelement(xml.getlocator("//locators/" + application + "/asrmanagementaddess_viewpage")).getText();
					ScrolltoElement(application, "routertools_header", xml);
					Thread.sleep(1000);

					//Command IPV4	
					addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);

					hostnametextField_IPV4(application, commandIPv4, ASRDevice_ManagementAddress);
					vrfNametextField_IPV4(application, commandIPv4, vrfname_ipv4);

					executeCommandAndFetchTheValue(application, "executebutton_Ipv4");

					//Commmand IPV6
					addDropdownValues_commonMethod(application, "Command IPV6", "commandIPV6_dropdown" , commandIPv6, xml);

					hostnametextField_IPV6(application, commandIPv6, ASRDevice_ManagementAddress);

					vrfNametextField_IPV6(application, commandIPv6, vrfname_ipv6);

					executeCommandAndFetchTheValue(application, "executebutton_IPv6");
					break;
				}
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
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
		boolean IPV6availability=false;
		try {  
			IPV6availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv6_hostnameTextfield")).isDisplayed();

			if(IPV6availability) {

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

	public void executeCommandAndFetchTheValue(String application, String executeButton) throws InterruptedException, DocumentException {

		click_commonMethod(application, "Execute", executeButton, xml);

		boolean remarkField=false;
		try {	
			remarkField=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).isDisplayed();
			if(remarkField) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Remark' text field is displaying");
				System.out.println( "'Remark' text field is displaying");

				String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, "value under 'Remark' field displaying as "+ remarkvalue);
				System.out.println("value under 'Remark' field displaying as "+ remarkvalue);

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Remark' text field is not displaying");
				System.out.println( "'Remark' text field is not displaying");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Remark' text field is not displaying");
			System.out.println("'Remark' text field is not displaying");
		}

	}

	public void verify_CiscoVendor_AddInterface(String application, String interfacename, String addInterface_Allocate, String configuration_dropdownvalue, String interfaceAddress, String virtualTemplate, String cpeAddressRange, String localPreShareKey, String remotePreShareKey, String identityEmail, String sid) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "commandIPV4_dropdown", xml);
		Thread.sleep(1000);
		//compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
		click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
		click_commonMethod(application, "Add Interface/Link", "addinterface_link", xml);
		Thread.sleep(2000);
		scrollToTop();
		GetText(application, "Add Interface header", "addinterface_header");
		scrolltoend();
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(1000);
		scrollToTop();
		//verify warning messages in add interface page
		warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);

		//Add Interface
		if(addInterface_Allocate.equalsIgnoreCase("Yes"))
		{
			click_commonMethod(application, "Allocate", "allocate_button", xml);
			Thread.sleep(2000);
			addtextFields_commonMethod(application, "Interface", "interfacename_textfield", interfacename, xml);
			String InterfaceNameValue= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_textfield")).getAttribute("value");
			compareText_fromtextFields(application, "Interface", "interfacename_textfield", InterfaceNameValue, xml);
			String InterfaceAddressValue= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_textfield")).getAttribute("value");
			compareText_fromtextFields(application, "Interface Address", "interfaceaddress_textfield", InterfaceAddressValue, xml);
			String VirtualTemplateValue= getwebelement(xml.getlocator("//locators/" + application + "/virtualtemplate_textfield")).getAttribute("value");
			compareText_fromtextFields(application, "Virtual Template", "virtualtemplate_textfield", VirtualTemplateValue, xml);
			String CPEAddressRangeValue= getwebelement(xml.getlocator("//locators/" + application + "/cpeaddressrange_textfield")).getAttribute("value");
			compareText_fromtextFields(application, "CPE Address Range", "cpeaddressrange_textfield", CPEAddressRangeValue, xml);
			String LocalPreshareKeyValue= getwebelement(xml.getlocator("//locators/" + application + "/localpresharekey_textfield")).getAttribute("value");
			compareText_fromtextFields(application, "Local pre-share key", "localpresharekey_textfield", LocalPreshareKeyValue, xml);
			String RemotePreshareKeyValue= getwebelement(xml.getlocator("//locators/" + application + "/remotepresharekey_textfield")).getAttribute("value");
			compareText_fromtextFields(application, "Remote pre-share key", "remotepresharekey_textfield", RemotePreshareKeyValue, xml);
			String IdentityEmailValue= getwebelement(xml.getlocator("//locators/" + application + "/identityemail_textfield")).getAttribute("value");
			compareText_fromtextFields(application, "Identity Email", "identityemail_textfield", IdentityEmailValue, xml);

			scrolltoend();
			//configuration panel in add interface page
			compareText(application, "Configuration", "configuration_header", "Configuration", xml);
			addDropdownValues_commonMethod(application, "Configuration", "configuration_dropdown", configuration_dropdownvalue, xml);
			click_commonMethod(application, "Generate Configuration", "generateconfiguration_link", xml);
			Thread.sleep(2000);
			GetText(application, "Configuration", "configuration_textarea");
			Thread.sleep(1000);
			click_commonMethod(application, "Save Configuration", "saveconfiguration_link", xml);
			Thread.sleep(2000);
		}
		else
		{
			addtextFields_commonMethod(application, "Interface", "interfacename_textfield", interfacename, xml);
			addtextFields_commonMethod(application, "Interface Address", "interfaceaddress_textfield", interfaceAddress, xml);
			addtextFields_commonMethod(application, "Virtual Template", "virtualtemplate_textfield", virtualTemplate, xml);
			addtextFields_commonMethod(application, "CPE Address Range", "cpeaddressrange_textfield", cpeAddressRange, xml);
			addtextFields_commonMethod(application, "Local pre-share key", "localpresharekey_textfield", localPreShareKey, xml);
			addtextFields_commonMethod(application, "Remote pre-share key", "remotepresharekey_textfield", remotePreShareKey, xml);
			addtextFields_commonMethod(application, "Identity Email", "identityemail_textfield", identityEmail, xml);

			scrolltoend();
			//configuration panel in add interface page
			compareText(application, "Configuration", "configuration_header", "Configuration", xml);
			addDropdownValues_commonMethod(application, "Configuration", "configuration_dropdown", configuration_dropdownvalue, xml);
			click_commonMethod(application, "Generate Configuration", "generateconfiguration_link", xml);
			Thread.sleep(2000);
			GetText(application, "Configuration", "configuration_textarea");
			Thread.sleep(1000);
			click_commonMethod(application, "Save Configuration", "saveconfiguration_link", xml);
			Thread.sleep(2000);
		}

		click_commonMethod(application, "OK", "okbutton", xml);

		Thread.sleep(5000);
		compareText(application, "Interface Added success message", "successmsg", "Interface added successfully", xml);
		Thread.sleep(2000);

		scrollToTop();
		clickOnBreadCrumb(application, sid);
		Thread.sleep(2000);
	}

	public void verify_CiscoVendor_EditInterface(String application, String interfacename, String edit_interfacename, String configuration_dropdownvalue, String edit_asrdevicename, String editInterface_Allocate, String edit_configuration_dropdownvalue, String edit_interfaceAddress, String edit_virtualTemplate, String edit_CPEAddressRange, String edit_localPreshareKey, String edit_remotePreshareKey, String edit_identityEmail) throws InterruptedException, DocumentException, IOException {

		//edit Interface
		ScrolltoElement(application, "asrdevice_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
		{
			click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		}
		Thread.sleep(1000);
		WebElement SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", interfacename));
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface checkbox");
			Thread.sleep(1000);

			if(!edit_asrdevicename.equalsIgnoreCase("null"))
			{
				WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", edit_asrdevicename));
				Clickon(AddedDevice_Interface_Actiondropdown);
			}
			else
			{
				WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", ASRDeviceNameValue));
				Clickon(AddedDevice_Interface_Actiondropdown);
			}


			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			compareText(application, "Edit Interface", "editinterface_header", "Edit", xml);

			if(editInterface_Allocate.equalsIgnoreCase("Yes"))
			{

				click_commonMethod(application, "Allocate", "allocate_button", xml);
				Thread.sleep(2000);
				if(!edit_interfacename.equalsIgnoreCase("null"))
				{
					edittextFields_commonMethod(application, "Interface", "interfacename_textfield", edit_interfacename, xml);
				}
				else
				{
					edittextFields_commonMethod(application, "Interface", "interfacename_textfield", interfacename, xml);
				}
				String InterfaceAddressValue= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_textfield")).getAttribute("value");
				compareText_fromtextFields(application, "Interface Address", "interfaceaddress_textfield", InterfaceAddressValue, xml);
				String VirtualTemplateValue= getwebelement(xml.getlocator("//locators/" + application + "/virtualtemplate_textfield")).getAttribute("value");
				compareText_fromtextFields(application, "Virtual Template", "virtualtemplate_textfield", VirtualTemplateValue, xml);
				String CPEAddressRangeValue= getwebelement(xml.getlocator("//locators/" + application + "/cpeaddressrange_textfield")).getAttribute("value");
				compareText_fromtextFields(application, "CPE Address Range", "cpeaddressrange_textfield", CPEAddressRangeValue, xml);
				String LocalPreshareKeyValue= getwebelement(xml.getlocator("//locators/" + application + "/localpresharekey_textfield")).getAttribute("value");
				compareText_fromtextFields(application, "Local pre-share key", "localpresharekey_textfield", LocalPreshareKeyValue, xml);
				String RemotePreshareKeyValue= getwebelement(xml.getlocator("//locators/" + application + "/remotepresharekey_textfield")).getAttribute("value");
				compareText_fromtextFields(application, "Remote pre-share key", "remotepresharekey_textfield", RemotePreshareKeyValue, xml);
				String IdentityEmailValue= getwebelement(xml.getlocator("//locators/" + application + "/identityemail_textfield")).getAttribute("value");
				compareText_fromtextFields(application, "Identity Email", "identityemail_textfield", IdentityEmailValue, xml);

				scrolltoend();
				//configuration panel in add interface page
				compareText(application, "Configuration", "configuration_header", "Configuration", xml);
				if(!edit_configuration_dropdownvalue.equalsIgnoreCase("null"))
				{
					addDropdownValues_commonMethod(application, "Configuration", "configuration_dropdown", edit_configuration_dropdownvalue, xml);
				}
				else
				{
					addDropdownValues_commonMethod(application, "Configuration", "configuration_dropdown", configuration_dropdownvalue, xml);
				}
				click_commonMethod(application, "Generate Configuration", "generateconfiguration_link", xml);
				Thread.sleep(2000);
				GetText(application, "Configuration", "configuration_textarea");
				Thread.sleep(1000);
				click_commonMethod(application, "Save Configuration", "saveconfiguration_link", xml);
				Thread.sleep(2000);
			}
			else
			{

				if(!edit_interfacename.equalsIgnoreCase("null"))
				{
					edittextFields_commonMethod(application, "Interface", "interfacename_textfield", edit_interfacename, xml);
				}
				else
				{
					String EditInterfaceNameValue= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_textfield")).getAttribute("value");
					compareText_fromtextFields(application, "Interface", "interfacename_textfield", EditInterfaceNameValue, xml);
				}

				if(!edit_interfaceAddress.equalsIgnoreCase("null"))
				{
					edittextFields_commonMethod(application, "Interface Address", "interfaceaddress_textfield", edit_interfaceAddress, xml);
				}
				else
				{
					String EditInterfaceAddressValue= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_textfield")).getAttribute("value");
					compareText_fromtextFields(application, "Interface Address", "interfaceaddress_textfield", EditInterfaceAddressValue, xml);
				}

				if(!edit_virtualTemplate.equalsIgnoreCase("null"))
				{
					edittextFields_commonMethod(application, "Virtual Template", "virtualtemplate_textfield", edit_virtualTemplate, xml);
				}
				else
				{
					String EditVirtualTemplateValue= getwebelement(xml.getlocator("//locators/" + application + "/virtualtemplate_textfield")).getAttribute("value");
					compareText_fromtextFields(application, "Virtual Template", "virtualtemplate_textfield", EditVirtualTemplateValue, xml);
				}

				if(!edit_CPEAddressRange.equalsIgnoreCase("null"))
				{
					edittextFields_commonMethod(application, "CPE Address Range", "cpeaddressrange_textfield", edit_CPEAddressRange, xml);
				}
				else
				{
					String EditCPEAddressRangeValue= getwebelement(xml.getlocator("//locators/" + application + "/cpeaddressrange_textfield")).getAttribute("value");
					compareText_fromtextFields(application, "CPE Address Range", "cpeaddressrange_textfield", EditCPEAddressRangeValue, xml);
				}

				if(!edit_localPreshareKey.equalsIgnoreCase("null"))
				{
					edittextFields_commonMethod(application, "Local pre-share key", "localpresharekey_textfield", edit_localPreshareKey, xml);
				}
				else
				{
					String EditLocalPreshareKeyValue= getwebelement(xml.getlocator("//locators/" + application + "/localpresharekey_textfield")).getAttribute("value");
					compareText_fromtextFields(application, "Local pre-share key", "localpresharekey_textfield", EditLocalPreshareKeyValue, xml);
				}
				if(!edit_remotePreshareKey.equalsIgnoreCase("null"))
				{
					edittextFields_commonMethod(application, "Remote pre-share key", "remotepresharekey_textfield", edit_remotePreshareKey, xml);
				}
				else
				{
					String EditRemotePreshareKeyValue= getwebelement(xml.getlocator("//locators/" + application + "/remotepresharekey_textfield")).getAttribute("value");
					compareText_fromtextFields(application, "Remote pre-share key", "remotepresharekey_textfield", EditRemotePreshareKeyValue, xml);
				}

				if(!edit_identityEmail.equalsIgnoreCase("null"))
				{
					edittextFields_commonMethod(application, "Identity Email", "identityemail_textfield", edit_identityEmail, xml);
				}
				else
				{
					String EditIdentityEmailValue= getwebelement(xml.getlocator("//locators/" + application + "/identityemail_textfield")).getAttribute("value");
					compareText_fromtextFields(application, "Identity Email", "identityemail_textfield", EditIdentityEmailValue, xml);
				}

				scrolltoend();

				//configuration panel in add interface page
				compareText(application, "Configuration", "configuration_header", "Configuration", xml);
				if(!edit_configuration_dropdownvalue.equalsIgnoreCase("null"))
				{
					addDropdownValues_commonMethod(application, "Configuration", "configuration_dropdown", edit_configuration_dropdownvalue, xml);
				}
				else
				{
					addDropdownValues_commonMethod(application, "Configuration", "configuration_dropdown", configuration_dropdownvalue, xml);
				}
				click_commonMethod(application, "Generate Configuration", "generateconfiguration_link", xml);
				Thread.sleep(2000);
				GetText(application, "Configuration", "configuration_textarea");
				Thread.sleep(1000);
				click_commonMethod(application, "Save Configuration", "saveconfiguration_link", xml);
				Thread.sleep(2000);

			}

			scrolltoend();
			click_commonMethod(application, "OK", "okbutton", xml);
			Thread.sleep(2000);
			verifysuccessmessage(application, "Interface updated successfully");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface is not added");
		}

	}

	public void selectInterfacelinkforDevice(String application, String imspoplocation_dropdownvalue) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "asrdevice_header", xml);
		Thread.sleep(1000);
		ExtentTestManager.getTest().log(LogStatus.INFO, "check 'Select Interface' link");

		String IMSPoplocation_Code= imsPopLocationValue(application, imspoplocation_dropdownvalue);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevices_list"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				if(AddedDeviceNameText.contains(IMSPoplocation_Code))
				{
					WebElement AddedDevice_SelectInterfacesLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_SelectInterfaceslink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_SelectInterfacesLink);
					Thread.sleep(5000);
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

	public void SelectInterfacetoremovefromservice(String application, String interfacename, String vendormodel)
			throws IOException, InterruptedException, DocumentException {

		ScrolltoElement(application, "viewpage_vendormodel", xml);
		Thread.sleep(2000);
		Cisco_selectRowforInterfaceInService(application, interfacename);
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

									Thread.sleep(3000);

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
		Thread.sleep(3000);

	}

	public void SelectInterfacetoaddwithservcie(String application, String interfacename, String sid)
			throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "interfacesToSelect_header", xml);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		//Cisco_selectrowforInterfaceToselecttable(application, interfacename);
		
		click_commonMethod(application, "Interfaces To Select Filter", "InteraceColumn_Filter", xml);
		addtextFields_commonMethod(application, "Interface search", "InterfacefilterTxt", interfacename, xml);
		WebElement InterfaceName_GridSelect= getwebelement(xml.getlocator("//locators/" + application + "/interface_gridselect").replace("value", interfacename));
		Clickon(InterfaceName_GridSelect);
		ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected under 'Interface to select' table");
		Thread.sleep(8000);

		ScrolltoElement(application, "interfacesToSelect_header", xml);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		click_commonMethod(application, "Action Dropdown", "InterfaceToselect_Actiondropdown", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Add", "InterfaceToselect_addbuton", xml);
		Thread.sleep(1000);
		scrollToTop();
		clickOnBreadCrumb(application, sid);
		Thread.sleep(2000);
	}

	public void Cisco_selectrowforInterfaceToselecttable(String application, String interfacename)
			throws IOException, InterruptedException, DocumentException {

		ScrolltoElement(application, "interfacesToSelect_header", xml);
		int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_totalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in Interface to select table is: " + TotalPages);


		if (TotalPages != 0) {
			ab:
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_currentpage")));
					int Current_page = Integer.parseInt(CurrentPage);

					assertEquals(k, Current_page);

					System.out.println("Currently we are in page number: " + Current_page);

					List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/InterfaceToselect_Checkbox").replace("value", interfacename));

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
									WebElement interfaceCheckbox= getwebelement(xml.getlocator("//locators/" + application + "/Interfacetoselect").replace("value", interfacename));
									scrolltoview(interfaceCheckbox);
									((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
									results.get(i).click();
									ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected under 'Interface to select' table");
									Thread.sleep(8000);

									ScrolltoElement(application, "InterfaceInService_panelHeader", xml);
									Thread.sleep(1000);
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + application + "/InterfaceToselect_Actiondropdown")));

									Thread.sleep(5000);

									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_addbuton")));
									Thread.sleep(3000);
									ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is added to service");

									break ab;

								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", interfacename));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								ExtentTestManager.getTest().log(LogStatus.FAIL, " Failure on selecting an Interface to add with service ");
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


	public void addTrunkSiteOrder(String application, String trunkGroupOrder, String trunkgroupOrderNumber) throws InterruptedException, DocumentException, IOException { 

		boolean trunkPanel=false;
		boolean siteOrderpage=false;
		boolean trunkgrupOrderErrMsg= false;

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

		trunkPanel= getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel")).isDisplayed();
		if(trunkPanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");
			System.out.println("'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");


			//Perform Add Site order
			click_commonMethod(application, "Add TrunkGroup/SiteOrder link", "addTrunkSiteOrderlink", xml);

			siteOrderpage= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
			if(siteOrderpage) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is displaying as expected");
				System.out.println("'Add Trunk Group/Site Order' page is displaying as expected");

				//verify mandatory Warning Message
				click_commonMethod(application, "OK", "trunk_okButton", xml);

				warningMessage_commonMethod(application, "trunkGrouporder_warningmsg", "Trunk Group Order", xml);

				warningMessage_commonMethod(application, "trunkGroupOrderName_warningmsg", "Trunk group Order Number", xml);

				//Add trunkgroup Order checkbox
				if(trunkGroupOrder.equalsIgnoreCase("yes")) {
					addCheckbox_commonMethod(application, "trunkGroupOrder_checkbox", "Trunk Group Order", trunkGroupOrder, "No", xml);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " ' Trunk Group order' checkbox is a mandatory field. No values passed");
					System.out.println(" ' Trunk Group order' checkbox is a mandatory field. No values passed");

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
							System.out.println(" Error message we are getting as: "+ actualMsg);
						}
						else if(actualMsg.equalsIgnoreCase("Trunk Group created successfully")) {

							ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as 'Trunk Group created successfully'");
							System.out.println(" Success Message displays as 'Trunk Group created successfully'");
						}

					}
				}catch(Exception e) {
					e.printStackTrace();

				}

			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is not displaying");
				System.out.println("'Add Trunk Group/Site Order' page is not displaying");
			}

		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
			System.out.println("'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
		}
	}

	public void editSiteOrder(String application, String siteOrderName, String trunkGroupOrder, String trunkGrouporderNumber) throws InterruptedException, DocumentException, IOException {

		boolean siteOrderpage=false;
		boolean trunkgrupOrderErrMsg=false;

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

		WebElement editlink=getwebelement("//div[div[span[text()='"+ siteOrderName +"']]]//span[text()='Edit']");
		Clickon(editlink);

		siteOrderpage= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
		if(siteOrderpage) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is displaying as expected");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying as expected");

			//Trunk group Order
			if(trunkGroupOrder.equalsIgnoreCase("no")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Trunk Group Order' is a mandatory field. It cannot be unselected");
				System.out.println("'Trunk Group Order' is a mandatory field. It cannot be unselected");
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
						System.out.println(" Error message we are getting as: "+ actualMsg);
					}
					else if(actualMsg.contains("Trunk Group successfully updated")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as 'Trunk Group successfully updated.'");
						System.out.println(" Success Message displays as 'Trunk Group successfully updated.'");
					}

				}
			}catch(Exception e) {
				e.printStackTrace();

			}

		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is not displaying");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying");
		}
	}

	public void verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel(String application, String siteOrderName) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		scrolltoend();
		Thread.sleep(1000);

		boolean siteOrderUnderTrunkPanel=false;


		siteOrderUnderTrunkPanel=getwebelement("//span[text()='"+ siteOrderName +"']").isDisplayed();

		if(siteOrderUnderTrunkPanel) {

			ExtentTestManager.getTest().log(LogStatus.PASS, siteOrderName + " 'Site Order' is displaying under 'Trunk' panel");
			System.out.println(siteOrderName + " 'Site Order' is displaying under 'Trunk' panel");

			//Click on Add trunk link	
			String addTrunklinkXpath="//div[div[span[text()='"+ siteOrderName +"']]]/following-sibling::div//span[text()='Add Trunk']";
			click_commonMethod_PassingWebelementDirectly(application, "Add Trunk", addTrunklinkXpath, xml);
			Thread.sleep(1000);

		}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, siteOrderName + " 'Site Order' is not displaying under 'Trunk' panel");
			System.out.println(siteOrderName + " 'Site Order' is not displaying under 'Trunk' panel");
		}
	}


	public void addTrunk(String application, String newCustomerCreation, String newCustomerName, String existingCustomerName, String sid, String country, String CDRdelivery, String gateway, String quality,
			String SIPURI, String resellerID_value, String ipAddresstype, String SIPsignallingPort, String thirdpartyinternet, String vlanTag, String subInterfaceSlot, String signallngZone, 
			String callAdmissionControl, String callrateLimit,String PSXmanualConfigvalue, String GSXmanualConfigvalue, String callLimit, String limitNumber, 
			String callrateLimiteValue, String SBCmanualconfigValue, String cosprofile_value, String lanrange_value, String numberporting_checkbox, String CLIPScreeningandCLIRperCall, 
			String clirPermanent, String clipNoScreening, String clipMainNumber, String presentationNumbers, String presentationnumber_value, String customerdefaultnumber_value, 
			String egressnumberformat_dropdownvalue, String incomingroutingonDDIlevel_checkbox, String PSXmanualConfigTG_value, String PSXmanualConfigDDI_value, String DRusingTDM_checkbox, 
			String codec_value, String faxdiversionnumber_value, String partialnumberreplacement_checkbox, String cpemanualconfig_checkbox, String vlanTag_FRASBC_value) throws IOException, InterruptedException, DocumentException {   

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
		String vlanUpdatedvalue3=null;
		String vlanUpdatedvalue5=null;

		String customerName=null;

		Thread.sleep(1000);
		scrolltoend();
		Thread.sleep(2000);


		click_commonMethod(application, "OK", "trunk_okButton", xml);

		scrollToTop();
		Thread.sleep(2000);

		if(newCustomerCreation.equalsIgnoreCase("yes")){
			customerName= newCustomerName;
		}
		else
		{
			customerName= existingCustomerName;
		}

		//Trunk Group Description
		String expectedValue1=customerName+"_"+sid+"_"+"IPVL";
		Thread.sleep(1000);
		compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

		//Primary trunk group
		WebElement PrimaryTrunkGroup= getwebelement(xml.getlocator("//locators/" + application + "/primaryTrunkGroup_Dropdown"));
		webelementpresencelogger(PrimaryTrunkGroup, "Primary Trunk Group");

		//VOIP Protocol
		GetText(application, "VOIP Protocol", "voipProtocol_Dropdown");
		String voip_code="S";

		//SIP URI
		addtextFields_commonMethod(application, "SIP URI", "SIPURI_textField", SIPURI, xml);
		//message displaying under "SIP URI" text field	
		methodToFindMessagesUnderTextField(application, "SIPURI_defaultValue_textvalue", "SIP URI", "Default URI: va.sip.colt.net");

		//SIP Signalling Port
		addtextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", SIPsignallingPort, xml);
		//message displaying under "SIP Signalling Port" text field	
		methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port: 5060");

		//Billing Country
		warningMessage_commonMethod(application, "country_warningMessage", "Billing Country", xml);   //validate warning message
		selectValueInsideDropdown(application, "billingCoutry_Dropdown", "Billing Country", country, xml);

		String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
		System.out.println("country dropdown value is: "+country_code);

		//CDR Delivery
		selectValueInsideDropdown(application, "CDRdelivery_Dropdown", "CDR Delivery", CDRdelivery, xml);
		String CDR_code=CDRdelivery_code(application, CDRdelivery);

		//ResellerID
		String ResellerIDValue= resellerID(application, resellerID_value);

		//Customer ID
		String CustomerID= getwebelement(xml.getlocator("//locators/" + application + "/customerid")).getAttribute("value");

		//Gateway
		selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", gateway, xml);
		gatewayCode=gateway_code(application, gateway);
		Thread.sleep(2000);

		//Quality
		selectValueInsideDropdown(application, "quality_Dropdown", "Quality", quality, xml);
		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");

		//Trunk Name
		String trunkName=country_code+gatewayCode+voip_code+"L"+CustomerID+"00"+CDR_code+ResellerIDValue+quality_code+"00";

		int totalLen=trunkName.length();
		System.out.println("Total lenth of 'Trunk Group' field is "+ totalLen);
		System.out.println("Trunk name is "+ trunkName);
		if(totalLen==20) {
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunkName, xml);
			primarytrunkGroupname=trunkName;
			System.out.println("Trunk group name is: "+primarytrunkGroupname);
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Trunk Group Name' length is: "+totalLen);
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunkName, xml);
		}

		ScrolltoElement(application, "SIPURI_textField", xml);
		//COS Profile
		selectValueInsideDropdown(application, "cosprofile", "COS Profile", cosprofile_value, xml);

		//LAN Range
		addtextFields_commonMethod(application, "LAN Range", "lanrange", lanrange_value, xml);

		//IP Address Type
		selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", ipAddresstype, xml);
		Thread.sleep(2000);

		//Internet Based Customer
		String InternetBasedCustomer_Checked= getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox")).getAttribute("checked");
		String InternetBasedCustomer_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox")).getAttribute("disabled");
		if(InternetBasedCustomer_Checked==null && InternetBasedCustomer_Disabled==null)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Internet Based Customer' is not checked and disabled by default");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Internet Based Customer' is checked and disabled by default");
		}

		//Splitting the Gateway functionality into 2
		if(!gateway.contains("SBC")) {

			if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

				addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet, "Yes", xml);

				String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				if(vlanDefaultvalue.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when '3rd Party Internet' is selected");
				}else {

					ExtentTestManager.getTest().log(LogStatus.PASS, "When '3rd Party Internet' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
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
							System.out.println("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}

					}else {

						//VLAN Tag
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);
						String vlanTagUpdatedvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

						//Sub Interface slot
						selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);

						if(subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanTagUpdatedvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanTagUpdatedvalue+NIGgroupdefaultValue_last;
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						else if(!subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanTagUpdatedvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanTagUpdatedvalue+NIGgroupdefaultValue_last;
							System.out.println("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
					}
				}
			}
			//3rd party internet checkbox not selected 
			else {
				if(vlanTag.equalsIgnoreCase("null")) {
					String vlanDefaultvalue1=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					//Sub Interface slot
					selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);

					if(subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanDefaultvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanDefaultvalue1+NIGgroupdefaultValue_last;
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
					else if(!subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanDefaultvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanDefaultvalue1+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}

				}else {

					//VLAN Tag
					cleartext(application, "VLAN Tag", "vlanTag_textField");
					edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);
					String vlanUpdatedvalue1=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					//Sub Interface slot
					selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);

					if(subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanUpdatedvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanUpdatedvalue1+NIGgroupdefaultValue_last;
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
					else if(!subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanUpdatedvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanUpdatedvalue1+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
				}

			}
		}

		else if(gateway.contains("SBC")) {
			if(gateway.startsWith("FRA")) {
				if(!thirdpartyinternet.equalsIgnoreCase("Yes")) {
					if(vlanTag.equalsIgnoreCase("null")) {
						String vlanDefaultvalue2=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

						//IP Interface
						boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
						if(ipInterface_Enabled) {
							System.out.println("'Ip Interface' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text field is disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						}

						compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values

					}
					else if(!vlanTag.equalsIgnoreCase("null")) {

						//VLAN Tag
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag_FRASBC_value, xml);
						String vlanUpdatedvalue2=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

						//IP Interface
						boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
						if(ipInterface_Enabled) {
							System.out.println("'Ip Interface' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text fieldis disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}

						ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue2;
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						addressContext=addressContextDefaultValue+vlanUpdatedvalue2;
						compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						}

						ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue2;
						compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values

					}

				}
				else if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//3rd party internet
					addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet, "No", xml);

					String vlanDefaultvalue3=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue3.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
					}else {
						//VLAN tag  
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag_FRASBC_value, xml);
						vlanUpdatedvalue3=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					}

					//IP Interface
					boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					if(ipInterface_Enabled) {
						System.out.println("'Ip Interface' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue3;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue3;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue3;
					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				}
			}
			else {
				if(!thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//VLAN tag  
					String vlanDefaultvalue4=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue4.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
					}else {
						//VLAN tag  
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag_FRASBC_value, xml);
						String vlanUpdatedvalue4=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					}

					//IP Interface
					boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					if(ipInterface_Enabled) {
						System.out.println("'Ip Interface' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values

				}

				if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//3rd party internet
					addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet, "No", xml);

					String vlanDefaultvalue5=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue5.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
					}else {
						//VLAN tag  
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag_FRASBC_value, xml);
						vlanUpdatedvalue5=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					}

					//IP Interface
					boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					if(ipInterface_Enabled) {
						System.out.println("'Ip Interface' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				}
			}
		}

		//Signalling Zone
		if(thirdpartyinternet.equalsIgnoreCase("yes")) {

			compareText_fromtextFields(application, "Signaling Zone", "signallingZone_textField", signallingZoneDefaultValue, xml);

			edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);

		}else {
			edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
		}

		//Number Porting checkbox
		addCheckbox_commonMethod(application, "numberporting_checkbox", "Number Porting", numberporting_checkbox, "no", xml);


		//CLI Features
		cliFeatures(application, CLIPScreeningandCLIRperCall, clirPermanent, clipNoScreening, clipMainNumber, presentationNumbers, presentationnumber_value);

		//Customer Default Number
		addtextFields_commonMethod(application, "Customer Default Number", "customerdefaultnumber_textfield", customerdefaultnumber_value, xml);

		//Invalid CLI Treatment
		boolean invalidCLITreatment_Allow= getwebelement(xml.getlocator("//locators/" + application + "/invalidclitreatment_allow")).isSelected();

		if(invalidCLITreatment_Allow)
		{
			//Multisite Default Number
			String MultisiteDefaultNumber= getwebelement(xml.getlocator("//locators/" + application + "/multisitedefaultnumber")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'MultiSite Default Number' value is displaying as "+MultisiteDefaultNumber);
		}
		else
		{
			click_commonMethod(application, "Block", "invalidclitreatment_block", xml);
		}

		//Egress Number Format
		selectValueInsideDropdown(application, "egressnumberformat_dropdown", "Egress Number Format", egressnumberformat_dropdownvalue, xml);

		//Call Admission Control
		addCheckbox_commonMethod(application, "callAdmissionControl_checkbox", "Call Admission Control", callAdmissionControl, "No", xml);

		if(callAdmissionControl.equalsIgnoreCase("yes")) {

			//Call Limit
			selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", callLimit, xml);

			if(callLimit.equalsIgnoreCase("Defined")) {
				addtextFields_commonMethod(application, "Limit Number", "limitNumber_textField", limitNumber, xml);
			}
		}

		//Call Rate Limit
		addCheckbox_commonMethod(application, "callrateLimit_checkbox", "Call Rate Limit", callrateLimit, "No", xml);
		if(callrateLimit.equalsIgnoreCase("yes")) {

			String callratelimitactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callRateLimitt_textField")).getAttribute("value");
			System.out.println(" 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);

			if(!callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(callrateLimiteValue);

				if(i>100) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less than 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
				System.out.println("'Call rate Limit' value is not edited");
			}
		}
		ScrolltoElement(application, "egressnumberformat_dropdown", xml);
		Thread.sleep(1000);
		//Usage of Incoming routing on DDI Level
		addCheckbox_commonMethod(application, "incomingroutingonDDIlevel_checkbox", "Usage of Incoming routing on DDI Level", incomingroutingonDDIlevel_checkbox, "Yes" ,xml);
		Thread.sleep(1000);

		//PSX Manual Configuration-Trunk Group 
		addCheckbox_commonMethod(application, "PSXmanualConfigTG_checkbox", "PSX Manual Configuration-Trunk Group", PSXmanualConfigTG_value, "No" ,xml);
		Thread.sleep(1000);
		//PSX Manual Configuration-DDI-Range
		addCheckbox_commonMethod(application, "PSXmanualConfigDDIRange_checkbox", "PSX Manual Configuration-DDI-Range", PSXmanualConfigDDI_value, "No" ,xml);
		Thread.sleep(1000);

		if(gateway.contains("SBC")) {
			//Manual Configuration on SBC
			addCheckbox_commonMethod(application, "SBCmanualconfig_checkbox", "Manual Configuration On SBC", SBCmanualconfigValue, "No", xml);
		}else {
			//Manual Configuration On GSX
			addCheckbox_commonMethod(application, "GSXmanualConfig_checkbox", "Manual Configuration On GSX", GSXmanualConfigvalue, "No", xml);
		}

		ScrolltoElement(application, "incomingroutingonDDIlevel_checkbox", xml);
		Thread.sleep(1000);
		//CPE Manual Configuration
		addCheckbox_commonMethod(application, "cpemanualconfig_checkbox", "CPE Manual Configuration", cpemanualconfig_checkbox, "No", xml);

		//DR using TDM
		addCheckbox_commonMethod(application, "DRusingTDM_checkbox", "DR Using TDM", DRusingTDM_checkbox, "No", xml);

		//Codec
		selectValueInsideDropdown(application, "codec_dropdown", "Codec", codec_value, xml);

		scrolltoend();
		//Fax Diversion Numbers
		addtextFields_commonMethod(application, "Fax Diversion Numbers", "faxdiversionnumbers_textfield", faxdiversionnumber_value, xml);
		click_commonMethod(application, "Fax Diversion Numbers Add arrow", "faxdiversion_addarrow", xml);

		//Partial Number Replacement
		addCheckbox_commonMethod(application, "partialnumberreplacement_checkbox", "Partial Number Replacement", partialnumberreplacement_checkbox, "No", xml);

		Thread.sleep(1000);
		scrolltoend();
		click_commonMethod(application, "OK", "trunk_okButton", xml);
		Thread.sleep(6000);
		verifysuccessmessage(application, "Trunk created successfully");

	}

	public String resellerID(String application, String resellerID_value) throws InterruptedException, DocumentException, IOException {

		String ResellerID=null;
		if(!resellerID_value.equalsIgnoreCase("null"))
		{
			cleartext(application, "Reseller ID", "resellerid");
			edittextFields_commonMethod(application, "Reseller ID", "resellerid", resellerID_value, xml);
			ResellerID= resellerID_value;
		}
		else
		{
			ResellerID= getwebelement(xml.getlocator("//locators/" + application + "/resellerid")).getAttribute("value");
		}
		return ResellerID;
	}

	public void cliFeatures(String application, String CLIPScreeningandCLIRperCall, String clirPermanent, String clipNoScreening, String clipMainNumber, String presentationNumbers, String presentationnumber_value) throws InterruptedException, DocumentException, IOException {

		String CLIPScreeningandCLIR= getwebelement(xml.getlocator("//locators/" + application + "/clipscreeningandclir_radio")).getAttribute("checked");
		if(CLIPScreeningandCLIRperCall.equalsIgnoreCase("yes")) {
			if(CLIPScreeningandCLIR==null)
			{
				click_commonMethod(application, "CLIP Screening and CLIR per Call (DEFAULT)", "clipscreeningandclir_radio", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "CLIP Screening and CLIR per Call (DEFAULT) radio button is selected by default");
			}
		}
		else if(clirPermanent.equalsIgnoreCase("yes")) {
			click_commonMethod(application, "CLIR Permanent", "clirPermanent_radio", xml);
		}
		else if(clipNoScreening.equalsIgnoreCase("yes")) {
			click_commonMethod(application, "CLIP NO Screening", "clipNoScreening_radio", xml);
		}
		else if(clipMainNumber.equalsIgnoreCase("yes")) {
			click_commonMethod(application, "CLIP Main Number", "clipmainnumber_radio", xml);
		}
		else if(presentationNumbers.equalsIgnoreCase("yes")) {
			click_commonMethod(application, "Presentation Numbers", "presentationnumbers_radio", xml);
			addtextFields_commonMethod(application, "Presentation Number", "presentationnumber_textfield", presentationnumber_value, xml);
			click_commonMethod(application, "Presentation Number Add arrow", "presentationnumber_addarrow", xml);
		}
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


	public String CDRdelivery_code(String application, String cdrDElivery) {

		String code_cdr=null;

		if(cdrDElivery.equalsIgnoreCase("Delivery to COCOM")) {
			code_cdr="C";
		}

		else if(cdrDElivery.equalsIgnoreCase("No delivery to COCOM")) {
			code_cdr="0";
		}

		return code_cdr;
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

		System.out.println("Value displaying under "+labelname+" is: "+ ls);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Value displaying under "+labelname+" is: "+ ls);

	}	

	public void methodToFindMessagesUnderTextField(String application ,String xpath, String labelname, String expectedmsg) {

		boolean defaultPortValueUnderSIPSignalling=false;
		try {	
			defaultPortValueUnderSIPSignalling=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			if(defaultPortValueUnderSIPSignalling) {

				WebElement defaultValue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				String defaultValueText= defaultValue.getText();
				if(defaultValueText.contains(expectedmsg)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Under '"+ labelname +"' text field', text message displays as "+ defaultValueText);
					System.out.println("Under '"+ labelname +"' text field', text message displays as "+ defaultValueText);
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Under '"+ labelname +"' text field', text message displays as "+ defaultValueText);
					System.out.println("Under '"+ labelname +"' text field', text message displays as "+ defaultValueText);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
				System.out.println("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			System.out.println("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
		}
	}

	public void viewTrunk_Primary(String application, String newCustomerCreation, String newCustomerName, String existingCustomerName, String serviceid, String country, String CDRdelivery, String gateway, String quality,
			String SIPURI, String resellerID_value, String ipAddresstype, String SIPsignallingPort, String thirdpartyinternet, String vlanTag, String subInterfaceSlot, String signallngZone, 
			String callAdmissionControl, String callrateLimitselection,String PSXmanualConfigvalue, String GSXmanualConfigvalue, String callLimit, String limitNumber, 
			String callrateLimiteValue, String SBCmanualconfigValue, String cosprofile_value, String lanrange_value, String numberporting_checkbox, String CLIPScreeningandCLIRperCall, 
			String clirPermanent, String clipNoScreening, String clipMainNumber, String presentationNumbers, String presentationnumber_value, String customerdefaultnumber_value, 
			String egressnumberformat_dropdownvalue, String incomingroutingonDDIlevel_checkbox, String PSXmanualConfigTG_value, String PSXmanualConfigDDI_value, String DRusingTDM_checkbox, 
			String codec_value, String faxdiversionnumber_value, String partialnumberreplacement_checkbox, String cpemanualconfig_checkbox) throws IOException, InterruptedException, DocumentException {   

		scrollToTop();
		Thread.sleep(2000);

		String AddressContext="EXTERNAL_AC_";
		String IPINTERFACEGROUP ="EXTERNAL_IPIG_";
		String IPINTERFACE=	"EXTERNAL_IPIF_";

		String subInterfacename_starting="SIF-1-";
		String subInterfacename_middle="-2-";
		String NIFgroupDEfaultValue_starting="SIF-1-";
		String NIFgroupDEfaultValue_middle="-2-";
		String NIGgroupdefaultValue_last="-OUTSIDE";

		String customerName=null;

		if(newCustomerCreation.equalsIgnoreCase("yes")){
			customerName= newCustomerName;
		}
		else
		{
			customerName= existingCustomerName;
		}

		//Trunk Group Description
		String expectedValue1=customerName+"_"+serviceid+"_"+"IPVL";
		Thread.sleep(1000);
		compareText_InViewPage(application, "Trunk Group Description", expectedValue1, xml);

		//VOIP Protocol
		compareText_InViewPage(application, "VOIP Protocol", "SIP", xml);

		//SIP Signaling Port
		compareText_InViewPage(application, "SIP Signalling Port", SIPsignallingPort, xml);

		//SIP URI
		compareText_InViewPage(application, "SIP URI", SIPURI, xml);

		//Billing Country
		compareText_InViewPage(application, "Billing Country", country, xml);

		//CDR Delivery
		compareText_InViewPage(application, "CDR Delivery", CDRdelivery, xml);

		//Reseller ID
		GetText(application, "Reseller ID", "viewtrunk_resellerid");

		ScrolltoElement(application, "viewtrunk_resellerid", xml);
		//Customer ID
		GetText(application, "Customer ID", "viewtrunk_customerid");

		//Gateway
		compareText_InViewPage(application, "Gateway", gateway, xml);

		//Quality
		compareText_InViewPage(application, "Quality", quality, xml);

		//Trunk Group Name
		GetText(application, "Trunk Group Name", "viewtrunk_trunkgroupname");

		//COS Profile
		GetText(application, "COS Profile", "viewpage_cosprofile");
		
		//LAN Range
		compareText_InViewPage(application, "LAN Range", lanrange_value, xml);

		//IP Address Type
		compareText_InViewPage(application, "IP Address Type", ipAddresstype, xml);

		//3rd Party Internet
		GetText(application, "3rd Party Internet", "viewpage_thirdpartyinternet");
		
		//Sub Interface Slot
		String subinterfaceSlot_viewPage= GetText(application, "Sub Interface Slot", "viewpage_subinterfaceslot");

		//VLAN Tag 
		String vlan_actualValue=GetText(application, "VLAN Tag", "viewpage_vlantag");
		if(!gateway.contains("SBC")) {

			if(thirdpartyinternet.equalsIgnoreCase("no")) {

				//Sub Interface Name
				String SubInterfaceName=subInterfacename_starting+subinterfaceSlot_viewPage+subInterfacename_middle+vlan_actualValue;
				compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);

				//NIF Group
				String NIFgroup=NIFgroupDEfaultValue_starting+subinterfaceSlot_viewPage+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
				System.out.println("NIF Group value is: "+NIFgroup);
				compareText_InViewPage(application, "NIF Group", NIFgroup, xml);

				//Signalling Zone
				String SignallingZone=getwebelement(xml.getlocator("//locators/" + application + "/viewpage_signallingzone")).getText();
				compareText_InViewPage(application, "Signalling Zone", SignallingZone, xml);

			}
			else if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

				//Sub Interface Name
				String SubInterfaceName=subInterfacename_starting+subinterfaceSlot_viewPage+subInterfacename_middle+vlan_actualValue;
				compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);

				//NIF Group
				String NIFgroup=NIFgroupDEfaultValue_starting+subinterfaceSlot_viewPage+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
				System.out.println("NIF Group value is: "+NIFgroup);
				compareText_InViewPage(application, "NIF Group", NIFgroup, xml);

				//Signalling Zone
				String SignallingZone=getwebelement(xml.getlocator("//locators/" + application + "/viewpage_signallingzone")).getText();
				compareText_InViewPage(application, "Signalling Zone", SignallingZone, xml);

			}
		}
		else if(gateway.contains("SBC")) {
			if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

				//Address Content
				compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);

				//IP Interface Group
				compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);

				//IP Interface
				compareText_InViewPage(application, "IP Interface", IPINTERFACE+vlan_actualValue, xml);

				//Signalling Zone
				GetText(application, "Signalling Zone", "viewpage_signallingzone");

			}
			else if(thirdpartyinternet.equalsIgnoreCase("No")) {

				//VLAN Tag
				compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);

				//Address Content
				compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);

				//IP Interface Group
				compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);

				//IP Interface
				compareText_InViewPage(application, "IP Interface", IPINTERFACE+vlan_actualValue, xml);
			}
		}

		//Signalling Port
		GetText(application, "Signalling Port", "viewpage_signallingport");
		
		//Number Porting
		compareText_InViewPage(application, "Number Porting", numberporting_checkbox, xml);

		//CLI Features
		GetText(application, "CLI Features", "viewpage_CLIfeatures");

		if(!presentationnumber_value.equalsIgnoreCase("null"))
		{
			compareText_InViewPage(application, "Presentation Numbers", presentationnumber_value, xml);
		}
		else
		{
			compareText_InViewPage(application, "Presentation Numbers", "null", xml);
		}
		//Customer Default Number
		if(!customerdefaultnumber_value.equalsIgnoreCase("null"))
		{
			compareText_InViewPage(application, "Customer Default Number", customerdefaultnumber_value, xml);
		}
		else
		{
			compareText_InViewPage(application, "Customer Default Number", "null", xml);
		}

		//Invalid CLI Treatment
		String InvalidCLITreatment_viewpage= GetText(application, "Invalid CLI Treatment", "viewpage_CLItreatment");
		//MultiSite Default Number
		if(InvalidCLITreatment_viewpage.equalsIgnoreCase("Allow"))
		{
			compareText_InViewPage(application, "MultiSite Default Number", customerdefaultnumber_value, xml);
		}

		//Egress Number Format
		GetText(application, "Egress Number Format", "viewpage_egressnumberformat");
		//Call Admission Control
		compareText_InViewPage(application, "Call Admission Control", callAdmissionControl, xml);
		ScrolltoElement(application, "viewpage_signallingzone", xml);
		Thread.sleep(2000);
		if(callAdmissionControl.equalsIgnoreCase("yes")) {
			//call limit
			compareText_InViewPage(application, "Call Limit", callLimit , xml);

			if(callLimit.equalsIgnoreCase("Defined")) {
				//Limit Number
				compareText_InViewPage(application, "Limit Number", limitNumber , xml);
			}
		}

		//Call Rate Limit
		if(callrateLimitselection.equalsIgnoreCase("Yes")) {

			//call rate limit value
			compareText_InViewPage(application,"Call Rate Limit", callrateLimiteValue, xml);
		}

		//Usage of Incoming routing on DDI Level
		GetText(application, "Usage of Incoming routing on DDI Level", "viewpage_usageofincomingrouting");
		//PSX Manual Configuration - Trunk Group
		compareText_InViewPage(application, "PSX Manual Configuration - Trunk Group", PSXmanualConfigTG_value, xml);

		//PSX Manual Configuration - DDI Range
		compareText_InViewPage(application, "PSX Manual Configuration - DDI Range", PSXmanualConfigDDI_value, xml);


		//PSX Manual Configuration
		compareText_InViewPage(application, "PSX Manual Configuration", PSXmanualConfigvalue, xml);

		if(gateway.contains("SBC")) {
			//Manual Configuration on SBC
			compareText_InViewPage(application, "Manual Configuration On SBC", SBCmanualconfigValue, xml);
		}else {
			//Manual Configuration On GSX
			compareText_InViewPage(application, "Manual Configuration on GSX", GSXmanualConfigvalue, xml);
		}

		//CPE Manual Configuration
		compareText_InViewPage(application, "CPE Manual Configuration", cpemanualconfig_checkbox, xml);

		//Codec
		GetText(application, "Codec", "viewpage_codec");
		//Fax Diversion Numbers
		if(!faxdiversionnumber_value.equalsIgnoreCase("null"))
		{
			compareText_InViewPage(application, "Fax Diversion Numbers", faxdiversionnumber_value, xml);
		}
		else
		{
			compareText_InViewPage(application, "Fax Diversion Numbers", "null", xml);
		}

		//DR Using TDM
		compareText_InViewPage(application, "DR Using TDM", DRusingTDM_checkbox, xml);

	}

	public void signallingPort_viewPage(String application, String gateway) throws InterruptedException, DocumentException {


		String signallingPort_expectedVaue=signalingport(application, gateway);
		compareText_InViewPage(application, "Signalling Port", signallingPort_expectedVaue, xml);
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


	public void editTrunk(String application, String newCustomerCreation, String newCustomerName, String existingCustomerName, String sid, String edit_SIPURI, String edit_SIPsignallingPort, String edit_ipAddresstype, 
			String edit_BillingCountry, String edit_CDRdelivery, String edit_resellerID_value, String gateway, String edit_gateway, String edit_quality, String edit_cosprofile_value, String edit_lanrange_value, 
			String edit_CLIPScreeningandCLIRperCall, String edit_clirPermanent, String edit_clipNoScreening, String edit_clipMainNumber, String edit_presentationNumbers, String edit_presentationnumber_value, 
			String edit_customerdefaultnumber_value, String edit_egressnumberformat_dropdownvalue, String edit_callAdmissionControl, String edit_callLimit, String edit_limitNumber, String edit_callrateLimit, 
			String edit_callrateLimiteValue, String edit_incomingroutingonDDIlevel_checkbox, String edit_PSXmanualConfigTG_value, String edit_PSXmanualConfigDDI_value, String edit_SBCmanualconfigValue, 
			String edit_GSXmanualConfigvalue, String edit_cpemanualconfig_checkbox, String edit_DRusingTDM_checkbox, String edit_codec_value, String edit_faxdiversionnumber_value, String edit_partialnumberreplacement_checkbox,
			String edit_thirdpartyinternet, String edit_subInterfaceSlot, String edit_vlanTag, String edit_vlanTag_FRASBC_value, String edit_signallingZone, String edit_numberporting_checkbox) throws IOException, InterruptedException, DocumentException {   

		String subInterfacename_starting="SIF-1-";
		String subInterfacename_middle="-2-";
		String NIFgroupDEfaultValue_starting="SIF-1-";
		String NIFgroupDEfaultValue_middle="-2-";
		String NIGgroupdefaultValue_last="-OUTSIDE";
		String ipInterfaceDEfaultValue="EXTERNAL_IPIF_";
		String addressContextDefaultValue="EXTERNAL_AC_";
		String ipInterfaceGroupDefaultvalue="EXTERNAL_IPIG_";
		String signallingZoneDefaultValue="OUT-UNTRUSTED";

		String gatewayCode=null;
		String primarytrunk="0";
		String CDR_code=null;
		String SubInterfaceName=null;
		String NIFgroup=null;
		String ipInterface=null;
		String addressContext=null;
		String ipInterfaceGroup=null;
		String vlanUpdatedvalue3=null;
		String vlanUpdatedvalue5=null;

		String customerName=null;

		Thread.sleep(1000);
		scrollToTop();
		//Action button	
		click_commonMethod(application, "Action", "viewPage_ActionButton", xml);

		//click on Edit link
		click_commonMethod(application, "Edit", "edit", xml);
		Thread.sleep(3000);

		if(newCustomerCreation.equalsIgnoreCase("yes")){
			customerName= newCustomerName;
		}
		else
		{
			customerName= existingCustomerName;
		}

		//Trunk Group Description
		String expectedValue1=customerName+"_"+sid+"_"+"IPVL";
		Thread.sleep(1000);
		compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

		//VOIP Protocol
		GetText(application, "VOIP Protocol", "voipProtocol_Dropdown");
		String voip_code="S";

		//SIP URI
		edittextFields_commonMethod(application, "SIP URI", "SIPURI_textField", edit_SIPURI, xml);
		String SIPURIvalue=getwebelement(xml.getlocator("//locators/" + application + "/SIPURI_textField")).getAttribute("value");
		System.out.println("SIP URI value is: "+SIPURIvalue);
		ExtentTestManager.getTest().log(LogStatus.PASS, "SIP URI value is: "+SIPURIvalue);

		//SIP Signalling Port
		edittextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", edit_SIPsignallingPort, xml);
		String SIPsignallingport_value=getwebelement(xml.getlocator("//locators/" + application + "/SIPsignallingport_textField")).getAttribute("value");
		System.out.println("SIP Signalling Port value is: "+SIPsignallingport_value);
		ExtentTestManager.getTest().log(LogStatus.PASS, "SIP Signalling Port value is: "+SIPsignallingport_value);

		//Billing COuntry
		selectValueInsideDropdown(application, "billingCoutry_Dropdown", "Billing Country", edit_BillingCountry, xml);

		String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
		System.out.println("country dropdown value is: "+country_code);
		ExtentTestManager.getTest().log(LogStatus.PASS, "country dropdown value is: "+country_code);

		//CDR Delivery
		if(!edit_CDRdelivery.equalsIgnoreCase("null"))
		{
			selectValueInsideDropdown(application, "CDRdelivery_Dropdown", "CDR Delivery", edit_CDRdelivery, xml);
			CDR_code=CDRdelivery_code(application, edit_CDRdelivery);
		}
		else
		{
			CDR_code=getwebelement(xml.getlocator("//locators/" + application + "/CDRdelivery_Dropdown")).getAttribute("value");
			System.out.println("CDR Delivery dropdown value is: "+CDR_code);
			ExtentTestManager.getTest().log(LogStatus.PASS, "CDR Delivery dropdown value is: "+CDR_code);
		}

		//ResellerID
		String ResellerIDValue= resellerID(application, edit_resellerID_value);

		//Customer ID
		String CustomerID= getwebelement(xml.getlocator("//locators/" + application + "/customerid")).getAttribute("value");

		ScrolltoElement(application, "billingCoutry_Dropdown", xml);
		//Gateway
		if(!edit_gateway.equalsIgnoreCase("null"))
		{
			selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", edit_gateway, xml);
			gatewayCode=gateway_code(application, edit_gateway);
			Thread.sleep(2000);
		}
		else
		{
			//			gatewayCode=getwebelement(xml.getlocator("//locators/" + application + "/gateway_Dropdown")).getAttribute("value");
			//			System.out.println("Gateway dropdown value is: "+gatewayCode);
			gatewayCode=gateway_code(application, gateway);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Gateway dropdown value is: "+gatewayCode);
		}

		//Quality
		selectValueInsideDropdown(application, "quality_Dropdown", "Quality", edit_quality, xml);

		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");
		System.out.println("Quality dropdown value is: "+quality_code);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Quality dropdown value is: "+quality_code);

		//Trunk Name
		String Edit_TrunkName=country_code+gatewayCode+voip_code+"L"+CustomerID+"00"+CDR_code+ResellerIDValue+quality_code+"00";
		int totalLen=Edit_TrunkName.length();
		System.out.println("Total lenth of 'Trunk Group' field is "+ totalLen);
		System.out.println("Trunk name is "+ Edit_TrunkName);
		if(totalLen==20) {
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", Edit_TrunkName, xml);
			Edit_primarytrunkGroupname=Edit_TrunkName;
			System.out.println("Trunk group name is: "+Edit_primarytrunkGroupname);
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Trunk Group Name' length is: "+totalLen);
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", Edit_TrunkName, xml);
		}

		//COS Profile
		selectValueInsideDropdown(application, "cosprofile", "COS Profile", edit_cosprofile_value, xml);
		String COSProfileValue=getwebelement(xml.getlocator("//locators/" + application + "/cosprofile")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "COS Profile dropdown value is: "+COSProfileValue);


		//LAN Range
		edittextFields_commonMethod(application, "LAN Range", "lanrange", edit_lanrange_value, xml);
		String LANRangevalue=getwebelement(xml.getlocator("//locators/" + application + "/lanrange")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "LAN Range value is: "+LANRangevalue);


		//IP Address Type
		selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", edit_ipAddresstype, xml);
		Thread.sleep(2000);
		String IPAddressTypevalue=getwebelement(xml.getlocator("//locators/" + application + "/IPaddresstype_Dropdown")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "IP Address Type dropdown value is: "+IPAddressTypevalue);

		//Internet Based Customer
		String InternetBasedCustomer_Checked= getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox")).getAttribute("checked");
		String InternetBasedCustomer_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox")).getAttribute("disabled");
		if(InternetBasedCustomer_Checked==null && InternetBasedCustomer_Disabled==null)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Internet Based Customer' is not checked and disabled by default");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Internet Based Customer' is checked and disabled by default");
		}

		//Splitting the Gateway functionality into 2
		if(!edit_gateway.contains("SBC")) {

			if(edit_thirdpartyinternet.equalsIgnoreCase("Yes")) {

				editcheckbox_commonMethod(application, edit_thirdpartyinternet, "thirdpartyinternet_checkbox", "3rd Party Internet", xml);
				String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				if(vlanDefaultvalue.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when '3rd Party Internet' is selected");
				}else {

					ExtentTestManager.getTest().log(LogStatus.PASS, "When '3rd Party Internet' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
					if(edit_vlanTag.equalsIgnoreCase("null")) {

						//Sub Interface slot
						selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", edit_subInterfaceSlot, xml);
						String SubInterfaceSlot_value=getwebelement(xml.getlocator("//locators/" + application + "/subInterfaceSlot_Dropdown")).getAttribute("value");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Sub Interface Slot dropdown value is displaying as "+SubInterfaceSlot_value);

						if(edit_subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanDefaultvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

							//NIF Group  
							NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanDefaultvalue+NIGgroupdefaultValue_last;
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						else if(!edit_subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+edit_subInterfaceSlot+subInterfacename_middle+vlanDefaultvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+edit_subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanDefaultvalue+NIGgroupdefaultValue_last;
							System.out.println("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}

					}else {

						//VLAN Tag
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", edit_vlanTag, xml);
						String vlanTagUpdatedvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

						//Sub Interface slot
						selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", edit_subInterfaceSlot, xml);
						String SubInterfaceSlot_value=getwebelement(xml.getlocator("//locators/" + application + "/subInterfaceSlot_Dropdown")).getAttribute("value");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Sub Interface Slot dropdown value is displaying as "+SubInterfaceSlot_value);

						if(edit_subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanTagUpdatedvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanTagUpdatedvalue+NIGgroupdefaultValue_last;
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						else if(!edit_subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+edit_subInterfaceSlot+subInterfacename_middle+vlanTagUpdatedvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+edit_subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanTagUpdatedvalue+NIGgroupdefaultValue_last;
							System.out.println("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
					}
				}
			}
			//3rd party internet checkbox not selected 
			else {
				if(edit_vlanTag.equalsIgnoreCase("null")) {
					String vlanDefaultvalue1=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					//Sub Interface slot
					selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", edit_subInterfaceSlot, xml);
					String SubInterfaceSlot_value=getwebelement(xml.getlocator("//locators/" + application + "/subInterfaceSlot_Dropdown")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Sub Interface Slot dropdown value is displaying as "+SubInterfaceSlot_value);

					if(edit_subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanDefaultvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanDefaultvalue1+NIGgroupdefaultValue_last;
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
					else if(!edit_subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+edit_subInterfaceSlot+subInterfacename_middle+vlanDefaultvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+edit_subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanDefaultvalue1+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}

				}else {

					//VLAN Tag
					cleartext(application, "VLAN Tag", "vlanTag_textField");
					edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", edit_vlanTag, xml);
					String vlanUpdatedvalue1=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					//Sub Interface slot
					selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", edit_subInterfaceSlot, xml);
					String SubInterfaceSlot_value=getwebelement(xml.getlocator("//locators/" + application + "/subInterfaceSlot_Dropdown")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Sub Interface Slot dropdown value is displaying as "+SubInterfaceSlot_value);

					if(edit_subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanUpdatedvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanUpdatedvalue1+NIGgroupdefaultValue_last;
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
					else if(!edit_subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+edit_subInterfaceSlot+subInterfacename_middle+vlanUpdatedvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+edit_subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanUpdatedvalue1+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
				}

			}
		}

		else if(edit_gateway.contains("SBC")) {
			if(edit_gateway.startsWith("FRA")) {
				if(!edit_thirdpartyinternet.equalsIgnoreCase("Yes")) {
					if(edit_vlanTag.equalsIgnoreCase("null")) {
						String vlanDefaultvalue2=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

						//IP Interface
						boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
						if(ipInterface_Enabled) {
							System.out.println("'Ip Interface' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text field is disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						}

						compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values

					}
					else if(!edit_vlanTag.equalsIgnoreCase("null")) {

						//VLAN Tag
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", edit_vlanTag_FRASBC_value, xml);
						String vlanUpdatedvalue2=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

						//IP Interface
						boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
						if(ipInterface_Enabled) {
							System.out.println("'Ip Interface' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text fieldis disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}

						ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue2;
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						addressContext=addressContextDefaultValue+vlanUpdatedvalue2;
						compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						}

						ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue2;
						compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values

					}

				}
				else if(edit_thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//3rd party internet
					addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", edit_thirdpartyinternet, "No", xml);

					String vlanDefaultvalue3=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue3.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
					}else {
						//VLAN tag  
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", edit_vlanTag_FRASBC_value, xml);
						vlanUpdatedvalue3=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					}

					//IP Interface
					boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					if(ipInterface_Enabled) {
						System.out.println("'Ip Interface' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue3;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue3;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue3;
					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				}
			}
			else {
				if(!edit_thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//VLAN tag  
					String vlanDefaultvalue4=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue4.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
					}else {
						//VLAN tag  
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", edit_vlanTag_FRASBC_value, xml);
						String vlanUpdatedvalue4=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					}

					//IP Interface
					boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					if(ipInterface_Enabled) {
						System.out.println("'Ip Interface' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values

				}

				if(edit_thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//3rd party internet
					addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", edit_thirdpartyinternet, "No", xml);

					String vlanDefaultvalue5=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue5.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
					}else {
						//VLAN tag  
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", edit_vlanTag_FRASBC_value, xml);
						vlanUpdatedvalue5=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					}

					//IP Interface
					boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					if(ipInterface_Enabled) {
						System.out.println("'Ip Interface' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				}
			}
		}

		//Signalling Zone
		edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", edit_signallingZone, xml);
		String SignallingZone_Value=getwebelement(xml.getlocator("//locators/" + application + "/signallingZone_textField")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Signalling Zone value is: "+SignallingZone_Value);

		//Number Porting checkbox
		editcheckbox_commonMethod(application, edit_numberporting_checkbox, "numberporting_checkbox", "Number Porting", xml);
		ScrolltoElement(application, "signallingZone_textField", xml);
		//CLI Features
		cliFeatures(application, edit_CLIPScreeningandCLIRperCall, edit_clirPermanent, edit_clipNoScreening, edit_clipMainNumber, edit_presentationNumbers, edit_presentationnumber_value);

		//Customer Default Number
		edittextFields_commonMethod(application, "Customer Default Number", "customerdefaultnumber_textfield", edit_customerdefaultnumber_value, xml);
		String CustomerDefaultNumber_Value=getwebelement(xml.getlocator("//locators/" + application + "/customerdefaultnumber_textfield")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Customer Default Number value is: "+CustomerDefaultNumber_Value);

		//Invalid CLI Treatment
		boolean invalidCLITreatment_Allow= getwebelement(xml.getlocator("//locators/" + application + "/invalidclitreatment_allow")).isSelected();

		if(invalidCLITreatment_Allow)
		{
			//Multisite Default Number
			String MultisiteDefaultNumber= getwebelement(xml.getlocator("//locators/" + application + "/multisitedefaultnumber")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'MultiSite Default Number' value is displaying as "+MultisiteDefaultNumber);
		}
		else
		{
			click_commonMethod(application, "Block", "invalidclitreatment_block", xml);
		}

		//Egress Number Format
		selectValueInsideDropdown(application, "egressnumberformat_dropdown", "Egress Number Format", edit_egressnumberformat_dropdownvalue, xml);
		String EgressNumberFormat_Value=getwebelement(xml.getlocator("//locators/" + application + "/egressnumberformat_dropdown")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Egress Number Format value is: "+EgressNumberFormat_Value);

		//Call Admission Control
		editcheckbox_commonMethod(application, edit_callAdmissionControl, "callAdmissionControl_checkbox", "Call Admission Control", xml);

		if(edit_callAdmissionControl.equalsIgnoreCase("yes")) {

			//Call Limit
			selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", edit_callLimit, xml);
			String CallLimit_Value=getwebelement(xml.getlocator("//locators/" + application + "/callLimit_Dropdown")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Call Limit value is: "+CallLimit_Value);

			if(edit_callLimit.equalsIgnoreCase("Defined")) {
				edittextFields_commonMethod(application, "Limit Number", "limitNumber_textField", edit_limitNumber, xml);
				String LimitNumber_Value=getwebelement(xml.getlocator("//locators/" + application + "/limitNumber_textField")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Limit Number value is: "+LimitNumber_Value);
			}
		}

		//Call Rate Limit
		editcheckbox_commonMethod(application, edit_callrateLimit, "callrateLimit_checkbox", "Call Rate Limit", xml);
		if(edit_callrateLimit.equalsIgnoreCase("yes")) {

			String callratelimitactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callRateLimitt_textField")).getAttribute("value");
			System.out.println(" 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);

			if(!edit_callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(edit_callrateLimiteValue);

				if(i>100) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less than 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", edit_callrateLimiteValue, xml);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
				System.out.println("'Call rate Limit' value is not edited");
			}
		}

		ScrolltoElement(application, "egressnumberformat_dropdown", xml);
		Thread.sleep(1000);
		//Usage of Incoming routing on DDI Level
		editcheckbox_commonMethod(application, edit_incomingroutingonDDIlevel_checkbox, "incomingroutingonDDIlevel_checkbox", "Usage of Incoming routing on DDI Level", xml);

		//PSX Manual Configuration-Trunk Group 
		editcheckbox_commonMethod(application, edit_PSXmanualConfigTG_value, "PSXmanualConfigTG_checkbox", "PSX Manual Configuration-Trunk Group", xml);

		//PSX Manual Configuration-DDI-Range
		editcheckbox_commonMethod(application, edit_PSXmanualConfigDDI_value, "PSXmanualConfigDDIRange_checkbox", "PSX Manual Configuration-DDI-Range", xml);

		if(edit_gateway.contains("SBC")) {
			//Manual Configuration on SBC
			editcheckbox_commonMethod(application, edit_SBCmanualconfigValue, "SBCmanualconfig_checkbox", "Manual Configuration On SBC", xml);

		}else {
			//Manual Configuration On GSX
			editcheckbox_commonMethod(application, edit_GSXmanualConfigvalue, "GSXmanualConfig_checkbox", "Manual Configuration On GSX", xml);
		}

		//CPE Manual Configuration
		editcheckbox_commonMethod(application, edit_cpemanualconfig_checkbox, "cpemanualconfig_checkbox", "CPE Manual Configuration", xml);

		//DR using TDM
		editcheckbox_commonMethod(application, edit_DRusingTDM_checkbox, "DRusingTDM_checkbox", "DR Using TDM", xml);

		//Codec
		selectValueInsideDropdown(application, "codec_dropdown", "Codec", edit_codec_value, xml);
		String Codec_Value=getwebelement(xml.getlocator("//locators/" + application + "/codec_dropdown")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Codec dropdown value is: "+Codec_Value);

		scrolltoend();
		//Fax Diversion Numbers
		if(!edit_faxdiversionnumber_value.equalsIgnoreCase("null"))
		{
			cleartext(application, "Fax Diversion Numbers", "faxdiversionnumbers_textfield");
			edittextFields_commonMethod(application, "Fax Diversion Numbers", "faxdiversionnumbers_textfield", edit_faxdiversionnumber_value, xml);
			click_commonMethod(application, "Fax Diversion Numbers Add arrow", "faxdiversion_addarrow", xml);
		}
		else
		{
			String FaxDiversionNumber_Value=getwebelement(xml.getlocator("//locators/" + application + "/faxdiversionnumbers_textfield")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Fax Diversion Numbers value is: "+FaxDiversionNumber_Value);
		}

		//Partial Number Replacement
		editcheckbox_commonMethod(application, edit_partialnumberreplacement_checkbox, "partialnumberreplacement_checkbox", "Partial Number Replacement", xml);
		Thread.sleep(1000);
		scrolltoend();
		click_commonMethod(application, "OK", "trunk_okButton", xml);
		Thread.sleep(6000);
		verifysuccessmessage(application, "Trunk updated successfully");

	}

	public void addResilienttrunk(String application, String newCustomerCreation, String newCustomerName, String existingCustomerName, String sid, String resilient_country, String Resilient_CDRdelivery, String resilient_gateway, String resilient_quality,
			String SIPURI, String resilient_resellerID_value, String ipAddresstype, String SIPsignallingPort, String thirdpartyinternet, String vlanTag, String subInterfaceSlot, String signallngZone, 
			String callAdmissionControl, String callrateLimit,String PSXmanualConfigvalue, String GSXmanualConfigvalue, String callLimit, String limitNumber, 
			String callrateLimiteValue, String SBCmanualconfigValue, String cosprofile_value, String lanrange_value, String numberporting_checkbox, String CLIPScreeningandCLIRperCall, 
			String clirPermanent, String clipNoScreening, String clipMainNumber, String presentationNumbers, String presentationnumber_value, String customerdefaultnumber_value, 
			String egressnumberformat_dropdownvalue, String incomingroutingonDDIlevel_checkbox, String PSXmanualConfigTG_value, String PSXmanualConfigDDI_value, String DRusingTDM_checkbox, 
			String codec_value, String faxdiversionnumber_value, String partialnumberreplacement_checkbox, String cpemanualconfig_checkbox, String vlanTag_FRASBC_value) throws IOException, InterruptedException, DocumentException {   

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
		String vlanUpdatedvalue3=null;
		String vlanUpdatedvalue5=null;
		String CDR_code=null;
		String customerName=null;

		Thread.sleep(2000);

		if(newCustomerCreation.equalsIgnoreCase("yes")){
			customerName= newCustomerName;
		}
		else
		{
			customerName= existingCustomerName;
		}

		//Trunk Group Description
		String expectedValue1=customerName+"_"+sid+"_"+"IPVL";
		Thread.sleep(1000);
		compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

		//Primary trunk group
		//WebElement PrimaryTrunkGroup= getwebelement(xml.getlocator("//locators/" + application + "/primaryTrunkGroup_Dropdown"));
		selectValueInsideDropdown(application, "primaryTrunkGroup_Dropdown", "Primary Trunk Group", Edit_primarytrunkGroupname, xml);
		Thread.sleep(2000);

		//VOIP Protocol
		GetText(application, "VOIP Protocol", "voipProtocol_Dropdown");
		String voip_code="S";

		//SIP URI
		addtextFields_commonMethod(application, "SIP URI", "SIPURI_textField", SIPURI, xml);
		//message displaying under "SIP URI" text field	
		methodToFindMessagesUnderTextField(application, "SIPURI_defaultValue_textvalue", "SIP URI", "Default URI: va.sip.colt.net");

		//SIP Signalling Port
		addtextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", SIPsignallingPort, xml);
		//message displaying under "SIP Signalling Port" text field	
		methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port: 5060");

		//Billing Country
		//warningMessage_commonMethod(application, "country_warningMessage", "Billing Country", xml);   //validate warning message
		selectValueInsideDropdown(application, "billingCoutry_Dropdown", "Billing Country", resilient_country, xml);

		String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
		System.out.println("country dropdown value is: "+country_code);

		//CDR Delivery

		if(!Resilient_CDRdelivery.equalsIgnoreCase("null"))
		{
			selectValueInsideDropdown(application, "CDRdelivery_Dropdown", "CDR Delivery", Resilient_CDRdelivery, xml);
			CDR_code=CDRdelivery_code(application, Resilient_CDRdelivery);
		}
		else
		{
			CDR_code=getwebelement(xml.getlocator("//locators/" + application + "/CDRdelivery_Dropdown")).getAttribute("value");
			System.out.println("CDR Delivery dropdown value is: "+CDR_code);
			ExtentTestManager.getTest().log(LogStatus.PASS, "CDR Delivery dropdown value is: "+CDR_code);
		}

		//ResellerID
		String ResellerIDValue= resellerID(application, resilient_resellerID_value);

		//Customer ID
		String CustomerID= getwebelement(xml.getlocator("//locators/" + application + "/customerid")).getAttribute("value");

		//Gateway
		selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", resilient_gateway, xml);
		Thread.sleep(1000);
		gatewayCode=gateway_code(application, resilient_gateway);
		Thread.sleep(2000);

		//Quality
		selectValueInsideDropdown(application, "quality_Dropdown", "Quality", resilient_quality, xml);
		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");

		//Trunk Name
		String trunkName=country_code+gatewayCode+voip_code+"L"+CustomerID+"00"+CDR_code+ResellerIDValue+quality_code+"0A";

		int totalLen=trunkName.length();
		System.out.println("Total lenth of 'Trunk Group' field is "+ totalLen);
		System.out.println("Trunk name is "+ trunkName);
		if(totalLen==20) {
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunkName, xml);
			primarytrunkGroupname_Resilient=trunkName;
			System.out.println("Trunk group name is: "+primarytrunkGroupname_Resilient);
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Trunk Group Name' length is: "+totalLen);
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunkName, xml);
		}

		//COS Profile
		selectValueInsideDropdown(application, "cosprofile", "COS Profile", cosprofile_value, xml);

		//LAN Range
		addtextFields_commonMethod(application, "LAN Range", "lanrange", lanrange_value, xml);

		//IP Address Type
		selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", ipAddresstype, xml);
		Thread.sleep(2000);

		//Internet Based Customer
		String InternetBasedCustomer_Checked= getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox")).getAttribute("checked");
		String InternetBasedCustomer_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox")).getAttribute("disabled");
		if(InternetBasedCustomer_Checked==null && InternetBasedCustomer_Disabled==null)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Internet Based Customer' is not checked and disabled by default");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Internet Based Customer' is checked and disabled by default");
		}

		//Splitting the Gateway functionality into 2
		if(!resilient_gateway.contains("SBC")) {

			if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

				addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet, "Yes", xml);

				String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				if(vlanDefaultvalue.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when '3rd Party Internet' is selected");
				}else {

					ExtentTestManager.getTest().log(LogStatus.PASS, "When '3rd Party Internet' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
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
							System.out.println("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}

					}else {

						//VLAN Tag
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);
						String vlanTagUpdatedvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

						//Sub Interface slot
						selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);

						if(subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanTagUpdatedvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanTagUpdatedvalue+NIGgroupdefaultValue_last;
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
						else if(!subInterfaceSlot.equalsIgnoreCase("null")) {

							//Sub Interface Name
							SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanTagUpdatedvalue;
							compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

							//NIF Group
							NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanTagUpdatedvalue+NIGgroupdefaultValue_last;
							System.out.println("NIF Group value is: "+NIFgroup);
							compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
						}
					}
				}
			}
			//3rd party internet checkbox not selected 
			else {
				if(vlanTag.equalsIgnoreCase("null")) {
					String vlanDefaultvalue1=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					//Sub Interface slot
					selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);

					if(subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanDefaultvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanDefaultvalue1+NIGgroupdefaultValue_last;
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
					else if(!subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanDefaultvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanDefaultvalue1+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}

				}else {

					//VLAN Tag
					cleartext(application, "VLAN Tag", "vlanTag_textField");
					edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag, xml);
					String vlanUpdatedvalue1=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					//Sub Interface slot
					selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", subInterfaceSlot, xml);

					if(subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfacename_middle+vlanUpdatedvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+NIFgroupDEfaultValue_middle+vlanUpdatedvalue1+NIGgroupdefaultValue_last;
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
					else if(!subInterfaceSlot.equalsIgnoreCase("null")) {

						//Sub Interface Name
						SubInterfaceName=subInterfacename_starting+subInterfaceSlot+subInterfacename_middle+vlanUpdatedvalue1;
						compareText_fromtextFields(application, "Sub Interface Name", "subInterfaceName_textField", SubInterfaceName, xml);

						//NIF Group
						NIFgroup=NIFgroupDEfaultValue_starting+subInterfaceSlot+NIFgroupDEfaultValue_middle+vlanUpdatedvalue1+NIGgroupdefaultValue_last;
						System.out.println("NIF Group value is: "+NIFgroup);
						compareText_fromtextFields(application, "NIF Group", "NIFgrouopp_textField", NIFgroup, xml);
					}
				}

			}
		}

		else if(resilient_gateway.contains("SBC")) {
			if(resilient_gateway.startsWith("FRA")) {
				if(!thirdpartyinternet.equalsIgnoreCase("Yes")) {
					if(vlanTag.equalsIgnoreCase("null")) {
						String vlanDefaultvalue2=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

						//IP Interface
						boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
						if(ipInterface_Enabled) {
							System.out.println("'Ip Interface' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text field is disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						}

						compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values

					}
					else if(!vlanTag.equalsIgnoreCase("null")) {

						//VLAN Tag
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag_FRASBC_value, xml);
						String vlanUpdatedvalue2=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

						//IP Interface
						boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
						if(ipInterface_Enabled) {
							System.out.println("'Ip Interface' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text fieldis disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}

						ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue2;
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						addressContext=addressContextDefaultValue+vlanUpdatedvalue2;
						compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
						}

						ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue2;
						compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values

					}

				}
				else if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//3rd party internet
					editcheckbox_commonMethod(application, thirdpartyinternet, "thirdpartyinternet_checkbox", "3rd Party Internet", xml);
					String vlanDefaultvalue3=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue3.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
					}else {
						//VLAN tag  
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag_FRASBC_value, xml);
						vlanUpdatedvalue3=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					}

					//IP Interface
					boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					if(ipInterface_Enabled) {
						System.out.println("'Ip Interface' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue3;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue3;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue3;
					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				}
			}
			else {
				if(!thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//VLAN tag  
					String vlanDefaultvalue4=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue4.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
					}else {
						//VLAN tag  
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag_FRASBC_value, xml);
						String vlanUpdatedvalue4=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");

					}

					//IP Interface
					boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					if(ipInterface_Enabled) {
						System.out.println("'Ip Interface' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values

				}

				if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//3rd party internet
					editcheckbox_commonMethod(application, thirdpartyinternet, "thirdpartyinternet_checkbox", "3rd Party Internet", xml);
					String vlanDefaultvalue5=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue5.isEmpty()) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
					}else {
						//VLAN tag  
						cleartext(application, "VLAN Tag", "vlanTag_textField");
						edittextFields_commonMethod(application, "VLAN Tag", "vlanTag_textField", vlanTag_FRASBC_value, xml);
						vlanUpdatedvalue5=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					}

					//IP Interface
					boolean ipInterface_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterface_textField")).isEnabled();
					if(ipInterface_Enabled) {
						System.out.println("'Ip Interface' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						ExtentTestManager.getTest().log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				}
			}
		}

		//Signalling Zone
		if(thirdpartyinternet.equalsIgnoreCase("yes")) {

			compareText_fromtextFields(application, "Signaling Zone", "signallingZone_textField", signallingZoneDefaultValue, xml);

			edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);

		}else {
			edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", signallngZone, xml);
		}

		//Number Porting checkbox
		editcheckbox_commonMethod(application, numberporting_checkbox, "numberporting_checkbox", "Number Porting", xml);

		//CLI Features
		ScrolltoElement(application, "signallingZone_textField", xml);
		Thread.sleep(1000);
		cliFeatures(application, CLIPScreeningandCLIRperCall, clirPermanent, clipNoScreening, clipMainNumber, presentationNumbers, presentationnumber_value);

		//Customer Default Number
		edittextFields_commonMethod(application, "Customer Default Number", "customerdefaultnumber_textfield", customerdefaultnumber_value, xml);

		//Invalid CLI Treatment
		boolean invalidCLITreatment_Allow= getwebelement(xml.getlocator("//locators/" + application + "/invalidclitreatment_allow")).isSelected();

		if(invalidCLITreatment_Allow)
		{
			//Multisite Default Number
			String MultisiteDefaultNumber= getwebelement(xml.getlocator("//locators/" + application + "/multisitedefaultnumber")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'MultiSite Default Number' value is displaying as "+MultisiteDefaultNumber);
		}
		else
		{
			click_commonMethod(application, "Block", "invalidclitreatment_block", xml);
		}

		//Egress Number Format
		selectValueInsideDropdown(application, "egressnumberformat_dropdown", "Egress Number Format", egressnumberformat_dropdownvalue, xml);

		//Call Admission Control
		editcheckbox_commonMethod(application, callAdmissionControl, "callAdmissionControl_checkbox", "Call Admission Control", xml);
		if(callAdmissionControl.equalsIgnoreCase("yes")) {

			//Call Limit
			selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", callLimit, xml);

			if(callLimit.equalsIgnoreCase("Defined")) {
				addtextFields_commonMethod(application, "Limit Number", "limitNumber_textField", limitNumber, xml);
			}
		}

		ScrolltoElement(application, "multisitedefaultnumber", xml);
		//Call Rate Limit
		editcheckbox_commonMethod(application, callrateLimit, "callrateLimit_checkbox", "Call Rate Limit", xml);
		Thread.sleep(1000);
		if(callrateLimit.equalsIgnoreCase("yes")) {

			String callratelimitactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callRateLimitt_textField")).getAttribute("value");
			System.out.println(" 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);

			if(!callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(callrateLimiteValue);

				if(i>100) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "The CallRateLimit should be less than 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Call rate Limit' value is not edited");
				System.out.println("'Call rate Limit' value is not edited");
			}
		}
		Thread.sleep(1000);
		//Usage of Incoming routing on DDI Level
		editcheckbox_commonMethod(application, incomingroutingonDDIlevel_checkbox, "incomingroutingonDDIlevel_checkbox", "Usage of Incoming routing on DDI Level", xml);

		Thread.sleep(1000);
		//PSX Manual Configuration-Trunk Group 
		editcheckbox_commonMethod(application, PSXmanualConfigTG_value, "PSXmanualConfigTG_checkbox", "PSX Manual Configuration-Trunk Group", xml);
		//PSX Manual Configuration-DDI-Range
		editcheckbox_commonMethod(application, PSXmanualConfigDDI_value, "PSXmanualConfigDDIRange_checkbox", "PSX Manual Configuration-DDI-Range", xml);

		if(resilient_gateway.contains("SBC")) {
			//Manual Configuration on SBC
			editcheckbox_commonMethod(application, SBCmanualconfigValue, "SBCmanualconfig_checkbox", "Manual Configuration On SBC", xml);
		}else {
			//Manual Configuration On GSX
			editcheckbox_commonMethod(application, GSXmanualConfigvalue, "GSXmanualConfig_checkbox", "Manual Configuration On GSX", xml);
		}

		//CPE Manual Configuration
		editcheckbox_commonMethod(application, cpemanualconfig_checkbox, "cpemanualconfig_checkbox", "CPE Manual Configuration", xml);
		//DR using TDM
		editcheckbox_commonMethod(application, DRusingTDM_checkbox, "DRusingTDM_checkbox", "DR Using TDM", xml);
		//Codec
		selectValueInsideDropdown(application, "codec_dropdown", "Codec", codec_value, xml);

		scrolltoend();
		//Fax Diversion Numbers
		addtextFields_commonMethod(application, "Fax Diversion Numbers", "faxdiversionnumbers_textfield", faxdiversionnumber_value, xml);
		click_commonMethod(application, "Fax Diversion Numbers Add arrow", "faxdiversion_addarrow", xml);

		//Partial Number Replacement
		editcheckbox_commonMethod(application, partialnumberreplacement_checkbox, "partialnumberreplacement_checkbox", "Partial Number Replacement", xml);
		Thread.sleep(1000);
		scrolltoend();
		click_commonMethod(application, "OK", "trunk_okButton", xml);
		Thread.sleep(6000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Trunk created successfully");
		scrollToTop();
		clickOnBreadCrumb(application, sid);
		Thread.sleep(2000);
	}

	public void verifyAddVoiceCPEDevice(String application, String voiceCPE_devicename, String voiceCPE_vendormodel, 
			String voiceCPE_managementaddress, String voiceCPE_country, String voiceCPE_existingcity, String voiceCPE_existingcityvalue, 
			String voiceCPE_existingsite, String voiceCPE_existingsitevalue, String voiceCPE_existingpremise, String voiceCPE_existingpremisevalue, String voiceCPE_newcity,
			String voiceCPE_cityname,String voiceCPE_Citycode, String voiceCPE_sitename, String voiceCPE_sitecode, String voiceCPE_premisename, String voiceCPE_premisecode, 
			String voiceCPE_newsite, String voiceCPE_NewPremise, String cpetoprovidedialtone_checkbox, String cpelinepowerrequired_checkbox, String numberporting_checkbox, 
			String briportmapping_checkbox, String crcsettings_dropdownvalue, String numberofpriports_dropdownvalue, String numberofbriports_dropdownvalue, 
			String briport1_value, String briport2_value, String briport3_value, String briport4_value, String briport5_value, String briport6_value, String briport7_value, 
			String briport8_value, String numberofFXSports_dropdownvalue, String fxsnumber1_value, String fxsnumber2_value) throws InterruptedException, DocumentException, IOException {


		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "voiceCPEdevice_header", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Add Voice CPE Device", "addvoicecpe_link", xml);
		Thread.sleep(2000);
		compareText(application, "Add VOice CPE header", "addvoicecpe_header", "Add Voice CPE", xml);
		edittextFields_commonMethod(application, "Name", "voicecpe_devicename", voiceCPE_devicename, xml);
		//addDropdownValues_commonMethod(application, "Vendor/Model", "vendormodel_dropdown", voiceCPE_vendormodel, xml);

		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/vendormodel_dropdown")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Vendor/Model dropdown is displaying");
				System.out.println("Vendor/Model dropdown is displaying");

				if(voiceCPE_vendormodel.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Vendor/Model dropdown");
					System.out.println(" No values selected under Vendor/Model dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//span[contains(@class,'css-f2o6e8-ItemComponent evc32pp0')]"));

					ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside Vendor/Model dropdown is:  ");
					System.out.println( " List of values inside Vendor/Model dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), voiceCPE_vendormodel);	
					Thread.sleep(2000);

					Clickon(getwebelement("(//span[contains(text(),'"+ voiceCPE_vendormodel +"')])[1]"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//label[text()='Vendor/Model']/following-sibling::div//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Vendor/Model dropdown value selected as: "+ actualValue );
					System.out.println("Vendor/Model dropdown value selected as: "+ actualValue);

				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Vendor/Model is not displaying");
				System.out.println("Vendor/Model is not displaying");
			}
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Vendor/Model is not displaying");
			System.out.println("Vendor/Model is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under Vendor/Model dropdown");
			System.out.println(" NO value selected under Vendor/Model dropdown");
		}

		addtextFields_commonMethod(application, "Management Address/Mask [e.g. 10.0.0.0/27]", "managementaddress_voicecpe", voiceCPE_managementaddress, xml);
		compareText_fromtextFields(application, "Snmpro", "snmpro_textfield", "incc", xml);

		//select country
		ScrolltoElement(application, "voicecpe_devicename", xml);
		Thread.sleep(2000);
		selectValueInsideDropdown(application, "countryinput", "Country", voiceCPE_country, xml);

		//New City		
		if(voiceCPE_existingcity.equalsIgnoreCase("no") & voiceCPE_newcity.equalsIgnoreCase("yes")) {
			Clickon_addToggleButton(application, "addcityswitch");
			//City name
			addtextFields_commonMethod(application, "City Name", "citynameinputfield", voiceCPE_cityname, xml);
			//City Code	
			addtextFields_commonMethod(application, "City Code", "citycodeinputfield", voiceCPE_Citycode, xml);
			//Site name
			addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", voiceCPE_sitename, xml);
			//Site Code
			addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", voiceCPE_sitecode, xml);
			//Premise name	
			addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", voiceCPE_premisename, xml);
			//Premise Code	
			addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", voiceCPE_premisecode, xml);

		}	

		//Existing City	
		else if(voiceCPE_existingcity.equalsIgnoreCase("yes") & voiceCPE_newcity.equalsIgnoreCase("no")) {

			selectValueInsideDropdown(application, "citydropdowninput", "City", voiceCPE_existingcityvalue, xml);

			//Existing Site
			if(voiceCPE_existingsite.equalsIgnoreCase("yes") & voiceCPE_newsite.equalsIgnoreCase("no")) {
				selectValueInsideDropdown(application, "sitedropdowninput", "Site", voiceCPE_existingsitevalue, xml);

				//Existing Premise
				if(voiceCPE_existingpremise.equalsIgnoreCase("yes") & voiceCPE_NewPremise.equalsIgnoreCase("no")) {
					selectValueInsideDropdown(application, "premisedropdowninput", "Premise", voiceCPE_existingpremisevalue, xml);
				}

				//New Premise  
				else if(voiceCPE_existingpremise.equalsIgnoreCase("no") & voiceCPE_NewPremise.equalsIgnoreCase("yes")) {

					Clickon_addToggleButton(application, "addpremiseswitch");
					//Premise name	
					addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", voiceCPE_premisename, xml);
					//Premise Code	
					addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", voiceCPE_premisecode, xml);
				} 
			}

			else if(voiceCPE_existingsite.equalsIgnoreCase("no") & voiceCPE_newsite.equalsIgnoreCase("yes")) {

				Clickon_addToggleButton(application, "addsiteswitch");
				//Site name
				addtextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", voiceCPE_sitename, xml);
				//Site Code
				addtextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", voiceCPE_sitecode, xml);
				//Premise name	
				addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", voiceCPE_premisename, xml);
				//Premise Code	
				addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", voiceCPE_premisecode, xml);
			}
		}
		Thread.sleep(1000);
		scrolltoend();
		Thread.sleep(1000);
		addCheckbox_commonMethod(application, "cpetoprovidedialtone_checkbox", "CPE to Provide Dial Tone", cpetoprovidedialtone_checkbox, "No", xml);
		addCheckbox_commonMethod(application, "cpelinepowerrequired_checkbox", "CPE Line Power Required", cpelinepowerrequired_checkbox, "No", xml);
		addCheckbox_commonMethod(application, "numberporting_checkbox", "Number Porting", numberporting_checkbox, "No", xml);
		addCheckbox_commonMethod(application, "briportmapping_checkbox", "BRI Port Mapping", briportmapping_checkbox, "No", xml);
		selectValueInsideDropdown(application, "crcsettings_dropdown", "CRC Settings", crcsettings_dropdownvalue, xml);
		selectValueInsideDropdown(application, "numberofpriports_dropdown", "Number of PRI Ports", numberofpriports_dropdownvalue, xml);
		selectValueInsideDropdown(application, "numberofbriports_dropdown", "Number of BRI Ports", numberofbriports_dropdownvalue, xml);
		if(briportmapping_checkbox.equalsIgnoreCase("Yes")) 
		{
			numberofBRIPorts(application, numberofbriports_dropdownvalue, briport1_value, briport2_value, briport3_value, briport4_value, briport5_value, briport6_value, briport7_value, briport8_value);
		}
		Thread.sleep(1000);
		ScrolltoElement(application, "okbutton", xml);
		selectValueInsideDropdown(application, "numberoffxsports_dropdown", "Number of FXS Ports", numberofFXSports_dropdownvalue, xml);
		NumberofFXSPorts(application, numberofFXSports_dropdownvalue, fxsnumber1_value, fxsnumber2_value);
		ScrolltoElement(application, "okbutton", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Device created successfully");
	}

	public void numberofBRIPorts(String application, String numberofbriports_dropdownvalue, String briport1_value, String briport2_value, String briport3_value, String briport4_value, String briport5_value, String briport6_value, String briport7_value, String briport8_value) throws InterruptedException, DocumentException, IOException {
		if(numberofbriports_dropdownvalue.equalsIgnoreCase("1"))
		{
			addtextFields_commonMethod(application, "BRI Port 1 Number", "briport1_textfield", briport1_value, xml);
			click_commonMethod(application, "BRI Port 1 Number Add arrow", "briport1_addarrow", xml);
			Thread.sleep(1000);
		}
		else if(numberofbriports_dropdownvalue.equalsIgnoreCase("2"))
		{
			addtextFields_commonMethod(application, "BRI Port 1 Number", "briport1_textfield", briport1_value, xml);
			click_commonMethod(application, "BRI Port 1 Number Add arrow", "briport1_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 2 Number", "briport2_textfield", briport2_value, xml);
			click_commonMethod(application, "BRI Port 2 Number Add arrow", "briport2_addarrow", xml);
			Thread.sleep(1000);
		}
		else if(numberofbriports_dropdownvalue.equalsIgnoreCase("3"))
		{
			addtextFields_commonMethod(application, "BRI Port 1 Number", "briport1_textfield", briport1_value, xml);
			click_commonMethod(application, "BRI Port 1 Number Add arrow", "briport1_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 2 Number", "briport2_textfield", briport2_value, xml);
			click_commonMethod(application, "BRI Port 2 Number Add arrow", "briport2_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 3 Number", "briport3_textfield", briport3_value, xml);
			click_commonMethod(application, "BRI Port 3 Number Add arrow", "briport3_addarrow", xml);
			Thread.sleep(1000);
		}
		else if(numberofbriports_dropdownvalue.equalsIgnoreCase("4"))
		{
			addtextFields_commonMethod(application, "BRI Port 1 Number", "briport1_textfield", briport1_value, xml);
			click_commonMethod(application, "BRI Port 1 Number Add arrow", "briport1_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 2 Number", "briport2_textfield", briport2_value, xml);
			click_commonMethod(application, "BRI Port 2 Number Add arrow", "briport2_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 3 Number", "briport3_textfield", briport3_value, xml);
			click_commonMethod(application, "BRI Port 3 Number Add arrow", "briport3_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 4 Number", "briport4_textfield", briport4_value, xml);
			click_commonMethod(application, "BRI Port 4 Number Add arrow", "briport4_addarrow", xml);
			Thread.sleep(1000);
		}
		else if(numberofbriports_dropdownvalue.equalsIgnoreCase("5"))
		{
			addtextFields_commonMethod(application, "BRI Port 1 Number", "briport1_textfield", briport1_value, xml);
			click_commonMethod(application, "BRI Port 1 Number Add arrow", "briport1_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 2 Number", "briport2_textfield", briport2_value, xml);
			click_commonMethod(application, "BRI Port 2 Number Add arrow", "briport2_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 3 Number", "briport3_textfield", briport3_value, xml);
			click_commonMethod(application, "BRI Port 3 Number Add arrow", "briport3_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 4 Number", "briport4_textfield", briport4_value, xml);
			click_commonMethod(application, "BRI Port 4 Number Add arrow", "briport4_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 5 Number", "briport5_textfield", briport5_value, xml);
			click_commonMethod(application, "BRI Port 5 Number Add arrow", "briport5_addarrow", xml);
			Thread.sleep(1000);
		}

		else if(numberofbriports_dropdownvalue.equalsIgnoreCase("6"))
		{
			addtextFields_commonMethod(application, "BRI Port 1 Number", "briport1_textfield", briport1_value, xml);
			click_commonMethod(application, "BRI Port 1 Number Add arrow", "briport1_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 2 Number", "briport2_textfield", briport2_value, xml);
			click_commonMethod(application, "BRI Port 2 Number Add arrow", "briport2_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 3 Number", "briport3_textfield", briport3_value, xml);
			click_commonMethod(application, "BRI Port 3 Number Add arrow", "briport3_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 4 Number", "briport4_textfield", briport4_value, xml);
			click_commonMethod(application, "BRI Port 4 Number Add arrow", "briport4_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 5 Number", "briport5_textfield", briport5_value, xml);
			click_commonMethod(application, "BRI Port 5 Number Add arrow", "briport5_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 6 Number", "briport6_textfield", briport6_value, xml);
			click_commonMethod(application, "BRI Port 6 Number Add arrow", "briport6_addarrow", xml);
			Thread.sleep(1000);
		}
		else if(numberofbriports_dropdownvalue.equalsIgnoreCase("7"))
		{
			addtextFields_commonMethod(application, "BRI Port 1 Number", "briport1_textfield", briport1_value, xml);
			click_commonMethod(application, "BRI Port 1 Number Add arrow", "briport1_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 2 Number", "briport2_textfield", briport2_value, xml);
			click_commonMethod(application, "BRI Port 2 Number Add arrow", "briport2_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 3 Number", "briport3_textfield", briport3_value, xml);
			click_commonMethod(application, "BRI Port 3 Number Add arrow", "briport3_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 4 Number", "briport4_textfield", briport4_value, xml);
			click_commonMethod(application, "BRI Port 4 Number Add arrow", "briport4_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 5 Number", "briport5_textfield", briport5_value, xml);
			click_commonMethod(application, "BRI Port 5 Number Add arrow", "briport5_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 6 Number", "briport6_textfield", briport6_value, xml);
			click_commonMethod(application, "BRI Port 6 Number Add arrow", "briport6_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 7 Number", "briport7_textfield", briport7_value, xml);
			click_commonMethod(application, "BRI Port 7 Number Add arrow", "briport7_addarrow", xml);
			Thread.sleep(1000);
		}
		else if(numberofbriports_dropdownvalue.equalsIgnoreCase("8"))
		{
			addtextFields_commonMethod(application, "BRI Port 1 Number", "briport1_textfield", briport1_value, xml);
			click_commonMethod(application, "BRI Port 1 Number Add arrow", "briport1_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 2 Number", "briport2_textfield", briport2_value, xml);
			click_commonMethod(application, "BRI Port 2 Number Add arrow", "briport2_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 3 Number", "briport3_textfield", briport3_value, xml);
			click_commonMethod(application, "BRI Port 3 Number Add arrow", "briport3_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 4 Number", "briport4_textfield", briport4_value, xml);
			click_commonMethod(application, "BRI Port 4 Number Add arrow", "briport4_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 5 Number", "briport5_textfield", briport5_value, xml);
			click_commonMethod(application, "BRI Port 5 Number Add arrow", "briport5_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 6 Number", "briport6_textfield", briport6_value, xml);
			click_commonMethod(application, "BRI Port 6 Number Add arrow", "briport6_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 7 Number", "briport7_textfield", briport7_value, xml);
			click_commonMethod(application, "BRI Port 7 Number Add arrow", "briport7_addarrow", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "BRI Port 8 Number", "briport8_textfield", briport8_value, xml);
			click_commonMethod(application, "BRI Port 8 Number Add arrow", "briport8_addarrow", xml);
			Thread.sleep(1000);
		}
	}

	public void NumberofFXSPorts(String application, String numberofFXSports_dropdownvalue, String fxsnumber1_value, String fxsnumber2_value) throws InterruptedException, DocumentException, IOException {

		if(numberofFXSports_dropdownvalue.equalsIgnoreCase("1")) {

			addtextFields_commonMethod(application, "fxsnumber1", "fxsnumber1_textfield", fxsnumber1_value, xml);
		}
		else if(numberofFXSports_dropdownvalue.equalsIgnoreCase("2")) {

			addtextFields_commonMethod(application, "fxsnumber1", "fxsnumber1_textfield", fxsnumber1_value, xml);
			addtextFields_commonMethod(application, "fxsnumber2", "fxsnumber2_textfield", fxsnumber2_value, xml);
		}
	}
	public void Clickon_addToggleButton(String application, String xpath) throws InterruptedException, DocumentException {

		//Add Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
		Thread.sleep(5000);
	}

	public void verifyAddDRPlans(String application, String Add_DRplanA, String Add_DRplanB, String Add_DRplanC, String Add_DRplanD, String Add_DRplanE, String rangestart_cc, String rangestart_lac, String rangestart_num, String rangefinish_cc, String rangefinish_lac, String rangefinish_num, String destinationnumber_cc, String destinationnumber_lac, String destinationnumber_num, String activate_deactivateDRplan_dropdownvalue, String edit_rangestart_cc, String edit_rangestart_lac, String edit_rangestart_num, String edit_rangefinish_cc, String edit_rangefinish_lac, String edit_rangefinish_num, String edit_destinationnumber_cc, String edit_destinationnumber_lac, String edit_destinationnumber_num, String edit_activate_deactivateDRplan_dropdownvalue) throws InterruptedException, DocumentException, IOException {

		Thread.sleep(3000);
		ScrolltoElement(application, "viewpage_usageofincomingrouting", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Add DR Plan", "addDRplans_link", xml);
		Thread.sleep(1000);
		waitforPagetobeenable();
		compareText(application, "Add DR Plans Header", "addDRplan_header", "Disaster Recovery Plans", xml);

		if(Add_DRplanA.equalsIgnoreCase("Yes")) {
			compareText(application, "DR Plan A_header", "DRplanA_header", "DR Plan A", xml);
			click_commonMethod(application, "DR Plan A Action dropdown", "DRplanA_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Add", "DRplan_addlink", xml);
			Thread.sleep(1000);
			AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
			compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
			compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
			compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
			compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

			//Edit DR Plan
			addedDRplan(application, "DR Plan A", rangestart_cc, rangestart_lac, rangestart_num);
			click_commonMethod(application, "DR Plan A Action dropdown", "DRplanA_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Edit", "DRplan_editlink", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

			//Delete DR Plan
			if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
			{
				editedDRplan(application, "DR Plan A", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
			}
			else
			{
				addedDRplan(application, "DR Plan A", rangestart_cc, rangestart_lac, rangestart_num);	
			}
			click_commonMethod(application, "DR Plan A Action dropdown", "DRplanA_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Delete", "DRplan_Deletelink", xml);
			Thread.sleep(2000);
			deleteDRPlan(application);

		}
		if(Add_DRplanB.equalsIgnoreCase("Yes")) {
			ScrolltoElement(application, "DRplanA_header", xml);
			Thread.sleep(1000);
			compareText(application, "DR Plan B_header", "DRplanB_header", "DR Plan B", xml);
			click_commonMethod(application, "DR Plan B Action dropdown", "DRplanB_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Add", "DRplan_addlink", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
			compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
			compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
			compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
			compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

			//Edit DR Plan
			ScrolltoElement(application, "DRplanA_header", xml);
			Thread.sleep(1000);
			addedDRplan(application, "DR Plan B", rangestart_cc, rangestart_lac, rangestart_num);
			click_commonMethod(application, "DR Plan B Action dropdown", "DRplanB_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Edit", "DRplan_editlink", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

			//Delete DR Plan
			ScrolltoElement(application, "DRplanA_header", xml);
			Thread.sleep(1000);
			if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
			{
				editedDRplan(application, "DR Plan B", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
			}
			else
			{
				addedDRplan(application, "DR Plan B", rangestart_cc, rangestart_lac, rangestart_num);	
			}
			click_commonMethod(application, "DR Plan B Action dropdown", "DRplanB_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Delete", "DRplan_Deletelink", xml);
			Thread.sleep(1000);
			deleteDRPlan(application);
		}
		if(Add_DRplanC.equalsIgnoreCase("Yes")) {

			ScrolltoElement(application, "DRplanB_header", xml);
			Thread.sleep(1000);
			compareText(application, "DR Plan C_header", "DRplanC_header", "DR Plan C", xml);
			click_commonMethod(application, "DR Plan C Action dropdown", "DRplanC_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Add", "DRplan_addlink", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
			compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
			compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
			compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
			compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

			//Edit DR Plan
			ScrolltoElement(application, "DRplanB_header", xml);
			Thread.sleep(1000);
			addedDRplan(application, "DR Plan C", rangestart_cc, rangestart_lac, rangestart_num);
			click_commonMethod(application, "DR Plan C Action dropdown", "DRplanC_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Edit", "DRplan_editlink", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

			//Delete DR Plan
			ScrolltoElement(application, "DRplanB_header", xml);
			Thread.sleep(1000);
			if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
			{
				editedDRplan(application, "DR Plan C", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
			}
			else
			{
				addedDRplan(application, "DR Plan C", rangestart_cc, rangestart_lac, rangestart_num);	
			}
			click_commonMethod(application, "DR Plan C Action dropdown", "DRplanC_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Delete", "DRplan_Deletelink", xml);
			Thread.sleep(1000);
			deleteDRPlan(application);

		}
		if(Add_DRplanD.equalsIgnoreCase("Yes")) {
			ScrolltoElement(application, "DRplanC_header", xml);
			Thread.sleep(1000);
			compareText(application, "DR Plan D_header", "DRplanD_header", "DR Plan D", xml);
			click_commonMethod(application, "DR Plan D Action dropdown", "DRplanD_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Add", "DRplanD_E_addlink", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
			compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
			compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
			compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
			compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

			//Edit DR Plan
			ScrolltoElement(application, "DRplanC_header", xml);
			Thread.sleep(1000);
			addedDRplan(application, "DR Plan D", rangestart_cc, rangestart_lac, rangestart_num);
			click_commonMethod(application, "DR Plan D Action dropdown", "DRplanD_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Edit", "DRplanD_E_editlink", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

			//Delete DR Plan
			ScrolltoElement(application, "DRplanC_header", xml);
			Thread.sleep(1000);
			if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
			{
				editedDRplan(application, "DR Plan D", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
			}
			else
			{
				addedDRplan(application, "DR Plan D", rangestart_cc, rangestart_lac, rangestart_num);	
			}
			click_commonMethod(application, "DR Plan D Action dropdown", "DRplanD_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Delete", "DRplanD_E_Deletelink", xml);
			Thread.sleep(1000);
			deleteDRPlan(application);

		}
		if(Add_DRplanE.equalsIgnoreCase("Yes")) {
			ScrolltoElement(application, "DRplanD_header", xml);
			Thread.sleep(2000);
			compareText(application, "DR Plan E_header", "DRplanE_header", "DR Plan E", xml);
			click_commonMethod(application, "DR Plan E Action dropdown", "DRplanE_actiondropdown", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Add", "DRplanD_E_addlink", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			AddDRPlan(application, rangestart_cc, rangestart_lac, rangestart_num, rangefinish_cc, rangefinish_lac, rangefinish_num, destinationnumber_cc, destinationnumber_lac, destinationnumber_num, activate_deactivateDRplan_dropdownvalue);
			compareText(application, "Range Start", "DRplanA_rangestart_columnheader", "Range Start", xml);
			compareText(application, "Range Finish", "DRplanA_rangefinish_columnheader", "Range Finish", xml);
			compareText(application, "Destination Number(DN)", "DRplanA_destinationnumber_columnheader", "Destination Number(DN)", xml);
			compareText(application, "Activate/Deactivate DR Plan", "DRplanA_activateDeactivate_columnheader", "Activate/Deactivate DR Plan", xml);

			//Edit DR Plan
			ScrolltoElement(application, "DRplanD_header", xml);
			Thread.sleep(1000);
			addedDRplan(application, "DR Plan E", rangestart_cc, rangestart_lac, rangestart_num);
			click_commonMethod(application, "DR Plan E Action dropdown", "DRplanE_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Edit", "DRplanD_E_editlink", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			EditDRPlan(application, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num, edit_rangefinish_cc, edit_rangefinish_lac, edit_rangefinish_num, edit_destinationnumber_cc, edit_destinationnumber_lac, edit_destinationnumber_num, edit_activate_deactivateDRplan_dropdownvalue);

			//Delete DR Plan
			ScrolltoElement(application, "DRplanD_header", xml);
			Thread.sleep(1000);
			if(edit_rangestart_cc.equalsIgnoreCase("Yes") || edit_rangestart_cc.equalsIgnoreCase("null") || edit_rangestart_lac.equalsIgnoreCase("Yes")|| edit_rangestart_lac.equalsIgnoreCase("null") || edit_rangestart_num.equalsIgnoreCase("Yes")||edit_rangestart_num.equalsIgnoreCase("null"))
			{
				editedDRplan(application, "DR Plan E", rangestart_cc, rangestart_lac, rangestart_num, edit_rangestart_cc, edit_rangestart_lac, edit_rangestart_num);
			}
			else
			{
				addedDRplan(application, "DR Plan E", rangestart_cc, rangestart_lac, rangestart_num);	
			}
			click_commonMethod(application, "DR Plan E Action dropdown", "DRplanE_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Delete", "DRplanD_E_Deletelink", xml);
			Thread.sleep(1000);
			deleteDRPlan(application);
		}
		Thread.sleep(1000);
		ScrolltoElement(application, "viewpage_backbutton", xml);
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		waitforPagetobeenable();
	}


	public void AddDRPlan(String application, String rangestart_cc, String rangestart_lac, String rangestart_num, String rangefinish_cc, String rangefinish_lac, String rangefinish_num, String destinationnumber_cc, String destinationnumber_lac, String destinationnumber_num, String activate_deactivateDRplan_dropdownvalue) throws InterruptedException, DocumentException, IOException {

		compareText(application, "Add DR Plan Header", "addDRplan_header1", "Disaster Recovery Plans", xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(1000);
		waitforPagetobeenable();
		//verify warning messages
		warningMessage_commonMethod(application, "rangestart_cc_warngmsg", "Range Start CC", xml);
		warningMessage_commonMethod(application, "rangestart_lac_warngmsg", "Range Start LAC", xml);
		warningMessage_commonMethod(application, "rangestart_num_warngmsg", "Range Start Num", xml);
		warningMessage_commonMethod(application, "rangefinish_cc_warngmsg", "Range Finish CC", xml);
		warningMessage_commonMethod(application, "rangefinish_lac_warngmsg", "Range Finish LAC", xml);
		warningMessage_commonMethod(application, "rangefinish_num_warngmsg", "Range Finish Num", xml);
		warningMessage_commonMethod(application, "destinationnumber_cc_warngmsg", "Destination Number CC", xml);
		warningMessage_commonMethod(application, "destinationnumber_lac_warngmsg", "Destination Number LAC", xml);
		warningMessage_commonMethod(application, "destinationnumber_num_warngmsg", "Destination Number Num", xml);

		//verify Add DR plan
		compareText(application, "Range Start", "rangestart_label", "Range Start", xml);
		addtextFields_commonMethod(application, "Range Start CC", "rangestart_cc", rangestart_cc, xml);
		addtextFields_commonMethod(application, "Range Start LAC", "rangestart_lac", rangestart_lac, xml);
		addtextFields_commonMethod(application, "Range Start Num", "rangestart_num", rangestart_num, xml);
		compareText(application, "Range Finish", "rangefinish_label", "Range Finish", xml);
		addtextFields_commonMethod(application, "Range Finish CC", "rangefinish_cc", rangefinish_cc, xml);
		addtextFields_commonMethod(application, "Range Finish LAC", "rangefinish_lac", rangefinish_lac, xml);
		addtextFields_commonMethod(application, "Range Finish Num", "rangefinish_num", rangefinish_num, xml);
		compareText(application, "Destination Number(DN)", "destinationnumber_label", "Destination Number(DN)", xml);
		addtextFields_commonMethod(application, "Destination Number CC", "destinationnumber_cc", destinationnumber_cc, xml);
		addtextFields_commonMethod(application, "Destination Number LAC", "destinationnumber_lac", destinationnumber_lac, xml);
		addtextFields_commonMethod(application, "Destination Number Num", "destinationnumber_num", destinationnumber_num, xml);
		compareText(application, "Activate/Deactivate DR Plan", "activate_deactivate_label", "Activate/Deactivate DR Plan", xml);
		activate_deactivateDRPlan_dropdown(application, "Activate/Deactivate DR Plan", "activate_deactivate_dropdownvalue", activate_deactivateDRplan_dropdownvalue, xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		waitforPagetobeenable();
	}

	public void addedDRplan(String application, String DRPlanHeader, String rangestart_cc, String rangestart_lac, String rangestart_num) throws InterruptedException, DocumentException {

		String RangeStartValue= rangestart_cc+"-"+rangestart_lac+"-"+rangestart_num;
		String AddedDRplan= getwebelement(xml.getlocator("//locators/" + application + "/addedDRplan_tablelist").replace("value", DRPlanHeader)).getAttribute("style");
		if(!AddedDRplan.contains("height: 1px"))
		{
			List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/addedDRplan_rangestart").replace("value", DRPlanHeader));

			int numofrows = results.size();
			System.out.println("no of results: " + numofrows);

			if ((numofrows == 0)) {

				ExtentTestManager.getTest().log(LogStatus.PASS, DRPlanHeader+" table is empty");
			}
			else {
				for (int i = 0; i < numofrows; i++) {
					try {

						String AddedDRplandata = results.get(i).getText();
						System.out.println(AddedDRplandata);
						if (AddedDRplandata.equalsIgnoreCase(RangeStartValue)) {

							String DRPlanRowID= getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@col-id='rangeStart'][text()='"+RangeStartValue+"']/parent::div").getAttribute("row-id");
							System.out.println("DR Plan row id: "+DRPlanRowID);
							if(getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@row-id='"+DRPlanRowID+"']/div//span[contains(@class,'unchecked')]").isDisplayed())
							{
								WebElement AddedDRPlan_Checkbox= getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@row-id='"+DRPlanRowID+"']/div//span[contains(@class,'unchecked')]");
								Clickon(AddedDRPlan_Checkbox);
								ExtentTestManager.getTest().log(LogStatus.PASS, "Added DR Plan checkbox is checked");
							}
							else
							{
								ExtentTestManager.getTest().log(LogStatus.PASS, "Added DR Plan checkbox is already checked");
							}

						}

					} catch (StaleElementReferenceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
				Thread.sleep(1000);
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No DR Plan added in table");
		}

	}

	public void editedDRplan(String application, String DRPlanHeader, String rangestart_cc, String rangestart_lac, String rangestart_num, String edit_rangestart_cc, String edit_rangestart_lac, String edit_rangestart_num) throws InterruptedException, DocumentException {

		String rangestart_cc_value;
		String rangestart_lac_value;
		String rangestart_num_value;

		if(edit_rangestart_cc.equalsIgnoreCase("null"))
		{
			rangestart_cc_value= rangestart_cc;
		}
		else
		{
			rangestart_cc_value= edit_rangestart_cc;
		}
		if(edit_rangestart_lac.equalsIgnoreCase("null"))
		{
			rangestart_lac_value= rangestart_lac;
		}
		else
		{
			rangestart_lac_value= edit_rangestart_lac;
		}
		if(edit_rangestart_num.equalsIgnoreCase("null"))
		{
			rangestart_num_value= rangestart_num;
		}
		else
		{
			rangestart_num_value= edit_rangestart_num;
		}
		String RangeStartValue= rangestart_cc_value+"-"+rangestart_lac_value+"-"+rangestart_num_value;
		String AddedDRplan= getwebelement(xml.getlocator("//locators/" + application + "/addedDRplan_tablelist").replace("value", DRPlanHeader)).getAttribute("style");
		if(!AddedDRplan.contains("height: 1px"))
		{
			List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/addedDRplan_rangestart").replace("value", DRPlanHeader));

			int numofrows = results.size();
			System.out.println("no of results: " + numofrows);

			if ((numofrows == 0)) {

				ExtentTestManager.getTest().log(LogStatus.PASS, DRPlanHeader+" table is empty");
			}
			else {
				for (int i = 0; i < numofrows; i++) {
					try {

						String AddedDRplandata = results.get(i).getText();
						System.out.println(AddedDRplandata);
						if (AddedDRplandata.equalsIgnoreCase(RangeStartValue)) {

							String DRPlanRowID= getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@col-id='rangeStart'][text()='"+RangeStartValue+"']/parent::div").getAttribute("row-id");
							System.out.println("DR Plan row id: "+DRPlanRowID);
							if(getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@row-id='"+DRPlanRowID+"']/div//span[contains(@class,'unchecked')]").isDisplayed())
							{
								WebElement AddedDRPlan_Checkbox= getwebelement("(//div[text()='"+DRPlanHeader+"']/parent::div/following-sibling::div[@class='ag-div-margin row'])[1]//div[@ref='eBodyContainer']//div[@row-id='"+DRPlanRowID+"']/div//span[contains(@class,'unchecked')]");
								Clickon(AddedDRPlan_Checkbox);
								ExtentTestManager.getTest().log(LogStatus.PASS, "Added DR Plan checkbox is checked");
							}
							else
							{
								ExtentTestManager.getTest().log(LogStatus.PASS, "Added DR Plan checkbox is already checked");
							}

						}

					} catch (StaleElementReferenceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
				Thread.sleep(1000);
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No DR Plan added in table");
		}

	}

	public void EditDRPlan(String application, String edit_rangestart_cc, String edit_rangestart_lac, String edit_rangestart_num, String edit_rangefinish_cc, String edit_rangefinish_lac, String edit_rangefinish_num, String edit_destinationnumber_cc, String edit_destinationnumber_lac, String edit_destinationnumber_num, String edit_activate_deactivateDRplan_dropdownvalue) throws InterruptedException, DocumentException, IOException {

		compareText(application, "Add DR Plan Header", "addDRplan_header1", "Disaster Recovery Plans", xml);
		compareText(application, "Range Start", "rangestart_label", "Range Start", xml);
		edittextFields_commonMethod(application, "Range Start CC", "rangestart_cc", edit_rangestart_cc, xml);
		edittextFields_commonMethod(application, "Range Start LAC", "rangestart_lac", edit_rangestart_lac, xml);
		edittextFields_commonMethod(application, "Range Start Num", "rangestart_num", edit_rangestart_num, xml);
		compareText(application, "Range Finish", "rangefinish_label", "Range Finish", xml);
		edittextFields_commonMethod(application, "Range Finish CC", "rangefinish_cc", edit_rangefinish_cc, xml);
		edittextFields_commonMethod(application, "Range Finish LAC", "rangefinish_lac", edit_rangefinish_lac, xml);
		edittextFields_commonMethod(application, "Range Finish Num", "rangefinish_num", edit_rangefinish_num, xml);
		compareText(application, "Destination Number(DN)", "destinationnumber_label", "Destination Number(DN)", xml);
		edittextFields_commonMethod(application, "Destination Number CC", "destinationnumber_cc", edit_destinationnumber_cc, xml);
		edittextFields_commonMethod(application, "Destination Number LAC", "destinationnumber_lac", edit_destinationnumber_lac, xml);
		edittextFields_commonMethod(application, "Destination Number Num", "destinationnumber_num", edit_destinationnumber_num, xml);
		compareText(application, "Activate/Deactivate DR Plan", "activate_deactivate_label", "Activate/Deactivate DR Plan", xml);
		activate_deactivateDRPlan_dropdown(application, "Activate/Deactivate DR Plan", "activate_deactivate_dropdownvalue", edit_activate_deactivateDRplan_dropdownvalue, xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		waitforPagetobeenable();
	}

	public void deleteDRPlan(String application) {

		Alert alert = driver.switchTo().alert();		

		// Capturing alert message   
		String alertMessage= driver.switchTo().alert().getText();
		if(alertMessage.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
			System.out.println("No Message displays"); 
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
			System.out.println("Text message for alert displays as: "+alertMessage);
		}

		try {  
			alert.accept();
			Thread.sleep(2000);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void activate_deactivateDRPlan_dropdown(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml) {

		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				System.out.println(labelname + " dropdown is displaying");

				if(expectedValueToAdd.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					System.out.println(" No values selected under "+ labelname + " dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='"+ labelname +"']]/parent::div/following-sibling::div//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));

					ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					System.out.println( " List of values inside "+ labelname + "dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					Thread.sleep(2000);
					SendKeys(getwebelement("(//label[text()='"+ labelname +"']/parent::div/parent::div/following-sibling::div//input)[1]"), expectedValueToAdd);	
					Thread.sleep(2000);

					Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//label[text()='"+ labelname +"']/parent::div/parent::div/following-sibling::div//span").getText();
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

	public void verifyDRPlansBulkInterface(String application, String bulkjob_filepath) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "viewpage_usageofincomingrouting", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "DR Plans Bulk Interface", "DRplans_bulkinterface_link", xml);
		Thread.sleep(1000);
		waitforPagetobeenable();
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		try {
		WebElement BulkJob_Choosefile_button= getwebelement(xml.getlocator("//locators/" + application + "/bulkjob_choosefilebutton"));
		BulkJob_Choosefile_button.sendKeys(bulkjob_filepath);
		click_commonMethod(application, "Submit", "bulkjobsubmit", xml);
		waitforPagetobeenable();
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Upload file path '"+bulkjob_filepath+"' is not found");
		}
		//Archive link in bulk interface page
		click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
		click_commonMethod(application, "Archive", "bulkinterface_archivelink", xml);
		Thread.sleep(4000);
		waitforPagetobeenable();
		ScrolltoElement(application, "archive_backbutton", xml);
		click_commonMethod(application, "Back", "archive_backbutton", xml);
		waitforPagetobeenable();
		//Refresh link in bulk interface page
		scrollToTop();
		click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
		click_commonMethod(application, "Refresh", "bulkinterface_refreshlink", xml);
		waitforPagetobeenable();
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Bulk Interface page refresh successful");
		Log.info("Bulk Interface page refresh successful");
		Thread.sleep(2000);

		addedBulkInterface(application);
		ScrolltoElement(application, "bulkinterface_backbutton", xml);
		click_commonMethod(application, "Back", "bulkinterface_backbutton", xml);
	}

	public void verifydownloadDRplans(String application, String DRPlansfilename, String browserfiles_downloadspath) throws InterruptedException, DocumentException {
		
		ScrolltoElement(application, "viewpage_usageofincomingrouting", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Download DR Plans", "downloadDRplans_link", xml);
		Thread.sleep(1000);
		waitforPagetobeenable();
		isFileDownloaded(DRPlansfilename, browserfiles_downloadspath);
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

	public void addedBulkInterface(String application) throws InterruptedException, DocumentException {

		compareText(application, "TrunkGroup Name", "trunkgroupname_columnheader", "TrunkGroup Name", xml);
		compareText(application, "User Name", "username_columnheader", "User Name", xml);
		compareText(application, "Submit Time", "submittime_columnheader", "Submit Time", xml);
		compareText(application, "Start Time", "starttime_columnheader", "Start Time", xml);
		compareText(application, "End Time", "endtime_columnheader", "End Time", xml);
		compareText(application, "Status", "status_columnheader", "Status", xml);
		compareText(application, "Completion (%)", "completion_columnheader", "Completion (%)", xml);
		compareText(application, "Log", "log_columnheader", "Log", xml);
		compareText(application, "Upload File", "uploadfile_columnheader", "Upload File", xml);

		int TotalPages;
		String TotalPagesText = getwebelement("//div[@class='ag-div-margin row']//div//span[@ref='lbTotal']").getText();
		TotalPages = Integer.parseInt(TotalPagesText);
		System.out.println("Total number of pages in table is: " + TotalPages);

		if (TotalPages != 0) {

			outerloop:
				for (int k = 1; k <= TotalPages; k++) {
					String AddedDRplan_BulkInterface= getwebelement(xml.getlocator("//locators/" + application + "/addedbulkinterface_tablelist")).getAttribute("style");
					if(!AddedDRplan_BulkInterface.contains("height: 1px"))
					{
						List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/addedbulkinterface_trunkgroupname").replace("value", Edit_primarytrunkGroupname));

						int numofrows = results.size();
						System.out.println("no of results: " + numofrows);

						if ((numofrows == 0)) {

							ExtentTestManager.getTest().log(LogStatus.PASS, "Bulk Interface table is empty");
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

									String AddedBulkInterfacedata = results.get(i).getText();
									System.out.println(AddedBulkInterfacedata);
									if (AddedBulkInterfacedata.equalsIgnoreCase(Edit_primarytrunkGroupname)) {

										String BulkInterfaceRowID= getwebelement(xml.getlocator("//locators/" + application + "/addedbulkinterface_rowid").replace("value", Edit_primarytrunkGroupname)).getAttribute("row-id");
										System.out.println("Bulk Interface row id: "+BulkInterfaceRowID);
										String BulkInterface_Trunkgroupname= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_trunkgroupname_value").replace("value", BulkInterfaceRowID)).getText();
										ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Trunk Group Name column value:"+BulkInterface_Trunkgroupname);
										String BulkInterface_Username= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_username_value").replace("value", BulkInterfaceRowID)).getText();
										ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User Name column value:"+BulkInterface_Username);
										String BulkInterface_SubmitTime= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_submittime_value").replace("value", BulkInterfaceRowID)).getText();
										ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Submit Time column value:"+BulkInterface_SubmitTime);
										String BulkInterface_startTime= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_starttime_value").replace("value", BulkInterfaceRowID)).getText();
										ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Start Time column value:"+BulkInterface_startTime);
										String BulkInterface_endTime= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_endtime_value").replace("value", BulkInterfaceRowID)).getText();
										ExtentTestManager.getTest().log(LogStatus.PASS, "Step : End Time column value:"+BulkInterface_endTime);
										String BulkInterface_status= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_status_value").replace("value", BulkInterfaceRowID)).getText();
										ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Status column value:"+BulkInterface_status);
										String BulkInterface_completionPercentage= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_completion_value").replace("value", BulkInterfaceRowID)).getText();
										ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Completion Percentage column value:"+BulkInterface_completionPercentage);
										String BulkInterface_Log= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_log").replace("value", BulkInterfaceRowID)).getText();
										ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Log column value:"+BulkInterface_Log);
										String BulkInterface_Uploadfile= getwebelement(xml.getlocator("//locators/" + application + "/bulkinterface_file").replace("value", BulkInterfaceRowID)).getText();
										ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Upload file column value:"+BulkInterface_Uploadfile);
										break outerloop;
									}

								} catch (StaleElementReferenceException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();

								}
							}
							Thread.sleep(1000);
						}
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No Bulk Interface added in table");
					}
				}

		}else {

			System.out.println("No data available in table");
			Log.info("No data available in table");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No data available in table");
		}
	}

	public void VerifyDisasterRecoveryStatus(String application) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "codecfield_viewpage", xml);
		Thread.sleep(2000);
		compareText(application, "GSX/PSX Disaster Recovery Status header", "disasterrecoverystatus_header", "GSX/PSX Disaster Recovery Status", xml);
		GetText(application, "PSX TDM DR Status", "psx_tdm_drstatus_value");
		Thread.sleep(2000);

	}

	public void ViewVoiceCPEDevice(String application, String voiceCPE_devicename, String voiceCPE_vendormodel, 
			String voiceCPE_managementaddress, String voiceCPE_country, String cpetoprovidedialtone_checkbox, String cpelinepowerrequired_checkbox, String numberporting_checkbox, 
			String briportmapping_checkbox, String crcsettings_dropdownvalue, String numberofpriports_dropdownvalue, String numberofbriports_dropdownvalue, String numberofFXSports_dropdownvalue) throws InterruptedException, DocumentException {

		Thread.sleep(3000);
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "voiceCPEdevice_header", xml);
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/existingvoicecpe_devicegrid")).isDisplayed())
		{
			List<WebElement> AddedVoiceCPEDevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addedvoiceCPEdevicename"));
			System.out.println(AddedVoiceCPEDevicesList);
			int AddedVoiceCPEDevicesCount= AddedVoiceCPEDevicesList.size();

			for(int i=0;i<AddedVoiceCPEDevicesCount;i++) {
				String AddedVoiceCPEDeviceNameText= AddedVoiceCPEDevicesList.get(i).getText();
				System.out.println(AddedVoiceCPEDeviceNameText);
				String AddedVoiceCPEDevice_SNo= AddedVoiceCPEDeviceNameText.substring(0, 1);

				WebElement AddedVoiceCPEDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addedvoicecpedevice_viewlink").replace("value", AddedVoiceCPEDevice_SNo));
				Clickon(AddedVoiceCPEDevice_ViewLink);
				Thread.sleep(2000);
				compareText(application, "Voice CPE Device", "viewpage_voicecpedevice_header", "Voice CPE Device", xml);
				compareText_InViewPage(application, "Name", voiceCPE_devicename, xml);
				compareText_InViewPage(application, "Vendor/Model", voiceCPE_vendormodel, xml);
				compareText_InViewPage(application, "Management Address", voiceCPE_managementaddress, xml);
				compareText_InViewPage(application, "Snmpro", "incc", xml);
				GetText(application, "Country", "country_voiceCPE_viewpage");
				GetText(application, "City", "city_voiceCPE_viewpage");
				GetText(application, "Site", "site_voiceCPE_viewpage");
				GetText(application, "Premise", "premise_voiceCPE_viewpage");

				if(cpetoprovidedialtone_checkbox.equalsIgnoreCase("Yes"))
				{
					compareText(application, "CPE to Provide Dial tone", "cpetoprovide_checkboxvalue", "true", xml);
				}
				else
				{
					compareText(application, "CPE to Provide Dial tone", "cpetoprovide_checkboxvalue", "", xml);
				}
				if(cpelinepowerrequired_checkbox.equalsIgnoreCase("Yes"))
				{
					compareText(application, "CPE line power required", "cpelinepower_checkboxvalue", "true", xml);
				}
				else
				{
					compareText(application, "CPE line power required", "cpelinepower_checkboxvalue", "", xml);
				}
				if(numberporting_checkbox.equalsIgnoreCase("Yes"))
				{
					compareText(application, "Number Porting", "numberporting_checkboxvalue", "true", xml);
				}
				else
				{
					compareText(application, "Number Porting", "numberporting_checkboxvalue", "", xml);
				}
//				if(briportmapping_checkbox.equalsIgnoreCase("Yes"))
//				{
//					compareText(application, "BRI Port Mapping", "BRIportmapping_checkboxvalue", "true", xml);
//				}
//				else
//				{
//					compareText(application, "BRI Port Mapping", "BRIportmapping_checkboxvalue", "", xml);
//				}
				GetText(application, "BRI Port Mapping", "BRIportmapping_checkboxvalue");
				compareText_InViewPage(application, "CRC Settings", crcsettings_dropdownvalue, xml);
				compareText_InViewPage(application, "Number of BRI ports", numberofbriports_dropdownvalue, xml);

				if(briportmapping_checkbox.equalsIgnoreCase("Yes")) 
				{
					if(numberofbriports_dropdownvalue.equalsIgnoreCase("1"))
					{
						GetText(application, "BRI Port 1 Number", "briport1number_viewpage");
						Thread.sleep(1000);
					}
					else if(numberofbriports_dropdownvalue.equalsIgnoreCase("2"))
					{
						GetText(application, "BRI Port 1 Number", "briport1number_viewpage");
						GetText(application, "BRI Port 2 Number", "briport2number_viewpage");
						Thread.sleep(1000);
					}
					else if(numberofbriports_dropdownvalue.equalsIgnoreCase("3"))
					{
						GetText(application, "BRI Port 1 Number", "briport1number_viewpage");
						GetText(application, "BRI Port 2 Number", "briport2number_viewpage");
						GetText(application, "BRI Port 3 Number", "briport3number_viewpage");
						Thread.sleep(1000);
					}
					else if(numberofbriports_dropdownvalue.equalsIgnoreCase("4"))
					{
						GetText(application, "BRI Port 1 Number", "briport1number_viewpage");
						GetText(application, "BRI Port 2 Number", "briport2number_viewpage");
						GetText(application, "BRI Port 3 Number", "briport3number_viewpage");
						GetText(application, "BRI Port 4 Number", "briport4number_viewpage");
						Thread.sleep(1000);
					}
					else if(numberofbriports_dropdownvalue.equalsIgnoreCase("5"))
					{
						GetText(application, "BRI Port 1 Number", "briport1number_viewpage");
						GetText(application, "BRI Port 2 Number", "briport2number_viewpage");
						GetText(application, "BRI Port 3 Number", "briport3number_viewpage");
						GetText(application, "BRI Port 4 Number", "briport4number_viewpage");
						GetText(application, "BRI Port 5 Number", "briport5number_viewpage");
						Thread.sleep(1000);
					}
					else if(numberofbriports_dropdownvalue.equalsIgnoreCase("6"))
					{
						GetText(application, "BRI Port 1 Number", "briport1number_viewpage");
						GetText(application, "BRI Port 2 Number", "briport2number_viewpage");
						GetText(application, "BRI Port 3 Number", "briport3number_viewpage");
						GetText(application, "BRI Port 4 Number", "briport4number_viewpage");
						GetText(application, "BRI Port 5 Number", "briport5number_viewpage");
						GetText(application, "BRI Port 6 Number", "briport6number_viewpage");
						Thread.sleep(1000);
					}
					else if(numberofbriports_dropdownvalue.equalsIgnoreCase("7"))
					{
						GetText(application, "BRI Port 1 Number", "briport1number_viewpage");
						GetText(application, "BRI Port 2 Number", "briport2number_viewpage");
						GetText(application, "BRI Port 3 Number", "briport3number_viewpage");
						GetText(application, "BRI Port 4 Number", "briport4number_viewpage");
						GetText(application, "BRI Port 5 Number", "briport5number_viewpage");
						GetText(application, "BRI Port 6 Number", "briport6number_viewpage");
						GetText(application, "BRI Port 7 Number", "briport7number_viewpage");
						Thread.sleep(1000);
					}
					else if(numberofbriports_dropdownvalue.equalsIgnoreCase("8"))
					{
						GetText(application, "BRI Port 1 Number", "briport1number_viewpage");
						GetText(application, "BRI Port 2 Number", "briport2number_viewpage");
						GetText(application, "BRI Port 3 Number", "briport3number_viewpage");
						GetText(application, "BRI Port 4 Number", "briport4number_viewpage");
						GetText(application, "BRI Port 5 Number", "briport5number_viewpage");
						GetText(application, "BRI Port 6 Number", "briport6number_viewpage");
						GetText(application, "BRI Port 7 Number", "briport7number_viewpage");
						GetText(application, "BRI Port 8 Number", "briport8number_viewpage");
						Thread.sleep(1000);
					}
				}
				compareText_InViewPage(application, "Number of PRI ports", numberofpriports_dropdownvalue, xml);
				compareText_InViewPage(application, "Number of FXS ports", numberofFXSports_dropdownvalue, xml);

				ScrolltoElement(application, "viewpage_backbutton", xml);
				click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				waitforPagetobeenable();
			}
		}

	}

	public void EditVoiceCPEDevice(String application, String edit_voiceCPE_devicename, String edit_voiceCPE_vendormodel, 
			String edit_voiceCPE_managementaddress, String edit_voiceCPE_country, String edit_voiceCPE_existingcity, String edit_voiceCPE_existingcityvalue, 
			String edit_voiceCPE_existingsite, String edit_voiceCPE_existingsitevalue, String edit_voiceCPE_existingpremise, String edit_voiceCPE_existingpremisevalue, String edit_voiceCPE_newcity,
			String edit_voiceCPE_cityname, String edit_voiceCPE_Citycode, String edit_voiceCPE_sitename, String edit_voiceCPE_sitecode, String edit_voiceCPE_premisename, String edit_voiceCPE_premisecode, 
			String edit_voiceCPE_newsite, String edit_voiceCPE_NewPremise, String edit_cpetoprovidedialtone_checkbox, String edit_cpelinepowerrequired_checkbox, String edit_numberporting_checkbox, 
			String edit_briportmapping_checkbox, String edit_crcsettings_dropdownvalue, String edit_numberofpriports_dropdownvalue, String edit_numberofbriports_dropdownvalue, 
			String edit_briport1_value, String edit_briport2_value, String edit_briport3_value, String edit_briport4_value, String edit_briport5_value, String edit_briport6_value, String edit_briport7_value, 
			String edit_briport8_value, String edit_numberofFXSports_dropdownvalue, String edit_fxsnumber1_value, String edit_fxsnumber2_value) throws IOException, InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "voiceCPEdevice_header", xml);
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/existingvoicecpe_devicegrid")).isDisplayed())
		{
			List<WebElement> AddedVoiceCPEDevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addedvoiceCPEdevicename"));
			System.out.println(AddedVoiceCPEDevicesList);
			int AddedVoiceCPEDevicesCount= AddedVoiceCPEDevicesList.size();

			for(int i=0;i<AddedVoiceCPEDevicesCount;i++) {
				String AddedVoiceCPEDeviceNameText= AddedVoiceCPEDevicesList.get(i).getText();
				System.out.println(AddedVoiceCPEDeviceNameText);
				String AddedVoiceCPEDevice_SNo= AddedVoiceCPEDeviceNameText.substring(0, 1);

				WebElement AddedVoiceCPEDevice_EditLink= getwebelement(xml.getlocator("//locators/" + application + "/addedvoicecpedevice_editlink").replace("value", AddedVoiceCPEDevice_SNo));
				Clickon(AddedVoiceCPEDevice_EditLink);
				Thread.sleep(2000);

				GetText(application, "Edit Voice CPE header", "editvoicecpe_header");
				scrollToTop();
				edittextFields_commonMethod(application, "Name", "voicecpe_devicename", edit_voiceCPE_devicename, xml);

				boolean availability=false;
				try {  
					availability=getwebelement(xml.getlocator("//locators/" + application + "/vendormodel_dropdown")).isDisplayed();
					if(availability) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Vendor/Model dropdown is displaying");
						System.out.println("Vendor/Model dropdown is displaying");

						if(edit_voiceCPE_vendormodel.equalsIgnoreCase("null")) {

							ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Vendor/Model dropdown");
							System.out.println(" No values selected under Vendor/Model dropdown");
						}else {

							Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='×']"));
							Thread.sleep(3000);

							//verify list of values inside dropdown
							List<WebElement> listofvalues = driver
									.findElements(By.xpath("//span[contains(@class,'css-f2o6e8-ItemComponent evc32pp0')]"));

							ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside Vendor/Model dropdown is:  ");
							System.out.println( " List of values inside Vendor/Model dropdown is:  ");

							for (WebElement valuetypes : listofvalues) {
								Log.info("service sub types : " + valuetypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
								System.out.println(" " + valuetypes.getText());
							}

							Thread.sleep(2000);
							SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), edit_voiceCPE_vendormodel);	
							Thread.sleep(2000);

							Clickon(getwebelement("(//span[contains(text(),'"+ edit_voiceCPE_vendormodel +"')])[1]"));
							Thread.sleep(3000);

							String actualValue=getwebelement("//label[text()='Vendor/Model']/following-sibling::div//span").getText();
							ExtentTestManager.getTest().log(LogStatus.PASS, "Vendor/Model dropdown value selected as: "+ actualValue );
							System.out.println("Vendor/Model dropdown value selected as: "+ actualValue);

						}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Vendor/Model is not displaying");
						System.out.println("Vendor/Model is not displaying");
					}
				}catch(NoSuchElementException e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Vendor/Model is not displaying");
					System.out.println("Vendor/Model is not displaying");
				}catch(Exception ee) {
					ee.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under Vendor/Model dropdown");
					System.out.println(" NO value selected under Vendor/Model dropdown");
				}

				edittextFields_commonMethod(application, "Management Address/Mask [e.g. 10.0.0.0/27]", "managementaddress_voicecpe", edit_voiceCPE_managementaddress, xml);
				compareText_fromtextFields(application, "Snmpro", "snmpro_textfield", "incc", xml);

				//select country
				ScrolltoElement(application, "okbutton", xml);
				Thread.sleep(2000);
				selectValueInsideDropdown(application, "countryinput", "Country", edit_voiceCPE_country, xml);
				ScrolltoElement(application, "managementaddress_voicecpe", xml);
				//New City		
				if(edit_voiceCPE_existingcity.equalsIgnoreCase("no") & edit_voiceCPE_newcity.equalsIgnoreCase("yes")) {
					Clickon_addToggleButton(application, "addcityswitch");
					//City name
					edittextFields_commonMethod(application, "City Name", "citynameinputfield", edit_voiceCPE_cityname, xml);
					//City Code	
					edittextFields_commonMethod(application, "City Code", "citycodeinputfield", edit_voiceCPE_Citycode, xml);
					//Site name
					edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", edit_voiceCPE_sitename, xml);
					//Site Code
					edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", edit_voiceCPE_sitecode, xml);
					//Premise name	
					edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", edit_voiceCPE_premisename, xml);
					//Premise Code	
					edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", edit_voiceCPE_premisecode, xml);

				}	

				//Existing City	
				else if(edit_voiceCPE_existingcity.equalsIgnoreCase("yes") & edit_voiceCPE_newcity.equalsIgnoreCase("no")) {

					selectValueInsideDropdown(application, "citydropdowninput", "City", edit_voiceCPE_existingcityvalue, xml);

					//Existing Site
					if(edit_voiceCPE_existingsite.equalsIgnoreCase("yes") & edit_voiceCPE_newsite.equalsIgnoreCase("no")) {
						selectValueInsideDropdown(application, "sitedropdowninput", "Site", edit_voiceCPE_existingsitevalue, xml);

						//Existing Premise
						if(edit_voiceCPE_existingpremise.equalsIgnoreCase("yes") & edit_voiceCPE_NewPremise.equalsIgnoreCase("no")) {
							selectValueInsideDropdown(application, "premisedropdowninput", "Premise", edit_voiceCPE_existingpremisevalue, xml);
						}

						//New Premise  
						else if(edit_voiceCPE_existingpremise.equalsIgnoreCase("no") & edit_voiceCPE_NewPremise.equalsIgnoreCase("yes")) {

							Clickon_addToggleButton(application, "addpremiseswitch");
							//Premise name	
							edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", edit_voiceCPE_premisename, xml);
							//Premise Code	
							edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", edit_voiceCPE_premisecode, xml);
						} 
					}

					else if(edit_voiceCPE_existingsite.equalsIgnoreCase("no") & edit_voiceCPE_newsite.equalsIgnoreCase("yes")) {

						Clickon_addToggleButton(application, "addsiteswitch");
						//Site name
						edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", edit_voiceCPE_sitename, xml);
						//Site Code
						edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", edit_voiceCPE_sitecode, xml);
						//Premise name	
						edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", edit_voiceCPE_premisename, xml);
						//Premise Code	
						edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", edit_voiceCPE_premisecode, xml);
					}
				}

				editcheckbox_commonMethod(application, edit_cpetoprovidedialtone_checkbox, "cpetoprovidedialtone_checkbox", "CPE to Provide Dial Tone", xml);
				editcheckbox_commonMethod(application, edit_cpelinepowerrequired_checkbox, "cpelinepowerrequired_checkbox", "CPE Line Power Required", xml);
				editcheckbox_commonMethod(application, edit_numberporting_checkbox, "numberporting_checkbox", "Number Porting", xml);
				editcheckbox_commonMethod(application, edit_briportmapping_checkbox, "briportmapping_checkbox", "BRI Port Mapping", xml);
				selectValueInsideDropdown(application, "crcsettings_dropdown", "CRC Settings", edit_crcsettings_dropdownvalue, xml);
				selectValueInsideDropdown(application, "numberofpriports_dropdown", "Number of PRI Ports", edit_numberofpriports_dropdownvalue, xml);
				selectValueInsideDropdown(application, "numberofbriports_dropdown", "Number of BRI Ports", edit_numberofbriports_dropdownvalue, xml);
				ScrolltoElement(application, "briportmapping_checkbox", xml);
				if(edit_briportmapping_checkbox.equalsIgnoreCase("Yes")) 
				{
					numberofBRIPorts(application, edit_numberofbriports_dropdownvalue, edit_briport1_value, edit_briport2_value, edit_briport3_value, edit_briport4_value, edit_briport5_value, edit_briport6_value, edit_briport7_value, edit_briport8_value);
				}
				Thread.sleep(1000);
				ScrolltoElement(application, "okbutton", xml);
				selectValueInsideDropdown(application, "numberoffxsports_dropdown", "Number of FXS Ports", edit_numberofFXSports_dropdownvalue, xml);
				NumberofFXSPorts(application, edit_numberofFXSports_dropdownvalue, edit_fxsnumber1_value, edit_fxsnumber2_value);
				ScrolltoElement(application, "okbutton", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "OK", "okbutton", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				verifysuccessmessage(application, "Device updated successfully");
			}
		}

	}

	public String ExistingPortGroupRow(String application, String prefix_dropdownvalue, String Edit_prefix_dropdownvalue, String routePriority_dropdownvalue, String voiceCPEdevicename, String edit_voiceCPEdevicename) throws InterruptedException, DocumentException {

		String RowID=null;
		List<WebElement> ExistingPort_prefix;
		System.out.println("Voice CPEs"+voiceCPEdevicename);
		if(Edit_prefix_dropdownvalue.equalsIgnoreCase("null"))
		{
			ExistingPort_prefix= getwebelements(xml.getlocator("//locators/" + application + "/portgrouptable_prefixvalue").replace("value", prefix_dropdownvalue));
		}
		else
		{
			ExistingPort_prefix= getwebelements(xml.getlocator("//locators/" + application + "/portgrouptable_prefixvalue").replace("value", Edit_prefix_dropdownvalue));
		}
		int ExistingPort_prefixCount= ExistingPort_prefix.size();
		for(int i=0;i<ExistingPort_prefixCount;i++)
		{
			String ExistingPort_prefix_RowID= ExistingPort_prefix.get(i).getAttribute("row-id");
			String RoutePriorityValue=getwebelement(xml.getlocator("//locators/" + application + "/portgrouptable_routepriorityvalue").replace("value", ExistingPort_prefix_RowID)).getText();
			String VoiceCPEsValue=getwebelement(xml.getlocator("//locators/" + application + "/portgrouptable_voiceCPEsValue").replace("value", ExistingPort_prefix_RowID)).getText();
			if(routePriority_dropdownvalue.contains(RoutePriorityValue))
			{
				RowID= ExistingPort_prefix_RowID;
				if(VoiceCPEsValue.contains(voiceCPEdevicename) || VoiceCPEsValue.contains(edit_voiceCPEdevicename))
				{
					RowID= ExistingPort_prefix_RowID;
					break;
				}
			}

		}
		return RowID;
	}

	public String viewPortGroupRow(String application, String prefix_dropdownvalue, String routePriority_dropdownvalue, String voiceCPEdevicename, String edit_voiceCPEdevicename) throws InterruptedException, DocumentException {

		String RowID=null;
		List<WebElement> ExistingPort_prefix;
		System.out.println("Voice CPEs"+voiceCPEdevicename);
		ExistingPort_prefix= getwebelements(xml.getlocator("//locators/" + application + "/portgrouptable_prefixvalue").replace("value", prefix_dropdownvalue));
		int ExistingPort_prefixCount= ExistingPort_prefix.size();
		for(int i=0;i<ExistingPort_prefixCount;i++)
		{
			String ExistingPort_prefix_RowID= ExistingPort_prefix.get(i).getAttribute("row-id");
			String RoutePriorityValue=getwebelement(xml.getlocator("//locators/" + application + "/portgrouptable_routepriorityvalue").replace("value", ExistingPort_prefix_RowID)).getText();
			String VoiceCPEsValue=getwebelement(xml.getlocator("//locators/" + application + "/portgrouptable_voiceCPEsValue").replace("value", ExistingPort_prefix_RowID)).getText();
			if(routePriority_dropdownvalue.contains(RoutePriorityValue))
			{
				RowID= ExistingPort_prefix_RowID;
				if(VoiceCPEsValue.contains(voiceCPEdevicename) || VoiceCPEsValue.contains(edit_voiceCPEdevicename))
				{
					RowID= ExistingPort_prefix_RowID;
					break;
				}
			}

		}
		return RowID;
	}

	public String DeletePortGroupRow(String application, String prefix_dropdownvalue, String edit_prefix_dropdownvalue, String routePriority_dropdownvalue, String voiceCPEdevicename, String edit_voiceCPEdevicename) throws InterruptedException, DocumentException {

		String RowID=null;
		List<WebElement> ExistingPort_prefix;
		if(!edit_prefix_dropdownvalue.equalsIgnoreCase("null")) {
		ExistingPort_prefix= getwebelements(xml.getlocator("//locators/" + application + "/portgrouptable_prefixvalue").replace("value", edit_prefix_dropdownvalue));
		}
		else
		{
			ExistingPort_prefix= getwebelements(xml.getlocator("//locators/" + application + "/portgrouptable_prefixvalue").replace("value", prefix_dropdownvalue));
		}
		int ExistingPort_prefixCount= ExistingPort_prefix.size();
		for(int i=0;i<ExistingPort_prefixCount;i++)
		{
			String ExistingPort_prefix_RowID= ExistingPort_prefix.get(i).getAttribute("row-id");
			String RoutePriorityValue=getwebelement(xml.getlocator("//locators/" + application + "/portgrouptable_routepriorityvalue").replace("value", ExistingPort_prefix_RowID)).getText();
			String VoiceCPEsValue=getwebelement(xml.getlocator("//locators/" + application + "/portgrouptable_voiceCPEsValue").replace("value", ExistingPort_prefix_RowID)).getText();
			if(routePriority_dropdownvalue.contains(RoutePriorityValue))
			{
				RowID= ExistingPort_prefix_RowID;
				if(VoiceCPEsValue.contains(voiceCPEdevicename) || VoiceCPEsValue.contains(edit_voiceCPEdevicename))
				{
					RowID= ExistingPort_prefix_RowID;
					break;
				}
			}

		}
		return RowID;
	}
	
	public void AddPortGroup(String application, String prefix_dropdownvalue, String routePriority_dropdownvalue, String voiceCPEdevicename, String edit_voiceCPEdevicename) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "disasterrecoverystatus_header", xml);
		compareText(application, "Port Group", "portgroup_header", "Port Group", xml);
		click_commonMethod(application, "Action Dropdown", "portgroup_Actiondropdown", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Add Port Group", "addportgroup_link", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		compareText(application, "Port Group", "addportgroup_header", "Port Group", xml);
		addPrefix_DropdownValues(application, "Prefix", "prefix_dropdown", prefix_dropdownvalue, xml);
		addDropdownValues_commonMethod(application, "Route Priority", "routepriority_dropdown", routePriority_dropdownvalue, xml);

		//Ports available
		selectPortsAvailableFromLeftDropdown(application, "Ports Available", "portsavailable_list" , voiceCPEdevicename, edit_voiceCPEdevicename, "portsavailable_addarrow");
		verifySelectedValuesInsideRightDropdown(application, "Ports Selected", "portsselected_list");

		click_commonMethod(application, "OK", "okbutton", xml);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Port Group VCPE successfully created.");
	}

	public void ViewPortGroup(String application, String prefix_dropdownvalue, String Edit_prefix_dropdownvalue, String routePriority_dropdownvalue, String voiceCPEdevicename, String edit_voiceCPEdevicename) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "disasterrecoverystatus_header", xml);
		compareText(application, "Port Group", "portgroup_header", "Port Group", xml);
		String AddedPort_RowID= viewPortGroupRow(application, prefix_dropdownvalue, routePriority_dropdownvalue, voiceCPEdevicename, edit_voiceCPEdevicename);
		WebElement AddedPort_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/addedport_checkbox").replace("value", AddedPort_RowID));
		Clickon(AddedPort_Checkbox);
		click_commonMethod(application, "Action Dropdown", "portgroup_Actiondropdown", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "View", "view", xml);
		waitforPagetobeenable();
		compareText(application, "Port Group", "addportgroup_header", "Port Group", xml);
		GetText(application, "Prefix", "prefix_viewportgroup");
		GetText(application, "Voice CPES", "voiceCPEs_viewportgroup");
		GetText(application, "Route Priority", "routepriority_viewportgroup");
		click_commonMethod(application, "Close", "close_viewportgroup", xml);
	}

	public void EditPortGroup(String application, String prefix_dropdownvalue, String routePriority_dropdownvalue, String Edit_prefix_dropdownvalue, String Edit_routePriority_dropdownvalue, String voiceCPEdevicename, String edit_voiceCPEdevicename) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "disasterrecoverystatus_header", xml);
		compareText(application, "Port Group", "portgroup_header", "Port Group", xml);
		String AddedPort_RowID= viewPortGroupRow(application, prefix_dropdownvalue, routePriority_dropdownvalue, voiceCPEdevicename, edit_voiceCPEdevicename);
		WebElement AddedPort_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/addedport_checkbox").replace("value", AddedPort_RowID));
		Clickon(AddedPort_Checkbox);
		click_commonMethod(application, "Action Dropdown", "portgroup_Actiondropdown", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Edit", "edit", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		compareText(application, "Port Group", "addportgroup_header", "Port Group", xml);
		Thread.sleep(1000);
		editPrefix_dropdown(application, "Prefix", "prefix_dropdown", Edit_prefix_dropdownvalue, xml);
		addDropdownValues_commonMethod(application, "Route Priority", "routepriority_dropdown", Edit_routePriority_dropdownvalue, xml);

		//Ports available
		selectAndRemovePortsFromRightDropdown(application, "Ports Selected", "portsselected_list", voiceCPEdevicename, edit_voiceCPEdevicename, "portsavailable_removearrow");
		selectPortsAvailableFromLeftDropdown(application, "Ports Available", "portsavailable_list" , voiceCPEdevicename, edit_voiceCPEdevicename, "portsavailable_addarrow");
		verifySelectedValuesInsideRightDropdown(application, "Ports Selected", "portsselected_list");

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Port Group VCPE successfully updated.");

	}

	public void OverflowPortGroup(String application, String prefix_dropdownvalue, String Edit_prefix_dropdownvalue, String routePriority_dropdownvalue, String voiceCPEdevicename, String edit_voiceCPEdevicename) throws InterruptedException, DocumentException {

		String overflowPrefixValue= null;
		ScrolltoElement(application, "disasterrecoverystatus_header", xml);
		compareText(application, "Port Group", "portgroup_header", "Port Group", xml);
		Thread.sleep(1000);
		String AddedPort_RowID= ExistingPortGroupRow(application, prefix_dropdownvalue, Edit_prefix_dropdownvalue, routePriority_dropdownvalue, voiceCPEdevicename, edit_voiceCPEdevicename);
		WebElement AddedPort_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/addedport_checkbox").replace("value", AddedPort_RowID));
		Clickon(AddedPort_Checkbox);
		click_commonMethod(application, "Action Dropdown", "portgroup_Actiondropdown", xml);
		click_commonMethod(application, "Add Overflow PortGroup", "addoverflowportgroup", xml);
		Thread.sleep(2000);

		String readonly_overflowprefix= getwebelement(xml.getlocator("//locators/" + application + "/prefix_overflowport")).getAttribute("readonly");
		overflowPrefixValue= getwebelement(xml.getlocator("//locators/" + application + "/prefix_overflowport")).getAttribute("value");
		if(readonly_overflowprefix==null) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Prefix value is displayed as: "+overflowPrefixValue);
		}
		if(getwebelement(xml.getlocator("//locators/" + application + "/routeprioritydisabled_overflowport")).getAttribute("disabled")==null) {
			String RoutePriorityValue= getwebelement(xml.getlocator("//locators/" + application + "/routepriorityvalue_overflowport")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Route Priority value is displayed as: "+RoutePriorityValue);
		}

		//Ports available
		selectPortsAvailableFromLeftDropdown(application, "Ports Available", "overflowportsavailable_list" , voiceCPEdevicename, edit_voiceCPEdevicename, "portsavailable_addarrow");
		verifySelectedValuesInsideRightDropdown(application, "Ports Selected", "overflow_portsselected_list");

		click_commonMethod(application, "OK", "okbutton", xml);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Port Group OverFlow successfully created.");
		
		//delete overflow portgroup
		driver.navigate().refresh();
		Thread.sleep(1000);
		ScrolltoElement(application, "disasterrecoverystatus_header", xml);
		Thread.sleep(1000);
		WebElement overflowcheckbox= getwebelement(xml.getlocator("//locators/" + application + "/overflow_checkbox").replace("value", overflowPrefixValue));
		Clickon(overflowcheckbox);
		Thread.sleep(1000);
		click_commonMethod(application, "Action Dropdown", "portgroup_Actiondropdown", xml);
		click_commonMethod(application, "Delete Overflow Portgroup", "delete_overflowportgroup", xml);
		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			click_commonMethod(application, "Delete", "delete_alertDelete", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Overflow PortGroup successfully deleted.");
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
	}

	public void selectPortsAvailableFromLeftDropdown(String application, String labelname, String xpath, String voiceCPEdevicename, String edit_voiceCPEdevicename, String xpathForAddButton) {

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
				Set<String> set= new HashSet<String>(ls);
				ls=new ArrayList<String>(set);
				ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
				System.out.println("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				Thread.sleep(5000);
				for(int j=0; j<ls.size() ; j++) {
					System.out.println("ls value "+ ls.get(j));
					if((ls.get(j)).contains(voiceCPEdevicename) || (ls.get(j)).contains(edit_voiceCPEdevicename))
					{
						elements.get(j).click();
						ExtentTestManager.getTest().log(LogStatus.PASS, elements.get(j) + " got selected" );
						Thread.sleep(1000);
						click_commonMethod(application, "Add", xpathForAddButton , xml);
						Thread.sleep(5000);
					}

				}

			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");
				System.out.println("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			System.out.println( "No values displaying under "+labelname + " available dropdown");
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
				System.out.println("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				for(int i=0; i<selectValue.length; i++)
				{
					Thread.sleep(5000);
					for(int j=0; j<ls.size() ; j++) {
						System.out.println("ls value "+ ls.get(j));
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

				System.out.println("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			System.out.println( "No values displaying under "+labelname + " available dropdown");
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
				System.out.println("list of values dipslaying inside "+labelname+" dropdown is: "+ls);
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");

				System.out.println("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			System.out.println( "No values displaying under "+labelname + " available dropdown");
		}
	}

	public void selectAndRemovePortsFromRightDropdown(String application, String labelname, String xpath, String voiceCPEdevicename, String edit_voiceCPEdevicename, String xpathForRemoveButton) {

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
				Set<String> set= new HashSet<String>(ls);
				ls= new ArrayList<String>(set);
				ExtentTestManager.getTest().log(LogStatus.PASS, "list of values displaying inside "+labelname+" available dropdown is: "+ls);
				System.out.println("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				Thread.sleep(2000);
				for(int j=0; j<ls.size() ; j++) {
					System.out.println("ls value "+ ls.get(j));
					if((ls.get(j)).contains(voiceCPEdevicename) || (ls.get(j)).contains(edit_voiceCPEdevicename))
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

			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");

				System.out.println("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			System.out.println( "No values displaying under "+labelname + " available dropdown");
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
				System.out.println("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				for(int i=0; i<selectValue.length; i++)
				{
					Thread.sleep(2000);
					for(int j=0; j<ls.size() ; j++) {
						System.out.println("ls value "+ ls.get(j));
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

				System.out.println("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			System.out.println( "No values displaying under "+labelname + " available dropdown");
		}
	}

	public void AddDDIRange(String application, String lac_value, String mainnumber_Value, String rangestart_Value, String rangeend_Value, String extensiondigits_value, String incomingrouting_checkbox, String prefix_dropdownvalue, String edit_prefix_dropdownvalue) throws InterruptedException, DocumentException, IOException {

		String expectedTitleName= "Add DDI Range";
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "portgroup_header", xml);
		Thread.sleep(1000);
		compareText(application, "DDI Range Header", "ddirange_panelheader", "DDI Range", xml);
		click_commonMethod(application, "Add DDI Range", "addDDIRange_link", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		String mainWindow=driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		Iterator<String> itr = allwindows.iterator();
		while(itr.hasNext())
		{
			String childWindow = itr.next();
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);

				String actualTitlename=driver.switchTo().window(childWindow).getTitle();
				if(expectedTitleName.equals(actualTitlename)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Page title is displaying as "+ actualTitlename +" as expected");
					Log.info("Page title is displaying as "+ actualTitlename +" as expected");

				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Page title is mismatching. Expected title is: "+expectedTitleName +" ."
							+ "But actual title displaying is: "+ actualTitlename);
					Log.info("Page title is mismatching. Expected title is: "+expectedTitleName +" ."
							+ "But actual title displaying is: "+ actualTitlename);
				}

				compareText(application, "Add DDI Range header", "addDDIRangepage_header", "DDI Range", xml);
				ScrolltoElement(application, "addDDIRangepage_header", xml);
				click_commonMethod(application, "ADD", "addDDI_addbutton", xml);
				waitforPagetobeenable();
				ScrolltoElement(application, "addDDI_addbutton", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Remove", "addDDI_removebutton", xml);
				Thread.sleep(1000);
				waitforPagetobeenable();
				String CountrycodeValue= getwebelement(xml.getlocator("//locators/" + application + "/addDDI_countrycode")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Country code is displayed as: "+CountrycodeValue);
				addtextFields_commonMethod(application, "LAC", "addDDI_lactextfield", lac_value, xml);
				addtextFields_commonMethod(application, "Main Number", "addDDI_mainnumber_textfield", mainnumber_Value, xml);
				addtextFields_commonMethod(application, "Range Start", "addDDI_rangestart", rangestart_Value, xml);
				addtextFields_commonMethod(application, "Range End", "addDDI_rangeend", rangeend_Value, xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Add Arrow", "addDDI_addArrow", xml);
				addtextFields_commonMethod(application, "Extension digits", "addDDI_extensiondigits_textfield", extensiondigits_value, xml);
				addCheckbox_commonMethod(application, "addDDI_incomingrouting_checkbox", "Activate Incoming Routing", incomingrouting_checkbox, "Yes", xml);
				if(edit_prefix_dropdownvalue.equalsIgnoreCase("null")) {
					selectValueInsideDropdown(application, "addDDI_portmappingprefix_dropdown", "Port Mapping Prefix", prefix_dropdownvalue, xml);
				}
				else
				{
					selectValueInsideDropdown(application, "addDDI_portmappingprefix_dropdown", "Port Mapping Prefix", edit_prefix_dropdownvalue, xml);
				}		

				ScrolltoElement(application, "okbutton", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "OK", "okbutton", xml);
				waitforPagetobeenable();

			}
		}

		driver.switchTo().window(mainWindow);

		Thread.sleep(3000);
		verifysuccessmessage(application, "DDI Range successfully created.");
		Thread.sleep(2000);
		driver.navigate().refresh();

		//Verify Added DDI Range
		ScrolltoElement(application, "portgroup_header", xml);
		Thread.sleep(1000);

		//verify table column headers
		compareText(application, "Country Code", "countrycode_columnheader", "Country Code", xml);
		compareText(application, "LAC", "lac_columnheader", "LAC", xml);
		compareText(application, "Main Number/Range Start-End", "mainrange_columnheader", "Main Number/Range Start-End", xml);
		compareText(application, "Extension digits", "extensiondigits_columnheader", "Extension digits", xml);
		compareText(application, "Port Group", "portgroup_columnheader", "Port Group", xml);
		compareText(application, "Emerg Area", "emergencyarea_columnheader", "Emerg Area", xml);
		compareText(application, "Incoming Routing", "incomingrouting_columnheader", "Incoming Routing", xml);
		compareText(application, "Action", "action_columnheader", "Action", xml);

		//verify table values
		GetText(application, "Country Code", "countrycode_value");
		compareText(application, "LAC", "lac_value", lac_value, xml);
		GetText(application, "Main Number/Range Start-End", "mainrange_value");
		compareText(application, "Extension digits", "extensiondigits_value", extensiondigits_value, xml);
		if(edit_prefix_dropdownvalue.equalsIgnoreCase("null"))
		{
			compareText(application, "Port Group", "portgroup_value", prefix_dropdownvalue, xml);
		}
		else
		{
			compareText(application, "Port Group", "portgroup_value", edit_prefix_dropdownvalue, xml);
		}
		GetText(application, "Emerg Area", "emergencyarea_value");
		if(incomingrouting_checkbox.equalsIgnoreCase("Yes") || incomingrouting_checkbox.equalsIgnoreCase("Null"))
		{
			String IncomingRouting= getwebelement(xml.getlocator("//locators/" + application + "/incomingrouting_checkbox")).getAttribute("checked");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Incoming routing checkbox is checked as expected");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Incoming routing checkbox is not checked");
		}

		List<WebElement> DDIRangeLinks= getwebelements(xml.getlocator("//locators/" + application + "/ddi_links"));
		int DDIRangeLinksCount= DDIRangeLinks.size();
		for(int i=0;i<DDIRangeLinksCount;i++)
		{
			String Link= DDIRangeLinks.get(i).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, ""+Link+" link is displaying under reseller panel");
			System.out.println("Reseller link:"+ Link + " is displaying");
			Log.info("Reseller link:"+ Link + " is displaying");
			Thread.sleep(2000);
		}
	}

	public void viewDDIRange(String application, String lac_value, String mainnumber_Value, String rangestart_Value, String rangeend_Value, String extensiondigits_value, String psxconfig_dropdownvalue) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "portgroup_header", xml);
		Thread.sleep(1000);
		compareText(application, "DDI Range Header", "ddirange_panelheader", "DDI Range", xml);
		click_commonMethod(application, "View", "ddi_viewlink", xml);
		Thread.sleep(3000);
		waitforPagetobeenable();
		ScrolltoElement(application, "ddirange_header", xml);
		compareText(application, "DDI Range Header", "ddirange_header", "DDI Range", xml);

		GetText(application, "Country Code", "viewpage_countrycode");
		compareText(application, "LAC", "viewpage_lac", lac_value, xml);
		compareText(application, "Main Number", "viewpage_mainnumber", "Main Number", xml);
		compareText(application, "Range Start", "viewpage_rangestart", "Range Start", xml);
		compareText(application, "Range End", "viewpage_rangeend", "Range End", xml);
		compareText(application, "Main Number Value", "viewpage_mainnumber_Value", mainnumber_Value, xml);
		compareText(application, "Range Start Value", "viewpage_rangestart_value", rangestart_Value, xml);
		compareText(application, "Range End Value", "viewpage_rangeend_value", rangeend_Value, xml);
		compareText(application, "Extension Digits", "viewpage_extensiondigits", extensiondigits_value, xml);
		GetText(application, "Emerg Area", "viewpage_emergArea");
		ScrolltoElement(application, "viewpage_backbutton", xml);
		GetText(application, "Incoming Routing", "viewpage_incomingrouting");
		//GetText(application, "In GEO", "viewpage_InGEO");
		GetText(application, "Port Mapping Prefix", "viewpage_portmapping");
		compareText(application, "Configuration Header", "viewddipage_configheader", "Configuration", xml);
		addDropdownValues_commonMethod(application, "PSX Configuration", "viewddipage-psxconfig_dropdown", psxconfig_dropdownvalue, xml);
		click_commonMethod(application, "Execute", "viewddipage_executebutton", xml);
		Thread.sleep(2000);
		verifysuccessmessage(application, "PSX sync started successfully. Please check the sync status of this Trunk Group. here ");
		ScrolltoElement(application, "viewpage_backbutton", xml);
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(3000);
		waitforPagetobeenable();
	}

	public void editDDIRange(String application, String edit_lac_value, String mainnumber_Value, String edit_mainnumber_Value, String edit_rangestart_Value, String edit_rangeend_Value, String edit_extensiondigits_value, String edit_incomingrouting_checkbox, String prefix_dropdownvalue, String edit_prefix_dropdownvalue) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "portgroup_header", xml);
		Thread.sleep(1000);
		compareText(application, "DDI Range Header", "ddirange_panelheader", "DDI Range", xml);
		click_commonMethod(application, "Edit", "ddi_editlink", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		ScrolltoElement(application, "ddirange_header", xml);
		compareText(application, "DDI Range Header", "ddirange_header", "DDI Range", xml);
		String CountrycodeValue= getwebelement(xml.getlocator("//locators/" + application + "/addDDI_countrycode")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Country code is displayed as: "+CountrycodeValue);
		edittextFields_commonMethod(application, "LAC", "addDDI_lactextfield", edit_lac_value, xml);
		selectAndRemoveDDIPhoneNumbersFromRightDropdown(application, "Phone Numbers", mainnumber_Value , "editDDI_selectedphonenumbers", "editDDI_phonenumber_removearrow");
		edittextFields_commonMethod(application, "Main Number", "addDDI_mainnumber_textfield", edit_mainnumber_Value, xml);
		edittextFields_commonMethod(application, "Range Start", "addDDI_rangestart", edit_rangestart_Value, xml);
		edittextFields_commonMethod(application, "Range End", "addDDI_rangeend", edit_rangeend_Value, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Add Arrow", "addDDI_addArrow", xml);
		ScrolltoElement(application, "okbutton", xml);
		edittextFields_commonMethod(application, "Extension digits", "addDDI_extensiondigits_textfield", edit_extensiondigits_value, xml);
		editcheckbox_commonMethod(application, edit_incomingrouting_checkbox, "addDDI_incomingrouting_checkbox", "Activate Incoming Routing", xml);

		if(edit_prefix_dropdownvalue.equalsIgnoreCase("null")) {
			selectValueInsideDropdown(application, "addDDI_portmappingprefix_dropdown", "Port Mapping Prefix", prefix_dropdownvalue, xml);
		}
		else
		{
			selectValueInsideDropdown(application, "addDDI_portmappingprefix_dropdown", "Port Mapping Prefix", edit_prefix_dropdownvalue, xml);
		}
		ScrolltoElement(application, "okbutton", xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "DDI Range successfully updated.");
		driver.navigate().refresh();
	}

	public void selectAndRemoveDDIPhoneNumbersFromRightDropdown(String application, String labelname, String mainNumber, String xpath, String xpathForRemoveButton) {

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
				System.out.println("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				Thread.sleep(2000);
				for(int j=0; j<ls.size() ; j++) {
					System.out.println("ls value "+ ls.get(j));
					if((ls.get(j)).contains(mainNumber))
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

			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");

				System.out.println("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			System.out.println( "No values displaying under "+labelname + " available dropdown");
		}
	}

	public void VerifyVoiceResiliency(String application, String backupnumber_checkbox, String Obackupnumber_value, String billingnumber_value) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "ddirange_panelheader", xml);
		Thread.sleep(2000);
		compareText(application, "Voice Resiliency header", "voiceresiliency_header", "Voice Resiliency", xml);
		GetText(application, "Backup Number", "backupnumber_value");
		GetText(application, "OBackup Number", "obackupnumber_value");
		GetText(application, "Billing Number", "billingnumber_value");
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "voiceresiliency_actiondropdown", xml);
		click_commonMethod(application, "Edit", "voiceresiliency_Edit", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		WebElement VoiceResiliencyPopup= getwebelement(xml.getlocator("//locators/" + application + "/voiceresiliency_popup"));
		if(VoiceResiliencyPopup.isDisplayed())
		{
			editcheckbox_commonMethod(application, backupnumber_checkbox, "backupnumber_checkbox", "Backup Number", xml);
			if(backupnumber_checkbox.equalsIgnoreCase("Yes"))
			{
				addtextFields_commonMethod(application, "OBackup Number", "obackupnumber_textfield", Obackupnumber_value, xml);
				addtextFields_commonMethod(application, "Billing Number", "billingnumber_textield", billingnumber_value, xml);
			}
			click_commonMethod(application, "SAVE", "savebutton_voiceresiliency", xml);
			Thread.sleep(3000);
			waitforPagetobeenable();
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit link is not working under Voice Resiliency panel");
		}

		//ScrolltoElement(application, "ddirange_header", xml);
		//Thread.sleep(1000);
		GetText(application, "Backup Number", "backupnumber_value");
		GetText(application, "OBackup Number", "obackupnumber_value");
		GetText(application, "Billing Number", "billingnumber_value");
		Thread.sleep(1000);
	}

	public void VerifyPSXcommandExecution(String application, String PSXcommand_dropdownvalue) throws InterruptedException, DocumentException {

		String expectedTitleName="Execute Command on Device";

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "commandsexecution_header", xml);
		Thread.sleep(1000);
		compareText(application, "Commands Execution", "commandsexecution_header", "Commands Execution", xml);
		compareText(application, "PSX Command", "PSXcommand_label", "PSX Command", xml);
		addDropdownValues_commonMethod(application, "PSX Command", "PSXcommand_dropdown", PSXcommand_dropdownvalue, xml);
		click_commonMethod(application, "Execute", "PSXcommand_executebutton", xml);
		Thread.sleep(1000);
		String mainWindow=driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		Iterator<String> itr = allwindows.iterator();
		while(itr.hasNext())
		{
			String childWindow = itr.next();
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);

				String actualTitlename=driver.switchTo().window(childWindow).getTitle();
				if(expectedTitleName.equals(actualTitlename)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Page title is displaying as "+ actualTitlename +" as expected");
					Log.info("Page title is displaying as "+ actualTitlename +" as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Page title is mismatching. Expected title is: "+expectedTitleName +" ."
							+ "But actual title displaying is: "+ actualTitlename);
					Log.info("Page title is mismatching. Expected title is: "+expectedTitleName +" ."
							+ "But actual title displaying is: "+ actualTitlename);
				}

				Thread.sleep(1000);
				driver.close();
			}
		}

		driver.switchTo().window(mainWindow);
	}


	public void VerifyGSXcommandExecution(String application, String GSXcommand_dropdownvalue) throws InterruptedException, DocumentException {

		String expectedTitleName="Execute Command on Device";
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "commandsexecution_header", xml);
		Thread.sleep(2000);
		compareText(application, "Commands Execution", "commandsexecution_header", "Commands Execution", xml);
		compareText(application, "GSX Command", "GSXcommand_label", "GSX Command", xml);
		Thread.sleep(1000);
		addDropdownValues_commonMethod(application, "GSX Command", "GSXcommand_dropdown", GSXcommand_dropdownvalue, xml);
		click_commonMethod(application, "Execute", "GSXcommand_executebutton", xml);
		Thread.sleep(1000);
		waitforPagetobeenable();
		String mainWindow=driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		Iterator<String> itr = allwindows.iterator();
		while(itr.hasNext())
		{
			String childWindow = itr.next();
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);

				String actualTitlename=driver.switchTo().window(childWindow).getTitle();
				if(expectedTitleName.equals(actualTitlename)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Page title is displaying as "+ actualTitlename +" as expected");
					Log.info("Page title is displaying as "+ actualTitlename +" as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Page title is mismatching. Expected title is: "+expectedTitleName +" ."
							+ "But actual title displaying is: "+ actualTitlename);
					Log.info("Page title is mismatching. Expected title is: "+expectedTitleName +" ."
							+ "But actual title displaying is: "+ actualTitlename);
				}

				Thread.sleep(1000);
				driver.close();
			}
		}

		driver.switchTo().window(mainWindow);
	}

	public void viewTrunk_PSX_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);
		try {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'PSX Execute Configuration' Functionality");

			ScrolltoElement(application, "configurationpanel_header_executecommand", xml);
			Thread.sleep(2000);

			addDropdownValues_commonMethod(application, "PSX Configuration", "PSXconfigurationDropdown_viewtrunk", expectedConfiguration , xml);
			click_commonMethod(application, "Execute", "viewTrunk_PSX_executeButton" , xml);
			waitforPagetobeenable();

			Alert alert = driver.switchTo().alert();

			// Capturing alert message.    
			String alertMessage= driver.switchTo().alert().getText();
			if(alertMessage.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
				Log.info("No Message displays"); 
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
				Log.info("text message for alert displays as: "+alertMessage);
			}

			try {  
				alert.accept();
				Thread.sleep(2000);

			}catch(Exception e) {
				e.printStackTrace();
			}

			if(expectedConfiguration.equalsIgnoreCase("Delete Trunk Group")) {
				verifysuccessmessage(application, "PSX sync started successfully.");
			}
			else if(expectedConfiguration.equalsIgnoreCase("Add Destination IP Address")) {
				String alertMessage1=alert.getText();
				if(alertMessage1.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on OK, No message displays");
					Log.info("No Message displays"); 
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on OK button, Alert message displays as: "+alertMessage);
					Log.info("text message for alert displays as: "+alertMessage);

					alert.dismiss();
					//		       alert.accept();

				}

			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "PSX Configuration execution is not successful");
		}
	}


	public void viewTrunk_SBC_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		try {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'SBC_Execute Configuration' Functionality");

			ScrolltoElement(application, "configurationpanel_header_executecommand", xml);
			Thread.sleep(2000);

			addDropdownValues_commonMethod(application, "SBC Configuration", "SBCconfigurationDropdown_viewtrunk", expectedConfiguration, xml);

			click_commonMethod(application, "Execute", "viewTrunk_SBC_executeButton" , xml);
			waitforPagetobeenable();
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
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "SBC Configuration execution is not successful");
		}
	}


	public void viewTrunk_GSX_executeConfiguration(String application, String expectedConfiguration) throws InterruptedException, DocumentException, IOException {

		try {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'GSX_Execute Configuration' Functionality");

			ScrolltoElement(application, "configurationpanel_header_executecommand", xml);
			Thread.sleep(2000);

			addDropdownValues_commonMethod(application, "GSX Configuration", "GSXconfigurationDropdown_viewtrunk", expectedConfiguration, xml);

			click_commonMethod(application, "Generate Configuration", "viewTrunk_GSX_generateConfigurationButton" , xml);
			waitforPagetobeenable();
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
					//               Write here  whatever you want to do and perform
					Log.info("came inside child window");

					//
					ScrolltoElement(application, "GSX_config_executeButton", xml);
					Thread.sleep(2000);
					click_commonMethod(application, "Execute", "GSX_config_executeButton", xml);
					waitforPagetobeenable();
				}
			}

			Thread.sleep(3000);
			driver.switchTo().window(mainWindow);
			Thread.sleep(1000);

			scrollToTop();
			String gsxSuccessMesage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/GSXconfig_sucessMessage")));
			if(gsxSuccessMesage.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "NO message displays after clicking on 'Execute' button");
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Execute' button, success Message displays as: "+gsxSuccessMesage);
			}

		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "PSX Configuration execution is not successful");
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

		String expectedTitleName= "Add SBC Manual Configuration";
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "SBCmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

		try {	
			boolean SBCHeader=getwebelement(xml.getlocator("//locators/" + application + "/SBCmanualConfig_PanelHeader")).isDisplayed();
			if(SBCHeader) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'SBC Manully Executed Configuration' panel is displaying");
				System.out.println("'SBC Manully Executed Configuration' panel is displaying");


				click_commonMethod(application, "Action", "SBCManualConfig_actionDropdown", xml);
				click_commonMethod(application, "Add link", "SBC_addLink", xml);

				String mainWindow=driver.getWindowHandle();
				Set<String> allwindows = driver.getWindowHandles();
				Iterator<String> itr = allwindows.iterator();
				while(itr.hasNext())
				{
					String childWindow = itr.next();
					if(!mainWindow.equals(childWindow)){
						driver.switchTo().window(childWindow);

						String actualTitlename=driver.switchTo().window(childWindow).getTitle();
						if(expectedTitleName.equals(actualTitlename)) {
							ExtentTestManager.getTest().log(LogStatus.PASS, "Page title is displaying as "+ actualTitlename +" as expected");
							Log.info("Page title is displaying as "+ actualTitlename +" as expected");

						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Page title is mismatching. Expected title is: "+expectedTitleName +" ."
									+ "But actual title displaying is: "+ actualTitlename);
							Log.info("Page title is mismatching. Expected title is: "+expectedTitleName +" ."
									+ "But actual title displaying is: "+ actualTitlename);
						}

						//manual configuration on SBC page
						addtextFields_commonMethod(application, "SBC Manual Configuration", "manualConfiguration_textArea", manualConfigurationValue, xml);
						click_commonMethod(application, "Save", "save_manualConfiguration", xml);

						scrollToTop();
						Thread.sleep(1000);
						driver.close();
					}
				}

				driver.switchTo().window(mainWindow);

				verifysuccessmessage(application, "Manual Configuration added Successfully");   //verify success message

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'SBC Manually Executed Configuration' panel is not displaying");
				System.out.println("'SBC Manually Executed Configuration' panel is not displaying");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'SBC Manully Executed Configuration' panel is not displaying");
			System.out.println("'SBC Manully Executed Configuration' panel is not displaying");
		}
	}


	public void verifySBCfileAdded(String application) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "SBCmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

		WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/SBC_selectCreatedValue"));
		String SBCfilenameCreated=Gettext(filename);

		if(SBCfilenameCreated.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "SBC Manually Executed configuration file name is not displaying");
			System.out.println("SBC Manually Executed configuration file name is not displaying");
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

	public void editSBC_manualExecutionConfig(String application, String editGSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "SBCmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

		click_commonMethod(application, "SBCcreated", "SBC_selectCreatedValue", xml);

		click_commonMethod(application, "Action", "SBCManualConfig_actionDropdown", xml);  //click acton dropdown
		click_commonMethod(application, "Edit link", "SBC_editLink", xml);   //click on edit link

		Thread.sleep(6000);

		String mainWindow=driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		Iterator<String> itr = allwindows.iterator();
		while(itr.hasNext())
		{
			String childWindow = itr.next();
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);
				System.out.println(driver.switchTo().window(childWindow).getTitle());

				//                    driver.manage().window().maximize();
				Thread.sleep(1000);

				//                   Write here  whatever you want to do and perform
				System.out.println("came inside child window");

				edittextFields_commonMethod(application, "GSX manual Configuration", "GSX_editPage_teaxtArea", editGSXmanualConfigvalur, xml);

				ScrolltoElement(application, "GSX_editPage_SaveButton", xml);
				Thread.sleep(2000);
				click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
				waitforPagetobeenable();
			}
		}

		driver.switchTo().window(mainWindow);
		Thread.sleep(1000);
		verifysuccessmessage(application, "Manual Configuration updated Successfully");


	}



	public void deleteSBC_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "SBCmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

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
	 *  perform Manual PSX execution
	 * @param application
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException 
	 */
	public void addPSX_manualExecutionConfig(String application, String PSXmanualConfigurationtext) throws InterruptedException, DocumentException, IOException {

		String expectedTitleName= "Add PSX Manual Configuration";
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "PSXmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

		boolean PSXHeader=getwebelement(xml.getlocator("//locators/" + application + "/PSXmanualConfig_PanelHeader")).isDisplayed();
		if(PSXHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'PSX Manully Executed Configuration' panel is displaying");
			System.out.println("'PSX Manully Executed Configuration' panel is displaying");


			click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);
			Thread.sleep(5000);
			click_commonMethod(application, "Add link", "PSX_addLink", xml);

			//TODO compare Header name
			Thread.sleep(2000);

			String mainWindow=driver.getWindowHandle();
			Set<String> allwindows = driver.getWindowHandles();
			Iterator<String> itr = allwindows.iterator();
			while(itr.hasNext())
			{
				String childWindow = itr.next();
				if(!mainWindow.equals(childWindow)){
					driver.switchTo().window(childWindow);

					String actualTitlename=driver.switchTo().window(childWindow).getTitle();
					if(expectedTitleName.equals(actualTitlename)) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Page title is displaying as "+ actualTitlename +" as expected");
						Log.info("Page title is displaying as "+ actualTitlename +" as expected");

					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Page title is mismatching. Expected title is: "+expectedTitleName +" ."
								+ "But actual title displaying is: "+ actualTitlename);
						Log.info("Page title is mismatching. Expected title is: "+expectedTitleName +" ."
								+ "But actual title displaying is: "+ actualTitlename);
					}

					//Text Area	
					addtextFields_commonMethod(application, "Manual Configuration", "manualConfiguration_textArea", PSXmanualConfigurationtext, xml);
					Thread.sleep(1000);

					click_commonMethod(application, "Save", "save_manualConfiguration", xml);   //click on Save buttton
					Thread.sleep(1000);
					driver.close();
				}
			}

			driver.switchTo().window(mainWindow);
			Thread.sleep(1000);
			verifysuccessmessage(application, "Manual Configuration added Successfully");  //verify success Message

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'PSX Manully Executed Configuration' panel is not displaying");
			System.out.println("'PSX Manully Executed Configuration' panel is not displaying");
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

		String expectedTitleName= "Add GSX Manual Configuration";
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "GSXmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

		boolean GSXHeader=getwebelement(xml.getlocator("//locators/" + application + "/GSXmanualConfig_PanelHeader")).isDisplayed();
		if(GSXHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'GSX Manually Executed Configuration' panel is displaying");
			System.out.println("'GSX Manually Executed Configuration' panel is displaying");
			Thread.sleep(1000);
			click_commonMethod(application, "Action", "GSXManualConfig_actionDropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Add link", "GSX_addLink", xml);

			//TODO compare Header name

			String mainWindow=driver.getWindowHandle();
			Set<String> allwindows = driver.getWindowHandles();
			Iterator<String> itr = allwindows.iterator();
			while(itr.hasNext())
			{
				String childWindow = itr.next();
				if(!mainWindow.equals(childWindow)){
					driver.switchTo().window(childWindow);

					String actualTitlename=driver.switchTo().window(childWindow).getTitle();
					if(expectedTitleName.equals(actualTitlename)) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Page title is displaying as "+ actualTitlename +" as expected");
						Log.info("Page title is displaying as "+ actualTitlename +" as expected");

					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Page title is mismatching. Expected title is: "+expectedTitleName +" ."
								+ "But actual title displaying is: "+ actualTitlename);
						Log.info("Page title is mismatching. Expected title is: "+expectedTitleName +" ."
								+ "But actual title displaying is: "+ actualTitlename);
					}

					//Text Area	
					addtextFields_commonMethod(application, "Manul Configuration", "manualConfiguration_textArea", GSXmanualConfiguratio, xml);
					ScrolltoElement(application, "save_manualConfiguration", xml);
					Thread.sleep(1000);
					click_commonMethod(application, "Save", "save_manualConfiguration", xml);   //click on Save buttton
					Thread.sleep(1000);
					waitforPagetobeenable();
					driver.close();
				}
			}

			driver.switchTo().window(mainWindow);

			verifysuccessmessage(application, "Manual Configuration added Successfully");  //verify success Message

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'GSX Manully Executed Configuration' panel is not displaying");
			System.out.println("'GSX Manully Executed Configuration' panel is not displaying");
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

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "GSXmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

		WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/PSX_selectCreatedValue"));
		String PSXfilenameCreated=Gettext(filename);

		if(PSXfilenameCreated.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "PSX Manually Executed configuration file name is not displaying");
			System.out.println("PSX Manually Executed configuration file name is not displaying");
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

			ExtentTestManager.getTest().log(LogStatus.INFO, "column names display as: "+ columnName[1] + "  ,  " + columnName[2]);  //printing column Names


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


		ScrolltoElement(application, "GSXmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

		WebElement filename=getwebelement(xml.getlocator("//locators/" + application + "/GSX_selectCreatedValue"));
		String GSXfilenameCreated=Gettext(filename);

		if(GSXfilenameCreated.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "GSX Manually Executed configuration file name is not displaying");
			System.out.println("GSX Manually Executed configuration file name is not displaying");
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

	public void editPSX_manualExecutionConfig(String application, String editPSXmanualConfigvalue) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "PSXmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
		Thread.sleep(1000);
		click_commonMethod(application, "PSXcreated", "PSX_selectCreatedValue", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);  //click acton dropdown
		click_commonMethod(application, "Edit link", "PSX_editLink", xml);   //click on edit link

		//	System.out.println("date displaying as : "+getCurrentDate());
		//	System.out.println("username displays as: "+Getkeyvalue("APT_login_1_Username"));

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
				System.out.println(driver.switchTo().window(childWindow).getTitle());

				//                driver.manage().window().maximize();
				Thread.sleep(1000);

				//               Write here  whatever you want to do and perform
				System.out.println("came inside child window");

				GetText(application, "File Name", "editPSX_filenamevalue");
				GetText(application, "Date", "editPSX_datevalue");
				GetText(application, "User", "editPSX_uservalue");
				GetText(application, "Trunk Name", "editPSX_trunkvalue");
				edittextFields_commonMethod(application, "PSX Manual Configuration", "GSX_editPage_teaxtArea", editPSXmanualConfigvalue, xml);

				ScrolltoElement(application, "GSX_editPage_SaveButton", xml);
				Thread.sleep(1000);

				click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
				waitforPagetobeenable();
			}
		}

		driver.switchTo().window(mainWindow);
		Thread.sleep(1000);
		verifysuccessmessage(application, "Manual Configuration updated Successfully");

	}

	/**
	 * perform delete operation under PSX manually executed configuration panel	
	 * @param application
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void deletePSX_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "PSXmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

		click_commonMethod(application, "PSXcreated", "PSX_selectCreatedValue", xml);

		click_commonMethod(application, "Action", "PSXManualConfig_actionDropdown", xml);  //click action dropdown
		click_commonMethod(application, "Delete link", "PSX_deleteLink", xml);   //click on edit link

		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
			Thread.sleep(1000);
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

	public void editGSX_manualExecutionConfig(String application, String editGSXmanualConfigvalur) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "GSXmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
		click_commonMethod(application, "GSXcreated", "GSX_selectCreatedValue", xml);

		click_commonMethod(application, "Action", "GSXManualConfig_actionDropdown", xml);  //click acton dropdown
		click_commonMethod(application, "Edit link", "GSX_editLink", xml);   //click on edit link

		//		System.out.println("date displaying as : "+getCurrentDate());
		//		System.out.println("username displays as: "+Getkeyvalue("APT_login_1_Username"));

		Thread.sleep(6000);

		String mainWindow=driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		Iterator<String> itr = allwindows.iterator();
		while(itr.hasNext())
		{
			String childWindow = itr.next();
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);
				System.out.println(driver.switchTo().window(childWindow).getTitle());

				//                    driver.manage().window().maximize();
				Thread.sleep(1000);

				//                   Write here  whatever you want to do and perform
				System.out.println("came inside child window");

				edittextFields_commonMethod(application, "GSX manual Configuration", "GSX_editPage_teaxtArea", editGSXmanualConfigvalur, xml);

				ScrolltoElement(application, "GSX_editPage_SaveButton", xml);
				Thread.sleep(2000);

				click_commonMethod(application, "Save", "GSX_editPage_SaveButton", xml);
				waitforPagetobeenable();
			}
		}

		driver.switchTo().window(mainWindow);
		Thread.sleep(1000);
		verifysuccessmessage(application, "Manual Configuration updated Successfully");


	}

	/**
	 * perform delete operation under GSX manually executed configuration panel	
	 * @param application
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void deleteGSX_manualExecutionConfig(String application) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "GSXmanualConfig_PanelHeader", xml);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

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

	public String fetchTrunkName(String application) throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		Thread.sleep(1000);

		WebElement trunkGroupNameelement = getwebelement(xml.getlocator("//locators/" + application + "/fetchTrunkGroupName"));
		String trunkGroupName=Gettext(trunkGroupNameelement);

		String[] trunkNames=trunkGroupName.split("-");
		System.out.println("trunk name: "+ trunkNames[1]);

		return trunkNames[1];

	}

	public void verifyConfiguration(String application, String sid, String voiceCPEdevicename, String edit_voiceCPEdevicename, String Configuration_dropdownValue) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "configurationpanel_header", xml);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
		compareText(application, "Configuration", "configurationpanel_header", "Configuration", xml);
		Thread.sleep(2000);
		VoiceCPEDevices_configDropdown(application, "Voice CPE Devices", "voiceCPEDevices_dropdown", voiceCPEdevicename, edit_voiceCPEdevicename, xml);
		addDropdownValues_commonMethod(application, "Configuration", "configuration_dropdownvalue", Configuration_dropdownValue, xml);
		click_commonMethod(application, "Generate Configuration", "generateconfig_link", xml);
		Thread.sleep(1000);
		GetText(application, "configuration Details", "config_textarea");
		click_commonMethod(application, "Save Configuration", "saveconfig_link", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Execute Configuration on Device", "executeconfigondevice_link", xml);
		Thread.sleep(1000);

	}
	
	public void VoiceCPEDevices_configDropdown(String application, String labelname, String xpath, String expectedValueToAdd1, String expectedValueToAdd2, XMLReader xml) throws InterruptedException, DocumentException {
		  boolean availability=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
			  System.out.println(labelname + " dropdown is displaying");
			  
				  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
				  Thread.sleep(3000);
				  
				  //verify list of values inside dropdown
				  List<WebElement> listofvalues = driver.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
				  int listofvaluesCount= listofvalues.size();
				  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
				  System.out.println( " List of values inside "+ labelname + " dropdown is:  ");
				  
					for (WebElement valuetypes : listofvalues) {
								Log.info("service sub types : " + valuetypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
								System.out.println(" " + valuetypes.getText());
					}
					
					for(int i=0;i<listofvaluesCount;i++) {
					String VoiceCPEdropdownList= driver.findElement(By.xpath("//div[@class='sc-bxivhb kqVrwh']")).getText();
					if(VoiceCPEdropdownList.equalsIgnoreCase(expectedValueToAdd1))
					{
					Thread.sleep(2000);
				SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd1);	
				Thread.sleep(1000);
					
				  Clickon(getwebelement("(//div[@class='sc-ifAKCX oLlzc'][contains(text(),'"+ expectedValueToAdd1 +"')])[1]"));
				  Thread.sleep(2000);
				  break;
					}
					else
					{
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd2);	
					Thread.sleep(1000);
						
					  Clickon(getwebelement("(//div[@class='sc-ifAKCX oLlzc'][contains(text(),'"+ expectedValueToAdd2 +"')])[1]"));
					  Thread.sleep(2000);
						}
					
					}
				  String actualValue=getwebelement("//label[text()='"+ labelname +"']/following-sibling::div//span").getText();
				  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
				  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
				  
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
	

	public void clickOnCPEdeviceLink(String application, String siteOrderName) throws Exception {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

		WebElement getTrunkRow=getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedTrunk_InViewServicePage").replace("value", Edit_primarytrunkGroupname));
		safeJavaScriptClick(getTrunkRow);


		//click on Action dropdown under Trunk Panel	
		WebElement trunkPanel_actionDropdown=getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel_ActionDropdown").replace("value", siteOrderName));
		safeJavaScriptClick(trunkPanel_actionDropdown);


		//Add CPE link
		click_commonMethod(application, "Add CPE device Link", "addCPEdeviceLink" , xml);

	}

//	public String DRusingTDMvalue(String application) throws InterruptedException, DocumentException {
//		
//		ScrolltoElement(application, "viewpage_codec", xml);
//		DRusingTDM_Value= GetText(application, "DR Using TDM", "viewpage_drusingtdm");
//		
//		return DRusingTDM_Value;
//	}
	
	public void clickOnViewTrunkLink(String application, String siteOrderName) throws Exception {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

		WebElement getTrunkRow=getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedTrunk_InViewServicePage").replace("value", Edit_primarytrunkGroupname));
		safeJavaScriptClick(getTrunkRow);

		//click on Action dropdown under Trunk Panel	
		WebElement trunkPanel_actionDropdown=getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel_ActionDropdown").replace("value", siteOrderName));
		safeJavaScriptClick(trunkPanel_actionDropdown);

		//view trunk link
		click_commonMethod(application, "View Trunk", "viewtrunk_link" , xml);
		Thread.sleep(5000);

	}

	public void clickOnViewCPEdeviceLink(String application, String siteOrderName) throws Exception {

		ScrolltoElement(application, "trunkPanel", xml);

		WebElement getTrunkRow=getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedTrunk_InViewServicePage").replace("value", Edit_primarytrunkGroupname));
		safeJavaScriptClick(getTrunkRow);


		//click on Action dropdown under Trunk Panel	
		WebElement trunkPanel_actionDropdown=getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel_ActionDropdown").replace("value", siteOrderName));
		safeJavaScriptClick(trunkPanel_actionDropdown);


		//View CPE link
		click_commonMethod(application, "View CPE device Link", "addCPEdeviceLink" , xml);

	}

	public void addCPEdevice(String application, String RouterId, String VendorModel, String ManagamentAddress, String Snmpro,
			String Snmprw, String SNMPv3ContaxtName, String SNMPv3ContextEngineId, String SNMPv3securityUsername, String SNMPv3AutoProto, String SNMPv3AuthPasswrd,
			String Country, String existingcity, 
			String existingcityvalue, String existingsite, 
			String existingsitevalue, String existingpremise, 
			String existingpremisevalue, String newcity,String cityname,String Citycode,
			String sitename, String sitecode,  String premisename,  String premisecode, 
			String newsite, String NewPremise) throws InterruptedException, DocumentException, IOException {

		Thread.sleep(2000);
		addtextFields_commonMethod(application, "Router ID" , "routerId"  , RouterId, xml);   //Router ID

		//addDropdownValues_commonMethod(application, "Vendor/Model" , "CPE_vendorModel" , VendorModel, xml);   //Vendor Model

		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/CPE_vendorModel")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Vendor/Model dropdown is displaying");
				System.out.println("Vendor/Model dropdown is displaying");

				if(VendorModel.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Vendor/Model dropdown");
					System.out.println(" No values selected under Vendor/Model dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

					ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside Vendor/Model dropdown is:  ");
					System.out.println( " List of values inside Vendor/Model dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), VendorModel);	
					Thread.sleep(2000);

					Clickon(getwebelement("(//span[contains(text(),'"+ VendorModel +"')])[1]"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//label[text()='Vendor/Model']/following-sibling::div//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Vendor/Model dropdown value selected as: "+ actualValue );
					System.out.println("Vendor/Model dropdown value selected as: "+ actualValue);

				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Vendor/Model is not displaying");
				System.out.println("Vendor/Model is not displaying");
			}
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Vendor/Model is not displaying");
			System.out.println("Vendor/Model is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under Vendor/Model dropdown");
			System.out.println(" NO value selected under Vendor/Model dropdown");
		}

		addtextFields_commonMethod(application, "Management Address", "CPE_manageAddress", ManagamentAddress, xml);   //Management Address

		addtextFields_commonMethod(application, "Snmpro", "CPE_snmpro" , Snmpro , xml);   //Snmpro

		addtextFields_commonMethod(application, "Snmprw" , "CPE_snmprw" , Snmprw, xml);   //Snmprw

		addtextFields_commonMethod(application, "Snmp V3 Context Name", "CPE_snmpV3ContextName" , SNMPv3ContaxtName, xml);   //Snmp V3 Context Name

		addtextFields_commonMethod(application, "Snmp V3 Context Engine ID" , "CPE_snmpV3ContextEngineID", SNMPv3ContextEngineId, xml);   //Snmp V3 Context Engine ID

		addtextFields_commonMethod(application, "Snmp V3 Security Username" , "CPE_snmpv3SecurityUserName", SNMPv3securityUsername, xml);   //Snmp V3 Security Username

		addDropdownValues_commonMethod(application, "Snmp V3 Auth Proto" , "CPE_snmpv3AuthProto" , SNMPv3AutoProto, xml);   ////Snmp V3 Auth Proto

		addtextFields_commonMethod(application, "Snmp V3 Auth Password", "CPE_snmpv3AuthPasswrd" , SNMPv3AuthPasswrd, xml);   //Snmp V3 Auth Password


		//select country
		ScrolltoElement(application, "trunk_okButton", xml);
		Thread.sleep(2000);

		//addDropdownValues_commonMethod(application, "Country", "countrydropdown", Country, xml);
		selectValueInsideDropdown(application, "countrydropdown", "Country", Country, xml);

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

			//addDropdownValues_commonMethod(application, "City", "citydropdown", existingcityvalue, xml);
			selectValueInsideDropdown(application, "citydropdown", "City", existingcityvalue, xml);

			//Existing Site
			if(existingsite.equalsIgnoreCase("yes") & newsite.equalsIgnoreCase("no")) {
				//addDropdownValues_commonMethod(application, "Site", "sitedropdown", existingsitevalue, xml);
				selectValueInsideDropdown(application, "sitedropdown", "Site", existingsitevalue, xml);

				//Existing Premise
				if(existingpremise.equalsIgnoreCase("yes") & NewPremise.equalsIgnoreCase("no")) {
					//addDropdownValues_commonMethod(application, "Premise", "premisedropdown", existingpremisevalue, xml);
					selectValueInsideDropdown(application, "premisedropdown", "Premise", existingpremisevalue, xml);
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

		ScrolltoElement(application, "trunk_okButton", xml);
		Thread.sleep(1000);

		clickOnBankPage();
		Thread.sleep(200);

		ScrolltoElement(application, "trunk_okButton", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "trunk_okButton" , xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
	}

	/**
	 *  Method is for clicking on "view" link under CPE device	
	 * @param application
	 * @param existingdevicename
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void CPEdevice_clickOnViewLink(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/existingvoicecpe_devicegrid")).isDisplayed())
		{
			List<WebElement> AddedCPEDevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addedCPEdevicename"));
			System.out.println(AddedCPEDevicesList);
			int AddedCPEDevicesCount= AddedCPEDevicesList.size();

			for(int i=0;i<AddedCPEDevicesCount;i++) {
				String AddedCPEDeviceNameText= AddedCPEDevicesList.get(i).getText();
				System.out.println(AddedCPEDeviceNameText);
				String AddedCPEDevice_SNo= AddedCPEDeviceNameText.substring(0, 1);

				WebElement AddedCPEDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdeviceLink").replace("value", AddedCPEDevice_SNo));
				Clickon(AddedCPEDevice_ViewLink);
			}
		}
	}

	public void viewCPEdevice(String application, String sid, String RouterId, String VendorModel, String ManagamentAddress, String Snmpro,
			String Snmprw, String SNMPv3ContextName, String SNMPv3ContextEngineId, String SNMPv3securityUsername, String SNMPv3AutoProto, String SNMPv3AuthPasswrd,
			String Country, String existingcity, 
			String existingcityvalue, String existingsite, 
			String existingsitevalue, String existingpremise, 
			String existingpremisevalue, String newcity,String cityname,String Citycode,
			String sitename, String sitecode,  String premisename,  String premisecode, 
			String newsite, String NewPremise) throws InterruptedException, DocumentException {

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
		GetText(application, "Country", "viewCPE_CountryValue");


		//City
		if((existingcity.equalsIgnoreCase("yes")) && (newcity.equalsIgnoreCase("NO"))) {

			//Existing City
			GetText(application, "City", "viewCPE_CityValue");

		}
		else if((existingcity.equalsIgnoreCase("NO")) && (newcity.equalsIgnoreCase("Yes"))) {

			//new City
			GetText(application, "City", "viewCPE_CityValue");

		}

		//Site
		if((existingsite.equalsIgnoreCase("yes")) && (newsite.equalsIgnoreCase("NO"))) {

			//Existing Site
			GetText(application, "Site", "viewCPE_SiteValue");
		}
		else if((existingsite.equalsIgnoreCase("No")) && (newsite.equalsIgnoreCase("Yes"))) {

			//New Site
			GetText(application, "Site", "viewCPE_SiteValue");

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

		Thread.sleep(2000);
		clickOnBreadCrumb(application, sid);
		Thread.sleep(2000);
	}


	public void CPEdevice_clickOnEditLink(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/existingvoicecpe_devicegrid")).isDisplayed())
		{
			List<WebElement> AddedCPEDevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addedCPEdevicename"));
			System.out.println(AddedCPEDevicesList);
			int AddedCPEDevicesCount= AddedCPEDevicesList.size();

			for(int i=0;i<AddedCPEDevicesCount;i++) {
				String AddedCPEDeviceNameText= AddedCPEDevicesList.get(i).getText();
				System.out.println(AddedCPEDeviceNameText);
				String AddedCPEDevice_SNo= AddedCPEDeviceNameText.substring(0, 1);

				WebElement AddedCPEDevice_EditLink= getwebelement(xml.getlocator("//locators/" + application + "/editCPEdeviceLink").replace("value", AddedCPEDevice_SNo));
				Clickon(AddedCPEDevice_EditLink);
			}
		}
	}


	public void editCPEdevice(String application, String sid, String editRouterId, String editVendorModel, String editManagamentAddress, String editSnmpro,
			String editSnmprw, String editSNMPv3ContaxtName, String editSNMPv3ContextEngineId, String editSNMPv3securityUsername, 
			String editSNMPv3AutoProto, String editSNMPv3AuthPasswrd,
			String editCountry, String editExistingCity, String editExistingCityValue, String editExistingSite, String editExistingSiteValue,
			String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, String editNewPremise, String editNewCityName,
			String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, DocumentException, IOException {

		Thread.sleep(2000);
		edittextFields_commonMethod(application, "Router ID" , "routerId"  , editRouterId, xml);   //Router ID

		//addDropdownValues_commonMethod(application, "Vendor/Model" , "CPE_vendorModel" , editVendorModel, xml);   //Vendor Model

		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/CPE_vendorModel")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Vendor/Model dropdown is displaying");
				System.out.println("Vendor/Model dropdown is displaying");

				if(editVendorModel.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Vendor/Model dropdown");
					System.out.println(" No values selected under Vendor/Model dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

					ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside Vendor/Model dropdown is:  ");
					System.out.println( " List of values inside Vendor/Model dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='Vendor/Model']]//input"), editVendorModel);	
					Thread.sleep(2000);

					Clickon(getwebelement("(//span[contains(text(),'"+ editVendorModel +"')])[1]"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//label[text()='Vendor/Model']/following-sibling::div//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Vendor/Model dropdown value selected as: "+ actualValue );
					System.out.println("Vendor/Model dropdown value selected as: "+ actualValue);

				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Vendor/Model is not displaying");
				System.out.println("Vendor/Model is not displaying");
			}
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Vendor/Model is not displaying");
			System.out.println("Vendor/Model is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under Vendor/Model dropdown");
			System.out.println(" NO value selected under Vendor/Model dropdown");
		}

		edittextFields_commonMethod(application, "Management Address", "CPE_manageAddress", editManagamentAddress, xml);   //Management Address

		edittextFields_commonMethod(application, "Snmpro", "CPE_snmpro" , editSnmpro , xml);   //Snmpro

		edittextFields_commonMethod(application, "Snmprw" , "CPE_snmprw" , editSnmprw, xml);   //Snmprw

		edittextFields_commonMethod(application, "Snmp V3 Context Name", "CPE_snmpV3ContextName" , editSNMPv3ContaxtName, xml);   //Snmp V3 Context Name

		edittextFields_commonMethod(application, "Snmp V3 Context Engine ID" , "CPE_snmpV3ContextEngineID", editSNMPv3ContextEngineId, xml);   //Snmp V3 Context Engine ID

		edittextFields_commonMethod(application, "Snmp V3 Security Username" , "CPE_snmpv3SecurityUserName", editSNMPv3securityUsername, xml);   //Snmp V3 Security Username

		addDropdownValues_commonMethod(application, "Snmp V3 Auth Proto" , "CPE_snmpv3AuthProto" , editSNMPv3AutoProto, xml);   ////Snmp V3 Auth Proto

		edittextFields_commonMethod(application, "Snmp V3 Auth Password", "CPE_snmpv3AuthPasswrd" , editSNMPv3AuthPasswrd, xml);   //Snmp V3 Auth Password

		ScrolltoElement(application, "trunk_okButton", xml);
		Thread.sleep(1000);

		//Country
		if(!editCountry.equalsIgnoreCase("Null")) {

			//addDropdownValues_commonMethod(application, "Country", "countrydropdown", editCountry, xml);
			selectValueInsideDropdown(application, "countrydropdown", "Country", editCountry, xml);

			//New City		
			if(editExistingCity.equalsIgnoreCase("no") & editNewCity.equalsIgnoreCase("yes")) {
				Clickon_addToggleButton(application, "addcityswitch");
				//City name
				edittextFields_commonMethod(application, "City Name", "citynameinputfield", editNewCityName, xml);
				//City Code	
				edittextFields_commonMethod(application, "City Code", "citycodeinputfield", editNewCityCode, xml);
				//Site name
				edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addCityToggleSelected", editNewSiteName, xml);
				//Site Code
				edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addCityToggleSelected", editNewSiteCode, xml);
				//Premise name
				edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected", editNewPremiseName, xml);
				//Premise Code	
				edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected", editNewPremiseCode, xml);

			}	

			//Existing City	
			else if(editExistingCity.equalsIgnoreCase("yes") & editNewCity.equalsIgnoreCase("no")) {

				selectValueInsideDropdown(application, "citydropdown", "City", editExistingCityValue, xml);

				//Existing Site
				if(editExistingSite.equalsIgnoreCase("yes") & editNewSite.equalsIgnoreCase("no")) {
					selectValueInsideDropdown(application, "sitedropdown", "Site", editExistingSiteValue, xml);

					//Existing Premise
					if(editExistingPremise.equalsIgnoreCase("yes") & editNewPremise.equalsIgnoreCase("no")) {
						selectValueInsideDropdown(application, "premisedropdown", "Premise", editExistingPremiseValue, xml);
					}

					//New Premise  
					else if(editExistingPremise.equalsIgnoreCase("no") & editNewPremise.equalsIgnoreCase("yes")) {

						Clickon_addToggleButton(application, "addpremiseswitch");
						//Premise name	
						edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addPremiseToggleSelected", editNewPremiseName, xml);
						//Premise Code	
						edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addPremiseToggleSelected", editNewPremiseCode, xml);
					} 
				}

				else if(editExistingSite.equalsIgnoreCase("no") & editNewSite.equalsIgnoreCase("yes")) {

					Clickon_addToggleButton(application, "addsiteswitch");
					//Site name
					edittextFields_commonMethod(application, "Site Name", "sitenameinputfield_addSiteToggleSelected", editNewSiteName, xml);
					//Site Code
					edittextFields_commonMethod(application, "Site Code", "sitecodeinputfield_addSiteToggleSelected", editNewSiteCode, xml);
					//Premise name	
					edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addSiteToggleSelected", editNewPremiseName, xml);
					//Premise Code	
					edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addSiteToggleSelected", editNewPremiseCode, xml);
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


		ScrolltoElement(application, "trunk_okButton", xml);
		Thread.sleep(1000);

		clickOnBankPage();

		ScrolltoElement(application, "trunk_okButton", xml);
		Thread.sleep(1000);

		click_commonMethod(application, "OK", "trunk_okButton" , xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "CPE Device updated successfully");
		scrollToTop();
		Thread.sleep(2000);
		clickOnBreadCrumb(application, sid);
		Thread.sleep(2000);
		waitforPagetobeenable();
	}

	public void verifyAllDeleteOperations(String application, String customernamevalue, String select_sansearchtype, String sannumbervalue) throws InterruptedException, DocumentException, IOException	{

		//Delete Service
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Delete", "delete", xml);
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
		compareText(application, "Service delete success message", "deletesuccessmsg", "Service successfully deleted.", xml);
		Thread.sleep(2000);
	}

	public void deleteDDIRange(String application) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "ddirange_panelheader", xml);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
		Thread.sleep(1000);
		click_commonMethod(application, "Delete", "ddi_deletelink", xml);
		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			Thread.sleep(1000);
			click_commonMethod(application, "Delete", "deletebutton", xml);
			verifysuccessmessage(application, "DDI Range successfully deleted.");
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
	}

	public void deletePortGroup(String application, String sid, String prefix_dropdownvalue, String edit_prefix_dropdownvalue, String routePriority_dropdownvalue, String voiceCPEdevicename, String edit_voiceCPEdevicename) throws InterruptedException, DocumentException {

		driver.navigate().refresh();
		Thread.sleep(1000);
		waitforPagetobeenable();
		ScrolltoElement(application, "disasterrecoverystatus_header", xml);
		Thread.sleep(1000);
		String AddedPort_RowID= DeletePortGroupRow(application, prefix_dropdownvalue, edit_prefix_dropdownvalue, routePriority_dropdownvalue, voiceCPEdevicename, edit_voiceCPEdevicename);
		Thread.sleep(2000);
		WebElement AddedPort_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/addedport_checkbox").replace("value", AddedPort_RowID));
		Clickon(AddedPort_Checkbox);
		Thread.sleep(1000);
		click_commonMethod(application, "Action Dropdown", "portgroup_Actiondropdown", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Delete Portgroup", "delete_portgroup", xml);
		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			Thread.sleep(1000);
			click_commonMethod(application, "Delete", "delete_alertDelete", xml);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Port Group successfully deleted.");
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}

		scrollToTop();
		Thread.sleep(1000);
		clickOnBreadCrumb(application, sid);
	}

	public void deleteVoiceCPEDevice(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "voiceCPEdevice_header", xml);
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/existingvoicecpe_devicegrid")).isDisplayed())
		{
			List<WebElement> AddedVoiceCPEDevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addedvoiceCPEdevicename"));
			System.out.println(AddedVoiceCPEDevicesList);
			int AddedVoiceCPEDevicesCount= AddedVoiceCPEDevicesList.size();

			for(int i=0;i<AddedVoiceCPEDevicesCount;i++) {
				String AddedVoiceCPEDeviceNameText= AddedVoiceCPEDevicesList.get(i).getText();
				System.out.println(AddedVoiceCPEDeviceNameText);
				String AddedVoiceCPEDevice_SNo= AddedVoiceCPEDeviceNameText.substring(0, 1);

				WebElement AddedVoiceCPEDevice_DeleteLink= getwebelement(xml.getlocator("//locators/" + application + "/addedvoicecpedevice_deletelink").replace("value", AddedVoiceCPEDevice_SNo));
				Clickon(AddedVoiceCPEDevice_DeleteLink);
				Thread.sleep(2000);
				WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
				if(DeleteAlertPopup.isDisplayed())
				{
					Thread.sleep(1000);
					click_commonMethod(application, "Delete Voice CPE Device", "deletebutton", xml);
					verifysuccessmessage(application, "Device deleted successfully");
				}
				else
				{
					Log.info("Delete alert popup is not displayed");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
				}
			}
		}
	}

	public void deleteCPEDevice(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

			List<WebElement> AddedCPEDevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addedCPEdevicename"));
			System.out.println(AddedCPEDevicesList);
			int AddedCPEDevicesCount= AddedCPEDevicesList.size();

			for(int i=0;i<AddedCPEDevicesCount;i++) {
				String AddedCPEDeviceNameText= AddedCPEDevicesList.get(i).getText();
				System.out.println(AddedCPEDeviceNameText);
				String AddedCPEDevice_SNo= AddedCPEDeviceNameText.substring(0, 1);

				WebElement AddedCPEDevice_DeleteLink= getwebelement(xml.getlocator("//locators/" + application + "/deleteCPEdeviceLink").replace("value", AddedCPEDevice_SNo));
				Clickon(AddedCPEDevice_DeleteLink);
				Thread.sleep(2000);
				WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
				if(DeleteAlertPopup.isDisplayed())
				{
					Thread.sleep(1000);
					click_commonMethod(application, "Delete CPE Device", "deletebutton", xml);
					verifysuccessmessage(application, "Device deleted successfully");
				}
				else
				{
					Log.info("Delete alert popup is not displayed");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
				}
			}
	}

	public void deleteResilientTrunk(String application, String siteOrderName) throws Exception {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

		WebElement getTrunkRow=getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedTrunk_InViewServicePage").replace("value", primarytrunkGroupname_Resilient));
		safeJavaScriptClick(getTrunkRow);

		//click on Action dropdown under Trunk Panel	
		WebElement trunkPanel_actionDropdown=getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel_ActionDropdown").replace("value", siteOrderName));
		safeJavaScriptClick(trunkPanel_actionDropdown);

		//delete trunk link
		click_commonMethod(application, "Delete Trunk", "deletetrunk_link" , xml);

		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			Thread.sleep(1000);
			click_commonMethod(application, "Delete Trunk", "deletebutton", xml);
			verifysuccessmessage(application, "Trunk deleted successfully");
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
	}

	public void deleteTrunk(String application, String siteOrderName) throws Exception {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

		WebElement getTrunkRow=getwebelement(xml.getlocator("//locators/" + application + "/selectCreatedTrunk_InViewServicePage").replace("value", Edit_primarytrunkGroupname));
		safeJavaScriptClick(getTrunkRow);

		//click on Action dropdown under Trunk Panel	
		WebElement trunkPanel_actionDropdown=getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel_ActionDropdown").replace("value", siteOrderName));
		safeJavaScriptClick(trunkPanel_actionDropdown);

		//delete trunk link
		click_commonMethod(application, "Delete Trunk", "deletetrunk_link" , xml);

		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			Thread.sleep(1000);
			click_commonMethod(application, "Delete Trunk", "deletebutton", xml);
			verifysuccessmessage(application, "Trunk deleted successfully");
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
	}
	
	public void deleteTrunkSiteOrder(String application, String siteOrderName) throws Exception {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "trunkPanel", xml);
		Thread.sleep(1000);

		WebElement deletelink=getwebelement("//div[div[span[text()='"+ siteOrderName +"']]]//span[text()='Delete']");
		Clickon(deletelink);

		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			Thread.sleep(1000);
			click_commonMethod(application, "Delete Trunk Group", "deletebutton", xml);
			verifysuccessmessage(application, "Trunk Group deleted successfully");
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
	}

	public void deleteInterface(String application, String interfacename, String edit_interfacename, String edit_asrdevicename) throws Exception {

		WebElement SelectInterface;
		
		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "asrdevice_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
		{
			click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		}
		Thread.sleep(1000);
		if(!edit_interfacename.equalsIgnoreCase("null")) {
			SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", edit_interfacename));
		}
		else
		{
			SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", interfacename));
		}
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface checkbox");
			Thread.sleep(1000);

			if(!edit_asrdevicename.equalsIgnoreCase("null"))
			{
				WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", edit_asrdevicename));
				Clickon(AddedDevice_Interface_Actiondropdown);
			}
			else
			{
				WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", ASRDeviceNameValue));
				Clickon(AddedDevice_Interface_Actiondropdown);
			}


			click_commonMethod(application, "Delete", "delete", xml);

			Thread.sleep(2000);
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			if(DeleteAlertPopup.isDisplayed())
			{
				Thread.sleep(1000);
				click_commonMethod(application, "Delete Interface", "deletebutton", xml);
				verifysuccessmessage(application, "Interface deleted successfully");
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
		}
	}

	public void deleteASRDevice(String application, String imspoplocation_dropdownvalue) throws Exception {

		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(1000);
		ScrolltoElement(application, "asrdevice_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevicename"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();

			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				System.out.println(AddedDeviceNameText);
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				String IMSPopLocation_Code= imsPopLocationValue(application, imspoplocation_dropdownvalue);
				if(AddedDeviceNameText.contains(IMSPopLocation_Code))
				{
					WebElement AddedDevice_DeleteLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_Deletefromservicelink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_DeleteLink);
					Thread.sleep(2000);
					WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
					if(DeleteAlertPopup.isDisplayed())
					{
						Thread.sleep(1000);
						click_commonMethod(application, "Delete ASR Device", "deletebutton", xml);
						waitforPagetobeenable();
						verifysuccessmessage(application, "ASR Device deleted successfully");
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
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Device not added for "+IMSPopLocation_Code+ " location");
				}
				
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}
	}

	public void deleteService(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Delete Service", "delete", xml);
		Thread.sleep(1000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			Thread.sleep(1000);
			click_commonMethod(application, "Delete ASR Device", "deletebutton", xml);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Service successfully deleted. ");
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
	}
	//======================================  Common Methods  ===========================================


	public void searchservice(String application, String sid) throws InterruptedException, DocumentException, IOException {

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
		Thread.sleep(1000);

		WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
		ScrolltoElement(application, "searchbutton", xml);
		Clickon(searchbutton);
		waitforPagetobeenable();
		ScrolltoElement(application, "searchbutton", xml);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
		Thread.sleep(1000);
		waitforPagetobeenable();
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

	public void shownewInfovista(String application) throws Exception {

		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		Thread.sleep(2000);
		click_commonMethod(application, "Show New Infovista Report", "shownewinfovistareport_link", xml);
		Thread.sleep(6000);

		String expectedPageName= "SSO Login Page";

		//Switch to new tab
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(browserTabs .get(1));
		Thread.sleep(10000);

		try { 
			// Get Tab name
			String pageTitle=driver.switchTo().window(browserTabs .get(1)).getTitle();
			System.out.println("page title displays as: "+pageTitle);

			assertEquals(pageTitle, expectedPageName, " on clicking 'Show Infovista link', it got naviagted to "+pageTitle);
			
			Thread.sleep(3000);
			driver.close();
			driver.switchTo().window(browserTabs.get(0)); 

			ExtentTestManager.getTest().log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
			Thread.sleep(3000);

			ExtentTestManager.getTest().log(LogStatus.PASS, "show info vista page actual title: "+pageTitle );
			ExtentTestManager.getTest().log(LogStatus.PASS, "show info vista page expected title: "+ expectedPageName);

		}catch(Exception e) {

			e.printStackTrace();

			Thread.sleep(3000);
			driver.close();
			driver.switchTo().window(browserTabs.get(0));

			ExtentTestManager.getTest().log(LogStatus.FAIL, expectedPageName + " page is not displaying");

		}
	}

	/**
	 *    
	 * @param application
	 * @throws DocumentException 
	 * @throws InterruptedException 
	 */
	public void clickOnBreadCrumb(String application, String sid) throws InterruptedException, DocumentException {

		scrollToTop();
		Thread.sleep(2000);
		WebElement breadcrumb=null;

		try {
			breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrumb").replace("value", sid));
			if(breadcrumb.isDisplayed()) 
			{
				Clickon(breadcrumb);
			}else {
				System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
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
			Log.info("failure in fetching success message - 'Service created Successfully'  ");
			ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
			System.out.println(expected+ " message is not getting dislpayed");
		}

	}

	public void editPrefix_dropdown(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml) throws InterruptedException, DocumentException {

		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
				System.out.println(labelname + " dropdown is displaying");

				if(expectedValueToAdd.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
					System.out.println(" No values selected under "+ labelname + " dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));

					ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					System.out.println( " List of values inside "+ labelname + "dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(2000);

					Clickon(getwebelement("(//div[label[text()='Prefix']]//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
					Thread.sleep(3000);

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
	
	public void addPrefix_DropdownValues(String application, String labelname, String xpath, String expectedValueToAdd , XMLReader xml) throws InterruptedException, DocumentException {
		  boolean availability=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
			  System.out.println(labelname + " dropdown is displaying");
			  
			  if(expectedValueToAdd.equalsIgnoreCase("null")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
				  System.out.println(" No values selected under "+ labelname + " dropdown");
			  }else {
				  
				  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
				  Thread.sleep(3000);
				  
				  //verify list of values inside dropdown
				  List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
				  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
				  
					for (WebElement valuetypes : listofvalues) {
								Log.info("service sub types : " + valuetypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
								System.out.println(" " + valuetypes.getText());
					}
					
					Thread.sleep(2000);
				SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
				Thread.sleep(2000);
					
				  Clickon(getwebelement("(//div[@class='sc-ifAKCX oLlzc'][contains(text(),'"+ expectedValueToAdd +"')])[1]"));
				  Thread.sleep(3000);
				  
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
	
	/**
	 *  For Comparing the value from actual value 
	 * @param application
	 * @param labelname
	 * @param xpath
	 * @param ExpectedText
	 * @param xml
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void compareText_fromActualvalue(String application, String labelname, String xpath, String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

		String text = null;
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
				   
				text = element.getText();
				if((text.contains(" ")) ||  text.contains("-")) {
					
					String[] actualTextValue=text.split(" ");
					String[] expectedValue =ExpectedText.split(" ");
					
					if(expectedValue[0].equalsIgnoreCase(actualTextValue[0])) {
						ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					}
					else if(expectedValue[0].contains(actualTextValue[0])) {
						ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					}
					
				}else {
					if(ExpectedText.equalsIgnoreCase(text)) {
						ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						Log.info(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					}
					else if(ExpectedText.contains(text)) {
						ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					
					}
					else if(text.contains(ExpectedText)) {
						ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
						Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
						Log.info("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			Log.info(labelname + " field is not displaying");
		}

	}
	
}
