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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.remote.server.handler.RefreshPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

	import com.relevantcodes.extentreports.LogStatus;
	import com.colt.qa.driverlibrary.DriverHelper;
	import com.colt.qa.driverlibrary.DriverTestcase;
	import com.colt.qa.driverlibrary.Log;
	import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;

	public class APT_HSSHelper extends DriverHelper{
		
		public APT_HSSHelper(WebDriver dr) {
			super(dr);
			// TODO Auto-generated constructor stub
		}
		
		WebElement ChooseCustomer_Select, Next_Button, CreateOrderService_Text;
		XMLReader xml = new XMLReader("src/com/colt/qa/pagerepository/APT_HSS.xml");

		boolean orderdopdown, serviceTypedropdown, modularmspCheckbox, autocreateservicecheckbox, interfacespeeddropdown,
				servicesubtypesdropdown, availablecircuitsdropdown, nextbutton, A_EndTechnolnogy, B_Endtechnolnogy;
		SoftAssert sa = new SoftAssert();

		public void createcustomer(String application, String name, String maindomain, String country, String ocn,
				String reference, String tcn, String type, String email, String phone, String fax)
						throws Exception {


			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
			Thread.sleep(2000);
			System.out.println("Mouse hovered on Manage Customer's Service");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
			Log.info("Mouse hovered on 'Manage Customers Service' menu item");

			click_commonMethod(application, "create customer link", "createcustomerlink", xml);
			waitforPagetobeenable();
			compareText(application, "create customer page header", "createcustomer_header", "Create Customer", xml);
			ScrolltoElement(application, "okbutton", xml);
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
			ScrolltoElement(application, "resetbutton", xml);
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
			ScrolltoElement(application, "okbutton", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Ok", "okbutton", xml);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Customer successfully created.");
			sa.assertAll();
		}


		public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected)
				throws InterruptedException, DocumentException, IOException {

			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageCustomerServiceLink")));
			Thread.sleep(2000);
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
				click_commonMethod(application, "select order switch", "selectorder_switch", xml);
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
			
		}

		
		public void orderServiceFieldvalidation(String application) throws InterruptedException, DocumentException, IOException 
		{
	        scrolltoend();
	        Thread.sleep(1000);
			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on OK button to check mandatory messages");

			Thread.sleep(5000);
			
			
			compareText(application, "Remark", "RemarkLabel", "Remark", xml);

			
			compareText(application, "Service Identification", "ServiceIdentificationLabel", "Service Identification", xml);
			
			compareText(application, "Email", "EmailLabel", "Email", xml);
			
			compareText(application, "Service Type", "ServiceTypeLabel", "Service Type", xml);
			
			compareText(application, "Phone Contact", "PhoneContactLabel", "Phone Contact", xml);
			
			
			compareText(application, "Performance Reporting", "PerformancereportingLabel", "Performance Reporting", xml);
			
			compareText(application, "Wave Service", "WaveServicelabel", "Wave Service", xml);
			
			scrollToTop();
			Thread.sleep(1000);
			warningMessage_commonMethod(application, "serviceIdentificationerrmsg", "Service Identification",xml);
			
			//public void warningMessage_commonMethod(String application, String xpath, String fieldlabelName, XMLReader xml)

			
		}
		
		
		public void verifyservicecreation(String application ,String ServiceIdvalue, String RemarkValue
				, String EmailValue, String PhoneContactValue, String PerformanceReporting_Checkbox 
				,String WaveService_checkbox, String InterfaceSpeedValue, String TypeOfServiceValue) 
				throws InterruptedException, DocumentException, IOException 
		{
			
			addtextFields_commonMethod(application, "Service Identification", "ServiceIdentification", ServiceIdvalue,xml);
			
			addtextFields_commonMethod(application, "Remark", "RemarkText",RemarkValue,xml);
			
			addtextFields_commonMethod(application, "Email", "EmailText",EmailValue,xml);
			click_commonMethod(application, ">>", "forwardarrow", xml);
			
			addtextFields_commonMethod(application, "Phone Contact", "PhoneContact",PhoneContactValue,xml);
			click_commonMethod(application, ">>", "forwardarrow_1", xml);
			
			ScrolltoElement(application, "managementoptions_header", xml);
			Thread.sleep(1000);
		    addCheckbox_commonMethod(application,"performanceReportingcheckbox","Performance Reporting",PerformanceReporting_Checkbox,"no",xml);
			
			 addCheckbox_commonMethod(application,"waveServiceCheckbox","waveServiceCheckbox",WaveService_checkbox,"no",xml);

			 if(WaveService_checkbox.equalsIgnoreCase("Yes")) {
				 addDropdownValues_commonMethod(application, "Interface Speed", "InterfaceSpeedDropdown", InterfaceSpeedValue, xml);
				 addDropdownValues_commonMethod(application, "Type Of Service", "TypeofServiceDropdown", TypeOfServiceValue, xml);
			 }
			 
				scrolltoend();
				click_commonMethod(application, "OK", "OKbutton", xml);
				waitforPagetobeenable();
				verifysuccessmessage(application, "Service successfully created.");
		    
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
		
		public void verifyservicepanelInformationinviewservicepage(String application ,String ServiceIdvalue
				, String RemarkValue, String EmailValue, String PhoneContactValue, String ServiceType
				) throws InterruptedException, DocumentException, IOException{
			
			ScrolltoElement(application, "orderpanelheader", xml);
			Thread.sleep(1000);
			
			compareText_InViewPage(application ,"Service Identification",ServiceIdvalue,xml);
			compareText_InViewPage(application ,"Service Type",ServiceType,xml);
			GetText(application, "Remarks", "servicepanel_remarksvalue");
			compareText(application, "Email", "servicepanel_email", EmailValue, xml);
			compareText_InViewPage(application ,"Phone Contact",PhoneContactValue,xml);
			
		}
		
		
		public void VerifyEditService(String application, String EditRemarks, String sid, String editemail, String editphonecontact) throws Exception
		{

			ScrolltoElement(application, "orderpanelheader", xml);
			Thread.sleep(1000);
			//Edit service
			click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
			click_commonMethod(application, "Edit", "edit", xml);
			Thread.sleep(2000);
			scrollToTop();
			edittextFields_commonMethod(application, "Service Identification", "serviceidentificationtextfield", sid, xml);
			edittextFields_commonMethod(application, "Remarks", "remarktextarea", EditRemarks, xml);
			
			//Edit email
			click_commonMethod(application, "Selected Email", "selectedemail", xml);
			click_commonMethod(application, "Email remove arrow", "emailremovearrow", xml);
			edittextFields_commonMethod(application, "Email", "emailtextfieldvalue", editemail, xml);
			click_commonMethod(application, "Email adding arrow", "emailaddarrow", xml);
			//Edit phone contact
			scrolltoend();
			click_commonMethod(application, "Selected phone contact", "selectedphone", xml);
			click_commonMethod(application, "Phonecontact remove arrow", "phoneremovearrow", xml);
			edittextFields_commonMethod(application, "Phone Contact", "phonecontacttextfieldvalue", editphonecontact, xml);
			click_commonMethod(application, "phonecontact adding Arrow", "phoneaddarrow", xml);
			scrolltoend();
			click_commonMethod(application, "OK", "editservice_okbutton", xml);
			Thread.sleep(3000);
			if(getwebelement(xml.getlocator("//locators/" + application + "/customerdetailsheader")).isDisplayed())
			{
				Log.info("Navigated to view service page");
				System.out.println("Navigated to view service page");
				//compareText(application, "Service updated success message", "successmsg", "Service updated successfully", xml);	
				verifysuccessmessage(application, "Service successfully updated.");
			}
			else
			{
				Log.info("Service not updated");
				System.out.println("Service not updated");
			}
			
		}
		
		public void verifyDump(String application) throws InterruptedException, DocumentException {
			
			ScrolltoElement(application, "orderpanelheader", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
			click_commonMethod(application, "Dump", "dump_link", xml);
			waitforPagetobeenable();
			Thread.sleep(2000);
			GetText(application, "Dump header", "dumppage_header");
			compareText(application, "Service header", "service_header", "Service", xml);
			GetText(application, "Dump page service details", "dumppage_text");
			Thread.sleep(1000);
			click_commonMethod(application, "Close", "closesymbol", xml);
		}
		
		public void verifyManageService(String application, String changeorderno, String sid, String servicetype, String servicestatus, String syncstatus, String servicestatuschangerequired) throws InterruptedException, DocumentException, IOException {
			
			//Manage service
			ScrolltoElement(application, "orderpanelheader", xml);
			Thread.sleep(1000);
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
			Gettext(getwebelement(xml.getlocator("//locators/" + application + "/status_servicedetails")));
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

			if(servicestatuschangerequired.equalsIgnoreCase("Yes"))
			{
				click_commonMethod(application, "Status", "statuslink", xml);
				waitforPagetobeenable();
				Thread.sleep(1000);
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
			compareText(application, "Service Type Header", "sync_servicetypeheader", "Service Type", xml);
			compareText(application, "Details Header", "sync_detailsheader", "Details", xml);
			compareText(application, "Sync Status Header", "sync_syncstatus", "Sync Status", xml);
			
			GetText(application, "Order Name", "sync_ordername");
			compareText(application, "Service Identification", "sync_servicename", sid, xml);
			compareText(application, "Service type", "sync_servicetype", servicetype, xml);
			GetText(application, "Service Details", "sync_servicedetails");
			GetText(application, "Sync Status", "sync_status");
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
			
			GetText(application, "Device Name", "deviceforservicepanel_devicename");
			GetText(application, "Sync Status", "deviceforservicepanel_syncstatus");
			click_commonMethod(application, "Manage", "deviceforservicepanel_managelink", xml);
			Thread.sleep(2000);
			waitforPagetobeenable();
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
		 
		public void verifyShowNewInfovistaReport(String application) throws Exception {
			
			shownewInfovista(application);
			Thread.sleep(2000);
		}

		public void shownewInfovista(String application) throws Exception {

			ScrolltoElement(application, "orderpanelheader", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Show New Infovista Report", "shownewinfovistareport_link", xml);
			Thread.sleep(3000);
			waitforPagetobeenable();

			String expectedPageName= "SSO Login Page";

			//Switch to new tab
			List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(browserTabs .get(1));
			Thread.sleep(10000);

			try { 
				// Get Tab name
				String pageTitle=driver.switchTo().window(browserTabs .get(1)).getTitle();
				System.out.println("page title displays as: "+pageTitle);

				//assertEquals(pageTitle, expectedPageName, " on clicking 'Show Infovista link', it got naviagted to "+pageTitle);
				sa.assertEquals(pageTitle, expectedPageName);
				
				Thread.sleep(3000);
				driver.close();
				driver.switchTo().window(browserTabs.get(0)); 

				ExtentTestManager.getTest().log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
				Thread.sleep(3000);

				ExtentTestManager.getTest().log(LogStatus.PASS, "show info vista page actual title: "+pageTitle );
				ExtentTestManager.getTest().log(LogStatus.PASS, "show info vista page expected title: "+ expectedPageName);

			}catch(Exception e) {

				e.printStackTrace();

				Thread.sleep(3000);
				driver.close();
				driver.switchTo().window(browserTabs.get(0));

				ExtentTestManager.getTest().log(LogStatus.FAIL, expectedPageName + " page is not displaying");

			}
			sa.assertAll();
		}
		
		public void verifyShowInfovistaReport(String application) throws Exception {
			
			showInfovista(application);
			Thread.sleep(2000);
		}

		public void showInfovista(String application) throws Exception {

			ScrolltoElement(application, "orderpanelheader", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Show Infovista Report", "showinfovistareport_link", xml);
			Thread.sleep(3000);
			waitforPagetobeenable();
			String expectedPageName= "SSO Login Page";

			//Switch to new tab
			List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(browserTabs .get(1));
			Thread.sleep(10000);

			try { 
				if((driver.switchTo().window(browserTabs .get(1)).getTitle()).contains(expectedPageName))
				{
				// Get Tab name
				String pageTitle=driver.switchTo().window(browserTabs .get(1)).getTitle();
				System.out.println("page title displays as: "+pageTitle);

				//assertEquals(pageTitle, expectedPageName, " on clicking 'Show Infovista link', it got naviagted to "+pageTitle);
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
		
		public void VerifyManagementPanel(String application , String PerformanceReporting_Checkbox , String WaveService_checkbox, String InterfaceSpeedValue
				, String TypeOfServiceValue) throws InterruptedException, DocumentException, IOException
		{
			ScrolltoElement(application, "managementoptions_header", xml);
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)"); 
			
			compareText(application, "Management Options", "managementoptions_header", "Management Options", xml);
			//Verify Performance Reporting
			if(PerformanceReporting_Checkbox.equalsIgnoreCase("Null")) {
				compareText(application, "Performance Reporting", "performancereporting_value", "No", xml);
			}
			else if(PerformanceReporting_Checkbox.equalsIgnoreCase("Yes")) {
				compareText(application, "Performance Reporting", "performancereporting_value", "Yes", xml);
			}
			else {
				compareText(application, "Performance Reporting", "performancereporting_value", "No", xml);
			}
			//Verify Wave Service
			if(WaveService_checkbox.equalsIgnoreCase("Null")) {
				compareText(application, "Wave Service", "waveservice_value", "No", xml);
			}
			else if(WaveService_checkbox.equalsIgnoreCase("Yes")) {
				compareText(application, "Wave Service", "waveservice_value", "Yes", xml);
			}
			else {
				compareText(application, "Wave Service", "waveservice_value", "No", xml);
			}
			
			if(WaveService_checkbox.equalsIgnoreCase("Yes")) {
				//verify interface speed
				GetText(application, "Interface Speed", "interfacespeed_value");
				//verify Type of service
				GetText(application, "Type Of Service", "typeofservice_value");
			}
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

		public void verifyorderpanel_editorder(String application, String editorderno, String editvoicelineno, String editOrderSelection) 
				throws InterruptedException, DocumentException, IOException {

			ExtentTestManager.getTest().log(LogStatus.INFO, "Verifying 'Edit Order' Functionality");
			
			ScrolltoElement(application, "userspanel_header", xml);

			if(editOrderSelection.equalsIgnoreCase("No")) {
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
				waitforPagetobeenable();
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
				waitforPagetobeenable();
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
				waitforPagetobeenable();
				compareText(application, "Change Order header", "changeorderheader", "Change Order", xml);
				Thread.sleep(1000);
				
					addDropdownValues_commonMethod(application, "Order/Contract Number (Parent SID)", "changeorder_chooseorderdropdown", ChangeOrder_existingOrderNumber, xml);
					
					click_commonMethod(application, "OK", "changeorder_okbutton", xml);
					waitforPagetobeenable();
					ScrolltoElement(application, "userspanel_header", xml);
					Thread.sleep(1000);
					compareText(application, "Order Number", "ordernumbervalue", ChangeOrder_existingOrderNumber, xml);
					compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
					Log.info("------ Change Order is successful ------");
		
			}
			
		}

		public void navigateToAddNewDevicepage(String application) throws InterruptedException, DocumentException {

			ScrolltoElement(application, "deviceheader", xml);
			Thread.sleep(1000);
			compareText(application, "Device", "deviceheader", "Device", xml);
			click_commonMethod(application, "Add Device", "AddDevice", xml);
			compareText(application, "Add Device Header", "adddevice_header", "Add Device", xml);
			click_commonMethod(application, "Add New Device toggle", "addnewdevice_togglebutton", xml);
		}
		
		public void addNewPEDevice(String application, String name, String devicetype, 
				String vendormodel, String telnet, String ssh, String snmp2c,String SnmPro, String Snmprw, 
				String snmpro2cvalue, String snmprw2cvalue , String snmp3 , String Snmpv3Username,String 
				Snmpv3Authpassword, String Snmpv3Privpassword, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,
				String Snmpv3Privpasswordvalue, String securityprotocols_dropdownvalue, String Country, String managementaddress, String existingcity, 
				String existingcityvalue, String existingsite,String existingsitevalue, String existingpremise, 
				String existingpremisevalue, String newcity,String cityname,String Citycode,
				String sitename, String sitecode, String premisename, String premisecode, 
				String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {

			try {

				scrollToTop();
				Thread.sleep(1000);
				//name
				addtextFields_commonMethod(application, "Name", "devicename_textfield", name, xml);

				//vendormodel
				addDropdownValues_commonMethod(application, "Vendor/Model", "vendormodelinput", vendormodel, xml);

				//Management address
				addtextFields_commonMethod(application, "Management Address", "managementaddresstextbox", managementaddress, xml);

				//snmpro
				String SnmproValue= getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Snmpro value is displayed as: " +SnmproValue);
				
				if(vendormodel.contains("Ciena")) {
					
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
					
					addDropdownValues_commonMethod(application, "Security Protocols", "securityprotocols_dropdown", securityprotocols_dropdownvalue, xml);
					
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
			click_commonMethod(application, "OK", "OKbutton", xml);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Device successfully created.");
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
		
		public void verifyViewpage_Devicedetails(String application, String sid, String name, String vendormodel
				, String telnet, String ssh, String snmp2c, String snmpro2cvalue, String snmprw2cvalue
				, String snmp3, String Snmpv3Usernamevalue, String Snmpv3Authpasswordvalue,String Snmpv3Privpasswordvalue
				, String securityprotocols_dropdownvalue, String Country, String managementaddress, String existingcity 
				, String existingcityvalue, String existingsite, 
				String existingsitevalue, String existingpremise, 
				String existingpremisevalue, String newcity,String cityname,String Citycode,
				String sitename, String sitecode,  String premisename,  String premisecode, 
				String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {

			
//			ScrolltoElement(application, "deviceheader", xml);
//			Thread.sleep(1000);
//			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
//			{
//				click_commonMethod(application, "View", "viewservicepage_viewdevicelink", xml);
//				Thread.sleep(5000);
			
			waitforPagetobeenable();
			scrollToTop();
			//Device Name
			GetText(application, "Name", "viewpage_devicename");

			//Vendor/model
			compareText(application, "Vendor/Model", "viewpage_vendormodel", vendormodel, xml);

			//Management Address
			compareText(application, "Management Address", "viewpage_managementaddress", managementaddress, xml);

			if(vendormodel.contains("Ciena")) 
			{
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
			GetText(application, "Security Protocols", "viewpage_securityprotocols");
			
			}
			
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
			
			scrollToTop();
			Thread.sleep(1000);
			clickOnBreadCrumb(application, sid);
			
//			}
//			else
//			{
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
//			}
			//waitforPagetobeenable();
		}

		public void verifyEditDevice(String application, String editDevicename, String editVendorModel
				, String editTelnet, String editSSH, String editSnmp2C, String editSnmp3,String editSnmProNewValue
				, String editSnmprwNewValue, String editSnmpv3UsernameNewValue, String editSnmpv3AuthpasswordNewValue
				, String editSnmpv3PrivpasswordNewValue, String edit_securityprotocols_dropdownvalue, String editManagementAddress, String editCountry
				, String editExistingCity, String editExistingCityValue, String editExistingSite, String editExistingSiteValue,
				String editExistingPremise, String editExistingPremiseValue, String editNewCity, String editNewSite, String editNewPremise, String editNewCityName,
				String editNewCityCode, String editNewSiteName, String editNewSiteCode, String editNewPremiseName, String editNewPremiseCode) throws InterruptedException, DocumentException, IOException {

			try {

				waitforPagetobeenable();
				ScrolltoElement(application, "deviceheader", xml);
				Thread.sleep(1000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
				{
					click_commonMethod(application, "Edit", "viewservicepage_editdevicelink", xml);
					Thread.sleep(5000);
					waitforPagetobeenable();
					scrollToTop();
					compareText(application, "Edit Device header", "editdeviceheader", "Edit Device", xml);

					//edit device fields

					//name
					String DeviceNameValue= getwebelement(xml.getlocator("//locators/" + application + "/devicename_textfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Device Name value is displayed as: " +DeviceNameValue);
					
					//vendormodel
					addDropdownValues_commonMethod(application, "Vendor/Model", "vendormodelinput", editVendorModel, xml);

					//Management address
					edittextFields_commonMethod(application, "Management Address", "managementaddresstextbox", editManagementAddress, xml);

					//snmpro
					String SnmproValue= getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Step : Snmpro value is displayed as: " +SnmproValue);
					
					if(editVendorModel.contains("Ciena")) {
						
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
						
						addDropdownValues_commonMethod(application, "Security Protocols", "securityprotocols_dropdown", edit_securityprotocols_dropdownvalue, xml);
					}
					
					//select country
					scrolltoend();
					Thread.sleep(2000);
					addDropdownValues_commonMethod(application, "Country", "countryinput", editCountry, xml);
					if(editCountry.equalsIgnoreCase("Yes")) {
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
				}
				else {

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
			click_commonMethod(application, "OK", "OKbutton", xml);
			waitforPagetobeenable();
			verifysuccessmessage(application, "Device successfully updated.");
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

		public void AddExistingDevice(String application, String existingDeviceName) throws InterruptedException, DocumentException {
			
			ScrolltoElement(application, "deviceheader", xml);
			Thread.sleep(1000);
		compareText(application, "Device", "deviceheader", "Device", xml);
		click_commonMethod(application, "Add Device", "AddDevice", xml);
		compareText(application, "Add Device Header", "adddevice_header", "Add Device", xml);
		scrollToTop();
		addDropdownValues_commonMethod(application, "Name", "name_dropdown", existingDeviceName, xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "OK", "okbutton", xml);
		waitforPagetobeenable();
		verifysuccessmessage(application, "Device successfully created.");
		scrolltoend();
		click_commonMethod(application, "Back", "Backbutton", xml);
		waitforPagetobeenable();
	}
		
		public void selectInterfacelinkforDevice(String application, String existingDeviceName) throws InterruptedException, DocumentException {
			
			Thread.sleep(2000);
			ScrolltoElement(application, "deviceheader", xml);
			Thread.sleep(1000);
			
			if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
			{
				List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevices_list"));
				System.out.println(addeddevicesList);
				int AddedDevicesCount= addeddevicesList.size();
				for(int i=0;i<AddedDevicesCount;i++) {
					String AddedDeviceNameText= addeddevicesList.get(i).getText();
					String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
					if(AddedDeviceNameText.contains(existingDeviceName))
					{
						WebElement AddedDevice_SelectInterfacesLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_selectinterfaceslink").replace("value", AddedDevice_SNo));
						Clickon(AddedDevice_SelectInterfacesLink);
						Thread.sleep(3000);
						waitforPagetobeenable();
					}
				}
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Device added in grid");
			}
						
		}

public void SelectInterface(String application) throws InterruptedException, DocumentException {
			
			ScrolltoElement(application, "viewdeviceheader", xml);
			compareText(application, "Interfaces Header", "interfaceheader", "Interfaces", xml);
			Thread.sleep(1000);
			WebElement InterfacesGridCheck= getwebelement("(//div[text()='Interfaces']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
			String InterfacesGrid= InterfacesGridCheck.getAttribute("style");
			WebElement AddedInterfaces= getwebelement("(//div[text()='Interfaces']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//span[contains(@class,'unchecked')])[1]");
			if(!InterfacesGrid.contains("height: 1px"))
			{
				Clickon(AddedInterfaces);
				Thread.sleep(1000);
				click_commonMethod(application, "Action dropdown", "interfacespanel_actiondropdown", xml);
				click_commonMethod(application, "OK", "interfacespanel_OK_link", xml);
				Thread.sleep(3000);
				waitforPagetobeenable();
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Interfaces available to select");
				click_commonMethod(application, "Back", "Backbutton", xml);
			}
				scrolltoend();
				click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
				
}

public void deleteDevice(String application, String name) throws InterruptedException, DocumentException {

	ScrolltoElement(application, "deviceheader", xml);
	Thread.sleep(1000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
		{
		List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevices_list"));
		//System.out.println(addeddevicesList);
		int AddedDevicesCount= addeddevicesList.size();
		for(int i=0;i<AddedDevicesCount;i++) {
			String AddedDeviceNameText= addeddevicesList.get(i).getText();
			String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
			if(AddedDeviceNameText.contains(name))
			{
				WebElement AddedDevice_DeletefromserviceLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_deletefromservicelink").replace("value", AddedDevice_SNo));
				Clickon(AddedDevice_DeletefromserviceLink);
				Thread.sleep(2000);
				WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
				if(DeleteAlertPopup.isDisplayed())
				{
					click_commonMethod(application, "Delete", "deletebutton", xml);
					Thread.sleep(2000);
					//compareText(application, "Device delete success message", "successmsg", "Device successfully removed from service.", xml);
					verifysuccessmessage(application, "Device successfully deleted ");
				}
				else
				{
					Log.info("Delete alert popup is not displayed");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
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
	
public void deleteExistingDevice(String application, String existingDeviceName) throws InterruptedException, DocumentException {

	ScrolltoElement(application, "deviceheader", xml);
	Thread.sleep(1000);
	if(getwebelement(xml.getlocator("//locators/" + application + "/existingdevicegrid")).isDisplayed())
	{
	List<WebElement> addeddevicesList= getwebelements(xml.getlocator("//locators/" + application + "/addeddevices_list"));
	//System.out.println(addeddevicesList);
	int AddedDevicesCount= addeddevicesList.size();
	for(int i=0;i<AddedDevicesCount;i++) {
		String AddedDeviceNameText= addeddevicesList.get(i).getText();
		String AddedDevice_SNo= AddedDeviceNameText.substring(0, 1);
		if(AddedDeviceNameText.contains(existingDeviceName))
		{
			WebElement AddedDevice_DeletefromserviceLink= getwebelement(xml.getlocator("//locators/" + application + "/addeddevice_deletefromservicelink").replace("value", AddedDevice_SNo));
			Clickon(AddedDevice_DeletefromserviceLink);
			Thread.sleep(2000);
			WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
			if(DeleteAlertPopup.isDisplayed())
			{
				click_commonMethod(application, "Delete", "deletebutton", xml);
				Thread.sleep(2000);
				//compareText(application, "Device delete success message", "successmsg", "Device successfully removed from service.", xml);
				verifysuccessmessage(application, "Device successfully deleted ");
			}
			else
			{
				Log.info("Delete alert popup is not displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
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

public void deleteService(String application) throws InterruptedException, DocumentException	{

	//Delete Service
	ScrolltoElement(application, "orderpanelheader", xml);
	Thread.sleep(1000);
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
	
}

		// scroll to end
		public void scrolltoend() {

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}
		
		
		
		public void clickOnBankPage() {
			driver.findElement(By.xpath("//body")).click();
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
			waitforPagetobeenable();
			scrolltoend();
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/serviceradiobutton")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/searchorder_actiondropdown")));
			Thread.sleep(1000);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/view")));
			Thread.sleep(3000);
			waitforPagetobeenable();
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
    
	}
