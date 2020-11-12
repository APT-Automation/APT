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
import org.openqa.selenium.interactions.Actions;
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


public class APT_IPAccessCMSDataCenterHelper extends DriverHelper {

	public APT_IPAccessCMSDataCenterHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_IPAccessCMSDataCenter.xml");

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
		//compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
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

	public void verifyselectservicetype(String application, String servicetype, String networkConfig_dropdownvalue)
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
					
					//select Network Configuration
					addDropdownValues_Clickondropdown(application, "Network Configuration", "networkconfig_dropdown", networkConfig_dropdownvalue, xml);
					Thread.sleep(1000);
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
		Thread.sleep(2000);

	}


	public void verifyservicecreation(String application, String sid, String Remarks, String orderno, String rfireqno
			, String servicetype, String networkConfig_dropdownvalue, String terminationdate, String billingtypevalue
			, String email, String phonecontact, String performancereporting_checkbox, String ipguardian_checkbox
			, String routerconfigview_ipv4_checkbox, String routerconfigview_ipv6_checkbox
			, String deliverychannel_dropdownvalue, String topology_dropdownvalue) throws InterruptedException, IOException, DocumentException {

		//Cancel service creation
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to create order page");
			System.out.println("Navigated to create order page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to create order page");
		}

		//Create service
		ScrolltoElement(application, "CreateOrderHeader", xml);
		click_commonMethod(application, "select order switch", "selectorderswitch", xml);
		addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno, xml);
		addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		//select Network Configuration
		addDropdownValues_Clickondropdown(application, "Network Configuration", "networkconfig_dropdown", networkConfig_dropdownvalue, xml);
		Thread.sleep(1000);
		// click on next button
		scrolltoend();
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
		warningMessage_commonMethod(application, "topology_warngmsg", "Topology", xml);
		
		//Create service
		addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
		compareText(application, "Service Type", "servicetypevalue", servicetype, xml);
		compareText(application, "Network Configuration", "networkconfig_value", networkConfig_dropdownvalue, xml);
		selectValueInsideDropdown(application, "billingtype_dropdown", "Billing Type", billingtypevalue, xml);
		addtextFields_commonMethod(application, "Termination Date", "terminationdate_field", terminationdate, xml);
		addtextFields_commonMethod(application, "Email", "emailtextfieldvalue", email, xml);
		addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", phonecontact, xml);
		addtextFields_commonMethod(application, "Remark", "remarktextareavalue", Remarks, xml);
		ScrolltoElement(application, "remarktextareavalue", xml);
		
		// management options panel
		compareText(application, "Management Options", "managementoptions_header", "Management Options", xml);
		addCheckbox_commonMethod(application, "performancereporting_checkbox", "Performance Reporting", performancereporting_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "routerconfigview_ipv4_checkbox", "Router Configuration View IPv4", routerconfigview_ipv4_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "routerconfigview_ipv6_checkbox", "Router Configuration View IPv6", routerconfigview_ipv6_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "ipguardian_checkbox", "IP Guardian", ipguardian_checkbox, "no", xml);
		selectValueInsideDropdown(application, "deliverychannel_dropdown", "Delivery Channel", deliverychannel_dropdownvalue, xml);
			
		//Configurations Options panel
		ScrolltoElement(application, "performancereporting_checkbox", xml);
		compareText(application, "Configuration Options", "configurationoptions_panelheader", "Configuration Options", xml);
		selectValueInsideDropdown(application, "topology_dropdown", "Topology", topology_dropdownvalue, xml);

		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(2000);
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
        compareText(application, "Create User success message", "successmsg", "User successfully created", xml);
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
      	       //verifysuccessmessage(application, "User successfully deleted");
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

		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");

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
			
				addDropdownValues_commonMethod(application, "Order/Contract Number (Parent SID)", "changeorder_chooseorderdropdown", ChangeOrder_existingOrderNumber, xml);
				
				click_commonMethod(application, "OK", "changeorder_okbutton", xml);
				Thread.sleep(1000);
				ScrolltoElement(application, "userspanel_header", xml);
				Thread.sleep(1000);
				compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_existingOrderNumber, xml);
				compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
				Log.info("------ Change Order is successful ------");
	
		}
		
	}
	
	

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype
			, String networkConfig_dropdownvalue, String Remarks, String terminationdate, String billingtypevalue, String email
			, String phonecontact) throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		ScrolltoElement(application, "servicepanel_header", xml);
		Thread.sleep(1000);
		compareText(application, "Service panel Header", "servicepanel_header", "Service", xml);
		compareText(application, "Service Identification", "servicepanel_serviceidentificationvalue", sid, xml);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype, xml);
		compareText(application, "Network Configuration", "servicepanel_networkconfigvalue", networkConfig_dropdownvalue, xml);
		compareText(application, "Billing Type", "servicepanel_billingtype", billingtypevalue, xml);
		GetText(application, "Termination Date", "servicepanel_terminationdate");
		GetText(application, "Email", "servicepanel_email");
		compareText(application, "Phone Contact", "servicepanel_phone", phonecontact, xml);
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks, xml);
		Thread.sleep(1000);
	}

	public void verifyEditservice(String application, String sid, String servicetype, String networkConfig_dropdownvalue
			, String edit_Remarks, String Remarks, String edit_terminationdate, String edit_billingtypevalue, String edit_email
			, String edit_phonecontact, String edit_performancereporting_checkbox, String edit_ipguardian_checkbox
			, String edit_routerconfigview_ipv4_checkbox, String edit_routerconfigview_ipv6_checkbox
			, String edit_deliverychannel_dropdownvalue, String edit_topology_dropdownvalue) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service
		Thread.sleep(2000);
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
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
		scrollToTop();
		edittextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
		compareText(application, "Service Type", "servicetypevalue", servicetype, xml);
		compareText(application, "Network Configuration", "networkconfig_value", networkConfig_dropdownvalue, xml);
		selectValueInsideDropdown(application, "billingtype_dropdown", "Billing Type", edit_billingtypevalue, xml);
		edittextFields_commonMethod(application, "Termination Date", "terminationdate_field", edit_terminationdate, xml);
		edittextFields_commonMethod(application, "Email", "emailtextfieldvalue", edit_email, xml);
		edittextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", edit_phonecontact, xml);
		edittextFields_commonMethod(application, "Remark", "remarktextareavalue", edit_Remarks, xml);
		ScrolltoElement(application, "remarktextareavalue", xml);
		
		// management options panel
		compareText(application, "Management Options", "managementoptions_header", "Management Options", xml);
		editcheckbox_commonMethod(application, edit_performancereporting_checkbox, "performancereporting_checkbox", "Performance Reporting", xml);
			editcheckbox_commonMethod(application, edit_routerconfigview_ipv4_checkbox, "routerconfigview_ipv4_checkbox", "Router Configuration View IPv4", xml);
			editcheckbox_commonMethod(application, edit_routerconfigview_ipv6_checkbox, "routerconfigview_ipv6_checkbox", "Router Configuration View IPv6", xml);
			editcheckbox_commonMethod(application, edit_ipguardian_checkbox, "ipguardian_checkbox", "IP Guardian", xml);
			selectValueInsideDropdown(application, "deliverychannel_dropdown", "Delivery Channel", edit_deliverychannel_dropdownvalue, xml);
			
			//Configurations Options panel
			ScrolltoElement(application, "deliverychannel_dropdown", xml);
			compareText(application, "Configuration Options", "configurationoptions_panelheader", "Configuration Options", xml);
			selectValueInsideDropdown(application, "topology_dropdown", "Topology", edit_topology_dropdownvalue, xml);
			
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "OK", "OK_button", xml);
		Thread.sleep(3000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			verifysuccessmessage(application, "Service successfully updated ");
		}
		else
		{
			Log.info("Service not updated");
			System.out.println("Service not updated");
		}

	}
	
	public void verifyManageSubnets(String application) throws InterruptedException, DocumentException {
		
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
				click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
				click_commonMethod(application, "Manage Subnets", "managesubnets_link", xml);
				Thread.sleep(3000);
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
	Thread.sleep(1000);
	click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
	click_commonMethod(application, "Dump", "dump_link", xml);
	Thread.sleep(2000);
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
	
ScrolltoElement(application, "orderpanelheader", xml);
Thread.sleep(1000);
click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
click_commonMethod(application, "Synchronize", "synchronizelink_servicepanel", xml);
ScrolltoElement(application, "customerdetailsheader", xml);
//compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.", xml);
verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
}

	
	public void verifyManageService(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		
		//Manage service
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage", "manageLink", xml);
		Thread.sleep(2000);
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
		//GetText(application, "Service Details", "status_servicedetails");
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
		//GetText(application, "Service Details", "sync_servicedetails");
		//GetText(application, "Sync Status", "sync_status");
		//GetText(application, "Vistamart Status", "vistamart_status");
		//GetText(application, "Vistamart DateTime", "vistamart_datetime");
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
//		compareText(application, "Smarts header", "devicesforservice_smartsheader", "Smarts", xml);
//		compareText(application, "Fetch Interfaces header", "devicesforservice_fetchinterfacesheader", "Fetch Interfaces", xml);
//		compareText(application, "VistaMart Device header", "devicesforservice_vistamartdeviceheader", "VistaMart Device", xml);
		
		GetText(application, "Device Name", "deviceforservicepanel_devicename");
//		GetText(application, "Sync Status", "deviceforservicepanel_syncstatus");
//		verify_SmartsValue(application);
//		verify_FetchInterfacesValue(application);
//		verify_VistamartDeviceValue(application);
//		click_commonMethod(application, "Manage", "deviceforservicepanel_managelink", xml);
//		Thread.sleep(2000);
//		scrollToTop();
//		compareText(application, "Manage Network header", "managenetwork_header", "Manage COLT's Network - Manage Network", xml);
//		driver.navigate().back();
//		Thread.sleep(2000);
		scrolltoend();
		Thread.sleep(2000);
		click_commonMethod(application, "Back", "managepage_backbutton", xml);
		Thread.sleep(2000);

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
	 
	public void verifyManagementConfigpanels(String application, String performancereporting_checkbox, String ipguardian_checkbox
			, String routerconfigview_ipv4_checkbox, String routerconfigview_ipv6_checkbox, String deliverychannel_dropdownvalue, String topology_dropdownvalue) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "managementoptions_header", xml);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
		compareText(application, "Management options header", "managementoptions_header", "Management Options", xml);
				//verify Router Configuration View IPv4 checkbox
				if(routerconfigview_ipv4_checkbox.equalsIgnoreCase("Null")) {
					compareText(application, "Router Configuration View IPv4", "managementoptions_routerconfigIPv4", "no", xml);
				}
				else if(routerconfigview_ipv4_checkbox.equalsIgnoreCase("yes")) {
					compareText(application, "Router Configuration View IPv4", "managementoptions_routerconfigIPv4", "yes", xml);
				}
				else {
					compareText(application, "Router Configuration View IPv4", "managementoptions_routerconfigIPv4", "no", xml);
				}
				//verify Router Configuration View IPv6 checkbox
				if(routerconfigview_ipv6_checkbox.equalsIgnoreCase("Null")) {
					compareText(application, "Router Configuration View IPv6", "managementoptions_routerconfigIPv6", "no", xml);
				}
				else if(routerconfigview_ipv6_checkbox.equalsIgnoreCase("yes")) {
					compareText(application, "Router Configuration View IPv6", "managementoptions_routerconfigIPv6", "yes", xml);
				}
				else {
					compareText(application, "Router Configuration View IPv6", "managementoptions_routerconfigIPv6", "no", xml);
				}
				//verify Performance Reporting checkbox
				if(performancereporting_checkbox.equalsIgnoreCase("Null")) {
					compareText(application, "Performance Reporting", "managementoptions_performancereporting", "no", xml);
				}
				else if(performancereporting_checkbox.equalsIgnoreCase("yes")) {
					compareText(application, "Performance Reporting", "managementoptions_performancereporting", "yes", xml);
				}
				else {
					compareText(application, "Performance Reporting", "managementoptions_performancereporting", "no", xml);
				}
				//verify IP Guardian checkbox
				if(ipguardian_checkbox.equalsIgnoreCase("Null")) {
					compareText(application, "IP Guardian", "managementoptions_ipguardian", "no", xml);
				}
				else if(ipguardian_checkbox.equalsIgnoreCase("yes")) {
					compareText(application, "IP Guardian", "managementoptions_ipguardian", "yes", xml);
				}
				else {
					compareText(application, "IP Guardian", "managementoptions_ipguardian", "no", xml);
				}
				//verify Delivery Channel checkbox
				compareText(application, "Delivery Channel", "managementoptions_deliverychannel", deliverychannel_dropdownvalue, xml);
				
				//Configurations Options panel
				ScrolltoElement(application, "configurationoptions_panelheader", xml);
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
				compareText(application, "Configuration Options header", "configurationoptions_panelheader", "Configuration Options", xml);
				compareText(application, "Topology", "configoptions_topology", topology_dropdownvalue, xml);
				Thread.sleep(1000);
			
	}
	

	public void Clickon_addToggleButton(String application, String xpath) throws InterruptedException, DocumentException {

		//Add Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
		Thread.sleep(5000);
	}


	public void deleteService(String application) throws InterruptedException, DocumentException	{

		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
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
		  waitforPagetobeenable();
		  verifysuccessmessage(application, "Service successfully deleted.");
		
	}

	
	//======================================Pramod Code from here ===========================================
	
	public void navigateToAddNewDestributionSwitchDevicePage(String application) throws InterruptedException, DocumentException {
		ScrolltoElement(application, "ConfigurationOptions_Header", xml);//portalaccess_header
		Thread.sleep(1000);
		if (getwebelement(xml.getlocator("//locators/" + application + "/DistributionSwitchPanel_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Existing Distribution Switch Device addition");
			System.out.println( "'DistributionSwitch' panel navigated as expected");
			click_commonMethod(application, "Add IAR", "AddIARLink", xml);
		Thread.sleep(5000);
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/addpedevice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add PE Device' page navigated as expected");
			System.out.println("'Add PE Device' page navigated as expected");
			
		compareText(application, "Add PE Device Header", "addpedevice_header", "Add PE Device", xml);
		click_commonMethod(application, "Add New Device toggle", "addnewdevice_togglebutton", xml);
		Thread.sleep(2000);
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add PE Deviceh' page not navigated");
			System.out.println("'Add PE Device' page not navigated");
		}
}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL,"'Distribution Switch' panel not navigated");
	System.out.println("'Distribution Switch' panel not navigated");
}
	}

	
	public void navigateToAddNewAccessSwitchDevicePage(String application) throws InterruptedException, DocumentException {
		
		scrolltoend();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying New Access Switch Device addition");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/distributionSwitch_ActionDropdown")));

		Thread.sleep(1000);
		if (getwebelement(xml.getlocator("//locators/" + application + "/AccessSwitchPanel_Header")).isDisplayed()) {
			System.out.println( "'Access Switch' panel navigated as expected");
			click_commonMethod(application, "Add Access Switch", "AddAccessSwitchLink", xml);
		Thread.sleep(5000);
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/addAccessSwitchDevice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Access Switch Device' page navigated as expected");
			System.out.println("'Add Access Switch Device' page navigated as expected");
			
		compareText(application, "Add Access Switch Device Header", "addAccessSwitchDevice_header", "Add Access Switch Device", xml);
		click_commonMethod(application, "Add New Device toggle", "addnewdevice_togglebutton", xml);
		Thread.sleep(2000);
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Access Switch Device' page not navigated");
			System.out.println("'Add Access Switch Device' page not navigated");
		}
}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL,"'Access Switch' panel not navigated");
	System.out.println("'Access Switch' panel not navigated");
}
	}
	
	
	
	
	public void verifyAddNewDestributionSwitchDeviceFields(String application) throws InterruptedException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add New Distribution Switch Device page Fields");
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
	
	
	public void verifyAddNewAccessSwitchDeviceFields(String application) throws InterruptedException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add New Access Switch Device page Fields");
		try {

			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), "Name" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/vendormodelinput")), "Vendor Model" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")), "Management Address" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/snmproinputfield")), "Snmpro" );
			webelementpresencelogger(getwebelement(xml.getlocator("//locators/" + application + "/countryinput")), "Country" );
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
	
	public void verifyAddNewDestributionSwitchDevice(String application, String name, String devicetype, 
			String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
			String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
			Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
			String Snmpv3Privpasswordvalue, String Country, String managementaddress, String existingcity, 
			String existingcityvalue, String existingsite,
			String existingsitevalue, String existingpremise, 
			String existingpremisevalue, String newcity,String cityname,String Citycode,
			String sitename, String sitecode,  String premisename,  String premisecode, 
			String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add New Distribution Switch Device Functionality");
		try {

			scrolltoend();
			//Verify Mandatory fields
			click_commonMethod(application, "Next", "Next_Button", xml);
			Thread.sleep(2000);
			scrollToTop();
			Thread.sleep(2000);
			warningMessage_commonMethod(application, "warningMessage_name", "Device Name", xml);
			warningMessage_commonMethod(application, "warningMessage_vendor", "Vendor/Model", xml);
			//warningMessage_commonMethod(application, "warningMessage_Country", "Country", xml);

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
			  Thread.sleep(2000);
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
					
			scrolltoend();
			Thread.sleep(2000);
			click_commonMethod(application, "Next", "Next_Button", xml);
			Thread.sleep(3000);
			scrollToTop();
			compareText(application, "Add Device success msg", "PE_AddPEDeviceSuccessfulMessage", "Device successfully created.", xml);
			Thread.sleep(2000);
			
	} catch (StaleElementReferenceException e) {
		
		e.printStackTrace();
	}
	
	}


	
	
	public void verifyAddNewAccessSwitchDevice(String application, String name, String devicetype, 
			String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
			String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
			Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
			String Snmpv3Privpasswordvalue, String Country, String managementaddress, String existingcity, 
			String existingcityvalue, String existingsite,
			String existingsitevalue, String existingpremise, 
			String existingpremisevalue, String newcity,String cityname,String Citycode,
			String sitename, String sitecode,  String premisename,  String premisecode, 
			String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add New Distribution Switch Device Functionality");
		try {

			scrolltoend();
			//Verify Mandatory fields
			click_commonMethod(application, "Next", "Next_Button", xml);
			Thread.sleep(2000);
			scrollToTop();
			Thread.sleep(2000);
			warningMessage_commonMethod(application, "warningMessage_name", "Device Name", xml);
			warningMessage_commonMethod(application, "warningMessage_vendor", "Vendor/Model", xml);
			//warningMessage_commonMethod(application, "warningMessage_Country", "Country", xml);

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
			  Thread.sleep(2000);
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
					
			scrolltoend();
			Thread.sleep(2000);
			click_commonMethod(application, "Next", "Next_Button", xml);
			Thread.sleep(3000);
			scrollToTop();
			compareText(application, "Add Device success msg", "PE_AddPEDeviceSuccessfulMessage", "Device successfully created.", xml);
			Thread.sleep(2000);
			
	} catch (StaleElementReferenceException e) {
		
		e.printStackTrace();
	}
	
	}

	public void verifyViewpage_AddedNewDestributionSwitchDeviceDetails(String application, String name, String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro,
			String Snmprw, 	String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String Snmpv3Authpassword,
			String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue, String Snmpv3Privpasswordvalue, String Country, 
			String managementaddress, String existingcity, 	String existingcityvalue, String existingsite, String existingsitevalue, String existingpremise, 
			String existingpremisevalue, String newcity,String cityname,String Citycode, String sitename, String sitecode,  String premisename, 
			String premisecode, String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {
		
		waitforPagetobeenable();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Added New Distribution Switch Device Information");
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Newly created device information'");
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View Distribution Switch Device' page navigated as expected");
			System.out.println("'View Distribution Switch Device' page navigated as expected");
			
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
			compareText(application, "City", "viewpage_city", existingcityvalue, xml);
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
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Distribution Switch Device page");
				System.out.println(  e+ " : Field is not displayed in View Distribution Switch Device page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Distribution Switch Device page");
				System.out.println(  e+" : Field is not displayed in View Distribution Switch Device page");
			}

			
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'View Distribution Switch Device' page not navigated");
				System.out.println("'View Distribution Switch Device' page not navigated");
			}
		
	}

	
	
	public void verifyViewpage_AddedNewAccessSwitchDeviceDetails(String application, String ServiceIdentification , String	NewAccessDeviceName, String	AS_VendorModel,
			String	AS_ManagementAddress, String AS_Snmpro,String AS_Country, String	AS_City, String CPE_SiteEdit, 
			String CPE_PremiseEdit) throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Added New Access Switch Device Information");
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Newly created device information'");
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View Distribution Switch Device' page navigated as expected");
			System.out.println("'View Distribution Switch Device' page navigated as expected");
			
			try {
		//Device Name
		compareText(application, "Name", "viewpage_devicename", NewAccessDeviceName, xml);

		//Vendor/model
		compareText(application, "Vendor/Model", "viewpage_vendormodel", AS_VendorModel, xml);

		//Snmpro
			compareText(application, "Snmpro", "viewpage_snmpro", "incc", xml);

		//Management Address
		compareText(application, "Management Address", "viewpage_managementaddress", AS_ManagementAddress, xml);

		//Country
		compareText(application, "Country", "viewpage_country", AS_Country, xml);

		//Existing City
			GetText(application, "City", "viewpage_city");

		//Site
			compareText(application, "Site", "viewpage_site", CPE_SiteEdit, xml);
		
		//Premise
			compareText(application, "Premise", "viewpage_premise", CPE_PremiseEdit, xml);
		
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View Distribution Switch Device page");
				System.out.println(  e+ " : Field is not displayed in View Distribution Switch Device page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View Distribution Switch Device page");
				System.out.println(  e+" : Field is not displayed in View Distribution Switch Device page");
			}

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'View Access Switch Device' page not navigated");
				System.out.println("'View Access Switch Device' page not navigated");
			}
		
	}

	
	
	public void verifyViewDevicepage_Links_PE(String application) throws InterruptedException, DocumentException {
		Thread.sleep(8000);
		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		compareText(application, "Edit", "viewdevice_Edit", "Edit", xml);
		compareText(application, "Delete", "viewdevice_delete", "Delete", xml);
		compareText(application, "Fetch Interface", "viewdevice_fetchinterfacelink", "Fetch Device Interfaces", xml);

		//Edit in view device page
		click_commonMethod(application, "Edit", "viewdevice_Edit", xml);
		Thread.sleep(2000);
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/editdeviceheader")).isDisplayed())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated to 'Edit PE Device' page successfully");
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			Thread.sleep(2000);
			//ScrolltoElement(application, "portalaccess_header", xml);
			scrollToTop();
