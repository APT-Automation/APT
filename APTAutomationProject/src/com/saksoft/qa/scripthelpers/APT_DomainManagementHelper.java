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
	
	public void createnewcustomer(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
			throws Exception {

		// webelementpresencelogger(getwebelement(xml.getlocator("//locators/" +
		// application + "/createcustomerlink")), "Create Customer link");
	//	WebDriverWait wait= new WebDriverWait(driver,50);
	//	wait= (WebDriverWait) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='ant-menu-submenu-title'])[2]")));
		
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);
		System.out.println("Mouser hovered on Manage Customer's Service");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");
		
		click(application, "create customer link", "createcustomerlink");
		Thread.sleep(2000);
		compareText(application, "create customer page header", "createcustomer_header", "Customer");
		
		click(application, "Ok", "okbutton");
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
		click(application, "Clear button", "clearbutton");
		DriverTestcase.logger.log(LogStatus.PASS, "All text field values are cleared");
		
		//Create customer by providing all info
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		addDropdownValues(application, "Country", "country", country);
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		addDropdownValues(application, "Type", "typedropdown", type);
		EnterTextValue(application, email, "Email", "emailtextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		click(application, "Ok", "okbutton");
		compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.");
		sa.assertAll();

	}


	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected, String customerName1, String customerName2)
			throws InterruptedException, DocumentException, IOException {
		
	
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		System.out.println("Mouser hovered on Manage Customer's Service");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");
		
		click(application, "Create Order/Service Link", "CreateOrderServiceLink");	
		Log.info("=== Create Order/Service navigated ===");
		
	//click on Next button to check mandatory messages	
		click(application, "Next", "nextbutton");
		
		//Customer Error message	
			WarningMessage(application, "customer_createorderpage_warngmsg", "Customer");
			
		//Entering Customer name	
			EnterTextValue(application, customerName1, "Customer Name", "entercustomernamefield");
			EnterTextValue(application, customerName2, "Customer Name", "entercustomernamefield");	
		
		//Select Customer from dropdown
			addDropdownValues(application, "Customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected);
			click(application, "Next", "Next_Button");
	}
	
	
	public void verifyCustomerDetailsInformation(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
			throws InterruptedException, DocumentException, IOException {
		
		WebElement CustomerDetailsHeader=getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader"));
		scrolltoview(CustomerDetailsHeader);
		
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
			
			WebElement CancelButton= getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton"));
			scrolltoview(CancelButton);
			Clickon(CancelButton);
			Thread.sleep(1000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on cancel button");
			
			Boolean UsersPanel_Header = getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header")).isDisplayed();
			Log.info("Users panel header text is displayed as : " + UsersPanel_Header);
			System.out.println("Users panel header text:"+ UsersPanel_Header);
			sa.assertTrue(UsersPanel_Header,"Users panel in view service page is displayed");
			
			//Create User
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserActionDropdown")));
			Thread.sleep(1000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown button");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")));
			Thread.sleep(1000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Add link");
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
			scrolltoview(OKButton);
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
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserActionDropdown")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown");
			
			delete(application, "delete", "User", "User successfully deleted");
			
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
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/UserActionDropdown")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown");
			
			delete(application, "delete", "User", "User successfully deleted");
		
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
	


	String value;

	public void verifyvaluedisplayed(WebElement ele) {

		try {

			Boolean flag = ele.isDisplayed();

			if (flag) {

				value = ele.getText();

				if (value.isEmpty()) {

					Reporter.log("No values displayed");
				} else {

					Reporter.log("value displayed is : " + value);
				}

			} else {

				Reporter.log("value displayed is : " + value);
			}

		} catch (Exception e) {

			Reporter.log("No values displaying under remark");
		}
	}

	public void navigateToManageCustomerServicePage(String application) throws InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/mcslink")));
		Thread.sleep(2000);
		Reporter.log("=== MCS page navigated ===");
		Thread.sleep(2000);
	}

	public void navigateToCreateOrderServicePage(String application) throws InterruptedException, DocumentException {

		navigateToManageCustomerServicePage(application);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createorderlink")));
		Thread.sleep(2000);
		Reporter.log("=== Create Order/Service navigated ===");
	}

	boolean customername;

	public void verifychoosecustomer(String application, String name, String customer)
			throws InterruptedException, IOException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		Thread.sleep(5000);

		boolean choosocustomerwarningmsg = getwebelement(
				xml.getlocator("//locators/" + application + "/choosocustomerwarningmsg")).isDisplayed();
		sa.assertTrue(choosocustomerwarningmsg, "next button is displayed");
		System.out.println("choose customer is required message is displayed");
		Log.info("===choose customer is required message is displayed ===");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
		Thread.sleep(10000);
		System.out.println("Entered Name is : " + name);

		/*
		 * SendKeys(getwebelement(xml.getlocator("//locators/" + application +
		 * "/choosecustomerdropdown")), customer); Thread.sleep(5000);
		 * System.out.println("Entered customer Name is : " + customer);
		 */

		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/choosecustomerdropdown")));
		Thread.sleep(5000);
		System.out.println("clicked on customer dropdown");

		try {
			customername = driver.findElement(By.xpath("//div[text()='No matches found']")).isDisplayed();
			Thread.sleep(5000);
			System.out.println("flag:" + customername);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			WebElement customername1 = driver.findElement(By.xpath("//span[text()='" + customer + "']"));
			Thread.sleep(5000);
			customername1.click();
		}

		while (customername) {

			getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")).clear();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
			Thread.sleep(10000);
			getwebelement(xml.getlocator("//locators/" + application + "/choosecustomerdropdown")).clear();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/choosecustomerdropdown")));
			Thread.sleep(5000);
			// System.out.println("clicked on customer dropdown")
			try {
				customername = driver.findElement(By.xpath("//div[text()='No matches found']")).isDisplayed();
			} catch (Exception e) {
				break;
			}

		}

		WebElement customername1 = driver.findElement(By.xpath("//span[text()='" + customer + "']"));
		Thread.sleep(5000);
		customername1.click();
		// System.out.println("selected cutomer is : " +
		// customername1.getText().toString());

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		Thread.sleep(5000);
		System.out.println("clicked on next button");

	}

	// verify create order/service panel
	public void verifycreateorderservicepanel(String application) throws InterruptedException, DocumentException {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		try {
			boolean ordercontractnumbertextfield = getwebelement(
					xml.getlocator("//locators/" + application + "/ordercontractnumber")).isDisplayed();
			sa.assertTrue(ordercontractnumbertextfield, "order contract number textfield is displayed");
			// DriverTestcase.logger.log(LogStatus.PASS, "order contract number textfield is
			// displayed");
			System.out.println("order contract number textfield is displayed");

			boolean selectorderswitch = getwebelement(
					xml.getlocator("//locators/" + application + "/selectorderswitch")).isDisplayed();
			sa.assertTrue(selectorderswitch, "c");
			// DriverTestcase.logger.log(LogStatus.PASS, "select order switch is
			// displayed");
			System.out.println("select order switch is displayed");

			boolean rfireqiptextfield = getwebelement(
					xml.getlocator("//locators/" + application + "/rfireqiptextfield")).isDisplayed();
			sa.assertTrue(rfireqiptextfield, "RFI / RFQ /IP Voice Line number text field is displayed ");
			// DriverTestcase.logger.log(LogStatus.PASS, "RFI / RFQ /IP Voice Line number
			// text field is displayed ");
			System.out.println("RFI / RFQ /IP Voice Line number text field is displayed ");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectorderswitch")));
			Thread.sleep(5000);

			System.out.println("clicked on the switch to verify the presence of create order button");
			// DriverTestcase.logger.log(LogStatus.PASS, "clicked on the switch to verify
			// the presence of create order button ");

			boolean createorderbutton1 = getwebelement(
					xml.getlocator("//locators/" + application + "/createorderbutton")).isDisplayed();

			if (createorderbutton1) {

				System.out.println("create order button is displayed");
			} else {

				sa.fail("create order button is not displayed");
			}

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createorderswitch")));
			Thread.sleep(5000);
			// DriverTestcase.logger.log(LogStatus.PASS, "clicked on the switch to verify
			// the presence of create order button ");

			System.out.println("clicked on switch to verify the presence of create order button");

			try {
				boolean actualcreateorder = getwebelement(
						xml.getlocator("//locators/" + application + "/createorderbutton")).isDisplayed();
			} catch (Exception e) {

				System.out.println("create order button is not displayed");
			}

			sa.assertAll();

		} catch (TimeoutException e) {

			e.printStackTrace();
		}

	}

