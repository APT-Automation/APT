package com.saksoft.qa.scripthelpers;

import java.io.File;
import java.io.FileInputStream;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.driverlibrary.Log;
import com.saksoft.qa.driverlibrary.XMLReader;


public class APT_NGINHelper extends DriverHelper {

	public APT_NGINHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/APT_NGIN.xml");

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
		Log.info("Mouse hovered on 'Manage Customers Service' menu item");

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
		compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
		sa.assertAll();
	}


	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected, String customerName1, String customerName2)
			throws InterruptedException, DocumentException, IOException {

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		System.out.println("Mouse hovered on Manage Customer's Service");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
		Log.info("Mouse hovered on 'Manage Customers Service' menu item");

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
		Thread.sleep(1000);
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
				  DriverTestcase.logger.log(LogStatus.PASS, "Service Type dropdown is displaying");
				  System.out.println("Service Type dropdown is displaying");
				  Log.info("Service Type dropdown is displaying");
				  
				  if(servicetype.equalsIgnoreCase("null")) {
					  
					  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under Service Type dropdown");
					  System.out.println("No values selected under Service Type dropdown");
					  Log.info("No values selected under Service Type dropdown");
				  }else {
					  
					  Clickon(getwebelement("//div[label[text()='Service Type']]//div[text()='×']"));
					  Thread.sleep(3000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = getwebelements("//div[@class='sc-ifAKCX oLlzc']");
					  
					  DriverTestcase.logger.log(LogStatus.PASS, "List of values inside Service Type dropdown is:  ");
					  System.out.println( "List of values inside Service Type dropdown is:  ");
					  Log.info("List of values inside Service Type dropdown is:  ");
					  
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
					  Log.info("Service Type dropdown value selected as: "+ actualValue);
					  
				  }
			  }else {
				  DriverTestcase.logger.log(LogStatus.FAIL, "Service Type is not displaying");
				  System.out.println("Service Type is not displaying");
				  Log.info("Service Type is not displaying");
			  }
			}catch(NoSuchElementException e) {
				DriverTestcase.logger.log(LogStatus.FAIL, "Service Type is not displaying");
				  System.out.println("Service Type is not displaying");
				  Log.info("Service Type is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "Not able to perform selection under Service Type dropdown");
				System.out.println("NO value selected under Service Type dropdown");
				Log.info("NO value selected under Service Type dropdown");
			}
		// click on next button
		click_commonMethod(application, "Next", "nextbutton", xml);
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

	}
	

	public void verifyservicecreation(String application, String sid, String Remarks, String customadm,
			String sanadm, String reselladm, String orderno, String rfireqno, String servicetype) throws InterruptedException, IOException, DocumentException {

		//Cancel service creation
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to create order page");
			System.out.println("Navigated to create order page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to create order page");
			Log.info("Navigated to create order page");
		}

		//Create service
		ScrolltoElement(application, "createorderservice_header", xml);
		addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno, xml);