//			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
//			{
//				click_commonMethod(application, "View", "viewservicepage_viewdevicelink", xml);
//				Thread.sleep(5000);
//				compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);
//			}
//			else
//			{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
//			}
//		}
//		else
//		{
//			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not navigated to 'Edit PE Device' page");
//		}

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
	}
		}
	
	
	
	public static String InterfaceAddress;
	public void verifyFetchInterface_PE(String application, String devicename, String Inservice_status, String Inmaintenance_status, String vendormodel, String managementaddress, String snmpro, String country, String interfacename) throws InterruptedException, DocumentException, IOException {

//		ScrolltoElement(application, "portalaccess_header", xml);
//		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
//		{
//			List<WebElement> addeddevicesList= driver.findElements(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b"));
//			System.out.println(addeddevicesList);
//			int AddedDevicesCount= addeddevicesList.size();
//			for(int i=0;i<AddedDevicesCount;i++) {
//				String AddedDeviceNameText= addeddevicesList.get(i).getText();
//				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
//				if(AddedDeviceNameText.contains(devicename))
//				{
//					WebElement AddedDevice_ViewLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='View']"));
//					Clickon(AddedDevice_ViewLink);
//					Thread.sleep(5000);
//					compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);
//				}
//			}
//			
//		}
//		else
//		{
//			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
//		}

		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		click_commonMethod(application, "Fetch Interface", "viewdevice_fetchinterfacelink", xml);
		Thread.sleep(3000);
		compareText(application, "Fetch Interface success msg", "fetchsuccessmsg", "Fetch interfaces started successfully. Please check the sync status of this device", xml);
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
		compareText(application, "Snmpro field header", "statuspage_snmproheader", "Snmpro", xml);
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
		Thread.sleep(2000);
		scrollToTop();
		compareText(application, "Device status update success message", "Sync_successmsg", "Device Status History successfully changed.", xml);
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

										compareText(application, "Changed By", "statuspage_changedbyvalue", Getkeyvalue("APT_login_1_Username"), xml);
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
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No interfaces added");

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
			compareText(application, "Current Status", "interface_statuspage_currentstatusvalue", Inservice_status, xml);
			click_commonMethod(application, "New Status Dropdown", "interface_statuspage_newstatusdropdown", xml);
			WebElement selectNewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue"));
			Clickon(selectNewStatusvalue1);
			String NewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue1);
			click_commonMethod(application, "OK", "interface_statuspage_okbutton", xml);
			Thread.sleep(2000);
			scrollToTop();
			compareText(application, "Interface status update success message", "Sync_successmsg", "Interface Status History successfully changed.", xml);
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
		//isElementPresent("//locators/" + application + "/Sync_successmsg");
		compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this device.", xml);
		Thread.sleep(2000);

		//			//verify device name link in status panel
		//			click(application, "Device", "status_devicevalue", xml);
		//			Thread.sleep(2000);
		//			compareText(application, "Search for Device header", "searchdevice_header", "Search For Device", xml);
		//			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to 'Search for device' page");
		//			driver.navigate().back();
		//			Thread.sleep(1000);
		//			
		//			//verify device name link in synchronization panel
		//			click(application, "Device", "synchronization_devicevalue", xml);
		//			Thread.sleep(2000);
		//			compareText(application, "Search for Device header", "searchdevice_header", "Search For Device", xml);
		//			ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Navigated to 'Search for device' page");
		//			driver.navigate().back();
		//			Thread.sleep(2000);
		//			driver.navigate().back();
		//			Thread.sleep(2000);
		//			scrolltoend();
		//			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		//			Thread.sleep(2000);
		//	
	}

	public void verifyEditDevice_PE(String application, String editDevicename, String editVendorModel, String editTelnet, String editSSH, String editSnmp2C, String editSnmp3,
			String editSnmProNewValue, String editSnmprwNewValue, String editSnmpv3UsernameNewValue, String editSnmpv3AuthpasswordNewValue, String editSnmpv3PrivpasswordNewValue,
			String editManagementAddress, String editCountry, String editExistingCity, String editExistingCityValue, String editExistingSite, String editExistingSiteValue,
			String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, String editNewPremise, String editNewCityName,
			String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edit PE Device Functionality");
		try {

			ScrolltoElement(application, "portalaccess_header", xml);
			Thread.sleep(1000);
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
			{
				click_commonMethod(application, "Edit", "viewservicepage_editdevicelink", xml);
				Thread.sleep(5000);
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
				Thread.sleep(2000);
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
		Thread.sleep(2000);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);
		compareText(application, "Edit Device success msg", "successmsg", "Device successfully updated.", xml);
		Thread.sleep(2000);

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
//			compareText(application, "Snmp v3 Username", "viewpage_snmpv3username", "colt-nms", xml);
//			compareText(application, "Snmp v3 Auth password", "viewpage_snmpv3authpassword", "OrHzjWmRvr4piJZb", xml);
//			compareText(application, "Snmp v3 Priv password", "viewpage_snmpv3privpassword", "3k0hw8thNxHucQkE", xml);
		
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
		}
		else if(editExistingCity.equalsIgnoreCase("yes") && editNewCity.equalsIgnoreCase("no")) {
			compareText(application, "City", "viewpage_city", editExistingCityValue, xml);

		}
		else if(editExistingCity.equalsIgnoreCase("no") && editNewCity.equalsIgnoreCase("yes")) {
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

//		scrolltoend();
//		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
//		Thread.sleep(2000);
		
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

	
	
	
	
	public void addExistingDistributionSwitchDevice_PE(String application, String Topology, String existingdevicename) throws InterruptedException, DocumentException, IOException {
		
		try{
			if(Topology.contains("1AS")) {
		ScrolltoElement(application, "ConfigurationOptions_Header", xml);//portalaccess_header
		Thread.sleep(1000);
		if (getwebelement(xml.getlocator("//locators/" + application + "/DistributionSwitchPanel_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Existing Distribution Switch Device addition");
			System.out.println( "'DistributionSwitch' panel navigated as expected");
			click_commonMethod(application, "Add IAR", "AddIARLink", xml);
		Thread.sleep(5000);
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/addpedevice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add PE Device' page navigated as expected");
			System.out.println("'Add PE Device' page navigated as expected");
			
			try {
		addDropdownValues_commonMethod3(application, "Type PE name to filter", "typepename_dropdown", existingdevicename, xml);
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
		Thread.sleep(3000);
		compareText(application, "Add Device success msg", "successmsg", "Device is added to Service.", xml);
		Thread.sleep(2000);
		
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
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL,"'Provider Equipment (PE)' panel not navigated");
		System.out.println("'Provider Equipment (PE)' panel not navigated");
	}
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


	
	public void addExistingAccessSwitchDevice(String application,String Topology, String existingdevicename) throws InterruptedException, DocumentException, IOException {
	
		try {
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/distributionSwitch_ActionDropdown")));
		Thread.sleep(1000);

		if (getwebelement(xml.getlocator("//locators/" + application + "/AccessSwitchPanel_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Existing Access Switch Device addition");
			System.out.println( "'Access Switch' panel navigated as expected");
			click_commonMethod(application, "Add Access Switch", "AddAccessSwitchLink", xml);
		Thread.sleep(5000);
		
		if (getwebelement(xml.getlocator("//locators/" + application + "/addAccessSwitchDevice_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Access Switch Device' page navigated as expected");
			System.out.println("'Add Access Switch Device' page navigated as expected");
			
			try {
		addDropdownValues_commonMethod3(application, "Name", "typepename_dropdown2", existingdevicename, xml);
		Thread.sleep(2000);
		
		//Verify existing device values
		GetText(application, "Vendor/Model", "existingdevice_vendormodelvalue");
		GetText(application, "Management Address", "existingdevice_managementaddressvalue");
			GetText(application, "Snmpro", "existingdevice_snmpro");
		GetText(application, "Country", "existingdevice_country");
		GetText(application, "City", "existingdevice_city");
		GetText(application, "Site", "existingdevice_site");
		GetText(application, "Premise", "existingdevice_premise");
		Thread.sleep(2000);
		scrolltoend();
		Thread.sleep(2000);
		click_commonMethod(application, "Next", "Next_Button", xml);
		Thread.sleep(3000);
		compareText(application, "Add Device success msg", "successmsg", "Device is added to Service.", xml);
		Thread.sleep(2000);
		
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Access Switch Device page");
				System.out.println(  e+ " : Field is not displayed in Add PE Device page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add Access Switch Device page ");
				System.out.println(  e+" : Field is not displayed in Add Access Switch Device page");
			}

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Access Switch Device' page not navigated");
				System.out.println("'Add Access Switch Device' page not navigated");
			}
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL,"'Access Switch' panel not navigated");
		System.out.println("'Access Switch' panel not navigated");
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

	
	public void verifyExistingDistributionSwitchDeviceInfo_PE(String application, String Topology, String existingdevicename) throws InterruptedException, IOException, DocumentException {
	
		try {
		if(Topology.contains("1AS")) {
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Existing PE Device Information");
			System.out.println("'View PE Device' page navigated as expected");
			scrollToTop();
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

//					scrolltoend();
//					click_commonMethod(application, "Back", "viewpage_backbutton", xml);
//					Thread.sleep(2000);
					
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

	
	
	public void verifyExistingAccessSwitchDeviceInfo_PE(String application,String Topology, String existingdevicename) throws InterruptedException, IOException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Existing PE Device Information");
		try{
			
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			System.out.println("'View PE Device' page navigated as expected");
			scrollToTop();
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

//					scrolltoend();
//					click_commonMethod(application, "Back", "viewpage_backbutton", xml);
//					Thread.sleep(2000);
					
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
	

	public void deleteDistributionSwitchDevice(String application, String DeviceName) throws InterruptedException, DocumentException { 
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete Distribution Switch' Functionality");
		
		WebElement configurationOptions_header= getwebelement(xml.getlocator("//locators/" + application + "/configurationoptions_panelheader"));
		scrolltoview(configurationOptions_header);
		Thread.sleep(2000);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/distributionSwitch_existinddeviceGrid")).isDisplayed())
        {
              List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/distributionSWitch_fetchAlldevice_InviewPage"));
//              Log.info(addeddevicesList);
              int AddedDevicesCount= addeddevicesList.size();
              for(int i=0;i<AddedDevicesCount;i++) {
                    String AddedDeviceNameText= addeddevicesList.get(i).getText();
                    String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
                    if(AddedDeviceNameText.contains(DeviceName))
                    {
                          WebElement AddedDevice_DeletefromserviceLink=getwebelement(xml.getlocator("//locators/" + application + "/distributionSwitch_deleteFromServiceLin").replace("value", AddedDevice_SNo)); 
                          Clickon(AddedDevice_DeletefromserviceLink);
                          Thread.sleep(1000);
                          
                          Alert alert = driver.switchTo().alert();
                          alert.accept();
                          
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
        	ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid under 'Distribution Switch' panel");
        }		
		}
	
	
	
	public void deleteAccessSwitchDevice(String application, String DeviceName) throws InterruptedException, DocumentException {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Delete Service Functionality");
		try {
			
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/distributionSwitch_ActionDropdown")));
			Thread.sleep(1000);
		
			
		if(getwebelement(xml.getlocator("//locators/" + application + "/AccessSwitchexistingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/fetchAllValuesUnderAccessswitchPanel"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
				if(AddedDeviceNameText.contains(DeviceName))
				{
					WebElement AddedDevice_DeletefromserviceLink= getwebelement(xml.getlocator("//locators/" + application + "/accessSwitch_deletefomServiceLink").replace("value", AddedDevice_SNo));
					Clickon(AddedDevice_DeletefromserviceLink);								   
					Thread.sleep(1000);
					
					driver.switchTo().alert().accept();
					
					verifysuccessmessage(application, "Device successfully deleted.");
					break;
//					compareText(application, "Device delete success message", "DeleteDeviceSuccessMessageAS", "Device successfully deleted.", xml);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
				}
			}
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid under 'Access Swicth' panel");
		}
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Field is not displaying" );
			System.out.println( " Field is not displaying");
			
		}catch(Exception ee) {
			ee.printStackTrace();
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displaying" );
			System.out.println( " Field is not displaying");
		}
	}
	
	public void deleteExistingPEDevice(String application, String DeviceName) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "ConfigurationOptions_Header", xml);
		try {
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
					Thread.sleep(2000);
					//**CancelJavaScriptMethod();
					AcceptJavaScriptMethod();
					compareText(application, "Device delete success message", "successmsg", "Device successfully removed from service.", xml);
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
	
	public void deleteDevice_AS(String application, String DeviceName) throws InterruptedException, DocumentException {
try {
		//ScrolltoElement(application, "portalaccess_header", xml);
		scrolltoend();
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			List<WebElement> addeddevicesList= driver.findElements(By.xpath("(//div[text()='Access Switch']/parent::div/following-sibling::div)[2]/div/div/b"));
			System.out.println(addeddevicesList);
			int AddedDevicesCount= addeddevicesList.size();
			for(int i=0;i<AddedDevicesCount;i++) {
				String AddedDeviceNameText= addeddevicesList.get(i).getText();
				String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
//				if(AddedDeviceNameText.contains(DeviceName))
//				{
					WebElement AddedDevice_DeletefromserviceLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='Delete from Service']"));
					Clickon(AddedDevice_DeletefromserviceLink);								   
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
					compareText(application, "Device delete success message", "successmsg", "Device successfully removed from service.", xml);
//				}
//				else
//				{
//					ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
//				}
			}
		}
		else
		{
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
	
	
	
	
	public void verify_Cisco_RouterTools_PE(String application, String commandIPv4, String commandIPv6, String ipAddress, 
			String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException {
		//scrolltoend();
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

	public void verify_Juniper_RouterTools_PE(String application, String DeviceName, String commandIPv4, String ipAddress, 
			String vrfname_ipv4) throws InterruptedException, DocumentException {

		navigateToViewDevicePage_PE("NoCpeConfig", DeviceName);
		
		//ScrolltoElement(application, "commandIPV4_dropdown", xml);//routertools_header
		scrolltoview(application, "View Device Header", "viewdevicepage_header", xml);//Routes_Header
		Thread.sleep(1000);
		
		//Command IPV4	
		addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);

		hostnametextField_IPV4(application, commandIPv4, ipAddress);

		vrfNametextField_IPV4(application, commandIPv4, vrfname_ipv4);

		executeCommandAndFetchTheValue(application, "executebutton_Ipv4");

	}

	
	
	public void verify_CiscoVendor_InterfacePanel_PE(String application, String configureinterface_checkbox, String interfacename, String getaddress, String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String bearertype_value, String bandwidth_value, String encapsulation_value, String bgp_checkbox, String framingtype_value, String vlanID_value, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value, String name, String editDevicename, String edit_configureinterface_checkbox, String edit_interfacename, String edit_network, String edit_eipallocation_city, String edit_interfaceaddressrange_value, String edit_interfaceaddressrangeIPv6_value, String edit_ipallocation_availableblocksvalue, String edit_linkvalue, String edit_bearertype_value, String edit_bandwidth_value, String edit_framingtype_value, String edit_encapsulation_value, String edit_vlanID_value, String edit_bgp_checkbox, String edit_bgptemplate_dropdownvalue, String edit_cpewan_value, String edit_cpewanipv6_value, String edit_descriptionfield_value, String edit_ascustomerfield_value, String edit_bgppassword_value, String ipsubnetipv6_value, String ipsubnetipv4_value) throws InterruptedException, DocumentException, IOException {

		//ScrolltoElement(application, "routertools_header", xml);
		ScrolltoElement(application, "portalaccess_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			click_commonMethod(application, "View", "viewservicepage_viewdevicelink", xml);
			Thread.sleep(5000);
			scrollToTop();
			compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}
		
		scrolltoview(application, "Routers", "Routes_Header", xml);

		Thread.sleep(1000);
		compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
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
		warningMessage_commonMethod(application, "bearertype_warngmsg", "Bearer Type", xml);
		warningMessage_commonMethod(application, "bandwidth_warngmsg", "Bandwidth", xml);
		warningMessage_commonMethod(application, "encapsulation_warngmsg", "Encapsulation", xml);

		//Add Interface
		addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", configureinterface_checkbox, "no", xml);
		addtextFields_commonMethod(application, "Interface", "interfacename_textfield", interfacename, xml);
		compareText(application, "Network", "network_fieldvalue", "XFER", xml);

		//verify EIP Allocation
		Thread.sleep(1000);
		click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
		{
			compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
			GetText(application, "Subnet Type", "subnettype_value");
			addDropdownValues_commonMethod(application, "City", "eipallocation_citydropdown", eipallocation_city, xml);
			addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", interfaceaddressrange_value, xml);
			GetText(application, "Available Pools", "eipallocation_availablepools_value");
			//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Close", "closesymbol", xml);
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
		}
		Thread.sleep(2000);

		//verify getaddress
		if(getaddress.equalsIgnoreCase("yes"))
		{
			click_commonMethod(application, "Get Address", "getaddress1_button", xml);
			addDropdownValues_commonMethod(application, "Interface Address Range", "interfacerange_Address_dropdown", interfaceaddressrange_value, xml);
			click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
		}
		else
		{
			addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", interfaceaddressrange_value, xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

		}

		compareText_fromtextFields(application, "Network", "networkipv6_fieldvalue", "WAN (XFER)", xml);

		//verify EIP Allocation
		Thread.sleep(1000);
		click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
		{
			compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
			GetText(application, "Subnet Type", "subnettype_value");
			GetText(application, "Space Name", "eipallocation_spacename");
			addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_ipv6_subnetsize", interfaceaddressrange_value, xml);
			addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", eipallocation_availableblocksvalue, xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
			Thread.sleep(2000);
			scrollToTop();
			compareText(application, "Subnet aalocation success message", "successmsg", "Subnet Allocation IPTransitService_05-IPTransitService_05-XFER-0017527 (null)Successfully", xml);
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
		}
		Thread.sleep(2000);

		//verify getaddress ipv6
		if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
		{
			click_commonMethod(application, "Get Address", "getaddress2_button", xml);
			addDropdownValues_commonMethod(application, "Interface Address Range IPv6", "interfacerange_AddressIpv6_dropdown", interfaceaddressrange_value, xml);
			click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
		}
		else
		{
			addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

		}

		ScrolltoElement(application, "link_textfield", xml);
		addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
		addDropdownValues_commonMethod(application, "Bearer Type", "bearertype_dropdown", bearertype_value, xml);
		addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
		if(bearertype_value.equalsIgnoreCase("E1")) {
			addDropdownValues_commonMethod(application, "Framing Type", "framingtype_dropdown", framingtype_value, xml);
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("Ethernet"))
		{
			addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanID_value, xml);
		}
		else
		{
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
		}
		Thread.sleep(1000);
		scrolltoend();
		
//		addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", bgp_checkbox, "no", xml);
//		if(bgp_checkbox.equalsIgnoreCase("yes"))
//		{
//			addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", bgptemplate_dropdownvalue, xml);
//			addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
//			addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
//			addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
//			addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
//			addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
//		}

		//configuration panel in add interface page
		if(configureinterface_checkbox.equalsIgnoreCase("yes"))
		{
			compareText(application, "Configuration", "configuration_header", "Configuration", xml);
			click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
			Thread.sleep(2000);
			GetText(application, "Configuration", "configuration_textarea");
			Thread.sleep(1000);
			click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
		}
		else
		{
			click_commonMethod(application, "OK", "okbutton", xml);
		}
		Thread.sleep(5000);
		compareText(application, "Interface Added success message", "successmsg", "Interface added successfully", xml);
		Thread.sleep(2000);

		//Verify added interface
		ScrolltoElement(application, "routertools_header", xml);
		WebElement AddedInterfaces= driver.findElement(By.xpath("//div[text()='Interfaces']/parent::div/following-sibling::div//div[@ref='eBodyContainer']"));
		String addedinterfacecheck= AddedInterfaces.getAttribute("style");
		if(!addedinterfacecheck.contains("height: 1px"))
		{
			compareText(application, "Interfaces", "interfaces_header", "Interfaces", xml);
			String Interface_RowID= driver.findElement(By.xpath("//div[@col-id='interfaceName'][text()='"+interfacename+"']/parent::div")).getAttribute("row-id");

			WebElement InterfaceName_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='interfaceName']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
			WebElement Link_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='linkId']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
			WebElement InterfaceAddressRange_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='interfaceAddressRange']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
			WebElement InterfaceAddress_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='interfaceAddress']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
			WebElement BearerType_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='bearerType']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
			WebElement Bandwidth_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='bandWidth']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
			Clickon(Bandwidth_value);
			Bandwidth_value.sendKeys(Keys.TAB);
			WebElement vlanIDValue= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='vlanId']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanIDValue);
			Clickon(vlanIDValue);
			vlanIDValue.sendKeys(Keys.TAB);
			WebElement IfInOctets_Value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='ifInOctets']"));
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

		//edit Interface
		ScrolltoElement(application, "portalaccess_header", xml);
		click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		Thread.sleep(1000);
		WebElement SelectInterface= driver.findElement(By.xpath("//div[@role='gridcell'][@col-id='interfaceName'][text()='"+interfacename+"']/following-sibling::div//span[contains(@class,'unchecked')]"));

		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("//div[div[b[text()='"+name+"']]]//following-sibling::div//button[text()='Action']")).isDisplayed())
			{
				WebElement AddedDevice_Interface_Actiondropdown= driver.findElement(By.xpath("//div[div[b[text()='"+name+"']]]//following-sibling::div//button[text()='Action']"));
				Clickon(AddedDevice_Interface_Actiondropdown);
			}
			else if(driver.findElement(By.xpath("//div[div[b[text()='"+editDevicename+"']]]//following-sibling::div//button[text()='Action']")).isDisplayed())
			{
				WebElement EditDevice_Interface_Actiondropdown= driver.findElement(By.xpath("//div[div[b[text()='"+editDevicename+"']]]//following-sibling::div//button[text()='Action']"));
				Clickon(EditDevice_Interface_Actiondropdown);	
			}

			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			compareText(application, "Edit Interface/Link", "editinterface_header", "Edit", xml);

			editcheckbox_commonMethod(application, edit_configureinterface_checkbox, "configureinterface_checkbox", "Configure Interface on Device", xml);
			if(!edit_interfacename.equalsIgnoreCase("null"))
			{
				cleartext(application, "Interface", "interfacename_textfield");
				addtextFields_commonMethod(application, "Interface", "interfacename_textfield", edit_interfacename, xml);
			}
			else
			{
				GetText(application, "Interface", "interfacename_textfield");
			}
			addDropdownValues_commonMethod(application, "Network", "network_fieldvalue", edit_network, xml);

			//verify EIP Allocation
			Thread.sleep(1000);
			click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
			{
				compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
				GetText(application, "Subnet Type", "subnettype_value");
				addDropdownValues_commonMethod(application, "City", "eipallocation_citydropdown", edit_eipallocation_city, xml);
				addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", edit_interfaceaddressrange_value, xml);
				GetText(application, "Available Pools", "eipallocation_availablepools_value");
				//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Close", "closesymbol", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
			}
			Thread.sleep(2000);

			//verify getaddress
			if(getaddress.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress1_button", xml);
				addDropdownValues_commonMethod(application, "Interface Address Range", "interfacerange_Address_dropdown", edit_interfaceaddressrange_value, xml);
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", edit_interfaceaddressrange_value, xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

			}
			compareText_fromtextFields(application, "Network", "networkipv6_fieldvalue", "WAN (XFER)", xml);

			//verify EIP Allocation
			Thread.sleep(1000);
			click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
			{
				compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
				GetText(application, "Subnet Type", "subnettype_value");
				GetText(application, "Space Name", "eipallocation_spacename");
				addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_ipv6_subnetsize", edit_interfaceaddressrange_value, xml);
				addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", edit_ipallocation_availableblocksvalue, xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
				Thread.sleep(2000);
				scrollToTop();
				GetText(application, "Subnet aalocation success message", "successmsg");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
			}
			Thread.sleep(2000);

			//verify getaddress ipv6
			if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress2_button", xml);
				addDropdownValues_commonMethod(application, "Interface Address Range IPv6", "interfacerange_AddressIpv6_dropdown", edit_interfaceaddressrangeIPv6_value, xml);
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", edit_interfaceaddressrangeIPv6_value, xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

			}

			ScrolltoElement(application, "link_textfield", xml);
			addtextFields_commonMethod(application, "Link", "link_textfield", edit_linkvalue, xml);
			addDropdownValues_commonMethod(application, "Bearer Type", "bearertype_dropdown", edit_bearertype_value, xml);
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", edit_bandwidth_value, xml);
			if(edit_bearertype_value.equalsIgnoreCase("E1")) {
				addDropdownValues_commonMethod(application, "Framing Type", "framingtype_dropdown", edit_framingtype_value, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("Ethernet"))
			{
				addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", edit_vlanID_value, xml);
			}
			else
			{
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
			}
			Thread.sleep(1000);
			scrolltoend();

			
//			if(getwebelement(xml.getlocator("//locators/" + application + "/bgp_checkbox")).isSelected())
//			{
//				editcheckbox_commonMethod(application, edit_bgp_checkbox, "bgp_checkbox", "BGP", xml);
//				if(edit_bgp_checkbox.equalsIgnoreCase("yes"))
//				{
//					addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", edit_bgptemplate_dropdownvalue, xml);
//				}
//				if(!edit_cpewan_value.equalsIgnoreCase("null"))
//				{
//					cleartext(application, "CPE WAN", "cpewan_textfield");
//					addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", edit_cpewan_value, xml);
//				}
//				else
//				{
//					GetText(application, "CPE WAN", "cpewan_textfield");
//				}
//				if(!edit_cpewanipv6_value.equalsIgnoreCase("null"))
//				{
//					cleartext(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield");
//					addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", edit_cpewanipv6_value, xml);
//				}
//				else
//				{
//					GetText(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield");
//				}
//				if(!edit_descriptionfield_value.equalsIgnoreCase("null"))
//				{
//					cleartext(application, "Description", "bgp-descriptionfield");
//					addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", edit_descriptionfield_value, xml);
//				}
//				else
//				{
//					GetText(application, "Description", "bgp-descriptionfield");
//				}
//				if(!edit_ascustomerfield_value.equalsIgnoreCase("null"))
//				{
//					cleartext(application, "AS CUSTOMER", "bgp_ascustomerfield");
//					addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", edit_ascustomerfield_value, xml);
//				}
//				else
//				{
//					GetText(application, "AS CUSTOMER", "bgp_ascustomerfield");
//				}
//				if(!edit_bgppassword_value.equalsIgnoreCase("null"))
//				{
//					cleartext(application, "BGP PASSWORD", "bgppassword_field");
//					addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", edit_bgppassword_value, xml);
//				}
//				else
//				{
//					GetText(application, "BGP PASSWORD", "bgppassword_field");
//				}
//			
//			
//				//Add IP Subnet IPv6
//				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
//				addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", ipsubnetipv6_value, xml);
//				//Remove IP Subnet IPv6
//				click_commonMethod(application, "IP Subnet IPv6 Remove", "ipsubnetipv6_removebutton", xml);
//				//Add IP Subnet IPv6
//				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
//				addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", ipsubnetipv6_value, xml);
//				//Add IP Subnet IPv4
//				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
//				addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", ipsubnetipv4_value, xml);
//				//Remove IP Subnet IPv4
//				click_commonMethod(application, "IP Subnet IPv4 Remove", "ipsubnetipv4_removebutton", xml);
//				//Add IP Subnet IPv4
//				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
//				addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", ipsubnetipv4_value, xml);
//			}
			
			
			Thread.sleep(1000);
			scrolltoend();
			click_commonMethod(application, "OK", "okbutton", xml);
			Thread.sleep(2000);
			compareText(application, "Interface update success message", "successmsg", "Interface successfully updated", xml);

		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface is not added");
		}

	}

	
	
	
	
	
	
	
	
	
	public void verify_JuniperVendor_InterfacePanel(String application, String interfacename, String getaddress, String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String bearertype_value, String bandwidth_value, String encapsulation_value, String bgp_checkbox, String framingtype_value, String vlanID_value, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value, String name, String editDevicename, String edit_configureinterface_checkbox, String edit_interfacename, String edit_network, String edit_eipallocation_city, String edit_interfaceaddressrange_value, String edit_interfaceaddressrangeIPv6_value, String edit_availableblocksvalue, String edit_linkvalue, String edit_bearertype_value, String edit_bandwidth_value, String edit_framingtype_value, String edit_encapsulation_value, String edit_vlanID_value, String edit_bgp_checkbox, String edit_bgptemplate_dropdownvalue, String edit_cpewan_value, String edit_cpewanipv6_value, String edit_descriptionfield_value, String edit_ascustomerfield_value, String edit_bgppassword_value, String ipsubnetipv6_value, String ipsubnetipv4_value, String juniper_configureinterface_checkbox, String cardtype_dropdownvalue, String clocksource_value, String STM1Number_value, String bearerNo_value, String unitid_value, String slot_value, String pic_value, String port_value, String ivmanagement_checkbox, String atricaconnected_checkbox, String edit_juniper_configureinterface_checkbox, String edit_juniper_interfacename, String edit_cardtype_dropdownvalue, String edit_clocksource_value, String edit_STM1Number_value, String edit_bearerNo_value, String edit_unitid_value, String edit_slot_value, String edit_pic_value, String edit_port_value, String edit_ivmanagement_checkbox, String edit_atricaconnected_checkbox) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "portalaccess_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			click_commonMethod(application, "View", "viewservicepage_viewdevicelink", xml);
			Thread.sleep(5000);
			scrollToTop();
			compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
		}
		
		ScrolltoElement(application, "routertools_header", xml);
		Thread.sleep(1000);
		compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
		click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
		click_commonMethod(application, "Add Interface/Link", "addinterface_link", xml);
		Thread.sleep(2000);
		scrollToTop();
		GetText(application, "Add Interface header", "addinterface_header");
		scrolltoend();
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
		addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", juniper_configureinterface_checkbox, "yes", xml);
		compareText(application, "Network", "network_fieldvalue", "XFER", xml);

		//verify EIP Allocation
		Thread.sleep(1000);
		click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
		{
			compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
			GetText(application, "Subnet Type", "subnettype_value");
			addDropdownValues_commonMethod(application, "City", "eipallocation_citydropdown", eipallocation_city, xml);
			addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", interfaceaddressrange_value, xml);
			GetText(application, "Available Pools", "eipallocation_availablepools_value");
			//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Close", "closesymbol", xml);
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
		}
		Thread.sleep(2000);

		//verify getaddress
		if(getaddress.equalsIgnoreCase("yes"))
		{
			click_commonMethod(application, "Get Address", "getaddress1_button", xml);
			addDropdownValues_commonMethod(application, "Interface Address Range", "interfacerange_Address_dropdown", interfaceaddressrange_value, xml);
			click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
		}
		else
		{
			addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", interfaceaddressrange_value, xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

		}

		compareText_fromtextFields(application, "Network", "networkipv6_fieldvalue", "WAN (XFER)", xml);

		//verify EIP Allocation for IPV6
		Thread.sleep(1000);
		click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
		{
			compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
			GetText(application, "Subnet Type", "subnettype_value");
			GetText(application, "Space Name", "eipallocation_spacename");
			addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_ipv6_subnetsize", interfaceaddressrange_value, xml);
			addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", eipallocation_availableblocksvalue, xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
			Thread.sleep(2000);
			scrollToTop();
			compareText(application, "Subnet aalocation success message", "successmsg", "Subnet Allocation IPTransitService_05-IPTransitService_05-XFER-0017527 (null)Successfully", xml);
		}
		else
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
		}
		Thread.sleep(2000);

		//verify getaddress ipv6
		if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
		{
			click_commonMethod(application, "Get Address", "getaddress2_button", xml);
			addDropdownValues_commonMethod(application, "Interface Address Range IPv6", "interfacerange_AddressIpv6_dropdown", interfaceaddressrange_value, xml);
			click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
		}
		else
		{
			addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

		}

		ScrolltoElement(application, "link_textfield", xml);
		addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
		addDropdownValues_commonMethod(application, "Bearer Type", "bearertype_dropdown", bearertype_value, xml);
		if(bearertype_value.equalsIgnoreCase("10Gigabit Ethernet") || bearertype_value.equalsIgnoreCase("Gigabit Ethernet") || bearertype_value.equalsIgnoreCase("Gigabit Ethernet")) {
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
			addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", cardtype_dropdownvalue, xml);
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("E1"))
		{
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
			addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", cardtype_dropdownvalue, xml);
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
			addDropdownValues_commonMethod(application, "Framing Type", "framingtype_dropdown", framingtype_value, xml);
			addDropdownValues_commonMethod(application, "Clock Source", "clocksource_dropdown", clocksource_value, xml);
			addDropdownValues_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
			addDropdownValues_commonMethod(application, "Bearer No", "bearerno_textfield", bearerNo_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("E3") || bearertype_value.equalsIgnoreCase("T3"))
		{
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
			addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", cardtype_dropdownvalue, xml);
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
			addDropdownValues_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
			addDropdownValues_commonMethod(application, "Bearer No", "bearerno_textfield", bearerNo_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("STM-1"))
		{
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
			addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", cardtype_dropdownvalue, xml);
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
			addDropdownValues_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
		}
		else
		{
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
			addDropdownValues_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
		}
		Thread.sleep(1000);
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
		
//		addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", bgp_checkbox, "no", xml);
//		if(bgp_checkbox.equalsIgnoreCase("yes"))
//		{
//			addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", bgptemplate_dropdownvalue, xml);
//			addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
//			addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
//			addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
//			addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
//			addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
//		}

		//configuration panel in add interface page
		if(juniper_configureinterface_checkbox.equalsIgnoreCase("yes") || juniper_configureinterface_checkbox.equalsIgnoreCase("null"))
		{
			compareText(application, "Configuration", "configuration_header", "Configuration", xml);
			click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
			Thread.sleep(2000);
			GetText(application, "Configuration", "configuration_textarea");
			Thread.sleep(1000);
			click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
		}
		else
		{
			click_commonMethod(application, "OK", "okbutton", xml);
		}
		Thread.sleep(5000);
		compareText(application, "Interface Added success message", "successmsg", "Interface added successfully", xml);
		Thread.sleep(2000);

		//Verify added interface
		ScrolltoElement(application, "routertools_header", xml);
		WebElement AddedInterfaces= driver.findElement(By.xpath("//div[text()='Interfaces']/parent::div/following-sibling::div//div[@ref='eBodyContainer']"));
		String addedinterfacecheck= AddedInterfaces.getAttribute("style");
		if(!addedinterfacecheck.contains("height: 1px"))
		{
			compareText(application, "Interfaces", "interfaces_header", "Interfaces", xml);
			String Interface_RowID= driver.findElement(By.xpath("//div[@col-id='interfaceName'][text()='"+Juniper_InterfaceName+"']/parent::div")).getAttribute("row-id");

			WebElement InterfaceName_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='interfaceName']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
			WebElement Link_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='linkId']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
			WebElement InterfaceAddressRange_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='interfaceAddressRange']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
			WebElement InterfaceAddress_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='interfaceAddress']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
			WebElement BearerType_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='bearerType']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
			WebElement Bandwidth_value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='bandWidth']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
			Clickon(Bandwidth_value);
			Bandwidth_value.sendKeys(Keys.TAB);
			WebElement vlanIDValue= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='vlanId']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanIDValue);
			Clickon(vlanIDValue);
			vlanIDValue.sendKeys(Keys.TAB);
			WebElement IfInOctets_Value= driver.findElement(By.xpath("//div[@row-id='"+Interface_RowID+"']/div[@col-id='ifInOctets']"));
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

		//edit Interface
		ScrolltoElement(application, "portalaccess_header", xml);
		click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		Thread.sleep(1000);
		WebElement SelectInterface= driver.findElement(By.xpath("//div[@role='gridcell'][@col-id='interfaceName'][text()='"+Juniper_InterfaceName+"']/following-sibling::div//span[contains(@class,'unchecked')]"));

		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("//div[div[b[text()='"+name+"']]]//following-sibling::div//button[text()='Action']")).isDisplayed())
			{
				WebElement AddedDevice_Interface_Actiondropdown= driver.findElement(By.xpath("//div[div[b[text()='"+name+"']]]//following-sibling::div//button[text()='Action']"));
				Clickon(AddedDevice_Interface_Actiondropdown);
			}
			else if(driver.findElement(By.xpath("//div[div[b[text()='"+editDevicename+"']]]//following-sibling::div//button[text()='Action']")).isDisplayed())
			{
				WebElement EditDevice_Interface_Actiondropdown= driver.findElement(By.xpath("//div[div[b[text()='"+editDevicename+"']]]//following-sibling::div//button[text()='Action']"));
				Clickon(EditDevice_Interface_Actiondropdown);	
			}

			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			compareText(application, "Edit Interface/Link", "editinterface_header", "Edit", xml);

			editcheckbox_commonMethod(application, edit_juniper_configureinterface_checkbox, "configureinterface_checkbox", "Configure Interface on Device", xml);
			if(!edit_juniper_interfacename.equalsIgnoreCase("null"))
			{
				cleartext(application, "Interface", "interfacename_textfield");
				addtextFields_commonMethod(application, "Interface", "interfacename_textfield", edit_juniper_interfacename, xml);
			}
			else
			{
				GetText(application, "Interface", "interfacename_textfield");
			}
			addDropdownValues_commonMethod(application, "Network", "network_fieldvalue", edit_network, xml);

			//verify EIP Allocation
			Thread.sleep(1000);
			click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
			{
				compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
				GetText(application, "Subnet Type", "subnettype_value");
				addDropdownValues_commonMethod(application, "City", "eipallocation_citydropdown", edit_eipallocation_city, xml);
				addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", edit_interfaceaddressrange_value, xml);
				GetText(application, "Available Pools", "eipallocation_availablepools_value");
				//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Close", "closesymbol", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
			}
			Thread.sleep(2000);

			//verify getaddress
			if(getaddress.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress1_button", xml);
				addDropdownValues_commonMethod(application, "Interface Address Range", "interfacerange_Address_dropdown", edit_interfaceaddressrange_value, xml);
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", edit_interfaceaddressrange_value, xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

			}
			compareText_fromtextFields(application, "Network", "networkipv6_fieldvalue", "WAN (XFER)", xml);

			//verify EIP Allocation
			Thread.sleep(1000);
			click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
			{
				compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
				GetText(application, "Subnet Type", "subnettype_value");
				GetText(application, "Space Name", "eipallocation_spacename");
				addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_ipv6_subnetsize", edit_interfaceaddressrange_value, xml);
				addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", edit_availableblocksvalue, xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
				Thread.sleep(2000);
				scrollToTop();
				GetText(application, "Subnet aalocation success message", "successmsg");
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
			}
			Thread.sleep(2000);

			//verify getaddress ipv6
			if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress2_button", xml);
				addDropdownValues_commonMethod(application, "Interface Address Range IPv6", "interfacerange_AddressIpv6_dropdown", edit_interfaceaddressrangeIPv6_value, xml);
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", edit_interfaceaddressrangeIPv6_value, xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

			}

			ScrolltoElement(application, "link_textfield", xml);
			addtextFields_commonMethod(application, "Link", "link_textfield", edit_linkvalue, xml);
			addDropdownValues_commonMethod(application, "Bearer Type", "bearertype_dropdown", edit_bearertype_value, xml);

			if(edit_bearertype_value.equalsIgnoreCase("10Gigabit Ethernet") || edit_bearertype_value.equalsIgnoreCase("Gigabit Ethernet") || edit_bearertype_value.equalsIgnoreCase("Gigabit Ethernet")) {
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", edit_bandwidth_value, xml);
				addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", edit_cardtype_dropdownvalue, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("E1"))
			{
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", edit_bandwidth_value, xml);
				addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", edit_cardtype_dropdownvalue, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
				addDropdownValues_commonMethod(application, "Framing Type", "framingtype_dropdown", edit_framingtype_value, xml);
				addDropdownValues_commonMethod(application, "Clock Source", "clocksource_dropdown", edit_clocksource_value, xml);
				addDropdownValues_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				addDropdownValues_commonMethod(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("E3") || edit_bearertype_value.equalsIgnoreCase("T3"))
			{
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", edit_bandwidth_value, xml);
				addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", edit_cardtype_dropdownvalue, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
				addDropdownValues_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				addDropdownValues_commonMethod(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
			}
			else if(edit_bearertype_value.equalsIgnoreCase("STM-1"))
			{
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", edit_bandwidth_value, xml);
				addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", edit_cardtype_dropdownvalue, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
				addDropdownValues_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
			}
			else
			{
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
				addDropdownValues_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
			}
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", edit_unitid_value, xml);
			addtextFields_commonMethod(application, "Slot", "slot_textfield", edit_slot_value, xml);
			addtextFields_commonMethod(application, "Pic", "pic_textfield", edit_pic_value, xml);
			addtextFields_commonMethod(application, "Port", "port_textfield", edit_port_value, xml);
			String edit_Juniper_InterfaceName= GetText(application, "Interface", "interfacename_textfield");
			if(edit_encapsulation_value.equalsIgnoreCase("802.1q"))
			{
				addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", edit_vlanID_value, xml);
			}
			addCheckbox_commonMethod(application, "ivmanagement_checkbox", "IV Management", edit_ivmanagement_checkbox, "no", xml);
			addCheckbox_commonMethod(application, "atricaconnected_checkbox", "Atrica Connected", edit_atricaconnected_checkbox, "no", xml);
			
//			addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", edit_bgp_checkbox, "no", xml);
//			if(edit_bgp_checkbox.equalsIgnoreCase("yes"))
//			{
//				addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", edit_bgptemplate_dropdownvalue, xml);
//				addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", edit_cpewan_value, xml);
//				addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", edit_cpewanipv6_value, xml);
//				addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", edit_descriptionfield_value, xml);
//				addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", edit_ascustomerfield_value, xml);
//				addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", edit_bgppassword_value, xml);
//			
//				//Add IP Subnet IPv6
//				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
//				addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", ipsubnetipv6_value, xml);
//				//Remove IP Subnet IPv6
//				click_commonMethod(application, "IP Subnet IPv6 Remove", "ipsubnetipv6_removebutton", xml);
//				//Add IP Subnet IPv6
//				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
//				addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", ipsubnetipv6_value, xml);
//				//Add IP Subnet IPv4
//				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
//				addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", ipsubnetipv4_value, xml);
//				//Remove IP Subnet IPv4
//				click_commonMethod(application, "IP Subnet IPv4 Remove", "ipsubnetipv4_removebutton", xml);
//				//Add IP Subnet IPv4
//				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
//				addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", ipsubnetipv4_value, xml);
//			
//			}

			
			//configuration panel in add interface page
			if(edit_juniper_configureinterface_checkbox.equalsIgnoreCase("yes") || edit_juniper_configureinterface_checkbox.equalsIgnoreCase("null"))
			{
				compareText(application, "Configuration", "configuration_header", "Configuration", xml);
				click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
				Thread.sleep(2000);
				GetText(application, "Configuration", "configuration_textarea");
				Thread.sleep(1000);
				click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
				Thread.sleep(2000);
				click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
			}
			else
			{
				click_commonMethod(application, "OK", "okbutton", xml);
			}
			Thread.sleep(5000);
		compareText(application, "Interface update success message", "successmsg", "Interface successfully updated", xml);

	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.PASS, "Interface is not added");
	}

}

	
	
	
	
	
	
	
public void deleteInterface_PE(String application, String interfacename, String name, String editDevicename) throws InterruptedException, DocumentException {
	//Delete Interface
	ScrolltoElement(application, "portalaccess_header", xml);
	scrolltoend();
	//click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
	Thread.sleep(1000);
	WebElement SelectInterface1= driver.findElement(By.xpath("//div[@role='gridcell' and @col-id='interfaceName']/div[text()='"+interfacename+"']"));
	if(SelectInterface1.isDisplayed())
	{
		Clickon(SelectInterface1);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
		Thread.sleep(1000);
		
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




public void verifyAddMultilink_PE(String application, String name, String multilink_interfacename, String configureinterface_checkbox, String getaddress, String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String bandwidth_value, String encapsulation_value, String bgp_checkbox, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value) throws InterruptedException, DocumentException, IOException {
	ScrolltoElement(application, "portalaccess_header", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		List<WebElement> addeddevicesList= driver.findElements(By.xpath("//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div[@class='div-margin row']//b"));
		System.out.println(addeddevicesList);
		int AddedDevicesCount= addeddevicesList.size();
		for(int i=0;i<AddedDevicesCount;i++) {
			String AddedDeviceNameText= addeddevicesList.get(i).getText();
			String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
			if(AddedDeviceNameText.contains(name))
			{
				WebElement AddedDevice_ViewLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='View']"));
				Clickon(AddedDevice_ViewLink);
				Thread.sleep(5000);
				compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);

				ScrolltoElement(application, "routertools_header", xml);
				Thread.sleep(1000);
				compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
				click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
				click_commonMethod(application, "Add Multilink", "addmultilink_link", xml);
				Thread.sleep(2000);
				scrollToTop();
				GetText(application, "Add Multilink header", "addmultilink_header");
				scrolltoend();
				click_commonMethod(application, "OK", "okbutton", xml);
				Thread.sleep(1000);
				scrollToTop();
				//verify warning messages in add interface page
				warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);
				warningMessage_commonMethod(application, "bandwidth_warngmsg", "Bandwidth", xml);
				warningMessage_commonMethod(application, "encapsulation_warngmsg", "Encapsulation", xml);

				//Add Multilink
				addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", configureinterface_checkbox, "no", xml);
				isDisplayed(application, "Multilink", "multilink_text");
				addtextFields_commonMethod(application, "Interface", "interfacename_textfield", multilink_interfacename, xml);
				compareText(application, "Network", "network_fieldvalue", "XFER", xml);

				//verify EIP Allocation
				Thread.sleep(1000);
				click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
				{
					compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
					GetText(application, "Subnet Type", "subnettype_value");
					addDropdownValues_commonMethod(application, "City", "eipallocation_citydropdown", eipallocation_city, xml);
					addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", interfaceaddressrange_value, xml);
					GetText(application, "Available Pools", "eipallocation_availablepools_value");
					//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
					Thread.sleep(1000);
					click_commonMethod(application, "Close", "closesymbol", xml);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
				}
				Thread.sleep(2000);

				//verify getaddress
				if(getaddress.equalsIgnoreCase("yes"))
				{
					click_commonMethod(application, "Get Address", "getaddress1_button", xml);
					addDropdownValues_commonMethod(application, "Interface Address Range", "interfacerange_Address_dropdown", interfaceaddressrange_value, xml);
					click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
				}
				else
				{
					addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", interfaceaddressrange_value, xml);
					Thread.sleep(1000);
					click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

				}

				compareText_fromtextFields(application, "Network", "networkipv6_fieldvalue", "WAN (XFER)", xml);

				//verify EIP Allocation
				Thread.sleep(1000);
				click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
				{
					compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
					GetText(application, "Subnet Type", "subnettype_value");
					GetText(application, "Space Name", "eipallocation_spacename");
					addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_ipv6_subnetsize", interfaceaddressrange_value, xml);
					addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", eipallocation_availableblocksvalue, xml);
					Thread.sleep(1000);
					click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
					Thread.sleep(2000);
					scrollToTop();
					compareText(application, "Subnet aalocation success message", "successmsg", "Subnet Allocation IPTransitService_05-IPTransitService_05-XFER-0017527 (null)Successfully", xml);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
				}
				Thread.sleep(2000);

				//verify getaddress ipv6
				if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
				{
					click_commonMethod(application, "Get Address", "getaddress2_button", xml);
					addDropdownValues_commonMethod(application, "Interface Address Range IPv6", "interfacerange_AddressIpv6_dropdown", interfaceaddressrange_value, xml);
					click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
				}
				else
				{
					addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
					Thread.sleep(1000);
					click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

				}

				ScrolltoElement(application, "link_textfield", xml);
				addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
				Thread.sleep(1000);
				scrolltoend();
				
//				addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", bgp_checkbox, "no", xml);
//				if(bgp_checkbox.equalsIgnoreCase("yes"))
//				{
//					addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", bgptemplate_dropdownvalue, xml);
//					addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
//					addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
//					addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
//					addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
//					addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
//				}

				//Multilinked Bearers table
				compareText(application, "Multilinked Bearers", "multilinkedbearers_header", "Multilinked Bearers", xml);

				//table columns
				compareText(application, "Check to add Interface", "checktoaddinterface_column", "Check to add Interface", xml);
				compareText(application, "Interface", "multilink_interface_column", "Interface", xml);
				compareText(application, "Link/Circuit", "multilink_link_column", "Link/Circuit", xml);
				compareText(application, "Bearer Type", "multilink_BearerType_column", "Bearer Type", xml);
				compareText(application, "Bandwidth", "multilink_bandwidth_column", "Bandwidth", xml);
				compareText(application, "VLAN Id", "multilink_vlanid_column", "VLAN Id", xml);
				compareText(application, "lflnOctets", "multilink_ifinoctets_column", "lflnOctets", xml);

				if(configureinterface_checkbox.equalsIgnoreCase("yes"))
				{
					compareText(application, "Configuration", "configuration_header", "Configuration", xml);
					click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
					Thread.sleep(2000);
					GetText(application, "Configuration", "configuration_textarea");
					Thread.sleep(1000);
					click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
					Thread.sleep(2000);
					click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);
				}
				else
				{
					click_commonMethod(application, "OK", "okbutton", xml);
				}
				Thread.sleep(5000);
				compareText(application, "Add Multilink success message", "successmsg", "Multilink Interface successfully created", xml);
				ScrolltoElement(application, "routertools_header", xml);
				Thread.sleep(1000);
				compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);

				//Multilink table values under interfaces panel
				String Multilink_Name= "Multilink"+multilink_interfacename;
				String Multilink_RowID= driver.findElement(By.xpath("//div[@col-id='interfaceName'][text()='"+Multilink_Name+"']/parent::div")).getAttribute("row-id");

				WebElement InterfaceName_value= driver.findElement(By.xpath("//div[@row-id='"+Multilink_RowID+"']/div[@col-id='interfaceName']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
				WebElement Link_value= driver.findElement(By.xpath("//div[@row-id='"+Multilink_RowID+"']/div[@col-id='linkId']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
				WebElement InterfaceAddressRange_value= driver.findElement(By.xpath("//div[@row-id='"+Multilink_RowID+"']/div[@col-id='interfaceAddressRange']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
				WebElement InterfaceAddress_value= driver.findElement(By.xpath("//div[@row-id='"+Multilink_RowID+"']/div[@col-id='interfaceAddress']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
				WebElement BearerType_value= driver.findElement(By.xpath("//div[@row-id='"+Multilink_RowID+"']/div[@col-id='bearerType']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
				WebElement Bandwidth_value= driver.findElement(By.xpath("//div[@row-id='"+Multilink_RowID+"']/div[@col-id='bandWidth']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
				Clickon(Bandwidth_value);
				Bandwidth_value.sendKeys(Keys.TAB);
				WebElement vlanIDValue= driver.findElement(By.xpath("//div[@row-id='"+Multilink_RowID+"']/div[@col-id='vlanId']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanIDValue);
				Clickon(vlanIDValue);
				vlanIDValue.sendKeys(Keys.TAB);
				WebElement IfInOctets_Value= driver.findElement(By.xpath("//div[@row-id='"+Multilink_RowID+"']/div[@col-id='ifInOctets']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
				Thread.sleep(2000);
				scrolltoend();
				click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				Thread.sleep(2000);
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

public void verifyInterfaceConfigHistory(String application) throws InterruptedException, DocumentException {
	
	ScrolltoElement(application, "portalaccess_header", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		click_commonMethod(application, "View", "viewservicepage_viewdevicelink", xml);
		Thread.sleep(5000);
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
	
	ScrolltoElement(application, "interfaces_header", xml);
	Thread.sleep(1000);
	compareText(application, "Interface Configuration History header", "interfaceconfighistory_header", "Interface Configuration History", xml);
	compareText(application, "Date column", "date_column", "Date", xml);
	compareText(application, "File Name column", "filename_column", "File Name", xml);
	Thread.sleep(1000);

}


public void selectInterfacelinkforEquipment(String application, String devicename) throws InterruptedException, DocumentException {
	Thread.sleep(5000);

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
		Thread.sleep(5000);
		compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);
		}
	}
	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
	
	Thread.sleep(3000);
}




public void SelectInterfacetoremovefromservice(String application, String interfacename)
		throws IOException, InterruptedException, DocumentException {

	ScrolltoElement(application, "labelname_managementAddresss", xml);
	Thread.sleep(2000);

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
								Thread.sleep(3000);

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


public void navigateToViewDevicePage_PE2(String application, String devicename) throws InterruptedException, DocumentException {
	//click_commonMethod(application, "Back Button", "BackButtonxpath", xml);
	ScrolltoElement(application, "ConfigurationOptions_Header", xml);//portalaccess_header//ConfigurationOptions_Header
	//ScrolltoElement(application, "ActelisConfiguration_PanelHeaderInViewServicePage", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		List<WebElement> addeddevicesList= driver.findElements(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b"));
		int AddedDevicesCount= addeddevicesList.size();
		for(int i=0;i<AddedDevicesCount;i++) {
			String AddedDeviceNameText= addeddevicesList.get(i).getText();
			String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
			if(AddedDeviceNameText.contains(devicename))
			{
				WebElement AddedDevice_ViewLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='View']"));
				//Clickon(AddedDevice_ViewLink);
				click_commonMethod(application, "View Link", "viewservicepage_viewdevicelink", xml);
				Thread.sleep(5000);
				compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);
			}
		}
		
	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
	
}




public void navigateToViewDevicePageDistributionSwitch(String application, String PE_DeviceName) throws InterruptedException, DocumentException {
	try {
	ScrolltoElement(application, "ConfigurationOptions_Header", xml);//ProviderEquipment_header //PE_AddPEDeviceLink
	if(getwebelement(xml.getlocator("//locators/" + application + "/DistributionSwitchexistingdevicegrid")).isDisplayed())
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




public void verifyFetchDeviceInterface_DS(String application, String devicename, String Inservice_status, String Inmaintenance_status, String vendormodel, String managementaddress, String snmpro, String country, String interfacename) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Fetch Device Functionality");
	scrollToTop();
	try {
	if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
		System.out.println("'View PE Device' page navigated as expected");
		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		click_commonMethod(application, "Fetch Interface", "viewdevice_fetchinterfacelink", xml);
		Thread.sleep(3000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_FetchDeviceInterfacesSuccessMessage")).isDisplayed()){
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
			System.out.println("Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
		if(getwebelement(xml.getlocator("//locators/" + application + "/PE_hereLink_UnderFetchDeviceInterfacesSuccessMessage")).isDisplayed()){
			
	Thread.sleep(2000);
	//click_commonMethod(application, "here", "PE_hereLink_UnderFetchDeviceInterfacesSuccessMessage", xml);/Not working
	safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" +application+ "/PE_hereLink_UnderFetchDeviceInterfacesSuccessMessage")));
	Thread.sleep(2000);


	//Manage Network
	compareText(application, "Manage Network header", "managenetwork_header", "Manage COLT's Network - Manage Network", xml);
	compareText(application, "Status header", "status_header", "Status", xml);
	compareText(application, "Synchronization header", "synchronization_header", "Synchronization", xml);

	//verify column headers
	compareText(application, "Status column header", "status_statuscloumn", "Status", xml);
	compareText(application, "Last Modification column header", "status_lastmodificationcolumn", "Last Modification", xml);
	compareText(application, "Action column header", "status_Action", "Action", xml);
	compareText(application, "Device column header", "synchronization_devicecolumn", "Device", xml);
	compareText(application, "Sync Status column header", "synchronization_syncstatuscolumn", "Sync Status", xml);
	compareText(application, "Smarts column header", "synchronization_smartscolumn", "Smarts", xml);
	compareText(application, "Fetch Interfaces column header", "synchronization_FetchInterfacescolumn", "Fetch Interfaces", xml);
	compareText(application, "VistaMart Device column header", "synchronization_vistamartdevice", "VistaMart Device", xml);
	compareText(application, "Action column header", "synchronization_actioncolumn", "Action", xml);

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,  " : here Link is not displayed in Manage COLT's Network Device page");
			System.out.println(  " : here Link is not displayed in Manage COLT's Network Device page");
		}
		
		
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
	compareText(application, "Snmpro field header", "statuspage_snmproheader", "Snmpro", xml);
	compareText(application, "Country field header", "statuspage_countryheader", "Country", xml);
	compareText(application, "City field header", "statuspage_cityheader", "City", xml);
	compareText(application, "Site field header", "statuspage_siteheader", "Site", xml);
	compareText(application, "Premise field header", "statuspage_premiseheader", "Premise", xml);

	//verify field values in status page
	compareText(application, "Name", "statuspage_namevalue", devicename, xml);
	compareText(application, "Vendor/Model", "statuspage_vendormodelvalue", vendormodel, xml);
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
	Thread.sleep(2000);
	scrollToTop();
	compareText(application, "Device status update success message", "Sync_successmsg", "Device Status history successfully changed", xml);
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

									compareText(application, "Changed By", "statuspage_changedbyvalue", Getkeyvalue("APT_NonVoiceService_Username"), xml);
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
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No interfaces added");

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
		compareText(application, "Current Status", "interface_statuspage_currentstatusvalue", Inservice_status, xml);
		click_commonMethod(application, "New Status Dropdown", "interface_statuspage_newstatusdropdown", xml);
		WebElement selectNewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue"));
		Clickon(selectNewStatusvalue1);
		String NewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue")).getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue1);
		click_commonMethod(application, "OK", "interface_statuspage_okbutton", xml);
		Thread.sleep(2000);
		scrollToTop();
		compareText(application, "Interface status update success message", "Sync_successmsg", "Interface Status History successfully changed.", xml);
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
	compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this device.", xml);
	Thread.sleep(2000);

		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			System.out.println("Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
			}
		
	
	
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
		System.out.println("'View PE Device' page navigated");
	}
		
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Manage COLT's Network page");
		System.out.println(  e+ " : Field is not displayed in Manage COLT's Network Device page");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Manage COLT's Network Device page");
		System.out.println(  e+" : Field is not displayed in Manage COLT's Network Device page");
	}
}


public void addRouterFunction_PE(String application, String ServiceIdentification, String CPE_DeviceName,String CPE_RouteCity, 
		String CPE_RouteSubnetSize, String	CPE_Gateway,String	CPE_NetworkAddress, String	CPE_NetworkMAS, String	CPE_Metrics
		) throws InterruptedException, DocumentException, IOException {
	
	ScrolltoElement(application, "routertools_header", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
		click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
		click_commonMethod(application, "Add Link", "Routes_AddLink", xml);
		compareText(application, "Add Route header", "Routes_AddRoutes_Header", "Add Route", xml);
		click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);
		Thread.sleep(4000);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
			
			compareText(application, "Subnet Type Value", "Routes_EIPAllocation_SubnetTypeValue", "XFER", xml);
			SelectDropdownValueUnderDivTag(application, "City dropdown", CPE_RouteCity, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueDivTag", xml);
			click_commonMethod(application, "Selecting City values", "Routes_ValueSelection", xml);
			SelectDropdownValueUnderDivTag(application, "Subnet Size dropdown", CPE_RouteSubnetSize, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueDivTag", xml);
			click_commonMethod(application, "Selecting Subnetsize values", "Routes_ValueSelection", xml);

			Thread.sleep(2000);
			compareText(application, "Available Pools Message", "Routes_EIPAllocation_AvailablePoolsMessage", "None of applicable pools contains a largest available fit which is equal to the selected network size. Please contact RIPEmaster team.", xml);
			compareText(application, "Allocate Subnet Button", "Routes_AllocateSubnetButon", "Allocate Subnet", xml);
			click_commonMethod(application, "Close EIP Allocation window", "Routes_EIPAllocation_CloseButton", xml);
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Subnet Allocation for Service popup is not displaying");
		}
		
		click_commonMethod(application, "OK Button", "Routes_OKButton", xml);
		warningMessage_commonMethod(application, "Routes_Gatway_WarningMessage", "Getway", xml);
		warningMessage_commonMethod(application, "Routes_NetworkAddress_WarningMessage", "Network Address", xml);
		warningMessage_commonMethod(application, "Routes_NetworkMASK_WarningMessage", "Network MAS", xml);
		
		addtextFields_commonMethod(application, "Getway", "Routes_Gatewaytextfield", CPE_Gateway, xml);
		addtextFields_commonMethod(application, "Network Address", "Routes_NetworkAddresstextfield", CPE_NetworkAddress, xml);
		addtextFields_commonMethod(application, "Network MAS", "Routes_NetworkMAStextfield", CPE_NetworkMAS, xml);
		addtextFields_commonMethod(application, "Metrics", "Routes_Metricstextfield", CPE_Metrics, xml);
		
		click_commonMethod(application, "OK Button", "Routes_OKButton", xml);
		scrollToTop();
		compareText(application, "Add Route Success Message", "AddRouteSuccessMessage", "Static Route successfully created.", xml);
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
	}

}





public void editRouterFunction_PE(String application, String ServiceIdentification, String CPE_DeviceName,String CPE_RouteCityEdit, 
		String CPE_RouteSubnetSizeEdit, String	CPE_GatewayEdit,String	CPE_NetworkAddressEdit, String	CPE_NetworkMASEdit, String	CPE_MetricsEdit
		) throws InterruptedException, DocumentException, IOException {
	
	ScrolltoElement(application, "interfaces_header", xml);//interfaces_header//routertools_header//CustomerReadonlySNMP_panelHeader//Routes_Header
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
		click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
		click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
		click_commonMethod(application, "Edit Link", "Routes_EditLink", xml);
		compareText(application, "Edit Route header", "Routes_EditRoutes_Header", "Edit Route", xml);
		click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);
		Thread.sleep(4000);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
			
			compareText(application, "Subnet Type Value", "Routes_EIPAllocation_SubnetTypeValue", "CUST", xml);

			SelectDropdownValueUnderSpanTag(application, "City dropdown", CPE_RouteCityEdit, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueSpanTag", xml);
			SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", CPE_RouteSubnetSizeEdit, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);
			
			Thread.sleep(4000);
			compareText(application, "Available Pools Message", "Routes_EIPAllocation_AvailablePoolsMessage", "None of applicable pools contains a largest available fit which is equal to the selected network size. Please contact RIPEmaster team.", xml);
			compareText(application, "Allocate Subnet Button", "Routes_AllocateSubnetButon", "Allocate Subnet", xml);
			click_commonMethod(application, "Close EIP Allocation window", "EditRoutes_EIPAllocation_CloseButton", xml);
			click_commonMethod(application, "Close EIP Allocation window", "EditRoutes_EIPAllocation_CloseButton", xml);


		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Subnet Allocation for Service popup is not displaying");
		}
		
		ClearAndEnterTextValue(application, "Getway", "Routes_Gatewaytextfield", CPE_GatewayEdit, xml);
		ClearAndEnterTextValue(application, "Network Address", "Routes_NetworkAddresstextfield", CPE_NetworkAddressEdit, xml);
		ClearAndEnterTextValue(application, "Network MAS", "Routes_NetworkMAStextfield", CPE_NetworkMASEdit, xml);
		ClearAndEnterTextValue(application, "Metrics", "Routes_Metricstextfield", CPE_MetricsEdit, xml);
		
		click_commonMethod(application, "OK Button", "Routes_OKButton", xml);
		scrollToTop();
		compareText(application, "Add Route Success Message", "UpdateRouteSuccessMessage", "Static Route successfully updated.", xml);
		
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
	}

}




public void deleteRouterFunction_PE(String application, String ServiceIdentification, String CPE_DeviceName,String CPE_RouteCityEdit, 
		String CPE_RouteSubnetSizeEdit, String	CPE_GatewayEdit,String	CPE_NetworkAddressEdit, String	CPE_NetworkMASEdit, String	CPE_MetricsEdit
		) throws InterruptedException, DocumentException, IOException {
	
	ScrolltoElement(application, "interfaces_header", xml);//interfaces_header//routertools_header//CustomerReadonlySNMP_panelHeader//Routes_Header
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
		click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
		click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
		click_commonMethod(application, "Delete Link", "Routes_DeleteLink", xml);
		
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
		{
			click_commonMethod(application, "Delete", "Routes_DeleteButton", xml);
		}else{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
		scrollToTop();
		compareText(application, "Delete Route Success Message", "DeleteRouteSuccessMessage", "Static Route successfully deleted.", xml);
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
	}

}























public void navigateToViewDevicePage_CPE(String application, String CPE_DeviceName) throws InterruptedException, DocumentException {
	try {
	//ScrolltoElement(application, "CustomerPremiseEquipment_CPE_Panelheader", xml);//CustomerPremiseEquipment_CPE_Panelheader //managementOptionsPanelheader//CustomerPremiseEquipment_CPE_Panelheader
	scrolltoend();
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingCPEdevicegrid")).isDisplayed())
	{
		//**WebElement AddedDevice_ViewLink=getwebelement("//div[div[b[text()='"+CPE_DeviceName+"']]]//span[text()='View']");
		//**Clickon(AddedDevice_ViewLink);
		click_commonMethod(application, "View Link", "CPE_viewdevice1", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View CPE Device' Page navigated as expected");
			System.out.println("'View CPE Device' Page navigated as expected");
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'View CPE Device' Page not navigated");
			System.out.println("'View CPE Device' Page not navigated");
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


public void navigateToViewDevicePage_CPE2(String application, String devicename) throws InterruptedException, DocumentException {
	//click_commonMethod(application, "Back Button", "BackButtonxpath", xml);
	ScrolltoElement(application, "ConfigurationOptions_Header", xml);//portalaccess_header//ConfigurationOptions_Header
	//ScrolltoElement(application, "ActelisConfiguration_PanelHeaderInViewServicePage", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
		List<WebElement> addeddevicesList= driver.findElements(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b"));
		int AddedDevicesCount= addeddevicesList.size();
		for(int i=0;i<AddedDevicesCount;i++) {
			String AddedDeviceNameText= addeddevicesList.get(i).getText();
			String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
			if(AddedDeviceNameText.contains(devicename))
			{
				WebElement AddedDevice_ViewLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='View']"));
				//Clickon(AddedDevice_ViewLink);
				click_commonMethod(application, "View Link", "viewservicepage_viewdevicelink", xml);
				Thread.sleep(5000);
				compareText(application, "View device header", "viewdevicepage_header", "View PE Device", xml);
			}
		}
		
	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
	}
	
}


		//Add/Edit/Delete Routes
public void addRouteFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCity, 
		String PE_RouteSubnetSize, String	PE_Gateway,String	PE_NetworkAddress, String	PE_NetworkMAS, String	PE_Metrics
		) throws InterruptedException, DocumentException, IOException {
	
	ScrolltoElement(application, "interfaces_header", xml);//interfaces_header//CustomerReadonlyNMP_panelHeader//routertools_header
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
		click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
		click_commonMethod(application, "Add Link", "Routes_AddLink", xml);
		compareText(application, "Add Route header", "Routes_AddRoutes_Header", "Add Route", xml);
		click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);
		Thread.sleep(4000);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
			
			compareText(application, "Subnet Type Value", "CPE_Routes_EIPAllocation_SubnetTypeValue", "CUST", xml);
			click_commonMethod(application, "Allocate Subnet Button", "Routes_AllocateSubnetButon", xml);
			
			warningMessage_commonMethod(application, "Routes_EIPAllocation_CityWarningMessage", "City  warning message", xml);
			warningMessage_commonMethod(application, "Routes_EIPAllocation_SubnetSizeWarningMessage", "Subnet Size  warning message", xml);
			warningMessage_commonMethod(application, "Routes_EIPAllocation_AvilablePoolWarningMessage", "Avilable Pool  warning message", xml);
			
			SelectDropdownValueUnderSpanTag(application, "City dropdown", PE_RouteCity, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueSpanTag", xml);
			SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", PE_RouteSubnetSize, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);
			
			Thread.sleep(4000);
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
		compareText(application, "Add Route Success Message", "AddRouteSuccessMessage", "Static Route successfully created.", xml);
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
	}

	sa.assertAll();
}





public void editRouteFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
		String PE_RouteSubnetSizeEdit, String	PE_GatewayEdit,String	PE_NetworkAddressEdit, String	PE_NetworkMASEdit, String	PE_MetricsEdit
		) throws InterruptedException, DocumentException, IOException {
	
	ScrolltoElement(application, "interfaces_header", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
		click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
		click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
		click_commonMethod(application, "Edit Link", "Routes_EditLink", xml);
		compareText(application, "Edit Route header", "Routes_EditRoutes_Header", "Edit Route", xml);
		click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);
		Thread.sleep(4000);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
			
			compareText(application, "Subnet Type Value", "CPE_Routes_EIPAllocation_SubnetTypeValue", "CUST", xml);
			SelectDropdownValueUnderSpanTag(application, "City dropdown", PE_RouteCityEdit, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueSpanTag", xml);
			SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", PE_RouteSubnetSizeEdit, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);
			
			Thread.sleep(2000);
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
		compareText(application, "Add Route Success Message", "UpdateRouteSuccessMessage", "Static Route successfully updated.", xml);
		
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
	}

}








public void deleteRouteFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
		String PE_RouteSubnetSizeEdit, String	PE_GatewayEdit,String	PE_NetworkAddressEdit, String	PE_NetworkMASEdit, String	PE_MetricsEdit
		) throws InterruptedException, DocumentException, IOException {
	
	ScrolltoElement(application, "interfaces_header", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
		click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
		click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
		click_commonMethod(application, "Delete Link", "Routes_DeleteLink", xml);
		
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		
		
		// Capturing alert message.    
//	       String alertMessage= driver.switchTo().alert().getText();
//	       if(alertMessage.isEmpty()) {
//	    	   ExtentTestManager.getTest().log(LogStatus.FAIL, "No Message displays under Alert popup");
//		       System.out.println("No Message displays under Alert popup"); 
//	       }else {
//	    	   ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
//		       System.out.println("text message for alert displays as: "+alertMessage);
//	       }
	     
		GetMessageFromAlertJavaScriptPopup();
		
	     try {  
	       AcceptJavaScriptMethod();//CancelJavaScriptMethod();
			compareText(application, "Delete Route Success Message", "DeleteRouteSuccessMessage", "Static Route successfully deleted.", xml);
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	     
	     
	     
//		if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
//		{
//			click_commonMethod(application, "Delete", "Routes_DeleteButton", xml);
//		}else{
//			Log.info("Delete alert popup is not displayed");
//			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
//		}
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
	}

}


	//Customer Readonly SNMP Panel New Tab
	public void addCustomerReadonlySNMPFunction_CPE_NewTab(String application, String ServiceIdentification, String CPE_DeviceName,
			String CustomerIPAddress, String CustomerCommunityString
			) throws InterruptedException, DocumentException, IOException {

		//ScrolltoElement(application, "Routes_Header", xml);//interfaces_header//CustomerReadonlyNMP_panelHeader//Routes_Header

		if(getwebelement(xml.getlocator("//locators/" + application + "/CustomerReadonlySNMP_panelHeader")).isDisplayed()){
		
			
			click_commonMethod(application, "Action", "SNMP_Actionbutton", xml);
			//click_commonMethod(application, "Add Link", "SNMP_AddLink", xml);
			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/SNMP_AddLink")));
			RightClick(getwebelement(xml.getlocator("//locators/" + application + "/SNMP_AddLink")));
			//ClickOnExpectedLinkAfterRightClick();
			EnterByAction();
			
			
			
			
			
			
			
			
			
			
			
			
//			if(getwebelement(xml.getlocator("//locators/" + application + "/AddSNMP_Header")).isDisplayed()){
//	
//				compareText(application, "Add SNMP_Header", "AddSNMP_Header", "Add Customer Readonly SNMP", xml);
//				click_commonMethod(application, "OK Button", "SNMP_OKButton", xml);
//				warningMessage_commonMethod(application, "SNMP_CustomerIPAddress_WarningMessage", "Customer IP Address", xml);
//				warningMessage_commonMethod(application, "SNMP_CustomerCommunityString_WarningMessage", "Customer Community String", xml);
//	
//				addtextFields_commonMethod(application, "Customer IP Address", "SNMP_CustomerIPAddress_Textfield", CustomerIPAddress, xml);
//				addtextFields_commonMethod(application, "Customer Community String", "SNMP_CustomerCommunityString_Textfield", CustomerCommunityString, xml);
//
//				click_commonMethod(application, "OK Button", "SNMP_OKButton", xml);
//				scrollToTop();
//				compareText(application, "Add SNMP Success Message", "AddSNMPSuccessMessage", "Customer Readonly SNMP successfully created.", xml);
//
//			}else{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Add Customer Readonly SNMP  panel header is not displaying");
//				System.out.println("Add Customer Readonly SNMP  panel header is not displaying");
//			}

			sa.assertAll();
		}


}





		//Customer Readonly SNMP Panel
public void addCustomerReadonlySNMPFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,
		String CustomerIPAddress, String CustomerCommunityString
		) throws InterruptedException, DocumentException, IOException {

	ScrolltoElement(application, "Routes_Header", xml);//interfaces_header//CustomerReadonlyNMP_panelHeader//Routes_Header
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/CustomerReadonlySNMP_panelHeader")).isDisplayed()){
		click_commonMethod(application, "Action", "SNMP_Actionbutton", xml);
		click_commonMethod(application, "Add Link", "SNMP_AddLink", xml);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddSNMP_Header")).isDisplayed()){
			
			compareText(application, "Add SNMP_Header", "AddSNMP_Header", "Add Customer Readonly SNMP", xml);
			click_commonMethod(application, "OK Button", "SNMP_OKButton", xml);
			warningMessage_commonMethod(application, "SNMP_CustomerIPAddress_WarningMessage", "Customer IP Address", xml);
			warningMessage_commonMethod(application, "SNMP_CustomerCommunityString_WarningMessage", "Customer Community String", xml);

		addtextFields_commonMethod(application, "Customer IP Address", "SNMP_CustomerIPAddress_Textfield", CustomerIPAddress, xml);
		addtextFields_commonMethod(application, "Customer Community String", "SNMP_CustomerCommunityString_Textfield", CustomerCommunityString, xml);
		
		click_commonMethod(application, "OK Button", "SNMP_OKButton", xml);
		scrollToTop();
		compareText(application, "Add SNMP Success Message", "AddSNMPSuccessMessage", "Customer Readonly SNMP successfully created.", xml);
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Add Customer Readonly SNMP  panel header is not displaying");
		System.out.println("Add Customer Readonly SNMP  panel header is not displaying");
	}

	sa.assertAll();
}


}


	public void editCustomerReadonlySNMPFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,
		String CustomerIPAddressEdit, String CustomerCommunityStringEdit) throws InterruptedException, DocumentException, IOException {

	ScrolltoElement(application, "Routes_Header", xml);//interfaces_header//CustomerReadonlyNMP_panelHeader//Routes_Header
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/CustomerReadonlySNMP_panelHeader")).isDisplayed()){
		click_commonMethod(application, "Select SNMP Checkbox", "SNMP_SelectRouteCheckbox", xml);
		click_commonMethod(application, "Action", "SNMP_Actionbutton", xml);
		click_commonMethod(application, "Edit Link", "SNMP_EditLink", xml);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/EditSNMP_Header")).isDisplayed()){
			
			compareText(application, "Edit SNMP_Header", "EditSNMP_Header", "Edit Customer Readonly SNMP", xml);

		ClearAndEnterTextValue(application, "Customer IP Address", "SNMP_CustomerIPAddress_Textfield", CustomerIPAddressEdit, xml);
		ClearAndEnterTextValue(application, "Customer Community String", "SNMP_CustomerCommunityString_Textfield", CustomerCommunityStringEdit, xml);
		
		click_commonMethod(application, "OK Button", "SNMP_OKButton", xml);
		scrollToTop();
		compareText(application, "Update SNMP Success Message", "UpdateSNMPSuccessMessage", "Customer Readonly SNMP successfully updated.", xml);
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Customer Readonly SNMP  panel header is not displaying");
		System.out.println("Edit Customer Readonly SNMP  panel header is not displaying");
	}

	sa.assertAll();
}

}



public void deleteCustomerReadonlySNMPFunction_CPE(String application, String ServiceIdentification, String PE_DeviceName,String CustomerIPAddressEdit, 
			String CustomerCommunityStringEdit

		) throws InterruptedException, DocumentException, IOException {
	
	ScrolltoElement(application, "Routes_Header", xml);//interfaces_header//CustomerReadonlyNMP_panelHeader//Routes_Header
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/CustomerReadonlySNMP_panelHeader")).isDisplayed()){

		click_commonMethod(application, "Select SNMP Checkbox", "SNMP_SelectRouteCheckbox", xml);
		click_commonMethod(application, "Action", "SNMP_Actionbutton", xml);
		click_commonMethod(application, "Delete Link", "SNMP_DeleteLink", xml);
		
	
		if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
		{
			click_commonMethod(application, "Delete", "SNMP_DeleteButton", xml);
		}else{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed for SNMP");
		}
		scrollToTop();
		compareText(application, "Delete SNMP Success Message", "DeleteSNMPSuccessMessage", "Customer Readonly SNMP successfully deleted.", xml);
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Customer Readonly SNMP  panel header is not displaying in View CPE Device page");
		System.out.println("Customer Readonly SNMP  panel header is not displaying in View CPE Device page");
	}

	sa.assertAll();
}




	//PI Ranges Panel
public void addPIRangesFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,
	String PIAddressRange) throws InterruptedException, DocumentException, IOException {

ScrolltoElement(application, "CustomerReadonlySNMP_panelHeader", xml);//CustomerReadonlySNMP_panelHeader//CustomerReadonlyNMP_panelHeader//Routes_Header

if(getwebelement(xml.getlocator("//locators/" + application + "/PIRangesPanel_Header")).isDisplayed()){
	click_commonMethod(application, "Action", "PIRanges_Action", xml);
	click_commonMethod(application, "Add Link", "PIRanges_AddLink", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/AddPIRanges_Header")).isDisplayed()){
		
		compareText(application, "Add PI Range", "AddPIRanges_Header", "PI Ranges", xml);
//*		click_commonMethod(application, "OK Button", "PIRanges_OKButton", xml);	//Issue Reported in ALM
//*		warningMessage_commonMethod(application, "PIAddressRange_WarningMessage", "PI Address Range", xml);

	addtextFields_commonMethod(application, "PI Address Range", "PIAddressRangeTextfield", PIAddressRange, xml);
	
	click_commonMethod(application, "OK Button", "PIRanges_OKButton", xml);
	compareText(application, "Add PI Range Success Message", "AddPIRangesSuccessMessage", "PI Range successfully inserted.", xml);
	
}else{
	ExtentTestManager.getTest().log(LogStatus.FAIL, "Add PI Ranges  panel header is not displaying");
	System.out.println("Add PI Ranges  panel header is not displaying");
}

sa.assertAll();
}

}






public void deletePIRangesFunction_CPE(String application, String ServiceIdentification, String PE_DeviceName,String CustomerIPAddress

	) throws InterruptedException, DocumentException, IOException {

	ScrolltoElement(application, "CustomerReadonlySNMP_panelHeader", xml);//CustomerReadonlySNMP_panelHeader//CustomerReadonlyNMP_panelHeader//Routes_Header

if(getwebelement(xml.getlocator("//locators/" + application + "/PIRangesPanel_Header")).isDisplayed()){

	click_commonMethod(application, "Select PI Ranges Checkbox", "PIRangesCheckbox", xml);
	click_commonMethod(application, "Action", "PIRanges_Action", xml);
	click_commonMethod(application, "Delete Link", "PIRanges_DeleteLink", xml);

	if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
	{
		click_commonMethod(application, "Delete", "PIRanges_DeleteButton", xml);
	}else{
		Log.info("Delete alert popup is not displayed");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed for SNMP");
	}
	scrollToTop();
	compareText(application, "Delete PI Range Success Message", "DeletePIRangesSuccessMessage", "PI Range successfully deleted.", xml);
	
}else{
	ExtentTestManager.getTest().log(LogStatus.FAIL, "PI Range panel header is not displaying in View CPE Device page");
	System.out.println("PI Range panel header is not displaying in View CPE Device page");
}

sa.assertAll();
}




		//Extra Subnet Panel
public void addExtraSubnetFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,
	String ExtraSubnets_City, String ExtraSubnets_SubnetSize) throws InterruptedException, DocumentException, IOException {

	ScrolltoElement(application, "PIRangesPanel_Header", xml);//CustomerReadonlySNMP_panelHeader//CustomerReadonlyNMP_panelHeader//Routes_Header

	if(getwebelement(xml.getlocator("//locators/" + application + "/ExtraSubnets_PanelHeader")).isDisplayed()){
		click_commonMethod(application, "Action", "ExtraSubnets_Action", xml);
		click_commonMethod(application, "Allocate Extra Subnets Link", "AllocateExtraSubnets_Link", xml);
	
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddExtraSubnets_Header")).isDisplayed()){
			compareText(application, "Subnet Type Value", "ExtraSubnets_SubnetTypeValue", "CUST", xml);
			click_commonMethod(application, "Allocate Subnet Button", "ExtraSubnets_AllocateSubnetButon", xml);
			
			warningMessage_commonMethod(application, "ExtraSubnets_City_WarningMessage", "City ", xml);
			warningMessage_commonMethod(application, "ExtraSubnets_SubnetSize_WarningMessage", "Subnet Size  ", xml);
			warningMessage_commonMethod(application, "ExtraSubnets_EIPAllocation_AvilablePoolWarningMessage", "Avilable Pool ", xml);
			
			SelectDropdownValueUnderSpanTag(application, "City dropdown", ExtraSubnets_City, "ExtraSubnets_CityDropdown", "commonDropdownValueSpanTag", xml);
			SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", ExtraSubnets_SubnetSize, "ExtraSubnets_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);

			System.out.println("City : "+ExtraSubnets_City);
			System.out.println("Subnet Size : "+ExtraSubnets_SubnetSize);
			System.out.println("Service ID : "+ServiceIdentification);
			System.out.println("CPE Device : "+CPE_DeviceName);
			
			Thread.sleep(4000);
			GetText(application, "Available Pool Message", "ExtraSubnets_AvailablePoolsMessage");
			compareText(application, "Allocate Subnet Button", "ExtraSubnets_AllocateSubnetButon", "Allocate Subnet", xml);
			click_commonMethod(application, "Close Extra Subnets window", "ExtraSubnets_CloseButton", xml);
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Add Extra Subnets popup is not displaying");
	}

	
}else{
	ExtentTestManager.getTest().log(LogStatus.FAIL, "Extra Subnets  panel header is not displaying In view CPE Device page");
	System.out.println("Extra Subnets  panel header is not displaying In view CPE Device page");
}

sa.assertAll();
}




		//NAT Configuration
	public void editNATConfigurationFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,
		String StaticNATEdit, String DynamicNATEdit) throws InterruptedException, DocumentException, IOException {

	ScrolltoElement(application, "ExtraSubnets_PanelHeader", xml);//ExtraSubnets_PanelHeader
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/NATConfiguration_panelHeader")).isDisplayed()){
		click_commonMethod(application, "Action", "NAT_Actionbutton", xml);
		click_commonMethod(application, "Edit Link", "NAT_EditLink", xml);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/EditNAT_Header")).isDisplayed()){
			compareText(application, "Edit NAT Configuration Header", "EditNAT_Header", "NAT Configuration", xml);

			
			// Static NAT Checkbox
			if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNAT_Checkbox")).isEnabled()){
				if (StaticNATEdit.equalsIgnoreCase("YES")) {
					click(application, "'Static NAT' checkbox", "StaticNAT_Checkbox");
					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Static NAT' checkbox is Enabled and 'Static NAT' checkbox Selected");
					System.out.println("Step : 'Static NAT' checkbox is Enabled and 'Static NAT' checkbox Selected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Static NAT' checkbox is not Selected");
					System.out.println("Step : 'Static NAT' checkbox is not Selected");
				}
				}else {
					ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Static NAT' checkbox is Disabled, So can't make any changes in Checkbox status");
					System.out.println("Step : 'Static NAT' checkbox is Disabled, So can't make any changes in Checkbox status");
				}
			
				// Dynamic NAT Checkbox
					if(getwebelement(xml.getlocator("//locators/" + application + "/DynamicNAT_Checkbox")).isEnabled()){
						if (DynamicNATEdit.equalsIgnoreCase("YES")) {
							click(application, "'Dynamic NAT' checkbox", "DynamicNAT_Checkbox");
							ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dynamic NAT' checkbox is Enabled and 'Dynamic NAT' checkbox Selected");
							System.out.println("Step : 'Dynamic NAT' checkbox is Enabled and 'Dynamic NAT' checkbox Selected");
						} else {
							ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dynamic NAT' checkbox is not Selected");
							System.out.println("Step : 'Dynamic NAT' checkbox is not Selected");
						}
					
						}else {
							ExtentTestManager.getTest().log(LogStatus.INFO, "Step : 'Dynamic NAT' checkbox is Disabled, So can't make any changes in Checkbox status");
							System.out.println("Step : 'Dynamic NAT' checkbox is Disabled, So can't make any changes in Checkbox status");
						}
		
					click_commonMethod(application, "OK Button", "NAT_OKButton", xml);
					scrollToTop();
					compareText(application, "Update NAT Success Message", "UpdateNATSuccessMessage", "Nat Configuration successfully updated.", xml);
					
					
					}	
	
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "NAT Configuration  panel header is not displaying");
		System.out.println("NAT Configuration  panel header is not displaying");
	}

	sa.assertAll();
}



	//Add Static NAT Mapping

