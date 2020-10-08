package com.colt.qa.scripthelpers;

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
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;

public class APT_DomainManagementHelper extends DriverHelper {

	public APT_DomainManagementHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_DomainManagement.xml");

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


	public void verifyservicecreation(String application, String sid, String Remarks, String email, String phonecontact
			, String servicecountry, String passwordvalue, String defaultemail, String user, String servicefirstname
			, String servicelastname, String organizationname, String serviceaddress, String servicecomplement
			, String servicepostalcode, String servicecity, String servicestate, String servicephone, String servicefax
			, String orderno, String rfireqno, String servicetype) throws InterruptedException, IOException, DocumentException {
		
		//Cancel service creation
		compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		waitforPagetobeenable();
		if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
		{
		Log.info("Navigated to create order page");
		System.out.println("Navigated to create order page");
		}
		
		//Create service
	
		ScrolltoElement(application, "createorderservice_header", xml);
		
		addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", orderno, xml);
		Thread.sleep(2000);
		addDropdownValues_commonMethod(application, "Service Type", "servicetypetextfield", servicetype, xml);
		// click on next button
		scrolltoend();
		click_commonMethod(application, "Next", "nextbutton", xml);
		waitforPagetobeenable();
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
		
		//select country from dropdown
				boolean availability1=false;
				try {  
				  availability1=getwebelement(xml.getlocator("//locators/" + application + "/service_country")).isDisplayed();
				  if(availability1) {
					  ExtentTestManager.getTest().log(LogStatus.PASS, "Country dropdown is displaying");
					  System.out.println("Country dropdown is displaying");
					  
					  if(servicecountry.equalsIgnoreCase("null")) {
						  
						  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Country dropdown");
						  System.out.println(" No values selected under Country dropdown");
					  }else {
						  
						  Clickon(getwebelement("//div[label[text()='Country']]//div[text()='×']"));
						  Thread.sleep(3000);
						  
						  //verify list of values inside dropdown
						  List<WebElement> listofvalues = driver
									.findElements(By.xpath("(//div[@role='list']//div)[3]"));
						  
						  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside Country dropdown is:  ");
						  System.out.println( " List of values inside Country dropdown is:  ");
						  
							for (WebElement valuetypes : listofvalues) {
										Log.info("service sub types : " + valuetypes.getText());
										ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
										System.out.println(" " + valuetypes.getText());
							}
							
							Thread.sleep(2000);
						SendKeys(getwebelement("(//div[label[text()='Country']]//input)[1]"), servicecountry);	
						Thread.sleep(2000);
							
						  Clickon(getwebelement("(//div[contains(text(),'"+ servicecountry +"')])[1]"));
						  Thread.sleep(3000);
						  
						  String actualValue=getwebelement("//label[text()='Country']/following-sibling::div//div/span").getText();
						  ExtentTestManager.getTest().log(LogStatus.PASS, "Country dropdown value selected as: "+ actualValue );
						  System.out.println("Country) dropdown value selected as: "+ actualValue);
						  
					  }
				  }else {
					  ExtentTestManager.getTest().log(LogStatus.FAIL, "Country is not displaying");
					  System.out.println("Country is not displaying");
				  }
				}catch(NoSuchElementException e) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Country is not displaying");
					  System.out.println("Country is not displaying");
				}catch(Exception ee) {
					ee.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to perform selection under Country dropdown");
					System.out.println(" NO value selected under Country dropdown");
				}
				
