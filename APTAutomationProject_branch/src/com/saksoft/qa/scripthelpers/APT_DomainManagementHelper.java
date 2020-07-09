package com.saksoft.qa.scripthelpers;

import java.io.IOException;
import java.util.List;

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

public class APT_DomainManagementHelper extends DriverHelper {

	public APT_DomainManagementHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/APT_DomainManagement.xml");

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
	
	
	public void verifyCustomerDetailsInformation(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
			throws InterruptedException, DocumentException, IOException {
		
		ScrolltoElement(application, "customerdetailsheader", xml);
		
		// verify Name information
		String Name_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name_Text")));
		String Name_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name_Value")));
		Log.info(Name_Text + " : Field value is displayed as : " + Name_Value);
		System.out.println(Name_Text + " : " + Name_Value);
		sa.assertEquals(Name_Value, name);
		DriverTestcase.logger.log(LogStatus.PASS, Name_Text + " : Field value is displayed as : " + Name_Value);
		
		// verify MainDomain information
		String MainDomain_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/MainDomain_Text")));
		String MainDomain_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/MainDomain_Value")));
		Log.info(MainDomain_Text + " : Field value is displayed as : " + MainDomain_Value);
		System.out.println(MainDomain_Text + "  " + MainDomain_Value);
		sa.assertEquals(MainDomain_Value, maindomain);
		DriverTestcase.logger.log(LogStatus.PASS, MainDomain_Text + " : Field value is displayed as : " + MainDomain_Value);
		
		// verify Country information
		String Country_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Country_Text")));
		String Country_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Country_Value")));
		Log.info(Country_Text + " : Field value is displayed as : " + Country_Value);
		System.out.println(Country_Text + " : " + Country_Value);
		sa.assertEquals(Country_Value, country);
		DriverTestcase.logger.log(LogStatus.PASS, Country_Text + " : Field value is displayed as : " + Country_Value);
		
		// verify OCN information
		String OCN_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/OCN_Text")));
		String OCN_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/OCN_Value")));
		Log.info(OCN_Text + " : Field value is displayed as : " + OCN_Value);
		System.out.println(OCN_Text + " : " + OCN_Value);
		sa.assertEquals(OCN_Value, ocn);
		DriverTestcase.logger.log(LogStatus.PASS, OCN_Text + " : Field value is displayed as : " + OCN_Value);
		
		// verify Reference information
		String Reference_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Reference_Text")));
		String Reference_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Reference_Value")));
		Log.info(Reference_Text + " : Field value is displayed as : " + Reference_Value);
		System.out.println(Reference_Text + " : " + Reference_Value);
		sa.assertEquals(Reference_Value, reference);
		DriverTestcase.logger.log(LogStatus.PASS, Reference_Text + " : Field value is displayed as : " + Reference_Value);
		
		// verify Technical Contact Name information
		String TechnicalContactName_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/TechnicalContactName_Text")));
		String TechnicalContactName_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/TechnicalContactName_Value")));
		Log.info(TechnicalContactName_Text + " : TextField value is displayed as : " + TechnicalContactName_Value);
		System.out.println(TechnicalContactName_Text + " : " + TechnicalContactName_Value);
		sa.assertEquals(TechnicalContactName_Value, tcn);
		DriverTestcase.logger.log(LogStatus.PASS, TechnicalContactName_Text + " : Field value is displayed as : " + TechnicalContactName_Value);
				
		// verify Type information
		String Type_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Type_Text")));
		String Type_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Type_Value")));
		Log.info(Type_Text + " : TextField value is displayed as : " + Type_Value);
		System.out.println(Type_Text + " : " + Type_Value);
		sa.assertEquals(Type_Value, type);
		DriverTestcase.logger.log(LogStatus.PASS, Type_Text + " : Field value is displayed as : " + Type_Value);
		
//		// verify Name2 information
//		String Name2_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name2_Text")));
//		String Name2_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name2_Value")));
//		Log.info(Name2_Text + " : TextField value is displayed as : " + Name2_Value);
//		System.out.println(Name2_Text + " : " + Name2_Value);

		// verify Email information
		String Email_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Email_Text")));
		String Email_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Email_Value")));
		Log.info(Email_Text + " : TextField value is displayed as : " + Email_Value);
		System.out.println(Email_Text + " : " + Email_Value);
		sa.assertEquals(Email_Value, email);
		DriverTestcase.logger.log(LogStatus.PASS, Email_Text + " : Field value is displayed as : " + Email_Value);
		
		// verify Phone information
		String Phone_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Phone_Text")));
		String Phone_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Phone_Value")));
		Log.info(Phone_Text + " : TextField value is displayed as : " + Phone_Value);
		System.out.println(Phone_Text + " : " + Phone_Value);
		sa.assertEquals(Phone_Value, phone);
		DriverTestcase.logger.log(LogStatus.PASS, Phone_Text + " : Field value is displayed as : " + Phone_Value);
		
		// verify Fax information
		String Fax_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Fax_Text")));
		String Fax_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Fax_Value")));
		Log.info(Fax_Text + " : TextField value is displayed as : " + Fax_Value);
		System.out.println(Fax_Text + " : " + Fax_Value);
		sa.assertEquals(Fax_Value, fax);
		DriverTestcase.logger.log(LogStatus.PASS, Fax_Text + " : Field value is displayed as : " + Fax_Value);

		Log.info("=== Customer Details panel fields Verified ===");
		sa.assertAll();
	}
	
	public void verifyUserDetailsInformation(String application, String Login, String Name, String Email, String Roles, String Address, String Resource)
			throws InterruptedException, DocumentException, IOException {
		
		// verify Login column
				
				String Login_Column = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/LoginColumn")));
				Log.info("Login Column text is displayed as : " + Login_Column);
				System.out.println("Login column text:"+ Login_Column);
				sa.assertEquals(Login_Column, Login);
				
		// verify Name column
				String Name_Column = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/NameColumn")));
				Log.info("Name Column text is displayed as : " + Name_Column);
				System.out.println("Name column text:"+ Name_Column);
				sa.assertEquals(Name_Column, Name);
				
		// verify Email column
				String Email_Column = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/EmailColumn")));
				Log.info("Email Column text is displayed as : " + Email_Column);
				System.out.println("Email column text:"+ Email_Column);	
				sa.assertEquals(Email_Column, Email);
				
		// verify Roles column
				String Roles_Column = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/RolesColumn")));
				Log.info("Roles Column text is displayed as : " + Roles_Column);
				System.out.println("Roles column text:"+ Roles_Column);
				sa.assertEquals(Roles_Column, Roles);
				
		// verify Address column
				String Address_Column = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/AddressColumn")));
				Log.info("Address Column text is displayed as : " + Address_Column);
				System.out.println("Address column text:"+ Address_Column);
				sa.assertEquals(Address_Column, Address);
				
		// verify Resource column
				String Resource_Column = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/ResourcesColumn")));
				Log.info("Resource Column text is displayed as : " + Resource_Column);
				System.out.println("Resource column text:"+ Resource_Column);
				sa.assertEquals(Resource_Column, Resource);
	}
	

	public void createnewuser(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email,String Phone, String EditUsername, String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone)
			throws InterruptedException, DocumentException, IOException {
			
		
		WebElement UserGridCheck= driver.findElement(By.xpath("(//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]"));
		String UserGrid= UserGridCheck.getAttribute("style");
		
		if(UserGrid.contains("height: 1px"))
		{
			//Cancel User
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserActionDropdown")));
			Thread.sleep(1000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown button");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")));
			Thread.sleep(1000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Add link");
			Thread.sleep(3000);
			
			Boolean CreateUser_Header = getwebelement(xml.getlocator("//locators/" + application + "/CreateUserHeader")).isDisplayed();
			Log.info("Create user header text is displayed as : " + CreateUser_Header);
			System.out.println("Create user header text:"+ CreateUser_Header);
			sa.assertTrue(CreateUser_Header,"Create User page is displayed");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/UserName")), Username);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered username is : " + Username);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/FirstName")), Firstname);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered firstname is : " + Firstname);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/SurName")), Surname);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered surname is : " + Surname);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PostalAddress")), Postaladdress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered address is : " + Postaladdress);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered email is : " + Email);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Phone")), Phone);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered phone number is : " + Phone);
			
			ScrolltoElement(application, "cancelbutton", xml);
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			Thread.sleep(1000);
			
			compareText(EditPostaladdress, "Users panel header", "userspanel_header", "Users", xml);
			
			//Create User
			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Add", "AddLink", xml);
			Thread.sleep(3000);
			
			Log.info("Create user header text is displayed as : " + CreateUser_Header);
			System.out.println("Create user header text:"+ CreateUser_Header);
			sa.assertTrue(CreateUser_Header,"Create User page is displayed");
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/UserName")), Username);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered username is : " + Username);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/FirstName")), Firstname);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered firstname is : " + Firstname);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/SurName")), Surname);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered surname is : " + Surname);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PostalAddress")), Postaladdress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered address is : " + Postaladdress);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered email is : " + Email);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Phone")), Phone);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered phone number is : " + Phone);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/GeneratePassword")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on generate password button");
			
			WebElement OKButton= getwebelement(xml.getlocator("//locators/" + application + "/OkButton"));
			ScrolltoElement(application, "OkButton", xml);
			Clickon(OKButton);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Ok button");
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : User added successfully");
			
			//Edit User
			List<WebElement> ExistingUsers= driver.findElements(By.xpath("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']"));
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);
			
			if(NoOfUsers==1)
			{
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserUnchecked")));
				Thread.sleep(2000);
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
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
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserActionDropdown")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/edit")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Edit link");
			
			Boolean EditUser_Header = getwebelement(xml.getlocator("//locators/" + application + "/edituser_header")).isDisplayed();
			Log.info("Edit User header text is displayed as : " + EditUser_Header);
			System.out.println("Edit User header text:"+ EditUser_Header);
			sa.assertTrue(EditUser_Header,"Edit User");
			Thread.sleep(1000);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/UserName")), EditUsername);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered username is : " + EditUsername);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/FirstName")), EditFirstname);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered firstname is : " + EditFirstname);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/SurName")), EditSurname);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered surname is : " + EditSurname);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PostalAddress")), EditPostaladdress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered address is : " + EditPostaladdress);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), EditEmail);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered email is : " + EditEmail);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Phone")), EditPhone);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered phone number is : " + EditPhone);
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/OkButton")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Ok button");
			
			//View User
						
			if(NoOfUsers==1)
			{
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserUnchecked")));
				Thread.sleep(2000);
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
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
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserActionDropdown")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on view link");
				
			String UserName_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/UserName")));
			Log.info("User name text is displayed as : " + UserName_field);
			System.out.println("User name text:"+ UserName_field);
			sa.assertEquals(UserName_field, Username);
			
			String FirstName_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FirstName")));
			Log.info("First name text is displayed as : " + FirstName_field);
			System.out.println("First name text:"+ FirstName_field);
			sa.assertEquals(FirstName_field, Firstname);
			
			String SurName_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SurName")));
			Log.info("Surname text is displayed as : " + SurName_field);
			System.out.println("Surname text:"+ SurName_field);
			sa.assertEquals(SurName_field, Surname);
			
			String PostalAddress_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PostalAddress")));
			Log.info("Postal Address text is displayed as : " + PostalAddress_field);
			System.out.println("Postal Address text:"+ PostalAddress_field);
			sa.assertEquals(PostalAddress_field, Postaladdress);
			
			String Email_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Email")));
			Log.info("Email text is displayed as : " + Email_field);
			System.out.println("Email text:"+ Email_field);
			sa.assertEquals(Email_field, Email);
			
			String Phone_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Phone")));
			Log.info("Postal Address text is displayed as : " + Phone_field);
			System.out.println("Postal Address text:"+ Phone_field);
			sa.assertEquals(Phone_field, Phone);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/OkButton")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Ok Button");
			
			Log.info("------ View User successful ------");
			
			//Delete User
			
			if(NoOfUsers==1)
			{
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserUnchecked")));
				Thread.sleep(2000);
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
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
			
			//delete user
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
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserUnchecked")));
				Thread.sleep(2000);
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
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
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserActionDropdown")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/edit")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Edit link");
			
			Boolean EditUser_Header = getwebelement(xml.getlocator("//locators/" + application + "/edituser_header")).isDisplayed();
			Log.info("Edit User header text is displayed as : " + EditUser_Header);
			System.out.println("Edit User header text:"+ EditUser_Header);
			sa.assertTrue(EditUser_Header,"Edit User");
			Thread.sleep(1000);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/UserName")), EditUsername);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered username is : " + EditUsername);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/FirstName")), EditFirstname);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered firstname is : " + EditFirstname);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/SurName")), EditSurname);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered surname is : " + EditSurname);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PostalAddress")), EditPostaladdress);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered address is : " + EditPostaladdress);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), EditEmail);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered email is : " + EditEmail);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Phone")), EditPhone);
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : entered phone number is : " + EditPhone);
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/OkButton")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Ok button");
			
			//View User
						
			if(NoOfUsers==1)
			{
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserUnchecked")));
				Thread.sleep(2000);
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
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
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserActionDropdown")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on view link");
				
			String UserName_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/UserName")));
			Log.info("User name text is displayed as : " + UserName_field);
			System.out.println("User name text:"+ UserName_field);
			sa.assertEquals(UserName_field, Username);
			
			String FirstName_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/FirstName")));
			Log.info("First name text is displayed as : " + FirstName_field);
			System.out.println("First name text:"+ FirstName_field);
			sa.assertEquals(FirstName_field, Firstname);
			
			String SurName_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/SurName")));
			Log.info("Surname text is displayed as : " + SurName_field);
			System.out.println("Surname text:"+ SurName_field);
			sa.assertEquals(SurName_field, Surname);
			
			String PostalAddress_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PostalAddress")));
			Log.info("Postal Address text is displayed as : " + PostalAddress_field);
			System.out.println("Postal Address text:"+ PostalAddress_field);
			sa.assertEquals(PostalAddress_field, Postaladdress);
			
			String Email_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Email")));
			Log.info("Email text is displayed as : " + Email_field);
			System.out.println("Email text:"+ Email_field);
			sa.assertEquals(Email_field, Email);
			
			String Phone_field = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Phone")));
			Log.info("Postal Address text is displayed as : " + Phone_field);
			System.out.println("Postal Address text:"+ Phone_field);
			sa.assertEquals(Phone_field, Phone);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/OkButton")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Ok Button");
			
			Log.info("------ View User successful ------");
			
			//Delete User
			
			if(NoOfUsers==1)
			{
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserUnchecked")));
				Thread.sleep(2000);
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
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
			
			//delete user
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
	
	public void verifyManagementOptionspanel(String application) throws InterruptedException, DocumentException, IOException {
		
		Boolean ManagementOptions_Header = getwebelement(xml.getlocator("//locators/" + application + "/managementoptions_header")).isDisplayed();
		Log.info("Management options header text is displayed as : " + ManagementOptions_Header);
		System.out.println("Management options header text:"+ ManagementOptions_Header);
		sa.assertTrue(ManagementOptions_Header,"Management Options");
		
		// verify customer administration information
				String CustomerAdmin_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/customeradmin_text")));
				String CustomerAdmin_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/customeradmin_value")));
				Log.info(CustomerAdmin_Text + " : checkbox value is displayed as : " + CustomerAdmin_Value);
				System.out.println(CustomerAdmin_Text + " : " + CustomerAdmin_Value);
				
		// verify SAN Administration information
				String SANAdmin_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/sanadmin_text")));
				String SANAdmin_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/sanadmin_value")));
				Log.info(SANAdmin_Text + " : checkbox value is displayed as : " + SANAdmin_Value);
				System.out.println(SANAdmin_Text + " : " + SANAdmin_Value);
		
		// verify Reseller Administration information
				String ResellerAdmin_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/reselleradmin_text")));
				String ResellerAdmin_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/reselleradmin_value")));
				Log.info(ResellerAdmin_Text + " : checkbox value is displayed as : " + ResellerAdmin_Value);
				System.out.println(ResellerAdmin_Text + " : " + ResellerAdmin_Value);
	}
	

	//============================================================================================
	

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
				addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
				// click on next button
				click_commonMethod(application, "Next", "nextbutton", xml);
				Thread.sleep(2000);
				scrollToTop();
				compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

	}


	public void verifyservicecreation(String application, String sid, String Remarks, String email, String phonecontact, String servicecountry, String passwordvalue, String defaultemail, String user, String servicefirstname, String servicelastname, String organizationname, String serviceaddress, String servicecomplement, String servicepostalcode, String servicecity, String servicestate, String servicephone, String servicefax, String orderno, String rfireqno, String servicetype) throws InterruptedException, IOException, DocumentException {
		
		//Cancel service creation
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
			
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
		Log.info("Navigated to create order page");
		System.out.println("Navigated to create order page");
		}
		
		//Create service
	
		ScrolltoElement(application, "createorderservice_header", xml);
		
		//addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno, xml);
		
		//Existing order no select
		boolean availability=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/existingorderdropdown")).isDisplayed();
		  if(availability) {
			  DriverTestcase.logger.log(LogStatus.PASS, "Order/Contract Number(Parent SID) dropdown is displaying");
			  System.out.println("Order/Contract Number(Parent SID) dropdown is displaying");
			  
			  if(orderno.equalsIgnoreCase("null")) {
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under Order/Contract Number(Parent SID) dropdown");
				  System.out.println(" No values selected under Order/Contract Number(Parent SID) dropdown");
			  }else {
				  
				  Clickon(getwebelement("//div[label[text()='Order/Contract Number(Parent SID)']]//div[text()='×']"));
				  Thread.sleep(3000);
				  
				  //verify list of values inside dropdown
				  List<WebElement> listofvalues = driver
							.findElements(By.xpath("(//div[@role='list']//div)[3]"));
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside Order/Contract Number(Parent SID) dropdown is:  ");
				  System.out.println( " List of values inside Order/Contract Number(Parent SID) dropdown is:  ");
				  
					for (WebElement valuetypes : listofvalues) {
								Log.info("service sub types : " + valuetypes.getText());
								DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
								System.out.println(" " + valuetypes.getText());
					}
					
					Thread.sleep(2000);
				SendKeys(getwebelement("//div[label[text()='Order/Contract Number(Parent SID)']]//input"), orderno);	
				Thread.sleep(2000);
					
				  Clickon(getwebelement("(//div[contains(text(),'"+ orderno +"')])[2]"));
				  Thread.sleep(3000);
				  
				  String actualValue=getwebelement("//label[text()='Order/Contract Number(Parent SID)']/following-sibling::div//div/span").getText();
				  DriverTestcase.logger.log(LogStatus.PASS, "Order/Contract Number(Parent SID) dropdown value selected as: "+ actualValue );
				  System.out.println("Order/Contract Number(Parent SID) dropdown value selected as: "+ actualValue);
				  
			  }
		  }else {
			  DriverTestcase.logger.log(LogStatus.FAIL, "Order/Contract Number(Parent SID) is not displaying");
			  System.out.println("Order/Contract Number(Parent SID) is not displaying");
		  }
		}catch(NoSuchElementException e) {
			DriverTestcase.logger.log(LogStatus.FAIL, "Order/Contract Number(Parent SID) is not displaying");
			  System.out.println("Order/Contract Number(Parent SID) is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under Order/Contract Number(Parent SID) dropdown");
			System.out.println(" NO value selected under Order/Contract Number(Parent SID) dropdown");
		}
		
		addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		// click on next button
		click_commonMethod(application, "Next", "nextbutton", xml);
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);
					
		// verify warning messages
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(1000);
		warningMessage_commonMethod(application, "sidwarngmsg", "Service Identification", xml);
		warningMessage_commonMethod(application, "userfieldwarngmsg", "User", xml);
		warningMessage_commonMethod(application, "passwordfieldwarngmsg", "Password", xml);
		warningMessage_commonMethod(application, "defaultemailwarngmsg", "Default Email", xml);
		warningMessage_commonMethod(application, "firstnamefieldwarngmsg", "First Name", xml);
		warningMessage_commonMethod(application, "lastnamefieldwarngmsg", "Last Name", xml);
		warningMessage_commonMethod(application, "organizationnamewarngmsg", "Organization Name", xml);
		warningMessage_commonMethod(application, "addressfieldwarngmsg", "Address", xml);
		warningMessage_commonMethod(application, "postalcodewarngmsg", "Postal Code", xml);
		warningMessage_commonMethod(application, "cityfieldwarngmsg", "City", xml);
		warningMessage_commonMethod(application, "phonefieldwarngmsg", "Phone", xml);
		warningMessage_commonMethod(application, "faxfieldwarngmsg", "Fax", xml);
		warningMessage_commonMethod(application, "emailfieldwarngmsg", "Email", xml);
		
		//service creation
		scrollToTop();
		Thread.sleep(1000);
		addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
		compareText(application, "Service Type", "servicetype_value", servicetype, xml);
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", Remarks, xml);
		addtextFields_commonMethod(application, "Email", "emailtextfieldvalue", email, xml);
		click_commonMethod(application, "Email adding Arrow", "emailaddarrow", xml);
		addtextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", phonecontact, xml);
		click_commonMethod(application, "phone contact adding Arrow", "phoneaddarrow", xml);
		ScrolltoElement(application, "phonecontacttextfieldvalue", xml);
		addtextFields_commonMethod(application, "User", "userfield", user, xml);
		//EnterTextValue(application, passwordvalue, "Password", "Password");
		click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
		addtextFields_commonMethod(application, "Default Email", "defaultemail", defaultemail, xml);
		addtextFields_commonMethod(application, "First Name", "servicefirstname", servicefirstname, xml);
		addtextFields_commonMethod(application, "Last Name", "servicelastname", servicelastname, xml);
		addtextFields_commonMethod(application, "Organization Name", "organizationname", organizationname, xml);
		addtextFields_commonMethod(application, "Address", "serviceaddress", serviceaddress, xml);
		addtextFields_commonMethod(application, "Complement", "servicecomplement", servicecomplement, xml);
		addtextFields_commonMethod(application, "Postal Code", "servicepostalcode", servicepostalcode, xml);
		addtextFields_commonMethod(application, "City", "servicecity", servicecity, xml);
		addtextFields_commonMethod(application, "State", "servicestate", servicestate, xml);
		scrolltoend();
		
		//addDropdown(application, "Country", "service_country", country);
		
		//select country from dropdown
				boolean availability1=false;
				try {  
				  availability1=getwebelement(xml.getlocator("//locators/" + application + "/service_country")).isDisplayed();
				  if(availability1) {
					  DriverTestcase.logger.log(LogStatus.PASS, "Country dropdown is displaying");
					  System.out.println("Country dropdown is displaying");
					  
					  if(servicecountry.equalsIgnoreCase("null")) {
						  
						  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under Country dropdown");
						  System.out.println(" No values selected under Country dropdown");
					  }else {
						  
						  Clickon(getwebelement("//div[label[text()='Country']]//div[text()='×']"));
						  Thread.sleep(3000);
						  
						  //verify list of values inside dropdown
						  List<WebElement> listofvalues = driver
									.findElements(By.xpath("(//div[@role='list']//div)[3]"));
						  
						  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside Country dropdown is:  ");
						  System.out.println( " List of values inside Country dropdown is:  ");
						  
							for (WebElement valuetypes : listofvalues) {
										Log.info("service sub types : " + valuetypes.getText());
										DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
										System.out.println(" " + valuetypes.getText());
							}
							
							Thread.sleep(2000);
						SendKeys(getwebelement("(//div[label[text()='Country']]//input)[1]"), servicecountry);	
						Thread.sleep(2000);
							
						  Clickon(getwebelement("(//div[contains(text(),'"+ servicecountry +"')])[1]"));
						  Thread.sleep(3000);
						  
						  String actualValue=getwebelement("//label[text()='Country']/following-sibling::div//div/span").getText();
						  DriverTestcase.logger.log(LogStatus.PASS, "Country dropdown value selected as: "+ actualValue );
						  System.out.println("Country) dropdown value selected as: "+ actualValue);
						  
					  }
				  }else {
					  DriverTestcase.logger.log(LogStatus.FAIL, "Country is not displaying");
					  System.out.println("Country is not displaying");
				  }
				}catch(NoSuchElementException e) {
					DriverTestcase.logger.log(LogStatus.FAIL, "Country is not displaying");
					  System.out.println("Country is not displaying");
				}catch(Exception ee) {
					ee.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under Country dropdown");
					System.out.println(" NO value selected under Country dropdown");
				}
				
		addtextFields_commonMethod(application, "Phone", "servicephone", servicephone, xml);
		addtextFields_commonMethod(application, "Fax", "servicefax", servicefax, xml);
		addtextFields_commonMethod(application, "Email", "serviceemail", email, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(3000);
		compareText(application, "Service creation success msg", "servicecreationmessage", "Service successfully created.", xml);
			sa.assertAll();
	
	}

	public void verifyorderpanelinformation_Existingorder(String application, String existingorder,
			String expectedorderno, String expectedvoicelineno) throws InterruptedException, DocumentException {

		if (existingorder.equalsIgnoreCase("Yes")) {

			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue"))
					.getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue"))
					.getText();
			System.out.println("actual voice line number displayed in order panel is : " + actualvoicelineno);

			if (expectedorderno.equalsIgnoreCase(actualorderno)
					&& expectedvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

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

			String actualorderno= GetText(application, "Order number", "ordernumbervalue");
			String actualvoicelineno= GetText(application, "Voice line number", "ordervoicelinenumbervalue");

			if (expectedneworderno.equalsIgnoreCase(actualorderno)&& expectednewvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

				DriverTestcase.logger.log(LogStatus.PASS, "Step : order information is matched");				
			} else {
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : order information is not matched");
			}

		} else {
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

	click_commonMethod(application, "Order Number", "editorderno", xml);
	Thread.sleep(1000);
	cleartext(application, "Order Number", "editorderno");
	Thread.sleep(1000);
	addtextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);

	click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
	Thread.sleep(1000);
	cleartext(application, "RFI Voice Line Number", "editvoicelineno");
	Thread.sleep(1000);
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
			//click_commonMethod(application, "Order Number", "changeordernumber", xml);
			Thread.sleep(2000);
			addtextFields_commonMethod(application, "Order Number", "changeordernumber", changeorderno, xml);
			//click_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", xml);
			Thread.sleep(2000);
			addtextFields_commonMethod(application, "RFI Voice Line Number", "changeordervoicelinenumber", changevoicelineno, xml);
			Thread.sleep(1000);
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
//			WebElement CreateOrderButton= getwebelement(xml.getlocator("//locators/" + application + "/createorder_button"));
//			((JavascriptExecutor) driver).executeScript("arguments[0].click();", CreateOrderButton);
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

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String Remarks, String email, String phonecontact, String country, String defaultemail, String servicefirstname, String servicelastname, String organizationname, String serviceaddress, String servicecomplement, String servicepostalcode, String servicecity, String servicestate, String servicephone, String servicefax, String user, String orderno, String rfireqno) throws InterruptedException, DocumentException, IOException {
		
		compareText(application, "Service panel header", "servicepanel_header", "Service", xml);
		Thread.sleep(1000);
		ScrolltoElement(application, "servicecityvalue", xml);
		compareText(application, "Service Identification", "sidvalue", sid, xml);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype, xml);
		GetText(application, "Email", "emailvalue");
		//compareText(application, "Email", "emailvalue", email, xml);
		compareText(application, "Phone Contact", "phonecontactvalue", phonecontact, xml);
		compareText(application, "Remarks", "remarksvalue", Remarks, xml);
		compareText(application, "User", "userfieldvalue", user, xml);
		compareText(application, "Default Email", "defaultemailvalue", defaultemail, xml);
		compareText(application, "First Name", "servicefirstnamevalue", servicefirstname, xml);
		compareText(application, "Last Name", "servicelastnamevalue", servicelastname, xml);
		compareText(application, "Organisation Name", "organizationnamevalue", organizationname, xml);
		compareText(application, "Address", "serviceaddressvalue", serviceaddress, xml);
		compareText(application, "Complement", "servicecomplementvalue", servicecomplement, xml);
		compareText(application, "Postal Code", "servicepostalcodevalue", servicepostalcode, xml);
		compareText(application, "City", "servicecityvalue", servicecity, xml);
		compareText(application, "State", "servicestatevalue", servicestate, xml);
		compareText(application, "Country", "servicecountryvalue", country, xml);
		compareText(application, "Phone", "servicephonevalue", servicephone, xml);
		compareText(application, "Fax", "servicefaxvalue", servicefax, xml);
		compareText(application, "Email", "serviceemailvalue", email, xml);
		
	}
	
	public void verifyservicepanel_links(String application, String EditRemarks, String Remarks, String changeorderno, String sid, String editsid, String servicetype, String editemail, String editphonecontact, String edituser, String editdefaultemail, String editservicefirstname, String editservicelastname, String editorganizationname, String editserviceaddress, String editservicecomplement, String editservicepostalcode, String editservicecity, String editservicestate, String editcountry, String editservicephone, String editservicefax ) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		isDisplayed(application, "editservice", "Edit Service Header");
		ScrolltoElement(application, "editservice", xml);
		cleartext(application, "Service Identification", "serviceidentificationtextfield");
		addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", editsid, xml);
		cleartext(application, "Remarks", "remarktextarea");
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			ScrolltoElement(application, "orderpanelheader", xml);
			compareText(application, "Service Identification", "sidvalue", sid, xml);
			compareText(application, "Remarks", "remarksvalue", Remarks, xml);
		}
		else
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
	
		//Edit service
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		isDisplayed(application, "editservice", "Edit Service Header");
		ScrolltoElement(application, "editservice", xml);
		cleartext(application, "Service Identification", "serviceidentificationtextfield");
		addtextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", editsid, xml);
		compareText(application, "Service Type", "servicetype_value", servicetype, xml);
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
		ScrolltoElement(application, "phonecontacttextfieldvalue", xml);
		cleartext(application, "User", "userfield");
		addtextFields_commonMethod(application, "User", "userfield", edituser, xml);
		cleartext(application, "Password", "Password");
		click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
		cleartext(application, "Default Email", "defaultemail");
		addtextFields_commonMethod(application, "Default Email", "defaultemail", editdefaultemail, xml);
		cleartext(application, "First Name", "servicefirstname");
		addtextFields_commonMethod(application, "First Name", "servicefirstname", editservicefirstname, xml);
		cleartext(application, "Last Name", "servicelastname");
		addtextFields_commonMethod(application, "Last Name", "servicelastname", editservicelastname, xml);
		cleartext(application, "Organization Name", "organizationname");
		addtextFields_commonMethod(application, "Organization Name", "organizationname", editorganizationname, xml);
		cleartext(application, "Address", "serviceaddress");
		addtextFields_commonMethod(application, "Address", "serviceaddress", editserviceaddress, xml);
		cleartext(application, "Complement", "servicecomplement");
		addtextFields_commonMethod(application, "Complement", "servicecomplement", editservicecomplement, xml);
		cleartext(application, "Postal Code", "servicepostalcode");
		addtextFields_commonMethod(application, "Postal Code", "servicepostalcode", editservicepostalcode, xml);
		cleartext(application, "City", "servicecity");
		addtextFields_commonMethod(application, "City", "servicecity", editservicecity, xml);
		cleartext(application, "State", "servicestate");
		addtextFields_commonMethod(application, "State", "servicestate", editservicestate, xml);
		ScrolltoElement(application, "country", xml);
		addDropdownValues_commonMethod(application, "Country", "country", editcountry, xml);
		cleartext(application, "Phone", "servicephone");
		addtextFields_commonMethod(application, "Phone", "servicephone", editservicephone, xml);
		cleartext(application, "Fax", "servicefax");
		addtextFields_commonMethod(application, "Fax", "servicefax", editservicefax, xml);
		cleartext(application, "Email", "serviceemail");
		addtextFields_commonMethod(application, "Email", "serviceemail", editemail, xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "Next", "editservice_next", xml);
							
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to view service page");
			compareText(application, "Service updated success msg", "serviceupdate_successmsg", "Service successfully updated.", xml);
			sa.assertAll();
			}
			else
				Log.info("Service not updated");
				System.out.println("Service not updated");
			
			//verify edit service details in view service page
				
				ScrolltoElement(application, "servicecityvalue", xml);
				compareText(application, "Service Identification", "sidvalue", editsid, xml);
				compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype, xml);
				//compareText(application, "Email", "emailvalue", editemail, xml);
				GetText(application, "Email", "emailvalue");
				compareText(application, "Phone Contact", "phonecontactvalue", editphonecontact, xml);
				compareText(application, "Remarks", "remarksvalue", EditRemarks, xml);
				compareText(application, "User", "userfieldvalue", edituser, xml);
				compareText(application, "Default Email", "defaultemailvalue", editdefaultemail, xml);
				compareText(application, "First Name", "servicefirstnamevalue", editservicefirstname, xml);
				compareText(application, "Last Name", "servicelastnamevalue", editservicelastname, xml);
				compareText(application, "Organisation Name", "organizationnamevalue", editorganizationname, xml);
				compareText(application, "Address", "serviceaddressvalue", editserviceaddress, xml);
				compareText(application, "Complement", "servicecomplementvalue", editservicecomplement, xml);
				compareText(application, "Postal Code", "servicepostalcodevalue", editservicepostalcode, xml);
				compareText(application, "City", "servicecityvalue", editservicecity, xml);
				compareText(application, "State", "servicestatevalue", editservicestate, xml);
				compareText(application, "Country", "servicecountryvalue", editcountry, xml);
				compareText(application, "Phone", "servicephonevalue", editservicephone, xml);
				compareText(application, "Fax", "servicefaxvalue", editservicefax, xml);
				compareText(application, "Email", "serviceemailvalue", editemail, xml);
				
			//synchronize link in view service page
				ScrolltoElement(application, "orderpanelheader", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
			click_commonMethod(application, "Synchronize", "synchronizelink_servicepanel", xml);
			Thread.sleep(2000);
			ScrolltoElement(application, "Sync_successmsg", xml);
			compareText(application, "Syncronize success message", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.", xml);

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
			compareText(application, "Service Delete success msg", "deletesuccessmsg", "Service successfully deleted.", xml);
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
	 	
	 public String ischecked(String application, String ischecked, String xpath) throws InterruptedException, DocumentException {
		 ischecked= "No";
		 ischecked= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).getText();
		 if(ischecked=="Yes")
		 {
			 return ischecked;
		 }
		 else
		return ischecked;
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