public void addStaticNATMappingFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String Static_Protocol, 
	String Static_LocalPort, String	Static_GlobalPort,String	Static_LocalIP, String	Static_GlobalIP) throws InterruptedException, DocumentException, IOException {

ScrolltoElement(application, "NATConfiguration_panelHeader", xml);//NATConfiguration_panelHeader//

if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNATMapping_panelHeader")).isDisplayed()){
	click_commonMethod(application, "Action", "StaticNAT_Actionbutton", xml);
	click_commonMethod(application, "Add Link", "StaticNAT_AddLink", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/AddStaticNAT_Header")).isDisplayed()){
		addDropdownValues_commonMethod(application, "Protocol", "StaticNAT_Protocol_Dropdown", Static_Protocol, xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common", xml);
			
		warningMessage_commonMethod(application, "StaticNAT_LocalPort_WarningMessage", "Local Port ", xml);
		warningMessage_commonMethod(application, "StaticNAT_GlobalPort_WarningMessage", "Global Port ", xml);
		warningMessage_commonMethod(application, "StaticNAT_LocalIP_WarningMessage", "Local IP ", xml);
		warningMessage_commonMethod(application, "StaticNAT_GlobalIP_WarningMessage", "Global IP ", xml);
		
		addtextFields_commonMethod(application, "LOCAL PORT textfield", "StaticNAT_LocalPort_Textfield", Static_LocalPort, xml);
		addtextFields_commonMethod(application, "GLOBAL PORT textfield", "StaticNAT_GlobalPort_Textfield", Static_GlobalPort, xml);
		addtextFields_commonMethod(application, "LOCAL IP textfield", "StaticNAT_LocalIP_Textfield", Static_LocalIP, xml);
		addtextFields_commonMethod(application, "GLOBAL IP textfield", "StaticNAT_GlobalIP_Textfield", Static_GlobalIP, xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common", xml);
		compareText(application, "Add Static NAT Mapping Success Message", "AddStaticNATSuccessMessage", "Static NAT successfully inserted.", xml);

		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Add Static NAT Mapping Header/popup not displayed");
		}
	
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Static NAT Mapping Panel not displayed");
			}

sa.assertAll();
}



		//Edit Static NAT Mapping
	public void editStaticNATMappingFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String Static_ProtocolEdit, 
			String Static_LocalPortEdit, String	Static_GlobalPortEdit,String	Static_LocalIPEdit, String	Static_GlobalIPEdit) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "NATConfiguration_panelHeader", xml);//NATConfiguration_panelHeader//

		if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNATMapping_panelHeader")).isDisplayed()){
			click_commonMethod(application, "Select Static NAT Checkbox", "SelectStaticNATCheckbox", xml);
			click_commonMethod(application, "Action", "StaticNAT_Actionbutton", xml);
			click_commonMethod(application, "Edit Link", "StaticNAT_Editink", xml);

			if(getwebelement(xml.getlocator("//locators/" + application + "/EditStaticNAT_Header")).isDisplayed()){
	addDropdownValues_commonMethod(application, "Protocol", "StaticNAT_Protocol_Dropdown", Static_ProtocolEdit, xml);
	
	edittextFields_commonMethod(application, "LOCAL PORT textfield", "StaticNAT_LocalPort_Textfield", Static_LocalPortEdit, xml);
	edittextFields_commonMethod(application, "GLOBAL PORT textfield", "StaticNAT_GlobalPort_Textfield", Static_GlobalPortEdit, xml);
	edittextFields_commonMethod(application, "LOCAL IP textfield", "StaticNAT_LocalIP_Textfield", Static_LocalIPEdit, xml);
	edittextFields_commonMethod(application, "GLOBAL IP textfield", "StaticNAT_GlobalIP_Textfield", Static_GlobalIPEdit, xml);
	
	click_commonMethod(application, "OK Button", "OKButton_common", xml);
	compareText(application, "Edit Static NAT Mapping Success Message", "UpdateStaticNATSuccessMessage", "Static NAT successfully updated.", xml);

	}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Static NAT Mapping Header/popup not displayed");
	}

	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Static NAT Mapping Panel not displayed");
		}

		sa.assertAll();
}


			//Delete Static NAT Mapping
	public void deleteStaticNATMappingFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String Static_ProtocolEdit, 
			String Static_LocalPortEdit, String	Static_GlobalPortEdit,String	Static_LocalIPEdit, String	Static_GlobalIPEdit) throws InterruptedException, DocumentException, IOException {

		
		ScrolltoElement(application, "NATConfiguration_panelHeader", xml);//NATConfiguration_panelHeader
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNATMapping_panelHeader")).isDisplayed()){
			click(application, "Statics Nat Checkbox", "SelectStaticNATCheckbox");
			click_commonMethod(application, "Action", "StaticNAT_Actionbutton", xml);
			click_commonMethod(application, "Delete Link", "StaticNAT_DeleteLink", xml);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click_commonMethod(application, "Delete", "DeleteButton_common", xml);
			}else{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			scrollToTop();
			compareText(application, "Delete Static NAT Mapping Success Message", "DeleteStaticNATSuccessMessage", "Static NAT successfully deleted.", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Static NAT Mapping panel header not displayed");
		}

	}





	//Add Dynamic NAT Mapping