//		//Existing order no select
//				boolean availability=false;
//				try {  
//				  availability=getwebelement(xml.getlocator("//locators/" + application + "/existingorderdropdown")).isDisplayed();
//				  if(availability) {
//					  DriverTestcase.logger.log(LogStatus.PASS, "Order/Contract Number(Parent SID) dropdown is displaying");
//					  System.out.println("Order/Contract Number(Parent SID) dropdown is displaying");
//					  
//					  if(orderno.equalsIgnoreCase("null")) {
//						  
//						  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under Order/Contract Number(Parent SID) dropdown");
//						  System.out.println(" No values selected under Order/Contract Number(Parent SID) dropdown");
//					  }else {
//						  
//						  Clickon(getwebelement("//div[label[text()='Order/Contract Number(Parent SID)']]//div[text()='×']"));
//						  Thread.sleep(3000);
//						  
//						  //verify list of values inside dropdown
//						  List<WebElement> listofvalues = driver
//									.findElements(By.xpath("(//div[@role='list']//div)[3]"));
//						  
//						  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside Order/Contract Number(Parent SID) dropdown is:  ");
//						  System.out.println( " List of values inside Order/Contract Number(Parent SID) dropdown is:  ");
//						  
//							for (WebElement valuetypes : listofvalues) {
//										Log.info("service sub types : " + valuetypes.getText());
//										DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
//										System.out.println(" " + valuetypes.getText());
//							}
//							
//							Thread.sleep(2000);
//						SendKeys(getwebelement("//div[label[text()='Order/Contract Number(Parent SID)']]//input"), orderno);	
//						Thread.sleep(2000);
//							
//						  Clickon(getwebelement("(//div[contains(text(),'"+ orderno +"')])[2]"));
//						  Thread.sleep(3000);
//						  
//						  String actualValue=getwebelement("//label[text()='Order/Contract Number(Parent SID)']/following-sibling::div//div/span").getText();
//						  DriverTestcase.logger.log(LogStatus.PASS, "Order/Contract Number(Parent SID) dropdown value selected as: "+ actualValue );
//						  System.out.println("Order/Contract Number(Parent SID) dropdown value selected as: "+ actualValue);
//						  
//					  }
//				  }else {
//					  DriverTestcase.logger.log(LogStatus.FAIL, "Order/Contract Number(Parent SID) is not displaying");
//					  System.out.println("Order/Contract Number(Parent SID) is not displaying");
//				  }
//				}catch(NoSuchElementException e) {
//					DriverTestcase.logger.log(LogStatus.FAIL, "Order/Contract Number(Parent SID) is not displaying");
//					  System.out.println("Order/Contract Number(Parent SID) is not displaying");
//				}catch(Exception ee) {
//					ee.printStackTrace();
//					DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under Order/Contract Number(Parent SID) dropdown");
//					System.out.println(" NO value selected under Order/Contract Number(Parent SID) dropdown");
//				}
				
		//addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
				boolean availability1=false;
				try {  
				  availability1=getwebelement(xml.getlocator("//locators/" + application + "/servicetypetextfield")).isDisplayed();
				  if(availability1) {
					  DriverTestcase.logger.log(LogStatus.PASS, "Service Type dropdown is displaying");
					  System.out.println("Service Type dropdown is displaying");
					  Log.info("Service Type dropdown is displaying");
					  
					  if(servicetype.equalsIgnoreCase("null")) {
						  
						  DriverTestcase.logger.log(LogStatus.PASS, "No values selected under Service Type dropdown");
						  System.out.println("No values selected under Service Type dropdown");
						  Log.info("No values selected under Service Type dropdown");
					  }else {
						  
						  Clickon(getwebelement("//div[label[text()='Service Type']]//div[text()='×']"));
						  Thread.sleep(3000);
						  
						  //verify list of values inside dropdown
						  List<WebElement> listofvalues = driver
									.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
						  
						  DriverTestcase.logger.log(LogStatus.PASS, "List of values inside Service Type dropdown is:  ");
						  System.out.println("List of values inside Service Type dropdown is:  ");
						  Log.info("List of values inside Service Type dropdown is:  ");
						  
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
						  Log.info("Service Type dropdown value selected as: "+ actualValue);
					  }
				  }else {
					  DriverTestcase.logger.log(LogStatus.FAIL, "Service Type is not displaying");
					  System.out.println("Service Type is not displaying");
					  Log.info("Service Type is not displaying");
				  }
				}catch(NoSuchElementException e) {
					DriverTestcase.logger.log(LogStatus.FAIL, "Service Type is not displaying");
					  System.out.println("Service Type is not displaying");
					  Log.info("Service Type is not displaying");
				}catch(Exception ee) {
					ee.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, "NOt able to perform selection under Service Type dropdown");
					System.out.println("NO value selected under Service Type dropdown");
					Log.info("NO value selected under Service Type dropdown");
				}
				
		// click on next button
		click_commonMethod(application, "Next", "nextbutton", xml);
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
		if (customadm.equalsIgnoreCase("YES")) {
			click_commonMethod(application, "customer administration checkbox", "customeradministrationcheckbox", xml);
		} else {
			System.out.println("customer administration checkbox is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : customer administration checkbox is not selected");
			Log.info("customer administration checkbox is not selected");
		}

		if (customadm.equalsIgnoreCase("NO")) {
			System.out.println("customer administration checkbox is already selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : customer administration checkbox is already selected");
			Log.info("customer administration checkbox is already selected");
		} else {
			click_commonMethod(application, "customer administration checkbox", "customeradministrationcheckbox", xml);
		}

		// management options- SAN Administration Selection
		if (sanadm.equalsIgnoreCase("YES")) {
			click_commonMethod(application, "SAN administration checkbox", "sanadministrationcheckbox", xml);
		} else {
			System.out.println("SAN administration checkbox is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : SAN administration checkbox is not selected");
			Log.info("SAN administration checkbox is not selected");
		}

		if (sanadm.equalsIgnoreCase("NO")) {
			System.out.println("SAN administration checkbox is already selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : SAN administration checkbox is already selected");
			Log.info("SAN administration checkbox is already selected");
		} else {
			click_commonMethod(application, "SAN administration checkbox", "sanadministrationcheckbox", xml);
		}

		// management options- Reseller Administration Selection
		if (reselladm.equalsIgnoreCase("YES")) {
			click_commonMethod(application, "Reseller Administration checkbox", "reselleradministrationcheckbox", xml);
		} else {
			System.out.println("Reseller Administration checkbox is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : Reseller Administration checkbox is not selected");
			Log.info("Reseller Administration checkbox is not selected");
		}

		if (reselladm.equalsIgnoreCase("NO")) {
			System.out.println("Reseller Administration checkbox is already selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : Reseller Administration checkbox is already selected");
			Log.info("Reseller Administration checkbox is already selected");
		} else {
			click_commonMethod(application, "Reseller Administration checkbox", "reselleradministrationcheckbox", xml);
		}

		//click_commonMethod(application, "Next", "nextbutton", xml);
		WebElement Nextbutton= getwebelement(xml.getlocator("//locators/" + application + "/nextbutton"));
		((JavascriptExecutor)driver). executeScript("arguments[0]. click();", Nextbutton);
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
		WebElement UserGridCheck= getwebelement("(//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
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

			scrolltoend();
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
			Log.info("User added successfully");

			//Edit User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers= getwebelements("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']");
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");
			Log.info("No users displayed");
			}
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
				WebElement AddedUser = getwebelement("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");
			Log.info("No users displayed");
			}
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
				WebElement AddedUser = getwebelement("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");
			Log.info("No users displayed");
			}
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
			List<WebElement> ExistingUsers= getwebelements("//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[@role='row']");
			int NoOfUsers = ExistingUsers.size();
			System.out.println("Total users:"+ NoOfUsers);

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");
				Log.info("No users displayed");
			}

			click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(1000);
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
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "okbutton", xml);

			//View User

			if(NoOfUsers==1)
			{
				click_commonMethod(application, "User Radio button", "UserUnchecked", xml);
				Thread.sleep(3000);
			}
			else if(NoOfUsers>1)
			{
				WebElement AddedUser = getwebelement("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");
			Log.info("No users displayed");
			}

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
				WebElement AddedUser = getwebelement("//div[contains(text(),'" + Username + "')]/preceding-sibling::div//span[@class='ag-icon ag-icon-checkbox-unchecked']");
				AddedUser.click();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : No users displayed");
			Log.info("No users displayed");
			}

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
				Log.info("order information is matched");
			} else {
				sa.fail("order information is not matched");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
				Log.info("order information is not matched");
			}

		} else {

			System.out.println("existing order is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : existing order is not selected");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : order information is matched");
				Log.info("order information is matched");
			} else {
				sa.fail("order information is not matched");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : order information is not matched");
				System.out.println("order information is not matched");
				Log.info("order information is not matched");
			}

		} else {

			System.out.println("new order is not selected");
			DriverTestcase.logger.log(LogStatus.INFO, "Step : new order is not selected");
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
			Log.info("Selected order from dropdown");
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

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String Remarks) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "orderpanelheader", xml);
		compareText(application, "Service panel Header", "servicepanel_header", "Service", xml);
		compareText(application, "Service Identification", "servicepanel_serviceidentificationvalue", sid, xml);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype, xml);
		compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks, xml);
	}

	public void verifyservicepanel_links(String application, String EditRemarks, String Remarks, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired, String bulkjob_filepath) throws InterruptedException, DocumentException, IOException {

		//Cancel edit service
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		cleartext(application, "Remarks", "remarktextarea");
		addtextFields_commonMethod(application, "Remarks", "remarktextarea",  EditRemarks, xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		ScrolltoElement(application, "orderpanelheader", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
		{
			compareText(application, "Remarks", "servicepanel_remarksvalue", Remarks, xml);	
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
		Log.info("Didn't navigate to view service page");
		}
		//Edit service
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Edit", "edit", xml);
		cleartext(application, "Remarks", "remarktextarea");
		addtextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);
		ScrolltoElement(application, "orderpanelheader", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			compareText(application, "Service updated success message", "serviceupdate_successmsg", "Service successfully updated", xml);	
		}
		else
		{
			Log.info("Service not updated");
			System.out.println("Service not updated");
		}
		//Manage service
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Manage", "manageLink", xml);
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
						Log.info("Service status change request logged");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request is not logged");
					Log.info("Service status change request is not logged");
					}
				}
				catch(StaleElementReferenceException e)
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "No service history to display");
					Log.info("No service history to display");
				}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Status link is not working");
			Log.info("No service history to display");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change not reqired");
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
		compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.", xml);
		scrolltoend();
		click_commonMethod(application, "Back", "managepage_backbutton", xml);

		//synchronize link in view service page
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Synchronize", "synchronizelink_servicepanel", xml);
		ScrolltoElement(application, "customerdetailsheader", xml);
		compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this service.", xml);

		//Bulk interface

		//cancel bulk interface
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Bulk Interface", "bulkinterfacelink", xml);
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		click_commonMethod(application, "Cancel", "bulkjobcancel", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}

		//submit bulk job
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Bulk Interface", "bulkinterfacelink", xml);
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		WebElement BulkJob_Choosefile_button= getwebelement(xml.getlocator("//locators/" + application + "/bulkjob_choosefilebutton"));
		BulkJob_Choosefile_button.sendKeys(bulkjob_filepath);
		click_commonMethod(application, "Submit", "bulkjobsubmit", xml);
		String bulkjob_successmsg= GetText(application, "BulkJob_msg", "successmsg");
		if(bulkjob_successmsg.contains("success"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step :" + bulkjob_successmsg);
			Log.info(bulkjob_successmsg);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step :" + bulkjob_successmsg);
			Log.info(bulkjob_successmsg);
		}
		
		//Archive link in bulk interface page
		click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
		click_commonMethod(application, "Archive", "bulkinterface_archivelink", xml);
		compareText(application, "Status history found message", "successmsg", "Service Status History Found Successfully.", xml);
		scrolltoend();
		click_commonMethod(application, "Cancel", "bulkinterfacepage_cancel", xml);

		//Refresh link in bulk interface page
		scrollToTop();
		click_commonMethod(application, "Action dropdown", "bulkinterface_actiondropdown", xml);
		click_commonMethod(application, "Archive", "bulkinterface_refreshlink", xml);
		compareText(application, "Bulk Interface Header", "bulkinterfaceheader", "Bulk Interface", xml);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Bulk Interface page refresh successful");
		Log.info("Bulk Interface page refresh successful");
		Thread.sleep(1000);
		scrolltoend();
		click_commonMethod(application, "Cancel", "bulkinterfacepage_cancel", xml);
		Thread.sleep(2000);
		//Service delete is performed in the last test case
	}

	

	public void verifyManagementOptionspanel(String application) throws InterruptedException, DocumentException, IOException {

		Boolean ManagementOptions_Header = getwebelement(xml.getlocator("//locators/" + application + "/managementoptions_header")).isDisplayed();
		Log.info("Management options header text is displayed as : " + ManagementOptions_Header);
		System.out.println("Management options header text:"+ ManagementOptions_Header);
		sa.assertTrue(ManagementOptions_Header,"Management Options");

		// verify customer administration information
		String CustomerAdmin_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/customeradmin_text")));
		GetText(application, CustomerAdmin_Text, "customeradmin_value");
		// verify SAN Administration information
		String SANAdmin_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/sanadmin_text")));		
		GetText(application, SANAdmin_Text, "sanadmin_value");	
		// verify Reseller Administration information
		String ResellerAdmin_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/reselleradmin_text")));
		GetText(application, SANAdmin_Text, "reselleradmin_value");

	}


	public void verifyResellerpanel(String application) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "servicepanel_header", xml);

		isDisplayed(application, "resellerheader", "Reseller Header");
		click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Add link is displaying under Reseller panel");
			Log.info("Add link is displaying under Reseller panel");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Add link is not displaying under Reseller panel");
		Log.info("Add link is not displaying under Reseller panel");
		}
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));

	}

	public static String ResellerName;
	public void AddReseller(String application, String ocn, String email, String city, String streetname, String streetno, String pobox, String zipcode, String phone, String fax) throws InterruptedException, DocumentException, IOException {
		ScrolltoElement(application, "servicepanel_header", xml);
		isDisplayed(application, "resellerheader", "Reseller Header");
	
		// verify customer name column
		compareText(application, "Customer Name", "reseller_customername_column", "Customer Name", xml);
		// verify status column
		GetText(application, "Status", "statuscolumn");
		// verify Add link in Reseller action dropdown
		//Cancel add reseller
		click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		isDisplayed(application, "manageresellerheader", "Manage Reseller in OSP");
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}

		//warning msg verfiy in reseller panel
		ScrolltoElement(application, "servicepanel_header", xml);
		click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		isDisplayed(application, "manageresellerheader", "Manage Reseller in OSP");
		click_commonMethod(application, "Next", "nextbutton", xml);
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Reseller Name is displaying as: '"+resellername+"'");
		Log.info("Reseller Name is displaying as: '"+resellername+"'");
		addtextFields_commonMethod(application, "Email", "reseller_email", email, xml);	
		addtextFields_commonMethod(application, "City", "reseller_city", city, xml);
		addtextFields_commonMethod(application, "Street Name", "reseller_streetname", streetname, xml);
		addtextFields_commonMethod(application, "Street Number", "reseller_streetno", streetno, xml);
		addtextFields_commonMethod(application, "PO Box", "reseller_pobox", pobox, xml);
		addtextFields_commonMethod(application, "Zip Code", "reseller_zipcode", zipcode, xml);
		addtextFields_commonMethod(application, "Phone", "reseller_phone", phone, xml);
		addtextFields_commonMethod(application, "Fax", "reseller_fax", fax, xml);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			scrollToTop();
			compareText(application, "Reseller created success message", "resellercreated_successmsg", "Manage Reseller successfully created", xml);
		}
		else
		{
			Log.info("Reseller not created");
			System.out.println("Reseller not created");
			DriverTestcase.logger.log(LogStatus.FAIL, "Reseller not created");
		}

		//Added Reseller
		ScrolltoElement(application, "servicepanel_header", xml);
		WebElement ResellerGridCheck= getwebelement("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String ResellerGrid= ResellerGridCheck.getAttribute("style");
		WebElement AddedReseller= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + ocn + "')]/parent::div//input[@type='radio']");
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
				DriverTestcase.logger.log(LogStatus.PASS, ""+Link+" link is displaying under reseller panel");
				System.out.println("Reseller link:"+ Link + " is displaying");
				Log.info("Reseller link:"+ Link + " is displaying");
				Thread.sleep(2000);
			}
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/resellerheader")));
			//Added Reseller grid verification
			compareText(application, "Added Reseller Name", "Addedreseller_columnvalue", ocn, xml);

		}
		else
		{
			Log.info("Reseller is not added in the grid");
		System.out.println("Reseller is not added in the grid");
		DriverTestcase.logger.log(LogStatus.FAIL, "Reseller is not added in the grid");
		}
		ResellerName= ocn;		
	}


	public void verifyResellerLinks(String application, String ocn, String editemail, String editcity, String editstreetname, String editstreetno, String editpobox, String editzipcode, String editphone, String editfax) throws InterruptedException, DocumentException, IOException {
		ScrolltoElement(application, "servicepanel_header", xml);
		WebElement ResellerGridCheck= getwebelement("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String ResellerGrid= ResellerGridCheck.getAttribute("style");
		WebElement AddedReseller= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ ocn +"')]/parent::div//input[@type='radio']");

		//Edit Reseller
		if(!ResellerGrid.contains("height: 1px"))
		{
			Clickon(AddedReseller);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
			click_commonMethod(application, "Edit", "edit", xml);
			GetText(application, "Manage Reseller Header", "manageresellerheader");
			cleartext(application, "Email", "reseller_email");
			addtextFields_commonMethod(application, "Email", "reseller_email", editemail, xml);
			cleartext(application, "City", "reseller_city");
			addtextFields_commonMethod(application, "City", "reseller_city", editcity, xml);
			cleartext(application, "Street Name", "reseller_streetname");
			addtextFields_commonMethod(application, "Street Name", "reseller_streetname", editstreetname, xml);
			cleartext(application, "Street Number", "reseller_streetno");
			addtextFields_commonMethod(application, "Street Number", "reseller_streetno", editstreetno, xml);
			cleartext(application, "PO Box", "reseller_pobox");
			addtextFields_commonMethod(application, "PO Box", "reseller_pobox", editpobox, xml);
			cleartext(application, "Zip Code", "reseller_zipcode");
			addtextFields_commonMethod(application, "Zip Code", "reseller_zipcode", editzipcode, xml);
			cleartext(application, "Phone", "reseller_phone");
			addtextFields_commonMethod(application, "Phone", "reseller_phone", editphone, xml);
			cleartext(application, "Fax", "reseller_fax");
			addtextFields_commonMethod(application, "Fax", "reseller_fax", editfax, xml);
			scrolltoend();
			click_commonMethod(application, "OK", "okbutton", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
				System.out.println("Navigated to view service page");
				ScrolltoElement(application, "editreseller_successmsg", xml);
				compareText(application, "Edit Reseller success message", "editreseller_successmsg", "Reseller already in OSP. Successfully updated.", xml);
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
			System.out.println("Reseller is not added in the grid");
			DriverTestcase.logger.log(LogStatus.FAIL, "Reseller is not added in the grid");
			
		}

		//View reseller
		ScrolltoElement(application, "servicepanel_header", xml);
		WebElement AddedReseller1= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ ocn +"')]/parent::div//input[@type='radio']");
		if(!ResellerGrid.contains("height: 1px"))
		{
			Clickon(AddedReseller1);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);

			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				click_commonMethod(application, "View", "view", xml);
				scrollToTop();
				//compareText(application, "Manage Reseller Header", "manageresellerheader_viewpage", "Manage Reseller In OSP", xml);
				GetText(application, "Manage Reseller Header", "manageresellerheader_viewpage");
				compareText(application, "Reseller Name", "resellernamevalue", ocn, xml);
				compareText(application, "Email", "reselleremailvalue", editemail, xml);
				compareText(application, "City", "resellercityvalue", editcity, xml);
				compareText(application, "Street Name", "resellerstreetnamevalue", editstreetname, xml);
				compareText(application, "Street Number", "resellerstreetnovalue", editstreetno, xml);
				compareText(application, "PO Box", "resellerpoboxvalue", editpobox, xml);
				compareText(application, "Zipcode", "resellerzipcodevalue", editzipcode, xml);
				compareText(application, "Phone", "resellerphonevalue", editphone, xml);
				compareText(application, "Fax", "resellerfaxvalue", editfax, xml);
				GetText(application, "Web Access Authorized", "resellerwebaccessvalue");
				Thread.sleep(1000);
				scrollToTop();
				Thread.sleep(2000);
				click_commonMethod(application, "View page Action dropdown", "viewpage_actiondropdown", xml);

				//Edit link in view reseller page
				click_commonMethod(application, "View page Edit", "edit", xml);
				//compareText(application, "Manage Reseller Header", "manageresellerheader", "Manage Reseller In OSP", xml);
				GetText(application, "Manage Reseller Header", "manageresellerheader");
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Edit Reseller page is displaying as expected");
				Log.info("Edit Reseller page is displaying as expected");
				scrolltoend();
				click_commonMethod(application, "Cancel", "cancelbutton", xml);

				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
				}
			}
			else
			{
				Log.info("Reseller is not added in the grid");
				System.out.println("Reseller is not added in the grid");
				DriverTestcase.logger.log(LogStatus.FAIL, "Reseller is not added in the grid");
			}
			ScrolltoElement(application, "servicepanel_header", xml);
			WebElement AddedReseller2= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ ocn +"')]/parent::div//input[@type='radio']");
			if(!ResellerGrid.contains("height: 1px"))
			{
				Clickon(AddedReseller2);
				Thread.sleep(1000);
				click_commonMethod(application, "Action dropdown", "ResellerActionDropdown", xml);
				if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
				{
					click_commonMethod(application, "View", "view", xml);
					GetText(application, "Manage Reseller Header", "manageresellerheader_viewpage");
					scrollToTop();
					click_commonMethod(application, "View page Action dropdown", "viewpage_actiondropdown", xml);

					//Delete link in view reseller page
					click_commonMethod(application, "View page Delete", "delete", xml);

					if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete Reseller alert is displaying as expected");
						Log.info("Delete Reseller alert is displaying as expected");
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletealertclose")));
						Thread.sleep(2000);
						DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on delete alert popup close button");
						Log.info("clicked on delete alert popup close button");
					}
					else
					{
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete alert popup is not displayed");
						Log.info("Delete alert popup is not displayed");
					}
				}

				//Associate Reseller with NGIN Objects link in view reseller page
				click_commonMethod(application, "View page Action dropdown", "viewpage_actiondropdown", xml);
				click_commonMethod(application, "Associate Reseller with NGIN Objects", "associateresellerlink", xml);
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Associate Reseller with NGIN Objects link verified");
				}

			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : View link is not displaying under Reseller panel");
			Log.info("View link is not displaying under Reseller panel");
			}
		}
		else
		{
			Log.info("Reseller is not added in the grid");
			System.out.println("Reseller is not added in the grid");
			DriverTestcase.logger.log(LogStatus.FAIL, "Reseller is not added in the grid");
		}
	}

	public void verifyCustomerpanel(String application) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "resellerheader", xml);
		compareText(application, "Customer panel header", "customerheader", "Customer", xml);
		click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddLink")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Add link is displaying under Customer panel");
			Log.info("Add link is displaying under Customer panel");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Add link is not displaying under Customer panel");
			Log.info("Add link is not displaying under Customer panel");
		}
		
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/customerheader")));
	}

	String Customername=null;
	public void AddCustomer(String application, String resellername, String defaultvalue, String configure, String country, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "resellerheader", xml);
		compareText(application, "Customer panel header", "customerheader", "Customer", xml);

		// verify customer name column
		compareText(application, "Customer Name column", "customerpanel_customernamecolumntext", "Customer Name", xml);

		// verify Add link in customer action dropdown
		//Cancel add customer
		click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		Thread.sleep(2000);
		scrollToTop();
		compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}

		//Warning msgs verify in Add customer page
		ScrolltoElement(application, "resellerheader", xml);
		click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);
		click_commonMethod(application, "Add", "AddLink", xml);
		Thread.sleep(2000);
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
		Thread.sleep(1000);
		scrollToTop();
		addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
		String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
		Log.info("OCN is displaying as: '"+CustomerOCN+"'");
		String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Default checkbox is checked");
				Log.info("Default checkbox is checked");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Default checkbox is not checked by default");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Configure checkbox is checked");
				Log.info("Configure checkbox is checked");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Configure checkbox is not checked");
				Log.info("Configure checkbox is not checked");
				click_commonMethod(application, "Configure checkbox", "configurecheckbox", xml);
			}
		}
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(3000);	

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			compareText(application, "Customer created success message", "addcustomer_successmsg", "Manage Customer successfully created", xml);
		}
		else
		{
			Log.info("Customer not created");
			System.out.println("Customer not created");
		}

		//Added Customer
		ScrolltoElement(application, "resellerheader", xml);
		WebElement CustomerGridCheck= getwebelement("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String CustomerGrid= CustomerGridCheck.getAttribute("style");

		WebElement AddedCustomer= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + CustomerName_viewpage + "')]/parent::div//input[@type='radio']");
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
				DriverTestcase.logger.log(LogStatus.PASS, ""+Link+" link is displaying under customer panel");
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


	public void verifyCustomerLinks(String application, String editreseller, String editemail, String editcity, String editstreetname, String editstreetno, String editpobox, String editzipcode, String editphone, String editfax) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "resellerheader", xml);
		WebElement CustomerGridCheck= getwebelement("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String CustomerGrid= CustomerGridCheck.getAttribute("style");

		WebElement AddedCustomer= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ Customername+"')]/parent::div//input[@type='radio']");

		//Edit Customer
		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Edit", "edit", xml);
			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			scrollToTop();
			compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
			Thread.sleep(1000);

			String CountryDisabled= getwebelement(xml.getlocator("//locators/" + application + "/customercountry_disabled")).getAttribute("disabled");
			if(CountryDisabled!=null)
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Country dropdown is disabled as expected");
				Log.info("Country dropdown is disabled as expected");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Country dropdown is enabled");
				Log.info("Country dropdown is enabled");
			}
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
			Log.info("OCN is displaying as: '"+CustomerOCN+"'");

			String CustomernameDisabled= getwebelement(xml.getlocator("//locators/" + application + "/customername_disabled")).getAttribute("disabled");
			if(CustomernameDisabled!=null)
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer name dropdown is disabled as expected");
				Log.info("Customer name dropdown is disabled as expected");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Customer name dropdown is enabled");
				Log.info("Customer name dropdown is enabled");
			}
			addtextFields_commonMethod(application, "Reseller name", "resellername", editreseller, xml);
			cleartext(application, "Email", "reseller_email");
			addtextFields_commonMethod(application, "Email", "reseller_email", editemail, xml);
			cleartext(application, "City", "reseller_city");
			addtextFields_commonMethod(application, "City", "reseller_city", editcity, xml);
			cleartext(application, "Street Name", "reseller_streetname");
			addtextFields_commonMethod(application, "Street Name", "reseller_streetname", editstreetname, xml);
			cleartext(application, "Street Number", "reseller_streetno");
			addtextFields_commonMethod(application, "Street Number", "reseller_streetno", editstreetno, xml);
			cleartext(application, "PO Box", "reseller_pobox");
			addtextFields_commonMethod(application, "PO Box", "reseller_pobox", editpobox, xml);
			cleartext(application, "Zip Code", "reseller_zipcode");
			addtextFields_commonMethod(application, "Zip Code", "reseller_zipcode", editzipcode, xml);
			cleartext(application, "Phone", "reseller_phone");
			addtextFields_commonMethod(application, "Phone", "reseller_phone", editphone, xml);
			cleartext(application, "Fax", "reseller_fax");
			addtextFields_commonMethod(application, "Fax", "reseller_fax", editfax, xml);
			scrolltoend();
			click_commonMethod(application, "Ok", "okbutton", xml);

			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
				System.out.println("Navigated to view service page");
				compareText(application, "Customer updated success message", "editcustomer_successmsg", "Manage Customer successfully updated", xml);
			}
			else
			{
				Log.info("Customer not updated");
				System.out.println("Customer not updated");
			}

		}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing customer displaying under customer panel");
				Log.info("No existing customer displaying under customer panel");
			}

		//View customer
		ScrolltoElement(application, "resellerheader", xml);
		WebElement AddedCustomer1= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ Customername +"')]/parent::div//input[@type='radio']");

		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer1);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);

			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				Thread.sleep(1000);
				click_commonMethod(application, "View", "view", xml);
				Thread.sleep(2000);
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
				compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Edit Customer in view customer page is verified");
				Log.info("Edit Customer in view customer page is verified");
				Thread.sleep(1000);
				scrolltoend();
				click_commonMethod(application, "Cancel", "cancelbutton", xml);
				Thread.sleep(1000);
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing customer displaying under customer panel");
			Log.info("No existing customer displaying under customer panel");
		}
		
		//Delete customer in view customer page
		ScrolltoElement(application, "resellerheader", xml);
		WebElement AddedCustomer2= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+ Customername +"')]/parent::div//input[@type='radio']");

		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer2);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "CustomerpanelActionDropdown", xml);

			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				click_commonMethod(application, "View", "view", xml);
				scrollToTop();
				compareText(application, "Manage Customer in OSP header", "manageresellerheader_viewpage", "Manage Customer In OSP", xml);
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete Customer in view customer page is verified");
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
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
				}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : View link is not displaying under customer panel");
				Log.info("View link is not displaying under customer panel");
			}
			scrolltoend();
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing customer displaying under customer panel");
			Log.info("No existing customer displaying under customer panel");
		}

	}

	public void verifySANpanel(String application) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);		

		if(getwebelement(xml.getlocator("//locators/" + application + "/addsan_link")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Add SAN link is displaying under SAN/FRC panel");
			Log.info("Add link is displaying under SAN/FRC panel");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Add link is not displaying under SAN/FRC panel");
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
		compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);	
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}

		//Add SAN
		ScrolltoElement(application, "customerheader", xml);
		click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);	
		click_commonMethod(application, "Add SAN", "addsan_link", xml);
		scrollToTop();
		Thread.sleep(2000);
		compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);
		addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
		click_commonMethod(application, "Customer Name dropdown", "customername", xml);
		Thread.sleep(1000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_valuesdisplay")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Existing customer is available");
			Log.info("Existing customer is available");
			click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
		}
		else 
		{
			//if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_nomatchesfound")).isDisplayed())
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Existing customer is not available");
			Log.info("Existing customer is not available");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")));

			//Manage new customer link
			click_commonMethod(application, "Manage new customer", "managenewcustomer_link", xml);
			compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
			Thread.sleep(1000);

			//=========================

			//Add Customer
			//Select Country from dropdown
			addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
			Log.info("OCN is displaying as: '"+CustomerOCN+"'");
			String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
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
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "Next", "nextbutton", xml);
			Thread.sleep(3000);	

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
		DriverTestcase.logger.log(LogStatus.PASS, "Step :Customer name selected is : " + SAN_Customernamevalue);
		Log.info("Customer name selected is : " + SAN_Customernamevalue);
		String SAN_Customername=SAN_Customernamevalue.substring(0, SAN_Customernamevalue.indexOf("(")).trim();
		writetoexcel("src\\com\\saksoft\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 133, SAN_Customername);

		String SANNumber_CountryCode=getwebelement(xml.getlocator("//locators/" + application + "/san_number")).getAttribute("value");
		addtextFields_commonMethod(application, "SAN Number", "san_number", sannumber, xml);
		//SAN number
		String SANNumberValue= SANNumber_CountryCode+sannumber;
		DriverTestcase.logger.log(LogStatus.PASS, "Step :SAN Number is dsplayed as : " + SANNumberValue);
		Log.info("SAN Number is dsplayed as : " + SANNumberValue);
		writetoexcel("src\\com\\saksoft\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 134, SANNumberValue);

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
		addDropdownValues_commonMethod(application, "Charge Band Name", "chargebandname", chargebandname, xml);
		addCheckbox_commonMethod(application, "payphoneblockingenabled", "Pay phone blocking enabled", payphoneblocking_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "webaccessblocked", "webAccessBlocked", webaccessblocked_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "sanblock", "SAN Block", sanblock_checkbox, "no", xml);
		addCheckbox_commonMethod(application, "focenabled", "FOC Enabled", focenabled_checkbox, "no", xml);
		addtextFields_commonMethod(application, "Pre Destination Number", "predestinationnumber", predestinationnumber, xml);
		String CPSFreeFormatValue= getwebelement(xml.getlocator("//locators/" + application + "/cpsfreeformat")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : CPS Free Format field value is displayed as:"+ CPSFreeFormatValue);
		String CPSSANEncodedValue= getwebelement(xml.getlocator("//locators/" + application + "/cpssanencoded")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : 'CPS: SAN encoded in modulo80' field value is displayed as:"+ CPSSANEncodedValue);
		scrolltoend();
		if(RingToNumber_Checkbox.equalsIgnoreCase("YES"))
		{
			addCheckbox_commonMethod(application, "ringtonumber_radiobutton", "'Ring To Number' radio", RingToNumber_Checkbox, "no", xml);
			//click_commonMethod(application, "'Ring To Number' radio", "ringtonumber_radiobutton", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "Ring To Number", "ringtonumber_field", ringtonumber, xml);
		}
		else if(AnnouncementToPlay_Checkbox.equalsIgnoreCase("YES"))
		{
			addCheckbox_commonMethod(application, "announcementtoplay_radiobutton", "'Announcement to play' radio", AnnouncementToPlay_Checkbox, "no", xml);
			//click_commonMethod(application, "'Announcement to play' radio", "announcementtoplay_radiobutton", xml);
			Thread.sleep(1000);
			//Select announcement to play value from dropdown
			click_commonMethod(application, "Announcement To Play dropdown", "announcementtoplay_dropdown", xml);
			click_commonMethod(application, "Announcement To Play value", "announcementtoplay_dropdownvalue", xml);
		}
		else if(ComplexRouting_Checkbox.equalsIgnoreCase("YES"))
		{
			addCheckbox_commonMethod(application, "complexroute_radiobutton", "'Complex route' radio", ComplexRouting_Checkbox, "no", xml);
			//click_commonMethod(application, "'Complex route' radio", "complexroute_radiobutton", xml);
			Thread.sleep(1000);
			addtextFields_commonMethod(application, "Routing for payphone", "routingforpayphone_field", routingforpayphone_value, xml);
			addtextFields_commonMethod(application, "Routing for mobile", "routingformobile", routingformobile_value, xml);
			addtextFields_commonMethod(application, "Default Routing", "defaultrouting", defaultrouting_value, xml);
			addCheckbox_commonMethod(application, "enablelogicalrouting", "Enable logical routing checkbox", enablelogicalrouting_Checkbox, "no", xml);
			//click_commonMethod(application, "Enable logical routing checkbox", "enablelogicalrouting", xml);
			addtextFields_commonMethod(application, "Default Route busy", "defaultroutebusy", defaultroutebusy_value, xml);
			addtextFields_commonMethod(application, "No Answer", "noanswer", noanswer_value, xml);
			addtextFields_commonMethod(application, "Network Congestion", "networkcongestion", networkcongestion, xml);
		}
		addCheckbox_commonMethod(application, "enablepriceannouncement", "Enable Price Announcement", enablepriceannouncement_checkbox, "no", xml);
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Add", "addsan_addbutton", xml);
		Thread.sleep(2000);
		scrollToTop();
		if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
		{
			Log.info("Navigated to view SAN page");
			System.out.println("Navigated to view SAN page");
			compareText(application, "Add SAN success message", "addsan_successmsg", "SAN successfully created", xml);
			scrolltoend();
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		}
		else
		{
			Log.info("SAN not created");
			System.out.println("SAN not created");
		}

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}
		else
		{
			Log.info("Not navigated to view service page");
			System.out.println("Not navigated to view service page");
		}
		//Added Customer
		scrolltoend();
		WebElement SANGridCheck= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid= SANGridCheck.getAttribute("style");
		WebElement AddedSAN= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + SAN_Customername + "')]/parent::div//input[@type='radio']");
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
				DriverTestcase.logger.log(LogStatus.PASS, ""+Link+" link is displaying under SAN/FRC panel");
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
			DriverTestcase.logger.log(LogStatus.PASS, "FRC Number for Added SAN is displayed as : " + AddedSAN_FRCNumber);

			WebElement AddedSan1= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + SAN_Customername + "')]");
			String AddedSAN_Customervalue = AddedSan1.getText();
			Log.info("Customer Name for Added SAN is displayed as : " + AddedSAN_Customervalue);
			System.out.println("Customer Name for Added SAN: "+ AddedSAN_Customervalue);
			sa.assertEquals(AddedSAN_Customervalue,SAN_Customername);
			DriverTestcase.logger.log(LogStatus.PASS, "Customer Name for Added SAN is displayed as : " + AddedSAN_Customervalue);
		}

		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}

	}


	public void verifySANLinks(String application, String customernamevalue, String sannumbervalue, String portinnumber, String portoutnumber, String edit_serviceprofilevalue, String supervisionfieldvalue, String edit_supervisionfieldvalue, String maxcallduration, String chargebandname, String predestinationnumber, String ringtonumber, String announcementtoplay_value, String routingforpayphone_value, String routingformobile_value, String defaultrouting_value, String RingToNumber_Checkbox, String AnnouncementToPlay_Checkbox, String ComplexRouting_Checkbox, String defaultroutebusy_value, String noanswer_value, String networkcongestion, String priceannouncementvalue, String priceannoriginvalue, String internationaloutgoingcalls_checkbox, String internationalincomingcalls_checkbox, String mobilecallsallowed_checkbox, String payphoneblocking_checkbox, String noreplytimervalue, String webaccessblocked_checkbox, String cpsfreeformatvalue, String sanblock_checkbox, String focenabled_checkbox, String enablepriceannouncement_checkbox, String select_sansearchtype, String interruptiblepriceannouncement_checkbox, String valueinprice, String sendfci_checkbox, String sendsci_checkbox, String enablecallerconfirmation_checkbox, String callerconfirmationannouncementvalue, String callerconfirmationdigitvalue, String numberofrepetitionsallowedvalue, String edit_interruptiblepriceannouncement_checkbox, String edit_enablepriceannouncement_checkbox, String edit_valueinprice, String edit_sendfci_checkbox, String edit_sendsci_checkbox, String edit_enablecallerconfirmation_checkbox, String edit_callerconfirmationannouncementvalue, String edit_callerconfirmationdigitvalue, String edit_numberofrepetitionsallowedvalue, String edit_chargebandname, String edit_priceannouncementvalue, String edit_priceannoriginvalue, String edit_internationaloutgoingcalls_checkbox, String edit_internationalincomingcalls_checkbox, String edit_mobilecallsallowed_checkbox, String edit_noreplytimervalue, String edit_maxcallduration, String edit_payphoneblocking_checkbox, String edit_webaccessblocked_checkbox, String edit_sanblock_checkbox, String edit_focenabled_checkbox, String edit_ringtonumber_checkbox, String edit_announcementtoplay_checkbox, String edit_complexroute_checkbox, String edit_predestinationnumber) throws InterruptedException, DocumentException, IOException {
		
		//Edit SAN link in View SAN page
		ScrolltoElement(application, "customerheader", xml);
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
		WebElement AddedSAN= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + customernamevalue + "')]/parent::div//input[@type='radio']");
		
		if(!SANGrid.contains("height: 1px"))
		{
			Clickon(AddedSAN);
			Thread.sleep(1000);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "Edit SAN", "editsan_link", xml);
			Thread.sleep(2000);
			scrollToTop();
			compareText(application, "Edit SAN Header", "editsan_header", "Edit SAN", xml);
			
			//verify Customer Name field
			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_customername")).getAttribute("readonly")!=null)
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name field is disabled as expected");
				Log.info("Customer Name field is disabled as expected");
				String CustomerNamevalue= getwebelement(xml.getlocator("//locators/" + application + "/editsan_customername")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name field value is displayed as:"+ CustomerNamevalue);
				Log.info("Customer Name field value is displayed as:"+ CustomerNamevalue);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Customer Name field is enabled");
				Log.info("Customer Name field is enabled");
			}
			
			//verify SAN Number field
			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_sannumber")).getAttribute("readonly")!=null)
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Number field is disabled as expected");
				Log.info("SAN Number field is disabled as expected");
				String SANNumbervalue= getwebelement(xml.getlocator("//locators/" + application + "/editsan_sannumber")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Number field value is displayed as:"+ SANNumbervalue);
				Log.info("SAN Number field value is displayed as:"+ SANNumbervalue);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : SAN Number field is enabled");
				Log.info("SAN Number field is enabled");
			}
			
			//Select service profile from dropdown
			addDropdownValues_commonMethod(application, "Service Profile", "serviceprofile", edit_serviceprofilevalue, xml);
			Thread.sleep(1000);
			addDropdownValues_commonMethod(application, "Supervision mode", "supervisionfield", edit_supervisionfieldvalue, xml);
			//GetText(application, "Supervision mode", "supervisionfield");
			
			if(internationaloutgoingcalls_checkbox.equalsIgnoreCase("Yes"))
			{
				editcheckbox_commonMethod(application, edit_internationaloutgoingcalls_checkbox, "internationaloutgoingforbidden_checkbox", "International Outgoing Calls Forbidden", xml);
				Thread.sleep(1000);
			}
			else
			{
				editcheckbox_commonMethod(application, edit_internationaloutgoingcalls_checkbox, "internationaloutgoingforbidden_checkbox", "International Outgoing Calls Forbidden", xml);
			}
			if(internationalincomingcalls_checkbox.equalsIgnoreCase("Yes"))
			{
				editcheckbox_commonMethod(application, edit_internationalincomingcalls_checkbox, "internationalincomingbarring", "International Incoming Calls Barring", xml);
				//click_commonMethod(application, "International Incoming Calls Barring checkbox", "internationalincomingbarring", xml);
				Thread.sleep(1000);
			}
			else
			{
				editcheckbox_commonMethod(application, edit_internationalincomingcalls_checkbox, "internationalincomingbarring", "International Incoming Calls Barring", xml);
			}
			if(mobilecallsallowed_checkbox.equalsIgnoreCase("Yes"))
			{
				editcheckbox_commonMethod(application, edit_mobilecallsallowed_checkbox, "mobilecallsallowed_checkbox", "Mobile Calls Allowed", xml);
				//click_commonMethod(application, "Mobile Calls Allowed checkbox", "mobilecallsallowed_checkbox", xml);	
				Thread.sleep(1000);
			}
			else
			{
				editcheckbox_commonMethod(application, edit_mobilecallsallowed_checkbox, "mobilecallsallowed_checkbox", "Mobile Calls Allowed", xml);
			}
			if(!edit_noreplytimervalue.equalsIgnoreCase("null"))
			{
			cleartext(application, "No reply timer value", "noreplytimervalue");
			addtextFields_commonMethod(application, "No reply timer value", "noreplytimervalue", edit_noreplytimervalue, xml);
			}
			else
			{
				addtextFields_commonMethod(application, "No reply timer value", "noreplytimervalue", noreplytimervalue, xml);
			}
			if(!edit_maxcallduration.equalsIgnoreCase("null"))
			{
			cleartext(application, "Max call duration", "maxcallduration");
			addtextFields_commonMethod(application, "Max call duration", "maxcallduration", edit_maxcallduration, xml);
			}
			else
			{
				addtextFields_commonMethod(application, "Max call duration", "maxcallduration", maxcallduration, xml);
			}
			//Select charge band name from dropdown
			addDropdownValues_commonMethod(application, "Charge Band Name", "chargebandname", edit_chargebandname, xml);
			
			if(payphoneblocking_checkbox.equalsIgnoreCase("Yes"))
			{
				editcheckbox_commonMethod(application, edit_payphoneblocking_checkbox, "payphoneblockingenabled", "Pay phone blocking enabled", xml);
				//click_commonMethod(application, "Pay phone blocking enabled checkbox", "payphoneblockingenabled", xml);
				Thread.sleep(1000);
			}
			else
			{
				editcheckbox_commonMethod(application, edit_payphoneblocking_checkbox, "payphoneblockingenabled", "Pay phone blocking enabled", xml);
			}
			if(webaccessblocked_checkbox.equalsIgnoreCase("Yes"))
			{
				editcheckbox_commonMethod(application, edit_webaccessblocked_checkbox, "webaccessblocked", "Web Access Blocked", xml);
				//click_commonMethod(application, "Web Access Blocked checkbox", "webaccessblocked", xml);
				Thread.sleep(1000);
			}
			else
			{
				editcheckbox_commonMethod(application, edit_webaccessblocked_checkbox, "webaccessblocked", "Web Access Blocked", xml);
			}
			ScrolltoElement(application, "sanblock", xml);
			
			if(sanblock_checkbox.equalsIgnoreCase("Yes"))
			{
				editcheckbox_commonMethod(application, edit_sanblock_checkbox, "sanblock", "SAN Block", xml);
				//click_commonMethod(application, "Sanblock checkbox", "sanblock", xml);
				Thread.sleep(1000);
			}
			else
			{
				editcheckbox_commonMethod(application, edit_sanblock_checkbox, "sanblock", "SAN Block", xml);
			}
			if(focenabled_checkbox.equalsIgnoreCase("Yes"))
			{
				editcheckbox_commonMethod(application, edit_focenabled_checkbox, "focenabled", "FOC Enabled", xml);
				//click_commonMethod(application, "Foc enabled checkbox", "focenabled", xml);
				Thread.sleep(1000);
			}
			else
			{
				editcheckbox_commonMethod(application, edit_focenabled_checkbox, "focenabled", "FOC Enabled", xml);
			}
			
			cleartext(application, "Pre destination number", "predestinationnumber");
			addtextFields_commonMethod(application, "Pre destination number", "predestinationnumber", predestinationnumber, xml);
			String CPSFreeFormatValue= getwebelement(xml.getlocator("//locators/" + application + "/cpsfreeformat")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : CPS Free Format field value is displayed as:"+ CPSFreeFormatValue);
			String CPSSANEncodedValue= getwebelement(xml.getlocator("//locators/" + application + "/cpssanencoded")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : 'CPS: SAN encoded in modulo80' field value is displayed as:"+ CPSSANEncodedValue);
			
			if(RingToNumber_Checkbox.equalsIgnoreCase("YES"))
			{
				editcheckbox_commonMethod(application, edit_ringtonumber_checkbox, "ringtonumber_radiobutton", "'Ring To Number' radio", xml);
				//Checkbox_commonMethod(application, "ringtonumber_radiobutton", "'Ring To Number' radio", edit_ringtonNumber_checkbox, "no", xml);
				click_commonMethod(application, "'Ring To Number' radio", "ringtonumber_radiobutton", xml);
				Thread.sleep(1000);
				cleartext(application, "Ring To Number", "ringtonumber_field");
				addtextFields_commonMethod(application, "Ring To Number", "ringtonumber_field", ringtonumber, xml);
			}
			else if(AnnouncementToPlay_Checkbox.equalsIgnoreCase("YES"))
			{
				editcheckbox_commonMethod(application, edit_announcementtoplay_checkbox, "announcementtoplay_radiobutton", "'Announcement to play' radio", xml);
				//click_commonMethod(application, "'Announcement to play' radio", "announcementtoplay_radiobutton", xml);
				Thread.sleep(1000);
				//Select announcement to play value from dropdown
				click_commonMethod(application, "Announcement To Play dropdown", "announcementtoplay_dropdown", xml);
				click_commonMethod(application, "Announcement To Play value", "announcementtoplay_dropdownvalue", xml);
			}
			else if(ComplexRouting_Checkbox.equalsIgnoreCase("YES"))
			{
				editcheckbox_commonMethod(application, edit_complexroute_checkbox, "complexroute_radiobutton", "'Complex route' radio", xml);
				//click_commonMethod(application, "'Complex route' radio", "complexroute_radiobutton", xml);
				Thread.sleep(1000);
				cleartext(application, "Routing for payphone", "routingforpayphone_field");
				addtextFields_commonMethod(application, "Routing for payphone", "routingforpayphone_field", routingforpayphone_value, xml);
				cleartext(application, "Routing for mobile", "routingformobile");
				addtextFields_commonMethod(application, "Routing for mobile", "routingformobile", routingformobile_value, xml);
				cleartext(application, "Default Routing", "defaultrouting");
				addtextFields_commonMethod(application, "Default Routing", "defaultrouting", defaultrouting_value, xml);
				click_commonMethod(application, "Enable logical routing checkbox", "enablelogicalrouting", xml);
				cleartext(application, "Default Route busy", "defaultroutebusy");
				addtextFields_commonMethod(application, "Default Route busy", "defaultroutebusy", defaultroutebusy_value, xml);
				cleartext(application, "No Answer", "noanswer");
				addtextFields_commonMethod(application, "No Answer", "noanswer", noanswer_value, xml);
				cleartext(application, "Network Congestion", "networkcongestion");
				addtextFields_commonMethod(application, "Network Congestion", "networkcongestion", networkcongestion, xml);
			}
			
			if(enablepriceannouncement_checkbox.equalsIgnoreCase("Yes"))
			{
				editcheckbox_commonMethod(application, edit_enablepriceannouncement_checkbox, "enablepriceannouncement", "Enable Price Announcement", xml);
				//click_commonMethod(application, "Enable Price Announcement checkbox", "enablepriceannouncement", xml);
				Thread.sleep(1000);
			}
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "Add", "addsan_addbutton", xml);
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
			{
				Log.info("Navigated to view SAN page");
				System.out.println("Navigated to view SAN page");
				ScrolltoElement(application, "editsan_successmsg", xml);
				compareText(application, "San Updated Success message", "editsan_successmsg", "San successfully updated", xml);
				Thread.sleep(3000);
				scrolltoend();
				click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
					Log.info("Didn't navigate to view service page");
				}
				
			}
			else
			{
				Log.info("SAN not updated");
				System.out.println("SAN not updated");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: SAN not updated");
			}
		
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}


		//======================================
		
		//View SAN
		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();
		WebElement SANGridCheck1= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid1= SANGridCheck1.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN1= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
		if(!SANGrid1.contains("height: 1px"))
		{
			Clickon(AddedSAN1);
			Thread.sleep(1000);

			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "View SAN link", "viewsan_link", xml);
			Thread.sleep(2000);
			scrollToTop();
			compareText(application, "View SAN Header", "viewsan_header", "View SAN", xml);
			compareText(application, "Customer Name", "viewsan_customername", customernamevalue, xml);
			compareText(application, "SAN/FRC Number", "viewsan_sannumber", sannumbervalue, xml);
			if(!edit_serviceprofilevalue.equalsIgnoreCase("null"))
			{
				compareText(application, "Service Profile", "serviceprofilevalue", edit_serviceprofilevalue, xml);
			}
			else
			{
				compareText(application, "Service Profile", "serviceprofilevalue", edit_serviceprofilevalue, xml);
			}
			if(!edit_supervisionfieldvalue.equalsIgnoreCase("null"))
			{
				compareText(application, "Supervision Mode", "supervisionmodevalue", edit_supervisionfieldvalue, xml);
			}
			else
			{
				compareText(application, "Supervision Mode", "supervisionmodevalue", supervisionfieldvalue, xml);
			}
			GetText(application, "International Outgoing Calls Forbidden", "internationaloutgoingcallsvalue");
			GetText(application, "International Incoming Calls Barring", "internationalincomingcallsvalue");
			GetText(application, "Mobile Calls Allowed", "mobilecallsallowedvalue");
			ScrolltoElement(application, "view_noreplytimervalue", xml);
			if(!edit_noreplytimervalue.equalsIgnoreCase("null"))
			{
				compareText(application, "No Reply Timer Value", "view_noreplytimervalue", edit_noreplytimervalue, xml);
			}
			else
			{
				compareText(application, "No Reply Timer Value", "view_noreplytimervalue", noreplytimervalue, xml);
			}
			if(!edit_maxcallduration.equalsIgnoreCase("null"))
			{
				compareText(application, "Max Call Duration", "maxcalldurationvalue", edit_maxcallduration, xml);
			}
			else
			{
				compareText(application, "Max Call Duration", "maxcalldurationvalue", maxcallduration, xml);
			}
			GetText(application, "Charge Band Name", "chargebandnamevalue");
			GetText(application, "Pay phone blocking enabled", "payphoneblockingenabledvalue");
			GetText(application, "webAccessBlocked", "payphoneblockingenabledvalue");
			GetText(application, "SAN Block", "sanblockvalue");
			ScrolltoElement(application, "focenabledvalue", xml);
			GetText(application, "FOC Enabled", "focenabledvalue");
			if(!edit_predestinationnumber.equalsIgnoreCase("null"))
			{
				compareText(application, "Predestination number", "predestinationnumbervalue", edit_predestinationnumber, xml);
			}
			else
			{
				compareText(application, "Predestination number", "predestinationnumbervalue", predestinationnumber, xml);
			}
			GetText(application, "CPS Free Format", "CPSvalue");
			compareText(application, "Ring To Number", "ringtonumbervalue", ringtonumber, xml);
			GetText(application, "Announcement to play", "announcementtoplay");
			GetText(application, "Tree name", "treenamevalue");

			//Edit SAN link in view SAN page
			Thread.sleep(2000);
			scrollToTop();
			click_commonMethod(application, "Action dropdown", "viewsan_actiondropdown", xml);
			click_commonMethod(application, "Edit SAN", "editsan_link", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/editsan_header")).isDisplayed())
			{
			compareText(application, "Edit SAN Header", "editsan_header", "Edit SAN", xml);
			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to Edit SAN page");
			Log.info("Navigated to Edit SAN page");
			scrolltoend();
			click_commonMethod(application, "Cancel", "cancelbutton", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
				System.out.println("Navigated to view service page");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
				Log.info("Didn't navigate to view service page");
			}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to Edit SAN page");
				Log.info("Didn't navigate to Edit SAN page");
			}
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step: SAN is not added in the grid");
		}
			
		
		//Add Another SAN link in view SAN page
		ScrolltoElement(application, "customerheader", xml);
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
			WebElement AddedSAN2= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
			if(!SANGrid2.contains("height: 1px"))
			{
				Clickon(AddedSAN2);
				Thread.sleep(1000);

				click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
				click_commonMethod(application, "View SAN link", "viewsan_link", xml);
				compareText(application, "View SAN Header", "viewsan_header", "View SAN", xml);
				scrollToTop();
			click_commonMethod(application, "Action dropdown", "viewsan_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Add Another SAN", "addanothersanlink", xml);
			Thread.sleep(2000);
			if(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")).isDisplayed())
			{
				compareText(application, "Add SAN Header", "addsan_header", "Add SAN", xml);
				DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to Add SAN page");
				Log.info("Navigated to Add SAN page");
				scrolltoend();
				click_commonMethod(application, "Cancel", "cancelbutton", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
				System.out.println("Navigated to view service page");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Navigated to view service page");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to view service page");
				Log.info("Didn't navigate to view service page");
			}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Didn't navigate to Add SAN page");
				Log.info("Didn't navigate to Add SAN page");
			}
			
			}
			else
			{
				Log.info("SAN is not added in the grid");
				System.out.println("SAN is not added in the grid");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: SAN is not added in the grid");
			}


		//Delete SAN link in View SAN page
			ScrolltoElement(application, "customerheader", xml);
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
		WebElement AddedSAN3= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
		if(!SANGrid3.contains("height: 1px"))
		{
			Clickon(AddedSAN3);
			Thread.sleep(1000);

			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "View SAN", "viewsan_link", xml);
			scrollToTop();
			compareText(application, "View SAN Header", "viewsan_header", "View SAN", xml);
			click_commonMethod(application, "Action dropdown", "viewsan_actiondropdown", xml);
			click_commonMethod(application, "Delete SAN", "deletesan_link", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step: Delete alert popup is displayed as expected");
				Log.info("Delete alert popup is displayed as expected");
				click_commonMethod(application, "Delete alert close", "deletealertclose", xml);
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Delete alert popup is not displayed");
				Log.info("Delete alert popup is not displayed");
			}
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}
		scrolltoend();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
		}

		//=====================================================================


		// PortIn SAN

		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

		WebElement SANGridCheck4= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid4= SANGridCheck4.getAttribute("style");
		System.out.println("Customer Name displaying: " +customernamevalue);
		WebElement AddedSAN4= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
		if(!SANGrid4.contains("height: 1px"))
		{
			Clickon(AddedSAN4);
			Thread.sleep(1000);

		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
		click_commonMethod(application, "PortIN SAN link", "portin_link", xml);

		WebElement Port_Dialog= getwebelement(xml.getlocator("//locators/" + application + "/portin_dialog"));
		if(Port_Dialog.isDisplayed())
		{
			compareText(application, "Customer Name", "Port_Customername", customernamevalue, xml);
			compareText(application, "SAN/FRC Number", "Port_SanNumber", sannumbervalue, xml);
			
			String PortToNumber= getwebelement(xml.getlocator("//locators/" + application + "/porttonumber")).getAttribute("value");
			if(PortToNumber!=null)
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step: PortIN Number is displayed as:" +PortToNumber);
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
					DriverTestcase.logger.log(LogStatus.PASS, "Step: Web access blocked checkbox is checked as expected");
					Log.info("Web access blocked checkbox is checked as expected");
				}
				else
					DriverTestcase.logger.log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
				Log.info("Web access blocked checkbox is not checked");
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step: Web access blocked checkbox is not checked");
				Log.info("Web access blocked checkbox is not checked");
			}

			try {
				String PortDate= getwebelement(xml.getlocator("//locators/" + application + "/portdate")).getAttribute("value");
				if(PortDate!= null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Port Date value is: '"+PortDate+"'");
					Log.info("Port Date value is: '"+PortDate+"'");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Port Date value is not displaying");
				Log.info("Port Date value is not displaying");
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Port Date value is not displaying");
				Log.info("Port Date value is not displaying");
			}

			//Port Time
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
			LocalDateTime ldt= LocalDateTime.now().plusHours(1).plusMinutes(10).plusSeconds(20);
			String FutureTime= ldt.format(formatter).substring(10).trim();
			System.out.println("Port Time:"+FutureTime);
			addtextFields_commonMethod(application, "Port Time", "porttime", FutureTime, xml);
			click_commonMethod(application, "OK", "port_okbutton", xml);
			//porting status
			try {
				String PortStatus= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/portingstatus")));
				if(PortStatus.equalsIgnoreCase("Ported"))
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : PortIN is successful");
					Log.info("PortIN is successful");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortIN is not successful");
					Log.info("PortIN is not successful");
					String PortError= getwebelement(xml.getlocator("//locators/" + application + "/port_errordisplay")).getText();
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Error is displayed as:" +PortError);
					Log.info("Error is displayed as:" +PortError);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortIN is unsuccessful");
				Log.info("PortIN is unsuccessful");
			}
			Thread.sleep(1000);
			click_commonMethod(application, "Close", "port_closesymbol", xml);
			Thread.sleep(2000);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortIN page is not opened");	
		Log.info("PortIN page is not opened");
		}
		//Cancel porting not done
		
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : SAN is not added in the grid");	
		}
		


		//===================================================================

		//Port OUT SAN

		ScrolltoElement(application, "customerheader", xml);
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
		WebElement AddedSAN5= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
		if(!SANGrid5.contains("height: 1px"))
		{
			Clickon(AddedSAN5);
			Thread.sleep(1000);

		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
		click_commonMethod(application, "PortIN SAN link", "portout_link", xml);
		WebElement Port_Dialog= getwebelement(xml.getlocator("//locators/" + application + "/portin_dialog"));
		if(Port_Dialog.isDisplayed())
		{
			compareText(application, "Customer Name", "Port_Customername", customernamevalue, xml);
			compareText(application, "SAN/FRC Number", "Port_SanNumber", sannumbervalue, xml);
			String PortToNumber= getwebelement(xml.getlocator("//locators/" + application + "/porttonumber")).getAttribute("value");
			if(PortToNumber!=null)
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step: PortIN Number is displayed as:" +PortToNumber);
				Log.info("PortIN Number is displayed as:" +PortToNumber);
			}
			else
			{
				addtextFields_commonMethod(application, "Port To Number", "porttonumber", portinnumber, xml);
			}

			
			try {
				String PortDate= getwebelement(xml.getlocator("//locators/" + application + "/portdate")).getAttribute("value");
				if(PortDate!= null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Port Date value is: '"+PortDate+"'");
					Log.info("Port Date value is: '"+PortDate+"'");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Port Date value is not displaying");
				Log.info("Port Date value is not displaying");
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Port Date value is not displaying");
				Log.info("Port Date value is not displaying");
			}

			//Port Time
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
			LocalDateTime ldt= LocalDateTime.now().plusHours(1).plusMinutes(10).plusSeconds(20);
			String FutureTime= ldt.format(formatter).substring(10).trim();
			System.out.println("Port Time:"+FutureTime);
			addtextFields_commonMethod(application, "Port Time", "porttime", FutureTime, xml);
			click_commonMethod(application, "OK", "port_okbutton", xml);
			//porting status
			try {
				String PortStatus= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/portingstatus")));
				if(PortStatus.equalsIgnoreCase("Ported"))
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : PortOUT is successful");
					Log.info("PortOUT is successful");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortOUT is not successful");
					Log.info("PortOUT is not successful");
					String PortError= getwebelement(xml.getlocator("//locators/" + application + "/port_errordisplay")).getText();
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Error is displayed as:" +PortError);
					Log.info("Error is displayed as:" +PortError);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortOUT is unsuccessful");
				Log.info("PortOUT is unsuccessful");
			}
			
			Thread.sleep(1000);
			click_commonMethod(application, "Close", "port_closesymbol", xml);
			Thread.sleep(2000);
			//Cancel porting not done
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : PortOut page is not opened");
			Log.info("PortOut page is not opened");
		}
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : SAN is not added in the grid");
		}
		
			
		//Manage additional FRC
				ScrolltoElement(application, "customerheader", xml);
				compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
				//SANFilter
				click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
				click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
				addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
				getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
				cleartext(application, "SAN Number Search", "sannumbersearchfield");
				addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
				getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

				WebElement SANGridCheck10= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
				String SANGrid10= SANGridCheck10.getAttribute("style");
				System.out.println("Customer Name displaying: " +customernamevalue);
				WebElement AddedSAN10= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
				if(!SANGrid10.contains("height: 1px"))
				{
					Clickon(AddedSAN10);
					Thread.sleep(1000);
				compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
				click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
				click_commonMethod(application, "Manage Addnl FRC", "manageaddnlfrc_link", xml);
				Thread.sleep(2000);
				compareText(application, "Manage Addnl FRC Header", "manageaddnlfrc_header", "Manage Addnl FRC", xml);
				click_commonMethod(application, "Action Dropdown", "manageaddnlfrc_actiondropdown", xml);
				click_commonMethod(application, "Add Addnl FRC", "addaddnlfrc_link", xml);
				Thread.sleep(2000);
				compareText(application, "Add Price Announcement Header", "addpriceannouncement_header", "Add Price Announcement", xml);
				compareText(application, "SAN/FRC", "sanfrcvalue", sannumbervalue, xml);
				compareText(application, "Customer Name", "customernamevalue", customernamevalue, xml);
				addCheckbox_commonMethod(application, "enablepriceannouncement_checkbox", "Enable price announcement", enablepriceannouncement_checkbox, "no", xml);
				addDropdownValues_commonMethod(application, "Price Announcement", "priceannouncement_dropdown", priceannouncementvalue, xml);
				addDropdownValues_commonMethod(application, "Price Ann Origin", "priceannorigin_dropdown", priceannoriginvalue, xml);
				addCheckbox_commonMethod(application, "interruptible_checkbox", "Interruptable Price Announcement", interruptiblepriceannouncement_checkbox, "no", xml);
				addtextFields_commonMethod(application, "Value In Price In(Cents)", "valueinprice_field", valueinprice, xml);
				addCheckbox_commonMethod(application, "sendfci_checkbox", "Send FCI", sendfci_checkbox, "yes", xml);
				addCheckbox_commonMethod(application, "sendsci_checkbox", "Send SCI", sendsci_checkbox, "no", xml);
				addDropdownValues_commonMethod(application, "Charge Band Name", "addfrc_chargebandname", chargebandname, xml);
				addCheckbox_commonMethod(application, "enablecallerconfirmation_checkbox", "Enable Caller Confirmation", enablecallerconfirmation_checkbox, "no", xml);
				addDropdownValues_commonMethod(application, "Caller Confirmation Announcement", "callerconfirmationannouncement_dropdown", callerconfirmationannouncementvalue, xml);
				addtextFields_commonMethod(application, "Caller Confirmation Digit", "callerconfirmationdigit_field", callerconfirmationdigitvalue, xml);
				addtextFields_commonMethod(application, "Number Of Repetation Allowed", "noofrepetitionallowed_field", numberofrepetitionsallowedvalue, xml);
				Thread.sleep(2000);
				scrolltoend();
				click_commonMethod(application, "OK", "OkButton", xml);
				Thread.sleep(2000);
				compareText(application, "Addl FRC created success message", "addsan_successmsg", "Addnl Frc successfully created.", xml);
				compareText(application, "Manage Addnl FRC Header", "manageaddnlfrc_header", "Manage Addnl FRC", xml);
				
				//verify table data in manage addnl frc page
				compareText(application, "FRC Number column", "manageaddnlfrc_frcnumber_column", "FRC Number", xml);
				compareText(application, "Price Ann Flag column", "manageaddnlfrc_priceannflag_column", "Price Ann Flag", xml);
				compareText(application, "Price Ann Origin column", "manageaddnlfrc_priceannorigin_column", "Price Ann Origin", xml);
				compareText(application, "Price Announcement column", "manageaddnlfrc_priceannouncement_column", "Price Annoncment", xml);
				compareText(application, "FRC Number value", "manageaddnlfrc_frcnumber_value", sannumbervalue, xml);
				String PriceAnnFlagvalue= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_priceannflag_value")).getText();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Price Ann Flag value is displayed as: " +PriceAnnFlagvalue);
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Max Call Flag value is displayed as: " +MaxCallFlagvalue);
				String MaxCallOriginvalue= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_maxcallorigin_value")).getText();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Max Call Origin value is displayed as: " +MaxCallOriginvalue);
				String MaxCallDurationvalue= getwebelement(xml.getlocator("//locators/" + application + "/manageaddnlfrc_maxcallduration_value")).getText();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Max Call Duration value is displayed as: " +MaxCallDurationvalue);
				Thread.sleep(1000);
				click_commonMethod(application, "Cancel", "cancelbutton", xml);
				
				}
				else
				{
					Log.info("SAN is not added in the grid");
					System.out.println("SAN is not added in the grid");
				}
				
				ScrolltoElement(application, "customerheader", xml);
				compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
				//SANFilter
				click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
				click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
				addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
				getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
				cleartext(application, "SAN Number Search", "sannumbersearchfield");
				addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
				getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();

				WebElement SANGridCheck11= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
				String SANGrid11= SANGridCheck11.getAttribute("style");
				System.out.println("Customer Name displaying: " +customernamevalue);
				WebElement AddedSAN11= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
				if(!SANGrid11.contains("height: 1px"))
				{
					Clickon(AddedSAN11);
					Thread.sleep(1000);
				compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
				click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
				click_commonMethod(application, "Manage Addnl FRC", "manageaddnlfrc_link", xml);
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
						DriverTestcase.logger.log(LogStatus.PASS, "Added Addnl FRC is already selected");
						Log.info("Added Addnl FRC is already selected");
					}
					Thread.sleep(1000);
					click_commonMethod(application, "Action Dropdown", "manageaddnlfrc_actiondropdown", xml);
					click_commonMethod(application, "Edit", "edit", xml);
					Thread.sleep(2000);
					compareText(application, "Add Price Announcement Header", "addpriceannouncement_header", "Add Price Announcement", xml);
					compareText(application, "SAN/FRC", "sanfrcvalue", sannumbervalue, xml);
					compareText(application, "Customer Name", "customernamevalue", customernamevalue, xml);
					addCheckbox_commonMethod(application, "enablepriceannouncement_checkbox", "Enable price announcement checkbox", edit_enablepriceannouncement_checkbox, "no", xml);
					addDropdownValues_commonMethod(application, "Price Announcement", "priceannouncement_dropdown", edit_priceannouncementvalue, xml);
					//addDropdownValues_commonMethod(application, "Price Ann Origin", "priceannorigin_dropdown", edit_priceannoriginvalue, xml);
					  boolean availability=false;
						try {  
						  availability=getwebelement(xml.getlocator("//locators/" + application + "/priceannorigin_dropdown")).isDisplayed();
						  if(availability) {
							  DriverTestcase.logger.log(LogStatus.PASS, "Price Ann Origin dropdown is displaying");
							  System.out.println("Price Ann Origin dropdown is displaying");
							  
							  if(edit_priceannoriginvalue.equalsIgnoreCase("null")) {
								  
								  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under Price Ann Origin dropdown");
								  System.out.println(" No values selected under Price Ann Origin dropdown");
							  }else {
								  
								  Clickon(getwebelement("//div[label[text()='Price Ann Origin']]//div[text()='×']"));
								  Thread.sleep(3000);
								  
								  //verify list of values inside dropdown
								  List<WebElement> listofvalues = driver
											.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
								  
								  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside Price Ann Origin dropdown is:  ");
								  System.out.println( " List of values inside Price Ann Origin dropdown is:  ");
								  
									for (WebElement valuetypes : listofvalues) {
												Log.info("service sub types : " + valuetypes.getText());
												DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
												System.out.println(" " + valuetypes.getText());
									}
									
									Thread.sleep(2000);
								SendKeys(getwebelement("//div[label[text()='Price Ann Origin']]//input"), edit_priceannoriginvalue);	
								Thread.sleep(2000);
									
								  Clickon(getwebelement("(//div[contains(text(),'"+ edit_priceannoriginvalue +"')])[2]"));
								  Thread.sleep(3000);
								  
								  String actualValue=getwebelement("//label[text()='Price Ann Origin']/following-sibling::div//span").getText();
								  DriverTestcase.logger.log(LogStatus.PASS, "Price Ann Origin dropdown value selected as: "+ actualValue );
								  System.out.println( "Price Ann Origin dropdown value selected as: "+ actualValue);
								  
							  }
						  }else {
							  DriverTestcase.logger.log(LogStatus.FAIL, "Price Ann Origin is not displaying");
							  System.out.println("Price Ann Origin is not displaying");
						  }
						}catch(NoSuchElementException e) {
							DriverTestcase.logger.log(LogStatus.FAIL, "Price Ann Origin is not displaying");
							  System.out.println("Price Ann Origin is not displaying");
						}catch(Exception ee) {
							ee.printStackTrace();
							DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to perform selection under Price Ann Origin dropdown");
							System.out.println(" NO value selected under Price Ann Origin dropdown");
						}
						
					addCheckbox_commonMethod(application, "interruptible_checkbox", "Interruptiable Price Announcement", edit_interruptiblepriceannouncement_checkbox, "no", xml);
					if(!edit_valueinprice.equalsIgnoreCase("null"))
					{
					cleartext(application, "Value In Price In(Cents)", "valueinprice_field");
					addtextFields_commonMethod(application, "Value In Price In(Cents)", "valueinprice_field", edit_valueinprice, xml);
					}
					else
					{
						addtextFields_commonMethod(application, "Value In Price In(Cents)", "valueinprice_field", valueinprice, xml);
					}
					addCheckbox_commonMethod(application, "sendfci_checkbox", "Send FCI", edit_sendfci_checkbox, "yes", xml);
					addCheckbox_commonMethod(application, "sendsci_checkbox", "Send SCI", edit_sendsci_checkbox, "no", xml);
					addDropdownValues_commonMethod(application, "Charge Band Name", "addfrc_chargebandname", edit_chargebandname, xml);
					addCheckbox_commonMethod(application, "enablecallerconfirmation_checkbox", "Enable Caller Confirmation", edit_enablecallerconfirmation_checkbox, "no", xml);
					addDropdownValues_commonMethod(application, "Caller Confirmation Announcement", "callerconfirmationannouncement_dropdown", edit_callerconfirmationannouncementvalue, xml);
					if(!edit_callerconfirmationdigitvalue.equalsIgnoreCase("null"))
					{
					cleartext(application, "Caller Confirmation Digit", "callerconfirmationdigit_field");
					addtextFields_commonMethod(application, "Caller Confirmation Digit", "callerconfirmationdigit_field", edit_callerconfirmationdigitvalue, xml);
					}
					else
					{
						addtextFields_commonMethod(application, "Caller Confirmation Digit", "callerconfirmationdigit_field", callerconfirmationdigitvalue, xml);
					}
					if(!edit_numberofrepetitionsallowedvalue.equalsIgnoreCase("null"))
					{
					cleartext(application, "Number Of Repetation Allowed", "noofrepetitionallowed_field");
					addtextFields_commonMethod(application, "Number Of Repetation Allowed", "noofrepetitionallowed_field", edit_numberofrepetitionsallowedvalue, xml);
					}
					else
					{
						addtextFields_commonMethod(application, "Number Of Repetation Allowed", "noofrepetitionallowed_field", numberofrepetitionsallowedvalue, xml);
					}
					Thread.sleep(2000);
					ScrolltoElement(application, "noofrepetitionallowed_field", xml);
					click_commonMethod(application, "OK", "OkButton", xml);
					compareText(application, "Addl FRC updated success message", "addsan_successmsg", "Addnl Frc successfully updated.", xml);
				}
				else
				{
					Log.info("Addnl FRC is not added in the grid");
					System.out.println("Addnl FRC is not added in the grid");
					DriverTestcase.logger.log(LogStatus.FAIL, "Addnl FRC is not added in the grid");
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
							DriverTestcase.logger.log(LogStatus.PASS, "Added Addnl FRC is already selected");
							Log.info("Added Addnl FRC is already selected");
						}
						Thread.sleep(1000);
						click_commonMethod(application, "Action Dropdown", "manageaddnlfrc_actiondropdown", xml);
						click_commonMethod(application, "View", "view", xml);
						Thread.sleep(2000);
						compareText(application, "View Price Announcement Header", "viewpriceannouncement_header", "View Price Announcement ", xml);
						compareText(application, "SAN/FRC", "sanfrcvalue", sannumbervalue, xml);
						compareText(application, "Customer Name", "customernamevalue", customernamevalue, xml);
						
						String EnablePriceAnnouncementCheckbox_disabled= getwebelement(xml.getlocator("//locators/" + application + "/enablepriceannouncement_checkbox")).getAttribute("disabled");
						String EnablePriceAnnouncementCheckbox_checked= getwebelement(xml.getlocator("//locators/" + application + "/enablepriceannouncement_checkbox")).getAttribute("checked");
						if(EnablePriceAnnouncementCheckbox_disabled!=null && EnablePriceAnnouncementCheckbox_checked!=null)
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Enable Price Announcement checkbox is checked & disabled");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Enable Price Announcement checkbox is disabled");
						}
						
						if(getwebelement(xml.getlocator("//locators/" + application + "/priceannouncementvalue_viewpage")).isDisplayed())
						{
						GetText(application, "Price Announcement", "priceannouncementvalue_viewpage");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, " No value selected in Price Announcement dropdown");
						}
						if(getwebelement(xml.getlocator("//locators/" + application + "/priceannoriginvalue_viewpage")).isDisplayed())
						{
							GetText(application, "Price Ann Origin", "priceannoriginvalue_viewpage");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, "No value selected in Price Ann Origin dropdown");
						}
						if(getwebelement(xml.getlocator("//locators/" + application + "/interruptible_checkbox")).getAttribute("checked")!=null)
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Interruptiable Price Announcement checkbox is checked");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Interruptiable Price Announcement checkbox is not checked");
						}
						GetText(application, "Value In Price In(Cents)", "valueinprice_field");
						if(getwebelement(xml.getlocator("//locators/" + application + "/sendfci_checkbox")).getAttribute("checked")!=null)
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Send FCI checkbox is checked");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Send FCI checkbox is not checked");
						}
						if(getwebelement(xml.getlocator("//locators/" + application + "/sendsci_checkbox")).getAttribute("checked")!=null)
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Send SCI checkbox is checked");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Send SCI checkbox is not checked");
						}
						
						if(getwebelement(xml.getlocator("//locators/" + application + "/chargebandnamevalue_viewpage")).isDisplayed())
						{
							GetText(application, "Charge Band Name", "chargebandnamevalue_viewpage");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, "No value selected in Charge Band Name dropdown");
						}
						if(getwebelement(xml.getlocator("//locators/" + application + "/enablecallerconfirmation_checkbox")).getAttribute("checked")!=null)
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Enable Caller Confirmation checkbox is checked");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Enable Caller Confirmation checkbox is not checked");
						}
						if(getwebelement(xml.getlocator("//locators/" + application + "/callerconfirmationannouncement_dropdown")).isDisplayed())
						{
							GetText(application, "Caller Confirmation Announcement", "callerconfirmationannouncement_dropdown");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, " No value selected in Caller Confirmation Announcement dropdown");
						}
						GetText(application, "Caller Confirmation Digit", "callerconfirmationdigit_field");
						GetText(application, "Number Of Repetation Allowed", "noofrepetitionallowed_field");
						Thread.sleep(2000);
						ScrolltoElement(application, "noofrepetitionallowed_field", xml);
						click_commonMethod(application, "Cancel", "viewaddnlfrc_cancelbutton", xml);
						Thread.sleep(2000);
						
					}
					else
					{
						Log.info("Addnl FRC is not added in the grid");
						System.out.println("Addnl FRC is not added in the grid");
						DriverTestcase.logger.log(LogStatus.FAIL, "Addnl FRC is not added in the grid");
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
							DriverTestcase.logger.log(LogStatus.PASS, "Added Addnl FRC is already selected");
							Log.info("Added Addnl FRC is already selected");
						}
						Thread.sleep(1000);
						click_commonMethod(application, "Action Dropdown", "manageaddnlfrc_actiondropdown", xml);
						click_commonMethod(application, "Delete Addnl FRC", "delete", xml);
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
						compareText(application, "Delete", "deletesuccessmsg", "", xml);
						Thread.sleep(2000);
						click_commonMethod(application, "Cancel", "cancelbutton", xml);
						Thread.sleep(2000);
					}
					else
					{
						Log.info("Addnl FRC is not added in the grid");
						System.out.println("Addnl FRC is not added in the grid");
						DriverTestcase.logger.log(LogStatus.FAIL, "Addnl FRC is not added in the grid");
					}
				}
				else
				{
					Log.info("SAN is not added in the grid");
					System.out.println("SAN is not added in the grid");
				}
	}
	
	
		//====================================================================================

		public void verifySANMove(String application, String customernamevalue, String sannumbervalue, String select_sansearchtype, String destinationcustomername, String sanmove_orderno) throws InterruptedException, DocumentException, IOException {
		//SAN Move link under SAN panel in view service page

		//Cancel SAN Move
			ScrolltoElement(application, "customerheader", xml);
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
		WebElement AddedSAN6= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
		if(!SANGrid6.contains("height: 1px"))
		{
			Clickon(AddedSAN6);
			Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
		click_commonMethod(application, "SAN Move", "sanmove_link", xml);
		Thread.sleep(2000);
		compareText(application, "SAN Move Header", "sanmoveheader", "SAN Move", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Move is cancelled");
		Log.info("SAN Move is cancelled");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
		}
		//SAN Move
		ScrolltoElement(application, "customerheader", xml);
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
		WebElement AddedSAN7= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
		if(!SANGrid7.contains("height: 1px"))
		{
			Clickon(AddedSAN7);
			Thread.sleep(1000);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
		click_commonMethod(application, "SAN Move", "sanmove_link", xml);
		Thread.sleep(2000);
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
		compareText(application, "SAN Move success message", "successmsgalert", "San Moved Successfully", xml);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Move is successful");
		Log.info("SAN Move is successful");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN is not added in the grid");
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
		scrolltoend();
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
		Thread.sleep(3000);
		
		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		cleartext(application, "SAN Number Search", "sannumbersearchfield");
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();
		
		WebElement AddedSAN11= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
		if(AddedSAN11.isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Move verification is successfull in destination customer");
			Log.info("SAN Move verification is successfull in destination customer");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : SAN is not moved successfully into destination customer");
			Log.info("SAN is not moved successfully into destination customer");
		}
		}
		//=======================================
		
		//Bulk Move
		
		public void verifyBulkMove(String application, String sid, String customernamevalue, String sannumbervalue, String select_sansearchtype, String bulkmove_country, String bulkmove_customer, String filterfrcnumber, String bulkmove_service, String country, String sannumber, String ringtonumber, String routingforpayphone_value, String routingformobile_value, String defaultrouting_value, String RingToNumber_Checkbox, String AnnouncementToPlay_Checkbox, String ComplexRouting_Checkbox, String defaultroutebusy_value, String noanswer_value, String networkcongestion, String resellername, String defaultvalue, String configure, String email, String phone, String fax, String city, String streetname, String streetno, String pobox, String zipcode, String serviceprofilevalue, String bulkmove_sannumber1, String bulkmove_sannumber2) throws InterruptedException, DocumentException, IOException {
		//Cancel Bulk Move
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),sid);
		Thread.sleep(1000);

		WebElement searchbutton1= getwebelement(xml.getlocator("//locators/" + application + "/searchbutton"));
		ScrolltoElement(application, "searchbutton", xml);
		Clickon(searchbutton1);
		Thread.sleep(2000);
		scrolltoend();
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
		Thread.sleep(3000);
		
		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		
		//==============Add SAN to perform bulk move======
		
		click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);	
		click_commonMethod(application, "Add SAN", "addsan_link", xml);
		scrollToTop();
		compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);

		addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
		click_commonMethod(application, "Customer Name dropdown", "customername", xml);
		Thread.sleep(1000);

		if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_valuesdisplay")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Existing customer is available");
			Log.info("Existing customer is available");
			click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
		}
		else if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_nomatchesfound")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Existing customer is not available");
			Log.info("Existing customer is not available");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")));

			//Manage new customer link
			click_commonMethod(application, "Manage new customer", "managenewcustomer_link", xml);
			compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
			Thread.sleep(1000);

			//=========================

			//Add Customer
			//Select Country from dropdown
			addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
			String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
			String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
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
					DriverTestcase.logger.log(LogStatus.PASS, "Default checkbox is checked");
					Log.info("Default checkbox is checked");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Default checkbox is not checked by default");
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
					DriverTestcase.logger.log(LogStatus.PASS, "Configure checkbox is checked");
					Log.info("Configure checkbox is checked");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Configure checkbox is not checked");
					Log.info("Configure checkbox is not checked");
					click_commonMethod(application, "Configure checkbox", "configurecheckbox", xml);
				}
			}
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "Next", "nextbutton", xml);
			Thread.sleep(3000);	

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
		DriverTestcase.logger.log(LogStatus.PASS, "Step :Customer name selected is : " + SAN_Customernamevalue);
		String SAN_Customername=SAN_Customernamevalue.substring(0, SAN_Customernamevalue.indexOf("(")).trim();
		writetoexcel("src\\com\\saksoft\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 109, SAN_Customername);

		String SANNumber_CountryCode=getwebelement(xml.getlocator("//locators/" + application + "/san_number")).getAttribute("value");
		addtextFields_commonMethod(application, "SAN Number", "san_number", bulkmove_sannumber1, xml);
		//SAN number
		String Bulkmove_SANNumberValue1= SANNumber_CountryCode+bulkmove_sannumber1;
		DriverTestcase.logger.log(LogStatus.PASS, "Step :SAN Number is dsplayed as : " + Bulkmove_SANNumberValue1);

		//Select service profile from dropdown
		addDropdownValues_commonMethod(application, "Service Profile", "serviceprofile", serviceprofilevalue, xml);
		Thread.sleep(1000);
		scrolltoend();
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
		scrolltoend();
		click_commonMethod(application, "Next", "nextbutton", xml);

		if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
		{
			Log.info("Navigated to view SAN page");
			System.out.println("Navigated to view SAN page");
			compareText(application, "Add SAN success message", "addsan_successmsg", "SAN successfully created", xml);
			scrolltoend();
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		}
		else
		{
			Log.info("SAN not created");
			System.out.println("SAN not created");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : SAN not created");
		}

		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
			Log.info("Navigated to view service page");
			System.out.println("Navigated to view service page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to view service page");
		}
		else
		{
			Log.info("Not navigated to view service page");
			System.out.println("Not navigated to view service page");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to view service page");
		}

		//===========End of Add SAN=========
		
		//==============Add SAN to perform bulk move======
		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		click_commonMethod(application, "Action dropdown", "SANActionDropdown", xml);	
		click_commonMethod(application, "Add SAN", "addsan_link", xml);
		scrollToTop();
				compareText(application, "Add SAN header", "addsan_header", "Add SAN", xml);

				addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
				click_commonMethod(application, "Customer Name dropdown", "customername", xml);
				Thread.sleep(1000);

				if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_valuesdisplay")).isDisplayed())
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Existing customer is available");
					Log.info("Existing customer is available");
					click_commonMethod(application, "Customer Name dropdown value", "customernamedropdown_valuesdisplay", xml);
				}
				else if(getwebelement(xml.getlocator("//locators/" + application + "/customernamedropdown_nomatchesfound")).isDisplayed())
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Existing customer is not available");
					Log.info("Existing customer is not available");
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsan_header")));

					//Manage new customer link
					click_commonMethod(application, "Manage new customer", "managenewcustomer_link", xml);
					compareText(application, "Manage Customer in OSP header", "manageresellerheader", "Manage Customer In OSP", xml);
					Thread.sleep(1000);

					//=========================

					//Add Customer
					//Select Country from dropdown
					addDropdownValues_commonMethod(application, "Country", "customer_country", country, xml);
					String CustomerOCN= getwebelement(xml.getlocator("//locators/" + application + "/customer_ocn")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : OCN is displaying as: '"+CustomerOCN+"'");
					String CustomerNamevalue= GetText(application, "Customer Name", "customerpanel_customernamevalue");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name is displaying as: '"+CustomerNamevalue+"'");
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
							DriverTestcase.logger.log(LogStatus.PASS, "Default checkbox is checked");
							Log.info("Default checkbox is checked");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.FAIL, "Default checkbox is not checked by default");
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
							DriverTestcase.logger.log(LogStatus.PASS, "Configure checkbox is checked");
							Log.info("Configure checkbox is checked");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.FAIL, "Configure checkbox is not checked");
							Log.info("Configure checkbox is not checked");
							click_commonMethod(application, "Configure checkbox", "configurecheckbox", xml);
						}
					}
					scrolltoend();
					Thread.sleep(1000);
					click_commonMethod(application, "Next", "nextbutton", xml);
					Thread.sleep(3000);	
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
				DriverTestcase.logger.log(LogStatus.PASS, "Step :Customer name selected is : " + SAN_Customernamevalue1);
				String SAN_Customername1=SAN_Customernamevalue1.substring(0, SAN_Customernamevalue1.indexOf("(")).trim();
				//writetoexcel("src\\com\\saksoft\\qa\\datalibrary\\APT_NGIN.xlsx", "NGIN", 109, SAN_Customername1);

				String SANNumber_CountryCode1=getwebelement(xml.getlocator("//locators/" + application + "/san_number")).getAttribute("value");
				addtextFields_commonMethod(application, "SAN Number", "san_number", bulkmove_sannumber2, xml);
				//SAN number
				String Bulkmove_SANNumberValue2= SANNumber_CountryCode1+bulkmove_sannumber2;
				DriverTestcase.logger.log(LogStatus.PASS, "Step :SAN Number is dsplayed as : " + Bulkmove_SANNumberValue2);

				//Select service profile from dropdown
				addDropdownValues_commonMethod(application, "Service Profile", "serviceprofile", serviceprofilevalue, xml);
				Thread.sleep(1000);
				scrolltoend();
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
				scrolltoend();
				click_commonMethod(application, "Next", "nextbutton", xml);

				if(getwebelement(xml.getlocator("//locators/" + application + "/viewsan_header")).isDisplayed())
				{
					Log.info("Navigated to view SAN page");
					System.out.println("Navigated to view SAN page");
					compareText(application, "Add SAN success message", "addsan_successmsg", "SAN successfully created", xml);
					scrolltoend();
					click_commonMethod(application, "Back", "viewpage_backbutton", xml);
				}
				else
				{
					Log.info("SAN not created");
					System.out.println("SAN not created");
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : SAN not created");
				}

				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to view service page");
				}
				else
				{
					Log.info("Not navigated to view service page");
					System.out.println("Not navigated to view service page");
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to view service page");
				}

				//===========End of Add SAN=========
		Thread.sleep(3000);
		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		
		WebElement SANGridCheck8= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String SANGrid8= SANGridCheck8.getAttribute("style");
		if(!SANGrid8.contains("height: 1px"))
		{
			Thread.sleep(1000);
			click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
			click_commonMethod(application, "Bulk Move", "bulkmove_link", xml);
		Thread.sleep(2000);
		compareText(application, "Bulk Move Header", "bulkmoveheader", "Bulk Move", xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Bulk Move is cancelled");
		Log.info("Bulk Move is cancelled");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN is not added in the grid");
		}
		
		//Bulk Move
		ScrolltoElement(application, "customerheader", xml);
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
		GetText(application, "Remarks", "remarktextarea");
		click_commonMethod(application, "Move", "movebutton_sanmove", xml);
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Bulk Move is successful");
		Log.info("Bulk Move is successful");
		}
		else
		{
			Log.info("SAN is not added in the grid");
			System.out.println("SAN is not added in the grid");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN is not added in the grid");
		}

		//verify SAN in destination customer service
		Thread.sleep(3000);
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(1000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorderlink")));

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/servicefield")),bulkmove_service);
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
		
		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		
		WebElement AddedSAN11= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+Bulkmove_SANNumberValue1+"')]/parent::div//input[@type='radio']");
		WebElement AddedSAN12= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+Bulkmove_SANNumberValue2+"')]/parent::div//input[@type='radio']");
		//		int AddedSANCount= AddedSAN11.size();
