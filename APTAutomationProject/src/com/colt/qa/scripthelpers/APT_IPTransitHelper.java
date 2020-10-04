package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;


public class APT_IPTransitHelper extends DriverHelper {

	public static String InterfaceName="Null";
	
	public APT_IPTransitHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_IPTransit.xml");

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
		Thread.sleep(2000);
		waitforPagetobeenable();
		//Warning messages verify
		warningMessage_commonMethod(application, "servicetype_warngmsg", "Service Type", xml);
		
		if (neworder.equalsIgnoreCase("YES")) {

			ScrolltoElement(application, "CreateOrderHeader", xml);
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
		scrolltoend();
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
					List<WebElement> listofvalues = getwebelements("//div[@class='sc-ifAKCX oLlzc']");

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

					//scrolltoend();
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
		scrolltoend();
		click_commonMethod(application, "Next", "nextbutton", xml);
		waitforPagetobeenable();
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

	}


	public void verifyservicecreation(String application, String sid, String Remarks, String orderno, String rfireqno, String servicetype, String terminationdate, String billingtypevalue, String email, String phonecontact, String performancereporting_checkbox, String ipguardian_checkbox) throws InterruptedException, IOException, DocumentException {

		//Cancel service creation
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to create order page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to create order page");
		}

		//Create service
		ScrolltoElement(application, "CreateOrderHeader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "select order switch", "selectorderswitch", xml);
		addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno, xml);
		addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		// click on next button
		ScrolltoElement(application, "nextbutton", xml);
		Thread.sleep(2000);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

		// verify warning messages
		scrolltoend();
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(1000);
		scrollToTop();
		warningMessage_commonMethod(application, "sidwarngmsg", "Service Identification", xml);
		warningMessage_commonMethod(application, "billingtype_warngmsg", "Billing Type", xml);

		//Create service
		addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
		addtextFields_commonMethod(application, "Termination Date", "terminationdate_field", terminationdate, xml);
		addDropdownValues_commonMethod(application, "Billing Type", "billingtype_dropdown", billingtypevalue, xml);
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", Remarks, xml);
		addtextFields_commonMethod(application, "Email", "emailtextfieldvalue", email, xml);
		click_commonMethod(application, "Email adding Arrow", "emailaddarrow", xml);
		ScrolltoElement(application, "remarktextarea", xml);
		addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", phonecontact, xml);
		click_commonMethod(application, "phone contact adding Arrow", "phoneaddarrow", xml);
		scrolltoend();

		// management options panel
		addCheckbox_commonMethod(application, "performancereporting_checkbox", "Performance Reporting", performancereporting_checkbox, "no", xml);
		
		addCheckbox_commonMethod(application, "ipguardian_checkbox", "IP Guardian", ipguardian_checkbox, "no", xml);
		
		scrolltoend();
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(2000);
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
		ScrolltoElement(application, "customerdetailsheader", xml);
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
        waitforPagetobeenable();
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
        addtextFields_commonMethod(application, "Email", "emailtextfield", Email, xml);
        addtextFields_commonMethod(application, "Phone", "phonetextfield", Phone, xml);
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

        ScrolltoElement(application, "emailtextfield", xml);
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
              waitforPagetobeenable();
              Thread.sleep(2000);
              compareText(application, "Edit User Header", "edituser_header", "Edit User", xml);
              scrollToTop();
              edittextFields_commonMethod(application, "User Name", "UserName" , EditUsername, xml);
              edittextFields_commonMethod(application, "First Name", "FirstName" , EditFirstname, xml);
              edittextFields_commonMethod(application, "Sur Name", "SurName" , EditSurname, xml);
              edittextFields_commonMethod(application, "Postal Address", "PostalAddress" , EditPostaladdress, xml);
              edittextFields_commonMethod(application, "Email", "emailtextfield" , EditEmail, xml);
              edittextFields_commonMethod(application, "Phone", "phonetextfield" , EditPhone, xml);
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

              ScrolltoElement(application, "emailtextfield", xml);

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
              

              //IP Guardian Account Group
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
              Log.info("------ View User successful ------");
              waitforPagetobeenable();
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
      	     Thread.sleep(1000);
      	       verifysuccessmessage(application, "User successfully deleted");
      	       Thread.sleep(2000);
      	      
      	     }catch(Exception e) {
      	    	 e.printStackTrace();
      	     } 
      	     
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

	public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "orderpanelheader", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
		//Cancel Edit order in Order panel
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		waitforPagetobeenable();
		compareText(application, "Edit Order", "editorderheader", "Edit Order", xml);
		Thread.sleep(1000);

		WebElement EditOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/editorderno"));
		click_commonMethod(application, "Order Number", "editorderno", xml);
		Thread.sleep(2000);
		edittextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);

		WebElement EditVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/editvoicelineno"));
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		Thread.sleep(2000);
		edittextFields_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		waitforPagetobeenable();
		compareText(application, "Order Header", "orderpanelheader", "Order", xml);
		Log.info("Navigated to order panel in view service page");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		//Edit Order
		ScrolltoElement(application, "orderpanelheader", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		waitforPagetobeenable();
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
		waitforPagetobeenable();
		ScrolltoElement(application, "orderpanelheader", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
		Thread.sleep(1000);
		compareText(application, "Order Header", "orderpanelheader", "Order", xml);
		Log.info("Navigated to order panel in view service page");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

		compareText(application, "Order Number", "ordernumbervalue", editorderno, xml);
		compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno, xml);
		Log.info("------ Edit Order is successful ------");

	}

	public void verifyorderpanel_changeorder(String application, String changeorderno, String changevoicelineno) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "orderpanelheader", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Change Order", "changeorderlink", xml);
		waitforPagetobeenable();
		compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Choose order dropdown", "changeorder_chooseorderdropdown", xml);
		List<WebElement> ChangeOrder_DropdownList= getwebelements(xml.getlocator("//locators/" + application + "/changeorder_dropdownlist"));
		int ChangeOrder_Dropdown_count= ChangeOrder_DropdownList.size();
		if(ChangeOrder_Dropdown_count> 1)
		{
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/changeorder_dropdownvalue")));
			Thread.sleep(3000);

			//Cancel change order
			click_commonMethod(application, "Cancel", "changeorder_cancelbutton", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
			Log.info("Navigated to order panel in view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

			//Change order
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			waitforPagetobeenable();
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Choose order dropdown", "changeorder_chooseorderdropdown", xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/changeorder_dropdownvalue")));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected order from dropdown");
			click_commonMethod(application, "OK", "changeorder_okbutton", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			ScrolltoElement(application, "orderpanelheader", xml);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
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
			waitforPagetobeenable();
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
			waitforPagetobeenable();
			ScrolltoElement(application, "orderpanelheader", xml);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
			compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
			Log.info("Navigated to order panel in view service page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to order panel in view service page");
			compareText(application, "Order Number", "ordernumbervalue", changeorderno, xml);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
			Log.info("------ Change Order is successful ------");
		}
	}

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String Remarks, String terminationdate, String billingtypevalue, String email, String phonecontact) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "orderpanelheader", xml);
		compareText(application, "Service panel Header", "servicepanel_header", "Service", xml);
		compareText(application, "Service Identification", "servicepanel_serviceidentificationvalue", sid, xml);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype, xml);
		GetText(application, "Termination Date", "servicepanel_terminationdate");
		GetText(application, "Email", "servicepanel_email");
		compareText(application, "Phone Contact", "servicepanel_phone", phonecontact, xml);
		compareText(application, "Billing Type", "servicepanel_billingtype", billingtypevalue, xml);
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks, xml);
	}

	public void verifyEditservice(String application, String EditRemarks, String Remarks, String sid, String editterminationdate, String editbillingtypevalue, String editemail, String editphonecontact, String edit_performancereporting_checkbox, String edit_ipguardian_checkbox) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service
		Thread.sleep(2000);
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		ScrolltoElement(application, "servicepanel_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to view service page");
		}
		else
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to view service page");

		//Edit service
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		edittextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
		edittextFields_commonMethod(application, "Termination Date", "terminationdate_field", editterminationdate, xml);
		edittextFields_commonMethod(application, "Billing Type", "billingtype_dropdown", editbillingtypevalue, xml);
		edittextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
		
		ScrolltoElement(application, "remarktextarea", xml);
		//Edit email
		click_commonMethod(application, "Selected Email", "selectedemail", xml);
		click_commonMethod(application, "Email remove arrow", "emailremovearrow", xml);
		edittextFields_commonMethod(application, "Email", "emailtextfieldvalue", editemail, xml);
		click_commonMethod(application, "Email adding arrow", "emailaddarrow", xml);
		//Edit phone contact
		ScrolltoElement(application, "remarktextarea", xml);
		click_commonMethod(application, "Selected phone contact", "selectedphone", xml);
		click_commonMethod(application, "Phonecontact remove arrow", "phoneremovearrow", xml);
		edittextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", editphonecontact, xml);
		click_commonMethod(application, "phonecontact adding Arrow", "phoneaddarrow", xml);

		// management options panel
		ScrolltoElement(application, "managementoptions_header", xml);
		editcheckbox_commonMethod(application, edit_performancereporting_checkbox, "performancereporting_checkbox", "Performance Reporting", xml);
		editcheckbox_commonMethod(application, edit_ipguardian_checkbox, "ipguardian_checkbox", "IP Guardian", xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "OK", "editservice_okbutton", xml);
		Thread.sleep(3000);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			verifysuccessmessage(application, "Service updated successfully");
		}
		else
		{
			Log.info("Service not updated");
			System.out.println("Service not updated");
		}

	}
	
	public void verifyManageSubnets(String application) throws InterruptedException, DocumentException {
		
		ScrolltoElement(application, "orderpanelheader", xml);
				click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
				click_commonMethod(application, "Manage Subnets", "managesubnets_link", xml);
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
	}
	
public void verifyManageSubnetsIPv6(String application) throws InterruptedException, DocumentException {
		
	ScrolltoElement(application, "orderpanelheader", xml);
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
	}

public void verifyDump(String application) throws InterruptedException, DocumentException {
	
	ScrolltoElement(application, "orderpanelheader", xml);
	click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
	click_commonMethod(application, "Dump", "dump_link", xml);
	Thread.sleep(2000);
	waitforPagetobeenable();
	GetText(application, "Dump header", "dumppage_header");
	GetText(application, "Service retrieved time", "serviceretrieved_text");
	compareText(application, "Service header", "service_header", "Service", xml);
	GetText(application, "Dump page service details", "dumppage_text");
	Thread.sleep(1000);
	click_commonMethod(application, "Close", "closesymbol", xml);
	Thread.sleep(2000);
}

public void verifyShowNewInfovistaReport(String application) throws Exception {
	
	shownewInfovista(application);
	Thread.sleep(2000);
}