public void addDynamicNATMappingFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String Dynamic_PoolMode, 
	String Dynamic_InterfaceMode, String Dynamic_LocalNetwork,String Dynamic_PoolStartAddress, String Dynamic_PoolEndAddress,
	String Dynamic_PoolPrefix, String Dynamic_MapsToInterface) throws InterruptedException, DocumentException, IOException {


	
ScrolltoElement(application, "StaticNATMapping_panelHeader", xml);//NATConfiguration_panelHeader//

if(getwebelement(xml.getlocator("//locators/" + application + "/DynamicNATMapping_panelHeader")).isDisplayed()){
	click_commonMethod(application, "Action", "DynamicNAT_Actionbutton", xml);
	click_commonMethod(application, "Add Link", "DynamicNAT_AddLink", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/AddDynamicNAT_Header")).isDisplayed()){
		
	 if(Dynamic_PoolMode.equalsIgnoreCase("Yes") && Dynamic_InterfaceMode.equalsIgnoreCase("No")) {
		try {
		click_commonMethod(application, "Pool Mode", "DynamicNAT_PoolModeCheckbox", xml);
		click_commonMethod(application, "OK Button", "OKButton_common", xml);
			
		warningMessage_commonMethod(application, "DynamicNAT_PoolLocalNetworkWarningMessage", "Local Network ", xml);
		warningMessage_commonMethod(application, "DynamicNAT_PoolStartAddressWarningMessage", "Pool Start Address ", xml);
		warningMessage_commonMethod(application, "DynamicNAT_PoolEndAddressWarningMessage", "Pool End Address ", xml);
		warningMessage_commonMethod(application, "DynamicNAT_PoolPrefixWarningMessage", "Pool Prefix ", xml);
		
		addtextFields_commonMethod(application, "Local Network textfield", "DynamicNAT_PoolLocalNetworkTextfield", Dynamic_LocalNetwork, xml);
		addtextFields_commonMethod(application, "Pool Start Address textfield", "DynamicNAT_PoolStartAddressTextfield", Dynamic_PoolStartAddress, xml);
		addtextFields_commonMethod(application, "Pool End Address textfield", "DynamicNAT_PoolEndAddressTextfield", Dynamic_PoolEndAddress, xml);
		SelectDropdownValueUnderSelectTag(application, "Pool Prefix", Dynamic_PoolPrefix, "DynamicNAT_PoolPrefixDropdown", xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common", xml);
		compareText(application, "Add Dynamic NAT Mapping Success Message", "AddDynamicNATSuccessMessage", "Dynamic NAT successfully inserted.", xml);

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Dynamic NAT page");
			System.out.println(  e+ " : Field is not displayed in  Add Dynamic NAT page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add Dynamic NAT page ");
			System.out.println(  e+" : Field is not displayed in Add Dynamic NAT page");
		}
	 }
	 
	 if(Dynamic_PoolMode.equalsIgnoreCase("No") && Dynamic_InterfaceMode.equalsIgnoreCase("Yes")) {
		 try {
		 click_commonMethod(application, "Interface Mode", "DynamicNAT_InterfaceModeCheckbox", xml);
			click_commonMethod(application, "OK Button", "OKButton_common", xml);
				
			warningMessage_commonMethod(application, "DynamicNAT_PoolLocalNetworkWarningMessage", "Local Network ", xml);
			warningMessage_commonMethod(application, "DynamicNAT_MapToInterfaceWarningMessage", "Map To Interface ", xml);
			
			addtextFields_commonMethod(application, "Local Network textfield", "DynamicNAT_PoolLocalNetworkTextfield", Dynamic_LocalNetwork, xml);
			SelectDropdownValueUnderSelectTag(application, "Maps to Interface", Dynamic_MapsToInterface, "DynamicNAT_MapToInterfaceDropdown", xml);

			
			click_commonMethod(application, "OK Button", "OKButton_common", xml);
			compareText(application, "Add Dynamic NAT Mapping Success Message", "AddDynamicNATSuccessMessage", "Dynamic NAT successfully inserted.", xml);

			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Dynamic NAT page");
				System.out.println(  e+ " : Field is not displayed in  Add Dynamic NAT page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add Dynamic NAT page ");
				System.out.println(  e+" : Field is not displayed in Add Dynamic NAT page");
			}
	 }
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Add Dynamic NAT Mapping Header/popup not displayed");
		}
	
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Dynamic NAT Mapping Panel not displayed");
			}

sa.assertAll();
}




//Edit Dynamic NAT Mapping
	public void editDynamicNATMappingFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String EditDynamic_PoolMode, 
	String EditDynamic_InterfaceMode, String EditDynamic_LocalNetwork,String EditDynamic_PoolStartAddress, String EditDynamic_PoolEndAddress,
	String EditDynamic_PoolPrefix, String EditDynamic_MapsToInterface) throws InterruptedException, DocumentException, IOException {

						

	
ScrolltoElement(application, "StaticNATMapping_panelHeader", xml);//NATConfiguration_panelHeader//

if(getwebelement(xml.getlocator("//locators/" + application + "/DynamicNATMapping_panelHeader")).isDisplayed()){
	click_commonMethod(application, "Select Dynamic NAT Checkbox", "SelectDynamicNATCheckbox", xml);
	click_commonMethod(application, "Action", "DynamicNAT_Actionbutton", xml);
	click_commonMethod(application, "Edit Link", "DynamicNAT_Editink", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/EditDynamicNAT_Header")).isDisplayed()){
		
	 if(EditDynamic_PoolMode.equalsIgnoreCase("Yes") && EditDynamic_InterfaceMode.equalsIgnoreCase("No")) {
		try {
		click_commonMethod(application, "Pool Mode", "DynamicNAT_PoolModeCheckbox", xml);
		
		ClearAndEnterTextValue(application, "Local Network textfield", "DynamicNAT_PoolLocalNetworkTextfield", EditDynamic_LocalNetwork, xml);
		ClearAndEnterTextValue(application, "Pool Start Address textfield", "DynamicNAT_PoolStartAddressTextfield", EditDynamic_PoolStartAddress, xml);
		ClearAndEnterTextValue(application, "Pool End Address textfield", "DynamicNAT_PoolEndAddressTextfield", EditDynamic_PoolEndAddress, xml);
		SelectDropdownValueUnderSelectTag(application, "Pool Prefix", EditDynamic_PoolPrefix, "DynamicNAT_PoolPrefixDropdown", xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common", xml);
		compareText(application, "Add Dynamic NAT Mapping Success Message", "UpdateDynamicNATSuccessMessage", "Dynamic NAT successfully updated.", xml);

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Dynamic NAT page");
			System.out.println(  e+ " : Field is not displayed in  Add Dynamic NAT page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add Dynamic NAT page ");
			System.out.println(  e+" : Field is not displayed in Add Dynamic NAT page");
		}
	 }
	 
	 if(EditDynamic_PoolMode.equalsIgnoreCase("No") && EditDynamic_InterfaceMode.equalsIgnoreCase("Yes")) {
		 try {
		 click_commonMethod(application, "Interface Mode", "DynamicNAT_InterfaceModeCheckbox", xml);
//			click_commonMethod(application, "OK Button", "OKButton_common", xml);
				
//			warningMessage_commonMethod(application, "DynamicNAT_PoolLocalNetworkWarningMessage", "Local Network ", xml);
//			warningMessage_commonMethod(application, "DynamicNAT_MapToInterfaceWarningMessage", "Map To Interface ", xml);
			
			SelectDropdownValueUnderSelectTag(application, "Maps to Interface", EditDynamic_MapsToInterface, "DynamicNAT_MapToInterfaceDropdown", xml);
			
			click_commonMethod(application, "OK Button", "OKButton_common", xml);
			compareText(application, "Update Dynamic NAT Mapping Success Message", "UpdateDynamicNATSuccessMessage", "Dynamic NAT successfully updated.", xml);

			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Edit Dynamic NAT page");
				System.out.println(  e+ " : Field is not displayed in  Add Dynamic NAT page");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Edit Dynamic NAT page ");
				System.out.println(  e+" : Field is not displayed in Add Dynamic NAT page");
			}
	 }
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Dynamic NAT Mapping Header/popup not displayed");
		}
	
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Dynamic NAT Mapping Panel not displayed");
			}

sa.assertAll();
}




				//Delete Dynamic NAT Mapping
	public void deleteDynamicNATMappingFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "StaticNATMapping_panelHeader", xml);//NATConfiguration_panelHeader//

		if(getwebelement(xml.getlocator("//locators/" + application + "/DynamicNATMapping_panelHeader")).isDisplayed()){
			click(application, "Select Dynamic NAT Checkbox", "SelectDynamicNATCheckbox", xml);
			click_commonMethod(application, "Action", "DynamicNAT_Actionbutton", xml);
			click_commonMethod(application, "Delete Link", "DynamicNAT_DeleteLink", xml);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click_commonMethod(application, "Delete", "DeleteButton_common", xml);
			}else{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			scrollToTop();
			compareText(application, "Delete Dynamic NAT Mapping Success Message", "DeleteDynamicNATSuccessMessage", "Dynamic NAT successfully deleted.", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Dynamic NAT Mapping panel header not displayed");
		}

	}




	
	//Add DHCP Servers on CPE
public void addDHCPServersonCPEFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String DHCP_CustomerLANSubnet, 
	String	DHCP_SubnetMask,String	DHCP_PrimaryDNSServer, String	DHCP_SecondaryDNSServer) throws InterruptedException, DocumentException, IOException {

ScrolltoElement(application, "DynamicNATMapping_panelHeader", xml);//NATConfiguration_panelHeader//DynamicNATMapping_panelHeader

if(getwebelement(xml.getlocator("//locators/" + application + "/DHCP_panelHeader")).isDisplayed()){
	click_commonMethod(application, "Action", "DHCP_Actionbutton", xml);
	click_commonMethod(application, "Add Link", "DHCP_AddLink", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/AddDHCP_Header")).isDisplayed()){
		click_commonMethod(application, "OK Button", "OKButton_common", xml);
			
		warningMessage_commonMethod(application, "DHCP_CustomerLANSubnet_WarningMessage", "Customer LAN Subnet ", xml);
		warningMessage_commonMethod(application, "DHCP_SubnetMask_WarningMessage", "Subnet Mask ", xml);
		warningMessage_commonMethod(application, "DHCP_PrimaryDNSServer_WarningMessage", "Primary DNS Server ", xml);
		
		addtextFields_commonMethod(application, "Customer LAN Subnet textfield", "DHCP_CustomerLANSubnet_Textfield", DHCP_CustomerLANSubnet, xml);
		addtextFields_commonMethod(application, "Subnet Mask textfield", "DHCP_SubnetMask_Textfield", DHCP_SubnetMask, xml);
		addtextFields_commonMethod(application, "Primary DNS Server textfield", "DHCP_PrimaryDNSServer_Textfield", DHCP_PrimaryDNSServer, xml);
		addtextFields_commonMethod(application, "Secondary DNS Server textfield", "DHCP_SecondaryDNSServer_Textfield", DHCP_SecondaryDNSServer, xml);
		

		click_commonMethod(application, "OK Button", "OKButton_common", xml);
		compareText(application, "Add DHCP Servers on CPE Success Message", "AddDHCPSuccessMessage", "DHCP server successfully created.", xml);

		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Add DHCP Servers on CPE Header/popup not displayed");
		}
	
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DHCP Servers on CPE Panel not displayed");
			}

sa.assertAll();
}


		//Edit DHCP Servers on CPE
	public void editDHCPServersonCPEFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String DHCP_CustomerLANSubnetEdit, 
			String	DHCP_SubnetMaskEdit,String	DHCP_PrimaryDNSServerEdit, String	DHCP_SecondaryDNSServerEdit) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "DynamicNATMapping_panelHeader", xml);//NATConfiguration_panelHeader//DynamicNATMapping_panelHeader

		if(getwebelement(xml.getlocator("//locators/" + application + "/DHCP_panelHeader")).isDisplayed()){
			click_commonMethod(application, "Select DHCP Servers on CPE Checkbox", "SelectDHCPCheckbox", xml);
			click_commonMethod(application, "Action", "DHCP_Actionbutton", xml);
			click_commonMethod(application, "Edit Link", "DHCP_Editink", xml);

			if(getwebelement(xml.getlocator("//locators/" + application + "/EditDHCP_Header")).isDisplayed()){
	
	edittextFields_commonMethod(application, "Customer LAN Subnet textfield", "DHCP_CustomerLANSubnet_Textfield", DHCP_CustomerLANSubnetEdit, xml);
	edittextFields_commonMethod(application, "Subnet Mask textfield", "DHCP_SubnetMask_Textfield", DHCP_SubnetMaskEdit, xml);
	edittextFields_commonMethod(application, "Primary DNS Server textfield", "DHCP_PrimaryDNSServer_Textfield", DHCP_PrimaryDNSServerEdit, xml);
	edittextFields_commonMethod(application, "Secondary DNS Server textfield", "DHCP_SecondaryDNSServer_Textfield", DHCP_SecondaryDNSServerEdit, xml);
	
	
	click_commonMethod(application, "OK Button", "OKButton_common", xml);
	compareText(application, "Edit DHCP Servers on CPE Success Message", "UpdateDHCPSuccessMessage", "DHCP server successfully updated.", xml);

	}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit DHCP Servers on CPE Header/popup not displayed");
	}

	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "DHCP Servers on CPE Panel not displayed");
		}

		sa.assertAll();
}


			//Delete DHCP Servers on CPE
	public void deleteDHCPServersonCPEFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String DHCP_CustomerLANSubnetEdit, 
			String	DHCP_SubnetMaskEdit,String	DHCP_PrimaryDNSServerEdit, String	DHCP_SecondaryDNSServerEdit) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "DynamicNATMapping_panelHeader", xml);//NATConfiguration_panelHeader//DynamicNATMapping_panelHeader
	
		if(getwebelement(xml.getlocator("//locators/" + application + "/DHCP_panelHeader")).isDisplayed()){
			click_commonMethod(application, "Select DHCP Servers on CPE Checkbox", "SelectDHCPCheckbox", xml);
			click_commonMethod(application, "Action", "DHCP_Actionbutton", xml);
			click_commonMethod(application, "Delete Link", "DHCP_DeleteLink", xml);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click_commonMethod(application, "Delete", "DeleteButton_common", xml);
			}else{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			scrollToTop();
			compareText(application, "Delete DHCP Servers on CPE Success Message", "DeleteDHCPSuccessMessage", "DHCP server successfully deleted.", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DHCP Servers on CPE panel header not displayed");
		}

	}


	
	//Add DHCP IPV6 Servers on CPE
