package com.colt.qa.scripthelpers;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.Count;
import org.dom4j.DocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.colt.qa.driverlibrary.DriverHelper;
import com.colt.qa.driverlibrary.DriverTestcase;
import com.colt.qa.driverlibrary.Log;
import com.colt.qa.driverlibrary.XMLReader;
import com.colt.qa.reporter.ExtentTestManager;


public class DDI_Helper extends DriverHelper{
	
	public DDI_Helper(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	WebElement ChooseCustomer_Select, Next_Button, CreateOrderService_Text, Wildcard, Search_Field, EmergencyAreaID_Text, OKbtn;
	XMLReader xml = new XMLReader("src\\com\\colt\\qa\\pagerepository\\APT_DDIRange.xml");

	boolean orderdopdown, serviceTypedropdown, modularmspCheckbox, autocreateservicecheckbox, interfacespeeddropdown,
			servicesubtypesdropdown, availablecircuitsdropdown, nextbutton, A_EndTechnolnogy, B_Endtechnolnogy;
	SoftAssert sa = new SoftAssert();


	
	public void navigateToManageCustomerServicePage(String application) throws InterruptedException, DocumentException 
	{
		Clickon(getwebelement(xml.getlocator("//locators/"+ application + "/ManageCustomerServiceLink")));
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on 'Manage Customer Service Link");
		Log.info("Clicked on 'Manage Customer Service Link");
		Thread.sleep(2000);
		Log.info("=== MCS page navigated ===");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SearchService")));
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on 'Search Order/Service");
		Log.info("Clicked on 'Search Order/Service");
		Log.info("=== Search Order/Service navigated ===");



		Thread.sleep(2000);

		
		
		Thread.sleep(2000);
	}
	
	public void SearchTrunkName(String application ,String TrunkValue ) throws Exception 
	{
		//public void addtextFields_commonMethod(String application, String labelname, String xpathname, String expectedValueToAdd, XMLReader xml) throws InterruptedException, DocumentException, IOException {
		
		
		Clickon(getwebelement(xml.getlocator("//locators/"+ application + "/ManageCustomerServiceLink")));
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on 'Manage Customer Service Link");
		Log.info("Clicked on 'Manage Customer Service Link");
		Log.info("=== MCS page navigated ===");
		
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SearchService")));
		DriverTestcase.logger.log(LogStatus.PASS, "Step : Clicked on 'Search Order/Service");
		Log.info("Clicked on 'Search Order/Service");
		Log.info("=== Search Order/Service navigated ===");
		
		addtextFields_commonMethod(application, "Trunk Name", "TrunkName", TrunkValue, xml);
		
		scrolltoend();
		click_commonMethod(application, "Search", "Searchbtn", xml);
		click_commonMethod(application, "DDI checkbox", "DDICheckbox", xml);
		
		click_commonMethod(application, "Action", "searchDDi_actionDropdown", xml);
		click_commonMethod(application, "View", "searchDDI-viewLink", xml);
		Thread.sleep(15000);
	}
	
	public void selectTrunk(String application ,String TrunkValue ) throws Exception
	{
		//Thread.sleep(10000);
		scrolltoend();
		Thread.sleep(2000);
		WebElement Trunknumber1=null;
		Trunknumber1 = getwebelement(xml.getlocator("//locators/" +application+ "/selectTrunkName").replace("value", TrunkValue));
		boolean Trunk = Trunknumber1.isDisplayed();
		if(Trunk)
		{
			safeJavaScriptClick(Trunknumber1);

			//safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" +application+ "/TrunkValue")));
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Trunk number");
			Log.info("Clicked on Trunk number");
			
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" +application+ "/TrunkPanelAction")));
			
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Action Dropdown");
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" +application+ "/TrunkPanelView")));
			Thread.sleep(4000);
		}
		else
		{
			DriverTestcase.logger.log(LogStatus.PASS, "Not Clicked on Trunk number");

		}
		
				
	}
	
	public void showDDIRange(String application) throws Exception
	{
		//Thread.sleep(10000);
		WebElement ShowDDIRanges = getwebelement(xml.getlocator("//locators/" + application + "/ShowDDIRanges"));
		scrolltoview(ShowDDIRanges);
		Thread.sleep(2000);

		
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/ShowDDIRanges")));
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Show DDI Ranges");
		
		Thread.sleep(2000);
    	compareText(application,"Country Code","CountrycodeLabel","Country Code",xml);
    	compareText(application,"LAC","LacLabel","LAC",xml);
    	
    	compareText(application,"Main Number/Range Start-End","MainNumberLabel","Main Number/Range Start-End",xml);
    	compareText(application,"Extension digits","ExtensionLabel","Extension digits",xml);
    	
    	compareText(application,"Emerg Area","EmergLabel","Emerg Area",xml);
    	compareText(application,"Incoming Routing","IncomingLabel","Incoming Routing",xml);
    	
    	compareText(application,"IN GEO","InGeoLabel","IN GEO",xml);
	}

	public void UploadDDIRange(String application,String expected, String uploadFilePath) throws Exception
	
	{
		
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/UploadDDIRanges")));
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Upload DDI Ranges");
		Thread.sleep(2000);
		
    	compareText(application,"OK","OKbutton","OK",xml);
    	DriverTestcase.logger.log(LogStatus.PASS,"Step : OK button is present");

    	compareText(application,"Cancel","Cancelbtn","Cancel",xml);
    	DriverTestcase.logger.log(LogStatus.PASS,"Step : Cancel button is present");
    	
    	try {
    	WebElement ChoseFile = (getwebelement(
				xml.getlocator("//locators/" + application + "/ChosefileDDI")));
		sa.assertTrue(ChoseFile.isDisplayed(),"Chose File btn is not displayed");
		Log.info("ChoseFile is displayed");
		 Log.info("ChoseFile is displayed");
			DriverTestcase.logger.log(LogStatus.PASS, "Verified Chose File button is diplayed");
			
			 SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/ChosefileDDI")), uploadFilePath);
				DriverTestcase.logger.log(LogStatus.PASS, "File uploaded");
				Thread.sleep(2000);
				
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" +application+ "/OKbutton")));
				
				Thread.sleep(2000);
				
				Log.info("File Upload Message");
				
				WebElement ActFileuploadmsg = (getwebelement(xml.getlocator("//locators/" +application+ "/FileuploadSuccess")));
				if (ActFileuploadmsg.getText().equals("File Upload successfull."))
				{
					Log.info("Wildcarde Message : " + ActFileuploadmsg.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "File Upload message:" + ActFileuploadmsg.getText());
					Log.info("Message Displayed as:"+ActFileuploadmsg.getText());
					DriverTestcase.logger.log(LogStatus.PASS, "Step:File Upload message:" +ActFileuploadmsg.getText());

				}
				else
				{
					sa.assertTrue(ActFileuploadmsg.isDisplayed(),"Message not displayed");
					Log.info("File upload message not displayed");
					Log.info("File upload message not displayed");
					DriverTestcase.logger.log(LogStatus.PASS, "Step:File upload message not displayed" );

				}	
				
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
					
				Thread.sleep(1000);
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" +application+ "/Cancelbtn")));
				Thread.sleep(10000);
    	

		
	}
	
	public void downloadDDIRange(String application)throws Exception
	{
		
		scrolltoend();
		Thread.sleep(2000);
		
		WebElement ShowDDIRanges = getwebelement(xml.getlocator("//locators/" + application + "/ShowDDIRanges"));

		scrolltoview(ShowDDIRanges);
		Thread.sleep(5000);

		
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/DownlaodDDIRanges")));
		
//		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Download DDI Ranges");
		
		Thread.sleep(4000);
		
		isFileDownloaded_DDI("C:\\Users\\PKumar4-ADM\\Downloads", "ddiRanges_Tgid");
		
		Thread.sleep(2000);
	}
	
	
	
	 public boolean isFileDownloaded_DDI(String downloadPath, String fileName) {
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();

		  for (int i = 0; i < dirContents.length; i++) {
		      if (dirContents[i].getName().contains(fileName)) {
		          // File has been found, it can now be deleted:
		          //dirContents[i].delete();
		    	  
		    	  String downloadedFileName=dirContents[i].getName();
		    	  Log.info("Downloaded file name is displaying as: "+ downloadedFileName);
		    	  DriverTestcase.logger.log(LogStatus.PASS, "Downloaded file name is displaying as: "+ dirContents[i]);
		    	  
		          return true;
		      }
		          }
		      return false;
		  }
	 
	 
	public void duplicateDDIRange(String application) throws Exception
	{
//		CreateOrderService_Text = getwebelement(
//				xml.getlocator("//locators/" + application + "/CreateOrderService_Text"));
//		sa.assertTrue(CreateOrderService_Text.isDisplayed(), "CreateOrderService_Text  is not displayed");

		
//		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/DuplicateDDIRange")));
		click_commonMethod(application, "Duplicate DDI Range", "DuplicateDDIRange" , xml);
		
		
        WebElement DDIDuplicatemsg = getwebelement(xml.getlocator("//locators/" + application + "/DuplicateRecord"));
		
		boolean DDIDuplicateMsg=DDIDuplicatemsg.isDisplayed();
		if(DDIDuplicateMsg) {
			Log.info("DDI Range Duplicate message is displaying as:" +DDIDuplicatemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "DDI Range Duplicate message is displaying as:" +DDIDuplicatemsg.getText());
		}
		else {
	        WebElement DDInoDuplicatemsg = getwebelement(xml.getlocator("//locators/" + application + "/NoDuplicateRecord"));
	        DDInoDuplicatemsg.isDisplayed();

			Log.info("DDI Range Duplicate message is displaying as:" +DDInoDuplicatemsg.getText());
			DriverTestcase.logger.log(LogStatus.PASS, "DDI Range Duplicate message is displaying as:" +DDInoDuplicatemsg.getText());
		
		
		}
	}
	
	public void dDIRangeCount(String application ,String TrunkValue ) throws Exception
	{
		Count.set(Getattribute(getwebelement(xml.getlocator("//locators/" +application+ "/DDIcount")),"Value"));
		DriverTestcase.logger.log(LogStatus.PASS, "Count of DDI Ranges" +Count.get());

	}
	
	
	String mainWindow = driver.getWindowHandle();
	public void VerifyDDIRangefields(String application) throws Exception
	{
		Thread.sleep(10000);

//		scrolltoend();
//		Thread.sleep(1000);
//		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/DDIPanelAction")));
//		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Action Dropdown");
//		Thread.sleep(1000);
		
		scrolltoview(application, "Non Service Impacting header", "NonServiceImpacting_Header", xml);
		click_commonMethod(application, "Add DDI Range Link", "AddDDIRange", xml);
		//safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/AddDDIRange")));
//		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Add DDI Range");
//		Thread.sleep(2000);
		
		Set<String> allwindows = driver.getWindowHandles();
		Iterator<String> itr = allwindows.iterator();
		while(itr.hasNext())
		{
			String childWindow = itr.next();
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);
				Log.info(driver.switchTo().window(childWindow).getTitle());
				
				compareText(application,"Country Code","CreateDDICountry","Country Code",xml);
		    	
		    	compareText(application,"LAC","CreateDDILAC","LAC",xml);
		    	    	
		    	compareText(application,"Phone Numbers","CreateDDIPhoneNumbers","Phone Numbers",xml);

		    	compareText(application,"Main Number","CreateDDIMainnumber","Main Number",xml);

		    	compareText(application,"Range Start","CreateDDIRangeStart","Range Start",xml);
		    	
		    	compareText(application,"Range End","CreateDDIRangeEnd","Range End",xml);
		    	
		    	compareText(application,"Extension digits","CreateDDIExtension","Extension digits",xml);
		    	
		    	compareText(application,"Activate Incoming Routing","ActivateIncomingRouting","Activate Incoming Routing",xml);
		    	
		    	compareText(application,"IN GEO","CreateInGeo","IN GEO",xml);
		    	
//		    	compareText(application,"Add More","AddMore","Add More",xml);
		    	
		    	compareText(application,"Cancel","Cancelbtn","Cancel",xml);
		    	compareText(application,"OK","OKbutton","OK",xml);

				
			}
		}
		
		
    	

}
	public void createDDIRange(String application ,String CountrycodeValue ,String LACValue ,String MainNumbervalue,String RangestartValue, 
			String RangeEndValue, String ExtensionDigits, String ActivateIncomingRouting, String InGeo ) throws Exception
	