public void shownewInfovista(String application) throws Exception {

	ScrolltoElement(application, "orderpanelheader", xml);
	Thread.sleep(3000);
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
		if ((driver.switchTo().window(browserTabs .get(1)).getTitle()).contains(expectedPageName)) {
		
		// Get Tab name
		String pageTitle=driver.switchTo().window(browserTabs .get(1)).getTitle();
		System.out.println("page title displays as: "+pageTitle);

		//assertEquals(pageTitle, expectedPageName, "on clicking 'Show Infovista link', it got naviagted to "+pageTitle);
		sa.assertEquals(pageTitle, expectedPageName);

		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(browserTabs.get(0)); 

		ExtentTestManager.getTest().log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
		Thread.sleep(3000);

		ExtentTestManager.getTest().log(LogStatus.PASS, "show info vista page actual title: "+pageTitle );
		ExtentTestManager.getTest().log(LogStatus.PASS, "show info vista page expected title: "+ expectedPageName);
		
		}
		else
		{
			Thread.sleep(3000);
			driver.close();
			driver.switchTo().window(browserTabs.get(0));
		}
	}catch(Exception e) {

		e.printStackTrace();

		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(browserTabs.get(0));

		ExtentTestManager.getTest().log(LogStatus.FAIL, expectedPageName + " page is not displaying");

	}
	sa.assertAll();
}