public void addDHCPIPV6ServersonCPEFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String DHCPIPV6_DHCPType, 
	String	DHCPIPV6_SubnetMask,String	DHCPIPV6_LANIPV6Subnet, String	DHCPIPV6_DomainName, String DHCPIPV6_PrimaryDNSServer, String DHCPIPV6_SecondaryDNSServer) throws InterruptedException, DocumentException, IOException {


ScrolltoElement(application, "DHCP_panelHeader", xml);

if(getwebelement(xml.getlocator("//locators/" + application + "/DHCPIPV6_panelHeader")).isDisplayed()){
	click_commonMethod(application, "Action", "DHCPIPV6_Actionbutton", xml);
	click_commonMethod(application, "Add Link", "DHCPIPV6_AddLink", xml);
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/AddDHCPIPV6_Header")).isDisplayed()){
		if(DHCPIPV6_DHCPType.contains("Stateless DHCP")){
		click_commonMethod(application, "OK Button", "OKButton_common", xml);
			
		warningMessage_commonMethod(application, "DHCPIPV6_PrimaryDNSServer_WarningMessage", "Primary DNS Server ", xml);
		
		click_commonMethod(application, "DHCP Type", "DHCPIPV6_IPV6DHCPType_Dropdown", xml);
		WebElement SelectDHCPIPV6_DHCPTypeDropdownValue=getwebelement("//div[text()='"+ DHCPIPV6_DHCPType +"']");
		Clickon(SelectDHCPIPV6_DHCPTypeDropdownValue);

		addtextFields_commonMethod(application, "Domatiin Name textfield", "DHCPIPV6_DomainName_Textfield", DHCPIPV6_DomainName, xml);
		addtextFields_commonMethod(application, "IPV6 Primary DNS Server textfield", "DHCPIPV6_PrimaryDNSServer_Textfield", DHCPIPV6_PrimaryDNSServer, xml);
		addtextFields_commonMethod(application, "IPV6 Secondary DNS Server textfield", "DHCPIPV6_SecondaryDNSServer_Textfield", DHCPIPV6_SecondaryDNSServer, xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common", xml);
		compareText(application, "Add DHCP IPV6 Servers on CPE Success Message", "AddDHCPIPV6SuccessMessage", "DHCP server successfully created.", xml);
		}
		


if(DHCPIPV6_DHCPType.contains("Statefull DHCP")){
	
	click_commonMethod(application, "DHCP Type", "DHCPIPV6_IPV6DHCPType_Dropdown", xml);
	WebElement SelectDHCPIPV6_DHCPTypeDropdownValue=getwebelement("//div[text()='"+ DHCPIPV6_DHCPType +"']");
	Clickon(SelectDHCPIPV6_DHCPTypeDropdownValue);
	
	click_commonMethod(application, "OK Button", "OKButton_common", xml);
		
	warningMessage_commonMethod(application, "DHCPIPV6_SubnetMask_WarningMessage", "Subnet Mask ", xml);
	warningMessage_commonMethod(application, "DHCPIPV6_LANIPV6Subnet_WarningMessage", "LAN IPV6 Subnet ", xml);
	warningMessage_commonMethod(application, "DHCPIPV6_PrimaryDNSServer_WarningMessage", "Primary DNS Server ", xml);
	
	addtextFields_commonMethod(application, "IPV6 Subnet Mask textfield", "DHCPIPV6_SubnetMask_Textfield", DHCPIPV6_SubnetMask, xml);
	addtextFields_commonMethod(application, "LAN IPV6 Subnet textfield", "DHCPIPV6_LANIPV6Subnet_Textfield", DHCPIPV6_LANIPV6Subnet, xml);
	addtextFields_commonMethod(application, "Domatiin Name textfield", "DHCPIPV6_DomainName_Textfield", DHCPIPV6_DomainName, xml);
	addtextFields_commonMethod(application, "IPV6 Primary DNS Server textfield", "DHCPIPV6_PrimaryDNSServer_Textfield", DHCPIPV6_PrimaryDNSServer, xml);
	addtextFields_commonMethod(application, "IPV6 Secondary DNS Server textfield", "DHCPIPV6_SecondaryDNSServer_Textfield", DHCPIPV6_SecondaryDNSServer, xml);
	
	click_commonMethod(application, "OK Button", "OKButton_common", xml);
	compareText(application, "Add DHCP IPV6 Servers on CPE Success Message", "AddDHCPIPV6SuccessMessage", "DHCP server successfully created.", xml);
	}
	
	}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "Add DHCP IPV6 Servers on CPE Header/popup not displayed");
	}

	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "DHCP IPV6 Servers on CPE Panel not displayed");
		}

	sa.assertAll();
}


		
	//Edit DHCP IPV6 Servers on CPE
		public void editDHCPIPV6ServersonCPEFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String DHCPIPV6_DHCPType, 
					String	DHCPIPV6_SubnetMask,String	DHCPIPV6_LANIPV6Subnet, String	DHCPIPV6_DomainName, String DHCPIPV6_PrimaryDNSServer, String DHCPIPV6_SecondaryDNSServer
					) throws InterruptedException, DocumentException, IOException {


				ScrolltoElement(application, "DHCP_panelHeader", xml);

				if(getwebelement(xml.getlocator("//locators/" + application + "/DHCPIPV6_panelHeader")).isDisplayed()){
					click_commonMethod(application, "Select DHCP IPV6 Checkbox", "SelectDHCPIPV6Checkbox", xml);
					click_commonMethod(application, "Action", "DHCPIPV6_Actionbutton", xml);
					click_commonMethod(application, "Edit Link", "DHCPIPV6_Editink", xml);
					
					if(getwebelement(xml.getlocator("//locators/" + application + "/EditDHCPIPV6_Header")).isDisplayed()){
						if(DHCPIPV6_DHCPType.contains("Stateless DHCP")){
						
						click_commonMethod(application, "DHCP Type", "DHCPIPV6_IPV6DHCPType_Dropdown", xml);
						WebElement SelectDHCPIPV6_DHCPTypeDropdownValue=getwebelement("//div[text()='"+ DHCPIPV6_DHCPType +"']");
						Clickon(SelectDHCPIPV6_DHCPTypeDropdownValue);

						ClearAndEnterTextValue(application, "Domatiin Name textfield", "DHCPIPV6_DomainName_Textfield", DHCPIPV6_DomainName, xml);
						ClearAndEnterTextValue(application, "IPV6 Primary DNS Server textfield", "DHCPIPV6_PrimaryDNSServer_Textfield", DHCPIPV6_PrimaryDNSServer, xml);
						ClearAndEnterTextValue(application, "IPV6 Secondary DNS Server textfield", "DHCPIPV6_SecondaryDNSServer_Textfield", DHCPIPV6_SecondaryDNSServer, xml);
						
						click_commonMethod(application, "OK Button", "OKButton_common", xml);
						compareText(application, "Update DHCP IPV6 Servers on CPE Success Message", "UpdateDHCPIPV6SuccessMessage", "DHCP server successfully updated.", xml);
						}
						


				if(DHCPIPV6_DHCPType.contains("Statefull DHCP")){
					
					click_commonMethod(application, "DHCP Type", "DHCPIPV6_IPV6DHCPType_Dropdown", xml);
					WebElement SelectDHCPIPV6_DHCPTypeDropdownValue=getwebelement("//div[text()='"+ DHCPIPV6_DHCPType +"']");
					Clickon(SelectDHCPIPV6_DHCPTypeDropdownValue);
					
					ClearAndEnterTextValue(application, "IPV6 Subnet Mask textfield", "DHCPIPV6_SubnetMask_Textfield", DHCPIPV6_SubnetMask, xml);
					ClearAndEnterTextValue(application, "LAN IPV6 Subnet textfield", "DHCPIPV6_LANIPV6Subnet_Textfield", DHCPIPV6_LANIPV6Subnet, xml);
					ClearAndEnterTextValue(application, "Domatiin Name textfield", "DHCPIPV6_DomainName_Textfield", DHCPIPV6_DomainName, xml);
					ClearAndEnterTextValue(application, "IPV6 Primary DNS Server textfield", "DHCPIPV6_PrimaryDNSServer_Textfield", DHCPIPV6_PrimaryDNSServer, xml);
					ClearAndEnterTextValue(application, "IPV6 Secondary DNS Server textfield", "DHCPIPV6_SecondaryDNSServer_Textfield", DHCPIPV6_SecondaryDNSServer, xml);
					
					click_commonMethod(application, "OK Button", "OKButton_common", xml);
					compareText(application, "Update DHCP IPV6 Servers on CPE Success Message", "UpdateDHCPIPV6SuccessMessage", "DHCP server successfully updated.", xml);
					}
					
					}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit DHCP IPV6 Servers on CPE Header/popup not displayed");
					}

					}else{
						ExtentTestManager.getTest().log(LogStatus.FAIL, "DHCP IPV6 Servers on CPE Panel not displayed");
						}

				sa.assertAll();
				}


		//Delete DHCP IPV6 Servers on CPE
	public void deleteDHCPIPV6ServersonCPEFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String DHCPIPV6_DHCPType, 
			String	DHCPIPV6_SubnetMask,String	DHCPIPV6_LANIPV6Subnet, String	DHCPIPV6_DomainName, String DHCPIPV6_PrimaryDNSServer, String DHCPIPV6_SecondaryDNSServer
			) throws InterruptedException, DocumentException, IOException {
		ScrolltoElement(application, "DHCP_panelHeader", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/DHCPIPV6_panelHeader")).isDisplayed()){
			click_commonMethod(application, "Select DHCP IPV6 Checkbox", "SelectDHCPIPV6Checkbox", xml);
			click_commonMethod(application, "Action", "DHCPIPV6_Actionbutton", xml);
			click_commonMethod(application, "Delete Link", "DHCPIPV6_DeleteLink", xml);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click_commonMethod(application, "Delete", "DeleteButton_common", xml);
			}else{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			scrollToTop();
			compareText(application, "Delete DHCP IPV6 Servers on CPE Success Message", "DeleteDHCPIPV6SuccessMessage", "DHCP server successfully deleted.", xml);
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DHCP IPV6 Servers on CPE panel header not displayed");
		}
	}


	
	
	public void VerifyConfigurationPanel_CPE(String application, String DCPE_DeviceName, String ServiceIdentification,String Configuration 
		) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "DHCPIPV6_panelHeader", xml);//DHCP_panelHeader for Colt

		if(getwebelement(xml.getlocator("//locators/" + application + "/Configuration_panelHeader")).isDisplayed()){
			isDisplayed(application, "ConfigurationTextfield", "Configuration");
			
			}else{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Configuration Panel not displayed");
				}

			sa.assertAll();
		}



	public void VerifyConfigurationPanel_DS(String application,String Configuration 
			) throws InterruptedException, DocumentException, IOException {

		scrolltoend();
			if(getwebelement(xml.getlocator("//locators/" + application + "/Configuration_panelHeader")).isDisplayed()){
				isDisplayed(application, "ConfigurationTextfield", "Configuration");
				
				}else{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Configuration Panel not displayed");
					}

				sa.assertAll();
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public void verifyAddDSLAMandHSLlink(String application, String DSLMdevice) throws InterruptedException, DocumentException {
		
		boolean actelisConfigurationPanel=false;
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add DSLAM and HSL'");
		
		waitForpageload();
		waitforPagetobeenable();
		
		scrolltoend();
		ScrolltoElement(application, "ConfigurationOptions_Header", xml);
		
		actelisConfigurationPanel=getwebelement(xml.getlocator("//locators/" + application + "/ActelisConfigurationPanel")).isDisplayed();
		
		if(actelisConfigurationPanel) {
			System.out.println(" 'Actelis Configuration' panel is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Configuration' panel is displaying as expected");
			
			boolean actelisLink=false;
		try {	
			actelisLink=getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM")).isDisplayed();
			if(actelisLink) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add DSLAM and HSL' link is displaying as expected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM")));
				Thread.sleep(3000);
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " ''Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel ");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to click on 'Add DSLAM and HSL' link");
		}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Actelis Configuration' panel is not displaying");
		}
		
}


 public void AddDSLAMandHSL(String application, String DSLMdevice, String HSlname) throws InterruptedException, DocumentException, IOException {
		 waitForpageload();
		 waitforPagetobeenable();
		 
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addDSLAMandHSL_xButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'X' button under 'DSLAM device' dropdown");
			Log.info("Clicked on 'X' button under 'DSLAM device' dropdown");

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/DSLM_Device_Select")),DSLMdevice);
			ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is entered under 'DSLAM device' dropdown");
			
			WebElement valueToSElect=getwebelement(xml.getlocator("//locators/" + application + "/selectDSLAMdeviceValue").replace("value", DSLMdevice));
			
			try {
				if(valueToSElect.isDisplayed()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is displaying under 'DSLAM device' dropdown");
					Log.info(DSLMdevice + " is displaying under 'DSLAM device' dropdown");
					
					Clickon(valueToSElect);
					ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is selected under 'DSLAM device' dropdown");
					Log.info(DSLMdevice + " is selected under 'DSLAM device' dropdown");
					
					
					waitForpageload();
					waitforPagetobeenable();
					
					click_commonMethod(application, "List_HSL", "List_HSL_Link", xml);		//click on "List HSL" button

					selectRowForAddingInterface_Actelis(application, HSlname);		// select the Interface
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
					Log.info(DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
				}
				
			}catch(Exception e){
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
				Log.info(DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
			}
			
			
	 }




 public void selectRowForAddingInterface_Actelis(String Application, String interfacenumber)
				throws IOException, InterruptedException, DocumentException {
scrolltoend();
Thread.sleep(3000);

		 System.out.println("check second time");
			int TotalPages;

			String TextKeyword = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToSelect_actelis_totalpage")));

			TotalPages = Integer.parseInt(TextKeyword);

			System.out.println("Total number of pages in table is: " + TotalPages);

			ab:

			if (TotalPages != 0) {
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToSelect_actelis_currentpage")));
					int Current_page = Integer.parseInt(CurrentPage);


					System.out.println("Currently we are in page number: " + Current_page);

					List<WebElement> results = getwebelements("//div[contains(text(),'"+ interfacenumber +"')]");
					
					int numofrows = results.size();
					System.out.println("no of results: " + numofrows);
					boolean resultflag;

					if (numofrows == 0) {

						Clickon(getwebelement("//button[text()='Next']"));
						Thread.sleep(3000);

					}

					else {

						for (int i = 0; i < numofrows; i++) {

							try {

								resultflag = results.get(i).isDisplayed();
								System.out.println("status of result: " + resultflag);
								if (resultflag) {
									System.out.println(results.get(i).getText());
									results.get(i).click();
									ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is selected under 'Add DSLAM and Device' page");
									
									Clickon(getwebelement("//span[text()='Next']"));
									Thread.sleep(3000);
								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = driver.findElements(By.xpath("//div[contains(text(),'"+ interfacenumber +"')]"));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								ExtentTestManager.getTest().log(LogStatus.FAIL, "failure while selecting interface to add with service");
							}
						}
						break ab;
					}
				}
			} else {

				System.out.println("No values available in table");
				Log.info("No Interfaces got fetched");
				ExtentTestManager.getTest().log(LogStatus.INFO, " NO interfaces got fetched");
			}

		}
 

 
 public void showInterface_ActelisConfiguuration(String application) throws InterruptedException, DocumentException {
	 
	 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/showInterface_ActelisCnfiguration")));
	 Thread.sleep(3000);
	 
 } 
 
 
	  
	 	 public void deletInterface_ActelisConfiguration(String application, String interfaceName) throws InterruptedException, DocumentException {
	 		 
	 		//select the interface
	 		 Clickon(getwebelement("//div[text()='"+ interfaceName +"']"));
	 		 
	 		 //click on Action button
	 		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AcionButton_ActelisConfiguration")));
	 		 
	 		 //Remove Button
	 		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/removeButton_ActelisConfiguration")));
	 		 
	 		 boolean popupMessage=false;
	 		 popupMessage=getwebelement(xml.getlocator("//locators/" + application + "/popupMessage_forRemove_ActelisConfiguration")).isDisplayed();
	 		 
	 		 if(popupMessage) {
	 			 String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/popupMessage_forRemove_ActelisConfiguration")).getText();
	 			 ExtentTestManager.getTest().log(LogStatus.PASS, " On clicking remoe button, popup message displays as: "+ actualmsg);
	 			 System.out.println(" On clicking remoe button, popup message displays as: "+ actualmsg);
	 			 
	 				 Clickon(getwebelement("//button[@class='btn btn-danger']"));
	 				 Thread.sleep(3000);
	 		 }else {
	 			 
	 			 ExtentTestManager.getTest().log(LogStatus.FAIL, " popup message does not display after clicking on 'Remove' button");
	 		 }
	 	 } 
	  

 
 public void successMessage_deleteInterfaceFromDevice_ActelisConfiguration(String application) throws InterruptedException, DocumentException {
		
		boolean successMessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface")).isDisplayed();
		String actualmessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface")).getText();
		if(successMessage) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for removing Interface is dipslaying as expected");
			System.out.println( " Success Message for removing interface is dipslaying as expected");
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Message displays as: "+actualmessage);
			System.out.println("Message displays as: "+actualmessage);
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for removing Interface is not dipslaying");
			System.out.println( " Success Message for removing Interface is not dipslaying");
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
						/////////////////////////////////////////

















///////////////////////////

public void routerPanel_PE(String application, String commandIPv4, String commandIPv6, 
String vrfname_ipv4, String vrfname_ipv6) throws InterruptedException, DocumentException, IOException {
	
ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Router Tool Functionality for Distribution Switch");
ScrolltoElement(application, "RouterTool_header", xml);
Thread.sleep(1000);

WebElement vendorModel=getwebelement(xml.getlocator("//locators/" + application + "/CPE_View_VendorModelValue"));
String vendor=Gettext(vendorModel);
WebElement manageAddress=getwebelement(xml.getlocator("//locators/" + application + "/CPE_View_ManagementAddressValue"));
String ipAddress=Gettext(manageAddress);

ScrolltoElement(application, "CPE_View_VendorModelValue" , xml);
Thread.sleep(1000);

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



//Add/Edit/Delete Routes
public void addRouteFunction_PE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCity, 
String PE_RouteSubnetSize, String	PE_Gateway,String	PE_NetworkAddress, String	PE_NetworkMAS, String	PE_Metrics
) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Route Functionality");
try {
	ScrolltoElement(application, "PE_CPE_Router_IPV4Command_Executebutton", xml);//interfaces_header//RouterTool_header//PE_CPE_Router_IPV4Command_Executebutton

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
click_commonMethod(application, "Add Link", "Routes_AddLink", xml);
compareText(application, "Add Route header", "Routes_AddRoutes_Header", "Add Route", xml);
click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);
Thread.sleep(4000);

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
	
	compareText(application, "Subnet Type Value", "CPE_Routes_EIPAllocation_SubnetTypeValue", "XFER", xml);
	click_commonMethod(application, "Allocate Subnet Button", "Routes_AllocateSubnetButon", xml);
	
	warningMessage_commonMethod(application, "Routes_EIPAllocation_CityWarningMessage", "City  warning message", xml);
	warningMessage_commonMethod(application, "Routes_EIPAllocation_SubnetSizeWarningMessage", "Subnet Size  warning message", xml);
	warningMessage_commonMethod(application, "Routes_EIPAllocation_AvilablePoolWarningMessage", "Avilable Pool  warning message", xml);
	
	SelectDropdownValueUnderSpanTag(application, "City dropdown", PE_RouteCity, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueSpanTag", xml);
	SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", PE_RouteSubnetSize, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);
	
	Thread.sleep(4000);
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
compareText(application, "Add Route Success Message", "AddRouteSuccessMessage", "Static Route successfully created.", xml);

}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
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





public void editRouteFunction_PE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
String PE_RouteSubnetSizeEdit, String	PE_GatewayEdit,String	PE_NetworkAddressEdit, String	PE_NetworkMASEdit, String	PE_MetricsEdit
) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edit Route Functionality");
	
	try {
	ScrolltoElement(application, "PE_CPE_Router_IPV4Command_Executebutton", xml);//interfaces_header//RouterTool_header//Routes_Header//PE_CPE_Router_IPV4Command_Executebutton

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
click_commonMethod(application, "Edit Link", "Routes_EditLink", xml);
compareText(application, "Edit Route header", "Routes_EditRoutes_Header", "Edit Route", xml);
click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);
Thread.sleep(4000);

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
	
	compareText(application, "Subnet Type Value", "CPE_Routes_EIPAllocation_SubnetTypeValue", "XFER", xml);
	SelectDropdownValueUnderSpanTag(application, "City dropdown", PE_RouteCityEdit, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueSpanTag", xml);
	SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", PE_RouteSubnetSizeEdit, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);
	
	Thread.sleep(2000);
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
compareText(application, "Add Route Success Message", "UpdateRouteSuccessMessage", "Static Route successfully updated.", xml);


}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
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








public void deleteRouteFunction_PE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
String PE_RouteSubnetSizeEdit, String	PE_GatewayEdit,String	PE_NetworkAddressEdit, String	PE_NetworkMASEdit, String	PE_MetricsEdit
) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Delete Route Functionality");
	
	try {
	ScrolltoElement(application, "PE_CPE_Router_IPV4Command_Executebutton", xml);//interfaces_header//RouterTool_header//PE_CPE_Router_IPV4Command_Executebutton

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
	Thread.sleep(2000);
click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
click_commonMethod(application, "Delete Link", "Routes_DeleteLink", xml);

Thread.sleep(2000);
Alert alert = driver.switchTo().alert();



GetMessageFromAlertJavaScriptPopup();

try {  
 AcceptJavaScriptMethod();//CancelJavaScriptMethod();
	compareText(application, "Delete Route Success Message", "DeleteRouteSuccessMessage", "Static Route successfully deleted.", xml);
}catch(Exception e) {
	 e.printStackTrace();
}

}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
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



//Add/Edit/Delete Routes
public void addRouteFunction_AS(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCity, 
String PE_RouteSubnetSize, String	PE_Gateway,String	PE_NetworkAddress, String	PE_NetworkMAS, String	PE_Metrics
) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Route Functionality For Access Switch");

	try {
	ScrolltoElement(application, "PE_CPE_Router_IPV4Command_Executebutton", xml);//interfaces_header//RouterTool_header//PE_CPE_Router_IPV4Command_Executebutton

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
click_commonMethod(application, "Add Link", "Routes_AddLink", xml);
compareText(application, "Add Route header", "Routes_AddRoutes_Header", "Add Route", xml);
click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);
Thread.sleep(4000);

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
	
	//*compareText(application, "Subnet Type Value", "CPE_Routes_EIPAllocation_SubnetTypeValue", "XFER", xml);
	click_commonMethod(application, "Allocate Subnet Button", "Routes_AllocateSubnetButon", xml);
	
	warningMessage_commonMethod(application, "Routes_EIPAllocation_CityWarningMessage", "City  warning message", xml);
	warningMessage_commonMethod(application, "Routes_EIPAllocation_SubnetSizeWarningMessage", "Subnet Size  warning message", xml);
	warningMessage_commonMethod(application, "Routes_EIPAllocation_AvilablePoolWarningMessage", "Avilable Pool  warning message", xml);
	
	//*SelectDropdownValueUnderSpanTag(application, "City dropdown", PE_RouteCity, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueSpanTag", xml);
	//*SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", PE_RouteSubnetSize, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);
	//*GetText(application, "Available Pool Message", "Routes_EIPAllocation_AvailablePoolsMessage");
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
compareText(application, "Add Route Success Message", "AddRouteSuccessMessage", "Static Route successfully created.", xml);

}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
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





public void editRouteFunction_AS(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
String PE_RouteSubnetSizeEdit, String	PE_GatewayEdit,String	PE_NetworkAddressEdit, String	PE_NetworkMASEdit, String	PE_MetricsEdit
) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edit Route Functionality For Access Switch");
	
	try {
	ScrolltoElement(application, "PE_CPE_Router_IPV4Command_Executebutton", xml);//interfaces_header//RouterTool_header//Routes_Header//PE_CPE_Router_IPV4Command_Executebutton

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
click_commonMethod(application, "Edit Link", "Routes_EditLink", xml);
compareText(application, "Edit Route header", "Routes_EditRoutes_Header", "Edit Route", xml);
click_commonMethod(application, "Routes EIP Allocation Link", "Routes_EIPAllocationLink", xml);
Thread.sleep(4000);

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_EIPSubnetAllocationforService_Header")).isDisplayed()){
	
	//*compareText(application, "Subnet Type Value", "CPE_Routes_EIPAllocation_SubnetTypeValue", "XFER", xml);
	//*SelectDropdownValueUnderSpanTag(application, "City dropdown", PE_RouteCityEdit, "Routes_EIPAllocation_CityDropdown", "commonDropdownValueSpanTag", xml);
	//*SelectDropdownValueUnderSpanTag(application, "Subnet Size dropdown", PE_RouteSubnetSizeEdit, "Routes_EIPAllocation_SubnetSizeDropdown", "commonDropdownValueSpanTag", xml);
	//*GetText(application, "Available Pool Message", "Routes_EIPAllocation_AvailablePoolsMessage");
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
compareText(application, "Add Route Success Message", "UpdateRouteSuccessMessage", "Static Route successfully updated.", xml);


}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
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








public void deleteRouteFunction_AS(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
String PE_RouteSubnetSizeEdit, String	PE_GatewayEdit,String	PE_NetworkAddressEdit, String	PE_NetworkMASEdit, String	PE_MetricsEdit
) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Delete Route Functionality For Access Switch");

	try {
	ScrolltoElement(application, "PE_CPE_Router_IPV4Command_Executebutton", xml);//interfaces_header//RouterTool_header//PE_CPE_Router_IPV4Command_Executebutton

if(getwebelement(xml.getlocator("//locators/" + application + "/Routes_Header")).isDisplayed()){
	Thread.sleep(2000);
click_commonMethod(application, "Select Route Checkbox", "Routes_SelectRouteCheckbox", xml);
click_commonMethod(application, "Action", "Routes_Actionbutton", xml);
click_commonMethod(application, "Delete Link", "Routes_DeleteLink", xml);

Thread.sleep(2000);
Alert alert = driver.switchTo().alert();



GetMessageFromAlertJavaScriptPopup();

try {  
AcceptJavaScriptMethod();//CancelJavaScriptMethod();
	compareText(application, "Delete Route Success Message", "DeleteRouteSuccessMessage", "Static Route successfully deleted.", xml);
}catch(Exception e) {
	 e.printStackTrace();
}

}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Routes panel header is not displaying");
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




//public static String JuniperInterfaceNameValue, Edit_JuniperInterfaceNameValue;
public void VerifyAddInterface_DistributionSwitch_JuniperVendor(String application, String interfacename, String getaddress, 
		String ipv6_getaddress_button, String interfaceaddressrange_value,
		String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize,
		String eipallocation_availableblocksvalue, String link_value, String bearertype_value,String PhysicalAddress,String VirtualAddress,
		String IPAddressingType, String bandwidth_value,
		String encapsulation_value, String bgp_checkbox, String framingtype_value, String vlanID_value, 
		String juniper_configureinterface_checkbox, 
	String STM1Number_value, String bearerNo_value, 
		String unitid_value, String ivmanagement_checkbox, 
		String atricaconnected_checkbox
		) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Interface Functionality - Distribution Switch Juniper");
		try {
	ScrolltoElement(application, "ConfigurationOptions_Header", xml);//ProviderEquipment_header //PE_AddPEDeviceLink
	if(getwebelement(xml.getlocator("//locators/" + application + "/DistributionSwitchexistingdevicegrid")).isDisplayed())
	{
		try {
		click_commonMethod(application, "View Link", "viewservicepage_viewdevicelink1", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
waitforPagetobeenable();
ScrolltoElement(application, "Routes_Header", xml);//Routes_Header//RouterTool_header
Thread.sleep(1000);

compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
click_commonMethod(application, "Add Interface/Link", "addinterface_link", xml);
waitforPagetobeenable();

if(getwebelement(xml.getlocator("//locators/" + application + "/addinterface_header")).isDisplayed()) {
ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Interface' page navigated as expected");
System.out.println("'Add Interface' page navigated as expected");

scrollToTop();
GetText(application, "Add Interface header", "addinterface_header");
scrolltoend();
click_commonMethod(application, "OK", "okbutton", xml);

Thread.sleep(1000);
scrollToTop();
//verify warning messages in add interface page
//warningMessage_commonMethod(application, "bearertype_warngmsg", "Bearer Type", xml);
//warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);
warningMessage_commonMethod(application, "IPAddressingType_warngmsg", "IP Addressing Type", xml);


//Add Interface
addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", juniper_configureinterface_checkbox, "no", xml);
click_commonMethod(application, "Interface Configuration Checkbox", "configureinterface_checkbox", xml);
GetText(application, "Network", "network_fieldvalue");


//verify EIP Allocation
Thread.sleep(1000);
click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
{
compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
GetText(application, "Subnet Type", "subnettype_value");
SelectDropdownValueUnderSelectTag(application, "City", eipallocation_city, "eipallocation_citydropdown", xml);
SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_subnetsize, "eipallocation_subnetsize", xml);
GetText(application, "Available Pools", "eipallocation_availablepools_value");
//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
Thread.sleep(1000);
click_commonMethod(application, "Close", "closesymbol", xml);
}
else
{
ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
}
Thread.sleep(2000);

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
Thread.sleep(1000);
click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

}

//compareText_fromtextFields(application, "Network", "networkipv6_fieldvalue", "WAN (XFER)", xml);
isDisplayed(application, "networkipv6_fieldvalue", "Network");

//verify EIP Allocation for IPV6
Thread.sleep(1000);
click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
{
compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
GetText(application, "Subnet Type", "subnettype_value");
GetText(application, "Space Name", "eipallocation_spacename");
SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_ipv6_subnetsize, "eipallocation_ipv6_subnetsize", xml);
SelectDropdownValueUnderSelectTag(application, "Available Blocks", eipallocation_availableblocksvalue, "availableblocks_dropdown", xml);
Thread.sleep(1000);
click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
Thread.sleep(2000);
scrollToTop();
compareText(application, "Subnet aalocation success message", "successmsg", "Subnet Allocation IPTransitService_05-IPTransitService_05-XFER-0017527 (null)Successfully", xml);
}
else
{
ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
}
Thread.sleep(2000);

//verify getaddress ipv6
if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
{
ScrolltoElement(application, "getaddress1_button", xml);
click_commonMethod(application, "Get Address", "getaddress2_button", xml);
SelectDropdownValueUnderSelectTag(application, "AvInterface Address Range IPv6", interfaceaddressrange_value, "interfacerange_AddressIpv6_dropdown", xml);
click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
}
else
{
addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
Thread.sleep(1000);
click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
}

ScrolltoElement(application, "link_textfield", xml);
addtextFields_commonMethod(application, "Physical Address", "PhysicalAddressTextfield", PhysicalAddress, xml);
addtextFields_commonMethod(application, "Virtual Address", "VirtualAddressTextfield", VirtualAddress, xml);
addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
SelectDropdownValueUnderSelectTag(application, "Bearer Type", bearertype_value, "bearertype_dropdown", xml);
SelectDropdownValueUnderSelectTag(application, "Bandwidth", bandwidth_value, "bandwidth_dropdown", xml);
SelectDropdownValueUnderSelectTag(application, "IP Addressing Type", IPAddressingType, "IPAddressingTypeDropdown", xml);
addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanID_value, xml);
addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", unitid_value, xml);

addCheckbox_commonMethod(application, "ivmanagement_checkbox", "IV Management", ivmanagement_checkbox, "no", xml);
addCheckbox_commonMethod(application, "atricaconnected_checkbox", "Atrica Connected", atricaconnected_checkbox, "no", xml);


//configuration panel in add interface page
if(juniper_configureinterface_checkbox.equalsIgnoreCase("yes") || juniper_configureinterface_checkbox.equalsIgnoreCase("null"))
{
compareText(application, "Configuration", "configuration_header", "Configuration", xml);
scrolltoend();
click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
click_commonMethod(application, "Generate Configuration", "GenerateConfigurationButton2", xml);
Thread.sleep(2000);
GetText(application, "Configuration", "configuration_textarea");
Thread.sleep(1000);
//click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
Thread.sleep(2000);
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
Thread.sleep(2000);
}catch(Exception e) {
e.printStackTrace();
}

}
else
{
//*click_commonMethod(application, "OK", "okbutton", xml);
	click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);

//Alert alert = driver.switchTo().alert();		
//// Capturing alert message.    
//String alertMessage= driver.switchTo().alert().getText();
//if(alertMessage.isEmpty()) {
//ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
//System.out.println("No Message displays"); 
//}else {
//ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
//System.out.println("text message for alert displays as: "+alertMessage);
//}
//
//try {  
//alert.accept();
//Thread.sleep(2000);
//}catch(Exception e) {
//e.printStackTrace();
//}
}
Thread.sleep(5000);
compareText(application, "Interface Added success message", "AddInterfaceSucessMessage", "Interface successfully created.", xml);

scrolltoend();
//**click_commonMethod(application, "Back", "viewpage_backbutton", xml);
Thread.sleep(2000);

}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Interface' page not navigated");
System.out.println("'Add Interface' page not navigated");
}
}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
System.out.println("'View PE Device' page not navigated");
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



