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
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Customers Service' menu item");
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
			//compareText(application, "create customer success message", "customercreationsuccessmsg", "Customer successfully created.", xml);
			verifysuccessmessage(application, "Customer successfully created.");
			sa.assertAll();
		}


		public void selectCustomertocreateOrder(String application, String ChooseCustomerToBeSelected)
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
				//compareText(application, "create order success message", "OrderCreatedSuccessMsg", "Order created successfully", xml);			
				verifysuccessmessage(application, "Order created successfully");
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
			//compareText(application, "Create Order / Service Header", "createorderservice_header", "Create Order / Service", xml);

		}

		
		public void orderServiceFieldvalidation(String application) throws InterruptedException, DocumentException, IOException 
		{
	        scrolltoend();
	        Thread.sleep(1000);
			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on OK button to check mandatory messages");

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
		
		
		public void verifyservicecreation(String application ,String ServiceIdvalue, String RemarkValue, String EmailValue,
			String PhoneContactValue ,String Performanceexpected, String PerformanceDefault ,String Waveexpected, String WaveDefault, String InterfaceSpeedValue, String TypeOfServiceValue ) 
				throws InterruptedException, DocumentException, IOException 
		{
			//public void addtextFields_commonMethod(String application, String labelname, String xpathname, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException, IOException {

			
			addtextFields_commonMethod(application, "Service Identification", "ServiceIdentification", ServiceIdvalue,xml);
			
			addtextFields_commonMethod(application, "Remark", "RemarkText",RemarkValue,xml);
			
			addtextFields_commonMethod(application, "Email", "EmailText",EmailValue,xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/forwardarrow")));
			
			
			addtextFields_commonMethod(application, "Phone Contact", "PhoneContact",PhoneContactValue,xml);
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/forwardarrow_1")));
			
			if(Performanceexpected.equalsIgnoreCase("Yes"))
			{
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/performanceReportingcheckbox")));
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Performance Reporting Checkbox");


		    //addCheckbox_commonMethod(application,"performanceReportingcheckbox","Performance Reporting","Performanceexpected","PerformanceDefault",xml);
			}
			
			if(Waveexpected.equalsIgnoreCase("Yes"))
			{
			   // addCheckbox_commonMethod(application,"waveServiceCheckbox","waveServiceCheckbox","Waveexpected","WaveDefault",xml);

				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/waveServiceCheckbox")));
				
				DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Wave Service Checkbox");
				Thread.sleep(1000);
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/InterfaceSpeedDropdown")));
				Clickon(getwebelement("//div[text()='" + InterfaceSpeedValue + "']"));
				DriverTestcase.logger.log(LogStatus.PASS, "Interface Speed Value Selected");
				
				Thread.sleep(1000);
				
				Clickon(getwebelement(xml.getlocator("//locators/" + application + "/TypeofServiceDropdown")));
				Clickon(getwebelement("//div[text()='" + TypeOfServiceValue + "']"));
				DriverTestcase.logger.log(LogStatus.PASS, "Interface Speed Value Selected");
				
			}
		    
		}
		 
		
		public void verifyservicepanelInformationinviewservicepage(String application ,String ServiceIdvalue, String RemarkValue, String EmailValue,
				String PhoneContactValue,String ServiceType ,String Performanceexpected, String PerformanceDefault ,String Waveexpected, String WaveDefault ) throws InterruptedException, DocumentException, IOException
		{
			scrolltoend();
			
			Clickon(getwebelement("//span[contains(text(),'OK')]"));
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on OK");
			Thread.sleep(5000);	
			
			
			
			//Service creation success message
					WebElement Serviceordrmsg = getwebelement(xml.getlocator("//locators/" + application + "/ServiceMessg"));
					
					scrollToTop();
					
					Thread.sleep(2000);
					
					
					boolean successMsgForSevicecreation=Serviceordrmsg.isDisplayed();
					if(successMsgForSevicecreation) {
						System.out.println("Service creation message is displaying as expected:" +Serviceordrmsg.getText());
						DriverTestcase.logger.log(LogStatus.PASS, "Service creation success message displays as:" +Serviceordrmsg.getText());
					}else {
						System.out.println("Success mesage for new order is not getting displayed");
						DriverTestcase.logger.log(LogStatus.FAIL, "Success mesage for new order creation is not getting displayed");
					}
					
					
					
					compareText_InViewPage(application ,"Service Identification",ServiceIdvalue,xml);
					
					//compareText_InViewPage(application ,"Remark",RemarkValue,xml);
					//compareText(application, "Remark", "servicepanel_remarksvalue", RemarkValue, xml);
					
					compareText(application, "Email", "servicepanel_email", EmailValue, xml);
					
					compareText_InViewPage(application ,"Phone Contact",PhoneContactValue,xml);
					
					compareText_InViewPage(application ,"Service Type",ServiceType,xml);
					
					compareText_InViewPage(application ,"Performance Reporting",Performanceexpected,xml);
					
					compareText_InViewPage(application ,"Wave Service",Waveexpected,xml);		
			
			
		}
		
		
		public void VerifyEditService(String application, String EditRemarks, String sid, String editemail, String editphonecontact) throws Exception
		{
			ScrolltoElement(application, "orderpanelheader", xml);
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
			click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
			click_commonMethod(application, "Dump", "dump_link", xml);
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
							DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request logged");
						}
						else
						{
							DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change request is not logged");
						}
					}
					catch(StaleElementReferenceException e)
					{
						DriverTestcase.logger.log(LogStatus.FAIL, "No service history to display");
					}
					click_commonMethod(application, "Close", "servicestatus_popupclose", xml);
				}
				else
					DriverTestcase.logger.log(LogStatus.FAIL, "Status link is not working");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Service status change not required");
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
				DriverTestcase.logger.log(LogStatus.INFO, "Synchronize link is not displaying. It is displaying as: " +Synchronization_serviceError);
			}
			
			scrolltoend();
			
			//Devices for service panel
			
			//Devices for service panel headers
			compareText(application, "Device header", "devicesforservice_deviceheader", "Device", xml);
			compareText(application, "Sync Status header", "devicesforservice_syncstatus", "Sync Status", xml);
