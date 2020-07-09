package com.saksoft.qa.scripthelpers;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.driverlibrary.Log;
import com.saksoft.qa.driverlibrary.XMLReader;

public class manageNetwork_Lanlink extends DriverHelper{



	public manageNetwork_Lanlink(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/ManageNetwork._createAccessCOreDevice.xml");

	
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
	
	
	public static String InterfaceAddress;
	public void verifyFetchInterface(String application, String devicename, String Inservice_status, String Inmaintenance_status, String vendormodel, String managementaddress, 
			String snmpro, String country, String interfacename) throws InterruptedException, DocumentException, IOException {
		
		
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
//		compareText(application, "Smarts column header", "synchronization_smartscolumn", "Smarts", xml);
		compareText(application, "Fetch Interfaces column header", "synchronization_FetchInterfacescolumn", "Fetch Interfaces", xml);
//		compareText(application, "VistaMart Device column header", "synchronization_vistamartdevice", "VistaMart Device", xml);
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
			compareText(application, "Snmpro value", "statuspage_snmprovalue", snmpro, xml);
			compareText(application, "Country", "statuspage_countryvalue", country, xml);
			GetText(application, "City", "statuspage_cityvalue");
			GetText(application, "Site", "statuspage_sitevalue");
			GetText(application, "Premise", "statuspage_premisevalue");
		
		compareText(application, "Status header", "Statuspage_statusheader", "Status", xml);
		compareText(application, "Current Status field header", "statuspage_currentstatusfieldheader", "Current Status", xml);
		compareText(application, "New Status field header", "statuspage_newstatusfieldheader", "New Status", xml);
		compareText(application, "Current Status", "statuspage_currentstatusvalue", Inservice_status, xml);
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
					DriverTestcase.logger.log(LogStatus.FAIL, "No interfaces added");
					
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
		WebElement InterfaceAddressRowValue= driver.findElement(By.xpath("(//div[@role='gridcell'][@col-id='address'])[1]"));
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
								WebElement InterfaceAddressRowValue1= driver.findElement(By.xpath("(//div[@role='gridcell'][@col-id='address'])[1]"));
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
		compareText(application, "Current Status", "interface_statuspage_currentstatusvalue", Inservice_status, xml);
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
		
		
//		//verify smarts value
//		GetText(application, "Smarts", "synchronization_smartsvalue");
//		//verify smarts date time 
//		try {
//			String GMTValue;
//			String Smartsvalue= getwebelement(xml.getlocator("//locators/" + application + "/smarts_datetimevalue")).getText();
//			String SmartsDateTimevalue= "";
//			if (Smartsvalue.length() > 20) 
//			{
//				SmartsDateTimevalue = Smartsvalue.substring(Smartsvalue.length() - 20);
//				System.out.println("last 20 characters:"+SmartsDateTimevalue);
//			} 
//			else 
//			{
//				SmartsDateTimevalue = Smartsvalue;
//			}
//
//			DriverTestcase.logger.log(LogStatus.PASS, "Smarts date value is displayed as: "+SmartsDateTimevalue);
//			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");
//			if (SmartsDateTimevalue.length() > 3) 
//			{
//			    GMTValue = SmartsDateTimevalue.substring(SmartsDateTimevalue.length() - 3);
//			} 
//			else
//			{
//				GMTValue = SmartsDateTimevalue;
//			}
//			assertEquals(GMTValue, "GMT");
//			
//			}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
		
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
		
//		//verify vistamart device value
//		GetText(application, "VistaMart Device", "synchronization_vistamartdevicevalue");
//		//verify vistamart device date time
//		try {
//			String GMTValue;
//			String VistaMartDevicevalue= getwebelement(xml.getlocator("//locators/" + application + "/vistamartdevice_datetime")).getText();
//			String VistaMartDevice_DateTimevalue= "";
//			if (VistaMartDevicevalue.length() > 20) 
//			{
//				VistaMartDevice_DateTimevalue = VistaMartDevicevalue.substring(VistaMartDevicevalue.length() - 20);
//				System.out.println("last 20 characters:"+VistaMartDevice_DateTimevalue);
//			} 
//			else 
//			{
//				VistaMartDevice_DateTimevalue = VistaMartDevicevalue;
//			}
//			
//			DriverTestcase.logger.log(LogStatus.PASS, "Vistamart Device date value is displayed as: "+VistaMartDevice_DateTimevalue);
//			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");
//			if (VistaMartDevice_DateTimevalue.length() > 3) 
//			{
//			    GMTValue = VistaMartDevice_DateTimevalue.substring(VistaMartDevice_DateTimevalue.length() - 3);
//			} 
//			else
//			{
//				GMTValue = VistaMartDevice_DateTimevalue;
//			}
//			assertEquals(GMTValue, "GMT");
//			
//			}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
		
		//verify synchronize link
		Thread.sleep(2000);
		click_commonMethod(application, "Synchronize", "synchronization_synchronizelink", xml);
		Thread.sleep(1000);
		scrollToTop();
		Thread.sleep(2000);
		//isElementPresent("//locators/" + application + "/Sync_successmsg");
		compareText(application, "Synchronize Success Msg", "Sync_successmsg", "Sync started successfully. Please check the sync status of this device.", xml);
		Thread.sleep(2000);
		
//		//verify device name link in status panel
//		click(application, "Device", "status_devicevalue", xml);
//		Thread.sleep(2000);
//		compareText(application, "Search for Device header", "searchdevice_header", "Search For Device", xml);
//		DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to 'Search for device' page");
//		driver.navigate().back();
//		Thread.sleep(1000);
//		
//		//verify device name link in synchronization panel
//		click(application, "Device", "synchronization_devicevalue", xml);
//		Thread.sleep(2000);
//		compareText(application, "Search for Device header", "searchdevice_header", "Search For Device", xml);
//		DriverTestcase.logger.log(LogStatus.PASS, "Step: Navigated to 'Search for device' page");
//		driver.navigate().back();
//		Thread.sleep(2000);
//		driver.navigate().back();
//		Thread.sleep(2000);
//		scrolltoend();
		click_commonMethod(application, "Back", "viewpage_backbutton", xml);
		Thread.sleep(2000);
//
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

//Repository(xml) file code:
	

	
}