public void VerifyAddInterface_AccessSwitch_AristaVendor(String application, String interfacename, String getaddress, 
		String interfaceaddressrange_value,	String eipallocation_city, String eipallocation_subnetsize,
		String eipallocation_availableblocksvalue, String link_value, String bearertype_value,String PhysicalAddress,String VirtualAddress,
		String IPAddressingType, String bandwidth_value,String vlanID_value, String Arista_ConfigInterface_checkbox, 
	String Interface_GroupNumber, String Interface_Speed,String Interface_Duplex, String Interface_AccessLeaf, String Interface_InterfacePortType, 
			String Interface_VRRPIP, String Interface_VRRPPassword, String Interface_InterfaceNode,String IVManagement_checkbox,
			String AtricaConnected_checkbox
		) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Interface Functionality - Access Switch Arista");
	try {
	ScrolltoElement(application, "DistributionSwitchPanel_Header", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/AccessSwitchexistingdevicegrid")).isDisplayed())
	{
		try {
		click_commonMethod(application, "View Link", "viewservicepage_viewdevicelink1", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()){
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
Thread.sleep(5000);
ScrolltoElement(application, "Routes_Header", xml);//Routes_Header//RouterTool_header
Thread.sleep(1000);
compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
click_commonMethod(application, "Add Interface/Link", "addinterface_link", xml);
Thread.sleep(2000);

if(getwebelement(xml.getlocator("//locators/" + application + "/addinterface_header")).isDisplayed()) {
ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Interface' page navigated as expected");
System.out.println("'Add Interface' page navigated as expected");

scrollToTop();
GetText(application, "Add Interface header", "addinterface_header");
scrolltoend();
click_commonMethod(application, "OK", "okbutton", xml);

Thread.sleep(1000);

//verify warning messages in add interface page
warningMessage_commonMethod(application, "IPAddressingType_warngmsg", "IP Addressing Type", xml);
warningMessage_commonMethod(application, "SpeedWarningMessage", "Speed", xml);
warningMessage_commonMethod(application, "DuplexWarningMessage", "Duplex", xml);
scrollToTop();
warningMessage_commonMethod(application, "interface_warngmsg", "Interface", xml);


//Add Interface
addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", Arista_ConfigInterface_checkbox, "no", xml);
	//click_commonMethod(application, "Interface Configuration Checkbox", "configureinterface_checkbox", xml);
addtextFields_commonMethod(application, "Interface", "interfacename_textfield", interfacename, xml);

//verify EIP Allocation
Thread.sleep(1000);
click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
{
compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
GetText(application, "Subnet Type", "subnettype_value");
SelectDropdownValueUnderSelectTag(application, "City", eipallocation_city, "eipallocation_citydropdown", xml);
SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_subnetsize, "eipallocation_subnetsize", xml);
GetText(application, "Available Pools", "eipallocation_availablepools_value");
//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
Thread.sleep(1000);
click_commonMethod(application, "Close", "closesymbol", xml);
}
else
{
ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
}
Thread.sleep(2000);

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
Thread.sleep(1000);
click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

}

			
ScrolltoElement(application, "link_textfield", xml);
addtextFields_commonMethod(application, "Group Number", "GroupNumberTextfield", Interface_GroupNumber, xml);
addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
SelectDropdownValueUnderSelectTag(application, "IP Addressing Type", IPAddressingType, "IPAddressingTypeDropdown", xml);


SelectDropdownValueUnderSelectTag(application, "Bearer Type", bearertype_value, "BarrierTypeDropdown", xml);

if(bearertype_value.equalsIgnoreCase("Ethernet") || bearertype_value.equalsIgnoreCase("Fast Ethernet") || bearertype_value.equalsIgnoreCase("Gigabit Ethernet")) {
	SelectDropdownValueUnderSelectTag(application, "Bandwidth", bandwidth_value, "BandwithDropdown", xml);
	SelectDropdownValueUnderSelectTag(application, "Speed", Interface_Speed, "SpeedDropdown", xml);
	SelectDropdownValueUnderSelectTag(application, "Duplex", Interface_Duplex, "DuplexDropdown", xml);
	addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanID_value, xml);
	
}
else if(bearertype_value.equalsIgnoreCase("Ethernet-VLAN"))
{
	SelectDropdownValueUnderSelectTag(application, "Bandwidth", bandwidth_value, "BandwithDropdown", xml);
	addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanID_value, xml);
	SelectDropdownValueUnderSelectTag(application, "Speed", Interface_Speed, "SpeedDropdown", xml);
	SelectDropdownValueUnderSelectTag(application, "Duplex", Interface_Duplex, "DuplexDropdown", xml);
	SelectDropdownValueUnderSelectTag(application, "Bandwidth", bandwidth_value, "BandwithDropdown", xml);
	addtextFields_commonMethod(application, "VRRP IP", "VRRPIPTextfield", Interface_VRRPIP, xml);
	addtextFields_commonMethod(application, "VRRP Password", "VRRPPasswordTextfield", Interface_VRRPPassword, xml);
	SelectDropdownValueUnderSelectTag(application, "Interface Node", Interface_InterfaceNode, "BandwithDropdown", xml);
}

// String Multilink_AccessLeaf, String Multilink_InterfacePortType
if(Interface_AccessLeaf.equalsIgnoreCase("Yes")) {
	click_commonMethod(application, "Access Leaf", "AccessLeafCheckbox", xml);
	SelectDropdownValueUnderSelectTag(application, "Interface Port Type", Interface_InterfacePortType, "InterfacePortypeDropdown", xml);
}

addCheckbox_commonMethod(application, "ivmanagement_checkbox", "IV Management", IVManagement_checkbox, "no", xml);




//configuration panel in add interface page
if(Arista_ConfigInterface_checkbox.equalsIgnoreCase("yes") || Arista_ConfigInterface_checkbox.equalsIgnoreCase("null"))
{
compareText(application, "Configuration", "configuration_header", "Configuration", xml);
scrolltoend();
click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
click_commonMethod(application, "Generate Configuration", "GenerateConfigurationButton2", xml);
Thread.sleep(2000);
GetText(application, "Configuration", "configuration_textarea");
Thread.sleep(1000);
//click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
Thread.sleep(2000);
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
Thread.sleep(2000);
}catch(Exception e) {
e.printStackTrace();
}

}
else
{
		click_commonMethod(application, "OK", "okbutton", xml);
		Alert alert = driver.switchTo().alert();
		alert.accept();
}
waitforPagetobeenable();
compareText(application, "Interface Added success message", "AddInterfaceSucessMessage", "Interface successfully created.", xml);


}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Interface' page not navigated");
System.out.println("'Add Interface' page not navigated");
}
}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
System.out.println("'View PE Device' page not navigated");
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
public void VerifyAddMultilink_DistributionSwitch_JuniperVendor(String application, String interfacename, String getaddress, 
		String ipv6_getaddress_button, String interfaceaddressrange_value,
		String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize,
		String eipallocation_availableblocksvalue, String link_value, String bearertype_value,String PhysicalAddress,String VirtualAddress,
		String bgp_checkbox, String juniper_configureinterface_checkbox, String unitid_value, String ivmanagement_checkbox, 
		String atricaconnected_checkbox
		) throws InterruptedException, DocumentException, IOException {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Multilink Functionality - Distribution Switch Juniper");
	
	try {
	ScrolltoElement(application, "ConfigurationOptions_Header", xml);//ProviderEquipment_header //PE_AddPEDeviceLink
	if(getwebelement(xml.getlocator("//locators/" + application + "/DistributionSwitchexistingdevicegrid")).isDisplayed())
	{
		try {
		click_commonMethod(application, "View Link", "viewservicepage_viewdevicelink1", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()){
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
Thread.sleep(5000);
ScrolltoElement(application, "Routes_Header", xml);//Routes_Header//RouterTool_header
Thread.sleep(1000);
compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
click_commonMethod(application, "Add Multilink", "addmultilink_link", xml);
Thread.sleep(2000);

if(getwebelement(xml.getlocator("//locators/" + application + "/addmultilink_header")).isDisplayed()) {
ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Multilink' page navigated as expected");
System.out.println("'Add Multilink' page navigated as expected");

scrolltoend();
click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);

Thread.sleep(1000);
scrollToTop();
//verify warning messages in add interface page
warningMessage_commonMethod(application, "Multilink_BandwidthWarningMessage", "Bandwidth", xml);
warningMessage_commonMethod(application, "Multilink_PhysicalAddressWarningMessage", "Physical Address", xml);
warningMessage_commonMethod(application, "Multilink_ConfigurationWarningMessage", "Configuration", xml);


//Add Interface
addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", juniper_configureinterface_checkbox, "no", xml);
scrollToTop();
//click_commonMethod(application, "Interface Configuration Checkbox", "configureinterface_checkbox", xml);
GetText(application, "Network", "network_fieldvalue");

//verify EIP Allocation
Thread.sleep(1000);
click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
{
compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
SelectDropdownValueUnderSelectTag(application, "City", eipallocation_city, "eipallocation_citydropdown", xml);
SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_subnetsize, "eipallocation_subnetsize", xml);
GetText(application, "Available Pools", "eipallocation_availablepools_value");
//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
Thread.sleep(1000);
click_commonMethod(application, "Close", "closesymbol", xml);
}
else
{
ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
}
Thread.sleep(2000);

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
Thread.sleep(1000);
click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

}


//verify EIP Allocation for IPV6
Thread.sleep(1000);
click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
{
compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
GetText(application, "Subnet Type", "subnettype_value");
GetText(application, "Space Name", "eipallocation_spacename");
SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_ipv6_subnetsize, "eipallocation_ipv6_subnetsize", xml);
SelectDropdownValueUnderSelectTag(application, "Available Blocks", eipallocation_availableblocksvalue, "availableblocks_dropdown", xml);
Thread.sleep(1000);
click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
Thread.sleep(2000);
scrollToTop();
compareText(application, "Subnet aalocation success message", "successmsg", "Subnet Allocation IPTransitService_05-IPTransitService_05-XFER-0017527 (null)Successfully", xml);
}
else
{
ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
}
Thread.sleep(2000);

//verify getaddress ipv6
if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
{
ScrolltoElement(application, "getaddress1_button", xml);
click_commonMethod(application, "Get Address", "getaddress2_button", xml);
SelectDropdownValueUnderSelectTag(application, "Interface Address Range IPV6", interfaceaddressrange_value, "interfacerange_AddressIpv6_dropdownMultilink", xml);
click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
}
else
{
addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", interfaceaddressrange_value, xml);
Thread.sleep(1000);
click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
}

ScrolltoElement(application, "link_textfield", xml);
addtextFields_commonMethod(application, "Physical Address", "PhysicalAddressTextfield", PhysicalAddress, xml);
addtextFields_commonMethod(application, "Virtual Address", "VirtualAddressTextfield", VirtualAddress, xml);
addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", unitid_value, xml);

addCheckbox_commonMethod(application, "ivmanagement_checkbox", "IV Management", ivmanagement_checkbox, "no", xml);
addCheckbox_commonMethod(application, "atricaconnected_checkbox", "Atrica Connected", atricaconnected_checkbox, "no", xml);


//configuration panel in add interface page
if(juniper_configureinterface_checkbox.equalsIgnoreCase("yes") || juniper_configureinterface_checkbox.equalsIgnoreCase("null"))
{
compareText(application, "Configuration", "configuration_header", "Configuration", xml);
scrolltoend();
click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
click_commonMethod(application, "Generate Configuration", "GenerateConfigurationButton2", xml);
Thread.sleep(2000);
GetText(application, "Configuration", "configuration_textarea");
Thread.sleep(1000);
//click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
Thread.sleep(2000);
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
Thread.sleep(2000);
}catch(Exception e) {
e.printStackTrace();
}

}
else
{
//*click_commonMethod(application, "OK", "okbutton", xml);
	click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);

//Alert alert = driver.switchTo().alert();		
//// Capturing alert message.    
//String alertMessage= driver.switchTo().alert().getText();
//if(alertMessage.isEmpty()) {
//ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
//System.out.println("No Message displays"); 
//}else {
//ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
//System.out.println("text message for alert displays as: "+alertMessage);
//}
//
//try {  
//alert.accept();
//Thread.sleep(2000);
//}catch(Exception e) {
//e.printStackTrace();
//}
}
Thread.sleep(5000);
compareText(application, "Multilink Added success message", "AddMultilinkSucessMessage", "Interface successfully created.", xml);

scrolltoend();
//**click_commonMethod(application, "Back", "viewpage_backbutton", xml);
Thread.sleep(2000);

}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Interface' page not navigated");
System.out.println("'Add Interface' page not navigated");
}
}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
System.out.println("'View PE Device' page not navigated");
}

	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Multilink page");
		System.out.println(  e+ " : Field is not displayed in Add Multilink page");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add Multilink page");
		System.out.println(  e+" : Field is not displayed in Add Multilink page");
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



public static String Arista_Multilink_InterfaceName;
public void VerifyAddMultilink_AccessSwitch_AristaVendor(String application, String MultilinkName, String VendorModel,  String getaddress,
		String ipv6_getaddress_button, String interfaceaddressrange_value,	String eipallocation_city, String eipallocation_subnetsize,
		String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value,String Bandwidth_Valu,
		String IPAddressingType, String bgp_checkbox, String Multilink_ConfigInterface_checkbox, String ivmanagement_checkbox, 
		String atricaconnected_checkbox, String  Multilink_GroupNumber, String  Multilink_Speed, String Multilink_Duplex, 
		String VLANID_Value, String Multilink_AccessLeaf, String Multilink_InterfacePortType
		) throws InterruptedException, DocumentException, IOException {
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Multilink Functionality - Access Switch Arista");
	
	try {
	ScrolltoElement(application, "DistributionSwitchPanel_Header", xml);
	if(getwebelement(xml.getlocator("//locators/" + application + "/AccessSwitchexistingdevicegrid")).isDisplayed())
	{
		try {
		click_commonMethod(application, "View Link", "viewservicepage_viewdevicelink1", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()){
			ExtentTestManager.getTest().log(LogStatus.PASS, "'View PE Device' page navigated as expected");
			System.out.println("'View PE Device' page navigated as expected");
			
Thread.sleep(5000);
ScrolltoElement(application, "Routes_Header", xml);//Routes_Header//RouterTool_header
Thread.sleep(1000);
compareText(application, "Interfaces header", "interfaces_header", "Interfaces", xml);
click_commonMethod(application, "Action dropdown", "interfacepanel_actiondropdown", xml);
click_commonMethod(application, "Add Multilink", "addmultilink_link", xml);
Thread.sleep(2000);

if(getwebelement(xml.getlocator("//locators/" + application + "/addmultilink_header")).isDisplayed()) {
ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Multilink' page navigated as expected");
System.out.println("'Add Multilink' page navigated as expected");

scrolltoend();
scrolltoend();
click_commonMethod(application, "OK", "okbutton", xml);

Thread.sleep(1000);
scrollToTop();
//verify warning messages in add interface page
warningMessage_commonMethod(application, "interface_warngmsg", "Multilink", xml);
addtextFields_commonMethod(application, "Multilink", "MultilinknterfaceTextfield", MultilinkName, xml);

//Add Interface
addCheckbox_commonMethod(application, "configureinterface_checkbox", "Configure Interface on Device", Multilink_ConfigInterface_checkbox, "no", xml);
scrollToTop();

//verify EIP Allocation
Thread.sleep(1000);
click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
{
compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
SelectDropdownValueUnderSelectTag(application, "City", eipallocation_city, "eipallocation_citydropdown", xml);
SelectDropdownValueUnderSelectTag(application, "Sub Net Size", eipallocation_subnetsize, "eipallocation_subnetsize", xml);
GetText(application, "Available Pools", "eipallocation_availablepools_value");
//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
Thread.sleep(1000);
click_commonMethod(application, "Close", "closesymbol", xml);
}
else
{
ExtentTestManager.getTest().log(LogStatus.FAIL, "EIP Allocation button is not working");
}
Thread.sleep(2000);

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
Thread.sleep(1000);
click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

}


ScrolltoElement(application, "link_textfield", xml);
addtextFields_commonMethod(application, "Group Number", "GroupNumberTextfield", Multilink_GroupNumber, xml);
addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", VLANID_Value, xml);
SelectDropdownValueUnderSelectTag(application, "Speed", Multilink_Speed, "SpeedDropdown", xml);
SelectDropdownValueUnderSelectTag(application, "Bandwidth", Bandwidth_Valu, "BandwithDropdown", xml);
SelectDropdownValueUnderSelectTag(application, "IP Addressing Type", IPAddressingType, "IPAddressingTypeDropdown", xml);
SelectDropdownValueUnderSelectTag(application, "Duplex", Multilink_Duplex, "DuplexDropdown", xml);

// String Multilink_AccessLeaf, String Multilink_InterfacePortType
if(Multilink_AccessLeaf.equalsIgnoreCase("Yes")) {
	click_commonMethod(application, "Access Leaf", "AccessLeafCheckbox", xml);
	SelectDropdownValueUnderSelectTag(application, "Interface Port Type", Multilink_InterfacePortType, "InterfacePortypeDropdown", xml);
}

addCheckbox_commonMethod(application, "ivmanagement_checkbox", "IV Management", ivmanagement_checkbox, "no", xml);

//configuration panel in add interface page
if(Multilink_ConfigInterface_checkbox.equalsIgnoreCase("yes") || Multilink_ConfigInterface_checkbox.equalsIgnoreCase("null"))
{
compareText(application, "Configuration", "configuration_header", "Configuration", xml);
scrolltoend();
click_commonMethod(application, "Generate Configuration", "generateconfiguration_button", xml);
click_commonMethod(application, "Generate Configuration", "GenerateConfigurationButton2", xml);
Thread.sleep(2000);
GetText(application, "Configuration", "configuration_textarea");
Thread.sleep(1000);
//click_commonMethod(application, "Save Configuration to File", "saveconfiguration_button", xml);
Thread.sleep(2000);
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
Thread.sleep(2000);
}catch(Exception e) {
e.printStackTrace();
}

}
else
{
click_commonMethod(application, "OK", "okbutton", xml);
	//click_commonMethod(application, "Execute and Save", "executeandsave_button", xml);

//Alert alert = driver.switchTo().alert();		
//// Capturing alert message.    
//String alertMessage= driver.switchTo().alert().getText();
//if(alertMessage.isEmpty()) {
//ExtentTestManager.getTest().log(LogStatus.FAIL, "No message displays");
//System.out.println("No Message displays"); 
//}else {
//ExtentTestManager.getTest().log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
//System.out.println("text message for alert displays as: "+alertMessage);
//}
//
//try {  
//alert.accept();
//Thread.sleep(2000);
//}catch(Exception e) {
//e.printStackTrace();
//}
}
Thread.sleep(5000);
compareText(application, "Multilink Added success message", "AddMultilinkSucessMessage", "Interface successfully created.", xml);

scrolltoend();
//**click_commonMethod(application, "Back", "viewpage_backbutton", xml);
Thread.sleep(2000);

}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Interface' page not navigated");
System.out.println("'Add Interface' page not navigated");
}
}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "'View PE Device' page not navigated");
System.out.println("'View PE Device' page not navigated");
}

	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add Multilink page");
		System.out.println(  e+ " : Field is not displayed in Add Multilink page");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add Multilink page");
		System.out.println(  e+" : Field is not displayed in Add Multilink page");
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






public void deleteInterface_PE1(String application, String interfacename, String name, String editDevicename) throws InterruptedException, DocumentException {

	try {
	//Delete Interface
ScrolltoElement(application, "portalaccess_header", xml);
scrolltoend();
//click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
Thread.sleep(1000);
WebElement SelectInterface1= driver.findElement(By.xpath("//div[@role='gridcell' and @col-id='interfaceName']/div[text()='"+interfacename+"']"));
if(SelectInterface1.isDisplayed())
{
Clickon(SelectInterface1);
ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
Thread.sleep(1000);

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





public void delete_MultilinkInterface(String application, String multilink_interfacename, String name, String editDevicename, String vendormodel) throws InterruptedException, DocumentException {

	try {
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
WebElement CiscoMultilinkInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", Arista_Multilink_InterfaceName));
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


/// Select Interface
public void selectInterfacelinkforDevice(String application, String name) throws InterruptedException, DocumentException {

//ScrolltoElement(application, "portalaccess_header", xml);
scrolltoend();
Thread.sleep(1000);
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

public void verifyAddCPEDeviceFunction(String application, String ServiceIdentification, String  CPE_DeviceName,
		String  CPE_VendorModel,  String   CPE_ManagementAddress,  String  CPE_Snmpro, String   CPE_Country,
		String   CPE_City,  String CPE_Site ,  String CPE_Premise,  String   CPE_Role1,  String  CPE_Role2
		) throws InterruptedException, DocumentException, IOException {
	
		//ScrolltoElement(application, "CustomerPremiseEquipment_CPE_Panelheader", xml);//CustomerPremiseEquipment_CPE_Panelheader
		scrolltoend();
			if(getwebelement(xml.getlocator("//locators/" + application + "/CustomerPremiseEquipment_CPE_Panelheader")).isDisplayed())
		{
			click_commonMethod(application, "Add CPE Device Link", "AddCPEDeviceLink", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEDevicePageHeader")).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add CPE Device' Page navigated as expected");
				System.out.println("'Add CPE Device' Page navigated as expected");
				
		scrolltoend();
		click(application, "Next Button", "AddCPE_NextButton");
		
		try { 
		WarningMessage(application, "Country", "AddCPE_WarningMessage_Country");
		WarningMessage(application, "Vendor Model", "AddCPE_WarningMessage_VendorModel");
		
		ClearAndEnterTextValue(application, "Device Name", "CPE_DeviceNameTextfield", CPE_DeviceName, xml);
		SelectDropdownValueUnderSelectTag(application, "Vendor/Model", CPE_VendorModel, "CPE_VendorModelDropdown",  xml); 
		EnterTextValue(application, CPE_ManagementAddress, "Management Address", "CPE_ManagementAddressTextfield");
		EnterTextValue(application, CPE_Snmpro, "Snmpro", "CPE_SnmproTextfield");
		
		SelectDropdownValueUnderSelectTag(application, "Country", CPE_Country, "CPE_CountryDropdown", xml);
		SelectDropdownValueUnderSelectTag(application, "City", CPE_City, "CPE_CityDropdown", xml);
		scrolltoend();
		SelectDropdownValueUnderSelectTag(application, "Site", CPE_Site, "CPE_SiteDropdown", xml);
		//**SelectDropdownValueUnderSelectTag(application, "Premise", CPE_Premise, "CPE_PremiseDropdown", xml);
		SelectDropdownValueUnderSelectTag(application, "CPE Role 1", CPE_Role1, "CPE_Role1", xml);
		SelectDropdownValueUnderSelectTag(application, "CPE Role 2", CPE_Role2, "CPE_Role2", xml);

		click(application, "Next Button", "AddCPE_NextButton");
		
		//verifysuccessmessage(application, "CPE Device added successfully");
		compareText(application, "Add CPE Device Success Message", "CPE_AddCPEDeviceSuccessMessage", "Device successfully created.");
		
		}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in Add CPE Device page");
		System.out.println(  e+ " : Field is not displayed in Add CPE Device page");
		}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in Add CPE Device page ");
		System.out.println(  e+" : Field is not displayed in Add CPE Device page");
		}

		
			
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add CPE Device' page not navigated");
			System.out.println("'Add CPE Device' page not navigated");
				  }
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'Customer Premise Equipment (CPE)' panel not displayed");
		System.out.println("'Customer Premise Equipment (CPE)' panel not displayed");
			  }
}
	















public void verifyAddedCPEDeviceInformation_View(String application, String ServiceIdentification, String  CPE_DeviceName,
		String  CPE_VendorModel,  String   CPE_ManagementAddress,  String  CPE_Snmpro, String   CPE_Country,
		String   CPE_City,  String CPE_Site ,  String CPE_Premise,  String   CPE_Role1,  String  CPE_Role2
		) throws InterruptedException, DocumentException, IOException {

	if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
		System.out.println("'View CPE Device' page navigated as expected");
	
	try {
	// Verify All Device Information under View CPE Device page//Not working
	
	compareText_InViewPage_CPE(application, "Name", CPE_DeviceName, xml);
	compareText_InViewPage_CPE(application, "Vendor/Model", CPE_VendorModel, xml);
	compareText_InViewPage_CPE(application, "Management Address", CPE_ManagementAddress, xml);
	compareText_InViewPage_CPE(application, "Snmpro", CPE_Snmpro, xml);

	GetText(application, "Country", "CPE_View_CountryValue");
	GetText(application, "City", "CPE_View_CityValue");
	GetText(application, "Site", "CPE_View_SiteValue");
	//**GetText(application, "Premise", "CPE_View_PremiseValue");
	compareText_InViewPage_CPE(application, "CPE Role 1", CPE_Role1, xml);
	compareText_InViewPage_CPE(application, "CPE Role 2", CPE_Role2, xml);

	GetText(application, "Test", "CPE_View_TestColumnName");
	GetText(application, "Status", "CPE_View_StatusColumnName");
	GetText(application, "Last Refresh", "CPE_View_LastRefresh");
	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, e+ " : Field is not displayed in View CPE Device page");
		System.out.println(  e+ " : Field is not displayed in View CPE Device page");
		}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,  e+" : Field is not displayed in View CPE Device page ");
		System.out.println(  e+" : Field is not displayed in View CPE Device page");
		}
	
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'View CPE Device' page not navigated");
		System.out.println("'View CPE Device' page not navigated");
		 }
	
	ExtentTestManager.getTest().log(LogStatus.PASS, "All Fields values verified in View CPE Device Page");
	Log.info("------ Verified Added CPE Device Information successfully ------");
}