public void verifySynchronize(String application) throws InterruptedException, DocumentException {
	
	ScrolltoElement(application, "servicepanel_header", xml);
	((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
Thread.sleep(1000);
click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
click_commonMethod(application, "Synchronize", "synchronizelink_servicepanel", xml);
ScrolltoElement(application, "customerdetailsheader", xml);
waitforPagetobeenable();
verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
}

	
	public void verifyManageService(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		//Manage service
		ScrolltoElement(application, "servicepanel_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage", "manageLink", xml);
		Thread.sleep(2000);
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
				Thread.sleep(2000);
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
				Thread.sleep(2000);
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
		Thread.sleep(2000);
		scrollToTop();
		compareText(application, "Manage Network header", "managenetwork_header", "Manage COLT's Network - Manage Network", xml);
		driver.navigate().back();
		Thread.sleep(2000);
		scrolltoend();
		Thread.sleep(2000);
		click_commonMethod(application, "Back", "managepage_backbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
	}
	
	public void verify_SmartsValue(String application) {
		try {
			if(getwebelement(xml.getlocator("//locators/" + application + "/deviceforservicepanel_smartsstatus")).isDisplayed())
			{
			GetText(application, "Smarts Status", "deviceforservicepanel_smartsstatus");
			GetText(application, "Smarts DateTime", "deviceforservicepanel_smartsdatetime");
			}
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No values displaying under Smarts column");
		}
	 }
	
	public void verify_FetchInterfacesValue(String application) {
		try {
			if(getwebelement(xml.getlocator("//locators/" + application + "/deviceforservicepanel_fetchinterfaces_status")).isDisplayed())
			{
			GetText(application, "fetchinterfaces Status", "deviceforservicepanel_fetchinterfaces_status");
			GetText(application, "fetchinterfaces DateTime", "deviceforservicepanel_fetchinterfaces_datetime");
			}
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No values displaying under Fetch Interfaces column");
		}
	 }
	
	 public void verify_VistamartDeviceValue(String application) {
		try {
			if(getwebelement(xml.getlocator("//locators/" + application + "/deviceforservicepanel_vistamart_status")).isDisplayed())
			{
			GetText(application, "Vistamart Status", "deviceforservicepanel_vistamart_status");
			GetText(application, "Vistamart DateTime", "deviceforservicepanel_vistamart_datetime");
			}
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No values displaying under VistaMart device column");
		}
	 }
	 
	public void verifyManagementOptionspanel(String application, String performancereporting_checkbox, String ipguardian_checkbox) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "managementoptions_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		compareText(application, "Management options header", "managementoptions_header", "Management Options", xml);
		
		compareText(application, "Performance Reporting", "managementoptions_performancereporting", performancereporting_checkbox, xml);
		compareText(application, "IP Guardian", "managementoptions_ipguardian", ipguardian_checkbox, xml);
	}

	public void navigateToAddNewDevicepage(String application) throws InterruptedException, DocumentException {
		ScrolltoElement(application, "providerequipment_header", xml);
		
		Thread.sleep(1000);
		compareText(application, "Provider Equipment (PE)", "providerequipment_header", "Provider Equipment (PE)", xml);
		click_commonMethod(application, "Add PE Device", "addpedevice_link", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		compareText(application, "Add PE Device Header", "addpedevice_header", "Add PE Device", xml);
		click_commonMethod(application, "Add New Device toggle", "addnewdevice_togglebutton", xml);
		Thread.sleep(2000);
	}

	public void verifyadddevicefields(String application) throws InterruptedException, DocumentException {

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

	public void addNewPEDevice(String application, String name, String devicetype, 
			String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
			String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
			Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
			String Snmpv3Privpasswordvalue, String Country, String managementaddress, String existingcity, 
			String existingcityvalue, String existingsite,
			String existingsitevalue, String existingpremise, 
			String existingpremisevalue, String newcity,String cityname,String Citycode,
			String sitename, String sitecode,  String premisename,  String premisecode, 
			String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {

		try {

			scrolltoend();
			//Verify Mandatory fields
			click_commonMethod(application, "Next", "Next_Button", xml);
			Thread.sleep(2000);
			scrollToTop();
			Thread.sleep(2000);
			warningMessage_commonMethod(application, "warningMessage_name", "Device Name", xml);
			warningMessage_commonMethod(application, "warningMessage_vendor", "Vendor/Model", xml);
			warningMessage_commonMethod(application, "warningMessage_Country", "Country", xml);

			//name
			addtextFields_commonMethod(application, "Name", "nametextfield", name, xml);

			//vendormodel
			selectValueInsideDropdown(application, "vendormodelinput", "Vendor/Model", vendormodel, xml);

			//Management address
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

			}
			else if((snmp2c.equalsIgnoreCase("no"))  && (snmp3.equalsIgnoreCase("yes"))) {

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
			Thread.sleep(2000);
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

				selectValueInsideDropdown(application, "citydropdowninput", "City", existingcityvalue, xml);

				//Existing Site
				if(existingsite.equalsIgnoreCase("yes") & newsite.equalsIgnoreCase("no")) {
					selectValueInsideDropdown(application, "sitedropdowninput", "Site", existingsitevalue, xml);
					
					//Existing Premise
					if(existingpremise.equalsIgnoreCase("yes") & NewPremise.equalsIgnoreCase("no")) {
						addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", existingpremisevalue, xml);
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
		Thread.sleep(1000);
		click_commonMethod(application, "Next", "Next_Button", xml);
		Thread.sleep(1000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Device successfully created");
		Thread.sleep(2000);
	}

	public void verifyViewpage_Devicedetails(String application, String name, String vendormodel, String telnet
			, String ssh, String snmp2c, String snmpro2cvalue, String snmprw2cvalue
			, String snmp3, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,String Snmpv3Privpasswordvalue
			, String Country, String managementaddress, String existingcity, String existingcityvalue
			, String existingsite, String existingsitevalue, String existingpremise, String existingpremisevalue
			, String newcity,String cityname,String Citycode, String sitename, String sitecode
			, String premisename, String premisecode, String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {


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
			if(!snmpro2cvalue.equalsIgnoreCase("Null")) {
			compareText(application, "Snmpro", "viewpage_snmpro", snmpro2cvalue, xml);
			}
			else {
				compareText(application, "Snmpro", "viewpage_snmpro", "incc", xml);
			}

			//Snmprw
			if(!snmprw2cvalue.equalsIgnoreCase("Null")) {
				compareText(application, "Snmprw", "viewpage_snmprw", snmprw2cvalue, xml);
			}
			else {
			compareText(application, "Snmprw", "viewpage_snmprw", "ip4corp3", xml);
			}

		}
		else if((snmp2c.equalsIgnoreCase("No")) && (snmp3.equalsIgnoreCase("Yes"))) {

			//Snmp v3 Username
			if(!Snmpv3Usernamevalue.equalsIgnoreCase("Null")) {
				compareText(application, "Snmp v3 Username", "viewpage_snmpv3username", Snmpv3Usernamevalue, xml);
			}
			else {
				compareText(application, "Snmp v3 Username", "viewpage_snmpv3username", "colt-nms", xml);
			}

			//Snmp v3 Auth Password
			if(!Snmpv3Authpasswordvalue.equalsIgnoreCase("Null")) {
			compareText(application, "Snmp V3 Auth Password", "viewpage_snmpv3authpassword", Snmpv3Authpasswordvalue, xml);
			}
			else {
				compareText(application, "Snmp V3 Auth Password", "viewpage_snmpv3authpassword", "OrHzjWmRvr4piJZb", xml);
			}

			//Snmp v3 Priv Password
			if(!Snmpv3Privpasswordvalue.equalsIgnoreCase("Null")) {
				compareText(application, "Snmp V3 Priv Password", "viewpage_snmpv3privpassword", Snmpv3Privpasswordvalue, xml);
			}
			else {
			compareText(application, "Snmp V3 Priv Password", "viewpage_snmpv3privpassword", "3k0hw8thNxHucQkE", xml);
			}
		}

		//Management Address
		GetText(application, "Management Address", "viewpage_managementaddress");
		
		//Country
		GetText(application, "Country", "viewpage_country");

		//City
		if((existingcity.equalsIgnoreCase("yes")) && (newcity.equalsIgnoreCase("NO"))) {

			//Existing City
			GetText(application, "City", "viewpage_city");

		}
		else if((existingcity.equalsIgnoreCase("NO")) && (newcity.equalsIgnoreCase("Yes"))) {

			//new City
			GetText(application, "City", "viewpage_city");

		}

		//Site
		if((existingsite.equalsIgnoreCase("yes")) && (newsite.equalsIgnoreCase("NO"))) {

			//Existing Site
			GetText(application, "Site", "viewpage_site");
		}

		else if((existingsite.equalsIgnoreCase("No")) && (newsite.equalsIgnoreCase("Yes"))) {

			//New Site
			GetText(application, "Site", "viewpage_site");
		}

		//Premise
		if((existingpremise.equalsIgnoreCase("yes")) && (NewPremise.equalsIgnoreCase("NO"))) {

			//Existing premise
			GetText(application, "Premise", "viewpage_premise");
		}

		else if((existingpremise.equalsIgnoreCase("No")) && (NewPremise.equalsIgnoreCase("Yes"))) {

			//new premise
			GetText(application, "Premise", "viewpage_premise");
		}
	}

	public void verifyViewDevicepage_Links(String application) throws InterruptedException, DocumentException {
		
		scrollToTop();
		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		compareText(application, "Edit", "viewdevice_Edit", "Edit", xml);
		compareText(application, "Delete", "viewdevice_delete", "Delete", xml);
		compareText(application, "Fetch Interface", "viewdevice_fetchinterfacelink", "Fetch Interface", xml);

		//Edit in view device page
		click_commonMethod(application, "Edit", "viewdevice_Edit", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/editdeviceheader")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated to 'Edit PE Device' page successfully");
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			ScrolltoElement(application, "providerequipment_header", xml);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
			{
				click_commonMethod(application, "View", "viewservicepage_viewdevicelink", xml);
				Thread.sleep(5000);
				waitforPagetobeenable();
				compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not navigated to 'Edit PE Device' page");
		}

		//verify delete device in view device page
		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		click_commonMethod(application, "Delete", "viewdevice_delete", xml);
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
	       alert.dismiss();
	       Thread.sleep(2000);
	      
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     } 
		
		scrolltoend();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
	}

	public static String InterfaceAddress;
	public void verifyFetchInterface(String application, String devicename, String Inservice_status, String Inmaintenance_status, String vendormodel, String managementaddress, String snmpro, String country, String interfacename) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "providerequipment_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/existingdevicegrid"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				if(AddedDeviceNameText.contains(devicename))
				{
					WebElement AddedDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_viewlink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_ViewLink);
					Thread.sleep(3000);
					waitforPagetobeenable();
					compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);
				}
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}

		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		click_commonMethod(application, "Fetch Interface", "viewdevice_fetchinterfacelink", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Fetch interfaces started successfully. Please check the sync status of this device here");
		click_commonMethod(application, "here", "herelink_fetchinterface", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();

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
		Thread.sleep(2000);
		compareText(application, "Status page header", "Statuspage_header", "Device", xml);

		//verify field headers in status page
		compareText(application, "Name field header", "statuspage_nameheader", "Name", xml);
		compareText(application, "Vendor/Model field header", "statuspage_vendormodelheader", "Vendor/Model", xml);
		compareText(application, "Management Address field header", "statuspage_managementaddressheader", "Management Address", xml);
		//compareText(application, "Snmpro field header", "statuspage_snmproheader", "Snmpro", xml);
		GetText(application, "Snmpro field header", "statuspage_snmproheader");
		compareText(application, "Country field header", "statuspage_countryheader", "Country", xml);
		compareText(application, "City field header", "statuspage_cityheader", "City", xml);
		compareText(application, "Site field header", "statuspage_siteheader", "Site", xml);
		compareText(application, "Premise field header", "statuspage_premiseheader", "Premise", xml);

		//verify field values in status page
		compareText(application, "Name", "statuspage_namevalue", devicename, xml);
		compareText(application, "Vendor/Model", "statuspage_vendormodelvalue", vendormodel, xml);
		compareText(application, "Management Address", "statuspage_managementaddressvalue", managementaddress, xml);
		compareText(application, "Snmpro", "statuspage_snmprovalue", snmpro, xml);
		GetText(application, "Country", "statuspage_countryvalue");
		GetText(application, "City", "statuspage_cityvalue");
		GetText(application, "Site", "statuspage_sitevalue");
		GetText(application, "Premise", "statuspage_premisevalue");

		compareText(application, "Status header", "Statuspage_statusheader", "Status", xml);
		compareText(application, "Current Status field header", "statuspage_currentstatusfieldheader", "Current Status", xml);
		compareText(application, "New Status field header", "statuspage_newstatusfieldheader", "New Status", xml);
		if(ServiceStatus.equalsIgnoreCase(Inservice_status))
		{
			compareText(application, "Status", "status_statusvalue", Inservice_status, xml);
		}
		else
		{
			compareText(application, "Status", "status_statusvalue", Inmaintenance_status, xml);
		}
		click_commonMethod(application, "New Status Dropdown", "statuspage_newstatusdropdown", xml);
		WebElement selectNewStatusvalue= getwebelement(xml.getlocator("//locators/" + application + "/statuspage_newstatusdropdownvalue"));
		Clickon(selectNewStatusvalue);
		String NewStatusvalue= getwebelement(xml.getlocator("//locators/" + application + "/statuspage_newstatusdropdownvalue")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue);
		click_commonMethod(application, "OK", "statuspage_okbutton", xml);
		Thread.sleep(2000);
		scrollToTop();
		//compareText(application, "Device status update success message", "Sync_successmsg", "Device Status history successfully changed", xml);
		verifysuccessmessage(application, "Device Status history successfully changed");
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
							for (int i = 0; i < numofrows; i++) {
								try {

									String Devicehistorydata = results.get(i).getText();
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
			compareText(application, "Name", "interface_statuspage_namevalue", interfacename, xml);
			compareText(application, "Interface Address", "interface_statuspage_interfaceaddressvalue", InterfaceAddress, xml);
			if(ServiceStatus.equalsIgnoreCase(Inservice_status))
			{
				compareText(application, "Status", "status_statusvalue", Inservice_status, xml);
			}
			else
			{
				compareText(application, "Status", "status_statusvalue", Inmaintenance_status, xml);
			}
			click_commonMethod(application, "New Status Dropdown", "interface_statuspage_newstatusdropdown", xml);
			WebElement selectNewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue"));
			Clickon(selectNewStatusvalue1);
			String NewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue1);
			click_commonMethod(application, "OK", "interface_statuspage_okbutton", xml);
			Thread.sleep(2000);
			scrollToTop();
			verifysuccessmessage(application, "Interface Status History successfully changed.");
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
							for (int i = 0; i < numofrows; i++) {
								try {
									String Interfacehistorydata = results.get(i).getText();
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

										compareText(application, "Changed By", "interface_statuspage_changedbyvalue", Getkeyvalue("APT_login_1_Username"), xml);
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
		Thread.sleep(2000);
		click_commonMethod(application, "Synchronize", "synchronization_synchronizelink", xml);
		Thread.sleep(1000);
		scrollToTop();
		Thread.sleep(2000);
		verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this device.");
		Thread.sleep(3000);
		scrollToTop();
		clickOnBreadCrumb(application, devicename);
		Thread.sleep(5000);
		waitforPagetobeenable();	
	}

	public void verifyEditDevice(String application, String editDevicename, String editVendorModel, String editTelnet, String editSSH, String editSnmp2C, String editSnmp3,
			String editSnmProNewValue, String editSnmprwNewValue, String editSnmpv3UsernameNewValue, String editSnmpv3AuthpasswordNewValue, String editSnmpv3PrivpasswordNewValue,
			String editManagementAddress, String editCountry, String editExistingCity, String editExistingCityValue, String editExistingSite, String editExistingSiteValue,
			String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, String editNewPremise, String editNewCityName,
			String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, DocumentException, IOException {

		try {

			ScrolltoElement(application, "providerequipment_header", xml);
			Thread.sleep(1000);
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
			{
				click_commonMethod(application, "Edit", "viewservicepage_editdevicelink", xml);
				Thread.sleep(5000);
				waitforPagetobeenable();
				compareText(application, "Edit PE Device header", "editdeviceheader", "Edit PE Device", xml);

				//edit device fields

				//name
				edittextFields_commonMethod(application, "Name", "nametextfield", editDevicename, xml);
				
				//vendormodel
				selectValueInsideDropdown(application, "vendormodelinput", "Vendor/Model", editVendorModel, xml);
				
				//Management address
				javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
				edittextFields_commonMethod(application, "Management Address", "managementaddresstextbox", editManagementAddress, xml);
				
				//SNMP version		
				if((editSnmp2C.equalsIgnoreCase("Yes"))  && (editSnmp3.equalsIgnoreCase("NO"))) {

					editcheckbox_commonMethod(application, editSnmp2C, "c2cradiobutton", "SNMP Version-2c", xml);

					//snmpro	
					edittextFields_SNMPversion(application, "Snmpro", "snmprotextfield", editSnmProNewValue);

					//snmprw 
					edittextFields_SNMPversion(application, "Snmprw", "snmprwtextfield", editSnmprwNewValue);

				}
				else if((editSnmp2C.equalsIgnoreCase("no"))  && (editSnmp3.equalsIgnoreCase("yes"))) {

					editcheckbox_commonMethod(application, editSnmp3, "c3radiobutton", "SNMP Version-3", xml);

					//Snmp v3 Username	
					edittextFields_SNMPversion(application, "Snmp v3 Username", "snmpv3username", editSnmpv3UsernameNewValue);

					//Snmp v3 Auth password
					edittextFields_SNMPversion(application, "Snmp v3 Auth password", "snmpv3authpassword", editSnmpv3AuthpasswordNewValue);

					//Snmp v3 Priv password
					edittextFields_SNMPversion(application, "Snmp v3 Priv password", "snmpv3privpassword", editSnmpv3PrivpasswordNewValue);

				}

				//select country
				scrolltoend();
				Thread.sleep(2000);
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

					selectValueInsideDropdown(application, "citydropdowninput", "City", editExistingCityValue, xml);

					//Existing Site
					if(editExistingSite.equalsIgnoreCase("yes") & editNewSite.equalsIgnoreCase("no")) {
						selectValueInsideDropdown(application, "sitedropdowninput", "Site", editExistingSiteValue, xml);
						
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
				
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
				
		} catch (StaleElementReferenceException e) {

			e.printStackTrace();
		}

		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Device successfully updated");
		Thread.sleep(2000);

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


	public void verifyViewpage_UpdatedDevicedetails(String application, String editDevicename, String editVendorModel, String editTelnet, String editSSH, String editSnmp2C, String editSnmp3,
			String editSnmProNewValue, String editSnmprwNewValue, String editSnmpv3UsernameNewValue, String editSnmpv3AuthpasswordNewValue, String editSnmpv3PrivpasswordNewValue,
			String editManagementAddress, String name, String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
			Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue, String Snmpv3Privpasswordvalue, String managementaddress) throws InterruptedException, IOException, DocumentException {

		ScrolltoElement(application, "providerequipment_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			click_commonMethod(application, "View", "viewservicepage_viewdevicelink", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}

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
			GetText(application, "Snmpro", "viewpage_snmpro");
			GetText(application, "Snmprw", "viewpage_snmprw");

		}
		else if(editSnmp2C.equalsIgnoreCase("Yes") && editSnmp3.equalsIgnoreCase("null")) {

			GetText(application, "SNMP Version", "viewpage_snmpversion");
			GetText(application, "Snmpro", "viewpage_snmpro");
			GetText(application, "Snmprw", "viewpage_snmprw");
		}
		else if(editSnmp2C.equalsIgnoreCase("no") && editSnmp3.equalsIgnoreCase("yes")) {
			GetText(application, "SNMP Version", "viewpage_snmpversion");
			GetText(application, "Snmp v3 Username", "viewpage_snmpv3username");
			GetText(application, "Snmp v3 Auth password", "viewpage_snmpv3authpassword");
			GetText(application, "Snmp v3 Priv password", "viewpage_snmpv3privpassword");
		}
		else if(editSnmp2C.equalsIgnoreCase("null") && editSnmp3.equalsIgnoreCase("yes")) {
			GetText(application, "SNMP Version", "viewpage_snmpversion");
			GetText(application, "Snmp v3 Username", "viewpage_snmpv3username");
			GetText(application, "Snmp v3 Auth password", "viewpage_snmpv3authpassword");
			GetText(application, "Snmp v3 Priv password", "viewpage_snmpv3privpassword");

		}
		else if((editSnmp2C.equalsIgnoreCase("null")) || (editSnmp2C.equalsIgnoreCase("no"))) {

			GetText(application, "SNMP Version", "viewpage_snmpversion");
		}
		else if((editSnmp3.equalsIgnoreCase("null")) || (editSnmp3.equalsIgnoreCase("no"))) {
			GetText(application, "SNMP Version", "viewpage_snmpversion");
		}
		//Country
			GetText(application, "Country", "viewpage_country");
		//City
			GetText(application, "City", "viewpage_city");
		//Site
			GetText(application, "Site", "viewpage_site");
			
		//Premise
			GetText(application, "Premise", "viewpage_premise");
		
		Thread.sleep(2000);
	}


	public void addExistingPEDevice(String application, String existingdevicename) throws InterruptedException, DocumentException {
		
		ScrolltoElement(application, "providerequipment_header", xml);
		Thread.sleep(1000);
		compareText(application, "Provider Equipment (PE)", "providerequipment_header", "Provider Equipment (PE)", xml);
		click_commonMethod(application, "Add PE Device", "addpedevice_link", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		compareText(application, "Add PE Device Header", "addpedevice_header", "Add PE Device", xml);
		//addDropdownValues_commonMethod(application, "Type PE name to filter", "typepename_dropdown", existingdevicename, xml);
		
		boolean availability=false;
		try {  
			availability=getwebelement(xml.getlocator("//locators/" + application + "/typepename_dropdown")).isDisplayed();
			if(availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "PE Name dropdown is displaying");
				System.out.println("PE Name dropdown is displaying");
				Log.info("PE Name dropdown is displaying");

				if(existingdevicename.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under PE Name dropdown");
					System.out.println("No values selected under PE Name dropdown");
					Log.info("No values selected under PE Name dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='Type PE name to filter']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = getwebelements("//div[label[text()='Type PE name to filter']]/div//span[@role='option']");

					ExtentTestManager.getTest().log(LogStatus.PASS, "List of values inside PE Name dropdown is:  ");
					System.out.println( "List of values inside PE Name dropdown is:  ");
					Log.info("List of values inside PE Name dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("PE Names : " + valuetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
						System.out.println(" " + valuetypes.getText());
					}

					Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='Type PE name to filter']]/div//input"), existingdevicename);	
					Thread.sleep(1000);
					ScrolltoElement(application, "nextbutton", xml);
					Thread.sleep(2000);
					Clickon(getwebelement("(//span[text()='"+existingdevicename+"'])[1]"));
					Thread.sleep(3000);

					String actualValue=getwebelement("//div[label[text()='Type PE name to filter']]//div//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "PE Name dropdown value selected as: "+ actualValue );
					System.out.println("PE Name dropdown value selected as: "+ actualValue);
					Log.info("PE Name dropdown value selected as: "+ actualValue);

				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "PE Name is not displaying");
				System.out.println("PE Name is not displaying");
				Log.info("PE Name is not displaying");
			}
		}catch(NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "PE Name is not displaying");
			System.out.println("PE Name is not displaying");
			Log.info("PE Name is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to perform selection under PE Name dropdown");
			System.out.println("NO value selected under PE Name dropdown");
			Log.info("NO value selected under PE Name dropdown");
		}
		
		Thread.sleep(2000);

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
		Thread.sleep(2000);
		scrolltoend();
		Thread.sleep(2000);
		click_commonMethod(application, "Next", "Next_Button", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Device successfully created");
		Thread.sleep(2000);
	}

	public void verifyExistingDevice_ViewDevicedetails(String application, String existingdevicename) throws InterruptedException, IOException, DocumentException {

		compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);

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
		Thread.sleep(2000);

	}

	public void deleteExistingDevice(String application, String existingdevicename) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "providerequipment_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevices_list"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				if(AddedDeviceNameText.contains(existingdevicename))
				{
					WebElement AddedDevice_DeletefromserviceLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_deletefromservicelink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_DeletefromserviceLink);
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
				     break;
				}
				
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}
	}

	public void deleteDevice(String application, String devicename) throws InterruptedException, DocumentException {

		scrolltoend();
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevices_list"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				if(AddedDeviceNameText.contains(devicename))
				{
					WebElement AddedDevice_DeletefromserviceLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_deletefromservicelink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_DeletefromserviceLink);
					Thread.sleep(2000);
					
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
					    Thread.sleep(2000);
					  }catch(Exception e) {
					     e.printStackTrace();
					  } 
					  verifysuccessmessage(application, "Device successfully removed from service.");
				break;
				}
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}
	}

	
	public void verify_Cisco_RouterTools(String application, String commandIPv4, String commandIPv6, String ipAddress, 
			String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "routertools_header", xml);
		Thread.sleep(1000);

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

	public void verify_Juniper_RouterTools(String application, String commandIPv4, String ipAddress, 
			String vrfname_ipv4) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "routertools_header", xml);
		Thread.sleep(1000);

		//Command IPV4	
		addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);

		hostnametextField_IPV4(application, commandIPv4, ipAddress);

		//vrfNametextField_IPV4(application, commandIPv4, vrfname_ipv4);

		executeCommandAndFetchTheValue(application, "executebutton_Ipv4");

	}

	public String DeviceName(String application) throws InterruptedException, DocumentException {
		
		scrollToTop();
		String DeviceNameValue= GetText(application, "Name", "viewpage_devicename");
		
		return DeviceNameValue;
	}

	public String VendorModel(String application) throws InterruptedException, DocumentException {
		
		scrollToTop();
		String VendorModelValue= GetText(application, "Vendor/Model", "viewpage_vendormodel");
		
		return VendorModelValue;
	}
	
	
	public void verify_CiscoVendor_AddInterface(String application, String configureinterface_checkbox
			, String interfaceaddressrange_value
			, String eipallocation_city, String existingAddressRangeIPv4selection, String existingAddressIPv4DropdownValue
			, String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSizeValue_IPv4
			,String existingAddressRangeIPv6selection, String existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection
			, String newinterfaceAddressrangeIPv6, String subnetSizeValue_IPv6, String availableBlocksValue_IPv6
			, String link_value, String bearertype_value, String ciscovendor_bandwidth_value, String encapsulation_value
			, String bgp_checkbox, String framingtype_value, String vlanID_value, String bgptemplate_dropdownvalue
			, String cpewan_value, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value
			, String bgppassword_value) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "interfaces_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
		click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
		click_commonMethod(application, "Add Interface/Link", "addinterface_link", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		GetText(application, "Add Interface header", "addinterface_header");
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(1000);
		scrollToTop();
		//verify warning messages in add interface page
		warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);
		warningMessage_commonMethod(application, "bearertype_warngmsg", "Bearer Type", xml);
		warningMessage_commonMethod(application, "encapsulation_warngmsg", "Encapsulation", xml);

		//Add Interface
		addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", configureinterface_checkbox, "no", xml);
		compareText(application, "Network", "network_fieldvalue", "XFER", xml);

		interfaceAddressRangeIPv4(application, existingAddressRangeIPv4selection, newAddressRangeIpv4selection, subnetSizeValue_IPv4, eipallocation_city, existingAddressIPv4DropdownValue, newinterfaceAddressrange);
		interfaceAddressRangeIPv6(application, existingAddressRangeIPv6selection, newAddressRangeIpv6selection, subnetSizeValue_IPv6, availableBlocksValue_IPv6, newinterfaceAddressrangeIPv6);
		
		ScrolltoElement(application, "link_textfield", xml);
		addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
		selectValueInsideDropdown(application, "bearertype_dropdown", "Bearer Type", bearertype_value, xml);
		Thread.sleep(2000);
		selectValueInsideDropdown(application, "bandwidth_dropdown", "Bandwidth", ciscovendor_bandwidth_value, xml);
		if(bearertype_value.equalsIgnoreCase("E1")) {
			selectValueInsideDropdown(application, "framingtype_dropdown", "Framing Type", framingtype_value, xml);
			selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", encapsulation_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("Ethernet"))
		{
			addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanID_value, xml);
		}
		else
		{
			selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", encapsulation_value, xml);
		}
		
		Thread.sleep(1000);
		ScrolltoElement(application, "link_textfield", xml);
		InterfaceName= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_textfield")).getAttribute("value");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName);
		
		addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", bgp_checkbox, "no", xml);
		if(bgp_checkbox.equalsIgnoreCase("yes"))
		{
			addDropdownValues_commonMethod(application, "BGP Templates Generate For", "multilink_bgptemplate_dropdown", bgptemplate_dropdownvalue, xml);
			addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
			addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
			addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
			addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
			addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
		}

		scrolltoend();
		//configuration panel in add interface page
		generateConfiguration(application);
		
		Thread.sleep(5000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Interface added successfully");
		Thread.sleep(2000);

		//Verify added interface
		ScrolltoElement(application, "interfaces_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		WebElement AddedInterfaces= getwebelement(xml.getlocator("//locators/" + application + "/addedinterfaces"));
		String addedinterfacecheck= AddedInterfaces.getAttribute("style");
		if(!addedinterfacecheck.contains("height: 1px"))
		{
			compareText(application, "Interfaces", "interfaces_header", "Interfaces", xml);
			String Interface_RowID= getwebelement(xml.getlocator("//locators/" + application + "/interface_rowid").replace("value", InterfaceName)).getAttribute("row-id");

			WebElement InterfaceName_value= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_tablevalue").replace("value", Interface_RowID));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
			WebElement Link_value= getwebelement(xml.getlocator("//locators/" + application + "/link_tablevalue").replace("value", Interface_RowID));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
			WebElement InterfaceAddressRange_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddressrange_tablevalue").replace("value", Interface_RowID));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
			WebElement InterfaceAddress_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_tablevalue").replace("value", Interface_RowID));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
			WebElement BearerType_value= getwebelement(xml.getlocator("//locators/" + application + "/bearertype_tablevalue").replace("value", Interface_RowID));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
			WebElement Bandwidth_value= getwebelement(xml.getlocator("//locators/" + application + "/bandwidth_tablevalue").replace("value", Interface_RowID));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
			Clickon(Bandwidth_value);
			Bandwidth_value.sendKeys(Keys.TAB);
			WebElement vlanIDValue= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_tablevalue").replace("value", Interface_RowID));
			ExtentTestManager.getTest().log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanIDValue);
			Clickon(vlanIDValue);
			vlanIDValue.sendKeys(Keys.TAB);
			WebElement IfInOctets_Value= getwebelement(xml.getlocator("//locators/" + application + "/ifinoctets_tablevalue").replace("value", Interface_RowID));
			ExtentTestManager.getTest().log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
			Thread.sleep(2000);

		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Interfaces added under Interfaces panel");
		}
		scrolltoend();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
	}

	public void verify_CiscoVendor_EditInterface(String application, String devicename, String edit_configureinterface_checkbox
			, String edit_network, String interfaceaddressrange_value
			, String eipallocation_city, String existingAddressRangeIPv4selection, String existingAddressIPv4DropdownValue
			, String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSizeValue_IPv4
			,String existingAddressRangeIPv6selection, String existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection
			, String newinterfaceAddressrangeIPv6, String subnetSizeValue_IPv6, String availableBlocksValue_IPv6
			, String edit_linkvalue, String edit_bearertype_value, String edit_ciscovendor_bandwidth_value
			, String edit_framingtype_value, String edit_encapsulation_value, String edit_vlanID_value
			, String edit_bgp_checkbox, String edit_bgptemplate_dropdownvalue, String edit_cpewan_value
			, String edit_cpewanipv6_value, String edit_descriptionfield_value, String edit_ascustomerfield_value
			, String edit_bgppassword_value, String ipsubnetipv6_value, String ipsubnetipv4_value
			, String edit_eipallocation_subnetsize, String edit_eipallocation_ipv6_subnetsize) throws InterruptedException, DocumentException, IOException {
		
		//edit Interface
		scrolltoend();
		if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
		{
		click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		}
		Thread.sleep(2000);
		WebElement SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", InterfaceName));
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
			Thread.sleep(1000);
			WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", devicename));
			Clickon(AddedDevice_Interface_Actiondropdown);
			

			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			compareText(application, "Edit Interface/Link", "editinterface_header", "Edit", xml);
			scrollToTop();
			editcheckbox_commonMethod(application, edit_configureinterface_checkbox, "configureinterface_checkbox", "Configure Interface on Device", xml);
			
			addDropdownValues_commonMethod(application, "Network", "network_fieldvalue", edit_network, xml);

			interfaceAddressRangeIPv4(application, existingAddressRangeIPv4selection, newAddressRangeIpv4selection, subnetSizeValue_IPv4, eipallocation_city, existingAddressIPv4DropdownValue, newinterfaceAddressrange);
			interfaceAddressRangeIPv6(application, existingAddressRangeIPv6selection, newAddressRangeIpv6selection, subnetSizeValue_IPv6, availableBlocksValue_IPv6, newinterfaceAddressrangeIPv6);
			
			ScrolltoElement(application, "link_textfield", xml);
			edittextFields_commonMethod(application, "Link", "link_textfield", edit_linkvalue, xml);
			addDropdownValues_commonMethod(application, "Bearer Type", "bearertype_dropdown", edit_bearertype_value, xml);
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", edit_ciscovendor_bandwidth_value, xml);
			if(edit_bearertype_value.equalsIgnoreCase("E1")) {
				addDropdownValues_commonMethod(application, "Framing Type", "framingtype_dropdown", edit_framingtype_value, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("Ethernet"))
			{
				edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", edit_vlanID_value, xml);
			}
			else
			{
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
			}
			Thread.sleep(1000);
			
			ScrolltoElement(application, "link_textfield", xml);
			InterfaceName= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_textfield")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as: "+InterfaceName);
			
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
			Thread.sleep(1000);
			
			scrolltoend();
			//configuration panel in edit interface page
			generateConfiguration(application);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Interface successfully updated");
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Interface is not added");
		}

	}
	
	public void generateConfiguration(String application) throws InterruptedException, DocumentException, IOException {
		
		//perform Generate configuration
		boolean configurationpanel=false;
		configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/configuration_header")).isDisplayed();
		if(configurationpanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
			System.out.println("'Configuration' panel is displaying");
			
			click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
			waitforPagetobeenable();
			Thread.sleep(2000);
			if(getwebelement(xml.getlocator("//locators/" + application + "/staus_statuspopup")).isDisplayed()) {
				click_commonMethod(application, "OK", "configAlert_okbutton", xml);
				}
				  waitforPagetobeenable();
			scrolltoend();
			Thread.sleep(1000);
			
			String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/configuration_textarea")));
			if(configurationvalues.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				System.out.println("After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
			}
		
			click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
			Thread.sleep(2000);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Configuration' panel is not displaying");
			System.out.println("'Configuration' panel is not displaying");
			click_commonMethod(application, "OK", "okbutton", xml);
		}
	}
	
	public void Juniper_generateConfiguration(String application) throws InterruptedException, DocumentException, IOException {
		
		//perform Generate configuration
		boolean configurationpanel=false;
		configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/configuration_header")).isDisplayed();
		if(configurationpanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
			System.out.println("'Configuration' panel is displaying");
			
			click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
			waitforPagetobeenable();
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/staus_statuspopup")).isDisplayed()) {
			click_commonMethod(application, "OK", "configAlert_okbutton", xml);
			}
			  waitforPagetobeenable();
			scrolltoend();
			Thread.sleep(1000);
			
			String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/configuration_textarea")));
			if(configurationvalues.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				System.out.println("After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
			}
		
			click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
			Thread.sleep(2000);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "'Configuration' panel is not displaying");
			System.out.println("'Configuration' panel is not displaying");
			click_commonMethod(application, "OK", "okbutton", xml);
		}
	}
	
	public void interfaceAddressRangeIPv4(String application, String existingAddressRangeIPv4selection
			, String newAddressRangeIpv4selection, String subnetSizeValue_IPv4, String eipallocation_city
			, String existingAddressIPv4DropdownValue, String newinterfaceAddressrange) throws InterruptedException, DocumentException, IOException {
		
		boolean addressValue=false;
		
	//IPv4 Configuration
	if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
		
	//EIP Allocation
		Thread.sleep(1000);
		click_commonMethod(application, "EIP Allocation button", "eipallocation1_button" , xml);
		
		compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
		GetText(application, "Subnet Type", "subnettype_value");
		addDropdownValues_commonMethod(application, "City", "eipallocation_citydropdown", eipallocation_city, xml);
		addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", subnetSizeValue_IPv4, xml);
		GetText(application, "Available Pools", "eipallocation_availablepools_value");
		//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
		Thread.sleep(1000);
		//EIPallocationSuccessMessage(application, "successfully allocated");
		click_commonMethod(application, "Close", "closesymbol", xml);
		Thread.sleep(2000);
		
		waitForpageload();  
		waitforPagetobeenable();
		click_commonMethod(application, "Get Address", "getaddress1_button", xml);
		Thread.sleep(1000);
		
		 Clickon(getwebelement("//div[label[text()='Interface Address Range']]//div[text()='×']"));
		  Thread.sleep(3000);	 
		 
		 String interfaceAddressRange=GetText(application, "Value", "interfaceaddressrange_optionslist");
		 
		if(interfaceAddressRange.isEmpty()) {
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range' dropdown");
			System.out.println("No values displaying under 'Interface Address Range' dropdown");
			  
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
			System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
				
			Thread.sleep(5000);
			waitForpageload();   
			waitforPagetobeenable();
			
			addressValue = getwebelement(xml.getlocator("//locators/" + application + "/interfacerange_Address_dropdown")).isDisplayed();
			if(addressValue) {
				
				selectEnableValueUnderAddressDropdown(application, "Address", "interfacerange_Address_dropdown" , existingAddressIPv4DropdownValue);
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Address' dropdown is not displaying");
				System.out.println("'Address' dropdown is not displaying");
			}
		}
	}
	else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
		
		  addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield" , newinterfaceAddressrange , xml);
		  GetText(application, "Address", "address_textfield");
		  
	}
}

	
public void interfaceAddressRangeIPv6(String application, String existingAddressRangeIPv6selection
		, String newAddressRangeIpv6selection, String subnetSizeValue_IPv6, String availableBlocksValue_IPv6
		, String newinterfaceAddressrangeIPv6) throws InterruptedException, DocumentException, IOException {
	
	
	ScrolltoElement(application, "network_fieldvalue", xml);
//IPv6 Configuration
	if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
		
		//EIP Allocation
		click_commonMethod(application, "EIP Allocation button", "eipallocation2_button" , xml);
		GetText(application, "EIP Address Allocation header", "eipallocationIPv6_header");
		GetText(application, "Subnet Type", "subnettype_value");
		GetText(application, "Space Name", "eipallocation_spacename");
		addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_ipv6_subnetsize", subnetSizeValue_IPv6, xml);
		addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", availableBlocksValue_IPv6, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
		Thread.sleep(2000);
		
			//EIPallocationSuccessMessage(application, "successfully allocated");
			
			click_commonMethod(application, "x", "closesymbol", xml);
			
			waitForpageload();
			waitforPagetobeenable();
			click_commonMethod(application, "Get Address", "getaddress2_button", xml);
			Thread.sleep(1000);
			
			String interfaceAddressRange=getwebelement(xml.getlocator("//locators/" + application + "/interfacerange_AddressIpv6_dropdown")).getText();
			
			if(interfaceAddressRange.isEmpty()) {
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range_IPv6' dropdown");
				System.out.println("No values displaying under 'Interface Address Range_IPv6' dropdown");
				
			}else {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
				System.out.println("'Interface Address Range_IPv6' dropdown value displays as: "+ interfaceAddressRange);
				
				click_commonMethod(application, ">>" , "interfaceaddressIPv6_Addarrow", xml);
				Thread.sleep(4000);
				waitForpageload();  waitforPagetobeenable();
				
				String AddressValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/addressIPv6_textfield")).getAttribute("value");
				  if(AddressValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
					  System.out.println("No values dipslaying under 'Address_IPv6' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+AddressValueIntextField);
					  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+AddressValueIntextField);
				  }
		
			}
	}
		else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
			
			  addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrangeIPv6_textfield" , newinterfaceAddressrangeIPv6 , xml);
			
			  click_commonMethod(application, ">>" , "interfaceaddressIPv6_Addarrow", xml);
			  
			  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/addressIPv6_textfield")).getAttribute("value");
			  if(interfaceValueIntextField.isEmpty()) {
				  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
				  System.out.println("No values dipslaying under 'Address_IPv6' text field");
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
				  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
			  }
		}
}

