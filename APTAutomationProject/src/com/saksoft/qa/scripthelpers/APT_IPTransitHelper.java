package com.saksoft.qa.scripthelpers;

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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.driverlibrary.Log;
import com.saksoft.qa.driverlibrary.XMLReader;


public class APT_IPTransitHelper extends DriverHelper {

	public APT_IPTransitHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/APT_IPTransit.xml");

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
		addtextFields_commonMethod(application, "OCN", "ocntextfield", ocn, xml);
		addtextFields_commonMethod(application, "Reference", "referencetextfield", reference, xml);
		addtextFields_commonMethod(application, "Technical Contact Name", "technicalcontactnametextfield", tcn, xml);
		addtextFields_commonMethod(application, "Email", "emailtextfield", email, xml);
		addtextFields_commonMethod(application, "Phone", "phonetextfield", phone, xml);
		addtextFields_commonMethod(application, "Fax", "faxtextfield", fax, xml);
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
		if (neworder.equalsIgnoreCase("YES")) {

			ScrolltoElement(application, "CreateOrderHeader", xml);
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
					List<WebElement> listofvalues = getwebelements("//div[@class='sc-ifAKCX oLlzc']");

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
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

	}




	public void verifyservicecreation(String application, String sid, String Remarks, String orderno, String rfireqno, String servicetype, String terminationdate, String billingtypevalue, String email, String phonecontact, String performancereporting_checkbox, String ipguardian_checkbox) throws InterruptedException, IOException, DocumentException {

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
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to create order page");
		}