	{

		//addtextFields_commonMethod(application, "Country Code", "CountryCodeText", CountrycodeValue,xml);
		
		addtextFields_commonMethod(application, "LAC", "LacText", LACValue,xml);
		
		addtextFields_commonMethod(application, "Main Number", "MainnumberText", MainNumbervalue,xml);
		
		addtextFields_commonMethod(application, "Range Start", "RangeStartText", RangestartValue,xml);
		
		addtextFields_commonMethod(application, "Range End", "RangeEndText", RangeEndValue,xml);
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/" +application+ "/forwardarrow")));

	//Extension Digits	
		addtextFields_commonMethod(application, "Extension digits", "ExtensionText", ExtensionDigits,xml);
		
	//Emergency Area
		click_commonMethod(application, "Emergency Area Choose Button", "EmergencyAreaChooseButton", xml);
		click_commonMethod(application, "Emergency Area Search Button", "EmergencyAreaSearchButton", xml);
		Thread.sleep(7000);
		click_commonMethod(application, "Emergency Area ID Checkbox", "EmergencyAreaIDCheckbox", xml);

	//Active Incoming Routing checkbox		
		addCheckbox_commonMethod(application, "CheckboxRouting", "Activate Incoming Routing", ActivateIncomingRouting, "Yes", xml);
		
	//IN GEO checkbox	
		addCheckbox_commonMethod(application, "CheckboxInGeo", "IN GEO", InGeo, "no", xml);
		
		
	scrolltoend();	
		click_commonMethod(application, "OK", "OKbutton" , xml);
		
		driver.switchTo().window(mainWindow);

		Thread.sleep(2000);
		
		scrollToTop();
		Thread.sleep(1000);
		compareText(application, "Add DDI Range Success Message", "AddDDIRangeSuccessMessage", "DDI Range successfully created.", xml);
		
	}

	
	
	
	public void ViewDDIRange(String application, String CountrycodeValue, String LACValue, String MainNumbervalue, String RangestartValue,
			String RangeEndValue, String ExtensionDigits, String ActivateIncomingRouting, String InGeo, String PSXconfigurationValue) throws Exception
	{

		String activateincoming=null;
		String ingeo=null;
		
		Thread.sleep(10000);
		waitForpageload();
		
		WebElement DDIrangePanelHeader = getwebelement(xml.getlocator("//locators/" + application + "/DDIpanelHeader"));
		scrolltoview(DDIrangePanelHeader);
		Thread.sleep(5000);

		click_commonMethod(application, "Show DDI Range", "ShowDDIRanges", xml);
		Thread.sleep(5000);	

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/LacLabel")));
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/LacLabel")));
		
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/LACFilter")));
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/LACFilter")));
		DriverTestcase.logger.log(LogStatus.PASS, "Filterng LAC Value");
		Thread.sleep(3000);
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/LACFilter_Text")), LACValue);
		DriverTestcase.logger.log(LogStatus.PASS, "Entering LAC Value");
		Thread.sleep(3000);

//		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/DDIRadiobtn")));
//		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Radio button");
//		Thread.sleep(2000);
//		
//		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/DDIPanelAction")));
//		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Action Dropdown");
//		Thread.sleep(1000);

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/TrunkPanelView")));
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on View");
		Thread.sleep(2000);
		
		
		
	//compare label Names	
		compareText(application, "LAC", "LAClabel_viewPage", "LAC", xml);
		
		compareText(application, "Extension Digit", "extensionLabel_viewPage", "Extension digits", xml);
		
    	compareText(application,"Configuration","ConfigurationLabel","Configuration",xml);
    	
    	compareText(application,"PSX Configuration","PsxConfigurationLabel","PSX Configuration",xml);
    	
    	compareText(application,"Phone Numbers","PhoneNumerLabel","Phone Numbers",xml);
    	
    	compareText(application,"Emerg Area","ViewEmergLabel","Emerg Area",xml);
    	
    	compareText(application,"Back","Backbutton","Back",xml);
    	
    	
    //Activate incoming routing	
    	if(ActivateIncomingRouting.equalsIgnoreCase("yes")) {
    		activateincoming="Active";
    	}else {
    		activateincoming="Inactive";
    	}
    	
    //INGEO	
    	if(InGeo.equalsIgnoreCase("yes")) {
    		ingeo="Active";
    	}else {
    		ingeo="Inactive";
    	}
    	
    //compare the values	
    	compareText(application, "Country", "viewPage_countryValue", CountrycodeValue, xml);
    	
    	compareText(application, "LAC", "viewPage_LACvalue", LACValue, xml);
    	
    	compareText(application, "Extension Digit", "viewPage_extensionDigitVale", ExtensionDigits, xml);
    	
    	compareText(application, "Activate Incoming Routing", "viewPage_activateIncomingRouting", activateincoming, xml);
    	
    	compareText(application, "IN GEO", "viewPage_INGEOValue", ingeo, xml);
    
    	
    	scrolltoend();
    	
    //PSX Configuration
    	addDropdownValues_commonMethod(application, "PSX Configuration", "PSXconfigurationDropdown_viewDDI", PSXconfigurationValue, xml);
    	
    	click_commonMethod(application, "Execute", "executeButton_vieDDI", xml);
    	Thread.sleep(4000);
    	scrollToTop();
		
    	
    	//compareText(application, "Execute PSX Configuration Success Message", "AddRouteSuccessMessage", "Routes successfully created.", xml);
    	
    	try {
    		if ((getwebelement(xml.getlocator("//locators/" + application + "/DDIPSXConfigurationSuccessMessage")).isDisplayed()) ||
    		    	((getwebelement(xml.getlocator("//locators/" + application + "/DDIPSXConfigurationWarningMessage")).isDisplayed()))){
    					DriverTestcase.logger.log(LogStatus.INFO, "'Success or  Warning' message is displayed in View DDI Range screen1");
    					Log.info( "'Success or Warning' message is displayed in View DDI Range screen1");
    		}else if((getwebelement(xml.getlocator("//locators/" + application + "/DDIPSXConfigurationSuccessMessage")).isDisplayed()) &&
    				((getwebelement(xml.getlocator("//locators/" + application + "/DDIPSXConfigurationWarningMessage")).isDisplayed()))){
    				DriverTestcase.logger.log(LogStatus.FAIL, "'Success or  Warning' message is not displayed in View DDI Range screen1");
    				Log.info( "'Success or Warning' message is not displayed in View DDI Range screen1");
		}
	
    		
    	}catch(NoSuchElementException e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Success or Warning' message is not displayed in View DDI Range screen2");
			Log.info( "'Success or Warning' message is not displayed in View DDI Range screen2");
		}catch(Exception e) {
			e.printStackTrace();
			DriverTestcase.logger.log(LogStatus.FAIL, "'Success or Warning' message is not displayed in View DDI Range screen3");
			Log.info( "'Success or Warning' message is not displayed in View DDI Range screen3");
		}
    	
    	
    	scrolltoend();
    	Thread.sleep(2000);
    	click_commonMethod(application, "Back", "Backbutton", xml);
		Thread.sleep(3000);
    	sa.assertAll();
	}
	
	
	
	
	
	
	public void editDDIRange (String application, String LACValue, String ExtensionDigits) throws Exception
	{
		WebElement ShowDDIRanges = getwebelement(xml.getlocator("//locators/" + application + "/ShowDDIRanges"));
		scrolltoview(ShowDDIRanges);
		
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/ShowDDIRanges")));
		//Thread.sleep(5000);	
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Show DDI Ranges");

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/LacLabel")));
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/LacLabel")));
		
		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/LACFilter")));
		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/LACFilter")));
		DriverTestcase.logger.log(LogStatus.PASS, "Filterng LAC Value");
		Thread.sleep(5000);
		
		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/LACFilter_Text")), LACValue);		
		DriverTestcase.logger.log(LogStatus.PASS, "Entering LAC Value");
		Thread.sleep(5000);

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/DDIEdit")));
		DriverTestcase.logger.log(LogStatus.PASS, "Selected View from Action Dropdown");
		Thread.sleep(2000);
		
        compareText(application,"Country Code","CreateDDICountry","Country Code",xml);
    	
    	compareText(application,"LAC","CreateDDILAC","LAC",xml);
    	    	
    	compareText(application,"Phone Numbers","CreateDDIPhoneNumbers","Phone Numbers",xml);

    	compareText(application,"Main Number","CreateDDIMainnumber","Main Number",xml);

    	compareText(application,"Range Start","CreateDDIRangeStart","Range Start",xml);
    	
    	compareText(application,"Range End","CreateDDIRangeEnd","Range End",xml);
    	
    	compareText(application,"Extension digits","CreateDDIExtension","Extension digits",xml);
    	
    	compareText(application,"Activate Incoming Routing","ActivateIncomingRouting","Activate Incoming Routing",xml);
    	
    	compareText(application,"IN GEO","CreateInGeo","IN GEO",xml);
    	
    	//compareText(application,"Add More","AddMore","Add More",xml);
    	
    	compareText(application,"Cancel","Cancelbtn","Cancel",xml);
    	
    	compareText(application,"OK","OKbutton","OK",xml);
    	Thread.sleep(1000);
    	
		ClearAndEnterTextValue(application, "Extension Digit", "ExtensionText", ExtensionDigits, xml);

		Clickon(getwebelement(xml.getlocator("//locators/" +application+ "/OKbutton")));
		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on OK button:" );

		Thread.sleep(5000);
		compareText(application, "DDI Update Scuccess Message", "UpdateDDIRangeSuccessMessage", "DDI Range successfully updated.", xml);
		
	}
	   
	  
	      public void deleteDDIRange (String application, String LACValue) throws Exception
	      {
	    	  safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/ShowDDIRanges")));
	  		Thread.sleep(5000);	
	  		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Show DDI Ranges");

	  		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/LacLabel")));
	  		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/LacLabel")));
	  		
	  		Moveon(getwebelement(xml.getlocator("//locators/" + application + "/LACFilter")));
	  		Clickon(getwebelement(xml.getlocator("//locators/" + application + "/LACFilter")));
	  		DriverTestcase.logger.log(LogStatus.PASS, "Filterng LAC Value");
	  		Thread.sleep(5000);
	  		
	  		
	  		SendKeys(getwebelement(xml.getlocator("//locators/" + application + "/LACFilter_Text")), LACValue);
	  		DriverTestcase.logger.log(LogStatus.PASS, "Entering LAC Value");
	  		Thread.sleep(5000);

	  		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/DDIRadiobtn")));
	  		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Radio button");
	  		Thread.sleep(5000);
	  		
	  		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/DDIPanelAction")));
	  		DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Action Dropdown");
	  		Thread.sleep(1000);

	  		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/DDIDelete")));
	  		DriverTestcase.logger.log(LogStatus.PASS, "Selected Delete from Action Dropdown");
	  		Thread.sleep(2000);
	  		
	  		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/" + application + "/Deletebtn")));
			DriverTestcase.logger.log(LogStatus.PASS, "Clicked on Delete button");
			Thread.sleep(5000);


	      }
	      
