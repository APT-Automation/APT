package com.saksoft.qa.scripthelpers;

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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.driverlibrary.Log;
import com.saksoft.qa.driverlibrary.XMLReader;


public class APT_VoiceLineHelper extends DriverHelper {

	public APT_VoiceLineHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/APT_VoiceLine.xml");

	public String primarytrunkGroupname=null;
	public String primarytrunkGroupname_Resilient=null;

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

	public void createcustomer(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws Exception {


		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);
		System.out.println("Mouse hovered on Manage Customer's Service");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "create customer link", "createcustomerlink", xml);
		Thread.sleep(2000);
		compareText(application, "create customer page header", "createcustomer_header", "Customer", xml);
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
		click_commonMethod(application, "Clear", "clearbutton", xml);
		DriverTestcase.logger.log(LogStatus.PASS, "All text field values are cleared");

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
		Thread.sleep(1000);
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);
		compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
		sa.assertAll();	
	}


	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected, String customerName1, String customerName2)
			throws InterruptedException, DocumentException, IOException {


		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		System.out.println("Mouse hovered on Manage Customer's Service");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "Create Order/Service", "CreateOrderServiceLink", xml);
		Log.info("=== Create Order/Service navigated ===");

		//click on Next button to check mandatory messages
		click_commonMethod(application, "Next", "nextbutton", xml);

		//Customer Error message	
		warningMessage_commonMethod(application, "customer_createorderpage_warngmsg", "Customer", xml);

		//Entering Customer name
		addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", customerName1, xml);
		addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", customerName2, xml);

		//Select Customer from dropdown
		addDropdownValues_commonMethod(application, "Customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);
		click_commonMethod(application, "Next", "Next_Button", xml);

	}

	public static String newordernumber, newVoiceLineNumber, SelectOrderNumber;

	public void createorderservice(String application, String neworder, String neworderno, String newrfireqno, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		scrolltoend();
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(1000);

		//Warning messages verify
		warningMessage_commonMethod(application, "order_contractnumber_warngmsg", "Order/Contract Number(Parent SID)", xml);
		warningMessage_commonMethod(application, "servicetype_warngmsg", "Service Type", xml);

		if (neworder.equalsIgnoreCase("YES")) {

			Thread.sleep(2000);

			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addtextFields_commonMethod(application, "Order/Contract Number", "newordertextfield", neworderno, xml);
			addtextFields_commonMethod(application, "RFI Voice line Number", "newrfireqtextfield", newrfireqno, xml);
			click_commonMethod(application, "create order", "createorderbutton", xml);
			compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully", xml);			
			ScrolltoElement(application, "CreateOrderHeader", xml);

			newordernumber = neworderno;
			newVoiceLineNumber = newrfireqno;
		} 

		else if (existingorderservice.equalsIgnoreCase("YES")) {
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber, xml);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

			SelectOrderNumber = existingordernumber;
		} else {

			System.out.println("Order not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step :Order not selected");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Service Type dropdown is displaying");
				System.out.println("Service Type dropdown is displaying");

				if(servicetype.equalsIgnoreCase("null")) {

					DriverTestcase.logger.log(LogStatus.PASS, " No values selected under Service Type dropdown");
					System.out.println(" No values selected under Service Type dropdown");
				}else {

					Clickon(getwebelement("//div[label[text()='Service Type']]//div[text()='×']"));
					Thread.sleep(3000);

					//verify list of values inside dropdown
					List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

					DriverTestcase.logger.log(LogStatus.PASS, " List of values inside Service Type dropdown is:  ");
					System.out.println( " List of values inside Service Type dropdown is:  ");

					for (WebElement valuetypes : listofvalues) {
						Log.info("service sub types : " + valuetypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
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
					DriverTestcase.logger.log(LogStatus.PASS, "Service Type dropdown value selected as: "+ actualValue );
					System.out.println("Service Type dropdown value selected as: "+ actualValue);

				}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "Service Type is not displaying");
				System.out.println("Service Type is not displaying");
			}
		}catch(NoSuchElementException e) {
			DriverTestcase.logger.log(LogStatus.FAIL, "Service Type is not displaying");
			System.out.println("Service Type is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under Service Type dropdown");
			System.out.println(" NO value selected under Service Type dropdown");
		}
		// click on next button
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(2000);
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

	}


	public void verifyservicecreation(String application, String sid, String Remarks, String orderno, String rfireqno, String servicetype, String resellercodevalue, String thirdpartyinternet_checkbox, String email, String phonecontact, String performancereporting_checkbox, String proactivenotification_checkbox, String notificationmanagementteam_value) throws InterruptedException, IOException, DocumentException {


		//Create service
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);
		//addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno, xml);

		// verify warning messages
		//		click_commonMethod(application, "OK", "okbutton", xml);
		//		Thread.sleep(1000);
		//		warningMessage_commonMethod(application, "sidwarngmsg", "Service Identification", xml);

		// service identification
		cleartext(application, "Service Identification", "serviceidentificationtextfield");
		addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
		Thread.sleep(1000);
		GetText(application, "Service Type", "servicetypevalue");
		addtextFields_commonMethod(application, "Reseller Code", "resellercode_textfield", resellercodevalue, xml);
		addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet_checkbox, "no", xml);
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", Remarks, xml);
		addtextFields_commonMethod(application, "Email", "emailtextfieldvalue", email, xml);
		click_commonMethod(application, "Email adding Arrow", "emailaddarrow", xml);
		ScrolltoElement(application, "phonecontacttextfieldvalue", xml);
		Thread.sleep(1000);
		addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", phonecontact, xml);
		click_commonMethod(application, "phone contact adding Arrow", "phoneaddarrow", xml);
		compareText(application, "Management Options", "managementoptions_header", "Management Options", xml);
		String Package= getwebelement(xml.getlocator("//locators/" + application + "/packagefield")).getAttribute("disabled");
		if(Package!=null)
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Package dropdown field is disabled as expected");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Package dropdown field is not disabled");
		}
		String ManagedService_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/managedservice_checkbox")).getAttribute("disabled");
		String ManagedService_Checked= getwebelement(xml.getlocator("//locators/" + application + "/managedservice_checkbox")).getAttribute("checked");
		if(ManagedService_Disabled!=null && ManagedService_Checked!=null)
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Managed Service Checkbox is disabled & checked by default as expected");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Managed Service Checkbox is not disabled & checked by default");
		}
		//syslog event view checkbox
		String SyslogEvent_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/syslogevent_checkbox")).getAttribute("disabled");
		if(SyslogEvent_Disabled!=null)
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Syslog Event View Checkbox is disabled as expected");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Syslog Event View Checkbox is not disabled");
		}
		//service status view checkbox
		String ServiceStatusView_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/servicestatusview_checkbox")).getAttribute("disabled");
		if(ServiceStatusView_Disabled!=null)
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Service Status View Checkbox is disabled as expected");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Service Status View Checkbox is not disabled");
		}
		//Router configuration view checkbox
		String RouterConfigurationView_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/routerconfigview_checkbox")).getAttribute("disabled");
		if(RouterConfigurationView_Disabled!=null)
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Router Configuration View Checkbox is disabled as expected");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Router Configuration View Checkbox is not disabled");
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
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Dial User Administration Checkbox is disabled as expected");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Dial User Administration Checkbox is not disabled");
		}
		click_commonMethod(application, "OK", "okbutton", xml);

		Thread.sleep(1000);
		compareText(application, "Service creation success msg", "servicecreationmessage", "Service successfully created", xml);
		sa.assertAll();

	}


	public void verifyCustomerDetailsInformation(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "customerdetailsheader", xml);

		compareText(application, "Customer Name", "Name_Value", name, xml);
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


	public void createnewuser(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email,String Phone, String EditUsername, String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone)
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
			addtextFields_commonMethod(application, "User Name", "UserName", Username, xml);
			addtextFields_commonMethod(application, "First Name", "FirstName", Firstname, xml);
			addtextFields_commonMethod(application, "SurName", "SurName", Surname, xml);
			addtextFields_commonMethod(application, "Postal Address", "PostalAddress", Postaladdress, xml);
			addtextFields_commonMethod(application, "Email", "Email", Email, xml);
			addtextFields_commonMethod(application, "Phone", "Phone", Phone, xml);

			ScrolltoElement(application, "cancelbutton", xml);
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			compareText(application, "User panel Header", "userspanel_header", "Users", xml);

			//Create User
			ScrolltoElement(application, "customerdetailsheader", xml);
			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Add", "AddLink", xml);
			compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);
			addtextFields_commonMethod(application, "User Name", "UserName", Username, xml);
			addtextFields_commonMethod(application, "First Name", "FirstName", Firstname, xml);
			addtextFields_commonMethod(application, "SurName", "SurName", Surname, xml);
			addtextFields_commonMethod(application, "Postal Address", "PostalAddress", Postaladdress, xml);
			addtextFields_commonMethod(application, "Email", "Email", Email, xml);
			addtextFields_commonMethod(application, "Phone", "Phone", Phone, xml);
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			scrolltoend();
			click_commonMethod(application, "OK", "OK_button", xml);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : User added successfully");

			//Edit User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			compareText(application, "Edit User Header", "edituser_header", "Edit User", xml);
			scrollToTop();
			cleartext(application, "User Name", "UserName");
			addtextFields_commonMethod(application, "User Name", "UserName", EditUsername, xml);
			cleartext(application, "First Name", "FirstName");
			addtextFields_commonMethod(application, "First Name", "FirstName", EditFirstname, xml);
			cleartext(application, "SurName", "SurName");
			addtextFields_commonMethod(application, "SurName", "SurName", EditSurname, xml);
			cleartext(application, "Postal Address", "PostalAddress");
			addtextFields_commonMethod(application, "Postal Address", "PostalAddress", EditPostaladdress, xml);
			cleartext(application, "Email", "Email");
			addtextFields_commonMethod(application, "Email", "Email", EditEmail, xml);
			cleartext(application, "Phone", "Phone");
			addtextFields_commonMethod(application, "Phone", "Phone", EditPhone, xml);
			cleartext(application, "Password", "Password");
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			scrolltoend();
			click_commonMethod(application, "OK", "OK_button", xml);
			Thread.sleep(2000);
			//compareText(application, "User update success message", "successmsg", "User updated successfully", xml);

			//View User
			ScrolltoElement(application, "customerdetailsheader", xml);
			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "view", "view", xml);
			ScrolltoElement(application, "usernamevalue", xml);
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
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
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
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			compareText(application, "User delete success msg", "deletesuccessmsg", "User successfully deleted", xml);
		}

		else if(!UserGrid.contains("1px"))
		{
			//Edit User
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

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
			cleartext(application, "Email", "Email");
			addtextFields_commonMethod(application, "Email", "Email", EditEmail, xml);
			cleartext(application, "Phone", "Phone");
			addtextFields_commonMethod(application, "Phone", "Phone", EditPhone, xml);
			cleartext(application, "Password", "Password");
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			ScrolltoElement(application, "OkButton", xml);
			click_commonMethod(application, "OK", "OkButton", xml);

			//View User

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

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

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = driver
						.findElement(By.xpath("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
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
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			compareText(application, "User delete success msg", "deletesuccessmsg", "User successfully deleted", xml);
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : order information is matched");
			} else {
				sa.fail("order information is not matched");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

		} else {

			System.out.println("existing order is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : existing order is not selected");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : order information is matched");				
			} else {
				sa.fail("order information is not matched");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
			}

		} else {

			System.out.println("new order is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : new order is not selected");
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

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
		DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

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
		List<WebElement> ChangeOrder_DropdownList= driver.findElements(By.xpath(xml.getlocator("//locators/" + application + "/changeorder_dropdownlist")));
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
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

			//Change order
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Choose order dropdown", "changeorder_chooseorderdropdown", xml);
			Clickon(getwebelement("//label[text()='Order/Contract Number (Parent SID)']/parent::div//div[@role='list']//span[@aria-selected='false'][1]"));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected order from dropdown");
			click_commonMethod(application, "OK", "changeorder_okbutton", xml);
			Thread.sleep(1000);
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
			Log.info("Navigated to order panel in view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");
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
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to order panel in view service page");
			compareText(application, "Order Number", "ordernumbervalue", changeorderno, xml);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
			Log.info("------ Change Order is successful ------");
		}
	}

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String Remarks, String resellercodevalue, String thirdpartyinternet_checkbox, String phonecontact) throws InterruptedException, DocumentException, IOException {

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

	public void verifyservicepanel_links(String application, String EditRemarks, String Remarks, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired, String resellercodevalue, String thirdpartyinternet_checkbox, String phonecontact, String edit_resellercodevalue, String edit_thirdpartyinternet_checkbox, String edit_email, String edit_phonecontact, String edit_performancereporting_checkbox, String edit_proactivenotification_checkbox, String edit_notificationmanagementteam_value) throws Exception {

		//		//Cancel edit service
		//		ScrolltoElement(application, "orderpanelheader", xml);
		//		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		//		click_commonMethod(application, "Edit", "edit", xml);
		//		scrolltoend();
		//		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		//		Thread.sleep(2000);
		//		ScrolltoElement(application, "orderpanelheader", xml);
		//		
		//		//Edit service
		//		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		//		click_commonMethod(application, "Edit", "edit", xml);
		//		
		//		String ServiceIdentification= getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")).getAttribute("value");
		//		DriverTestcase.logger.log(LogStatus.PASS, "Step : Service Identification number is displayed as: "+sid);
		//		GetText(application, "Service Type", "servicetypevalue");
		//		if(!edit_resellercodevalue.equalsIgnoreCase("null"))
		//		{
		//			cleartext(application, "Reseller Code", "resellercode_textfield");
		//			edittextFields_commonMethod(application, "Reseller Code", "resellercode_textfield", edit_resellercodevalue, xml);
		//		}
		//		else
		//		{
		//			edittextFields_commonMethod(application, "Reseller Code", "resellercode_textfield", edit_resellercodevalue, xml);
		//		}
		//		editcheckbox_commonMethod(application, edit_thirdpartyinternet_checkbox, "thirdpartyinternet_checkbox", "3rd Party Internet", xml);
		//		if(!EditRemarks.equalsIgnoreCase("null"))
		//		{
		//			cleartext(application, "Remarks", "remarktextarea");
		//			edittextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
		//		}
		//		else
		//		{
		//			edittextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
		//		}
		//		//Edit email
		//				ScrolltoElement(application, "servicetypevalue", xml);
		//				click_commonMethod(application, "Selected Email", "selectedemail", xml);
		//				click_commonMethod(application, "Email remove arrow", "emailremovearrow", xml);
		//				Thread.sleep(1000);
		//				cleartext(application, "Email", "emailtextfieldvalue");
		//				addtextFields_commonMethod(application, "Email", "emailtextfieldvalue", edit_email, xml);
		//				click_commonMethod(application, "Email adding arrow", "emailaddarrow", xml);
		//				scrolltoend();
		//				//Edit phone contact
		//				click_commonMethod(application, "Selected phone contact", "selectedphone", xml);
		//				click_commonMethod(application, "Phonecontact remove arrow", "phoneremovearrow", xml);
		//				cleartext(application, "Phone Contact", "phonecontacttextfieldvalue");
		//				addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", edit_phonecontact, xml);
		//				click_commonMethod(application, "phonecontact adding Arrow", "phoneaddarrow", xml);
		//				Thread.sleep(1000);
		//				
		//		compareText(application, "Management Options", "managementoptions_header", "Management Options", xml);
		//		String Package= getwebelement(xml.getlocator("//locators/" + application + "/packagefield")).getAttribute("disabled");
		//		if(Package!=null)
		//		{
		//			DriverTestcase.logger.log(LogStatus.PASS, "Step : Package dropdown field is disabled as expected");
		//		}
		//		else
		//		{
		//			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Package dropdown field is not disabled");
		//		}
		//		String ManagedService_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/managedservice_checkbox")).getAttribute("disabled");
		//		String ManagedService_Checked= getwebelement(xml.getlocator("//locators/" + application + "/managedservice_checkbox")).getAttribute("checked");
		//		if(ManagedService_Disabled!=null && ManagedService_Checked!=null)
		//		{
		//			DriverTestcase.logger.log(LogStatus.PASS, "Step : Managed Service Checkbox is disabled & checked by default as expected");
		//		}
		//		else
		//		{
		//			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Managed Service Checkbox is not disabled & checked by default");
		//		}
		//		//syslog event view checkbox
		//		String SyslogEvent_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/syslogevent_checkbox")).getAttribute("disabled");
		//		if(SyslogEvent_Disabled!=null)
		//		{
		//			DriverTestcase.logger.log(LogStatus.PASS, "Step : Syslog Event View Checkbox is disabled as expected");
		//		}
		//		else
		//		{
		//			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Syslog Event View Checkbox is not disabled");
		//		}
		//		//service status view checkbox
		//				String ServiceStatusView_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/servicestatusview_checkbox")).getAttribute("disabled");
		//				if(ServiceStatusView_Disabled!=null)
		//				{
		//					DriverTestcase.logger.log(LogStatus.PASS, "Step : Service Status View Checkbox is disabled as expected");
		//				}
		//				else
		//				{
		//					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Service Status View Checkbox is not disabled");
		//				}
		//				//Router configuration view checkbox
		//				String RouterConfigurationView_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/routerconfigview_checkbox")).getAttribute("disabled");
		//				if(RouterConfigurationView_Disabled!=null)
		//				{
		//					DriverTestcase.logger.log(LogStatus.PASS, "Step : Router Configuration View Checkbox is disabled as expected");
		//				}
		//				else
		//				{
		//					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Router Configuration View Checkbox is not disabled");
		//				}
		//				editcheckbox_commonMethod(application, edit_performancereporting_checkbox, "performancereporting_checkbox", "Performance Reporting", xml);
		//				editcheckbox_commonMethod(application, edit_proactivenotification_checkbox, "proactivenotification_checkbox", "Pro-active Notification", xml);
		//				if(edit_proactivenotification_checkbox.equalsIgnoreCase("Yes"))
		//				{
		//					addDropdownValues_commonMethod(application, "Notification Management Team", "notificationmanagementteam_dropdown", edit_notificationmanagementteam_value, xml);
		//				}
		//				//Dial user administration checkbox
		//				String DialUserAdministration_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/dialuseradministration_checkbox")).getAttribute("disabled");
		//				if(DialUserAdministration_Disabled!=null)
		//				{
		//					DriverTestcase.logger.log(LogStatus.PASS, "Step : Dial User Administration Checkbox is disabled as expected");
		//				}
		//				else
		//				{
		//					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Dial User Administration Checkbox is not disabled");
		//				}
		//				scrolltoend();
		//		click_commonMethod(application, "OK", "okbutton", xml);
		//		Thread.sleep(2000);
		//		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		//		{
		//			Log.info("Navigated to view service page");
		//			System.out.println("Navigated to view service page");
		//			compareText(application, "Service updated success message", "serviceupdate_successmsg", "Service successfully updated", xml);	
		//		}
		//		else
		//		{
		//			Log.info("Service not updated");
		//			System.out.println("Service not updated");
		//		}
		//
		//		//view service details
		//		Thread.sleep(2000);
		//		ScrolltoElement(application, "orderpanelheader", xml);
		//		compareText(application, "Service panel Header", "servicepanel_header", "Service", xml);
		//		GetText(application, "Service Identification", "servicepanel_serviceidentificationvalue");
		//		GetText(application, "Service Type", "servicepanel_servicetypevalue");
		//		if(edit_resellercodevalue.equalsIgnoreCase("null"))
		//		{
		//		compareText(application, "Reseller Code", "servicepanel_resellercode", resellercodevalue, xml);
		//		}
		//		else
		//		{
		//			compareText(application, "Reseller Code", "servicepanel_resellercode", edit_resellercodevalue, xml);
		//		}
		//		GetText(application, "Email", "servicepanel_email");
		//		if(edit_phonecontact.equalsIgnoreCase("null"))
		//		{
		//		compareText(application, "Phone Contact", "servicepanel_phonecontact", phonecontact, xml);
		//		}
		//		else
		//		{
		//			compareText(application, "Phone Contact", "servicepanel_phonecontact", edit_phonecontact, xml);
		//		}
		//		if(edit_thirdpartyinternet_checkbox.equalsIgnoreCase("null"))
		//		{
		//		compareText(application, "3rd Party Internet", "servicepanel_thirdpartyinternet", thirdpartyinternet_checkbox, xml);
		//		}
		//		else
		//		{
		//			compareText(application, "3rd Party Internet", "servicepanel_thirdpartyinternet", edit_thirdpartyinternet_checkbox, xml);
		//		}
		//		if(EditRemarks.equalsIgnoreCase("null"))
		//		{
		//		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks, xml);
		//		}
		//		else
		//		{
		//			compareText(application, "Remarks", "servicepanel_remarksvalue", EditRemarks, xml);
		//		}


		//manage subnets IPv6
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
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

		//Show new infovista report
		shownewInfovista(application);
		Thread.sleep(2000);


		//dump
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Dump", "dump_link", xml);
		Thread.sleep(3000);
		GetText(application, "Dump header", "dumppage_header");
		GetText(application, "Service retrieved time", "serviceretrieved_text");
		compareText(application, "Service header", "service_header", "Service", xml);
		GetText(application, "Dump page service details", "dumppage_text");
		Thread.sleep(1000);
		click_commonMethod(application, "Close", "closesymbol", xml);
		Thread.sleep(2000);

		//synchronize link in view service page
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Synchronize", "synchronizelink_servicepanel", xml);
		ScrolltoElement(application, "customerdetailsheader", xml);
		compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.", xml);

		Thread.sleep(2000);

		//Service delete is performed in the last test case
	}


	public void verifyManageService(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
		//Manage service
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage", "manageLink", xml);
		Thread.sleep(2000);
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
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request logged");
					}
					else
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request is not logged");
				}
				catch(StaleElementReferenceException e)
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "No service history to display");
				}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Status link is not working");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change not reqired");
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

		scrolltoend();
		compareText(application, "Sync Status", "sync_status", syncstatus, xml);
		click_commonMethod(application, "Synchronize", "synchronizelink", xml);
		compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.", xml);
		scrolltoend();
		click_commonMethod(application, "Back", "managepage_backbutton", xml);
		Thread.sleep(2000);

	}

	public void verifyManagementOptionspanel(String application, String performancereporting_checkbox, String proactivenotification_checkbox, String edit_performancereporting_checkbox, String edit_proactivenotification_checkbox) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "servicepanel_header", xml);
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

		ScrolltoElement(application, "managementoptions_header", xml);
		compareText(application, "ASR Device header", "asrdevice_header", "ASR Device", xml);
		Thread.sleep(1000);
		scrolltoend();
		click_commonMethod(application, "Add ASR Device link", "adddevice_link", xml);
		Thread.sleep(2000);
		compareText(application, "Add ASR Device header", "adddevice_header", "Add ASR Device", xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(1000);
		addDropdownValues_commonMethod(application, "IMS POP Location", "imspoplocation_dropdown", imspoplocation_dropdownvalue, xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		//verifysuccessmessage(application, "ASR Device added successfully", xml);
		compareText(application, "Add ASR Device success message", "successmsg", "ASR Device added successfully", xml);

		//Added device
		Thread.sleep(2000);
		ScrolltoElement(application, "managementoptions_header", xml);
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
						DriverTestcase.logger.log(LogStatus.PASS, "View link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "View link is not displaying for added device");
					}
					//verify Edit link
					if(AddedDevice_EditLink.contains("Edit"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Edit link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "Edit link is not displaying for added device");
					}
					//verify Delete from Service link
					if(AddedDevice_DeletefromServiceLink.contains("Delete from Service"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Delete from Service link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "Delete from Service link is not displaying for added device");
					}
					//verify Select Interfaces link
					if(AddedDevice_SelectInterfacesLink.contains("Select Interfaces"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Select Interfaces link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "Select Interfaces link is not displaying for added device");
					}

				}
				else if(imspoplocation_dropdownvalue.equalsIgnoreCase("Paris-1"))
				{
					//verify view link
					if(AddedDevice_ViewLink.contains("View"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "View link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "View link is not displaying for added device");
					}
					//verify Edit link
					if(AddedDevice_EditLink.contains("Edit"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Edit link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "Edit link is not displaying for added device");
					}
					//verify Delete from Service link
					if(AddedDevice_DeletefromServiceLink.contains("Delete from Service"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Delete from Service link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "Delete from Service link is not displaying for added device");
					}
					//verify Select Interfaces link
					if(AddedDevice_SelectInterfacesLink.contains("Select Interfaces"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Select Interfaces link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "Select Interfaces link is not displaying for added device");
					}
				}
				else if(imspoplocation_dropdownvalue.equalsIgnoreCase("Frankfurt-1"))
				{
					//verify view link
					if(AddedDevice_ViewLink.contains("View"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "View link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "View link is not displaying for added device");
					}
					//verify Edit link
					if(AddedDevice_EditLink.contains("Edit"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Edit link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "Edit link is not displaying for added device");
					}
					//verify Delete from Service link
					if(AddedDevice_DeletefromServiceLink.contains("Delete from Service"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Delete from Service link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "Delete from Service link is not displaying for added device");
					}
					//verify Select Interfaces link
					if(AddedDevice_SelectInterfacesLink.contains("Select Interfaces"))
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Select Interfaces link is displaying as expected for added device");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "Select Interfaces link is not displaying for added device");
					}
				}

			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}

	}

	public static String ASRDeviceNameValue, ASRManagementAddressValue, ASRDeviceCountryValue;
	public void verifyEditASRDevice(String application, String imspoplocation_dropdownvalue, String edit_asrdevicename, String edit_asrmanagementaddress, String editCountry, String editExistingCity, 
			String editExistingCityValue, String editExistingSite, String editExistingSiteValue, String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, 
			String editNewPremise, String editNewCityName, String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, DocumentException, IOException {

		String ASRDeviceName=null;
		String ASRManagementAddress=null;
		String ASRDevice_Country=null;

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

					compareText(application, "Vendor/Model", "edit_asrvendormodel", "Cisco 12016", xml);

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
					scrolltoend();
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

						DriverTestcase.logger.log(LogStatus.PASS, " No changes made for 'Country' dropdown");

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
					click_commonMethod(application, "OK", "okbutton", xml);
					Thread.sleep(2000);
					compareText(application, "Update ASR Device success message", "successmsg", "ASR Device updated successfully", xml);

					ASRDeviceNameValue= ASRDeviceName;
					ASRManagementAddressValue= ASRManagementAddress;
					ASRDeviceCountryValue= ASRDevice_Country;
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Device not added for "+IMSPopLocation_Code+ " location");
				}
				break;
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
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

			DriverTestcase.logger.log(LogStatus.PASS, "No chnges made under 'City' field");
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

			DriverTestcase.logger.log(LogStatus.PASS, "No changes made under 'Site' field");
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

			DriverTestcase.logger.log(LogStatus.PASS, "No changes made under 'Premise' field");
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

					compareText(application, "View Device header", "viewasrdevice_header", "Device", xml);
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
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
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
			DriverTestcase.logger.log(LogStatus.PASS, "Navigated to 'Edit ASR Device' page successfully");
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
						compareText(application, "View device header", "viewasrdevice_header", "Device", xml);

						break;
					}
				}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Not navigated to 'Edit ASR Device' page");
		}

		//verify delete device in view device page
		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		click_commonMethod(application, "Delete", "viewdevice_delete", xml);
		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete alert is displayed as expected");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletealertclose")));
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert is not displayed");
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

	public static String InterfaceAddress;
	public void verifyFetchInterface(String application, String imspoplocation_dropdownvalue, String sid, String edit_asrdevicename, String Inservice_status, String Inmaintenance_status, String interfacename) throws InterruptedException, DocumentException, IOException {

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
					compareText(application, "View device header", "viewasrdevice_header", "Device", xml);
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
						DriverTestcase.logger.log(LogStatus.FAIL, "Step : Last Modification column value field is not displaying");
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
					DriverTestcase.logger.log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue);
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

										DriverTestcase.logger.log(LogStatus.PASS, "Device Status History is empty");
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
													DriverTestcase.logger.log(LogStatus.PASS, "Device status history table has data");
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
														DriverTestcase.logger.log(LogStatus.FAIL, "Step : Changed on column value field is not displaying");
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
									DriverTestcase.logger.log(LogStatus.INFO, "No interfaces added");

								}
							}

					}else {

						System.out.println("No data available in table");
						Log.info("No data available in table");
						DriverTestcase.logger.log(LogStatus.FAIL, "No data available in table");
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

										DriverTestcase.logger.log(LogStatus.PASS, "Interface table is empty");
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
												if (AddedInterfacedata.equalsIgnoreCase(interfacename)) {

													String InterfaceNameRowID= getwebelement("//div[@role='gridcell'][text()='"+interfacename+"']/parent::div[@role='row']").getAttribute("row-id");
													System.out.println("Interface row id: "+InterfaceNameRowID);

													//verify interface values in table
													String DeviceNamevalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='deviceName']").getText();
													DriverTestcase.logger.log(LogStatus.PASS, "Step: Interface Device Name value is displayed as : "+DeviceNamevalue);
													String InterfaceNamevalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='name']").getText();
													DriverTestcase.logger.log(LogStatus.PASS, "Step: Interface Name value is displayed as : "+InterfaceNamevalue);
													String InterfaceAddressvalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='address']").getText();
													DriverTestcase.logger.log(LogStatus.PASS, "Step: Interface Address value is displayed as : "+InterfaceAddressvalue);
													WebElement InterfaceAddressRowValue1= getwebelement(xml.getlocator("//locators/" + application + "/interfacetype_rowvalue"));
													Clickon(InterfaceAddressRowValue1);
													InterfaceAddressRowValue1.sendKeys(Keys.TAB);
													String InterfaceTypevalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='type.desc']").getText();
													DriverTestcase.logger.log(LogStatus.PASS, "Step: Interface Type value is displayed as : "+InterfaceTypevalue);
													WebElement InterfaceTypeRowValue1= getwebelement(xml.getlocator("//locators/" + application + "/interfacetype_rowvalue"));
													Clickon(InterfaceTypeRowValue1);
													InterfaceTypeRowValue1.sendKeys(Keys.TAB);
													String Statusvalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='currentStatus.desc']").getText();
													DriverTestcase.logger.log(LogStatus.PASS, "Step: Status value is displayed as : "+Statusvalue);
													WebElement StatusRowValue1= getwebelement(xml.getlocator("//locators/" + application + "/viewinterface_status_rowvalue"));
													Clickon(StatusRowValue1);
													StatusRowValue1.sendKeys(Keys.TAB);
													String LastModificationvalue= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='m_time']").getText();
													DriverTestcase.logger.log(LogStatus.PASS, "Step: Last Modification value is displayed as : "+LastModificationvalue);
													WebElement LastModificationRowValue= getwebelement(xml.getlocator("//locators/" + application + "/viewinterface_lastmod_rowvalue"));
													Clickon(LastModificationRowValue);
													LastModificationRowValue.sendKeys(Keys.TAB);
													WebElement StatusLink= getwebelement("//div[@role='gridcell']/parent::div[@row-id="+InterfaceNameRowID+"]//div[@col-id='Status']/div/a");
													Clickon(StatusLink);
													DriverTestcase.logger.log(LogStatus.PASS, "Step: Clicked on Status link in interface table");

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
						GetText(application, "Status", "status_statusvalue");
						click_commonMethod(application, "New Status Dropdown", "interface_statuspage_newstatusdropdown", xml);
						WebElement selectNewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue"));
						Clickon(selectNewStatusvalue1);
						String NewStatusvalue1= getwebelement(xml.getlocator("//locators/" + application + "/interface_statuspage_newstatusdropdownvalue")).getText();
						DriverTestcase.logger.log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue1);
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

										DriverTestcase.logger.log(LogStatus.PASS, "Interface Status History is empty");
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
													DriverTestcase.logger.log(LogStatus.PASS, "Interface status history table has data");
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
														DriverTestcase.logger.log(LogStatus.FAIL, "Step : Changed On column value field is not displaying");
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
							DriverTestcase.logger.log(LogStatus.FAIL, "No data available in status history table");
						}
						}else {

							System.out.println("No data available in Interface table");
							Log.info("No data available in Interface table");
							DriverTestcase.logger.log(LogStatus.FAIL, "No data available in Interface table");
						}
					}else
					{
						DriverTestcase.logger.log(LogStatus.INFO, "No Interface added in table");
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

						DriverTestcase.logger.log(LogStatus.PASS, "Smarts date value is displayed as: "+SmartsDateTimevalue);
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

						DriverTestcase.logger.log(LogStatus.PASS, "Fetch Interfaces date value is displayed as: "+FetchInterfaces_DateTimevalue);
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
						DriverTestcase.logger.log(LogStatus.FAIL, "Step : Fetch Interfaces date value field is not displaying");
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
					//		DriverTestcase.logger.log(LogStatus.PASS, "Vistamart Device date value is displayed as: "+VistaMartDevice_DateTimevalue);
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
					compareText(application, "View Device header", "viewasrdevice_header", "Device", xml);
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to 'View Device' page");
					driver.navigate().back();
					Thread.sleep(1000);

					//verify device name link in synchronization panel
					click(application, "Device", "synchronization_devicevalue", xml);
					Thread.sleep(2000);
					compareText(application, "View Device header", "viewasrdevice_header", "Device", xml);
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to 'View Device' page");
					Thread.sleep(2000);
					clickOnBreadCrumb(application, sid);
					Thread.sleep(2000);

				}
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}

	}

	public void verify_Cisco_RouterTools(String application, String imspoplocation_dropdownvalue, String commandIPv4, String edit_asrmanagementaddress, 
			String vrfname_ipv4) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "managementoptions_header", xml);
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

					ScrolltoElement(application, "routertools_header", xml);
					Thread.sleep(1000);

					//Command IPV4	
					addDropdownValues_commonMethod(application, "Command IPV4", "commandIPV4_dropdown" , commandIPv4, xml);

					if(!edit_asrmanagementaddress.equalsIgnoreCase("null"))
					{
						hostnametextField_IPV4(application, commandIPv4, edit_asrmanagementaddress);
					}
					else
					{
						hostnametextField_IPV4(application, commandIPv4, ASRManagementAddressValue);
					}

					vrfNametextField_IPV4(application, commandIPv4, vrfname_ipv4);

					executeCommandAndFetchTheValue(application, "executebutton_Ipv4");
					break;
				}
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}

	}

	public void hostnametextField_IPV4(String application, String command_ipv4, String ipAddress) {
		boolean IPV4availability=false;
		try {  
			IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv4_hostnameTextfield")).isDisplayed();

			if(IPV4availability) {

				addtextFields_commonMethod(application, "IP Address or Hostname", "commandIPv4_hostnameTextfield", ipAddress, xml);

			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
				System.out.println("'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
			}
		}catch(Exception e) {
			e.printStackTrace();

			DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPv4' text field is not displaying for "+ command_ipv4);
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
					DriverTestcase.logger.log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
					System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				}
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
				System.out.println("'VRF Name' for 'IPv4' text field is not displaying for "+ command_ipv4);
			}

		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "'VRF Name IPv4' text field is not displaying for "+ command_ipv4);
			System.out.println("'VRF Name IPv4' text field is not displaying for "+ command_ipv4 +" command");
		}

	}

	public void executeCommandAndFetchTheValue(String application, String executeButton) throws InterruptedException, DocumentException {

		click_commonMethod(application, "Execute", executeButton, xml);

		boolean remarkField=false;
		try {	
			remarkField=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).isDisplayed();
			if(remarkField) {
				DriverTestcase.logger.log(LogStatus.PASS, "'Remark' text field is displaying");
				System.out.println( "'Remark' text field is displaying");

				String remarkvalue=getwebelement(xml.getlocator("//locators/" + application + "/result_textArea")).getText();
				DriverTestcase.logger.log(LogStatus.PASS, "value under 'Remark' field displaying as "+ remarkvalue);
				System.out.println("value under 'Remark' field displaying as "+ remarkvalue);

			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Remark' text field is not displaying");
				System.out.println( "'Remark' text field is not displaying");
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Remark' text field is not displaying");
			System.out.println("'Remark' text field is not displaying");
		}

	}

	public void verify_CiscoVendor_InterfacePanel(String application, String interfacename, String edit_interfacename, String addInterface_Allocate, String configuration_dropdownvalue, String interfaceAddress, String virtualTemplate, String cpeAddressRange, String localPreShareKey, String remotePreShareKey, String identityEmail, String sid, String edit_asrdevicename, String editInterface_Allocate, String edit_configuration_dropdownvalue, String edit_interfaceAddress, String edit_virtualTemplate, String edit_CPEAddressRange, String edit_localPreshareKey, String edit_remotePreshareKey, String edit_identityEmail) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "routertools_header", xml);
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

		//Add Interface
		if(addInterface_Allocate.equalsIgnoreCase("Yes"))
		{
			click_commonMethod(application, "Allocate", "allocate_button", xml);
			Thread.sleep(2000);
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

		//Verify added interface
		ScrolltoElement(application, "interfaces_header", xml);
		WebElement AddedInterfaces= getwebelement(xml.getlocator("//locators/" + application + "/addedinterfaces"));
		String addedinterfacecheck= AddedInterfaces.getAttribute("style");
		if(!addedinterfacecheck.contains("height: 1px"))
		{
			compareText(application, "Interfaces", "interfaces_header", "Interfaces", xml);
			String Interface_RowID= getwebelement(xml.getlocator("//locators/" + application + "/interface_rowid").replace("value", interfacename)).getAttribute("row-id");

			WebElement InterfaceName_value= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
			WebElement Link_value= getwebelement(xml.getlocator("//locators/" + application + "/link_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
			WebElement InterfaceAddressRange_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddressrange_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
			WebElement InterfaceAddress_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
			WebElement BearerType_value= getwebelement(xml.getlocator("//locators/" + application + "/bearertype_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
			WebElement Bandwidth_value= getwebelement(xml.getlocator("//locators/" + application + "/bandwidth_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
			WebElement vlanIDValue= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanIDValue);
			WebElement IfInOctets_Value= getwebelement(xml.getlocator("//locators/" + application + "/ifinoctets_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
			WebElement IVManagement_Value= getwebelement(xml.getlocator("//locators/" + application + "/ivmanagement_column").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "IfInOctets value is displayed as:"+IVManagement_Value);
			Thread.sleep(2000);

		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Interfaces added under Interfaces panel");
		}
		scrollToTop();
		clickOnBreadCrumb(application, sid);
		Thread.sleep(2000);

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
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on existing Interface checkbox");
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
			compareText(application, "Interface update success message", "successmsg", "Interface successfully updated", xml);

		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Interface is not added");
		}

	}

	public void addTrunkSiteOrder(String application, String trunkGroupOrder, String trunkgroupOrderNumber) throws InterruptedException, DocumentException, IOException { 

		boolean trunkPanel=false;
		boolean siteOrderpage=false;
		boolean trunkgrupOrderErrMsg= false;

		scrolltoend();
		Thread.sleep(2000);

		trunkPanel= getwebelement(xml.getlocator("//locators/" + application + "/trunkPanel")).isDisplayed();
		if(trunkPanel) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");
			System.out.println("'Trunk Group/Site Orders' panel is displaying as expected in 'view Service' page");


			//Perform Add Site order
			click_commonMethod(application, "Add TrunkGroup/SiteOrder link", "addTrunkSiteOrderlink", xml);

			siteOrderpage= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
			if(siteOrderpage) {

				DriverTestcase.logger.log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is displaying as expected");
				System.out.println("'Add Trunk Group/Site Order' page is displaying as expected");

				//verify mandatory Warning Message
				click_commonMethod(application, "OK", "trunk_okButton", xml);

				warningMessage_commonMethod(application, "trunkGrouporder_warningmsg", "Trunk Group Order", xml);

				warningMessage_commonMethod(application, "trunkGroupOrderName_warningmsg", "Trunk group Order Number", xml);

				//Add trunkgroup Order checkbox
				if(trunkGroupOrder.equalsIgnoreCase("yes")) {
					addCheckbox_commonMethod(application, "trunkGroupOrder_checkbox", "Trunk Group Order", trunkGroupOrder, "No", xml);
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " ' Trunk Group order' checkbox is a mandatory field. No values passed");
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

							DriverTestcase.logger.log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
							System.out.println(" Error message we are getting as: "+ actualMsg);
						}
						else if(actualMsg.equalsIgnoreCase("Trunk Group created successfully")) {

							DriverTestcase.logger.log(LogStatus.PASS, " Success Message displays as 'Trunk Group created successfully'");
							System.out.println(" Success Message displays as 'Trunk Group created successfully'");
						}

					}
				}catch(Exception e) {
					e.printStackTrace();

				}

			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "'Add Trunk Group/Site Order' page is not displaying");
				System.out.println("'Add Trunk Group/Site Order' page is not displaying");
			}

		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
			System.out.println("'Trunk Group/Site Orders' panel is not displaying in 'view Service' page");
		}
	}

	public void editSiteOrder(String application, String siteOrderName, String trunkGroupOrder, String trunkGrouporderNumber) throws InterruptedException, DocumentException, IOException {

		boolean siteOrderpage=false;
		boolean trunkgrupOrderErrMsg=false;

		scrolltoend();
		Thread.sleep(3000);

		WebElement editlink=getwebelement("//div[div[span[text()='"+ siteOrderName +"']]]//span[text()='Edit']");
		Clickon(editlink);

		siteOrderpage= getwebelement(xml.getlocator("//locators/" + application + "/addtrunkSiteorderPage_panelheader")).isDisplayed();
		if(siteOrderpage) {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is displaying as expected");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying as expected");

			//Trunk group Order
			if(trunkGroupOrder.equalsIgnoreCase("no")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Trunk Group Order' is a mandatory field. It cannot be unselected");
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

						DriverTestcase.logger.log(LogStatus.FAIL, " Error message we are getting as: "+ actualMsg);
						System.out.println(" Error message we are getting as: "+ actualMsg);
					}
					else if(actualMsg.contains("Trunk Group successfully updated")) {

						DriverTestcase.logger.log(LogStatus.PASS, " Success Message displays as 'Trunk Group successfully updated.'");
						System.out.println(" Success Message displays as 'Trunk Group successfully updated.'");
					}

				}
			}catch(Exception e) {
				e.printStackTrace();

			}

		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "'Edit Trunk Group/Site Order' page is not displaying");
			System.out.println("'Edit Trunk Group/Site Order' page is displaying");
		}
	}

	public void verifyAddedSiteOrderAndTrunkLinkUnderTrunkPanel(String application, String siteOrderName) throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(1000);

		boolean siteOrderUnderTrunkPanel=false;


		siteOrderUnderTrunkPanel=getwebelement("//span[text()='"+ siteOrderName +"']").isDisplayed();

		if(siteOrderUnderTrunkPanel) {

			DriverTestcase.logger.log(LogStatus.PASS, siteOrderName + " 'Site Order' is displaying under 'Trunk' panel");
			System.out.println(siteOrderName + " 'Site Order' is displaying under 'Trunk' panel");

			//Click on Add trunk link	
			String addTrunklinkXpath="//div[div[span[text()='"+ siteOrderName +"']]]/following-sibling::div//span[text()='Add Trunk']";
			click_commonMethod_PassingWebelementDirectly(application, "Add Trunk", addTrunklinkXpath, xml);
			Thread.sleep(1000);

		}
		else {
			DriverTestcase.logger.log(LogStatus.FAIL, siteOrderName + " 'Site Order' is not displaying under 'Trunk' panel");
			System.out.println(siteOrderName + " 'Site Order' is not displaying under 'Trunk' panel");
		}
	}


	public void addTrunk(String application, String customerName, String sid, String country, String CDRdelivery, String gateway, String quality,
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

		Thread.sleep(1000);
		scrolltoend();
		Thread.sleep(2000);


		click_commonMethod(application, "OK", "trunk_okButton", xml);

		scrollToTop();
		Thread.sleep(2000);


		//Trunk Group Description
		String expectedValue1=customerName+"_"+sid+"_"+"IPVL";
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
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Trunk Group Name' length is: "+totalLen);
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
			DriverTestcase.logger.log(LogStatus.FAIL, "'Internet Based Customer' is not checked and disabled by default");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "'Internet Based Customer' is checked and disabled by default");
		}

		//Splitting the Gateway functionality into 2
		if(!gateway.contains("SBC")) {

			if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

				addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet, "Yes", xml);

				String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				if(vlanDefaultvalue.isEmpty()) {
					DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when '3rd Party Internet' is selected");
				}else {

					DriverTestcase.logger.log(LogStatus.PASS, "When '3rd Party Internet' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
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
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text field is disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text fieldis disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}

						ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue2;
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						addressContext=addressContextDefaultValue+vlanUpdatedvalue2;
						compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
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
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue3;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue3;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
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
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values

				}

				if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//3rd party internet
					addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet, "No", xml);

					String vlanDefaultvalue5=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue5.isEmpty()) {
						DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
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
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
			DriverTestcase.logger.log(LogStatus.PASS, " 'MultiSite Default Number' value is displaying as "+MultisiteDefaultNumber);
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
			DriverTestcase.logger.log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);

			if(!callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(callrateLimiteValue);

				if(i>100) {
					DriverTestcase.logger.log(LogStatus.FAIL, "The CallRateLimit should be less than 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "'Call rate Limit' value is not edited");
				System.out.println("'Call rate Limit' value is not edited");
			}
		}

		//Usage of Incoming routing on DDI Level
		addCheckbox_commonMethod(application, "incomingroutingonDDIlevel_checkbox", "Usage of Incoming routing on DDI Level", incomingroutingonDDIlevel_checkbox, "Yes" ,xml);


		//PSX Manual Configuration-Trunk Group 
		addCheckbox_commonMethod(application, "PSXmanualConfigTG_checkbox", "PSX Manual Configuration-Trunk Group", PSXmanualConfigTG_value, "No" ,xml);

		//PSX Manual Configuration-DDI-Range
		addCheckbox_commonMethod(application, "PSXmanualConfigDDIRange_checkbox", "PSX Manual Configuration-DDI-Range", PSXmanualConfigDDI_value, "No" ,xml);


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

		click_commonMethod(application, "OK", "trunk_okButton", xml);
		Thread.sleep(6000);
		compareText(application, "Add Trunk success msg", "successmsg", "Trunk created successfully", xml);
		//verifysuccessmessage(application, "Trunk created successfully", xml);

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
				DriverTestcase.logger.log(LogStatus.PASS, "CLIP Screening and CLIR per Call (DEFAULT) radio button is selected by default");
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
		DriverTestcase.logger.log(LogStatus.PASS, "Value displaying under "+labelname+" is: "+ ls);

	}	

	public void methodToFindMessagesUnderTextField(String application ,String xpath, String labelname, String expectedmsg) {

		boolean defaultPortValueUnderSIPSignalling=false;
		try {	
			defaultPortValueUnderSIPSignalling=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
			if(defaultPortValueUnderSIPSignalling) {

				WebElement defaultValue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
				String defaultValueText= defaultValue.getText();
				if(defaultValueText.contains(expectedmsg)) {
					DriverTestcase.logger.log(LogStatus.PASS, "Under '"+ labelname +"' text field', text message displays as "+ defaultValueText);
					System.out.println("Under '"+ labelname +"' text field', text message displays as "+ defaultValueText);
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Under '"+ labelname +"' text field', text message displays as "+ defaultValueText);
					System.out.println("Under '"+ labelname +"' text field', text message displays as "+ defaultValueText);
				}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
				System.out.println("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
			System.out.println("No text message displays under '"+ labelname +"' text field. It should display as '"+ expectedmsg +"'");
		}
	}

	public void viewTrunk_Primary(String application, String customerName, String serviceid, String country, String CDRdelivery, String gateway, String quality,
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

		//Trunk Group Description
		String expectedValue1=customerName+"_"+serviceid+"_"+"IPVL";
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
		String COSProfile_viewPage=getwebelement("//div[div[label[contains(text(),'COS Profile')]]]/div[2]").getText();
		compareText_InViewPage(application, "COS Profile", COSProfile_viewPage, xml);

		//LAN Range
		compareText_InViewPage(application, "LAN Range", lanrange_value, xml);

		//IP Address Type
		compareText_InViewPage(application, "IP Address Type", ipAddresstype, xml);

		//3rd Party Internet
		String thirdPartyInternet_viewPage=getwebelement("//div[div[label[contains(text(),'3rd Party Internet')]]]/div[2]").getText();
		compareText_InViewPage(application, "3rd Party Internet", thirdPartyInternet_viewPage, xml);

		//Sub Interface Slot
		String subinterfaceSlot_viewPage=getwebelement("//div[div[label[contains(text(),'Sub Interface Slot')]]]/div[2]").getText();
		if(!subInterfaceSlot.equalsIgnoreCase("null"))
		{
			compareText_InViewPage(application, "Sub Interface Slot", subInterfaceSlot, xml);
		}
		else
		{
			compareText_InViewPage(application, "Sub Interface Slot", "null", xml);
		}

		//VLAN Tag 
		if(!gateway.contains("SBC")) {

			if(thirdpartyinternet.equalsIgnoreCase("no")) {

				//VLAN Tag
				//					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
				String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();
				compareText_InViewPage(application, "VLAN Tag", vlan_actualValue, xml);

				//Sub Interface Name
				String SubInterfaceName=subInterfacename_starting+subinterfaceSlot_viewPage+subInterfacename_middle+vlan_actualValue;
				compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);

				//NIF Group
				String NIFgroup=NIFgroupDEfaultValue_starting+subinterfaceSlot_viewPage+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
				System.out.println("NIF Group value is: "+NIFgroup);
				compareText_InViewPage(application, "NIF Group", NIFgroup, xml);

				//Signalling Zone
				String SignallingZone=getwebelement("//div[div[label[contains(text(),'Signalling Zone')]]]/div[2]").getText();
				compareText_InViewPage(application, "Signalling Zone", SignallingZone, xml);

			}
			else if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

				//VLAN Tag
				//					  compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);
				String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();

				//Sub Interface Name
				String SubInterfaceName=subInterfacename_starting+subinterfaceSlot_viewPage+subInterfacename_middle+vlan_actualValue;
				compareText_InViewPage(application, "Sub Interface Name", SubInterfaceName, xml);

				//NIF Group
				String NIFgroup=NIFgroupDEfaultValue_starting+subinterfaceSlot_viewPage+NIFgroupDEfaultValue_middle+vlan_actualValue+NIGgroupdefaultValue_last;
				System.out.println("NIF Group value is: "+NIFgroup);
				compareText_InViewPage(application, "NIF Group", NIFgroup, xml);

				//Signalling Zone
				String SignallingZone=getwebelement("//div[div[label[contains(text(),'Signalling Zone')]]]/div[2]").getText();
				compareText_InViewPage(application, "Signalling Zone", SignallingZone, xml);

			}
		}
		else if(gateway.contains("SBC")) {
			if(thirdpartyinternet.equalsIgnoreCase("Yes")) {
				String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();

				//Address Content
				compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);

				//IP Interface Group
				compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);

				//IP Interface
				compareText_InViewPage(application, "IP Interface", IPINTERFACE+vlan_actualValue, xml);

				//Signalling Zone
				String SignallingZone=getwebelement("//div[div[label[contains(text(),'Signalling Zone')]]]/div[2]").getText();
				compareText_InViewPage(application, "Signalling Zone", SignallingZone, xml);

			}
			else if(thirdpartyinternet.equalsIgnoreCase("No")) {

				//VLAN Tag
				compareText_InViewPage(application, "VLAN Tag", vlanTag, xml);

				String vlan_actualValue=getwebelement("//div[div[label[contains(text(),'VLAN Tag')]]]/div[2]").getText();

				//Address Content
				compareText_InViewPage(application, "Address Context", AddressContext+vlan_actualValue, xml);

				//IP Interface Group
				compareText_InViewPage(application, "IP Interface Group", IPINTERFACEGROUP+vlan_actualValue, xml);

				//IP Interface
				compareText_InViewPage(application, "IP Interface", IPINTERFACE+vlan_actualValue, xml);
			}
		}

		//Signalling Port

		String SignallingPort=getwebelement("//div[div[label[contains(text(),'Signalling Port')]]]/div[2]").getText();
		if(!SignallingPort.equalsIgnoreCase("null"))
		{
			compareText_InViewPage(application, "Signalling Port", SignallingPort, xml);
		}
		else
		{
			compareText_InViewPage(application, "Signalling Port", "null", xml);
		}

		//Number Porting
		compareText_InViewPage(application, "Number Porting", numberporting_checkbox, xml);

		//CLI Features
		String CLIFeatures_viewpage=getwebelement("//div[div[label[contains(text(),'CLI Features')]]]/div[2]").getText();
		compareText_InViewPage(application, "CLI Features", CLIFeatures_viewpage, xml);

		//PresentationNumbers
		//				 String PresentationNumbers_viewpage=getwebelement("//div[div[label[contains(text(),'Presentation Numbers')]]]/div[2]").getText();
		//				 compareText_InViewPage(application, "Presentation Numbers", PresentationNumbers_viewpage, xml);

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
		String InvalidCLITreatment_viewpage=getwebelement("//div[div[label[contains(text(),'Invalid CLI Treatment')]]]/div[2]").getText();
		compareText_InViewPage(application, "Invalid CLI Treatment", InvalidCLITreatment_viewpage, xml);

		//MultiSite Default Number
		if(InvalidCLITreatment_viewpage.equalsIgnoreCase("Allow"))
		{
			compareText_InViewPage(application, "MultiSite Default Number", customerdefaultnumber_value, xml);
		}

		//Egress Number Format
		String EgressNumberFormat_viewpage=getwebelement("//div[div[label[contains(text(),'Egress Number Format')]]]/div[2]").getText();
		compareText_InViewPage(application, "Egress Number Format", EgressNumberFormat_viewpage, xml);

		//Call Admission Control
		compareText_InViewPage(application, "Call Admission Control", callAdmissionControl, xml);
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
		String UsageofIncomingRouting_viewpage=getwebelement("//div[div[label[contains(text(),'Usage of Incoming routing on DDI Level')]]]/div[2]").getText();
		compareText_InViewPage(application, "Usage of Incoming routing on DDI Level", UsageofIncomingRouting_viewpage, xml);

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
		String Codec_viewpage=getwebelement("//div[div[label[contains(text(),'Codec')]]]/div[2]").getText();
		compareText_InViewPage(application, "Codec", Codec_viewpage, xml);

		//Fax Diversion Numbers
		//String FaxDiversionNumbers_viewpage=getwebelement("//div[div[label[contains(text(),'Fax Diversion Numbers')]]]/div[2]").getText();
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


	public void editTrunk(String application, String customerName, String sid, String edit_SIPURI, String edit_SIPsignallingPort, String edit_ipAddresstype, 
			String edit_BillingCountry, String edit_CDRdelivery, String edit_resellerID_value, String edit_gateway, String edit_quality, String edit_cosprofile_value, String edit_lanrange_value, 
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

		Thread.sleep(1000);
		//Action button	
		click_commonMethod(application, "Action", "viewPage_ActionButton", xml);

		//click on Edit link
		click_commonMethod(application, "Edit", "editLink", xml);

		//Trunk Group Description
		String expectedValue1=customerName+"_"+sid+"_"+"IPVL";
		compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

		//VOIP Protocol
		GetText(application, "VOIP Protocol", "voipProtocol_Dropdown");
		String voip_code="S";

		//SIP URI
		edittextFields_commonMethod(application, "SIP URI", "SIPURI_textField", edit_SIPURI, xml);
		String SIPURIvalue=getwebelement(xml.getlocator("//locators/" + application + "/SIPURI_textField")).getAttribute("value");
		System.out.println("SIP URI value is: "+SIPURIvalue);
		DriverTestcase.logger.log(LogStatus.PASS, "SIP URI value is: "+SIPURIvalue);

		//SIP Signalling Port
		edittextFields_commonMethod(application, "SIP Signaling Port", "SIPsignallingport_textField", edit_SIPsignallingPort, xml);
		String SIPsignallingport_value=getwebelement(xml.getlocator("//locators/" + application + "/SIPsignallingport_textField")).getAttribute("value");
		System.out.println("SIP Signalling Port value is: "+SIPsignallingport_value);
		DriverTestcase.logger.log(LogStatus.PASS, "SIP Signalling Port value is: "+SIPsignallingport_value);

		//Billing COuntry
		selectValueInsideDropdown(application, "billingCoutry_Dropdown", "Billing Country", edit_BillingCountry, xml);

		String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
		System.out.println("country dropdown value is: "+country_code);
		DriverTestcase.logger.log(LogStatus.PASS, "country dropdown value is: "+country_code);

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
			DriverTestcase.logger.log(LogStatus.PASS, "CDR Delivery dropdown value is: "+CDR_code);
		}

		//ResellerID
		String ResellerIDValue= resellerID(application, edit_resellerID_value);

		//Customer ID
		String CustomerID= getwebelement(xml.getlocator("//locators/" + application + "/customerid")).getAttribute("value");

		//Gateway
		if(!edit_gateway.equalsIgnoreCase("null"))
		{
			selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", edit_gateway, xml);
			gatewayCode=gateway_code(application, edit_gateway);
			Thread.sleep(2000);
		}
		else
		{
			gatewayCode=getwebelement(xml.getlocator("//locators/" + application + "/gateway_Dropdown")).getAttribute("value");
			System.out.println("Gateway dropdown value is: "+gatewayCode);
			DriverTestcase.logger.log(LogStatus.PASS, "Gateway dropdown value is: "+gatewayCode);
		}

		//Quality
		selectValueInsideDropdown(application, "quality_Dropdown", "Quality", edit_quality, xml);

		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");
		System.out.println("Quality dropdown value is: "+quality_code);
		DriverTestcase.logger.log(LogStatus.PASS, "Quality dropdown value is: "+quality_code);

		//Trunk Name
		String trunkName=country_code+gatewayCode+voip_code+"L"+CustomerID+"01"+CDR_code+ResellerIDValue+quality_code+"00";
		int totalLen=trunkName.length();
		System.out.println("Total lenth of 'Trunk Group' field is "+ totalLen);
		System.out.println("Trunk name is "+ trunkName);
		if(totalLen==20) {
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunkName, xml);
			primarytrunkGroupname=trunkName;
			System.out.println("Trunk group name is: "+primarytrunkGroupname);
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Trunk Group Name' length is: "+totalLen);
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunkName, xml);
		}

		//COS Profile
		selectValueInsideDropdown(application, "cosprofile", "COS Profile", edit_cosprofile_value, xml);
		String COSProfileValue=getwebelement(xml.getlocator("//locators/" + application + "/cosprofile")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "COS Profile dropdown value is: "+COSProfileValue);


		//LAN Range
		edittextFields_commonMethod(application, "LAN Range", "lanrange", edit_lanrange_value, xml);
		String LANRangevalue=getwebelement(xml.getlocator("//locators/" + application + "/lanrange")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "LAN Range value is: "+LANRangevalue);


		//IP Address Type
		selectValueInsideDropdown(application, "IPaddresstype_Dropdown", "IP Address Type", edit_ipAddresstype, xml);
		Thread.sleep(2000);
		String IPAddressTypevalue=getwebelement(xml.getlocator("//locators/" + application + "/IPaddresstype_Dropdown")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "IP Address Type dropdown value is: "+IPAddressTypevalue);

		//Internet Based Customer
		String InternetBasedCustomer_Checked= getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox")).getAttribute("checked");
		String InternetBasedCustomer_Disabled= getwebelement(xml.getlocator("//locators/" + application + "/internetBasedCustomer_checkbox")).getAttribute("disabled");
		if(InternetBasedCustomer_Checked==null && InternetBasedCustomer_Disabled==null)
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "'Internet Based Customer' is not checked and disabled by default");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "'Internet Based Customer' is checked and disabled by default");
		}

		//Splitting the Gateway functionality into 2
		if(!edit_gateway.contains("SBC")) {

			if(edit_thirdpartyinternet.equalsIgnoreCase("Yes")) {

				editcheckbox_commonMethod(application, edit_thirdpartyinternet, "thirdpartyinternet_checkbox", "3rd Party Internet", xml);
				String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				if(vlanDefaultvalue.isEmpty()) {
					DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when '3rd Party Internet' is selected");
				}else {

					DriverTestcase.logger.log(LogStatus.PASS, "When '3rd Party Internet' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
					if(edit_vlanTag.equalsIgnoreCase("null")) {

						//Sub Interface slot
						selectValueInsideDropdown(application, "subInterfaceSlot_Dropdown", "Sub Interface Slot", edit_subInterfaceSlot, xml);
						String SubInterfaceSlot_value=getwebelement(xml.getlocator("//locators/" + application + "/subInterfaceSlot_Dropdown")).getAttribute("value");
						DriverTestcase.logger.log(LogStatus.PASS, "Sub Interface Slot dropdown value is displaying as "+SubInterfaceSlot_value);

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
						DriverTestcase.logger.log(LogStatus.PASS, "Sub Interface Slot dropdown value is displaying as "+SubInterfaceSlot_value);

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
					DriverTestcase.logger.log(LogStatus.PASS, "Sub Interface Slot dropdown value is displaying as "+SubInterfaceSlot_value);

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
					DriverTestcase.logger.log(LogStatus.PASS, "Sub Interface Slot dropdown value is displaying as "+SubInterfaceSlot_value);

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
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text field is disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text fieldis disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}

						ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue2;
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						addressContext=addressContextDefaultValue+vlanUpdatedvalue2;
						compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
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
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue3;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue3;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
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
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values

				}

				if(edit_thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//3rd party internet
					addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", edit_thirdpartyinternet, "No", xml);

					String vlanDefaultvalue5=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue5.isEmpty()) {
						DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
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
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					ipInterfaceGroup=ipInterfaceGroupDefaultvalue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField",ipInterfaceGroup , xml);    //verify default values
				}
			}
		}

		//Signalling Zone
		edittextFields_commonMethod(application, "Signaling Zone", "signallingZone_textField", edit_signallingZone, xml);
		String SignallingZone_Value=getwebelement(xml.getlocator("//locators/" + application + "/signallingZone_textField")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "Signalling Zone value is: "+SignallingZone_Value);

		//Number Porting checkbox
		editcheckbox_commonMethod(application, edit_numberporting_checkbox, "numberporting_checkbox", "Number Porting", xml);

		//CLI Features
		cliFeatures(application, edit_CLIPScreeningandCLIRperCall, edit_clirPermanent, edit_clipNoScreening, edit_clipMainNumber, edit_presentationNumbers, edit_presentationnumber_value);

		//Customer Default Number
		edittextFields_commonMethod(application, "Customer Default Number", "customerdefaultnumber_textfield", edit_customerdefaultnumber_value, xml);
		String CustomerDefaultNumber_Value=getwebelement(xml.getlocator("//locators/" + application + "/customerdefaultnumber_textfield")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "Customer Default Number value is: "+CustomerDefaultNumber_Value);

		//Invalid CLI Treatment
		boolean invalidCLITreatment_Allow= getwebelement(xml.getlocator("//locators/" + application + "/invalidclitreatment_allow")).isSelected();

		if(invalidCLITreatment_Allow)
		{
			//Multisite Default Number
			String MultisiteDefaultNumber= getwebelement(xml.getlocator("//locators/" + application + "/multisitedefaultnumber")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, " 'MultiSite Default Number' value is displaying as "+MultisiteDefaultNumber);
		}
		else
		{
			click_commonMethod(application, "Block", "invalidclitreatment_block", xml);
		}

		//Egress Number Format
		selectValueInsideDropdown(application, "egressnumberformat_dropdown", "Egress Number Format", edit_egressnumberformat_dropdownvalue, xml);
		String EgressNumberFormat_Value=getwebelement(xml.getlocator("//locators/" + application + "/egressnumberformat_dropdown")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "Egress Number Format value is: "+EgressNumberFormat_Value);

		//Call Admission Control
		editcheckbox_commonMethod(application, edit_callAdmissionControl, "callAdmissionControl_checkbox", "Call Admission Control", xml);

		if(edit_callAdmissionControl.equalsIgnoreCase("yes")) {

			//Call Limit
			selectValueInsideDropdown(application, "callLimit_Dropdown", "Call Limit", edit_callLimit, xml);
			String CallLimit_Value=getwebelement(xml.getlocator("//locators/" + application + "/callLimit_Dropdown")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, "Call Limit value is: "+CallLimit_Value);

			if(edit_callLimit.equalsIgnoreCase("Defined")) {
				edittextFields_commonMethod(application, "Limit Number", "limitNumber_textField", edit_limitNumber, xml);
				String LimitNumber_Value=getwebelement(xml.getlocator("//locators/" + application + "/limitNumber_textField")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, "Limit Number value is: "+LimitNumber_Value);
			}
		}

		//Call Rate Limit
		editcheckbox_commonMethod(application, edit_callrateLimit, "callrateLimit_checkbox", "Call Rate Limit", xml);
		if(edit_callrateLimit.equalsIgnoreCase("yes")) {

			String callratelimitactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/callRateLimitt_textField")).getAttribute("value");
			System.out.println(" 'Call rate Limit' value is displaying as "+callratelimitactualvalue);
			DriverTestcase.logger.log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);

			if(!edit_callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(edit_callrateLimiteValue);

				if(i>100) {
					DriverTestcase.logger.log(LogStatus.FAIL, "The CallRateLimit should be less than 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", edit_callrateLimiteValue, xml);
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "'Call rate Limit' value is not edited");
				System.out.println("'Call rate Limit' value is not edited");
			}
		}

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
		DriverTestcase.logger.log(LogStatus.PASS, "Codec dropdown value is: "+Codec_Value);

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
			DriverTestcase.logger.log(LogStatus.PASS, "Fax Diversion Numbers value is: "+FaxDiversionNumber_Value);
		}

		//Partial Number Replacement
		editcheckbox_commonMethod(application, edit_partialnumberreplacement_checkbox, "partialnumberreplacement_checkbox", "Partial Number Replacement", xml);
		Thread.sleep(1000);

		click_commonMethod(application, "OK", "trunk_okButton", xml);
		Thread.sleep(6000);
		compareText(application, "Update Trunk success message", "successmsg", "Trunk updated successfully", xml);
		//verifysuccessmessage(application, "Trunk updated successfully", xml);

	}

	public void addResilienttrunk(String application, String customerName, String sid, String resilient_country, String Resilient_CDRdelivery, String resilient_gateway, String resilient_quality,
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

		Thread.sleep(2000);

		//Trunk Group Description
		String expectedValue1=customerName+"_"+sid+"_"+"IPVL";
		compareText_fromtextFields(application, "Trunk Group Description", "trunkGroupDEscription_textField", expectedValue1, xml);

		//Primary trunk group
		WebElement PrimaryTrunkGroup= getwebelement(xml.getlocator("//locators/" + application + "/primaryTrunkGroup_Dropdown"));
		selectValueInsideDropdown(application, "primaryTrunkGroup_Dropdown", "Primary Trunk Group", primarytrunkGroupname, xml);
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
		methodToFindMessagesUnderTextField(application, "SIPsignallingPOrt_defaultValue_textvalue", "SIP Signalling Port", "Default Port:5060");

		//Billing Country
		warningMessage_commonMethod(application, "country_warningMessage", "Billing Country", xml);   //validate warning message
		selectValueInsideDropdown(application, "billingCoutry_Dropdown", "Billing Country", resilient_country, xml);

		String country_code=getwebelement(xml.getlocator("//locators/" + application + "/billingCoutry_Dropdown")).getAttribute("value");
		System.out.println("country dropdown value is: "+country_code);

		//CDR Delivery
		selectValueInsideDropdown(application, "CDRdelivery_Dropdown", "CDR Delivery", Resilient_CDRdelivery, xml);
		String CDR_code=CDRdelivery_code(application, Resilient_CDRdelivery);

		//ResellerID
		String ResellerIDValue= resellerID(application, resilient_resellerID_value);

		//Customer ID
		String CustomerID= getwebelement(xml.getlocator("//locators/" + application + "/customerid")).getAttribute("value");

		//Gateway
		selectValueInsideDropdown(application, "gateway_Dropdown", "Gateway", resilient_gateway, xml);
		gatewayCode=gateway_code(application, resilient_gateway);
		Thread.sleep(2000);

		//Quality
		selectValueInsideDropdown(application, "quality_Dropdown", "Quality", resilient_quality, xml);
		String quality_code=getwebelement(xml.getlocator("//locators/" + application + "/quality_Dropdown")).getAttribute("value");

		//Trunk Name
		String trunkName=country_code+gatewayCode+voip_code+"L"+CustomerID+"01"+CDR_code+ResellerIDValue+quality_code+"00";

		int totalLen=trunkName.length();
		System.out.println("Total lenth of 'Trunk Group' field is "+ totalLen);
		System.out.println("Trunk name is "+ trunkName);
		if(totalLen==20) {
			compareText_fromtextFields(application, "Trunk Group Name", "trunkGroupName_TextField", trunkName, xml);
			primarytrunkGroupname_Resilient=trunkName;
			System.out.println("Trunk group name is: "+primarytrunkGroupname_Resilient);
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Trunk Group Name' length is: "+totalLen);
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
			DriverTestcase.logger.log(LogStatus.FAIL, "'Internet Based Customer' is not checked and disabled by default");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "'Internet Based Customer' is checked and disabled by default");
		}

		//Splitting the Gateway functionality into 2
		if(!resilient_gateway.contains("SBC")) {

			if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

				addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet, "Yes", xml);

				String vlanDefaultvalue=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
				if(vlanDefaultvalue.isEmpty()) {
					DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when '3rd Party Internet' is selected");
				}else {

					DriverTestcase.logger.log(LogStatus.PASS, "When '3rd Party Internet' is selected, 'VLAN tag' field value is displaying as "+vlanDefaultvalue);
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
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text field is disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
						}else {
							System.out.println("'Ip Interface' text fieldis disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
						}

						ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue2;
						compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

						//Address Context
						boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
						if(addressContext_Enabled) {
							System.out.println("'Address Context' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
						}else {
							System.out.println("'Address Context' text fieldis disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
						}

						addressContext=addressContextDefaultValue+vlanUpdatedvalue2;
						compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


						//IP Interface Group
						boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
						if(ipInterfaceGroup_Enabled) {
							System.out.println("'IP Inteface Group' text field is enabled");
							DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
						}else {
							System.out.println("'IP Inteface Group' text field is disabled");
							DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
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
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue3;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue3;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
						DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
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
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField", ipInterfaceDEfaultValue, xml);  //verify default values

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					compareText_fromtextFields(application, "Address Context", "AddressContext_textField", addressContextDefaultValue, xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
					}

					compareText_fromtextFields(application, "IP Interface Group", "ipInterfaceGroup_textField", ipInterfaceGroupDefaultvalue, xml);    //verify default values

				}

				if(thirdpartyinternet.equalsIgnoreCase("Yes")) {

					//3rd party internet
					addCheckbox_commonMethod(application, "thirdpartyinternet_checkbox", "3rd Party Internet", thirdpartyinternet, "No", xml);

					String vlanDefaultvalue5=getwebelement(xml.getlocator("//locators/" + application + "/vlanTag_textField")).getAttribute("value");
					if(vlanDefaultvalue5.isEmpty()) {
						DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under 'VLAN tag' field by default, when 'Thrid Party Internet' is selected");
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
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Ip Interface' text field is enabled");
					}else {
						System.out.println("'Ip Interface' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Ip Interface' text field is disabled");
					}

					ipInterface = ipInterfaceDEfaultValue +vlanUpdatedvalue5;
					compareText_fromtextFields(application, "IP Interface", "ipInterface_textField",ipInterface , xml);  

					//Address Context
					boolean addressContext_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/AddressContext_textField")).isEnabled();
					if(addressContext_Enabled) {
						System.out.println("'Address Context' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Address Context' text field is enabled");
					}else {
						System.out.println("'Address Context' text fieldis disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'Address Context' text field is disabled");
					}

					addressContext=addressContextDefaultValue+vlanUpdatedvalue5;
					compareText_fromtextFields(application, "Address Context", "AddressContext_textField",addressContext , xml);  //verify default values


					//IP Interface Group
					boolean ipInterfaceGroup_Enabled=getwebelement(xml.getlocator("//locators/" + application + "/ipInterfaceGroup_textField")).isEnabled();
					if(ipInterfaceGroup_Enabled) {
						System.out.println("'IP Inteface Group' text field is enabled");
						DriverTestcase.logger.log(LogStatus.FAIL, " 'IP Inteface Group' text field is enabled");
					}else {
						System.out.println("'IP Inteface Group' text field is disabled");
						DriverTestcase.logger.log(LogStatus.PASS, "'IP Inteface Group' text field is disabled");
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
			DriverTestcase.logger.log(LogStatus.PASS, " 'MultiSite Default Number' value is displaying as "+MultisiteDefaultNumber);
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
			DriverTestcase.logger.log(LogStatus.PASS, " 'Call rate Limit' value is displaying as "+callratelimitactualvalue);

			if(!callrateLimiteValue.equalsIgnoreCase("null")) {
				int i=Integer.parseInt(callrateLimiteValue);

				if(i>100) {
					DriverTestcase.logger.log(LogStatus.FAIL, "The CallRateLimit should be less than 100 for all Trunks");
				}
				else if(i<=100){
					edittextFields_commonMethod(application, "Call rate Limit", "callRateLimitt_textField", callrateLimiteValue, xml);
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "'Call rate Limit' value is not edited");
				System.out.println("'Call rate Limit' value is not edited");
			}
		}

		//Usage of Incoming routing on DDI Level
		addCheckbox_commonMethod(application, "incomingroutingonDDIlevel_checkbox", "Usage of Incoming routing on DDI Level", incomingroutingonDDIlevel_checkbox, "Yes" ,xml);


		//PSX Manual Configuration-Trunk Group 
		addCheckbox_commonMethod(application, "PSXmanualConfigTG_checkbox", "PSX Manual Configuration-Trunk Group", PSXmanualConfigTG_value, "No" ,xml);

		//PSX Manual Configuration-DDI-Range
		addCheckbox_commonMethod(application, "PSXmanualConfigDDIRange_checkbox", "PSX Manual Configuration-DDI-Range", PSXmanualConfigDDI_value, "No" ,xml);


		if(resilient_gateway.contains("SBC")) {
			//Manual Configuration on SBC
			addCheckbox_commonMethod(application, "SBCmanualconfig_checkbox", "Manual Configuration On SBC", SBCmanualconfigValue, "No", xml);
		}else {
			//Manual Configuration On GSX
			addCheckbox_commonMethod(application, "GSXmanualConfig_checkbox", "Manual Configuration On GSX", GSXmanualConfigvalue, "No", xml);
		}

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

		click_commonMethod(application, "OK", "trunk_okButton", xml);
		Thread.sleep(6000);
		compareText(application, "Create Trunk success message", "successmsg", "Trunk created successfully", xml);
		//verifysuccessmessage(application, "Trunk created successfully", xml);

	}

	public void verifyAddVoiceCPEDevice(String application, String voiceCPE_devicename, String voiceCPE_vendormodel, 
			String voiceCPE_managementaddress, String voiceCPE_country, String voiceCPE_existingcity, String voiceCPE_existingcityvalue, 
			String voiceCPE_existingsite, String voiceCPE_existingsitevalue, String voiceCPE_existingpremise, String voiceCPE_existingpremisevalue, String voiceCPE_newcity,
			String voiceCPE_cityname,String voiceCPE_Citycode, String voiceCPE_sitename, String voiceCPE_sitecode, String voiceCPE_premisename, String voiceCPE_premisecode, 
			String voiceCPE_newsite, String voiceCPE_NewPremise, String cpetoprovidedialtone_checkbox, String cpelinepowerrequired_checkbox, String numberporting_checkbox, 
			String briportmapping_checkbox, String crcsettings_dropdownvalue, String numberofpriports_dropdownvalue, String numberofbriports_dropdownvalue, 
			String briport1_value, String briport2_value, String briport3_value, String briport4_value, String briport5_value, String briport6_value, String briport7_value, 
			String briport8_value, String numberofFXSports_dropdownvalue, String fxsnumber1_value, String fxsnumber2_value) throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Add Voice CPE Device", "addvoicecpe_link", xml);
		Thread.sleep(2000);
		compareText(application, "Add VOice CPE header", "addvoicecpe_header", "Add Voice CPE", xml);
		cleartext(application, "Name", "voicecpe_devicename");
		addtextFields_commonMethod(application, "Name", "voicecpe_devicename", voiceCPE_devicename, xml);
		addDropdownValues_commonMethod(application, "Vendor/Model", "vendormodel_dropdown", voiceCPE_vendormodel, xml);
		addtextFields_commonMethod(application, "Management Address/Mask [e.g. 10.0.0.0/27]", "managementaddress_voicecpe", voiceCPE_managementaddress, xml);
		compareText_fromtextFields(application, "Snmpro", "snmpro_textfield", "incc", xml);

		//select country
		scrolltoend();
		Thread.sleep(2000);
		addDropdownValues_commonMethod(application, "Country", "countryinput", voiceCPE_country, xml);

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

			addDropdownValues_commonMethod(application, "City", "citydropdowninput", voiceCPE_existingcityvalue, xml);


			//Existing Site
			if(voiceCPE_existingsite.equalsIgnoreCase("yes") & voiceCPE_newsite.equalsIgnoreCase("no")) {
				addDropdownValues_commonMethod(application, "Site", "sitedropdowninput", voiceCPE_existingsitevalue, xml);

				//Existing Premise
				if(voiceCPE_existingpremise.equalsIgnoreCase("yes") & voiceCPE_NewPremise.equalsIgnoreCase("no")) {
					addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", voiceCPE_existingpremisevalue, xml);
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

		addCheckbox_commonMethod(application, "cpetoprovidedialtone_checkbox", "CPE to Provide Dial Tone", cpetoprovidedialtone_checkbox, "No", xml);
		addCheckbox_commonMethod(application, "cpelinepowerrequired_checkbox", "CPE Line Power Required", cpelinepowerrequired_checkbox, "No", xml);
		addCheckbox_commonMethod(application, "numberporting_checkbox", "Number Porting", numberporting_checkbox, "No", xml);
		addCheckbox_commonMethod(application, "briportmapping_checkbox", "BRI Port Mapping", briportmapping_checkbox, "No", xml);
		addDropdownValues_commonMethod(application, "CRC Settings", "crcsettings_dropdown", crcsettings_dropdownvalue, xml);
		addDropdownValues_commonMethod(application, "Number of PRI Ports", "numberofpriports_dropdown", numberofpriports_dropdownvalue, xml);
		addDropdownValues_commonMethod(application, "Number of BRI Ports", "numberofbriports_dropdown", numberofbriports_dropdownvalue, xml);
		numberofBRIPorts(application, numberofbriports_dropdownvalue, briport1_value, briport2_value, briport3_value, briport4_value, briport5_value, briport6_value, briport7_value, briport8_value);
		Thread.sleep(1000);
		scrolltoend();
		addDropdownValues_commonMethod(application, "Number of FXS Ports", "numberoffxsports_dropdown", numberofFXSports_dropdownvalue, xml);
		NumberofFXSPorts(application, numberofFXSports_dropdownvalue, fxsnumber1_value, fxsnumber2_value);
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
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

		Thread.sleep(2000);
		scrolltoend();

		//	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		//	{
		//		List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevicename"));
		//		System.out.println(addeddevicesList);
		//		int AddedDevicesCount= addeddevicesList.size();
		//		
		//		for(int i=0;i<AddedDevicesCount;i++) {
		//			String AddedDeviceNameText= addeddevicesList.get(i).getText();
		//			System.out.println(AddedDeviceNameText);
		//			String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
		//			String AddedDevice_ViewLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_viewlink").replace("value", AddedDevice_SNo)).getText();
		//			String AddedDevice_EditLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_editlink").replace("value", AddedDevice_SNo)).getText();
		//			String AddedDevice_DeletefromServiceLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_Deletefromservicelink").replace("value", AddedDevice_SNo)).getText();
		//			
		//		}
		//		}

		compareText(application, "Voice CPE Device", "viewpage_voicecpedevice_header", "Voice CPE Device", xml);
		compareText_InViewPage(application, "Name", voiceCPE_devicename, xml);
		compareText_InViewPage(application, "Vendor/Model", voiceCPE_vendormodel, xml);
		compareText_InViewPage(application, "Management Address", voiceCPE_managementaddress, xml);
		compareText_InViewPage(application, "Snmpro", "incc", xml);
		compareText_InViewPage(application, "Country", voiceCPE_country, xml);
		GetText(application, "City", "city_voiceCPE_viewpage");
		GetText(application, "Site", "site_voiceCPE_viewpage");
		GetText(application, "Premise", "premise_voiceCPE_viewpage");

		compareText_InViewPage(application, "CPE to Provide Dial tone", cpetoprovidedialtone_checkbox, xml);
		compareText_InViewPage(application, "CPE line power required", cpelinepowerrequired_checkbox, xml);
		compareText_InViewPage(application, "Number Porting", numberporting_checkbox, xml);
		compareText_InViewPage(application, "BRI Port Mapping", briportmapping_checkbox, xml);
		compareText_InViewPage(application, "CRC Settings", crcsettings_dropdownvalue, xml);
		compareText_InViewPage(application, "Number of BRI ports", numberofbriports_dropdownvalue, xml);
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

		compareText_InViewPage(application, "Number of PRI ports", numberofpriports_dropdownvalue, xml);
		compareText_InViewPage(application, "Number of FXS ports", numberofFXSports_dropdownvalue, xml);


	}

	public void EditVoiceCPEDevice(String application) {

		scrolltoend();

	}

	public void VerifyVoiceResiliency(String application, String backupnumber_checkbox, String Obackupnumber_value, String billingnumber_value) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "ddirange_header", xml);
		Thread.sleep(2000);
		compareText(application, "Voice Resiliency header", "voiceresiliency_header", "Voice Resiliency", xml);
		GetText(application, "Backup Number", "backupnumber_value");
		GetText(application, "OBackup Number", "obackupnumber_value");
		GetText(application, "Billing Number", "billingnumber_value");
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "voiceresiliency_actiondropdown", xml);
		click_commonMethod(application, "Edit", "voiceresiliency_Edit", xml);
		Thread.sleep(2000);
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
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Edit link is not working under Voice Resiliency panel");
		}

		//ScrolltoElement(application, "ddirange_header", xml);
		//Thread.sleep(1000);
		GetText(application, "Backup Number", "backupnumber_value");
		GetText(application, "OBackup Number", "obackupnumber_value");
		GetText(application, "Billing Number", "billingnumber_value");
		Thread.sleep(1000);
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
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
		compareText(application, "Service delete success message", "deletesuccessmsg", "Service successfully deleted.", xml);
		Thread.sleep(2000);
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
			// Get Tab name
			String pageTitle=driver.switchTo().window(browserTabs .get(1)).getTitle();
			System.out.println("page title displays as: "+pageTitle);

			assertEquals(pageTitle, expectedPageName, " on clicking 'Show Infovista link', it got naviagted to "+pageTitle);

			Thread.sleep(3000);
			driver.switchTo().window(browserTabs.get(0)); 

			DriverTestcase.logger.log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
			Thread.sleep(3000);

			DriverTestcase.logger.log(LogStatus.PASS, "show info vista page actual title: "+pageTitle );
			DriverTestcase.logger.log(LogStatus.PASS, "show info vista page expected title: "+ expectedPageName);

		}catch(Exception e) {

			e.printStackTrace();

			Thread.sleep(3000);
			driver.switchTo().window(browserTabs.get(0));

			DriverTestcase.logger.log(LogStatus.FAIL, expectedPageName + " page is not displaying");

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

}
