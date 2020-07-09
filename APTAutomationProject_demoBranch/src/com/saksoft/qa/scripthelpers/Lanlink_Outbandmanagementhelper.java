package com.saksoft.qa.scripthelpers;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Lanlink_Outbandmanagementhelper extends DriverHelper {
	

	
	public Lanlink_Outbandmanagementhelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	WebElement ChooseCustomer_Select, Next_Button, CreateOrderService_Text;
	
	XMLReader xml = new XMLReader("src\\com\\saksoft\\qa\\pagerepository\\ForOutbandmanagement.xml");

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

	public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected, String customerName)
			throws InterruptedException, DocumentException, IOException {
		
		
	System.out.println(" have to select Manage Customer Service");	
	Thread.sleep(3000);
	
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
		DriverTestcase.logger.log(LogStatus.PASS,
				"Step : clicked on next button to verify the mandatory fields error messages");
		Log.info("Step : clicked on next button to verify the mandatory fields error messages");

		//Customer Error message	
				boolean slctcustomer = getwebelement("//div[text()='Customer']").isDisplayed();
				sa.assertTrue(slctcustomer, "Select Customer mandatory warning is not displayed ");
				String customermsg = getwebelement("//div[text()='Customer']").getText();
				Thread.sleep(3000);
				System.out.println("Customter validation message displayed as : " + customermsg);
				DriverTestcase.logger.log(LogStatus.PASS, "Step :  validation message displayed as : " + customermsg);
				Log.info("Customer validation message displayed as : " + customermsg);
				Thread.sleep(3000);
			
		//Entering Customer name in Name field
				try {
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")), customerName);
					Thread.sleep(5000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")), "*");
					
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, "Existing Customer name has been Entered");
				}catch(Exception e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, "Customer field is not available");
				}
		
				
	//Select Customer from dropdown
    boolean customervalue=true;
    boolean avaialbiity=false;
    boolean customervalueDisplayed=false;
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/chooseCustomerdropdown")));
		Thread.sleep(4000);
		WebElement el1 = getwebelement("//div[contains(text(),'"  + ChooseCustomerToBeSelected + "')][1]");
		try {
		avaialbiity=el1.isDisplayed();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(avaialbiity) {
			el1.click();
			Log.info("=== Choose Customer selected===");
			DriverTestcase.logger.log(LogStatus.PASS, "Customer name has been selected from the dropdown");
			System.out.println( "Customer name has been selected from the dropdown");
		}else {
		
		while(customervalue)	{
			getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")).clear();
			Thread.sleep(2000);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")), customerName);
			
			//select the dropodwn again
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/chooseCustomerdropdown")));
			Thread.sleep(4000);
		
			try {
			customervalueDisplayed= getwebelement("//div[contains(text(),'" + ChooseCustomerToBeSelected + "')][1]").isDisplayed();
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(customervalueDisplayed) {
				el1.click();
				customervalue=false;
			}else {
				customervalue=true;
			}
		}
			
		}
	
			
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));
		DriverTestcase.logger.log(LogStatus.PASS, "Next button is selected after selecting customer");
	
		Thread.sleep(3000);

	}
	
	
	public void selectCustomertocreateOrderfromleftpane(String application, String ChooseCustomerToBeSelected, String customerName)
			throws InterruptedException, DocumentException, IOException {

		Thread.sleep(5000);

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
		Thread.sleep(3000);
		System.out.println("Mouser hovered on Manage Customer's Service");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CreateOrderServiceLink")));
		Thread.sleep(2000);
		
		
		Log.info("=== Create Order/Service navigated ===");
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")), customerName);
		Thread.sleep(3000);
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/entercustomernamefield")), "*");
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/chooseCustomerdropdown")));
		Thread.sleep(000);
		System.out.println("-----HAve clicked as expected-----");
		WebElement el1 = driver
				.findElement(By.xpath("//div[contains(text(),'" + ChooseCustomerToBeSelected + "')][1]"));
		el1.click();
		Log.info("=== Choose Customer selected===");

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
		sa.assertAll();
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
		sa.assertAll();
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

	public void select_ServiceType(String application, String ServiceTypeToBeSelected)
			throws IOException, InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceType_Select")));
		Thread.sleep(1000);
		WebElement el1 = driver.findElement(By.xpath("(//div[contains(text(),'" + ServiceTypeToBeSelected + "')])[1]"));
		el1.click();
		Log.info("=== Service Type selected===");
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

	public void MASdevice(String application, String IMSlocation, String selectOrclicktogglebutttontocreateDevice,
			String name, String VendorModel, String ManagementAddress, String Snmpro, String Country, String City,
			String Site, String Premise) throws IOException, InterruptedException, DocumentException {

		System.out.println("----- Going to perform add MAS device actions------------");

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

		Thread.sleep(3000);
	}

	public void SelectServiceType(String application, String OrderNumber, String ServiceTypeToBeSelected,
			String ordertype, String valuetobeselectedinorderdropdown)
			throws InterruptedException, DocumentException, IOException {


		scrolltoend();
		Thread.sleep(3000);

		if (ordertype.equalsIgnoreCase("new")) {
			DriverTestcase.logger.log(LogStatus.INFO, "For new order");
			System.out.println("For new order");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/OrderContractNumbertoggleButton")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/OrderName")), OrderNumber);
			Log.info("Order name has been entered");
			DriverTestcase.logger.log(LogStatus.PASS, "Order number value has been entered for New order");
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
				DriverTestcase.logger.log(LogStatus.PASS, "Order creation success message displays as : "+createordermsg.getText());
			}else {
				System.out.println("Success mesage for new order is not getting displayed");
				DriverTestcase.logger.log(LogStatus.FAIL, "Success mesage for new order creation is not getting displayed");
			}

			ScrolltoElement(createordermsg);
			Thread.sleep(5000);
			
		} else {
			DriverTestcase.logger.log(LogStatus.INFO, "For existing order");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ordercontractNumberdropdown")));
			Clickon(getwebelement("//div[text()='" + valuetobeselectedinorderdropdown + "']"));
			DriverTestcase.logger.log(LogStatus.PASS, "Existing order has been selected");

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
			DriverTestcase.logger.log(LogStatus.FAIL, ServiceTypeToBeSelected+ " is not available under Service type dropdown ");
		}

		DriverTestcase.logger.log(LogStatus.INFO, "The service selected is: "+ServiceTypeToBeSelected);

	}
	
	
	
	public void SelectServiceType2(String application, String OrderNumber, String ServiceTypeToBeSelected,
			String ordertype, String valuetobeselectedinorderdropdown)
			throws InterruptedException, DocumentException, IOException {


		scrolltoend();
		Thread.sleep(3000);

		if (ordertype.equalsIgnoreCase("new")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ordercontractNumberdropdown_secondTime")));
			Clickon(getwebelement("//div[text()='" + OrderNumber + "']"));
			
		
			
		} else {
			DriverTestcase.logger.log(LogStatus.INFO, "For existing order");
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ordercontractNumberdropdown_secondTime")));
			Clickon(getwebelement("//div[text()='" + valuetobeselectedinorderdropdown + "']"));
			DriverTestcase.logger.log(LogStatus.PASS, "Existing order has been selected");

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
			DriverTestcase.logger.log(LogStatus.FAIL, ServiceTypeToBeSelected+ " is not available under Service type dropdown ");
		}

		DriverTestcase.logger.log(LogStatus.INFO, "The service selected is: "+ServiceTypeToBeSelected);

	}

	public void  selectsubtypeunderServiceTypeSelected(String application, String SelectSubService, String Interfacespeed,
			String modularmsp, String autoCreateService, String A_Endtechnologydropdown, String B_Endtechnologydropdown)
			throws InterruptedException, DocumentException, IOException {
		
		
		scrolltoend();
		Thread.sleep(3000);


		if (modularmsp.equalsIgnoreCase("no") && autoCreateService.equalsIgnoreCase("no")) {
			
			DriverTestcase.logger.log(LogStatus.PASS,"when'Modular msp' and 'Autocreate service' are not selected,   'Interface speed' value and 'Service subtype' value should be selected as mandatory ");

			// Select interface speed
	boolean interfaceSpeed=	getwebelement(xml.getlocator("//locators/" + application + "/InterfaceSpeed")).isDisplayed();
	if(interfaceSpeed) {
		DriverTestcase.logger.log(LogStatus.PASS, " 'Interface Speed' dropdown is displaying as expected");
		 if(Interfacespeed.equalsIgnoreCase("null")) {
			 
			 DriverTestcase.logger.log(LogStatus.FAIL, " 'Interface speed' is a mandatory field but No value has been passed as input to 'Interface speed' ");
				
		 }else {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceSpeed")));
			Thread.sleep(3000);
			WebElement interfacesped = driver
					.findElement(By.xpath("//div[contains(text(),'" + Interfacespeed + "')]"));
			interfacesped.click();
			Log.info("=== Interface speed has got selected===");
			DriverTestcase.logger.log(LogStatus.PASS,Interfacespeed +" speed has been selected");
		 }
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Interface Speed' dropdown is not displaying");
	}

	
	
	// select service sub type
	boolean serviceSubTypeAvailability=false;
	serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();

	if(serviceSubTypeAvailability) {
		DriverTestcase.logger.log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
		System.out.println(" 'Service subtype mandatory dropdown is displaying as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
		Thread.sleep(3000);
		System.out.println("clicked on srvice type");
		DriverTestcase.logger.log(LogStatus.PASS, "Service subtype dropdown has been selected");
		
		if((SelectSubService.equals("LANLink International")) || (SelectSubService.equals("LANLink Metro")) || SelectSubService.equals("LANLink National") ||
		         SelectSubService.equals("OLO - (GCR/EU)") || (SelectSubService.equals("Direct Fiber")) || (SelectSubService.equals("LANLink Outband Management"))){

		WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
		el2.click();
		Log.info("=== Service sub Type selected===");
		DriverTestcase.logger.log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
		}
		else{
			Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
			
			DriverTestcase.logger.log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
					+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
					+ "    1) Direct Fiber"
					+ "    2) LANLink International"
					+ "    3) LANLink Metro"
					+ "    4) LANLink National"
					+ "    5) Lanlink Outband management"
					+ "    6) OLO - (GCR/EU)");
			driver.close();
		}

	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
	}

			
//			 clickon(getwebelement(xml.getlocator("//locators/"+application+"/AvailableCircuits")));

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

		else if (modularmsp.equalsIgnoreCase("yes") && autoCreateService.equalsIgnoreCase("no")) {
			
			DriverTestcase.logger.log(LogStatus.INFO,"when 'Modular msp' is selected and 'Autocreateservice' is not selected, 'Service subtype' value should be selected as it is mandatory field ");
			
          try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")));
			DriverTestcase.logger.log(LogStatus.PASS, "Modular msp checkbox has been selected");
          }catch(Exception e) {
        	  e.printStackTrace();
        	  DriverTestcase.logger.log(LogStatus.FAIL, "Modular msp check box is not available");
        	  
          }

	// select service sub type
      	boolean serviceSubTypeAvailability=false;
		serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();
	
		if(serviceSubTypeAvailability) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
			System.out.println(" 'Service subtype mandatory dropdown is displaying as expected");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
			Thread.sleep(3000);
			System.out.println("clicked on srvice type");
			DriverTestcase.logger.log(LogStatus.PASS, "Service subtype dropdown has been selected");
			
			if(SelectSubService.equals("LANLink International") || SelectSubService.equals("LANLink Metro") || SelectSubService.equals("LANLink National") ||
			         SelectSubService.equals("OLO - (GCR/EU)")){

			WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
			el2.click();
			Log.info("=== Service sub Type selected===");
			DriverTestcase.logger.log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
			}
			else{
				Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
				
				DriverTestcase.logger.log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
						+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
						+ "    1) LANLink International"
						+ "    2) LANLink Metro"
						+ "    3) LANLink National"
						+ "    4) OLO - (GCR/EU)");
				driver.close();
			}

		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
		}

//			 SendKeys(getwebelement(xml.getlocator("//locators/"+Application+"/AvailableCircuits")),
//			 Availablecircuits);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

		if (modularmsp.equalsIgnoreCase("no") && autoCreateService.equalsIgnoreCase("yes")) {
			
			
			DriverTestcase.logger.log(LogStatus.INFO, " 'Service subtype' should be selected as mandatory when 'AutocreateService' is selected, 'Modular msp' not selected");
			
			System.out.println("Only auto creta check box is selected");
			
			try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AutocreateServicecheckbox")));
			DriverTestcase.logger.log(LogStatus.PASS, " 'Autocreateservice' checkbox is selected ");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Auto create service' checkbox is not available under 'Service' panel ");
			}

	//A end technology	
			boolean A_EndtechAvailability=false;
		try {
			A_EndtechAvailability=getwebelement(xml.getlocator("//locators/" + application + "/A_Endtechnology")).isDisplayed();
	if(A_EndtechAvailability) {	
		DriverTestcase.logger.log(LogStatus.PASS, " 'A-End technology' dropdown is displaying as expected");
		System.out.println(" 'A-End technology' dropdown is displaying as expected");
		
		if(A_Endtechnologydropdown.equalsIgnoreCase("null")) {	
			System.out.println(" No changes made for 'A-End technology' dropdown");
			DriverTestcase.logger.log(LogStatus.PASS, " No changes made for 'A-End technology' dropdown");
		}else {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/A_EndTechnology_xbuton")));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, " 'A_End technology' dropdown is selected");
		
			Clickon(getwebelement("//div[contains(text(),'" + A_Endtechnologydropdown + "')]"));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, A_Endtechnologydropdown+ " value is selected under 'A_end technology' dropdown");
		}
	}else {
		DriverTestcase.logger.log(LogStatus.PASS, " 'A-End technology' dropdown is not displaying");
		System.out.println(" 'A-End technology' dropdown is not displaying");
	}
		}catch(NoSuchElementException ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'A-End technology' dropdown is not available");
			System.out.println( " 'A-End technology' dropdown is not available");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, A_Endtechnologydropdown+ " value is not availble under 'A_end technology' dropdown");
			System.out.println(A_Endtechnologydropdown+ " value is not availble under 'A_end technology' dropdown");
		}

		
	//B end technology	
		boolean B_EndTechAvailability=false;
		try {	
			B_EndTechAvailability=getwebelement(xml.getlocator("//locators/" + application + "/B_Endtechnology")).isDisplayed();
			
		if(B_EndTechAvailability) {	
			DriverTestcase.logger.log(LogStatus.PASS, " 'B-End TEchnology' dropdown is displaying as expected");
			System.out.println(" 'B-End TEchnology' dropdown is displaying");
			
			if(B_Endtechnologydropdown.equalsIgnoreCase("null")) {	
				System.out.println(" No changes made for 'B-End technology' dropdown");
				DriverTestcase.logger.log(LogStatus.PASS, " No changes made for 'B-End technology' dropdown");
			}else {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/B_EndTechcnology_xbutton")));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, " 'B_End technology' dropdown is selected");

			Clickon(getwebelement("//div[contains(text(),'" + B_Endtechnologydropdown + "')]"));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, B_Endtechnologydropdown+ " value is selected under 'B_end technology' dropdown");
			}
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, " 'B-End TEchnology' dropdown is not displaying");
			System.out.println(" 'B-End TEchnology' dropdown is not displaying");
		}
		}catch(NoSuchElementException ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'B-End technology' dropdown is not available");
			System.out.println( " 'B-End technology' dropdown is not available");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, B_Endtechnologydropdown+ " value is not availble under 'B_end technology' dropdown");
			System.out.println(B_Endtechnologydropdown+ " value is not availble under 'A_end technology' dropdown");
		}
		
	//Interface speed	
		try {
			 if(Interfacespeed.equalsIgnoreCase("null")) {
				 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceSpeed")));
				 System.out.println("interface speed dropdown button is clicked");
				 DriverTestcase.logger.log(LogStatus.PASS, " 'Interface speed' dropdown has been selected");
				
			 }else {
				 
				 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceSpeed")));
				 System.out.println("interface spedd dropdown button is clicked");
				 DriverTestcase.logger.log(LogStatus.PASS, " 'Interface speed' dropdown has been selected");
				 
			 }
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Interface speed' dropdown under 'Service' panel is not available");
		}
				
		
		try {
			 if(Interfacespeed.equalsIgnoreCase("null")) {
				 
				 DriverTestcase.logger.log(LogStatus.FAIL, " 'Interface speed' is a mandatory field but No value has been passed as input to 'Interface speed' ");
					
			 }else {
		
				WebElement interfacesped = driver
						.findElement(By.xpath("//div[contains(text(),'" + Interfacespeed + "')]"));
				Thread.sleep(3000);
				interfacesped.click();
				Thread.sleep(3000);
				Log.info("=== Interface speed has got selected===");
				DriverTestcase.logger.log(LogStatus.PASS, Interfacespeed+ " speed has been selected");
			 }
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,Interfacespeed+ " value is not available under 'Interface speed' dropdown");
			}


	// select service sub type
		
		boolean serviceSubTypeAvailability=false;
		serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();
	
		if(serviceSubTypeAvailability) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
			System.out.println(" 'Service subtype mandatory dropdown is displaying as expected");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
			Thread.sleep(3000);
			System.out.println("clicked on srvice type");
			DriverTestcase.logger.log(LogStatus.PASS, "Service subtype dropdown has been selected");
			
			if((SelectSubService.equals("LANLink International")) || (SelectSubService.equals("LANLink Metro")) || SelectSubService.equals("LANLink National") ||
			         SelectSubService.equals("OLO - (GCR/EU)") || (SelectSubService.equals("Direct Fiber")) || (SelectSubService.equals("LANLink Outband Management"))){

			WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
			el2.click();
			Log.info("=== Service sub Type selected===");
			DriverTestcase.logger.log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
			}
			else{
				Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
				
				DriverTestcase.logger.log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
						+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
						+ "    1) Direct Fiber"
						+ "    2) LANLink International"
						+ "    3) LANLink Metro"
						+ "    4) LANLink National"
						+ "    5) Lanlink Outband management"
						+ "    6) OLO - (GCR/EU)");
				driver.close();
			}

		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
		}

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

		if (modularmsp.equalsIgnoreCase("yes") && autoCreateService.equalsIgnoreCase("yes")) {
			
			DriverTestcase.logger.log(LogStatus.INFO, " 'Service subtype' is mandatory when 'modular msp' and 'Autocreateservices' are selected");

			
		//modular msp	
			 try {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")));
					DriverTestcase.logger.log(LogStatus.PASS, "Modular msp checkbox has been selected");
	              }catch(Exception e) {
	            	  e.printStackTrace();
	            	  DriverTestcase.logger.log(LogStatus.FAIL, "Modular msp check box is not available under 'Service' panel");
	            	  
	              }

		//Auto create service	 
			 try {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AutocreateServicecheckbox")));
					DriverTestcase.logger.log(LogStatus.PASS, " 'Autocreateservice' checkbox is selected ");
					}catch(Exception e) {
						e.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Auto create service' checkbox is not available under 'Service' panel ");
					}

			//A end technology		
				try {
				if(A_Endtechnologydropdown.equalsIgnoreCase("null")) {	
					System.out.println(" No changes made for 'A-End technology' dropdown");
					DriverTestcase.logger.log(LogStatus.PASS, " No changes made for 'A-End technology' dropdown");
				}else {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/A_Endtechnology")));
					DriverTestcase.logger.log(LogStatus.PASS, " 'A_End technology' dropdown is selected");
				
					Clickon(getwebelement("//div[contains(text(),'" + A_Endtechnologydropdown + "')]"));
					DriverTestcase.logger.log(LogStatus.PASS, A_Endtechnologydropdown+ " value is selected under 'A_end technology' dropdown");
				}
				}catch(NoSuchElementException ee) {
					ee.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " 'A-End technology' dropdown is not available");
					System.out.println( " 'A-End technology' dropdown is not available");
				}catch(Exception e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, A_Endtechnologydropdown+ " value is not availble under 'A_end technology' dropdown");
					System.out.println(A_Endtechnologydropdown+ " value is not availble under 'A_end technology' dropdown");
				}

				
			//B end technology	
				
				try {	
					if(A_Endtechnologydropdown.equalsIgnoreCase("null")) {	
						System.out.println(" No changes made for 'B-End technology' dropdown");
						DriverTestcase.logger.log(LogStatus.PASS, " No changes made for 'B-End technology' dropdown");
					}else {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/B_Endtechnology")));
					DriverTestcase.logger.log(LogStatus.PASS, " 'B_End technology' dropdown is selected");

					Clickon(getwebelement("//div[contains(text(),'" + B_Endtechnologydropdown + "')]"));
					DriverTestcase.logger.log(LogStatus.PASS, B_Endtechnologydropdown+ " value is selected under 'B_end technology' dropdown");
					}
				}catch(NoSuchElementException ee) {
					ee.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " 'B-End technology' dropdown is not available");
					System.out.println( " 'B-End technology' dropdown is not available");
				}catch(Exception e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, B_Endtechnologydropdown+ " value is not availble under 'B_end technology' dropdown");
					System.out.println(B_Endtechnologydropdown+ " value is not availble under 'A_end technology' dropdown");
				}


			
		// select service sub type
			boolean serviceSubTypeAvailability=false;
			serviceSubTypeAvailability=	getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")).isDisplayed();
		
			if(serviceSubTypeAvailability) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Service subtype mandatory dropdown is displaying as expected");
				System.out.println(" 'Service subtype mandatory dropdown is displaying as expected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
				Thread.sleep(3000);
				System.out.println("clicked on srvice type");
				DriverTestcase.logger.log(LogStatus.PASS, "Service subtype dropdown has been selected");
				
				if(SelectSubService.equals("LANLink International") || SelectSubService.equals("LANLink Metro") || SelectSubService.equals("LANLink National") ||
				         SelectSubService.equals("OLO - (GCR/EU)")){

				WebElement el2 = getwebelement("//div[contains(text(),'" + SelectSubService + "')]");
				el2.click();
				Log.info("=== Service sub Type selected===");
				DriverTestcase.logger.log(LogStatus.PASS, "Service sub type" +SelectSubService +" has been selected");
				}
				else{
					Log.info(SelectSubService+ " is not available under Service subtype dropdown when Modular msp is selected");
					
					DriverTestcase.logger.log(LogStatus.FAIL,SelectSubService+ " is not available when Modular msp is selected."
							+ "                      The list of sub services types under LANLINK when moduler msp is selected are:"
							+ "    1) LANLink International"
							+ "    2) LANLink Metro"
							+ "    3) LANLink National"
							+ "    4) OLO - (GCR/EU)");
					driver.close();
				}

			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Service subtype mandatory dropdown is not displaying");
			}
			

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next")));
			Thread.sleep(3000);

			Log.info("Page has to be selected based on service and its subtype selected");

		}

	}

	public void VerifyFieldsForServiceSubTypeSelected(String application, String serviceType, String SelectSubService,
			String Interfacespeed, String proActivemonitoring, String vpntopology, String aggregateTraffic, String modularmsp) throws InterruptedException, DocumentException, IOException {

		
		
	if(modularmsp.equalsIgnoreCase("no"))	{		
		if (Interfacespeed.equalsIgnoreCase("10GigE")) {

			Fieldvalidation_DirectFibre10G(application, serviceType, SelectSubService, Interfacespeed, vpntopology);

		}
		else if (Interfacespeed.equalsIgnoreCase("1GigE")) {
			Fieldvalidation_DirectFibre1G(application, serviceType, SelectSubService, Interfacespeed,proActivemonitoring, vpntopology);
		}

	}
	
	else if(modularmsp.equalsIgnoreCase("yes"))	{	
		
		DriverTestcase.logger.log(LogStatus.FAIL, " 'lanlink Outband Management' service will not occur when 'Modular Msp'checkbox is selected is selected");
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
	DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Next Button");
	Log.info("=== Clicked on Next Button ===");

	// WebElement ele=getwebelement(xml.getlocator("//locators/" + application +
	// "/choosocustomerwarningmsg"));
	boolean choosocustomerwarningmsg = getwebelement(
			xml.getlocator("//locators/" + application + "/choosocustomerwarningmsg")).isDisplayed();
	sa.assertTrue(choosocustomerwarningmsg, "next button is displayed");
	System.out.println("choose customer is required message is displayed");
	DriverTestcase.logger.log(LogStatus.PASS, "Step : 'choose customer is required' message is displayed");
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
	DriverTestcase.logger.log(LogStatus.PASS, "Step : Entered Customer Name is : " + name);
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
	DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on customer dropdown");
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
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on customer dropdown");
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
	DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Next Button");
	Log.info("=== Clicked on Next Button ===");

}
	

	// scroll to end
	public void scrolltoend() {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	
	public void scrollToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
	}
	
	public void clickOnBankPage() {
		driver.findElement(By.xpath("//body")).click();
	}

	
//Scroll to particular webelement
	public void ScrolltoElement(WebElement Element) {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",(Element));
		
	}

	public void Verifyfields(String application, String ServiceTypeToBeSelected, String modularMSP,
			String autoCreateService) throws InterruptedException, DocumentException {

System.out.println("came inside verify fields");

scrolltoend();
Thread.sleep(5000);


//Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));
//Thread.sleep(5000);

Log.info("Step : clicked on next button to verify the mandatory fields error messages");

Clickon(getwebelement("//span[contains(text(),'Next')]"));
Thread.sleep(3000);
System.out.println("clicked on next button");
DriverTestcase.logger.log(LogStatus.PASS,
	"Step : clicked on next button to verify the mandatory fields error messages");

//Create Order/Contract Number Error message
	

WebElement wb=getwebelement(xml.getlocator("//locators/" + application + "/order_contractnumberErrmsg"));
System.out.println("the webeleent is : "+ wb);
	boolean order_Contractnumbr = getwebelement(
			xml.getlocator("//locators/" + application + "/order_contractnumberErrmsg")).isDisplayed();
	sa.assertTrue(order_Contractnumbr, "Order/Contract Number mandatory warning is not displayed ");
	String ordererrmsg = getwebelement(xml.getlocator("//locators/" + application + "/order_contractnumberErrmsg"))
			.getText();
	System.out.println("Order/Contract Number message displayed as : " + ordererrmsg);
	DriverTestcase.logger.log(LogStatus.PASS, "Step :  validation message displayed as : " + ordererrmsg);
	Log.info("Order/Contract Number validation message displayed as : " + ordererrmsg);

//Service Type Error message	
	boolean servictypeErr = getwebelement(xml.getlocator("//locators/" + application + "/servicetypeerrmsg"))
			.isDisplayed();
	sa.assertTrue(servictypeErr, "Service Type mandatory warning is not displayed ");
	String servuiceTypeErrorMessage = getwebelement(
			xml.getlocator("//locators/" + application + "/servicetypeerrmsg")).getText();
	System.out.println("Service Type message displayed as : " + servuiceTypeErrorMessage);
	DriverTestcase.logger.log(LogStatus.PASS,
			"Step :  validation message displayed as : " + servuiceTypeErrorMessage);
	Log.info("Service Type validation message displayed as : " + servuiceTypeErrorMessage);
			
	
		String[] Servicetypelists = { "BM (Broadcast Media)", "Domain Management", "DSL", "FAX to Mail", "HSS",
				"IP Access (On-net/Offnet/EoS)", "IP Access Bundle", "IP Transit", "IP VPN", "IP Web/Mail", "LANLink",
				"MDF/MVF/DI", "NGIN", "Number Hosting", "Transmission Link", "Voice Line (V)", "VOIP Access",
				"Wholesale SIP Trunking" };

		System.out.println("order dropdown");
		
		orderdopdown = getwebelement(xml.getlocator("//locators/" + application + "/orderdropdown")).isDisplayed();
		sa.assertTrue(orderdopdown, "Order/Contract Number dropdown is not displayed");
		System.out.println("order dropdown field is verified");
		
		serviceTypedropdown = getwebelement(xml.getlocator("//locators/" + application + "/servicetypedropdowntoclick"))
				.isDisplayed();
		
		sa.assertTrue(serviceTypedropdown, "service type dropdown is not displayed");
		System.out.println("Service type dropdown field is verified");

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/servicetypedropdowntoclick")));
		Thread.sleep(2000);

		// verifying list of service types
		try {
		List<WebElement> listofservicetypes = driver.findElements(By.xpath("//div/span[@role='option']"));
		for (WebElement servicetype : listofservicetypes) {

			boolean match = false;
			for (int i = 0; i < Servicetypelists.length; i++) {
				if (servicetype.getText().equals(Servicetypelists[i])) {
					match = true;
					Log.info("service type name : " + servicetype.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "service type names are : " + servicetype.getText());
				}
			}
			sa.assertTrue(match);

		}
		}catch(Exception e) {
			Log.info("service type lists got mismatched");
			DriverTestcase.logger.log(LogStatus.FAIL, "Service type lists are mismatching");
		}
		// select lanlink service type

	try {	
		WebElement el2 = driver.findElement(By.xpath("//div[contains(text(),'" + ServiceTypeToBeSelected + "')]"));
		el2.click();
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, ServiceTypeToBeSelected+" value is not available under 'Service Subtype' dropdown");
	}
		Log.info("=== Service Type selected===");
		Thread.sleep(3000);
		
		scrolltoend();
		Thread.sleep(5000);
		
	//Click on next button to check mandatory messages	
		Clickon(getwebelement("//span[contains(text(),'Next')]"));
		Thread.sleep(3000);
		System.out.println("clicked on next button");
		DriverTestcase.logger.log(LogStatus.PASS,
				"Step : clicked on next button to verify the mandatory fields error messages");
		Log.info("Step : clicked on next button to verify the mandatory fields error messages");

		
	//Interface Speed Error message	
		boolean interfceSpeedErr = getwebelement(
				xml.getlocator("//locators/" + application + "/interfaceSpeedErrmsg")).isDisplayed();
		sa.assertTrue(interfceSpeedErr, "Interface speed warning is not displayed ");
		String inetrfaceSpeedErrorMessage = getwebelement(
				xml.getlocator("//locators/" + application + "/interfaceSpeedErrmsg")).getText();
		System.out.println(
				"Interface Speed  message displayed as : " + inetrfaceSpeedErrorMessage);
		DriverTestcase.logger.log(LogStatus.PASS,
				"Step :  validation message displayed as : " + inetrfaceSpeedErrorMessage);
		Log.info("Interface Speed  validation message displayed as : " + inetrfaceSpeedErrorMessage);
		
		
	//Service Sub Type Error message	
		boolean serviceSubTypeErr = getwebelement(
				xml.getlocator("//locators/" + application + "/servicesubtypeErrMsg")).isDisplayed();
		sa.assertTrue(serviceSubTypeErr, "Service Sub Type warning is not displayed ");
		String servicesubtypeErrormessage = getwebelement(
				xml.getlocator("//locators/" + application + "/servicesubtypeErrMsg")).getText();
		System.out.println(
				"Service Sub Type  message displayed as : " + servicesubtypeErrormessage);
		DriverTestcase.logger.log(LogStatus.PASS,
				"Step :  validation message displayed as : " + servicesubtypeErrormessage);
		Log.info("Service Sub Type validation message displayed as : " + servicesubtypeErrormessage);

		
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
			
//			if (modularMSP.equalsIgnoreCase("no") && autoCreateService.equalsIgnoreCase("no")) {
			
			DriverTestcase.logger.log(LogStatus.INFO, "When btoh Modular msp and Autocreate Service is not selected, list of fields should occur:"
					+ "1) Interface speed dropdown"
					+ "2) Service Subtype dropdown"
					+ "3) Available circuit dropdown");
				
				verifyinterfaceSpeeddropdown(application);

				verifyservicesubtypesdropdownwhenMSPandAutoCreatenotslected(application);

				verifyavailablecircuitdropdown(application);

//			}
		}
	
		
		else if(i==1) {
			
			DriverTestcase.logger.log(LogStatus.INFO, "When 'Modular msp' is selected but 'Autocreate Service' is not selected, list of fields should occur:"
					+ "1) Service Subtype dropdown"
					+ "2) Available circuit dropdown");

//			else if (modularMSP.equalsIgnoreCase("yes") && autoCreateService.equalsIgnoreCase("no")) {
			
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")));

				verifyservicesubtypesdropdownwhenMSPaloneselected(application);

				verifyavailablecircuitdropdown(application);

//			}
			
		}


		else if(i==2) {
			

//			else if (modularMSP.equalsIgnoreCase("no") && autoCreateService.equalsIgnoreCase("yes")) {
			
			DriverTestcase.logger.log(LogStatus.INFO, "When 'Modular msp' is not selected but 'Autocreate Service' is selected, list of fields should occur: "
					+ "1) A_End technology dropdown"
					+ "2) B_End technology dropdown"
					+ "3) Interface speeed dropdown"
					+ "4) Service Subtype dropdown"
					+ "5) Available circuit dropdown");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")));
				Thread.sleep(2000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AutocreateServicecheckbox")));
				Thread.sleep(1000);

				verifyA_Endtechnologydropdown(application);

				verifyB_Endtechnologydropdowb(application);

				verifyinterfaceSpeeddropdown(application);

				verifyservicesubtypesdropdownwgenAutoCreatealoneselected(application);

				verifyavailablecircuitdropdown(application);

//			}
			
		}
		
		
		else if(i==3) {
			
			
			DriverTestcase.logger.log(LogStatus.INFO, "When both 'Modular msp' and 'Autocreate Service' is selected, list of fields should occur:"
					+ "1) A_End technology"
					+ "2) B_End technology "
					+ "1) Service Subtype dropdown"
					+ "2) Available circuit dropdown");
			
//			else if (modularMSP.equalsIgnoreCase("yes") && autoCreateService.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/modularmspcheckbox")));
				
				verifyA_Endtechnologydropdown(application);

				verifyB_Endtechnologydropdowb(application);

				verifyservicesubtypesdropdownwhenMSPandAutoCreateselected(application);

				verifyavailablecircuitdropdown(application);

//			}
			
			
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
				.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement interfacespeed : listofinterfacespeed) {

			boolean match = false;
			for (int i = 0; i < interfacelist.length; i++) {
				if (interfacespeed.getText().equals(interfacelist[i])) {
					match = true;
					Log.info("interface speeds : " + interfacespeed.getText());
					DriverTestcase.logger.log(LogStatus.PASS,"interface speeds : " + interfacespeed.getText());
					sa.assertTrue(match);
				}
			}
			
		}
		
		} catch (AssertionError error) {
           
			error.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Interface speed' dropdown under Create order detail page is not available");
			
        }catch(Exception e) {
			Log.info("dropdowns values in Interface speed are mismiatching under service type");
			System.out.println("dropdowns values in Interface speed are mismiatching under service type");
			DriverTestcase.logger.log(LogStatus.FAIL, " Interface speed dropdown values are not displaying as expected ");
		}

	}

	public void verifyservicesubtypesdropdownwhenMSPandAutoCreatenotslected(String application) throws InterruptedException, DocumentException {

		String[] servicesubtypelist = { "Direct Fiber", "LANLink International", "LANLink Metro", "LANLink National","LANLink Outband Management", "OLO - (GCR/EU)" };

		try {
		servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
				.isDisplayed();
		sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");
		
		// verify the list of service sub types
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
		
		List<WebElement> listofServicesubtypes = driver
				.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement servicesubtypes : listofServicesubtypes) {

			boolean match = false;
			for (int i = 0; i < servicesubtypelist.length; i++) {
				if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
					match = true;
					Log.info("service sub types : " + servicesubtypes.getText());
					DriverTestcase.logger.log(LogStatus.PASS,"service sub types : " + servicesubtypes.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		
		} catch (AssertionError error) {

			error.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL," 'Service subtypes' dropdown is not available");
			
        }catch(Exception e) {
			Log.info("Dropdown values in Service subtypes are mismatching");
			System.out.println("Dropdown values in Service subtypes are mismatching");
			DriverTestcase.logger.log(LogStatus.FAIL,"Dropdown values in Service subtypes are mismatching");
		}

	}

	

	public void verifyservicesubtypesdropdownwhenMSPaloneselected(String application) throws InterruptedException, DocumentException {

		try {
		String[] servicesubtypelist = { "LANLink International", "LANLink Metro", "LANLink National", "OLO - (GCR/EU)" };

		servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
				.isDisplayed();
		sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");
		
		// verify the list of service sub types
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
		
		List<WebElement> listofServicesubtypes = driver
				.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement servicesubtypes : listofServicesubtypes) {

			boolean match = false;
			for (int i = 0; i < servicesubtypelist.length; i++) {
				if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
					match = true;
					Log.info("service sub types : " + servicesubtypes.getText());
					DriverTestcase.logger.log(LogStatus.PASS,"service sub types : " + servicesubtypes.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		} catch (AssertionError error) {
           
			error.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL," 'Service subtype' dropdown under create order detail page is not available");
			
		}catch (Exception e) {

		Log.info("Dropdown values inside service subtypes are mismatching");
		System.out.println("Dropdown values inside service subtypes are mismatching");
		DriverTestcase.logger.log(LogStatus.FAIL,"Dropdown values inside service subtypes are mismatching");
		}

	}
	
	
	public void verifyservicesubtypesdropdownwgenAutoCreatealoneselected(String application) throws InterruptedException, DocumentException {

		try {
		String[] servicesubtypelist = { "Direct Fiber", "LANLink International", "LANLink Metro", "LANLink National","LANLink Outband Management", "OLO - (GCR/EU)" };

		servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
				.isDisplayed();
		sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");

		// verify the list of service sub types
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));

		List<WebElement> listofServicesubtypes = driver
				.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement servicesubtypes : listofServicesubtypes) {

			boolean match = false;
			for (int i = 0; i < servicesubtypelist.length; i++) {
				if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
					match = true;
					Log.info("service sub types : " + servicesubtypes.getText());
					DriverTestcase.logger.log(LogStatus.PASS,"service sub types : " + servicesubtypes.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		} catch (AssertionError error) {

			  error.printStackTrace();
			  DriverTestcase.logger.log(LogStatus.FAIL,"'Service subtype' dropdown under create order detail page is not available");
			
		}catch(Exception e) {
			Log.info("Dropdown values inside service subtypes are mismatching");
			System.out.println("Dropdwon values inside service subtypes are mismatching");
			DriverTestcase.logger.log(LogStatus.FAIL,"Dropdwon values inside service subtypes are mismatching");
		}

	}
	
	
	
	public void verifyservicesubtypesdropdownwhenMSPandAutoCreateselected(String application) throws InterruptedException, DocumentException {

		try {
		String[] servicesubtypelist = { "LANLink International", "LANLink Metro", "LANLink National", "OLO - (GCR/EU)" };

		servicesubtypesdropdown = getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype"))
				.isDisplayed();
		sa.assertTrue(servicesubtypesdropdown, "Service sub type dropdown is not displayed");

		
		// verify the list of service sub types
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ServiceSubtype")));
		

		List<WebElement> listofServicesubtypes = driver
				.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement servicesubtypes : listofServicesubtypes) {

			boolean match = false;
			for (int i = 0; i < servicesubtypelist.length; i++) {
				if (servicesubtypes.getText().equals(servicesubtypelist[i])) {
					match = true;
					Log.info("service sub types : " + servicesubtypes.getText());
					DriverTestcase.logger.log(LogStatus.PASS,"service sub types are : " + servicesubtypes.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		
	} catch (AssertionError error) {

		  error.printStackTrace();
		  DriverTestcase.logger.log(LogStatus.FAIL,"'Service subtype' dropdown under create order detail page is not available");
		  
	}catch(Exception e){
    	 Log.info("Dropdwon values inside Service subtypes are mismatching");
    	 System.out.println("Dropdwon values inside Service subtypes are mismatching");
    	 DriverTestcase.logger.log(LogStatus.FAIL,"Dropdwon values inside Service subtypes are mismatching");
     }

	}



	
	
	public void verifyavailablecircuitdropdown(String application) throws InterruptedException, DocumentException {
try {
		availablecircuitsdropdown = getwebelement(xml.getlocator("//locators/" + application + "/AvailableCircuits"))
				.isDisplayed();
		
		sa.assertTrue(availablecircuitsdropdown, "available circuit dropdown is not displayed");
		DriverTestcase.logger.log(LogStatus.PASS, "Available circuit dropdown is displayed");
		
}catch(AssertionError e) {
	Log.info("Available circuit dropdown under servicetype got failed");
	System.out.println("Available circuit dropdown under servicetype got failed");
	DriverTestcase.logger.log(LogStatus.FAIL, "Available circuit dropdown is not available under create order detail page");
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
				.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement A_endTechnolnogies : listofA_endTechnologies) {

			boolean match = false;
			for (int i = 0; i < A_endTechnolnogylist.length; i++) {
				if (A_endTechnolnogies.getText().equals(A_endTechnolnogylist[i])) {
					match = true;
					Log.info("A end technology values : " + A_endTechnolnogies.getText());
					DriverTestcase.logger.log(LogStatus.PASS,"A end technology are : " + A_endTechnolnogies.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		} catch (AssertionError error) {

		  error.printStackTrace();
		  DriverTestcase.logger.log(LogStatus.FAIL," A-end technology  dropdown under create order detail page is not available");
		
		}catch(Exception e) {
			Log.info("Dropdwon values inside A-end technology are mismatching");
			System.out.println("Dropdwon values inside A-end technology are mismatching");
			DriverTestcase.logger.log(LogStatus.FAIL,"Dropdwon values inside A-end technology are mismatching");
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
				.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement B_endTechnolnogies : listofB_endTechnologies) {

			boolean match = false;
			for (int i = 0; i < B_endTechnolnogylist.length; i++) {
				if (B_endTechnolnogies.getText().equals(B_endTechnolnogylist[i])) {
					match = true;
					Log.info("B end technology values : " + B_endTechnolnogies.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "B end technologies are : " + B_endTechnolnogies.getText());
					sa.assertTrue(match);
				}
			}
			

		}
		} catch (AssertionError error) {

			  error.printStackTrace();
			  DriverTestcase.logger.log(LogStatus.FAIL," 'B-end technology' dropdown under create order detail page is not available");
			
		}catch(Exception e) {
			Log.info("Dropdwon values inside B-end technology are mismatching");
			System.out.println("Dropdwon values inside B-end technology are mismatching");
			DriverTestcase.logger.log(LogStatus.FAIL,"Dropdwon values inside B-end technology are mismatching");
		}
	}

	public void DirectFibre_10G(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String Manageconnection,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement, 
			String ENNIcheckBox)
			throws InterruptedException, IOException, DocumentException {
		

scrollToTop();
Thread.sleep(3000);
		
	//Service Identification
		createService_ServiceIdentification(application, ServiceIdentificationNumber);
		
		
	//End point CPE	
		createService_singleEndPointCPE(application, EndpointCPE);

		
	//Email	
		createSerivce_email(application, Email);

		
	//Phone Contact	
		createService_phone(application, PhoneContact);

		
	//Remark	
		createService_remark(application, remark);
		
		scrolltoend();
		Thread.sleep(2000);
		
	//Performance Reporting	
		if(!PerformanceReporting.equalsIgnoreCase("null")) {
			
			if (PerformanceReporting.equalsIgnoreCase("yes")) {
				
				boolean perfrmReprtFieldcheck=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();
				if(perfrmReprtFieldcheck) {
					DriverTestcase.logger.log(LogStatus.PASS, " 'Performance reporting' checkbox is displaying under 'Manage ment options' panel in 'Create Service' page as exepcted");
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Thread.sleep(5000);
					
					boolean prfrmReportselection=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
					if(prfrmReportselection) {
						Log.info("performance monitoring check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting checkbox is selected as expected");
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Performance Reporting checkbox is not selected");
					}
				
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Performance Reporting' checkbox is not available");
				}
			}
			else {

				System.out.println("Performance Repoting is not selected");
				DriverTestcase.logger.log(LogStatus.PASS,"performance Reporting checkbox is not selected as expected");
			}
		}


	//Pro active Monitoring	
		createService_proactivemonitoring(application, ProactiveMonitoring, notificationManagement);
	
	//Delivery Channel	
		createService_deliveryChannel(application, deliveryChannel);

	//management Connection	
		createService_managementOptions(application, Manageconnection);

		//ENNI checkbox
				addCheckbox_commonMethod(application, "ENNI_checkbox", "ENNI", ENNIcheckBox);	
		

	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

}
	
	
	
	public void DirectFibre_1G(String application, String ServiceIdentificationNumber, String SelectSubService,
			String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String Manageconnection,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement,
			String perCocPerfrmReprt, String actelsBased, String standrdCir, String standrdEir, String prmiumCir, String prmiumEir, String ENNIcheckBox)
			throws InterruptedException, IOException, DocumentException {
		

scrollToTop();
Thread.sleep(3000);
		
	//Service Identification
		createService_ServiceIdentification(application, ServiceIdentificationNumber);
		
		
	//End point CPE	
		createService_singleEndPointCPE(application, EndpointCPE);

		
	//Email	
		createSerivce_email(application, Email);

		
	//Phone Contact	
		createService_phone(application, PhoneContact);

		
	//Remark	
		createService_remark(application, remark);
		
		scrolltoend();
		Thread.sleep(2000);
		
	//Performance Reporting	
		if(!PerformanceReporting.equalsIgnoreCase("null")) {
			
			if (PerformanceReporting.equalsIgnoreCase("yes")) {
				
				boolean perfrmReprtFieldcheck=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();
				if(perfrmReprtFieldcheck) {
					DriverTestcase.logger.log(LogStatus.PASS, " 'Performance reporting' checkbox is displaying under 'Manage ment options' panel in 'Create Service' page as exepcted");
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Thread.sleep(5000);
					
					boolean prfrmReportselection=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
					if(prfrmReportselection) {
						Log.info("performance monitoring check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting checkbox is selected as expected");
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Performance Reporting checkbox is not selected");
					}
					
					
				//Per CoS Performance Reporting chekcbox	
					boolean perCoSPrfrmReprtFieldcheck=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isDisplayed();
					if(perCoSPrfrmReprtFieldcheck) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'Per CoS Performance Reporting' checkbox is displaying, when 'Performance reporting' checkbox is selected");
						if(perCocPerfrmReprt.equalsIgnoreCase("Yes")){
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));
							Thread.sleep(3000);
							
							boolean perCoSSelection=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isSelected();
							if(perCoSSelection) {
								DriverTestcase.logger.log(LogStatus.PASS, "Per CoS Performance Reporting checkbox is selected as expected");
							}else {
								DriverTestcase.logger.log(LogStatus.FAIL, "Per CoS Performance Reporting checkbox is not selected");
							}
						}else {
							DriverTestcase.logger.log(LogStatus.PASS, " 'Per CoS Performance Reporting' checkbox is not selected as exepected");
						}
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Per CoS Performance Rpeorting' checkbox is not displaying, when 'Performance reporting' checkbox is selected");
					}
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Performance Reporting' checkbox is not available");
				}
			}
			else {

				System.out.println("Performance Repoting is not selected");
				DriverTestcase.logger.log(LogStatus.PASS,"performance Reporting checkbox is not selected as expected");
			}
		}


	//Pro active Monitoring	
		createService_proactivemonitoring(application, ProactiveMonitoring, notificationManagement);
	
	//Delivery Channel	
		createService_deliveryChannel(application, deliveryChannel);

	//management Connection	
		createService_managementOptions(application, Manageconnection);

		//ENNI checkbox
				addCheckbox_commonMethod(application, "ENNI_checkbox", "ENNI", ENNIcheckBox);	
		

	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

}

	public void verifydataEntered_DirectFibre10G(String application, String serviceype,
			String ServiceIdentificationNumber, String SelectSubService, String Interfacespeed, String EndpointCPE,
			String email, String PhoneContact, String remark, String PerformanceMonitoring, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String modularMSP, String notificationManagement,
			String Manageconnectiondropdown, String ENNIcheckBox) throws InterruptedException, IOException, DocumentException {

		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
		
		
		/**
		 * Service Panel	
		 */
			//Service Identification
			verifyEnteredvalues("Service Identification", ServiceIdentificationNumber);
			
			//Service type
			verifyEnteredvalues("Service Type", serviceype);
			
			//Service Subtype
			verifyEnteredvalues("Service Subtype", SelectSubService);
			
			//Interface Speed
			verifyEnteredvalues("Interface Speed", Interfacespeed);
			
			//Single Endpoint CPE
			verifyEnteredvalues("Single Endpoint CPE", EndpointCPE);
			
			//Email
			verifyEnteredvalueForEmail_serviceCreationpage("Email", email);
			
			//Phone Contact
			verifyEnteredvalues("Phone Contact", PhoneContact);
			
			//Modular MSP
			verifyEnteredvalues("Modular MSP", modularMSP);
			
			//Remark
			verifyEnteredvalues("Remark", remark);
		
			WebElement servicePanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_Servicepanel"));
			ScrolltoElement(servicePanel);
			Thread.sleep(3000);
			
			
		/**
		 * Management Options panel	
		 */
			
			//Delivery Channel
			verifyEnteredvalues("Delivery Channel", deliveryChannel);
			
			//Management Connection
			verifyEnteredvalues("Management Connection", Manageconnectiondropdown);
			
			//Proactive Monitoring
			verifyEnteredvalues("Proactive Monitoring", ProactiveMonitoring);
			
			if(ProactiveMonitoring.equalsIgnoreCase("yes")) {
				verifyEnteredvalues("Notification Management Team", notificationManagement);
			}
			
			
			
			//Performance Reporting
				verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
			
			
			
			//ENNI checkbox
			verifyEnteredvalues("ENNI", ENNIcheckBox);
			
		}

	
	public void verifydataEntered_DirectFibre1G(String application, String serviceype,
			String ServiceIdentificationNumber, String SelectSubService, String Interfacespeed, String EndpointCPE,
			String Email, String PhoneContact, String remark, String PerformanceMonitoring, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String modularMSP,String perCocPerfrmReprt, String actelsBased, String standrdCir,
			String standrdEir, String prmiumCir, String prmiumEir, String notificationManagement, String Manageconnectiondropdown, String ENNIcheckBox) 
					throws InterruptedException, IOException, DocumentException {

		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
		
		
		/**
		 * Service Panel	
		 */
			//Service Identification
			verifyEnteredvalues("Service Identification", ServiceIdentificationNumber);
			
			//Service type
			verifyEnteredvalues("Service Type", serviceype);
			
			//Service Subtype
			verifyEnteredvalues("Service Subtype", SelectSubService);
			
			//Interface Speed
			verifyEnteredvalues("Interface Speed", Interfacespeed);
			
			//Single Endpoint CPE
			verifyEnteredvalues("Single Endpoint CPE", EndpointCPE);
			
			//Email
			verifyEnteredvalueForEmail_serviceCreationpage("Email", Email);
			
			//Phone Contact
			verifyEnteredvalues("Phone Contact", PhoneContact);
			
			//Modular MSP
			verifyEnteredvalues("Modular MSP", modularMSP);
			
			//Remark
			verifyEnteredvalues("Remark", remark);
		
			WebElement servicePanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_Servicepanel"));
			ScrolltoElement(servicePanel);
			Thread.sleep(3000);
			
			
		/**
		 * Management Options panel	
		 */
			
			//Delivery Channel
			verifyEnteredvalues("Delivery Channel", deliveryChannel);
			
			//Management Connection
			verifyEnteredvalues("Management Connection", Manageconnectiondropdown);
			
			//Proactive Monitoring
			verifyEnteredvalues("Proactive Monitoring", ProactiveMonitoring);
			
			if(ProactiveMonitoring.equalsIgnoreCase("yes")) {
				verifyEnteredvalues("Notification Management Team", notificationManagement);
			}
			
			
			
			//Performance Reporting
				verifyEnteredvalues("Performance Reporting", PerformanceMonitoring);
			if(PerformanceMonitoring.equalsIgnoreCase("Yes")){
				verifyEnteredvalues("Per CoS Performance Reporting", perCocPerfrmReprt);
				
			}
			
			
			//ENNI checkbox
			verifyEnteredvalues("ENNI", ENNIcheckBox);
			
		}

	
	
	public void Fieldvalidation_DirectFibre10G(String application, String serviceType, String SelectSubService,
			String Interfacespeed,String vpntopology) throws InterruptedException, DocumentException, IOException {
		
		String[] deliverychannel = { "--", "Retail", "Reseller" , "WLC", "WLEC", "CES Solutions"};

		String[] notifyManagement= {"DNA"}; 
		 

		boolean serviceIdentificationField, ServiceType, ServiceSubtype,interfacespeedvalue, singleendpointCPE, email,
				phone, remark, performancereoprting, deliveryChanel, proactiveMonitor, Managementorder,	okButton, cancelButton;
  
		try {	
		
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(3000);
		
			
		     	boolean managementConnection=false;
		     	//management Connection Error Message
		     			try {
		     				managementConnection = getwebelement(xml.getlocator("//locators/" + application + "/manageConnection_warningMesage")).isDisplayed();
		     				Thread.sleep(3000);
		     			sa.assertTrue(managementConnection, " 'Management Connection' dropdown field warning message is not displayed ");
		     			if(managementConnection) {
		     			String nameErrMsg = getwebelement(xml.getlocator("//locators/" + application + "/manageConnection_warningMesage")).getText();
		     			
		     			System.out.println(" 'Management Connection' field warning  message displayed as : " + nameErrMsg);
		     			DriverTestcase.logger.log(LogStatus.PASS,
		     					"Step :  validation message for 'Management Connection' text field displayed as : " + nameErrMsg);
		     			Log.info(" 'Management Connection' field warning message displayed as : " + nameErrMsg);
		     			}else {
		     				System.out.println(" 'Management Connection' field warning message is not dipslaying");
		     				DriverTestcase.logger.log(LogStatus.FAIL, " 'Management Connection' field warning message is not displaying");
		     			}
		     			}catch(NoSuchElementException e) {
		     				e.printStackTrace();
		     				System.out.println(" 'Management Connection' field warning message is not dipslaying");
		     				DriverTestcase.logger.log(LogStatus.FAIL, " 'Management Connection' field warning message is not displaying");
		     			}catch(Exception ed) {
		     				ed.printStackTrace();
		     			}
			
			
		//Service Identification Error message
		    boolean serviceidentifyErr=false;
		    try {
		     serviceidentifyErr = getwebelement(
					xml.getlocator("//locators/" + application + "/serviceIdentificationerrmsg")).isDisplayed();
			sa.assertTrue(serviceidentifyErr, "Service Identification warning is not displayed ");
			String serviceIdentifyErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/serviceIdentificationerrmsg")).getText();
			System.out.println(
					"Service Identification  message displayed as : " + serviceIdentifyErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message displayed as : " + serviceIdentifyErrMsg);
			Log.info("Service Identification  validation message displayed as : " + serviceIdentifyErrMsg);
		    }catch(NoSuchElementException e) {
 				e.printStackTrace();
 				System.out.println(" 'Service Identification' field warning message is not dipslaying");
 				DriverTestcase.logger.log(LogStatus.FAIL, " 'Service Identification' field warning message is not displaying");
 			}catch(Exception ed) {
 				ed.printStackTrace();
 			}

			
	//service Identification	
		serviceIdentificationField = getwebelement(
				xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
		sa.assertTrue(serviceIdentificationField, "Service identification field is not displayed");
		if(serviceIdentificationField) {
			DriverTestcase.logger.log(LogStatus.PASS, " ' Service Identfication' mandatory field is displaynig under 'Add Service' page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " ' Service Identfication' mandatory field is not available under 'Add Service' page");
		}
    	   

     //Service type  
		ServiceType = getwebelement(xml.getlocator("//locators/" + application + "/ServiceType")).isDisplayed();
		sa.assertTrue(ServiceType, "Service type is not displayed");
		if(ServiceType) {
			DriverTestcase.logger.log(LogStatus.PASS,  " 'LANLink' is displying under 'Service type' as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'LANKLink' is not displying under 'Service type'");
		}
		
	//Service subtype
		ServiceSubtype = getwebelement("//div[contains(text(),'" + SelectSubService + "')]").isDisplayed();
		sa.assertTrue(ServiceSubtype, "Service subtype is not displayed");
		if(ServiceSubtype) {
			DriverTestcase.logger.log(LogStatus.PASS, SelectSubService + " is displying under 'Service Sub type' as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, SelectSubService + " is not displying under 'Service Sub type'");
		}
		
	
	//Interface speed 	
	    interfacespeedvalue=getwebelement("//div[contains(text(),'" + Interfacespeed + "')]").isDisplayed();
		sa.assertTrue(interfacespeedvalue, "Interface speed dropdown is not displaying as expected");
		if(interfacespeedvalue) {
			DriverTestcase.logger.log(LogStatus.PASS, Interfacespeed + " is displying under 'Interface Speed' as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, Interfacespeed + " is not displying under 'Interface Speed'");
		}
	
	//Single endpoint cpe
		try {
		singleendpointCPE = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isDisplayed();
		sa.assertTrue(singleendpointCPE, "single End point CPE checkbox is disabled by default");
		if(singleendpointCPE) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Single endpoint cpe' field is displying under 'Create Service'page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL,  " 'Single endpoint cpe' field is not available under 'Create Service' pag");
		}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Single Endpoint CPE' checkbox is not available under 'Create Service' page");
		}
	
		
	//Email	
	try {	
		email = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
		sa.assertTrue(email, "email field is not displayed");
		if(email) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Email' field is displying under 'Create Service'page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Email' field is not available under 'Create Service' pag");
		}
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "'Email' field is not available under 'Create Service' page");
	}	
		
	//phone	
	try {
		phone = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
		sa.assertTrue(phone, "phone contact field is not displayed");
		if(phone) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'phone' field is displying under 'Create Service'page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'phone' field is not available under 'Create Service' page");
		}
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "'Phone' field is not available under 'Create Service' page");
	}	
		
  //remark
try {	
		remark = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
		sa.assertTrue(remark, "remark field is not displayed");
		if(remark) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Remark' field is displying under 'Create Service'page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Remark' field is not available under 'Create Service' page");
		}
}catch(Exception e) {
	e.printStackTrace();
	DriverTestcase.logger.log(LogStatus.FAIL, "'Remark' field is not available under 'Create Service' page");
}	
		

scrolltoend();
Thread.sleep(3000);

//performance Reporting			
		performancereoprting = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox"))
				.isDisplayed();
		sa.assertTrue(performancereoprting,
				"performance reporting checbox is not displayed under 'Create Service' page");
		if(performancereoprting) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'performance Reporting' checkbox is displying under 'Create Service'page as expected");
		
		
		boolean performancereoprtingselection = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
		if(performancereoprtingselection) {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Performance Reporting' checkbox is selected by default under 'Management Options' panel in 'Create Service page'"); 
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "performance Reporting' checkbox is not selected by default under 'Management Options' panel in 'Create Service page as expected");
	
			
		//Per CoS Performance Reporting
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
			Thread.sleep(3000);
			
			boolean perCosreopt=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isDisplayed();
			sa.assertTrue(perCosreopt,
					"Per CoS Performance Reporting checbox is not displayed under 'Create Service' page, when 'Performance Reporting' checkbox is selected");
			if(perCosreopt) {
				DriverTestcase.logger.log(LogStatus.PASS, "Per CoS Performance Reporting checbox is displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox is selected");
			
				boolean perCoSselection=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isSelected();
				if(perCoSselection) {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Per CoS Performance Reporting' checkbox is selected by default under 'Management Options' panel in 'Create Service page'"); 
				}else {
					DriverTestcase.logger.log(LogStatus.PASS, "Per CoS Performance Reporting' checkbox is not selected by default under 'Management Options' panel in 'Create Service page as expected");
					
				}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Per CoS Performance Reporting checbox is not displayed under 'Management Options' panel in 'Create Service' page, when 'Performance Reporting' checkbox is selected");
    	 }
		}
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'performance Reporting' checkbox is not available under 'Create Service' page");		
		}
		
		
	//proactive monitoring			
		proactiveMonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring"))
				.isDisplayed();
		sa.assertTrue(proactiveMonitor,
				"pro active monitoring checkbox is not displayed");
		if(proactiveMonitor) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'proactive monitoring' checkbox is displying under 'Create Service'page as expected");
		
		
		boolean proactiveMonitorselection = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
		if(proactiveMonitorselection) {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'proactive monitoring' checkbox is selected under 'Management Options' panel in 'Create Service page'"); 
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "proactive monitoring' checkbox is not selected under 'Management Options' panel in 'Create Service page as expected");
		
	
	//Notification Management Dropdown	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
		Log.info("Pro active monitoring check box is selected");
		Thread.sleep(3000);
	
	boolean notificationManagement=getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement"))
	.isDisplayed();
	sa.assertTrue(notificationManagement, "Notification management dropdown is not displayed when proactive monitoring is selected");
	Log.info("Notification management dropdown gets displayed when proactive monitoring is selected");
	if(notificationManagement) {
		DriverTestcase.logger.log(LogStatus.PASS, " 'Notification Management' dropdown is displaying under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
	try {
		List<WebElement> listofnotificationmanagement = driver
				.findElements(By.xpath("//span[@role='list']//span[@role='option']"));
		for (WebElement notificationmanagementtypes : listofnotificationmanagement) {

			boolean match = false;
			for (int i = 0; i < notifyManagement.length; i++) {
				if (notificationmanagementtypes.getText().equals(notifyManagement[i])) {
					match = true;
					Log.info("list of notification management are : " + notificationmanagementtypes.getText());
					DriverTestcase.logger.log(LogStatus.PASS,"list of notification management are : " + notificationmanagementtypes.getText());
				}
			}
			sa.assertTrue(match);
			
		}
      }catch(Exception e) {
    	  Log.info("Notification Management dropdown values are mismatching");
    	  e.printStackTrace();
    	  DriverTestcase.logger.log(LogStatus.FAIL,"  values in Notification management dropdown under Direct Fiber service subtype is not displaying as expected");
      }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Notification Management' dropdown is not available under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
		}
		}
	}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'proactive monitoring' checkbox is not available under 'Create Service' page");
		}
	
	
//delivery channel
	try {	
	deliveryChanel = getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
		sa.assertTrue(deliveryChanel, "delivery channel dropdown is not displayed");
		if(deliveryChanel) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Delivery Channel' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
    try {
		List<WebElement> listofdeliverychannel = driver
				.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement deliverychanneltypes : listofdeliverychannel) {

			boolean match = false;
			for (int i = 0; i < deliverychannel.length; i++) {
				if (deliverychanneltypes.getText().equals(deliverychannel[i])) {
					match = true;
					Log.info("list of delivery channel are : " + deliverychanneltypes.getText());
					DriverTestcase.logger.log(LogStatus.INFO," List of Delivery channel dropdown values under Direct Fiber service subtype are: "+deliverychanneltypes.getText());	
					
				}
			}
			sa.assertTrue(match);
		}
	}catch(Exception e) {
    	e.printStackTrace();
    	Log.info("delivery channel dropdown values are mismatching");
    	DriverTestcase.logger.log(LogStatus.FAIL,"  values in delivery channel dropdowns under Direct Fiber service subtype are not displaying as expected");
    }
		} else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Delivery Channel' dropdown is not avilable under 'Management options' panel in 'Create Service' page");
		}
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "'Delivery Channel' dropdown is not available under 'Create Service' page");
	}	

		
    //Management Connection
		Managementorder = getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")).isDisplayed();
		sa.assertTrue(Managementorder, "Management Connection field is not displayed");
		if(Managementorder) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Management Connection' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")));
		
		List<WebElement> listofmanagementOrder = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement mnaagementOrdertypes : listofmanagementOrder) {
			
			System.out.println("Available Management Connection name is : " + mnaagementOrdertypes.getText().toString());
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step : Available Management Connection name is : " + mnaagementOrdertypes.getText().toString());
			Log.info("Available Management Connection name is :" + mnaagementOrdertypes.getText().toString());
			
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Management Connection' dropdown is not available under 'Management options' panel in 'Create Service' page");
		}
		
		//ENNI checkbox
				verfyFields_ENNIcheckbox(application);
			
			//OK button		
				okButton = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
				sa.assertTrue(okButton, "OK button is not displayed");
				
			//cancel button	
				cancelButton = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
				sa.assertTrue(cancelButton, "Cancel button is not displayed");
				
			scrolltoend();
			Thread.sleep(3000);
			
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
				Thread.sleep(3000);
				
				sa.assertAll();
				DriverTestcase.logger.log(LogStatus.PASS," Fields are verified");
		       }catch(AssertionError e) {
		    	 e.printStackTrace();
		    	 DriverTestcase.logger.log(LogStatus.FAIL, "validation failure for Lanlink Outband management service subtype");
		       }	


	}
	
	
	public void Fieldvalidation_DirectFibre1G(String application, String serviceType, String SelectSubService,
			String Interfacespeed,String proActivemonitoring, String vpntopology) throws InterruptedException, DocumentException, IOException {
		
		String[] deliverychannel = { "--", "Retail", "Reseller" , "WLC", "WLEC", "CES Solutions"};

		String[] notifyManagement= {"DNA"}; 
		 

		boolean serviceIdentificationField, ServiceType, ServiceSubtype,interfacespeedvalue, singleendpointCPE, email,
				phone, remark, performancereoprting, deliveryChanel, proactiveMonitor, Managementorder,	okButton, cancelButton;
  
		try {	
		
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(3000);
		
			
		     	boolean managementConnection=false;
		     	//management Connection Error Message
		     			try {
		     				managementConnection = getwebelement(xml.getlocator("//locators/" + application + "/manageConnection_warningMesage")).isDisplayed();
		     				Thread.sleep(3000);
		     			sa.assertTrue(managementConnection, " 'Management Connection' dropdown field warning message is not displayed ");
		     			if(managementConnection) {
		     			String nameErrMsg = getwebelement(xml.getlocator("//locators/" + application + "/manageConnection_warningMesage")).getText();
		     			
		     			System.out.println(" 'Management Connection' field warning  message displayed as : " + nameErrMsg);
		     			DriverTestcase.logger.log(LogStatus.PASS,
		     					"Step :  validation message for 'Management Connection' text field displayed as : " + nameErrMsg);
		     			Log.info(" 'Management Connection' field warning message displayed as : " + nameErrMsg);
		     			}else {
		     				System.out.println(" 'Management Connection' field warning message is not dipslaying");
		     				DriverTestcase.logger.log(LogStatus.FAIL, " 'Management Connection' field warning message is not displaying");
		     			}
		     			}catch(NoSuchElementException e) {
		     				e.printStackTrace();
		     				System.out.println(" 'Management Connection' field warning message is not dipslaying");
		     				DriverTestcase.logger.log(LogStatus.FAIL, " 'Management Connection' field warning message is not displaying");
		     			}catch(Exception ed) {
		     				ed.printStackTrace();
		     			}
			
			
		//Service Identification Error message
		    boolean serviceidentifyErr=false;
		    try {
		     serviceidentifyErr = getwebelement(
					xml.getlocator("//locators/" + application + "/serviceIdentificationerrmsg")).isDisplayed();
			sa.assertTrue(serviceidentifyErr, "Service Identification warning is not displayed ");
			String serviceIdentifyErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/serviceIdentificationerrmsg")).getText();
			System.out.println(
					"Service Identification  message displayed as : " + serviceIdentifyErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message displayed as : " + serviceIdentifyErrMsg);
			Log.info("Service Identification  validation message displayed as : " + serviceIdentifyErrMsg);
		    }catch(NoSuchElementException e) {
 				e.printStackTrace();
 				System.out.println(" 'Service Identification' field warning message is not dipslaying");
 				DriverTestcase.logger.log(LogStatus.FAIL, " 'Service Identification' field warning message is not displaying");
 			}catch(Exception ed) {
 				ed.printStackTrace();
 			}

			
	//service Identification	
		serviceIdentificationField = getwebelement(
				xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
		sa.assertTrue(serviceIdentificationField, "Service identification field is not displayed");
		if(serviceIdentificationField) {
			DriverTestcase.logger.log(LogStatus.PASS, " ' Service Identfication' mandatory field is displaynig under 'Add Service' page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " ' Service Identfication' mandatory field is not available under 'Add Service' page");
		}
    	   

     //Service type  
		ServiceType = getwebelement(xml.getlocator("//locators/" + application + "/ServiceType")).isDisplayed();
		sa.assertTrue(ServiceType, "Service type is not displayed");
		if(ServiceType) {
			DriverTestcase.logger.log(LogStatus.PASS,  " 'LANLink' is displying under 'Service type' as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'LANKLink' is not displying under 'Service type'");
		}
		
	//Service subtype
		ServiceSubtype = getwebelement("//div[contains(text(),'" + SelectSubService + "')]").isDisplayed();
		sa.assertTrue(ServiceSubtype, "Service subtype is not displayed");
		if(ServiceSubtype) {
			DriverTestcase.logger.log(LogStatus.PASS, SelectSubService + " is displying under 'Service Sub type' as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, SelectSubService + " is not displying under 'Service Sub type'");
		}
		
	
	//Interface speed 	
	    interfacespeedvalue=getwebelement("//div[contains(text(),'" + Interfacespeed + "')]").isDisplayed();
		sa.assertTrue(interfacespeedvalue, "Interface speed dropdown is not displaying as expected");
		if(interfacespeedvalue) {
			DriverTestcase.logger.log(LogStatus.PASS, Interfacespeed + " is displying under 'Interface Speed' as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, Interfacespeed + " is not displying under 'Interface Speed'");
		}
	
	//Single endpoint cpe
		try {
		singleendpointCPE = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isDisplayed();
		sa.assertTrue(singleendpointCPE, "single End point CPE checkbox is disabled by default");
		if(singleendpointCPE) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Single endpoint cpe' field is displying under 'Create Service'page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL,  " 'Single endpoint cpe' field is not available under 'Create Service' pag");
		}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Single Endpoint CPE' checkbox is not available under 'Create Service' page");
		}
	
		
	//Email	
	try {	
		email = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
		sa.assertTrue(email, "email field is not displayed");
		if(email) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Email' field is displying under 'Create Service'page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Email' field is not available under 'Create Service' pag");
		}
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "'Email' field is not available under 'Create Service' page");
	}	
		
	//phone	
	try {
		phone = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
		sa.assertTrue(phone, "phone contact field is not displayed");
		if(phone) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'phone' field is displying under 'Create Service'page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'phone' field is not available under 'Create Service' page");
		}
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "'Phone' field is not available under 'Create Service' page");
	}	
		
  //remark
try {	
		remark = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
		sa.assertTrue(remark, "remark field is not displayed");
		if(remark) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Remark' field is displying under 'Create Service'page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Remark' field is not available under 'Create Service' page");
		}
}catch(Exception e) {
	e.printStackTrace();
	DriverTestcase.logger.log(LogStatus.FAIL, "'Remark' field is not available under 'Create Service' page");
}	
		

scrolltoend();
Thread.sleep(3000);

//performance Reporting			
		performancereoprting = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox"))
				.isDisplayed();
		sa.assertTrue(performancereoprting,
				"performance reporting checbox is not displayed under 'Create Service' page");
		if(performancereoprting) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'performance Reporting' checkbox is displying under 'Create Service'page as expected");
		
		
		boolean performancereoprtingselection = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
		if(performancereoprtingselection) {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Performance Reporting' checkbox is selected by default under 'Management Options' panel in 'Create Service page'"); 
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "performance Reporting' checkbox is not selected by default under 'Management Options' panel in 'Create Service page as expected");
	
		}
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'performance Reporting' checkbox is not available under 'Create Service' page");		
		}
		
		
	//proactive monitoring			
		proactiveMonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring"))
				.isDisplayed();
		sa.assertTrue(proactiveMonitor,
				"pro active monitoring checkbox is not displayed");
		if(proactiveMonitor) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'proactive monitoring' checkbox is displying under 'Create Service'page as expected");
		
		
		boolean proactiveMonitorselection = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
		if(proactiveMonitorselection) {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'proactive monitoring' checkbox is selected under 'Management Options' panel in 'Create Service page'"); 
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "proactive monitoring' checkbox is not selected under 'Management Options' panel in 'Create Service page as expected");
		
	
	//Notification Management Dropdown	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
		Log.info("Pro active monitoring check box is selected");
		Thread.sleep(3000);
	
	boolean notificationManagement=getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement"))
	.isDisplayed();
	sa.assertTrue(notificationManagement, "Notification management dropdown is not displayed when proactive monitoring is selected");
	Log.info("Notification management dropdown gets displayed when proactive monitoring is selected");
	if(notificationManagement) {
		DriverTestcase.logger.log(LogStatus.PASS, " 'Notification Management' dropdown is displaying under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
	try {
		List<WebElement> listofnotificationmanagement = driver
				.findElements(By.xpath("//span[@role='list']//span[@role='option']"));
		for (WebElement notificationmanagementtypes : listofnotificationmanagement) {

			boolean match = false;
			for (int i = 0; i < notifyManagement.length; i++) {
				if (notificationmanagementtypes.getText().equals(notifyManagement[i])) {
					match = true;
					Log.info("list of notification management are : " + notificationmanagementtypes.getText());
					DriverTestcase.logger.log(LogStatus.PASS,"list of notification management are : " + notificationmanagementtypes.getText());
				}
			}
			sa.assertTrue(match);
			
		}
      }catch(Exception e) {
    	  Log.info("Notification Management dropdown values are mismatching");
    	  e.printStackTrace();
    	  DriverTestcase.logger.log(LogStatus.FAIL,"  values in Notification management dropdown under Direct Fiber service subtype is not displaying as expected");
      }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Notification Management' dropdown is not available under 'Management Options' panel when 'proactive Monitoring' checkbox is selected");
		}
		}
	}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'proactive monitoring' checkbox is not available under 'Create Service' page");
		}
	
	
//delivery channel
	try {	
	deliveryChanel = getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
		sa.assertTrue(deliveryChanel, "delivery channel dropdown is not displayed");
		if(deliveryChanel) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Delivery Channel' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
    try {
		List<WebElement> listofdeliverychannel = driver
				.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement deliverychanneltypes : listofdeliverychannel) {

			boolean match = false;
			for (int i = 0; i < deliverychannel.length; i++) {
				if (deliverychanneltypes.getText().equals(deliverychannel[i])) {
					match = true;
					Log.info("list of delivery channel are : " + deliverychanneltypes.getText());
					DriverTestcase.logger.log(LogStatus.INFO," List of Delivery channel dropdown values under Direct Fiber service subtype are: "+deliverychanneltypes.getText());	
					
				}
			}
			sa.assertTrue(match);
		}
	}catch(Exception e) {
    	e.printStackTrace();
    	Log.info("delivery channel dropdown values are mismatching");
    	DriverTestcase.logger.log(LogStatus.FAIL,"  values in delivery channel dropdowns under Direct Fiber service subtype are not displaying as expected");
    }
		} else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Delivery Channel' dropdown is not avilable under 'Management options' panel in 'Create Service' page");
		}
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "'Delivery Channel' dropdown is not available under 'Create Service' page");
	}	

		
    //Management Connection
		Managementorder = getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")).isDisplayed();
		sa.assertTrue(Managementorder, "Management Connection field is not displayed");
		if(Managementorder) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Management Connection' dropdown is displaying under 'Management options' panel in 'Create Service' page as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")));
		
		List<WebElement> listofmanagementOrder = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
		for (WebElement mnaagementOrdertypes : listofmanagementOrder) {
			
			System.out.println("Available Management Connection name is : " + mnaagementOrdertypes.getText().toString());
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step : Available Management Connection name is : " + mnaagementOrdertypes.getText().toString());
			Log.info("Available Management Connection name is :" + mnaagementOrdertypes.getText().toString());
			
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Management Connection' dropdown is not available under 'Management options' panel in 'Create Service' page");
		}
		
		//ENNI checkbox
				verfyFields_ENNIcheckbox(application);
			
			//OK button		
				okButton = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
				sa.assertTrue(okButton, "OK button is not displayed");
				
			//cancel button	
				cancelButton = getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
				sa.assertTrue(cancelButton, "Cancel button is not displayed");
				
			scrolltoend();
			Thread.sleep(3000);
			
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
				Thread.sleep(3000);
				
				sa.assertAll();
				DriverTestcase.logger.log(LogStatus.PASS," Fields are verified");
		       }catch(AssertionError e) {
		    	 e.printStackTrace();
		    	 DriverTestcase.logger.log(LogStatus.FAIL, "validation failure for Lanlink Outband management service subtype");
		       }	


	}
	
	
		
	public void dataToBeEnteredOncesubservicesselected(String application, String SelectSubService, String Interfacespeed,
			String ServiceIdentificationNumber, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType,
			String AggregateTraffic, String DeliveryChannelForselecttag, String modularmsp, String autoCreateService,
			String ENNIcheckBox, String Manageconnectiondropdown, String A_Endtechnologydropdown,
			String B_Endtechnologydropdown, String notificationManagement, String Performancereporting,
			String perCocPerfrmReprt, String actelsBased, String standrdCir, String standrdEir, String prmiumCir, String prmiumEir) throws InterruptedException, IOException, DocumentException {

		Thread.sleep(5000);

	if(modularmsp.equalsIgnoreCase("no")) {	
		
		if (Interfacespeed.equalsIgnoreCase("10GigE")) {

			DirectFibre_10G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE, Email,
					PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel, Manageconnectiondropdown,
					vpnTopology, intermediateTechnology, CircuitReference, CircuitType,notificationManagement, ENNIcheckBox);

		}
		
		else if (Interfacespeed.equalsIgnoreCase("1GigE")) {

			DirectFibre_1G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE, Email,
					PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel, Manageconnectiondropdown,
					vpnTopology, intermediateTechnology, CircuitReference, CircuitType,notificationManagement,perCocPerfrmReprt,actelsBased,
					standrdCir, standrdEir, prmiumCir,prmiumEir, ENNIcheckBox);

		}
	}
	
	

	else if(modularmsp.equalsIgnoreCase("yes")) {	
		

	 DriverTestcase.logger.log(LogStatus.FAIL, "When Modular MSP checkbox is selected, 'Lanlink Outband Management' service will not display");
		Thread.sleep(3000);
	
		
	}
}
	
	
	
	
	public void EditTheservicesselected(String application, String SelectSubService, String Interfacespeed,
			String ServiceIdentificationNumber, String EndpointCPE, String Email, String PhoneContact, String remark,
			String PerformanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
			String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType,
			String AggregateTraffic, String DeliveryChannelForselecttag, String modularmsp, String autoCreateService,
			String ENNIcheckBox, String Manageconnectiondropdown, String A_Endtechnologydropdown,
			String B_Endtechnologydropdown, String notificationManagement,
			String perCoSperformanceReport, String actelisBased, String standardCIR, String standardEIR, String premiumCIR, String premiumEIR, String circuitTypeUsedInServiceCreation) throws InterruptedException, IOException, DocumentException {


		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
		
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_actiondropdown")));
	System.out.println("Action dropdown is working");
	Thread.sleep(3000);
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_Editlink")));
	Thread.sleep(8000);
	

if(modularmsp.equalsIgnoreCase("no")) {	
	
	if (Interfacespeed.equalsIgnoreCase("10GigE")) {

		Edit_DirectFibre10G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE, Email,
				PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel, ManagementOrder,
				vpnTopology, intermediateTechnology, CircuitReference, circuitTypeUsedInServiceCreation,notificationManagement,
				Manageconnectiondropdown, ENNIcheckBox);

	}
	
	else if (Interfacespeed.equalsIgnoreCase("1GigE")) {


		Edit_DirectFibre1G(application, ServiceIdentificationNumber, SelectSubService, Interfacespeed, EndpointCPE, Email,
				PhoneContact, remark, PerformanceReporting, ProactiveMonitoring, deliveryChannel, ManagementOrder,
				vpnTopology, intermediateTechnology, CircuitReference, circuitTypeUsedInServiceCreation,notificationManagement,
				perCoSperformanceReport, actelisBased, standardCIR, standardEIR, premiumCIR, premiumEIR, Manageconnectiondropdown, ENNIcheckBox);
		

		DriverTestcase.logger.log(LogStatus.PASS,"Values has been Edited");
	
		
	}
}else {
	DriverTestcase.logger.log(LogStatus.FAIL, "Edit Service for 'Lanlink OutbandManagement' cannot be performed as 'modular MSP' checkbox is selected");
}

	Thread.sleep(3000);

}
	
	


	public void verifysuccessmessageforCreateService(String application) throws InterruptedException {
		
	try {	
		
		boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).isDisplayed();
		
		String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
		if(successMsg) {
			
			boolean alertmessage = getwebelement("//div[text()='Service successfully created.']").isDisplayed();
			String alrtmsg=getwebelement("//div[text()='Service successfully created.']").getText();
			Log.info("Service has been created successfully");
			if(alertmessage) {
				DriverTestcase.logger.log(LogStatus.PASS, " Service creation message displaying as: "+alrtmsg);
				DriverTestcase.logger.log(LogStatus.PASS," 'service successfully created' message is verified");
				System.out.println("Success message displayed");
			}else {
				
				DriverTestcase.logger.log(LogStatus.FAIL, "Service creation message is displaying but the success message display as: "+ actualmsg);
				DriverTestcase.logger.log(LogStatus.INFO, "Expected: service creation message should display as: 'Service successfully created.'");
			}
			
		}else {
			
			DriverTestcase.logger.log(LogStatus.FAIL, " 'service successfully created' message is not displaying after creating service");
			
		}
		
		
	}catch(Exception e) {
		Log.info("failure in fetching success message - 'Service created Successfully'  ");
		DriverTestcase.logger.log(LogStatus.FAIL, " 'service successfully created' message is not displaying after creating service");
		System.out.println("Success message is not getting dislpayed");
	}

	
	}
	
	
	
	public void verifysuccessmessageforEditService(String application) throws InterruptedException, DocumentException {
		
		
			
			boolean successMsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).isDisplayed();
			
			String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
			if(successMsg) {
				
				boolean alertmessage = getwebelement("//span[contains(text(),'Service updated succesully')]").isDisplayed();
				String alrtmsg=getwebelement(xml.getlocator("//locators/" + application + "/AlertForServiceCreationSuccessMessage")).getText();
				Log.info("Service has been updated successfully");
				if(alertmessage) {
					DriverTestcase.logger.log(LogStatus.PASS, " Service Updation message displaying as: "+alrtmsg);
					DriverTestcase.logger.log(LogStatus.PASS," 'Service updated succesully' message is verified");
					System.out.println("Success message displayed");
				}else {
					
					DriverTestcase.logger.log(LogStatus.FAIL, "Service Updation message is displaying but the success message display as: "+ actualmsg);
					DriverTestcase.logger.log(LogStatus.INFO, "Expected: service Updation message should display as: 'Service updated succesully'");
				}
				
			}else {
				
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Service updated succesully' message is not displaying after creating service");
				
			}
			
			
		}
	
	
	
	public void verifysuccessmessageforSiteOrder() throws InterruptedException {
		
	scrollToTop();
	Thread.sleep(3000);
	
		try {	
			boolean alertmessage = getwebelement("//span[text()='Site order created successfully']").isDisplayed();
			Log.info("Site order has been created successfully");
			DriverTestcase.logger.log(LogStatus.PASS," 'Site order created successfully' message is verified");
			System.out.println("Success message displayed");
			
		}catch(Exception e) {
			Log.info("failure in fetching success message - 'Site order created successfully'  ");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'site order created successfully' message is not displaying after creating service");
			System.out.println("Success message is not getting dislpayed");
		}

		
		}
	

	public void VerifydatenteredForServiceSubTypeSelected(String application, String serviceType,
			String SelectSubService, String Interfacespeed, String ServiceIdentificationNumber, String EndpointCPE,
			String Email, String PhoneContact, String remark, String PerformanceMonitoring, String ProactiveMonitoring,
			String deliveryChannel, String ManagementOrder, String vpnTopology, String intermediateTechnology,
			String CircuitReference, String CircuitType, String AggregateTraffic, String DeliveryChannelForselecttag,
			String modularmsp, String autoCreateService, String ENNIcheckBox, String Manageconnectiondropdown,
			String A_Endtechnologydropdown, String B_Endtechnologydropdown, String Performancereporting,
			String perCocPerfrmReprt, String actelsBased, String standrdCir, String standrdEir, String prmiumCir, String prmiumEir, String notificationManagement)
			throws InterruptedException, IOException, DocumentException {

		Thread.sleep(2000);
		try {	
		
		if(modularmsp.equalsIgnoreCase("no")) {	
			
			if (Interfacespeed.equalsIgnoreCase("10GigE")) {

				verifydataEntered_DirectFibre10G(application, serviceType, ServiceIdentificationNumber, SelectSubService,
						Interfacespeed, EndpointCPE, Email, PhoneContact, remark, PerformanceMonitoring,
						ProactiveMonitoring, deliveryChannel, ManagementOrder, vpnTopology, intermediateTechnology,
						CircuitReference, CircuitType, modularmsp,notificationManagement, Manageconnectiondropdown, ENNIcheckBox);


			}
			
			else if (Interfacespeed.equalsIgnoreCase("1GigE")) {

				verifydataEntered_DirectFibre1G(application, serviceType, ServiceIdentificationNumber, SelectSubService,
						Interfacespeed, EndpointCPE, Email, PhoneContact, remark, PerformanceMonitoring,
						ProactiveMonitoring, deliveryChannel, ManagementOrder, vpnTopology, intermediateTechnology,
						CircuitReference, CircuitType, modularmsp,perCocPerfrmReprt, actelsBased, standrdCir, standrdEir,
						prmiumCir, prmiumEir, notificationManagement, Manageconnectiondropdown, ENNIcheckBox);

			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Direct Fiber' service will not occur when 'modular MSP' is selected");
		}
			
		sa.assertAll();
		
		}catch(AssertionError e) {
			Log.info("validation failed for verify Direct Fiber service subtype page ");
			e.printStackTrace();
		}
	}

	public void addsiteorder(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection, String interfaceSpeed)throws InterruptedException, DocumentException, IOException {



	//Existing Country
		if(country.equalsIgnoreCase("null")) {
			
			DriverTestcase.logger.log(LogStatus.FAIL, "Country is a mandatory field and the value is not provided ");
		}else {
		  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")));
		  Clickon(getwebelement("//div[text()='"+ country +"']"));
		  DriverTestcase.logger.log(LogStatus.PASS,country+ " has been selected under 'Country' dropdown");
		}
	

	//Existing City	
		if(existingcityselection.equalsIgnoreCase("yes")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_City")));
			Clickon(getwebelement("//div[text()='" + city + "']"));
			
			DriverTestcase.logger.log(LogStatus.PASS, city+ " is selected under Device Xng City dropdown");
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "Existing City is not selected");
		}
		
		
	//new City	
		if(newcityselection.equalsIgnoreCase("yes")) {
			
			 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
			 Thread.sleep(3000);
			 
			//City name 
			 if(xngcityname.equalsIgnoreCase("null")) {
				 DriverTestcase.logger.log(LogStatus.FAIL, "City name field is a mandatory field and the value is not provided");
			 }else {
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname")), xngcityname);
			 DriverTestcase.logger.log(LogStatus.PASS, xngcityname+ " is entered in City name field");
			 Thread.sleep(3000);
			 }
			 
			 //City code
			 if(xngcitycode.equalsIgnoreCase("null")) {
				 DriverTestcase.logger.log(LogStatus.FAIL, "City Code field is a mandatory field and the value is not provided");
			 }else {
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode")), xngcitycode);
			 Thread.sleep(3000);
			 DriverTestcase.logger.log(LogStatus.PASS, xngcitycode+" is entered in City Code field" );
			 }
			 
			 DriverTestcase.logger.log(LogStatus.PASS, "New City is created");
	
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "New city is not created");
		}
		
		
	//Existing Site	
		if(existingsiteselection.equalsIgnoreCase("yes")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")));
			
			if(sitevalue.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "Physical Site field is mandatory and no values are provided" );
			}else {
			Clickon(getwebelement("//div[text()='" + sitevalue + "']"));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, sitevalue+  " is selected under Physical Site dropdown");
			}
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "Existing Site is not selected");
		}
		
		
	//New Site	
		if (newsiteselection.equalsIgnoreCase("yes")) {
			
			if(CSR_Name.equalsIgnoreCase("null")){
				DriverTestcase.logger.log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
				
			}else {
				
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
			}

		} else {

			DriverTestcase.logger.log(LogStatus.PASS, "CSR name is not created");

		}
		
	//Perfomance Reporting	
		if(performReport.equalsIgnoreCase("null")) {
			
			DriverTestcase.logger.log(LogStatus.PASS, "Performance reporting value is not provided. 'Follow Service' is selected by default");
		}else {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[text()='" + performReport + "']"));
		DriverTestcase.logger.log(LogStatus.PASS, performReport + " is selected under Performance reporting dropdown");
		}

	//Pro active monitoring	
		if(ProactiveMonitor.equalsIgnoreCase("null")) {
			DriverTestcase.logger.log(LogStatus.PASS, "Pro active monitoring value is not provided. 'Follow Service' is selected by default");
			
		}else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[text()='" + ProactiveMonitor + "']"));
		Thread.sleep(3000);
		DriverTestcase.logger.log(LogStatus.PASS, ProactiveMonitor+ " is selected under proactive monitoring dropdown");
		}

		
	//Smart monitoring
		if(smartmonitor.equalsIgnoreCase("null")) {
			DriverTestcase.logger.log(LogStatus.PASS, "Smart monitoring value is not provided. 'Follow Service' is selected by default");
		}else {
			
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[text()='" + smartmonitor + "']"));
		Thread.sleep(3000);
		DriverTestcase.logger.log(LogStatus.PASS, smartmonitor+ " is selected under Smart monitoring dropdown");
		}
	
		
		//Site Allias
				if(siteallias.equalsIgnoreCase("null")) {
					DriverTestcase.logger.log(LogStatus.PASS, "No values entered for 'Site Alias' field");
				}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")), siteallias);
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, siteallias+ " is entered under 'Site Alias' field");
				}

			//Vlan id
				if(VLANid.equalsIgnoreCase("null")) {
					DriverTestcase.logger.log(LogStatus.PASS, "No values entered for 'Vlan id' field");
				}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")), VLANid);
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, VLANid + " is entered under Vlan id field");
				}

			//DCA Enabled Site	
				if (DCAenabledsite.equalsIgnoreCase("yes")) {

					Clickon(getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
					DriverTestcase.logger.log(LogStatus.PASS, "DCA enabled checkbox is selected");
					
				 //Cloud Service provider
					if(cloudserviceprovider.equalsIgnoreCase("null")) {
						DriverTestcase.logger.log(LogStatus.FAIL, "DCA clous service provider dropdown is mandatory. No values are provided");
					}else {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, cloudserviceprovider +  " is selected under 'cloud service provider' dropdown");
					}

				} else {
					Log.info("DCA site is not selected");
					DriverTestcase.logger.log(LogStatus.PASS, "DCA enabled checkbox is not selected");

				}
				
			//Remark
				if(remark.equalsIgnoreCase("null")) {
					DriverTestcase.logger.log(LogStatus.PASS, "No values entered under remark ");
				}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")), remark);
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, remark + " is entered under 'remark' field");
				}
	
				

		
			

	//Technology
		if(technology.equalsIgnoreCase("null")) {
			DriverTestcase.logger.log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
		}else {
		
			if(interfaceSpeed.equals("1GigE")) {	
			
		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equals("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[text()='" + technology + "']"));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
			
			
			
			if(technology.equals("Actelis")) {	
				
			     System.out.println("No additional fields displays");
			}
			

			else if(technology.equals("Atrica")) {
				
				
			//Device name
				if(devicename.equalsIgnoreCase("null")) {
					DriverTestcase.logger.log(LogStatus.FAIL, "device name field is mandatory. No values entered under 'device name' field");
				}else {
				
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")), devicename);
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, devicename + " is entered under 'device name' field");
				}
			
				
			//Non- termination point	
				if(nonterminatepoinr.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point checkbox is selected");
				}else {
					System.out.println("Non termination point checkbox is not selected as expected");
					DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point chekbox is not selected");
				}
				
				
			//Protected	
				if(Protected.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is selected");
				}else {
					System.out.println("Protected checkbox is not selecetd as expected");
					DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is not selected");
				}

			}
			
			

			else if(technology.equals("Overture") || technology.equals("Accedian-1G")) {	

			
						
			//Non- termination point	
				if(nonterminatepoinr.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point checkbox is selected");
				}else {
					System.out.println("Non termination point checkbox is not selected as expected");
					DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point chekbox is not selected");
				}
				
				
			//Protected	
				if(Protected.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is selected");
				}else {
					System.out.println("Protected checkbox is not selecetd as expected");
					DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is not selected");
				}
				

			}
			

			else if(technology.equals("Cyan")) {	

						
			//Non- termination point	
				if(nonterminatepoinr.equalsIgnoreCase("yes")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point checkbox is selected");
				}else {
					System.out.println("Non termination point checkbox is not selected as expected");
					DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point chekbox is not selected");
				}
				
			
			}
			
			
			else if(technology.equals("Alu")) {
				
				//Device name
						if(devicename.equalsIgnoreCase("null")) {
							DriverTestcase.logger.log(LogStatus.FAIL, "device name field is mandatory. No values entered under 'device name' field");
						}else {
						
							SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")), devicename);
							Thread.sleep(3000);
							DriverTestcase.logger.log(LogStatus.PASS, devicename + " is entered under 'device name' field");
						}
				
		       	}

			
			
		}
	}
			
			
			if(interfaceSpeed.equals("10GigE")) {	
				
				if(technology.equals("Accedian"))	{
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
						
						//Non- termination point	
								if(nonterminatepoinr.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
									Thread.sleep(3000);
									DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point checkbox is selected");
								}else {
									System.out.println("Non termination point checkbox is not selected as expected");
									DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point chekbox is not selected");
								}
								
								
							//Protected	
								if(Protected.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
									Thread.sleep(3000);
									DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is selected");
								}else {
									System.out.println("Protected checkbox is not selecetd as expected");
									DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is not selected");
								}
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
						}
					
				}
			}	
	
	
	DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered for add site order");
	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

	}

	public void editSiteOrder(String application, String interfaceSpeed, String VPNTopology, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark, String IVreference,
			String siteOrderNumber, String Circuitreference, String GCRoloType, String VLAN, String VlanEtherType, String primaryVLAN, String primaryVlanEtherType, String offnetCreatedValue, String editOffnetvalue,
			String editEPNoffnetvalue, String mappingMode, String portBased, String vlanBased, String maapingModeAddedValue)
			throws InterruptedException, DocumentException, IOException {


		  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditsideOrderlink")));
		  Thread.sleep(6000);

		  
		  scrollToTop();
		  Thread.sleep(3000);
	//Point to Point	  
		  if(VPNTopology.equals("Point-to-Point")) {
			  
			  if(interfaceSpeed.equals("1GigE"))
			  {
				  editSiteOrder_P2P_1G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, technology, nontermination, Protected, devicename, remark);
			  }
			  
			  else  if(interfaceSpeed.equals("10GigE"))
			  {
				  editSiteOrder_P2P_10G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, technology, nontermination, Protected, remark);
			  }
			  
		  }
		  
		  
	//HUb & Spoke	  
		  if(VPNTopology.equals("Hub&Spoke")) {
			  
			  if(interfaceSpeed.equals("1GigE")) {
				  
				  if(IVreference.equals("Primary")) {
					  
					  editSiteOrder_HubAndSpoke_1G_IVRefPrimary(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, 
							  cloudserviceprovider, technology, nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference,editOffnetvalue);
					  
				  }
				  
				  else if(IVreference.equals("Access")) {
					  
					  editSiteOrder_HubAndSpoke_1G_IVRefAccess(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider,
							  technology, nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference, GCRoloType, VLAN, VlanEtherType, primaryVLAN,
							  primaryVlanEtherType, editOffnetvalue);
					  
				  }
			  }
			  
			  else  if(interfaceSpeed.equals("10GigE")) {
				  
				  editSiteOrder_HubAndSpoke_10G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, technology,
						  nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference,editOffnetvalue);
			  }
		  }
		  
		  
	//E-PN (Any to Any)	  
		  if(VPNTopology.equals("E-PN (Any-to-Any)")) {
			  
			  if(interfaceSpeed.equals("1GigE")) {
				  
				  if(IVreference.equals("Primary")) {
					  
					  editSiteOrder_EPN_1G_IVRefPrimary(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, 
							  cloudserviceprovider, technology, nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference,editEPNoffnetvalue);
					  
				  }
				  
				  else if(IVreference.equals("Access")) {
					  
					  editSiteOrder_EPN_1G_IVRefAccess(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider,
							  technology, nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference, GCRoloType, VLAN, VlanEtherType, primaryVLAN,
							  primaryVlanEtherType, editEPNoffnetvalue, mappingMode, portBased, vlanBased, maapingModeAddedValue);
					  
				  }
			  }
			  
			  else  if(interfaceSpeed.equals("10GigE")) {
				  
				  editSiteOrder_HubAndSpoke_10G(application, performReport, ProactiveMonitor, smartmonitor, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, technology,
						  nontermination, Protected, devicename, remark, siteOrderNumber, IVreference, Circuitreference,editEPNoffnetvalue);
			  }
			  
		  }
		  
	
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		

	}
	
	public void editSiteOrder_P2P_10G(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String remark) throws InterruptedException, DocumentException, IOException {
		
		
			//Performance Reporting
				editSiteOrder_Performancereporting(application, performReport);
				
				 
			//Pro active Monitoring 
				editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

				 
			//Smart Monitoring
				editSiteOrder_smartMonitoring(application, smartmonitor);

			//Vlan id
				editsiteOrder_vlanid(application, VLANid);
				 
			//Site alias
				editsiteorder_sitealias(application, siteallias);
				 
			//DCA Enabled Site 
				editesiteOrder_DcaEnabled(application, DCAenabledsite, cloudserviceprovider);
		
				
				scrolltoend();
				Thread.sleep(3000);
			//Remark
				editsiteOrder_remark(application, remark);
				
			//Technology
				editSiteOrder_technology(application,technology);
				 
				
				if(technology.equalsIgnoreCase("Accedian")) {
					 
					//Non-termination point
					 editsiteorder_NonterminationPoint(application, nontermination); 
					
					//Protected 
					 editsiteOrder_protected(application, Protected);
					 
				 }
		
	}
	
	public void editSiteOrder_P2P_1G(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark)
			throws InterruptedException, DocumentException, IOException {
		
		
		
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

	//Vlan id
		editsiteOrder_vlanid(application, VLANid);
		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		 
	//DCA Enabled Site 
		editesiteOrder_DcaEnabled(application, DCAenabledsite, cloudserviceprovider);
	
		scrolltoend();
		Thread.sleep(3000);
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			System.out.println(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		}
		
		if(technology.equalsIgnoreCase("Accedian-1G")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
		
	}
	
	
	
	public void editSiteOrder_HubAndSpoke_1G_IVRefAccess(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, 
			String devicename, String remark, String siteOrderNumber, String IVReference, String circuitReference, String GCRoloType, String VLAN, String VlanEtherType,
			String primaryVLAN, String primaryVlanEtherType, String offnet)
			throws InterruptedException, DocumentException, IOException {
	
		//Performance Reporting
				editSiteOrder_Performancereporting(application, performReport);
			
				
		
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
		
		//Site Order Number (Siebel Service ID)
				editSiteOrder_siteOrderNumber(application, siteOrderNumber);	
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		
	//Offnet
		editSiteorder_Offnet(application, offnet);
	
		
		scrolltoend();
		Thread.sleep(3000);
		
	//Remark
		editsiteOrder_remark(application, remark);
		
	// Circuit Reference
		editsiteorder_circuitReference(application, circuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			System.out.println(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//GCR OLO Type
			 editSiteOrder_GCRoloType(application, GCRoloType);
			 
			//VLAN
			 editsiteorder_VLAN(application, VLAN);
			 
		    //VLAN Ether Type	
			 editSiteOrder_VLANEtherType(application, VlanEtherType);
			 
		    //Primary VLAN
			 editsiteorder_primaryVLAN(application, primaryVLAN);
			 
		    //Primary VLAN Ether type	
			 editSiteOrder_primaryVLANEtherType(application, primaryVlanEtherType);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			System.out.println("No additional fields display for Alu Technology");
		}
		
		if((technology.equals("Accedian")) || (technology.equals("Accedian-1G"))) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
	}
	
	
	public void editSiteOrder_EPN_1G_IVRefAccess(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, 
			String devicename, String remark, String siteOrderNumber, String IVReference, String circuitReference, String GCRoloType, String VLAN, String VlanEtherType,
			String primaryVLAN, String primaryVlanEtherType, String offnet, String mappingMode, String portBased, String vlanBased, String maapingModeAddedValue)
			throws InterruptedException, DocumentException, IOException {
	
		
	//Site Order Number (Siebel Service ID)
		editSiteOrder_siteOrderNumber(application, siteOrderNumber);
		
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
	
		scrolltoend();
		Thread.sleep(3000);

	//Remark
		editsiteOrder_remark(application, remark);
		
	// Circuit Reference
		editsiteorder_circuitReference(application, circuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			System.out.println(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename);
			 
			//Mapping Mode 
			 editSiteOrder_mappingMode(application, mappingMode, portBased, vlanBased);
		
		//validation --> sometime mapping mode dropdown will not edited, port or Vlan fields will be edited	 
			 if((maapingModeAddedValue.equalsIgnoreCase("Port Based")) && (mappingMode.equalsIgnoreCase("null"))) {
				 
				 edittextFields_commonMethod(application, "Port Name" , "portname_textField", portBased);
					
			 }
			 else  if((maapingModeAddedValue.equalsIgnoreCase("Vlan Based")) && (mappingMode.equalsIgnoreCase("null"))) {
				 
				 edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased);
			 }
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename);	 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//GCR OLO Type
			 editSiteOrder_GCRoloType(application, GCRoloType);
			 
			//VLAN
			 editsiteorder_VLAN(application, VLAN);
			 
		    //VLAN Ether Type	
			 editSiteOrder_VLANEtherType(application, VlanEtherType);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			 
			//Mapping Mode
			 editSiteOrder_mappingMode(application, mappingMode, portBased, vlanBased);
			 
			//validation --> sometime mapping mode dropdown will not edited, port or Vlan fields will be edited	 
			 if((maapingModeAddedValue.equalsIgnoreCase("Port Based")) && (mappingMode.equalsIgnoreCase("null"))) {
				 
				 edittextFields_commonMethod(application, "Port Name" , "portname_textField", portBased);
					
			 }
			 else  if((maapingModeAddedValue.equalsIgnoreCase("Vlan Based")) && (mappingMode.equalsIgnoreCase("null"))) {
				 
				 edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased);
			 }
			 
		}
		
		if((technology.equalsIgnoreCase("Accedian")) || (technology.equalsIgnoreCase("Accedian-1G")) ) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
		
	}
	
	
	
	
	public void editSiteOrder_HubAndSpoke_1G_IVRefPrimary(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark, 
			String siteOrderNumber, String IVReference, String CircuitReference, String offnet)
			throws InterruptedException, DocumentException, IOException {
	
		
	//Site Order Number (Siebel Service ID)
		editSiteOrder_siteOrderNumber(application, siteOrderNumber);
		
	
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		
	//Offnet 
		editSiteorder_Offnet(application, offnet);

		scrolltoend();
		Thread.sleep(3000);
		
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Circuit Reference
		editsiteorder_circuitReference(application, CircuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			System.out.println(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		}
		
		if((technology.equals("Accedian-1G")) || (technology.equals("Accedian"))) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
	}
	
	
	public void editSiteOrder_EPN_1G_IVRefPrimary(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark, 
			String siteOrderNumber, String IVReference, String CircuitReference, String EPNoffnet)
			throws InterruptedException, DocumentException, IOException {
	
		
	//Site Order Number (Siebel Service ID)
		editSiteOrder_siteOrderNumber(application, siteOrderNumber);
		
	
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smart Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
	
		scrolltoend();
		Thread.sleep(3000);
		
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Circuit Reference
		editsiteorder_circuitReference(application, CircuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		if(technology.equalsIgnoreCase("Actelis")) {
			System.out.println(" NO additional fields display for technology Actelis");
		}
		
		
		if(technology.equalsIgnoreCase("Atrica")) {
			 
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		 }
		
		if(technology.equalsIgnoreCase("Overture")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename);
			 
		}
		
		
		if(technology.equalsIgnoreCase("Alu")) {
			
			 //Device Name
			 editSiteOrder_deviceName(application, devicename);
			
		}
		
		if((technology.equals("Accedian-1G")) || (technology.equals("Accedian"))) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
			//Protected 
			 editsiteOrder_protected(application, Protected);
			 
			//Device Name
			 editSiteOrder_deviceName(application, devicename); 
			
		}
		
		if(technology.equalsIgnoreCase("Cyan")) {
			
			//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
		}
		
		
	  //EPN Offnet
			editSiteorder_Offnet(application, EPNoffnet);
			
	 //EPN EOSDH
			
		
	}
	
	
	public void editSiteOrder_HubAndSpoke_10G(String application, String performReport, String ProactiveMonitor, String smartmonitor,
			String siteallias, String VLANid, String DCAenabledsite, String cloudserviceprovider, String technology, String nontermination, String Protected, String devicename, String remark, 
			String siteOrderNumber, String IVReference, String CircuitReference, String offnet)
			throws InterruptedException, DocumentException, IOException {
	
		
	//Site Order Number (Siebel Service ID)
		editSiteOrder_siteOrderNumber(application, siteOrderNumber);
		
	
	//IV Reference
		editSiteOrder_IVReference(application, IVReference);
	
	//Performance Reporting
		editSiteOrder_Performancereporting(application, performReport);
		
		 
	//Pro active Monitoring 
		editSiteOrder_proactiveMonitoring(application, ProactiveMonitor);

		 
	//Smarts Monitoring
		editSiteOrder_smartMonitoring(application, smartmonitor);

		 
	//Site alias
		editsiteorder_sitealias(application, siteallias);
		
	//offnet
		editSiteorder_Offnet(application, offnet);
		
		scrolltoend();
		Thread.sleep(3000);
		 
	//Remark
		editsiteOrder_remark(application, remark);
		
	//Circuit Reference
		editsiteorder_circuitReference(application, CircuitReference);
		
	//Technology
		editSiteOrder_technology(application,technology);
		 
		
	//Non-termination point
			 editsiteorder_NonterminationPoint(application, nontermination); 
			
	//Protected 
			 editsiteOrder_protected(application, Protected);
		
		
	}
	
	
	public void editSiteOrder_Performancereporting(String application, String performReport) throws InterruptedException, DocumentException {
		
		//Performance Reporting
		boolean perfrmReportAvailability = false;
	try {	
		perfrmReportAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")).isDisplayed();
	
		if(perfrmReportAvailability) {
		 if(performReport.equalsIgnoreCase("null")) {
			 DriverTestcase.logger.log(LogStatus.PASS, "Performance reporting dropdown is not edited");
			 System.out.println( "Performance reporting dropdown is not edited");
		 }else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting_xbutton")));
		Thread.sleep(3000);

		Clickon(getwebelement("//div[label[text()='Performance Reporting']]//div[text()='"+ performReport +"']"));
		
		Thread.sleep(3000);
		System.out.println("perform reporting selected");
		DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Performance reporting' dropdown is: "+performReport);
		System.out.println("Edited value for 'Performance reporting' dropdown is: "+performReport);
		
		 }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " Performance Reporting' dropdown is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Performance Reporting' dropdown is not available under 'Edit Site Order' page");
		System.out.println(" Performance Reporting' dropdown is not available under 'Edit Site Order' page");
	}catch(Exception err) {
		err.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to select value under 'Performance reporting' checkbox ");
		System.out.println(" Not able to select value under 'Performance reporting' checkbox ");
	}
	
	}
	
	
	public void editSiteOrder_GCRoloType(String application, String GCRoloType) throws InterruptedException, DocumentException {
		
		boolean GCRoloTypeAvailability = false;
	try {	
		GCRoloTypeAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
	
		if(GCRoloTypeAvailability) {
		 if(GCRoloType.equalsIgnoreCase("null")) {
			 DriverTestcase.logger.log(LogStatus.PASS, "'GCR OLO Type' dropdown is not edited");
		 }else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown_xbutton")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[text()='" + GCRoloType + "']"));
		Thread.sleep(3000);
		System.out.println("'GCR OLO Type' dropdown selected");
		
		String actualvalue=getwebelement("//div[label[text()='GCR OLO Type']]//span").getText();
		DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'GCR OLO Type' dropdown is: "+actualvalue);
		 }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Edit Site Order' page");
		}
		
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Edit Site Order' page");
		System.out.println( " 'GCR OLO Type' dropdown is not available under 'Edit Site Order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, GCRoloType +  " not available under 'GCR OLO type' dropdown");
		System.out.println(GCRoloType +  " not available under 'GCR OLO type' dropdown");
	}
	
	}
	
	
public void editSiteOrder_VLANEtherType(String application, String VlanEtherType) throws InterruptedException, DocumentException {
		
		boolean VLANEtherTypeAvailability = false;
	try {	
		VLANEtherTypeAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
	
		if(VLANEtherTypeAvailability) {
		 if(VlanEtherType.equalsIgnoreCase("null")) {
			 DriverTestcase.logger.log(LogStatus.PASS, "'VLAN Ether Type' dropdown value is not edited");
		 }else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAnEtheryTypeDropdown_xbutton")));
		Thread.sleep(3000);
		Clickon(getwebelement("//div[label[text()='VLAN Ether Type']]//div[text()='"+ VlanEtherType +"']"));
		Thread.sleep(3000);
		System.out.println("'VLAN Ether Type' dropdown selected");
		
		String actualValue=getwebelement("//div[label[text()='VLAN Ether Type']]//span").getText();
		DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'VLAN Ether Type' dropdown is: "+actualValue);
		
		 }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
		System.out.println(" 'VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, VlanEtherType + " is not selected under 'VLAN Ether type' dropdown");
		System.out.println(VlanEtherType + " is not edited under 'VLAN Ether type' dropdown");
	}
	
	}


public void editSiteOrder_primaryVLANEtherType(String application, String primaryVlanEtherType) throws InterruptedException, DocumentException {
	
	boolean primaryVLANEtherTypeAvailability = false;
try {	
	primaryVLANEtherTypeAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")).isDisplayed();


	if(primaryVLANEtherTypeAvailability) {
	 if(primaryVlanEtherType.equalsIgnoreCase("null")) {
		 DriverTestcase.logger.log(LogStatus.PASS, "'Primary VLAN Ether Type' dropdown value is not edited");
	 }else {
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown_xbutton")));
	Thread.sleep(3000);
	Clickon(getwebelement("//div[label[text()='Primary VLAN Ether Type']]//div[text()='"+ primaryVlanEtherType +"']"));
	Thread.sleep(3000);
	System.out.println("'Primary VLAN Ether Type' dropdown selected");
	
	String actualValue=getwebelement("//div[label[text()='Primary VLAN Ether Type']]//span").getText();
	DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Primary VLAN Ether Type' dropdown is: "+primaryVlanEtherType);
	
	 }
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
	}
}catch(NoSuchElementException e) {
	e.printStackTrace();
	DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
	System.out.println(" 'Primary VLAN Ether Type' dropdown is not available under 'Edit Site Order' page");
}catch(Exception ee) {
	ee.printStackTrace();
	DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit value under 'Primary Vlan Ether type' dropdown");
	System.out.println(" Not able to edit value under 'Primary Vlan Ether type' dropdown");
}
}


	

	public void editSiteOrder_technology(String application, String technology) throws InterruptedException {
		
		boolean techValue=false;
		
		try {
		techValue=getwebelement("//div[text()='"+technology +"']").isDisplayed();
		
		if(techValue) {
			
			DriverTestcase.logger.log(LogStatus.PASS, " Technology value is displaying as: "+technology +" as expected");
			
		}else {
			String actualValue=getwebelement("//div[div[label[contains(text(),'Technology')]]]/div[2]").getText();
			DriverTestcase.logger.log(LogStatus.FAIL, " Technology value is not displaying as expected"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for Technology is: "+technology);
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Technology value is not displaying as expected");
			System.out.println(" Technology value is not displaying as expected");
		}
	}
	
	
	public void editSiteOrder_siteOrderNumber(String application, String siteOrderNumber) throws InterruptedException {
		
		boolean siteOrderValue=false;
		try {
		siteOrderValue=getwebelement("//div[text()='"+siteOrderNumber +"']").isDisplayed();
		
		if(siteOrderValue) {
			
			DriverTestcase.logger.log(LogStatus.PASS, " Site Order Number (Siebel Service ID) value is displaying as: "+siteOrderNumber +" as expected");
			System.out.println( " Site Order Number (Siebel Service ID) value is displaying as: "+siteOrderNumber +" as expected");
		}else {
			String actualValue=getwebelement("//div[div[label[contains(text(),'Site Order Number (Siebel Service ID)')]]]/div[2]").getText();
			DriverTestcase.logger.log(LogStatus.FAIL, " Site Order Number (Siebel Service ID) value is not displaying"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for 'Site Order Number (Siebel Service ID)' is: "+siteOrderNumber);
			
			
			System.out.println(" Site Order Number (Siebel Service ID) value is not displaying"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for 'Site Order Number (Siebel Service ID)' is: "+siteOrderNumber);
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Site Order NUmber' value is not displaying as expected");
			System.out.println(" 'Site Order NUmber' value is not displaying as expected");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Site Order NUmber' value is not displaying as expected");
			System.out.println(" 'Site Order NUmber' value is not displaying as expected");
		}
		
	}
	
	
	public void editSiteOrder_IVReference(String application, String IVReference) throws InterruptedException {
		
		boolean IVrefValue=false;
		
		try {
			IVrefValue=getwebelement("//div[text()='"+IVReference +"']").isDisplayed();
		
		if(IVrefValue) {
			
			DriverTestcase.logger.log(LogStatus.PASS, " IV reference value is displaying as: "+IVReference +" as expected");
			System.out.println( " IV reference value is displaying as: "+IVReference +" as expected");
			
		}else {
			String actualValue=getwebelement("//div[div[label[contains(text(),'IV Reference')]]]/div[2]").getText();
			DriverTestcase.logger.log(LogStatus.FAIL, " IV Reference) value is not displaying as expected"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for 'IV Reference' is: "+IVReference);
			
			System.out.println(" IV Reference) value is not displaying as expected"
					+ "   Actual value displaying is: "+actualValue 
					+"  Expected value for 'IV Reference' is: "+IVReference);
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'IV Reference' value is not dispaying as expected");
			System.out.println(" 'IV Reference' value is not dispaying as expected");
		}
	}


	public void editSiteOrder_proactiveMonitoring(String application, String ProactiveMonitor) throws InterruptedException, DocumentException {
		
		boolean proactiveMonitorAvilability=false;
		try {
		proactiveMonitorAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")).isDisplayed();
		
		if(proactiveMonitorAvilability) {
			DriverTestcase.logger.log(LogStatus.PASS, " Proactive Monitoring' dropdown is displaying under 'Edit Site Order' page as expected");
			System.out.println(" Proactive Monitoring' dropdown is displaying under 'Edit Site Order' page as expected");
			
		if(ProactiveMonitor.equalsIgnoreCase("null")) {
			 DriverTestcase.logger.log(LogStatus.PASS, "Proactive monitoring' dropdown value is not edited");
			 System.out.println( "Proactive monitoring' dropdown value is not edited");
		 }else {
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsitorder_proactivemonitoring_xbutton")));
		Thread.sleep(3000);
		
		
		Clickon(getwebelement("//div[label[text()='Proactive Monitoring']]//div[text()='"+ ProactiveMonitor +"']"));
		
		Thread.sleep(3000);
		System.out.println("proa ctive monitorin selected");
		DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Pro active Monitoring' dropdown is: "+ProactiveMonitor);
		System.out.println("Edited value for 'Pro active Monitoring' dropdown is: "+ProactiveMonitor);
	 }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
			System.out.println(" Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
			System.out.println(" Pro active Monitoring' dropdown is not available under 'Edit Site Order' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'pro active monitoring' dropodwn");
			System.out.println(" Not able to enter value under 'pro active monitoring' dropodwn");
		}
	}
	
	
	public void editSiteOrder_smartMonitoring(String application, String smartmonitor) throws InterruptedException, DocumentException {
		
		boolean smartmonitorAvailability=false;
		try {
			smartmonitorAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring")).isDisplayed();
		
		
		if(smartmonitorAvailability) {
		
		 if(smartmonitor.equalsIgnoreCase("null")) {
			 DriverTestcase.logger.log(LogStatus.PASS, "Smarts Monitoring dropdown value is not edited");
			 System.out.println( "Smarts Monitoring dropdown value is not edited");
		 }else {
	    Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring_xbutton")));
	    Thread.sleep(3000);
	    Clickon(getwebelement("//div[label[text()='Smarts Monitoring']]//div[text()='"+ smartmonitor +"']"));
		Thread.sleep(3000);
	  
		System.out.println("smarts monitoring is selected");
		DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Smarts Monitoring' dropdown is: "+smartmonitor);
		System.out.println("Edited value for 'Smarts Monitoring' dropdown is: "+smartmonitor);
		 }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Smart Monitoring dropdown is not available under 'Edit Site Order' page");
			System.out.println("Smart Monitoring dropdown is not available under 'Edit Site Order' page");
		}
		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Smart Monitoring dropdown is not available under 'Edit Site Order' page");
			System.out.println("Smart Monitoring dropdown is not available under 'Edit Site Order' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to select value under 'Smart Montoring' checkbox");
			System.out.println(" NOt able to select value under 'Smart Montoring' checkbox");
		}
		 
	}
	
	
	public void editSiteOrder_deviceName(String application, String devicename) throws InterruptedException, DocumentException, IOException {
		
		boolean devicenameAvailability=false;
		try {
		
			devicenameAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).isDisplayed();
			
		if(devicenameAvailability) {
			
			if(devicename.equalsIgnoreCase("Null")) {
				
				DriverTestcase.logger.log(LogStatus.PASS, " NO changes made for 'Device Name' field");
			}else {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).clear();
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")), devicename);
				Thread.sleep(3000);
				
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, " Device Name value has been edited and the edited value is: "+ actualvalue);
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " Device name text field is not displaying under 'Edit Site Order' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Device name text field is not displaying under 'Edit Site Order' page");
			System.out.println(" Device name text field is not displaying under 'Edit Site Order' page");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'device name' field");
			System.out.println( " Not able to edit 'device name' field");
		}
	}
			
	
	public void editSiteOrder_mappingMode(String application, String mappingmode, String portBased, String vlanBased) throws InterruptedException, DocumentException {
		
		boolean mappingModeAvailability=false;
	try {	
		mappingModeAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")).isDisplayed();
		if(mappingModeAvailability) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Mapping Mode' dropdown is displaying in 'Edit Site Order' page as expected");
			System.out.println(" 'Mapping Mode' dropdown is displaying in 'Edit Site Order' page as expected");
			
			if(mappingmode.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'mapping Mode' dropdown is not edited");
				System.out.println(" 'mapping Mode' dropdown is not edited");
				
				
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown_xbutton")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='"+ mappingmode +"']"));
				Thread.sleep(3000);
				
				String actualValue=getwebelement("//div[label[text()='Mapping Mode']]//span").getText();
				DriverTestcase.logger.log(LogStatus.PASS, " 'Mapping mode' dropdown is edited as: "+ actualValue);
				
				if(actualValue.equalsIgnoreCase("Port Based")) {
					
					edittextFields_commonMethod(application, "Port Name" , "portname_textField", portBased);
					
				}else if(actualValue.equalsIgnoreCase("Vlan Based")) {
					
					edittextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased);
				}
				
			}
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Mapping mode' dropdown is not displaying under 'Edit Site order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Mapping mode' dropdown is not displaying under 'Edit Site order' page");
		System.out.println(" 'Mapping mode' dropdown is not displaying under 'Edit Site order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit value under 'Mapping Mode' dropdown");
		System.out.println(" Not able to edit value under 'Mapping Mode' dropdown");
	}
	}
	
	
	
	public void editsiteOrder_vlanid(String application, String VLANid) throws InterruptedException, DocumentException, IOException {
		
		boolean vlanAvailability = false;
		
	try {	
		vlanAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).isDisplayed();
	
		if(vlanAvailability) {
		 if(VLANid.equalsIgnoreCase("null")) {
			 DriverTestcase.logger.log(LogStatus.PASS, "Vlanid field value is not edited");
			 System.out.println("Vlanid field value is not edited");
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).clear();
			 Thread.sleep(3000);
			 
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")), VLANid);
			 Thread.sleep(3000);
			 
			String VLANidValue= getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).getAttribute("value");
			 DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Vlan id' field is: "+ VLANidValue);
			 System.out.println( "Edited value for 'Vlan id' field is: "+ VLANidValue);
		 }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "VLAN Id field is not available under 'Edit Site Order' page");
			System.out.println("VLAN Id field is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "VLAN Id field is not available under 'Edit Site Order' page");
		System.out.println("VLAN Id field is not available under 'Edit Site Order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'VLAn Id' text field");
		System.out.println(" not able to edit 'VLAN ID' text field");
	}
	}
	
	
public void editsiteOrder_remark(String application, String remark) throws InterruptedException, DocumentException, IOException {
		
		boolean remarkAvailability = false;
		scrolltoend();
		Thread.sleep(3000);
	try {	
		remarkAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).isDisplayed();
	
		if(remarkAvailability) {
		 if(remark.equalsIgnoreCase("null")) {
			 DriverTestcase.logger.log(LogStatus.PASS, "Remark text field value is not edited");
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).clear();
			 Thread.sleep(3000);
			 
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")), remark);
			 Thread.sleep(3000);
			 
			String remarkValue= getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).getAttribute("value");
			 DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Remark' field is: "+ remarkValue);
		 }
		 
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Remark text field is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "Remark text field is not available under 'Edit Site Order' page");
		System.out.println("Remark text field is not available under 'Edit Site Order' page");
	}catch(Exception err) {
		err.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'remark' field");
		System.out.println(" Not able to enter value under 'remark' field");
	}
	
	}
	
	
	public void editsiteorder_sitealias(String application, String siteallias) throws InterruptedException, DocumentException, IOException {
		boolean siteAliasAvilability= false;
	try {	
		siteAliasAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).isDisplayed();
	
		if(siteAliasAvilability) {
		 if(siteallias.equalsIgnoreCase("null")) {
			 DriverTestcase.logger.log(LogStatus.PASS, "Site Alias field value is not edited");
			 System.out.println("Site Alias field value is not edited");
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).clear();
			 Thread.sleep(3000);
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")), siteallias);
			 Thread.sleep(3000);
			 String siteAliasvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).getAttribute("value");
			 DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Site Alias' field is: "+siteAliasvalue);
			 System.out.println("Edited value for 'Site Alias' field is: "+siteAliasvalue);
		 }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " Site Alias field is not available under 'Edit Site Order' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Site Alias field is not available under 'Edit Site Order' page");
			System.out.println(" Site Alias field is not available under 'Edit Site Order' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'Site Alias' field" );
			System.out.println(" Not able to enter value under 'Site Alias' field");
		}
		
	
	}
	
	
	 public void editSiteorder_Offnet(String application, String offnet) throws InterruptedException, DocumentException {
		 
		 boolean offnetAvailability=false;
		 
		 try {
		 offnetAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isDisplayed();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		if(offnetAvailability) {
			
			DriverTestcase.logger.log(LogStatus.PASS, " 'Offnet' checkbox is displaying under 'Edit Site ordeer' page as exepected");
		 if(!offnet.equalsIgnoreCase("null")) {
				
				boolean offnetselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();
				Thread.sleep(2000);
				
				if (offnet.equalsIgnoreCase("yes")) {

					if(offnetselection) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Offnet checkbox is not edited and it is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
						Log.info("Offnet check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"Offnet is edited and gets selected as expected");
					}
					
					 
				}

				else if (offnet.equalsIgnoreCase("no")) {
					
					if(offnetselection) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
						Log.info("Offnet check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"offnet is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "Offnet is not edited and it remains unselected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for Offnet chekbox");
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Offnet' checkbox is not available under 'Edit Site Order' page");
		}
	 }
	
	
	public void editsiteorder_circuitReference(String application, String circuitRef) throws InterruptedException, DocumentException, IOException {
		
		boolean circuitRefAvilability= false;
	try {	
		circuitRefAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).isDisplayed();

		if(circuitRefAvilability) {
		 if(circuitRef.equalsIgnoreCase("null")) {
			 
			 DriverTestcase.logger.log(LogStatus.PASS, "Circuit Reference field value is not edited");
			 
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).clear();
			 Thread.sleep(3000);
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")), circuitRef);
			 Thread.sleep(3000);
			 String circuitRefvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).getAttribute("value");
			 DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Circuit Reference' field is: "+circuitRefvalue);
		 }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " Circuit Reference field is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Circuit Reference field is not available under 'Edit Site Order' page");
		System.out.println(" Circuit Reference field is not available under 'Edit Site Order' page");
	}catch(Exception err) {
		err.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'Circuit reference' field");
		System.out.println(" Not able to enter value under 'Circuit reference' field");
	}
	}
	
	
public void editsiteorder_VLAN(String application, String VLAN) throws InterruptedException, DocumentException, IOException {
		
		boolean VLANAvilability= false;
	try {	
		VLANAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
	
		if(VLANAvilability) {
		 if(VLAN.equalsIgnoreCase("null")) {
			 
			 DriverTestcase.logger.log(LogStatus.PASS, "VLAN text field value is not edited");
			 
		 }else {
			 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).clear();
			 Thread.sleep(3000);
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")), VLAN);
			 Thread.sleep(3000);
			 
			 String VLANactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).getAttribute("value");
			 DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'VLAN' text field is: "+VLANactualvalue);
		 }
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " VLAN text field is not available under 'Edit Site Order' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " VLAN text field is not available under 'Edit Site Order' page");
		System.out.println(" VLAN text field is not available under 'Edit Site Order' page");
	}catch(Exception ee) {
		ee.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit value under 'VLAN' text field");
		System.out.println(" Not able to edit value under 'VLAN' text field");
	}
	
	}



public void editsiteorder_primaryVLAN(String application, String primaryVLAN) throws InterruptedException, DocumentException, IOException {
	
	boolean primaryVLANAvilability= false;
try {	
	primaryVLANAvilability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).isDisplayed();

	if(primaryVLANAvilability) {
	 if(primaryVLAN.equalsIgnoreCase("null")) {
		 
		 DriverTestcase.logger.log(LogStatus.PASS, " Primary VLAN text field value is not edited");
		 
	 }else {
		 getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).clear();
		 Thread.sleep(3000);
		 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")), primaryVLAN);
		 Thread.sleep(3000);
		 String primaryVLANactualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).getAttribute("value");
		 DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Primary VLAN' text field is: "+primaryVLANactualvalue);
	 }
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " Primary VLAN text field is not available under 'Edit Site Order' page");
	}
}catch(NoSuchElementException e) {
	e.printStackTrace();
	DriverTestcase.logger.log(LogStatus.FAIL, " Primary VLAN text field is not available under 'Edit Site Order' page");
	System.out.println(" Primary VLAN text field is not available under 'Edit Site Order' page");
}catch(Exception ee) {
	ee.printStackTrace();
	DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'Primary VLAn Type' text field");
	System.out.println( " Not able to edit 'Primary VLAn Type' text field");
}

}
                  
	
	public void editesiteOrder_DcaEnabled(String application, String DCAenabledsite, String cloudserviceprovider) throws InterruptedException, DocumentException {
		
		boolean DCAavailability=false;
		
		try {
		DCAavailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isDisplayed();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(DCAavailability) {
		
		 if(!DCAenabledsite.equalsIgnoreCase("null")) {
				
				boolean dcaenabled=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isSelected();
				Thread.sleep(2000);
				
				if (DCAenabledsite.equalsIgnoreCase("yes")) {

					if(dcaenabled) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "DCA Enabled Site is already Selected while creating");

						if(cloudserviceprovider.equalsIgnoreCase("null")) {
							
							DriverTestcase.logger.log(LogStatus.PASS,"No changes made to Cloud Service Provider");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
							Thread.sleep(3000);
							Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
							Thread.sleep(3000);
							DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Cloud Service provider' dropdown is: "+cloudserviceprovider);
						}
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
						Log.info("DCA Enabled Site check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"DCA Enabled Site checkbox is selected");
						
						
						if(cloudserviceprovider.equalsIgnoreCase("null")) {
							
							DriverTestcase.logger.log(LogStatus.PASS,"No changes made to Cloud Service Provider");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
							Thread.sleep(3000);
							Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
							Thread.sleep(3000);
							DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Cloud Service provider' dropdown is: "+cloudserviceprovider);
						}
					}
					
					
				}

				else if (DCAenabledsite.equalsIgnoreCase("no")) {
					
					if(dcaenabled) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
						Log.info("DCA Enabled Site check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"DCA Enabled Site is unselected as Expected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "DCA Enabled Site was not selected during service creation and it remains unselected as expected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for DCAenabled site chekbox as expected");
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "DCA Enabled Site checkbox is not displaying under 'Edit Site Order' page");
		}
	}
	
	
	public void editsiteorder_NonterminationPoint(String application, String nontermination) throws InterruptedException, DocumentException {
		
		boolean NonTerminationPointAvailability=false;
		
		try {
			NonTerminationPointAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isDisplayed();
		
		if(NonTerminationPointAvailability) {
		
      if(!nontermination.equalsIgnoreCase("null")) {
			
    	  boolean nonterminatepoint=false;
    	 try { 
    	  nonterminatepoint=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
    	 }catch(Exception e) {
    		 e.printStackTrace();
    	 }
			Thread.sleep(2000);
			
			if (nontermination.equalsIgnoreCase("yes")) {

				if(nonterminatepoint) {
					
					DriverTestcase.logger.log(LogStatus.PASS, " 'Non-Termination point' checkbox is already Selected while creating");
					
				}else {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Log.info("'Non-Termination point' check box is selected");
					DriverTestcase.logger.log(LogStatus.PASS,"'Non-Termination point' is edited and gets selected");
				}
				
				
			}

			else if (nontermination.equalsIgnoreCase("no")) {
				
				if(nonterminatepoint) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
					Log.info("'Non-Termination point' check box is unselected");
					DriverTestcase.logger.log(LogStatus.PASS,"'Non-Termination point' is edited and gets unselected");
					
				}else {
					DriverTestcase.logger.log(LogStatus.PASS, "'Non-Termination point' was not selected during service creation and it remains unselected as expected");
				}
				
			}
		}else {
			DriverTestcase.logger.log(LogStatus.PASS,"No changes made for 'Non-Termination point' chekbox as expected");
		}
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " Non-Termination Point checkbox is not available under 'Edit Site order' page");
	}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Non-Termination Point checkbox is not available under 'Edit Site order' page");
			System.out.println(" Non-Termination Point checkbox is not available under 'Edit Site order' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on 'Non-termination point' checkbox ");
			System.out.println(" Not able to click on 'Non-termination point' checkbox ");
		}
	}
	
	
	
	public void editsiteOrder_protected(String application, String Protected) throws InterruptedException, DocumentException {
		
		boolean prtctedAvailability= false;
		
		try {
			prtctedAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isDisplayed();
		
		if(prtctedAvailability) {
          if(!Protected.equalsIgnoreCase("null")) {
				
        	  boolean prtcted=false;
        	 try { 
        	  prtcted=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
        	 }catch(Exception e) {
        		 e.printStackTrace();
        	 }
				Thread.sleep(2000);
				
				if (Protected.equalsIgnoreCase("yes")) {
					if(prtcted) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'Protected' checkbox is already Selected while creating");
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
						Log.info("'Non-Termination point' check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"'Protected' is edited and gets selected");
					}
				}

				else if (Protected.equalsIgnoreCase("no")) {
					if(prtcted) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
						Log.info("'Non-Termination point' check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"'Protected' is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "'Protected' was not selected during service creation and it remains unselected as expected");
					}
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for 'Protected' chekbox as expected");
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " Protected checkbox is not displaying under 'Edit Site order' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Protected checkbox is not displaying under 'Edit Site order' page");
			System.out.println(" Protected checkbox is not displaying under 'Edit Site order' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to select 'protected' checkbox");
			System.out.println(" Not able to select 'protected' checkbox");
		
		}
		
	}
	
	
	public void editsiteOrder_cloudserviceprovider(String application, String cloudserviceprovider) throws InterruptedException, DocumentException {
		if(cloudserviceprovider.equalsIgnoreCase("null")) {
			
			DriverTestcase.logger.log(LogStatus.PASS,"No changes made to Cloud Service Provider");
			
		}else {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
			Thread.sleep(4000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
			Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
			DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Cloud Service provider' dropdown is: "+cloudserviceprovider);
		}
	}

	public void Enteraddsiteorder(String application) throws InterruptedException, DocumentException {

		Thread.sleep(5000);
		
		scrolltoend();
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actiondropdown_siteorder")));
		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorderlink")));
	}

	public void clickonEditwithoutselectingrow(String application)
			throws InterruptedException, DocumentException, IOException {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actiondropdown_siteorder")));
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditsideOrderlink")));
		Thread.sleep(2000);

		String popupmessage = Gettext(getwebelement("//div[text()='Please select a row to edit']"));
		System.out.println("Edit popup message before selecting row: " + popupmessage);
		Log.info("Edit popup message before selecting row: \"+popupmessage");

		Clickon(getwebelement("//div[@class='modal-header']//div[contains(text(),'�')]"));

	}

	public void clickondeletewithoutselectingrow(String application)
			throws InterruptedException, DocumentException, IOException {

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Backbutton")));
		Thread.sleep(5000);

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

		Clickon(getwebelement("//div[@class='modal-header']//div[contains(text(),'�')]"));

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

		Clickon(getwebelement("//div[@class='modal-header']//div[contains(text(),'�')]"));

	}

	public void viewsiteorderlink(String application) throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewsiteorderlink")));
		Thread.sleep(2000);
		Log.info("Entered View site order page");
		
		DriverTestcase.logger.log(LogStatus.PASS, "Entered view site order page");

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
		sa.assertEquals(fetchedvalue_country, country, "Country field is not displaying same Entered value while creating");

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
		sa.assertEquals(fetchedvalue_vlanid, VLANid, "VLAN id field is not displaying same Entered value while creating");

		boolean fetchedvalue_DCAenabledsite = getwebelement(
				xml.getlocator("//locators/" + application + "/Editsiteorder_DCAenabledsite")).isSelected();
		sa.assertFalse(fetchedvalue_DCAenabledsite, "DCA enabled is not selected as expected");
		System.out.println("DCA enables is: " + fetchedvalue_DCAenabledsite);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
		
		sa.assertAll();
    }catch(AssertionError e) {
    	e.printStackTrace();
    }

	}

	public void verifyAddsiteorderFields(String application, String interfaceSpeed, String VPNtopology, String circuitType,
			String offnetSelection,String EPNoffnet,String EPNEOSDHselection) throws InterruptedException, DocumentException, IOException {
		
		
		
		if( (VPNtopology.equals("Point-to-Point")) & (circuitType.equals("Default"))) {
			
			verifySiteOrderForPoint_to_point(application, interfaceSpeed);
			
		}
		else if(VPNtopology.equals("Hub&Spoke")) {
			
			if(offnetSelection.equalsIgnoreCase("No")) {
			
			verifySiteOrderForHubAndSpoke(application, interfaceSpeed);
		}
			
			else if(offnetSelection.equalsIgnoreCase("Yes")) {
				
				verifySiteOrderForHubAndSpoke_offnetSelected(application, interfaceSpeed);
				
			}
			
			
		}else if(VPNtopology.equals("E-PN (Any-to-Any)")) {
			
			if(EPNEOSDHselection.equalsIgnoreCase("No")) {
			
			verifySiteOrderForE_PN(application, interfaceSpeed);
			
			}
			else if(EPNEOSDHselection.equalsIgnoreCase("yes")) {
				
				verifySiteOrderForEPN_EOSDHselected(application, interfaceSpeed);
				
				}
		}
	}
	
	
	public void addsiteorder(String application, String interfaceSpeed, String VPNtopology, String circuitType,
			String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,	String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH,
  			String mappingmode, String portBased, String vlanBased) throws InterruptedException, DocumentException, IOException {
		
		
		
		System.out.println("Entered to add Site Order vales");
		scrollToTop();
		Thread.sleep(5000);
		 
		if( (VPNtopology.equals("Point-to-Point")) & (circuitType.equals("Default"))) {
			
			addSiteOrderValues_point2point(application, interfaceSpeed, country,  city,  CSR_Name,  site,
					 performReport,  ProactiveMonitor,  smartmonitor,  technology,  siteallias,
					 VLANid,  DCAenabledsite,  cloudserviceprovider,  sitevalue,  remark,
					 xngcityname,  xngcitycode, devicename,  nonterminatepoinr,  Protected,  newcityselection,  existingcityselection,
					 existingsiteselection,  newsiteselection);
			
		}
		else if(VPNtopology.equals("Hub&Spoke")) {
			
			if(offnetSelection.equalsIgnoreCase("No")) {
			
			addSiteOrderValues_HubAndSPoke(application, interfaceSpeed, country, city, CSR_Name, newsiteselection, 
					performReport, ProactiveMonitor, smartmonitor, technology, siteallias, VLANid, DCAenabledsite,cloudserviceprovider, sitevalue, 
					remark, xngcityname, xngcitycode, devicename, nonterminatepoinr, Protected, newcityselection,existingcityselection, existingsiteselection, 
					newsiteselection, siteOrderNumber, circuitref, offnetSelection, IVReference,
		  			GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether);
			
			}
			
			if(offnetSelection.equalsIgnoreCase("Yes")) {
				
				addSiteOrderValues_HubAndSPoke_OffnetSelected(application, interfaceSpeed, country, city, CSR_Name, newsiteselection, 
						performReport, ProactiveMonitor, smartmonitor, technology, siteallias, VLANid, DCAenabledsite,cloudserviceprovider, sitevalue, 
						remark, xngcityname, xngcitycode, devicename, nonterminatepoinr, Protected, newcityselection,existingcityselection, existingsiteselection, 
						newsiteselection, siteOrderNumber, circuitref, offnetSelection, IVReference,
			  			GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether);
				
				}
			
			
			
			
		}else if(VPNtopology.equals("E-PN (Any-to-Any)")) {
			
			addSiteOrderValues_EPN(application, interfaceSpeed, country, city, CSR_Name, newsiteselection, 
					performReport, ProactiveMonitor, smartmonitor, technology, siteallias, VLANid, DCAenabledsite,cloudserviceprovider, sitevalue, 
					remark, xngcityname, xngcitycode, devicename, nonterminatepoinr, Protected, newcityselection,existingcityselection, existingsiteselection, 
					newsiteselection, siteOrderNumber, circuitref, offnetSelection, IVReference,
		  			GCRolo, Vlan, Vlanether, EPNoffnet, EPNEOSDH, mappingmode, portBased, vlanBased);
			
		}
		
		
		
	}
	
	public void verifySiteOrderForPoint_to_point(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
		

		DriverTestcase.logger.log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> Point to Point");
		
		try {
				
				Clickon(getwebelement("//span[contains(text(),'OK')]"));
				Thread.sleep(5000);
					
				//Country Error message	
					boolean countryErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).isDisplayed();
					sa.assertTrue(countryErr, "Country warning message is not displayed ");
					if(countryErr) {
						DriverTestcase.logger.log(LogStatus.PASS, " Warning message for 'Country' dropdown is displying under 'Add Site order' page as expected ");
						String countryErrMsg = getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).getText();
						System.out.println(
								"Country  message displayed as : " + countryErrMsg);
						DriverTestcase.logger.log(LogStatus.PASS,
								"Step :  validation message for Country field displayed as : " + countryErrMsg);
						Log.info("Country warning message displayed as : " + countryErrMsg);
						
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " Warning message for 'Country' dropdown is not displaying under 'Add Site order' page ");
					}
					
					
					
				//City Error message	
					boolean cityErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).isDisplayed();
					sa.assertTrue(cityErr, "City warning message is not displayed ");
					if(cityErr) {
						DriverTestcase.logger.log(LogStatus.PASS, " Warning message for 'City' dropdown is displying under 'Add Site order' page as expected ");
						
						String cityErrMsg = getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).getText();
						System.out.println(
								"City  message displayed as : " + cityErrMsg);
						DriverTestcase.logger.log(LogStatus.PASS,
								"Step :  validation message for City field displayed as : " + cityErrMsg);
						Log.info("City warning message displayed as : " + cityErrMsg);
					
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " Warning message for 'City' dropdown is not displaying under 'Add Site order' page");
					}
					
					
					
				//CSR name Error message	
					boolean csrErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).isDisplayed();
					sa.assertTrue(csrErr, "CSR Name warning message is not displayed ");
					if(csrErr) {
						DriverTestcase.logger.log(LogStatus.PASS, " Warning message for 'CSR Name' text field is displying under 'Add Site order' page as expected ");
					
						String csrErrMsg = getwebelement(
								xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).getText();
						System.out.println(
								"CSR Name  message displayed as : " + csrErrMsg);
						DriverTestcase.logger.log(LogStatus.PASS,
								"Step :  validation message for CSR name field displayed as : " + csrErrMsg);
						Log.info("CSR Name warning message displayed as : " + csrErrMsg);	
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " Warning message for 'City' dropdown is not displaying under 'Add Site order' page ");
					}
					
					
					
				//Technology Error message	
					boolean technologyErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).isDisplayed();
					sa.assertTrue(technologyErr, "Technology warning is not displayed ");
					if(technologyErr) {
						DriverTestcase.logger.log(LogStatus.PASS, " Warning message for 'Technology' dropdown is displying under 'Add Site order' page as expected ");
					
						String technologyErrMsg = getwebelement(
								xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).getText();
						System.out.println(
								"Technology  message displayed as : " + technologyErrMsg);
						DriverTestcase.logger.log(LogStatus.PASS,
								"Step :  validation message displayed as : " + technologyErrMsg);
						Log.info("Technology validation message displayed as : " + technologyErrMsg);	
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " Warning message for 'Technology' dropdown is not displying under 'Add Site order' page ");
					}
					
				
	scrollToTop();
	Thread.sleep(3000);
					
		//Validate Country dropdown
				System.out.println("validate Country dropdown");
				validateCountry_AddSiteorder(application);
				
					
		//Validate City Fields
				System.out.println("Validate city fields");
				validateCity_AddSiteOrder(application);
				
		//Validate Site/CSR field
				System.out.println("validate Site Fields");
				validateSite_AddSiteOrder(application);
	
				scrolltoend();
				Thread.sleep(3000);
				
		// Validate performance reporting dropdown
				System.out.println("validate performance reporting checkbox");
				validatePerformancereporting_AddSiteOrder(application);
				
				
		//validate proactive Monitoring dropdown
				System.out.println("validate proactive monitoring checkbox");
				validateProactiveMonitoring_AddSiteOrder(application);
				
				
		//Validate Smarts monitoring dropdown
				System.out.println("validate Smarts monitoring checkbox");
				validateSmartsMOnitoring_AddSiteOrder(application);
				
				
		//Validate Site Alias field
				System.out.println("validate Site Alias fields");
				validateSiteAlias_AddSiteOrder(application);
				
		
		//Validate VLAN Id field
				System.out.println("validate VLAn Id fields");
				validateVlanID_AddSiteOrder(application);
				
				
		//Validate DCA Enabled Site and Cloud Service Provider dropdown
				System.out.println("validate DCA enabled site checkbox");
				valiadateDCAEnabledsite_AddSieOrder(application);
				
		
		//Verify Remark field
				System.out.println("validate Remark fields");
				validateRemark_AddSiteOrder(application);
				
				
				
		
	if(interfaceSpeed.equals("1GigE"))	{
		
		technologyDropdownFor1GigE(application);
	}	
			
	else if(interfaceSpeed.equals("10GigE"))	{
		
		technologyDropdownFor10GigE(application);
	}
			
			
	//Validate OK button
		OKbutton_AddSiteOrder(application);
				
	//Validate Cancel button
		cancelbutton_AddSiteOrder(application);

							Thread.sleep(3000);
							scrolltoend();
							Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cancel")));
		Thread.sleep(3000);
		
		sa.assertAll();
		
		}catch(AssertionError e) {
		   e.printStackTrace();
			}

	}

	
public void verifySiteOrderForHubAndSpoke(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
		
	DriverTestcase.logger.log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> Hub&Spoke");

		try {
				
				String[] IVrefrence = { "Primary", "Access" };

				Clickon(getwebelement("//span[contains(text(),'OK')]"));
				Thread.sleep(5000);
					
				//Country Error message	
					boolean countryErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).isDisplayed();
					sa.assertTrue(countryErr, "Country warning message is not displayed ");
					if(countryErr) {
					String countryErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).getText();
					System.out.println(
							"Country  message displayed as : " + countryErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for Country field displayed as : " + countryErrMsg);
					Log.info("Country warning message displayed as : " + countryErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Country field is not displaying");
					}
					
				//City Error message	
					boolean cityErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).isDisplayed();
					sa.assertTrue(cityErr, "City warning message is not displayed ");
					if(cityErr) {
					String cityErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).getText();
					System.out.println(
							"City  message displayed as : " + cityErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for City field displayed as : " + cityErrMsg);
					Log.info("City warning message displayed as : " + cityErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for City field is not displaying");
					}
					
					
				//CSR name Error message	
					boolean csrErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).isDisplayed();
					sa.assertTrue(csrErr, "CSR Name warning message is not displayed ");
					if(csrErr) {
					String csrErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).getText();
					System.out.println(
							"CSR Name  message displayed as : " + csrErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for CSR name field displayed as : " + csrErrMsg);
					Log.info("CSR Name warning message displayed as : " + csrErrMsg);	
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for CSR Name field is not displaying");
					}
					
					
				//Technology Error message	
					boolean technologyErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).isDisplayed();
					sa.assertTrue(technologyErr, "Technology warning is not displayed ");
					if(technologyErr) {
					String technologyErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).getText();
					System.out.println(
							"Technology  message displayed as : " + technologyErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message displayed as : " + technologyErrMsg);
					Log.info("Technology validation message displayed as : " + technologyErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Technology field is not displaying");
					}
					
			scrollToTop();
			Thread.sleep(3000);
			
				//Site Order Number Error Message	
					boolean SiteOrderNumberErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_sitOrderNumberErrmsg")).isDisplayed();
					sa.assertTrue(SiteOrderNumberErr, "Site order Number warning message is not displayed ");
					if(SiteOrderNumberErr) {
					String siteOrderErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_sitOrderNumberErrmsg")).getText();
					System.out.println(
							"Site order Number warning  message displayed as : " + siteOrderErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for Site order Number field displayed as : " + siteOrderErrMsg);
					Log.info("Site order Number warning message displayed as : " + siteOrderErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Site Order Number field is not displaying");
					}
					
					
				//Circuit Reference Error Message
					boolean circuitRefErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceErrmsg")).isDisplayed();
					sa.assertTrue(circuitRefErr, "Circuit Reference warning message is not displayed ");
					if(circuitRefErr) {
					String circuitReferenceErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceErrmsg")).getText();
					System.out.println(
							"Circuit Reference warning  message displayed as : " + circuitReferenceErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for Circuit Reference field displayed as : " + circuitReferenceErrMsg);
					Log.info("Circuit Reference warning message displayed as : " + circuitReferenceErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Circuit Reference field is not displaying");
					}
				
					
				//IV Reference Error Messages
					boolean IVrefErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_IVReferenceErrmsg")).isDisplayed();
					sa.assertTrue(IVrefErr, "IV Reference warning message is not displayed ");
					if(IVrefErr) {
					String IVReferenceErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_IVReferenceErrmsg")).getText();
					System.out.println(
							"IV Reference warning  message displayed as : " + IVReferenceErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for IV Reference field displayed as : " + IVReferenceErrMsg);
					Log.info("IV Reference warning message displayed as : " + IVReferenceErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for IV Reference field is not displaying");
					}
					
			
					
					//Validate site order Number Field
					 		validatesiteOrderNumber_AddSiteOrder(application);

					
					//Validate Country dropdown
							validateCountry_AddSiteorder(application);
							
								
					//Validate City Fields
							validateCity_AddSiteOrder(application);
							
					//Validate Site/CSR field
							validateSite_AddSiteOrder(application);
							
				scrolltoend();
				Thread.sleep(3000);
				
					//Validate performance reporting dropdown
							validatePerformancereporting_AddSiteOrder(application);
							
							
					//validate proactive Monitoring dropdown
							validateProactiveMonitoring_AddSiteOrder(application);
							
							
					//Validate Smarts monitoring dropdown
							validateSmartsMOnitoring_AddSiteOrder(application);
							
						
					//Validate IV Reference dropdown
							validateIVReference_AddSiteorder(application);

					//Validate Circuit reference Field
							validateCircuitreference_AddSiteOrder(application);

					//Spoke ID
							validatespokeId_AddSiteOrder(application);

							
					//Validate Site Alias field
							validateSiteAlias_AddSiteOrder(application);

									
					//Verify Remark field
							validateRemark_AddSiteOrder(application);
							
					//Validate Offnet checkbox
							validateoffnet_AddSiteOrder(application);		

	
							scrolltoend();
							Thread.sleep(3000);
		
	if( (interfaceSpeed.equals("1GigE")))	{
		
		for(int i=0; i<IVrefrence.length; i++) {
			if(IVrefrence[i].equals("Primary")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
				Thread.sleep(3000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
				Thread.sleep(3000);
				
				technologyDropdownFor1GigE_HubAndSpoke_Primary(application);
				
			}
			
			else if(IVrefrence[i].equals("Access")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
				Thread.sleep(3000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
				Thread.sleep(3000);
				
				technologyDropdownFor1GigE_HubAndSpoke_Access(application);
				
			}
		}
	}	
			
	else if(interfaceSpeed.equals("10GigE"))	{
		
		for(int i=0; i<IVrefrence.length; i++) {
			if(IVrefrence[i].equals("Primary")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
				Thread.sleep(3000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
				Thread.sleep(3000);
				
				technologyDropdownFor10GigE_HubAndSpoke_primary(application);
				
			}
			
			else if(IVrefrence[i].equals("Access")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
				Thread.sleep(3000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
				Thread.sleep(3000);
				
				technologyDropdownFor10GigE_HubAndSpoke_Access(application);
				
			}
		}
	}
			

	//Validate OK button
				OKbutton_AddSiteOrder(application);

		//validate cancel button
				cancelbutton_AddSiteOrder(application);

		Thread.sleep(3000);

		scrolltoend();
		Thread.sleep(3000);
Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cancel")));
Thread.sleep(3000);

sa.assertAll();

		}catch(AssertionError e) {
		   e.printStackTrace();
			}
	
	}


public void verifySiteOrderForHubAndSpoke_offnetSelected(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
	
	DriverTestcase.logger.log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> Hub&Spoke");

		try {
				
				String[] IVrefrence = { "Primary", "Access" };

				Clickon(getwebelement("//span[contains(text(),'OK')]"));
				Thread.sleep(5000);
					
				//Country Error message	
					boolean countryErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).isDisplayed();
					sa.assertTrue(countryErr, "Country warning message is not displayed ");
					if(countryErr) {
					String countryErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).getText();
					System.out.println(
							"Country  message displayed as : " + countryErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for Country field displayed as : " + countryErrMsg);
					Log.info("Country warning message displayed as : " + countryErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Country field is not displaying");
					}
					
				//City Error message	
					boolean cityErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).isDisplayed();
					sa.assertTrue(cityErr, "City warning message is not displayed ");
					if(cityErr) {
					String cityErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).getText();
					System.out.println(
							"City  message displayed as : " + cityErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for City field displayed as : " + cityErrMsg);
					Log.info("City warning message displayed as : " + cityErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for City field is not displaying");
					}
					
					
				//CSR name Error message	
					boolean csrErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).isDisplayed();
					sa.assertTrue(csrErr, "CSR Name warning message is not displayed ");
					if(csrErr) {
					String csrErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).getText();
					System.out.println(
							"CSR Name  message displayed as : " + csrErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for CSR name field displayed as : " + csrErrMsg);
					Log.info("CSR Name warning message displayed as : " + csrErrMsg);	
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for CSR Name field is not displaying");
					}
					
					
				//Technology Error message	
					boolean technologyErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).isDisplayed();
					sa.assertTrue(technologyErr, "Technology warning is not displayed ");
					if(technologyErr) {
					String technologyErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).getText();
					System.out.println(
							"Technology  message displayed as : " + technologyErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message displayed as : " + technologyErrMsg);
					Log.info("Technology validation message displayed as : " + technologyErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Technology field is not displaying");
					}
					
			scrollToTop();
			Thread.sleep(3000);
			
				//Site Order Number Error Message	
					boolean SiteOrderNumberErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_sitOrderNumberErrmsg")).isDisplayed();
					sa.assertTrue(SiteOrderNumberErr, "Site order Number warning message is not displayed ");
					if(SiteOrderNumberErr) {
					String siteOrderErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_sitOrderNumberErrmsg")).getText();
					System.out.println(
							"Site order Number warning  message displayed as : " + siteOrderErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for Site order Number field displayed as : " + siteOrderErrMsg);
					Log.info("Site order Number warning message displayed as : " + siteOrderErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Site Order Number field is not displaying");
					}
					
					
				//Circuit Reference Error Message
					boolean circuitRefErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceErrmsg")).isDisplayed();
					sa.assertTrue(circuitRefErr, "Circuit Reference warning message is not displayed ");
					if(circuitRefErr) {
					String circuitReferenceErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceErrmsg")).getText();
					System.out.println(
							"Circuit Reference warning  message displayed as : " + circuitReferenceErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for Circuit Reference field displayed as : " + circuitReferenceErrMsg);
					Log.info("Circuit Reference warning message displayed as : " + circuitReferenceErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Circuit Reference field is not displaying");
					}
				
					
				//IV Reference Error Messages
					boolean IVrefErr = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_IVReferenceErrmsg")).isDisplayed();
					sa.assertTrue(IVrefErr, "IV Reference warning message is not displayed ");
					if(IVrefErr) {
					String IVReferenceErrMsg = getwebelement(
							xml.getlocator("//locators/" + application + "/Addsiteorder_IVReferenceErrmsg")).getText();
					System.out.println(
							"IV Reference warning  message displayed as : " + IVReferenceErrMsg);
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step :  validation message for IV Reference field displayed as : " + IVReferenceErrMsg);
					Log.info("IV Reference warning message displayed as : " + IVReferenceErrMsg);
					}
					else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for IV Reference field is not displaying");
					}
					
		
					
					//Validate site order Number Field
					 		validatesiteOrderNumber_AddSiteOrder(application);
					
					//Validate Country dropdown
							validateCountry_AddSiteorder(application);
								
					//Validate City Fields
							validateCity_AddSiteOrder(application);
							
					//Validate Site/CSR field
							validateSite_AddSiteOrder(application);
							
			scrolltoend();
			Thread.sleep(3000);
			
					//Validate performance reporting dropdown
							validatePerformancereporting_AddSiteOrder(application);
							
							
					//validate proactive Monitoring dropdown
							validateProactiveMonitoring_AddSiteOrder(application);
							
							
					//Validate Smarts monitoring dropdown
							validateSmartsMOnitoring_AddSiteOrder(application);
							
						
					//Validate IV Reference dropdown
							validateIVReference_AddSiteorder(application);

					//Validate Circuit reference Field
							validateCircuitreference_AddSiteOrder(application);

					//Spoke ID
							validatespokeId_AddSiteOrder(application);

							
					//Validate Site Alias field
							validateSiteAlias_AddSiteOrder(application);

									
					//Verify Remark field
							validateRemark_AddSiteOrder(application);
							
					//Validate Offnet checkbox
							validateoffnet_AddSiteOrder(application);		

			
		
	if( (interfaceSpeed.equals("1GigE")))	{
		
		for(int i=0; i<IVrefrence.length; i++) {
			if(IVrefrence[i].equals("Primary")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
				Thread.sleep(3000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
				Thread.sleep(3000);
				
				technologyDropdownFor1GigE_HubAndSpoke_Primary_offnetselected(application, "Primary");
				
			}
			
			else if(IVrefrence[i].equals("Access")) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
				Thread.sleep(3000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
				Thread.sleep(3000);
				
				technologyDropdownFor1GigE_HubAndSpoke_Access_offnetselected(application, "Access");
				
			}
		}
	}	
			
	else if(interfaceSpeed.equals("10GigE"))	{
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
		
		DriverTestcase.logger.log(LogStatus.INFO, " If 'Offnet' is selected for '10GigE', 'No value displays under 'Technology' dropdown");
	}
			
			
	
		//Validate OK button
				OKbutton_AddSiteOrder(application);

		//validate cancel button
				cancelbutton_AddSiteOrder(application);

				
		Thread.sleep(3000);

	scrolltoend();
	Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cancel")));
		Thread.sleep(3000);
		
		sa.assertAll();
		
		}catch(AssertionError e) {
		   e.printStackTrace();
			}
	}




public void verifySiteOrderForE_PN(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
	
	DriverTestcase.logger.log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> E-PN (Any to Any)");

	try {
			
			String[] IVrefrence = { "Primary", "Access" };

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			Thread.sleep(5000);
				
			//Country Error message	
				boolean countryErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).isDisplayed();
				sa.assertTrue(countryErr, "Country warning message is not displayed ");
				if(countryErr) {
				String countryErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).getText();
				System.out.println(
						"Country  message displayed as : " + countryErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for Country field displayed as : " + countryErrMsg);
				Log.info("Country warning message displayed as : " + countryErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Country field is not displaying");
				}
				
			//City Error message	
				boolean cityErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).isDisplayed();
				sa.assertTrue(cityErr, "City warning message is not displayed ");
				if(cityErr) {
				String cityErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).getText();
				System.out.println(
						"City  message displayed as : " + cityErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for City field displayed as : " + cityErrMsg);
				Log.info("City warning message displayed as : " + cityErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for City field is not displaying");
				}
				
				
			//CSR name Error message	
				boolean csrErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).isDisplayed();
				sa.assertTrue(csrErr, "CSR Name warning message is not displayed ");
				if(csrErr) {
				String csrErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).getText();
				System.out.println(
						"CSR Name  message displayed as : " + csrErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for CSR name field displayed as : " + csrErrMsg);
				Log.info("CSR Name warning message displayed as : " + csrErrMsg);	
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for CSR Name field is not displaying");
				}
				
				
			//Technology Error message	
				boolean technologyErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).isDisplayed();
				sa.assertTrue(technologyErr, "Technology warning is not displayed ");
				if(technologyErr) {
				String technologyErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).getText();
				System.out.println(
						"Technology  message displayed as : " + technologyErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message displayed as : " + technologyErrMsg);
				Log.info("Technology validation message displayed as : " + technologyErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Technology field is not displaying");
				}
		scrollToTop();
		Thread.sleep(3000);
				
			//Site Order Number Error Message	
				boolean SiteOrderNumberErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_sitOrderNumberErrmsg")).isDisplayed();
				sa.assertTrue(SiteOrderNumberErr, "Site order Number warning message is not displayed ");
				if(SiteOrderNumberErr) {
				String siteOrderErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_sitOrderNumberErrmsg")).getText();
				System.out.println(
						"Site order Number warning  message displayed as : " + siteOrderErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for Site order Number field displayed as : " + siteOrderErrMsg);
				Log.info("Site order Number warning message displayed as : " + siteOrderErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Site Order Number field is not displaying");
				}
				
				
			//Circuit Reference Error Message
				boolean circuitRefErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceErrmsg")).isDisplayed();
				sa.assertTrue(circuitRefErr, "Circuit Reference warning message is not displayed ");
				if(circuitRefErr) {
				String circuitReferenceErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceErrmsg")).getText();
				System.out.println(
						"Circuit Reference warning  message displayed as : " + circuitReferenceErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for Circuit Reference field displayed as : " + circuitReferenceErrMsg);
				Log.info("Circuit Reference warning message displayed as : " + circuitReferenceErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Circuit Reference field is not displaying");
				}
			
				
			//IV Reference Error Messages
				boolean IVrefErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_IVReferenceErrmsg")).isDisplayed();
				sa.assertTrue(IVrefErr, "IV Reference warning message is not displayed ");
				if(IVrefErr) {
				String IVReferenceErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_IVReferenceErrmsg")).getText();
				System.out.println(
						"IV Reference warning  message displayed as : " + IVReferenceErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for IV Reference field displayed as : " + IVReferenceErrMsg);
				Log.info("IV Reference warning message displayed as : " + IVReferenceErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for IV Reference field is not displaying");
				}
			
		
				
				//Validate site order Number Field
				 		validatesiteOrderNumber_AddSiteOrder(application);

				
				//Validate Country dropdown
						validateCountry_AddSiteorder(application);
						
							
				//Validate City Fields
						validateCity_AddSiteOrder(application);
						
				//Validate Site/CSR field
						validateSite_AddSiteOrder(application);
						
			scrolltoend();
			Thread.sleep(3000);
			
				//Validate performance reporting dropdown
						validatePerformancereporting_AddSiteOrder(application);
						
						
				//validate proactive Monitoring dropdown
						validateProactiveMonitoring_AddSiteOrder(application);
						
						
				//Validate Smarts monitoring dropdown
						validateSmartsMOnitoring_AddSiteOrder(application);
						
					
				//Validate IV Reference dropdown
						validateIVReference_AddSiteorder(application);

				//Validate Circuit reference Field
						validateCircuitreference_AddSiteOrder(application);

				//Validate EPN Offnet checkbox
					
						validateEPNoffnet_AddSiteOrder(application);
					
						
				//validate EPN EOSDH checkbox
				if(interfaceSpeed.equals("1GigE")) {
					validateEPNEOSDH_AddSiteOrder(application);
				}else {
					System.out.println(" 'EPN EOSDH' checkbix does not display for 10G interface speed");
					DriverTestcase.logger.log(LogStatus.PASS, " 'EPN EOSDH' checkbix does not display for 10G interface speed");
				}

				//Validate Site Alias field
						validateSiteAlias_AddSiteOrder(application);

								
						
				//Verify Remark field
						validateRemark_AddSiteOrder(application);

		
	
if( (interfaceSpeed.equals("1GigE")))	{
	
	for(int i=0; i<IVrefrence.length; i++) {
		if(IVrefrence[i].equals("Primary")) {
			

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
			Thread.sleep(3000);
			
			technologyDropdownFor1GigE_EPN_Primary(application);
			
		}
		
		else if(IVrefrence[i].equals("Access")) {
			

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
			Thread.sleep(3000);
			
			technologyDropdownFor1GigE_EPN_Access(application);
			
		}
	}
}	
		
else if(interfaceSpeed.equals("10GigE"))	{
	
	for(int i=0; i<IVrefrence.length; i++) {
		if(IVrefrence[i].equals("Primary")) {
			

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
			Thread.sleep(3000);
			
			technologyDropdownFor10GigE_HubAndSpoke_primary(application);
			
		}
		
		else if(IVrefrence[i].equals("Access")) {
			

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
			Thread.sleep(3000);
			
			technologyDropdownFor10GigE_HubAndSpoke_Access(application);
			
		}
	}
	
}
	//Validate OK button
			OKbutton_AddSiteOrder(application);

	//validate cancel button
			cancelbutton_AddSiteOrder(application);

			
	Thread.sleep(3000);
scrolltoend();
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cancel")));
	Thread.sleep(3000);
	
	sa.assertAll();
	
	}catch(AssertionError e) {
	   e.printStackTrace();
		}
}


public void verifySiteOrderForEPN_EOSDHselected(String application, String interfaceSpeed) throws InterruptedException, DocumentException, IOException{
	
	DriverTestcase.logger.log(LogStatus.INFO, " Site order functions will be performed for 'VPN Topology' --> E-PN (Any to Any)");

	try {
			
			String[] IVrefrence = { "Primary", "Access" };

			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			Thread.sleep(5000);
				
			//Country Error message	
				boolean countryErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).isDisplayed();
				sa.assertTrue(countryErr, "Country warning message is not displayed ");
				if(countryErr) {
				String countryErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_countryerrmsg")).getText();
				System.out.println(
						"Country  message displayed as : " + countryErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for Country field displayed as : " + countryErrMsg);
				Log.info("Country warning message displayed as : " + countryErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Country field is not displaying");
				}
				
			//City Error message	
				boolean cityErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).isDisplayed();
				sa.assertTrue(cityErr, "City warning message is not displayed ");
				if(cityErr) {
				String cityErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_devciexngCityErrmsg")).getText();
				System.out.println(
						"City  message displayed as : " + cityErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for City field displayed as : " + cityErrMsg);
				Log.info("City warning message displayed as : " + cityErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for City field is not displaying");
				}
				
				
			//CSR name Error message	
				boolean csrErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).isDisplayed();
				sa.assertTrue(csrErr, "CSR Name warning message is not displayed ");
				if(csrErr) {
				String csrErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_csrnameErrmsg")).getText();
				System.out.println(
						"CSR Name  message displayed as : " + csrErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for CSR name field displayed as : " + csrErrMsg);
				Log.info("CSR Name warning message displayed as : " + csrErrMsg);	
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for CSR Name field is not displaying");
				}
				
				
			//Technology Error message	
				boolean technologyErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).isDisplayed();
				sa.assertTrue(technologyErr, "Technology warning is not displayed ");
				if(technologyErr) {
				String technologyErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsitieorder_technologyErrmsg")).getText();
				System.out.println(
						"Technology  message displayed as : " + technologyErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message displayed as : " + technologyErrMsg);
				Log.info("Technology validation message displayed as : " + technologyErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Technology field is not displaying");
				}
		scrollToTop();
		Thread.sleep(3000);
				
			//Site Order Number Error Message	
				boolean SiteOrderNumberErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_sitOrderNumberErrmsg")).isDisplayed();
				sa.assertTrue(SiteOrderNumberErr, "Site order Number warning message is not displayed ");
				if(SiteOrderNumberErr) {
				String siteOrderErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_sitOrderNumberErrmsg")).getText();
				System.out.println(
						"Site order Number warning  message displayed as : " + siteOrderErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for Site order Number field displayed as : " + siteOrderErrMsg);
				Log.info("Site order Number warning message displayed as : " + siteOrderErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Site Order Number field is not displaying");
				}
				
				
			//Circuit Reference Error Message
				boolean circuitRefErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceErrmsg")).isDisplayed();
				sa.assertTrue(circuitRefErr, "Circuit Reference warning message is not displayed ");
				if(circuitRefErr) {
				String circuitReferenceErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceErrmsg")).getText();
				System.out.println(
						"Circuit Reference warning  message displayed as : " + circuitReferenceErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for Circuit Reference field displayed as : " + circuitReferenceErrMsg);
				Log.info("Circuit Reference warning message displayed as : " + circuitReferenceErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for Circuit Reference field is not displaying");
				}
			
				
			//IV Reference Error Messages
				boolean IVrefErr = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_IVReferenceErrmsg")).isDisplayed();
				sa.assertTrue(IVrefErr, "IV Reference warning message is not displayed ");
				if(IVrefErr) {
				String IVReferenceErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_IVReferenceErrmsg")).getText();
				System.out.println(
						"IV Reference warning  message displayed as : " + IVReferenceErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for IV Reference field displayed as : " + IVReferenceErrMsg);
				Log.info("IV Reference warning message displayed as : " + IVReferenceErrMsg);
				}
				else {
					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory Warning/Validation message for IV Reference field is not displaying");
				}
			
		
				
				//Validate site order Number Field
				 		validatesiteOrderNumber_AddSiteOrder(application);

				//Validate Country dropdown
						validateCountry_AddSiteorder(application);
						
				//Validate City Fields
						validateCity_AddSiteOrder(application);
						
				//Validate Site/CSR field
						validateSite_AddSiteOrder(application);
						
			scrolltoend();
			Thread.sleep(3000);
			
				//Validate performance reporting dropdown
						validatePerformancereporting_AddSiteOrder(application);
						
						
				//validate proactive Monitoring dropdown
						validateProactiveMonitoring_AddSiteOrder(application);
						
						
				//Validate Smarts monitoring dropdown
						validateSmartsMOnitoring_AddSiteOrder(application);
						
				//Validate IV Reference dropdown
						validateIVReference_AddSiteorder(application);

				//Validate Circuit reference Field
						validateCircuitreference_AddSiteOrder(application);

				//Validate EPN Offnet checkbox
						validateEPNoffnet_AddSiteOrder(application);
					
						
				//validate EPN EOSDH checkbox
				if(interfaceSpeed.equals("1GigE")) {
					validateEPNEOSDH_AddSiteOrder(application);
				}else {
					System.out.println(" 'EPN EOSDH' checkbix does not display for 10G interface speed");
					DriverTestcase.logger.log(LogStatus.PASS, " 'EPN EOSDH' checkbix does not display for 10G interface speed");
				}

				//Validate Site Alias field
						validateSiteAlias_AddSiteOrder(application);
						
				//Verify Remark field
						validateRemark_AddSiteOrder(application);

		
if( (interfaceSpeed.equals("1GigE")))	{
	
	for(int i=0; i<IVrefrence.length; i++) {
		if(IVrefrence[i].equals("Primary")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")));
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Primary")));
			Thread.sleep(3000);
			
			technologyDropdownFor1GigE_EPNEOSDHselected_Primary(application);
			
		}
		
		else if(IVrefrence[i].equals("Access")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreference_Access")));
			Thread.sleep(3000);
			
			technologyDropdownFor1GigE_EPNEOSDHselected_Access(application);
			
		}
	}
}	
		
	//Validate OK button
			OKbutton_AddSiteOrder(application);

	//validate cancel button
			cancelbutton_AddSiteOrder(application);

			
	Thread.sleep(3000);
scrolltoend();
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cancel")));
	Thread.sleep(3000);
	
	sa.assertAll();
	
	}catch(AssertionError e) {
	   e.printStackTrace();
		}
}



	
	public void practise() {
		
		By.xpath("");
	}
	
	
	public void verifySiteOrderFields_NonterminationField(String application) throws InterruptedException, DocumentException {
		boolean Nonterminationpointcheckbox=false;
		try {		
			Nonterminationpointcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isDisplayed();
			sa.assertTrue(Nonterminationpointcheckbox, "On selecting 'Overture' under Technology, Non termination point checkbox is not available");
			if(Nonterminationpointcheckbox) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Non Termination Point' checkbox is displayed under 'Add Site order' page as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			}
			
			boolean nonTerminaionpointselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
			sa.assertFalse(nonTerminaionpointselection,"Non-termination point checbox under Add site is selected by default");
			if(nonTerminaionpointselection) {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Non-Termination Point' checkbox is selected by default");
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Non-Termination Point' checkbox is not selected by default as expected");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			System.out.println(" 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Non-Termination Point' checkbox is selected by default");
			System.out.println( " 'Non-Termination Point' checkbox is selected by default");
		}
	}
	
	
	public void verifySiteOrderFields_protected(String application) throws InterruptedException, DocumentException {
		boolean portectedcheckbox=false;
		try {	
			portectedcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isDisplayed();
		
			sa.assertTrue(portectedcheckbox, "On selecting Atrica under Technology, protected checkbox is not available");
			if(portectedcheckbox) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Protected' checkbox is displayed under 'Add Site order' page as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Protected' checkbox is not available under 'Add Site order' page");
			}
		
			boolean protectedtselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
			sa.assertFalse(protectedtselection,"Protected checbox under Add site is selected by default");
			if(protectedtselection) {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Protected' checkbox is selected by default");
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Protected' checkbox is not selected by default as expected");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Protected' checkbox is not Available under 'Add Site order' page");
			System.out.println(" 'Protected' checkbox is not Available under 'Add Site order' page");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Protected' checkbox is selected by default");
			System.out.println( " 'Protected' checkbox is selected by default");
		}
	}
	
	
	public void verifySiteOrderField_deviceName(String application) throws InterruptedException, DocumentException {
		boolean devicename=false;
	try {	
		devicename=	getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).isDisplayed();
		sa.assertTrue(devicename, "On selecting Atrica under Technology, Device name is not available");
		if(devicename) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Device Name' field is displaying under 'Add Site Order' page as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Device Name' field is not displaying under 'Add Site Order' page");
	
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Device Name' field is not displaying under 'Add Site Order' page");
		System.out.println(" 'Device Name' field is not displaying under 'Add Site Order' page");
	}
	}
	
	
	public void verifySiteorderFields_mappingMode(String application) throws InterruptedException, DocumentException {
		
		String[] mappingMode=new String[2];
		int i=0;
		boolean MappingdropdownAvailability=false;
		try {
			MappingdropdownAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")).isDisplayed();
			
			if(MappingdropdownAvailability) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'mapping mode' dropdown is displaying in 'Add Site Order' page as expected");
				System.out.println(" 'mapping mode' dropdown is displaying in 'Add Site Order' page as expected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")));
				Thread.sleep(3000);
				
				List<WebElement> listofMappingMode = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
				
				if(listofMappingMode.size()>=1) {	
					for (WebElement mappingModetypes : listofMappingMode) {

								Log.info("list of Mapping modes are : " + mappingModetypes.getText());
								DriverTestcase.logger.log(LogStatus.PASS,"The list of Mapping modes  inside dropdown is: "+mappingModetypes.getText());
								System.out.println("The list of mapping Modes  inside dropdown is: "+mappingModetypes.getText());
							 mappingMode[i]=	mappingModetypes.getText();
							 i++;
								
							}
				}			
				
		
			for(int j=0; j<mappingMode.length; j++) {
				
				if(mappingMode[j].equalsIgnoreCase("Port Based")) {
					Clickon(getwebelement("//div[text()='"+ mappingMode[j] +"']"));
					Thread.sleep(3000);
				
					boolean portname=false;
				try {	
					portname=getwebelement(xml.getlocator("//locators/" + application + "/portname_textField")).isDisplayed();
					Thread.sleep(3000);
					
					if(portname) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'Port name' text field is displaying as expected");
						System.out.println(" 'Port name' text field is displayig");
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Port name' text field is not displaying");
						System.out.println(" 'Port name' text field is not displaying");
					}
				}catch(NoSuchElementException e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Port name' text field is not displaying");
					System.out.println(" 'Port name' text field is not displaying");
				}
					
					
				}
				else if(mappingMode[j].equalsIgnoreCase("Vlan Based")) {
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")));
					Thread.sleep(3000);
					
					Clickon(getwebelement("//div[text()='"+ mappingMode[j] +"']"));
					Thread.sleep(3000);
					
					boolean vlanName=false;
					try {	
						vlanName=getwebelement(xml.getlocator("//locators/" + application + "/vlanid_textfield")).isDisplayed();
						Thread.sleep(3000);
						
						if(vlanName) {
							DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN Id' text field is displaying as expected");
							System.out.println(" 'VLAN Id' text field is displayig");
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Id' text field is not displaying");
							System.out.println(" 'VLAN Id' text field is not displaying");
						}
					}catch(NoSuchElementException e) {
						e.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Id' text field is not displaying");
						System.out.println(" 'VLAN Id' text field is not displaying");
					}

			}
			}				
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
				System.out.println(" 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.PASS, " 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
			System.out.println(" 'mapping mode' dropdown is not displaying in 'Add Site Order' page");
		}
	}

	
	public void technologyDropdownFor1GigE(String application) throws InterruptedException, DocumentException {
		
		String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };

		boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox;
		
		// Technology dropdown
				technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
						.isDisplayed();
				sa.assertTrue(technology, "Technology dropdown is not displayed");

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
				List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
				
			if(listoftechnology.size()>=1) {	
				for (WebElement technologytypes : listoftechnology) {

							Log.info("list of technology are : " + technologytypes.getText());
							DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
							System.out.println("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
							String technologyValue=technologytypes.getText();
				}
				
		
				for(int k=0;k<Technology.length;k++) {	
					//Actelis	
							if(Technology[k].equalsIgnoreCase("Actelis")) {
								DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
										+ "no additional fields displays");
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
								Clickon(getwebelement("//div[text()='" +Technology[k] + "']"));
									
							}
							
						//Atrica	
							else if(Technology[k].equalsIgnoreCase("Atrica")) {
								
								DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
										+ "list of fields should occur: "
										+ "Device name - Mandatory field"
										+ "Non Termination point checkbox"
										+ "Protected checkbox");
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
								Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
								
								
						//Device Name	
							verifySiteOrderField_deviceName(application);
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
						}
						
						//Overture	
							else if(Technology[k].equalsIgnoreCase("Overture")) {
								DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
										+ "list of fields should occur: "
										+ "Non Termination point checkbox"
										+ "Protected checkbox");
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
								Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
								
							//Non Termination Point	
								verifySiteOrderFields_NonterminationField(application);
								
							//Protected checkbox	
								verifySiteOrderFields_protected(application);
				
							}
					
						//Alu	
							else if(Technology[k].equalsIgnoreCase("Alu")) {
								
								DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
										+ "list of fields should occur: "
										+ "Device name - Mandatory field");
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
								Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
								
							//Device Name	
								verifySiteOrderField_deviceName(application);
							}
							
							
					//Accedian
							else if((Technology[k].equalsIgnoreCase("Accedian-1G"))) {
								
								DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
										+ "Non Termination point checkbox"
										+ "Protected checkbox");
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
								Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

							//Non Termination Point	
								verifySiteOrderFields_NonterminationField(application);

								
							//Protected checkbox	
								verifySiteOrderFields_protected(application);			
									
							}	
						
					//Cyan		
							else if(Technology[k].equalsIgnoreCase("Cyan")) {
								
								DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
										+ "list of fields should occur: "
										+ "Non Termination point checkbox");
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
								Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));

							//Non Termination Point	
								verifySiteOrderFields_NonterminationField(application);
								
							}	
				}
			}else {
				
				System.out.println("no values are available inside technology dropdown for Add site order");
				DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
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
				List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
				
			if(listoftechnology.size()>=1) {	
				for (WebElement technologytypes : listoftechnology) {

							Log.info("list of technology are : " + technologytypes.getText());
							System.out.println("list of technology are : " + technologytypes.getText());
							DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
				}
			}else {
				
				System.out.println("no values are available inside technology dropdown for Add site order");
				DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
			}
			
		DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
						+ "list of fields should occur: "
						+ "Non Termination point checkbox"
						+ "Protected checkbox");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
				Clickon(getwebelement("//div[text()='" + Technology + "']"));
				
				
			//Non Termination Point	
				verifySiteOrderFields_NonterminationField(application);

				
			//Protected checkbox	
				verifySiteOrderFields_protected(application);

			
			}	

	
public void technologyDropdownFor1GigE_HubAndSpoke_Access(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian", "Cyan" };
	
	String[] GCROLOType = { "2A3", "1A4", "1A3", "2A4/1A4" };
	
	String[] VLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
	
	String[] primaryVLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
			

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType, PrimaryVlan, primaryVlanetherType;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						System.out.println("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			
			for(int k=0;k<Technology.length;k++) {
				
				//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
								
						}
						
						
					//Atrica	
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Protected checkbox");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
						}

				//Overture
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Protected checkbox"
									+ "GCR OLO Type dropdown"
									+ "VLAN text field"
									+ "VLAN Ether Type dropdown"
									+ "Primary VLAN Text Field"
									+ "Primary VLAN Ether Type dropdown");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);		
					
					
						
					//GCR OLO Type dropdown
					try {
						GCRoloField = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
						sa.assertTrue(GCRoloField, "GCR OLO Type dropdown is not Available");
						if(GCRoloField) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'GCR OLO Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
						List<WebElement> listofGcrOLOtype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						if(listofGcrOLOtype.size()>=1) {
						for (WebElement GCROlotypes : listofGcrOLOtype) {
								boolean match = false;
							for (int i = 0; i < GCROLOType.length; i++) {
								if (GCROlotypes.getText().equals(GCROLOType[i])) {
									match = true;
									Log.info("list of 'GCR OLO Type' are : " + GCROlotypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS,"The list of 'GCR OLO Type' inside dropdown is: "+GCROlotypes.getText());
									
								}
								}
								sa.assertTrue(match);
								
						}
					}else {
						System.out.println("no values are available inside 'GCR OLO Type' dropdown for Add site order");
						DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'GCR OLO Type' dropdown for Add site order");
				}
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site order' page");
					}	
					}catch(NoSuchElementException e) {
						e.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
						System.out.println(" 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
					}catch(Exception ee) {
						ee.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'GCR OLO type' dropdown");
						System.out.println( " Not able to enter value under 'GCR OLO type' dropdown");
					}
					
							
							
					//VLAN Text field
					try {
						Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
						sa.assertTrue(Vlan, " 'VLAN' text field is not available");
						if(Vlan) {
							DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
					}catch(NoSuchElementException e) {
						e.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
						System.out.println(" 'VLAN' text field is not Available under 'Add Site order' page");
					}
				
						
						
					//VLAN Ether Type
					try {
						VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
						if(VlanetherType) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
						List<WebElement> listofVLANethertype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						if(listofVLANethertype.size()>=1) {
						for (WebElement VLANEthertypes : listofVLANethertype) {
								boolean match = false;
							for (int i = 0; i < VLANEtherType.length; i++) {
								if (VLANEthertypes.getText().equals(VLANEtherType[i])) {
									match = true;
									Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
									
								}
								}
							sa.assertTrue(match);
								
						}
					}else {
						System.out.println("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
						DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
					}catch(Exception e) {
						e.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
					}
					
						
						
					//Primary VLAN Text field
					try {
						PrimaryVlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).isDisplayed();
						sa.assertTrue(PrimaryVlan, " 'Primary VLAN' text field is not available");
						if(PrimaryVlan) {
							DriverTestcase.logger.log(LogStatus.PASS, " 'Primary VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
					}catch(NoSuchElementException e) {
						e.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN' text field is not Available under 'Add Site order' page");
						System.out.println(" 'Primary VLAN' text field is not Available under 'Add Site order' page");
					}
				
						
					//Primary VLAN Ether Type	
					try {
						primaryVlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(primaryVlanetherType, " 'Primary VLAN Ether Type' dropdown is not Available");
						if(primaryVlanetherType) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'Primary VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")));
						List<WebElement> listofprimaryVLANethertype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						if(listofprimaryVLANethertype.size()>=1) {
						for (WebElement primaryVLANEthertypes : listofprimaryVLANethertype) {
								boolean match = false;
							for (int i = 0; i < primaryVLANEtherType.length; i++) {
								if (primaryVLANEthertypes.getText().equals(primaryVLANEtherType[i])) {
									match = true;
									Log.info("list of 'Primary VLAN Ether Type' are : " + primaryVLANEthertypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS,"The list of 'Primary VLAN Ether Type' inside dropdown is: "+primaryVLANEthertypes.getText());
									
								}
								}
							sa.assertTrue(match);
								
						}
					}else {
						System.out.println("no values are available inside 'Primary VLAN Ether Type' dropdown for Add site order");
						DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'Primary VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
					}catch(Exception e) {
						e.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN Ether type' dropdown is not Available under 'Add Site order' page");
					}
				}		

						
			//Alu
				else if(Technology[k].equalsIgnoreCase("Alu")) {
					DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
							+ "no additional fields display");
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
				}	
				

				//Accedian-1G		
					else if((Technology[k].equalsIgnoreCase("Accedian-1G"))) {
					
					DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
							+ "Non Termination point checkbox"
							+ "Protected checkbox");
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
					
				//Non Termination Point	
					verifySiteOrderFields_NonterminationField(application);
					
				//Protected checkbox	
					verifySiteOrderFields_protected(application);
				}	

				
			  //Cyan
					else if(Technology[k].equalsIgnoreCase("Cyan")) {
						
						DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
								+ "list of fields should occur: "
								+ "Non Termination point checkbox");
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
						Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
						
						
					//Non Termination Point	
						verifySiteOrderFields_NonterminationField(application);
					}
			
			}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
}



public void technologyDropdownFor1GigE_HubAndSpoke_Access_offnetselected(String application, String IVReferenceSelection) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Overture", "Accedian-1G" };
	
	String[] GCROLOType = { "2A3", "1A4", "1A3", "2A4/1A4" };
	
	String[] VLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
	
	String[] primaryVLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
			

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType, PrimaryVlan, primaryVlanetherType;
	
	
	//Select "IV Reference" as "Primary
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
	Thread.sleep(3000);
	
	Clickon(getwebelement("//span[contains(text(),'"+ IVReferenceSelection+"')]"));
	Thread.sleep(3000);
	
	
//Select "Offnet" checkbox
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
	Thread.sleep(3000);
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());

			}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	
		for(int k=0;k<Technology.length;k++) {
			
		if(Technology[k].equalsIgnoreCase("Overture")) {
				
				DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
						+ "list of fields should occur: "
						+ "Non Termination point checkbox"
						+ "Protected checkbox"
						+ "GCR OLO Type dropdown"
						+ "VLAN text field"
						+ "VLAN Ether Type dropdown"
						+ "Primary VLAN Text Field"
						+ "Primary VLAN Ether Type dropdown");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
				Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
				
				
		//Non Termination Point	
		verifySiteOrderFields_NonterminationField(application);
			
		//Protected checkbox	
		verifySiteOrderFields_protected(application);
	
			
		//GCR OLO Type dropdown
		try {
			GCRoloField = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
			sa.assertTrue(GCRoloField, "GCR OLO Type dropdown is not Available");
			if(GCRoloField) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'GCR OLO Type' drodpown field is displaying under 'Add Site Order' page as expected");
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
			List<WebElement> listofGcrOLOtype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

			if(listofGcrOLOtype.size()>=1) {
			for (WebElement GCROlotypes : listofGcrOLOtype) {
					boolean match = false;
				for (int i = 0; i < GCROLOType.length; i++) {
					if (GCROlotypes.getText().equals(GCROLOType[i])) {
						match = true;
						Log.info("list of 'GCR OLO Type' are : " + GCROlotypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of 'GCR OLO Type' inside dropdown is: "+GCROlotypes.getText());
						
					}
					}
					sa.assertTrue(match);
					
			}
		}else {
			System.out.println("no values are available inside 'GCR OLO Type' dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'GCR OLO Type' dropdown for Add site order");
	}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site order' page");
		}	
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
		}
		
				
				
		//VLAN Text field
		try {
			Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
			sa.assertTrue(Vlan, " 'VLAN' text field is not available");
			if(Vlan) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
		}
	
			
			
		//VLAN Ether Type
		try {
			VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
			sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
			if(VlanetherType) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
			List<WebElement> listofVLANethertype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

			if(listofVLANethertype.size()>=1) {
			for (WebElement VLANEthertypes : listofVLANethertype) {
					boolean match = false;
				for (int i = 0; i < VLANEtherType.length; i++) {
					if (VLANEthertypes.getText().equals(VLANEtherType[i])) {
						match = true;
						Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
						
					}
					}
				sa.assertTrue(match);
					
			}
		}else {
			System.out.println("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
	}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
		}	
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
		}
		
			
			
			
		//Primary VLAN Text field
		try {
			PrimaryVlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).isDisplayed();
			sa.assertTrue(PrimaryVlan, " 'Primary VLAN' text field is not available");
			if(PrimaryVlan) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Primary VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN' text field is not Available under 'Add Site order' page");
		}
	
			
		//Primary VLAN Ether Type	
		try {
			primaryVlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")).isDisplayed();
			sa.assertTrue(primaryVlanetherType, " 'Primary VLAN Ether Type' dropdown is not Available");
			if(primaryVlanetherType) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Primary VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")));
			List<WebElement> listofprimaryVLANethertype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

			if(listofprimaryVLANethertype.size()>=1) {
			for (WebElement primaryVLANEthertypes : listofprimaryVLANethertype) {
					boolean match = false;
				for (int i = 0; i < primaryVLANEtherType.length; i++) {
					if (primaryVLANEthertypes.getText().equals(primaryVLANEtherType[i])) {
						match = true;
						Log.info("list of 'Primary VLAN Ether Type' are : " + primaryVLANEthertypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of 'Primary VLAN Ether Type' inside dropdown is: "+primaryVLANEthertypes.getText());
						
					}
					}
				sa.assertTrue(match);
					
			}
		}else {
			System.out.println("no values are available inside 'Primary VLAN Ether Type' dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'Primary VLAN Ether Type' dropdown for Add site order");
	}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN Ether Type' dropdown is not available under 'Add Site order' page");
		}	
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN Ether type' dropdown is not Available under 'Add Site order' page");
		}
		}		
			
		//Accedian-1G
			else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ) {
				
				DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
						+ "Non Termination point checkbox"
						+ "Protected checkbox");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
				Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
				
			//Non Termination Point	
				verifySiteOrderFields_NonterminationField(application);
	
			//Protected checkbox
			 verifySiteOrderFields_protected(application);
					
			}	
			
		}
}


public void technologyDropdownFor1GigE_HubAndSpoke_Primary(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
		

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			System.out.println("Number of technology sizes: "+listoftechnology.size());
			
			for (WebElement technologytypesSample : listoftechnology ) {
				System.out.println(" list of technologies are: "+ technologytypesSample.getText());
			}
			
	try {		
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology ) {
				
				  driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
	
				  System.out.println("tech value to be found: "+technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						System.out.println("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			

			for(int k=0;k<Technology.length;k++) {
				
				
					//Actelis	
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
						}
						
					//Atrica	
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Protected checkbox"
									+ " Device name field");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
				
//						//Device Name	
							verifySiteOrderField_deviceName(application);
					}
					
						
					//Overture	
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Protected checkbox");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
					}	
						
						
				//Alu		
						else if(Technology[k].equalsIgnoreCase("Alu")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ "'Device Name' Text field should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Device Name	
								verifySiteOrderField_deviceName(application);
						
						}
						
						
					//Accedian-1G
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ "Non Termination point checkbox"
									+ "Protected checkbox");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
						}
				

					//Cyan
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						}
						
			}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
	}catch(StaleElementReferenceException e){

	    e.printStackTrace();

	  }	
	
}



public void technologyDropdownFor1GigE_HubAndSpoke_Primary_offnetselected(String application, String IVReferenceSelection) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Overture","Accedian-1G" };
		
	//Select "IV Reference" as "Primary
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
			Thread.sleep(3000);
			
			Clickon(getwebelement("//span[contains(text(),'"+ IVReferenceSelection+"')]"));
			Thread.sleep(3000);
			
			
	//Select "Offnet" checkbox
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
			Thread.sleep(3000);
			
			
	
	boolean technology, Nonterminationpointcheckbox, portectedcheckbox;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						System.out.println("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
			}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	
		for(int k=0;k<Technology.length;k++) {
			
			
			if(Technology[k].equalsIgnoreCase("Overture")) {
				
				DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
						+ "list of fields should occur: "
						+ "Non Termination point checkbox"
						+ "Protected checkbox");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
				Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
				
			//Non Termination Point	
				verifySiteOrderFields_NonterminationField(application);

				
			//Protected checkbox	
				verifySiteOrderFields_protected(application);
	
		}		
			
			
			else if((Technology[k].equalsIgnoreCase("Accedian")) || (Technology[k].equalsIgnoreCase("Accedian-1G"))) {
				
				DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
						+ "Non Termination point checkbox"
						+ "Protected checkbox");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
				Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
				
			
			//Non Termination Point	
				verifySiteOrderFields_NonterminationField(application);

				
			//Protected checkbox	
				verifySiteOrderFields_protected(application);
			
	
			}	
			
		}
}



public void technologyDropdownFor10GigE_HubAndSpoke_primary(String application) throws InterruptedException, DocumentException {
	
	String Technology = "Accedian";

	boolean technology;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

				Log.info("list of technology are : " + technologytypes.getText());
				System.out.println("list of technology are : " + technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
					}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
					+ "list of fields should occur: "
					+ "Non Termination point checkbox"
					+ "Protected checkbox");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			System.out.println("site order to be selected is: "+Technology);
			Clickon(getwebelement("//div[text()='" + Technology + "']"));
			
		
		//Non Termination Point	
			verifySiteOrderFields_NonterminationField(application);

			
		//Protected checkbox	
			verifySiteOrderFields_protected(application);

		}

public void technologyDropdownFor10GigE_HubAndSpoke_Access(String application) throws InterruptedException, DocumentException {
	
	String Technology = "Accedian";

	boolean technology;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

				Log.info("list of technology are : " + technologytypes.getText());
				System.out.println("list of technology are : " + technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
					}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
					+ "list of fields should occur: "
					+ "Non Termination point checkbox"
					+ "Protected checkbox");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			System.out.println("site order to be selected is: "+Technology);
			Clickon(getwebelement("(//div[text()='"+ Technology +"'])[2]"));
			
		
		//Non Termination Point	
			verifySiteOrderFields_NonterminationField(application);

			
		//Protected checkbox	
			verifySiteOrderFields_protected(application);

		}

public void technologyDropdownFor1GigE_EPN_Access(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
	
	String[] GCROLOType = { "2A3", "1A4", "1A3", "2A4/1A4" };
	
	String[] VLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
	

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						System.out.println("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			
			
			for(int k=0;k<Technology.length;k++) {				
					//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
						}
						
						
				//Atrica
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Mapping Mode drodpown"
									+ " Device Name text field");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							
						//Mapping Mode
							verifySiteorderFields_mappingMode(application);
							
						//Device Name Text Field
							verifySiteOrderField_deviceName(application);
						}
						
						
				//Overture
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Device Name text Field"
									+ "Protected checkbox"
									+ "GCR OLO Type dropdown"
									+ "VLAN text field"
									+ "VLAN Ether Type dropdown");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);

					//Device Name	
							verifySiteOrderField_deviceName(application);
				
						
					//GCR OLO Type dropdown
							try {
						GCRoloField = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
						sa.assertTrue(GCRoloField, "GCR OLO Type dropdown is not Available");
						if(GCRoloField) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'GCR OLO Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
						List<WebElement> listofGcrOLOtype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						if(listofGcrOLOtype.size()>=1) {
						for (WebElement GCROlotypes : listofGcrOLOtype) {
								boolean match = false;
							for (int i = 0; i < GCROLOType.length; i++) {
								if (GCROlotypes.getText().equals(GCROLOType[i])) {
									match = true;
									Log.info("list of 'GCR OLO Type' are : " + GCROlotypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS,"The list of 'GCR OLO Type' inside dropdown is: "+GCROlotypes.getText());
									
								}
								}
								sa.assertTrue(match);
								
						}
					}else {
						System.out.println("no values are available inside 'GCR OLO Type' dropdown for Add site order");
						DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'GCR OLO Type' dropdown for Add site order");
				}
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site order' page");
					}
							}catch(Exception e) {
								e.printStackTrace();
								DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
							}	
							
							
					//VLAN Text field
							try {
						Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
						sa.assertTrue(Vlan, " 'VLAN' text field is not available");
						if(Vlan) {
							DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
							}catch(Exception e) {
								e.printStackTrace();
								DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
							}
						
						
					//VLAN Ether Type
							try {
						VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
						if(VlanetherType) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
						List<WebElement> listofVLANethertype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						if(listofVLANethertype.size()>=1) {
						for (WebElement VLANEthertypes : listofVLANethertype) {
								boolean match = false;
							for (int i = 0; i < VLANEtherType.length; i++) {
								if (VLANEthertypes.getText().equals(VLANEtherType[i])) {
									match = true;
									Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
									
								}
								}
							sa.assertTrue(match);
								
						}
					}else {
						System.out.println("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
						DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
							}catch(Exception e) {
								e.printStackTrace();
								DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
							}
				}		

						
					//Alu
						else if(Technology[k].equalsIgnoreCase("Alu")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ " Mapping Mode dropdown "
									+ " Device Name Text Field should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Mapping Mode
							verifySiteorderFields_mappingMode(application);

						//Device Name	
								verifySiteOrderField_deviceName(application);
											
						}	
						
						
				//Accedian
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ||  (Technology[k].equalsIgnoreCase("Accedian"))) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text Field should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
				

						//Device Name	
								verifySiteOrderField_deviceName(application);		
						}	
						
						
				//Cyan		
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						}
						
			}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
}



public void technologyDropdownFor1GigE_EPNEOSDHselected_Access(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
	
	String[] GCROLOType = { "2A3", "1A4", "1A3", "2A4/1A4" };
	
	String[] VLANEtherType = { "S-VLAN(88A8)", "C-VLAN(8100)" };
	

	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						System.out.println("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
				}
			}
			
			
			for(int k=0;k<Technology.length;k++) {				
					//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
						}
						
						
				//Atrica
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Mapping Mode drodpown"
									+ " Device Name text field");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							
						//Mapping Mode
							verifySiteorderFields_mappingMode(application);
							
						//Device Name Text Field
							verifySiteOrderField_deviceName(application);
						}
						
						
				//Overture
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Device Name text Field"
									+ "Protected checkbox"
									+ "GCR OLO Type dropdown"
									+ "VLAN text field"
									+ "VLAN Ether Type dropdown");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);

					//Device Name	
							verifySiteOrderField_deviceName(application);
				
						
					//GCR OLO Type dropdown
							try {
						GCRoloField = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")).isDisplayed();
						sa.assertTrue(GCRoloField, "GCR OLO Type dropdown is not Available");
						if(GCRoloField) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'GCR OLO Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
						List<WebElement> listofGcrOLOtype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						if(listofGcrOLOtype.size()>=1) {
						for (WebElement GCROlotypes : listofGcrOLOtype) {
								boolean match = false;
							for (int i = 0; i < GCROLOType.length; i++) {
								if (GCROlotypes.getText().equals(GCROLOType[i])) {
									match = true;
									Log.info("list of 'GCR OLO Type' are : " + GCROlotypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS,"The list of 'GCR OLO Type' inside dropdown is: "+GCROlotypes.getText());
									
								}
								}
								sa.assertTrue(match);
								
						}
					}else {
						System.out.println("no values are available inside 'GCR OLO Type' dropdown for Add site order");
						DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'GCR OLO Type' dropdown for Add site order");
				}
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site order' page");
					}
							}catch(Exception e) {
								e.printStackTrace();
								DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not Available under 'Add Site order' page");
							}	
							
							
					//VLAN Text field
							try {
						Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
						sa.assertTrue(Vlan, " 'VLAN' text field is not available");
						if(Vlan) {
							DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
							}catch(Exception e) {
								e.printStackTrace();
								DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
							}
						
						
					//VLAN Ether Type
							try {
						VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
						if(VlanetherType) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
						List<WebElement> listofVLANethertype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						if(listofVLANethertype.size()>=1) {
						for (WebElement VLANEthertypes : listofVLANethertype) {
								boolean match = false;
							for (int i = 0; i < VLANEtherType.length; i++) {
								if (VLANEthertypes.getText().equals(VLANEtherType[i])) {
									match = true;
									Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
									
								}
								}
							sa.assertTrue(match);
								
						}
					}else {
						System.out.println("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
						DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
							}catch(Exception e) {
								e.printStackTrace();
								DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
							}
				}		
						
					//Alu
						else if(Technology[k].equalsIgnoreCase("Alu")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ " Mapping Mode dropdown "
									+ " Device Name Text Field should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Mapping Mode
							verifySiteorderFields_mappingMode(application);

						//Device Name	
								verifySiteOrderField_deviceName(application);
											
						}	
						
						
				//Accedian
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ||  (Technology[k].equalsIgnoreCase("Accedian"))) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text Field should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
				
						//Device Name	
								verifySiteOrderField_deviceName(application);		
						}	
						
					//Cyan		
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
						}
		}
		
}


public void technologyDropdownFor1GigE_EPN_Primary(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
	
	boolean technology, devicename, Nonterminationpointcheckbox, portectedcheckbox, GCRoloField, Vlan, VlanetherType;
	
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						System.out.println("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			
			for(int k=0;k<Technology.length;k++) {
						
				//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
						}
						
						
				//Atrica		
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text field");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							
						
						//Device Name Text Field
							verifySiteOrderField_deviceName(application);
						
						}
						
						
					//Overture	
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Device Name text Field"
									+ "Protected checkbox"
									+  " should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);

					//Device Name	
						verifySiteOrderField_deviceName(application);
				}		
						
						
					//Alu	
						else if(Technology[k].equals("Alu")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ " Device Name Text Field should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
					
						//Device Name	
								verifySiteOrderField_deviceName(application);
					
						}	
						
						
				//Accedian		
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ||  (Technology[k].equalsIgnoreCase("Accedian"))) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text Field should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							

						//Device Name	
								verifySiteOrderField_deviceName(application);
						}	
						
				
				//Cyan
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
						}
			}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
}


public void technologyDropdownFor1GigE_EPNEOSDHselected_Primary(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = { "Actelis", "Atrica", "Overture","Alu", "Accedian-1G", "Cyan" };
	
	boolean technology;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

						Log.info("list of technology are : " + technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						System.out.println("The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
						String technologyValue=technologytypes.getText();
			}
			
			for(int k=0;k<Technology.length;k++) {
						
				//Actelis
						if(Technology[k].equalsIgnoreCase("Actelis")) {
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Actelis' is selected under Technology"
									+ "no additional fields displays");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
						}
						
						
				//Atrica		
						else if(Technology[k].equalsIgnoreCase("Atrica")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Atrica' is selected under Technology"
									+ "list of fields should occur: "
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text field");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							
						//Device Name Text Field
							verifySiteOrderField_deviceName(application);
						
						}
						
						
					//Overture	
						else if(Technology[k].equalsIgnoreCase("Overture")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Overture' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox"
									+ "Device Name text Field"
									+ "Protected checkbox"
									+  " should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);

							//VLAN Text field
						boolean Vlan=false;	
							try {
						Vlan=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).isDisplayed();
						sa.assertTrue(Vlan, " 'VLAN' text field is not available");
						if(Vlan) {
							DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN' field is displaying as expected, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' field is not displaying, when 'Technology' is selected as 'Overture' and 'IV Reference' selected as 'Access'");
						}
							}catch(Exception e) {
								e.printStackTrace();
								DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' text field is not Available under 'Add Site order' page");
							}
						
						
					//VLAN Ether Type
							boolean VlanetherType=false;
							try {
						VlanetherType = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")).isDisplayed();
						sa.assertTrue(VlanetherType, " 'VLAN Ether Type' dropdown is not Available");
						if(VlanetherType) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'VLAN Ether Type' drodpown field is displaying under 'Add Site Order' page as expected");
							
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
						List<WebElement> listofVLANethertype = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

						if(listofVLANethertype.size()>=1) {
						for (WebElement VLANEthertypes : listofVLANethertype) {
									Log.info("list of 'VLAN Ether Type' are : " + VLANEthertypes.getText());
									DriverTestcase.logger.log(LogStatus.PASS,"The list of 'VLAN Ether Type' inside dropdown is: "+VLANEthertypes.getText());
								
						}
					}else {
						System.out.println("no values are available inside 'VLAN Ether Type' dropdown for Add site order");
						DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'VLAN Ether Type' dropdown for Add site order");
				}
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not available under 'Add Site order' page");
					}	
							}catch(Exception e) {
								e.printStackTrace();
								DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether type' dropdown is not Available under 'Add Site order' page");
							}
				}		

							
						
					//Alu	
						else if(Technology[k].equals("Alu")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Alu' is selected under Technology"
									+ " Device Name Text Field should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
					
						//Device Name	
								verifySiteOrderField_deviceName(application);
					
						}	
						
						
				//Accedian		
						else if((Technology[k].equalsIgnoreCase("Accedian-1G"))  ||  (Technology[k].equalsIgnoreCase("Accedian"))) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
									+ " Non Termination point checkbox"
									+ " Protected checkbox"
									+ " Device Name text Field should display");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);

							
						//Protected checkbox	
							verifySiteOrderFields_protected(application);
							

						//Device Name	
								verifySiteOrderField_deviceName(application);
						}	
						
				
				//Cyan
						else if(Technology[k].equalsIgnoreCase("Cyan")) {
							
							DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Cyan' is selected under Technology"
									+ "list of fields should occur: "
									+ "Non Termination point checkbox");
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
							Clickon(getwebelement("//div[text()='" + Technology[k] + "']"));
							
						//Non Termination Point	
							verifySiteOrderFields_NonterminationField(application);
						}
			}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
}


public void technologyDropdownFor10GigE_EPN(String application) throws InterruptedException, DocumentException {
	
	String[] Technology = {"Accedian"};

	boolean technology, Nonterminationpointcheckbox, portectedcheckbox;
	
	// Technology dropdown
			technology = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology"))
					.isDisplayed();
			sa.assertTrue(technology, "Technology dropdown is not displayed");

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			List<WebElement> listoftechnology = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listoftechnology.size()>=1) {	
			for (WebElement technologytypes : listoftechnology) {

				boolean match = false;
				for (int i = 0; i < Technology.length; i++) {
					if (technologytypes.getText().equals(Technology[i])) {
						match = true;
						Log.info("list of technology are : " + technologytypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of technology  inside dropdown while  adding site order is: "+technologytypes.getText());
					}
				}
				sa.assertTrue(match);
			}
		}else {
			
			System.out.println("no values are available inside technology dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside technology dropdown for Add site order");
		}
		
	DriverTestcase.logger.log(LogStatus.INFO, "when technology 'Accedian' is selected under Technology"
					+ "list of fields should occur: "
					+ "Non Termination point checkbox"
					+ "Protected checkbox");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
			Clickon(getwebelement("//div[text()='" + Technology + "']"));
			
			
		//Non Termination Point	
			try {
			Nonterminationpointcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isDisplayed();
			sa.assertTrue(Nonterminationpointcheckbox, "On selecting 'Accedian' under Technology, Non termination point checkbox is not available");
			if(Nonterminationpointcheckbox) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Non Termination Point' checkbox is displayed under 'Add Site order' page as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			}
			
			boolean nonTerminaionpointselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
			sa.assertFalse(nonTerminaionpointselection,"Non-termination point checbox under Add site is selected by default");
			if(nonTerminaionpointselection) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Non-Termination Point' checkbox is not selected by default as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Non-Termination Point' checkbox is selected by default");
			}
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Non Termination Point' checkbox is not Available under 'Add Site order' page");
			}

			
		//Protected checkbox
			try {
			portectedcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isDisplayed();
			sa.assertTrue(portectedcheckbox, "On selecting 'Accedian' under Technology, protected checkbox is not available");
			if(portectedcheckbox) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Protected' checkbox is displayed under 'Add Site order' page as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Protected' checkbox is not available under 'Add Site order' page");
			}
			boolean protectedSelection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
			sa.assertFalse(protectedSelection,"Protected checbox under Add site is selected by default");
			if(protectedSelection) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Protected' checkbox is not selected by default as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Protected' checkbox is selected by default");
			}
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Protected' checkbox is not Available under 'Add Site order' page");
			}

		}
	
	public void selectRowForsiteorder(String Application, String siteordernumber, String siteOrdernumber_P2P, String topology)
			throws InterruptedException, DocumentException, IOException {

		
		System.out.println("-----------------------------" + siteordernumber + "---------------------");
		int TotalPages;
 
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
			
		//div[div[contains(text(),'" + siteOrdernumber_P2P + "')]]//span[@class='ag-icon ag-icon-checkbox-unchecked']  --> needs to be updated
			List<WebElement> results = null;
			
		if(topology.equalsIgnoreCase("Point-to-Point")) {
			results = getwebelements("//div[contains(text(),'"+ siteOrdernumber_P2P +"')]");
		}else {
			results = getwebelements("//div[contains(text(),'"+ siteordernumber +"')]");
		}
		
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
							Clickon(getwebelement(
									xml.getlocator("//locators/" + Application + "/Actiondropdown_siteorder")));

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

		sa.assertAll();

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
	public void verifyFieldsandAddCPEdevicefortheserviceselected_1G(String application,String interfaceSpeed,  String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String mediaSelection, String Macaddress,String serialNumber, 
			String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue, String technologySelected)
			throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(3000);
		
		DriverTestcase.logger.log(LogStatus.INFO, "Verifying fields for CPE device under Equipment");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(6000);
		
		if(technologySelected.equalsIgnoreCase("Accedian-1G")) {
			equip_adddevi_Accedian1G(application, interfaceSpeed, cpename, vender, snmpro, managementAddress, Mepid, poweralarm, mediaSelection, 
				Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, newmanagementAddress, existingmanagementAddress,
				manageaddressdropdownvalue);
			
			
		}else {
			
		equip_addDevice_1G(application, interfaceSpeed, cpename, vender, snmpro, managementAddress, Mepid, poweralarm, mediaSelection, 
				Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, newmanagementAddress, existingmanagementAddress,
				manageaddressdropdownvalue);
		}
		
		}
	
	
	public void equip_adddevi_Accedian1G(String application,String interfaceSpeed,  String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String mediaSelection, String Macaddress,String serialNumber, 
			String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress,
			String manageaddressdropdownvalue) throws InterruptedException, DocumentException, IOException {
		
		
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(3000);
		
		try {
			
		String linklostForwardingcheckboxstate="disabled"; 
			
		String[] Vender= {"Accedian-1G 1GigE-MetroNID-GT", "Accedian-1G 1GigE-MetroNID-GT-S", "Accedian-1G GX"};
		
		String[] powerAlarm= {"DC Single Power Supply - Feed A", "DC Dual Power Supply - Feed-A+B", "AC Single Power Supply - Feed A", "AC Dual Power Supply -Feed A+B"};
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>.lanlink.dcn.colt.net";
		
		String expectedValueForSnmpro= "JdhquA5";
		
		
		
			// name field Error Message
			device_nameFieldWarningMessage(application);

			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			//serial number Eror Message
			device_serialNumberWarningMessage(application);
			
			//Hexa serial Number
			device_hexaSerialNumberWarningMessage(application);
	
			//Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
			
			//Vendor/Model
			device_vendorModel(application, Vender, vender);      
		
			//Snmpro
			device_snmPro(application, snmpro);
			
			//Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
			
			//MEP Id
			device_mepID(application, Mepid);
		
			//Power Alarm	
			device_powerAlarm(application, powerAlarm, poweralarm);
			
			//Serial Number
			device_serialNumber(application,serialNumber);
		
		    //Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
		    
		scrolltoend();
		Thread.sleep(3000);
			//OK button
			device_okbutton(application);
			
			//cancel button
			device_cancelButton(application);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")));
			Thread.sleep(3000);
		    
		sa.assertAll();
		
		}catch(AssertionError e) {
			
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}

	}
	
	public void equip_addDevice_1G(String application,String interfaceSpeed,  String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String mediaSelection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue) throws InterruptedException, DocumentException {
		
		try {
			
		String linklostForwardingcheckboxstate="enabled"; 
		
		String[] Vender= {"Overture ISG-26", "Overture ISG-26R", "Overture ISG-26S", "Overture ISG140", "Overture ISG180", "Overture ISG6000"};
		
		String[] powerAlarm= {"AC", "DC"};
		
		String[] Mediaselection= {"SFP-A with SFP-B","RJ45-A with SFP-B"};	
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>.lanlink.dcn.colt.net";
		
		String mepValue="null";
		
		
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(5000);
		
		//name field Error Message
		device_nameFieldWarningMessage(application);
		
			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);
			
		//Media Selection Error Message
		device_mediaSelectionWarningMessage(application);
			
			
		//MAC Address Error Message
		device_macAddressWarningMessage(application);
			

		//Name
		device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
		
		//Vendor/Model
		device_vendorModel(application, Vender, vender);      
	
		//Snmpro
		device_snmPro(application, snmpro);
		
		//Management Address dropdown
		device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
		
		//MEP Id
		device_mepID(application, Mepid);
	
		//Power Alarm	
		device_powerAlarm(application, powerAlarm, poweralarm);
	
	scrolltoend();
	Thread.sleep(3000);
	
		//Media Selection
		device_mediaSelection(application, Mediaselection, mediaSelection);
		
		//MAC Address
		device_MAcaddress(application, Macaddress);
	    
	    //Link lost Forwarding
		device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
	    
		scrolltoend();
		Thread.sleep(3000);
	    
	    //OK button
		device_okbutton(application);
		
		//cancel button
		device_cancelButton(application);
			
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")));
		Thread.sleep(3000);
		
		sa.assertAll();
		
		}catch(AssertionError e) {
			
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}
		
		
	}
	
	public void verifyFieldsandAddCPEdevicefortheserviceselected_10G(String application,String interfaceSpeed,  String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String mediaSelection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException, IOException {
		
		
		DriverTestcase.logger.log(LogStatus.INFO, "Verifying fields for CPE device under Equipment");
	scrolltoend();
	Thread.sleep(3000);
	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(9000);
		
		
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(3000);
		
		try {
			
		String linklostForwardingcheckboxstate="disabled"; 
			
		String[] Vender= {"Accedian 10GigE-MetroNode-CE-2Port"};
		
		String[] powerAlarm= {"DC Single Power Supply - PSU A", "DC Dual Power Supply - PSU-A+B"};
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>-10G.lanlink.dcn.colt.net";
		
		String MEPid="5555";
		
		String expectedValueForSnmpro= "JdhquA5";
		
		
		
			// name field Error Message
			device_nameFieldWarningMessage(application);

			// Vendor/Model Error Message
			device_vendorModelWarningMessage(application);

			// Management Address Error Message
			device_managementAddressWarningMessage(application);

			// Power Alarm Error Message
			device_powerAlarmWarningMessage(application);

			//serial number Eror Message
			device_serialNumberWarningMessage(application);
			
			//Hexa serial Number
			device_hexaSerialNumberWarningMessage(application);
	
			//Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
			
			//Vendor/Model
			device_vendorModel(application, Vender, vender);      
		
			//Snmpro
			device_snmPro(application, snmpro);
			
			//Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
			
			//MEP Id
			device_mepID(application, Mepid);
		
			//Power Alarm	
			device_powerAlarm(application, powerAlarm, poweralarm);
			
			//Serial Number
			device_serialNumber(application,serialNumber);
		
		    //Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
		    
		scrolltoend();
		Thread.sleep(3000);
			//OK button
			device_okbutton(application);
			
			//cancel button
			device_cancelButton(application);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")));
			Thread.sleep(3000);
		    
		sa.assertAll();
		
		}catch(AssertionError e) {
			
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}
		
	
			 
		}

	
	
	public void AddCPEdevicefortheserviceselected(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException, IOException {

		DriverTestcase.logger.log(LogStatus.INFO, "Adding device for equipment");
		
		Thread.sleep(5000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		
		Thread.sleep(3000);

		Log.info("Adding details to the fields to create a CPE device");

	

	//vender/model	
		try {
			if(vender.equalsIgnoreCase("null")) {
				
				DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device under Equipment");
			}
		
			else {	
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "add cpe device  vender/model dropdown field not avaialble");
		}
		
		
		try {
			if(vender.equalsIgnoreCase("null")) {
				
				System.out.println("No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device under Equipment");
				
			}
		
			else {	
		Clickon(getwebelement("//div[label[text()='Vender/Model']]//div[text()='"+vender +"']"));
		DriverTestcase.logger.log(LogStatus.PASS, vender + " is the value passed for Mandatory 'Vendor/Model' dropdown for adding device under Equipment");
			}
			}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "FAilure at Vender/model dropdown. It does not have the value provided as input");
		}
	
	
	//snmpro	
		try {
		if(snmpro.equalsIgnoreCase("null"))	{
			
			DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory field 'snmpro' for adding device under Equipment");
			
		}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);
			DriverTestcase.logger.log(LogStatus.PASS, snmpro + " is the value passed for Mandatory 'Snmpro' field for adding device under Equipment");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Snm pro' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside the 'Snm pro' field");
		}

		
//Manage address text field	
		
	if(newmanagementAddress.equalsIgnoreCase("yes") && existingmanagementAddress.equalsIgnoreCase("no")) {	
		try {
			if(managementAddress.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory field 'Manage Address' for adding device under Equipment");
			}else {
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);
		DriverTestcase.logger.log(LogStatus.PASS, managementAddress + " is the value passed for Mandatory 'Management Address' field for adding device under Equipment");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Manage Address' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside the 'Manage Address' field");
		}
	}
//Manage address dropdown
	else if(newmanagementAddress.equalsIgnoreCase("no") && existingmanagementAddress.equalsIgnoreCase("yes")) {	
		try {
		if(manageaddressdropdownvalue.equalsIgnoreCase("null")) {
			DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory field 'Manage Address' dropdownfor adding device under Equipment");
		}else {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_selectSubnettogglebutton")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")));
			Thread.sleep(3000);
		}

		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "add cpe device  'Management Address' dropdown field not avaialble");
		}
		
			
			
		try {
			if(manageaddressdropdownvalue.equalsIgnoreCase("null")) {
					
				System.out.println("No values has been passed for Mandatory 'Management Address' dropdown for adding device under Equipment");
					
			}else {	
			Clickon(getwebelement("//div[label[text()='Management Address']]//div[text()='"+ manageaddressdropdownvalue+" ']]"));
			DriverTestcase.logger.log(LogStatus.PASS, manageaddressdropdownvalue + " is the value passed for Mandatory 'Management Address' dropdown for adding device under Equipment");
				}
				}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "FAilure at 'Management Address' dropdown. It does not have the value provided as input");
			}
	}

	//Mepid	
		try {
			
			if(Mepid.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.INFO, "No values has been passed for 'mepid' field for adding device under Equipment");
				
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);
				DriverTestcase.logger.log(LogStatus.PASS, Mepid + " is the value passed for 'Mepid' field for adding device under Equipment");
				
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Mep Id' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside the 'Mep Id' field");
		}
		
	
	//Power alarm	
		try {
			
			if(poweralarm.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory dropdown 'Power alarm' for adding device under Equipment");
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Power Alarm' dropdown is not available ");
		}
		
		
		try {
			
			if(poweralarm.equalsIgnoreCase("null")) {
				
				System.out.println("power alarm dropdown selected");
			}else {
				Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='"+poweralarm +"']"));
				DriverTestcase.logger.log(LogStatus.PASS, poweralarm + " is the value passed for Mandatory 'Power Alarm' dropdown field for adding device under Equipment");
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "FAilure at power alarm dropdown. It does not have the value provided as input ");
		}
		

	//Media selection	
		try {
			
			if(Mediaselection.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for 'Media Selection' mandatory field for adding device under Equipment");
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Media selection' dropdown is not available ");
		}
		
		try {
			
			if(Mediaselection.equalsIgnoreCase("null")) {
				System.out.println("Media selection dropdown selected");
				
			}else {
				Clickon(getwebelement("//div[label[text()='Media Selection']]//div[text()='"+Mediaselection +"']"));
				DriverTestcase.logger.log(LogStatus.PASS, Mediaselection + " is the value passed for Mandatory 'Media Selection' field for adding device under Equipment");
			}
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "FAilure at Media selection dropdown. It does not have the value provided as input");
		}
				

	//Mac address	
		try {
			
			if(Macaddress.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, " No values has been passed for 'mac address' mandatory field for adding device under Equipment");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);
				DriverTestcase.logger.log(LogStatus.PASS, Macaddress + " is the value passed for 'Macaddress' field for adding device under Equipment");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Mac Address' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside the 'Mac Address' field");
		}

		
		
		
	//Serial number	
		try {
			
			if(serialNumber.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.INFO, " No values has been passed for 'Serial number' field for adding device under Equipment");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")), serialNumber);
				DriverTestcase.logger.log(LogStatus.PASS,serialNumber + " is the value passed for Mandatory 'Serial Number' field for adding device under Equipment");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Serial Number' field is not available");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside the 'Serial Number' field");
		}
		
//
//		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaserialnumber")),
//				hexaSerialnumber);

		if (linkLostForwarding.equalsIgnoreCase("yes")) {
		
			try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
			DriverTestcase.logger.log(LogStatus.PASS, "Link lost forwarding checkbox is selected for adding device under Equipment");
			}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "Link  lost forwarding checkbox is not avaialble");
			}
			
		} else {
			
			System.out.println("link lost forwarding is not selected");
			DriverTestcase.logger.log(LogStatus.PASS, "Link lost forwarding checkbox is not selected for adding device under Equipment");
			
			
		}

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

		Thread.sleep(3000);

	}

	public void verifydetailsEnteredforCPEdevice_1G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding,
			String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue, String technologySelected)
			throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger.log(LogStatus.INFO, "verify the details entered for creating device");
	
		String[] RouterId=new String[2];
		RouterId=cpename.split(".lanlink");
		
		String RouterIdValue=RouterId[0];
		
		
		String mediaSelectionValueInViewDevicePage="no";
		if(Mediaselection.equalsIgnoreCase("null")) {
			Mediaselection=mediaSelectionValueInViewDevicePage;
		}else {
			Mediaselection=mediaSelectionValueInViewDevicePage;
		}
	  
	  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
	  
	  verifyEnteredvalues("Router Id", RouterIdValue);
	  
	  verifyEnteredvalues("Vendor/Model", vender);
	  
	  verifyEnteredvalues("Snmpro", snmpro);
	  
	  
		//Management Address  
		  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) || (newmanagementAddress.equalsIgnoreCase("no"))) {
			  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
			 }
			 else if((existingmanagementAddress.equalsIgnoreCase("no")) || (newmanagementAddress.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("Management Address", managementAddress);
			 } 
	  
//	  verifyEnteredvalues("MEP Id", Mepid);
	  
	  verifyEnteredvalues("Power Alarm", poweralarm);
	  
	if(technologySelected.equalsIgnoreCase("Accedian-1G")) {  
		
	  verifyEnteredvalues("Serial Number", serialNumber);
	  
	}else {
	  
	  verifyEnteredvalues("Media Selection", Mediaselection);
	  
	  verifyEnteredvalues("MAC Address", Macaddress);
	}
	  
	  
  

	}
	
	
	public void verifydetailsEnteredforCPEdevice_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String newmanagementAddress, 
			String existingmanagementAddress, String manageaddressdropdownvalue)
			throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger.log(LogStatus.INFO, "verify the details entered for creating device");
		
	//Splitting device name	
		String[] RouterId=new String[2];
		RouterId=cpename.split(".lanlink");
		
		String RouterIdValue=RouterId[0];
		
		Mediaselection="no";
		
	  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
		  
	  verifyEnteredvalues("Router Id", RouterIdValue);
	  
	  verifyEnteredvalues("Vendor/Model", vender);
	  
	  verifyEnteredvalues("Snmpro", snmpro);
	  
	//Management Address  
	  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) || (newmanagementAddress.equalsIgnoreCase("no"))) {
		  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		 }
		 else if((existingmanagementAddress.equalsIgnoreCase("no")) || (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Management Address", managementAddress);
		 } 
	  
	  verifyEnteredvalues("Power Alarm", poweralarm);
	  
	  verifyEnteredvalues("Media Selection", Mediaselection);
	  
	  verifyEnteredvalues("Link Lost Forwarding", "yes");
	  
	  verifyEnteredvalues("Serial Number", serialNumber);
	  
  

	}

	public void eDITCPEdevicedetailsentered_1G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding,String createddevicename, String technologySelected)
			throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger.log(LogStatus.INFO, "edit CPE device under Equipment");
		
		System.out.println("Entered edit functionalitty");

		scrollToTop();
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");
		
	     
	     
	//Name field
		device_editnamefield(application, cpename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
		scrolltoend();
		Thread.sleep(3000);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
	
	if(technologySelected.equalsIgnoreCase("Accedian-1G")) {
		
		//Serial Number
				device_editserialnumber(application, serialNumber);
				
	}else {
		
		//Media Selection   
				device_editMediaselection(application, Mediaselection);
			    
				Thread.sleep(3000);
			    
			//Mac address  
				device_editMACaddress(application, Macaddress);
			    
			//linklost forwarding	
				device_editlinkLostforwarding(application, linkLostForwarding);
	}
	
	    
scrolltoend();
Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(2000);
		
	}
	
	
	public void eDITCPEdevicedetailsentered_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding,String devicename)
			throws InterruptedException, DocumentException, IOException {
		

		
		DriverTestcase.logger.log(LogStatus.INFO, "edit CPE device under Equipment");
		
		System.out.println("Entered edit functionalitty");

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");
	     
	     
	//Name field
		device_editnamefield(application, cpename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
		
	//serial Number
		device_editserialnumber(application, serialNumber);
		
	//linklost forwarding	
		device_editlinklostforwarding_10G(application);
	    
	    
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(2000);
		
	}
	
	
	public void eDITCPEdevicedetailsentered_1G_Overture(String application, String cpedevicename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country, String City,
			String Site, String Premise)
			throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger.log(LogStatus.INFO, "edit CPE device");
		
		System.out.println("Entered edit functionalitty");

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");
		
	     
	//Name field
		device_editnamefield(application, cpedevicename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
		
		WebElement countrylabelname=getwebelement(xml.getlocator("//locators/" + application + "/countrylabelname_IntEquipment"));
		ScrolltoElement(countrylabelname);
	Thread.sleep(3000);
	
	//Media Selection  
		device_editMediaselection(application, Mediaselection);
	    
	    
	//Mac address  
		device_editMACaddress(application, Macaddress);
	    
	//linklost forwarding	
		device_editlinkLostforwarding(application, linkLostForwarding);
	
		scrolltoend();
		Thread.sleep(3000);
	    
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(2000);
		
	}
	
	
	public void eDITCPEdevicedetailsentered_1G_Accedian(String application, String cpedevicename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country, String City,
			String Site, String Premise)
			throws InterruptedException, DocumentException, IOException {
		
		DriverTestcase.logger.log(LogStatus.INFO, "edit CPE device");
		
		System.out.println("Entered edit functionalitty");

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");
		
	     
	//Name field
		device_editnamefield(application, cpedevicename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
	
	//Serial Number
		device_editserialnumber(application, serialNumber);
	    
	    
	//linklost forwarding	
//		device_editlinkLostforwarding(application, linkLostForwarding);
	    
	
		scrolltoend();
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(2000);
		
	}
	
	
	
	public void AddCPEdevicefortheLAnlinkNationalservice(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding)
			throws InterruptedException, DocumentException, IOException {

		
		Thread.sleep(5000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_adddevicelink")));
		Thread.sleep(3000);

		Log.info("Adding details to the fields to create a CPE device");

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
		Clickon(getwebelement("//div[label[text()='Vender/Model']]//div[text()='"+vender +"']"));
	
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmpro);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
				managementAddress);

		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
	    Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='"+poweralarm +"']"));
		

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
		Clickon(getwebelement("//div[label[text()='Media Selection']]//div[text()='"+Mediaselection +"']"));
				

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

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

		Thread.sleep(3000);

	}
	
	
	public void addDevice_IntEquipment(String application) throws InterruptedException, DocumentException {
		
		//click on Add device link	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_adddevicelink")));
		Thread.sleep(3000);
	}

	public void selectTechnology(String application, String technologyToBeSelected) throws InterruptedException, DocumentException {
		
				
			//verify Technology popup
				boolean technologypopup=false;
				technologypopup=getwebelement(xml.getlocator("//locators/" + application + "/technologyPopup")).isDisplayed();
				if(technologypopup) {
					System.out.println("Technology popup is displaying as expected");
					DriverTestcase.logger.log(LogStatus.PASS, " Technology popup is displaying as expected");
				}else {
					System.out.println("Technology popup is not displaying");
					DriverTestcase.logger.log(LogStatus.FAIL, " Technology popup is notdisplaying");
				}
				
			//Dropdown values inside popup
				boolean technologyDropdown=false;
				technologyDropdown=getwebelement(xml.getlocator("//locators/" + application + "/technologypopup_dropdown")).isDisplayed();
				if(technologyDropdown) {
					System.out.println("Technology dropdown is displaying as expected");
					DriverTestcase.logger.log(LogStatus.PASS, " Technology dropdown is displaying as expected");
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/technologypopup_dropdown")));
					Thread.sleep(3000);
					
					//verify list of values inside technology dropdown
					 List<WebElement> listofTechnololgy = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
						
						if(listofTechnololgy.size()>0) {
				
						for (WebElement technoloyTypes : listofTechnololgy) {
							DriverTestcase.logger.log(LogStatus.PASS, "List of values available under 'Technology' dropdown are: "+technoloyTypes.getText());
							System.out.println("List of values available under 'Technology' dropdown are: "+technoloyTypes.getText());
						}
					}
						
						
				  //Select the Technology
						Clickon(getwebelement("//span[contains(text(),'"+ technologyToBeSelected +"')]"));
						Thread.sleep(3000);
						String actualValue=getwebelement(xml.getlocator("//locators/" + application + "/tchnologyPopup_dropdownValues")).getText();
						DriverTestcase.logger.log(LogStatus.PASS, " 'Technology' selected is: "+actualValue);
						System.out.println( " 'Technology' selected is: "+actualValue);
						
				}else {
					System.out.println("Technology dropdown is not displaying");
					DriverTestcase.logger.log(LogStatus.FAIL, " Technology dropdown is notdisplaying");
				}
				
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateEquipment_OKbuttonforpopup")));
				Thread.sleep(3000);
				
	}
	public void verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_1G(String application, String cpename, String vender_Overture, String vender_Accedian, String snmpro,
			String managementAddress, String Mepid, String poweralarm_Overture, String powerAlarm_Accedian, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String City,
			String Site, String Premise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename,
			String premisecode, String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {

	
			
			if(technologySelectedfordevicecreation.equalsIgnoreCase("Overture")) {
				deviceCreatoin_Overture(application, cpename, vender_Overture, snmpro, managementAddress, Mepid, poweralarm_Overture, MediaselectionActualValue,
						Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, country, City, Site, Premise, newmanagementAddress, 
						existingmanagementAddress, manageaddressdropdownvalue, existingcityselectionmode, newcityselectionmode, cityname, 
						citycode, existingsiteselectionmode, newsiteselectionmode, sitename, sitecode, existingpremiseselectionmode, 
						newpremiseselectionmode, premisename, premisecode);
				
			}
			
			if((technologySelectedfordevicecreation.equalsIgnoreCase("Accedian")) || (technologySelectedfordevicecreation.equalsIgnoreCase("Accedian-1G"))) {
			deviceCreatoin_Accedian(application, cpename, vender_Accedian, snmpro, managementAddress, Mepid, powerAlarm_Accedian, MediaselectionActualValue,
					Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, country, City, Site, Premise, newmanagementAddress, 
					existingmanagementAddress, manageaddressdropdownvalue, existingcityselectionmode, newcityselectionmode, cityname, 
					citycode, existingsiteselectionmode, newsiteselectionmode, sitename, sitecode, existingpremiseselectionmode, 
					newpremiseselectionmode, premisename, premisecode);
		}
			
	}
	
	
	public void verifyFieldsandAddCPEdevicefortheserviceselected_IntEquip_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String City,
			String Site, String Premise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode, String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {

		
		DriverTestcase.logger.log(LogStatus.INFO, "Verifying fields for CPE device under Intermediate Equipment");
		
		try {
			
		String linklostForwardingcheckboxstate="disabled"; 
			
		String[] Vender= {"Accedian 10GigE-MetroNode-CE-2Port", "Accedian 10GigE-MetroNode-CE-4Port"};
		
		String[] powerAlarm= {"DC Single Power Supply - PSU A", "DC Dual Power Supply - PSU-A+B"};
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>-10G.lanlink.dcn.colt.net";
		
		String MEPid="5555";
		
		String expectedValueForSnmpro= "JdhquA5";
		
		
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

			//serial number Eror Message
			device_serialNumberWarningMessage(application);
			
			//Hexa serial Number
			device_hexaSerialNumberWarningMessage(application);
	
			//Name
			device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
			
			//Vendor/Model
			device_vendorModel(application, Vender, vender);      
		
			//Snmpro
			device_snmPro(application, snmpro);
			
			//Management Address dropdown
			device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
			
			//MEP Id
			device_mepID(application, Mepid);
		
			//Power Alarm	
			device_powerAlarm(application, powerAlarm, poweralarm);
			
			//Serial Number
			device_serialNumber(application,serialNumber);
		
		    //Link lost Forwarding
			device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
			
			//Country
			device_country(application, country);
		
			//verify fields for City, Site and premise
			device_verifycity(application);

			//City		
			if(existingcityselectionmode.equalsIgnoreCase("no") & newcityselectionmode.equalsIgnoreCase("yes")) {
				addCityToggleButton(application);
			   //New City	
				newcity(application, newcityselectionmode, cityname, citycode);
			      // New Site
				newSite(application, newsiteselectionmode, sitename, sitecode);
				  //New Premise	
				newPremise(application, newpremiseselectionmode, premisename, premisecode);
					
			}	
				
			else if(existingcityselectionmode.equalsIgnoreCase("yes") & newcityselectionmode.equalsIgnoreCase("no")) {
			//Existing City		
			   existingCity(application, existingcityselectionmode, City);
				
				//Site
			 
				  if(existingsiteselectionmode.equalsIgnoreCase("yes") & newsiteselectionmode.equalsIgnoreCase("no")) {
					//Existing Site 
					  existingSite(application, existingsiteselectionmode, Site);
					  
					   //Premise
					  if(existingpremiseselectionmode.equalsIgnoreCase("yes") & newpremiseselectionmode.equalsIgnoreCase("no")) {
						  existingPremise(application, existingpremiseselectionmode, Premise);
					  
			          	 }
					  else if(existingpremiseselectionmode.equalsIgnoreCase("no") & newpremiseselectionmode.equalsIgnoreCase("yes")) {
						  //New Premise
						    addPremiseTogglebutton(application);
						    newPremise_clickOnPremisetoggleButton(application, newpremiseselectionmode, premisename, premisecode);
					  } 
		         	}
	  		
			  else if(existingsiteselectionmode.equalsIgnoreCase("no") & newsiteselectionmode.equalsIgnoreCase("yes")) {
				  	//New Site 
				  	addSiteToggleButton(application);
				  	newSite_ClickOnSiteTogglebutton(application, newsiteselectionmode, sitename, sitecode); 
				  	
				  	//New Premise
				  	newPremise_clickonSiteToggleButton(application, newpremiseselectionmode, premisename, premisecode);
				  }
			}
			
			
			//OK button
			device_okbutton(application);
			
			//cancel button
			device_cancelButton(application);
		    
		sa.assertAll();
		
		}catch(AssertionError e) {
			
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")));
			 
			
	}

	
	
	public void verifyCPEdevicedataenteredForIntermediateEquipment_1G(String application, String cpename, String vender_Overture, String vender_Accedian, String snmpro,
			String managementAddress, String Mepid, String poweralarm_Overture, String powerAlarm_Accedian, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String existingCity,
			String existingSite, String existingPremise, String newmanagementAddressSelection, String existingmanagementAddressSelection, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename,
			String premisecode, String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {
		
	
	if(technologySelectedfordevicecreation.equalsIgnoreCase("Overture")) {
		
		viewdevice_Overture(application, cpename, vender_Overture, snmpro, managementAddress, Mepid, poweralarm_Overture, MediaselectionActualValue,
				Macaddress,linkLostForwarding, country, existingCity, cityname, existingSite, sitename, existingPremise, premisename,existingcityselectionmode,
				newcityselectionmode, existingsiteselectionmode, newsiteselectionmode, newmanagementAddressSelection, existingmanagementAddressSelection,
				manageaddressdropdownvalue, existingpremiseselectionmode, newpremiseselectionmode);
		
	}
	
	
	if((technologySelectedfordevicecreation.equalsIgnoreCase("Accedian")) || (technologySelectedfordevicecreation.equalsIgnoreCase("Accedian-1G"))) {
		
		viewdevice_Accedian(application, cpename, vender_Accedian, snmpro, managementAddress, Mepid, powerAlarm_Accedian, MediaselectionActualValue,
				Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding,country, existingCity, cityname, existingSite, sitename, existingPremise, premisename,existingcityselectionmode,
				newcityselectionmode, existingsiteselectionmode, newsiteselectionmode, newmanagementAddressSelection, existingmanagementAddressSelection,
				manageaddressdropdownvalue, existingpremiseselectionmode, newpremiseselectionmode);
	}
		

	
	}
	
	
	public void verifyCPEdevicedataenteredForIntermediateEquipment_10G(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String existingcountry, String existingCity,
			String existingSite, String existingPremise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode, String technologySelectedfordevicecreation) throws InterruptedException, DocumentException, IOException {
		
		

		
		DriverTestcase.logger.log(LogStatus.INFO, "verify the details entered while creating device");
		
		String[] RouterId=new String[2];
		RouterId=cpename.split(".lanlink");
		
		String RouterIdValue=RouterId[0];
		
		
		String mediaSelectionValueInViewDevicePage="no";
		if(MediaselectionActualValue.equalsIgnoreCase("null")) {
			MediaselectionActualValue=mediaSelectionValueInViewDevicePage;
		}else {
			MediaselectionActualValue=mediaSelectionValueInViewDevicePage;
		}
	  
	  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
	  
	  verifyEnteredvalues("Router Id", RouterIdValue);
	  
	  verifyEnteredvalues("Vendor/Model", vender);
	  
	  verifyEnteredvalues("Snmpro", snmpro);
	  
	//Management Address  
	  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) || (newmanagementAddress.equalsIgnoreCase("no"))) {
		  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		 }
		 else if((existingmanagementAddress.equalsIgnoreCase("no")) || (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Management Address", managementAddress);
		 } 
	  
	  
	  verifyEnteredvalues("Power Alarm", poweralarm);
	  
	  verifyEnteredvalues("Media Selection", MediaselectionActualValue);
	  
	  verifyEnteredvalues("Serial Number", serialNumber);
	  
	  verifyEnteredvalues("Link Lost Forwarding", linkLostForwarding);
	  
	  verifyEnteredvalues("Country", existingcountry);
	  
	//City  
		 if((existingcityselectionmode.equalsIgnoreCase("Yes")) || (newcityselectionmode.equalsIgnoreCase("no"))) {
			 verifyEnteredvalues("City", existingCity);
		 }
		 else if((existingcityselectionmode.equalsIgnoreCase("no")) || (newcityselectionmode.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("City", cityname);
		 } 
		 
		 
		//Site
		 if((existingsiteselectionmode.equalsIgnoreCase("Yes")) || (newsiteselectionmode.equalsIgnoreCase("no"))) {
			 verifyEnteredvalues("Site", existingSite);
		 }
		 else if((existingsiteselectionmode.equalsIgnoreCase("no")) || (newsiteselectionmode.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Site", sitename);
		 } 
		 
		 
		//Premise
		 if((existingpremiseselectionmode.equalsIgnoreCase("Yes")) || (newpremiseselectionmode.equalsIgnoreCase("no"))) {
			 verifyEnteredvalues("Site", existingPremise);
		 }
		 else if((existingpremiseselectionmode.equalsIgnoreCase("no")) || (newpremiseselectionmode.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Site", premisename);
		 } 

	}

	public void EDITCPEdevicedforIntermediateEquipment_1G(String application, String Cpename , String vender_1G_Overture,String vender_1G_Accedian,
			String vender_10G_Accedian,String snmpro,String managementAddress, String Mepid, String poweralarm_Overture, String poweralarm_Accedian,String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country, String City,
			String Site, String Premise, String technologySelected) throws InterruptedException, DocumentException, IOException {
		
		scrollToTop();
		Thread.sleep(3000);
		
		if(technologySelected.equalsIgnoreCase("Overture")) {
			
			eDITCPEdevicedetailsentered_1G_Overture(application, Cpename, vender_1G_Overture, snmpro, managementAddress, Mepid, poweralarm_Overture, 
					Mediaselection, Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, Country, City,Site, Premise);
		}
		else if((technologySelected.equalsIgnoreCase("Accedian")) || (technologySelected.equalsIgnoreCase("Accedian-1G"))) {
			
			eDITCPEdevicedetailsentered_1G_Accedian(application, Cpename, vender_1G_Accedian, snmpro, managementAddress, Mepid, poweralarm_Accedian,
					Mediaselection, Macaddress, serialNumber, hexaSerialnumber, linkLostForwarding, Country, City,Site, Premise);
		}
		
	}
	
	
	public void EDITCPEdevice_IntermediateEquipment_10G(String application, String Cpename, String vender_10G_Accedian,String snmpro,String managementAddress,
			String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country, String City,
			String Site, String Premise, String technologySelected) throws InterruptedException, DocumentException, IOException {
		

		scrollToTop();
		Thread.sleep(3000);
		
		DriverTestcase.logger.log(LogStatus.INFO, "edit CPE device");
		
		System.out.println("Entered edit functionalitty");

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");
		
	     
	//Name field
		device_editnamefield(application, Cpename);
		
	//vendor/model
		device_editVendorModelField(application, vender_10G_Accedian);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
	
	//Serial Number
		device_editserialnumber(application, serialNumber);
	    
	    
	//linklost forwarding	
		device_editlinkLostforwarding(application, linkLostForwarding);
	    
	    
		scrolltoend();
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(2000);
		
	
	}
	
	
	public void EDITCPEdevicedforIntEquipment_1G_Overture(String application, String Cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String Mediaselection, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String Country, String City,
			String Site, String Premise, String technologySelected) throws InterruptedException, DocumentException, IOException {
		

		
		DriverTestcase.logger.log(LogStatus.INFO, "edit CPE device under Equipment");
		
		System.out.println("Entered edit functionalitty");

		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));
		
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevicelinkunderviewpage")));
		Thread.sleep(3000);
		System.out.println("edit functionality worked");
		
//		Clickon(getwebelement("(//div[div[div[text()='Equipment']]]//div[div[text()='"+ devicename+"']]/div/a/span[text()='Edit'])[2]"));

//		  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevlielink_underEquipment")));

	     
	     
	//Name field
		device_editnamefield(application, Cpename);
		
	//vendor/model
		device_editVendorModelField(application, vender);
		
	//Snmpro
		device_editSnmproField(application);

	//Management address
		device_editManagementAddressField(application, managementAddress);
		
	//Mepid	
		device_editMEPIdField(application, Mepid);
		
	//power alarm	
		device_editPowerAlarm(application, poweralarm);
		
	//Media Selection   
		device_editMediaselection(application, Mediaselection);
	    
	    
	//Mac address  
		device_editMACaddress(application, Macaddress);
	    
	//linklost forwarding	
		device_editlinkLostforwarding(application, linkLostForwarding);
	    
	    
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(2000);
		
	
	}
		
	
	public void deleteDeviceFromServiceForequipment(String application,String deleteDeviceSelection, String devicename) throws InterruptedException, DocumentException {
		
		
		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Delete from Service']"));
		System.out.println(" 'Delete From Service' link has been clicked for cpe device under Equipment");
		Log.info(" 'Delete From Service' link has been clicked for cpe device under Equipment");
		DriverTestcase.logger.log(LogStatus.PASS, " 'Delete From Service' link has been clicked for cpe device under Equipment");


		boolean deletemessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).isDisplayed();
		while(deletemessage) {
			Log.info("Are you sure want to delete - message is getting displayed on clicking DeletefromService link");
			Log.info("Delete popup message is getting displayed");
			String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Delete device popup is displaying and popup message displays as: "+ actualMessage);
			System.out.println( "Delete device popup is displaying and popup message displays as: "+ actualMessage);
			break;
		} 
		
		
		if(deleteDeviceSelection.equalsIgnoreCase("yes")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, devicename + " device has got deleted from service");
			Log.info("Device got deleted from service as expected");

		}else {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/xbuttonfordeletefromservicepopup_Equipment")));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, devicename + " device is not deleted from service as expected");
			Log.info("Device is not deleted from service as expected");

		}
	}
	

public void deleteDeviceFromService_EquipmentConfig_Actelis(String application,String deleteDeviceSelection, String devicename) throws InterruptedException, DocumentException {
		
		
		Clickon(getwebelement("//div[div[div[text()='Equipment Configuration']]]//div[div[text()='"+ devicename +"']]//span[text()='Delete from Service']"));
		
		
		System.out.println("SelectInterface link for Equipment is selected");
		Log.info("Select an inertface to add with the service under equipment");
		DriverTestcase.logger.log(LogStatus.PASS, " 'Delete From Service' link has been clicked for cpe device under Equipment");


		boolean deletemessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).isDisplayed();
		while(deletemessage) {
			Log.info("Are you sure want to delete - message is getting displayed on clicking DeletefromService link");
			Log.info("Delete popup message is getting displayed");
			String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Delete device popup is displaying and popup message displays as: "+ actualMessage);
			System.out.println( "Delete device popup is displaying and popup message displays as: "+ actualMessage);
			break;
		} 
		
		
		if(deleteDeviceSelection.equalsIgnoreCase("yes")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, devicename + " device has got deleted from service");
			Log.info("Device got deleted from service as expected");

		}else {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/xbuttonfordeletefromservicepopup_Equipment")));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, devicename + " device is not deleted from service as expected");
			Log.info("Device is not deleted from service as expected");

		}
	}


	
	public void successMessage_deleteFromService(String application) throws InterruptedException, DocumentException {
		
		boolean successMessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteDevice_successmEssage")).isDisplayed();
		String actualmessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteDevice_successmEssage")).getText();
		if(successMessage) {
			
			DriverTestcase.logger.log(LogStatus.PASS, " Success Message for device deletion is dipslaying as expected");
			System.out.println( " Success Message for device deletion is dipslaying as expected");
			
			DriverTestcase.logger.log(LogStatus.PASS, "Message displays as: "+actualmessage);
			System.out.println("Message displays as: "+actualmessage);
			
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, " Success Message for device deletion is not dipslaying");
			System.out.println( " Success Message for device deletion is not dipslaying");
		}
	}


	
public void deleteDeviceFromServiceForIntermediateequipment(String application, String deleteDeviceSelection, String devicename) throws InterruptedException, DocumentException {
	
	
	Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Delete from Service']"));
	System.out.println(" 'Delete From Service' link has been clicked for cpe device under Intermediate Equipment");
	Log.info(" 'Delete From Service' link has been clicked for cpe device under Intermediate Equipment");
	DriverTestcase.logger.log(LogStatus.PASS, " 'Delete From Service' link has been clicked for cpe device under Intermediate Equipment");


	boolean deletemessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).isDisplayed();
	while(deletemessage) {
		Log.info("Are you sure want to delete - message is getting displayed on clicking DeletefromService link");
		Log.info("Delete popup message is getting displayed");
		String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/deleteMessage_equipment")).getText();
		DriverTestcase.logger.log(LogStatus.PASS, "Delete device popup is displaying and popup message displays as: "+ actualMessage);
		System.out.println("Delete device popup is displaying and popup message displays as: "+ actualMessage);
		break;
	} 
	
	if(deleteDeviceSelection.equalsIgnoreCase("yes")) {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deletebutton_deletefromsrviceforequipment")));
		Thread.sleep(3000);
		DriverTestcase.logger.log(LogStatus.PASS, devicename + " device has got deleted from service");
		Log.info("Device got deleted from service as expected");

	}else {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/xbuttonfordeletefromservicepopup_Equipment")));
		Thread.sleep(3000);
		DriverTestcase.logger.log(LogStatus.PASS, devicename + " device is not deleted from service as expected");
		Log.info("Device is not deleted from service as expected");

	}
	
}


   public void VerifythefieldsforProviderEquipment(String application) throws InterruptedException, DocumentException {
	 
	try {
		   
	  boolean IMSlocation= getwebelement(xml.getlocator("//locators/" + application + "/AddPElink_LANlinkoutband")).isDisplayed();
	  sa.assertTrue(IMSlocation," IMS location dropdown is not displaying under Provider Equipment ");
	  
	  
	 boolean togglebutton= getwebelement(xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")).isDisplayed();
	 sa.assertTrue(togglebutton, "Toggle button is not disaplaying under Provider Equipment");
	  
	 boolean name= getwebelement(xml.getlocator("//locators/" + application + "/Name")).isDisplayed();
	 sa.assertTrue(name, "NAme field is not displaying under Provider Equipment");
	 
	 boolean vendor= getwebelement(xml.getlocator("//locators/" + application + "/VenderModel")).isDisplayed();
	 sa.assertTrue(vendor, "Vendor/Model dropdown ");
	 
	  
	 boolean address= getwebelement(xml.getlocator("//locators/" + application + "/managementaddress")).isDisplayed();
	 sa.assertTrue(address, "Management Address field ");
	  
	  boolean snmpro=getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")).isDisplayed();
	  sa.assertTrue(snmpro, "SNM pro field");
	  
	  boolean Country=getwebelement(xml.getlocator("//locators/" + application + "/country")).isDisplayed();
	  sa.assertTrue(Country, "Country dropdown");
	  
	  boolean city=getwebelement(xml.getlocator("//locators/" + application + "/city")).isDisplayed();
	  sa.assertTrue(city, "City dropdown");
	  
	  boolean Site=getwebelement(xml.getlocator("//locators/" + application + "/site")).isDisplayed();
	  sa.assertTrue(Site, "Sitedropdown");
	  
	  boolean Premise=getwebelement(xml.getlocator("//locators/" + application + "/premise")).isDisplayed();
	  sa.assertTrue(Premise, "Premise dropdown");
	  
	  boolean Nextbutton= getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")).isDisplayed();
	  sa.assertTrue(Nextbutton, "Next button");
	  
	  boolean cancelbutton= getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
	  sa.assertTrue(cancelbutton, "Cancel button");
	  
	  
	  sa.assertAll();
	   
	   }catch(AssertionError e) {
		   e.printStackTrace();
	   }
	   
   }

 	

	public void providerEquipment(String application, String IMSlocation,
			String selectOrclicktogglebutttontocreateDevice, String name, String VendorModel, String ManagementAddress,
			String Snmpro, String Country, String City, String Site, String Premise)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddPElink_LANlinkoutband")));

		System.out.println("----- Going to perform add PE device actions------------");

		if (selectOrclicktogglebutttontocreateDevice.equalsIgnoreCase("create")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Name")), name);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/VenderModel")), VendorModel);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddress")),
					ManagementAddress);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")), Snmpro);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/country")), Country);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/city")), City);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/site")), Site);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/premise")), Premise);
//					
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else {

			Select(getwebelement(xml.getlocator("//locators/" + application + "/SelectIMSlocation")), IMSlocation);

		}

	}

	public void CustomerPremiseEquipment(String application, String IMSlocation,
			String selectOrclicktogglebutttontocreateDevice, String name, String VendorModel, String ManagementAddress,
			String Snmpro, String Country, String City, String Site, String Premise)
			throws InterruptedException, DocumentException, IOException {

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPElink_LANlinkoutband")));

		System.out.println("----- Going to perform add PE device actions------------");

		if (selectOrclicktogglebutttontocreateDevice.equalsIgnoreCase("create")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Name")), name);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/VenderModel")), VendorModel);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddress")),
					ManagementAddress);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")), Snmpro);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/country")), Country);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/city")), City);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/site")), Site);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/premise")), Premise);
//					
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else {

			Select(getwebelement(xml.getlocator("//locators/" + application + "/SelectIMSlocation")), IMSlocation);

		}

	}

	public void verifyPEdeviceEnteredvalue(String application, String name, String VendorModel, String ManagementAddress,
			String Snmpro, String Country, String City, String Site, String Premise)
			throws IOException, InterruptedException, DocumentException {

		try {
		String nAME = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEname_lanlinkoutband")));
		sa.assertEquals(nAME, name, "provider equipment Name is displaying as expected");

		String vendor = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEvendor_lanlinkoutband")));
		sa.assertEquals(vendor, VendorModel, "VendorModel is displaying as expectd");

		String address = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEaddress_lanlinkoutband")));
		sa.assertEquals(address, ManagementAddress, "ManagementAddress is displaying as expectd");

		String snmpro = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEsnmpro_lanlinkoutband")));
		sa.assertEquals(snmpro, Snmpro, "Snmpro is displaying as expectd");

		String country = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEcountry_lanlinkoutband")));
		sa.assertEquals(country, Country, "Country is displaying as expectd");

		String city = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPECity_lanlinkoutband")));
		sa.assertEquals(city, City, "City is displaying as expectd");

		String sity = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPESite_lanlinkoutband")));
		sa.assertEquals(sity, Site, "Site is displaying as expectd");

		String premise = Gettext(
				getwebelement(xml.getlocator("//locators/" + application + "/verifyPEPremise_lanlinkoutband")));
		sa.assertEquals(premise, Premise, "Premise is displaying as expectd");
		
		sa.assertAll();
		
		}catch(AssertionError e) {
			e.printStackTrace();
		}

	}
	
	public void EditProviderEquipment(String application, String IMSlocation,
			String selectOrclicktogglebutttontocreateDevice, String name, String VendorModel, String ManagementAddress,
			String Snmpro, String Country, String City, String Site, String Premise) throws InterruptedException, DocumentException, IOException {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_actiondropdownfromviewpage")));
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_Editlinkunderviewdevicepage")));
		
		

		System.out.println("----- Going to perform Edit PE device actions------------");

		if (selectOrclicktogglebutttontocreateDevice.equalsIgnoreCase("create")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectdevice_ToggleButton")));

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Name")), name);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/VenderModel")), VendorModel);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementaddress")),
					ManagementAddress);

			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Snmpro")), Snmpro);

//					Select(getwebelement(xml.getlocator("//locators/"+application+"/country")), Country);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/city")), City);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/site")), Site);
//					
//					Select(getwebelement(xml.getlocator("//locators/"+application+"/premise")), Premise);
//					
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Next_Button")));

		}

		else {

			Select(getwebelement(xml.getlocator("//locators/" + application + "/SelectIMSlocation")), IMSlocation);

		}
		
		
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Backbutton")));


	}

	
	public void verifyAddDSLAMandHSLlink(String application, String DSLMdevice) throws InterruptedException, DocumentException {
		
		scrolltoend();
		Thread.sleep(3000);
		
		boolean actelisConfigurationPanel=false;
		
		actelisConfigurationPanel=getwebelement(xml.getlocator("//locators/" + application + "/ActelisConfigurationPanel")).isDisplayed();
		
		if(actelisConfigurationPanel) {
			System.out.println(" 'Actelis Configuration' panel is displaying as expected");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Actelis Configuration' panel is displaying as expected");
			
			boolean actelisLink=false;
		try {	
			actelisLink=getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM")).isDisplayed();
			if(actelisLink) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Add DSLAM and HSL' link is displaying as expected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actelisconfig_addDSLAM")));
				Thread.sleep(3000);
				
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " ''Add DSLAM and HSL' link is not displaying under 'Actelis Configuration' panel ");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to click on 'Add DSLAM and HSL' link");
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Actelis Configuration' panel is not displaying");
		}
		
	}


	
	public void selectInterfacelinkforEqipment(String Application, String devicename) throws InterruptedException, DocumentException {
		Thread.sleep(5000);

		DriverTestcase.logger.log(LogStatus.INFO, "check 'Select Interface' link");
		scrolltoend();
		Thread.sleep(3000);
		
		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Select Interfaces']"));
		System.out.println("SelectInterface link for Equipment is selected");
		Log.info("Select an inertface to add with the service under equipment");
		DriverTestcase.logger.log(LogStatus.PASS, "Select Interface link has been clicked for cpe device under Equipment");
		
		Thread.sleep(3000);
	}
	
	//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'67bts.lanlink.dcn.colt.net')]]]//span[text()='Select Interfaces']
	
	public void selectInterfacelinkforProviderEqipment(String Application) throws InterruptedException, DocumentException {
		Thread.sleep(5000);

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/Providerequipment_selectinterface")));
		System.out.println("SelectInterface link for Equipment is selected");
		Log.info("Select an inertface to add with the service under equipment");

	}

	public void selectInterfacelinkforIntermediateEqipment(String Application, String devicename)
			throws InterruptedException, DocumentException {

		try {
		Clickon(getwebelement("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Select Interfaces']"));
		System.out.println("SelectInterface link for Intermediate Equipment is selected");
		Log.info("Select an inertface to add with the service under Intermediate equipment");
		DriverTestcase.logger.log(LogStatus.PASS, "For " +devicename + " 'Select Interface' link has been clicked for cpe device under Intermediate Equipment");
	}catch(Exception e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "Select Interface link is not available under Intermediate Equipment");
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
		boolean result = driver
				.findElement(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"+ interfacenumber+"']]//input"))
				.isDisplayed();
		
		if(result) {
			
			DriverTestcase.logger.log(LogStatus.PASS, "Verified: " +interfacenumber+ " has been added to service");
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Verified: "+interfacenumber+ " is not added to service");
		}
		} catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Failure while verifying whether interface got added to service");
		}

	}

	public void verifyInterfaceremovedFromService(String application, String interfacenumber)
			throws InterruptedException, DocumentException {

		try {
		boolean result = driver
				.findElement(By.xpath(""))
				.isDisplayed();
		//needs to star scripting from here
		if(result) {
			
			DriverTestcase.logger.log(LogStatus.PASS, "Verified: "+ interfacenumber + " has been removed from the service service");
			
		}else {
			
			DriverTestcase.logger.log(LogStatus.FAIL, "The selected interface " + interfacenumber +" is not removed from the service");
			
		}
		
		}catch(Exception e) {
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

				List<WebElement> results = getwebelements("//div[div[contains(text(),'Interfaces to Select')]]/following-sibling::div[1]//div[div[text()='Client']]//span[@class='ag-icon ag-icon-checkbox-unchecked']");
						
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
								DriverTestcase.logger.log(LogStatus.PASS, interfacenumber + " is selected under 'Interface to select' table");
								Thread.sleep(8000);
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + Application + "/InterfaceToselect_Actiondropdown")));

								Thread.sleep(5000);
								
								Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_addbuton")));
								Thread.sleep(3000);
								DriverTestcase.logger.log(LogStatus.PASS, interfacenumber + " is added to service");


							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("(//div[@class='row'])[8]//div[div[contains(text(),'"
									+ interfacenumber + "')]]//input"));
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

				List<WebElement> results = driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='" + interfacenumber +"']]//span[@class='ag-icon ag-icon-checkbox-unchecked']"));
				
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
								DriverTestcase.logger.log(LogStatus.PASS, interfacenumber + " is selected under 'Interface in Service' table");
								Clickon(getwebelement(xml.getlocator(
										"//locators/" + Application + "/InterfaceInselect_Actiondropdown")));

								Thread.sleep(3000);
								
								Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_removebuton")));
								DriverTestcase.logger.log(LogStatus.PASS, interfacenumber + " has been selected to get removed from service");

							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("//div[div[contains(text(),'Interfaces in Service')]]/following-sibling::div[1]//div[div[text()='"+ interfacenumber+"']]//input"));
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

	public void PageNavigation_NextPageForInterfaceInService(String Application)
			throws InterruptedException, DocumentException {

		Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceInselect_nextpage")));
		Thread.sleep(3000);

	}

	
	

	public void selectconfigurelinkAndverifyEditInterfacefield(String application,String devicename, String interfacename) throws InterruptedException, DocumentException, IOException {
		
		try {
		
		Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Configure']"));
		
		
		
		Thread.sleep(3000);
		
		selectRowForconfiglinkunderEquipmentconfig(application, interfacename);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
        Thread.sleep(1000);	 	
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);		
		
	    
		boolean XNGcircuitID=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
		sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
		
		System.out.println("Entering bearer type dropdown");
		boolean BearerTypedropdown=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
		sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
		
		boolean Bearerspeed=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
		sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");
		
		boolean bandwidth=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")).isDisplayed();
		sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");
		
		boolean vlan=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")).isDisplayed();
		sa.assertTrue(vlan, "VLAN ID field is not displaying");
		
		boolean vlantype=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
		sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");
		System.out.println("vlan type failed");
		
		boolean ok=getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
		sa.assertTrue(ok, "Ok Button is not displaying");
		
		boolean CANCEL=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
		sa.assertTrue(CANCEL, "Cancel Button is not displaying");
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
		
		sa.assertAll();
		
		DriverTestcase.logger.log(LogStatus.PASS, "Fields are verified for 'Edit Interface' under config link");
		}catch(AssertionError e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Field validation failed for 'Edit Interface' page");
		}
		
	}
	
public void selectconfigurelinkAndverifyEditInterfacefield__Equipment(String application, String devicename) throws InterruptedException, DocumentException, IOException {
	
	scrolltoend();
	Thread.sleep(3000);
	
	Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='Configure']"));
	Thread.sleep(3000);
		
	//Try to edit without selecting the interface	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
        Thread.sleep(1000);		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);	
		
		//check whether Alert message displays
		boolean alertMessage=false;
	try {	
		alertMessage=getwebelement(xml.getlocator("//locators/" + application + "/configure_alertPopup")).isDisplayed();
		if(alertMessage) {
			DriverTestcase.logger.log(LogStatus.PASS, "Alert popup displays, if we click on 'Edit' without selected the interface");
			System.out.println("Alert popup displays, if we click on 'Edit' without selected the interface");
			
			String alertMsg=getwebelement(xml.getlocator("//locators/" + application + "/configure_alertMessage")).getText();
			DriverTestcase.logger.log(LogStatus.PASS, " Alert message displays as: "+alertMsg);
			System.out.println(" Alert message displays as: "+alertMsg);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/configure_alertPopup_xbutton")));
			Thread.sleep(3000);
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			System.out.println("'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			
			boolean editInterface_popuptitleName=false;
		try {	
			editInterface_popuptitleName=getwebelement(xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
			if(editInterface_popuptitleName) {
				DriverTestcase.logger.log(LogStatus.FAIL, "Edit interface popup is displaying without selected an interface");
				System.out.println("Edit interface popup is displaying without selected an interface");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditInterfacepopup_xbutton")));
				Thread.sleep(3000);
			}
			}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.PASS, " 'Edit interface' popup is not displaying, without selecting an Interface");
			System.out.println(" 'Edit interface' popup is not displaying, without selecting an Interface");
			}
		
		
		}}catch(NoSuchElementException e) {
			DriverTestcase.logger.log(LogStatus.FAIL, "'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			System.out.println("'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			
			
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
			System.out.println("'Alert' popup is not displaying when we click on 'edit' without selecting an interface");
		}
		
		
	}
	
	
	
	
	public void SelectShowInterfacelinkAndVerifyEditInterfacePage(String application, String interfacename, String devicename) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_showinterfaceslink")));
		Thread.sleep(3000);
		
		selectRowForEditingInterface_showInterface(application, interfacename, devicename);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showintefaceedit")));
		Thread.sleep(3000);		
		
	}
	
	
	public void SelectShowInterfacelink_IntermediateequipmentAndVerifyEditInterfacePage(String application, String interfacename, String devicename) throws InterruptedException, DocumentException, IOException {
		
		scrolltoend();
		Thread.sleep(3000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_showinterfaceslink_intEquip")));
		Thread.sleep(3000);
		
		selectRowForEditingInterface_showInterface(application, interfacename, devicename);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showintefaceedit")));
		Thread.sleep(3000);		
		
	}
	
	
public void SelectShowInterfacelink_CustomerPremiseeequipmentAndVerifyEditInterfacePage(String application, String interfacename) throws InterruptedException, DocumentException, IOException {
		
		try {
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/showInterface_ForIntermediateEquipment")));
		Thread.sleep(3000);
		
		selectRowUnderIntermediateEquipment(application, interfacename);
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateEquipment_Actiondropdown")));
		Thread.sleep(3000);		
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateEquipment_Editlink")));
		Thread.sleep(3000);		
		
		
		boolean XNGcircuitID=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
		sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
		System.out.println("circuit id is fetched");
		
		System.out.println("Entering bearer type dropdown");
		boolean BearerTypedropdown=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
		sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
		System.out.println("bearer type dropdown is fetchecd");
		
		boolean Bearerspeed=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
		sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");
		
		
		boolean bandwidth=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")).isDisplayed();
		sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");
		
		boolean vlan=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")).isDisplayed();
		sa.assertTrue(vlan, "VLAN ID field is not displaying");
		
		boolean vlantype=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
		sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");
		
		
		boolean ok=getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
		sa.assertTrue(ok, "Ok Button is not displaying");
		
		boolean CANCEL=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
		sa.assertTrue(CANCEL, "Cancel Button is not displaying");
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
		
		
		sa.assertAll();
		}catch(AssertionError e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void enterdataInsideEditInterfacepage(String application) {
		
	}



public void selectRowForEditingInterface_showInterface(String Application, String interfacename, String devicename) throws InterruptedException, DocumentException, IOException {


	int TotalPages;

	String TextKeyword = getwebelement("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//span[@ref='lbTotal']").getText();

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in table is: " + TotalPages);

	ab:
	if (TotalPages != 0) {

		for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = getwebelement("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//span[@ref='lbCurrent']").getText();
		int Current_page = Integer.parseInt(CurrentPage);
		System.out.println("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		System.out.println("Currently we are in page number: " + Current_page);

		List<WebElement> results = getwebelements("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//div[text()='"+ interfacename +"']");
		
			
		int numofrows = results.size();
		System.out.println("no of results: " + numofrows);
		boolean resultflag;

	
		if ((numofrows == 0)) {
			
			Clickon(getwebelement("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//button[text()='Next']"));
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
						DriverTestcase.logger.log(LogStatus.PASS, interfacename + " is selected under 'show Interface' ");
						Thread.sleep(8000);
						Clickon(getwebelement("(//div[div[div[contains(@title,'"+ devicename +"')]]]/following-sibling::div)[1]//button[text()='Action']"));

					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					results = driver.findElements(
							By.xpath("(//div[@class='ag-root-wrapper ag-layout-auto-height ag-ltr'])[2]//div[text()='"+interfacename +"']"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

			}


		}

	}
	}else {
		
		System.out.println("No values available in table");
		Log.info("No values available inside the InterfaceInService table");
		DriverTestcase.logger.log(LogStatus.FAIL, "No value available inside 'show Interface' panel");
	}
}


public void selectRowForshowInterfaceunderProviderEquipment(String Application, String interfacename) throws IOException, InterruptedException, DocumentException {



	int TotalPages;

	// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceTatoalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in table is: " + TotalPages);

	ab:

	for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceCurrentpage")));
		int Current_page = Integer.parseInt(CurrentPage);
		System.out.println("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		System.out.println("Currently we are in page number: " + Current_page);

		List<WebElement> results = driver
				.findElements(By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"+interfacename +"']]//input"));
		
		
			
		int numofrows = results.size();
		System.out.println("no of results: " + numofrows);
		boolean resultflag;

		if (numofrows == 0) {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceNextpage")));
			

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
							By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"+interfacename +"']]//input"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

			}


		}

	}

}



public void selectRowForshowInterfaceunderCustomerPremiseEquipment(String Application, String interfacename) throws IOException, InterruptedException, DocumentException {



	int TotalPages;

	// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceTatoalpage")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in table is: " + TotalPages);

	ab:

	for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceCurrentpage")));
		int Current_page = Integer.parseInt(CurrentPage);
		System.out.println("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		System.out.println("Currently we are in page number: " + Current_page);

		List<WebElement> results = driver
				.findElements(By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"+interfacename +"']]//input"));
		
		
			
		int numofrows = results.size();
		System.out.println("no of results: " + numofrows);
		boolean resultflag;

		if (numofrows == 0) {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_showinterfaceNextpage")));
			

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
							By.xpath("(//div[@class='ag-body-viewport ag-layout-normal'])[1]//div[div[text()='"+interfacename +"']]//input"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

			}


		}

	}

}



public void selectRowForconfiglinkunderEquipmentconfig(String Application, String interfacename) throws InterruptedException, DocumentException, IOException {


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
				.findElements(By.xpath("//div[div[text()='Interfaces']]/following-sibling::div[1]//div[text()='"+interfacename +"']"));
		
		
			
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
						DriverTestcase.logger.log(LogStatus.PASS, interfacename + " is selected");
						Thread.sleep(8000);										

					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					results = driver.findElements(
							By.xpath("//div[div[text()='Interfaces']]/following-sibling::div[1]//div[text()='\"+interfacename +\"']"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

			}


		}

	 }
	}else {
		System.out.println("No values available in table");
		Log.info("No values available inside the InterfaceInService table");
		DriverTestcase.logger.log(LogStatus.FAIL, "No value available inside the table for configure ");
	}
}


public void selectRowForconfiglinkunderIntermediateEquipment(String Application, String interfacename) throws InterruptedException, DocumentException, IOException {


	int TotalPages;

	// scroll down the page
//	  JavascriptExecutor js = ((JavascriptExecutor) driver);
//	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	  Thread.sleep(3000);

	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + Application + "/configure_totalPage")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in table is: " + TotalPages);

	ab:
if (TotalPages != 0) {	
	  for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/configure_currentpage")));
		int Current_page = Integer.parseInt(CurrentPage);
		System.out.println("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		System.out.println("Currently we are in page number: " + Current_page);

		List<WebElement> results = driver
				.findElements(By.xpath("//div[@role='row']//div[text()='"+ interfacename +"']"));
		
		
			
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
						DriverTestcase.logger.log(LogStatus.PASS, interfacename + " is selected");
						Thread.sleep(8000);
					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					results = driver.findElements(
							By.xpath("//div[@role='row']//div[text()='"+ interfacename +"']"));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

			}


		}

	}
}else {
	System.out.println("No values present inside the table");
	DriverTestcase.logger.log(LogStatus.FAIL, "No values present inside the table");
}
}



public void selectRowUnderIntermediateEquipment(String Application, String interfacename) throws InterruptedException, DocumentException, IOException {



	int TotalPages;
		

	String TextKeyword = Gettext(
			getwebelement(xml.getlocator("//locators/" + Application + "/totalpage_intermeiateshowinterfacelink")));

	TotalPages = Integer.parseInt(TextKeyword);

	System.out.println("Total number of pages in table is: " + TotalPages);

	ab:

	if (TotalPages != 0) {	
		for (int k = 1; k <= TotalPages; k++) {

		// Current page
		String CurrentPage = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/currentpage_intermeduateshowinterfacelink")));
		int Current_page = Integer.parseInt(CurrentPage);
		System.out.println("The current page is: " + Current_page);

		assertEquals(k, Current_page);

		System.out.println("Currently we are in page number: " + Current_page);

		List<WebElement> results = driver
				.findElements(By.xpath("(//div[@class='ag-root-wrapper ag-layout-auto-height ag-ltr'])[8]//div[div[text()='"+interfacename +"']]//input"));

		int numofrows = results.size();
		System.out.println("no of results: " + numofrows);
		boolean resultflag;

		if (numofrows == 0) {

			Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/pagenavigateforIntermediate")));

		}

		else {

			for (int i = 0; i < numofrows; i++) {

				try {

					resultflag = results.get(i).isDisplayed();
					System.out.println("status of result: " + resultflag);
					if (resultflag) {
						System.out.println(results.get(i).getText());
						results.get(i).click();
						DriverTestcase.logger.log(LogStatus.PASS, interfacename + " is selected after clicking on 'show Interface' ");
						Thread.sleep(8000);

					}

				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					results = driver.findElements(
							By.xpath(""));
					numofrows = results.size();
					// results.get(i).click();
					Log.info("selected row is : " + i);

				}

		   	}

	    	}

    	} 
	}else {
		
		System.out.println("No values available in table");
		Log.info("No values available inside the InterfaceInService table");
		DriverTestcase.logger.log(LogStatus.FAIL, "No value available inside 'show Interface' panel");
	}

}


    public void VerifyDataEnteredForSiteOrder(String application, String interfaceSpeed, String VPNtopology, String circuitType,
			String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	if(VPNtopology.equals("Point-to-Point")) {	
    	VerifyDataEnteredForSiteOrder_viewSiteOrder_P2P(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
    			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
    			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection);
    	}
    	
    	if(IVReference.equals("Primary")) {
    		
    		if(VPNtopology.equals("Hub&Spoke")) {
    		
    		VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Primay(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
        			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
        			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection, siteOrderNumber, 
        			circuitref, offnetSelection, IVReference, GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether, EPNoffnet, EPNEOSDH);
    		}
    		else if(VPNtopology.equals("E-PN (Any-to-Any)")) {
    			VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Primay(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
            			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
            			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection, siteOrderNumber, 
            			circuitref, offnetSelection, IVReference, GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether, EPNoffnet, EPNEOSDH);
    			
    		}
    		
    	}
    	
    	if(IVReference.equals("Access")) {
    		
    		if(VPNtopology.equals("Hub&Spoke")) {
    			
    			VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Access(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
            			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
            			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection, siteOrderNumber, 
            			circuitref, offnetSelection, IVReference, GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether, EPNoffnet, EPNEOSDH);
    			
    		}
    		else if(VPNtopology.equals("E-PN (Any-to-Any)")) {
    			
    			VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Access(application, country, city, CSR_Name, site, performReport, ProactiveMonitor, smartmonitor,
            			technology, siteallias, VLANid, DCAenabledsite, cloudserviceprovider, sitevalue, remark, xngcityname, xngcitycode, devicename,
            			nonterminatepoinr, Protected, newcityselection, existingcityselection, existingsiteselection, newsiteselection, siteOrderNumber, 
            			circuitref, offnetSelection, IVReference, GCRolo, Vlan, Vlanether, primaryVlan, primaryVlanether, EPNoffnet, EPNEOSDH);
    			
    		}
    	}	
    }
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_P2P(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection)
			throws InterruptedException, DocumentException, IOException{
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			verifyEnteredvalues("CSR Name", site);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    	
    //VLAN Id
    	verifyEnteredvalues("VLAN Id", VLANid);
    	
    //DCA Enabled Site
    	verifyEnteredvalues("DCA Enabled Site", DCAenabledsite);
    	
    	if(DCAenabledsite.equalsIgnoreCase("Yes")) {
    		
    		//Cloud Service Provider
    		     verifyEnteredvalues("Cloud Service Provider", cloudserviceprovider);
    		
    	}
    	
    //Remark
    	verifyEnteredvalues("Remark", remark);
    	
    	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Alu")) {
    		//Device Name text field
			verifyEnteredvalues("Device Name", devicename);
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    }
    
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Primay(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	
    //Site order Number
    	verifyEnteredvalues("Site Order Number", siteOrderNumber);
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			verifyEnteredvalues("CSR Name", site);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	Thread.sleep(3000);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    
    //IV Reference
    	verifyEnteredvalues("IV Reference", IVReference);
    	
    	
    //Remark
    	verifyEnteredvalues("Remark", remark);
    	
    	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Alu")) {
    		//Device Name text field
			verifyEnteredvalues("Device Name", devicename);
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    	
    	
    	
    }
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Primay(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	
    //Site order Number
    	verifyEnteredvalues("Site Order Number", siteOrderNumber);
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			verifyEnteredvalues("CSR Name", site);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    
    //IV Reference
    	verifyEnteredvalues("IV Reference", IVReference);
    	
    	
    //Remark
    	verifyEnteredvalues("Remark", remark);
    	
    	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    			
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    			
    		//EPN Offnet
    			if(EPNoffnet.equalsIgnoreCase("yes")) {
    				verifyEnteredvalues("EPN Offnet", EPNoffnet);
    			}else{
    				System.out.println("EPN offnet will not display, if it is selected");
    			}
    			
    			
    		//EPN EOSDH
    			if(EPNEOSDH.equalsIgnoreCase("Yes")) {
    				verifyEnteredvalues("EPN EOSDH", EPNEOSDH);
    			}else {
    				System.out.println("EPN EOSDH will not display, if it is not selected");
    			}
    			
    	}
    	
    	
    	if(technology.equals("Alu")) {
    		//Device Name text field
			verifyEnteredvalues("Device Name", devicename);
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
			
			//Device Name text field
			verifyEnteredvalues("Device Name", devicename);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    	
    	
    	
    }
    
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_EPN_Access(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	
    //Site order Number
    	verifyEnteredvalues("Site Order Number", siteOrderNumber);
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			verifyEnteredvalues("CSR Name", site);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    
    //IV Reference
    	verifyEnteredvalues("IV Reference", IVReference);
    	
    	
    //Remark
    	verifyEnteredvalues("Remark", remark);
    	
    	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    			
    		//Device Name text field
    			verifyEnteredvalues("Device Name", devicename);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    			
    		//EPN Offnet
    			verifyEnteredvalues("EPN Offnet", EPNoffnet);
    			
    		//EPN EOSDH
    			
    	}
    	
    	
    	if(technology.equals("Alu")) {
    	//Device Name text field
			verifyEnteredvalues("Device Name", devicename);
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
			
			//Device Name text field
//			verifyEnteredvalues("Device Name", devicename);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    	
    	
    	
    }
    
    
    
    public void VerifyDataEnteredForSiteOrder_viewSiteOrder_hubAndSpoke_Access(String application, String country, String city, String CSR_Name, String site,
			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
			String existingsiteselection, String newsiteselection,
			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether,String EPNoffnet,String EPNEOSDH)
			throws InterruptedException, DocumentException, IOException{
    	
    	
    //Site order Number
    	verifyEnteredvalues("Site Order Number", siteOrderNumber);
    	
    //Country	
    	verifyEnteredvalues("Device Country", country);
    	
    //City
    	
    	if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {
    		
    	//City	
    		verifyEnteredvalues("Device Xng City", city);
    		
    	//Site
    		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
    			verifyEnteredvalues("CSR Name", site);
    		}
    		
    		else if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
    			verifyEnteredvalues("CSR Name", CSR_Name);
    		}
    		
    	}
    	else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
    		
    		//New City
    		verifyEnteredvalues("Device Xng City", xngcitycode);
    		
    		//New Site
    		verifyEnteredvalues("CSR Name", CSR_Name);
    		
    	}
    	
    	
    //Performance Reporting
    	verifyEnteredvalues("Performance Reporting", performReport);
    	
    //Proactive Monitoring
    	verifyEnteredvalues("Proactive Monitoring", ProactiveMonitor);
    	
    //Smarts Monitoring
    	verifyEnteredvalues("Smarts Monitoring", smartmonitor);
    	
    //Technology
    	verifyEnteredvalues("Technology", technology);
    	
    //Site Alias
    	verifyEnteredvalues("Site Alias", siteallias);
    
    //IV Reference
    	verifyEnteredvalues("IV Reference", IVReference);
    	
    	
    //Remark
    	verifyEnteredvalues("Remark", remark);
    	
    //Based on Technology	
    	if(technology.equals("Atrica")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	
    	if(technology.equals("Overture")) {
    		//Non_termination Point checkbox
    			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    		
    		//Protected checkbox
    			verifyEnteredvalues("Protected", Protected);
    			
    		
    		//GCR OLO Type dropdown
    			verifyEnteredvalues("GCR OLO Type", GCRolo);
    			
    		//VLAN tetx field
    			verifyEnteredvalues("VLAN", Vlan);
    			
    		//VLAN Ether type dropdown
    			verifyEnteredvalues("VLAN Ether Type", Vlanether);
    			
    		//Primary VLAN Text field
    			verifyEnteredvalues("Primary VLAN", primaryVlan);
    			
    		//Primary VLAN Ether Type
    			verifyEnteredvalues("Primary VLAN Ether Type", primaryVlanether);
    	}
    	
    	
    	if(technology.equals("Alu")) {
    		
    		System.out.println("No additional Fields");
    	}
    	
    	if(technology.equals("Accedian-1G")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);

			//Protected checkbox
			verifyEnteredvalues("Protected", Protected);
    	}
    	
    	if(technology.equals("Cyan")) {
    		//Non_termination Point checkbox
			verifyEnteredvalues("Non Termination Point", nonterminatepoinr);
    	}
    	
    	
    	
    }
    
    
    
    public void VerifySiteOrderdetailsInTable(String Application, String siteordernumber) throws InterruptedException, DocumentException, IOException {

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

  if(TotalPages>=1) {			
		for (int k = 1; k <= TotalPages; k++) {

			// Current page
			String CurrentPage = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/Currentpageforsiteorder")));
			int Current_page = Integer.parseInt(CurrentPage);

			
			System.out.println("Checking whether next page button is disabled or not");
	
				
				boolean nextpage= getwebelement(xml.getlocator("//locators/" + Application + "/Pagenavigationfornextpage")).isEnabled();
	
				System.out.println("Entered while loop");
  while(nextpage)
  {
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
							System.out.println("The field values are: "+results.get(i).getText());
							Log.info("The values stored in the table for adding site order are: "+results.get(i).getText());
							
						}

					} catch (StaleElementReferenceException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						results = driver.findElements(
								By.xpath("//div[div[contains(text(),'" + siteordernumber + "')]]"));
						numofrows = results.size();
						// results.get(i).click();
						Log.info("selected row is : " + i);

					}

				}

				break ab;

			}

		}
		
      }else {
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
    
    
    public void EnterdataForEditInterfaceforShowInterfacelinkunderEquipment(String application, String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
    		String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

			//verify Edit Interface page	
				boolean editInterface_popuptitleName=false;
				try {	
					editInterface_popuptitleName=getwebelement(xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
					if(editInterface_popuptitleName) {
						DriverTestcase.logger.log(LogStatus.PASS, "Edit interface popup is displaying as expected");
						System.out.println("Edit interface popup is displaying as expected");
						
						
					//Interface name
				    	verifyenteredvaluesForEditPage_noneditableFields(application, "Interface", interfacename);
				    	
				    //Circuit ID	
					     configure_circuitId(application, circuitID );
					
					 //Bearer type	
					     addDropdownValues_commonMethod(application, "Bearer Type", "Equipment_configureBearerType", bearertype);
					 
					 //Bearerspeed
					     addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed", bearerspeed);
						
					//Total Circuit bandwidth	
					     addDropdownValues_commonMethod(application, "Total Circuit Bandwidth", "Equipment_configureTotalcircuitbandwidth", totalbandwidth);
					     
					//VLAN type 
					     addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype);
					
					//vlan	
					     addtextFields_commonMethod(application, "VLAN Id", "Equipment_configureVLANid", vlanid);
					 Thread.sleep(3000);
								
					
					}
					}catch(NoSuchElementException e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Edit interface' popup is not displaying");
					System.out.println(" 'Edit interface' popup is not displaying");
					}
				
		
	DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered inside Edit Interface page by selecting configure link under Intermediate Equipment");	
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(3000);
	
		   
    
    }


    public void EnterdataForEditInterfaceforShowInterfacelinkunderProviderEquipment(String application, String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
    		String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

    	
    	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showInterfaceActiondropdown")));
		Thread.sleep(3000);		
		
    	
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);		
		
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")), circuitID);
		

		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")));
		Clickon(getwebelement("//div[text()='"+bearertype +"']"));
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")));
		Clickon(getwebelement("//div[text()='"+bearerspeed +"']"));
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")));
		Clickon(getwebelement("//div[text()='"+totalbandwidth +"']"));
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")), vlanid);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")));
		Clickon(getwebelement("//div[text()='"+vlantype +"']"));
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(5000);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_hideinterfaceslinkforequipment")));
		Thread.sleep(3000);
		   
    }


    public void EnterdataForEditInterfaceforShowInterfacelinkunderIntermediateEquipment(String application, String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
    		String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

			//verify Edit Interface page	
				boolean editInterface_popuptitleName=false;
				try {	
					editInterface_popuptitleName=getwebelement(xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
					if(editInterface_popuptitleName) {
						DriverTestcase.logger.log(LogStatus.PASS, "Edit interface popup is displaying as expected");
						System.out.println("Edit interface popup is displaying as expected");
						
						
					//Interface name
				    	verifyenteredvaluesForEditPage_noneditableFields(application, "Interface", interfacename);
				    	
				    //Circuit ID	
					     configure_circuitId(application, circuitID );
					
					 //Bearer type	
					     addDropdownValues_commonMethod(application, "Bearer Type", "Equipment_configureBearerType", bearertype);
					 
					 //Bearerspeed
					     addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed", bearerspeed);
						
					//Total Circuit bandwidth	
					     addDropdownValues_commonMethod(application, "Total Circuit Bandwidth", "Equipment_configureTotalcircuitbandwidth", totalbandwidth);
					     
					//VLAN type 
					     addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype);
					
					//vlan	
					     addtextFields_commonMethod(application, "VLAN Id", "Equipment_configureVLANid", vlanid);
					 Thread.sleep(3000);
								
					
					}
					}catch(NoSuchElementException e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Edit interface' popup is not displaying");
					System.out.println(" 'Edit interface' popup is not displaying");
					}
				
		
	DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered inside Edit Interface page by selecting configure link under Intermediate Equipment");	
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(3000);
	
		   
    
    }

    
    public void EnterdataForEditInterfaceforConfigurelinkunderEquipment(String application, String interfacename, String circuitID, String bearertype, 
    		String bearerspeed, String totalbandwidth,String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

    	 
	    Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
        Thread.sleep(1000);		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
		Thread.sleep(3000);			
		
	//Circuit ID	
	     configure_circuitId(application, circuitID );
	
	 //Bearer type	
	     addDropdownValues_commonMethod(application, "Bearer Type", "Equipment_configureBearerType", bearertype);
	 
	 //Bearerspeed
	     addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed", bearerspeed);
		
	//Circuit bandwidth	
	     addDropdownValues_commonMethod(application, "Total Circuit Bandwidth", "Equipment_configureTotalcircuitbandwidth", totalbandwidth);
	     
	//VLAN type 
	     addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype);
	
//vlan	
	 try {
		if(vlanid.equalsIgnoreCase("null")) {
			
			DriverTestcase.logger.log(LogStatus.PASS, " No input provided for 'Vlan id'");
			
		}else {
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")), vlanid);
		DriverTestcase.logger.log(LogStatus.PASS, vlanid + " is the value passed for 'Vlan ID' field");
		}
		
	 }catch(NoSuchElementException e) {
		 e.printStackTrace();
		 DriverTestcase.logger.log(LogStatus.FAIL, "'Vlan id' dropdown under 'Edit Interface' page is not available");
	 }catch(Exception err) {
		 err.printStackTrace();
		 DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value for 'Vlan id' field");
	 }
	 Thread.sleep(3000);
		
		
		
		
		
		DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered inside Edit Interfaec page by selecting configure link under Equipment");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(3000);
		   
    }
   
	
    
    public void EnterdataForEditInterfaceforConfigurelinkunderIntermediateEquipment(String application, String interfacename, String circuitID, String bearertype, String bearerspeed, String totalbandwidth,
    		String vlanid, String vlantype) throws InterruptedException, DocumentException, IOException {
    	

    	//select the Interface	
				selectRowForconfiglinkunderIntermediateEquipment(application, interfacename);
				
			//Perform edit Interface	
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureActiondropdown")));
		        Thread.sleep(3000);		
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
				Thread.sleep(3000);	
				
			//verify Edit Interface page	
				boolean editInterface_popuptitleName=false;
				try {	
					editInterface_popuptitleName=getwebelement(xml.getlocator("//locators/" + application + "/Editinterface_popupTitlename")).isDisplayed();
					if(editInterface_popuptitleName) {
						DriverTestcase.logger.log(LogStatus.PASS, "Edit interface popup is displaying as expected");
						System.out.println("Edit interface popup is displaying as expected");
						
						
					//Interface name
				    	verifyenteredvaluesForEditPage_noneditableFields(application, "Interface", interfacename);
				    	
				    //Circuit ID	
					     configure_circuitId(application, circuitID );
					
					 //Bearer type	
					     addDropdownValues_commonMethod(application, "Bearer Type", "Equipment_configureBearerType", bearertype);
					 
					 //Bearerspeed
					     addDropdownValues_commonMethod(application, "Bearer Speed", "Equipment_configureBearerSpeed", bearerspeed);
						
					//Total Circuit bandwidth	
					     addDropdownValues_commonMethod(application, "Total Circuit Bandwidth", "Equipment_configureTotalcircuitbandwidth", totalbandwidth);
					     
					//VLAN type 
					     addDropdownValues_commonMethod(application, "VLAN Type", "Equipment_configureVlanType", vlantype);
					
					//vlan	
					     addtextFields_commonMethod(application, "VLAN Id", "Equipment_configureVLANid", vlanid);
					 Thread.sleep(3000);
								
					
					}
					}catch(NoSuchElementException e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Edit interface' popup is not displaying");
					System.out.println(" 'Edit interface' popup is not displaying");
					}
				
		
	DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered inside Edit Interface page by selecting configure link under Intermediate Equipment");	
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(3000);
	
		   
    }
    
    
    
    
    public void AddInterfaceToserviceforProviderEquipment(String Application, String interfacenumber) throws IOException, InterruptedException, DocumentException {
    	
    	int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectTotalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in Interface to select table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_currentpageInterfaceToselect")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));
				
				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					Clickon(getwebelement(xml.getlocator(
							"//locators/" + Application + "/providerEquipment_InterfaceToselectnextpage")));


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
										"//locators/" + Application + "/providerEquipment_InterfaceToselectActiondropdown")));

								Thread.sleep(5000);
								
								Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectAddbutton")));


							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));
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
				.findElement(By.xpath("(//div[@class='row'][1]//div[div[contains(text(),'"+interfacenumber +"')]])"))
				.isDisplayed();
		sa.assertTrue(result, "Verified: Interface got added to service");
		} catch(Exception e) {
			System.out.println("No values available inside the table");
		}

	}
    
    
    public void ProviderEquipmentInterfaceInService(String Application, String interfacenumber) throws IOException, InterruptedException, DocumentException {
    	


		int TotalPages;

		String TextKeyword = Gettext(
				getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceInselectTotalpage")));

		TotalPages = Integer.parseInt(TextKeyword);

		System.out.println("Total number of pages in table is: " + TotalPages);

		ab:

		if (TotalPages != 0) {
			for (int k = 1; k <= TotalPages; k++) {

				// Current page
				String CurrentPage = Gettext(
						getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_currentpageInterfaceInselect")));
				int Current_page = Integer.parseInt(CurrentPage);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);

				List<WebElement> results = driver.findElements(By.xpath("(//div[@class='row'][1]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);
				boolean resultflag;

				if (numofrows == 0) {

					getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceInselectnextpage"));
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
								
								Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/InterfaceToselect_removebuton")));


							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
							results = driver.findElements(By.xpath("(//div[@class='row'][1]//div[div[contains(text(),'\"+interfacenumber +\"')]])//input"));
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
				.findElement(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"))
				.isDisplayed();
		sa.assertTrue(result, "Verified: Interface got removed from the service");
		sa.assertAll();
		}catch(Exception e) {
			System.out.println("No values found inside the table");
		}

	}
    
    
    public void SelectShowInterfacelinkAndVerifyEditInterfacePageforProviderEquipment(String application, String interfacename) throws InterruptedException, DocumentException, IOException {
		
    	try {
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_showInterfacelink")));
    		Thread.sleep(3000);
    		     		
    		selectRowForshowInterfaceunderProviderEquipment(application, interfacename);
    		
    	
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showInterfaceActiondropdown")));
    		Thread.sleep(3000);		
    		
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
    		Thread.sleep(3000);		
    		
    		
    		boolean XNGcircuitID=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
    		sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
    		System.out.println("circuit id is fetched");
    		
    		System.out.println("Entering bearer type dropdown");
    		boolean BearerTypedropdown=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
    		sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
    		System.out.println("bearer type dropdown is fetchecd");
    		
    		boolean Bearerspeed=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
    		sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");
    		
    		
    		boolean bandwidth=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")).isDisplayed();
    		sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");
    		
    		boolean vlan=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")).isDisplayed();
    		sa.assertTrue(vlan, "VLAN ID field is not displaying");
    		
    		boolean vlantype=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
    		sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");
    		
    		
    		boolean ok=getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
    		sa.assertTrue(ok, "Ok Button is not displaying");
    		
    		boolean CANCEL=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
    		sa.assertTrue(CANCEL, "Cancel Button is not displaying");
    		
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
    		Thread.sleep(3000);
    		
    		sa.assertAll();
    		
    	}catch(AssertionError e) {
    		e.printStackTrace();
    	}
    		
    	}
    
    
 public void SelectShowInterfacelinkAndVerifyEditInterfacePageforCustomerPremiseEquipment(String application, String interfacename) throws InterruptedException, DocumentException, IOException {
		
    	try {
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/customerpremiseEQuipment_showinetrfacelink")));
    		Thread.sleep(3000);
    		     		
    		selectRowForshowInterfaceunderCustomerPremiseEquipment(application, interfacename);
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_showInterfaceActiondropdown")));
    		Thread.sleep(3000);		
    		
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureEditlink")));
    		Thread.sleep(3000);		
    		
    		
    		boolean XNGcircuitID=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).isDisplayed();
    		sa.assertTrue(XNGcircuitID, "Circuit id is not displaying");
    		System.out.println("circuit id is fetched");
    		
    		System.out.println("Entering bearer type dropdown");
    		boolean BearerTypedropdown=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerType")).isDisplayed();
    		sa.assertTrue(BearerTypedropdown, "Circuit bearer type dropdown is not displaying");
    		System.out.println("bearer type dropdown is fetchecd");
    		
    		boolean Bearerspeed=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureBearerSpeed")).isDisplayed();
    		sa.assertTrue(Bearerspeed, "Circut bearer speed dropdown is not displaying");
    		
    		
    		boolean bandwidth=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureTotalcircuitbandwidth")).isDisplayed();
    		sa.assertTrue(bandwidth, "Total circuit bandwidth dropdown is not displaying");
    		
    		boolean vlan=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVLANid")).isDisplayed();
    		sa.assertTrue(vlan, "VLAN ID field is not displaying");
    		
    		boolean vlantype=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureVlanType")).isDisplayed();
    		sa.assertTrue(vlantype, "VLANtype dropdown is not displaying");
    		
    		
    		boolean ok=getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
    		sa.assertTrue(ok, "Ok Button is not displaying");
    		
    		boolean CANCEL=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
    		sa.assertTrue(CANCEL, "Cancel Button is not displaying");
    		
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
    		Thread.sleep(3000);
    		
    		sa.assertAll();
    		
    	}catch(AssertionError e) {
    		e.printStackTrace();
    	}
    		
    	}

    
    
public void selectconfigurelinkAndverifyForProviderEquipment(String application, String interfacename) throws InterruptedException, DocumentException, IOException {
		
		try {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Providerequipment_configurelink")));
		Thread.sleep(3000);
		
		
		boolean GenerateConfigForStaticRoutes=getwebelement(xml.getlocator("//locators/" + application + "/ProviderEquipment_Generateconfigforstaticlink")).isDisplayed();
		sa.assertTrue(GenerateConfigForStaticRoutes, "generate configuration for static routes link is not displayed");
		
		boolean SaveConfigurationlink=getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_saveconfigurationlink")).isDisplayed();
		sa.assertTrue(SaveConfigurationlink, "Save configuration link is not displayed");
		
		boolean ExecuteconfigOndevice=getwebelement(xml.getlocator("//locators/" + application + "/providerEquipment_ExecuteconfigurationOndevicelink")).isDisplayed();
		sa.assertTrue(ExecuteconfigOndevice, "Execute configuration on device link is not displayed");
		
		sa.assertAll();
		
		}catch(AssertionError e) {
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
			Log.info(
					"Are you sure want to delete ? - message is getting displayed on clicking DeletefromService link");
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
	
	
	public void verifyAddnewlinkunderPE2CPEtable(String application) throws InterruptedException, DocumentException, IOException {
	
		
		String HeaderNameExpected="Add New Link";
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Actionbutton_PE2CPE")));
		Thread.sleep(3000);
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addnewlink_Pe2CPE")));
		Thread.sleep(3000);
	
		String headername=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Headername_PE2CPElink")));
		sa.assertEquals(headername, HeaderNameExpected, "Header name is not displaying as expected");
		
		boolean CircuitId=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_LinkorCircuitId")).isDisplayed();
		sa.assertTrue(CircuitId, "circuit id is not displaying");
		
		
		boolean sourceDevice=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceDevice")).isDisplayed();
		sa.assertTrue(sourceDevice, "Source Device dropdown is not displaying");
		
		
		boolean Sourceinterface=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceInterface")).isDisplayed();
		sa.assertTrue(Sourceinterface, "Source Interface dropdown is not displaying");
		
		boolean targetdevice=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetDevice")).isDisplayed();
		sa.assertTrue(targetdevice, "Target device dropdown is not displaying");
		
		boolean targetInterface=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetInterface")).isDisplayed();
		sa.assertTrue(targetInterface, "Target Interface dropdown is not displaying");
		
		boolean next=getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_Nextbutton")).isDisplayed();
		sa.assertTrue(next, "Next button is not displaying");
		
		boolean cancel=getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")).isDisplayed();
		sa.assertTrue(cancel, "CAncel button is not displaying");
		
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cancelButton")));
		Thread.sleep(3000);
	
		
		
	}

	public void enterdataforAddNewlinkunderPEtoCPEtable(String application, String CircuitId, String sourcedevice, String sourceinterfacce, String targetDevice,String targetInterface, String interfacename) throws InterruptedException, DocumentException, IOException {
		
	
		
		selecttherowforPEtoCPElinktable(application, interfacename);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addnewlink_Pe2CPE")));

		
        SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_LinkorCircuitId")), CircuitId);
        
        Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceDevice")), sourcedevice);
        
        Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_SourceInterface")), sourceinterfacce);
        
        Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetDevice")), targetDevice);
        
        Select(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_TargetInterface")), targetInterface);
        
        Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddnewlinkPe2CPE_Nextbutton")));
		Thread.sleep(3000);
	

		
	}
	
	
	
	public void enterdataforeditNewlinkunderPEtoCPEtable(String application) {
		
	}

	
	
	 public void selecttherowforPEtoCPElinktable(String Application, String interfacename) throws IOException, InterruptedException, DocumentException {
		 

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

					List<WebElement> results = driver.findElements(By.xpath("(//div[@class='ag-div-margin row']//div[div[contains(text(),'"+interfacename +"')]])//input"));

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
									Clickon(getwebelement(xml.getlocator(
											"//locators/" + Application + "/Actionbutton_PE2CPE")));

									Thread.sleep(5000);
									
									

								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = driver.findElements(By.xpath("(//div[@class='ag-div-margin row']//div[div[contains(text(),'"+interfacename +"')]])//input"));
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
	 
	 
	 
	 public void selectInterfacelinkforCustomerpremiseequipment(String application) throws InterruptedException, DocumentException {
		 
				Thread.sleep(5000);

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/cusomerpremiseequipment_SelectInertafeceslink")));
				System.out.println("SelectInterface link for Customer premise Equipment is selected");
				Log.info("Select an inertface to add with the service under customer premise equipment");

			}
	 
	 
	 public void AddInterfacetoserviceforcustomerpremiseEquipment(String Application, String interfacenumber) throws IOException, InterruptedException, DocumentException {
		 
 	    	
	    	int TotalPages;

			String TextKeyword = Gettext(
					getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectTotalpage")));

			TotalPages = Integer.parseInt(TextKeyword);

			System.out.println("Total number of pages in Interface to select table is: " + TotalPages);

			ab:

			if (TotalPages != 0) {
				for (int k = 1; k <= TotalPages; k++) {

					// Current page
					String CurrentPage = Gettext(
							getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_currentpageInterfaceToselect")));
					int Current_page = Integer.parseInt(CurrentPage);

					assertEquals(k, Current_page);

					System.out.println("Currently we are in page number: " + Current_page);

					List<WebElement> results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));
					
					int numofrows = results.size();
					System.out.println("no of results: " + numofrows);
					boolean resultflag;

					if (numofrows == 0) {

						Clickon(getwebelement(xml.getlocator(
								"//locators/" + Application + "/providerEquipment_InterfaceToselectnextpage")));


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
											"//locators/" + Application + "/providerEquipment_InterfaceToselectActiondropdown")));

									Thread.sleep(5000);
									
									Clickon(getwebelement(xml.getlocator("//locators/" + Application + "/providerEquipment_InterfaceToselectAddbutton")));


								}

							} catch (StaleElementReferenceException e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								results = driver.findElements(By.xpath("(//div[@class='row'][2]//div[div[contains(text(),'"+interfacenumber +"')]])//input"));
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
				String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement,
				String managementConnection, String ENNIcheckBox)
				throws InterruptedException, IOException, DocumentException {

			
			//Service Identification
				 editService_serviceIdentification(application, ServiceIdentificationNumber);
				
			//Endpoint CPE	
				 editService_singleEndPointCPE(application, EndpointCPE, vpnTopology);
				 
			//Email	
				 editService_Email(application, Email);
				
			//Phone contact	`
				 editService_phoneContact(application, PhoneContact);
				
			//Remark	
				 editService_remark(application, remark);
				
				
			//Performance Reporting	
			  boolean perfrmReportAvailability=false;
			  try {
				  perfrmReportAvailability=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();
			  
			  if(perfrmReportAvailability) {
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " 'Performance reporting' checkbox is displaying in 'Edit Service' page as expected");
				  
				if(!performanceReporting.equalsIgnoreCase("null")) {
					
					boolean perfReport=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
					Thread.sleep(2000);
					
					if (performanceReporting.equalsIgnoreCase("yes")) {

						if(perfReport) {
							
							DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting checkbox is not edited and it is already Selected while creating");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
							Log.info("Performance Reporting check box is selected");
							DriverTestcase.logger.log(LogStatus.PASS,"Performance Reporting checkbox is selected");
						}
					}
					else if (performanceReporting.equalsIgnoreCase("no")) {
						
						if(perfReport) {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
							Log.info("Performance Reporting check box is unselected");
							DriverTestcase.logger.log(LogStatus.PASS,"Performance Reporting is edited and gets unselected");
							
						}else {
							DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting is not edited and it remains unselected");
						}
						
					}
				}else {
					DriverTestcase.logger.log(LogStatus.PASS,"No changes made for Performance Reporting chekbox");
				}
			  }else {
				  DriverTestcase.logger.log(LogStatus.FAIL, " 'Performance reporting' checkbox is not available in 'Edit Service' page");
			  }

				}catch(NoSuchElementException e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " ' Performance Reporting' checkbox is not displaying under 'Edit service' page");
					System.out.println(" ' Performance Reporting' checkbox is not displaying under 'Edit service' page");
				}catch(Exception err) {
					err.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on 'Performance Reporting' checkbox");
					System.out.println(" Not able to click on 'erformance Reporting' checkbox");
				}
				
							
				//Proactive monitoring
					editService_proactiveMonitoring(application, ProactiveMonitoring, notificationManagement);		
				
				//Delivery channel
				  	editService_deliveryChannel(application, deliveryChannel);
			
				//Management Connection
				  	editService_managementConnection(application, managementConnection);
				  	
				//ENNI checkbox
				  	editcheckbox_commonMethod(application, ENNIcheckBox, "ENNI_checkbox", "ENNI");

				
						
			
				System.out.println("going to click on OK buttto");
				scrolltoend();
				Thread.sleep(3000);
				
			 //click on "Ok button to update
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
				Thread.sleep(3000);
			 
			 }
	 
	 
	 
	 public void Edit_DirectFibre1G(String application, String ServiceIdentificationNumber, String SelectSubService,
				String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
				String performanceReporting, String ProactiveMonitoring, String deliveryChannel, String ManagementOrder,
				String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement,
				String perCoSperformanceReport, String actelisBased, String standardCIR, String standardEIR, String premiumCIR, String premiumEIR,
				String ManagementConnection, String ENNIcheckBox)
				throws InterruptedException, IOException, DocumentException {

		
	//Service Identification
		 editService_serviceIdentification(application, ServiceIdentificationNumber);
		
	//Endpoint CPE	
		 editService_singleEndPointCPE(application, EndpointCPE, vpnTopology);
		 
	//Email	
		 editService_Email(application, Email);
		
	//Phone contact	`
		 editService_phoneContact(application, PhoneContact);
		
	//Remark	
		 editService_remark(application, remark);
		
		
	//Performance Reporting	
	  boolean perfrmReportAvailability=false;
	  try {
		  perfrmReportAvailability=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isDisplayed();
	  
	  if(perfrmReportAvailability) {
		  
		  DriverTestcase.logger.log(LogStatus.PASS, " 'Performance reporting' checkbox is displaying in 'Edit Service' page as expected");
		  
		if(!performanceReporting.equalsIgnoreCase("null")) {
			
			boolean perfReport=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
			Thread.sleep(2000);
			
			if (performanceReporting.equalsIgnoreCase("yes")) {

				if(perfReport) {
					
					DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting checkbox is not edited and it is already Selected while creating");
					
				}else {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("Performance Reporting check box is selected");
					DriverTestcase.logger.log(LogStatus.PASS,"Performance Reporting checkbox is selected");
				}
			}
			else if (performanceReporting.equalsIgnoreCase("no")) {
				
				if(perfReport) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("Performance Reporting check box is unselected");
					DriverTestcase.logger.log(LogStatus.PASS,"Performance Reporting is edited and gets unselected");
					
				}else {
					DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting is not edited and it remains unselected");
				}
				
			}
		}else {
			DriverTestcase.logger.log(LogStatus.PASS,"No changes made for Performance Reporting chekbox");
		}
	  }else {
		  DriverTestcase.logger.log(LogStatus.FAIL, " 'Performance reporting' checkbox is not available in 'Edit Service' page");
	  }

		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " ' Performance Reporting' checkbox is not displaying under 'Edit service' page");
			System.out.println(" ' Performance Reporting' checkbox is not displaying under 'Edit service' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on 'Performance Reporting' checkbox");
			System.out.println(" Not able to click on 'erformance Reporting' checkbox");
		}
		
	//Per CoS Performance Reporting
	if(performanceReporting.equalsIgnoreCase("yes")) {
		boolean perCoSdisplay=false;
		try {
		perCoSdisplay=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isDisplayed();
		Thread.sleep(3000);
		
		if(perCoSdisplay) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Per CoS Performance Reporting' checkbox is displaying in 'Edit Service' page");
		if(!perCoSperformanceReport.equalsIgnoreCase("null")) {
			
			boolean perCoSreport=getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")).isSelected();
			Thread.sleep(2000);
			
			if (perCoSperformanceReport.equalsIgnoreCase("yes")) {

				if(perCoSreport) {
					
					DriverTestcase.logger.log(LogStatus.PASS, "Per CoS Performance Reporting checkbox is not edited and it is already Selected while creating");
					
				}else {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));
					Log.info("Per CoS Performance Reporting check box is selected");
					DriverTestcase.logger.log(LogStatus.PASS,"Per CoS Performance Reporting is selected");
				}
			}

			else if (perCoSperformanceReport.equalsIgnoreCase("no")) {
				
				if(perCoSreport) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/perCoSperformncereport")));
					Log.info("Per CoS Performance Reporting check box is unselected");
					DriverTestcase.logger.log(LogStatus.PASS,"PPer CoS Performance Reporting is edited and gets unselected");
					
				}else {
					DriverTestcase.logger.log(LogStatus.PASS, "Per CoS Performance Reporting is not edited and it remains unselected");
				}
				
			}
		}else {
			DriverTestcase.logger.log(LogStatus.PASS," 'Per CoS Performance Reporting' chekbox is not edited");
		}
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Per CoS Performance Reporting' checkbox is not displaying under 'Edit service' page");
	}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Per CoS Performance Reporting' checkbox is not displaying under 'Edit service' page");
			System.out.println(" 'Per CoS Performance Reporting' checkbox is not displaying under 'Edit service' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on 'Per CoS Performance Reporting' checkbox");
			System.out.println(" Not able to click on 'Per CoS Performance Reporting' checkbox");
		}
	}	
		
		//Proactive monitoring
			editService_proactiveMonitoring(application, ProactiveMonitoring, notificationManagement);		
		
		//Delivery channel
		  	editService_deliveryChannel(application, deliveryChannel);
	
		//Management Connection
		  	editService_managementConnection(application, ManagementConnection);
		  	
		//ENNI checkbox
		  	editcheckbox_commonMethod(application, ENNIcheckBox, "ENNI_checkbox", "ENNI");

		
		System.out.println("going to click on OK buttto");
		scrolltoend();
		Thread.sleep(3000);
		
	 //click on "Ok button to update
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(3000);
	 
	 }
	 
	 
	 public void EditLanlinkMetro(String application, String ServiceIdentificationNumber, String SelectSubService,
				String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
				String PerformanceMonitoring, String ProactiveMonitoring, String deliveryChannel,
				String vpnTopology, String intermediateTechnology, String CircuitReference, String CircuitType, String notificationManagement)
				throws InterruptedException, IOException, DocumentException {
		 


		//Service Identification		
			if(!ServiceIdentificationNumber.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).clear();
				Thread.sleep(2000);
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
						ServiceIdentificationNumber);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Service Identification' field is "+ ServiceIdentificationNumber);
				
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Service Identification field");
			}
			
			
		//Endpoint CPE	
			if(!EndpointCPE.equalsIgnoreCase("null")) {
				
				boolean endpoint=getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isSelected();
				Thread.sleep(2000);
				
				if (EndpointCPE.equalsIgnoreCase("yes")) {

					if(endpoint) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Endpoint CPE is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"Endpoint CE is selected as Expected");
					}
					
					
				}

				else if (EndpointCPE.equalsIgnoreCase("no")) {
					
					if(endpoint) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"Endpoint CE is unselected as Expected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "Endpoint CPE was not selected during service creation and it remains unselected as expected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for EndPoint CPE chekbox as expected");
			}
			
		//Email	
			if(!Email.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Email")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Email' field is "+ Email);

			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Email field");
			}
			
		
		//Phone contact	
			if(!PhoneContact.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Phone contact' field is " + PhoneContact);
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Phone contact field");
			}
			
			
		//Remark	
			if(!remark.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Remark")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Remark' field is "+ remark);
				
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Remark field");
		}
			
			
		//Performance Monitoring	
			if(!PerformanceMonitoring.equalsIgnoreCase("null")) {
				
				boolean perfmmonitr=getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")).isSelected();
				Thread.sleep(2000);
				
				if (PerformanceMonitoring.equalsIgnoreCase("yes")) {

					if(perfmmonitr) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Performance Monitor checkbox is not edited and it is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
						Log.info("Performance monitor check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"Performance monitor is selected");
					}
					
					
				}

				else if (PerformanceMonitoring.equalsIgnoreCase("no")) {
					
					if(perfmmonitr) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
						Log.info("Performance Monitor check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"Performance Monitor is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "Performance Monitor is not edited and it remains unselected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for Performance Monitor chekbox");
			}



		//Proactive monitoring
			if(!ProactiveMonitoring.equalsIgnoreCase("null")) {
				
				boolean proactivemonitor=getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
				Thread.sleep(2000);
				
				if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

					if(proactivemonitor) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Proactive monitoring is not edited and it is already Selected while creating");
						

						if(notificationManagement.equalsIgnoreCase("null")) {
							
							DriverTestcase.logger.log(LogStatus.PASS,"No changes made to notification management");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
							Thread.sleep(4000);
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
							Thread.sleep(3000);
							Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
							Thread.sleep(3000);
							DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
						}
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
						Log.info("proactive monitoring check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"proactive monitoring is edited and gets selected");
						
						
						if(notificationManagement.equalsIgnoreCase("null")) {
							
							DriverTestcase.logger.log(LogStatus.PASS,"No changes made to notification management");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
							Thread.sleep(2000);
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
							Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
							DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
						}
					}
					
					
				}

				else if (ProactiveMonitoring.equalsIgnoreCase("no")) {
					
					if(proactivemonitor) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
						Log.info("Proactive monitoring check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"proactive monitoring is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "proactive monitoring was not selected during service creation and it remains unselected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for 'Proactive monitoring' chekbox");
			}
			
			
			
		
		//Delivery channel
			if (!deliveryChannel.equalsIgnoreCase("null")) {
				
//				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_xbutton")));
//				Thread.sleep(2000);

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));
				
				DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Delivery Channel' dropdown is "+deliveryChannel);
				System.out.println("Delivery channel dropdown value is edited as expected");

			}else {
				
				DriverTestcase.logger.log(LogStatus.PASS, "Delivery channel dropdown value is not edited");
				
			}

			
			//VPN topology	
			if(vpnTopology.equals("Point-to-Point")) {
				
				if(intermediateTechnology.equalsIgnoreCase("null")) {
					
					DriverTestcase.logger.log(LogStatus.PASS, "No changes has made for 'Intermediate Technology' field");
					
				}else {
					
					getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).clear();
					Thread.sleep(3000);
					
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
							intermediateTechnology);
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Intermediate Technology' field is "+ intermediateTechnology);
					
				}
			
				if(CircuitReference.equalsIgnoreCase("null")) {
					
					DriverTestcase.logger.log(LogStatus.PASS, "No changes has made for 'Circuit reference' field ");
					
				}else {
					
					getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")), CircuitReference);
					
					DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Circuit reference' field is "+CircuitReference);
				}
				
				

				if(CircuitType.equalsIgnoreCase("null")) {
					
					DriverTestcase.logger.log(LogStatus.PASS, "No changes has made for Circuit Type field");
					
				}else {
					
					Clickon(getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input"));
					
					DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Circuit Type' is "+ CircuitType);
					
				}
				

				
				
			}
			
			else if(vpnTopology.equals("Hub&Spoke") || vpnTopology.equals("E-PN (Any-to-Any)")) {

				
				if(CircuitReference.equalsIgnoreCase("null")) {
					
					DriverTestcase.logger.log(LogStatus.PASS, "No changes has made for Circuit reference field");
					
				}else {
					
					getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
					Thread.sleep(3000);

					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")), CircuitReference);
					Thread.sleep(3000);
					
					DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Circuit reference' field is "+CircuitReference);
				}
				
			}
			
			
			DriverTestcase.logger.log(LogStatus.PASS,"Values has been Edited under Lanlink Metro subtype under Lanlink service");
			
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
	 }
	 
	 
	 public void EditLanlink_outbandmanagement(String application, String ServiceIdentificationNumber,
				String SelectSubService, String Interfacespeed, String EndpointCPE, String Email, String PhoneContact,
				String remark, String Performancereporting, String ProactiveMonitoring, String ENNIcheckBox,
				String deliveryChannel, String Manageconnectiondropdown, String notificationManagement)
				throws InterruptedException, IOException, DocumentException {
			
			
		//Service Identification		
			if(!ServiceIdentificationNumber.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).clear();
				Thread.sleep(2000);
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
						ServiceIdentificationNumber);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Service Identification' field is "+ ServiceIdentificationNumber);
				
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Service Identification field");
			}
			
			
		//Endpoint CPE	
			if(!EndpointCPE.equalsIgnoreCase("null")) {
				
				boolean endpoint=getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isSelected();
				Thread.sleep(2000);
				
				if (EndpointCPE.equalsIgnoreCase("yes")) {

					if(endpoint) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Endpoint CPE is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"Endpoint CE is selected as Expected");
					}
					
					
				}

				else if (EndpointCPE.equalsIgnoreCase("no")) {
					
					if(endpoint) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"Endpoint CE is unselected as Expected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "Endpoint CPE was not selected during service creation and it remains unselected as expected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for EndPoint CPE chekbox as expected");
			}
			
		//Email	
			if(!Email.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Email")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Email' field is "+ Email);

			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Email field");
			}
			
		
		//Phone contact	
			if(!PhoneContact.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Phone contact' field is " + PhoneContact);
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Phone contact field");
			}
			
			
		//Remark	
			if(!remark.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Remark")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Remark' field is "+ remark);
				
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Remark field");
		}
		
		
			
		//Performance Reporting	
			if(!Performancereporting.equalsIgnoreCase("null")) {
				
				boolean perfmmonitr=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
				Thread.sleep(2000);
				
				if (Performancereporting.equalsIgnoreCase("yes")) {

					if(perfmmonitr) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting checkbox is not edited and it is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
						Log.info("Performance Reporting check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"Performance Reporting is selected");
					}
					
					
				}

				else if (Performancereporting.equalsIgnoreCase("no")) {
					
					if(perfmmonitr) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
						Log.info("Performance Reporting check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"Performance Reporting is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting is not edited and it remains unselected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for Performance Reporting chekbox");
			}




			//Proactive monitoring
				if(!ProactiveMonitoring.equalsIgnoreCase("null")) {
					
					boolean proactivemonitor=getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
					Thread.sleep(2000);
					
					if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

						if(proactivemonitor) {
							
							DriverTestcase.logger.log(LogStatus.PASS, "Proactive monitoring is not edited and it is already Selected while creating");
							

							if(notificationManagement.equalsIgnoreCase("null")) {
								
								DriverTestcase.logger.log(LogStatus.PASS,"No changes made to notification management");
								
							}else {
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
								Thread.sleep(4000);
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
								Thread.sleep(3000);
								Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
								Thread.sleep(3000);
								DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
							}
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
							Log.info("proactive monitoring check box is selected");
							DriverTestcase.logger.log(LogStatus.PASS,"proactive monitoring is edited and gets selected");
							
							
							if(notificationManagement.equalsIgnoreCase("null")) {
								
								DriverTestcase.logger.log(LogStatus.PASS,"No changes made to notification management");
								
							}else {
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
								Thread.sleep(2000);
								
								Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
								Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
								DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
							}
						}
						
						
					}

					else if (ProactiveMonitoring.equalsIgnoreCase("no")) {
						
						if(proactivemonitor) {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
							Log.info("Proactive monitoring check box is unselected");
							DriverTestcase.logger.log(LogStatus.PASS,"proactive monitoring is edited and gets unselected");
							
						}else {
							DriverTestcase.logger.log(LogStatus.PASS, "proactive monitoring was not selected during service creation and it remains unselected");
						}
						
					}
				}else {
					DriverTestcase.logger.log(LogStatus.PASS,"No changes made for 'Proactive monitoring' chekbox");
				}
			
		
			
		//ENNI chekcbox	
			if(!ENNIcheckBox.equalsIgnoreCase("null")) {
				
				boolean enni=getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")).isSelected();
				Thread.sleep(2000);
				
				if (ENNIcheckBox.equalsIgnoreCase("yes")) {

					if(enni) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "ENNI checkbox is not edited and it is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")));
						Log.info("ENNI check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"ENNI is selected");
					}
					
					
				}

				else if (ENNIcheckBox.equalsIgnoreCase("no")) {
					
					if(enni) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")));
						Log.info("ENNI check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"ENNI is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "ENNI is not edited and it remains unselected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for ENNI chekbox");
			}
	
			
		//Delivery channel
				if (!deliveryChannel.equalsIgnoreCase("null")) {
					
//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_xbutton")));
//					Thread.sleep(2000);

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));
					
					DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Delivery Channel' dropdown is "+deliveryChannel);
					System.out.println("Delivery channel dropdown value is edited as expected");

				}else {
					
					DriverTestcase.logger.log(LogStatus.PASS, "Delivery channel dropdown value is not edited");
					
				}

			if (!Manageconnectiondropdown.equalsIgnoreCase("null")) {

				getwebelement(xml.getlocator("//locators/" + application + "/managementconnection")).clear();
				Thread.sleep(2000);

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementconnection")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[contains(text(),'" + Manageconnectiondropdown + "')]"));
				DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Manage Connection' dropdown is "+Manageconnectiondropdown);
				System.out.println("Manage Connection dropdown value is edited as expected");

			}else {
				
				DriverTestcase.logger.log(LogStatus.PASS, "Manage Connection dropdown value is not edited");
				
			}

			

			DriverTestcase.logger.log(LogStatus.PASS,"Values has been Edited under lanlink outbandmanagement service subtype");
			
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

		}
	 
	 
	 
	 public void EditLanlink_OLO(String application, String ServiceIdentificationNumber, String SelectSubService,
				String Interfacespeed, String EndpointCPE, String Email, String PhoneContact, String remark,
				String PerformanceMonitoring, String ProactiveMonitoring, String ENNIcheckBox, String deliveryChannel,
				String ManagementOrder, String vpnTopology, String intermediateTechnology, String CircuitReference,
				String CircuitType, String notificationManagement) throws InterruptedException, IOException, DocumentException {

		
		//Service Identification		
			if(!ServiceIdentificationNumber.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).clear();
				Thread.sleep(2000);
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
						ServiceIdentificationNumber);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Service Identification' field is "+ ServiceIdentificationNumber);
				
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Service Identification field");
			}
			
			
		//Endpoint CPE	
			if(!EndpointCPE.equalsIgnoreCase("null")) {
				
				boolean endpoint=getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isSelected();
				Thread.sleep(2000);
				
				if (EndpointCPE.equalsIgnoreCase("yes")) {

					if(endpoint) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Endpoint CPE is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"Endpoint CE is selected as Expected");
					}
					
					
				}

				else if (EndpointCPE.equalsIgnoreCase("no")) {
					
					if(endpoint) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"Endpoint CE is unselected as Expected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "Endpoint CPE was not selected during service creation and it remains unselected as expected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for EndPoint CPE chekbox as expected");
			}
			
		//Email	
			if(!Email.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Email")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Email' field is "+ Email);

			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Email field");
			}
			
		
		//Phone contact	
			if(!PhoneContact.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Phone contact' field is " + PhoneContact);
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Phone contact field");
			}
			
			
		//Reamrk	
			if(!remark.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Remark")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Remark' field is "+ remark);
				
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, "No changes made for Remark field");
		}
			
			
		//Performance Monitoring	
			if(!PerformanceMonitoring.equalsIgnoreCase("null")) {
				
				boolean perfmmonitr=getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")).isSelected();
				Thread.sleep(2000);
				
				if (PerformanceMonitoring.equalsIgnoreCase("yes")) {

					if(perfmmonitr) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Performance Monitor checkbox is not edited and it is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
						Log.info("Performance monitor check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"Performance monitor is selected");
					}
					
					
				}

				else if (PerformanceMonitoring.equalsIgnoreCase("no")) {
					
					if(perfmmonitr) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
						Log.info("Performance Monitor check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"Performance Monitor is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "Performance Monitor is not edited and it remains unselected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for Performance Monitor chekbox");
			}



		//Proactive monitoring
			if(!ProactiveMonitoring.equalsIgnoreCase("null")) {
				
				boolean proactivemonitor=getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
				Thread.sleep(2000);
				
				if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

					if(proactivemonitor) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Proactive monitoring is not edited and it is already Selected while creating");
						

						if(notificationManagement.equalsIgnoreCase("null")) {
							
							DriverTestcase.logger.log(LogStatus.PASS,"No changes made to notification management");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
							Thread.sleep(4000);
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
							Thread.sleep(3000);
							Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
							Thread.sleep(3000);
							DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
						}
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
						Log.info("proactive monitoring check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"proactive monitoring is edited and gets selected");
						
						
						if(notificationManagement.equalsIgnoreCase("null")) {
							
							DriverTestcase.logger.log(LogStatus.PASS,"No changes made to notification management");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
							Thread.sleep(2000);
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
							Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
							DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
						}
					}
					
					
				}

				else if (ProactiveMonitoring.equalsIgnoreCase("no")) {
					
					if(proactivemonitor) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
						Log.info("Proactive monitoring check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"proactive monitoring is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "proactive monitoring was not selected during service creation and it remains unselected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for 'Proactive monitoring' chekbox");
			}
			
			
			
	
			//Delivery channel
				if (!deliveryChannel.equalsIgnoreCase("null")) {
					
//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_xbutton")));
//					Thread.sleep(2000);

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));
					
					DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Delivery Channel' dropdown is "+deliveryChannel);
					System.out.println("Delivery channel dropdown value is edited as expected");

				}else {
					
					DriverTestcase.logger.log(LogStatus.PASS, "Delivery channel dropdown value is not edited");
				}	
				


if(!ENNIcheckBox.equalsIgnoreCase("null")) {

boolean eni=getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")).isSelected();
Thread.sleep(2000);

if (ENNIcheckBox.equalsIgnoreCase("yes")) {

	if(eni) {
		
		DriverTestcase.logger.log(LogStatus.PASS, "ENNI checkbox is already Selected while creating");
		
	}else {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")));
		Log.info("ENNI check box is selected");
		DriverTestcase.logger.log(LogStatus.PASS,"ENNI checkbox is selected as Expected");
	}
	
	
}

else if (ENNIcheckBox.equalsIgnoreCase("no")) {
	
	if(eni) {
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/ENNIcheckbox")));
		Log.info("ENNI check box is unselected");
		DriverTestcase.logger.log(LogStatus.PASS,"ENNI checkbox is unselected as Expected");
		
	}else {
		DriverTestcase.logger.log(LogStatus.PASS, "ENNI checkbox was not selected during service creation and it remains unselected as expected");
	}
	
}
}else {
DriverTestcase.logger.log(LogStatus.PASS,"No changes made for ENNI chekbox as expected");
}

			

//management order	
if (!ManagementOrder.equalsIgnoreCase("null")) {

getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")).clear();
Thread.sleep(2000);

SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/managementOrder")), ManagementOrder);

DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Management Order' field is "+ ManagementOrder);

}else {

DriverTestcase.logger.log(LogStatus.PASS, "No changes has been made to Management order field as expected ");
}


//VPN topology	
if(vpnTopology.equals("Point-to-Point")) {

if(intermediateTechnology.equalsIgnoreCase("null")) {
	
	DriverTestcase.logger.log(LogStatus.PASS, "No changes has made for 'Intermediate Technology' field");
	
}else {
	
	getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).clear();
	Thread.sleep(3000);
	
	SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
			intermediateTechnology);
	Thread.sleep(3000);
	DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Intermediate Technology' field is "+ intermediateTechnology);
	
}

if(CircuitReference.equalsIgnoreCase("null")) {
	
	DriverTestcase.logger.log(LogStatus.PASS, "No changes has made for 'Circuit reference' field ");
	
}else {
	
	getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
	Thread.sleep(3000);

	SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")), CircuitReference);
	
	DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Circuit reference' field is "+CircuitReference);
}



if(CircuitType.equalsIgnoreCase("null")) {
	
	DriverTestcase.logger.log(LogStatus.PASS, "No changes has made for Circuit Type field");
	
}else {
	
	Clickon(getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input"));
	
	DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Circuit Type' is "+ CircuitType);
	
}




}

else if(vpnTopology.equals("Hub&Spoke") || vpnTopology.equals("E-PN (Any-to-Any)")) {


if(CircuitReference.equalsIgnoreCase("null")) {
	
	DriverTestcase.logger.log(LogStatus.PASS, "No changes has made for Circuit reference field");
	
}else {
	
	getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
	Thread.sleep(3000);

	SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")), CircuitReference);
	Thread.sleep(3000);
	
	DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Circuit reference' field is "+CircuitReference);
}

}


	DriverTestcase.logger.log(LogStatus.PASS,"Values has been Edited for 'OLO - (GCR/EU)' subtype under LALINK Service");
		
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

		}

	 
	
	 
   public void syncservices(String application) throws InterruptedException, DocumentException {
	   
	   WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
	   
	   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_actiondropdown")));
	   Thread.sleep(3000);
	   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_sysnchronizelink")));
	   Thread.sleep(3000);
	   
	   scrollToTop();
	   Thread.sleep(3000);
	   
	   boolean syncSuccessMessage=getwebelement(xml.getlocator("//locators/" + application + "/alertForSynchronize")).isDisplayed();
	   if(syncSuccessMessage) {
		   
		   String actualmsg=getwebelement(xml.getlocator("//locators/" + application + "/alertMSG_synchronize")).getAttribute("value");
		   DriverTestcase.logger.log(LogStatus.PASS, " success message for synchronize displays as: "+actualmsg);
		   System.out.println( " success message for synchronize displays as: "+actualmsg);
		   
	   }else {
		   DriverTestcase.logger.log(LogStatus.FAIL, " Success message did not display after clicking on 'Synchronize' button");
		   System.out.println(" Success message did not display after clicking on 'Synchronize' button");
	   }
   }
   
   
   	public void shownewInfovista(String application) throws InterruptedException, DocumentException {
	  
   		WebElement orderPanel= getwebelement(xml.getlocator("//locators/" + application + "/viewServicepage_OrderPanel"));
		ScrolltoElement(orderPanel);
		Thread.sleep(3000);
   		
	   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_actiondropdown")));
	   Thread.sleep(3000);
	   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_infovistareport")));
	   Thread.sleep(6000);
	   
	   
	   //Switch to new tab
	   List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
	   driver.switchTo().window(browserTabs .get(1));
	   Thread.sleep(15000);


	   // Get Tab name
	   String pageTitle=driver.getTitle();
	   String expectedPageName= "SSO login Page";
	   
	   sa.assertEquals(pageTitle, expectedPageName, " on clicking 'Show Infovista link', it got naviagted to "+pageTitle);
	   
	
	  
	  DriverTestcase.logger.log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
	   Thread.sleep(3000);
	   
	   DriverTestcase.logger.log(LogStatus.PASS, "show info vista page actual title: "+pageTitle );
	   DriverTestcase.logger.log(LogStatus.PASS, "show info vista page expected title: "+ expectedPageName);
	   
	   //Get back to prevoius tab
//	   driver.close();
	   driver.switchTo().window(browserTabs.get(0)); 
	   
//	   sa.assertAll();
   		
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
		   
		   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_actiondropdown")));
		   Thread.sleep(3000);
		   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Editservice_managesubnets")));
		   Thread.sleep(3000);
		   
		   Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Backbutton")));
		   Thread.sleep(3000);
		   
	   }
	   	
	public void verifysuccessmessageforDeviceCreation() throws InterruptedException {
		
		boolean devicecreationmsg=getwebelement("//span[contains(text(),'Site device created successfully')]").isDisplayed();
		sa.assertTrue(devicecreationmsg, " 'Site device created successfully' message is not getting displayed in viewdevice page after creating device");
		if(devicecreationmsg) {
			DriverTestcase.logger.log(LogStatus.PASS, " ' Site device created successfully' ,message is displaying as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL," 'Site device created successfully' message is not getting displayed in viewdevice page after creating device' ");
		}
		
		
	}
	
	
	
public void verifysuccessmessageforDeviceUpdation() throws InterruptedException {
		
		boolean devicecreationmsg=getwebelement("//span[contains(text(),'Site device updated successfully')]").isDisplayed();
		sa.assertTrue(devicecreationmsg, " 'Site device updated successfully' message is not getting displayed in viewdevice page after creating device");
		if(devicecreationmsg) {
			DriverTestcase.logger.log(LogStatus.PASS, " ' Site device updated successfully' ,message is displaying as expected");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL," 'Site device updated successfully' message is not getting displayed in viewdevice page after creating device' ");
		}
		
		
	}
	
	public void verifyAddcpedevicepageforIntermediatEquipmetn(String application) throws InterruptedException, DocumentException {
		
		DriverTestcase.logger.log(LogStatus.INFO, "Verifying fields for CPE device under Intermediate Equipment");
		
		Clickon(getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_adddevicelink")));
		Thread.sleep(3000);

		Thread.sleep(3000);
		try {
		
		String[] Vender= {"AC", "DC"};
		
		String[] powerAlarm= {"AC", "DC"};
		
		String[] Mediaselection= {"Type A","Type B","Type C"};	
		
		
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(3000);
		
		
	
			
			
		//Snmpro Error Message
			boolean snmproErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_snmproerrmsg")).isDisplayed();
			sa.assertTrue(snmproErr, "Snmpro warning message is not displayed ");
			String snmproErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_snmproerrmsg")).getText();
			System.out.println(
					"Snmpro  message displayed as : " + snmproErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for Snmpro field displayed as : " + snmproErrMsg);
			Log.info("Snmpro warning message displayed as : " + snmproErrMsg);
			
			
		//Management Address Error Message
			boolean mangadrsErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg")).isDisplayed();
			sa.assertTrue(mangadrsErr, "Management Addres warning message is not displayed ");
			String mngadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg")).getText();
			System.out.println(
					"Management Addres  message displayed as : " + mngadresErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for Management Addres field displayed as : " + mngadresErrMsg);
			Log.info("Management Addres warning message displayed as : " + mngadresErrMsg);
			
			
		//Power Alarm Error Message
			boolean pwralrmErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).isDisplayed();
			sa.assertTrue(pwralrmErr, "Power Alarm warning message is not displayed ");
			String pwralarmErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).getText();
			System.out.println(
					"Power Alarm  message displayed as : " + pwralarmErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for Power Alarm field displayed as : " + pwralarmErrMsg);
			Log.info("Power Alarm warning message displayed as : " + pwralarmErrMsg);
			
			
		//Media Selection Error Message
			boolean mediaErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).isDisplayed();
			sa.assertTrue(mediaErr, "Media Selection warning message is not displayed ");
			String mediaselectionErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).getText();
			System.out.println(
					"Media Selection  message displayed as : " + mediaselectionErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for Media Selection field displayed as : " + mediaselectionErrMsg);
			Log.info("Media Selection warning message displayed as : " + mediaselectionErrMsg);
			
			
	//MAC Address Error Message
			boolean macErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).isDisplayed();
			sa.assertTrue(macErr, "MAC Address warning message is not displayed ");
			String macadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).getText();
			System.out.println(
					"MAC Address  message displayed as : " + macadresErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for MAC Address field displayed as : " + macadresErrMsg);
			Log.info("MAC Address warning message displayed as : " + macadresErrMsg);
			
			
		//Country Error Message
			boolean countryErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).isDisplayed();
			sa.assertTrue(countryErr, "MAC Address warning message is not displayed ");
			String countryErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).getText();
			System.out.println(
					"Country  message displayed as : " + countryErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for Country field displayed as : " + countryErrMsg);
			Log.info("Country warning message displayed as : " + countryErrMsg);	
		
		
		boolean name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).isDisplayed();
		sa.assertTrue(name, "name field is not available under create device for Equipment");
		
		boolean vender=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")).isDisplayed();
		sa.assertTrue(vender, "Vender/Model dropdown is not available");
			
	
	  try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
	  }catch (Exception e) {
		  e.printStackTrace();
		  DriverTestcase.logger.log(LogStatus.FAIL, "AddCPE device not available");  
	}
			
	  try {
	  List<WebElement> listofvender = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
			if(listofvender.size()>0) {
	
			for (WebElement vendertypes : listofvender) {

				boolean match = false;
				for (int i = 0; i < Vender.length; i++) {
					if (vendertypes.getText().equals(Vender[i])) {
						match = true;
						Log.info("list of vendor under add devices are : " + vendertypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of vender/Model under Add device are: "+vendertypes.getText());
						System.out.println("list of vendor under add devices are : " + vendertypes.getText());
						

					}
					}
				sa.assertTrue(match);
				}
				
			}else {
				System.out.println("dropdown value inside Vender/Model is empty");
				DriverTestcase.logger.log(LogStatus.FAIL, "No values available inside Vender/Model dropdown for adding devices");
			}
			
	  }catch(Exception e) {
		  
		  e.printStackTrace();
		  DriverTestcase.logger.log(LogStatus.FAIL, "Failure at vendor dropdown");
		  
	  }
      
			
			boolean snmpro=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).isDisplayed();
			sa.assertTrue(snmpro, "Snmpro field under add device is not available");
			
			boolean managementaddres=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).isDisplayed();
			sa.assertTrue(managementaddres, "Management Address field under add device is not available");
			
			boolean mepid=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).isDisplayed();
			sa.assertTrue(mepid, "Mepid field under add device is not available");
			
			boolean powralrm=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")).isDisplayed();
			sa.assertTrue(powralrm, "The poweralarm dropdown under add device is not available");
			
			
		try {	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Poweralarm dropdown field is not available");
		}
			
		try {
		List<WebElement> listofalarm = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

		if(listofalarm.size()>0) {	
			for (WebElement alarmtypes : listofalarm) {

				boolean match = false;
				for (int i = 0; i < powerAlarm.length; i++) {
					if (alarmtypes.getText().equals(powerAlarm[i])) {
						match = true;
						Log.info("list of power alarm under add devices are : " + alarmtypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of powerAlarm under Add device are: "+alarmtypes.getText());

					}
					}
				 sa.assertTrue(match);
				}
			   
			}else {
				System.out.println("dropdown value inside Vender/Model is empty");
				DriverTestcase.logger.log(LogStatus.FAIL, "No values available inside power alarm dropdown for adding devices");
			}
		}catch(Exception e) {
			  
			  e.printStackTrace();
			  DriverTestcase.logger.log(LogStatus.FAIL, "value mismatch for poweralarm dropdown");
			  
		  }
			
			boolean media=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")).isDisplayed();
			sa.assertTrue(media, "Media selection dropdwon under add devices is not available");
			
		try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Mediaselection dropdown is not available");
		}
		try {	
		List<WebElement> listofmedia = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

		if(listofmedia.size()>0) {
			for (WebElement mediatypes : listofmedia) {

				boolean match = false;
				for (int i = 0; i < Mediaselection.length; i++) {
					if (mediatypes.getText().equals(Mediaselection[i])) {
						match = true;
						Log.info("list of Media selection under add devices are : " + mediatypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of media selection under Add device are: "+mediatypes.getText());
					}
					}
				sa.assertTrue(match);
				}
				
			}else {
				System.out.println("dropdown value inside Vender/Model is empty");
				DriverTestcase.logger.log(LogStatus.FAIL, "No values available inside Media selection dropdown for adding devices");
			}
		}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "FAilure at Media selection dropdown");
			}
			
			
			boolean masAddrss=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).isDisplayed();
			sa.assertTrue(masAddrss, "MAC Address field under add device is not available");
			
//		try {
//			boolean serial=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).isDisplayed();
//			sa.assertTrue(serial, "Serial number field is not available");
//			sa.assertAll();
//			DriverTestcase.logger.log(LogStatus.PASS,"serial number field is available under create device for Equipment" );
//		}catch(AssertionError e) {
//			e.printStackTrace();
//			DriverTestcase.logger.log(LogStatus.FAIL,"serial number field is not available under create device for Equipment" );
//		}
			
//		    try {
//			boolean lanlink=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();
//			sa.assertTrue(lanlink, "Lanlink forwarding chckbox under add device is not available");
//			sa.assertAll();
//			DriverTestcase.logger.log(LogStatus.PASS,"link lost forwarding checkbox is available under create device for Equipment" );
//			}catch(AssertionError e) {
//				e.printStackTrace();
//				DriverTestcase.logger.log(LogStatus.FAIL,"link lost forwarding checkbox is not available under create device for Equipment" );
//			}
			
			
		//Country dropdown
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_countrydiv")));
			Thread.sleep(5000);
			System.out.println("Clicked on Country dropdown");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Country dropdown");
			Log.info("Clicked on Country dropdown");

			List<WebElement> cntrylist = driver.findElements(By.xpath("//span[@role='option']"));
			for (WebElement countrylist : cntrylist) {

				System.out.println("Available Country name is : " + countrylist.getText().toString());
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Available Country name is : " + countrylist.getText().toString());
				Log.info("Available Country name is :" + countrylist.getText().toString());
			}

		//City dropdown
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip__citydiv")));
			Thread.sleep(5000);
			System.out.println("Clicked on City dropdown");
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on City dropdown");
			Log.info("Clicked on City dropdown");

			List<WebElement> citylist = driver.findElements(By.xpath("//span[@role='option']"));
			for (WebElement ctylist : citylist) {

				System.out.println("Available City name is : " + ctylist.getText().toString());
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Available City name is : " + ctylist.getText().toString());
				Log.info("Available City name is :" + ctylist.getText().toString());
			}
			
		//Select City toggle button
			try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citytogglebutton")));
			DriverTestcase.logger.log(LogStatus.PASS, "cliked on 'Select City' toggle button");
			}catch(NoSuchElementException e) {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Select City' toggle button is not available");
			}
			
		//City name
			boolean cityname=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield")).isDisplayed();
			sa.assertTrue(cityname, " 'City name' field is not getting displyed");
			
		//City Code
			boolean citycode=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield")).isDisplayed();
			sa.assertTrue(citycode, " 'City Code' field is not getting displyed");
				
				
		//Site name
			boolean sitename=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield")).isDisplayed();
			sa.assertTrue(sitename, " 'Site name' field is not getting displyed");
					
		//Site Code
			boolean sitecode=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield")).isDisplayed();
			sa.assertTrue(sitecode, " 'Site Code' field is not getting displyed");
						
						
		//Premise name
			boolean premisename=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield")).isDisplayed();
			sa.assertTrue(premisename, " 'Premise name' field is not getting displyed");
							
		//Premise Code
			boolean premisecode=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield")).isDisplayed();
			sa.assertTrue(premisecode, " 'Premise Code' field is not getting displyed");
								
		//Select City toggle button to get site dropdown and premise dropdown
				try {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_disabledCityToggleButton")));
//				DriverTestcase.logger.log(LogStatus.PASS, "cliked on 'Select City' toggle button");
				}catch(NoSuchElementException e) {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Select City' toggle button is not available");
				}	
			
		//Site dropdown
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitediv")));
				Thread.sleep(5000);
				System.out.println("Clicked on Site dropdown");
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Site dropdown");
				Log.info("Clicked on Site dropdown");

				List<WebElement> stelist = driver.findElements(By.xpath("//span[@role='option']"));
				for (WebElement sitelist : stelist) {

					System.out.println("Available site name is : " + sitelist.getText().toString());
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step : Available Site name is : " + sitelist.getText().toString());
					Log.info("Available Site name is :" + sitelist.getText().toString());
				}
				
				
				
		//Premise dropdown
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisediv")));
				Thread.sleep(5000);
				System.out.println("Clicked on Premise dropdown");
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Premise dropdown");
				Log.info("Clicked on Premise dropdown");

				List<WebElement> prmlist = driver.findElements(By.xpath("//span[@role='option']"));
					for (WebElement premiselist : prmlist) {

					System.out.println("Available Premise name is : " + premiselist.getText().toString());
					DriverTestcase.logger.log(LogStatus.PASS,
							"Step : Available Premise name is : " + premiselist.getText().toString());
					Log.info("Available Premise name is :" + premiselist.getText().toString());
				}		
			
				
			boolean Ok=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")).isDisplayed();
			sa.assertTrue(Ok, "OK button under add device is not available");
			
			boolean cancel=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_cancelbutton")).isDisplayed();
			sa.assertTrue(cancel, "cancel button under add device is not available");
	
			
			
			
		
		sa.assertAll();
		
		DriverTestcase.logger.log(LogStatus.PASS, "Fields successfully verified for Add site order");
		}catch(AssertionError e) {
			
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
			
		}
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_cancelbutton")));
			 
		
	}
	
	public void existingCity(String application, String existingcityselectionmode, String city) throws InterruptedException, DocumentException {
		
		//Existing City	
				if(existingcityselectionmode.equalsIgnoreCase("yes")) {
					
					if(city.equalsIgnoreCase("null")) {
						
						DriverTestcase.logger.log(LogStatus.PASS, " No values selected under City dropdown");
						
					}
					else {
						//City dropdown
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip__citydiv")));
						Thread.sleep(5000);
						System.out.println("Clicked on City dropdown");
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on City dropdown");

						List<WebElement> ctylist = driver.findElements(By.xpath("//span[@role='option']"));
						for (WebElement citylist : ctylist) {

							System.out.println("Available City name is : " + citylist.getText().toString());
							DriverTestcase.logger.log(LogStatus.PASS,
									"Step : Available City name is : " + citylist.getText().toString());
						}


						// click on City
						WebElement cty = driver.findElement(By.xpath("//div[contains(text(),'" + city + "')]"));
						System.out.println("Selected City dropdowm is : " + cty.getText().toString());
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected City dropdowm is : " + cty.getText().toString());
						cty.click();
						Thread.sleep(5000);	
					}
				}else {
					DriverTestcase.logger.log(LogStatus.PASS, " Existing city value is not selected");
				}	
		
	}
	
	
	public void newcity(String application, String newcityselectionmode, String cityname, String citycode) throws InterruptedException, IOException, DocumentException {
		
		//New City	
				if(newcityselectionmode.equalsIgnoreCase("yes")) {
					
					
					if(cityname.equalsIgnoreCase("null")) {
						DriverTestcase.logger.log(LogStatus.PASS, " value for City name field  is not added");
					}else {
						//City Name Field	
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield")), cityname);
						Thread.sleep(5000);
						String citynme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield")).getAttribute("value");
						Thread.sleep(3000);
						System.out.println("Entered City Name is : " + citynme);
						DriverTestcase.logger.log(LogStatus.PASS,
								"Step : Entered City Name is : " + citynme);
					}
					
					
					if(citycode.equalsIgnoreCase("null")) {
						DriverTestcase.logger.log(LogStatus.PASS, " value for City Code field  is not added");
					}else {
						
						//City Code Field	
							SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield")), citycode);
							Thread.sleep(5000);
							String citycde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield")).getAttribute("value");
							Thread.sleep(3000);
							System.out.println("Entered City Code is : " + citycde);
							DriverTestcase.logger.log(LogStatus.PASS,
									"Step : Entered City Code is : " + citycde);
							
					}	
					
				}
				else{
					DriverTestcase.logger.log(LogStatus.PASS, " Add new city is not selected");
				}
				
	}
	
	public void addCityToggleButton(String application) throws InterruptedException, DocumentException {
		
		//Add city Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citytogglebutton")));
		Thread.sleep(5000);
	}

	
	public void existingSite(String application, String existingsiteselectionmode, String site) throws InterruptedException, DocumentException {
		//Existing Site
		 if(existingsiteselectionmode.equalsIgnoreCase("yes")) {
			 
			 if(site.equalsIgnoreCase("null")) {
				 
				 DriverTestcase.logger.log(LogStatus.PASS, " No values selected under Site dropdown");
				 
			 }else {
				 
				//Site dropdown
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitediv")));
					Thread.sleep(5000);
					System.out.println("Clicked on Site dropdown");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Site dropdown");

					List<WebElement> stelist = driver.findElements(By.xpath("//span[@role='option']"));
					for (WebElement sitelist : stelist) {

						System.out.println("Available Site name is : " + sitelist.getText().toString());
						DriverTestcase.logger.log(LogStatus.PASS,
								"Step : Available Site name is : " + sitelist.getText().toString());
					}


					// click on Site
					WebElement ste = driver.findElement(By.xpath("//div[contains(text(),'" + site + "')]"));
					System.out.println("Selected Site dropdown is : " + ste.getText().toString());
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected Site dropdown is : " + ste.getText().toString());
					ste.click();
					Thread.sleep(5000);
			 } 
			 
		 }else {
			 DriverTestcase.logger.log(LogStatus.PASS, " Existing Site is not selected");
		 }
		
		
	}
	
	
	public void newSite(String application, String newsiteselectionmode, String sitename, String sitecode) throws InterruptedException, IOException, DocumentException {
		
		//New site 
		 if(newsiteselectionmode.equalsIgnoreCase("yes")) {
			 
			if(sitename.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " value for Site name field  is not entered");
			}else {
				//Site Name Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield")), sitename);
				Thread.sleep(5000);
				String sitenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Site Name is : " + sitenme);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Entered Site Name is : " + sitenme);
			}
			
			if(sitecode.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " value for Site code field  is not entered");
			}else {
				
				//Site Code Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield")), sitecode);
				Thread.sleep(5000);
				String sitecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Site Code is : " + sitecde);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Entered Site Code is : " + sitecde);
				
				
			}	
			 
		 }else {
			 DriverTestcase.logger.log(LogStatus.PASS, " Add new city is not selected");
		 }
	}
	
	
public void newSite_ClickOnSiteTogglebutton(String application, String newsiteselectionmode, String sitename, String sitecode) throws InterruptedException, IOException, DocumentException {
		
		//New site 
		 if(newsiteselectionmode.equalsIgnoreCase("yes")) {
			 
			if(sitename.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " value for Site name field  is not entered");
			}else {
				//Site Name Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield_sitetogglebutton")), sitename);
				Thread.sleep(5000);
				String sitenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield_sitetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Site Name is : " + sitenme);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Entered Site Name is : " + sitenme);
			}
			
			if(sitecode.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " value for Site code field  is not entered");
			}else {
				
				//Site Code Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield_sitetogglebutton")), sitecode);
				Thread.sleep(5000);
				String sitecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield_sitetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Site Code is : " + sitecde);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Entered Site Code is : " + sitecde);
				
				
			}	
			 
		 }else {
			 DriverTestcase.logger.log(LogStatus.PASS, " Add new city is not selected");
		 }
	}
	
	public void addSiteToggleButton(String application) throws InterruptedException, DocumentException {
		
		//Add Site Toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitetogglebutton")));
		Thread.sleep(5000);
	}
	
	
	public void existingPremise(String application, String existingpremiseselectionmode, String premise) throws InterruptedException, DocumentException {
		
		
		//Existing Premise 
			 if(existingpremiseselectionmode.equalsIgnoreCase("yes")) {
				 
				 if(premise.equalsIgnoreCase("null")) {
					 DriverTestcase.logger.log(LogStatus.PASS, " No values selected under Site dropdown");
				 }else {
					//Premise dropdown
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisediv")));
						Thread.sleep(5000);
						System.out.println("Clicked on Premise dropdown");
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Premise dropdown");

						List<WebElement> prmselist = driver.findElements(By.xpath("//span[@role='option']"));
						for (WebElement premiselist : prmselist) {

							System.out.println("Available Premise name is : " + premiselist.getText().toString());
							DriverTestcase.logger.log(LogStatus.PASS,
									"Step : Available Premise name is : " + premiselist.getText().toString());
						}

						// click on Premise
						WebElement prmse = driver.findElement(By.xpath("//div[contains(text(),'" + premise + "')]"));
						System.out.println("Selected Premise dropdown is : " + prmse.getText().toString());
						DriverTestcase.logger.log(LogStatus.PASS, "Step : Selected Premise dropdown is : " + prmse.getText().toString());
						prmse.click();
						Thread.sleep(5000);	
				 } 
			 }else {
				 DriverTestcase.logger.log(LogStatus.PASS, " Existing Site is not selected");
			 }
	}
	
	
	public void newPremise(String application, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, IOException, DocumentException {
		
		//New premise 
		 if(newpremiseselectionmode.equalsIgnoreCase("yes")) {
			 
			if(premisename.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " value for Premise Name field  is not entered");
			}else {
				//Premise Name Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield")), premisename);
				Thread.sleep(5000);
				String prmsenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Name is : " + prmsenme);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Entered Premise Name is : " + prmsenme);
			}
			
			if(premisecode.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " value for Premise code field  is not entered");
			}else {
				//Premise Code Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield")), premisecode);
				Thread.sleep(5000);
				String premisecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Code is : " + premisecde);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Entered Premise Code is : " + premisecde);
			}	
			 
			 
		 }else {
			 DriverTestcase.logger.log(LogStatus.PASS, " Add new Premise is not selected");
		 }
		 
	}
	
	
public void newPremise_clickonSiteToggleButton(String application, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, IOException, DocumentException {
		
		//New premise 
		 if(newpremiseselectionmode.equalsIgnoreCase("yes")) {
			 
			if(premisename.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " value for Premise Name field  is not entered");
			}else {
				//Premise Name Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_sitetogglebutton")), premisename);
				Thread.sleep(5000);
				String prmsenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_sitetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Name is : " + prmsenme);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Entered Premise Name is : " + prmsenme);
			}
			
			if(premisecode.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " value for Premise code field  is not entered");
			}else {
				//Premise Code Field	
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_sitetogglebutton")), premisecode);
				Thread.sleep(5000);
				String premisecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_sitetogglebutton")).getAttribute("value");
				Thread.sleep(3000);
				System.out.println("Entered Premise Code is : " + premisecde);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step : Entered Premise Code is : " + premisecde);
			}	
			 
			 
		 }else {
			 DriverTestcase.logger.log(LogStatus.PASS, " Add new Premise is not selected");
		 }
	}
	

public void newPremise_clickOnPremisetoggleButton(String application, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, IOException, DocumentException {
	
	//New premise 
	 if(newpremiseselectionmode.equalsIgnoreCase("yes")) {
		 
		if(premisename.equalsIgnoreCase("null")) {
			DriverTestcase.logger.log(LogStatus.PASS, " value for Premise Name field  is not entered");
		}else {
			//Premise Name Field	
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_premisetogglebutton")), premisename);
			Thread.sleep(5000);
			String prmsenme=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield_premisetogglebutton")).getAttribute("value");
			Thread.sleep(3000);
			System.out.println("Entered Premise Name is : " + prmsenme);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step : Entered Premise Name is : " + prmsenme);
		}
		
		if(premisecode.equalsIgnoreCase("null")) {
			DriverTestcase.logger.log(LogStatus.PASS, " value for Premise code field  is not entered");
		}else {
			//Premise Code Field	
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_premisetogglebutton")), premisecode);
			Thread.sleep(5000);
			String premisecde=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield_premisetogglebutton")).getAttribute("value");
			Thread.sleep(3000);
			System.out.println("Entered Premise Code is : " + premisecde);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step : Entered Premise Code is : " + premisecde);
		}	
		 
		 
	 }else {
		 DriverTestcase.logger.log(LogStatus.PASS, " Add new Premise is not selected");
	 }
	 
}

	public void addPremiseTogglebutton(String application) throws InterruptedException {
		
		//Add Premise Toggle button
		Clickon(getwebelement("//div[div[label[text()='Add Premise']]]//div[@class='react-switch-bg']"));
		Thread.sleep(5000);
	}

	
	
public void addOverture(String application, String serviceName) throws InterruptedException, DocumentException, IOException {
	
	//Click on Action dropdown
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/circuit_10GigeActiondropdown")));
	Thread.sleep(3000);
	
	//Click on Add Overture Link
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Circuit_10GigEaddOverturelink")));
	Thread.sleep(3000);
	
	
	//Click on Next buttton to find mandatory message
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
	Thread.sleep(3000);
	
	
//Service name warning message
	boolean nameerr = getwebelement(
			xml.getlocator("//locators/" + application + "/Overture_servicenameErrmsg")).isDisplayed();
	sa.assertTrue(nameerr, "Service name mandatory warning under 'Overture Circuit'page is not displayed ");
	String nameErrorwarning = getwebelement(
			xml.getlocator("//locators/" + application + "/Overture_servicenameErrmsg")).getText();
	System.out.println(
			"Name validation message displayed as : " + nameErrorwarning);
	DriverTestcase.logger.log(LogStatus.PASS,
			"Step : Service Name validation message displayed as : " + nameErrorwarning);
	Log.info("Name validation message displayed as : " + nameErrorwarning);
	
	
//service name fields
	boolean nameField = getwebelement(
			xml.getlocator("//locators/" + application + "/Overture_ServiceNameField")).isDisplayed();
	
	if(nameField) {
		System.out.println("Service Name is displaying as expected under Overture page");
		DriverTestcase.logger.log(LogStatus.PASS, " 'Service Name' is field is displaying ");
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Overture_ServiceNameField")), serviceName);
		Thread.sleep(3000);
		WebElement name=getwebelement(xml.getlocator("//locators/" + application + "/Overture_ServiceNameField"));
		
		DriverTestcase.logger.log(LogStatus.PASS, "The value entered under Service name Field is: "+name.getAttribute("value"));
	}else {
		
		System.out.println("Service Name is not displaying under Overture page");
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Service Name' is field is not displaying ");
		
	}
	
//click on Search button
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Overture_searchButton")));
	
	
}


public void configure(String application, String serviceName) throws InterruptedException, DocumentException, IOException {
	
	
}
	

public void City_AddSiteorder(String application, String existingcityselection, String city, String newcityselection,  String xngcityname, String xngcitycode,
		String sitevalue, String CSR_Name, String existingsiteselection, String newsiteselection) throws InterruptedException, DocumentException, IOException {
	
	//Existing City
			if((existingcityselection.equalsIgnoreCase("yes")) & (newcityselection.equalsIgnoreCase("no"))) {

//				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_disabledCitytogglebutton")));
//				 Thread.sleep(5000);
				 
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_City")));
				Thread.sleep(5000);
				Clickon(getwebelement("//div[text()='" + city + "']"));
				Thread.sleep(5000);
				
				DriverTestcase.logger.log(LogStatus.PASS, city+ " is selected under Device Xng City dropdown");
				
			//Existing Site	
				if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {
	    			
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton")));
					Thread.sleep(3000);
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")));
					Thread.sleep(3000);	
					Clickon(getwebelement("//div[text()='" + sitevalue + "']"));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, sitevalue+  " is selected under Physical Site dropdown");
	    		}
				
			//New site
				if((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
					
//					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddsiteOrdr_disabledSitetogglebutton")));
//					Thread.sleep(3000);
					
					if(CSR_Name.equalsIgnoreCase("null")){
						DriverTestcase.logger.log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
						System.out.println("No values provided for mandatory field 'CSR Name'");
						
					}else {
						
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
					}
				}
			
				
				
			}
			else if((existingcityselection.equalsIgnoreCase("no")) & (newcityselection.equalsIgnoreCase("yes"))) {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
				Thread.sleep(3000);
				
				//City name 
				 if(xngcityname.equalsIgnoreCase("null")) {
					 DriverTestcase.logger.log(LogStatus.FAIL, "City name field is a mandatory field and the value is not provided");
				 }else {
				 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname")), xngcityname);
				 Thread.sleep(3000);
				 DriverTestcase.logger.log(LogStatus.PASS, xngcityname+ " is entered in City name field");
				 System.out.println(xngcityname+ " is entered in City name field");
				 Thread.sleep(3000);
				 }
				 
				 //City code
				 if(xngcitycode.equalsIgnoreCase("null")) {
					 DriverTestcase.logger.log(LogStatus.FAIL, "City Code field is a mandatory field and the value is not provided");
					 System.out.println("no values provided for city code text field");
				 }else {
				 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode")), xngcitycode);
				 Thread.sleep(3000);
				 DriverTestcase.logger.log(LogStatus.PASS, xngcitycode+" is entered in City Code field" );
				 }
				 Thread.sleep(8000);
				 
			//Add New Site	 
				 
//				try {
//				 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddsiteOrdr_disabledSitetogglebutton")));
//					Thread.sleep(3000);
//				}catch(Exception e) {
//					e.printStackTrace();
//					DriverTestcase.logger.log(LogStatus.FAIL, " Site disabled toggle button not available");
//				}
				
				try {
					
					if(CSR_Name.equalsIgnoreCase("null")){
						DriverTestcase.logger.log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
						System.out.println(" no values provided for 'CSR Name' text field");
						
					}else {
						
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
					System.out.println(CSR_Name+ " is entered under CSR Name field");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " CSR NAme not performed");
				}
			
			}
			
}


public void validateCity_AddSiteOrder(String application) throws InterruptedException, DocumentException {
	
	
	// City dropdown
	boolean CIty = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_City")).isDisplayed();
	sa.assertTrue(CIty, "City dropdown is not displayed");
	if(CIty) {
		DriverTestcase.logger.log(LogStatus.PASS, " 'City' mandatory dropdown is displaying under 'Add Site Order' page as expected");
	
}else {
DriverTestcase.logger.log(LogStatus.FAIL, " 'City' mandatory dropdown is not available under 'Add Site Order' page");
}

		
	//select city toggle button
	boolean selectcitytoggle=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")).isDisplayed();
	sa.assertTrue(selectcitytoggle, "Select city toggle button for Add Site is not available");
	if(selectcitytoggle) {
		DriverTestcase.logger.log(LogStatus.PASS, " 'Select City' toggle button is displaying under 'Add Site Order' page as expected");
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Select City' toggle button is not avilable under 'Add Site Order' page ");
	}
	
	
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Citytogglebutton")));
	Thread.sleep(5000);
	
	scrolltoend();
	Thread.sleep(3000);
	
	System.out.println("Scrolling down to validate error messgae for City name and city code");
	//Click on Next button to get warning message for XNG City name and XNG City Code text fields
	Clickon(getwebelement("//span[contains(text(),'OK')]"));
	Thread.sleep(5000);
	
	WebElement deviceCountry=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country"));
	ScrolltoElement(deviceCountry);
	Thread.sleep(3000);
	
	System.out.println("scrolling above till device country for validating error message for 'city name ' and 'city code'");
	//XNG City Name Error message	
	boolean xngCitynameErr = getwebelement(
			xml.getlocator("//locators/" + application + "/Addsiteorder_xngcitynameerrmsg")).isDisplayed();
	sa.assertTrue(xngCitynameErr, " 'XNG City Name' warning message is not displayed ");
	if(xngCitynameErr) {
		DriverTestcase.logger.log(LogStatus.PASS, " Warning message for 'XNG City Name' dropdown is displying under 'Add Site order' page as expected ");
		String citynameErrMsg = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_xngcitynameerrmsg")).getText();
		System.out.println(
				"XNG City Name  message displayed as : " + citynameErrMsg);
		DriverTestcase.logger.log(LogStatus.PASS,
				"Step :  validation message for 'XNG City Name' text field displayed as : " + citynameErrMsg);
		Log.info("XNG City Name warning message displayed as : " + citynameErrMsg);
		
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " Warning message for 'XNG City Name' dropdown is not displaying under 'Add Site order' page ");
	}


	
	//XNG City Code Error message	
	boolean xngCitycodeErr = getwebelement(
			xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityCodeerrmsg")).isDisplayed();
	sa.assertTrue(xngCitycodeErr, " 'XNG City Code' warning message is not displayed ");
	if(xngCitycodeErr) {
		DriverTestcase.logger.log(LogStatus.PASS, " Warning message for 'XNG City Code' dropdown is displying under 'Add Site order' page as expected ");
		String cityCodeErrMsg = getwebelement(
				xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityCodeerrmsg")).getText();
		System.out.println(
				"XNG City Name  message displayed as : " + cityCodeErrMsg);
		DriverTestcase.logger.log(LogStatus.PASS,
				"Step :  validation message for 'XNG City Code' text field displayed as : " + cityCodeErrMsg);
		Log.info("XNG City Code warning message displayed as : " + cityCodeErrMsg);
		
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " Warning message for 'XNG City Code' dropdown is not displaying under 'Add Site order' page ");
	}


//xng city name
boolean XNGcityname=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_xngcityname")).isDisplayed();
sa.assertTrue(XNGcityname, "XNG city name field for Add Site is not available");
if(XNGcityname) {
	DriverTestcase.logger.log(LogStatus.PASS, " 'XNG City name field is displaying under 'Add Site order' as expected");
}else {
	DriverTestcase.logger.log(LogStatus.FAIL, " 'XNG City name field is not available under 'Add Site order'");
}

//xng city code
boolean XNGcitycode=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_XNGcitycode")).isDisplayed();
sa.assertTrue(XNGcitycode, "XNG city code field for Add Site is not available");
if(XNGcitycode) {
	DriverTestcase.logger.log(LogStatus.PASS, " 'XNG City code field is displaying under 'Add Site order' as expected");
}else {
	DriverTestcase.logger.log(LogStatus.FAIL, " 'XNG City code field is not available under 'Add Site order'");
}

	
}


public void validateCountry_AddSiteorder(String application) throws InterruptedException, DocumentException {
	
	boolean COuntry=false;
	
	COuntry = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")).isDisplayed();
	sa.assertTrue(COuntry, "Country dropdown is not displayed");
	if(COuntry) {
		DriverTestcase.logger.log(LogStatus.PASS, " 'Country' mandatory dropdown is displaying under 'Add Site Order' page as expected");	
		System.out.println("Country dropdown is displaying");
		
	Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")));
	List<WebElement> listofcountry = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

	if(listofcountry.size()>=1) {
	for (WebElement countrytypes : listofcountry) {
		
				DriverTestcase.logger.log(LogStatus.PASS,"The list of country inside dropdown is: "+countrytypes.getText());
		
	}
}else {
	System.out.println("no values are available inside Country dropdown for Add site order");
	DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside Country dropdown for Add site order");
}

//click on Blank page	
	clickOnBankPage();
	Thread.sleep(3000);
	
}else {
DriverTestcase.logger.log(LogStatus.FAIL, " 'Country' mandatory dropdown is not available under 'Add Site Order' page");
}
	
}



public void Site_AddSiteOrder(String application, String existingsiteselection, String sitevalue, String newsiteselection, String CSR_Name ) throws InterruptedException, IOException, DocumentException {
	
	
	//Existing Site	
		if((existingsiteselection.equalsIgnoreCase("yes")) & (newsiteselection.equalsIgnoreCase("no"))) {

			
			
			if(sitevalue.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "Physical Site field is mandatory and no values are provided" );
			}else {
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")));
			Thread.sleep(3000);	
			Clickon(getwebelement("//div[text()='" + sitevalue + "']"));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, sitevalue+  " is selected under Physical Site dropdown");
			}
		}

		else if ((existingsiteselection.equalsIgnoreCase("no")) & (newsiteselection.equalsIgnoreCase("yes"))) {
				
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton")));
			Thread.sleep(3000);
			
			if(CSR_Name.equalsIgnoreCase("null")){
				DriverTestcase.logger.log(LogStatus.FAIL, "CSR name field is mandatory and no values are provided");
				
			}else {
				
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")), CSR_Name);
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, CSR_Name+ " is entered under CSR Name field");
			}
			} 

}

     public void Countyr_AddSiteOrder(String application, String country) throws InterruptedException, DocumentException {
    	 
    	//Select Existing Country
				if(country.equalsIgnoreCase("null")) {
					
					DriverTestcase.logger.log(LogStatus.FAIL, "Country is a mandatory field and the value is not provided ");
				}else {
				  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country")));
				  Thread.sleep(3000);
				  Clickon(getwebelement("//div[text()='"+ country +"']"));
				  Thread.sleep(3000);
				  DriverTestcase.logger.log(LogStatus.PASS,country+ " has been selected under 'Country' dropdown");
				}
     }

	
     public void validateSite_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
    	 
    	// CSR name field
    	 boolean csr_name=false;
			csr_name = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_CSRname")).isDisplayed();
			sa.assertTrue(csr_name, "CSR_Name field is not displayed");
			if(csr_name) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'CSR Name' text field is displaying under 'Add Site order' page as expected");
				System.out.println("CSR name field is dipslaying as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'CSR Name' text field is not available under 'Add Site order' page");
			}

		// click on site toggle button to check Physical site dropdown
			boolean sitetogglebutton=false;
			sitetogglebutton = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton"))
					.isDisplayed();
			sa.assertTrue(sitetogglebutton, "select Site toggle button is not displayed");
			if(sitetogglebutton) {
				System.out.println("site order toggle button is displaying as expected");
				DriverTestcase.logger.log(LogStatus.PASS, " 'Select Site' toggle button is displaying under 'Add Site Order' page as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Select Site' toggle button is not avilable under 'Add Site Order' page");
			}

			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Sitetogglebutton")));
			Thread.sleep(3000);

	//Check for Error message for physical Site
			scrolltoend();
			Thread.sleep(3000);
			System.out.println("scrolling down to click n OK button to find eror message for site Dropdown");
			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			Thread.sleep(5000);
			
			WebElement deviceCountry=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Country"));
			ScrolltoElement(deviceCountry);
			Thread.sleep(3000);
			
			System.out.println("scrolling up back till device country dropodwn to find error message validation for physical site");
			boolean physicalsiteErr=false;
			physicalsiteErr = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_physicalsiteErrmsg")).isDisplayed();
			sa.assertTrue(physicalsiteErr, "Physical Site dropdown warning is not displayed ");
			if(physicalsiteErr) {
				System.out.println("Physical Site Error message is displaying as expected");
				DriverTestcase.logger.log(LogStatus.PASS, " 'Physite Site' dropdown warning message is displaying under 'Add Site Order' page as expected");
				String physicalsiteErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_physicalsiteErrmsg")).getText();
				System.out.println(
						"Physical Site  message displayed as : " + physicalsiteErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for Physical Site dropdown displayed as : " + physicalsiteErrMsg);
				Log.info("Physical Site validation message displayed as : " + physicalsiteErrMsg);	
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Physical Site' dropdown warning message is not displaying under 'Add Site Order' page");
				System.out.println("Physical site warning message is not displaying");
			}
			
		

	//Physical Site dropdown
			boolean SIte=false;
			SIte = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitedropdown")).isDisplayed();
			sa.assertTrue(SIte, "PhysicalSite dropdown is not displayed");
			if(SIte) {
				System.out.println("Physical Site dropdown is displaying as expected");
				DriverTestcase.logger.log(LogStatus.PASS, " 'physical Site' dropdown is displaying under 'Add Site order' page as expected");

			}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Physical Site' dropdown is not available under 'Add Site Order' page");
	}
     }
     
     
     
     public void validatePerformancereporting_AddSiteOrder(String application) throws InterruptedException, DocumentException, IOException {
    	 
    	 
    	 String[] Performancereporting = { "Follow Service", "no" };

    	// Performance reporting dropdown
    	 boolean performancereport=false;
			performancereport = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")).isDisplayed();
			sa.assertTrue(performancereport, "performance reporting dropdown is not displayed");
			if(performancereport) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Performance reporting' dropdown is displaying under 'Add Site order' as expected");
				Thread.sleep(3000);
				
				//check default value
				String performanceRprtDefaultValues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereportingdefaultvalue")));
				DriverTestcase.logger.log(LogStatus.PASS, performanceRprtDefaultValues+ " is displaying under 'Performance reporting' dropdown by default");
	
				//check list of values inside Performance Reporting drodpown		
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting")));
			List<WebElement> listofperformancereporting = driver
					.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listofperformancereporting.size()>=1) {	
			for (WebElement perfoemancereportingtypes : listofperformancereporting) {
				boolean match = false;
				for (int i = 0; i < Performancereporting.length; i++) {
					if (perfoemancereportingtypes.getText().equals(Performancereporting[i])) {
						match = true;
						Log.info("list of performance reporting : " + perfoemancereportingtypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"list of performance reporting for AddSite order : " + perfoemancereportingtypes.getText());
					}
					
				}
				
				sa.assertTrue(match);

			}
		}else {
			System.out.println("no values are available inside performance reporting dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside performance reporting dropdown for Add site order");
		}
	}else {
		DriverTestcase.logger.log(LogStatus.PASS, " 'Performance reporting' dropdown is not availble under 'Add Site order' ");
	}
     }
     
     
     public void performancereporting_AddSiteOrder(String application, String performReport) throws InterruptedException, DocumentException {
    	 
    	//Perfomance Reporting	
 		if(performReport.equalsIgnoreCase("Null")) {
 			
 			System.out.println("NO changes in 'Performance Reporting' dropdown");
 			DriverTestcase.logger.log(LogStatus.PASS, "Performance reporting value is not provided. 'Follow Service' is selected by default");
 			
 		}else {
 	try {		
 		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_performancereporting_xbutton")));
 		Thread.sleep(3000);
 		Clickon(getwebelement("//div[label[text()='Performance Reporting']]//div[text()='"+ performReport +"']"));
 		Thread.sleep(3000);
 		
 		String actualvalue=getwebelement("(//div[label[text()='Performance Reporting']]//span)[2]").getText();
 		DriverTestcase.logger.log(LogStatus.PASS, actualvalue + " is selected under Performance reporting dropdown");
 	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Performance reporting' dropdown is not displaying under 'Add Site order' page");
		System.out.println( " 'Performance reporting' dropdown is not displaying under 'Add Site order' page");
	}catch(Exception err) {
		err.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " not able to select value under 'Performance reporting' checkbox");
		System.out.println(" not able to select value under 'Performance Reporting' checkbox");
	}
 		}
     }

     
     
     public void validateProactiveMonitoring_AddSiteOrder(String application) throws InterruptedException, DocumentException, IOException {
    	 
    	 String[] Proactivemonitoring = { "Follow Service", "no" };

    	// pro active monitoring
    	 boolean proactivemonitoring=false;
			proactivemonitoring = getwebelement(
					xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")).isDisplayed();
			sa.assertTrue(proactivemonitoring, "pro active monitoring dropdown is not displayed");
			if(proactivemonitoring) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Proactie Monitoring' dropdown is displaying under 'Add Site Order' page as Expected");
				
				//check default value
				String proactiveMonitorDefaultValues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoringdefaultvalue")));
				DriverTestcase.logger.log(LogStatus.PASS, proactiveMonitorDefaultValues+ " is displaying under 'roactive Monitoring' dropdown by default");
	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_proactivemonitoring")));
			List<WebElement> listofproactivemonitoring = driver
					.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
			
		if(listofproactivemonitoring.size()>=1) {	
			for (WebElement proactivemonitoringtypes : listofproactivemonitoring) {

				boolean match = false;
				for (int i = 0; i < Proactivemonitoring.length; i++) {
					if (proactivemonitoringtypes.getText().equals(Proactivemonitoring[i])) {
						match = true;
						Log.info("list of pro active monitoring : " + proactivemonitoringtypes.getText());
						
						DriverTestcase.logger.log(LogStatus.PASS,"The list of proactive monitoring inside dropdown while  adding site order is: "+proactivemonitoringtypes.getText());
					}
			}
				sa.assertTrue(match);

			}
		}else {
			
			System.out.println("no values are available inside pro active monitoring dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside pro active monitoring dropdown for Add site order");
		}
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Proactie Monitoring' dropdown is not available under 'Add Site Order' page ");
	}
     }
     
     
     public void proactiveMonitoring_AddSiteOrder(String application, String ProactiveMonitor) throws InterruptedException, DocumentException {
    	 
    	//Pro active monitoring	
  		if(ProactiveMonitor.equalsIgnoreCase("Null")) {
  			DriverTestcase.logger.log(LogStatus.PASS, "Pro active monitoring value is not provided. 'Follow Service' is selected by default");
  			
  		}else {
  	try {		
  		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsitorder_proactivemonitoring_xbutton")));
  		Thread.sleep(3000);
  		Clickon(getwebelement("//div[label[text()='Proactive Monitoring']]//div[text()='"+ ProactiveMonitor +"']"));
  		Thread.sleep(3000);
  		
  		String actualvalue=getwebelement("(//div[label[text()='Proactive Monitoring']]//span)[2]").getText();
  		DriverTestcase.logger.log(LogStatus.PASS, actualvalue+ " is selected under proactive monitoring dropdown");
  		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Pro active Monitoring' dropdown is not displaying under 'Add Site order' page");
			System.out.println( " 'pro active Monitoring' dropdown is not displaying under 'Add Site order' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " not able to select value under 'Proactive Monitoring' dropdown");
			System.out.println(" not able to select value under 'pro active Monitoring' dropdown");
		}
  		} 
     }
     
     
     
     public void validateSmartsMOnitoring_AddSiteOrder(String application) throws InterruptedException, DocumentException, IOException {
    	 
    	 String[] Smartmonitoring = { "Follow Service", "no" };

    	// smarts monitoring
    	 boolean smartmonitoring=false;
			smartmonitoring = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring"))
					.isDisplayed();
			sa.assertTrue(smartmonitoring, "Smart monitoring dropdown is not displayed");
			if(smartmonitoring) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Smart Monitoring' dropdown is displaying under 'Add Site Order' page as expected");
				//check default value
				String smartmonitorDefaultValues=Gettext(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoringdefaultvalue")));
				DriverTestcase.logger.log(LogStatus.PASS, smartmonitorDefaultValues+ " is displaying under 'Smart Monitoring' dropdown by default");
	
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring")));
			List<WebElement> listofsmartmonitoring = driver
					.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

		if(listofsmartmonitoring.size()>=1) {	
			for (WebElement smartmonitoringtypes : listofsmartmonitoring) {

				boolean match = false;
				for (int i = 0; i < Smartmonitoring.length; i++) {
					if (smartmonitoringtypes.getText().equals(Smartmonitoring[i])) {
						match = true;
						Log.info("list of smart monitoring are : " + smartmonitoringtypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of smart monitoring  inside dropdown while  adding site order is: "+smartmonitoringtypes.getText());
					}
				}
				
				sa.assertTrue(match);
			}
		}else {

			System.out.println("no values are available inside smart monitoring dropdown for Add site order");
			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside smart monitoring dropdown for Add site order");
		}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Smart Monitoring' dropdown is not avilable under 'Add Site Order' page");
			}
     }
     
     
     public void smartsMonitoring_AddSiteOrder(String application, String smartmonitor) throws InterruptedException, DocumentException {
    	 
    			if(smartmonitor.equalsIgnoreCase("null")) {
    				DriverTestcase.logger.log(LogStatus.PASS, "Smart monitoring value is not provided. 'Follow Service' is selected by default");
    			}else {
    		try {		
    			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_smartmonitoring_xbutton")));
    			Thread.sleep(3000);
    			Clickon(getwebelement("//div[label[text()='Smarts Monitoring']]//div[text()='"+ smartmonitor +"']"));
    			Thread.sleep(3000);
    			
    			String actualValue=getwebelement("(//div[label[text()='Smarts Monitoring']]//span)[2]").getText();
    			DriverTestcase.logger.log(LogStatus.PASS, actualValue+ " is selected under Smart monitoring dropdown");
    		}catch(NoSuchElementException e) {
    			e.printStackTrace();
    			DriverTestcase.logger.log(LogStatus.FAIL, " 'Smart Monitoring' dropdown is not displaying under 'Add Site order' page");
    			System.out.println( " 'Smart Monitoring' dropdown is not displaying under 'Add Site order' page");
    		}catch(Exception err) {
    			err.printStackTrace();
    			DriverTestcase.logger.log(LogStatus.FAIL, " not able to select value under 'Smart Monitoring' dropdown");
    			System.out.println(" not able to select value under 'Smart Monitoring' dropdown");
    		}
    			}

     }
     
     
     public void validateSiteAlias_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    		
 		// Site alias Field
    	 boolean sitealias=false; 
try { 				
	sitealias = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias"))
 						.isDisplayed();
 				sa.assertTrue(sitealias, "Site alias field is not displayed");
 				if(sitealias) {
 					DriverTestcase.logger.log(LogStatus.PASS, " 'Site Alias' text field is displaying under 'Add Site order' page as expected");
 				}else {
 					DriverTestcase.logger.log(LogStatus.FAIL, " 'Site Alias' text field is not displaying under 'Add Site order' page");
 				}
     }catch	(Exception e) {
    	 e.printStackTrace();
    	 DriverTestcase.logger.log(LogStatus.FAIL, " 'Site Alias' text field is not displaying under 'Add Site order' page");
     }
     }
     
     
     public void SiteAlias_AddSiteOrder(String application, String siteallias) throws InterruptedException, IOException, DocumentException {
    
    	 if(siteallias.equalsIgnoreCase("null")) {
 					DriverTestcase.logger.log(LogStatus.PASS, "No values entered for 'Site Alias' field");
 				}else {
 			try {		
 				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")), siteallias);
 				Thread.sleep(3000);
 				
 				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_sitealias")).getAttribute("value");
 				DriverTestcase.logger.log(LogStatus.PASS, actualvalue+ " is entered under 'Site Alias' field");
 			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Site Alias' field is not displaying under 'Add Site order' page");
				System.out.println(" 'Site Alias' field is not dispyating under 'Add Site order' page");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'Site Alias' field");
				System.out.println(" Not able to enter value under 'Site Alias' field");
			}
 		}

     }
     
     
     public void validateVlanID_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 boolean vlanid=false;
    	 try {
    				vlanid = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).isDisplayed();
    				sa.assertTrue(vlanid, "VLAN id field is not displayed");
    				if(vlanid) {
    					DriverTestcase.logger.log(LogStatus.PASS, "'VLAN ID' text field is displaying under 'Add Site order' page as expected");
    				}else {
    					DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN ID' text field is not displaying under 'Add Site order' page");
    				}
    	 }catch(Exception e) {
    		 e.printStackTrace();
    		 DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN ID' text field is not displaying under 'Add Site order' page");
    	 }
     }
     
     
     public void VLANid_AddSiteOrder(String application, String VLANid) throws InterruptedException, IOException, DocumentException {
    	 
			if(VLANid.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No values entered for 'Vlan id' field");
			}else {
			try {	
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")), VLANid);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Vlanid")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, actualvalue + " is entered under Vlan id field");
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Vlan Id' field is not displating under 'Add Site order' page");
				System.out.println(" 'Vlan Id' field is not displating under 'Add Site order' page");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'Vlan Id' field");
				System.out.println(" Not able to enter value under 'Vlan Id' field");
			}
			}

     }
     
     
     public void valiadateDCAEnabledsite_AddSieOrder(String application) throws InterruptedException, DocumentException {
    	 
    	 String[] cloudServiceprovider = { "Amazon Web Service", "Microsoft Azure" };
    	 boolean DCAEnabledsite=false;

    	// DCA Enabled site
    				DCAEnabledsite = getwebelement(
    						xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isDisplayed();
    				sa.assertTrue(DCAEnabledsite, "DCA enabled site is not displayed ");
    				if(DCAEnabledsite) {
    					
    				DriverTestcase.logger.log(LogStatus.PASS, " 'DCA Enabled Site' checkbox is displaying under 'Add Site order' page as expected");	
    				boolean DCAselection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isSelected();
    				sa.assertFalse(DCAselection,"DCA checkbox under Addsite order is selected by default");
    				if(DCAselection) {
    					DriverTestcase.logger.log(LogStatus.FAIL, " ' DCA Enabled Site' checkbox should not be selected by default");
    				}else {
    					DriverTestcase.logger.log(LogStatus.PASS, " 'DCA Enabled Site' checkbox is not selected by default as expected");
    							
    				DriverTestcase.logger.log(LogStatus.INFO,"when DCA Enabled site checkbox is selected, Cloud service provider dropdown should occur"
    						+ " Cloud service provider dropdown should have values: "
    						+ "  1) Amazon Web Service "
    						+ "  2) Microsoft Azure");	
    				
    				Thread.sleep(5000);
    			// For Cloud service provider
    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
    				Log.info("DCA site is enabled to add cloud service provider details");
    				Thread.sleep(3000);
    				
    				boolean DCAafterSelection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")).isSelected();
    				if(DCAafterSelection) {
    				DriverTestcase.logger.log(LogStatus.PASS,"DCA site is selected to add cloud service provider details");
    				Thread.sleep(3000);

    				boolean cloudservice = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider"))
    						.isDisplayed();
    				sa.assertTrue(cloudservice, "cloud service provider dropdown is not displayed");
    				if(cloudservice) {
    					DriverTestcase.logger.log(LogStatus.PASS, " 'Cloud Service Provider' dropdown is displaying when 'DCA Enabled Site' checkbox is selected as expected");

    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
    				List<WebElement> listofcloudservices = driver
    						.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
    				
    			if(listofcloudservices.size()>0) {	
    				for (WebElement cloudserviceprovidertypes : listofcloudservices) {

    					boolean match = false;
    					for (int i = 0; i < cloudServiceprovider.length; i++) {
    						if (cloudserviceprovidertypes.getText().equals(cloudServiceprovider[i])) {
    							match = true;
    							Log.info("list of cloud service providers are : " + cloudserviceprovidertypes.getText());
    							DriverTestcase.logger.log(LogStatus.PASS,"The list of cloudservice provider inside dropdown while  adding site order is: "+cloudserviceprovidertypes.getText());
    						}
    					}
    					sa.assertTrue(match);
    				}
    			}else {
    				System.out.println("no values are available inside cloudservice provider dropdown for Add site order");
    				DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside cloudservice provider dropdown for Add site order");
    				
    			}
    				}else {
    					DriverTestcase.logger.log(LogStatus.PASS, " 'Cloud Service Provider' dropdown is not available when 'DCA Enabled Site' checkbox is selected");
    				}
    				
    				}else {
    					DriverTestcase.logger.log(LogStatus.FAIL, " 'DCA Enabled Site' checkbox is not getting selected");
    				}
    		  }
    			
    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
    				Thread.sleep(5000);
    				
    		}else {
    			DriverTestcase.logger.log(LogStatus.FAIL, " 'DCA Enabled Site' checkbox is not available under 'Add Site order' page");
    		}
     }
     
     
     public void DCAEnabledSite_AddSiteOrder(String application, String DCAenabledsite, String cloudserviceprovider) throws InterruptedException, DocumentException {
    	 
    	//DCA Enabled Site	
			if (DCAenabledsite.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(
						xml.getlocator("//locators/" + application + "/Addsiteorder_DCAenabledsitecheckbox")));
				DriverTestcase.logger.log(LogStatus.PASS, "DCA enabled checkbox is selected");
				
				
				
			 //Cloud Service provider
				if(cloudserviceprovider.equalsIgnoreCase("null")) {
					DriverTestcase.logger.log(LogStatus.FAIL, "DCA cloud service provider dropdown is mandatory. No values are provided");
				}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cloudserviceProvider")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[text()='" + cloudserviceprovider + "']"));
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, cloudserviceprovider +  " is selected under 'cloud service provider' dropdown");
				}

			} else {
				Log.info("DCA site is not selected");
				DriverTestcase.logger.log(LogStatus.PASS, "DCA enabled checkbox is not selected");
			}	

     }
     
     
     public void validateRemark_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 boolean REmark=false;
    	 try {
			REmark = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark"))
			.isDisplayed();
			sa.assertTrue(REmark, " Remark field is not displayed");
			if(REmark) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Remak' field is displaying under 'Add Site order' page as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Remak' field is not displaying under 'Add Site order' page");
			}
    	 }catch(Exception e) {
    		 e.printStackTrace();
    		 DriverTestcase.logger.log(LogStatus.FAIL, " 'Remak' field is not displaying under 'Add Site order' page");
    		 
    	 }

     }
     
     
     public void remark_AddSiteOrder(String application, String remark) throws InterruptedException, IOException, DocumentException {
    	 
			if(remark.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No values entered under remark ");
			}else {
		try {		
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")), remark);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_remark")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, actualvalue + " is entered under 'remark' field");
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Remark' field is not displating under 'Add Site order' page");
			System.out.println(" 'Remark' field is not displating under 'Add Site order' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'Remark' field");
			System.out.println(" Not able to enter value under 'Remark' field");
		}
			}

     }
     
     
     public void nontermination_AddSiteorder(String application, String nonterminatepoinr) throws InterruptedException, DocumentException {
    	 
    	//Non- termination point	
			if(nonterminatepoinr.equalsIgnoreCase("yes")) {
			try {	
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
				Thread.sleep(3000);
				
				boolean nonTerminationSelection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")).isSelected();
				if(nonTerminationSelection) {
					DriverTestcase.logger.log(LogStatus.PASS, " 'Non-Termination point' checkbox is selected as expected");
					System.out.println(" 'Non-Termination point' checkbox is selected as expected");
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Non-Termination point' checkbox is not selected");
					System.out.println(" 'Non-Termination point' checkbox is not selected");
				}
				
				DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point checkbox is selected");
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Non-Termination point' checkbox is not dipslaying under 'Add Site order' page");
				System.out.println(" Non-Termination point' checkbox is not dipslaying under 'Add Site order' page");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on 'non-Termination point' checkbox");
				System.out.println(" Not able to click on 'non-Termination point' checkbox");
			}
			}else {
				System.out.println("Non termination point checkbox is not selected as expected");
				DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point chekbox is not selected");
			}

     }
     
     
     public void protected_AddSiteOrder(String application, String Protected) throws InterruptedException, DocumentException {
    	 
			//Protected	
				if(Protected.equalsIgnoreCase("yes")) {
				try {	
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
					Thread.sleep(3000);
					
					boolean protectedSelection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")).isSelected();
					if(protectedSelection) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'Protected' checkbox is selected as expected");
						System.out.println(" 'Protected' checkbox is selected as expected");
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Non-Termination point' checkbox is not selected");
						System.out.println(" 'Non-Termination point' checkbox is not selected");
					}
					DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is selected");
				  }catch(NoSuchElementException e) {
						e.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " 'Protected' checkbox is not displaying under 'Add Site order' page");
						System.out.println(" 'Protected' checkbox is not displaying under 'Add Site order' page");
					}catch(Exception err) {
						err.printStackTrace();
						DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on 'non-Termination point' checkbox");
						System.out.println(" Not able to click on 'non-Termination point' checkbox");
					}
					
				}else {
					System.out.println("Protected checkbox is not selecetd as expected");
					DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is not selected");
				}
				
			}
     
     
     public void devicename_AddSiteOrder(String application, String devicename) throws InterruptedException, IOException, DocumentException {

			//Device name
					if(devicename.equalsIgnoreCase("null")) {
						DriverTestcase.logger.log(LogStatus.FAIL, "device name field is mandatory. No values entered under 'device name' field");
					}else {
					  try {
						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")), devicename);
						Thread.sleep(3000);
						
						String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Devicenamefield")).getAttribute("value");
						
						DriverTestcase.logger.log(LogStatus.PASS, actualvalue + " is entered under 'device name' field");
					  }catch(NoSuchElementException e) {
						  e.printStackTrace();
						  DriverTestcase.logger.log(LogStatus.FAIL, " 'Device name' field is not displaying under 'Add Site Order' page");
						  System.out.println(" 'Device name' field is not displaying under 'Add Site Order' page");
					  }catch(Exception err) {
						  err.printStackTrace();
						  DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in 'Device name' field");
						  System.out.println(" Not able to enter value in 'Device name' field");
					  }
					}
			
	       	}


    	 
     
     
     public void addSiteOrderValues_point2point(String application,  String interfaceSpeed,
 			String country, String city, String CSR_Name, String site,
 			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
 			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
 			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
 			String existingsiteselection, String newsiteselection) throws InterruptedException, DocumentException, IOException {
    	 
    	 Countyr_AddSiteOrder(application, country);
    	 
    	 City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode, sitevalue, CSR_Name, existingsiteselection, newsiteselection);

//    	 Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

    	 
    	//scroll to bottom
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
 		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
    	 

     }
     
     
     
     public void technologyP2P_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, String nonterminatepoinr, String Protected) throws InterruptedException, IOException, DocumentException {
    	 
    	//Technology
 		if(technology.equalsIgnoreCase("null")) {
 			DriverTestcase.logger.log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
 		}else {
 		
 			if(interfaceSpeed.equals("1GigE")) {	
 			
 		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equals("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
 			
 			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
 			Thread.sleep(3000);
 			Clickon(getwebelement("//div[text()='" + technology + "']"));
 			Thread.sleep(3000);
 			DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
 			
 			
 			
 			if(technology.equals("Actelis")) {	
 				
 			     System.out.println("No additional fields displays");
 			}
 			

 			else if(technology.equals("Atrica")) {
 				
 				//Device name
 					devicename_AddSiteOrder(application, devicename);

 	  			//Non- termination point	
 	  				nontermination_AddSiteorder(application, nonterminatepoinr);
 	  				
 	  			//Protected	
 	  				protected_AddSiteOrder(application, Protected);
 			
 			}
 			
 			

 			else if(technology.equals("Overture") || technology.equals("Accedian-1G")) {	

 				//Non- termination point	
	  				nontermination_AddSiteorder(application, nonterminatepoinr);
	  				
	  			//Protected	
	  				protected_AddSiteOrder(application, Protected);
 				

 			}
 			

 			else if(technology.equals("Cyan")) {	

 				//Non- termination point	
	  				nontermination_AddSiteorder(application, nonterminatepoinr);
	  				
 			}
 			
 			else if(technology.equals("Alu")) {
 				
 				//Device name
					devicename_AddSiteOrder(application, devicename);
 				
 		       	}
 		}
 	}
 			
 			if(interfaceSpeed.equals("10GigE")) {	
 				
 				if(technology.equals("Accedian"))	{
 					
 					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
 					Thread.sleep(3000);
 					Clickon(getwebelement("//div[text()='" + technology + "']"));
 					Thread.sleep(3000);
 					DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
 						
 				//Non- termination point	
 	  				nontermination_AddSiteorder(application, nonterminatepoinr);
 	  				
 	  			//Protected	
 	  				protected_AddSiteOrder(application, Protected);
 						}else {
 							DriverTestcase.logger.log(LogStatus.FAIL, technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
 						}
 					
 				}
 			}	
 	
 	
 	DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered for add site order");
 	
 		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

 	 }
     
     
     
     
     public void technologyHubAndSpoke1G_IVRefrencePrimary_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			DriverTestcase.logger.log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
  			
  			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
  			Thread.sleep(3000);
  			Clickon(getwebelement("//div[text()='" + technology + "']"));
  			Thread.sleep(3000);
  			DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			if(technology.equals("Actelis")) {	
  			     System.out.println("No additional fields displays");
  			}
  			

  			else if((technology.equals("Atrica")) ) {
  			//Device name
  				devicename_AddSiteOrder(application, devicename);
  			
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected	
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			
  			else if(technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G")) {	
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected	
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			else if(technology.equals("Cyan")) {	
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  			
  			}
  			
  			else if(technology.equals("Alu")) {
  			//Device name
  				devicename_AddSiteOrder(application, devicename);
  				
  		       	}
  		    }
  		}
  	}	
     

     public void technologyHubAndSpoke1G_IVRefrencePrimary_OffnetSelected_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			DriverTestcase.logger.log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  		if(technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G") || technology.equalsIgnoreCase("Accedian"))	{
  			
  			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
  			Thread.sleep(3000);
  			Clickon(getwebelement("//div[text()='" + technology + "']"));
  			Thread.sleep(3000);
  			DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			if(technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G")) {	
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected	
  				protected_AddSiteOrder(application, Protected);
  			}
  		  }	
  		}
  	}	

     
     public void technologyEPN1G_IVRefrencePrimary_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			DriverTestcase.logger.log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equals("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
  			
  			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
  			Thread.sleep(3000);
  			Clickon(getwebelement("//div[text()='" + technology + "']"));
  			Thread.sleep(3000);
  			DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			if(technology.equals("Actelis")) {	
  			     System.out.println("No additional fields displays");
  			}
  			

  			else if((technology.equals("Atrica")) || (technology.equals("Overture")) || (technology.equals("Accedian-1G"))) {
  			//Device name
  				devicename_AddSiteOrder(application, devicename);
  			
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected	
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			else if(technology.equals("Cyan")) {	
  			//Non- termination point	
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  			
  			}
  			
  			else if(technology.equals("Alu")) {
  			//Device name
  				devicename_AddSiteOrder(application, devicename);
  				
  		       	}
  		    }
  		}
  	}	
  	
  	 
     public void technologyHubAndSpoke1G_IVRefrenceAccess_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected, String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			DriverTestcase.logger.log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  			if(interfaceSpeed.equals("1GigE")) {	
  			
  		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
  			
  			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
  			Thread.sleep(3000);
  			Clickon(getwebelement("//div[text()='" + technology + "']"));
  			Thread.sleep(3000);
  			DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			
  			if(technology.equals("Actelis")) {	
  				
  			     System.out.println("No additional fields displays");
  			}
  			

  			else if((technology.equals("Atrica")) || (technology.equalsIgnoreCase("Accedian-1G")) ) {
  				
  				//Non-Termination Point
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  				//Protected
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			

  			else if(technology.equals("Overture")) {	
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected checkbox	
  				protected_AddSiteOrder(application, Protected);
  				
  			//GCR OLO Type dropdown
  				GCROLOType_AddSiteorder(application, GCRolo);
  				
  			//VLAN Text field
  				Vlan_AddSiteorder(application, Vlan);
  				
  			//VLAN Ether Type dropdown
  				Vlanethertype_AddSiteorder(application, Vlanether);
  				
  			//Primary VLAN Text Field
  				primaryVlan_AddSiteorder(application, primaryVlan);
  				
  			//Primary Ether Vlan dropdown
  				primaryVlanethertype_AddSiteorder(application, primaryVlanether);
  				
  			}
  			
  			else if(technology.equals("Cyan")) {	
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  			
  			}
  			
  			
  			else if(technology.equals("Alu")) {
  				System.out.println("No Additional fields display for 'Alu' technology");
  				
  			}
  		}
  	}
  			
  	}	
  	
  	DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered for add site order");
  	
  		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

  	 }
     
     
     public void technologyHubAndSpoke1G_IVRefrenceAccess_offnetSelected_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected, String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			DriverTestcase.logger.log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  			if(interfaceSpeed.equals("1GigE")) {	
  			
  		if(technology.equals("Overture") || technology.equalsIgnoreCase("Accedian-1G") || technology.equals("Accedian" ))	{
  			
  			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
  			Thread.sleep(3000);
  			Clickon(getwebelement("//div[text()='" + technology + "']"));
  			Thread.sleep(3000);
  			DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			

  			if((technology.equalsIgnoreCase("Accedian-1G")) ) {
  				
  				//Non-Termination Point
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  				//Protected
  				protected_AddSiteOrder(application, Protected);
  			}
  			
  			else if(technology.equals("Overture")) {
  				
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected checkbox
  				protected_AddSiteOrder(application, Protected);
  				
  			//GCR OLO Type dropdown
  				GCROLOType_AddSiteorder(application, GCRolo);
  				
  			//VLAN Text field
  				Vlan_AddSiteorder(application, Vlan);
  				
  			//VLAN Ether Type dropdown
  				Vlanethertype_AddSiteorder(application, Vlanether);
  				
  			//Primary VLAN Text Field
  				primaryVlan_AddSiteorder(application, primaryVlan);
  				
  			//Primary Ether Vlan dropdown
  				primaryVlanethertype_AddSiteorder(application, primaryVlanether);
  				
  			}
  			
  		}
  	}
  			}	
  	
  	DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered for add site order");
  	
  		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

  	 }

     
     
     public void technologyEPN1G_IVRefrenceAccess_AddSiteOrder(String application, String technology, String interfaceSpeed, String devicename, 
    		 String nonterminatepoinr, String Protected, String GCRolo, String Vlan, String Vlanether,
    		 String mappingmode, String portBased, String vlanBased) throws InterruptedException, IOException, DocumentException {
    	 
     	//Technology
  		if(technology.equalsIgnoreCase("null")) {
  			DriverTestcase.logger.log(LogStatus.FAIL, "Technology dropdown is a mandatory field and no values are provided"); 
  		}else {
  		
  			if(interfaceSpeed.equals("1GigE")) {	
  			
  		if(technology.equals("Actelis") || technology.equals("Atrica") || technology.equals("Overture") || technology.equals("Accedian-1G") || technology.equals("Cyan" ) || technology.equals("Alu"))	{
  			
  			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
  			Thread.sleep(3000);
  			Clickon(getwebelement("//div[text()='" + technology + "']"));
  			Thread.sleep(3000);
  			DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
  			
  			
  			
  			if(technology.equals("Actelis")) {	
  				
  			     System.out.println("No additional fields displays");
  			}
  			

  			else if((technology.equals("Atrica"))) {
  				
  				//Non-Termination Point
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  				//Protected
  				protected_AddSiteOrder(application, Protected);
  				
  				//Device Name
  				devicename_AddSiteOrder(application, devicename);
  				
  				//Mapping Mode
  				addMappingMode(application, mappingmode);
  			}
  			
  			

  			else if(technology.equals("Overture")) {	
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  			//Protected checkbox	
  				protected_AddSiteOrder(application, Protected);
  				
  			//Device Name
  				devicename_AddSiteOrder(application, devicename);
  				
  			//GCR OLO Type dropdown
  				GCROLOType_AddSiteorder(application, GCRolo);
  				
  			//VLAN Text field
  				Vlan_AddSiteorder(application, Vlan);
  				
  			//VLAN Ether Type dropdown
  				Vlanethertype_AddSiteorder(application, Vlanether);
  				
  			}
  			
  			else if(technology.equals("Cyan")) {	
  			//Non- termination point checkbox
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  			
  			}
  			
  			else if(technology.equals("Accedian-1G")) {
  				
  				//Non-Termination Point
  				nontermination_AddSiteorder(application, nonterminatepoinr);
  				
  				//Protected
  				protected_AddSiteOrder(application, Protected);
  				
  				//Device Name
  				devicename_AddSiteOrder(application, devicename);
  			}
  			
  			else if(technology.equals("Alu")) {
  				
  				//Device Name
  				devicename_AddSiteOrder(application, devicename);
  				
  				//Mappin Mode
  				addMappingMode(application, mappingmode);
  				
  				if(mappingmode.equalsIgnoreCase("port Based")) {
  					addtextFields_commonMethod(application, "Port Name", "portname_textField", portBased);
  					
  				}if(mappingmode.equalsIgnoreCase("Vlan Based")) {
  					
  					addtextFields_commonMethod(application, "VLAN Id", "vlanid_textfield", vlanBased);
  				}
  				
  			}
  		}
  	}
  			}	
  	
  	DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered for add site order");
  	
  		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

  	 }
     
     
     
     public void technologyHubANdSpoke10G_AddSiteOrder(String application, String interfaceSpeed, String technology, String nonterminatepoinr, String Protected) throws InterruptedException, DocumentException {
    	 
				if(technology.equals("Accedian"))	{
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
						
						//Non- termination point	
								if(nonterminatepoinr.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
									Thread.sleep(3000);
									DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point checkbox is selected");
								}else {
									System.out.println("Non termination point checkbox is not selected as expected");
									DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point chekbox is not selected");
								}
								
								
							//Protected	
								if(Protected.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
									Thread.sleep(3000);
									DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is selected");
								}else {
									System.out.println("Protected checkbox is not selecetd as expected");
									DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is not selected");
								}
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
						}
     }
     
     
     
     public void technologyEPN10G_AddSiteOrder(String application, String interfaceSpeed, String technology, String nonterminatepoinr, String Protected) throws InterruptedException, DocumentException {
    	 
				if(technology.equals("Accedian"))	{
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_Technology")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[text()='" + technology + "']"));
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, technology + " is selected under technology dropdown");
						
						//Non- termination point	
								if(nonterminatepoinr.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_nonterminationpoint")));
									Thread.sleep(3000);
									DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point checkbox is selected");
								}else {
									System.out.println("Non termination point checkbox is not selected as expected");
									DriverTestcase.logger.log(LogStatus.PASS, "Non-termination point chekbox is not selected");
								}
								
								
							//Protected	
								if(Protected.equalsIgnoreCase("yes")) {
									
									Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_protected")));
									Thread.sleep(3000);
									DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is selected");
								}else {
									System.out.println("Protected checkbox is not selecetd as expected");
									DriverTestcase.logger.log(LogStatus.PASS, "Protected checkbox is not selected");
								}
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, technology + "is not available under 'Technology' dropdown for '10GigE' interface speed");
						}
     }
     
     
     public void validatesiteOrderNumber_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
    		
  		// Site Order Number Field
  				boolean siteorderNmber = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_siteordernumbertextfield"))
  						.isDisplayed();
  				sa.assertTrue(siteorderNmber, " 'site order number' field is not displayed");
  				if(siteorderNmber) {
  					DriverTestcase.logger.log(LogStatus.PASS, " 'Site Order Number (Siebel Service ID)' text field is displaying under 'Add Site order' page as expected");
  				}else {
  					DriverTestcase.logger.log(LogStatus.FAIL, " 'site order number (Siebel Service ID)' text field is not displaying under 'Add Site order' page");
  				}
    	 
     }
     
     
     
     public void siteOrderNumber_AddSiteOrder(String application, String siteOrderNumber) throws InterruptedException, IOException, DocumentException {
    	 
    	 if(siteOrderNumber.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Site Order Number' field is a mandatory field and no values are provided");
			}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_siteordernumbertextfield")), siteOrderNumber);
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, siteOrderNumber+ " is entered under 'Site Order Number' field");
			}
    	 
     }
     
     public void validateIVReference_AddSiteorder(String application) throws InterruptedException, DocumentException {
    	 
 			boolean IVReference = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")).isDisplayed();
 			sa.assertTrue(IVReference, " 'IV reference' dropdown is not displayed");
 			if(IVReference) {
 				
 			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
 			List<WebElement> listofIVreference= driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
 			
 		if(listofIVreference.size()>=1) {	
 			for (WebElement IVreferencetypes : listofIVreference) {

 						Log.info("list of IV References : " + IVreferencetypes.getText());
 						System.out.println("list of IV References for AddSite order are: "+IVreferencetypes.getText());
 						DriverTestcase.logger.log(LogStatus.PASS,"list of IV References for AddSite order are: "+IVreferencetypes.getText());
 					
 			}
 					
 		}else {
 			System.out.println("no values are available inside 'IV reference' dropdown for Add site order");
 			DriverTestcase.logger.log(LogStatus.FAIL,"no values are available inside 'IV reference' dropdown for Add site order");
 		}
 		
 	}else {
 		DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory field 'IV Reference' dropdown is not available under 'Add Site ORder' page");
 	}
     }
     
     
     public void validateCircuitreference_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
    				boolean circuitReference = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField"))
    						.isDisplayed();
    				sa.assertTrue(circuitReference, "Circuit Reference field is not displayed");	
    				if(circuitReference) {
    					DriverTestcase.logger.log(LogStatus.PASS, "Mandatory field 'Circuit Reference' text field is displaying under 'Add Site order' page as expected");
    				}else {
    					DriverTestcase.logger.log(LogStatus.FAIL, "Mandatory field 'Circuit Reference' text field is not displaying under 'Add Site order' page");
    				}
     }
     
     public void circuitreference_AddSiteorder(String application, String circuitref) throws InterruptedException, IOException, DocumentException {
    	 
    	 if(circuitref.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Circuit Reference' field is a mandatory field and no values are provided");
			}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")), circuitref);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_circuitReferenceField")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, actualvalue+ " is entered under 'Circuit Reference' field");
			}
     }
     
     
     public void validateoffnet_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
    	//Offnet checkbox
    	 
			boolean offnet = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox"))
					.isDisplayed();
			sa.assertTrue(offnet, "Offnet field is not displayed");	
			boolean offnetcheckbox = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();
			sa.assertFalse(offnetcheckbox, " Offnet checkbox is selected");
			
			if(offnet) {
				DriverTestcase.logger.log(LogStatus.PASS, "'Offnet' text field is displaying under 'Add Site order' page as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, "'Offent' text field is not displaying under 'Add Site order' page");
			}
			
			if(offnetcheckbox) {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Offnet' checkbox is selected by default");
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Offnet' checkbox is not selected by default as expected");
			}
     }
     
     public void validateEPNoffnet_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
     	//EPN Offnet checkbox
 			boolean EPNoffnet = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNoffnetCheckbox"))
 					.isDisplayed();
 			sa.assertTrue(EPNoffnet, " EPN Offnet checkbox is not displayed");	
 			boolean EPNoffnetcheckbox = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNoffnetCheckbox")).isSelected();
 			sa.assertFalse(EPNoffnetcheckbox, "  EPN Offnet checkbox is selected");
 			
 			if(EPNoffnet) {
 				DriverTestcase.logger.log(LogStatus.PASS, "'EPN Offnet' text field is displaying under 'Add Site order' page as expected");
 			}else {
 				DriverTestcase.logger.log(LogStatus.FAIL, "'EPN Offnet' text field is not displaying under 'Add Site order' page");
 			}
 			
 			if(EPNoffnetcheckbox) {
 				DriverTestcase.logger.log(LogStatus.FAIL, " 'EPN Offnet' checkbox is selected by default");
 			}else {
 				DriverTestcase.logger.log(LogStatus.PASS, " 'EPN Offnet' checkbox is not selected by default as expected");
 			}
      }
     
     
     public void validateEPNEOSDH_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
     	//EPN EOSDH checkbox
 			boolean EPNEOSDH = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox"))
 					.isDisplayed();
 			sa.assertTrue(EPNEOSDH, " EPN EOSDH checkbox is not displayed");	
 			boolean EPNEOSDHcheckbox = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")).isSelected();
 			sa.assertFalse(EPNEOSDHcheckbox, "  EPN EOSDH checkbox is selected");
 			
 			if(EPNEOSDH) {
 				DriverTestcase.logger.log(LogStatus.PASS, "'EPN EOSDH' text field is displaying under 'Add Site order' page as expected");
 			}else {
 				DriverTestcase.logger.log(LogStatus.FAIL, "'EPN EOSDH' text field is not displaying under 'Add Site order' page");
 			}
 			
 			if(EPNEOSDHcheckbox) {
 				DriverTestcase.logger.log(LogStatus.FAIL, " 'EPN EOSDH' checkbox is selected by default");
 			}else {
 				DriverTestcase.logger.log(LogStatus.PASS, " 'EPN EOSDH' checkbox is not selected by default");
 			}
      }
     
     public void offnet_AddSiteOrder(String application, String offnetSelection) throws InterruptedException, DocumentException {
    	 
    	 boolean offnetselect=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();
    	 
    	 if(offnetSelection.equalsIgnoreCase("yes")) {

    		 if(offnetselect) {
    			 System.out.println("Offnet chckbox is selected already while verifying the fields");
    		 }else {
    		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")));
    		 DriverTestcase.logger.log(LogStatus.PASS, " 'Offnet' checkbox is selected as expected");
    		 
    		 boolean offnetActualSelection=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_offnetCheckbox")).isSelected();
    		 if(offnetActualSelection){
    			 DriverTestcase.logger.log(LogStatus.PASS, " 'offnet' checkbox is selected as expected");
    			 System.out.println(" 'offnet' checkbox is selected as expected");
    		 }else {
    			 DriverTestcase.logger.log(LogStatus.PASS, " 'offnet' checkbox is not selected");
    			 System.out.println(" 'offnet' checkbox is not selected");
    		 }
    		 
    		 }
    	 }
    	 else if(offnetSelection.equalsIgnoreCase("no")) {
    		 
    		 DriverTestcase.logger.log(LogStatus.PASS, " 'Offnet' checkbox is not selected");
    	 }
     }
     
     public void EPNoffnet_AddSiteOrder(String application, String EPNoffnetSelection) throws InterruptedException, DocumentException {
    	 
    	 if(EPNoffnetSelection.equalsIgnoreCase("yes")) {

    		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNoffnetCheckbox")));
    		 DriverTestcase.logger.log(LogStatus.PASS, " 'EPN Offnet' checkbox is selected as expected");
    	 }
    	 else if(EPNoffnetSelection.equalsIgnoreCase("no")) {
    		 
    		 DriverTestcase.logger.log(LogStatus.PASS, " 'EPN Offnet' checkbox is not selected as expected");
    	 }
     }
     
     
     public void EPNEOSDH_AddSiteOrder(String application, String EPNEOSDHSelection) throws InterruptedException, DocumentException {
    	 
    	 if(EPNEOSDHSelection.equalsIgnoreCase("yes")) {

    		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_EPNEOSDHCheckbox")));
    		 DriverTestcase.logger.log(LogStatus.PASS, " 'EPN EOSDH' checkbox is selected as expected");
    	 }
    	 else if(EPNEOSDHSelection.equalsIgnoreCase("no")) {
    		 
    		 DriverTestcase.logger.log(LogStatus.PASS, " 'EPN EOSDH' checkbox is not selected as expected");
    	 }
     }
     
     
     public void validatespokeId_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 try {
			boolean spokeIdlabel = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_spokeIdField")).isDisplayed();
			if(spokeIdlabel) {
				boolean spokeIDvalue = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_spokeId"))
						.isDisplayed();
				if(spokeIDvalue) {
					DriverTestcase.logger.log(LogStatus.PASS, " 'Spoke Id' value is displaying as '0' by default as expected");
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL," 'Spoke Id' is not displaying as expected" );
				}
			}
			else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Spoke Id' field is not displaying under 'Add Site Order' page");
			}
    	 }catch(Exception e) {
    		 e.printStackTrace();
    		 DriverTestcase.logger.log(LogStatus.FAIL, " 'Spoke Id' field is not displaying under 'Add Site Order' page");
    	 }
			
     }
     
     
     public void OKbutton_AddSiteOrder(String application) throws InterruptedException, DocumentException {

    	 
    	 boolean ok = getwebelement(xml.getlocator("//locators/" + application + "/okbutton")).isDisplayed();
			sa.assertTrue(ok, "OK button is not displayed");
			if(ok) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'OK' button is displaying as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'OK' button is not displaying");
			}
     }
     
     
     public void cancelbutton_AddSiteOrder(String application) throws InterruptedException, DocumentException {
    	 
			boolean cancel = getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_cancel")).isDisplayed();
			sa.assertTrue(cancel, "Cancel button is not "
					+ "displayed");
			if(cancel) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Cancel' button is displaying as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Cancel' button is not displaying");
			}
     }
     
     
     public void Ivrefrence_AddSiteOrder(String application, String iVReference) throws InterruptedException, DocumentException {
    	 
    	if(iVReference.equalsIgnoreCase("null")){
    		
    		DriverTestcase.logger.log(LogStatus.FAIL, " 'IV Reference' dropdown field is a mandatory field and no values has been passed");
    	}else {
    		
    		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_IVreferencedropdown")));
    		Thread.sleep(3000);
    		Clickon(getwebelement("//div[text()='" + iVReference +"']"));
    		DriverTestcase.logger.log(LogStatus.PASS, iVReference + " is selected under 'IV Reference' dropdown");
    	}
    	 
     }
     
     public void GCROLOType_AddSiteorder(String application, String gcrOlo) throws InterruptedException, DocumentException {
    	try { 
    	 if(gcrOlo.equalsIgnoreCase("null")){
     		
     		DriverTestcase.logger.log(LogStatus.PASS, "  No values has been selected under 'GCR OLO Type' dropdown field");
     	}else {
     		
     		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_GCRoloTypeDropdown")));
     		Thread.sleep(3000);
     		Clickon(getwebelement("//div[text()='" + gcrOlo +"']"));
     		
     		String actualvalue=getwebelement("//div[label[text()='GCR OLO Type']]//span").getText();
     		DriverTestcase.logger.log(LogStatus.PASS, actualvalue + " is selected under 'GCR OLO Type' dropdown");
     	}
    	}catch(NoSuchElementException e) {
    		e.printStackTrace();
    		DriverTestcase.logger.log(LogStatus.FAIL, " 'GCR OLO Type' dropdown is not available under 'Add Site Order' page");
    		System.out.println(" 'GCR OLO Type' dropdown is not available under 'Add Site Order' page");
    	}catch(Exception ee) {
    		ee.printStackTrace();
    		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to select value under 'GCR olo Dropdown'");
    		System.out.println( " Not able to select value under 'GCR olo Dropdown'");
    	}
     }
     
     public void Vlanethertype_AddSiteorder(String application, String VlanEthertype) throws InterruptedException, DocumentException {
    try {	 
    	 if(VlanEthertype.equalsIgnoreCase("null")){
      		
      		DriverTestcase.logger.log(LogStatus.PASS, " No values has been selected under 'VLAN Ether Type' dropdown field");
      	}else {
      		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLANEtherTypeDropdown")));
      		Thread.sleep(3000);
      		Clickon(getwebelement("//div[label[text()='VLAN Ether Type']]//div[text()='"+ VlanEthertype +"']"));
      		
      		String actualvalue=getwebelement("//div[label[text()='VLAN Ether Type']]//span").getText();
      		DriverTestcase.logger.log(LogStatus.PASS, actualvalue + " is selected under 'VLAN Ether Type' dropdown");
      	}
    }catch(NoSuchElementException e){
    	e.printStackTrace();
    	DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN Ether Type' dropdown is not displaying under 'Add Site Oder' page");
    	System.out.println(" 'VLAN Ether Type' dropdown is not displaying under 'Add Site Oder' page");
    }catch(Exception ee) {
    	ee.printStackTrace();
    	DriverTestcase.logger.log(LogStatus.FAIL, " Not able to selected value under 'VLAN Ether Type' dropdown");
    	System.out.println(" Not able to selected value under 'VLAN Ether Type' dropdown");
    }
    	 
     }
     
     public void primaryVlanethertype_AddSiteorder(String application, String primaryVlanEthertype) throws InterruptedException, DocumentException {
    
    	 try {
    	 if(primaryVlanEthertype.equalsIgnoreCase("null")){
      		
      		DriverTestcase.logger.log(LogStatus.PASS, " No values has been selected under 'Primary VLAN Ether Type' dropdown field");
      	}else {
      		
      		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_primaryVLANEtherTypeDropdown")));
      		Thread.sleep(3000);
      		Clickon(getwebelement("//div[label[text()='Primary VLAN Ether Type']]//div[text()='"+ primaryVlanEthertype +"']"));
      		
      		String actualvalue=getwebelement("//div[label[text()='Primary VLAN Ether Type']]//span").getText();
      		DriverTestcase.logger.log(LogStatus.PASS, actualvalue + " is selected under 'Primary VLAN Ether Type' dropdown");
      	}
    	 }catch(NoSuchElementException e) {
    		 e.printStackTrace();
    		 DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN Ether type' dropdown is not displaying in 'Add Site Order' page");
    		 System.out.println(" 'Primary VLA Ether type' dropdown is not displaying in 'Add Site Order' page");
    		 
    	 }catch(Exception ee) {
    		 ee.printStackTrace();
    		 DriverTestcase.logger.log(LogStatus.FAIL, " Not able to select value under 'Primary VLAN Ether type' dropdown");
    		 System.out.println(" Not able to select value under 'Primary VLAN Ether type' dropdown");
    	 }
    	 
     }
     
     public void Vlan_AddSiteorder(String application, String vlan) throws InterruptedException, IOException, DocumentException {
    	try { 
    	 if(vlan.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No values has been passed for 'VLAN' text field");
			}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")), vlan);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_VLAN")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, actualvalue+ " is entered under 'VLAN' text field");
			}
    	}catch(NoSuchElementException e) {
    		e.printStackTrace();
    		DriverTestcase.logger.log(LogStatus.FAIL, " 'VLAN' text field is not displying under 'Add Site Order'");
    		System.out.println(" 'VLAN' text field is not displying under 'Add Site Order'");
    		
    	}catch(Exception ee) {
    		ee.printStackTrace();
    		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'VLAN' text field");
    		System.out.println( " Not able to enter value under 'VLAN' text field");
    	}
     }
     
     
     public void primaryVlan_AddSiteorder(String application, String primaryvlan) throws InterruptedException, IOException, DocumentException {
    	try { 
    	 if(primaryvlan.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No values has been passed for 'Primary VLAN' text field");
			}else {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")), primaryvlan);
			Thread.sleep(3000);
			
			String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_PrimaryVLAN")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, actualvalue+ " is entered under 'Primary VLAN' text field");
			}
    	}catch(NoSuchElementException e) {
    		e.printStackTrace();
    		DriverTestcase.logger.log(LogStatus.FAIL, " 'Primary VLAN' text field is not displaying under 'Add Site Order' page");
    		System.out.println(" 'Primary VLAN' text field is not displaying under 'Add Site Order' page");
    	}catch(Exception ee) {
    		ee.printStackTrace();
    		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in 'Primary VLAN type' text field");
    		System.out.println(" Not able to enter value in 'Primary VLAN type' text field");
    	}
     }
     

     public void addSiteOrderValues_HubAndSPoke(String application,  String interfaceSpeed,
  			String country, String city, String CSR_Name, String site,
  			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
  			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
  			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
  			String existingsiteselection, String newsiteselection,
  			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
  			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether) throws InterruptedException, DocumentException, IOException {
     	 
		siteOrderNumber_AddSiteOrder(application, siteOrderNumber);

		Countyr_AddSiteOrder(application, country);

		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode, sitevalue, CSR_Name, existingsiteselection, newsiteselection);

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

	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Primary")))	{
		
		Ivrefrence_AddSiteOrder(application, IVReference); 
		technologyHubAndSpoke1G_IVRefrencePrimary_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected);
		
	}
	
	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Access")))	{
		
		Ivrefrence_AddSiteOrder(application, IVReference); 
		technologyHubAndSpoke1G_IVRefrenceAccess_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected,
				 GCRolo,  Vlan,  Vlanether,  primaryVlan, primaryVlanether);
		
	}
	
	if(interfaceSpeed.equals("10GigE")){
		Ivrefrence_AddSiteOrder(application, IVReference);
		technologyHubANdSpoke10G_AddSiteOrder(application, interfaceSpeed, technology, nonterminatepoinr, Protected);

      }
	
	    DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered for add site order");
  	
	   scrolltoend();
	   Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

     
     }
     
     
     public void addSiteOrderValues_HubAndSPoke_OffnetSelected(String application,  String interfaceSpeed,
   			String country, String city, String CSR_Name, String site,
   			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
   			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
   			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
   			String existingsiteselection, String newsiteselection,
   			String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
   			String GCRolo, String Vlan, String Vlanether, String primaryVlan, String primaryVlanether) throws InterruptedException, DocumentException, IOException {
      	 
 		siteOrderNumber_AddSiteOrder(application, siteOrderNumber);

 		Countyr_AddSiteOrder(application, country);

 		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode, sitevalue, CSR_Name, existingsiteselection, newsiteselection);

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

 	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Primary")))	{
 		
 		Ivrefrence_AddSiteOrder(application, IVReference); 
 		technologyHubAndSpoke1G_IVRefrencePrimary_OffnetSelected_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected);
 		
 	}
 	
 	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Access")))	{
 		
 		Ivrefrence_AddSiteOrder(application, IVReference); 
 		technologyHubAndSpoke1G_IVRefrenceAccess_offnetSelected_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected,
 				 GCRolo,  Vlan,  Vlanether,  primaryVlan, primaryVlanether);
 		
 	}
 	
 	if(interfaceSpeed.equals("10GigE")){
 		
 		DriverTestcase.logger.log(LogStatus.INFO, " If 'Offnet' is selected for '10GigE', 'No value displays under 'Technology' dropdown");

       }
 	
 	    DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered for add site order");
   	
 	    scrolltoend();
 	    Thread.sleep(3000);
 		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

      
      }
     
     
     public void addSiteOrderValues_EPN(String application,  String interfaceSpeed,
   			String country, String city, String CSR_Name, String site,
   			String performReport, String ProactiveMonitor, String smartmonitor, String technology, String siteallias,
   			String VLANid, String DCAenabledsite, String cloudserviceprovider, String sitevalue, String remark,
   			String xngcityname, String xngcitycode,String devicename, String nonterminatepoinr, String Protected, String newcityselection, String existingcityselection,
   			String existingsiteselection, String newsiteselection,String siteOrderNumber, String circuitref, String offnetSelection, String IVReference,
   			String GCRolo, String Vlan, String Vlanether, String EPNoffnetSelection, String EPNEOSDHSelection,
   			String mappingmode, String portBased, String vlanBased) throws InterruptedException, DocumentException, IOException {
      	
    	 
 		siteOrderNumber_AddSiteOrder(application, siteOrderNumber);

 		Countyr_AddSiteOrder(application, country);

 		City_AddSiteorder(application, existingcityselection, city, newcityselection, xngcityname, xngcitycode, sitevalue, CSR_Name, existingsiteselection, newsiteselection);

// 		Site_AddSiteOrder(application, existingsiteselection, sitevalue, newsiteselection, CSR_Name);

 		performancereporting_AddSiteOrder(application, performReport);

 		proactiveMonitoring_AddSiteOrder(application, ProactiveMonitor);

 		smartsMonitoring_AddSiteOrder(application, smartmonitor);

 	scrolltoend();
 	Thread.sleep(3000);
 	
 		circuitreference_AddSiteorder(application, circuitref);

 		SiteAlias_AddSiteOrder(application, siteallias);
 		
 		EPNoffnet_AddSiteOrder(application, EPNoffnetSelection);
 		
 		if(interfaceSpeed.equals("1GigE")) {
 			EPNEOSDH_AddSiteOrder(application, EPNEOSDHSelection);
 		}
 		

 		remark_AddSiteOrder(application, remark);

 	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Primary")))	{
 		
 		Ivrefrence_AddSiteOrder(application, IVReference); 
 		technologyEPN1G_IVRefrencePrimary_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected);
 		
 	}
 	
 	if((interfaceSpeed.equals("1GigE")) &  (IVReference.equals("Access")))	{
 		
 		Ivrefrence_AddSiteOrder(application, IVReference); 
 		technologyEPN1G_IVRefrenceAccess_AddSiteOrder(application, technology, interfaceSpeed, devicename, nonterminatepoinr, Protected,
 				 GCRolo,  Vlan,  Vlanether, mappingmode, portBased, vlanBased);
 		
 	}
 	
 	if(interfaceSpeed.equals("10GigE")){
 		Ivrefrence_AddSiteOrder(application, IVReference);
 		technologyEPN10G_AddSiteOrder(application, interfaceSpeed, technology, nonterminatepoinr, Protected);

       }
 	
 	    DriverTestcase.logger.log(LogStatus.PASS, "Data has been entered for add site order");
   	
 	    scrolltoend();
 	   Thread.sleep(3000); 
 		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));

      
      }

     
     public void device_nameFieldWarningMessage(String application) throws InterruptedException, DocumentException {
     	boolean name=false;
     	//Name field Error Message
     			try {
     				name = getwebelement(
     					xml.getlocator("//locators/" + application + "/AddCPEdevice_nameerrmsg")).isDisplayed();
     				Thread.sleep(3000);
     			sa.assertTrue(name, "name field warning message is not displayed ");
     			if(name) {
     			String nameErrMsg = getwebelement(
     					xml.getlocator("//locators/" + application + "/AddCPEdevice_nameerrmsg")).getText();
     			System.out.println(
     					"Name field warning  message displayed as : " + nameErrMsg);
     			DriverTestcase.logger.log(LogStatus.PASS,
     					"Step :  validation message for 'Name' text field displayed as : " + nameErrMsg);
     			Log.info(" Name field warning message displayed as : " + nameErrMsg);
     			}
     			}catch(NoSuchElementException e) {
     				e.printStackTrace();
     				System.out.println(" Name field warning message is not dipslaying");
     				DriverTestcase.logger.log(LogStatus.FAIL, " name field warning message is not displaying");
     			}catch(Exception ed) {
     				ed.printStackTrace();
     			}
     }
     
    public void device_vendorModelWarningMessage(String application) throws InterruptedException, DocumentException {
    	boolean vendorErr=false;
    	//Vendor/Model Error Message
    			try {
    				vendorErr = getwebelement(
    					xml.getlocator("//locators/" + application + "/AddCPEdevice_vendorErrmsg")).isDisplayed();
    			sa.assertTrue(vendorErr, "Vendor/Model warning message is not displayed ");
    			if(vendorErr) {
    			String vendorErrMsg = getwebelement(
    					xml.getlocator("//locators/" + application + "/AddCPEdevice_vendorErrmsg")).getText();
    			System.out.println(
    					"Vendor/Model  message displayed as : " + vendorErrMsg);
    			DriverTestcase.logger.log(LogStatus.PASS,
    					"Step :  validation message for Vendor/Model field displayed as : " + vendorErrMsg);
    			Log.info("Vendor/Model warning message displayed as : " + vendorErrMsg);
    			}
    			}catch(NoSuchElementException e) {
    				e.printStackTrace();
    				System.out.println("Vendor/Mdel warning message is not dipslaying");
    				DriverTestcase.logger.log(LogStatus.FAIL, " Vendor/Model warning message is not displaying");
    			}catch(Exception ed) {
    				ed.printStackTrace();
    			}
    }

   public void device_managementAddressWarningMessage(String application) throws InterruptedException, DocumentException {
	   
	   boolean mangadrsErr=false;
	   try {
		   mangadrsErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg")).isDisplayed();
			sa.assertTrue(mangadrsErr, "Management Addres warning message is not displayed ");
			if(mangadrsErr) {
			String mngadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_managementAddresserrmsg")).getText();
			System.out.println(
					"Management Addres  message displayed as : " + mngadresErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for Management Addres field displayed as : " + mngadresErrMsg);
			Log.info("Management Addres warning message displayed as : " + mngadresErrMsg);
			}
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 System.out.println("management Address warning message is not found");
			 DriverTestcase.logger.log(LogStatus.FAIL, " Management Address warning message is not displaying");
		 }catch(Exception ed) {
			 ed.printStackTrace();
		 }
   }

   public void device_powerAlarmWarningMessage(String application) throws InterruptedException, DocumentException {
	   boolean pwralrmErr=false;
	   try {
		   pwralrmErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).isDisplayed();
			sa.assertTrue(pwralrmErr, "Power Alarm warning message is not displayed ");
			if(pwralrmErr) {
			String pwralarmErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_powerAlarmerrmsg")).getText();
			System.out.println(
					"Power Alarm  message displayed as : " + pwralarmErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for Power Alarm field displayed as : " + pwralarmErrMsg);
			Log.info("Power Alarm warning message displayed as : " + pwralarmErrMsg);
			}
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 System.out.println("Power Alarm warning message is not dipslaying");
			 DriverTestcase.logger.log(LogStatus.FAIL, " Power Alarm warning message is not displaying");
		 }catch(Exception er) {
			 er.printStackTrace();
		 }
   }
   
   
   public void device_mediaSelectionWarningMessage(String application) throws InterruptedException, DocumentException {
	   boolean mediaErr=false;
	   try {
			mediaErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).isDisplayed();
			sa.assertTrue(mediaErr, "Media Selection warning message is not displayed ");
			if(mediaErr) {
			String mediaselectionErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselectionerrmsg")).getText();
			System.out.println(
					"Media Selection  message displayed as : " + mediaselectionErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for Media Selection field displayed as : " + mediaselectionErrMsg);
			Log.info("Media Selection warning message displayed as : " + mediaselectionErrMsg);
			}
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 System.out.println("Media selection waning message is not displaying");
			 DriverTestcase.logger.log(LogStatus.FAIL, " Media Selection warning message is not displaying");
		 }catch(Exception er) {
			 er.printStackTrace();
		 }
   }

   public void device_macAddressWarningMessage(String application) throws InterruptedException, DocumentException {
	   boolean macErr=false;
	   try {
		   macErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).isDisplayed();
			sa.assertTrue(macErr, "MAC Address warning message is not displayed ");
			if(macErr) {
			String macadresErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_macAdressErrmsg")).getText();
			System.out.println(
					"MAC Address  message displayed as : " + macadresErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for MAC Address field displayed as : " + macadresErrMsg);
			Log.info("MAC Address warning message displayed as : " + macadresErrMsg);
			}else{
				DriverTestcase.logger.log(LogStatus.FAIL, " 'MAC Address' warning message is not displaying under 'Add cpe device' page");
			}
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 System.out.println("Mac Adress warning message is not dipslaying");
			 DriverTestcase.logger.log(LogStatus.FAIL, " MAC Address warning message is not displaying");
		 }catch(Exception er) {
			 er.printStackTrace();
		 }
   }
   
   
   public void device_serialNumberWarningMessage(String application) throws InterruptedException, DocumentException {
	 //Serial Number Error Message
	   boolean serialNumberErr=false;
	   
	   try {
		serialNumberErr = getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEdevice_serialNumberErrmsg")).isDisplayed();
		sa.assertTrue(serialNumberErr, "Serial Number warning message is not displayed ");
	if(serialNumberErr)	{
		System.out.println(" 'Serial number; warning message is dipslaying as expected");
		String serialnumberErrMsg = getwebelement(
				xml.getlocator("//locators/" + application + "/AddCPEdevice_serialNumberErrmsg")).getText();
		System.out.println(
				"Serial Number  message displayed as : " + serialnumberErrMsg);
		DriverTestcase.logger.log(LogStatus.PASS,
				"Step :  validation message for Serial Number field displayed as : " + serialnumberErrMsg);
		Log.info("Serial Number warning message displayed as : " + serialnumberErrMsg);
	}else {
		System.out.println("Serial Number warning message is not dipslaying");
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Serial Number' validation mesage is not displaying");
	}
	   }catch(NoSuchElementException e) {
		   e.printStackTrace();
		   System.out.println("Serial Number Warning message is not diplsying");
		   DriverTestcase.logger.log(LogStatus.FAIL, " 'Serial Number' warning message is not displaying");
	   }
   }
   
   
   public void device_hexaSerialNumberWarningMessage(String application) throws InterruptedException, DocumentException {
		 //Serial Number Error Message
		   boolean HexaserialNumberErr=false;
		   
		   try {
			   HexaserialNumberErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaSerialNumnerErrmsg")).isDisplayed();
			sa.assertTrue(HexaserialNumberErr, "Hexa Serial Number warning message is not displayed ");
			if(HexaserialNumberErr)	{
				System.out.println(" 'Hexa Serial number' warning message is dipslaying as expected");
				String hexaserialnumberErrMsg = getwebelement(
						xml.getlocator("//locators/" + application + "/AddCPEdevice_hexaSerialNumnerErrmsg")).getText();
				System.out.println(
						"Hexa Serial Number  message displayed as : " + hexaserialnumberErrMsg);
				DriverTestcase.logger.log(LogStatus.PASS,
						"Step :  validation message for Hexa Serial Number field displayed as : " + hexaserialnumberErrMsg);
				Log.info("Hexa Serial Number warning message displayed as : " + hexaserialnumberErrMsg);
			}else {
				System.out.println("Hexa Serial Number warning message is not dipslaying");
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Hexa Serial Number' validation mesage is not displaying");
			}
		   }catch(NoSuchElementException e) {
			   e.printStackTrace();
			   System.out.println("Serial Number Warning message is not diplsying");
			   DriverTestcase.logger.log(LogStatus.FAIL, " 'Serial Number' warning message is not displaying");
		   }
	   }
   
   
   public void device_countrywarningMessage(String application) throws InterruptedException, DocumentException {
		
		//Country Error Message
	   boolean countryErr=false;
			countryErr = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_countryErrmsg")).isDisplayed();
			sa.assertTrue(countryErr, "Country warning message is not displayed ");
			
		if(countryErr) {
			System.out.println("country warning message is displaying as expected");
			String countryErrMsg = getwebelement(
					xml.getlocator("//locators/" + application + "/AddCPEdevice_countryErrmsg")).getText();
			System.out.println(
					"Country  message displayed as : " + countryErrMsg);
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step :  validation message for Country field displayed as : " + countryErrMsg);
			Log.info("Country warning message displayed as : " + countryErrMsg);	
		}else {
			System.out.println("Country warning message is not displaying");
			DriverTestcase.logger.log(LogStatus.FAIL, " Validation message for Country dropdown is not displaying");
		}
   }


   public void device_nameField(String application, String cpename, String expectedDeviceNameFieldAutopopulatedValue) {
	   boolean name=false;
		try { 
		 name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).isDisplayed();
		 sa.assertTrue(name, "Name text field is not available under create device for Equipment");
		
		 if(name) {
	          if(cpename.equalsIgnoreCase("null")) {
	        	  System.out.println("No values has been assed for 'Name' text field");
					DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory 'Name' field under 'Add CPE Device' page");
				}
				
			   else {
				   String deviceNameActualPopulatedvalue=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).getAttribute("value");
				   sa.assertEquals(deviceNameActualPopulatedvalue, expectedDeviceNameFieldAutopopulatedValue, "Device Name field Auto Populated value is not displaying as expected");
				   DriverTestcase.logger.log(LogStatus.PASS, " Under 'Name' text field, value displaying by default is: "+deviceNameActualPopulatedvalue);
				   getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).clear();
				   Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, cpename + " is the value passed for Mandatory 'Cpe name' text field");
					}
		 		  }else {
		 			  DriverTestcase.logger.log(LogStatus.FAIL, " Name text field is not displaying under 'Add CPE Device' page");
		 		  }
				}catch(NoSuchElementException e) {
					e.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Name' text field is not available");
				}catch(Exception err) {
					err.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside the 'Name' field");
				}
		
   }
   
   
   public void device_vendorModel(String application, String[] Vender, String vender) throws InterruptedException, DocumentException {
	   boolean vendor=false;
		try {	
			vendor=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")).isDisplayed();
			sa.assertTrue(vendor, "Vender/Model dropdown is not available");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")));
				
		  try {
		  List<WebElement> listofvender = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
				
				if(listofvender.size()>0) {
		
				for (WebElement vendertypes : listofvender) {

					boolean match = false;
					for (int i = 0; i < Vender.length; i++) {
						if (vendertypes.getText().equals(Vender[i])) {
							match = true;
							Log.info("list of vendor under add devices are : " + vendertypes.getText());
							DriverTestcase.logger.log(LogStatus.PASS,"The list of vender/Model under Add device are: "+vendertypes.getText());
							System.out.println("list of vendor under add devices are : " + vendertypes.getText());

						}
						}
					sa.assertTrue(match);
					}
					
				}else {
					System.out.println("dropdown value inside Vender/Model is empty");
					DriverTestcase.logger.log(LogStatus.FAIL, "No values available inside Vender/Model dropdown for adding devices");
				}
				
		  }catch(Exception e) {
			  e.printStackTrace();
			  DriverTestcase.logger.log(LogStatus.FAIL, "Failure at vendor dropdown");
	 	  }
		  
		  
		  //Entering value inside Vendor/Model dropdown
		  try {
				if(vender.equalsIgnoreCase("null")) {
					
					System.out.println("No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device");
					DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory 'Vendor/Model' dropdown for adding device");
					
				}
			else {	
			Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='"+vender +"']"));
			DriverTestcase.logger.log(LogStatus.PASS, vender + " is the value passed for Mandatory 'Vendor/Model' dropdown for adding device");
			System.out.println(vender+" is the value passed for Mandatory 'Vendor/Model' dropdown for adding device");	
			}
				}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "FAilure at Vender/model dropdown. It does not have the value provided as input"
						+ " Value provided is: "+ vender);
			}
		  
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("vendor/Model dropdown is not dipslaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Vendor/Model' dropdown is not displayind in 'Add CPE Device' page");
		}
   }
   
  
   	public void device_snmPro(String application, String snmproValueToBeChanged) {
   		boolean sNmpro=false;
   		try {
   			
   		  sNmpro=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).isDisplayed();
   		  
   		 if(sNmpro) { 
   			 DriverTestcase.logger.log(LogStatus.PASS, " ' Snmpro' field is displaying in 'Add CPE Device' page as expected");
   			 System.out.println("Smpro text field is displaying as expected");
   			 
   			  boolean actualValue_snmpro=getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_snmpro_autoPopulatedValue")).isDisplayed();
   			  if(actualValue_snmpro) {
   				  DriverTestcase.logger.log(LogStatus.PASS, " 'Snmpro' field value is displaying as expected."
   				  		+ " It is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
   				  
   			  }else {
   				  DriverTestcase.logger.log(LogStatus.FAIL, " 'Snmpro' value is not displaying as expected."
   				  		+ " It is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
   			  }
   			  
   			  if(snmproValueToBeChanged.equalsIgnoreCase("null")) {
   				 System.out.println("No changes has been made to 'Snmpro' field"); 
   				 DriverTestcase.logger.log(LogStatus.PASS, " 'Snmpro' field value is not changed");
   				 System.out.println(" 'Snmpro' field is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
   			  }else {
   				getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).clear();
   				Thread.sleep(3000);
   				
   				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")), snmproValueToBeChanged);
   				Thread.sleep(3000);
   				
   				DriverTestcase.logger.log(LogStatus.PASS, " 'Snmro' field value has been changes "
   						+ "And it is displaying as: "+ getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
   			  }
   			  
   		 }else {
   			 DriverTestcase.logger.log(LogStatus.FAIL, " 'Snmpro' field is not available in 'Add CPE Device' page");
   		 }
   		}catch(NoSuchElementException e) {
   			e.printStackTrace();
   			DriverTestcase.logger.log(LogStatus.FAIL, " 'Snm pro' field is not available ");
   		}catch(Exception err) {
   			err.printStackTrace();
   			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in 'Snm pro' field");
   		}
   			
   	}
   	
   	
   	public void device_mepID(String application, String Mepid) {
   		
   		String mepValue="null";
   		boolean mepid=false;
   		try {
   			mepid=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).isDisplayed();
   			DriverTestcase.logger.log(LogStatus.PASS, " ' Snmpro' field is displaying in 'Add CPE Device' page as expected");
   			
   				sa.assertTrue(mepid, "Mepid field under 'Add CPE device' page is not available");
   				
   				if(mepid) {
   					
   					System.out.println("MEP Id  text field is displaying as expected");
   						
   						mepValue=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).getAttribute("value");
   						if(mepValue.equalsIgnoreCase("null")) {
   							DriverTestcase.logger.log(LogStatus.FAIL, " No values are displaying under 'MEP ID' field. It should be auto populated by default");
   							System.out.println(" No values are displaying under 'MEP ID' field. It should be auto populated by default");
   						}else {
   							DriverTestcase.logger.log(LogStatus.PASS, " MEP ID field is auto populated and it is displaying as : "+mepValue);
   							System.out.println( " MEP ID field is auto populated and it is displaying as : "+mepValue);
   						}
   						
   				}	
   			}catch(NoSuchElementException e) {
   				e.printStackTrace();
   				DriverTestcase.logger.log(LogStatus.FAIL, " 'Mep Id' field is not available");
   			}catch(Exception err) {
   				err.printStackTrace();
   				DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside the 'Mep Id' field");
   			}
   	}
   	
   	
   	public void device_powerAlarm(String application, String[] powerAlarm, String poweralarm) throws InterruptedException, DocumentException {
   	 boolean powralrm=false;
	    try {
			powralrm=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")).isDisplayed();
			sa.assertTrue(powralrm, "The poweralarm dropdown under add device is not available");
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")));
			Thread.sleep(3000);
		
	//Check values inside Power Alarm dropdown	
		try {
		List<WebElement> listofalarm = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

		if(listofalarm.size()>0) {	
			for (WebElement alarmtypes : listofalarm) {

				boolean match = false;
				for (int i = 0; i < powerAlarm.length; i++) {
					if (alarmtypes.getText().equals(powerAlarm[i])) {
						match = true;
						Log.info("list of power alarm under add devices are : " + alarmtypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of powerAlarm under Add device are: "+alarmtypes.getText());
					}
					}
				 sa.assertTrue(match);
				}
			   
			}else {
				System.out.println("dropdown value inside Vender/Model is empty");
				DriverTestcase.logger.log(LogStatus.FAIL, "No values available inside power alarm dropdown for adding devices");
			}
		}catch(Exception e) {
			  
			  e.printStackTrace();
			  DriverTestcase.logger.log(LogStatus.FAIL, "value mismatch for poweralarm dropdown");
			  
		  }
		
	//Select value inside power Alarm dropdown	
			if(poweralarm.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory field 'Powre Alarm' for adding device");
				System.out.println("No values has been passed for Power Alarm dropdown mandatory Field");
			}else {
				Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='"+poweralarm +"']"));
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, poweralarm + " is the value passed for Mandatory 'Power Alarm' dropdown field for adding device");
				System.out.println(poweralarm+" is the value passed for Mandatory 'Power Alarm' dropdown field for adding device");
			}
	}catch(NoSuchElementException e) {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Power Alarm' dropdown is not available in 'Add CPE Device' page");
		}

   	}
   	
   	
   	public void device_mediaSelection(String application, String Mediaselection[], String mediaSelection) throws InterruptedException, DocumentException {
   	 boolean media=false;
	    try {
			media=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")).isDisplayed();
			sa.assertTrue(media, "Media selection dropdown under add devices is not available");
			
		try {
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")));
			Thread.sleep(3000);
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "Mediaselection dropdown is not available");
		}
		
	//check list of values inside Media selection dropdown	
		try {	
		List<WebElement> listofmedia = driver.findElements(By.xpath("//div[@role='list']//span[@role='option']"));

		if(listofmedia.size()>0) {
			for (WebElement mediatypes : listofmedia) {

				boolean match = false;
				for (int i = 0; i < Mediaselection.length; i++) {
					if (mediatypes.getText().equals(Mediaselection[i])) {
						match = true;
						Log.info("list of Media selection under add devices are : " + mediatypes.getText());
						DriverTestcase.logger.log(LogStatus.PASS,"The list of media selection under Add device are: "+mediatypes.getText());
					}
					}
				sa.assertTrue(match);
				}
				
			}else {
				System.out.println("dropdown value inside Vender/Model is empty");
				DriverTestcase.logger.log(LogStatus.FAIL, "No values available inside Media selection dropdown for adding devices");
			}
		}catch(Exception e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, "FAilure at Media selection dropdown");
			}
		
	// Select value inside Media Selection dropdown	
			if(mediaSelection.equalsIgnoreCase("null")) {
				System.out.println("Media selection dropdown selected");
				DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory field 'Media selection' for adding device");
				
			}else {
				Clickon(getwebelement("//div[label[text()='Media Selection']]//div[text()='"+mediaSelection +"']"));
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, mediaSelection + " is the value passed for Mandatory 'Media Selection' field in 'Add CPE Device' page");
			}
	    }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	DriverTestcase.logger.log(LogStatus.FAIL, " 'Media Selcetion' dropdown is not available in 'Add CPE Device' page");
	    }
   	}


   	public void device_linklostForwarding(String application, String linkLostForwarding, String state) throws InterruptedException, DocumentException {
   	
   	 boolean linklostenable=false;	
   	 boolean linklost=false;
	    try {
	    	linklost=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();
			sa.assertTrue(linklost, "Link lost Forwarding checkbox under add device is not available");
	    if(linklost) {
	    	DriverTestcase.logger.log(LogStatus.PASS, " 'Link lost Forwarding' checkbox is displaying under 'Add CPE device' page as expected");
	    //Find whether it enabled or disabled
	    	linklostenable=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isEnabled();
	    	if(state.equalsIgnoreCase("disabled")){
	    		if(linklostenable) {
	    			DriverTestcase.logger.log(LogStatus.FAIL, " 'Link lost Forwarding' checkbox is enabled under 'Add CPE device' page");
	    		}else {
	    			DriverTestcase.logger.log(LogStatus.PASS, " 'Link lost Forwarding' checkbox is disabled under 'Add CPE device' page");
	    		}
	    	}
	    	else if(state.equalsIgnoreCase("enabled")) {
	    		if(linklostenable) {
	    			DriverTestcase.logger.log(LogStatus.PASS, " 'Link Lost Forwarding' checkbox is enabled under 'Add CPE device' page");
	    			
	    			//select the checkbox as per input	
	    			boolean linklostSelection=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isSelected();
    				if(linklostSelection) {
    					DriverTestcase.logger.log(LogStatus.PASS, " 'link lostforwarding' is selected by default as expected");
    				
    				//click on link lost checkbox	
    					if(linkLostForwarding.equalsIgnoreCase("Yes")) {
    	    				
    	    				DriverTestcase.logger.log(LogStatus.PASS, " No changes made for 'Link lost Forwarding' checkbox");
    	    			}else{
    	    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
    	    				Thread.sleep(3000);
    	    				DriverTestcase.logger.log(LogStatus.PASS, " Link Lost Forwarding is unselected as expected");
    	    				System.out.println(" Link Lost Forwarding is unselected as expected");
    	    			}
    				}else {
    					DriverTestcase.logger.log(LogStatus.FAIL, " 'link lostforwarding' is not selected by default");
    					System.out.println(" 'link lostforwarding' is not selected by default");
    				}
	    			
	    			
	    		}else {
	    			DriverTestcase.logger.log(LogStatus.FAIL, " 'Link lost Forwarding' checkbox is disabled under 'Add CPE device' page");
	    		}

	    
	    } 	
	    }else {
	    	DriverTestcase.logger.log(LogStatus.FAIL, " 'Link Lost Forwarding' checkbox is not dipslaying under 'Add CPE device' page");
	    	System.out.println(" 'Link Lost Forwarding' checkbox is not displaying under 'Add CPE device' page");
	    }
	    }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	DriverTestcase.logger.log(LogStatus.FAIL, " Link LOst Forwarding checkbox is not displaying in 'Add CPE Device' page");
	    }
   	}


   	public void device_managementAddress(String application, String existingmanagementAddress, String newmanagementAddress, String managementAddress) throws InterruptedException, DocumentException {
   		boolean managementaddresdropdown=false;
   		boolean manageAddresstextField=false;
   		String manageAddresspopulatedValue="null";
   		
   		if((existingmanagementAddress.equalsIgnoreCase("Yes")) & (newmanagementAddress.equalsIgnoreCase("No")))
   		{
   			try {
   				managementaddresdropdown=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")).isDisplayed();
   				sa.assertTrue(managementaddresdropdown, "Management Address dropdown under add device is not available");
   				
   				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_getSubnetbutton")));
   				Thread.sleep(5000);
   				manageAddresspopulatedValue=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")).getAttribute("value");
   				if(manageAddresspopulatedValue.equalsIgnoreCase("null")) {
   					DriverTestcase.logger.log(LogStatus.FAIL, " No values gets populates in 'Manage Address' dropdown, on clicking 'get Subnets' button");
   				}else {
   					DriverTestcase.logger.log(LogStatus.PASS, " Values displaying under 'Manage Addres' dropodwn is : "+ manageAddresspopulatedValue);
   				}
   				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddressdropdown")));
   				Thread.sleep(3000);
   			}catch(Exception e) {
   				e.printStackTrace();
   				DriverTestcase.logger.log(LogStatus.FAIL, " Management Address dropdown is not available in 'Add CPE Device' page");
   			}
   			
   		}
   		
   		
   		else if((existingmanagementAddress.equalsIgnoreCase("No")) & (newmanagementAddress.equalsIgnoreCase("Yes"))) {
   			
   			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_selectSubnettogglebutton")));
   			Thread.sleep(3000);
   		try {	
   			manageAddresstextField=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).isDisplayed();
   			sa.assertTrue(manageAddresstextField, "Manage Address text Field is not displaying in 'Add CPE Device' page");
   			if(manageAddresstextField) {
   					if(managementAddress.equalsIgnoreCase("null")) {
   						DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for Mandatory field 'Manage Address' for adding device");
   					}else {
   						
   						
   				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
   						managementAddress); 
   				Thread.sleep(3000);
   				DriverTestcase.logger.log(LogStatus.PASS, managementAddress + " is the value passed for Mandatory 'Management Address' field in 'Add CPE Device' page");
   					}
   			}else {
   				DriverTestcase.logger.log(LogStatus.FAIL, " 'Management Address' text field is not dipslaying under 'Add CPE device' page");
   			}
   				
   		}catch(NoSuchElementException e) {
   			e.printStackTrace();
   			DriverTestcase.logger.log(LogStatus.FAIL, " 'Manage Address' text field is not available in 'Add CPE Device' page");
   		}catch(Exception err) {
   			err.printStackTrace();
   			DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside the 'Manage Address' field");
   		}
   	}
   	}


public void device_MAcaddress(String application, String macAdressInput) {
   		
   		boolean macAdres=false;
   		try {
   			macAdres=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).isDisplayed();
   				sa.assertTrue(macAdres, "Mepid field under 'Add CPE device' page is not available");
   				
   				if(macAdres) {
   					DriverTestcase.logger.log(LogStatus.PASS, " ' MAC Address' field is displaying in 'Add CPE Device' page as expected");
   					System.out.println(" 'MAC Address'  text field is displaying as expected");
   					
   					if(macAdressInput.equalsIgnoreCase("null")) {
   						DriverTestcase.logger.log(LogStatus.FAIL, "No values has been passed for 'MAC Address' text field for adding device");
   						System.out.println("No values has been passed for 'MAC Address' mandaotyr field");
   						
   					}else {
   						
   						SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), macAdressInput);
   						Thread.sleep(3000);
   						
   						DriverTestcase.logger.log(LogStatus.PASS, macAdressInput+ " is entered under 'MAc Address' text field" );
   						System.out.println(macAdressInput+ " is entered under 'MAc Address' text field");
   						
   					}
   				}	
   			}catch(NoSuchElementException e) {
   				e.printStackTrace();
   				DriverTestcase.logger.log(LogStatus.FAIL, " 'MAC Address' text field is not available");
   			}catch(Exception err) {
   				err.printStackTrace();
   				DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside the 'MAC Address' text field");
   			}
   	}


	public void device_okbutton(String application) throws InterruptedException, DocumentException {
		
	//OK button
	    boolean Ok=false;
	    try {
			Ok=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_OKbutton")).isDisplayed();
			sa.assertTrue(Ok, "OK button under add device is not available");
			
			if(Ok) {
				System.out.println(" 'OK' button is displaying under 'Add CPE deivce' page as expected");
				DriverTestcase.logger.log(LogStatus.PASS, " 'OK' button is displaying under 'Add CPE deivce' page as expected");
			}else {
				System.out.println(" 'OK' button is not displaying under 'Add CPE device' page");
				DriverTestcase.logger.log(LogStatus.FAIL, " 'OK' button is not displaying under 'Add CPE device' page");
			}
	    }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	DriverTestcase.logger.log(LogStatus.FAIL, " OK button is not available in 'Add CPE Device' page");
	    }
	    
	}
	
	
	public void device_cancelButton(String application) throws InterruptedException, DocumentException {
		
		 //Cancel button
	    boolean cancel=false;
	    try {
	    cancel=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_cancelbutton")).isDisplayed();
			sa.assertTrue(cancel, "cancel button under add device is not available");
			
			if(cancel) {
				System.out.println(" 'Cancel' button is displaying under 'Add CPE deivce' page as expected");
				DriverTestcase.logger.log(LogStatus.PASS, " 'Cancel' button is displaying under 'Add CPE deivce' page as expected");
			}else {
				System.out.println(" 'Cancel' button is not displaying under 'Add CPE device' page");
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Cancel' button is not displaying under 'Add CPE device' page");
			}
	    }catch(NoSuchElementException e) {
	    	e.printStackTrace();
	    	DriverTestcase.logger.log(LogStatus.FAIL, " Cancel button is not available in 'Add CPE Device' page");
	    }
	}
	
	
	public void device_serialNumber(String application, String serialNumber) throws InterruptedException, IOException, DocumentException {
		
		 //Serial Number
		   boolean serialNmber=false;
		   try {
			   serialNmber=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).isDisplayed();
			   sa.assertTrue(serialNmber, "Serial Number is not available in 'Add CPE Device' page");
			 if(serialNmber) {  
			   if(serialNumber.equalsIgnoreCase("null")) {
				   DriverTestcase.logger.log(LogStatus.FAIL, " 'Serial Number' is a mandatory field. no values has been passed as an input");
			   }else {
				   
				   SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")), serialNumber);
				   Thread.sleep(3000);
				   String SNactualValue=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).getAttribute("value");
				   DriverTestcase.logger.log(LogStatus.PASS, SNactualValue + " is the value entered in 'Serial number' field in 'Add CPE Device' page");
			   }
			 }  
		   }catch(NoSuchElementException e) {
			   e.printStackTrace();
			   System.out.println(" 'Serial Number' text field is not displaying");
			   DriverTestcase.logger.log(LogStatus.FAIL, " 'Serial Number' text field is not dipslaying under 'Add CPE device' page");
		   }catch(Exception er) {
			   er.printStackTrace();
			   System.out.println("not able to enter value under 'Serial number' textfield");
			   DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value under 'Serial Number' text field");
		   }
	}
	
	
	public void device_country(String application, String country) throws InterruptedException, DocumentException {
	
		boolean countryDrpodown=false;
	try {	
		//Country dropdown
		
		countryDrpodown=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_countrydiv")).isDisplayed();
		Thread.sleep(3000);
	 } catch(NoSuchElementException e) {
  	   e.printStackTrace();
  	   System.out.println(" 'Country' dropown is not displaying");
  	   DriverTestcase.logger.log(LogStatus.FAIL, " 'Country' dropown is not displaying");
     }
		

		if(countryDrpodown) {
			System.out.println(" 'Country' dropodwn is displaying as expected");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Country' dropodwn is displaying as expected");
			
	//verify dropdown values		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_countrydiv")));
		Thread.sleep(5000);
		System.out.println("Clicked on Country dropdown");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on Country dropdown");
		Log.info("Clicked on Country dropdown");

		List<WebElement> cntrylist = driver.findElements(By.xpath("//span[@role='option']"));
		for (WebElement countrylist : cntrylist) {

			System.out.println("Available Country name is : " + countrylist.getText().toString());
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step : Available Country name is : " + countrylist.getText().toString());
			Log.info("Available Country name is :" + countrylist.getText().toString());
		}
		
		
	//Select value inside country dropdown	
	  try {		
			if(country.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Country' dropdown is a mandatory field and no values has been provided as input");
				
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_countrydiv")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[label[text()='Country']]//div[text()='"+country +"']"));
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, country + " is the value passed for 'Country' field for adding device");
			}
		
       } catch(NoSuchElementException e) {
    	   e.printStackTrace();
    	   System.out.println(country+" is not present inside 'Country' dropdown");
    	   DriverTestcase.logger.log(LogStatus.FAIL,  country+" is not present inside 'Country' dropdown");
       } catch(Exception er) {
    	   er.printStackTrace();
    	   System.out.println("Not able to select value inside country dropdown");
    	   
       }
		}else {
			System.out.println("Coutry dropodwn is not displaying");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Country' dropdown is not displaying");
		}
	}
	

	public void device_verifycity(String application) throws InterruptedException, DocumentException {
		
		//City dropdown
		boolean citydropdown=false;
		citydropdown=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip__citydiv")).isDisplayed();
		
		if(citydropdown) {
			System.out.println(" 'City' dropdown is displaying as expected");
			DriverTestcase.logger.log(LogStatus.PASS, " 'City' dropdown is displaying as expected");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip__citydiv")));
		Thread.sleep(5000);
		System.out.println("Clicked on City dropdown");
		String actualvalue=getwebelement("(//div[label[text()='Country']]//span)[2]").getText();
		DriverTestcase.logger.log(LogStatus.PASS, "For the selected country '"+ actualvalue + "' list of city populated are:  ");
		Log.info("Clicked on City dropdown");

		List<WebElement> citylist = driver.findElements(By.xpath("//span[@role='option']"));
		for (WebElement ctylist : citylist) {

			System.out.println("Available City name is : " + ctylist.getText().toString());
			DriverTestcase.logger.log(LogStatus.PASS,
					"Step : Available City name is : " + ctylist.getText().toString());
			Log.info("Available City name is :" + ctylist.getText().toString());
		}
	}else {
		System.out.println(" 'City' dropdown is not displaying");
		DriverTestcase.logger.log(LogStatus.FAIL, " 'City' dropdown is not displaying under 'Add CPE device' page");
	}
		
		
	//Site dropdown	
		boolean siteDropdown=false;
		try {
			siteDropdown=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitediv")).isDisplayed();
		if(siteDropdown) {
			System.out.println(" 'Site' dropdown is displaying as expected");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Site' dropodwn is dipslaying as expected");
		}else {
			System.out.println(" 'Site' dropdown is not displaying");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Site' dropodwn is not dipslaying");
		}}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'site' dropdown field is not displaying");
			System.out.println(" 'Site' dropdown field is not displaying");
		}
		
		
		//Premise dropdown
		boolean premiseDropdown=false;
		try {
		premiseDropdown=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisediv")).isDisplayed();
		
		if(premiseDropdown) {
			System.out.println(" 'Premise' dropdown is displaying as expected");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Premise' dropdown is displaying as expected");
		}else {
			System.out.println(" 'Premise' dropdown is not displaying");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Premise' dropdown is not displaying");
		}}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Premise' dropdown field is not displaying");
			System.out.println(" 'Premise' dropdown field is not displaying");
		}
	

		
	//Select City toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citytogglebutton")));
		DriverTestcase.logger.log(LogStatus.PASS, "cliked on 'Select City' toggle button");
		
	//City name
		boolean cityname=false;
	try {	
		cityname=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citynamefield")).isDisplayed();
		sa.assertTrue(cityname, " 'City name' field is not getting displyed");
		if(cityname) {
			System.out.println(" 'City name' text field is displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.PASS, " 'City name' text field is displaying under 'Add CPE device' page");
		}else {
			System.out.println(" 'City name' text field is not displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'City name' text field is not displaying under 'Add CPE device' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " 'City name' text field is not displaying");
		System.out.println(" 'City name' text field is not displaying");
	}
		
	//City Code
		boolean citycode=false;
		try {
		citycode=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_citycodefield")).isDisplayed();
		sa.assertTrue(citycode, " 'City Code' field is not getting displyed");
		if(citycode) {
			System.out.println(" 'City Code' text field is displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.PASS, " 'City Code' text field is displaying under 'Add CPE device' page");
		}else {
			System.out.println(" 'City Code' text field is not displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'City Code' text field is not displaying under 'Add CPE device' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'City Code' text field is not displaying");
			System.out.println(" 'City Code' text field is not displaying");
		}
		
		
		
	//Site name
		boolean sitename=false;
		try {
		sitename=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitenamefield")).isDisplayed();
		sa.assertTrue(sitename, " 'Site name' field is not getting displyed");
		if(sitename) {
			System.out.println(" 'Site Name' text field is displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Site Name' text field is displaying under 'Add CPE device' page");
		}else {
			System.out.println(" 'Site Name' text field is not displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Site Name' text field is not displaying under 'Add CPE device' page");
		}}catch(NoSuchElementException ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Site Name' text field is not displaying");
			System.out.println(" 'Site Name' text field is not displaying");
		}
				
	//Site Code
		boolean sitecode=false;
		try {
		sitecode=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_sitecodefield")).isDisplayed();
		sa.assertTrue(sitecode, " 'Site Code' field is not getting displyed");
		if(sitecode) {
			System.out.println(" 'Site Code' text field is displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Site Code' text field is displaying under 'Add CPE device' page");
		}else {
			System.out.println(" 'Site Code' text field is not displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Site Code' text field is not displaying under 'Add CPE device' page");
		}}catch(NoSuchElementException eee) {
			eee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Site Code' text field is not displaying");
			System.out.println(" 'Site Code' text field is not displaying");
		}
					
					
	//Premise name
		boolean premisename=false;
		try {
		premisename=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisenamefield")).isDisplayed();
		sa.assertTrue(premisename, " 'Premise name' field is not getting displyed");
		if(premisename) {
			System.out.println(" 'Premise Name' text field is displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Premise Name' text field is displaying under 'Add CPE device' page");
		}else {
			System.out.println(" 'Premise Name' text field is not displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Premise Name' text field is not displaying under 'Add CPE device' page");
		}}catch(NoSuchElementException p) {
			p.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Premise Name' text field is not displaying");
			System.out.println(" 'Premise Name' text field is not displaying");
		}
						
	//Premise Code
		boolean premisecode=false;
		try {
		premisecode=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_premisecodefield")).isDisplayed();
		sa.assertTrue(premisecode, " 'Premise Code' field is not getting displyed");
		if(premisecode) {
			System.out.println(" 'Premise Code' text field is displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.PASS, " 'Premise Code' text field is displaying under 'Add CPE device' page");
		}else {
			System.out.println(" 'Premise Code' text field is not displaying under 'Add CPE device' page");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Premise Code' text field is not displaying under 'Add CPE device' page");
		}
		}catch(NoSuchElementException pp) {
			pp.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Premise Code' text field is not displaying");
			System.out.println(" 'Premise Code' text field is not displaying");
		}
		
	//Click on disabled City toggle button
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEforIntermediateEquip_disabledCityToggleButton")));
		Thread.sleep(5000);
	}
	

	public void deviceCreatoin_Overture(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String City,
			String Site, String Premise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, DocumentException, IOException {
	
	try {	
		String linklostForwardingcheckboxstate="enabled"; 
		
		String[] Vender= {"Overture ISG-26", "Overture ISG-26R", "Overture ISG-26S", "Overture ISG140", "Overture ISG180", "Overture ISG6000"};
		
		String[] powerAlarm= {"AC", "DC"};
		
		String[] MediaSelectionExpectedValue= {"SFP-A with SFP-B","RJ45-A with SFP-B"};	
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>.lanlink.dcn.colt.net";
		
	scrolltoend();
	Thread.sleep(3000);
	
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(3000);
		
		//Country Error Message
		device_countrywarningMessage(application);
		
		//Media Selection Error Message
		device_mediaSelectionWarningMessage(application);
		
	ScrolltoElement(getwebelement("//label[text()='Name']"));
	Thread.sleep(3000);
	
		//name field Error Message
		device_nameFieldWarningMessage(application);
		
		// Vendor/Model Error Message
		device_vendorModelWarningMessage(application);

		// Management Address Error Message
		device_managementAddressWarningMessage(application);

		// Power Alarm Error Message
		device_powerAlarmWarningMessage(application);
			
		//MAC Address Error Message
		device_macAddressWarningMessage(application);
		
		
			

		//Name
		device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
		
		//Vendor/Model
		device_vendorModel(application, Vender, vender);      
	
		//Snmpro
		device_snmPro(application, snmpro);
		
		//Management Address dropdown
		device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
		
		//MEP Id
		device_mepID(application, Mepid);
	
		//Power Alarm	
		device_powerAlarm(application, powerAlarm, poweralarm);
		
		//MAC Address
		device_MAcaddress(application, Macaddress);
	
	scrolltoend();
	Thread.sleep(3000);
	
		//Media Selection
		device_mediaSelection(application, MediaSelectionExpectedValue, MediaselectionActualValue);
		
	    //Link lost Forwarding
		device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
	    
	
		//Country
		device_country(application, country);
	
		//verify fields for City, Site and premise
		device_verifycity(application);

		//City		
		if(existingcityselectionmode.equalsIgnoreCase("no") & newcityselectionmode.equalsIgnoreCase("yes")) {
			addCityToggleButton(application);
		   //New City	
			newcity(application, newcityselectionmode, cityname, citycode);
		      // New Site
			newSite(application, newsiteselectionmode, sitename, sitecode);
			  //New Premise	
			newPremise(application, newpremiseselectionmode, premisename, premisecode);
				
		}	
			
		else if(existingcityselectionmode.equalsIgnoreCase("yes") & newcityselectionmode.equalsIgnoreCase("no")) {
		//Existing City		
		   existingCity(application, existingcityselectionmode, City);
			
			//Site
		 
			  if(existingsiteselectionmode.equalsIgnoreCase("yes") & newsiteselectionmode.equalsIgnoreCase("no")) {
				//Existing Site 
				  existingSite(application, existingsiteselectionmode, Site);
				  
				   //Premise
				  if(existingpremiseselectionmode.equalsIgnoreCase("yes") & newpremiseselectionmode.equalsIgnoreCase("no")) {
					  existingPremise(application, existingpremiseselectionmode, Premise);
				  
		          	 }
				  else if(existingpremiseselectionmode.equalsIgnoreCase("no") & newpremiseselectionmode.equalsIgnoreCase("yes")) {
					  //New Premise
					    addPremiseTogglebutton(application);
					    newPremise_clickOnPremisetoggleButton(application, newpremiseselectionmode, premisename, premisecode);
				  } 
			
				
	         	}
  		
		  else if(existingsiteselectionmode.equalsIgnoreCase("no") & newsiteselectionmode.equalsIgnoreCase("yes")) {
			  	//New Site 
			  	addSiteToggleButton(application);
			  	newSite_ClickOnSiteTogglebutton(application, newsiteselectionmode, sitename, sitecode); 
			  	
			  	//New Premise
			  	newPremise_clickonSiteToggleButton(application, newpremiseselectionmode, premisename, premisecode);
			  }
		}
		
		 //OK button
		device_okbutton(application);
		
		//cancel button
		device_cancelButton(application);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(5000);
			
		sa.assertAll();
		
	}catch(AssertionError e) {
		
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
		
	}
	DriverTestcase.logger.log(LogStatus.INFO, "Input data has been passed for creating device");
	Thread.sleep(3000);

	}
	
	
	public void deviceCreatoin_Accedian(String application, String cpename, String vender, String snmpro,
			String managementAddress, String Mepid, String poweralarm, String MediaselectionActualValue, String Macaddress,
			String serialNumber, String hexaSerialnumber, String linkLostForwarding, String country, String City,
			String Site, String Premise, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingcityselectionmode, String newcityselectionmode, String cityname, String citycode, String existingsiteselectionmode, String newsiteselectionmode,
			String sitename, String sitecode, String existingpremiseselectionmode, String newpremiseselectionmode, String premisename, String premisecode) throws InterruptedException, DocumentException, IOException {
	
	try {	  
		String linklostForwardingcheckboxstate="disabled"; 
		
		String[] Vender= {"Accedian-1G 1GigE-MetroNID-GT", "Accedian-1G 1GigE-MetroNID-GT-S", "Accedian-1G GX"};
		
		String[] powerAlarm= {"AC", "DC"};
		
		String expectedDeviceNameFieldAutopopulatedValue="<Device>.lanlink.dcn.colt.net";
		

	scrolltoend();
	Thread.sleep(3000);
	
		Clickon(getwebelement("//span[contains(text(),'OK')]"));
		Thread.sleep(3000);
		
		//Country Error Message
		device_countrywarningMessage(application);
		
		//serial Number Error Message
		device_serialNumberWarningMessage(application);
		
		//Hexa Serial Number Error Message
		device_hexaSerialNumberWarningMessage(application);
		
	ScrolltoElement(getwebelement("//label[text()='Name']"));
	Thread.sleep(3000);
	
		//name field Error Message
		device_nameFieldWarningMessage(application);
		
		// Vendor/Model Error Message
		device_vendorModelWarningMessage(application);

		// Management Address Error Message
		device_managementAddressWarningMessage(application);

		// Power Alarm Error Message
		device_powerAlarmWarningMessage(application);
			
		
		//Name
		device_nameField(application, cpename, expectedDeviceNameFieldAutopopulatedValue);
		
		//Vendor/Model
		device_vendorModel(application, Vender, vender);      
	
		//Snmpro
		device_snmPro(application, snmpro);
		
		//Management Address dropdown
		device_managementAddress(application, existingmanagementAddress, newmanagementAddress, managementAddress);
		
		//MEP Id
		device_mepID(application, Mepid);
	
		//Power Alarm	
		device_powerAlarm(application, powerAlarm, poweralarm);
		
		//serial number
		device_serialNumber(application, serialNumber);
		
	scrolltoend();
	Thread.sleep(3000);
	
	    //Link lost Forwarding
//		device_linklostForwarding(application, linkLostForwarding, linklostForwardingcheckboxstate);
	    
	
		//Country
		device_country(application, country);
	
		//verify fields for City, Site and premise
		device_verifycity(application);

		//City		
		if(existingcityselectionmode.equalsIgnoreCase("no") & newcityselectionmode.equalsIgnoreCase("yes")) {
			addCityToggleButton(application);
		   //New City	
			newcity(application, newcityselectionmode, cityname, citycode);
		      // New Site
			newSite(application, newsiteselectionmode, sitename, sitecode);
			  //New Premise	
			newPremise(application, newpremiseselectionmode, premisename, premisecode);
				
		}	
			
		else if(existingcityselectionmode.equalsIgnoreCase("yes") & newcityselectionmode.equalsIgnoreCase("no")) {
		//Existing City		
		   existingCity(application, existingcityselectionmode, City);
			
			//Site
		 
			  if(existingsiteselectionmode.equalsIgnoreCase("yes") & newsiteselectionmode.equalsIgnoreCase("no")) {
				//Existing Site 
				  existingSite(application, existingsiteselectionmode, Site);
				  
				   //Premise
				  if(existingpremiseselectionmode.equalsIgnoreCase("yes") & newpremiseselectionmode.equalsIgnoreCase("no")) {
					  existingPremise(application, existingpremiseselectionmode, Premise);
				  
		          	 }
				  else if(existingpremiseselectionmode.equalsIgnoreCase("no") & newpremiseselectionmode.equalsIgnoreCase("yes")) {
					  //New Premise
					    addPremiseTogglebutton(application);
					    newPremise_clickOnPremisetoggleButton(application, newpremiseselectionmode, premisename, premisecode);
				  } 
			
				
	         	}
  		
		  else if(existingsiteselectionmode.equalsIgnoreCase("no") & newsiteselectionmode.equalsIgnoreCase("yes")) {
			  	//New Site 
			  	addSiteToggleButton(application);
			  	newSite_ClickOnSiteTogglebutton(application, newsiteselectionmode, sitename, sitecode); 
			  	
			  	//New Premise
			  	newPremise_clickonSiteToggleButton(application, newpremiseselectionmode, premisename, premisecode);
			  }
		}
				
		 //OK button
		device_okbutton(application);
		
		//cancel button
		device_cancelButton(application);
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		Thread.sleep(5000);
			
		sa.assertAll();
		
	}catch(AssertionError e) {
		
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, "FAiled while verify the fields for Add CPE device");
		
	}
	DriverTestcase.logger.log(LogStatus.INFO, "Input data has been passed for creating device");
	Thread.sleep(3000);

	}
	
	
	public void viewdevice_Overture(String application, String cpename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String Mediaselection,
			String Macaddress, String linkLostForwarding, String existingcountry, String existingCity,
			String newCity, String existingSite, String newSite, String existingPremise, String newPremise, 
			String existingcityselectionmode, String newcityselectionmode, String existingsiteselectionmode, 
			String newsiteselectionmode, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingpremiseselectionmode, String newpremiseselectionmode) throws InterruptedException {
		
		DriverTestcase.logger.log(LogStatus.INFO, "verify the details entered while creating device");
		
		String[] RouterId=new String[2];
		RouterId=cpename.split(".lanlink");
		
		String RouterIdValue=RouterId[0];
		
		
		String mediaSelectionValueInViewDevicePage="no";
		if(Mediaselection.equalsIgnoreCase("null")) {
			Mediaselection=mediaSelectionValueInViewDevicePage;
		}else {
			Mediaselection=mediaSelectionValueInViewDevicePage;
		}
	  
	  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
	  
	  verifyEnteredvalues("Router Id", RouterIdValue);
	  
	  verifyEnteredvalues("Vendor/Model", vender);
	  
	  verifyEnteredvalues("Snmpro", snmpro);
	  
	//Management Address  
	  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) || (newmanagementAddress.equalsIgnoreCase("no"))) {
		  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
		 }
		 else if((existingmanagementAddress.equalsIgnoreCase("no")) || (newmanagementAddress.equalsIgnoreCase("Yes"))) {
			 verifyEnteredvalues("Management Address", managementAddress);
		 } 
	  
	  
//	  verifyEnteredvalues("MEP Id", Mepid);
	  
	  verifyEnteredvalues("Power Alarm", poweralarm);
	  
	  verifyEnteredvalues("Media Selection", Mediaselection);
	  
	  verifyEnteredvalues("MAC Address", Macaddress);
	  
	  verifyEnteredvalues("Link Lost Forwarding", linkLostForwarding);
	  
	  verifyEnteredvalues("Country", existingcountry);
	
	//City  
	 if((existingcityselectionmode.equalsIgnoreCase("Yes")) || (newcityselectionmode.equalsIgnoreCase("no"))) {
		 verifyEnteredvalues("City", existingCity);
	 }
	 else if((existingcityselectionmode.equalsIgnoreCase("no")) || (newcityselectionmode.equalsIgnoreCase("Yes"))) {
		 verifyEnteredvalues("City", newCity);
	 } 
	 
	 
	//Site
	 if((existingsiteselectionmode.equalsIgnoreCase("Yes")) || (newsiteselectionmode.equalsIgnoreCase("no"))) {
		 verifyEnteredvalues("Site", existingSite);
	 }
	 else if((existingsiteselectionmode.equalsIgnoreCase("no")) || (newsiteselectionmode.equalsIgnoreCase("Yes"))) {
		 verifyEnteredvalues("Site", newSite);
	 } 
	 
	 
	//Premise
	 if((existingpremiseselectionmode.equalsIgnoreCase("Yes")) || (newpremiseselectionmode.equalsIgnoreCase("no"))) {
		 verifyEnteredvalues("Site", existingPremise);
	 }
	 else if((existingpremiseselectionmode.equalsIgnoreCase("no")) || (newpremiseselectionmode.equalsIgnoreCase("Yes"))) {
		 verifyEnteredvalues("Site", newPremise);
	 } 
	  
	  
	  

	}


	public void viewdevice_Accedian(String application, String cpename, String vender,
			String snmpro, String managementAddress, String Mepid, String poweralarm, String Mediaselection,
			String Macaddress, String serialNumber, String hexaSerialnumber, String linkLostForwarding, String existingcountry, String existingCity,
			String newCity, String existingSite, String newSite, String existingPremise, String newPremise, 
			String existingcityselectionmode, String newcityselectionmode, String existingsiteselectionmode, 
			String newsiteselectionmode, String newmanagementAddress, String existingmanagementAddress, String manageaddressdropdownvalue,
			String existingpremiseselectionmode, String newpremiseselectionmode) throws InterruptedException {
		
			DriverTestcase.logger.log(LogStatus.INFO, "verify the details entered while creating device");
			
			String[] RouterId=new String[2];
			RouterId=cpename.split(".lanlink");
			
			String RouterIdValue=RouterId[0];
			
			
			String mediaSelectionValueInViewDevicePage="no";
			if(Mediaselection.equalsIgnoreCase("null")) {
				Mediaselection=mediaSelectionValueInViewDevicePage;
			}else {
				Mediaselection=mediaSelectionValueInViewDevicePage;
			}
		  
		  verifyEnteredvalues_deviceName("Name", RouterIdValue,cpename );
		  
		  verifyEnteredvalues("Router Id", RouterIdValue);
		  
		  verifyEnteredvalues("Vendor/Model", vender);
		  
		  verifyEnteredvalues("Snmpro", snmpro);
		  
		//Management Address  
		  	if((existingmanagementAddress.equalsIgnoreCase("Yes")) || (newmanagementAddress.equalsIgnoreCase("no"))) {
			  verifyEnteredvalues("Management Address", manageaddressdropdownvalue);
			 }
			 else if((existingmanagementAddress.equalsIgnoreCase("no")) || (newmanagementAddress.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("Management Address", managementAddress);
			 } 
		  
		  verifyEnteredvalues("Power Alarm", poweralarm);
		  
		  verifyEnteredvalues("Media Selection", Mediaselection);
		  
//		  verifyEnteredvalues("MAC Address", hexaSerialnumber);
		  
		  verifyEnteredvalues("Serial Number", serialNumber);
		  
//		  verifyEnteredvalues("Link Lost Forwarding", linkLostForwarding);

		//City  
			 if((existingcityselectionmode.equalsIgnoreCase("Yes")) || (newcityselectionmode.equalsIgnoreCase("no"))) {
				 verifyEnteredvalues("City", existingCity);
			 }
			 else if((existingcityselectionmode.equalsIgnoreCase("no")) || (newcityselectionmode.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("City", newCity);
			 } 
			 
			 
			//Site
			 if((existingsiteselectionmode.equalsIgnoreCase("Yes")) || (newsiteselectionmode.equalsIgnoreCase("no"))) {
				 verifyEnteredvalues("Site", existingSite);
			 }
			 else if((existingsiteselectionmode.equalsIgnoreCase("no")) || (newsiteselectionmode.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("Site", newSite);
			 } 
			 
			 
			//Premise
			 if((existingpremiseselectionmode.equalsIgnoreCase("Yes")) || (newpremiseselectionmode.equalsIgnoreCase("no"))) {
				 verifyEnteredvalues("Site", existingPremise);
			 }
			 else if((existingpremiseselectionmode.equalsIgnoreCase("no")) || (newpremiseselectionmode.equalsIgnoreCase("Yes"))) {
				 verifyEnteredvalues("Site", newPremise);
			 } 
	}
	
	
	public void device_editnamefield(String application, String cpename) {
		
		 boolean name=false;
			try {
				
				name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).isDisplayed();
				Thread.sleep(3000);
				 
		if(name) {	
			DriverTestcase.logger.log(LogStatus.PASS, " 'Name' field is displaying under 'Edit CPE device' page");
			
			if(cpename.equalsIgnoreCase("null")) {
				
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for 'Name' field while editing");
				
			}else {
				getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).clear();
				Thread.sleep(3000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")), cpename);
				Thread.sleep(3000);
				 
				String actualValue_Name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_Name")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, actualValue_Name+ " is the edited value for 'Name' field");
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Name' field is not available under 'Edit CPE device' page");
		}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " CPE Name field is not available ");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in CPE name field");
			}
	}
	
	
	public void device_editVendorModelField(String application, String vender) {
		
		boolean vend0r=false;
		try {	
			
			vend0r=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_vender")).isDisplayed();
			Thread.sleep(3000);
		if(vend0r) {
			
			DriverTestcase.logger.log(LogStatus.PASS, " 'Vendor/Model' dropdown is displaying in 'Edit CPE Device' page");
			
			if(vender.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for 'Vender/Model' dropdown while editing");
				
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevice_vendoModel_xbutton")));
				Thread.sleep(3000);
				
				Clickon(getwebelement("//div[label[text()='Vendor/Model']]//div[text()='"+vender +"']"));
				DriverTestcase.logger.log(LogStatus.PASS, vender+ " is the edited value for 'vender/Model' field");
			}
		  }else {
			  DriverTestcase.logger.log(LogStatus.FAIL, " 'Vendor/Model' dropdown is not available in 'Edit CPE Device' page");
		  }
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Vender/Model' dropdown is not available");
		}
	}
	
	
	public void device_editSnmproField(String application) {
		
		boolean sNmpro=false;
		try {
			
		  sNmpro=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).isDisplayed();
		  
		 if(sNmpro) { 
			 DriverTestcase.logger.log(LogStatus.PASS, " ' Snmpro' field is displaying in 'Edit CPE Device' page as expected");
			 
			  boolean actualValue_snmpro=getwebelement(xml.getlocator("//locators/" + application + "/CPEdevice_snmpro_autoPopulatedValue")).isDisplayed();
			  if(actualValue_snmpro) {
				  DriverTestcase.logger.log(LogStatus.PASS, " 'Snmpro' field value is displaying as expected."
				  		+ " It is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
				  
			  }else {
				  DriverTestcase.logger.log(LogStatus.FAIL, " 'Snmpro' value is not displaying as expected."
				  		+ " It is displaying as: "+getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_snmpro")).getAttribute("value"));
			  }
			  
			  DriverTestcase.logger.log(LogStatus.PASS, "No changes made for 'snmpro' field while editing cpe device under Equipment");
			  
		 }else {
			 DriverTestcase.logger.log(LogStatus.FAIL, " 'Snmpro' field is not available in 'Edit CPE Device' page");
		 }
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Snm pro' field is not available ");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in 'Snm pro' field");
		}
	}
	
	
	public void device_editManagementAddressField(String application, String managementAddress) {
		
		boolean manageAddressAvailability=false;
		try {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_selectSubnettogglebutton")));
			Thread.sleep(3000);
			
			manageAddressAvailability=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).isDisplayed();
			Thread.sleep(3000);
			
			if(manageAddressAvailability) {
				DriverTestcase.logger.log(LogStatus.PASS, "' Management Address' text field is displaying in 'Edit CPE Device' page as expected");
				
				if(managementAddress.equalsIgnoreCase("null")) {
					
					DriverTestcase.logger.log(LogStatus.PASS, "No changes made for 'Manage address' field while editing cpe device under Equipment");
					
				}else {
					
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")).clear();
					Thread.sleep(3000);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_manageaddress")),
					managementAddress);
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, managementAddress+ " is the edited value for 'Manage address' field");
				}
				
			}
			
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Manage Address' field is not available ");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in 'Manage Addres' field");
		}
	}
	
	
	public void device_editMEPIdField(String application, String Mepid) {
		
		boolean mepIdavailability=false;
		try {
			mepIdavailability =getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).isDisplayed();
		if(mepIdavailability) {	
			
			DriverTestcase.logger.log(LogStatus.PASS, " 'Mep Id' field is displaying under 'Edit CPE device' page as expected");
			if(Mepid.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for 'mepid' field while editing cpe device under Equipment");
			}else {
				getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")).clear();
				Thread.sleep(3000);
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mepid")), Mepid);
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS,  Mepid+ " is the edited value for 'Mepid' field");
			}
		  }
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Mep id' field is not available ");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in 'Mep Id' field");
		}
	}
	
	
	public void device_editPowerAlarm(String application, String poweralarm) throws InterruptedException, DocumentException {
		
		boolean powerAlarm=false;
		try {
			
			powerAlarm=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_poweralarm")).isDisplayed();
			
		if(powerAlarm) {	
			
			DriverTestcase.logger.log(LogStatus.PASS, " 'POwer Alarm' field is displaying in 'Edit CPE device' page as expected");
			if(poweralarm.equalsIgnoreCase("null")) {
				
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for 'Power alarm' dropdown while editing cpe device under Equipment");
				
			}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevice_powerAlarm_xbutton")));
				Thread.sleep(3000);
				
				Clickon(getwebelement("//div[label[text()='Power Alarm']]//div[text()='"+poweralarm +"']"));
				DriverTestcase.logger.log(LogStatus.PASS, poweralarm+ " is the edited value for 'Power Alarm' field");
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Power Alarm' field is not available in 'Edit CPE device' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'power alarm' mandatory dropdown is not available");
		}catch(Exception er) {
			er.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to enter value under 'Power Alarm' dropdown");
		}
	    
	}
	
	
	public void device_editMediaselection(String application, String Mediaselection) throws InterruptedException, DocumentException {
		
		boolean mediaSelection1=false;
		try {
			
			mediaSelection1=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_mediaselection")).isDisplayed();
		if(mediaSelection1)	{
			
			DriverTestcase.logger.log(LogStatus.PASS, " 'Media Selection' dropdown is displaying in 'Edit CPE device' page as expected");
			if(Mediaselection.equalsIgnoreCase("null")) {
				
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for 'Media selection' dropdown while editing cpe device under Equipment");
				
			}else {
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EditCPEdevice_mediaselection_xbutton")));
				Thread.sleep(5000);
				
				Clickon(getwebelement("//div[label[text()='Media Selection']]//div[text()='"+Mediaselection +"']"));
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, Mediaselection+ " is the edited value for 'Media Selection' field");
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Media selection' dropdown is not avilable in 'Edit CPE device' page");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Media selection' mandatory dropdown is not available");
		}catch(Exception er) {
			er.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in 'Media Selection' field");
			
		}
	}
	
	
	public void device_editMACaddress(String application, String Macaddress) {
		
		 boolean macAddress=false;
		    try {
		    	macAddress=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).isDisplayed();
		    	
		    	if(macAddress) {
		    		DriverTestcase.logger.log(LogStatus.PASS, " 'MAC Address' field is displaying in 'Edit CPE device' page as expected");
		    	if(Macaddress.equalsIgnoreCase("null")) {
		    		DriverTestcase.logger.log(LogStatus.PASS, "No changes made for 'Mac address' field while editing cpe device under Equipment");
				}else {
					getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).clear();
					Thread.sleep(3000);
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")), Macaddress);
					String actualValue_MacAddress=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_macaddress")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, actualValue_MacAddress+ " is the edited value for 'Macaddress' field");
				}
		    	}else {
		    		DriverTestcase.logger.log(LogStatus.FAIL, " 'MAC Address' field is not available in 'Edit CPE device' page");
		    	}
		    }catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Mac Address' field is not available ");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in 'Mac Address' field");
			}
	}
	
	
	public void device_editlinkLostforwarding(String application, String linkLostForwarding) {
		
		 boolean linklostcheckbox=false;
		    try { 
		    	linklostcheckbox=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();
		    	
		    if(linklostcheckbox) {	
		    	
		    	DriverTestcase.logger.log(LogStatus.PASS,  " 'Link lost Forwarding' checkbox is displaying in 'Edit CPE device' page as expected");
		    	
		    	if (linkLostForwarding.equalsIgnoreCase("null")) {
		    		
		    		DriverTestcase.logger.log(LogStatus.PASS, "No changes made for linklost forwarding while editing cpe device under equipment");
		    	}else {
		    
		    		boolean linklost=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isSelected();
		    		
		    		if (linkLostForwarding.equalsIgnoreCase("yes")) {
				
		    			if(linklost) {
		    				DriverTestcase.logger.log(LogStatus.PASS, "linklost forwarding has been edited for cpe device under Equipment");
		    				
		    			}else {
		    				
		    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		    				DriverTestcase.logger.log(LogStatus.PASS, "linklost forwarding has been edited for cpe device under Equipment");
		    			}
		    			
				
		    		}else if(linkLostForwarding.equalsIgnoreCase("no")) {
		    			
		    			if(linklost) {
		    				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")));
		    				DriverTestcase.logger.log(LogStatus.PASS, "linklost forwarding has been edited for cpe device under Equipment");
		    				
		    			}else {
		    				
		    				DriverTestcase.logger.log(LogStatus.PASS, "linklost forwarding has been edited for cpe device under Equipment");
		    			}
		    			
		    		}
		    	}	
		    }else {
		    	DriverTestcase.logger.log(LogStatus.FAIL, " 'Link Lost Forwarding' checkbox is not available in 'Edit CPE device' page");
		    }
				}catch (NoSuchElementException e) {
				    e.printStackTrace();
				    DriverTestcase.logger.log(LogStatus.FAIL, "'linklost forwarding'  checkbox is ont available under 'Edit CPE device' page");
				}catch(Exception er) {
					er.printStackTrace();
					DriverTestcase.logger.log(LogStatus.FAIL, " NOt able to click on 'link lost forwarding' checkbox in 'Edit CPE device' page");
				}
	}
	
	
	public void device_editlinklostforwarding_10G(String application) {
		
		 boolean linklostcheckboxAvailability=false;
		 boolean linklostcheckboxEnabled=false;
		    try { 
		    	linklostcheckboxAvailability=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isDisplayed();
		    	
		    if(linklostcheckboxAvailability) {	
		    	DriverTestcase.logger.log(LogStatus.PASS,  " 'Link lost Forwarding' checkbox is displaying in 'Edit CPE device' page as expected");
		    	
		    	linklostcheckboxEnabled=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_linklostforowarding")).isEnabled();
		    	if(linklostcheckboxEnabled) {
		    		System.out.println(" 'link lostforwarding is enabled for 10G");
		    		DriverTestcase.logger.log(LogStatus.FAIL, " 'Link Lost forwarding' is enabled");
		    		
		    	}else {
		    		System.out.println("link lost checkbox is disabled as expected");
		    		DriverTestcase.logger.log(LogStatus.PASS, " 'Link lost Forwarding' checkbox is disabled as expected");
		    	}
		    	
		    }else {
		    	DriverTestcase.logger.log(LogStatus.FAIL, " 'Link Lost Forwarding' checkbox is not available in 'Edit CPE device' page");
		    }
				}catch (NoSuchElementException e) {
				    e.printStackTrace();
				    DriverTestcase.logger.log(LogStatus.FAIL, "'linklost forwarding'  checkbox is ont available under 'Edit CPE device' page");
				}catch(Exception er) {
					er.printStackTrace();
					DriverTestcase.logger.log(LogStatus.PASS, " 'link lost forwarding' checkbox is disabled 'Edit CPE device' page");
				}
	
	}
	
	
	public void device_editserialnumber(String application, String serialNumber) {
		
		 boolean serialunmberAvailability=false;
			try {
				
				serialunmberAvailability=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).isDisplayed();
				Thread.sleep(3000);
				 
		if(serialunmberAvailability) {	
			DriverTestcase.logger.log(LogStatus.PASS, " 'Serial Number' field is displaying under 'Edit CPE device' page");
			
			if(serialNumber.equalsIgnoreCase("null")) {
				
				DriverTestcase.logger.log(LogStatus.PASS, "No changes made for 'Serial Number' field while editing");
				
			}else {
				getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).clear();
				Thread.sleep(3000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")), serialNumber);
				Thread.sleep(3000);
				 
				String actualValue_Name=getwebelement(xml.getlocator("//locators/" + application + "/AddCPEdevice_serialnumber")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, actualValue_Name+ " is the edited value for 'Serial Number' field");
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Serial Number' field is not available under 'Edit CPE device' page");
		}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Serial Number field is not available ");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value in Serial Number field");
			}
	}
	
	public void createService_ServiceIdentification(String application, String ServiceIdentificationNumber) throws InterruptedException, IOException, DocumentException {
		//Service Identification
		
		boolean serviceIdentificationField = getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
		if(serviceIdentificationField) {
			
			DriverTestcase.logger.log(LogStatus.PASS, " 'Service Identification' text field is displaying as exepected");
		if(!ServiceIdentificationNumber.equalsIgnoreCase("null")) {
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, ServiceIdentificationNumber+ " is entered under 'Service Identification' field");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL,"It is a mandatory field. No values entered for service identification"); 
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " ' Service Identfication' mandatory field is not available under 'Add Service' page");
		}
	
	}
	
	
	public void createService_singleEndPointCPE(String application, String EndpointCPE) throws InterruptedException, DocumentException {
		
		boolean singleendpointCPE=false;
		try {	
			singleendpointCPE = getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isDisplayed();
			if(singleendpointCPE) {
				
				DriverTestcase.logger.log(LogStatus.PASS, " 'Single End Point CPE' checkbox is displaying as expected");
				
			if(!EndpointCPE.equalsIgnoreCase("null")) {
				if (EndpointCPE.equals("yes")) {

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
					Thread.sleep(3000);
					Log.info("End point CPE check box is selected");
					
					boolean CPEselection=getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isSelected();
					if(CPEselection) {
						DriverTestcase.logger.log(LogStatus.PASS, " 'End point CPE' checkbox is selected as expected");
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " 'End point CPE' checkbox is not selected");
					}
					
				}
				else {

					Log.info("Sing Endpoint CPE is not selected");
					DriverTestcase.logger.log(LogStatus.PASS,"Sing Endpoint CPE is not selected "); 
				}
			}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL,  " 'Single endpoint cpe' field is not available under 'Create Service' page");
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Single endpoint cpe' field is not available under 'Create Service' page");
		}catch(Exception er) {
			er.printStackTrace();
			System.out.println("not able to select single end point CPE checkbox");
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on 'single end point CPE' checkbox");
		}
	}
	
	
	public void createSerivce_email(String application, String Email) throws InterruptedException, DocumentException, IOException {
		
		boolean email=false;
		 try {
				email = getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
				if(email) {
					DriverTestcase.logger.log(LogStatus.PASS, " 'Email' text field is displaying as expected");
				
				if(!Email.equalsIgnoreCase("null")) {
				
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
					Thread.sleep(3000);
					DriverTestcase.logger.log(LogStatus.PASS, Email + " is entered under 'Email' field");

				}else {
					DriverTestcase.logger.log(LogStatus.PASS,"value not entered under 'Email' field"); 
				}
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Email' field is not available under 'Create Service' page");
				}
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 System.out.println("Email field is not available");
			 DriverTestcase.logger.log(LogStatus.PASS, " 'Email' field is not available under 'create Service' page");
		 }catch(Exception er) {
			 er.printStackTrace();
			 System.out.println("Not able to enter value in 'Email' field");
			 DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value in 'Email' field");
		 }
	}
	
	
	public void createService_phone(String application, String PhoneContact) throws InterruptedException, DocumentException, IOException {
		
		boolean phone=false;
	try {	
		phone = getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
		sa.assertTrue(phone, "phone contact field is not displayed");
		if(phone) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Phone Contact' text field is displaying as expected");
			
		if(!PhoneContact.equalsIgnoreCase("null")) {
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, PhoneContact + " is entered under 'Phone contact' field" );
		}else {
			DriverTestcase.logger.log(LogStatus.PASS,"Value not entered under 'Phone contact' field");
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Phone Contact' text field is not available under 'Create Service' page");
		}
	  }catch(NoSuchElementException e) {
		  e.printStackTrace();
		  System.out.println("Phone contact text field is not available");
		  DriverTestcase.logger.log(LogStatus.FAIL, " 'Phone Contact' text field is not available under 'Create Service' page");
	  }catch(Exception err) {
		  err.printStackTrace();
		  DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'hone Contact' field");
		  System.out.println("Not able to enter value under 'Phone Contact' field");
	  }
	}
	
	
	public void createService_remark(String application, String remark) throws InterruptedException, DocumentException, IOException {
		boolean Remark=false;
	try {	
		Remark = getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
		if(Remark) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Remark' text field is displaying as expected");

		if(!remark.equalsIgnoreCase("null")) {
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, remark + " is entered under 'Remark' field");
			
		}else {
			
			DriverTestcase.logger.log(LogStatus.PASS,"value not entered under 'Remark' field");
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Remark' text field is not available under 'Create Service' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		System.out.println("Remak text field is not availeble");
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Remark' text field is not available under 'Create Service' page");
	}catch(Exception err) {
		err.printStackTrace();
		System.out.println(" Not able t enter value in 'remark' text field");
		 System.out.println("Not able to enter value under 'Remark' field");
	}
		
	}
	
	
	public void createService_performancereporting_10G(String application, String PerformanceReporting) throws InterruptedException, DocumentException {
		
		boolean performancereoprting=false;
	try {	
		performancereoprting = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox"))
				.isDisplayed();
			if(performancereoprting) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Performance Reporting' checkbox is displaying as expected");
			
			if(!PerformanceReporting.equalsIgnoreCase("null")) {
				
				if (PerformanceReporting.equalsIgnoreCase("yes")) {
			
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("performance Reporting check box is selected");
					
					boolean prfrmselection = getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
					if(prfrmselection) {
						DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting checkbox is selected as expected");
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, "Performance Reporting checkbox is not selected");
					}
				}
				else {
			
					System.out.println("Performance Reporting is not selected");
					DriverTestcase.logger.log(LogStatus.PASS,"performance Reporting checkbox is not selected");
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Performance Reporting' checkbox is not selected");
			}
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Performance Reporting' checkbox is not displaying under 'create service' page");
			}
		   }catch(NoSuchElementException e) {
			   e.printStackTrace();
			   System.out.println(" 'Perormance reporting' checkbox is not selected");
			   DriverTestcase.logger.log(LogStatus.FAIL, " 'Performance Reporting' checkbox is not displaying under 'create service' page");
		   }catch(Exception err) {
			   err.printStackTrace();
		   }
		}
	
	
	public void createService_deliveryChannel(String application, String deliveryChannel) throws InterruptedException, DocumentException {
		
		boolean deliveryChanel=false;
	try {	
		deliveryChanel = getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
		sa.assertTrue(deliveryChanel, "delivery channel dropdown is not displayed");
		if(deliveryChanel) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Delivery Channel' dropdown is displaying as expected");
		if (!deliveryChannel.equalsIgnoreCase("null")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, deliveryChannel + " is selected under 'Delivery channel' dropdown");

		}else {
			DriverTestcase.logger.log(LogStatus.PASS,"No value selected under 'Delivery channel' dropdown"); 
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Delivery channel' dropdown is not dispalying under 'create Serice' page");
		}
	  }catch(NoSuchElementException e) {
		  e.printStackTrace();
		  System.out.println(" 'Delivery channel' dropdown is not dispalying");
		  DriverTestcase.logger.log(LogStatus.FAIL, " 'Delivery channel' dropdown is not dispalying under 'create Serice' page");
	  }catch(Exception err) {
		  err.printStackTrace();
		  DriverTestcase.logger.log(LogStatus.FAIL, " Not able to selected value under 'Delivery channel' dropodwn");
	  }
	}

	
	public void createService_managementOptions(String application, String ManagementConnection) throws InterruptedException, DocumentException {
		
		boolean Managementorder=false;
	try {	
		Managementorder = getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")).isDisplayed();
		sa.assertTrue(Managementorder, "Management Connection field is not displayed");
		if(Managementorder) {
			DriverTestcase.logger.log(LogStatus.PASS, " ' Management Connection' dropdown is displaying as expected");
		if (!ManagementConnection.equalsIgnoreCase("null")) {
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + ManagementConnection + "')][1]"));
			Thread.sleep(3000);
			
			DriverTestcase.logger.log(LogStatus.PASS, ManagementConnection + " is selected under 'management Connection' field");
		
		}else {
			DriverTestcase.logger.log(LogStatus.PASS,"Values not entered under 'Management Connection' field");
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " ' Management Connection' dropdown is not displaying under 'Create Service' page");
		}
	  }catch(NoSuchElementException e) {
		  e.printStackTrace();
		  System.out.println(" 'Management Connection' dropdown is not displaying");
		  DriverTestcase.logger.log(LogStatus.FAIL, " ' Management Connection' dropdown is not displaying under 'Create Service' page");
	  }catch(Exception err) {
		  err.printStackTrace();
		  DriverTestcase.logger.log(LogStatus.FAIL, " Not able to select value under 'management Connection' dropdown");
	  }
	}
	
	
	public void createService_proactivemonitoring(String application,String ProactiveMonitoring, String notificationManagement) throws InterruptedException, DocumentException {
		
		boolean proactiveMonitor=false;
		proactiveMonitor = getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring"))
				.isDisplayed();
		sa.assertTrue(proactiveMonitor, "pro active monitoring checkbox is not displayed");
		if(proactiveMonitor) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Pro active Monitoring' text field is displaying as expected");
		
		if (!ProactiveMonitoring.equalsIgnoreCase("null")) {
			if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
				Log.info("Pro active monitoring check box is selected");
				
				boolean proactiveSelection=getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
				if(proactiveSelection) {
					DriverTestcase.logger.log(LogStatus.PASS, " 'pro active monitoring' checkbox is selected as expected");
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'pro active monitoring' checkbox is not selected");
				}
				
			  //Notification management	
			try {	
				DriverTestcase.logger.log(LogStatus.INFO, "Notification Management dropdown displays when pro active monitoring is selected");
				
				if (!notificationManagement.equalsIgnoreCase("null")) {
				Log.info("Notificationan Management dropdown displays when pro active monitoring is selected");
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
				Thread.sleep(3000);
				Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
				Thread.sleep(3000);
				DriverTestcase.logger.log(LogStatus.PASS, notificationManagement + " is selected under 'Notification management' dropdown");
				
				}else {
					DriverTestcase.logger.log(LogStatus.PASS,"No values selected under Notification management dropdown"); 
					
				}
				
			}catch(NoSuchElementException e) {
				System.out.println(" 'Notification management' dropodwn is not displaying under 'create Service' page");
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Notification management' dropodwn is not displaying under 'create Service' page");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL,"Not able to select values under Notification management dropdown"); 
			}
			} else {
				Log.info("Pro active monitoring is not selected");
				System.out.println("pro active monitoring is not selected");
				DriverTestcase.logger.log(LogStatus.PASS,"performance monitor checkbox is not selected "); 
			}

		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Pro active monitoring' checkbox is not displaying");
		}
	}
	
	
	public void createService_intermediateTechnology(String application, String intermediateTechnology) throws InterruptedException, DocumentException, IOException {
		boolean intrTech=false;
	try {	
		intrTech=getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).isDisplayed();
		if(intrTech) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Intermediate Technology' text field is displying as expected under 'create service' page");
		if (!intermediateTechnology.equalsIgnoreCase("null")) {
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
				intermediateTechnology);
		
		String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).getAttribute("value");
		Thread.sleep(3000);
		DriverTestcase.logger.log(LogStatus.PASS, actualvalue + " is entered under 'Intermediate technology' field");
		}else {
			DriverTestcase.logger.log(LogStatus.PASS,"No value entered under 'Intermediate  Technology' field");
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Intermediate Technology' text field is not displying under 'create service' page");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		System.out.println("'Intermediate Technology' text field is not displying");
		DriverTestcase.logger.log(LogStatus.FAIL," 'Intermediate Technology' text field is not displying under 'create service' page");
	}catch(Exception err) {
		err.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL," Not able to enter value under 'Intermediate Technology' text field in 'create service' page");
		System.out.println("Not able to enter value under 'Intermediate Technology' text field in 'create service' page");
	}
	}
	
	
	public void createService_circuitreference(String application, String CircuitReference) throws InterruptedException, DocumentException, IOException {
		boolean crctRef=getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
		if(crctRef) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Circuit Reference' text field is displying as expected");
		if (!CircuitReference.equalsIgnoreCase("null")) {
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")), CircuitReference);
		Thread.sleep(3000);
		
		String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).getAttribute("value");
		
		DriverTestcase.logger.log(LogStatus.PASS, actualvalue + " is entered under 'Circuit Reference' field");
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL,"Circuit reference field is mandatory, but no inputs has been provided");
		}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Circuit Reference' text field is not displying");
		}
	}
	
	
	public void createSerivce_circuitType(String application, String CircuitType) throws InterruptedException {
       
		if (!CircuitType.equalsIgnoreCase("null")) {
		Clickon(getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input"));
		Thread.sleep(3000);
		DriverTestcase.logger.log(LogStatus.PASS, CircuitType + " is selected under 'Circuit Type'");
		}else {
			DriverTestcase.logger.log(LogStatus.PASS," No changes made. 'Default' is selected under'Circuit type' as no input provided");
			
		}
	}
	
	
	public void createService_standardCIR(String application, String standrdCir) throws InterruptedException, DocumentException, IOException {
		boolean standrdCiR=false;
		
	try {	
		standrdCiR=getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).isDisplayed();
		if(standrdCiR) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Standard CIR' text field displaying, when 'Actelis Based' checkbox is selected");
			if(standrdCir.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No Values entered in 'Standard CIR' text field");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")), standrdCir);
				
				String valuesForSCIR=getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, valuesForSCIR +" is enerted under 'Standard CIR' text field" );
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Standard CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		System.out.println("'Standard CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Standard CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
	}catch(Exception err) {
		err.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'Standard CIR' field in 'create Service' page");
	}
	}
	
	
	public void createService_standardEIR(String application, String standrdEir) throws InterruptedException, DocumentException, IOException {
		boolean standrdEiR=false;
	try {	
		standrdEiR=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).isDisplayed();
		if(standrdEiR) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Standard EIR' text field displaying, when 'Actelis Based' checkbox is selected");
			if(standrdEir.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No Values entered in 'Standard EIR' text field");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")), standrdEir);
				
				String valuesForSEIR=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, valuesForSEIR +" is enerted under 'Standard EIR' text field" );
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Standard EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}
	 }catch(NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("'Standard EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Standard EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not albe to enter value under 'Standard EIR' field in 'create Service' page");
			System.out.println(" Not able to enter value under 'Standard EIR' field in 'create Service' page");
		}
	}
	
	
	public void createService_premiumCIR(String application, String prmiumCir) throws InterruptedException, DocumentException, IOException {
		boolean premiumCiR=false;
	try {	
		premiumCiR=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).isDisplayed();
		if(premiumCiR) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Premium CIR' text field displaying, when 'Actelis Based' checkbox is selected");
			if(prmiumCir.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No Values entered in 'Premium CIR' text field");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")), prmiumCir);
				
				String valuesForPCIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, valuesForPCIR +" is enerted under 'Premium CIR' text field" );
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Premium CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}	
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		System.out.println("'Premium CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Premium CIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
	}catch(Exception err) {
		err.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'Premium CIR' field in 'create Service' page");
	}	
	}
	
	
	public void createService_premiumEIR(String application, String prmiumEir) throws InterruptedException, DocumentException, IOException {
		boolean premiumEiR=false;
	try {	
		premiumEiR=getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).isDisplayed();
		if(premiumEiR) {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Standard EIR' text field displaying, when 'Actelis Based' checkbox is selected");
			if(prmiumEir.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, "No Values entered in 'Premium EIR' text field");
			}else {
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")), prmiumEir);
				
				String valuesForPEIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, valuesForPEIR +" is enerted under 'Premium EIR' text field" );
			}
			
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Premium EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}
	  }catch(NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("'Premium EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Premium EIR' Text Field is not displaying, when 'Actelis Based' checkbox is selected");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to enter value under 'Premium EIR' field in 'create Service' page");
		}		
	}
	
	
	public void editService_serviceIdentification(String application, String ServiceIdentificationNumber) throws InterruptedException, DocumentException, IOException {
		
		 boolean serviceAvailability=false;
		 serviceAvailability=getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).isDisplayed();
		 
	if(serviceAvailability) {
		
		DriverTestcase.logger.log(LogStatus.PASS, " 'Service Identification' field is displaying in 'Edit Service'page as expected");
		
		if(!ServiceIdentificationNumber.equalsIgnoreCase("null")) {
			
			getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).clear();
			Thread.sleep(2000);
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")),
					ServiceIdentificationNumber);
			
			String Actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/ServiceIdentification")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Service Identification' field is "+ Actualvalue);
			
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, " Service Identification field value is not edited");
			System.out.println(" Service Identification field value is not edited");
		}
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Service Identification' field is not availale in 'Edit Service'page");
	}
	}
	
	
	public void editService_singleEndPointCPE(String application, String EndpointCPE, String Topology) throws InterruptedException, DocumentException {
		boolean CPEAvailability=false;
		
	if(Topology.equalsIgnoreCase("Point-to-Point"))	{
		
		try {
		CPEAvailability=getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isDisplayed();
		if(CPEAvailability) {
			
			DriverTestcase.logger.log(LogStatus.PASS, "Single EndPoint CPE checkbox is displaying under 'Edit Service' page");
			
			if(!EndpointCPE.equalsIgnoreCase("null")) {
				
				boolean endpoint=getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")).isSelected();
				Thread.sleep(2000);
				
				if (EndpointCPE.equalsIgnoreCase("yes")) {

					if(endpoint) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Endpoint CPE is not edited. It is already Selected while creating.");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is edited and it got selected");
						DriverTestcase.logger.log(LogStatus.PASS,"Endpoint CE is edited and it got selected");
					}
				}
				else if (EndpointCPE.equalsIgnoreCase("no")) {
					if(endpoint) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/EndpointCPE")));
						Log.info("End point CPE check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"Endpoint CE is edited and it is unselected as Expected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "Endpoint CPE is not edited. It was not selected during service creation and it remains unselected as expected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for EndPoint CPE chekbox as expected");
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Single EndPoint CPE checkbox is not available under 'Edit Service' page");
		}
	  }catch(NoSuchElementException e) {
		  e.printStackTrace();
		  DriverTestcase.logger.log(LogStatus.FAIL, "Single EndPoint CPE checkbox is not available under 'Edit Service' page");
		  System.out.println("Single EndPoint CPE checkbox is not available under 'Edit Service' page");
	  }catch(Exception err) {
		  err.printStackTrace();
		  DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on 'Single endpoint CPE' checkbox");
		  System.out.println("Not able to click on 'Single endpoint CPE' checkbox");
	  }
	
	}	else{
	
		DriverTestcase.logger.log(LogStatus.PASS, " 'Single Endpoint CPE' checkbox is not displaying as expected,"
				+ " when 'VPN Topology' selected as 'hub&spoke' or 'E-PN(Any to Any)");
	
	}
	}	
	
	
	public void editService_Email(String application, String Email) throws InterruptedException, DocumentException, IOException {
		
		 boolean emailAvailability=false;
		 try {
			 emailAvailability= getwebelement(xml.getlocator("//locators/" + application + "/Email")).isDisplayed();
		 
		 if(emailAvailability) {
			 
			 DriverTestcase.logger.log(LogStatus.PASS, " 'Email' text field is displaying in 'Edit Service' page");
			 
			if(!Email.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Email")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Email")), Email);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Email' field is "+ Email);
				
				String Actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Email")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, " 'Email' field is edited as: "+Actualvalue );
				System.out.println("'Email' field is edited as: "+Actualvalue);

			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "'Email' field is not edited");
			}
		 }else {
			 DriverTestcase.logger.log(LogStatus.FAIL, " 'Email' text field is not available in 'Edit Service' page");
		 }
		 }catch(NoSuchElementException e) {
			 e.printStackTrace();
			 DriverTestcase.logger.log(LogStatus.FAIL, " 'Email' text field is not available in 'Edit Service' page");
			 System.out.println(" 'Email' text field is not available in 'Edit Service' page");
		 } catch(Exception err) {
			 err.printStackTrace();
			 DriverTestcase.logger.log(LogStatus.FAIL, " not able to edit 'Email' field");
		 }
		 
	}
	
	
	public void editService_phoneContact(String application, String PhoneContact) throws InterruptedException, DocumentException, IOException {
		
		 boolean phoneAvailability=false;
	     try {
	    	 phoneAvailability=getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).isDisplayed();
	    
	   if(phoneAvailability) { 
		   
		   DriverTestcase.logger.log(LogStatus.PASS, " Phone Contact field is displaying in 'Edit Service' page as expected");
		   
		if(!PhoneContact.equalsIgnoreCase("null")) {
			
			getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).clear();
			Thread.sleep(2000);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")), PhoneContact);
			Thread.sleep(3000);
			String actualValue=getwebelement(xml.getlocator("//locators/" + application + "/PhoneContact")).getAttribute("value");
			DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Phone contact' field is " + actualValue);
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Phone contact' field is not edited");
		}
	   }else {
		   DriverTestcase.logger.log(LogStatus.FAIL, " Phone Contact field is not available in 'Edit Service' page");
	   }
	     }catch(NoSuchElementException e) {
	    	 e.printStackTrace();
	    	 DriverTestcase.logger.log(LogStatus.FAIL, " Phone Contact field is not available in 'Edit Service' page");
	    	 System.out.println("Phone Contact field is not available in 'Edit Service' page");
	     }catch(Exception err) {
	    	 err.printStackTrace();
	    	 DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'phone Contact' field");
	    	 System.out.println(" Not able to edit 'phone Contact' field");
	     }
	}
	
	
	public void editService_remark(String application, String remark) throws InterruptedException, DocumentException, IOException {
		
		 boolean remarkAvailability=false;
		   try {
			   remarkAvailability=getwebelement(xml.getlocator("//locators/" + application + "/Remark")).isDisplayed();
		   
		  if(remarkAvailability) {
			  
			  DriverTestcase.logger.log(LogStatus.PASS, " 'Remark' field is displaying in 'Edit Service' page as expected");
			  
			if(!remark.equalsIgnoreCase("null")) {
				
				getwebelement(xml.getlocator("//locators/" + application + "/Remark")).clear();
				Thread.sleep(2000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Remark")), remark);
				
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Remark")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Remark' field is "+ actualvalue);
				
		}else {
			DriverTestcase.logger.log(LogStatus.PASS, " 'Remark' field is not edited");
		}
		  }else {
			  DriverTestcase.logger.log(LogStatus.FAIL, " 'Remark' field is not available in 'Edit Service' page");
		  }
		   }catch(NoSuchElementException e) {
		    	 e.printStackTrace();
		    	 DriverTestcase.logger.log(LogStatus.FAIL, " 'Remark' field is not available in 'Edit Service' page");
		    	 System.out.println(" 'remark' field is not available in 'Edit Service' page");
		     }catch(Exception err) {
		    	 err.printStackTrace();
		    	 DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'Remark' field");
		    	 System.out.println(" Not able to edit 'Remark' field");
		     }
	}
	
	
	public void editService_performancereporting_10G(String application, String performanceReporting) throws InterruptedException, DocumentException {
		
		if(!performanceReporting.equalsIgnoreCase("null")) {
			
			boolean perfmmonitr=getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")).isSelected();
			Thread.sleep(2000);
			
			if (performanceReporting.equalsIgnoreCase("yes")) {

				if(perfmmonitr) {
					
					DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting checkbox is not edited and it is already Selected while creating");
					
				}else {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
					Log.info("Performance monitor check box is selected");
					DriverTestcase.logger.log(LogStatus.PASS,"Performance Reporting is edited and it is selected");
				}
			}
			else if (performanceReporting.equalsIgnoreCase("no")) {
				if(perfmmonitr) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performancemonitoring")));
					Log.info("Performance Reporting check box is unselected");
					DriverTestcase.logger.log(LogStatus.PASS,"Performance Reporting is edited and gets unselected");
					
				}else {
					DriverTestcase.logger.log(LogStatus.PASS, "Performance Reporting is not edited and it remains unselected");
				}
			}
		}else {
			DriverTestcase.logger.log(LogStatus.PASS," 'Performance reporting' checkbox is not edited");
		}
	}

		public void editService_proactiveMonitoring(String application, String ProactiveMonitoring, String notificationManagement) throws InterruptedException, DocumentException {
			if(!ProactiveMonitoring.equalsIgnoreCase("null")) {
				
				boolean proactivemonitor=getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")).isSelected();
				Thread.sleep(2000);
				
				if (ProactiveMonitoring.equalsIgnoreCase("yes")) {

					if(proactivemonitor) {
						
						DriverTestcase.logger.log(LogStatus.PASS, "Proactive monitoring is not edited and it is already Selected while creating");
						

						if(notificationManagement.equalsIgnoreCase("null")) {
							
							DriverTestcase.logger.log(LogStatus.PASS,"No changes made to notification management");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
							Thread.sleep(4000);
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
							Thread.sleep(3000);
							Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
							Thread.sleep(3000);
							DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
						}
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
						Log.info("proactive monitoring check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS,"proactive monitoring is edited and gets selected");
						
						
						if(notificationManagement.equalsIgnoreCase("null")) {
							
							DriverTestcase.logger.log(LogStatus.PASS,"No changes made to notification management");
							
						}else {
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notigymanagement_xbutton")));
							Thread.sleep(2000);
							
							Clickon(getwebelement(xml.getlocator("//locators/" + application + "/notificationmanagement")));
							Clickon(getwebelement("//div[contains(text(),'" + notificationManagement + "')]"));
							DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Notification Management' field is "+notificationManagement);
						}
					}
				}

				else if (ProactiveMonitoring.equalsIgnoreCase("no")) {
					
					if(proactivemonitor) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/proactiveMonitoring")));
						Log.info("Proactive monitoring check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,"proactive monitoring is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, "proactive monitoring was not selected during service creation and it remains unselected");
					}
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for 'Proactive monitoring' chekbox");
			}
		}
		
		
		public void editService_deliveryChannel(String application, String deliveryChannel) throws InterruptedException, DocumentException {
			
			 boolean deliveryChannelAvailability=false;
			   try {
				   deliveryChannelAvailability= getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")).isDisplayed();
			   
			  if(deliveryChannelAvailability) { 
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " 'Delivery channel' dropdown is displaying in 'Edit Service' page as expected");
				  
				if (!deliveryChannel.equalsIgnoreCase("null")) {
					
					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_xbutton")));
					Thread.sleep(3000);

					Clickon(getwebelement(xml.getlocator("//locators/" + application + "/deliverychannel_withclasskey")));
					Thread.sleep(3000);
					Clickon(getwebelement("//div[contains(text(),'" + deliveryChannel + "')]"));
					
					DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Delivery Channel' dropdown is "+deliveryChannel);
					System.out.println("Delivery channel dropdown value is edited as expected");

				}else {
					DriverTestcase.logger.log(LogStatus.PASS, "Delivery channel dropdown value is not edited");
				}
			  }else {
				  DriverTestcase.logger.log(LogStatus.FAIL, " 'Delivery channel' dropdown is not available in 'Edit Service' page");
			  }
			   }catch(NoSuchElementException e) {
				   e.printStackTrace();
				   DriverTestcase.logger.log(LogStatus.FAIL, " 'Delivery channel' dropdown is not available in 'Edit Service' page");
				   System.out.println(" 'Delivery channel' dropdown is not available in 'Edit Service' page");
			   }catch(Exception err){
				   err.printStackTrace();
				   DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'delvery Channel' dropdown");
			   }
		}
		
		
		public void editService_managementConnection(String application, String ManagementConnection) throws InterruptedException, DocumentException {
			
			boolean managemenOrderAvailabiliy=false;
			try {
				managemenOrderAvailabiliy=getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")).isDisplayed();
			
		
	if(managemenOrderAvailabiliy) {	
		DriverTestcase.logger.log(LogStatus.PASS, " 'Management Connection' dropdown is displaying in 'Edit Service' page as expected");
		
		if (!ManagementConnection.equalsIgnoreCase("null")) {

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementConnection_xButton")));
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/managementConnection")));
			Thread.sleep(3000);
			Clickon(getwebelement("//div[contains(text(),'" + ManagementConnection + "')]"));
			Thread.sleep(3000);
			
			DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'management Connection' field is " + ManagementConnection);
			System.out.println("Edited value for 'Management Connection' field is " + ManagementConnection);
			
		}else {
			
			DriverTestcase.logger.log(LogStatus.PASS, "No changes has been made to 'Management Connection' field");
			System.out.println("No changes has been made to 'Management Connection' field");
		}
	}else {
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Management Connection' dropdown is not available in 'Edit Service' page");
	}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Management OrdConnectioner' dropdown is not available in 'Edit Service' page");
				System.out.println(" 'Management Connection' dropdown is not available in 'Edit Service' page");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'Management Connection' dropodwn");
				System.out.println(" Not able to edit 'Management Connection' dropodwn");
			}
		}
		
		
	public void editService_circuitreference(String application, String CircuitReference) throws InterruptedException, DocumentException, IOException {
			
			boolean circuitRefAvailability=false;
			
			circuitRefAvailability=getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).isDisplayed();
		if(circuitRefAvailability) {	
			if(CircuitReference.equalsIgnoreCase("null")) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Circuit reference' field is not edited");
			}else {
				
				getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).clear();
				Thread.sleep(3000);

				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")), CircuitReference);
				Thread.sleep(3000);
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/circuitReference")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS,"Edited value for 'Circuit reference' field is "+actualvalue);
			}
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Circuit Reference' field is not displaying in 'Edit CPE device' page");
		}
	}	
	
	
	public void editService_IntermediateTechnology(String application, String intermediateTechnology) throws InterruptedException, DocumentException, IOException {
		
		boolean intTechAvilability=false;
	try {
		intTechAvilability=getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).isDisplayed();
	if(intTechAvilability) {
		DriverTestcase.logger.log(LogStatus.PASS, "Intermediate Technologies' field is displaying under 'Edit Service' page as expected");
		if(intermediateTechnology.equalsIgnoreCase("null")) {
			
			DriverTestcase.logger.log(LogStatus.PASS, "No changes has made for 'Intermediate Technology' field");
			
		}else {
			
			getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")).clear();
			Thread.sleep(3000);
			
			SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/IntermediateTechnology")),
					intermediateTechnology);
			Thread.sleep(3000);
			DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Intermediate Technology' field is "+ intermediateTechnology);
			
		}
	  }else {
		  DriverTestcase.logger.log(LogStatus.FAIL, " 'Intermediate technologies' field is not displaying under 'edit Sevice' page");
	  }
	}catch(NoSuchElementException e) {
		e.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " 'Intermediate technologies' field is not displaying under 'edit Sevice' page");
		System.out.println(" 'Intermediate technologies' field is not displaying under 'edit Sevice' page");
	}catch(Exception err) {
		err.printStackTrace();
		DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'Intermediate technologies' field");
	}
	}
	
	
		public void editService_circuitType(String application, String CircuitType) throws InterruptedException {
		
			try {
			if(CircuitType.equalsIgnoreCase("null")) {
				
				DriverTestcase.logger.log(LogStatus.PASS, " 'Circuit type' field is not edited");
				
			}else {
				
			//verify whether it is selected	
				boolean circuitType=getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input").isSelected();
				if(circuitType) {
					
					DriverTestcase.logger.log(LogStatus.PASS, " 'Circuit Type' value selected as : "+ CircuitType +" as expected");
					
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'Circuit Type' value is not selected as : "+ CircuitType );
				}
				
			//verify whether it is editable
//				boolean circuitTypeEditable=getwebelement("//div[span[contains(text(),'" + CircuitType + "')]]//input").isEnabled();
//				if(circuitType) {
//					
//					DriverTestcase.logger.log(LogStatus.FAIL, " 'Circuit Type' is editable");
//					
//				}else {
//					DriverTestcase.logger.log(LogStatus.PASS, " 'Circuit Type' value is not editable as expected" );
//				}
			}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Circuit type' "+ CircuitType +" is not dipslaying under 'Edit Service page");
				System.out.println(" 'Circuit type' "+ CircuitType +" is not dipslaying under 'Edit Service page");
			}catch(Exception er) {
				er.printStackTrace();
				DriverTestcase.logger.log(LogStatus.PASS, " 'Circuit type' "+ CircuitType +" is disabled under 'Edit Service page");
				System.out.println(" 'Circuit type' "+ CircuitType +" is disabled under 'Edit Service page");
			}
		}
		
		
	  public void editService_premiumEIR(String application, String premiumEIR) throws InterruptedException, DocumentException, IOException {
		  
		  boolean premumEIR=false;
		try {  
		  premumEIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).isDisplayed();
			if(premumEIR) {
				DriverTestcase.logger.log(LogStatus.PASS, " Premise EIR' field is displaying in 'Edit Service' page as expected");
			if(!premiumEIR.equalsIgnoreCase("null")) {
					
				getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")).clear();
				Thread.sleep(2000);
					
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumEIRtextfield")), premiumEIR);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Premium EIR' field is "+ premiumEIR);

			}else {
				DriverTestcase.logger.log(LogStatus.PASS, "  'Premium EIR' field is not edited");
			}	
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " Premise EIR' field is not displaying in 'Edit Service' page ");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Premise EIR' field is not displaying in 'Edit Service' page ");
			System.out.println(" Premise EIR' field is not displaying in 'Edit Service' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'Premise EIR' field");
			System.out.println("Not able to edit 'Premise EIR' field");
			
		}
	  }
	  
	  
	  public void editService_premiumCIR(String application, String premiumCIR) throws InterruptedException, DocumentException, IOException {
		  
		  boolean premumCIR=false;
		try {  
		  premumCIR=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).isDisplayed();
			if(premumCIR) {
				DriverTestcase.logger.log(LogStatus.PASS, " Premise CIR' field is displaying in 'Edit Service' page as expected");
			if(!premiumCIR.equalsIgnoreCase("null")) {
					
				getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).clear();
				Thread.sleep(2000);
					
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")), premiumCIR);
				
				String actualValue=getwebelement(xml.getlocator("//locators/" + application + "/premiumCIRtextfield")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Premium CIR' field is "+ actualValue);

			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Premium CIR' field is not edited");
			}	
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " Premise CIR' field is not displaying in 'Edit Service' page ");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Premise CIR' field is not displaying in 'Edit Service' page ");
			System.out.println(" Premise CIR' field is not displaying in 'Edit Service' page ");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'Premise CIR' field");
			System.out.println("Not able to edit 'Premise CIR' field");
			
		}
	  }
	  
	  
	  public void editService_standardEIR(String application, String standardEIR) throws InterruptedException, DocumentException, IOException {
		  
		  boolean stndrdEIR=false;
		try {  
		  stndrdEIR=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).isDisplayed();
			if(stndrdEIR) {
				DriverTestcase.logger.log(LogStatus.PASS, " Standard EIR' field is displaying in 'Edit Service' page as expected");
			if(!standardEIR.equalsIgnoreCase("null")) {
					
				getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).clear();
				Thread.sleep(3000);
					
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")), standardEIR);
				
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/standardEIRtextfield")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Standard EIR' field is "+ actualvalue);

			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Standard EIR' field is not edited");
			}	
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " Standard EIR' field is not displaying in 'Edit Service' page ");
		}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Standard EIR' field is not displaying in 'Edit Service' page ");
			System.out.println(" 'Standard EIR' field is not displaying in 'Edit Service' page");
		}catch(Exception err) {
			err.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'Standard EIR' field");
			System.out.println(" Not able to edit 'Standard EIR' field");
			
		}
	  }
		
	  
	  public void editService_standardCIR(String application, String standardCIR) throws InterruptedException, DocumentException, IOException {
		  
		try {  
		  boolean stndrdCIR=getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).isDisplayed();
			if(stndrdCIR) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Standard CIR' field is displaying in 'Edit Service' page ");
			if(!standardCIR.equalsIgnoreCase("null")) {
					
				getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")).clear();
				Thread.sleep(3000);
					
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/standardCIRtextfield")), standardCIR);
				DriverTestcase.logger.log(LogStatus.PASS, "Edited value for 'Standard CIR' field is "+ standardCIR);

			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Standard CIR' field is not edited");
			}	
		}else {
			DriverTestcase.logger.log(LogStatus.FAIL, " 'Standard CIR' field is not displaying in 'Edit Service' page ");
		}
		}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'Standard CIR' field is not displaying in 'Edit Service' page ");
				System.out.println(" 'Standard CIR' field is not displaying in 'Edit Service' page");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit 'Standard CIR' field");
				System.out.println(" Not able to edit 'Standard CIR' field");
				
			}
	  }
	  
	  
	  public void verifyEnteredvalueForEmail_serviceCreationpage(String label, String expectedValue) throws InterruptedException {

			try {
				
				WebElement ele = getwebelement("(//div[div[label[contains(text(),'"+ label +"')]]]/div[2])[2]");
				String valueinfo = ele.getText().toString();
				if ((valueinfo.equals("")) || (valueinfo.equalsIgnoreCase(null))) {

					System.out.println("value not displayed for " + label);
					valueinfo= "Null";
					
					sa.assertEquals(valueinfo, expectedValue, label + " value is not displaying as expected");
					
//					DriverTestcase.logger.log(LogStatus.PASS, "Step : No value displaying for : " + label);
					
					
				} else {
					
					System.out.println("value displayed for " + label + " is : " + valueinfo);
					
					Log.info("Step : value displayed for" + label + "is : " + valueinfo);
					
					sa.assertEquals(valueinfo, expectedValue, label + " value is not displaying as expected");

					if(valueinfo.equalsIgnoreCase(expectedValue)) {
						System.out.println("The valus is dipslaying as expected");
						DriverTestcase.logger.log(LogStatus.PASS, " Value is displaying as expected in 'view' page for "+label);
						DriverTestcase.logger.log(LogStatus.PASS, "Step : value displayed for" + label + "is : " + valueinfo);
					}else {
						System.out.println("the values are not dipslaying as expected for label: "+label);
						DriverTestcase.logger.log(LogStatus.FAIL, " Value is not displaying as expected in 'view' page for "+ label);
						DriverTestcase.logger.log(LogStatus.FAIL, "Step : value displayed for " + label + "is : " + valueinfo);
						
					}
					
				} 
			} catch(AssertionError err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, label + " value is not displaying as expected ");
			} catch (NoSuchElementException e) {
				System.out.println("value not displayed for " + label);
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : " + label +" is not displaying");
				
			}
		}

	  public void edittextFields_commonMethod(String application, String labelname, String xpathname, String expectedValueToEdit) throws InterruptedException, DocumentException, IOException {
			boolean availability=false;
		try {	
			availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).isDisplayed();
			if(availability) {
				DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is displaying as expected");
				System.out.println(labelname + " text field is displaying as expected");
				
				if(expectedValueToEdit.equalsIgnoreCase("null")) {
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is not edited as expected");
					System.out.println(labelname + " text field is not edited as expected");
				}else {
					
					getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).clear();
					Thread.sleep(3000);
					
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToEdit);
					Thread.sleep(3000);
					
					String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is edited as: "+ actualvalue);
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
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to edit"+ labelname + " text field");
			System.out.println(" Not able to edit"+ labelname + " text field");
		}
	}
	  
	  
	  public void addtextFields_commonMethod(String application, String labelname, String xpathname, String expectedValueToAdd) throws InterruptedException, DocumentException, IOException {
			boolean fieldAvailability=false;
			
		try {	
			fieldAvailability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).isDisplayed();
			if(fieldAvailability) {
				DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field is displaying as expected");
				System.out.println(labelname + " text field is displaying as expected");
				
				if(expectedValueToAdd.equalsIgnoreCase("null")) {
					DriverTestcase.logger.log(LogStatus.PASS, "No values added to text field "+labelname);
					System.out.println("No values added to text field "+labelname);
				}else {
					
					SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")), expectedValueToAdd);
					Thread.sleep(3000);
					
					String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpathname +"")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " text field value added as: "+ actualvalue);
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
			DriverTestcase.logger.log(LogStatus.FAIL, " Not able to add value to "+ labelname + " text field");
			System.out.println(" Not able to add value to "+ labelname + " text field");
		}
	}
	  
	  public void addDropdownValues_commonMethod(String application, String labelname, String xpath, String expectedValueToAdd) throws InterruptedException, DocumentException {
		  boolean availability=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  if(availability) {
			  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown is displaying as expected");
			  System.out.println(labelname + " dropdown is displaying as expected");
			  
			  if(expectedValueToAdd.equalsIgnoreCase("null")) {
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
				  System.out.println(" No values selected under "+ labelname + " dropdown");
			  }else {
				  
				  Clickon(getwebelement("//div[label[text()='"+ labelname +"']]//div[text()='�']"));
				  Thread.sleep(3000);
				  
				  //verify list of values inside dropdown
				  List<WebElement> listofvalues = driver
							.findElements(By.xpath("//div[@role='list']//span[@role='option']"));
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " List of values inside "+ labelname + "dropdown is:  ");
				  System.out.println( " List of values inside "+ labelname + "dropdown is:  ");
				  
					for (WebElement valuetypes : listofvalues) {

								Log.info("service sub types : " + valuetypes.getText());
								DriverTestcase.logger.log(LogStatus.PASS," " + valuetypes.getText());
								System.out.println(" " + valuetypes.getText());

					}
					
					Thread.sleep(3000);
				  Clickon(getwebelement("//div[text()='"+ expectedValueToAdd +"']"));
				  Thread.sleep(3000);
				  
				  String actualValue=getwebelement("//div[label[text()='"+ labelname +"']]//span").getText();
				  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
				  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
				  
			  }
		  }else {
			  DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
			  System.out.println(labelname + " is not displaying");
		  }
		}catch(NoSuchElementException e) {
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " is not displaying");
			  System.out.println(labelname + " is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " NO value selected under "+ labelname + " dropdown");
			System.out.println(" NO value selected under "+ labelname + " dropdown");
		}
		
	  }


	  public void addMappingMode(String application, String mappingMode) {
		  
		  String labelname="Mapping Mode";
		  boolean availability=false;
		try {  
		  availability=getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown")).isDisplayed();
		  if(availability) {
			  DriverTestcase.logger.log(LogStatus.PASS, "Mapping mode dropdown is displaying as expected");
			  System.out.println(labelname + " is displaying as expected");
			  
			  if(mappingMode.equalsIgnoreCase("null")) {
				  
				  DriverTestcase.logger.log(LogStatus.PASS, " No values selected under "+ labelname + " dropdown");
				  System.out.println(" No values selected under "+ labelname + " dropdown");
			  }else {
				  
				  Clickon(getwebelement(xml.getlocator("//locators/" + application + "/Addsiteorder_mappingModedropdown_xbutton")));
				  Thread.sleep(3000);
				  
				  Clickon(getwebelement("//div[text()='"+ mappingMode +"']"));
				  Thread.sleep(3000);
				  
				  String actualValue=getwebelement("//div[label[text()='"+ labelname +"']]//span").getText();
				  DriverTestcase.logger.log(LogStatus.PASS, labelname + " dropdown value selected as: "+ actualValue );
				  System.out.println( labelname + " dropdown value selected as: "+ actualValue);
				  
			  }
		  }else {
			  DriverTestcase.logger.log(LogStatus.FAIL, labelname + " dropdown is not displaying");
			  System.out.println(labelname + " is not displaying");
		  }
		}catch(NoSuchElementException e) {
			DriverTestcase.logger.log(LogStatus.FAIL, labelname + " dropdown is not displaying");
			  System.out.println(labelname + " is not displaying");
		}catch(Exception ee) {
			ee.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, " NO value selected under "+ labelname + " dropdown");
			System.out.println(" NO value selected under "+ labelname + " dropdown");
		}
		  
	  }

	  
	  public boolean findPanelHeader(String application, String devicePanelName) throws InterruptedException {

		  scrolltoend();
		  Thread.sleep(3000);
		  
		 boolean panelheader=false;
		try { 
		 panelheader=getwebelement("//div[div[text()='"+  devicePanelName +"']]").isDisplayed();
		 
		 if(panelheader) {
			 DriverTestcase.logger.log(LogStatus.PASS, " 'Equipment' panel is displaying under 'view site order' page");
			 System.out.println(" 'Equipment' panel is displaying under 'view site order' page");
			 panelheader=true;
			 
		 }else {
			 DriverTestcase.logger.log(LogStatus.PASS, " 'Equipment' panel is not displaying under 'view site order' page");
			 System.out.println(" 'Equipment' panel is not displaying under 'view site order' page");
			 panelheader=false;
			 
		 }}catch(NoSuchElementException e) {
			 e.printStackTrace();
			 DriverTestcase.logger.log(LogStatus.PASS, " 'Equipment' panel is not displaying under 'view site order' page");
			 System.out.println(" 'Equipment' panel is not displaying under 'view site order' page");
			 panelheader=false;
			 
		 }
		 
		  return panelheader;
	  }
	  
	  
	  public void findlistofInterfaces_Equipment_viewdevicePage(String application, String devicename) throws InterruptedException, DocumentException {
		 
		  scrolltoend();
		  Thread.sleep(3000);
		  
		  Clickon(getwebelement("//div[div[div[text()='Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='View']"));
			Thread.sleep(5000);  
		  
		 
		 List<WebElement> interfacelist= getwebelements(xml.getlocator("//locators/" + application + "/findlistofInterfacesNames"));
		 
		 int interfacelistSize=interfacelist.size();
		 System.out.println("list of interfaces displaying are:"+ interfacelistSize);
		 DriverTestcase.logger.log(LogStatus.PASS, " size of interfaces displaying is: "+ interfacelistSize);
		 
		 DriverTestcase.logger.log(LogStatus.PASS, " Interfaces displaying are: ");
		 System.out.println(" Interfaces displaying are: ");
		 
		 for (WebElement interfaceName : interfacelist) {
			 
			 DriverTestcase.logger.log(LogStatus.INFO, " "+ interfaceName.getText());
			 System.out.println("Interface names are: "+ interfaceName.getText());
		 }
	  }
	  
	  
	  public void findlistofInterfaces_IntEquipment_viewdevicePage(String application, String devicename) throws InterruptedException, DocumentException {
		scrolltoend();
		Thread.sleep(3000);
		
		
		  Clickon(getwebelement(xml.getlocator("//div[div[div[text()='Intermediate Equipment']]]//div[div[div[contains(@title,'"+ devicename +"')]]]//span[text()='View']")));
			Thread.sleep(5000); 
		  
		  scrolltoend();
		  Thread.sleep(3000);
		 List<WebElement> interfacelist= getwebelements(xml.getlocator("//locators/" + application + "/findlistofInterfacesNames"));
		 
		 int interfacelistSize=interfacelist.size();
		 System.out.println("list of interfaces displaying are:"+ interfacelistSize);
		 DriverTestcase.logger.log(LogStatus.PASS, " size of interfaces displaying is: "+ interfacelistSize);
		 
		 DriverTestcase.logger.log(LogStatus.PASS, " Interfaces displaying are: ");
		 System.out.println(" Interfaces displaying are: ");
		 
		 for (WebElement interfaceName : interfacelist) {
			 
			 DriverTestcase.logger.log(LogStatus.INFO, " "+ interfaceName.getText());
			 System.out.println("Interface names are: "+ interfaceName.getText());
		 }
	  }
	  
	  
	  public void configure_circuitId(String application, String circuitID) throws InterruptedException {
		  
		  try {	
				if(circuitID.equalsIgnoreCase("null")) {
					
					DriverTestcase.logger.log(LogStatus.PASS, " No input provided for 'Circuit ID' field");
					System.out.println(" No input provided for 'Circuit ID' field");
					
				}else {
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/selectCircuit_togglebutton")));
				Thread.sleep(3000);
				
				SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")), circuitID);
				DriverTestcase.logger.log(LogStatus.PASS, circuitID + " is the value passed for 'Circuit ID' field");
				
				String actualvalue=getwebelement(xml.getlocator("//locators/" + application + "/Equipment_configureXNGCircuitID")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, " Vallue entered in 'Circuit Id' field is: "+actualvalue);
				}
			 }catch(NoSuchElementException e) {
				 e.printStackTrace();
				 DriverTestcase.logger.log(LogStatus.FAIL, "'circuit Id' field under 'Edit Interface' page is not available ");
			 }catch(Exception err) {
				 err.printStackTrace();
				 DriverTestcase.logger.log(LogStatus.FAIL, "Not able to enter value inside 'Circuit Id' field");
			 }
			 Thread.sleep(3000);
	  }
	  
	  
	 public void fetchDeviceInterface_viewdevicepage(String application) throws InterruptedException, DocumentException {
	
		 scrollToTop();
		 Thread.sleep(3000);
		 
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/viewPCEdevice_Actiondropdown")));
			
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/fetchDeviceinterfacelink_viewDevicePage")));
			Thread.sleep(3000);
			
			
		//verify success Message
			boolean successMessage=false;
			successMessage=getwebelement(xml.getlocator("//locators/" + application + "/succesMessageForfetchDeviceInterfcae")).isDisplayed();
			String actualMessage=getwebelement(xml.getlocator("//locators/" + application + "/succesMessageForfetchDeviceInterfcae")).getText();
			if(successMessage) {
				DriverTestcase.logger.log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
				System.out.println(" After clicking on 'Fetch Device Interface' link, success Message is displaiyng as expected");
				
				DriverTestcase.logger.log(LogStatus.PASS, " Success Message displays as: "+actualMessage);
				System.out.println(" Success Message displays as: "+actualMessage);
				
				//click on the 'click here' link
//				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/succesMessageForfetchDeviceInterfcae")));
//				Thread.sleep(3000);
				
//				manageNetwork(application);
				
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " After clicking on 'Fetch Device Interface' link, success Message is not displaying");
				System.out.println(" After clicking on 'Fetch Device Interface' link, success Message is not displaying");
			}
	 }
	 
	 
	 public void manageNetwork(String application) throws InterruptedException, DocumentException {
		 
		 boolean manageNetworkHeader=false;
		 manageNetworkHeader= getwebelement(xml.getlocator("//locators/" + application + "/ManageNetworkHeaderName")).isDisplayed();
		 if(manageNetworkHeader) {
			 DriverTestcase.logger.log(LogStatus.PASS, " 'Manage Network' header name is displaying as expected");
		 }
	 }
	

	 public void verifyeditedinterfacevalue(String application, String interfaceName) throws InterruptedException {
		 
		 List<WebElement> interfacedetails=getwebelements("//div[div[text()='"+ interfaceName +"']]/div");
		 
		 
		 for(WebElement listOfInterfacenames : interfacedetails) {
			 
			 String ColumnNames=listOfInterfacenames.getAttribute("col-id");
			 String values= listOfInterfacenames.getText();
			 
			 DriverTestcase.logger.log(LogStatus.PASS, "After Editing interface, list of values displaying in interface table are: ");
			 DriverTestcase.logger.log(LogStatus.PASS, "value displaying for "+ ColumnNames + " is: "+values);
			 
			 System.out.println("After Editing interface, list of values displaying in interface table are: ");
			 System.out.println("value displaying for "+ ColumnNames + " is: "+values);
			 
		 }
	 }
	 
	 public boolean EquipmentCOnfigurationPanel(String application) throws InterruptedException, DocumentException {
		 
		 boolean EquipConfigPanel=false;
		 EquipConfigPanel = getwebelement(xml.getlocator("//locators/" + application + "/EquipementConfigurationPanel")).isDisplayed();
		 if(EquipConfigPanel) {
			 DriverTestcase.logger.log(LogStatus.PASS, "In 'view Site Order' page, 'Equipment Configuration' panel is displaying as expected for 'Actelis' Technology");
		System.out.println( "In 'view Site Order' page, 'Equipment Configuration' panel is displaying as expected for 'Actelis' Technology");
		 }else {
			 DriverTestcase.logger.log(LogStatus.FAIL, "In 'view Site Order' page, 'Equipment Configuration' panel is not displaying for 'Actelis' Technology");
			 System.out.println("In 'view Site Order' page, 'Equipment Configuration' panel is not displaying for 'Actelis' Technology");
			 
		 }
		return EquipConfigPanel;
		 
	 }
	 
	 public void equipConfiguration_Actelis_AddDevice(String application, String devicename, String vendor, String routerId, 
			 String manageAddress, String MEPid, String ETH_Port) throws InterruptedException, DocumentException, IOException {
		
		 scrolltoend();
		 Thread.sleep(3000);
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/equipConfig_addCPEdevice")));
		 Thread.sleep(3000);
		 
		 boolean addActelisHeader=false; 
		 addActelisHeader=getwebelement(xml.getlocator("//locators/" + application + "/addActelisCPEpage_headerName")).isDisplayed();
		 if(addActelisHeader) {
			 DriverTestcase.logger.log(LogStatus.PASS, " 'Add Actelis CPE device' page is displaying as expected");
			 System.out.println(" 'Add Actelis CPE device' page is displaying as expected");
			
			 Clickon(getwebelement("//span[text()='OK']"));
			 Thread.sleep(2000);
			 
			//Validate Warning Messages_Name Field
			 warningMessage_commonMethod(application, "devicenameFieldErrMSg_addCPE_Actelis" , "Name");
			 
			//Validate Warning Messages_Router ID Field
			 warningMessage_commonMethod(application, "RouterIDFieldErrMSg_addCPE_Actelis",  "Router Id");
			 
			//Validate Warning Messages_Management Address Field 
			 warningMessage_commonMethod(application, "manageAddressFieldErrMSg_addCPE_Actelis", "Management Address");
			 
			//Name 
			 addtextFields_commonMethod(application, "Name", "nameField_addCPE_Actelis", devicename);
			 
			//vendor/Model
			 addDropdownValues_commonMethod(application, "Vendor/Model", "vendorField_addCPE_Actelis", vendor);
			 
			//Router Id
			 addtextFields_commonMethod(application, "Router Id", "RouterIdField_addCPE_Actelis", routerId);
			 
			//Management Address
			 addtextFields_commonMethod(application, "Management Address", "managementAddressField_addCPE_Actelis", manageAddress);
			 
			//MEP Id
			 addtextFields_commonMethod(application, "MEP Id", "MEPidField_addCPE_Actelis", MEPid);
			 
			//ETH Port
			 addDropdownValues_commonMethod(application, "ETH Port", "ETHportField_addCPE_Actelis", ETH_Port);
			 
			//OK button
			 OKbutton_AddSiteOrder(application);
			 
			//CAncel button 
		     cancelbutton_AddSiteOrder(application);
		     
		     
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/okbutton")));
		 Thread.sleep(4000);
		 

		 }else {
			 DriverTestcase.logger.log(LogStatus.FAIL, " 'Add Actelis CPE device' page is not displaying");
		 }
	 }
	 
	 
	 public void warningMessage_commonMethod(String application, String xpath, String fieldlabelName) throws InterruptedException, DocumentException {
	     	boolean message=false;
	     	//Field Error Message
	     			try {
	     				message = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath  +"")).isDisplayed();
	     				Thread.sleep(3000);
	     			sa.assertTrue(message, fieldlabelName + " field warning message is not displayed ");
	     			if(message) {
	     			String ErrMsg = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath  +"")).getText();
	     			
	     			System.out.println( fieldlabelName + " field warning  message displayed as : " + ErrMsg);
	     			DriverTestcase.logger.log(LogStatus.PASS, "Step :  validation message for"+ fieldlabelName +"  field displayed as : " + ErrMsg);
	     			Log.info(fieldlabelName + " field warning  message displayed as : " + ErrMsg);
	     			}
	     			}catch(NoSuchElementException e) {
	     				e.printStackTrace();
	     				System.out.println( fieldlabelName + " field warning message is not dipslaying");
	     				DriverTestcase.logger.log(LogStatus.FAIL, fieldlabelName + " field warning message is not dipslaying");
	     			}catch(Exception ed) {
	     				ed.printStackTrace();
	     				System.out.println(" NO warning message displayed for "+ fieldlabelName);
	     			}
	     }
	 
	 
	 public void verifysuccessmessageforDeviceCreation_Actellis() throws InterruptedException {
			
			boolean devicecreationmsg=getwebelement("//span[contains(text(),'Device successfully created')]").isDisplayed();
			sa.assertTrue(devicecreationmsg, " 'Site device created successfully' message is not getting displayed in viewdevice page after creating device");
			if(devicecreationmsg) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'Device successfully created ' ,message is displaying as expected");
			System.out.println(" 'Device successfully created ' ,message is displaying as expected");
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL," 'Device successfullycreated' message is not getting displayed after creating device' ");
				System.out.println(" 'Device successfullycreated' message is not getting displayed after creating device' ");
			}
			
			
		}
	 
	 public void verifyDataEnteredFordeviceCreation_Actelis(String application, String devicename, String vendorModel, String RouterID,
			 String manageAddress, String mepID, String ETH_Port) throws InterruptedException {
		 scrolltoend();
		 Thread.sleep(3000);
		 
		 Clickon(getwebelement("//div[div[div[text()='Equipment Configuration']]]//div[div[text()='"+ devicename +"']]//span[text()='View']"));
		 Thread.sleep(5000);
		 
		 verifyEnteredvalues( "Name", devicename);
		 
		 verifyEnteredvalues("Vendor/Model", vendorModel);
		 
		 verifyEnteredvalues("Router Id", RouterID);
		 
		 verifyEnteredvalues("Management Address", manageAddress);
		 
		 verifyEnteredvalues("MEP Id", mepID);
		 
		 verifyEnteredvalues("ETH Port", ETH_Port);
		 
	 }
	 
	 
	 public void AddDSLAMandHSL(String application, String DSLMdevice, String HSlname) throws InterruptedException, DocumentException, IOException {
		 
		 System.out.println(" ");
			addDropdownValues_commonMethod(application, "DSLAM Device:", "DSLM_Device_Select", DSLMdevice);

			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/List_HSL_Link")));
			
			selectRowForAddingInterface_Actelis(application, HSlname);
			
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
									DriverTestcase.logger.log(LogStatus.PASS, interfacenumber + " is selected under 'Add DSLAM and Device' page");
									
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
								DriverTestcase.logger.log(LogStatus.FAIL, "failure while selecting interface to add with service");
							}
						}
						break ab;
					}
				}
			} else {

				System.out.println("No values available in table");
				Log.info("No Interfaces got fetched");
				DriverTestcase.logger.log(LogStatus.FAIL, " NO interfaces got fetched");
			}

		}

	 
	 public void showInterface_ActelisConfiguuration(String application) throws InterruptedException, DocumentException {
		 
		 Clickon(getwebelement(xml.getlocator("//locators/" + application + "/showInterface_ActelisCnfiguration")));
		 Thread.sleep(3000);
		 
		 
	 }
	 
	 
	 public void deletInterface_ActelisConfiguration(String application, String interfaceName, String InterfaceDeletionSelection) throws InterruptedException, DocumentException {
		 
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
			 DriverTestcase.logger.log(LogStatus.PASS, " On clicking remoe button, popup message displays as: "+ actualmsg);
			 System.out.println(" On clicking remoe button, popup message displays as: "+ actualmsg);
			 
			 if(InterfaceDeletionSelection.equalsIgnoreCase("Yes")) {
				 
				 Clickon(getwebelement("//button[@class='btn btn-danger']"));
				 Thread.sleep(3000);
			 }else {
				Clickon(getwebelement("//div[contains(text(),'�')]")); 
				Thread.sleep(3000);
				
				DriverTestcase.logger.log(LogStatus.PASS, " Interface is not removed as expected");
			 }
		 }else {
			 
			 DriverTestcase.logger.log(LogStatus.FAIL, " popup message does not display after clicking on 'Remove' button");
		 }
	 }
	
	 
	 public void successMessage_deleteInterfaceFromDevice_ActelisConfiguration(String application) throws InterruptedException, DocumentException {
			
			boolean successMessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface")).isDisplayed();
			String actualmessage=getwebelement(xml.getlocator("//locators/" + application + "/successMessage_ActelisConfiguration_removeInterface")).getText();
			if(successMessage) {
				
				DriverTestcase.logger.log(LogStatus.PASS, " Success Message for removing Interface is dipslaying as expected");
				System.out.println( " Success Message for removing interface is dipslaying as expected");
				
				DriverTestcase.logger.log(LogStatus.PASS, "Message displays as: "+actualmessage);
				System.out.println("Message displays as: "+actualmessage);
				
			}else {
				DriverTestcase.logger.log(LogStatus.PASS, " Success Message for removing Interface is not dipslaying");
				System.out.println( " Success Message for removing Interface is not dipslaying");
			}
		}
	 
	 
	 public void verfyFields_ENNIcheckbox(String application) {
		 boolean ENNIAvailability=false;
			boolean ENNISelection=false;
			try {
				ENNIAvailability=getwebelement(xml.getlocator("//locators/" + application + "/ENNI_checkbox")).isDisplayed();
			
			if(ENNIAvailability) {
				DriverTestcase.logger.log(LogStatus.PASS, " 'ENNI' checkbox is displaying under 'Create Service'page as expected");
				
				ENNISelection=getwebelement(xml.getlocator("//locators/" + application + "/ENNI_checkbox")).isSelected();
				if(ENNISelection) {
					DriverTestcase.logger.log(LogStatus.FAIL, " 'ENNI' checkbox is selected by default");
					System.out.println(" 'ENNI' checkbox is selected by default");
				}else {
					DriverTestcase.logger.log(LogStatus.PASS, " 'ENNI' checkbox is not selected by default");
					System.out.println( " 'ENNI' checkbox is not selected by default");
				}
				
			}else {
				DriverTestcase.logger.log(LogStatus.FAIL, " 'ENNI' checkbox is not available under 'Create Service' page");
			}
			
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " 'ENNI' chekcbox is not displaying");
				System.out.println(" 'ENNI' chekcbox is not displaying");
			}catch(Exception ee) {
				ee.printStackTrace();
				DriverTestcase.logger.log(LogStatus.PASS, " 'ENNI' checkbox is not selected");
				System.out.println(" 'ENNI' checkbox is not selected");
			}
	 }
	 
	 
	 public void addCheckbox_commonMethod(String application, String xpath, String labelname, String expectedValue) throws InterruptedException, DocumentException {
			
			boolean availability=false;
			try {	
				availability = getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
				if(availability) {
					
					DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is displaying as expected");
					System.out.println(labelname + " checkbox is displaying as expected");
				if(!expectedValue.equalsIgnoreCase("null")) {
					if (expectedValue.equalsIgnoreCase("yes")) {

						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
						Thread.sleep(3000);
						Log.info(labelname + " check box is selected");
						
						boolean CPEselection=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isSelected();
						if(CPEselection) {
							DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is selected as expected");
							System.out.println(labelname + " checkbox is selected as expected");
						}else {
							DriverTestcase.logger.log(LogStatus.FAIL, labelname + "  checkbox is not selected");
							System.out.println(labelname + "  checkbox is not selected");
						}
						
					}
					else {

						Log.info(labelname + " checkbox is not selected as expected");
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is not selected as expected"); 
					}
				}
				}else {
					DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checbox is not available");
					System.out.println(labelname + " checbox is not available");
				}
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, labelname +  " checkbox is not available ");
				System.out.println( labelname +  " checkbox is not available ");
			}catch(Exception er) {
				er.printStackTrace();
				System.out.println("Not able to select single end point CPE checkbox");
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on 'single end point CPE' checkbox");
			}
		}
	 
	 
	 public void editcheckbox_commonMethod(String application, String expectedResult, String xpath, String labelname) {
		 
		  boolean Availability=false;
		  try {
			  Availability=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
		  
		  if(Availability) {
			  
			  DriverTestcase.logger.log(LogStatus.PASS,  labelname+ " checkbox is displaying as expected in edit page");
			  
			if(!expectedResult.equalsIgnoreCase("null")) {
				
				boolean isElementSelected=getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isSelected();
				Thread.sleep(2000);
				
				if (expectedResult.equalsIgnoreCase("yes")) {

					if(isElementSelected) {
						
						DriverTestcase.logger.log(LogStatus.PASS, labelname +" checkbox is not edited and it is already Selected while creating");
						
					}else {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
						Log.info(labelname + " check box is selected");
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " checkbox is selected");
					}
				}
				else if (expectedResult.equalsIgnoreCase("no")) {
					
					if(isElementSelected) {
						
						Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
						Log.info(labelname + " check box is unselected");
						DriverTestcase.logger.log(LogStatus.PASS,labelname + " is edited and gets unselected");
						
					}else {
						DriverTestcase.logger.log(LogStatus.PASS, labelname + " is not edited and it remains unselected");
					}
					
				}
			}else {
				DriverTestcase.logger.log(LogStatus.PASS,"No changes made for "+ labelname +"  chekbox");
			}
		  }else {
			  DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checkbox is not available in 'Edit Service' page");
		  }

			}catch(NoSuchElementException e) {
				e.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, labelname + " checkbox is not displaying under 'Edit service' page");
				System.out.println(labelname+" checkbox is not displaying under 'Edit service' page");
			}catch(Exception err) {
				err.printStackTrace();
				DriverTestcase.logger.log(LogStatus.FAIL, " Not able to click on "+ labelname + " checkbox");
				System.out.println(" Not able to click on "+ labelname + " checkbox");
			}
	 }
	 
}
