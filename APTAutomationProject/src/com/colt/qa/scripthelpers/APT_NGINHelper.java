package com.colt.qa.scripthelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;


public class APT_NGINHelper extends DriverHelper {

	public APT_NGINHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_NGIN.xml");

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
		scrolltoend();
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
		scrolltoend();
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
		scrolltoend();
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

		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(1000);

		//Warning messages verify
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
			Log.info("Order not selected");
		}
	}

	public void verifyselectservicetype(String application, String servicetype)
			throws InterruptedException, IOException, DocumentException {

		// select service type
		scrolltoend();
		//addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/servicetypetextfield")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service Type dropdown is displaying");
				System.out.println("Service Type dropdown is displaying");
				Log.info("Service Type dropdown is displaying");

				if(servicetype.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Service Type dropdown");
					System.out.println("No values selected under Service Type dropdown");
					Log.info("No values selected under Service Type dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='Service Type']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = getwebelements("//div[@class='sc-ifAKCX oLlzc']");

					ExtentTestManager.getTest().log(LogStatus.PASS, "List of values inside Service Type dropdown is:  ");
					System.out.println( "List of values inside Service Type dropdown is:  ");
					Log.info("List of values inside Service Type dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='Service Type']]//input"), servicetype);	
					Thread.sleep(2000);

					//scrolltoend();
					ScrolltoElement(application, "nextbutton", xml);
					Thread.sleep(2000);
					Clickon(getwebelement("(//div[text()='"+ servicetype +"'])[1]"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//label[text()='Service Type']/following-sibling::div//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Service Type dropdown value selected as: "+ actualValue );
					System.out.println("Service Type dropdown value selected as: "+ actualValue);
					Log.info("Service Type dropdown value selected as: "+ actualValue);

				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Service Type is not displaying");
				System.out.println("Service Type is not displaying");
				Log.info("Service Type is not displaying");
			}
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Service Type is not displaying");
			System.out.println("Service Type is not displaying");
			Log.info("Service Type is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to perform selection under Service Type dropdown");
			System.out.println("NO value selected under Service Type dropdown");
			Log.info("NO value selected under Service Type dropdown");
		}
		// click on next button
		click_commonMethod(application, "Next", "nextbutton", xml);
		waitforPagetobeenable();
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

	}


	public void verifyservicecreation(String application, String sid, String Remarks, String customadm,
			String sanadm, String reselladm, String orderno, String rfireqno, String servicetype) throws InterruptedException, IOException, DocumentException {

		//Cancel service creation
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		waitforPagetobeenable();
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to create order page");
			System.out.println("Navigated to create order page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to create order page");
			Log.info("Navigated to create order page");
		}

		//Create service
		ScrolltoElement(application, "createorderservice_header", xml);
		click_commonMethod(application, "select order switch", "selectorderswitch", xml);
		addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno, xml);
		
		boolean availability1=false;
		try {  
			availability1=getwebelement(xml.getlocator("//locators/" + application + "/servicetypetextfield")).isDisplayed();
			if(availability1) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service Type dropdown is displaying");
				System.out.println("Service Type dropdown is displaying");
				Log.info("Service Type dropdown is displaying");

				if(servicetype.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "No values selected under Service Type dropdown");
					System.out.println("No values selected under Service Type dropdown");
					Log.info("No values selected under Service Type dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='Service Type']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

					ExtentTestManager.getTest().log(LogStatus.PASS, "List of values inside Service Type dropdown is:  ");
					System.out.println("List of values inside Service Type dropdown is:  ");
					Log.info("List of values inside Service Type dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='Service Type']]//input"), servicetype);	
					Thread.sleep(2000);

					//scrolltoend();
					ScrolltoElement(application, "nextbutton", xml);
					Thread.sleep(2000);
					Clickon(getwebelement("(//div[text()='"+ servicetype +"'])[1]"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//label[text()='Service Type']/following-sibling::div//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Service Type dropdown value selected as: "+ actualValue );
					System.out.println("Service Type dropdown value selected as: "+ actualValue);
					Log.info("Service Type dropdown value selected as: "+ actualValue);
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Service Type is not displaying");
				System.out.println("Service Type is not displaying");
				Log.info("Service Type is not displaying");
			}
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Service Type is not displaying");
			System.out.println("Service Type is not displaying");
			Log.info("Service Type is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "NOt able to perform selection under Service Type dropdown");
			System.out.println("NO value selected under Service Type dropdown");
			Log.info("NO value selected under Service Type dropdown");
		}

		// click on next button
		click_commonMethod(application, "Next", "nextbutton", xml);
		waitforPagetobeenable();
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

		// verify warning messages
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(1000);
		warningMessage_commonMethod(application, "sidwarngmsg", "Service Identification", xml);
		// service identification
		addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
		// remarks
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", Remarks, xml);
		
		// management options- Customer Administration Selection
		addCheckbox_commonMethod(application, "customeradministrationcheckbox", "customer administration checkbox", customadm, "yes", xml);
		
//		if (customadm.equalsIgnoreCase("YES")) {
//			click_commonMethod(application, "customer administration checkbox", "customeradministrationcheckbox", xml);
//		} else {
//			System.out.println("customer administration checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : customer administration checkbox is not selected");
//			Log.info("customer administration checkbox is not selected");
//		}
//
//		if (customadm.equalsIgnoreCase("NO")) {
//			System.out.println("customer administration checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : customer administration checkbox is already selected");
//			Log.info("customer administration checkbox is already selected");
//		} else {
//			click_commonMethod(application, "customer administration checkbox", "customeradministrationcheckbox", xml);
//		}

		// management options- SAN Administration Selection
		addCheckbox_commonMethod(application, "sanadministrationcheckbox", "SAN administration checkbox", sanadm, "yes", xml);

//		if (sanadm.equalsIgnoreCase("YES")) {
//			click_commonMethod(application, "SAN administration checkbox", "sanadministrationcheckbox", xml);
//		} else {
//			System.out.println("SAN administration checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : SAN administration checkbox is not selected");
//			Log.info("SAN administration checkbox is not selected");
//		}
//
//		if (sanadm.equalsIgnoreCase("NO")) {
//			System.out.println("SAN administration checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : SAN administration checkbox is already selected");
//			Log.info("SAN administration checkbox is already selected");
//		} else {
//			click_commonMethod(application, "SAN administration checkbox", "sanadministrationcheckbox", xml);
//		}

		// management options- Reseller Administration Selection
		addCheckbox_commonMethod(application, "reselleradministrationcheckbox", "Reseller Administration checkbox", reselladm, "yes", xml);
		
//		if (reselladm.equalsIgnoreCase("YES")) {
//			click_commonMethod(application, "Reseller Administration checkbox", "reselleradministrationcheckbox", xml);
//		} else {
//			System.out.println("Reseller Administration checkbox is not selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Reseller Administration checkbox is not selected");
//			Log.info("Reseller Administration checkbox is not selected");
//		}
//
//		if (reselladm.equalsIgnoreCase("NO")) {
//			System.out.println("Reseller Administration checkbox is already selected");
//			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Reseller Administration checkbox is already selected");
//			Log.info("Reseller Administration checkbox is already selected");
//		} else {
//			click_commonMethod(application, "Reseller Administration checkbox", "reselleradministrationcheckbox", xml);
//		}

		
		//click_commonMethod(application, "Next", "nextbutton", xml);
		WebElement Nextbutton= getwebelement(xml.getlocator("//locators/" + application + "/nextbutton"));
		((JavascriptExecutor)driver). executeScript("arguments[0]. click();", Nextbutton);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Service successfully created");
		sa.assertAll();

	}


	public void verifyCustomerDetailsInformation(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "customerdetailsheader", xml);
		//compareText(application, "Customer Name", "Name_Value", name, xml);
		GetText(application, "Customer Name", "Name_Value");
		compareText(application, "Main Domain", "MainDomain_Value", maindomain, xml);
		compareText(application, "Country", "Country_Value", country, xml);
		compareText(application, "OCN", "OCN_Value", ocn, xml);
		compareText(application, "Reference", "Reference_Value", reference, xml);
		compareText(application, "Technical Contact Name", "TechnicalContactName_Value", tcn, xml);
		compareText(application, "Type", "Type_Value", type, xml);
		compareText(application, "Email", "Email_Value", email, xml);
		compareText(application, "Phone", "Phone_Value", phone, xml);
		compareText(application, "Fax", "Fax_Value", fax, xml);
		Log.info("=== Customer Details panel fields Verified ===");
		sa.assertAll();
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
			String Services_ToBeAvailable, String Services_ToBeHidden, String SiteOrders_ToBeAvailable, String SiteOrders_ToBeHidden, String editIPGuardianAccountGroup, String editColtOnlineUser) throws InterruptedException, DocumentException, IOException {

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

        //Cancel User
        click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
        click_commonMethod(application, "Add", "AddLink", xml);
        compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);
        scrolltoend();
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

        scrolltoend();
        Thread.sleep(1000);

        //Hide Router Tool IPv4 Commands(Cisco)
        selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv4 Commands(Cisco)", "hideRouterToolIPv4_Cisco_Available", routerToolIPv4CiscoTobeSelectedList, "hideRouterToolIPv4_Cisco_addButton");
        verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv4 Commands(Cisco)", "hideRouterToolIpv4_Cisco_selectedvalues");


        //Hide Router Tool IPv4 Commands(Huawei)
        selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIPv4_Huawei_available" , routerToolIPv4HuaweiTobeSelectedList, "hideRouterToolIPv4__Huawei_addButton");
        verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv4 Commands(Huawei)" , "hideRouterToolIpv4_Huawei_selectedvalues");


        //          //Hide Router Tool IPv6 Commands(Cisco)   
        //                selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , "HideRouterToolIPv6_Cisco_Available" , selectValue, xpathForAddButton);
        //                verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , xpath);

        scrolltoend();
        Thread.sleep(1000);
        click_commonMethod(application, "OK", "OK_button", xml);
        Thread.sleep(2000);
        waitforPagetobeenable();
        verifysuccessmessage(application, "User successfully created");
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
              waitforPagetobeenable();
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
              System.out.println("Generated Password is : "+editpassword);

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
              waitforPagetobeenable();
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
                    System.out.println("list of values in Hide router Tool Command IPv4(Cisco) are: "+listofHiddenCiscoValues.getText());
                    ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Cisco) are: " + listofHiddenCiscoValues.getText());
              }

              scrolltoend();
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

              scrolltoend();
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


        //Delete User
        ScrolltoElement(application, "customerdetailsheader", xml);
        List<WebElement> ExistingUsers2= getwebelements("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']");
        int NoOfUsers2 = ExistingUsers2.size();
        System.out.println("Total users:"+ NoOfUsers2);
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
     	     waitforPagetobeenable();
     	     verifysuccessmessage(application, "User successfully deleted");
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
				Log.info("order information is matched");
			} else {
				sa.fail("order information is not matched");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
				Log.info("order information is not matched");
			}

		} else {

			System.out.println("existing order is not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : existing order is not selected");
			Log.info("existing order is not selected");
		}

		sa.assertAll();
	}

	public void verifyorderpanelinformation_Neworder(String application, String neworder, String expectedneworderno,
			String expectednewvoicelineno) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "userspanel_header", xml);

		if (neworder.equalsIgnoreCase("YES")) {

			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue")).getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue")).getText();
			System.out.println("actual voice line number displayed in order panel is : " + actualvoicelineno);

			if (expectedneworderno.equalsIgnoreCase(actualorderno)&& expectednewvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

				System.out.println("order information is matched");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : order information is matched");
				Log.info("order information is matched");
			} else {
				sa.fail("order information is not matched");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
				Log.info("order information is not matched");
			}

		} else {

			System.out.println("new order is not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : new order is not selected");
			Log.info("new order is not selected");
		}

		sa.assertAll();
	}

	public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "userspanel_header", xml);

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
		compareText(application, "Order Header", "orderpanelheader", "Order", xml);
		Log.info("Navigated to order panel in view service page");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

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
		compareText(application, "Order Header", "orderpanelheader", "Order", xml);
		Log.info("Navigated to order panel in view service page");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		compareText(application, "Order Number", "ordernumbervalue", editorderno, xml);
		compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno, xml);
		Log.info("------ Edit Order is successful ------");

	}

	public void verifyorderpanel_changeorder(String application, String changeorderno, String changevoicelineno) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "userspanel_header", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Change Order", "changeorderlink", xml);
		compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Choose order dropdown", "changeorder_chooseorderdropdown", xml);
		List<WebElement> ChangeOrder_DropdownList= getwebelements(xml.getlocator("//locators/" + application + "/changeorder_dropdownlist"));
		int ChangeOrder_Dropdown_count= ChangeOrder_DropdownList.size();
		if(ChangeOrder_Dropdown_count> 1)
		{
			Clickon(getwebelement("//label[text()='Order/Contract Number (Parent SID)']/parent::div//div[@role='list']//span[@aria-selected='false'][1]"));
			Thread.sleep(3000);

			//Cancel change order
			click_commonMethod(application, "Cancel", "changeorder_cancelbutton", xml);
			Thread.sleep(1000);
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
			Log.info("Navigated to order panel in view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

			//Change order
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Choose order dropdown", "changeorder_chooseorderdropdown", xml);
			Clickon(getwebelement("//label[text()='Order/Contract Number (Parent SID)']/parent::div//div[@role='list']//span[@aria-selected='false'][1]"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected order from dropdown");
			Log.info("Selected order from dropdown");
			click_commonMethod(application, "OK", "changeorder_okbutton", xml);
			Thread.sleep(1000);
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
			Log.info("Navigated to order panel in view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");
			compareText(application, "Order Number", "ordernumbervalue", changeorderno, xml);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
			Log.info("------ Change Order is successful ------");

		}
		else
		{
			click_commonMethod(application, "Select order switch", "changeorder_selectorderswitch", xml);
			click_commonMethod(application, "Order Number", "changeordernumber", xml);
			Thread.sleep(2000);
			addtextFields_commonMethod(application, "Order Number", "changeordernumber", changeorderno, xml);
			click_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", xml);
			Thread.sleep(3000);
			addtextFields_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", changevoicelineno, xml);
			click_commonMethod(application, "Cancel", "changeorder_cancelbutton", xml);
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
			Log.info("Navigated to order panel in view service page");

			//Change Order
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Select order switch", "changeorder_selectorderswitch", xml);
			click_commonMethod(application, "Order Number", "changeordernumber", xml);
			Thread.sleep(2000);
			addtextFields_commonMethod(application, "Order Number", "changeordernumber", changeorderno, xml);
			click_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", xml);
			Thread.sleep(3000);
			addtextFields_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", changevoicelineno, xml);
			click_commonMethod(application, "Create Order", "createorder_button", xml);
			Thread.sleep(1000);
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
			Log.info("Navigated to order panel in view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to order panel in view service page");
			compareText(application, "Order Number", "ordernumbervalue", changeorderno, xml);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
			Log.info("------ Change Order is successful ------");
		}
	}

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String Remarks) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "orderpanelheader", xml);
		//compareText(application, "Service panel Header", "servicepanel_header", "Service", xml);
		GetText(application, "Service panel Header", "servicepanel_header");
		compareText(application, "Service Identification", "servicepanel_serviceidentificationvalue", sid, xml);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype, xml);
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks, xml);
	}

	public void verifyEditService(String application, String EditRemarks, String Remarks, String sid, String servicetype) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service
		waitforPagetobeenable();
		ScrolltoElement(application, "orderpanelheader", xml);
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
		Thread.sleep(2000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		cleartext(application, "Remarks", "remarktextarea");
		addtextFields_commonMethod(application, "Remarks", "remarktextarea",  EditRemarks, xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		ScrolltoElement(application, "orderpanelheader", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks, xml);	
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
			Log.info("Didn't navigate to view service page");
		}
		//Edit service
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		waitforPagetobeenable();
		cleartext(application, "Remarks", "remarktextarea");
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
		scrolltoend();
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			verifysuccessmessage(application, "Service successfully updated");
		}
		else
		{
			Log.info("Service not updated");
		}
	}
	
	public void verifyManageService(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage", "manageLink", xml);
		waitforPagetobeenable();
		compareText(application, "Manage service header", "manageservice_header", "Manage Service", xml);
		//compareText(application, "Order Name", "status_ordername", changeorderno, xml);
		GetText(application, "Order Name", "status_ordername");
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
		if(servicestatuschangerequired.equalsIgnoreCase("Yes"))
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
						Log.info("Service status change request logged");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service status change request is not logged");
						Log.info("Service status change request is not logged");
					}
				}
				catch(StaleElementReferenceException e)
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No service history to display");
					Log.info("No service history to display");
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

		//compareText(application, "Order Name", "sync_ordername", changeorderno, xml);
		GetText(application, "Order Name", "status_ordername");
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
		waitforPagetobeenable();
		verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
		scrolltoend();
		click_commonMethod(application, "Back", "managepage_backbutton", xml);
		waitforPagetobeenable();
	}
		
	public void verifySynchronizeLink(String application) throws InterruptedException, DocumentException {
		
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Synchronize", "synchronizelink_servicepanel", xml);
		ScrolltoElement(application, "customerdetailsheader", xml);
		Thread.sleep(2000);
		verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
	}
	
	public void verifyBulkInterface(String application, String bulkjob_filepath, String ocn) throws InterruptedException, DocumentException, IOException, CsvException {
		
		//cancel bulk interface
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Bulk Interface", "bulkinterfacelink", xml);
		waitforPagetobeenable();
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		click_commonMethod(application, "Cancel", "bulkjobcancel", xml);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
		}

		//submit bulk job
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Bulk Interface", "bulkinterfacelink", xml);
		waitforPagetobeenable();
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		WebElement BulkJob_Choosefile_button= getwebelement(xml.getlocator("//locators/" + application + "/bulkjob_choosefilebutton"));
		BulkJob_Choosefile_button.sendKeys(bulkjob_filepath);
		click_commonMethod(application, "Submit", "bulkjobsubmit", xml);
		waitforPagetobeenable();
		verifysuccessmessage(application, "FRC Numbers sent to Queue for Creation. Please check the bulk operation of SANs here.");
		
		//Archive link in bulk interface page
		click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
		click_commonMethod(application, "Archive", "bulkinterface_archivelink", xml);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Service Status History Found Successfully.");
		ScrolltoElement(application, "bulkinterfacepage_cancel", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Cancel", "bulkinterfacepage_cancel", xml);
		Thread.sleep(1000);
		waitforPagetobeenable();
		//Refresh link in bulk interface page
		//scrollToTop();
		ScrolltoElement(application, "bulkinterfaceheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
		click_commonMethod(application, "Refresh", "bulkinterface_refreshlink", xml);
		waitforPagetobeenable();
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Bulk Interface page refresh successful");
		Log.info("Bulk Interface page refresh successful");
		Thread.sleep(1000);
		scrolltoend();
		click_commonMethod(application, "Cancel", "bulkinterfacepage_cancel", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();

	}

	public void verifyManagementOptionspanel(String application, String customadm,
			String sanadm, String reselladm) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "managementoptions_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		
		compareText(application, "Management Options", "managementoptions_header", "Management Options", xml);

		// verify customer administration information
		compareText(application, "Customer Administration", "customeradmin_text", "Customer Administration", xml);
		if(customadm.equalsIgnoreCase("No")) {
		compareText(application, "Customer Administration", "customeradmin_value", "No", xml);
		}
		else {
			compareText(application, "Customer Administration", "customeradmin_value", "Yes", xml);
		}
		
		// verify SAN Administration information
		compareText(application, "SAN Administration", "sanadmin_text", "SAN Administration", xml);
		if(sanadm.equalsIgnoreCase("No")) {
			compareText(application, "SAN Administration", "sanadmin_value", "No", xml);
			}
			else {
				compareText(application, "SAN Administration", "sanadmin_value", "Yes", xml);
			}
		// verify Reseller Administration information
		compareText(application, "Reseller Administration", "reselleradmin_text", "Reseller Administration", xml);
		if(reselladm.equalsIgnoreCase("No")) {
			compareText(application, "Reseller Administration", "reselleradmin_value", "No", xml);
			}
			else {
				compareText(application, "Reseller Administration", "reselleradmin_value", "Yes", xml);
			}
	}


	public void verifyResellerpanel(String application) throws InterruptedException, DocumentException, IOException {

//		ScrolltoElement(application, "resellerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "managementoptions_header", xml);
		compareText(application, "Reseller", "resellerheader", "Reseller", xml);
		click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Add link is displaying under Reseller panel");
			Log.info("Add link is displaying under Reseller panel");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Add link is not displaying under Reseller panel");
			Log.info("Add link is not displaying under Reseller panel");
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));

	}

	public static String ResellerName;
	public void AddReseller(String application, String ocn, String email, String city, String streetname, String streetno, String pobox, String zipcode, String phone, String fax) throws InterruptedException, DocumentException, IOException {
		
//		ScrolltoElement(application, "resellerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "managementoptions_header", xml);
		compareText(application, "Reseller", "resellerheader", "Reseller", xml);

		// verify customer name column
		compareText(application, "Customer Name", "reseller_customername_column", "Customer Name", xml);
		// verify status column
		GetText(application, "Status", "statuscolumn");
		// verify Add link in Reseller action dropdown
		//Cancel add reseller
		click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		waitforPagetobeenable();
		compareText(application, "Manage Reseller in OSP", "manageresellerheader", "Manage Reseller in OSP", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
		}

		//warning msg verfiy in reseller panel
//		ScrolltoElement(application, "resellerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "managementoptions_header", xml);
		click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		waitforPagetobeenable();
		compareText(application, "Manage Reseller in OSP", "manageresellerheader", "Manage Reseller in OSP", xml);
		click_commonMethod(application, "Next", "Next_Button", xml);
		warningMessage_commonMethod(application, "reselleremail_warngmsg", "Email", xml);
		warningMessage_commonMethod(application, "resellercity_warngmsg", "City", xml);
		warningMessage_commonMethod(application, "resellerstreetname_warngmsg", "Street Name", xml);
		warningMessage_commonMethod(application, "resellerstreetno_warngmsg", "Street Number", xml);
		warningMessage_commonMethod(application, "resellerpobox_warngmsg", "PO Box", xml);
		warningMessage_commonMethod(application, "resellerzipcode_warngmsg", "Zip Code", xml);
		warningMessage_commonMethod(application, "resellerphone_warngmsg", "Phone", xml);

		//Add reseller
		ScrolltoElement(application, "manageresellerheader", xml);
		Thread.sleep(1000);
		String resellername= getwebelement(xml.getlocator("//locators/" + application + "/resellername")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Reseller Name is displaying as: '"+resellername+"'");
		Log.info("Reseller Name is displaying as: '"+resellername+"'");
		addtextFields_commonMethod(application, "Email", "reseller_email", email, xml);
		addtextFields_commonMethod(application, "City", "reseller_city", city, xml);
		addtextFields_commonMethod(application, "Street Name", "reseller_streetname", streetname, xml);
		addtextFields_commonMethod(application, "Street Number", "reseller_streetno", streetno, xml);
		addtextFields_commonMethod(application, "PO Box", "reseller_pobox", pobox, xml);
		addtextFields_commonMethod(application, "Zip Code", "reseller_zipcode", zipcode, xml);
		addtextFields_commonMethod(application, "Phone", "reseller_phone", phone, xml);
		addtextFields_commonMethod(application, "Fax", "reseller_fax", fax, xml);
		scrolltoend();
		click_commonMethod(application, "Next", "Next_Button", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			scrollToTop();
			verifysuccessmessage(application, "Reseller successfully created. Please check the Reseller Status for association details.");
		}
		else
		{
			Log.info("Reseller not created");
			System.out.println("Reseller not created");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Reseller not created");
		}

		//Added Reseller
//		ScrolltoElement(application, "resellerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "managementoptions_header", xml);
		WebElement ResellerGridCheck= getwebelement("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String ResellerGrid= ResellerGridCheck.getAttribute("style");
		WebElement AddedReseller= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + ocn + "')]/parent::div//span[contains(@class,'unchecked')]");
		if(!ResellerGrid.contains("height: 1px"))
		{
			Clickon(AddedReseller);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
			List<WebElement> ResellerLinks= getwebelements("//div[contains(text(),'Reseller')]/following-sibling::div//div//a");
			int ResellerLinksCount= ResellerLinks.size();
			for(int i=0;i<ResellerLinksCount;i++)
			{
				String Link= ResellerLinks.get(i).getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, ""+Link+" link is displaying under reseller panel");
				System.out.println("Reseller link:"+ Link + " is displaying");
				Log.info("Reseller link:"+ Link + " is displaying");
				Thread.sleep(2000);
			}
			//Added Reseller grid verification
			compareText(application, "Added Reseller Name", "Addedreseller_columnvalue", ocn, xml);
			
		}
		else
		{
			Log.info("Reseller is not added in the grid");
			System.out.println("Reseller is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Reseller is not added in the grid");
		}
		ResellerName= ocn;
		
	}