//			compareText(application, "Fetch Interfaces header", "devicesforservice_fetchinterfacesheader", "Fetch Interfaces", xml);
//			compareText(application, "VistaMart Device header", "devicesforservice_vistamartdeviceheader", "VistaMart Device", xml);
			
			GetText(application, "Device Name", "deviceforservicepanel_devicename");
			GetText(application, "Sync Status", "deviceforservicepanel_syncstatus");
//			GetText(application, "Smarts Status", "deviceforservicepanel_smartsstatus");
//			GetText(application, "Smarts DateTime", "deviceforservicepanel_smartsdatetime");
//			verify_FetchInterfacesValue(application);
//			verify_VistamartDeviceValue(application);
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

		public void verify_FetchInterfacesValue(String application) {
			try {
				if(getwebelement(xml.getlocator("//locators/" + application + "/deviceforservicepanel_fetchinterfaces_status")).isDisplayed())
				{
				GetText(application, "fetchinterfaces Status", "deviceforservicepanel_fetchinterfaces_status");
				GetText(application, "fetchinterfaces DateTime", "deviceforservicepanel_fetchinterfaces_datetime");
				}
			}
			catch (Exception e) {
				DriverTestcase.logger.log(LogStatus.PASS, "No values displaying under Fetch Interfaces column");
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
				DriverTestcase.logger.log(LogStatus.PASS, "No values displaying under VistaMart device column");
			}
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
				// Get Tab name
				String pageTitle=driver.switchTo().window(browserTabs .get(1)).getTitle();
				System.out.println("page title displays as: "+pageTitle);

				//assertEquals(pageTitle, expectedPageName, " on clicking 'Show Infovista link', it got naviagted to "+pageTitle);
				sa.assertEquals(pageTitle, expectedPageName);
				
				Thread.sleep(3000);
				driver.close();
				driver.switchTo().window(browserTabs.get(0)); 

				DriverTestcase.logger.log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
				Thread.sleep(3000);

				DriverTestcase.logger.log(LogStatus.PASS, "show info vista page actual title: "+pageTitle );
				DriverTestcase.logger.log(LogStatus.PASS, "show info vista page expected title: "+ expectedPageName);

			}catch(Exception e) {

				e.printStackTrace();

				Thread.sleep(3000);
				driver.close();
				driver.switchTo().window(browserTabs.get(0));

				DriverTestcase.logger.log(LogStatus.FAIL, expectedPageName + " page is not displaying");

			}
			sa.assertAll();
		}
		
		public void verifyShowInfovistaReport(String application) throws Exception {
			
			showInfovista(application);
			Thread.sleep(2000);
		}

		public void showInfovista(String application) throws Exception {

			ScrolltoElement(application, "orderpanelheader", xml);
			Thread.sleep(3000);
			click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
			Thread.sleep(2000);
			click_commonMethod(application, "Show Infovista Report", "showinfovistareport_link", xml);
			Thread.sleep(6000);

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

				DriverTestcase.logger.log(LogStatus.PASS, "on clicking 'Show Infovista link', it got naviagted to "+ pageTitle + " as expected");
				Thread.sleep(3000);

				DriverTestcase.logger.log(LogStatus.PASS, "show info vista page actual title: "+pageTitle );
				DriverTestcase.logger.log(LogStatus.PASS, "show info vista page expected title: "+ expectedPageName);
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

				DriverTestcase.logger.log(LogStatus.FAIL, expectedPageName + " page is not displaying");

			}
			sa.assertAll();
		}
		
		public void VerifyManagementPanel(String application ,String Performanceexpected, String Waveexpected, String InterfaceSpeed, 
				String TypeOfService  ) throws InterruptedException, DocumentException, IOException
		{
			compareText_InViewPage(application ,"Performance Reporting",Performanceexpected,xml);
			
			compareText_InViewPage(application ,"Wave Service",Waveexpected,xml);
			
			if(Waveexpected.equals("Yes")) 
				
			{
			compareText_InViewPage(application ,"Interface Speed",InterfaceSpeed,xml);	
			
			compareText_InViewPage(application ,"Type Of Service",TypeOfService,xml);	
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
//			compareText(application, "Order Header", "orderpanelheader", "Order", xml);
//			Log.info("Navigated to order panel in view service page");
//			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

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
//			compareText(application, "Order Header", "orderpanelheader", "Order", xml);
//			Log.info("Navigated to order panel in view service page");
//			DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

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
//				compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
//				Log.info("Navigated to order panel in view service page");
//				DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");

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
//				compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
//				Log.info("Navigated to order panel in view service page");
//				DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to order panel in view service page");
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
//				compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
//				Log.info("Navigated to order panel in view service page");

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
//				compareText(application, "Order Panel Header", "orderpanelheader", "Order", xml);
//				Log.info("Navigated to order panel in view service page");
//				DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to order panel in view service page");
				compareText(application, "Order Number", "ordernumbervalue", changeorderno, xml);
				compareText(application, "RFI Voice Line Number", "ordervoicelinenumbervalue", changevoicelineno, xml);
				Log.info("------ Change Order is successful ------");
			}
		}

		public void navigateToAddNewDevicepage(String application) throws InterruptedException, DocumentException {
			ScrolltoElement(application, "portalaccess_header", xml);
			compareText(application, "Device", "deviceheader", "Device", xml);
			click_commonMethod(application, "Add Device", "AddDevice", xml);
			compareText(application, "Add Device Header", "adddevice_header", "Add Device", xml);
			click_commonMethod(application, "Add New Device toggle", "addnewdevice_togglebutton", xml);
		}
		
		public void addNewPEDevice(String application, String name, String devicetype, 
				String vendormodel, String Country, String managementaddress, String existingcity, 
				String existingcityvalue, String existingsite,
				String existingsitevalue, String existingpremise, 
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
				//javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
				addtextFields_commonMethod(application, "Management Address", "managementaddresstextbox", managementaddress, xml);

				//snmpro
				String SnmproValue= getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Snmpro value is displayed as: " +SnmproValue);
				
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
			//compareText(application, "Add Device success msg", "successmsg", "Device successfully created", xml);
			verifysuccessmessage(application, "Device successfully created.");
		}

		public void Clickon_addToggleButton(String application, String xpath) throws InterruptedException, DocumentException {

			//Add Toggle button
			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")));
			Thread.sleep(5000);
		}
		
		public void verifyViewpage_Devicedetails(String application, String sid, String name, String vendormodel, String Country, String managementaddress, String existingcity, 
				String existingcityvalue, String existingsite, 
				String existingsitevalue, String existingpremise, 
				String existingpremisevalue, String newcity,String cityname,String Citycode,
				String sitename, String sitecode,  String premisename,  String premisecode, 
				String newsite, String NewPremise) throws InterruptedException, IOException, DocumentException {


			//Device Name
			//compareText(application, "Name", "viewpage_devicename", name, xml);
			GetText(application, "Name", "viewpage_devicename");

			//Vendor/model
			compareText(application, "Vendor/Model", "viewpage_vendormodel", vendormodel, xml);

			//Management Address
			compareText(application, "Management Address", "viewpage_managementaddress", managementaddress, xml);

			//snmpro
			compareText_InViewPage(application, "Snmpro", "incc", xml);
			
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
			
			clickOnBreadCrumb(application, sid);
		}

		public void verifyEditDevice(String application, String editDevicename, String editVendorModel,
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
					compareText(application, "Edit Device header", "editdeviceheader", "Edit Device", xml);

					//edit device fields

					//name
					String DeviceNameValue= getwebelement(xml.getlocator("//locators/" + application + "/devicename_textfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Device Name value is displayed as: " +DeviceNameValue);
					
					//vendormodel
					addDropdownValues_commonMethod(application, "Vendor/Model", "vendormodelinput", editVendorModel, xml);

					//Management address
					//javascriptexecutor(getwebelement(xml.getlocator("//locators/" + application + "/managementaddresstextbox")));
					edittextFields_commonMethod(application, "Management Address", "managementaddresstextbox", editManagementAddress, xml);

					//snmpro
					String SnmproValue= getwebelement(xml.getlocator("//locators/" + application + "/snmprotextfield")).getAttribute("value");
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Snmpro value is displayed as: " +SnmproValue);
					
					//select country
					scrolltoend();
					Thread.sleep(2000);
					addDropdownValues_commonMethod(application, "Country", "countryinput", editCountry, xml);

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
			click_commonMethod(application, "OK", "OKbutton", xml);
			//compareText(application, "Edit Device success msg", "successmsg", "Device successfully updated", xml);
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

		public void AddExistingDevice(String application, String existingDeviceName) throws InterruptedException, DocumentException {
			
		ScrolltoElement(application, "portalaccess_header", xml);
		compareText(application, "Device", "deviceheader", "Device", xml);
		click_commonMethod(application, "Add Device", "AddDevice", xml);
		compareText(application, "Add Device Header", "adddevice_header", "Add Device", xml);
		scrollToTop();
		addDropdownValues_commonMethod(application, "Name", "name_dropdown", existingDeviceName, xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "OK", "okbutton", xml);
		//compareText(application, "Add Device success msg", "successmsg", "Device successfully created", xml);
		verifysuccessmessage(application, "Device successfully created.");
		scrolltoend();
		click_commonMethod(application, "Back", "Backbutton", xml);
		
	}
		
		public void selectInterfacelinkforDevice(String application, String existingDeviceName) throws InterruptedException, DocumentException {
			
			Thread.sleep(2000);
			ScrolltoElement(application, "portalaccess_header", xml);
			Thread.sleep(2000);
			
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
						Thread.sleep(5000);
					}
				}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
			}
						
		}