public void EIPallocationSuccessMessage(String application, String expected) throws InterruptedException {
	
	waitForpageload();
	waitforPagetobeenable();
	
	scrollToTop();
	Thread.sleep(3000);
	try {	
		
		boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert")).isDisplayed();

		if(successMsg) {
			String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
			if(alrtmsg.contains(expected)) {
				ExtentTestManager.getTest().log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
				System.out.println("Message is verified. It is displaying as: "+alrtmsg);
				
			}else if(expected.equals(alrtmsg)){
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
		Thread.sleep(2000);
		
	}catch(Exception e) {
		Log.info("failure in fetching success message");
		ExtentTestManager.getTest().log(LogStatus.FAIL, expected+ " Message is not displaying");
		System.out.println(expected+ " message is not getting dislpayed");
		Thread.sleep(2000);
	}

}

public void selectEnableValueUnderAddressDropdown(String application, String labelname, String xpath, String expectedValueToAdd ) {
	
	  List<String> ls = new ArrayList<String>();
	  boolean availability = false;
	  
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
							ls.add(valuetypes.getText());
				}
				
				    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
		            System.out.println("list of values inside "+labelname+" dropdown is: "+ls);
		            
		         Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectFirstValueUnderAddressDropdown")));
		         Thread.sleep(1000);
				
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

	
	public void verify_JuniperVendor_AddInterface(String application, String juniper_configureinterface_checkbox
			, String interfaceaddressrange_value
			, String eipallocation_city, String existingAddressRangeIPv4selection, String existingAddressIPv4DropdownValue
			, String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSizeValue_IPv4
			,String existingAddressRangeIPv6selection, String existingAddressIPv6DropdownValue
			, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSizeValue_IPv6
			, String availableBlocksValue_IPv6, String link_value, String bearertype_value, String bandwidth_value
			, String encapsulation_value, String bgp_checkbox, String framingtype_value, String vlanID_value
			, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value
			, String ascustomerfield_value, String bgppassword_value, String cardtype_dropdownvalue, String clocksource_value
			, String STM1Number_value, String bearerNo_value, String unitid_value, String slot_value, String pic_value
			, String port_value, String ivmanagement_checkbox, String atricaconnected_checkbox
			, String cardtype_dropdownvalue_gigabit) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "interfaces_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
		click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
		click_commonMethod(application, "Add Interface/Link", "addinterface_link", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		scrollToTop();
		GetText(application, "Add Interface header", "addinterface_header");
		ScrolltoElement(application, "executeandsave_button", xml);
		Thread.sleep(2000);
		click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
		Thread.sleep(1000);
		scrollToTop();
		//verify warning messages in add interface page
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
		Thread.sleep(2000);
		addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", juniper_configureinterface_checkbox, "yes", xml);
		Thread.sleep(2000);
		compareText(application, "Network", "network_fieldvalue", "XFER", xml);

		interfaceAddressRangeIPv4(application, existingAddressRangeIPv4selection, newAddressRangeIpv4selection, subnetSizeValue_IPv4, eipallocation_city, existingAddressIPv4DropdownValue, newinterfaceAddressrange);
		interfaceAddressRangeIPv6(application, existingAddressRangeIPv6selection, newAddressRangeIpv6selection, subnetSizeValue_IPv6, availableBlocksValue_IPv6, newinterfaceAddressrangeIPv6);
		
		ScrolltoElement(application, "getaddress2_button", xml);
		addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
		selectValueInsideDropdown(application, "bearertype_dropdown", "Bearer Type", bearertype_value, xml);
		Thread.sleep(2000);
		if(bearertype_value.equalsIgnoreCase("10Gigabit Ethernet") || bearertype_value.equalsIgnoreCase("Gigabit Ethernet")) {
			selectValueInsideDropdown(application, "bandwidth_dropdown", "Bandwidth", bandwidth_value, xml);
			selectValueInsideDropdown(application, "cardtype_dropdown", "Card Type", cardtype_dropdownvalue_gigabit, xml);
			selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", encapsulation_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("E1"))
		{
			selectValueInsideDropdown(application, "bandwidth_dropdown", "Bandwidth", bandwidth_value, xml);
			selectValueInsideDropdown(application, "cardtype_dropdown", "Card Type", cardtype_dropdownvalue, xml);
			selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", encapsulation_value, xml);
			selectValueInsideDropdown(application, "framingtype_dropdown", "Framing Type", framingtype_value, xml);
			selectValueInsideDropdown(application, "clocksource_dropdown", "Clock Source", clocksource_value, xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
			addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", bearerNo_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("E3") || bearertype_value.equalsIgnoreCase("T3"))
		{
			selectValueInsideDropdown(application, "bandwidth_dropdown", "Bandwidth", bandwidth_value, xml);
			selectValueInsideDropdown(application, "cardtype_dropdown", "Card Type", cardtype_dropdownvalue, xml);
			selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", encapsulation_value, xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
			addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", bearerNo_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("STM-1"))
		{
			selectValueInsideDropdown(application, "bandwidth_dropdown", "Bandwidth", bandwidth_value, xml);
			selectValueInsideDropdown(application, "cardtype_dropdown", "Card Type", cardtype_dropdownvalue, xml);
			selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", encapsulation_value, xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
		}
		else
		{
			selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", encapsulation_value, xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
		}
		Thread.sleep(1000);
		addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", unitid_value, xml);
		addtextFields_commonMethod(application, "Slot", "slot_textfield", slot_value, xml);
		addtextFields_commonMethod(application, "Pic", "pic_textfield", pic_value, xml);
		addtextFields_commonMethod(application, "Port", "port_textfield", port_value, xml);
		Thread.sleep(1000);
		InterfaceName= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_textfield")).getAttribute("value");
		if(encapsulation_value.equalsIgnoreCase("802.1q"))
		{
			addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanID_value, xml);
		}
		ScrolltoElement(application, "interfacename_textfield", xml);
		addCheckbox_commonMethod(application, "ivmanagement_checkbox", "IV Management", ivmanagement_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "atricaconnected_checkbox", "Atrica Connected", atricaconnected_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", bgp_checkbox, "no", xml);
		if(bgp_checkbox.equalsIgnoreCase("yes"))
		{
			selectValueInsideDropdown(application, "bgptemplate_dropdown", "BGP Templates Generate For", bgptemplate_dropdownvalue, xml);
			addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
			addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
			addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
			addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
			addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
		}
		scrolltoend();
		//configuration panel in add interface page
		Juniper_generateConfiguration(application);
		Thread.sleep(4000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Interface added successfully");
		Thread.sleep(2000);

		//Verify added interface
		ScrolltoElement(application, "interfaces_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		WebElement AddedInterfaces= getwebelement(xml.getlocator("//locators/" + application + "/addedinterfaces"));
		String addedinterfacecheck= AddedInterfaces.getAttribute("style");
		if(!addedinterfacecheck.contains("height: 1px"))
		{
			compareText(application, "Interfaces", "interfaces_header", "Interfaces", xml);
			String Interface_RowID= getwebelement(xml.getlocator("//locators/" + application + "/interface_rowid").replace("value", InterfaceName)).getAttribute("row-id");

			String InterfaceName_value= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_tablevalue").replace("value", Interface_RowID)).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
			String Link_value= getwebelement(xml.getlocator("//locators/" + application + "/link_tablevalue").replace("value", Interface_RowID)).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
			String InterfaceAddressRange_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddressrange_tablevalue").replace("value", Interface_RowID)).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
			String InterfaceAddress_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_tablevalue").replace("value", Interface_RowID)).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
			String BearerType_value= getwebelement(xml.getlocator("//locators/" + application + "/bearertype_tablevalue").replace("value", Interface_RowID)).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
			WebElement Bandwidth= getwebelement(xml.getlocator("//locators/" + application + "/bandwidth_tablevalue").replace("value", Interface_RowID));
			String Bandwidth_value= Bandwidth.getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
			Clickon(Bandwidth);
			Bandwidth.sendKeys(Keys.TAB);
			WebElement vlanID= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_tablevalue").replace("value", Interface_RowID));
			String vlanID_Value= vlanID.getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanID_Value);
			Clickon(vlanID);
			vlanID.sendKeys(Keys.TAB);
			String IfInOctets_Value= getwebelement(xml.getlocator("//locators/" + application + "/ifinoctets_tablevalue").replace("value", Interface_RowID)).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
			Thread.sleep(2000);

		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Interfaces added under Interfaces panel");
		}
		scrolltoend();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		
	}
	
	public boolean availableBlocks_dropdownCheck(String application, String labelname, String xpath) {
		
		boolean availability=false;
		boolean Value=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
			  
				  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
				  Thread.sleep(3000);
				  
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

	public void verify_JuniperVendor_EditInterface(String application, String devicename, String edit_configureinterface_checkbox
			, String edit_network, String interfaceaddressrange_value
			, String eipallocation_city, String existingAddressRangeIPv4selection, String existingAddressIPv4DropdownValue
			, String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSizeValue_IPv4
			,String existingAddressRangeIPv6selection, String existingAddressIPv6DropdownValue
			, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSizeValue_IPv6
			, String availableBlocksValue_IPv6, String edit_linkvalue, String edit_bearertype_value, String edit_bandwidth_value
			, String edit_framingtype_value, String edit_encapsulation_value, String edit_vlanID_value, String edit_bgp_checkbox
			, String edit_bgptemplate_dropdownvalue, String edit_cpewan_value, String edit_cpewanipv6_value
			, String edit_descriptionfield_value, String edit_ascustomerfield_value, String edit_bgppassword_value
			, String edit_cardtype_dropdownvalue, String edit_clocksource_value, String edit_STM1Number_value
			, String edit_bearerNo_value, String edit_unitid_value, String edit_slot_value, String edit_pic_value
			, String edit_port_value, String edit_ivmanagement_checkbox, String edit_atricaconnected_checkbox
			, String cardtype_dropdownvalue_gigabit, String edit_ipsubnetipv6_value, String edit_ipsubnetipv4_value) throws InterruptedException, DocumentException, IOException {
		
		//edit Interface
		ScrolltoElement(application, "providerequipment_header", xml);
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
		{
		click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		}
		Thread.sleep(1000);
		scrolltoend();
		WebElement SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", InterfaceName));
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
			Thread.sleep(1000);
			WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", devicename));
			Clickon(AddedDevice_Interface_Actiondropdown);

			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
			compareText(application, "Edit Interface/Link", "editinterface_header", "Edit", xml);

			editcheckbox_commonMethod(application, edit_configureinterface_checkbox, "configureinterface_checkbox", "Configure Interface on Device", xml);
			addDropdownValues_commonMethod(application, "Network", "network_fieldvalue", edit_network, xml);

			interfaceAddressRangeIPv4(application, existingAddressRangeIPv4selection, newAddressRangeIpv4selection, subnetSizeValue_IPv4, eipallocation_city, existingAddressIPv4DropdownValue, newinterfaceAddressrange);
			interfaceAddressRangeIPv6(application, existingAddressRangeIPv6selection, newAddressRangeIpv6selection, subnetSizeValue_IPv6, availableBlocksValue_IPv6, newinterfaceAddressrangeIPv6);
			
			ScrolltoElement(application, "link_textfield", xml);
			edittextFields_commonMethod(application, "Link", "link_textfield", edit_linkvalue, xml);
			
			//addDropdownValues_commonMethod(application, "Bearer Type", "bearertype_dropdown", edit_bearertype_value, xml);
			selectValueInsideDropdown(application, "bearertype_dropdown", "Bearer Type", edit_bearertype_value, xml);
			if(edit_bearertype_value.equalsIgnoreCase("10Gigabit Ethernet") || edit_bearertype_value.equalsIgnoreCase("Gigabit Ethernet") || edit_bearertype_value.equalsIgnoreCase("Gigabit Ethernet")) {
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", edit_bandwidth_value, xml);
				addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", edit_cardtype_dropdownvalue, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("E1"))
			{
				selectValueInsideDropdown(application, "bandwidth_dropdown", "Bandwidth", edit_bandwidth_value, xml);
				selectValueInsideDropdown(application, "cardtype_dropdown", "Card Type", edit_cardtype_dropdownvalue, xml);
				selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", edit_encapsulation_value, xml);
				selectValueInsideDropdown(application, "framingtype_dropdown", "Framing Type", edit_framingtype_value, xml);
				selectValueInsideDropdown(application, "clocksource_dropdown", "Clock Source", edit_clocksource_value, xml);
				edittextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				edittextFields_commonMethod(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("E3") || edit_bearertype_value.equalsIgnoreCase("T3"))
			{
				selectValueInsideDropdown(application, "bandwidth_dropdown", "Bandwidth", edit_bandwidth_value, xml);
				selectValueInsideDropdown(application, "cardtype_dropdown", "Card Type", edit_cardtype_dropdownvalue, xml);
				selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", edit_encapsulation_value, xml);
				edittextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				edittextFields_commonMethod(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("STM-1"))
			{
				selectValueInsideDropdown(application, "bandwidth_dropdown", "Bandwidth", edit_bandwidth_value, xml);
				selectValueInsideDropdown(application, "cardtype_dropdown", "Card Type", edit_cardtype_dropdownvalue, xml);
				selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", edit_encapsulation_value, xml);
				edittextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
			}
			else
			{
				selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", edit_encapsulation_value, xml);
				edittextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
			}
			Thread.sleep(1000);
			edittextFields_commonMethod(application, "Unit ID", "unitid_textfield", edit_unitid_value, xml);
			edittextFields_commonMethod(application, "Slot", "slot_textfield", edit_slot_value, xml);
			edittextFields_commonMethod(application, "Pic", "pic_textfield", edit_pic_value, xml);
			edittextFields_commonMethod(application, "Port", "port_textfield", edit_port_value, xml);
			
			InterfaceName= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_textfield")).getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as: "+InterfaceName);
			
			if(edit_encapsulation_value.equalsIgnoreCase("802.1q"))
			{
				edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", edit_vlanID_value, xml);
			}
			editcheckbox_commonMethod(application, edit_ivmanagement_checkbox, "ivmanagement_checkbox", "IV Management", xml);
			editcheckbox_commonMethod(application, edit_atricaconnected_checkbox, "atricaconnected_checkbox", "Atrica Connected", xml);
			editcheckbox_commonMethod(application, edit_bgp_checkbox, "bgp_checkbox", "BGP", xml);
			if(edit_bgp_checkbox.equalsIgnoreCase("yes"))
			{
				selectValueInsideDropdown(application, "bgptemplate_dropdown", "BGP Templates Generate For", edit_bgptemplate_dropdownvalue, xml);
				edittextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", edit_cpewan_value, xml);
				edittextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", edit_cpewanipv6_value, xml);
				edittextFields_commonMethod(application, "Description", "bgp-descriptionfield", edit_descriptionfield_value, xml);
				edittextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", edit_ascustomerfield_value, xml);
				edittextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", edit_bgppassword_value, xml);
				
				//Add IP Subnet IPv4
				scrolltoend();
				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
				edittextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", edit_ipsubnetipv4_value, xml);
				
				//Remove IP Subnet IPv4	
				click_commonMethod(application, "IP Subnet IPv4 Remove", "ipsubnetipv4_removebutton", xml);
				//Add IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
				edittextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", edit_ipsubnetipv4_value, xml);
				
				
				//Add IP Subnet IPv6
				scrolltoend();
				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
				edittextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", edit_ipsubnetipv6_value, xml);
				
				//Remove IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Remove", "ipsubnetipv6_removebutton", xml);
				//Add IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
				edittextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", edit_ipsubnetipv6_value, xml);
				
			}

			//configuration panel in edit interface page
			scrolltoend();
			Thread.sleep(2000);
			generateConfiguration(application);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Interface successfully updated");
			
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface is not added");
		}
		
	}

	public void deleteInterface(String application, String devicename, String vendormodel) throws InterruptedException, DocumentException {
		//Delete Interface
		Thread.sleep(2000);
		ScrolltoElement(application, "providerequipment_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
		{
		click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		}
		Thread.sleep(1000);
		if(vendormodel.contains("Cisco"))
		{
			WebElement Interface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", InterfaceName));
			if(Interface.isDisplayed())
			{
				Clickon(Interface);
			}
		}
			else
			{
				WebElement EditInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", InterfaceName));
				if(EditInterface.isDisplayed())
				{
				Clickon(EditInterface);
				}
			}
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
			Thread.sleep(1000);
			WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", devicename));
			Clickon(AddedDevice_Interface_Actiondropdown);

			click_commonMethod(application, "Delete", "delete", xml);
			Thread.sleep(2000);
			
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
			    Thread.sleep(2000);
			  }catch(Exception e) {
			     e.printStackTrace();
			  } 
			verifysuccessmessage(application, "Interface successfully deleted.");
	
	}
	
	public void delete_MultilinkInterface(String application, String multilink_interfacename, String devicename, String vendormodel) throws InterruptedException, DocumentException {
		//Delete Interface
		Thread.sleep(2000);
		ScrolltoElement(application, "portalaccess_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
		{
		click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		}
		Thread.sleep(1000);
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
			Thread.sleep(1000);
			WebElement AddedDevice_Interface_Actiondropdown= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_interface_actiondropdown").replace("value", devicename));
			Clickon(AddedDevice_Interface_Actiondropdown);

			click_commonMethod(application, "Delete", "delete", xml);
			Thread.sleep(2000);
			
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
			    Thread.sleep(2000);
			  }catch(Exception e) {
			     e.printStackTrace();
			  } 
			verifysuccessmessage(application, "Interface successfully deleted.");
	
	}
	
	public static String Juniper_Multilink_InterfaceName;
	public void verify_JuniperVendor_AddMultilink(String application, String name, String multilink_interfacename
			, String vendormodel, String interfaceaddressrange_value
			, String eipallocation_city, String existingAddressRangeIPv4selection, String existingAddressIPv4DropdownValue
			, String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSizeValue_IPv4
			,String existingAddressRangeIPv6selection, String existingAddressIPv6DropdownValue
			, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSizeValue_IPv6
			, String availableBlocksValue_IPv6, String link_value, String bandwidth_value, String encapsulation_value
			, String multilink_bgpcheckbox, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value
			, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value
			, String multilink_configureinterface_checkbox, String checktoaddinterface_checkbox, String unitid_value
			, String slot_value, String pic_value, String port_value) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
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
					WebElement AddedDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_viewlink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_ViewLink);
					Thread.sleep(5000);
					compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);

					ScrolltoElement(application, "routertools_header", xml);
					Thread.sleep(1000);
					compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
					click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
					click_commonMethod(application, "Add Multilink", "addmultilink_link", xml);
					Thread.sleep(2000);
					scrollToTop();
					GetText(application, "Add Multilink header", "addmultilink_header");
					addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", multilink_configureinterface_checkbox, "yes", xml);
					scrolltoend();
					Thread.sleep(1000);
					if(multilink_configureinterface_checkbox.equalsIgnoreCase("no"))
					{
					click_commonMethod(application, "OK", "okbutton", xml);
					Thread.sleep(1000);
					}
					else
					{
						click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
						Thread.sleep(1000);
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
					
					interfaceAddressRangeIPv4(application, existingAddressRangeIPv4selection, newAddressRangeIpv4selection, subnetSizeValue_IPv4, eipallocation_city, existingAddressIPv4DropdownValue, newinterfaceAddressrange);
					interfaceAddressRangeIPv6(application, existingAddressRangeIPv6selection, newAddressRangeIpv6selection, subnetSizeValue_IPv6, availableBlocksValue_IPv6, newinterfaceAddressrangeIPv6);
					
					ScrolltoElement(application, "getaddress2_button", xml);
					addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
					Thread.sleep(1000);
					addDropdownValues_commonMethod(application, "Bandwidth", "multilink_bandwidth_dropdown", bandwidth_value, xml);
					selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", encapsulation_value, xml);
					ScrolltoElement(application, "link_textfield", xml);
					addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", unitid_value, xml);
					addtextFields_commonMethod(application, "Slot", "slot_textfield", slot_value, xml);
					addtextFields_commonMethod(application, "Pic", "pic_textfield", pic_value, xml);
					addtextFields_commonMethod(application, "Port", "port_textfield", port_value, xml);
					Thread.sleep(1000);
					ScrolltoElement(application, "encapsulation_dropdown", xml);
					addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", multilink_bgpcheckbox, "no", xml);
					if(multilink_bgpcheckbox.equalsIgnoreCase("yes"))
					{
						addDropdownValues_commonMethod(application, "BGP Templates Generate For", "multilink_bgptemplate_dropdown", bgptemplate_dropdownvalue, xml);
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
							WebElement CheckToAddInterface= getwebelement(xml.getlocator("//locators/" + application + "/checktoaddinterface").replace("value", InterfaceName));
							Clickon(CheckToAddInterface);
						}
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "No existing interfaces to display");
					}
					scrolltoend();
					//configuration panel in add interface page
					generateConfiguration(application);
					
					Thread.sleep(3000);
					waitforPagetobeenable();
					verifysuccessmessage(application, "Multilink Interface successfully created");
					ScrolltoElement(application, "interfaces_header", xml);
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
					compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);

					//Multilink table values under interfaces panel
						String Juniper_Multilink_Name= "Multilink"+multilink_interfacename;
						String Multilink_RowID= getwebelement(xml.getlocator("//locators/" + application + "/multilink_rowid").replace("value", multilink_interfacename)).getAttribute("row-id");
						String InterfaceName_value= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_tablevalue").replace("value", Multilink_RowID)).getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
						String Link_value= getwebelement(xml.getlocator("//locators/" + application + "/link_tablevalue").replace("value", Multilink_RowID)).getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
						String InterfaceAddressRange_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddressrange_tablevalue").replace("value", Multilink_RowID)).getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
						String InterfaceAddress_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_tablevalue").replace("value", Multilink_RowID)).getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
						String BearerType_value= getwebelement(xml.getlocator("//locators/" + application + "/bearertype_tablevalue").replace("value", Multilink_RowID)).getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
						WebElement Bandwidth= getwebelement(xml.getlocator("//locators/" + application + "/bandwidth_tablevalue").replace("value", Multilink_RowID));
						String Bandwidth_value= Bandwidth.getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
						Clickon(Bandwidth);
						Bandwidth.sendKeys(Keys.TAB);
						WebElement vlanID= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_tablevalue").replace("value", Multilink_RowID));
						String vlanID_value= vlanID.getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanID_value);
						Clickon(vlanID);
						vlanID.sendKeys(Keys.TAB);
						String IfInOctets_Value= getwebelement(xml.getlocator("//locators/" + application + "/ifinoctets_tablevalue").replace("value", Multilink_RowID)).getText();
						ExtentTestManager.getTest().log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
						Thread.sleep(2000);
					scrolltoend();
					click_commonMethod(application, "Back", "viewpage_backbutton", xml);
					Thread.sleep(2000);
					
				Juniper_Multilink_InterfaceName= Juniper_Multilink_Name;
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

	public static String Cisco_Multilink_InterfaceName;
	public void verify_CiscoVendor_AddMultilink(String application, String devicename, String multilink_interfacename
			, String vendormodel, String interfaceaddressrange_value
			, String eipallocation_city, String existingAddressRangeIPv4selection, String existingAddressIPv4DropdownValue
			, String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSizeValue_IPv4
			,String existingAddressRangeIPv6selection, String existingAddressIPv6DropdownValue
			, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSizeValue_IPv6
			, String availableBlocksValue_IPv6, String link_value, String multilink_bandwidth_value
			, String encapsulation_value, String multilink_bgpcheckbox, String bgptemplate_dropdownvalue, String cpewan_value
			, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value
			, String multilink_configureinterface_checkbox, String checktoaddinterface_checkbox, String unitid_value
			, String slot_value, String pic_value, String port_value) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevices_list"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				if(AddedDeviceNameText.contains(devicename))
				{
					WebElement AddedDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_viewlink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_ViewLink);
					Thread.sleep(5000);
					compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);

					ScrolltoElement(application, "interfaces_header", xml);
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
					compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
					click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
					click_commonMethod(application, "Add Multilink", "addmultilink_link", xml);
					Thread.sleep(2000);
					scrollToTop();
					GetText(application, "Add Multilink header", "addmultilink_header");
					addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", multilink_configureinterface_checkbox, "no", xml);
					scrolltoend();
					Thread.sleep(1000);
					if(multilink_configureinterface_checkbox.equalsIgnoreCase("no"))
					{
					click_commonMethod(application, "OK", "okbutton", xml);
					Thread.sleep(1000);
					}
					else
					{
						click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
						Thread.sleep(1000);
					}
					scrollToTop();
					//verify warning messages in add interface page
					warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);
					warningMessage_commonMethod(application, "encapsulation_warngmsg", "Encapsulation", xml);

					//Add Multilink
					//isDisplayed(application, "Multilink", "multilink_text");
					addtextFields_commonMethod(application, "Interface", "interfacename_textfield", multilink_interfacename, xml);
					
					interfaceAddressRangeIPv4(application, existingAddressRangeIPv4selection, newAddressRangeIpv4selection, subnetSizeValue_IPv4, eipallocation_city, existingAddressIPv4DropdownValue, newinterfaceAddressrange);
					interfaceAddressRangeIPv6(application, existingAddressRangeIPv6selection, newAddressRangeIpv6selection, subnetSizeValue_IPv6, availableBlocksValue_IPv6, newinterfaceAddressrangeIPv6);
					
					ScrolltoElement(application, "link_textfield", xml);
					addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
					Thread.sleep(1000);
					selectValueInsideDropdown(application, "multilink_bandwidth_dropdown", "Bandwidth", multilink_bandwidth_value, xml);
					selectValueInsideDropdown(application, "encapsulation_dropdown", "Encapsulation", encapsulation_value, xml);
					ScrolltoElement(application, "link_textfield", xml);
					Thread.sleep(1000);
					
					if(multilink_bgpcheckbox.equalsIgnoreCase("yes"))
					{
						addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", multilink_bgpcheckbox, "no", xml);
						addDropdownValues_commonMethod(application, "BGP Templates Generate For", "multilink_bgptemplate_dropdown", bgptemplate_dropdownvalue, xml);
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
							WebElement CheckToAddInterface= getwebelement(xml.getlocator("//locators/" + application + "/checktoaddinterface").replace("value", InterfaceName));
							Clickon(CheckToAddInterface);
						}
					}
					else
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "No existing interfaces to display");
					}
					scrolltoend();
					//configuration panel in add interface page
					generateConfiguration(application);
					
					Thread.sleep(3000);
					waitforPagetobeenable();
					verifysuccessmessage(application, "Multilink Interface successfully created");
					ScrolltoElement(application, "interfaces_header", xml);
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
					compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);

					//Multilink table values under interfaces panel
					String Cisco_Multilink_Name= "Multilink"+multilink_interfacename;
					String Cisco_Multilink_RowID= getwebelement(xml.getlocator("//locators/" + application + "/multilink_rowid").replace("value", Cisco_Multilink_Name)).getAttribute("row-id");

					String InterfaceName_value= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
					String Link_value= getwebelement(xml.getlocator("//locators/" + application + "/link_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
					String InterfaceAddressRange_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddressrange_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
					String InterfaceAddress_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
					String BearerType_value= getwebelement(xml.getlocator("//locators/" + application + "/bearertype_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
					WebElement Bandwidth= getwebelement(xml.getlocator("//locators/" + application + "/bandwidth_tablevalue").replace("value", Cisco_Multilink_RowID));
					String Bandwidth_value= Bandwidth.getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
					Clickon(Bandwidth);
					Bandwidth.sendKeys(Keys.TAB);
					WebElement vlanID= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_tablevalue").replace("value", Cisco_Multilink_RowID));
					String vlanID_value= vlanID.getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanID_value);
					Clickon(vlanID);
					vlanID.sendKeys(Keys.TAB);
					String IfInOctets_Value= getwebelement(xml.getlocator("//locators/" + application + "/ifinoctets_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
					Thread.sleep(2000);
					
					scrolltoend();
					click_commonMethod(application, "Back", "viewpage_backbutton", xml);
					Thread.sleep(2000);
					
					Cisco_Multilink_InterfaceName= Cisco_Multilink_Name;
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

	public void verifyInterfaceConfigHistory(String application, String vendormodel) throws InterruptedException, DocumentException {
		
		if(vendormodel.contains("Juniper"))
		{
			ScrolltoElement(application, "interfaceconfighistory_header", xml);
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		compareText(application, "Interface Configuration History", "interfaceconfighistory_header", "Interface Configuration History", xml);
		compareText(application, "Date column", "date_column", "Date", xml);
		compareText(application, "File Name column", "filename_column", "File Name", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		}
		else
		{
			scrolltoend();
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);	
		}

	}


	public void selectInterfacelinkforDevice(String application, String name) throws InterruptedException, DocumentException {
		
		scrolltoend();
		Thread.sleep(1000);
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

	public void SelectInterfacetoremovefromservice(String application)
			throws IOException, InterruptedException, DocumentException {

//		ScrolltoElement(application, "viewpage_vendormodel", xml);
//		Thread.sleep(2000);
//		
			ScrolltoElement(application, "InterfaceInService_panelHeader", xml);
			click_commonMethod(application, "Interfaces in Service Filter", "interfacesinservice_filter", xml);
			addtextFields_commonMethod(application, "Interface in Service search", "interfaceinservice_fitertext", InterfaceName, xml);
			WebElement InterfaceName_GridSelect= getwebelement(xml.getlocator("//locators/" + application + "/interfaceinservice_gridselect").replace("value", InterfaceName));
			Clickon(InterfaceName_GridSelect);
			ExtentTestManager.getTest().log(LogStatus.PASS, InterfaceName + " is selected under 'Interface to select' table");
			Thread.sleep(8000);

			ScrolltoElement(application, "InterfaceInService_panelHeader", xml);
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
			click_commonMethod(application, "Action Dropdown", "InterfaceInselect_Actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Remove", "InterfaceInselect_removebuton", xml);
			Thread.sleep(1000);

	}

	public void Juniper_selectRowforInterfaceInService(String application)
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

									Thread.sleep(3000);

									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_removebuton")));
									ExtentTestManager.getTest().log(LogStatus.PASS, InterfaceName + " has been selected to get removed from service");

								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
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

	public void SelectInterfacetoaddwithservcie(String application, String sid)
			throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(2000);

		ScrolltoElement(application, "interfacesToSelect_header", xml);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		
		click_commonMethod(application, "Interfaces To Select Filter", "InteraceColumn_Filter", xml);
		addtextFields_commonMethod(application, "Interface search", "InterfacefilterTxt", InterfaceName, xml);
		WebElement InterfaceName_GridSelect= getwebelement(xml.getlocator("//locators/" + application + "/interface_gridselect").replace("value", InterfaceName));
		Clickon(InterfaceName_GridSelect);
		ExtentTestManager.getTest().log(LogStatus.PASS, InterfaceName + " is selected under 'Interface to select' table");
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
									Thread.sleep(8000);
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + application + "/InterfaceToselect_Actiondropdown")));

									Thread.sleep(5000);

									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_addbuton")));
									Thread.sleep(3000);
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
									Thread.sleep(8000);
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + application + "/InterfaceToselect_Actiondropdown")));

									Thread.sleep(5000);

									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_addbuton")));
									Thread.sleep(3000);
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

					ExtentTestManager.getTest().log(LogStatus.PASS, "No values displaying under "+labelname+" text field");
					System.out.println("No values displaying under "+labelname+" text field");

					if(expectedValueToEdit.equalsIgnoreCase("null")) {
						//ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " text field is not edited as expected");
						System.out.println(labelname + " text field is not edited as expected");
					}else {

						getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).clear();
						Thread.sleep(3000);

						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
						Thread.sleep(3000);

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
						Thread.sleep(3000);

						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
						Thread.sleep(3000);

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
		Thread.sleep(5000);
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


	public void deleteService(String application) throws InterruptedException, DocumentException	{

		ScrolltoElement(application, "servicepanel_header", xml);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		//Delete Service
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Delete", "delete", xml);
		Thread.sleep(2000);
		
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
		    Thread.sleep(2000);
		  }catch(Exception e) {
		     e.printStackTrace();
		  } 
		  Thread.sleep(2000);
		  verifysuccessmessage(application, "Service successfully deleted");
		
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
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
		Thread.sleep(1000);
		WebElement searchbutton= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
		ScrolltoElement(application, "searchbutton", xml);
		Clickon(searchbutton);
		Thread.sleep(2000);
		scrolltoend();
		Thread.sleep(1000);
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

           /**
            *  It fetches all the value inside dropdown. And it selects the required value inside dropdown			
            * @param application
            * @param xpath
            * @param labelname
            * @param expectedValueToAdd
            * @param xml
            * @throws IOException
            * @throws InterruptedException
            */
           public void selectValueInsideDropdown(String application, String xpath, String labelname, String expectedValueToAdd, XMLReader xml) throws IOException, InterruptedException
           			{ 
           				//getAllValuesInsideDropDown
           				 boolean availability=false;
           				 List<String> ls = new ArrayList<String>();
           				 
           					try {  
           					  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
           					  if(availability) {
           						  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
           						  System.out.println(labelname + " dropdown is displaying");
           						  
           						  WebElement el =getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
           						  
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
           				            
           						  if(expectedValueToAdd.equalsIgnoreCase("null")) {
           							  
           							  ExtentTestManager.getTest().log(LogStatus.PASS, "No values selected under "+ labelname + " dropdown");
           						  }else {
           							  Select s1=new Select(el);
           							  s1.selectByVisibleText(expectedValueToAdd);
           							  
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