public void verify_ViewReseller(String application, String ocn, String email, String city, String streetname, String streetno, String pobox, String zipcode, String phone, String fax) throws InterruptedException, DocumentException, IOException {
		
//	ScrolltoElement(application, "resellerheader", xml);
//	((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
	ScrolltoElement(application, "managementoptions_header", xml);
		WebElement ResellerGridCheck= getwebelement("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String ResellerGrid= ResellerGridCheck.getAttribute("style");
		WebElement AddedReseller= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ ocn +"')]/parent::div//span[contains(@class,'unchecked')]");
		
		//View Reseller
		if(!ResellerGrid.contains("height: 1px"))
		{
			//Clickon(AddedReseller);
			//Thread.sleep(1000);
			//click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
			Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
		{
			click_commonMethod(application, "View", "view", xml);
			Thread.sleep(2000);
			scrollToTop();
			waitforPagetobeenable();
			//compareText(application, "Manage Reseller Header", "manageresellerheader_viewpage", "Manage Reseller In OSP", xml);
			GetText(application, "Manage Reseller Header", "manageresellerheader_viewpage");
			compareText(application, "Reseller Name", "resellernamevalue", ocn, xml);
			compareText(application, "Email", "reselleremailvalue", email, xml);
			compareText(application, "City", "resellercityvalue", city, xml);
			compareText(application, "Street Name", "resellerstreetnamevalue", streetname, xml);
			compareText(application, "Street Number", "resellerstreetnovalue", streetno, xml);
			compareText(application, "PO Box", "resellerpoboxvalue", pobox, xml);
			compareText(application, "Zipcode", "resellerzipcodevalue", zipcode, xml);
			compareText(application, "Phone", "resellerphonevalue", phone, xml);
			compareText(application, "Fax", "resellerfaxvalue", fax, xml);
			GetText(application, "Web Access Authorized", "resellerwebaccessvalue");
			Thread.sleep(1000);
			scrollToTop();
			Thread.sleep(2000);
			click_commonMethod(application, "View page Action dropdown", "viewpage_actiondropdown", xml);

			//Edit link in view reseller page
			click_commonMethod(application, "View page Edit", "edit", xml);
			waitforPagetobeenable();
			//compareText(application, "Manage Reseller Header", "manageresellerheader", "Manage Reseller In OSP", xml);
			GetText(application, "Manage Reseller Header", "manageresellerheader");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Edit Reseller page is displaying as expected");
			Log.info("Edit Reseller page is displaying as expected");
			scrolltoend();
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			waitforPagetobeenable();
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : View link is not displaying under Reseller panel");
			Log.info("View link is not displaying under Reseller panel");
		}
		}
		else
		{
			Log.info("Reseller is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Reseller is not added in the grid");
		}
//		ScrolltoElement(application, "resellerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "managementoptions_header", xml);
		WebElement AddedReseller2= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ ocn +"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!ResellerGrid.contains("height: 1px"))
		{
			Clickon(AddedReseller2);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				click_commonMethod(application, "View", "view", xml);
				waitforPagetobeenable();
				GetText(application, "Manage Reseller Header", "manageresellerheader_viewpage");
				scrollToTop();
				click_commonMethod(application, "View page Action dropdown", "viewpage_actiondropdown", xml);

				//Delete link in view reseller page
				click_commonMethod(application, "View page Delete", "delete", xml);
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete Reseller alert is displaying as expected");
					Log.info("Delete Reseller alert is displaying as expected");
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletealertclose")));
					Thread.sleep(2000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on delete alert popup close button");
					Log.info("clicked on delete alert popup close button");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete alert popup is not displayed");
					Log.info("Delete alert popup is not displayed");
				}

			//Associate Reseller with NGIN Objects link in view reseller page
			click_commonMethod(application, "View page Action dropdown", "viewpage_actiondropdown", xml);
			click_commonMethod(application, "Associate Reseller with NGIN Objects", "associateresellerlink", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Associate Reseller with NGIN Objects link verified");
			}

		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : View link is not displaying under Reseller panel");
			Log.info("View link is not displaying under Reseller panel");
		}
	}
	else
	{
		Log.info("Reseller is not added in the grid");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Reseller is not added in the grid");
	}

}

public void verify_EditReseller(String application, String ocn, String editemail, String editcity, String editstreetname, String editstreetno, String editpobox, String editzipcode, String editphone, String editfax) throws InterruptedException, DocumentException, IOException {
	
//	ScrolltoElement(application, "resellerheader", xml);
//	((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
	ScrolltoElement(application, "managementoptions_header", xml);
	WebElement ResellerGridCheck= getwebelement("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
	String ResellerGrid= ResellerGridCheck.getAttribute("style");
	WebElement AddedReseller= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ ocn +"')]/parent::div//span[contains(@class,'unchecked')]");
	//WebElement AddedReseller= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'nginocn2506')]/parent::div//span[contains(@class,'unchecked')]");
	//Edit Reseller
	if(!ResellerGrid.contains("height: 1px"))
	{
		Clickon(AddedReseller);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		GetText(application, "Manage Reseller Header", "manageresellerheader");
		edittextFields_commonMethod(application, "Email", "reseller_email", editemail, xml);
		edittextFields_commonMethod(application, "City", "reseller_city", editcity, xml);
		scrolltoend();
		edittextFields_commonMethod(application, "Street Name", "reseller_streetname", editstreetname, xml);
		edittextFields_commonMethod(application, "Street Number", "reseller_streetno", editstreetno, xml);
		edittextFields_commonMethod(application, "PO Box", "reseller_pobox", editpobox, xml);
		edittextFields_commonMethod(application, "Zip Code", "reseller_zipcode", editzipcode, xml);
		edittextFields_commonMethod(application, "Phone", "reseller_phone", editphone, xml);
		edittextFields_commonMethod(application, "Fax", "reseller_fax", editfax, xml);
		scrolltoend();
		click_commonMethod(application, "OK", "okbutton", xml);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			//ScrolltoElement(application, "editreseller_successmsg", xml);
			verifysuccessmessage(application, "Reseller already in OSP. Successfully updated.");
		}
		else
		{
			Log.info("Reseller not updated");
			System.out.println("Reseller not updated");
		}
	}
	else
	{
		Log.info("Reseller is not added in the grid");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Reseller is not added in the grid");

	}
}
			
	
	public void verifyCustomerpanel(String application) throws InterruptedException, DocumentException, IOException {

//		ScrolltoElement(application, "customerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "resellerheader", xml);
		compareText(application, "Customer panel header", "customerheader", "Customer", xml);
		click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Add link is displaying under Customer panel");
			Log.info("Add link is displaying under Customer panel");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Add link is not displaying under Customer panel");
			Log.info("Add link is not displaying under Customer panel");
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
	}

	String Customername=null;
	public void AddCustomer(String application, String resellername, String defaultvalue, String configure, String country, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode) throws InterruptedException, DocumentException, IOException {

//		ScrolltoElement(application, "customerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "resellerheader", xml);
		compareText(application, "Customer panel header", "customerheader", "Customer", xml);

		// verify customer name column
		compareText(application, "Customer Name column", "customerpanel_customernamecolumntext", "Customer Name", xml);

		// verify Add link in customer action dropdown
		//Cancel add customer
		click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
		}

		//Warning msgs verify in Add customer page