//	public static String SelectOrderNumber;
//
//	public void createexistingorderservice(String application, String existingorderservice, String existingordernumber)
//			throws InterruptedException, IOException, DocumentException {
//
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//
//		if (existingorderservice.equalsIgnoreCase("YES")) {
//
//			addDropdownValues(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber);
//			Log.info("=== Order Contract Number selected===");
//
//			Thread.sleep(3000);
//
//			SelectOrderNumber = existingordernumber;
//		
//
//		} else {
//
//			System.out.println("existing order not selected");
//			DriverTestcase.logger.log(LogStatus.INFO, "Step :existing order not selected");
//		}
//
//	}

	public static String newordernumber, newVoiceLineNumber, SelectOrderNumber;
	
	public void createorderservice(String application, String neworder, String neworderno, String newrfireqno, String existingorderservice, String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		if (neworder.equalsIgnoreCase("YES")) {

			WebElement CreateOrder_Header= driver.findElement(By.xpath("//div[text()='Create Order / Service']"));
			scrolltoview(CreateOrder_Header);
			Thread.sleep(2000);
			
			click(application, "select order switch", "selectorderswitch");	
			EnterTextValue(application, neworderno, "Order/Contract Number", "newordertextfield");
			EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");
			click(application, "create order", "createorderbutton");	
			compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully");			
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

	public void verifyselectservicetype(String application, String servicetype)
			throws InterruptedException, IOException, DocumentException {

			// select service type
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
			addDropdownValues(application, "Service Type", "servicetypetextfield", servicetype);
			// click on next button
			click(application, "Next", "nextbutton");	
			compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service");
			
	}

	public void servicecreationfields(String application) throws InterruptedException, DocumentException {

		try {

			// service identification
			boolean serviceidentificationtextfield = getwebelement(
					xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")).isDisplayed();
			sa.assertTrue(serviceidentificationtextfield, "service identification textfield is not displayed");

			String serviceidentificationtextfieldvalue = getwebelement(
					xml.getlocator("//locators/" + application + "/serviceidentificationtextfield"))
							.getAttribute("value");
			if (!(serviceidentificationtextfieldvalue.isEmpty())) {

				sa.fail("service identification textfield is not empty");
				DriverTestcase.logger.log(LogStatus.FAIL,
						"Step : service identification textfield is not empty, value displayed is : "
								+ serviceidentificationtextfieldvalue);
			} else {

				DriverTestcase.logger.log(LogStatus.PASS, "Step : service identification textfield is empty ");
			}

			// service type
			boolean servicetype = getwebelement(xml.getlocator("//locators/" + application + "/servicetypevalue")).isDisplayed();
			sa.assertTrue(servicetype, "service type value is not displayed");

			String servicetypevalue = getwebelement(xml.getlocator("//locators/" + application + "/servicetypevalue")).getText();
			if (servicetypevalue != null && servicetypevalue.equalsIgnoreCase("NGIN")) {

				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Service Type value is not empty  and displayed service type is : " + servicetypevalue);
			}

			// remarks
			boolean remarktextarea = getwebelement(xml.getlocator("//locators/" + application + "/remarktextarea"))
					.isDisplayed();
			sa.assertTrue(remarktextarea, "remarks textarea is not displayed");

			String remarktextareavalue = getwebelement(
					xml.getlocator("//locators/" + application + "/remarktextareavalue")).getAttribute("value");
			if (!(remarktextareavalue).isEmpty()) {

				sa.fail("remarks textarea is not empty");
				DriverTestcase.logger.log(LogStatus.FAIL,
						"Step : remarks textarea is not empty, value displayed is : " + remarktextareavalue);
			} else {

				DriverTestcase.logger.log(LogStatus.PASS, "Step : remarks textarea is empty ");
			}

			// management options- customer administration
			boolean customeradministrationcheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/customeradministrationcheckbox")).isEnabled();
			boolean customeradministrationcheckbox2 = getwebelement(
					xml.getlocator("//locators/" + application + "/customeradministrationcheckbox")).isSelected();

			if (customeradministrationcheckbox == true && customeradministrationcheckbox2 == false) {

				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : customer administration checkbox is unselected by default ");

			} else {

				sa.fail("customer administration checkbox is disabled or selected by default");
				DriverTestcase.logger.log(LogStatus.FAIL,
						"Step : customer administration checkbox is disabled or selected by default ");
			}

			// management options- SAN administration
			boolean SANadministrationcheckbox1 = getwebelement(
					xml.getlocator("//locators/" + application + "/sanadministrationcheckbox")).isEnabled();
			boolean SANadministrationcheckbox2 = getwebelement(
					xml.getlocator("//locators/" + application + "/sanadministrationcheckbox")).isSelected();

			if (SANadministrationcheckbox1 == true && SANadministrationcheckbox2 == false) {

				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : SAN administration checkbox is unselected by default ");

			} else {

				sa.fail("SAN administration checkbox is disabled or selected by default");
				DriverTestcase.logger.log(LogStatus.FAIL,
						"Step : SAN administration checkbox is disabled or selected by default ");
			}

			// management options- Reseller Administration
			boolean Reselleradministrationcheckbox1 = getwebelement(
					xml.getlocator("//locators/" + application + "/reselleradministrationcheckbox")).isEnabled();
			boolean Reselleradministrationcheckbox2 = getwebelement(
					xml.getlocator("//locators/" + application + "/reselleradministrationcheckbox")).isSelected();

			if (Reselleradministrationcheckbox1 == true && Reselleradministrationcheckbox2 == false) {

				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Reseller Administration checkbox is unselected by default ");

			} else {

				sa.fail("Reseller Administration checkbox is disabled or selected by default");
				DriverTestcase.logger.log(LogStatus.FAIL,
						"Step : Reseller Administration checkbox is disabled or selected by default ");
			}

			// next and cancel buttons
			boolean nextbutton = getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")).isDisplayed();
			sa.assertTrue(nextbutton, "Next button is not displayed");

			boolean cancelbutton = getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton")).isDisplayed();
			sa.assertTrue(cancelbutton, "Cancel button is not displayed");
			sa.assertAll();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Element is not displayed in UI");
		}

	}

	public void verifyingservicecreation(String application, String sid, String Remarks, String email, String phonecontact, String country, String passwordvalue, String defaultemail, String user, String servicefirstname, String servicelastname, String organizationname, String serviceaddress, String servicecomplement, String servicepostalcode, String servicecity, String servicestate, String servicephone, String servicefax, String orderno, String rfireqno, String servicetype) throws InterruptedException, IOException, DocumentException {
		
		//Cancel service creation
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service");
		
		click(application, "Cancel", "cancelbutton");
			
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
		Log.info("Navigated to create order page");
		System.out.println("Navigated to create order page");
		}
		
		//Create service
		
		WebElement CreateOrder_Header= driver.findElement(By.xpath("//div[text()='Create Order / Service']"));
		scrolltoview(CreateOrder_Header);
		
		addDropdownValues(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno);
		addDropdownValues(application, "Service Type", "servicetypetextfield", servicetype);
		// click on next button
		click(application, "Next", "nextbutton");
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service");
					
		// verify warning messages
		click(application, "Next", "nextbutton");
		Thread.sleep(1000);
		WarningMessage(application, "sidwarngmsg", "Service Identification");
		WarningMessage(application, "userfieldwarngmsg", "User");
		WarningMessage(application, "passwordfieldwarngmsg", "Password");
		WarningMessage(application, "defaultemailwarngmsg", "Default Email");
		WarningMessage(application, "firstnamefieldwarngmsg", "First Name");
		WarningMessage(application, "lastnamefieldwarngmsg", "Last Name");
		WarningMessage(application, "organizationnamewarngmsg", "Organization Name");
		WarningMessage(application, "addressfieldwarngmsg", "Address");
		WarningMessage(application, "postalcodewarngmsg", "Postal Code");
		WarningMessage(application, "cityfieldwarngmsg", "City");
		WarningMessage(application, "phonefieldwarngmsg", "Phone");
		WarningMessage(application, "faxfieldwarngmsg", "Fax");
		WarningMessage(application, "emailfieldwarngmsg", "Email");
		
		//service creation
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(1000);
		EnterTextValue(application, sid, "Service Identification", "serviceidentificationtextfield");
		compareText(application, "Service Type", "servicetype_value", servicetype);
		EnterTextValue(application, Remarks, "Remarks", "remarktextarea");
		EnterTextValue(application, email, "Email", "emailtextfieldvalue");
		click(application, "Email adding Arrow", "emailaddarrow");
		EnterTextValue(application, phonecontact, "Phone Contact", "phonecontacttextfieldvalue");
		click(application, "phone contact adding Arrow", "phoneaddarrow");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/phonecontacttextfieldvalue")));
		EnterTextValue(application, user, "User", "userfield");
		//EnterTextValue(application, passwordvalue, "Password", "Password");
		click(application, "Generate Password", "GeneratePassword");
		EnterTextValue(application, defaultemail, "Default Email", "defaultemail");
		EnterTextValue(application, servicefirstname, "First Name", "servicefirstname");
		EnterTextValue(application, servicelastname, "Last Name", "servicelastname");
		EnterTextValue(application, organizationname, "Organization Name", "organizationname");
		EnterTextValue(application, serviceaddress, "Address", "serviceaddress");
		EnterTextValue(application, servicecomplement, "Complement", "servicecomplement");
		EnterTextValue(application, servicepostalcode, "Postal Code", "servicepostalcode");
		EnterTextValue(application, servicecity, "City", "servicecity");
		EnterTextValue(application, servicestate, "State", "servicestate");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		addDropdownValues(application, "Country", "country", country);
		EnterTextValue(application, servicephone, "Phone", "servicephone");
		EnterTextValue(application, servicefax, "Fax", "servicefax");
		EnterTextValue(application, email, "Email", "serviceemail");
		
		click(application, "Next", "nextbutton");
		compareText(application, "Service creation success msg", "servicecreationmessage", "Service successfully created.");
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

		WebElement UsersPanel_Header= getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header"));
		scrolltoview(UsersPanel_Header);
		
		if (neworder.equalsIgnoreCase("YES")) {

			String actualorderno = getwebelement(xml.getlocator("//locators/" + application + "/ordernumbervalue"))
					.getText();
			System.out.println("actual order number displayed in order panel is : " + actualorderno);

			String actualvoicelineno = getwebelement(xml.getlocator("//locators/" + application + "/ordervoicelinenumbervalue"))
					.getText();
			System.out.println("actual voice line number displayed in order panel is : " + actualvoicelineno);

			if (expectedneworderno.equalsIgnoreCase(actualorderno)
					&& expectednewvoicelineno.equalsIgnoreCase(actualvoicelineno)) {

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
	
	
	public void scrolltoview(WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno) throws InterruptedException, DocumentException, IOException {
		
	WebElement UsersPanel_Header= getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header"));
	scrolltoview(UsersPanel_Header);
	
	//Cancel Edit order in Order panel
	click(application, "Action dropdown", "orderactionbutton");
	click(application, "Edit Order", "editorderlink");
	
	Boolean EditOrder_Header = getwebelement(xml.getlocator("//locators/" + application + "/editorderheader")).isDisplayed();
	Log.info("Edit Order header text is displayed as : " + EditOrder_Header);
	System.out.println("Edit Order header text:"+ EditOrder_Header);
	sa.assertTrue(EditOrder_Header,"Edit Order");
	DriverTestcase.logger.log(LogStatus.PASS, "Step: Edit Order header text is displayed as : "+ EditOrder_Header);
	Thread.sleep(1000);
	
	WebElement EditOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/editorderno"));
	click(application, "Order Number", "editorderno");
	Thread.sleep(2000);
	Clear(EditOrderNo);
	Thread.sleep(2000);
	EnterTextValue(application, editorderno, "Order Number", "editorderno");

	WebElement EditVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/editvoicelineno"));
	click(application, "RFI Voice Line Number", "editvoicelineno");
	Thread.sleep(2000);
	Clear(EditVoiceLineNo);
	Thread.sleep(2000);
	EnterTextValue(application, editvoicelineno, "Order Number", "editvoicelineno");
	click(application, "Cancel", "cancelbutton");
		
	Boolean OrderHeader = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
	sa.assertTrue(OrderHeader,"Order");
	Log.info("Navigated to order panel in view service page");
	DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");
	
	//Edit Order
	click(application, "Action dropdown", "orderactionbutton");
	click(application, "Edit Order", "editorderlink");
		
	Boolean EditOrder1_Header = getwebelement(xml.getlocator("//locators/" + application + "/editorderheader")).isDisplayed();
	Log.info("Edit Order header text is displayed as : " + EditOrder1_Header);
	System.out.println("Edit Order header text:"+ EditOrder1_Header);
	sa.assertTrue(EditOrder1_Header,"Edit Order");
	Thread.sleep(1000);
	
	click(application, "Order Number", "editorderno");
	Thread.sleep(2000);
	Clear(EditOrderNo);
	Thread.sleep(2000);
	EnterTextValue(application, editorderno, "Order Number", "editorderno");
	
	click(application, "RFI Voice Line Number", "editvoicelineno");
	Thread.sleep(2000);
	Clear(EditVoiceLineNo);
	Thread.sleep(2000);
	EnterTextValue(application, editvoicelineno, "Order Number", "editvoicelineno");
	click(application, "OK", "editorder_okbutton");
	Thread.sleep(2000);
	compareText(application, "Edit Order success msg", "successmsg", "Order successfully updated");
		
	//Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
	sa.assertTrue(OrderHeader,"Order");
	Log.info("Navigated to order panel in view service page");
	DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");
	
	compareText(application, "Order Number", "ordernumbervalue", editorderno);
	compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno);
	Log.info("------ Edit Order is successful ------");
	
}
		
	public void verifyorderpanel_changeorder(String application, String changeorderno, String changevoicelineno) throws InterruptedException, DocumentException, IOException {
		
		WebElement UsersPanel_Header= getwebelement(xml.getlocator("//locators/" + application + "/userspanel_header"));
		scrolltoview(UsersPanel_Header);
		
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Change Order", "changeorderlink");
				
		Boolean ChangeOrder_Header = getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed();
		Log.info("Change Order header text is displayed as : " + ChangeOrder_Header);
		System.out.println("Change Order header text:"+ ChangeOrder_Header);
		sa.assertTrue(ChangeOrder_Header,"Change Order");
		Thread.sleep(1000);
		
		click(application, "Choose order dropdown", "changeorder_chooseorderdropdown");
				
		List<WebElement> ChangeOrder_DropdownList= driver.findElements(By.xpath(xml.getlocator("//locators/" + application + "/changeorder_dropdownlist")));
		int ChangeOrder_Dropdown_count= ChangeOrder_DropdownList.size();
		if(ChangeOrder_Dropdown_count>= 1)
		{
			Clickon(getwebelement("//label[text()='Order/Contract Number (Parent SID)']/parent::div//div[@role='list']//span[@aria-selected='false'][1]"));
			Thread.sleep(3000);
			
			//Cancel change order
			click(application, "Cancel", "changeorder_cancelbutton");
						
			Boolean OrderHeader = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader,"Order");
			Log.info("Navigated to order panel in view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");
			
			//Change order
			click(application, "Action dropdown", "orderactionbutton");
			click(application, "Change Order", "changeorderlink");
						
			Boolean ChangeOrder1_Header = getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed();
			Log.info("Change Order header text is displayed as : " + ChangeOrder1_Header);
			System.out.println("Change Order header text:"+ ChangeOrder1_Header);
			sa.assertTrue(ChangeOrder1_Header,"Change Order");
			Thread.sleep(1000);
			
			click(application, "Choose order dropdown", "changeorder_chooseorderdropdown");
			Clickon(getwebelement("//label[text()='Order/Contract Number (Parent SID)']/parent::div//div[@role='list']//span[@aria-selected='false'][1]"));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected order from dropdown");
			
			click(application, "OK", "changeorder_okbutton");
			Thread.sleep(2000);
			compareText(application, "Change Order success msg", "successmsg", "Order successufully changed.");
						
			Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
			sa.assertTrue(OrderHeader1,"Order");
			Log.info("Navigated to order panel in view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");
			
			compareText(application, "Order Number", "ordernumbervalue", changeorderno);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno);
			
			Log.info("------ Change Order is successful ------");
			
		}
		else
		{
			//Cancel change order
			click(application, "Select order switch", "changeorder_selectorderswitch");

	//	WebElement ChangeOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/changeordernumber"));
		click(application, "Order Number", "changeordernumber");
		Thread.sleep(2000);
		EnterTextValue(application, changeorderno, "Order Number", "changeordernumber");
				
	//	WebElement ChangeVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/changeordervoicelinenumber"));
		click(application, "RFI Voice Line Number", "changeordervoicelinenumber");
		Thread.sleep(3000);
		EnterTextValue(application, changevoicelineno, "RFI Voice Line Number", "changeordervoicelinenumber");
		//scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton")));
		click(application, "Cancel", "changeorder_cancelbutton");

		Boolean OrderHeader = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
		sa.assertTrue(OrderHeader,"Order");
		Log.info("Navigated to order panel in view service page");
		
		//Change Order
		click(application, "Action dropdown", "orderactionbutton");
		click(application, "Change Order", "changeorderlink");
				
		Boolean ChangeOrder1_Header = getwebelement(xml.getlocator("//locators/" + application + "/changeorderheader")).isDisplayed();
		Log.info("Change Order header text is displayed as : " + ChangeOrder1_Header);
		System.out.println("Change Order header text:"+ ChangeOrder1_Header);
		sa.assertTrue(ChangeOrder1_Header,"Change Order");
		Thread.sleep(1000);
		
		click(application, "Select order switch", "changeorder_selectorderswitch");
		click(application, "Order Number", "changeordernumber");
		Thread.sleep(2000);
		EnterTextValue(application, changeorderno, "Order Number", "changeordernumber");
		click(application, "RFI Voice Line Number", "changeordervoicelinenumber");
		Thread.sleep(3000);
		EnterTextValue(application, changevoicelineno, "RFI Voice Line Number", "changeordervoicelinenumber");
		click(application, "Create Order", "createorder_button");
		Thread.sleep(2000);
		compareText(application, "Change Order success msg", "successmsg", "Order successufully changed.");
				
		Boolean OrderHeader1 = getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")).isDisplayed();
		sa.assertTrue(OrderHeader1,"Order");
		Log.info("Navigated to order panel in view service page");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to order panel in view service page");
		
		compareText(application, "Order Number", "ordernumbervalue", changeorderno);
		compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno);
		Log.info("------ Change Order is successful ------");
		}
		
	}

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String Remarks, String email, String phonecontact, String country, String defaultemail, String servicefirstname, String servicelastname, String organizationname, String serviceaddress, String servicecomplement, String servicepostalcode, String servicecity, String servicestate, String servicephone, String servicefax, String user, String orderno, String rfireqno) throws InterruptedException, DocumentException, IOException {
		
		
		Boolean Servicepanel_Header = getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed();
		Log.info("Service panel header text is displayed as : " + Servicepanel_Header);
		System.out.println("Service panel header text:"+ Servicepanel_Header);
		sa.assertTrue(Servicepanel_Header,"Service");
		Thread.sleep(1000);
		WebElement cityelement= getwebelement(xml.getlocator("//locators/" + application + "/servicecityvalue"));
		scrolltoview(cityelement);
		compareText(application, "Service Identification", "sidvalue", sid);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype);
		compareText(application, "Email", "emailvalue", email);
		compareText(application, "Phone Contact", "phonecontactvalue", phonecontact);
		compareText(application, "Remarks", "remarksvalue", Remarks);
		compareText(application, "User", "userfieldvalue", user);
		compareText(application, "Default Email", "defaultemailvalue", defaultemail);
		compareText(application, "First Name", "servicefirstnamevalue", servicefirstname);
		compareText(application, "Last Name", "servicelastnamevalue", servicelastname);
		compareText(application, "Organisation Name", "organizationnamevalue", organizationname);
		compareText(application, "Address", "serviceaddressvalue", serviceaddress);
		compareText(application, "Complement", "servicecomplementvalue", servicecomplement);
		compareText(application, "Postal Code", "servicepostalcodevalue", servicepostalcode);
		compareText(application, "City", "servicecityvalue", servicecity);
		compareText(application, "State", "servicestatevalue", servicestate);
		compareText(application, "Country", "servicecountryvalue", country);
		compareText(application, "Phone", "servicephonevalue", servicephone);
		compareText(application, "Fax", "servicefaxvalue", servicefax);
		compareText(application, "Email", "serviceemailvalue", email);
		
	}
	
	public void verifyservicepanel_links(String application, String EditRemarks, String Remarks, String changeorderno, String sid, String editsid, String servicetype, String editemail, String editphonecontact, String edituser, String editdefaultemail, String editservicefirstname, String editservicelastname, String editorganizationname, String editserviceaddress, String editservicecomplement, String editservicepostalcode, String editservicecity, String editservicestate, String editcountry, String editservicephone, String editservicefax ) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		isDisplayed(application, "editservice", "Edit Service Header");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/editservice")));
		cleartext(application, "Service Identification", "serviceidentificationtextfield");
		EnterTextValue(application, editsid, "Service Identification", "serviceidentificationtextfield");
		cleartext(application, "Remarks", "remarktextarea");
		EnterTextValue(application, EditRemarks, "Remarks", "remarktextarea");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/cancelbutton")));
		click(application, "Cancel", "cancelbutton");
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
			compareText(application, "Service Identification", "sidvalue", sid);
			compareText(application, "Remarks", "remarksvalue", Remarks);
		}
		else
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
	
		//Edit service
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
		click(application, "Action dropdown", "serviceactiondropdown");
		click(application, "Edit", "edit");
		isDisplayed(application, "editservice", "Edit Service Header");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/editservice")));
		cleartext(application, "Service Identification", "serviceidentificationtextfield");
		EnterTextValue(application, editsid, "Service Identification", "serviceidentificationtextfield");
		compareText(application, "Service Type", "servicetype_value", servicetype);
		cleartext(application, "Remarks", "remarktextarea");
		EnterTextValue(application, EditRemarks, "Remarks", "remarktextarea");
		//Edit email
		click(application, "Selected Email", "selectedemail");
		click(application, "Email remove arrow", "emailremovearrow");
		cleartext(application, "Email", "emailtextfieldvalue");
		EnterTextValue(application, editemail, "Email", "emailtextfieldvalue");
		click(application, "Email adding arrow", "emailaddarrow");
		//Edit phone contact
		click(application, "Selected phone contact", "selectedphone");
		click(application, "Phonecontact remove arrow", "phoneremovearrow");
		cleartext(application, "Phone Contact", "phonecontacttextfieldvalue");
		EnterTextValue(application, editphonecontact, "Phone Contact", "phonecontacttextfieldvalue");
		click(application, "phonecontact adding Arrow", "phoneaddarrow");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/phonecontacttextfieldvalue")));
		cleartext(application, "User", "userfield");
		EnterTextValue(application, edituser, "User", "userfield");
		cleartext(application, "Password", "Password");
		click(application, "Generate Password", "GeneratePassword");
		cleartext(application, "Default Email", "defaultemail");
		EnterTextValue(application, editdefaultemail, "Default Email", "defaultemail");
		cleartext(application, "First Name", "servicefirstname");
		EnterTextValue(application, editservicefirstname, "First Name", "servicefirstname");
		cleartext(application, "Last Name", "servicelastname");
		EnterTextValue(application, editservicelastname, "Last Name", "servicelastname");
		cleartext(application, "Organization Name", "organizationname");
		EnterTextValue(application, editorganizationname, "Organization Name", "organizationname");
		cleartext(application, "Address", "serviceaddress");
		EnterTextValue(application, editserviceaddress, "Address", "serviceaddress");
		cleartext(application, "Complement", "servicecomplement");
		EnterTextValue(application, editservicecomplement, "Complement", "servicecomplement");
		cleartext(application, "Postal Code", "servicepostalcode");
		EnterTextValue(application, editservicepostalcode, "Postal Code", "servicepostalcode");
		cleartext(application, "City", "servicecity");
		EnterTextValue(application, editservicecity, "City", "servicecity");
		cleartext(application, "State", "servicestate");
		EnterTextValue(application, editservicestate, "State", "servicestate");
		scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/country")));
		addDropdownValues(application, "Country", "country", editcountry);
		cleartext(application, "Phone", "servicephone");
		EnterTextValue(application, editservicephone, "Phone", "servicephone");
		cleartext(application, "Fax", "servicefax");
		EnterTextValue(application, editservicefax, "Fax", "servicefax");
		cleartext(application, "Email", "serviceemail");
		EnterTextValue(application, editemail, "Email", "serviceemail");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		JavascriptExecutor executor =(JavascriptExecutor)driver;
		WebElement Nextbutton= getwebelement(xml.getlocator("//locators/" + application + "/editservice_next"));
		((JavascriptExecutor)driver). executeScript("arguments[0]. click();", Nextbutton);
		//click(application, "Next", "nextbutton");
							
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to view service page");
			compareText(application, "Service updated success msg", "serviceupdate_successmsg", "Service successfully updated.");
			sa.assertAll();
			}
			else
				Log.info("Service not updated");
				System.out.println("Service not updated");
			
			//verify edit service details in view service page
				WebElement cityelement= getwebelement(xml.getlocator("//locators/" + application + "/servicecityvalue"));
				scrolltoview(cityelement);
				compareText(application, "Service Identification", "sidvalue", editsid);
				compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype);
				compareText(application, "Email", "emailvalue", editemail);
				compareText(application, "Phone Contact", "phonecontactvalue", editphonecontact);
				compareText(application, "Remarks", "remarksvalue", EditRemarks);
				compareText(application, "User", "userfieldvalue", edituser);
				compareText(application, "Default Email", "defaultemailvalue", editdefaultemail);
				compareText(application, "First Name", "servicefirstnamevalue", editservicefirstname);
				compareText(application, "Last Name", "servicelastnamevalue", editservicelastname);
				compareText(application, "Organisation Name", "organizationnamevalue", editorganizationname);
				compareText(application, "Address", "serviceaddressvalue", editserviceaddress);
				compareText(application, "Complement", "servicecomplementvalue", editservicecomplement);
				compareText(application, "Postal Code", "servicepostalcodevalue", editservicepostalcode);
				compareText(application, "City", "servicecityvalue", editservicecity);
				compareText(application, "State", "servicestatevalue", editservicestate);
				compareText(application, "Country", "servicecountryvalue", editcountry);
				compareText(application, "Phone", "servicephonevalue", editservicephone);
				compareText(application, "Fax", "servicefaxvalue", editservicefax);
				compareText(application, "Email", "serviceemailvalue", editemail);
				
			//synchronize link in view service page
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
			Thread.sleep(1000);
			click(application, "Action dropdown", "serviceactiondropdown");
			click(application, "Synchronize", "synchronizelink_servicepanel");
			Thread.sleep(2000);
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/Sync_successmsg")));
			compareText(application, "Syncronize success message", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.");

			//Delete Service
			scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/orderpanelheader")));
			click(application, "Action dropdown", "serviceactiondropdown");
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
		scrolltoview(searchbutton);
		Clickon(searchbutton);
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
		Thread.sleep(3000);
	}
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
				  List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside "+ fieldname + "dropdown is:  ");
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
	 
	 public void delete(String application, String xpath, String labelname, String expectedvalue) throws InterruptedException, DocumentException{
		 Thread.sleep(1000);	
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
			Thread.sleep(2000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on delete link");
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click(application, "Delete", "deletebutton");
				scrolltoview(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
				compareText(application, "'"+ labelname +"' delete success message", "'"+ xpath +"'", "'"+expectedvalue+"'");
			}
			else
				Log.info("Delete alert popup is not displayed");
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
	 
	 public void click(String application, String labelname, String xpath) throws InterruptedException, DocumentException {
		 WebElement element= null;
		 		 	 
		 try {
			 Thread.sleep(1000);
			 element = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			 if(element==null)
			 {
				 DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			 }
			 else {
				 element.click();	
				 DriverTestcase.logger.log(LogStatus.PASS, "Step: Clicked on '"+labelname+"' button");
			 }

		 } catch (Exception e) {
			 DriverTestcase.logger.log(LogStatus.FAIL,"Step: Clicking on '"+labelname+"' button is unsuccessful");
			 e.printStackTrace();
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
				 DriverTestcase.logger.log(LogStatus.FAIL, "Step:  '"+labelname+"' not found");
			 }
			 else if (emptyele!=null && emptyele.isEmpty()) {
				 DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+ labelname +"' value is empty");
			 }else 
			 {   
				 text = element.getText();
				 if(text.equals(ExpectedText)) {
					 DriverTestcase.logger.log(LogStatus.PASS,"Step: The ExpectedText '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
				 }
				 else if(text.contains(ExpectedText)) {
					 DriverTestcase.logger.log(LogStatus.PASS,"Step: The ExpectedText '"+ExpectedText+"' is same as the Acutal Text '"+text+"'");
				 }
				 else
				 {
					 DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
				 }
			 }
		 }catch (Exception e) {
			 DriverTestcase.logger.log(LogStatus.FAIL,"Step: The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'");
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