//		System.out.println("BulkSANAdded");
		if(AddedSAN11.isDisplayed() && AddedSAN12.isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Bulk Move verification is successfull in destination customer");
			Log.info("Bulk Move verification is successfull in destination customer");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Bulk SAN is not moved successfully into destination customer");
			Log.info("Bulk SAN is not moved successfully into destination customer");
		}
	}
		
		

	public void verifyAllDeleteOperations(String application, String customernamevalue, String select_sansearchtype, String sannumbervalue) throws InterruptedException, DocumentException, IOException	{

		//Delete SAN
		ScrolltoElement(application, "customerheader", xml);
		compareText(application, "SAN/FRC Header", "sanheader", "SAN/FRC", xml);
		//SANFilter
		click_commonMethod(application, "Search San Menu", "sanpanel_searchsanmenu", xml);
		click_commonMethod(application, "Select SAN Search type", "select_sansearchtype", xml);
		addtextFields_commonMethod(application, "Enter SAN Search type", "select_sansearchtype", select_sansearchtype, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/select_sansearchtype")).sendKeys(Keys.ENTER);
		addtextFields_commonMethod(application, "SAN Number Search", "sannumbersearchfield", sannumbervalue, xml);
		getwebelement(xml.getlocator("//locators/" + application + "/sanheader")).click();
		
			WebElement SANGridCheck2= getwebelement("(//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
			String SANGrid2= SANGridCheck2.getAttribute("style");
			System.out.println("Customer Name displaying: " +customernamevalue);
			WebElement AddedSAN2= getwebelement("//div[text()='SAN/FRC']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
			if(!SANGrid2.contains("height: 1px"))
			{
				Clickon(AddedSAN2);
				Thread.sleep(1000);

		click_commonMethod(application, "SAN Action dropdown", "SANActionDropdown", xml);
		click_commonMethod(application, "Delete SAN", "deletesan_link", xml);
		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			click_commonMethod(application, "Delete", "deletebutton", xml);
			Thread.sleep(2000);
			compareText(application, "SAN Delete success msg", "deletesuccessmsg", "San deleted Successfully", xml);
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
		
		Thread.sleep(2000);
			}
			else
			{
				Log.info("SAN is not added in the grid");
				System.out.println("SAN is not added in the grid");
			}

		//Delete Customer
			ScrolltoElement(application, "resellerheader", xml);
		WebElement CustomerGridCheck= getwebelement("(//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String CustomerGrid= CustomerGridCheck.getAttribute("style");
		WebElement AddedCustomer= getwebelement("//div[text()='Customer']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'"+customernamevalue+"')]/parent::div//input[@type='radio']");
		if(!CustomerGrid.contains("height: 1px"))
		{
			Clickon(AddedCustomer);
			Thread.sleep(1000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CustomerpanelActionDropdown")));
			Thread.sleep(1000);
			DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on Action dropdown button");
			Log.info("clicked on Action dropdown button");

			click_commonMethod(application, "Delete", "delete", xml);
			Thread.sleep(2000);
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			if(DeleteAlertPopup.isDisplayed())
			{
				click_commonMethod(application, "Delete", "deletebutton", xml);
				Thread.sleep(2000);
				compareText(application, "Customer Delete success msg", "deletesuccessmsg", "Data deleted successfully. ", xml);
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			
			Thread.sleep(2000);
		}

		//Delete Reseller
		ScrolltoElement(application, "servicepanel_header", xml);
		WebElement ResellerGridCheck= getwebelement("(//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String ResellerGrid= ResellerGridCheck.getAttribute("style");

		WebElement AddedReseller= getwebelement("//div[text()='Reseller']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div[contains(text(),'" + ResellerName + "')]/parent::div//input[@type='radio']");

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
				compareText(application, "Reseller Delete success msg", "deletesuccessmsg", "Delete Reseller:Data deleted successfully. RESELLER REMOVED", xml);
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			Thread.sleep(2000);
		}

		//Delete Service
		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Delete", "delete", xml);
			Thread.sleep(2000);
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			if(DeleteAlertPopup.isDisplayed())
			{
				click_commonMethod(application, "Delete", "deletebutton", xml);
				Thread.sleep(2000);
				compareText(application, "Service delete success message", "deletesuccessmsg", "Service successfully deleted", xml);
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
		Thread.sleep(2000);
	}


	//============================================================================================

	public void verifySearchSAN(String application, String search_sannumber, String customernamevalue, String searchSANfilename, String browserfiles_downloadspath, String serviceprofilevalue, String internationaloutgoingcalls_checkedvalue, String internationalincomingcalls_checkedvalue, String mobilecallsallowed_checkedvalue, String payphoneblocking_checkedvalue, String supervisionfieldvalue, String noreplytimervalue, String webaccessblockedvalue, String cpsfreeformatvalue, String maxcallduration, String chargebandname, String predestinationnumber) throws InterruptedException, DocumentException, IOException
	{
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/managecoltnetworklink")));
		Thread.sleep(1000);
		click_commonMethod(application, "Search for SANs", "searchsanlink", xml);
		Thread.sleep(2000);
		compareText(application, "SAN Search", "sansearchheader", "SAN Search", xml);
		addtextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		Thread.sleep(3000);
		click_commonMethod(application, "Download To Excel", "downloadtoexcellink", xml);
		Thread.sleep(3000);
		isFileDownloaded(searchSANfilename, browserfiles_downloadspath);
		
		//Rework done
		addtextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		Thread.sleep(3000);
		
		WebElement SelectSAN= getwebelement("//div[contains(text(),'"+search_sannumber+"')]/parent::div//input[@type='radio']");

		if(SelectSAN.isDisplayed())
		{
			//Verify SearchSAN columns headers
			compareText(application, "FRC Number", "frcnumber_column", "FRC Number", xml);
			compareText(application, "Service Profile", "serviceprofilecolumn", "Service Profile", xml);
			compareText(application, "Customer Name", "customernamecolumn", "Customer Name", xml);
			compareText(application, "Begin Date", "begindatecolumn", "Begin Date", xml);
			compareText(application, "Order Name", "ordernamecolumn", "Order Name", xml);
			Thread.sleep(1000);

			Clickon(SelectSAN);
			Thread.sleep(2000);
			click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
			//View link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/view")).isDisplayed())
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : View link is displaying in Search SAN page");
				Log.info("View link is displaying in Search SAN page");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : View link is not displaying in Search SAN page");
				Log.info("View link is not displaying in Search SAN page");
			}

			//Delete link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete")).isDisplayed())
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Delete link is displaying in Search SAN page");
				Log.info("Delete link is displaying in Search SAN page");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete link is not displaying in Search SAN page");
				Log.info("Delete link is not displaying in Search SAN page");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
			Log.info("No existing SAN to display");
		}
		if(SelectSAN.isDisplayed())
		{
			Clickon(SelectSAN);
			Thread.sleep(2000);
			click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "View", "view", xml);
			compareText(application, "View San Header", "searchsan_viewpageheader", "View San", xml);

			//Verify view San details

			//compareText(application, "Customer Name", "viewsan_customername", customernamevalue, xml);
			GetText(application, "Customer Name", "viewsan_customername");
			//compareText(application, "SAN/FRC Number", "viewsan_sannumber", search_sannumber, xml);
			GetText(application, "SAN/FRC Number", "viewsan_sannumber");
			//compareText(application, "Service Profile", "serviceprofilevalue", serviceprofilevalue, xml);
			GetText(application, "Service Profile", "serviceprofilevalue");
			//compareText(application, "International outgoing calls", "internationaloutgoingcallsvalue", internationaloutgoingcalls_checkedvalue, xml);
			GetText(application, "International outgoing calls", "internationaloutgoingcallsvalue");
			//compareText(application, "International incoming calls", "searchsan_internationalincomingcallsvalue", internationalincomingcalls_checkedvalue, xml);
			GetText(application, "International incoming calls", "searchsan_internationalincomingcallsvalue");
			//compareText(application, "Mobile calls allowed", "mobilecallsallowedvalue", mobilecallsallowed_checkedvalue, xml);
			GetText(application, "Mobile calls allowed", "mobilecallsallowedvalue");
			//compareText(application, "No reply timer", "view_noreplytimervalue", noreplytimervalue, xml);
			GetText(application, "No reply timer", "view_noreplytimervalue");
			//compareText(application, "Max call duration", "maxcalldurationvalue", maxcallduration, xml);
			GetText(application, "Max call duration", "maxcalldurationvalue");
			//compareText(application, "Charge band name", "chargebandnamevalue", chargebandname, xml);
			GetText(application, "Charge band name", "chargebandnamevalue");
			//compareText(application, "Payphone blocking enabled", "payphoneblockingenabledvalue", payphoneblocking_checkedvalue, xml);
			GetText(application, "Payphone blocking enabled", "payphoneblockingenabledvalue");
			//compareText(application, "Web access blocked", "webaccessblockedvalue", webaccessblockedvalue, xml);
			GetText(application, "Web access blocked", "webaccessblockedvalue");
			//compareText(application, "Predestination number", "searchsan_predestinationnumbervalue", predestinationnumber, xml);
			GetText(application, "Predestination number", "searchsan_predestinationnumbervalue");
			//compareText(application, "CPS Free Format", "CPSvalue", cpsfreeformatvalue, xml);
			GetText(application, "CPS Free Format", "CPSvalue");
			GetText(application, "Ring To Number", "ringtonumbervalue");
			GetText(application, "Announcement to play", "searchsan_announcementtoplayvalue");
			Thread.sleep(2000);
			click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
			Log.info("No existing SAN to display");
		}

		if(getwebelement(xml.getlocator("//locators/" + application + "/sansearchheader")).isDisplayed())
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to SAN Search page");
			Log.info("Navigated to SAN Search page");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to SAN Search page");
			Log.info("Didn't navigate to SAN Search page");
		}

		//Verify Search order/service page
		addtextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		Thread.sleep(3000);
		WebElement SelectSAN1= getwebelement("//div[contains(text(),'"+search_sannumber+"')]/parent::div//input[@type='radio']");
		if(SelectSAN1.isDisplayed())
		{	
			String OrderNumber= getwebelement(xml.getlocator("//locators/" + application + "/ordername_link")).getText();
			click_commonMethod(application, "Order Name", "ordername_link", xml);
			compareText(application, "Search For Order / Service Header", "searchfororder_header", "Search For Order / Service", xml);
			Thread.sleep(1000);
			WebElement SelectExistingOrder= getwebelement("//div[contains(text(),'"+OrderNumber+"')]/parent::div//input[@type='radio']");

			if(SelectExistingOrder.isDisplayed())
			{
				Clickon(SelectExistingOrder);
				click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "View", "searchfororder_viewlink", xml);
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to view service page");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
					Log.info("Didn't navigate to view service page");
				}

			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Existing order is not available");
				Log.info("Existing order is not available");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
			Log.info(" No existing SAN to display");
		}

		driver.navigate().back();
		Thread.sleep(2000);

		//Verify manage link in search order page
		compareText(application, "SAN Search", "sansearchheader", "SAN Search", xml);
		if(getwebelement(xml.getlocator("//locators/" + application + "/sansearchheader")).isDisplayed())
		{
			Log.info("Navigated to 'SAN Search' page");
			System.out.println("Navigated to 'SAN Search' page");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to 'SAN Search' page");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to 'SAN Search' page");
			Log.info("Didn't navigate to 'SAN Search' page");
		}

		addtextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		Thread.sleep(3000);

		WebElement SelectSAN2= getwebelement("//div[contains(text(),'"+search_sannumber+"')]/parent::div//input[@type='radio']");
		if(SelectSAN2.isDisplayed())
		{
			String OrderNumber= getwebelement(xml.getlocator("//locators/" + application + "/ordername_link")).getText();
			click_commonMethod(application, "Order Name", "ordername_link", xml);
			compareText(application, "Search For Order / Service Header", "searchfororder_header", "Search For Order / Service", xml);
			Thread.sleep(1000);
			WebElement SelectExistingOrder= getwebelement("//div[contains(text(),'"+OrderNumber+"')]/parent::div//input[@type='radio']");
			if(SelectExistingOrder.isDisplayed())
			{
				Clickon(SelectExistingOrder);
				click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
				Thread.sleep(1000);
				click_commonMethod(application, "Manage", "searchfororder_managelink", xml);
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
				{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to view service page");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
					Log.info("Didn't navigate to view service page");
				}

			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Existing order is not available");
				Log.info("Existing order is not available");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
			Log.info("No existing SAN to display");
		}

		driver.navigate().back();
		Thread.sleep(2000);

		//Verify Delete SAN details
		addtextFields_commonMethod(application, "SAN", "santextfield", search_sannumber, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		Thread.sleep(3000);
		WebElement SelectSAN3= getwebelement("//div[contains(text(),'"+search_sannumber+"')]/parent::div//input[@type='radio']");
		if(SelectSAN3.isDisplayed())
		{
			Clickon(SelectSAN3);
			Thread.sleep(2000);
			click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Delete SAN", "delete", xml);
			Thread.sleep(2000);
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			if(DeleteAlertPopup.isDisplayed())
			{
				click_commonMethod(application, "Delete", "searchsan_deletebutton", xml);
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			compareText(application, "SAN delete success msg", "deletesuccessmsg", "SAN successfully deleted.", xml);
			Thread.sleep(1000);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing SAN to display");
			Log.info("No existing SAN to display");
		}

	}


	public void verifyNGINMessage(String application, String nginmessage_sannumber) throws InterruptedException, DocumentException, IOException
	{
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/managecoltnetworklink")));
		Thread.sleep(1000);
		click_commonMethod(application, "NGIN Messages", "nginmessageslink", xml);
		Thread.sleep(2000);
		compareText(application, "Manage Messages Header", "nginmessageheader", "Manage Messages - Messages Search", xml);
		isDisplayed(application, "nginmsg_sannumber", "SAN Number text field");
		isDisplayed(application, "nginmsg_customername_textfield", "Customer Name text field");
		Thread.sleep(1000);
		addtextFields_commonMethod(application, "SAN Number", "nginmsg_sannumber", nginmessage_sannumber, xml);
		click_commonMethod(application, "Search", "san_searchbutton", xml);
		Thread.sleep(2000);


		WebElement SelectExistingSAN= getwebelement("//div[text()='"+nginmessage_sannumber+"']/preceding-sibling::div//div//input[@type='radio']");
		if(SelectExistingSAN.isDisplayed())
		{
			//Verify existing SAN column headers
			compareText(application, "FRC Number", "nginmsg_frcnumbercolumn", "FRC Number", xml);
			compareText(application, "Name", "nginmsg_namecolumn", "Name", xml);
			compareText(application, "Customer Name", "customernamecolumn", "Customer Name", xml);
			Thread.sleep(1000);
			Clickon(SelectExistingSAN);
			Thread.sleep(2000);
			click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
			//View link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/edit")).isDisplayed())
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Edit link is displaying in Manage Messages page");
				Log.info("Edit link is displaying in Manage Messages page");

				click_commonMethod(application, "Edit", "edit", xml);
				Thread.sleep(2000);
				compareText(application, "Edit Message Header", "editmessageheader", "Edit Message", xml);
				//verify edit message page fields
				//verify customer name field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_customername")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name field is disabled as expected");
					String Customernamevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_customername")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Customer Name field value is displayed as:"+ Customernamevalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Customer Name field is enabled");
				}

				//verify country code field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_countrycode")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Country code field is disabled as expected");
					String CountryCodevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_countrycode")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Country code field value is displayed as:"+ CountryCodevalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Country code field is enabled");
				}

				//verify san reference field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_sanreference")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Reference field is disabled as expected");
					String SANReferencevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_sanreference")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : SAN Reference field value is displayed as:"+ SANReferencevalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : SAN Reference field is enabled");
				}

				//verify name field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_name")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Name field is disabled as expected");
					String Namevalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_name")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Name field value is displayed as:"+ Namevalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Name field is enabled");
				}

				//verify description field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_description")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Description field is disabled as expected");
					String Descriptionvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_description")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Description field value is displayed as:"+ Descriptionvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Description field is enabled");
				}

				//verify body field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_bodyfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Body field is disabled as expected");
					String Bodyvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_bodyfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Body field value is displayed as:"+ Bodyvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Body field is enabled");
				}

				//verify prefix field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_prefixfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Prefix field is disabled as expected");
					String Prefixvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_prefixfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Prefix field value is displayed as:"+ Prefixvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Prefix field is enabled");
				}

				//verify repetitions field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_repetitionsfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Repetitions field is disabled as expected");
					String Repetitionsvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_repetitionsfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Repetitions field value is displayed as:"+ Repetitionsvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Repetitions field is enabled");
				}

				//verify duration field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_durationfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Duration field is disabled as expected");
					String Durationvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_durationfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Duration field value is displayed as:"+ Durationvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Duration field is enabled");
				}

				//verify suffix field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_suffixfield")).getAttribute("readonly")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Suffix field is disabled as expected");
					String Suffixvalue= getwebelement(xml.getlocator("//locators/" + application + "/editmessage_suffixfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Suffix field value is displayed as:"+ Suffixvalue);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Suffix field is enabled");
				}

				//verify start network charge checkbox field
				if(getwebelement(xml.getlocator("//locators/" + application + "/editmessage_startnetworkcharge")).getAttribute("checked")!=null)
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : 'Start Network Charge' checkbox is checked by default as expected");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : 'Start Network Charge' checkbox is not checked by default");
				}

				click_commonMethod(application, "Cancel", "cancelbutton", xml);
				Thread.sleep(3000);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Edit link is not displaying in Manage Messages page");
				Log.info("Edit link is not displaying in Manage Messages page");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing NGIN Messages to display");
			Log.info("No existing NGIN Messages to display");
		}

		WebElement SelectExistingSAN1= getwebelement("//div[text()='"+nginmessage_sannumber+"']/preceding-sibling::div//div//input[@type='radio']");
		if(SelectExistingSAN1.isDisplayed())
		{
			Clickon(SelectExistingSAN1);
			Thread.sleep(2000);
			click_commonMethod(application, "Action dropdown", "searchsan_actiondropdown", xml);
			//View link displayed verify
			if(getwebelement(xml.getlocator("//locators/" + application + "/edit")).isDisplayed())
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Edit link is displaying in Manage Messages page");
				Log.info("Edit link is displaying in Manage Messages page");

				click_commonMethod(application, "Edit", "edit", xml);
				Thread.sleep(2000);
				compareText(application, "Edit Message Header", "editmessageheader", "Edit Message", xml);
				//Uncheck start network charge checkbox
				click_commonMethod(application, "Start Network Charge Checkbox", "editmessage_startnetworkcharge", xml);

				click_commonMethod(application, "OK", "editmessage_okbutton", xml);
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/nginmessageheader")).isDisplayed())
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to Manage Messages page");
					Log.info("Navigated to Manage Messages page");
					compareText(application, "Edit Message page success message", "editmessage_successmessage", "Message successfully updated.", xml);
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Not navigated to Manage Messages page");
					Log.info("Not navigated to Manage Messages page");
				}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Edit link is not displaying in Manage Messages page");
				Log.info("Edit link is not displaying in Manage Messages page");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing NGIN Messages to display");
			Log.info("No existing NGIN Messages to display");
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
	

}