	      public void searchDDIRange (String application) throws Exception
	      {
	    	  Log.info("select Manage Colt's Network");
	  		DriverTestcase.logger.log(LogStatus.PASS, "Step : clicked on 'Manage colt's Network' link");


	  		Thread.sleep(3000);
	  		
	  			Moveon(getwebelement(xml.getlocator("//locators/" + application + "/ManageColtNetworkLink")));
	  			Thread.sleep(3000);
	  			Log.info("Mouse hovered on Manage Colt Network ");
	  			DriverTestcase.logger.log(LogStatus.PASS, "Step : Mouse hovered on Manage Colt Network");
	  			
	  			Thread.sleep(2000);
	  			boolean isDisplayed = getwebelement(xml.getlocator("//locators/" + application + "/SearchDDI")).isDisplayed();
	  			sa.assertTrue(isDisplayed,"Search DDI Range not displayed ");
	  			
	  			Log.info("Search DDI Range not displayed ");
	  			DriverTestcase.logger.log(LogStatus.PASS, "Step :Search DDI Ranges displayed ");

	  			Clickon(getwebelement(xml.getlocator("//locators/" + application + "/SearchDDI")));
	  			Thread.sleep(2000);
	  			Log.info("=== Clicked on Search DDI Ranges ===");
	  			Log.info("Clicked on Search DDI Ranges");
	  			DriverTestcase.logger.log(LogStatus.PASS, "Step :Clicked on Search DDI Ranges");

	  			
	   		    DriverTestcase.logger.log(LogStatus.PASS, "Step:Navigated to Search DDI Ranges");


	  			Log.info("=== Navigated to Search DDI Ranges ===");
	      }
	      