public void verifyEditDeviceFunction_DS(String application, String ServiceIdentification , String	CPE_DeviceNameEdit, String	CPE_VendorModelEdit,
		String	CPE_ManagementAddressEdit	
		, String CPE_CountryEdit, String	CPE_CityEdit, String CPE_SiteEdit, 
		String CPE_PremiseEdit) throws InterruptedException, DocumentException, IOException {
try {
	scrollToTop();
	if(getwebelement(xml.getlocator("//locators/" + application + "/viewdevicepage_header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'View Device' page navigated as expected");
		System.out.println("'View Device' page navigated as expected");
		
		click(application, "ACTION Link", "CPE_View_ActionLink");
		Thread.sleep(2000);
		click(application, "Edit Link", "CPE_View_Action_EditLink");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/DS_EditDevice_Header")).isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Edit PE Device' page navigated as expected");
			System.out.println("'Edit PE Device' page navigated as expected");
	
	
	try {
		
		//***ClearAndEnterTextValue(application, "Device Name", "CPE_DeviceNameTextfield", CPE_DeviceNameEdit, xml);
		SelectDropdownValueUnderSelectTag(application, "Vendor/Model", CPE_VendorModelEdit, "CPE_VendorModelDropdown",  xml); 

		//***ClearAndEnterTextValue(application, "Management Address", "CPE_ManagementAddressTextfield", CPE_ManagementAddressEdit, xml);

		scrolltoend();
		SelectDropdownValueUnderSelectTag(application, "Country", CPE_CountryEdit, "CPE_CountryDropdown", xml);
		SelectDropdownValueUnderSelectTag(application, "City", CPE_CityEdit, "CPE_CityDropdown", xml);
		SelectDropdownValueUnderSelectTag(application, "Site", CPE_SiteEdit, "CPE_SiteDropdown", xml);
		scrolltoend();
		//**SelectDropdownValueUnderSelectTag(application, "Premise", CPE_Premise, "CPE_PremiseDropdown", xml);

		click(application, "OK Button", "EditCPE_OKButton");
		Thread.sleep(5000);
		compareText(application, "CPE Device Update message", "CPE_UpdateCPEDeviceSuccessMessage", "Device successfully updated.");
		Log.info("------  CPE Device updated successfully   ------");
		
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in Edit CPE Device page");
		System.out.println( "Field is not displayed in Edit CPE Device page");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in Edit CPE Device page ");
		System.out.println( "Field is not displayed in Edit CPE Device page");
	}
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Edit CPE Device' page not  navigated");
			System.out.println("'Edit CPE Device' page not navigated");
		}
		
	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "'View CPE Device' page not navigated");
		System.out.println("'View CPE Device' page not navigated");
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





public void verifyAddNewAccessSwitchDeviceFunction(String application, String ServiceIdentification , String	NewAccessDeviceName, String	AS_VendorModel,
		String	AS_ManagementAddress, String AS_Snmpro,String AS_Country, String	AS_City, String CPE_SiteEdit, 
		String CPE_PremiseEdit) throws InterruptedException, DocumentException, IOException {
	try {

		scrolltoend();
		//Verify Mandatory fields
		click_commonMethod(application, "Next", "Next_Button", xml);
		Thread.sleep(2000);
		scrollToTop();
		Thread.sleep(2000);
		warningMessage_commonMethod(application, "warningMessage_name", "Device Name", xml);
		warningMessage_commonMethod(application, "warningMessage_vendor", "Vendor/Model", xml);
		//warningMessage_commonMethod(application, "warningMessage_Country", "Country", xml);
	
		addtextFields_commonMethod(application, "Name", "CPE_DeviceNameTextfield", NewAccessDeviceName, xml);
		SelectDropdownValueUnderSelectTag(application, "Vendor/Model", AS_VendorModel, "CPE_VendorModelDropdown",  xml); 
		addtextFields_commonMethod(application, "Management Address", "CPE_ManagementAddressTextfield", AS_ManagementAddress, xml);
		//**addtextFields_commonMethod(application, "snmpro", "snmproinputfield", AS_Snmpro, xml);//Bydefault


		scrolltoend();
		SelectDropdownValueUnderSelectTag(application, "Country", AS_Country, "CPE_CountryDropdown", xml);
		SelectDropdownValueUnderSelectTag(application, "City", AS_City, "CPE_CityDropdown", xml);
		SelectDropdownValueUnderSelectTag(application, "Site", CPE_SiteEdit, "CPE_SiteDropdown", xml);
		scrolltoend();
		//**SelectDropdownValueUnderSelectTag(application, "Premise", CPE_Premise, "CPE_PremiseDropdown", xml);
		click_commonMethod(application, "Next", "Next_Button", xml);
		scrollToTop();
		compareText(application, "Add Device success msg", "AS_AddPEDeviceSuccessfulMessage", "Device successfully created.", xml);
		
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in Add Access Switch Device page");
		System.out.println( "Field is not displayed in Add Access Switch Device page");
	}catch(Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in Add Access Switch Device page ");
		System.out.println( "Field is not displayed in Add Access Switch Device page");
	}
		
}

public void veriyFetchDeviceInterfacesFunction_CPE(String application,String ServiceIdentification,
		String CPE_ServiceStatusChangeRequired) throws Exception {

scrollToTop();
if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ViewDevice_Header")).isDisplayed()) {
	ExtentTestManager.getTest().log(LogStatus.PASS, "'View CPE Device' page navigated as expected");
	System.out.println("'View CPE Device' page navigated as expected");
	implicitlyWait("View CPE Device screen");
	try {
	click(application, "ACTION link", "CPE_View_ActionLink");
	click(application, "Fetch Device Interfaces Link", "CPE_View_Action_FetchDeviceInterfacesLink");
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_FetchDeviceInterfacesSuccessMessage")).isDisplayed()){
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
		System.out.println("Step : Device fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' displayed ");
	}else {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
		System.out.println("Step : Device not fetched successfully and confirmation message 'Fetch Interfaces started successfully. Please check the sync status of this device here' Not displayed ");
	}
	
	
	Thread.sleep(2000);
	//click_commonMethod(application, "here", "CPE_hereLink_UnderFetchDeviceInterfacesSuccessMessage", xml);/Not working
	safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" +application+ "/CPE_hereLink_UnderFetchDeviceInterfacesSuccessMessage")));
	Thread.sleep(2000);
	
	//Manage COLT's Network - Manage Network
	if(getwebelement(xml.getlocator("//locators/" + application + "/CPE_ManageCOLTsNetworkManageNetwork_header")).isDisplayed()) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Manage COLT's Network - Manage Network' page navigated as expected");
		System.out.println("'Manage COLT's Network - Manage Network' page navigated as expected");
		
	scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CPE_Manage_Synchronization_SynchronizeLink")));
	
	try {
	compareText(application, "Manage COLT's Network - Manage Network header", "CPE_ManageCOLTsNetworkManageNetwork_header", "Manage COLT's Network - Manage Network");
	GetText(application, "Device Name in Manage Colt page under Status Panel", "CPE_Manage_Status_DeviceValue");
	GetText(application, "Status in Manage Colt page", "CPE_Manage_Status_StatusValue");
	GetText(application, "Last Modification in Manage Colt page", "CPE_Manage_Status_LastModificationValue");
	GetText(application, "Status Link in Manage Colt page", "CPE_Manage_Status_StatusLink");
	GetText(application, "View Interface Link in Manage Colt page", "CPE_Manage_Status_ViewInterfacesLink");
	
	GetText(application, "Device Name in Manage Colt page under Synchronization Panel", "CPE_Manage_Synchronization_DeviceValue");
	GetText(application, "Sync Status in Manage Colt page", "CPE_Manage_Synchronization_SyncStatusValue");
	GetText(application, "Fetch Interfaces in Manage Colt page", "CPE_Manage_Synchronization_FetchInterfacesValue");
	GetText(application, "Synchronize Link in Manage Colt page", "CPE_Manage_Synchronization_SynchronizeLink");

	
	scrollToTop();
	String CPE_Manage_Status_LastModificationValue= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/CPE_Manage_Status_LastModificationValue")));
	if(CPE_Manage_Status_LastModificationValue.contains("GMT"))
	{
		Log.info("Service status is displayed as : " + CPE_Manage_Status_LastModificationValue);
		System.out.println("Last Modification is :"+ CPE_Manage_Status_LastModificationValue);
	}
	else
	{
		Log.info("Incorrect modification time format");
		System.out.println("Incorrect modification time format");
	}
	
	
	////
	click(application, "Status", "CPE_Manage_Status_StatusLink");

	if(CPE_ServiceStatusChangeRequired=="Yes")
	{
		WebElement ServiceStatusPage= getwebelement(xml.getlocator("//locators/" + application + "/CPE_Manage_Status_Servicestatus_popup"));
		if(ServiceStatusPage.isDisplayed())
		{
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/CPE_Device_Status_OK")));
			click(application, "Click on OK to change Status", "CPE_Device_Status_OK");

			WebElement PE_servicestatushistoryValue= getwebelement(xml.getlocator("//locators/" + application + "/CPE_servicestatushistoryValue"));
			try
			{
				if(PE_servicestatushistoryValue.isDisplayed())
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
		//**click(application, "Close", "CPE_servicestatus_popupclose");
	}

	
	
	////synchronize panel in manage colt page
		scrolltoend();
		click(application, "Synchronize", "CPE_Manage_Synchronization_SynchronizeLink");
		compareText(application, "Synchronize Warning Message", "CPE_Manage_SynchronizeWarningMessage", "Error while synchronizing device, Reason is :: null.");
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
			System.out.println( "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
			System.out.println( "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
		}
		
}else {
	ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage COLT's Network - Manage Network' page not navigated");
	System.out.println("'Manage COLT's Network - Manage Network' page not navigated");
}

}catch(NoSuchElementException e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
	System.out.println( "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
}catch(Exception e) {
	e.printStackTrace();
	ExtentTestManager.getTest().log(LogStatus.FAIL, "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
	System.out.println( "Field is not displayed in 'Manage COLT's Network - Manage Network' page");
}
}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "'View CPE Device' page not navigated");
System.out.println("'View CPE Device' page not navigated");
}

	
}



public void testStatus_CPE(String application) throws InterruptedException {
	
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



public void compareText_InViewPage_CPE(String application, String labelname,  String ExpectedText, XMLReader xml) throws InterruptedException, DocumentException {

	String text = null;
	WebElement element = null;
	try {
		Thread.sleep(1000);
		element = getwebelement("//div[label[contains(text(),'"+labelname+"')]]/div[1]");
		String emptyele = element.getText().toString();

		if(element==null)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
			System.out.println(labelname+" not found");
		}
		else if (emptyele!=null && emptyele.isEmpty()) {
//			ExtentTestManager.getTest().log(LogStatus.PASS,  labelname + "' value is empty");
			
			emptyele= "Null";
			
			sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
			
			if(emptyele.equalsIgnoreCase(ExpectedText)) {
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "The Expected value for '"+ labelname +"' field  is same as the Acutal value. It is id displaying blank");
				System.out.println("The Expected value for '\"+ labelname +\"' field  is same as the Acutal value. It is displaying blank");
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				System.out.println(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
			}
			

		}else 
		{   
			text = element.getText();
			if(text.contains("-")) {
				
				String[] actualTextValue=text.split(" ");
				String[] expectedValue =ExpectedText.split(" ");
				
				if(expectedValue[0].equalsIgnoreCase(actualTextValue[0])) {
					ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				}
				else if(expectedValue[0].contains(actualTextValue[0])) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
				
			}else {
				if(ExpectedText.equalsIgnoreCase(text)) {
					ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				}
				else if(ExpectedText.contains(text)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
		System.out.println(labelname + " field is not displaying");
	}

}





public void deleteInterface_CPE1(String application, String interfacename, String name, String editDevicename) throws InterruptedException, DocumentException {
	//Delete Interface
	ScrolltoElement(application, "portalaccess_header", xml);
	scrolltoend();
	//click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
	Thread.sleep(1000);
	WebElement SelectInterface1= driver.findElement(By.xpath("//div[@role='gridcell' and @col-id='interfaceName']/div[text()='"+interfacename+"']"));
	if(SelectInterface1.isDisplayed())
	{
	Clickon(SelectInterface1);
	ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on existing Interface radio button");
	Thread.sleep(1000);

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








public void deleteDevice_CPE(String application, String DeviceName) throws InterruptedException, DocumentException {

	scrolltoend();
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingCPEdevicegrid")).isDisplayed())
	{
		List<WebElement> addeddevicesList= driver.findElements(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b"));
		System.out.println(addeddevicesList);
		int AddedDevicesCount= addeddevicesList.size();
		for(int i=0;i<AddedDevicesCount;i++) {
			String AddedDeviceNameText= addeddevicesList.get(i).getText();
			String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
//			if(AddedDeviceNameText.contains(DeviceName))
//			{
				WebElement AddedDevice_DeletefromserviceLink= driver.findElement(By.xpath("(//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div)[2]/div/div/b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='Delete from Service']"));
				Clickon(AddedDevice_DeletefromserviceLink);								   
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
				compareText(application, "Device delete success message", "successmsg", "Device successfully removed from service.", xml);
//			}
//			else
//			{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "Invalid device name");
//			}
		}
	}
	else
	{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
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
		  
		  Select
		  sel = new Select(el);
		  
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
				Thread.sleep(1000);

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

				//Thread.sleep(2000);
				SendKeys(getwebelement("//div[label[text()='" + labelname + "']]//input"), expectedValueToAdd);
				Thread.sleep(1000);

				Clickon(getwebelement("(//span[contains(text(),'" + expectedValueToAdd + "')])[1]"));
				Thread.sleep(1000);

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

public void PageNavigation_NextPageForInterfaceToselect(String Application)
throws InterruptedException, DocumentException {

Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_nextpage")));
Thread.sleep(3000);

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
			
			
			//Thread.sleep(1000);
			// click on Country dropdown
			WebElement selectDropdownValue = driver.findElement(By.xpath("//div[contains(text(),'" + dropdownToBeSelectedInTheEnd + "')]"));
			System.out.println("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected  '"+lebelname+ "'  is : " + selectDropdownValue.getText().toString());
			Log.info("Selected  '" +lebelname+ "'  is : " + selectDropdownValue.getText().toString());
			selectDropdownValue.click();
			Thread.sleep(2000);
			
		
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

public void EnterTextValue(String application, String value, String labelname, String xpath) {
	WebElement element = null;

	try {
		//Thread.sleep(1000);
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

public void verifyManageService2(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
	//Manage service
	ScrolltoElement(application, "orderpanelheader", xml);
	click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
	click_commonMethod(application, "Manage", "manageLink", xml);
	Thread.sleep(2000);
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
//	verify_SmartsValue(application);
//	verify_FetchInterfacesValue(application);
//	verify_VistamartDeviceValue(application);
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
	
public void RightClick(WebElement el) {
	Actions action = new Actions(driver);
    action.contextClick().build().perform();
}

public void EnterByAction() {
	Actions action = new Actions(driver);
    action.sendKeys(Keys.ENTER).perform();
}

public void click(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
	WebElement element= null;

	try {
		//Thread.sleep(1000);
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

public void WarningMessage(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
	WebElement element= null;
	try {
		//Thread.sleep(1000);
		element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
		//Thread.sleep(2000);
		if(element==null) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
		}
		else
		{
			String WarningMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getText();

			System.out.println("'"+labelname+"' field warning  message displayed as : " + WarningMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step : validation message for '"+labelname+"' text field displayed as : " + WarningMsg);
			Log.info("'"+labelname+"' field warning message displayed as : " + WarningMsg);
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		System.out.println("'"+labelname+"' field warning message is not dipslaying");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' field warning message is not displaying");
	}catch(Exception ed) {
		ed.printStackTrace();
	}
}

public void compareText(String application, String labelname, String xpath, String ExpectedText) throws InterruptedException, DocumentException {

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
					System.out.println(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				}
				else if(expectedValue[0].contains(actualTextValue[0])) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
				
			}else {
				if(ExpectedText.equalsIgnoreCase(text)) {
					ExtentTestManager.getTest().log(LogStatus.PASS," The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println(" The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				}
				else if(ExpectedText.contains(text)) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is same as the Acutal value '"+text+"'");
				
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					System.out.println("The Expected value for '"+ labelname +"' field '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
		System.out.println(labelname + " field is not displaying");
	}

}

public void implicitlyWait(String pageNavigation) {
	driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);			
	}
	
	public void implicitlyWait10S(String pageNavigation) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);			
		}
	public void webdriverWait(String application, String xpath, XMLReader xml) throws DocumentException, InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, 300);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xml.getlocator("//locators/" + application + "/"+ xpath +""))));
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
	}
	
public void clickOnAddInterfaceLink_CMSdata(String application) throws InterruptedException, DocumentException {
		
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Add Interface Functionality");
//		ScrolltoElement(application, "ConfigurationOptions_Header", xml);//ProviderEquipment_header //PE_AddPEDeviceLink
//	click_commonMethod(application, "View Link", "viewservicepage_viewdevicelink1", xml);

		WebElement RoutesPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/RoutesPanelheader"));
		scrolltoview(RoutesPanelHeader);
		Thread.sleep(1000);
		
		click_commonMethod(application, "Action", "InterfacePanel_actionDropdown" , xml);
		
		click_commonMethod(application, "Add Interface/Link", "addInterfaceOrLink" , xml);
		Thread.sleep(2000);
		
	}
	
	
	public void clickOnAddMultilink_CMSdata(String application) throws InterruptedException, DocumentException {
		
		WebElement RoutesPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/RoutesPanelheader"));
		scrolltoview(RoutesPanelHeader);
		Thread.sleep(1000);
		
		click_commonMethod(application, "Action", "InterfacePanel_actionDropdown" , xml);
		
		click_commonMethod(application, "Add Multlink", "addMultilinkLink" , xml);
		Thread.sleep(2000);
		
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

			ScrolltoElement(application, xpath , xml);
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)"); 
			
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


	public void addMultilink_cisco_CMS(String application, String multilink, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, 
			String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSize_ipv4, String cityValue_Ipv4, String existingAddressRangeIPv6selection,
			String existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSize_ipv6, String availableBlocks_Ipv6,  String configureInterfaceOnDevice, String network_ipv4,
			String bandwidth, String vlan, String speed, String duplex, String ivManagement) throws InterruptedException, DocumentException, 
	IOException { 
		
		boolean addressValue=false;
		String expectedAvailablePoolvalue = "None of applicable pools contains a largest available fit which is equal to the selected network size. Please contact RIPEmaster team.";
		waitForpageload();   waitforPagetobeenable();
		
		scrolltoend();
		
		click_commonMethod(application, "OK" , "okButton" , xml);
		
		warningMessage_commonMethod(application, "speed_warningmessage", "Speed", xml);
		warningMessage_commonMethod(application, "duplex_warningMessage", "Duplex", xml);
		
		scrollToTop();
		
		warningMessage_commonMethod(application, "interface_warningMessage" , "Interface", xml);
		
	//Configure Interface on Device	
		addCheckbox_commonMethod(application, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", configureInterfaceOnDevice, "No" , xml);
	
	//Multilink
		addtextFields_commonMethod(application, "Multilink", "multilinktextfield", multilink, xml);
		
	//IPv4 Configuration
		selectValueInsideDropdown(application, "networkDropdown_ipv4", "Network", network_ipv4 , xml);
		
		if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
			
		//EIP Allocation
			click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
			
			String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addMultilink_subnetTypeValue_EIpAllocation")));
			if(subnetTypevalue.equals("XFER")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'XFER' as expected");
				Log.info("Subnet Type value is displaying as 'XFER' as expected");
			}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
				Log.info("No values dipslaying under 'Subnet Type' field");
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
				Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
			}
			
			selectValueInsideDropdown(application, "ipv4_subnetSizeValue" , "Sub Net Size" , subnetSize_ipv4, xml);
			
			isDisplayed(application, "ipv4_cityValue", "City");
			
			
			String availablePools = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availablePool_IPv4_messages")));
			if(availablePools.equals(expectedAvailablePoolvalue)) {
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "No dropdown dipslaying under 'Available pool' field.");
				ExtentTestManager.getTest().log(LogStatus.PASS, "under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
				
				Log.info("No dropdown dipslaying under 'Available pool' field.");
				Log.info("under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
			}
			
			click_commonMethod(application, "x", "EIPallocation_xButton", xml);
			
			Thread.sleep(2000);
			
			waitForpageload();  waitforPagetobeenable();
			click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
			waitForpageload();  waitforPagetobeenable();
			
			org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
			List<WebElement> addressRangeIpv4Dropdownvalue = sec.getOptions();
			
			int r = addressRangeIpv4Dropdownvalue.size();
			if(r<=1) {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv4'");
				Log.info("No value displaying under 'Interface Address Range _ IPv4'");
			}else {
				
				sec.selectByIndex(2);
				String IPv6selectedValue = sec.getFirstSelectedOption().getText();
				
				 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
			}
			
		}
		else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
			
			  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
			
			  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
			  
			  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
			  if(interfaceValueIntextField.isEmpty()) {
				  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv4' text field");
				  System.out.println("No values dipslaying under 'Address_IPv4' text field");
			  }else {
				  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
			  }
		}
		
		

	//IPv6 Configuration
		if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
			
			//EIP Allocation
				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
				
				String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SubnetTypeValue_EIpAlloction")));
				if(subnetTypevalue.equals("WAN")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'WAN' as expected");
					Log.info("Subnet Type value is displaying as 'XFER' as expected");
				}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
					Log.info("No values dipslaying under 'Subnet Type' field");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
					Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
				}
				
				selectValueInsideDropdown(application, "ipV6_subnetSizeDropdown" , "Sub Net Size" , subnetSize_ipv6, xml);
				
				selectValueInsideDropdown(application, "ipv6_availableBlocks" , "Available blocks" , availableBlocks_Ipv6, xml);
				
				click_commonMethod(application, "Allocate Subnet", "addInterface_allocateSubnet", xml);
				clickOnBankPage();
				
				String EIPSubnetAllocation_Header=getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).getText();
				if(EIPSubnetAllocation_Header.equalsIgnoreCase("EIP Subnet Allocation")) {
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				}
				
				waitForpageload();   waitforPagetobeenable();
				
				click_commonMethod(application, "Get Address", "getAddress_IPv6", xml);
				waitForpageload();  waitforPagetobeenable();
				
				org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")));
				List<WebElement> addressRangeIpv6Dropdownvalue = sec.getOptions();
				
				int r = addressRangeIpv6Dropdownvalue.size();
				if(r<=1) {
					ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv6'");
					Log.info("No value displaying under 'Interface Address Range _ IPv6'");
				}else {
					
					sec.selectByIndex(2);
					String IPv6selectedValue = sec.getFirstSelectedOption().getText();
					
					 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				}
				
				
			}
			else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
				
				  addtextFields_commonMethod(application, "Interface Address Range IPV6", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
					  System.out.println("No values dipslaying under 'Address_IPv6' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
		
	
	WebElement networkIPv6=getwebelement(xml.getlocator("//locators/" + application + "/addMultilink_networkField_Ipv6"));
	scrolltoview(networkIPv6);
		
		String expectedLinkvalue = "Multilink" + multilink;
		String linktextFieldValue = getwebelement(xml.getlocator("//locators/" + application + "/Link_textField")).getAttribute("value");
		if(linktextFieldValue.equals(expectedLinkvalue)) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Link text field value dipslaying as " + linktextFieldValue + " as expected");
			Log.info("Link text field value dipslaying as " + linktextFieldValue + " as expected");
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Link text field value mismatches. It is displaying as " + linktextFieldValue+ " .The expected value is: " + expectedLinkvalue);
			Log.info("Link text field value mismatches. It is displaying as " + linktextFieldValue+ " .The expected value is: " + expectedLinkvalue);
		}
	
		//bandwidth
		selectValueInsideDropdown(application, "addInterface_bandwidthDropdown", "Bandwidth", bandwidth, xml);
		
		//VLAN Id
		addtextFields_commonMethod(application, "VLAN Id", "addInterface_VLANid", vlan, xml);
		
		//Speed
		selectValueInsideDropdown(application, "addInterface_speedDropdown", "Speed", speed, xml);
		
		//Duplex
		selectValueInsideDropdown(application, "addInterface_duplexDropdown", "Duplex", duplex, xml);
		
		
		//IV Management
		addCheckbox_commonMethod(application, "ivManagementCheckbox", "IV Management", ivManagement, "No" , xml);
		
		
		scrollToTop();
		Thread.sleep(1000);
		boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configureInterfaceOnDevice_checkbox")).isSelected();
		
		scrolltoend();
		Thread.sleep(1000);
		
		WebElement multilinkBeraerPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/mulilinBeraerPanelHeader"));
		scrolltoview(multilinkBeraerPanelHeader);
		
	if(configureInterfaceSelection) {	
		//perform Generate configuration
		boolean configurationpanel=false;
		configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
		if(configurationpanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
			System.out.println("'Configuration' panel is displaying");
			
			click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
			Thread.sleep(2000);
			
			waitForpageload();   waitforPagetobeenable();
			scrolltoend();
			Thread.sleep(1000);
			
			String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
			if(configurationvalues.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				System.out.println("After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
			}
		
			Thread.sleep(2000);
			scrolltoend();
			click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			System.out.println("'Configuration' panel is not displaying");
		}
	  }else {
		  scrolltoend();
			click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
	  }
	}

	
	/**
	 * 	
	 * @param application
	 * @throws DocumentException 
	 * @throws InterruptedException 
	 */
		public void clickOnBreadCrump(String application, String breadCrumpLink) throws InterruptedException, DocumentException {
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(2000);
			WebElement breadcrumb=null;
	
			try {
			breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
			if(breadcrumb.isDisplayed()) {
				click_commonMethod_PassingWebelementDirectly(application, "Breadcrump", "breadcrump", xml);
				Thread.sleep(3000);
			}else {
				System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Breadcrumb is not displaying for the element "+ breadcrumb);
		}
	}
		
		
		public void clickOnEditInterface(String application, String interfaceName) throws InterruptedException, DocumentException {
			
			waitForpageload();  waitforPagetobeenable();
			
			WebElement configurationOptionPanel = getwebelement(xml.getlocator("//locators/" + application + "/configurationOptionPanel"));
			scrolltoview(configurationOptionPanel);
			Thread.sleep(1000);
			
			WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectInterfaceValueUnderDistributionSwitch").replace("value", interfaceName));
			Clickon(interfaceValue);
			
			click_commonMethod(application, "Action", "distributionSwitch_ActionDropdown", xml);
			click_commonMethod(application, "Edit", "distributionSwitch_editLink", xml);
			Thread.sleep(1000);
			waitForpageload();   waitforPagetobeenable();
			
		}
		
		
		public void clickOnEditInterface_accessSwitch(String application, String interfaceName) throws InterruptedException, DocumentException {
			
			waitForpageload();  waitforPagetobeenable();
			
			scrolltoend();	Thread.sleep(1000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/distributionSwitch_ActionDropdown")));
			
			WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderAccessSwitchPanel").replace("value", interfaceName));
			Clickon(interfaceValue);
			
			click_commonMethod(application, "Action", "accessSwitch_ActionDropdown", xml);
			click_commonMethod(application, "Edit", "distributionSwitch_editLink", xml);
			Thread.sleep(1000);
			waitForpageload();   waitforPagetobeenable();
			
		}
		
		
		
		
		

		public void clickOnEditMultilink(String application, String interfaceName) throws InterruptedException, DocumentException {
			
			waitForpageload();  waitforPagetobeenable();
			
			WebElement configurationOptionPanel = getwebelement(xml.getlocator("//locators/" + application + "/configurationOptionPanel"));
			scrolltoview(configurationOptionPanel);
			Thread.sleep(1000);
			
			WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectMultilinkValueunderDistributionSwitch").replace("value", interfaceName));
			Clickon(interfaceValue);
			
			click_commonMethod(application, "Action", "distributionSwitch_ActionDropdown", xml);
			click_commonMethod(application, "Edit", "distributionSwitch_editLink", xml);
			Thread.sleep(1000);
			waitForpageload();   waitforPagetobeenable();
			
		}
		
		
		public void clickOnEditMultilink_AccessSWitch(String application, String interfaceName) throws InterruptedException, DocumentException {
			
			waitForpageload();  waitforPagetobeenable();
			try {
			scrolltoend();
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/distributionSwitch_ActionDropdown")));
			Thread.sleep(1000);
			WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectMultilinkUnderAccessSwitch").replace("value", interfaceName));
			Clickon(interfaceValue);
			
			click_commonMethod(application, "Action", "accessSwitch_ActionDropdown", xml);
			click_commonMethod(application, "Edit", "distributionSwitch_editLink", xml);
			Thread.sleep(1000);
			waitForpageload();   waitforPagetobeenable();
			
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
		
		
		public void deleteMultilink_AccessSwitch(String application, String multilinkInterfaceName) throws InterruptedException, DocumentException {
			
			waitForpageload();  waitforPagetobeenable();
			
			scrolltoend();
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/distributionSwitch_ActionDropdown")));
			Thread.sleep(1000);
			WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectMultilinkUnderAccessSwitch").replace("value", multilinkInterfaceName));
			Clickon(interfaceValue);
			
			click_commonMethod(application, "Action", "accessSwitch_ActionDropdown", xml);
			click_commonMethod(application, "Edit", "acessSwitch_deleteLink", xml);
			Thread.sleep(1000);
			waitForpageload();   waitforPagetobeenable();
			
		}
		
		
		public void deleteInterface_Cisco_CMS(String application, String interfaceNameCreated, String interfaceNameEdited) throws InterruptedException, DocumentException {
			
			waitForpageload();  waitforPagetobeenable();
			
			String interfaceValueToBeDeleted = "null";
			
			if(interfaceNameEdited.equalsIgnoreCase("Null")) {
				interfaceValueToBeDeleted = interfaceNameCreated;
			}else {
				interfaceValueToBeDeleted = interfaceNameEdited;
			}
			
			WebElement configurationOptionPanel = getwebelement(xml.getlocator("//locators/" + application + "/configurationOptionPanel"));
			scrolltoview(configurationOptionPanel);
			Thread.sleep(1000);
			
			WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectInterfaceValueUnderDistributionSwitch").replace("value", interfaceValueToBeDeleted));
			Clickon(interfaceValue);
			
			click_commonMethod(application, "Action", "distributionSwitch_ActionDropdown", xml);
			click_commonMethod(application, "Edit", "distributionSwitch_resetlink", xml);
			Thread.sleep(1000);
			
			driver.switchTo().alert().accept();
			
			verifysuccessmessage(application, "Interface successfully removed from this service.");
			
		}
		
		
		public void deleteInterface_AccessSwitch_CMS(String application, String interfaceNameCreated, String interfaceNameEdited) throws InterruptedException, DocumentException {
			
			waitForpageload();  waitforPagetobeenable();
			
			String interfaceValueToBeDeleted = "null";
			
			if(interfaceNameEdited.equalsIgnoreCase("Null")) {
				interfaceValueToBeDeleted = interfaceNameCreated;
			}else {
				interfaceValueToBeDeleted = interfaceNameEdited;
			}
			
			scrolltoend();
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/distributionSwitch_ActionDropdown")));
			Thread.sleep(1000);
			WebElement interfaceValue = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderAccessSwitchPanel").replace("value", interfaceValueToBeDeleted));
			Clickon(interfaceValue);
			
			click_commonMethod(application, "Action", "accessSwitch_ActionDropdown", xml);
			click_commonMethod(application, "Edit", "acessSwitch_deleteLink", xml);
			Thread.sleep(1000);
			
			click_commonMethod(application, "deleteButton under Popup", "deleteButtonInsidePopup", xml);
			Thread.sleep(6000);
			waitforPagetobeenable();
			
			verifysuccessmessage(application, "Interface successfully removed from this service.");
			
		}
		
		
		
		
		public void editInterface_Cisco_CMSdata(String application, String interfaceValue, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, 
				String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSize_ipv4, String cityValue_Ipv4, String existingAddressRangeIPv6selection,
				String existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSize_ipv6, String availableBlocks_Ipv6,  String configureInterfaceOnDevice, String network_ipv4,
				String link, String bearerType,String bandwidth, String vlan, String speed, String duplex, String ivManagement, String vrrpIP, String vrrpGroupNumber, String vrrpTrackNumber, String vrrpPassword, String vrrpIPv6, String interfaceNode) throws InterruptedException, DocumentException, 
		IOException { 
			
			boolean addressValue=false;
			String expectedAvailablePoolvalue = "None of applicable pools contains a largest available fit which is equal to the selected network size. Please contact RIPEmaster team.";
			waitForpageload();   waitforPagetobeenable();
			
			scrollToTop();
			
		//Configure Interface on Device	
			editcheckbox_commonMethod(application, configureInterfaceOnDevice, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", xml);
		
		//Interface
			edittextFields_commonMethod(application, "Interface", "interfaceTextField", interfaceValue, xml);
			
		//IPv4 Configuration
			selectValueInsideDropdown(application, "networkDropdown_ipv4", "Network", network_ipv4 , xml);
			
			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
				
			//EIP Allocation
				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
				
				String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SubnetTypeValue_EIpAlloction")));
				if(subnetTypevalue.equals("XFER")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'XFER' as expected");
					Log.info("Subnet Type value is displaying as 'XFER' as expected");
				}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
					Log.info("No values dipslaying under 'Subnet Type' field");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
					Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
				}
				
				selectValueInsideDropdown(application, "ipv4_subnetSizeValue" , "Sub Net Size" , subnetSize_ipv4, xml);
				
				selectValueInsideDropdown(application, "ipv4_cityValue" , "City", cityValue_Ipv4, xml);
				
				Thread.sleep(6000);
				
				String availablePools = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availablePool_IPv4_messages")));
				if(availablePools.equals(expectedAvailablePoolvalue)) {
					
					ExtentTestManager.getTest().log(LogStatus.INFO, "No dropdown dipslaying under 'Available pool' field.");
					ExtentTestManager.getTest().log(LogStatus.PASS, "under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
					
					Log.info("No dropdown dipslaying under 'Available pool' field.");
					Log.info("under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
				}
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				Thread.sleep(2000);
				
				waitForpageload();  waitforPagetobeenable();
				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
				waitForpageload();  waitforPagetobeenable();
				
				org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
				List<WebElement> addressRangeIpv4Dropdownvalue = sec.getOptions();
				
				int r = addressRangeIpv4Dropdownvalue.size();
				if(r<=1) {
					ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv4'");
					Log.info("No value displaying under 'Interface Address Range _ IPv4'");
				}else {
					
					sec.selectByIndex(2);
					String IPv6selectedValue = sec.getFirstSelectedOption().getText();
					
					 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				}
				
			}
			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
				
				edittextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv4' text field");
					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
			
			
		//IPv6 Configuration
			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SubnetTypeValue_EIpAlloction")));
					if(subnetTypevalue.equals("WAN")) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'WAN' as expected");
						Log.info("Subnet Type value is displaying as 'XFER' as expected");
					}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
						Log.info("No values dipslaying under 'Subnet Type' field");
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
						Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
					}
					
					selectValueInsideDropdown(application, "ipV6_subnetSizeDropdown" , "Sub Net Size" , subnetSize_ipv6, xml);
					
					selectValueInsideDropdown(application, "ipv6_availableBlocks" , "Available blocks" , availableBlocks_Ipv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "addInterface_allocateSubnet", xml);
					clickOnBankPage();
					
					waitForpageload();   waitforPagetobeenable();
					
					click_commonMethod(application, "Get Address", "getAddress_IPv6", xml);
					waitForpageload();  waitforPagetobeenable();
					
					org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")));
					List<WebElement> addressRangeIpv6Dropdownvalue = sec.getOptions();
					
					int r = addressRangeIpv6Dropdownvalue.size();
					if(r<=1) {
						ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv6'");
						Log.info("No value displaying under 'Interface Address Range _ IPv6'");
					}else {
						
						sec.selectByIndex(2);
						String IPv6selectedValue = sec.getFirstSelectedOption().getText();
						
						 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
					}
					
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  edittextFields_commonMethod(application, "Interface Address Range IPV6", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
			
		
		WebElement networkIPv6=getwebelement(xml.getlocator("//locators/" + application + "/networkDropdown_Ipv6"));
		scrolltoview(networkIPv6);
			
		//Link Text Field
			edittextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
			
		//Bearer Type Dropdown
			selectValueInsideDropdown(application, "addInterface_bearerTypeDropdown" , "Bearer Type", bearerType, xml);
			
			org.openqa.selenium.support.ui.Select sel = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_bearerTypeDropdown")));
			String bearerTypeSelected = sel.getFirstSelectedOption().getText();
			
			if(bearerTypeSelected.equalsIgnoreCase("Ethernet-VLAN")) {
				
				edittextFields_commonMethod(application, "VRRP IP", "addInterface_VRRPip", vrrpIP, xml);   //VRRP IP
				
				edittextFields_commonMethod(application, "VRRP Group Number", "addInterface_vrrpgroupNumber", vrrpGroupNumber, xml);	//VRRP Group Number
				
				edittextFields_commonMethod(application, "VRRP Track Number", "addInterface_vrrpTrackNumber", vrrpTrackNumber, xml);	//VRRP Track Number
				
				edittextFields_commonMethod(application, "VRRP Password", "addInterface_vrrpPassword", vrrpPassword, xml);	//VRRP Password
				
				edittextFields_commonMethod(application, "VRRP IPV6", "addInterface_vrrpIPv6", vrrpIPv6, xml);	//VRRP IPV6
				
				selectValueInsideDropdown(application, "addInterface_interfaceNode", "Interface Node", interfaceNode, xml);	//Interface Node
			}
			else {
				
				selectValueInsideDropdown(application, "addInterface_speedDropdown", "Speed", speed, xml);	//Speed
  				
  				selectValueInsideDropdown(application, "addInterface_duplexDropdown", "Duplex", duplex, xml);	//duplex
			}
			
			//bandwidth
			selectValueInsideDropdown(application, "addInterface_bandwidthDropdown", "Bandwidth", bandwidth, xml);
			
			//VLAN Id
			edittextFields_commonMethod(application, "VLAN Id", "addInterface_VLANid", vlan, xml);
			
			//IV Management
			editcheckbox_commonMethod(application, ivManagement, "ivManagementCheckbox" , "IV Management", xml);
			
			
			scrollToTop();
			Thread.sleep(1000);
			boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configureInterfaceOnDevice_checkbox")).isSelected();
			
			scrolltoend();
			Thread.sleep(1000);
			
			
		if(configureInterfaceSelection) {	
			//perform Generate configuration
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				waitForpageload();   waitforPagetobeenable();
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
			
				Thread.sleep(2000);
				scrolltoend();
				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
				 verifysuccessmessage(application, "Interface successfully updated.");

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
			}
		  }else {
			  scrolltoend();
				click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
				
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
				    verifysuccessmessage(application, "Interface successfully updated.");
				  }catch(Exception e) {
				     e.printStackTrace();
				  } 
		  }
		}
		
		
		public void editMultilink_cisco_CMS(String application, String multilink, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, 
				String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSize_ipv4, String cityValue_Ipv4, String existingAddressRangeIPv6selection,
				String existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSize_ipv6, String availableBlocks_Ipv6,  String configureInterfaceOnDevice, String network_ipv4,
				String bandwidth, String vlan, String speed, String duplex, String ivManagement) throws InterruptedException, DocumentException, 
		IOException { 
			
			try {
			boolean addressValue=false;
			String expectedAvailablePoolvalue = "None of applicable pools contains a largest available fit which is equal to the selected network size. Please contact RIPEmaster team.";
			waitForpageload();   waitforPagetobeenable();
			scrollToTop();
			
		//Configure Interface on Device	
			editcheckbox_commonMethod(application, configureInterfaceOnDevice, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", xml);
			
		//Multilink
			edittextFields_commonMethod(application, "Multilink", "multilinktextfield", multilink, xml);
			
		//IPv4 Configuration
			selectValueInsideDropdown(application, "networkDropdown_ipv4", "Network", network_ipv4 , xml);
			
			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
				
			//EIP Allocation
				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
				
				String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addMultilink_subnetTypeValue_EIpAllocation")));
				if(subnetTypevalue.equals("XFER")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'XFER' as expected");
					Log.info("Subnet Type value is displaying as 'XFER' as expected");
				}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
					Log.info("No values dipslaying under 'Subnet Type' field");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
					Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
				}
				
				selectValueInsideDropdown(application, "ipv4_subnetSizeValue" , "Sub Net Size" , subnetSize_ipv4, xml);
				
				selectValueInsideDropdown(application, "ipv4_cityValue" , "City", cityValue_Ipv4, xml);
				
				
				String availablePools = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availablePool_IPv4_messages")));
				if(availablePools.equals(expectedAvailablePoolvalue)) {
					
					ExtentTestManager.getTest().log(LogStatus.INFO, "No dropdown dipslaying under 'Available pool' field.");
					ExtentTestManager.getTest().log(LogStatus.PASS, "under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
					
					Log.info("No dropdown dipslaying under 'Available pool' field.");
					Log.info("under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
				}
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				Thread.sleep(2000);
				
				waitForpageload();  waitforPagetobeenable();
				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
				waitForpageload();  waitforPagetobeenable();
				
				org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
				List<WebElement> addressRangeIpv4Dropdownvalue = sec.getOptions();
				
				int r = addressRangeIpv4Dropdownvalue.size();
				if(r<=1) {
					ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv4'");
					Log.info("No value displaying under 'Interface Address Range _ IPv4'");
				}else {
					
					sec.selectByIndex(2);
					String IPv6selectedValue = sec.getFirstSelectedOption().getText();
					
					 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				}
				
			}
			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
				
				  edittextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
				
				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
				  
				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
				  if(interfaceValueIntextField.isEmpty()) {
					  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv4' text field");
					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
				  }
			}
			
			

		//IPv6 Configuration
			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
				
				//EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
					
					String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SubnetTypeValue_EIpAlloction")));
					if(subnetTypevalue.equals("WAN")) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'WAN' as expected");
						Log.info("Subnet Type value is displaying as 'XFER' as expected");
					}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
						Log.info("No values dipslaying under 'Subnet Type' field");
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
						Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
					}
					
					selectValueInsideDropdown(application, "ipV6_subnetSizeDropdown" , "Sub Net Size" , subnetSize_ipv6, xml);
					
					selectValueInsideDropdown(application, "ipv6_availableBlocks" , "Available blocks" , availableBlocks_Ipv6, xml);
					
					click_commonMethod(application, "Allocate Subnet", "addInterface_allocateSubnet", xml);
					clickOnBankPage();
					
					waitForpageload();   waitforPagetobeenable();
					
					click_commonMethod(application, "Get Address", "getAddress_IPv6", xml);
					waitForpageload();  waitforPagetobeenable();
					
					org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")));
					List<WebElement> addressRangeIpv6Dropdownvalue = sec.getOptions();
					
					int r = addressRangeIpv6Dropdownvalue.size();
					if(r<=1) {
						ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv6'");
						Log.info("No value displaying under 'Interface Address Range _ IPv6'");
					}else {
						
						sec.selectByIndex(2);
						String IPv6selectedValue = sec.getFirstSelectedOption().getText();
						
						 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
					}
					
					
				}
				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
					
					  edittextFields_commonMethod(application, "Interface Address Range IPV6", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
					
					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
					  
					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
					  if(interfaceValueIntextField.isEmpty()) {
						  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv6' text field");
						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
					  }else {
						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
					  }
				}
			
		
		WebElement networkIPv6=getwebelement(xml.getlocator("//locators/" + application + "/addMultilink_networkField_Ipv6"));
		scrolltoview(networkIPv6);
			
			//bandwidth
			selectValueInsideDropdown(application, "addInterface_bandwidthDropdown", "Bandwidth", bandwidth, xml);
			
			//VLAN Id
			edittextFields_commonMethod(application, "VLAN Id", "addInterface_VLANid", vlan, xml);
			
			//Speed
			selectValueInsideDropdown(application, "addInterface_speedDropdown", "Speed", speed, xml);
			
			//Duplex
			selectValueInsideDropdown(application, "addInterface_duplexDropdown", "Duplex", duplex, xml);
			
			
			//IV Management
			editcheckbox_commonMethod(application, ivManagement, "ivManagementCheckbox", "IV Management", xml);
			
			
			scrollToTop();
			Thread.sleep(1000);
			boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configureInterfaceOnDevice_checkbox")).isSelected();
			
			scrolltoend();
			Thread.sleep(1000);
			
		if(configureInterfaceSelection) {	
			//perform Generate configuration
			boolean configurationpanel=false;
			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
			if(configurationpanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				System.out.println("'Configuration' panel is displaying");
				
				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
				Thread.sleep(2000);
				
				waitForpageload();   waitforPagetobeenable();
				scrolltoend();
				Thread.sleep(1000);
				
				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
				if(configurationvalues.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
					System.out.println("After clicking on 'Generate' button, "
							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
				}
			
				Thread.sleep(2000);
				scrolltoend();
				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
				System.out.println("'Configuration' panel is not displaying");
			}
		  }else {
			  scrolltoend();
				click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
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
        		
        	   waitforPagetobeenable();
        		scrollToTop();
        		Thread.sleep(1000);
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
           
           /**
      		 *   For Dropdown common method _  click on dropdown field
      		 * @param application
      		 * @param labelname
      		 * @param xpath
      		 * @param expectedValueToAdd
      		 * @param xml
      		 * @throws InterruptedException
      		 * @throws DocumentException
      		 */
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
      					  Thread.sleep(3000);
      					  
      					  //verify list of values inside dropdown
      					  List<WebElement> listofvalues = driver
      								.findElements(By.xpath("//div[@class='sc-bxivhb kqVrwh']"));
      					  
      					  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
      					  System.out.println( " List of values inside "+ labelname + " dropdown is:  ");
      					  
      						for (WebElement valuetypes : listofvalues) {
      									Log.info("service sub types : " + valuetypes.getText());
      									 ls.add(valuetypes.getText());
      						}
      						
      						Thread.sleep(1000);
      						    ExtentTestManager.getTest().log(LogStatus.PASS, "list of values inside "+labelname+" dropdown is: "+ls);
      				            System.out.println("list of values inside "+labelname+" dropdown is: "+ls);
      						
      						Thread.sleep(2000);
      					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
      					Thread.sleep(2000);
      					  Clickon(getwebelement("(//div[label[text()='"+ labelname +"']]//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
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
      		
      		
      		public void addInterface_Cisco_CMSdata(String application, String interfaceValue, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, 
      				String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSize_ipv4, String cityValue_Ipv4, String existingAddressRangeIPv6selection,
      				String existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSize_ipv6, String availableBlocks_Ipv6,  String configureInterfaceOnDevice, String network_ipv4,
      				String link, String bearerType,String bandwidth, String vlan, String speed, String duplex, String ivManagement, String vrrpIP, String vrrpGroupNumber, String vrrpTrackNumber, String vrrpPassword, String vrrpIPv6, String interfaceNode) throws InterruptedException, DocumentException, 
      		IOException { 
      			
      			boolean addressValue=false;
      			String expectedAvailablePoolvalue = "None of applicable pools contains a largest available fit which is equal to the selected network size. Please contact RIPEmaster team.";
      			waitForpageload();   waitforPagetobeenable();
      			
      			scrolltoend();
      			
      			click_commonMethod(application, "OK" , "okButton" , xml);
      			
      			warningMessage_commonMethod(application, "speed_warningmessage", "Speed", xml);
      			warningMessage_commonMethod(application, "duplex_warningMessage", "Duplex", xml);
      			
      			scrollToTop();
      			
      			warningMessage_commonMethod(application, "interface_warningMessage" , "Interface", xml);
      			
      		//Configure Interface on Device	
      			addCheckbox_commonMethod(application, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", configureInterfaceOnDevice, "No" , xml);
      		
      		//Interface
      			addtextFields_commonMethod(application, "Interface", "interfaceTextField", interfaceValue, xml);
      			
      		//IPv4 Configuration
      			selectValueInsideDropdown(application, "networkDropdown_ipv4", "Network", network_ipv4 , xml);
      			
      			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
      				
      			//EIP Allocation
      				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
      				
      				String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SubnetTypeValue_EIpAlloction")));
      				if(subnetTypevalue.equals("XFER")) {
      					ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'XFER' as expected");
      					Log.info("Subnet Type value is displaying as 'XFER' as expected");
      				}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
      					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
      					Log.info("No values dipslaying under 'Subnet Type' field");
      					
      				}else {
      					ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      					Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      				}
      				
      				selectValueInsideDropdown(application, "ipv4_subnetSizeValue" , "Sub Net Size" , subnetSize_ipv4, xml);
      				
      				selectValueInsideDropdown(application, "ipv4_cityValue" , "City", cityValue_Ipv4, xml);
      				
      				Thread.sleep(6000);
      				
      				String availablePools = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availablePool_IPv4_messages")));
      				if(availablePools.equals(expectedAvailablePoolvalue)) {
      					
      					ExtentTestManager.getTest().log(LogStatus.INFO, "No dropdown dipslaying under 'Available pool' field.");
      					ExtentTestManager.getTest().log(LogStatus.PASS, "under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
      					
      					Log.info("No dropdown dipslaying under 'Available pool' field.");
      					Log.info("under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
      				}
      				
      				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
      				
      				Thread.sleep(2000);
      				
      				waitForpageload();  waitforPagetobeenable();
      				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
      				waitForpageload();  waitforPagetobeenable();
      				
      				org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
      				List<WebElement> addressRangeIpv4Dropdownvalue = sec.getOptions();
      				
      				int r = addressRangeIpv4Dropdownvalue.size();
      				if(r<=1) {
      					ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv4'");
      					Log.info("No value displaying under 'Interface Address Range _ IPv4'");
      				}else {
      					
      					sec.selectByIndex(2);
      					String IPv6selectedValue = sec.getFirstSelectedOption().getText();
      					
      					 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
      				}
      				
      			}
      			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
      				
      				  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
      				
      				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
      				  
      				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
      				  if(interfaceValueIntextField.isEmpty()) {
      					  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv4' text field");
      					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
      				  }else {
      					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
      					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
      				  }
      			}
      			
      			

      		//IPv6 Configuration
      			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
      				
      				//EIP Allocation
      					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
      					
      					String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SubnetTypeValue_EIpAlloction")));
      					if(subnetTypevalue.equals("WAN")) {
      						ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'WAN' as expected");
      						Log.info("Subnet Type value is displaying as 'XFER' as expected");
      					}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
      						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
      						Log.info("No values dipslaying under 'Subnet Type' field");
      						
      					}else {
      						ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      						Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      					}
      					
      					selectValueInsideDropdown(application, "ipV6_subnetSizeDropdown" , "Sub Net Size" , subnetSize_ipv6, xml);
      					
      					selectValueInsideDropdown(application, "ipv6_availableBlocks" , "Available blocks" , availableBlocks_Ipv6, xml);
      					
      					click_commonMethod(application, "Allocate Subnet", "addInterface_allocateSubnet", xml);
      					clickOnBankPage();
      					
      					waitForpageload();   waitforPagetobeenable();
      					
      					click_commonMethod(application, "Get Address", "getAddress_IPv6", xml);
      					waitForpageload();  waitforPagetobeenable();
      					
      					org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")));
      					List<WebElement> addressRangeIpv6Dropdownvalue = sec.getOptions();
      					
      					int r = addressRangeIpv6Dropdownvalue.size();
      					if(r<=1) {
      						ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv6'");
      						Log.info("No value displaying under 'Interface Address Range _ IPv6'");
      					}else {
      						
      						sec.selectByIndex(2);
      						String IPv6selectedValue = sec.getFirstSelectedOption().getText();
      						
      						 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
      					}
      					
      					
      				}
      				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
      					
      					  addtextFields_commonMethod(application, "Interface Address Range IPV6", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
      					
      					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
      					  
      					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
      					  if(interfaceValueIntextField.isEmpty()) {
      						  ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Address_IPv6' text field");
      						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
      					  }else {
      						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
      						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
      					  }
      				}
      			
      		
      		WebElement networkIPv6=getwebelement(xml.getlocator("//locators/" + application + "/networkDropdown_Ipv6"));
      		scrolltoview(networkIPv6);
      			
      		//Link Text Field
      			addtextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
      			
      		//Bearer Type Dropdown
      			selectValueInsideDropdown(application, "addInterface_bearerTypeDropdown" , "Bearer Type", bearerType, xml);
      			
      			
      			if(bearerType.equalsIgnoreCase("Ethernet-VLAN")) {
      				
      				selectValueInsideDropdown(application, "addInterface_speedDropdown", "Speed", speed, xml);  //Speed
      				
      				selectValueInsideDropdown(application, "addInterface_duplexDropdown", "Duplex", duplex, xml);	//Duplex
      				
      				addtextFields_commonMethod(application, "VRRP IP", "addInterface_VRRPip", vrrpIP, xml);   //VRRP IP
      				
      				addtextFields_commonMethod(application, "VRRP Group Number", "addInterface_vrrpgroupNumber", vrrpGroupNumber, xml);	//VRRP Group Number
      				
      				addtextFields_commonMethod(application, "VRRP Track Number", "addInterface_vrrpTrackNumber", vrrpTrackNumber, xml);	//VRRP Track Number
      				
      				addtextFields_commonMethod(application, "VRRP Password", "addInterface_vrrpPassword", vrrpPassword, xml);	//VRRP Password
      				
      				addtextFields_commonMethod(application, "VRRP IPV6", "addInterface_vrrpIPv6", vrrpIPv6, xml);	//VRRP IPV6
      				
      				selectValueInsideDropdown(application, "addInterface_interfaceNode", "Interface Node", interfaceNode, xml);	//Interface Node
      			}
      			else {
      				//Speed
      				selectValueInsideDropdown(application, "addInterface_speedDropdown", "Speed", speed, xml);
      				
      				//Duplex
      				selectValueInsideDropdown(application, "addInterface_duplexDropdown", "Duplex", duplex, xml);
      				
      			}
      			
      			//bandwidth
      			selectValueInsideDropdown(application, "addInterface_bandwidthDropdown", "Bandwidth", bandwidth, xml);
      			
      			//VLAN Id
      			addtextFields_commonMethod(application, "VLAN Id", "addInterface_VLANid", vlan, xml);
      			
      			//IV Management
      			addCheckbox_commonMethod(application, "ivManagementCheckbox", "IV Management", ivManagement, "No" , xml);
      			
      			
      			scrollToTop();
      			Thread.sleep(1000);
      			boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configureInterfaceOnDevice_checkbox")).isSelected();
      			
      			scrolltoend();
      			Thread.sleep(1000);
      			
      			
      		if(configureInterfaceSelection) {	
      			//perform Generate configuration
      			boolean configurationpanel=false;
      			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
      			if(configurationpanel) {
      				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
      				System.out.println("'Configuration' panel is displaying");
      				
      				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
      				Thread.sleep(2000);
      				
      				waitForpageload();   waitforPagetobeenable();
      				scrolltoend();
      				Thread.sleep(1000);
      				
      				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
      				if(configurationvalues.isEmpty()) {
      					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
      					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
      				}else {
      					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
      							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
      					System.out.println("After clicking on 'Generate' button, "
      							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
      				}
      			
      				Thread.sleep(2000);
      				scrolltoend();
      				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);

      			}else {
      				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
      				System.out.println("'Configuration' panel is not displaying");
      			}
      		  }else {
      			  scrolltoend();
      				click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
      		  }

      		}	

      		
      		public void addInterface_Cisco_CMSdata_accessSwitch(String application, String interfaceValue, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, 
      				String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSize_ipv4, String cityValue_Ipv4, String existingAddressRangeIPv6selection,
      				String existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSize_ipv6, String availableBlocks_Ipv6,  String configureInterfaceOnDevice, String network_ipv4,
      				String link, String bearerType,String bandwidth, String vlan, String speed, String duplex, String ivManagement, String vrrpIP, String vrrpGroupNumber, String vrrpTrackNumber, String vrrpPassword, String vrrpIPv6, String interfaceNode) throws InterruptedException, DocumentException, 
      		IOException { 
      			
      			boolean addressValue=false;
      			String expectedAvailablePoolvalue = "None of applicable pools contains a largest available fit which is equal to the selected network size. Please contact RIPEmaster team.";
      			waitForpageload();   waitforPagetobeenable();
      			
      			scrolltoend();
      			
      			click_commonMethod(application, "OK" , "okButton" , xml);
      			
      			warningMessage_commonMethod(application, "speed_warningmessage", "Speed", xml);
      			warningMessage_commonMethod(application, "duplex_warningMessage", "Duplex", xml);
      			
      			scrollToTop();
      			
      			warningMessage_commonMethod(application, "interface_warningMessage" , "Interface", xml);
      			
      		//Configure Interface on Device	
      			addCheckbox_commonMethod(application, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", configureInterfaceOnDevice, "No" , xml);
      		
      		//Interface
      			addtextFields_commonMethod(application, "Interface", "interfaceTextField", interfaceValue, xml);
      			
      		//IPv4 Configuration
      			selectValueInsideDropdown(application, "networkDropdown_ipv4", "Network", network_ipv4 , xml);
      			
      			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
      				
      			//EIP Allocation
      				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
      				
      				String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SubnetTypeValue_EIpAlloction")));
      				if(subnetTypevalue.equals("XFER")) {
      					ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'XFER' as expected");
      					Log.info("Subnet Type value is displaying as 'XFER' as expected");
      				}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
      					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
      					Log.info("No values dipslaying under 'Subnet Type' field");
      					
      				}else {
      					ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      					Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      				}
      				
      				//*selectValueInsideDropdown(application, "ipv4_subnetSizeValue" , "Sub Net Size" , subnetSize_ipv4, xml);
      				GetText(application, "Sub Net Size", "ipv4_subnetSizeValue");
      				
      				//*selectValueInsideDropdown(application, "ipv4_cityValue" , "City", cityValue_Ipv4, xml);
      				GetText(application, "City", "ipv4_cityValue");
      				
      				Thread.sleep(6000);
      				
      				String availablePools = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availablePool_IPv4_messages")));
      				if(availablePools.equals(expectedAvailablePoolvalue)) {
      					
      					ExtentTestManager.getTest().log(LogStatus.INFO, "No dropdown dipslaying under 'Available pool' field.");
      					ExtentTestManager.getTest().log(LogStatus.PASS, "under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
      					
      					Log.info("No dropdown dipslaying under 'Available pool' field.");
      					Log.info("under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
      				}
      				
      				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
      				
      				Thread.sleep(2000);
      				
      				waitForpageload();  waitforPagetobeenable();
      				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
      				waitForpageload();  waitforPagetobeenable();
      				
      				org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
      				List<WebElement> addressRangeIpv4Dropdownvalue = sec.getOptions();
      				
      				int r = addressRangeIpv4Dropdownvalue.size();
      				if(r<=1) {
      					ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv4'");
      					Log.info("No value displaying under 'Interface Address Range _ IPv4'");
      				}else {
      					
      					sec.selectByIndex(2);
      					String IPv6selectedValue = sec.getFirstSelectedOption().getText();
      					
      					 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
      				}
      				
      			}
      			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
      				
      				  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
      				
      				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
      				  
      				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
      				  if(interfaceValueIntextField.isEmpty()) {
      					  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv4' text field");
      					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
      				  }else {
      					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
      					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
      				  }
      			}
      			
      			

      		//IPv6 Configuration
      			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
      				
      				//EIP Allocation
      					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
      					
      					String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SubnetTypeValue_EIpAlloction")));
      					if(subnetTypevalue.equals("WAN")) {
      						ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'WAN' as expected");
      						Log.info("Subnet Type value is displaying as 'XFER' as expected");
      					}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
      						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
      						Log.info("No values dipslaying under 'Subnet Type' field");
      						
      					}else {
      						ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      						Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      					}
      					
      					selectValueInsideDropdown(application, "ipV6_subnetSizeDropdown" , "Sub Net Size" , subnetSize_ipv6, xml);
      					
      					selectValueInsideDropdown(application, "ipv6_availableBlocks" , "Available blocks" , availableBlocks_Ipv6, xml);
      					
      					click_commonMethod(application, "Allocate Subnet", "addInterface_allocateSubnet", xml);
      					clickOnBankPage();
      					
      					waitForpageload();   waitforPagetobeenable();
      					
      					click_commonMethod(application, "Get Address", "getAddress_IPv6", xml);
      					waitForpageload();  waitforPagetobeenable();
      					
      					org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")));
      					List<WebElement> addressRangeIpv6Dropdownvalue = sec.getOptions();
      					
      					int r = addressRangeIpv6Dropdownvalue.size();
      					if(r<=1) {
      						ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv6'");
      						Log.info("No value displaying under 'Interface Address Range _ IPv6'");
      					}else {
      						
      						sec.selectByIndex(2);
      						String IPv6selectedValue = sec.getFirstSelectedOption().getText();
      						
      						 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
      					}
      					
      					
      				}
      				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
      					
      					  addtextFields_commonMethod(application, "Interface Address Range IPV6", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
      					
      					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
      					  
      					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
      					  if(interfaceValueIntextField.isEmpty()) {
      						  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv6' text field");
      						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
      					  }else {
      						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
      						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
      					  }
      				}
      			
      		
      		WebElement networkIPv6=getwebelement(xml.getlocator("//locators/" + application + "/networkDropdown_Ipv6"));
      		scrolltoview(networkIPv6);
      			
      		//Link Text Field
      			addtextFields_commonMethod(application, "Link", "Link_textField" , link, xml);
      			
      		//Bearer Type Dropdown
      			selectValueInsideDropdown(application, "addInterface_bearerTypeDropdown" , "Bearer Type", bearerType, xml);
      			
      			
      			if(bearerType.equalsIgnoreCase("Ethernet-VLAN")) {
      				
      				selectValueInsideDropdown(application, "addInterface_speedDropdown", "Speed", speed, xml);  //Speed
      				
      				selectValueInsideDropdown(application, "addInterface_duplexDropdown", "Duplex", duplex, xml);  	//duplex
      				
      				addtextFields_commonMethod(application, "VRRP IP", "addInterface_VRRPip", vrrpIP, xml);   //VRRP IP
      				
      				addtextFields_commonMethod(application, "VRRP Group Number", "addInterface_vrrpgroupNumber", vrrpGroupNumber, xml);	//VRRP Group Number
      				
      				addtextFields_commonMethod(application, "VRRP Track Number", "addInterface_vrrpTrackNumber", vrrpTrackNumber, xml);	//VRRP Track Number
      				
      				addtextFields_commonMethod(application, "VRRP Password", "addInterface_vrrpPassword", vrrpPassword, xml);	//VRRP Password
      				
      				addtextFields_commonMethod(application, "VRRP IPV6", "addInterface_vrrpIPv6", vrrpIPv6, xml);	//VRRP IPV6
      				
      				selectValueInsideDropdown(application, "addInterface_interfaceNode", "Interface Node", interfaceNode, xml);	//Interface Node
      			}
      			else {
      				//Speed
      				selectValueInsideDropdown(application, "addInterface_speedDropdown", "Speed", speed, xml);
      				
      				//Duplex
      				selectValueInsideDropdown(application, "addInterface_duplexDropdown", "Duplex", duplex, xml);
      				
      			}
      			
      			//bandwidth
      			selectValueInsideDropdown(application, "addInterface_bandwidthDropdown", "Bandwidth", bandwidth, xml);
      			
      			//VLAN Id
      			addtextFields_commonMethod(application, "VLAN Id", "addInterface_VLANid", vlan, xml);
      			
      			//IV Management
      			addCheckbox_commonMethod(application, "ivManagementCheckbox", "IV Management", ivManagement, "No" , xml);
      			
      			
      			scrollToTop();
      			Thread.sleep(1000);
      			boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configureInterfaceOnDevice_checkbox")).isSelected();
      			
      			scrolltoend();
      			Thread.sleep(1000);
      			
      			
      		if(configureInterfaceSelection) {	
      			//perform Generate configuration
      			boolean configurationpanel=false;
      			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
      			if(configurationpanel) {
      				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
      				System.out.println("'Configuration' panel is displaying");
      				
      				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
      				Thread.sleep(2000);
      				
      				waitForpageload();   waitforPagetobeenable();
      				scrolltoend();
      				Thread.sleep(1000);
      				
      				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
      				if(configurationvalues.isEmpty()) {
      					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
      					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
      				}else {
      					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
      							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
      					System.out.println("After clicking on 'Generate' button, "
      							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
      				}
      			
      				Thread.sleep(2000);
      				scrolltoend();
      				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
      				Thread.sleep(1000);
      				waitforPagetobeenable();
      				
      				
//      				Alert alert = driver.switchTo().alert();
//      				alert.accept();

      			}else {
      				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
      				System.out.println("'Configuration' panel is not displaying");
      			}
      		  }else {
      			  scrolltoend();
      				click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
      				Thread.sleep(1000);
      				waitforPagetobeenable();
      				
//      				Alert alert = driver.switchTo().alert();
//      				alert.accept();
      				
      		  }
      		}
      		
      		
      		
      		
      		public void addMultilink_cisco_CMS_AccessSwitch(String application, String multilink, String existingAddressRangeIPv4selection, String  existingAddressIPv4DropdownValue, 
      				String newAddressRangeIpv4selection, String newinterfaceAddressrange, String subnetSize_ipv4, String cityValue_Ipv4, String existingAddressRangeIPv6selection,
      				String existingAddressIPv6DropdownValue, String newAddressRangeIpv6selection, String newinterfaceAddressrangeIPv6, String subnetSize_ipv6, String availableBlocks_Ipv6,  String configureInterfaceOnDevice, String network_ipv4,
      				String bandwidth, String vlan, String speed, String duplex, String ivManagement) throws InterruptedException, DocumentException, 
      		IOException { 
      			
      			boolean addressValue=false;
      			String expectedAvailablePoolvalue = "None of applicable pools contains a largest available fit which is equal to the selected network size. Please contact RIPEmaster team.";
      			waitForpageload();   waitforPagetobeenable();
      			
      			scrolltoend();
      			
      			click_commonMethod(application, "OK" , "okButton" , xml);
      			
      			warningMessage_commonMethod(application, "speed_warningmessage", "Speed", xml);
      			warningMessage_commonMethod(application, "duplex_warningMessage", "Duplex", xml);
      			
      			scrollToTop();
      			
      			warningMessage_commonMethod(application, "interface_warningMessage" , "Interface", xml);
      			
      		//Configure Interface on Device	
      			addCheckbox_commonMethod(application, "configureInterfaceOnDevice_checkbox", "Configure Interface on Device", configureInterfaceOnDevice, "No" , xml);
      		
      		//Multilink
      			addtextFields_commonMethod(application, "Multilink", "multilinktextfield", multilink, xml);
      			
      		//IPv4 Configuration
      			selectValueInsideDropdown(application, "networkDropdown_ipv4", "Network", network_ipv4 , xml);
      			
      			if(existingAddressRangeIPv4selection.equalsIgnoreCase("yes") && newAddressRangeIpv4selection.equalsIgnoreCase("no")) {
      				
      			//EIP Allocation
      				click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIPV4" , xml);
      				
      				String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addMultilink_subnetTypeValue_EIpAllocation")));
      				if(subnetTypevalue.equals("XFER")) {
      					ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'XFER' as expected");
      					Log.info("Subnet Type value is displaying as 'XFER' as expected");
      				}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
      					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
      					Log.info("No values dipslaying under 'Subnet Type' field");
      					
      				}else {
      					ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      					Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      				}
      				
      				//**selectValueInsideDropdown(application, "ipv4_subnetSizeValue" , "Sub Net Size" , subnetSize_ipv4, xml);
      				//**selectValueInsideDropdown(application, "ipv4_cityValue" , "City", cityValue_Ipv4, xml);
      				GetText(application, "Sub Net Size", "ipv4_subnetSizeValue");
      				GetText(application, "City", "ipv4_cityValue");
      				
      				String availablePools = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availablePool_IPv4_messages")));
      				if(availablePools.equals(expectedAvailablePoolvalue)) {
      					
      					ExtentTestManager.getTest().log(LogStatus.INFO, "No dropdown dipslaying under 'Available pool' field.");
      					ExtentTestManager.getTest().log(LogStatus.PASS, "under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
      					
      					Log.info("No dropdown dipslaying under 'Available pool' field.");
      					Log.info("under 'Available Pool' dropodwn message displays as: "+expectedAvailablePoolvalue);
      				}
      				
      				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
      				
      				Thread.sleep(2000);
      				
      				waitForpageload();  waitforPagetobeenable();
      				click_commonMethod(application, "Get Address", "getAddress_IPv4", xml);
      				waitForpageload();  waitforPagetobeenable();
      				
      				org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/FetchInterfaceAddressRangeDropdown_addInterface")));
      				List<WebElement> addressRangeIpv4Dropdownvalue = sec.getOptions();
      				
      				int r = addressRangeIpv4Dropdownvalue.size();
      				if(r<=1) {
      					ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv4'");
      					Log.info("No value displaying under 'Interface Address Range _ IPv4'");
      				}else {
      					
      					sec.selectByIndex(2);
      					String IPv6selectedValue = sec.getFirstSelectedOption().getText();
      					
      					 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
      				}
      				
      			}
      			else if(existingAddressRangeIPv4selection.equalsIgnoreCase("No") && newAddressRangeIpv4selection.equalsIgnoreCase("Yes")) {
      				
      				  addtextFields_commonMethod(application, "Interface Address Range", "interfaceAddressRange_textField" , newinterfaceAddressrange , xml);
      				
      				  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
      				  
      				  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddress_textField")).getAttribute("value");
      				  if(interfaceValueIntextField.isEmpty()) {
      					  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv4' text field");
      					  System.out.println("No values dipslaying under 'Address_IPv4' text field");
      				  }else {
      					  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
      					  System.out.println( "value in 'Address_IPv4' text field is displaying as: "+interfaceValueIntextField);
      				  }
      			}
      			
      			

      		//IPv6 Configuration
      			if(existingAddressRangeIPv6selection.equalsIgnoreCase("yes") && newAddressRangeIpv6selection.equalsIgnoreCase("no")) {
      				
      				//EIP Allocation
      					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
      					
      					String subnetTypevalue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SubnetTypeValue_EIpAlloction")));
      					if(subnetTypevalue.equals("WAN")) {
      						ExtentTestManager.getTest().log(LogStatus.PASS, "Subnet Type value is displaying as 'WAN' as expected");
      						Log.info("Subnet Type value is displaying as 'XFER' as expected");
      					}else if(subnetTypevalue.equalsIgnoreCase("Null") || subnetTypevalue.equals(" ")){
      						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values dipslaying under 'Subnet Type' field");
      						Log.info("No values dipslaying under 'Subnet Type' field");
      						
      					}else {
      						ExtentTestManager.getTest().log(LogStatus.FAIL, "Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      						Log.info("Subnet Type value is not displaying as expected. It is displaying as: " + subnetTypevalue);
      					}
      					
      					selectValueInsideDropdown(application, "ipV6_subnetSizeDropdown" , "Sub Net Size" , subnetSize_ipv6, xml);
      					
      					selectValueInsideDropdown(application, "ipv6_availableBlocks" , "Available blocks" , availableBlocks_Ipv6, xml);
      					
      					click_commonMethod(application, "Allocate Subnet", "addInterface_allocateSubnet", xml);
      					clickOnBankPage();
      					
      					waitForpageload();   waitforPagetobeenable();
      					
      					click_commonMethod(application, "Get Address", "getAddress_IPv6", xml);
      					waitForpageload();  waitforPagetobeenable();
      					
      					org.openqa.selenium.support.ui.Select sec = new org.openqa.selenium.support.ui.Select(getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")));
      					List<WebElement> addressRangeIpv6Dropdownvalue = sec.getOptions();
      					
      					int r = addressRangeIpv6Dropdownvalue.size();
      					if(r<=1) {
      						ExtentTestManager.getTest().log(LogStatus.INFO, "No value displaying under 'Interface Address Range _ IPv6'");
      						Log.info("No value displaying under 'Interface Address Range _ IPv6'");
      					}else {
      						
      						sec.selectByIndex(2);
      						String IPv6selectedValue = sec.getFirstSelectedOption().getText();
      						
      						 click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRange", xml);
      					}
      					
      					
      				}
      				else if(existingAddressRangeIPv6selection.equalsIgnoreCase("No") && newAddressRangeIpv6selection.equalsIgnoreCase("Yes")) {
      					
      					  addtextFields_commonMethod(application, "Interface Address Range IPV6", "interfaceAddressRangeIPv6_textField" , newinterfaceAddressrangeIPv6 , xml);
      					
      					  click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
      					  
      					  String interfaceValueIntextField = getwebelement(xml.getlocator("//locators/" + application + "/interfaceAddressIPv6_textField")).getAttribute("value");
      					  if(interfaceValueIntextField.isEmpty()) {
      						  ExtentTestManager.getTest().log(LogStatus.INFO, "No values dipslaying under 'Address_IPv6' text field");
      						  System.out.println("No values dipslaying under 'Address_IPv6' text field");
      					  }else {
      						  ExtentTestManager.getTest().log(LogStatus.PASS, "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
      						  System.out.println( "value in 'Address_IPv6' text field is displaying as: "+interfaceValueIntextField);
      					  }
      				}
      			
      		
      		WebElement networkIPv6=getwebelement(xml.getlocator("//locators/" + application + "/addMultilink_networkField_Ipv6"));
      		scrolltoview(networkIPv6);
      			
      			String expectedLinkvalue = "Multilink" + multilink;
      			String linktextFieldValue = getwebelement(xml.getlocator("//locators/" + application + "/Link_textField")).getAttribute("value");
      			if(linktextFieldValue.equals(expectedLinkvalue)) {
      				ExtentTestManager.getTest().log(LogStatus.PASS, "Link text field value dipslaying as " + linktextFieldValue + " as expected");
      				Log.info("Link text field value dipslaying as " + linktextFieldValue + " as expected");
      			}else {
      				ExtentTestManager.getTest().log(LogStatus.FAIL, "Link text field value mismatches. It is displaying as " + linktextFieldValue+ " .The expected value is: " + expectedLinkvalue);
      				Log.info("Link text field value mismatches. It is displaying as " + linktextFieldValue+ " .The expected value is: " + expectedLinkvalue);
      			}
      		
      			//bandwidth
      			selectValueInsideDropdown(application, "addInterface_bandwidthDropdown", "Bandwidth", bandwidth, xml);
      			
      			//VLAN Id
      			addtextFields_commonMethod(application, "VLAN Id", "addInterface_VLANid", vlan, xml);
      			
      			//Speed
      			selectValueInsideDropdown(application, "addInterface_speedDropdown", "Speed", speed, xml);
      			
      			//Duplex
      			selectValueInsideDropdown(application, "addInterface_duplexDropdown", "Duplex", duplex, xml);
      			
      			
      			//IV Management
      			addCheckbox_commonMethod(application, "ivManagementCheckbox", "IV Management", ivManagement, "No" , xml);
      			
      			
      			scrollToTop();
      			Thread.sleep(1000);
      			boolean configureInterfaceSelection = getwebelement(xml.getlocator("//locators/" + application + "/configureInterfaceOnDevice_checkbox")).isSelected();
      			
      			scrolltoend();
      			Thread.sleep(1000);
      			
      			WebElement multilinkBeraerPanelHeader=getwebelement(xml.getlocator("//locators/" + application + "/mulilinBeraerPanelHeader"));
      			scrolltoview(multilinkBeraerPanelHeader);
      			
      		if(configureInterfaceSelection) {	
      			//perform Generate configuration
      			boolean configurationpanel=false;
      			configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
      			if(configurationpanel) {
      				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
      				System.out.println("'Configuration' panel is displaying");
      				
      				click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
      				Thread.sleep(2000);
      				
      				waitForpageload();   waitforPagetobeenable();
      				scrolltoend();
      				Thread.sleep(1000);
      				
      				String configurationvalues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
      				if(configurationvalues.isEmpty()) {
      					ExtentTestManager.getTest().log(LogStatus.FAIL, "After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
      					System.out.println("After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
      				}else {
      					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
      							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
      					System.out.println("After clicking on 'Generate' button, "
      							+ "under 'Configuration' textbox values displaying as: "+configurationvalues);
      				}
      			
      				Thread.sleep(2000);
      				scrolltoend();
      				click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);

      			}else {
      				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
      				System.out.println("'Configuration' panel is not displaying");
      			}
      		  }else {
      			  scrolltoend();
      				click_commonMethod(application, "OK" , "addInterface_okButton" , xml);
      		  }
      		}


      		
      		
      		
}