public void SelectInterface(String application) throws InterruptedException, DocumentException {
			
			ScrolltoElement(application, "viewdeviceheader", xml);
			compareText(application, "Interfaces Header", "interfaceheader", "Interfaces", xml);
			WebElement InterfacesGridCheck= getwebelement("(//div[text()='Interfaces']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//div)[1]");
			String InterfacesGrid= InterfacesGridCheck.getAttribute("style");
			WebElement AddedInterfaces= getwebelement("(//div[text()='Interfaces']/parent::div/following-sibling::div//div[@ref='eBodyViewport']//span[contains(@class,'unchecked')])[1]");
			if(!InterfacesGrid.contains("height: 1px"))
			{
				Clickon(AddedInterfaces);
				Thread.sleep(1000);
				click_commonMethod(application, "Action dropdown", "interfacespanel_actiondropdown", xml);
				click_commonMethod(application, "OK", "interfacespanel_OK_link", xml);
				Thread.sleep(5000);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "No Interfaces available to select");
				click_commonMethod(application, "Back", "Backbutton", xml);
			}
				scrolltoend();
				click_commonMethod(application, "Show Interfaces", "showinterfaces_link", xml);
				
}

public void deleteDevice(String application, String name) throws InterruptedException, DocumentException {

		ScrolltoElement(application, "portalaccess_header", xml);
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
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
				}
				break;
			}
			
		}
		
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
		}
}
	