		//Create service
		ScrolltoElement(application, "CreateOrderHeader", xml);
		addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno, xml);
		addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		// click on next button
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(2000);
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
		addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", phonecontact, xml);
		click_commonMethod(application, "phone contact adding Arrow", "phoneaddarrow", xml);
		scrolltoend();

		// management options panel
		if(performancereporting_checkbox.equalsIgnoreCase("Yes"))
		{
			addCheckbox_commonMethod(application, "performancereporting_checkbox", "Performance Reporting", performancereporting_checkbox, "no", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Performance Reporting checkbox is not checked");
		}

		if(ipguardian_checkbox.equalsIgnoreCase("Yes"))
		{
			addCheckbox_commonMethod(application, "ipguardian_checkbox", "IP Guardian", ipguardian_checkbox, "no", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : IP Guardian checkbox is not checked");
		}

		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(2000);
		compareText(application, "Service creation success msg", "servicecreationmessage", "Service created successfully", xml);
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
		ScrolltoElement(application, "customerdetailsheader", xml);
		compareText(application, "Login column", "LoginColumn", Login, xml);
		compareText(application, "Name column", "NameColumn", Name, xml);
		compareText(application, "Email column", "EmailColumn", Email, xml);
		compareText(application, "Roles column", "RolesColumn", Roles, xml);
		compareText(application, "Address column", "AddressColumn", Address, xml);
		compareText(application, "Resource column", "ResourcesColumn", Resource, xml);

	}


	public void createnewuser(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email,String Phone, String EditUsername, String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone, String ipguardianaccountgroup, String coltonlineuser, String selectrole)
			throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "customerdetailsheader", xml);
		WebElement UserGridCheck= getwebelement(xml.getlocator("//locators/" + application + "/usergridcheck"));
		String UserGrid= UserGridCheck.getAttribute("style");

		if(UserGrid.contains("height: 1px"))
		{
			//Cancel User
			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Add", "AddLink", xml);
			compareText(application, "Create User Header", "CreateUserHeader", "Create User", xml);
			ScrolltoElement(application, "cancelbutton", xml);
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			Thread.sleep(2000);

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

			//			//Verify Roles field
			//			try{
			//
			//				List<WebElement> Rolesvalue= driver.findElements(By.xpath("//select[@id='availableRole']//option"));
			//				int Rolesvalue_count= Rolesvalue.size();
			//				DriverTestcase.logger.log(LogStatus.PASS, "Step : Available Roles are: ");
			//				for(int i=0; i<Rolesvalue_count; i++)
			//				{
			//					String Rolesvaluetext= Rolesvalue.get(i).getText();
			//					DriverTestcase.logger.log(LogStatus.PASS, "Step : " +Rolesvaluetext);
			//					if(selectrole.contains(Rolesvaluetext))
			//					{
			//						Rolesvalue.get(i).click();
			//						click_commonMethod(application, "Roles field add arrow", "rolesaddarrow", xml);
			//					}
			//				}
			//			}catch(Exception e) {
			//				e.printStackTrace();
			//				DriverTestcase.logger.log(LogStatus.FAIL, "Step : No available roles to display");
			//			}
			//			try{
			//				List<WebElement> SelectedRoles= driver.findElements(By.xpath("//select[@id='selectedRole']//option"));
			//				int SelectedRoles_count= SelectedRoles.size();
			//				DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected Roles are: ");
			//				for(int j=0;j<SelectedRoles_count;j++) 
			//				{
			//					if(SelectedRoles_count>=1)
			//					{
			//						String Selectedroletext= SelectedRoles.get(j).getText();
			//						DriverTestcase.logger.log(LogStatus.PASS, "Step : " +Selectedroletext);
			//					}
			//				}
			//			}catch(Exception e) {
			//				e.printStackTrace();
			//				DriverTestcase.logger.log(LogStatus.FAIL, "Step : No selected to display");
			//			}

			scrolltoend();
			click_commonMethod(application, "OK", "user_okbutton", xml);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : User added successfully");

			//Edit User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers= getwebelements(xml.getlocator("//locators/" + application + "/existinguser_row"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/" + application + "/addeduser_checkbox").replace("value", "Username"));
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
			cleartext(application, "Email", "adduser_Email");
			addtextFields_commonMethod(application, "Email", "adduser_Email", EditEmail, xml);
			cleartext(application, "Phone", "adduser_Phone");
			addtextFields_commonMethod(application, "Phone", "adduser_Phone", EditPhone, xml);
			cleartext(application, "Password", "Password");
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			scrolltoend();
			click_commonMethod(application, "OK", "user_okbutton", xml);

			//View User
			Thread.sleep(2000);
			ScrolltoElement(application, "customerdetailsheader", xml);
			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/" + application + "/addeduser_checkbox").replace("value", "Username"));
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
			Thread.sleep(2000);
			ScrolltoElement(application, "customerdetailsheader", xml);
			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/" + application + "/addeduser_checkbox").replace("value", "Username"));
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
				Thread.sleep(2000);
				compareText(application, "Customer Delete success msg", "deletesuccessmsg", "User successfully deleted", xml);
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			
		}

		else if(!UserGrid.contains("1px"))
		{
			//Edit User
			Thread.sleep(2000);
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers= getwebelements(xml.getlocator("//locators/" + application + "/existinguser_row"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/" + application + "/addeduser_checkbox").replace("value", "Username"));
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
			cleartext(application, "Email", "adduser_Email");
			addtextFields_commonMethod(application, "Email", "adduser_Email", EditEmail, xml);
			cleartext(application, "Phone", "adduser_Phone");
			addtextFields_commonMethod(application, "Phone", "adduser_Phone", EditPhone, xml);
			cleartext(application, "Password", "Password");
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			scrolltoend();
			click_commonMethod(application, "OK", "user_okbutton", xml);

			//View User
			Thread.sleep(2000);
			ScrolltoElement(application, "customerdetailsheader", xml);
			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/" + application + "/addeduser_checkbox").replace("value", "Username"));
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
			}
			else
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");

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
			Thread.sleep(2000);
			ScrolltoElement(application, "customerdetailsheader", xml);
			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/" + application + "/addeduser_checkbox").replace("value", "Username"));
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
				Thread.sleep(2000);
				compareText(application, "Customer Delete success msg", "deletesuccessmsg", "User successfully deleted", xml);
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}

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
		addtextFields_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", editvoicelineno, xml);
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
		List<WebElement> ChangeOrder_DropdownList= getwebelements(xml.getlocator("//locators/" + application + "/changeorder_dropdownlist"));
		int ChangeOrder_Dropdown_count= ChangeOrder_DropdownList.size();
		if(ChangeOrder_Dropdown_count> 1)
		{
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/changeorder_dropdownvalue")));
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
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/changeorder_dropdownvalue")));
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

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String Remarks, String terminationdate, String billingtypevalue, String email, String phonecontact) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "orderpanelheader", xml);
		compareText(application, "Service panel Header", "servicepanel_header", "Service", xml);
		compareText(application, "Service Identification", "servicepanel_serviceidentificationvalue", sid, xml);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype, xml);
		compareText(application, "Termination Date", "servicepanel_terminationdate", terminationdate, xml);
		compareText(application, "Email", "servicepanel_email", email, xml);
		compareText(application, "Phone Contact", "servicepanel_phone", phonecontact, xml);
		compareText(application, "Billing Type", "servicepanel_billingtype", billingtypevalue, xml);
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks, xml);
	}

	public void verifyservicepanel_links(String application, String EditRemarks, String Remarks, String sid, String editterminationdate, String editbillingtypevalue, String editemail, String editphonecontact, String edit_performancereporting_checkbox, String edit_ipguardian_checkbox) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service
		Thread.sleep(2000);
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		ScrolltoElement(application, "orderpanelheader", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to view service page");
		}
		else
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");

		//Edit service
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		Thread.sleep(2000);
		scrollToTop();
		cleartext(application, "Service Identification", "serviceidentificationtextfield");
		addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
		cleartext(application, "Termination Date", "terminationdate_field");
		addtextFields_commonMethod(application, "Termination Date", "terminationdate_field", editterminationdate, xml);
		cleartext(application, "Billing Type", "billingtype_dropdown");
		addDropdownValues_commonMethod(application, "Billing Type", "billingtype_dropdown", editbillingtypevalue, xml);
		cleartext(application, "Remarks", "remarktextarea");
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
		//Edit email
		click_commonMethod(application, "Selected Email", "selectedemail", xml);
		click_commonMethod(application, "Email remove arrow", "emailremovearrow", xml);
		cleartext(application, "Email", "emailtextfieldvalue");
		addtextFields_commonMethod(application, "Email", "emailtextfieldvalue", editemail, xml);
		click_commonMethod(application, "Email adding arrow", "emailaddarrow", xml);
		//Edit phone contact
		click_commonMethod(application, "Selected phone contact", "selectedphone", xml);
		click_commonMethod(application, "Phonecontact remove arrow", "phoneremovearrow", xml);
		cleartext(application, "Phone Contact", "phonecontacttextfieldvalue");
		addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", editphonecontact, xml);
		click_commonMethod(application, "phonecontact adding Arrow", "phoneaddarrow", xml);

		// management options panel
		editcheckbox_commonMethod(application, edit_performancereporting_checkbox, "performancereporting_checkbox", "Performance Reporting", xml);
		editcheckbox_commonMethod(application, edit_ipguardian_checkbox, "ipguardian_checkbox", "IP Guardian", xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "OK", "editservice_okbutton", xml);
		Thread.sleep(3000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			compareText(application, "Service updated success message", "successmsg", "Service updated successfully", xml);	
		}
		else
		{
			Log.info("Service not updated");
			System.out.println("Service not updated");
		}

		//Manage subnets
		ScrolltoElement(application, "orderpanelheader", xml);
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

		//manage subnets IPv6
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

		//Show new infovista report
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Show New Infovista Report", "shownewinfovistareport_link", xml);
		Thread.sleep(4000);
		String parentWinHandle = driver.getWindowHandle();
		Set<String> totalopenwindow=driver.getWindowHandles();
		Iterator<String> itr= totalopenwindow.iterator();
		while(itr.hasNext()){
		String childWindow=itr.next();
		// Compare whether the main windows is not equal to child window. If not equal, we will close.
		if(!parentWinHandle.equals(childWindow)){
		driver.switchTo().window(childWindow);
//		String showinfovistapage= driver.switchTo().window(childWindow).getTitle();
//		System.out.println(showinfovistapage);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : 'Show new infovista report' link navigated to: ");
		driver.close();
		}
		}
		// This is to switch to the main window
		driver.switchTo().window(parentWinHandle);
		Thread.sleep(2000);


		//dump
		ScrolltoElement(application, "orderpanelheader", xml);
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

		//synchronize link in view service page
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Synchronize", "synchronizelink_servicepanel", xml);
		ScrolltoElement(application, "customerdetailsheader", xml);
		compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.", xml);

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
				DriverTestcase.logger.log(LogStatus.FAIL, "Status link is not working");
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
	public void verifyManagementOptionspanel(String application, String performancereporting_checkbox, String ipguardian_checkbox) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "servicepanel_header", xml);
		compareText(application, "Management options header", "managementoptions_header", "Management Options", xml);
		compareText(application, "Performance Reporting", "managementoptions_performancereporting", performancereporting_checkbox, xml);
		compareText(application, "IP Guardian", "managementoptions_ipguardian", ipguardian_checkbox, xml);
	}

	public void navigateToAddNewDevicepage(String application) throws InterruptedException, DocumentException {
		ScrolltoElement(application, "portalaccess_header", xml);
		Thread.sleep(1000);
		compareText(application, "Provider Equipment (PE)", "providerequipment_header", "Provider Equipment (PE)", xml);
		click_commonMethod(application, "Add PE Device", "addpedevice_link", xml);
		Thread.sleep(2000);
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
			DriverTestcase.logger.log(LogStatus.FAIL, "Step: expected field is not displayed in page ");
			e.printStackTrace();
		}catch (TimeoutException e) {
			System.out.println("webElement is not  present" );
			DriverTestcase.logger.log(LogStatus.FAIL, "Step: expected field is not displayed in page ");
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
			addDropdownValues_commonMethod(application, "Vendor/Model", "vendormodelinput", vendormodel, xml);

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
			addDropdownValues_commonMethod(application, "Country", "countryinput", Country, xml);

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

				addDropdownValues_commonMethod(application, "City", "citydropdowninput", existingcityvalue, xml);


				//Existing Site
				if(existingsite.equalsIgnoreCase("yes") & newsite.equalsIgnoreCase("no")) {
					addDropdownValues_commonMethod(application, "Site", "sitedropdowninput", existingsitevalue, xml);

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
		Thread.sleep(2000);
		click_commonMethod(application, "Next", "Next_Button", xml);
		Thread.sleep(3000);
		compareText(application, "Add Device success msg", "successmsg", "Device successfully created", xml);
		Thread.sleep(2000);
	}

	public void verifyViewpage_Devicedetails(String application, String name, String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
			String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
			Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
			String Snmpv3Privpasswordvalue, String Country, String managementaddress, String existingcity, 
			String existingcityvalue, String existingsite, 
			String existingsitevalue, String existingpremise, 
			String existingpremisevalue, String newcity,String cityname,String Citycode,
			String sitename, String sitecode,  String premisename,  String premisecode, 
			String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {


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
			compareText(application, "Snmpro", "viewpage_snmpversion", "2c", xml);

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
		click_commonMethod(application, "Action", "viewdevice_Actiondropdown", xml);
		compareText(application, "Edit", "viewdevice_Edit", "Edit", xml);
		compareText(application, "Delete", "viewdevice_delete", "Delete", xml);
		compareText(application, "Fetch Interface", "viewdevice_fetchinterfacelink", "Fetch Interface", xml);

		//Edit in view device page
		click_commonMethod(application, "Edit", "viewdevice_Edit", xml);
		Thread.sleep(2000);
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/editdeviceheader")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Navigated to 'Edit PE Device' page successfully");
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			Thread.sleep(2000);
			ScrolltoElement(application, "portalaccess_header", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
			{
				click_commonMethod(application, "View", "viewservicepage_viewdevicelink", xml);
				Thread.sleep(5000);
				compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Not navigated to 'Edit PE Device' page");
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
		scrolltoend();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);
	}

	public static String InterfaceAddress;
	public void verifyFetchInterface(String application, String devicename, String Inservice_status, String Inmaintenance_status, String vendormodel, String managementaddress, String snmpro, String country, String interfacename) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "portalaccess_header", xml);
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
					//driver.findElement(By.xpath("//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='View']"));
					Clickon(AddedDevice_ViewLink);
					Thread.sleep(5000);
					compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);
				}
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}

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
		DriverTestcase.logger.log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue);
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

							DriverTestcase.logger.log(LogStatus.PASS, "Device Status History is empty");
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
							for (int i = 0; i < numofrows; i++) {
								try {

									String AddedInterfacedata = results.get(i).getText();
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
			DriverTestcase.logger.log(LogStatus.PASS, "New Status Value is: "+NewStatusvalue1);
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

							DriverTestcase.logger.log(LogStatus.PASS, "Interface Status History is empty");
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

			DriverTestcase.logger.log(LogStatus.PASS, "Vistamart Device date value is displayed as: "+VistaMartDevice_DateTimevalue);
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
		Thread.sleep(3000);
		scrollToTop();
		//driver.navigate().back();
		clickOnBreadCrumb(application, "IPTransitDevice_08", devicename);
		Thread.sleep(5000);
		scrolltoend();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);
		
		//			//verify device name link in status panel
		//			click(application, "Device", "status_devicevalue", xml);
		//			Thread.sleep(2000);
		//			compareText(application, "Search for Device header", "searchdevice_header", "Search For Device", xml);
		//			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to 'Search for device' page");
		//			driver.navigate().back();
		//			Thread.sleep(1000);
		//			
		//			//verify device name link in synchronization panel
		//			click(application, "Device", "synchronization_devicevalue", xml);
		//			Thread.sleep(2000);
		//			compareText(application, "Search for Device header", "searchdevice_header", "Search For Device", xml);
		//			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to 'Search for device' page");
		//			driver.navigate().back();
		//			Thread.sleep(2000);
		//			driver.navigate().back();
		//			Thread.sleep(2000);
		//			scrolltoend();
		//			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		//			Thread.sleep(2000);
		//	
	}

	public void verifyEditDevice(String application, String editDevicename, String editVendorModel, String editTelnet, String editSSH, String editSnmp2C, String editSnmp3,
			String editSnmProNewValue, String editSnmprwNewValue, String editSnmpv3UsernameNewValue, String editSnmpv3AuthpasswordNewValue, String editSnmpv3PrivpasswordNewValue,
			String editManagementAddress, String editCountry, String editExistingCity, String editExistingCityValue, String editExistingSite, String editExistingSiteValue,
			String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, String editNewPremise, String editNewCityName,
			String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, DocumentException, IOException {

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

				//SNMP version		
				if((editSnmp2C.equalsIgnoreCase("Yes"))  && (editSnmp3.equalsIgnoreCase("NO"))) {

					editcheckbox_commonMethod(application, editSnmp2C, "c2cradiobutton", "SNMP Version-2c", xml);

					//snmpro	
					edittextFields_SNMPversion(application, "Snmpro", "snmprotextfield", editSnmProNewValue);

					//snmprw 
					edittextFields_SNMPversion(application, "Snmprw", "snmprwtextfield", editSnmprwNewValue);

				}
				else if((editSnmp2C.equalsIgnoreCase("no"))  && (editSnmp3.equalsIgnoreCase("yes"))) {

					addCheckbox_commonMethod(application, "c3radiobutton", "SNMP Version-3", editSnmp3, "Yes", xml);

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

			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
			}
		} catch (StaleElementReferenceException e) {

			e.printStackTrace();
		}

		scrolltoend();
		Thread.sleep(2000);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);
		compareText(application, "Edit Device success msg", "successmsg", "Device successfully updated", xml);
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


	public void verifyViewpage_UpdatedDevicedetails(String application, String editDevicename, String editVendorModel, String editTelnet, String editSSH, String editSnmp2C, String editSnmp3,
			String editSnmProNewValue, String editSnmprwNewValue, String editSnmpv3UsernameNewValue, String editSnmpv3AuthpasswordNewValue, String editSnmpv3PrivpasswordNewValue,
			String editManagementAddress, String name, String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
			Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue, String Snmpv3Privpasswordvalue, String managementaddress) throws InterruptedException, IOException, DocumentException {

		ScrolltoElement(application, "portalaccess_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
			click_commonMethod(application, "View", "viewservicepage_viewdevicelink", xml);
			Thread.sleep(5000);
			compareText(application, "View device header", "viewdevicepage_header", "Device Details", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
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
		
		//		scrolltoend();
		//		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);
	}


	public void addExistingPEDevice(String application, String existingdevicename) throws InterruptedException, DocumentException {
		ScrolltoElement(application, "portalaccess_header", xml);
		Thread.sleep(1000);
		compareText(application, "Provider Equipment (PE)", "providerequipment_header", "Provider Equipment (PE)", xml);
		click_commonMethod(application, "Add PE Device", "addpedevice_link", xml);
		Thread.sleep(2000);
		compareText(application, "Add PE Device Header", "addpedevice_header", "Add PE Device", xml);
		addDropdownValues_commonMethod(application, "Type PE name to filter", "typepename_dropdown", existingdevicename, xml);
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
		compareText(application, "Add Device success msg", "successmsg", "Device successfully created", xml);
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

		ScrolltoElement(application, "portalaccess_header", xml);
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
					//driver.findElement(By.xpath("//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='Delete from Service']"));
					Clickon(AddedDevice_DeletefromserviceLink);
					Thread.sleep(2000);
					WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
					if(DeleteAlertPopup.isDisplayed())
					{
						click_commonMethod(application, "Delete", "deletebutton", xml);
						Thread.sleep(2000);
						compareText(application, "Device delete success message", "successmsg", "Device successfully removed from service.", xml);
					}
					else
					{
						Log.info("Delete alert popup is not displayed");
						DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
					}
					
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Invalid device name");
				}
				break;
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}
	}

	public void deleteDevice(String application, String name) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "portalaccess_header", xml);
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
					WebElement AddedDevice_DeletefromserviceLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_deletefromservicelink").replace("value", AddedDevice_SNo));
					//driver.findElement(By.xpath("//div[text()='Provider Equipment (PE)']/parent::div/following-sibling::div[@class='div-margin row']//b[contains(text(),'"+AddedDevice_SNo+"')]/parent::div/following-sibling::div//span[text()='Delete from Service']"));
					Clickon(AddedDevice_DeletefromserviceLink);
					Thread.sleep(2000);
					WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
					if(DeleteAlertPopup.isDisplayed())
					{
						click_commonMethod(application, "Delete", "deletebutton", xml);
						Thread.sleep(2000);
						compareText(application, "Device delete success message", "successmsg", "Device successfully removed from service.", xml);
					}
					else
					{
						Log.info("Delete alert popup is not displayed");
						DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
					}
					
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Invalid device name");
				}
				break;
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
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

	public void verify_CiscoVendor_InterfacePanel(String application, String configureinterface_checkbox, String interfacename, String getaddress, String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String bearertype_value, String bandwidth_value, String encapsulation_value, String bgp_checkbox, String framingtype_value, String vlanID_value, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value, String name, String editDevicename, String edit_configureinterface_checkbox, String edit_interfacename, String edit_network, String edit_eipallocation_city, String edit_interfaceaddressrange_value, String edit_interfaceaddressrangeIPv6_value, String edit_ipallocation_availableblocksvalue, String edit_linkvalue, String edit_bearertype_value, String edit_bandwidth_value, String edit_framingtype_value, String edit_encapsulation_value, String edit_vlanID_value, String edit_bgp_checkbox, String edit_bgptemplate_dropdownvalue, String edit_cpewan_value, String edit_cpewanipv6_value, String edit_descriptionfield_value, String edit_ascustomerfield_value, String edit_bgppassword_value, String ipsubnetipv6_value, String ipsubnetipv4_value) throws InterruptedException, DocumentException, IOException {

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
			addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", eipallocation_subnetsize, xml);
			GetText(application, "Available Pools", "eipallocation_availablepools_value");
			//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Close", "closesymbol", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
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
			addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_ipv6_subnetsize", eipallocation_ipv6_subnetsize, xml);
			addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", eipallocation_availableblocksvalue, xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
			Thread.sleep(2000);
			scrollToTop();
			compareText(application, "Subnet aalocation success message", "successmsg", "Subnet Allocation IPTransitService_05-IPTransitService_05-XFER-0017527 (null)Successfully", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
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
		addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", bgp_checkbox, "no", xml);
		if(bgp_checkbox.equalsIgnoreCase("yes"))
		{
			addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", bgptemplate_dropdownvalue, xml);
			addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
			addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
			addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
			addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
			addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
		}

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
		WebElement AddedInterfaces= getwebelement(xml.getlocator("//locators/" + application + "/addedinterfaces"));
		String addedinterfacecheck= AddedInterfaces.getAttribute("style");
		if(!addedinterfacecheck.contains("height: 1px"))
		{
			compareText(application, "Interfaces", "interfaces_header", "Interfaces", xml);
			String Interface_RowID= getwebelement(xml.getlocator("//locators/" + application + "/interface_rowid").replace("value", interfacename)).getAttribute("row-id");
			//driver.findElement(By.xpath("//div[@col-id='interfaceName'][text()='"+interfacename+"']/parent::div")).getAttribute("row-id");

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
			Clickon(Bandwidth_value);
			Bandwidth_value.sendKeys(Keys.TAB);
			WebElement vlanIDValue= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanIDValue);
			Clickon(vlanIDValue);
			vlanIDValue.sendKeys(Keys.TAB);
			WebElement IfInOctets_Value= getwebelement(xml.getlocator("//locators/" + application + "/ifinoctets_tablevalue").replace("value", Interface_RowID));
			DriverTestcase.logger.log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
			Thread.sleep(2000);

		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Interfaces added under Interfaces panel");
		}
		scrolltoend();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);

		//edit Interface
		ScrolltoElement(application, "portalaccess_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
		{
		click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		}
		Thread.sleep(1000);
		WebElement SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", interfacename));
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on existing Interface radio button");
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
				DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
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
				DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
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

			if(getwebelement(xml.getlocator("//locators/" + application + "/bgp_checkbox")).isSelected())
			{
				editcheckbox_commonMethod(application, edit_bgp_checkbox, "bgp_checkbox", "BGP", xml);
				if(edit_bgp_checkbox.equalsIgnoreCase("yes"))
				{
					addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", edit_bgptemplate_dropdownvalue, xml);
				}
				if(!edit_cpewan_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "CPE WAN", "cpewan_textfield");
					addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", edit_cpewan_value, xml);
				}
				else
				{
					GetText(application, "CPE WAN", "cpewan_textfield");
				}
				if(!edit_cpewanipv6_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield");
					addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", edit_cpewanipv6_value, xml);
				}
				else
				{
					GetText(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield");
				}
				if(!edit_descriptionfield_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "Description", "bgp-descriptionfield");
					addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", edit_descriptionfield_value, xml);
				}
				else
				{
					GetText(application, "Description", "bgp-descriptionfield");
				}
				if(!edit_ascustomerfield_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "AS CUSTOMER", "bgp_ascustomerfield");
					addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", edit_ascustomerfield_value, xml);
				}
				else
				{
					GetText(application, "AS CUSTOMER", "bgp_ascustomerfield");
				}
				if(!edit_bgppassword_value.equalsIgnoreCase("null"))
				{
					cleartext(application, "BGP PASSWORD", "bgppassword_field");
					addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", edit_bgppassword_value, xml);
				}
				else
				{
					GetText(application, "BGP PASSWORD", "bgppassword_field");
				}
				//Add IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
				addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", ipsubnetipv6_value, xml);
				//Remove IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Remove", "ipsubnetipv6_removebutton", xml);
				//Add IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
				addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", ipsubnetipv6_value, xml);
				//Add IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
				addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", ipsubnetipv4_value, xml);
				//Remove IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Remove", "ipsubnetipv4_removebutton", xml);
				//Add IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
				addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", ipsubnetipv4_value, xml);
			}
			Thread.sleep(1000);
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

	public static String JuniperInterfaceNameValue, Edit_JuniperInterfaceNameValue;
	public void verify_JuniperVendor_InterfacePanel(String application, String interfacename, String getaddress, String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String bearertype_value, String bandwidth_value, String encapsulation_value, String bgp_checkbox, String framingtype_value, String vlanID_value, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value, String name, String editDevicename, String edit_configureinterface_checkbox, String edit_interfacename, String edit_network, String edit_eipallocation_city, String edit_interfaceaddressrange_value, String edit_interfaceaddressrangeIPv6_value, String edit_availableblocksvalue, String edit_linkvalue, String edit_bearertype_value, String edit_bandwidth_value, String edit_framingtype_value, String edit_encapsulation_value, String edit_vlanID_value, String edit_bgp_checkbox, String edit_bgptemplate_dropdownvalue, String edit_cpewan_value, String edit_cpewanipv6_value, String edit_descriptionfield_value, String edit_ascustomerfield_value, String edit_bgppassword_value, String ipsubnetipv6_value, String ipsubnetipv4_value, String juniper_configureinterface_checkbox, String cardtype_dropdownvalue, String clocksource_value, String STM1Number_value, String bearerNo_value, String unitid_value, String slot_value, String pic_value, String port_value, String ivmanagement_checkbox, String atricaconnected_checkbox, String edit_juniper_configureinterface_checkbox, String edit_juniper_interfacename, String edit_cardtype_dropdownvalue, String edit_clocksource_value, String edit_STM1Number_value, String edit_bearerNo_value, String edit_unitid_value, String edit_slot_value, String edit_pic_value, String edit_port_value, String edit_ivmanagement_checkbox, String edit_atricaconnected_checkbox, String cardtype_dropdownvalue_gigabit, String edit_eipallocation_subnetsize, String edit_eipallocation_ipv6_subnetsize, String edit_eipallocation1, String edit_getaddress, String edit_eipallocation2, String edit_ipv6_getaddress, String edit_ipsubnetipv6_value, String edit_ipsubnetipv4_value) throws InterruptedException, DocumentException, IOException {

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
			addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", eipallocation_subnetsize, xml);
			Thread.sleep(2000);
			GetText(application, "Available Pools", "eipallocation_availablepools_value");
			//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Close", "closesymbol", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
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
			//compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
			GetText(application, "EIP Address Allocation header", "eipallocationIPv6_header");
			GetText(application, "Subnet Type", "subnettype_value");
			GetText(application, "Space Name", "eipallocation_spacename");
			addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", eipallocation_ipv6_subnetsize, xml);
			addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", eipallocation_availableblocksvalue, xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
			Thread.sleep(2000);
			scrollToTop();
			compareText(application, "Subnet aalocation success message", "successmsg", "Subnet Allocation IPTransitService_05-IPTransitService_05-XFER-0017527 (null)Successfully", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
		}
		Thread.sleep(2000);

		//verify getaddress ipv6
		if(ipv6_getaddress_button.equalsIgnoreCase("yes"))
		{
			ScrolltoElement(application, "getaddress1_button", xml);
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

		ScrolltoElement(application, "getaddress2_button", xml);
		addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
		addDropdownValues_commonMethod(application, "Bearer Type", "bearertype_dropdown", bearertype_value, xml);
		if(bearertype_value.equalsIgnoreCase("10Gigabit Ethernet") || bearertype_value.equalsIgnoreCase("Gigabit Ethernet")) {
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
			addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", cardtype_dropdownvalue_gigabit, xml);
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("E1"))
		{
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
			addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", cardtype_dropdownvalue, xml);
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
			addDropdownValues_commonMethod(application, "Framing Type", "framingtype_dropdown", framingtype_value, xml);
			addDropdownValues_commonMethod(application, "Clock Source", "clocksource_dropdown", clocksource_value, xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
			addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", bearerNo_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("E3") || bearertype_value.equalsIgnoreCase("T3"))
		{
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
			addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", cardtype_dropdownvalue, xml);
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
			addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", bearerNo_value, xml);
		}
		else if(bearertype_value.equalsIgnoreCase("STM-1"))
		{
			addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
			addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", cardtype_dropdownvalue, xml);
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
		}
		else
		{
			addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
			addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", STM1Number_value, xml);
		}
		Thread.sleep(1000);
		addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", unitid_value, xml);
		addtextFields_commonMethod(application, "Slot", "slot_textfield", slot_value, xml);
		addtextFields_commonMethod(application, "Pic", "pic_textfield", pic_value, xml);
		addtextFields_commonMethod(application, "Port", "port_textfield", port_value, xml);
		//addtextFields_commonMethod(application, "Interface", "interfacename_textfield", interfacename, xml);
		Thread.sleep(1000);
		String Juniper_InterfaceName= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_textfield")).getAttribute("value");
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
			addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", bgptemplate_dropdownvalue, xml);
			addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", cpewan_value, xml);
			addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", cpewanipv6_value, xml);
			addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", descriptionfield_value, xml);
			addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", ascustomerfield_value, xml);
			addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", bgppassword_value, xml);
		}
		scrolltoend();
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
		WebElement AddedInterfaces= getwebelement(xml.getlocator("//locators/" + application + "/addedinterfaces"));
		String addedinterfacecheck= AddedInterfaces.getAttribute("style");
		if(!addedinterfacecheck.contains("height: 1px"))
		{
			compareText(application, "Interfaces", "interfaces_header", "Interfaces", xml);
			String Interface_RowID= getwebelement(xml.getlocator("//locators/" + application + "/interface_rowid").replace("value", Juniper_InterfaceName)).getAttribute("row-id");

			String InterfaceName_value= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_tablevalue").replace("value", Interface_RowID)).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
			String Link_value= getwebelement(xml.getlocator("//locators/" + application + "/link_tablevalue").replace("value", Interface_RowID)).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
			String InterfaceAddressRange_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddressrange_tablevalue").replace("value", Interface_RowID)).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
			String InterfaceAddress_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_tablevalue").replace("value", Interface_RowID)).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
			String BearerType_value= getwebelement(xml.getlocator("//locators/" + application + "/bearertype_tablevalue").replace("value", Interface_RowID)).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
			WebElement Bandwidth= getwebelement(xml.getlocator("//locators/" + application + "/bandwidth_tablevalue").replace("value", Interface_RowID));
			String Bandwidth_value= Bandwidth.getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
			Clickon(Bandwidth);
			Bandwidth.sendKeys(Keys.TAB);
			WebElement vlanID= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_tablevalue").replace("value", Interface_RowID));
			String vlanID_Value= vlanID.getText();
			DriverTestcase.logger.log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanID_Value);
			Clickon(vlanID);
			vlanID.sendKeys(Keys.TAB);
			String IfInOctets_Value= getwebelement(xml.getlocator("//locators/" + application + "/ifinoctets_tablevalue").replace("value", Interface_RowID)).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
			Thread.sleep(2000);

		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Interfaces added under Interfaces panel");
		}
		scrolltoend();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);

		//edit Interface
		ScrolltoElement(application, "portalaccess_header", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/showinterfaces_link")).isDisplayed())
		{
		click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		}
		Thread.sleep(1000);
		scrolltoend();
		WebElement SelectInterface= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", Juniper_InterfaceName));
		if(SelectInterface.isDisplayed())
		{
			Clickon(SelectInterface);
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on existing Interface radio button");
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

			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			compareText(application, "Edit Interface/Link", "editinterface_header", "Edit", xml);

			editcheckbox_commonMethod(application, edit_juniper_configureinterface_checkbox, "configureinterface_checkbox", "Configure Interface on Device", xml);
			addDropdownValues_commonMethod(application, "Network", "network_fieldvalue", edit_network, xml);

			//verify EIP Allocation
			Thread.sleep(1000);
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
				//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Close", "closesymbol", xml);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
			}
			}
			Thread.sleep(2000);

			//verify getaddress
			if(edit_getaddress.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress1_button", xml);
				addDropdownValues_commonMethod(application, "Interface Address Range", "interfacerange_Address_dropdown", edit_interfaceaddressrange_value, xml);
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);
			}
			else
			{
				if(!edit_interfaceaddressrange_value.equalsIgnoreCase("null") || !edit_interfaceaddressrange_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "Interface Address Range", "interfaceaddressrange_textfield");
					addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", edit_interfaceaddressrange_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "Interface Address Range", "interfaceaddressrange_textfield", edit_interfaceaddressrange_value, xml);
				}
				Thread.sleep(1000);
				click_commonMethod(application, "Interface Address Range Arrow", "interfaceaddress_Addarrow", xml);

			}
			compareText_fromtextFields(application, "Network", "networkipv6_fieldvalue", "WAN(XFER)", xml);

			//verify EIP Allocation
			if(edit_eipallocation2.equalsIgnoreCase("yes"))
			{
			Thread.sleep(1000);
			click_commonMethod(application, "EIP Allocation button", "eipallocation2_button", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/eipallocationIPv6_header")).isDisplayed())
			{
				compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
				GetText(application, "Subnet Type", "subnettype_value");
				GetText(application, "Space Name", "eipallocation_spacename");
				addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_ipv6_subnetsize", edit_eipallocation_ipv6_subnetsize, xml);
				addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", edit_availableblocksvalue, xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
				Thread.sleep(2000);
				scrollToTop();
				GetText(application, "Subnet aalocation success message", "successmsg");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
			}
			}
			Thread.sleep(2000);

			//verify getaddress ipv6
			if(edit_ipv6_getaddress.equalsIgnoreCase("yes"))
			{
				click_commonMethod(application, "Get Address", "getaddress2_button", xml);
				addDropdownValues_commonMethod(application, "Interface Address Range IPv6", "interfacerange_AddressIpv6_dropdown", edit_interfaceaddressrangeIPv6_value, xml);
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);
			}
			else
			{
				if(!edit_interfaceaddressrangeIPv6_value.equalsIgnoreCase("null") || !edit_interfaceaddressrangeIPv6_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield");
					addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", edit_interfaceaddressrangeIPv6_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "Interface Address Range IPv6", "interfaceaddressrangeIPv6_textfield", edit_interfaceaddressrangeIPv6_value, xml);
				}
				Thread.sleep(1000);
				click_commonMethod(application, "Interface Address Range IPv6 Arrow", "interfaceaddressIPv6_Addarrow", xml);

			}

			ScrolltoElement(application, "link_textfield", xml);
			if(!edit_linkvalue.equalsIgnoreCase("null") || !edit_linkvalue.equalsIgnoreCase("no"))
			{
				cleartext(application, "Link", "link_textfield");
				addtextFields_commonMethod(application, "Link", "link_textfield", edit_linkvalue, xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Link", "link_textfield", edit_linkvalue, xml);
			}
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
				if(!edit_STM1Number_value.equalsIgnoreCase("null") || !edit_STM1Number_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "STM1 Number", "STM1Number_textfield");
					addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				}
				if(!edit_bearerNo_value.equalsIgnoreCase("null") || !edit_bearerNo_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "Bearer No", "bearerno_textfield");
					addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
				}
			}
			else if(edit_bearertype_value.equalsIgnoreCase("E3") || edit_bearertype_value.equalsIgnoreCase("T3"))
			{
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", edit_bandwidth_value, xml);
				addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", edit_cardtype_dropdownvalue, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
				if(!edit_STM1Number_value.equalsIgnoreCase("null") || !edit_STM1Number_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "STM1 Number", "STM1Number_textfield");
					addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				}
				if(!edit_bearerNo_value.equalsIgnoreCase("null") || !edit_bearerNo_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "Bearer No", "bearerno_textfield");
					addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "Bearer No", "bearerno_textfield", edit_bearerNo_value, xml);
				}
			}
			else if(edit_bearertype_value.equalsIgnoreCase("STM-1"))
			{
				addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", edit_bandwidth_value, xml);
				addDropdownValues_commonMethod(application, "Card Type", "cardtype_dropdown", edit_cardtype_dropdownvalue, xml);
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
				if(!edit_STM1Number_value.equalsIgnoreCase("null") || !edit_STM1Number_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "STM1 Number", "STM1Number_textfield");
					addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				}
			}
			else
			{
				addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", edit_encapsulation_value, xml);
				if(!edit_STM1Number_value.equalsIgnoreCase("null") || !edit_STM1Number_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "STM1 Number", "STM1Number_textfield");
					addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "STM1 Number", "STM1Number_textfield", edit_STM1Number_value, xml);
				}
			}
			Thread.sleep(1000);
			if(!edit_unitid_value.equalsIgnoreCase("null") || !edit_unitid_value.equalsIgnoreCase("no"))
			{
				cleartext(application, "Unit ID", "unitid_textfield");
				addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", edit_unitid_value, xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", edit_unitid_value, xml);
			}
			if(!edit_slot_value.equalsIgnoreCase("null") || !edit_slot_value.equalsIgnoreCase("no"))
			{
				cleartext(application, "Slot", "slot_textfield");
				addtextFields_commonMethod(application, "Slot", "slot_textfield", edit_slot_value, xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Slot", "slot_textfield", edit_slot_value, xml);
			}
			if(!edit_pic_value.equalsIgnoreCase("null") || !edit_pic_value.equalsIgnoreCase("no"))
			{
				cleartext(application, "Pic", "pic_textfield");
				addtextFields_commonMethod(application, "Pic", "pic_textfield", edit_pic_value, xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Pic", "pic_textfield", edit_pic_value, xml);
			}
			if(!edit_port_value.equalsIgnoreCase("null") || !edit_port_value.equalsIgnoreCase("no"))
			{
				cleartext(application, "Port", "port_textfield");
				addtextFields_commonMethod(application, "Port", "port_textfield", edit_port_value, xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Port", "port_textfield", edit_port_value, xml);
			}
			
			String edit_Juniper_InterfaceName= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_textfield")).getAttribute("value");
			if(edit_encapsulation_value.equalsIgnoreCase("802.1q"))
			{
				if(!edit_vlanID_value.equalsIgnoreCase("null") || !edit_vlanID_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "VLAN Id", "vlanid_textfield");
					addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", edit_vlanID_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", edit_vlanID_value, xml);
				}
			}
			editcheckbox_commonMethod(application, edit_ivmanagement_checkbox, "ivmanagement_checkbox", "IV Management", xml);
			editcheckbox_commonMethod(application, edit_atricaconnected_checkbox, "atricaconnected_checkbox", "Atrica Connected", xml);
			editcheckbox_commonMethod(application, edit_bgp_checkbox, "bgp_checkbox", "BGP", xml);
			if(edit_bgp_checkbox.equalsIgnoreCase("yes"))
			{
				addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", edit_bgptemplate_dropdownvalue, xml);
				if(!edit_cpewan_value.equalsIgnoreCase("null") || !edit_cpewan_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "CPE WAN", "cpewan_textfield");
					addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", edit_cpewan_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "CPE WAN", "cpewan_textfield", edit_cpewan_value, xml);
				}
				if(!edit_cpewanipv6_value.equalsIgnoreCase("null") || !edit_cpewanipv6_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield");
					addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", edit_cpewanipv6_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "CPE WAN IPv6 Address", "cpewanipv6_textfield", edit_cpewanipv6_value, xml);
				}
				if(!edit_descriptionfield_value.equalsIgnoreCase("null") || !edit_descriptionfield_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "Description", "bgp-descriptionfield");
					addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", edit_descriptionfield_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "Description", "bgp-descriptionfield", edit_descriptionfield_value, xml);
				}
				if(!edit_ascustomerfield_value.equalsIgnoreCase("null") || !edit_ascustomerfield_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "Description", "bgp-descriptionfield");
					addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", edit_ascustomerfield_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "AS CUSTOMER", "bgp_ascustomerfield", edit_ascustomerfield_value, xml);
				}
				if(!edit_bgppassword_value.equalsIgnoreCase("null") || !edit_bgppassword_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "BGP PASSWORD", "bgppassword_field");
					addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", edit_bgppassword_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "BGP PASSWORD", "bgppassword_field", edit_bgppassword_value, xml);
				}

				//Add IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
				if(!edit_ipsubnetipv6_value.equalsIgnoreCase("null") || !edit_ipsubnetipv6_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "IP Subnet IPv6", "ipsubnetipv6_textfield");
					addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", edit_ipsubnetipv6_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", edit_ipsubnetipv6_value, xml);
				}
				//Remove IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Remove", "ipsubnetipv6_removebutton", xml);
				//Add IP Subnet IPv6
				click_commonMethod(application, "IP Subnet IPv6 Add", "ipsubnetipv6_addbutton", xml);
				if(!edit_ipsubnetipv6_value.equalsIgnoreCase("null") || !edit_ipsubnetipv6_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "IP Subnet IPv6", "ipsubnetipv6_textfield");
					addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", edit_ipsubnetipv6_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "IP Subnet IPv6", "ipsubnetipv6_textfield", edit_ipsubnetipv6_value, xml);
				}
				//Add IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
				if(!edit_ipsubnetipv4_value.equalsIgnoreCase("null") || !edit_ipsubnetipv4_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "IP Subnet IPv4", "ipsubnetipv4_textfield");
					addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", edit_ipsubnetipv4_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", edit_ipsubnetipv4_value, xml);
				}
				//Remove IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Remove", "ipsubnetipv4_removebutton", xml);
				//Add IP Subnet IPv4
				click_commonMethod(application, "IP Subnet IPv4 Add", "ipsubnetipv4_addbutton", xml);
				if(!edit_ipsubnetipv4_value.equalsIgnoreCase("null") || !edit_ipsubnetipv4_value.equalsIgnoreCase("no"))
				{
					cleartext(application, "IP Subnet IPv4", "ipsubnetipv4_textfield");
					addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", edit_ipsubnetipv4_value, xml);
				}
				else
				{
					addtextFields_commonMethod(application, "IP Subnet IPv4", "ipsubnetipv4_textfield", edit_ipsubnetipv4_value, xml);
				}

			}

			//configuration panel in edit interface page
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
				
				Thread.sleep(1000);
				compareText(application, "Interface Configuration History header", "interfaceconfighistory_header", "Interface Configuration History", xml);
				compareText(application, "Date column", "date_column", "Date", xml);
				compareText(application, "File Name column", "filename_column", "File Name", xml);
				Thread.sleep(5000);
				compareText(application, "Interface update success message", "successmsg", "Interface successfully updated", xml);
			}
			else
			{
				click_commonMethod(application, "OK", "okbutton", xml);
			}
			
			Edit_JuniperInterfaceNameValue= edit_Juniper_InterfaceName;
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Interface is not added");
		}
		JuniperInterfaceNameValue= Juniper_InterfaceName;
		
	}

	public void deleteInterface(String application, String interfacename, String name, String editDevicename) throws InterruptedException, DocumentException {
		//Delete Interface
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);
		ScrolltoElement(application, "portalaccess_header", xml);
		click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
		Thread.sleep(1000);
		WebElement SelectInterface1= getwebelement(xml.getlocator("//locators/" + application + "/selectinterface").replace("value", interfacename));
		if(SelectInterface1.isDisplayed())
		{
			Clickon(SelectInterface1);
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on existing Interface radio button");
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
			compareText(application, "Interface delete success message", "successmsg", "Interface successfully deleted.", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Interface is not added");
		}
	}
	
	public void verifyAddMultilink(String application, String name, String multilink_interfacename, String getaddress, String ipv6_getaddress_button, String interfaceaddressrange_value, String eipallocation_city, String eipallocation_subnetsize, String eipallocation_ipv6_subnetsize, String eipallocation_availableblocksvalue, String link_value, String bandwidth_value, String encapsulation_value, String multilink_bgpcheckbox, String bgptemplate_dropdownvalue, String cpewan_value, String cpewanipv6_value, String descriptionfield_value, String ascustomerfield_value, String bgppassword_value, String multilink_configureinterface_checkbox, String interfacename, String checktoaddinterface_checkbox, String unitid_value, String slot_value, String pic_value, String port_value) throws InterruptedException, DocumentException, IOException {
		
		ScrolltoElement(application, "portalaccess_header", xml);
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
					
					//verify EIP Allocation
					Thread.sleep(1000);
					click_commonMethod(application, "EIP Allocation button", "eipallocation1_button", xml);
					if(getwebelement(xml.getlocator("//locators/" + application + "/eipsubnetallocation_header")).isDisplayed())
					{
						compareText(application, "EIP Subnet Allocation", "eipsubnetallocation_header", "EIP Subnet Allocation", xml);
						GetText(application, "Subnet Type", "subnettype_value");
						addDropdownValues_commonMethod(application, "City", "eipallocation_citydropdown", eipallocation_city, xml);
						addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", eipallocation_subnetsize, xml);
						Thread.sleep(2000);
						GetText(application, "Available Pools", "eipallocation_availablepools_value");
						//click_commonMethod(application, "Allocate subnet button", "allocatesubnet_button", xml);
						Thread.sleep(1000);
						click_commonMethod(application, "Close", "closesymbol", xml);
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
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
						//compareText(application, "EIP Address Allocation header", "eipallocationIPv6_header", "EIP Address Allocation For Interface  ", xml);
						GetText(application, "EIP Address Allocation header", "eipallocationIPv6_header");
						GetText(application, "Subnet Type", "subnettype_value");
						GetText(application, "Space Name", "eipallocation_spacename");
						addDropdownValues_commonMethod(application, "Sub Net Size", "eipallocation_subnetsize", eipallocation_ipv6_subnetsize, xml);
						addDropdownValues_commonMethod(application, "Available Blocks", "availableblocks_dropdown", eipallocation_availableblocksvalue, xml);
						Thread.sleep(1000);
						click_commonMethod(application, "Allocate subnet", "allocatesubnet_button", xml);
						Thread.sleep(2000);
						scrollToTop();
						GetText(application, "Subnet aalocation success message", "successmsg");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "EIP Allocation button is not working");
					}
					Thread.sleep(2000);

					//verify getaddress ipv6
					ScrolltoElement(application, "getaddress1_button", xml);
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
					
					addtextFields_commonMethod(application, "Link", "link_textfield", link_value, xml);
					Thread.sleep(1000);
					addDropdownValues_commonMethod(application, "Bandwidth", "bandwidth_dropdown", bandwidth_value, xml);
					addDropdownValues_commonMethod(application, "Encapsulation", "encapsulation_dropdown", encapsulation_value, xml);
					ScrolltoElement(application, "link_textfield", xml);
					addtextFields_commonMethod(application, "Unit ID", "unitid_textfield", unitid_value, xml);
					addtextFields_commonMethod(application, "Slot", "slot_textfield", slot_value, xml);
					addtextFields_commonMethod(application, "Pic", "pic_textfield", pic_value, xml);
					addtextFields_commonMethod(application, "Port", "port_textfield", port_value, xml);
					Thread.sleep(1000);
					addCheckbox_commonMethod(application, "bgp_checkbox", "BGP", multilink_bgpcheckbox, "no", xml);
					if(multilink_bgpcheckbox.equalsIgnoreCase("yes"))
					{
						addDropdownValues_commonMethod(application, "BGP Templates Generate For", "bgptemplate_dropdown", bgptemplate_dropdownvalue, xml);
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
						DriverTestcase.logger.log(LogStatus.PASS, "Existing interface details are displaying in multilink bearer table");
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
						DriverTestcase.logger.log(LogStatus.PASS, "No existing interfaces to display");
					}
					scrolltoend();
					//configuration panel in add interface page
					if(multilink_configureinterface_checkbox.equalsIgnoreCase("yes") || multilink_configureinterface_checkbox.equalsIgnoreCase("null"))
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
					if(name.equalsIgnoreCase("Cisco"))
					{
					String Cisco_Multilink_Name= "Multilink"+multilink_interfacename;
					String Cisco_Multilink_RowID= getwebelement(xml.getlocator("//locators/" + application + "/multilink_rowid").replace("value", Cisco_Multilink_Name)).getAttribute("row-id");

					String InterfaceName_value= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					DriverTestcase.logger.log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
					String Link_value= getwebelement(xml.getlocator("//locators/" + application + "/link_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					DriverTestcase.logger.log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
					String InterfaceAddressRange_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddressrange_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					DriverTestcase.logger.log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
					String InterfaceAddress_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					DriverTestcase.logger.log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
					String BearerType_value= getwebelement(xml.getlocator("//locators/" + application + "/bearertype_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					DriverTestcase.logger.log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
					WebElement Bandwidth= getwebelement(xml.getlocator("//locators/" + application + "/bandwidth_tablevalue").replace("value", Cisco_Multilink_RowID));
					String Bandwidth_value= Bandwidth.getText();
					DriverTestcase.logger.log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
					Clickon(Bandwidth);
					Bandwidth.sendKeys(Keys.TAB);
					WebElement vlanID= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_tablevalue").replace("value", Cisco_Multilink_RowID));
					String vlanID_value= vlanID.getText();
					DriverTestcase.logger.log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanID_value);
					Clickon(vlanID);
					vlanID.sendKeys(Keys.TAB);
					String IfInOctets_Value= getwebelement(xml.getlocator("//locators/" + application + "/ifinoctets_tablevalue").replace("value", Cisco_Multilink_RowID)).getText();
					DriverTestcase.logger.log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
					Thread.sleep(2000);
					}
					else
					{
						String Multilink_RowID= getwebelement(xml.getlocator("//locators/" + application + "/multilink_rowid").replace("value", multilink_interfacename)).getAttribute("row-id");

						String InterfaceName_value= getwebelement(xml.getlocator("//locators/" + application + "/interfacename_tablevalue").replace("value", Multilink_RowID)).getText();
						DriverTestcase.logger.log(LogStatus.PASS, "Interface value is displayed as:"+InterfaceName_value);
						String Link_value= getwebelement(xml.getlocator("//locators/" + application + "/link_tablevalue").replace("value", Multilink_RowID)).getText();
						DriverTestcase.logger.log(LogStatus.PASS, "Link/Circuit Id value is displayed as:"+Link_value);
						String InterfaceAddressRange_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddressrange_tablevalue").replace("value", Multilink_RowID)).getText();
						DriverTestcase.logger.log(LogStatus.PASS, "Interface Address Range value is displayed as:"+InterfaceAddressRange_value);
						String InterfaceAddress_value= getwebelement(xml.getlocator("//locators/" + application + "/interfaceaddress_tablevalue").replace("value", Multilink_RowID)).getText();
						DriverTestcase.logger.log(LogStatus.PASS, "Interface Address value is displayed as:"+InterfaceAddress_value);
						String BearerType_value= getwebelement(xml.getlocator("//locators/" + application + "/bearertype_tablevalue").replace("value", Multilink_RowID)).getText();
						DriverTestcase.logger.log(LogStatus.PASS, "Bearer Type value is displayed as:"+BearerType_value);
						WebElement Bandwidth= getwebelement(xml.getlocator("//locators/" + application + "/bandwidth_tablevalue").replace("value", Multilink_RowID));
						String Bandwidth_value= Bandwidth.getText();
						DriverTestcase.logger.log(LogStatus.PASS, "Bandwidth value is displayed as:"+Bandwidth_value);
						Clickon(Bandwidth);
						Bandwidth.sendKeys(Keys.TAB);
						WebElement vlanID= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_tablevalue").replace("value", Multilink_RowID));
						String vlanID_value= vlanID.getText();
						DriverTestcase.logger.log(LogStatus.PASS, "VLAN Id value is displayed as:"+vlanID_value);
						Clickon(vlanID);
						vlanID.sendKeys(Keys.TAB);
						String IfInOctets_Value= getwebelement(xml.getlocator("//locators/" + application + "/ifinoctets_tablevalue").replace("value", Multilink_RowID)).getText();
						DriverTestcase.logger.log(LogStatus.PASS, "IfInOctets value is displayed as:"+IfInOctets_Value);
						Thread.sleep(2000);
					}
					scrolltoend();
					click_commonMethod(application, "Back", "viewpage_backbutton", xml);
					Thread.sleep(2000);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Invalid device name");
				}
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}

	}

	public void verifyInterfaceConfigHistory(String application) throws InterruptedException, DocumentException {
		ScrolltoElement(application, "interfaces_header", xml);
		Thread.sleep(1000);
		compareText(application, "Interface Configuration History", "interfaceconfighistory_header", "Interface Configuration History", xml);
		compareText(application, "Date column", "date_column", "Date", xml);
		compareText(application, "File Name column", "filename_column", "File Name", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);

	}


	public void selectInterfacelinkforDevice(String application, String name) throws InterruptedException, DocumentException {
		
		ScrolltoElement(application, "portalaccess_header", xml);
		Thread.sleep(1000);
		DriverTestcase.logger.log(LogStatus.INFO, "check 'Select Interface' link");
		
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
					DriverTestcase.logger.log(LogStatus.FAIL, "Invalid device name");
				}
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}
					
	}

	public void SelectInterfacetoremovefromservice(String application, String interfacename, String vendormodel)
			throws IOException, InterruptedException, DocumentException {

		ScrolltoElement(application, "viewpage_vendormodel", xml);
		Thread.sleep(2000);

		if(vendormodel.equalsIgnoreCase("Cisco"))
		{
		Cisco_selectRowforInterfaceInService(application, interfacename);
		}
		else
		{
			Juniper_selectRowforInterfaceInService(application);
		}

	}

	public void Juniper_selectRowforInterfaceInService(String application)
			throws IOException, InterruptedException, DocumentException {

		String Edit_JuniperInterfaceNameValue= "e3-4468/2799/3456:4:38962.33256";
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

					List<WebElement> results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", Edit_JuniperInterfaceNameValue));

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
									DriverTestcase.logger.log(LogStatus.PASS, Edit_JuniperInterfaceNameValue + " is selected under 'Interface in Service' table");
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + application + "/InterfaceInselect_Actiondropdown")));

									Thread.sleep(3000);

									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_removebuton")));
									DriverTestcase.logger.log(LogStatus.PASS, Edit_JuniperInterfaceNameValue + " has been selected to get removed from service");

								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", Edit_JuniperInterfaceNameValue));
									//driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"+ interfacenumber+"']]//input"));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								DriverTestcase.logger.log(LogStatus.FAIL, "failure while selecting interface to remove from service");

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
									DriverTestcase.logger.log(LogStatus.PASS, interfacename + " is selected under 'Interface in Service' table");
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + application + "/InterfaceInselect_Actiondropdown")));

									Thread.sleep(3000);

									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceInselect_removebuton")));
									DriverTestcase.logger.log(LogStatus.PASS, interfacename + " has been selected to get removed from service");

								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", interfacename));
									//driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"+ interfacenumber+"']]//input"));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								DriverTestcase.logger.log(LogStatus.FAIL, "failure while selecting interface to remove from service");

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

	public void SelectInterfacetoaddwithservcie(String application, String interfacename, String vendormodel)
			throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(2000);

		if(vendormodel.equalsIgnoreCase("Cisco"))
		{
			Cisco_selectrowforInterfaceToselecttable(application, interfacename);
		}
		else
		{
			Juniper_selectrowforInterfaceToselecttable(application);
		}

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
									DriverTestcase.logger.log(LogStatus.PASS, Edit_JuniperInterfaceNameValue + " is selected under 'Interface to select' table");
									Thread.sleep(8000);
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + application + "/InterfaceToselect_Actiondropdown")));

									Thread.sleep(5000);

									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_addbuton")));
									Thread.sleep(3000);
									DriverTestcase.logger.log(LogStatus.PASS, Edit_JuniperInterfaceNameValue + " is added to service");


								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", Edit_JuniperInterfaceNameValue));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								DriverTestcase.logger.log(LogStatus.FAIL, " Failure on selecting an Interface to ad with service ");


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
									DriverTestcase.logger.log(LogStatus.PASS, interfacename + " is selected under 'Interface to select' table");
									Thread.sleep(8000);
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + application + "/InterfaceToselect_Actiondropdown")));

									Thread.sleep(5000);

									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceToselect_addbuton")));
									Thread.sleep(3000);
									DriverTestcase.logger.log(LogStatus.PASS, interfacename + " is added to service");


								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = getwebelements(xml.getlocator("//locators/" + application + "/interfacesinservice_list").replace("value", interfacename));
								numofrows = results.size();
								// results.get(i).click();
								Log.info("selected row is : " + i);
								DriverTestcase.logger.log(LogStatus.FAIL, " Failure on selecting an Interface to ad with service ");


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

				DriverTestcase.logger.log(LogStatus.PASS, " 'SSH' is displaying under 'Connectivity protocol' as expected");
				System.out.println(" 'SSH' is displaying under 'Connectivity protocol' as expected");

				conectivityProtocolssh_Selection=getwebelement(xml.getlocator("//locators/" + application + "/sshradiobutton")).isSelected();
				if(conectivityProtocolssh_Selection) {
					DriverTestcase.logger.log(LogStatus.PASS, " 'SSH' is selected under 'Connectivity protocol' by default as expected");
					System.out.println(" 'SSH' is selected under 'Connectivity protocol' as expected");

				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'SSH' is not selected by default under 'Connectivity protocol'");
					System.out.println(" 'SSH' is not selected under 'Connectivity protocol'");
				}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'SSH' is not displaying under 'Connectivity protocol'");
				System.out.println(" 'SSH' is not displaying under 'Connectivity protocol'");
			}

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'SSH' is not displaying under 'Connectivity protocol'");
			System.out.println(" 'SSH' is not displaying under 'Connectivity protocol'");

		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'SSH' is not selected under 'Connectivity protocol'");
			System.out.println(" 'SSH' is not selected under 'Connectivity protocol'");

		}
	}


	public void verifyDefaultSelection_connectivityprotocol_telnet(String application) throws InterruptedException, DocumentException {
		boolean conectivityProtocoltelnet_availability=false;
		boolean conectivityProtocoltelnet_Selection=false;
		try {	
			conectivityProtocoltelnet_availability=getwebelement(xml.getlocator("//locators/" + application + "/telnetradiobutton")).isDisplayed();
			if(conectivityProtocoltelnet_availability) {

				DriverTestcase.logger.log(LogStatus.PASS, " 'Telnet' is displaying under 'Connectivity protocol' as expected");
				System.out.println(" 'Telnet' is displaying under 'Connectivity protocol' as expected");

				conectivityProtocoltelnet_Selection=getwebelement(xml.getlocator("//locators/" + application + "/telnetradiobutton")).isSelected();
				if(conectivityProtocoltelnet_Selection) {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Telnet' is selected under 'Connectivity protocol' by default");
					System.out.println(" 'Telnet' is selected under 'Connectivity protocol'");

				}else {
					DriverTestcase.logger.log(LogStatus.PASS, " 'Telnet' is not selected under 'Connectivity protocol' by default as expected");
					System.out.println(" 'Telnet' is not selected under 'Connectivity protocol'");
				}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Telnet' is not displaying under 'Connectivity protocol'");
				System.out.println(" 'Telnet' is not displaying under 'Connectivity protocol'");
			}


		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Telnet' is not displaying under 'Connectivity protocol'");
			System.out.println(" 'Telnet' is not displaying under 'Connectivity protocol'");

		}
	}

	public void edittextFields_SNMPversion(String application, String labelname, String xpathname, String expectedValueToEdit) throws InterruptedException, DocumentException, IOException {
		boolean availability=false;
		try {	
			availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).isDisplayed();
			if(availability) {
				DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is displaying as expected");
				System.out.println(labelname + " text field is displaying as expected");

				String actualvalueInsidetextfield=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");

				if(actualvalueInsidetextfield.isEmpty()) {

					DriverTestcase.logger.log(LogStatus.FAIL, "No values displaying under "+labelname+" text field");
					System.out.println("No values displaying under "+labelname+" text field");

					if(expectedValueToEdit.equalsIgnoreCase("null")) {
						//DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is not edited as expected");
						System.out.println(labelname + " text field is not edited as expected");
					}else {

						getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).clear();
						Thread.sleep(3000);

						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
						Thread.sleep(3000);

						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field value is edited as: "+ actualvalue);
					}

				}else {

					DriverTestcase.logger.log(LogStatus.PASS, "Value displaying under "+labelname + " field is: "+actualvalueInsidetextfield);
					System.out.println("Value displaying under "+labelname + " field is: "+actualvalueInsidetextfield);

					if(expectedValueToEdit.equalsIgnoreCase("null")) {
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is not edited as expected");
						System.out.println(labelname + " text field is not edited as expected");
					}else {

						getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).clear();
						Thread.sleep(3000);

						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
						Thread.sleep(3000);

						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field value is edited as: "+ actualvalue);
					}
				}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " text field is not displaying");
				System.out.println(labelname + " text field is not displaying");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " text field is not displaying");
			System.out.println(labelname + " text field is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under "+ labelname + " text field");
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

	public void hostnametextField_IPV6(String application, String commandIPv6, String ipv6Address) {
		boolean IPV4availability=false;
		try {  
			IPV4availability=getwebelement(xml.getlocator("//locators/" + application + "/commandIPv6_hostnameTextfield")).isDisplayed();

			if(IPV4availability) {

				addtextFields_commonMethod(application, "IP Address or Hostname", "commandIPv6_hostnameTextfield", ipv6Address, xml);

			}else {
				DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
				System.out.println("'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
			}
		}catch(Exception e) {
			e.printStackTrace();

			DriverTestcase.logger.log(LogStatus.INFO, "'Hostname or IpAddress' for 'IPV6' text field is not displaying for "+ commandIPv6);
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
					DriverTestcase.logger.log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
					System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				}
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.INFO, "'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
				System.out.println("'VRF Name' for 'IPv6' text field is not displaying for "+ commandIPV6);
			}
		}
		else {
			DriverTestcase.logger.log(LogStatus.PASS, "'VRF Name IPv6' text field is not displaying for "+ commandIPV6);
			System.out.println("'VRF Name IPv6' text field is not displaying for "+ commandIPV6 +" command");
		}

	}	


	public void deleteService(String application) throws InterruptedException, DocumentException	{

		//Delete Service
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Delete", "delete", xml);
		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			click_commonMethod(application, "Delete", "deletebutton", xml);
			Thread.sleep(2000);
			compareText(application, "Service Delete success msg", "deletesuccessmsg", "Service successfully deleted", xml);
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
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
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Downloads folder is empty");
			flag = false;
		} else {
			for (File listFile : files) {
				if (listFile.getName().contains(fileName)) {
					System.out.println(fileName + " is present");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+fileName+"' excel file is downloaded successfully");
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

	/**
     *    
      * @param application
     * @throws DocumentException 
      * @throws InterruptedException 
      */
           public void clickOnBreadCrumb(String application, String breadCrumbLink, String devicename) throws InterruptedException, DocumentException {
                 
                 scrollToTop();
                 Thread.sleep(2000);
                 WebElement breadcrumb=null;
     
                 try {
                 breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrumb").replace("value", devicename));
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
