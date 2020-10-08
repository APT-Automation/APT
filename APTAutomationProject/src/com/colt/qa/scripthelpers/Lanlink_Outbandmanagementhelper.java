package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dom4j.DocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;

public class Lanlink_Outbandmanagementhelper extends DriverHelper {

	public Lanlink_Outbandmanagementhelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement ChooseCustomer_Select, Next_Button, CreateOrderService_Text;

	XMLReader xml = new XMLReader("src\\com\\colt\\qa\\pagerepository\\ForOutbandmanagement.xml");

	boolean orderdopdown, serviceTypedropdown, modularmspCheckbox, autocreateservicecheckbox, interfacespeeddropdown,
			servicesubtypesdropdown, availablecircuitsdropdown, nextbutton, A_EndTechnolnogy, B_Endtechnolnogy;
	SoftAssert sa = new SoftAssert();

	public void navigateToManageCustomerServicePage() throws InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/ManageCustomerServiceLink")));
		Thread.sleep(2000);
		Log.info("=== MCS page navigated ===");
		Thread.sleep(2000);
	}

	public void navigateToCreateOrderServicePage(String application) throws InterruptedException, DocumentException {

		navigateToManageCustomerServicePage();

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CreateOrderServiceLink")));
		Thread.sleep(2000);
		Log.info("=== Create Order/Service navigated ===");
	}
	
	public void CreateCustomer(String application, String name, String maindomain, String country, String ocn,
			String reference, String tcn, String type, String email, String phone, String fax)
					throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying create New Customer Functionality");
		
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(2000);
		Log.info("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "create customer link", "createcustomerlink", xml);
		Thread.sleep(2000);
		compareText(application, "create customer page header", "createcustomer_header", "Create Customer", xml);
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);
		//Warning msg check
		warningMessage_commonMethod(application, "customernamewarngmsg", "Customer Name", xml);
		warningMessage_commonMethod(application, "countrywarngmsg", "Country" , xml);
		warningMessage_commonMethod(application, "ocnwarngmsg", "OCN", xml);
		warningMessage_commonMethod(application, "typewarngmsg", "Type", xml);
		warningMessage_commonMethod(application, "emailwarngmsg", "Email" , xml);

		//Clear customer info
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		EnterTextValue(application, email, "Email", "emailtextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		scrolltoend();
		click_commonMethod(application, "Reset", "resetButton", xml);
		ExtentTestManager.getTest().log(LogStatus.PASS, "All text field values are cleared");

		//Create customer by providing all info
		cleartext(application, "Customer Name", "nametextfield");
		EnterTextValue(application, name, "Customer Name", "nametextfield");
		cleartext(application, "Main Domain", "maindomaintextfield");
		EnterTextValue(application, maindomain, "Main Domain", "maindomaintextfield");
		addDropdownValues_commonMethod(application, "Country", "country", country, xml);
		cleartext(application, "OCN", "ocntextfield");
		EnterTextValue(application, ocn, "OCN", "ocntextfield");
		cleartext(application, "Reference", "referencetextfield");
		EnterTextValue(application, reference, "Reference", "referencetextfield");
		cleartext(application, "Technical Contact Name", "technicalcontactnametextfield");
		EnterTextValue(application, tcn, "Technical Contact Name", "technicalcontactnametextfield");
		addDropdownValues_commonMethod(application, "Type", "typedropdown", type, xml);
		cleartext(application, "Email", "emailtextfield");
		EnterTextValue(application, email, "Email", "emailtextfield");
		cleartext(application, "Phone", "phonetextfield");
		EnterTextValue(application, phone, "Phone", "phonetextfield");
		cleartext(application, "Fax", "faxtextfield");
		EnterTextValue(application, fax, "Fax", "faxtextfield");
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);
		compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
			
	}


	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected, String customerName)
			throws InterruptedException, DocumentException, IOException {
		
		waitForpageload();
		waitforPagetobeenable();
		
		Thread.sleep(1000);
		
			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
			Thread.sleep(3000);
			System.out.println("Mouser hovered on Manage Customer's Service");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CreateOrderServiceLink")));
			Thread.sleep(2000);
		
			Log.info("=== Create Order/Service navigated ===");
			
		//click on Next button to check mandatory messages	
			Clickon(getwebelement("//span[contains(text(),'Next')]"));
			Thread.sleep(5000);
			System.out.println("clicked on next button");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step : clicked on next button to verify the mandatory fields error messages");
			Log.info("Step : clicked on next button to verify the mandatory fields error messages");

			//Customer Error message
					boolean slctcustomer = getwebelement("//div[text()='Choose a customer']").isDisplayed();
					sa.assertTrue(slctcustomer, "Select Customer mandatory warning is not displayed ");
					String customermsg = getwebelement("//div[text()='Choose a customer']").getText();
					Thread.sleep(3000);
					System.out.println("Customter validation message displayed as : " + customermsg);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step :  validation message displayed as : " + customermsg);
					Log.info("Customer validation message displayed as : " + customermsg);
					Thread.sleep(3000);
					
				
			//Entering Customer name in Name field
					try {
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")), customerName);
						Thread.sleep(7000);
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")), "*");
						
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, "Existing Customer name has been Entered");
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Customer field is not available");
					}
			
					
		//Select Customer from dropdown
	    boolean customervalue=true;
	    boolean avaialbiity=false;
	    boolean customervalueDisplayed=false;
	    
	    addDropdownValues_commonMethod(application, "Choose a customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);
	    
	    
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Next button is selected after selecting customer");
		
			Thread.sleep(3000);

		
	}

	public void selectCustomertocreateOrderfromleftpane(String application, String ChooseCustomerToBeSelected,
			String customerName) throws InterruptedException, DocumentException, IOException {

		Thread.sleep(5000);

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		System.out.println("Mouser hovered on Manage Customer's Service");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CreateOrderServiceLink")));
		Thread.sleep(2000);
		
		
		Log.info("=== Create Order/Service navigated ===");
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")), customerName);
		Thread.sleep(7000);
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")), "*");
		Thread.sleep(3000);

		 addDropdownValues_commonMethod(application, "Choose a customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));
		Thread.sleep(1000);
	}

	public void verifyCreateOrderServiceFields(String application)
			throws InterruptedException, DocumentException, IOException {
		navigateToCreateOrderServicePage("CreateOrderService");

		CreateOrderService_Text = getwebelement(
				xml.getlocator("//locators/" + application + "/CreateOrderService_Text"));
		sa.assertTrue(CreateOrderService_Text.isDisplayed(), "CreateOrderService_Text  is not displayed");
//		sa.assertTrue(CreateOrderService_Text.isEnabled(), "CreateOrderService_Text is not disabled");
		Log.info("=== Create Order Service Text is displayed ===");

		ChooseCustomer_Select = getwebelement(xml.getlocator("//locators/" + application + "/ChooseCustomer_Select"));
		sa.assertTrue(ChooseCustomer_Select.isDisplayed(), "ChooseCustomer_Select dropdown is not displayed");
//		sa.assertTrue(ChooseCustomer_Select.isEnabled(), "ChooseCustomer_Select dropdown is not disabled");
		Log.info("=== Choose Customer dropdown is displayed ===");

		Next_Button = getwebelement(xml.getlocator("//locators/" + application + "/Next_Button"));
		sa.assertTrue(Next_Button.isDisplayed(), "Next_Button  is not displayed");
//		sa.assertTrue(Next_Button.isEnabled(), "Next_Button is not disabled");
		Log.info("=== Next_Button  is displayed ===");

		Log.info("=== Create Order/Service all fields Verified ===");
		
	}

	public void verifyCreateOrderDetailsInformation(String application)
			throws InterruptedException, DocumentException, IOException {

		// verify Name information
		String Name_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name_Text")));
		String Name_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name_Value")));
		Log.info(Name_Text + " : TextField value is displayed as : " + Name_Value);
		System.out.println(Name_Text + " : " + Name_Value);

		// verify MainDomain information
		String MainDomain_Text = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/MainDomain_Text")));
		String MainDomain_Value = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/MainDomain_Value")));
		Log.info(MainDomain_Text + " : TextField value is displayed as : " + MainDomain_Value);
		System.out.println(MainDomain_Text + "  " + MainDomain_Value);

		// verify Country information
		String Country_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Country_Text")));
		String Country_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Country_Value")));
		Log.info(Country_Text + " : TextField value is displayed as : " + Country_Value);
		System.out.println(Country_Text + " : " + Country_Value);

		// verify OCN information
		String OCN_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/OCN_Text")));
		String OCN_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/OCN_Value")));
		Log.info(OCN_Text + " : TextField value is displayed as : " + OCN_Value);
		System.out.println(OCN_Text + " : " + OCN_Value);

		// verify Reference information
		String Reference_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Reference_Text")));
		String Reference_Value = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Reference_Value")));
		Log.info(Reference_Text + " : TextField value is displayed as : " + Reference_Value);
		System.out.println(Reference_Text + " : " + Reference_Value);

		// verify Type information
		String Type_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Type_Text")));
		String Type_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Type_Value")));
		Log.info(Type_Text + " : TextField value is displayed as : " + Type_Value);
		System.out.println(Type_Text + " : " + Type_Value);

		// verify Technical Contact Name information
		String TechnicalContactName_Text = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/TechnicalContactName_Text")));
		String TechnicalContactName_Value = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/TechnicalContactName_Value")));
		Log.info(TechnicalContactName_Text + " : TextField value is displayed as : " + TechnicalContactName_Value);
		System.out.println(TechnicalContactName_Text + " : " + TechnicalContactName_Value);

		// verify Name2 information
		String Name2_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name2_Text")));
		String Name2_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Name2_Value")));
		Log.info(Name2_Text + " : TextField value is displayed as : " + Name2_Value);
		System.out.println(Name2_Text + " : " + Name2_Value);

		// verify Email information
		String Email_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Email_Text")));
		String Email_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Email_Value")));
		Log.info(Email_Text + " : TextField value is displayed as : " + Email_Value);
		System.out.println(Email_Text + " : " + Email_Value);

		// verify Phone information
		String Phone_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Phone_Text")));
		String Phone_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Phone_Value")));
		Log.info(Phone_Text + " : TextField value is displayed as : " + Phone_Value);
		System.out.println(Phone_Text + " : " + Phone_Value);

		// verify Fax information
		String Fax_Text = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Fax_Text")));
		String Fax_Value = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Fax_Value")));
		Log.info(Fax_Text + " : TextField value is displayed as : " + Fax_Value);
		System.out.println(Fax_Text + " : " + Fax_Value);

		Log.info("=== Create Order Detail all fields Verified ===");
		
	}

	public void select_ChooseCustomer(String application, String ChooseCustomerToBeSelected)
			throws IOException, InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ChooseCustomer_Select")));
		Thread.sleep(1000);
		WebElement el1 = driver.findElement(By.xpath("//div[contains(text(),'" + ChooseCustomerToBeSelected + "')]"));
		el1.click();
		Log.info("=== Choose Customer selected===");
	}

	public void select_OrderContractNumber(String application, String OrderContractNumberToBeSelected)
			throws IOException, InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/OrderContractNumber_Select")));
		Thread.sleep(1000);
		WebElement el1 = driver
				.findElement(By.xpath("(//div[contains(text(),'" + OrderContractNumberToBeSelected + "')])[1]"));
		el1.click();
		Log.info("=== Order Contract Number selected===");
	}

	

	public void select_OrderContractNumberAndServiceType(String application, String OrderContractNumberToBeSelected,
			String ServiceTypeToBeSelected) throws IOException, InterruptedException, DocumentException {

//		int OrderContractNumberToBeSelected=Integer.parseInt(OrderContractNumberToBeSelected1);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/OrderContractNumber_Select_build3")));
		Thread.sleep(1000);
		WebElement el1 = driver
				.findElement(By.xpath("(//div[contains(text(),'" + OrderContractNumberToBeSelected + "')])[1]"));
		el1.click();
		Log.info("=== Order Contract Number selected===");

		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceType_Select1_build3")));
		Thread.sleep(1000);
		System.out.println("cluicked on srvice type");

		System.out.println("-----has clicked service type------------");

		WebElement el2 = driver.findElement(By.xpath("//div[contains(text(),'" + ServiceTypeToBeSelected + "')]"));
		el2.click();
		Log.info("=== Service Type selected===");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

	}

	public void EnterDataForTheServiceSelected(String application, String ServiceSelected,
			String ServiceIdentificationNumber, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveNotification,
			String NotificationManagementValueForTransmissionLink, String Resellercode, String customAdmin,
			String SAnAdmin, String resellerAdmin, String NotifyManageTeam_VOIPAcsess, String selectpackage_VOIPAccess,
			String syslogEventViewcheckbox, String STRM_Ip_tag)
			throws InterruptedException, DocumentException, IOException {

		if (ServiceSelected.equals("Wholesale SIP Trunking")) {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);

			System.out.println("The values getffjkbfjcbjfsdffh: "
					+ Gettext(getwebelement("//div[contains(text(),'" + ServiceSelected + "')]")));

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Email")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Remark")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);

			if (PerformanceReporting.equals("yes")) {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Performancereporting")));
			}

			else {

				System.out.println("Performance reporting is not selected");
			}

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else if (ServiceSelected.equals("Transmission Link")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);

			Gettext(getwebelement(xml.getlocator("//locators/" + application + "/ServiceType")));

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Email")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Remark")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);

			if (ProactiveNotification.equals("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/proActivenotification_TransmissionLink")));
			}

			else {

				System.out.println("Pro active notification is not selected");
			}

			Select(getwebelement(
					xml.getlocator("//locators/" + application + "/NotificationManagementTeam_TransmissionLink")),
					NotificationManagementValueForTransmissionLink);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));
		}

		else if (ServiceSelected.equals("VOIP Access")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);

			Gettext(getwebelement(xml.getlocator("//locators/" + application + "/serviceType_VOIPaccess")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ResellerCode")), Resellercode);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Email")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Remark")));
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);

			Select(getwebelement(xml.getlocator("//locators/" + application + "/managementOptions_SyslogEventView")),
					selectpackage_VOIPAccess);
			Log.info("VOIP access package has been selected");

			WebElement ManagedServicecheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/managementOptions_managesservice"));
			if (ManagedServicecheckbox.isEnabled()) {

				Clickon(ManagedServicecheckbox);
				Log.info("ManagedServicecheckbox is selected");

			} else {
				Log.info("It is disable as expected");
			}

			WebElement SysLogEventViewcheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/managementOptions_SyslogEventView"));
			if (SysLogEventViewcheckbox.isEnabled()) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/managementOptions_SyslogEventView")));
				Log.info("syslog event view checkbox is selected");
			} else {
				Log.info("syslog is disabled as expected");
			}

			WebElement ServicestatusViewcheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/managementOptions_serviceStatusView"));
			if (ServicestatusViewcheckbox.isEnabled()) {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/managementOptions_serviceStatusView")));
				Log.info("Service status view check box is selected");
			} else {
				Log.info("Service status viwe checkbox is disabled as expected");
			}

			WebElement Routerconfigcheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/managementOptions_RouterConfigView"));
			if (Routerconfigcheckbox.isEnabled()) {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/managementOptions_RouterConfigView")));
				Log.info("Router config check box is selected");
			} else {
				Log.info("Router config check box is disabled as expected");
			}

			if (ProactiveNotification.equals("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/managementOptions_ProActiveNotify")));
				Log.info("Pro active notification check box is selected");
			}

			else {

				Log.info("Pro active notification is not selected");
			}

			WebElement Dialuseradmincheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/managementOptions_PerformnceReport"));
			if (Dialuseradmincheckbox.isEnabled()) {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/managementOptions_DialsUserAdmin")));
				Log.info("dial user admin check box is selected");
			} else {
				Log.info("dial user check box is disabled as expected");
			}

			if (PerformanceReporting.equals("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/managementOptions_PerformnceReport")));
				Log.info("performance reporting check box is selected");
			}

			else {

				System.out.println("Performance reporting is not selected");
			}

			Select(getwebelement(xml.getlocator("//locators/" + application + "/managementOptions_NotifyManageTeam")),
					NotifyManageTeam_VOIPAcsess);
			Log.info("Notification management dropdown has been selected as expected");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else if (ServiceSelected.equals("Number Hosting")) {

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);

//    	Gettext(getwebelement(xml.getlocator("//locators/"+application+"/ServiceType")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else if (ServiceSelected.equals("NGIN")) {

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);

//    	Gettext(getwebelement(xml.getlocator("//locators/"+application+"/ServiceType")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);

			if (customAdmin.equals("yes")) {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CustomAdmin")));
				Log.info("Custom admin check box is selected");
			} else {
				Log.info("Custom Admin check box is not selected");
			}

			if (SAnAdmin.equals("yes")) {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SANadmin")));
				Log.info("SAN admin check box is selected");
			} else {
				Log.info("SAN Admin check box is not selected");
			}

			if (SAnAdmin.equals("yes")) {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ResellerAdmin")));
				Log.info("Reseller admin check box is selected");
			} else {
				Log.info("Reseller Admin check box is not selected");
			}

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else if (ServiceSelected.equals("MDF/MVF/DI")) {

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);

			Gettext(getwebelement(xml.getlocator("//locators/" + application + "/ServiceType")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/STRM_IP_tag")), STRM_Ip_tag);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);

			if (ProactiveNotification.equals("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/managementOptions_ProActiveNotify")));
				Log.info("Pro active notification check box is selected");
			}

			else {

				Log.info("Pro active notification is not selected");
			}

			if (PerformanceReporting.equals("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/managementOptions_PerformnceReport")));
				Log.info("performance reporting check box is selected");
			}

			else {

				System.out.println("Performance reporting is not selected");
			}

			if (syslogEventViewcheckbox.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/managementOptions_SyslogEventView")));

				Log.info("syslog event view checkbox is selected");
			} else {
				Log.info("syslog event view is not selected");
			}

		}

	}

	public void VerifyTheValuesForTheServiceSelected(String application, String ServiceSelected,
			String ServiceIdentificationNumber, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveNotification,
			String NotificationManagementValueForTransmissionLink) {

		if (ServiceSelected.equals("Transmission Link")) {

			WebElement ForServiceIdentification = driver
					.findElement(By.xpath("//div[contains(text(),'" + ServiceIdentificationNumber + "')]"));
			sa.assertTrue(ForServiceIdentification.isDisplayed());

//    		  WebElement ForServicetype = driver.findElement(By.xpath("//div[contains(text(),'"+ServiceIdentificationNumber+"')]"));
//    		  sa.assertTrue(ForServicetype.isDisplayed());

			WebElement ForEmail = driver.findElement(By.xpath("//div[contains(text(),'" + Email + "')]"));
			sa.assertTrue(ForEmail.isDisplayed());

			WebElement ForRemark = driver.findElement(By.xpath("//div[contains(text(),'" + remark + "')]"));
			sa.assertTrue(ForEmail.isDisplayed());

			WebElement ForPhoneContact = driver.findElement(By.xpath("//div[contains(text(),'" + PhoneContact + "')]"));
			sa.assertTrue(ForPhoneContact.isDisplayed());

			WebElement ForProactiveNotification = driver
					.findElement(By.xpath("//div[contains(text(),'" + ProactiveNotification + "')]"));
			sa.assertTrue(ForProactiveNotification.isDisplayed());

			WebElement ForNotificationManagementValue = driver.findElement(
					By.xpath("//div[contains(text(),'" + NotificationManagementValueForTransmissionLink + "')]"));
			sa.assertTrue(ForProactiveNotification.isDisplayed());

			// div[contains(text(),'Test Service')]
		}

	}

	public void AddPEDevice(String application, String IMSlocation, String selectOrclicktogglebutttontocreateDevice,
			String name, String VendorModel, String ManagementAddress, String Snmpro, String Country, String City,
			String Site, String Premise) throws InterruptedException, DocumentException, IOException {

		System.out.println("----- Going to perform add PE device actions------------");

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddMASdevice")));

		if (selectOrclicktogglebutttontocreateDevice.equalsIgnoreCase("create")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Name")), name);

//			Select(getwebelement(xml.getlocator("//locators/"+application+"/VenderModel")), VendorModel);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddress")),
					ManagementAddress);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")), Snmpro);

//			Select(getwebelement(xml.getlocator("//locators/"+application+"/country")), Country);
//			
//			Select(getwebelement(xml.getlocator("//locators/"+application+"/city")), City);
//			
//			Select(getwebelement(xml.getlocator("//locators/"+application+"/site")), Site);
//			
//			Select(getwebelement(xml.getlocator("//locators/"+application+"/premise")), Premise);
//			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else {

			Select(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")), IMSlocation);
		}
	}


	public void SelectServiceType(String application, String OrderNumber, String ServiceTypeToBeSelected,
			String ordertype, String valuetobeselectedinorderdropdown)
			throws InterruptedException, DocumentException, IOException {


		scrolltoend();
		Thread.sleep(3000);

		if (ordertype.equalsIgnoreCase("new")) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "For new order");
			System.out.println("For new order");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/OrderContractNumbertoggleButton")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/OrderName")), OrderNumber);
			Log.info("Order name has been entered");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Order number value has been entered for New order");
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/createButton")));
			Thread.sleep(3000);
			
			
			
		//Order creation success message
			WebElement createordermsg=getwebelement(xml.getlocator("//locators/" + application + "/CreateOrderSuccessMessage"));
			
			scrollToTop();
			
			Thread.sleep(2000);
			
			
			boolean successMsgForOrdercreation=createordermsg.isDisplayed();
			if(successMsgForOrdercreation) {
				System.out.println("Order creation message is displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Order creation success message displays as : "+createordermsg.getText());
			}else {
				System.out.println("Success mesage for new order is not getting displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Success mesage for new order creation is not getting displayed");
			}

			ScrolltoElement(createordermsg);
			Thread.sleep(5000);
			
		} else {
			ExtentTestManager.getTest().log(LogStatus.INFO, "For existing order");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ordercontractNumberdropdown")));
			Clickon(getwebelement("//div[text()='" + valuetobeselectedinorderdropdown + "']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Existing order has been selected");

		}

		scrolltoend();
		Thread.sleep(3000);
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/servicetypedropdowntoclick")));
		Thread.sleep(1000);
		System.out.println("clicked on service type");

		System.out.println("-----has clicked service type dropdown------------");

		try {
		WebElement el2 = driver.findElement(By.xpath("//div[contains(text(),'" + ServiceTypeToBeSelected + "')]"));
		el2.click();
		Log.info("=== Service Type selected===");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, ServiceTypeToBeSelected+ " is not available under Service type dropdown ");
		}

		ExtentTestManager.getTest().log(LogStatus.INFO, "The service selected is: "+ServiceTypeToBeSelected);

	}

	
	public void selectsubtypeunderServiceTypeSelected(String application, String SelectSubService,
			String Interfacespeed, String modularmsp, String autoCreateService, String A_Endtechnologydropdown,
			String B_Endtechnologydropdown) throws InterruptedException, DocumentException, IOException {
		
		
		scrolltoend();
		Thread.sleep(1000);


		if (modularmsp.equalsIgnoreCase("no") && autoCreateService.equalsIgnoreCase("no")) {
			
			ExtentTestManager.getTest().log(LogStatus.PASS,"when'Modular msp' and 'Autocreate service' are not selected,   'Interface speed' value and 'Service subtype' value should be selected as mandatory ");

			// Select interface speed
				addDropdownValues_commonMethod(application, "Interface Speed", "InterfaceSpeed", Interfacespeed, xml);
	
	
	// select service sub type
	boolean serviceSubTypeAvailability=false;
	serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();

	if(serviceSubTypeAvailability) {
		ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
		System.out.println(" 'Service subtype mandatory dropdown is displaying as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
		Thread.sleep(3000);
		System.out.println("clicked on srvice type");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Service subtype dropdown has been selected");
		
		if((SelectSubService.equals("LANLink International")) || (SelectSubService.equals("LANLink Metro")) || SelectSubService.equals("LANLink National") ||
		         SelectSubService.equals("OLO - (GCR/EU)") || (SelectSubService.equals("Direct Fiber")) || (SelectSubService.equals("LANLink Outband Management"))){

		WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
		el2.click();
		Log.info("=== Service sub Type selected===");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
		}
		else{
			Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
			
			ExtentTestManager.getTest().log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
					+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
					+ "    1) Direct Fiber"
					+ "    2) LANLink International"
					+ "    3) LANLink Metro"
					+ "    4) LANLink National"
					+ "    5) Lanlink Outband management"
					+ "    6) OLO - (GCR/EU)");
//			driver.close();
		}

	}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
	}

			
//			 clickon(getwebelement(xml.getlocator("//locators/"+application+"/AvailableCircuits")));

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

		else if (modularmsp.equalsIgnoreCase("yes") && autoCreateService.equalsIgnoreCase("no")) {
			
			ExtentTestManager.getTest().log(LogStatus.INFO,"when 'Modular msp' is selected and 'Autocreateservice' is not selected, 'Service subtype' value should be selected as it is mandatory field ");
			
          try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Modular msp checkbox has been selected");
          }catch(Exception e) {
        	  e.printStackTrace();
        	  ExtentTestManager.getTest().log(LogStatus.FAIL, "Modular msp check box is not available");
        	  
          }

	// select service sub type
      	boolean serviceSubTypeAvailability=false;
		serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();
	
		if(serviceSubTypeAvailability) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
			System.out.println(" 'Service subtype mandatory dropdown is displaying as expected");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
			Thread.sleep(3000);
			System.out.println("clicked on srvice type");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Service subtype dropdown has been selected");
			
			if(SelectSubService.equals("LANLink International") || SelectSubService.equals("LANLink Metro") || SelectSubService.equals("LANLink National") ||
			         SelectSubService.equals("OLO - (GCR/EU)")){

			WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
			el2.click();
			Log.info("=== Service sub Type selected===");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
			}
			else{
				Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
				
				ExtentTestManager.getTest().log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
						+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
						+ "    1) LANLink International"
						+ "    2) LANLink Metro"
						+ "    3) LANLink National"
						+ "    4) OLO - (GCR/EU)");
//				driver.close();
			}

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
		}

//			 SendKeys(getwebelement(xml.getlocator("//locators/"+Application+"/AvailableCircuits")),
//			 Availablecircuits);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

		if (modularmsp.equalsIgnoreCase("no") && autoCreateService.equalsIgnoreCase("yes")) {
			
			
			ExtentTestManager.getTest().log(LogStatus.INFO, " 'Service subtype' should be selected as mandatory when 'AutocreateService' is selected, 'Modular msp' not selected");
			
			System.out.println("Only auto creta check box is selected");
			
			try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AutocreateServicecheckbox")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Autocreateservice' checkbox is selected ");
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Auto create service' checkbox is not available under 'Service' panel ");
			}

	//A end technology	
		addDropdownValues_commonMethod(application, "A-End Technology", "A_Endtechnology", A_Endtechnologydropdown, xml);
		
	//B end technology	
		addDropdownValues_commonMethod(application, "B-End Technology", "B_Endtechnology", B_Endtechnologydropdown, xml);
		
	//Interface speed
		addDropdownValues_commonMethod(application, "Interface Speed", "InterfaceSpeed", Interfacespeed, xml);
		

	// select service sub type
		
		boolean serviceSubTypeAvailability=false;
		serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();
	
		if(serviceSubTypeAvailability) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
			System.out.println(" 'Service subtype mandatory dropdown is displaying as expected");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
			Thread.sleep(1000);
			System.out.println("clicked on srvice type");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Service subtype dropdown has been selected");
			
			if((SelectSubService.equals("LANLink International")) || (SelectSubService.equals("LANLink Metro")) || SelectSubService.equals("LANLink National") ||
			         SelectSubService.equals("OLO - (GCR/EU)") || (SelectSubService.equals("Direct Fiber")) || (SelectSubService.equals("LANLink Outband Management"))){

			WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
			el2.click();
			Log.info("=== Service sub Type selected===");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Service sub type " +SelectSubService +" has been selected");
			}
			else{
				Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
				
				ExtentTestManager.getTest().log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
						+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
						+ "    1) Direct Fiber"
						+ "    2) LANLink International"
						+ "    3) LANLink Metro"
						+ "    4) LANLink National"
						+ "    5) Lanlink Outband management"
						+ "    6) OLO - (GCR/EU)");
				
//				driver.close();
			}

		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
		}
		scrolltoend();

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

		if (modularmsp.equalsIgnoreCase("yes") && autoCreateService.equalsIgnoreCase("yes")) {
			
			ExtentTestManager.getTest().log(LogStatus.INFO, " 'Service subtype' is mandatory when 'modular msp' and 'Autocreateservices' are selected");

			
		//modular msp	
			 try {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")));
					ExtentTestManager.getTest().log(LogStatus.PASS, "Modular msp checkbox has been selected");
	              }catch(Exception e) {
	            	  e.printStackTrace();
	            	  ExtentTestManager.getTest().log(LogStatus.FAIL, "Modular msp check box is not available under 'Service' panel");
	            	  
	              }

		//Auto create service	 
			 try {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AutocreateServicecheckbox")));
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Autocreateservice' checkbox is selected ");
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Auto create service' checkbox is not available under 'Service' panel ");
					}

			//A end technology	
				addDropdownValues_commonMethod(application, "A-End Technology", "A_Endtechnology", A_Endtechnologydropdown, xml);
				
			//B end technology	
				addDropdownValues_commonMethod(application, "B-End Technology", "B_Endtechnology", B_Endtechnologydropdown, xml);
				
			
		// select service sub type
			boolean serviceSubTypeAvailability=false;
			serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();
		
			if(serviceSubTypeAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
				System.out.println(" 'Service subtype mandatory dropdown is displaying as expected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
				Thread.sleep(3000);
				System.out.println("clicked on srvice type");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service subtype dropdown has been selected");
				
				if(SelectSubService.equals("LANLink International") || SelectSubService.equals("LANLink Metro") || SelectSubService.equals("LANLink National") ||
				         SelectSubService.equals("OLO - (GCR/EU)")){

				WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
				el2.click();
				Log.info("=== Service sub Type selected===");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
				}
				else{
					Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
					
					ExtentTestManager.getTest().log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
							+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
							+ "    1) LANLink International"
							+ "    2) LANLink Metro"
							+ "    3) LANLink National"
							+ "    4) OLO - (GCR/EU)");
//					driver.close();
				}

			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
			}
			
scrolltoend();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

	}

	public void VerifyFieldsForServiceSubTypeSelected(String application, String serviceType, String SelectSubService,
			String Interfacespeed, String proActivemonitoring,
			String modularmsp) throws InterruptedException, DocumentException, IOException {

		if (modularmsp.equalsIgnoreCase("no")) {
			if (Interfacespeed.equalsIgnoreCase("10GigE")) {
				Fieldvalidation_DirectFibre10G(application, serviceType, SelectSubService, Interfacespeed);

			} else if (Interfacespeed.equalsIgnoreCase("1GigE")) {
				Fieldvalidation_DirectFibre1G(application, serviceType, SelectSubService, Interfacespeed );
			}
		}
		else if (modularmsp.equalsIgnoreCase("yes")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'lanlink Outband Management' service will not occur when 'Modular Msp'checkbox is selected is selected");
		}

	}
	
	public void selectOrder(String application, String neworderSelection, String neworderno, String newrfireqno, String existingorderSelection,
			String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		
	//Create New order or select Existing Order	
		scrolltoend();
		if (neworderSelection.equalsIgnoreCase("YES")) {
			scrolltoend();
			Thread.sleep(1000);
			
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", neworderno , xml);
		} 
		
		else if (existingorderSelection.equalsIgnoreCase("YES")) {
			
			scrolltoend();
			click_commonMethod(application, "select order switch", "selectorderswitch", xml);
			addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber , xml);
			Log.info("=== Order Contract Number selected===");

			Thread.sleep(3000);

		} else {
			Log.info("Order not selected");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Step :Order not selected");
		}
	}

	public void verifyDataEntered(String ServiceSelected, String SelectSubService, String Interfacespeed,
			String ServiceIdentificationNumber, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceMonitoring, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType) {

		if (SelectSubService.equals("Direct Fiber")) {

			// Service Idenification
			try {
				Boolean flag = getwebelement("//div[contains(text(),'" + ServiceIdentificationNumber + "')]")
						.isDisplayed();

				String ServiceNumber = Gettext(
						getwebelement("//div[contains(text(),'" + ServiceIdentificationNumber + "')]"));

				if (ServiceNumber.isEmpty()) {
					System.out.println("service Identification is present");
					Log.info("Service Identification is not null and the values entered is: " + ServiceNumber);
				} else {

					Log.info("No values displaying under service Identification");
				}

			} catch (Exception e) {

				Log.info("No values displaying under service Identification");
			}

			// Service type
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + ServiceSelected + "')]").isDisplayed();

				String serviceType = Gettext(getwebelement("//div[contains(text(),'" + ServiceSelected + "')]"));

				if (serviceType.isEmpty()) {
					System.out.println("service Type is present");
					Log.info("Service Type is not null and the values entered is: " + serviceType);
				} else {

					Log.info("No values displaying under service Type");
				}

			} catch (Exception e) {

				Log.info("No values displaying under service type");
			}

			// Service subtype
			try {
				Boolean flag = getwebelement("//div[contains(text(),'" + SelectSubService + "')]").isDisplayed();

				String Subservice = Gettext(getwebelement("//div[contains(text(),'" + SelectSubService + "')]"));

				if (Subservice.isEmpty()) {
					System.out.println("Subservice value is present");
					Log.info("Subservice value is not null and the values entered is: " + Subservice);
				} else {

					Log.info("No values displaying under SubserviceType");
				}

			} catch (Exception e) {

				Log.info("No values displaying under service sub type");
			}

			// Interface Speed
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + Interfacespeed + "')]").isDisplayed();

				String interfacespeed = Gettext(getwebelement("//div[contains(text(),'" + Interfacespeed + "')]"));

				if (interfacespeed.isEmpty()) {
					System.out.println("interfaceSpeed is present");
					Log.info("interfaceSpeed value is not null and the values entered is: " + interfacespeed);
				} else {

					Log.info("No values displaying under interfaceSpeed");
				}

			} catch (Exception e) {

				Log.info("No values displaying under Interface Speed");
			}

			// Single Endpoint CPE
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + EndpointCPE + "')]").isDisplayed();

				String cpe = Gettext(getwebelement("//div[contains(text(),'" + EndpointCPE + "')]"));

				if (cpe.isEmpty()) {
					System.out.println("Single Endpoint CPE is present");
					Log.info("Single Endpoint CPE is not null and the values entered is: " + cpe);
				} else {

					Log.info("No values displaying under Single Endpoint CPE");
				}
			} catch (Exception e) {

				Log.info("No values displaying under Single End point CPE");
			}

			// Email
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + Email + "')]").isDisplayed();

				String email = Gettext(getwebelement("//div[contains(text(),'" + Email + "')]"));

				if (email.isEmpty()) {
					System.out.println("Email is present");
					Log.info("Email is not null and the values entered is: " + email);
				} else {

					Log.info("No values displaying under Email");
				}
			} catch (Exception e) {

				Log.info("No values displaying under Email");
			}

			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + PhoneContact + "')]").isDisplayed();

				String contact = Gettext(getwebelement("//div[contains(text(),'" + PhoneContact + "')]"));

				if (contact.isEmpty()) {
					System.out.println("Contact is present");
					Log.info("Email is not null and the values entered is: " + contact);
				} else {

					Log.info("No values displaying under Phone contact");
				}
			} catch (Exception e) {

				Log.info("No values displaying under Phone Contact");
			}

			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + remark + "')]").isDisplayed();

				String Remark = Gettext(getwebelement("//div[contains(text(),'" + remark + "')]"));

				if (Remark.isEmpty()) {
					System.out.println("Contact is present");
					Log.info("remark is not null and the values entered is: " + Remark);
				} else {

					Log.info("No values displaying under remark");
				}
			} catch (Exception e) {

				Log.info("No values displaying under remark");
			}

			// Performance reporting
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + PerformanceMonitoring + "')]").isDisplayed();

				String perFormance = Gettext(getwebelement("//div[contains(text(),'" + PerformanceMonitoring + "')]"));

				if (perFormance.isEmpty()) {
					System.out.println("Performance reporting is present");
					Log.info("Performance reporting is not null and the values entered is: " + perFormance);
				} else {

					Log.info("No values displaying under Performance Reporting");
				}
			} catch (Exception e) {

				Log.info("No values displaying under Performance Reporting");
			}

			// ProactiveMonitoring
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + ProactiveMonitoring + "')]").isDisplayed();

				String proactive = Gettext(getwebelement("//div[contains(text(),'" + ProactiveMonitoring + "')]"));

				if (proactive.isEmpty()) {
					System.out.println("PRo active Montoring is present");
					Log.info("Pro active Monitoring is not null and the values entered is: " + proactive);
				} else {

					Log.info("No values displaying under Pro active Monitoring");
				}
			} catch (Exception e) {

				Log.info("No values displaying under Pro active monitoring");
			}

			// DeliveryChannel
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + deliveryChannel + "')]").isDisplayed();

				String delivery = Gettext(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));

				if (delivery.isEmpty()) {
					System.out.println("Delivery channel value is present");
					Log.info("Delivery channel value is not null and the values entered is: " + delivery);
				} else {

					Log.info("No values displaying under delivery channel");
				}
			} catch (Exception e) {

				Log.info("No values displaying under delivery channel");
			}

			// ManagementOrder
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + ManagementOrder + "')]").isDisplayed();

				String order = Gettext(getwebelement("//div[contains(text(),'" + ManagementOrder + "')]"));

				if (order.isEmpty()) {
					System.out.println("management order value is present");
					Log.info("management Order value is not null and the values entered is: " + order);
				} else {

					Log.info("No values displaying under management Order");
				}
			} catch (Exception e) {

				Log.info("No values displaying under management Order");
			}

			// vpnTopology
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + vpnTopology + "')]").isDisplayed();

				String vpn = Gettext(getwebelement("//div[contains(text(),'" + vpnTopology + "')]"));

				if (vpn.isEmpty()) {
					System.out.println("VPN Topology value is present");
					Log.info("VPN Topology value is not null and the values entered is: " + vpn);
				} else {

					Log.info("No values displaying under VPN Topology");
				}
			} catch (Exception e) {

				Log.info("No values displaying under VPN Topology");
			}

			// CircuitReference
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + CircuitReference + "')]").isDisplayed();

				String circuitref = Gettext(getwebelement("//div[contains(text(),'" + CircuitReference + "')]"));

				if (circuitref.isEmpty()) {
					System.out.println("Circuit reference value is present");
					Log.info("circuit reference is not null and the values entered is: " + circuitref);
				} else {

					Log.info("No values displaying under Circuit reference");
				}
			} catch (Exception e) {

				Log.info("No values displaying under Circuit reference");
			}

			// CircuitType
			try {

				Boolean flag = getwebelement("//div[contains(text(),'" + CircuitType + "')]").isDisplayed();

				String circuitype = Gettext(getwebelement("//div[contains(text(),'" + CircuitType + "')]"));

				if (circuitype.isEmpty()) {
					System.out.println("Circuit Type value is present");
					Log.info("circuit Type is not null and the values entered is: " + circuitype);
				} else {

					Log.info("No values displaying under Circuit Type");
				}
			} catch (Exception e) {

				Log.info("No values displaying under Circuit type");
			}

		}

	}

	boolean customername;

	public void verifychoosecustomer(String application, String name, String customer)
			throws InterruptedException, IOException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		Thread.sleep(8000);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Next Button");
		Log.info("=== Clicked on Next Button ===");

		// WebElement ele=getwebelement(xml.getlocator("//locators/" + application +
		// "/choosocustomerwarningmsg"));
		boolean choosocustomerwarningmsg = getwebelement(
				xml.getlocator("//locators/" + application + "/choosocustomerwarningmsg")).isDisplayed();
		sa.assertTrue(choosocustomerwarningmsg, "next button is displayed");
		System.out.println("choose customer is required message is displayed");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : 'choose customer is required' message is displayed");
		Log.info("=== 'choose customer is required' message is displayed ===");
		// logStatus(ele, "'choose customer is required' message is displayed", "'choose
		// customer is required' message is not displayed ");

		/*
		 * SendKeys(getwebelement(xml.getlocator("//locators/" + application +
		 * "/nametextfield")), "n"); Thread.sleep(2000);
		 * SendKeys(getwebelement(xml.getlocator("//locators/" + application +
		 * "/nametextfield")), "h"); Thread.sleep(2000);
		 * SendKeys(getwebelement(xml.getlocator("//locators/" + application +
		 * "/nametextfield")), "1"); Thread.sleep(2000);
		 * SendKeys(getwebelement(xml.getlocator("//locators/" + application +
		 * "/nametextfield")), "1"); Thread.sleep(5000);
		 *
		 */

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/nametextfield")), name);
		Thread.sleep(5000); // Shwetha

		System.out.println("Entered Customer Name is : " + name);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Customer Name is : " + name);
		Log.info("Entered Customer Name is : " + name);
		Thread.sleep(5000);

		/*
		 * SendKeys(getwebelement(xml.getlocator("//locators/" + application +
		 * "/choosecustomerdropdown")), customer); Thread.sleep(5000);
		 * System.out.println("Entered customer Name is : " + customer);
		 */

		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/choosecustomerdropdown")));
		Thread.sleep(5000);
		System.out.println("Clicked on customer dropdown");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on customer dropdown");
		Log.info("=== Clicked on customer dropdown ===");

		try {
			customername = driver.findElement(By.xpath("//div[text()='No matches found']")).isDisplayed();
			Thread.sleep(5000);
			System.out.println("flag:" + customername);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			WebElement customername1 = driver.findElement(By.xpath("//div[text()='" + customer + "']"));
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
			System.out.println("Clicked on customer dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on customer dropdown");
			Log.info("=== Clicked on customer dropdown ===");
			// logStatus(ele2, "Customer Selected from dropdown"+ele2.getText(), "Customer
			// not Selected from dropdown");

			try {
				customername = driver.findElement(By.xpath("//div[text()='No matches found']")).isDisplayed();
			} catch (Exception e) {
				break;
			}

		}

		WebElement customername1 = driver.findElement(By.xpath("//div[text()='" + customer + "']"));
		Thread.sleep(5000);
		customername1.click();

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/nextbutton")));
		Thread.sleep(5000);
		System.out.println("Clicked on Next Button");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Next Button");
		Log.info("=== Clicked on Next Button ===");

	}


//Scroll to particular webelement
	public void ScrolltoElement(WebElement Element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", (Element));

	}

	public void Verifyfields(String application, String ServiceTypeToBeSelected, String modularMSP,
			String autoCreateService) throws InterruptedException, DocumentException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying list of fields under 'Create Order/ Service' Page");
		
		waitForpageload();
		waitforPagetobeenable();
		
		clickOnBankPage();
		
		Thread.sleep(3000);
		scrolltoend();
		
		click_commonMethod(application, "Next", "Next_Button", xml);
		Log.info("Step : clicked on next button to verify the mandatory fields error messages");
		
		
		//Create Order/Contract Number Error message
			warningMessage_commonMethod(application, "order_contractnumberErrmsg", "Order/Contract Number", xml);
		
		//Service Type Error message
			warningMessage_commonMethod(application, "servicetypeerrmsg", "Service Type", xml);
					
			
				String[] Servicetypelists = { "BM (Broadcast Media)", "Domain Management", "DSL", "FAX to Mail", "HSS",
						"IP Access (On-net/Offnet/EoS)", "IP Access Bundle", "IP Transit", "IP VPN", "IP Web/Mail", "LANLink",
						"MDF/MVF/DI", "NGIN", "Number Hosting", "Transmission Link", "Voice Line (V)", "VOIP Access",
						"Wholesale SIP Trunking" };
		
				System.out.println("order dropdown");
				
			//check whether Order dropdown is displayed	
				orderdopdown = getwebelement(xml.getlocator("//locators/" + application + "/orderdropdown")).isDisplayed();
				sa.assertTrue(orderdopdown, "Order/Contract Number dropdown is not displayed");
				System.out.println("order dropdown field is verified");
				
				
			//Select value under 'Service Type' dropdown
				addDropdownValues_commonMethod(application, "Service Type", "servicetypedropdowntoclick", ServiceTypeToBeSelected, xml);
				
				
				scrolltoend();
				Thread.sleep(5000);
				
			//Click on next button to check mandatory messages
				click_commonMethod(application, "Next", "Next_Button", xml);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : clicked on next button to verify the mandatory fields error messages");
				Log.info("Step : clicked on next button to verify the mandatory fields error messages");
		
				
			//Interface Speed Error message	
				warningMessage_commonMethod(application, "interfaceSpeedErrmsg", "Interface Speed", xml);
				
				
			//Service Sub Type Error message
				warningMessage_commonMethod(application, "servicesubtypeErrMsg", "Service Subtype", xml);
		
				
			//Modular msp checkbox	
				modularmspCheckbox = getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox"))
						.isDisplayed();
				sa.assertTrue(modularmspCheckbox, "modularmsp checkbox is displayed");
		
			//AutoCreate checkbox	
				autocreateservicecheckbox = getwebelement(
						xml.getlocator("//locators/" + application + "/AutocreateServicecheckbox")).isDisplayed();
				sa.assertTrue(autocreateservicecheckbox, "Auto create check box is not displayed");
				
			
			for(int i=0; i<4; i++) {
				
				if(i==0) {
					
					ExtentTestManager.getTest().log(LogStatus.INFO, "When btoh Modular msp and Autocreate Service is not selected, list of fields should occur:"
							+ "1) Interface speed dropdown"
							+ "2) Service Subtype dropdown"
							+ "3) Available circuit dropdown");
						
						verifyinterfaceSpeeddropdown(application);
		
						verifyservicesubtypesdropdownwhenMSPandAutoCreatenotslected(application);
		
						verifyavailablecircuitdropdown(application);
		
				}
			
				
				else if(i==1) {
					
					ExtentTestManager.getTest().log(LogStatus.INFO, "When 'Modular msp' is selected but 'Autocreate Service' is not selected, list of fields should occur:"
							+ "1) Service Subtype dropdown"
							+ "2) Available circuit dropdown");
		
						click_commonMethod(application, "Modular MSP checkbox", "modularmspcheckbox", xml);
		
						verifyservicesubtypesdropdownwhenMSPaloneselected(application);
		
						verifyavailablecircuitdropdown(application);
					
				}
				else if(i==2) {
					
					ExtentTestManager.getTest().log(LogStatus.INFO, "When 'Modular msp' is not selected but 'Autocreate Service' is selected, list of fields should occur: "
							+ "1) A_End technology dropdown"
							+ "2) B_End technology dropdown"
							+ "3) Interface speeed dropdown"
							+ "4) Service Subtype dropdown"
							+ "5) Available circuit dropdown");
						
						click_commonMethod(application, "Modular MSP checkbox", "modularmspcheckbox", xml);
						Thread.sleep(2000);
						
						click_commonMethod(application, "AutoCreate Service", "AutocreateServicecheckbox", xml);
						Thread.sleep(1000);
		
						verifyA_Endtechnologydropdown(application);
		
						verifyB_Endtechnologydropdowb(application);
		
						verifyinterfaceSpeeddropdown(application);
		
						verifyservicesubtypesdropdownwgenAutoCreatealoneselected(application);
		
						verifyavailablecircuitdropdown(application);
		
				}
				else if(i==3) {
					
					ExtentTestManager.getTest().log(LogStatus.INFO, "When both 'Modular msp' and 'Autocreate Service' is selected, list of fields should occur:"
							+ "1) A_End technology"
							+ "2) B_End technology "
							+ "1) Service Subtype dropdown"
							+ "2) Available circuit dropdown");
					
						click_commonMethod(application, "Modular MSP checkbox", "modularmspcheckbox", xml);
						Thread.sleep(1000);
						
						verifyA_Endtechnologydropdown(application);
		
						verifyB_Endtechnologydropdowb(application);
		
						verifyservicesubtypesdropdownwhenMSPandAutoCreateselected(application);
		
						verifyavailablecircuitdropdown(application);
				}
			}
			
				
			}

	public void verifyinterfaceSpeeddropdown(String application) throws InterruptedException, DocumentException {
		// verify the list of interfaceSpeed
		try {

			String[] interfacelist = { "1GigE", "10GigE" };

			interfacespeeddropdown = getwebelement(xml.getlocator("//locators/" + application + "/InterfaceSpeed"))
					.isDisplayed();
			sa.assertTrue(interfacespeeddropdown, "Interface speed dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceSpeed")));

			List<WebElement> listofinterfacespeed = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement interfacespeed : listofinterfacespeed) {

				boolean match = false;
				for (int i = 0; i < interfacelist.length; i++) {
					if (interfacespeed.getText().equals(interfacelist[i])) {
						match = true;
						Log.info("interface speeds : " + interfacespeed.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS, "interface speeds : " + interfacespeed.getText());
						sa.assertTrue(match);
					}
				}

			}

		} catch (AssertionError error) {

			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Interface speed' dropdown under Create order detail page is not available");

		} catch (Exception e) {
			Log.info("dropdowns values in Interface speed are mismiatching under service type");
			System.out.println("dropdowns values in Interface speed are mismiatching under service type");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Interface speed dropdown values are not displaying as expected ");
		}

	}

	public void verifyservicesubtypesdropdownwhenMSPandAutoCreatenotslected(String application)
			throws InterruptedException, DocumentException {

		String[] servicesubtypelist = { "Direct Fiber", "LANLink International", "LANLink Metro", "LANLink National",
				"LANLink Outband Management", "OLO - (GCR/EU)" };

		try {
			servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
					.isDisplayed();
			sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");

			// verify the list of service sub types
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));

			List<WebElement> listofServicesubtypes = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement servicesubtypes : listofServicesubtypes) {

				boolean match = false;
				for (int i = 0; i < servicesubtypelist.length; i++) {
					if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
						match = true;
						Log.info("service sub types : " + servicesubtypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS, "service sub types : " + servicesubtypes.getText());
						sa.assertTrue(match);
					}
				}

			}

		} catch (AssertionError error) {

			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Service subtypes' dropdown is not available");

		} catch (Exception e) {
			Log.info("Dropdown values in Service subtypes are mismatching");
			System.out.println("Dropdown values in Service subtypes are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Dropdown values in Service subtypes are mismatching");
		}

	}

	public void verifyservicesubtypesdropdownwhenMSPaloneselected(String application)
			throws InterruptedException, DocumentException {

		try {
			String[] servicesubtypelist = { "LANLink International", "LANLink Metro", "LANLink National",
					"OLO - (GCR/EU)" };

			servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
					.isDisplayed();
			sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");

			// verify the list of service sub types
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));

			List<WebElement> listofServicesubtypes = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement servicesubtypes : listofServicesubtypes) {

				boolean match = false;
				for (int i = 0; i < servicesubtypelist.length; i++) {
					if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
						match = true;
						Log.info("service sub types : " + servicesubtypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS, "service sub types : " + servicesubtypes.getText());
						sa.assertTrue(match);
					}
				}

			}
		} catch (AssertionError error) {

			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Service subtype' dropdown under create order detail page is not available");

		} catch (Exception e) {

			Log.info("Dropdown values inside service subtypes are mismatching");
			System.out.println("Dropdown values inside service subtypes are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Dropdown values inside service subtypes are mismatching");
		}

	}

	public void verifyservicesubtypesdropdownwgenAutoCreatealoneselected(String application)
			throws InterruptedException, DocumentException {

		try {
			String[] servicesubtypelist = { "Direct Fiber", "LANLink International", "LANLink Metro",
					"LANLink National", "LANLink Outband Management", "OLO - (GCR/EU)" };

			servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
					.isDisplayed();
			sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");

			// verify the list of service sub types
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));

			List<WebElement> listofServicesubtypes = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement servicesubtypes : listofServicesubtypes) {

				boolean match = false;
				for (int i = 0; i < servicesubtypelist.length; i++) {
					if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
						match = true;
						Log.info("service sub types : " + servicesubtypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS, "service sub types : " + servicesubtypes.getText());
						sa.assertTrue(match);
					}
				}

			}
		} catch (AssertionError error) {

			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'Service subtype' dropdown under create order detail page is not available");

		} catch (Exception e) {
			Log.info("Dropdown values inside service subtypes are mismatching");
			System.out.println("Dropdwon values inside service subtypes are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Dropdwon values inside service subtypes are mismatching");
		}

	}

	public void verifyservicesubtypesdropdownwhenMSPandAutoCreateselected(String application)
			throws InterruptedException, DocumentException {

		try {
			String[] servicesubtypelist = { "LANLink International", "LANLink Metro", "LANLink National",
					"OLO - (GCR/EU)" };

			servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
					.isDisplayed();
			sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");

			// verify the list of service sub types
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));

			List<WebElement> listofServicesubtypes = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement servicesubtypes : listofServicesubtypes) {

				boolean match = false;
				for (int i = 0; i < servicesubtypelist.length; i++) {
					if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
						match = true;
						Log.info("service sub types : " + servicesubtypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"service sub types are : " + servicesubtypes.getText());
						sa.assertTrue(match);
					}
				}

			}

		} catch (AssertionError error) {

			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'Service subtype' dropdown under create order detail page is not available");

		} catch (Exception e) {
			Log.info("Dropdwon values inside Service subtypes are mismatching");
			System.out.println("Dropdwon values inside Service subtypes are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Dropdwon values inside Service subtypes are mismatching");
		}

	}

	public void verifyavailablecircuitdropdown(String application) throws InterruptedException, DocumentException {
		try {
			availablecircuitsdropdown = getwebelement(
					xml.getlocator("//locators/" + application + "/AvailableCircuits")).isDisplayed();

			sa.assertTrue(availablecircuitsdropdown, "available circuit dropdown is not displayed");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Available circuit dropdown is displayed");

		} catch (AssertionError e) {
			Log.info("Available circuit dropdown under servicetype got failed");
			System.out.println("Available circuit dropdown under servicetype got failed");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Available circuit dropdown is not available under create order detail page");
		}
	}

	public void verifyA_Endtechnologydropdown(String application) throws InterruptedException, DocumentException {

		try {
			String[] A_endTechnolnogylist = { "Atrica", "MMSP", "Ethernet over Fibre" };

			A_EndTechnolnogy = getwebelement(xml.getlocator("//locators/" + application + "/A_Endtechnology"))
					.isDisplayed();
			sa.assertTrue(A_EndTechnolnogy, "A-End technolnogy dropdown is not displayed");

			// verify the list of A-End technolnogies
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/A_Endtechnology")));

			List<WebElement> listofA_endTechnologies = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement A_endTechnolnogies : listofA_endTechnologies) {

				boolean match = false;
				for (int i = 0; i < A_endTechnolnogylist.length; i++) {
					if (A_endTechnolnogies.getText().equals(A_endTechnolnogylist[i])) {
						match = true;
						Log.info("A end technology values : " + A_endTechnolnogies.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"A end technology are : " + A_endTechnolnogies.getText());
						sa.assertTrue(match);
					}
				}

			}
		} catch (AssertionError error) {

			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" A-end technology  dropdown under create order detail page is not available");

		} catch (Exception e) {
			Log.info("Dropdwon values inside A-end technology are mismatching");
			System.out.println("Dropdwon values inside A-end technology are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Dropdwon values inside A-end technology are mismatching");
		}

	}

	public void verifyB_Endtechnologydropdowb(String application) throws InterruptedException, DocumentException {

		try {
			String[] B_endTechnolnogylist = { "Atrica", "MMSP", "Ethernet over Fibre" };

			B_Endtechnolnogy = getwebelement(xml.getlocator("//locators/" + application + "/B_Endtechnology"))
					.isDisplayed();
			sa.assertTrue(B_Endtechnolnogy, "B-End technolnogy dropdown is not displayed");

			// verify the list of A-End technolnogies
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/B_Endtechnology")));

			List<WebElement> listofB_endTechnologies = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement B_endTechnolnogies : listofB_endTechnologies) {

				boolean match = false;
				for (int i = 0; i < B_endTechnolnogylist.length; i++) {
					if (B_endTechnolnogies.getText().equals(B_endTechnolnogylist[i])) {
						match = true;
						Log.info("B end technology values : " + B_endTechnolnogies.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"B end technologies are : " + B_endTechnolnogies.getText());
						sa.assertTrue(match);
					}
				}

			}
		} catch (AssertionError error) {

			error.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'B-end technology' dropdown under create order detail page is not available");

		} catch (Exception e) {
			Log.info("Dropdwon values inside B-end technology are mismatching");
			System.out.println("Dropdwon values inside B-end technology are mismatching");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Dropdwon values inside B-end technology are mismatching");
		}
	}

	public void DirectFibre_10G(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String Manageconnection,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType,
			String notificationManagement, String ENNIcheckBox)
			throws InterruptedException, IOException, DocumentException {

		scrollToTop();
		Thread.sleep(3000);

		// Service Identification
		createService_ServiceIdentification(application, ServiceIdentificationNumber);

		// End point CPE
		createService_singleEndPointCPE(application, EndpointCPE);

		// Email
		createSerivce_email(application, Email);

		// Phone Contact
		createService_phone(application, PhoneContact);

		// Remark
		createService_remark(application, remark);

		scrolltoend();
		Thread.sleep(2000);

		// Performance Reporting
		if (!PerformanceReporting.equalsIgnoreCase("null")) {

			if (PerformanceReporting.equalsIgnoreCase("yes")) {

				boolean perfrmReprtFieldcheck = getwebelement(
						xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();
				if (perfrmReprtFieldcheck) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Performance reporting' checkbox is displaying under 'Manage ment options' panel in 'Create Service' page as exepcted");
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Thread.sleep(5000);

					boolean prfrmReportselection = getwebelement(
							xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
					if (prfrmReportselection) {
						Log.info("performance monitoring check box is selected");
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Performance Reporting checkbox is selected as expected");
					} else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Performance Reporting checkbox is not selected");
					}

				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance Reporting' checkbox is not available");
				}
			} else {

				System.out.println("Performance Repoting is not selected");
				ExtentTestManager.getTest().log(LogStatus.PASS, "performance Reporting checkbox is not selected as expected");
			}
		}

		// Pro active Monitoring
		createService_proactivemonitoring(application, ProactiveMonitoring, notificationManagement);

		// Delivery Channel
		createService_deliveryChannel(application, deliveryChannel);

		// management Connection
		createService_managementOptions(application, Manageconnection);

		// ENNI checkbox
		addCheckbox_commonMethod(application, "ENNI_checkbox", "ENNI", ENNIcheckBox, "no", xml);

		scrolltoend();
		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void DirectFibre_1G(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String Manageconnection,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType,
			String notificationManagement, String perCocPerfrmReprt, String actelsBased, String standrdCir,
			String standrdEir, String prmiumCir, String prmiumEir, String ENNIcheckBox)
			throws InterruptedException, IOException, DocumentException {

		scrollToTop();
		Thread.sleep(3000);

		// Service Identification
		createService_ServiceIdentification(application, ServiceIdentificationNumber);

		// End point CPE
		createService_singleEndPointCPE(application, EndpointCPE);

		// Email
		createSerivce_email(application, Email);

		// Phone Contact
		createService_phone(application, PhoneContact);

		// Remark
		createService_remark(application, remark);

		scrolltoend();
		Thread.sleep(2000);

		
		// Pro active Monitoring
		createService_proactivemonitoring(application, ProactiveMonitoring, notificationManagement);

		// Delivery Channel
		createService_deliveryChannel(application, deliveryChannel);

		// management Connection
		createService_managementOptions(application, Manageconnection);

		// ENNI checkbox
		addCheckbox_commonMethod(application, "ENNI_checkbox", "ENNI", ENNIcheckBox, "no", xml);
		
		
		//Performance Reporting	
				if(!PerformanceReporting.equalsIgnoreCase("null")) {
					
					if (PerformanceReporting.equalsIgnoreCase("yes")) {
						
						boolean perfrmReprtFieldcheck=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();
						if(perfrmReprtFieldcheck) {
							ExtentTestManager.getTest().log(LogStatus.PASS, " 'Performance reporting' checkbox is displaying under 'Manage ment options' panel in 'Create Service' page as exepcted");
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
							Thread.sleep(5000);
							
							boolean prfrmReportselection=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
							if(prfrmReportselection) {
								Log.info("performance monitoring check box is selected");
								ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting checkbox is selected as expected");
							}else {
								ExtentTestManager.getTest().log(LogStatus.FAIL, "Performance Reporting checkbox is not selected");
							}
							
							
						//Per CoS Performance Reporting chekcbox	
							boolean perCoSPrfrmReprtFieldcheck=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isDisplayed();
							if(perCoSPrfrmReprtFieldcheck) {
								ExtentTestManager.getTest().log(LogStatus.PASS, " 'Per CoS Performance Reporting' checkbox is displaying, when 'Performance reporting' checkbox is selected");
								if(perCocPerfrmReprt.equalsIgnoreCase("Yes")){
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));
									Thread.sleep(3000);
									
									boolean perCoSSelection=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isSelected();
									if(perCoSSelection) {
										ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting checkbox is selected as expected");
									}else {
										ExtentTestManager.getTest().log(LogStatus.FAIL, "Per CoS Performance Reporting checkbox is not selected");
									}
									
									
								//Actelis Based	
									boolean actelisbasedFieldcheck=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isDisplayed();
									if(actelisbasedFieldcheck) {
										ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checkbox is displaying, when 'Per CoS Perfoemance Reporting' checkbox is selected");
										if(actelsBased.equalsIgnoreCase("Yes")) {
											Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")));
											Thread.sleep(3000);
											
											boolean actelisSelection= getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isSelected();
											if(actelisSelection) {
												ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checkbox is selected as expected");
											}else {
												ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Actelis Based' checkbox is not selected" );
											}
										
											scrolltoend();
											Thread.sleep(1000);
											
										//Standard CIR Text Field
											createService_standardCIR(application, standrdCir);
											
										//Standard EIR Text Field
											createService_standardEIR(application, standrdEir);
											
											
										//Premium CIR Text Field
											createService_premiumCIR(application, prmiumCir);
											
										//Premium EIR Text Field
											createService_premiumEIR(application, prmiumEir);		
											
											
										}else {
											ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checkbox is not selected as expected");
										}
										
										
									}else {
										ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Actelis Based' checkbox is not displaying, when 'Per CoS Perfoemance Reporting' checkbox is selected");
									}
									
									
								}else {
									ExtentTestManager.getTest().log(LogStatus.PASS, " 'Per CoS Performance Reporting' checkbox is not selected as exepected");
								}
								
							
							}else {
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Per CoS Performance Rpeorting' checkbox is not displaying, when 'Performance reporting' checkbox is selected");
							}
							
						}else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance Reporting' checkbox is not available");
						}
						
						
						
					}
					else {

						System.out.println("Performance Repoting is not selected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"performance Reporting checkbox is not selected as expected");
						
					}
				}

		
		scrolltoend();
		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void verifydataEntered_DirectFibre10G(String application, String serviceype,
			String ServiceIdentificationNumber, String SelectSubService, String Interfacespeed, String EndpointCPE,
			String email, String PhoneContact, String remark, String PerformanceMonitoring, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String modularMSP, String notificationManagement,
			String Manageconnectiondropdown, String ENNIcheckBox)
			throws InterruptedException, IOException, DocumentException {

		WebElement orderPanel = getwebelement(
				xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);

		/**
		 * Service Panel
		 */
		// Service Identification
		verifyEnteredvalues("Service Identification", ServiceIdentificationNumber);

		// Service type
		verifyEnteredvalues("Service Type", serviceype);

		// Service Subtype
		verifyEnteredvalues("Service Subtype", SelectSubService);

		// Interface Speed
		verifyEnteredvalues("Interface Speed", Interfacespeed);

		// Single Endpoint CPE
//			verifyEnteredvalues("Single Endpoint CPE", EndpointCPE);
//			verifyEnteredvalues("Single Endpoint CPE", "No");

		// Email
		verifyEnteredvalueForEmail_serviceCreationpage("Email", email);

		// Phone Contact
		verifyEnteredvalues("Phone Contact", PhoneContact);

		// Modular MSP
		verifyEnteredvalues("Modular MSP", modularMSP);

		// Remark
		compareText(application, "Remark", "remark_viewPage", remark, xml);

		WebElement servicePanel = getwebelement(
				xml.getlocator("//locators/" + application + "/viewServicepage_Servicepanel"));
		ScrolltoElement(servicePanel);
		Thread.sleep(3000);

		/**
		 * Management Options panel
		 */

		// Delivery Channel
		verifyEnteredvalues("Delivery Channel", deliveryChannel);

		// Management Connection
		verifyEnteredvalues("Management Connection", Manageconnectiondropdown);

		// Proactive Monitoring
		verifyEnteredvalues("Proactive Monitoring", ProactiveMonitoring);

		if (ProactiveMonitoring.equalsIgnoreCase("yes")) {
			verifyEnteredvalues("Notification Management Team", notificationManagement);
		}

		// Performance Reporting
		verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);

		// ENNI checkbox
		verifyEnteredvalues("ENNI", ENNIcheckBox);

	}

	public void verifydataEntered_DirectFibre1G(String application, String serviceype,
			String ServiceIdentificationNumber, String SelectSubService, String Interfacespeed, String EndpointCPE,
			String Email, String PhoneContact, String remark, String PerformanceMonitoring, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String modularMSP, String perCocPerfrmReprt,
			String actelsBased, String standrdCir, String standrdEir, String prmiumCir, String prmiumEir,
			String notificationManagement, String Manageconnectiondropdown, String ENNIcheckBox)
			throws InterruptedException, IOException, DocumentException {

		WebElement orderPanel = getwebelement(
				xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);

		/**
		 * Service Panel
		 */
		// Service Identification
		verifyEnteredvalues("Service Identification", ServiceIdentificationNumber);

		// Service type
		verifyEnteredvalues("Service Type", serviceype);

		// Service Subtype
		verifyEnteredvalues("Service Subtype", SelectSubService);

		// Interface Speed
		verifyEnteredvalues("Interface Speed", Interfacespeed);

		// Single Endpoint CPE
			verifyEnteredvalues("Single Endpoint CPE", EndpointCPE);

		// Email
		verifyEnteredvalueForEmail_serviceCreationpage("Email", Email);

		// Phone Contact
		verifyEnteredvalues("Phone Contact", PhoneContact);

		// Modular MSP
		verifyEnteredvalues("Modular MSP", modularMSP);

		// Remark
		compareText(application, "Remark", "remark_viewPage", remark, xml);

		WebElement servicePanel = getwebelement(
				xml.getlocator("//locators/" + application + "/viewServicepage_Servicepanel"));
		ScrolltoElement(servicePanel);
		Thread.sleep(3000);

		/**
		 * Management Options panel
		 */

		// Delivery Channel
		verifyEnteredvalues("Delivery Channel", deliveryChannel);

		// Management Connection
		verifyEnteredvalues("Management Connection", Manageconnectiondropdown);

		// Proactive Monitoring
		verifyEnteredvalues("Proactive Monitoring", ProactiveMonitoring);

		if (ProactiveMonitoring.equalsIgnoreCase("yes")) {
			verifyEnteredvalues("Notification Management Team", notificationManagement);
		}

		//Performance Reporting
		if(PerformanceMonitoring.equalsIgnoreCase("no")){
		
			verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
		}
		
			
			if((PerformanceMonitoring.equalsIgnoreCase("yes")) & (perCocPerfrmReprt.equalsIgnoreCase("no")) & (actelsBased.equalsIgnoreCase("no"))) {
				
				verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
				
				verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
				
			}
			
			
			else if((PerformanceMonitoring.equalsIgnoreCase("yes")) & (perCocPerfrmReprt.equalsIgnoreCase("yes")) & (actelsBased.equalsIgnoreCase("no"))) {
				
				verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
				
				verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
				
				verifyEnteredvalues("Actelis Based", actelsBased);
			}
			
			
			else if((PerformanceMonitoring.equalsIgnoreCase("yes")) & (perCocPerfrmReprt.equalsIgnoreCase("yes")) & (actelsBased.equalsIgnoreCase("yes"))) {
				
				verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
				
				verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
				
				verifyEnteredvalues("Actelis Based", actelsBased);
				
			//Standard 	
				compareText(application, "Header Name for 1st row", "standardHeaderName_viewPage", "Standard", xml);
				
				compareText(application, "Standard CIR", "standardCIRvalue_viewPage", standrdCir, xml);
				
				compareText(application, "Standard EIR", "standardEIRvalue_viewPage", standrdEir, xml);
				
			//Premium
				compareText(application, "Header Name for 2nd row", "PremisumHeaderName_viewPage", "Premium", xml);
				
				compareText(application, "Premium CIR", "premiumCIRvalue_viewPage", prmiumCir, xml);
				
				compareText(application, "Premium EIR", "premiumEIRvalue_viewPage", prmiumEir, xml);
				
				
			}
		

		// ENNI checkbox
		verifyEnteredvalues("ENNI", ENNIcheckBox);

	}

	public void Fieldvalidation_DirectFibre10G(String application, String serviceType, String SelectSubService,
			String Interfacespeed) throws InterruptedException, DocumentException, IOException {

		String[] deliverychannel = { "--", "Retail", "Reseller", "WLC", "WLEC", "CES Solutions" };

		String[] notifyManagement = { "DNA" };

		boolean serviceIdentificationField, ServiceType, ServiceSubtype, interfacespeedvalue, singleendpointCPE, email,
				phone, remark, performancereoprting, deliveryChanel, proactiveMonitor, Managementorder, okButton,
				cancelButton;

		try {

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'OK' butto to verify mandatory warning Messages");
			Thread.sleep(3000);

			
		// management Connection Error Message
			warningMessage_commonMethod(application, "manageConnection_warningMesage", "Management Connection", xml);
			

		// Service Identification Error message
			warningMessage_commonMethod(application, "serviceIdentificationerrmsg", "Service Identification", xml);
			
			
			// service Identification
			serviceIdentificationField = getwebelement(
					xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
			sa.assertTrue(serviceIdentificationField, "Service identification field is not displayed");
			if (serviceIdentificationField) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" ' Service Identfication' mandatory field is displaynig under 'Add Service' page as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" ' Service Identfication' mandatory field is not available under 'Add Service' page");
			}

			// Service type
			ServiceType = getwebelement(xml.getlocator("//locators/" + application + "/ServiceType")).isDisplayed();
			sa.assertTrue(ServiceType, "Service type is not displayed");
			if (ServiceType) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'LANLink' is displying under 'Service type' as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'LANKLink' is not displying under 'Service type'");
			}

			// Service subtype
			ServiceSubtype = getwebelement("//div[contains(text(),'" + SelectSubService + "')]").isDisplayed();
			sa.assertTrue(ServiceSubtype, "Service subtype is not displayed");
			if (ServiceSubtype) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						SelectSubService + " is displying under 'Service Sub type' as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						SelectSubService + " is not displying under 'Service Sub type'");
			}

			// Interface speed
			interfacespeedvalue = getwebelement("//div[contains(text(),'" + Interfacespeed + "')]").isDisplayed();
			sa.assertTrue(interfacespeedvalue, "Interface speed dropdown is not displaying as expected");
			if (interfacespeedvalue) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						Interfacespeed + " is displying under 'Interface Speed' as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, Interfacespeed + " is not displying under 'Interface Speed'");
			}

			// Single endpoint cpe
			try {
				singleendpointCPE = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE"))
						.isDisplayed();
				sa.assertTrue(singleendpointCPE, "single End point CPE checkbox is disabled by default");
				if (singleendpointCPE) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Single endpoint cpe' field is displying under 'Create Service'page as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Single endpoint cpe' field is not available under 'Create Service' pag");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"'Single Endpoint CPE' checkbox is not available under 'Create Service' page");
			}

			// Email
			try {
				email = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
				sa.assertTrue(email, "email field is not displayed");
				if (email) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Email' field is displying under 'Create Service'page as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Email' field is not available under 'Create Service' pag");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Email' field is not available under 'Create Service' page");
			}

			// phone
			try {
				phone = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
				sa.assertTrue(phone, "phone contact field is not displayed");
				if (phone) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'phone' field is displying under 'Create Service'page as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'phone' field is not available under 'Create Service' page");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Phone' field is not available under 'Create Service' page");
			}

			// remark
			try {
				remark = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
				sa.assertTrue(remark, "remark field is not displayed");
				if (remark) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Remark' field is displying under 'Create Service'page as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Remark' field is not available under 'Create Service' page");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"'Remark' field is not available under 'Create Service' page");
			}

			scrolltoend();
			Thread.sleep(1000);

			//performance Reporting	
			 try {	
				performancereoprting = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox"))
						.isDisplayed();
				sa.assertTrue(performancereoprting,
						"performance monitoring checbox is not displayed and by default not selected as expected");
				if(performancereoprting) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'performance Reporting' checkbox is displying under 'Create Service'page as expected");
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'performance Reporting' checkbox is not available under 'Create Service' page");
				}
				
				boolean performancereoprtingselection = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
				if(performancereoprtingselection) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance Reporting' checkbox is selected under 'Management Options' panel in 'Create Service page'"); 
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "performance Reporting' checkbox is not selected under 'Management Options' panel in 'Create Service page as expected");
				}
			 }catch(Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, "'Performance reporting' checkbox is not available under 'Create Service' page");
				}
			
			// proactive monitoring
			proactiveMonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring"))
					.isDisplayed();
			sa.assertTrue(proactiveMonitor, "pro active monitoring checkbox is not displayed");
			if (proactiveMonitor) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'proactive monitoring' checkbox is displying under 'Create Service'page as expected");

				boolean proactiveMonitorselection = getwebelement(
						xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
				if (proactiveMonitorselection) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'proactive monitoring' checkbox is selected under 'Management Options' panel in 'Create Service page'");
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"proactive monitoring' checkbox is not selected under 'Management Options' panel in 'Create Service page as expected");

					// Notification Management Dropdown
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("Pro active monitoring check box is selected");
					Thread.sleep(3000);

					boolean notificationManagement = getwebelement(
							xml.getlocator("//locators/" + application + "/notificationmanagement")).isDisplayed();
					sa.assertTrue(notificationManagement,
							"Notification management dropdown is not displayed when proactive monitoring is selected");
					Log.info("Notification management dropdown gets displayed when proactive monitoring is selected");
					if (notificationManagement) {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								" 'Notification Management' dropdown is displaying under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						try {
							List<WebElement> listofnotificationmanagement = driver
									.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
							for (WebElement notificationmanagementtypes : listofnotificationmanagement) {

								boolean match = false;
								for (int i = 0; i < notifyManagement.length; i++) {
									if (notificationmanagementtypes.getText().equals(notifyManagement[i])) {
										match = true;
										Log.info("list of notification management are : "
												+ notificationmanagementtypes.getText());
										ExtentTestManager.getTest().log(LogStatus.PASS,
												"list of notification management are : "
														+ notificationmanagementtypes.getText());
									}
								}
								sa.assertTrue(match);

							}
						} catch (Exception e) {
							Log.info("Notification Management dropdown values are mismatching");
							e.printStackTrace();
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"  values in Notification management dropdown under Direct Fiber service subtype is not displaying as expected");
						}
					} else {
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								" 'Notification Management' dropdown is not available under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
					}
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'proactive monitoring' checkbox is not available under 'Create Service' page");
			}

			// delivery channel
			try {
				deliveryChanel = getwebelement(
						xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
				sa.assertTrue(deliveryChanel, "delivery channel dropdown is not displayed");
				if (deliveryChanel) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Delivery Channel' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
					try {
						List<WebElement> listofdeliverychannel = driver
								.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
						for (WebElement deliverychanneltypes : listofdeliverychannel) {

							boolean match = false;
							for (int i = 0; i < deliverychannel.length; i++) {
								if (deliverychanneltypes.getText().equals(deliverychannel[i])) {
									match = true;
									Log.info("list of delivery channel are : " + deliverychanneltypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,
											" List of Delivery channel dropdown values under Direct Fiber service subtype are: "
													+ deliverychanneltypes.getText());

								}
							}
							sa.assertTrue(match);
						}
					} catch (Exception e) {
						e.printStackTrace();
						Log.info("delivery channel dropdown values are mismatching");
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								"  values in delivery channel dropdowns under Direct Fiber service subtype are not displaying as expected");
					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Delivery Channel' dropdown is not avilable under 'Management options' panel in 'Create Service' page");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"'Delivery Channel' dropdown is not available under 'Create Service' page");
			}

			// Management Connection
			Managementorder = getwebelement(xml.getlocator("//locators/" + application + "/managementConnection"))
					.isDisplayed();
			sa.assertTrue(Managementorder, "Management Connection field is not displayed");
			if (Managementorder) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Management Connection' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")));

				List<WebElement> listofmanagementOrder = driver
						.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
				for (WebElement mnaagementOrdertypes : listofmanagementOrder) {

					System.out.println(
							"Available Management Connection name is : " + mnaagementOrdertypes.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Available Management Connection name is : "
							+ mnaagementOrdertypes.getText().toString());
					Log.info("Available Management Connection name is :" + mnaagementOrdertypes.getText().toString());

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Management Connection' dropdown is not available under 'Management options' panel in 'Create Service' page");
			}

			// ENNI checkbox
			verfyFields_ENNIcheckbox(application);

			scrolltoend();
			Thread.sleep(1000);

			// OK button
			okButton = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
			sa.assertTrue(okButton, "OK button is not displayed");

			// cancel button
			cancelButton = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
			sa.assertTrue(cancelButton, "Cancel button is not displayed");

			scrolltoend();
			Thread.sleep(3000);

			click_commonMethod(application, "Cancel", "cancelButton", xml);
			Thread.sleep(3000);

			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Fields are verified");
		} catch (AssertionError e) {
			e.printStackTrace();
//			ExtentTestManager.getTest().log(LogStatus.FAIL,
//					"validation failure for Lanlink Outband management service subtype");
		}

	}

	public void Fieldvalidation_DirectFibre1G(String application, String serviceType, String SelectSubService,
			String Interfacespeed)throws InterruptedException, DocumentException, IOException {

		String[] deliverychannel = { "--", "Retail", "Reseller", "WLC", "WLEC", "CES Solutions" };

		String[] notifyManagement = { "DNA" };

		boolean serviceIdentificationField, ServiceType, ServiceSubtype, interfacespeedvalue, singleendpointCPE, email,
				phone, remark, performancereoprting, deliveryChanel, proactiveMonitor, Managementorder, okButton,
				cancelButton;

		try {

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'OK' button to verify mandatory warning Messages");
			Thread.sleep(3000);

			
		// management Connection Error Message
			warningMessage_commonMethod(application, "manageConnection_warningMesage", "Management Connection", xml);
			

		// Service Identification Error message
			warningMessage_commonMethod(application, "serviceIdentificationerrmsg", "Service Identification", xml);

			// service Identification
			serviceIdentificationField = getwebelement(
					xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
			sa.assertTrue(serviceIdentificationField, "Service identification field is not displayed");
			if (serviceIdentificationField) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" ' Service Identfication' mandatory field is displaynig under 'Add Service' page as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" ' Service Identfication' mandatory field is not available under 'Add Service' page");
			}

			// Service type
			ServiceType = getwebelement(xml.getlocator("//locators/" + application + "/ServiceType")).isDisplayed();
			sa.assertTrue(ServiceType, "Service type is not displayed");
			if (ServiceType) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'LANLink' is displying under 'Service type' as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'LANKLink' is not displying under 'Service type'");
			}

			// Service subtype
			ServiceSubtype = getwebelement("//div[contains(text(),'" + SelectSubService + "')]").isDisplayed();
			sa.assertTrue(ServiceSubtype, "Service subtype is not displayed");
			if (ServiceSubtype) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						SelectSubService + " is displying under 'Service Sub type' as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						SelectSubService + " is not displying under 'Service Sub type'");
			}

			// Interface speed
			interfacespeedvalue = getwebelement("//div[contains(text(),'" + Interfacespeed + "')]").isDisplayed();
			sa.assertTrue(interfacespeedvalue, "Interface speed dropdown is not displaying as expected");
			if (interfacespeedvalue) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						Interfacespeed + " is displying under 'Interface Speed' as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, Interfacespeed + " is not displying under 'Interface Speed'");
			}

			// Single endpoint cpe
			try {
				singleendpointCPE = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE"))
						.isDisplayed();
				sa.assertTrue(singleendpointCPE, "single End point CPE checkbox is disabled by default");
				if (singleendpointCPE) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Single endpoint cpe' field is displying under 'Create Service'page as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Single endpoint cpe' field is not available under 'Create Service' pag");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"'Single Endpoint CPE' checkbox is not available under 'Create Service' page");
			}

			// Email
			try {
				email = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
				sa.assertTrue(email, "email field is not displayed");
				if (email) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Email' field is displying under 'Create Service'page as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Email' field is not available under 'Create Service' pag");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Email' field is not available under 'Create Service' page");
			}

			// phone
			try {
				phone = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
				sa.assertTrue(phone, "phone contact field is not displayed");
				if (phone) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'phone' field is displying under 'Create Service'page as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'phone' field is not available under 'Create Service' page");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Phone' field is not available under 'Create Service' page");
			}

			// remark
			try {
				remark = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
				sa.assertTrue(remark, "remark field is not displayed");
				if (remark) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Remark' field is displying under 'Create Service'page as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Remark' field is not available under 'Create Service' page");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"'Remark' field is not available under 'Create Service' page");
			}

			scrolltoend();
			Thread.sleep(3000);

			// proactive monitoring
			proactiveMonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring"))
					.isDisplayed();
			sa.assertTrue(proactiveMonitor, "pro active monitoring checkbox is not displayed");
			if (proactiveMonitor) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'proactive monitoring' checkbox is displaying under 'Create Service'page");

				boolean proactiveMonitorselection = getwebelement(
						xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
				if (proactiveMonitorselection) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'proactive monitoring' checkbox is selected under 'Management Options' panel in 'Create Service page'");
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"proactive monitoring' checkbox is not selected under 'Management Options' panel in 'Create Service page as expected");

					// Notification Management Dropdown
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("Pro active monitoring check box is selected");
					Thread.sleep(3000);

					boolean notificationManagement = getwebelement(
							xml.getlocator("//locators/" + application + "/notificationmanagement")).isDisplayed();
					sa.assertTrue(notificationManagement,
							"Notification management dropdown is not displayed when proactive monitoring is selected");
					Log.info("Notification management dropdown gets displayed when proactive monitoring is selected");
					if (notificationManagement) {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								" 'Notification Management' dropdown is displaying under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						try {
							List<WebElement> listofnotificationmanagement = driver
									.findElements(By.xpath("//span[@role='list']//div[@class='sc-ifAKCX oLlzc']"));
							for (WebElement notificationmanagementtypes : listofnotificationmanagement) {

								boolean match = false;
								for (int i = 0; i < notifyManagement.length; i++) {
									if (notificationmanagementtypes.getText().equals(notifyManagement[i])) {
										match = true;
										Log.info("list of notification management are : "
												+ notificationmanagementtypes.getText());
										ExtentTestManager.getTest().log(LogStatus.PASS,
												"list of notification management are : "
														+ notificationmanagementtypes.getText());
									}
								}
								sa.assertTrue(match);

							}
						} catch (Exception e) {
							Log.info("Notification Management dropdown values are mismatching");
							e.printStackTrace();
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"  values in Notification management dropdown under Direct Fiber service subtype is not displaying as expected");
						}
					} else {
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								" 'Notification Management' dropdown is not available under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
					}
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'proactive monitoring' checkbox is not available under 'Create Service' page");
			}

//delivery channel
			try {
				deliveryChanel = getwebelement(
						xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
				sa.assertTrue(deliveryChanel, "delivery channel dropdown is not displayed");
				if (deliveryChanel) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Delivery Channel' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
					try {
						List<WebElement> listofdeliverychannel = driver
								.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
						for (WebElement deliverychanneltypes : listofdeliverychannel) {

							boolean match = false;
							for (int i = 0; i < deliverychannel.length; i++) {
								if (deliverychanneltypes.getText().equals(deliverychannel[i])) {
									match = true;
									Log.info("list of delivery channel are : " + deliverychanneltypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,
											" List of Delivery channel dropdown values under Direct Fiber service subtype are: "
													+ deliverychanneltypes.getText());

								}
							}
							sa.assertTrue(match);
						}
					} catch (Exception e) {
						e.printStackTrace();
						Log.info("delivery channel dropdown values are mismatching");
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								"  values in delivery channel dropdowns under Direct Fiber service subtype are not displaying as expected");
					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Delivery Channel' dropdown is not avilable under 'Management options' panel in 'Create Service' page");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"'Delivery Channel' dropdown is not available under 'Create Service' page");
			}

		// Management Connection
			Managementorder = getwebelement(xml.getlocator("//locators/" + application + "/managementConnection"))
					.isDisplayed();
			sa.assertTrue(Managementorder, "Management Connection field is not displayed");
			if (Managementorder) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Management Connection' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")));

				List<WebElement> listofmanagementOrder = driver
						.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
				for (WebElement mnaagementOrdertypes : listofmanagementOrder) {

					System.out.println(
							"Available Management Connection name is : " + mnaagementOrdertypes.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Available Management Connection name is : "
							+ mnaagementOrdertypes.getText().toString());
					Log.info("Available Management Connection name is :" + mnaagementOrdertypes.getText().toString());

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Management Connection' dropdown is not available under 'Management options' panel in 'Create Service' page");
			}

			// ENNI checkbox
			verfyFields_ENNIcheckbox(application);

			scrolltoend();

			//performance Reporting			
					performancereoprting = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox"))
							.isDisplayed();
					sa.assertTrue(performancereoprting,
							"performance reporting checbox is not displayed under 'Create Service' page");
					if(performancereoprting) {
						ExtentTestManager.getTest().log(LogStatus.PASS, " 'performance Reporting' checkbox is displying under 'Create Service'page as expected");
					
					
					boolean performancereoprtingselection = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
					if(performancereoprtingselection) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance Reporting' checkbox is selected by default under 'Management Options' panel in 'Create Service page'"); 
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "performance Reporting' checkbox is not selected by default under 'Management Options' panel in 'Create Service page as expected");
				
						
					//Per CoS Performance Reporting
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
						Thread.sleep(3000);
						
						boolean perCosreopt=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isDisplayed();
						sa.assertTrue(perCosreopt,
								"Per CoS Performance Reporting checbox is not displayed under 'Create Service' page, when 'Performance Reporting' checkbox is selected");
						if(perCosreopt) {
							ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting checbox is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox is selected");
						
							boolean perCoSselection=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isSelected();
							if(perCoSselection) {
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Per CoS Performance Reporting' checkbox is selected by default under 'Management Options' panel in 'Create Service page'"); 
							}else {
								ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting' checkbox is not selected by default under 'Management Options' panel in 'Create Service page as expected");
						
						//Actelis Based    
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));	
								Thread.sleep(3000);
								boolean actelisBasedcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isDisplayed();
								sa.assertTrue(actelisBasedcheckbox,
										" 'Atelis Based' checbox is not displayed under 'Create Service' page, when 'Per CoS Performance Reporting' checkbox is selected");
								if(actelisBasedcheckbox) {
									ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checbox is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox and 'Per CoS Performance Reporting' checkbox is selected");
								
								boolean actelisBasedselection=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isSelected();
								if(actelisBasedselection) {
									ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Actelis Based' checkbox is selected by default under 'Management Options' panel in 'Create Service page'"); 
								}else {
									ExtentTestManager.getTest().log(LogStatus.PASS, "Actelis Based' checkbox is not selected by default under 'Management Options' panel in 'Create Service page as expected");
								
									
							//Bandwidth Options table
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")));	
								Thread.sleep(3000);
								
								scrolltoend();
								Thread.sleep(1000);
								
								//Standard CIR text field
								boolean standardCIR=getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).isDisplayed();
								sa.assertTrue(standardCIR,
										" 'Standard CIR' text field is not displayed under 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkboxes are selected");  
								if(standardCIR) {
									ExtentTestManager.getTest().log(LogStatus.PASS," 'Standard CIR' text field is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
								}else {
									ExtentTestManager.getTest().log(LogStatus.PASS," 'Standard CIR' text field is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
								}
								
								
								//Standard EIR Text field
								boolean standardEIR=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).isDisplayed();
								sa.assertTrue(standardEIR,
										" 'Standard EIR' text field is not displayed under 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkboxes are selected");  
								if(standardEIR) {
									ExtentTestManager.getTest().log(LogStatus.PASS," 'Standard EIR' text field is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
								}else {
									ExtentTestManager.getTest().log(LogStatus.PASS," 'Standard EIR' text field is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
								}
								
								
								//Premium CIR text field
								boolean premiumCIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).isDisplayed();
								sa.assertTrue(premiumCIR,
										" 'Premium CIR' text field is not displayed under 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkboxes are selected");  
								if(premiumCIR) {
									ExtentTestManager.getTest().log(LogStatus.PASS," 'Premium CIR' text field is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
								}else {
									ExtentTestManager.getTest().log(LogStatus.PASS," 'Premium CIR' text field is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
								}
								
								
								//Premium EIR Text field
								boolean premiumEIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).isDisplayed();
								sa.assertTrue(premiumEIR,
										" 'Premium EIR' text field is not displayed under 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkboxes are selected");  
								if(premiumEIR) {
									ExtentTestManager.getTest().log(LogStatus.PASS," 'Premium EIR' text field is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
								}else {
									ExtentTestManager.getTest().log(LogStatus.PASS," 'Premium EIR' text field is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting'checkbox, 'Per CoS Performance Reporting' checkbox and 'Actelis Based' checkbox are selected");
								}
								
						 }
						}else {
							ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Based' checbox is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox and 'Per CoS Performance Reporting' checkbox is selected");
						}
					 }
					}else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Per CoS Performance Reporting checbox is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox is selected");
			    	 }
					}
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'performance Reporting' checkbox is not available under 'Create Service' page");		
					}
					
		
			// OK button
			okButton = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
			sa.assertTrue(okButton, "OK button is not displayed");

			// cancel button
			cancelButton = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
			sa.assertTrue(cancelButton, "Cancel button is not displayed");

			scrolltoend();
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
			Thread.sleep(3000);

			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Fields are verified");
		} catch (AssertionError e) {
			e.printStackTrace();
//			ExtentTestManager.getTest().log(LogStatus.FAIL,
//					"validation failure for Lanlink Outband management service subtype");
		}

	}

	public void dataToBeEnteredOncesubservicesselected(String application, String SelectSubService,
			String Interfacespeed, String ServiceIdentificationNumber, String EndpointCPE, String Email,
			String PhoneContact, String remark, String PerformanceReporting, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String AggregateTraffic, String DeliveryChannelForselecttag,
			String modularmsp, String autoCreateService, String ENNIcheckBox, String Manageconnectiondropdown,
			String A_Endtechnologydropdown, String B_Endtechnologydropdown, String notificationManagement,
			String Performancereporting, String perCocPerfrmReprt, String actelsBased, String standrdCir,
			String standrdEir, String prmiumCir, String prmiumEir)
			throws InterruptedException, IOException, DocumentException {

		Thread.sleep(5000);

		if (modularmsp.equalsIgnoreCase("no")) {

			if (Interfacespeed.equalsIgnoreCase("10GigE")) {

				DirectFibre_10G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE,
						Email, PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel,
						Manageconnectiondropdown, vpnTopology, intermediateTechnology, CircuitReference, CircuitType,
						notificationManagement, ENNIcheckBox);

			}

			else if (Interfacespeed.equalsIgnoreCase("1GigE")) {

				DirectFibre_1G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE,
						Email, PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel,
						Manageconnectiondropdown, vpnTopology, intermediateTechnology, CircuitReference, CircuitType,
						notificationManagement, perCocPerfrmReprt, actelsBased, standrdCir, standrdEir, prmiumCir,
						prmiumEir, ENNIcheckBox);

			}
		}

		else if (modularmsp.equalsIgnoreCase("yes")) {

			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"When Modular MSP checkbox is selected, 'Lanlink Outband Management' service will not display");
			Thread.sleep(3000);

		}
	}

	public void EditTheservicesselected(String application, String SelectSubService, String Interfacespeed,
			String ServiceIdentificationNumber, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String intermediateTechnology, String CircuitReference, String CircuitType,
			String AggregateTraffic, String DeliveryChannelForselecttag, String modularmsp, String autoCreateService,
			String ENNIcheckBox, String Manageconnectiondropdown, String A_Endtechnologydropdown,
			String B_Endtechnologydropdown, String notificationManagement, String perCoSperformanceReport,
			String actelisBased, String standardCIR, String standardEIR, String premiumCIR, String premiumEIR) throws InterruptedException, IOException, DocumentException {

		WebElement orderPanel = getwebelement(
				xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);

		click_commonMethod(application, "Action_dropdown", "Editservice_actiondropdown", xml);
		
		click_commonMethod(application, "Edit", "Editservice_Editlink", xml);
		Thread.sleep(8000);
		
		waitForpageload();
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(3000);
		
		if (modularmsp.equalsIgnoreCase("no")) {

			if (Interfacespeed.equalsIgnoreCase("10GigE")) {

				Edit_DirectFibre10G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed,
						EndpointCPE, Email, PhoneContact, remark, PerformanceReporting, ProactiveMonitoring,
						deliveryChannel, ManagementOrder, intermediateTechnology, CircuitReference,
						notificationManagement, Manageconnectiondropdown,
						ENNIcheckBox);

			}

			else if (Interfacespeed.equalsIgnoreCase("1GigE")) {

				Edit_DirectFibre1G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed,
						EndpointCPE, Email, PhoneContact, remark, PerformanceReporting, ProactiveMonitoring,
						deliveryChannel, ManagementOrder, intermediateTechnology, CircuitReference,
						notificationManagement, perCoSperformanceReport, actelisBased,
						standardCIR, standardEIR, premiumCIR, premiumEIR, Manageconnectiondropdown, ENNIcheckBox);

				ExtentTestManager.getTest().log(LogStatus.PASS, "Values has been Edited");

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Edit Service for 'Lanlink OutbandManagement' cannot be performed as 'modular MSP' checkbox is selected");
		}

		Thread.sleep(3000);

	}

	

	/**
	 * success Message common method
	 * 
	 * @param application
	 * @throws InterruptedException
	 */
	public void verifysuccessmessage(String application, String expected) throws InterruptedException {

		waitforPagetobeenable();
		Thread.sleep(1000);

		scrollToTop();
		Thread.sleep(2000);
		try {

			boolean successMsg = getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert"))
					.isDisplayed();

			if (successMsg) {

				String alrtmsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage"))
								.getText();

				if (expected.contains(alrtmsg)) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "Message is verified. It is displaying as: " + alrtmsg);
					System.out.println("Message is verified. It is displaying as: " + alrtmsg);

				} else {

					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"Message is displaying and it gets mismatches. It is displaying as: " + alrtmsg
									+ " .The Expected value is: " + expected);
					System.out.println("Message is displaying and it gets mismatches. It is displaying as: " + alrtmsg);
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
				System.out.println(" Success Message is not displaying");
			}

		} catch (Exception e) {
			Log.info("failure in fetching success message ");
			ExtentTestManager.getTest().log(LogStatus.FAIL, expected + " Message is not displaying");
			System.out.println(expected + " message is not getting dislpayed");
		}

	}

	public void VerifydatenteredForServiceSubTypeSelected(String application, String serviceType,
			String SelectSubService, String Interfacespeed, String ServiceIdentificationNumber, String EndpointCPE,
			String Email, String PhoneContact, String remark, String PerformanceMonitoring, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String AggregateTraffic, String DeliveryChannelForselecttag,
			String modularmsp, String autoCreateService, String ENNIcheckBox, String Manageconnectiondropdown,
			String A_Endtechnologydropdown, String B_Endtechnologydropdown, String Performancereporting,
			String perCocPerfrmReprt, String actelsBased, String standrdCir, String standrdEir, String prmiumCir,
			String prmiumEir, String notificationManagement)
			throws InterruptedException, IOException, DocumentException {

		Thread.sleep(2000);
		try {

			if (modularmsp.equalsIgnoreCase("no")) {

				if (Interfacespeed.equalsIgnoreCase("10GigE")) {

					verifydataEntered_DirectFibre10G(application, serviceType, ServiceIdentificationNumber,
							SelectSubService, Interfacespeed, EndpointCPE, Email, PhoneContact, remark,
							PerformanceMonitoring, ProactiveMonitoring, deliveryChannel, ManagementOrder, vpnTopology,
							intermediateTechnology, CircuitReference, CircuitType, modularmsp, notificationManagement,
							Manageconnectiondropdown, ENNIcheckBox);

				}

				else if (Interfacespeed.equalsIgnoreCase("1GigE")) {

					verifydataEntered_DirectFibre1G(application, serviceType, ServiceIdentificationNumber,
							SelectSubService, Interfacespeed, EndpointCPE, Email, PhoneContact, remark,
							PerformanceMonitoring, ProactiveMonitoring, deliveryChannel, ManagementOrder, vpnTopology,
							intermediateTechnology, CircuitReference, CircuitType, modularmsp, perCocPerfrmReprt,
							actelsBased, standrdCir, standrdEir, prmiumCir, prmiumEir, notificationManagement,
							Manageconnectiondropdown, ENNIcheckBox);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Lanlink OutbandManagement' service will not occur when 'modular MSP' is selected");
			}

			

		} catch (AssertionError e) {
			Log.info("validation failed for verify Direct Fiber service subtype page ");
			e.printStackTrace();
		}
	}

	public void addsiteorder(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode, String devicename, String nonterminatepoinr, String Protected,
			String newcityselection, String existingcityselection, String existingsiteselection,
			String newsiteselection, String interfaceSpeed)
			throws InterruptedException, DocumentException, IOException {

		// Existing Country
		if (country.equalsIgnoreCase("null")) {

			ExtentTestManager.getTest().log(LogStatus.FAIL, "Country is a mandatory field and the value is not provided ");
		} else {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")));
			Clickon(getwebelement("//div[text()='" + country + "']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, country + " has been selected under 'Country' dropdown");
		}

		// Existing City
		if (existingcityselection.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_City")));
			Clickon(getwebelement("//div[text()='" + city + "']"));

			ExtentTestManager.getTest().log(LogStatus.PASS, city + " is selected under Device Xng City dropdown");
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Existing City is not selected");
		}

		// new City
		if (newcityselection.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
			Thread.sleep(3000);

			// City name
			if (xngcityname.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"City name field is a mandatory field and the value is not provided");
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname")),
						xngcityname);
				ExtentTestManager.getTest().log(LogStatus.PASS, xngcityname + " is entered in City name field");
				Thread.sleep(3000);
			}

			// City code
			if (xngcitycode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"City Code field is a mandatory field and the value is not provided");
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode")),
						xngcitycode);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, xngcitycode + " is entered in City Code field");
			}

			ExtentTestManager.getTest().log(LogStatus.PASS, "New City is created");

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "New city is not created");
		}

		// Existing Site
		if (existingsiteselection.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton")));
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")));

			if (sitevalue.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Physical Site field is mandatory and no values are provided");
			} else {
				Clickon(getwebelement("//div[text()='" + sitevalue + "']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, sitevalue + " is selected under Physical Site dropdown");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Existing Site is not selected");
		}

		// New Site
		if (newsiteselection.equalsIgnoreCase("yes")) {

			if (CSR_Name.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");

			} else {

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")),
						CSR_Name);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, CSR_Name + " is entered under CSR Name field");
			}

		} else {

			ExtentTestManager.getTest().log(LogStatus.PASS, "CSR name is not created");

		}

		// Perfomance Reporting
		if (performReport.equalsIgnoreCase("null")) {

			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Performance reporting value is not provided. 'Follow Service' is selected by default");
		} else {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[text()='" + performReport + "']"));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					performReport + " is selected under Performance reporting dropdown");
		}

		// Pro active monitoring
		if (ProactiveMonitor.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Pro active monitoring value is not provided. 'Follow Service' is selected by default");

		} else {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[text()='" + ProactiveMonitor + "']"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					ProactiveMonitor + " is selected under proactive monitoring dropdown");
		}

		// Smart monitoring
		if (smartmonitor.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Smart monitoring value is not provided. 'Follow Service' is selected by default");
		} else {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[text()='" + smartmonitor + "']"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, smartmonitor + " is selected under Smart monitoring dropdown");
		}

		// Site Allias
		if (siteallias.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered for 'Site Alias' field");
		} else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")),
					siteallias);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, siteallias + " is entered under 'Site Alias' field");
		}

		// Vlan id
		if (VLANid.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered for 'Vlan id' field");
		} else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")), VLANid);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, VLANid + " is entered under Vlan id field");
		}

		// DCA Enabled Site
		if (DCAenabledsite.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "DCA enabled checkbox is selected");

			// Cloud Service provider
			if (cloudserviceprovider.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"DCA clous service provider dropdown is mandatory. No values are provided");
			} else {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						cloudserviceprovider + " is selected under 'cloud service provider' dropdown");
			}

		} else {
			Log.info("DCA site is not selected");
			ExtentTestManager.getTest().log(LogStatus.PASS, "DCA enabled checkbox is not selected");

		}

		// Remark
		if (remark.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered under remark ");
		} else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")), remark);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, remark + " is entered under 'remark' field");
		}

		// Technology
		if (technology.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Technology dropdown is a mandatory field and no values are provided");
		} else {

			if (interfaceSpeed.equals("1GigE")) {

				if (technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture")
						|| technology.equals("Accedian-1G") || technology.equals("Cyan") || technology.equals("Alu")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

					if (technology.equals("Actelis")) {

						System.out.println("No additional fields displays");
					}

					else if (technology.equals("Atrica")) {

						// Device name
						if (devicename.equalsIgnoreCase("null")) {
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"device name field is mandatory. No values entered under 'device name' field");
						} else {

							SendKeys(
									getwebelement(xml
											.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")),
									devicename);
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS,
									devicename + " is entered under 'device name' field");
						}

						// Non- termination point
						if (nonterminatepoinr.equalsIgnoreCase("yes")) {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
						} else {
							System.out.println("Non termination point checkbox is not selected as expected");
							ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
						}

						// Protected
						if (Protected.equalsIgnoreCase("yes")) {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
						} else {
							System.out.println("Protected checkbox is not selecetd as expected");
							ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
						}

					}

					else if (technology.equals("Overture") || technology.equals("Accedian-1G")) {

						// Non- termination point
						if (nonterminatepoinr.equalsIgnoreCase("yes")) {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
						} else {
							System.out.println("Non termination point checkbox is not selected as expected");
							ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
						}

						// Protected
						if (Protected.equalsIgnoreCase("yes")) {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
						} else {
							System.out.println("Protected checkbox is not selecetd as expected");
							ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
						}

					}

					else if (technology.equals("Cyan")) {

						// Non- termination point
						if (nonterminatepoinr.equalsIgnoreCase("yes")) {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
						} else {
							System.out.println("Non termination point checkbox is not selected as expected");
							ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
						}

					}

					else if (technology.equals("Alu")) {

						// Device name
						if (devicename.equalsIgnoreCase("null")) {
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"device name field is mandatory. No values entered under 'device name' field");
						} else {

							SendKeys(
									getwebelement(xml
											.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")),
									devicename);
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS,
									devicename + " is entered under 'device name' field");
						}

					}

				}
			}

			if (interfaceSpeed.equals("10GigE")) {

				if (technology.equals("Accedian")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

					// Non- termination point
					if (nonterminatepoinr.equalsIgnoreCase("yes")) {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
					} else {
						System.out.println("Non termination point checkbox is not selected as expected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
					}

					// Protected
					if (Protected.equalsIgnoreCase("yes")) {

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
					} else {
						System.out.println("Protected checkbox is not selecetd as expected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
				}

			}
		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");

		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void editSiteOrder(String application, String interfaceSpeed, String performReport,
			String ProactiveMonitor, String smartmonitor, String siteallias, String VLANid, String DCAenabledsite,
			String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename,
			String remark, String IVreference, String siteOrderNumber)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditsideOrderlink")));
		Thread.sleep(6000);

		scrollToTop();
		Thread.sleep(3000);
		// Point to Point

			if (interfaceSpeed.equals("1GigE")) {
				editSiteOrder_Onetoffnet_1G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid,
						DCAenabledsite, cloudserviceprovider, technology, nontermination, Protected, devicename,
						remark);
			}

			else if (interfaceSpeed.equals("10GigE")) {
				editSiteOrder_OnnetOffnet_10G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid,
						DCAenabledsite, cloudserviceprovider, technology, nontermination, Protected, remark);
			}


		scrolltoend();
		Thread.sleep(3000);
		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void editIPVPNsiteOrder(String application, String performReport, String ProactiveMonitor,
			String smartmonitor, String technology, String siteallias, String remark, String IVReference,
			String perCoSPerformancereport, String primarySiteOrder, String routerConfigurationViewIpv4,
			String wholesaleProvider, String managedSite, String priority, String actelisBased, String voip,
			String voipClassOfService) throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditsideOrderlink")));
		Thread.sleep(6000);

		scrollToTop();
		Thread.sleep(3000);

		// Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);

		// Pro active Monitoring
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		// Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		// primary Site Order
		addDropdownValues_commonMethod(application, "Primary Site Order", "primarySiteOrder_out", primarySiteOrder,
				xml);

		// Site alias
		editsiteorder_sitealias(application, siteallias);

		// Per CoS Performance Reporting
		editSiteOrder_PerCoSPerformancereporting(application, performReport);

		// IV Reference
		addDropdownValues_commonMethod(application, "IV Reference", "Addsiteorder_IVreferencedropdown", IVReference,
				xml);

		// Router Configuration View IPv4
		editSiteOrder_RouterConfigurationViewIPv4(application, routerConfigurationViewIpv4);

		// Wholesale Provider
		addDropdownValues_commonMethod(application, "Wholesale Provider", "wholesaleProvider_out", wholesaleProvider,
				xml);

		scrolltoend();
		Thread.sleep(1000);

		// Managed Site
		editSiteOrder_managedSite(application, managedSite);

		// Priority
		addDropdownValues_commonMethod(application, "Priority", "priority_out", priority, xml);

		// Actelis Based
		editcheckbox_commonMethod(application, actelisBased, "actelisBased_out", "Actelis Based", xml);

		// VOIP
		editcheckbox_commonMethod(application, voip, "voip_out", "VoIP", xml);

		boolean voipSelection = getwebelement(xml.getlocator("//locators/" + application + "/voip_out")).isSelected();
		if (voipSelection) {
			addDropdownValues_commonMethod(application, "VoIP Class of Service", "voipClassOfService_out",
					voipClassOfService, xml);
		}

		// Remark
		editsiteOrder_remark(application, remark);

		scrolltoend();
		Thread.sleep(3000);

		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void editSiteOrder_OnnetOffnet_10G(String application, String performReport, String ProactiveMonitor,
			String smartmonitor, String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider,
			String technology, String nontermination, String Protected, String remark)
			throws InterruptedException, DocumentException, IOException {

		// Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);

		// Pro active Monitoring
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		// Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		// Vlan id
		editsiteOrder_vlanid(application, VLANid);

		// Site alias
		editsiteorder_sitealias(application, siteallias);

		// DCA Enabled Site
		editesiteOrder_DcaEnabled(application, DCAenabledsite, cloudserviceprovider);

		scrolltoend();
		Thread.sleep(3000);
		// Remark
		editsiteOrder_remark(application, remark);

		// Technology
		editSiteOrder_technology(application, technology);

		if (technology.equalsIgnoreCase("Accedian")) {

			// Non-termination point
			editsiteorder_NonterminationPoint(application, nontermination);

		}

	}

	public void editSiteOrder_Onetoffnet_1G(String application, String performReport, String ProactiveMonitor,
			String smartmonitor, String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider,
			String technology, String nontermination, String Protected, String devicename, String remark)
			throws InterruptedException, DocumentException, IOException {

		// Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);

		// Pro active Monitoring
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		// Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		// Vlan id
		editsiteOrder_vlanid(application, VLANid);

		// Site alias
		editsiteorder_sitealias(application, siteallias);

		// DCA Enabled Site
		editesiteOrder_DcaEnabled(application, DCAenabledsite, cloudserviceprovider);

		scrolltoend();
		Thread.sleep(3000);
		// Remark
		editsiteOrder_remark(application, remark);

		// Technology
		editSiteOrder_technology(application, technology);

		if (technology.equalsIgnoreCase("Actelis")) {
			System.out.println(" NO additional fields display for technology Actelis");
		}

		if (technology.equalsIgnoreCase("Atrica")) {

			// Non-termination point
			editsiteorder_NonterminationPoint(application, nontermination);

			// Device Name
			editSiteOrder_deviceName(application, devicename);

		}

		if (technology.equalsIgnoreCase("Overture")) {

			// Non-termination point
			editsiteorder_NonterminationPoint(application, nontermination);

		}

		if (technology.equalsIgnoreCase("Alu")) {

			// Device Name
			editSiteOrder_deviceName(application, devicename);

		}

		if (technology.equalsIgnoreCase("Accedian-1G")) {

			// Non-termination point
			editsiteorder_NonterminationPoint(application, nontermination);

		}

		if (technology.equalsIgnoreCase("Cyan")) {

			// Non-termination point
			editsiteorder_NonterminationPoint(application, nontermination);

		}

	}



	public void editSiteOrder_Performancereporting(String application, String performReport)
			throws InterruptedException, DocumentException {

		// Performance Reporting
		boolean perfrmReportAvailability = false;
		try {
			perfrmReportAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")).isDisplayed();

			if (perfrmReportAvailability) {
				if (performReport.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance reporting dropdown is not edited");
					System.out.println("Performance reporting dropdown is not edited");
				} else {
					Clickon(getwebelement(xml
							.getlocator("//locators/" + application + "/Addsiteorder_performancereporting_xbutton")));
					Thread.sleep(3000);

					Clickon(getwebelement(
							"//div[label[text()='Performance Reporting']]//div[text()='" + performReport + "']"));

					Thread.sleep(3000);
					System.out.println("perform reporting selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Performance reporting' dropdown is: " + performReport);
					System.out.println("Edited value for 'Performance reporting' dropdown is: " + performReport);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Performance Reporting' dropdown is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Performance Reporting' dropdown is not available under 'Edit Site Order' page");
			System.out.println(" Performance Reporting' dropdown is not available under 'Edit Site Order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to select value under 'Performance reporting' checkbox ");
			System.out.println(" Not able to select value under 'Performance reporting' checkbox ");
		}

	}

	public void editSiteOrder_RouterConfigurationViewIPv4(String application, String routerConfigViewIPv4)
			throws InterruptedException, DocumentException {

		// Performance Reporting
		boolean perfrmReportAvailability = false;
		try {
			perfrmReportAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/routerConfigView_Field_out")).isDisplayed();

			if (perfrmReportAvailability) {
				if (routerConfigViewIPv4.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Router Configuration View IPv4 dropdown is not edited");
					System.out.println("Router Configuration View IPv4 dropdown is not edited");
				} else {
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/routerConfigurationviewIPv4_out")));
					Thread.sleep(3000);

					Clickon(getwebelement("//div[label[text()='Router Configuration View IPv4']]//div[text()='"
							+ routerConfigViewIPv4 + "']"));

					Thread.sleep(3000);
					System.out.println("Router Configuration View IPv4 selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Router Configuration View IPv4' dropdown is: " + routerConfigViewIPv4);
					System.out.println(
							"Edited value for 'Router Configuration View IPv4' dropdown is: " + routerConfigViewIPv4);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Router Configuration View IPv4' dropdown is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Router Configuration View IPv4' dropdown is not available under 'Edit Site Order' page");
			System.out
					.println(" Router Configuration View IPv4' dropdown is not available under 'Edit Site Order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to select value under 'Router Configuration View IPv4' checkbox ");
			System.out.println(" Not able to select value under 'Router Configuration View IPv4' checkbox ");
		}

	}

	public void editSiteOrder_PerCoSPerformancereporting(String application, String perCoSperformReport)
			throws InterruptedException, DocumentException {

		// Performance Reporting
		boolean perfrmReportAvailability = false;
		try {
			perfrmReportAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/perCoSperformanceReporting_out")).isDisplayed();

			if (perfrmReportAvailability) {
				if (perCoSperformReport.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance reporting dropdown is not edited");
					System.out.println("Per CoS Performance reporting dropdown is not edited");
				} else {
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/perCoSperformancereporting_xbutton_out")));
					Thread.sleep(3000);

					Clickon(getwebelement("//div[label[text()='Per CoS Performance Reporting']]//div[text()='"
							+ perCoSperformReport + "']"));

					Thread.sleep(3000);
					System.out.println("Per CoS Performance reporting selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Per CoS Performance reporting' dropdown is: " + perCoSperformReport);
					System.out.println(
							"Edited value for 'Per CoS Performance reporting' dropdown is: " + perCoSperformReport);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Per CoS Performance reporting' dropdown is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Per CoS Performance reporting' dropdown is not available under 'Edit Site Order' page");
			System.out.println("Per CoS Performance reporting' dropdown is not available under 'Edit Site Order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to select value under 'Per CoS Performance reporting' checkbox ");
			System.out.println(" Not able to select value under 'Per CoS Performance reporting' checkbox ");
		}

	}

	public void editSiteOrder_GCRoloType(String application, String GCRoloType)
			throws InterruptedException, DocumentException {

		boolean GCRoloTypeAvailability = false;
		try {
			GCRoloTypeAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();

			if (GCRoloTypeAvailability) {
				if (GCRoloType.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'GCR OLO Type' dropdown is not edited");
				} else {
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown_xbutton")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + GCRoloType + "']"));
					Thread.sleep(3000);
					System.out.println("'GCR OLO Type' dropdown selected");

					String actualvalue = getwebelement("//div[label[text()='GCR OLO Type']]//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'GCR OLO Type' dropdown is: " + actualvalue);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'GCR OLO Type' dropdown is not available under 'Edit Site Order' page");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'GCR OLO Type' dropdown is not available under 'Edit Site Order' page");
			System.out.println(" 'GCR OLO Type' dropdown is not available under 'Edit Site Order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, GCRoloType + " not available under 'GCR OLO type' dropdown");
			System.out.println(GCRoloType + " not available under 'GCR OLO type' dropdown");
		}

	}

	public void editSiteOrder_VLANEtherType(String application, String VlanEtherType)
			throws InterruptedException, DocumentException {

		boolean VLANEtherTypeAvailability = false;
		try {
			VLANEtherTypeAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();

			if (VLANEtherTypeAvailability) {
				if (VlanEtherType.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'VLAN Ether Type' dropdown value is not edited");
				} else {
					Clickon(getwebelement(xml
							.getlocator("//locators/" + application + "/Addsiteorder_VLAnEtheryTypeDropdown_xbutton")));
					Thread.sleep(3000);
					Clickon(getwebelement(
							"//div[label[text()='VLAN Ether Type']]//div[text()='" + VlanEtherType + "']"));
					Thread.sleep(3000);
					System.out.println("'VLAN Ether Type' dropdown selected");

					String actualValue = getwebelement("//div[label[text()='VLAN Ether Type']]//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'VLAN Ether Type' dropdown is: " + actualValue);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
			System.out.println(" 'VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					VlanEtherType + " is not selected under 'VLAN Ether type' dropdown");
			System.out.println(VlanEtherType + " is not edited under 'VLAN Ether type' dropdown");
		}

	}

	public void editSiteOrder_primaryVLANEtherType(String application, String primaryVlanEtherType)
			throws InterruptedException, DocumentException {

		boolean primaryVLANEtherTypeAvailability = false;
		try {
			primaryVLANEtherTypeAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown"))
							.isDisplayed();

			if (primaryVLANEtherTypeAvailability) {
				if (primaryVlanEtherType.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Primary VLAN Ether Type' dropdown value is not edited");
				} else {
					Clickon(getwebelement(xml.getlocator(
							"//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown_xbutton")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[label[text()='Primary VLAN Ether Type']]//div[text()='"
							+ primaryVlanEtherType + "']"));
					Thread.sleep(3000);
					System.out.println("'Primary VLAN Ether Type' dropdown selected");

					String actualValue = getwebelement("//div[label[text()='Primary VLAN Ether Type']]//span")
							.getText();
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Primary VLAN Ether Type' dropdown is: " + primaryVlanEtherType);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Primary VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Primary VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
			System.out.println(" 'Primary VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to edit value under 'Primary Vlan Ether type' dropdown");
			System.out.println(" Not able to edit value under 'Primary Vlan Ether type' dropdown");
		}
	}

	public void editSiteOrder_technology(String application, String technology) throws InterruptedException {

		boolean techValue = false;

		try {
			techValue=getwebelement("//div[contains(text(),'"+ technology + "')]").isDisplayed();

			if (techValue) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Technology value is displaying as: " + technology + " as expected");

			} else {
				String actualValue = getwebelement("//div[div[label[contains(text(),'Technology')]]]/div[2]").getText();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Technology value is not displaying as expected" + "   Actual value displaying is: "
								+ actualValue + "  Expected value for Technology is: " + technology);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Technology value is not displaying as expected");
			System.out.println(" Technology value is not displaying as expected");
		}
	}

	public void editSiteOrder_siteOrderNumber(String application, String siteOrderNumber) throws InterruptedException {

		boolean siteOrderValue = false;
		try {
			siteOrderValue = getwebelement("//div[text()='" + siteOrderNumber + "']").isDisplayed();

			if (siteOrderValue) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Site Order Number (Siebel Service ID) value is displaying as: " + siteOrderNumber
								+ " as expected");
				System.out.println(" Site Order Number (Siebel Service ID) value is displaying as: " + siteOrderNumber
						+ " as expected");
			} else {
				String actualValue = getwebelement(
						"//div[div[label[contains(text(),'Site Order Number (Siebel Service ID)')]]]/div[2]").getText();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Site Order Number (Siebel Service ID) value is not displaying"
								+ "   Actual value displaying is: " + actualValue
								+ "  Expected value for 'Site Order Number (Siebel Service ID)' is: "
								+ siteOrderNumber);

				System.out.println(" Site Order Number (Siebel Service ID) value is not displaying"
						+ "   Actual value displaying is: " + actualValue
						+ "  Expected value for 'Site Order Number (Siebel Service ID)' is: " + siteOrderNumber);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Site Order NUmber' value is not displaying as expected");
			System.out.println(" 'Site Order NUmber' value is not displaying as expected");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Site Order NUmber' value is not displaying as expected");
			System.out.println(" 'Site Order NUmber' value is not displaying as expected");
		}

	}

	public void editSiteOrder_IVReference(String application, String IVReference) throws InterruptedException {

		boolean IVrefValue = false;

		try {
			IVrefValue = getwebelement("//div[text()='" + IVReference + "']").isDisplayed();

			if (IVrefValue) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" IV reference value is displaying as: " + IVReference + " as expected");
				System.out.println(" IV reference value is displaying as: " + IVReference + " as expected");

			} else {
				String actualValue = getwebelement("//div[div[label[contains(text(),'IV Reference')]]]/div[2]")
						.getText();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" IV Reference) value is not displaying as expected" + "   Actual value displaying is: "
								+ actualValue + "  Expected value for 'IV Reference' is: " + IVReference);

				System.out.println(
						" IV Reference) value is not displaying as expected" + "   Actual value displaying is: "
								+ actualValue + "  Expected value for 'IV Reference' is: " + IVReference);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'IV Reference' value is not dispaying as expected");
			System.out.println(" 'IV Reference' value is not dispaying as expected");
		}
	}

	public void editSiteOrder_proactiveMonitoring(String application, String ProactiveMonitor)
			throws InterruptedException, DocumentException {

		boolean proactiveMonitorAvilability = false;
		try {
			proactiveMonitorAvilability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")).isDisplayed();

			if (proactiveMonitorAvilability) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Proactive Monitoring' dropdown is displaying under 'Edit Site Order' page as expected");
				System.out.println(
						" Proactive Monitoring' dropdown is displaying under 'Edit Site Order' page as expected");

				if (ProactiveMonitor.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Proactive monitoring' dropdown value is not edited");
					System.out.println("Proactive monitoring' dropdown value is not edited");
				} else {
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/Addsitorder_proactivemonitoring_xbutton")));
					Thread.sleep(3000);

					Clickon(getwebelement(
							"//div[label[text()='Proactive Monitoring']]//div[text()='" + ProactiveMonitor + "']"));

					Thread.sleep(3000);
					System.out.println("proa ctive monitorin selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Pro active Monitoring' dropdown is: " + ProactiveMonitor);
					System.out.println("Edited value for 'Pro active Monitoring' dropdown is: " + ProactiveMonitor);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
				System.out.println(" Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
			System.out.println(" Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to enter value under 'pro active monitoring' dropodwn");
			System.out.println(" Not able to enter value under 'pro active monitoring' dropodwn");
		}
	}

	public void editSiteOrder_smartMonitoring(String application, String smartmonitor)
			throws InterruptedException, DocumentException {

		boolean smartmonitorAvailability = false;
		try {
			smartmonitorAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring")).isDisplayed();

			if (smartmonitorAvailability) {

				if (smartmonitor.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Smarts Monitoring dropdown value is not edited");
					System.out.println("Smarts Monitoring dropdown value is not edited");
				} else {
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring_xbutton")));
					Thread.sleep(3000);
					Clickon(getwebelement(
							"//div[label[text()='Smarts Monitoring']]//div[text()='" + smartmonitor + "']"));
					Thread.sleep(3000);

					System.out.println("smarts monitoring is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Smarts Monitoring' dropdown is: " + smartmonitor);
					System.out.println("Edited value for 'Smarts Monitoring' dropdown is: " + smartmonitor);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Smart Monitoring dropdown is not available under 'Edit Site Order' page");
				System.out.println("Smart Monitoring dropdown is not available under 'Edit Site Order' page");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Smart Monitoring dropdown is not available under 'Edit Site Order' page");
			System.out.println("Smart Monitoring dropdown is not available under 'Edit Site Order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to select value under 'Smart Montoring' checkbox");
			System.out.println(" NOt able to select value under 'Smart Montoring' checkbox");
		}

	}

	public void editSiteOrder_managedSite(String application, String managedSite)
			throws InterruptedException, DocumentException {

		boolean managedSitevailability = false;
		try {
			managedSitevailability = getwebelement(
					xml.getlocator("//locators/" + application + "/managedSiteDropdown_out")).isDisplayed();

			if (managedSitevailability) {

				if (managedSite.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Managed Site dropdown value is not edited");
					System.out.println("Managed Site dropdown value is not edited");
				} else {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managedSite_xbutton_out")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[label[text()='Managed Site']]//div[text()='" + managedSite + "']"));
					Thread.sleep(3000);

					System.out.println("smarts monitoring is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Managed Site' dropdown is: " + managedSite);
					System.out.println("Edited value for 'Managed Site' dropdown is: " + managedSite);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Managed Site dropdown is not available under 'Edit Site Order' page");
				System.out.println("Managed Site dropdown is not available under 'Edit Site Order' page");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Managed Site dropdown is not available under 'Edit Site Order' page");
			System.out.println("Smart Monitoring dropdown is not available under 'Edit Site Order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to select value under 'Managed Site' checkbox");
			System.out.println(" NOt able to select value under 'Managed Site' checkbox");
		}

	}

	public void editSiteOrder_deviceName(String application, String devicename)
			throws InterruptedException, DocumentException, IOException {

		boolean devicenameAvailability = false;
		try {

			devicenameAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).isDisplayed();

			if (devicenameAvailability) {

				if (devicename.equalsIgnoreCase("Null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " NO changes made for 'Device Name' field");
				} else {

					getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield"))
							.clear();

					SendKeys(
							getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")),
							devicename);
					Thread.sleep(3000);

					String actualvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" Device Name value has been edited and the edited value is: " + actualvalue);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Device name text field is not displaying under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Device name text field is not displaying under 'Edit Site Order' page");
			System.out.println(" Device name text field is not displaying under 'Edit Site Order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'device name' field");
			System.out.println(" Not able to edit 'device name' field");
		}
	}

	public void editSiteOrder_mappingMode(String application, String mappingmode, String portBased, String vlanBased)
			throws InterruptedException, DocumentException {

		boolean mappingModeAvailability = false;
		try {
			mappingModeAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")).isDisplayed();
			if (mappingModeAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Mapping Mode' dropdown is displaying in 'Edit Site Order' page as expected");
				System.out.println(" 'Mapping Mode' dropdown is displaying in 'Edit Site Order' page as expected");

				if (mappingmode.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'mapping Mode' dropdown is not edited");
					System.out.println(" 'mapping Mode' dropdown is not edited");

				} else {
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown_xbutton")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + mappingmode + "']"));
					Thread.sleep(3000);

					String actualValue = getwebelement("//div[label[text()='Mapping Mode']]//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Mapping mode' dropdown is edited as: " + actualValue);

					if (actualValue.equalsIgnoreCase("Port Based")) {

						edittextFields_commonMethod(application, "Port Name", "portname_textField", portBased, xml);

					} else if (actualValue.equalsIgnoreCase("Vlan Based")) {

						edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased, xml);
					}

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Mapping mode' dropdown is not displaying under 'Edit Site order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Mapping mode' dropdown is not displaying under 'Edit Site order' page");
			System.out.println(" 'Mapping mode' dropdown is not displaying under 'Edit Site order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit value under 'Mapping Mode' dropdown");
			System.out.println(" Not able to edit value under 'Mapping Mode' dropdown");
		}
	}

	public void editsiteOrder_vlanid(String application, String VLANid)
			throws InterruptedException, DocumentException, IOException {

		boolean vlanAvailability = false;

		try {
			vlanAvailability = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid"))
					.isDisplayed();

			if (vlanAvailability) {
				if (VLANid.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Vlanid field value is not edited");
					System.out.println("Vlanid field value is not edited");
				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")),
							VLANid);
					Thread.sleep(3000);

					String VLANidValue = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Vlan id' field is: " + VLANidValue);
					System.out.println("Edited value for 'Vlan id' field is: " + VLANidValue);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"VLAN Id field is not available under 'Edit Site Order' page");
				System.out.println("VLAN Id field is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "VLAN Id field is not available under 'Edit Site Order' page");
			System.out.println("VLAN Id field is not available under 'Edit Site Order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'VLAn Id' text field");
			System.out.println(" not able to edit 'VLAN ID' text field");
		}
	}

	public void editsiteOrder_remark(String application, String remark)
			throws InterruptedException, DocumentException, IOException {

		boolean remarkAvailability = false;
		scrolltoend();
		Thread.sleep(3000);
		try {
			remarkAvailability = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark"))
					.isDisplayed();

			if (remarkAvailability) {
				if (remark.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Remark text field value is not edited");
				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")),
							remark);
					Thread.sleep(3000);

					String remarkValue = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Remark' field is: " + remarkValue);
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Remark text field is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Remark text field is not available under 'Edit Site Order' page");
			System.out.println("Remark text field is not available under 'Edit Site Order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'remark' field");
			System.out.println(" Not able to enter value under 'remark' field");
		}

	}

	public void editsiteorder_sitealias(String application, String siteallias)
			throws InterruptedException, DocumentException, IOException {
		boolean siteAliasAvilability = false;
		try {
			siteAliasAvilability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).isDisplayed();

			if (siteAliasAvilability) {
				if (siteallias.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Site Alias field value is not edited");
					System.out.println("Site Alias field value is not edited");
				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).clear();
					Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")),
							siteallias);
					Thread.sleep(3000);
					String siteAliasvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Site Alias' field is: " + siteAliasvalue);
					System.out.println("Edited value for 'Site Alias' field is: " + siteAliasvalue);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Site Alias field is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Site Alias field is not available under 'Edit Site Order' page");
			System.out.println(" Site Alias field is not available under 'Edit Site Order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Site Alias' field");
			System.out.println(" Not able to enter value under 'Site Alias' field");
		}

	}

	public void editSiteorder_Offnet(String application, String offnet) throws InterruptedException, DocumentException {

		boolean offnetAvailability = false;

		try {
			offnetAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (offnetAvailability) {

			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Offnet' checkbox is displaying under 'Edit Site ordeer' page as exepected");
			if (!offnet.equalsIgnoreCase("null")) {

				boolean offnetselection = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();
				Thread.sleep(2000);

				if (offnet.equalsIgnoreCase("yes")) {

					if (offnetselection) {

						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Offnet checkbox is not edited and it is already Selected while creating");

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
						Log.info("Offnet check box is selected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Offnet is edited and gets selected as expected");
					}

				}

				else if (offnet.equalsIgnoreCase("no")) {

					if (offnetselection) {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
						Log.info("Offnet check box is unselected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "offnet is edited and gets unselected");

					} else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Offnet is not edited and it remains unselected");
					}

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Offnet chekbox");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Offnet' checkbox is not available under 'Edit Site Order' page");
		}
	}

	public void editsiteorder_circuitReference(String application, String circuitRef)
			throws InterruptedException, DocumentException, IOException {

		boolean circuitRefAvilability = false;
		try {
			circuitRefAvilability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).isDisplayed();

			if (circuitRefAvilability) {
				if (circuitRef.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "Circuit Reference field value is not edited");

				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField"))
							.clear();
					Thread.sleep(3000);
					SendKeys(
							getwebelement(xml
									.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")),
							circuitRef);
					Thread.sleep(3000);
					String circuitRefvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Circuit Reference' field is: " + circuitRefvalue);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Circuit Reference field is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Circuit Reference field is not available under 'Edit Site Order' page");
			System.out.println(" Circuit Reference field is not available under 'Edit Site Order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Circuit reference' field");
			System.out.println(" Not able to enter value under 'Circuit reference' field");
		}
	}

	public void editsiteorder_VLAN(String application, String VLAN)
			throws InterruptedException, DocumentException, IOException {

		boolean VLANAvilability = false;
		try {
			VLANAvilability = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN"))
					.isDisplayed();

			if (VLANAvilability) {
				if (VLAN.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "VLAN text field value is not edited");

				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).clear();
					Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")), VLAN);
					Thread.sleep(3000);

					String VLANactualvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'VLAN' text field is: " + VLANactualvalue);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" VLAN text field is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " VLAN text field is not available under 'Edit Site Order' page");
			System.out.println(" VLAN text field is not available under 'Edit Site Order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit value under 'VLAN' text field");
			System.out.println(" Not able to edit value under 'VLAN' text field");
		}

	}

	public void editsiteorder_primaryVLAN(String application, String primaryVLAN)
			throws InterruptedException, DocumentException, IOException {

		boolean primaryVLANAvilability = false;
		try {
			primaryVLANAvilability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).isDisplayed();

			if (primaryVLANAvilability) {
				if (primaryVLAN.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " Primary VLAN text field value is not edited");

				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).clear();
					Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")),
							primaryVLAN);
					Thread.sleep(3000);
					String primaryVLANactualvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Primary VLAN' text field is: " + primaryVLANactualvalue);
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Primary VLAN text field is not available under 'Edit Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Primary VLAN text field is not available under 'Edit Site Order' page");
			System.out.println(" Primary VLAN text field is not available under 'Edit Site Order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Primary VLAn Type' text field");
			System.out.println(" Not able to edit 'Primary VLAn Type' text field");
		}

	}

	public void editesiteOrder_DcaEnabled(String application, String DCAenabledsite, String cloudserviceprovider)
			throws InterruptedException, DocumentException {

		boolean DCAavailability = false;

		try {
			DCAavailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (DCAavailability) {

			if (!DCAenabledsite.equalsIgnoreCase("null")) {

				boolean dcaenabled = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox"))
								.isSelected();
				Thread.sleep(2000);

				if (DCAenabledsite.equalsIgnoreCase("yes")) {

					if (dcaenabled) {

						ExtentTestManager.getTest().log(LogStatus.PASS,
								"DCA Enabled Site is already Selected while creating");

						if (cloudserviceprovider.equalsIgnoreCase("null")) {

							ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to Cloud Service Provider");

						} else {

							addDropdownValues_commonMethod(application, "Cloud Service Provider", "Addsiteorder_cloudserviceProvider", cloudserviceprovider, xml);
						}

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
						Log.info("DCA Enabled Site check box is selected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "DCA Enabled Site checkbox is selected");

						if (cloudserviceprovider.equalsIgnoreCase("null")) {

							ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to Cloud Service Provider");

						} else {

							addDropdownValues_commonMethod(application, "Cloud Service Provider", "Addsiteorder_cloudserviceProvider", cloudserviceprovider, xml);
						}
					}

				}

				else if (DCAenabledsite.equalsIgnoreCase("no")) {

					if (dcaenabled) {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
						Log.info("DCA Enabled Site check box is unselected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "DCA Enabled Site is unselected as Expected");

					} else {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"DCA Enabled Site was not selected during service creation and it remains unselected as expected");
					}

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for DCAenabled site chekbox as expected");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"DCA Enabled Site checkbox is not displaying under 'Edit Site Order' page");
		}
	}

	public void editsiteorder_NonterminationPoint(String application, String nontermination)
			throws InterruptedException, DocumentException {

		boolean NonTerminationPointAvailability = false;

		try {
			NonTerminationPointAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isDisplayed();

			if (NonTerminationPointAvailability) {

				if (!nontermination.equalsIgnoreCase("null")) {

					boolean nonterminatepoint = false;
					try {
						nonterminatepoint = getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint"))
										.isSelected();
					} catch (Exception e) {
						e.printStackTrace();
					}
					Thread.sleep(2000);

					if (nontermination.equalsIgnoreCase("yes")) {

						if (nonterminatepoint) {

							ExtentTestManager.getTest().log(LogStatus.PASS,
									" 'Non-Termination point' checkbox is already Selected while creating");

						} else {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
							Log.info("'Non-Termination point' check box is selected");
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"'Non-Termination point' is edited and gets selected");
						}

					}

					else if (nontermination.equalsIgnoreCase("no")) {

						if (nonterminatepoint) {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
							Log.info("'Non-Termination point' check box is unselected");
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"'Non-Termination point' is edited and gets unselected");

						} else {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"'Non-Termination point' was not selected during service creation and it remains unselected as expected");
						}

					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes made for 'Non-Termination point' chekbox as expected");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Non-Termination Point checkbox is not available under 'Edit Site order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Non-Termination Point checkbox is not available under 'Edit Site order' page");
			System.out.println(" Non-Termination Point checkbox is not available under 'Edit Site order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Non-termination point' checkbox ");
			System.out.println(" Not able to click on 'Non-termination point' checkbox ");
		}
	}

	public void editsiteOrder_protected(String application, String Protected)
			throws InterruptedException, DocumentException {

		boolean prtctedAvailability = false;

		try {
			prtctedAvailability = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected"))
					.isDisplayed();

			if (prtctedAvailability) {
				if (!Protected.equalsIgnoreCase("null")) {

					boolean prtcted = false;
					try {
						prtcted = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected"))
								.isSelected();
					} catch (Exception e) {
						e.printStackTrace();
					}
					Thread.sleep(2000);

					if (Protected.equalsIgnoreCase("yes")) {
						if (prtcted) {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									" 'Protected' checkbox is already Selected while creating");
						} else {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
							Log.info("'Non-Termination point' check box is selected");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Protected' is edited and gets selected");
						}
					}

					else if (Protected.equalsIgnoreCase("no")) {
						if (prtcted) {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
							Log.info("'Non-Termination point' check box is unselected");
							ExtentTestManager.getTest().log(LogStatus.PASS, "'Protected' is edited and gets unselected");

						} else {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"'Protected' was not selected during service creation and it remains unselected as expected");
						}
					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Protected' chekbox as expected");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Protected checkbox is not displaying under 'Edit Site order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Protected checkbox is not displaying under 'Edit Site order' page");
			System.out.println(" Protected checkbox is not displaying under 'Edit Site order' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to select 'protected' checkbox");
			System.out.println(" Not able to select 'protected' checkbox");

		}

	}

	public void editsiteOrder_cloudserviceprovider(String application, String cloudserviceprovider)
			throws InterruptedException, DocumentException {
		if (cloudserviceprovider.equalsIgnoreCase("null")) {

			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to Cloud Service Provider");

		} else {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
			Thread.sleep(4000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
			Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Edited value for 'Cloud Service provider' dropdown is: " + cloudserviceprovider);
		}
	}

	public void Enteraddsiteorder(String application) throws InterruptedException, DocumentException {

		Thread.sleep(2000);
		
		waitForpageload();
		waitforPagetobeenable();

		scrolltoend();
		Thread.sleep(2000);

		click_commonMethod(application, "Action", "Actiondropdown_siteorder", xml);
		Thread.sleep(1000);

		click_commonMethod(application, "AddSiteOrder", "Addsiteorderlink", xml);
	}

	public void clickonEditwithoutselectingrow(String application)
			throws InterruptedException, DocumentException, IOException {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		click_commonMethod(application, "Action", "Actiondropdown_siteorder", xml);
		Thread.sleep(2000);

		click_commonMethod(application, "editSiteOrderlink" , "EditsideOrderlink", xml);
		Thread.sleep(2000);

		String popupmessage = Gettext(getwebelement("//div[text()='Please select a row to edit']"));
		System.out.println("Edit popup message before selecting row: " + popupmessage);
		Log.info("Edit popup message before selecting row: \"+popupmessage");

		Clickon(getwebelement("//div[@class='modal-header']//div[contains(text(),'×')]"));

	}

	public void clickondeletewithoutselectingrow(String application)
			throws InterruptedException, DocumentException, IOException {

		click_commonMethod(application, "Back", "Backbutton", xml);
		Thread.sleep(5000);
		
		waitForpageload();
		waitforPagetobeenable();

		Log.info("Deleting site order without selecting row");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actiondropdown_siteorder")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletesiteorderlink")));
		Thread.sleep(2000);

		String popupmessage = Gettext(getwebelement("//div[text()='Please select a row to delete']"));
		System.out.println("Delete popup message before selecting row: " + popupmessage);
		Log.info("Delete popup message before selecting row: \"+popupmessage");

		Clickon(getwebelement("//div[@class='modal-header']//div[contains(text(),'×')]"));

	}

	public void deletesiteorderdetails(String application) throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletesiteorderlink")));
		Thread.sleep(2000);

		String deletemessage = Gettext(
				getwebelement("//div[text()='Are you sure that you want to delete this item?']"));
		System.out.println("delete popup displays message as : " + deletemessage);
		Log.info("delete popup displays message as : \"+deletemessage");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Deletesiteorderrbutton")));
		Thread.sleep(2000);

	}

	public void clickonviewewithoutselectingrow(String application)
			throws InterruptedException, DocumentException, IOException {

//		System.out.println("have to navigate back");
//		driver.navigate().back();
//		Thread.sleep(3000);
//		System.out.println("got navigated back");

		Log.info("View site order without selecting row");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actiondropdown_siteorder")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewsiteorderlink")));
		Thread.sleep(2000);

		String popupmessage = Gettext(getwebelement("//div[text()='Please select a row to view']"));
		System.out.println("popup message before selecting row for viewing occurs as: " + popupmessage);
		Log.info(" popup message before selecting row for viewing occcurs as: " + popupmessage);

		Clickon(getwebelement("//div[@class='modal-header']//div[contains(text(),'×')]"));

	}

	public void viewsiteorderlink(String application) throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewsiteorderlink")));
		Thread.sleep(2000);
		Log.info("Entered View site order page");

		ExtentTestManager.getTest().log(LogStatus.PASS, "Entered view site order page");

	}

	public void verifyEditSiteOrder(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue)
			throws InterruptedException, DocumentException, IOException {
		try {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditsideOrderlink")));
			Thread.sleep(3000);

			Log.info("Entered edit siteorder page");

			String fetchedvalue_country = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_country")));
			sa.assertEquals(fetchedvalue_country, country,
					"Country field is not displaying same Entered value while creating");

			System.out.println("country value is: " + fetchedvalue_country);

			String fetchedvalue_city = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_city")));
			sa.assertEquals(fetchedvalue_city, city, "City field is not displaying same Entered value while creating");

			System.out.println("city value is: " + fetchedvalue_city);

			String fetchedvalue_csrname = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_CSRname")));
			sa.assertEquals(fetchedvalue_csrname, CSR_Name,
					"CSR name field isnot  displaying same Entered value while creating");

//		  String fetchedvalue_proactivemonitorin=Gettext(getwebelement("//div[label[contains(text(),'Procative Monitoring')]]//span[contains(text(),'"+ ProactiveMonitor +"')]"));

			String fetchedvalue_proactivemonitorin = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_proactivemonitor")));
			sa.assertEquals(fetchedvalue_proactivemonitorin, ProactiveMonitor,
					"Pro active monitoring dropdowmn is not displaying same Entered value while creating");

			System.out.println("proactive monitroing value is: " + fetchedvalue_proactivemonitorin);

//		  String fetchedvalue_performreporting=Gettext(getwebelement("//div[label[contains(text(),'Performance Reporting')]]//span[contains(text(),'"+ performReport +"')]"));

			String fetchedvalue_performreporting = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_performreport")));
			sa.assertEquals(fetchedvalue_performreporting, performReport,
					"Perormance reporting dropdown is not displaying same Entered value while creating");

//		  String fetchedvalue_smartsmonitor=Gettext(getwebelement("//div[label[contains(text(),'Smarts Monitoring')]]//span[contains(text(),'"+ smartmonitor +"')]"));

			String fetchedvalue_smartsmonitor = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_smartmonitor")));
			sa.assertEquals(fetchedvalue_smartsmonitor, smartmonitor,
					"smarts monitoring dropdown is not displaying same Entered value while creating");

			String fetchedvalue_technology = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_technology")));
			sa.assertEquals(fetchedvalue_technology, technology,
					"Technology field is not displaying same Entered value while creating");
			System.out.println("technology is: " + fetchedvalue_technology);

			String fetchedvalue_sitealias = Getattribute(
					getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_sitealias")), "value");
			sa.assertEquals(fetchedvalue_sitealias, siteallias,
					"Site alias field is not displaying same Entered value while creating");

			System.out.println("site alias value: " + fetchedvalue_sitealias);

			String fetchedvalue_vlanid = Getattribute(
					getwebelement(xml.getlocator("//locators/" + application + "/Editsiteorder_VLANid")), "value");
			sa.assertEquals(fetchedvalue_vlanid, VLANid,
					"VLAN id field is not displaying same Entered value while creating");

			boolean fetchedvalue_DCAenabledsite = getwebelement(
					xml.getlocator("//locators/" + application + "/Editsiteorder_DCAenabledsite")).isSelected();
			sa.assertFalse(fetchedvalue_DCAenabledsite, "DCA enabled is not selected as expected");
			System.out.println("DCA enables is: " + fetchedvalue_DCAenabledsite);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
			Thread.sleep(3000);

			
		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}

	public void verifyAddsiteorderFields(String application, String interfaceSpeed)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "verifying field under 'Add Site order' page");

		verifySiteOrderFields_OnnetOffnet(application, interfaceSpeed);

	}

	public void addsiteorder(String application, String interfaceSpeed, String VPNtopology, String circuitType,
			String country, String city, String CSR_Name, String site, String performReport, String ProactiveMonitor,
			String smartmonitor, String technology, String siteallias, String VLANid, String DCAenabledsite,
			String cloudserviceprovider, String sitevalue, String remark, String xngcityname, String xngcitycode,
			String devicename, String nonterminatepoinr, String Protected, String newcityselection,
			String existingcityselection, String existingsiteselection, String newsiteselection, String siteOrderNumber,
			String circuitref, String offnetSelection, String IVReference, String GCRolo, String Vlan, String Vlanether,
			String primaryVlan, String primaryVlanether, String EPNoffnet, String EPNEOSDH, String mappingmode,
			String portBased, String vlanBased) throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		Thread.sleep(2000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "addSiteOrder");

		addSiteOrderValues_OnnetOffnet(application, interfaceSpeed, country, city, CSR_Name, site, performReport,
					ProactiveMonitor, smartmonitor, technology, siteallias, VLANid, DCAenabledsite,
					cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename, nonterminatepoinr,
					Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection);


	}

	public void addIPVPNsiteorder(String application, String interfaceSpeed, String VPNtopology, String circuitType,
			String country, String city, String CSR_Name, String site, String performReport, String ProactiveMonitor,
			String smartmonitor, String technology, String siteallias, String VLANid, String DCAenabledsite,
			String cloudserviceprovider, String sitevalue, String remark, String xngcityname, String xngcitycode,
			String devicename, String nonterminatepoinr, String Protected, String newcityselection,
			String existingcityselection, String existingsiteselection, String newsiteselection, String siteOrderNumber,
			String IVReference, String perCoSPerformancereport, String primarySiteOrder,
			String routerConfigurationViewIpv4, String wholesaleProvider, String managedSite, String priority,
			String actelisBased, String voip, String voipClassOfService)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Add IPVPN Site Order");
		
		addtextFields_commonMethod(application, "Site Order Number", "dslSiteOrder_outband", siteOrderNumber, xml); // dsl
																													// Site
																													// order
																													// Number

		Countyr_AddIPVPNSiteOrder(application, country);

		City_AddIPVPNSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode,
				sitevalue, CSR_Name, existingsiteselection, newsiteselection);

		// scroll to bottom
		scrolltoend();
		Thread.sleep(1000);

		performancereporting_AddSiteOrder(application, performReport);

		proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

		smartsMonitoring_AddSiteOrder(application, smartmonitor);

		// Primary Site Order
		addDropdownValues_commonMethod(application, "Primary Site Order", "primarySiteOrder_out", primarySiteOrder,
				xml);

		// Per CoS Performance reporting
		perCoSperformancereporting_AddSiteOrder(application, perCoSPerformancereport);

		// Site Alias
		SiteAlias_AddSiteOrder(application, siteallias);

		// IV Reference
		addDropdownValues_commonMethod(application, "IV Reference", "Addsiteorder_IVreferencedropdown", IVReference,
				xml);

		// Router Configuration View IPv4
		RouterConfigurationViewIPv4_addSiteOrder(application, routerConfigurationViewIpv4);

		// Wholesale Provider
		addDropdownValues_commonMethod(application, "Wholesale Provider", "wholesaleProvider_out", wholesaleProvider,
				xml);

		// Managed Site
		managedSite_AddSiteOrder(application, managedSite);

		// Priority
		addDropdownValues_commonMethod(application, "Priority", "priority_out", priority, xml);

		// Actelis Based
		addCheckbox_commonMethod(application, "actelisBased_out", "Actelis Based", actelisBased, "no", xml);

		// VOIP
		addCheckbox_commonMethod(application, "voip_out", "VoIP", voip, "no", xml);

		if (voip.equalsIgnoreCase("yes")) {
			addDropdownValues_commonMethod(application, "VoIP Class of Service", "voipClassOfService_out",
					voipClassOfService, xml);
		}

		// Remark
		remark_AddSiteOrder(application, remark);

		Thread.sleep(3000);
		scrolltoend();
		Thread.sleep(3000);
		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void verifySiteOrderFields_OnnetOffnet(String application, String interfaceSpeed)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO," Verifying 'Site Order' fields");

		try {

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			Thread.sleep(5000);

		//Country Error message	
			warningMessage_commonMethod(application, "Addsiteorder_countryerrmsg" , "Country", xml);
			
			
		//City Error message	
			warningMessage_commonMethod(application, "Addsiteorder_devciexngCityErrmsg", "City", xml);
			
			
		//CSR name Error message	
			warningMessage_commonMethod(application, "Addsiteorder_csrnameErrmsg" , "CSR Name", xml);
			
		//Technology Error message	
			warningMessage_commonMethod(application, "Addsitieorder_technologyErrmsg ", "Technology", xml);
			
			
			scrollToTop();
			Thread.sleep(3000);

			// Validate Country dropdown
			System.out.println("validate Country dropdown");
			validateCountry_AddSiteorder(application);

			// Validate City Fields
			System.out.println("Validate city fields");
			validateCity_AddSiteOrder(application);

			// Validate Site/CSR field
			System.out.println("validate Site Fields");
			validateSite_AddSiteOrder(application);

			scrolltoend();
			Thread.sleep(3000);

			// Validate performance reporting dropdown
			System.out.println("validate performance reporting checkbox");
			validatePerformancereporting_AddSiteOrder(application);

			// validate proactive Monitoring dropdown
			validateProactiveMonitoring_AddSiteOrder(application);

			// Validate Smarts monitoring dropdown
			validateSmartsMOnitoring_AddSiteOrder(application);

			// Validate Site Alias field
			validateSiteAlias_AddSiteOrder(application);

			// Validate VLAN Id field
			validateVlanID_AddSiteOrder(application);

			// Validate DCA Enabled Site and Cloud Service Provider dropdown
			valiadateDCAEnabledsite_AddSieOrder(application);

			// Verify Remark field
			System.out.println("validate Remark fields");
			validateRemark_AddSiteOrder(application);

			if (interfaceSpeed.equals("1GigE")) {

				technologyDropdownFor1GigE(application);
			}

			else if (interfaceSpeed.equals("10GigE")) {

				technologyDropdownFor10GigE(application);
			}

			// Validate OK button
			OKbutton_AddSiteOrder(application);

			// Validate Cancel button
			cancelbutton_AddSiteOrder(application);

			Thread.sleep(3000);
			scrolltoend();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cancel")));
			Thread.sleep(3000);

			

		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}


	public void verifySiteOrderFields_NonterminationField(String application)
			throws InterruptedException, DocumentException {
		boolean Nonterminationpointcheckbox = false;
		try {
			Nonterminationpointcheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isDisplayed();
			sa.assertTrue(Nonterminationpointcheckbox,
					"On selecting 'Overture' under Technology, Non termination point checkbox is not available");
			if (Nonterminationpointcheckbox) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Non Termination Point' checkbox is displayed under 'Add Site order' page as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			}

			boolean nonTerminaionpointselection = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
			sa.assertFalse(nonTerminaionpointselection,
					"Non-termination point checbox under Add site is selected by default");
			if (nonTerminaionpointselection) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non-Termination Point' checkbox is selected by default");
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Non-Termination Point' checkbox is not selected by default as expected");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			System.out.println(" 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non-Termination Point' checkbox is selected by default");
			System.out.println(" 'Non-Termination Point' checkbox is selected by default");
		}
	}

	public void verifySiteOrderFields_protected(String application) throws InterruptedException, DocumentException {}

	public void verifySiteOrderField_deviceName(String application) throws InterruptedException, DocumentException {
		boolean devicename = false;
		try {
			devicename = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield"))
					.isDisplayed();
			sa.assertTrue(devicename, "On selecting Atrica under Technology, Device name is not available");
			if (devicename) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Device Name' field is displaying under 'Add Site Order' page as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Device Name' field is not displaying under 'Add Site Order' page");

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Device Name' field is not displaying under 'Add Site Order' page");
			System.out.println(" 'Device Name' field is not displaying under 'Add Site Order' page");
		}
	}

	public void verifySiteorderFields_mappingMode(String application) throws InterruptedException, DocumentException {

		String[] mappingMode = new String[2];
		int i = 0;
		boolean MappingdropdownAvailability = false;
		try {
			MappingdropdownAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")).isDisplayed();

			if (MappingdropdownAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'mapping mode' dropdown is displaying in 'Add Site Order' page as expected");
				System.out.println(" 'mapping mode' dropdown is displaying in 'Add Site Order' page as expected");

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")));
				Thread.sleep(3000);

				List<WebElement> listofMappingMode = driver
						.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

				if (listofMappingMode.size() >= 1) {
					for (WebElement mappingModetypes : listofMappingMode) {

						Log.info("list of Mapping modes are : " + mappingModetypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"The list of Mapping modes  inside dropdown is: " + mappingModetypes.getText());
						System.out.println(
								"The list of mapping Modes  inside dropdown is: " + mappingModetypes.getText());
						mappingMode[i] = mappingModetypes.getText();
						i++;

					}
				}

				for (int j = 0; j < mappingMode.length; j++) {

					if (mappingMode[j].equalsIgnoreCase("Port Based")) {
						Clickon(getwebelement("//div[text()='" + mappingMode[j] + "']"));
						Thread.sleep(3000);

						boolean portname = false;
						try {
							portname = getwebelement(
									xml.getlocator("//locators/" + application + "/portname_textField")).isDisplayed();
							Thread.sleep(3000);

							if (portname) {
								ExtentTestManager.getTest().log(LogStatus.PASS,
										" 'Port name' text field is displaying as expected");
								System.out.println(" 'Port name' text field is displayig");
							} else {
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Port name' text field is not displaying");
								System.out.println(" 'Port name' text field is not displaying");
							}
						} catch (NoSuchElementException e) {
							e.printStackTrace();
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Port name' text field is not displaying");
							System.out.println(" 'Port name' text field is not displaying");
						}

					} else if (mappingMode[j].equalsIgnoreCase("Vlan Based")) {
						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")));
						Thread.sleep(3000);

						Clickon(getwebelement("//div[text()='" + mappingMode[j] + "']"));
						Thread.sleep(3000);

						boolean vlanName = false;
						try {
							vlanName = getwebelement(xml.getlocator("//locators/" + application + "/vlanid_textfield"))
									.isDisplayed();
							Thread.sleep(3000);

							if (vlanName) {
								ExtentTestManager.getTest().log(LogStatus.PASS,
										" 'VLAN Id' text field is displaying as expected");
								System.out.println(" 'VLAN Id' text field is displayig");
							} else {
								ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Id' text field is not displaying");
								System.out.println(" 'VLAN Id' text field is not displaying");
							}
						} catch (NoSuchElementException e) {
							e.printStackTrace();
							ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN Id' text field is not displaying");
							System.out.println(" 'VLAN Id' text field is not displaying");
						}

					}
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
				System.out.println(" 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
			System.out.println(" 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
		}
	}

	public void technologyDropdownFor1GigE(String application) throws InterruptedException, DocumentException {

		String[] Technology = { "Actelis", "Atrica", "Overture", "Alu", "Accedian-1G", "Cyan" };

		boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox;

		// Technology dropdown
		technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
				.isDisplayed();
		sa.assertTrue(technology, "Technology dropdown is not displayed");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
		List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

		if (listoftechnology.size() >= 1) {
			for (WebElement technologytypes : listoftechnology) {

				Log.info("list of technology are : " + technologytypes.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"The list of technology  inside dropdown while  adding site order is: "
								+ technologytypes.getText());
				System.out.println("The list of technology  inside dropdown while  adding site order is: "
						+ technologytypes.getText());
				String technologyValue = technologytypes.getText();
			}

			for (int k = 0; k < Technology.length; k++) {
				// Actelis
				if (Technology[k].equalsIgnoreCase("Actelis")) {
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Actelis' is selected under Technology" + "no additional fields displays");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

				}

				// Atrica
				else if (Technology[k].equalsIgnoreCase("Atrica")) {

					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Atrica' is selected under Technology" + "list of fields should occur: "
									+ "Device name - Mandatory field" + "Non Termination point checkbox"
									+ "Protected checkbox");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Device Name
					verifySiteOrderField_deviceName(application);

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);

				}

				// Overture
				else if (Technology[k].equalsIgnoreCase("Overture")) {
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Overture' is selected under Technology" + "list of fields should occur: "
									+ "Non Termination point checkbox" + "Protected checkbox");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);

				}

				// Alu
				else if (Technology[k].equalsIgnoreCase("Alu")) {

					ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
							+ "list of fields should occur: " + "Device name - Mandatory field");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Device Name
					verifySiteOrderField_deviceName(application);
				}

				// Accedian
				else if ((Technology[k].equalsIgnoreCase("Accedian-1G"))) {

					ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
							+ "Non Termination point checkbox" + "Protected checkbox");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);


				}

				// Cyan
				else if (Technology[k].equalsIgnoreCase("Cyan")) {

					ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
							+ "list of fields should occur: " + "Non Termination point checkbox");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);

				}
			}
		} else {

			System.out.println("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"no values are available inside technology dropdown for Add site order");
		}
	}

	public void technologyDropdownFor10GigE(String application) throws InterruptedException, DocumentException {

		String Technology = "Accedian";

		boolean technology, Nonterminationpointcheckbox, portectedcheckbox;

		// Technology dropdown
		technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
				.isDisplayed();
		sa.assertTrue(technology, "Technology dropdown is not displayed");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
		List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

		if (listoftechnology.size() >= 1) {
			for (WebElement technologytypes : listoftechnology) {

				Log.info("list of technology are : " + technologytypes.getText());
				System.out.println("list of technology are : " + technologytypes.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"The list of technology  inside dropdown while  adding site order is: "
								+ technologytypes.getText());
			}
		} else {

			System.out.println("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"no values are available inside technology dropdown for Add site order");
		}

		ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
				+ "list of fields should occur: " + "Non Termination point checkbox" + "Protected checkbox");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
		Clickon(getwebelement("//div[text()='" + Technology + "']"));

		// Non Termination Point
		verifySiteOrderFields_NonterminationField(application);


	}

	public void technologyDropdownFor1GigE_HubAndSpoke_Access(String application)
			throws InterruptedException, DocumentException {}

	public void technologyDropdownFor1GigE_HubAndSpoke_Access_offnetselected(String application,
			String IVReferenceSelection) throws InterruptedException, DocumentException {}

	public void technologyDropdownFor1GigE_HubAndSpoke_Primary(String application)
			throws InterruptedException, DocumentException {}


	public void technologyDropdownFor1GigE_EPN_Primary(String application)
			throws InterruptedException, DocumentException {

		String[] Technology = { "Actelis", "Atrica", "Overture", "Alu", "Accedian-1G", "Cyan" };

		boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan,
				VlanetherType;

		// Technology dropdown
		technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
				.isDisplayed();
		sa.assertTrue(technology, "Technology dropdown is not displayed");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
		List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

		if (listoftechnology.size() >= 1) {
			for (WebElement technologytypes : listoftechnology) {

				Log.info("list of technology are : " + technologytypes.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"The list of technology  inside dropdown while  adding site order is: "
								+ technologytypes.getText());
				System.out.println("The list of technology  inside dropdown while  adding site order is: "
						+ technologytypes.getText());
				String technologyValue = technologytypes.getText();
			}

			for (int k = 0; k < Technology.length; k++) {

				// Actelis
				if (Technology[k].equalsIgnoreCase("Actelis")) {
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Actelis' is selected under Technology" + "no additional fields displays");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
				}

				// Atrica
				else if (Technology[k].equalsIgnoreCase("Atrica")) {

					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Atrica' is selected under Technology" + "list of fields should occur: "
									+ " Non Termination point checkbox" + " Protected checkbox"
									+ " Device Name text field");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);

					// Protected checkbox
					verifySiteOrderFields_protected(application);

					// Device Name Text Field
					verifySiteOrderField_deviceName(application);

				}

				// Overture
				else if (Technology[k].equalsIgnoreCase("Overture")) {

					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Overture' is selected under Technology" + "list of fields should occur: "
									+ "Non Termination point checkbox" + "Device Name text Field" + "Protected checkbox"
									+ " should display");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);

					// Protected checkbox
					verifySiteOrderFields_protected(application);

					// Device Name
					verifySiteOrderField_deviceName(application);
				}

				// Alu
				else if (Technology[k].equals("Alu")) {

					ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
							+ " Device Name Text Field should display");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Device Name
					verifySiteOrderField_deviceName(application);

				}

				// Accedian
				else if ((Technology[k].equalsIgnoreCase("Accedian-1G"))
						|| (Technology[k].equalsIgnoreCase("Accedian"))) {

					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox" + " Protected checkbox"
									+ " Device Name text Field should display");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);

					// Protected checkbox
					verifySiteOrderFields_protected(application);

					// Device Name
					verifySiteOrderField_deviceName(application);
				}

				// Cyan
				else if (Technology[k].equalsIgnoreCase("Cyan")) {

					ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
							+ "list of fields should occur: " + "Non Termination point checkbox");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);
				}
			}
		} else {

			System.out.println("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"no values are available inside technology dropdown for Add site order");
		}

	}

	public void technologyDropdownFor1GigE_EPNEOSDHselected_Primary(String application)
			throws InterruptedException, DocumentException {

		String[] Technology = { "Actelis", "Atrica", "Overture", "Alu", "Accedian-1G", "Cyan" };

		boolean technology;

		// Technology dropdown
		technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
				.isDisplayed();
		sa.assertTrue(technology, "Technology dropdown is not displayed");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
		List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

		if (listoftechnology.size() >= 1) {
			for (WebElement technologytypes : listoftechnology) {

				Log.info("list of technology are : " + technologytypes.getText());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"The list of technology  inside dropdown while  adding site order is: "
								+ technologytypes.getText());
				System.out.println("The list of technology  inside dropdown while  adding site order is: "
						+ technologytypes.getText());
				String technologyValue = technologytypes.getText();
			}

			for (int k = 0; k < Technology.length; k++) {

				// Actelis
				if (Technology[k].equalsIgnoreCase("Actelis")) {
					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Actelis' is selected under Technology" + "no additional fields displays");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
				}

				// Atrica
				else if (Technology[k].equalsIgnoreCase("Atrica")) {

					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Atrica' is selected under Technology" + "list of fields should occur: "
									+ " Non Termination point checkbox" + " Protected checkbox"
									+ " Device Name text field");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);

					// Protected checkbox
					verifySiteOrderFields_protected(application);

					// Device Name Text Field
					verifySiteOrderField_deviceName(application);

				}

				// Overture
				else if (Technology[k].equalsIgnoreCase("Overture")) {

					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Overture' is selected under Technology" + "list of fields should occur: "
									+ "Non Termination point checkbox" + "Device Name text Field" + "Protected checkbox"
									+ " should display");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);

					// Protected checkbox
					verifySiteOrderFields_protected(application);

					// VLAN Text field
					boolean Vlan = false;
					try {
						Vlan = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN"))
								.isDisplayed();
						sa.assertTrue(Vlan, " 'VLAN' text field is not available");
						if (Vlan) {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									" 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						} else {
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									" 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
					} catch (Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								" 'VLAN' text field is not Available under 'Add Site order' page");
					}

					// VLAN Ether Type
					boolean VlanetherType = false;
					try {
						VlanetherType = getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown"))
										.isDisplayed();
						sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
						if (VlanetherType) {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									" 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");

							Clickon(getwebelement(xml
									.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
							List<WebElement> listofVLANethertype = driver
									.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

							if (listofVLANethertype.size() >= 1) {
								for (WebElement VLANEthertypes : listofVLANethertype) {
									Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS,
											"The list of 'VLAN Ether Type' inside dropdown is: "
													+ VLANEthertypes.getText());

								}
							} else {
								System.out.println(
										"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
								ExtentTestManager.getTest().log(LogStatus.FAIL,
										"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
							}
						} else {
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									" 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
						}
					} catch (Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								" 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
					}
				}

				// Alu
				else if (Technology[k].equals("Alu")) {

					ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
							+ " Device Name Text Field should display");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Device Name
					verifySiteOrderField_deviceName(application);

				}

				// Accedian
				else if ((Technology[k].equalsIgnoreCase("Accedian-1G"))
						|| (Technology[k].equalsIgnoreCase("Accedian"))) {

					ExtentTestManager.getTest().log(LogStatus.INFO,
							"when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox" + " Protected checkbox"
									+ " Device Name text Field should display");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);

					// Protected checkbox
					verifySiteOrderFields_protected(application);

					// Device Name
//					verifySiteOrderField_deviceName(application);
				}

				// Cyan
				else if (Technology[k].equalsIgnoreCase("Cyan")) {

					ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
							+ "list of fields should occur: " + "Non Termination point checkbox");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

					// Non Termination Point
					verifySiteOrderFields_NonterminationField(application);
				}
			}
		} else {

			System.out.println("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"no values are available inside technology dropdown for Add site order");
		}

	}

	public void technologyDropdownFor10GigE_EPN(String application) throws InterruptedException, DocumentException {

		String[] Technology = { "Accedian" };

		boolean technology, Nonterminationpointcheckbox, portectedcheckbox;

		// Technology dropdown
		technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
				.isDisplayed();
		sa.assertTrue(technology, "Technology dropdown is not displayed");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
		List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

		if (listoftechnology.size() >= 1) {
			for (WebElement technologytypes : listoftechnology) {

				boolean match = false;
				for (int i = 0; i < Technology.length; i++) {
					if (technologytypes.getText().equals(Technology[i])) {
						match = true;
						Log.info("list of technology are : " + technologytypes.getText());
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"The list of technology  inside dropdown while  adding site order is: "
										+ technologytypes.getText());
					}
				}
				sa.assertTrue(match);
			}
		} else {

			System.out.println("no values are available inside technology dropdown for Add site order");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"no values are available inside technology dropdown for Add site order");
		}

		ExtentTestManager.getTest().log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
				+ "list of fields should occur: " + "Non Termination point checkbox" + "Protected checkbox");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
		Clickon(getwebelement("//div[text()='" + Technology + "']"));

		// Non Termination Point
		try {
			Nonterminationpointcheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isDisplayed();
			sa.assertTrue(Nonterminationpointcheckbox,
					"On selecting 'Accedian' under Technology, Non termination point checkbox is not available");
			if (Nonterminationpointcheckbox) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Non Termination Point' checkbox is displayed under 'Add Site order' page as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			}

			boolean nonTerminaionpointselection = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
			sa.assertFalse(nonTerminaionpointselection,
					"Non-termination point checbox under Add site is selected by default");
			if (nonTerminaionpointselection) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Non-Termination Point' checkbox is not selected by default as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non-Termination Point' checkbox is selected by default");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
		}

		// Protected checkbox
		try {
			portectedcheckbox = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected"))
					.isDisplayed();
			sa.assertTrue(portectedcheckbox,
					"On selecting 'Accedian' under Technology, protected checkbox is not available");
			if (portectedcheckbox) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Protected' checkbox is displayed under 'Add Site order' page as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Protected' checkbox is not available under 'Add Site order' page");
			}
			boolean protectedSelection = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
			sa.assertFalse(protectedSelection, "Protected checbox under Add site is selected by default");
			if (protectedSelection) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Protected' checkbox is not selected by default as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Protected' checkbox is selected by default");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Protected' checkbox is not Available under 'Add Site order' page");
		}

	}

	public void selectRowForsiteorder(String Application, String siteordernumber) throws InterruptedException, DocumentException, IOException {

		System.out.println("-----------------------------" + siteordernumber + "---------------------");
		int TotalPages;

		waitForpageload();
		waitforPagetobeenable();
		
		
		scrolltoend();
		Thread.sleep(3000);
// WebElement web=getwebelement(xml.getlocator("//locators/" + Application + "/TotalPagesforsiteorder"));
//	System.out.println(" webelement name for total page: "+ web);
//		String TextKeyword = getwebelement(xml.getlocator("//locators/" + Application + "/TotalPagesforsiteorder")).getText();
//	
//		TotalPages = Integer.parseInt(TextKeyword);
//
//		System.out.println("Total number of pages in table is: " + TotalPages);
//
//		ab:
//
//		for (int k = 1; k <= TotalPages; k++) {
//
//			// Current page
//			String CurrentPage = Gettext(getwebelement(xml.getlocator("//locators/" + Application + "/Currentpageforsiteorderfunc")));
//			int Current_page = Integer.parseInt(CurrentPage);
//			System.out.println("The current page is: " + Current_page);
//
//			assertEquals(k, Current_page);
//
//			System.out.println("Currently we are in page number: " + Current_page);

		// div[div[contains(text(),'" + siteOrdernumber_P2P +
		// "')]]//span[@class='ag-icon ag-icon-checkbox-unchecked'] --> needs to be
		// updated
		List<WebElement> results = null;

			results = getwebelements("//div[div[text()='Site Orders']]//following-sibling::div//div[text()='"+ siteordernumber + "']");

		int numofrows = results.size();
		System.out.println("no of results: " + numofrows);
		boolean resultflag;
//
//			if (numofrows == 0) {
//
////				PageNavigation_NextPage(Application);
//				Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/pagenavigationfornextpage_underviewserviceforsiteorder")));
//				
//
//			}
//
//			else {
//
//				for (int i = 0; i < numofrows; i++) {
//
//					try {

		resultflag = results.get(0).isDisplayed();
		System.out.println("status of result: " + resultflag);
		if (resultflag) {
			System.out.println(results.get(0).getText());
			results.get(0).click();
			Thread.sleep(8000);
			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Actiondropdown_siteorder")));

			Thread.sleep(5000);

		}
//
//					} catch (StaleElementReferenceException e) {
//						// TODO Auto-generated catch block
//						// e.printStackTrace();
//						results = driver.findElements(
//								By.xpath("//div[div[contains(text(),'" + siteordernumber + "')]]//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
//						numofrows = results.size();
//						// results.get(i).click();
//						Log.info("selected row is : " + i);
//					}
//				}
//				break ab;
//			}
//		}
	}

	public void PageNavigation_NextPage(String Application) throws InterruptedException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Pagenavigationfornextpage")));
		Thread.sleep(3000);

	}

	public void AddCPEdevice(String application, String cpename, String vender, String snmpro, String managementAddress,
			String Mepid, String poweralarm, String Mediaselection, String Macaddress, String serialNumber,
			String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(3000);

//			 driver.navigate().to("http://172.30.246.170:4400/#/addLanLinkDirectFiberSiteDevice");
		Thread.sleep(3000);

		System.out.println("enter details to add CPE device");

		Log.info("Adding details to the fields to create a CPE device");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")), vender);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")), poweralarm);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")), Mediaselection);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
				serialNumber);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		Thread.sleep(3000);

	}

	public void viewCPEdevice(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevicelink")));
		Thread.sleep(3000);

		String name = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_name")));
		sa.assertEquals(name, cpename, "name is displaying as expected");

		String vendor_model = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_vendor")));
		sa.assertEquals(vendor_model, vender, "Vendor name is displaying as expected");

		String snmPro = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_snmpro")));
		sa.assertEquals(snmPro, snmpro, "SNM pro name is displaying as expected");

		String manageaddress = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_managementAddress")));
		sa.assertEquals(manageaddress, managementAddress, "management address is displaying as expected");

		String mEPid = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_mepid")));
		sa.assertEquals(mEPid, Mepid, "MEP Id is displaying as expected");

		String powerAlarm = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_poweralarm")));
		sa.assertEquals(powerAlarm, poweralarm, "power alarm is displaying as expected");

		String mediaSelection = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_mediaselection")));
		sa.assertEquals(mediaSelection, Mediaselection, "Media selection is displaying as expected");

		String linkLostforwarding = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_linklostforwarding")));
		sa.assertEquals(linkLostforwarding, linkLostForwarding, "link lost forwarding is displaying as expected");

		String macAddress = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_macaddress")));
		sa.assertEquals(macAddress, Macaddress, "mac address is displaying as expected");

		String Serialnumber = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_serialnumber")));
		sa.assertEquals(Serialnumber, serialNumber, "Serial number is displaying as expected");

		String hexaSerialNumber = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/viewCPEdevice_hexaserialnumber")));
		sa.assertEquals(hexaSerialNumber, hexaSerialnumber, "Hexa serial number is displaying as expected");

		

	}

	public void eDITCPEdevice(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelink")));
		Thread.sleep(3000);

//		  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevlielink_underEquipment")));
//	     Thread.sleep(3000);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")), vender);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")), poweralarm);

//      	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")), Mediaselection);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
				serialNumber);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));
		Thread.sleep(2000);

		System.out.println("Next buttton is not working under Add cpe device...... so gonnah select CAncel button");
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));

	}

	public void addCPEdeviceforIntermediateequipment(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(3000);

		System.out.println("enter details to add CPE device for intermediate equipment");

		Log.info("Adding details to the fields to create a CPE device");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);

//    	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")), vender);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);

//    	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")), poweralarm);

//    	Select(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")), Mediaselection);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
				serialNumber);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		System.out.println("Next buttton is not working under Add cpe device...... so gonnah select CAncel button");
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));

		Thread.sleep(3000);

	}

	public void verifyFieldsandAddCPEdevicefortheserviceselected_1G(String application, String interfaceSpeed,
			String cpename, String vender, String snmpro, String managementAddress, String Mepid, String poweralarm,
			String mediaSelection, String Macaddress, String serialNumber, String hexaSerialnumber,
			String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue, String technologySelected)
			throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(3000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields for CPE device under Equipment");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(6000);

		if (technologySelected.equalsIgnoreCase("Accedian-1G")) {
			equip_adddevi_Accedian1G(application, interfaceSpeed, cpename, vender, snmpro, managementAddress, Mepid,
					poweralarm, mediaSelection, Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding,
					newmanagementAddress, existingmanagementAddress, manageaddressdropdownvalue);

		} else {

			equip_addDevice_1G(application, interfaceSpeed, cpename, vender, snmpro, managementAddress, Mepid,
					poweralarm, mediaSelection, Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding,
					newmanagementAddress, existingmanagementAddress, manageaddressdropdownvalue);
		}

	}

	public void equip_adddevi_Accedian1G(String application, String interfaceSpeed, String cpename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String mediaSelection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding,
			String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(3000);

		try {

			String linklostForwardingcheckboxstate = "disabled";

			String[] Vender = { "Accedian-1G 1GigE-MetroNID-GT", "Accedian-1G 1GigE-MetroNID-GT-S", "Accedian-1G GX" };

			String[] powerAlarm = { "DC Single Power Supply - Feed A", "DC Dual Power Supply - Feed-A+B",
					"AC Single Power Supply - Feed A", "AC Dual Power Supply -Feed A+B" };

			String expectedDeviceNameFieldAutopopulatedValue = "<Device>.lanlink.dcn.colt.net";

			String expectedValueForSnmpro = "JdhquA5";

			// name field Error Message
			device_nameFieldWarningMessage(application);

			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			// serial number Eror Message
			device_serialNumberWarningMessage(application);

			// Hexa serial Number
			device_hexaSerialNumberWarningMessage(application);

			// Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);

			// Vendor/Model
			device_vendorModel(application, Vender, vender);

			// Snmpro
			device_snmPro(application, snmpro);

			// Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);

			// MEP Id
			device_mepID(application, Mepid);

			// Power Alarm
			device_powerAlarm(application, powerAlarm, poweralarm);

			// Serial Number
			device_serialNumber(application, serialNumber);

			// Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);

			scrolltoend();
			Thread.sleep(3000);
			// OK button
			device_okbutton(application);

			// cancel button
			device_cancelButton(application);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")));
			Thread.sleep(3000);

			

		} catch (AssertionError e) {

			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");

		}

	}

	public void equip_addDevice_1G(String application, String interfaceSpeed, String cpename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String mediaSelection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding,
			String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException {

		try {

			String linklostForwardingcheckboxstate = "enabled";

			String[] Vender = { "Overture ISG-26", "Overture ISG-26R", "Overture ISG-26S", "Overture ISG140",
					"Overture ISG180", "Overture ISG6000" };

			String[] powerAlarm = { "AC", "DC" };

			String[] Mediaselection = { "SFP-A with SFP-B", "RJ45-A with SFP-B" };

			String expectedDeviceNameFieldAutopopulatedValue = "<Device>.lanlink.dcn.colt.net";

			String mepValue = "null";

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			Thread.sleep(5000);

			// name field Error Message
			device_nameFieldWarningMessage(application);

			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			// Media Selection Error Message
//		device_mediaSelectionWarningMessage(application);

			// MAC Address Error Message
			device_macAddressWarningMessage(application);

			// Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);

			// Vendor/Model
			device_vendorModel(application, Vender, vender);

			// Snmpro
			device_snmPro(application, snmpro);

			// Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);

			// MEP Id
			device_mepID(application, Mepid);

			// Power Alarm
			device_powerAlarm(application, powerAlarm, poweralarm);

			scrolltoend();
			Thread.sleep(3000);

			// Media Selection
			device_mediaSelection(application, Mediaselection, mediaSelection);

			// MAC Address
			device_MAcaddress(application, Macaddress);

			// Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);

			scrolltoend();
			Thread.sleep(3000);

			// OK button
			device_okbutton(application);

			// cancel button
			device_cancelButton(application);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")));
			Thread.sleep(3000);

			

		} catch (AssertionError e) {

			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");

		}

	}

	public void verifyFieldsandAddCPEdevicefortheserviceselected_10G(String application, String interfaceSpeed,
			String cpename, String vender, String snmpro, String managementAddress, String Mepid, String poweralarm,
			String mediaSelection, String Macaddress, String serialNumber, String hexaSerialnumber,
			String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue) throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields for CPE device under Equipment");
		scrolltoend();
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(9000);

		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(3000);

		try {

			String linklostForwardingcheckboxstate = "disabled";

			String[] Vender = { "Accedian 10GigE-MetroNode-CE-2Port" };

			String[] powerAlarm = { "DC Single Power Supply - PSU A", "DC Dual Power Supply - PSU-A+B" };

			String expectedDeviceNameFieldAutopopulatedValue = "<Device>-10G.lanlink.dcn.colt.net";

			String MEPid = "5555";

			String expectedValueForSnmpro = "JdhquA5";

			// name field Error Message
			device_nameFieldWarningMessage(application);

			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			// serial number Eror Message
			device_serialNumberWarningMessage(application);

			// Hexa serial Number
			device_hexaSerialNumberWarningMessage(application);

			// Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);

			// Vendor/Model
			device_vendorModel(application, Vender, vender);

			// Snmpro
			device_snmPro(application, snmpro);

			// Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);

			// MEP Id
			device_mepID(application, Mepid);

			// Power Alarm
			device_powerAlarm(application, powerAlarm, poweralarm);

			// Serial Number
			device_serialNumber(application, serialNumber);

			// Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);

			scrolltoend();
			Thread.sleep(3000);
			// OK button
			device_okbutton(application);

			// cancel button
			device_cancelButton(application);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")));
			Thread.sleep(3000);

			

		} catch (AssertionError e) {

			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");

		}

	}

	public void AddCPEdevicefortheserviceselected(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress,
			String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Adding device for equipment");

		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));

		Thread.sleep(3000);

		Log.info("Adding details to the fields to create a CPE device");

		// vender/model
		try {
			if (vender.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device under Equipment");
			}

			else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));

			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "add cpe device  vender/model dropdown field not avaialble");
		}

		try {
			if (vender.equalsIgnoreCase("null")) {

				System.out.println(
						"No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device under Equipment");

			}

			else {
				Clickon(getwebelement("//div[label[text()='Vender/Model']]//div[text()='" + vender + "']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, vender
						+ " is the value passed for Mandatory 'Vendor/Model' dropdown for adding device under Equipment");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"FAilure at Vender/model dropdown. It does not have the value provided as input");
		}

		// snmpro
		try {
			if (snmpro.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"No values has been passed for Mandatory field 'snmpro' for adding device under Equipment");

			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						snmpro + " is the value passed for Mandatory 'Snmpro' field for adding device under Equipment");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snm pro' field is not available");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Snm pro' field");
		}

//Manage address text field	

		if (newmanagementAddress.equalsIgnoreCase("yes") && existingmanagementAddress.equalsIgnoreCase("no")) {
			try {
				if (managementAddress.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values has been passed for Mandatory field 'Manage Address' for adding device under Equipment");
				} else {
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
							managementAddress);
					ExtentTestManager.getTest().log(LogStatus.PASS, managementAddress
							+ " is the value passed for Mandatory 'Management Address' field for adding device under Equipment");
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Manage Address' field is not available");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Manage Address' field");
			}
		}
//Manage address dropdown
		else if (newmanagementAddress.equalsIgnoreCase("no") && existingmanagementAddress.equalsIgnoreCase("yes")) {
			try {
				if (manageaddressdropdownvalue.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values has been passed for Mandatory field 'Manage Address' dropdownfor adding device under Equipment");
				} else {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/AddCPEdevice_selectSubnettogglebutton")));
					Thread.sleep(3000);
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")));
					Thread.sleep(3000);
				}

			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"add cpe device  'Management Address' dropdown field not avaialble");
			}

			try {
				if (manageaddressdropdownvalue.equalsIgnoreCase("null")) {

					System.out.println(
							"No values has been passed for Mandatory 'Management Address' dropdown for adding device under Equipment");

				} else {
					Clickon(getwebelement("//div[label[text()='Management Address']]//div[text()='"
							+ manageaddressdropdownvalue + " ']]"));
					ExtentTestManager.getTest().log(LogStatus.PASS, manageaddressdropdownvalue
							+ " is the value passed for Mandatory 'Management Address' dropdown for adding device under Equipment");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"FAilure at 'Management Address' dropdown. It does not have the value provided as input");
			}
		}

		// Mepid
		try {

			if (Mepid.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.INFO,
						"No values has been passed for 'mepid' field for adding device under Equipment");

			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						Mepid + " is the value passed for 'Mepid' field for adding device under Equipment");

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mep Id' field is not available");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Mep Id' field");
		}

		// Power alarm
		try {

			if (poweralarm.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"No values has been passed for Mandatory dropdown 'Power alarm' for adding device under Equipment");
			} else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Power Alarm' dropdown is not available ");
		}

		try {

			if (poweralarm.equalsIgnoreCase("null")) {

				System.out.println("power alarm dropdown selected");
			} else {
				Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='" + poweralarm + "']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, poweralarm
						+ " is the value passed for Mandatory 'Power Alarm' dropdown field for adding device under Equipment");

			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"FAilure at power alarm dropdown. It does not have the value provided as input ");
		}

		// Media selection
		try {

			if (Mediaselection.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"No values has been passed for 'Media Selection' mandatory field for adding device under Equipment");
			} else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Media selection' dropdown is not available ");
		}

		try {

			if (Mediaselection.equalsIgnoreCase("null")) {
				System.out.println("Media selection dropdown selected");

			} else {
				Clickon(getwebelement("//div[label[text()='Media Selection']]//div[text()='" + Mediaselection + "']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, Mediaselection
						+ " is the value passed for Mandatory 'Media Selection' field for adding device under Equipment");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"FAilure at Media selection dropdown. It does not have the value provided as input");
		}

		// Mac address
		try {

			if (Macaddress.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" No values has been passed for 'mac address' mandatory field for adding device under Equipment");
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")),
						Macaddress);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						Macaddress + " is the value passed for 'Macaddress' field for adding device under Equipment");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mac Address' field is not available");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Mac Address' field");
		}

		// Serial number
		try {

			if (serialNumber.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.INFO,
						" No values has been passed for 'Serial number' field for adding device under Equipment");
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
						serialNumber);
				ExtentTestManager.getTest().log(LogStatus.PASS, serialNumber
						+ " is the value passed for Mandatory 'Serial Number' field for adding device under Equipment");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number' field is not available");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Serial Number' field");
		}

//
//		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
//				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {

			try {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Link lost forwarding checkbox is selected for adding device under Equipment");
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Link  lost forwarding checkbox is not avaialble");
			}

		} else {

			System.out.println("link lost forwarding is not selected");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Link lost forwarding checkbox is not selected for adding device under Equipment");

		}

		click_commonMethod(application, "OK", "okbutton", xml);

		Thread.sleep(3000);

	}

	public void verifydetailsEnteredforCPEdevice_1G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress,
			String existingmanagementAddress, String manageaddressdropdownvalue, String technologySelected)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered for creating device");

		String[] RouterId = new String[2];
		RouterId = cpename.split(".lanlink");

		String RouterIdValue = RouterId[0];

		String mediaSelectionValueInViewDevicePage = "no";
		if (Mediaselection.equalsIgnoreCase("null")) {
			Mediaselection = mediaSelectionValueInViewDevicePage;
		} else {
			Mediaselection = mediaSelectionValueInViewDevicePage;
		}

		verifyEnteredvalues_deviceName("Name", RouterIdValue, cpename);

		verifyEnteredvalues("Router Id", RouterIdValue);

		verifyEnteredvalues("Vendor/Model", vender);

		verifyEnteredvalues("Snmpro", snmpro);

		// Management Address
		if ((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		} else if ((existingmanagementAddress.equalsIgnoreCase("no"))
				&& (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Management Address", managementAddress);
		}

//	  verifyEnteredvalues("MEP Id", Mepid);

		verifyEnteredvalues("Power Alarm", poweralarm);

		if (technologySelected.equalsIgnoreCase("Accedian-1G")) {

			verifyEnteredvalues("Serial Number", serialNumber);

		} else {

			verifyEnteredvalues("Media Selection", Mediaselection);

			verifyEnteredvalues("MAC Address", Macaddress);
		}

	}

	public void verifydetailsEnteredforCPEdevice_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress,
			String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered for creating device");

		// Splitting device name
		String[] RouterId = new String[2];
		RouterId = cpename.split(".lanlink");

		String RouterIdValue = RouterId[0];

		Mediaselection = "no";

		verifyEnteredvalues_deviceName("Name", RouterIdValue, cpename);

		verifyEnteredvalues("Router Id", RouterIdValue);

		verifyEnteredvalues("Vendor/Model", vender);

		verifyEnteredvalues("Snmpro", snmpro);

		// Management Address
		if ((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		} else if ((existingmanagementAddress.equalsIgnoreCase("no"))
				&& (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Management Address", managementAddress);
		}

		verifyEnteredvalues("Power Alarm", poweralarm);

		verifyEnteredvalues("Media Selection", Mediaselection);

		verifyEnteredvalues("Link Lost Forwarding", "yes");

		verifyEnteredvalues("Serial Number", serialNumber);

	}

	public void eDITCPEdevicedetailsentered_1G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String createddevicename,
			String technologySelected) throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device under Equipment");

		System.out.println("Entered edit functionalitty");

		scrollToTop();
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));

		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");

		// Name field
		device_editnamefield(application, cpename);

		// vendor/model
		device_editVendorModelField(application, vender);

		// Snmpro
		device_editSnmproField(application);

		// Management address
		device_editManagementAddressField(application, managementAddress);

		// Mepid
		device_editMEPIdField(application, Mepid);

		scrolltoend();
		Thread.sleep(3000);

		// power alarm
		device_editPowerAlarm(application, poweralarm);

		if (technologySelected.equalsIgnoreCase("Accedian-1G")) {

			// Serial Number
			device_editserialnumber(application, serialNumber);

		} else {

			// Media Selection
			device_editMediaselection(application, Mediaselection);

			Thread.sleep(3000);

			// Mac address
			device_editMACaddress(application, Macaddress);

			// linklost forwarding
			device_editlinkLostforwarding(application, linkLostForwarding);
		}

		scrolltoend();
		Thread.sleep(3000);

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);

	}

	public void eDITCPEdevicedetailsentered_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String devicename)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device under Equipment");

		System.out.println("Entered edit functionalitty");

		scrollToTop();
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));

		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");

		// Name field
		device_editnamefield(application, cpename);

		// vendor/model
		device_editVendorModelField(application, vender);

		// Snmpro
		device_editSnmproField(application);

		// Management address
		device_editManagementAddressField(application, managementAddress);

		// Mepid
		device_editMEPIdField(application, Mepid);

		// power alarm
		device_editPowerAlarm(application, poweralarm);

		// serial Number
		device_editserialnumber(application, serialNumber);

		// linklost forwarding
		device_editlinklostforwarding_10G(application);

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);

	}

	public void eDITCPEdevicedetailsentered_1G_Overture(String application, String cpedevicename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String Mediaselection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country,
			String City, String Site, String Premise) throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device");

		System.out.println("Entered edit functionalitty");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));

		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");

		// Name field
		device_editnamefield(application, cpedevicename);

		// vendor/model
		device_editVendorModelField(application, vender);

		// Snmpro
		device_editSnmproField(application);

		// Management address
		device_editManagementAddressField(application, managementAddress);

		// Mepid
		device_editMEPIdField(application, Mepid);

		// power alarm
		device_editPowerAlarm(application, poweralarm);

		WebElement countrylabelname = getwebelement(
				xml.getlocator("//locators/" + application + "/countrylabelname_IntEquipment"));
		ScrolltoElement(countrylabelname);
		Thread.sleep(3000);

		// Media Selection
		device_editMediaselection(application, Mediaselection);

		// Mac address
		device_editMACaddress(application, Macaddress);

		// linklost forwarding
		device_editlinkLostforwarding(application, linkLostForwarding);

		scrolltoend();
		Thread.sleep(3000);

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);

	}

	public void eDITCPEdevicedetailsentered_1G_Accedian(String application, String cpedevicename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String Mediaselection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country,
			String City, String Site, String Premise) throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device");

		System.out.println("Entered edit functionalitty");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));

		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");

		// Name field
		device_editnamefield(application, cpedevicename);

		// vendor/model
		device_editVendorModelField(application, vender);

		// Snmpro
		device_editSnmproField(application);

		// Management address
		device_editManagementAddressField(application, managementAddress);

		// Mepid
		device_editMEPIdField(application, Mepid);

		// power alarm
		device_editPowerAlarm(application, poweralarm);

		// Serial Number
		device_editserialnumber(application, serialNumber);

		// linklost forwarding
//		device_editlinkLostforwarding(application, linkLostForwarding);

		scrolltoend();
		Thread.sleep(3000);

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);

	}

	public void AddCPEdevicefortheLAnlinkNationalservice(String application, String cpename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String Mediaselection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(3000);

		Log.info("Adding details to the fields to create a CPE device");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
		Clickon(getwebelement("//div[label[text()='Vender/Model']]//div[text()='" + vender + "']"));

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
		Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='" + poweralarm + "']"));

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
		Clickon(getwebelement("//div[label[text()='Media Selection']]//div[text()='" + Mediaselection + "']"));

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);

//		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
//				serialNumber);
//
//		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
//				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		} else {

		}

		click_commonMethod(application, "OK", "okbutton", xml);

		Thread.sleep(3000);

	}

	public void addDevice_IntEquipment(String application) throws InterruptedException, DocumentException {

		// click on Add device link
		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_adddevicelink")));
		Thread.sleep(3000);
	}

	public void selectTechnology(String application, String technologyToBeSelected)
			throws InterruptedException, DocumentException {

		// verify Technology popup
		boolean technologypopup = false;
		technologypopup = getwebelement(xml.getlocator("//locators/" + application + "/technologyPopup")).isDisplayed();
		if (technologypopup) {
			System.out.println("Technology popup is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Technology popup is displaying as expected");
		} else {
			System.out.println("Technology popup is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Technology popup is notdisplaying");
		}

		// Dropdown values inside popup
		boolean technologyDropdown = false;
		technologyDropdown = getwebelement(xml.getlocator("//locators/" + application + "/technologypopup_dropdown"))
				.isDisplayed();
		if (technologyDropdown) {
			System.out.println("Technology dropdown is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Technology dropdown is displaying as expected");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/technologypopup_dropdown")));
			Thread.sleep(3000);

			// verify list of values inside technology dropdown
			List<WebElement> listofTechnololgy = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

			if (listofTechnololgy.size() > 0) {

				for (WebElement technoloyTypes : listofTechnololgy) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"List of values available under 'Technology' dropdown are: " + technoloyTypes.getText());
					System.out.println(
							"List of values available under 'Technology' dropdown are: " + technoloyTypes.getText());
				}
			}

			// Select the Technology
			Clickon(getwebelement("//span[contains(text(),'" + technologyToBeSelected + "')]"));
			Thread.sleep(3000);
			String actualValue = getwebelement(
					xml.getlocator("//locators/" + application + "/tchnologyPopup_dropdownValues")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Technology' selected is: " + actualValue);
			System.out.println(" 'Technology' selected is: " + actualValue);

		} else {
			System.out.println("Technology dropdown is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Technology dropdown is notdisplaying");
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateEquipment_OKbuttonforpopup")));
		Thread.sleep(3000);

	}

	public void verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_1G(String application, String cpename,
			String vender_Overture, String vender_Accedian, String snmpro, String managementAddress, String Mepid,
			String poweralarm_Overture, String powerAlarm_Accedian, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String City,
			String Site, String Premise, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue, String existingcityselectionmode, String newcityselectionmode,
			String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode,
			String premisename, String premisecode, String technologySelectedfordevicecreation)
			throws InterruptedException, DocumentException, IOException {

		if (technologySelectedfordevicecreation.equalsIgnoreCase("Overture")) {
			deviceCreatoin_Overture(application, cpename, vender_Overture, snmpro, managementAddress, Mepid,
					poweralarm_Overture, MediaselectionActualValue, Macaddress, serialNumber, hexaSerialnumber,
					linkLostForwarding, country, City, Site, Premise, newmanagementAddress, existingmanagementAddress,
					manageaddressdropdownvalue, existingcityselectionmode, newcityselectionmode, cityname, citycode,
					existingsiteselectionmode, newsiteselectionmode, sitename, sitecode, existingpremiseselectionmode,
					newpremiseselectionmode, premisename, premisecode);

		}

		if ((technologySelectedfordevicecreation.equalsIgnoreCase("Accedian"))
				|| (technologySelectedfordevicecreation.equalsIgnoreCase("Accedian-1G"))) {
			deviceCreatoin_Accedian(application, cpename, vender_Accedian, snmpro, managementAddress, Mepid,
					powerAlarm_Accedian, MediaselectionActualValue, Macaddress, serialNumber, hexaSerialnumber,
					linkLostForwarding, country, City, Site, Premise, newmanagementAddress, existingmanagementAddress,
					manageaddressdropdownvalue, existingcityselectionmode, newcityselectionmode, cityname, citycode,
					existingsiteselectionmode, newsiteselectionmode, sitename, sitecode, existingpremiseselectionmode,
					newpremiseselectionmode, premisename, premisecode);
		}

	}

	public void verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_10G(String application, String cpename,
			String vender, String snmpro, String managementAddress, String Mepid, String poweralarm,
			String MediaselectionActualValue, String Macaddress, String serialNumber, String hexaSerialnumber,
			String linkLostForwarding, String country, String City, String Site, String Premise,
			String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode,
			String existingsiteselectionmode, String newsiteselectionmode, String sitename, String sitecode,
			String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode,
			String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields for CPE device under Intermediate Equipment");

		try {

			String linklostForwardingcheckboxstate = "disabled";

			String[] Vender = { "Accedian 10GigE-MetroNode-CE-2Port", "Accedian 10GigE-MetroNode-CE-4Port" };

			String[] powerAlarm = { "DC Single Power Supply - PSU A", "DC Dual Power Supply - PSU-A+B" };

			String expectedDeviceNameFieldAutopopulatedValue = "<Device>-10G.lanlink.dcn.colt.net";

			String MEPid = "5555";

			String expectedValueForSnmpro = "JdhquA5";

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			Thread.sleep(3000);

			// name field Error Message
			device_nameFieldWarningMessage(application);

			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			// serial number Eror Message
			device_serialNumberWarningMessage(application);

			// Hexa serial Number
			device_hexaSerialNumberWarningMessage(application);

			// Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);

			// Vendor/Model
			device_vendorModel(application, Vender, vender);

			// Snmpro
			device_snmPro(application, snmpro);

			// Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);

			// MEP Id
			device_mepID(application, Mepid);

			// Power Alarm
			device_powerAlarm(application, powerAlarm, poweralarm);

			// Serial Number
			device_serialNumber(application, serialNumber);

			// Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);

			// Country
			device_country(application, country);

			// verify fields for City, Site and premise
			device_verifycity(application);

			// City
			if (existingcityselectionmode.equalsIgnoreCase("no") & newcityselectionmode.equalsIgnoreCase("yes")) {
				addCityToggleButton(application);
				// New City
				newcity(application, newcityselectionmode, cityname, citycode);
				// New Site
				newSite(application, newsiteselectionmode, sitename, sitecode);
				// New Premise
				newPremise(application, newpremiseselectionmode, premisename, premisecode);

			}

			else if (existingcityselectionmode.equalsIgnoreCase("yes") & newcityselectionmode.equalsIgnoreCase("no")) {
				// Existing City
				existingCity(application, existingcityselectionmode, City);

				// Site

				if (existingsiteselectionmode.equalsIgnoreCase("yes") & newsiteselectionmode.equalsIgnoreCase("no")) {
					// Existing Site
					existingSite(application, existingsiteselectionmode, Site);

					// Premise
					if (existingpremiseselectionmode.equalsIgnoreCase("yes")
							& newpremiseselectionmode.equalsIgnoreCase("no")) {
						existingPremise(application, existingpremiseselectionmode, Premise);

					} else if (existingpremiseselectionmode.equalsIgnoreCase("no")
							& newpremiseselectionmode.equalsIgnoreCase("yes")) {
						// New Premise
						addPremiseTogglebutton(application);
						newPremise_clickOnPremisetoggleButton(application, newpremiseselectionmode, premisename,
								premisecode);
					}
				}

				else if (existingsiteselectionmode.equalsIgnoreCase("no")
						& newsiteselectionmode.equalsIgnoreCase("yes")) {
					// New Site
					addSiteToggleButton(application);
					newSite_ClickOnSiteTogglebutton(application, newsiteselectionmode, sitename, sitecode);

					// New Premise
					newPremise_clickonSiteToggleButton(application, newpremiseselectionmode, premisename, premisecode);
				}
			}

			// OK button
			device_okbutton(application);

			// cancel button
			device_cancelButton(application);

			

		} catch (AssertionError e) {

			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");

		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")));

	}

	public void verifyCPEdevicedataenteredForIntermediateEquipment_1G(String application, String cpename,
			String vender_Overture, String vender_Accedian, String snmpro, String managementAddress, String Mepid,
			String poweralarm_Overture, String powerAlarm_Accedian, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country,
			String existingCity, String existingSite, String existingPremise, String newmanagementAddressSelection,
			String existingmanagementAddressSelection, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode,
			String existingsiteselectionmode, String newsiteselectionmode, String sitename, String sitecode,
			String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode,
			String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {

		if (technologySelectedfordevicecreation.equalsIgnoreCase("Overture")) {

			viewdevice_Overture(application, cpename, vender_Overture, snmpro, managementAddress, Mepid,
					poweralarm_Overture, MediaselectionActualValue, Macaddress, linkLostForwarding, country,
					existingCity, cityname, existingSite, sitename, existingPremise, premisename,
					existingcityselectionmode, newcityselectionmode, existingsiteselectionmode, newsiteselectionmode,
					newmanagementAddressSelection, existingmanagementAddressSelection, manageaddressdropdownvalue,
					existingpremiseselectionmode, newpremiseselectionmode);

		}

		if ((technologySelectedfordevicecreation.equalsIgnoreCase("Accedian"))
				|| (technologySelectedfordevicecreation.equalsIgnoreCase("Accedian-1G"))) {

			viewdevice_Accedian(application, cpename, vender_Accedian, snmpro, managementAddress, Mepid,
					powerAlarm_Accedian, MediaselectionActualValue, Macaddress, serialNumber, hexaSerialnumber,
					linkLostForwarding, country, existingCity, cityname, existingSite, sitename, existingPremise,
					premisename, existingcityselectionmode, newcityselectionmode, existingsiteselectionmode,
					newsiteselectionmode, newmanagementAddressSelection, existingmanagementAddressSelection,
					manageaddressdropdownvalue, existingpremiseselectionmode, newpremiseselectionmode);
		}

	}

	public void verifyCPEdevicedataenteredForIntermediateEquipment_10G(String application, String cpename,
			String vender, String snmpro, String managementAddress, String Mepid, String poweralarm,
			String MediaselectionActualValue, String Macaddress, String serialNumber, String hexaSerialnumber,
			String linkLostForwarding, String existingcountry, String existingCity, String existingSite,
			String existingPremise, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue, String existingcityselectionmode, String newcityselectionmode,
			String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode,
			String premisename, String premisecode, String technologySelectedfordevicecreation)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered while creating device");

		String[] RouterId = new String[2];
		RouterId = cpename.split(".lanlink");

		String RouterIdValue = RouterId[0];

		String mediaSelectionValueInViewDevicePage = "no";
		if (MediaselectionActualValue.equalsIgnoreCase("null")) {
			MediaselectionActualValue = mediaSelectionValueInViewDevicePage;
		} else {
			MediaselectionActualValue = mediaSelectionValueInViewDevicePage;
		}

		verifyEnteredvalues_deviceName("Name", RouterIdValue, cpename);

		verifyEnteredvalues("Router Id", RouterIdValue);

		verifyEnteredvalues("Vendor/Model", vender);

		verifyEnteredvalues("Snmpro", snmpro);

		// Management Address
		if ((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		} else if ((existingmanagementAddress.equalsIgnoreCase("no"))
				&& (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Management Address", managementAddress);
		}

		verifyEnteredvalues("Power Alarm", poweralarm);

		verifyEnteredvalues("Media Selection", MediaselectionActualValue);

		verifyEnteredvalues("Serial Number", serialNumber);

		verifyEnteredvalues("Link Lost Forwarding", linkLostForwarding);

		verifyEnteredvalues("Country", existingcountry);

		// City
		if ((existingcityselectionmode.equalsIgnoreCase("Yes")) && (newcityselectionmode.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("City", existingCity);
		} else if ((existingcityselectionmode.equalsIgnoreCase("no"))
				&& (newcityselectionmode.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("City", cityname);
		}

		// Site
		if ((existingsiteselectionmode.equalsIgnoreCase("Yes")) && (newsiteselectionmode.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Site", existingSite);
		} else if ((existingsiteselectionmode.equalsIgnoreCase("no"))
				&& (newsiteselectionmode.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Site", sitename);
		}

		// Premise
		if ((existingpremiseselectionmode.equalsIgnoreCase("Yes"))
				&& (newpremiseselectionmode.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Site", existingPremise);
		} else if ((existingpremiseselectionmode.equalsIgnoreCase("no"))
				&& (newpremiseselectionmode.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Site", premisename);
		}

	}

	public void EDITCPEdevicedforIntermediateEquipment_1G(String application, String Cpename, String vender_1G_Overture,
			String vender_1G_Accedian, String vender_10G_Accedian, String snmpro, String managementAddress,
			String Mepid, String poweralarm_Overture, String poweralarm_Accedian, String Mediaselection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country,
			String City, String Site, String Premise, String technologySelected)
			throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		Thread.sleep(3000);

		if (technologySelected.equalsIgnoreCase("Overture")) {

			eDITCPEdevicedetailsentered_1G_Overture(application, Cpename, vender_1G_Overture, snmpro, managementAddress,
					Mepid, poweralarm_Overture, Mediaselection, Macaddress, serialNumber, hexaSerialnumber,
					linkLostForwarding, Country, City, Site, Premise);
		} else if ((technologySelected.equalsIgnoreCase("Accedian"))
				|| (technologySelected.equalsIgnoreCase("Accedian-1G"))) {

			eDITCPEdevicedetailsentered_1G_Accedian(application, Cpename, vender_1G_Accedian, snmpro, managementAddress,
					Mepid, poweralarm_Accedian, Mediaselection, Macaddress, serialNumber, hexaSerialnumber,
					linkLostForwarding, Country, City, Site, Premise);
		}

	}

	public void EDITCPEdevice_IntermediateEquipment_10G(String application, String Cpename, String vender_10G_Accedian,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String Mediaselection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country,
			String City, String Site, String Premise, String technologySelected)
			throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		Thread.sleep(3000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device");

		System.out.println("Entered edit functionalitty");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));

		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");

		// Name field
		device_editnamefield(application, Cpename);

		// vendor/model
		device_editVendorModelField(application, vender_10G_Accedian);

		// Snmpro
		device_editSnmproField(application);

		// Management address
		device_editManagementAddressField(application, managementAddress);

		// Mepid
		device_editMEPIdField(application, Mepid);

		// power alarm
		device_editPowerAlarm(application, poweralarm);

		// Serial Number
		device_editserialnumber(application, serialNumber);

		// linklost forwarding
		device_editlinkLostforwarding(application, linkLostForwarding);

		scrolltoend();
		Thread.sleep(3000);

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);

	}

	public void EDITCPEdevicedforIntEquipment_1G_Overture(String application, String Cpename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String Mediaselection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country,
			String City, String Site, String Premise, String technologySelected)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "edit CPE device under Equipment");

		System.out.println("Entered edit functionalitty");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));

		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");

//		Clickon(getwebelement("(//div[div[div[text()='Equipment']]]//div[div[text()='"+ devicename+"']]/div/a/span[text()='Edit'])[2]"));

//		  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevlielink_underEquipment")));

		// Name field
		device_editnamefield(application, Cpename);

		// vendor/model
		device_editVendorModelField(application, vender);

		// Snmpro
		device_editSnmproField(application);

		// Management address
		device_editManagementAddressField(application, managementAddress);

		// Mepid
		device_editMEPIdField(application, Mepid);

		// power alarm
		device_editPowerAlarm(application, poweralarm);

		// Media Selection
		device_editMediaselection(application, Mediaselection);

		// Mac address
		device_editMACaddress(application, Macaddress);

		// linklost forwarding
		device_editlinkLostforwarding(application, linkLostForwarding);

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(2000);

	}

	public void deleteDeviceFromServiceForequipment(String application, String deleteDeviceSelection, String devicename)
			throws InterruptedException, DocumentException {

		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'" + devicename
				+ "')]]]//span[text()='Delete from Service']"));
		System.out.println(" 'Delete From Service' link has been clicked for cpe device under Equipment");
		Log.info(" 'Delete From Service' link has been clicked for cpe device under Equipment");
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" 'Delete From Service' link has been clicked for cpe device under Equipment");

		boolean deletemessage = getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment"))
				.isDisplayed();
		while (deletemessage) {
			Log.info("Are you sure want to delete - message is getting displayed on clicking DeletefromService link");
			Log.info("Delete popup message is getting displayed");
			String actualMessage = getwebelement(
					xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Delete device popup is displaying and popup message displays as: " + actualMessage);
			System.out.println("Delete device popup is displaying and popup message displays as: " + actualMessage);
			break;
		}

		if (deleteDeviceSelection.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " device has got deleted from service");
			Log.info("Device got deleted from service as expected");

		} else {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/xbuttonfordeletefromservicepopup_Equipment")));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " device is not deleted from service as expected");
			Log.info("Device is not deleted from service as expected");

		}
	}

	public void deleteDeviceFromService_EquipmentConfig_Actelis(String application, String devicename)
			throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();

		scrolltoend();

		click_commonMethod(application, "delete_link", "actelis_EquipConfig_deleteLink", xml);

		Log.info(" 'Delete From Service' link has been clicked for cpe device under 'Equipment Configuration' panel");
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" 'Delete From Service' link has been clicked for cpe device under 'Equipment Configuration' panel");

		boolean deletemessage = getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment"))
				.isDisplayed();
		while (deletemessage) {
			Log.info("Are you sure want to delete - message is getting displayed on clicking DeletefromService link");
			Log.info("Delete popup message is getting displayed");
			String actualMessage = getwebelement(
					xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Delete device popup is displaying and popup message displays as: " + actualMessage);
			System.out.println("Delete device popup is displaying and popup message displays as: " + actualMessage);
			break;
		}

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "click on 'delete' button");
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " device has got deleted from service");
		Log.info("Device got deleted from service as expected");

	}

	public void successMessage_deleteFromService(String application) throws InterruptedException, DocumentException {

		boolean successMessage = getwebelement(
				xml.getlocator("//locators/" + application + "/deleteDevice_successmEssage")).isDisplayed();
		String actualmessage = getwebelement(
				xml.getlocator("//locators/" + application + "/deleteDevice_successmEssage")).getText();
		if (successMessage) {

			ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for device deletion is dipslaying as expected");
			System.out.println(" Success Message for device deletion is dipslaying as expected");

			ExtentTestManager.getTest().log(LogStatus.PASS, "Message displays as: " + actualmessage);
			System.out.println("Message displays as: " + actualmessage);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for device deletion is not dipslaying");
			System.out.println(" Success Message for device deletion is not dipslaying");
		}
	}

	public void deleteDeviceFromServiceForIntermediateequipment(String application, String deleteDeviceSelection,
			String devicename) throws InterruptedException, DocumentException {

		Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"
				+ devicename + "')]]]//span[text()='Delete from Service']"));
		System.out.println(" 'Delete From Service' link has been clicked for cpe device under Intermediate Equipment");
		Log.info(" 'Delete From Service' link has been clicked for cpe device under Intermediate Equipment");
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" 'Delete From Service' link has been clicked for cpe device under Intermediate Equipment");

		boolean deletemessage = getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment"))
				.isDisplayed();
		while (deletemessage) {
			Log.info("Are you sure want to delete - message is getting displayed on clicking DeletefromService link");
			Log.info("Delete popup message is getting displayed");
			String actualMessage = getwebelement(
					xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).getText();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Delete device popup is displaying and popup message displays as: " + actualMessage);
			System.out.println("Delete device popup is displaying and popup message displays as: " + actualMessage);
			break;
		}

		if (deleteDeviceSelection.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " device has got deleted from service");
			Log.info("Device got deleted from service as expected");

		} else {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/xbuttonfordeletefromservicepopup_Equipment")));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, devicename + " device is not deleted from service as expected");
			Log.info("Device is not deleted from service as expected");

		}

	}

	public void VerifythefieldsforProviderEquipment(String application) throws InterruptedException, DocumentException {

		try {

			boolean IMSlocation = getwebelement(
					xml.getlocator("//locators/" + application + "/AddPElink_LANlinkoutband")).isDisplayed();
			sa.assertTrue(IMSlocation, " IMS location dropdown is not displaying under Provider Equipment ");

			boolean togglebutton = getwebelement(
					xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")).isDisplayed();
			sa.assertTrue(togglebutton, "Toggle button is not disaplaying under Provider Equipment");

			boolean name = getwebelement(xml.getlocator("//locators/" + application + "/Name")).isDisplayed();
			sa.assertTrue(name, "NAme field is not displaying under Provider Equipment");

			boolean vendor = getwebelement(xml.getlocator("//locators/" + application + "/VenderModel")).isDisplayed();
			sa.assertTrue(vendor, "Vendor/Model dropdown ");

			boolean address = getwebelement(xml.getlocator("//locators/" + application + "/managementaddress"))
					.isDisplayed();
			sa.assertTrue(address, "Management Address field ");

			boolean snmpro = getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")).isDisplayed();
			sa.assertTrue(snmpro, "SNM pro field");

			boolean Country = getwebelement(xml.getlocator("//locators/" + application + "/country")).isDisplayed();
			sa.assertTrue(Country, "Country dropdown");

			boolean city = getwebelement(xml.getlocator("//locators/" + application + "/city")).isDisplayed();
			sa.assertTrue(city, "City dropdown");

			boolean Site = getwebelement(xml.getlocator("//locators/" + application + "/site")).isDisplayed();
			sa.assertTrue(Site, "Sitedropdown");

			boolean Premise = getwebelement(xml.getlocator("//locators/" + application + "/premise")).isDisplayed();
			sa.assertTrue(Premise, "Premise dropdown");

			boolean Nextbutton = getwebelement(xml.getlocator("//locators/" + application + "/Next_Button"))
					.isDisplayed();
			sa.assertTrue(Nextbutton, "Next button");

			boolean cancelbutton = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton"))
					.isDisplayed();
			sa.assertTrue(cancelbutton, "Cancel button");

			

		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 *   For Dropdown _  Add Existig device
	 * @param application
	 * @param labelname
	 * @param xpath
	 * @param expectedValueToAdd
	 * @param xml
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	public void addDropdownValues_forExistingDevice(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
		  boolean availability=false;
		try {  
			
			waitForpageload();
			waitforPagetobeenable();
			
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " dropdown is displaying");
			  System.out.println(labelname + " dropdown is displaying");
			  
			  if(expectedValueToAdd.equalsIgnoreCase("null")) {
				  
				  ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
				  System.out.println(" No values selected under "+ labelname + " dropdown");
			  }else {
				  
				  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='×']"));
				  Thread.sleep(2000);
				  
				  waitForpageload();
				  waitforPagetobeenable();
				  
					Thread.sleep(2000);
				SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
				Thread.sleep(4000);
					
				  Clickon(getwebelement("(//div[contains(text(),'"+ expectedValueToAdd +"')])[1]"));
				  Thread.sleep(2000);
				  
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

	public void providerEquipment(String application, String chooseAdevice)
			throws InterruptedException, DocumentException, IOException {

		
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "AddPEDeviceLink", "AddPElink_LANlinkoutband" , xml);

		waitForpageload();
		waitforPagetobeenable();
		
		click_commonMethod(application, "Next", "AddnewlinkPe2CPE_Nextbutton", xml); // click o NExt button without
																						// select any value in dropdown

		
		warningMessage_commonMethod(application, "chooseAdevice_warningMessage", "Choose a Device", xml);

		// select value inside 'Choose A device' dropdown
		addDropdownValues_forExistingDevice(application, "Choose a Device", "chooseAdevice_dropdown_out", chooseAdevice,
				xml);

		click_commonMethod(application, "Next", "AddnewlinkPe2CPE_Nextbutton", xml);
		
		waitForpageload();
		waitforPagetobeenable();
	}

	public void clickOnCustomerPremiseEquipmentLink_addDevice(String application)
			throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(2000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addCPEdevice_Outband_out")));
		Thread.sleep(4000);

//		click_commonMethod(application, "Next", "AddnewlinkPe2CPE_Nextbutton", xml);  //click o NExt button without select any value in dropdown
//		
//		warningMessage_commonMethod(application, "chooseAdevice_warningMessage", "Choose a Device", xml);
//		
//		//select value inside 'Choose A device' dropdown
//		addDropdownValues_commonMethod(application, "Choose a Device", "chooseAdevice_dropdown_out", chooseAdevice, xml);
//		
//		click_commonMethod(application, "Next", "AddnewlinkPe2CPE_Nextbutton", xml);
	}

	public void verifyPEdeviceEnteredvalue(String application, String Pe_chooseAdeviceDropdown)
			throws IOException, InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		
		String actualDeviceName = null;
		String labelname = "Device Name";
		String ExpectedText = Pe_chooseAdeviceDropdown;
		scrollToTop();

		// Fetch device name
		WebElement deviceName = getwebelement(xml.getlocator("//locators/" + application + "/fetchDevicename_out"));
		String selecteddevicename = Gettext(deviceName);

		if (selecteddevicename.contains("...")) {
			actualDeviceName = selecteddevicename.substring(0, 10);
		} else {
			actualDeviceName = selecteddevicename;
		}

		// Compare device name
		if (Pe_chooseAdeviceDropdown.equals(actualDeviceName)) { 
			ExtentTestManager.getTest().log(LogStatus.PASS, " The Expected value for '" + labelname + "' field '"
					+ ExpectedText + "' is same as the Acutal value '" + actualDeviceName + "'");
			System.out.println(" The Expected value for '" + labelname + "' field '" + ExpectedText
					+ "' is same as the Acutal value '" + actualDeviceName + "'");
		} else if (Pe_chooseAdeviceDropdown.contains(actualDeviceName)) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " The Expected value for '" + labelname + "' field '"
					+ ExpectedText + "' is same as the Acutal value '" + actualDeviceName + "'");
			System.out.println(" The Expected value for '" + labelname + "' field '" + ExpectedText
					+ "' is same as the Acutal value '" + actualDeviceName + "'");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " The Expected value for '" + labelname + "' field '"
					+ ExpectedText + "' is not same as the Acutal value '" + actualDeviceName + "'");
			System.out.println(" The Expected value for '" + labelname + "' field '" + ExpectedText
					+ "' is not same as the Acutal value '" + actualDeviceName + "'");
		}

		// fetching the list of values displaying in view device page
		fetchValueFromViewPage(application, "Vendor/Model");
		fetchValueFromViewPage(application, "Management Address");
		fetchValueFromViewPage(application, "Snmpro");
		fetchValueFromViewPage(application, "Country");
		fetchValueFromViewPage(application, "City");
		fetchValueFromViewPage(application, "Site");
		fetchValueFromViewPage(application, "Premise");
	}

	public void verifyAddDSLAMandHSLlink(String application, String DSLMdevice)
			throws InterruptedException, DocumentException {

		boolean actelisConfigurationPanel = false;

		waitForpageload();
		waitforPagetobeenable();

		scrolltoend();

		actelisConfigurationPanel = getwebelement(
				xml.getlocator("//locators/" + application + "/ActelisConfigurationPanel")).isDisplayed();

		if (actelisConfigurationPanel) {
			System.out.println(" 'Actelis Configuration' panel is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Actelis Configuration' panel is displaying as expected");

			boolean actelisLink = false;
			try {
				actelisLink = getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM"))
						.isDisplayed();
				if (actelisLink) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add DSLAM and HSL' link is displaying as expected");

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM")));
					Thread.sleep(3000);

				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel");
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" ''Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel ");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to click on 'Add DSLAM and HSL' link");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Actelis Configuration' panel is not displaying");
		}

	}

	public void selectInterfacelinkforEqipment(String Application, String devicename)
			throws InterruptedException, DocumentException {
		Thread.sleep(5000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "check 'Select Interface' link");
		scrolltoend();
		Thread.sleep(3000);

		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'" + devicename
				+ "')]]]//span[text()='Select Interfaces']"));
		System.out.println("SelectInterface link for Equipment is selected");
		Log.info("Select an inertface to add with the service under equipment");
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Select Interface link has been clicked for cpe device under Equipment");

		Thread.sleep(3000);
	}

	// div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'67bts.lanlink.dcn.colt.net')]]]//span[text()='Select
	// Interfaces']

	public void selectInterfacelinkforProviderEqipment(String Application)
			throws InterruptedException, DocumentException {
		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Providerequipment_selectinterface")));
		System.out.println("SelectInterface link for Equipment is selected");
		Log.info("Select an inertface to add with the service under equipment");

	}

	public void selectInterfacelinkforIntermediateEqipment(String Application, String devicename)
			throws InterruptedException, DocumentException {

		try {
			Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"
					+ devicename + "')]]]//span[text()='Select Interfaces']"));
			System.out.println("SelectInterface link for Intermediate Equipment is selected");
			Log.info("Select an inertface to add with the service under Intermediate equipment");
			ExtentTestManager.getTest().log(LogStatus.PASS, "For " + devicename
					+ " 'Select Interface' link has been clicked for cpe device under Intermediate Equipment");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Select Interface link is not available under Intermediate Equipment");
		}

	}

	public void SelectInterfacetoaddwithservcie(String Application, String interfacenumber)
			throws InterruptedException, DocumentException, IOException {

		selectrowforInterfaceToselecttable(Application, interfacenumber);

	}

	public void SelectInterfacetoremovefromservice(String Application, String interfacename)
			throws IOException, InterruptedException, DocumentException {

		selectRowforInterfaceInService(Application, interfacename);

	}

	public void verifyInterfaceaddedToService(String application, String interfacenumber) {

		try {
			boolean result = driver.findElement(By.xpath(
					"//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"
							+ interfacenumber + "']]//input"))
					.isDisplayed();

			if (result) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Verified: " + interfacenumber + " has been added to service");

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Verified: " + interfacenumber + " is not added to service");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failure while verifying whether interface got added to service");
		}

	}

	public void verifyInterfaceremovedFromService(String application, String interfacenumber)
			throws InterruptedException, DocumentException {

		try {
			boolean result = driver.findElement(By.xpath("")).isDisplayed();
			// needs to star scripting from here
			if (result) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Verified: " + interfacenumber + " has been removed from the service service");

			} else {

				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"The selected interface " + interfacenumber + " is not removed from the service");

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failure while verifying the removed interface from the service");
		}

	}

	public void selectrowforInterfaceToselecttable(String Application, String interfacenumber)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_totalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in Interface to select table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements(
						"//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[div[text()='Client']]//span[@class='ag-icon ag-icon-checkbox-unchecked']");

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceToselect(Application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS,
										interfacenumber + " is selected under 'Interface to select' table");
								Thread.sleep(8000);
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + Application + "/InterfaceToselect_Actiondropdown")));

								Thread.sleep(5000);

								Clickon(getwebelement(
										xml.getlocator("//locators/" + Application + "/InterfaceToselect_addbuton")));
								Thread.sleep(3000);
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacenumber + " is added to service");

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("(//div[@class='row'])[8]//div[div[contains(text(),'"
									+ interfacenumber + "')]]//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									" Failure on selecting an Interface to ad with service ");

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

	public void selectRowforInterfaceInService(String Application, String interfacenumber)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_totalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath(
						"//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"
								+ interfacenumber + "']]//span[@class='ag-icon ag-icon-checkbox-unchecked']"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPageForInterfaceToselect(Application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS,
										interfacenumber + " is selected under 'Interface in Service' table");
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + Application + "/InterfaceInselect_Actiondropdown")));

								Thread.sleep(3000);

								Clickon(getwebelement(xml
										.getlocator("//locators/" + Application + "/InterfaceInselect_removebuton")));
								ExtentTestManager.getTest().log(LogStatus.PASS,
										interfacenumber + " has been selected to get removed from service");

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath(
									"//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"
											+ interfacenumber + "']]//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"failure while selecting interface to remove from service");

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

	public void PageNavigation_NextPageForInterfaceInService(String Application)
			throws InterruptedException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_nextpage")));
		Thread.sleep(3000);

	}

	public void selectconfigurelinkAndverifyEditInterfacefield(String application, String devicename,
			String interfacename) throws InterruptedException, DocumentException, IOException {

		try {

			Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'" + devicename
					+ "')]]]//span[text()='Configure']"));

			Thread.sleep(3000);

			selectRowForconfiglinkunderEquipmentconfig(application, interfacename);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
			Thread.sleep(1000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
			Thread.sleep(3000);

			boolean XNGcircuitID = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
			sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");

			System.out.println("Entering bearer type dropdown");
			boolean BearerTypedropdown = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
			sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");

			boolean Bearerspeed = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
			sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");

			boolean bandwidth = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth"))
							.isDisplayed();
			sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");

			boolean vlan = getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid"))
					.isDisplayed();
			sa.assertTrue(vlan, "VLAN ID field is not displaying");

			boolean vlantype = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
			sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");
			System.out.println("vlan type failed");

			boolean ok = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
			sa.assertTrue(ok, "Ok Button is not displaying");

			boolean CANCEL = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
			sa.assertTrue(CANCEL, "Cancel Button is not displaying");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
			Thread.sleep(3000);

			

			ExtentTestManager.getTest().log(LogStatus.PASS, "Fields are verified for 'Edit Interface' under config link");
		} catch (AssertionError e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Field validation failed for 'Edit Interface' page");
		}

	}

	public void selectconfigurelinkAndverifyEditInterfacefield__Equipment(String application, String devicename)
			throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(3000);

		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'" + devicename
				+ "')]]]//span[text()='Configure']"));
		Thread.sleep(3000);

		// Try to edit without selecting the interface
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
		Thread.sleep(1000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);

		// check whether Alert message displays
		boolean alertMessage = false;
		try {
			alertMessage = getwebelement(xml.getlocator("//locators/" + application + "/configure_alertPopup"))
					.isDisplayed();
			if (alertMessage) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Alert popup displays, if we click on 'Edit' without selected the interface");
				System.out.println("Alert popup displays, if we click on 'Edit' without selected the interface");

				String alertMsg = getwebelement(xml.getlocator("//locators/" + application + "/configure_alertMessage"))
						.getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Alert message displays as: " + alertMsg);
				System.out.println(" Alert message displays as: " + alertMsg);

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/configure_alertPopup_xbutton")));
				Thread.sleep(3000);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
				System.out.println(
						"'Alert' popup is not displaying when we click on 'edit' without selecting an interface");

				boolean editInterface_popuptitleName = false;
				try {
					editInterface_popuptitleName = getwebelement(
							xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename"))
									.isDisplayed();
					if (editInterface_popuptitleName) {
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								"Edit interface popup is displaying without selected an interface");
						System.out.println("Edit interface popup is displaying without selected an interface");

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/EditInterfacepopup_xbutton")));
						Thread.sleep(3000);
					}
				} catch (Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Edit interface' popup is not displaying, without selecting an Interface");
					System.out.println(" 'Edit interface' popup is not displaying, without selecting an Interface");
				}

			}
		} catch (NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			System.out
					.println("'Alert' popup is not displaying when we click on 'edit' without selecting an interface");

		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			System.out
					.println("'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
		}

	}

	public void SelectShowInterfacelinkAndVerifyEditInterfacePage(String application, String interfacename,
			String devicename) throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_showinterfaceslink")));
		Thread.sleep(3000);

		selectRowForEditingInterface_showInterface(application, interfacename, devicename);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showintefaceedit")));
		Thread.sleep(3000);

	}

	public void SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage(String application,
			String interfacename, String devicename) throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_showinterfaceslink_intEquip")));
		Thread.sleep(3000);

		selectRowForEditingInterface_showInterface(application, interfacename, devicename);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showintefaceedit")));
		Thread.sleep(3000);

	}

	public void SelectShowInterfacelink_CustomerPremiseeequipmentAndVerifyEditInterfacePage(String application,
			String interfacename) throws InterruptedException, DocumentException, IOException {

		try {
			Thread.sleep(3000);
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/showInterface_ForIntermediateEquipment")));
			Thread.sleep(3000);

			selectRowUnderIntermediateEquipment(application, interfacename);

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/IntermediateEquipment_Actiondropdown")));
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateEquipment_Editlink")));
			Thread.sleep(3000);

			boolean XNGcircuitID = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
			sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
			System.out.println("circuit id is fetched");

			System.out.println("Entering bearer type dropdown");
			boolean BearerTypedropdown = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
			sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
			System.out.println("bearer type dropdown is fetchecd");

			boolean Bearerspeed = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
			sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");

			boolean bandwidth = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth"))
							.isDisplayed();
			sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");

			boolean vlan = getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid"))
					.isDisplayed();
			sa.assertTrue(vlan, "VLAN ID field is not displaying");

			boolean vlantype = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
			sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");

			boolean ok = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
			sa.assertTrue(ok, "Ok Button is not displaying");

			boolean CANCEL = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
			sa.assertTrue(CANCEL, "Cancel Button is not displaying");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
			Thread.sleep(3000);

			
		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}

	public void enterdataInsideEditInterfacepage(String application) {

	}

	public void selectRowForEditingInterface_showInterface(String Application, String interfacename, String devicename)
			throws InterruptedException, DocumentException, IOException {

		int TotalPages;

		String TextKeyword = getwebelement("(//div[div[div[contains(@title,'" + devicename
				+ "')]]]/following-sibling::div)[1]//span[@ref='lbTotal']").getText();

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab: if (TotalPages != 0) {

			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = getwebelement("(//div[div[div[contains(@title,'" + devicename
						+ "')]]]/following-sibling::div)[1]//span[@ref='lbCurrent']").getText();
				int Current_page = Integer.parseInt(CurrentPage);
				System.out.println("The current page is: " + Current_page);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements("(//div[div[div[contains(@title,'" + devicename
						+ "')]]]/following-sibling::div)[1]//div[text()='" + interfacename + "']");

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if ((numofrows == 0)) {

					Clickon(getwebelement("(//div[div[div[contains(@title,'" + devicename
							+ "')]]]/following-sibling::div)[1]//button[text()='Next']"));
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
								ExtentTestManager.getTest().log(LogStatus.PASS,
										interfacename + " is selected under 'show Interface' ");
								Thread.sleep(8000);
								Clickon(getwebelement("(//div[div[div[contains(@title,'" + devicename
										+ "')]]]/following-sibling::div)[1]//button[text()='Action']"));

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath(
									"(//div[@class='ag-root-wrapper ag-layout-auto-height ag-ltr'])[2]//div[text()='"
											+ interfacename + "']"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

						}

					}

				}

			}
		} else {

			System.out.println("No values available in table");
			Log.info("No values available inside the InterfaceInService table");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No value available inside 'show Interface' panel");
		}
	}

	public void selectRowForshowInterfaceunderProviderEquipment(String Application, String interfacename)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

		String TextKeyword = Gettext(getwebelement(
				xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceTatoalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab:

		for (int k = 1; k <= TotalPages; k++) {

			// Current page
			String CurrentPage = Gettext(getwebelement(
					xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceCurrentpage")));
			int Current_page = Integer.parseInt(CurrentPage);
			System.out.println("The current page is: " + Current_page);

			assertEquals(k, Current_page);

			System.out.println("Currently we are in page number: " + Current_page);

			List<WebElement> results = driver
					.findElements(By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"
							+ interfacename + "']]//input"));

			int numofrows = results.size();
			System.out.println("no of results: " + numofrows);
			boolean resultflag;

			if (numofrows == 0) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceNextpage")));

			}

			else {

				for (int i = 0; i < numofrows; i++) {

					try {

						resultflag = results.get(i).isDisplayed();
						System.out.println("status of result: " + resultflag);
						if (resultflag) {
							System.out.println(results.get(i).getText());
							results.get(i).click();
							Thread.sleep(8000);

						}

					} catch (StaleElementReferenceException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						results = driver.findElements(
								By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"
										+ interfacename + "']]//input"));
						numofrows = results.size();
						// results.get(i).click();
						Log.info("selected row is : " + i);

					}

				}

			}

		}

	}

	public void selectRowForshowInterfaceunderCustomerPremiseEquipment(String Application, String interfacename)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

		String TextKeyword = Gettext(getwebelement(
				xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceTatoalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab:

		for (int k = 1; k <= TotalPages; k++) {

			// Current page
			String CurrentPage = Gettext(getwebelement(
					xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceCurrentpage")));
			int Current_page = Integer.parseInt(CurrentPage);
			System.out.println("The current page is: " + Current_page);

			assertEquals(k, Current_page);

			System.out.println("Currently we are in page number: " + Current_page);

			List<WebElement> results = driver
					.findElements(By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"
							+ interfacename + "']]//input"));

			int numofrows = results.size();
			System.out.println("no of results: " + numofrows);
			boolean resultflag;

			if (numofrows == 0) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceNextpage")));

			}

			else {

				for (int i = 0; i < numofrows; i++) {

					try {

						resultflag = results.get(i).isDisplayed();
						System.out.println("status of result: " + resultflag);
						if (resultflag) {
							System.out.println(results.get(i).getText());
							results.get(i).click();
							Thread.sleep(8000);

						}

					} catch (StaleElementReferenceException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						results = driver.findElements(
								By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"
										+ interfacename + "']]//input"));
						numofrows = results.size();
						// results.get(i).click();
						Log.info("selected row is : " + i);

					}

				}

			}

		}

	}

	public void selectRowForconfiglinkunderEquipmentconfig(String Application, String interfacename)
			throws InterruptedException, DocumentException, IOException {

		int TotalPages;

		// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/Equipmentconfig_Totalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/Equipmentconfig_Currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);
				System.out.println("The current page is: " + Current_page);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver
						.findElements(By.xpath("//div[div[text()='Interfaces']]/following-sibling::div[1]//div[text()='"
								+ interfacename + "']"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Equipmentconfig_nextpage")));
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
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected");
								Thread.sleep(3000);
								break ab;
							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath(
									"//div[div[text()='Interfaces']]/following-sibling::div[1]//div[text()='\"+interfacename +\"']"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

						}

					}

				}

			}
		} else {
			System.out.println("No values available in table");
			Log.info("No values available inside the InterfaceInService table");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No value available inside the table for configure ");
		}
	}

	public void selectRowForconfiglinkunderIntermediateEquipment(String Application, String interfacename)
			throws InterruptedException, DocumentException, IOException {

		int TotalPages;

		// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/configure_totalPage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab: if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/configure_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);
				System.out.println("The current page is: " + Current_page);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver
						.findElements(By.xpath("//div[@role='row']//div[text()='" + interfacename + "']"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/intermediateequip_nextpage")));

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS, interfacename + " is selected");
								Thread.sleep(8000);
							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver
									.findElements(By.xpath("//div[@role='row']//div[text()='" + interfacename + "']"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

						}

					}

				}

			}
		} else {
			System.out.println("No values present inside the table");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values present inside the table");
		}
	}

	public void selectRowUnderIntermediateEquipment(String Application, String interfacename)
			throws InterruptedException, DocumentException, IOException {

		int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/totalpage_intermeiateshowinterfacelink")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(getwebelement(
						xml.getlocator("//locators/" + Application + "/currentpage_intermeduateshowinterfacelink")));
				int Current_page = Integer.parseInt(CurrentPage);
				System.out.println("The current page is: " + Current_page);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(
						By.xpath("(//div[@class='ag-root-wrapper ag-layout-auto-height ag-ltr'])[8]//div[div[text()='"
								+ interfacename + "']]//input"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + Application + "/pagenavigateforIntermediate")));

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								ExtentTestManager.getTest().log(LogStatus.PASS,
										interfacename + " is selected after clicking on 'show Interface' ");
								Thread.sleep(8000);

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath(""));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

						}

					}

				}

			}
		} else {

			System.out.println("No values available in table");
			Log.info("No values available inside the InterfaceInService table");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No value available inside 'show Interface' panel");
		}

	}

	public void VerifyDataEnteredForSiteOrder(String application, String interfaceSpeed, String VPNtopology,
			String circuitType, String country, String city, String CSR_Name, String site, String performReport,
			String ProactiveMonitor, String smartmonitor, String technology, String siteallias, String VLANid,
			String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark, String xngcityname,
			String xngcitycode, String devicename, String nonterminatepoinr, String Protected, String newcityselection,
			String existingcityselection, String existingsiteselection, String newsiteselection, String siteOrderNumber,
			String circuitref, String offnetSelection, String IVReference, String GCRolo, String Vlan, String Vlanether,
			String primaryVlan, String primaryVlanether, String EPNoffnet, String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException {

			VerifyDataEnteredForSiteOrder_viewSiteOrder_P2P(application, country, city, CSR_Name, site, performReport,
					ProactiveMonitor, smartmonitor, technology, siteallias, VLANid, DCAenabledsite,
					cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename, nonterminatepoinr,
					Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection);

		
	}

	public void VerifyDataEnteredForIPVPNSiteOrder(String application, String interfaceSpeed, String VPNtopology,
			String circuitType, String country, String city, String CSR_Name, String site, String performReport,
			String ProactiveMonitor, String smartmonitor, String technology, String siteallias, String VLANid,
			String DCAenabledsite, String cloudserviceprovider, String existingSitevalue, String remark, String xngcityname,
			String xngcitycode, String devicename, String nonterminatepoinr, String Protected, String newcityselection,
			String existingcityselection, String existingsiteselection, String newsiteselection, String siteOrderNumber,
			String IVReference, String perCoSPerformancereport, String primarySiteOrder,
			String routerConfigurationViewIpv4, String wholesaleProvider, String managedSite, String priority,
			String actelisBased, String voip, String voipClassOfService)
			throws InterruptedException, DocumentException, IOException {

		scrollToTop();

		// Site Order Number
		verifyEnteredvalues("Site Order Number", siteOrderNumber);

		// Country
		verifyEnteredvalues("Device Country", country);

		// City

		if ((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

			// City
			verifyEnteredvalues("Device Xng City", city);

			// Site
			if ((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
				verifyEnteredvalues("CSR Name", existingSitevalue);
			}

			else if ((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
				verifyEnteredvalues("CSR Name", CSR_Name);
			}

		} else if ((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {

			// New City
			verifyEnteredvalues("Device Xng City", xngcitycode);

			// New Site
			verifyEnteredvalues("CSR Name", CSR_Name);

		}

		// Performance Reporting
		verifyEnteredvalues("Performance Reporting", performReport);

		// Proactive Monitoring
		verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);

		// Smarts Monitoring
		verifyEnteredvalues("Smarts Monitoring", smartmonitor);

		// Per CoS Performance reporting
		verifyEnteredvalues("Per CoS Performance Reporting", perCoSPerformancereport);

		// Site Alias
		verifyEnteredvalues("Site Alias", siteallias);

		// Primary Site Order
		verifyEnteredvalues("Primary Site Order", primarySiteOrder);

		// IV Reference
		verifyEnteredvalues("IV Reference", IVReference);

		// Priority
		verifyEnteredvalues("Priority", priority);

		// Managed Site
		verifyEnteredvalues("Managed Site", managedSite);

		// Router Configuration View IPv4
		verifyEnteredvalues("Router Configuration View IPv4", routerConfigurationViewIpv4);

		// Actelis Based
		verifyEnteredvalues("Actelis Based", actelisBased);

		// Wholesale Provider
		verifyEnteredvalues("Wholesale Provider", wholesaleProvider);

		// VoIP
		verifyEnteredvalues("VoIP", voip);

		// VoIP Class of Service
		verifyEnteredvalues("VoIP Class of Service", voipClassOfService);

		// Remark
		compareText(application, "Remark", "remark_viewPage", remark, xml);

	}

	public void VerifyDataEnteredForSiteOrder_viewSiteOrder_P2P(String application, String country, String city,
			String CSR_Name, String site, String performReport, String ProactiveMonitor, String smartmonitor,
			String technology, String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider,
			String sitevalue, String remark, String xngcityname, String xngcitycode, String devicename,
			String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection)
			throws InterruptedException, DocumentException, IOException {

		// Country
		verifyEnteredvalues("Device Country", country);

		// City

		if ((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

			// City
			verifyEnteredvalues("Device Xng City", city);

			// Site
			if ((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
				verifyEnteredvalues("CSR Name", site);
			}

			else if ((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
				verifyEnteredvalues("CSR Name", CSR_Name);
			}

		} else if ((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {

			// New City
			verifyEnteredvalues("Device Xng City", xngcitycode);

			// New Site
			verifyEnteredvalues("CSR Name", CSR_Name);

		}

		// Performance Reporting
		verifyEnteredvalues("Performance Reporting", performReport);

		// Proactive Monitoring
		verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);

		// Smarts Monitoring
		verifyEnteredvalues("Smarts Monitoring", smartmonitor);

		// Technology
		verifyEnteredvalues("Technology", technology);

		// Site Alias
		verifyEnteredvalues("Site Alias", siteallias);

		// VLAN Id
		verifyEnteredvalues("VLAN Id", VLANid);

		// DCA Enabled Site
		verifyEnteredvalues("DCA Enabled Site", DCAenabledsite);

		if (DCAenabledsite.equalsIgnoreCase("Yes")) {

			// Cloud Service Provider
			verifyEnteredvalues("Cloud Service Provider", cloudserviceprovider);

		}

		// Remark
		compareText(application, "Remark", "remark_viewPage", remark, xml);

		if (technology.equals("Atrica")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);

		}

		if (technology.equals("Overture")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

		}

		if (technology.equals("Alu")) {
			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);
		}

		if (technology.equals("Accedian-1G")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

		}

		if (technology.equals("Cyan")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
		}
	}

	public void VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Primay(String application, String country,
			String city, String CSR_Name, String site, String performReport, String ProactiveMonitor,
			String smartmonitor, String technology, String siteallias, String VLANid, String DCAenabledsite,
			String cloudserviceprovider, String sitevalue, String remark, String xngcityname, String xngcitycode,
			String devicename, String nonterminatepoinr, String Protected, String newcityselection,
			String existingcityselection, String existingsiteselection, String newsiteselection, String siteOrderNumber,
			String circuitref, String offnetSelection, String IVReference, String GCRolo, String Vlan, String Vlanether,
			String primaryVlan, String primaryVlanether, String EPNoffnet, String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException {

		// Site order Number
		verifyEnteredvalues("Site Order Number", siteOrderNumber);

		// Country
		verifyEnteredvalues("Device Country", country);

		// City

		if ((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

			// City
			verifyEnteredvalues("Device Xng City", city);

			// Site
			if ((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
				verifyEnteredvalues("CSR Name", site);
			}

			else if ((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
				verifyEnteredvalues("CSR Name", CSR_Name);
			}

		} else if ((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {

			// New City
			verifyEnteredvalues("Device Xng City", xngcitycode);

			// New Site
			verifyEnteredvalues("CSR Name", CSR_Name);

		}

		// Performance Reporting
		verifyEnteredvalues("Performance Reporting", performReport);

		// Proactive Monitoring
		verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
		Thread.sleep(3000);

		// Smarts Monitoring
		verifyEnteredvalues("Smarts Monitoring", smartmonitor);

		// Technology
		verifyEnteredvalues("Technology", technology);

		// Site Alias
		verifyEnteredvalues("Site Alias", siteallias);

		// IV Reference
		verifyEnteredvalues("IV Reference", IVReference);

		// Remark
		compareText(application, "Remark", "remark_viewPage", remark, xml);

		if (technology.equals("Atrica")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);
		}

		if (technology.equals("Overture")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);
		}

		if (technology.equals("Alu")) {
			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);
		}

		if (technology.equals("Accedian-1G")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);
		}

		if (technology.equals("Cyan")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
		}

	}

	public void VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Primay(String application, String country, String city,
			String CSR_Name, String site, String performReport, String ProactiveMonitor, String smartmonitor,
			String technology, String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider,
			String sitevalue, String remark, String xngcityname, String xngcitycode, String devicename,
			String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection, String siteOrderNumber, String circuitref,
			String offnetSelection, String IVReference, String GCRolo, String Vlan, String Vlanether,
			String primaryVlan, String primaryVlanether, String EPNoffnet, String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException {

		// Site order Number
		verifyEnteredvalues("Site Order Number", siteOrderNumber);

		// Country
		verifyEnteredvalues("Device Country", country);

		// City

		if ((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

			// City
			verifyEnteredvalues("Device Xng City", city);

			// Site
			if ((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
				verifyEnteredvalues("CSR Name", site);
			}

			else if ((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
				verifyEnteredvalues("CSR Name", CSR_Name);
			}

		} else if ((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {

			// New City
			verifyEnteredvalues("Device Xng City", xngcitycode);

			// New Site
			verifyEnteredvalues("CSR Name", CSR_Name);

		}

		// Performance Reporting
		verifyEnteredvalues("Performance Reporting", performReport);

		// Proactive Monitoring
		verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);

		// Smarts Monitoring
		verifyEnteredvalues("Smarts Monitoring", smartmonitor);

		// Technology
		verifyEnteredvalues("Technology", technology);

		// Site Alias
		verifyEnteredvalues("Site Alias", siteallias);

		// IV Reference
		verifyEnteredvalues("IV Reference", IVReference);

		// Remark
		compareText(application, "Remark", "remark_viewPage", remark, xml);

		if (technology.equals("Atrica")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);
		}

		if (technology.equals("Overture")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);

			// EPN Offnet
			if (EPNoffnet.equalsIgnoreCase("yes")) {
				verifyEnteredvalues("EPN Offnet", EPNoffnet);
			} else {
				System.out.println("EPN offnet will not display, if it is selected");
			}

			// EPN EOSDH
			if (EPNEOSDH.equalsIgnoreCase("Yes")) {
				verifyEnteredvalues("EPN EOSDH", EPNEOSDH);
			} else {
				System.out.println("EPN EOSDH will not display, if it is not selected");
			}

		}

		if (technology.equals("Alu")) {
			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);
		}

		if (technology.equals("Accedian-1G")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);
		}

		if (technology.equals("Cyan")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
		}

	}

	public void VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Access(String application, String country, String city,
			String CSR_Name, String site, String performReport, String ProactiveMonitor, String smartmonitor,
			String technology, String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider,
			String sitevalue, String remark, String xngcityname, String xngcitycode, String devicename,
			String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection, String siteOrderNumber, String circuitref,
			String offnetSelection, String IVReference, String GCRolo, String Vlan, String Vlanether,
			String primaryVlan, String primaryVlanether, String EPNoffnet, String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException {

		// Site order Number
		verifyEnteredvalues("Site Order Number", siteOrderNumber);

		// Country
		verifyEnteredvalues("Device Country", country);

		// City

		if ((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

			// City
			verifyEnteredvalues("Device Xng City", city);

			// Site
			if ((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
				verifyEnteredvalues("CSR Name", site);
			}

			else if ((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
				verifyEnteredvalues("CSR Name", CSR_Name);
			}

		} else if ((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {

			// New City
			verifyEnteredvalues("Device Xng City", xngcitycode);

			// New Site
			verifyEnteredvalues("CSR Name", CSR_Name);

		}

		// Performance Reporting
		verifyEnteredvalues("Performance Reporting", performReport);

		// Proactive Monitoring
		verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);

		// Smarts Monitoring
		verifyEnteredvalues("Smarts Monitoring", smartmonitor);

		// Technology
		verifyEnteredvalues("Technology", technology);

		// Site Alias
		verifyEnteredvalues("Site Alias", siteallias);

		// IV Reference
		verifyEnteredvalues("IV Reference", IVReference);

		// Remark
		compareText(application, "Remark", "remark_viewPage", remark, xml);

		if (technology.equals("Atrica")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);
		}

		if (technology.equals("Overture")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);

			// EPN Offnet
			verifyEnteredvalues("EPN Offnet", EPNoffnet);

			// EPN EOSDH

		}

		if (technology.equals("Alu")) {
			// Device Name text field
			verifyEnteredvalues("Device Name", devicename);
		}

		if (technology.equals("Accedian-1G")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Device Name text field
//			verifyEnteredvalues("Device Name", devicename);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);
		}

		if (technology.equals("Cyan")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
		}

	}

	public void VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Access(String application, String country,
			String city, String CSR_Name, String site, String performReport, String ProactiveMonitor,
			String smartmonitor, String technology, String siteallias, String VLANid, String DCAenabledsite,
			String cloudserviceprovider, String sitevalue, String remark, String xngcityname, String xngcitycode,
			String devicename, String nonterminatepoinr, String Protected, String newcityselection,
			String existingcityselection, String existingsiteselection, String newsiteselection, String siteOrderNumber,
			String circuitref, String offnetSelection, String IVReference, String GCRolo, String Vlan, String Vlanether,
			String primaryVlan, String primaryVlanether, String EPNoffnet, String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException {

		// Site order Number
		verifyEnteredvalues("Site Order Number", siteOrderNumber);

		// Country
		verifyEnteredvalues("Device Country", country);

		// City

		if ((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

			// City
			verifyEnteredvalues("Device Xng City", city);

			// Site
			if ((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
				verifyEnteredvalues("CSR Name", site);
			}

			else if ((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
				verifyEnteredvalues("CSR Name", CSR_Name);
			}

		} else if ((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {

			// New City
			verifyEnteredvalues("Device Xng City", xngcitycode);

			// New Site
			verifyEnteredvalues("CSR Name", CSR_Name);

		}

		// Performance Reporting
		verifyEnteredvalues("Performance Reporting", performReport);

		// Proactive Monitoring
		verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);

		// Smarts Monitoring
		verifyEnteredvalues("Smarts Monitoring", smartmonitor);

		// Technology
		verifyEnteredvalues("Technology", technology);

		// Site Alias
		verifyEnteredvalues("Site Alias", siteallias);

		// IV Reference
		verifyEnteredvalues("IV Reference", IVReference);

		// Remark
		compareText(application, "Remark", "remark_viewPage", remark, xml);

		// Based on Technology
		if (technology.equals("Atrica")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);
		}

		if (technology.equals("Overture")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);

			// GCR OLO Type dropdown
			verifyEnteredvalues("GCR OLO Type", GCRolo);

			// VLAN tetx field
			verifyEnteredvalues("VLAN", Vlan);

			// VLAN Ether type dropdown
			verifyEnteredvalues("VLAN Ether Type", Vlanether);

			// Primary VLAN Text field
			verifyEnteredvalues("Primary VLAN", primaryVlan);

			// Primary VLAN Ether Type
			verifyEnteredvalues("Primary VLAN Ether Type", primaryVlanether);
		}

		if (technology.equals("Alu")) {

			System.out.println("No additional Fields");
		}

		if (technology.equals("Accedian-1G")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			// Protected checkbox
			verifyEnteredvalues("Protected", Protected);
		}

		if (technology.equals("Cyan")) {
			// Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
		}

	}

	public void VerifySiteOrderdetailsInTable(String Application, String siteordernumber)
			throws InterruptedException, DocumentException, IOException {

		Thread.sleep(5000);

		System.out.println("Entererd inside the table");

		int TotalPages;

		// scroll down the page
//		  JavascriptExecutor js = ((JavascriptExecutor) driver);
//		  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		  Thread.sleep(3000);

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/TotalPagesforsiteorder")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab:

		if (TotalPages >= 1) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/Currentpageforsiteorder")));
				int Current_page = Integer.parseInt(CurrentPage);

				System.out.println("Checking whether next page button is disabled or not");

				boolean nextpage = getwebelement(
						xml.getlocator("//locators/" + Application + "/Pagenavigationfornextpage")).isEnabled();

				System.out.println("Entered while loop");
				while (nextpage) {
					System.out.println("its enabled");
					break;
				}

				assertEquals(k, Current_page);

				List<WebElement> results = driver
						.findElements(By.xpath("//div[div[contains(text(),'" + siteordernumber + "')]]"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					PageNavigation_NextPage(Application);

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println("The field values are: " + results.get(i).getText());
								Log.info("The values stored in the table for adding site order are: "
										+ results.get(i).getText());

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver
									.findElements(By.xpath("//div[div[contains(text(),'" + siteordernumber + "')]]"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

						}

					}

					break ab;

				}

			}

		} else {
			Log.info("The data entered for adding site order is not getting displyed inside the site order table.");
			System.out.println("No values inside the site order table");
		}

	}

	public void returnbacktoviewsiteorderpage(String application) throws InterruptedException, DocumentException {

		scrolltoend();
		Thread.sleep(4000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Backbutton")));
		Thread.sleep(5000);
	}

	public void EnterdataForEditInterfaceforShowInterfacelinkunderEquipment(String application, String interfacename,
			String circuitID, String bearertype, String bearerspeed, String totalbandwidth, String vlanid,
			String vlantype) throws InterruptedException, DocumentException, IOException {

		// verify Edit Interface page
		boolean editInterface_popuptitleName = false;
		try {
			editInterface_popuptitleName = getwebelement(
					xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
			if (editInterface_popuptitleName) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Edit interface popup is displaying as expected");
				System.out.println("Edit interface popup is displaying as expected");

				// Interface name
				verifyenteredvaluesForEditPage_noneditableFields(application, "Interface", interfacename);

				// Circuit ID
				configure_circuitId(application, circuitID);

				// Bearer type
				addDropdownValues_commonMethod(application, "Bearer Type", "Equipment_configureBearerType", bearertype, xml);

				// Bearerspeed
				addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed",
						bearerspeed, xml);

				// Total Circuit bandwidth
				addDropdownValues_commonMethod(application, "Total Circuit Bandwidth",
						"Equipment_configureTotalcircuitbandwidth", totalbandwidth, xml);

				// VLAN type
				addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype, xml);

				// vlan
				addtextFields_commonMethod(application, "VLAN Id", "Equipment_configureVLANid", vlanid, xml);
				Thread.sleep(3000);

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Edit interface' popup is not displaying");
			System.out.println(" 'Edit interface' popup is not displaying");
		}

		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Data has been entered inside Edit Interface page by selecting configure link under Intermediate Equipment");

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);

	}

	public void EnterdataForEditInterfaceforShowInterfacelinkunderProviderEquipment(String application,
			String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
			String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showInterfaceActiondropdown")));
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")),
				circuitID);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")));
		Clickon(getwebelement("//div[text()='" + bearertype + "']"));

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")));
		Clickon(getwebelement("//div[text()='" + bearerspeed + "']"));

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")));
		Clickon(getwebelement("//div[text()='" + totalbandwidth + "']"));

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")), vlanid);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")));
		Clickon(getwebelement("//div[text()='" + vlantype + "']"));

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(5000);

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/CPEdevice_hideinterfaceslinkforequipment")));
		Thread.sleep(3000);

	}

	public void EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment(String application,
			String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
			String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {

		// verify Edit Interface page
		boolean editInterface_popuptitleName = false;
		try {
			editInterface_popuptitleName = getwebelement(
					xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
			if (editInterface_popuptitleName) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Edit interface popup is displaying as expected");
				System.out.println("Edit interface popup is displaying as expected");

				// Interface name
				verifyenteredvaluesForEditPage_noneditableFields(application, "Interface", interfacename);

				// Circuit ID
				configure_circuitId(application, circuitID);

				// Bearer type
				addDropdownValues_commonMethod(application, "Bearer Type", "Equipment_configureBearerType", bearertype, xml);

				// Bearerspeed
				addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed",
						bearerspeed, xml);

				// Total Circuit bandwidth
				addDropdownValues_commonMethod(application, "Total Circuit Bandwidth",
						"Equipment_configureTotalcircuitbandwidth", totalbandwidth, xml);

				// VLAN type
				addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype, xml);

				// vlan
				addtextFields_commonMethod(application, "VLAN Id", "Equipment_configureVLANid", vlanid, xml);
				Thread.sleep(3000);

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Edit interface' popup is not displaying");
			System.out.println(" 'Edit interface' popup is not displaying");
		}

		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Data has been entered inside Edit Interface page by selecting configure link under Intermediate Equipment");

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);

	}

	public void EnterdataForEditInterfaceforConfigurelinkunderEquipment(String application, String interfacename,
			String circuitID, String bearertype, String bearerspeed, String totalbandwidth, String vlanid,
			String vlantype) throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
		Thread.sleep(1000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);

		// Circuit ID
		configure_circuitId(application, circuitID);

		// Bearer type
		addDropdownValues_commonMethod(application, "Bearer Type", "Equipment_configureBearerType", bearertype, xml);

		// Bearerspeed
		addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed", bearerspeed, xml);

		// Circuit bandwidth
		addDropdownValues_commonMethod(application, "Total Circuit Bandwidth",
				"Equipment_configureTotalcircuitbandwidth", totalbandwidth, xml);

		// VLAN type
		addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype, xml);

//vlan	
		try {
			if (vlanid.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, " No input provided for 'Vlan id'");

			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")),
						vlanid);
				ExtentTestManager.getTest().log(LogStatus.PASS, vlanid + " is the value passed for 'Vlan ID' field");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'Vlan id' dropdown under 'Edit Interface' page is not available");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value for 'Vlan id' field");
		}
		Thread.sleep(3000);

		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Data has been entered inside Edit Interfaec page by selecting configure link under Equipment");

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);

	}

	public void EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment(String application,
			String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
			String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {

		// select the Interface
		selectRowForconfiglinkunderIntermediateEquipment(application, interfacename);

		// Perform edit Interface
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);

		// verify Edit Interface page
		boolean editInterface_popuptitleName = false;
		try {
			editInterface_popuptitleName = getwebelement(
					xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
			if (editInterface_popuptitleName) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Edit interface popup is displaying as expected");
				System.out.println("Edit interface popup is displaying as expected");

				// Interface name
				verifyenteredvaluesForEditPage_noneditableFields(application, "Interface", interfacename);

				// Circuit ID
				configure_circuitId(application, circuitID);

				// Bearer type
				addDropdownValues_commonMethod(application, "Bearer Type", "Equipment_configureBearerType", bearertype, xml);

				// Bearerspeed
				addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed",
						bearerspeed, xml);

				// Total Circuit bandwidth
				addDropdownValues_commonMethod(application, "Total Circuit Bandwidth",
						"Equipment_configureTotalcircuitbandwidth", totalbandwidth, xml);

				// VLAN type
				addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype, xml);

				// vlan
				addtextFields_commonMethod(application, "VLAN Id", "Equipment_configureVLANid", vlanid, xml);
				Thread.sleep(3000);

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Edit interface' popup is not displaying");
			System.out.println(" 'Edit interface' popup is not displaying");
		}

		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Data has been entered inside Edit Interface page by selecting configure link under Intermediate Equipment");

		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);

	}

	public void AddInterfaceToserviceforProviderEquipment(String Application, String interfacenumber)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		String TextKeyword = Gettext(getwebelement(
				xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectTotalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in Interface to select table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(getwebelement(xml
						.getlocator("//locators/" + Application + "/providerEquipment_currentpageInterfaceToselect")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath(
						"(//div[@class='row'][2]//div[div[contains(text(),'" + interfacenumber + "')]])//input"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					Clickon(getwebelement(xml
							.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectnextpage")));

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								Thread.sleep(8000);
								Clickon(getwebelement(xml.getlocator("//locators/" + Application
										+ "/providerEquipment_InterfaceToselectActiondropdown")));

								Thread.sleep(5000);

								Clickon(getwebelement(xml.getlocator("//locators/" + Application
										+ "/providerEquipment_InterfaceToselectAddbutton")));

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"
									+ interfacenumber + "')]])//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

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

	public void verifyInterfaceaddedToServiceForProviderEquipment(String application, String interfacenumber) {

		try {
			boolean result = driver
					.findElement(
							By.xpath("(//div[@class='row'][1]//div[div[contains(text(),'" + interfacenumber + "')]])"))
					.isDisplayed();
			sa.assertTrue(result, "Verified: Interface got added to service");
		} catch (Exception e) {
			System.out.println("No values available inside the table");
		}

	}

	public void ProviderEquipmentInterfaceInService(String Application, String interfacenumber)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		String TextKeyword = Gettext(getwebelement(
				xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceInselectTotalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(getwebelement(xml
						.getlocator("//locators/" + Application + "/providerEquipment_currentpageInterfaceInselect")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath(
						"(//div[@class='row'][1]//div[div[contains(text(),'" + interfacenumber + "')]])//input"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					getwebelement(xml
							.getlocator("//locators/" + Application + "/providerEquipment_InterfaceInselectnextpage"));
				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								Thread.sleep(8000);
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + Application + "/InterfaceInselect_Actiondropdown")));

								Thread.sleep(5000);

								Clickon(getwebelement(xml
										.getlocator("//locators/" + Application + "/InterfaceToselect_removebuton")));

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath(
									"(//div[@class='row'][1]//div[div[contains(text(),'\"+interfacenumber +\"')]])//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

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

	public void verifyInterfaceremovedFromServiceforProviderEquipment(String application, String interfacenumber)
			throws InterruptedException, DocumentException {

		try {
			boolean result = driver
					.findElement(By.xpath(
							"(//div[@class='row'][2]//div[div[contains(text(),'" + interfacenumber + "')]])//input"))
					.isDisplayed();
			sa.assertTrue(result, "Verified: Interface got removed from the service");
			
		} catch (Exception e) {
			System.out.println("No values found inside the table");
		}

	}

	public void SelectShowInterfacelinkAndVerifyEditInterfacePageforProviderEquipment(String application,
			String interfacename) throws InterruptedException, DocumentException, IOException {

		try {
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/ProviderEquipment_showInterfacelink")));
			Thread.sleep(3000);

			selectRowForshowInterfaceunderProviderEquipment(application, interfacename);

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_showInterfaceActiondropdown")));
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
			Thread.sleep(3000);

			boolean XNGcircuitID = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
			sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
			System.out.println("circuit id is fetched");

			System.out.println("Entering bearer type dropdown");
			boolean BearerTypedropdown = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
			sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
			System.out.println("bearer type dropdown is fetchecd");

			boolean Bearerspeed = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
			sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");

			boolean bandwidth = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth"))
							.isDisplayed();
			sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");

			boolean vlan = getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid"))
					.isDisplayed();
			sa.assertTrue(vlan, "VLAN ID field is not displaying");

			boolean vlantype = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
			sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");

			boolean ok = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
			sa.assertTrue(ok, "Ok Button is not displaying");

			boolean CANCEL = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
			sa.assertTrue(CANCEL, "Cancel Button is not displaying");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
			Thread.sleep(3000);

			

		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}

	public void SelectShowInterfacelinkAndVerifyEditInterfacePageforCustomerPremiseEquipment(String application,
			String interfacename) throws InterruptedException, DocumentException, IOException {

		try {
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/customerpremiseEQuipment_showinetrfacelink")));
			Thread.sleep(3000);

			selectRowForshowInterfaceunderCustomerPremiseEquipment(application, interfacename);

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_showInterfaceActiondropdown")));
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
			Thread.sleep(3000);

			boolean XNGcircuitID = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
			sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
			System.out.println("circuit id is fetched");

			System.out.println("Entering bearer type dropdown");
			boolean BearerTypedropdown = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
			sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
			System.out.println("bearer type dropdown is fetchecd");

			boolean Bearerspeed = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
			sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");

			boolean bandwidth = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth"))
							.isDisplayed();
			sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");

			boolean vlan = getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid"))
					.isDisplayed();
			sa.assertTrue(vlan, "VLAN ID field is not displaying");

			boolean vlantype = getwebelement(
					xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
			sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");

			boolean ok = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
			sa.assertTrue(ok, "Ok Button is not displaying");

			boolean CANCEL = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
			sa.assertTrue(CANCEL, "Cancel Button is not displaying");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
			Thread.sleep(3000);

			

		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}

	public void selectconfigurelinkAndverifyForProviderEquipment(String application, String interfacename)
			throws InterruptedException, DocumentException, IOException {

		try {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Providerequipment_configurelink")));
			Thread.sleep(3000);

			boolean GenerateConfigForStaticRoutes = getwebelement(
					xml.getlocator("//locators/" + application + "/ProviderEquipment_Generateconfigforstaticlink"))
							.isDisplayed();
			sa.assertTrue(GenerateConfigForStaticRoutes,
					"generate configuration for static routes link is not displayed");

			boolean SaveConfigurationlink = getwebelement(
					xml.getlocator("//locators/" + application + "/providerEquipment_saveconfigurationlink"))
							.isDisplayed();
			sa.assertTrue(SaveConfigurationlink, "Save configuration link is not displayed");

			boolean ExecuteconfigOndevice = getwebelement(
					xml.getlocator("//locators/" + application + "/providerEquipment_ExecuteconfigurationOndevicelink"))
							.isDisplayed();
			sa.assertTrue(ExecuteconfigOndevice, "Execute configuration on device link is not displayed");

			

		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}

	public void deleteDeviceFromServiceForProviderequipment(String application, String deleteDevice)
			throws InterruptedException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Providerequipment_deletefromservice")));
		Thread.sleep(3000);

		boolean deletemessage = getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment"))
				.isDisplayed();
		while (deletemessage) {
			Log.info("Are you sure want to delete ? - message is getting displayed on clicking DeletefromService link");
			Log.info("Delete popup message is getting displayed");
			break;
		}

		if (deleteDevice.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
			Thread.sleep(3000);

			Log.info("Device got deleted from service as expected");

		} else {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/xbuttonfordeletefromservicepopup_Equipment")));
			Thread.sleep(3000);

			Log.info("Device is not deleted from service as expected");

		}

	}

	public void verifyAddnewlinkunderPE2CPEtable(String application)
			throws InterruptedException, DocumentException, IOException {

		String HeaderNameExpected = "Add New Link";

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actionbutton_PE2CPE")));
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addnewlink_Pe2CPE")));
		Thread.sleep(3000);

		String headername = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/Headername_PE2CPElink")));
		sa.assertEquals(headername, HeaderNameExpected, "Header name is not displaying as expected");

		boolean CircuitId = getwebelement(
				xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_LinkorCircuitId")).isDisplayed();
		sa.assertTrue(CircuitId, "circuit id is not displaying");

		boolean sourceDevice = getwebelement(
				xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceDevice")).isDisplayed();
		sa.assertTrue(sourceDevice, "Source Device dropdown is not displaying");

		boolean Sourceinterface = getwebelement(
				xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceInterface")).isDisplayed();
		sa.assertTrue(Sourceinterface, "Source Interface dropdown is not displaying");

		boolean targetdevice = getwebelement(
				xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetDevice")).isDisplayed();
		sa.assertTrue(targetdevice, "Target device dropdown is not displaying");

		boolean targetInterface = getwebelement(
				xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetInterface")).isDisplayed();
		sa.assertTrue(targetInterface, "Target Interface dropdown is not displaying");

		boolean next = getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_Nextbutton"))
				.isDisplayed();
		sa.assertTrue(next, "Next button is not displaying");

		boolean cancel = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
		sa.assertTrue(cancel, "CAncel button is not displaying");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);

	}

	public void enterdataforAddNewlinkunderPEtoCPEtable(String application, String CircuitId, String sourcedevice,
			String sourceinterfacce, String targetDevice, String targetInterface, String interfacename)
			throws InterruptedException, DocumentException, IOException {

		selecttherowforPEtoCPElinktable(application, interfacename);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addnewlink_Pe2CPE")));

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_LinkorCircuitId")),
				CircuitId);

		Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceDevice")),
				sourcedevice);

		Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceInterface")),
				sourceinterfacce);

		Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetDevice")),
				targetDevice);

		Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetInterface")),
				targetInterface);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_Nextbutton")));
		Thread.sleep(3000);

	}

	public void enterdataforeditNewlinkunderPEtoCPEtable(String application) {

	}

	public void selecttherowforPEtoCPElinktable(String Application, String interfacename)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/Pe2CPElinktable_totalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/Pe2CPElinktable_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver
						.findElements(By.xpath("(//div[@class='ag-div-margin row']//div[div[contains(text(),'"
								+ interfacename + "')]])//input"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					getwebelement(xml.getlocator("//locators/" + Application + "/Pe2CPElinktable_nextpage"));
				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								Thread.sleep(8000);
								Clickon(getwebelement(
										xml.getlocator("//locators/" + Application + "/Actionbutton_PE2CPE")));

								Thread.sleep(5000);

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(
									By.xpath("(//div[@class='ag-div-margin row']//div[div[contains(text(),'"
											+ interfacename + "')]])//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

						}

					}

					break ab;

				}

			}

		} else {

			System.out.println("No values available in table");
			Log.info("No values available inside the PEtoCPElink table");
		}

	}

	public void selectInterfacelinkforCustomerpremiseequipment(String application)
			throws InterruptedException, DocumentException {

		Thread.sleep(5000);

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/cusomerpremiseequipment_SelectInertafeceslink")));
		System.out.println("SelectInterface link for Customer premise Equipment is selected");
		Log.info("Select an inertface to add with the service under customer premise equipment");

	}

	public void AddInterfacetoserviceforcustomerpremiseEquipment(String Application, String interfacenumber)
			throws IOException, InterruptedException, DocumentException {

		int TotalPages;

		String TextKeyword = Gettext(getwebelement(
				xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectTotalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in Interface to select table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(getwebelement(xml
						.getlocator("//locators/" + Application + "/providerEquipment_currentpageInterfaceToselect")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath(
						"(//div[@class='row'][2]//div[div[contains(text(),'" + interfacenumber + "')]])//input"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					Clickon(getwebelement(xml
							.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectnextpage")));

				}

				else {

					for (int i = 0; i < numofrows; i++) {

						try {

							resultflag = results.get(i).isDisplayed();
							System.out.println("status of result: " + resultflag);
							if (resultflag) {
								System.out.println(results.get(i).getText());
								results.get(i).click();
								Thread.sleep(8000);
								Clickon(getwebelement(xml.getlocator("//locators/" + Application
										+ "/providerEquipment_InterfaceToselectActiondropdown")));

								Thread.sleep(5000);

								Clickon(getwebelement(xml.getlocator("//locators/" + Application
										+ "/providerEquipment_InterfaceToselectAddbutton")));

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"
									+ interfacenumber + "')]])//input"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);

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

	public void pageRefresh() throws InterruptedException {

		Pagerefresh();
	}

	/**
	 * 
	 * 04-Dec
	 * 
	 */

	public void Edit_DirectFibre10G(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String performanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String intermediateTechnology, String CircuitReference, 
			String notificationManagement, String managementConnection, String ENNIcheckBox)
			throws InterruptedException, IOException, DocumentException {

		// Service Identification
		editService_serviceIdentification(application, ServiceIdentificationNumber);

		// Endpoint CPE
		editService_singleEndPointCPE(application, EndpointCPE);

		// Email
		editService_Email(application, Email);

		// Phone contact `
		editService_phoneContact(application, PhoneContact);

		// Remark
		editService_remark(application, remark);

		// Performance Reporting
		boolean perfrmReportAvailability = false;
		try {
			perfrmReportAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();

			if (perfrmReportAvailability) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Performance reporting' checkbox is displaying in 'Edit Service' page as expected");

				if (!performanceReporting.equalsIgnoreCase("null")) {

					boolean perfReport = getwebelement(
							xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
					Thread.sleep(2000);

					if (performanceReporting.equalsIgnoreCase("yes")) {

						if (perfReport) {

							ExtentTestManager.getTest().log(LogStatus.PASS,
									"Performance Reporting checkbox is not edited and it is already Selected while creating");

						} else {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
							Log.info("Performance Reporting check box is selected");
							ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting checkbox is selected");
						}
					} else if (performanceReporting.equalsIgnoreCase("no")) {

						if (perfReport) {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
							Log.info("Performance Reporting check box is unselected");
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"Performance Reporting is edited and gets unselected");

						} else {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"Performance Reporting is not edited and it remains unselected");
						}

					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Performance Reporting chekbox");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Performance reporting' checkbox is not available in 'Edit Service' page");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" ' Performance Reporting' checkbox is not displaying under 'Edit service' page");
			System.out.println(" ' Performance Reporting' checkbox is not displaying under 'Edit service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Performance Reporting' checkbox");
			System.out.println(" Not able to click on 'erformance Reporting' checkbox");
		}

		// Proactive monitoring
		editService_proactiveMonitoring(application, ProactiveMonitoring, notificationManagement);

		// Delivery channel
		editService_deliveryChannel(application, deliveryChannel);

		// Management Connection
		editService_managementConnection(application, managementConnection);

		// ENNI checkbox
		editcheckbox_commonMethod(application, ENNIcheckBox, "ENNI_checkbox", "ENNI");

		scrolltoend();
		Thread.sleep(1000);

		// click on "Ok button to update
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);

	}

	public void Edit_DirectFibre1G(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String performanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String intermediateTechnology, String CircuitReference,
			String notificationManagement, String perCoSperformanceReport, String actelisBased, String standardCIR,
			String standardEIR, String premiumCIR, String premiumEIR, String ManagementConnection, String ENNIcheckBox)
			throws InterruptedException, IOException, DocumentException {

		scrollToTop();
		Thread.sleep(2000);
		
		// Service Identification
		editService_serviceIdentification(application, ServiceIdentificationNumber);

		// Endpoint CPE
		editService_singleEndPointCPE(application, EndpointCPE);

		// Email
		editService_Email(application, Email);

		// Phone contact `
		editService_phoneContact(application, PhoneContact);

		// Remark
		editService_remark(application, remark);

		WebElement serviceIdentificationlabelName=getwebelement(xml.getlocator("//locators/" + application + "/serviceIdentificationLabelName"));
		scrolltoview(serviceIdentificationlabelName);
		Thread.sleep(1000);

		// Proactive monitoring
		editService_proactiveMonitoring(application, ProactiveMonitoring, notificationManagement);

		// Delivery channel
		editService_deliveryChannel(application, deliveryChannel);

		// Management Connection
		editService_managementConnection(application, ManagementConnection);

		// ENNI checkbox
		editcheckbox_commonMethod(application, ENNIcheckBox, "ENNI_checkbox", "ENNI");

		scrolltoend();
		Thread.sleep(1000);
		
		//Per CoS Performance Reporting
		if(performanceReporting.equalsIgnoreCase("yes")) {
			boolean perCoSdisplay=false;
			try {
			perCoSdisplay=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isDisplayed();
			Thread.sleep(3000);
			
			if(perCoSdisplay) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Per CoS Performance Reporting' checkbox is displaying in 'Edit Service' page");
			if(!perCoSperformanceReport.equalsIgnoreCase("null")) {
				
				boolean perCoSreport=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isSelected();
				Thread.sleep(2000);
				
				if (perCoSperformanceReport.equalsIgnoreCase("yes")) {

					if(perCoSreport) {
						
						ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting checkbox is not edited and it is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));
						Log.info("Per CoS Performance Reporting check box is selected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"Per CoS Performance Reporting is selected");
					}
				}

				else if (perCoSperformanceReport.equalsIgnoreCase("no")) {
					
					if(perCoSreport) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));
						Log.info("Per CoS Performance Reporting check box is unselected");
						ExtentTestManager.getTest().log(LogStatus.PASS,"PPer CoS Performance Reporting is edited and gets unselected");
						
					}else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Per CoS Performance Reporting is not edited and it remains unselected");
					}
					
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS," 'Per CoS Performance Reporting' chekbox is not edited");
			}
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Per CoS Performance Reporting' checkbox is not displaying under 'Edit service' page");
		}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Per CoS Performance Reporting' checkbox is not displaying under 'Edit service' page");
				System.out.println(" 'Per CoS Performance Reporting' checkbox is not displaying under 'Edit service' page");
			}catch(Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Per CoS Performance Reporting' checkbox");
				System.out.println(" Not able to click on 'Per CoS Performance Reporting' checkbox");
			}
		}
		
		//Actelis Based
				if(perCoSperformanceReport.equalsIgnoreCase("yes")) {
					boolean actelisbased=false;
					try {
						actelisbased=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isDisplayed();
						Thread.sleep(3000);
						if(actelisbased) {
							ExtentTestManager.getTest().log(LogStatus.PASS, " Actelis Based checkbox is dipslaying in 'Edit Serivce' page");
						if(!actelisBased.equalsIgnoreCase("null")) {
							
							
							boolean actelsBased=getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")).isSelected();
							Thread.sleep(2000);
							
							if (actelisBased.equalsIgnoreCase("yes")) {

								if(actelsBased) {
									
									ExtentTestManager.getTest().log(LogStatus.PASS, "Actelis Based checkbox is not edited and it is already Selected while creating");
									
								}else {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")));
									Log.info("Actelis Based check box is selected");
									ExtentTestManager.getTest().log(LogStatus.PASS,"Actelis Based checkbox is selected");
								}
							}

							else if (actelisBased.equalsIgnoreCase("no")) {
								
								if(actelsBased) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ActelisBasedcheckbox")));
									Log.info("Per CoS Performance Reporting check box is unselected");
									ExtentTestManager.getTest().log(LogStatus.PASS,"Actelis Based is edited and gets unselected");
									
								}else {
									ExtentTestManager.getTest().log(LogStatus.PASS, "Actelis Based checkbox is not edited and it remains unselected");
								}
								
							}
						}else {
							ExtentTestManager.getTest().log(LogStatus.PASS,"No changes made for Actelis Based chekbox");
						}
					}
					}catch(NoSuchElementException e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " Actelis Based checkbox is not dipslaying in 'Edit Serivce' page");
					}catch(Exception err) {
						err.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Acteis' checkbox");
						System.out.println(" Not able to click on 'Acteis' checkbox");
					}
				}		
						
						
			if(actelisBased.equalsIgnoreCase("yes")) {

				//Standard CIR
					editService_standardCIR(application, standardCIR);			
					
				//Standard EIR
					editService_standardEIR(application, standardEIR);			
					
				//Premium CIR
					editService_premiumCIR(application, premiumCIR);			
			
				//Premium EIR
					editService_premiumEIR(application, premiumEIR);
		}


		// click on "Ok button to update
		scrolltoend();
		Thread.sleep(1000);
		click_commonMethod(application, "OK", "okbutton", xml);
		Thread.sleep(3000);

	}

	public void EditLanlinkMetro(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceMonitoring, String ProactiveMonitoring, String deliveryChannel, String vpnTopology,
			String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement)
			throws InterruptedException, IOException, DocumentException {

		// Service Identification
		if (!ServiceIdentificationNumber.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).clear();
			Thread.sleep(2000);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Edited value for 'Service Identification' field is " + ServiceIdentificationNumber);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Service Identification field");
		}

		// Endpoint CPE
		if (!EndpointCPE.equalsIgnoreCase("null")) {

			boolean endpoint = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isSelected();
			Thread.sleep(2000);

			if (EndpointCPE.equalsIgnoreCase("yes")) {

				if (endpoint) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CPE is already Selected while creating");

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
					Log.info("End point CPE check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CE is selected as Expected");
				}

			}

			else if (EndpointCPE.equalsIgnoreCase("no")) {

				if (endpoint) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
					Log.info("End point CPE check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CE is unselected as Expected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Endpoint CPE was not selected during service creation and it remains unselected as expected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for EndPoint CPE chekbox as expected");
		}

		// Email
		if (!Email.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/Email")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Email' field is " + Email);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Email field");
		}

		// Phone contact
		if (!PhoneContact.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Phone contact' field is " + PhoneContact);
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Phone contact field");
		}

		// Remark
		if (!remark.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/Remark")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Remark' field is " + remark);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Remark field");
		}

		// Performance Monitoring
		if (!PerformanceMonitoring.equalsIgnoreCase("null")) {

			boolean perfmmonitr = getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring"))
					.isSelected();
			Thread.sleep(2000);

			if (PerformanceMonitoring.equalsIgnoreCase("yes")) {

				if (perfmmonitr) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Performance Monitor checkbox is not edited and it is already Selected while creating");

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
					Log.info("Performance monitor check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance monitor is selected");
				}

			}

			else if (PerformanceMonitoring.equalsIgnoreCase("no")) {

				if (perfmmonitr) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
					Log.info("Performance Monitor check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Monitor is edited and gets unselected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Performance Monitor is not edited and it remains unselected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Performance Monitor chekbox");
		}

		// Proactive monitoring
		if (!ProactiveMonitoring.equalsIgnoreCase("null")) {

			boolean proactivemonitor = getwebelement(
					xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
			Thread.sleep(2000);

			if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

				if (proactivemonitor) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Proactive monitoring is not edited and it is already Selected while creating");

					if (notificationManagement.equalsIgnoreCase("null")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to notification management");

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
						Thread.sleep(4000);

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						Thread.sleep(3000);
						Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Edited value for 'Notification Management' field is " + notificationManagement);
					}

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("proactive monitoring check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring is edited and gets selected");

					if (notificationManagement.equalsIgnoreCase("null")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to notification management");

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
						Thread.sleep(2000);

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Edited value for 'Notification Management' field is " + notificationManagement);
					}
				}

			}

			else if (ProactiveMonitoring.equalsIgnoreCase("no")) {

				if (proactivemonitor) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("Proactive monitoring check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring is edited and gets unselected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"proactive monitoring was not selected during service creation and it remains unselected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Proactive monitoring' chekbox");
		}

		// Delivery channel
		if (!deliveryChannel.equalsIgnoreCase("null")) {

//				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_xbutton")));
//				Thread.sleep(2000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));

			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Edited value for 'Delivery Channel' dropdown is " + deliveryChannel);
			System.out.println("Delivery channel dropdown value is edited as expected");

		} else {

			ExtentTestManager.getTest().log(LogStatus.PASS, "Delivery channel dropdown value is not edited");

		}

		// VPN topology
		if (vpnTopology.equals("Point-to-Point")) {

			if (intermediateTechnology.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has made for 'Intermediate Technology' field");

			} else {

				getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).clear();
				Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
						intermediateTechnology);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Edited value for 'Intermediate Technology' field is " + intermediateTechnology);

			}

			if (CircuitReference.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has made for 'Circuit reference' field ");

			} else {

				getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
				Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")),
						CircuitReference);

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Edited value for 'Circuit reference' field is " + CircuitReference);
			}

			if (CircuitType.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has made for Circuit Type field");

			} else {

				Clickon(getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input"));

				ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Circuit Type' is " + CircuitType);

			}

		}

		else if (vpnTopology.equals("Hub&Spoke") || vpnTopology.equals("E-PN (Any-to-Any)")) {

			if (CircuitReference.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has made for Circuit reference field");

			} else {

				getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
				Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")),
						CircuitReference);
				Thread.sleep(3000);

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Edited value for 'Circuit reference' field is " + CircuitReference);
			}

		}

		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Values has been Edited under Lanlink Metro subtype under Lanlink service");

		click_commonMethod(application, "OK", "okbutton", xml);
	}

	public void EditLanlink_outbandmanagement(String application, String ServiceIdentificationNumber,
			String SelectSubService, String Interfacespeed, String EndpointCPE, String Email, String PhoneContact,
			String remark, String Performancereporting, String ProactiveMonitoring, String ENNIcheckBox,
			String deliveryChannel, String Manageconnectiondropdown, String notificationManagement)
			throws InterruptedException, IOException, DocumentException {

		// Service Identification
		if (!ServiceIdentificationNumber.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).clear();
			Thread.sleep(2000);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Edited value for 'Service Identification' field is " + ServiceIdentificationNumber);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Service Identification field");
		}

		// Endpoint CPE
		if (!EndpointCPE.equalsIgnoreCase("null")) {

			boolean endpoint = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isSelected();
			Thread.sleep(2000);

			if (EndpointCPE.equalsIgnoreCase("yes")) {

				if (endpoint) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CPE is already Selected while creating");

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
					Log.info("End point CPE check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CE is selected as Expected");
				}

			}

			else if (EndpointCPE.equalsIgnoreCase("no")) {

				if (endpoint) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
					Log.info("End point CPE check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CE is unselected as Expected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Endpoint CPE was not selected during service creation and it remains unselected as expected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for EndPoint CPE chekbox as expected");
		}

		// Email
		if (!Email.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/Email")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Email' field is " + Email);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Email field");
		}

		// Phone contact
		if (!PhoneContact.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Phone contact' field is " + PhoneContact);
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Phone contact field");
		}

		// Remark
		if (!remark.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/Remark")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Remark' field is " + remark);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Remark field");
		}

		// Performance Reporting
		if (!Performancereporting.equalsIgnoreCase("null")) {

			boolean perfmmonitr = getwebelement(
					xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
			Thread.sleep(2000);

			if (Performancereporting.equalsIgnoreCase("yes")) {

				if (perfmmonitr) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Performance Reporting checkbox is not edited and it is already Selected while creating");

				} else {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("Performance Reporting check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting is selected");
				}

			}

			else if (Performancereporting.equalsIgnoreCase("no")) {

				if (perfmmonitr) {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("Performance Reporting check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting is edited and gets unselected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Performance Reporting is not edited and it remains unselected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Performance Reporting chekbox");
		}

		// Proactive monitoring
		if (!ProactiveMonitoring.equalsIgnoreCase("null")) {

			boolean proactivemonitor = getwebelement(
					xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
			Thread.sleep(2000);

			if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

				if (proactivemonitor) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Proactive monitoring is not edited and it is already Selected while creating");

					if (notificationManagement.equalsIgnoreCase("null")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to notification management");

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
						Thread.sleep(4000);

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						Thread.sleep(3000);
						Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Edited value for 'Notification Management' field is " + notificationManagement);
					}

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("proactive monitoring check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring is edited and gets selected");

					if (notificationManagement.equalsIgnoreCase("null")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to notification management");

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
						Thread.sleep(2000);

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Edited value for 'Notification Management' field is " + notificationManagement);
					}
				}

			}

			else if (ProactiveMonitoring.equalsIgnoreCase("no")) {

				if (proactivemonitor) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("Proactive monitoring check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring is edited and gets unselected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"proactive monitoring was not selected during service creation and it remains unselected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Proactive monitoring' chekbox");
		}

		// ENNI chekcbox
		if (!ENNIcheckBox.equalsIgnoreCase("null")) {

			boolean enni = getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")).isSelected();
			Thread.sleep(2000);

			if (ENNIcheckBox.equalsIgnoreCase("yes")) {

				if (enni) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"ENNI checkbox is not edited and it is already Selected while creating");

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")));
					Log.info("ENNI check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "ENNI is selected");
				}

			}

			else if (ENNIcheckBox.equalsIgnoreCase("no")) {

				if (enni) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")));
					Log.info("ENNI check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "ENNI is edited and gets unselected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "ENNI is not edited and it remains unselected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for ENNI chekbox");
		}

		// Delivery channel
		if (!deliveryChannel.equalsIgnoreCase("null")) {

//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_xbutton")));
//					Thread.sleep(2000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));

			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Edited value for 'Delivery Channel' dropdown is " + deliveryChannel);
			System.out.println("Delivery channel dropdown value is edited as expected");

		} else {

			ExtentTestManager.getTest().log(LogStatus.PASS, "Delivery channel dropdown value is not edited");

		}

		if (!Manageconnectiondropdown.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/managementconnection")).clear();
			Thread.sleep(2000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementconnection")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + Manageconnectiondropdown + "')]"));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Edited value for 'Manage Connection' dropdown is " + Manageconnectiondropdown);
			System.out.println("Manage Connection dropdown value is edited as expected");

		} else {

			ExtentTestManager.getTest().log(LogStatus.PASS, "Manage Connection dropdown value is not edited");

		}

		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Values has been Edited under lanlink outbandmanagement service subtype");

		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void EditLanlink_OLO(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceMonitoring, String ProactiveMonitoring, String ENNIcheckBox, String deliveryChannel,
			String ManagementOrder, String vpnTopology, String intermediateTechnology, String CircuitReference,
			String CircuitType, String notificationManagement)
			throws InterruptedException, IOException, DocumentException {

		// Service Identification
		if (!ServiceIdentificationNumber.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).clear();
			Thread.sleep(2000);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Edited value for 'Service Identification' field is " + ServiceIdentificationNumber);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Service Identification field");
		}

		// Endpoint CPE
		if (!EndpointCPE.equalsIgnoreCase("null")) {

			boolean endpoint = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isSelected();
			Thread.sleep(2000);

			if (EndpointCPE.equalsIgnoreCase("yes")) {

				if (endpoint) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CPE is already Selected while creating");

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
					Log.info("End point CPE check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CE is selected as Expected");
				}

			}

			else if (EndpointCPE.equalsIgnoreCase("no")) {

				if (endpoint) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
					Log.info("End point CPE check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CE is unselected as Expected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Endpoint CPE was not selected during service creation and it remains unselected as expected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for EndPoint CPE chekbox as expected");
		}

		// Email
		if (!Email.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/Email")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Email' field is " + Email);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Email field");
		}

		// Phone contact
		if (!PhoneContact.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Phone contact' field is " + PhoneContact);
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Phone contact field");
		}

		// Reamrk
		if (!remark.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/Remark")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Remark' field is " + remark);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Remark field");
		}

		// Performance Monitoring
		if (!PerformanceMonitoring.equalsIgnoreCase("null")) {

			boolean perfmmonitr = getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring"))
					.isSelected();
			Thread.sleep(2000);

			if (PerformanceMonitoring.equalsIgnoreCase("yes")) {

				if (perfmmonitr) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Performance Monitor checkbox is not edited and it is already Selected while creating");

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
					Log.info("Performance monitor check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance monitor is selected");
				}

			}

			else if (PerformanceMonitoring.equalsIgnoreCase("no")) {

				if (perfmmonitr) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
					Log.info("Performance Monitor check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Monitor is edited and gets unselected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Performance Monitor is not edited and it remains unselected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for Performance Monitor chekbox");
		}

		// Proactive monitoring
		if (!ProactiveMonitoring.equalsIgnoreCase("null")) {

			boolean proactivemonitor = getwebelement(
					xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
			Thread.sleep(2000);

			if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

				if (proactivemonitor) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Proactive monitoring is not edited and it is already Selected while creating");

					if (notificationManagement.equalsIgnoreCase("null")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to notification management");

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
						Thread.sleep(4000);

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						Thread.sleep(3000);
						Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Edited value for 'Notification Management' field is " + notificationManagement);
					}

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("proactive monitoring check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring is edited and gets selected");

					if (notificationManagement.equalsIgnoreCase("null")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to notification management");

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
						Thread.sleep(2000);

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Edited value for 'Notification Management' field is " + notificationManagement);
					}
				}

			}

			else if (ProactiveMonitoring.equalsIgnoreCase("no")) {

				if (proactivemonitor) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("Proactive monitoring check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring is edited and gets unselected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"proactive monitoring was not selected during service creation and it remains unselected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Proactive monitoring' chekbox");
		}

		// Delivery channel
		if (!deliveryChannel.equalsIgnoreCase("null")) {

//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_xbutton")));
//					Thread.sleep(2000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));

			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Edited value for 'Delivery Channel' dropdown is " + deliveryChannel);
			System.out.println("Delivery channel dropdown value is edited as expected");

		} else {

			ExtentTestManager.getTest().log(LogStatus.PASS, "Delivery channel dropdown value is not edited");
		}

		if (!ENNIcheckBox.equalsIgnoreCase("null")) {

			boolean eni = getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")).isSelected();
			Thread.sleep(2000);

			if (ENNIcheckBox.equalsIgnoreCase("yes")) {

				if (eni) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "ENNI checkbox is already Selected while creating");

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")));
					Log.info("ENNI check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "ENNI checkbox is selected as Expected");
				}

			}

			else if (ENNIcheckBox.equalsIgnoreCase("no")) {

				if (eni) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")));
					Log.info("ENNI check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "ENNI checkbox is unselected as Expected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"ENNI checkbox was not selected during service creation and it remains unselected as expected");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for ENNI chekbox as expected");
		}

//management order	
		if (!ManagementOrder.equalsIgnoreCase("null")) {

			getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")).clear();
			Thread.sleep(2000);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")), ManagementOrder);

			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Edited value for 'Management Order' field is " + ManagementOrder);

		} else {

			ExtentTestManager.getTest().log(LogStatus.PASS,
					"No changes has been made to Management order field as expected ");
		}

//VPN topology	
		if (vpnTopology.equals("Point-to-Point")) {

			if (intermediateTechnology.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has made for 'Intermediate Technology' field");

			} else {

				getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).clear();
				Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
						intermediateTechnology);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Edited value for 'Intermediate Technology' field is " + intermediateTechnology);

			}

			if (CircuitReference.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has made for 'Circuit reference' field ");

			} else {

				getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
				Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")),
						CircuitReference);

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Edited value for 'Circuit reference' field is " + CircuitReference);
			}

			if (CircuitType.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has made for Circuit Type field");

			} else {

				Clickon(getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input"));

				ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Circuit Type' is " + CircuitType);

			}

		}

		else if (vpnTopology.equals("Hub&Spoke") || vpnTopology.equals("E-PN (Any-to-Any)")) {

			if (CircuitReference.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "No changes has made for Circuit reference field");

			} else {

				getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
				Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")),
						CircuitReference);
				Thread.sleep(3000);

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Edited value for 'Circuit reference' field is " + CircuitReference);
			}

		}

		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Values has been Edited for 'OLO - (GCR/EU)' subtype under LALINK Service");

		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void syncservices(String application) throws InterruptedException, DocumentException {
		   
		   WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
			ScrolltoElement(orderPanel);
			Thread.sleep(3000);
		   
		   click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
		   Thread.sleep(2000);
		   click_commonMethod(application, "Synchronize link", "Editservice_sysnchronizelink", xml);
		   Thread.sleep(3000);
		   
		   scrollToTop();
		   Thread.sleep(3000);
		   
		   boolean syncSuccessMessage=getwebelement(xml.getlocator("//locators/" + application + "/alertForSynchronize")).isDisplayed();
		   if(syncSuccessMessage) {
			   
			   String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSG_synchronize")).getText();
			   ExtentTestManager.getTest().log(LogStatus.PASS, " success message for synchronize displays as: "+actualmsg);
			   System.out.println( " success message for synchronize displays as: "+actualmsg);
			   
		   }else {
			   ExtentTestManager.getTest().log(LogStatus.FAIL, " Success message did not display after clicking on 'Synchronize' button");
			   System.out.println(" Success message did not display after clicking on 'Synchronize' button");
		   }
	   }

	public void shownewInfovista(String application) throws InterruptedException, DocumentException {
	  
   		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
   		
	   click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
	   Thread.sleep(2000);
	   click_commonMethod(application, "Show infovista link", "Editservice_infovistareport", xml);
	   Thread.sleep(6000);
	   
	   String expectedPageName= "SSO login Page";
	   
	   //Switch to new tab
	   List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
	   driver.switchTo().window(browserTabs .get(1));
	   Thread.sleep(10000);

	  try { 
	   // Get Tab name
	   String pageTitle=driver.switchTo().window(browserTabs .get(1)).getTitle();
	   System.out.println("page title displays as: "+pageTitle);
	  
	   
	   Thread.sleep(3000);
	   driver.switchTo().window(browserTabs.get(0)); 
	   
	   sa.assertEquals(pageTitle, expectedPageName, " on clicking 'Show Infovista link', it got naviagted to "+pageTitle);
	   
	   
	   sa.assertAll();
	   ExtentTestManager.getTest().log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
	   Thread.sleep(3000);
	   
	  }catch(AssertionError e) {
		  
		  e.printStackTrace();
		  
		  Thread.sleep(3000);
		  driver.switchTo().window(browserTabs.get(0));
		  
		  ExtentTestManager.getTest().log(LogStatus.FAIL, expectedPageName + " page is not displaying");
		  
	  }
   		
   }

	public void manageService(String application) throws InterruptedException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_actiondropdown")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_managelink")));
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Backbutton")));
		Thread.sleep(3000);

	}

	public void manageSubnets(String application) throws InterruptedException, DocumentException {
		
		 WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
			ScrolltoElement(orderPanel);
			Thread.sleep(3000);
		   
		   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_actiondropdown")));
		   Thread.sleep(3000);
		   click_commonMethod(application, "Manage Subnet", "Editservice_managesubnets", xml);
		   Thread.sleep(2000);
		   
		   boolean manageSubnetPage=false;
		  try { 
		   manageSubnetPage=getwebelement(xml.getlocator("//locators/" + application + "/manageSubnetPage_header")).isDisplayed();
		   if(manageSubnetPage) {
			   ExtentTestManager.getTest().log(LogStatus.PASS, "'Manage Subnet' page is displaying");
			   System.out.println("'Manage Subnet' page is displaying");
			   
			   String errMsg=getwebelement(xml.getlocator("//locators/" + application + "/manageSubnet_errMsg")).getText();
			   if(errMsg.isEmpty()) {
				   
				   ExtentTestManager.getTest().log(LogStatus.FAIL, "No messages displays under 'manage Subnet' page");
				   System.out.println("No messages displays under 'manage Subnet' page");
			   }else {
				   ExtentTestManager.getTest().log(LogStatus.PASS, "Message in 'Manage Subnet' page displays as "+errMsg);
				   System.out.println(" Message in 'Manage Subnet' page displays as "+errMsg);
			   }
			   
		   }else {
			   ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage Subnet' page is not displaying");
			   System.out.println("'Manage Subnet' page is not displaying");
		   }
		  }catch(Exception e) {
			  e.printStackTrace();
			  ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage Subnet' page is not displaying");
			  System.out.println("'Manage Subnet' page is not displaying");
		  }
		   
		  click_commonMethod(application, "Cancel", "cancelButton", xml); 
		  Thread.sleep(1000);
	   }

	public void verifyAddcpedevicepageforIntermediatEquipmetn(String application)
			throws InterruptedException, DocumentException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying fields for CPE device under Intermediate Equipment");

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_adddevicelink")));
		Thread.sleep(3000);

		Thread.sleep(3000);
		try {

			String[] Vender = { "AC", "DC" };

			String[] powerAlarm = { "AC", "DC" };

			String[] Mediaselection = { "Type A", "Type B", "Type C" };

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			Thread.sleep(3000);

			// Snmpro Error Message
			boolean snmproErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_snmproerrmsg")).isDisplayed();
			sa.assertTrue(snmproErr, "Snmpro warning message is not displayed ");
			String snmproErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_snmproerrmsg")).getText();
			System.out.println("Snmpro  message displayed as : " + snmproErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for Snmpro field displayed as : " + snmproErrMsg);
			Log.info("Snmpro warning message displayed as : " + snmproErrMsg);

			// Management Address Error Message
			boolean mangadrsErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg"))
							.isDisplayed();
			sa.assertTrue(mangadrsErr, "Management Addres warning message is not displayed ");
			String mngadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg")).getText();
			System.out.println("Management Addres  message displayed as : " + mngadresErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for Management Addres field displayed as : " + mngadresErrMsg);
			Log.info("Management Addres warning message displayed as : " + mngadresErrMsg);

			// Power Alarm Error Message
			boolean pwralrmErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).isDisplayed();
			sa.assertTrue(pwralrmErr, "Power Alarm warning message is not displayed ");
			String pwralarmErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).getText();
			System.out.println("Power Alarm  message displayed as : " + pwralarmErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for Power Alarm field displayed as : " + pwralarmErrMsg);
			Log.info("Power Alarm warning message displayed as : " + pwralarmErrMsg);

			// Media Selection Error Message
			boolean mediaErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).isDisplayed();
			sa.assertTrue(mediaErr, "Media Selection warning message is not displayed ");
			String mediaselectionErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).getText();
			System.out.println("Media Selection  message displayed as : " + mediaselectionErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for Media Selection field displayed as : " + mediaselectionErrMsg);
			Log.info("Media Selection warning message displayed as : " + mediaselectionErrMsg);

			// MAC Address Error Message
			boolean macErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).isDisplayed();
			sa.assertTrue(macErr, "MAC Address warning message is not displayed ");
			String macadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).getText();
			System.out.println("MAC Address  message displayed as : " + macadresErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for MAC Address field displayed as : " + macadresErrMsg);
			Log.info("MAC Address warning message displayed as : " + macadresErrMsg);

			// Country Error Message
			boolean countryErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).isDisplayed();
			sa.assertTrue(countryErr, "MAC Address warning message is not displayed ");
			String countryErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).getText();
			System.out.println("Country  message displayed as : " + countryErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for Country field displayed as : " + countryErrMsg);
			Log.info("Country warning message displayed as : " + countryErrMsg);

			boolean name = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name"))
					.isDisplayed();
			sa.assertTrue(name, "name field is not available under create device for Equipment");

			boolean vender = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender"))
					.isDisplayed();
			sa.assertTrue(vender, "Vender/Model dropdown is not available");

			try {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "AddCPE device not available");
			}

			try {
				List<WebElement> listofvender = driver
						.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

				if (listofvender.size() > 0) {

					for (WebElement vendertypes : listofvender) {

						boolean match = false;
						for (int i = 0; i < Vender.length; i++) {
							if (vendertypes.getText().equals(Vender[i])) {
								match = true;
								Log.info("list of vendor under add devices are : " + vendertypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS,
										"The list of vender/Model under Add device are: " + vendertypes.getText());
								System.out.println("list of vendor under add devices are : " + vendertypes.getText());

							}
						}
						sa.assertTrue(match);
					}

				} else {
					System.out.println("dropdown value inside Vender/Model is empty");
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values available inside Vender/Model dropdown for adding devices");
				}

			} catch (Exception e) {

				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Failure at vendor dropdown");

			}

			boolean snmpro = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro"))
					.isDisplayed();
			sa.assertTrue(snmpro, "Snmpro field under add device is not available");

			boolean managementaddres = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).isDisplayed();
			sa.assertTrue(managementaddres, "Management Address field under add device is not available");

			boolean mepid = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid"))
					.isDisplayed();
			sa.assertTrue(mepid, "Mepid field under add device is not available");

			boolean powralrm = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm"))
					.isDisplayed();
			sa.assertTrue(powralrm, "The poweralarm dropdown under add device is not available");

			try {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Poweralarm dropdown field is not available");
			}

			try {
				List<WebElement> listofalarm = driver
						.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

				if (listofalarm.size() > 0) {
					for (WebElement alarmtypes : listofalarm) {

						boolean match = false;
						for (int i = 0; i < powerAlarm.length; i++) {
							if (alarmtypes.getText().equals(powerAlarm[i])) {
								match = true;
								Log.info("list of power alarm under add devices are : " + alarmtypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS,
										"The list of powerAlarm under Add device are: " + alarmtypes.getText());

							}
						}
						sa.assertTrue(match);
					}

				} else {
					System.out.println("dropdown value inside Vender/Model is empty");
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values available inside power alarm dropdown for adding devices");
				}
			} catch (Exception e) {

				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "value mismatch for poweralarm dropdown");

			}

			boolean media = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection"))
					.isDisplayed();
			sa.assertTrue(media, "Media selection dropdwon under add devices is not available");

			try {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Mediaselection dropdown is not available");
			}
			try {
				List<WebElement> listofmedia = driver
						.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

				if (listofmedia.size() > 0) {
					for (WebElement mediatypes : listofmedia) {

						boolean match = false;
						for (int i = 0; i < Mediaselection.length; i++) {
							if (mediatypes.getText().equals(Mediaselection[i])) {
								match = true;
								Log.info("list of Media selection under add devices are : " + mediatypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS,
										"The list of media selection under Add device are: " + mediatypes.getText());
							}
						}
						sa.assertTrue(match);
					}

				} else {
					System.out.println("dropdown value inside Vender/Model is empty");
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values available inside Media selection dropdown for adding devices");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "FAilure at Media selection dropdown");
			}

			boolean masAddrss = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress"))
					.isDisplayed();
			sa.assertTrue(masAddrss, "MAC Address field under add device is not available");

//		try {
//			boolean serial=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).isDisplayed();
//			sa.assertTrue(serial, "Serial number field is not available");
//			
//			ExtentTestManager.getTest().log(LogStatus.PASS,"serial number field is available under create device for Equipment" );
//		}catch(AssertionError e) {
//			e.printStackTrace();
//			ExtentTestManager.getTest().log(LogStatus.FAIL,"serial number field is not available under create device for Equipment" );
//		}

//		    try {
//			boolean lanlink=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();
//			sa.assertTrue(lanlink, "Lanlink forwarding chckbox under add device is not available");
//			
//			ExtentTestManager.getTest().log(LogStatus.PASS,"link lost forwarding checkbox is available under create device for Equipment" );
//			}catch(AssertionError e) {
//				e.printStackTrace();
//				ExtentTestManager.getTest().log(LogStatus.FAIL,"link lost forwarding checkbox is not available under create device for Equipment" );
//			}

			// Country dropdown
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_countrydiv")));
			Thread.sleep(5000);
			System.out.println("Clicked on Country dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Country dropdown");
			Log.info("Clicked on Country dropdown");

			List<WebElement> cntrylist = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement countrylist : cntrylist) {

				System.out.println("Available Country name is : " + countrylist.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Available Country name is : " + countrylist.getText().toString());
				Log.info("Available Country name is :" + countrylist.getText().toString());
			}

			// City dropdown
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip__citydiv")));
			Thread.sleep(5000);
			System.out.println("Clicked on City dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on City dropdown");
			Log.info("Clicked on City dropdown");

			List<WebElement> citylist = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement ctylist : citylist) {

				System.out.println("Available City name is : " + ctylist.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Available City name is : " + ctylist.getText().toString());
				Log.info("Available City name is :" + ctylist.getText().toString());
			}

			// Select City toggle button
			try {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citytogglebutton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, "cliked on 'Select City' toggle button");
			} catch (NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Select City' toggle button is not available");
			}

			// City name
			boolean cityname = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield"))
							.isDisplayed();
			sa.assertTrue(cityname, " 'City name' field is not getting displyed");

			// City Code
			boolean citycode = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield"))
							.isDisplayed();
			sa.assertTrue(citycode, " 'City Code' field is not getting displyed");

			// Site name
			boolean sitename = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield"))
							.isDisplayed();
			sa.assertTrue(sitename, " 'Site name' field is not getting displyed");

			// Site Code
			boolean sitecode = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield"))
							.isDisplayed();
			sa.assertTrue(sitecode, " 'Site Code' field is not getting displyed");

			// Premise name
			boolean premisename = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield"))
							.isDisplayed();
			sa.assertTrue(premisename, " 'Premise name' field is not getting displyed");

			// Premise Code
			boolean premisecode = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield"))
							.isDisplayed();
			sa.assertTrue(premisecode, " 'Premise Code' field is not getting displyed");

			// Select City toggle button to get site dropdown and premise dropdown
			try {
				Clickon(getwebelement(xml.getlocator(
						"//locators/" + application + "/AddCPEforIntermediateEquip_disabledCityToggleButton")));
//				ExtentTestManager.getTest().log(LogStatus.PASS, "cliked on 'Select City' toggle button");
			} catch (NoSuchElementException e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Select City' toggle button is not available");
			}

			// Site dropdown
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitediv")));
			Thread.sleep(5000);
			System.out.println("Clicked on Site dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Site dropdown");
			Log.info("Clicked on Site dropdown");

			List<WebElement> stelist = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement sitelist : stelist) {

				System.out.println("Available site name is : " + sitelist.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Available Site name is : " + sitelist.getText().toString());
				Log.info("Available Site name is :" + sitelist.getText().toString());
			}

			// Premise dropdown
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisediv")));
			Thread.sleep(5000);
			System.out.println("Clicked on Premise dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Premise dropdown");
			Log.info("Clicked on Premise dropdown");

			List<WebElement> prmlist = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement premiselist : prmlist) {

				System.out.println("Available Premise name is : " + premiselist.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Available Premise name is : " + premiselist.getText().toString());
				Log.info("Available Premise name is :" + premiselist.getText().toString());
			}

			boolean Ok = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton"))
					.isDisplayed();
			sa.assertTrue(Ok, "OK button under add device is not available");

			boolean cancel = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_cancelbutton"))
					.isDisplayed();
			sa.assertTrue(cancel, "cancel button under add device is not available");

			

			ExtentTestManager.getTest().log(LogStatus.PASS, "Fields successfully verified for Add site order");
		} catch (AssertionError e) {

			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");

		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_cancelbutton")));

	}

	public void existingCity(String application, String existingcityselectionmode, String city)
			throws InterruptedException, DocumentException {

		// Existing City
		if (existingcityselectionmode.equalsIgnoreCase("yes")) {

			if (city.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under City dropdown");

			} else {
				// City dropdown
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip__citydiv")));
				Thread.sleep(5000);
				System.out.println("Clicked on City dropdown");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on City dropdown");

				List<WebElement> ctylist = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
				for (WebElement citylist : ctylist) {

					System.out.println("Available City name is : " + citylist.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Step : Available City name is : " + citylist.getText().toString());
				}

				// click on City
				WebElement cty = driver.findElement(By.xpath("//div[contains(text(),'" + city + "')]"));
				System.out.println("Selected City dropdowm is : " + cty.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Selected City dropdowm is : " + cty.getText().toString());
				cty.click();
				Thread.sleep(5000);
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Existing city value is not selected");
		}

	}

	public void newcity(String application, String newcityselectionmode, String cityname, String citycode)
			throws InterruptedException, IOException, DocumentException {

		// New City
		if (newcityselectionmode.equalsIgnoreCase("yes")) {

			if (cityname.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for City name field  is not added");
			} else {
				// City Name Field
				SendKeys(
						getwebelement(xml
								.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield")),
						cityname);
				Thread.sleep(5000);
				String citynme = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered City Name is : " + citynme);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered City Name is : " + citynme);
			}

			if (citycode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for City Code field  is not added");
			} else {

				// City Code Field
				SendKeys(
						getwebelement(xml
								.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield")),
						citycode);
				Thread.sleep(5000);
				String citycde = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered City Code is : " + citycde);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered City Code is : " + citycde);

			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Add new city is not selected");
		}

	}

	public void addCityToggleButton(String application) throws InterruptedException, DocumentException {

		// Add city Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addcityswitch")));
		Thread.sleep(5000);
	}

	public void existingSite(String application, String existingsiteselectionmode, String site)
			throws InterruptedException, DocumentException {
		// Existing Site
		if (existingsiteselectionmode.equalsIgnoreCase("yes")) {

			if (site.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Site dropdown");

			} else {

				// Site dropdown
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitediv")));
				Thread.sleep(5000);
				System.out.println("Clicked on Site dropdown");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Site dropdown");

				List<WebElement> stelist = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
				for (WebElement sitelist : stelist) {

					System.out.println("Available Site name is : " + sitelist.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Step : Available Site name is : " + sitelist.getText().toString());
				}

				// click on Site
				WebElement ste = driver.findElement(By.xpath("//div[contains(text(),'" + site + "')]"));
				System.out.println("Selected Site dropdown is : " + ste.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Selected Site dropdown is : " + ste.getText().toString());
				ste.click();
				Thread.sleep(5000);
			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Existing Site is not selected");
		}

	}

	public void newSite(String application, String newsiteselectionmode, String sitename, String sitecode)
			throws InterruptedException, IOException, DocumentException {

		// New site
		if (newsiteselectionmode.equalsIgnoreCase("yes")) {

			if (sitename.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Site name field  is not entered");
			} else {
				// Site Name Field
				SendKeys(
						getwebelement(xml
								.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield")),
						sitename);
				Thread.sleep(5000);
				String sitenme = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Site Name is : " + sitenme);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Site Name is : " + sitenme);
			}

			if (sitecode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Site code field  is not entered");
			} else {

				// Site Code Field
				SendKeys(
						getwebelement(xml
								.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield")),
						sitecode);
				Thread.sleep(5000);
				String sitecde = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Site Code is : " + sitecde);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Site Code is : " + sitecde);

			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Add new city is not selected");
		}
	}

	public void newSite_ClickOnSiteTogglebutton(String application, String newsiteselectionmode, String sitename,
			String sitecode) throws InterruptedException, IOException, DocumentException {

		// New site
		if (newsiteselectionmode.equalsIgnoreCase("yes")) {

			if (sitename.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Site name field  is not entered");
			} else {
				// Site Name Field
				SendKeys(getwebelement(xml.getlocator(
						"//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield_sitetogglebutton")),
						sitename);
				Thread.sleep(5000);
				String sitenme = getwebelement(xml.getlocator(
						"//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield_sitetogglebutton"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Site Name is : " + sitenme);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Site Name is : " + sitenme);
			}

			if (sitecode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Site code field  is not entered");
			} else {

				// Site Code Field
				SendKeys(getwebelement(xml.getlocator(
						"//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield_sitetogglebutton")),
						sitecode);
				Thread.sleep(5000);
				String sitecde = getwebelement(xml.getlocator(
						"//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield_sitetogglebutton"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Site Code is : " + sitecde);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Site Code is : " + sitecde);

			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Add new city is not selected");
		}
	}

	public void addSiteToggleButton(String application) throws InterruptedException, DocumentException {

		// Add Site Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addsiteswitch")));
		Thread.sleep(5000);
	}

	public void existingPremise(String application, String existingpremiseselectionmode, String premise)
			throws InterruptedException, DocumentException {

		// Existing Premise
		if (existingpremiseselectionmode.equalsIgnoreCase("yes")) {

			if (premise.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under Site dropdown");
			} else {
				// Premise dropdown
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisediv")));
				Thread.sleep(5000);
				System.out.println("Clicked on Premise dropdown");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Premise dropdown");

				List<WebElement> prmselist = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
				for (WebElement premiselist : prmselist) {

					System.out.println("Available Premise name is : " + premiselist.getText().toString());
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Step : Available Premise name is : " + premiselist.getText().toString());
				}

				// click on Premise
				WebElement prmse = driver.findElement(By.xpath("//div[contains(text(),'" + premise + "')]"));
				System.out.println("Selected Premise dropdown is : " + prmse.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Selected Premise dropdown is : " + prmse.getText().toString());
				prmse.click();
				Thread.sleep(5000);
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Existing Site is not selected");
		}
	}

	public void newPremise(String application, String newpremiseselectionmode, String premisename, String premisecode)
			throws InterruptedException, IOException, DocumentException {

		// New premise
		if (newpremiseselectionmode.equalsIgnoreCase("yes")) {

			if (premisename.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise Name field  is not entered");
			} else {
				// Premise Name Field
				SendKeys(
						getwebelement(xml.getlocator(
								"//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield")),
						premisename);
				Thread.sleep(5000);
				String prmsenme = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Name is : " + prmsenme);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Premise Name is : " + prmsenme);
			}

			if (premisecode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise code field  is not entered");
			} else {
				// Premise Code Field
				SendKeys(
						getwebelement(xml.getlocator(
								"//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield")),
						premisecode);
				Thread.sleep(5000);
				String premisecde = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Code is : " + premisecde);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Premise Code is : " + premisecde);
			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Add new Premise is not selected");
		}

	}

	public void newPremise_clickonSiteToggleButton(String application, String newpremiseselectionmode,
			String premisename, String premisecode) throws InterruptedException, IOException, DocumentException {

		// New premise
		if (newpremiseselectionmode.equalsIgnoreCase("yes")) {

			if (premisename.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise Name field  is not entered");
			} else {
				// Premise Name Field
				SendKeys(getwebelement(xml.getlocator(
						"//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_sitetogglebutton")),
						premisename);
				Thread.sleep(5000);
				String prmsenme = getwebelement(xml.getlocator(
						"//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_sitetogglebutton"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Name is : " + prmsenme);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Premise Name is : " + prmsenme);
			}

			if (premisecode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise code field  is not entered");
			} else {
				// Premise Code Field
				SendKeys(getwebelement(xml.getlocator(
						"//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_sitetogglebutton")),
						premisecode);
				Thread.sleep(5000);
				String premisecde = getwebelement(xml.getlocator(
						"//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_sitetogglebutton"))
								.getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Code is : " + premisecde);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Premise Code is : " + premisecde);
			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Add new Premise is not selected");
		}
	}

	public void newPremise_clickOnPremisetoggleButton(String application, String newpremiseselectionmode,
			String premisename, String premisecode) throws InterruptedException, IOException, DocumentException {

		// New premise
		if (newpremiseselectionmode.equalsIgnoreCase("yes")) {

			if (premisename.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise Name field  is not entered");
			} else {
				// Premise Name Field
				SendKeys(getwebelement(xml.getlocator("//locators/" + application
						+ "/AddCPEforIntermediateEquip_premisenamefield_premisetogglebutton")), premisename);
				Thread.sleep(5000);
				String prmsenme = getwebelement(xml.getlocator("//locators/" + application
						+ "/AddCPEforIntermediateEquip_premisenamefield_premisetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Name is : " + prmsenme);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Premise Name is : " + prmsenme);
			}

			if (premisecode.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " value for Premise code field  is not entered");
			} else {
				// Premise Code Field
				SendKeys(getwebelement(xml.getlocator("//locators/" + application
						+ "/AddCPEforIntermediateEquip_premisecodefield_premisetogglebutton")), premisecode);
				Thread.sleep(5000);
				String premisecde = getwebelement(xml.getlocator("//locators/" + application
						+ "/AddCPEforIntermediateEquip_premisecodefield_premisetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Code is : " + premisecde);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Entered Premise Code is : " + premisecde);
			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Add new Premise is not selected");
		}

	}

	public void addPremiseTogglebutton(String application) throws InterruptedException, DocumentException {

		// Add Premise Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addpremiseswitch")));
		Thread.sleep(5000);
	}

	public void addOvertureCircuit(String application, String serviceName)
			throws InterruptedException, DocumentException, IOException {

		scrolltoend();

		// Click on Add Overture Link
		click_commonMethod(application, "Add Overture", "addOvertureLink", xml);
		Thread.sleep(1000);
		waitForpageload();  waitforPagetobeenable();


		boolean overturePanelHeader = getwebelement(xml.getlocator("//locators/" + application + "/addOverture_overturePanel")).isDisplayed();
		if(overturePanelHeader) {
//			ExtentTestManager.getTest().log(LogStatus.PASS, "'Overture' page is displaying");
			Log.info("'Overture' page is displaying");
			
			addtextFields_commonMethod(application, "Service Name", "addOverture_serviceNameTextField", serviceName , xml);	//Search ame text Field
			
			click_commonMethod(application, "Search", "addOverture_searchButton" , xml);   //Search Button
			Thread.sleep(2000);
			waitForpageload();  waitforPagetobeenable();
			
			
			WebElement selectValueInTable = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderAddOverturePage").replace("value", serviceName));
			try {
				selectValueInTable.isDisplayed();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Records displays for the Service " + serviceName);
				Log.info("Records displays for the Service " + serviceName);
				
				Clickon(selectValueInTable);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Service selected under the table");
				Thread.sleep(1000);
				
				click_commonMethod(application, "OK", "addOverture_OKbutton", xml);
				Thread.sleep(1000);
				waitForpageload();   waitforPagetobeenable();
				
			}catch(Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No record displays for the Service " + serviceName);
				Log.info("No record displays for the Service " + serviceName);
				
				click_commonMethod(application, "cancel", "addOverture_cancelButton" , xml);
			}
			
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Overture' page is not displaying");
			Log.info("'Overture' page is not displaying");
		}
	}
	
	
	public void City_AddSiteorder(String application, String existingcityselection, String city,
			String newcityselection, String xngcityname, String xngcitycode, String sitevalue, String CSR_Name,
			String existingsiteselection, String newsiteselection)
			throws InterruptedException, DocumentException, IOException {
		
		//Existing City
				if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_disabledCitytogglebutton")));
//					 Thread.sleep(5000);
					 
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_City")));
					Thread.sleep(5000);
					Clickon(getwebelement("//div[text()='" + city + "']"));
					Thread.sleep(5000);
					
					ExtentTestManager.getTest().log(LogStatus.PASS, city+ " is selected under Device Xng City dropdown");
					
				//Existing Site	
					if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
		    			
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton")));
						Thread.sleep(3000);
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")));
						Thread.sleep(3000);	
						Clickon(getwebelement("//span[text()='" + sitevalue + "']"));
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, sitevalue+  " is selected under Physical Site dropdown");
		    		}
					
				//New site
					if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
						
//						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddsiteOrdr_disabledSitetogglebutton")));
//						Thread.sleep(3000);
						
						if(CSR_Name.equalsIgnoreCase("null")){
							ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
							System.out.println("No values provided for mandatory field 'CSR Name'");
							
						}else {
							
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
						}
					}
				
					
					
				}
				else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
					Thread.sleep(3000);
					
					//City name 
					 if(xngcityname.equalsIgnoreCase("null")) {
						 ExtentTestManager.getTest().log(LogStatus.FAIL, "City name field is a mandatory field and the value is not provided");
					 }else {
					 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname")), xngcityname);
					 Thread.sleep(3000);
					 ExtentTestManager.getTest().log(LogStatus.PASS, xngcityname+ " is entered in City name field");
					 System.out.println(xngcityname+ " is entered in City name field");
					 Thread.sleep(3000);
					 }
					 
					 //City code
					 if(xngcitycode.equalsIgnoreCase("null")) {
						 ExtentTestManager.getTest().log(LogStatus.FAIL, "City Code field is a mandatory field and the value is not provided");
						 System.out.println("no values provided for city code text field");
					 }else {
					 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode")), xngcitycode);
					 Thread.sleep(3000);
					 ExtentTestManager.getTest().log(LogStatus.PASS, xngcitycode+" is entered in City Code field" );
					 }
					 Thread.sleep(8000);
					 
					
			//add new Site 		 
					try {
						if(CSR_Name.equalsIgnoreCase("null")){
							ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
							System.out.println(" no values provided for 'CSR Name' text field");
							
						}else {
							
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
						System.out.println(CSR_Name+ " is entered under CSR Name field");
						}
						
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " CSR NAme not performed");
					}
				
				}
				
}

	
	public void City_AddIPVPNSiteorder(String application, String existingcityselection, String city,
			String newcityselection, String xngcityname, String xngcitycode, String sitevalue, String CSR_Name,
			String existingsiteselection, String newsiteselection)
			throws InterruptedException, DocumentException, IOException {
		
		//Existing City
				if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

					selectValueInsideDropdown(application, "addIPVPNsiteOrder_cityDropdown", "City", city, xml);
					
				//Existing Site	
					if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
		    			
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton")));
						Thread.sleep(2000);
						
						selectValueInsideDropdown(application, "addIPVPNsiteOrer_siteDropdown", "Physical Site", sitevalue, xml);
		    		}
					
				//New site
					else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
						
						if(CSR_Name.equalsIgnoreCase("null")){
							ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
							System.out.println("No values provided for mandatory field 'CSR Name'");
							
						}else {
							
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
						}
					}
				}
				else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
					Thread.sleep(3000);
					
					//City name 
					 if(xngcityname.equalsIgnoreCase("null")) {
						 ExtentTestManager.getTest().log(LogStatus.FAIL, "City name field is a mandatory field and the value is not provided");
					 }else {
					 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname")), xngcityname);
					 Thread.sleep(3000);
					 ExtentTestManager.getTest().log(LogStatus.PASS, xngcityname+ " is entered in City name field");
					 System.out.println(xngcityname+ " is entered in City name field");
					 Thread.sleep(3000);
					 }
					 
					 //City code
					 if(xngcitycode.equalsIgnoreCase("null")) {
						 ExtentTestManager.getTest().log(LogStatus.FAIL, "City Code field is a mandatory field and the value is not provided");
						 System.out.println("no values provided for city code text field");
					 }else {
					 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode")), xngcitycode);
					 Thread.sleep(3000);
					 ExtentTestManager.getTest().log(LogStatus.PASS, xngcitycode+" is entered in City Code field" );
					 }
					 Thread.sleep(8000);
					 
			//add new Site 		 
					try {
						if(CSR_Name.equalsIgnoreCase("null")){
							ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
							System.out.println(" no values provided for 'CSR Name' text field");
							
						}else {
							
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
						System.out.println(CSR_Name+ " is entered under CSR Name field");
						}
						
					}catch(Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, " CSR NAme not performed");
					}
				}
		}

	
	
	
	public void validateCity_AddSiteOrder(String application) throws InterruptedException, DocumentException {

		// City dropdown
		boolean CIty = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_City")).isDisplayed();
		sa.assertTrue(CIty, "City dropdown is not displayed");
		if (CIty) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'City' mandatory dropdown is displaying under 'Add Site Order' page as expected");

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'City' mandatory dropdown is not available under 'Add Site Order' page");
		}

		// select city toggle button
		boolean selectcitytoggle = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")).isDisplayed();
		sa.assertTrue(selectcitytoggle, "Select city toggle button for Add Site is not available");
		if (selectcitytoggle) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Select City' toggle button is displaying under 'Add Site Order' page as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Select City' toggle button is not avilable under 'Add Site Order' page ");
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
		Thread.sleep(5000);

		scrolltoend();
		Thread.sleep(3000);

		System.out.println("Scrolling down to validate error messgae for City name and city code");
		// Click on Next button to get warning message for XNG City name and XNG City
		// Code text fields
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(5000);

		WebElement deviceCountry = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country"));
		ScrolltoElement(deviceCountry);
		Thread.sleep(3000);

		System.out.println(
				"scrolling above till device country for validating error message for 'city name ' and 'city code'");
		// XNG City Name Error message
		boolean xngCitynameErr = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_xngcitynameerrmsg")).isDisplayed();
		sa.assertTrue(xngCitynameErr, " 'XNG City Name' warning message is not displayed ");
		if (xngCitynameErr) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Warning message for 'XNG City Name' dropdown is displying under 'Add Site order' page as expected ");
			String citynameErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_xngcitynameerrmsg")).getText();
			System.out.println("XNG City Name  message displayed as : " + citynameErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for 'XNG City Name' text field displayed as : " + citynameErrMsg);
			Log.info("XNG City Name warning message displayed as : " + citynameErrMsg);

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Warning message for 'XNG City Name' dropdown is not displaying under 'Add Site order' page ");
		}

		// XNG City Code Error message
		boolean xngCitycodeErr = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityCodeerrmsg")).isDisplayed();
		sa.assertTrue(xngCitycodeErr, " 'XNG City Code' warning message is not displayed ");
		if (xngCitycodeErr) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Warning message for 'XNG City Code' dropdown is displying under 'Add Site order' page as expected ");
			String cityCodeErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityCodeerrmsg")).getText();
			System.out.println("XNG City Name  message displayed as : " + cityCodeErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for 'XNG City Code' text field displayed as : " + cityCodeErrMsg);
			Log.info("XNG City Code warning message displayed as : " + cityCodeErrMsg);

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Warning message for 'XNG City Code' dropdown is not displaying under 'Add Site order' page ");
		}

//xng city name
		boolean XNGcityname = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname"))
				.isDisplayed();
		sa.assertTrue(XNGcityname, "XNG city name field for Add Site is not available");
		if (XNGcityname) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'XNG City name field is displaying under 'Add Site order' as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'XNG City name field is not available under 'Add Site order'");
		}

//xng city code
		boolean XNGcitycode = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode"))
				.isDisplayed();
		sa.assertTrue(XNGcitycode, "XNG city code field for Add Site is not available");
		if (XNGcitycode) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'XNG City code field is displaying under 'Add Site order' as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'XNG City code field is not available under 'Add Site order'");
		}

	}
	
	

	public void validateCountry_AddSiteorder(String application) throws InterruptedException, DocumentException {

		boolean COuntry = false;

		COuntry = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")).isDisplayed();
		sa.assertTrue(COuntry, "Country dropdown is not displayed");
		if (COuntry) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Country' mandatory dropdown is displaying under 'Add Site Order' page as expected");
			System.out.println("Country dropdown is displaying");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")));
			List<WebElement> listofcountry = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

			if (listofcountry.size() >= 1) {
				for (WebElement countrytypes : listofcountry) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"The list of country inside dropdown is: " + countrytypes.getText());

				}
			} else {
				System.out.println("no values are available inside Country dropdown for Add site order");
//				ExtentTestManager.getTest().log(LogStatus.FAIL,"no values are available inside Country dropdown for Add site order");
			}

//click on Blank page	
			clickOnBankPage();
			Thread.sleep(3000);

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Country' mandatory dropdown is not available under 'Add Site Order' page");
		}

	}

	public void Site_AddSiteOrder(String application, String existingsiteselection, String sitevalue,
			String newsiteselection, String CSR_Name) throws InterruptedException, IOException, DocumentException {

		// Existing Site
		if ((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {

			if (sitevalue.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Physical Site field is mandatory and no values are provided");
			} else {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='" + sitevalue + "']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, sitevalue + " is selected under Physical Site dropdown");
			}
		}

		else if ((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton")));
			Thread.sleep(3000);

			if (CSR_Name.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");

			} else {

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")),
						CSR_Name);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, CSR_Name + " is entered under CSR Name field");
			}
		}

	}

	public void Countyr_AddSiteOrder(String application, String country)
			throws InterruptedException, DocumentException {

		addDropdownValues_commonMethod(application, "Device Country", "Addsiteorder_Country", country, xml);
		
	}
	
	
	public void Countyr_AddIPVPNSiteOrder(String application, String country)
			throws InterruptedException, DocumentException, IOException {

		// Select Existing Country
			selectValueInsideDropdown(application, "addIPVPNsiteOrder_countryDropdown" , "Country", country, xml);
	}

	public void validateSite_AddSiteOrder(String application) throws InterruptedException, DocumentException {

		// CSR name field
		boolean csr_name = false;
		csr_name = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")).isDisplayed();
		sa.assertTrue(csr_name, "CSR_Name field is not displayed");
		if (csr_name) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'CSR Name' text field is displaying under 'Add Site order' page as expected");
			System.out.println("CSR name field is dipslaying as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'CSR Name' text field is not available under 'Add Site order' page");
		}

		// click on site toggle button to check Physical site dropdown
		boolean sitetogglebutton = false;
		sitetogglebutton = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton"))
				.isDisplayed();
		sa.assertTrue(sitetogglebutton, "select Site toggle button is not displayed");
		if (sitetogglebutton) {
			System.out.println("site order toggle button is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Select Site' toggle button is displaying under 'Add Site Order' page as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Select Site' toggle button is not avilable under 'Add Site Order' page");
		}

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton")));
		Thread.sleep(3000);

		// Check for Error message for physical Site
		scrolltoend();
		Thread.sleep(3000);
		System.out.println("scrolling down to click n OK button to find eror message for site Dropdown");
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(5000);

		WebElement deviceCountry = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country"));
		ScrolltoElement(deviceCountry);
		Thread.sleep(3000);

		System.out.println(
				"scrolling up back till device country dropodwn to find error message validation for physical site");
		boolean physicalsiteErr = false;
		physicalsiteErr = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_physicalsiteErrmsg")).isDisplayed();
		sa.assertTrue(physicalsiteErr, "Physical Site dropdown warning is not displayed ");
		if (physicalsiteErr) {
			System.out.println("Physical Site Error message is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Physite Site' dropdown warning message is displaying under 'Add Site Order' page as expected");
			String physicalsiteErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_physicalsiteErrmsg")).getText();
			System.out.println("Physical Site  message displayed as : " + physicalsiteErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for Physical Site dropdown displayed as : " + physicalsiteErrMsg);
			Log.info("Physical Site validation message displayed as : " + physicalsiteErrMsg);
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Physical Site' dropdown warning message is not displaying under 'Add Site Order' page");
			System.out.println("Physical site warning message is not displaying");
		}

		// Physical Site dropdown
		boolean SIte = false;
		SIte = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")).isDisplayed();
		sa.assertTrue(SIte, "PhysicalSite dropdown is not displayed");
		if (SIte) {
			System.out.println("Physical Site dropdown is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'physical Site' dropdown is displaying under 'Add Site order' page as expected");

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Physical Site' dropdown is not available under 'Add Site Order' page");
		}
	}

	public void validatePerformancereporting_AddSiteOrder(String application)
			throws InterruptedException, DocumentException, IOException {

		String[] Performancereporting = { "Follow Service", "no" };

		// Performance reporting dropdown
		boolean performancereport = false;
		performancereport = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")).isDisplayed();
		sa.assertTrue(performancereport, "performance reporting dropdown is not displayed");
		if (performancereport) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Performance reporting' dropdown is displaying under 'Add Site order' as expected");
			Thread.sleep(3000);

			// check default value
			String performanceRprtDefaultValues = Gettext(getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_performancereportingdefaultvalue")));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					performanceRprtDefaultValues + " is displaying under 'Performance reporting' dropdown by default");

			// check list of values inside Performance Reporting drodpown
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")));
			List<WebElement> listofperformancereporting = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

			if (listofperformancereporting.size() >= 1) {
				for (WebElement perfoemancereportingtypes : listofperformancereporting) {
					boolean match = false;
					for (int i = 0; i < Performancereporting.length; i++) {
						if (perfoemancereportingtypes.getText().equals(Performancereporting[i])) {
							match = true;
							Log.info("list of performance reporting : " + perfoemancereportingtypes.getText());
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"list of performance reporting for AddSite order : "
											+ perfoemancereportingtypes.getText());
						}

					}

					sa.assertTrue(match);

				}
			} else {
				System.out.println("no values are available inside performance reporting dropdown for Add site order");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"no values are available inside performance reporting dropdown for Add site order");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Performance reporting' dropdown is not availble under 'Add Site order' ");
		}
	}

	public void RouterConfigurationViewIPv4_addSiteOrder(String application, String performReport)
			throws InterruptedException, DocumentException {

		// Perfomance Reporting
		if (performReport.equalsIgnoreCase("Null")) {

			System.out.println("NO changes in 'Router Configuration View IPv4' dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Router Configuration View IPv4 value is not provided. 'Follow Service' is selected by default");

		} else {
			try {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/routerConfigurationviewIPv4_out")));
				Thread.sleep(3000);
				Clickon(getwebelement(
						"//div[label[text()='Router Configuration View IPv4']]//div[text()='" + performReport + "']"));
				Thread.sleep(3000);

				String actualvalue = getwebelement("(//div[label[text()='Router Configuration View IPv4']]//span)[2]")
						.getText();
				ExtentTestManager.getTest().log(LogStatus.PASS,
						actualvalue + " is selected under Router Configuration View IPv4 dropdown");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Router Configuration View IPv4' dropdown is not displaying under 'Add Site order' page");
				System.out.println(
						" 'Router Configuration View IPv4' dropdown is not displaying under 'Add Site order' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" not able to select value under 'Router Configuration View IPv4' checkbox");
				System.out.println(" not able to select value under 'Router Configuration View IPv4' checkbox");
			}
		}
	}

	public void performancereporting_AddSiteOrder(String application, String performReport)
			throws InterruptedException, DocumentException {
   	 
   	//Perfomance Reporting	
		if(performReport.equalsIgnoreCase("Null")) {
			
			System.out.println("NO changes in 'Performance Reporting' dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Performance reporting value is not provided. 'Follow Service' is selected by default");
			
		}else {
	try {		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting_xbutton")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[label[text()='Performance Reporting']]//div[text()='"+ performReport +"']"));
		Thread.sleep(3000);
		
		String actualvalue=getwebelement("(//div[label[text()='Performance Reporting']]//span)[2]").getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is selected under Performance reporting dropdown");
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Performance reporting' dropdown is not displaying under 'Add Site order' page");
		System.out.println( " 'Performance reporting' dropdown is not displaying under 'Add Site order' page");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " not able to select value under 'Performance reporting' checkbox");
		System.out.println(" not able to select value under 'Performance Reporting' checkbox");
	}
		}
    }

	public void managedSite_AddSiteOrder(String application, String managedSite)
			throws InterruptedException, DocumentException {

		// Managed Site
		if (managedSite.equalsIgnoreCase("Null")) {

			System.out.println("NO changes in 'Managed Site' dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Managed Site value is not provided. 'Follow Service' is selected by default");

		} else {
			try {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managedSite_xbutton_out")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[label[text()='Managed Site']]//div[text()='" + managedSite + "']"));
				Thread.sleep(3000);

				String actualvalue = getwebelement("(//div[label[text()='Managed Site']]//span)[2]").getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is selected under Managed Site dropdown");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Managed Site' dropdown is not displaying under 'Add Site order' page");
				System.out.println(" 'Managed Site' dropdown is not displaying under 'Add Site order' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " not able to select value under 'Managed Site' checkbox");
				System.out.println(" not able to select value under 'Managed Site' checkbox");
			}
		}
	}

	public void perCoSperformancereporting_AddSiteOrder(String application, String performReport)
			throws InterruptedException, DocumentException {

		// Perfomance Reporting
		if (performReport.equalsIgnoreCase("Null")) {

			System.out.println("NO changes in 'Performance Reporting' dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Per CoS Performance reporting input value is not provided. 'Follow Service' is selected by default");

		} else {
			try {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/perCoSperformancereporting_xbutton_out")));
				Thread.sleep(3000);
				Clickon(getwebelement(
						"//div[label[text()='Per CoS Performance Reporting']]//div[text()='" + performReport + "']"));
				Thread.sleep(3000);

				String actualvalue = getwebelement("(//div[label[text()='Per CoS Performance Reporting']]//span)[2]")
						.getText();
				ExtentTestManager.getTest().log(LogStatus.PASS,
						actualvalue + " is selected under Per CoS Performance reporting dropdown");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Per CoS Performance reporting' dropdown is not displaying under 'Add Site order' page");
				System.out.println(
						" 'Per CoS Performance reporting' dropdown is not displaying under 'Add Site order' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" not able to select value under 'Per CoS Performance reporting' checkbox");
				System.out.println(" not able to select value under 'Per CoS Performance Reporting' checkbox");
			}
		}
	}

	public void validateProactiveMonitoring_AddSiteOrder(String application)
			throws InterruptedException, DocumentException, IOException {

		String[] Proactivemonitoring = { "Follow Service", "no" };

		// pro active monitoring
		boolean proactivemonitoring = false;
		proactivemonitoring = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")).isDisplayed();
		sa.assertTrue(proactivemonitoring, "pro active monitoring dropdown is not displayed");
		if (proactivemonitoring) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Proactie Monitoring' dropdown is displaying under 'Add Site Order' page as Expected");

			// check default value
			String proactiveMonitorDefaultValues = Gettext(getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoringdefaultvalue")));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					proactiveMonitorDefaultValues + " is displaying under 'roactive Monitoring' dropdown by default");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")));
			List<WebElement> listofproactivemonitoring = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

			if (listofproactivemonitoring.size() >= 1) {
				for (WebElement proactivemonitoringtypes : listofproactivemonitoring) {

					boolean match = false;
					for (int i = 0; i < Proactivemonitoring.length; i++) {
						if (proactivemonitoringtypes.getText().equals(Proactivemonitoring[i])) {
							match = true;
							Log.info("list of pro active monitoring : " + proactivemonitoringtypes.getText());

							ExtentTestManager.getTest().log(LogStatus.PASS,
									"The list of proactive monitoring inside dropdown while  adding site order is: "
											+ proactivemonitoringtypes.getText());
						}
					}
					sa.assertTrue(match);

				}
			} else {

				System.out.println("no values are available inside pro active monitoring dropdown for Add site order");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"no values are available inside pro active monitoring dropdown for Add site order");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Proactie Monitoring' dropdown is not available under 'Add Site Order' page ");
		}
	}

	public void proactiveMonitoring_AddSiteOrder(String application, String ProactiveMonitor)
			throws InterruptedException, DocumentException {
   	 
   	//Pro active monitoring	
 		if(ProactiveMonitor.equalsIgnoreCase("Null")) {
 			ExtentTestManager.getTest().log(LogStatus.PASS, "Pro active monitoring value is not provided. 'Follow Service' is selected by default");
 			
 		}else {
 	try {		
 		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsitorder_proactivemonitoring_xbutton")));
 		Thread.sleep(3000);
 		Clickon(getwebelement("//div[label[text()='Proactive Monitoring']]//div[text()='"+ ProactiveMonitor +"']"));
 		Thread.sleep(3000);
 		
 		String actualvalue=getwebelement("(//div[label[text()='Proactive Monitoring']]//span)[2]").getText();
 		ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue+ " is selected under proactive monitoring dropdown");
 		}catch(NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Pro active Monitoring' dropdown is not displaying under 'Add Site order' page");
			System.out.println( " 'pro active Monitoring' dropdown is not displaying under 'Add Site order' page");
		}catch(Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " not able to select value under 'Proactive Monitoring' dropdown");
			System.out.println(" not able to select value under 'pro active Monitoring' dropdown");
		}
 		} 
    }

	public void validateSmartsMOnitoring_AddSiteOrder(String application)
			throws InterruptedException, DocumentException, IOException {

		String[] Smartmonitoring = { "Follow Service", "no" };

		// smarts monitoring
		boolean smartmonitoring = false;
		smartmonitoring = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring"))
				.isDisplayed();
		sa.assertTrue(smartmonitoring, "Smart monitoring dropdown is not displayed");
		if (smartmonitoring) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Smart Monitoring' dropdown is displaying under 'Add Site Order' page as expected");
			// check default value
			String smartmonitorDefaultValues = Gettext(getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoringdefaultvalue")));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					smartmonitorDefaultValues + " is displaying under 'Smart Monitoring' dropdown by default");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring")));
			List<WebElement> listofsmartmonitoring = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

			if (listofsmartmonitoring.size() >= 1) {
				for (WebElement smartmonitoringtypes : listofsmartmonitoring) {

					boolean match = false;
					for (int i = 0; i < Smartmonitoring.length; i++) {
						if (smartmonitoringtypes.getText().equals(Smartmonitoring[i])) {
							match = true;
							Log.info("list of smart monitoring are : " + smartmonitoringtypes.getText());
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"The list of smart monitoring  inside dropdown while  adding site order is: "
											+ smartmonitoringtypes.getText());
						}
					}

					sa.assertTrue(match);
				}
			} else {

				System.out.println("no values are available inside smart monitoring dropdown for Add site order");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"no values are available inside smart monitoring dropdown for Add site order");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Smart Monitoring' dropdown is not avilable under 'Add Site Order' page");
		}
	}

	public void smartsMonitoring_AddSiteOrder(String application, String smartmonitor)
			throws InterruptedException, DocumentException {
   	 
		if(smartmonitor.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Smart monitoring value is not provided. 'Follow Service' is selected by default");
		}else {
	try {		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring_xbutton")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[label[text()='Smarts Monitoring']]//div[text()='"+ smartmonitor +"']"));
		Thread.sleep(3000);
		
		String actualValue=getwebelement("(//div[label[text()='Smarts Monitoring']]//span)[2]").getText();
		ExtentTestManager.getTest().log(LogStatus.PASS, actualValue+ " is selected under Smart monitoring dropdown");
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Smart Monitoring' dropdown is not displaying under 'Add Site order' page");
		System.out.println( " 'Smart Monitoring' dropdown is not displaying under 'Add Site order' page");
	}catch(Exception err) {
		err.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL, " not able to select value under 'Smart Monitoring' dropdown");
		System.out.println(" not able to select value under 'Smart Monitoring' dropdown");
	}
		}

}

	public void validateSiteAlias_AddSiteOrder(String application) throws InterruptedException, DocumentException {

		// Site alias Field
		boolean sitealias = false;
		try {
			sitealias = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias"))
					.isDisplayed();
			sa.assertTrue(sitealias, "Site alias field is not displayed");
			if (sitealias) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Site Alias' text field is displaying under 'Add Site order' page as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Site Alias' text field is not displaying under 'Add Site order' page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Site Alias' text field is not displaying under 'Add Site order' page");
		}
	}

	public void SiteAlias_AddSiteOrder(String application, String siteallias)
			throws InterruptedException, IOException, DocumentException {

		if (siteallias.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered for 'Site Alias' field");
		} else {
			try {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")),
						siteallias);
				Thread.sleep(3000);

				String actualvalue = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'Site Alias' field");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Site Alias' field is not displaying under 'Add Site order' page");
				System.out.println(" 'Site Alias' field is not dispyating under 'Add Site order' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Site Alias' field");
				System.out.println(" Not able to enter value under 'Site Alias' field");
			}
		}

	}

	public void validateVlanID_AddSiteOrder(String application) throws InterruptedException, DocumentException {
		boolean vlanid = false;
		try {
			vlanid = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).isDisplayed();
			sa.assertTrue(vlanid, "VLAN id field is not displayed");
			if (vlanid) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"'VLAN ID' text field is displaying under 'Add Site order' page as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'VLAN ID' text field is not displaying under 'Add Site order' page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'VLAN ID' text field is not displaying under 'Add Site order' page");
		}
	}

	public void VLANid_AddSiteOrder(String application, String VLANid)
			throws InterruptedException, IOException, DocumentException {

		if (VLANid.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered for 'Vlan id' field");
		} else {
			try {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")), VLANid);
				Thread.sleep(3000);

				String actualvalue = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid"))
						.getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under Vlan id field");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Vlan Id' field is not displating under 'Add Site order' page");
				System.out.println(" 'Vlan Id' field is not displating under 'Add Site order' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Vlan Id' field");
				System.out.println(" Not able to enter value under 'Vlan Id' field");
			}
		}

	}

	public void valiadateDCAEnabledsite_AddSieOrder(String application) throws InterruptedException, DocumentException {

		String[] cloudServiceprovider = { "Amazon Web Service", "Microsoft Azure" };
		boolean DCAEnabledsite = false;

		// DCA Enabled site
		DCAEnabledsite = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isDisplayed();
		sa.assertTrue(DCAEnabledsite, "DCA enabled site is not displayed ");
		if (DCAEnabledsite) {

			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'DCA Enabled Site' checkbox is displaying under 'Add Site order' page as expected");
			boolean DCAselection = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isSelected();
			sa.assertFalse(DCAselection, "DCA checkbox under Addsite order is selected by default");
			if (DCAselection) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" ' DCA Enabled Site' checkbox should not be selected by default");
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'DCA Enabled Site' checkbox is not selected by default as expected");

				ExtentTestManager.getTest().log(LogStatus.INFO,
						"when DCA Enabled site checkbox is selected, Cloud service provider dropdown should occur"
								+ " Cloud service provider dropdown should have values: " + "  1) Amazon Web Service "
								+ "  2) Microsoft Azure");

				Thread.sleep(5000);
				// For Cloud service provider
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
				Log.info("DCA site is enabled to add cloud service provider details");
				Thread.sleep(3000);

				boolean DCAafterSelection = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox"))
								.isSelected();
				if (DCAafterSelection) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"DCA site is selected to add cloud service provider details");
					Thread.sleep(3000);

					boolean cloudservice = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider"))
									.isDisplayed();
					sa.assertTrue(cloudservice, "cloud service provider dropdown is not displayed");
					if (cloudservice) {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								" 'Cloud Service Provider' dropdown is displaying when 'DCA Enabled Site' checkbox is selected as expected");

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
						List<WebElement> listofcloudservices = driver
								.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

						if (listofcloudservices.size() > 0) {
							for (WebElement cloudserviceprovidertypes : listofcloudservices) {

								boolean match = false;
								for (int i = 0; i < cloudServiceprovider.length; i++) {
									if (cloudserviceprovidertypes.getText().equals(cloudServiceprovider[i])) {
										match = true;
										Log.info("list of cloud service providers are : "
												+ cloudserviceprovidertypes.getText());
										ExtentTestManager.getTest().log(LogStatus.PASS,
												"The list of cloudservice provider inside dropdown while  adding site order is: "
														+ cloudserviceprovidertypes.getText());
									}
								}
								sa.assertTrue(match);
							}
						} else {
							System.out.println(
									"no values are available inside cloudservice provider dropdown for Add site order");
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"no values are available inside cloudservice provider dropdown for Add site order");

						}
					} else {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								" 'Cloud Service Provider' dropdown is not available when 'DCA Enabled Site' checkbox is selected");
					}

				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'DCA Enabled Site' checkbox is not getting selected");
				}
			}

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
			Thread.sleep(5000);

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'DCA Enabled Site' checkbox is not available under 'Add Site order' page");
		}
	}

	public void DCAEnabledSite_AddSiteOrder(String application, String DCAenabledsite, String cloudserviceprovider)
			throws InterruptedException, DocumentException {

		// DCA Enabled Site
		if (DCAenabledsite.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "DCA enabled checkbox is selected");

			// Cloud Service provider
			if (cloudserviceprovider.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"DCA cloud service provider dropdown is mandatory. No values are provided");
			} else {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						cloudserviceprovider + " is selected under 'cloud service provider' dropdown");
			}

		} else {
			Log.info("DCA site is not selected");
			ExtentTestManager.getTest().log(LogStatus.PASS, "DCA enabled checkbox is not selected");
		}

	}

	public void validateRemark_AddSiteOrder(String application) throws InterruptedException, DocumentException {
		boolean REmark = false;
		try {
			REmark = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).isDisplayed();
			sa.assertTrue(REmark, " Remark field is not displayed");
			if (REmark) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Remak' field is displaying under 'Add Site order' page as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Remak' field is not displaying under 'Add Site order' page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remak' field is not displaying under 'Add Site order' page");

		}

	}

	public void remark_AddSiteOrder(String application, String remark)
			throws InterruptedException, IOException, DocumentException {

		if (remark.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered under remark ");
		} else {
			try {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")), remark);
				Thread.sleep(3000);

				String actualvalue = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark"))
						.getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'remark' field");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Remark' field is not displating under 'Add Site order' page");
				System.out.println(" 'Remark' field is not displating under 'Add Site order' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'Remark' field");
				System.out.println(" Not able to enter value under 'Remark' field");
			}
		}

	}

	public void nontermination_AddSiteorder(String application, String nonterminatepoinr)
			throws InterruptedException, DocumentException {

		// Non- termination point
		if (nonterminatepoinr.equalsIgnoreCase("yes")) {
			try {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
				Thread.sleep(3000);

				boolean nonTerminationSelection = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
				if (nonTerminationSelection) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Non-Termination point' checkbox is selected as expected");
					System.out.println(" 'Non-Termination point' checkbox is selected as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non-Termination point' checkbox is not selected");
					System.out.println(" 'Non-Termination point' checkbox is not selected");
				}

				ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Non-Termination point' checkbox is not dipslaying under 'Add Site order' page");
				System.out.println(" Non-Termination point' checkbox is not dipslaying under 'Add Site order' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'non-Termination point' checkbox");
				System.out.println(" Not able to click on 'non-Termination point' checkbox");
			}
		} else {
			System.out.println("Non termination point checkbox is not selected as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
		}

	}

	public void protected_AddSiteOrder(String application, String Protected)
			throws InterruptedException, DocumentException {

		// Protected
		if (Protected.equalsIgnoreCase("yes")) {
			try {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
				Thread.sleep(3000);

				boolean protectedSelection = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
				if (protectedSelection) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Protected' checkbox is selected as expected");
					System.out.println(" 'Protected' checkbox is selected as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Non-Termination point' checkbox is not selected");
					System.out.println(" 'Non-Termination point' checkbox is not selected");
				}
				ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Protected' checkbox is not displaying under 'Add Site order' page");
				System.out.println(" 'Protected' checkbox is not displaying under 'Add Site order' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'non-Termination point' checkbox");
				System.out.println(" Not able to click on 'non-Termination point' checkbox");
			}

		} else {
			System.out.println("Protected checkbox is not selecetd as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
		}

	}

	public void devicename_AddSiteOrder(String application, String devicename)
			throws InterruptedException, IOException, DocumentException {

		// Device name
		if (devicename.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"device name field is mandatory. No values entered under 'device name' field");
		} else {
			try {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")),
						devicename);
				Thread.sleep(3000);

				String actualvalue = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield"))
								.getAttribute("value");

				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'device name' field");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Device name' field is not displaying under 'Add Site Order' page");
				System.out.println(" 'Device name' field is not displaying under 'Add Site Order' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Device name' field");
				System.out.println(" Not able to enter value in 'Device name' field");
			}
		}

	}

	public void addSiteOrderValues_OnnetOffnet(String application, String interfaceSpeed, String country, String city,
			String CSR_Name, String site, String performReport, String ProactiveMonitor, String smartmonitor,
			String technology, String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider,
			String sitevalue, String remark, String xngcityname, String xngcitycode, String devicename,
			String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection)
			throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		
		Countyr_AddSiteOrder(application, country);

		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode,
				sitevalue, CSR_Name, existingsiteselection, newsiteselection);


		// scroll to bottom
		scrolltoend();
		Thread.sleep(3000);

		performancereporting_AddSiteOrder(application, performReport);

		proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

		smartsMonitoring_AddSiteOrder(application, smartmonitor);

		SiteAlias_AddSiteOrder(application, siteallias);

		VLANid_AddSiteOrder(application, VLANid);

		DCAEnabledSite_AddSiteOrder(application, DCAenabledsite, cloudserviceprovider);

		remark_AddSiteOrder(application, remark);

		technologyP2P_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected);

		Thread.sleep(3000);
		scrolltoend();
		Thread.sleep(3000);
		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void technologyP2P_AddSiteOrder(String application, String technology, String interfaceSpeed,
			String devicename, String nonterminatepoinr, String Protected)
			throws InterruptedException, IOException, DocumentException {

		// Technology
		if (technology.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Technology dropdown is a mandatory field and no values are provided");
		} else {

			if (interfaceSpeed.equals("1GigE")) {

				if (technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture")
						|| technology.equals("Accedian-1G") || technology.equals("Cyan") || technology.equals("Alu")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

					if (technology.equals("Actelis")) {

						System.out.println("No additional fields displays");
					}

					else if (technology.equals("Atrica")) {

						// Device name
						devicename_AddSiteOrder(application, devicename);

						// Non- termination point
						nontermination_AddSiteorder(application, nonterminatepoinr);

					}

					else if (technology.equals("Overture") || technology.equals("Accedian-1G")) {

						// Non- termination point
						nontermination_AddSiteorder(application, nonterminatepoinr);

					}

					else if (technology.equals("Cyan")) {

						// Non- termination point
						nontermination_AddSiteorder(application, nonterminatepoinr);

					}

					else if (technology.equals("Alu")) {

						// Device name
						devicename_AddSiteOrder(application, devicename);

					}
				}
			}

			if (interfaceSpeed.equals("10GigE")) {

				if (technology.equals("Accedian")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

					// Non- termination point
					nontermination_AddSiteorder(application, nonterminatepoinr);

				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
				}

			}
		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");

	}

	public void technologyHubAndSpoke1G_IVRefrencePrimary_AddSiteOrder(String application, String technology,
			String interfaceSpeed, String devicename, String nonterminatepoinr, String Protected)
			throws InterruptedException, IOException, DocumentException {

		// Technology
		if (technology.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Technology dropdown is a mandatory field and no values are provided");
		} else {

			if (technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture")
					|| technology.equalsIgnoreCase("Accedian-1G") || technology.equals("Cyan")
					|| technology.equals("Alu")) {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='" + technology + "']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

				if (technology.equals("Actelis")) {
					System.out.println("No additional fields displays");
				}

				else if ((technology.equals("Atrica"))) {
					// Device name
					devicename_AddSiteOrder(application, devicename);

					// Non- termination point
					nontermination_AddSiteorder(application, nonterminatepoinr);

					// Protected
					protected_AddSiteOrder(application, Protected);
				}

				else if (technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G")) {
					// Non- termination point
					nontermination_AddSiteorder(application, nonterminatepoinr);

					// Protected
					protected_AddSiteOrder(application, Protected);
				}

				else if (technology.equals("Cyan")) {
					// Non- termination point
					nontermination_AddSiteorder(application, nonterminatepoinr);

				}

				else if (technology.equals("Alu")) {
					// Device name
					devicename_AddSiteOrder(application, devicename);

				}
			}
		}
	}

	public void technologyHubAndSpoke1G_IVRefrencePrimary_OffnetSelected_AddSiteOrder(String application,
			String technology, String interfaceSpeed, String devicename, String nonterminatepoinr, String Protected)
			throws InterruptedException, IOException, DocumentException {

		// Technology
		if (technology.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Technology dropdown is a mandatory field and no values are provided");
		} else {

			if (technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G")
					|| technology.equalsIgnoreCase("Accedian")) {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='" + technology + "']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

				if (technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G")) {
					// Non- termination point
					nontermination_AddSiteorder(application, nonterminatepoinr);

					// Protected
					protected_AddSiteOrder(application, Protected);
				}
			}
		}
	}

	public void technologyEPN1G_IVRefrencePrimary_AddSiteOrder(String application, String technology,
			String interfaceSpeed, String devicename, String nonterminatepoinr, String Protected)
			throws InterruptedException, IOException, DocumentException {

		// Technology
		if (technology.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Technology dropdown is a mandatory field and no values are provided");
		} else {

			if (technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture")
					|| technology.equals("Accedian-1G") || technology.equals("Cyan") || technology.equals("Alu")) {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='" + technology + "']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

				if (technology.equals("Actelis")) {
					System.out.println("No additional fields displays");
				}

				else if ((technology.equals("Atrica")) || (technology.equals("Overture"))
						|| (technology.equals("Accedian-1G"))) {
					// Device name
					devicename_AddSiteOrder(application, devicename);

					// Non- termination point
					nontermination_AddSiteorder(application, nonterminatepoinr);

					// Protected
					protected_AddSiteOrder(application, Protected);
				}

				else if (technology.equals("Cyan")) {
					// Non- termination point
					nontermination_AddSiteorder(application, nonterminatepoinr);

				}

				else if (technology.equals("Alu")) {
					// Device name
					devicename_AddSiteOrder(application, devicename);

				}
			}
		}
	}

	public void technologyHubAndSpoke1G_IVRefrenceAccess_AddSiteOrder(String application, String technology,
			String interfaceSpeed, String devicename, String nonterminatepoinr, String Protected, String GCRolo,
			String Vlan, String Vlanether, String primaryVlan, String primaryVlanether)
			throws InterruptedException, IOException, DocumentException {

		// Technology
		if (technology.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Technology dropdown is a mandatory field and no values are provided");
		} else {

			if (interfaceSpeed.equals("1GigE")) {

				if (technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture")
						|| technology.equalsIgnoreCase("Accedian-1G") || technology.equals("Cyan")
						|| technology.equals("Alu")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

					if (technology.equals("Actelis")) {

						System.out.println("No additional fields displays");
					}

					else if ((technology.equals("Atrica")) || (technology.equalsIgnoreCase("Accedian-1G"))) {

						// Non-Termination Point
						nontermination_AddSiteorder(application, nonterminatepoinr);

						// Protected
						protected_AddSiteOrder(application, Protected);
					}

					else if (technology.equals("Overture")) {
						// Non- termination point checkbox
						nontermination_AddSiteorder(application, nonterminatepoinr);

						// Protected checkbox
						protected_AddSiteOrder(application, Protected);

						// GCR OLO Type dropdown
						GCROLOType_AddSiteorder(application, GCRolo);

						// VLAN Text field
						Vlan_AddSiteorder(application, Vlan);

						// VLAN Ether Type dropdown
						Vlanethertype_AddSiteorder(application, Vlanether);

						// Primary VLAN Text Field
						primaryVlan_AddSiteorder(application, primaryVlan);

						// Primary Ether Vlan dropdown
						primaryVlanethertype_AddSiteorder(application, primaryVlanether);

					}

					else if (technology.equals("Cyan")) {
						// Non- termination point checkbox
						nontermination_AddSiteorder(application, nonterminatepoinr);

					}

					else if (technology.equals("Alu")) {
						System.out.println("No Additional fields display for 'Alu' technology");

					}
				}
			}

		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");

	}

	public void technologyHubAndSpoke1G_IVRefrenceAccess_offnetSelected_AddSiteOrder(String application,
			String technology, String interfaceSpeed, String devicename, String nonterminatepoinr, String Protected,
			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether)
			throws InterruptedException, IOException, DocumentException {

		// Technology
		if (technology.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Technology dropdown is a mandatory field and no values are provided");
		} else {

			if (interfaceSpeed.equals("1GigE")) {

				if (technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G")
						|| technology.equals("Accedian")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

					if ((technology.equalsIgnoreCase("Accedian-1G"))) {

						// Non-Termination Point
						nontermination_AddSiteorder(application, nonterminatepoinr);

						// Protected
						protected_AddSiteOrder(application, Protected);
					}

					else if (technology.equals("Overture")) {

						// Non- termination point checkbox
						nontermination_AddSiteorder(application, nonterminatepoinr);

						// Protected checkbox
						protected_AddSiteOrder(application, Protected);

						// GCR OLO Type dropdown
						GCROLOType_AddSiteorder(application, GCRolo);

						// VLAN Text field
						Vlan_AddSiteorder(application, Vlan);

						// VLAN Ether Type dropdown
						Vlanethertype_AddSiteorder(application, Vlanether);

						// Primary VLAN Text Field
						primaryVlan_AddSiteorder(application, primaryVlan);

						// Primary Ether Vlan dropdown
						primaryVlanethertype_AddSiteorder(application, primaryVlanether);

					}

				}
			}
		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");

	}

	public void technologyEPN1G_IVRefrenceAccess_AddSiteOrder(String application, String technology,
			String interfaceSpeed, String devicename, String nonterminatepoinr, String Protected, String GCRolo,
			String Vlan, String Vlanether, String mappingmode, String portBased, String vlanBased)
			throws InterruptedException, IOException, DocumentException {

		// Technology
		if (technology.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Technology dropdown is a mandatory field and no values are provided");
		} else {

			if (interfaceSpeed.equals("1GigE")) {

				if (technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture")
						|| technology.equals("Accedian-1G") || technology.equals("Cyan") || technology.equals("Alu")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

					if (technology.equals("Actelis")) {

						System.out.println("No additional fields displays");
					}

					else if ((technology.equals("Atrica"))) {

						// Non-Termination Point
						nontermination_AddSiteorder(application, nonterminatepoinr);

						// Protected
						protected_AddSiteOrder(application, Protected);

						// Device Name
						devicename_AddSiteOrder(application, devicename);

						// Mapping Mode
						addMappingMode(application, mappingmode);
					}

					else if (technology.equals("Overture")) {
						// Non- termination point checkbox
						nontermination_AddSiteorder(application, nonterminatepoinr);

						// Protected checkbox
						protected_AddSiteOrder(application, Protected);

						// Device Name
						devicename_AddSiteOrder(application, devicename);

						// GCR OLO Type dropdown
						GCROLOType_AddSiteorder(application, GCRolo);

						// VLAN Text field
						Vlan_AddSiteorder(application, Vlan);

						// VLAN Ether Type dropdown
						Vlanethertype_AddSiteorder(application, Vlanether);

					}

					else if (technology.equals("Cyan")) {
						// Non- termination point checkbox
						nontermination_AddSiteorder(application, nonterminatepoinr);

					}

					else if (technology.equals("Accedian-1G")) {

						// Non-Termination Point
						nontermination_AddSiteorder(application, nonterminatepoinr);

						// Protected
						protected_AddSiteOrder(application, Protected);

						// Device Name
						devicename_AddSiteOrder(application, devicename);
					}

					else if (technology.equals("Alu")) {

						// Device Name
						devicename_AddSiteOrder(application, devicename);

						// Mappin Mode
						addMappingMode(application, mappingmode);

						if (mappingmode.equalsIgnoreCase("port Based")) {
							addtextFields_commonMethod(application, "Port Name", "portname_textField", portBased, xml);

						}
						if (mappingmode.equalsIgnoreCase("Vlan Based")) {

							addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased, xml);
						}

					}
				}
			}
		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");

	}

	public void technologyHubANdSpoke10G_AddSiteOrder(String application, String interfaceSpeed, String technology,
			String nonterminatepoinr, String Protected) throws InterruptedException, DocumentException {

		if (technology.equals("Accedian")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[text()='" + technology + "']"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

			// Non- termination point
			if (nonterminatepoinr.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
			} else {
				System.out.println("Non termination point checkbox is not selected as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
			}

			// Protected
			if (Protected.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
			} else {
				System.out.println("Protected checkbox is not selecetd as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
		}
	}

	public void technologyEPN10G_AddSiteOrder(String application, String interfaceSpeed, String technology,
			String nonterminatepoinr, String Protected) throws InterruptedException, DocumentException {

		if (technology.equals("Accedian")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[text()='" + technology + "']"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, technology + " is selected under technology dropdown");

			// Non- termination point
			if (nonterminatepoinr.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point checkbox is selected");
			} else {
				System.out.println("Non termination point checkbox is not selected as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Non-termination point chekbox is not selected");
			}

			// Protected
			if (Protected.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is selected");
			} else {
				System.out.println("Protected checkbox is not selecetd as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Protected checkbox is not selected");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
		}
	}

	public void validatesiteOrderNumber_AddSiteOrder(String application)
			throws InterruptedException, DocumentException {

		// Site Order Number Field
		boolean siteorderNmber = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_siteordernumbertextfield")).isDisplayed();
		sa.assertTrue(siteorderNmber, " 'site order number' field is not displayed");
		if (siteorderNmber) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Site Order Number (Siebel Service ID)' text field is displaying under 'Add Site order' page as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'site order number (Siebel Service ID)' text field is not displaying under 'Add Site order' page");
		}

	}

	public void siteOrderNumber_AddSiteOrder(String application, String siteOrderNumber)
			throws InterruptedException, IOException, DocumentException {

		if (siteOrderNumber.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'Site Order Number' field is a mandatory field and no values are provided");
		} else {
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_siteordernumbertextfield")),
					siteOrderNumber);
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, siteOrderNumber + " is entered under 'Site Order Number' field");
		}

	}

	public void validateIVReference_AddSiteorder(String application) throws InterruptedException, DocumentException {

		boolean IVReference = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")).isDisplayed();
		sa.assertTrue(IVReference, " 'IV reference' dropdown is not displayed");
		if (IVReference) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			List<WebElement> listofIVreference = driver
					.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

			if (listofIVreference.size() >= 1) {
				for (WebElement IVreferencetypes : listofIVreference) {

					Log.info("list of IV References : " + IVreferencetypes.getText());
					System.out.println("list of IV References for AddSite order are: " + IVreferencetypes.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"list of IV References for AddSite order are: " + IVreferencetypes.getText());

				}

			} else {
				System.out.println("no values are available inside 'IV reference' dropdown for Add site order");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"no values are available inside 'IV reference' dropdown for Add site order");
			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Mandatory field 'IV Reference' dropdown is not available under 'Add Site ORder' page");
		}
	}

	public void validateCircuitreference_AddSiteOrder(String application)
			throws InterruptedException, DocumentException {

		boolean circuitReference = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).isDisplayed();
		sa.assertTrue(circuitReference, "Circuit Reference field is not displayed");
		if (circuitReference) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Mandatory field 'Circuit Reference' text field is displaying under 'Add Site order' page as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Mandatory field 'Circuit Reference' text field is not displaying under 'Add Site order' page");
		}
	}

	public void circuitreference_AddSiteorder(String application, String circuitref)
			throws InterruptedException, IOException, DocumentException {

		if (circuitref.equalsIgnoreCase("null")) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'Circuit Reference' field is a mandatory field and no values are provided");
		} else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")),
					circuitref);
			Thread.sleep(3000);

			String actualvalue = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField"))
							.getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'Circuit Reference' field");
		}
	}

	public void validateoffnet_AddSiteOrder(String application) throws InterruptedException, DocumentException {

		// Offnet checkbox

		boolean offnet = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox"))
				.isDisplayed();
		sa.assertTrue(offnet, "Offnet field is not displayed");
		boolean offnetcheckbox = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();
		sa.assertFalse(offnetcheckbox, " Offnet checkbox is selected");

		if (offnet) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"'Offnet' text field is displaying under 'Add Site order' page as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'Offent' text field is not displaying under 'Add Site order' page");
		}

		if (offnetcheckbox) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Offnet' checkbox is selected by default");
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Offnet' checkbox is not selected by default as expected");
		}
	}

	public void validateEPNoffnet_AddSiteOrder(String application) throws InterruptedException, DocumentException {

		// EPN Offnet checkbox
		boolean EPNoffnet = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_EPNoffnetCheckbox")).isDisplayed();
		sa.assertTrue(EPNoffnet, " EPN Offnet checkbox is not displayed");
		boolean EPNoffnetcheckbox = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_EPNoffnetCheckbox")).isSelected();
		sa.assertFalse(EPNoffnetcheckbox, "  EPN Offnet checkbox is selected");

		if (EPNoffnet) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"'EPN Offnet' text field is displaying under 'Add Site order' page as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'EPN Offnet' text field is not displaying under 'Add Site order' page");
		}

		if (EPNoffnetcheckbox) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'EPN Offnet' checkbox is selected by default");
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN Offnet' checkbox is not selected by default as expected");
		}
	}

	public void validateEPNEOSDH_AddSiteOrder(String application) throws InterruptedException, DocumentException {

		// EPN EOSDH checkbox
		boolean EPNEOSDH = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox"))
				.isDisplayed();
		sa.assertTrue(EPNEOSDH, " EPN EOSDH checkbox is not displayed");
		boolean EPNEOSDHcheckbox = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")).isSelected();
		sa.assertFalse(EPNEOSDHcheckbox, "  EPN EOSDH checkbox is selected");

		if (EPNEOSDH) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"'EPN EOSDH' text field is displaying under 'Add Site order' page as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'EPN EOSDH' text field is not displaying under 'Add Site order' page");
		}

		if (EPNEOSDHcheckbox) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'EPN EOSDH' checkbox is selected by default");
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN EOSDH' checkbox is not selected by default");
		}
	}

	public void offnet_AddSiteOrder(String application, String offnetSelection)
			throws InterruptedException, DocumentException {

		boolean offnetselect = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();

		if (offnetSelection.equalsIgnoreCase("yes")) {

			if (offnetselect) {
				System.out.println("Offnet chckbox is selected already while verifying the fields");
			} else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Offnet' checkbox is selected as expected");

				boolean offnetActualSelection = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();
				if (offnetActualSelection) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'offnet' checkbox is selected as expected");
					System.out.println(" 'offnet' checkbox is selected as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'offnet' checkbox is not selected");
					System.out.println(" 'offnet' checkbox is not selected");
				}

			}
		} else if (offnetSelection.equalsIgnoreCase("no")) {

			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Offnet' checkbox is not selected");
		}
	}

	public void EPNoffnet_AddSiteOrder(String application, String EPNoffnetSelection)
			throws InterruptedException, DocumentException {

		if (EPNoffnetSelection.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNoffnetCheckbox")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN Offnet' checkbox is selected as expected");
		} else if (EPNoffnetSelection.equalsIgnoreCase("no")) {

			ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN Offnet' checkbox is not selected as expected");
		}
	}

	public void EPNEOSDH_AddSiteOrder(String application, String EPNEOSDHSelection)
			throws InterruptedException, DocumentException {

		if (EPNEOSDHSelection.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN EOSDH' checkbox is selected as expected");
		} else if (EPNEOSDHSelection.equalsIgnoreCase("no")) {

			ExtentTestManager.getTest().log(LogStatus.PASS, " 'EPN EOSDH' checkbox is not selected as expected");
		}
	}

	public void validatespokeId_AddSiteOrder(String application) throws InterruptedException, DocumentException {
		try {
			boolean spokeIdlabel = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_spokeIdField")).isDisplayed();
			if (spokeIdlabel) {
				boolean spokeIDvalue = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_spokeId")).isDisplayed();
				if (spokeIDvalue) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Spoke Id' value is displaying as '0' by default as expected");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Spoke Id' is not displaying as expected");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Spoke Id' field is not displaying under 'Add Site Order' page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Spoke Id' field is not displaying under 'Add Site Order' page");
		}

	}

	public void OKbutton_AddSiteOrder(String application) throws InterruptedException, DocumentException {

		boolean ok = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
		sa.assertTrue(ok, "OK button is not displayed");
		if (ok) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'OK' button is displaying as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'OK' button is not displaying");
		}
	}

	public void cancelbutton_AddSiteOrder(String application) throws InterruptedException, DocumentException {

		boolean cancel = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cancel"))
				.isDisplayed();
		sa.assertTrue(cancel, "Cancel button is not " + "displayed");
		if (cancel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Cancel' button is displaying as expected");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Cancel' button is not displaying");
		}
	}

	public void Ivrefrence_AddSiteOrder(String application, String iVReference)
			throws InterruptedException, DocumentException {

		if (iVReference.equalsIgnoreCase("null")) {

			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'IV Reference' dropdown field is a mandatory field and no values has been passed");
		} else {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[text()='" + iVReference + "']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, iVReference + " is selected under 'IV Reference' dropdown");
		}

	}

	public void GCROLOType_AddSiteorder(String application, String gcrOlo)
			throws InterruptedException, DocumentException {
		try {
			if (gcrOlo.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"  No values has been selected under 'GCR OLO Type' dropdown field");
			} else {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='" + gcrOlo + "']"));

				String actualvalue = getwebelement("//div[label[text()='GCR OLO Type']]//span").getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is selected under 'GCR OLO Type' dropdown");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'GCR OLO Type' dropdown is not available under 'Add Site Order' page");
			System.out.println(" 'GCR OLO Type' dropdown is not available under 'Add Site Order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to select value under 'GCR olo Dropdown'");
			System.out.println(" Not able to select value under 'GCR olo Dropdown'");
		}
	}

	public void Vlanethertype_AddSiteorder(String application, String VlanEthertype)
			throws InterruptedException, DocumentException {
		try {
			if (VlanEthertype.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" No values has been selected under 'VLAN Ether Type' dropdown field");
			} else {
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[label[text()='VLAN Ether Type']]//div[text()='" + VlanEthertype + "']"));

				String actualvalue = getwebelement("//div[label[text()='VLAN Ether Type']]//span").getText();
				ExtentTestManager.getTest().log(LogStatus.PASS,
						actualvalue + " is selected under 'VLAN Ether Type' dropdown");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'VLAN Ether Type' dropdown is not displaying under 'Add Site Oder' page");
			System.out.println(" 'VLAN Ether Type' dropdown is not displaying under 'Add Site Oder' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to selected value under 'VLAN Ether Type' dropdown");
			System.out.println(" Not able to selected value under 'VLAN Ether Type' dropdown");
		}

	}

	public void primaryVlanethertype_AddSiteorder(String application, String primaryVlanEthertype)
			throws InterruptedException, DocumentException {

		try {
			if (primaryVlanEthertype.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" No values has been selected under 'Primary VLAN Ether Type' dropdown field");
			} else {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")));
				Thread.sleep(3000);
				Clickon(getwebelement(
						"//div[label[text()='Primary VLAN Ether Type']]//div[text()='" + primaryVlanEthertype + "']"));

				String actualvalue = getwebelement("//div[label[text()='Primary VLAN Ether Type']]//span").getText();
				ExtentTestManager.getTest().log(LogStatus.PASS,
						actualvalue + " is selected under 'Primary VLAN Ether Type' dropdown");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Primary VLAN Ether type' dropdown is not displaying in 'Add Site Order' page");
			System.out.println(" 'Primary VLA Ether type' dropdown is not displaying in 'Add Site Order' page");

		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to select value under 'Primary VLAN Ether type' dropdown");
			System.out.println(" Not able to select value under 'Primary VLAN Ether type' dropdown");
		}

	}

	public void Vlan_AddSiteorder(String application, String vlan)
			throws InterruptedException, IOException, DocumentException {
		try {
			if (vlan.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No values has been passed for 'VLAN' text field");
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")), vlan);
				Thread.sleep(3000);

				String actualvalue = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN"))
						.getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'VLAN' text field");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'VLAN' text field is not displying under 'Add Site Order'");
			System.out.println(" 'VLAN' text field is not displying under 'Add Site Order'");

		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'VLAN' text field");
			System.out.println(" Not able to enter value under 'VLAN' text field");
		}
	}

	public void primaryVlan_AddSiteorder(String application, String primaryvlan)
			throws InterruptedException, IOException, DocumentException {
		try {
			if (primaryvlan.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No values has been passed for 'Primary VLAN' text field");
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")),
						primaryvlan);
				Thread.sleep(3000);

				String actualvalue = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN"))
								.getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'Primary VLAN' text field");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Primary VLAN' text field is not displaying under 'Add Site Order' page");
			System.out.println(" 'Primary VLAN' text field is not displaying under 'Add Site Order' page");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Primary VLAN type' text field");
			System.out.println(" Not able to enter value in 'Primary VLAN type' text field");
		}
	}

	public void addSiteOrderValues_HubAndSPoke(String application, String interfaceSpeed, String country, String city,
			String CSR_Name, String site, String performReport, String ProactiveMonitor, String smartmonitor,
			String technology, String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider,
			String sitevalue, String remark, String xngcityname, String xngcitycode, String devicename,
			String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection, String siteOrderNumber, String circuitref,
			String offnetSelection, String IVReference, String GCRolo, String Vlan, String Vlanether,
			String primaryVlan, String primaryVlanether) throws InterruptedException, DocumentException, IOException {

		siteOrderNumber_AddSiteOrder(application, siteOrderNumber);

		Countyr_AddSiteOrder(application, country);

		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode,
				sitevalue, CSR_Name, existingsiteselection, newsiteselection);

//		Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

		performancereporting_AddSiteOrder(application, performReport);

		proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

		smartsMonitoring_AddSiteOrder(application, smartmonitor);

		scrolltoend();
		Thread.sleep(3000);

		circuitreference_AddSiteorder(application, circuitref);

		SiteAlias_AddSiteOrder(application, siteallias);

		offnet_AddSiteOrder(application, offnetSelection);

		remark_AddSiteOrder(application, remark);

		if ((interfaceSpeed.equals("1GigE")) & (IVReference.equals("Primary"))) {

			Ivrefrence_AddSiteOrder(application, IVReference);
			technologyHubAndSpoke1G_IVRefrencePrimary_AddSiteOrder(application, technology, interfaceSpeed, devicename,
					nonterminatepoinr, Protected);

		}

		if ((interfaceSpeed.equals("1GigE")) & (IVReference.equals("Access"))) {

			Ivrefrence_AddSiteOrder(application, IVReference);
			technologyHubAndSpoke1G_IVRefrenceAccess_AddSiteOrder(application, technology, interfaceSpeed, devicename,
					nonterminatepoinr, Protected, GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether);

		}

		if (interfaceSpeed.equals("10GigE")) {
			Ivrefrence_AddSiteOrder(application, IVReference);
			technologyHubANdSpoke10G_AddSiteOrder(application, interfaceSpeed, technology, nonterminatepoinr,
					Protected);

		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");

		scrolltoend();
		Thread.sleep(3000);
		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void addSiteOrderValues_HubAndSPoke_OffnetSelected(String application, String interfaceSpeed, String country,
			String city, String CSR_Name, String site, String performReport, String ProactiveMonitor,
			String smartmonitor, String technology, String siteallias, String VLANid, String DCAenabledsite,
			String cloudserviceprovider, String sitevalue, String remark, String xngcityname, String xngcitycode,
			String devicename, String nonterminatepoinr, String Protected, String newcityselection,
			String existingcityselection, String existingsiteselection, String newsiteselection, String siteOrderNumber,
			String circuitref, String offnetSelection, String IVReference, String GCRolo, String Vlan, String Vlanether,
			String primaryVlan, String primaryVlanether) throws InterruptedException, DocumentException, IOException {

		siteOrderNumber_AddSiteOrder(application, siteOrderNumber);

		Countyr_AddSiteOrder(application, country);

		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode,
				sitevalue, CSR_Name, existingsiteselection, newsiteselection);

// 		Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

		performancereporting_AddSiteOrder(application, performReport);

		proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

		smartsMonitoring_AddSiteOrder(application, smartmonitor);

		scrolltoend();
		Thread.sleep(3000);

		circuitreference_AddSiteorder(application, circuitref);

		SiteAlias_AddSiteOrder(application, siteallias);

		offnet_AddSiteOrder(application, offnetSelection);

		remark_AddSiteOrder(application, remark);

		if ((interfaceSpeed.equals("1GigE")) & (IVReference.equals("Primary"))) {

			Ivrefrence_AddSiteOrder(application, IVReference);
			technologyHubAndSpoke1G_IVRefrencePrimary_OffnetSelected_AddSiteOrder(application, technology,
					interfaceSpeed, devicename, nonterminatepoinr, Protected);

		}

		if ((interfaceSpeed.equals("1GigE")) & (IVReference.equals("Access"))) {

			Ivrefrence_AddSiteOrder(application, IVReference);
			technologyHubAndSpoke1G_IVRefrenceAccess_offnetSelected_AddSiteOrder(application, technology,
					interfaceSpeed, devicename, nonterminatepoinr, Protected, GCRolo, Vlan, Vlanether, primaryVlan,
					primaryVlanether);

		}

		if (interfaceSpeed.equals("10GigE")) {

			ExtentTestManager.getTest().log(LogStatus.INFO,
					" If 'Offnet' is selected for '10GigE', 'No value displays under 'Technology' dropdown");

		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");

		scrolltoend();
		Thread.sleep(3000);
		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void addSiteOrderValues_EPN(String application, String interfaceSpeed, String country, String city,
			String CSR_Name, String site, String performReport, String ProactiveMonitor, String smartmonitor,
			String technology, String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider,
			String sitevalue, String remark, String xngcityname, String xngcitycode, String devicename,
			String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection, String siteOrderNumber, String circuitref,
			String offnetSelection, String IVReference, String GCRolo, String Vlan, String Vlanether,
			String EPNoffnetSelection, String EPNEOSDHSelection, String mappingmode, String portBased, String vlanBased)
			throws InterruptedException, DocumentException, IOException {

		siteOrderNumber_AddSiteOrder(application, siteOrderNumber);

		Countyr_AddSiteOrder(application, country);

		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode,
				sitevalue, CSR_Name, existingsiteselection, newsiteselection);

// 		Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

		performancereporting_AddSiteOrder(application, performReport);

		proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

		smartsMonitoring_AddSiteOrder(application, smartmonitor);

		scrolltoend();
		Thread.sleep(3000);

		circuitreference_AddSiteorder(application, circuitref);

		SiteAlias_AddSiteOrder(application, siteallias);

		EPNoffnet_AddSiteOrder(application, EPNoffnetSelection);

		if (interfaceSpeed.equals("1GigE")) {
			EPNEOSDH_AddSiteOrder(application, EPNEOSDHSelection);
		}

		remark_AddSiteOrder(application, remark);

		if ((interfaceSpeed.equals("1GigE")) & (IVReference.equals("Primary"))) {

			Ivrefrence_AddSiteOrder(application, IVReference);
			technologyEPN1G_IVRefrencePrimary_AddSiteOrder(application, technology, interfaceSpeed, devicename,
					nonterminatepoinr, Protected);

		}

		if ((interfaceSpeed.equals("1GigE")) & (IVReference.equals("Access"))) {

			Ivrefrence_AddSiteOrder(application, IVReference);
			technologyEPN1G_IVRefrenceAccess_AddSiteOrder(application, technology, interfaceSpeed, devicename,
					nonterminatepoinr, Protected, GCRolo, Vlan, Vlanether, mappingmode, portBased, vlanBased);

		}

		if (interfaceSpeed.equals("10GigE")) {
			Ivrefrence_AddSiteOrder(application, IVReference);
			technologyEPN10G_AddSiteOrder(application, interfaceSpeed, technology, nonterminatepoinr, Protected);

		}

		ExtentTestManager.getTest().log(LogStatus.PASS, "Data has been entered for add site order");

		scrolltoend();
		Thread.sleep(3000);
		click_commonMethod(application, "OK", "okbutton", xml);

	}

	public void device_nameFieldWarningMessage(String application) throws InterruptedException, DocumentException {
		boolean name = false;
		// Name field Error Message
		try {
			name = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_nameerrmsg"))
					.isDisplayed();
			Thread.sleep(3000);
			sa.assertTrue(name, "name field warning message is not displayed ");
			if (name) {
				String nameErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_nameerrmsg")).getText();
				System.out.println("Name field warning  message displayed as : " + nameErrMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step :  validation message for 'Name' text field displayed as : " + nameErrMsg);
				Log.info(" Name field warning message displayed as : " + nameErrMsg);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println(" Name field warning message is not dipslaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " name field warning message is not displaying");
		} catch (Exception ed) {
			ed.printStackTrace();
		}
	}

	public void device_vendorModelWarningMessage(String application) throws InterruptedException, DocumentException {
		boolean vendorErr = false;
		// Vendor/Model Error Message
		try {
			vendorErr = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vendorErrmsg"))
					.isDisplayed();
			sa.assertTrue(vendorErr, "Vendor/Model warning message is not displayed ");
			if (vendorErr) {
				String vendorErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_vendorErrmsg")).getText();
				System.out.println("Vendor/Model  message displayed as : " + vendorErrMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step :  validation message for Vendor/Model field displayed as : " + vendorErrMsg);
				Log.info("Vendor/Model warning message displayed as : " + vendorErrMsg);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Vendor/Mdel warning message is not dipslaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Vendor/Model warning message is not displaying");
		} catch (Exception ed) {
			ed.printStackTrace();
		}
	}

	public void device_managementAddressWarningMessage(String application)
			throws InterruptedException, DocumentException {

		boolean mangadrsErr = false;
		try {
			mangadrsErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg"))
							.isDisplayed();
			sa.assertTrue(mangadrsErr, "Management Addres warning message is not displayed ");
			if (mangadrsErr) {
				String mngadresErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg"))
								.getText();
				System.out.println("Management Addres  message displayed as : " + mngadresErrMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step :  validation message for Management Addres field displayed as : " + mngadresErrMsg);
				Log.info("Management Addres warning message displayed as : " + mngadresErrMsg);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("management Address warning message is not found");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Management Address warning message is not displaying");
		} catch (Exception ed) {
			ed.printStackTrace();
		}
	}

	public void device_powerAlarmWarningMessage(String application) throws InterruptedException, DocumentException {
		boolean pwralrmErr = false;
		try {
			pwralrmErr = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg"))
					.isDisplayed();
			sa.assertTrue(pwralrmErr, "Power Alarm warning message is not displayed ");
			if (pwralrmErr) {
				String pwralarmErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).getText();
				System.out.println("Power Alarm  message displayed as : " + pwralarmErrMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step :  validation message for Power Alarm field displayed as : " + pwralarmErrMsg);
				Log.info("Power Alarm warning message displayed as : " + pwralarmErrMsg);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Power Alarm warning message is not dipslaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Power Alarm warning message is not displaying");
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	public void device_mediaSelectionWarningMessage(String application) throws InterruptedException, DocumentException {
		boolean mediaErr = false;
		try {
			mediaErr = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg"))
					.isDisplayed();
			sa.assertTrue(mediaErr, "Media Selection warning message is not displayed ");
			if (mediaErr) {
				String mediaselectionErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).getText();
				System.out.println("Media Selection  message displayed as : " + mediaselectionErrMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step :  validation message for Media Selection field displayed as : " + mediaselectionErrMsg);
				Log.info("Media Selection warning message displayed as : " + mediaselectionErrMsg);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Media selection waning message is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Media Selection warning message is not displaying");
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	public void device_macAddressWarningMessage(String application) throws InterruptedException, DocumentException {
		boolean macErr = false;
		try {
			macErr = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg"))
					.isDisplayed();
			sa.assertTrue(macErr, "MAC Address warning message is not displayed ");
			if (macErr) {
				String macadresErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).getText();
				System.out.println("MAC Address  message displayed as : " + macadresErrMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step :  validation message for MAC Address field displayed as : " + macadresErrMsg);
				Log.info("MAC Address warning message displayed as : " + macadresErrMsg);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'MAC Address' warning message is not displaying under 'Add cpe device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Mac Adress warning message is not dipslaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " MAC Address warning message is not displaying");
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	public void device_serialNumberWarningMessage(String application) throws InterruptedException, DocumentException {
		// Serial Number Error Message
		boolean serialNumberErr = false;

		try {
			serialNumberErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_serialNumberErrmsg")).isDisplayed();
			sa.assertTrue(serialNumberErr, "Serial Number warning message is not displayed ");
			if (serialNumberErr) {
				System.out.println(" 'Serial number; warning message is dipslaying as expected");
				String serialnumberErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_serialNumberErrmsg")).getText();
				System.out.println("Serial Number  message displayed as : " + serialnumberErrMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step :  validation message for Serial Number field displayed as : " + serialnumberErrMsg);
				Log.info("Serial Number warning message displayed as : " + serialnumberErrMsg);
			} else {
				System.out.println("Serial Number warning message is not dipslaying");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number' validation mesage is not displaying");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Serial Number Warning message is not diplsying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number' warning message is not displaying");
		}
	}

	public void device_hexaSerialNumberWarningMessage(String application)
			throws InterruptedException, DocumentException {
		// Serial Number Error Message
		boolean HexaserialNumberErr = false;

		try {
			HexaserialNumberErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaSerialNumnerErrmsg")).isDisplayed();
			sa.assertTrue(HexaserialNumberErr, "Hexa Serial Number warning message is not displayed ");
			if (HexaserialNumberErr) {
				System.out.println(" 'Hexa Serial number' warning message is dipslaying as expected");
				String hexaserialnumberErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaSerialNumnerErrmsg")).getText();
				System.out.println("Hexa Serial Number  message displayed as : " + hexaserialnumberErrMsg);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step :  validation message for Hexa Serial Number field displayed as : "
								+ hexaserialnumberErrMsg);
				Log.info("Hexa Serial Number warning message displayed as : " + hexaserialnumberErrMsg);
			} else {
				System.out.println("Hexa Serial Number warning message is not dipslaying");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Hexa Serial Number' validation mesage is not displaying");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Serial Number Warning message is not diplsying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Serial Number' warning message is not displaying");
		}
	}

	public void device_countrywarningMessage(String application) throws InterruptedException, DocumentException {

		// Country Error Message
		boolean countryErr = false;
		countryErr = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_countryErrmsg"))
				.isDisplayed();
		sa.assertTrue(countryErr, "Country warning message is not displayed ");

		if (countryErr) {
			System.out.println("country warning message is displaying as expected");
			String countryErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_countryErrmsg")).getText();
			System.out.println("Country  message displayed as : " + countryErrMsg);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"Step :  validation message for Country field displayed as : " + countryErrMsg);
			Log.info("Country warning message displayed as : " + countryErrMsg);
		} else {
			System.out.println("Country warning message is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Validation message for Country dropdown is not displaying");
		}
	}

	public void device_nameField(String application, String cpename, String expectedDeviceNameFieldAutopopulatedValue) {
		boolean name = false;
		try {
			name = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).isDisplayed();
			sa.assertTrue(name, "Name text field is not available under create device for Equipment");

			if (name) {
				if (cpename.equalsIgnoreCase("null")) {
					System.out.println("No values has been assed for 'Name' text field");
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values has been passed for Mandatory 'Name' field under 'Add CPE Device' page");
				}

				else {
					String deviceNameActualPopulatedvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).getAttribute("value");
					sa.assertEquals(deviceNameActualPopulatedvalue, expectedDeviceNameFieldAutopopulatedValue,
							"Device Name field Auto Populated value is not displaying as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" Under 'Name' text field, value displaying by default is: "
									+ deviceNameActualPopulatedvalue);
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).clear();
					Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")),
							cpename);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							cpename + " is the value passed for Mandatory 'Cpe name' text field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Name text field is not displaying under 'Add CPE Device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Name' text field is not available");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Name' field");
		}

	}

	public void device_vendorModel(String application, String[] Vender, String vender)
			throws InterruptedException, DocumentException {
		boolean vendor = false;
		try {
			vendor = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")).isDisplayed();
			sa.assertTrue(vendor, "Vender/Model dropdown is not available");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));

			try {
				List<WebElement> listofvender = driver
						.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

				if (listofvender.size() > 0) {

					for (WebElement vendertypes : listofvender) {

						boolean match = false;
						for (int i = 0; i < Vender.length; i++) {
							if (vendertypes.getText().equals(Vender[i])) {
								match = true;
								Log.info("list of vendor under add devices are : " + vendertypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS,
										"The list of vender/Model under Add device are: " + vendertypes.getText());
								System.out.println("list of vendor under add devices are : " + vendertypes.getText());

							}
						}
						sa.assertTrue(match);
					}

				} else {
					System.out.println("dropdown value inside Vender/Model is empty");
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values available inside Vender/Model dropdown for adding devices");
				}

			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Failure at vendor dropdown");
			}

			// Entering value inside Vendor/Model dropdown
			try {
				if (vender.equalsIgnoreCase("null")) {

					System.out.println(
							"No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device");
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device");

				} else {
					Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='" + vender + "']"));
					ExtentTestManager.getTest().log(LogStatus.PASS,
							vender + " is the value passed for Mandatory 'Vendor/Model' dropdown for adding device");
					System.out.println(
							vender + " is the value passed for Mandatory 'Vendor/Model' dropdown for adding device");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"FAilure at Vender/model dropdown. It does not have the value provided as input"
								+ " Value provided is: " + vender);
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("vendor/Model dropdown is not dipslaying under 'Add CPE device' page");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Vendor/Model' dropdown is not displayind in 'Add CPE Device' page");
		}
	}

	public void device_snmPro(String application, String snmproValueToBeChanged) {
		boolean sNmpro = false;
		try {

			sNmpro = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).isDisplayed();

			if (sNmpro) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" ' Snmpro' field is displaying in 'Add CPE Device' page as expected");
				System.out.println("Smpro text field is displaying as expected");

				boolean actualValue_snmpro = getwebelement(
						xml.getlocator("//locators/" + application + "/CPEdevice_snmpro_autoPopulatedValue"))
								.isDisplayed();
				if (actualValue_snmpro) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Snmpro' field value is displaying as expected." + " It is displaying as: "
									+ getwebelement(
											xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro"))
													.getAttribute("value"));

				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Snmpro' value is not displaying as expected." + " It is displaying as: "
									+ getwebelement(
											xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro"))
													.getAttribute("value"));
				}

				if (snmproValueToBeChanged.equalsIgnoreCase("null")) {
					System.out.println("No changes has been made to 'Snmpro' field");
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Snmpro' field value is not changed");
					System.out.println(" 'Snmpro' field is displaying as: "
							+ getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro"))
									.getAttribute("value"));
				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")),
							snmproValueToBeChanged);
					Thread.sleep(3000);

					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Snmro' field value has been changes " + "And it is displaying as: "
									+ getwebelement(
											xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro"))
													.getAttribute("value"));
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snmpro' field is not available in 'Add CPE Device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snm pro' field is not available ");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Snm pro' field");
		}

	}

	public void device_mepID(String application, String Mepid) {

		String mepValue = "null";
		boolean mepid = false;
		try {
			mepid = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).isDisplayed();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" ' Snmpro' field is displaying in 'Add CPE Device' page as expected");

			sa.assertTrue(mepid, "Mepid field under 'Add CPE device' page is not available");

			if (mepid) {

				System.out.println("MEP Id  text field is displaying as expected");

				mepValue = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid"))
						.getAttribute("value");
				if (mepValue.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" No values are displaying under 'MEP ID' field. It should be auto populated by default");
					System.out.println(
							" No values are displaying under 'MEP ID' field. It should be auto populated by default");
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" MEP ID field is auto populated and it is displaying as : " + mepValue);
					System.out.println(" MEP ID field is auto populated and it is displaying as : " + mepValue);
				}

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mep Id' field is not available");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Mep Id' field");
		}
	}

	public void device_powerAlarm(String application, String[] powerAlarm, String poweralarm)
			throws InterruptedException, DocumentException {
		boolean powralrm = false;
		try {
			powralrm = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm"))
					.isDisplayed();
			sa.assertTrue(powralrm, "The poweralarm dropdown under add device is not available");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
			Thread.sleep(3000);

			// Check values inside Power Alarm dropdown
			try {
				List<WebElement> listofalarm = driver
						.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));

				if (listofalarm.size() > 0) {
					for (WebElement alarmtypes : listofalarm) {

						boolean match = false;
						for (int i = 0; i < powerAlarm.length; i++) {
							if (alarmtypes.getText().equals(powerAlarm[i])) {
								match = true;
								Log.info("list of power alarm under add devices are : " + alarmtypes.getText());
								ExtentTestManager.getTest().log(LogStatus.PASS,
										"The list of powerAlarm under Add device are: " + alarmtypes.getText());
							}
						}
						sa.assertTrue(match);
					}

				} else {
					System.out.println("dropdown value inside Vender/Model is empty");
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values available inside power alarm dropdown for adding devices");
				}
			} catch (Exception e) {

				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "value mismatch for poweralarm dropdown");

			}

			// Select value inside power Alarm dropdown
			if (poweralarm.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"No values has been passed for Mandatory field 'Powre Alarm' for adding device");
				System.out.println("No values has been passed for Power Alarm dropdown mandatory Field");
			} else {
				Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='" + poweralarm + "']"));
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, poweralarm
						+ " is the value passed for Mandatory 'Power Alarm' dropdown field for adding device");
				System.out.println(poweralarm
						+ " is the value passed for Mandatory 'Power Alarm' dropdown field for adding device");
			}
		} catch (NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Power Alarm' dropdown is not available in 'Add CPE Device' page");
		}

	}

	public void device_mediaSelection(String application, String Mediaselection[], String mediaSelection)
			throws InterruptedException, DocumentException {
		
		addDropdownValues_commonMethod(application, "Media Selection", "AddCPEdevice_mediaselection", mediaSelection, xml);
		
	}

	public void device_linklostForwarding(String application, String linkLostForwarding, String state)
			throws InterruptedException, DocumentException {

		boolean linklostenable = false;
		boolean linklost = false;
		try {
			linklost = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding"))
					.isDisplayed();
			sa.assertTrue(linklost, "Link lost Forwarding checkbox under add device is not available");
			if (linklost) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Link lost Forwarding' checkbox is displaying under 'Add CPE device' page as expected");
				// Find whether it enabled or disabled
				linklostenable = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isEnabled();
				if (state.equalsIgnoreCase("disabled")) {
					if (linklostenable) {
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								" 'Link lost Forwarding' checkbox is enabled under 'Add CPE device' page");
					} else {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								" 'Link lost Forwarding' checkbox is disabled under 'Add CPE device' page");
					}
				} else if (state.equalsIgnoreCase("enabled")) {
					if (linklostenable) {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								" 'Link Lost Forwarding' checkbox is enabled under 'Add CPE device' page");

						// select the checkbox as per input
						boolean linklostSelection = getwebelement(
								xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding"))
										.isSelected();
						if (linklostSelection) {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									" 'link lostforwarding' is selected by default as expected");

							// click on link lost checkbox
							if (linkLostForwarding.equalsIgnoreCase("Yes")) {

								ExtentTestManager.getTest().log(LogStatus.PASS,
										" No changes made for 'Link lost Forwarding' checkbox");
							} else {
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + application + "/AddCPEdevice_linklostforowarding")));
								Thread.sleep(3000);
								ExtentTestManager.getTest().log(LogStatus.PASS,
										" Link Lost Forwarding is unselected as expected");
								System.out.println(" Link Lost Forwarding is unselected as expected");
							}
						} else {
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									" 'link lostforwarding' is not selected by default");
							System.out.println(" 'link lostforwarding' is not selected by default");
						}

					} else {
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								" 'Link lost Forwarding' checkbox is disabled under 'Add CPE device' page");
					}

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Link Lost Forwarding' checkbox is not dipslaying under 'Add CPE device' page");
				System.out.println(" 'Link Lost Forwarding' checkbox is not displaying under 'Add CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Link LOst Forwarding checkbox is not displaying in 'Add CPE Device' page");
		}
	}

	public void device_managementAddress(String application, String existingmanagementAddress,
			String newmanagementAddress, String managementAddress) throws InterruptedException, DocumentException {
		boolean managementaddresdropdown = false;
		boolean manageAddresstextField = false;
		String manageAddresspopulatedValue = "null";

		if ((existingmanagementAddress.equalsIgnoreCase("Yes")) & (newmanagementAddress.equalsIgnoreCase("No"))) {
			try {
				managementaddresdropdown = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown"))
								.isDisplayed();
				sa.assertTrue(managementaddresdropdown,
						"Management Address dropdown under add device is not available");

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_getSubnetbutton")));
				Thread.sleep(5000);
				manageAddresspopulatedValue = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown"))
								.getAttribute("value");
				if (manageAddresspopulatedValue.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" No values gets populates in 'Manage Address' dropdown, on clicking 'get Subnets' button");
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" Values displaying under 'Manage Addres' dropodwn is : " + manageAddresspopulatedValue);
				}
				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")));
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Management Address dropdown is not available in 'Add CPE Device' page");
			}

		}

		else if ((existingmanagementAddress.equalsIgnoreCase("No")) & (newmanagementAddress.equalsIgnoreCase("Yes"))) {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_selectSubnettogglebutton")));
			Thread.sleep(3000);
			try {
				manageAddresstextField = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).isDisplayed();
				sa.assertTrue(manageAddresstextField,
						"Manage Address text Field is not displaying in 'Add CPE Device' page");
				if (manageAddresstextField) {
					if (managementAddress.equalsIgnoreCase("null")) {
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								"No values has been passed for Mandatory field 'Manage Address' for adding device");
					} else {

						SendKeys(
								getwebelement(
										xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
								managementAddress);
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS, managementAddress
								+ " is the value passed for Mandatory 'Management Address' field in 'Add CPE Device' page");
					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Management Address' text field is not dipslaying under 'Add CPE device' page");
				}

			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Manage Address' text field is not available in 'Add CPE Device' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'Manage Address' field");
			}
		}
	}

	public void device_MAcaddress(String application, String macAdressInput) {

		boolean macAdres = false;
		try {
			macAdres = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress"))
					.isDisplayed();
			sa.assertTrue(macAdres, "Mepid field under 'Add CPE device' page is not available");

			if (macAdres) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" ' MAC Address' field is displaying in 'Add CPE Device' page as expected");
				System.out.println(" 'MAC Address'  text field is displaying as expected");

				if (macAdressInput.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"No values has been passed for 'MAC Address' text field for adding device");
					System.out.println("No values has been passed for 'MAC Address' mandaotyr field");

				} else {

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")),
							macAdressInput);
					Thread.sleep(3000);

					ExtentTestManager.getTest().log(LogStatus.PASS,
							macAdressInput + " is entered under 'MAc Address' text field");
					System.out.println(macAdressInput + " is entered under 'MAc Address' text field");

				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'MAC Address' text field is not available");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside the 'MAC Address' text field");
		}
	}

	public void device_okbutton(String application) throws InterruptedException, DocumentException {

		// OK button
		boolean Ok = false;
		try {
			Ok = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")).isDisplayed();
			sa.assertTrue(Ok, "OK button under add device is not available");

			if (Ok) {
				System.out.println(" 'OK' button is displaying under 'Add CPE deivce' page as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'OK' button is displaying under 'Add CPE deivce' page as expected");
			} else {
				System.out.println(" 'OK' button is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'OK' button is not displaying under 'Add CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " OK button is not available in 'Add CPE Device' page");
		}

	}

	public void device_cancelButton(String application) throws InterruptedException, DocumentException {

		// Cancel button
		boolean cancel = false;
		try {
			cancel = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_cancelbutton"))
					.isDisplayed();
			sa.assertTrue(cancel, "cancel button under add device is not available");

			if (cancel) {
				System.out.println(" 'Cancel' button is displaying under 'Add CPE deivce' page as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Cancel' button is displaying under 'Add CPE deivce' page as expected");
			} else {
				System.out.println(" 'Cancel' button is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Cancel' button is not displaying under 'Add CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Cancel button is not available in 'Add CPE Device' page");
		}
	}

	public void device_serialNumber(String application, String serialNumber)
			throws InterruptedException, IOException, DocumentException {

		// Serial Number
		boolean serialNmber = false;
		try {
			serialNmber = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber"))
					.isDisplayed();
			sa.assertTrue(serialNmber, "Serial Number is not available in 'Add CPE Device' page");
			if (serialNmber) {
				if (serialNumber.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Serial Number' is a mandatory field. no values has been passed as an input");
				} else {

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
							serialNumber);
					Thread.sleep(3000);
					String SNactualValue = getwebelement(
							xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							SNactualValue + " is the value entered in 'Serial number' field in 'Add CPE Device' page");
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println(" 'Serial Number' text field is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Serial Number' text field is not dipslaying under 'Add CPE device' page");
		} catch (Exception er) {
			er.printStackTrace();
			System.out.println("not able to enter value under 'Serial number' textfield");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value under 'Serial Number' text field");
		}
	}

	public void device_country(String application, String country) throws InterruptedException, DocumentException {

		boolean countryDrpodown = false;
		try {
			// Country dropdown

			countryDrpodown = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_countrydiv"))
							.isDisplayed();
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println(" 'Country' dropown is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Country' dropown is not displaying");
		}

		if (countryDrpodown) {
			System.out.println(" 'Country' dropodwn is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Country' dropodwn is displaying as expected");

			// verify dropdown values
			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_countrydiv")));
			Thread.sleep(5000);
			System.out.println("Clicked on Country dropdown");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Clicked on Country dropdown");
			Log.info("Clicked on Country dropdown");

			List<WebElement> cntrylist = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement countrylist : cntrylist) {

				System.out.println("Available Country name is : " + countrylist.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Available Country name is : " + countrylist.getText().toString());
				Log.info("Available Country name is :" + countrylist.getText().toString());
			}

			// Select value inside country dropdown
			try {
				if (country.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Country' dropdown is a mandatory field and no values has been provided as input");

				} else {
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_countrydiv")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[label[text()='Country']]//div[text()='" + country + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							country + " is the value passed for 'Country' field for adding device");
				}

			} catch (NoSuchElementException e) {
				e.printStackTrace();
				System.out.println(country + " is not present inside 'Country' dropdown");
				ExtentTestManager.getTest().log(LogStatus.FAIL, country + " is not present inside 'Country' dropdown");
			} catch (Exception er) {
				er.printStackTrace();
				System.out.println("Not able to select value inside country dropdown");

			}
		} else {
			System.out.println("Coutry dropodwn is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Country' dropdown is not displaying");
		}
	}

	public void device_verifycity(String application) throws InterruptedException, DocumentException {

		// City dropdown
		boolean citydropdown = false;
		citydropdown = getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip__citydiv")).isDisplayed();

		if (citydropdown) {
			System.out.println(" 'City' dropdown is displaying as expected");
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'City' dropdown is displaying as expected");

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip__citydiv")));
			Thread.sleep(5000);
			System.out.println("Clicked on City dropdown");
			String actualvalue = getwebelement("(//div[label[text()='Country']]//span)[2]").getText();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"For the selected country '" + actualvalue + "' list of city populated are:  ");
			Log.info("Clicked on City dropdown");

			List<WebElement> citylist = driver.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
			for (WebElement ctylist : citylist) {

				System.out.println("Available City name is : " + ctylist.getText().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Step : Available City name is : " + ctylist.getText().toString());
				Log.info("Available City name is :" + ctylist.getText().toString());
			}
		} else {
			System.out.println(" 'City' dropdown is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'City' dropdown is not displaying under 'Add CPE device' page");
		}

		// Site dropdown
		boolean siteDropdown = false;
		try {
			siteDropdown = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitediv")).isDisplayed();
			if (siteDropdown) {
				System.out.println(" 'Site' dropdown is displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Site' dropodwn is dipslaying as expected");
			} else {
				System.out.println(" 'Site' dropdown is not displaying");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Site' dropodwn is not dipslaying");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'site' dropdown field is not displaying");
			System.out.println(" 'Site' dropdown field is not displaying");
		}

		// Premise dropdown
		boolean premiseDropdown = false;
		try {
			premiseDropdown = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisediv"))
							.isDisplayed();

			if (premiseDropdown) {
				System.out.println(" 'Premise' dropdown is displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Premise' dropdown is displaying as expected");
			} else {
				System.out.println(" 'Premise' dropdown is not displaying");
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Premise' dropdown is not displaying");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Premise' dropdown field is not displaying");
			System.out.println(" 'Premise' dropdown field is not displaying");
		}

		// Select City toggle button
		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citytogglebutton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "cliked on 'Select City' toggle button");

		// City name
		boolean cityname = false;
		try {
			cityname = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield"))
							.isDisplayed();
			sa.assertTrue(cityname, " 'City name' field is not getting displyed");
			if (cityname) {
				System.out.println(" 'City name' text field is displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'City name' text field is displaying under 'Add CPE device' page");
			} else {
				System.out.println(" 'City name' text field is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'City name' text field is not displaying under 'Add CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'City name' text field is not displaying");
			System.out.println(" 'City name' text field is not displaying");
		}

		// City Code
		boolean citycode = false;
		try {
			citycode = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield"))
							.isDisplayed();
			sa.assertTrue(citycode, " 'City Code' field is not getting displyed");
			if (citycode) {
				System.out.println(" 'City Code' text field is displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'City Code' text field is displaying under 'Add CPE device' page");
			} else {
				System.out.println(" 'City Code' text field is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'City Code' text field is not displaying under 'Add CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'City Code' text field is not displaying");
			System.out.println(" 'City Code' text field is not displaying");
		}

		// Site name
		boolean sitename = false;
		try {
			sitename = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield"))
							.isDisplayed();
			sa.assertTrue(sitename, " 'Site name' field is not getting displyed");
			if (sitename) {
				System.out.println(" 'Site Name' text field is displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Site Name' text field is displaying under 'Add CPE device' page");
			} else {
				System.out.println(" 'Site Name' text field is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Site Name' text field is not displaying under 'Add CPE device' page");
			}
		} catch (NoSuchElementException ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Site Name' text field is not displaying");
			System.out.println(" 'Site Name' text field is not displaying");
		}

		// Site Code
		boolean sitecode = false;
		try {
			sitecode = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield"))
							.isDisplayed();
			sa.assertTrue(sitecode, " 'Site Code' field is not getting displyed");
			if (sitecode) {
				System.out.println(" 'Site Code' text field is displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Site Code' text field is displaying under 'Add CPE device' page");
			} else {
				System.out.println(" 'Site Code' text field is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Site Code' text field is not displaying under 'Add CPE device' page");
			}
		} catch (NoSuchElementException eee) {
			eee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Site Code' text field is not displaying");
			System.out.println(" 'Site Code' text field is not displaying");
		}

		// Premise name
		boolean premisename = false;
		try {
			premisename = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield"))
							.isDisplayed();
			sa.assertTrue(premisename, " 'Premise name' field is not getting displyed");
			if (premisename) {
				System.out.println(" 'Premise Name' text field is displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Premise Name' text field is displaying under 'Add CPE device' page");
			} else {
				System.out.println(" 'Premise Name' text field is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Premise Name' text field is not displaying under 'Add CPE device' page");
			}
		} catch (NoSuchElementException p) {
			p.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Premise Name' text field is not displaying");
			System.out.println(" 'Premise Name' text field is not displaying");
		}

		// Premise Code
		boolean premisecode = false;
		try {
			premisecode = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield"))
							.isDisplayed();
			sa.assertTrue(premisecode, " 'Premise Code' field is not getting displyed");
			if (premisecode) {
				System.out.println(" 'Premise Code' text field is displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Premise Code' text field is displaying under 'Add CPE device' page");
			} else {
				System.out.println(" 'Premise Code' text field is not displaying under 'Add CPE device' page");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Premise Code' text field is not displaying under 'Add CPE device' page");
			}
		} catch (NoSuchElementException pp) {
			pp.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Premise Code' text field is not displaying");
			System.out.println(" 'Premise Code' text field is not displaying");
		}

		// Click on disabled City toggle button
		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_disabledCityToggleButton")));
		Thread.sleep(5000);
	}

	public void deviceCreatoin_Overture(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country,
			String City, String Site, String Premise, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue, String existingcityselectionmode, String newcityselectionmode,
			String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode,
			String premisename, String premisecode) throws InterruptedException, DocumentException, IOException {

		try {
			String linklostForwardingcheckboxstate = "enabled";

			String[] Vender = { "Overture ISG-26", "Overture ISG-26R", "Overture ISG-26S", "Overture ISG140",
					"Overture ISG180", "Overture ISG6000" };

			String[] powerAlarm = { "AC", "DC" };

			String[] MediaSelectionExpectedValue = { "SFP-A with SFP-B", "RJ45-A with SFP-B" };

			String expectedDeviceNameFieldAutopopulatedValue = "<Device>.lanlink.dcn.colt.net";

			scrolltoend();
			Thread.sleep(3000);

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			Thread.sleep(3000);

			// Country Error Message
			device_countrywarningMessage(application);

			// Media Selection Error Message
//		device_mediaSelectionWarningMessage(application);

			ScrolltoElement(getwebelement("//label[text()='Name']"));
			Thread.sleep(3000);

			// name field Error Message
			device_nameFieldWarningMessage(application);

			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			// MAC Address Error Message
			device_macAddressWarningMessage(application);

			// Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);

			// Vendor/Model
			device_vendorModel(application, Vender, vender);

			// Snmpro
			device_snmPro(application, snmpro);

			// Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);

			// MEP Id
			device_mepID(application, Mepid);

			// Power Alarm
			device_powerAlarm(application, powerAlarm, poweralarm);

			// MAC Address
			device_MAcaddress(application, Macaddress);

			scrolltoend();
			Thread.sleep(3000);

			// Media Selection
			device_mediaSelection(application, MediaSelectionExpectedValue, MediaselectionActualValue);

			// Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);

			// Country
			device_country(application, country);

			// verify fields for City, Site and premise
			device_verifycity(application);

			// City
			if (existingcityselectionmode.equalsIgnoreCase("no") & newcityselectionmode.equalsIgnoreCase("yes")) {
				addCityToggleButton(application);
				// New City
				newcity(application, newcityselectionmode, cityname, citycode);
				// New Site
				newSite(application, newsiteselectionmode, sitename, sitecode);
				// New Premise
				newPremise(application, newpremiseselectionmode, premisename, premisecode);

			}

			else if (existingcityselectionmode.equalsIgnoreCase("yes") & newcityselectionmode.equalsIgnoreCase("no")) {
				// Existing City
				existingCity(application, existingcityselectionmode, City);

				// Site

				if (existingsiteselectionmode.equalsIgnoreCase("yes") & newsiteselectionmode.equalsIgnoreCase("no")) {
					// Existing Site
					existingSite(application, existingsiteselectionmode, Site);

					// Premise
					if (existingpremiseselectionmode.equalsIgnoreCase("yes")
							& newpremiseselectionmode.equalsIgnoreCase("no")) {
						existingPremise(application, existingpremiseselectionmode, Premise);

					} else if (existingpremiseselectionmode.equalsIgnoreCase("no")
							& newpremiseselectionmode.equalsIgnoreCase("yes")) {
						// New Premise
						addPremiseTogglebutton(application);
						newPremise_clickOnPremisetoggleButton(application, newpremiseselectionmode, premisename,
								premisecode);
					}

				}

				else if (existingsiteselectionmode.equalsIgnoreCase("no")
						& newsiteselectionmode.equalsIgnoreCase("yes")) {
					// New Site
					addSiteToggleButton(application);
					newSite_ClickOnSiteTogglebutton(application, newsiteselectionmode, sitename, sitecode);

					// New Premise
					newPremise_clickonSiteToggleButton(application, newpremiseselectionmode, premisename, premisecode);
				}
			}

			// OK button
			device_okbutton(application);

			// cancel button
			device_cancelButton(application);

			click_commonMethod(application, "OK", "okbutton", xml);
			Thread.sleep(5000);

			

		} catch (AssertionError e) {

			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");

		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Input data has been passed for creating device");
		Thread.sleep(3000);

	}

	public void deviceCreatoin_Accedian(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country,
			String City, String Site, String Premise, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue, String existingcityselectionmode, String newcityselectionmode,
			String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode,
			String premisename, String premisecode) throws InterruptedException, DocumentException, IOException {

		try {
			String linklostForwardingcheckboxstate = "disabled";

			String[] Vender = { "Accedian-1G 1GigE-MetroNID-GT", "Accedian-1G 1GigE-MetroNID-GT-S", "Accedian-1G GX" };

			String[] powerAlarm = { "AC", "DC" };

			String expectedDeviceNameFieldAutopopulatedValue = "<Device>.lanlink.dcn.colt.net";

			scrolltoend();
			Thread.sleep(3000);

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			Thread.sleep(3000);

			// Country Error Message
			device_countrywarningMessage(application);

			// serial Number Error Message
			device_serialNumberWarningMessage(application);

			// Hexa Serial Number Error Message
			device_hexaSerialNumberWarningMessage(application);

			ScrolltoElement(getwebelement("//label[text()='Name']"));
			Thread.sleep(3000);

			// name field Error Message
			device_nameFieldWarningMessage(application);

			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			// Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);

			// Vendor/Model
			device_vendorModel(application, Vender, vender);

			// Snmpro
			device_snmPro(application, snmpro);

			// Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);

			// MEP Id
			device_mepID(application, Mepid);

			// Power Alarm
			device_powerAlarm(application, powerAlarm, poweralarm);

			// serial number
			device_serialNumber(application, serialNumber);

			scrolltoend();
			Thread.sleep(3000);

			// Link lost Forwarding
//		device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);

			// Country
			device_country(application, country);

			// verify fields for City, Site and premise
			device_verifycity(application);

			// City
			if (existingcityselectionmode.equalsIgnoreCase("no") & newcityselectionmode.equalsIgnoreCase("yes")) {
				addCityToggleButton(application);
				// New City
				newcity(application, newcityselectionmode, cityname, citycode);
				// New Site
				newSite(application, newsiteselectionmode, sitename, sitecode);
				// New Premise
				newPremise(application, newpremiseselectionmode, premisename, premisecode);

			}

			else if (existingcityselectionmode.equalsIgnoreCase("yes") & newcityselectionmode.equalsIgnoreCase("no")) {
				// Existing City
				existingCity(application, existingcityselectionmode, City);

				// Site

				if (existingsiteselectionmode.equalsIgnoreCase("yes") & newsiteselectionmode.equalsIgnoreCase("no")) {
					// Existing Site
					existingSite(application, existingsiteselectionmode, Site);

					// Premise
					if (existingpremiseselectionmode.equalsIgnoreCase("yes")
							& newpremiseselectionmode.equalsIgnoreCase("no")) {
						existingPremise(application, existingpremiseselectionmode, Premise);

					} else if (existingpremiseselectionmode.equalsIgnoreCase("no")
							& newpremiseselectionmode.equalsIgnoreCase("yes")) {
						// New Premise
						addPremiseTogglebutton(application);
						newPremise_clickOnPremisetoggleButton(application, newpremiseselectionmode, premisename,
								premisecode);
					}

				}

				else if (existingsiteselectionmode.equalsIgnoreCase("no")
						& newsiteselectionmode.equalsIgnoreCase("yes")) {
					// New Site
					addSiteToggleButton(application);
					newSite_ClickOnSiteTogglebutton(application, newsiteselectionmode, sitename, sitecode);

					// New Premise
					newPremise_clickonSiteToggleButton(application, newpremiseselectionmode, premisename, premisecode);
				}
			}

			// OK button
			device_okbutton(application);

			// cancel button
			device_cancelButton(application);

			click_commonMethod(application, "OK", "okbutton", xml);
			Thread.sleep(5000);

			

		} catch (AssertionError e) {

			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");

		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Input data has been passed for creating device");
		Thread.sleep(3000);

	}

	public void viewdevice_Overture(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String linkLostForwarding, String existingcountry, String existingCity, String newCity, String existingSite,
			String newSite, String existingPremise, String newPremise, String existingcityselectionmode,
			String newcityselectionmode, String existingsiteselectionmode, String newsiteselectionmode,
			String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingpremiseselectionmode, String newpremiseselectionmode) throws InterruptedException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered while creating device");

		String[] RouterId = new String[2];
		RouterId = cpename.split(".lanlink");

		String RouterIdValue = RouterId[0];

		String mediaSelectionValueInViewDevicePage = "no";
		if (Mediaselection.equalsIgnoreCase("null")) {
			Mediaselection = mediaSelectionValueInViewDevicePage;
		} else {
			Mediaselection = mediaSelectionValueInViewDevicePage;
		}

		verifyEnteredvalues_deviceName("Name", RouterIdValue, cpename);

		verifyEnteredvalues("Router Id", RouterIdValue);

		verifyEnteredvalues("Vendor/Model", vender);

		verifyEnteredvalues("Snmpro", snmpro);

		// Management Address
		if ((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		} else if ((existingmanagementAddress.equalsIgnoreCase("no"))
				&& (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Management Address", managementAddress);
		}

//	  verifyEnteredvalues("MEP Id", Mepid);

		verifyEnteredvalues("Power Alarm", poweralarm);

		verifyEnteredvalues("Media Selection", Mediaselection);

		verifyEnteredvalues("MAC Address", Macaddress);

		verifyEnteredvalues("Link Lost Forwarding", linkLostForwarding);

		verifyEnteredvalues("Country", existingcountry);

		// City
		if ((existingcityselectionmode.equalsIgnoreCase("Yes")) && (newcityselectionmode.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("City", existingCity);
		} else if ((existingcityselectionmode.equalsIgnoreCase("no"))
				&& (newcityselectionmode.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("City", newCity);
		}

		// Site
		if ((existingsiteselectionmode.equalsIgnoreCase("Yes")) || (newsiteselectionmode.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Site", existingSite);
		} else if ((existingsiteselectionmode.equalsIgnoreCase("no"))
				&& (newsiteselectionmode.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Site", newSite);
		}

		// Premise
		if ((existingpremiseselectionmode.equalsIgnoreCase("Yes"))
				&& (newpremiseselectionmode.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Premise", existingPremise);
		} else if ((existingpremiseselectionmode.equalsIgnoreCase("no"))
				&& (newpremiseselectionmode.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Premise", newPremise);
		}

	}

	public void viewdevice_Accedian(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String existingcountry,
			String existingCity, String newCity, String existingSite, String newSite, String existingPremise,
			String newPremise, String existingcityselectionmode, String newcityselectionmode,
			String existingsiteselectionmode, String newsiteselectionmode, String newmanagementAddress,
			String existingmanagementAddress, String manageaddressdropdownvalue, String existingpremiseselectionmode,
			String newpremiseselectionmode) throws InterruptedException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "verify the details entered while creating device");

		String[] RouterId = new String[2];
		RouterId = cpename.split(".lanlink");

		String RouterIdValue = RouterId[0];

		String mediaSelectionValueInViewDevicePage = "no";
		if (Mediaselection.equalsIgnoreCase("null")) {
			Mediaselection = mediaSelectionValueInViewDevicePage;
		} else {
			Mediaselection = mediaSelectionValueInViewDevicePage;
		}

		verifyEnteredvalues_deviceName("Name", RouterIdValue, cpename);

		verifyEnteredvalues("Router Id", RouterIdValue);

		verifyEnteredvalues("Vendor/Model", vender);

		verifyEnteredvalues("Snmpro", snmpro);

		// Management Address
		if ((existingmanagementAddress.equalsIgnoreCase("Yes")) && (newmanagementAddress.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		} else if ((existingmanagementAddress.equalsIgnoreCase("no"))
				&& (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Management Address", managementAddress);
		}

		verifyEnteredvalues("Power Alarm", poweralarm);

		verifyEnteredvalues("Media Selection", Mediaselection);

//		  verifyEnteredvalues("MAC Address", hexaSerialnumber);

		verifyEnteredvalues("Serial Number", serialNumber);

//		  verifyEnteredvalues("Link Lost Forwarding", linkLostForwarding);

		// City
		if ((existingcityselectionmode.equalsIgnoreCase("Yes")) && (newcityselectionmode.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("City", existingCity);
		} else if ((existingcityselectionmode.equalsIgnoreCase("no"))
				&& (newcityselectionmode.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("City", newCity);
		}

		// Site
		if ((existingsiteselectionmode.equalsIgnoreCase("Yes")) && (newsiteselectionmode.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Site", existingSite);
		} else if ((existingsiteselectionmode.equalsIgnoreCase("no"))
				&& (newsiteselectionmode.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Site", newSite);
		}

		// Premise
		if ((existingpremiseselectionmode.equalsIgnoreCase("Yes"))
				&& (newpremiseselectionmode.equalsIgnoreCase("no"))) {
			verifyEnteredvalues("Premise", existingPremise);
		} else if ((existingpremiseselectionmode.equalsIgnoreCase("no"))
				&& (newpremiseselectionmode.equalsIgnoreCase("Yes"))) {
			verifyEnteredvalues("Premise", newPremise);
		}
	}

	public void device_editnamefield(String application, String cpename) {

		boolean name = false;
		try {

			name = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).isDisplayed();
			Thread.sleep(3000);

			if (name) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Name' field is displaying under 'Edit CPE device' page");

				if (cpename.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Name' field while editing");

				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")),
							cpename);
					Thread.sleep(3000);

					String actualValue_Name = getwebelement(
							xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							actualValue_Name + " is the edited value for 'Name' field");
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Name' field is not available under 'Edit CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " CPE Name field is not available ");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in CPE name field");
		}
	}

	public void device_editVendorModelField(String application, String vender) {

		boolean vend0r = false;
		try {

			vend0r = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")).isDisplayed();
			Thread.sleep(3000);
			if (vend0r) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Vendor/Model' dropdown is displaying in 'Edit CPE Device' page");

				if (vender.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes made for 'Vender/Model' dropdown while editing");

				} else {
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/EditCPEdevice_vendoModel_xbutton")));
					Thread.sleep(3000);

					Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='" + vender + "']"));
					ExtentTestManager.getTest().log(LogStatus.PASS, vender + " is the edited value for 'vender/Model' field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Vendor/Model' dropdown is not available in 'Edit CPE Device' page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Vender/Model' dropdown is not available");
		}
	}

	public void device_editSnmproField(String application) {

		boolean sNmpro = false;
		try {

			sNmpro = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).isDisplayed();

			if (sNmpro) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" ' Snmpro' field is displaying in 'Edit CPE Device' page as expected");

				boolean actualValue_snmpro = getwebelement(
						xml.getlocator("//locators/" + application + "/CPEdevice_snmpro_autoPopulatedValue"))
								.isDisplayed();
				if (actualValue_snmpro) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Snmpro' field value is displaying as expected." + " It is displaying as: "
									+ getwebelement(
											xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro"))
													.getAttribute("value"));

				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Snmpro' value is not displaying as expected." + " It is displaying as: "
									+ getwebelement(
											xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro"))
													.getAttribute("value"));
				}

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"No changes made for 'snmpro' field while editing cpe device under Equipment");

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snmpro' field is not available in 'Edit CPE Device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Snm pro' field is not available ");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Snm pro' field");
		}
	}

	public void device_editManagementAddressField(String application, String managementAddress) {

		boolean manageAddressAvailability = false;
		try {

			Clickon(getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_selectSubnettogglebutton")));
			Thread.sleep(3000);

			manageAddressAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).isDisplayed();
			Thread.sleep(3000);

			if (manageAddressAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"' Management Address' text field is displaying in 'Edit CPE Device' page as expected");

				if (managementAddress.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes made for 'Manage address' field while editing cpe device under Equipment");

				} else {

					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).clear();
					Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
							managementAddress);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							managementAddress + " is the edited value for 'Manage address' field");
				}

			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Manage Address' field is not available ");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Manage Addres' field");
		}
	}

	public void device_editMEPIdField(String application, String Mepid) {

		boolean mepIdavailability = false;
		try {
			mepIdavailability = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid"))
					.isDisplayed();
			if (mepIdavailability) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Mep Id' field is displaying under 'Edit CPE device' page as expected");
				if (Mepid.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes made for 'mepid' field while editing cpe device under Equipment");
				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).clear();
					Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, Mepid + " is the edited value for 'Mepid' field");
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mep id' field is not available ");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Mep Id' field");
		}
	}

	public void device_editPowerAlarm(String application, String poweralarm)
			throws InterruptedException, DocumentException {

		boolean powerAlarm = false;
		try {

			powerAlarm = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm"))
					.isDisplayed();

			if (powerAlarm) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'POwer Alarm' field is displaying in 'Edit CPE device' page as expected");
				if (poweralarm.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes made for 'Power alarm' dropdown while editing cpe device under Equipment");

				} else {
					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/EditCPEdevice_powerAlarm_xbutton")));
					Thread.sleep(3000);

					Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='" + poweralarm + "']"));
					ExtentTestManager.getTest().log(LogStatus.PASS,
							poweralarm + " is the edited value for 'Power Alarm' field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Power Alarm' field is not available in 'Edit CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'power alarm' mandatory dropdown is not available");
		} catch (Exception er) {
			er.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NOt able to enter value under 'Power Alarm' dropdown");
		}

	}

	public void device_editMediaselection(String application, String Mediaselection)
			throws InterruptedException, DocumentException {

		boolean mediaSelection1 = false;
		try {

			mediaSelection1 = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")).isDisplayed();
			if (mediaSelection1) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Media Selection' dropdown is displaying in 'Edit CPE device' page as expected");
				if (Mediaselection.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes made for 'Media selection' dropdown while editing cpe device under Equipment");

				} else {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/EditCPEdevice_mediaselection_xbutton")));
					Thread.sleep(5000);

					Clickon(getwebelement(
							"//div[label[text()='Media Selection']]//div[text()='" + Mediaselection + "']"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							Mediaselection + " is the edited value for 'Media Selection' field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Media selection' dropdown is not avilable in 'Edit CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Media selection' mandatory dropdown is not available");
		} catch (Exception er) {
			er.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Media Selection' field");

		}
	}

	public void device_editMACaddress(String application, String Macaddress) {

		boolean macAddress = false;
		try {
			macAddress = getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress"))
					.isDisplayed();

			if (macAddress) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'MAC Address' field is displaying in 'Edit CPE device' page as expected");
				if (Macaddress.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes made for 'Mac address' field while editing cpe device under Equipment");
				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).clear();
					Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")),
							Macaddress);
					String actualValue_MacAddress = getwebelement(
							xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							actualValue_MacAddress + " is the edited value for 'Macaddress' field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'MAC Address' field is not available in 'Edit CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Mac Address' field is not available ");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in 'Mac Address' field");
		}
	}

	public void device_editlinkLostforwarding(String application, String linkLostForwarding) {

		boolean linklostcheckbox = false;
		try {
			linklostcheckbox = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();

			if (linklostcheckbox) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Link lost Forwarding' checkbox is displaying in 'Edit CPE device' page as expected");

				if (linkLostForwarding.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes made for linklost forwarding while editing cpe device under equipment");
				} else {

					boolean linklost = getwebelement(
							xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding"))
									.isSelected();

					if (linkLostForwarding.equalsIgnoreCase("yes")) {

						if (linklost) {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"linklost forwarding has been edited for cpe device under Equipment");

						} else {

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"linklost forwarding has been edited for cpe device under Equipment");
						}

					} else if (linkLostForwarding.equalsIgnoreCase("no")) {

						if (linklost) {
							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"linklost forwarding has been edited for cpe device under Equipment");

						} else {

							ExtentTestManager.getTest().log(LogStatus.PASS,
									"linklost forwarding has been edited for cpe device under Equipment");
						}

					}
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Link Lost Forwarding' checkbox is not available in 'Edit CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'linklost forwarding'  checkbox is ont available under 'Edit CPE device' page");
		} catch (Exception er) {
			er.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" NOt able to click on 'link lost forwarding' checkbox in 'Edit CPE device' page");
		}
	}

	public void device_editlinklostforwarding_10G(String application) {

		boolean linklostcheckboxAvailability = false;
		boolean linklostcheckboxEnabled = false;
		try {
			linklostcheckboxAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();

			if (linklostcheckboxAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Link lost Forwarding' checkbox is displaying in 'Edit CPE device' page as expected");

				linklostcheckboxEnabled = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isEnabled();
				if (linklostcheckboxEnabled) {
					System.out.println(" 'link lostforwarding is enabled for 10G");
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Link Lost forwarding' is enabled");

				} else {
					System.out.println("link lost checkbox is disabled as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Link lost Forwarding' checkbox is disabled as expected");
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Link Lost Forwarding' checkbox is not available in 'Edit CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'linklost forwarding'  checkbox is ont available under 'Edit CPE device' page");
		} catch (Exception er) {
			er.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'link lost forwarding' checkbox is disabled 'Edit CPE device' page");
		}

	}

	public void device_editserialnumber(String application, String serialNumber) {

		boolean serialunmberAvailability = false;
		try {

			serialunmberAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).isDisplayed();
			Thread.sleep(3000);

			if (serialunmberAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Serial Number' field is displaying under 'Edit CPE device' page");

				if (serialNumber.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes made for 'Serial Number' field while editing");

				} else {
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")),
							serialNumber);
					Thread.sleep(3000);

					String actualValue_Name = getwebelement(
							xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							actualValue_Name + " is the edited value for 'Serial Number' field");
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Serial Number' field is not available under 'Edit CPE device' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Serial Number field is not available ");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value in Serial Number field");
		}
	}

	public void createService_ServiceIdentification(String application, String ServiceIdentificationNumber)
			throws InterruptedException, IOException, DocumentException {
		// Service Identification

		boolean serviceIdentificationField = getwebelement(
				xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
		if (serviceIdentificationField) {

			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Service Identification' text field is displaying as exepected");
			if (!ServiceIdentificationNumber.equalsIgnoreCase("null")) {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
						ServiceIdentificationNumber);
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						ServiceIdentificationNumber + " is entered under 'Service Identification' field");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"It is a mandatory field. No values entered for service identification");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" ' Service Identfication' mandatory field is not available under 'Add Service' page");
		}

	}

	public void createService_singleEndPointCPE(String application, String EndpointCPE)
			throws InterruptedException, DocumentException {
		
		addCheckbox_commonMethod(application, "EndpointCPE", "Single Endpoint CPE", EndpointCPE, "No", xml);
	}

	public void createSerivce_email(String application, String Email)
			throws InterruptedException, DocumentException, IOException {

		boolean email = false;
		try {
			email = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
			if (email) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' text field is displaying as expected");

				if (!Email.equalsIgnoreCase("null")) {

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, Email + " is entered under 'Email' field");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "value not entered under 'Email' field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Email' field is not available under 'Create Service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Email field is not available");
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' field is not available under 'create Service' page");
		} catch (Exception er) {
			er.printStackTrace();
			System.out.println("Not able to enter value in 'Email' field");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value in 'Email' field");
		}
	}

	public void createService_phone(String application, String PhoneContact)
			throws InterruptedException, DocumentException, IOException {

		boolean phone = false;
		try {
			phone = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
			sa.assertTrue(phone, "phone contact field is not displayed");
			if (phone) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Phone Contact' text field is displaying as expected");

				if (!PhoneContact.equalsIgnoreCase("null")) {

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")),
							PhoneContact);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, PhoneContact + " is entered under 'Phone contact' field");
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Value not entered under 'Phone contact' field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Phone Contact' text field is not available under 'Create Service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Phone contact text field is not available");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Phone Contact' text field is not available under 'Create Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to enter value under 'hone Contact' field");
			System.out.println("Not able to enter value under 'Phone Contact' field");
		}
	}

	public void createService_remark(String application, String remark)
			throws InterruptedException, DocumentException, IOException {
		boolean Remark = false;
		try {
			Remark = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
			if (Remark) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Remark' text field is displaying as expected");

				if (!remark.equalsIgnoreCase("null")) {

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS, remark + " is entered under 'Remark' field");

				} else {

					ExtentTestManager.getTest().log(LogStatus.PASS, "value not entered under 'Remark' field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Remark' text field is not available under 'Create Service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Remak text field is not availeble");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Remark' text field is not available under 'Create Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			System.out.println(" Not able t enter value in 'remark' text field");
			System.out.println("Not able to enter value under 'Remark' field");
		}

	}

	public void createService_performancereporting_10G(String application, String PerformanceReporting)
			throws InterruptedException, DocumentException {

		boolean performancereoprting = false;
		try {
			performancereoprting = getwebelement(
					xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();
			if (performancereoprting) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Performance Reporting' checkbox is displaying as expected");

				if (!PerformanceReporting.equalsIgnoreCase("null")) {

					if (PerformanceReporting.equalsIgnoreCase("yes")) {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
						Log.info("performance Reporting check box is selected");

						boolean prfrmselection = getwebelement(
								xml.getlocator("//locators/" + application + "/performanceReportingcheckbox"))
										.isSelected();
						if (prfrmselection) {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"Performance Reporting checkbox is selected as expected");
						} else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Performance Reporting checkbox is not selected");
						}
					} else {

						System.out.println("Performance Reporting is not selected");
						ExtentTestManager.getTest().log(LogStatus.PASS, "performance Reporting checkbox is not selected");
					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Performance Reporting' checkbox is not selected");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Performance Reporting' checkbox is not displaying under 'create service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println(" 'Perormance reporting' checkbox is not selected");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Performance Reporting' checkbox is not displaying under 'create service' page");
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	public void createService_deliveryChannel(String application, String deliveryChannel)
			throws InterruptedException, DocumentException {

		boolean deliveryChanel = false;
		try {
			deliveryChanel = getwebelement(
					xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
			sa.assertTrue(deliveryChanel, "delivery channel dropdown is not displayed");
			if (deliveryChanel) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Delivery Channel' dropdown is displaying as expected");
				if (!deliveryChannel.equalsIgnoreCase("null")) {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							deliveryChannel + " is selected under 'Delivery channel' dropdown");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No value selected under 'Delivery channel' dropdown");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Delivery channel' dropdown is not dispalying under 'create Serice' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println(" 'Delivery channel' dropdown is not dispalying");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Delivery channel' dropdown is not dispalying under 'create Serice' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to selected value under 'Delivery channel' dropodwn");
		}
	}

	public void createService_managementOptions(String application, String ManagementConnection)
			throws InterruptedException, DocumentException {

		boolean Managementorder = false;
		try {
			Managementorder = getwebelement(xml.getlocator("//locators/" + application + "/managementConnection"))
					.isDisplayed();
			sa.assertTrue(Managementorder, "Management Connection field is not displayed");
			if (Managementorder) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" ' Management Connection' dropdown is displaying as expected");
				if (!ManagementConnection.equalsIgnoreCase("null")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[contains(text(),'" + ManagementConnection + "')][1]"));
					Thread.sleep(3000);

					ExtentTestManager.getTest().log(LogStatus.PASS,
							ManagementConnection + " is selected under 'management Connection' field");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Values not entered under 'Management Connection' field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" ' Management Connection' dropdown is not displaying under 'Create Service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println(" 'Management Connection' dropdown is not displaying");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" ' Management Connection' dropdown is not displaying under 'Create Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to select value under 'management Connection' dropdown");
		}
	}

	public void createService_proactivemonitoring(String application, String ProactiveMonitoring,
			String notificationManagement) throws InterruptedException, DocumentException {

		boolean proactiveMonitor = false;
		proactiveMonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring"))
				.isDisplayed();
		sa.assertTrue(proactiveMonitor, "pro active monitoring checkbox is not displayed");
		if (proactiveMonitor) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Pro active Monitoring' text field is displaying as expected");

			if (!ProactiveMonitoring.equalsIgnoreCase("null")) {
				if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("Pro active monitoring check box is selected");

					boolean proactiveSelection = getwebelement(
							xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
					if (proactiveSelection) {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								" 'pro active monitoring' checkbox is selected as expected");
					} else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, " 'pro active monitoring' checkbox is not selected");
					}

					// Notification management
					try {
						ExtentTestManager.getTest().log(LogStatus.INFO,
								"Notification Management dropdown displays when pro active monitoring is selected");

						if (!notificationManagement.equalsIgnoreCase("null")) {
							Log.info(
									"Notificationan Management dropdown displays when pro active monitoring is selected");

							Clickon(getwebelement(
									xml.getlocator("//locators/" + application + "/notificationmanagement")));
							Thread.sleep(3000);
							Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
							Thread.sleep(3000);
							ExtentTestManager.getTest().log(LogStatus.PASS,
									notificationManagement + " is selected under 'Notification management' dropdown");

						} else {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									"No values selected under Notification management dropdown");

						}

					} catch (NoSuchElementException e) {
						System.out.println(
								" 'Notification management' dropodwn is not displaying under 'create Service' page");
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								" 'Notification management' dropodwn is not displaying under 'create Service' page");
					} catch (Exception err) {
						err.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								"Not able to select values under Notification management dropdown");
					}
				} else {
					Log.info("Pro active monitoring is not selected");
					System.out.println("pro active monitoring is not selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "performance monitor checkbox is not selected ");
				}

			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Pro active monitoring' checkbox is not displaying");
		}
	}

	public void createService_intermediateTechnology(String application, String intermediateTechnology)
			throws InterruptedException, DocumentException, IOException {
		boolean intrTech = false;
		try {
			intrTech = getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology"))
					.isDisplayed();
			if (intrTech) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Intermediate Technology' text field is displying as expected under 'create service' page");
				if (!intermediateTechnology.equalsIgnoreCase("null")) {
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
							intermediateTechnology);

					String actualvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/IntermediateTechnology"))
									.getAttribute("value");
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							actualvalue + " is entered under 'Intermediate technology' field");
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No value entered under 'Intermediate  Technology' field");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Intermediate Technology' text field is not displying under 'create service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("'Intermediate Technology' text field is not displying");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Intermediate Technology' text field is not displying under 'create service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to enter value under 'Intermediate Technology' text field in 'create service' page");
			System.out.println(
					"Not able to enter value under 'Intermediate Technology' text field in 'create service' page");
		}
	}

	public void createService_circuitreference(String application, String CircuitReference)
			throws InterruptedException, DocumentException, IOException {
		boolean crctRef = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference"))
				.isDisplayed();
		if (crctRef) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Reference' text field is displying as expected");
			if (!CircuitReference.equalsIgnoreCase("null")) {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")),
						CircuitReference);
				Thread.sleep(3000);

				String actualvalue = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference"))
						.getAttribute("value");

				ExtentTestManager.getTest().log(LogStatus.PASS, actualvalue + " is entered under 'Circuit Reference' field");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Circuit reference field is mandatory, but no inputs has been provided");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Reference' text field is not displying");
		}
	}

	public void createSerivce_circuitType(String application, String CircuitType) throws InterruptedException {

		if (!CircuitType.equalsIgnoreCase("null")) {
			Clickon(getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, CircuitType + " is selected under 'Circuit Type'");
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" No changes made. 'Default' is selected under'Circuit type' as no input provided");

		}
	}

	public void createService_standardCIR(String application, String standrdCir)
			throws InterruptedException, DocumentException, IOException {
		boolean standrdCiR = false;

		try {
			standrdCiR = getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield"))
					.isDisplayed();
			if (standrdCiR) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Standard CIR' text field displaying, when 'Actelis Based' checkbox is selected");
				if (standrdCir.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No Values entered in 'Standard CIR' text field");
				} else {
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")),
							standrdCir);

					String valuesForSCIR = getwebelement(
							xml.getlocator("//locators/" + application + "/standardCIRtextfield"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							valuesForSCIR + " is enerted under 'Standard CIR' text field");
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Standard CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out
					.println("'Standard CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Standard CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to enter value under 'Standard CIR' field in 'create Service' page");
		}
	}

	public void createService_standardEIR(String application, String standrdEir)
			throws InterruptedException, DocumentException, IOException {
		boolean standrdEiR = false;
		try {
			standrdEiR = getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield"))
					.isDisplayed();
			if (standrdEiR) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Standard EIR' text field displaying, when 'Actelis Based' checkbox is selected");
				if (standrdEir.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No Values entered in 'Standard EIR' text field");
				} else {
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")),
							standrdEir);

					String valuesForSEIR = getwebelement(
							xml.getlocator("//locators/" + application + "/standardEIRtextfield"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							valuesForSEIR + " is enerted under 'Standard EIR' text field");
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Standard EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out
					.println("'Standard EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Standard EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not albe to enter value under 'Standard EIR' field in 'create Service' page");
			System.out.println(" Not able to enter value under 'Standard EIR' field in 'create Service' page");
		}
	}

	public void createService_premiumCIR(String application, String prmiumCir)
			throws InterruptedException, DocumentException, IOException {
		boolean premiumCiR = false;
		try {
			premiumCiR = getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield"))
					.isDisplayed();
			if (premiumCiR) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Premium CIR' text field displaying, when 'Actelis Based' checkbox is selected");
				if (prmiumCir.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No Values entered in 'Premium CIR' text field");
				} else {
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")),
							prmiumCir);

					String valuesForPCIR = getwebelement(
							xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							valuesForPCIR + " is enerted under 'Premium CIR' text field");
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Premium CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("'Premium CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Premium CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to enter value under 'Premium CIR' field in 'create Service' page");
		}
	}

	public void createService_premiumEIR(String application, String prmiumEir)
			throws InterruptedException, DocumentException, IOException {
		boolean premiumEiR = false;
		try {
			premiumEiR = getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield"))
					.isDisplayed();
			if (premiumEiR) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Standard EIR' text field displaying, when 'Actelis Based' checkbox is selected");
				if (prmiumEir.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No Values entered in 'Premium EIR' text field");
				} else {
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")),
							prmiumEir);

					String valuesForPEIR = getwebelement(
							xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							valuesForPEIR + " is enerted under 'Premium EIR' text field");
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Premium EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("'Premium EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Premium EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Not able to enter value under 'Premium EIR' field in 'create Service' page");
		}
	}

	public void editService_serviceIdentification(String application, String ServiceIdentificationNumber)
			throws InterruptedException, DocumentException, IOException {

		boolean serviceAvailability = false;
		serviceAvailability = getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification"))
				.isDisplayed();

		if (serviceAvailability) {

			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Service Identification' field is displaying in 'Edit Service'page as expected");

			if (!ServiceIdentificationNumber.equalsIgnoreCase("null")) {

				getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).clear();
				Thread.sleep(2000);
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
						ServiceIdentificationNumber);

				String Actualvalue = getwebelement(
						xml.getlocator("//locators/" + application + "/ServiceIdentification")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Edited value for 'Service Identification' field is " + Actualvalue);

			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS, " Service Identification field value is not edited");
				System.out.println(" Service Identification field value is not edited");
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Service Identification' field is not availale in 'Edit Service'page");
		}
	}

	public void editService_singleEndPointCPE(String application, String EndpointCPE)
			throws InterruptedException, DocumentException {
		boolean CPEAvailability = false;

			try {
				CPEAvailability = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE"))
						.isDisplayed();
				if (CPEAvailability) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Single EndPoint CPE checkbox is displaying under 'Edit Service' page");

					if (!EndpointCPE.equalsIgnoreCase("null")) {

						boolean endpoint = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE"))
								.isSelected();
						Thread.sleep(2000);

						if (EndpointCPE.equalsIgnoreCase("yes")) {

							if (endpoint) {

								ExtentTestManager.getTest().log(LogStatus.PASS,
										"Endpoint CPE is not edited. It is already Selected while creating.");

							} else {

								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
								Log.info("End point CPE check box is edited and it got selected");
								ExtentTestManager.getTest().log(LogStatus.PASS, "Endpoint CE is edited and it got selected");
							}
						} else if (EndpointCPE.equalsIgnoreCase("no")) {
							if (endpoint) {

								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
								Log.info("End point CPE check box is unselected");
								ExtentTestManager.getTest().log(LogStatus.PASS,
										"Endpoint CE is edited and it is unselected as Expected");

							} else {
								ExtentTestManager.getTest().log(LogStatus.PASS,
										"Endpoint CPE is not edited. It was not selected during service creation and it remains unselected as expected");
							}

						}
					} else {
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"No changes made for EndPoint CPE chekbox as expected");
					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"Single EndPoint CPE checkbox is not available under 'Edit Service' page");
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Single EndPoint CPE checkbox is not available under 'Edit Service' page");
				System.out.println("Single EndPoint CPE checkbox is not available under 'Edit Service' page");
			} catch (Exception err) {
				err.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on 'Single endpoint CPE' checkbox");
				System.out.println("Not able to click on 'Single endpoint CPE' checkbox");
			}

	}

	public void editService_Email(String application, String Email)
			throws InterruptedException, DocumentException, IOException {

		boolean emailAvailability = false;
		try {
			emailAvailability = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();

			if (emailAvailability) {

				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' text field is displaying in 'Edit Service' page");

				if (!Email.equalsIgnoreCase("null")) {

					getwebelement(xml.getlocator("//locators/" + application + "/Email")).clear();
					Thread.sleep(2000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Email' field is " + Email);

					String Actualvalue = getwebelement(xml.getlocator("//locators/" + application + "/Email"))
							.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Email' field is edited as: " + Actualvalue);
					System.out.println("'Email' field is edited as: " + Actualvalue);

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Email' field is not edited");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Email' text field is not available in 'Edit Service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Email' text field is not available in 'Edit Service' page");
			System.out.println(" 'Email' text field is not available in 'Edit Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " not able to edit 'Email' field");
		}

	}

	public void editService_phoneContact(String application, String PhoneContact)
			throws InterruptedException, DocumentException, IOException {

		boolean phoneAvailability = false;
		try {
			phoneAvailability = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact"))
					.isDisplayed();

			if (phoneAvailability) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Phone Contact field is displaying in 'Edit Service' page as expected");

				if (!PhoneContact.equalsIgnoreCase("null")) {

					getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).clear();
					Thread.sleep(2000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")),
							PhoneContact);
					Thread.sleep(3000);
					String actualValue = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact"))
							.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Phone contact' field is " + actualValue);
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Phone contact' field is not edited");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Phone Contact field is not available in 'Edit Service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Phone Contact field is not available in 'Edit Service' page");
			System.out.println("Phone Contact field is not available in 'Edit Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'phone Contact' field");
			System.out.println(" Not able to edit 'phone Contact' field");
		}
	}

	public void editService_remark(String application, String remark)
			throws InterruptedException, DocumentException, IOException {

		boolean remarkAvailability = false;
		try {
			remarkAvailability = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();

			if (remarkAvailability) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Remark' field is displaying in 'Edit Service' page as expected");

				if (!remark.equalsIgnoreCase("null")) {

					getwebelement(xml.getlocator("//locators/" + application + "/Remark")).clear();
					Thread.sleep(2000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);

					String actualvalue = getwebelement(xml.getlocator("//locators/" + application + "/Remark"))
							.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Remark' field is " + actualvalue);

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Remark' field is not edited");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remark' field is not available in 'Edit Service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Remark' field is not available in 'Edit Service' page");
			System.out.println(" 'remark' field is not available in 'Edit Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Remark' field");
			System.out.println(" Not able to edit 'Remark' field");
		}
	}

	public void editService_performancereporting_10G(String application, String performanceReporting)
			throws InterruptedException, DocumentException {

		if (!performanceReporting.equalsIgnoreCase("null")) {

			boolean perfmmonitr = getwebelement(
					xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
			Thread.sleep(2000);

			if (performanceReporting.equalsIgnoreCase("yes")) {

				if (perfmmonitr) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Performance Reporting checkbox is not edited and it is already Selected while creating");

				} else {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("Performance monitor check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting is edited and it is selected");
				}
			} else if (performanceReporting.equalsIgnoreCase("no")) {
				if (perfmmonitr) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
					Log.info("Performance Reporting check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Performance Reporting is edited and gets unselected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Performance Reporting is not edited and it remains unselected");
				}
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Performance reporting' checkbox is not edited");
		}
	}

	public void editService_proactiveMonitoring(String application, String ProactiveMonitoring,
			String notificationManagement) throws InterruptedException, DocumentException {
		if (!ProactiveMonitoring.equalsIgnoreCase("null")) {

			boolean proactivemonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
			Thread.sleep(2000);

			if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

				if (proactivemonitor) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Proactive monitoring is not edited and it is already Selected while creating");

					if (notificationManagement.equalsIgnoreCase("null")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to notification management");

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
						Thread.sleep(4000);

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						Thread.sleep(3000);
						Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
						Thread.sleep(3000);
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Edited value for 'Notification Management' field is " + notificationManagement);
					}

				} else {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("proactive monitoring check box is selected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring is edited and gets selected");

					if (notificationManagement.equalsIgnoreCase("null")) {

						ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made to notification management");

					} else {

						Clickon(getwebelement(
								xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
						Thread.sleep(2000);

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
						Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Edited value for 'Notification Management' field is " + notificationManagement);
					}
				}
			}

			else if (ProactiveMonitoring.equalsIgnoreCase("no")) {

				if (proactivemonitor) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
					Log.info("Proactive monitoring check box is unselected");
					ExtentTestManager.getTest().log(LogStatus.PASS, "proactive monitoring is edited and gets unselected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"proactive monitoring was not selected during service creation and it remains unselected");
				}
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for 'Proactive monitoring' chekbox");
		}
	}

	public void editService_deliveryChannel(String application, String deliveryChannel)
			throws InterruptedException, DocumentException {

		boolean deliveryChannelAvailability = false;
		try {
			deliveryChannelAvailability = getwebelement(
					xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();

			if (deliveryChannelAvailability) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Delivery channel' dropdown is displaying in 'Edit Service' page as expected");

				if (!deliveryChannel.equalsIgnoreCase("null")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_xbutton")));
					Thread.sleep(3000);

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Delivery Channel' dropdown is " + deliveryChannel);
					System.out.println("Delivery channel dropdown value is edited as expected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Delivery channel dropdown value is not edited");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Delivery channel' dropdown is not available in 'Edit Service' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Delivery channel' dropdown is not available in 'Edit Service' page");
			System.out.println(" 'Delivery channel' dropdown is not available in 'Edit Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'delvery Channel' dropdown");
		}
	}

	public void editService_managementConnection(String application, String ManagementConnection)
			throws InterruptedException, DocumentException {
		
		
		addDropdownValues_commonMethod(application, "Management Connection" , "managementConnection", ManagementConnection, xml);
		
	}

	public void editService_circuitreference(String application, String CircuitReference)
			throws InterruptedException, DocumentException, IOException {

		boolean circuitRefAvailability = false;

		circuitRefAvailability = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference"))
				.isDisplayed();
		if (circuitRefAvailability) {
			if (CircuitReference.equalsIgnoreCase("null")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit reference' field is not edited");
			} else {

				getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
				Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")),
						CircuitReference);
				Thread.sleep(3000);
				String actualvalue = getwebelement(xml.getlocator("//locators/" + application + "/circuitReference"))
						.getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Edited value for 'Circuit reference' field is " + actualvalue);
			}
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Circuit Reference' field is not displaying in 'Edit CPE device' page");
		}
	}

	public void editService_IntermediateTechnology(String application, String intermediateTechnology)
			throws InterruptedException, DocumentException, IOException {

		boolean intTechAvilability = false;
		try {
			intTechAvilability = getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology"))
					.isDisplayed();
			if (intTechAvilability) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Intermediate Technologies' field is displaying under 'Edit Service' page as expected");
				if (intermediateTechnology.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							"No changes has made for 'Intermediate Technology' field");

				} else {

					getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
							intermediateTechnology);
					Thread.sleep(3000);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Intermediate Technology' field is " + intermediateTechnology);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Intermediate technologies' field is not displaying under 'edit Sevice' page");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Intermediate technologies' field is not displaying under 'edit Sevice' page");
			System.out.println(" 'Intermediate technologies' field is not displaying under 'edit Sevice' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Intermediate technologies' field");
		}
	}

	public void editService_circuitType(String application, String CircuitType) throws InterruptedException {

		try {
			if (CircuitType.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit type' field is not edited");

			} else {

				// verify whether it is selected
				boolean circuitType = getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input")
						.isSelected();
				if (circuitType) {

					ExtentTestManager.getTest().log(LogStatus.PASS,
							" 'Circuit Type' value selected as : " + CircuitType + " as expected");

				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" 'Circuit Type' value is not selected as : " + CircuitType);
				}

				// verify whether it is editable
//				boolean circuitTypeEditable=getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input").isEnabled();
//				if(circuitType) {
//					
//					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Circuit Type' is editable");
//					
//				}else {
//					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Circuit Type' value is not editable as expected" );
//				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Circuit type' " + CircuitType + " is not dipslaying under 'Edit Service page");
			System.out.println(" 'Circuit type' " + CircuitType + " is not dipslaying under 'Edit Service page");
		} catch (Exception er) {
			er.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Circuit type' " + CircuitType + " is disabled under 'Edit Service page");
			System.out.println(" 'Circuit type' " + CircuitType + " is disabled under 'Edit Service page");
		}
	}

	public void editService_premiumEIR(String application, String premiumEIR)
			throws InterruptedException, DocumentException, IOException {

		boolean premumEIR = false;
		try {
			premumEIR = getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield"))
					.isDisplayed();
			if (premumEIR) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Premise EIR' field is displaying in 'Edit Service' page as expected");
				if (!premiumEIR.equalsIgnoreCase("null")) {

					getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).clear();
					Thread.sleep(2000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")),
							premiumEIR);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Premium EIR' field is " + premiumEIR);

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "  'Premium EIR' field is not edited");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Premise EIR' field is not displaying in 'Edit Service' page ");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Premise EIR' field is not displaying in 'Edit Service' page ");
			System.out.println(" Premise EIR' field is not displaying in 'Edit Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Premise EIR' field");
			System.out.println("Not able to edit 'Premise EIR' field");

		}
	}

	public void editService_premiumCIR(String application, String premiumCIR)
			throws InterruptedException, DocumentException, IOException {

		boolean premumCIR = false;
		try {
			premumCIR = getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield"))
					.isDisplayed();
			if (premumCIR) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Premise CIR' field is displaying in 'Edit Service' page as expected");
				if (!premiumCIR.equalsIgnoreCase("null")) {

					getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).clear();
					Thread.sleep(2000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")),
							premiumCIR);

					String actualValue = getwebelement(
							xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Edited value for 'Premium CIR' field is " + actualValue);

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Premium CIR' field is not edited");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Premise CIR' field is not displaying in 'Edit Service' page ");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Premise CIR' field is not displaying in 'Edit Service' page ");
			System.out.println(" Premise CIR' field is not displaying in 'Edit Service' page ");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Premise CIR' field");
			System.out.println("Not able to edit 'Premise CIR' field");

		}
	}

	public void editService_standardEIR(String application, String standardEIR)
			throws InterruptedException, DocumentException, IOException {

		boolean stndrdEIR = false;
		try {
			stndrdEIR = getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield"))
					.isDisplayed();
			if (stndrdEIR) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Standard EIR' field is displaying in 'Edit Service' page as expected");
				if (!standardEIR.equalsIgnoreCase("null")) {

					getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")),
							standardEIR);

					String actualvalue = getwebelement(
							xml.getlocator("//locators/" + application + "/standardEIRtextfield"))
									.getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Standard EIR' field is " + actualvalue);

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Standard EIR' field is not edited");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Standard EIR' field is not displaying in 'Edit Service' page ");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Standard EIR' field is not displaying in 'Edit Service' page ");
			System.out.println(" 'Standard EIR' field is not displaying in 'Edit Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Standard EIR' field");
			System.out.println(" Not able to edit 'Standard EIR' field");

		}
	}

	public void editService_standardCIR(String application, String standardCIR)
			throws InterruptedException, DocumentException, IOException {

		try {
			boolean stndrdCIR = getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield"))
					.isDisplayed();
			if (stndrdCIR) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Standard CIR' field is displaying in 'Edit Service' page ");
				if (!standardCIR.equalsIgnoreCase("null")) {

					getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")),
							standardCIR);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Edited value for 'Standard CIR' field is " + standardCIR);

				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'Standard CIR' field is not edited");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'Standard CIR' field is not displaying in 'Edit Service' page ");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" 'Standard CIR' field is not displaying in 'Edit Service' page ");
			System.out.println(" 'Standard CIR' field is not displaying in 'Edit Service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to edit 'Standard CIR' field");
			System.out.println(" Not able to edit 'Standard CIR' field");

		}
	}

	public void verifyEnteredvalueForEmail_serviceCreationpage(String label, String expectedValue)
			throws InterruptedException {

		try {

			WebElement ele = getwebelement("(//div[div[label[contains(text(),'" + label + "')]]]/div[2])[2]");
			String valueinfo = ele.getText().toString();
			if ((valueinfo.equals("")) || (valueinfo.equalsIgnoreCase(null))) {

				System.out.println("value not displayed for " + label);
				valueinfo = "Null";

				sa.assertEquals(valueinfo, expectedValue, label + " value is not displaying as expected");

//					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No value displaying for : " + label);

			} else {

				System.out.println("value displayed for " + label + " is : " + valueinfo);

				Log.info("Step : value displayed for" + label + "is : " + valueinfo);

				sa.assertEquals(valueinfo, expectedValue, label + " value is not displaying as expected");

				if (valueinfo.equalsIgnoreCase(expectedValue)) {
					System.out.println("The valus is dipslaying as expected");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							" Value is displaying as expected in 'view' page for " + label);
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Step : value displayed for" + label + "is : " + valueinfo);
				} else {
					System.out.println("the values are not dipslaying as expected for label: " + label);
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							" Value is not displaying as expected in 'view' page for " + label);
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"Step : value displayed for " + label + "is : " + valueinfo);

				}

			}
		} catch (AssertionError err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, label + " value is not displaying as expected ");
		} catch (NoSuchElementException e) {
			System.out.println("value not displayed for " + label);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : " + label + " is not displaying");

		}
	}

	
	public void addMappingMode(String application, String mappingMode) {

		String labelname = "Mapping Mode";
		boolean availability = false;
		try {
			availability = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")).isDisplayed();
			if (availability) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Mapping mode dropdown is displaying as expected");
				System.out.println(labelname + " is displaying as expected");

				if (mappingMode.equalsIgnoreCase("null")) {

					ExtentTestManager.getTest().log(LogStatus.PASS, " No values selected under " + labelname + " dropdown");
					System.out.println(" No values selected under " + labelname + " dropdown");
				} else {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown_xbutton")));
					Thread.sleep(3000);

					Clickon(getwebelement("//div[text()='" + mappingMode + "']"));
					Thread.sleep(3000);

					String actualValue = getwebelement("//div[label[text()='" + labelname + "']]//span").getText();
					ExtentTestManager.getTest().log(LogStatus.PASS,
							labelname + " dropdown value selected as: " + actualValue);
					System.out.println(labelname + " dropdown value selected as: " + actualValue);

				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " dropdown is not displaying");
				System.out.println(labelname + " is not displaying");
			}
		} catch (NoSuchElementException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " dropdown is not displaying");
			System.out.println(labelname + " is not displaying");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NO value selected under " + labelname + " dropdown");
			System.out.println(" NO value selected under " + labelname + " dropdown");
		}

	}

	public boolean findPanelHeader(String application, String devicePanelName) throws InterruptedException {

		scrolltoend();
		Thread.sleep(3000);

		boolean panelheader = false;
		try {
			panelheader = getwebelement("//div[div[text()='" + devicePanelName + "']]").isDisplayed();

			if (panelheader) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Equipment' panel is displaying under 'view site order' page");
				System.out.println(" 'Equipment' panel is displaying under 'view site order' page");
				panelheader = true;

			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'Equipment' panel is not displaying under 'view site order' page");
				System.out.println(" 'Equipment' panel is not displaying under 'view site order' page");
				panelheader = false;

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" 'Equipment' panel is not displaying under 'view site order' page");
			System.out.println(" 'Equipment' panel is not displaying under 'view site order' page");
			panelheader = false;

		}

		return panelheader;
	}

	public void findlistofInterfaces_Equipment_viewdevicePage(String application, String devicename)
			throws InterruptedException, DocumentException {

		scrolltoend();
		Thread.sleep(3000);

		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'" + devicename
				+ "')]]]//span[text()='View']"));
		Thread.sleep(5000);

		List<WebElement> interfacelist = getwebelements(
				xml.getlocator("//locators/" + application + "/findlistofInterfacesNames"));

		int interfacelistSize = interfacelist.size();
		System.out.println("list of interfaces displaying are:" + interfacelistSize);
		ExtentTestManager.getTest().log(LogStatus.PASS, " size of interfaces displaying is: " + interfacelistSize);

		ExtentTestManager.getTest().log(LogStatus.PASS, " Interfaces displaying are: ");
		System.out.println(" Interfaces displaying are: ");

		for (WebElement interfaceName : interfacelist) {

			ExtentTestManager.getTest().log(LogStatus.INFO, " " + interfaceName.getText());
			System.out.println("Interface names are: " + interfaceName.getText());
		}
	}

	public void findlistofInterfaces_IntEquipment_viewdevicePage(String application, String devicename)
			throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();

		scrolltoend();
		Thread.sleep(3000);

		Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"
				+ devicename + "')]]]//span[text()='View']"));
		Thread.sleep(5000);

		scrolltoend();
		Thread.sleep(3000);
		List<WebElement> interfacelist = getwebelements(
				xml.getlocator("//locators/" + application + "/findlistofInterfacesNames"));

		int interfacelistSize = interfacelist.size();
		System.out.println("list of interfaces displaying are:" + interfacelistSize);
		ExtentTestManager.getTest().log(LogStatus.PASS, " size of interfaces displaying is: " + interfacelistSize);

		ExtentTestManager.getTest().log(LogStatus.PASS, " Interfaces displaying are: ");
		System.out.println(" Interfaces displaying are: ");

		for (WebElement interfaceName : interfacelist) {

			ExtentTestManager.getTest().log(LogStatus.INFO, " " + interfaceName.getText());
			System.out.println("Interface names are: " + interfaceName.getText());
		}
	}

	public void configure_circuitId(String application, String circuitID) throws InterruptedException {

		try {
			if (circuitID.equalsIgnoreCase("null")) {

				ExtentTestManager.getTest().log(LogStatus.PASS, " No input provided for 'Circuit ID' field");
				System.out.println(" No input provided for 'Circuit ID' field");

			} else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectCircuit_togglebutton")));
				Thread.sleep(3000);

				SendKeys(
						getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")),
						circuitID);
				ExtentTestManager.getTest().log(LogStatus.PASS, circuitID + " is the value passed for 'Circuit ID' field");

				String actualvalue = getwebelement(
						xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID"))
								.getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Vallue entered in 'Circuit Id' field is: " + actualvalue);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"'circuit Id' field under 'Edit Interface' page is not available ");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Not able to enter value inside 'Circuit Id' field");
		}
		Thread.sleep(3000);
	}

	public void fetchDeviceInterface_viewdevicepage(String application) throws InterruptedException, DocumentException {

		scrollToTop();
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));

		Thread.sleep(3000);

		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/fetchDeviceinterfacelink_viewDevicePage")));
		Thread.sleep(3000);

		// verify success Message
		boolean successMessage = false;
		successMessage = getwebelement(
				xml.getlocator("//locators/" + application + "/succesMessageForfetchDeviceInterfcae")).isDisplayed();
		String actualMessage = getwebelement(
				xml.getlocator("//locators/" + application + "/succesMessageForfetchDeviceInterfcae")).getText();
		if (successMessage) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
			System.out.println(
					" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");

			ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message displays as: " + actualMessage);
			System.out.println(" Success Message displays as: " + actualMessage);

			// click on the 'click here' link
//				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/succesMessageForfetchDeviceInterfcae")));
//				Thread.sleep(3000);

//				manageNetwork(application);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" After clicking on 'Fetch Device Interface' link, success Message is not displaying");
			System.out.println(" After clicking on 'Fetch Device Interface' link, success Message is not displaying");
		}
	}

	public void manageNetwork(String application) throws InterruptedException, DocumentException {

		boolean manageNetworkHeader = false;
		manageNetworkHeader = getwebelement(xml.getlocator("//locators/" + application + "/ManageNetworkHeaderName"))
				.isDisplayed();
		if (manageNetworkHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Manage Network' header name is displaying as expected");
		}
	}

	public void verifyeditedinterfacevalue(String application, String interfaceName) throws InterruptedException {

		List<WebElement> interfacedetails = getwebelements("//div[div[text()='" + interfaceName + "']]/div");

		for (WebElement listOfInterfacenames : interfacedetails) {

			String ColumnNames = listOfInterfacenames.getAttribute("col-id");
			String values = listOfInterfacenames.getText();

			ExtentTestManager.getTest().log(LogStatus.PASS,
					"After Editing interface, list of values displaying in interface table are: ");
			ExtentTestManager.getTest().log(LogStatus.PASS, "value displaying for " + ColumnNames + " is: " + values);

			System.out.println("After Editing interface, list of values displaying in interface table are: ");
			System.out.println("value displaying for " + ColumnNames + " is: " + values);

		}
	}

	public boolean EquipmentCOnfigurationPanel(String application) throws InterruptedException, DocumentException {

		boolean EquipConfigPanel = false;
		EquipConfigPanel = getwebelement(xml.getlocator("//locators/" + application + "/EquipementConfigurationPanel"))
				.isDisplayed();
		if (EquipConfigPanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"In 'view Site Order' page, 'Equipment Configuration' panel is displaying as expected for 'Actelis' Technology");
			System.out.println(
					"In 'view Site Order' page, 'Equipment Configuration' panel is displaying as expected for 'Actelis' Technology");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"In 'view Site Order' page, 'Equipment Configuration' panel is not displaying for 'Actelis' Technology");
			System.out.println(
					"In 'view Site Order' page, 'Equipment Configuration' panel is not displaying for 'Actelis' Technology");

		}
		return EquipConfigPanel;

	}

	public void equipConfiguration_Actelis_AddDevice(String application, String devicename, String vendor,
			String routerId, String manageAddress, String MEPid, String ETH_Port)
			throws InterruptedException, DocumentException, IOException {

		scrolltoend();
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/equipConfig_addCPEdevice")));
		Thread.sleep(3000);

		boolean addActelisHeader = false;
		addActelisHeader = getwebelement(xml.getlocator("//locators/" + application + "/addActelisCPEpage_headerName"))
				.isDisplayed();
		if (addActelisHeader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add Actelis CPE device' page is displaying as expected");
			System.out.println(" 'Add Actelis CPE device' page is displaying as expected");

			Clickon(getwebelement("//span[text()='OK']"));
			Thread.sleep(2000);

			// Validate Warning Messages_Name Field
			warningMessage_commonMethod(application, "devicenameFieldErrMSg_addCPE_Actelis", "Name", xml);

			// Validate Warning Messages_Router ID Field
			warningMessage_commonMethod(application, "RouterIDFieldErrMSg_addCPE_Actelis", "Router Id", xml);

			// Validate Warning Messages_Management Address Field
			warningMessage_commonMethod(application, "manageAddressFieldErrMSg_addCPE_Actelis", "Management Address",
					xml);

			// Name
			addtextFields_commonMethod(application, "Name", "nameField_addCPE_Actelis", devicename, xml);

			// vendor/Model
			addDropdownValues_commonMethod(application, "Vendor/Model", "vendorField_addCPE_Actelis", vendor, xml);

			// Router Id
			addtextFields_commonMethod(application, "Router Id", "RouterIdField_addCPE_Actelis", routerId, xml);

			// Management Address
			addtextFields_commonMethod(application, "Management Address", "managementAddressField_addCPE_Actelis",
					manageAddress, xml);

			// MEP Id
			addtextFields_commonMethod(application, "MEP Id", "MEPidField_addCPE_Actelis", MEPid, xml);

			// ETH Port
			addDropdownValues_commonMethod(application, "ETH Port", "ETHportField_addCPE_Actelis", ETH_Port, xml);

			// OK button
			OKbutton_AddSiteOrder(application);

			// CAncel button
			cancelbutton_AddSiteOrder(application);

			click_commonMethod(application, "OK", "okbutton", xml);
			Thread.sleep(4000);

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'Add Actelis CPE device' page is not displaying");
		}
	}

	public void verifyDataEnteredFordeviceCreation_Actelis(String application, String devicename, String vendorModel,
			String RouterID, String manageAddress, String mepID, String ETH_Port)
			throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();

		scrolltoend();
		Thread.sleep(3000);

		click_commonMethod(application, "view_Link", "actelis_EquipConfig_viewLink", xml);
		Thread.sleep(5000);

		verifyEnteredvalues("Name", devicename);

//		verifyEnteredvalues("Vendor/Model", vendorModel);

		verifyEnteredvalues("Router Id", RouterID);

		verifyEnteredvalues("Management Address", manageAddress);

		verifyEnteredvalues("MEP Id", mepID);

		verifyEnteredvalues("ETH Port", ETH_Port);

	}

	public void AddDSLAMandHSL(String application, String DSLMdevice, String HSlname)
			throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/addDSLAMandHSL_xButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'X' button under 'DSLAM device' dropdown");
		Log.info("Clicked on 'X' button under 'DSLAM device' dropdown");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/DSLM_Device_Select")), DSLMdevice);
		ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is entered under 'DSLAM device' dropdown");

		WebElement valueToSElect = getwebelement(
				xml.getlocator("//locators/" + application + "/selectDSLAMdeviceValue").replace("value", DSLMdevice));

		try {
			if (valueToSElect.isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is displaying under 'DSLAM device' dropdown");
				Log.info(DSLMdevice + " is displaying under 'DSLAM device' dropdown");

				Clickon(valueToSElect);
				ExtentTestManager.getTest().log(LogStatus.PASS, DSLMdevice + " is selected under 'DSLAM device' dropdown");
				Log.info(DSLMdevice + " is selected under 'DSLAM device' dropdown");

				waitForpageload();
				waitforPagetobeenable();

				click_commonMethod(application, "List_HSL", "List_HSL_Link", xml); // click on "List HSL" button

				selectRowForAddingInterface_Actelis(application, HSlname); // select the Interface

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
				Log.info(DSLMdevice + " is not displaying under 'DSLAM device' dropdown");
			}

		} catch (Exception e) {
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
				String CurrentPage = Gettext(getwebelement(
						xml.getlocator("//locators/" + Application + "/InterfaceToSelect_actelis_currentpage")));
				int Current_page = Integer.parseInt(CurrentPage);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = getwebelements("//div[contains(text(),'" + interfacenumber + "')]");

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
								ExtentTestManager.getTest().log(LogStatus.PASS,
										interfacenumber + " is selected under 'Add DSLAM and Device' page");

								Clickon(getwebelement("//span[text()='Next']"));
								Thread.sleep(3000);
							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver
									.findElements(By.xpath("//div[contains(text(),'" + interfacenumber + "')]"));
							numofrows = results.size();
							// results.get(i).click();
							Log.info("selected row is : " + i);
							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"failure while selecting interface to add with service");
						}
					}
					break ab;
				}
			}
		} else {

			System.out.println("No values available in table");
			Log.info("No Interfaces got fetched");
			ExtentTestManager.getTest().log(LogStatus.FAIL, " NO interfaces got fetched");
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
	

	public void deletInterface_ActelisConfiguration(String application, String interfaceName,
			String InterfaceDeletionSelection) throws InterruptedException, DocumentException {

		// select the interface
		Clickon(getwebelement("//div[text()='" + interfaceName + "']"));

		// click on Action button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AcionButton_ActelisConfiguration")));

		// Remove Button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/removeButton_ActelisConfiguration")));

		boolean popupMessage = false;
		popupMessage = getwebelement(
				xml.getlocator("//locators/" + application + "/popupMessage_forRemove_ActelisConfiguration"))
						.isDisplayed();

		if (popupMessage) {
			String actualmsg = getwebelement(
					xml.getlocator("//locators/" + application + "/popupMessage_forRemove_ActelisConfiguration"))
							.getText();
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" On clicking remoe button, popup message displays as: " + actualmsg);
			System.out.println(" On clicking remoe button, popup message displays as: " + actualmsg);

			if (InterfaceDeletionSelection.equalsIgnoreCase("Yes")) {

				Clickon(getwebelement("//button[@class='btn btn-danger']"));
				Thread.sleep(3000);
			} else {
				Clickon(getwebelement("//div[contains(text(),'×')]"));
				Thread.sleep(3000);

				ExtentTestManager.getTest().log(LogStatus.PASS, " Interface is not removed as expected");
			}
		} else {

			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" popup message does not display after clicking on 'Remove' button");
		}
	}

	public void successMessage_deleteInterfaceFromDevice_ActelisConfiguration(String application)
			throws InterruptedException, DocumentException {

		boolean successMessage = getwebelement(
				xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface"))
						.isDisplayed();
		String actualmessage = getwebelement(
				xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface"))
						.getText();
		if (successMessage) {

			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Success Message for removing Interface is dipslaying as expected");
			System.out.println(" Success Message for removing interface is dipslaying as expected");

			ExtentTestManager.getTest().log(LogStatus.PASS, "Message displays as: " + actualmessage);
			System.out.println("Message displays as: " + actualmessage);

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Success Message for removing Interface is not dipslaying");
			System.out.println(" Success Message for removing Interface is not dipslaying");
		}
	}

	public void verfyFields_ENNIcheckbox(String application) {
		boolean ENNIAvailability = false;
		boolean ENNISelection = false;
		try {
			ENNIAvailability = getwebelement(xml.getlocator("//locators/" + application + "/ENNI_checkbox"))
					.isDisplayed();

			if (ENNIAvailability) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" 'ENNI' checkbox is displaying under 'Create Service'page as expected");

				ENNISelection = getwebelement(xml.getlocator("//locators/" + application + "/ENNI_checkbox"))
						.isSelected();
				if (ENNISelection) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, " 'ENNI' checkbox is selected by default");
					System.out.println(" 'ENNI' checkbox is selected by default");
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, " 'ENNI' checkbox is not selected by default");
					System.out.println(" 'ENNI' checkbox is not selected by default");
				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" 'ENNI' checkbox is not available under 'Create Service' page");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'ENNI' chekcbox is not displaying");
			System.out.println(" 'ENNI' chekcbox is not displaying");
		} catch (Exception ee) {
			ee.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'ENNI' checkbox is not selected");
			System.out.println(" 'ENNI' checkbox is not selected");
		}
	}

	public void editcheckbox_commonMethod(String application, String expectedResult, String xpath, String labelname) {

		boolean Availability = false;
		try {
			Availability = getwebelement(xml.getlocator("//locators/" + application + "/" + xpath + "")).isDisplayed();

			if (Availability) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						labelname + " checkbox is displaying as expected in edit page");

				if (!expectedResult.equalsIgnoreCase("null")) {

					boolean isElementSelected = getwebelement(
							xml.getlocator("//locators/" + application + "/" + xpath + "")).isSelected();
					Thread.sleep(2000);

					if (expectedResult.equalsIgnoreCase("yes")) {

						if (isElementSelected) {

							ExtentTestManager.getTest().log(LogStatus.PASS,
									labelname + " checkbox is not edited and it is already Selected while creating");

						} else {

							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/" + xpath + "")));
							Log.info(labelname + " check box is selected");
							ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " checkbox is selected");
						}
					} else if (expectedResult.equalsIgnoreCase("no")) {

						if (isElementSelected) {

							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/" + xpath + "")));
							Log.info(labelname + " check box is unselected");
							ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " is edited and gets unselected");

						} else {
							ExtentTestManager.getTest().log(LogStatus.PASS,
									labelname + " is not edited and it remains unselected");
						}

					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No changes made for " + labelname + "  chekbox");
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						labelname + " checkbox is not available in 'Edit Service' page");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					labelname + " checkbox is not displaying under 'Edit service' page");
			System.out.println(labelname + " checkbox is not displaying under 'Edit service' page");
		} catch (Exception err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, " Not able to click on " + labelname + " checkbox");
			System.out.println(" Not able to click on " + labelname + " checkbox");
		}
	}

	/**
	 * verify the value used for creating service or site order or device in view
	 * page
	 * 
	 * @param label
	 * @param expectedValue
	 * @throws InterruptedException
	 */
	public void verifyEnteredvalues(String labelname, String ExpectedText) throws InterruptedException {

		String text = null;
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element = getwebelement("//div[div[label[contains(text(),'"+ labelname + "')]]]/div[2]");
			String emptyele = element.getText().toString();

			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname+" not found");
				Log.info(labelname+" not found");
			}
			else if (emptyele!=null && emptyele.isEmpty()) {
//				ExtentTestManager.getTest().log(LogStatus.PASS,  labelname + "' value is empty");
				
				emptyele= "Null";
				
				sa.assertEquals(emptyele, ExpectedText, labelname + " value is not displaying as expected");
				
				if(emptyele.equalsIgnoreCase(ExpectedText)) {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "The Expected value for '"+ labelname +"' field  is same as the Acutal value. It is id displaying blank");
					Log.info("The Expected value for '\"+ labelname +\"' field  is same as the Acutal value. It is displaying blank");
					
				}else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,"The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
					Log.info(" The Expected value '"+ExpectedText+"' is not same as the Acutal value '"+text+"'");
				}
				

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

	/**
	 * verify the entered device name value
	 * 
	 * @param label
	 * @param expectedValue
	 * @param devicename
	 * @throws InterruptedException
	 */
	public void verifyEnteredvalues_deviceName(String label, String expectedValue, String devicename)
			throws InterruptedException {

		try {

			boolean deviceName = getwebelement(
					"//div[div[label[text()='Name']]]//div[contains(text(),'" + expectedValue + "')]").isDisplayed();

			if (deviceName) {
				System.out.println("device name is displaying as expected");

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Device name is displaying as:  " + devicename + "as expected");
			} else {

				WebElement Actualvalue = getwebelement("//div[div[label[text()='Name']]]//div[2]");
				System.out.println("Device name is not displaying as expected");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Device name is displaying as:  " + Actualvalue.getText());
			}
		} catch (AssertionError err) {
			err.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, label + " value is not displaying as expected ");
		} catch (NoSuchElementException e) {
			System.out.println("value not displayed for " + label);

			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : value not displayed for : " + label);

		}
	}

	/**
	 * verify value for non-editable fields under Edit Interface
	 * 
	 * @param application
	 * @param labelname
	 * @param ExpectedValue
	 * @throws InterruptedException
	 */
	public void verifyenteredvaluesForEditPage_noneditableFields(String application, String labelname,
			String ExpectedValue) throws InterruptedException {
		boolean actualvalue = false;
		try {
			actualvalue = getwebelement(
					"//div[div[label[text()='" + labelname + "']]]//div[text()='" + ExpectedValue + "']").isDisplayed();
			if (actualvalue) {

				System.out.println("Value is displaying as expected for the field: " + labelname);
				ExtentTestManager.getTest().log(LogStatus.PASS, " Value displaying for " + labelname + " disabled field is: "
						+ ExpectedValue + " . And it is displaying as expected");

			} else {

				System.out.println("Value is not displaying as expected for the text field: " + labelname);
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Value is not displaying as expected for the text field: " + labelname);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Value is not displaying as expected for the text field: " + labelname);
		}

	}

	public void selectRowForIPVPNsiteorder(String Application, String siteordernumber)
			throws InterruptedException, DocumentException, IOException {

		System.out.println("-----------------------------" + siteordernumber + "---------------------");
		int TotalPages;

		scrolltoend();
		Thread.sleep(3000);
		List<WebElement> results = null;

		results = getwebelements(
				"//div[div[text()='Site Orders']]//following-sibling::div//div[text()='" + siteordernumber + "']");

		int numofrows = results.size();
		System.out.println("no of results: " + numofrows);
		boolean resultflag;

		resultflag = results.get(0).isDisplayed();
		System.out.println("status of result: " + resultflag);
		if (resultflag) {
			System.out.println(results.get(0).getText());
			results.get(0).click();
			Thread.sleep(8000);
			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Actiondropdown_siteorder")));

			Thread.sleep(5000);
		}
	}

	/**
	 * Fetching the value from View device page
	 * 
	 * @param application
	 * @param labelname
	 * @throws InterruptedException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unused")
	public void fetchValueFromViewPage(String application, String labelname)
			throws InterruptedException, DocumentException {

		String text = null;
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element = getwebelement(xml.getlocator("//locators/" + application + "/pedevice_viewpage")
					.replace("value", labelname));
			String emptyele = element.getText().toString();

			if (element == null) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " not found");
				System.out.println(labelname + " not found");
			} else if (emptyele != null && emptyele.isEmpty()) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "No value displaying under " + labelname);
				System.out.println("No value displaying under " + labelname);
			}
//						
			else {
				element.getText();
				ExtentTestManager.getTest().log(LogStatus.PASS, labelname + " value is displaying as: " + emptyele);
				System.out.println(labelname + " value is displaying as: " + emptyele);

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			System.out.println(labelname + " field is not displaying");
		}
	}

	/**
	 * 
	 * @param application
	 * @throws DocumentException
	 * @throws InterruptedException
	 */
	public void clickOnBreadCrump(String application, String breadCrumpLink)
			throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(2000);
//					WebElement breadcrumb=getwebelement(xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
		WebElement breadcrumb = null;

		try {
			breadcrumb = getwebelement(
					xml.getlocator("//locators/" + application + "/breadcrump").replace("value", breadCrumpLink));
			
			if (breadcrumb.isDisplayed()) {
				click_commonMethod_PassingWebelementDirectly(application, "Breadcrump", "breadcrump", xml);
				Thread.sleep(3000);
				
				waitForpageload();
				waitforPagetobeenable();
				
			} else {
				System.out.println("Breadcrumb is not displaying for the element " + breadcrumb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Breadcrumb is not displaying for the element " + breadcrumb);
		}
	}

	public void cickOnViewButton_PEdevice(String application, String devicename) throws Exception {

		Thread.sleep(3000);

		waitForpageload();
		waitforPagetobeenable();
		
		
		scrolltoend();
		Thread.sleep(2000);

		WebElement viewLink = getwebelement(
				xml.getlocator("//locators/" + application + "/selectViewButtonUnderPEdevice").replace("value",
						devicename));
		safeJavaScriptClick(viewLink);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on 'View' button");

	}
	
	
	public void clickOnautoDiscoverVPNPEdevice(String application, String devicename) throws Exception {

		waitForpageload();
		waitforPagetobeenable();
		
		scrolltoend();
		Thread.sleep(2000);

		WebElement autoDiscoverVPN = getwebelement(
				xml.getlocator("//locators/" + application + "/clickOnAutoDiscoverVPNLink").replace("value",
						devicename));
		safeJavaScriptClick(autoDiscoverVPN);
		ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on 'Autodiscover VPNs' link");
		
		scrollToTop();
		try {
			boolean successMsg = getwebelement(xml.getlocator("//locators/" + application + "/serivceAlert"))
					.isDisplayed();
			if (successMsg) {
				String alrtmsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage"))
								.getText();
				
				ExtentTestManager.getTest().log(LogStatus.INFO, "Message displays as: " + alrtmsg);

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Success Message is not displaying");
				System.out.println(" Success Message is not displaying");
			}

		} catch (Exception e) {
			Log.info("failure in fetching success message ");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Message displays");
			Log.info("No Message displays");
		}


	}

	

	public void clickOnAddInterfaceLink_PE(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		
		Thread.sleep(3000);
		
		waitForpageload();
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(1000);
		
		WebElement siteLabelName = getwebelement(
				xml.getlocator("//locators/" + application + "/viewPEdevice_siteLabelName"));
		scrolltoview(siteLabelName);
		Thread.sleep(2000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Add Interface' panel");

		click_commonMethod(application, "Action", "InterfacePanel_actionDropdown", xml);

		click_commonMethod(application, "Add Interface", "addInterfaceOrLink", xml);
	}

	
	public void clickOnAddInterfaceLink_CPE(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		
		Thread.sleep(3000);
		
		waitForpageload();
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(1000);
		
		WebElement siteLabelName = getwebelement(
				xml.getlocator("//locators/" + application + "/viewCPEdevice_siteLabelName"));
		scrolltoview(siteLabelName);
		Thread.sleep(2000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Add Interface' panel");

		click_commonMethod(application, "Action", "InterfacePanel_actionDropdown", xml);

		click_commonMethod(application, "Add Interface", "addInterfaceOrLink", xml);
	}

	
	
	public void clickOnAddMultiLink_PE(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		
		Thread.sleep(2000);
		
		WebElement siteLabelName = getwebelement(
				xml.getlocator("//locators/" + application + "/viewPEdevice_siteLabelName"));
		scrolltoview(siteLabelName);
		Thread.sleep(1000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Add Multilink' panel");

		click_commonMethod(application, "Action", "InterfacePanel_actionDropdown", xml);

		click_commonMethod(application, "Add Multilink", "addMultilink_PE_out", xml);
	}
	
	
	public void clickOnAddMultiLink_CPE(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		
		Thread.sleep(2000);
		
		WebElement siteLabelName = getwebelement(
				xml.getlocator("//locators/" + application + "/viewCPEdevice_siteLabelName"));
		scrolltoview(siteLabelName);
		Thread.sleep(1000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "verifying 'Add Multilink' panel");

		click_commonMethod(application, "Action", "InterfacePanel_actionDropdown", xml);

		click_commonMethod(application, "Add Multilink", "addMultilink_PE_out", xml);
	}
	

	/**
	 * Add Interface for PE device
	 * 
	 * @param application
	 * @param bearerType
	 * @param encapsulation
	 * @param pppEncapsulation
	 * @param dslDownStreamSpeed
	 * @param dslUpstreamSpeed
	 * @param mbsDropdown
	 * @param vpi
	 * @param vci
	 * @param slot
	 * @param port
	 * @param ivManagement
	 * @param existingAddressRange
	 * @param newAddressRange
	 * @param country
	 * @param SubnetSize
	 * @param availableBlocks
	 * @param address
	 * @param newInterfaceAddressRangeValue
	 * @throws InterruptedException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void addInterface(String application, String bearerType, String encapsulation, String pppEncapsulation,
			String dslDownStreamSpeed, String dslUpstreamSpeed, String mbsDropdown, String vpi, String vci, String slot,
			String port, String ivManagement, String existingAddressRange, String newAddressRange, String country,
			String SubnetSize, String availableBlocks, String address, String newInterfaceAddressRangeValue)
			throws InterruptedException, DocumentException, IOException {
		
		Thread.sleep(3000);
		scrolltoend();
		
		click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton", xml);    //click on Execute and Save button to check warning message
		
		scrollToTop();
		
		//Interface
		warningMessage_commonMethod(application, "InterfaceName_warningmEssage_out", "Interface", xml);
//		addtextFields_commonMethod(application, "Interface", "InterfaceField_out", interfaceName, xml);	//Interface
		
		if(existingAddressRange.equalsIgnoreCase("Yes") && newAddressRange.equalsIgnoreCase("No"))
		{
			click_commonMethod(application, "Get subnets", "getSubnets_out", xml);
			
			
			//EIP Allocation
			click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterface" , xml);
			
			addDropdownValues_commonMethod(application, "Country", "countryDropdown_EIpallocation", country, xml);
			
			addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", SubnetSize, xml);
			
		try {	
			boolean availableBlock=getwebelement(xml.getlocator("//locators/" + application + "/availableBlocks_dropdown")).isDisplayed();
			if(availableBlock) {
				
				String EIpmessage=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availableBlocksMessage_out")));
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Available Block dropdown is not dipslaying. Message displays as "+EIpmessage);
				System.out.println("Available Block dropdown is not dipslaying. Message displays as "+EIpmessage);
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
			}else {
				addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocks, xml);
				
				click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
				
				verifysuccessmessage(application, "successfully allocated");
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				
				
			}	
		}catch(Exception e) {
				e.printStackTrace();
				String EIpmessage=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availableBlocksMessage_out")));
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Available Block dropdown is not displaying. Message displays as "+EIpmessage);
				System.out.println("Available Block dropdown is not dipslaying. Message displays as "+EIpmessage);
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
			}
	
	//check value in Interface Address Range dropdown	
		try {
		String interfaceAddressRange=getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")).getAttribute("value");
		
		if(interfaceAddressRange.isEmpty()) {
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range' dropdown");
			System.out.println("No values displaying under 'Interface Address Range' dropdown");
			
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
			System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
			
			click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
			
			String addressValue=null;
		
			addDropdownValues_commonMethod(application, "Address", "FetchInterfaceDropdown_addInterface" , address, xml);
		}	
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Address' dropdown");
			System.out.println("No values displaying under 'Address' dropdown");
			
		}
		}	
		else if(existingAddressRange.equalsIgnoreCase("No") && newAddressRange.equalsIgnoreCase("Yes"))
		{
			addtextFields_commonMethod(application, "Interface Address Range", "newInterfaceAddressRange_textField", newInterfaceAddressRangeValue, xml);
		}
			
		addDropdownValues_commonMethod(application, "Bearer Type", "bearerTypeDropdown_out", bearerType, xml);	//Bearer Type	
		
		addDropdownValues_commonMethod(application, "Encapsulation", "encapsultionDropdown_out", encapsulation, xml);	//Encapsulation
		
		addDropdownValues_commonMethod(application, "PPP Encapsulation", "pppEncapsulation_out", pppEncapsulation, xml);	//PPP Encapsulation
		
		addDropdownValues_commonMethod(application, "DSL Downstream Speed", "dslDownStreamdropdown_ut", dslDownStreamSpeed, xml);	//DSL Downstream Speed
		
		addDropdownValues_commonMethod(application, "DSL Upstream Speed", "dslUpStreamDropdown_out", dslUpstreamSpeed, xml);	//DSL Upstream Speed
		
		addDropdownValues_commonMethod(application, "MBS", "MBSdropdown_out", mbsDropdown, xml);	//MBS
		
		WebElement interfaceHeader = getwebelement(xml.getlocator("//locators/" + application + "/PEdevice_interfaceHeaderName"));
		scrolltoview(interfaceHeader);
		Thread.sleep(2000);
		
		warningMessage_commonMethod(application, "addInterace_vpi_warningMessage_out", "VPI", xml);	  //VPI warning Message
		addtextFields_commonMethod(application, "VPI", "vpiTextFild_out", vpi, xml);		//VPI
		
		warningMessage_commonMethod(application, "addInterface_vci_warningMessage", "VCI", xml);   //VCI warning Message
		addtextFields_commonMethod(application, "VCI", "vciTextField_out", vci, xml);		//VCI
		
		warningMessage_commonMethod(application, "addInterface_PE_slot_warningMessage", "Slot", xml);  	//Slot warning message
		addDropdownValues_commonMethod(application, "Slot", "slotTextField_out", slot, xml);	//Slot
		
		warningMessage_commonMethod(application, "addInterface_PE_port_warningmEssage", "Port", xml);  //Port warning Message
		addDropdownValues_commonMethod(application, "Port", "portTextField_out", port, xml);	//Port
		
		
		addCheckbox_commonMethod(application, "IVmanagement_checkbox_out", "IV Management", ivManagement, "no", xml);
		
		
		scrolltoend();
		Thread.sleep(1000);
		
		//perform Generate configuration
		boolean configurationpanel=false;
		configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
		if(configurationpanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
			System.out.println("'Configuration' panel is displaying");
			
			click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
			Thread.sleep(2000);
			
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
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			System.out.println("'Configuration' panel is not displaying");
		}

	scrolltoend();
	Thread.sleep(2000);
	click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
		
	}


	/**
	 * Add Multilink for PE device
	 * @param application
	 * @param existingAddressRangeSelection
	 * @param newAddressRangeSelection
	 * @param Country
	 * @param SubnetSize
	 * @param availableBlocks
	 * @throws DocumentException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void addMultilink(String application, String existingAddressRangeSelection, String newAddressRangeSelection, String country, String SubnetSize,
		String availableBlocks, String address, String newInterfaceAddressRangeValue, String encapsulation,
		String dslDownstreamSpeed, String dslUpstreamSpeed) throws InterruptedException, DocumentException, IOException {
		
		Thread.sleep(3000);
		scrolltoend();
		
		click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton", xml);    //click on Execute and Save button to check warning message
		
		scrollToTop();
		
		//Interface
//		warningMessage_commonMethod(application, "InterfaceName_warningmEssage_out", "Interface", xml);
//		addtextFields_commonMethod(application, "Interface", "multilink_interfacefield_out", multilink_InterfaceName, xml);	//Interface
		
		if(existingAddressRangeSelection.equalsIgnoreCase("Yes") && newAddressRangeSelection.equalsIgnoreCase("No"))
		{
			click_commonMethod(application, "Get subnets", "getSubnets_out", xml);
			
			
			//EIP Allocation
			click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6" , xml);
			
			addDropdownValues_commonMethod(application, "Country", "countryDropdown_EIpallocation", country, xml);
			
			addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", SubnetSize, xml);
			
		try {	
			boolean availableBlock=getwebelement(xml.getlocator("//locators/" + application + "/availableBlocks_dropdown")).isDisplayed();
			if(availableBlock) {
				
				String EIpmessage=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availableBlocksMessage_out")));
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Available Block dropdown is not displaying. Message displays as "+EIpmessage);
				System.out.println("Available Block dropdown is not dipslaying. Message displays as "+EIpmessage);
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
			}else {
				addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown" , availableBlocks, xml);
				
				click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton" , xml);
				
				verifysuccessmessage(application, "successfully allocated");
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
				
				
				
			}	
		}catch(Exception e) {
				e.printStackTrace();
				String EIpmessage=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/availableBlocksMessage_out")));
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Available Block dropdown is not displaying. Message displays as "+EIpmessage);
				System.out.println("Available Block dropdown is not dipslaying. Message displays as "+EIpmessage);
				
				click_commonMethod(application, "x", "EIPallocation_xButton", xml);
			}
	
	//check value in Interface Address Range dropdown	
		try {
		String interfaceAddressRange=getwebelement(xml.getlocator("//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6")).getAttribute("value");
		
		if(interfaceAddressRange.isEmpty()) {
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface Address Range' dropdown");
			System.out.println("No values displaying under 'Interface Address Range' dropdown");
			
		}else {
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
			System.out.println("'Interface Address Range' dropdown value displays as: "+ interfaceAddressRange);
			
			click_commonMethod(application, ">>" , "rightArrowButton_interfaceAddressRangeIPv6", xml);
			
			String addressValue=null;
		
			addDropdownValues_commonMethod(application, "Address", "FetchInterfaceDropdown_addInterface" , address, xml);
		}	
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Address' dropdown");
			System.out.println("No values displaying under 'Address' dropdown");
			
		}
		}	
		else if(existingAddressRangeSelection.equalsIgnoreCase("No") && newAddressRangeSelection.equalsIgnoreCase("Yes"))
		{
			addtextFields_commonMethod(application, "Interface Address Range", "newInterfaceAddressRange_textField", newInterfaceAddressRangeValue, xml);
		}
		
		
	//Encapsulation
		warningMessage_commonMethod(application, "addMultilink_PE_encapsulation_warningMessage", "Encapsulation", xml);	//Encapsulation warning Message
		addDropdownValues_commonMethod(application, "Encapsulation", "encapsultionDropdown_out", encapsulation, xml);	//Encapsulation
		
	
	//DSL Downstream Speed (PCR)
		warningMessage_commonMethod(application, "downloadDSrdropdown_warningMessage", "DSL Downstream Speed (PCR)", xml);
		addDropdownValues_commonMethod(application, "DSL Downstream Speed (PCR)", "addMultilink_dslDownstreamSpeed", dslDownstreamSpeed, xml);
		
		
	//DSL Upstream Speed (SCR)
		addDropdownValues_commonMethod(application, "DSL Upstream Speed (SCR)", "addmultilink_dslUpstreamSpeed_out", dslUpstreamSpeed, xml);
		
		
	//perform Generate configuration
		scrolltoend();
		Thread.sleep(1000);
		
		
		boolean configurationpanel=false;
		configurationpanel=getwebelement(xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
		if(configurationpanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
			System.out.println("'Configuration' panel is displaying");
			
			click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
			Thread.sleep(2000);
			
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
		
		}else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			System.out.println("'Configuration' panel is not displaying");
		}

	scrolltoend();
	Thread.sleep(2000);
	click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton" , xml);
	}

	
	public void clickOnAddLoopback_PE(String application) throws InterruptedException, DocumentException {

		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);
		
		WebElement siteLabelName = getwebelement(
				xml.getlocator("//locators/" + application + "/viewPEdevice_siteLabelName"));
		scrolltoview(siteLabelName);
		Thread.sleep(1000);

		click_commonMethod(application, "Action", "InterfacePanel_actionDropdown", xml);

		click_commonMethod(application, "Add Loopback", "addLoopback_PE_out", xml);
	}

	public void addLoopback(String application, String interfaceAddress, String ivManagement)
			throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(1000);
		
		// Interface name text field
		String interfacevalue = getwebelement(xml.getlocator("//locators/" + application + "/InterfaceField_out"))
				.getAttribute("value");
		if (interfacevalue.isEmpty()) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Interface' text field");
			System.out.println("No values displaying under 'Interface' text field");
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Interface value is displaying as: " + interfacevalue);
			System.out.println("Interface value is displaying as: " + interfacevalue);
		}

		
		scrolltoend();
		click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton", xml);
		Thread.sleep(1000);
		
		scrollToTop();
		
		// Interface Address
		warningMessage_commonMethod(application, "addLoopback_PE_addInterface_out", "Interface Address", xml);
		addtextFields_commonMethod(application, "Interface Address", "addLoopback_addInterfcae_textField_out",
				interfaceAddress, xml);

		
		//IV Management
		addCheckbox_commonMethod(application, "IVmanagement_checkbox_out", "IV Management", ivManagement, "no", xml);

		// perform Generate configuration
		scrolltoend();
		Thread.sleep(1000);
		boolean configurationpanel = false;
		configurationpanel = getwebelement(
				xml.getlocator("//locators/" + application + "/addInterface_confiugrationPanelheader")).isDisplayed();
		if (configurationpanel) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
			System.out.println("'Configuration' panel is displaying");

			click_commonMethod(application, "Generate", "addInterface_generateLink", xml);
			Thread.sleep(2000);

			scrolltoend();
			Thread.sleep(1000);

			String configurationvalues = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/addInterface_configurationtextArea")));
			if (configurationvalues.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
				System.out.println(
						"After clicking on 'Generate' button, no values displaying under 'Configuration' text box");
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: " + configurationvalues);
				System.out.println("After clicking on 'Generate' button, "
						+ "under 'Configuration' textbox values displaying as: " + configurationvalues);
			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			System.out.println("'Configuration' panel is not displaying");
		}

//				addCheckbox_commonMethod(application, xpath, "Configure on backup BRX", expectedValue, DefaultSelection, xml);

		scrolltoend();
		Thread.sleep(2000);
		click_commonMethod(application, "Execute and Save", "addInterface_executeAndSaveButton", xml);
	}

	public String fetchCityName(String application) throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		Thread.sleep(1000);

		WebElement devicenameElement = getwebelement(xml.getlocator("//locators/" + application + "/xngCityName_out"));
		String cityname = Gettext(devicenameElement);

		return cityname;
	}

	public void addCPEdevice_CustomerPremiseEquipment(String application, String routerId, String vendorModel,
			String snmpV3Contextname, String managementAddress, String snmpro, String snmprw,
			String snmpV3SecurityUsername, String snmpv3AuthPassword, String snmpV3ContextEngineId,
			String snmpV3AuthProto, String existingpremiseSelection, String NewPremiseSelection,
			String existingpremisevalue, String premisename, String premisecode)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "addCPEdevice_CustomerPremiseEquipment");
		
		waitForpageload();
		waitforPagetobeenable();
		
		scrolltoend();
		click_commonMethod(application, "Next", "AddnewlinkPe2CPE_Nextbutton", xml); // click on Next button without
																						// select any value in dropdown

		scrollToTop();
		Thread.sleep(1000);
		
		// Router Id
		addtextFields_commonMethod(application, "Router Id", "CEdevice_AddrouterId_out", routerId, xml);

		// Vendor/Model
		addDropdownValues_commonMethod(application, "Vendor/Model", "CPEdveice_vendorModel_out", vendorModel, xml);

		// Management Address
		addtextFields_commonMethod(application, "Management Address", "CPEdevice_managementAddress_out",
				managementAddress, xml);

		// Snmpro
		addtextFields_commonMethod(application, "Snmpro", "CPEdevice_snmpro_out", snmpro, xml);

		// Snmprw
		addtextFields_commonMethod(application, "Snmprw", "CPEdevice_snmprw_out", snmprw, xml);

		// Snmp V3 Context Name
		addtextFields_commonMethod(application, "Snmp V3 Context Name", "CPEdevice_snmpV3contextname",
				snmpV3Contextname, xml);

		// Snmp V3 Context Engine ID
		addtextFields_commonMethod(application, "Snmp V3 Context Engine ID", "CPEdevice_SNMpv3ContextEngineID",
				snmpV3ContextEngineId, xml);

		scrolltoend();
		Thread.sleep(1000);

		// Snmp V3 Security Username
		addtextFields_commonMethod(application, "Snmp V3 Security Username", "CPEdevice_SnmpV3SecurityUsername",
				snmpV3SecurityUsername, xml);

		// Snmp V3 Auth Password
		addtextFields_commonMethod(application, "Snmp V3 Auth Password", "CPEdevice_SNMpv3AuthPassword",
				snmpv3AuthPassword, xml);

		// Snmp V3 Auth Proto
		addDropdownValues_commonMethod(application, "Snmp V3 Auth Proto", "CPEdevice_SNMPv3AuthProto_out",
				snmpV3AuthProto, xml);

		// Premise
		if (existingpremiseSelection.equalsIgnoreCase("yes") & NewPremiseSelection.equalsIgnoreCase("no")) {
			addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", existingpremisevalue, xml);
		}

		// New Premise
		else if (existingpremiseSelection.equalsIgnoreCase("no") & NewPremiseSelection.equalsIgnoreCase("yes")) {

			click_commonMethod(application, "Select Premise Toggle", "CPEdevice_addCPE_selectpremise_out", xml);
			// Premise name
			addtextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected",
					premisename, xml);
			// Premise Code
			addtextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected",
					premisecode, xml);
		}

		scrolltoend();
		Thread.sleep(1000);

		click_commonMethod(application, "Next", "AddnewlinkPe2CPE_Nextbutton", xml);

	}

	public void clickOnEditLink_CPEdevice(String application, String devicename) {

	}

	public void editCPEdevice_CustomerPremiseEquipment(String application, String routerId, String vendorModel,
			String snmpV3Contextname, String managementAddress, String snmpro, String snmprw,
			String snmpV3SecurityUsername, String snmpv3AuthPassword, String snmpV3ContextEngineId,
			String snmpV3AuthProto, String existingpremiseSelection, String NewPremiseSelection,
			String existingpremisevalue, String premisename, String premisecode)
			throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(1000);
		
		// Router Id
		edittextFields_commonMethod(application, "Router Id", "CEdevice_AddrouterId_out", routerId, xml);

		// Vendor/Model
		addDropdownValues_commonMethod(application, "Vendor/Model", "CPEdveice_vendorModel_out", vendorModel, xml);

		// Management Address
		edittextFields_commonMethod(application, "Management Address", "CPEdevice_managementAddress_out",
				managementAddress, xml);

		// Snmpro
		edittextFields_commonMethod(application, "Snmpro", "CPEdevice_snmpro_out", snmpro, xml);

		// Snmprw
		edittextFields_commonMethod(application, "Snmprw", "CPEdevice_snmprw_out", snmprw, xml);

		// Snmp V3 Context Name
		edittextFields_commonMethod(application, "Snmp V3 Context Name", "CPEdevice_snmpV3contextname",
				snmpV3Contextname, xml);

		// Snmp V3 Context Engine ID
		edittextFields_commonMethod(application, "Snmp V3 Context Engine ID", "CPEdevice_SNMpv3ContextEngineID",
				snmpV3ContextEngineId, xml);

		scrolltoend();
		Thread.sleep(1000);

		// Snmp V3 Security Username
		edittextFields_commonMethod(application, "Snmp V3 Security Username", "CPEdevice_SnmpV3SecurityUsername",
				snmpV3SecurityUsername, xml);

		// Snmp V3 Auth Password
		edittextFields_commonMethod(application, "Snmp V3 Auth Password", "CPEdevice_SNMpv3AuthPassword",
				snmpv3AuthPassword, xml);

		// Snmp V3 Auth Proto
		addDropdownValues_commonMethod(application, "Snmp V3 Auth Proto", "CPEdevice_SNMPv3AuthProto_out",
				snmpV3AuthProto, xml);

		// Premise
		if (existingpremiseSelection.equalsIgnoreCase("yes") & NewPremiseSelection.equalsIgnoreCase("no")) {
			addDropdownValues_commonMethod(application, "Premise", "premisedropdowninput", existingpremisevalue, xml);
		}

		// New Premise
		else if (existingpremiseSelection.equalsIgnoreCase("no") & NewPremiseSelection.equalsIgnoreCase("yes")) {

			click_commonMethod(application, "Select Premise Toggle", "CPEdevice_addCPE_selectpremise_out", xml);
			// Premise name
			edittextFields_commonMethod(application, "Premise Name", "premisenameinputfield_addCityToggleSelected",
					premisename, xml);
			// Premise Code
			edittextFields_commonMethod(application, "Premise Code", "premisecodeinputfield_addCityToggleSelected",
					premisecode, xml);
		}

		scrolltoend();
		Thread.sleep(1000);

		click_commonMethod(application, "Next", "AddnewlinkPe2CPE_Nextbutton", xml);

	}

	public void verifyCPEdevice(String application, String vendorModel, String managementAddress,
			String snmproEditedValue) throws InterruptedException, DocumentException {

		compareText_InViewPage(application, "Vendor/Model", vendorModel, xml); // Vendor Model

		compareText_InViewPage(application, "Management Address", managementAddress, xml); // Management Address

		// Snmpro
		if (snmproEditedValue.equalsIgnoreCase("null")) {
			compareText_InViewPage(application, "Snmpro", "incc", xml);
		} else {
			compareText_InViewPage(application, "Snmpro", snmproEditedValue, xml);
		}
	}

	public void addInterface_CPEdevice(String application, String interfaceValue, String networkValue,
			String existingAddressRangeSelection, String newAddressRangeSelection, String country, String SubnetSize,
			String availableBlocks, String newInterfaceAddressRangeValue, String addressDropdownValue,
			String IV64bitCounterValue, String ethernet, String speed, String duplex, String clockSource)
			throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(1000);

		boolean addInterfacePanelheader = getwebelement(
				xml.getlocator("//locators/" + application + "/CPE_network_addInterface_out")).isDisplayed();
		if (addInterfacePanelheader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Interface' panel is displaying");
			System.out.println("'Add Interface' panel is displaying");

			scrolltoend();

			click_commonMethod(application, "OK", "CPE_addInterface_OKbutton_out", xml);

			// Interface
			warningMessage_commonMethod(application, "InterfaceName_warningmEssage_out", "Interface", xml);
			addtextFields_commonMethod(application, "Interface", "InterfaceField_out", interfaceValue, xml);

			// Network dropdown
			addDropdownValues_commonMethod(application, "Network", "CPE_network_addInterface_out", networkValue, xml);

			if (networkValue.equals("WAN")) {

				WebElement addressValue_WAN = getwebelement(
						xml.getlocator("//locators/" + application + "/CPE_network_addInterface_out"));
				String WANaddressValue = Gettext(addressValue_WAN);

				if (WANaddressValue.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Address' text field");
					System.out.println("No values displaying under 'Address' text field");

					addtextFields_commonMethod(application, "Interface Address Range",
							"newInterfaceAddressRange_textField", newInterfaceAddressRangeValue, xml);
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"On selecting 'WAN' value under 'Network' dropdown,Address value is displaying as: "
									+ WANaddressValue);
					System.out.println(
							"On selecting 'WAN' value under 'Network' dropdown,Address value is displaying as: "
									+ WANaddressValue);

					addtextFields_commonMethod(application, "Interface Address Range",
							"newInterfaceAddressRange_textField", newInterfaceAddressRangeValue, xml);
				}
			} else if (networkValue.equals("LAN")) {
				if (existingAddressRangeSelection.equalsIgnoreCase("Yes")
						&& newAddressRangeSelection.equalsIgnoreCase("No")) {
					click_commonMethod(application, "Get subnets", "getSubnets_out", xml);

					// EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6", xml);

					addDropdownValues_commonMethod(application, "Country", "countryDropdown_EIpallocation", country,
							xml);

					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", SubnetSize, xml);

					try {
						boolean availableBlock = getwebelement(
								xml.getlocator("//locators/" + application + "/availableBlocks_dropdown"))
										.isDisplayed();
						if (availableBlock) {

							String EIpmessage = Gettext(getwebelement(
									xml.getlocator("//locators/" + application + "/availableBlocksMessage_out")));

							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"Available Block dropdown is not displaying. Message displays as " + EIpmessage);
							System.out.println(
									"Available Block dropdown is not dipslaying. Message displays as " + EIpmessage);

							click_commonMethod(application, "x", "EIPallocation_xButton", xml);

						} else {
							addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown",
									availableBlocks, xml);

							click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton", xml);

							verifysuccessmessage(application, "successfully allocated");

							click_commonMethod(application, "x", "EIPallocation_xButton", xml);

						}
					} catch (Exception e) {
						e.printStackTrace();
						String EIpmessage = Gettext(getwebelement(
								xml.getlocator("//locators/" + application + "/availableBlocksMessage_out")));
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								"Available Block dropdown is not displaying. Message displays as " + EIpmessage);
						System.out.println(
								"Available Block dropdown is not dipslaying. Message displays as " + EIpmessage);

						click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					}

					// check value in Interface Address Range dropdown
					try {
						String interfaceAddressRange = getwebelement(xml.getlocator(
								"//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6"))
										.getAttribute("value");

						if (interfaceAddressRange.isEmpty()) {

							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"No values displaying under 'Interface Address Range' dropdown");
							System.out.println("No values displaying under 'Interface Address Range' dropdown");

						} else {

							ExtentTestManager.getTest().log(LogStatus.PASS,
									"'Interface Address Range' dropdown value displays as: " + interfaceAddressRange);
							System.out.println(
									"'Interface Address Range' dropdown value displays as: " + interfaceAddressRange);

							String addressValue = null;

							addDropdownValues_commonMethod(application, "Address",
									"FetchInterfaceDropdown_addInterface", addressDropdownValue, xml);
						}
					} catch (Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Address' dropdown");
						System.out.println("No values displaying under 'Address' dropdown");

					}
				} else if (existingAddressRangeSelection.equalsIgnoreCase("No")
						&& newAddressRangeSelection.equalsIgnoreCase("No")) {
					addtextFields_commonMethod(application, "Interface Address Range",
							"newInterfaceAddressRange_textField", newInterfaceAddressRangeValue, xml);
				}
			}

			// IV 64 Bit Counter
			addCheckbox_commonMethod(application, "CPE_IV64bitCounter_out", "IV 64 Bit Counter", IV64bitCounterValue,
					"no", xml);

			// Ethernet
			addCheckbox_commonMethod(application, "CPE_addInterface_ethernet_out", "Ethernet", ethernet, "no", xml);

			if (ethernet.equalsIgnoreCase("Yes")) {

				addDropdownValues_commonMethod(application, "Speed", "CPE_addInterface_speed_out", speed, xml); // Speed

				addDropdownValues_commonMethod(application, "Duplex", "CPE_addInterface_duplex_out", duplex, xml); // Duplex
			} else {
				addDropdownValues_commonMethod(application, "clockSource", "CPE_addInterface_clockSource_out",
						clockSource, xml); // Clock Source
			}

			scrolltoend();
			click_commonMethod(application, "OK", "CPE_addInterface_OKbutton_out", xml);

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Interface' panel is not displaying");
			System.out.println("'Add Interface' panel is not displaying");
		}
	}

	public void addMultilink_CPEdevice(String application, String interfaceValue, String networkValue,
			String existingAddressRangeSelection, String newAddressRangeSelection, String country, String SubnetSize,
			String availableBlocks, String newInterfaceAddressRangeValue, String addressDropdownValue,
			String IV64bitCounterValue, String ethernet, String speed, String duplex, String clockSource)
			throws InterruptedException, DocumentException, IOException {

		scrollToTop();
		Thread.sleep(1000);

		boolean addInterfacePanelheader = getwebelement(
				xml.getlocator("//locators/" + application + "/CPEdevice_addMultilinkPanelHeader")).isDisplayed();
		if (addInterfacePanelheader) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Add Multilink' panel is displaying");
			System.out.println("'Add Multilink' panel is displaying");

			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "CPE_addInterface_OKbutton_out", xml);
			
			waitForpageload();  waitforPagetobeenable();
			
			scrollToTop();
			Thread.sleep(1000);
			// Interface
			warningMessage_commonMethod(application, "InterfaceName_warningmEssage_out", "Interface", xml);
			addtextFields_commonMethod(application, "Interface", "CPEdevice_addMultilink_InterfaceTextfield", interfaceValue, xml);

			// Network dropdown
			addDropdownValues_commonMethod(application, "Network", "CPE_network_addInterface_out", networkValue, xml);
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(2000);

			if (networkValue.equals("WAN")) {

				WebElement addressValue_WAN = getwebelement(
						xml.getlocator("//locators/" + application + "/CPEdevice_addMultilink_addressTextField"));
				String WANaddressValue = Gettext(addressValue_WAN);

				if (WANaddressValue.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Address' text field");
					System.out.println("No values displaying under 'Address' text field");

					addtextFields_commonMethod(application, "Interface Address Range",
							"newInterfaceAddressRange_textField", newInterfaceAddressRangeValue, xml);
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"On selecting 'WAN' value under 'Network' dropdown,Address value is displaying as: "
									+ WANaddressValue);
					System.out.println(
							"On selecting 'WAN' value under 'Network' dropdown,Address value is displaying as: "
									+ WANaddressValue);

					addtextFields_commonMethod(application, "Interface Address Range",
							"newInterfaceAddressRange_textField", newInterfaceAddressRangeValue, xml);
				}
			} else if (networkValue.equals("LAN")) {
				if (existingAddressRangeSelection.equalsIgnoreCase("Yes")
						&& newAddressRangeSelection.equalsIgnoreCase("No")) {
					click_commonMethod(application, "Get subnets", "getSubnets_out", xml);

					// EIP Allocation
					click_commonMethod(application, "EIP Allocation", "EIPallocation_addInterfaceIpv6", xml);

					addDropdownValues_commonMethod(application, "Country", "countryDropdown_EIpallocation", country,
							xml);

					addDropdownValues_commonMethod(application, "Subnet Size", "subnetSize_dropdown", SubnetSize, xml);

					try {
						boolean availableBlock = getwebelement(
								xml.getlocator("//locators/" + application + "/availableBlocks_dropdown"))
										.isDisplayed();
						if (availableBlock) {

							String EIpmessage = Gettext(getwebelement(
									xml.getlocator("//locators/" + application + "/availableBlocksMessage_out")));

							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"Available Block dropdown is not displaying. Message displays as " + EIpmessage);
							System.out.println(
									"Available Block dropdown is not dipslaying. Message displays as " + EIpmessage);

							click_commonMethod(application, "x", "EIPallocation_xButton", xml);

						} else {
							addDropdownValues_commonMethod(application, "Available Blocks", "availableBlocks_dropdown",
									availableBlocks, xml);

							click_commonMethod(application, "Allocate Subnet", "allocateSubnetButton", xml);

							verifysuccessmessage(application, "successfully allocated");

							click_commonMethod(application, "x", "EIPallocation_xButton", xml);

						}
					} catch (Exception e) {
						e.printStackTrace();
						String EIpmessage = Gettext(getwebelement(
								xml.getlocator("//locators/" + application + "/availableBlocksMessage_out")));
						ExtentTestManager.getTest().log(LogStatus.FAIL,
								"Available Block dropdown is not displaying. Message displays as " + EIpmessage);
						System.out.println(
								"Available Block dropdown is not dipslaying. Message displays as " + EIpmessage);

						click_commonMethod(application, "x", "EIPallocation_xButton", xml);
					}

					//check value in Interface Address Range dropdown
					try {
						String interfaceAddressRange = getwebelement(xml.getlocator(
								"//locators/" + application + "/fetchInterfaceAddressRangeDropdown_addInterfaceIPv6"))
										.getAttribute("value");

						if (interfaceAddressRange.isEmpty()) {

							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"No values displaying under 'Interface Address Range' dropdown");
							System.out.println("No values displaying under 'Interface Address Range' dropdown");

						} else {

							ExtentTestManager.getTest().log(LogStatus.PASS,
									"'Interface Address Range' dropdown value displays as: " + interfaceAddressRange);
							System.out.println(
									"'Interface Address Range' dropdown value displays as: " + interfaceAddressRange);

							String addressValue = null;

							addDropdownValues_commonMethod(application, "Address",
									"FetchInterfaceDropdown_addInterface", addressDropdownValue, xml);
						}
					} catch (Exception e) {
						e.printStackTrace();
						ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Address' dropdown");
						System.out.println("No values displaying under 'Address' dropdown");

					}
				} else if (existingAddressRangeSelection.equalsIgnoreCase("No")
						&& newAddressRangeSelection.equalsIgnoreCase("No")) {
					addtextFields_commonMethod(application, "Interface Address Range",
							"newInterfaceAddressRange_textField", newInterfaceAddressRangeValue, xml);
				}
			}

			// IV 64 Bit Counter
			addCheckbox_commonMethod(application, "CPE_IV64bitCounter_out", "IV 64 Bit Counter", IV64bitCounterValue,
					"no", xml);

			// Ethernet
			addCheckbox_commonMethod(application, "CPE_addInterface_ethernet_out", "Ethernet", ethernet, "no", xml);

			if (ethernet.equalsIgnoreCase("Yes")) {

				addDropdownValues_commonMethod(application, "Speed", "CPE_addInterface_speed_out", speed, xml); // Speed

				addDropdownValues_commonMethod(application, "Duplex", "CPE_addInterface_duplex_out", duplex, xml); // Duplex
			} else {
				addDropdownValues_commonMethod(application, "clockSource", "CPE_addInterface_clockSource_out",
						clockSource, xml); // Clock Source
			}

			// Multilink Bearer Panel
			try {
				boolean multilinkBearerPanel = getwebelement(
						xml.getlocator("//locators/" + application + "/multilinkBearerPanel_addMultilink"))
								.isDisplayed();
				if (multilinkBearerPanel) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Multilink Beraer' panel is displaying");
					System.out.println("Multilink Beraer' panel is displaying");
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Multilink Beraer' panel is not displaying");
					System.out.println("Multilink Beraer' panel is ont displaying");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Multilink Beraer' panel is not displaying");
				System.out.println("Multilink Beraer' panel is ont displaying");
			}

			scrolltoend();
			click_commonMethod(application, "OK", "CPE_addInterface_OKbutton_out", xml);

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add Multilink' panel is not displaying");
			System.out.println("'Add Multilink' panel is not displaying");
		}
	}

	public void cickOnEditButton_CPEdevice(String application, String devicename) throws Exception {

		Thread.sleep(3000);

		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);
		
		scrolltoend();
		Thread.sleep(1000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edit CPE device");

		WebElement editLink = getwebelement(
				xml.getlocator("//locators/" + application + "/clickOnEditLink_CPEdevice_out").replace("value",
						devicename));
		safeJavaScriptClick(editLink);

	}
	
	
	public void cickOnViewButton_CPEdevice(String application, String devicename) throws Exception {

		Thread.sleep(3000);
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);
		
		scrolltoend();
		Thread.sleep(1000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Edit CPE device");

		WebElement editLink = getwebelement(
				xml.getlocator("//locators/" + application + "/clickOnViewLink_CPEdevice_out").replace("value",
						devicename));
		safeJavaScriptClick(editLink);

	}
	
	
	
	public void clickOnFetchDeviceInterfaec_CPEdevice(String application, String devicename) throws Exception {

		Thread.sleep(3000);

		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);
		
		scrolltoend();
		Thread.sleep(1000);

		WebElement fetchDeviceInterfaceLink = getwebelement(
				xml.getlocator("//locators/" + application + "/clickOnFetchDeviceInterface_CPEdevice").replace("value",
						devicename));
		safeJavaScriptClick(fetchDeviceInterfaceLink);

	}

	public void clickOnpppConfigurationButton_CPEdevice(String application, String devicename) throws Exception {

		Thread.sleep(12000);

		scrolltoend();
		Thread.sleep(2000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying PPP Configuration");

		WebElement pppconfigLink = getwebelement(
				xml.getlocator("//locators/" + application + "/clickOnPPPconfigurationLink_CPedveice").replace("value",
						devicename));
		safeJavaScriptClick(pppconfigLink);

	}
	
	
	public void clickOnViewButton_CPEdevice(String application, String devicename) throws Exception {

		Thread.sleep(12000);

		scrolltoend();
		Thread.sleep(2000);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying PPP Configuration");

		WebElement viewLink = getwebelement(
				xml.getlocator("//locators/" + application + "/clickOnViewLink_CPEdevice_out").replace("value",
						devicename));
		safeJavaScriptClick(viewLink);

	}

	public String fetchdeviceNameFromviewDevicePage(String application)
			throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		
		scrollToTop();
		Thread.sleep(1000);

		String actualDeviceName = null;

		// Fetch device name
		WebElement deviceName = getwebelement(xml.getlocator("//locators/" + application + "/fetchCPEdeviceName_out"));
		String selecteddevicename = deviceName.getAttribute("title");

		if (selecteddevicename.contains("...")) {
			actualDeviceName = selecteddevicename.substring(0, 10);
		} else {
			actualDeviceName = selecteddevicename;
		}

		System.out.println("device name fetched is: "+actualDeviceName);
		return actualDeviceName;
	}
	
//	
//	public String fetchdeviceTitleFromviewDevicePage(String application)
//			throws InterruptedException, DocumentException, IOException {
//
//		waitForpageload();
//		waitforPagetobeenable();
//		
//		scrollToTop();
//		Thread.sleep(1000);
//
//		String actualDeviceName = null;
//
//		// Fetch device name
//		WebElement deviceName = getwebelement(xml.getlocator("//locators/" + application + "/fetchCPEdeviceName_out"));
//		String selecteddevicename = deviceName.getAttribute("Value");
//
//		if (selecteddevicename.contains("...")) {
//			actualDeviceName = selecteddevicename.substring(0, 10);
//		} else {
//			actualDeviceName = selecteddevicename;
//		}
//
//		System.out.println("device name fetched is: "+actualDeviceName);
//		return actualDeviceName;
//	}


	public void pppConfiguration(String application) throws InterruptedException, DocumentException {

		waitforPagetobeenable();

		WebElement pppConfigurationPanelHeader = getwebelement(
				xml.getlocator("//locators/" + application + "/pppConfigurationPanelheader"));

		if (pppConfigurationPanelHeader.isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'PPP Configuration' panel is displaying");
			System.out.println(" 'PPP Configuration' panel is displaying");

			boolean clickHereLink = getwebelement(
					xml.getlocator("//locators/" + application + "/pppConfiguration_CPEdevice_clickHereLink"))
							.isDisplayed();
			if (clickHereLink) {
				click_commonMethod(application, "Click here to add", "pppConfiguration_CPEdevice_clickHereLink", xml);
			} else {

				ExtentTestManager.getTest().log(LogStatus.FAIL, "No links displayed for creating new PPP Configuration");

			}

		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, " 'PPP Configuration' panel is not displaying");
			System.out.println(" 'PPP Configuration' panel is not displaying");

		}
	}

	public void addPPPconfiguration(String application, String expectedDevicename, String manualPasswordvalue,
			String framedWANipAddress, String framedRoute0, String framedRoute1, String framedRoute2,
			String framedRoute3, String framedRoute4, String framedRoute5, String framedRoute6, String framedRoute7,
			String uniVirtualRouterName, String uniLoopbackInteface)
			throws InterruptedException, DocumentException, IOException {

		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Add PPP Configuration");
		
		WebElement adpppConfigurationPanelHeader = getwebelement(
				xml.getlocator("//locators/" + application + "/addPPPconfigurationPanel_CPedevice_out"));

		if (adpppConfigurationPanelHeader.isDisplayed()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add PPP Configuration' panel is displaying");
			System.out.println(" 'Add PPP Configuration' panel is displaying");

			// Device Name
			String actualDevicename = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfiguration_deviceName")));
			if (expectedDevicename.equalsIgnoreCase(actualDevicename)) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Device name is displaying as " + actualDevicename + " as expected");
				System.out.println("Device name is displaying as " + actualDevicename + " as expected");
			} else if (expectedDevicename.contains(actualDevicename)) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Device name is displaying as " + actualDevicename + " as expected");
				System.out.println("Device name is displaying as " + actualDevicename + " as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Device name is displaying as " + actualDevicename
						+ ". But the expected value is " + expectedDevicename);
				System.out.println("Device name is displaying as " + actualDevicename + ". But the expected value is "
						+ expectedDevicename);
			}

			// PPP Password
			click_commonMethod(application, "generate password", "addPPPconfiguration_generatePassowrd", xml);

			String passwordField = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfiguration_passwordField")).getAttribute("value");
			if (passwordField.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No value displaying under 'Password' field");
				System.out.println("No value displaying under 'Password' field");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"value displaying under 'Password' field is displaying as " + passwordField);
				System.out.println("value displaying under 'Password' field is displaying as " + passwordField);

				// Enter password manually
				addtextFields_commonMethod(application, "PPP Password", "addPPPconfiguration_passwordField",
						manualPasswordvalue, xml);
			}

			// Framed/WAN/IP Address
			addtextFields_commonMethod(application, "Framed/WAN/IP Address", "addPPPcofig_famedWANipAddress",
					framedWANipAddress, xml);

			// Framed Protocol
			String framedProtocol = Gettext(
					getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_framedProtocol")));
			if (framedProtocol.equals("PPP")) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed protocol value is displaying as " + framedProtocol + " as expected");
				System.out.println("Framed protocol value is displaying as " + framedProtocol + " as expected");
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Framed protocol value is displaying as " + framedProtocol + ". Expected value is 'PPP'");
				System.out.println(
						"Framed protocol value is displaying as " + framedProtocol + ". Expected value is 'PPP'");

			}

			// Framed Route0
			addtextFields_commonMethod(application, "Framed Route0", "addPPPconfig_framedRouter0", framedRoute0, xml);

			// Framed Router1
			addtextFields_commonMethod(application, "Framed Route1", "addPPPconfig_framedRouter1", framedRoute1, xml);

			// Framed Route2
			addtextFields_commonMethod(application, "Framed Route2", "addPPPconfig_framedroute2", framedRoute2, xml);

			// Framed Route3
			addtextFields_commonMethod(application, "Framed Route3", "addPPPconfig_framedRoute3", framedRoute3, xml);

			// Framed Route4
			addtextFields_commonMethod(application, "Framed Route4", "addPPconfig_framedRoute4", framedRoute4, xml);

			// Framed Router5
			addtextFields_commonMethod(application, "Framed Route5", "addPPPconfig_framedRoute5", framedRoute5, xml);

			// Framed route6
			addtextFields_commonMethod(application, "Framed Route6", "addPPPconfig_framedRoute6", framedRoute6, xml);

			// Framed route7
			addtextFields_commonMethod(application, "Framed Route7", "addPPPconfig_framedRoute7", framedRoute7, xml);

			// Uni Virtual Route Name
			String uniVirtualActualValue = getwebelement(xml.getlocator("//locators/" + application + "/addPPPconfig_uniVirtualRouterName")).getAttribute("value");
			if (uniVirtualActualValue.isEmpty()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Uni Virtual Route Name'");
				System.out.println("No values dipslaying under 'Uni Virtual Route Name'");

				addtextFields_commonMethod(application, "Uni Virtual Route Name", "addPPPconfig_uniVirtualRouterName",
						uniVirtualRouterName, xml);

			} else {
				edittextFields_commonMethod(application, "Uni Virtual Route Name", "addPPPconfig_uniVirtualRouterName",
						uniVirtualRouterName, xml);
			}

			// Uni Local Loopback Interface
			edittextFields_commonMethod(application, "Uni Local Loopback Interface",
					"addPPPconfig_uniLocalLoopbackInterface", uniLoopbackInteface, xml);

			
			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "CPE_addInterface_OKbutton_out", xml); // OK button

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, " 'Add PPP Configuration' panel is not displaying");
			System.out.println(" 'Add PPP Configuration' panel is not displaying");
		}
	}
	
	
	public void dump_viewServicepage(String application) throws InterruptedException, DocumentException, IOException {
		
		 WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
			ScrolltoElement(orderPanel);
			Thread.sleep(3000);
		
			click_commonMethod(application, "Action", "Editservice_actiondropdown", xml);
		   Thread.sleep(3000);
		   click_commonMethod(application, "Dump", "Editservice_Dumplink", xml);
		   Thread.sleep(2000);
	
		   waitForpageload();
		   
		   waitforPagetobeenable();
		   
		   WebElement dumpelement= getwebelement(xml.getlocator("//locators/" + application + "/dump_container"));
		   
		   String dumpvalue=dumpelement.getText();
		  
		   if(dumpvalue.isEmpty()) {
			   
			   ExtentTestManager.getTest().log(LogStatus.INFO, "NO values dipslaying under 'Dump' page");
			   System.out.println("NO values dipslaying under 'Dump' page");
			   
		   }else{
			  ExtentTestManager.getTest().log(LogStatus.PASS, "Dump value is displaying as:   "+ dumpvalue); 
			  System.out.println("Dump value is displaying as:   "+ dumpvalue);
		   }
		   
		   driver.navigate().back();
		   Thread.sleep(5000);
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
	
	
	public void EnterTextValue(String application, String value, String labelname, String xpath) {
		WebElement element = null;

		try {
			Thread.sleep(1000);
			element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));
			if(element==null)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step: '"+labelname+"' text field not found");
				Log.info("Step: '"+labelname+"' text field not found");
			}
			else 
			{
				if(value.equalsIgnoreCase("null")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "No values entered in "+labelname + " text field");
				}else {
					element.sendKeys(value);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step: Entered '"+value+"' into '"+labelname+"' text field");
				}
			}
		}catch(NoSuchElementException ep) {
			ep.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, labelname + " field is not displaying");
			Log.info(labelname + " field is not displaying");
		}
		catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Not able to enter '"+value+"' into '"+labelname+"' text field");
			e.printStackTrace();
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
				Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				for(int i=0; i<selectValue.length; i++)
				{
					Thread.sleep(2000);
					for(int j=0; j<ls.size() ; j++) {
						Log.info("ls value "+ ls.get(j));
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
				Log.info("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			Log.info( "No values displaying under "+labelname + " available dropdown");
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
				Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);
			}else {
				ExtentTestManager.getTest().log(LogStatus.INFO, "No values displaying under " + labelname + " dropdown");

				Log.info("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			Log.info( "No values displaying under "+labelname + " available dropdown");
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
				Log.info("list of values dipslaying inside "+labelname+" dropdown is: "+ls);

				//select value inside the dropdown     
				for(int i=0; i<selectValue.length; i++)
				{
					Thread.sleep(5000);
					for(int j=0; j<ls.size() ; j++) {
						Log.info("ls value "+ ls.get(j));
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

				Log.info("No values displaying under " + labelname + " available dropdown");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under "+labelname + " available dropdown");
			Log.info( "No values displaying under "+labelname + " available dropdown");
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
	
	
	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected)
			throws InterruptedException, DocumentException, IOException {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Select Existing Customer Functionality");

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(1000);
		Log.info("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouser hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "Create Order/Service Link", "CreateOrderServiceLink", xml);	
		Log.info("=== Create Order/Service navigated ===");

		//click_commonMethod on Next button to check mandatory messages	
			click_commonMethod(application, "Next", "nextbutton", xml);

		//Customer Error message	
			warningMessage_commonMethod(application, "customer_createorderpage_warngmsg", "Choose a customer", xml);

		//Entering Customer name
			addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected, xml);
		
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(3000);
		EnterTextValue(application, "*", "Customer Name", "entercustomernamefield");	

		//Select Customer from dropdown
			addDropdownValues_commonMethod(application, "Choose a customer", "choosecustomerdropdown", ChooseCustomerToBeSelected, xml);
			click_commonMethod(application, "Next", "nextbutton", xml);

	}
	
	
	public void selectCustomertocreateOrderfromleftpane(String application, String ChooseCustomerToBeSelected)
			throws InterruptedException, DocumentException, IOException {
		

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying Select Existing Customer Functionality");

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		Log.info("Mouser hovered on Manage Customer's Service");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");

		click_commonMethod(application, "Create Order/Service Link", "CreateOrderServiceLink", xml);	
		Log.info("=== Create Order/Service navigated ===");


		//Entering Customer name
			addtextFields_commonMethod(application, "Customer Name", "entercustomernamefield", ChooseCustomerToBeSelected, xml);
		
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(3000);
		EnterTextValue(application, "*", "Customer Name", "entercustomernamefield");
		
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);

		//Select Customer from dropdown
			addDropdownValues_commonMethod(application, "Choose a customer", "chooseCustomerdropdown", ChooseCustomerToBeSelected, xml);
			click_commonMethod(application, "Next", "nextbutton", xml);
		
	}
	
	
	public void createorderservice(String application, String neworderSelection, String neworderno, String newrfireqno, String existingorderSelection,
			String existingordernumber)
			throws InterruptedException, IOException, DocumentException {

		//Create New order or select Existing Order	
			scrolltoend();
			if (neworderSelection.equalsIgnoreCase("YES")) {

				WebElement CreateOrder_Header= getwebelement(xml.getlocator("//locators/" + application + "/createOrderORService"));
				scrolltoview(CreateOrder_Header);
				Thread.sleep(2000);
				
				EnterTextValue(application, neworderno, "Order/Contract Number", "newordertextfield");
				EnterTextValue(application, newrfireqno, "RFI Voice line Number", "newrfireqtextfield");
				click_commonMethod(application, "create order", "createorderbutton", xml);
				
				scrollToTop();
				Thread.sleep(1000);
				compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully", xml);			
				scrolltoview(CreateOrder_Header);
			} 
			
			else if (existingorderSelection.equalsIgnoreCase("YES")) {
				
				click_commonMethod(application, "select order switch", "selectorderswitch", xml);
				addDropdownValues_commonMethod(application, "Order/Contract Number(Parent SID)", "existingorderdropdown", existingordernumber , xml);
				Log.info("=== Order Contract Number selected===");

				Thread.sleep(3000);

			} else {
				Log.info("Order not selected");
				ExtentTestManager.getTest().log(LogStatus.INFO, "Step :Order not selected");
			}
				
		}
	
	
	public void selectServiceType(String application, String serviceTypeToBeSelected) throws InterruptedException, DocumentException {
		
		//select Service Type
		scrolltoend();
		Thread.sleep(3000);
		
		addDropdownValues_commonMethod(application, "Service Type", "servicetypedropdowntoclick", serviceTypeToBeSelected, xml);
		
	}
	
	
	
	public void VerifyUsersPanel(String application, String Username, String Firstname, String Surname, String Postaladdress, String Email, String Phone, String EditUsername, 
			String EditFirstname, String EditSurname, String EditPostaladdress, String EditEmail, String EditPhone, String IPGuardianAccountGroup,String ColtOnlineUser, 
			String GeneratePassword, String RolesToBeSelected,String HideRouterToolsIPv6CommandsCisco_ToBeSelected, String HideRouterToolsIPv4CommandsHuiwai_ToBeSelected, 
			String HideRouterToolsIPv4CommandsCisco_ToBeSelected,String HideServicesToBeSelected,String HideSiteOrderToBeSelected, String editRolesToBeSelected, 
			String edit_RoleToBeHidden, String RouterToolsIPv6CommandsCisco_ToBeAvailable, String RouterToolsIPv6CommandsCisco_ToBeHidden, String RouterToolsIPv4CommandsHuiwai_ToBeAvailable, 
			String HideRouterToolsIPv4CommandsHuiwai_ToBeHidden, String HideRouterToolsIPv4CommandsCisco_ToBeAvailable, String HideRouterToolsIPv4CommandsCisco_ToBeHidden,
			String Services_ToBeAvailable, String Services_ToBeHidden, String SiteOrders_ToBeAvailable, String SiteOrders_ToBeHidden, String editIPGuardianAccountGroup, String editColtOnlineUser) 
					throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying User Panel in 'View Service' page");
		
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
		WebElement UserGridCheck= getwebelement("(//div[text()='Users']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
		String UserGrid= UserGridCheck.getAttribute("style");

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
			addtextFields_commonMethod(application, "Email", "Email", Email, xml);
			addtextFields_commonMethod(application, "Phone", "Phone", Phone, xml);
			addtextFields_commonMethod(application, "IPGuardian Account Group" , "IPGuardianAccountGroup" , IPGuardianAccountGroup, xml);
			addtextFields_commonMethod(application, "Colt Online User", "ColtOnlineUser", ColtOnlineUser, xml);
			click_commonMethod(application, "Generate Password", "GeneratePassword", xml);
			String  password=getwebelement(xml.getlocator("//locators/"+application+"/Password_Textfield")).getAttribute("value");
			Log.info("Generated Password is : "+password);

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


			//		//Hide Router Tool IPv6 Commands(Cisco)	
			//			selectAndAddValueFromLeftDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , "HideRouterToolIPv6_Cisco_Available" , selectValue, xpathForAddButton);
			//			verifySelectedValuesInsideRightDropdown(application, "Hide Router Tool IPv6 Commands(Cisco)" , xpath);

			scrolltoend();
			Thread.sleep(1000);
			click_commonMethod(application, "OK", "OK_button", xml);
			Thread.sleep(2000);
			
			waitForpageload();
			waitforPagetobeenable();
			
			clickOnBankPage();
			scrollToTop();
			compareText(application, "Create User success message", "successmsg", "User successfully created", xml);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : User added successfully");
			Log.info("User added successfully");

			//Edit User
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers= getwebelements(xml.getlocator("//locators/"+application+"/checkExistingUserUnderUserPanel"));
			int NoOfUsers = ExistingUsers.size();
			Log.info("Total users:"+ NoOfUsers);

			if(NoOfUsers==1 || NoOfUsers>1)
			{
				click_commonMethod(application, "UserNamefilter", "userPanelFilterButton", xml);
				Thread.sleep(1000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/"+application+"/userNamefilterTextField")), Username);
				Thread.sleep(1000);
				clickOnBankPage();
				
				ScrolltoElement(application, "customerdetailsheader", xml);
				Thread.sleep(1000);
				
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/"+application+"/selectuncheckedCheckbox_UserPanel").replace("value", Username));
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
				edittextFields_commonMethod(application, "IPGuardian Account Group" , "IPGuardianAccountGroup" , editIPGuardianAccountGroup, xml);
				edittextFields_commonMethod(application, "Colt Online User", "ColtOnlineUser", editColtOnlineUser, xml);

				String editpassword=getwebelement(xml.getlocator("//locators/"+application+"/Password")).getAttribute("value");
				Log.info("Generated Password is : "+editpassword);

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
				
				waitForpageload();
				waitforPagetobeenable();
				
				clickOnBankPage();
				Thread.sleep(2000);
				
				scrollToTop();
				compareText(application, "User update success message", "successmsg", "User successfully updated", xml);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : No users displayed");
				Log.info("No users displayed");
			}


		//As we are getting a save Username popup. we are opening a new tab and closing to close the username popup	
			Actions action =new Actions(driver);
			action.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();
			
			Switchtotab();
			CloseProposalwindow();
			
			//View User
			String UserNametoSelect=null;
			ScrolltoElement(application, "customerdetailsheader", xml);
			List<WebElement> ExistingUsers1= getwebelements(xml.getlocator("//locators/"+application+"/checkExistingUserUnderUserPanel"));
			int NoOfUsers1 = ExistingUsers1.size();
			Log.info("Total users:"+ NoOfUsers1);
			if(NoOfUsers1==1 || NoOfUsers1>1)
			{
				if(!EditUsername.equalsIgnoreCase("null")) {
					UserNametoSelect = Username;
				}
				else {
					UserNametoSelect = EditUsername;
				}
				click_commonMethod(application, "UserNamefilter", "userPanelFilterButton", xml);
				Thread.sleep(1000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/"+application+"/userNamefilterTextField")), Username);
				Thread.sleep(1000);
				clickOnBankPage();
				
				ScrolltoElement(application, "customerdetailsheader", xml);
				Thread.sleep(1000);
				
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/"+application+"/selectuncheckedCheckbox_UserPanel").replace("value", UserNametoSelect));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
				Thread.sleep(2000);
				
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
					Log.info("list of values in Hide router Tool Command IPv4(Cisco) are: "+listofHiddenCiscoValues.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Cisco) are: " + listofHiddenCiscoValues.getText());
				}

				scrolltoend();
				Thread.sleep(2000);

				//Hidden Router Tool IPv4 (Huawei)
				List<WebElement> Ipv4CommandHuawei = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv4Huawei"));	

				for(WebElement listofHuaweiValues : Ipv4CommandHuawei) {
					Log.info("list of values in Hide router Tool Command (Cisco) are: "+listofHuaweiValues.getText());
					ExtentTestManager.getTest().log(LogStatus.PASS, "List of Hidden Router Tool IPv4 Commands(Huawei) are: "+ listofHuaweiValues.getText());
				}	


				//Hidden Router Tools IPv6 (Cisco)
				List<WebElement> HiddenIPv6cisco = getwebelements(xml.getlocator("//locators/"+application+"/viewUser_HiddenRouterToolCommandIPv6Cisco"));	

				for(WebElement listofHiddenIPv6CiscoValues : HiddenIPv6cisco) {
					Log.info("list of values in Hide router Tool Command IPv6 (Cisco) are: "+listofHiddenIPv6CiscoValues.getText());
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
			List<WebElement> ExistingUsers2= getwebelements(xml.getlocator("//locators/"+application+"/checkExistingUserUnderUserPanel"));
			int NoOfUsers2 = ExistingUsers2.size();
			Log.info("Total users:"+ NoOfUsers2);
			if(NoOfUsers2==1 || NoOfUsers2>1)
			{
				if(!EditUsername.equalsIgnoreCase("null")) {
					UserNametoSelect = Username;
				}
				else {
					UserNametoSelect = EditUsername;
				}
				click_commonMethod(application, "UserNamefilter", "userPanelFilterButton", xml);
				Thread.sleep(1000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/"+application+"/userNamefilterTextField")), Username);
				Thread.sleep(1000);
				clickOnBankPage();
				
				ScrolltoElement(application, "customerdetailsheader", xml);
				Thread.sleep(1000);
				
				WebElement AddedUser = getwebelement(xml.getlocator("//locators/"+application+"/selectuncheckedCheckbox_UserPanel").replace("value", UserNametoSelect));
				AddedUser.click();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : clicked on Existing user radio button");
				Log.info("clicked on Existing user radio button");
				Thread.sleep(2000);

				click_commonMethod(application, "Action dropdown", "UserActionDropdown", xml);
				click_commonMethod(application, "Delete", "delete", xml);
				Thread.sleep(2000);
				
				Alert alert= driver.switchTo().alert ();
				alert.accept();
				
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : No users displayed");
				Log.info("No users displayed");
			}
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
			
				addDropdownValues_commonMethod(application, "Order/Contract Number (Parent SID)", "changeOrder_orderDropdown", ChangeOrder_existingOrderNumber, xml);
				
				click_commonMethod(application, "OK", "changeorder_okbutton", xml);
				Thread.sleep(1000);
				ScrolltoElement(application, "userspanel_header", xml);
				Thread.sleep(1000);
				compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_existingOrderNumber, xml);
				compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
				Log.info("------ Change Order is successful ------");
	
		}
		
	}
	
	
	public void selectRowForsiteorder_ForPAMTest(String Application, String siteordernumber, String interfaceSpeed)
			throws InterruptedException, DocumentException, IOException {

		waitForpageload();
		waitforPagetobeenable();
		
		System.out.println("-----------------------------" + siteordernumber + "---------------------");
		int TotalPages;
 
		scrolltoend();
		Thread.sleep(3000);
			List<WebElement> results = null;
			
				if(interfaceSpeed.equals("10GigE")) {
					results=getwebelements("//div[div[text()='Site Orders']]//following-sibling::div//div[text()='"+ siteordernumber + "']");
				}else if(interfaceSpeed.equals("1GigE")) {
					results=getwebelements("//div[div[text()='Site Orders']]//following-sibling::div//div[text()='"+ siteordernumber + "']");
				}
		
			int numofrows = results.size();
			System.out.println("no of results: " + numofrows);
			boolean resultflag;

						resultflag = results.get(0).isDisplayed();
						System.out.println("status of result: " + resultflag);
						if (resultflag) {
							System.out.println(results.get(0).getText());
							results.get(0).click();
							Thread.sleep(5000);
							click_commonMethod(Application, "Action", "Actiondropdown_siteorder", xml);

							Thread.sleep(5000);

						}
	}
	
	
	public void viewPPPconfiguration(String application, String framedWANipAddress, String framedRoute0, String framedRoute1, String framedRoute2,
			String framedRoute3, String framedRoute4, String framedRoute5, String framedRoute6, String framedRoute7, String CPEdeviceName) throws InterruptedException, DocumentException {
		
	    
		compareText_InViewPage(application, "Device", CPEdeviceName, xml);		//CPE device Name
		
		compareText_InViewPage(application, "Framed/WAN/IP Address", framedWANipAddress, xml);		//Framed/WAN/IP Address
		
		compareText_InViewPage(application, "Framed Protocol", "PPP" , xml);			//Framed Protocol
		
		compareText_InViewPage(application, "Framed Route0", framedRoute0, xml);		//Framed Route0
		
		compareText_InViewPage(application, "Framed Route1", framedRoute1, xml);		//Framed Route1
		
		compareText_InViewPage(application, "Framed Route2", framedRoute2, xml);		//Framed Route2
		
		compareText_InViewPage(application, "Framed Route3", framedRoute3, xml);		//Framed Route3
		
		compareText_InViewPage(application, "Framed Route4", framedRoute4, xml);		//Framed Route4
		
		compareText_InViewPage(application, "Framed Route5", framedRoute5, xml);		//Framed Route5
		
		compareText_InViewPage(application, "Framed Route6", framedRoute6, xml);		//Framed Route6
		
		compareText_InViewPage(application, "Framed Route7", framedRoute7, xml);		//Framed Route7
		
	}
	
	
	public void pppConfigurationClickOnEditLink(String application) throws InterruptedException, DocumentException {
		
		click_commonMethod(application, "Action", "addPPPconfig_viewPage_actionDrpodown", xml);
		
		click_commonMethod(application, "Edit", "pppConfigurationViewPage_editLink" , xml);
	}


	public void editPPPconfiguration(String application, String framedRoute0, String framedRoute1, String framedRoute2,
			String framedRoute3, String framedRoute4, String framedRoute5, String framedRoute6, String framedRoute7,
			String uniVirtualRouterName, String uniLoopbackInteface) throws InterruptedException, DocumentException, IOException {
		
					// Framed Route0
					addtextFields_commonMethod(application, "Framed Route0", "addPPPconfig_framedRouter0", framedRoute0, xml);

					// Framed Router1
					addtextFields_commonMethod(application, "Framed Route1", "addPPPconfig_framedRouter1", framedRoute1, xml);

					// Framed Route2
					addtextFields_commonMethod(application, "Framed Route2", "addPPPconfig_framedroute2", framedRoute2, xml);

					// Framed Route3
					addtextFields_commonMethod(application, "Framed Route3", "addPPPconfig_framedRoute3", framedRoute3, xml);

					// Framed Route4
					addtextFields_commonMethod(application, "Framed Route4", "addPPconfig_framedRoute4", framedRoute4, xml);

					// Framed Router5
					addtextFields_commonMethod(application, "Framed Route5", "addPPPconfig_framedRoute5", framedRoute5, xml);
					
					scrolltoend();
					Thread.sleep(1000);

					// Framed route6
					addtextFields_commonMethod(application, "Framed Route6", "addPPPconfig_framedRoute6", framedRoute6, xml);

					// Framed route7
					addtextFields_commonMethod(application, "Framed Route7", "addPPPconfig_framedRoute7", framedRoute7, xml);

					// Uni Virtual Route Name
						edittextFields_commonMethod(application, "Uni Virtual Route Name", "addPPPconfig_uniVirtualRouterName",
								uniVirtualRouterName, xml);

					// Uni Local Loopback Interface
					edittextFields_commonMethod(application, "Uni Local Loopback Interface",
							"addPPPconfig_uniLocalLoopbackInterface", uniLoopbackInteface, xml);

					scrolltoend();
					Thread.sleep(1000);
					click_commonMethod(application, "OK", "CPE_addInterface_OKbutton_out", xml); // OK button

	}
	
	
		public void deletePPPconfiguration(String application, String siteOrderNumber) throws InterruptedException, DocumentException, IOException {
			
			click_commonMethod(application, "Action", "addPPPconfig_viewPage_actionDrpodown", xml);
			
			click_commonMethod(application, "Delete", "pppConfigurationViewPage_deleteLink" , xml);
			
			 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			try { 
	         if(DeleteAlertPopup.isDisplayed())
	         {
	       	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
	       	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
	       	 
	            click_commonMethod(application, "Delete", "pppConfiguration_viewPage_DeleteButton", xml);
	            
	            verifysuccessmessage(application, "PPP Configuration successfully deleted");
	         }
	         else
	         {
	               Log.info("Delete alert popup is not displayed");
	               ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
	         }
			}catch(Exception e) {
				e.printStackTrace();
				Log.info("Delete alert popup is not displayed for 'PPP Configuration'");
	            ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed for 'PPP Configuration'");
	            
	            clickOnBreadCrump(application, siteOrderNumber);
			}
		}
		
		
		
		public void deletePEdevice(String application, String devicename) throws Exception {
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrolltoend();
			Thread.sleep(2000);

			WebElement autoDiscoverVPN = getwebelement(
					xml.getlocator("//locators/" + application + "/clickOnDeleteLink_PEdevice").replace("value", devicename));
			safeJavaScriptClick(autoDiscoverVPN);
			ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on 'Delete From Service' link under 'Provider Equipment");
			
			 WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
				try { 
		         if(DeleteAlertPopup.isDisplayed())
		         {
		       	 String deletPopUpMessage= Gettext(getwebelement(xml.getlocator("//locators/" + application + "/deleteMessages_textMessage")));
		       	 ExtentTestManager.getTest().log(LogStatus.PASS, "Delete Pop up message displays as: "+ deletPopUpMessage);
		       	 
		            click_commonMethod(application, "Delete", "deletebutton", xml);
		            
		            verifysuccessmessage(application, "Device successfully deleted");
		         }
		         else
		         {
		               Log.info("Delete alert popup is not displayed");
		               ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed");
		         }
				}catch(Exception e) {
					e.printStackTrace();
					Log.info("Delete alert popup is not displayed for 'PPP Configuration'");
		            ExtentTestManager.getTest().log(LogStatus.FAIL, "Delete alert popup is not displayed for 'PPP Configuration'");
				}
		}
		
		
		public void deleteCPEdevice(String application, String devicename) throws Exception {
			
			Thread.sleep(12000);

			scrolltoend();
			Thread.sleep(2000);

			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Delete From Service' Functionality for CPE deviec");

			WebElement deleteLink = getwebelement(
					xml.getlocator("//locators/" + application + "/clickOnDEleteFromServiceLink_CPEdevice").replace("value",
							devicename));
			safeJavaScriptClick(deleteLink);
			
		}
		
		

		//Add/Edit/Delete Routes
public void addRouterFunction_CPE(String application, String PE_RouteCity, 
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

	
}





public void editRouterFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
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
			click_commonMethod(application, "Close additionally opened Add Route popup 2nd window Issue reported", "EditRoutes_EIPAllocation_CloseButton", xml);

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




public void deleteRouterFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String PE_RouteCityEdit, 
		String PE_RouteSubnetSizeEdit, String	PE_GatewayEdit,String	PE_NetworkAddressEdit, String	PE_NetworkMASEdit, String	PE_MetricsEdit
		) throws InterruptedException, DocumentException, IOException {
	
	ScrolltoElement(application, "interfaces_header", xml);
	
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

			
		}
}





		//Customer Readonly SNMP Panel
public void addCustomerReadonlySNMPFunction_CPE(String application, String CustomerIPAddress, String CustomerCommunityString
		) throws InterruptedException, DocumentException, IOException {

	
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Add Customer Readonly SNMP' Functionality");
	
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
		Thread.sleep(20000);
		
		waitForpageload();  waitforPagetobeenable();
		
		scrollToTop();
		verifysuccessmessage(application, "Customer Readonly SNMP successfully created");
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Add Customer Readonly SNMP  panel header is not displaying");
		System.out.println("Add Customer Readonly SNMP  panel header is not displaying");
	}

	
}


}


	public void editCustomerReadonlySNMPFunction_CPE(String application, String CustomerIPAddressEdit, String CustomerCommunityStringEdit,
			String customerIPCreatedValue) throws Exception {

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit Customer readonly SNMP' Functionality");
		
	ScrolltoElement(application, "Routes_Header", xml);//interfaces_header//CustomerReadonlyNMP_panelHeader//Routes_Header
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/CustomerReadonlySNMP_panelHeader")).isDisplayed()){
		
		WebElement valueUnderCustomerSNMPPanel = getwebelement(xml.getlocator("//locators/" + application + "/customerReadonlySelectValueUnderPanel").replace("value", customerIPCreatedValue));
		safeJavaScriptClick(valueUnderCustomerSNMPPanel);
		
		click_commonMethod(application, "Action", "SNMP_Actionbutton", xml);
		click_commonMethod(application, "Edit Link", "SNMP_EditLink", xml);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/EditSNMP_Header")).isDisplayed()){
			
			compareText(application, "Edit SNMP_Header", "EditSNMP_Header", "Edit Customer Readonly SNMP", xml);

		ClearAndEnterTextValue(application, "Customer IP Address", "SNMP_CustomerIPAddress_Textfield", CustomerIPAddressEdit, xml);
		ClearAndEnterTextValue(application, "Customer Community String", "SNMP_CustomerCommunityString_Textfield", CustomerCommunityStringEdit, xml);
		
		click_commonMethod(application, "OK Button", "SNMP_OKButton", xml);
		scrollToTop();
		Thread.sleep(2000);
		
		waitForpageload();  waitforPagetobeenable();
		
		verifysuccessmessage(application, "Customer Readonly SNMP successfully updated.");
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Customer Readonly SNMP  panel header is not displaying");
		System.out.println("Edit Customer Readonly SNMP  panel header is not displaying");
	}

	
}

}



public void deleteCustomerReadonlySNMPFunction_CPE(String application, String CustomerIPAddressEdit, 
			String CustomerCommunityStringEdit, String customerIPAddressValue ) throws Exception {
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'delete Customer Readonly SNMP' Functionality");
	
	ScrolltoElement(application, "Routes_Header", xml);//interfaces_header//CustomerReadonlyNMP_panelHeader//Routes_Header
	
	if(getwebelement(xml.getlocator("//locators/" + application + "/CustomerReadonlySNMP_panelHeader")).isDisplayed()){

		WebElement valueUnderCustomerSNMPPanel = getwebelement(xml.getlocator("//locators/" + application + "/customerReadonlySelectValueUnderPanel").replace("value", customerIPAddressValue));
		safeJavaScriptClick(valueUnderCustomerSNMPPanel);
		
		click_commonMethod(application, "Action", "SNMP_Actionbutton", xml);
		click_commonMethod(application, "Delete Link", "SNMP_DeleteLink", xml);
		
	
		if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
		{
			click_commonMethod(application, "Delete", "SNMP_DeleteButton", xml);
		}else{
			Log.info("Delete alert popup is not displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed for SNMP");
		}
		
		Thread.sleep(2000);
		waitForpageload();  waitforPagetobeenable();
		
		scrollToTop();
		verifysuccessmessage(application, "Customer Readonly SNMP successfully deleted.");
		
	}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Customer Readonly SNMP  panel header is not displaying in View CPE Device page");
		System.out.println("Customer Readonly SNMP  panel header is not displaying in View CPE Device page");
	}

	
}



public void SelectDropdownValueUnderSpanTag(String application ,String lebelname, String dropdownToBeSelectedInTheEnd, String dropdownXpath, String commonDropdownValueTag, XMLReader xml) throws InterruptedException, DocumentException, IOException {
	
	try {
		// Select  Country dropdown
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+dropdownXpath+"")));
			List<WebElement> dropdownValueList = driver.findElements(By.xpath(commonDropdownValueTag));
			
			for (WebElement dropdownvaluelist : dropdownValueList) {
				System.out.println("Available " +lebelname+ " are : " + dropdownvaluelist.getText().toString()+ "  ,  ");
				ExtentTestManager.getTest().log(LogStatus.PASS,"Step : Available  '" +lebelname+ "'  is : " + dropdownvaluelist.getText().toString());
				Log.info("Available " +lebelname+ " is : " + dropdownvaluelist.getText().toString());
			}
			
			Thread.sleep(2000);
			// click on Country dropdown
			WebElement selectDropdownValue = driver.findElement(By.xpath("//span[@aria-label='"+dropdownToBeSelectedInTheEnd+"']"));//span[text()='" + dropdownToBeSelectedInTheEnd + "']
			System.out.println("Selected  '" +lebelname+ "'  is : "+ selectDropdownValue.getText().toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Selected  '"+lebelname+ "'  is : "+ selectDropdownValue.getText().toString());
			Log.info("Selected  '" +lebelname+ "'  is : " +selectDropdownValue.getText().toString());
			selectDropdownValue.click();
			Thread.sleep(2000);
			
			//span[@aria-label='Device-901']

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



public void RightClick(WebElement el) {
	Actions action = new Actions(driver);
    action.contextClick().build().perform();
}

public void EnterByAction() {
	Actions action = new Actions(driver);
    action.sendKeys(Keys.ENTER).perform();
}

public void ClickOnExpectedLinkAfterRightClick() {
	Actions action = new Actions(driver);
    action.sendKeys("Open link in new tab").build().perform();
    //moveToElement(el).click().build().perform();
}


public void clickUsingAction(WebElement el)
{
	Actions act=new Actions(driver);
	act.moveToElement(el).click().build().perform();
}



//Extra Subnet Panel
public void addExtraSubnetFunction_CPE(String application, String ExtraSubnets_City, String ExtraSubnets_SubnetSize) throws InterruptedException, DocumentException, IOException {

ScrolltoElement(application, "CustomerReadonlySNMP_panelHeader", xml);		//Scroll till sCustomerReadonlySNMP_panelHeader


if(getwebelement(xml.getlocator("//locators/" + application + "/ExtraSubnets_PanelHeader")).isDisplayed()){
click_commonMethod(application, "Action", "ExtraSubnets_Action", xml);
click_commonMethod(application, "Allocate Extra Subnets Link", "AllocateExtraSubnets_Link", xml);

if(getwebelement(xml.getlocator("//locators/" + application + "/AddExtraSubnets_Header")).isDisplayed()){
	compareText(application, "Subnet Type Value", "ExtraSubnets_SubnetTypeValue", "CUST", xml);
	click_commonMethod(application, "Allocate Subnet Button", "ExtraSubnets_AllocateSubnetButon", xml);
	
	warningMessage_commonMethod(application, "ExtraSubnets_City_WarningMessage", "City ", xml);
	warningMessage_commonMethod(application, "ExtraSubnets_SubnetSize_WarningMessage", "Subnet Size  ", xml);
	warningMessage_commonMethod(application, "ExtraSubnets_EIPAllocation_AvilablePoolWarningMessage", "Avilable Pool ", xml);
	
	addDropdownValues_commonMethod(application, "City" , "ExtraSubnets_CityDropdown" , ExtraSubnets_City , xml);		//City
	
	addDropdownValues_commonMethod(application, "Subnet Size" , "ExtraSubnets_SubnetSizeDropdown" , ExtraSubnets_SubnetSize, xml);

	Thread.sleep(4000);
	WebElement allocateBlockMessage = getwebelement(xml.getlocator("//locators/" + application + "/ExtraSubnets_AvailablePoolsMessage"));
	
	try {
	if(allocateBlockMessage.isDisplayed()) {
		
		String actualAllocateBLockMessage = Gettext(allocateBlockMessage);
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Allocate BLock' field, value displays as: " + actualAllocateBLockMessage);
		
		click_commonMethod(application, "Allocate Subnet" , "ExtraSubnets_AllocateSubnetButon" , xml);
		
		click_commonMethod(application, "Close Extra Subnets window", "ExtraSubnets_CloseButton", xml);
		
	}else {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "No messages displays under 'Allocate Blocks' field");
		
		click_commonMethod(application, "Allocate Subnet" , "ExtraSubnets_AllocateSubnetButon" , xml);
		
		click_commonMethod(application, "Close Extra Subnets window", "ExtraSubnets_CloseButton", xml);
	}
	
	
	}catch(Exception e) {
		e.printStackTrace();
		
		click_commonMethod(application, "Allocate Subnet" , "ExtraSubnets_AllocateSubnetButon" , xml);
		
		click_commonMethod(application, "Close Extra Subnets window", "ExtraSubnets_CloseButton", xml);
	}
	
	
}else {
ExtentTestManager.getTest().log(LogStatus.FAIL, "Add Extra Subnets popup is not displaying");
}


}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "Extra Subnets  panel header is not displaying In view CPE Device page");
System.out.println("Extra Subnets  panel header is not displaying In view CPE Device page");
}


}




//NAT Configuration
public void editNATConfigurationFunction_CPE(String application,String StaticNATEdit, String DynamicNATEdit) throws InterruptedException, DocumentException, IOException {

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
			
			Thread.sleep(2000);
			
			waitForpageload();
			waitforPagetobeenable();
			scrollToTop();
			verifysuccessmessage(application, "NAT updated successfully");
			
			}	


}else{
ExtentTestManager.getTest().log(LogStatus.FAIL, "NAT Configuration  panel header is not displaying");
System.out.println("NAT Configuration  panel header is not displaying");
}


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


		
		//Add Static NAT Mapping
		
		public void addStaticNATMappingFunction_CPE(String application, String Static_Protocol, 
		String Static_LocalPort, String	Static_GlobalPort,String	Static_LocalIP, String	Static_GlobalIP) throws InterruptedException, DocumentException, IOException {
		
			ExtentTestManager.getTest().log(LogStatus.INFO, "Add 'Static NAT Mapping'");
			
		ScrolltoElement(application, "NATConfiguration_panelHeader", xml);//NATConfiguration_panelHeader//
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNATMapping_panelHeader")).isDisplayed()){
		click_commonMethod(application, "Action", "StaticNAT_Actionbutton", xml);
		click_commonMethod(application, "Add Link", "StaticNAT_AddLink", xml);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddStaticNAT_Header")).isDisplayed()){
		addDropdownValues_commonMethod(application, "Protocol", "StaticNAT_Protocol_Dropdown", Static_Protocol, xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common1", xml);
			
		warningMessage_commonMethod(application, "StaticNAT_LocalPort_WarningMessage", "Local Port ", xml);
		warningMessage_commonMethod(application, "StaticNAT_GlobalPort_WarningMessage", "Global Port ", xml);
		warningMessage_commonMethod(application, "StaticNAT_LocalIP_WarningMessage", "Local IP ", xml);
		warningMessage_commonMethod(application, "StaticNAT_GlobalIP_WarningMessage", "Global IP ", xml);
		
		addtextFields_commonMethod(application, "LOCAL PORT textfield", "StaticNAT_LocalPort_Textfield", Static_LocalPort, xml);
		addtextFields_commonMethod(application, "GLOBAL PORT textfield", "StaticNAT_GlobalPort_Textfield", Static_GlobalPort, xml);
		addtextFields_commonMethod(application, "LOCAL IP textfield", "StaticNAT_LocalIP_Textfield", Static_LocalIP, xml);
		addtextFields_commonMethod(application, "GLOBAL IP textfield", "StaticNAT_GlobalIP_Textfield", Static_GlobalIP, xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common1", xml);
		
		Thread.sleep(2000);
		
		waitForpageload();  waitforPagetobeenable();
		scrollToTop();
		verifysuccessmessage(application, "Static NAT successfully inserted.");
		
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Add Static NAT Mapping Header/popup not displayed");
		}
		
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Static NAT Mapping Panel not displayed");
			}
		
		
		}
		
		
		
		//Edit Static NAT Mapping
		public void editStaticNATMappingFunction_CPE(String application, String Static_ProtocolEdit, 
			String Static_LocalPortEdit, String	Static_GlobalPortEdit,String	Static_LocalIPEdit, String	Static_GlobalIPEdit, String localIPCreatedValue) throws Exception {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Edit 'Static NAT Mapping'");	
			
		ScrolltoElement(application, "NATConfiguration_panelHeader", xml);//NATConfiguration_panelHeader//
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNATMapping_panelHeader")).isDisplayed()){
			
			WebElement valueUnderCustomerSNMPPanel = getwebelement(xml.getlocator("//locators/" + application + "/editStatic_selectValueUnderPanel").replace("value", localIPCreatedValue));
			safeJavaScriptClick(valueUnderCustomerSNMPPanel);
			
			click_commonMethod(application, "Action", "StaticNAT_Actionbutton", xml);
			click_commonMethod(application, "Edit Link", "StaticNAT_Editink", xml);
		
			if(getwebelement(xml.getlocator("//locators/" + application + "/EditStaticNAT_Header")).isDisplayed()){
				
		addDropdownValues_ForProtocol(application, "Protocol", "StaticNAT_Protocol_Dropdown", Static_ProtocolEdit, xml);
		
		edittextFields_commonMethod(application, "LOCAL PORT textfield", "StaticNAT_LocalPort_Textfield", Static_LocalPortEdit, xml);
		edittextFields_commonMethod(application, "GLOBAL PORT textfield", "StaticNAT_GlobalPort_Textfield", Static_GlobalPortEdit, xml);
		edittextFields_commonMethod(application, "LOCAL IP textfield", "StaticNAT_LocalIP_Textfield", Static_LocalIPEdit, xml);
		edittextFields_commonMethod(application, "GLOBAL IP textfield", "StaticNAT_GlobalIP_Textfield", Static_GlobalIPEdit, xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common1", xml);
		Thread.sleep(2000);
		
		verifysuccessmessage(application, "Static NAT successfully updated.");
		
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Static NAT Mapping Header/popup not displayed");
		}
		
		}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Static NAT Mapping Panel not displayed");
		}
		
		
		}
		
		
		/**
		 *   For Dropdown common method _  Add
		 * @param application
		 * @param labelname
		 * @param xpath
		 * @param expectedValueToAdd
		 * @param xml
		 * @throws InterruptedException
		 * @throws DocumentException
		 */
		public void addDropdownValues_ForProtocol(String application, String labelname, String xpath, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException {
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
					  Thread.sleep(2000);
					  
					  //verify list of values inside dropdown
					  List<WebElement> listofvalues = driver
								.findElements(By.xpath("//div[@class='sc-ifAKCX oLlzc']"));
					  
					  ExtentTestManager.getTest().log(LogStatus.PASS, " List of values inside "+ labelname + " dropdown is:  ");
					  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
					  
						for (WebElement valuetypes : listofvalues) {
									Log.info("service sub types : " + valuetypes.getText());
									ExtentTestManager.getTest().log(LogStatus.PASS," " + valuetypes.getText());
									System.out.println(" " + valuetypes.getText());
						}
						
						Thread.sleep(2000);
					SendKeys(getwebelement("//div[label[text()='"+ labelname +"']]//input"), expectedValueToAdd);	
					Thread.sleep(4000);
						
					  Clickon(getwebelement("//div[text()='Edit Static NAT Mapping']/parent::div//following-sibling::div//div[contains(text(),'" + expectedValueToAdd + "')]"));
					  Thread.sleep(2000);
					  
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
		
		
			//Delete Static NAT Mapping
		public void deleteStaticNATMappingFunction_CPE(String application, String Static_LocalIP) throws Exception {
		
			ExtentTestManager.getTest().log(LogStatus.INFO, "delete Statuc NAT Mapping");
		
		ScrolltoElement(application, "NATConfiguration_panelHeader", xml);//NATConfiguration_panelHeader
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNATMapping_panelHeader")).isDisplayed()){
			
			WebElement valueUnderCustomerSNMPPanel = getwebelement(xml.getlocator("//locators/" + application + "/editStatic_selectValueUnderPanel").replace("value", Static_LocalIP));
			safeJavaScriptClick(valueUnderCustomerSNMPPanel);
			
			click_commonMethod(application, "Action", "StaticNAT_Actionbutton", xml);
			click_commonMethod(application, "Delete Link", "StaticNAT_DeleteLink", xml);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click_commonMethod(application, "Delete", "SNMP_DeleteButton", xml);
			}else{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			Thread.sleep(2000);
			scrollToTop();
			verifysuccessmessage(application, "Static NAT successfully deleted.");
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Static NAT Mapping panel header not displayed");
		}
		
		}
		
		
		
		
		
		//Add Dynamic NAT Mapping
		public void addDynamicNATMappingFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String Static_Protocol, 
		String Static_LocalPort, String	Static_GlobalPort,String	Static_LocalIP, String	Static_GlobalIP) throws InterruptedException, DocumentException, IOException {
		
		ScrolltoElement(application, "NATConfiguration_panelHeader", xml);//NATConfiguration_panelHeader//
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNATMapping_panelHeader")).isDisplayed()){
		click_commonMethod(application, "Action", "StaticNAT_Actionbutton", xml);
		click_commonMethod(application, "Add Link", "StaticNAT_AddLink", xml);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddStaticNAT_Header")).isDisplayed()){
		addDropdownValues_commonMethod(application, "Protocol", "StaticNAT_Protocol_Dropdown", Static_Protocol, xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common1", xml);
			
		warningMessage_commonMethod(application, "StaticNAT_LocalPort_WarningMessage", "Local Port ", xml);
		warningMessage_commonMethod(application, "StaticNAT_GlobalPort_WarningMessage", "Global Port ", xml);
		warningMessage_commonMethod(application, "StaticNAT_LocalIP_WarningMessage", "Local IP ", xml);
		warningMessage_commonMethod(application, "StaticNAT_GlobalIP_WarningMessage", "Global IP ", xml);
		
		addtextFields_commonMethod(application, "LOCAL PORT textfield", "StaticNAT_LocalPort_Textfield", Static_LocalPort, xml);
		addtextFields_commonMethod(application, "GLOBAL PORT textfield", "StaticNAT_GlobalPort_Textfield", Static_GlobalPort, xml);
		addtextFields_commonMethod(application, "LOCAL IP textfield", "StaticNAT_LocalIP_Textfield", Static_LocalIP, xml);
		addtextFields_commonMethod(application, "GLOBAL IP textfield", "StaticNAT_GlobalIP_Textfield", Static_GlobalIP, xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common1", xml);
		compareText(application, "Add Static NAT Mapping Success Message", "AddStaticNATSuccessMessage", "Static NAT successfully inserted.", xml);
		
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Add Static NAT Mapping Header/popup not displayed");
		}
		
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Static NAT Mapping Panel not displayed");
			}
		
		
		}
		
		
		
		//Edit Dynamic NAT Mapping
		public void editDynamicNATMappingFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String Static_ProtocolEdit, 
			String Static_LocalPortEdit, String	Static_GlobalPortEdit,String	Static_LocalIPEdit, String	Static_GlobalIPEdit) throws InterruptedException, DocumentException, IOException {
		
		ScrolltoElement(application, "NATConfiguration_panelHeader", xml);//NATConfiguration_panelHeader//
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNATMapping_panelHeader")).isDisplayed()){
			
//			WebElement valueUnderCustomerSNMPPanel = getwebelement(xml.getlocator("//locators/" + application + "/editStatic_selectValueUnderPanel").replace("value", localIPCreatedValue));
//			safeJavaScriptClick(valueUnderCustomerSNMPPanel);
			
			click_commonMethod(application, "Action", "StaticNAT_Actionbutton", xml);
			click_commonMethod(application, "Edit Link", "StaticNAT_Editink", xml);
		
			if(getwebelement(xml.getlocator("//locators/" + application + "/EditStaticNAT_Header")).isDisplayed()){
		addDropdownValues_commonMethod(application, "Protocol", "StaticNAT_Protocol_Dropdown", Static_ProtocolEdit, xml);
		
		edittextFields_commonMethod(application, "LOCAL PORT textfield", "StaticNAT_LocalPort_Textfield", Static_LocalPortEdit, xml);
		edittextFields_commonMethod(application, "GLOBAL PORT textfield", "StaticNAT_GlobalPort_Textfield", Static_GlobalPortEdit, xml);
		edittextFields_commonMethod(application, "LOCAL IP textfield", "StaticNAT_LocalIP_Textfield", Static_LocalIPEdit, xml);
		edittextFields_commonMethod(application, "GLOBAL IP textfield", "StaticNAT_GlobalIP_Textfield", Static_GlobalIPEdit, xml);
		
		click_commonMethod(application, "OK Button", "OKButton_common1", xml);
		compareText(application, "Edit Static NAT Mapping Success Message", "UpdateStaticNATSuccessMessage", "Static NAT successfully updated.", xml);
		
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Static NAT Mapping Header/popup not displayed");
		}
		
		}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Static NAT Mapping Panel not displayed");
		}
		
		
		}
		
		
				//Delete Dynamic NAT Mapping
		public void deleteDynamicNATMappingFunction_CPE(String application, String ServiceIdentification, String CPE_DeviceName,String Static_ProtocolEdit, 
			String Static_LocalPortEdit, String	Static_GlobalPortEdit,String	Static_LocalIPEdit, String	Static_GlobalIPEdit) throws InterruptedException, DocumentException, IOException {
		
		ScrolltoElement(application, "NATConfiguration_panelHeader", xml);//NATConfiguration_panelHeader
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/StaticNATMapping_panelHeader")).isDisplayed()){
			
//			WebElement valueUnderCustomerSNMPPanel = getwebelement(xml.getlocator("//locators/" + application + "/editStatic_selectValueUnderPanel").replace("value", localIPCreatedValue));
//			safeJavaScriptClick(valueUnderCustomerSNMPPanel);
			
			click_commonMethod(application, "Action", "StaticNAT_Actionbutton", xml);
			click_commonMethod(application, "Delete Link", "StaticNAT_DeleteLink", xml);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click_commonMethod(application, "Delete", "DeleteButton_common1", xml);
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
		
		
		
		
		
		//Add DHCP Servers on CPE
		public void addDHCPServersonCPEFunction_CPE(String application,String DHCP_CustomerLANSubnet, 
		String	DHCP_SubnetMask,String	DHCP_PrimaryDNSServer, String	DHCP_SecondaryDNSServer, String editStaticNAT, String editDynamicNAT)
				throws InterruptedException, DocumentException, IOException {
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Add DHCP Servers on CPE");
		
		if(editDynamicNAT.equalsIgnoreCase("Yes")) {
			ScrolltoElement(application, "DynamicNATMapping_panelHeader", xml);
		}
		else if((editDynamicNAT.equalsIgnoreCase("No")) && (editStaticNAT.equalsIgnoreCase("Yes"))){
			ScrolltoElement(application, "StaticNATMapping_panelHeader", xml);
		}
		else if((editDynamicNAT.equalsIgnoreCase("No")) && (editStaticNAT.equalsIgnoreCase("No"))){
			ScrolltoElement(application, "NATConfiguration_panelHeader", xml);
		}
		
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/DHCP_panelHeader")).isDisplayed()){
		click_commonMethod(application, "Action", "DHCP_Actionbutton", xml);
		click_commonMethod(application, "Add Link", "DHCP_AddLink", xml);
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/AddDHCP_Header")).isDisplayed()){
		click_commonMethod(application, "OK Button", "OKButton_common1", xml);
			
		warningMessage_commonMethod(application, "DHCP_CustomerLANSubnet_WarningMessage", "Customer LAN Subnet ", xml);
		warningMessage_commonMethod(application, "DHCP_SubnetMask_WarningMessage", "Subnet Mask ", xml);
		warningMessage_commonMethod(application, "DHCP_PrimaryDNSServer_WarningMessage", "Primary DNS Server ", xml);
		
		addtextFields_commonMethod(application, "Customer LAN Subnet textfield", "DHCP_CustomerLANSubnet_Textfield", DHCP_CustomerLANSubnet, xml);
		addtextFields_commonMethod(application, "Subnet Mask textfield", "DHCP_SubnetMask_Textfield", DHCP_SubnetMask, xml);
		addtextFields_commonMethod(application, "Primary DNS Server textfield", "DHCP_PrimaryDNSServer_Textfield", DHCP_PrimaryDNSServer, xml);
		addtextFields_commonMethod(application, "Secondary DNS Server textfield", "DHCP_SecondaryDNSServer_Textfield", DHCP_SecondaryDNSServer, xml);
		
		
		click_commonMethod(application, "OK Button", "OKButton_common1", xml);
		Thread.sleep(2000);
		
		waitForpageload();  waitforPagetobeenable();
		verifysuccessmessage(application, "DHCP server successfully inserted");
		
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Add DHCP Servers on CPE Header/popup not displayed");
		}
		
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DHCP Servers on CPE Panel not displayed");
			}
		
		
		}
		
		
		//Edit DHCP Servers on CPE
		public void editDHCPServersonCPEFunction_CPE(String application, String DHCP_CustomerLANSubnetEdit, String	DHCP_SubnetMaskEdit, 
			String	DHCP_PrimaryDNSServerEdit, String DHCP_SecondaryDNSServerEdit, String customerLANsubnet, String editStaticNAT, String editDynamicNAT) throws Exception {
		
			ExtentTestManager.getTest().log(LogStatus.INFO, "Edit DHCP Servers on CPE");
			
			if(editDynamicNAT.equalsIgnoreCase("Yes")) {
				ScrolltoElement(application, "DynamicNATMapping_panelHeader", xml);
			}
			else if((editDynamicNAT.equalsIgnoreCase("No")) && (editStaticNAT.equalsIgnoreCase("Yes"))){
				ScrolltoElement(application, "StaticNATMapping_panelHeader", xml);
			}
			else if((editDynamicNAT.equalsIgnoreCase("No")) && (editStaticNAT.equalsIgnoreCase("No"))){
				ScrolltoElement(application, "NATConfiguration_panelHeader", xml);
			}
			
		if(getwebelement(xml.getlocator("//locators/" + application + "/DHCP_panelHeader")).isDisplayed()){
			
			WebElement valueUnderCustomerSNMPPanel = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderDHCPpanel").replace("value", customerLANsubnet));
			safeJavaScriptClick(valueUnderCustomerSNMPPanel);
			
			click_commonMethod(application, "Action", "DHCP_Actionbutton", xml);
			click_commonMethod(application, "Edit Link", "DHCP_Editink", xml);
		
			if(getwebelement(xml.getlocator("//locators/" + application + "/EditDHCP_Header")).isDisplayed()){
		
		edittextFields_commonMethod(application, "Customer LAN Subnet textfield", "DHCP_CustomerLANSubnet_Textfield", DHCP_CustomerLANSubnetEdit, xml);
		edittextFields_commonMethod(application, "Subnet Mask textfield", "DHCP_SubnetMask_Textfield", DHCP_SubnetMaskEdit, xml);
		edittextFields_commonMethod(application, "Primary DNS Server textfield", "DHCP_PrimaryDNSServer_Textfield", DHCP_PrimaryDNSServerEdit, xml);
		edittextFields_commonMethod(application, "Secondary DNS Server textfield", "DHCP_SecondaryDNSServer_Textfield", DHCP_SecondaryDNSServerEdit, xml);
		
		
		click_commonMethod(application, "OK Button", "OKButton_common1", xml);
		Thread.sleep(2000);
		
		waitForpageload(); waitforPagetobeenable();
		
		verifysuccessmessage(application, "DHCP server successfully updated");
		
		}else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit DHCP Servers on CPE Header/popup not displayed");
		}
		
		}else{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "DHCP Servers on CPE Panel not displayed");
		}
		
		
		}
		
		
	//Delete DHCP Servers on CPE
		public void deleteDHCPServersonCPEFunction_CPE(String application, String customerLANsubnet, String editStaticNAT, String editDynamicNAT) throws Exception {
		
			ExtentTestManager.getTest().log(LogStatus.INFO, "Delete DHCP Servers on CPE");
			
			if(editDynamicNAT.equalsIgnoreCase("Yes")) {
				ScrolltoElement(application, "DynamicNATMapping_panelHeader", xml);
			}
			else if((editDynamicNAT.equalsIgnoreCase("No")) && (editStaticNAT.equalsIgnoreCase("Yes"))){
				ScrolltoElement(application, "StaticNATMapping_panelHeader", xml);
			}
			else if((editDynamicNAT.equalsIgnoreCase("No")) && (editStaticNAT.equalsIgnoreCase("No"))){
				ScrolltoElement(application, "NATConfiguration_panelHeader", xml);
			}
		
		if(getwebelement(xml.getlocator("//locators/" + application + "/DHCP_panelHeader")).isDisplayed()){
			
			WebElement valueUnderCustomerSNMPPanel = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderDHCPpanel").replace("value", customerLANsubnet));
			safeJavaScriptClick(valueUnderCustomerSNMPPanel);

			click_commonMethod(application, "Action", "DHCP_Actionbutton", xml);
			click_commonMethod(application, "Delete Link", "DHCP_DeleteLink", xml);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup")).isDisplayed())
			{
				click_commonMethod(application, "Delete", "SNMP_DeleteButton", xml);
			}else{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			
			Thread.sleep(2000);
			
			waitForpageload();  waitforPagetobeenable();
			scrollToTop();
			
			verifysuccessmessage(application, "DHCP server successfully deleted.");
		}else{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DHCP Servers on CPE panel header not displayed");
		}
		
		}
		
		
		public void generateConfiguration(String application, String generateConfigurationvalue) throws InterruptedException, DocumentException, IOException {
			
			scrolltoend();
			Thread.sleep(2000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/configurationPanelHeader")).isDisplayed()){
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Configuration' panel is displaying");
				
				addDropdownValues_commonMethod(application, "Generate Configuration for", "generateConfigurationDropdown" , generateConfigurationvalue, xml);
				
				click_commonMethod(application, "Generate" , "generateButton", xml);
				
				String configurationValue = Gettext(getwebelement(xml.getlocator("//locators/" + application + "/generateConfiguration_generatedValue")));
				if(configurationValue.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No configuration value displays");
					System.out.println("No configuration value displays");
				}
				else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "After clicking on 'Generate' button, value displays as: " + configurationValue);
					System.out.println("After clicking on 'Generate' button, value displays as: " + configurationValue);
				}
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Configuration' panel is not displaying");
			}
		}
		
		
		public void createPEtoCPElink(String application, String linkOrCircuitID, String peSourceDevice, String peSourceInterface,
				String CPEtargetDevice, String CPEtargetInterface) throws InterruptedException, DocumentException, IOException {
			
			waitForpageload();
			waitforPagetobeenable();
			
			scrolltoend();
			Thread.sleep(2000);
			
			click_commonMethod(application, "Action", "peToCpeLink_ActionDropdown", xml);
			
			click_commonMethod(application, "Ad New Link", "peToCpeLink_addNewLink" , xml);
			Thread.sleep(1000);
			
			waitForpageload();  waitforPagetobeenable();
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/addNewLink_PanelHeader")).isDisplayed()){
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add New Link' Popup displays");
				Log.info("'Add New Link' popup displays");
				
				click_commonMethod(application, "Next", "addNewLink_nextButton", xml);		//Next button	
				Thread.sleep(1000);
				
				warningMessage_commonMethod(application, "addNewLink_linkOrCircuitID_warningMessage", "Link/Circuit Id", xml);		//Link/Circuit Id
				warningMessage_commonMethod(application, "addNewLink_PEsourceDevice_warningMEsage", "PE Source Device", xml);		//PE Source Device
				warningMessage_commonMethod(application, "addNewLink_PEsourceInterface_warningMEsage", "PE Source Interface", xml);	//PE Source Interface
				warningMessage_commonMethod(application, "addNewLink_CPEtargetDevice_warningMessage", "CPE Target Device", xml);		//CPE Target Device
				warningMessage_commonMethod(application, "addNewLink_CPEtargetInterface_warningMessage", "CPE Target Interface", xml);	//CPE Target Interface
				
				
				addtextFields_commonMethod(application, "Link/Circuit Id", "addNewlink_linkOrcicuittextField", linkOrCircuitID , xml);	//Link/Circuit ID
				
				addDropdownValues_commonMethod(application, "PE Source Device", "addNewlink_PEsourceDeviceDropdown", peSourceDevice, xml);	//PE Source Device
				
				addDropdownValues_commonMethod(application, "PE Source Interface", "addNewLink_PEsourceInterfaceDropdown", peSourceInterface, xml);		//PE Source Interface
				
				addDropdownValues_commonMethod(application, "CPE Target Device", "addNewlink_CPEtargetDevice", CPEtargetDevice, xml);		//CPE Target Device
				
				addDropdownValues_commonMethod(application, "CPE Target Interface", "addNewlink_CPEtargetInterface", CPEtargetInterface, xml);	//CPE Target Interface
				Thread.sleep(2000);
				
				click_commonMethod(application, "Next", "addNewLink_nextButton", xml);		//Next button
				
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Add New Link' Popup not displayed");
				System.out.println("'Add New Link' popup not dispslayed");
			}
		}
		
		
		public void selectInterfaceForCircuits(String application, String interface1, String interface2, String selectEdgePointForInterface1,
				String selectEdgePointForInterface2) throws InterruptedException, DocumentException, IOException {
			
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/addOverture_interfaceInServicePanel")).isDisplayed()){
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Interface in Service' page displays");
				Log.info("'Interface in Service' page displays");
				
				
			//Select row for 1st interface	
				click_commonMethod(application, "interface_filter", "interfaceFilterButton" , xml);
				
				addtextFields_commonMethod(application, "filter" , "interfacePage_filterText", interface1, xml);
				
				WebElement selectInterface	= getwebelement(xml.getlocator("//locators/" + application + "/interfaceInService_selectValueUnderTable").replace("value", interface1));
				Clickon(selectInterface);
				ExtentTestManager.getTest().log(LogStatus.PASS, interface1 + " is selected under 'Interface In Service' page");
				Log.info(interface1 + " is selected under 'Interface In Service' page");
				
				if(selectEdgePointForInterface1.equalsIgnoreCase("Yes")) {
					WebElement selectEdgePoint = getwebelement(xml.getlocator("//locators/" + application + "/interfaceinService_selectEdgePointforInterface").replace("value", interface1));
					Clickon(selectEdgePoint);
					ExtentTestManager.getTest().log(LogStatus.PASS, interface1 + " is selected under 'Interface In Service' page");
					Log.info(interface1 + " is selected under 'Interface In Service' page");
					
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Edge End Point' radio button is not selected for " +  interface1);
					Log.info("'Edge End Point' radio button is not selected for "+ interface1);
				}
				
				
			//select row for 2nd interface	
				click_commonMethod(application, "interface_filter", "interfaceFilterButton" , xml);
				
				edittextFields_commonMethod(application, "filter" , "interfacePage_filterText", interface2, xml);
				Thread.sleep(3000);
				
				WebElement selectInterface2	= getwebelement(xml.getlocator("//locators/" + application + "/interfaceInService_selectValueUnderTable").replace("value", interface2));
				Clickon(selectInterface2);
				ExtentTestManager.getTest().log(LogStatus.PASS, interface2 + " is selected under 'Interface In Service' page");
				Log.info(interface2 + " is selected under 'Interface In Service' page");
				
				if(selectEdgePointForInterface2.equalsIgnoreCase("Yes")) {
					WebElement selectEdgePoint2 = getwebelement(xml.getlocator("//locators/" + application + "/interfaceinService_selectEdgePointforInterface").replace("value", interface2));
					Clickon(selectEdgePoint2);
					ExtentTestManager.getTest().log(LogStatus.PASS, interface2 + " is selected under 'Interface In Service' page");
					Log.info(interface2 + " is selected under 'Interface In Service' page");
					
				}else {
					
					ExtentTestManager.getTest().log(LogStatus.PASS, "'Edge End Point' radio button is not selected for "+  interface2);
					Log.info("'Edge End Point' radio button is not selected for "+  interface2);
				}
				
				Thread.sleep(1000);
				click_commonMethod(application, "OK", "addOverture_OKbutton", xml);
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Interface in Service' page not displays");
				Log.info("'Interface in Service' page not displays");
			}
		}
		
		
		public void addOveture_PAMtest_selectRow(String application,  String interface1) throws InterruptedException, DocumentException {
			
			waitForpageload();  waitforPagetobeenable();
			
			scrolltoend();
			
			WebElement selectInterface	= getwebelement(xml.getlocator("//locators/" + application + "/PAMtest_selectinterface").replace("value", interface1));
			Clickon(selectInterface);
			ExtentTestManager.getTest().log(LogStatus.PASS, interface1 + " is selected for performing 'PAM Test'");
			Log.info(interface1 + " is selected for performing 'PAM Test'");
			
			
			click_commonMethod(application, "Action", "PAMtest_actionDropdown", xml);
			click_commonMethod(application, "PAMTest", "PAMtest_Link", xml);
			
			Thread.sleep(2000);
			waitForpageload();  waitforPagetobeenable();
			
		}
		
		
		public void PAMtest_ForCircuitCreation(String application, String serviceName, String siteName) throws InterruptedException, DocumentException {
			
			boolean pamTestPage=false;
			try {	
			pamTestPage=getwebelement(xml.getlocator("//locators/" + application + "/PAMtest_popupPage")).isDisplayed();
			if(pamTestPage) {
//				ExtentTestManager.getTest().log(LogStatus.PASS, "'PAM Test' popup page is displaying");
				System.out.println("'PAM Test' popup page is displaying");
				
			//Type Value	
				String typeValue=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_TypeFieldValue")));
				if(typeValue.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Type' field");
					System.out.println("No values displaying under 'Type' field");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Type' field, value is dispaying as: "+typeValue);
					System.out.println("Under 'Type' field, value is dispaying as: "+typeValue);
				}
				
			//Service
				String serviceValue=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_ServiceValue")));
				if(serviceValue.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Service' field");
					System.out.println("No values displaying under 'Service' field");
				}else {
					compareText(application, "Service", "PAMTest_ServiceValue" , serviceName, xml);
				}
				
				
			//site
				String siteValue=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_siteValue")));
				if(siteValue.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Site' field");
					System.out.println("No values displaying under 'Site' field");
				}else {
					compareText(application, "Site", "PAMTest_siteValue" , siteName, xml);
				}
				
			//Tool Response
				String toolResponse=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/PAMTest_ToolResponse")));
				if(toolResponse.isEmpty()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No values displaying under 'Tool Response' field");
					System.out.println("No values displaying under 'Tool Response' field");
				}else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Under 'Tool Response' field, value is dispaying as: "+toolResponse);
					System.out.println("Under 'Tool Response' field, value is dispaying as: "+toolResponse);
				}
				
			//click on "X"button to close the popup
				click_commonMethod(application, "X", "configure_alertPopup_xbutton" , xml);
				
			}else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'PAM Test' popup page is not displaying");
				System.out.println("'PAM Test' popup page is not displaying");
			}
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "'PAM Test' popup page is not displaying");
			System.out.println("'PAM Test' popup page is not displaying");
			}
		}

		
		public void deleteCircuit(String application) throws InterruptedException, DocumentException {
			
			scrolltoend();
			click_commonMethod(application, "deleteLink", "PAMTest_deleteButton", xml);
			
			click_commonMethod(application, "deleteCircuitpopup_deleteLink", "deleteCircuit_deleteButton", xml);
			Thread.sleep(1000);
			
			waitForpageload();   waitforPagetobeenable();
			
			verifysuccessmessage(application, "Circuit deleted successfully");
			
		}
		
		public void addAccedianCircuit(String application, String serviceName)
				throws InterruptedException, DocumentException, IOException {

			// Click on Add Accedian-1G Link
			click_commonMethod(application, "Add Accedian-1G", "addCircuit_AccedianLink", xml);
			Thread.sleep(1000);
			waitForpageload();  waitforPagetobeenable();


			boolean overturePanelHeader = getwebelement(xml.getlocator("//locators/" + application + "/addCircuit_Accedian1Gpage_panel")).isDisplayed();
			if(overturePanelHeader) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Accedian-1G' page is displaying");
				Log.info("'Accedian-1G' page is displaying");
				
				addtextFields_commonMethod(application, "Service Name", "addOverture_serviceNameTextField", serviceName , xml);	//Search ame text Field
				
				click_commonMethod(application, "Search", "addOverture_searchButton" , xml);   //Search Button
				Thread.sleep(2000);
				waitForpageload();  waitforPagetobeenable();
				
				
				WebElement selectValueInTable = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderAddOverturePage").replace("value", serviceName));
				try {
					selectValueInTable.isDisplayed();
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Records displays for the Service " + serviceName);
					Log.info("Records displays for the Service " + serviceName);
					
					Clickon(selectValueInTable);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Service selected under the table");
					Thread.sleep(1000);
					
					click_commonMethod(application, "OK", "addOverture_OKbutton", xml);
					Thread.sleep(1000);
					waitForpageload();   waitforPagetobeenable();
					
				}catch(Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No record displays for the Service " + serviceName);
					Log.info("No record displays for the Service " + serviceName);
					
					click_commonMethod(application, "cancel", "addOverture_cancelButton" , xml);
				}
				
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Overture' page is not displaying");
				Log.info("'Overture' page is not displaying");
			}
		}


		public void addAtricaCircuit(String application, String serviceName)
				throws InterruptedException, DocumentException, IOException {

			scrolltoend();
			
			// Click on Add Circuit Link
			click_commonMethod(application, "Add Circuit", "addCircuit_AtricaLink", xml);
			Thread.sleep(1000);
			waitForpageload();  waitforPagetobeenable();


			boolean overturePanelHeader = getwebelement(xml.getlocator("//locators/" + application + "/addCircuit_Atricapage_Panel")).isDisplayed();
			if(overturePanelHeader) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Accedian-1G' page is displaying");
				Log.info("'Accedian-1G' page is displaying");
				
				addtextFields_commonMethod(application, "Service Name", "addOverture_serviceNameTextField", serviceName , xml);	//Search ame text Field
				
				click_commonMethod(application, "Search", "addOverture_searchButton" , xml);   //Search Button
				Thread.sleep(2000);
				waitForpageload();  waitforPagetobeenable();
				
				
				WebElement selectValueInTable = getwebelement(xml.getlocator("//locators/" + application + "/selectValueUnderAddOverturePage").replace("value", serviceName));
				try {
					selectValueInTable.isDisplayed();
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Records displays for the Service " + serviceName);
					Log.info("Records displays for the Service " + serviceName);
					
					Clickon(selectValueInTable);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Service selected under the table");
					Thread.sleep(1000);
					
					click_commonMethod(application, "OK", "addOverture_OKbutton", xml);
					Thread.sleep(1000);
					waitForpageload();   waitforPagetobeenable();
					
				}catch(Exception e) {
					e.printStackTrace();
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No record displays for the Service " + serviceName);
					Log.info("No record displays for the Service " + serviceName);
					
					click_commonMethod(application, "cancel", "addOverture_cancelButton" , xml);
				}
				
			}
			else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "'Overture' page is not displaying");
				Log.info("'Overture' page is not displaying");
			}
			

		}


}
