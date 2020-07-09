package com.saksoft.qa.scripthelpers;


import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.saksoft.qa.driverlibrary.DriverHelper;
import com.saksoft.qa.driverlibrary.DriverTestcase;
import com.saksoft.qa.driverlibrary.Log;
import com.saksoft.qa.driverlibrary.XMLReader;

public class APT_MSPLatencyHelper extends DriverHelper {

	public APT_MSPLatencyHelper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	WebElement el;

	SoftAssert sa = new SoftAssert();

	static XMLReader xml = new XMLReader("src/com/saksoft/qa/pagerepository/APT_MSPLatency.xml");

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

	public void VerifyMSPLatency(String application, String city, String deviceip, String vlanid, String circuitname, String thresholdfilename) throws InterruptedException, DocumentException, IOException {

		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/managecoltnetworklink")));
		Thread.sleep(2000);
		System.out.println("Mouse hovered on Manage Colt's network");
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on 'Manage Colt's network' menu item");

		click_commonMethod(application, "MSP Latency Grid", "msplatencylink", xml);
		Thread.sleep(2000);
		compareText(application, "MSP Latency Grid Endpoint header", "msplatency_header", "MSP Latency Grid EndPoint", xml);

		//verify links in msp latency grid
		isDisplayed(application, "addmsplatency_link", "Add MSP Latency Grid EndPoint");
		isDisplayed(application, "generatecircuits_link", "Generate Circuits");
		isDisplayed(application, "viewgeneratedcircuits_link", "View Generated Circuits");
		isDisplayed(application, "viewremovedcircuithistory_link", "View Removed Circuit History");
		isDisplayed(application, "uploadthresholdvalues_link", "Upload Threshold Values");

		//verify MSP Latency column names
		compareText(application, "City Code", "citycode_column", "City Code", xml);
		compareText(application, "City Name", "cityname_column", "City Name", xml);
		compareText(application, "Device Name", "devicename_column", "Device Name", xml);
		compareText(application, "Device IP", "deviceip_column", "Device IP", xml);
		
		WebElement aspendeviceidvalue= getwebelement(xml.getlocator("//locators/" + application + "/aspendeviceid_cellvalue"));
		Clickon(aspendeviceidvalue);
		aspendeviceidvalue.sendKeys(Keys.TAB);
		WebElement aspenportnamevalue= getwebelement(xml.getlocator("//locators/" + application + "/aspenportname_cellvalue"));
		Clickon(aspenportnamevalue);
		aspenportnamevalue.sendKeys(Keys.TAB);
		WebElement aspenportidvalue= getwebelement(xml.getlocator("//locators/" + application + "/aspenportid_cellvalue"));
		Clickon(aspenportidvalue);
		aspenportidvalue.sendKeys(Keys.TAB);
		
		compareText(application, "Aspen Device ID", "aspendeviceid_column", "Aspen Device ID", xml);
		compareText(application, "Aspen Port Name", "aspenportname_column", "Aspen Port Name", xml);
		compareText(application, "Aspen Port ID", "aspenportid_column", "Aspen Port ID", xml);
		compareText(application, "VLAN ID", "vlanid_column", "VLAN ID", xml);
		WebElement VLANIDvalue= getwebelement(xml.getlocator("//locators/" + application + "/vlanid_cellvalue"));
		Clickon(VLANIDvalue);
		VLANIDvalue.sendKeys(Keys.TAB);
		
		//Verify Add MSP Latency grid

		//Cancel add msp
		click_commonMethod(application, "Add MSP Latency Grid EndPoint", "addmsplatency_link", xml);
		Thread.sleep(2000);
		compareText(application, "Add MSP Latency Grid EndPoints", "msplatency_header", "Add MSP Latency Grid EndPoints", xml);
		Thread.sleep(2000);
		scrolltoend();
		click_commonMethod(application, "Cancel", "cancelbutton", xml);
		Thread.sleep(2000);
		if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("MSP Latency Grid EndPoint"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to MSP Latency Grid EndPoint page");
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to MSP Latency Grid EndPoint page");
		}

		//Add MSP
		click_commonMethod(application, "Add MSP Latency Grid EndPoint", "addmsplatency_link", xml);
		Thread.sleep(2000);
		compareText(application, "Add MSP Latency Grid EndPoints", "msplatency_header", "Add MSP Latency Grid EndPoints", xml);
		Thread.sleep(2000);

		//Verify warning messages
		scrolltoend();
		click_commonMethod(application, "Ok", "okbutton", xml);
		warningMessage_commonMethod(application, "warningmsg_city", "City", xml);
		warningMessage_commonMethod(application, "warningmsg_deviceip", "Device IP", xml);
		warningMessage_commonMethod(application, "warningmsg_Aspenportname", "Aspen Port Name", xml);
		warningMessage_commonMethod(application, "warningmsg_devicename", "Device Name", xml);
		warningMessage_commonMethod(application, "warningmsg_vlanid", "VLANID", xml);

		addtextFields_commonMethod(application, "City", "city_textfield", city, xml);
		addDropdownValues_commonMethod(application, "Available", "available_dropdown", city, xml);
		String CityValue= GetText(application, "Available field dropdown value", "available_dropdownvalue");
		DriverTestcase.logger.log(LogStatus.PASS, "Step: Available field dropdown value is displayed as:" +CityValue);
		addtextFields_commonMethod(application, "Device IP", "deviceip_textfield", deviceip, xml);
		click_commonMethod(application, "Search In Aspen", "searchinaspen_button", xml);
		Thread.sleep(6000);
		String AspenPortNamevalueCheck= getwebelement(xml.getlocator("//locators/" + application + "/aspenportnamevalue")).getAttribute("value");
		String AspenPortNamevalue= GetText(application, "Aspen Port Name", "aspenportnamevalue");
//		if(AspenPortNamevalueCheck.equalsIgnoreCase("null"))
//		{
//			Assert.assertEquals(AspenPortNamevalueCheck, AspenPortNamevalue);
//		}
		
		DriverTestcase.logger.log(LogStatus.PASS, "Step: Aspen Port Name field value is displayed as:" +AspenPortNamevalue);
		String DeviceNamevalue= getwebelement(xml.getlocator("//locators/" + application + "/devicenamevalue")).getAttribute("value");
		DriverTestcase.logger.log(LogStatus.PASS, "Step: Device Name field value is displayed as:" +DeviceNamevalue);
		addtextFields_commonMethod(application, "VLANID", "vlanid_textfield", vlanid, xml);
		click_commonMethod(application, "Ok", "okbutton", xml);
		Thread.sleep(2000);
		compareText(application, "Add MSP Latency Success Message", "successmsg", "Latency Grid Points successfully created and Latency Grid Circuits successfully generated.", xml);
		
		selectAddedMSP(application, vlanid);

		//verify generate circuits
		//cancel generate circuits
		click_commonMethod(application, "Generate Circuits", "generatecircuits_link", xml);
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/alertpopup")).isDisplayed())
		{
			Thread.sleep(1000);
			click_commonMethod(application, "Cancel", "generatecircuits_cancel", xml);
			Thread.sleep(2000);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Generate Circuits popup is not displayed");
		}

		//generate circuits
		click_commonMethod(application, "Generate Circuits", "generatecircuits_link", xml);
		Thread.sleep(2000);
		if(getwebelement(xml.getlocator("//locators/" + application + "/alertpopup")).isDisplayed())
		{
			Thread.sleep(1000);
			click_commonMethod(application, "Ok", "generatecircuits_ok", xml);
			Thread.sleep(5000);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Generate Circuits popup is not displayed");
		}

		String Generatecircuits_successmsg= getwebelement(xml.getlocator("//locators/" + application + "/successmsg")).getText();
		DriverTestcase.logger.log(LogStatus.FAIL, "Generate Circuits message is displayed as: " +Generatecircuits_successmsg );


		//View generated circuits
		//verify back button
		click_commonMethod(application, "View Generated Circuits", "viewgeneratedcircuits_link", xml);
		Thread.sleep(2000);
		click_commonMethod(application, "Back", "backbutton", xml);
		Thread.sleep(2000);
		if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("MSP Latency Grid EndPoint"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to MSP Latency Grid EndPoint page");
			Thread.sleep(1000);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to MSP Latency Grid EndPoint page");
		}

		//verify view generated circuits
		click_commonMethod(application, "View Generated Circuits", "viewgeneratedcircuits_link", xml);
		Thread.sleep(3000);
		addtextFields_commonMethod(application, "Circuit Name", "circuitname_textfield", circuitname, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Search", "searchbutton", xml);
		Thread.sleep(3000);
		scrolltoend();
		if(getwebelement(xml.getlocator("//locators/" + application + "/generatedcircuit")).isDisplayed())
		{
			//Verify columns in generated circuits table
//			compareText(application, "Connection Name Column", "connectionname_column", "Connection Name", xml);
//			compareText(application, "Actions Column", "actions_column", "Actions", xml);
//			compareText(application, "Threshold Column", "threshold_column", "Threshold(ms)", xml);
			compareText(application, "Device Name", "generatedcircuit_devicename_column", "Device Name", xml);
			compareText(application, "Device IP", "generatedcircuit_deviceip_column", "Device IP", xml);
			compareText(application, "Aspen Port Name", "generatedcircuit_aspenportname_column", "Aspen Port Name", xml);
			compareText(application, "Provisioned VLAN ID", "generatedcircuit_provisionedvlanid_column", "Provisioned VLAN ID", xml);

			compareText(application, "Circuit Name", "existingcircuitname", circuitname, xml);
			String AspenSyncStatusvalue= getwebelement(xml.getlocator("//locators/" + application + "/aspensyncstatus")).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Aspen Sync Status value is displayed as: "+AspenSyncStatusvalue);
			String VistaMartSyncStatusvalue= getwebelement(xml.getlocator("//locators/" + application + "/vistamartsyncstatus")).getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : VistaMart Sync Status value is displayed as: "+VistaMartSyncStatusvalue);
			String Thresholdvalue= getwebelement("(//div[@role='gridcell'])[4]").getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Threshold value is displayed as: "+Thresholdvalue);

			//verify view circuit history page
			click_commonMethod(application, "View Circuit History", "viewcircuithistory_link", xml);
			Thread.sleep(2000);
			List<String> ch = new ArrayList<String>();
			compareText(application, "Circuit History Header", "msplatency_header", "Circuit History", xml);
			if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("Circuit History"))
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to Circuit History page");
				Thread.sleep(1000);

				if((getwebelement(xml.getlocator("//locators/" + application + "/circuithistorydata"))).isDisplayed())
				{
					//List<WebElement> CircuitHistoryvalues= driver.findElements(By.xpath("//div[@class='row']//ul//li//a"));
					List<WebElement> CircuitHistoryvalues= getwebelements("//div[@class='row']//ul//li//a");
					int CircuitHistoryvalues_count= CircuitHistoryvalues.size();
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Circuit History data is displayed as: ");
					for(int i=0; i<CircuitHistoryvalues_count; i++)
					{
						String CircuitHistoryListvalue= CircuitHistoryvalues.get(i).getText();
						String GMTValue;
						try {
							SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd mm:ss");
							Date javadate= sdf.parse(CircuitHistoryListvalue);
							if (CircuitHistoryListvalue.length() > 3) 
							{
							    GMTValue = CircuitHistoryListvalue.substring(CircuitHistoryListvalue.length() - 3);
							} 
							else
							{
								GMTValue = CircuitHistoryListvalue;
							}
							String circuithistorydata= sdf.format(javadate)+ " " +GMTValue;
							ch.add(circuithistorydata);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					DriverTestcase.logger.log(LogStatus.PASS," " +ch);
					DriverTestcase.logger.log(LogStatus.PASS, "Step : Circuit History values are displaying in proper format as expected");
				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Circuit History data is empty");
				}
				Thread.sleep(2000);
				click_commonMethod(application, "Back", "backbutton", xml);
				Thread.sleep(3000);
				
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to Circuit History page");
			}

			//verify fetch info from aspen page
			addtextFields_commonMethod(application, "Circuit Name", "circuitname_textfield", circuitname, xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Search", "searchbutton", xml);
			Thread.sleep(3000);
			scrolltoend();
			if(getwebelement(xml.getlocator("//locators/" + application + "/generatedcircuit")).isDisplayed())
			{
				click_commonMethod(application, "Fetch Info from Aspen", "fetchinfofromaspen_link", xml);
				Thread.sleep(2000);
				if(getwebelement(xml.getlocator("//locators/" + application + "/alertpopup")).isDisplayed())
				{
					click_commonMethod(application, "Ok", "aspen_okbutton", xml);
					Thread.sleep(2000);
					scrollToTop();
					String FetchInfofromaspen_SuccessMessage= getwebelement(xml.getlocator("//locators/" + application + "/successmsg")).getText();
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Fetch Info from Aspen message is displayed as: "+FetchInfofromaspen_SuccessMessage);

				}
				else
				{
					DriverTestcase.logger.log(LogStatus.FAIL, "Step : Fetch Info from Aspen popup is not displayed");
				}
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing circuit to display");
			}

			//verify create selected circuits in aspen
			scrolltoend();
			WebElement SelectExistingCircuit= getwebelement("//b[contains(text(),'"+circuitname+"')]/ancestor::div[@role='gridcell']/preceding-sibling::div//span[@class='ag-selection-checkbox']//span[2]");
			Clickon(SelectExistingCircuit);
			Thread.sleep(2000);
			click_commonMethod(application, "Create Selected Circuits In Aspen", "createselectedcircuitsinaspen_link", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/alertpopup")).isDisplayed())
			{
				click_commonMethod(application, "Ok", "aspen_okbutton", xml);
				Thread.sleep(2000);
				scrollToTop();
				compareText(application, "'Create Selected Circuits In Aspen' success message", "successmsg", "Apply Latency Grid Circuits has been started successfully.", xml);

			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Create Selected Circuits In Aspen popup is not displayed");
			}

			//verify synchronize selected circuits to vistamart
			Thread.sleep(2000);
			click_commonMethod(application, "Synchronize Selected Circuits To VistaMart", "synchronizeselectedcircuitstovistamart_link", xml);
			if(getwebelement(xml.getlocator("//locators/" + application + "/alertpopup")).isDisplayed())
			{
				click_commonMethod(application, "Ok", "aspen_okbutton", xml);
				Thread.sleep(3000);
				scrollToTop();
				compareText(application, "'Synchronize Selected Circuits To VistaMart' success message", "successmsg", "Vistamart Sync for selected Circuits has been started successfully.", xml);

			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Synchronize Selected Circuits To VistaMart popup is not displayed");
			}

			Thread.sleep(1000);
			click_commonMethod(application, "Back", "backbutton", xml);
			Thread.sleep(3000);

			if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("MSP Latency Grid EndPoint"))
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to MSP Latency Grid EndPoint page");
				Thread.sleep(1000);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to MSP Latency Grid EndPoint page");
			}

		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing circuit to display");
		}
		//verify view removed circuit history
		click_commonMethod(application, "View Removed Circuit History", "viewremovedcircuithistory_link", xml);
		Thread.sleep(2000);
		List<String> rc = new ArrayList<String>();
		if(getwebelement(xml.getlocator("//locators/" + application + "/alertpopup")).isDisplayed())
		{
			compareText(application, "Removed Circuit History", "removedcircuithistory_header", "Removed Circuit History", xml);
			
			if((getwebelement(xml.getlocator("//locators/" + application + "/removedcircuithistorydata"))).isDisplayed())
			{
				//List<WebElement> RemovedCircuitHistoryvalues= driver.findElements(By.xpath("//div[@class='row']//ul//li//a"));
				List<WebElement> RemovedCircuitHistoryvalues= getwebelements("//div[@class='row']//ul//li//a");
				int RemovedCircuitHistoryvalues_count= RemovedCircuitHistoryvalues.size();
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Removed Circuit History data is displayed as: ");
				for(int i=0; i<RemovedCircuitHistoryvalues_count; i++)
				{
					String RemovedCircuitHistoryListvalue= RemovedCircuitHistoryvalues.get(i).getText();
					try {
						rc.add(RemovedCircuitHistoryListvalue);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				DriverTestcase.logger.log(LogStatus.PASS," " +rc);
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Removed Circuit History values are displaying as expected");
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Removed Circuit History data is empty");
			}
			Thread.sleep(2000);
			click_commonMethod(application, "Close", "close_removedcircuithistorypopup", xml);
			Thread.sleep(3000);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Removed Circuit History popup is not displayed");
		}

		if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("MSP Latency Grid EndPoint"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to MSP Latency Grid EndPoint page");
			Thread.sleep(1000);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to MSP Latency Grid EndPoint page");
		}
		

		//Verify upload threshold
		//Cancel upload
		click_commonMethod(application, "Upload Threshold Values", "uploadthresholdvalues_link", xml);
		Thread.sleep(2000);
		if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("Upload Threshold Values"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to Upload Threshold values page");
			Thread.sleep(1000);
			click_commonMethod(application, "Cancel", "cancel_uploadthreshold", xml);
			if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("MSP Latency Grid EndPoint"))
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to MSP Latency Grid EndPoint page");
				Thread.sleep(1000);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to MSP Latency Grid EndPoint page");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to Upload Threshold values page");
		}


		//upload threshold
		click_commonMethod(application, "Upload Threshold Values", "uploadthresholdvalues_link", xml);
		Thread.sleep(2000);
		if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("Upload Threshold Values"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to Upload Threshold values page");
			Thread.sleep(1000);
			//click_commonMethod(application, "Choose File", "choosefile_uploadthreshold", xml);
			//Thread.sleep(1000);
			WebElement Choosefile_button= getwebelement(xml.getlocator("//locators/" + application + "/choosefile_uploadthreshold"));
			Choosefile_button.sendKeys(thresholdfilename);
			
			Thread.sleep(1000);
			click_commonMethod(application, "Ok", "ok_uploadthreshold", xml);
			Thread.sleep(4000);
			compareText(application, "'Upload Threshold Values' success message", "successmsg", "Threshold values updated successfully.", xml);
			Thread.sleep(1000);
			click_commonMethod(application, "Cancel", "cancel_uploadthreshold", xml);
			Thread.sleep(2000);
			if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("MSP Latency Grid EndPoint"))
			{
				DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to MSP Latency Grid EndPoint page");
				Thread.sleep(1000);
			}
			else
			{
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to MSP Latency Grid EndPoint page");
			}
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to Upload Threshold values page");
		}

		//Verify updated threshold value
		click_commonMethod(application, "View Generated Circuits", "viewgeneratedcircuits_link", xml);
		Thread.sleep(2000);
		addtextFields_commonMethod(application, "Circuit Name", "circuitname_textfield", circuitname, xml);
		Thread.sleep(1000);
		click_commonMethod(application, "Search", "searchbutton", xml);
		Thread.sleep(3000);
		scrolltoend();
//		if(getwebelement(xml.getlocator("//locators/" + application + "/generatedcircuit")).isDisplayed())
//		{
			String Thresholdvalue1= getwebelement("(//div[@role='gridcell'])[4]").getText();
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Threshold value is displayed as: "+Thresholdvalue1);
//		}
//		else
//		{
//			DriverTestcase.logger.log(LogStatus.FAIL, "Step : No existing circuit to display");
//		}
		Thread.sleep(2000);
		click_commonMethod(application, "Back", "backbutton", xml);
		Thread.sleep(3000);
		if((getwebelement(xml.getlocator("//locators/" + application + "/msplatency_header"))).getText().equals("MSP Latency Grid EndPoint"))
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Step : Navigated to MSP Latency Grid EndPoint page");
			Thread.sleep(1000);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.FAIL, "Step : Not navigated to MSP Latency Grid EndPoint page");
		}


		//Delete Added MSP
		selectAddedMSP(application, vlanid);
		String VLANIDRowNumber= getwebelement("//div[@ref='eBody']//div[@role='row']//div[text()='"+vlanid+"']/parent::div").getAttribute("row-id");
		WebElement SelectAddedMSP= getwebelement("//div[@ref='eBody']//div[@role='row'][@row-id='"+VLANIDRowNumber+"']//div//input[@type='radio']");
		Clickon(SelectAddedMSP);
		Thread.sleep(3000);
		click_commonMethod(application, "Action dropdown", "msp_actiondropdown", xml);
		click_commonMethod(application, "Delete", "mspdelete_link", xml);
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
		compareText(application, "MSP Latency Delete success msg", "deletesuccessmsg", "Latency Grid Points successfully deleted.", xml);
	
	}


	public void selectAddedMSP(String application, String vlanid) throws InterruptedException, DocumentException, IOException {

		int TotalPages;
		String TextKeyword = getwebelement("//div[@class='app-main']//div//span[@ref='lbTotal']").getText();
		TotalPages = Integer.parseInt(TextKeyword);
		System.out.println("Total number of pages in table is: " + TotalPages);

		if (TotalPages != 0) {

			WebElement aspendeviceidvalue= getwebelement(xml.getlocator("//locators/" + application + "/aspendeviceid_cellvalue"));
			Clickon(aspendeviceidvalue);
			aspendeviceidvalue.sendKeys(Keys.TAB);
			WebElement aspenportnamevalue= getwebelement(xml.getlocator("//locators/" + application + "/aspenportname_cellvalue"));
			Clickon(aspenportnamevalue);
			aspenportnamevalue.sendKeys(Keys.TAB);
			WebElement aspenportidvalue= getwebelement(xml.getlocator("//locators/" + application + "/aspenportid_cellvalue"));
			Clickon(aspenportidvalue);
			aspenportidvalue.sendKeys(Keys.TAB);
			
			outerloop:
			for (int k = 1; k <= TotalPages; k++) {

				List<WebElement> results = getwebelements("//div[@ref='eBody']//div[@role='row']//div[@col-id='vlanId']");

				int numofrows = results.size();
				System.out.println("no of results: " + numofrows);

				if ((numofrows == 0)) {

					Clickon(getwebelement("//div[@class='app-main']//div//button[text()='Next']"));
					Thread.sleep(3000);
				}
				else {
					
				// Current page
				String CurrentPage = getwebelement("//div[@class='app-main']//div//span[@ref='lbCurrent']").getText();
				int Current_page = Integer.parseInt(CurrentPage);
				System.out.println("The current page is: " + Current_page);

				assertEquals(k, Current_page);

				System.out.println("Currently we are in page number: " + Current_page);
				System.out.println("VLANID's displaying as: ");
					for (int i = 0; i < numofrows; i++) {
						try {

							String AddedVLANID = results.get(i).getText();
							System.out.println(AddedVLANID);
							if (AddedVLANID.equalsIgnoreCase(vlanid)) {

								DriverTestcase.logger.log(LogStatus.PASS, "MSP Latency Grid is added successfully");
								Thread.sleep(2000);
								WebElement VLANIDvalue= getwebelement("//div[@ref='eBody']//div[@role='row']//div[text()='"+vlanid+"']");
								Clickon(VLANIDvalue);
								VLANIDvalue.sendKeys(Keys.TAB);
								break outerloop;
							}

						} catch (StaleElementReferenceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}
					}
					Clickon(getwebelement("//div[@class='app-main']//div//button[text()='Next']"));
					Thread.sleep(3000);
				}
			}
			
		}else {

			System.out.println("No data available in table");
			Log.info("No data available in table");
			DriverTestcase.logger.log(LogStatus.FAIL, "No data available in table");
		}
	}



	//====================================== Common Methods ========================================


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



}