//		ScrolltoElement(application, "customerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "resellerheader", xml);
		click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
		Thread.sleep(1000);
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Next", "nextbutton", xml);
		warningMessage_commonMethod(application, "addcustomer_country_warningmsg", "Country", xml);
		warningMessage_commonMethod(application, "addcustomer_customername_warningmsg", "Customer Name", xml);
		warningMessage_commonMethod(application, "addcustomer_city_warningmsg", "City", xml);
		warningMessage_commonMethod(application, "addcustomer_streetname_warningmsg", "Street Name", xml);
		warningMessage_commonMethod(application, "addcustomer_streetno_warningmsg", "Street Number", xml);
		warningMessage_commonMethod(application, "addcustomer_pobox_warningmsg", "PO Box", xml);
		warningMessage_commonMethod(application, "addcustomer_zipcode_warningmsg", "ZIP Code", xml);

		//Add Customer
		//Select Country from dropdown
		ScrolltoElement(application, "manageresellerheader", xml);
		Thread.sleep(2000);
		addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
		String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
		Log.info("OCN is displaying as: '"+CustomerOCN+"'");
		String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
		Log.info("Customer Name is displaying as: '"+CustomerNamevalue+"'");
		String CustomerName_viewpage= CustomerNamevalue.replace("(New)", "").trim();
		System.out.println("Customer name is :"+CustomerName_viewpage);
		addtextFields_commonMethod(application, "Reseller Name", "resellername", resellername, xml);
		GetText(application, "Email", "reseller_email");
		addtextFields_commonMethod(application, "City", "reseller_city", city, xml);
		addtextFields_commonMethod(application, "Street Name", "reseller_streetname", streetname, xml);
		addtextFields_commonMethod(application, "Street Number", "reseller_streetno", streetno, xml);
		addtextFields_commonMethod(application, "PO Box", "reseller_pobox", pobox, xml);
		addtextFields_commonMethod(application, "Zip Code", "reseller_zipcode", zipcode, xml);
		GetText(application, "Phone", "reseller_phone");
		GetText(application, "Fax", "reseller_fax");

		if(defaultvalue.equalsIgnoreCase("YES"))
		{
			String Default_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/defaultcheckbox")).getAttribute("checked");
			if(Default_Checkbox!=null)
			{
				System.out.println("Default checkbox is checked");
				Thread.sleep(1000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Default checkbox is checked");
				Log.info("Default checkbox is checked");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Default checkbox is not checked by default");
				Log.info("Default checkbox is not checked by default");
			}
		}

		else if(configure.equalsIgnoreCase("YES"))
		{
			String Configure_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/configurecheckbox")).getAttribute("checked");
			if(Configure_Checkbox!=null)
			{
				System.out.println("Configure checkbox is checked");
				Thread.sleep(1000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Configure checkbox is checked");
				Log.info("Configure checkbox is checked");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Configure checkbox is not checked");
				Log.info("Configure checkbox is not checked");
				click_commonMethod(application, "Configure checkbox", "configurecheckbox", xml);
			}
		}
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(3000);	
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			verifysuccessmessage(application, "Manage Customer successfully created");
		}
		else
		{
			Log.info("Customer not created");
			System.out.println("Customer not created");
		}

		//Added Customer
//		ScrolltoElement(application, "customerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "resellerheader", xml);
		WebElement CustomerGridCheck= getwebelement("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String CustomerGrid= CustomerGridCheck.getAttribute("style");

		WebElement AddedCustomer= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + CustomerName_viewpage + "')]/parent::div//span[contains(@class,'unchecked')]");
		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);

			List<WebElement> CustomerLinks= getwebelements("//div[contains(text(),'Customer')]/following-sibling::div//div//a");
			int CustomerLinksCount= CustomerLinks.size();
			for(int i=0;i<CustomerLinksCount;i++)
			{
				String Link= CustomerLinks.get(i).getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, ""+Link+" link is displaying under customer panel");
				Log.info(""+Link+" link is displaying under customer panel");
				System.out.println(""+Link+" link is displaying under customer panel");
				Thread.sleep(2000);
			}
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));

			//Added Customer grid verification
			WebElement AddedCustomer1= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + CustomerName_viewpage + "')]");
			String AddedCustomerName_value = AddedCustomer1.getText();
			Log.info("Added Customer Name is displayed as : " + AddedCustomerName_value);
			System.out.println("Added Customer Name:"+ AddedCustomerName_value);
			sa.assertEquals(AddedCustomerName_value,CustomerName_viewpage);
		}
		else
		{
			Log.info("Customer is not added in the grid");
			System.out.println("Customer is not added in the grid");
		}

		Customername= CustomerName_viewpage;
	}

	public void verify_ViewCustomer(String application, String resellername, String defaultvalue, String configure, String country, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "resellerheader", xml);
		WebElement CustomerGridCheck= getwebelement("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String CustomerGrid= CustomerGridCheck.getAttribute("style");

		WebElement AddedCustomer= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ Customername+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);

			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				Thread.sleep(1000);
				click_commonMethod(application, "View", "view", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				scrollToTop();
				compareText(application, "Manage Customer in OSP header", "manageresellerheader_viewpage", "Manage Customer In OSP", xml);
				Thread.sleep(1000);

				GetText(application, "Country", "customer_countryvalue");
				GetText(application, "Customer Name", "customer_customernamevalue");
				GetText(application, "Reseller Name", "resellernamevalue");
				GetText(application, "Email", "reselleremailvalue");
				GetText(application, "City", "resellercityvalue");
				GetText(application, "Street Name", "resellerstreetnamevalue");
				GetText(application, "Street Number", "resellerstreetnovalue");
				GetText(application, "PO Box", "resellerpoboxvalue");
				GetText(application, "Zip Code", "resellerzipcodevalue");
				GetText(application, "Phone", "resellerphonevalue");
				GetText(application, "Fax", "resellerfaxvalue");
				Thread.sleep(1000);

				//Edit customer in view customer page
				scrollToTop();
				click_commonMethod(application, "Action dropdown", "viewpage_actiondropdown", xml);
				click_commonMethod(application, "Edit", "viewpage_editcustomer", xml);
				waitforPagetobeenable();
				compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Edit Customer in view customer page is verified");
				Log.info("Edit Customer in view customer page is verified");
				Thread.sleep(1000);
				scrolltoend();
				click_commonMethod(application, "Cancel", "cancelbutton", xml);
				Thread.sleep(1000);
				waitforPagetobeenable();
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing customer displaying under customer panel");
			Log.info("No existing customer displaying under customer panel");
		}

		//Delete customer in view customer page