	      public void fillfieldDDIRange (String application, String LACValue, String CountrycodeValue) throws Exception
	     
	      {
	      	compareText(application,"(You can use % as wildcard)","DDIWildcard","(You can use % as wildcard)",xml);
  
	    	  
	      	compareText(application,"ISD Code(Country Code)","DDIISdcode","ISD Code(Country Code)",xml);
	    	compareText(application,"STD Code(LAC)","DDISTdcode","STD Code(LAC)",xml);

	    	compareText(application,"Main Number/Range Start-End","DDIManageNmbr","Main Number/Range Start-End",xml);
	    	
			addtextFields_commonMethod(application, "ISD Code(Country Code)", "DDIISdcode_Text", CountrycodeValue,xml);
			
			addtextFields_commonMethod(application, "STD Code(LAC)", "DDILAC_Text", LACValue,xml);
			
			click_commonMethod(application, "Search", "Searchbtn", xml);
			Thread.sleep(3000);

	      }
	      
	      public void viewSearchDDIRange(String application, String LACValue, String CountrycodeValue, String trunkValue) throws Exception
	      {
	    	  scrolltoend();
	    	  Thread.sleep(2000);

		  			WebElement LACvalue=getwebelement(xml.getlocator("//locators/" +application+ "/selectLACvalue_searchFoeDDipage").replace("value", trunkValue));
//		  			Log.info();
		  			safeJavaScriptClick(LACvalue);
		  			
		  			Thread.sleep(2000);
					click_commonMethod(application, "Action", "searchForDDI_actionDropdown" , xml);
					Thread.sleep(1000);
					click_commonMethod(application, "view", "ssearchForDDI_viewLink", xml);
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
							Log.info("Message is verified. It is displaying as: "+alrtmsg);
							
						}else {
							
							DriverTestcase.logger.log(LogStatus.FAIL, "Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg +" .The Expected value is: "+ expected);
							Log.info("Message is displaying and it gets mismatches. It is displaying as: "+ alrtmsg);
						}
						
					}else {
						DriverTestcase.logger.log(LogStatus.FAIL, " Success Message is not displaying");
						Log.info(" Success Message is not displaying");
					}
					
				}catch(Exception e) {
					Log.info("failure in fetching success message - 'Service created Successfully'  ");
					DriverTestcase.logger.log(LogStatus.FAIL, expected+ " Message is not displaying");
					Log.info(expected+ " message is not getting dislpayed");
				}
			}