public void deleteExistingDevice(String application, String existingDeviceName) throws InterruptedException, DocumentException {

	ScrolltoElement(application, "portalaccess_header", xml);
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
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
			}
			break;
		}
		
	}
	
	}
	else
	{
		DriverTestcase.logger.log(LogStatus.FAIL, "No Device added in grid");
	}
}

public void deleteService(String application) throws InterruptedException, DocumentException	{

	//Delete Service
	ScrolltoElement(application, "orderpanelheader", xml);
	click_commonMethod(application, "Action dropdown", "serviceactiondropdown", xml);
	click_commonMethod(application, "Delete", "delete", xml);
	Thread.sleep(2000);
	
	Alert alert = driver.switchTo().alert();       
  
  // Capturing alert message.    
    String alertMessage= driver.switchTo().alert().getText();
    if(alertMessage.isEmpty()) {
       DriverTestcase.logger.log(LogStatus.FAIL, "No message displays");
          System.out.println("No Message displays"); 
    }else {
       DriverTestcase.logger.log(LogStatus.PASS, "Alert message displays as: "+alertMessage);
          System.out.println("text message for alert displays as: "+alertMessage);
    }
  
  try {  
    alert.accept();
    Thread.sleep(2000);
  }catch(Exception e) {
     e.printStackTrace();
  } 
	
	
//	WebElement DeleteAlertPopup= getwebelement(xml.getlocator("//locators/" + application + "/delete_alertpopup"));
//	if(DeleteAlertPopup.isDisplayed())
//	{
//		click_commonMethod(application, "Delete", "deletebutton", xml);
//		Thread.sleep(2000);
//		//compareText(application, "Service Delete success msg", "deletesuccessmsg", "Service successfully deleted", xml);
//		verifysuccessmessage(application, "Service successfully deleted");
//	}
//	else
//	{
//		Log.info("Delete alert popup is not displayed");
//		DriverTestcase.logger.log(LogStatus.FAIL, "Step : Delete alert popup is not displayed");
//	}
	
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
 					
 					DriverTestcase.logger.log(LogStatus.PASS,"Message is verified. It is displaying as: "+alrtmsg);
 					System.out.println("Message is verified. It is displaying as: "+alrtmsg);
 					
 				}else {
 					
 					DriverTestcase.logger.log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
 					System.out.println("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
 				}
 				
 			}else {
 				DriverTestcase.logger.log(LogStatus.FAIL, " Success Message is not displaying");
 				System.out.println(" Success Message is not displaying");
 			}
 			
 		}catch(Exception e) {
 			Log.info("failure in fetching success message");
 			DriverTestcase.logger.log(LogStatus.FAIL, expected+ " Message is not displaying");
 			System.out.println(expected+ " message is not getting dislpayed");
 		}

 	}
    
	}