//		ScrolltoElement(application, "customerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "resellerheader", xml);
		WebElement AddedCustomer2= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ Customername +"')]/parent::div//span[contains(@class,'unchecked')]");

		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer2);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);

			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				click_commonMethod(application, "View", "view", xml);
				scrollToTop();
				waitforPagetobeenable();
				compareText(application, "Manage Customer in OSP header", "manageresellerheader_viewpage", "Manage Customer In OSP", xml);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Delete Customer in view customer page is verified");
				Log.info("Delete Customer in view customer page is verified");
				Thread.sleep(1000);
				scrollToTop();
				click_commonMethod(application, "Action dropdown", "viewpage_actiondropdown", xml);

				//Delete customer in view customer page
				click_commonMethod(application, "Delete", "viewpage_deletecustomer", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
				{
					click_commonMethod(application, "Delete Alert close", "deletealertclose", xml);
				}
				else
				{
					Log.info("Delete alert popup is not displayed");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
				}
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : View link is not displaying under customer panel");
				Log.info("View link is not displaying under customer panel");
			}
			scrolltoend();
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			waitforPagetobeenable();
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing customer displaying under customer panel");
			Log.info("No existing customer displaying under customer panel");
		}
	}
		
	public void verify_EditCustomer(String application, String editreseller, String editemail, String editcity, String editstreetname, String editstreetno, String editpobox, String editzipcode, String editphone, String editfax) throws InterruptedException, DocumentException, IOException {

//		ScrolltoElement(application, "customerheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "resellerheader", xml);
		WebElement CustomerGridCheck= getwebelement("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String CustomerGrid= CustomerGridCheck.getAttribute("style");

		WebElement AddedCustomer= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ Customername+"')]/parent::div//span[contains(@class,'unchecked')]");

		//Edit Customer
		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			scrollToTop();
			compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
			Thread.sleep(1000);

			String CountryDisabled= getwebelement(xml.getlocator("//locators/" + application + "/customercountry_disabled")).getAttribute("disabled");
			if(CountryDisabled!=null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Country dropdown is disabled as expected");
				Log.info("Country dropdown is disabled as expected");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Country dropdown is enabled");
				Log.info("Country dropdown is enabled");
			}
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
			Log.info("OCN is displaying as: '"+CustomerOCN+"'");

			String CustomernameDisabled= getwebelement(xml.getlocator("//locators/" + application + "/customername_disabled")).getAttribute("disabled");
			if(CustomernameDisabled!=null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer name dropdown is disabled as expected");
				Log.info("Customer name dropdown is disabled as expected");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Customer name dropdown is enabled");
				Log.info("Customer name dropdown is enabled");
			}
			edittextFields_commonMethod(application, "Reseller name", "resellername", editreseller, xml);
			edittextFields_commonMethod(application, "Email", "reseller_email", editemail, xml);
			edittextFields_commonMethod(application, "City", "reseller_city", editcity, xml);
			edittextFields_commonMethod(application, "Street Name", "reseller_streetname", editstreetname, xml);
			edittextFields_commonMethod(application, "Street Number", "reseller_streetno", editstreetno, xml);
			edittextFields_commonMethod(application, "PO Box", "reseller_pobox", editpobox, xml);
			edittextFields_commonMethod(application, "Zip Code", "reseller_zipcode", editzipcode, xml);
			edittextFields_commonMethod(application, "Phone", "reseller_phone", editphone, xml);
			edittextFields_commonMethod(application, "Fax", "reseller_fax", editfax, xml);
			scrolltoend();
			click_commonMethod(application, "Ok", "okbutton", xml);
			waitforPagetobeenable();
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
				verifysuccessmessage(application, "Manage Customer successfully updated");
			}
			else
			{
				Log.info("Customer not updated");
				System.out.println("Customer not updated");
			}

		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No existing customer displaying under customer panel");
			Log.info("No existing customer displaying under customer panel");
		}
	}
	
	public void verifySANpanel(String application) throws InterruptedException, DocumentException, IOException {

//		ScrolltoElement(application, "sanheader", xml);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);		

		if(getwebelement(xml.getlocator("//locators/" + application + "/addsan_link")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Add SAN link is displaying under SAN/FRC panel");
			Log.info("Add link is displaying under SAN/FRC panel");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Add link is not displaying under SAN/FRC panel");
			Log.info("Add link is not displaying under SAN/FRC panel");
		}
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/sanheader")));

	}


	public void AddSAN(String application, String country, String sannumber, String predestinationnumber, String ringtonumber, String routingforpayphone_value, String routingformobile_value, String defaultrouting_value, String RingToNumber_Checkbox, String AnnouncementToPlay_Checkbox, String ComplexRouting_Checkbox, String defaultroutebusy_value, String noanswer_value, String networkcongestion, String resellername, String defaultvalue, String configure, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode, String serviceprofilevalue, String maxcallduration, String chargebandname, String internationaloutgoingcalls_checkbox, String internationalincomingcalls_checkbox, String mobilecallsallowed_checkbox, String payphoneblocking_checkbox, String supervisionfieldvalue, String noreplytimervalue, String webaccessblocked_checkbox, String cpsfreeformatvalue, String sanblock_checkbox, String focenabled_checkbox, String enablelogicalrouting_Checkbox, String enablepriceannouncement_checkbox) throws InterruptedException, DocumentException, IOException, InvalidFormatException {

		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);

		//verify FRC number column
		compareText(application, "FRC Number column", "frcnumber_column", "FRC Number", xml);
		// verify customer name column
		compareText(application, "Customer name column", "san_customername", "Customer Name", xml);

		// verify Add SAN link in SAN/FRC action dropdown
		//Cancel add SAN
		click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);	
		click_commonMethod(application, "Add SAN", "addsan_link", xml);
		scrollToTop();
		waitforPagetobeenable();
		compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);
		Thread.sleep(1000);
		ScrolltoElement(application, "cancelbutton", xml);
		Thread.sleep(2000);
		//click_commonMethod(application, "Cancel", "cancelbutton", xml);
		WebElement Cancel= getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", Cancel);
		Thread.sleep(2000);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
		}

		//Add SAN
		ScrolltoElement(application, "customerheader", xml);
		click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);	
		click_commonMethod(application, "Add SAN", "addsan_link", xml);
		waitforPagetobeenable();
		scrollToTop();
		Thread.sleep(2000);
		compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);
		addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
		click_commonMethod(application, "Customer Name dropdown", "customername", xml);
		Thread.sleep(1000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_valuesdisplay")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Existing customer is available");
			Log.info("Existing customer is available");
			click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
		}
		else 
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Existing customer is not available");
			Log.info("Existing customer is not available");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")));

			//Manage new customer link
			click_commonMethod(application, "Manage new customer", "managenewcustomer_link", xml);
			waitforPagetobeenable();
			compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
			Thread.sleep(1000);

			//=========================

			//Add Customer
			//Select Country from dropdown
			addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
			Log.info("OCN is displaying as: '"+CustomerOCN+"'");
			String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
			Log.info("Customer Name is displaying as: '"+CustomerNamevalue+"'");
			CustomerNamevalue.replace("(New)", "").trim();
			addtextFields_commonMethod(application, "Reseller Name", "resellername", resellername, xml);
			GetText(application, "Email", "reseller_email");
			addtextFields_commonMethod(application, "City", "reseller_city", city, xml);
			addtextFields_commonMethod(application, "Street Name", "reseller_streetname", streetname, xml);
			addtextFields_commonMethod(application, "Street Number", "reseller_streetno", streetno, xml);
			addtextFields_commonMethod(application, "PO Box", "reseller_pobox", pobox, xml);
			addtextFields_commonMethod(application, "Zip Code", "reseller_zipcode", zipcode, xml);
			GetText(application, "Phone", "reseller_phone");
			GetText(application, "Fax", "reseller_fax");

			addCheckbox_commonMethod(application, "defaultcheckbox", "Default", defaultvalue, "yes", xml);
			addCheckbox_commonMethod(application, "configurecheckbox", "Configure", configure, "no", xml);
			ScrolltoElement(application, "nextbutton", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Next", "nextbutton", xml);
			Thread.sleep(3000);	
			waitforPagetobeenable();
			ScrolltoElement(application, "addsan_header", xml);
			compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);
			Thread.sleep(1000);

			//Select Country from dropdown
			addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
			click_commonMethod(application, "Customer Name dropdown", "customername", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
		}

		Thread.sleep(1000);
		//SAN customer name
		String SAN_Customernamevalue= getwebelement(xml.getlocator("//locators/" + application + "/customername_selectedtext")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step :Customer name selected is : " + SAN_Customernamevalue);
		Log.info("Customer name selected is : " + SAN_Customernamevalue);
		String SAN_Customername=SAN_Customernamevalue.substring(0, SAN_Customernamevalue.indexOf("(")).trim();
		writetoexcel("src\\com\\colt\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 162, SAN_Customername);

		String SANNumber_CountryCode=getwebelement(xml.getlocator("//locators/" + application + "/san_number")).getAttribute("value");
		addtextFields_commonMethod(application, "SAN Number", "san_number", sannumber, xml);
		//SAN number
		String SANNumberValue= SANNumber_CountryCode+sannumber;
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step :SAN Number is dsplayed as : " + SANNumberValue);
		Log.info("SAN Number is dsplayed as : " + SANNumberValue);
		writetoexcel("src\\com\\colt\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 163, SANNumberValue);

		//Select service profile from dropdown
		addDropdownValues_commonMethod(application, "Service Profile", "serviceprofile", serviceprofilevalue, xml);
		Thread.sleep(1000);
		GetText(application, "Supervision Mode", "supervisionfield");
		addCheckbox_commonMethod(application, "internationaloutgoingforbidden_checkbox", "International Outgoing Calls Forbidden", internationaloutgoingcalls_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "internationalincomingbarring", "International Incoming Calls Barring", internationalincomingcalls_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "mobilecallsallowed_checkbox", "Mobile Calls Allowed", mobilecallsallowed_checkbox, "yes", xml);
		addtextFields_commonMethod(application, "No reply timer value", "noreplytimervalue", noreplytimervalue, xml);
		addtextFields_commonMethod(application, "Max call duration", "maxcallduration", maxcallduration, xml);
		//Select charge band name from dropdown
		ScrolltoElement(application, "customer_country", xml);
		addDropdownValues_commonMethod(application, "Charge Band Name", "chargebandname", chargebandname, xml);
		addCheckbox_commonMethod(application, "payphoneblockingenabled", "Pay phone blocking enabled", payphoneblocking_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "webaccessblocked", "webAccessBlocked", webaccessblocked_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "sanblock", "SAN Block", sanblock_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "focenabled", "FOC Enabled", focenabled_checkbox, "no", xml);
		addtextFields_commonMethod(application, "Pre Destination Number", "predestinationnumber", predestinationnumber, xml);
		String CPSFreeFormatValue= getwebelement(xml.getlocator("//locators/" + application + "/cpsfreeformat")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : CPS Free Format field value is displayed as:"+ CPSFreeFormatValue);
		String CPSSANEncodedValue= getwebelement(xml.getlocator("//locators/" + application + "/cpssanencoded")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : 'CPS: SAN encoded in modulo80' field value is displayed as:"+ CPSSANEncodedValue);
		scrolltoend();
		if(RingToNumber_Checkbox.equalsIgnoreCase("YES"))
		{
			addCheckbox_commonMethod(application, "ringtonumber_radiobutton", "'Ring To Number' radio", RingToNumber_Checkbox, "no", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "Ring To Number", "ringtonumber_field", ringtonumber, xml);
		}
		else if(AnnouncementToPlay_Checkbox.equalsIgnoreCase("YES"))
		{
			addCheckbox_commonMethod(application, "announcementtoplay_radiobutton", "'Announcement to play' radio", AnnouncementToPlay_Checkbox, "no", xml);
			Thread.sleep(1000);
			//Select announcement to play value from dropdown
			click_commonMethod(application, "Announcement To Play dropdown", "announcementtoplay_dropdown", xml);
			click_commonMethod(application, "Announcement To Play value", "announcementtoplay_dropdownvalue", xml);
		}
		else if(ComplexRouting_Checkbox.equalsIgnoreCase("YES"))
		{
			addCheckbox_commonMethod(application, "complexroute_radiobutton", "'Complex route' radio", ComplexRouting_Checkbox, "no", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "Routing for payphone", "routingforpayphone_field", routingforpayphone_value, xml);
			addtextFields_commonMethod(application, "Routing for mobile", "routingformobile", routingformobile_value, xml);
			addtextFields_commonMethod(application, "Default Routing", "defaultrouting", defaultrouting_value, xml);
			addCheckbox_commonMethod(application, "enablelogicalrouting", "Enable logical routing checkbox", enablelogicalrouting_Checkbox, "no", xml);
			addtextFields_commonMethod(application, "Default Route busy", "defaultroutebusy", defaultroutebusy_value, xml);
			addtextFields_commonMethod(application, "No Answer", "noanswer", noanswer_value, xml);
			addtextFields_commonMethod(application, "Network Congestion", "networkcongestion", networkcongestion, xml);
		}
		addCheckbox_commonMethod(application, "enablepriceannouncement", "Enable Price Announcement", enablepriceannouncement_checkbox, "no", xml);
		ScrolltoElement(application, "addsan_addbutton", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Add", "addsan_addbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
		{
			Log.info("Navigated to view SAN page");
			verifysuccessmessage(application, "SAN successfully created");
			scrolltoend();
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			waitforPagetobeenable();
		}
		else
		{
			Log.info("SAN not created");
		}

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
		}
		else
		{
			Log.info("Not navigated to view service page");
		}
		//Added Customer
		scrolltoend();
		WebElement SANGridCheck= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid= SANGridCheck.getAttribute("style");
		WebElement AddedSAN= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + SAN_Customername + "')]/parent::div//span[contains(@class,'unchecked')]");
		if(!SANGrid.contains("height: 1px"))
		{
			Clickon(AddedSAN);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);
			List<WebElement> SANLinks= getwebelements("//div[contains(text(),'SAN/FRC')]/following-sibling::div//div//a");
			int SANLinksCount= SANLinks.size();
			for(int i=0;i<SANLinksCount;i++)
			{
				String Link= SANLinks.get(i).getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, ""+Link+" link is displaying under SAN/FRC panel");
				Log.info(""+Link+" link is displaying under SAN/FRC panel");
				System.out.println("Customer link:"+ Link + " is displaying");
				Thread.sleep(2000);
			}
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/sanheader")));

			//Added Customer grid verification
			WebElement AddedSan= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + SANNumberValue + "')]");
			String AddedSAN_FRCNumber = AddedSan.getText();
			Log.info("FRC Number for Added SAN is displayed as : " + AddedSAN_FRCNumber);
			System.out.println("FRC Number for Added SAN: "+ AddedSAN_FRCNumber);
			sa.assertEquals(AddedSAN_FRCNumber,SANNumberValue);
			ExtentTestManager.getTest().log(LogStatus.PASS, "FRC Number for Added SAN is displayed as : " + AddedSAN_FRCNumber);

			WebElement AddedSan1= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + SAN_Customername + "')]");
			String AddedSAN_Customervalue = AddedSan1.getText();
			Log.info("Customer Name for Added SAN is displayed as : " + AddedSAN_Customervalue);
			System.out.println("Customer Name for Added SAN: "+ AddedSAN_Customervalue);
			sa.assertEquals(AddedSAN_Customervalue,SAN_Customername);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Customer Name for Added SAN is displayed as : " + AddedSAN_Customervalue);

		}

		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}

	}

	public void verifyEditSAN(String application, String customernamevalue, String sannumbervalue, String edit_serviceprofilevalue, String supervisionfieldvalue, String edit_supervisionfieldvalue, String maxcallduration, String chargebandname, String predestinationnumber, String ringtonumber, String routingforpayphone_value, String routingformobile_value, String defaultrouting_value, String RingToNumber_Checkbox, String AnnouncementToPlay_Checkbox, String ComplexRouting_Checkbox, String defaultroutebusy_value, String noanswer_value, String networkcongestion, String internationaloutgoingcalls_checkbox, String internationalincomingcalls_checkbox, String mobilecallsallowed_checkbox, String payphoneblocking_checkbox, String noreplytimervalue, String webaccessblocked_checkbox, String cpsfreeformatvalue, String enablepriceannouncement_checkbox, String select_sansearchtype, String edit_enablepriceannouncement_checkbox, String edit_chargebandname, String edit_internationaloutgoingcalls_checkbox, String edit_internationalincomingcalls_checkbox, String edit_mobilecallsallowed_checkbox, String edit_noreplytimervalue, String edit_maxcallduration, String edit_payphoneblocking_checkbox, String edit_webaccessblocked_checkbox, String edit_sanblock_checkbox, String edit_focenabled_checkbox, String edit_ringtonumber_checkbox, String edit_announcementtoplay_checkbox, String edit_complexroute_checkbox, String edit_predestinationnumber) throws InterruptedException, DocumentException, IOException {

		//Edit SAN link in View SAN page
		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		edittextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid= SANGridCheck.getAttribute("style");
		WebElement AddedSAN= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + customernamevalue + "')]/parent::div//span[contains(@class,'unchecked')]");

		if(!SANGrid.contains("height: 1px"))
		{
			Clickon(AddedSAN);
			Thread.sleep(1000);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "Edit SAN", "editsan_link", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			scrollToTop();
			compareText(application, "Edit SAN Header", "editsan_header", "Edit SAN", xml);

			//verify Customer Name field
			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_customername")).getAttribute("readonly")!=null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name field is disabled as expected");
				Log.info("Customer Name field is disabled as expected");
				String CustomerNamevalue= getwebelement(xml.getlocator("//locators/" + application + "/editsan_customername")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name field value is displayed as:"+ CustomerNamevalue);
				Log.info("Customer Name field value is displayed as:"+ CustomerNamevalue);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Customer Name field is enabled");
				Log.info("Customer Name field is enabled");
			}

			//verify SAN Number field
			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_sannumber")).getAttribute("readonly")!=null)
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Number field is disabled as expected");
				Log.info("SAN Number field is disabled as expected");
				String SANNumbervalue= getwebelement(xml.getlocator("//locators/" + application + "/editsan_sannumber")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Number field value is displayed as:"+ SANNumbervalue);
				Log.info("SAN Number field value is displayed as:"+ SANNumbervalue);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : SAN Number field is enabled");
				Log.info("SAN Number field is enabled");
			}

			//Select service profile from dropdown
			addDropdownValues_commonMethod(application, "Service Profile", "serviceprofile", edit_serviceprofilevalue, xml);
			Thread.sleep(1000);
			addDropdownValues_commonMethod(application, "Supervision mode", "supervisionfield", edit_supervisionfieldvalue, xml);

			editcheckbox_commonMethod(application, edit_internationaloutgoingcalls_checkbox, "internationaloutgoingforbidden_checkbox", "International Outgoing Calls Forbidden", xml);
			editcheckbox_commonMethod(application, edit_internationalincomingcalls_checkbox, "internationalincomingbarring", "International Incoming Calls Barring", xml);
			editcheckbox_commonMethod(application, edit_mobilecallsallowed_checkbox, "mobilecallsallowed_checkbox", "Mobile Calls Allowed", xml);
			Thread.sleep(2000);
			cleartext(application, "No reply timer value", "noreplytimervalue");
			edittextFields_commonMethod(application, "No reply timer value", "noreplytimervalue", edit_noreplytimervalue, xml);
			edittextFields_commonMethod(application, "Max call duration", "maxcallduration", edit_maxcallduration, xml);
			addDropdownValues_commonMethod(application, "Charge Band Name", "chargebandname", edit_chargebandname, xml);
			editcheckbox_commonMethod(application, edit_payphoneblocking_checkbox, "payphoneblockingenabled", "Pay phone blocking enabled", xml);
			editcheckbox_commonMethod(application, edit_webaccessblocked_checkbox, "webaccessblocked", "Web Access Blocked", xml);
			ScrolltoElement(application, "sanblock", xml);
			editcheckbox_commonMethod(application, edit_sanblock_checkbox, "sanblock", "SAN Block", xml);
			editcheckbox_commonMethod(application, edit_focenabled_checkbox, "focenabled", "FOC Enabled", xml);
			edittextFields_commonMethod(application, "Pre destination number", "predestinationnumber", edit_predestinationnumber, xml);
			
			String CPSFreeFormatValue= getwebelement(xml.getlocator("//locators/" + application + "/cpsfreeformat")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : CPS Free Format field value is displayed as:"+ CPSFreeFormatValue);
			String CPSSANEncodedValue= getwebelement(xml.getlocator("//locators/" + application + "/cpssanencoded")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : 'CPS: SAN encoded in modulo80' field value is displayed as:"+ CPSSANEncodedValue);

			if(RingToNumber_Checkbox.equalsIgnoreCase("YES"))
			{
				editcheckbox_commonMethod(application, edit_ringtonumber_checkbox, "ringtonumber_radiobutton", "'Ring To Number' radio", xml);
				click_commonMethod(application, "'Ring To Number' radio", "ringtonumber_radiobutton", xml);
				Thread.sleep(1000);
				edittextFields_commonMethod(application, "Ring To Number", "ringtonumber_field", ringtonumber, xml);
			}
			else if(AnnouncementToPlay_Checkbox.equalsIgnoreCase("YES"))
			{
				editcheckbox_commonMethod(application, edit_announcementtoplay_checkbox, "announcementtoplay_radiobutton", "'Announcement to play' radio", xml);
				Thread.sleep(1000);
				//Select announcement to play value from dropdown
				click_commonMethod(application, "Announcement To Play dropdown", "announcementtoplay_dropdown", xml);
				click_commonMethod(application, "Announcement To Play value", "announcementtoplay_dropdownvalue", xml);
			}
			else if(ComplexRouting_Checkbox.equalsIgnoreCase("YES"))
			{
				editcheckbox_commonMethod(application, edit_complexroute_checkbox, "complexroute_radiobutton", "'Complex route' radio", xml);
				Thread.sleep(1000);
				edittextFields_commonMethod(application, "Routing for payphone", "routingforpayphone_field", routingforpayphone_value, xml);
				edittextFields_commonMethod(application, "Routing for mobile", "routingformobile", routingformobile_value, xml);
				edittextFields_commonMethod(application, "Default Routing", "defaultrouting", defaultrouting_value, xml);
				click_commonMethod(application, "Enable logical routing checkbox", "enablelogicalrouting", xml);
				edittextFields_commonMethod(application, "Default Route busy", "defaultroutebusy", defaultroutebusy_value, xml);
				edittextFields_commonMethod(application, "No Answer", "noanswer", noanswer_value, xml);
				edittextFields_commonMethod(application, "Network Congestion", "networkcongestion", networkcongestion, xml);
			
			}

				editcheckbox_commonMethod(application, edit_enablepriceannouncement_checkbox, "enablepriceannouncement", "Enable Price Announcement", xml);
				Thread.sleep(1000);
			
				ScrolltoElement(application, "addsan_addbutton", xml);
				Thread.sleep(1000);
			click_commonMethod(application, "Add", "addsan_addbutton", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
			{
				Log.info("Navigated to view SAN page");
				ScrolltoElement(application, "editsan_successmsg", xml);
				waitforPagetobeenable();
				verifysuccessmessage(application, "San successfully updated");
				Thread.sleep(1000);
				ScrolltoElement(application, "viewpage_backbutton", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
					Log.info("Didn't navigate to view service page");
				}

			}
			else
			{
				Log.info("SAN not updated");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: SAN not updated");
			}

		}
		else
		{
			Log.info("SAN is not added in the grid");
		}
	}
	
	public void verifyViewSAN(String application, String customernamevalue, String sannumbervalue, String serviceprofilevalue, String supervisionfieldvalue, String maxcallduration, String predestinationnumber, String ringtonumber, String noreplytimervalue, String select_sansearchtype) throws InterruptedException, DocumentException, IOException {

		//View SAN link in View SAN page
		ScrolltoElement(application, "sanheader", xml);
		Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid= SANGridCheck.getAttribute("style");
		WebElement AddedSAN= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + customernamevalue + "')]/parent::div//span[contains(@class,'unchecked')]");

		if(!SANGrid.contains("height: 1px"))
		{
			Clickon(AddedSAN);
			Thread.sleep(1000);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "View SAN link", "viewsan_link", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			scrollToTop();
			compareText(application, "View SAN Header", "viewsan_header", "View SAN", xml);
			compareText(application, "Customer Name", "viewsan_customername", customernamevalue, xml);
			compareText(application, "SAN/FRC Number", "viewsan_sannumber", sannumbervalue, xml);
			compareText(application, "Service Profile", "serviceprofilevalue", serviceprofilevalue, xml);
			compareText(application, "Supervision Mode", "supervisionmodevalue", supervisionfieldvalue, xml);
			GetText(application, "International Outgoing Calls Forbidden", "internationaloutgoingcallsvalue");
			GetText(application, "International Incoming Calls Barring", "internationalincomingcallsvalue");
			GetText(application, "Mobile Calls Allowed", "mobilecallsallowedvalue");
			ScrolltoElement(application, "view_noreplytimervalue", xml);
			GetText(application, "No Reply Timer Value", "view_noreplytimervalue");
			GetText(application, "Max Call Duration", "maxcalldurationvalue");
			GetText(application, "Charge Band Name", "chargebandnamevalue");
			GetText(application, "Pay phone blocking enabled", "payphoneblockingenabledvalue");
			GetText(application, "webAccessBlocked", "payphoneblockingenabledvalue");
			GetText(application, "SAN Block", "sanblockvalue");
			ScrolltoElement(application, "focenabledvalue", xml);
			GetText(application, "FOC Enabled", "focenabledvalue");
			GetText(application, "Predestination number", "predestinationnumbervalue");
			GetText(application, "CPS Free Format", "CPSvalue");
			compareText(application, "Ring To Number", "ringtonumbervalue", ringtonumber, xml);
			GetText(application, "Announcement to play", "announcementtoplay");
			GetText(application, "Tree name", "treenamevalue");

			//Edit SAN link in view SAN page
			Thread.sleep(2000);
			scrollToTop();
			click_commonMethod(application, "Action dropdown", "viewsan_actiondropdown", xml);
			click_commonMethod(application, "Edit SAN", "editsan_link", xml);
			waitforPagetobeenable();
			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_header")).isDisplayed())
			{
				compareText(application, "Edit SAN Header", "editsan_header", "Edit SAN", xml);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to Edit SAN page");
				Log.info("Navigated to Edit SAN page");
				ScrolltoElement(application, "cancelbutton", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Cancel", "cancelbutton", xml);
				waitforPagetobeenable();
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to view service page");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
					Log.info("Didn't navigate to view service page");
				}
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to Edit SAN page");
				Log.info("Didn't navigate to Edit SAN page");
			}
		}
		else
		{
			Log.info("SAN is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: SAN is not added in the grid");
		}

		//Add Another SAN link in view SAN page
		ScrolltoElement(application, "sanheader", xml);
		Thread.sleep(2000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck2= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid2= SANGridCheck2.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN2= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!SANGrid2.contains("height: 1px"))
		{
			Clickon(AddedSAN2);
			Thread.sleep(1000);

			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "View SAN link", "viewsan_link", xml);
			waitforPagetobeenable();
			compareText(application, "View SAN Header", "viewsan_header", "View SAN", xml);
			scrollToTop();
			click_commonMethod(application, "Action dropdown", "viewsan_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Add Another SAN", "addanothersanlink", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			if(getwebelement(xml.getlocator("//locators/" + application + "/addanothersan_header")).isDisplayed())
			{
				GetText(application,  "Add another SAN Header", "addanothersan_header");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to Add SAN page");
				Log.info("Navigated to Add SAN page");
				ScrolltoElement(application, "cancelbutton", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Cancel", "cancelbutton", xml);
				waitforPagetobeenable();
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to view service page");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
					Log.info("Didn't navigate to view service page");
				}
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Didn't navigate to Add SAN page");
				Log.info("Didn't navigate to Add SAN page");
			}

		}
		else
		{
			Log.info("SAN is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: SAN is not added in the grid");
		}

		//Delete SAN link in View SAN page
		ScrolltoElement(application, "sanheader", xml);
		Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck3= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid3= SANGridCheck3.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN3= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!SANGrid3.contains("height: 1px"))
		{
			Clickon(AddedSAN3);
			Thread.sleep(1000);

			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "View SAN", "viewsan_link", xml);
			waitforPagetobeenable();
			scrollToTop();
			compareText(application, "View SAN Header", "viewsan_header", "View SAN", xml);
			click_commonMethod(application, "Action dropdown", "viewsan_actiondropdown", xml);
			click_commonMethod(application, "Delete SAN", "deletesan_link", xml);
			waitforPagetobeenable();
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Delete alert popup is displayed as expected");
				Log.info("Delete alert popup is displayed as expected");
				click_commonMethod(application, "Delete alert close", "deletealertclose", xml);
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Delete alert popup is not displayed");
				Log.info("Delete alert popup is not displayed");
			}
			Thread.sleep(1000);
			ScrolltoElement(application, "viewpage_backbutton", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
			}
		}
		else
		{
			Log.info("SAN is not added in the grid");
		}
		
	}
			
	public void verifyPortIn(String application, String customernamevalue, String sannumbervalue, String select_sansearchtype, String portinnumber, String cancelporting) throws InterruptedException, DocumentException, IOException {
		
		ScrolltoElement(application, "sanheader", xml);
		Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		edittextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck4= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid4= SANGridCheck4.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN4= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!SANGrid4.contains("height: 1px"))
		{
			Clickon(AddedSAN4);
			Thread.sleep(1000);

			compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "PortIN SAN link", "portin_link", xml);
			waitforPagetobeenable();
			WebElement Port_Dialog= getwebelement(xml.getlocator("//locators/" + application + "/portin_dialog"));
			if(Port_Dialog.isDisplayed())
			{
				compareText(application, "Customer Name", "Port_Customername", customernamevalue, xml);
				compareText(application, "SAN/FRC Number", "Port_SanNumber", sannumbervalue, xml);

				String PortToNumber= getwebelement(xml.getlocator("//locators/" + application + "/porttonumber")).getAttribute("value");
				if(PortToNumber!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: PortIN Number is displayed as:" +PortToNumber);
					Log.info("PortIN Number is displayed as:" +PortToNumber);
				}
				else
				{
					addtextFields_commonMethod(application, "Port To Number", "porttonumber", portinnumber, xml);
				}

				try {
					WebElement WebAccessBlocked_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/webaccessblocked_checkbox"));
					if (WebAccessBlocked_Checkbox.isEnabled())
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Web access blocked checkbox is checked as expected");
						Log.info("Web access blocked checkbox is checked as expected");
					}
					else
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
					Log.info("Web access blocked checkbox is not checked");
				}
				catch (Exception e) {
					// TODO: handle exception
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
					Log.info("Web access blocked checkbox is not checked");
				}

				try {
					String PortDate= getwebelement(xml.getlocator("//locators/" + application + "/portdate")).getAttribute("value");
					if(PortDate!= null)
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Port Date value is: '"+PortDate+"'");
						Log.info("Port Date value is: '"+PortDate+"'");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Port Date value is not displaying");
						Log.info("Port Date value is not displaying");
					}
				}
				catch (Exception e) {
					// TODO: handle exception
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Port Date value is not displaying");
					Log.info("Port Date value is not displaying");
				}

				//Port Time
				DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
				LocalDateTime ldt= LocalDateTime.now().plusHours(1).plusMinutes(10).plusSeconds(20);
				String FutureTime= ldt.format(formatter).substring(10).trim();
				System.out.println("Port Time:"+FutureTime);
				addtextFields_commonMethod(application, "Port Time", "porttime", FutureTime, xml);
				click_commonMethod(application, "OK", "port_okbutton", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				GetText(application, "PortIN success message", "successmsg");
				
//				//porting status
//				String PortStatus= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/portingstatus")));
//				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Porting status is displayed as: "+PortStatus);	
//				Log.info("Porting status is displayed as: "+PortStatus);
//				if(cancelporting.equalsIgnoreCase("Yes"))
//				{
//					click_commonMethod(application, "Cancel Porting", "cancelporting", xml);
//					String CancelPort_message= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/port_errordisplay")));
//					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Cancel Porting status is displayed as: "+CancelPort_message);	
//					Log.info("Porting status is displayed as: "+PortStatus);	
//				}
//				click_commonMethod(application, "Close", "port_closesymbol", xml);
//				//compareText(application, "PortIN success message", "successmsg", "PortIN schedule is successful", xml);
//				Thread.sleep(2000);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortIN page is not opened");	
				Log.info("PortIN page is not opened");
			}

		}
		else
		{
			Log.info("SAN is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : SAN is not added in the grid");	
		}

	}
	
	public void verifyPortOut(String application, String customernamevalue, String sannumbervalue, String select_sansearchtype, String portoutnumber) throws InterruptedException, DocumentException, IOException {
		
		ScrolltoElement(application, "sanheader", xml);
		Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck5= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid5= SANGridCheck5.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN5= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!SANGrid5.contains("height: 1px"))
		{
			Clickon(AddedSAN5);
			Thread.sleep(1000);

			compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "PortOUT SAN link", "portout_link", xml);
			waitforPagetobeenable();
			WebElement Port_Dialog= getwebelement(xml.getlocator("//locators/" + application + "/portin_dialog"));
			if(Port_Dialog.isDisplayed())
			{
				compareText(application, "Customer Name", "Port_Customername", customernamevalue, xml);
				compareText(application, "SAN/FRC Number", "Port_SanNumber", sannumbervalue, xml);
				String PortToNumber= getwebelement(xml.getlocator("//locators/" + application + "/porttonumber")).getAttribute("value");
				if(PortToNumber!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: PortOUT Number is displayed as:" +PortToNumber);
					Log.info("PortOUT Number is displayed as:" +PortToNumber);
				}
				else
				{
					addtextFields_commonMethod(application, "Port To Number", "porttonumber", portoutnumber, xml);
				}


				try {
					String PortDate= getwebelement(xml.getlocator("//locators/" + application + "/portdate")).getAttribute("value");
					if(PortDate!= null)
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Port Date value is: '"+PortDate+"'");
						Log.info("Port Date value is: '"+PortDate+"'");
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Port Date value is not displaying");
						Log.info("Port Date value is not displaying");
					}
				}
				catch (Exception e) {
					// TODO: handle exception
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Port Date value is not displaying");
					Log.info("Port Date value is not displaying");
				}

				//Port Time