		addtextFields_commonMethod(application, "Phone", "servicephone", servicephone, xml);
		addtextFields_commonMethod(application, "Fax", "servicefax", servicefax, xml);
		addtextFields_commonMethod(application, "Email", "serviceemail", email, xml);
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "Next", "nextbutton", xml);
		Thread.sleep(3000);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Service successfully created.");
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

		ScrolltoElement(application, "orderpanelheader", xml);

		//Cancel Edit order in Order panel
		click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
		click_commonMethod(application, "Edit Order", "editorderlink", xml);
		waitforPagetobeenable();
		compareText(application, "Edit Order", "editorderheader", "Edit Order", xml);
		Thread.sleep(1000);

		//WebElement EditOrderNo= getwebelement(xml.getlocator("//locators/" + application + "/editorderno"));
		click_commonMethod(application, "Order Number", "editorderno", xml);
		Thread.sleep(2000);
		edittextFields_commonMethod(application, "Order Number", "editorderno", editorderno, xml);

		//WebElement EditVoiceLineNo= getwebelement(xml.getlocator("//locators/" + application + "/editvoicelineno"));
		click_commonMethod(application, "RFI Voice Line Number", "editvoicelineno", xml);
		Thread.sleep(2000);
		edittextFields_commonMethod(application, "RFI Voiceline Number", "editvoicelineno", editvoicelineno, xml);
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		waitforPagetobeenable();
		
		//Edit Order
		Thread.sleep(1000);
		ScrolltoElement(application, "userspanel_header", xml);
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
		Thread.sleep(1000);
		waitforPagetobeenable();
		ScrolltoElement(application, "userspanel_header", xml);
		Thread.sleep(1000);

		compareText(application, "Order Number", "ordernumbervalue", editorderno, xml);
		compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", editvoicelineno, xml);
		Log.info("------ Edit Order is successful ------");

	}

	public void verifyorderpanel_changeorder(String application, String changeorderno, String changevoicelineno) throws InterruptedException, DocumentException, IOException {

		ScrolltoElement(application, "userspanel_header", xml);
		Thread.sleep(1000);
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
			Clickon(getwebelement("//label[text()='Order/Contract Number (Parent SID)']/parent::div//div[@role='list']//span[@aria-selected='false'][1]"));
			Thread.sleep(3000);

			//Cancel change order
			click_commonMethod(application, "Cancel", "changeorder_cancelbutton", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);

			//Change order
			click_commonMethod(application, "Action dropdown", "orderactionbutton", xml);
			click_commonMethod(application, "Change Order", "changeorderlink", xml);
			waitforPagetobeenable();
			compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Choose order dropdown", "changeorder_chooseorderdropdown", xml);
			Clickon(getwebelement("//label[text()='Order/Contract Number (Parent SID)']/parent::div//div[@role='list']//span[@aria-selected='false'][1]"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected order from dropdown");
			Log.info("Selected order from dropdown");
			click_commonMethod(application, "OK", "changeorder_okbutton", xml);
			Thread.sleep(1000);
			waitforPagetobeenable();
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
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
			waitforPagetobeenable();
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);

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
			ScrolltoElement(application, "userspanel_header", xml);
			Thread.sleep(1000);
			compareText(application, "Order Number", "ordernumbervalue", changeorderno, xml);
			compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
			Log.info("------ Change Order is successful ------");
		}
	}

	public void verifyservicepanelInformationinviewservicepage(String application, String sid, String servicetype, String Remarks, String email, String phonecontact, String country, String defaultemail, String servicefirstname, String servicelastname, String organizationname, String serviceaddress, String servicecomplement, String servicepostalcode, String servicecity, String servicestate, String servicephone, String servicefax, String user, String orderno, String rfireqno) throws InterruptedException, DocumentException, IOException {
		
		ScrolltoElement(application, "orderpanelheader", xml);
		compareText(application, "Service panel header", "servicepanel_header", "Service", xml);
		Thread.sleep(1000);
		ScrolltoElement(application, "servicecityvalue", xml);
		compareText(application, "Service Identification", "sidvalue", sid, xml);
		compareText(application, "Service Type", "servicepanel_servicetypevalue", servicetype, xml);
		GetText(application, "Email", "emailvalue");
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
	
	public void verifyEditService(String application, String EditRemarks, String Remarks, String changeorderno, String sid, String editsid, String servicetype, String editemail, String editphonecontact, String edituser, String editdefaultemail, String editservicefirstname, String editservicelastname, String editorganizationname, String editserviceaddress, String editservicecomplement, String editservicepostalcode, String editservicecity, String editservicestate, String editcountry, String editservicephone, String editservicefax) throws InterruptedException, DocumentException, IOException {
		
		//Cancel edit service
				ScrolltoElement(application, "orderpanelheader", xml);
				click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
				click_commonMethod(application, "Edit", "edit", xml);
				waitforPagetobeenable();
				ScrolltoElement(application, "editservice", xml);
				String ServiceIDValue= getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service Identification value is displayed as: " +ServiceIDValue);
				edittextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
				scrolltoend();
				click_commonMethod(application, "Cancel", "cancelbutton", xml);
				waitforPagetobeenable();
				if(getwebelement(xml.getlocator("//locators/" + application + "/servicepanel_header")).isDisplayed())
				{
					ScrolltoElement(application, "orderpanelheader", xml);
					compareText(application, "Service Identification", "sidvalue", sid, xml);
					compareText(application, "Remarks", "remarksvalue", Remarks, xml);
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Didn't navigate to view service page");
				}
			
				//Edit service
				ScrolltoElement(application, "orderpanelheader", xml);
				click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
				click_commonMethod(application, "Edit", "edit", xml);
				waitforPagetobeenable();
				ScrolltoElement(application, "editservice", xml);
				String ServiceIDValue1= getwebelement(xml.getlocator("//locators/" + application + "/serviceidentificationtextfield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Service Identification value is displayed as: " +ServiceIDValue1);
				compareText(application, "Service Type", "servicetype_value", servicetype, xml);
				edittextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
				//Edit email
				click_commonMethod(application, "Selected Email", "selectedemail", xml);
				click_commonMethod(application, "Email remove arrow", "emailremovearrow", xml);
				edittextFields_commonMethod(application, "Email", "emailtextfieldvalue", editemail, xml);
				click_commonMethod(application, "Email adding arrow", "emailaddarrow", xml);
				//Edit phone contact
				click_commonMethod(application, "Selected phone contact", "selectedphone", xml);
				click_commonMethod(application, "Phonecontact remove arrow", "phoneremovearrow", xml);
				edittextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", editphonecontact, xml);
				click_commonMethod(application, "phonecontact adding Arrow", "phoneaddarrow", xml);
				ScrolltoElement(application, "phonecontacttextfieldvalue", xml);
				edittextFields_commonMethod(application, "User", "userfield", edituser, xml);
				cleartext(application, "Password", "Password");
				click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
				edittextFields_commonMethod(application, "Default Email", "defaultemail", editdefaultemail, xml);
				edittextFields_commonMethod(application, "First Name", "servicefirstname", editservicefirstname, xml);
				edittextFields_commonMethod(application, "Last Name", "servicelastname", editservicelastname, xml);
				edittextFields_commonMethod(application, "Organization Name", "organizationname", editorganizationname, xml);
				edittextFields_commonMethod(application, "Address", "serviceaddress", editserviceaddress, xml);
				edittextFields_commonMethod(application, "Complement", "servicecomplement", editservicecomplement, xml);
				edittextFields_commonMethod(application, "Postal Code", "servicepostalcode", editservicepostalcode, xml);
				edittextFields_commonMethod(application, "City", "servicecity", editservicecity, xml);
				edittextFields_commonMethod(application, "State", "servicestate", editservicestate, xml);
				edittextFields_commonMethod(application, "Country", "country", editcountry, xml);
				edittextFields_commonMethod(application, "Phone", "servicephone", editservicephone, xml);
				edittextFields_commonMethod(application, "Fax", "servicefax", editservicefax, xml);
				edittextFields_commonMethod(application, "Email", "serviceemail", editemail, xml);
				Thread.sleep(2000);
				scrolltoend();
				click_commonMethod(application, "Next", "editservice_next", xml);
				waitforPagetobeenable();
									
					if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
					{
					Log.info("Navigated to view service page");
					System.out.println("Navigated to view service page");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Navigated to view service page");
					verifysuccessmessage(application, "Service successfully updated.");
					sa.assertAll();
					}
					else
					{
						Log.info("Service not updated");
						System.out.println("Service not updated");
					}
					
	}

	public void verifySynchronize(String application) throws InterruptedException, DocumentException {
		
		ScrolltoElement(application, "orderpanelheader", xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Synchronize", "synchronizelink_servicepanel", xml);
		Thread.sleep(2000);
		waitforPagetobeenable();
		ScrolltoElement(application, "Sync_successmsg", xml);
		verifysuccessmessage(application, "Sync started successfully. Please check the sync status of this service.");
	}
	
	public void verifyDeleteService(String application) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "orderpanelheader", xml);
		click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
		click_commonMethod(application, "Delete", "delete", xml);
		Thread.sleep(2000);
		WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
		if(DeleteAlertPopup.isDisplayed())
		{
			click_commonMethod(application, "Delete", "deletebutton", xml);
			//compareText(application, "Service Delete success msg", "deletesuccessmsg", "Service successfully deleted.", xml);
			verifysuccessmessage(application, "Service deleted successfully");
		}
		else
		{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
		}
		
	}
	
		
//====================================================================================================
	
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