public void isDisplayed(String application, String xpath, String labelname, XMLReader xml) {
	boolean availability = false;

	try {
		Thread.sleep(1000);
		availability= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +"")).isDisplayed();
//		Log.info(availability);
		if (availability) {
			DriverTestcase.logger.log(LogStatus.PASS, "Step: '"+labelname+"' is displayed as expected");
			Log.info("Step: '"+labelname+"' is displayed as expected");
		}
		else {
			DriverTestcase.logger.log(LogStatus.FAIL, "Step: '"+labelname+"' is not displayed");
			Log.info("Step: '"+labelname+"' is not displayed");
			}

	} catch (Exception e) {
		DriverTestcase.logger.log(LogStatus.FAIL,"Step: '"+labelname+"' is not available to display");
		e.printStackTrace();
	}
}


public void implicitlyWait(String pageNavigation) {
	driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);			
	}
	
	public void implicitlyWait10S(String pageNavigation) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);			
		}
	public void webdriverWait(String application, String xpath, XMLReader xml) throws DocumentException, InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, 300);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xml.getlocator("//locators/" + application + "/"+ xpath +""))));
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
	}
	
	
	
	//public static Boolean isFileDownloaded(String fileName, String downloadspath) {
			public static Boolean isFileDownloaded(String fileName, String downloadspath) {

			boolean flag = false;
			//paste your directory path below
			//eg: C:\\Users\\username\\Downloads
			String dirPath = downloadspath; 
			File dir = new File(dirPath);
			File[] files = dir.listFiles();
			if (files.length == 0 || files == null) {
				Log.info("The directory is empty");
				DriverTestcase.logger.log(LogStatus.FAIL, "Step : Downloads folder is empty");
				flag = false;
			} else {
				for (File listFile : files) {
					if (listFile.getName().contains(fileName)) {
						Log.info(fileName + " is present");
						DriverTestcase.logger.log(LogStatus.PASS, "Step : '"+fileName+"' excel file is downloaded successfully");
						break;
					}
					flag = true;
				}
			}
			return flag;
		}
			
			
			public void scrolltoview (String application, String labelname, String xpath, XMLReader xml) throws InterruptedException, DocumentException {
				WebElement element = null;
				element= getwebelement(xml.getlocator("//locators/" + application + "/"+ xpath +""));

				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			

}

  