//				DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
//				LocalDateTime ldt= LocalDateTime.now().plusHours(1).plusMinutes(10).plusSeconds(20);
//				String FutureTime= ldt.format(formatter).substring(10).trim();
//				System.out.println("Port Time:"+FutureTime);
//				addtextFields_commonMethod(application, "Port Time", "porttime", FutureTime, xml);
				
				click_commonMethod(application, "Close", "port_closesymbol", xml);
//				click_commonMethod(application, "OK", "port_okbutton", xml);
//				GetText(application, "PortOUT success message", "successmsg");
//				//porting status
//				try {
//					String PortStatus= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/portingstatus")));
//					if(PortStatus.equalsIgnoreCase("Ported"))
//					{
//						ExtentTestManager.getTest().log(LogStatus.PASS, "Step : PortOUT is successful");
//						Log.info("PortOUT is successful");
//					}
//					else
//					{
//						Log.info("PortOUT is not successful");
//						ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortOUT is not successful");
//						String PortError= getwebelement(xml.getlocator("//locators/" + application + "/port_errordisplay")).getText();
//						ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Error is displayed as:" +PortError);
//						Log.info("Error is displayed as:" +PortError);
//					}
//				}
//				catch (Exception e) {
//					// TODO: handle exception
//					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortOUT is unsuccessful");
//					Log.info("PortOUT is unsuccessful");
//				}
//
//				Thread.sleep(1000);
//				click_commonMethod(application, "Close", "port_closesymbol", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : PortOut page is not opened");
				Log.info("PortOut page is not opened");
			}
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : SAN is not added in the grid");
		}

	}
	
	public void verifyManageAddnlFRC(String application, String sid, String customernamevalue, String sannumbervalue1, String select_sansearchtype, String enablepriceannouncement_checkbox, String priceannouncementvalue, String priceannoriginvalue, String interruptiblepriceannouncement_checkbox, String valueinprice, String sendfci_checkbox, String sendsci_checkbox, String enablecallerconfirmation_checkbox, String callerconfirmationannouncementvalue, String callerconfirmationdigitvalue, String numberofrepetitionsallowedvalue, String edit_interruptiblepriceannouncement_checkbox, String edit_enablepriceannouncement_checkbox, String edit_valueinprice, String edit_sendfci_checkbox, String edit_sendsci_checkbox, String edit_enablecallerconfirmation_checkbox, String edit_callerconfirmationannouncementvalue, String edit_callerconfirmationdigitvalue, String edit_numberofrepetitionsallowedvalue, String chargebandname, String edit_chargebandname, String edit_priceannouncementvalue, String edit_priceannoriginvalue, String country, String resellername, String defaultvalue, String configure, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode, String ringtonumber, String routingforpayphone_value, String routingformobile_value, String defaultrouting_value, String RingToNumber_Checkbox, String AnnouncementToPlay_Checkbox, String ComplexRouting_Checkbox, String defaultroutebusy_value, String noanswer_value, String networkcongestion, String serviceprofilevalue) throws InterruptedException, DocumentException, IOException {
		
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(1000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));
		Thread.sleep(2000);
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
		Thread.sleep(1000);

		WebElement searchbutton1= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
		ScrolltoElement(application, "searchbutton", xml);
		Clickon(searchbutton1);
		Thread.sleep(2000);
		waitforPagetobeenable();
		ScrolltoElement(application, "searchbutton", xml);
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
		Thread.sleep(3000);
		waitforPagetobeenable();
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		
		//==============Add SAN to perform bulk move======

				click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);	
				click_commonMethod(application, "Add SAN", "addsan_link", xml);
				waitforPagetobeenable();
				scrollToTop();
				compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);

				addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
				click_commonMethod(application, "Customer Name dropdown", "customername", xml);
				Thread.sleep(1000);

				if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_valuesdisplay")).isDisplayed())
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Existing customer is available");
					Log.info("Existing customer is available");
					click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
				}
				else if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_nomatchesfound")).isDisplayed())
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Existing customer is not available");
					Log.info("Existing customer is not available");
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")));

					//Manage new customer link
					click_commonMethod(application, "Manage new customer", "managenewcustomer_link", xml);
					waitforPagetobeenable();
					compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
					Thread.sleep(1000);

					//=========================

					//Add Customer
					//Select Country from dropdown
					addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
					String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
					String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
					CustomerNamevalue.replace("(New)", "").trim();
					addtextFields_commonMethod(application, "Reseller Name", "resellername", resellername, xml);
					GetText(application, "Email", "reseller_email");
					addtextFields_commonMethod(application, "City", "reseller_city", city, xml);
					addtextFields_commonMethod(application, "Street Name", "reseller_streetname", streetname, xml);
					addtextFields_commonMethod(application, "Street Number", "reseller_streetno", streetno, xml);
					addtextFields_commonMethod(application, "PO Box", "reseller_pobox", pobox, xml);
					addtextFields_commonMethod(application, "Zip Code", "reseller_zipcode", zipcode, xml);
					GetText(application, "Phone", "reseller_phone");
					GetText(application, "Fax", "reseller_fax");

					if(defaultvalue.equalsIgnoreCase("YES"))
					{
						String Default_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/defaultcheckbox")).getAttribute("checked");
						if(Default_Checkbox.isEmpty())
						{
							Thread.sleep(1000);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Default checkbox is checked");
							Log.info("Default checkbox is checked");
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Default checkbox is not checked by default");
							Log.info("Default checkbox is not checked by default");
						}
					}

					else if(configure.equalsIgnoreCase("YES"))
					{
						String Configure_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/configurecheckbox")).getAttribute("checked");
						if(Configure_Checkbox.isEmpty())
						{
							Thread.sleep(1000);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Configure checkbox is checked");
							Log.info("Configure checkbox is checked");
						}
						else
						{
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Configure checkbox is not checked");
							Log.info("Configure checkbox is not checked");
							click_commonMethod(application, "Configure checkbox", "configurecheckbox", xml);
						}
					}
					ScrolltoElement(application, "nextbutton", xml);
					Thread.sleep(1000);
					click_commonMethod(application, "Next", "nextbutton", xml);
					Thread.sleep(3000);	
					waitforPagetobeenable();
					ScrolltoElement(application, "addsan_header", xml);
					compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);
					Thread.sleep(1000);

					//Select Country from dropdown
					addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
					click_commonMethod(application, "Customer Name dropdown", "customername", xml);
					Thread.sleep(1000);
					click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
				}

				Thread.sleep(1000);
				//SAN customer name
				String SAN_Customernamevalue= getwebelement(xml.getlocator("//locators/" + application + "/customername_selectedtext")).getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step :Customer name selected is : " + SAN_Customernamevalue);
				String SAN_Customername=SAN_Customernamevalue.substring(0, SAN_Customernamevalue.indexOf("(")).trim();
				writetoexcel("src\\com\\colt\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 162, SAN_Customername);

				String SANNumber_CountryCode=getwebelement(xml.getlocator("//locators/" + application + "/san_number")).getAttribute("value");
				addtextFields_commonMethod(application, "SAN Number", "san_number", sannumbervalue1, xml);
				//SAN number
				String SANNumberValue1= SANNumber_CountryCode+sannumbervalue1;
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step :SAN Number is dsplayed as : " + SANNumberValue1);

				//Select service profile from dropdown
				addDropdownValues_commonMethod(application, "Service Profile", "serviceprofile", serviceprofilevalue, xml);
				Thread.sleep(1000);
				ScrolltoElement(application, "serviceprofile", xml);
				Thread.sleep(1000);
				if(RingToNumber_Checkbox.equalsIgnoreCase("YES"))
				{
					click_commonMethod(application, "'Ring To Number' radio", "ringtonumber_radiobutton", xml);
					Thread.sleep(1000);
					addtextFields_commonMethod(application, "Ring To Number", "ringtonumber_field", ringtonumber, xml);
				}
				else if(AnnouncementToPlay_Checkbox.equalsIgnoreCase("YES"))
				{
					click_commonMethod(application, "'Announcement to play' radio", "announcementtoplay_radiobutton", xml);
					Thread.sleep(1000);
					//Select announcement to play value from dropdown
					click_commonMethod(application, "Announcement To Play dropdown", "announcementtoplay_dropdown", xml);
					click_commonMethod(application, "Announcement To Play value", "announcementtoplay_dropdownvalue", xml);
				}
				else if(ComplexRouting_Checkbox.equalsIgnoreCase("YES"))
				{
					click_commonMethod(application, "'Complex route' radio", "complexroute_radiobutton", xml);
					Thread.sleep(1000);
					addtextFields_commonMethod(application, "Routing for payphone", "routingforpayphone_field", routingforpayphone_value, xml);
					addtextFields_commonMethod(application, "Routing for mobile", "routingformobile", routingformobile_value, xml);
					addtextFields_commonMethod(application, "Default Routing", "defaultrouting", defaultrouting_value, xml);
					click_commonMethod(application, "Enable logical routing checkbox", "enablelogicalrouting", xml);
					addtextFields_commonMethod(application, "Default Route busy", "defaultroutebusy", defaultroutebusy_value, xml);
					addtextFields_commonMethod(application, "No Answer", "noanswer", noanswer_value, xml);
					addtextFields_commonMethod(application, "Network Congestion", "networkcongestion", networkcongestion, xml);
				}
				ScrolltoElement(application, "addsan_addbutton", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Add", "addsan_addbutton", xml);
				waitforPagetobeenable();
				if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
				{
					Log.info("Navigated to view SAN page");
					ScrolltoElement(application, "viewpage_backbutton", xml);
					Thread.sleep(1000);
					click_commonMethod(application, "Back", "viewpage_backbutton", xml);
					waitforPagetobeenable();
				}
				else
				{
					Log.info("SAN not created");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : SAN not created");
				}

				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to view service page");
				}
				else
				{
					Log.info("Not navigated to view service page");
					System.out.println("Not navigated to view service page");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Not navigated to view service page");
				}

				//===========End of Add SAN=========

		//SANFilter
				ScrolltoElement(application, "sanheader", xml);
				Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", SANNumberValue1, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck10= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid10= SANGridCheck10.getAttribute("style");
		System.out.println("SAN Number displaying: " +SANNumberValue1);
		WebElement AddedSAN10= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+SANNumberValue1+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!SANGrid10.contains("height: 1px"))
		{
			Clickon(AddedSAN10);
			Thread.sleep(1000);
			compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "Manage Addnl FRC", "manageaddnlfrc_link", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			compareText(application, "Manage Addnl FRC Header", "manageaddnlfrc_header", "Manage Addnl FRC", xml);
			click_commonMethod(application, "Action Dropdown", "manageaddnlfrc_actiondropdown", xml);
			click_commonMethod(application, "Add Addnl FRC", "addaddnlfrc_link", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			compareText(application, "Add Price Announcement Header", "addpriceannouncement_header", "Add Price Announcement", xml);
			compareText(application, "SAN/FRC", "sanfrcvalue", SANNumberValue1, xml);
			compareText(application, "Customer Name", "customernamevalue", customernamevalue, xml);
			addCheckbox_commonMethod(application, "enablepriceannouncement_checkbox", "Enable price announcement", enablepriceannouncement_checkbox, "no", xml);
			addDropdownValues_commonMethod(application, "Price Announcement", "priceannouncement_dropdown", priceannouncementvalue, xml);
			//addDropdownValues_commonMethod(application, "Price Ann Origin", "priceannorigin_dropdown", priceannoriginvalue, xml);
			
			boolean availability=false;
			try {  
			  availability=getwebelement(xml.getlocator("//locators/" + application + "/priceannorigin_dropdown")).isDisplayed();
			  if(availability) {
				  ExtentTestManager.getTest().log(LogStatus.PASS, "Price Ann Origin dropdown is displaying");
				  System.out.println("Price Ann Origin dropdown is displaying");
				  
				  if(priceannoriginvalue.equalsIgnoreCase("null")) {
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Price Ann Origin dropdown");
					  System.out.println(" No values selected under Price Ann Origin dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='Price Ann Origin']]//div[text()='×']"));
					  Thread.sleep(3000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside Price Ann Origin dropdown is:  ");
					  System.out.println( " List of values inside Price Ann Origin dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
									System.out.println(" " + valuetypes.getText());
						}
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='Price Ann Origin']]//input"), priceannoriginvalue);	
					Thread.sleep(2000);
						
					  Clickon(getwebelement("(//div[@class='modal-body']//div[contains(text(),'"+ priceannoriginvalue +"')])[1]"));
					  Thread.sleep(3000);
					  
					  String actualValue=getwebelement("//label[text()='Price Ann Origin']/following-sibling::div//span").getText();
					  ExtentTestManager.getTest().log(LogStatus.PASS, "Price Ann Origin dropdown value selected as: "+ actualValue );
					  
				  }
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, "Price Ann Origin is not displaying");
			  }
			}catch(NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Price Ann Origin is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under Price Ann Origin dropdown");
			}
		
			addCheckbox_commonMethod(application, "interruptible_checkbox", "Interruptable Price Announcement", interruptiblepriceannouncement_checkbox, "no", xml);
			addtextFields_commonMethod(application, "Value In Price In(Cents)", "valueinprice_field", valueinprice, xml);
			addCheckbox_commonMethod(application, "sendfci_checkbox", "Send FCI", sendfci_checkbox, "yes", xml);
			addCheckbox_commonMethod(application, "sendsci_checkbox", "Send SCI", sendsci_checkbox, "yes", xml);
			addDropdownValues_commonMethod(application, "Charge Band Name", "addfrc_chargebandname", chargebandname, xml);
			addCheckbox_commonMethod(application, "enablecallerconfirmation_checkbox", "Enable Caller Confirmation", enablecallerconfirmation_checkbox, "no", xml);
			addDropdownValues_commonMethod(application, "Caller Confirmation Announcement", "callerconfirmationannouncement_dropdown", callerconfirmationannouncementvalue, xml);
			addtextFields_commonMethod(application, "Caller Confirmation Digit", "callerconfirmationdigit_field", callerconfirmationdigitvalue, xml);
			addtextFields_commonMethod(application, "Number Of Repetation Allowed", "noofrepetitionallowed_field", numberofrepetitionsallowedvalue, xml);
			Thread.sleep(2000);
			ScrolltoElement(application, "OkButton", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "OkButton", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Addnl Frc successfully created.");
			compareText(application, "Manage Addnl FRC Header", "manageaddnlfrc_header", "Manage Addnl FRC", xml);

			//verify table data in manage addnl frc page
			compareText(application, "FRC Number column", "manageaddnlfrc_frcnumber_column", "FRC Number", xml);
			compareText(application, "Price Ann Flag column", "manageaddnlfrc_priceannflag_column", "Price Ann Flag", xml);
			compareText(application, "Price Ann Origin column", "manageaddnlfrc_priceannorigin_column", "Price Ann Origin", xml);
			compareText(application, "Price Announcement column", "manageaddnlfrc_priceannouncement_column", "Price Announcement", xml);
			compareText(application, "FRC Number value", "manageaddnlfrc_frcnumber_value", SANNumberValue1, xml);
			String PriceAnnFlagvalue= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_priceannflag_value")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Price Ann Flag value is displayed as: " +PriceAnnFlagvalue);
			compareText(application, "Price Ann Origin value", "manageaddnlfrc_priceannorigin_value", priceannoriginvalue, xml);
			compareText(application, "Price Announcement value", "manageaddnlfrc_priceannouncement_value", priceannouncementvalue, xml);
			WebElement priceannouncement= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_priceannouncement_value"));
			Clickon(priceannouncement);
			priceannouncement.sendKeys(Keys.TAB);
			WebElement maxcallflag= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_maxcallflag_value"));
			Clickon(maxcallflag);
			maxcallflag.sendKeys(Keys.TAB);
			WebElement maxcallorigin= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_maxcallorigin_value"));
			Clickon(maxcallorigin);
			maxcallorigin.sendKeys(Keys.TAB);

			compareText(application, "Max Call Flag column", "manageaddnlfrc_maxcallflag_column", "Max Call Flag", xml);
			compareText(application, "Max Call Origin column", "manageaddnlfrc_maxcallorigin_column", "Max Call Origin", xml);
			compareText(application, "Max Call Duration column", "manageaddnlfrc_maxcallduration_column", "Max Call Duration", xml);
			String MaxCallFlagvalue= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_maxcallflag_value")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Max Call Flag value is displayed as: " +MaxCallFlagvalue);
			String MaxCallOriginvalue= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_maxcallorigin_value")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Max Call Origin value is displayed as: " +MaxCallOriginvalue);
			String MaxCallDurationvalue= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_maxcallduration_value")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Max Call Duration value is displayed as: " +MaxCallDurationvalue);
			Thread.sleep(1000);
			ScrolltoElement(application, "viewpage_backbutton", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			waitforPagetobeenable();
		}
		else
		{
			Log.info("SAN is not added in the grid");
		}

		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", SANNumberValue1, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck11= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid11= SANGridCheck11.getAttribute("style");
		WebElement AddedSAN11= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+SANNumberValue1+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!SANGrid11.contains("height: 1px"))
		{
			Clickon(AddedSAN11);
			Thread.sleep(1000);
			compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "Manage Addnl FRC", "manageaddnlfrc_link", xml);
			waitforPagetobeenable();
			compareText(application, "Manage Addnl FRC Header", "manageaddnlfrc_header", "Manage Addnl FRC", xml);

			//verify edit addnl frc
			WebElement AddnlFRCGridCheck= getwebelement("//div[@ref='eBodyContainer']");
			String AddnlFRCGrid= AddnlFRCGridCheck.getAttribute("style");
			WebElement AddedAddnlFRC= getwebelement("//div[@ref='eBodyContainer']//span[@class='ag-selection-checkbox']//span[2]");
			String AddedAddnlFRC_checkbox= AddedAddnlFRC.getAttribute("class");
			if(!AddnlFRCGrid.contains("height: 1px"))
			{
				if(AddedAddnlFRC_checkbox.equalsIgnoreCase("ag-icon ag-icon-checkbox-unchecked"))
				{
					Clickon(AddedAddnlFRC);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Added Addnl FRC is already selected");
					Log.info("Added Addnl FRC is already selected");
				}
				Thread.sleep(1000);
				click_commonMethod(application, "Action Dropdown", "manageaddnlfrc_actiondropdown", xml);
				click_commonMethod(application, "Edit", "edit", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				compareText(application, "Edit Price Announcement Header", "editpriceannouncement_header", "Edit Price Announcement", xml);
				compareText(application, "SAN/FRC", "sanfrcvalue", SANNumberValue1, xml);
				compareText(application, "Customer Name", "customernamevalue", customernamevalue, xml);
				addCheckbox_commonMethod(application, "enablepriceannouncement_checkbox", "Enable price announcement checkbox", edit_enablepriceannouncement_checkbox, "no", xml);
				addDropdownValues_commonMethod(application, "Price Announcement", "priceannouncement_dropdown", edit_priceannouncementvalue, xml);
				//addDropdownValues_commonMethod(application, "Price Ann Origin", "priceannorigin_dropdown", edit_priceannoriginvalue, xml);
				boolean availability=false;
				try {  
					availability=getwebelement(xml.getlocator("//locators/" + application + "/priceannorigin_dropdown")).isDisplayed();
					if(availability) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Price Ann Origin dropdown is displaying");
						System.out.println("Price Ann Origin dropdown is displaying");

						if(edit_priceannoriginvalue.equalsIgnoreCase("null")) {

							ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Price Ann Origin dropdown");
							System.out.println(" No values selected under Price Ann Origin dropdown");
						}else {

							Clickon(getwebelement("//div[label[text()='Price Ann Origin']]//div[text()='×']"));
							Thread.sleep(3000);

							//verify list of values inside dropdown
							List<WebElement> listofvalues = driver
									.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

							ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside Price Ann Origin dropdown is:  ");
							System.out.println( " List of values inside Price Ann Origin dropdown is:  ");

							for (WebElement valuetypes : listofvalues) {
								Log.info("service sub types : " + valuetypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
								System.out.println(" " + valuetypes.getText());
							}

							Thread.sleep(2000);
							SendKeys(getwebelement("//div[label[text()='Price Ann Origin']]//input"), edit_priceannoriginvalue);	
							Thread.sleep(2000);

							Clickon(getwebelement("(//div[contains(text(),'"+ edit_priceannoriginvalue +"')])[2]"));
							Thread.sleep(3000);

							String actualValue=getwebelement("//label[text()='Price Ann Origin']/following-sibling::div//span").getText();
							ExtentTestManager.getTest().log(LogStatus.PASS, "Price Ann Origin dropdown value selected as: "+ actualValue );

						}
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Price Ann Origin is not displaying");
					}
				}catch(NoSuchElementException e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Price Ann Origin is not displaying");
				}catch(Exception ee) {
					ee.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under Price Ann Origin dropdown");
				}
				editcheckbox_commonMethod(application, edit_interruptiblepriceannouncement_checkbox, "interruptible_checkbox", "Interruptible Price Announcement", xml);
				edittextFields_commonMethod(application, "Value In Price In(Cents)", "valueinprice_field", valueinprice, xml);
				editcheckbox_commonMethod(application, edit_sendfci_checkbox, "sendfci_checkbox", "Send FCI", xml);
				editcheckbox_commonMethod(application, edit_sendsci_checkbox, "sendsci_checkbox", "Send SCI", xml);
				addDropdownValues_commonMethod(application, "Charge Band Name", "addfrc_chargebandname", edit_chargebandname, xml);
				editcheckbox_commonMethod(application, edit_enablecallerconfirmation_checkbox, "enablecallerconfirmation_checkbox", "Enable Caller Confirmation", xml);
				addDropdownValues_commonMethod(application, "Caller Confirmation Announcement", "callerconfirmationannouncement_dropdown", edit_callerconfirmationannouncementvalue, xml);
				edittextFields_commonMethod(application, "Caller Confirmation Digit", "callerconfirmationdigit_field", edit_callerconfirmationdigitvalue, xml);
				edittextFields_commonMethod(application, "Number Of Repetation Allowed", "noofrepetitionallowed_field", numberofrepetitionsallowedvalue, xml);
				Thread.sleep(2000);
				ScrolltoElement(application, "OkButton", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "OK", "OkButton", xml);
				waitforPagetobeenable();
				verifysuccessmessage(application, "Addnl Frc successfully updated.");
			}
			else
			{
				Log.info("Addnl FRC is not added in the grid");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Addnl FRC is not added in the grid");
			}
			//verify view addnl frc
			WebElement AddnlFRCGridCheck1= getwebelement("//div[@ref='eBodyContainer']");
			String AddnlFRCGrid1= AddnlFRCGridCheck1.getAttribute("style");
			WebElement AddedAddnlFRC1= getwebelement("//div[@ref='eBodyContainer']//span[@class='ag-selection-checkbox']//span[2]");
			String AddedAddnlFRC1_checkbox= AddedAddnlFRC1.getAttribute("class");
			if(!AddnlFRCGrid1.contains("height: 1px"))
			{
				if(AddedAddnlFRC1_checkbox.equalsIgnoreCase("ag-icon ag-icon-checkbox-unchecked"))
				{
					Clickon(AddedAddnlFRC1);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Added Addnl FRC is already selected");
					Log.info("Added Addnl FRC is already selected");
				}
				Thread.sleep(1000);
				click_commonMethod(application, "Action Dropdown", "manageaddnlfrc_actiondropdown", xml);
				click_commonMethod(application, "View", "view", xml);
				Thread.sleep(2000);
				waitforPagetobeenable();
				compareText(application, "View Price Announcement Header", "viewpriceannouncement_header", "View Price Announcement", xml);
				compareText(application, "SAN/FRC", "sanfrcvalue", SANNumberValue1, xml);
				compareText(application, "Customer Name", "customernamevalue", customernamevalue, xml);

				String EnablePriceAnnouncementCheckbox_disabled= getwebelement(xml.getlocator("//locators/" + application + "/enablepriceannouncement_checkbox")).getAttribute("disabled");
				String EnablePriceAnnouncementCheckbox_checked= getwebelement(xml.getlocator("//locators/" + application + "/enablepriceannouncement_checkbox")).getAttribute("checked");
				if(EnablePriceAnnouncementCheckbox_disabled!=null && EnablePriceAnnouncementCheckbox_checked!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Enable Price Announcement checkbox is checked & disabled");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Enable Price Announcement checkbox is disabled");
				}

				if(getwebelement(xml.getlocator("//locators/" + application + "/priceannouncementvalue_viewpage")).isDisplayed())
				{
					GetText(application, "Price Announcement", "priceannouncementvalue_viewpage");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, " No value selected in Price Announcement dropdown");
				}
				if(getwebelement(xml.getlocator("//locators/" + application + "/priceannoriginvalue_viewpage")).isDisplayed())
				{
					GetText(application, "Price Ann Origin", "priceannoriginvalue_viewpage");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "No value selected in Price Ann Origin dropdown");
				}
				if(getwebelement(xml.getlocator("//locators/" + application + "/interruptible_checkbox")).getAttribute("checked")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Interruptible Price Announcement checkbox is checked");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Interruptible Price Announcement checkbox is not checked");
				}
				GetText(application, "Value In Price In(Cents)", "valueinprice_field");
				if(getwebelement(xml.getlocator("//locators/" + application + "/sendfci_checkbox")).getAttribute("checked")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Send FCI checkbox is checked");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Send FCI checkbox is not checked");
				}
				if(getwebelement(xml.getlocator("//locators/" + application + "/sendsci_checkbox")).getAttribute("checked")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Send SCI checkbox is checked");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Send SCI checkbox is not checked");
				}

				if(getwebelement(xml.getlocator("//locators/" + application + "/chargebandnamevalue_viewpage")).isDisplayed())
				{
					GetText(application, "Charge Band Name", "chargebandnamevalue_viewpage");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "No value selected in Charge Band Name dropdown");
				}
				if(getwebelement(xml.getlocator("//locators/" + application + "/enablecallerconfirmation_checkbox")).getAttribute("checked")!=null)
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Enable Caller Confirmation checkbox is checked");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Enable Caller Confirmation checkbox is not checked");
				}
				if(getwebelement(xml.getlocator("//locators/" + application + "/callerconfirmationannouncement_dropdown")).isDisplayed())
				{
					GetText(application, "Caller Confirmation Announcement", "callerconfirmationannouncement_dropdown");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, " No value selected in Caller Confirmation Announcement dropdown");
				}
				
				GetText(application, "Caller Confirmation Digit", "callerconfirmationdigit_field");
				GetText(application, "Number Of Repetation Allowed", "noofrepetitionallowed_field");
				Thread.sleep(2000);
				ScrolltoElement(application, "noofrepetitionallowed_field", xml);
				click_commonMethod(application, "Close", "viewaddnlfrc_closesymbol", xml);
				Thread.sleep(2000);

			}
			else
			{
				Log.info("Addnl FRC is not added in the grid");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Addnl FRC is not added in the grid");
			}

			//verify Delete addnl frc
			WebElement AddnlFRCGridCheck2= getwebelement("//div[@ref='eBodyContainer']");
			String AddnlFRCGrid2= AddnlFRCGridCheck2.getAttribute("style");
			WebElement AddedAddnlFRC2= getwebelement("//div[@ref='eBodyContainer']//span[@class='ag-selection-checkbox']//span[2]");
			String AddedAddnlFRC2_checkbox= AddedAddnlFRC2.getAttribute("class");
			if(!AddnlFRCGrid2.contains("height: 1px"))
			{
				if(AddedAddnlFRC2_checkbox.equalsIgnoreCase("ag-icon ag-icon-checkbox-unchecked"))
				{
					Clickon(AddedAddnlFRC2);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Added Addnl FRC is already selected");
					Log.info("Added Addnl FRC is already selected");
				}
				Thread.sleep(1000);
				click_commonMethod(application, "Action Dropdown", "manageaddnlfrc_actiondropdown", xml);
				click_commonMethod(application, "Delete Addnl FRC", "delete", xml);
				Thread.sleep(2000);
				WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
				if(DeleteAlertPopup.isDisplayed())
				{
					click_commonMethod(application, "Delete", "frc_deletebutton", xml);
					Thread.sleep(2000);
					verifysuccessmessage(application, "Addnl Frc successfully deleted.");
				}
				else
				{
					Log.info("Delete alert popup is not displayed");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
				}
				Thread.sleep(2000);
				ScrolltoElement(application, "viewpage_backbutton", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				waitforPagetobeenable();
				Thread.sleep(2000);
			}
			else
			{
				Log.info("Addnl FRC is not added in the grid");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Addnl FRC is not added in the grid");
			}
		}
		else
		{
			Log.info("SAN is not added in the grid");
		}
	}

	//====================================================================================

	public void verifySANMove(String application, String customernamevalue, String sannumbervalue, String select_sansearchtype, String destinationcustomername, String sanmove_orderno) throws InterruptedException, DocumentException, IOException {
		//SAN Move link under SAN panel in view service page

		//Cancel SAN Move
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck6= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid6= SANGridCheck6.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN6= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!SANGrid6.contains("height: 1px"))
		{
			Clickon(AddedSAN6);
			Thread.sleep(1000);
			compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "SAN Move", "sanmove_link", xml);
			waitforPagetobeenable();
			Thread.sleep(2000);
			compareText(application, "SAN Move Header", "sanmoveheader", "SAN Move", xml);
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Move is cancelled");
			Log.info("SAN Move is cancelled");
		}
		else
		{
			Log.info("SAN is not added in the grid");
		}
		//SAN Move
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck7= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid7= SANGridCheck7.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN7= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!SANGrid7.contains("height: 1px"))
		{
			Clickon(AddedSAN7);
			Thread.sleep(1000);
			compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "SAN Move", "sanmove_link", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			compareText(application, "SAN Move Header", "sanmoveheader", "SAN Move", xml);
			click_commonMethod(application, "Move", "movebutton_sanmove", xml);
			//warning message check
			warningMessage_commonMethod(application, "destinationcustomername_warningmsg", "Destination Customer name", xml);
			warningMessage_commonMethod(application, "orderservice_warningmsg", "Order/Service", xml);

			compareText(application, "Customer Name", "sanmove_customername", customernamevalue, xml);
			compareText(application, "SAN Number", "sanmove_sannumber", sannumbervalue, xml);
			addDropdownValues_commonMethod(application, "Destination Customer Name", "destinationcustomername", destinationcustomername, xml);
			addDropdownValues_commonMethod(application, "Order/Service", "orderservice_dropdown", sanmove_orderno, xml);
			click_commonMethod(application, "Move", "movebutton_sanmove", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			verifysuccessmessage(application, "San Moved Successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Move is successful");
			Log.info("SAN Move is successful");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN is not added in the grid");
		}

		//verify SAN in destination customer service
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sanmove_orderno);
		Thread.sleep(1000);

		WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
		ScrolltoElement(application, "searchbutton", xml);
		Clickon(searchbutton);
		Thread.sleep(2000);
		waitforPagetobeenable();
		ScrolltoElement(application, "searchbutton", xml);
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
		Thread.sleep(3000);
		waitforPagetobeenable();
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement AddedSAN11= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+sannumbervalue+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(AddedSAN11.isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN Move verification is successfull in destination customer");
			Log.info("SAN Move verification is successfull in destination customer");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : SAN is not moved successfully into destination customer");
			Log.info("SAN is not moved successfully into destination customer");
		}
	}
	//=======================================

	//Bulk Move

	public void verifyBulkMove(String application, String sid, String customernamevalue, String sannumbervalue, String select_sansearchtype, String bulkmove_country, String bulkmove_customer, String filterfrcnumber, String bulkmove_service, String country, String sannumber, String ringtonumber, String routingforpayphone_value, String routingformobile_value, String defaultrouting_value, String RingToNumber_Checkbox, String AnnouncementToPlay_Checkbox, String ComplexRouting_Checkbox, String defaultroutebusy_value, String noanswer_value, String networkcongestion, String resellername, String defaultvalue, String configure, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode, String serviceprofilevalue, String bulkmove_sannumber1, String bulkmove_sannumber2) throws InterruptedException, DocumentException, IOException {
		//Cancel Bulk Move
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(1000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));
		Thread.sleep(2000);
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
		Thread.sleep(1000);

		WebElement searchbutton1= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
		ScrolltoElement(application, "searchbutton", xml);
		Clickon(searchbutton1);
		Thread.sleep(2000);
		waitforPagetobeenable();
		ScrolltoElement(application, "searchbutton", xml);
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
		Thread.sleep(3000);
		waitforPagetobeenable();
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);

		//==============Add SAN to perform bulk move======

		click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);	
		click_commonMethod(application, "Add SAN", "addsan_link", xml);
		scrollToTop();
		waitforPagetobeenable();
		compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);

		addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
		click_commonMethod(application, "Customer Name dropdown", "customername", xml);
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_valuesdisplay")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Existing customer is available");
			Log.info("Existing customer is available");
			click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
		}
		else if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_nomatchesfound")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Existing customer is not available");
			Log.info("Existing customer is not available");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")));

			//Manage new customer link
			click_commonMethod(application, "Manage new customer", "managenewcustomer_link", xml);
			waitforPagetobeenable();
			compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
			Thread.sleep(1000);

			//=========================

			//Add Customer
			//Select Country from dropdown
			addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
			String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
			CustomerNamevalue.replace("(New)", "").trim();
			addtextFields_commonMethod(application, "Reseller Name", "resellername", resellername, xml);
			GetText(application, "Email", "reseller_email");
			addtextFields_commonMethod(application, "City", "reseller_city", city, xml);
			addtextFields_commonMethod(application, "Street Name", "reseller_streetname", streetname, xml);
			addtextFields_commonMethod(application, "Street Number", "reseller_streetno", streetno, xml);
			addtextFields_commonMethod(application, "PO Box", "reseller_pobox", pobox, xml);
			addtextFields_commonMethod(application, "Zip Code", "reseller_zipcode", zipcode, xml);
			GetText(application, "Phone", "reseller_phone");
			GetText(application, "Fax", "reseller_fax");

			if(defaultvalue.equalsIgnoreCase("YES"))
			{
				String Default_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/defaultcheckbox")).getAttribute("checked");
				if(Default_Checkbox.isEmpty())
				{
					System.out.println("Default checkbox is checked");
					Thread.sleep(1000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Default checkbox is checked");
					Log.info("Default checkbox is checked");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Default checkbox is not checked by default");
					Log.info("Default checkbox is not checked by default");
				}
			}

			else if(configure.equalsIgnoreCase("YES"))
			{
				String Configure_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/configurecheckbox")).getAttribute("checked");
				if(Configure_Checkbox.isEmpty())
				{
					System.out.println("Configure checkbox is checked");
					Thread.sleep(1000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Configure checkbox is checked");
					Log.info("Configure checkbox is checked");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Configure checkbox is not checked");
					Log.info("Configure checkbox is not checked");
					click_commonMethod(application, "Configure checkbox", "configurecheckbox", xml);
				}
			}
			ScrolltoElement(application, "nextbutton", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Next", "nextbutton", xml);
			Thread.sleep(3000);	
			waitforPagetobeenable();
			ScrolltoElement(application, "addsan_header", xml);
			compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);
			Thread.sleep(1000);

			//Select Country from dropdown
			addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
			click_commonMethod(application, "Customer Name dropdown", "customername", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
		}

		Thread.sleep(1000);
		//SAN customer name
		String SAN_Customernamevalue= getwebelement(xml.getlocator("//locators/" + application + "/customername_selectedtext")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step :Customer name selected is : " + SAN_Customernamevalue);
		String SAN_Customername=SAN_Customernamevalue.substring(0, SAN_Customernamevalue.indexOf("(")).trim();
		writetoexcel("src\\com\\colt\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 162, SAN_Customername);

		String SANNumber_CountryCode=getwebelement(xml.getlocator("//locators/" + application + "/san_number")).getAttribute("value");
		addtextFields_commonMethod(application, "SAN Number", "san_number", bulkmove_sannumber1, xml);
		//SAN number
		String Bulkmove_SANNumberValue1= SANNumber_CountryCode+bulkmove_sannumber1;
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step :SAN Number is dsplayed as : " + Bulkmove_SANNumberValue1);

		//Select service profile from dropdown
		addDropdownValues_commonMethod(application, "Service Profile", "serviceprofile", serviceprofilevalue, xml);
		ScrolltoElement(application, "serviceprofile", xml);
		Thread.sleep(1000);
		if(RingToNumber_Checkbox.equalsIgnoreCase("YES"))
		{
			click_commonMethod(application, "'Ring To Number' radio", "ringtonumber_radiobutton", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "Ring To Number", "ringtonumber_field", ringtonumber, xml);
		}
		else if(AnnouncementToPlay_Checkbox.equalsIgnoreCase("YES"))
		{
			click_commonMethod(application, "'Announcement to play' radio", "announcementtoplay_radiobutton", xml);
			Thread.sleep(1000);
			//Select announcement to play value from dropdown
			click_commonMethod(application, "Announcement To Play dropdown", "announcementtoplay_dropdown", xml);
			click_commonMethod(application, "Announcement To Play value", "announcementtoplay_dropdownvalue", xml);
		}
		else if(ComplexRouting_Checkbox.equalsIgnoreCase("YES"))
		{
			click_commonMethod(application, "'Complex route' radio", "complexroute_radiobutton", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "Routing for payphone", "routingforpayphone_field", routingforpayphone_value, xml);
			addtextFields_commonMethod(application, "Routing for mobile", "routingformobile", routingformobile_value, xml);
			addtextFields_commonMethod(application, "Default Routing", "defaultrouting", defaultrouting_value, xml);
			click_commonMethod(application, "Enable logical routing checkbox", "enablelogicalrouting", xml);
			addtextFields_commonMethod(application, "Default Route busy", "defaultroutebusy", defaultroutebusy_value, xml);
			addtextFields_commonMethod(application, "No Answer", "noanswer", noanswer_value, xml);
			addtextFields_commonMethod(application, "Network Congestion", "networkcongestion", networkcongestion, xml);
		}
		ScrolltoElement(application, "addsan_addbutton", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Add", "addsan_addbutton", xml);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
		{
			Log.info("Navigated to view SAN page");
			ScrolltoElement(application, "viewpage_backbutton", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			waitforPagetobeenable();
		}
		else
		{
			Log.info("SAN not created");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : SAN not created");
		}

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to view service page");
		}
		else
		{
			Log.info("Not navigated to view service page");
			System.out.println("Not navigated to view service page");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Not navigated to view service page");
		}

		//===========End of Add SAN=========

		//==============Add SAN to perform bulk move======
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);	
		click_commonMethod(application, "Add SAN", "addsan_link", xml);
		waitforPagetobeenable();
		scrollToTop();
		compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);

		addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
		click_commonMethod(application, "Customer Name dropdown", "customername", xml);
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_valuesdisplay")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Existing customer is available");
			Log.info("Existing customer is available");
			click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
		}
		else if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_nomatchesfound")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Existing customer is not available");
			Log.info("Existing customer is not available");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")));

			//Manage new customer link
			click_commonMethod(application, "Manage new customer", "managenewcustomer_link", xml);
			waitforPagetobeenable();
			compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
			Thread.sleep(1000);

			//=========================

			//Add Customer
			//Select Country from dropdown
			addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
			String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
			CustomerNamevalue.replace("(New)", "").trim();
			addtextFields_commonMethod(application, "Reseller Name", "resellername", resellername, xml);
			GetText(application, "Email", "reseller_email");
			addtextFields_commonMethod(application, "City", "reseller_city", city, xml);
			addtextFields_commonMethod(application, "Street Name", "reseller_streetname", streetname, xml);
			addtextFields_commonMethod(application, "Street Number", "reseller_streetno", streetno, xml);
			addtextFields_commonMethod(application, "PO Box", "reseller_pobox", pobox, xml);
			addtextFields_commonMethod(application, "Zip Code", "reseller_zipcode", zipcode, xml);
			GetText(application, "Phone", "reseller_phone");
			GetText(application, "Fax", "reseller_fax");

			if(defaultvalue.equalsIgnoreCase("YES"))
			{
				String Default_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/defaultcheckbox")).getAttribute("checked");
				if(Default_Checkbox.isEmpty())
				{
					System.out.println("Default checkbox is checked");
					Thread.sleep(1000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Default checkbox is checked");
					Log.info("Default checkbox is checked");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Default checkbox is not checked by default");
					Log.info("Default checkbox is not checked by default");
				}
			}

			else if(configure.equalsIgnoreCase("YES"))
			{
				String Configure_Checkbox= getwebelement(xml.getlocator("//locators/" + application + "/configurecheckbox")).getAttribute("checked");
				if(Configure_Checkbox.isEmpty())
				{
					System.out.println("Configure checkbox is checked");
					Thread.sleep(1000);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Configure checkbox is checked");
					Log.info("Configure checkbox is checked");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Configure checkbox is not checked");
					Log.info("Configure checkbox is not checked");
					click_commonMethod(application, "Configure checkbox", "configurecheckbox", xml);
				}
			}
			ScrolltoElement(application, "nextbutton", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Next", "nextbutton", xml);
			Thread.sleep(3000);	
			waitforPagetobeenable();
			ScrolltoElement(application, "addsan_header", xml);
			compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);
			Thread.sleep(1000);

			//Select Country from dropdown
			addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
			click_commonMethod(application, "Customer Name dropdown", "customername", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
		}

		Thread.sleep(1000);
		//SAN customer name
		String SAN_Customernamevalue1= getwebelement(xml.getlocator("//locators/" + application + "/customername_selectedtext")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step :Customer name selected is : " + SAN_Customernamevalue1);
		String SAN_Customername1=SAN_Customernamevalue1.substring(0, SAN_Customernamevalue1.indexOf("(")).trim();

		String SANNumber_CountryCode1=getwebelement(xml.getlocator("//locators/" + application + "/san_number")).getAttribute("value");
		addtextFields_commonMethod(application, "SAN Number", "san_number", bulkmove_sannumber2, xml);
		//SAN number
		String Bulkmove_SANNumberValue2= SANNumber_CountryCode1+bulkmove_sannumber2;
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step :SAN Number is dsplayed as : " + Bulkmove_SANNumberValue2);

		//Select service profile from dropdown
		addDropdownValues_commonMethod(application, "Service Profile", "serviceprofile", serviceprofilevalue, xml);
		Thread.sleep(1000);
		ScrolltoElement(application, "serviceprofile", xml);
		Thread.sleep(1000);
		if(RingToNumber_Checkbox.equalsIgnoreCase("YES"))
		{
			click_commonMethod(application, "'Ring To Number' radio", "ringtonumber_radiobutton", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "Ring To Number", "ringtonumber_field", ringtonumber, xml);
		}
		else if(AnnouncementToPlay_Checkbox.equalsIgnoreCase("YES"))
		{
			click_commonMethod(application, "'Announcement to play' radio", "announcementtoplay_radiobutton", xml);
			Thread.sleep(1000);
			//Select announcement to play value from dropdown
			click_commonMethod(application, "Announcement To Play dropdown", "announcementtoplay_dropdown", xml);
			click_commonMethod(application, "Announcement To Play value", "announcementtoplay_dropdownvalue", xml);
		}
		else if(ComplexRouting_Checkbox.equalsIgnoreCase("YES"))
		{
			click_commonMethod(application, "'Complex route' radio", "complexroute_radiobutton", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "Routing for payphone", "routingforpayphone_field", routingforpayphone_value, xml);
			addtextFields_commonMethod(application, "Routing for mobile", "routingformobile", routingformobile_value, xml);
			addtextFields_commonMethod(application, "Default Routing", "defaultrouting", defaultrouting_value, xml);
			click_commonMethod(application, "Enable logical routing checkbox", "enablelogicalrouting", xml);
			addtextFields_commonMethod(application, "Default Route busy", "defaultroutebusy", defaultroutebusy_value, xml);
			addtextFields_commonMethod(application, "No Answer", "noanswer", noanswer_value, xml);
			addtextFields_commonMethod(application, "Network Congestion", "networkcongestion", networkcongestion, xml);
		}
		ScrolltoElement(application, "addsan_addbutton", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Add", "addsan_addbutton", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
		{
			Log.info("Navigated to view SAN page");
			ScrolltoElement(application, "viewpage_backbutton", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
			waitforPagetobeenable();
		}
		else
		{
			Log.info("SAN not created");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : SAN not created");
		}

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to view service page");
		}
		else
		{
			Log.info("Not navigated to view service page");
			System.out.println("Not navigated to view service page");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Not navigated to view service page");
		}

		//===========End of Add SAN=========
		
		Thread.sleep(3000);
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);

		WebElement SANGridCheck8= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid8= SANGridCheck8.getAttribute("style");
		if(!SANGrid8.contains("height: 1px"))
		{
			Thread.sleep(1000);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "Bulk Move", "bulkmove_link", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			compareText(application, "Bulk Move Header", "bulkmoveheader", "Bulk Move", xml);
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Bulk Move is cancelled");
			Log.info("Bulk Move is cancelled");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN is not added in the grid");
		}

		//Bulk Move
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);

		WebElement SANGridCheck9= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid9= SANGridCheck9.getAttribute("style");
		if(!SANGrid9.contains("height: 1px"))
		{
			Thread.sleep(1000);
			compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "Bulk Move", "bulkmove_link", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			compareText(application, "Bulk Move Header", "bulkmoveheader", "Bulk Move", xml);
			click_commonMethod(application, "Move", "movebutton_sanmove", xml);
			//warning message check
			warningMessage_commonMethod(application, "bulkmove_countrywarngmsg", "Country", xml);
			warningMessage_commonMethod(application, "bulkmove_customerwarngmsg", "Customer", xml);
			warningMessage_commonMethod(application, "filterfrcnumber_warngmsg", "Filter FRC Number", xml);
			warningMessage_commonMethod(application, "bulkmove_Servicewarngmsg", "Service", xml);
			addDropdownValues_commonMethod(application, "Country", "bulkmove_countrydropdown", bulkmove_country, xml);
			addDropdownValues_commonMethod(application, "Customer", "bulkmove_customerdropdown", bulkmove_customer, xml);
			click_commonMethod(application, "Filter FRC Number Dropdown", "filterfrcnumberdropdown", xml);
			WebElement FilterFRC_Dropdownvalueselect1= getwebelement("//div[label[text()='Filter FRC Number']]//div[@role='list']//div//div[2]/div//div[contains(text(),'"+Bulkmove_SANNumberValue1+"')]/preceding-sibling::input[@type='checkbox']");
			Clickon(FilterFRC_Dropdownvalueselect1);
			WebElement FilterFRC_Dropdownvalueselect2= getwebelement("//div[label[text()='Filter FRC Number']]//div[@role='list']//div//div[2]/div//div[contains(text(),'"+Bulkmove_SANNumberValue2+"')]/preceding-sibling::input[@type='checkbox']");
			Clickon(FilterFRC_Dropdownvalueselect2);
			Thread.sleep(2000);
			addDropdownValues_commonMethod(application, "Service", "bulkmove_servicedropdown", bulkmove_service, xml);
			//GetText(application, "Remarks", "remarktextarea");
			click_commonMethod(application, "Move", "movebutton_sanmove", xml);
			Thread.sleep(10000);
			waitforPagetobeenable();
			verifysuccessmessage(application, "San Bulk Moved Successfully");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Bulk Move is successful");
			Log.info("Bulk Move is successful");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : SAN is not added in the grid");
		}

		//verify SAN in destination customer service
		Thread.sleep(2000);
		scrollToTop();
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));
		Thread.sleep(2000);
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),bulkmove_service);
		Thread.sleep(1000);

		WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
		ScrolltoElement(application, "searchbutton", xml);
		Clickon(searchbutton);
		Thread.sleep(2000);
		waitforPagetobeenable();
		ScrolltoElement(application, "searchbutton", xml);
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
		Thread.sleep(3000);
		waitforPagetobeenable();
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);

		WebElement AddedSAN11= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+Bulkmove_SANNumberValue1+"')]/parent::div//span[contains(@class,'unchecked')]");
		WebElement AddedSAN12= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+Bulkmove_SANNumberValue2+"')]/parent::div//span[contains(@class,'unchecked')]");
		
		//		int AddedSANCount= AddedSAN11.size();
		//		System.out.println("BulkSANAdded");
		if(AddedSAN11.isDisplayed() && AddedSAN12.isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Bulk Move verification is successfull in destination customer");
			Log.info("Bulk Move verification is successfull in destination customer");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Bulk SAN is not moved successfully into destination customer");
			Log.info("Bulk SAN is not moved successfully into destination customer");
		}
	}



	public void verifyAllDeleteOperations(String application, String customernamevalue, String select_sansearchtype
			, String sannumbervalue, String customadm, String sanadm, String reselladm) throws InterruptedException, DocumentException, IOException	{

		//Delete SAN
		if(!sanadm.equalsIgnoreCase("No")) {
		ScrolltoElement(application, "sanheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
//		//SANFilter
//		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
//		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
//		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
//		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
//		cleartext(application, "Clear SAN Number", "sannumbersearchfield");
//		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
//		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck2= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid2= SANGridCheck2.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		if(!SANGrid2.contains("height: 1px"))
		{
		List<WebElement> AddedSAN2= getwebelements("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//span[contains(@class,'unchecked')]");
		int AddedSAN2_count=AddedSAN2.size();
		for(int i=0;i<AddedSAN2_count;i++)
		{
			Clickon(AddedSAN2.get(i));
			Thread.sleep(1000);

			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "Delete SAN", "deletesan_link", xml);
			Thread.sleep(2000);
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			if(DeleteAlertPopup.isDisplayed())
			{
				click_commonMethod(application, "Delete", "deletebutton", xml);
				Thread.sleep(2000);
				verifysuccessmessage(application, "San deleted Successfully");
				Thread.sleep(1000);
				ScrolltoElement(application, "customerheader", xml);
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}

			Thread.sleep(2000);
		}
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}
		}
		
		//Delete Customer
		if(!customadm.equalsIgnoreCase("No")) {
		ScrolltoElement(application, "customerheader", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		WebElement CustomerGridCheck= getwebelement("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String CustomerGrid= CustomerGridCheck.getAttribute("style");
		WebElement AddedCustomer= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//span[contains(@class,'unchecked')]");
		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer);
			Thread.sleep(1000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CustomerpanelActionDropdown")));
			Thread.sleep(1000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Action dropdown button");
			Log.info("clicked on Action dropdown button");

			click_commonMethod(application, "Customer Delete", "delete", xml);
			Thread.sleep(2000);
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			if(DeleteAlertPopup.isDisplayed())
			{
				click_commonMethod(application, "Delete", "deletebutton", xml);
				Thread.sleep(2000);
				verifysuccessmessage(application, "Customer successfully deleted.");
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}

			Thread.sleep(2000);
		}
		}
		
		if(!reselladm.equalsIgnoreCase("No")) {
		//Delete Reseller
			ScrolltoElement(application, "managementoptions_header", xml);
		Thread.sleep(2000);
		WebElement ResellerGridCheck= getwebelement("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String ResellerGrid= ResellerGridCheck.getAttribute("style");

		WebElement AddedReseller= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + ResellerName + "')]/parent::div//span[contains(@class,'unchecked')]");

		if(!ResellerGrid.contains("height: 1px"))
		{
			Clickon(AddedReseller);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);

			click_commonMethod(application, "Delete", "delete", xml);
			Thread.sleep(2000);
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			if(DeleteAlertPopup.isDisplayed())
			{
				click_commonMethod(application, "Delete", "deletebutton", xml);
				Thread.sleep(2000);
				verifysuccessmessage(application, "Reseller successfully deleted.");
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			Thread.sleep(2000);
		}
		}
		
		//Delete Service
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Service Delete", "delete", xml);
		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			click_commonMethod(application, "Delete", "deletebutton", xml);
			Thread.sleep(2000);
			verifysuccessmessage(application, "Service successfully deleted");
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
		Thread.sleep(2000);
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

	public void searchservice(String application, String sid) throws InterruptedException, DocumentException, IOException {

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


	public void writetoexcel(String filepath, String sheetname, int columnnumber, String inputvalue) throws IOException{

		File file= new File(filepath);
		FileInputStream fis= new FileInputStream(file);
		XSSFWorkbook work= new XSSFWorkbook(fis);
		XSSFSheet sh= work.getSheet(sheetname);
		for(int i=1;i<=sh.getLastRowNum();i++)
		{
		sh.getRow(i).createCell(columnnumber).setCellValue(inputvalue);
		}
		fis.close();
		
		FileOutputStream fos= new FileOutputStream(file);
		work.write(fos);
		work.close();
		fis.close();

	}

	public void writeToCSVFile(String filepath, String FRCNumber, String CustomerName) throws IOException, CsvException{

		File inputFile= new File(filepath);
		 // Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();
        // get CSV row column and replace with by using row and column
        for(int i=1; i<csvBody.size(); i++){
            String[] strArray = csvBody.get(i);
            System.out.println("i value " +i);
            System.out.println(strArray);
            for(int j=0; j<strArray.length; j++){
                csvBody.get(i)[1] = FRCNumber; //Target replacement
                csvBody.get(i)[3] = CustomerName;
            }
        }
        reader.close();

        // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
	}
	
	
	public void Highlight(String application, String xpath, XMLReader xml) {
		
		WebElement element = null;

		try {
			//Thread.sleep(1000);
			element = getwebelement(xml.getlocator("//locators/" + application + "/" + xpath + ""));
		JavascriptExecutor js = (JavascriptExecutor) driver;
        //use executeScript() method and pass the arguments 
        //Here i pass values based on css style. solid red color border. 
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid #6699ff;');", element);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
				
			}else {
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
				System.out.println("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
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

}
